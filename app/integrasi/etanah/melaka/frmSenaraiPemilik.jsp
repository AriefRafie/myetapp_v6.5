<fieldset>
<legend><strong>SENARAI PEMILIK</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr class="table_header">
    <td width="5%" scope="row" align="center"><strong><font color="white">Bil.</font></strong></td>
    <td><strong><font color="white">Nama</font></strong></td>
    <td ><strong><font color="white">Jenis Pengenalan</font></strong></td>
    <td ><strong><font color="white">No. Pengenalan</font></strong></td>
    <td align="center" ><strong ><font color="white">Bahagian Pemilik</font></strong></td>
  </tr>
  #set ($list = "")
  #set ($counter = 0)
  #if ($hakmilik.pihakBerkepentinganList.size() > 0)
  #foreach ($list in $hakmilik.pihakBerkepentinganList)
  #set( $counter = $counter + 1 )
  #if ($counter == '')
  #set( $row = "row1" )
  #elseif (($counter % 2) != 0)
  #set( $row = "row1" )
  #else 
  #set( $row = "row2" )
  #end
  <tr>
    <td class="$row" align="center"> $counter </td>
    <td class="$row">$!list.namaPB.toUpperCase()</td>
    <td class="$row">$!list.idJenisPengenalanPB.toUpperCase()</td>
    <td class="$row">$!list.noPengenalanPB</td>
    <td class="$row" align="center">$!list.BA / $!list.BB</td>
  </tr>
  #end  
  #else
  <tr>
    <td colspan="5" class="row"> Tiada rekod </td>
  </tr>
  #end
</table>
</fieldset>
