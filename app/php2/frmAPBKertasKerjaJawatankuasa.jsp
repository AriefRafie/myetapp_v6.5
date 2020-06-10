<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idKertasKerjaApb" type="hidden" id="idKertasKerjaApb" value="$idKertasKerjaApb"/>
  <input name="idMesyuarat" type="hidden" id="idMesyuarat" value="$idMesyuarat"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td><div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '' && $flagOpenDetail)
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>PENYEDIAAN KERTAS JAWATANKUASA ONE STOP CENTRE</strong></legend>
      #parse("app/php2/frmAPBKertasKerjaJawatankuasaDetail.jsp")
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #elseif ($idFail != '')
  <tr>
    <td> 
      <div class="warning"><strong>STATUS FAIL MASIH DI PERINGKAT $!status</strong></div></td>
  </tr>
  #end
</table>

<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI DOKUMEN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakKertasKerjaJawatanKuasa('$idFail')"> Cetakan Ulasan Jabatan Teknikal </a></td>
  </tr>
</table>
</fieldset>

<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakKertasKerjaJawatanKuasa(idFail) {
	var url = "../servlet/ekptg.report.php2.APBKertasKerjaJawatankuasa?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function doKemaskini() {
	document.${formName}.mode.value = "update";
	document.${formName}.submit();
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function doSimpanKemaskiniMaklumatKertasRingkas() {
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatKertasRingkas";
	document.${formName}.submit();
}
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
</script>
