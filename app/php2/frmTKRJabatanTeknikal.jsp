<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
.style3 {
	color: #FF0000;
	font-weight: bold;
}
.blink {
	animation: blink 1s steps(5, start) infinite;
}
@keyframes blink {
 to {
 visibility: hidden;
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
  <input name="idNegeriTanah" type="hidden" id="idNegeriTanah" value="$idNegeriTanah"/>
  <input name="idKementerianTanah" type="hidden" id="idKementerianTanah" value="$idKementerianTanah"/>
  <input name="idAgensiTanah" type="hidden" id="idAgensiTanah" value="$idAgensiTanah"/>
  <input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>
  <input name="step" type="hidden" id="step" value="$step"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmTKRHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end

  #if ($idFail != '' && $idStatus != '1610198')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">SENARAI DOKUMEN</li>
          <!--<li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">ULASAN JABATAN TEKNIKAL (KJT)</li>-->
          <!-- <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">ULASAN JPPH</li> -->
          <!-- <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">NILAIAN</li> -->
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
          	#if ($errMsg != "")
				<div class="info"><strong>$errMsg</strong></div>
			#end
            #parse("app/php2/frmTKRDokumenKJP.jsp")
          </div>
          <!--<div class="TabbedPanelsContent">
            #parse("app/php2/frmTKRDokumenKJT.jsp") </div>-->
          <div class="TabbedPanelsContent">
           #if ($errMsg != "")
				<div class="info"><strong>$errMsg</strong></div>
		   #end
           #parse("app/php2/frmTKRUlasanJPPH.jsp")
          </div>
          <div class="TabbedPanelsContent">
           #if ($errMsg != "")
				<div class="info"><strong>$errMsg</strong></div>
		    #end
           #parse("app/php2/frmTKRNilaianJPPH.jsp")
          </div>
        </div>
      </div></td>
  </tr>
  #end
</table>
<script type="text/javascript">
#if ($idFail != '' && $idStatus != '1610198')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeTabUpper(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.submit();
}
function doSeterusnya(){
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
</script>

<script>
function cetakTKRSMUKJP(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratMUKJP&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratUlanganKJP(idFail,idUlasanTeknikal,bilUlangan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratUlanganKJP&idUlasanTeknikal="+idUlasanTeknikal+"&bilUlangan="+bilUlangan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratLulusBersyarat(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratLulusBersyarat";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratTolak(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratTolak";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratMULT(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratMULT&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratUlanganLT(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratUlanganLT&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSMUKJT(idFail,idUlasanTeknikal) {
	alert('Sila Hubungi Administrator.');
}
function cetakTKRSuratUlanganKJT(idFail,idUlasanTeknikal) {
	alert('Sila Hubungi Administrator.');;
}
function cetakSuratMUJPPB(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratMUJPPHB&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratMUJPPHP(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratMUJPPHP&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratMUJPPHG(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratMUJPPHG&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratUlanganJPPH(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupCetakLaporanView?idFail="+idFail+"&report=suratUlanganJPPHAsal&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratMUJPPHGanti(idFail,idTanahGanti) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupCetakLaporanView?idFail="+idFail+"&report=suratMUJPPHGanti&idTanahGanti="+idTanahGanti;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromMuatNaikLampiran() {
	document.${formName}.submit();
}

function cetakLampiran(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function uploadDoc(idUlasanTeknikal){
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPopupMuatNaikLampiranTeknikalView?idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>

