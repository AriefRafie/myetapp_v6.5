<style type="text/css">
<!--
.style2 {
	color: #0000FF
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '' && $idStatus != '1610198')
  #if ($flagPopup2 == 'openPopupMaklumatKoordinat')
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT TITIK KOORDINAT</strong></legend>
      #parse("app/php2/frmAPBMaklumatTitikKoordinat.jsp")
      </fieldset></td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI KEDUDUKAN TITIK KOORDINAT</strong></legend>
      <table align="center" width="100%">
        #if ($flagPopup2 == '')
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:doDaftarMaklumatKoordinat()"/></td>
        </tr>
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="35%"><strong>Label Titik</strong></td>
          <td align="center"><strong>Latitud (U)</strong></td>
          <td align="center"><strong>Longitud (T)</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiTitkKoordinat.size() > 0)
        #foreach ($list in $SenaraiTitkKoordinat)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparKoordinat('$list.idKoordinatPermohonan')" class="style2">$list.noTitik</a></td>
          <td align="center" class="$row">$list.darjahU&deg;$list.minitU&prime;$list.saatU&Prime;</td>
          <td align="center" class="$row">$list.darjahT&deg;$list.minitT&prime;$list.saatT&Prime;</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
</table>
<script>
function doDaftarMaklumatKoordinat(){
	document.${formName}.selectedTabUpper = "1";
	document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "openPopupMaklumatKoordinat";
	document.${formName}.modePopup.value = "new";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function paparKoordinat(idKoordinatPermohonan){
	document.${formName}.selectedTabUpper = "1";
	document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "openPopupMaklumatKoordinat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idKoordinatPermohonan.value = idKoordinatPermohonan;
	document.${formName}.submit();
}
function doSimpanMaklumatKoordinat(){
	if(document.${formName}.txtNoTitik.value == ""){
		alert('Sila masukakn No Titik.');
  		document.${formName}.txtNoTitik.focus(); 
		return; 
	}	
	if(document.${formName}.txtDarjahU.value == ""){
		alert('Sila masukakn Darjah U.');
  		document.${formName}.txtDarjahU.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitU.value == ""){
		alert('Sila masukakn Minit U.');
  		document.${formName}.txtMinitU.focus(); 
		return; 
	}
	if(document.${formName}.txtSaatU.value == ""){
		alert('Sila masukakn Saat U.');
  		document.${formName}.txtSaatU.focus(); 
		return; 
	}	
	if(document.${formName}.txtDarjahT.value == ""){
		alert('Sila masukakn Darjah T.');
  		document.${formName}.txtDarjahT.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitT.value == ""){
		alert('Sila masukakn Minit T.');
  		document.${formName}.txtMinitT.focus(); 
		return; 
	}	
	if(document.${formName}.txtSaatT.value == ""){
		alert('Sila masukakn Saat T.');
  		document.${formName}.txtSaatT.focus(); 
		return; 
	}	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.selectedTabUpper = "1";
	document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "openPopupMaklumatKoordinat";
	document.${formName}.modePopup.value = "new";
	document.${formName}.hitButton.value = "simpanMaklumatKoordinat";
	document.${formName}.submit();
}	
function doSimpanKemaskiniMaklumatKoordinat(){
	if(document.${formName}.txtNoTitik.value == ""){
		alert('Sila masukakn No Titik.');
  		document.${formName}.txtNoTitik.focus(); 
		return; 
	}
	if(document.${formName}.txtDarjahU.value == ""){
		alert('Sila masukakn Darjah U.');
  		document.${formName}.txtDarjahU.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitU.value == ""){
		alert('Sila masukakn Minit U.');
  		document.${formName}.txtMinitU.focus(); 
		return; 
	}
	if(document.${formName}.txtSaatU.value == ""){
		alert('Sila masukakn Saat U.');
  		document.${formName}.txtSaatU.focus(); 
		return; 
	}	
	if(document.${formName}.txtDarjahT.value == ""){
		alert('Sila masukakn Darjah T.');
  		document.${formName}.txtDarjahT.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitT.value == ""){
		alert('Sila masukakn Minit T.');
  		document.${formName}.txtMinitT.focus(); 
		return; 
	}	
	if(document.${formName}.txtSaatT.value == ""){
		alert('Sila masukakn Saat T.');
  		document.${formName}.txtSaatT.focus(); 
		return; 
	}	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.selectedTabUpper = "1";
	document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "openPopupMaklumatKoordinat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatKoordinat";
	document.${formName}.submit();
}
function doKemaskiniMaklumatKoordinat(){
	document.${formName}.selectedTabUpper = "1";
	document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "openPopupMaklumatKoordinat";
	document.${formName}.modePopup.value = "update";
	document.${formName}.submit();
}
function doHapusMaklumatKoordinat(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.selectedTabUpper = "1";
	document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatKoordinat";
	document.${formName}.submit();
}
function validateNumber(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
}
function doBatal(){
	document.${formName}.selectedTabUpper = "1";
	document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}
</script>
