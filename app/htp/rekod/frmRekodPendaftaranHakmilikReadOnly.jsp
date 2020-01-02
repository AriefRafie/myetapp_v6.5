<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>
<input name="idHakmilik" type="hidden" value="$idHakmilik" />
<input name="mode" type="hidden" value="$mode" />
<input name="txtIdHakmilikCukai" type="hidden" value="$idHakmilikCukai" />
<input name="txtCukaiSemasa" type="hidden" value="$txtCukaiTerkini" />
<input name="txtKodSocJenisHakmilik" type="hidden" value="$txtKodSocJenisHakmilik" />

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
			<legend>MAKLUMAT HAKMILIK</legend>
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
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Tajuk</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
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
		                    	<tr>
		  							<td width="1%" valign="top" >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Cara Perolehan</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="pautanms" >
		 								$caraPerolehan
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
		                            		<span class="labelinput">Jenis Hakmilik</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		 								$!selectJenisHakmilikHR
		  							</td>
		                		</tr>
		              			
		            			<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">No. Hakmilik</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		      							<!--<input name="txtNoHakmilik" type="text" class="$disabled" id="txtNoHakmilik" maxlength="50" value="$txtNoHakmilik" size="20" $readonly />-->
		  								$!txtNoHakmilik
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
		      							<!--<input name="txtNoBangunan" type="text" class="$disabled" id="txtNoBangunan" onkeyup="validateNumber(this,this.value);" value="$txtNoBangunan" size="3" $readonly />
										<input name="txtNoTingkat" type="text" class="$disabled" id="txtNoTingkat" onkeyup="validateNumber(this,this.value);" value="$txtNoTingkat" size="3" $readonly />
										<input name="txtNoPetak" type="text" class="$disabled" id="txtNoPetak" onkeyup="validateNumber(this,this.value);" value="$txtNoPetak" size="3" $readonly />
		  							-->$!txtNoBangunan $!txtNoTingkat $!txtNoPetak
		  							</td>
		                		</tr>
		                    	<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Kod</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		 								$!selectJenisLotHR
		  							</td>
		                		</tr>
		                    	<tr>
		  							<td width="1%"  >
		  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">No. Lot/PT</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		      							<!--<input name="txtNoLot" type="text" class="$disabled" id="txtNoLot" maxlength="15" value="$txtNoLot" size="20" $readonly /> -->
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
		                            		<span class="labelinput">Tarikh Daftar Hakmilik</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
										<!--<input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" size="11" maxlength="10" $readonly class="$disabled" onblur="check_date(this)" />-->
		      							$!txdTarikhDaftar
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
		 								<!--<input name="txdTarikhTerima" type="text" id="txdTarikhTerima" value="$txdTarikhTerima" size="11" maxlength="10" $readonly class="$disabled" onblur="check_date(this)"/> -->
		    							$!txdTarikhTerima
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
		                   			<td width="68%" class="labeldisplay" >
										<!--
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
										</select> -->
										#if($socTaraf == 'P')
										   P - PAJAKAN
										      
										#elseif($socTaraf == 'S')
											S - PEGANGAN BEBAS/FREE HOLD
										      
										#else								         
										      
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
		      							<!--<input name="txtTempoh" type="text" id="txtTempoh" value="$txtTempoh" size="4" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="cal_tarikh_luput()"/> -->
		      							$!txtTempoh Tahun 
		      						#else
		      							<!--<input name="txtTempoh" type="text" id="txtTempoh" value="" size="4" readonly="readonly" class="disabled" onchange="cal_tarikh_luput()" onkeyup="validateNumber(this,this.value);" onblur="cal_tarikh_luput()"/> -->
		      						#end  			
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
								      <!-- <input name="txdTarikhLuput" type="text" id="txdTarikhLuput" value="$txdTarikhLuput" size="11" maxlength="10" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);" /> -->
								    	$!txdTarikhLuput
								    #else
								    	<!-- <input name="txdTarikhLuput" type="text" id="txdTarikhLuput" value="" size="11" maxlength="10" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);" /> -->
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
		    							<!--<input name="txtCukaiTahun" type="text" id="txtCukaiTahun" value="$txtCukaiTahun" size="20" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="formatCurrencyPertama(this.value)"/> --> 						
		      							$!txtCukaiTahun
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
								        <!--<input name="txtCukaiTerkini" type="text" id="txtCukaiTerkini" value="$txtCukaiTerkini" size="20" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="formatCurrencySemasa(this.value)"/> -->
								        $!txtCukaiTerkini
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
										<!--<input name="txtLokasi" type="text" id="txtLokasi" value="$txtLokasi" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>		-->
										$!txtLokasi
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
										<!--<input name="txtNoHakmilikAsal" type="text" id="txtNoHakmilikAsal" value="$txtNoHakmilikAsal" size="20" readonly="readonly" class="disabled" /> -->
						   			$!txtNoHakmilikAsal
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
									      <input name="txtLuas1" type="text" class="$disabled" id="txtLuas1" size="3" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')" />
									    #elseif($socLuas == '8' || $socLuas == '4')
									      <input type="text" name="txtLuas2" id="txtLuas2" class="$disabled" size="3" $readonly onkeyup="validateNumber(this,this.value);"/>
									      <input type="text" name="txtLuas3" id="txtLuas3" class="$disabled" size="3" $readonly onkeyup="validateNumber(this,this.value);"/>
									      <input type="text" name="txtLuas4" id="txtLuas4" class="$disabled" size="3" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')"/>      
									    #elseif($socLuas == '7')
									      <input name="txtLuas5" type="text" class="$disabled" id="txtLuas5" size="3" $readonly onkeyup="validateNumber(this,this.value);" />
									      <input name="txtLuas6" type="text" class="$disabled" id="txtLuas6" size="3" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')"/>
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
		  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Rizab</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
										<!--<select name="socRizab" id="socRizab" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeTaraf()">
										-->
									        #if($socRizab == 'Y')
										    <!-- <option value="">SILA PILIH</option>
									        <option value="Y" selected="selected"> -->Y - YA <!--</option>
									        <option value="T"> T - TIDAK</option> -->
									        #elseif ($socRizab == 'T') 
									   	    <!-- <option value="">SILA PILIH</option>
									        <option value="Y"> Y - YA</option>
									        <option value="T"selected="selected"> --> T - TIDAK <!-- </option> -->
									        #else   
									      	<!-- <option value="" selected="selected">SILA PILIH</option>
									        <option value="Y"> Y - YA</option>
									        <option value="T"> T - TIDAK</option> -->
									        #end
									    <!--</select> -->
		        					</td>
		                		</tr>
		                   		<tr>
		  							<td width="1%"  >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">No. Rizab</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="labeldisplay" >
		      							<!--<input name="txtNoRizab" type="text" id="txtNoRizab" value="$txtNoRizab" $readonly class="$disabled"/> -->
		      							$!txtNoRizab
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
										<!-- <input name="txdTarikhRizab" type="text" id="txdTarikhRizab" value="$txdTarikhRizab" size="11" maxlength="10" $readonly class="$disabled" onblur="check_date(this)"/> -->
		      							$!txdTarikhRizab
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
		                   			<td width="68%" class="labeldisplay" >
										$!selectRizab
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
										<!--<input name="txtKawasanRizab" type="text" id="txtKawasanRizab" style="text-transform:uppercase;" value="$txtKawasanRizab" size="27" $readonly class="$disabled"/> -->
										$!txtKawasanRizab
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
		                   			<td width="68%" class="labeldisplay" >
										$!selectKategori
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
		        						<!--<textarea name="txtSyarat" cols="27" rows="5" id="txtSyarat" style="text-transform:uppercase;"  $readonly class="$disabled">$txtSyarat </textarea> -->
		  								$!txtSyarat
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
										<!--<textarea name="txtSekatan" cols="27" rows="5" id="txtSekatan" style="text-transform:uppercase;" $readonly="$readonly" class="$disabled">$txtSekatan</textarea> -->
										$!txtSekatan  							
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
										<!-- <textarea name="txtKemAgenTerkini" cols="27" rows="5" class="$disabled" id="txtKemAgenTerkini" $readonly="$readonly">$txtKemAgenTerkini</textarea> -->
										$!txtKemAgenTerkini 							
									</td>
		                		</tr>
			                   	<tr>
			  						<td width="1%"  >
							        </td>				        
			                        <td width="30%">
			                           	<div align="left">
			                           		<span class="labelinput">No. Pelan Diperakui</span>
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
			                           		<span class="labelinput">No. Permohonan Ukur</span>
			                           	</div>
			                        </td>
			                  		<td width="1%" class="labelinput" >:</td>
			                   		<td width="68%" class="labeldisplay" >
										<!-- <input name="txtNoPu" type="text" id="txtNoPu" value="$!txtNoPu" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/> -->
										$!txtNoPu
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
										<select name="socStatus" id="socStatus" style="width:200px;" $readonly class="$disabled" $disabled onChange="doChangeTaraf()">
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
		      	  					$!socStatusTanah
		      	  					<!-- <input name="txtBatal" type="text" id="txtBatal" value="BATAL" size="11" maxlength="10" $readonly class="disabled"/> -->  							     	  					
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
			
			#if ($mode == 'update')
			<table>
				<tr>
					<td colspan="2">
						<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
					</td>
				</tr>
			</table>
		   #end
		   
			</br>
			 
			#if($statusBatal=='S' || $socStatus=="S" || $statusBatal=="B" )
			<fieldset>
			   	<legend>SENARAI HAKMILIK SAMBUNGAN</legend>
			   	<table border="0" width="100%">
			     	<tr class="table_header">
			       	<td width="3%">Bil.</td>
			       	<td width="87%">No. Hakmilik Sambungan</td>
			       	<td width="10%">Tindakan</td>
			     	</tr>
			     	#foreach ($senarai in $listSambungan)
			      	#set( $i = $velocityCount )
			      	#if ( ($i % 2) != 1 )
			      		#set( $row = 'row2')
			      	#else
			      		#set( $row = 'row1')
			      	#end
			  		<tr class="$row">
			    		<td>$senarai.bil</td>
			    		#if ($mode == 'view')	    				
			    		<td>
			    			<a href="javascript:hakmilikDetailLagi('$senarai.idHakmilikBaru','$senarai.statusSah');" class="pautanms">$senarai.kodJenisBaru $senarai.noHakmilikBaru</a>
				    	</td>    		
			    		#else
			    		<td>
			    			$senarai.kodJenisBaru $senarai.noHakmilikBaru
			    		</td>
			    		#end
			    		
			    		#if($senarai.bil!='')
			    			<td>
			    				#if ($mode != 'view')	    				
			    				<input type="button" class="stylobutton" name="cmdHapus" id="cmdHapus" value="Hapus" onclick = "deleteHakmilikBaru('$senarai.idHakmilikAsal','$senarai.idHakmilikBaru')">
			   				#end
			   			</td>
			   		#else
			   			<td>&nbsp;</td>
			   		#end 
			   	</tr>
			    	#end
			  	</table>
			  </fieldset>
			#end
		  
			#if($socStatus=="S")
		    <fieldset>
		    	<legend>PENDAFTARAN HAKMILIK SAMBUNGAN</legend>
		    	<table width="100%" border="0">
		        <tr>
		        	<!--<td valign="top" width="1%"><div align="center">:</div></td>-->
		          <td class="labelinput"><div align="left" >#if ($mode !='view')<i><font color="#ff0000">*</font></i> #end Jenis Hakmilik Sambungan</div></td>
		          <td><div align="center">:</div></td>
		          <td width="56%">$selectJenisHakmilikBaru</td>
		        </tr>
		        <tr>
		          <td width="43%" class="labelinput"><div align="left">#if ($mode !='view')<i><font color="#ff0000">*</font></i> #end No. Hakmilik Sambungan</div></td>
		          <td width="1%"><div align="center">:</div></td>
		          <td><label>
		            <input name="txtHakmilikBerikut" type="text" class="$disabled2" id="txtHakmilikBerikut" value="$!txtHakmilikBerikut" size="30" $readonly2/>
		          </label></td>
		        </tr>
		        <!--<tr>
		          <td colspan="3">&nbsp;</td>
		        </tr>-->
		      	</table>
		    </fieldset>
		   	#end
		   	 
			<table width="100%"></table>
		</fieldset>
			
			##parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")
	
			<fieldset id="tableReport1" style="display:none;">
				<legend>SENARAI LAPORAN</legend>
				<table width="100%" border="0">
				  <tr>
				    <td><a href="javascript:cetakHakmilik($idHakmilik);" class="pautanms">Maklumat Hakmilik</a></td>
				  </tr>
				</table>
			</fieldset>
	
			<!-- start add new -->
			<fieldset>
				<legend>SENARAI PEMBANGUNAN</legend>
				<table border="0" width="100%">
					<tr>
						<td colspan="10"><label></label></td>
					</tr>
					<tr class="table_header">
						<td width="3%"><div valign="center">Bil.</div></td>
						<td width="20%"><div valign="center">Jenis Binaan</div></td>
						<td width="20%"><div valign="center">No. Rujukan JKR</div></td>
						<td width="10%"><div valign="center">Tarikh Binaan</div></td>
						<td width="10%"><div align="center">Harga (RM)</div></td>
						<td width="10%"><div align="center">Luas (Hektar)</div></td>
						<td width="30%"><div align=center>Catatan</div></td>
					</tr>	
					
					#if($SenaraiPembangunan.size()!=0)
						#foreach ($senaraiB in $SenaraiPembangunan)
							#set( $i = $velocityCount )
							#if ( ($i % 2) != 1 )
								#set( $row = "row2")
							#else
								#set( $row = "row1")
							#end 
			
							<tr class="$row">
								<td>
									<div align="left">
										<!--  <a href="javascript:viewDetailPerihal('$senaraiB.idHakmilikPerihal')"></a>-->
										$senaraiB.bil
									</div>
								</td>
								<td>
									<div valign="left">
										#set($strBinaan = "")
										#if($senaraiB.jenisBangunan=="P")
											#set($strBinaan = "PADANG")
										#elseif($senaraiB.jenisBangunan=="B")
											#set($strBinaan = "BANGUNAN")
										#elseif($senaraiB.jenisBangunan=="PR")
											#set($strBinaan = "PARKING")
										#elseif($senaraiB.jenisBangunan=="J")
											#set($strBinaan = "JALAN")   
										#elseif($senaraiB.jenisBangunan=="L")
											#set($strBinaan = "LAIN-LAIN")    
										#else
											#set($strBinaan = "")	   
										#end
			
										<!--zul disable sebab x perlu view detail pun <a href="javascript:viewPembangunan('$senaraiB.idHakmilikPerihal','$senaraiB.idSusulanStatus','$senaraiB.idStatusFail','$!senaraiB.pautan')" class="pautanms">$!strBinaan</a> -->
										$!strBinaan
										
									</div>
								</td>
								<td ><div valign="left">$senaraiB.noRujukanJKR</div></td>
								<td ><div valign="left">$senaraiB.tarikhBina</div></td>
								<td ><div align="right">$senaraiB.hargaBina</div></td>
								<td >
									<div align="right">
										#if($senaraiB.jenisBangunan=="P")
											$senaraiB.luasP
										#elseif($senaraiB.jenisBangunan=="B")
											$senaraiB.luasB
										#elseif($senaraiB.jenisBangunan=="PR")
											$senaraiB.luasPR
										#elseif($senaraiB.jenisBangunan=="J")
											$senaraiB.luasJ      
										#elseif($senaraiB.jenisBangunan=="L")
											$senaraiB.luasL      
										#end 
									</div>
								</td>
								<td>
									<div valign="left">$senaraiB.catatan</div>
								</td>
							</tr>
						#end
							
					#else
						<td colspan="7">Tiada Rekod</td>
					#end
				</table>
			</fieldset>
			<!-- end add new -->
		</td>
	</tr>	
	<tr>
	    <td ><div align="center">	        
	        <p>	
		   	  <input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:cetakMaklumatHakmilik($idHakmilik);" />
	         <input type="button" class="stylobutton100" name="btnBackHakmilik" id="btnBackHakmilik" value="Kembali" onclick="kembaliHakmilik()" />
	        </p>
	    </div></td>
	</tr>
