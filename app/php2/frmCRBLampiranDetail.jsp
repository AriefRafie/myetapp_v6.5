<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><fieldset>
      <legend>MAKLUMAT LAMPIRAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatLampiran in $BeanMaklumatLampiran)
        #if ($modePopup != 'new')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Muat Turun Lampiran</td>
          <td width="1%">:</td>
          <td width="70%"><a href="#" onclick="cetakImej($idDokumen)" class="style2">$beanMaklumatLampiran.namaFail</a> </td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">&nbsp;</td>
          <td width="1%">&nbsp;</td>
          <td width="70%">&nbsp;</td>
        </tr>
        #end
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Nama Lampiran</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNamaLampiran" type="text" id="txtNamaLampiran" value="$beanMaklumatLampiran.namaLampiran" size="43" maxlength="100" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtCatatanLampiran" cols="50" rows="5" class="$inputTextClassPopup" id="txtCatatanLampiran" $readonlyPopup>$beanMaklumatLampiran.catatanLampiran</textarea></td>
        </tr>
        #end
        #if ($modePopup == 'new')
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Direktori Lampiran</td>
          <td>:</td>
          <td><input id="fileuploadLampiran" name="fileuploadLampiran" type="file" size="40" $readonlyPopup2  class="$inputTextClassPopup" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><span class="style4"><i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik Lampiran adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz Lampiran yang lebih kecil.</span></span></td>
        </tr>
        #end
        #if ($modePopup != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> #if ($modePopup == 'new')
            <input name="cmdSimpan" type="button" value="Simpan" onClick="simpanLampiran('$idLaporanTanah','$idPermohonan')" />
            <input name="cmdBatal" type="button" value="Batal" onClick="batalDokumen()" />
            #end
            #if ($modePopup == 'view')
            <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniDokumen()" />
            <input name="cmdHapus" type="button" value="Hapus" onClick="hapusDokumen()" >
            <input name="cmdBatal1" type="button" value="Kembali" onClick="batalDokumen()" />
            #end
            #if ($modePopup == 'update')
            <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskiniLampiran()" />
            <input name="cmdBatal" type="button" value="Batal" onClick="batalDokumen()" />
            #end </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
