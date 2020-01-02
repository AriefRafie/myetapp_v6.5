<!-- frmPajakanKecilAddHakmilik.jsp -->
<br/>
#parse('app/htp/frmPajakanKecilPaging.jsp')

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
		#parse ("app/htp/frmPajakanKecilInfo.jsp")			
			
		</td>
	</tr>

	<tr>
    	<td>	
			<fieldset><legend><strong>MAKLUMAT HAKMILIK/PEMILIK</strong></legend>
				<table width="100%" border="0">
			  		<tr>
			    		<td width="50%" valign="top" >
				        	<fieldset><legend>HAKMILIK</legend>
		    				<table width="100%" border="0">
					        	<tr>
									<td width="1%">&nbsp;</td>
									<td width="30%">														
										<div align="right" class="labelinput">
						    				<div align="left">Negeri</div>
						    			</div>
									</td>
									<td width="1%">:</td>
					              	 <td width="68%">$socNegeri</td>
					            </tr>		
				            	<tr>
							    	<td width="1%"><span class="labelmandatory">*</span></td>
								    <td width="30%">
										<div align="right" class="labelinput">
						    				<div align="left">Daerah</div>
						    			</div>								    
								    </td>
								    <td width="1%">:</td>
				              		<td width="68%">$socDaerah1</tr>
				            	<tr>
								<td width="1%"><span class="labelmandatory">*</span></td>
									<td width="30%">
										<div align="right" class="labelinput">
						    				<div align="left">Mukim</div>
						    			</div>
									</td>
								    <td width="1%">:</td>
				              		<td width="68%">$socMukim</td>
				            	</tr>
				            	<tr>
									<td width="1%"><span class="labelmandatory">*</span></td>
								    <td width="30%">
										<div align="right" class="labelinput">
						    				<div align="left">Jenis Hakmilik</div>
						    			</div>
								    </td>
								    <td width="1%">:</td>
				              		<td width="66%">$socHakmilik</td>
				            	</tr>
				            	<tr>
								    <td width="1%"></td>
								    <td width="30%">
								    			<div align="right" class="labelinput">
						    				<div align="left">No. Hakmilik</div>
						    			</div>
								    	</td>
								    <td width="1%"></td>
				              		<td width="58%">

									 	<input type="text" name="txtnohakmilik" size="30" value="" onkeyup="this.value=this.value.toUpperCase();" maxlength="50"/>
				              		</td>
				            	</tr>
				            	<tr>
									<td width="1%"><span class="labelmandatory"></span></td>
									<td width="30%">
								    	<div align="right" class="labelinput">
											<div align="left">No. Strata</div>
										</div>
									</td>
									<td width="1%">:</td>
									<td width="68%"><span class="labeldisplay"><span class="labelinput">No.Bang</span>&nbsp;
									  <input name="noBangunan" type="text" class="$disabled" id="noBangunan"  value="" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
									  <span class="labelinput">No.Ting</span>&nbsp;
									  <input name="noTingkat" type="text" class="$disabled" id="noTingkat"  value="" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
									  <span class="labelinput">No.Petak</span>&nbsp;
									  <input name="noPetak" type="text" class="$disabled" id="noPetak"  value="" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
									  </span></td>
							    </tr>
				            	<tr>
									<td width="1%"></td>
									<td width="30%"><div align="right" class="labelinput">
									  <div align="left">No Lot</div>
									  </div></td>
									<td width="1%">:</td>
									<td width="68%">$noLot</td>
							    </tr>
				            	<tr>
									<td width="1%">&nbsp;</td>
									<td width="30%"></td>
									<td width="1%"></td>
									<td width="68%"><input type="text" name="txtnolot" size="15" onkeyup="this.value=this.value.toUpperCase();" maxlength="20" /></td>
							    </tr>	            			
				            	
							</table>				      					
	            			</fieldset>
			      		</td>
			      		
			    		<td width="50%" valign="top" >
							<fieldset><legend>PEMILIK</legend>
		    				<table width="100%" border="0">
						    	<tr>
									<td width="1%">&nbsp;</td>
									<td width="40%">
										<div align="right" class="labelinput">
						    				<div align="left">No. KP/Polis/Tentera/Syarikat</div>
						    			</div>
						    		</td>
									<td width="1%">:</td>
					              	<td width="58%"><input type="text" name="txtnorujukan" size="30" value="$!pihakKepentigan.noRujukan" readonly="readonly" disabled="disabled" class="disabled"></td>
					           	</tr>
					            	<tr>
										<td width="1%"></td>
									    <td width="40%">
										<div align="right" class="labelinput">
						    				<div align="left">Nama</div>
						    			</div>									    
									    </td>
									    <td width="1%">:</td>
					              		<td width="58%"><input type="text" name="txtnama" size="30"  value="$!pihakKepentigan.nama" readonly disabled="disabled" class="disabled"></td>
					            	</tr>
					            	<tr>
										<td width="1%"></td>
									    <td width="40%">
											<div align="right" class="labelinput">
							    				<div align="left">Alamat Pemilik</div>
							    			</div>									    
									    </td>
									    <td width="1%">:</td>
					              		<td width="58%"><input type="text" name="txtalamat1" size="30" value="$!pihakKepentigan.alamat1" readonly disabled="disabled" class="disabled"/></td>
					            	</tr>
					            	<tr>
										<td width="1%">&nbsp;</td>
									    <td width="35%">&nbsp;</td>
									    <td width="1%">&nbsp;</td>
					              		<td width="63%"><input type="text" name="txtalamat2" size="30"  value="$!pihakKepentigan.alamat2" readonly disabled="disabled" class="disabled"></td>
					            	</tr>
					            	<tr>
										<td width="1%">&nbsp;</td>
									    <td width="35%">&nbsp;</td>
									    <td width="1%">&nbsp;</td>
					              		<td width="63%"><input type="text" name="txtalamat3" size="30"  value="$!pihakKepentigan.alamat3" readonly disabled="disabled" class="disabled"></td>
					  				</tr>
					            	<tr>
										<td width="1%">&nbsp;</td>
									    <td width="35%">
											<div align="right" class="labelinput">
							    				<div align="left">Poskod</div>
							    			</div>									    
									    </td>
									    <td width="1%">:</td>
					              		<td width="63%"><input type="text" name="txtposkod" maxlength="5" size="5" onkeyup="validatePoskod(this,this.value);" value="$!pihakKepentigan.poskod" readonly disabled="disabled" class="disabled"/> </td>
					            	</tr>	            			
					            	<tr>
										<td width="1%"></td>
										<td><div align="right" class="labelinput">
										  <div align="left">Negeri</div>
										  </div></td>
										<td>:</td>
										<td>$socNegeri1</td>
								    </tr>	            			
					            	<tr>
										<td width="1%">&nbsp;</td>
									    <td width="35%">Daerah</td>
									    <td width="1%">:</td>
					              		<td width="63%">$socDaerah</td>
					            	</tr>	
									<input type="hidden" name="idnegeri" size="30" value="$idnegeri"  />	
								</table>
							</fieldset>				      					
			      		</td>			      		
			      				
					</tr>			
				</table>	
				<table width="100%">
					<tr>
		        		<td>
		        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
		        		</td>
           			</tr>
				</table>
			</fieldset>
								<!--Button setting-->	
			<p align="center">
				<div align="center">
					<input class="stylobutton" type="button" name="cmdX" id="cmdX" value="Simpan" onclick="tambahMaklumatPemilik('$permohonanInfo.idpermohonan')">
					<input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan">
					<input class="stylobutton" type="button" name="cmdX" id="cmdX" value="Kembali" onclick="skrinSenaraiHakmilikPemilik('$permohonanInfo.idpermohonan')">
				</div>
			</p>
				<input type="hidden" name="pagemode" value="">
				<input type="hidden" name="id_kemaskini" value="">
				<input type="hidden" name="id_pemilik" value="$idPihakBerkepentingan">		
		</td>
	</tr>			
</table>