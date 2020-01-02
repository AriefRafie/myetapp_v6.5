
			<fieldset><legend>SENARAI HAKMILIK/RIZAB</legend>
				<table border="0" width="100%">
				    <tr>
				    	<td colspan="8">#parse("app/utils/record_paging.jsp") </td>
				    </tr>
					<tr class="table_header">
					  <td width="3%"><strong>Bil.</strong></td>
				   	  <td width="15%"><div align="left"><strong>No. Fail</strong></div></td>
				   	  <td width="12%"><div align="left"><strong>
				   	  #if ($idJenisTanah==12)
				   	  	No. Hakmilik
				   	  #elseif ($idJenisTanah==22)
				   	   	No. Warta
				   	  #else
				   	  	No. Hakmilik/ Warta
				      #end
				      </strong></div></td>
				  	  <td width="10%"><div align="left"><strong>
				  	  #if ($idJenisTanah==2)
				   	   	No. Lot
				   	  #else
				  	  	No. Lot/PT
				      #end </strong></div></td>
				   	  <td width="15%"><div align="center"><strong>Status</strong></div></td>
				  	  <td width="40%"><div align="left"><strong>Kegunaan Tanah</strong></div></td>
				  </tr>
				##Kemaskini pd 21/09/2011 oleh Mohamad Rosli (Penyelesaian kpd page number apabila Vector kosong) 
				##foreach ($senarai in $SenaraiTanah)
				#foreach ($senarai in $SenaraiFail)
				  #set( $i = $velocityCount )
				    #if ( ($i % 2) != 1 )
				       #set( $row = "row2" )
				    #else
				       #set( $row = "row1" )
				    #end
				    <tr class="$row">
				    <td width="1%">$senarai.bil</td>
				    #if($senarai.bil != '')
				      #if($senarai.jenisTanah == 'M')     	
				       <td width="18%">
				       		<!-- <a href="javascript:hakmilik_detail('$senarai.idHakmilik','$senarai.statusSah');" class="style1"> -->
				       			$!senarai.noFail
				       		<!-- </a> -->
				       	</td>
				      #elseif($senarai.jenisTanah == 'R')
				   	    <td width="18%">
				   	    	<!-- <a href="javascript:rizab_detail('$senarai.idHakmilik','$senarai.statusSah');" class="style1"> -->
				   	    		$!senarai.noFail
				       		<!-- </a> -->
				   	    </td>
				      #else
				   	    <td width="18%">$!senarai.noFail</td>
				      #end    
				    #else
				    	<td width="18%">$!senarai.noFail</td>
				    #end
				   	<td width="8%"><div align="left">$!senarai.kodJenis $!senarai.noHakmilik </div></td>
				    <td width="8%"><div align="left">$!senarai.noLot</div></td>
				    <td width="5%"><div align="center">$!senarai.statusSah</div></td>
				    <td width="23%">$!senarai.kegunaanTanah</td>
					</tr> 
				 #end
				</table>
			</fieldset>
