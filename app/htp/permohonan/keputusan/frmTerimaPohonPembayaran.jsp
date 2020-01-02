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
						           	#if($pagemode == "view")
						            		$!pembayaran.getTarikhTerimaBayaranFormat()
						            #else
						            	<input name="txtTarikhSuratKePTD" type="text" id="txtTarikhSuratKePTD" value="$!pembayaran.getTarikhTerimaBayaranFormat()" size="11" maxlength="10" $style/>
									  		<a href="javascript:displayDatePicker('txtTarikhSuratKePTD',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
									#end
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Tarikh Baucer/Cek/Bank Draft/EFT</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						           	#if($pagemode == "view")
						            		$!pembayaran.getTarikhBayaranFormat()
						            #else						            	
						            	<input name="txtTarikhBaucerCek" type="text" id="txtTarikhBaucerCek" value="$!pembayaran.getTarikhBayaranFormat()" size="11" maxlength="10" $style/>
											<a href="javascript:displayDatePicker('txtTarikhBaucerCek',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
									#end
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">No. Baucer/Cek/Bank Draft/EFT</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            #if($pagemode == "view")
						            		$!pembayaran.getNoRujukan()
						            #else	
						            	<input type="text" name="txtNoBaucerCekDraft" id="txtNoBaucerCekDraft" size="30"  value="$!pembayaran.getNoRujukan()" onKeyUp="this.value=this.value.toUpperCase();" $style>
						            #end
						            </td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Cara Bayaran</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">$!socBayaran</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Bayaran Proses (RM)</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            #if($pagemode == "view")
						            	$!jumlahBayaranFormat
			            		
						            #else
						                 <input type="text" name="txtBayaranProses" id="txtBayaranProses" size="11" 
    										value="$!jumlahBayaranFormat" onBlur="validateCurrency(this,this.value,'')"  $style>
    								#end
    								</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Penerima Bayaran</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            #if($pagemode == "view")
						            		$!pembayaran.getBank()
						            #else						            	
						            	<input type="text" name="txttempatBayaran" id="txttempatBayaran" size="30"  value="$!pembayaran.getBank()" onKeyUp="this.value=this.value.toUpperCase();" $style></td>
						          	#end
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
						            #if($pagemode == "view")
						            		$!pembayaran.getResit().getTarikhFormat()
						            #else						            	
						            	<input name="txtTarikhResit" type="text" id="txtTarikhResit" value="$!pembayaran.getResit().getTarikhFormat()" size="11" maxlength="10" $style/>
									      <a href="javascript:displayDatePicker('txtTarikhResit',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
									#end
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
						            <td width="58%">
						            #if($pagemode == "view")
						            		$!pembayaran.getResit().getNoRujukan()
						            #else
						            	<input type="text" name="txtNoResit" id="txtNoResit" size="30" value="$!pembayaran.getResit().getNoRujukan()" 
    										onKeyUp="this.value=this.value.toUpperCase();" $style>
									#end
    								</td>
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
						    	<input type="button" class="stylobutton100"  value="Simpan" onclick="TambahPembayaran()" />
						    #elseif ($tabmode == "4" && $!style == "")
						   		<input type="button" class="stylobutton100"  value="Simpan" onclick="doKemaskiniBuktiBayaran($tabmode)" />
						   		<input type="button" class="stylobutton100"  value="Batal" onclick="selectTab3('0','kemaskinipermohonan','Pembayaran','BuktiPembayaran','')" />
						    #else
						    	<input type="button" class="stylobutton100"  value="Kemaskini" onclick="doViewForKemaskiniBuktiBayaran($tabmode)" />
						    #end 
						 	<!--Add on 2010/06/01 for print Surat-->
							<input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" 
								onclick="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$idpermohonan','urusan','&template=HTPPermohonanSuratPermohonanRizabPTD$!suratExt')"/> 
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
									    #if($pagemode == "view")
						            		$!selesaiBean.tarikhSelesai
						            	#else
						            	<input name="txttarikhsuratPTGPTD" type="text" id="txttarikhsuratPTGPTD" value="$!selesaiBean.tarikhSelesai" size="11" maxlength="10" $style/>
									    #if($visible == 1)
									      <a href="javascript:displayDatePicker('txttarikhsuratPTGPTD',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
										#end
										
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
						            #if($pagemode == "view")
						            	$!selesaiBean.catatan
						            #else
						            	<textarea name="txtcatatanPTGPTD" id="txtcatatanPTGPTD" cols="45" rows="5" onKeyUp="this.value=this.value.toUpperCase();" $style>$!selesaiBean.catatan</textarea>
									#end
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
				<input name="txtid" type="hidden" value="$!selesaiBean.id_suburusanstatusfail" size="11" maxlength="10" $style/>
						
						<p align="center">
							<div align="center">
						    #if ($tabmode == "3" && $!style == "")
						    	<input type="button" class="stylobutton100"  value="Simpan" onclick="tambahHantarPTGPTD()" />
						    	<input type="reset" class="stylobutton100"  value="Kosongkan" />
						    #elseif ($tabmode == "4" && $!style == "")
						   		<input type="button" class="stylobutton100"  value="Simpan" onclick="kemaskiniSimpanPTGPTD()" />
						    	<input type="button" class="stylobutton100"  value="Batal" onclick="selectTab3('1','kemaskinipermohonan','Pembayaran','hantarpermohonan','')"/>
						    #else
						    	<input type="button" class="stylobutton100"  value="Kemaskini" onclick="kemaskiniHantarPTGPTD()" />
						    	<!-- <input type="button" class="stylobutton100"  value="hapus" onclick="hapusHantarPTGPTD()" /> -->
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
						    	<input type="button" class="stylobutton100"  value="Simpan" onclick="TambahKeputusan()" />
						    #elseif ($tabmode == "4" && $!style == "")
						   	 	<input type="button" class="stylobutton100"  value="Simpan" onclick="doKemaskiniKeputusan($tabmode)" />
						    #else
						    	<input type="button" class="stylobutton100"  value="Kemaskini" onclick="doViewForKemaskiniKeputusan($tabmode)" />
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
