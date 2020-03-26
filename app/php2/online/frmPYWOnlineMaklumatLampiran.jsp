<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupLampiran')
  <tr>
    <td> #parse("app/php2/online/frmPYWOnlineLampiranDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI LAMPIRAN</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="2" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarLampiran()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td><strong>Nama Lampiran</strong></td>
        </tr>
        #set ($senaraiLampiran = "")
        #if ($SenaraiLampiran.size() > 0)
        #foreach ($senaraiLampiran in $SenaraiLampiran)
        #if ($senaraiLampiran.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiLampiran.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiLampiran.bil</td>
          <td class="$row"><a href="javascript:paparLampiran('$senaraiLampiran.idDokumen')" class="style2">$!senaraiLampiran.namaDokumen</a></td>
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
function simpanLampiran(idPermohonan) {
	if(document.${formName}.txtNamaLampiran.value == ""){
		alert('Sila masukkan Nama Lampiran.');
  		document.${formName}.txtNamaLampiran.focus(); 
		return; 
	}
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Lampiran yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	var namaLampiran = document.${formName}.txtNamaLampiran.value;
 	var catatanLampiran = document.${formName}.txtCatatanLampiran.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPYWOnlineSenaraiFailView&hitButton=simpanLampiran&namaLampiran="+namaLampiran+"&catatanLampiran="+catatanLampiran+"&idPermohonan="+idPermohonan+"&selectedTabUpper=5"+dopost+"&mode=view&flagPopup=openPopupLampiran&modePopup=new";
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function batalLampiran(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniLampiran(){
	document.${formName}.flagPopup.value = "openPopupLampiran";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniLampiran() {

	if(document.${formName}.txtNamaLampiran.value == ""){
		alert('Sila masukkan Nama Lampiran.');
  		document.${formName}.txtNamaLampiran.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.flagPopup.value = "openPopupLampiran";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniLampiran";
	doAjaxCall${formName}("");
}
function paparLampiran(idDokumen){
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPYWOnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.flagPopup.value = "openPopupLampiran";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}
function hapusLampiran(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}	
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusLampiran";
	document.${formName}.submit();
}
function cetakLampiran(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function batalKemaskiniLampiran(){
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
</script>