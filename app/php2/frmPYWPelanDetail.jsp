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
      <legend>MAKLUMAT PELAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPelan in $BeanMaklumatPelan)
        #if ($modePopup != 'new') 
        #if ($beanMaklumatPelan.jenisMime == 'image')  
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">&nbsp;</td>
          <td width="1%">&nbsp;</td>
          <td width="70%"><img src="../servlet/ekptg.view.php2.FrmDisplayImage?id=$idDokumen" alt="Pelan Pelan" border="1" width="250" height="250" onclick="cetakPelan($idDokumen)"/></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">&nbsp;</td>
          <td width="1%">&nbsp;</td>
          <td width="70%">&nbsp;</td>
        </tr>
        #else
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Lampiran</td>
          <td width="1%">:</td>
          <td width="70%"><a href="javascript:cetakPelan($idDokumen)" class="style2"><b>MUAT TURUN PELAN</b></a></td>
        </tr>
        #end        
        #end
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Nama Pelan</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNamaPelan" type="text" id="txtNamaPelan" value="$beanMaklumatPelan.namaPelan" size="43" maxlength="100" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtCatatanPelan" cols="50" rows="5" class="$inputTextClassPopup" id="txtCatatanPelan" $readonlyPopup>$beanMaklumatPelan.catatanPelan</textarea></td>
        </tr>
        #end
        #if ($modePopup == 'new')
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Direktori Pelan</td>
          <td>:</td>
          <td><input id="fileupload" name="fileupload" type="file" size="40" $readonlyPopup2  class="$inputTextClassPopup" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><span class="style4"><i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik imej adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz imej yang lebih kecil.</span></span></td>
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
            <input name="cmdSimpan" type="button" value="Simpan" onClick="simpanPelan('$idLaporanTanah','$idPermohonan')" />
            <input name="cmdBatal" type="button" value="Batal" onClick="batalPelan()" />
            #end
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
            #if ($modePopup == 'view')
            <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniPelan()" />
            <input name="cmdHapus" type="button" value="Hapus" onClick="hapusPelan()" >
            <input name="cmdBatal" type="button" value="Kembali" onClick="batalDokumen()" />
            #end
            #end
            #if ($modePopup == 'update')
            <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskiniPelan()" />
            <input name="cmdBatal" type="button" value="Batal" onClick="batalKemaskiniPelan()" />
            #end </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
