<fieldset>
<legend>Fail Seksyen Untuk Melepas Gadaian</legend>
<table border="0" cellpadding="2" cellspacing="1" width="100%">
  <tbody>
    <tr class="table_header">
      <td width="5%"><b>No</b></td>
      <td width="20%"><b>No Fail</b></td>
      <td width="17%"><b>Negeri</b></td>
      <td width="35%"><b>Nama Fail</b></td>
      <td width="23%"><b>Urusan</b></td>
      <td width="23%"><strong>Melepas Gadai</strong></td>
    </tr>
  #set ($count = 0)
  #foreach ( $fail in $Senaraifail )
  #set ($count = $count+1)
  #set( $i = $velocityCount )
  #if ( ($i % 2) != 1 )
  #set( $row = "row2" )
  #else
  #set( $row = "row1" )
  #end
  <tr>
    <td class="$row">$fail.bil</td>
    <td class="$row">$fail.noFail</td>
    <td class="$row">$fail.negeri</td>
    <td class="$row">$fail.tajuk</td>
    <td class="$row">$fail.urusan</td>
    <td class="$row" align="center"><input type="radio" name="PelepasanGadai" id="PelepasanGadai" value="$fail.idFail"></td>
  </tr>
  #end
  #if ($count == 0)
  <tr>
    <td colspan="6" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  </tr>
  #end
  </tbody>
  
</table>
<p>&nbsp;</p>
<table width="100%" border="0">
  <tr align="center">
    <td><input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGSFG_LepasGadai()" />
      &nbsp;&nbsp;
      <input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePeguam onclick="javascript:fGSFG_Batal()" /></td>
  </tr>
  <tr>
    <td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
</table>
<p>&nbsp;</p>
</fieldset>

<input type="hidden" name="idFail" value="$idFail" />
