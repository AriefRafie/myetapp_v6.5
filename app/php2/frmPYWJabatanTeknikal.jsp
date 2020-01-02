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
  <input name="idHakmilikPermohonan" type="hidden" id="idHakmilikPermohonan" value="$idHakmilikPermohonan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
  <input name="flagPermohonanDari" type="hidden" id="flagPermohonanDari" value="$flagPermohonanDari"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idNegeriTanah" type="hidden" id="idNegeriTanah" value="$idNegeriTanah"/>
  <input name="idKementerianTanah" type="hidden" id="idKementerianTanah" value="$idKementerianTanah"/>
  <input name="idAgensiTanah" type="hidden" id="idAgensiTanah" value="$idAgensiTanah"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus"/>
  <input name="aktif" type="hidden" id="aktif" value="$aktif"/>
  <input name="idKementerianJKT" type="hidden" id="idKementerianJKT" value="$idKementerianJKT"/>
  <input name="idAgensiJKT" type="hidden" id="idAgensiJKT" value="$idAgensiJKT"/>
  <input name="idPejabatJKT" type="hidden" id="idPejabatJKT" value="$idPejabatJKT"/>
  <input name="step" type="hidden" id="step" value="$step"/>
</p>
#set($idLampiran = "")
    #set($namaLampiran = "")
    #set($catatanLampiran = "")
    #set($namaFailLampiran = "")
#foreach($beanMaklumatLampiran in $BeanMaklumatLampiran)
	#set($idLampiran = $beanMaklumatLampiran.idDokumen)
    #set($namaLampiran = $beanMaklumatLampiran.namaDokumen)
    #set($catatanLampiran = $beanMaklumatLampiran.catatan)
    #set($namaFailLampiran = $beanMaklumatLampiran.namaFail)
#end
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '' && $idStatus != '1610198')
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
  
  #if ($idFail != '' && $idStatus != '1610198')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">ULASAN JABATAN PENGGUNA (KJP)</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">ULASAN JABATAN TEKNIKAL (KJT)</li>
          <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">ULASAN JPPH</li>
          #if ($flagPermohonanDari == '0')
          <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">ULASAN JAWATANKUASA TETAP</li>
          #if ($idSuburusan == '27')
          <li onClick="doChangeTabUpper(4);" class="TabbedPanelsTab" tabindex="0">ULASAN JABATAN HUTAN</li>
          #end
          #else
          #if ($idSuburusan == '27')
          <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">ULASAN JABATAN HUTAN</li>
          #end
          #end
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWDokumenKJP.jsp") </div>
          <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWDokumenKJT.jsp") </div>
          <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWNilaianJPPH.jsp") </div>
          #if ($flagPermohonanDari == '0')
          <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWDokumenJKT.jsp") </div>
          #end
          #if ($idSuburusan == '27')
          <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWDokumenJH.jsp") </div>
          #end </div>
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
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakPYWSMintaUlasanKJP(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratMintaUlasanKJP&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganKJP(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratUlanganKJP&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSMUKJT(idFail,idUlasanTeknikal) {
	alert('Sila Hubungi Administrator.');
}
function cetakPYWSuratUlanganKJT(idFail,idUlasanTeknikal) {
	alert('Sila Hubungi Administrator.');
}
function cetakSuratMintaLaporanTanah(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratMintaLaporanTanah&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganLT(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratUlanganLT&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSMintaUlasanJKT(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratMintaUlasanJKT&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganKWP(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratUlanganKWP&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganKKW(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratUlanganKKW&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganPTG(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratUlanganPTG&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganDBKL(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratUlanganDBKL&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganBPH(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratUlanganBPH&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSMintaUlasanJPPH(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratMintaUlasanJPPH";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganJPPH(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratUlanganJPPH&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSMintaUlasanJH(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratMintaUlasanJH";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratUlanganJH(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratUlanganJH&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function gotoSenaraiFailKeseluruhan() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailKeseluruhanView";
	document.${formName}.submit();
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
