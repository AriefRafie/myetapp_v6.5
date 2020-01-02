<style type="text/css">
<!--
.style1 {
	color: #0000FF
}
.style2 {
	color: #FF0000
}
.style3 {
	color: #FF0000;
	font-weight: bold;
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
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="checkPampasan" type="hidden" id="checkPampasan" value= "$checkPampasan"/>
  <input name="checkSyor" type="hidden" id="checkSyor" value= "$checkSyor"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPLPHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200')
  #if ($flagPopup == 'openMesyuarat')
  <tr>
    <td> #parse("app/php2/frmPLPMesyuaratDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MESYUARAT</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">BENTUK PAMPASAN</li>     
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
           <!-- START SENARAI MESYUARAT -->
           <table width="100%" border="0" cellspacing="2" cellpadding="2">
   			<tr>
    		  <td><fieldset>
      			<legend><strong>SENARAI MESYUARAT</strong></legend>
      			<table align="center" width="100%">
	        		<tr>
	          			<td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarMesyuarat()"/></td>
	        		</tr>
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
			          <td height="21" align="center" class="row1">&nbsp;</td>
			          <td class="row1">Tiada Rekod</td>
			          <td class="row1">&nbsp;</td>
			          <td class="row1">&nbsp;</td>
			          <td class="row1">&nbsp;</td>
			        </tr>
			        #end
      			</table>
      			</fieldset></td>
  			  </tr>
			  </table></div>
 			  <!-- END SENARAI MESYUARAT -->
          <div class="TabbedPanelsContent">
           #parse("app/php2/frmPLPMesyuaratSenarai.jsp") </div>
        </div>
      </div></td>
  </tr>
  #end
  <tr>
    <td align="center">
    #if($idStatus == '1610201')
    <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/>
    <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
    #end
    #if ($mode == 'view' && $selectedTabUpper == '1')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniPampasan()"/>
      #end
    #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="simpanKemaskiniPampasan()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalPampasan()"/>
      #end 
    </td>
  </tr>
</table>
<script type="text/javascript">
#if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeLokasiMesyuarat() {
	doAjaxCall${formName}("doChangeLokasiMesyuarat");
}
function seterusnya(){
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
function doChangeTabUpper(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.submit();
}
function onChangeValue(str){
  
 document.${formName}.checkPampasan.value = str.value;
 doAjaxCall${formName}("onChangeValue");	
 
}
function kemaskiniPampasan() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");	
}
function batalPampasan() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPampasan(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpan";
	document.${formName}.submit();
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
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPLPMesyuaratView&hitButton=simpanDokumenMM&idPermohonan="+idPermohonan+"&idMesyuarat="+idMesyuarat+"&flagPopup=openMesyuarat&modePopup=view"+dopost;
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
function onChangeValue(str){
	 document.${formName}.checkSyor.value = str.value;
	 doAjaxCall${formName}("onChangeValue");	
}
function cetakPLPSuratLulusBersyarat(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=PLPSuratLulusBersyarat";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPLPSuratTolak(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratTolak";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>