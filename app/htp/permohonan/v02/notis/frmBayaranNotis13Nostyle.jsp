 			<!-- --> <fieldset>
				<legend>MAKLUMAT 
				 #parse ("app/htp/permohonan/notis/frmNotisHeaderScript.jsp")
				 </legend>
				   		<table width="100%" border="1">
				<!-- Mula TR level 1-->
		  			<tr>
		 			<!-- Mula TD Kiri -->
		    			<td valign="top"  width="50%">
			 	       		<table width="100%" border="0">
				         		<tr>
									<td width="1%">							
										<span class="labelmandatory">&nbsp;</span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
										Tarikh 
					 					#parse ("app/htp/permohonan/notis/frmNotisScript.jsp")
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%">
								<input name="txtTarikhNotis5A" type="text" id="txtTarikhNotis5A" onBlur=doCalculateTarikh(); value="$!dat.tarikhnotis5a" size="11" maxlength="10" /> 
						      <a href="javascript:displayDatePicker('txtTarikhNotis5A',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
										</td>
								</tr> 
				         		<tr>
									<td width="1%">							
										<span class="labelmandatory">&nbsp;</span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
										Tarikh Terima 
										#parse ("app/htp/permohonan/notis/frmNotisScript.jsp")&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
		
						#end
							</table>
		 			<!-- Tamat TD Kiri -->
						</td>
			       	
		 			<!-- Mula TD Kanan -->
			       		<td valign="top"  width="50%">
			       			<table width="100%" border="0">
				         		 <tr>
									<td width="1%">							
										<span class="labelmandatory">&nbsp;</span>
									</td>
									<td width="40%">
										<div align="left" class="labelinput">
											Kadar Cukai (RM
										</div>             
									</td>
									<td width="1%">:</td>
									<td width="58%"> <!---->
										<input type="text" name="txtCukaiTahunPertama" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.kadarcukai"/>
										
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
										<input name="txtBayaranLain" type="text" id="txtBayaranLain" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayaranlain" />							
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
										<input name="txtPremium" type="text" id="txtPremium" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarpremium"/>							
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
										<input type="text" name="txtBayarUkur" id="txtBayarUkur" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarukur"/>							
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
										<input type="text" name="txtBayaranLain2" id="txtBayaranLain2" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayaranlain2" />							
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
							</table>
		 			<!-- Tamat TD Kanan -->
						</td>	
				<!-- Tamat TR level 1-->
					</tr>		
				<!-- Tamat TABLE level 1-->
				</table>
   			</fieldset>
