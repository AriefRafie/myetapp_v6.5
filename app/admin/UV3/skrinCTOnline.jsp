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
				Nama / Username / MyID / MyCOID	
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
				Kategori Pengguna			
				</td>
				<td valign="top" >
				:
				</td>
				
				<td valign="top" >
				<select id="CT_ID_KATEGORI_PENGGUNA_$internalType" name="CT_ID_KATEGORI_PENGGUNA_$internalType"
				onChange="onChangeKategoriPengguna(this.value,'tr_CT_HAD_UMUR_$internalType','CT_HAD_UMUR_$internalType')"				
				>
				<option value = "" >SILA PILIH</option>
				#set ( $selected_individu = "" )
				#set ( $selected_syarikat = "" )
							#if($CT_ID_KATEGORI_PENGGUNA=="INDIVIDU")
								#set ( $selected_individu = "selected" )
							#elseif($CT_ID_KATEGORI_PENGGUNA=="SYARIKAT")
								#set ( $selected_syarikat = "selected" )
							#end
				<option value="Individu" $selected_individu >INDIVIDU</option>
				<option value="Syarikat" $selected_syarikat >SYARIKAT</option>
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
			
			
			
			<tr id="tr_CT_HAD_UMUR_$internalType" >
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Had Umur
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="CT_HAD_UMUR_$internalType" name="CT_HAD_UMUR_$internalType">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_HU_A = "" )
				#set ( $selected_HU_B = "" )
				#set ( $selected_HU_C = "" )
				#set ( $selected_HU_D = "" )
				#set ( $selected_HU_E = "" )
				
							#if($CT_HAD_UMUR=="A")
								#set ( $selected_HU_A = "selected" )
							#elseif($CT_HAD_UMUR=="B")
								#set ( $selected_HU_B = "selected" )
							#elseif($CT_HAD_UMUR=="C")
								#set ( $selected_HU_C = "selected" )
							#elseif($CT_HAD_UMUR=="D")
								#set ( $selected_HU_D = "selected" )
							#elseif($CT_HAD_UMUR=="E")
								#set ( $selected_HU_E = "selected" )
							#end
				<option value="A" $selected_HU_A > 18 Kebawah</option>
				<option value="B" $selected_HU_B > 19 - 30</option>
				<option value="C" $selected_HU_C >31 - 40</option>
				<option value="D" $selected_HU_D >41 - 50</option>
				<option value="E" $selected_HU_E >51 Keatas</option>
				
				</select>
				</td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Peranan MyeTaPP
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				<div id="div_CT_ROLE_MAIN_$internalType" >
					<select id="CT_ROLE_MAIN_$internalType"  name="CT_ROLE_MAIN_$internalType" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listRole )		
							#set ( $selected_CT_ROLE_MAIN = "" )
							#if($CT_ROLE_MAIN == $ruj.ID && $ruj.ID !="")
							#set ( $selected_CT_ROLE_MAIN = "selected" )
							#end		
							<option $selected_CT_ROLE_MAIN value="$ruj.ID" >
							#if($ruj.ID == "" && $ruj.KOD!="")
								--------- $ruj.KOD --------- 
							#else
								$ruj.KOD - $ruj.KETERANGAN
							#end
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
				onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('Y');if(validateDateCetakLaporan('$internalType')==true){doDivAjaxCall$formname('div_PenggunaOnline','showSenaraiPenggunaOnline','FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));}"
				>
				
				<input style="display:none" type="button" id="cmdCetakPenggunaCT_$internalType" name="cmdCetakPenggunaCT_$internalType"
				 onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('Y');if(validateDateCetakLaporan('$internalType')==true){doDivAjaxCall$formname('SenaraiForPrint_Online','showSenaraiPenggunaOnline','FlagCetak=Y&FlagCari=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));}"
				 value="Cetak Laporan" />
	   			
				<input type="button" id="cmdTutupCariPenggunaCT_$internalType" name="cmdTutupCariPenggunaCT_$internalType" value="Reset" 
				onClick="$jquery('#CT_OPENCLOSE_CARIAN_$internalType').val('close');$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('N');doDivAjaxCall$formname('div_PenggunaOnline','showSenaraiPenggunaOnline','FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());" >
				
				<input style="display:none" type="button" id="cmdCetakBorangPenggunaCT_$internalType" name="cmdCetakBorangPenggunaCT_$internalType"
				 onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('Y');if(validateDateCetakLaporan('$internalType')==true){doDivAjaxCall$formname('SenaraiForPrint_Online','showSenaraiPenggunaOnline','FlagCetakSemakanOnline=Y&FlagCari=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));}" 
				 value="Cetak Semakan Pengguna" />
				
				</td>
			</tr>
            
            <tr>
 <td></td>
 <td></td>
	<td></td>
    <td>Laporan Statistik JKPTG Online <img src="../img/images_stat.png" alt="" width="20" height="20" onclick="statsUser('Online')" /></td>
</tr>

 <tr>
<td></td>
<td></td><td></td>
<td>
<div id="div_statsOnline" style="display:none">

<table>

<tr>
<td>
<a href="javascript:doCetakStatsSub('div_statsSubOnline','OnlineNegeri')"  class="help" title="">
<font color="blue"><li>Statistik Penggunaan JKPTG Online mengikut Negeri </li></font>
</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:doCetakStatsSub('div_statsSubOnline','Online')" class="help" title="">
<font color="blue"><li>Statistik Penggunaan JKPTG Online mengikut Kategori Umur</li></font>
</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:doCetakStatsSub('div_statsSubOnline','OnlineKateg')"  class="help" title="">
<font color="blue"><li>Statistik Penggunaan JKPTG Online mengikut Kategori Pengguna </li></font>
</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:doCetakStatsSub('div_statsSubOnline','OnlineHari')"  class="help" title="">
<font color="blue"><li>Statistik Penggunaan JKPTG Online mengikut Hari Bekerja </li></font>
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

<div id="div_statsSubOnline">
</div>

<br />

<script>
onChangeKategoriPengguna('$CT_ID_KATEGORI_PENGGUNA','tr_CT_HAD_UMUR_$internalType','CT_HAD_UMUR_$internalType');
if( $jquery('#TABLE_CT_ID_'+'$internalType').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_'+'$internalType').offset().top);
}

</script>


