
	<fieldset>
	<legend><strong>SENARAI BORANG K</strong></legend>
			
    		#if($saiz_borangk > 10)
                <div style="width:100%;height:220;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  				<tr>
  					<td colspan="9">
  						
  						<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Tambah" onclick="viewBorangK()">
  				
  			
  					</td>
  				
  				</tr>
        		<tr class="table_header">
        			<td align="center"><b>Bil.</b></td>
        			<td><b>Tarikh Borang K</b></td>
        			<td><b>Tarikh Endorsan Borang K</b></td>
	       			<td><b>No.Lot</b></td> 
        			
        		</tr>
        		
  				#set ( $cnt = 0 )			
        		#if($saiz_borangk!=0)
                    #foreach($listK in $listBorangK)
     				#set ( $cnt = $cnt + 1 )
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!cnt.</td>
                   <td class="$row">$!listK.tarikhBorangK</td>
                   <td class="$row">$!listK.tarikhEndosan</td>
                   <td class="$row">$!listK.noLot</td>
                
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_borangk > 10)
                </div>
            #end
	
	</fieldset> 
 
<p id="Submit">&nbsp;</p>
