        
        <br />
        <fieldset>
          <legend>SENARAI BAB
          </legend><table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="12%"><strong>Penggal</strong></td>
              <td width="13%"><strong>Bahagian</strong></td>
              <td width="12%"><strong>No Bab</strong></td>
              <td width="52%"><strong>Tajuk Bab</strong></td>
              <td width="11%">&nbsp;</td>
            </tr>
#set ($fail = '')
#foreach ($fail in $List_CarianBab)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
            <tr>
              <td class="$row">$fail.NoPenggal</td>
              <td class="$row">$fail.NoBahagian</td>
              <td class="$row">
              $fail.NoBab
              </td>
              <td class="$row">$fail.TajukBab</td>
              <td class="$row"></td>
            </tr>
#end
          </table>
</fieldset>
        <script type="text/javascript">
		  function viewBab(ID_Bab) {
			  document.${formName}.action = "$action";
			  document.${formName}.selectedTab.value = "$selectedTab";
			  document.${formName}.Bab_IDBab.value = ID_Bab;
			  document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta";
			  document.${formName}.submit();
		  }
        </script>
