<tr >
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Peranan / Role Terlibat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top">
				
				
				#if($listRoleByModule.size()>0)	
					<table width="100%" border="0"  >
					<tr>
								<td align="right" width="1%"  valign="top" >
								</td>
								<td align="center"  width="1%"  valign="top" ></td>
								<td align="left"  width="94%"  valign="top" ></td>
								</tr>
					    		
							#foreach($ar in $listRoleByModule)
							
								#if($ar.LAYER == "1")
								
										<tr>
										<td colspan="3"><b>$ar.KOD</b></td>
										</tr>
								#end
								#if($ar.LAYER == "2")
								
										<tr>
										<td align="right"   valign="top" >
										$ar.NEWBIL.
										</td>
										<td align="center"   valign="top" ></td>
										<td align="left"   valign="top" >$ar.KETERANGAN</td>
										</tr>
								#end							
							#end
						
						<tr>
						<td colspan="3">
						
						<input 
type="button" id="EDITADDROLE$internalType$USER_ID" 
name="EDITADDROLE$internalType$USER_ID" 
onClick="doDivAjaxCall$formname('div_DisplayRoleModule$MODULE_GROUP$MODULE_ID_TRIM','edit_AddRole','MODULE_ID_TRIM=$MODULE_ID_TRIM&MODULE_ID_ASAL=$MODULE_ID_ASAL&MODULE_GROUP=$MODULE_GROUP');" 
value="Kemaskini / Tambah Role" >
						
						</td>
						</tr>			
					</table>
					
					
					#else
						<table width="100%" border="0"  >
						<tr>
						<td colspan="3">-<input 
type="button" id="EDITADDROLE$internalType$USER_ID" 
name="EDITADDROLE$internalType$USER_ID" 
onClick="doDivAjaxCall$formname('div_DisplayRoleModule$MODULE_GROUP$MODULE_ID_TRIM','edit_AddRole','MODULE_ID_TRIM=$MODULE_ID_TRIM&MODULE_ID_ASAL=$MODULE_ID_ASAL&MODULE_GROUP=$MODULE_GROUP');" 
value="Kemaskini / Tambah Role" > </td>
						</tr>
						</table>
					
					#end
					</td>
</tr>




