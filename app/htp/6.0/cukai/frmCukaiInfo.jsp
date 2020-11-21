	<table border="0" cellpadding="0" cellspacing="0" width="100%">
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
   	
   	##if(($!statusPeringkatBayar) && ($cukaiUtama != 0))	    					
    <!--	<tr>
        	<td height="25"  align="right">Tahun Cukai : &nbsp;&nbsp;</td>
        	<td>$tahun_cukai</td>
      	</tr>	-->
    ##else  
    	<tr>
        	<td height="25"  align="right">Tahun Cukai : &nbsp;&nbsp;</td>
        	<td> 
			#set ( $selected = "" )
			<select class="autoselect" name="Form_tahun" onchange="doChangeYear('$negeri','$idNegeri','$idPermohonan')">
		   		<option value="0" $selected>Sila Pilih</option>
		   		#set ( $ints = $!tahuncukai + 2 )
		   		#foreach ( $y in [2007..$ints] )
		   		#if ( $y == $tahun_cukai )
		   			#set ( $selected = "selected" )
		   		#else
		   			#set ( $selected = "" )
		   		#end
		   		<option value="$y" $selected>$y</option>
		   		#end
			</select>
			</td>
		</tr>  
    ##end  
      <tr>
        <td height="25"  align="right">Peringkat Bayar : &nbsp;&nbsp;</td>
        	#set ( $statusPeringkat1 = "Peringkat Negeri" ) 
  			#set ( $statusPeringkat2 = "Peringkat Negeri" ) 
			#set ( $statusPeringkat3 = "Peringkat Belanjawan" ) 
  			#set ( $statusDisplay = "" ) 
  			#if( $peringkat_bayaran == "1" )
  				#set ($statusDisplay = $statusPeringkat1)
  			#elseif( $peringkat_bayaran == "2" )
    			#set ($statusDisplay = $statusPeringkat2)
    		#elseif( $peringkat_bayaran == "3" )
		    	#set ($statusDisplay = $statusPeringkat3)	
  			#else
    			#set ($statusDisplay = $statusDisplay)
            #end
        <td>$soCukai</td>
        <input type="hidden" name="peringkat_bayaran" value="$peringkat_bayaran" >
        <input type="hidden" name="socbayaran" value="$idbayaran" >
      </tr>
  </table>
