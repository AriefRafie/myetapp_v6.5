<fieldset>
  <legend><strong>KEHADIRAN</strong></legend>
  <table width="100%"  align="center">
    <tr class="table_header">
      <td width="5%" style="text-align:center" valign="top">No</td>
      <td width="35%" valign="top">Nama Ahli</td>
      <td width="10%" style="text-align:center" valign="top">Hadir</td>
      <td width="10%" style="text-align:center" valign="top">Tidak Hadir</td>
      <td width="35%" valign="top">Wakil</td>
    </tr>
    <tr>
#set ($fail = '')
#foreach ($fail in $List_CarianAhliMesyuarat)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
    #if ($fail.No != '')
    <tr>
      <td class="$row" style="text-align:center">$fail.No</td>
      <td class="$row">$fail.NamaAhli</td>
      <td style="text-align:center" class="$row"><input type="radio" id="Hadir_$fail.IDAhli" name="Hadir_$fail.IDAhli" value="1" $fail.CheckHadir /></td>
      <td style="text-align:center" class="$row"><input type="radio" id="Hadir_$fail.IDAhli" name="Hadir_$fail.IDAhli" value="0" $fail.CheckTidakHadir /></td>
      <td class="$row"><input name="Wakil_$fail.IDAhli" type="text" id="Wakil_$fail.IDAhli" size="30" maxlength="255" value="$fail.Wakil" onkeyup="this.value=this.value.toUpperCase();" /></td>
    </tr>
	#end
#end
    <tr>
      <td colspan="5" style="text-align:center">
#if ($hideSaveButton != 'true')  
        <input type="button" id="cmdSimpan" name="cmdSimpan" value="Simpan" onclick="simpanKehadiran();" />
#end
      </td>
    </tr>
  </table>
</fieldset>