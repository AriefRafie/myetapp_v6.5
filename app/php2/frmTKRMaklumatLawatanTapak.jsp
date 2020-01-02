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
  <input name="actionTukarguna" type="hidden" id="actionTukarguna" value="$actionTukarguna"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idHakmilikPermohonan" type="hidden" id="idHakmilikPermohonan" value="$idHakmilikPermohonan"/>
  <input name="idTanahGanti" type="hidden" id="idTanahGanti" value="$idTanahGanti"/>
  <input name="idLaporanTanah" type="hidden" id="idLaporanTanah" value="$idLaporanTanah"/>
  <input name="idPegawaiLaporanTanah" type="hidden" id="idPegawaiLaporanTanah" value="$idPegawaiLaporanTanah"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
  
  <input name="flagJenisTanah" type="hidden" id="flagJenisTanah" value="$flagJenisTanah"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td> #parse("app/php2/frmTKRHeader.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">LAWATAN TAPAK</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT LAIN</li>
          <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">KEHADIRAN</li>
          <li onClick="doChangeTab(4);" class="TabbedPanelsTab" tabindex="0">PEGAWAI PELAPOR</li>
          <li onClick="doChangeTab(5);" class="TabbedPanelsTab" tabindex="0">IMEJAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmTKRMaklumatTanah.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmTKRLawatanTapakDetail.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmTKRMaklumatLain.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmPLPMaklumatKehadiran.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmTKRPegawaiPelapor.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmTKRImejan.jsp") </div>
        </div>
      </div></td>
  </tr>
</table>

<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakLaporanTanah('$idFail','$idTanah')"> Laporan Tanah </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakImej('$idTanah','$idLaporanTanah')"> Lampiran A </a></td>
  </tr>
</table>

</fieldset>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
</script>

<script>
function doChangeTab(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmTKRLawatanTapakView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.actionTukarguna.value = "";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmTKRLawatanTapakView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
</script>
