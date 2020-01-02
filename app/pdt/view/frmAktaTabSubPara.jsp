        
        <br />
        <fieldset>
          <legend>SENARAI SUB PARA</legend>
          <table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="9%" scope="row"><strong>No Akta</strong></td>
              <td width="10%" scope="row"><strong>No Penggal</strong></td>
              <td width="11%" scope="row"><strong>No Bahagian</strong></td>
              <td width="8%" scope="row"><strong>No Bab</strong></td>
              <td width="12%" scope="row"><strong>Seksyen</strong></td>
              <td width="12%" scope="row"><strong>Sub Seksyen</strong></td>
              <td width="12%" scope="row"><strong>Para</strong></td>
              <td width="16%" scope="row"><strong>Sub Para</strong></td>
              <td width="10%" scope="row">&nbsp;</td>
            </tr>
#set ($fail = '')
#foreach ($fail in $List_CarianSubPara)
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
              <a href="javascript:viewSubPara('$fail.IDSubPara')" style="color:#0000FF">$fail.NoAkta</a>
    #else
              $fail.NoAkta
    #end    </td>
              <td class="$row">$fail.NoPenggal</td>
              <td class="$row">$fail.NoBahagian</td>
              <td class="$row">$fail.NoBab</td>
              <td class="$row">$fail.Seksyen</td>
              <td class="$row">$fail.SubSeksyen</td>
              <td class="$row">$fail.Para</td>
              <td class="$row">$fail.SubPara</td>
              <td class="$row"><input type="button" value="Hapus" onclick="doHapusSubPara('$fail.IDSubPara');" /></td>
            </tr>
#end
          </table>
</fieldset>
        <input type="hidden" id="SubPara_IDSubPara" name="SubPara_IDSubPara" value="$SubPara_IDSubPara" />
        <script type="text/javascript">
		  function viewSubPara(ID_SubPara) {
			  document.${formName}.action = "$action";
			  document.${formName}.selectedTab.value = "$selectedTab";
			  document.${formName}.SubPara_IDSubPara.value = ID_SubPara;
			  document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta";
			  document.${formName}.submit();
		  }
        </script>
