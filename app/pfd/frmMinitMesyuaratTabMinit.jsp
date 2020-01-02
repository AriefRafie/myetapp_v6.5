<fieldset>
  

<legend><strong>MINIT</strong></legend>
<table width="100%" border="0" align="center">
    <tr>
      <td width="29%" align="right" valign="top"><div align="left">Agenda Mesyuarat</div></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="70%" valign="top">$!Mesyuarat_Agenda</td>
    </tr>
    <tr>
      <td  align="right" valign="top"><div align="left">Minit</div></td>
      <td  align="center" valign="top">:</td>
      <td  valign="top"><textarea id="Mesyuarat_Minit" name="Mesyuarat_Minit" cols="50" rows="5" $readonlyMinit>$!Mesyuarat_Minit</textarea></td>
    </tr>
    <tr>
      <td colspan="3">
        <div align="center">#if ($action == 'minit')
          #if ($mode == 'view' && $ID_MINIT != '')
          <input type="button" id="cmdMinitKemaskini" name="cmdMinitKemaskini" value="Kemaskini" onclick="minitKemaskini();" />
          <input type="button" id="cmdMinitKembali" name="cmdMinitKembali" value="Kembali" onclick="minitKembali();" />
          #elseif ($mode == 'update')
          <input type="button" id="cmdMinitSimpan" name="cmdMinitSimpan" value="Simpan" onclick="minitSimpan();" />
          <input type="button" id="cmdMinitHapus" name="cmdMinitHapus" value="Hapus" onclick="minitHapus();" />
          <input type="button" id="cmdMinitKembali" name="cmdMinitKembali" value="Kembali" onclick="minitKembali();" />
          #else
          <input type="button" id="cmdMinitKembali" name="cmdMinitKembali" value="Kembali" onclick="kembaliCarian();" />
          #end
          #end
      </div></td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
  </table>
</fieldset>