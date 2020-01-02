<fieldset><legend>SENARAI CUKAI MENGIKUT TAHUN</legend>
<table width="100%" border="0">
  <tr class="table_header">
    <td>Tahun </td>
    <td>Bilangan File </td>
  </tr>
   #set ( $cnt = 0 )	
  #foreach ($tahun1 in $ListTahun)
  #set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
  <tr>
    <td class="$row"><a href="javascript:SenaraiTahun('$tahun1.tahun')" class="style1">$tahun1.tahun</a></td>
    <td class="$row"> $tahun1.counttahun</td>
  </tr>
  #end
</table>
</fieldset>