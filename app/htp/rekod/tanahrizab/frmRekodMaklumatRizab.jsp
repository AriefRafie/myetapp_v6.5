<!-- <fieldset>
<legend>MAKLUMAT RIZAB</legend> -->

	<fieldset>
		<table width="100%">
       		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%" valign="top">
				        	</td>				        
                        	<td width="30%" valign="top">
                            	<div align="left">
                            		<span class="labelinput">Kementerian</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top">:</td>
                   			<td width="68%" class="labelinput" >
 								$txtNamaKementerian
  							</td>
                		</tr>
                     	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Fail Seksyen</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labelinput" >
 								$txtNoFailSeksyen
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
                   			<td width="68%" class="labelinput" >
 								$txtFailPTG
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%" valign="top" >
                            	<div align="left">
                            		<span class="labelinput">Tajuk</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top" >:</td>
                   			<td width="68%" class="labelinput" >
 								$txtTajuk
  							</td>
                		</tr>
                 	</table>
           		</td>
                        	
                <td valign="top">
               		<table width="100%">
                   	<tr>
  							<td width="1%" valign="top" ></td>				        
                        	<td width="30%" valign="top">
                            	<div align="left">
                            		<span class="labelinput">Agensi</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top">:</td>
                   			<td width="68%" class="labelinput" valign="top">
 								$txtNamaAgensi
 							#if ($!mode == 'update' || $!mode == 'view') 
						        <br><a href="javascript:viewTransaksiAgensi('$idHakmilik')" title="Kementerian/ Agensi Terkini" class="pautanms">Kementerian/ Agensi Terkini</a>     						
						     #end
 		
 							</td>
                		</tr>
              			
            			<tr>
  							<td width="1%" valign="top" ></td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Fail KJP</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labelinput" >
 								$txtFailKJP
  							</td>
                		</tr>

                    	<tr>
  							<td width="1%" valign="top" ></td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Fail PTD</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labelinput" >
 								$!txtFailPTD
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%" valign="top" ></td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Cara Perolehan</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labelinput" >
 								$!caraPerolehan
  							</td>
                		</tr>
                	</table>
                </td>
       		</tr>                    
		</table>
	</fieldset>
	<!--<br>-->
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
                            		<span class="labelinput">
                     			#if ($!cariIdNegeri == '13' )
				        			Bahagian
				        		#else
				        			Daerah
				        		#end
                            		</span>
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
	                            		<span class="labelinput">Jenis Rizab</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
		    						<input type="radio" name="socJenisTanahtemp" value="1" $checkedMilik  onclick="cari()"/>Bewarta
					 				<input type="radio" name="socJenisTanahtemp" value="2" $checkedRizab  onclick="cari()"/>Tidak Bewarta
								</td>
	                	</tr>
	                   	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Warta</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
									<input name="txtNoWarta" type="text" id="txtNoWarta" value="$txtNoWarta" size="30" $readonly class="$disabled"/>
								</td>
	                		</tr>
	                     	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Lot</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
	      							<input name="txtNoLot" type="text" class="$disabled" id="txtNoLot" size="30" maxlength="25" value="$txtNoLot"  $readonly />
	  							</td>
	                		</tr> 

                	</table>
                </td>
       		</tr>                    
		</table>
	</fieldset>	
	<!--<br>-->
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
	                            		<span class="labelinput">Tarikh Warta</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
									<input name="txdTarikhWarta" type="text" id="txdTarikhWarta" value="$txdTarikhWarta" size="11" maxlength="10" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhTerima);"/>
	        						#if ($mode == 'update') 
	        							<a href="javascript:displayDatePicker('txdTarikhWarta',false,'dmy');"> <img border="0" src="../img/calendar.gif"/>
	      								 <span class="pautancalendar">dd/mm/yyyy </span> </a>
	    							#end 			
	        					</td>
	                		</tr>
	                     	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Tarikh Terima Warta</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
	 								<input name="txdTarikhTerima" type="text" id="txdTarikhTerima" value="$txdTarikhTerima" size="11" maxlength="10" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhTerima);"/>
	    							#if ($mode == 'update') 
	    								<a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"> <img border="0" src="../img/calendar.gif"/>
	    								<span class="pautancalendar">dd/mm/yyyy</span></a> 
	    							#end
	  							</td>
	                		</tr>
	                   		<tr>
	  						<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Lokasi</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
									<input name="txtLokasi" type="text" id="txtLokasi" value="$txtLokasi" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>		
					   			</td>
	                		</tr>
	                   		<tr>
	  							<td width="1%" valign="top">
	  								<span class="labelmandatory" >#if ($mode == 'update') * #end </span>
					        	</td>				        
	                        	<td width="30%" valign="top" >
	                            	<div align="left">
	                            		<span class="labelinput" >Kegunaan Tanah</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" valign="top" >:</td>
	                   			<td width="68%" class="labeldisplay" >
									<textarea name="txtKegunaanTanah" cols="27" rows="5" id="txtKegunaanTanah" style="text-transform:uppercase;" $readonly="$readonly" class="$disabled">$txtKegunaanTanah</textarea>
							   	</td>
	                		</tr>                		
	                   		<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Unit Luas</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
	                   			#if ($mode == 'view')
	                   				#set( $sluas = '' )
	                   				#if($socLuas == '1')
										#set( $sluas = 'KM - KILOMETER PERSEGI')
									#elseif($socLuas == '2')	
										#set( $sluas = 'H - HEKTAR')											
									#elseif($socLuas == '3')	
										#set( $sluas = 'M - METER PERSEGI')	
									#elseif($socLuas == '4')	
										#set( $sluas = 'E- EKAR,ROOD,POLE')											
									#elseif($socLuas == '5')	
										#set( $sluas = 'K - KAKI PERSEGI')										
									#elseif($socLuas == '6')	
										#set( $sluas = 'P - EKAR PERPULUHAN')											
									#elseif($socLuas == '7')	
										#set( $sluas = 'D - EKAR,DEPA')	
									#elseif($socLuas == '8')	
										#set( $sluas = 'R - RELONG,JEMBA,KAKI PERSEGI')											
									#elseif($socLuas == '9')	
										#set( $sluas = 'BN - BATU NAUTIKA')										
									#end
									$!sluas
									<input name="socLuas" type="hidden" value="$socLuas" />
									
								#else
									<select name="socLuas" id="socLuas" style="width:200px;" 
									$readonly class="$disabled" 
									$disabled onchange = "doChangeLuasRizab('$!idHakmilik');">
									      #if($socLuas == '1')
									      <option value="">SILA PILIH</option>
									        <option value="1" selected="selected"> KM - KILOMETER PERSEGI</option>
									        <option value="2"> H - HEKTAR</option>
									        <option value="3"> M - METER PERSEGI</option>
									        <option value="4"> E - EKAR,ROOD,POLE </option>
									        <option value="5"> K - KAKI PERSEGI</option>
									        <option value="6"> P - EKAR PERPULUHAN</option>
									        <option value="7"> D - EKAR,DEPA</option>
									        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
									        <option value="9"> BN - BATU NAUTIKA</option>
									      
									      #elseif($socLuas == '2')
									        
									      <option value="">SILA PILIH</option>
									        <option value="1"> KM - KILOMETER PERSEGI</option>
									        <option value="2" selected="selected"> H - HEKTAR</option>
									        <option value="3"> M - METER PERSEGI</option>
									        <option value="4"> E - EKAR,ROOD,POLE </option>
									        <option value="5"> K - KAKI PERSEGI</option>
									        <option value="6"> P - EKAR PERPULUHAN</option>
									        <option value="7"> D - EKAR,DEPA</option>
									        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
									        <option value="9"> BN - BATU NAUTIKA</option>
									      
									       #elseif($socLuas == '3')
									        
									      <option value="">SILA PILIH</option>
									        <option value="1"> KM - KILOMETER PERSEGI</option>
									        <option value="2"> H - HEKTAR</option>
									        <option value="3" selected="selected"> M - METER PERSEGI</option>
									        <option value="4"> E - EKAR,ROOD,POLE </option>
									        <option value="5"> K - KAKI PERSEGI</option>
									        <option value="6"> P - EKAR PERPULUHAN</option>
									        <option value="7"> D - EKAR,DEPA</option>
									        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
									        <option value="9"> BN - BATU NAUTIKA</option>
									      
									       #elseif($socLuas == '4')
									        
									      <option value="">SILA PILIH</option>
									        <option value="1"> KM - KILOMETER PERSEGI</option>
									        <option value="2"> H - HEKTAR</option>
									        <option value="3"> M - METER PERSEGI</option>
									        <option value="4" selected="selected"> E- EKAR,ROOD,POLE </option>
									        <option value="5"> K - KAKI PERSEGI</option>
									        <option value="6"> P - EKAR PERPULUHAN</option>
									        <option value="7"> D - EKAR,DEPA</option>
									        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
									        <option value="9"> BN - BATU NAUTIKA</option>
									       
									       #elseif($socLuas == '5')
									        
									      <option value="">SILA PILIH</option>
									        <option value="1"> KM - KILOMETER PERSEGI</option>
									        <option value="2"> H - HEKTAR</option>
									        <option value="3"> M - METER PERSEGI</option>
									        <option value="4"> E - EKAR,ROOD,POLE </option>
									        <option value="5" selected="selected"> K - KAKI PERSEGI</option>
									        <option value="6"> P - EKAR PERPULUHAN</option>
									        <option value="7"> D - EKAR,DEPA</option>
									        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
									        <option value="9"> BN - BATU NAUTIKA</option>
									      
									       #elseif($socLuas == '6')
									        
									      <option value="">SILA PILIH</option>
									        <option value="1"> KM - KILOMETER PERSEGI</option>
									        <option value="2"> H - HEKTAR</option>
									        <option value="3"> M - METER PERSEGI</option>
									        <option value="4"> E - EKAR,ROOD,POLE </option>
									        <option value="5"> K - KAKI PERSEGI</option>
									        <option value="6" selected="selected"> P - EKAR PERPULUHAN</option>
									        <option value="7"> D - EKAR,DEPA</option>
									        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
									        <option value="9"> BN - BATU NAUTIKA</option>
									      
									       #elseif($socLuas == '7')
									        
									      <option value="">SILA PILIH</option>
									        <option value="1"> KM - KILOMETER PERSEGI</option>
									        <option value="2"> H - HEKTAR</option>
									        <option value="3"> M - METER PERSEGI</option>
									        <option value="4"> E - EKAR,ROOD,POLE </option>
									        <option value="5"> K- KAKI PERSEGI</option>
									        <option value="6"> P - EKAR PERPULUHAN</option>
									        <option value="7" selected="selected"> D - EKAR,DEPA</option>
									        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
									        <option value="9"> BN - BATU NAUTIKA</option>
									      
									       #elseif($socLuas == '8')
									        
									      <option value="">SILA PILIH</option>
									        <option value="1"> KM - KILOMETER PERSEGI</option>
									        <option value="2"> H - HEKTAR</option>
									        <option value="3"> M - METER PERSEGI</option>
									        <option value="4"> E - EKAR,ROOD,POLE </option>
									        <option value="5"> K - KAKI PERSEGI</option>
									        <option value="6"> P - EKAR PERPULUHAN</option>
									        <option value="7"> D - EKAR,DEPA</option>
									        <option value="8" selected="selected"> R - RELONG,JEMBA,KAKI PERSEGI</option>
									        <option value="9"> BN - BATU NAUTIKA</option>
									      
									       #elseif($socLuas == '9')
									        
									      <option value="">SILA PILIH</option>
									        <option value="1"> KM - KILOMETER PERSEGI</option>
									        <option value="2"> H - HEKTAR</option>
									        <option value="3"> M - METER PERSEGI</option>
									        <option value="4"> E - EKAR,ROOD,POLE </option>
									        <option value="5"> K - KAKI PERSEGI</option>
									        <option value="6"> P - EKAR PERPULUHAN</option>
									        <option value="7"> D - EKAR,DEPA</option>
									        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
									        <option value="9" selected="selected"> BN - BATU NAUTIKA</option>
									         
									       #elseif($socLuas == '')
									        
									      <option value="" selected="selected">SILA PILIH</option>
									        <option value="1"> KM - KILOMETER PERSEGI</option>
									        <option value="2"> H - HEKTAR</option>
									        <option value="3"> M - METER PERSEGI</option>
									        <option value="4"> E- EKAR,ROOD,POLE </option>
									        <option value="5"> K - KAKI PERSEGI</option>
									        <option value="6"> P - EKAR PERPULUHAN</option>
									        <option value="7"> D - EKAR,DEPA</option>
									        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
									        <option value="9"> BN - BATU NAUTIKA</option>
									                                            
									 	  #end
									</select>
								#end
							   	</td>
	                		</tr> 
	                   		<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Luas</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
								#if($mode == "update")
								    #if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6')
								      <input name="txtLuas1" type="text" value="$!txtLuasLama" class="$disabled" id="txtLuas1" size="30" maxlength="15" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')" />
								    #elseif($socLuas == '8' || $socLuas == '4')
								      <input type="text" name="txtLuas2" value="$!txtLuasLama" id="txtLuas2" class="$disabled" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);"/>
								      <input type="text" name="txtLuas3" value="$!txtLuasLama1" id="txtLuas3" class="$disabled" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);"/>
								      <input type="text" name="txtLuas4" value="$!txtLuasLama2" id="txtLuas4" class="$disabled" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')"/>      
								    #elseif($socLuas == '7')
								      <input name="txtLuas5" type="text" value="$!txtLuasLama" class="$disabled" id="txtLuas5" size="13" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" />
								      <input name="txtLuas6" type="text" value="$!txtLuasLama1" class="$disabled" id="txtLuas6" size="13" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')"/>
								    #end 
								    <!--<a href="javascript:kiraLuas('$socLuas')" title="Kira luas dalam hektar." class="style1 style3">Kira Luas</a> -->
								#elseif ($mode =="view")
								    <input name="txtLuasLama" type="text" size="30" maxlength="15" id="txtLuasLama" value="$txtLuasLama" 
                                    size="15" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" />
								#end
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
									<input name="txtLuas" type="text" class="disabled" id="txtLuas" value="$!txtLuas" size="30" readonly="readonly"/>(Hektar)  							
								</td>
	                		</tr>
                		
	                		<tr>
	  							<td width="1%">
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">&nbsp;</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >&nbsp;</td>
	                   			<td width="68%" class="labeldisplay" >
	        					#if(!$!jenisAkses.equals('readonly'))	        			
		                   				<a href="javascript:pergerakanhakmilik_detail('$!idHakmilik');" class="pautanms">Maklumat Pergerakan</a>
	                   			#end
	                   			</td>
	                		</tr> 	
	                 	</table>
	           		</td>
	                        	
	                <td valign="top">
	               		<table width="100%">
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
									<input name="txtNoPelan" type="text" id="txtNoPelan" value="$txtNoPelan" size="20" $readonly class="$disabled" />
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
	      							<input name="txtNoSyit" type="text" id="txtNoSyit" value="$txtNoSyit" size="20" $readonly class="$disabled" />
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
									<input name="txtNoPu" type="text" id="txtNoPu" value="$txtNoPu" size="20" $readonly class="$disabled" />
	      						</td>
	                		</tr>
	                		<tr>
	  							<td width="1%" >
					        	</td>				        
	                        	<td width="30%" valign="top" >
	                            	<div align="left">
	                            		<span class="labelinput">Catatan/Urusan</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" valign="top" >:</td>
	                   			<td width="68%" class="labeldisplay" >
									<textarea name="txtKemAgenTerkini" cols="27" rows="5" class="$disabled" id="txtKemAgenTerkini" style="text-transform:uppercase;" $readonly="$readonly">$!txtKemAgenTerkini</textarea>  							
								</td>
                			</tr>
      						#if($statusBatal!='S' && $statusBatal!='B')
	                   		<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Status Sah</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms1" >
    							#if ($mode == 'view')
	                   				#set( $sstatus = '' )
	                   				#if($statusBatal  == 'D')
										#set( $sstatus = 'D - DAFTAR')
									#elseif($statusBatal  == 'P')									
										#set( $sstatus = 'P - BATAL (PELEPASAN)')
									#elseif($statusBatal  == 'Q')									
										#set( $sstatus = 'Q - BATAL (PERMOHONAN)')
									#end
									$!sstatus 
								#else
									<select name="socStatusDaftar" style="width:200px;" $readonly class="$disabled" $disabled onChange="doChangeTaraf()">
							            #if($socStatus == 'D')
							          		<option value="">SILA PILIH</option>
							            	<option value="D" selected="selected">D - DAFTAR</option>
								        	<option value="P">P - BATAL (PELEPASAN)</option>
								        	<option value="Q">Q - BATAL (PERMOHONAN)</option>
								        	
							         	#elseif($socStatus == 'P')						                
								        	<option value="">SILA PILIH</option>
								        	<option value="D">D - DAFTAR</option>
								        	<option value="P" selected="selected" >P - BATAL (PELEPASAN)</option>
								        	<option value="Q"> bhy6tgvfc </option>
							         	
							         	#elseif($socStatus == 'Q')						                
								        	<option value="">SILA PILIH</option>
								        	<option value="D">D - DAFTAR</option>
								        	<option value="P" >P - BATAL (PELEPASAN)</option>
								        	<option value="Q" selected="selected" >Q - BATAL (PERMOHONAN)</option>
								        
							            #else 
							                <option value="">SILA PILIH</option>
							                <option value="D">D - DAFTAR</option>
								        	<option value="P">P - BATAL (PELEPASAN)</option>
							        	   	<option value="Q">Q - BATAL (PERMOHONAN)</option>
							        	#end
							        </select> 
							     #end   
        	      				</td>
	                		</tr>
	                		#else
	                   		<tr>
	  							<td width="1%"  >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Status Sah</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
	                   			#set( $sstatus = '' )
	                   			#if($statusBatal  == 'D')
									#set( $sstatus = 'D - DAFTAR')
								#elseif($statusBatal  == 'P')									
									#set( $sstatus = 'P - BATAL (PELEPASAN)')
								#elseif($statusBatal  == 'L')									
									#set( $sstatus = 'L - BATAL (PERLETAKHAKAN)')
								#elseif($statusBatal  == 'B')									
									#set( $sstatus = 'B - BATAL')
								#end
									$!sstatus 
	                   				<input name="socStatusDaftar" type="hidden" value="$!statusBatal" />
	                   				
	      						</td>
	                		</tr>
      						#end
	                		<tr>
	  							<td width="1%"  >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Pegawai Akhir Kemaskini</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
									<input name="txtPegawaiAkhir" type="text" class="disabled" id="txtPegawaiAkhir" value="$!txtPegawaiAkhir" size="30" readonly="readonly" />
								</td>
	                		</tr>
	                   		<tr>
	  							<td width="1%"  >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Tarikh Akhir Kemaskini</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
									<input name="txdTarikhKemaskini" type="text" id="txdTarikhKemaskini" value="$txdTarikhKemaskini" size="11" maxlength="10" onkeyup="validateNumber(this,this.value);" $readonly class="disabled"/>  							
								</td>
	                		</tr>
	                	</table>
	                </td>
	       		</tr>   
	       		                 
			</table>	

    </fieldset>
   	 
	<table width="100%">
		<tr>
	    <td colspan="2" ><div align="center">        
	        <p>
	#if($statusBatal!='S' || $statusBatal!='P' || $statusBatal!='B')
		#if ($mode == 'view')
		          
			#if($!statusBatal == '')
		    	#set($labelPengesahan = '')
		    	
		    	#if ($statuSemasa =='1' && ($portal_role_ =='(HTP)PenggunaNegeri' || $portal_role_ =='(HTP)PenggunaNegeriSS'))
		    		#set($labelPengesahan = 'Hantar Semakan')		
		    		    	
		    	#elseif($statuSemasa =='3' && ($portal_role_ =='(HTP)PegawaiNegeri' || $portal_role_ =='(HTP)PegawaiNegeriSS'))
		    		#set($labelPengesahan = 'Hantar Pengesahan')
		    	
		    	#elseif($statuSemasa =='4' && ($portal_role_ =='(HTP)PengarahNegeri' || $portal_role_ =='(HTP)PengarahNegeriSS'))		     
		    		#set($labelPengesahan = 'Hantar Ke HQ')

		  		#elseif($statuSemasa =='5' && $portal_role_ =='(HTP)HQPengguna')
		  			#set($labelPengesahan = 'Hantar Ke Unit Rekod')
		  		
		  		#elseif($statuSemasa =='6' && $portal_role_ =='(HTP)HQPenggunaRekod')
		  			#set($labelPengesahan = 'Hantar Semakan')

		  		#elseif($statuSemasa =='7' && ($portal_role_ =='(HTP)HQPegawai1' || $portal_role_ =='(HTP)HQPegawai'))
		  			#set($labelPengesahan = 'Hantar Pengesahan')

		  		#elseif($statuSemasa =='8' && $portal_role_ =='(HTP)HQPengarah')
		  			#set($labelPengesahan = 'Sahkan Maklumat Tanah')
		  		
		  		#end
		  		
		 		#if($!labelPengesahan != '')	
		  		<input type="button" name="cmdSimpan" id="cmdpengesahan" value="$!labelPengesahan" onclick="doAjaxCall${formName}('simpanpengesahan','idFail=$!idFail')" />
		 		#end
		 	
		 	#end
		        	
		 	#if (!$!jenisAkses.equals('readonly'))		          	
				<input type="button" class="stylobutton100" name="btnUpdate" value="Kemaskini" onclick="kemaskini_detailRizab($idHakmilik)" />
			#end
		   		<!--<input type="button" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:setTable('tableReport1')" class="stylobutton" /> -->
				<input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:cetakMaklumatRizab($idHakmilik);" />
		
		#end
		          
		#if ($mode == 'update')
		          	<!-- Tidak digunakan
				    	<input type="button" class="stylobutton100" name="btnSaveRizab" id="btnSaveRizab" value="Simpan" onclick="update_detailRizab($idHakmilik)" />
				    	<input type="button" class="stylobutton100" name="btnResetRizab" id="btnResetRizab"  value="Batal" onclick="rizab_detail('$!idHakmilik','$!sstatus');"/>
		          	-->
		#end
		          
	#end
				<input type="button" class="stylobutton100" name="btnBackRizab" id="btnBackRizab" value="Kembali" onclick="kembaliHakmilik()" />
	        </p>
	    </div></td>
	  </tr>
	  	  <tr>
	    <td colspan="2" ><div align="right">	  
	  		CL-02-70
	  	</td>
	  </tr>
	</table>
<!-- </fieldset> -->