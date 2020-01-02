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
				Nama / Username	</td>
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
			
			<!--  
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jawatan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<input size="50" type="text" id="CT_JAWATAN_$internalType" 
				name="CT_JAWATAN_$internalType" 
				value="$CT_JAWATAN" 
				>
				
				</td>
			</tr>
			-->
			
			
			<tr>
				<td valign="top" >	
				</td>			
				<td valign="top" >
				Jenis Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="CT_ID_JENISPEJABAT_$internalType"  name="CT_ID_JENISPEJABAT_$internalType"  
					onChange="doDivAjaxCall$formname('CT_showDETAILBY_JENISPEJABAT_$internalType','selectJenisPejabatINT_CT','ID_JENISPEJABAT='+$jquery('#CT_ID_JENISPEJABAT_$internalType').val()+'&internalType=$internalType');" 
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach( $listPej in $list_TBLINTRUJJENISPEJABAT )
                        <option value="$listPej.ID_JENISPEJABAT">
                        $listPej.JENISPEJABAT
                       </option>
                        #end
					</select>
				</td>
			</tr>
			</table>
			
			<div id="CT_showDETAILBY_JENISPEJABAT_$internalType">
			<!--#if($CT_ID_JENISPEJABAT=="3")-->
			<table width="100%" border="0" cellpadding="0" cellspacing="2">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
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
					onChange="doDivAjaxCall$formname('CT_divListDaerah$internalType','selectDaerahByNegeri_CT','ID_NEGERI='+$jquery('#CT_ID_NEGERI_$internalType').val()+'&internalType=$internalType');" 
					
					 >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJNEGERI )		
							#set ( $selected_ruj = "" )
							#if($CT_ID_NEGERI==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
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
				Daerah
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				
				<div id="CT_divListDaerah$internalType">
					<select id="CT_ID_DAERAH_$internalType"  name="CT_ID_DAERAH_$internalType" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJDAERAH )		
							#set ( $selected_ruj = "" )
							#if($CT_ID_DAERAH==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
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
				Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				<div id="CT_divListPejabat$internalType">
					<select id="CT_ID_PEJABAT_$internalType"  
					name="CT_ID_PEJABAT_$internalType" 
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listPejabat )		
							#set ( $selected_ruj = "" )
							#if($CT_ID_PEJABAT==$ruj.ID_PEJABAT)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID_PEJABAT" >
							$ruj.NAMA_PEJABAT
							</option>
						#end
					</select>
				</div>
				</td>
			</tr>
		</table>
			<!--#end-->
			</div>
			
			<table width="100%" align="center" border="0" cellpadding="0" cellspacing="2">	
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
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
				onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('Y');if(validateDateCetakLaporan('$internalType')==true){doDivAjaxCall$formname('div_PenggunaINT','showSenaraiPenggunaINT','FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));}"
				
				>
				
				<input style="display:none" type="button" id="cmdCetakPenggunaCT_$internalType" name="cmdCetakPenggunaCT_$internalType"
				 onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('Y');if(validateDateCetakLaporan('$internalType')==true){doDivAjaxCall$formname('SenaraiForPrint_INT','showSenaraiPenggunaINT','FlagCetak=Y&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));}"
				 value="Cetak Laporan" />
	   			
				<input type="button" id="cmdTutupCariPenggunaCT_$internalType" name="cmdTutupCariPenggunaCT_$internalType" value="Reset" 
				onClick="$jquery('#CT_OPENCLOSE_CARIAN_$internalType').val('close');$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('N');doDivAjaxCall$formname('div_PenggunaINT','showSenaraiPenggunaINT','FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());" >
				
				 <input style="display:none" type="button" id="cmdCetakBorangPenggunaCT_$internalType" name="cmdCetakBorangPenggunaCT_$internalType"
				 onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN_$internalType').val('Y');if(validateDateCetakLaporan('$internalType')==true){doDivAjaxCall$formname('SenaraiForPrint_$internalType','showSenaraiPenggunaINT','FlagCetakSemakan$internalType=Y&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));}" 
				 value="Cetak Semakan Pengguna" />
				
				</td>
			</tr>
	  <tr>
 <td></td>
 <td></td>
	<td></td>
    <td>Laporan Statistik Pengguna Integrasi <img src="../img/images_stat.png" alt="" width="20" height="20" onclick="statsUser('Integrasi')" /></td>
</tr>  

<tr>
<td></td>
<td></td><td></td>
<td>
<div id="div_statsIntegrasi" style="display:none">

<table>

<tr>
<td>
<a href="javascript:doCetakStatsSub('div_statsSubIntegrasi','Integrasi','')"  class="help" title="">
<font color="blue"><li>Statistik Log Masuk Pengguna Integrasi mengikut Negeri </li></font>
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

<div id="div_statsSubIntegrasi">
</div>

<br />

<script>
if( $jquery('#TABLE_CT_ID_'+'$internalType').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_'+'$internalType').offset().top);
}
</script>

