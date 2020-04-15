<style type="text/css">
<!--
.pautancalendar {
	font-size: x-small;
	font-style: italic;
	color: #0000FF;
}
.pautanms {color: #0000FF}
.inputnumber {
     text-align: right;
     background: url("eTapp_HTP/fieldBg.jpg") repeat scroll 0 0 transparent;
     border: 1px solid #4C2F4D;
     color: #000000;
}
-->
</style>




<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="idHakmilik" value="$idHakmilik" />
<input type="hidden" name="mode" value="$mode" />
<input type="hidden" name="idHakmilikPerihal" value="$idHakmilikPerihal" />
<input type="hidden" name="negeri" value="$txtNamaNegeri" />
#parse("app/htp/rekod/utiliti/pagingRekodTanah.jsp")
<table width="99%" border="0">
  <tr>
    <td>
    	

<fieldset>
	<!--<legend>MAKLUMAT HAKMILIK</legend>-->
    <legend>MAKLUMAT $!rizab_hakmilik_label</legend>
    
	<table width="100%" border="0">
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
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Cara Perolehan</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labelinput" >
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
	                   			<td width="68%" class="labelinput" >
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
	                   			<td width="68%" class="labelinput" >
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
	                   			<td width="68%" class="labelinput" >
	 								$!txtFailPTD
	  							</td>
	                		</tr>
	
	                	</table>
	                </td>
	       		</tr>                    		

	</table>
</fieldset>

	<fieldset>
	<legend>SENARAI PEMBANGUNAN</legend>
	
		<table border="0" width="100%">
			<tr>
		    	<td colspan="10"><label></label></td>
		  	</tr>
		  		
		  	<tr class="table_header">
			    <td width="3%"><div valign="left">Bil.</div></td>
			    <td width="31%"><div valign="left">Jenis Binaan</div></td>
			    <td width="15%"><div valign="left">No. Rujukan JKR</div></td>
			    <td width="10%"><div valign="left">Tarikh Binaan</div></td>
			    <td width="15%"><div align="right">Harga (RM)</div></td>
			    <td width="15%"><div align="right">Luas (Hektar)</div></td>
			    <td width="6%"><div align="right">Tindakan</div></td>
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
			    			</div></td>
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
							    
							    <!-- 
	    						if($portal_role_.contains('Admin'))
									<a href="javascript:viewTindakan('$senaraiB.idHakmilikPerihal','$senaraiB.idSusulanStatus','$senaraiB.idStatusFail','$!senaraiB.pautan')" class="pautanms">$!strBinaan</a>
								else 
								-->
									<!-- if($!senaraiB.pautan.trim() == "")
										$!strBinaan 
									else
										<a href="javascript:viewTindakan('$senaraiB.idHakmilikPerihal','$senaraiB.idSusulanStatus','$senaraiB.idStatusFail','$!senaraiB.pautan')" class="pautanms">$!strBinaan</a>
									end -->
								
								<a href="javascript:viewTindakan('$senaraiB.idHakmilikPerihal','$senaraiB.idSusulanStatus','$senaraiB.idStatusFail','$!senaraiB.pautan')" class="pautanms">$!strBinaan  </a>
								
								<!-- end -->
	    					
	    						<div class="pautanms"><b><blink>$!senaraiB.status</blink></b></div>
	    						
	    					</div>
	    				</td>
	    				
	    				<td ><div valign="left">$senaraiB.noRujukanJKR</div></td>
					    <td ><div valign="left">$senaraiB.tarikhBina</div></td>
					    <td ><div align="right">$senaraiB.hargaBina</div></td>
	    				<td ><div align="right">
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
						
						<td align="right">
						<!--    	<div align="center">
						          	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick = "deleteDetailPembangunan('$senaraiB.idHakmilikPerihal')">
						    	</div>
						-->
							#if ($!jenisTanah.equals("M"))
							
						      	<img border="0" src="../img/print.gif" onClick="cetakPembangunan('$senaraiB.idHakmilikPerihal','HTPRekod284')" />
						   	#elseif($!jenisTanah.equals("R"))
								<a alt="Cetak" href = "javascript:cetakPembangunan('$senaraiB.idHakmilikPerihal','HTPRekod283')">
						      	<img border="0" src="../img/print.gif" /></a>
						    #end
						      	<a alt="Hapus" href = "javascript:deleteDetailPembangunan('$senaraiB.idHakmilikPerihal')">&nbsp;&nbsp;<img border="0" src="../img/delete.gif" /></a> 
						</td>
						
			    	</tr>
			    	
				#end
				
				
			#else
				<td colspan="7">Tiada Rekod</td>
			#end
			
		</table>
		
	</fieldset>

		<table width="100%" border="0">
	       		<tr>
	            	<td width="50%" valign="top">
						<fieldset>
						<legend>MAKLUMAT  PEMBANGUNAN</legend>
	                	<table width="100%" border="0">
	                    	<tr>
	  							<td width="1%" valign="top" >
  								<span class="labelmandatory">#if ($mode == 'update' || $mode == 'new') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Jenis Binaan</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<select name="socJenisBinaan" id="socJenisBinaan" style="width:200px;" $readonly class="$disabled" $disabled>							      
							        
							        #if($socJenisBinaan == 'B')							        
							        
							      <option value="">SILA PILIH</option>
							      <option value="B" selected="selected">B - BANGUNAN</option>
							      <option value="P">P - PADANG</option>
							      <option value="PR">PR - PARKING</option>
							      <option value="J">J - JALAN</option>
							      <option value="L">L - LAIN-LAIN</option>						      
							        
							        #elseif($socJenisBinaan == 'P')  							        
							        
							      <option value="B">B - BANGUNAN</option>
							      <option value="P" selected="selected">P - PADANG</option>
							      <option value="PR">PR - PARKING</option>
							      <option value="J">J - JALAN</option>
							      <option value="L">L - LAIN-LAIN</option>							      
							        
							        #elseif($socJenisBinaan == 'PR')  							        
							        
							      <option value="B">B - BANGUNAN</option>
							      <option value="P">P - PADANG</option>
							      <option value="PR" selected="selected">PR - PARKING</option>
							      <option value="J">J - JALAN</option>
							      <option value="L">L - LAIN-LAIN</option>							      
							        
							        #elseif($socJenisBinaan == 'J')  							        
							        
							      <option value="B">B - BANGUNAN</option>
							      <option value="P">P - PADANG</option>
							      <option value="PR">PR - PARKING</option>
							      <option value="J" selected="selected">J - JALAN</option>
							      <option value="L">L - LAIN-LAIN</option>							      
							        
							        #elseif($socJenisBinaan == 'L')  							        
							        
							      <option value="B">B - BANGUNAN</option>
							      <option value="P">P - PADANG</option>
							      <option value="PR">PR - PARKING</option>
							      <option value="J">J - JALAN</option>
							      <option value="L" selected="selected">L - LAIN-LAIN</option>							      
							                
									#else							        
							        
							      <option value="" selected="selected">SILA PILIH</option>
							      <option value="B">B - BANGUNAN</option>
							      <option value="P">P - PADANG</option>
							      <option value="PR">PR - PARKING</option>
							      <option value="J">J - JALAN</option>
							      <option value="L">L - LAIN-LAIN</option>							      
							            
							        #end 							      
							      
							    </select>
	  							</td>
	                		</tr>
	                     	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Rujukan JKR</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
									<input name="txtNoJKR" type="text" id="txtNoJKR" value="$txtNoJKR" size="30" $readonly class="$disabled"/>	  							
								</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Tarikh Binaan</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<input name="txdTarikhBina" type="text" id="txdTarikhBina" value="$txdTarikhBina" size="9" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);"/>
     								<a href="javascript:displayDatePicker('txdTarikhBina',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> <span class="pautancalendar">dd/mm/yyyy </span></a>
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Harga (RM)</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<input name="txtHarga" type="text" id="txtHarga" value="$txtHarga" size="30" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);"/>
	  							</td>
	                		</tr>
	                    	<tr>
	  							<td width="1%" valign="top" >
  								<span class="labelmandatory">#if ($mode == 'update'|| $mode == 'new') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Unit</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 								<select name="socLuas" id="socLuas" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeTaraf2('$mode')">
        
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
							        
							         
							       #else
							        
							      
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
	  							<td width="1%" valign="top" >
  								<span class="labelmandatory">#if ($mode == 'update'|| $mode == 'new') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Luas</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 							#if($mode == "view")
	 							<input name="txtLuasLama" type="text" id="txtLuasLama" value="$!txtLuasLama" size="20" $readonly class="$disabled"/>
	 							#else
								    #if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6' || $socLuas == '')
								      <input type="text" name="txtLuas1" id="txtLuas1" size="20" onkeyup="validateNumber(this,this.value)"; class="$disabled"  $readonly onBlur="kiraLuas('$socLuas')"/>
								    #elseif($socLuas == '8' || $socLuas == '4')
								      <input type="text" name="txtLuas2" id="txtLuas2" class="$disabled" size="3" onkeyup="validateNumber(this,this.value)"; $readonly />
								      <input type="text" name="txtLuas3" id="txtLuas3" class="$disabled" size="3" onkeyup="validateNumber(this,this.value)"; $readonly/>
								      <input type="text" name="txtLuas4" id="txtLuas4" class="$disabled" size="3" onkeyup="validateNumber(this,this.value)"; $readonly onBlur="kiraLuas('$socLuas')"/>      
								    #elseif($socLuas == '7')
								      <input name="txtLuas5" type="text" class="$disabled" id="txtLuas5" size="7" onkeyup="validateNumber(this,this.value)"; $readonly />
								      <input name="txtLuas6" type="text" class="$disabled" id="txtLuas6" size="7" onkeyup="validateNumber(this,this.value)"; $readonly onBlur="kiraLuas('$socLuas')"/>
								    #end 
							    	<!--<a href="javascript:kiraLuas('$socLuas')" title="Kira luas dalam hektar." class="pautancalendar">Kira Luas</a>--> </label></td>
							    #end
	  							</td>
	                		</tr>
							<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Luas Bersamaan</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" >
	 								<input name="txtLuas" type="text" id="txtLuas" value="$!txtLuas" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);"/>
	 								(Hektar)
	  							</td>
	                		</tr>	               	                		
							<tr>
	  							<td width="1%" valign="top" >&nbsp;</td>				        
	                        	<td width="30%" valign="top">
	                            	<div align="left" >
	                            		<span class="labelinput">Catatan</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" valign="top">:</td>
	                   			<td width="68%" class="pautanms" >
	 								<textarea name="txtCatatan" cols="43" rows="5" class="$disabled" id="txtCatatan" 
	 								onkeyup="this.value=this.value.toUpperCase();textCounter(this.form.txtCatatan,this.form.remtxtcatatan,1500);" 
	 								onkeydown="textCounter(this.form.txtCatatan,this.form.remtxtcatatan,1500);"
	 								$readonly="$readonly">$txtCatatan</textarea>
	  							</td>
	                		</tr>
							     #if ($mode != 'view')
									<tr>
								        <td>&nbsp;</td>
								        <td>&nbsp;</td>
								        <td valign="top">&nbsp;</td>
								        <td><input type="text" readonly class="disabled" name="remtxtcatatan" size="3" maxlength="4" value="1500"> Baki Aksara </td>
								    </tr>	
								#end	                		               		
	                 	</table>
	                 	</fieldset>
	           		</td>
	                        	
	                <td valign="top">
	      			<fieldset>
	      				<legend>JUMLAH KELUASAN PEMBANGUNAN (HEKTAR)</legend>
		               		<table width="100%">
		            			<tr>
		  							<td width="1%" valign="top" >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Luas Asal</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="pautanms" >
		 								<input class="inputnumber" name="txtLuasAsal" id="txtLuasAsal" value="$!txtLuasAsal" size="30"  readonly="readonly"/>
		  							</td>
		                		</tr>
		                		
		                   	<tr>
		  							<td width="1%" valign="top" >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Bangunan</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="pautanms" >
		 								<input type="text" class="inputnumber" name="txtBangunan" id="txtBangunan" value="$!txtBangunan" size="30" readonly="readonly" onkeyup="validateNumber(this,this.value);"/>
		  							</td>
		                		</tr>
		              			
		
		                    	<tr>
		  							<td width="1%" valign="top" >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Jalan</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="pautanms" >
		 								<input type="text" name="txtJalan" class="inputnumber" id="txtJalan" value="$!txtJalan" size="30" readonly="readonly" onkeyup="validateNumber(this,this.value);"/>
		  							</td>
		                		</tr>
		                    	<tr>
		  							<td width="1%" valign="top" >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Padang</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="pautanms" >
		 								<input type="text" class="inputnumber" name="txtPadang" id="txtPadang" value="$!txtPadang" size="30" readonly="readonly" onkeyup="validateNumber(this,this.value);"/>
		  							</td>
		                		</tr>
		                    	<tr>
		  							<td width="1%" valign="top" >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Parking</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="pautanms" >
		 								<input type="text" class="inputnumber" name="txtParking" id="txtParking" value="$!txtParking" size="30" readonly="readonly" onkeyup="validateNumber(this,this.value);"/>
		  							</td>
		                		</tr>
		                    	<tr>
		  							<td width="1%" valign="top" >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Lain-lain</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="pautanms" >
		 								<input type="text" class="inputnumber" name="txtLain" id="txtLain" value="$!txtLain" size="30" readonly="readonly" onkeyup="validateNumber(this,this.value);"/>
		  							</td>
		                		</tr>
		                		
		                    	<tr>
		  							<td width="1%" valign="top" >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Jumlah Guna</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="pautanms" >
		 								<input type="text" class="inputnumber" name="txtJumlahGuna" id="txtJumlahGuna" onkeyup="validateNumber(this,this.value);" value="$!txtJumlahGuna" size="30" readonly="readonly"/>
		  							</td>
		                		</tr>

		                    	<tr>
		  							<td width="1%" valign="top" >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Baki Belum Guna</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="pautanms" >
		 								<input type="text" class="inputnumber" name="txtBakiBelum" id="txtBakiBelum" onkeyup="validateNumber(this,this.value);" value="$!txtBakiBelum" size="30"  readonly="readonly"/>
		  							</td>
		                		</tr>
		                    	<tr>
		  							<td width="1%" valign="top" >
						        	</td>				        
		                        	<td width="30%">
		                            	<div align="left">
		                            		<span class="labelinput">Peratus Belum Guna</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="pautanms" >
		 								<input name="txtPeratusBelum" type="text" class="disabled" id="txtPeratusBelum" onkeyup="validateNumber(this,this.value);" value="$!txtPeratusBelum" size="5"  readonly="readonly"/>
		  							</td>
		                		</tr>
		                			

		</table>


        </td>
      </tr>	
      
      
	  <div name="pembagunanimg">
      <tr>
		  <td valign="top" colspan=3>
		  #set($perihal = "")
	      	<fieldset>
	      			<legend>IMEJ PEMBANGUNAN </legend>
	      					<div id="imejId">
		               		<table width="100%" border=0>
      
      #if($listGambarPembangunan.size()!=0 )
		    #if($mode == 'kemaskini' || $mode == 'view')
				#foreach ($senaraiG in $listGambarPembangunan)
					#set( $i = $velocityCount )
			    	#if ( ($i % 2) != 1 )
			    		#set( $rowA = "row2")
			    	#else
			    		#set( $rowA = "row1")
			    	#end 
			    	#set($perihal = $senaraiG.perihalImej)
			    	
			   
			    	<tr class="$rowA" >
				    	<td colspan="4" align="center">
				    		
				      		<p><img src="../servlet/ekptg.view.htp.FrmRekodDisplayImej?id=$senaraiG.idGambar" alt="Imej Pelan" border="1" width="250" height="250"/></p>
				      		<p><input type="button" name="btnImejPenuh" id="btnImejPenuh" value="Imej Penuh" onclick="cetakImej($senaraiG.idGambar)" /></p><br><br>
				      		
				      	</td> <br/>
			  		</tr>
      			#end
      			
      			
    			<tr>
					<td valign="top" width="1%">
					</td>				        
					<td width="30%" valign="top">
						<div align="right" class="labelinput">
						<div align="left"> Perihal Imej</div>
						</div>
					</td>				      	
					<td width="1%" valign="top">:</td>				        
					<td width="68%">
					<input type="hidden" name="sizePicYgAda" value="$listGambarPembangunan.size()" />
							<textarea name="txtPerihalImej" cols="43" rows="5"  id="txtPerihalImej"  style="text-transform:uppercase;" class="$disabled" >$perihal</textarea>
					</td>
				</tr>
			#end
			#elseif($listGambarPembangunan.size()==0 && $mode == 'view')
				<!-- No Data pic  -->
				<tr>
								<td valign="top" colspan="1">Tiada Rekod Imej.
								</td>
								</tr>
				
			#end
      		
      		#if($mode == 'new' ||($mode == 'kemaskini'&& $listGambarPembangunan.size()==0) )
      		
		            		<tr>
								<td valign="top" width="1%">
								<span class="labelmandatory">##if ($mode != 'readonly') * #end </span></td>				        
								<td width="30%">
									<div align="right" class="labelinput">
									<div align="left">Bil. Lampiran</div>
									</div>
								</td>				      	
								<td width="1%">:</td>				        
								<td width="68%">
									<input type=text size=2 name=jumlahlampiran  $!RO_General onBlur="doChangeJumlahLampiran1('$!IDJadualLampiran',this,'$!action');" 
	              					value=$!num_files> (<font size=1 color=red>Sila masukkan jumlah lampiran</font>)
								</td>
							</tr>
							<tr>
								<td valign="top" width="1%">
									<span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
								<td valign="top" width="30%">
									<div align="right" class="labelinput">
									<div align="left"> Direktori  </div>
									</div>
								</td>				      	
								<td valign="top" width="1%">:</td>				        
								<td width="68%">
									#foreach( $i in [1..$num_files] )							
									<input id="fileupload" name="fileupload" type="file" size="40" $readOnly  class="$disabled" /></br>
									#end
								</td>
							</tr>
							<tr>
								<td valign="top" width="1%">
								</td>				        
								<td width="30%" valign="top">
									<div align="right" class="labelinput">
									<div align="left"> Perihal Imej</div>
									</div>
								</td>				      	
								<td width="1%" valign="top">:</td>		
									        
								<td width="68%">
									<input type="hidden" name="sizePicYgAda" value="0" />	
									<textarea name="txtPerihalImej" cols="43" rows="5"  id="txtPerihalImej"  style="text-transform:uppercase;"></textarea>
								</td>
					</tr>
							<tr>
								<td valign="top" width="1%">
									<span class="labelmandatory"></span></td>				        
								<td width="30%">
									<div align="right" class="labelinput">
									<div align="left"></div>
									</div>
								</td>				      	
								<td width="1%"></td>				        
								<td width="68%">
									<i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik imej adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz imej yang lebih kecil.</span></span>
								</td>
							</tr> 
						#end
      		</table> </div>
    		</fieldset>
	    </td>
	</tr>
		
						
		    	</table>
			</fieldset>
		</td>
	</tr>  
	</div> <!-- end pembangunan -->                  	
   ##end 
