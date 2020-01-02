<br/>
#parse('app/htp/frmPajakanKecilPaging.jsp')
<!-- <br/> -->

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
			    				<table width="100%" border="0">
			      				<tr>
			        				<td valign="top">
				        				<fieldset>
				          				<legend>HAKMILIK</legend>
				          				<table width="100%" border="0">
				            			<tr>
								              <td width="2%">&nbsp;</td>
									            <td width="32%">
													<div align="right" class="labelinput">
												    	<div align="left">Negeri</div>
												    </div>
												</td>
								              <td width="2%">:</td>
				              				  <td width="64%">$socNegeri </td>
				            			</tr>
				            			
				            			<tr>
								              <td width="2%"><span class="labelmandatory">*</span></td>
									              <td width="32%">
													<div align="right" class="labelinput">
												    	<div align="left">Daerah</div>
												    </div>
												  </td>
								              <td width="2%">:</td>
				              				  <td width="64%">$socDaerah1</tr>
				            			<tr>
								              <td width="2%"><span class="labelmandatory">*</span></td>
									            <td width="32%">
													<div align="right" class="labelinput">
														<div align="left">Mukim</div>
													</div>
												</td>
								              <td width="2%">:</td>
				              				  <td width="64%">$socMukim</td>
				            			</tr>	            			
				            			<!-- <tr>
								              <td width="1%">&nbsp;</td>
								              <td width="40%">No Hakmilik</td>
								              <td width="1%">:</td>
				              				  <td width="58%">
												 <select name="sochakmilik" onchange="enableField()">
	                        						<option value="0">SILA PILIH NO HAKMILIK</option>
	       											#foreach ( $senarai in $senaraihakmilik )
	     				  			                <option value="$senarai.idhakmilikurusan">$senarai.kodjenishakmilik $senarai.nohakmilik</option>
	       											#end
	                       		                  	<option value="">LAIN-LAIN</option>
	                     						</select>			              				  
	                     					</td>
				            			</tr>	-->
				            			<tr>
								              <td width="2%"><span class="labelmandatory">*</span></td>
									            <td width="32%">
													<div align="right" class="labelinput">
												    	<div align="left">Jenis Hakmilik</div>
												    </div>
												</td>
								              <td width="2%">:</td>
				              				  <td width="64%">
												$socHakmilik
				              				  </td>
				            			</tr>
				            			<tr>

								              <td width="2%"></td>
								              <td width="32%">
													<div align="right" class="labelinput">
												    	<div align="left">No. Hakmilik</div>
												    </div>								              	
								              	</td>
								              <td width="2%"></td>
				              				  <td width="64%">

												<input type="text" name="txtnohakmilik" size="30" value="$!nohakmilik" onkeyup="this.value=this.value.toUpperCase();"/>
				              				  </td>
				            			</tr>
				            			<tr>
								        	<td width="2%"><span class="labelmandatory"><!--*--></span></td>
									    	<td width="32%">
												<div align="right" class="labelinput">
													<div align="left">No. Strata</div>
												</div>
											</td>
								            <td width="2%">:</td>
				              				<td width="64%"><span class="labeldisplay"><span class="labelinput">No.Bang</span>&nbsp;
				              				    <input name="noBangunan" type="text" class="$disabled" id="noBangunan"  value="" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
                                                <span class="labelinput">No.Ting</span>&nbsp;
                                                <input name="noTingkat" type="text" class="$disabled" id="noTingkat"  value="" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
                                                <span class="labelinput">No.Petak</span>&nbsp;
                                                <input name="noPetak" type="text" class="$disabled" id="noPetak"  value="" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
				              				</span></td>
				            			</tr>
				            			<tr>
				            			  <td><span class="labelmandatory">*</span></td>
				            			  <td><div align="right" class="labelinput">
				            			    <div align="left">No Lot</div>
				            			    </div></td>
				            			  <td>:</td>
				            			  <td> $noLot </td>
				            			  </tr>
				            			<tr>
				            			  <td></td>
				            			  <td></td>
				            			  <td></td>
				            			  <td><input type="text" name="txtnolot" size="15" value="$!nolot" onkeyup="this.value=this.value.toUpperCase();" /></td>
				            			  </tr>
				            			</table>
				            			</fieldset>
				   					</td>	
								</tr>
								</table>
							</td><!--Right side-->			
							<td width="50%" valign="top" >
			    				<table width="100%" border="0">
				      				<tr>
				        				<td valign="top">
					        				<fieldset>
					          				<legend>PEMILIK</legend>
					          				<table width="100%" border="0">
					          				<tr>
									              <td width="1%">&nbsp;</td>
									        	<td width="40%">
													<div align="right" class="labelinput">
									    				<div align="left">
									    					</div>
									    			</div>
									    		</td>
									              <td width="1%">:</td>
					              				  <td width="58%"><input type="checkbox" name="copy" onclick="copyAlamat('maklumat_pemohon')"  />
									    					Alamat surat menyurat adalah sama
					              				  </td>
					            			</tr>
					            			<tr>
									              <td width="1%">&nbsp;</td>
									        	<td width="40%">
													<div align="right" class="labelinput">
									    				<div align="left">No. KP/Polis/Tentera/Syarikat</div>
									    			</div>
									    		</td>
									              <td width="1%">:</td>
					              				  <td width="63%">
					              				  	<input type="text" name="txtnorujukan" size="30" value="$!norujukan" onblur="this.value=this.value.toUpperCase()" >
					              				  </td>
					            			</tr>
					            			<tr>
									              <td width="1%"><span class="labelmandatory">*</span></td>
									              <td width="40%">
													<div align="right" class="labelinput">
									    				<div align="left">Nama</div>
									    			</div>									    
												  </td>
									              <td width="1%">:</td>
					              				  <td width="63%">
					              				  	<input type="text" name="txtnama" size="30"  value="$!nama" onkeyup="this.value=this.value.toUpperCase();" >
					              				  </td>
					            			</tr>
					            			<tr>
									        	<td width="1%"><span class="labelmandatory">*</span></td>
									        	<td width="40%">
													<div align="right" class="labelinput">
									    				<div align="left">Alamat Pemilik</div>
									    			</div>									    
											    </td>
									            <td width="1%">:</td>
					              				<td width="63%">
					              					<input type="text" name="txtalamat1" size="30" value="$!alamat1"  onblur="this.value=this.value.toUpperCase()" >
					              				</td>
					            			</tr>
					            			<tr>
									              <td width="1%">&nbsp;</td>
									              <td width="40%">&nbsp;</td>
									              <td width="1%">&nbsp;</td>
					              				  <td width="63%">
					              				  	<input type="text" name="txtalamat2" size="30" value="$!alamat1" onblur="this.value=this.value.toUpperCase()" >
					              				  </td>
					            			</tr>
					            			<tr>
									              <td width="1%">&nbsp;</td>
									              <td width="40%">&nbsp;</td>
									              <td width="1%">&nbsp;</td>
					              				  <td width="63%">
					              				  	<input type="text" name="txtalamat3" size="30" value="$!alamat1" onblur="this.value=this.value.toUpperCase()" >
					              				  </td>
					            			</tr>
					            			<tr>
									              <td width="1%">&nbsp;</td>
									        	<td width="40%">
													<div align="right" class="labelinput">
									    				<div align="left">Poskod</div>
									    			</div>									    
												</td>
									              <td width="1%">:</td>
					              				  <td width="63%">
													<input type="text" name="txtposkod" maxlength="5" size="5" value="$!poskod" onkeyup="validatePoskod(this,this.value);" >
					                	        	</td>
					            			</tr>	            			
					            			<tr>
									        	<td width="1%"><span class="labelmandatory">*</span></td>
									       		<td width="35%">
													<div align="right" class="labelinput">
									    				<div align="left">Negeri</div>
									    			</div>									    
											    </td>
									            <td width="1%">:</td>
					              				<td width="63%">$socNegeri1</td>
					            			</tr>	

					            			<tr>
					            			  <td><span class="labelmandatory">*</span></td>
					            			  <td><div align="right" class="labelinput">
					            			    <div align="left">Daerah</div>
					            			    </div></td>
					            			  <td>:</td>
					            			  <td>$socDaerah</td>
					            			  </tr>	            			

					            			<!--<tr>
									              <td width="1%">&nbsp;</td>
									              <td width="35%">Nama Pegawai</td>
									              <td width="1%">:</td>
					              				  <td width="63%">$socPegawai</td>
					            			</tr>-->	            				            			
											</table>
										</fieldset>
					   					</td>	
									</tr>
								</table>
							</td> <!--end right (pemilik)-->
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
					<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="tambahMaklumatPemilik('$permohonanInfo.idpermohonan')">
					<input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan">
					<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="skrinSenaraiHakmilikPemilik('$permohonanInfo.idpermohonan')">  				
				</div>
			</p> 
 			<input type="hidden" name="id_kemaskini" value="$permohonanInfo.idpermohonan"/>
 			<input type="hidden" name="idpermohonan" value="$permohonanInfo.idpermohonan"/>
   			<input type="hidden" name="pagemode" value="$pagemode"/>
			<input type="hidden" name="idnegeri2" size="30" value="$idnegeri" />		
			<input type="hidden" name="idnegeri" size="30" value="$idnegeri"/>
		</td>
	</tr>
		<input type="hidden" name="txtTempPBNama" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="80" value="$!pemilik.getNama()" />
		<input type="hidden" name="txtTempPBJenis" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="80" value="$!pemilik.getNama()" />
		<input type="hidden" name="txtTempPBNoRuj" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="80" value="$!pemilik.getNoRujukan()" />
		<input type="hidden" name="txtTempPBAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat1()" />
		<input type="hidden" name="txtTempPBAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat2()"/>
		<input type="hidden" name="txtTempPBAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemilik.getAlamat3()" />
		<input type="hidden" name="txtTempPBPoskod" size="5" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$!pemilik.getPoskod()"  />
		<input type="hidden" name="txtTempPBSocNegeri" size="5" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$!pemilik.getIdNegeri()"  />
		<input type="hidden" name="txtTempPBSocDaerah" size="5" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$!pemilik.getIdDaerah()"  />
		<input type="hidden" name="txtTempPBNoTelefon" size="20" maxlength="14" value="$!pemilik.getTel()"  />
        <input type="hidden" name="txtTempPBNoFax" size="20" maxlength="14"  value="$!pemilik.getFax()"  />
	
