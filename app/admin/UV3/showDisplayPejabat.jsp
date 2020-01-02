<tr id="div_ALAMAT_PEJABATJKPTG$internalType$USER_ID">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td>
				#if($viewPejabatJKPTG.size() > 0)
					
					#if($viewPejabatJKPTG.ALAMAT1 != "")
						$viewPejabatJKPTG.ALAMAT1<br>
					#end								
					#if($viewPejabatJKPTG.ALAMAT2 != "")
						$viewPejabatJKPTG.ALAMAT2<br>
					#end					
					#if($viewPejabatJKPTG.ALAMAT3 != "")
						$viewPejabatJKPTG.ALAMAT3<br>
					#end					
					#if($viewPejabatJKPTG.POSKOD != "")
						$viewPejabatJKPTG.POSKOD &nbsp;						
					#end
					#if($viewPejabatJKPTG.BANDAR != "")
						$viewPejabatJKPTG.BANDAR<br>
					#end
					#if($viewPejabatJKPTG.NEGERI != "")
						$viewPejabatJKPTG.NEGERI<br>
					#end
					#if($viewPejabatJKPTG.NO_TEL != "")
						No. Tel : $viewPejabatJKPTG.NO_TEL<br>
					#end
					#if($viewPejabatJKPTG.NO_FAX != "")
						No. Fax : $viewPejabatJKPTG.NO_FAX<br>
					#end
					
				<script>
					document.getElementById("div_ALAMAT_PEJABATJKPTG$internalType$USER_ID").style.display="";
					if('$viewPejabatJKPTG.ID_SEKSYEN'=='2')
					{
						$jquery(document).ready(function () {
							document.getElementById("div_displayDaerahJagaan$internalType$USER_ID").style.display="";
					  		doDivAjaxCall$formname('div_displayDaerahJagaan$internalType$USER_ID','showDisplayDaerahJagaan','ID_PEJABATJKPTG=$viewPejabatJKPTG.ID_PEJABATJKPTG&ID_JENISPEJABAT=$viewPejabatJKPTG.ID_JENISPEJABAT');			 	  
					  
				  		});
					}
					else
					{
						document.getElementById("div_displayDaerahJagaan$internalType$USER_ID").style.display="none";
					}
				</script>
				#else
				<script>	
					
						$jquery(document).ready(function () {
							document.getElementById("div_displayDaerahJagaan$internalType$USER_ID").style.display="none";
					  		doDivAjaxCall$formname('div_displayDaerahJagaan$internalType$USER_ID','showDisplayDaerahJagaan','ID_PEJABATJKPTG=&ID_JENISPEJABAT=22');			 	  
					  
				  		});
					
				</script>
				#end				
				</td>
</tr>
