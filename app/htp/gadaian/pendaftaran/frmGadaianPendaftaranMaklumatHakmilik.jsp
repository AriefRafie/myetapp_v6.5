<style type="text/css">
<!--
	.pautanms{color: #0000FF}
-->
</style>

#foreach ( $penHamilik in $PenHamilik )
	#set ($TajukFail = $penHamilik.TajukFail)
    #set ($NoFail = $penHamilik.NoFail)
    #set ($NoRujukanLain = $penHamilik.NoRujukanLain)
    #set ($IdNegeri = $penHamilik.IdNegeri)
	#set ($NoHakmilik = $penHamilik.NoHakmilik)
    #set ($NoLot = $penHamilik.NoLot)
    #set ($TarikhMula = $penHamilik.TarikhMula)
    #set ($Cukai = $penHamilik.Cukai)
    #set ($Lokasi = $penHamilik.Lokasi)
    #set ($NoPelan = $penHamilik.NoPelan)
    #set ($Syarat = $penHamilik.Syarat)
    #set ($TarikhLuput = $penHamilik.TarikhLuput)
    #set ($CukaiTerkini = $penHamilik.CukaiTerkini)
    #set ($Luas = $penHamilik.Luas)
    #set ($TarikhRizab = $penHamilik.TarikhRizab)
    #set ($NoRizab = $penHamilik.NoRizab)
    #set ($Noptk = $penHamilik.Noptk)
    #set ($NoSyit = $penHamilik.NoSyit)
    #set ($Sekatan = $penHamilik.Sekatan)
    #set ($IdHakmilikurusan = $penHamilik.IdHakmilikurusan)
    #set($TarikhLepasGadai = $penHamilik.TarikhLepasGadai)
#end

#set ($btnName = "value='Kosongkan'")
#if ($IdHakmilikurusan != "")
	#set ($btnName = "value='Batal'")
#end

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
    </tr>
	<tr>
		<td>
			<fieldset><legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
			#parse ("app/htp/gadaian/frmGadaianInfoAjax.jsp")
			</fieldset>
		</td>
    </tr>

	<tr>
		<td>
			<fieldset><legend><strong>MAKLUMAT HAKMILIK</strong></legend>
				<table width="100%" >
		     		<tr>
		            	<td width="50%" valign="top">
		                	<table width="100%" border="0">
		                    	<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory">#if($mode == 'baru' || $mode == 'kemaskini')* #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Negeri</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		 								$selectNegeri
		 								<input type="hidden" name="idNegeri" value="$IdNegeri" />
		  							</td>
		                		</tr>
		                     	<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory">#if($mode == 'baru' || $mode == 'kemaskini')* #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Daerah</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		 								$selectDaerah
		  							</td>
		                		</tr>
		                    	<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory">#if($mode == 'baru' || $mode == 'kemaskini')* #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Bandar/Pekan/Mukim</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		 								$selectMukim
		  							</td>
		                		</tr>
		                		<!-- -->
                     			<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory">#if($mode == 'baru' || $mode == 'kemaskini')* #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Tarikh Terima Hakmilik</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		 								<input type="text" size="11" maxlength="10" name="txdTarikhTerima"  id="txdTarikhTerima" value="$TarikhMula"  class="$disabled" $readonly onblur="check_date(this)"/>
		    							
		  							</td>
		                		</tr>
		                     	<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory"></span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Tarikh Luput Hakmilik</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		 								<input type="text" size="11" maxlength="10" name="txdTarikhLuput" id="txdTarikhLuput" value="$TarikhLuput" class="$disabled" $readonly onblur="check_date(this)" />
      <img src="../img/calendar.gif" alt="calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTarikhLuput',false,'dmy');" />
		  							</td>
		                		</tr>
		                    	<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory"></span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Cukai Tahun Pertama (RM)</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		 								<input type="text" maxlength="9" size="17" name="txtCukaiPertama" id="txtCukaiPertama" onkeyup="validateCurrency(this,this.value);" value="$Cukai" class="$disabled" $readonly />
		  							</td>
		                		</tr>
		                    	<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory"></span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Cukai Semasa (RM)</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		 								<input type="text" maxlength="9" size="17" name="txtCukaiSemasa" id="txtCukaiSemasa" onkeyup="validateCurrency(this,this.value);" value="$CukaiTerkini" class="$disabled" $readonly />
		  							</td>
		                		</tr>
		                   		<tr>
		  						<td width="1%"  >
		  								<span class="labelmandatory"></span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Lokasi</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
										<input type="text" maxlength="60" name="txtLokasi" id="txtLokasi" value="$!Lokasi" class="$disabled" $readonly onkeyup="this.value=this.value.toUpperCase();" />	
						   			</td>
		                		</tr>
								<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory"></span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Unit Luas</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" > 
		                   			#if($pagemode == 'kemaskini')	 
		                   				
										#parse("app/htp/unit_luasV1.jsp") 
										
									#else
									
										#parse("app/htp/unit_luas.jsp") 	
																	
									#end	 <!-- $selectLuas-->
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
		                        	##set ($disabled = $modeSoc)
		                        	##set ($readonly =$classDis)
		                        	
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
										#if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6' || $socLuas == '9')
											<input type="text" size="8" maxlength="8" name="txtLuas1" id="txtLuas1" value="$!txtLuas1" class="$disabled" $readonly onBlur="kiraLuas('$socLuas')"onkeyup="validateNumber(this,this.value);" />
										#elseif($socLuas == '8' || $socLuas == '4')
											<input type="text" size="8" maxlength="8" name="txtLuas2" id="txtLuas2" class="$disabled" $readonly onkeyup="validateNumber(this,this.value);" />
											<input type="text" size="8" maxlength="8" name="txtLuas3" id="txtLuas3" class="$disabled" $readonly onkeyup="validateNumber(this,this.value);" />
											<input type="text" size="8" maxlength="8" name="txtLuas4" id="txtLuas4" class="$disabled" $readonly onBlur="kiraLuas('$socLuas')" onkeyup="validateNumber(this,this.value);" />
										#elseif($socLuas == '7')
											<input type="text" size="8" maxlength="8" name="txtLuas5" id="txtLuas5" class="$disabled" $readonly onkeyup="validateNumber(this,this.value);" />
											<input type="text" size="8" maxlength="8" name="txtLuas6" id="txtLuas6" class="$disabled" $readonly onBlur="kiraLuas('$socLuas')" onkeyup="validateNumber(this,this.value);" />
										#else
											<input type="text" size="8" maxlength="8" name="txtLuas1" id="txtLuas1" value="$!txtLuas1" class="$disabled" $readonly onBlur="kiraLuas('$socLuas')" onkeyup="validateNumber(this,this.value);"/>
										#end 
											<input type="hidden" name="txtLuasGabung" value="$!txtLuas1">
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
										<!--<input type="text" size="20" id="txtLuas" name="txtLuas" value="$!txtLuas" $readonly class="disabled" onkeyup="this.value=this.value.toUpperCase();" />   -->				
										<input type="text" size="20" id="txtLuas" name="txtLuas" value="$!txtLuas" $readonly class="disabled" onkeyup="this.value=this.value.toUpperCase();" />   				
		          				        (Hektar)  							
									</td>
		                		</tr>		                		
			            			<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%" valign="top" >
			                            	<div align="left">
			                            		<span class="labelinput">Syarat Nyata</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" valign="top" >:</td>
			                   			<td width="68%" class="labeldisplay">
			        						<textarea cols="27" rows="5" name="txtSyarat" id="txtSyarat" class="$disabled" $readonly style="text-transform:uppercase;">$!Syarat</textarea>
			  							</td>
			                		</tr>			
		                 	</table>
		           		</td>
		                        	
		                <td width="50%" valign="top">
		               		<table width="100%">
		                   	<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory">#if($mode == 'baru' || $mode == 'kemaskini')* #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Jenis Hakmilik</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		 								$!selectJenisHakmilik
		  							</td>
		                		</tr>
		              			
		            			<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory">#if($pagemode == 'baru' || $pagemode == 'kemaskini')* #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">No. Hakmilik</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		      							<input type="text" size="20" maxlength="50" name="txtNoHakmilik" id="txtNoHakmilik" value="$!NoHakmilik" class="$disabled" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
		  							</td>
		                		</tr>

		                    	<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory">#if($pagemode == 'baru' || $pagemode == 'kemaskini')* #end </span>
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
		  								<span class="labelmandatory">#if($pagemode == 'baru' || $pagemode == 'kemaskini')* #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">No Lot/PT</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		      							<input type="text" size="20" maxlength="15" name="txtNoLot" id="txtNoLot" value="$NoLot" class="$disabled" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
		  							</td>
		                		</tr> 
		                		<!---->
		                		<tr>
		  							<td width="1%"  >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Tarikh Warta</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
										<input type="text" size="11" maxlength="10" name="txdTarikhWarta"  id="txdTarikhWarta" value="$!TarikhRizab"   
					                	 class="$disabled" $readonly  onkeyup="validateNumber(this,this.value);" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdTarikhWarta);"/>					                	
					        			
		      						</td>
		                		</tr>		               			
		                   	<tr>
		                   		
		                   		<tr>
		  							<td width="1%"  >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">No Warta</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		      							<input type="text" name="txtNoWarta"  id="txtNoWarta" value="$!NoRizab" class="$disabled" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
		        					</td>
		                		</tr>
		 						<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory"></span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Kategori</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
										$selectKategori
		        					</td>
		                		</tr>	
			                   		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">No. Pelan</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
											<input type="text" size="30" id="txtNoPelan" name="txtNoPelan"  value="$!NoPelan" class="$disabled" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
			        					</td>
			                		</tr>
			                   		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">No. Syit Piawai</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
			      							<input  type="text" maxlength="50" size="30" name="txtNoSyit" id="txtNoSyit" value="$!NoSyit" class="$disabled" $readonly style="text-transform:uppercase;"/>
			        					</td>
			                		</tr>
			                   		<tr>
			  							<td width="1%"  >
							        	</td>				        
			                        	<td width="30%">
			                            	<div align="left">
			                            		<span class="labelinput">No. PU</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" >:</td>
			                   			<td width="68%" class="labeldisplay" >
											<input type="text" maxlength="50" size="30" name="txtNoPu" id="txtNoPu" value="$!Noptk" class="$disabled" $readonly style="text-transform:uppercase;"/>
			      						</td>
			                		</tr>

			                    	<tr>
			  							<td width="1%">
							        	</td>				        
			                        	<td width="30%" valign="top" >
			                            	<div align="left">
			                            		<span class="labelinput">Sekatan Kepentingan</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" valign="top" >:</td>
			                   			<td width="68%" class="labeldisplay" >
											<textarea cols="27" rows="5" name="txtSekatan" id="txtSekatan" class="$disabled" $readonly style="text-transform:uppercase;">$!Sekatan</textarea>  							
										</td>
			                		</tr>

			                    	<tr>
			  							<td width="1%">
							        	</td>				        
			                        	<td width="30%" valign="top" >
			                            	<div align="left">
			                            		<span class="labelinput">No. Fail JOFA</span>
			                            	</div>
			                        	</td>
			                  			<td width="1%" class="labelinput" valign="top" >:</td>
			                   			<td width="68%" class="labeldisplay" >
			                    	  		<input type="text" maxlength="50" size="30" name="txtNoJofa" id="txtNoJofa" value="$!NoJofa" class="$disabled" $readonly style="text-transform:uppercase;"/>
										</td>
			                		</tr>			                				                					                				                		
			                		
		                	</table>
		                </td>
		       		</tr>                    		
					
				</table>
				<!-- Bahagian 2-->
				<table width="100%" >
		     		<tr>
		            	<td width="50%" valign="top">
		                	<table width="100%" border="0">


		
		                 	</table>
		           		</td>
		                        	
		                <td width="50%" valign="top">
		               		<table width="100%">

				                		
		                			                		
		                	</table>
		                </td>
		       		</tr>                    		
					
				</table>			
			
			</fieldset>	
			<!-- penambahan yati -->
			
