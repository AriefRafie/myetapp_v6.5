<!--frmBayaranNotis13.jsp-->
<!-- CL-02-010 -->
<style type="text/css">
<!--
.q {
	color: #F00;
}
.star {color: #F00;
}
input.kanan{ 
         text-align:right; 
         }
-->
</style>
#set ($inputstyle = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )
#foreach($N in $vNotis)
	#set($a = $N.NoKPPemilikAsal)
#end
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
						<input name="txtTarikhNotis5A" type="text" id="txtTarikhNotis5A" value="$!dat.tarikhnotis5a" size="11" maxlength="10" onBlur=doCalculateTarikh(); /> 
				      <a href="javascript:displayDatePicker('txtTarikhNotis5A',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
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
						<input name="txtTarikhTerimaNotis5A" type="text" id="txtTarikhTerimaNotis5A" value="$!dat.tarikhterima" size="11" maxlength="10" />
				      <a href="javascript:displayDatePicker('txtTarikhTerimaNotis5A',false,'dmy');"> <img src="../img/calendar.gif" alt="" border="0"/></a>
						</td>
					</tr>		
				#if($!idNegeriNotis=="13")
					<input name="txtTarikhLuputPertama" type="hidden" value="$!dat.tarikhluput1" size="11" maxlength="10" />
					<input name="txtTarikhLuputNotisKedua" type="hidden" value="$!dat.tarikhluput2" size="11" maxlength="10" />
					<input name="txtTarikhLuputNotisKetiga" type="hidden" value="$!dat.tarikhluput3" size="11" maxlength="10"/>

				#elseif($!idNegeriNotis=="12")
					<input name="txtTarikhLuputPertama" type="hidden" value="$!dat.tarikhluput1" size="11" maxlength="10" />
					<input name="txtTarikhLuputNotisKedua" type="hidden" value="$!dat.tarikhluput2" size="11" maxlength="10" />
					<input name="txtTarikhLuputNotisKetiga" type="hidden" value="$!dat.tarikhluput3" size="11" maxlength="10"/>
				#else
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="40%">
							<div align="left" class="labelinput">
							Tarikh Luput Notis Pertama</div>
						</td>
						<td width="1%">:</td>
						<td width="58%">
						<input name="txtTarikhLuputPertama" type="text" id="txtTarikhLuputPertama" value="$!dat.tarikhluput1" size="11" maxlength="10" />
				      <a href="javascript:displayDatePicker('txtTarikhLuputPertama',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
						</td>
					</tr>	
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="40%">
							<div align="left" class="labelinput">
								Tarikh Luput Notis Kedua
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
						<input name="txtTarikhLuputNotisKedua" type="text" id="txtTarikhLuputNotisKedua" value="$!dat.tarikhluput2" size="11" maxlength="10" />
				      <a href="javascript:displayDatePicker('txtTarikhLuputNotisKedua',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
						</td>
					</tr>		
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="40%">
							<div align="left" class="labelinput">
								Tarikh Luput Notis Ketiga
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
						<input name="txtTarikhLuputNotisKetiga" type="text" id="txtTarikhLuputNotisKetiga" value="$!dat.tarikhluput3" size="11" maxlength="10"/>
       <a href="javascript:displayDatePicker('txtTarikhLuputNotisKetiga',false,'dmy');">   
       <img src="../img/calendar.gif" alt="" border="0"/></a>
						</td>
					</tr>						
				#end			
	 	       			
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
											Kadar Cukai (RM)
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%"> <!---->
										<input type="text" $!inputStyleNum name="txtCukaiTahunPertama" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.kadarcukai"/>
										
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
										<input name="txtBayaranLain" class="kanan" type="text" id="txtBayaranLain" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayaranlain" />							
									</td>
								</tr>
				         		<tr>
									<td width="1%">							
										<span class="labelmandatory">*</span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Premium (RM)
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
										<input name="txtPremium" class="kanan" type="text" id="txtPremium" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarpremium"/>							
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
										<input type="text" class="kanan" name="txtBayarUkur" id="txtBayarUkur" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarukur"/>							
									</td>
								</tr>	
				         		<tr>
									<!-- <td width="1%">							
										<span class="labelmandatory"></span>
									</td>
									<td width="40%">
										<div align="right" class="labelinput">
											<div align="left">Tanda Sempadan (RM)</div>
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">-->
										<input type="hidden" name="txtTandaSempadan" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarsempadan"/>							
									<!-- </td>
								</tr>  -->	
				         		<!-- <tr>
									<td width="1%">							
										<span class="labelmandatory"></span>
									</td>
									<td width="40%">
										<div align="right" class="labelinput">
											<div align="left">Bayar Pendaftaran Hakmilik (RM)</div>
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%"> -->
										<input type="hidden" name="txtPendaftaranHakmilik" id="txtPendaftaranHakmilik" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarhakmilik"/>							
									<!-- </td>
								</tr> -->	 					
				         		<!-- <tr>
									<td width="1%">							
										<span class="labelmandatory"></span>
									</td>
									<td width="40%">
										<div align="right" class="labelinput">
											<div align="left">Bayaran Notis 5F (RM)</div>
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%"> -->
										<input type="hidden" name="txtBayaranNotisF" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarnotisf"/>
									<!-- </td>
								</tr>	-->
				         		<!--<tr>
									<td width="1%">							
										 <span class="labelmandatory"></span> 
									</td>
									<td width="40%">
										<div align="right" class="labelinput">
											<div align="left">Bayaran Fail (RM)</div>
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%"> -->
										<input type="hidden" name="txtBayaranFail" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarfail"/>							
									<!-- </td>
								</tr> -->							         		
				         		<!--<tr>
									<td width="1%">							
										 <span class="labelmandatory"></span> 
									</td>
									<td width="40%">
										<div align="right" class="labelinput">
											<div align="left">Bayaran Perenggan (RM)</div>
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%"> -->
										<input type="hidden" name="txtBayaranPerenggan" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarperenggan"/>							
									<!-- </td>
								</tr>  -->	
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
										<input type="text" class="kanan" name="txtBayaranLain2" id="txtBayaranLain2" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayaranlain2" />							
									</td>
								</tr>
				         		<!--<tr>
									<td width="1%">							
										 <span class="labelmandatory"></span>
									</td>
									<td width="40%">
										<div align="right" class="labelinput">
											<div align="left">Bayaran Lain 3 (RM)</div>
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%"> -->
										<input type="hidden" name="txtBayaranLain3" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayaranlain3" />							
									<!-- </td>
								</tr> -->						
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
										<input name="txtRayuanPremium" class="kanan" type="text" id="txtRayuanPremium" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.rayuanpremium"/>							
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
								<input name="txtJumBayaran" class="kanan" type="text" id="txtJumBayaran" onblur="validateCurrency(this,this.value,'');" value="$!dat.jumBayaran" $inputstyleread/>							
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

		<div >
	        	<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>

		</div>
		<div align="center">
			#if ($tabmode == '1')
				<input class="stylobutton100" type="button" value="Simpan" onclick="SimpanNotis()"/>
			#else
				<input class="stylobutton100" type="button" value="Simpan" onclick="KemaskiniNotis()"/>
			#end
		</div>
	
	<input type="hidden" name="idNotis" value="$idNotis" />