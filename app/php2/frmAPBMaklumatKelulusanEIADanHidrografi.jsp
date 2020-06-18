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
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
</p>
<body>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '' && $idStatus != '1610212')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td><div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end  
  
  #if ($idFail != '' && $flagOpenDetail)
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">KELULUSAN EIA</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">KELULUSAN HIDROGRAFI</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">KELULUSAN KAJIAN PASIR</li>
          <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">KELULUSAN KAJIAN HIDRAULIK</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBLaporanKelulusanEIA.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBLaporanKelulusanHidrografi.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBLaporanKelulusanKajianPasir.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBLaporanKelulusanKajianHidraulik.jsp") </div>
        </div>
      </div></td>
  </tr>
  #elseif ($idFail != '')
  <tr>
    <td> 
      <div class="warning"><strong>STATUS FAIL MASIH DI PERINGKAT $!status</strong></div></td>
  </tr>
  #end
</table>
<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeTab(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBMaklumatKelulusanHidrografi";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doKemaskiniMaklumatHIDRO(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatPasir(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatJPS(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatJPS(){
	if(document.${formName}.txtNoRujJPS.value == ""){
		alert('Sila masukkan No. Ruj JPS.');
  		document.${formName}.txtNoRujJPS.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhSuratLulusJPS.value == ""){
		alert('Sila masukkan Tarikh Surat Kelulusan.');
  		document.${formName}.txtTarikhSuratLulusJPS.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTerimaJPS.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.txtTarikhTerimaJPS.focus(); 
		return; 
	}
	if(document.${formName}.socKeputusanJPS.value == ""){
		alert('Sila pilih Keputusan.');
  		document.${formName}.socKeputusanJPS.focus(); 
		return; 
	}	
	if(document.${formName}.txtTarikhLulusJPS.value == ""){
		alert('Sila masukkan Tarikh Diluluskan.');
  		document.${formName}.txtTarikhLulusJPS.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}			
	document.${formName}.hitButton.value = "doSimpanKemaskiniJPS";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function doSimpanKemaskiniMaklumatPasir(){
	if(document.${formName}.txtNoRujPasir.value == ""){
		alert('Sila masukkan No. Ruj Pusat Kajian Pasir.');
  		document.${formName}.txtNoRujPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhSuratLulusPasir.value == ""){
		alert('Sila masukkan Tarikh Surat Kelulusan.');
  		document.${formName}.txtTarikhSuratLulusPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTerimaPasir.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.txtTarikhTerimaPasir.focus(); 
		return; 
	}
	if(document.${formName}.socKeputusanPasir.value == ""){
		alert('Sila pilih Keputusan.');
  		document.${formName}.socKeputusanPasir.focus(); 
		return; 
	}	
	if(document.${formName}.txtTarikhLulusPasir.value == ""){
		alert('Sila masukkan Tarikh Diluluskan.');
  		document.${formName}.txtTarikhLulusPasir.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}			
	document.${formName}.hitButton.value = "doSimpanKemaskiniPasir";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function doSimpanKemaskiniMaklumatHIDRO(){
	if(document.${formName}.txtNoRujHidro.value == ""){
		alert('Sila masukkan No. Ruj Pusat Hidrografi Nasional.');
  		document.${formName}.txtNoRujHidro.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhSuratLulusHIDRO.value == ""){
		alert('Sila masukkan Tarikh Surat Kelulusan.');
  		document.${formName}.txtTarikhSuratLulusHIDRO.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTerimaHIDRO.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.txtTarikhTerimaHIDRO.focus(); 
		return; 
	}
	if(document.${formName}.socKeputusanHIDRO.value == ""){
		alert('Sila pilih Keputusan.');
  		document.${formName}.socKeputusanHIDRO.focus(); 
		return; 
	}	
	if(document.${formName}.txtTarikhLulusHIDRO.value == ""){
		alert('Sila masukkan Tarikh Diluluskan.');
  		document.${formName}.txtTarikhLulusHIDRO.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}			
	document.${formName}.hitButton.value = "doSimpanKemaskiniHidro";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function doKemaskiniMaklumatJAS(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatJAS(){
	if(document.${formName}.txtNoRujJAS.value == ""){
		alert('Sila masukkan No. Ruj. Jabatan Alam Sekitar.');
  		document.${formName}.txtNoRujJAS.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhSuratLulusJAS.value == ""){
		alert('Sila masukkan Tarikh Surat Kelulusan.');
  		document.${formName}.txtTarikhSuratLulusJAS.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTerimaJAS.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.txtTarikhTerimaJAS.focus(); 
		return; 
	}
	if(document.${formName}.socKeputusanJAS.value == ""){
		alert('Sila pilih Keputusan.');
  		document.${formName}.socKeputusanJAS.focus(); 
		return; 
	}	
	if(document.${formName}.txtTarikhLulusJAS.value == ""){
		alert('Sila masukkan Tarikh Diluluskan.');
  		document.${formName}.txtTarikhLulusJAS.focus(); 
		return; 
	}	
	if(document.${formName}.txtTempohLulusJAS.value == ""){
		alert('Sila masukkan Tempoh Kelulusan EIA.');
  		document.${formName}.txtTempohLulusJAS.focus(); 
		return; 
	}		
	if(document.${formName}.socJAS.value == ""){
		alert('Sila pilih Cawangan Jabatan Alam Sekitar.');
  		document.${formName}.socJAS.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	document.${formName}.hitButton.value = "doSimpanKemaskiniJAS";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function doBatalMaklumatHIDRO(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doBatalMaklumatPasir(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doBatalMaklumatJAS(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doBatalMaklumatJPS(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function seterusnya(){	

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBMaklumatKelulusanHidrografi";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
</script>
