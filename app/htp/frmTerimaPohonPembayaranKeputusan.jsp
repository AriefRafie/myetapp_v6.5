#foreach($keputusan in $keputusanInfo)
	#set($!keputusan = $keputusan.no_rujukan_keputusan)

<table width="100%" border="0">
  <tr>
    <td width="11%">No. Rujukan PTD</td>
    <td width="1%"><strong>:</strong></td>
    <td width="24%"><input type="text" name="txtNoRujukPTD" id="txtNoRujukPTD" size="30" value="$!keputusan" onkeyup="this.value=this.value.toUpperCase();"></td>
    <td width="1%">&nbsp;</td>
    <td width="25%">Status Keputusan</td>
    <td width="1%"><strong>:</strong></td>
    <td width="37%"><select name="PilihKeputusan" id="PilihKeputusan">
      <option value="TIADA">SILA PILIH KEPUTUSAN</option>
      <option value="DILULUSKAN">DILULUSKAN</option>
      <option value="TIDAK DILULUSKAN">TIDAK DILULUSKAN</option>
      <option value="BELUM ADA KEPUTUSAN">BELUM ADA KEPUTUSAN</option>
    </select>
      $!keputusaninfo.status </td>
  </tr>
  <tr>
    <td>Tarikh Keputusan</td>
    <td><strong>:</strong></td>
    <td><input type="text" name="txtTarikhKeputusan" id="txtTarikhKeputusan" size="30" value="$!keputusaninfo.tarikhkeputusan">
      <a href="javascript:displayDatePicker('txtTarikhKeputusan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
    <td>&nbsp;</td>
    <td>Catatan</td>
    <td><strong>:</strong></td>
    <td rowspan="3"><textarea name="txtCatatan" id="txtCatatan" cols="45" rows="5" onkeyup="this.value=this.value.toUpperCase();" >$!keputusaninfo.ulasan</textarea></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><input class="stylobutton" type="button" name="SimpanKeputusan" id="SimpanKeputusan" value="Simpan" onclick="SimpanKeputusanPTD()"></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
#end 