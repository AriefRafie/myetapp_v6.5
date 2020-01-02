        
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
