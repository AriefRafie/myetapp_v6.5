        <fieldset>
        <table width="100%" border="0">
        <!--
          <tr>
            <td width="29%" align="right" valign="top">Penggal</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              $SubSeksyen_Penggal 
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Bahagian</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $SubSeksyen_Bahagian
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Bab</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $SubSeksyen_Bab
            </td>
          </tr>  
          -->
          <tr>
            <td width="29%" align="right" valign="top">Seksyen</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $SubSeksyen_Seksyen
            </td>
          </tr>  
	<tr>
	<td width="29%" align="right" valign="top">No Subseksyen</td>
	<td align="center" valign="top">:</td>
	<td valign="top">
	<input type="text" name="txtNoSubSeksyen" id="txtNoSubSeksyen" 
	value="$!SubSeksyen_NoSubSeksyen" size="15" $RO_General />
	</td>
	</tr>
	<tr>
	<td width="29%" align="right" valign="top">Tajuk Subseksyen</td>
	<td align="center" valign="top">:</td>
	<td valign="top">
	<input type="text" name="txtTajukSubSeksyen" id="txtTajukSubSeksyen" 
	value="$!SubSeksyen_TajukSubSeksyen" size="50" $RO_General />
	</td>
	</tr>
          
          <tr>
            <td width="29%" align="right" valign="top">Subseksyen</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
            	<textarea rows="3" cols="47" name="txtSubSeksyen" $RO_General>$!SubSeksyen_SubSeksyen</textarea>
		<!--
              <input type="text" name="txtTajukSubSeksyen" id="txtTajukSubSeksyen" value="$!txtTajukSubSeksyen" size="50" $RO_General />
            -->
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Proviso</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <textarea rows="5" cols="47" name="txtProviso" $RO_General>$!SubSeksyen_Proviso</textarea>
              <!--
              <input type="text" name="txtProviso" id="txtProviso" value="$!SubSeksyen_Proviso" size="50" $RO_General />
              -->
            </td>
          </tr>  
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>#if ($action == 'view')
              <input type="button" value="Kemaskini" onclick="doAdd()"/>
#elseif ($action == 'doUpdate')
<input type="button" value="Simpan" onclick="doKemaskiniSubSeksyen($SubSeksyen_IDSubSeksyen)"/>
<input type="button" value="Batal" onclick="doCancel()" />
#elseif ($action == 'update')
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSaveSubSeksyen()"/>
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
