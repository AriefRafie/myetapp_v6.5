<style type="text/css">
<!--
.pautancalendar {
	font-size: x-small;
	font-style: italic;
	color: #0000FF;
}
-->
<!--
.pautanms {color: #0000FF}
-->

</style>
<input name="idHakmilik" type="hidden" value="$idHakmilik" />
<input name="mode" type="hidden" value="$mode" />
##parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")
<table width="99%" border="0">
 <tr>
    <td>
    	&nbsp;
    </td>
</tr>      
    <tr>
    <td>
    	<fieldset>
    	<legend>MAKLUMAT RIZAB</legend>
    
	    	<fieldset>
			<table width="100%">
	       		<tr>
	            	<td width="50%" valign="top">
	                	<table width="100%" border="0">
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Kementerian</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
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
	                   			<td width="68%" class="pautanms" >
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
	                   			<td width="68%" class="pautanms" >
	 								$txtFailPTG 
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%" valign="top">
	                            	<div align="left">
	                            		<span class="labelinput">Tajuk</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" valign="top">:</td>
	                   			<td width="68%" class="pautanms" >
	 								$txtTajuk
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
	 								$txtNamaAgensi
	  							</td>
	                		</tr>
	              			
	            			<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Fail KJP</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								$txtFailKJP
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
	 								$!txtFailPTD
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
	                   			<td width="68%" class="labeldisplay" >
	 								$!selectNegeriHR
	  							</td>
	                		</tr>
	                     	<tr>
	  							<td width="1%"  >
	  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Daerah</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
	 								$!selectDaerahHR
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
	                   			<td width="68%" class="labeldisplay" >
	 								$!selectMukimHR
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
	                            		<span class="labelinput">No. Warta</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
									<!-- <input name="txtNoWarta" type="text" id="txtNoWarta" value="$txtNoWarta" size="30" $readonly class="$disabled"/> -->
									$!txtNoWarta
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
	      							<!-- <input name="txtNoLot" type="text" class="$disabled" id="txtNoLot" maxlength="15" value="$txtNoLot" size="20" $readonly /> -->
	      							$!txtNoLot
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
									<!-- <input name="txdTarikhWarta" type="text" id="txdTarikhWarta" value="$txdTarikhWarta" size="11" maxlength="10" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="validateTarikhSemasa();"/>-->
									$!txdTarikhWarta
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
	 								<!-- <input name="txdTarikhTerima" type="text" id="txdTarikhTerima" value="$txdTarikhTerima" size="11" maxlength="10" $readonly class="$disabled" onblur="check_date(this)"/> -->
	 								$!txdTarikhTerima
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
									<!--<input name="txtLokasi" type="text" id="txtLokasi" value="$txtLokasi" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>		-->
									$!txtLokasi
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
								<!-- <textarea name="txtKegunaanTanah" cols="27" rows="5" id="txtKegunaanTanah" style="text-transform:uppercase;" $readonly="$readonly" class="$disabled">$txtKegunaanTanah</textarea> -->
								$!txtKegunaanTanah
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
                   			<td width="68%" class="labeldisplay" >
								<!-- <select name="socLuas" id="socLuas" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeTaraf()">	-->
								      #if($socLuas == '1')
								      <!--<option value="">SILA PILIH</option>
								        <option value="1" selected="selected"> --> KM - KILOMETER PERSEGI <!-- </option>
								        <option value="2"> H - HEKTAR</option>
								        <option value="3"> M - METER PERSEGI</option>
								        <option value="4"> E - EKAR,ROOD,POLE </option>
								        <option value="5"> K - KAKI PERSEGI</option>
								        <option value="6"> P - EKAR PERPULUHAN</option>
								        <option value="7"> D - EKAR,DEPA</option>
								        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
								        <option value="9"> BN - BATU NAUTIKA</option> -->
								      
								      #elseif($socLuas == '2')
								        
								      <!-- <option value="">SILA PILIH</option>
								        <option value="1"> KM - KILOMETER PERSEGI</option>
								        <option value="2" selected="selected"> --> H - HEKTAR <!--</option>
								        <option value="3"> M - METER PERSEGI</option>
								        <option value="4"> E - EKAR,ROOD,POLE </option>
								        <option value="5"> K - KAKI PERSEGI</option>
								        <option value="6"> P - EKAR PERPULUHAN</option>
								        <option value="7"> D - EKAR,DEPA</option>
								        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
								        <option value="9"> BN - BATU NAUTIKA</option> -->
								      
								       #elseif($socLuas == '3')
								        
								      <!--<option value="">SILA PILIH</option>
								        <option value="1"> KM - KILOMETER PERSEGI</option>
								        <option value="2"> H - HEKTAR</option>
								        <option value="3" selected="selected"> --> M - METER PERSEGI <!-- </option>
								        <option value="4"> E - EKAR,ROOD,POLE </option>
								        <option value="5"> K - KAKI PERSEGI</option>
								        <option value="6"> P - EKAR PERPULUHAN</option>
								        <option value="7"> D - EKAR,DEPA</option>
								        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
								        <option value="9"> BN - BATU NAUTIKA</option> -->
								      
								       #elseif($socLuas == '4')
								        
								      <!-- <option value="">SILA PILIH</option>
								        <option value="1"> KM - KILOMETER PERSEGI</option>
								        <option value="2"> H - HEKTAR</option>
								        <option value="3"> M - METER PERSEGI</option>
								        <option value="4" selected="selected"> -->E- EKAR,ROOD,POLE <!-- </option>
								        <option value="5"> K - KAKI PERSEGI</option>
								        <option value="6"> P - EKAR PERPULUHAN</option>
								        <option value="7"> D - EKAR,DEPA</option>
								        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
								        <option value="9"> BN - BATU NAUTIKA</option> -->
								       
								       #elseif($socLuas == '5')
								        
								      <!-- <option value="">SILA PILIH</option>
								        <option value="1"> KM - KILOMETER PERSEGI</option>
								        <option value="2"> H - HEKTAR</option>
								        <option value="3"> M - METER PERSEGI</option>
								        <option value="4"> E - EKAR,ROOD,POLE </option>
								        <option value="5" selected="selected"> -->K - KAKI PERSEGI <!-- </option>
								        <option value="6"> P - EKAR PERPULUHAN</option>
								        <option value="7"> D - EKAR,DEPA</option>
								        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
								        <option value="9"> BN - BATU NAUTIKA</option> -->
								      
								       #elseif($socLuas == '6')
								        
								      <!-- <option value="">SILA PILIH</option>
								        <option value="1"> KM - KILOMETER PERSEGI</option>
								        <option value="2"> H - HEKTAR</option>
								        <option value="3"> M - METER PERSEGI</option>
								        <option value="4"> E - EKAR,ROOD,POLE </option>
								        <option value="5"> K - KAKI PERSEGI</option>
								        <option value="6" selected="selected"> -->P - EKAR PERPULUHAN <!--</option>
								        <option value="7"> D - EKAR,DEPA</option>
								        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
								        <option value="9"> BN - BATU NAUTIKA</option> -->
								      
								       #elseif($socLuas == '7')
								        
								      <!-- <option value="">SILA PILIH</option>
								        <option value="1"> KM - KILOMETER PERSEGI</option>
								        <option value="2"> H - HEKTAR</option>
								        <option value="3"> M - METER PERSEGI</option>
								        <option value="4"> E - EKAR,ROOD,POLE </option>
								        <option value="5"> K- KAKI PERSEGI</option>
								        <option value="6"> P - EKAR PERPULUHAN</option>
								        <option value="7" selected="selected"> -->D - EKAR,DEPA <!--</option>
								        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
								        <option value="9"> BN - BATU NAUTIKA</option> -->
								      
								       #elseif($socLuas == '8')
								        
								      <!-- <option value="">SILA PILIH</option>
								        <option value="1"> KM - KILOMETER PERSEGI</option>
								        <option value="2"> H - HEKTAR</option>
								        <option value="3"> M - METER PERSEGI</option>
								        <option value="4"> E - EKAR,ROOD,POLE </option>
								        <option value="5"> K - KAKI PERSEGI</option>
								        <option value="6"> P - EKAR PERPULUHAN</option>
								        <option value="7"> D - EKAR,DEPA</option>
								        <option value="8" selected="selected"> -->R - RELONG,JEMBA,KAKI PERSEGI <!--</option>
								        <option value="9"> BN - BATU NAUTIKA</option> -->
								      
								       #elseif($socLuas == '9')
								        
								      <!-- <option value="">SILA PILIH</option>
								        <option value="1"> KM - KILOMETER PERSEGI</option>
								        <option value="2"> H - HEKTAR</option>
								        <option value="3"> M - METER PERSEGI</option>
								        <option value="4"> E - EKAR,ROOD,POLE </option>
								        <option value="5"> K - KAKI PERSEGI</option>
								        <option value="6"> P - EKAR PERPULUHAN</option>
								        <option value="7"> D - EKAR,DEPA</option>
								        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
								        <option value="9" selected="selected"> --> BN - BATU NAUTIKA <!--</option>-->
								         
								       #elseif($socLuas == '' || $socLuas == '0')
								        
								      <!-- <option value="" selected="selected">SILA PILIH</option>
								        <option value="1"> KM - KILOMETER PERSEGI</option>
								        <option value="2"> H - HEKTAR</option>
								        <option value="3"> M - METER PERSEGI</option>
								        <option value="4"> E- EKAR,ROOD,POLE </option>
								        <option value="5"> K - KAKI PERSEGI</option>
								        <option value="6"> P - EKAR PERPULUHAN</option>
								        <option value="7"> D - EKAR,DEPA</option>
								        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
								        <option value="9"> BN - BATU NAUTIKA</option> -->
								                                            
								 	  #end
								</select>
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
							      <input name="txtLuas1" type="text" class="$disabled" id="txtLuas1" size="30" maxlength="15" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')" />
							    #elseif($socLuas == '8' || $socLuas == '4')
							      <input type="text" name="txtLuas2" id="txtLuas2" class="$disabled" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);"/>
							      <input type="text" name="txtLuas3" id="txtLuas3" class="$disabled" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);"/>
							      <input type="text" name="txtLuas4" id="txtLuas4" class="$disabled" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')"/>      
							    #elseif($socLuas == '7')
							      <input name="txtLuas5" type="text" class="$disabled" id="txtLuas5" size="13" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" />
							      <input name="txtLuas6" type="text" class="$disabled" id="txtLuas6" size="13" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')"/>
							    #end 
							    <!--<a href="javascript:kiraLuas('$socLuas')" title="Kira luas dalam hektar." class="style1 style3">Kira Luas</a> -->
							#elseif ($mode =="view")
							    <!-- <input name="txtLuasLama" type="text" id="txtLuasLama" value="$txtLuasLama" size="15" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" /> -->
							    $!txtLuasLama
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
								<!-- <input name="txtLuas" type="text" class="disabled" id="txtLuas" onkeyup="this.value=this.value.toUpperCase();" value="$!txtLuas" size="20" readonly="readonly"/> --> $!txtLuas (Hektar) 							
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
								<!-- <input name="txtNoPelan" type="text" id="txtNoPelan" value="$!txtNoPelan" size="30" $readonly class="$disabled" onkeyup="this.value=this.value.toUpperCase();"/> -->
								$!txtNoPelan
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
	      						<!-- <input name="txtNoSyit" type="text" id="txtNoSyit" value="$!txtNoSyit" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/> -->
	      						$!txtNoSyit
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
								<!-- <input name="txtNoPu" type="text" id="txtNoPu" value="$!txtNoPu" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/> -->
								$!txtNoPu
	      					</td>
	                	</tr>                	
      						#if($statusBatal!='S' && $statusBatal!='P' && $statusBatal!='B')
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
	                   			<td width="68%" class="labeldisplay" >
									<!-- <select name="socStatus" id="socStatus" style="width:200px;" $readonly class="$disabled" $disabled> -->
							            #if($socStatus == 'D')
							          		<!-- <option value="">SILA PILIH</option>
							            	<option value="D" selected="selected"> -->D - DAFTAR <!--</option> -->
							            #else 
							                <!-- <option value="" selected="selected">SILA PILIH</option>
							                <option value="D">D - DAFTAR</option> -->
							        	#end
							        <!-- </select> -->
        	      				</td>
	                		</tr>
	                		#else
	                   		<tr>
	  							<td width="1%">
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Status Sah</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
	                   				BATAL
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
								<!-- <input name="txtPegawaiAkhir" type="text" class="disabled" id="txtPegawaiAkhir" value="$txtPegawaiAkhir" size="30" readonly="readonly" /> -->
								$!txtPegawaiAkhir
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
								<!--<input name="txdTarikhKemaskini" type="text" id="txdTarikhKemaskini" value="$txdTarikhKemaskini" size="11" maxlength="10" onkeyup="validateNumber(this,this.value);" $readonly class="disabled"/> -->
								$!txdTarikhKemaskini 							
							</td>
                		</tr>
	                	</table>
	                </td>
	       		</tr>   
	       		                 
			</table>
		</fieldset>	
  

    	</fieldset>
    </td>
  </tr>
    #if ($mode == 'update')
      <tr>
        <td>
   			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
        </td>
      </tr>
	#end
      <tr>
        <td>
			<div align="center">
			  <p> 
				<input type="button" class="stylobutton100" name="btnCetak" idtn="bCetak" value="Cetak" onclick="javascript:cetakMaklumatRizab($idHakmilik);" />
			    <input type="button" class="stylobutton100" name="btnBackRizab" id="btnBackRizab" value="Kembali" onclick="kembaliHakmilik()" />			     			
			  </p>
			</div>
        </td>
      </tr>	
