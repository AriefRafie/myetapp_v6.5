#if ( $!portal_role_.contains('NegeriS'))
<link rel="stylesheet" type="text/css" href="../../css/eTapp_SabahSarawak.css" />
#else
<link rel="stylesheet" type="text/css" href="../../css/eTapp_HTP.css" />
#end
<table style="width:100%">
  <tr>	
  	 <td>
		<fieldset><legend>SENARAI TRANSAKSI CUKAI</legend>
		<table style="width:100%" >
		  <tr class="table_header">
		    <td width="3%">Bil.</td>
		    <td width="20%"><div align="center">Cukai Semasa <br>(RM)</div></td>
		    <td width="10%"><div align="center">Tarikh Transaksi</div></td>
		    <td width="67%"><div align="center">Pengguna Akhir Kemaskini</div></td>
		  </tr>
#foreach ($senarai in $SenaraiTransaksi)
	#set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
       #set( $row = "row2" )
    #else
       #set( $row = "row1" )
    #end
		<tr class="$row" >
		    <td>$senarai.bil</td>
		    <td><div align="right">$!senarai.cukaiTerkini</div></td>
		    <td><div align="center">$!senarai.tarikhTransaksi</div></td>
		    <td><div align="center">$!senarai.pengguna</div></td>
		</tr>
#end
		</table>
		</fieldset>
  	 </td>
  </tr>
</table>