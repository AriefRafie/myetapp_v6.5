<!-- 
# AUTHOR : zufazdliabuas@gmail.com
# CREATE NEW FOR ONLINE VIEW ONLY DATA
# Date : 11/5/2017
 -->

<style type="text/css">
<!--
.q {
	color: #F00;
}
-->
</style>


<!-- ****************** START UNTUK MAKLUMAT NOTIS 5A KTN ************************* -->
## #parse("app/htp/permohonan/notis/frmBayaranNotis.jsp") <!--path file asal sebelum diubah kepada view sahaja.-->
##<br/><br/><br/><br/>
<fieldset>
	<legend>MAKLUMAT 
		#if($!idNegeriNotis=="13")
			BAYARAN L & S 80
		#elseif($!idNegeriNotis=="12")
			SURAT TAWARAN KELULUSAN
		#else
			NOTIS 5A KTN
		#end
	</legend>
	
	<table width="100%" border="0">
  		<tr>
    		<td valign="top">
				<table width="100%" border="0">
					<tr>
						<td width="1%">
							<span class="labelmandatory"><!--*--></span>
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Tarikh 
									#if($!idNegeriNotis=="13")
										Bayaran L & S 80
									#elseif($!idNegeriNotis=="12")
										Surat Tawaran Kelulusan
									#else
										Notis 5A
									#end	 						
								</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.tarikhnotis5a
						</td>
					</tr>
					<tr>
						<td width="1%"></td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Tarikh Terima 
									#if($!idNegeriNotis=="13")
										Bayaran L & S 80
									#elseif($!idNegeriNotis=="12")
										Surat Tawaran Kelulusan
									#else
										Notis 5A
									#end
								</div>								
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.tarikhterima
						</td>
					</tr>		
					
					#if($!idNegeriNotis=="13")
						$!dat.tarikhluput1
						$!dat.tarikhluput2
						$!dat.tarikhluput3
						<!-- <input name="txtTarikhLuputPertama" type="hidden" value="$!dat.tarikhluput1" size="11" maxlength="10" />
						<input name="txtTarikhLuputNotisKedua" type="hidden" value="$!dat.tarikhluput2" size="11" maxlength="10" />
						<input name="txtTarikhLuputNotisKetiga" type="hidden" value="$!dat.tarikhluput3" size="11" maxlength="10"/> -->
					#elseif($!idNegeriNotis=="12")
						$!dat.tarikhluput1
						$!dat.tarikhluput2
						$!dat.tarikhluput3
						<!--<input name="txtTarikhLuputPertama" type="hidden" value="$!dat.tarikhluput1" size="11" maxlength="10" />
						<input name="txtTarikhLuputNotisKedua" type="hidden" value="$!dat.tarikhluput2" size="11" maxlength="10" />
						<input name="txtTarikhLuputNotisKetiga" type="hidden" value="$!dat.tarikhluput3" size="11" maxlength="10"/> -->
					#else
					<tr>
						<td width="1%">
							</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Tarikh Luput Notis Pertama</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.tarikhluput1
						</td>
					</tr>	
					<tr>
						<td width="1%">
							</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Tarikh Luput Notis Kedua</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.tarikhluput2
						</td>
					</tr>		
					<tr>
						<td width="1%">
							</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Tarikh Luput Notis Ketiga</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.tarikhluput3
						</td>
					</tr>						
				#end			
																		
			</table>
		</td>
	       	
	       	<td valign="top">
	       		<table width="100%" border="0">
	         		<tr>
						<td width="1%">							
							<span class="labelmandatory"><!--*--></span>
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Cukai Tahun Pertama (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.kadarcukai
						</td>
					</tr>
	         		<tr>
						<td width="1%">							
							<span class="labelmandatory"><!-- * --></span>
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Premium (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.bayarpremium							
						</td>
					</tr>					
	
	         		<tr>
						<td width="1%">							
							<span class="labelmandatory"><!--*--></span>
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Bayaran Ukur(RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.bayarukur							
						</td>
					</tr>	
	         		<tr>
						<td width="1%">							
							<span class="labelmandatory"><!--*--></span>
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Tanda Sempadan (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.bayarsempadan							
						</td>
					</tr>	
	         		<tr>
						<td width="1%">							
							<span class="labelmandatory"><!--*--></span>
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Bayar Pendaftaran Hakmilik (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.bayarhakmilik							
						</td>
					</tr>	
					
	         		<tr>
						<td width="1%">							
							<!-- <span class="labelmandatory">*</span> -->
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Bayaran Notis 5F (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.bayarnotisf
						</td>
					</tr>	
	         		<tr>
						<td width="1%">							
							<!-- <span class="labelmandatory">*</span> -->
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Bayaran Fail (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.bayarfail							
						</td>
					</tr>							         		
	         		<tr>
						<td width="1%">							
							<!-- <span class="labelmandatory">*</span> -->
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Bayaran Perenggan (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.bayarperenggan							
						</td>
					</tr>	
					<tr>
						<td width="1%">							
							<!-- <span class="labelmandatory">*</span> -->
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Bayaran Lain 1 (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.bayaranlain				
						</td>
					</tr>
	         		<tr>
						<td width="1%">							
							<!-- <span class="labelmandatory">*</span> -->
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Bayaran Lain 2 (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.bayaranlain2
						</td>
					</tr>
	         		<tr>
						<td width="1%">							
							<!-- <span class="labelmandatory">*</span> -->
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Bayaran Lain 3 (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.bayaranlain3							
						</td>
					</tr>
					
				</table>			          
			</td><!--</fieldset> -->
  		</tr>
  		
	  	<tr><!--<fieldset><legend>Maklumat Notis</legend>-->
	   		<td valign="top">
				<table width="100%" border="0">	
					<tr>
						<td valign="top" width="1%">
							</td>
						<td valign="top" width="40%">
							<div align="right" class="labelinput">
								<div align="left">Perihal Rayuan</div>
							</div>             
						</td>
						<td valign="top" width="1%">:</td>
						<td width="58%">
							$!dat.perihalrayuan
						</td>
					</tr>						
					<tr>
						<td valign="top" width="1%">
							</td>
						<td valign="top" width="40%">
							<div align="right" class="labelinput">
								<div align="left">Tempoh Rayuan</div>
							</div>             
						</td>
						<td valign="top" width="1%">:</td>
						<td width="58%">
							$!dat.tempohRayuan
						</td>
					</tr>	
					<tr>
						<td valign="top" width="1%">
							</td>
						<td valign="top" width="40%">
							<div align="right" class="labelinput">
								<div align="left">Tarikh Rayuan</div>
							</div>             
						</td>
						<td valign="top" width="1%">:</td>
						<td width="58%">
							$!dat.tarikhRayuan
						</td>
					</tr>																				
				</table>
			</td>
		       	
	       	<td valign="top">
	       		<table width="100%" border="0">
	         		<tr>
						<td width="1%">							
							<!-- <span class="labelmandatory">*</span> -->
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Rayuan Premium (-RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.rayuanpremium					
						</td>
					</tr>					
				</table>			          
			</td><!--</fieldset> -->
	 	</tr>
  		
  		
		<tr><!--<fieldset><legend>Maklumat Notis</legend>-->
    		<td valign="top">
			</td>
	       	
	       	<td valign="top">
	       		<table width="100%" border="0">
	         		<tr>
						<td width="1%">							
							<!-- <span class="labelmandatory">*</span> -->
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Bayaran Notis 5A (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							$!dat.jumBayaran						
						</td>
					</tr>					
				</table>			          
			</td><!--</fieldset> -->
  		</tr>  		
  		
	 	<!-- <tr>
		    <td >
	        	<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
			</td>
		</tr> 		
  		<tr>
    		<td align="center">
    		#if ($tabmode == '1')
				<input class="stylobutton" type="button" value="Simpan" onclick="SimpanNotis()"/>
			#else
				<input class="stylobutton" type="button" value="Simpan" onclick="KemaskiniNotis()"/>
			#end
    		</td>
  		</tr> -->
	</table>
</fieldset>	
<!-- ****************** END UNTUK MAKLUMAT NOTIS 5A KTN ************************* -->

<!-- ****************** START UNTUK Senarai Bukti Pembayaran ************************* -->
<fieldset>
	<legend>Senarai Bukti Pembayaran</legend>
	<table width="100%" border="0">
		<tr class="table_header">
			<td width="3%">Bil.</td>
			<td width="18%">No.Baucer</td>
			<td width="18%">No.Resit</td>
			<td width="18%">Tarikh Baucer</td>
			<td width="18%">Tarikh Resit</td>
			<td width="20%">Jumlah Baucer</td>
		</tr>
		
		#set ( $cnt1 = 0 )			
		#foreach ( $Bukti in $BuktiBayaranInfo )
			#set ( $cnt1 = $cnt1 + 1 )
			#set( $i = $velocityCount )
			#if ( ($i % 2) == 0 )
				#set( $row = "row2" )
			#else
				#set( $row = "row1" )
			#end
			<tr>
				<td class="$row">$cnt1.</td>
				<td class="$row">$Bukti.nobaucer</td>
				<td class="$row">$Bukti.noresit</td>
				<td class="$row">$Bukti.tarikhbaucer</td>
				<td class="$row">$Bukti.tarikhresit</td>
				<td class="$row">$Bukti.jumlahbayaran</td>
			</tr>
		#end
		
		#if ($cnt == 0)
			<tr> 
				<td colspan="4" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
				<td colspan="3" scope="row"></td>
			</tr> 
		#end
	</table>
</fieldset>
<!-- ****************** START UNTUK Senarai Bukti Pembayaran ************************* -->