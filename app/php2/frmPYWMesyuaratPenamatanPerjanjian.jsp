<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openMesyuarat')
  <tr>
    <td> #parse("app/php2/frmPYWMesyuaratDetailPenamatanPerjanjian.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI MESYUARAT</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarMesyuarat()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="60%"><strong>Tajuk Mesyuarat</strong></td>
          <td width="10%"><strong>Bil Mesyuarat</strong></td>
          <td width="10%" align="center"><strong>Tarikh</strong></td>
          <td width="15%"><strong>Syor</strong></td>
        </tr>
        #set ($senaraiMesyuarat = "")
        #if ($SenaraiMesyuarat.size() > 0)
        #foreach ($senaraiMesyuarat in $SenaraiMesyuarat)
        #if ($senaraiMesyuarat.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiMesyuarat.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiMesyuarat.bil</td>
          <td class="$row"><a href="javascript:paparMesyuarat($senaraiMesyuarat.idMesyuarat)" class="style2">$senaraiMesyuarat.tajukMesyuarat</a></td>
          <td class="$row">$senaraiMesyuarat.bilMesyuarat</td>
          <td class="$row" align="center">$senaraiMesyuarat.tarikhMesyuarat</td>
          <td class="$row">$senaraiMesyuarat.syor</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($idStatus == '1610221')
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center">
    <input name="cmdSelesai" type="button" value="Selesai" onClick="selesai()">
    </td>
  </tr>
  #end
</table>
<script>
function paparMesyuarat(id){
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idMesyuarat.value = id;
	doAjaxCall${formName}("");
}
function daftarMesyuarat(){
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function simpanMesyuarat(){
	
	if(document.${formName}.txtTajukMesyuarat.value == ""){
		alert('Sila masukkan Tajuk Mesyuarat.');
		document.${formName}.txtTajukMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.txtBilMesyuarat.value == ""){
		alert('Sila masukkan Bil Mesyuarat.');
		document.${formName}.txtBilMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhMesyuarat.value == ""){
		alert('Sila masukkan Tarikh Mesyuarat.');
		document.${formName}.txtTarikhMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.socJamDari.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socJamDari.focus(); 
		return; 
	}
	if(document.${formName}.socMinitDari.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socMinitDari.focus(); 
		return; 
	}
	if(document.${formName}.socJamHingga.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socJamHingga.focus(); 
		return; 
	}
	if(document.${formName}.socMinitHingga.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socMinitHingga.focus(); 
		return; 
	}
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMesyuarat";
	doAjaxCall${formName}("");
}
function batalMesyuarat(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniMesyuarat(){
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function hapusMesyuarat(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMesyuarat";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMesyuarat(){
	
	if(document.${formName}.txtTajukMesyuarat.value == ""){
		alert('Sila masukkan Tajuk Mesyuarat.');
		document.${formName}.txtTajukMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.txtBilMesyuarat.value == ""){
		alert('Sila masukkan Bil Mesyuarat.');
		document.${formName}.txtBilMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhMesyuarat.value == ""){
		alert('Sila masukkan Tarikh Mesyuarat.');
		document.${formName}.txtTarikhMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.socJamDari.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socJamDari.focus(); 
		return; 
	}
	if(document.${formName}.socMinitDari.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socMinitDari.focus(); 
		return; 
	}
	if(document.${formName}.socJamHingga.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socJamHingga.focus(); 
		return; 
	}
	if(document.${formName}.socMinitHingga.value == ""){
		alert('Sila pilih Masa Mesyuarat.');
		document.${formName}.socMinitHingga.focus(); 
		return; 
	}
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMesyuarat";
	doAjaxCall${formName}("");
}
function batalKemaskiniMesyuarat(){
	document.${formName}.flagPopup.value = "openMesyuarat";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function seterusnyaMS(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "seterusnyaMS";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
</script>
