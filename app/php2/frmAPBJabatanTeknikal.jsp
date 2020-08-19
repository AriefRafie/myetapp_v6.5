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
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idPertindihan" type="hidden" id="idPertindihan" value="$idPertindihan"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="flagNotifikasi" type="hidden" id="flagNotifikasi" value="$flagNotifikasi"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>
</p>
<body>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  
  #if ($idFail != '' && $flagOpenDetail)
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">ULASAN JUPEM</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">ULASAN JAS</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">ULASAN JMG</li>
          <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">ULASAN JABATAN PERIKANAN</li>
          <li onClick="doChangeTab(4);" class="TabbedPanelsTab" tabindex="0">ULASAN JLM</li>
          <li onClick="doChangeTab(5);" class="TabbedPanelsTab" tabindex="0">ULASAN PHN</li>
          <li onClick="doChangeTab(6);" class="TabbedPanelsTab" tabindex="0">ULASAN JPS</li>
          <li onClick="doChangeTab(7);" class="TabbedPanelsTab" tabindex="0">ULASAN PTG</li>
          <li onClick="doChangeTab(8);" class="TabbedPanelsTab" tabindex="0">PERTINDIHAN KOORDINAT</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBUlasanJUPEM.jsp")</div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBUlasanJAS.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBUlasanJMG.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBUlasanJP.jsp")  </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBUlasanJLM.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBUlasanPHM.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBUlasanJPS.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBUlasanPTG.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBSenaraiPertindihan.jsp") </div>
        </div>
      </div></td>
  </tr>
  <tr>
    <td align="center"> #if ($idStatus == '1610199' && $flagPopup == '')
      <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end</td>
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
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
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
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
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
function cetakAPBSuratMintaUlasanJabatanTeknikal(idFail, idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&idUlasanTeknikal="+idUlasanTeknikal+"&report=suratMintaUlasanJabatanTeknikal";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
