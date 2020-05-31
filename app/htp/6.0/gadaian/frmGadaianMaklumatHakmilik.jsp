<style type="text/css">
<!--
	.pautanms{color: #0000FF}
-->
</style>
#set ($TajukFail = "")
#set ($NoFail = "")
#set ($NoRujukanLain = "")
#set ($IdNegeri = "")
#set ($NoHakmilik = "")
#set ($NoLot = "")
#set ($TarikhMula = "")
#set ($Cukai = "")
#set ($Lokasi = "")
#set ($NoPelan = "")
#set ($Syarat = "")
#set ($TarikhLuput = "")
#set ($CukaiTerkini = "")
#set ($Luas = "")
#set ($TarikhRizab = "")
#set ($NoRizab = "")
#set ($Noptk = "")
#set ($NoSyit = "")
#set ($Sekatan = "")
#set ($IdHakmilikurusan = "")
#set($TarikhLepasGadai = "")
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
		  								<span class="labelmandatory">#if($pagemode == 'baru' || $pagemode == 'kemaskini')* #end </span>
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
		  								<span class="labelmandatory">#if($pagemode == 'baru' || $pagemode == 'kemaskini')* #end </span>
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
		  								<span class="labelmandatory">#if($pagemode == 'baru' || $pagemode == 'kemaskini')* #end </span>
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
		  								<span class="labelmandatory">#if($pagemode == 'baru' || $pagemode == 'kemaskini')* #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Tarikh Terima Hakmilik</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		 								<input type="text" size="11" maxlength="10" name="txdTarikhTerima"  id="txdTarikhTerima" value="$TarikhMula"  $classDis onblur="check_date(this)"/>
		    							#if ($pagemode == 'baru' || $pagemode == 'kemaskini') 
		    								<a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"> <img border="0" src="../img/calendar.gif"/>
		    								<span class="pautanms">dd/mm/yyyy</span></a> 
		    							#end
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
		 								<input type="text" size="11" maxlength="10" name="txdTarikhLuput" id="txdTarikhLuput" value="$TarikhLuput" $classDis onblur="check_date(this)" />
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
		 								<input type="text" maxlength="9" size="17" name="txtCukaiPertama" id="txtCukaiPertama" onkeyup="validateCurrency(this,this.value);" value="$Cukai" $modeSoc $classDis />
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
		 								<input type="text" maxlength="9" size="17" name="txtCukaiSemasa" id="txtCukaiSemasa" onkeyup="validateCurrency(this,this.value);" value="$CukaiTerkini" $modeSoc $classDis />
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
										<input type="text" maxlength="60" name="txtLokasi" id="txtLokasi" value="$!Lokasi" $modeSoc $classDis onkeyup="this.value=this.value.toUpperCase();" />	
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
			        						<textarea cols="27" rows="5" name="txtSyarat" id="txtSyarat" $modeSoc $classDis style="text-transform:uppercase;">$!Syarat</textarea>
			  							</td>
			                		</tr>			
		                 	</table>
		           		</td>
		                        	
		                <td width="50%" valign="top">
		               		<table width="100%">
		                   	<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory">#if($pagemode == 'baru' || $pagemode == 'kemaskini')* #end </span>
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
		      							<input type="text" size="20" maxlength="50" name="txtNoHakmilik" id="txtNoHakmilik" value="$!NoHakmilik" $classDis $modeSoc onkeyup="this.value=this.value.toUpperCase();"/>
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
		      							<input type="text" size="20" maxlength="15" name="txtNoLot" id="txtNoLot" value="$NoLot" $classDis $modeSoc onkeyup="this.value=this.value.toUpperCase();"/>
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
					                	 $classDis  onkeyup="validateNumber(this,this.value);" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdTarikhWarta);"/>					                	
					        			#if ($pagemode == 'baru' || $pagemode == 'kemaskini')
					            			<a href="javascript:displayDatePicker('txdTarikhWarta',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> <span class="pautanms">dd/mm/yyyy </span>
					        			#end
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
		      							<input type="text" name="txtNoWarta"  id="txtNoWarta" value="$!NoRizab" $modeSoc $classDis onkeyup="this.value=this.value.toUpperCase();"/>
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
											<input type="text" size="30" id="txtNoPelan" name="txtNoPelan"  value="$!NoPelan" $modeSoc $classDis onkeyup="this.value=this.value.toUpperCase();"/>
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
			      							<input  type="text" maxlength="50" size="30" name="txtNoSyit" id="txtNoSyit" value="$!NoSyit" $modeSoc $classDis style="text-transform:uppercase;"/>
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
											<input type="text" maxlength="50" size="30" name="txtNoPu" id="txtNoPu" value="$!Noptk" $modeSoc $classDis style="text-transform:uppercase;"/>
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
											<textarea cols="27" rows="5" name="txtSekatan" id="txtSekatan" $modeSoc $classDis style="text-transform:uppercase;">$!Sekatan</textarea>  							
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
			                    	  		<input type="text" maxlength="50" size="30" name="txtNoJofa" id="txtNoJofa" value="$!NoJofa" $modeSoc $classDis style="text-transform:uppercase;"/>
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

        <input type="button" class="stylobutton_" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGPHA_Simpan()">
        <input type="reset" class="stylobutton_" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fGPHA_Batal()">
        
        #elseif($pagemode == 'simpan')
        <input type="button" class="stylobutton_" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGPHA_Kemaskini()">
        <input type="button" class="stylobutton_" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnya()">
        
        #else
        
        <input type="button" class="stylobutton_" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGPHA_Kembali()">
        <input type="button" class="stylobutton_" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnya()">
        
        #end     
        </td>     
	</tr>   
</table>		

  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="noFail" value="$NoFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">

  
  

