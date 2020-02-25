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
  
  <input name="step" type="hidden" id="step" value="$step"/>
  
  <input name="hitButton" type="hidden" id="hitButton" />

<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>    
      <legend>PEMBETULAN MAKLUMAT</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%" valign="top"><span class="style1">*</span></td>
          <td width="28%" valign="top">Catatan</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" onBlur="this.value=this.value;"></textarea></td>
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
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Hantar" onClick="doPembetulanMaklumat()"/>
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doKembali()"/>          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>

<script>
function doPembetulanMaklumat(){
	if(document.${formName}.txtCatatan.value == ""){
		alert('Sila Masukkan Catatan.');
  		document.${formName}.txtCatatan.focus(); 
		return; 
	}
				
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "doPembetulanMaklumat";
	document.${formName}.submit();
}
function gotoSenaraiFail(){
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailView";
	document.${formName}.submit();
}
function doKembali() {
	document.${formName}.step.value = "";
	document.${formName}.submit();
}
</script>