</table>

<script>
	
function kemaskini_detailHakmilik(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&idHakmilik="+id;
	document.${formName}.submit();
}
function doChangeStateHR() {
  doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeStateHR");
}
function doChangeDaerahHR() {
	doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeDaerahHR");
}
function doChangeMukimHR() {
	doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeMukimHR");
}

function update_detailHakmilik(id){
////VALIDATAION
 if(document.${formName}.socNegeriHR.value == 99999){ 
	alert('Sila masukkan " Negeri " terlebih dahulu.'); 
	document.${formName}.socNegeriHR.focus();
	return; 
 }
 if(document.${formName}.socDaerahHR.value == ""){ 
	alert('Sila masukkan " Daerah " terlebih dahulu.'); 
	document.${formName}.socDaerahHR.focus();
	return; 
 }
 if(document.${formName}.socMukimHR.value == ""){ 
	alert('Sila masukkan " Bandar/Pekan/Mukim " terlebih dahulu.'); 
	document.${formName}.socMukimHR.focus();
	return; 
 }
 if(document.${formName}.txdTarikhTerima.value == ""){ 
	alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
	document.${formName}.txdTarikhTerima.focus();
	return; 
 }
 if(document.${formName}.txdTarikhDaftar.value == ""){ 
	alert('Sila masukkan " Tarikh Daftar " terlebih dahulu.'); 
	document.${formName}.txdTarikhDaftar.focus();
	return; 
 }
 if(document.${formName}.socTaraf.value == ""){ 
	alert('Sila masukkan " Taraf Hakmilik " terlebih dahulu.'); 
	document.${formName}.socTaraf.focus(); 
	return; 
 }	
 if(document.${formName}.txtCukaiTahun.value == ""){ 
	alert('Sila masukkan " Cukai Tahun Pertama " terlebih dahulu.'); 
	document.${formName}.txtCukaiTahun.focus();
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
 if(document.${formName}.txtLuas.value == ""){ 
	alert('Sila masukkan " Luas " terlebih dahulu.'); 
	document.${formName}.txtLuas.focus();
	return; 
 }
 if(document.${formName}.socKategori.value == ""){ 
	alert('Sila masukkan " Kategori " terlebih dahulu.'); 
	document.${formName}.socKategori.focus();
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
  var str1 = document.${formName}.txdTarikhTerima.value; 
  var dt1 = parseInt(str1.substring(0,2),10); 
  var mon1 = parseInt(str1.substring(3,5),10)-1; 
  var yr1 = parseInt(str1.substring(6,10),10);
  var tarikhTerima = new Date(yr1, mon1, dt1);
  var str2 = document.${formName}.txdTarikhDaftar.value; 
  var dt2 = parseInt(str2.substring(0,2),10); 
  var mon2 = parseInt(str2.substring(3,5),10)-1; 
  var yr2 = parseInt(str2.substring(6,10),10); 
  var tarikhDaftar = new Date(yr2, mon2, dt2); 
  var currentDate = new Date(); 
  if (tarikhTerima > currentDate){ 
  	 alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.'); 
   	 document.${formName}.txdTarikhTerima.focus(); return; 
  } 
  if (tarikhDaftar > currentDate){ 
   	  alert('Tarikh Daftar tidak boleh melebihi dari tarikh hari ini.'); 
   	  document.${formName}.txdTarikhDaftar.focus(); return; 
  } 
  if (tarikhDaftar > tarikhTerima){ 
      alert('Tarikh Daftar tidak boleh melebihi dari Tarikh Terima.'); 
     document.${formName}.txdTarikhSurat.focus(); return; 
  }
//END OF VALIDATION
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=updateDetailHakmilik&idHakmilik="+id;	
	document.${formName}.submit();
}

function doChangeTaraf() {
	doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik");
}


function kembaliHakmilik(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";	
	document.${formName}.submit();
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function cal_tarikh_luput(){
  var tr = document.${formName}.txdTarikhDaftar.value;
  var temp_tr = tr.substring(0,6)
  var year_cur = tr.substring(6,10)
  var tempoh = document.${formName}.txtTempoh.value; 
  var luput = temp_tr+(parseInt(year_cur)+parseInt(tempoh));
  document.${formName}.txdTarikhLuput.value = luput;
}
function kiraLuas(idLuas){
  var jenisLuas = idLuas;
  // KILOMETER PERSEGI
  if(jenisLuas == "1"){
  	var luasK = (document.${formName}.txtLuas1.value);
	var luasH = luasK*100;
  	document.${formName}.txtLuas.value = luasH.toFixed(4);
   }
   else
   //HEKTER
    if(jenisLuas == "2"){
  	var luasH = (document.${formName}.txtLuas1.value);
  	document.${formName}.txtLuas.value = luasH.toFixed(4);
   }
   else
   // METER PERSEGI
   if(jenisLuas == "3"){
  	  var luasM = document.${formName}.txtLuas1.value;
  	  var luasH = (luasM*0.0001);
	  document.${formName}.txtLuas.value = luasH.toFixed(4);
   }
   else
   //EKAR, ROOD, POLE
   if(jenisLuas == "4"){
  	  var luasE = document.${formName}.txtLuas2.value;
	  var luasR = document.${formName}.txtLuas3.value;
	  var luasP = document.${formName}.txtLuas4.value;
	  var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  document.${formName}.txtLuas.value = luasH.toFixed(4);
   }
   else
   //KAKI PERSEGI
   if(jenisLuas == "5"){
  	  var luasAsal = document.${formName}.txtLuas1.value;
	  var luasK = luasAsal*0.0000092;
  	  document.${formName}.txtLuas.value = luasK.toFixed(4);
  	  
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
  	  document.${formName}.txtLuas.value = luasH.toFixed(4);
   }
    else
   //RELONG,JEMBA,KAKI PERSEGI
   if(jenisLuas == "8"){
  	  var luasR = document.${formName}.txtLuas2.value;
	  var luasJ = document.${formName}.txtLuas3.value;
	  var luasK = document.${formName}.txtLuas4.value;
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
  	  document.${formName}.txtLuas.value = luasH.toFixed(4);
   }
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function formatCurrencyPertama(num) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
	num.substring(num.length-(4*i+3));
	document.${formName}.txtCukaiTahun.value = num + '.' + cents;
}
function formatCurrencySemasa(num) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
	num.substring(num.length-(4*i+3));
	document.${formName}.txtCukaiTerkini.value = num + '.' + cents;
}

function viewTransaksiCukai(id) {
	var url = "../x/${securityToken}/ekptg.view.htp.FrmRekodTransaksiCukai?idHakmilik="+id;
    var hWnd = window.open(url,'printuser','width=300,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function updateDetailHakmilikSamb(id,status){
	updateHakmilikVerifikasi(id);
  	if(status == 'S'){
 		if(document.${formName}.socJenisHakmilikBaru.value == ""){ 
			alert('Sila masukkan " Jenis Hakmilik Sambungan " terlebih dahulu.'); 
			document.${formName}.socJenisHakmilikBaru.focus();
			return;
	 	}
	 	if(document.${formName}.txtHakmilikBerikut.value == ""){ 
			alert('Sila masukkan " No Hakmilik Sambungan " terlebih dahulu.'); 
			document.${formName}.txtHakmilikBerikut.focus();
			return; 
	 	}
  	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
	   return;
	}
	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=updateDetailHakmilik&idHakmilik="+id;	
	document.${formName}.submit();

}

function updateHakmilikVerifikasi(id){
////VALIDATAION
 if(document.${formName}.socNegeriHR.value == 99999){ 
	alert('Sila masukkan " Negeri " terlebih dahulu.'); 
	document.${formName}.socNegeriHR.focus();
	return; 
 }
 if(document.${formName}.socDaerahHR.value == ""){ 
	alert('Sila masukkan " Daerah " terlebih dahulu.'); 
	document.${formName}.socDaerahHR.focus();
	return; 
 }
 if(document.${formName}.socMukimHR.value == ""){ 
	alert('Sila masukkan " Bandar/Pekan/Mukim " terlebih dahulu.'); 
	document.${formName}.socMukimHR.focus();
	return; 
 }
 if(document.${formName}.txdTarikhTerima.value == ""){ 
	alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
	document.${formName}.txdTarikhTerima.focus();
	return; 
 }
 if(document.${formName}.txdTarikhDaftar.value == ""){ 
	alert('Sila masukkan " Tarikh Daftar " terlebih dahulu.'); 
	document.${formName}.txdTarikhDaftar.focus();
	return; 
 }
 if(document.${formName}.socTaraf.value == ""){ 
	alert('Sila masukkan " Taraf Hakmilik " terlebih dahulu.'); 
	document.${formName}.socTaraf.focus(); 
	return; 
 }	
 if(document.${formName}.txtCukaiTahun.value == ""){ 
	alert('Sila masukkan " Cukai Tahun Pertama " terlebih dahulu.'); 
	document.${formName}.txtCukaiTahun.focus();
	return; 
 }
  if(document.${formName}.txtCukaiTerkini.value == ""){ 
	alert('Sila masukkan " Cukai Tahun Semasa " terlebih dahulu.'); 
	document.${formName}.txtCukaiTerkini.focus();
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
 if(document.${formName}.txtLuas.value == ""){ 
	alert('Sila masukkan " Luas " terlebih dahulu.'); 
	document.${formName}.txtLuas.focus();
	return; 
 }
 if(document.${formName}.socKategori.value == ""){ 
	alert('Sila masukkan " Kategori " terlebih dahulu.'); 
	document.${formName}.socKategori.focus();
	return; 
 }
 if(document.${formName}.socStatus.value == ""){ 
	alert('Sila masukkan " Status Sah " terlebih dahulu.'); 
	document.${formName}.socStatus.focus();
	return; 
 }        

  var str1 = document.${formName}.txdTarikhTerima.value; 
  var dt1 = parseInt(str1.substring(0,2),10); 
  var mon1 = parseInt(str1.substring(3,5),10)-1; 
  var yr1 = parseInt(str1.substring(6,10),10);
  var tarikhTerima = new Date(yr1, mon1, dt1);
  var str2 = document.${formName}.txdTarikhDaftar.value; 
  var dt2 = parseInt(str2.substring(0,2),10); 
  var mon2 = parseInt(str2.substring(3,5),10)-1; 
  var yr2 = parseInt(str2.substring(6,10),10); 
  var tarikhDaftar = new Date(yr2, mon2, dt2); 
  var currentDate = new Date(); 
  if (tarikhTerima > currentDate){ 
  	 alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.'); 
   	 document.${formName}.txdTarikhTerima.focus(); return; 
  } 
  if (tarikhDaftar > currentDate){ 
   	  alert('Tarikh Daftar tidak boleh melebihi dari tarikh hari ini.'); 
   	  document.${formName}.txdTarikhDaftar.focus(); return; 
  } 
  if (tarikhDaftar > tarikhTerima){ 
  	alert('Tarikh Daftar tidak boleh melebihi dari Tarikh Terima.'); 
  	document.${formName}.txdTarikhSurat.focus(); return; 
  }
  
}

function deleteHakmilikBaru(id,id2){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "";
	/* firzan edit to cater hakmilik sambungan combine with rekod hakmilik/rizab */
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=deleteHakmilikBaru&idHakmilik="+id+"&idHakmilikBaru="+id2;
	document.${formName}.submit();
	
}

//function Xhakmilik_detail(id,status){
//	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id+"&statusSah="+status;
//	document.${formName}.submit();
//}

	function XhakmilikDetailLagi(id,status){
		doAjaxCall${formName}("paparterperincihakmilik","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id+"&statusSah="+status);
	
	}

</script>
 