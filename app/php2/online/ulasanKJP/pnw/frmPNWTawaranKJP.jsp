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
  ##if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPNWHeader.jsp") </td>
  </tr>
  ##elseif ($idFail == '' )
  <!-- <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr> -->
  ##end
  
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200')
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupAgensi')
  <tr>
    <td> #parse("app/php2/online/ulasanKJP/pnw/frmPNWTawaranDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end

  <!-- UNTUK DISPLAY PADA MENU TAWARAN ATAU MENU PENERIMA TAWARAN -->
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
    
      #if ($!bilSenaraiAgensi < 1)
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarAgensi()"/></td>
        </tr>
   
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No Rujukan</strong></td>
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
          <td class="$row"><a href="javascript:paparAgensi($senaraiAgensi.idPenawaranKJP)" class="style2">$senaraiAgensi.noRujukanKJP</a></td>
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
        #else
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No Rujukan</strong></td>
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
          <td class="$row"><a href="javascript:paparAgensi($senaraiAgensi.idPenawaranKJP)" class="style2">$senaraiAgensi.noRujukanKJP</a></td>
          <td class="$row">$senaraiAgensi.kementerian</td>
          <td class="$row">$senaraiAgensi.agensi</td>
          <td class="$row" align="center">$senaraiAgensi.tarikhTerima</td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
    <!-- <td align="center">    
 #if ($idStatus == '1610210')
    <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Hantar" onclick="javascript:seterusnya()"/>
    <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="javascript:gotoBatalPermohonan()"/>    
  #end  
    <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
    </td> -->
  </tr>
</table>
    </td>
  </tr>
  #end
  	#end
  	<td align="center">
  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>
  </td>
</table>
<script>
function paparFail(idFail,idStatus) {
	document.${formName}.idFail.value = idFail;
	document.${formName}.idStatus.value = idStatus;
	document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.online.FrmPNWTawaranKJPView";
	document.${formName}.actionPenawaran.value = "paparFail";
	//document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function kembali() {
	document.${formName}.flagPopup.value = "";
	document.${formName}.submit();
}
function daftarAgensi() {
	document.${formName}.flagPopup.value = "openPopupAgensi";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function simpanAgensi(){
 	if(document.${formName}.txtNoRujukanKJP.value == ""){
 		alert('Sila masukkan No Rujukan Surat KJP.');
   		document.${formName}.txtNoRujukanKJP.focus(); 
 		return; 
 	}
	if(document.${formName}.txtTarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.txtTarikhTerima.focus(); 
		return; 
	}
	/* if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih Kementerian.');
  		document.${formName}.socKementerian.focus(); 
		return; 
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih Agensi.');
  		document.${formName}.socAgensi.focus(); 
		return; 
	} */
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
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWTawaranKJPView&"+data;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
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
function kemaskiniAgensi() {
	document.${formName}.flagPopup.value = "openPopupAgensi";
	document.${formName}.modePopup.value = "update";
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
 		alert('Sila masukkan No Rujukan Surat KJP.');
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
</script>