<fieldset><legend><strong>TARIKH PENGHANTARAN/SELESAI DOKUMEN</strong></legend>
	<table width="100%" border="0">
  
	##foreach ($geran in $selesaiBean)  
		<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($pagemode == "baru" || $pagemode == "kemaskini") * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left"> Tarikh  </div>
				        	</div>
				        </td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
				        	<input name="txdTarikhTerimaSelesai" type="text" id="txdTarikhTerimaSelesai" onblur="check_date(this);checkDate(this);" value="$!selesaiBean.tarikhSelesai" size="10" maxlength="10" $classDis $mode /> 
                        
                    #if ($pagemode == "baru" || $pagemode == "kemaskini") 
                        <img src="../img/calendar.gif" alt="calendar" border="0" onclick="displayDatePicker('txdTarikhTerimaSelesai',false,'dmy');" />
                    
                     #end   
                        
                        
                    </td>
		</tr>
        
        <tr>
                	  <td valign="top">&nbsp;</td>
                	  <td valign="top">	
                	  	<div align="right" class="labelinput">
				        	<div align="left">Keterangan</div>
				        	</div>
				        </td>
                	  <td valign="top">:</td>
                	  <td>
                	  #if ($pagemode == "baru" || $pagemode == "kemaskini") 
                	  <textarea name="txtKeterangan" id="txtKeterangan" cols="45" rows="3" maxlength="49" onkeyup="this.value=this.value.toUpperCase();" >$!selesaiBean.catatan</textarea>
                	  #else
                	  	<textarea name="txtKeterangan" id="txtKeterangan" cols="45" rows="3" maxlength="49" onkeyup="this.value=this.value.toUpperCase();" $mode $classDis >$!selesaiBean.catatan</textarea>
                	 #end
                	  </td>
 		</tr>
                  
	##end                
		<tr>
        	<td valign="top">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
     	</tr>						
	</table>

	
	<input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
	<input type="hidden" name="idFail" value="$IdFail">
	<input type="hidden" name="noFail" value="$NoFail">
	<input type="hidden" name="idPermohonan" value="$IdPermohonan">  
	<input type="hidden" name="idPihakberkepentingan" value="$IdPihakberkepentingan">  
	<input type="hidden" name="idBebanan" value="$IdBebanan">  
	<input type="hidden" name="idHTPGadaian" value="$idHTPGadaian"> 
	<input type="hidden" name="pagemode" value="$pagemode"> 

