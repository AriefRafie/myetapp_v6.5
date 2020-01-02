

#if($OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN').style.display = "";
	document.getElementById('span_LINK_CARIAN').style.display = "none";	
	if( $jquery('#div_CARIAN').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN').offset().top);
	}
</script>
#end
#if($OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN').style.display = "none";
	document.getElementById('span_LINK_CARIAN').style.display = "";
</script>
#end



<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" id="TABLE_CARIAN" class="classFade">
<tr  >
<td  width="100%"  valign="top">
	
	
			
	<fieldset>
	<legend>$link_carian_terperinci</legend>
	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="2">	
	<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				$label_no_aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="CR_NO_ADUAN" 
				name="CR_NO_ADUAN" value="$CR_NO_ADUAN" >				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				$label_pengadu	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="CR_NAMA_PENGADU" 
				name="CR_NAMA_PENGADU" value="$CR_NAMA_PENGADU" >				
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				$label_jenis_aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				 <select id="CR_ID_JENISADUAN" name="CR_ID_JENISADUAN" 
				onChange = "doDivAjaxCall3$formname('tdKategoriAduan','showKategoriAduan','ID_JENISADUAN='+this.value);"
				>	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listJenisAduan)		
							#set ( $selected_N = "" )
							#if($CR_ID_JENISADUAN==$N.ID_JENISADUAN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_JENISADUAN" >
							$N.JENIS_ADUAN
							</option>							
						#end
					</select>		
				</td>
			</tr>
            
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				$label_kategori_aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="tdKategoriAduan" >				
				<select id="CR_ID_KATEGORIADUAN" name="CR_ID_KATEGORIADUAN" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listKategoriAduan)		
							#set ( $selected_N = "" )
							#if($CR_ID_KATEGORIADUAN==$N.ID_KATEGORIADUAN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_KATEGORIADUAN" >
							$N.KATEGORIADUAN
							</option>							
						#end
					</select>	
				</td>
			</tr>
            
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				$label_sumber_pengadu	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  >				
				<select id="CR_ID_SUMBERADUAN" name="CR_ID_SUMBERADUAN" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listJenisSumberPengadu)		
							#set ( $selected_N = "" )
							#if($CR_ID_SUMBERADUAN==$N.ID_SUMBERADUAN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_SUMBERADUAN" >
							$N.NAMA_SUMBER
							</option>							
						#end
					</select>	
				</td>
			</tr>
            
           
                    
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				$label_jenis_tindakan_aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"   >				
				<select id="CR_ID_JENISTINDAKAN" name="CR_ID_JENISTINDAKAN"
                onChange = "doDivAjaxCall3$formname('tdBahagian','showBahagianCR','ID_JENISTINDAKAN='+this.value);" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listJenisTindakan)		
							#set ( $selected_N = "" )
							#if($CR_ID_JENISTINDAKAN==$N.ID_KATEGORITINDAKAN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_KATEGORITINDAKAN" >
							$N.KATEGORITINDAKAN
							</option>							
						#end
					</select>	
				</td>
			</tr>
            
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				$label_negeri_bahagian	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  id="tdNegeriBahagian" >				
				<select id="CR_ID_NEGERI_BAHAGIAN" name="CR_ID_NEGERI_BAHAGIAN" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listNegeriBahagian)		
							#set ( $selected_N = "" )
							#if($CR_ID_NEGERI_BAHAGIAN==$N.ID_NEGERI)
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
				</td>			
				<td valign="top" >
				$label_tindakan_bahagian	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  id="tdBahagian"  >				
				<select id="CR_ID_BAHAGIAN" name="CR_ID_BAHAGIAN">	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listBahagian)		
							#set ( $selected_N = "" )
							#if($CR_ID_BAHAGIAN==$N.ID_SEKSYEN)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_SEKSYEN" >
							$N.NAMA_SEKSYEN
							</option>							
						#end
					</select>	
				</td>
			</tr>
            
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				$label_negeri	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  id="tdNegeri" >				
				<select id="CR_ID_NEGERI" name="CR_ID_NEGERI" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listNegeri)		
							#set ( $selected_N = "" )
							#if($CR_ID_NEGERI==$N.ID_NEGERI)
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
				</td>			
				<td valign="top" >
				$label_daerah	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  id="tdDaerah" >				
				<select id="CR_ID_DAERAH" name="CR_ID_DAERAH" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listDaerah)		
							#set ( $selected_N = "" )
							#if($CR_ID_DAERAH==$N.ID_DAERAH)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_DAERAH" >
							$N.NAMA_DAERAH
							</option>							
						#end
					</select>	
				</td>
			</tr>
            
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				$label_status_aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"   >				
				<select id="CR_ID_STATUS" name="CR_ID_STATUS" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listStatus)		
							#set ( $selected_N = "" )
							#if($CR_ID_STATUS==$N.ID_STATUS)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_STATUS" >
							$N.KETERANGAN
							</option>							
						#end
					</select>	
				</td>
			</tr>
           
			
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				$label_tarikh_aduan</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$label_from
				<input name="CR_TARIKH_MULA" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_CR_TARIKH_MULA')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_CR_TARIKH_MULA')" 
				type="text" id="CR_TARIKH_MULA" style="text-transform:uppercase;" value="$CR_TARIKH_MULA" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('CR_TARIKH_MULA',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      <span id="span_CHECK_CR_TARIKH_MULA">
      <input type='hidden' id='CHECK_CR_TARIKH_MULA' name='CHECK_CR_TARIKH_MULA' value='true' >
      </span>
      &nbsp;&nbsp;
      
       $label_to
				<input name="CR_TARIKH_AKHIR" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_CR_TARIKH_AKHIR')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_CR_TARIKH_AKHIR')" 
				type="text" id="CR_TARIKH_AKHIR" style="text-transform:uppercase;" value="$CR_TARIKH_AKHIR" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('CR_TARIKH_AKHIR',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       <span id="span_CHECK_CR_TARIKH_AKHIR">
       <input type='hidden' id='CHECK_CR_TARIKH_AKHIR' name='CHECK_CR_TARIKH_AKHIR' value='true' >
       </span>
				</td>
			</tr>
			
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				$label_keterangan_log
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="CR_DETAILS_ADUAN" 
				name="CR_DETAILS_ADUAN" value="$CR_DETAILS_ADUAN" >				
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
				<input type="button" id="cmdCari" name="cmdCari" value="$button_cari" 
				onClick="if(valCarian()==true){document.getElementById('SenaraiLaporanLogForPrint').innerHTML='';doDivAjaxCall$formname('div_Senarai','showSenarai','FLAG_CARIAN=Y&scrolPosition='+getPageLocation('$command'));}" 
				>								
				<input type="button" id="cmdTutup" name="cmdTutup" value="$button_reset" 
				onClick="doDivAjaxCall$formname('div_Senarai','showSenarai','OPENCLOSE_CARIAN=close&FLAG_CARIAN=N');" >
				
				
				<input style="display:none;" type="button" id="cmdCetak" name="cmdCetak" value="$button_cetak" 
				onClick="doDivAjaxCall$formname('SenaraiLaporanLogForPrint','showSenarai_Print','FLAG_CARIAN=Y');" 
				>	
				
				</td>
			</tr>
	</table>
	</fieldset>
	
	<div id="SenaraiLaporanLogForPrint">	
	</div>

<br>
</td>
</tr>
</table>

<script>
/*
if( $jquery('#TABLE_CT_ID_'+'$internalType').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_'+'$internalType').offset().top);
}
*/
</script>

