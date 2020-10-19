<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="flagDetail" type="hidden" id="flagDetail" value="$flagDetail"/>
  <input name="checkTanah" type="hidden" id="checkTanah" value= "$checkTanah"/>
  <input name="idPenawaranKJP" type="hidden" id="idPenawaranKJP" value="$idPenawaranKJP"/>
 <!-- <input name="idFail" type="text" id="idFail" value="$idFail"/>
  <input name="idStatus" type="text" id="idStatus" value="$idStatus"/> --> 
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Fail : </td>
          <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" >
            <input type="hidden" name="idFail" />
            <input type="hidden" name="idPermohonan" />
            <input type="hidden" name="idStatus" />
            <input type="hidden" name="actionPenawaran" />
            #if($flagDetail == '') <a href="javascript:bukaCarian();" class="style1">Buka Carian Terperinci </a> #else <a href="javascript:tutupCarian();" class="style1">Tutup Carian Terperinci </a> #end </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tajuk Fail : </td>
          <td width="70%"><input name="txtTajukFail" id="txtTajukFail" type="text" value="$txtTajukFail" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Penawar : </td>
          <td width="70%"><input name="txtPemohon" id="txtPemohon" type="text" value="$txtPemohon" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Terima : </td>
          <td width="70%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$txdTarikhTerima" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
                <tr>
          <td width="30%" height="24" scope="row" align="right">Status Tanah: </td>
		  <td width="70%">

          <select name="socTanah" id="socTanah" onChange="onChangeValue(this)">
				#if ($checkTanah == '1')
                <option value="0">SILA PILIH</option>
				<option value="1" selected>HAKMILIK</option>
				<option value="2">RIZAB</option>
                
                #elseif($checkTanah == '2')
                <option value="0">SILA PILIH</option>
				<option value="1" >HAKMILIK</option>
				<option value="2" selected>RIZAB</option>
                #else
                <option value="0" selected>SILA PILIH</option>
				<option value="1">HAKMILIK</option>
				<option value="2">RIZAB</option>
                #end
			</select></td>
        </tr>
        #if ($checkTanah == '1')
        <tr>
          <td scope="row" align="right"> No Hakmilik :</td>
          <td><input name="txtNoHakmilik" id="txtNoHakmilik" type="text" value="$txtNoHakmilik" size="30" maxlength="50" /></td>
        </tr>
        #elseif($checkTanah == '2')
        <tr>
          <td scope="row" align="right"> No Warta :</td>
          <td><input name="txtNoWarta" id="txtNoWarta" type="text" value="$txtNoWarta" size="30" maxlength="50"/></td>
        </tr>
        #end
        <tr>
          <td scope="row" align="right">Status :</td>
          <td>$selectStatus</td>
        </tr>
        #if($flagDetail == 'buka')
       <tr style="display:none">
          <td scope="row" align="right">No Pegangan Hakmilik :</td>
          <td><input name="txtNoPegangan" id="txtNoPegangan" type="text" value="$txtNoPegangan" size="30" maxlength="50"/></td>
        </tr>
        <tr style="display:none">
          <td scope="row" align="right"> Jenis Hakmilik :</td>
          <td>$selectJenisHakmilik</td>
        </tr>
        <tr>
          <td scope="row" align="right">Jenis Lot :</td>
          <td>$selectLot</td>
        </tr>
        <tr>
          <td scope="row" align="right">No Lot :</td>
          <td><input name="txtNoLot" id="txtNoLot" type="text" value="$txtNoLot" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right">Negeri :</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td scope="row" align="right">Daerah :</td>
          <td>$selectDaerah</td>
        </tr>
        <tr>
          <td scope="row" align="right">Mukim :</td>
          <td>$selectMukim</td>
        </tr>
        <tr>
          <td scope="row" align="right">Kementerian :</td>
          <td>$selectKementerian</td>
        </tr>
        <tr>
          <td scope="row" align="right">Agensi :</td>
          <td>$selectAgensi</td>
        </tr>
        #end
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan('$flagDetail')"></td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI PERMOHONAN</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        
        <tr class="table_header" align="center">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>No Fail</strong></td>
          <td width="35%"><strong>Tajuk Fail</strong></td>
          <td width="10%"><strong>Negeri</strong></td>
          <td width="8%" align="center"><strong>Tarikh Terima</strong></td>
          <td width="8%" align="center"><strong>Tarikh Buka Fail</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="10%"><strong>Daftar Oleh</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiFail.size() > 0)
        #foreach ($list in $SenaraiFail)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparFail('$list.idFail','$list.idStatus')" class="style1">$list.noFail</a></td>
          <td class="$row">$list.tajukFail</td>
          <td class="$row">$list.namaNegeri</td>
          <td class="$row" align="center">$list.tarikhTerima</td>
          <td class="$row" align="center">$list.tarikhBukaFail</td>
          <td class="$row">$list.status</td>
          <td class="$row">$list.userLogin</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function onChangeValue(str){
  
 document.${formName}.checkTanah.value = str.value;
 
 doAjaxCall${formName}("onChangeValue");	
 
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function bukaCarian(){
	document.${formName}.flagDetail.value = "buka";
	document.${formName}.actionPenawaran.value = "";
	doAjaxCall${formName}("");
}
function tutupCarian(){
	document.${formName}.flagDetail.value = "";
	document.${formName}.actionPenawaran.value = "";
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtTajukFail.value = "";
	document.${formName}.txtPemohon.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.txtNoPegangan.value = "";
	document.${formName}.socJenisHakmilikC.value = "";
	document.${formName}.socLotC.value = "";
	document.${formName}.txtNoLot.value = "";		
	document.${formName}.socNegeriC.value = "";
	document.${formName}.socDaerahC.value = "";
	document.${formName}.socMukimC.value = "";		
	document.${formName}.socStatusC.value = "";
	document.${formName}.socKementerianC.value = "";	
	document.${formName}.socAgensiC.value = "";
	document.${formName}.checkTanah.value = "";		
	doAjaxCall${formName}("");
}
function carian(){
	document.${formName}.actionPenawaran.value = "";
	doAjaxCall${formName}("");
}
function kosongkan(flagDetail) {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtTajukFail.value = "";
	document.${formName}.txtPemohon.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.socStatusC.value = "";
	document.${formName}.checkTanah.value = "";
	if (flagDetail == 'buka'){
		document.${formName}.txtNoPegangan.value = "";
		document.${formName}.socJenisHakmilikC.value = "";
		document.${formName}.socLotC.value = "";
		document.${formName}.txtNoLot.value = "";		
		document.${formName}.socNegeriC.value = "";
		document.${formName}.socDaerahC.value = "";
		document.${formName}.socMukimC.value = "";		
		document.${formName}.socKementerianC.value = "";	
		document.${formName}.socAgensiC.value = "";	
	}
	doAjaxCall${formName}("");
}
function paparFail(idFail,idStatus) {
	document.${formName}.idFail.value = idFail;
	document.${formName}.idStatus.value = idStatus;
	document.${formName}.action = "$EkptgUtil.getTabID("Penawaran",$portal_role)?_portal_module=ekptg.view.php2.online.FrmPNWTawaranKJPView";
	document.${formName}.actionPenawaran.value = "paparFail";
	//document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
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
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWTawaranKJPView&idPenawaranKJP="+idPenawaranKJP+dopost+"&flagPopup=openPopupAgensi&modePopup=update&hitButton=simpanDokumen";
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
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWTawaranKJPView&idPenawaranKJP="+idPenawaranKJP+"&idDokumen="+idDokumen+dopost+"&flagPopup=openPopupAgensi&modePopup=view&hitButton=simpanKemaskiniDokumen";
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
// 	if(document.${formName}.txtNoRujukan.value == ""){
// 		alert('Sila masukkan No Rujukan Surat.');
//   		document.${formName}.txtNoRujukan.focus(); 
// 		return; 
// 	}
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
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWTawaranKJPView&"+data;
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
// 	if(document.${formName}.txtNoRujukan.value == ""){
// 		alert('Sila masukkan No Rujukan Surat.');
//   		document.${formName}.txtNoRujukan.focus(); 
// 		return; 
// 	}
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
