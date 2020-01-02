        <fieldset>
        <table width="100%" border="0">
          <tr>
          <!--<input type="text" id="action" name="action" value="$action" />-->
            <td width="29%" align="right" valign="top">Penggal</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              $Bahagian_Penggal
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">No Bahagian</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtNoBahagian" id="txtNoBahagian" value="$!Bahagian_NoBahagian" size="50" $RO_General />
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Tajuk Bahagian</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtTajukBahagian" id="txtTajukBahagian" value="$!Bahagian_TajukBahagian" size="50" $RO_General />
            </td>
          </tr>  
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>
            #if ($action == 'view')
            <button type="button" value="Kemaskini" onclick="doAdd()"><font size = "1">Kemaskini</font></button>
            #elseif ($action == 'doUpdate')
            <input type="button" value="Simpan" onclick="doKemaskiniBahagian()"/>
            <input type="button" value="Batal" onclick="doCancel()" />
            #elseif ($action == 'update')
            <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSaveBahagian()"/>
            
            #else
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doBack()"/>
            #end 
</td>
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
          <legend>SENARAI BAHAGIAN</legend>
          <table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
            <td width="11%"><strong>No Bahagian</strong></td>
              <td width="10%"><strong>Penggal</strong></td>
              
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
            <td class="$row">
              <a href="javascript:viewBahagian('$fail.IDBahagian')" style="color:#0000FF">
              $fail.NoBahagian              </a></td>
              <td class="$row">$fail.NoPenggal</td>
              
              <td class="$row">$fail.TajukBahagian</td>
              <td class="$row"><input type="button" value="Hapus" onclick="doHapusBahagian('$fail.IDBahagian');" /></td>
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
