#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CarianInteg$Type').style.display = "";
	document.getElementById('span_LinkCarianInteg$Type').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$Type').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CarianInteg$Type').style.display = "none";
	document.getElementById('span_LinkCarianInteg$Type').style.display = "";
	//document.getElementById('div_CT_CARIAN_$Type').className = "";
</script>
#end



<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr  >
<td  width="100%"  valign="top">
	
<fieldset>

<legend>Carian Integrasi #if ($Type.equals("1")) Dalaman #elseif ($Type.equals("2")) Luaran #elseif ($Type.equals("3")) Sistem #end
#if ($Type.equals("1"))
#else
<input type="button" id="cmdAddIntegDalaman" name="cmdAddIntegDalaman"   
onClick="doDivAjaxCall$formname('div_AddInteg$Type','addInteg','ID_INTEGRASI=&Type=$Type');" value="Tambah Agensi" > 
#end

</legend>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="2">	
	<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Agensi</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<input size="50" type="text" id="CT_NAMA_$Type" 
				name="CT_NAMA_$Type" 
				value="">
				
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
					<select id="CT_ID_NEGERI_$Type"  name="CT_ID_NEGERI_$Type">	   
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
				Keaktifan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="CT_FLAG_AKTIF_$Type" name="CT_FLAG_AKTIF_$Type">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_aktif_CT_FLAG_AKTIF = "" )
				#set ( $selected_tidakaktif_CT_FLAG_AKTIF = "" )
							#if($CT_FLAG_AKTIF=="1" || $CT_FLAG_AKTIF=="")
								#set ( $selected_aktif_CT_FLAG_AKTIF = "selected" )
							#elseif($CT_FLAG_AKTIF=="2")
								#set ( $selected_tidakaktif_CT_FLAG_AKTIF = "selected" )
							#end
				<option value="1" $selected_aktif_CT_FLAG_AKTIF >AKTIF</option>
				<option value="2" $selected_tidakaktif_CT_FLAG_AKTIF >TIDAK AKTIF</option>
				</select>
				</td>
			</tr>
			#if ($Type.equals("1"))
            #else
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kategori
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="CT_KATEGORI_$Type" name="CT_KATEGORI_$Type">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_govCT_KATEGORI_ = "" )
				#set ( $selected_swastaCT_KATEGORI_ = "" )
							#if($CT_KATEGORI =="1" || $CT_KATEGORI =="")
								#set ( $selected_govCT_KATEGORI_ = "selected" )
							#elseif($CT_KATEGORI =="2")
								#set ( $selected_swastaCT_KATEGORI_ = "selected" )
							#end
				<option value="1" $selected_govCT_KATEGORI_ >KERAJAAN</option>
				<option value="2" $selected_swastaCT_KATEGORI_ >SWASTA</option>
				</select>
				</td>
			</tr>
			#end
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				<br>
				<input type="button" id="cmdCariPenggunaCT_$Type" name="cmdCariPenggunaCT_$Type" value="Cari" 
				onClick= "$jquery('#CT_FLAGTEPERINCI_CARIAN_$Type').val('Y');document.getElementById('cmdCetakIntegrasi_$Type').style.display='';
                doDivAjaxCall$formname('div_ListInteg$Type','showSenaraiInteg','FlagCari=&Type=$Type');" >
								
				<input type="button" id="cmdTutupCariPenggunaCT_$Type" name="cmdTutupCariPenggunaCT_$Type" value="Reset" 
				onClick="$jquery('#CT_OPENCLOSE_CARIAN$Type').val('close');document.getElementById('span_LinkCarianInteg$Type').style.display='';$jquery('#CT_FLAGTEPERINCI_CARIAN$Type').val('N');doDivAjaxCall$formname('div_CarianInteg$Type','closeCarian','');" >
                
<input style="display:none" type="button" id="cmdCetakIntegrasi_$Type" name="cmdCetakIntegrasi_$Type"
onClick="$jquery('#CT_FLAGTEPERINCI_CARIAN$Type').val('Y');
doDivAjaxCall$formname('SenaraiForPrint_$Type','showSenaraiInteg','Type=$Type&FlagCetak=Y&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val()+'&scrolPosition='+getPageLocation('$command'));" 
				 value="Cetak Laporan" />
				
				</td>
			</tr>
	</table>
    
    <div id="div_AddInteg$Type">
    
    </div>
    
    <div id="SenaraiForPrint_$Type"></div>

</fieldset>

<br>
</td>
</tr>
</table>
