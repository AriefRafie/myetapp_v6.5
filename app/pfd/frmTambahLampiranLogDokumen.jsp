<style type="text/css">
<!--
.style1 {
	color: #0033FF;
	font-style: italic;
}
-->
</style>
<input name="idLogDokumen" type="hidden" value="$idLogDokumen" />
<input name="action1" type="hidden" value="$action1" />
<input name="mode" type="hidden" value="$mode" />
<input name="url" type="hidden" value="" />
<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
  <div id="progressBarBoxContent"></div>
</div>
#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">File successfully uploaded.</div>";
</script>
#end
<fieldset>
<legend>MAKLUMAT LAMPIRAN</legend>
<table width="100%">

  <tr>
    <td width="29%" align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td width="1%" align="left" valign="top" scope="row"><div align="right">:</div></td>
    <td width="70%"><label>#foreach( $i in [1..50] )
      <input name="txtLampiran" type="file" id="txtLampiran" size="50" /><br>
    #end<br>
    </label></td>
  </tr>
  <tr>
    <td colspan="3" scope="row">&nbsp;</td>
  </tr>

  <tr>
    <td colspan="3" align="center" scope="row">
      <label></label>
      <label>
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
      </label>    </td>
  </tr>
</table>
</fieldset>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-07</strong></td>
  </tr>
</table>
<script>
function hapus1(id){
	//document.${formName}.idLampiran.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=hapusLampiran&idLampiran="+id+"";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=tambahLampiran";
	document.${formName}.submit();
}
function simpan(){
	
	
if ( !window.confirm("Adakah Anda Pasti?")) return;
	var id = document.${formName}.idLogDokumen.value ;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=simpanTambahLampiranLogDokumen&idLogDokumen="+id;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function papar_Lampiran(id) {
	    var url = "../servlet/ekptg.view.pfd.DisplayBlob2?id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}
function tambahLampiran(id) {
	// kmie, 20100906
	// check hakmilik status with eTanah
    var url = "../x/${securityToken}/ekptg.view.pfd.LogDokumen?action1=tambahLampiranLogDokumen&idLogDokumen=" + id;
    var hWnd = window.open(url, 'Tambah Lampiran Log Dokumen', 'width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>