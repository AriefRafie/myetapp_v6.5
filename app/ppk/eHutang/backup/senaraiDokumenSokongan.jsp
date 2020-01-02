<table align="center" width="100%">
  <tr class="table_header">
    <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
    <td ><strong>Nama Dokumen</strong></td>
    <td >&nbsp;</td>
  </tr>
  #set ($list = "")
  #set ( $count = $startNumber )
  #if ($listDokumenSokongan.size() > 0)
  #foreach ($list in $listDokumenSokongan)
  #set ( $count = $count + 1 )
  #if ($count == '')
  #set( $row = "row1" )
  #elseif (($count % 2) != 0)
  #set( $row = "row1" )
  #else 
  #set( $row = "row2" )
  #end
  <tr>
    <td class="$row" align="center">$count</td>
    <td class="$row"><a href="javascript:cetakImej($!list.idDokumen)" class="style2">$!list.namaDokumen</a></td>
    <td class="$row" align="center"><span id="hapusDoc"> <a href="javascript:void()" onClick="deleteDokumen($!list.idDokumen)" ><img src="../img/delete.gif" border="0"></a> </span></td>
  </tr>
  #end
  #else
  <tr>
    <td class="row1" align="center">&nbsp;</td>
    <td class="row1" colspan="3">Tiada Rekod</td>
  </tr>
  #end
</table>
