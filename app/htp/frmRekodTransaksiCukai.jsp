#if ( $!portal_role_.contains('NegeriS'))
<link rel="stylesheet" type="text/css" href="../../css/eTapp_SabahSarawak.css" />
#else
<link rel="stylesheet" type="text/css" href="../../css/eTapp_HTP.css" />
#end
<table width="100%" border="0">
  <tr>	
  	 <td>

<fieldset><legend>SENARAI TRANSAKSI CUKAI</legend>
<table width="100%" border="0">
  <tr class="table_header">
    <td width="1%">Bil.</td>
    <td width="23%"><div align="center">Cukai Semasa <br>(RM)</div></td>
    <td width="50%"><div align="center">Tarikh Transaksi</div></td>
  </tr>
  #foreach ($senarai in $SenaraiTransaksi)
  #set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
       #set( $row = "row2" )
    #else
       #set( $row = "row1" )
    #end
  <tr>
    <td class="$row" width="1%">$senarai.bil</td>
    <td class="$row" width="23%"><div align="right">$senarai.cukaiTerkini</div></td>
    <td class="$row" width="50%"><div align="center">$senarai.tarikhTransaksi</div></td></td>
  </tr>
  #end
</table>
</fieldset>

  	 </td>
  </tr>
</table>