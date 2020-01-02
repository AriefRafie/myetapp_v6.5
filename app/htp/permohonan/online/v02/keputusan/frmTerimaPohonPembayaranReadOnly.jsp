<!--frmTerimaPohonPembayaran.jsp-->
<!-- CL-02-009 -->
<fieldset>

	<div id="TabbedPanels3" class="TabbedPanels">
	  	<ul class="TabbedPanelsTabGroup">
		    <li class="TabbedPanelsTab" tabindex="0" onClick="selectTab3('0','kemaskinipermohonan','Pembayaran','BuktiPembayaran','')">BUKTI PEMBAYARAN PERMOHONAN</li>
		    <li class="TabbedPanelsTab" tabindex="1" onClick="selectTab3('1','kemaskinipermohonan','Pembayaran','hantarpermohonan','')">HANTAR PERMOHONAN</li>
		    <li class="TabbedPanelsTab" tabindex="2" onClick="selectTab3('2','kemaskinipermohonan','Pembayaran','KeputusanPermohonan','')">KEPUTUSAN PERMOHONAN</li>
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
						 			<td width="1%">							
						 			&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Tarikh Surat Ke PTD</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	<input name="txtTarikhSuratKePTD" type="text" id="txtTarikhSuratKePTD" value="$!pembayaran.tarikhterima" size="11" maxlength="10" $!inputStyle/>
									    #if($visible == 1)
									    <!--  
									      <a href="javascript:displayDatePicker('txtTarikhSuratKePTD',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
										-->
										#end
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Tarikh Baucer / Cek</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	<input name="txtTarikhBaucerCek" type="text" id="txtTarikhBaucerCek" value="$!pembayaran.tarikhbaucer" size="11" maxlength="10" $!inputStyle/>
									    <!--  
									    #if($visible == 1)
									      <a href="javascript:displayDatePicker('txtTarikhBaucerCek',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
										#end
										-->
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">No. Baucer/Cek/Bank Draft</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txtNoBaucerCekDraft" id="txtNoBaucerCekDraft" size="30"  value="$!pembayaran.nobaucer" onKeyUp="this.value=this.value.toUpperCase();" $!inputStyle></td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Cara Bayaran</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">$socBayaran</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Bayaran Proses (RM)</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txtBayaranProses" id="txtBayaranProses" size="11" value="$!pembayaran.jumlahbayaran" onBlur="validateCurrency(this,this.value,'')"  $!inputStyle></td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Penerima Bayaran</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txttempatBayaran" id="txttempatBayaran" size="30"  value="$!pembayaran.namabank" onKeyUp="this.value=this.value.toUpperCase();" $!inputStyle></td>
						          </tr>
	         				</table>			
	         			</td>
        
	        			<td width="50%" valign="top">
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Tarikh Resit</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	<input name="txtTarikhResit" type="text" id="txtTarikhResit" value="$!pembayaran.tarikhresit" size="11" maxlength="10" $!inputStyle/>
									    <!--  
									    #if($visible == 1)
									      <a href="javascript:displayDatePicker('txtTarikhResit',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
										#end
										-->
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%" valign="top">
						            	<div align="right" class="labelinput">
											<div align="left">No.Resit</div>
										</div>             
						            </td>
						            <td width="1%" valign="top">:</td>
						            <td width="58%"><input type="text" name="txtNoResit" id="txtNoResit" size="30" value="$!pembayaran.NoResit" onKeyUp="this.value=this.value.toUpperCase();" $!inputStyle></td>
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
						    <!--	<input type="button" class="stylobutton100"  value="Simpan" onclick="TambahPembayaran()" /> -->
						    #elseif ($tabmode == "4" && $!style == "")
						   		<input type="button" class="stylobutton100"  value="Simpan" onclick="doKemaskiniBuktiBayaran($tabmode)" />
						    #else
						    <!--	<input type="button" class="stylobutton100"  value="Kemaskini" onclick="doViewForKemaskiniBuktiBayaran($tabmode)" /> -->
						    #end 
						 	<!--Add on 2010/06/01 for print Surat
							<input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" 
								onclick="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$idpermohonan','urusan','&template=HTPPermohonanSuratPermohonanRizab')"/> 
							-->
							</div>
						</p>
   
     		</div>
     		<!-- End Content 1-->
 
     		<div class="TabbedPanelsContent">
    		<!-- Start Content 1.1 -->  
				<fieldset>
  				<table width="100%" border="0"> 					
         			<tr>
	         			<td valign="top">
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%" valign="top">
						 				<span class="labelmandatory">*</span>
						 				&nbsp;</td>
						            <td width="40%" valign="top">
						            	<div align="right" class="labelinput">
											<div align="left">Tarikh Hantar Ke PTG/PTD</div>
										</div>             
						            </td>
						            <td width="1%" valign="top">:</td>
						            <td width="58%" valign="top">
						            	<input name="txttarikhsuratPTGPTD" type="text" id="txttarikhsuratPTGPTD" value="$!pembayaran.tarikhterima" size="11" maxlength="10" $style/>
									    #if($visible == 1)
									      <a href="javascript:displayDatePicker('txttarikhsuratPTGPTD',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
										#end
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%" valign="top">
						            	<div align="right" class="labelinput">
											<div align="left">Catatan</div>
										</div>             
						            </td>
						            <td width="1%" valign="top">:</td>
						            <td width="58%">
						            	<textarea name="txtcatatanPTGPTD" id="txtcatatanPTGPTD" cols="45" rows="5" onKeyUp="this.value=this.value.toUpperCase();" $style>$!keputusan.ulasan</textarea>
									</td>
						          </tr>
	         				</table>			
	         			</td>
        
	        			<td width="50%" valign="top">
	         				<table width="100%" border="0">
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
						    <!--	<input type="button" class="stylobutton100"  value="Simpan" onclick="tambahHantarPTGPTD()" />
						    	<input type="reset" class="stylobutton100"  value="Kosongkan" /> -->
						    #elseif ($tabmode == "4" && $!style == "")
						   	<!--	<input type="button" class="stylobutton100"  value="Simpan" onclick="tambahHantarPTGPTD($tabmode)" />
						    	<input type="reset" class="stylobutton100"  value="Kosongkan" /> -->
						    #else
						    <!--	<input type="button" class="stylobutton100"  value="Kemaskini" onclick="kemaskiniHantarPTGPTD($tabmode)" /> -->
						    #end 
						 	<!-- Add on 2010/06/01 for print Surat
							<input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" 
								onclick="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$idpermohonan','urusan','&template=HTPPermohonanSuratPermohonanRizab')"/> 
							-->
							</div>
						</p>
   
     		</div>
     		<!-- End Content 1.1 -->
    
    		<!-- Start Content 2-->  
     		<div class="TabbedPanelsContent">
				<fieldset>
  				<table width="100%" border="0">
  					
         			<tr>
	         			<td valign="top">
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Tarikh Keputusan</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	<input name="txtTarikhKeputusan" type="text" id="txtTarikhKeputusan" value="$!keputusan.tarikhkeputusan" size="11" maxlength="10" $style/>
									    #if($visible == 1)
									      <a href="javascript:displayDatePicker('txtTarikhKeputusan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
										#end
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">No. Rujukan PTD</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%"><input type="text" name="txtNoRujukPTD" id="txtNoRujukPTD" size="30" value="$!keputusan.norujukankeputusan" onKeyUp="this.value=this.value.toUpperCase();" $style/></td>
						          </tr>
	         				</table>			
	         			</td>
        
	        			<td width="50%" valign="top">
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Status Keputusan</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            	<select name="PilihKeputusan" style="width:200px;" $style>

											#set ($list = ["BELUM ADA KEPUTUSAN","DILULUSKAN","TIDAK DILULUSKAN","DIBATALKAN/DITARIKBALIK"])
											#set( $counter = 0 )
											#foreach ($i in $list)
												#if ($!keputusan.status == $counter) 
													<option selected value="0$counter">$i</option>
												#else
													<option value="0$counter">$i</option>
												#end
												#set ($counter = $counter+1)
											#end
										</select>
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%" valign="top">
						            	<div align="right" class="labelinput">
											<div align="left">Catatan</div>
										</div>             
						            </td>
						            <td width="1%" valign="top">:</td>
						            <td width="58%"><textarea name="txtCatatan" id="txtCatatan" cols="45" rows="5" onKeyUp="this.value=this.value.toUpperCase();" $style>$!keputusan.ulasan</textarea></td>
						          </tr>
						          <input type="hidden" name="idkeputusanmohon" id="idkeputusanmohon" value="$!keputusan.idkeputusanmohon" />
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
						    <!--	<input type="button" class="stylobutton100"  value="Simpan" onclick="TambahKeputusan()" /> -->
						    #elseif ($tabmode == "4" && $!style == "")
						   	 	<input type="button" class="stylobutton100"  value="Simpan" onclick="doKemaskiniKeputusan($tabmode)" />
						    #else
						    <!--	<input type="button" class="stylobutton100"  value="Kemaskini" onclick="doViewForKemaskiniKeputusan($tabmode)" /> -->
						    #end 
							</div>
						</p>
    		</div>
       		<!-- End Content 2-->
    
  		</div>	  	


	</div>

</fieldset>

<script type="text/javascript">
	var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTab3});
</script>
