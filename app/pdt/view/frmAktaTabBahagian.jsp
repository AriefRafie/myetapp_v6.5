        
        <br />
        <fieldset>
          <legend>SENARAI BAHAGIAN</legend>
          <table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="10%"><strong>Penggal</strong></td>
              <td width="11%"><strong>No Bahagian</strong></td>
              <td width="38%"><strong>Tajuk Bahagian</strong></td>
              <td width="9%">&nbsp;</td>
            </tr>
#set ($fail = '')
#foreach ($fail in $List_CarianBahagian)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
            <tr>
              <td class="$row">$fail.NoPenggal</td>
              <td class="$row">
              
              $fail.NoBahagian              </td>
              <td class="$row">$fail.TajukBahagian</td>
              <td class="$row"></td>
            </tr>
#end
          </table>
</fieldset>
        <script type="text/javascript">
		  function viewBahagian(ID_Bahagian) {
			  document.${formName}.action = "$action";
			  document.${formName}.selectedTab.value = "$selectedTab";
			  document.${formName}.Bahagian_IDBahagian.value = ID_Bahagian;
			  document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta";
			  document.${formName}.submit();
		  }
        </script>
