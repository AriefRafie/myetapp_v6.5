<style type="text/css">
<!--
.pautancalendar {
	font-size: x-small;
	font-style: italic;
	color: #0000FF;
}
.pautanms {color: #0000FF}
-->
</style>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="idHakmilik" value="$idHakmilik" />
<input type="hidden" name="mode" value="$mode" />
<input type="hidden" name="idHakmilikPerihal" value="$idHakmilikPerihal" />
#parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")
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
	                            		<span class="labelinput">Cara Perolehan</span>
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
    <td width="15%"><div valign="left">Jenis Binaan</div></td>
    <td width="25%"><div valign="left">No. Rujukan JKR</div></td>
    <td width="10%"><div valign="left">Tarikh Binaan</div></td>
    <td width="22%"><div valign="left">Harga (RM)</div></td>
    <td width="20%"><div valign="left">Luas(Hektar)</div></td>
    <td width="5%"><div valign="left">Tindakan</div></td>
    #foreach ($senaraiB in $SenaraiPembangunan)
    #set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
    #set( $row = "row2")
    #else
    #set( $row = "row1")
    #end </tr>
  <tr class="$row">
    <td width="1%"><div align="left"><a href="javascript:viewDetailPerihal('$senaraiB.idHakmilikPerihal')">$senaraiB.bil</a></div></td>
    #if($senaraiB.bil!="")    
    <td  width="10%"><div valign="left"><a href="javascript:viewDetailPerihal('$senaraiB.idHakmilikPerihal')" class="pautanms"> #if($senaraiB.jenisBangunan=="P")
      PADANG
      #elseif($senaraiB.jenisBangunan=="B")
      BANGUNAN
      #elseif($senaraiB.jenisBangunan=="PR")
      PARKING
      #elseif($senaraiB.jenisBangunan=="J")
      JALAN       
      #elseif($senaraiB.jenisBangunan=="L")
      LAIN-LAIN        
    #end </a></div></td>
    #else
    <td  width="10%"><div valign="left">Tiada Rekod.</div></td>
    #end
    <td width="20%"><div valign="left">$senaraiB.noRujukanJKR</div></td>
    <td width="6%"><div valign="left">$senaraiB.tarikhBina</div></td>
    <td width="9%"><div valign="left">$senaraiB.hargaBina</div></td>
    <td width="15%">#if($senaraiB.jenisBangunan=="P")
    	$senaraiB.luasP
    #elseif($senaraiB.jenisBangunan=="B")
    	$senaraiB.luasB
     #elseif($senaraiB.jenisBangunan=="PR")
    	$senaraiB.luasPR
    #elseif($senaraiB.jenisBangunan=="J")
    	$senaraiB.luasJ      
    #elseif($senaraiB.jenisBangunan=="L")
    	$senaraiB.luasL      
    #end </td>
    #if($senaraiB.bil!="")  
    <td width="5%">
    	<div align="center">
          	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick = "deleteDetailPembangunan('$senaraiB.idHakmilikPerihal')">
    	</div>
    </td>
    #else
    <td width="5%">&nbsp;</td>
    #end
</tr>
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
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
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
	                            		<span class="labelinput">No Rujukan JKR</span>
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
     								<a href="javascript:displayDatePicker('txdTarikhBina',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> <span class="pautancalendar">dd/mm/yyyy </span></a></label>
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
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
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
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">Luas</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="pautanms" >
	 							#if($mode == "view")
	 							<input name="txtLuasLama" type="text" id="txtLuasLama" value="$txtLuasLama" size="15" $readonly class="$disabled"/>
	 							#else
								    #if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6' || $socLuas == '')
								      <input onkeyup="validateNumber(this,this.value)"; name="txtLuas1" type="text" class="$disabled" id="txtLuas1" size="3" $readonly onBlur="kiraLuas('$socLuas')"/>
								    #elseif($socLuas == '8' || $socLuas == '4')
								      <input type="text" name="txtLuas2" id="txtLuas2" class="$disabled" size="3" onkeyup="validateNumber(this,this.value)"; $readonly />
								      <input type="text" name="txtLuas3" id="txtLuas3" class="$disabled" size="3" onkeyup="validateNumber(this,this.value)"; $readonly/>
								      <input type="text" name="txtLuas4" id="txtLuas4" class="$disabled" size="3" onkeyup="validateNumber(this,this.value)"; $readonly onBlur="kiraLuas('$socLuas')"/>      
								    #elseif($socLuas == '7')
								      <input name="txtLuas5" type="text" class="$disabled" id="txtLuas5" size="3" onkeyup="validateNumber(this,this.value)"; $readonly />
								      <input name="txtLuas6" type="text" class="$disabled" id="txtLuas6" size="3" onkeyup="validateNumber(this,this.value)"; $readonly onBlur="kiraLuas('$socLuas')"/>
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
	 								<input name="txtLuas" type="text" id="txtLuas" value="$txtLuas" size="20" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);"/>
	 								(Hektar)
	  							</td>
	                		</tr>	               	                		
							<tr>
	  							<td width="1%" valign="top" >
					        	</td>				        
	                        	<td width="30%" valign="top">
	                            	<div align="left" >
	                            		<span class="labelinput">Catatan</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" valign="top">:</td>
	                   			<td width="68%" class="pautanms" >
	 								<textarea name="txtCatatan" cols="43" rows="5" class="$disabled" id="txtCatatan" $readonly="$readonly">$txtCatatan</textarea>
	  							</td>
	                		</tr>
	                		               		
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
		                            		<span class="labelinput">Bangunan</span>
		                            	</div>
		                        	</td>
		                  			<td width="1%" class="labelinput" >:</td>
		                   			<td width="68%" class="pautanms" >
		 								<input name="txtBangunan" type="text" id="txtBangunan" value="$txtBangunan" size="30" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);"/>
		  							</td>
		                		</tr>
		              			
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
		 								<input name="txtLuasAsal" type="text" class="disabled" id="txtLuasAsal" value="$txtLuasAsal" size="30"  readonly="readonly"/>
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
		 								<input name="txtJalan" type="text" id="txtJalan" value="$txtJalan" size="30" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);"/>
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
		 								<input name="txtJumlahGuna" type="text" class="disabled" id="txtJumlahGuna" onkeyup="validateNumber(this,this.value);" value="$txtJumlahGuna" size="30" readonly="readonly"/>
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
		 								<input name="txtPadang" type="text" id="txtPadang" value="$txtPadang" size="30" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);"/>
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
		 								<input name="txtBakiBelum" type="text" class="disabled" id="txtBakiBelum" onkeyup="validateNumber(this,this.value);" value="$txtBakiBelum" size="30"  readonly="readonly"/>
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
		 								<input name="txtParking" type="text" id="txtParking" value="$txtParking" size="30" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);"/>
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
		 								<input name="txtPeratusBelum" type="text" class="disabled" id="txtPeratusBelum" onkeyup="validateNumber(this,this.value);" value="$txtPeratusBelum" size="5"  readonly="readonly"/>
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
		 								<input name="txtLain" type="text" id="txtLain" value="$txtLain" size="30" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);"/>
		  							</td>
		                		</tr>
		
		                	</table>
	                		
			      		</fieldset>
	                </td>
	       		</tr>                    		

		</table>


        </td>
      </tr>	
      
	<tr>
  		<td >
    	<div align="center"> #if($mode == 'view')
          <input type="button" class="stylobutton100" name="btnUpdate" id="btnUpdate" value="Kemaskini" onclick="kemaskiniDetailPerihal($idHakmilikPerihal)"/>
	        #end
	        #if($mode == 'kemaskini')
	  		<input type="button" class="stylobutton100" name="btnSave" id="btnSave" value="Simpan" onclick="updatePerihalHakmilik('$idHakmilikPerihal','$txtLuasAsal','$txtBakiBelum','$txtJumlahGuna','$txtLuas')"/>
	        #end
	        #if($mode == 'new')
	  		<input type="button" class="stylobutton100" name="btnAdd" id="btnAdd" value="Simpan" onclick="tambahPerihalHakmilik('$idHakmilik','$txtLuasAsal','$txtBakiBelum','$txtJumlahGuna')"/>
	        #end
	        <input type="button" class="stylobutton100" name="btnReset" id="btnReset" value="Batal" onclick="baru($idHakmilik)"/>
	 		<input type="button" class="stylobutton100" name="btnBack" id="btnBack" value="Kembali" onclick="kembali('$idHakmilik','$socRizab')"/>
      	</div>
      </td>
	</tr>
