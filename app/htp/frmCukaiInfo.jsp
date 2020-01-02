   <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td width="29%" height="25" align="right">No. Fail Cukai : &nbsp;&nbsp;</td>
        <td width="71%">$info.fail</td>
      </tr>
      		#if( $command1 == "cukaiBayaran" )
      <tr>
        <td height="25"  align="right">Negeri : &nbsp;&nbsp;</td>
        <td>$negeri</td>
      </tr>		
            #end
      <tr>
        <td height="25"  align="right">Tahun Cukai : &nbsp;&nbsp;</td>
        <td>$tahun_cukai</td>
      </tr>
      <tr>
        <td height="25"  align="right">Peringkat Bayar : &nbsp;&nbsp;</td>
        	#set ( $statusPeringkat1 = "Peringkat Negeri" ) 
  			#set ( $statusPeringkat2 = "Peringkat Daerah" ) 
  			#set ( $statusDisplay = "" ) 
  			#if( $peringkat_bayaran == "1" )
  				#set ($statusDisplay = $statusPeringkat1)
  			#elseif( $peringkat_bayaran == "2" )
    			#set ($statusDisplay = $statusPeringkat2)
  			#else
    			#set ($statusDisplay = $statusDisplay)
            #end
        <td>$statusDisplay</td>
        <input type="hidden" name="peringkat_bayaran" value="$peringkat_bayaran" >
        <input type="hidden" name="socbayaran" value="$idbayaran" >
      </tr>
    </tbody>
  </table>
