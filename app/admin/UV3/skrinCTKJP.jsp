





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
				Kementerian	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="CT_ID_KEMENTERIAN_$internalType"  
					name="CT_ID_KEMENTERIAN_$internalType" 
					onChange = "doDivAjaxCall$formname('div_CT_ID_AGENSI$internalType','showListAgensi_CT','ID_KEMENTERIAN='+$jquery('#CT_ID_KEMENTERIAN_$internalType').val()+'&internalType=$internalType');"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJKEMENTERIAN)		
							#set ( $selected_CT_ID_KEMENTERIAN = "" )
							#if($CT_ID_KEMENTERIAN==$ruj.ID)
							#set ( $selected_CT_ID_KEMENTERIAN = "selected" )
							#end		
							<option $selected_CT_ID_KEMENTERIAN value="$ruj.ID" >
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
				Agensi
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				<div id="div_CT_ID_AGENSI$internalType" >
				<select id="CT_ID_AGENSI_$internalType"  name="CT_ID_AGENSI_$internalType"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJAGENSI)		
							#set ( $selected_CT_ID_AGENSI = "" )
							#if($CT_ID_AGENSI==$ruj.ID)
							#set ( $selected_CT_ID_AGENSI = "selected" )
							#end	
							<option $selected_CT_ID_AGENSI value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>							
						#end
					</select>
				</div>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Tugasan
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
				
					#set($selected_pelulus = "")
					#set($selected_penyemak = "")
					#set($selected_penyedia = "")
					
					#if($CT_ID_TUGASAN=="4")
						#set($selected_pelulus = "selected")
					#elseif($CT_ID_TUGASAN=="9")
						#set($selected_penyemak = "selected")
					#elseif($CT_ID_TUGASAN=="24")
						#set($selected_penyedia = "selected")
					#end
		
		
					<select id="CT_ID_TUGASAN_$internalType"  name="CT_ID_TUGASAN_$internalType" >	   
					   		<option value = "" >SILA PILIH</option>						
							<option $selected_pelulus value="4" >PELULUS</option>
							<option $selected_penyemak value="9" >PENYEMAK</option>
							<option $selected_penyedia value="24" >PENYEDIA</option>					
					</select>
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Keaktifan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="CT_FLAG_AKTIF_$internalType" name="CT_FLAG_AKTIF_$internalType">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_aktif_CT_FLAG_AKTIF = "" )
				#set ( $selected_tidakaktif_CT_FLAG_AKTIF = "" )
							#if($CT_FLAG_AKTIF=="1" || $CT_FLAG_AKTIF=="")
								#set ( $selected_aktif_CT_FLAG_AKTIF = "selected" )
							#else
								#set ( $selected_tidakaktif_CT_FLAG_AKTIF = "selected" )
							#end
				<option value="1" $selected_aktif_CT_FLAG_AKTIF >AKTIF</option>
				<option value="2" $selected_tidakaktif_CT_FLAG_AKTIF >TIDAK AKTIF</option>
				</select>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Log Masuk Terakhir
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				Dari
				<input name="CT_LOGMASUK_MULA_$internalType" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_CT_LOGMASUK_MULA_$internalType')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_CT_LOGMASUK_MULA_$internalType')" 
				type="text" id="CT_LOGMASUK_MULA_$internalType" style="text-transform:uppercase;" value="$CT_LOGMASUK_MULA" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('CT_LOGMASUK_MULA_$internalType',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      <span id="span_CHECK_CT_LOGMASUK_MULA_$internalType">
      <input type='hidden' id='CHECK_CT_LOGMASUK_MULA_$internalType' name='CHECK_CT_LOGMASUK_MULA_$internalType' value='true' >
      </span>
      &nbsp;&nbsp;
      
       Sehingga
				<input name="CT_LOGMASUK_AKHIR_$internalType" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_CT_LOGMASUK_AKHIR_$internalType')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_CT_LOGMASUK_AKHIR_$internalType')" 
				type="text" id="CT_LOGMASUK_AKHIR_$internalType" style="text-transform:uppercase;" value="$CT_LOGMASUK_AKHIR" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('CT_LOGMASUK_AKHIR_$internalType',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       <span id="span_CHECK_CT_LOGMASUK_AKHIR_$internalType">
       <input type='hidden' id='CHECK_CT_LOGMASUK_AKHIR_$internalType' name='CHECK_CT_LOGMASUK_AKHIR_$internalType' value='true' >
       </span>
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Status Log Masuk
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="CT_STATUS_LOG_$internalType" name="CT_STATUS_LOG_$internalType">
				<option value="" >SILA PILIH</option>
				#set ( $selected_senyap_CT_STATUS_LOG = "" )
				#set ( $selected_luput_CT_STATUS_LOG = "" )
							#if($CT_STATUS_LOG=="1")
								#set ( $selected_senyap_CT_STATUS_LOG = "selected" )
							#elseif($CT_STATUS_LOG=="2")
								#set ( $selected_luput_CT_STATUS_LOG = "selected" )
							#end
				<option value="1" $selected_senyap_CT_STATUS_LOG >SENYAP (60 Hari Dari Tarikh Terakhir Log Masuk)</option>
				<option value="2" $selected_luput_CT_STATUS_LOG >KATALALUAN TELAH LUPUT (180 Hari Dari Tarikh Terakhir Menukar Katalaluan)</option>
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
				onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('Y');if(validateDateCetakLaporan('$internalType')==true){doDivAjaxCall$formname('div_Pengguna$internalType','showSenaraiPengguna$internalType','FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));}" 
				>
				
				<input style="display:none" type="button" id="cmdCetakPenggunaCT_$internalType" name="cmdCetakPenggunaCT_$internalType"
				 onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('Y');if(validateDateCetakLaporan('$internalType')==true){doDivAjaxCall$formname('SenaraiForPrint_$internalType','showSenaraiPengguna$internalType','FlagCetak=Y&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));}" 
				value="Cetak Laporan" />
	   			
				<input type="button" id="cmdTutupCariPenggunaCT_$internalType" name="cmdTutupCariPenggunaCT_$internalType" value="Reset" 
				onClick="$jquery('#CT_OPENCLOSE_CARIAN_$internalType').val('close');$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('N');doDivAjaxCall$formname('div_Pengguna$internalType','showSenaraiPengguna$internalType','FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());" >
				
				 <input style="display:none" type="button" id="cmdCetakBorangPenggunaCT_$internalType" name="cmdCetakBorangPenggunaCT_$internalType"
				 onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('Y');if(validateDateCetakLaporan('$internalType')==true){doDivAjaxCall$formname('SenaraiForPrint_$internalType','showSenaraiPengguna$internalType','FlagCetakSemakan$internalType=Y&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));}" 
				 value="Cetak Semakan Pengguna" />
				
				
				</td>
			</tr>
     <tr>
 <td></td>
 <td></td>
	<td></td>
    <td>Laporan Statistik Pengguna KJP <img src="../img/images_stat.png" alt="" width="20" height="20" onclick="statsUser('KJP')" /></td>
</tr>  

<tr>
<td></td>
<td></td><td></td>
<td>
<div id="div_statsKJP" style="display:none">

<table>

<tr>
<td>
<a href="javascript:doCetakStatsSub('div_statsSubKJP','KJP','')"  class="help" title="">
<font color="blue"><li>Statistik Log Masuk Pengguna KJP mengikut Negeri </li></font>
</a>
</td>
</tr> 
 </table>
</div>
</td></tr>           
	</table>
	</fieldset>

<br>
</td>
</tr>
</table>

<div id="div_statsSubKJP">
</div>

<br />

<script>
if( $jquery('#TABLE_CT_ID_'+'$internalType').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_'+'$internalType').offset().top);
}
</script>

