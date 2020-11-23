<table border="0" width="100%"  class="nav">
  <tr >
    <td valign="top" ><strong>Senarai Dokumen Endorsan</strong> </td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
  <tr class="table_header">
    <td width="5%" scope="row"  align="center"><strong><font color="white">Bil.</font></strong></td>
    <td ><strong><font color="white">Jenis Dokumen</font></strong></td>
  </tr>
  #set ($listD = "")
  #set ($counter = 0)
  #if ($listDokumenEndorsan.size() > 0)
  #foreach ($listD in $listDokumenEndorsan)
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
    <td class="$row"><a href="javascript:paparDokumenEndorsan($!listD.idDokumenEndorsan)" class="style2"><strong>$!listD.jenisDokumen</strong></a></td>
  </tr>
  #end  
  #else
  <tr>
    <td colspan="2" class="row"> Tiada rekod </td>
  </tr>
  #end
</table>
