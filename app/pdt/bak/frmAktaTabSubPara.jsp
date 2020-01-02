        <fieldset>
        <table width="100%" border="0">
          <tr>
            <td width="29%" align="right" valign="top">Penggal</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              $SubPara_Penggal 
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Bahagian</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $SubPara_Bahagian
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Bab</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $SubPara_Bab
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Seksyen</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $SubPara_Seksyen
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Subseksyen</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $SubPara_SubSeksyen
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Para</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $SubPara_Para
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Sub Para</td>
            <td align="center" valign="top">:</td>
            <td valign="top"><textarea name="txtSubPara" cols="50" rows="5" id="txtSubPara" $RO_General>$!SubPara_SubPara</textarea>
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Jadual</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtJadual" id="txtJadual" value="$!SubPara_Jadual" size="50" $RO_General />
            </td>
          </tr>  
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>#if ($action == 'view')
              <input type="button" value="Kemaskini" onclick="doAdd()"/>
#elseif ($action == 'doUpdate')
<input type="button" value="Simpan" onclick="doKemaskiniSubPara()"/>
<input type="button" value="Batal" onclick="doCancel()" />
#elseif ($action == 'update')
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSaveSubPara()"/>
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