<!-- 	</tr>	
 -->
	<tr>
  		<td colspan=3>
    	<div align="center">
    	
    	#if($mode == 'view')
    	
    			<!-- TAK LEH PAKAI. GANTI BARU UTK PENAMBAHBAIKAN
    			
    			if($portal_role_.contains('PenggunaNegeri'))
		    		<input type="button" name="cmdtambah" value="Hantar Untuk Semakan" onclick="simpanTindakanRole('$!idHakmilikPerihal','$!idSusulanStatus','$!idStatusFail')"/>
		  	
		  		elseif ($portal_role_.contains('PegawaiNegeri'))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Sahkan " onclick="simpanTindakanRole('$!idHakmilikPerihal','$!idSusulanStatus','$!idStatusFail')"/>
		  		
		  		elseif ($portal_role_.contains('PengarahNegeri'))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Dilulus" onclick="simpanTindakanRole('$!idHakmilikPerihal','$!idSusulanStatus','$!idStatusFail')"/>
	  			
	  			elseif($portal_role_.contains('HQPengguna'))
		    		<input type="button" name="cmdtambah" value="Hantar Untuk Semakan" onclick="simpanTindakanRole('$!idHakmilikPerihal','$!idSusulanStatus','$!idStatusFail')"/>
		  	
		  		elseif (($portal_role_.contains('HQPegawai') || $portal_role.contains('KetuaPenolong')))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Sahkan " onclick="simpanTindakanRole('$!idHakmilikPerihal','$!idSusulanStatus','$!idStatusFail')"/>
		  		
		  		elseif ($portal_role_.contains('HQPengarah'))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Dilulus" onclick="simpanTindakanRole('$!idHakmilikPerihal','$!idSusulanStatus','$!idStatusFail')"/>
		  				  		
		  		end -->
		  	#if($!flagKemaskiniState.equalsIgnoreCase("E") 
		  			&& ($portal_role_.contains('PenggunaNegeri') 
		  		|| $portal_role_.contains('PegawaiNegeri') 
		  		|| $portal_role_.contains('PengarahNegeri')) )
		  		<input type="button" class="stylobutton100" style="width:auto !important" name="btnHantarPengesahan" value="Hantar Pengesahan" onclick="doStateHantarPengesahan($idHakmilikPerihal)"/>	
		  	#elseif($!flagKemaskiniState.equalsIgnoreCase("H") 
		  			&& ($portal_role_.contains('PegawaiNegeri') 
		  		|| $portal_role_.contains('PengarahNegeri')) )
		  		<input type="button" class="stylobutton100" style="width:auto !important" name="btnPengesahanState" value="Pengesahan Negeri" onclick="doPengesahanNegeri($idHakmilikPerihal)"/>	
		  	#elseif($!flagKemaskiniState.equalsIgnoreCase("PS") 
		  			&& $portal_role_.contains('HQPengguna'))
		  		<input type="button" class="stylobutton100" style="width:auto !important" name="btnPengesahanHQ" value="Pengesahan HQ" onclick="doPengesahanHq($idHakmilikPerihal)"/>		
		  	#end
		  		    		
          	<input type="button" class="stylobutton100" name="btnUpdate" value="Kemaskini" onclick="kemaskiniDetailPerihal('$idHakmilikPerihal')"/>
 	        <input type="button" class="stylobutton100" name="btnReset" value="Batal" onclick="baruPembangunan($idHakmilik)"/>
	  	#end
	   	#if($mode == 'kemaskini')
	  		<input type="button" class="stylobutton100" name="btnSave" id="btnSave" value="Simpan" onclick="updatePerihalHakmilik('$idHakmilikPerihal','$txtLuasAsal','$txtBakiBelum','$txtJumlahGuna','$txtLuas')"/>
	        <input type="button" class="stylobutton100" name="btnReset" id="btnReset" value="Batal" onclick="baruPembangunan($idHakmilik)"/>
	   	#end
	    #if($mode == 'new')
	  		<input type="button" class="stylobutton100" name="btnAdd" value="Simpan" onclick="tambahPerihalHakmilik('$idHakmilik','$txtLuasAsal','$txtBakiBelum','$txtJumlahGuna')"/>
	 		
	 		#if ($!jenisTanah.equals("M"))
	        <!--    <input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:cetakPembangunan($idHakmilik,'HTPRekod284');" />  -->         	
          	#elseif($!jenisTanah.equals("R"))
	        <!--   <input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:cetakPembangunan($idHakmilik,'HTPRekod283');" />  -->    	
          	#else
          	#end
	 		
	 		<input type="button" class="stylobutton100" name="btnBack" id="btnBack" value="Kembali" onclick="dariPembangunan('$idHakmilik','$socRizab')"/>
	   	#end
      	</div>
      </td>
	</tr>
</table>
##parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")
#parse("app/htp/rekod/utiliti/pagingRekodTanah.jsp")



