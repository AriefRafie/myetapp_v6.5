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
	
	<!-- ****************** START UNTUK MAKLUMAT BAYARAN L & S 80 ************************* -->
	<tr>
	    <td>	    	
			## #parse ("app/htp/permohonan/notis/frmBayaranNotis13.jsp")<!-- path file asal sebelum diubah kepada view sahaja. -->
   			<fieldset>
				<legend>MAKLUMAT 
						 #parse ("app/htp/permohonan/notis/frmNotisHeaderScript.jsp")
				</legend>
				<table width="100%" border="0">
					<!-- Mula TR level 1-->
		  			<tr>
		 				<!-- Mula TD Kiri -->
		    			<td valign="top" width="50%">
			 	       		<table width="100%" border="0">
								<tr>
									<td width="1%">
										<span class="labelmandatory"><!--*-->&nbsp;</span>						
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Tarikh 
						 					#parse ("app/htp/permohonan/notis/frmNotisScript.jsp")
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.tarikhnotis5a
									</td>
								</tr>
								<tr>
									<td width="1%">&nbsp;</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Tarikh Terima 
											#parse ("app/htp/permohonan/notis/frmNotisScript.jsp")
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.tarikhterima
									</td>
								</tr>
							</table>
						</td>
			       	
		 				<!-- Mula TD Kanan -->
			       		<td valign="top" width="50%">
			       			<table width="100%" border="0">
				         		 <tr>
									<td width="1%">							
										<span class="labelmandatory">&nbsp;</span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Kadar Cukai (RM)
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%"> <!---->
										$!dat.kadarcukai
									</td>
								</tr> <!---->
								<tr>
									<td width="1%">							
										 <span class="labelmandatory">&nbsp;</span> <!---->
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Bayaran Cukai Tertunggak (RM)
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.bayaranlain					
									</td>
								</tr>
				         		<tr>
									<td width="1%">							
										<span class="labelmandatory"><!-- * --></span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Premium (RM)
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.bayarpremium			
									</td>
								</tr>					
				         		<tr>
									<td width="1%">							
										<span class="labelmandatory">&nbsp;</span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Bayaran Ukur(RM)
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.bayarukur					
									</td>
								</tr>	
				         		<tr>
									<td width="1%">							
										<span class="labelmandatory">&nbsp;</span> <!-- -->
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Bayaran Penyediaan Hakmilik (RM)
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.bayaranlain2
									</td>
								</tr>
				         		<tr>
									<td width="1%">							
										<span class="labelmandatory">&nbsp;<!-- *--></span> 
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Rayuan Premium (-RM)
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.rayuanpremium					
									</td>
								</tr>					
			         			<tr>
									<td width="1%">							
										<span class="labelmandatory">&nbsp;<!-- * --></span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Jumlah Bayaran (RM)
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										$!dat.jumBayaran						
									</td>
								</tr>	       				
							</table>
						</td>	
						<!-- Mula TD Kanan -->
					</tr>		
				</table>
			</fieldset>		   			
	    </td>
 	</tr>
 	<!-- ****************** END UNTUK MAKLUMAT BAYARAN L & S 80 ************************* -->
 	
 	<!-- ****************** START UNTUK SENARAI BUKTI PEMBAYARAN ************************* -->
 	 <tr>
	    <td> 			
  			<fieldset>
				<legend>SENARAI BUKTI PEMBAYARAN&nbsp;</legend>
				<table width="100%" border="0">
					<tr class="table_header">
						<td width="3%">Bil.</td>
						<td width="18%">No. 
							#parse ("app/htp/permohonan/utiliti/frmBayaranLabelScript.jsp")
						</td>
						<td width="18%">Tarikh 
							#parse ("app/htp/permohonan/utiliti/frmBayaranLabelScript.jsp")
						</td>
						<td width="18%">No.Resit</td>
						<td width="20%">Tarikh Resit</td>
						<td width="18%" align="right">Jumlah 
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
					
					#if ($cnt == 0)
						<tr> 
							<td colspan="4" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
					        <td colspan="3" scope="row"></td>
						</tr> 
					#end
					
				</table> 
   			</fieldset> 			
	    </td>
 	</tr>
 	<!-- ****************** END UNTUK SENARAI BUKTI PEMBAYARAN ************************* -->
</table>  
