
<script>
if( $jquery('#'+'div_rowPengguna$internalType$USER_ID').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPengguna$internalType$USER_ID').offset().top);
}
else
{
	if( $jquery('#'+'div_viewPengguna$internalType$USER_ID').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewPengguna$internalType$USER_ID').offset().top);
	}
	
}
</script>

#if($listAdditionalRoleByUserLogin.size()>0)
<tr id="div_displayaddrole$internalType$USER_ID">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				
				Peranan Tambahan (Pilihan)
				</td>
				<td valign="top" >
				:
				</td>
				<td>
				<div id="div_listaddrole$internalType$USER_ID">
				<table width="100%" border="0"  >
					<tr>
								<td align="right" width="1%"  valign="top" >
								</td>
								<td align="center"  width="1%"  valign="top" ></td>
								<td align="left"  width="94%"  valign="top" ></td>
								</tr>
						#foreach($ar in $listAdditionalRoleByUserLogin)
						
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
						<tr >
						<td></td><td></td>
						<td >
						<input 
type="button" id="EDITADDROLE$internalType$USER_ID" 
name="EDITADDROLE$internalType$USER_ID" 
onClick="doDivAjaxCall$formname('div_listaddrole$internalType$USER_ID','edit_AddRole','USER_ID=$USER_ID&internalType=$internalType&USER_LOGIN=$USER_LOGIN');" 
value="Kemaskini / Tambah Role" >  
						</td>
						</tr>					
					</table>
				</div>
				</td>
</tr>
<script>	
	document.getElementById("div_displayaddrole$internalType$USER_ID").style.display="";	
</script>
#else
<tr id="div_displayaddrole$internalType$USER_ID" >
<td valign="top" >				
				</td>			
				<td valign="top" >
				Peranan Tambahan (Pilihan)
				</td>
				<td valign="top" >
				:
				</td>
				<td>
				<div id="div_listaddrole$internalType$USER_ID">
					<table width="100%" border="0" >
												
						<tr >
						<td >
						- &nbsp;
						<input 
type="button" id="EDITADDROLE$internalType$USER_ID" 
name="EDITADDROLE$internalType$USER_ID" 
onClick="doDivAjaxCall$formname('div_listaddrole$internalType$USER_ID','edit_AddRole','USER_ID=$USER_ID&internalType=$internalType&USER_LOGIN=$USER_LOGIN');" 
value="Kemaskini / Tambah Role" >  
						</td>
						</tr>					
					</table>
				</div>
				</td>
</tr>
#end