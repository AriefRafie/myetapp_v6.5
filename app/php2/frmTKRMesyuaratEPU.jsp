<style type="text/css">
<!--
.style1 {
	color: #0000FF
}
.style2 {
	color: #FF0000
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idMesyuarat" type="hidden" id="idMesyuarat" value="$idMesyuarat"/>
  <input name="idKeputusan" type="hidden" id="idKeputusan" value="$idKeputusan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmTKRHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idMesyuarat != '')
  <tr>
    <td> #parse("app/php2/frmTKRMesyuaratEPUDetail.jsp") </td>
  </tr>
  #end
</table>

<script>
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function kemaskiniMesyuarat(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMesyuarat(){
	
	if(document.${formName}.txtTajukMesyuarat.value == ""){
		alert('Sila masukkan Tajuk Mesyuarat.');
		document.${formName}.txtTajukMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.txtBilMesyuarat.value == ""){
		alert('Sila masukkan Bil Mesyuarat.');
		document.${formName}.txtBilMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhMesyuarat.value == ""){
		alert('Sila masukkan Tarikh Mesyuarat.');
		document.${formName}.txtTarikhMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.socJamDari.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socJamDari.focus(); 
		return; 
	}
	if(document.${formName}.socMinitDari.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socMinitDari.focus(); 
		return; 
	}
	if(document.${formName}.socJamHingga.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socJamHingga.focus(); 
		return; 
	}
	if(document.${formName}.socMinitHingga.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socMinitHingga.focus(); 
		return; 
	}
	if (document.${formName}.idStatus.value == '1610191'){
		if(document.${formName}.socSyor.value == ""){
			alert('Sila pilih Keputusan Mesyuarat.');
			document.${formName}.socSyor.focus(); 
			return; 
		}
	}
	
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMesyuarat";
	doAjaxCall${formName}("");
}
function batalKemaskiniMesyuarat(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
</script>