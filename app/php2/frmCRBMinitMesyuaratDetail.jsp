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
      <legend>MAKLUMAT MINIT MESYUARAT</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatImejan in $BeanMaklumatImejan)
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Nama Minit Mesyuarat</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNamaImej" type="text" id="txtNamaImej" value="$beanMaklumatImejan.namaImej" size="43" maxlength="100" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtCatatanImej" cols="50" rows="5" class="$inputTextClassPopup" id="txtCatatanImej" $readonlyPopup>$beanMaklumatImejan.catatanImej</textarea></td>
        </tr>
        #if ($modePopup != 'new')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Muat Turun Dokumen</td>
          <td width="1%">:</td>
          <td width="70%"><a href="#" onclick="cetakImej($idDokumen)" class="style2">$beanMaklumatImejan.namaFail</a> </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #end
        #end
        #if ($modePopup == 'new')
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Direktori Minit Mesyuarat</td>
          <td>:</td>
          <td><input id="fileupload" name="fileupload" type="file" size="40" $readonlyPopup2  class="$inputTextClassPopup" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><span class="style4"><i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik fail adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz fail yang lebih kecil.</span></span></td>
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
            <input name="cmdSimpan" type="button" value="Simpan" onClick="simpanDokumen('$idMesyuarat','$idPermohonan')" />
            <input name="cmdBatal" type="button" value="Batal" onClick="batalDokumen()" />
            #end
            #if ($modePopup == 'view')
            <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniDokumen()" />
            <input name="cmdHapus" type="button" value="Hapus" onClick="hapusDokumen()" >
            <input name="cmdBatal1" type="button" value="Kembali" onClick="batalDokumen()" />
            #end
            #if ($modePopup == 'update')
            <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskiniDokumen()" />
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
