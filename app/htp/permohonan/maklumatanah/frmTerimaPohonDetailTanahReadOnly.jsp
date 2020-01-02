<!--frmTerimaPohonDeatailTanah.jsp-->
<!-- CL-02-007 -->

<fieldset>

	<div id="TabbedPanels2" class="TabbedPanels">
	  	<ul class="TabbedPanelsTabGroup">
	    	<li class="TabbedPanelsTab" tabindex="0" onClick="selectTab2('0','kemaskinipermohonan','MakAsasTanah','DetailLot','')">LOKASI TANAH</li>
	    	<li class="TabbedPanelsTab" tabindex="1" onClick="selectTab2('1','kemaskinipermohonan','MakAsasTanah','DetailLot','')">MAKLUMAT CHARTING</li>
	  	</ul>
  		<div class="TabbedPanelsContentGroup">
  			
    		<div class="TabbedPanelsContent">
    		<!-- Start Content 1-->  
				<fieldset>
  				<table width="100%" border="0">
  					
         			<tr>
	         			<td valign="top">
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Zone</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	<select name="zone" style="width:70%;" $style>
								        #set ($listZone = ["Sila Pilih Zone","Bandar","Pekan","Desa"])
								        #set( $counter = 0 )
								        #foreach ($i in $listZone)
								        #if ($detail.lokasi == $counter) 
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
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left"></div>
										</div>             
						            </td>
						            <td width="1%"></td>
						            <td width="58%">Jarak (KM)</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Jauh Dari Bandar</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txtJDbandar" id="txtJDbandar" value="$!detail.jarak_bandar" $style/></td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Jauh Dari LebuhRaya</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txtJDLebuhRaya" id="txtJDLebuhRaya" value="$!detail.jarak_jalan" $style/></td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Jauh Dari Jalan Keretapi</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txtJDJalanKeretapi" id="txtJDJalanKeretapi" value="$!detail.jarak_keretapi" $style/></td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Jauh Dari Sungai / Pantai</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txtJDSungaiPantai" id="txtJDSungaiPantai" value="$!detail.jarak_sungai" $style/></td>
						          </tr>
			          			<tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Sempadan Utara</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txtSempadanUtara" id="txtSempadanUtara" value="$!detail.sempadan_utara" $style /></td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Sempadan Selatan</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txtSempadanSelatan" id="txtSempadanSelatan" value="$!detail.sempadan_selatan" $style/></td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Sempadan Timur</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txtSempadanTimur" id="txtSempadanTimur" value="$!detail.sempadan_timur" $style /></td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Sempadan Barat</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txtSempadanBarat" id="txtSempadanBarat" value="$!detail.sempadan_barat" $style /></td>
						          </tr>						          
	         				</table>			
	         			</td>
        
	        			<td width="50%" valign="top">
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%" valign="top">
						            	<div align="right" class="labelinput">
											<div align="left">Perihal</div>
										</div>             
						            </td>
						            <td width="1%" valign="top">:</td>
						            <td width="58%"><textarea name="PerihalLokasi" id="PerihalLokasi" cols="45" rows="5" $style onkeyup="this.value=this.value.toUpperCase();">$!detail.keterangan</textarea></td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%" valign="top">
						            	<div align="right" class="labelinput">
											<div align="left">Keterangan Lain Mengenai Tanah</div>
										</div>             
						            </td>
						            <td width="1%" valign="top">:</td>
						            <td width="58%"><textarea name="txtKeteranganLain" id="txtKeteranganLain" cols="45" rows="5" $style onkeyup="this.value=this.value.toUpperCase();">$!detail.lain_lain</textarea onkeyup="this.value=this.value.toUpperCase();"></td>
						          </tr>
	         				</table>		        				
	         			</td>
					</tr>
					<!--<tr>
						<td colspan=2 align="center">

					</tr> -->
				</table>
				</fieldset>
						<p align="center">
							<div align="center">
						    #if ($tabmode == "3" && $!style == "")
						    	#if (!$jenisAkses.equals("Readonly"))
						    	<input type="button" class="stylobutton100"  value="Simpan" onclick="SimpanDetailLot()" />
						    	#end 
						    #elseif ($tabmode == "4" && $!style == "")
						   		<input type="button" class="stylobutton100"  value="Simpan" onclick="doKemaskiniDetailLot($tabmode)" />
						    #else
						    	#if (!$jenisAkses.equals("Readonly"))
						    	<input type="button" class="stylobutton100"  value="Kemaskini" onclick="doViewForKemaskiniDetailLot($tabmode)" />
						    	#end 
						    #end 
						    	<input type="button" class="stylobutton100" value="Kembali" name="Kembali" id="Kembali" onclick="KembaliAsas(0,'kemaskinipermohonan','MakAsasTanah',0)" /> 
							</div>
						</p>
   
     		</div>
     		<!-- End Content 1-->
    
    		<!-- Start Content 2-->  
     		<div class="TabbedPanelsContent">
				<fieldset>
  				<table width="100%" border="0">
  					
					<tr>
						<td width="1%">&nbsp;</td>
							<td width="30%">
								<div align="right" class="labelinput">
									<div align="left">No.Pelan</div>
								</div>             
							</td>
						<td width="1%">:</td>
						<td width="68%"><input type="text" name="NoPelan" id="NoPelan" value="$!detail.nopelan"  readonly class="disabled" /></td>
					</tr>   
					      			
				   	<tr>
						<td width="1%">&nbsp;</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">No. Syit Piawai</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%"><input type="text" name="NoSyitPiawai" id="NoSyitPiawai" value="$!detail.nosyit"  readonly class="disabled" /></td>
					</tr>
					<tr>
						<td width="1%">&nbsp;</td>
							<td width="30%">
								<div align="right" class="labelinput">
									<div align="left">Jumlah Bayaran Proses (RM)</div>
								</div>             
							</td>
						<td width="1%">:</td>
						<td width="68%"><input type="text" name="JumBayaranPelan" id="JumBayaranPelan" value="$!detail.bayarproses" onBlur="validateCurrency(this,this.value,'')" $style/></td>
					</tr>   
					      			
				   	<tr>
						<td width="1%">&nbsp;</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Charting</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
						#if( $detail.flagcharting == "1")
		                  <input checked="checked" type="radio" name="RadioGroup1" value="1" $style/>
		                  YA
		                  <input type="radio" name="RadioGroup1" value="2" $style/>
		                  TIDAK
		                 #elseif( $detail.flagcharting == "2")
		                  <input type="radio" name="RadioGroup1" value="1" />
		                  YA
		                  <input checked="checked" type="radio" name="RadioGroup1" value="2" />
		                  TIDAK
		                  #else
		                  <input type="radio" name="RadioGroup1" value="1" />
		                  YA
		                  <input type="radio" name="RadioGroup1" value="2" />
		                  TIDAK
		                  #end
						</td>
					</tr>
					<tr>
						<td width="1%">&nbsp;</td>
							<td width="30%" valign="top">
								<div align="right" class="labelinput">
									<div align="left">Ulasan</div>
								</div>             
							</td>
						<td width="1%" valign="top">:</td>
						<td width="68%"><textarea name="ulasan" id="ulasan" cols="45" rows="5" $style onKeyUp="this.value=this.value.toUpperCase();">$!detail.ulasan</textarea></td>
					</tr>
					<!--<tr>
						<td colspan=2 align="center">

					</tr> -->
				</table>
				</fieldset>
						<p align="center">
							<div align="center">
						   	#if ($tabmode == "3" && $!style == "")
						    	#if (!$jenisAkses.equals("Readonly"))
						      <input type="button" class="stylobutton100"  value="Simpan" onclick="SimpanCharting()" />
						    	#end 
						    #elseif ($tabmode == "4" && $!style == "")
						   	 <input type="button" class="stylobutton100"  value="Simpan" onclick="doKemaskiniCharting($tabmode)" />
						    #else
						    	#if (!$jenisAkses.equals("Readonly"))
						    <input type="button" class="stylobutton100"  value="Kemaskini" onclick="doViewForKemaskiniCharting($tabmode)" />
						    	#end 
						    #end 
                 			<input type="button" class="stylobutton100" value="Kembali" name="Kembali" id="Kembali" onClick="KembaliAsas(0,'kemaskinipermohonan','MakAsasTanah',0)" />
							</div>
						</p>
    		</div>
       		<!-- End Content 2-->
    
  		</div>	  	


	</div>

</fieldset>

<script type="text/javascript">
	var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTab2});
</script>
