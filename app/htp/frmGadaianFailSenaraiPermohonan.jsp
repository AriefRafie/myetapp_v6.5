<fieldset>
<legend>Permohonan Untuk Melepas Gadaian</legend>
<table border="0" cellpadding="2" cellspacing="1" width="100%">
  <tbody>
    <tr class="table_header">
      <td width="6%"><b>No</b></td>
      <td width="16%"><b>Id Permohonan</b></td>
      <td width="30%"><b>Nama</b></td>
      <td width="18%"><b>No Fail</b></td>
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
    <td class="$row"><a href="javascript:viewDetails('$fail.idFail', '$fail.idPermohonan')" >$fail.idPermohonan</a></td>
    <td class="$row">$fail.nama</td>
    <td class="$row">$fail.noFailLain</td>

  </tr>
  #end
  #if ($count == 0)
  <tr>
    <td colspan="4" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  </tr>
  #end
  </tbody>
  
</table>
</fieldset>

