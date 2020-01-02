<style type="text/css">
<!--
#parse("css/eTapp_PHP.css") .style1 {
color: #FF0000
}
-->
</style>

  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>

#if ($close_window == 'yes')
<body onLoad = closeWin();>
#end

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
  <tr>
    <td><fieldset>
      <legend><strong>MUAT NAIK LAMPIRAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%" valign="top"><span class="style1">*</span></td>
          <td width="28%" valign="top">Lampiran</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top"><input id="fileupload" name="fileupload" type="file" size="40"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="simpan()">
            <input type="button" name="cmdKembali" id="cmdKembali" value="Tutup" onClick="kembali()"></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function kembali() {	
	window.close();
}
function simpan() {
	
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Lampiran yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	var idUlasanTeknikal = document.${formName}.idUlasanTeknikal.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPopupMuatNaikLampiranTeknikalView&hitButton=simpanDokumen&idUlasanTeknikal="+idUlasanTeknikal+"&selectedTabUpper=5"+dopost;
	
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}

function closeWin(){
	window.opener.refreshFromMuatNaikLampiran();
	window.close();
}
</script>
