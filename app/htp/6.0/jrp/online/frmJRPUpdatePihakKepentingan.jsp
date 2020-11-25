
##parse('app/htp/frmPajakanKecilPaging.jsp')
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
		#parse ("app/htp/frmPajakanKecilInfo.jsp")			
			
		</td>
	</tr>	
    </table>

			<fieldset><legend><strong>MAKLUMAT PEMILIK</strong></legend>
				<table width="100%" border="0"  cellspacing="2">
					<tr>
						<td valign="top" width="1%">
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
									    				<div align="left">
															#parse ("app/htp/pajakankecil/utiliti/frmPBLabelScript.jsp")									    					</div>
									    				</div>						    	
						    </div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							<input type="text" name="txtnorujukan" size="50" maxlength="60" value="$!pihak.noRujukan" onkeyup="this.value=this.value.toUpperCase();" $classDis>
						</td>
					</tr>
					<tr>
						<td width="1%">
							<span class="labelmandatory">#if($pageMode == "new" || $pageMode == "kemaskini")* #end</span>
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left">Nama</div>
						    </div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							<input type="text" name="txtnama" size="50" maxlength="80" value="$!pihak.nama" onkeyup="this.value=this.value.toUpperCase();" $classDis>
						</td>
					</tr>
					<tr>
						<td width="1%">
							<span class="labelmandatory">#if($pageMode == "new" || $pageMode == "kemaskini")* #end</span>
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left">Alamat Pemilik</div>
						    </div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							<input type="text" name="txtalamat1" size="50" maxlength="60" value="$!pihak.alamat1" onkeyup="this.value=this.value.toUpperCase();" $classDis>
						</td>
					</tr>

					<tr>
						<td width="1%">
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left"></div>
						    </div>
						</td>				      	
						<td width="1%"></td>				        
						<td width="68%">
							<input type="text" name="txtalamat2" size="50" maxlength="60" value="$!pihak.alamat2" onkeyup="this.value=this.value.toUpperCase();" $classDis>
						</td>
					</tr>
					<tr>
						<td valign="top" width="1%">
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left"></div>
						    </div>
						</td>				      	
						<td width="1"></td>				        
						<td width="68%">
							<input type="text" name="txtalamat3" size="50" maxlength="60" value="$!pihak.alamat3" onkeyup="this.value=this.value.toUpperCase();" $classDis>
						</td>
					</tr>
					<tr>
						<td valign="top" width="1%">
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left">Poskod</div>
						    </div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							<input type="text" name="txtposkod" maxlength="5" size="5" 
	                	onkeyup="validatePoskod(this,this.value);" value="$!pihak.poskod" $classDis >
						</td>
					</tr>
					<tr>
						<td valign="top" width="1%">
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left">Negeri</div>
						    </div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							$socNegeri
							<input type="hidden" name="idnegeri" size="30" value="$idnegeri"  />
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
							$socDaerah
							<input type="hidden" name="idnegeri" size="30" value="$idnegeri"  />

						</td>
					</tr>
					<tr>
						<td width="1%">
							<span class="labelmandatory">#if($pageMode == "new" || $pageMode == "kemaskini")* #end</span>
						</td>				        
						<td width="30%">
							<div align="right" class="labelinput">
						    	<div align="left">No. Telefon</div>
						    </div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							<input type="text" name="txtnotelefon" size="50" maxlength="80" value="$!pihak.tel" onkeyup="this.value=this.value.toUpperCase();" $classDis>
						</td>
					</tr>

					<tr>
						<td colspan=4 align="center"></td>
					</tr>
				<table>
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
						<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanUpdatePemilik('$idPermohonan',$!pihak.idpihakberkepentingan)">
						<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="skrinSenaraiHakmilikPemilik('$idPermohonan')">                        
                     
                     #else                       
						#if (!$!jenisAkses.equals('Readonly'))
                     	<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniPemilik('$idPermohonan',$!pihak.idpihakberkepentingan)">
						#end
						<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="skrinSenaraiHakmilikPemilik('$idPermohonan')">

					#end									
					</div>
				</p>		
			
			<input type="hidden" name="id_kemaskini" value="$permohonanInfo.idpermohonan"/>
			<input type="hidden" name="pagemode" value="$pagemode"/>	
			<input type="hidden" name="idnegeri" size="30" value="$idnegeri"/>
			<input type="hidden" name="id_permohonan" value="$idPermohonan"/>
			<input type="hidden" name="idPihakBerkepentingan" value="$!pihak.idpihakberkepentingan"/>
			
		</td>
	</tr>

</table>

