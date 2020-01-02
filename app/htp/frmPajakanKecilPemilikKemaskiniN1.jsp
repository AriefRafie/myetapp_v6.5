<br/>
#parse('app/htp/frmPajakanKecilPaging.jsp')
<!-- <br/> -->

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
		#parse ("app/htp/frmPajakanKecilInfo.jsp")			
			
		</td>
	</tr>	

## 0=
	## 1=
	## 2=view
	## 3=
	## 4=
	#set ( $pagemode = 0 )			
	##set ( $nofailseksyen = $nofail )
	##if ( $nofailseksyen == ""  )
			##set ( $nofailseksyen = "" )	
	##end
	
	#set ($inputstyle = "class=disabled" )
	#set ($inputstyleread = "readonly class=disabled" )
	#set ($selectstyle = "disabled class=disabled" )
	#if ($pageMode == 2 )	
		##set ($inputstyle = "" )
		##set ($selectstyle = "" )
		##set ($inputstylerep = "class=disabled readonly" )
			
	#elseif($pageMode == 3 )	
		#set ($inputstyleread = "" )
		#set ($selectstyle = "" )

	#else
		#set ($inputstyle = "class=disabled" )
		##elseif ( $pageMode != 0  )
		#set ( $pagemode = $pageMode )	
	#end		

 	<tr>
    	<td>	
			<fieldset><legend><strong>MAKLUMAT HAKMILIK/PEMILIK</strong></legend>
				<table width="100%" border="0">
			  		<tr>
			    		<td width="50%" valign="top" >
		    				<table width="100%" border="0">
			      				<tr>
			        				<td valign="top">
				        				<fieldset><legend>HAKMILIK</legend>
				          				<table width="100%" border="0">
					            			<tr>
									        	<td width="1%">&nbsp;</td>
									            <td width="23%">
													<div align="right" class="labelinput">
												    	<div align="left">Negeri</div>
												    </div>
												</td>
									            <td width="2%">:</td>
					              				<td width="74%"><input name="lblnegeri" value="$infohakmilik.labelNegeri" readonly="readonly" disabled="disabled" class="disabled"></td>
					            			</tr>
					            			
					            			<tr>
									              <td width="1%"></td>
									              <td width="23%">
													<div align="right" class="labelinput">
												    	<div align="left">Daerah</div>
												    </div>
												  </td>
									              <td width="2%">:</td>
					              				  <td width="74%"><input name="lblnegeri" value=" $infohakmilik.labelDaerah" readonly="readonly" disabled="disabled" class="disabled"></tr>
					            			<tr>
									        	<td width="1%"></td>
									            <td width="23%">
													<div align="right" class="labelinput">
														<div align="left">Mukim</div>
													</div>
												</td>
									              <td width="2%">:</td>
					              				  <td width="74%"><input name="lblnegeri" value="$infohakmilik.labelMukim" readonly="readonly"  class="disabled" size="30%"></td>
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
					            			<!--<tr>
									              <td width="1%"><span class="labelmandatory">*</span></td>
									              <td width="40%">Kod Hakmilik</td>
									              <td width="1%">:</td>
					              				  <td width="58%">
													$socHakmilik
					              				  </td>
					            			</tr> -->
					            			<tr>
									        	<td width="1%"></td>
									            <td width="23%">
													<div align="right" class="labelinput">
												    	<div align="left">Jenis dan No. Hakmilik</div>
												    </div>
												</td>
									              <td width="2%">:</td>
					              				  <td width="74%">
					              				  	<input name="lblnegeri" value="$infohakmilik.labelNohakmilik" size="30" readonly="readonly" disabled="disabled" class="disabled">
													<input name="txtnohakmilik" value="$infohakmilik.NoHakmilik" size="50" type="hidden" />
					              				  </td>
					            			</tr>
					            			<!--<tr>
									              <td width="1%"><span class="labelmandatory">*</span></td>
									              <td width="40%">Kod</td>
									              <td width="1%">:</td>
					              				  <td width="58%">
					                				$noLot
					              				  </td>
					            			</tr>-->
					            			<tr>
									              <td width="1%"></td>
									              <td><div align="right" class="labelinput">
									                <div align="left">No. Strata</div>
									                </div></td>
									              <td>:</td>
									     		<td>
									     			<span class="labeldisplay"><span class="labelinput">No.Bang</span>&nbsp;
									                <input name="noBangunan" type="text"  id="noBangunan"  value="$infohakmilik.noBangunan" size="3" maxlength="3"  readonly="readonly" disabled="disabled" class="disabled"  onkeyup="this.value=this.value.toUpperCase();"/>
									                <span class="labelinput">No.Ting</span>&nbsp;
									                <input name="noTingkat" type="text" id="noTingkat"  value="$infohakmilik.noTingkat" size="3" maxlength="3" readonly="readonly" disabled="disabled" class="disabled" onkeyup="this.value=this.value.toUpperCase();"/>
									                <span class="labelinput">No.Petak</span>&nbsp;
									                <input name="noPetak" type="text"  id="noPetak"  value="$infohakmilik.noPetak" size="3" maxlength="3" readonly="readonly" disabled="disabled" class="disabled" onkeyup="this.value=this.value.toUpperCase();"/>
									                </span>
									         	</td>
							                </tr>
					            			<tr>
									              <td width="1%">&nbsp;</td>
									              <td><div align="right" class="labelinput">
									                <div align="left">No Lot</div>
									                </div></td>
									              <td>:</td>
									              <td><input name="lblnegeri" value="$infohakmilik.LotKeterangan$infohakmilik.NoLot" size="15"  readonly="readonly" disabled="disabled" class="disabled" /></td>
							                </tr>	            			
					            			<tr>
									              <td width="1%">&nbsp;</td>
									              <td>&nbsp;</td>
									              <td>&nbsp;</td>
									              <td>&nbsp;</td>
							                </tr>				            			
					            			<tr>
									              <td width="1%">&nbsp;</td>
									              <td width="23%">&nbsp;</td>
									              <td width="2%">&nbsp;</td>
					              				  <td width="74%">&nbsp; </td>
					            			</tr>	
					            			<tr>
									              <td width="1%">&nbsp;</td>
									              <td width="23%">&nbsp;</td>
									              <td width="2%">&nbsp;</td>
					              				  <td width="74%">&nbsp; </td>
					            			</tr>
				            			</table>
				            			</fieldset>
				   					</td>	
								</tr>
							</table>
						</td>
						<!--Right side-->			
						<td width="50%" valign="top" >
		    				<table width="100%" border="0">
			      				<tr>
			        				<td valign="top">
				        				<fieldset><legend>PEMILIK</legend>
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
					              				  <td width="63%"><input type="checkbox" name="copy" onclick="copyAlamat('maklumat_pemohon')"  />
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
					              				  	<input type="text" name="txtnorujukan" size="30" value="$!noRujukan" onblur="this.value=this.value.toUpperCase()" >
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
					              				  	<input type="text" name="txtnama" size="30"  value="$!name" onkeyup="this.value=this.value.toUpperCase();" >
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
					              				  	<input type="text" name="txtalamat1" size="30" value="$!alamat1" onblur="this.value=this.value.toUpperCase()" >
					              				  </td>
					            			</tr>
					            			<tr>
									              <td width="1%">&nbsp;</td>
									              <td width="40%">&nbsp;</td>
									              <td width="1%">&nbsp;</td>
					              				  <td width="63%">
					              				  	<input type="text" name="txtalamat2" size="30" value="$!alamat2" onblur="this.value=this.value.toUpperCase()" >
					              				  </td>
					            			</tr>
					            			<tr>
									              <td width="1%">&nbsp;</td>
									              <td width="40%">&nbsp;</td>
									              <td width="1%">&nbsp;</td>
					              				  <td width="63%">
					              				  	<input type="text" name="txtalamat3" size="30" value="$!alamat3" onblur="this.value=this.value.toUpperCase()" >
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
													<input type="text" name="txtposkod" maxlength="5" size="5" value="$!postcode" onkeyup="validatePoskod(this,this.value);" >
					                	        	</td>
					            			</tr>	            			
					            			<tr>
									              <td width="1%"><span class="labelmandatory">*</span></td>
									              <td><div align="right" class="labelinput">
									                <div align="left">Negeri</div>
									                </div></td>
									              <td>:</td>
									              <td>$socNegeri</td>
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
						</tr>
				</table> 			
				<table width="100%">
					<tr>
		        		<td colspan=2>
		        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
		        		</td>
           			</tr>
				</table>
							
			</fieldset>
				<!--Button setting-->	
				<p align="center">
					<div align="center">
						<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="tambahMaklumatPemilik2('$permohonanInfo.idpermohonan')">
						<input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan">
						<input class="stylobutton" type="button" name="cmdX" id="cmdX" value="Kembali" onclick="skrinSenaraiHakmilikPemilik('$permohonanInfo.idpermohonan')">
	  				</div>
				</p> 
	
				<input type="hidden" name="fail" value="$infohakmilik.IdHakmilikurusan">
			  	<input type="hidden" name="id_kemaskini" value="$permohonanInfo.idpermohonan">
			   	<input type="hidden" name="pagemode" value="$pagemode">				
		</td>
	</tr>
