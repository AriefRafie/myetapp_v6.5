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
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idPenawaranKJP" type="hidden" id="idPenawaranKJP" value="$idPenawaranKJP"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>  
  <input name="step" type="hidden" id="step" value="$step"/>
    <input name="modeDokumen" type="hidden" id="modeDokumen" value="$modeDokumen"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPNWHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200')
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupAgensi')
  <tr>
    <td> #parse("app/php2/frmPNWTawaranDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end

  <!-- UNTUK DISPLAY PADA MENU TAWARAN ATAU MENU PENERIMA TAWARAN -AIN- -->
  #if ($!{session.getAttribute("_portal_module")} == "ekptg.view.php2.FrmPNWTawaranView_SuratTawaran")
  <tr>
  	<td><fieldset id="tableReport" >
		<legend><strong>SURAT TAWARAN</strong></legend>
		<table width="100%" border="0" cellspacing="2" cellpadding="2">
  		<tr>
    		<td ><a href="#" class="style2" onClick="javascript:cetakSuratTawaran('$idFail')"> Surat Tawaran </a></td>
  		</tr>
  		<tr>
    		<td ><a href="#" class="style2" onClick="javascript:cetakSenaraiEdaran('$idFail')"> Senarai Edaran </a></td>
  		</tr>
		</table>
	</fieldset></td>
  </tr>
  #else
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI PENERIMA TAWARAN</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarAgensi()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No. Rujukan KJP</strong></td>
          <td width="35%"><strong>Kementerian</strong></td>
          <td width="30%"><strong>Agensi</strong></td>
          <td width="10%" align="center"><strong>Tarikh Terima</strong></td>
          
        </tr>
        #set ($senaraiAgensi = "")
        #if ($SenaraiAgensi.size() > 0)
        #foreach ($senaraiAgensi in $SenaraiAgensi)
        #if ($senaraiAgensi.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiAgensi.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiAgensi.bil</td>
          <td class="$row"><a href="javascript:paparAgensi($senaraiAgensi.idPenawaranKJP)" class="style2">$senaraiAgensi.noRujukanSuratKJP</a></td>
          <td class="$row">$senaraiAgensi.kementerian</td>
          <td class="$row">$senaraiAgensi.agensi</td>
          <td class="$row" align="center">$senaraiAgensi.tarikhTerima</td>
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
    <td align="center">    
 #if ($idStatus == '1610210')
    <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:seterusnya()"/>
    <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="javascript:gotoBatalPermohonan()"/>    
  #end  
<!--     <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/> -->
    </td>
  </tr>
</table>
    </td>
  </tr>
  #end
</table>

<script>
function batalDokumen(){
	document.${formName}.modePopup.value = "update";
	document.${formName}.flagPopup.value = "openPopupAgensi";
	document.${formName}.modeDokumen.value = "noDokumen";
	doAjaxCall${formName}("");
}

function simpanDokumen() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	var idPenawaranKJP = document.${formName}.idPenawaranKJP.value;
// 	var namaImej = document.${formName}.testNamaFile.value;
//  	var catatanImej = document.${formName}.testCatatanFile.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPNWTawaranView&idPenawaranKJP="+idPenawaranKJP+dopost+"&flagPopup=openPopupAgensi&modePopup=update&hitButton=simpanDokumen";
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}

function tambahMinitMesyuarat(){
	document.${formName}.flagPopup.value = "openPopupAgensi";
	document.${formName}.modePopup.value = "newMinit";
	doAjaxCall${formName}("");
}

function hapusDokumen(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openPopupAgensi";
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButton.value = "hapusDokumen";
	doAjaxCall${formName}("");
}

function batalKemaskiniDokumen(){
	document.${formName}.modePopup.value = "update";
	document.${formName}.flagPopup.value = "openPopupAgensi";
// 	flagPopup=openMesyuarat&modePopup=view
	doAjaxCall${formName}("");
}

function simpanKemaskiniDokumen() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	var idPenawaranKJP = document.${formName}.idPenawaranKJP.value;
	var idDokumen = document.${formName}.idDokumen.value;
// 	var namaImej = document.${formName}.testNamaFile.value;
//  	var catatanImej = document.${formName}.testCatatanFile.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPNWTawaranView&idPenawaranKJP="+idPenawaranKJP+"&idDokumen="+idDokumen+dopost+"&flagPopup=openPopupAgensi&modePopup=view&hitButton=simpanKemaskiniDokumen";
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}

function kemaskiniDokumen(){
	document.${formName}.modePopup.value = "updateMinit";
	doAjaxCall${formName}("");
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function paparAgensi(id) {
	document.${formName}.idPenawaranKJP.value = id;
	document.${formName}.flagPopup.value = "openPopupAgensi";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function daftarAgensi() {
	document.${formName}.flagPopup.value = "openPopupAgensi";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function simpanAgensi(){
 	if(document.${formName}.txtNoRujukanKJP.value == ""){
 		alert('Sila masukkan No. Rujukan Surat.');
   		document.${formName}.txtNoRujukanKJP.focus(); 
 		return; 
 	}
	if(document.${formName}.txtTarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.txtTarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih Kementerian.');
  		document.${formName}.socKementerian.focus(); 
		return; 
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih Agensi.');
  		document.${formName}.socAgensi.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanKegunaan.value == ""){
		alert('Sila masukkan Tujuan Kegunaan.');
  		document.${formName}.txtTujuanKegunaan.focus(); 
		return; 
	}
	/* if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Imej yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	} */

	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openPopupAgensi";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanAgensi";
	
	var data = create_request_string(document.${formName});
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPNWTawaranView&"+data;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function batalAgensi() {
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniAgensi() {
	document.${formName}.flagPopup.value = "openPopupAgensi";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniAgensi() {
	document.${formName}.flagPopup.value = "openPopupAgensi";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniAgensi(){
 	if(document.${formName}.txtNoRujukanKJP.value == ""){
 		alert('Sila masukkan No. Rujukan Surat.');
   		document.${formName}.txtNoRujukanKJP.focus(); 
 		return; 
 	}
	if(document.${formName}.txtTarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.txtTarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih Kementerian.');
  		document.${formName}.socKementerian.focus(); 
		return; 
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih Agensi.');
  		document.${formName}.socAgensi.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanKegunaan.value == ""){
		alert('Sila masukkan Tujuan Kegunaan.');
  		document.${formName}.txtTujuanKegunaan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openPopupAgensi";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniAgensi";
	doAjaxCall${formName}("");
}
function hapusAgensi(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusAgensi";
	doAjaxCall${formName}("");
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
function cetakSuratTawaran(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=PNWSuratTawaran";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSenaraiEdaran(idFail) {
	var url = "../servlet/ekptg.report.php2.PNWSenaraiEdaran?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakImej(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>