</table>

##parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")
<script>
	
function kemaskini_detailRizab(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailRizab&idHakmilik="+id;
	document.${formName}.submit();
}
function update_detailRizab(id){
	////VALIDATAION
 if(document.${formName}.socNegeriHR.value == 99999){ 
	alert('Sila masukkan " Negeri " terlebih dahulu.'); 
	document.${formName}.socNegeriHR.focus();
	return; 
 }
 if(document.${formName}.txdTarikhTerima.value == ""){ 
	alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
	document.${formName}.txdTarikhTerima.focus();
	return; 
 }
 if(document.${formName}.txtNoWarta.value == ""){ 
	alert('Sila masukkan " No Warta " terlebih dahulu.'); 
	document.${formName}.txtNoWarta.focus();
	return; 
 }
 if(document.${formName}.txdTarikhWarta.value == ""){ 
	alert('Sila masukkan " Tarikh Warta " terlebih dahulu.'); 
	document.${formName}.txdTarikhWarta.focus();
	return; 
 }
 if(document.${formName}.txtLokasi.value == ""){ 
	alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
	document.${formName}.txtLokasi.focus();
	return; 
 }
 if(document.${formName}.txtKegunaanTanah.value == ""){ 
	alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
	document.${formName}.txtKegunaanTanah.focus();
	return; 
 }			
 if(document.${formName}.socLuas.value == ""){ 
	alert('Sila masukkan " Jenis Luas " terlebih dahulu.'); 
	document.${formName}.socLuas.focus();
	return; 
 }   
 if(document.${formName}.txtKegunaanTanah.value == ""){ 
	alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
	document.${formName}.txtKegunaanTanah.focus();
	return; 
 }
 if(document.${formName}.txtLuas.value == ""){ 
	alert('Sila masukkan " Luas " terlebih dahulu.'); 
	document.${formName}.txtLuas.focus();
	return; 
 }
 if(document.${formName}.socStatus.value == ""){ 
	alert('Sila masukkan " Status Sah " terlebih dahulu.'); 
	document.${formName}.socStatus.focus();
	return; 
 }        
 if ( !window.confirm("Adakah Anda Pasti ?") ){
	   return;
 }
//END OF VALIDATION
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=updateDetailRizab&idHakmilik="+id;
	document.${formName}.submit();
}
function kembaliRizab(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";	
	document.${formName}.submit();
}
function kiraLuas(idLuas){
  var jenisLuas = idLuas;
  // KILOMETER PERSEGI
  if(jenisLuas == "1"){
  	var luasK = (document.${formName}.txtLuas1.value);
	var luasH = luasK*100;
  	document.${formName}.txtLuas.value = luasH;
   }
   else
   //HEKTER
    if(jenisLuas == "2"){
  	var luasH = (document.${formName}.txtLuas1.value);
  	document.${formName}.txtLuas.value = luasH;
   }
   else
   // METER PERSEGI
   if(jenisLuas == "3"){
  	  var luasM = document.${formName}.txtLuas1.value;
  	  var luasH = (luasM*0.0001);
	  document.${formName}.txtLuas.value = luasH;
   }
   else
   //EKAR, ROOD, POLE
   if(jenisLuas == "4"){
  	  var luasE = document.${formName}.txtLuas2.value;
	  var luasR = document.${formName}.txtLuas3.value;
	  var luasP = document.${formName}.txtLuas4.value;
	  var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  document.${formName}.txtLuas.value = luasH;
   }
   else
   //KAKI PERSEGI
   if(jenisLuas == "5"){
  	  var luasAsal = document.${formName}.txtLuas1.value;
	  var luasK = luasAsal*0.0000092;
  	  document.${formName}.txtLuas.value = luasK;
  	  
   }else if(jenisLuas == "6"){	//EKAR PERPULUHAN
  	  var luasAsal = document.${formName}.txtLuas1.value;
	  /* AZAM */
	  var luasK = luasAsal*0.405;
  	  document.${formName}.txtLuas.value = luasK.toFixed(4);
  	  
   }
   else
   //EKAR,DEPA
   if(jenisLuas == "7"){
  	  var luasE = document.${formName}.txtLuas5.value;
	  var luasD = document.${formName}.txtLuas6.value;
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
  	  document.${formName}.txtLuas.value = luasH;
   }
    else
   //RELONG,JEMBA,KAKI PERSEGI
   if(jenisLuas == "8"){
  	  var luasR = document.${formName}.txtLuas2.value;
	  var luasJ = document.${formName}.txtLuas3.value;
	  var luasK = document.${formName}.txtLuas4.value;
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
  	  document.${formName}.txtLuas.value = luasH;
   }
}
function doChangeTaraf() {
	doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailRizab");
}

function kembaliHakmilik(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";	
	document.${formName}.submit();
}

//01/06/2010
function cetakMaklumatRizab(idhakmilik){
	var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatRizab&idHakmilik="+idhakmilik;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>