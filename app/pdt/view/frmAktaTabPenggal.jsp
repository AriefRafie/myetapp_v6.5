       
        <br />
        <fieldset>
          <legend>SENARAI PENGGAL</legend>
          <table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="19%"><strong>No Penggal</strong></td>
              <td width="33%"><strong>Tajuk Penggal</strong></td>
              <td width="16%">&nbsp;</td>
            </tr>
#set ($fail = '')
#foreach ($fail in $List_CarianPenggal)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
            <tr>
              <td class="$row">
              
              $fail.NoPenggal              </td>
              <td width="33%" class="$row">$fail.TajukPenggal</td>
              <td width="16%" class="$row"></td>
            </tr>
#end
          </table>
</fieldset>
      
