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
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idKertasKerjaAPB" type="hidden" id="idKertasKerjaAPB" value="$idKertasKerjaAPB"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
</p>
<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  
  #if ($idFail != '' && $flagOpenDetail)
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">PERAKUAN KSU</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">KEPUTUSAN MENTERI</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBPerakuanDetail.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBKeputusanMenteri.jsp") </div>
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
<fieldset id="tableReport" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratPenolakan('$idFail')"> Surat Penolakan </a></td>
  </tr>
</table>
</fieldset>
<script type="text/javascript">
#if ($idFail != '' && $idStatus != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeTab(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.submit();
}
function kemaskiniPerakuanKSU() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
	
}
function kemaskiniKeputusan() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
	
}
function simpanKemaskiniPerakuanKSU(){
	if(document.${formName}.txtTarikhPerakuan.value == ""){
		alert('Sila masukkan Tarikh Perakuan');
  		document.${formName}.txtTarikhPerakuan.focus(); 
		return; 
	}
	if(document.${formName}.socPerakuan.value == ""){
		alert('Sila masukkan Perakuan');
  		document.${formName}.socPerakuan.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaKSU.value == ""){
		alert('Sila masukkan Nama Ketua Setiausaha NRE');
  		document.${formName}.txtNamaKSU.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPerakuanKSU";
	document.${formName}.submit();
}
function simpanKemaskiniKeputusan(){
	
	if(document.${formName}.txtTarikhKeputusan.value == ""){
		alert('Sila masukkan Tarikh Keputusan');
  		document.${formName}.txtTarikhKeputusan.focus(); 
		return; 
	}
	if(document.${formName}.socKeputusan.value == ""){
		alert('Sila masukkan Keputusan');
  		document.${formName}.socKeputusan.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaMenteri.value == ""){
		alert('Sila masukkan Nama Menteri NRE');
  		document.${formName}.txtNamaMenteri.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniKeputusan";
	document.${formName}.submit();
}
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function batalPerakuanKSU(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function batalKeputusan(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
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
function cetakSuratPenolakan(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=suratPenolakan";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<input name="step" type="hidden" id="step"/>
<script>
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>
