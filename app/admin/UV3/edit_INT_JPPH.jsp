<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			<tr>
				<td valign="top" >		
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="ID_NEGERI_$internalType$USER_ID"  name="ID_NEGERI_$internalType$USER_ID"
					onChange="doDivAjaxCall$formname('divListDaerah$internalType$USER_ID','selectDaerahByNegeri','ID_NEGERI='+$jquery('#ID_NEGERI_$internalType$USER_ID').val()+'&USER_ID=$USER_ID&internalType=$internalType');" 
					
					 >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJNEGERI )		
							#set ( $selected_ruj = "" )									
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</td>
			</tr>
            
            <tr>
				<td valign="top" >
				<font color="red" >*</font>						
				</td>			
				<td valign="top" >
				Daerah
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				
				<div id="divListDaerah$internalType$USER_ID">
					<select id="ID_DAERAH_$internalType$USER_ID"  name="ID_DAERAH_$internalType$USER_ID" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJDAERAH )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_DAERAH==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</div>
				</td>
			</tr>
            
		<!--	BUANG DAERAH-->
			<tr>
				<td valign="top" >	
				<font color="red" >*</font>		
				</td>			
				<td valign="top" >
				Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				<div id="divListPejabat$internalType$USER_ID">
					<select id="ID_PEJABAT_$internalType$USER_ID"  
					name="ID_PEJABAT_$internalType$USER_ID" 
					onChange="doDivAjaxCall$formname('div_ALAMAT_PEJABAT_$internalType$USER_ID','showAlamatPejabat','ID_PEJABAT='+$jquery('#ID_PEJABAT_$internalType$USER_ID').val()+'&USER_ID=$USER_ID&internalType=$internalType');" 
					
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listPejabat )		
							#set ( $selected_ruj = "" )									
							<option $selected_ruj value="$ruj.ID_PEJABAT" >
							$ruj.NAMA_PEJABAT
							</option>
						#end
					</select>
				</div>
				</td>
			</tr>
			
			
			<tr id="div_ALAMAT_PEJABAT_$internalType$USER_ID" style="display:none">
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
					</script>
					
				#end
				</td>
			</tr>
			
			
			
			
			
			
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
				<script>
				document.getElementById("div_displayDaerahJagaan$internalType$USER_ID").style.display="";
				  $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_displayDaerahJagaan$internalType$USER_ID','showDisplayDaerahJagaan','ID_PEJABATJKPTG=&ID_JENISPEJABAT=3');			 	  
				  });
				
				</script>
				</td>
			</tr>
		
			</table>