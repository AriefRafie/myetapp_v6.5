<!--frmBayaranNotis.jsp-->
<!-- CL-02-010 -->
<style type="text/css">
<!--
.q {
	color: #F00;
}
.star {color: #F00;
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
						<input name="txtTarikhNotis5A" type="text" id="txtTarikhNotis5A" onBlur=doCalculateTarikh(); value="$!dat.tarikhnotis5a" size="11" maxlength="10" /> 
				      <a href="javascript:displayDatePicker('txtTarikhNotis5A',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
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
						<td width="1%">
							</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Tarikh Luput Notis Pertama</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
						<input name="txtTarikhLuputPertama" type="text" id="txtTarikhLuputPertama" value="$!dat.tarikhluput1" size="11" maxlength="10" />
				      <a href="javascript:displayDatePicker('txtTarikhLuputPertama',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
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
						<input name="txtTarikhLuputNotisKedua" type="text" id="txtTarikhLuputNotisKedua" value="$!dat.tarikhluput2" size="11" maxlength="10" />
				      <a href="javascript:displayDatePicker('txtTarikhLuputNotisKedua',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
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
						<input name="txtTarikhLuputNotisKetiga" type="text" id="txtTarikhLuputNotisKetiga" value="$!dat.tarikhluput3" size="11" maxlength="10"/>
       <a href="javascript:displayDatePicker('txtTarikhLuputNotisKetiga',false,'dmy');">   
       <img src="../img/calendar.gif" alt="" border="0"/></a>
						</td>
					</tr>						
				#end			
																		
				</table>
			</td>
	       	
	       	<td valign="top">
	       		<table width="100%" border="0">
	         		<!-- <tr>
						<td width="1%">							
							<span class="labelmandatory"></span>
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Cukai Tahun Pertama (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%"> -->
							<input type="hidden" name="txtCukaiTahunPertama" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.kadarcukai"/>
						<!--	
						</td>
					</tr> -->
	         		<tr>
						<td width="1%">							
							<span class="labelmandatory">*</span>
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Premium (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							<input type="text" $!inputStyleNum name="txtPremium" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarpremium"/>							
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
							<input type="text" $!inputStyleNum name="txtBayarUkur" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarukur"/>							
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
							<input type="text" $!inputStyleNum name="txtPendaftaranHakmilik" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarhakmilik"/>							
						</td>
					</tr>	 
					
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
							<!-- <span class="labelmandatory">*</span> -->
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Bayaran Deraf Hakmilik (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							<input type="text" $!inputStyleNum name="txtBayaranLain" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayaranlain" />							
						</td>
					</tr>
	         		<tr>
						<td width="1%">							
							<!-- <span class="labelmandatory">*</span> -->
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Bayaran Penyediaan Hakmilik (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							<input type="text" $!inputStyleNum name="txtBayaranLain2" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayaranlain2" />							
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
			</td><!--</fieldset> -->
  		</tr>
  		
  		  	<tr><!--<fieldset><legend>Maklumat Notis</legend>-->
    		<td valign="top">
				<table width="100%" border="0">	
					<!-- <tr>
						<td valign="top" width="1%">
							</td>
						<td valign="top" width="40%">
							<div align="right" class="labelinput">
								<div align="left">Perihal Rayuan</div>
							</div>             
						</td>
						<td valign="top" width="1%">:</td>
						<td width="58%"> 
						<textarea name="txtPerihalRayuan" cols="30" rows="5" onkeyup="this.value=this.value.toUpperCase();">$!dat.perihalrayuan</textarea>
						 </td>
					</tr> -->				
						<input type="hidden" name="txTempohRayuan" value="$!dat.perihalrayuan"/>
					<!--<tr>
						<td valign="top" width="1%">
							</td>
						<td valign="top" width="40%">
							<div align="right" class="labelinput">
								<div align="left">Tempoh Rayuan</div>
							</div>             
						</td>
						<td valign="top" width="1%">:</td>
						<td width="58%"> -->
						<input type="hidden" name="txTempohRayuan" size="11" maxlength="10"/>
						<!--</td>
					</tr> -->	
					<!--<tr>
						<td valign="top" width="1%">
							</td>
						<td valign="top" width="40%">
							<div align="right" class="labelinput">
								<div align="left">Tarikh Rayuan</div>
							</div>             
						</td>
						<td valign="top" width="1%">:</td>
						<td width="58%"> -->
						<input type="hidden" name="txtTarikhRayuan" size="11" maxlength="10"/>
						<!--
               <a href="javascript:displayDatePicker('txtTarikhRayuan',false,'dmy');">   <img src="../img/calendar.gif" alt="" border="0"/>
               </a>
						</td>
					</tr> -->																				
				</table>
			</td>
	       	
	       	<td valign="top">
	       		<table width="100%" border="0">
	         		<tr>
						<td width="1%">							
							<span class="labelmandatory"><!-- *--></span> 
						</td>
						<td width="40%">
							<div align="right" class="labelinput">
								<div align="left">Rayuan Premium (-RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							<input type="text" $!inputStyleNum name="txtRayuanPremium" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.rayuanpremium"/>							
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
								<div align="left">Jumlah Bayaran (RM)</div>
							</div>             
						</td>
						<td width="1%">:</td>
						<td width="58%">
							<input type="text" $!inputStyleNum name="txtJumBayaran" onblur="validateCurrency(this,this.value,'');" value="$!dat.jumBayaran" $inputstyleread/>							
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