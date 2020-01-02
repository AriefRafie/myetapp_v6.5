<tr >
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
				#if($viewPejabat.size() > 0)
					#if($viewPejabat.ALAMAT1 != "")
					$viewPejabat.ALAMAT1<br>
					#end
					#if($viewPejabat.ALAMAT2 != "")
						$viewPejabat.ALAMAT2<br>
					#end
					#if($viewPejabat.ALAMAT3 != "")
						$viewPejabat.ALAMAT3<br>
					#end
					#if($viewPejabat.POSKOD != "")
						$viewPejabat.POSKOD &nbsp;						
					#end
					#if($viewPejabat.BANDAR != "")
						$viewPejabat.BANDAR<br>
					#end
					#if($viewPejabat.NEGERI != "")
						$viewPejabat.NEGERI<br>
					#end
					
					#if($viewPejabat.NO_TEL != "")
						No. Tel : $viewPejabat.NO_TEL<br>
					#end
					#if($viewPejabat.NO_FAX != "")
						No. Fax : $viewPejabat.NO_FAX<br>
					#end
					
					<script type="text/javascript">
					document.getElementById("div_ALAMAT_PEJABAT_$internalType$USER_ID").style.display="";
					$jquery(document).ready(function () {
						document.getElementById("div_displayDaerahJagaan$internalType$USER_ID").style.display="";
				  		doDivAjaxCall$formname('div_displayDaerahJagaan$internalType$USER_ID','showDisplayDaerahJagaan','ID_PEJABATJKPTG=$ID_PEJABAT&ID_JENISPEJABAT=3');			 	  
				  
			  		});
					</script>
				#else
				<script type="text/javascript">
					$jquery(document).ready(function () {
							document.getElementById("div_displayDaerahJagaan$internalType$USER_ID").style.display="none";
					  		doDivAjaxCall$formname('div_displayDaerahJagaan$internalType$USER_ID','showDisplayDaerahJagaan','ID_PEJABATJKPTG=&ID_JENISPEJABAT=3');			 	  
					  
				  		});
					</script>
				#end
				</td>
			</tr>
			