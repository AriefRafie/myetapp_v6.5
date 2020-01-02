        
        <br />
        <fieldset>
          <legend>SENARAI SUB SEKSYEN
          </legend><table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="8%" scope="row"><strong>No</strong></td>
              <td width="12%"><strong>Seksyen</strong></td>
              <td width="68%"><strong>Sub Seksyen - Proviso</strong></td>
              <td width="12%">&nbsp;</td>
            </tr>
#set ($fail = '')
#foreach ($fail in $List_CarianSubSeksyen)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
            <tr>
              <td class="$row">
              <a href="javascript:viewSubSeksyen('$fail.IDSubSeksyen')" style="color:#0000FF">
              $fail.No              </a></td>
              <td class="$row">$fail.Seksyen</td>
              <td class="$row">$fail.NoSubSeksyen $fail.SubSeksyen <p> $fail.SubSeksyenProviso</td>
              <td class="$row"><input type="button" value="Hapus" onclick="doHapusSubSeksyen('$fail.IDSubSeksyen');" /></td>
            </tr>
#end          
          </table>
</fieldset>
        <input type="hidden" id="SubSeksyen_IDSubSeksyen" name="SubSeksyen_IDSubSeksyen" value="$SubSeksyen_IDSubSeksyen" />
        <script type="text/javascript">
		  function viewSubSeksyen(ID_SubSeksyen) {
			  document.${formName}.action = "$action";
			  document.${formName}.selectedTab.value = "$selectedTab";
			  document.${formName}.SubSeksyen_IDSubSeksyen.value = ID_SubSeksyen;
			  document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta";
			  document.${formName}.submit();
		  }
        </script>
