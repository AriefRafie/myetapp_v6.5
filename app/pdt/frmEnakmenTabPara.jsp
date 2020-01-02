<div id="mydiv">

        <fieldset>
        <table width="100%" border="0">
          <tr>
            <td width="29%" align="right" valign="top">Penggal</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              $Para_Penggal 
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Bahagian</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $Para_Bahagian
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Bab</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $Para_Bab
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Seksyen</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $Para_Seksyen
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Subseksyen</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              $Para_SubSeksyen
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Para</td>
            <td align="center" valign="top">:</td>
            <td valign="top"><textarea name="txtPara" cols="50" rows="5" id="txtPara">$Para_Para</textarea>
            </td>
          </tr>  
          <!--
          <tr>
            <td width="29%" align="right" valign="top">Jadual</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtJadual" id="txtJadual" value="$!Para_Jadual" size="50" $RO_General />
            </td>
          </tr>  
          -->
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>#if ($action == 'view')
              <button type="button" value="Kemaskini" onclick="doAdd()"><font size = "1">Kemaskini</font></button>
#elseif ($action == 'doUpdate')
<input type="button" value="Simpan" onclick="doKemaskiniPara()"/>
<input type="button" value="Batal" onclick="doCancel()" />
#elseif ($action == 'update')
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSavePara()"/>
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
</div>

<script type="text/javascript" src="../app/pdt/enakmen.js"></script>
        
<script>
getDisableFieldDiv('mydiv','$action');
</script>

        <br />
        <fieldset>
          <legend>SENARAI PARA</legend>
          <table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="9%" scope="row"><strong>No Enakmen</strong></td>
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
              <a href="javascript:viewPara('$fail.IDPara')" style="color:#0000FF">$fail.NoEnakmen</a>
    #else
              $fail.NoEnakmen
    #end    </td>
              <td class="$row">$fail.NoPenggal</td>
              <td class="$row">$fail.NoBahagian</td>
              <td class="$row">$fail.NoBab</td>
              <td class="$row">$fail.Seksyen</td>
              <td class="$row">$fail.SubSeksyen</td>
              <td class="$row">$fail.Para</td>
              <td class="$row"><input type="hidden" id="Para_IDPara" name="Para_IDPara" value="$fail.IDPara" />
              <input type="button" value="Hapus" onclick="doHapusPara('$fail.IDPara');" /></td>
            </tr>
#end
          </table>
</fieldset>



        
        <script type="text/javascript">
		  function viewPara(ID_Para) {
			  document.${formName}.action = "$action";
			  document.${formName}.selectedTab.value = "$selectedTab";
			  document.${formName}.Para_IDPara.value = ID_Para;
			  document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen";
			  document.${formName}.submit();
		  }
        </script>
