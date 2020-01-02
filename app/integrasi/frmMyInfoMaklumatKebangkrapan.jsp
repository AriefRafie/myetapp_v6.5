<fieldset>
  <legend><strong>MUAT NAIK FAIL TEKS YANG TELAH DIKEMASKINI</strong></legend>
  <br />
  <table width="100%" border="0" align="center">
    <tr>
      <td width="38%" align="right" valign="top" nowrap="nowrap"><strong>FAIL</strong></td>
      <td width="2%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="60%" align="left" valign="top" nowrap="nowrap"><input type="file" id="FILE_UPLOAD" name="FILE_UPLOAD" size="50" /></td>
    </tr>
    <tr>
      <td width="38%" align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td width="2%" align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td width="60%" align="left" valign="top" nowrap="nowrap"><input id="cmdUpload" name="cmdUpload" type="button" value="Muat Naik" onclick="uploadFile();" /></td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL BAGI SEMAKAN MALAYSIA DEPARTMENT OF INSOLVENCY</strong></legend>
#if ($isJIMUser == 'true')  
  <input type="button" id="cmdGenText" name="cmdGenText" value="Jana Fail Teks" onclick="genText();" />&nbsp;
  <input type="button" id="cmdGenText" name="cmdGenText" value="Jana Fail Teks (Semua Permohonan)" onclick="genTextAll();" /><br /><br />
#end  
  <table width="100%" cellspacing="0" cellpadding="2" style="border:1px solid #000">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="20%"><strong>NAMA PEMOHON</strong></td>
      <td width="15%"><strong>NO KP PEMOHON</strong></td>
      <td width="15%"><strong>TARIKH HANTAR</strong></td>
      <td width="15%" align="center"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListMaklumatKebangkrapan)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '')
    	#if ($list.date30 == '1')
        	#set ($row_color = 'style="text-transform:uppercase; color:#FF0000"')
        #elseif ($list.date14 == '1')
        	#set ($row_color = 'style="text-transform:uppercase; color:#009900"')
        #elseif ($list.date05 == '1')
        	#set ($row_color = 'style="text-transform:uppercase; color:#0000FF"')
        #else
        	#set ($row_color = 'style="text-transform:uppercase"')
      	#end
        #if ($list.NoFail == '')
        	#set ($list.NoFail = 'BELUM DIPASTIKAN')
        #end
        #if ($list.Status == '1')
        	#set ($list.Status = 'BARU')
        #elseif ($list.Status == '2')
        	#set ($list.Status = 'DALAM PROSES')
        #elseif ($list.Status == '3')
        	#set ($list.Status = 'SELESAI')
        #end
      <td class="$row" valign="top" $row_color align="center">$list.No</td>
      <td class="$row" valign="top" $row_color><a href="javascript:viewMaklumatKebangkrapan('$list.ID_PERMOHONAN', '$list.ID_PB')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top" $row_color>$list.NoPermohonan</td>
      <td class="$row" valign="top" $row_color>$list.NamaPemohon</td>
      <td class="$row" valign="top" $row_color>$list.NoKPPemohon</td>
      <td class="$row" valign="top" $row_color>$list.TarikhHantar</td>
      <td class="$row" valign="top" $row_color align="center">$list.Status</td>
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
#if ($isJIMUser == 'false')
<br />
<fieldset>
  <legend><strong>SENARAI FAIL BAGI SEMAKAN MALAYSIA DEPARTMENT OF INSOLVENCY YANG TELAH SELESAI</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2" style="border:1px solid #000">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="20%"><strong>NAMA PEMOHON</strong></td>
      <td width="15%"><strong>NO KP PEMOHON</strong></td>
      <td width="15%"><strong>TARIKH HANTAR</strong></td>
      <td width="15%" align="center"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListMaklumatKebangkrapanSelesai)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
        #if ($list.NoFail == '')
        	#set ($list.NoFail = 'BELUM DIPASTIKAN')
        #end
      <td class="$row" valign="top" style="text-transform:uppercase" align="center">$list.No</td>
      <td class="$row" valign="top" style="text-transform:uppercase"><a href="javascript:viewMaklumatKebangkrapan('$list.ID_PERMOHONAN', '$list.ID_PB')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top" style="text-transform:uppercase">$list.NoPermohonan</td>
      <td class="$row" valign="top" style="text-transform:uppercase">$list.NamaPemohon</td>
      <td class="$row" valign="top" style="text-transform:uppercase">$list.NoKPPemohon</td>
      <td class="$row" valign="top" style="text-transform:uppercase">$list.TarikhHantar</td>
      <td class="$row" valign="top" style="text-transform:uppercase" align="center">$list.Status</td>
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
#end
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" />
<input type="hidden" id="ID_PB" name="ID_PB" />
<input type="hidden" id="mode" name="mode" value="$mode" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script>
function viewMaklumatKebangkrapan(ID_PERMOHONAN, ID_PB) {
	document.${formName}.action = "$EkptgUtil.getTabID("JIM",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewMaklumatKebangkrapan&action2=viewMaklumatKebangkrapan&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_PB=" + ID_PB;
	document.${formName}.submit();
}
function genText() {
      var url = "../servlet/ekptg.view.integrasi.DisplayText?&action2=generateMDI";
      var hWnd = window.open(url, 'printuser', 'width=500, height=300,  resizable=yes, scrollbars=yes, copyhistory=yes, location=no, directories=no, status=yes, toolbar=no, menubar=no');
      if ((document.window != null) && (!hWnd.opener))
      hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();
}
function genTextAll() {
      var url = "../servlet/ekptg.view.integrasi.DisplayText?&action2=generateMDIAll";
      var hWnd = window.open(url, 'printuser', 'width=500, height=300,  resizable=yes, scrollbars=yes, copyhistory=yes, location=no, directories=no, status=yes, toolbar=no, menubar=no');
      if ((document.window != null) && (!hWnd.opener))
      hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();
}
  function uploadFile() {
      if (!window.confirm("Sila pastikan tiada tanda \" di dalam fail yang akan dimuatnaik. Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMyInfoMaklumatKebangkrapan&action2=uploadFile";
      document.${formName}.enctype = "multipart/form-data";
      document.${formName}.encoding = "multipart/form-data";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>