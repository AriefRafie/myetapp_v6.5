<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idPejabat" type="hidden" id="idPejabat" value="$idPejabat"/>
  <input name="idHakmilikPermohonan" type="hidden" id="idHakmilikPermohonan" value="$idHakmilikPermohonan"/>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201')
  <tr>
    <td> #parse("app/php2/frmCRBHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  
  #if ($idFail != '' && $flagOpenDetail && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">SURAT TINDAKAN PENGUATKUASAAN</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">SURAT PANGGILAN OPERASI</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> #parse("app/php2/frmCRBCetakSuratTindakanDetail.jsp") </div>
          <div class="TabbedPanelsContent"> #parse("app/php2/frmCRBSuratPanggilanOperasi.jsp") </div>
        </div>
      </div></td>
  </tr>
  #if ($idStatus == '1610216' && $flagPopup == '')
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center">
    	<input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      	<input type="button" name="cmdSelesaiPermohonan" id="cmdSelesaiPermohonan" value="Selesai Permohonan" onClick="gotoSelesaiPermohonan()"/>
      	<input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
    </td>
  </tr>
  #end
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
function doChangeTabUpper(tabId) {
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
function cetakCRBSuratTindakanPenguatkuasaan(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idUlasanTeknikal="+idUlasanTeknikal+"&idFail="+idFail+"&report=suratTindakanPenguatkuasaan";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakCRBSuratPanggilanOperasi(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idUlasanTeknikal="+idUlasanTeknikal+"&idFail="+idFail+"&report=suratPanggilanOperasi";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<input name="step" type="hidden" id="step"/>
<script>
function gotoSelesaiPermohonan(){	
	document.${formName}.step.value = "selesaiPermohonan";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>
