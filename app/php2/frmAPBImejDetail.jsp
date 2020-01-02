<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<!-- saiz text -->
#set($saizTxtCatatan = "500")
#foreach($beanMaklumatImej in $BeanMaklumatImej)
   #set($idImejanTanah = $beanMaklumatImej.idImejanTanah)
   #set($txtNamaImej = $beanMaklumatImej.txtNamaImej)
   #set($txtCatatan = $$beanMaklumatImej.txtCatatan)
#end
#if ($modePopup != 'new')
<fieldset>
<legend>IMEJ</legend>
<table width="100%" border="0">
  <tr>
    <td align="center"><p><img src="../servlet/ekptg.view.php2.FrmPYWDisplayImej?id=$idDokumenUpload" alt="Imej Pelan" border="1" width="250" height="250"/></p></td>
  </tr>
  <tr>
    <td align="center"><input type="button" name="btnImejPenuh" id="btnImejPenuh" value="Imej Penuh" onclick="cetakImej($idDokumenUpload)" />
    </td>
  </tr>
</table>
#end
</fieldset>
<fieldset>
<legend>PERINCIAN IMEJ</legend>
<table width="100%" border="0">
  #if ($modePopup == 'new')
  <tr>
    <td width="1%"><span class="style1">*</span></td>
    <td width="28%">Direktori</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input id="fileupload" name="fileupload" type="file" size="40" $readonlyPopup2  class="$inputTextClassPopup" />
      </label></td>
  </tr>
  #end
  #if ($modePopup == 'new')
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"><span class="style4"><i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik imej adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz imej yang lebih kecil.</span></span></td>
  </tr>
  #end
  <tr>
    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">Nama Imej</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><input name="txtNamaImej" type="text" class="$inputTextClassPopup" id="txtNamaImej" value="$txtNamaImej" size="43" maxlength="100" $readonlyPopup></td>
  </tr>
  <tr>
    <td valign="top" width="1%">&nbsp;</td>
    <td valign="top" width="28%">Catatan</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><textarea name="txtCatatan" cols="43" rows="5" class="$inputTextClassPopup" id="txtCatatan" $readonlyPopup oonKeyUp="textCounter(this.form.txtCatatan,this.form.remLen13,$!saizTxtCatatan);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen13,$!saizTxtCatatan);" >$txtCatatan
</textarea></td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">Baki Aksara :&nbsp;
        <input type="text" readonly="readonly" class="disabled" name="remLen13" size="3" maxlength="3" value="$!saizTxtCatatan" /></td>
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
      <input name="cmdSimpan" type="button" value="Simpan" onClick="simpanDokumen('$idUlasanTeknikal','$idPermohonan')" />
      <input name="cmdBatal" type="button" value="Batal" onClick="doBatal()" />
      #end
      #if ($modePopup == 'view')
      <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniDokumen()" />
      <input name="cmdHapus" type="button" value="Hapus" onClick="hapusMaklumatDokumen()" >
      <input name="cmdBatal" type="button" value="Kembali" onClick="doBatal()" />
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskiniDokumen()" />
      <input name="txtBatal" type="button" value="Batal" onClick="doBatal()" />
      #end </td>
  </tr>
</table>
</fieldset>
