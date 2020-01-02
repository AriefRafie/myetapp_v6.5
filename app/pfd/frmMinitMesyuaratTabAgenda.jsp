<fieldset>
  <legend><strong>AGENDA</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="29%" align="right" valign="top"><div align="left">Agenda Mesyuarat</div></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="79%" valign="top"><textarea id="Mesyuarat_Agenda" name="Mesyuarat_Agenda" cols="50" rows="5" $readonlyAgenda>$!Mesyuarat_Agenda</textarea></td>
    </tr>
    <tr>
      <td colspan="3">
        <div align="center">#if ($action == 'agenda')
          #if ($mode == 'view' && $ID_AGENDA != '')
          <input type="button" id="cmdAgendaKemaskini" name="cmdAgendaKemaskini" value="Kemaskini" onclick="agendaKemaskini();" />
          <input type="button" id="cmdAgendaKembali" name="cmdAgendaKembali" value="Kembali" onclick="agendaKembali();" />
          #elseif ($mode == 'update')
          <input type="button" id="cmdAgendaSimpan" name="cmdAgendaSimpan" value="Simpan" onclick="agendaSimpan();" />
          <input type="button" id="cmdAgendaHapus" name="cmdAgendaHapus" value="Hapus" onclick="agendaHapus();" />
          <input type="button" id="cmdAgendaKembali" name="cmdAgendaKembali" value="Kembali" onclick="agendaKembali();" />
          #else
          <input type="button" id="cmdAgendaKembali" name="cmdAgendaKembali" value="Kembali" onclick="kembaliCarian();" />
          #end
          #end
      </div></td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
  </table>
</fieldset>