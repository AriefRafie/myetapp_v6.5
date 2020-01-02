<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr class="table_header">
    <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
    <td width="30%"><strong>Nama Pegawai</strong></td>
    <td width="40%"><strong>KJP/Agensi/Syarikat</strong></td>
    <td width="20%"><strong>Jawatan</strong></td>
    <td width="5%" align="center"><strong>Ketua</strong></td>
  </tr>
  
  #foreach ($i in [1..10])
  #if ($i == '')
        #set( $row = "row1" )
        #elseif (($i % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row1" )
  #end 
  <tr>
    <td class="$row" align="center">$i</td>
    <td class="$row"><input name="txtNama" type="text" id="txtNama" size="30" onBlur="this.value=this.value.toUpperCase();"/>
    </td>
    <td class="$row"><input name="txtAgensi" type="text" id="txtAgensi" size="60" onBlur="this.value=this.value.toUpperCase();"/></td>
    <td class="$row"><input name="txtJawatan" type="text" id="txtJawatan" size="30" onBlur="this.value=this.value.toUpperCase();"/></td>
    <td class="$row" align="center"><input type="checkbox" value="$i" name="flagPengerusi" onclick="doUpdateCheck('$i')"/></td>
  </tr>
#end

  <tr>
    <td></td>
  </tr>
  <tr>
    <td colspan="6" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  <tr>
    <td colspan="6" align="center"><input name="cmdSimpanKehadiran" type="button" value="Simpan" onClick="simpanKehadiran()" />
      <input name="cmdBatalKehadiran" type="button" value="Batal" onClick="batalKehadiran()" />
    </td>
  </tr>
</table>
