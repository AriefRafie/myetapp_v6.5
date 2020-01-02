        <fieldset>
        <table width="100%" border="0">
        
        <tr>
            <td width="29%" align="right" valign="top">No Bab</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtNoBab" id="txtNoBab" value="$!Bab_NoBab" size="50" $RO_General />
            </td>
          </tr>
        
          <tr>
            <td width="29%" align="right" valign="top">Penggal</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              $Bab_Penggal 
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Bahagian</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $Bab_Bahagian
            </td>
          </tr>  
            
          <tr>
            <td width="29%" align="right" valign="top">Tajuk Bab</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtTajukBab" id="txtTajukBab" value="$!Bab_TajukBab" size="50" $RO_General />
            </td>
          </tr>  
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>#if ($action == 'view')
              <button type="button" value="Kemaskini" onclick="doAdd()"><font size = "1">Kemaskini</font></button>
#elseif ($action == 'doUpdate')
<input type="button" value="Simpan" onclick="doKemaskiniBab($!Bab_IDBab)"/>
<input type="button" value="Batal" onclick="doCancel()" />
#elseif ($action == 'update')
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSaveBab()"/>
#else
<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doBack()"/>
#end </td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        </fieldset>
        <br />
        <fieldset>
          <legend>SENARAI BAB
          </legend><table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
            <td width="12%"><strong>No Bab</strong></td>
              <td width="12%"><strong>Penggal</strong></td>
              <td width="13%"><strong>Bahagian</strong></td>
              
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
            <td class="$row"><a href="javascript:viewBab('$fail.IDBab')" style="color:#0000FF">
              $fail.NoBab
              </a></td>
              <td class="$row">$fail.NoPenggal</td>
              <td class="$row">$fail.NoBahagian</td>
              
              <td class="$row">$fail.TajukBab</td>
              <td class="$row"><input type="button" value="Hapus" onclick="doHapusBab('$fail.IDBab');" /></td>
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
