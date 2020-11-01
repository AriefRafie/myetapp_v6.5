<style type="text/css">
<!--
.style1 {
	color: #0000FF
}
-->
</style>
<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
  <div id="progressBarBoxContent"></div>
</div>
#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Dokumen berjaya dimuat naik.</div>";
</script>
#end
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupDokumen')
  <tr>
    <td colspan="2"> #parse("app/php2/frmImejanLaporanTanahDetail.jsp") </td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  #end
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>SENARAI IMEJAN</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="4" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarDokumen()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>Nama Imejan</strong></td>
          <td width="40%"><strong>Keterangan</strong></td>
          <td width="35%"><strong>Muat Turun</strong></td>
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
          <td class="$row"><a href="javascript:updateDokumen($senaraiImejan.idDokumen)" style="color:#0000FF">$senaraiImejan.namaImej</a></td>
          <td class="$row">$senaraiImejan.catatan</td>
          <td class="$row"><a href="javascript:javascript:cetakImej($senaraiImejan.idDokumen)" style="color:#0000FF">$senaraiImejan.namaFail</a></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%">
    	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
    	<input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onclick="hantar()"/>
    </td>
  </tr>
</table>
<script>
function daftarDokumen() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmSenaraiLaporanTanahView";
	document.${formName}.method="POST";
	document.${formName}.step.value = "kemaskini";
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}
function simpanDokumen(idLaporan,idPermohonan) {

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

	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmSenaraiLaporanTanahView&hitButton=simpanDokumen&namaImej="+namaImej+"&idPermohonan="+idPermohonan+"&catatanImej="+catatanImej+"&idLaporan="+idLaporan+"&selectedTab=3"+dopost+"&step=kemaskini&flagPopup=openPopupDokumen&modePopup=new";
	
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
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButton.value = "simpanKemaskiniDokumen";
	doAjaxCall${formName}("");
}
function updateDokumen(idDokumen){
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmSenaraiLaporanTanahView";
	document.${formName}.method="POST";
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "update";
	document.${formName}.submit();
}
function batalKemaskiniDokumen(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
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
	var url = "../servlet/ekptg.view.php2.FrmDisplayImejLaporanTanah?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>
