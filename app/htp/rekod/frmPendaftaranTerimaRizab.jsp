<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->

</style>
<input name="idHakmilik" type="hidden" value="$!idHakmilik" />
<input name="idPermohonan" type="hidden" value="$!idPermohonan" />
<input name="txtIdKementerian1" type="hidden" value="$!txtIdKementerian" />
<input name="txtIdAgensi1" type="hidden" value="$!txtIdAgensi" />
<input name="txtLuasGabung" type="hidden" value="$!txtLuasGabung" />
<input name="mode" type="hidden" value="$!mode" />
<!-- ID_LOT = 1(LOT)-->
<input name="socLot" type="hidden" value="1" />
<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch">

##parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")

<table width="99%" border="0">
  <tr>
    <td>&nbsp;    	
    </td>
  </tr>  
  <tr>
    <td>
    <fieldset>
    <legend>MAKLUMAT RIZABLALAL </legend>

	<fieldset>
		<table width="100%">
       		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%" valign="top" >
  								<span class="labelmandatory"> #if ($mode == 'update') * #end </span>
  								
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kementerian</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms" >
 								$!txtNamaKementerian
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
 								$!txtNoFailSeksyen 
 								<input type="hidden" name="txtNoFail" value="$!txtNoFailSeksyen"/>
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
                   			<td width="68%" class="labeldisplay" >
 								<!--$txtFailPTG--> 
 								<input type="text" name="txtFailPTG" id="txtFailPTG" value="$!txtFailPTG" size="45" $readonly class="$disabled"/>
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
 								<textarea readonly class="disabled" name="txtNamaFail" cols="61" rows="3" 
                                class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase()">$!txtTajuk</textarea>
  							</td>
                		</tr>
                 	</table>
           		</td>
                        	
                <td valign="top">
               		<table width="100%">
                   	<tr>
  							<td width="1%" valign="top" >
  								<span class="labelmandatory"> #if ($mode == 'update') * #end </span>
  								
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Agensi</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms" >
 								$!txtNamaAgensi
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
 								$!txtFailKJP
 								<input type="hidden" name="txtFailKJP" value="$txtFailKJP"/>
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
                   			<td width="68%" class="labeldisplay" >
 								<!--$!txtFailPTD -->
 								<input type="text" name="txtFailPTD" id="txtFailPTD" value="$!txtFailPTD" size="45" $readonly class="$disabled"/>
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
 								$!selectNegeri
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
 								$!selectDaerah
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
 								$!selectMukim
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
								<input name="txtNoWarta" type="text" id="txtNoWarta" value="$!txtNoWarta" size="30" $readonly class="$disabled"/>
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
						<input name="txtNoLot" type="text" class="$disabled" id="txtNoLot" size="30" maxlength="25" value="$!txtNoLot" $readonly />
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
								<input name="txdTarikhWarta" type="text" id="txdTarikhWarta" value="$!txdTarikhWarta" size="11" maxlength="10" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this); validateTarikhWarta();"/>
        						#if ($mode == 'update') 
        							<a href="javascript:displayDatePicker('txdTarikhWarta',false,'dmy');"> <img border="0" src="../img/calendar.gif"/>
      								 <span class="pautanms">dd/mm/yyyy </span> </a>
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
 								<input name="txdTarikhTerima" type="text" id="txdTarikhTerima" value="$!txdTarikhTerima" size="11" maxlength="10" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this); validateTarikhWarta();"/>
    							#if ($mode == 'update') 
    								<a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"> <img border="0" src="../img/calendar.gif"/>
    								<span class="pautanms">dd/mm/yyyy</span></a> 
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
                   			<td width="68%" class="labeldisplay" >
								<select name="socLuas" id="socLuas" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeTarafRizab('$!changeLuas')">
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
                            
							#if($mode == 'new' || $mode == 'update')
							    #if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6')
							      <input value="$!txtLuasLama" name="txtLuas1" type="text" class="$disabled" id="txtLuas1" size="30" maxlength="15" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')" />
							    #elseif($socLuas == '8' || $socLuas == '4')
							      <input value="$!txtLuasLama" type="text" name="txtLuas2" id="txtLuas2" class="$disabled" size="8" maxlength="8"  $readonly onkeyup="validateNumber(this,this.value);"/>
							      <input value="$!txtLuasLama1" type="text" name="txtLuas3" id="txtLuas3" class="$disabled" size="8" maxlength="8"  $readonly onkeyup="validateNumber(this,this.value);"/>
							      <input value="$!txtLuasLama2" type="text" name="txtLuas4" id="txtLuas4" class="$disabled" size="8" maxlength="8"  $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')"/>      
							    #elseif($socLuas == '7')
							      <input value="$!txtLuasLama" name="txtLuas5" type="text" class="$disabled" id="txtLuas5" size="13" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" />
							      <input value="$!txtLuasLama1" name="txtLuas6" type="text" class="$disabled" id="txtLuas6" size="13" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')"/>
							    #end 
							    <!--<a href="javascript:kiraLuas('$socLuas')" title="Kira luas dalam hektar." class="style1 style3">Kira Luas</a> -->
							#elseif ($mode =="view" )
							    <input value="$!txtLuasLama" name="txtLuasLama" type="text" id="txtLuasLama"  size="30" maxlength="15" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" />
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
								<input name="txtNoPelan" type="text" id="txtNoPelan" value="$!txtNoPelan" size="20" $readonly class="$disabled" />
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
      							<input name="txtNoSyit" type="text" id="txtNoSyit" value="$!txtNoSyit" size="20" $readonly class="$disabled" />
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
								<input name="txtNoPu" type="text" id="txtNoPu" value="$!txtNoPu" size="20" $readonly class="$disabled" />
      						</td>
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
								<textarea name="txtKemAgenTerkini" cols="27" rows="5" style="text-transform:uppercase;" class="$disabled" id="txtKemAgenTerkini" $readonly>$!txtKemAgenTerkini</textarea>  							
							</td>
                		</tr>      						
                		</tr>
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
								<input name="txtPegawaiAkhir" type="text" class="disabled" id="txtPegawaiAkhir" value="$!pegawaiAkhir" size="30" readonly="readonly" />
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
								<input name="txdTarikhKemaskini" type="text" id="txdTarikhKemaskini" value="$!txdTarikhKemaskini" size="11" maxlength="10" onkeyup="validateNumber(this,this.value);" $readonly class="disabled"/>  							
							</td>
                		</tr>
                	</table>
                </td>
       		</tr>   
       		                 
		</table>
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
			    #if($statusBatal!='S' && $statusBatal!='P' && $statusBatal!='B')
				  	#if ($mode == 'view')
				    	<input type="button" class="stylobutton100" name="btnUpdate" id="btnUpdate" value="Kemaskini" onclick="kemaskiniMaklumatRizab($idHakmilik)"/>
				    #end
				    
				    #if ($mode == 'update' || $mode == 'new')  
				   		<!-- Asal
				    	<input type="button" name="btnSaveRizab" id="btnSaveRizab" value="Simpan" onclick="update_detailRizab($idHakmilik)"/>
				    	-->
				    	<input type="button" class="stylobutton100" name="btnSaveRizab" id="btnSaveRizab" value="Simpan" onclick="simpanRizab($idHakmilik)"/>
				    	<input type="reset" class="stylobutton100" name="btnResetRizab" id="btnResetRizab"  value="Kosongkan"/>
				    #end
			    #end
			    
			    #if($!flagPendaftaran.equals('permohonan'))
    				<input type="button" class="stylobutton100" name="btnBackRizab" id="btnBackRizab" value="Kembali" onclick="kembaliRizab()"/> 
                	#if ($mode == 'view')
				    	<input type="button" name=class="stylobutton100" "Cetak" id="Cetak" value="Cetak" onclick="javascript:cetakMaklumatRizab($idHakmilik);" />
				    #end 
			    #else
			    	<input type="button" class="stylobutton100" name="btnBackRizab" id="btnBackRizab" value="Kembali" onclick="rizab_detail2('$idPermohonan')"/>
				#end
			        <!--<input type="button" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:cetakMaklumatRizab($idHakmilik);" class="stylobutton" />-->
			  </p>
			</div>        	
        </td>
      </tr>	
</table>

##parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")
<script>
function kemaskini_detailRizab(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah2&firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailRizab&idHakmilik="+id;
	document.${formName}.submit();
}
//X Guna
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
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah2&firstAction=PendaftaranHakmilik&nextAction=updateDetailRizab&idHakmilik="+id;
	document.${formName}.submit();
}
function kembaliRizab(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah2&firstAction=carianHakmilikRizab";	
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
</script>