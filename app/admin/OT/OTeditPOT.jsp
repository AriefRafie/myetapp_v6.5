

#if($viewPOT.ID_PERMOHONANOT!="")
<script>
if( $jquery('#div_rowPOT$viewPOT.ID_PERMOHONANOT').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#div_rowPOT$viewPOT.ID_PERMOHONANOT').offset().top);
}
</script>
#else
<script>
if( $jquery('#div_addPOT').length )         // use this if you are using id to check
{
		window.scrollTo(0, $jquery('#div_addPOT').offset().top);
}
</script>
#end

<tr >
<td align="left" valign="top" colspan="14" >
	
	<fieldset class="classFade">
	<legend>
	
	#if($viewPOT.ID_PERMOHONANOT != "")
	Maklumat Permohonan OT : $viewPOT.NO_OT
	#else
	Kemasukan Maklumat OT
	#end
	</legend>
	
	
	<div id="printableArea_$viewPOT.ID_PERMOHONANOT">
	
		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%" id="td_checkTransaksiPOT_$viewPOT.ID_PERMOHONANOT">
			<input type="hidden" id="checkTransaksiPOT_$viewPOT.ID_PERMOHONANOT" name="checkTransaksiPOT_$viewPOT.ID_PERMOHONANOT" value="true" >
			</td>
			</tr>
			
			#if($viewPOT.NO_OT!="")
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				No. OT
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPOT.NO_OT
			<input style="text-transform:uppercase;" size="50" type="hidden" id="POT_NO_OT_$viewPOT.ID_PERMOHONANOT" 
				name="POT_NO_OT_$viewPOT.ID_PERMOHONANOT" 
				value="$viewPOT.NO_OT" onKeyUp="" >	
				</td>
			</tr>
			#end
			
			#if($viewPOT.ID_PERMOHONANOT != "")
			<tr>
				<td valign="top" ></td>			
				<td valign="top" >
				Tarikh Permohonan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPOT.TARIKH_MOHON				
				</td>
			</tr>
			<tr>
				<td valign="top" ></td>			
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPOT.STATUS				
				</td>
			</tr>
			#end
			<tr>
				<td valign="top" >
					
				</td>			
				<td valign="top" >
				Pemohon
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPOT.NAMA_PEMOHON				
				<input style="text-transform:uppercase;" size="50" type="hidden" id="POT_ID_PEMOHON_$viewPOT.ID_PERMOHONANOT" 
				name="POT_ID_PEMOHON_$viewPOT.ID_PERMOHONANOT" 
				value="$viewPOT.ID_PEMOHON" onKeyUp="" >	
				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
					
				</td>			
				<td valign="top" >
				Waktu Kerja
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($viewPOT.WAKTU_KERJA!="")
				$viewPOT.WAKTU_KERJA
				#else 
				<font color="red" class="blink">Tiada Rekod, Sila Kemaskini Profil Anda!</font>
				#end
				
								
				</td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >	
				#if($viewPOT.ID_PERMOHONANOT == "" || ($viewPOT.ID_STATUS == "1" && $viewPOT.ID_PELULUS == $USER_ID_SYSTEM))
				<font color="red" >*</font>	
				#end			
				</td>			
				<td valign="top" >
				Tarikh OT
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				
				Dari
				
		#if($viewPOT.ID_PERMOHONANOT == "" || ($viewPOT.ID_STATUS == "1" && $viewPOT.ID_PELULUS == $USER_ID_SYSTEM) )
		<input name="POT_TARIKH_OT_MULA_$viewPOT.ID_PERMOHONANOT" 
		onKeyUp="document.getElementById('POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT').value = this.value;checkFormatDate_V3(this,this.value,'span_CHECK_POT_TARIKH_OT_MULA_$viewPOT.ID_PERMOHONANOT');doDivAjaxCall3$formname('div_POT_DUPLICATE_DATE_$viewPOT.ID_PERMOHONANOT','checkDatePOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT');" 
			onBlur = "document.getElementById('POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT').value = this.value;doDivAjaxCall3$formname('div_POT_DUPLICATE_DATE_$viewPOT.ID_PERMOHONANOT','checkDatePOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT');"
			onFocus="document.getElementById('POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT').value = this.value;checkFormatDate_V3(this,this.value,'span_CHECK_POT_TARIKH_OT_MULA_$viewPOT.ID_PERMOHONANOT');doDivAjaxCall3$formname('div_POT_DUPLICATE_DATE_$viewPOT.ID_PERMOHONANOT','checkDatePOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT');" 
		type="text" id="POT_TARIKH_OT_MULA_$viewPOT.ID_PERMOHONANOT" style="text-transform:uppercase;" value="$viewPOT.TARIKH_MULA" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('POT_TARIKH_OT_MULA_$viewPOT.ID_PERMOHONANOT',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      #else
      $viewPOT.TARIKH_MULA
      <input type="hidden" id="POT_TARIKH_OT_MULA_$viewPOT.ID_PERMOHONANOT" name="POT_TARIKH_OT_MULA_$viewPOT.ID_PERMOHONANOT" value="$viewPOT.TARIKH_MULA" >
      #end
      <span id="span_CHECK_POT_TARIKH_OT_MULA_$viewPOT.ID_PERMOHONANOT">
      <input type='hidden' id='CHECK_POT_TARIKH_OT_MULA_$viewPOT.ID_PERMOHONANOT' name='CHECK_POT_TARIKH_OT_MULA_$viewPOT.ID_PERMOHONANOT' value='true' >
      </span>
      
      
      
       Sehingga
       #if($viewPOT.ID_PERMOHONANOT == "" || ($viewPOT.ID_STATUS == "1" && $viewPOT.ID_PELULUS == $USER_ID_SYSTEM) )	
				<input name="POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT" 
				onBlur = "doDivAjaxCall3$formname('div_POT_DUPLICATE_DATE_$viewPOT.ID_PERMOHONANOT','checkDatePOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT');"
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT');doDivAjaxCall3$formname('div_POT_DUPLICATE_DATE_$viewPOT.ID_PERMOHONANOT','checkDatePOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT');" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT');doDivAjaxCall3$formname('div_POT_DUPLICATE_DATE_$viewPOT.ID_PERMOHONANOT','checkDatePOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT');" 
				type="text" id="POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT" style="text-transform:uppercase;" value="$viewPOT.TARIKH_AKHIR" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       #else
       $viewPOT.TARIKH_AKHIR
       <input type="hidden" id="POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT" name="POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT" value="$viewPOT.TARIKH_AKHIR" >
       #end
       <span id="span_CHECK_POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT">
       <input type='hidden' id='CHECK_POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT' name='CHECK_POT_TARIKH_OT_AKHIR_$viewPOT.ID_PERMOHONANOT' value='true' >
       </span>
       <div id="div_POT_DUPLICATE_DATE_$viewPOT.ID_PERMOHONANOT" >
       <input type='hidden' id='CHECK_DUPLICATE_DATE_$viewPOT.ID_PERMOHONANOT' name='CHECK_DUPLICATE_DATE_$viewPOT.ID_PERMOHONANOT' value='true' >
       </div>
       
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >
				#if($viewPOT.ID_PERMOHONANOT=="")
				<font color="red" >*</font>	
				#end			
				</td>			
				<td valign="top" >
				Pelulus
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($viewPOT.ID_PERMOHONANOT!="")
				$viewPOT.NAMA_PELULUS
			<input style="text-transform:uppercase;" size="50" type="hidden" id="POT_ID_PELULUS_$viewPOT.ID_PERMOHONANOT" 
				name="POT_ID_PELULUS_$viewPOT.ID_PERMOHONANOT" 
				value="$viewPOT.ID_PELULUS" onKeyUp="" >	
				#else
				<select id="POT_ID_PELULUS_$viewPOT.ID_PERMOHONANOT"  name="POT_ID_PELULUS_$viewPOT.ID_PERMOHONANOT" 
				>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $PL in $listPelulus)		
							<option value="$PL.ID_PELULUS" >
							$PL.NAMA_PELULUS, $PL.JAWATAN
							</option>							
						#end
					</select>
					
					
				#end
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Catatan Pemohon
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($viewPOT.ID_PERMOHONANOT!="")
				$viewPOT.CATATAN_PEMOHON
		 		<input type="hidden" id="POT_CATATAN_PEMOHON_$viewPOT.ID_PERMOHONANOT" name="POT_CATATAN_PEMOHON_$viewPOT.ID_PERMOHONANOT" value="$viewPOT.CATATAN_PEMOHON" >
		 		#else
		 		 <textarea style="text-transform:uppercase;" name="POT_CATATAN_PEMOHON_$viewPOT.ID_PERMOHONANOT" 
				 id="POT_CATATAN_PEMOHON_$viewPOT.ID_PERMOHONANOT" cols="50" rows="5" 
		 onKeyup="check_length(this,4000,'span_POT_CATATAN_PEMOHON_$viewPOT.ID_PERMOHONANOT');" >$viewPOT.CATATAN_PEMOHON</textarea>
		 		#end
         <span id="span_POT_CATATAN_PEMOHON_$viewPOT.ID_PERMOHONANOT"  ></span>	
         </td>
		</tr>
			
		
		</table>
	
	#if(($viewPOT.ID_STATUS == "1" && $viewPOT.ID_PELULUS == $USER_ID_SYSTEM) || ($viewPOT.ID_STATUS == "2" || $viewPOT.ID_STATUS == "3"))

		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"><br></td>
			<td valign="top"  width="70%" id="td_checkTransaksiPOT_$viewPOT.ID_PERMOHONANOT">
			<input type="hidden" id="checkTransaksiPOT_$viewPOT.ID_PERMOHONANOT" name="checkTransaksiPOT_$viewPOT.ID_PERMOHONANOT" value="true" >
			</td>
			</tr>
			<tr >
			<td valign="top"  ></td>
			<td valign="top"  colspan="3" style="border-bottom: 2px solid #000;"><b>Maklumat Kelulusan</b></td>
			</tr>
			<tr>
				<td valign="top" >
				#if($viewPOT.ID_STATUS == "1")
				<font color="red" >*</font>
				#end
				</td>			
				<td valign="top" >
				Kelulusan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				#if($viewPOT.ID_STATUS == "2" || $viewPOT.ID_STATUS == "3")
				$viewPOT.STATUS
				#else
				
				#set($POT_CHECK_STATUS1 = "")
				#set($POT_CHECK_STATUS2 = "")
				#if($viewPOT.ID_STATUS=="2")
				#set($POT_CHECK_STATUS1 = "checked")
				#elseif($viewPOT.ID_STATUS=="3")
				#set($POT_CHECK_STATUS2 = "checked")
				#end		
				
				<input type="radio" id="RADIO_POT_ID_STATUS_$viewPOT.ID_PERMOHONANOT" 
				name="RADIO_POT_ID_STATUS_$viewPOT.ID_PERMOHONANOT" 
				onClick="fromRadioToText(this,this.value,'POT_ID_STATUS_$viewPOT.ID_PERMOHONANOT')" 
				value="2" $POT_CHECK_STATUS1>LULUS
				&nbsp;&nbsp;
				<input type="radio" id="RADIO_POT_ID_STATUS_$viewPOT.ID_PERMOHONANOT" 
				name="RADIO_POT_ID_STATUS_$viewPOT.ID_PERMOHONANOT" 
				onClick="fromRadioToText(this,this.value,'POT_ID_STATUS_$viewPOT.ID_PERMOHONANOT')" 
				value="3" $POT_CHECK_STATUS2>TOLAK
				
				#end
				
				<input type="hidden" id="POT_ID_STATUS_$viewPOT.ID_PERMOHONANOT" 
				name="POT_ID_STATUS_$viewPOT.ID_PERMOHONANOT" value="$viewPOT.ID_STATUS" >
				</td>
			</tr>
			
			<tr>
				<td valign="top" ></td>			
				<td valign="top" >
				Tarikh Keputusan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPOT.TARIKH_LULUS				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
					
				</td>			
				<td valign="top" >
				Cuti Umum
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($viewPOT.ID_STATUS == "2" || $viewPOT.ID_STATUS == "3")
					#if($viewPOT.FLAG_CUTI == "Y")
					YA
					#else
					TIDAK
					#end
				<input type="hidden" id="POT_FLAG_CUTI_$viewPOT.ID_PERMOHONANOT" 
					name="POT_FLAG_CUTI_$viewPOT.ID_PERMOHONANOT" 
					value="$viewPOT.FLAG_CUTI"  >
				#else
					#set($POT_CHECK_CUTI = "")				
					#if($viewPOT.FLAG_CUTI=="Y")
					#set($POT_CHECK_CUTI = "checked")
					#end						
					<input type="checkbox" id="POT_FLAG_CUTI_$viewPOT.ID_PERMOHONANOT" 
					name="POT_FLAG_CUTI_$viewPOT.ID_PERMOHONANOT" 
					value="Y" $POT_CHECK_CUTI >
				#end	
				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Catatan Pelulus
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($viewPOT.ID_STATUS == "2" || $viewPOT.ID_STATUS == "3")
				$viewPOT.CATATAN_PELULUS
				#else
		 		 <textarea style="text-transform:uppercase;" name="POT_CATATAN_PELULUS_$viewPOT.ID_PERMOHONANOT" 
				 id="POT_CATATAN_PELULUS_$viewPOT.ID_PERMOHONANOT" cols="50" rows="5" 
		 onKeyup="check_length(this,4000,'span_POT_CATATAN_PELULUS_$viewPOT.ID_PERMOHONANOT');" >$viewPOT.CATATAN_PELULUS</textarea>
         <span id="span_POT_CATATAN_PELULUS_$viewPOT.ID_PERMOHONANOT"  ></span>	
         
         		#end
         		<br><br>
         </td>
		</tr>		
		</table>
	
	
	#end
	
	#if($viewPOT.ID_STATUS != "")
	<table width="100%" border="0" >
				   <tr>
					<td valign="top"  width="1%"></td>
					<td valign="top"  width="28%"></td>
					<td valign="top"  width="1%"></td>
					<td valign="top"  width="70%" >
					</td>
					</tr>
					<tr >
			<td valign="top"  ></td>
			<td valign="top"  colspan="3" style="border-bottom: 2px solid #000;"><b>Rumusan Overtime</b></td>
		</tr>
				   <tr>
				   <td valign="top"  ></td>
				   	<td valign="top">Jumlah Hari</td><td valign="top">:</td><td valign="top">$OVERALLMINIT.OV_DAYS Hari</td>
				   </tr>
				   <tr>
				    <td valign="top"  ></td>
				   	<td valign="top">A: Total</td><td valign="top">:</td><td valign="top">$OVERALLMINIT.OV_MPD Minit</td>
				   <tr>
				   <tr>
				    <td valign="top"  ></td>
				   	<td valign="top">B: Waktu Kerja</td><td valign="top">:</td><td valign="top">
				   	#if($OVERALLMINIT.OV_MWH < $OVERALLMINIT.OV_MWH_ALL)
				   		<font color="red">$OVERALLMINIT.OV_MWH Minit</font>
				   	#else
				   		$OVERALLMINIT.OV_MWH Minit
				   	#end
				   	
				   	</td>
				   </tr>
				   <tr>
				    <td valign="top"  ></td>
				   	<td valign="top"><b>C: OT(A-B)</b></td><td valign="top">:</td><td valign="top"><b>$OVERALLMINIT.OV_MOT Minit</b></td>
				   </tr>
				   
	</table>
	
	
	
	<div id="SenaraiLogForPrint_$viewPOT.ID_PERMOHONANOT">	
	</div>
	
	
	</div>
	<br>
	<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%" >
			</td>
			</tr>
			<tr >
			<td valign="top"  ></td>
			<td valign="top"  colspan="3" style="border-bottom: 2px solid #000;"><b>Log Overtime</b></td>
		</tr>
		<tr >
			<td valign="top"  ></td>
			<td valign="top"  colspan="3" id="div_SenaraiLogOT" >
			#parse("app/admin/OT/OTlistDates_POT.jsp")
			<script>
			/*
			  $jquery(document).ready(function () {
				 doDivAjaxCall$formname('div_SenaraiLogOT','showListDateOT_POT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT');			 	  
			  });
			*/
			</script>		
			</td>
		</tr>   
	</table>
	#end
	
	
	<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%" >
			</td>
			</tr>
	<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >			
				#if($viewPOT.ID_PERMOHONANOT!="")		
					#if($viewPOT.ID_PERMOHONANOT == "" || ($viewPOT.ID_STATUS == "1" && $viewPOT.ID_PELULUS == $USER_ID_SYSTEM))		
					<input type="button" id="BTNSAVE$viewPOT.ID_PERMOHONANOT" name="BTNSAVE$viewPOT.ID_PERMOHONANOT" 
					onClick="if(valEditPOT('$viewPOT.ID_PERMOHONANOT')==true){doDivAjaxCall$formname('div_rowPOT$viewPOT.ID_PERMOHONANOT','savePOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT&BIL=$BIL&rowCss=$rowCss');}" value="Simpan" > 
		   			<input type="button" id="BTNBTL$viewPOT.ID_PERMOHONANOT" name="BTNBTL$viewPOT.ID_PERMOHONANOT" onClick="doDivAjaxCall$formname('div_rowPOT$viewPOT.ID_PERMOHONANOT','editPOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT&BIL=$BIL&rowCss=$rowCss');" value="Batal" > 
		   			#end
	   			<input type="button" id="BTNCLOSE$viewPOT.ID_PERMOHONANOT" name="BTNCLOSE$viewPOT.ID_PERMOHONANOT" onClick="doDivAjaxCall$formname('div_rowPOT$viewPOT.ID_PERMOHONANOT','closePOT','ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT&BIL=$BIL&rowCss=$rowCss');" value="Tutup" > 
	   			#if($viewPOT.ID_STATUS == "2")
	   			<input type="button" id="BTNPRINT$viewPOT.ID_PERMOHONANOT" name="BTNPRINT$viewPOT.ID_PERMOHONANOT" onclick="doDivAjaxCall$formname('SenaraiLogForPrint_$viewPOT.ID_PERMOHONANOT','showPrintPOT','TARIKH_AKHIR=$viewPOT.TARIKH_AKHIR&TARIKH_MULA=$viewPOT.TARIKH_MULA&FLAG_CUTI=$FLAG_CUTI&WAKTU_KERJA=$viewPOT.WAKTU_KERJA&NO_OT=$viewPOT.NO_OT&ID_PERMOHONANOT=$viewPOT.ID_PERMOHONANOT');" value="Cetak" />
	   			#end
	   			
	   			#else
		   			#if($viewPOT.ID_PERMOHONANOT == "" || ($viewPOT.ID_STATUS == "1" && $viewPOT.ID_PELULUS == $USER_ID_SYSTEM))
		   			<input type="button" id="BTNSAVE$viewPOT.ID_PERMOHONANOT" name="BTNSAVE$viewPOT.ID_PERMOHONANOT" 
					onClick="if(valEditPOT('')==true){doDivAjaxCall$formname('div_SenaraiPOT','insertPOT','ID_PERMOHONANOT=');}" value="Simpan" > 
		   			<input type="button" id="BTNBTL$viewPOT.ID_PERMOHONANOT" name="BTNBTL$viewPOT.ID_PERMOHONANOT" onClick="doDivAjaxCall$formname('div_addPOT','addPOT','ID_PERMOHONANOT=');" value="Batal" > 
		   			#end
	   			<input type="button" id="BTNCLOSE$viewPOT.ID_PERMOHONANOT" name="BTNCLOSE$viewPOT.ID_PERMOHONANOT" onClick="document.getElementById('div_addPOT').innerHTML='';" value="Tutup" > 
	   			#end
	   			</td>
			</tr>
	</table>
	
	
	
	</fieldset>
	
	
	<br>
</td>		
</tr>