</table>
#parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")
<script>
function tambahPerihalHakmilik(id,luasAsal,luasCurrent,luasJumlahGuna){

//alert(document.${formName}.socLuas.value);

var diff1;var diff2;var combine;

combine = eval(luasJumlahGuna) + eval(document.${formName}.txtLuas.value);
diff1 = eval(luasAsal) - eval(document.${formName}.txtLuas.value);
diff2 = luasAsal - combine;

/*
alert("luas belum guna:"+luasCurrent);
alert("combine:"+combine);
alert("diff1:"+diff1);
alert("diff2:"+diff2);
*/

//VALIDATAION
 if(document.${formName}.socJenisBinaan.value == ""){ 
	alert('Sila masukkan " Jenis Binaan " terlebih dahulu.'); 
	document.${formName}.socJenisBinaan.focus();
	return; 
 }
 if(document.${formName}.socLuas.value == ""){ 
	alert('Sila masukkan "Unit Luas " terlebih dahulu.'); 
	document.${formName}.socLuas.focus();
	return; 
 }
 if(document.${formName}.txtLuas.value == ""){ 
	alert('Sila masukkan " Luas " terlebih dahulu.'); 
	document.${formName}.txtLuas.focus(); 
	return; 
 }	
 
 //if(document.${formName}.txtLuas.value>luasAsal){
 if(diff1 != 0 && diff1<0){
  	alert("Jumlah luas guna melebihi luas asal.");
	//alert(luasCurrent);
	return;
 }
  //if(luasCurrent!=""){
  	if (diff2 != 0 && diff2 < 0) {
  		//if(document.${formName}.txtLuas.value>luasCurrent){
  		//if(document.${formName}.txtLuas.value.toFixed(4)>luasCurrent.toFixed(4)){
 		alert("Jumlah luas guna melebihi baki luas yang ada.");
		return;
	}
 //}

//END OF VALIDATAION
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=tambahDetailKeluasan&idHakmilik="+id;
	document.${formName}.submit();
}
function viewDetailPerihal(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=viewDetailKeluasan&idHakmilikPerihal="+id;
	document.${formName}.submit();
}
function kemaskiniDetailPerihal(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=kemaskiniDetailKeluasan&idHakmilikPerihal="+id;
	document.${formName}.submit();
}


