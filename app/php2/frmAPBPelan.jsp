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
  #if ($flagPopup == 'openPopupPelan')
  <tr>
    <td> #parse("app/php2/frmAPBPelanDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI PELAN</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="2" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarPelan()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td><strong>Nama Pelan</strong></td>
        </tr>
        #set ($senaraiPelan = "")
        #if ($SenaraiPelan.size() > 0)
        #foreach ($senaraiPelan in $SenaraiPelan)
        #if ($senaraiPelan.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiPelan.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiPelan.bil</td>
          <td class="$row"><a href="javascript:paparPelan($senaraiPelan.idDokumen)" class="style2">$senaraiPelan.namaDokumen</a></td>
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
function daftarPelan() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBCetakSuratKelulusanLesenKepadaPemohon";
	document.${formName}.method="POST";
	document.${formName}.mode.value = "view";	
	document.${formName}.flagPopup.value = "openPopupPelan";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}
function simpanPelan(idPermohonan) {
	
	if(document.${formName}.txtNamaPelan.value == ""){
		alert('Sila masukkan Nama Pelan.');
  		document.${formName}.txtNamaPelan.focus(); 
		return; 
	}
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Pelan yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	var namaPelan = document.${formName}.txtNamaPelan.value;
 	var catatanPelan = document.${formName}.txtCatatanPelan.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBCetakSuratKelulusanLesenKepadaPemohon&hitButton=simpanPelan&namaPelan="+namaPelan+"&catatanPelan="+catatanPelan+"&selectedTabUpper=1"+dopost+"&mode=view&flagPopup=openPopupPelan&modePopup=new&idPermohonan="+idPermohonan;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function batalPelan(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniPelan(){
	document.${formName}.flagPopup.value = "openPopupPelan";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPelan() {

	if(document.${formName}.txtNamaPelan.value == ""){
		alert('Sila masukkan Nama Pelan.');
  		document.${formName}.txtNamaPelan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.flagPopup.value = "openPopupPelan";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniPelan";
	doAjaxCall${formName}("");
}
function paparPelan(idDokumen){
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBCetakSuratKelulusanLesenKepadaPemohon";
	document.${formName}.method="POST";
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.flagPopup.value = "openPopupPelan";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}
function hapusPelan(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}	
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusPelan";
	document.${formName}.submit();
}
function cetakPelan(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function batalKemaskiniPelan(){
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
</script>
