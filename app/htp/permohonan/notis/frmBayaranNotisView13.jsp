<style type="text/css">
<!--
.q {
	color: #F00;
}
-->
</style>
<table width="100%" border="0">
	<tr>
	    <td>	    	
			#parse ("app/htp/permohonan/notis/frmBayaranNotis13.jsp")	    
   						   			
	    </td>
 	</tr>
 	<tr>
	    <td> 			
  			<fieldset>
				<legend>BUKTI PEMBAYARAN&nbsp;</legend>
				<!-- Mula TABLE level 1-->
			   	<table width="100%" border="0">
					<!-- Mula TR level 1-->
			  		<tr>
			 			<!-- Mula TD Kiri -->
			    		<td valign="top" width="50%">
				 	       	<table width="100%" border="0">
					         	<tr>
									<td width="1%">							
										<span class="labelmandatory">&nbsp;</span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Cara Bayaran
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
			        					$!socBayaran								
									</td>
								</tr> 
					         	<tr>
									<td width="1%">							
										<span class="labelmandatory">&nbsp;</span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											No. 
			        						#parse ("app/htp/permohonan/utiliti/frmBayaranLabelScript.jsp")
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
			        					<input name="txtNoBaucer" type="text" id="txtNoBaucer" onkeyup="this.value=this.value.toUpperCase();" value="$!frr"/>								
									</td>
								</tr> 
				         		<tr>
									<td width="1%">							
										<span class="labelmandatory"></span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Tarikh 
		        							#parse ("app/htp/permohonan/utiliti/frmBayaranLabelScript.jsp")
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
		         						<input name="txtTarikhBaucer" type="text" id="txtTarikhBaucer" value="$!ftt" />
		          						<a href="javascript:displayDatePicker('txtTarikhBaucer',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
									</td>
								</tr> 											  				
				         		<tr>
									<td width="1%">							
										<span class="labelmandatory"></span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Jumlah 
		        							#parse ("app/htp/permohonan/utiliti/frmBayaranLabelScript.jsp")
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
		         				          	<input name="txtJumBaucer" type="text" id="txtJumBaucer"  onblur="validateCurrency(this,this.value,'')" value="$!fuu" />
									</td>
								</tr> 
								
							</table>
			 			<!-- Tamat TD Kiri -->
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
											No. Resit
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										<input name="txtNoResit" type="text" id="txtNoResit" onkeyup="this.value=this.value.toUpperCase();" value="$!fii" />							
									</td>
								</tr>
				         		<tr>
									<td width="1%">							
										<span class="labelmandatory">&nbsp;</span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Tarikh Resit
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										<input name="txtTarikhResit" type="text" id="txtTarikhResit" value="$!fyy" />
		          						<a href="javascript:displayDatePicker('txtTarikhResit',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/>							
		          					</td>
								</tr>						
								
							</table>
			 			<!-- Tamat TD Kanan -->
						</td>	
					<!-- Tamat TR level 1-->
					</tr>		
				<!-- Tamat TABLE level 1-->
				</table>				
   			</fieldset> 
		</td>
	</tr>
 	 <tr>
	    <td> 			
        	<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	    	
		</td>
	</tr>
 	 <tr>
	    <td align="center" >
			<input class="stylobutton100" type="button" value="Simpan" onclick="BayaranNotis($idNotis)" />
		</td>
	</tr>
	<input type="hidden" name="idNotis5a" value="$idNotis5a" />
	<input type="hidden" name="dipermohonanNotis" id="dipermohonanNotis" value=" $idPermohonan" />	
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
					    <td width="18%">Tarikh 
					   	#parse ("app/htp/permohonan/utiliti/frmBayaranLabelScript.jsp")
					    </td>
					    <td width="18%">No.Resit</td>
					    <td width="20%">Tarikh Resit</td>
					    <td width="18%" align="right">Jumlah 
							#parse ("app/htp/permohonan/utiliti/frmBayaranLabelScript.jsp") (RM)   
					    </td>
					    <td width="5"></td>
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
					    <td class="$row">
					    	<input class="stylobutton100" type="button" value="Hapus" onclick="BayaranNotisHapus($!Bukti.idBayaran)" />
					    </td>
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
</table>  
