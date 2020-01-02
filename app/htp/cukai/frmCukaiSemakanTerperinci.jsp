<!-- frmCukaiSemakanTerperinci.jsp -->
<!-- CL-02-02X -->

<table width="98%" border="0">
	<tr>
		<td>
			<fieldset><legend>MAKLUMAT CUKAI</legend> 
				<table width="100%" border="0">
					<tr>
						<td>
							#parse("app/htp/rekod/frmPendaftaranHakmilikMaklumatFailReadOnly.jsp")
						</td>
				  	</tr>
				  	
				 	<tr>
						<td>
							<fieldset>							
				  				<table width="100%" border="0">
					      			<tr>
					            		<td width="50%" valign="top">
						                	<table width="100%" border="0">
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Negeri</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$txtNamaNegeri
						  							</td>
						                		</tr>
						                     	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Daerah</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$txtNamaDaerah
						  							</td>
						                		</tr>
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Bandar/Pekan/Mukim</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$txtNamaMukim
						  							</td>
						                		</tr>
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Kegunaan Tanah</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$!kegunaanTanah
						  							</td>
						                		</tr>
						                 	</table>
           								</td>
                        	
                						<td valign="top">
						               		<table width="100%">
						                   		<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Jenis Hakmilik</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$!txtJenisHakmilik
						  							</td>
                								</tr>
              			
						            			<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">No. Hakmilik</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						                   				$!txtNoHakmilik
						      							<!--<input name="txtNoHakmilik" type="text" class="$disabled" id="txtNoHakmilik" maxlength="50" value="$!txtNoHakmilik" size="20" $readonly /> -->
						  							</td>
						                		</tr>
						
						                    	<!--<tr>
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
						                		</tr>-->
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Kod</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$selectLot
						  							</td>
						                		</tr>
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">No Lot/PT</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						                   				$!txtNoLot
						      							<!-- <input name="txtNoLot" type="text" class="$disabled" id="txtNoLot" maxlength="15" value="$txtNoLot" size="20" $readonly /> -->
						  							</td>
						                		</tr> 
						                	</table>
						                </td>
						       		</tr>                

								</table>
							</fieldset>
             			</td>
       				</tr>   
       				
				 	<tr>
						<td>
							<fieldset>							
				  				<table width="100%" border="0">
					      			<tr>
					            		<td width="50%" valign="top">
						                	<table width="100%" border="0">
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Tarikh Daftar Hakmilik</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$!tarikhDaftar
						  							</td>
						                		</tr>
						                     	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Luas</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$!txtLuas (Hektar)
						  							</td>
						                		</tr>
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Cukai Tali Air</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$!cukaiTaliAir
						  							</td>
						                		</tr>
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Cukai Parit</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$!cukaiParit
						  							</td>
						                		</tr>
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Denda</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$!Denda
						  							</td>
						                		</tr>						                		
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Bayaran Lain</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$!bayaranLain
						  							</td>
						                		</tr>

						                 	</table>
           								</td>
                        	
                						<td valign="top">
						               		<table width="100%">
						                   		<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Cukai Terkini</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$!cukaiTerkini
						  							</td>
                								</tr>
              			
						            			<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Cukai Tahun Pertama</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						                   				$!Cukai
						  							</td>
						                		</tr>
						
						                    	<!--<tr>
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
						                		</tr>-->
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Pengecualian</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						 								$!pengecualian
						  							</td>
						                		</tr>
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Tunggakkan</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						                   				$!tunggakkan
						  							</td>
						                		</tr> 
						                    	<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Lebihan</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						                   				$!lebihan
						  							</td>
						                		</tr>
		                    					<tr>
						  							<td width="1%"  >
						  								<span class="labelmandatory"></span>
										        	</td>				        
						                        	<td width="30%">
						                            	<div align="left">
						                            		<span class="labelinput">Status Cukai</span>
						                            	</div>
						                        	</td>
						                  			<td width="1%" class="labelinput" >:</td>
						                   			<td width="68%" class="labeldisplay" >
						                   				$!txtCukaiStatus
						  							</td>
						                		</tr> 						                		
						                		
						                	</table>
						                </td>
						       		</tr>                

								</table>
							</fieldset>
             			</td>
       				</tr>        				
       				 
				</table>

			</fieldset>
		</td>
	</tr>
	<tr>
		<td align="center">
			<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="search_data()">
		</td>
  	</tr>
</table>
  <input type="hidden" name="idHakmilik" value="$!idHakmilik">
  <input type="hidden" name="noHakmilik" value="$!txtNoHakmilik">

		