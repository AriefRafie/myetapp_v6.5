	<fieldset>
		<table width="100%">
       		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Negeri</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$selectNegeriHR
  							</td>
                		</tr>
                     	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Daerah</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$selectDaerahHR
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Bandar/Pekan/Mukim</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$selectMukimHR
  							</td>
                		</tr>

                 	</table>
           		</td>
                        	
                <td valign="top">
               		<table width="100%">
                   	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Jenis Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$selectJenisHakmilikHR
      							<input name="txtKodHakmilik" type="hidden" value="$!txtKodSocJenisHakmilik" />
  							</td>
                		</tr>
              			
            			<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
      							<input name="txtNoHakmilik" type="text" class="$disabled" id="txtNoHakmilik" maxlength="50" value="$!txtNoHakmilik" size="20" $readonly />
	      						<input name="txtmaklumatnotanah" type="hidden" value="$!txtKodSocJenisHakmilik$!txtNoHakmilik  $!txtNoBangunan$!txtNoTingkat$!txtNoPetak  $!selectJenisLotHR$!txtNoLot" />
  							</td>
                		</tr>

                    	<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Strata</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
      							<span class="labelinput">No.Bang</span>&nbsp;<input name="txtNoBangunan" type="text" class="$disabled" id="txtNoBangunan"  value="$!txtNoBangunan" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
								<span class="labelinput">No.Ting</span>&nbsp;<input name="txtNoTingkat" type="text" class="$disabled" id="txtNoTingkat"  value="$!txtNoTingkat" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
								<span class="labelinput">No.Petak</span>&nbsp;<input name="txtNoPetak" type="text" class="$disabled" id="txtNoPetak"  value="$!txtNoPetak" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
								
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kod</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$selectJenisLotHR
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Lot/PT</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
      							<input name="txtNoLot" type="text" class="$disabled" id="txtNoLot" size="30" maxlength="25" value="$!txtNoLot" $readonly />
  							</td>
                		</tr> 
                		
                	</table>
                </td>
       		</tr>                    
		</table>
	</fieldset>	