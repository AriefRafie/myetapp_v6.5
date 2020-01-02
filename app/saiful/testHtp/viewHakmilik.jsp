<table>
#if ($Listhakmilik.size() > 0)
      #foreach ($list in $Listhakmilik)
       #set( $i = $velocityCount )
       #if ( ($i % 2) != 1 ) 
       #set( $row = "row2" ) 
       #else 
       #set( $row = "row1" ) 
       #end
  <tr class="$row">
    <td  width="1%">$list.bil</td>
        <td><a href="javascript:paparHakmilik('$list.idHakmilikurusan')"><font color="blue">$list.noLot</font></a></td>
        <td>$list.noHakmilik</td>
        <td>$list.negeri</td>
        <td>$list.daerah</td>
        <td>$list.mukim</td>
        <td>$list.idHakmilikurusan</td>
      </tr>
      #end
     #end
     </table>
     
     $button.command("transferHakmilik").param("idPermohonan=$idPermohonan").text("SAHKAN & TRANSFER ($idPermohonan)")