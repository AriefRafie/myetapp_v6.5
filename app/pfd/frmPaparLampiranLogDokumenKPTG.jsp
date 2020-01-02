
<input name="idLogDokumenKPTG" type="hidden" value="$idLogDokumenKPTG" />
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
    <td width="29%" scope="row">No Rujukan Dokumen</td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">$no_Rujukan_Lain</td>
  </tr>
  <tr>
    <td scope="row" valign="top">Jenis Dokumen</td>
    <td scope="row" valign="top"><div align="right">:</div></td>
    <td>$jenis_Dokumen</td>
  </tr>

  <tr>
    <td align="left" valign="top" scope="row">Lampiran</td>
    <td align="left" valign="top" scope="row"><div align="right">:</div></td>
    <td><label>
    <input name="txtLampiran" type="file" id="txtLampiran" size="50" /><br>
    </label></td>
  </tr>
  <tr>
    <td colspan="3" scope="row">&nbsp;</td>
  </tr>

  <tr>
    <td colspan="3" align="center" scope="row">
      <label>
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
      </label>
      <label>
        <input type="button" name="cmdTutup" id="cmdTutup" value="Kembali" onclick="kembali()" />
        </label>    </td>
  </tr>
</table>
</fieldset>
<fieldset>
  <legend>SENARAI LAMPIRAN</legend>
<table width="100%">
  <tr class="table_header">
    <td width="1%" scope="row"><strong>No</strong></td>
    <td width="40%"><strong>Nama Fail</strong></td>
    <td width="20%"><strong>Jenis Fail</strong></td>
    <td width="2%">&nbsp;</td>
  </tr>
  #foreach ($listLampiran in $SenaraiLampiran)
   #if ($listLampiran.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listLampiran.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listLampiran.bil</td>
    #if ($listLampiran.bil != '') 
    <td class="$row"><a href="javascript:papar_Lampiran('$listLampiran.id_Lampiran')"><font color="blue">$listLampiran.nama_Fail</font></a></td>
    #else
    <td class="$row">$listLampiran.nama_Fail</td>
    #end
    <td class="$row">$listLampiran.jenis_Mime</td>
    <td class="$row"><input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus1('$listLampiran.id_Lampiran')" /></td>
  </tr>
 #end
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
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=hapusLampiran&idLampiran="+id+"";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=";
	document.${formName}.submit();
}
function simpan(){
	
	
if ( !window.confirm("Adakah Anda Pasti?")) return;
	var id = document.${formName}.idLogDokumenKPTG.value ;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=simpanLampiran&idLogDokumenKPTG="+id;
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
</script>