</fieldset>
                        	
		</td>
    </tr>
    
	#if($SOC == 'SOC')
	<tr>
		<td>
			<fieldset><legend>PELEPASAN GADAIAN</legend>
				<table width="100%" border="0">
					<tr>
				    	<td width="31%">Tarikh Pelepasan Gadaian</td>
				    	<td width="1%">:</td>
				    	<td width="68%">
				    		<input type="text" name="txdTarikhLepasGadai" id="txdTarikhLepasGadai" size="15" value="$TarikhLepasGadai" $classDis onblur="check_date(this)" />
				      		<img src="../img/calendar.gif" alt="calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTarikhLepasGadai',false,'dmy');" />
				      	</td>
					</tr>
				</table>
			</fieldset>		
		</td>
    </tr>    
	#end
    

    
   	#if ($pagemode == 'baru' || $pagemode == 'kemaskini')
    <tr>
        <td>
   			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
        </td>
    </tr>
	#end    
	  
	<tr>
		<td colspan="2" align="center">
        
		#if($pagemode == 'baru' || $pagemode == 'kemaskini')

        <input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGPHA_Simpan()">
        <input type="reset" class="stylobutton100" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fGPHA_Batal()">
        
        #elseif($pagemode == 'simpan')
        <input type="button" class="stylobutton100" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGPHA_Kemaskini()">
        <!--  <input type="button" class="stylobutton" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnya()"> -->
        <input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGPHA_Kembali()">
        
        #else
        
       <!--  <input type="button" class="stylobutton" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGPHA_Kembali()">
        <input type="button" class="stylobutton" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnya()">
        -->
        
          <input type="button" class="stylobutton100" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGPHA_Kemaskini()">
          <input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGPHA_Kembali()">
        #end     
        </td>     
	</tr>   
	
</table>		

 
 
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">

  

