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
 	<!-- ****************** START UNTUK BUKTI PEMBAYARAN ************************* -->
	#if($mode=="new") #if($bukti_pembayaran_Salin.size()>0)

	#set($txtNoBaucer=$bukti_pembayaran_Salin.txtNoBaucer)
	#set($txtNoResit=$bukti_pembayaran_Salin.txtNoResit)
	#set($txdTarikhBaucer=$bukti_pembayaran_Salin.txdTarikhBaucer)
	#set($txdTarikhResit=$bukti_pembayaran_Salin.txdTarikhResit)
	#set($txtJumlahBaucer=$bukti_pembayaran_Salin.txtJumlahBaucer) #end
	#end
	<tr>
		<td>
			<fieldset>
				<table width="100%" border="0">
					<legend>Bukti Pembayaran</legend>
					<td valign="top">
						<table width="100%" border="0">
							<tr>
								<td width="40%">
									<div align="right" class="labelinput">
										<div align="left">No. Baucer</div>
									</div>
								</td>
								<td width="1%">:</td>
								<td><input id="txtNoBaucer" name="txtNoBaucer" type="text"
									value="$!txtNoBaucer"></td>
							</tr>
							<tr>
								<td width="40%">
									<div align="right" class="labelinput">
										<div align="left">No. Resit</div>
									</div>
								</td>
								<td width="1%">:</td>
								<td><input id="txtNoResit" name="txtNoResit"
									value="$!txtNoResit" type="text"></td>
							</tr>
						</table>
					</td>

					<td valign="top">
						<table width="100%" border="0">
							<tr>
								<td width="20%">
									<div align="right" class="labelinput">
										<div align="left">Tarikh Baucer</div>
									</div>
								</td>
								<td width="1%">:</td>
								<td><input name="txdTarikhBaucer" id="txdTarikhBaucer"
									type="text" value="$!txdTarikhBaucer"
									onkeyup="validateTarikh(this,this.value)"> <img
									src="../img/calendar.gif"
									onclick="displayDatePicker('txdTarikhBaucer',false,'dmy');"></td>
							</tr>
							<tr>
								<td width="40%">
									<div align="right" class="labelinput">
										<div align="left">Tarikh Resit</div>
									</div>
								</td>
								<td width="1%">:</td>
								<td><input name="txdTarikhResit" id="txdTarikhResit"
									type="text" value="$!txdTarikhResit"
									onkeyup="validateTarikh(this,this.value)"> <img
									src="../img/calendar.gif"
									onclick="displayDatePicker('txdTarikhResit',false,'dmy');"></td>
							</tr>

							<tr>
								<td width="40%">
									<div align="right" class="labelinput">
										<div align="left">Jumlah Baucer</div>
									</div>
								</td>
								<td width="1%">:</td>
								<td><input id="txtJumlahBaucer" name="txtJumlahBaucer"
									type="text" value="$!txtJumlahBaucer"></td>
							</tr>
							<tr>
								<td><input id="btnSimpan" type="button"
									value="Simpan Bukti Pembayaran"
									onClick="javascript:simpanBuktiPembayaran('$!idpermohonan', '$!mode')">
							</tr>

						</table>
					</td>
				</table>
			</fieldset>
	</tr>
	</td>
	<!-- ****************** END UNTUK BUKTI PEMBAYARAN**************************** -->
	<!-- ****************** START UNTUK MAKLUMAT DOKUMEN ************************* -->
	#if($mode=="new") #if($maklumat_Dokumen_Salin.size()>0)

	#set($txtNamaDokumen=$maklumat_Dokumen_Salin.txtNamaDokumen)
	#set($txtKeterangan=$maklumat_Dokumen_Salin.txtKeterangan) #end #end
	<tr>
	<td>
	<fieldset>
		<legend>Maklumat Dokumen</legend>
		<table>
			<tr>
				<td><font color="red">*</font></td>
				<td valign="top" width="40%">
					<div align="right" class="labelinput">
						<div align="left">Nama Dokumen</div>
					</div>
				</td>
				<td>:</td>
				<td width="58%"><input id="txtNamaDokumen"
					nama="txtNamaDokumen" value="$!txtNamaDokumen" type="text"
					onblur="javascript:updatetxtNamaDokumen()"></td>
			</tr>
			<tr>
				<td valign="top" width="1%"></td>
				<td valign="top" width="40%">
					<div align="right" class="labelinput">
						<div align="left">Keterangan</div>
					</div>
				</td>
				<td valign="top" width="1%">:</td>
				<td width="58%"><textarea name="txtKeterangan"
						id="txtKeterangan" cols="70%" rows="10"
						onKeyUp="textCounter(this.form.txtKeterangan,this.form.remLen1,4000);"
						onKeyDown="textCounter(this.form.txtKeterangan,this.form.remLen1,4000);"
						onblur="javascript:updatetxtKeterangan()">$!txtKeterangan</textarea></td>
			</tr>
			<tr>
				<td><font color="red">*</font></td>
				<td>
					<div width="1%" class="labelinput">
						<div align="left">Lampiran Dokumen</div>
					</div>
				</td>
				<td>:</td>
				<td width="58%"><input class="texts" id="txtNamaDokumen2"
					name="txtNamaDokumen2" type="file"></td>

			</tr>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><input type="button" name="cmdUpdate" value="Simpan"
					onClick="javascript:simpanMD('$!idpermohonan', '$!mode')">
					<input type="button" name="cmdBatal" value="Batal"
					onClick="javascript:batalMaklumatDokumen('$!id_permohonan')">
				</td>
			</tr>

		</table>
	</fieldset>
	<input type="hidden" name="flag_subjaket" value="$!flag_subjaket">
	<input type="hidden" name="txtNamaDokumenHidden">
	<input type="hidden" name="txtKeteranganHidden" value="$!txtKeterangan">
	</tr>
	</td>

	<!-- ****************** END UNTUK MAKLUMAT DOKUMEN *************************** -->
 	
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
					#foreach ( $Bukti in $BuktiBayaranInfo2 )
						#set ( $cnt1 = $cnt1 + 1 )
		                #set( $i = $velocityCount )
		                #if ( ($i % 2) == 0 )
		                    #set( $row = "row2" )
		                #else
		                    #set( $row = "row1" )
		                #end
						 <tr>
						<td class="$row">$cnt1.</td>
						<td class="$row">$Bukti.noBaucer</td>
						<td class="$row">$Bukti.noResit</td>
						<td class="$row">$Bukti.tarikhbaucer</td>
						<td class="$row">$Bukti.tarikhresit</td>
						<td class="$row">$Bukti.jumlahBaucer</td>
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
