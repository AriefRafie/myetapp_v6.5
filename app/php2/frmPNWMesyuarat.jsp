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
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="hitButton" type="hidden" id="hitButton"/>  
  <input name="step" type="hidden" id="step" value="$step"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPNWHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610210')
  #if ($flagPopup == 'openMesyuarat')
  <tr>
    <td> #parse("app/php2/frmPNWMesyuaratDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI MESYUARAT</strong></legend>
      <table align="center" width="100%">
        ##<tr>
        ##  <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarMesyuarat()"/></td>
        ##</tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="60%"><strong>Tajuk Mesyuarat</strong></td>
          <td width="10%"><strong>Bil Mesyuarat</strong></td>
          <td width="10%" align="center"><strong>Tarikh</strong></td>
          <td width="15%"><strong>Syor</strong></td>
        </tr>
        #set ($senaraiMesyuarat = "")
        #if ($SenaraiMesyuarat.size() > 0)
        #foreach ($senaraiMesyuarat in $SenaraiMesyuarat)
        #if ($senaraiMesyuarat.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiMesyuarat.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiMesyuarat.bil</td>
          <td class="$row"><a href="javascript:paparMesyuarat($senaraiMesyuarat.idMesyuarat)" class="style1">$senaraiMesyuarat.tajukMesyuarat</a></td>
          <td class="$row">$senaraiMesyuarat.bilMesyuarat</td>
          <td class="$row" align="center">$senaraiMesyuarat.tarikhMesyuarat</td>
          <td class="$row">$senaraiMesyuarat.syor</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center">
    #if($idStatus == '1610201')
    <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/>
    <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
    #end</td>
  </tr>
</table>

<script>
function seterusnya(id){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
function paparMesyuarat(id){
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idMesyuarat.value = id;
	doAjaxCall${formName}("");
}
function daftarMesyuarat(){
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function simpanMesyuarat(){
	
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
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMesyuarat";
	doAjaxCall${formName}("");
}
function batalMesyuarat(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniMesyuarat(){
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function hapusMesyuarat(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMesyuarat";
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
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMesyuarat";
	doAjaxCall${formName}("");
}
function batalKemaskiniMesyuarat(){
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function kembali(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function simpanDokumenMM(idMesyuarat,idPermohonan) {
	
	if(document.${formName}.fileuploadMM.value == ""){
		alert('Sila pilih Fail yang Ingin Dimuatnaik.');
  		document.${formName}.fileuploadMM.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPNWMesyuaratView&hitButton=simpanDokumenMM&idPermohonan="+idPermohonan+"&idMesyuarat="+idMesyuarat+"&flagPopup=openMesyuarat&modePopup=view"+dopost;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function cetakDokumen(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>