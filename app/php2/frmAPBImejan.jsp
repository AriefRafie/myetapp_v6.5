<br/>
<div id="fileupload_progress"></div>
<div id="progressBar" style="display:none;">
  <div id="progressBarBoxContent"></div>
</div>
<!--#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Imej telah berjaya dimuatnaik.</div>";
</script>
#end-->
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup2 == 'openPopupDokumen')
  <tr>
    <td> #parse("app/php2/frmAPBImejDetail.jsp") </td>
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
          <td colspan="2" scope="row"><a href="http://g4nre.mygeoportal.gov.my" target="_blank" style="color:#0000FF">Klik di sini untuk membuka laman web MacGDI</a></td>
        </tr>
        <!-- end edit (kmie) -->
        #if ($flagPopup2 == '')
        <tr>
          <td colspan="2" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:doDaftarBaruDokumen()"/></td>
        </tr>
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td><strong>Nama Imej</strong></td>
        </tr>
        #set ($senaraiImej = "")
        #if ($SenaraiImej.size() > 0)
        #foreach ($senaraiImej in $SenaraiImej)
        #if ($senaraiImej.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiImej.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiImej.bil</td>
          <td class="$row"><a href="javascript:doPaparDokumen('$senaraiImej.idDokumenUpload')" class="style2">$senaraiImej.namaDokumen</a></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
        </tr>
        #end
        <tr>
          <td colspan="6">&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doDaftarBaruDokumen() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.flagPopup2.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "new";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function simpanDokumen(idUlasanTeknikal,idPermohonan) {
	
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Imej yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaImej.value == ""){
		alert('Sila masukkan Nama Imej.');
  		document.${formName}.txtNamaImej.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	var namaImej = document.${formName}.txtNamaImej.value;
 	var catatan = document.${formName}.txtCatatan.value ;
	var tabId = document.${formName}.selectedTabUpper.value	
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	var flagPopup = document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.mode.value = "view";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView&hitButton=doSimpanDokumen&idUlasanTeknikal="+idUlasanTeknikal+"&txtNamaImej="+namaImej+"&idPermohonan="+idPermohonan+"&txtCatatan="+catatan+"&flagPopup="+flagPopup+"&selectedTabUpper="+tabId+dopost;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function kemaskiniDokumen(){
	document.${formName}.selectedTabUpper = "1";
	document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "update";
	document.${formName}.submit();
}
function simpanKemaskiniDokumen() {
	if(document.${formName}.txtNamaImej.value == ""){
		alert('Sila masukkan Nama Imej.');
  		document.${formName}.txtNamaImej.focus(); 
		return; 
	}
	document.${formName}.selectedTabUpper = "1";
	document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniDokumen";
	document.${formName}.submit();
}
function hapusMaklumatDokumen(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.selectedTabUpper = "1";
	document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusMaklumatDokumen";
	document.${formName}.submit();
}
function doPaparDokumen(idDokumenUpload){
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.idDokumenUpload.value = idDokumenUpload;
	document.${formName}.selectedTabUpper = "1";
	document.${formName}.flagPopup.value = "openPopupMaklumatJUPEM";
	document.${formName}.flagPopup2.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function cetakImej(id){
	var url = "../servlet/ekptg.view.php2.FrmPYWDisplayImej?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>