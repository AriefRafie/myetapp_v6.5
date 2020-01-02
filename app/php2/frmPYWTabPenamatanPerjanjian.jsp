<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PENAMATAN PERJANJIAN</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">KERTAS RINGKASAN</li>
          <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">MESYUARAT</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWMaklumatPenamatanPerjanjian.jsp") </div>
          <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWKertasMaklumanPenamatanPerjanjian.jsp") </div>
          <div class="TabbedPanelsContent"> #parse("app/php2/frmPYWMesyuaratPenamatanPerjanjian.jsp") </div>
        </div>
      </div></td>
  </tr>
</table>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
</script>
<script>
function kemaskini(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batal(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMaklumatPenamatan(idStatus){
	if(document.${formName}.tarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.tarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if (idStatus == '1610221'){
		if(document.${formName}.socFlagSebabTamat.value == ""){
			alert('Sila pilih Sebab Penamatan.');
			document.${formName}.socFlagSebabTamat.focus(); 
			return; 
		}
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatPenamatan";
	doAjaxCall${formName}("");
}
function simpanKemaskiniKertasMakluman(){
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniKertasMakluman";
	doAjaxCall${formName}("");
}
function selesai(){
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "selesai";
	doAjaxCall${formName}("");
}
</script>
