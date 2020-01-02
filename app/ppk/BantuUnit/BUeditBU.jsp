#if($viewBU.ID_PERMOHONANBANTUUNIT!="")
<script>
if( $jquery('#div_rowBU$viewBU.ID_PERMOHONANBANTUUNIT').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#div_rowBU$viewBU.ID_PERMOHONANBANTUUNIT').offset().top);
}
</script>
#else
<script>
if( $jquery('#div_addBU').length )         // use this if you are using id to check
{
		window.scrollTo(0, $jquery('#div_addBU').offset().top);
}
</script>
#end

<tr >
<td align="left" valign="top" colspan="14" >
	
	<fieldset class="classFade">
	<legend>
	
	#if($viewBU.ID_PERMOHONANBANTUUNIT != "")
	Maklumat Permohonan : $viewBU.NO_BANTUUNIT
	#else
	Kemasukan Maklumat
	#end
	</legend>
	
	
	<div id="printableArea_$viewBU.ID_PERMOHONANBANTUUNIT">
	
		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%" id="td_checkTransaksiBU_$viewBU.ID_PERMOHONANBANTUUNIT">
			<input type="hidden" id="checkTransaksiBU_$viewBU.ID_PERMOHONANBANTUUNIT" name="checkTransaksiBU_$viewBU.ID_PERMOHONANBANTUUNIT" value="true" >
			</td>
			</tr>
			
			#if($viewBU.NO_BANTUUNIT!="")
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				No. Bantu Unit</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBU.NO_BANTUUNIT
			<input style="text-transform:uppercase;" size="50" type="hidden" id="BU_NOBANTUUNIT_$viewBU.ID_PERMOHONANBANTUUNIT" 
				name="BU_NOBANTUUNIT_$viewBU.ID_PERMOHONANBANTUUNIT" 
				value="$viewBU.NO_BANTUUNIT" onKeyUp="" >	
				</td>
			</tr>
			#end
			
			#if($viewBU.ID_PERMOHONANBANTUUNIT != "")
			<tr>
				<td valign="top" ></td>			
				<td valign="top" >
				Tarikh Permohonan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBU.TARIKH_MOHON				
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
				$viewBU.STATUS				
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
				$viewBU.NAMA_PEMOHON				
				<input style="text-transform:uppercase;" size="50" type="hidden" id="BU_ID_PEMOHON_$viewBU.ID_PERMOHONANBANTUUNIT" 
				name="BU_ID_PEMOHON_$viewBU.ID_PERMOHONANBANTUUNIT" 
				value="$viewBU.ID_PEMOHON" onKeyUp="" >	
				
				</td>
			</tr>
            
            <tr>

				<td valign="top" >	
                #if($viewBU.ID_PERMOHONANBANTUUNIT == "" || ($viewBU.ID_STATUS == "1" && ($viewBU.ID_PEMOHON == $USER_ID_SYSTEM))  ||  ($viewBU.ID_STATUS == "1" && ($viewBU.ID_UNIT == $USER_UNIT)) || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true)	 )
				<font color="red" >*</font>	
				#end		
				</td>			
				<td valign="top" >
				Negeri Bantuan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                
                #set($display_negeri = "")
                #if($viewBU.ID_PERMOHONANBANTUUNIT == "" || ($viewBU.ID_STATUS == "1" && ($viewBU.ID_PEMOHON == $USER_ID_SYSTEM))  ||  ($viewBU.ID_STATUS == "1" && ($viewBU.ID_UNIT == $USER_UNIT)) || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true)	 )
                #set($display_negeri = "")
                #else
                $viewBU.NAMA_NEGERI
                #set($display_negeri = "style='display:none'")
                #end
				
              <select id="BU_ID_NEGERI_$viewBU.ID_PERMOHONANBANTUUNIT" $display_negeri name="BU_ID_NEGERI_$viewBU.ID_PERMOHONANBANTUUNIT" 
				onChange = "doDivAjaxCall$formname('td_BU_ID_UNIT_$viewBU.ID_PERMOHONANBANTUUNIT','showUnit_editBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT');doDivAjaxCall3$formname('div_BU_DUPLICATE_DATE_$viewBU.ID_PERMOHONANBANTUUNIT','checkDateBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT');"
				>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $N in $listNegeri)		
							#set ( $selected_N = "" )
							#if($viewBU.ID_NEGERI==$N.ID_NEGERI)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_NEGERI" >
							$N.NAMA_NEGERI
							</option>							
						#end
					</select>
				
							
				</td>
			</tr>
						
			
			
			<tr>
				<td valign="top" >	
                #if($viewBU.ID_PERMOHONANBANTUUNIT == "" || ($viewBU.ID_STATUS == "1" && ($viewBU.ID_PEMOHON == $USER_ID_SYSTEM))  ||  ($viewBU.ID_STATUS == "1" && ($viewBU.ID_UNIT == $USER_UNIT)) || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true) || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true)	 )
				<font color="red" >*</font>	
				#end		
				</td>			
				<td valign="top" >                
				Unit Bantuan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  id="td_BU_ID_UNIT_$viewBU.ID_PERMOHONANBANTUUNIT" >

                #set($display_unit = "")
                #if($viewBU.ID_PERMOHONANBANTUUNIT == "" || ($viewBU.ID_STATUS == "1" && ($viewBU.ID_PEMOHON == $USER_ID_SYSTEM))  ||  ($viewBU.ID_STATUS == "1" && ($viewBU.ID_UNIT == $USER_UNIT)) || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true) || ($viewBU.ID_STATUS == "1" && $jawatanPengarah == true	 ))
                	#set($display_unit = "")
                #else
                	$viewBU.NAMA_UNIT
                	#set($display_unit = "style='display:none'")
                #end
                
				<select id="BU_ID_UNIT_$viewBU.ID_PERMOHONANBANTUUNIT"  $display_unit name="BU_ID_UNIT_$viewBU.ID_PERMOHONANBANTUUNIT" onchange="doDivAjaxCall3$formname('div_BU_DUPLICATE_DATE_$viewBU.ID_PERMOHONANBANTUUNIT','checkDateBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT');">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $U in $listUnit)		
							#set ( $selected_U = "" )
							#if($viewBU.ID_UNIT==$U.ID_UNIT)
							#set ( $selected_U = "selected" )
							#end
							<option $selected_U value="$U.ID_UNIT" >
							#if($U.ID_UNIT=="ALL")
								$U.NAMA_UNIT
							#else
								$U.NAMA_UNIT
							#end							
							</option>							
						#end
					</select>
				
							
				</td>
			</tr>
			
			
			
			
			<tr>
				<td valign="top" >	
				#if($viewBU.ID_PERMOHONANBANTUUNIT == "" || ($viewBU.ID_STATUS == "1" && ($viewBU.ID_PEMOHON == $USER_ID_SYSTEM))  ||  ($viewBU.ID_STATUS == "1" && ($viewBU.ID_UNIT == $USER_UNIT)) || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true) || ($viewBU.ID_STATUS == "1" && $jawatanPengarah == true)	 )
				<font color="red" >*</font>	
				#end			
				</td>			
				<td valign="top" >
				Tarikh Bantuan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				
				Dari
				
		 #if($viewBU.ID_PERMOHONANBANTUUNIT == "" || ($viewBU.ID_STATUS == "1" && ($viewBU.ID_PEMOHON == $USER_ID_SYSTEM))  ||  ($viewBU.ID_STATUS == "1" && ($viewBU.ID_UNIT == $USER_UNIT)) || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true)	|| ($viewBU.ID_STATUS == "1" && $jawatanPengarah == true)	 )
		<input name="BU_TARIKH_BU_MULA_$viewBU.ID_PERMOHONANBANTUUNIT" 
		onKeyUp="document.getElementById('BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT').value = this.value;checkFormatDate_V3(this,this.value,'span_CHECK_BU_TARIKH_BU_MULA_$viewBU.ID_PERMOHONANBANTUUNIT');doDivAjaxCall3$formname('div_BU_DUPLICATE_DATE_$viewBU.ID_PERMOHONANBANTUUNIT','checkDateBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT');" 
			onBlur = "document.getElementById('BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT').value = this.value;doDivAjaxCall3$formname('div_BU_DUPLICATE_DATE_$viewBU.ID_PERMOHONANBANTUUNIT','checkDateBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT');"
			onFocus="document.getElementById('BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT').value = this.value;checkFormatDate_V3(this,this.value,'span_CHECK_BU_TARIKH_BU_MULA_$viewBU.ID_PERMOHONANBANTUUNIT');doDivAjaxCall3$formname('div_BU_DUPLICATE_DATE_$viewBU.ID_PERMOHONANBANTUUNIT','checkDateBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT');" 
		type="text" id="BU_TARIKH_BU_MULA_$viewBU.ID_PERMOHONANBANTUUNIT" style="text-transform:uppercase;" value="$viewBU.TARIKH_MULA" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('BU_TARIKH_BU_MULA_$viewBU.ID_PERMOHONANBANTUUNIT',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      #else
      $viewBU.TARIKH_MULA
      <input type="hidden" id="BU_TARIKH_BU_MULA_$viewBU.ID_PERMOHONANBANTUUNIT" name="BU_TARIKH_BU_MULA_$viewBU.ID_PERMOHONANBANTUUNIT" value="$viewBU.TARIKH_MULA" >
      #end
      <span id="span_CHECK_BU_TARIKH_BU_MULA_$viewBU.ID_PERMOHONANBANTUUNIT">
      <input type='hidden' id='CHECK_BU_TARIKH_BU_MULA_$viewBU.ID_PERMOHONANBANTUUNIT' name='CHECK_BU_TARIKH_BU_MULA_$viewBU.ID_PERMOHONANBANTUUNIT' value='true' >
      </span>
      
      
      &nbsp;
       Sehingga
       #if($viewBU.ID_PERMOHONANBANTUUNIT == "" || ($viewBU.ID_STATUS == "1" && ($viewBU.ID_PEMOHON == $USER_ID_SYSTEM))  ||  ($viewBU.ID_STATUS == "1" && ($viewBU.ID_UNIT == $USER_UNIT)) || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true)	|| ($viewBU.ID_STATUS == "1" && $jawatanPengarah == true)		 )
				<input name="BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT" 
				onBlur = "doDivAjaxCall3$formname('div_BU_DUPLICATE_DATE_$viewBU.ID_PERMOHONANBANTUUNIT','checkDateBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT');"
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT');doDivAjaxCall3$formname('div_BU_DUPLICATE_DATE_$viewBU.ID_PERMOHONANBANTUUNIT','checkDateBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT');" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT');doDivAjaxCall3$formname('div_BU_DUPLICATE_DATE_$viewBU.ID_PERMOHONANBANTUUNIT','checkDateBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT');" 
				type="text" id="BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT" style="text-transform:uppercase;" value="$viewBU.TARIKH_AKHIR" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       #else
       $viewBU.TARIKH_AKHIR
       <input type="hidden" id="BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT" name="BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT" value="$viewBU.TARIKH_AKHIR" >
       #end
       <span id="span_CHECK_BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT">
       <input type='hidden' id='CHECK_BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT' name='CHECK_BU_TARIKH_BU_AKHIR_$viewBU.ID_PERMOHONANBANTUUNIT' value='true' >
       </span>
       <div id="div_BU_DUPLICATE_DATE_$viewBU.ID_PERMOHONANBANTUUNIT" >
       <input type='hidden' id='CHECK_DUPLICATE_DATE_$viewBU.ID_PERMOHONANBANTUUNIT' name='CHECK_DUPLICATE_DATE_$viewBU.ID_PERMOHONANBANTUUNIT' value='true' >
       </div>
       
				</td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >
				#if($viewBU.ID_PERMOHONANBANTUUNIT == "" || ($viewBU.ID_STATUS == "1" && ($viewBU.ID_PEMOHON == $USER_ID_SYSTEM))  ||  ($viewBU.ID_STATUS == "1" && ($viewBU.ID_UNIT == $USER_UNIT)) || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true)		|| ($viewBU.ID_STATUS == "1" && $jawatanPengarah == true)	 )
				<font color="red" >*</font>	
				#end	
				</td>			
				<td valign="top" >
				Catatan Pemohon
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				 #if($viewBU.ID_PERMOHONANBANTUUNIT == "" || ($viewBU.ID_STATUS == "1" && ($viewBU.ID_PEMOHON == $USER_ID_SYSTEM))  ||  ($viewBU.ID_STATUS == "1" && ($viewBU.ID_UNIT == $USER_UNIT)) || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true)	|| ($viewBU.ID_STATUS == "1" && $jawatanPengarah == true)		 )	 
				 
				  <textarea style="text-transform:uppercase;" name="BU_CATATAN_PEMOHON_$viewBU.ID_PERMOHONANBANTUUNIT" 
				 id="BU_CATATAN_PEMOHON_$viewBU.ID_PERMOHONANBANTUUNIT" cols="50" rows="5" 
		 		onKeyup="check_length(this,4000,'span_BU_CATATAN_PEMOHON_$viewBU.ID_PERMOHONANBANTUUNIT');" >$viewBU.CATATAN_PEMOHON</textarea>
				
		 		#else
		 		
		 		$viewBU.CATATAN_PEMOHON
		 		<input type="hidden" id="BU_CATATAN_PEMOHON_$viewBU.ID_PERMOHONANBANTUUNIT" name="BU_CATATAN_PEMOHON_$viewBU.ID_PERMOHONANBANTUUNIT" value="$viewBU.CATATAN_PEMOHON" >
		 		
		 		#end
         <span id="span_BU_CATATAN_PEMOHON_$viewBU.ID_PERMOHONANBANTUUNIT"  ></span>	
         </td>
		</tr>
			
		
		</table>
	
	#if(($viewBU.ID_STATUS == "1" && ($viewBU.ID_STATUS == "1" && ($viewBU.ID_PEMOHON == $USER_ID_SYSTEM))) || ($viewBU.ID_STATUS == "2" || $viewBU.ID_STATUS == "3") || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true)	|| ($viewBU.ID_STATUS == "1" && $jawatanPengarah == true)	   )

		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"><br></td>
			<td valign="top"  width="70%" id="td_checkTransaksiBU_$viewBU.ID_PERMOHONANBANTUUNIT">
			<input type="hidden" id="checkTransaksiBU_$viewBU.ID_PERMOHONANBANTUUNIT" name="checkTransaksiBU_$viewBU.ID_PERMOHONANBANTUUNIT" value="true" >
			</td>
			</tr>
			<tr >
			<td valign="top"  ></td>
			<td valign="top"  colspan="3" style="border-bottom: 2px solid #000;"><b>Maklumat Kelulusan</b></td>
			</tr>
			<tr>
				<td valign="top" >
				#if($viewBU.ID_STATUS == "1")
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
				
				#if($viewBU.ID_STATUS == "2" || $viewBU.ID_STATUS == "3")
				$viewBU.STATUS
				#else
				
				#set($BU_CHECK_STATUS1 = "")
				#set($BU_CHECK_STATUS2 = "")
				#if($viewBU.ID_STATUS=="2")
				#set($BU_CHECK_STATUS1 = "checked")
				#elseif($viewBU.ID_STATUS=="3")
				#set($BU_CHECK_STATUS2 = "checked")
				#end		
				
				<input type="radio" id="RADIO_BU_ID_STATUS_$viewBU.ID_PERMOHONANBANTUUNIT" 
				name="RADIO_BU_ID_STATUS_$viewBU.ID_PERMOHONANBANTUUNIT" 
				onClick="fromRadioToText(this,this.value,'BU_ID_STATUS_$viewBU.ID_PERMOHONANBANTUUNIT')" 
				value="2" $BU_CHECK_STATUS1>LULUS
				&nbsp;&nbsp;
				<input type="radio" id="RADIO_BU_ID_STATUS_$viewBU.ID_PERMOHONANBANTUUNIT" 
				name="RADIO_BU_ID_STATUS_$viewBU.ID_PERMOHONANBANTUUNIT" 
				onClick="fromRadioToText(this,this.value,'BU_ID_STATUS_$viewBU.ID_PERMOHONANBANTUUNIT')" 
				value="3" $BU_CHECK_STATUS2>TOLAK
				
				#end
				
				
				</td>
			</tr>
            
            <tr>
				<td valign="top" >
					
				</td>			
				<td valign="top" >
				Pelulus
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBU.NAMA_PELULUS				
				<input style="text-transform:uppercase;" size="50" type="hidden" id="BU_ID_PELULUS_$viewBU.ID_PERMOHONANBANTUUNIT" 
				name="BU_ID_PELULUS_$viewBU.ID_PERMOHONANBANTUUNIT" 
				value="$viewBU.ID_PELULUS" onKeyUp="" >	
				
				</td>
			</tr>
			
            #if($viewBU.TARIKH_LULUS!="")
			<tr>
				<td valign="top" ></td>			
				<td valign="top" >
				Tarikh Keputusan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBU.TARIKH_LULUS				
				</td>
			</tr>
			#end
			
			
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
				#if($viewBU.ID_STATUS == "2" || $viewBU.ID_STATUS == "3")
				$viewBU.CATATAN_PELULUS
				#else
		 		 <textarea style="text-transform:uppercase;" name="BU_CATATAN_PELULUS_$viewBU.ID_PERMOHONANBANTUUNIT" 
				 id="BU_CATATAN_PELULUS_$viewBU.ID_PERMOHONANBANTUUNIT" cols="50" rows="5" 
		 		onKeyup="check_length(this,4000,'span_BU_CATATAN_PELULUS_$viewBU.ID_PERMOHONANBANTUUNIT');" >$viewBU.CATATAN_PELULUS</textarea>
         		<span id="span_BU_CATATAN_PELULUS_$viewBU.ID_PERMOHONANBANTUUNIT"  ></span>	
         
         		#end
         		<br><br>
         </td>
		</tr>		
		</table>
	
	
	#end
	
	</div>
	
	<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%" >
			<input type="hidden" id="BU_ID_STATUS_$viewBU.ID_PERMOHONANBANTUUNIT" name="BU_ID_STATUS_$viewBU.ID_PERMOHONANBANTUUNIT" value="$viewBU.ID_STATUS" >
			<input type="hidden" id="BU_ID_UNIT_$viewBU.ID_PERMOHONANBANTUUNIT" name="BU_ID_UNIT_$viewBU.ID_PERMOHONANBANTUUNIT" value="$viewBU.ID_UNIT" >
			<input type="hidden" id="USER_UNIT" name="USER_UNIT" value="$USER_UNIT" >
			<input type="hidden" id="jawatanKpp" name="jawatanKpp" value="$jawatanKpp" >
			
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
				#if($viewBU.ID_PERMOHONANBANTUUNIT!="")		
				
                    #if($viewBU.ID_PERMOHONANBANTUUNIT == "" ||   ($viewBU.ID_STATUS == "1" && ($viewBU.ID_UNIT == $USER_UNIT)) || ($viewBU.ID_STATUS == "1" && $jawatanKpp == true) 	|| ($viewBU.ID_STATUS == "1" && $jawatanPengarah == true)		 )
                    <input type="button" id="BTNSAVE$viewBU.ID_PERMOHONANBANTUUNIT" name="BTNSAVE$viewBU.ID_PERMOHONANBANTUUNIT" 
                    onClick="if(valEditBU('$viewBU.ID_PERMOHONANBANTUUNIT')==true){doDivAjaxCall$formname('div_rowBU$viewBU.ID_PERMOHONANBANTUUNIT','saveBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT&BIL=$BIL&rowCss=$rowCss');}" value="Simpan" > 
                    <input type="button" id="BTNBTL$viewBU.ID_PERMOHONANBANTUUNIT" name="BTNBTL$viewBU.ID_PERMOHONANBANTUUNIT" onClick="doDivAjaxCall$formname('div_rowBU$viewBU.ID_PERMOHONANBANTUUNIT','editBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT&BIL=$BIL&rowCss=$rowCss');" value="Batal" > 
                    #end
                    <input type="button" id="BTNCLOSE$viewBU.ID_PERMOHONANBANTUUNIT" name="BTNCLOSE$viewBU.ID_PERMOHONANBANTUUNIT" onClick="doDivAjaxCall$formname('div_rowBU$viewBU.ID_PERMOHONANBANTUUNIT','closeBU','ID_PERMOHONANBANTUUNIT=$viewBU.ID_PERMOHONANBANTUUNIT&BIL=$BIL&rowCss=$rowCss');" value="Tutup" > 
                    #if($viewBU.ID_STATUS == "2")
                    <input type="button" id="BTNPRINT$viewBU.ID_PERMOHONANBANTUUNIT" name="BTNPRINT$viewBU.ID_PERMOHONANBANTUUNIT" 
                    onclick="printBUForm('printableArea_$viewBU.ID_PERMOHONANBANTUUNIT','$viewBU.NO_BANTUUNIT')" 
                    value="Cetak" />
                    #end
	   			
	   			#else
	   			
		   			#if($viewBU.ID_PERMOHONANBANTUUNIT == "" || ($viewBU.ID_STATUS == "1" && $viewBU.ID_PELULUS == $USER_ID_SYSTEM))
		   			<input type="button" id="BTNSAVE$viewBU.ID_PERMOHONANBANTUUNIT" name="BTNSAVE$viewBU.ID_PERMOHONANBANTUUNIT" 
					onClick="if(valEditBU('')==true){doDivAjaxCall$formname('div_SenaraiBU','insertBU','ID_PERMOHONANBANTUUNIT=');}" value="Simpan" > 
		   			<input type="button" id="BTNBTL$viewBU.ID_PERMOHONANBANTUUNIT" name="BTNBTL$viewBU.ID_PERMOHONANBANTUUNIT" onClick="doDivAjaxCall$formname('div_addBU','addBU','ID_PERMOHONANBANTUUNIT=');" value="Batal" > 
		   			#end
	   			<input type="button" id="BTNCLOSE$viewBU.ID_PERMOHONANBANTUUNIT" name="BTNCLOSE$viewBU.ID_PERMOHONANBANTUUNIT" onClick="document.getElementById('div_addBU').innerHTML='';" value="Tutup" > 
	   			#end
	   			</td>
			</tr>
	</table>
	
	
	
	</fieldset>
	
	
	<br>
</td>		
</tr>