</table>

<script>
 	//Tambah Hakmilik dan Pemilik
 function tambahMaklumatPemilik(id) {
	
	/*if(document.${formName}.sochakmilik.value == "" || document.${formName}.sochakmilik.value == 0){
		if(document.${formName}.txtnohakmilik.value == "" || document.${formName}.txtnohakmilik.value == 0){
			alert('Sila pilih No Hakmilik terlebih dahulu @\n Lain-lain bagi kemasukan baru');
			return;
		}else if(document.${formName}.txtnolot.value == ""){	
			alert('Sila masukkan No Lot terlebih dahulu');
			return;
		}	
	}*/
	
	if ( document.${formName}.socDaerah1.value == "" ) { 
    	alert('Sila pilih maklumat daerah Hakmilik terlebih dahulu.');
    	document.${formName}.socDaerah1.focus(); 
    	return; 
    }
	
	if ( document.${formName}.socMukim.value == "" ) { 
    	alert('Sila masukkan nama Mukim terlebih dahulu.');
    	document.${formName}.socMukim.focus(); 
    	return; 
    	}    
	
	if ( document.${formName}.socHakmilik.value == "" ) { 
    	alert('Sila masukkan Jenis Hakmilik terlebih dahulu.');
    	document.${formName}.socHakmilik.focus(); 
    	return; 
    }    
    if ( document.${formName}.txtnohakmilik.value == "" ) { 
    	alert('Sila masukkan Nombor Hakmilik terlebih dahulu.');
    	document.${formName}.txtnohakmilik.focus(); 
    	return; 
    }
    if ( document.${formName}.noLot.value == "" ) { 
    	alert('Sila masukkan Jenis Lot terlebih dahulu.');
    	document.${formName}.noLot.focus(); 
    	return; 
    } 
    if(document.${formName}.txtnolot.value == ""){	
		alert('Sila masukkan No Lot terlebih dahulu');
    	document.${formName}.txtnolot.focus(); 
		return;
	}

	if ( document.${formName}.txtnama.value == "" ) { 
    	alert('Sila masukkan nama terlebih dahulu.');
    	document.${formName}.txtnama.focus(); 
    	return; 
    	}    
	
	if ( document.${formName}.txtalamat1.value == "" ) { 
    	alert('Sila masukkan alamat 1 terlebih dahulu.');
    	document.${formName}.txtalamat1.focus(); 
    	return; 
    }  
	/*
    if ( document.${formName}.txtalamat2.value == "" ) { 
    	alert('Sila masukkan alamat 2 lot terlebih dahulu.');
    	document.${formName}.txtalamat2.focus(); 
    	return; 
    }
	*/
    if ( document.${formName}.txtposkod.value == "" ) { 
    	alert('Sila masukkan maklumat poskod terlebih dahulu.');
    	document.${formName}.txtposkod.focus(); 
    	return; 
    } 
   	if ( document.${formName}.socDaerah.value == "" ) { 
    	alert('Sila pilih maklumat daerah terlebih dahulu.');
    	document.${formName}.socDaerah.focus(); 
    	return; 
    }
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 'simpan';
	doAjaxCall${formName}("tambahhakmilikpemilik");
	
}

function nexti6(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.method="post";
	doAjaxCall${formName}("pksemakanseterus");
}
//sama dengan nexti()
function back(id) {
	document.${formName}.id_kemaskini.value = id;
	doAjaxCall${formName}("pksemakanseterus");
}

// Skrin Senarai Hakmilik
function nexti2(id) {
	document.${formName}.id_kemaskini.value = id;
	doAjaxCall${formName}("pkpemilikseterus");
}

// Skrin Senarai Hakmilik

function skrinSenaraiHakmilikPemilik(id) {
	document.${formName}.id_kemaskini.value = id;
	doAjaxCall${formName}("pksemakanseterus");
}

function doChangeDaerahHakmilik(id) {
	document.${formName}.pagemode.value = 'dochangedaerah';
	document.${formName}.id_kemaskini.value = id;
	doAjaxCall${formName}("tambahhakmilikpemilik");
}
//Skrin Tambah Hakmilik & Pemilik
function skrinTambahHakmilikPemilik(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 0;
	doAjaxCall${formName}("tambahhakmilikpemilik");
}

 </script>
