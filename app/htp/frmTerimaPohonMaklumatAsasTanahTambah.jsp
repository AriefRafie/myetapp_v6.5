<style type="text/css">
<!--
.star {color: #F00;
}
-->
</style>

<table width="100%" border="0">
	<tr>
		<td>
			<fieldset>
			<legend>
			#if ($tabmode == "2")
			KEMASKINI
			#else
			TAMBAH
			#end 
			MAKLUMAT ASAS TANAH
			</legend>
				<table width="100%" border="0">
	       			<tr>
	         			<td valign="top">
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Negeri</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	$socNegeri2
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">
						 				<span class="labelmandatory">#if ($tabmode == "1" && $!style == "" || $tabmode == "2" && $!style == "") * #end </span>
						 				</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Daerah</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	$socDaerah2
									</td>
						          </tr>		
						     	<tr>
						 			<td width="1%">
						 				<span class="labelmandatory">#if ($tabmode == "1" && $!style == "" || $tabmode == "2" && $!style == "") * #end </span>
						 			</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Bandar/Pekan/Mukim</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	$socMukim2
									</td>
						          </tr>	
						          <tr>
						 			<td width="1%">
						 				<span class="labelmandatory">#if ($tabmode == "1" && $!style == "" || $tabmode == "2" && $!style == "") * #end </span>
						 				</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Kod Lot</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	$!socLot
									</td>
						          </tr>		
						     	<tr>
						 			<td width="1%">
						 				<span class="labelmandatory">#if ($tabmode == "1" && $!style == "" || $tabmode == "2" && $!style == "") * #end </span>
						 			</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Nombor Lot</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	<input $style name="txtNoLot" type="text" id="txtNoLot" onkeyup="this.value=this.value.toUpperCase();" value="$!txtNoLot" />
									</td>
						          </tr>	
									<tr>
						 			<td valign="top" width="1%">&nbsp;</td>
						            <td valign="top" width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Lokasi</div>
										</div>             
						            </td>
						            <td valign="top" width="1%">:</td>
						            <td width="58%">
						            	<textarea $style name="Lokasi" id="Lokasi" cols="35" rows="5" onkeyup="this.value=this.value.toUpperCase();">$!Lokasi</textarea>
									</td>
						          </tr>							          				          
							</table>			
	         			</td>
        
	        			<td width="50%" valign="top">
	         				<table width="100%" border="0">						          
          						<tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">No.Syit</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	<input $style name="noSyit" type="text" id="noSyit" value="$!noSyit" />
									</td>
						          </tr>
          						<tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">No. Pelan</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	<input $style name="noPelan" type="text" id="noPelan" value="$!noPelan"/>
									</td>
						          </tr>

          						<tr>
						 			<td width="1%">
						 				<!-- <span class="labelmandatory">#if ($tabmode == "1" && $!style == "" || $tabmode == "2" && $!style == "") * #end </span> -->
						 			</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Unit Luas</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	<select $style name="socLuas" style="width:200px;" onchange="doChangeKodLuas('$tabmode')">
									     #set ($listUnitLuas = ["SILA PILIH","KM - KILOMETER PERSEGI","H - HEKTAR","M - METER PERSEGI","E - EKAR,ROOD,POLE","K - KAKI PERSEGI","P - EKAR PERPULUHAN","D - EKAR,DEPA","R - RELONG,JEMBA,KAKI PERSEGI","BN - BATU NAUTIKA"])
									    #set( $counter = 0 )
									    #foreach ($i in $listUnitLuas)
									    #if ($socLuas == $counter) 
									        <option selected value="$counter">$i</option>
									    #else
									        <option value="$counter">$i</option>
									    #end
									    #set ($counter = $counter+1)
									    #end
									    </select>
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">
						 				<!-- <span class="labelmandatory"> #if ($tabmode == "1" && $!style == "" || $tabmode == "2" && $!style == "") * #end </span> -->
						 				</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Luas</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	#if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6')
									      <input $style value="$!txtLuas1"  name="txtLuas1" type="text" id="txtLuas1"   
									      onBlur="kiraLuas('$socLuas')" onkeyup="validateNumber(this,this.value);"/>
									    #elseif($socLuas == '8' || $socLuas == '4')
									      <input $style type="text" name="txtLuas2" id="txtLuas2" size="4" />
									      <input $style type="text" name="txtLuas3" id="txtLuas3" size="4" />
									      <input type="text" name="txtLuas4" id="txtLuas4" size="4" />      
									    #elseif($socLuas == '7')
									      <input $style name="txtLuas5" type="text" id="txtLuas5" size="8" />
									      <input $style name="txtLuas6" type="text" id="txtLuas6" size="8" />
									    #else
									    <input $style value="$!txtLuas1" name="txtLuas1" type="text" 
									    onBlur="kiraLuas('$socLuas')" onkeyup="validateNumber(this,this.value);" maxlength="8" />
										#end
									</td>
						          </tr>		
						     	<tr>						          
          						<tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Luas Bersamaan</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	<input name="Luas" type="text" class="disabled" id="Luas" onkeyup="validateNumber(this,this.value);" value="$!Luas"/>(Hektar)
						          </tr>						          
							</table>
						</td>          
					</tr>
				</table>
			</fieldset>
	  
	  </td>
	
	</tr>
	
		 	<tr>
		    <td >
	        	<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
			</td>
		</tr> 		
  		<tr>
    		<td align="center">
  			#if ($tabmode == "1" && $!style == "")
      			<input type="button" class="stylobutton100"  value="Simpan" onclick="SimpanMaklumatAsasTanah()" />
    		#elseif ($tabmode == "2" && $!style == "")
   	 			<input type="button" class="stylobutton100"  value="Simpan" onclick="doKemaskiniMaklumatAsasTanah()" />
    		#else
    			<input type="button" class="stylobutton100"  value="Kemaskini" onclick="doViewForKemaskini()" />
    		#end 
      			<input type="button" class="stylobutton100" name="Kembali" id="Kembali" value="Kembali" onclick="KembaliAsas(0,'kemaskinipermohonan','MakAsasTanah',0)" /></td>

    		</td>
  		</tr>
  		
</table>