function updatePerihalHakmilik(id,luasAsal,luasCurrent,luasJumlahGuna,luasYgDiUpdate){

var diff1;var diff2;var combine;

combine = eval(luasJumlahGuna) + eval(document.${formName}.txtLuas.value) - eval(luasYgDiUpdate);
diff1 = eval(luasAsal) - eval(document.${formName}.txtLuas.value);
diff2 = luasAsal - combine;


//VALIDATAION
 if(document.${formName}.socJenisBinaan.value == ""){ 
	alert('Sila masukkan " Jenis Binaan " terlebih dahulu.'); 
	document.${formName}.socJenisBinaan.focus();
	return; 
 }
 if(document.${formName}.socLuas.value == ""){ 
	alert('Sila masukkan "Unit Luas " terlebih dahulu.'); 
	document.${formName}.socLuas.focus();
	return; 
 }
 if(document.${formName}.txtLuas.value == ""){ 
	alert('Sila masukkan " Luas " terlebih dahulu.'); 
	document.${formName}.txtLuas.focus(); 
	return; 
 }	
 if(diff1 != 0 && diff1<0){
  	alert("Jumlah luas guna melebihi luas asal.");
	return;
 }
 
if (diff2 != 0 && diff2 < 0) {
	alert("Jumlah luas guna melebihi baki luas yang ada.");
	return;
}

//END OF VALIDATAION
	document.${formName}.idHakmilikPerihal.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=updateDetailKeluasan&idHakmilikPerihal="+id;
	document.${formName}.submit();
}
function kembali(id,jenis){
   	if(jenis == 'Y' || jenis == 'T'){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id;
	}
	else{
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
	}
	document.${formName}.submit();
}


function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function calculate(valueMohon,valueBaki){
 var luasPelepasan = document.${formName}.txtLuasPelepasan.value * 1;
 var luasAsal = document.${formName}.txtLuasAsal.value * 1; 
 if (luasPelepasan != ""){
  if (luasPelepasan > luasAsal){
   //alert('Luas yang dimasukkan telah melebihi luas asal.'); 
   document.${formName}.txtLuasPelepasan.value = valueMohon; 
   document.${formName}.txtBakiLuas.value = valueBaki;
   return;
  }
  else {
   var luasBaki = (luasAsal - luasPelepasan) * 1; 
   document.${formName}.txtBakiLuas.value = luasBaki.toFixed(5); 
   }
  }
} 
function deleteDetailPembangunan(id){
if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=deleteDetailPembangunan&idHakmilikPerihal="+id;
	document.${formName}.submit();
}
function baru(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&idHakmilik="+id;
	document.${formName}.submit();
}

function doChangeTaraf() {
	doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=doChangeLuas");
}

function doChangeTaraf2(mode) {
	doAjaxCall${formName}("","mode="+mode+"&firstAction=PendaftaranPembangunan&nextAction=doChangeLuas");
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
</script>