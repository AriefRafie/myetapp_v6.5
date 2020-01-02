#set($saizketeranganAduan="500")
#set($saizsumberAduan="500")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatAduan in $BeanMaklumatAduan)
  <tr>
    <td width="1%">#if ($mode!= 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Keterangan Aduan</td>
    <td width="1%">:</td>
    <td width="70%" rowspan="4" valign="top"><textarea name="txtKeteranganAduan" id="txtKeteranganAduan" rows="5" cols="50" $readonly class="$inputTextClass">$beanMaklumatAduan.keteranganAduan</textarea></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if ($mode == 'update')
  #end
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Catatan</td>
    <td width="1%">:</td>
    <td rowspan="4" valign="top"><textarea name="txtSumberAduan" id="txtSumberAduan" rows="5" cols="50" $readonly class="$inputTextClass">$beanMaklumatAduan.sumberAduan</textarea>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if ($mode == 'update')
  #end
  <tr>
    <td colspan="4" valign="bottom"></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniMaklumatAduan()"/>
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpankemaskiniMaklumatAduan()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalMaklumatAduan()"/>
      #end </td>
  </tr>
  #end
</table>
<script>
function kemaskiniMaklumatAduan() {
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function simpankemaskiniMaklumatAduan() {
	if(document.${formName}.txtKeteranganAduan.value == ""){
		alert('Sila masukkan Keterangan Aduan.');
  		document.${formName}.txtKeteranganAduan.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatAduan";
	document.${formName}.submit();
}
function batalMaklumatAduan() {
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
</script>
