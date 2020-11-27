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
    <td> #parse("app/php2/frmPLPLampiranDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI LAMPIRAN</strong></legend>
      <table align="center" width="100%">
		#if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
        <tr>
          <td colspan="2" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarDokumen()"/></td>
        </tr>
        #end
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
          <td class="$row"><a href="javascript:paparDokumen($senaraiLampiran.idDokumen)" class="style2">$senaraiLampiran.namaDokumen</a></td>
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
  <tr>
    <td align="center">
    <input type="button" name="cmKembali" id="cmKembali" value="Kembali" onclick="kembali()"/>
    <input name="cmdCetak" type="button" value="Cetak" onClick="javascript:setTable('tableReportLaporanTanah')"/>
    </td>
  </tr>
</table>

<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function daftarDokumen() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPLPLawatanTapakView";
	document.${formName}.method="POST";
	document.${formName}.actionPelepasan.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}
function simpanDokumen2(idLaporanTanah,idHakmilikPermohonan,idTanahGanti,flagJenisTanah) {

	if(document.${formName}.txtNamaLampiran.value == ""){
		alert('Sila masukkan Nama Lampiran.');
  		document.${formName}.txtNamaImej.focus();
		return;
	}
	if(document.${formName}.fileupload2.value == ""){
		alert('Sila pilih Lampiran yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload2.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	var namaImej = document.${formName}.txtNamaLampiran.value;
 	var catatanImej = document.${formName}.txtCatatanLampiran.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;

	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPLPLawatanTapakView&hitButton=simpanDokumenLampiran&namaImej="+namaImej+"&catatanImej="+catatanImej+"&idLaporanTanah="+idLaporanTanah+"&selectedTabUpper=5"+dopost+"&actionPelepasan=papar&mode=view&flagPopup=openPopupDokumen&modePopup=new&idHakmilikPermohonan="+idHakmilikPermohonan+"&idTanahGanti="+idTanahGanti+"&flagJenisTanah="+flagJenisTanah;
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
function kemaskiniDokumen2(){
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniDokumen2() {

	if(document.${formName}.txtNamaLampiran.value == ""){
		alert('Sila masukkan Nama Lampiran.');
  		document.${formName}.txtNamaLampiran.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniLampiran";
	doAjaxCall${formName}("");
}
function paparDokumen2(idDokumen){
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPLPLawatanTapakView";
	document.${formName}.method="POST";
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.flagPopup.value = "openPopupLampiran";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}
function hapusDokumen2(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusDokumen";
	document.${formName}.submit();
}
function batalKemaskiniDokumen2(){
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}

function cetakImej(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>
