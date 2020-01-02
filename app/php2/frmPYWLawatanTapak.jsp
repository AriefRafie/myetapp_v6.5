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
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idHakmilikPermohonan" type="hidden" id="idHakmilikPermohonan" value="$idHakmilikPermohonan"/>
  <input name="idLaporanTanah" type="hidden" id="idLaporanTanah" value="$idLaporanTanah"/>
  <input name="idPegawaiLaporanTanah" type="hidden" id="idPegawaiLaporanTanah" value="$idPegawaiLaporanTanah"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
  <input name="step" type="hidden" id="step" value="$step"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199')
  <tr>
    <td> #parse("app/php2/frmPYWHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI TUGASAN TERLEBIH DAHULU</div></td>
  </tr>
  #else
  #foreach($beanHeader in $BeanHeader)
  <tr>
    <td>&nbsp;
      <div class="warning">FAIL INI MASIH DI STATUS <strong>$beanHeader.status</strong></div></td>
  </tr>
  #end
  #end
  
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">LAWATAN TAPAK</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT LAIN</li>
          <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">KEHADIRAN</li>
          <li onClick="doChangeTab(4);" class="TabbedPanelsTab" tabindex="0">PEGAWAI PELAPOR</li>
          <li onClick="doChangeTab(5);" class="TabbedPanelsTab" tabindex="0">IMEJAN</li>
          <li onClick="doChangeTab(6);" class="TabbedPanelsTab" tabindex="0">PELAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmPLPMaklumatTanah.jsp") 
            
              	<table  width="100%" border="0" cellspacing="2" cellpadding="2">
            		<tr>
            			<td align="center">
            				 #if ($session.getAttribute('FLAG_FROM') == 'failNegeri')
			            		<input type="button" name="cmdKembali" value="Kembali" onClick="gotoSenaraiFailNegeri()"/>
			            	#end
            			</td>
            		</tr>
            	</table>
            	
            </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmPYWLawatanTapakDetail.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmPYWMaklumatLain.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmPYWMaklumatKehadiran.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmPYWPegawaiPelapor.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmPYWImejan.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmPYWPelan.jsp") </div>
        </div>
      </div></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWLaporanTanah('$idFail','$idLaporanTanah')"> Laporan Tanah </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWLampiranA('$idLaporanTanah')"> Lampiran A </a></td>
  </tr>
</table>
</fieldset>
<script type="text/javascript">
#if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function gotoSenaraiFailNegeri(){
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailNegeriView";
	document.${formName}.submit();
}

function doChangeTab(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPYWLawatanTapakView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.actionPenawaran.value = "";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPYWLawatanTapakView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function seterusnya(id){
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
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakPYWLaporanTanah(idFail,idLaporanTanah) {
	var url = "../servlet/ekptg.report.php2.PYWLaporanTanah?ID_FAIL="+idFail+"&ID_LAPORANTANAH="+idLaporanTanah;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWLampiranA(idLaporanTanah) {
	var url = "../servlet/ekptg.report.php2.PYWLampiranA?ID_LAPORANTANAH="+idLaporanTanah;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function gotoSenaraiFailKeseluruhan() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailKeseluruhanView";
	document.${formName}.submit();
}
</script>
