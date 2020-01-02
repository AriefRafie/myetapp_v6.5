 		#if($ListDaerahJagaanDisplay.size()>0)
		<tr id="div_displayaddDaerahJagaan$viewPejabat.ID_PEJABAT">
							<td valign="top" >				
							</td>			
							<td valign="top" >Daerah Jagaan </td>
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
											
									#foreach($daerahJagaan in $ListDaerahJagaanDisplay)
									
									<tr>
									<td align="right"   valign="top" >$daerahJagaan.BIL. </td>
									<td align="center"   valign="top" ></td>
									<td align="left"   valign="top" >$daerahJagaan.KETERANGAN</td>
											</tr>
									#end	
									<tr >
									<td></td><td></td>
									<td >
									<input 
			type="button" id="cmdBttnDaerahJagaan$viewPejabat.ID_PEJABAT" 
			name="cmdBttnDaerahJagaan$viewPejabat.ID_PEJABAT" 
			onClick="doDivAjaxCall$formname('div_listaddDaerahJagaan$viewPejabat.ID_PEJABAT','editAddDaerahJagaan','ID_PEJABAT=$viewPejabat.ID_PEJABAT&ID_NEGERI=$viewPejabat.ID_NEGERI&JENISPEJ=$viewPejabat.JENIS_PEJ');" 
			value="Kemaskini / Tambah Daerah Jagaan" >   
									</td>
									</tr>					
								</table>
							</div>
							</td>
			</tr> 
			<script>	
			document.getElementById("div_displayaddDaerahJagaan$viewPejabat.ID_PEJABAT").style.display="";	
			</script>
			#else
			
			<tr id="div_displayaddDaerahJagaan$viewPejabat.ID_PEJABAT" >
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
								<table width="100%" border="0" >
															
									<tr >
									<td >
									- &nbsp;
									<input type="button" id="cmdBttnDaerahJagaan$viewPejabat.ID_PEJABAT" 
			name="cmdBttnDaerahJagaan$viewPejabat.ID_PEJABAT" 
			onclick="doDivAjaxCall$formname('div_listaddDaerahJagaan$viewPejabat.ID_PEJABAT','editAddDaerahJagaan','ID_PEJABAT=$viewPejabat.ID_PEJABAT&ID_NEGERI=$viewPejabat.ID_NEGERI&JENISPEJ=$viewPejabat.JENIS_PEJ');" 
			value="Kemaskini / Tambah Daerah Jagaan" />
			</td>
									</tr>					
								</table>
							</div>
							</td>
			</tr>
			#end
