
    <table width="100%" border="0" cellpadding="2" cellspacing="2">
      <tr>
        <td width="30%"><div align="right">Bil. Mesyuarat</div></td>
        <td width="70%">
          <input type="text" name="txtbilmesyuarat" id="txtbilmesyuarat">
        </td>
      </tr>
      <tr>
        <td><div align="right">Pengatur</div></td>
        <td>
          <input type="text" name="txtpengatur" id="txtpengatur">
        </td>
      </tr>
      <tr>
        <td><div align="right">Tarikh</div></td>
        <td>
          <input type="text" name="txttarikh" id="txttarikh">
         
        <a href="javascript:displayDatePicker('txttarikh',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
      </tr>
      <tr>
        <td><div align="right">Masa</div></td>
        <td>
          <input type="text" name="txtmasa" id="txtmasa">
        </td>
      </tr>
      <tr>
        <td><div align="right">Tempat</div></td>
        <td>
          <input type="text" name="txttempat1" id="txttempat1">
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>
          <input type="text" name="txtteampat2" id="txtteampat2">
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>
          <input type="text" name="txtteampat3" id="txtteampat3">
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>
          <input type="text" name="txttempat4" id="txttempat4">
        </td>
      </tr>
      <tr>
        <td><div align="right">Perbincangan</div></td>
        <td>
          <textarea name="txtperbincngan" id="txtperbincngan" cols="25" rows="3"></textarea>
        </td>
      </tr>
      <tr>
        <td><div align="right">Tindakan</div></td>
        <td>
          <textarea name="txttindakan" id="txttindakan" cols="25" rows="3"></textarea>
        </td>
      </tr>
      <tr>
        <td><div align="right">Keputusan</div></td>
        <td>&nbsp;</td>
      </tr>
    </table>


<div align="center">
  <input type="button" name="txtkemaskini" id="txtkemaskini" value="Kemaskini" /> 
  <input type="button" name="txthapus" id="txthapus" value="Hapus" />
  <input type="button" name="txtsimpan" id="txtsimpan" value="Simpan" />
  <input type="button" name="txtbatal" id="txtbatal" value="Batal" /> 
  <input type="button" name="txtkembali" id="txtkembali" value="Kembali" onclick="backToPageMaklumatSykt('$id_permohonan')"/>
  <input type="button" name="txtseterusnya" id="txtseterusnya" value="Seterusnya" onclick="screenPindahMilik('$id_permohonan')"/>
</div>

<input type="text" name="id_permohonan" value="$id_permohonan"/>

