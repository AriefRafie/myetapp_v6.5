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
                            		<span class="labelinput">Tarikh Daftar Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" size="11" maxlength="10" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhDaftar);" />
      							#if ($mode == 'update') 
      								<a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');" > <img border="0" src="../img/calendar.gif"/>
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
                            		<span class="labelinput">Tarikh Terima Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
 								<input name="txdTarikhTerima" type="text" id="txdTarikhTerima" value="$txdTarikhTerima" size="11" maxlength="10" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhTerima);"/>
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
                            		<span class="labelinput">Taraf Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
	                   		#if ($mode == 'view')
	                   				#set( $staraf = '' )
	                   				#if($socTaraf == 'P')
										#set( $staraf = 'P - PAJAKAN')
									#elseif($socTaraf == 'S')	
										#set( $staraf = 'S - PEGANGAN BEBAS/FREE HOLD')																			
									#end
									$!staraf
								#else							
								<select name="socTaraf" id="socTaraf" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeTaraf()">								             
								#if($socTaraf == 'P')
								          
								    <option value="">SILA PILIH</option>
									<option value="P" selected="selected">P - PAJAKAN</option>
								    <option value="S">S - PEGANGAN BEBAS/FREE HOLD</option>
								      
								#elseif($socTaraf == 'S')
								          
									<option value="">SILA PILIH</option>
								    <option value="P">P - PAJAKAN</option>
								   	<option value="S" selected="selected">S - PEGANGAN BEBAS/FREE HOLD</option>
								      
								#else
								          
									<option value="" selected="selected">SILA PILIH</option>
								    <option value="P">P - PAJAKAN</option>
								   	<option value="S">S - PEGANGAN BEBAS/FREE HOLD</option>
								      
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
                            		<span class="labelinput">Tempoh</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
							#if($socTaraf == 'P')
      							<input name="txtTempoh" type="text" id="txtTempoh" value="$txtTempoh" size="4" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="cal_tarikh_luput()"/>
      						#else
      							<input name="txtTempoh" type="text" id="txtTempoh" value="" size="4" readonly="readonly" class="disabled" onchange="cal_tarikh_luput()" onkeyup="validateNumber(this,this.value);" onblur="cal_tarikh_luput()"/>
      						#end Tahun  			
      						</td>
                		</tr>
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Luput</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
						    #if($socTaraf == 'P')
						      <input name="txdTarikhLuput" type="text" id="txdTarikhLuput" value="$txdTarikhLuput" size="11" maxlength="10" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);" />
						    #else
						    	<input name="txdTarikhLuput" type="text" id="txdTarikhLuput" value="" size="11" maxlength="10" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);" />
						  	#end      						
      						</td>
                		</tr>
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Cukai Tahun Pertama (RM)</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
    							<input name="txtCukaiTahun" type="text" id="txtCukaiTahun" value="$txtCukaiTahun" size="20" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="formatCurrencyPertama(this.value)"/> 						
      						</td>
                		</tr>
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Cukai Semasa (RM)</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
						        <input name="txtCukaiTerkini" type="text" id="txtCukaiTerkini" value="$txtCukaiTerkini" size="20" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="formatCurrencySemasa(this.value)"/>
						        <!--<span class="pautan">-->
						        #if ($mode == 'update') 
						        	<a href="javascript:viewTransaksiCukai('$idHakmilik')" title="Papar transaksi cukai terdahulu" class="pautanms">Cukai Terdahulu</a>     						
						       	#end <!--</span>-->
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
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Hakmilik Asal</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<input name="txtNoHakmilikAsal" type="text" id="txtNoHakmilikAsal" value="$txtNoHakmilikAsal" size="20" readonly="readonly" class="disabled" />
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
									<input name="socLuas" type="hidden" value="$!socLuas" />
									
								#else
								<select name="socLuas" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeTaraf()">
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
								         
								       #elseif($socLuas == '' || $socLuas == '0')
								        
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
							      <input type="text" name="txtLuas1" class="$disabled" id="txtLuas1" value="$!txtLuasLama" size="30" maxlength="15" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')" />
							    #elseif($socLuas == '8' || $socLuas == '4')
							      <input type="text" name="txtLuas2" id="txtLuas2" class="$disabled" value="$!txtLuasLama" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);"/>
							      <input type="text" name="txtLuas3" id="txtLuas3" class="$disabled" value="$!txtLuasLama1" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);"/>
							      <input type="text" name="txtLuas4" id="txtLuas4" class="$disabled" value="$!txtLuasLama2" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')"/>      
							    #elseif($socLuas == '7')
							      <input type="text" name="txtLuas5" id="txtLuas5" class="$disabled" value="$!txtLuasLama" size="13" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" />
							      <input type="text" name="txtLuas6" id="txtLuas6" class="$disabled" value="$!txtLuasLama1" size="13" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')"/>
							    #end 
							    <!--<a href="javascript:kiraLuas('$socLuas')" title="Kira luas dalam hektar." class="style1 style3">Kira Luas</a> -->
							#elseif ($mode =="view")
							    <input name="txtLuasLama" type="text" id="txtLuasLama" value="$!txtLuasLama" size="30" maxlength="15" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" />
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
								<input name="txtLuas" type="text" class="disabled" id="txtLuas" onkeyup="this.value=this.value.toUpperCase();" value="$!txtLuas" size="30" readonly="readonly"/>(Hektar) 							
							</td>
                		</tr>
                   		<tr>
  							<td width="1%">
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
                        	
                <td valign="top">
               		<table width="100%">
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Rizab</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
	                   		#if ($mode == 'view')
	                   				#set( $srizab = '' )
	                   				#if($socRizab == 'Y')
										#set( $srizab = 'Y - YA')
									#elseif($socRizab == 'T')	
										#set( $srizab = 'T - TIDAK')																			
									#end
									$!srizab
							#else	                   				
			                   
								<select name="socRizab" id="socRizab" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeTaraf()">
							
							        #if($socRizab == 'Y')
								    <option value="">SILA PILIH</option>
							        <option value="Y" selected="selected"> Y - YA</option>
							        <option value="T"> T - TIDAK</option>
							        #elseif ($socRizab == 'T') 
							   	    <option value="">SILA PILIH</option>
							        <option value="Y"> Y - YA</option>
							        <option value="T"selected="selected"> T - TIDAK</option>
							        #else   
							      	<option value="" selected="selected">SILA PILIH</option>
							        <option value="Y"> Y - YA</option>
							        <option value="T"> T - TIDAK</option>
							        #end
							    </select>
							#end    
        					</td>
                		</tr>
                   		<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No Rizab</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
      							<input name="txtNoRizab" type="text" id="txtNoRizab" value="$txtNoRizab" $readonly class="$disabled"/>
        					</td>
                		</tr>
                   		<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Rizab</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<input name="txdTarikhRizab" type="text" id="txdTarikhRizab" value="$txdTarikhRizab" size="11" maxlength="10" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhRizab);"/>
      							#if ($mode == 'update') 
      								<a href="javascript:displayDatePicker('txdTarikhRizab',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> 
      								<span class="pautanms">dd/mm/yyyy</span></a>
      							#end
      						</td>
                		</tr>
                   		<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Jenis Rizab</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
								$selectRizab
        					</td>
                		</tr>
                		<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kawasan Rizab</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<input name="txtKawasanRizab" type="text" id="txtKawasanRizab" style="text-transform:uppercase;" value="$txtKawasanRizab" size="27" $readonly class="$disabled"/>
        					</td>
                		</tr>
                		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kategori</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
								$selectKategori
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
                   			<td width="68%" class="labeldisplay" >
        						<textarea name="txtSyarat" cols="27" rows="5" id="txtSyarat" style="text-transform:uppercase;"  $readonly class="$disabled">$txtSyarat </textarea>
  							</td>
                		</tr>

                    	<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%" valign="top" >
                            	<div align="left">
                            		<span class="labelinput">Sekatan Kepentingan</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<textarea name="txtSekatan" cols="27" rows="5" id="txtSekatan" style="text-transform:uppercase;" $readonly="$readonly" class="$disabled">$txtSekatan</textarea>  							
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
								<textarea name="txtKemAgenTerkini" cols="27" rows="5" class="$disabled" id="txtKemAgenTerkini" $readonly="$readonly">$txtKemAgenTerkini</textarea>  							
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
								<input name="txtNoPelan" type="text" id="txtNoPelan" value="$!txtNoPelan" size="30" $readonly class="$disabled" onkeyup="this.value=this.value.toUpperCase();"/>
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
	      						<input name="txtNoSyit" type="text" id="txtNoSyit" value="$!txtNoSyit" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>
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
								<input name="txtNoPu" type="text" id="txtNoPu" value="$!txtNoPu" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>
	      					</td>
	                	</tr>                		
   					#if($statusBatal!='S' && $statusBatal!='P' && $statusBatal!='B')
   							
						#if ($mode == 'update')                   	
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
							 <!--<select name="socStatus" style="width:200px;" $readonly class="$disabled" $disabled onChange="doChangeTaraf()"> -->
								<select name="socStatusDaftar" style="width:200px;" $readonly class="$disabled" $disabled onChange="doChangeTaraf()">
									#if($socStatus == 'D')
								
								     	<option value="">SILA PILIH</option>
								        <option value="D" selected="selected">D - DAFTAR</option>
								        <option value="H">H - HAKMILIK ASAL TIADA</option>
								        <option value="T">T - TUKARGUNA</option>
								        <option value="P">P - BATAL (PELEPASAN)</option>
								        <option value="S">S - BATAL (SAMBUNGAN)</option>
								        <option value="M">M - BATAL (PINDAHMILIK)</option>
								
								    #elseif($socStatus == 'H')
								
								     	<option value="">SILA PILIH</option>
								        <option value="D">D - DAFTAR</option>
								        <option value="H" selected="selected">H - HAKMILIK ASAL TIADA</option>
								        <option value="T">T - TUKARGUNA</option>
								        <option value="P">P - BATAL (PELEPASAN)</option>
								        <option value="S">S - BATAL (SAMBUNGAN)</option>
								        <option value="M">M - BATAL (PINDAHMILIK)</option>
								
								    #elseif($socStatus == 'P')
								                
								        <option value="">SILA PILIH</option>
								        <option value="D">D - DAFTAR</option>
								        <option value="H">H - HAKMILIK ASAL TIADA</option>
								        <option value="T">T - TUKARGUNA</option>
								        <option value="P" selected="selected" >P - BATAL (PELEPASAN)</option>
								        <option value="S">S - BATAL (SAMBUNGAN)</option>
								        <option value="M">M - BATAL (PINDAHMILIK)</option>
								
								    #elseif($socStatus == 'S')
								
								        <option value="">SILA PILIH</option>
								        <option value="D">D - DAFTAR</option>
								        <option value="H">H - HAKMILIK ASAL TIADA</option>
								        <option value="T">T - TUKARGUNA</option>
								        <option value="P">P - BATAL (PELEPASAN)</option>
								        <option value="S" selected="selected" >S - BATAL (SAMBUNGAN)</option>
								        <option value="M">M - BATAL (PINDAHMILIK)</option>
								
								    #elseif($socStatus == 'T')
								                
								      	<option value="">SILA PILIH</option>
								        <option value="D">D - DAFTAR</option>
								        <option value="H">H - HAKMILIK ASAL TIADA</option>
								        <option value="T" selected="selected">T - TUKARGUNA</option>
								        <option value="P">P - BATAL (PELEPASAN)</option>
								        <option value="S">S - BATAL (SAMBUNGAN)</option>
								        <option value="M">M - BATAL (PINDAHMILIK)</option>
								    
								    #elseif($socStatus == 'M')
								                
								      	<option value="">SILA PILIH</option>
								        <option value="D">D - DAFTAR</option>
								        <option value="H">H - HAKMILIK ASAL TIADA</option>
								        <option value="T">T - TUKARGUNA</option>
								        <option value="P">P - BATAL (PELEPASAN)</option>
								        <option value="S">S - BATAL (SAMBUNGAN)</option>
								        <option value="M" selected="selected">M - BATAL (PINDAHMILIK)</option>
								           
								    #else
								
								      	<option value="" selected="selected">SILA PILIH</option>
								        <option value="D">D - DAFTAR</option>
								        <option value="H">H - HAKMILIK ASAL TIADA</option>
								        <option value="T">T - TUKARGUNA</option>
								        <option value="P">P - BATAL (PELEPASAN)</option>
								        <option value="S">S - BATAL (SAMBUNGAN)</option>
								        <option value="M">M - BATAL (PINDAHMILIK)</option>
								
								    #end
								
								    </select>
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
		      	  						<input type="hidden" name="socStatusDaftar" value="$!socStatus" />
		      	  						$!socStatusTanah		      	  					
									</td>
		                	</tr> 
                			
                		#end
                			
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
      	  					##set( $socStatusTanahTemp = '')
      	  					#if($socStatus=='S')
      	  						BATAL (SAMBUNGAN)
					  	  	#else
								$!socStatusTanah  	  	
					  	  	#end
		      	  			<input type="hidden" name="socStatusDaftar" value="$!socStatus" />
      	  					<!-- <input name="txtBatal" type="text" id="txtBatal" value="BATAL" size="11" maxlength="10" $readonly class="disabled"/> -->  							     	  					
							</td>
                		</tr> 
  					#end
                	</table>
                </td>
       		</tr>   
       		                 
		</table>
	</fieldset>	