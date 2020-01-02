<fieldset>
  <legend><strong>SENARAI FAIL BORANG J</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2" style="border:1px solid #000">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="20%"><strong>NAMA PEMOHON</strong></td>
      <td width="20%"><strong>NAMA SI MATI</strong></td>
      <td width="15%"><strong>TARIKH HANTAR</strong></td>
      <td width="10%" align="center"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListBorangJ)
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
        	#set ($row_color = 'style="color:#FF0000"')
        #elseif ($list.date14 == '1')
        	#set ($row_color = 'style="color:#009900"')
        #elseif ($list.date05 == '1')
        	#set ($row_color = 'style="color:#0000FF"')
        #else
        	#set ($row_color = '')
      	#end
      <td class="$row" valign="top" $row_color align="center">$list.No</td>
      <td class="$row" valign="top" $row_color><a href="javascript:viewBorangJ('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top" $row_color>$list.NoPermohonan</td>
      <td class="$row" valign="top" $row_color>$list.NamaPemohon</td>
      <td class="$row" valign="top" $row_color>$list.NamaSiMati</td>
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
<br />
<fieldset>
  <legend><strong>SENARAI FAIL BORANG J YANG TELAH SELESAI</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2" style="border:1px solid #000">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="20%"><strong>NAMA PEMOHON</strong></td>
      <td width="20%"><strong>NAMA SI MATI</strong></td>
      <td width="15%"><strong>TARIKH HANTAR</strong></td>
      <td width="10%" align="center"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListBorangJSelesai)
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
      <td class="$row" valign="top"><a href="javascript:viewBorangJ('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NoPermohonan</td>
      <td class="$row" valign="top">$list.NamaPemohon</td>
      <td class="$row" valign="top">$list.NamaSiMati</td>
      <td class="$row" valign="top">$list.TarikhHantar</td>
      <td class="$row" valign="top" align="center">$list.Status</td>
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
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" />
<!--<input type="hidden" id="mode" name="mode" value="$mode" />-->
<!--<input name="action" id="action" type="hidden" value="$action" />-->
<script>
function viewBorangJ(ID_PERMOHONAN) {
	document.${formName}.action = "$EkptgUtil.getTabID("JKSM",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewBorangJ&action2=viewBorangJ&ID_PERMOHONAN=" + ID_PERMOHONAN;
	document.${formName}.submit();
}
</script>