</table>
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

<script>

function back(id){
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.command.value = "pkmaklumatseterus";
	document.${formName}.action = "";
	document.${formName}.submit();
	//doAjaxCall${formName}("pkpemilikseterus");
}

function senaraiHakmilik(i) {
	document.${formName}.id_kemaskini.value = i;
	document.${formName}.command.value = "pksemakanseterus";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function nexti6(id) {
	document.${formName}.command.value = "pksemakanseterus";
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.method="post";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function cancel() {
	//document.setup.buttonsubmit.value = "ADD";
	document.cari.reset();
	//document.forms[0].kod_agensi.focus();
}

function senaraiPermohonan(id) {
	document.${formName}.action = "?_portal_module=HTP-00&fail="+id;
	document.${formName}.submit();
}


function isxNumber(){
	var val = document.${formName}.txtposkod.value;
	if(val == null || isNaN(val)) {
   		alert("Sila masukkan nombor yang betul.");
   		document.${formName}.txtposkod.value = "";
   		document.${formName}.txtposkod.focus();
   		return;
	}
	
}

 function enableField(){
 	//alert(document.${formName}.sochakmilik.value);
 	//if(document.${formName}.sochakmilik.value==1)
	document.${formName}.txtnohakmilik.value = document.${formName}.sochakmilik.value;
}

function doChangeDaerah(id){

	if(document.${formName}.socDaerah.value=="")
 		return;       			
	document.${formName}.id_kemaskini.value = id;
	doAjaxCall${formName}("pilihMukim");
}

function doChangeDaerah123(id,x){
	if(document.${formName}.socDaerah1.value=="")
 		return; 
 		
 	//alert(document.${formName}.socDaerah.value);      			
	document.${formName}.id_kemaskini.value = id;
	doAjaxCall${formName}("pilihMukim");
}


function tambahMaklumatPemilik2(id) {
	
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
	
    if ( document.${formName}.txtposkod.value == "" ) { 
    	alert('Sila masukkan maklumat poskod terlebih dahulu.');
    	document.${formName}.txtposkod.focus(); 
    	return; 
    } 
	*/
   	/*if ( document.${formName}.socDaerahalamat.value == "" ) { 
    	alert('Sila pilih maklumat daerah terlebih dahulu.');
    	document.${formName}.socDaerahalamat.focus(); 
    	return; 
    }*/
    
    if ( document.${formName}.socDaerah.value == "" ) { 
    	alert('Sila pilih maklumat daerah terlebih dahulu.');
    	document.${formName}.socDaerah.focus(); 
    	return; 
    }
	//document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 1;
	doAjaxCall${formName}("pkpemiliktambah2");
	
}
//document.forms[0].no_fail.focus(); 
</script>