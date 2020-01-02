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
<table width="100%" border="0">
	<!-- ****************** START UNTUK MAKLUMAT SURAT TAWARAN KELULUSAN ************************* -->
	<tr>
	    <td>	    	
			## #parse ("app/htp/permohonan/v02/notis/frmBayaranNotis12.jsp") <!-- path file asal sebelum diubah kepada view sahaja. -->
			<fieldset>
				<legend>MAKLUMAT #parse ("app/htp/permohonan/notis/frmNotisHeaderScript.jsp")</legend>
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
						 					#parse ("app/htp/permohonan/notis/frmNotisScript.jsp")
											</div>
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.tarikhnotis5a
									</td>
								</tr>
								<tr>
									<td width="1%">
										</td>
									<td width="40%">
										<div align="right" class="labelinput">
											<div align="left">Tarikh Terima 
											#parse ("app/htp/permohonan/notis/frmNotisScript.jsp")
											</div>								
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.tarikhterima
									</td>
								</tr>		
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
										<span class="labelmandatory"></span>
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
											<div align="left">Bayaran Deraf Hakmilik (RM)</div>
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
									<td width="41%">
										<div align="right" class="labelinput">
											<div align="left">Bayaran Penyediaan Hakmilik (RM)</div>
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.bayaranlain2				
									</td>
								</tr>
							</table>			          
						</td>
			  		</tr>
			  		<tr>
			  			<td valign="top"></td>
				       	<td valign="top">
				       		<table width="100%" border="0">
				         		<tr>
									<td width="1%">							
										<span class="labelmandatory"><!-- *--></span> 
									</td>
									<td width="41%">
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
						</td>
			  		</tr>
			 		<tr>
			    		<td valign="top"></td>
				       	<td valign="top">
				       		<table width="100%" border="0">
				         		<tr>
									<td width="1%">							
										<!-- <span class="labelmandatory">*</span> -->
									</td>
									<td width="41%">
										<div align="right" class="labelinput">
											<div align="left">Jumlah Bayaran (RM)</div>
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.jumBayaran					
									</td>
								</tr>					
							</table>			          
						</td>
			  		</tr>  	
				</table>
			</fieldset>	
	    </td>
 	</tr>
 	<!-- ****************** END UNTUK MAKLUMAT SURAT TAWARAN KELULUSAN ************************* -->
 	
 	<!-- ****************** START UNTUK BUKTI PEMBAYARAN ************************* -->
 	<tr>	
	    <td> 			
  			<fieldset>
				<legend>SENARAI BUKTI PEMBAYARAN&nbsp;</legend>
						<!-- Mula TABLE level 1-->
					<table width="100%" border="0">
					
					  <tr class="table_header">
					    <td width="3%">Bil.</td>
					    <td width="18%">No. 
					    #parse ("app/htp/permohonan/utiliti/frmBayaranLabelScript.jsp")
					    </td>
					    <td width="15%">Tarikh 
					   	#parse ("app/htp/permohonan/utiliti/frmBayaranLabelScript.jsp")
					    </td>
					    <td width="18%">No.Resit</td>
					    <td width="15%">Tarikh Resit</td>
					    <td width="28%" align="right">Jumlah 
							#parse ("app/htp/permohonan/utiliti/frmBayaranLabelScript.jsp") (RM)   
					    </td>

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
					    <td class="$row">$Bukti.tarikhbaucer</td>
					    <td class="$row">$Bukti.noresit</td>
					    <td class="$row">$Bukti.tarikhresit</td>
					    <td class="$row" align="right">$Bukti.jumlahbayaran</td>					    
					  </tr>
					#end
					
					#if ($cnt1 == 0)
						<tr> 
					        <td scope="row"></td>
							<td colspan="6" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
						</tr> 
					#end
					</table> 
				
   			</fieldset> 			
	    </td>
 	</tr>
 	<!-- ****************** END UNTUK BUKTI PEMBAYARAN ************************* -->
 	
</table>  
