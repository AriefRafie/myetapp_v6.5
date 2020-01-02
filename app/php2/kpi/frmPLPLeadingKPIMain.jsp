<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF;
	font-weight: bold;
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="flagOpenKPI" type="hidden" id="flagOpenKPI" value="$flagOpenKPI"/>
  <input name="selectedTab" type="hidden" id="selectedTab" value="$selectedTab"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idKPISasaran" type="hidden" id="idKPISasaran" value="$idKPISasaran"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>PILIHAN KPI</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Urusan</td>
          <td width="1%">:</td>
          <td width="70%">$selectSuburusan</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Tarikh Mula</td>
          <td>:</td>
          <td><input type="text" name="txdTarikhMula" id="txdTarikhMula" value="$txdTarikhMula" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Tarikh Akhir</td>
          <td>:</td>
          <td><input type="text" name="txdTarikhAkhir" id="txdTarikhAkhir" value="$txdTarikhAkhir" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhAkhir',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdPapar" id="cmdPapar" value="Papar" onClick="papar();">
            <input type="button" name="cmdKosong" id="cmdKosong" value="Kosongkan" onClick="kosongkan();"></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT KPI</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">TETAPAN SASARAN KPI</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> #if ($flagOpenKPI == 'Y')
            #if ($idSuburusan == '32')
            <!-- PENAWARAN -->
            #parse("app/php2/kpi/frmPNWMaklumatKPI.jsp")
            #elseif ($idSuburusan == '33')
            <!-- TUKAR GUNA -->
            #parse("app/php2/kpi/frmTKRMaklumatKPI.jsp")
            #elseif ($idSuburusan == '34')
            <!-- PELEPASAN -->
            #parse("app/php2/kpi/frmPLPMaklumatKPI.jsp")
            #end
            #else
            <div class="warning">SILA MASUKKAN MAKLUMAT PILIHAN KPI TERLEBIH DAHULU</div>
            #end </div>
          <div class="TabbedPanelsContent"> #if ($idSuburusan != '' && $idSuburusan != '99999')
            #if ($idSuburusan == '32')
            <!-- PENAWARAN -->
            #parse("app/php2/kpi/frmPNWTetapanSasaranKPI.jsp")
            #elseif ($idSuburusan == '33')
            <!-- TUKAR GUNA -->
            #parse("app/php2/kpi/frmTKRTetapanSasaranKPI.jsp")
            #elseif ($idSuburusan == '34')
            <!-- PELEPASAN -->
            #parse("app/php2/kpi/frmPLPTetapanSasaranKPI.jsp")
            #end
            #else
            <div class="warning">SILA PILIH URUSAN TERLEBIH DAHULU</div>
            #end </div>
        </div>
      </div></td>
  </tr>
</table>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>
<script>
function doChangeTab(tabId) {
	document.${formName}.selectedTab.value = tabId;
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doChangeSuburusan() {
	doAjaxCall${formName}("doChangeSuburusan");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.socSuburusan.value = "";
	document.${formName}.txdTarikhMula.value = "";
	document.${formName}.txdTarikhAkhir.value = "";
	doAjaxCall${formName}("");
}
function papar(tabId) {
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih Urusan.');
  		document.${formName}.socSuburusan.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula.');
  		document.${formName}.txdTarikhMula.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhAkhir.value == ""){
		alert('Sila masukkan Tarikh Akhir.');
  		document.${formName}.txdTarikhAkhir.focus(); 
		return; 
	}
	
	doAjaxCall${formName}("");
}

function kemaskini(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batal(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doChangeField() {
	doAjaxCall${formName}("");
}
function validateHariDimasukkan(elmnt,content,content2,content3) {
	var a = content * 1;
	var b = content3 * 1;
	
	if ( a < b ){
		alert('Bilangan hari yang dimasukkan adalah tidak sah.')
		elmnt.value = content3;
		return;
	}
}
function simpanKemaskini(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskini";
	doAjaxCall${formName}("");
}
</script>
