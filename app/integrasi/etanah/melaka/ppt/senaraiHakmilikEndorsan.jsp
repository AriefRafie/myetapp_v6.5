<table border="0" width="100%"  class="nav">
  <tr >
    <td valign="top" ><strong>Senarai Hakmilik Endorsan</strong></td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
  <tr class="table_header">
    <td width="5%" scope="row"  align="center"><strong><font color="white">Bil.</font></strong></td>
    <td ><strong><font color="white">ID Hakmilik</font></strong></td>
    <td ><strong><font color="white">No. Lot</font></strong></td>
    <td align="right" ><strong><font color="white">Luas Asal</font></strong></td>
    <td align="right" ><strong ><font color="white">Luas Ambil</font></strong></td>
    <td ><strong><font color="white">ID Hakmilik Sambungan</font></strong></td>
    <td align="right" ><strong ><font color="white">Luas Sambungan</font></strong></td>
    <td align="center"><strong><font color="white">No. Perserahan</font></strong></td>
    <td align="center"><strong><font color="white">Tarikh Endorsan</font></strong></td>
    <td align="center"><strong><font color="white">Masa Endorsan</font></strong></td>
    <td width="5%" align="center"><strong><font color="white">Status Endorsan</font></strong></td>
  </tr>
  #set ($list = "")
  #set ($counter = 0)
  #if ($listHakmilikEndorsan.size() > 0)
  #foreach ($list in $listHakmilikEndorsan)
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
    <td class="$row">$!list.idHakmilik</td>
    <td class="$row">$!list.kodLot $!list.noLot</td>
    <td class="$row" align="right">$!list.luasAsal $!list.kodLuasAsal</td>
    <td class="$row" align="right">$!list.luasAmbil $!list.kodLuasAmbil</td>
    <td class="$row">$!list.idHakmilikSambungan</td>
    <td class="$row" align="right">$!list.luasSambungan $!list.kodLuasSambungan</td>
    <td class="$row" align="center">$!list.noPerserahan</td>
    <td class="$row" align="center">$!list.tarikhEndorsan</td>
    <td class="$row" align="center">$!list.masaEndorsan</td>
    #if ($!list.statusEndorsan == 'Y')
    	<td class="$row" align="center"><strong>LULUS</strong></td>
    #elseif ($!list.statusEndorsan == 'T')
    	<td class="$row" align="center"><strong>TOLAK</strong></td>
    #else
    	<td class="$row" align="center"><strong>BATAL</strong></td>
    #end
    </tr>
  #end  
  #else
  <tr>
    <td colspan="11" class="row"> Tiada rekod </td>
  </tr>
  #end
</table>
