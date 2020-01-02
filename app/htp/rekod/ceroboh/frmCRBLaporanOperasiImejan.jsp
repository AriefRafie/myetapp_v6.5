<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>

#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Fail berjaya dimuatnaik.</div>";
</script>
#end

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupDokumen')
  <tr>
    <td> #parse("app/php2/frmCRBOperasiImejDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI IMEJ</strong></legend>
      <table align="center" width="100%">
        <!-- kmie, 20100812 (MacGDI) -->
        <tr>
          <td colspan="2" scope="row"><a href="http://g4nre.mygeoportal.gov.my" target="_blank" style="color:#0000FF">MacGDI</a></td>
        </tr>
        <!-- end edit (kmie) -->
        #if ($flagPopup == '')
        <!--
        <tr>
          <td colspan="2" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarDokumen()"/></td>
        </tr>
        -->
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td><strong>Nama Imej</strong></td>
        </tr>
        #set ($senaraiImejan = "")
        #if ($SenaraiImejan.size() > 0)
        #foreach ($senaraiImejan in $SenaraiImejan)
        #if ($senaraiImejan.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiImejan.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiImejan.bil</td>
          <td class="$row"><a href="javascript:paparDokumen($senaraiImejan.idDokumen)" class="style2">$senaraiImejan.namaDokumen</a></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function daftarDokumen() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBOperasiPenguatkuasaanView";
	document.${formName}.method="POST";
	document.${formName}.mode.value = "view";	
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}
function simpanDokumen(idLaporanTanah) {
	
	if(document.${formName}.txtNamaImej.value == ""){
		alert('Sila masukkan Nama Imej.');
  		document.${formName}.txtNamaImej.focus(); 
		return; 
	}
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Imej yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	var namaImej = document.${formName}.txtNamaImej.value;

 	var catatanImej = document.${formName}.txtCatatanImej.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBOperasiPenguatkuasaanView&hitButton=simpanDokumen&namaImej="+namaImej+"&catatanImej="+catatanImej+"&idLaporanTanah="+idLaporanTanah+"&selectedTabUpper=2"+dopost+"&mode=view&flagPopup=openPopupDokumen&modePopup=new";
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function batalDokumen(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniDokumen(){
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniDokumen() {

	if(document.${formName}.txtNamaImej.value == ""){
		alert('Sila masukkan Nama Imej.');
  		document.${formName}.txtNamaImej.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniDokumen";
	doAjaxCall${formName}("");
}
function paparDokumen(idDokumen){
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBOperasiPenguatkuasaanView";
	document.${formName}.method="POST";
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}
function hapusDokumen(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}	
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusDokumen";
	document.${formName}.submit();
}
function cetakImej(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>
