<!-- frmPajakanKecilUpdateHakmilik.jsp -->
<br/>
##parse('app/htp/frmPajakanKecilPaging.jsp')
<!-- <br/> -->

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
		#parse ("app/htp/frmPajakanKecilInfo.jsp")			
			
		</td>
	</tr>	
	<tr>
		<td>

			<fieldset><legend><strong>MAKLUMAT HAKMILIK</strong></legend>
				<table width="100%" border="0"  cellspacing="2">
					<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">#if($pageMode == "new" || $pageMode == "kemaskini")* #end</span>
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left">Negeri</div>
						    </div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							$socNegeri
						</td>
					</tr>
					<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">#if($pageMode == "new" || $pageMode == "kemaskini")* #end</span>
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left">Daerah</div>
						    </div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							$socDaerah1
						</td>
					</tr>					
					<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">#if($pageMode == "new" || $pageMode == "kemaskini")* #end</span>
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left">Mukim</div>
						    </div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							$socMukim
						</td>
					</tr>
					<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">#if($pageMode == "new" || $pageMode == "kemaskini")* #end</span>
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left">Jenis Hakmilik</div>
						    </div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							$socHakmilik
						</td>
					</tr>								
					<tr>
						<td valign="top" width="1%">
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
								<div align="left" class="labelinput">No. Hakmilik</div>
							</div>
						</td>				      	
						<td width="1%"></td>				        
						<td width="68%">
							<input type="text" name="txtnohakmilik" size="30" value="$noHakmilik" onkeyup="this.value=this.value.toUpperCase();" $classDis maxlength="50"/>
						</td>
					</tr>
					<tr>
					  <td valign="top" width="1%"></td>
					  <td width="30%">
							<div align="left" class="labelinput">No. Strata</div>					  
					  </td>
					  <td width="1%">:</td>
					  	<td width="68%">
					 		<span class="labelinput">No.Bang</span>&nbsp;
				            <input name="noBangunan" type="text"  id="noBangunan"  value="$!noBangunan" size="3" maxlength="3" $classDis onkeyup="this.value=this.value.toUpperCase();"/>
                           	<span class="labelinput">No.Ting</span>&nbsp;
                         	<input name="noTingkat" type="text" id="noTingkat"  value="$!noTingkat" size="3" maxlength="3" $classDis onkeyup="this.value=this.value.toUpperCase();"/>
                           	<span class="labelinput">No.Petak</span>&nbsp;
                    		<input name="noPetak" type="text" id="noPetak"  value="$!noPetak" size="3" maxlength="3" $classDis onkeyup="this.value=this.value.toUpperCase();"/>
				            </span>
				   		</td>
				  	</tr>
					<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">#if($pageMode == "new" || $pageMode == "kemaskini")* #end</span>
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left">No Lot</div>
						    </div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							$noLot
						</td>
					</tr>				
					<tr>
						<td valign="top" width="1%">
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    </div>
						</td>				      	
						<td width="1%"></td>				        
						<td width="68%">
							<input type="text" name="txtnolot" size="15" value="$noLotvalue" onkeyup="this.value=this.value.toUpperCase();" $classDis maxlength="20">
						</td>
					</tr>
					<tr>
						<td colspan=4 align="center"></td>
					</tr>
					
					<!-- <tr>
						<td colspan=4 align="center">		                   
		                    #if($pageMode == "new" || $pageMode == "kemaskini")
								<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanUpdateHakmilik('$idPermohonan',$idHakmilikurusan)">
								<input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="skrinSenaraiHakmilikPemilik('$idPermohonan')">
		                     
		                     #else	                       
		                       <input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniHakmilik('$idPermohonan',$idHakmilikurusan)">		
								<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="skrinSenaraiHakmilikPemilik('$idPermohonan')">
		
							#end
		
						</td>
					</tr> -->
				</table> 
				<table width="100%">
			  <tr>
		        		<td>
		        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
		        		</td>
           			</tr>
				</table>
			</fieldset>  
			<p align="center">
				<div align="center">
				#if($pageMode == "new" || $pageMode == "kemaskini")
					<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanUpdateHakmilik('$idPermohonan',$idHakmilikurusan)">
					<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="skrinSenaraiHakmilikPemilik('$idPermohonan')">
		                     
		  		#else	                       
					#if (!$!jenisAkses.equals('Readonly'))
		        	<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniHakmilik('$idPermohonan',$idHakmilikurusan)">		
					#end
					<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="skrinSenaraiHakmilikPemilik('$idPermohonan')">
		
				#end
				</div>
			</p>
 			<input type="hidden" name="id_kemaskini" value="$permohonanInfo.idpermohonan"/>
   			<input type="hidden" name="pagemode" value="$pagemode"/>	
			<input type="hidden" name="idnegeri" size="30" value="$idnegeri"/>
            <input type="hidden" name="id_permohonan" value="$idPermohonan"/>
            
		</td>
	</tr>
</table>
 