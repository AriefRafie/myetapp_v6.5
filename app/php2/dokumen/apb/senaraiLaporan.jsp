<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI DOKUMEN</b></legend>
      <table align="center" width="100%">
         <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td ><strong>Nama Dokumen</strong></td>
        </tr>
        #set ($list = "")
        #set ( $count = 0 )
        #if ($listLaporan.size() > 0)
        #foreach ($list in $listLaporan)
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
          <td class="$row"><a href="javascript:paparLaporan('$!list.idLaporan')" class="style2">$!list.namaLaporan.toUpperCase()</a></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" colspan="1">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
