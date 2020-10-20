<table border="0" width="100%"  class="nav">
  <tr >
    <td valign="top" ><strong>Senarai Hakmilik Permohonan</strong></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
  <tr class="table_header">
    <td width="5%" scope="row"  align="center"><strong><font color="white">Bil.</font></strong></td>
    <td ><strong><font color="white">ID Hakmilik</font></strong></td>
    <td ><strong><font color="white">No. Lot</font></strong></td>
    <td align="right" ><strong><font color="white">Luas Asal</font></strong></td>
    <td align="right" ><strong ><font color="white">Luas Ambil</font></strong></td>
    #if ($!jenisSkrin == "WartaS4" || $!jenisSkrin == "WartaS8" || $!jenisSkrin == "TarikBalik")
    <td align="center" ><b><font color="white">Tarikh Warta</font></b></td>
    <td align="center" ><b><font color="white">No Warta</font></b></td>
    #elseif ($!jenisSkrin == "BorangK")
    <td align="center" ><b><font color="white">Tarikh Borang K</font></b></td>
    #end
    #if ($!jenisSkrin == "TarikBalik")
    <td ><b><font color="white">Sebab Penarikan Balik</font></b></td>
    <td align="center" ><b><font color="white">Jenis Pengambilan</font></b></td>
    #end
    <td ><b><font color="white">Catatan</font></b></td>
  </tr>
  #set ($list = "")
  #set ($counter = 0)
  #if ($listHakmilik.size() > 0)
  #foreach ($list in $listHakmilik)
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
    #if ($!jenisSkrin == "WartaS4" || $!jenisSkrin == "WartaS8" || $!jenisSkrin == "TarikBalik")
    <td class="$row" align="center">$!list.tarikhWarta</td>
    <td class="$row" align="center">$!list.noWarta</td>
    #elseif ($!jenisSkrin == "BorangK")
    <td class="$row" align="center">$!list.tarikhBorangK</td>
    #end
    #if ($!jenisSkrin == "TarikBalik")
    <td class="$row" align="center">$!list.sebabPenarikanBalik</td>
    <td class="$row" align="center">$!list.statusAmbil</td>
    #end 
    <td class="$row" align="center"><font color="red"><b>$!list.catatanHakmilik</b></font></td>
    </tr>
  #end  
  #else
  <tr>
    <td colspan="10" class="row"> Tiada rekod </td>
  </tr>
  #end
</table>
