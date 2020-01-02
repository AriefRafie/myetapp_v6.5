#if ($UploadStatus == 'true')
<div class="success">Fail telah berjaya dimuatnaik.</div>
<br />
#end
#if ($NoFileSpecified == 'true')
<div class="error">Tiada fail yang dipilih untuk langkah seterusnya.</div>
<br />
#end
#if ($FileProcessSuccess == 'true')
<div class="success">Fail yang dipilih telah berjaya diproses.</div>
<br />
#end
#if ($FileProcessFail == 'true')
<div class="error">Fail yang dipilih tidak berjaya diproses. Sila cuba sebentar lagi.</div>
<br />
#end
#if ($FileDeleteSuccess == 'true')
<div class="success">Fail yang dipilih telah berjaya dihapuskan.</div>
<br />
#end
#if ($FileDeleteFail == 'true')
<div class="error">Fail yang dipilih tidak berjaya dihapuskan. Sila cuba sebentar lagi.</div>
<br />
#end
<fieldset>
  <legend><strong>eKadaster</strong>
  </legend><table width="100%">
    <tr>
      <td width="30%" align="right" valign="top" scope="row">Fail untuk Dimuatnaik</td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="UPLOAD_FILE" type="file" id="UPLOAD_FILE" style="font-family:Verdana, Geneva, sans-serif" size="50" /></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row"><input id="cmdCari" name="cmdCari" type="button" value="Muat Naik" onclick="uploadFile();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyForm();" /></td>
    </tr> 
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>Senarai Fail yang Dimuatnaik</strong></legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%">
    <tr class="table_header">
      <td valign="top" width="5%" align="center"><strong>No</strong></td>
      <td valign="top" width="25%" align="left"><strong>Nama Fail</strong></td>
      <td valign="top" width="20%" align="center"><strong>Nama Pegawai</strong></td>
      <td valign="top" width="15%" align="center"><strong>Tarikh Muatnaik</strong></td>
      <td valign="top" width="15%" align="center"><strong>Kod SHA</strong></td>
      <td valign="top" width="10%" align="center">&nbsp;</td>
      <td valign="top" width="10%" align="center">&nbsp;</td>
    </tr>
#set ($list = '')
#foreach ($list in $ListUploaded)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
      <td class="$row" valign="top" align="center">$list.No</td>
      <td class="$row" valign="top">
#if ($list.MIME == 'text/plain')
        <a href="javascript:displayFile('$list.IDFail')" style="color:#0000FF">$list.NamaFail</a>
#else
        <a href="javascript:displayFile('$list.IDFail')" style="color:#0000FF">$list.NamaFail</a>
#end        
#if ($list.MIME != '')
        ($list.MIME)
#end        
      </td>
      <td class="$row" valign="top" align="center">$list.NamaPegawai</td>
      <td class="$row" valign="top" align="center">$list.TarikhMuatNaik</td>
      <td class="$row" valign="top" align="center">$list.SHA1</td>
      <td class="$row" valign="top" align="center">
        <input type="button" id="cmdGetXML" name="cmdGetXML" value="JANA XML" onclick="generateXML('$list.IDFail');">
      </td>
      <td class="$row" valign="top" align="center">
        <input type="button" id="cmdHapus" name="cmdHapus" value="HAPUS" onclick="deleteFile('$list.IDFail');">
      </td>
    #else
      <td colspan="7" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="7">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<input name="action2" id="action2" type="hidden" />
#if ($UploadStatus == 'true' && $isMIMEOkay == 'true' && $IDFail != '')
<script type="text/javascript">
  var ans = confirm('Fail telah berjaya dimuatnaik. Anda mahu teruskan dengan memproses fail tersebut?');
  if (ans) {
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewEKadaster&action2=processFile&IDFail=$IDFail";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>
#end
<script type="text/javascript">
  function processFile(IDFail) {	
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewEKadaster&action2=processFile&IDFail=" + IDFail;
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function deleteFile(IDFail) {	
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewEKadaster&action2=deleteFile&IDFail=" + IDFail;
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function displayFile(IDFail) {	
      var url = "../servlet/ekptg.view.integrasi.DisplayText?action2=displayEKadaster&IDFail=" + IDFail;
	  var hWnd = window.open(url,'DisplayFile','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
  }
  function uploadFile() {	
      if (document.${formName}.UPLOAD_FILE.value == '') {
		  alert('Sila pilih fail untuk dimuatnaik.');
		  return;
	  }
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewEKadaster&action2=uploadFile";
      document.${formName}.method = "POST";
      document.${formName}.enctype = "multipart/form-data";
      document.${formName}.encoding = "multipart/form-data";
      document.${formName}.submit();
  }
  function generateXML(IDFail) {	
      var url = "../servlet/ekptg.view.integrasi.DisplayText?&action2=generateXML&IDFail=" + IDFail;
      var hWnd = window.open(url,'printuser','width=500,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
      hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();
  }
  function emptyForm() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewEKadaster&action2=";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>