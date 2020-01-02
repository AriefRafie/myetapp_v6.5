#if($listDaerahJagaanByIdPejabat.size()>0)
<tr id="div_displayDaerahJagaan$internalType$USER_ID">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daerah Urusan / Jagaan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  >
					<table width="100%" border="0" >
					#if($listDaerahJagaanByIdPejabat.size()>0)
						#foreach($dj in $listDaerahJagaanByIdPejabat)
							<tr>
							<td align="right"  width="5%" valign="top" >$dj.BIL.</td>
							<td align="right"  width="1%"  valign="top" ></td>
							<td align="left" width="94%"  valign="top" >$dj.NAMA_DAERAH</td>
							</tr>
						#end
					#end
					</table>
				</td>
</tr>
#else
<tr id="div_displayDaerahJagaan$internalType$USER_ID" style="display:none">
<td valign="top" colspan="4" >	
</td>
</tr>
#end

