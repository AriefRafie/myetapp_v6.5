<fieldset>
<legend>Semakkan Status Permohonan</legend>
<table width="90%">
	<tr>
		<td align="center">
				<table>
					<tr>
						<td>
							MyID/ No. KP Si Mati:
						
						</td>
						
						<td>
							<input type='text' name="icNo">
						
						</td>
						<td>
							<input type="button" value="Semak" onclick="cariICSiMati()">
						
						</td>
					
					</tr>
				
				</table>
		
		</td>
	
	
	</tr>

	<tr>
		<td>
			<table width="100%" cellspacing="2" cellpadding="1" border="0">
				  <tr class="table_header">
				  	<td width="2%" align="center">No</td>
				  	<td width="9%"align="center">Tarikh Mohon</td>	
					<td width="30%" align="center">No Fail</td>
					<td width="4%" align="center">Seksyen</td>
				  	<td width="15%">Status Pergerakan Fail</td>
				  </tr>
				  #if($statusList.size() > 0)
					#foreach ($senarai in $statusList )
					#set( $counter = $velocityCount )
					#if ( ($counter % 2) == 0 )
						#set( $row = "row2" )
					#else
						#set( $row = "row1" )
					#end
				<tr>
					  <td class="$row" align="center">
					  #set ($cnt = ($page - 1) * $itemsPerPage + $counter )
					  $!cnt</td>
					  <td class="$row" align="center">$!senarai.tarikhMohon</td>
					  
					  <td class="$row" align="center">
					   $!senarai.noFail
					  </td>
					  
					  <td class="$row" align="center">$senarai.seksyen</td>
					  <td class="$row">$senarai.status</td>
				</tr>
				#end
				#else
					<tr>
						<td colspan="6">Rekod Tidak Dijumpai</td>
					</tr>
					
					
				
				#end
			</table>
		</td>
		
	
	</tr>
	<tr>
		<td colspan="4" align="center"><input type="button" value="Kembali" onclick="menuUtama()"></td>
	</tr>
	
</table>
</fieldset>