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
  <input name="idUrusan" type="hidden" id="idUrusan" value="$idUrusan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="actionPenyewaan" type="hidden" id="actionPenyewaan" value="$actionPenyewaan"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>
  
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus"/>
  <input name="aktif" type="hidden" id="aktif" value="$aktif"/>
  
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="flagJenisPerjanjian" type="hidden" id="flagJenisPerjanjian" value="$flagJenisPerjanjian"/>
  <input name="idPermohonanPerjanjianTambahan" type="hidden" id="idPermohonanPerjanjianTambahan" value="$idPermohonanPerjanjianTambahan"/>
  <input name="idStatusPerjanjianTambahan" type="hidden" id="idStatusPerjanjianTambahan" value="$idStatusPerjanjianTambahan"/>
  
  <input name="idPejabat" type="hidden" id="idPejabat" value="$idPejabat"/>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idMesyuarat" type="hidden" id="idMesyuarat" value="$idMesyuarat"/>
  <input name="idKeputusan" type="hidden" id="idKeputusan" value="$idKeputusan"/>
  <input name="idPerjanjian" type="hidden" id="idPerjanjian" value="$idPerjanjian"/>
  <input name="idLuas" type="hidden" id="idLuas" value="$idLuas"/>
  <input name="idLuasKegunaan" type="hidden" id="idLuasKegunaan" value="$idLuasKegunaan"/>
</p>
<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td> #parse("app/php2/frmPYWHeaderPerjanjianTambahan.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PERMOHONAN</li>
        #if ($idStatusPerjanjianTambahan != '1610198')
        <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">ULASAN JPPH</li>
        #end
        #if ($idStatusPerjanjianTambahan != '1610198' && $idStatusPerjanjianTambahan != '1610199')
        <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">MESYUARAT</li>
        #end
        #if ($idStatusPerjanjianTambahan != '1610198' && $idStatusPerjanjianTambahan != '1610199' && $idStatusPerjanjianTambahan != '1610201')
        <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">KEPUTUSAN</li>
        #end
        #if ($idStatusPerjanjianTambahan != '1610198' && $idStatusPerjanjianTambahan != '1610199' && $idStatusPerjanjianTambahan != '1610201' && $idStatusPerjanjianTambahan != '1610206' && $idStatusPerjanjianTambahan != '1610208')
        <li onClick="doChangeTabUpper(4);" class="TabbedPanelsTab" tabindex="0">PERJANJIAN</li>
        <li onClick="doChangeTabUpper(5);" class="TabbedPanelsTab" tabindex="0">MAKLUMBALAS</li>
        #end
      </ul>
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWPerjanjianTambahanMaklumatPermohonan.jsp") </div>
        #if ($idStatusPerjanjianTambahan != '1610198')
        <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWPerjanjianTambahanJabatanTeknikal.jsp") </div>
        #end
        #if ($idStatusPerjanjianTambahan != '1610198' && $idStatusPerjanjianTambahan != '1610199')
        <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWPerjanjianTambahanMesyuarat.jsp") </div>
        #end
        #if ($idStatusPerjanjianTambahan != '1610198' && $idStatusPerjanjianTambahan != '1610199' && $idStatusPerjanjianTambahan != '1610201')
        <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWPerjanjianTambahanKeputusan.jsp") </div>
        #end
        #if ($idStatusPerjanjianTambahan != '1610198' && $idStatusPerjanjianTambahan != '1610199' && $idStatusPerjanjianTambahan != '1610201' && $idStatusPerjanjianTambahan != '1610206' && $idStatusPerjanjianTambahan != '1610208')
        <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWPerjanjianTambahanPerjanjian.jsp") </div>
        <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWPerjanjianTambahanMaklumbalas.jsp") </div>
        #end
      </div>
    </div></td>
  </tr>
</table>
<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
</script>

<script>
function doChangeTabUpper(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.modePopup.value = "";
	document.${formName}.flagPopup.value = "";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.submit();
}
function kemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batal() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function kembali() {	
	document.${formName}.actionPenyewaan.value = "";
	document.${formName}.submit();
}
</script>
