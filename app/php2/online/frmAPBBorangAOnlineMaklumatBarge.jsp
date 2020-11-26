<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

  <input name="idBorangA" type="hidden" id="idBorangA" value="$idBorangA" />
  <input name="idJadualKedua" type="hidden" id="idJadualKedua" value="$idJadualKedua"/>
  <input name="idBarge" type="hidden" id="idBarge" value="$idBarge"/>
 
 
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
 #if(($mode == 'newBarge') || ($mode == 'viewBarge')|| ($mode == 'updateBarge'))
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT BARGE</strong></legend>
      #parse("app/php2/online/frmAPBBorangAOnlineMaklumatBargeDetail.jsp")
      </fieldset></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI BARGE</strong></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        
        <tr>
          <td colspan="4" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarMaklumatBarge('$idJadualKedua','$idBorangA')"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="95%"><strong>Nama Didaftarkan</strong></td>         
        </tr>
        #set ($senaraiBarge = "")
        #if ($SenaraiBarge.size() > 0)
        #foreach ($senaraiBarge in $SenaraiBarge)
        #if ($senaraiBarge.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiBarge.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiBarge.bil</td>
          <td class="$row"><a href="javascript:paparBarge('$senaraiBarge.idBarge','$senaraiBarge.idBorangA')" class="style2" >$senaraiBarge.namaDidaftarkan</a></td>
          
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          
        </tr>
        #end
        <tr>
          <td colspan="8">&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function paparBarge(idBarge) {
	document.${formName}.idBarge.value = idBarge;
	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.mode.value = "viewBarge";
	document.${formName}.submit();
}
function daftarMaklumatBarge(idJadualKedua,idBorangA){
	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.mode.value = "newBarge";
	document.${formName}.idJadualKedua.value = idJadualKedua;
	document.${formName}.idBorangA.value = idBorangA;
	//alert(idJadualKedua);
	document.${formName}.submit();
}
function doSimpanMaklumatBarge(){
	
	if(document.${formName}.txtNamaDaftar.value == ""){
		alert('Sila masukkan Nama Didaftarkan.');
  		document.${formName}.txtNamaDaftar.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.mode.value = "viewBarge";
	document.${formName}.hitButton.value = "simpanMaklumatBarge";
	document.${formName}.submit();
}
function simpanKemaskiniMaklumatBarge(){
	
	if(document.${formName}.txtNamaDaftar.value == ""){
		alert('Sila masukkan Nama Didaftarkan.');
  		document.${formName}.txtNamaDaftar.focus(); 
		return; 
	}
	if(document.${formName}.txtJenis.value == ""){
		alert('Sila masukkan Jenis Kapal.');
  		document.${formName}.txtJenis.focus(); 
		return; 
	}
	if(document.${formName}.txtNoPendaftaran.value == ""){
		alert('Sila masukkan No. Pendaftaran.');
  		document.${formName}.txtNoPendaftaran.focus(); 
		return; 
	}
	if(document.${formName}.txtNoTel.value == ""){
		alert('Sila masukkan No. Tel.');
  		document.${formName}.txtNoTel.focus(); 
		return; 
	}
	if(document.${formName}.txtKapasiti.value == ""){
		alert('Sila masukkan Kapasiti.');
  		document.${formName}.txtKapasiti.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.mode.value = "viewBarge";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatBarge";
	document.${formName}.submit();
}
function kemaskiniMaklumatBarge(){
	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.mode.value = "updateBarge";
	document.${formName}.hitButton.value = "";
	doAjaxCall${formName}("");
}
function doBatalMaklumatBarge(){
	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.hitButton.value = "";
	document.${formName}.mode.value = "viewBarge";
	doAjaxCall${formName}("");
}
function doKembaliMaklumatBarge(){
	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.hitButton.value = "";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function doHapusBarge(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewBarge";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusBarge";
	document.${formName}.submit();
}
</script>
