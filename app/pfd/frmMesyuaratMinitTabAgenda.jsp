<fieldset>
  <legend><strong>AGENDA</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="29%" align="right" valign="top"><div align="left">No Agenda</div></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="70%" valign="top"><input name="Agenda_No" type="text" id="Agenda_No" value="$!Agenda_No" size="10" maxlength="2" readonly="readonly" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><div align="left">Tajuk Agenda</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="Agenda_Tajuk" type="text" id="Agenda_Tajuk" value="$!Agenda_Tajuk" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" /></td>
    </tr>
    <tr>
      <td  align="right" valign="top"><div align="left">Agenda Mesyuarat</div></td>
      <td  align="center" valign="top">:</td>
      <td  valign="top"><textarea id="Agenda_Agenda" name="Agenda_Agenda" cols="50" rows="5">$!Agenda_Agenda</textarea></td>
    </tr>
    <tr>
      <td colspan="2"><div align="left"></div></td>
      <td>
#if ($hideSaveButton != 'true')  
        <input type="button" id="cmdSimpan" name="cmdSimpan" value="Simpan" onclick="simpanMesyuaratAgenda();" />
#end
        <input type="button" id="cmdKosong" name="cmdKosong" value="Kosongkan" onclick="doChangeTab(1, 'daftarMesyuaratAgenda');" />      </td>
    </tr>
    <tr>
      <td colspan="3"><div align="left"></div></td>
    </tr>
  </table>
</fieldset>