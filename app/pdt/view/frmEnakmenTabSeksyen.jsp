        <fieldset>
        <table width="100%" border="0">
          <tr>
            <td width="29%" align="right" valign="top">Penggal</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              $Seksyen_Penggal 
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Bahagian</td>
            <td align="center" valign="top">:</td>
            <td valign="top">$Seksyen_Bahagian</td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Bab</td>
            <td align="center" valign="top">:</td>
            <td valign="top">$Seksyen_Bab</td>
          </tr> 

<tr>
<td width="29%" align="right" valign="top">No Seksyen</td>
<td align="center" valign="top">:</td>
<td valign="top">
<input type="text" name="txtNoSeksyen" id="txtNoSeksyen" value="$!Seksyen_NoSeksyen" 
size="15" $RO_General />
</td>
</tr> 

<tr>
<td width="29%" align="right" valign="top">Tajuk Seksyen</td>
<td align="center" valign="top">:</td>
<td valign="top">
<input type="text" name="txtTajukSeksyen" id="txtTajukSeksyen" value="$!Seksyen_TajukSeksyen" size="50" $RO_General />
</td>
</tr>
          
          <tr>
            <td width="29%" align="right" valign="top">Seksyen</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
            <!--
              <input type="text" name="txtSeksyen" id="txtSeksyen" value="$!Seksyen_Seksyen" size="50" $RO_General />
            -->
              <textarea rows="3" cols="47" name="txtSeksyen" $RO_General>$!Seksyen_Seksyen</textarea>

            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Proviso</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <textarea rows="5" cols="47" name="txtProviso" $RO_General>$!Seksyen_Proviso</textarea>
              <!--
              <input type="text" name="txtProviso" id="txtProviso" value="$!Seksyen_Proviso" size="50" $RO_General />
              -->
            </td>
          </tr>  
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>#if ($action == 'view')
              <input type="button" value="Kemaskini" onclick="doAdd()"/>
#elseif ($action == 'doUpdate')
<input type="button" value="Simpan" onclick="doKemaskiniSeksyen()"/>
<input type="button" value="Batal" onclick="doCancel()" />
#elseif ($action == 'update')
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSaveSeksyen()"/>
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
              <a href="javascript:viewSeksyen('$fail.IDSeksyen')" style="color:#0000FF">
              $fail.NoSeksyen - $fail.TajukSeksyen
              </a></td>
              <td class="$row">$fail.Seksyen</td>
              <td class="$row"><input type="button" value="Hapus" onclick="doHapusSeksyen('$fail.IDSeksyen');" /></td>
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
