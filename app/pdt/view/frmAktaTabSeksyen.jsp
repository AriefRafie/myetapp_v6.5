    <fieldset>    
          <legend>SENARAI SEKSYEN
          </legend><table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="10%"><strong>Bab</strong></td>
              <td width="23%"><strong>Tajuk Seksyen</strong></td>
              <td width="46%"><strong>Seksyen</strong></td>
              <td width="15%">&nbsp;</td>
            </tr>
#set ($fail = '')
#foreach ($fail in $List_CarianSeksyen)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
            <tr>
             <td class="$row">$fail.NoBab</td>
              <td class="$row">
            
              $fail.NoSeksyen - $fail.TajukSeksyen
              </td>
              <td class="$row">$fail.Seksyen</td>
              <td class="$row"></td>
            </tr>
#end          
          </table>
</fieldset>
        <input type="hidden" id="Seksyen_IDSeksyen" name="Seksyen_IDSeksyen" value="$Seksyen_IDSeksyen" />
        <script type="text/javascript">
		  function viewSeksyen(ID_Seksyen) {
			  document.${formName}.action = "$action";
			  document.${formName}.selectedTab.value = "$selectedTab";
			  document.${formName}.Seksyen_IDSeksyen.value = ID_Seksyen;
			  document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta";
			  document.${formName}.submit();
		  }
        </script>
