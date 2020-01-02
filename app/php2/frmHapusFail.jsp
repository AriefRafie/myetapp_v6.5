<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>

  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
   <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="step" type="hidden" id="step" value="$step"/>
  
  <input name="hitButton" type="hidden" id="hitButton" />

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>HAPUS FAIL</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Tarikh Hapus</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="tarikhHapus" id="tarikhHapus" onBlur="check_date(this);" size="9" />
            <a href="javascript:displayDatePicker('tarikhHapus',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
        </tr>
        <tr>
          <td valign="top"><span class="style1">*</span></td>
          <td valign="top">Sebab Hapus</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtSebab" id="txtSebab" rows="5" cols="50" onBlur="this.value=this.value.toUpperCase();"></textarea></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="doHapusFail()"/>
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doKembali()"/>
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>

<script>
function doHapusFail(){
	if(document.${formName}.tarikhHapus.value == ""){
		alert('Sila masukkan Tarikh Hapus Fail.');
  		document.${formName}.tarikhHapus.focus(); 
		return; 
	}
	if(document.${formName}.txtSebab.value == ""){
		alert('Sila masukan Sebab Hapus Fail.');
  		document.${formName}.txtSebab.focus(); 
		return; 
	}			
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "doHapusFail";
	document.${formName}.submit();
}
function doKembali() {
	document.${formName}.step.value = "";
	document.${formName}.submit();
}
</script>
