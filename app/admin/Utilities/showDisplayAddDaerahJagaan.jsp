
<script>
if( $jquery('#'+'div_rowDJ$viewPejabat.ID_PEJABAT').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowDJ$viewPejabat.ID_PEJABAT').offset().top);
}
else
{
	if( $jquery('#'+'div_viewDJ$viewPejabat.ID_PEJABAT').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewDJ$viewPejabat.ID_PEJABAT').offset().top);
	}
	
}
</script>

#if($listDaerahJagaanByIdPejabat.size()>0)
<tr id="div_displayaddDaerahJagaan$viewPejabat.ID_PEJABAT">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				
				Daerah Jagaan
				</td>
				<td valign="top" >
				:
				</td>
				<td>
				<div id="div_listaddDaerahJagaan$viewPejabat.ID_PEJABAT">
				<table width="100%" border="0"  >
					<tr>
								<td align="right" width="1%"  valign="top" >
								</td>
								<td align="center"  width="1%"  valign="top" ></td>
								<td align="left"  width="94%"  valign="top" ></td>
								</tr>
						#foreach($ar in $listDaerahJagaanByIdPejabat)
						
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
			type="button" id="cmdBttnDaerahJagaan" 
			name="cmdBttnDaerahJagaan" 
			onClick="doDivAjaxCall$formname('div_listaddDaerahJagaan$viewPejabat.ID_PEJABAT','showDisplayAddDaerahJagaan','ID_PEJABAT=$viewPejabat.ID_PEJABAT&idNegeri=$idNegeri');" 
			value="Kemaskini / Tambah Daerah Jagaan" >   
						</td>
						</tr>					
					</table>
				</div>
				</td>
</tr>
<script>	
	document.getElementById("div_displayaddDaerahJagaan$ID_PEJABAT").style.display="";	
</script>
#else
<tr id="div_displayaddDaerahJagaan$ID_PEJABAT" >
<td valign="top" >				
				</td>			
				<td valign="top" >
				Peranan Tambahan (Pilihan)
				</td>
				<td valign="top" >
				:
				</td>
				<td>
				<div id="div_displayaddDaerahJagaan$ID_PEJABAT">
					<table width="100%" border="0" >
												
						<tr >
						<td >
						- &nbsp;
						<input 
			type="button" id="cmdBttnDaerahJagaan2" 
			name="cmdBttnDaerahJagaan2" 
			onclick="doDivAjaxCall$formname('div_listaddDaerahJagaan$viewPejabat.ID_PEJABAT','editAddDaerahJagaan','ID_PEJABAT=$viewPejabat.ID_PEJABAT&idNegeri=$idNegeri');" 
			value="Kemaskini / Tambah Daerah Jagaan" /> 
						</td>
						</tr>					
					</table>
				</div>
				</td>
</tr>
#end