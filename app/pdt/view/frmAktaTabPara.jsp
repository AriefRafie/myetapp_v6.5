        
        <br />
        <fieldset>
          <legend>SENARAI PARA
          </legend><table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="9%" scope="row"><strong>No Akta</strong></td>
              <td width="10%" scope="row"><strong>No Penggal</strong></td>
              <td width="11%" scope="row"><strong>No Bahagian</strong></td>
              <td width="8%" scope="row"><strong>No Bab</strong></td>
              <td width="14%" scope="row"><strong>Seksyen</strong></td>
              <td width="14%" scope="row"><strong>Sub Seksyen</strong></td>
              <td width="25%" scope="row"><strong>Para</strong></td>
              <td width="9%" scope="row">&nbsp;</td>
            </tr>
#set ($fail = '')
#foreach ($fail in $List_CarianPara)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
            <tr>
            <td class="$row">
    #if ($fail.No != '') 
              $!fail.NoAkta
    #else
              $!fail.NoAkta
    #end    </td>
              <td class="$row">$fail.NoPenggal</td>
              <td class="$row">$fail.NoBahagian</td>
              <td class="$row">$fail.NoBab</td>
              <td class="$row">$fail.Seksyen</td>
              <td class="$row">$fail.SubSeksyen</td>
              <td class="$row">$fail.Para</td>
              <td class="$row"></td>
            </tr>
#end
          </table>
</fieldset>
        <input type="hidden" id="Para_IDPara" name="Para_IDPara" value="$!Para_IDPara" />
        <script type="text/javascript">
		  function viewPara(ID_Para) {
			  document.${formName}.action = "$action";
			  document.${formName}.selectedTab.value = "$selectedTab";
			  document.${formName}.Para_IDPara.value = ID_Para;
			  document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta";
			  document.${formName}.submit();
		  }
        </script>
