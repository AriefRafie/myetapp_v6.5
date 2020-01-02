        
         <div id="mydiv">
        <fieldset>
        <table width="100%" border="0">
          <tr>
            <td width="29%" align="right" valign="top"> Jadual</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              <input type="text" name="txtNamaJadual" id="txtNamaJadual" 
              value="$!NamaJadual" size="50" $RO_General />            </td>
          </tr>  
	<tr>
	<td width="29%" align="right" valign="top"> Tajuk</td>
	<td width="1%" align="center" valign="top">:</td>
	<td width="70%" valign="top">
	<input type="text" name="txtTajukJadual" id="txtTajukJadual" 
	value="$!TajukJadual" size="50" $RO_General />            </td>
	</tr> 
	<tr>
	<td width="29%" align="right" valign="top"> Catatan</td>
	<td width="1%" align="center" valign="top">:</td>
        <td valign="top"><textarea name="txtCatatanJadual" cols="50" rows="5" id="txtCatatanJadual" $RO_General>$!CatatanJadual</textarea>            </td>

	</tr> 
          <!--
          <tr>
            <td align="right">Lampiran</td>
            <td>&nbsp;</td>
            <td><input id="txfMuatNaikDokumen" name="txfMuatNaikDokumen" type="file" size="40" /></td>
          </tr>
          -->
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>#if ($action == 'view')
              <button type="button" value="Kemaskini" onclick="doAdd()"><font size = "1">Kemaskini</font></button>
#elseif ($action == 'doUpdate')
<input type="button" value="Simpan" onclick="doKemaskiniJadualPara($!IDJadual)"/>
<input type="button" value="Batal" onclick="doCancel()" />
#elseif ($action == 'update')
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSaveJadualPara()"/>
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

        <fieldset>
        <br />
        <fieldset>
        <legend>SENARAI JADUAL(PARA) </legend>
        <table width="100%" align="center" cellpadding="2" cellspacing="0">
          <tr class="table_header">
            <td width="16%" scope="row"><strong>Jadual</strong></td>
            <td width="23%" scope="row"><strong>Tajuk</strong></td>
            <td width="53%" scope="row"><strong>Catatan</strong></td>
            <td width="8%" scope="row">&nbsp;</td>
          </tr>
          #set ($fail = '')
          #foreach ($fail in $List_CarianJadual)
          #if ($fail.No == '') 
          #set ($row = 'row1')
          #elseif ($fail.No % 2 != 0)
          #set ($row = 'row1')
          #else 
          #set ($row = 'row2')
          #end
  <tr>
    <td class="$row"><a href="javascript:viewJadual('$fail.IDJadual')" style="color:#0000FF">$!fail.NamaJadual</a></td>
    <td class="$row"><a href="javascript:viewJadual('$fail.IDJadual')" style="color:#0000FF">$!fail.TajukJadual</a></td>
    <td class="$row"><a href="javascript:viewJadual('$fail.IDJadual')" style="color:#0000FF">$!fail.CatatanJadual</a></td>
    <td class="$row"><input type="button" value="Hapus" onclick="doHapusJadual('$fail.IDJadual');" /></td>
  </tr>
          #end
        </table>
        </fieldset>
</fieldset>
