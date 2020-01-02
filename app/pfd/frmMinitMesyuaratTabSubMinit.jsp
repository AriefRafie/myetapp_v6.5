<fieldset>
  <div align="left">
    <legend><strong>SUB-MINIT</strong></legend>
  </div>
<table width="100%" border="0" align="center">
    <tr>
      <td width="29%" align="right" valign="top"><div align="left">Agenda Mesyuarat</div></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="79%" valign="top">$!Mesyuarat_Agenda</td>
    </tr>
    <tr>
      <td  align="right" valign="top"><div align="left">Minit Mesyuarat</div></td>
      <td  align="center" valign="top">:</td>
      <td  valign="top">$!Mesyuarat_Minit</td>
    </tr>
    <tr>
      <td align="right" valign="top"><div align="left">Sub-Minit</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><textarea id="Mesyuarat_SubMinit" name="Mesyuarat_SubMinit" cols="50" rows="5" $readonlySubMinit>$!Mesyuarat_SubMinit</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top"><div align="left">Tindakan/Makluman</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top">
        <textarea id="Mesyuarat_Tindakan" name="Mesyuarat_Tindakan" cols="50" rows="5" $readonlySubMinit>$!Mesyuarat_Tindakan</textarea><br />
        <input type="checkbox" id="Mesyuarat_Makluman" name="Mesyuarat_Makluman" value="1" onclick="javascript:checkMakluman();" $Mesyuarat_Makluman $readonlySubMinit />Makluman
      </td>
    </tr>
    <tr>
      <td colspan="3">
#if ($action == 'subminit')
	#if ($mode == 'view' && $ID_SUBMINIT != '')
        <input type="button" id="cmdSubMinitKemaskini" name="cmdSubMinitKemaskini" value="Kemaskini" onclick="subMinitKemaskini();" />
        <input type="button" id="cmdSubMinitKembali" name="cmdSubMinitKembali" value="Kembali" onclick="subMinitKembali();" />
    #elseif (($mode == 'update' || $mode == 'changeAgenda'))
        <input type="button" id="cmdSubMinitSimpan" name="cmdSubMinitSimpan" value="Simpan" onclick="subMinitSimpan();" />
        <input type="button" id="cmdSubMinitHapus" name="cmdSubMinitHapus" value="Hapus" onclick="subMinitHapus();" />
        <input type="button" id="cmdSubMinitKembali" name="cmdSubMinitKembali" value="Kembali" onclick="subMinitKembali();" />
    #else
        <input type="button" id="cmdSubMinitKembali" name="cmdSubMinitKembali" value="Kembali" onclick="kembaliCarian();" />
    #end
#end
      </td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
  </table>
</fieldset>