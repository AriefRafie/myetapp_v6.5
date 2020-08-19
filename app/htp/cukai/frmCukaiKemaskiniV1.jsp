<!-- frmCukaiKemaskiniV1.jsp -->
<!-- CL-02-021 -->
<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>
<table width="98%" border="0">
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			<fieldset>
			<legend>KEMASKINI CUKAI</legend> 
		#if ($pagemode == "viewCukaiV1" || $ResultSimpan == "kemaskini" )
			#foreach ( $kemaskini in $cukaikemaskini )
				#set ($idNegeri = $kemaskini.idNegeri)
				#set ($idDaerah = $kemaskini.idDaerah)
				#set ($idMukim = $kemaskini.idMukim)
				#set ($idJenisHakmilik = $kemaskini.idJenisHakmilik)
				#set ($noHakmilik = $kemaskini.noHakmilik)
				#set ($noFail = $kemaskini.noFail)
				#set ($noFailPTG = $kemaskini.noFailPTG)
				#set ($kegunaanTanah = $kemaskini.kegunaanTanah)
				#set ($caraPerolehi = $kemaskini.caraPerolehi)
				#set ($noFailPTD = $kemaskini.noFailPTD)
				#set ($pembayarCukai = $kemaskini.pembayarCukai)
				#set ($tarikhDaftar = $kemaskini.tarikhDaftar)
				#set ($Luas = $kemaskini.Luas)
				#set ($cukaiTerkini = $kemaskini.cukaiTerkini)
				#set ($cukaiTaliAir = $kemaskini.cukaiTaliAir)
				#set ($cukaiParit = $kemaskini.cukaiParit)
				#set ($Denda = $kemaskini.Denda)
				#set ($bayaranLain = $kemaskini.bayaranLain)
				#set ($NoLot = $kemaskini.NoLot)
				#set ($Cukai = $kemaskini.Cukai)
				#set ($pengecualian = $kemaskini.pengecualian)
				#set ($pengurangan = $kemaskini.pengurangan)
				#set ($tunggakkan = $kemaskini.tunggakkan)
				#set ($lebihan = $kemaskini.lebihan)
				#set ($statusBayaran = $kemaskini.statusBayaran)
			    #set ($tMasuk = $kemaskini.tMasuk)
			#end
				<fieldset>
			<table width="100%">
	       		<tr>
	            	<td width="50%" valign="top">
	                	<table width="100%" border="0">
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%" valign="top">
	                            	<div align="left">
	                            		<span class="labelinput">Kementerian</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" valign="top">:</td>
	                   			<td width="68%" class="pautanms" >
	 								$!selectKementerian
	  							</td>
	                		</tr>
	                     	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Fail</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<input type="text" name="txtNoFailSek" size="40" value="$!noFail" readonly class="disabled"/>
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Fail PTG</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<input type="text" name="txtNoFailPTG" size="40" value="$!noFailPTG" readonly class="disabled"/>
	  							</td>
	                		</tr>
	                    	<!----> <tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%" valign="top">
	                            	<div align="left">
	                            		<span class="labelinput">Kegunaan Tanah</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" valign="top">:</td>
	                   			<td width="68%" class="pautanms" >
									<textarea name="txtKegunaanTanah" cols="37" rows="4" id="txtKegunaanTanah" style="text-transform:uppercase;" class="disabled" onblur="this.value=this.value.toUpperCase();">$!kegunaanTanah</textarea>

	  							</td>
	                		</tr> 
	                		
	                 	</table>
	           		</td>
	                        	
	                <td valign="top">
	               		<table width="100%">
	                   	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Agensi</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								$!selectAgensi
	  							</td>
	                		</tr>
	              			
	            			<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%" >
	                            	<div align="left">
	                            		<span class="labelinput">Cara Perolehan</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<input type="text" name="txtCaraPerolehi" size="40" value="$!caraPerolehi" readonly class="disabled"/>
	  							</td>
	                		</tr>
	
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Fail PTD</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<input type="text" name="txtNoFailLain" id="txtNoFailPTD" size="40" value="$!noFailPTD" readonly class="disabled"">
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">&nbsp;</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >&nbsp;</td>
	                   			<td width="68%" class="pautanms" >
	  							</td>
	                		</tr>
	                	</table>
	                </td>
	       		</tr>                    
			</table>
		</fieldset>
		
			<fieldset>
					<table width="100%">
	       		<tr>
	            	<td width="50%" valign="top">
	                	<table width="100%" border="0">
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Negeri</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
	 								$!selectNegeri	<input type="hidden" name="idNegeri" value="$!idNegeri" />
	  							</td>
	                		</tr>
	                     	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Daerah</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
	 								$!selectDaerah <input type="hidden" name="idDaerah" value="$!idDaerah" />
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Bandar/Pekan/Mukim</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
	 								$!selectMukim <input type="hidden" name="idMukim" value="$!idMukim" />
	  							</td>
	                		</tr>
	
	                 	</table>
	           		</td>
	                        	
	                <td valign="top">
	               		<table width="100%">
	                   	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Jenis Hakmilik</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
	 								$!selectJenisHakmilik <input type="hidden" name="idJenisHakmilik" value="$!idJenisHakmilik" />
	  							</td>
	                		</tr>
	              			
	            			<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Hakmilik</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
	      							<input name="txtNoHakmilik" type="text" id="txtNoHakmilik" maxlength="50" size="20" onkeyup="this.value=this.value.toUpperCase();" value="$!noHakmilik" readonly class="disabled"/>
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
	      							<span class="labelinput">No.Bang</span>&nbsp;<input name="txtNoBangunan" type="text" class="disabled" id="txtNoBangunan"  value="$!txtNoBangunan" size="3" maxlength="3" readonly onkeyup="this.value=this.value.toUpperCase();"/>
									<span class="labelinput">No.Ting</span>&nbsp;<input name="txtNoTingkat" type="text" class="disabled" id="txtNoTingkat"  value="$!txtNoTingkat" size="3" maxlength="3" readonly onkeyup="this.value=this.value.toUpperCase();"/>
									<span class="labelinput">No.Petak</span>&nbsp;<input name="txtNoPetak" type="text" class="disabled" id="txtNoPetak"  value="$!txtNoPetak" size="3" maxlength="3" readonly onkeyup="this.value=this.value.toUpperCase();"/>
									
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Kod</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
	 								$!selectLot
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Lot/PT</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
	      							<input name="txtNoLot" type="text" id="txtNoLot" size="20" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" value="$!NoLot" readonly class="disabled"/>
	  							</td>
	                		</tr> 
	                		
	                	</table>
	                </td>
	       		</tr>                    
			</table>
		</fieldset>	
		
				<fieldset>
					<table width="100%">
			       		<tr>
			            	<td width="50%" valign="top">
			                	<table width="100%" border="0">
			                    	<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Tarikh Daftar Hakmilik</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
											<input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$!tarikhDaftar" size="20" maxlength="10" readonly class="disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhDaftar);" />
			      									
			        					</td>
			                		</tr>
			                   		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Unit/Luas</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
				                   			$!selectLuas
									   	</td>
			                		</tr> 			
			                   		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Luas Bersamaan</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtLuas" id="txtLuas" size="20" maxlength="40" onkeyup="validateCurrency(this,this.value);" value="$!Luas" readonly class="disabled" >(Hektar)
										</td>
			                		</tr>			
			                		
			                   		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Cukai Tahun Pertama (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
              								<input type="text" name="txtCukaiPertama" id="txtCukaiPertama" value="$Cukai" size="20" maxlength="9" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled"/>
			        					</td>
			                		</tr>	
			                		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Status Bayaran</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
			                   				<!--
              								<input type="text" name="txtStatusBayaran" id="txtStatusBayaran" value="$!statusBayaran" size="20" maxlength="60" readonly class="disabled">
			        					-->
			        						#set($checkedBayar = "")
					        				#set($checkedBelum = "")
											#if($statusBayar.equals('S'))
					          					#set($checkedBayar = "checked")
					         					#set($checkedBelum = "")
					            
									        #elseif($statusBayar.equals('B'))
									         	#set($checkedBelum ="checked")
									         	#set($checkedBayar ="")
									
									        #end
									    	<input type="radio" name="txtStatusBayaran" value="S" $checkedBayar disabled />TELAH DIBAYAR
									 		<input type="radio" name="txtStatusBayaran" value="B" $checkedBelum disabled />BELUM DIBAYAR
			        					
			        					</td>
			                		</tr>   	
	                				<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Maklumat Bayaran(Baucer/EFT)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
              								<input type="text" name="txtMaklumat" id="txtMaklumat" value="$!hakmilik.getHakmilikCukai().getBayaranCukai().getNoRujukan()" size="20" maxlength="60" readonly class="disabled">
			        					</td>
			                		</tr> 			                				                		
			                    	<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Tarikh Bayaran</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
											<input name="txdTarikhBayar" type="text" id="txdTarikhBayar" value="$!hakmilik.getHakmilikCukai().getBayaranCukai().getTarikhFormat()" size="20" maxlength="10" readonly class="disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhBayar);" />			      									
			        					</td>
			                		</tr>			                				                		                			                		                				                		
			                		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">No. Resit</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
              								<input type="text" name="txtNoResit" id="txtNoResit" value="$!noResit" size="20" maxlength="60" readonly class="disabled">
			        					</td>
			                		</tr>   			                		
			                    	<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Tarikh Resit</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
											<input name="txdTarikhResit" type="text" id="txdTarikhResit" value="$!tarikhResit" size="20" maxlength="10" readonly class="disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhResit);" />			      									
			        					</td>
			                		</tr>	
			                 	</table>
			           		</td>
			                        	
			                <td valign="top">
			               		<table width="100%">
			                   		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Cukai Terkini (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
      										<input type="text" name="txtCukaiTerkini" id="txtCukaiTerkini" value="$!cukaiTerkini" size="20" maxlength="16" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled">
			      						</td>
			                		</tr>
			                   		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Cukai Tali Air (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
     										<input type="text" name="txtCukaiTaliAir" id="txtCukaiTaliAir" value="$!cukaiTaliAir" size="20" maxlength="16" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled">
									   	</td>
			                		</tr>
			                   		<tr>
			  						<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Cukai Parit (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
		      								<input type="text" name="txtCukaiParit" id="txtCukaiParit" value="$!cukaiParit" size="20" maxlength="16" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled" >		
							   			</td>
			                		</tr>
			  						<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Denda (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtDenda" id="txtDenda" value="$!Denda" size="20" maxlength="60" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled">
							   			</td>
			                		</tr> 
                		
			                   		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Bayaran Lain (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtBayaranLain" id="txtBayaranLain" value="$!bayaranLain" size="20" maxlength="60" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled">

									   	</td>
			                		</tr> 			               		
			                   		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Tunggakkan (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
              								<input type="text" name="txtTunggakkan" id="txtTunggakkan" value="$!tunggakkan" size="20" maxlength="60" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled">
			        					</td>
			                		</tr>
			                   		 <tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Pengurangan (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtPengurangan" id="txtPengurangan" value="$!pengurangan" size="20" maxlength="60" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled">
			      						</td>
			                		</tr> 
			                   		<tr>
			  							<td width="1%">
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Pengecualian (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtPengecualian" id="txtPengecualian" value="$!pengecualian" size="20" maxlength="60" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled">
			        					</td>
			                		</tr>

									<!--
			                		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Lebihan (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtLebihan" id="txtLebihan" value="$!lebihan" size="20" maxlength="60" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled">
			        					</td>
			                		</tr> 
			                		-->
			                		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Cukai Kena Bayar (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtKenaBayar" id="txtKenaBayar" value="$!kenaBayar" size="20" maxlength="60" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled">
			        					</td>
			                		</tr>           			
                		
			                	</table>
			                </td>
			       		</tr>   
			       		                 
					</table>


				</fieldset>
				
	           	<table width="100%">
	           		<tr>
	           		<!-- <td>
	           		</td> -->
		           		<td colspan="2" align="center">
						<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini()">
						<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()">
						</td>
		       		</tr>                    
				</table>
				
  <input type="hidden" name="idKementerian" value="$idKementerian" />
  <input type="hidden" name="idjenishakmilik" value="$idjenishakmilik">
  <input type="hidden" name="tMasuk" value="$tMasuk" />
  <input type="hidden" name="idHakmilikCukai" value="$idHakmilikCukai" />
  <input type="hidden" name="idHakmilik" value="$idHakmilik">
  <input type="hidden" name="noHakmilik" value="$noHakmilik">
  <input type="hidden" name="command1" value="$command1">
  <input type="hidden" name="pagemode" value="$pagemode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">
			
		##EDIT CUKAI
		#elseif ($pagemode == "editCukai")
			#foreach ( $kemaskini in $cukaikemaskini )
				#set ($idNegeri = $kemaskini.idNegeri)
				#set ($idDaerah = $kemaskini.idDaerah)
				#set ($idMukim = $kemaskini.idMukim)
				#set ($idJenisHakmilik = $kemaskini.idJenisHakmilik)
				#set ($noHakmilik = $kemaskini.noHakmilik)
				#set ($noFail = $kemaskini.noFail)
				#set ($noFailPTG = $kemaskini.noFailPTG)
				#set ($kegunaanTanah = $kemaskini.kegunaanTanah)
				#set ($caraPerolehi = $kemaskini.caraPerolehi)
				#set ($noFailPTD = $kemaskini.noFailPTD)
				#set ($pembayarCukai = $kemaskini.pembayarCukai)
				#set ($tarikhDaftar = $kemaskini.tarikhDaftar)
				#set ($Luas = $kemaskini.Luas)
				#set ($cukaiTerkini = $kemaskini.cukaiTerkini)
				#set ($cukaiTaliAir = $kemaskini.cukaiTaliAir)
				#set ($cukaiParit = $kemaskini.cukaiParit)
				#set ($Denda = $kemaskini.Denda)
				#set ($bayaranLain = $kemaskini.bayaranLain)
				#set ($NoLot = $kemaskini.NoLot)
				#set ($Cukai = $kemaskini.Cukai)
				#set ($pengecualian = $kemaskini.pengecualian)
				#set ($pengurangan = $kemaskini.pengurangan)
				#set ($tunggakkan = $kemaskini.tunggakkan)
				#set ($lebihan = $kemaskini.lebihan)
				#set ($statusBayaran = $kemaskini.statusBayaran)
			    #set ($tMasuk = $kemaskini.tMasuk)
			#end
			
			#if ( $SimpanStatus == "success" )
			    <table width="100%" border="0">
			        <tr>
			            <td colspan="2" >
			            <font color="blue">
			            <b>
			            #if ( $ResultSimpan == "baru" )
			                Maklumat telah berjaya disimpan.
			            #elseif ($ResultSimpan == "kemaskini" )
			                Maklumat telah berjaya dikemaskini.
			            #end
			            </b>
			            </font>
			            </td>
			        </tr>
			    </table>
			#end
			
				<fieldset>
			<table width="100%">
	       		<tr>
	            	<td width="50%" valign="top">
	                	<table width="100%" border="0">
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%" valign="top">
	                            	<div align="left">
	                            		<span class="labelinput">Kementerian</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" valign="top">:</td>
	                   			<td width="68%" class="pautanms" >
	 								$!selectKementerian
	  							</td>
	                		</tr>
	                     	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Fail</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<input type="text" name="txtNoFailSek" size="40" value="$!noFail" readonly class="disabled"/>
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Fail PTG</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<input type="text" name="txtNoFailPTG" size="40" value="$!noFailPTG" readonly class="disabled"/>
	  							</td>
	                		</tr>
	                    	<!----> <tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%" valign="top">
	                            	<div align="left">
	                            		<span class="labelinput">Kegunaan Tanah</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" valign="top">:</td>
	                   			<td width="68%" class="pautanms" >
									<textarea name="txtKegunaanTanah" cols="37" rows="4" id="txtKegunaanTanah" style="text-transform:uppercase;" class="disabled" onblur="this.value=this.value.toUpperCase();">$!kegunaanTanah</textarea>

	  							</td>
	                		</tr> 
	                		
	                 	</table>
	           		</td>
	                        	
	                <td valign="top">
	               		<table width="100%">
	                   	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Agensi</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								$!selectAgensi
	  							</td>
	                		</tr>
	              			
	            			<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%" >
	                            	<div align="left">
	                            		<span class="labelinput">Cara Perolehan</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<input type="text" name="txtCaraPerolehi" size="40" value="$!caraPerolehi" readonly class="disabled"/>
	  							</td>
	                		</tr>
	
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Fail PTD</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<input type="text" name="txtNoFailLain" id="txtNoFailPTD" size="40" value="$!noFailPTD" readonly class="disabled"">
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">&nbsp;</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >&nbsp;</td>
	                   			<td width="68%" class="pautanms" >
	  							</td>
	                		</tr>
	                	</table>
	                </td>
	       		</tr>                    
			</table>
		</fieldset>
		
			<fieldset>
					<table width="100%">
	       		<tr>
	            	<td width="50%" valign="top">
	                	<table width="100%" border="0">
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Negeri</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
	 								$!selectNegeri	<input type="hidden" name="idNegeri" value="$!idNegeri" />
	  							</td>
	                		</tr>
	                     	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Daerah</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
	 								$!selectDaerah <input type="hidden" name="idDaerah" value="$!idDaerah" />
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Bandar/Pekan/Mukim</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
	 								$!selectMukim <input type="hidden" name="idMukim" value="$!idMukim" />
	  							</td>
	                		</tr>
	
	                 	</table>
	           		</td>
	                        	
	                <td valign="top">
	               		<table width="100%">
	                   	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Jenis Hakmilik</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
	 								$!selectJenisHakmilik <input type="hidden" name="idJenisHakmilik" value="$!idJenisHakmilik" />
	  							</td>
	                		</tr>
	              			
	            			<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Hakmilik</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
	      							<input name="txtNoHakmilik" type="text" id="txtNoHakmilik" maxlength="50" size="20" onkeyup="this.value=this.value.toUpperCase();" value="$!noHakmilik" readonly class="disabled"/>
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
	      							<span class="labelinput">No.Bang</span>&nbsp;<input name="txtNoBangunan" type="text" class="disabled" id="txtNoBangunan"  value="$!txtNoBangunan" size="3" maxlength="3" readonly onkeyup="this.value=this.value.toUpperCase();"/>
									<span class="labelinput">No.Ting</span>&nbsp;<input name="txtNoTingkat" type="text" class="disabled" id="txtNoTingkat"  value="$!txtNoTingkat" size="3" maxlength="3" readonly onkeyup="this.value=this.value.toUpperCase();"/>
									<span class="labelinput">No.Petak</span>&nbsp;<input name="txtNoPetak" type="text" class="disabled" id="txtNoPetak"  value="$!txtNoPetak" size="3" maxlength="3" readonly onkeyup="this.value=this.value.toUpperCase();"/>
									
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Kod</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
	 								$!selectLot
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Lot/PT</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
	      							<input name="txtNoLot" type="text" id="txtNoLot" size="20" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" value="$!NoLot" readonly class="disabled"/>
	  							</td>
	                		</tr> 
	                		
	                	</table>
	                </td>
	       		</tr>                    
			</table>
		</fieldset>	
		
				<fieldset>
					<table width="100%">
			       		<tr>
			            	<td width="50%" valign="top">
			                	<table width="100%" border="0">
			                    	<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Tarikh Daftar Hakmilik</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
											<input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$!tarikhDaftar" size="20" maxlength="10" readonly class="disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhDaftar);" />
			      							#if ($mode == 'update') 
			      								<a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');" > <img border="0" src="../img/calendar.gif"/>
			      								 <span class="pautanms">dd/mm/yyyy </span> </a>
			    							#end 			
			        					</td>
			                		</tr>
			                   		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Unit/Luas</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
				                   			$!selectLuas
									   	</td>
			                		</tr> 			
			                   		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Luas Bersamaan</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtLuas" id="txtLuas" size="20" maxlength="40" onkeyup="validateCurrency(this,this.value);" value="$!Luas" readonly class="disabled" >(Hektar)
										</td>
			                		</tr>
			                   		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Cukai Tahun Pertama (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
              								<input type="text" name="txtCukaiPertama" id="txtCukaiPertama" value="$Cukai" size="20" maxlength="9" onblur="validateCurrency(this,this.value);" onkeyup="validateNumber(this,this.value);" readonly class="disabled"/>
			        					</td>
			                		</tr>			                					                			                		                				                		

			                		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Status Bayaran</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
              								<!--<input type="text" name="txtStatusBayaran" id="txtStatusBayaran" value="$!statusBayaran" size="20" maxlength="60" onblur="this.value=this.value.toUpperCase();">
			        						S(SUDAH),B(BELUM)-->
          									<input type="hidden" name="XtxtidStatusBayaran" id="txtidStatusBayaran" value="S" $mode>
										      <!-- Dibuat oleh Rosli pada 12/10/2011 -->
									 		#set($checkedBayar = "")
					        				#set($checkedBelum = "")
											#if($statusBayar.equals('S'))
					          					#set($checkedBayar = "checked")
					         					#set($checkedBelum = "")
					            
									        #elseif($statusBayar.equals('B'))
									         	#set($checkedBelum ="checked")
									         	#set($checkedBayar ="")
									
									        #end
									    	<input type="radio" name="txtStatusBayaran" value="S" $checkedBayar disabled />TELAH DIBAYAR
									 		<input type="radio" name="txtStatusBayaran" value="B" $checkedBelum disabled />BELUM DIBAYAR
									 		
									    	<input type="hidden" name="txtidStatusBayaran" value="$statusBayar">
							        	</td>
			                		</tr> 			                		
               						<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Maklumat Bayaran(Baucer/EFT)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
              								<input type="text" name="txtMaklumat" id="txtMaklumat" value="$!hakmilik.getHakmilikCukai().getBayaranCukai().getNoRujukan()" size="20" maxlength="60" readonly class="disabled">
			        					</td>
			                		</tr> 			                				                		
			                    	<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Tarikh Bayaran</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
											<input name="txdTarikhBayar" type="text" id="txdTarikhBayar" value="$!hakmilik.getHakmilikCukai().getBayaranCukai().getTarikhFormat()" size="20" maxlength="10" readonly class="disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhBayar);" />			      									
			        						<!-- <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhBayar',false,'dmy');" style="display:$Style2"> <span class="pautanms">dd/mm/yyyy </span> -->
			        					</td>
			                		</tr>			                				                		                			                		                				                		
			                		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">No. Resit</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
              								<input type="text" name="txtNoResit" id="txtNoResit" value="$!noResit" size="20" maxlength="60" $mode">
			        					</td>
			                		</tr>   			                		
			                    	<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Tarikh Resit</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
											<input name="txdTarikhResit" type="text" id="txdTarikhResit" value="$!tarikhResit" size="20" maxlength="10" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhResit);" $mode />			      									
			        						<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhResit',false,'dmy');" style="display:$Style2"> <span class="pautanms">dd/mm/yyyy </span>
			        					</td>
			                		</tr>	
			                 	</table>
			           		</td>
			                        	
			                <td valign="top">
			               		<table width="100%">

			                   		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Cukai Terkini (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
      										<input type="text" name="txtCukaiTerkini" id="txtCukaiTerkini" value="$!cukaiTerkini" size="20" maxlength="16" onblur="Check();validateCurrency(this,this.value);addText(this);kirakira(1);" $mode>
			      						</td>
			                		</tr>
			                   		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Cukai Tali Air (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
     										<input type="text" name="txtCukaiTaliAir" id="txtCukaiTaliAir" value="$!cukaiTaliAir" size="20" maxlength="16" onblur="Check();validateCurrency(this,this.value);addText(this);kirakira(1);" $mode>
									   	</td>
			                		</tr>
			                   		<tr>
			  						<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update') #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Cukai Parit (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
		      								<input type="text" name="txtCukaiParit" id="txtCukaiParit" value="$!cukaiParit" size="20" maxlength="16"  onblur="Check();validateCurrency(this,this.value);addText(this);kirakira(1);" $mode>		
							   			</td>
			                		</tr>
			  						<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Denda (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtDenda" id="txtDenda" value="$!Denda" size="20" maxlength="60"  onblur="Check();validateCurrency(this,this.value);addText(this);kirakira(1);" $mode>
							   			</td>
			                		</tr>                 		
			                   		<tr>
			  							<td width="1%"  >
			  								<span class="labelmandatory">#if ($mode == 'update')  #end </span>
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Bayaran Lain (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtBayaranLain" id="txtBayaranLain" value="$!bayaranLain" size="20" maxlength="60"  onblur="Check();validateCurrency(this,this.value);addText(this);kirakira(1);" $mode>

									   	</td>
			                		</tr> 		
			                   		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Tunggakkan (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="pautanms1" >
              								<input type="text" name="txtTunggakkan" id="txtTunggakkan" value="$!tunggakkan" size="20" maxlength="60"  onblur="Check();validateCurrency(this,this.value);addText(this);kirakira(1);" $mode>
			        					</td>
			                		</tr>
			                		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Pengurangan (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtPengurangan" id="txtPengurangan" value="$!pengurangan" size="20" maxlength="60"  onblur="Check();validateCurrency(this,this.value);addText(this);kirakira(1);" $mode>
			      						</td>
			                		</tr>			                			                		
			                   		<tr>
			  							<td width="1%">
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Pengecualian (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtPengecualian" id="txtPengecualian" value="$!pengecualian" size="20" maxlength="60"  onblur="Check();validateCurrency(this,this.value);addText(this);kirakira(1);" $mode>
			        					</td>
			                		</tr>
			                		
			                		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Lebihan (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtLebihan" id="txtLebihan" value="$!lebihan" size="20" maxlength="60"  onblur="Check();validateCurrency(this,this.value);addText(this);kirakira(1);" $mode>
			        					</td>
			                		</tr>
             						<!---->
			                		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">Cukai Kena Bayar (RM)</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
              								<input type="text" name="txtKenaBayar" id="txtKenaBayar" value="$!kenaBayar" size="20" maxlength="60" onblur="validateCurrency(this,this.value);" readonly class="disabled">
			        					</td>
			                		</tr>                 		
			                	</table>
			                </td>
			       		</tr>   
			       		                 
					</table>


				</fieldset>						
			
	           	<table width="100%">
	           		<tr>
		           		<td colspan="2" align="center">
						<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;
						<input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Batal" >&nbsp;
						<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()">
						</td>
		       		</tr>                    
				</table>			

			  <input type="hidden" name="cukaiTerkini" value="$cukaiTerkini" />
			  <input type="hidden" name="cukaiTaliAir" value="$cukaiTaliAir" />
			  <input type="hidden" name="cukaiParit" value="$cukaiParit" />
			  <input type="hidden" name="idHakmilikCukai" value="$idHakmilikCukai" />
			  <input type="hidden" name="idHC" value="$idHakmilikCukai" />
			  <input type="hidden" name="tMasuk" value="$tMasuk" />
			  <input type="hidden" name="idKementerian" value="$idKementerian" />
			  <input type="hidden" name="idjenishakmilik" value="$idjenishakmilik">
			  <input type="hidden" name="idHakmilik" value="$idHakmilik">
			  <input type="hidden" name="noHakmilik" value="$noHakmilik">
			  <input type="hidden" name="command1" value="$command1">
			  <input type="hidden" name="pagemode" value="$pagemode">
			  <input type="hidden" name="style1" value="$Style1">
			  <input type="hidden" name="style2" value="$Style2">				
				
		#end
			</fieldset>
		</td>
	</tr>
</table>
		
<script>
function validateNumber(elmnt,content) {
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function validateCurrency(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function addText(field) {
	if(field.value==""){
		field.defaultValue="";}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function Kembali1() {
	document.${formName}.action = "";
	document.${formName}.command1.value = "kemaskiniCukai";
	document.${formName}.pagemode.value = "viewCukai";
	document.${formName}.submit();
}
	function Kembali() {
		document.${formName}.action = "";
		document.${formName}.command1.value = "";
		document.${formName}.submit();
	}
function Batal() {
	document.${formName}.action = "";
	document.${formName}.command1.value = "";
	document.${formName}.submit();
}
function Kemaskini() {
	document.${formName}.action = "";
	document.${formName}.pagemode.value = "editCukai";
	document.${formName}.command1.value = "kemaskiniCukai";
	document.${formName}.submit();
}

	function Check() {
		var a = parseFloat(document.${formName}.txtCukaiTerkini.value);
		var b = parseFloat(document.${formName}.txtCukaiTaliAir.value);
		var c = parseFloat(document.${formName}.txtCukaiParit.value);
		var x = parseFloat(document.${formName}.cukaiTerkini.value);
		var y = parseFloat(document.${formName}.cukaiTaliAir.value);
		var z = parseFloat(document.${formName}.cukaiParit.value);
		
		if(a > x || a < x ){
	  		document.${formName}.idHakmilikCukai.value = "0";
		}
		if(b > y || b < y ){
	  		document.${formName}.idHakmilikCukai.value = "0";
		}
		if(c > z || c < z ){
	  		document.${formName}.idHakmilikCukai.value = "0";
		}
	}
	
	function Simpan() {
	
		if(document.${formName}.txtCukaiTerkini.value == "0.00"){
			alert('Sila masukkan " Jumlah Cukai Terkini " terlebih dahulu.');
	  		document.${formName}.txtCukaiTerkini.focus(); 
			return; 
		}
		if ( !window.confirm("Anda Pasti?") ) return;
		document.${formName}.command1.value = "kemaskiniCukai";
		document.${formName}.pagemode.value = "simpan";
		document.${formName}.action = "";
		document.${formName}.submit();
	}


</script>
