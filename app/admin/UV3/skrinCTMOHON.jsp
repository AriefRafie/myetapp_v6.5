#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_$internalType').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_$internalType').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_$internalType').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_$internalType').style.display = "";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "";
</script>
#end

<input size="50" type="hidden" id="CT_OPENCLOSE_CARIAN_$internalType" 
				name="CT_OPENCLOSE_CARIAN_$internalType" 
				value="$CT_OPENCLOSE_CARIAN" 
				>
				<input size="50" type="hidden" id="CT_FLAGTEPERINCI_CARIAN_$internalType" 
				name="CT_FLAGTEPERINCI_CARIAN_$internalType" 
				value="$CT_FLAGTEPERINCI_CARIAN" 
				>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" id="TABLE_CT_ID_$internalType">
<tr  >
<td  width="100%"  valign="top">
	
	
	
	<fieldset>
	<legend>Carian Terperinci</legend>
	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="2">	
	<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama / Username	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<input size="50" type="text" id="CT_NAMA_$internalType" 
				name="CT_NAMA_$internalType" 
				value="$CT_NAMA" 
				>
				
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Bahagian	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="CT_ID_SEKSYEN_$internalType"  
					name="CT_ID_SEKSYEN_$internalType" 
					onChange = "doDivAjaxCall$formname('div_CT_ID_PEJABATJKPTG_$internalType','showListPejabat_CT','ID_SEKSYEN='+$jquery('#CT_ID_SEKSYEN_$internalType').val()+'&ID_NEGERI='+$jquery('#CT_ID_NEGERI_$internalType').val()+'&internalType=$internalType');"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJSEKSYEN )		
							#set ( $selected_CT_ID_SEKSYEN = "" )
							#if($CT_ID_SEKSYEN==$ruj.ID)
							#set ( $selected_CT_ID_SEKSYEN = "selected" )
							#end		
							<option $selected_CT_ID_SEKSYEN value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >	
				</td>			
				<td valign="top" >
				Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="CT_ID_NEGERI_$internalType"  name="CT_ID_NEGERI_$internalType"
					onChange = "doDivAjaxCall$formname('div_CT_ID_PEJABATJKPTG_$internalType','showListPejabat_CT','ID_SEKSYEN='+$jquery('#CT_ID_SEKSYEN_$internalType').val()+'&ID_NEGERI='+$jquery('#CT_ID_NEGERI_$internalType').val()+'&internalType=$internalType');"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJNEGERI )		
							#set ( $selected_CT_ID_NEGERI = "" )
							#if($CT_ID_NEGERI==$ruj.ID)
							#set ( $selected_CT_ID_NEGERI = "selected" )
							#end			
							
							<option $selected_CT_ID_NEGERI value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>					
							
						#end
					</select>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >	
				</td>			
				<td valign="top" >
				Unit / Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td id="div_CT_ID_PEJABATJKPTG_$internalType" >
					<select id="CT_ID_PEJABATJKPTG_$internalType"  name="CT_ID_PEJABATJKPTG_$internalType" 
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listPejabatJKPTG )		
							#set ( $selected_CT_ID_PEJABATJKPTG = "" )
							#if($CT_ID_PEJABATJKPTG==$ruj.ID_PEJABATJKPTG)
							#set ( $selected_CT_ID_PEJABATJKPTG = "selected" )
							#end		
							<option $selected_CT_ID_PEJABATJKPTG value="$ruj.ID_PEJABATJKPTG" >
							$ruj.NAMA_UNIT
							</option>
						#end
					</select>
				</td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >
							
				</td>			
				<td valign="top" >
				Jawatan
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
					<select id="CT_ID_JAWATAN_$internalType"  name="CT_ID_JAWATAN_$internalType" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJJAWATAN )		
							#set ( $selected_CT_ID_JAWATAN = "" )
							#if($CT_ID_JAWATAN==$ruj.ID)
							#set ( $selected_CT_ID_JAWATAN = "selected" )
							#end		
							<option $selected_CT_ID_JAWATAN value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Pendaftaran
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				Dari
				<input name="CT_TARIKHPENDAFTARAN_MULA_$internalType" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_CT_TARIKHPENDAFTARAN_MULA_$internalType')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_CT_TARIKHPENDAFTARAN_MULA_$internalType')" 
				type="text" id="CT_TARIKHPENDAFTARAN_MULA_$internalType" style="text-transform:uppercase;" value="$CT_TARIKHPENDAFTARAN_MULA" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('CT_TARIKHPENDAFTARAN_MULA_$internalType',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      <span id="span_CHECK_CT_TARIKHPENDAFTARAN_MULA_$internalType">
      <input type='hidden' id='CHECK_CT_TARIKHPENDAFTARAN_MULA_$internalType' name='CHECK_CT_TARIKHPENDAFTARAN_MULA_$internalType' value='true' >
      </span>
      &nbsp;&nbsp;
      
       Sehingga
				<input name="CT_TARIKHPENDAFTARAN_AKHIR_$internalType" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_CT_TARIKHPENDAFTARAN_AKHIR_$internalType')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_CT_TARIKHPENDAFTARAN_AKHIR_$internalType')" 
				type="text" id="CT_TARIKHPENDAFTARAN_AKHIR_$internalType" style="text-transform:uppercase;" value="$CT_TARIKHPENDAFTARAN_AKHIR" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('CT_TARIKHPENDAFTARAN_AKHIR_$internalType',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       <span id="span_CHECK_CT_TARIKHPENDAFTARAN_AKHIR_$internalType">
       <input type='hidden' id='CHECK_CT_TARIKHPENDAFTARAN_AKHIR_$internalType' name='CHECK_CT_TARIKHPENDAFTARAN_AKHIR_$internalType' value='true' >
       </span>
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Status Pendaftaran
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<select id="CT_STATUSPENDAFTARAN_$internalType" name="CT_STATUSPENDAFTARAN_$internalType">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_A = "" )
				#set ( $selected_B = "" )
				#set ( $selected_C = "" )
							#if($CT_STATUSPENDAFTARAN=="1")
								#set ( $selected_A = "selected" )
							#elseif($CT_STATUSPENDAFTARAN=="2")
								#set ( $selected_B = "selected" )
							#elseif($CT_STATUSPENDAFTARAN=="3")
								#set ($selected_C = "selected" )
							#end
				<option value="1" $selected_A >PENDAFTARAN BARU</option>
				<option value="2" $selected_B >PERMOHONAN DITOLAK</option>
				<option value="3" $selected_C >PERMOHONAN DITERIMA</option>
				</select>				
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
				<br>
				<input type="button" id="cmdCariPenggunaCT_$internalType" name="cmdCariPenggunaCT_$internalType" value="Cari" 
				onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('Y');if(validateDateRegisterID('$internalType')==true){doDivAjaxCall$formname('div_Pengguna$internalType','showSenaraiPenggunaMemohon','FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));}" 
				>
								
				<input style="display:none" type="button" id="cmdCetakPenggunaCT_$internalType" name="cmdCetakPenggunaCT_$internalType"
				 onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('Y');if(validateDateRegisterID('$internalType')==true){doDivAjaxCall$formname('SenaraiForPrint_$internalType','showSenaraiPenggunaMemohon','FlagCetak=Y&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));}" 
				 value="Cetak Laporan" />
	   			
				<input type="button" id="cmdTutupCariPenggunaCT_$internalType" name="cmdTutupCariPenggunaCT_$internalType" value="Reset" 
				onClick="$jquery('#CT_OPENCLOSE_CARIAN_$internalType').val('close');$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('N');doDivAjaxCall$formname('div_Pengguna$internalType','showSenaraiPenggunaMemohon','FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());" >
				
				
				
				</td>
			</tr>
	</table>
	</fieldset>

<br>
</td>
</tr>
</table>

<script>
if( $jquery('#TABLE_CT_ID_'+'$internalType').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_'+'$internalType').offset().top);
}
</script>

