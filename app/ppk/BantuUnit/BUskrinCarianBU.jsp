

#if($BU_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN_BU').style.display = "";
	document.getElementById('span_LINK_CARIAN_BU').style.display = "none";	
	if( $jquery('#div_CARIAN_BU').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN_BU').offset().top);
	}
</script>
#end
#if($BU_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN_BU').style.display = "none";
	document.getElementById('span_LINK_CARIAN_BU').style.display = "";
</script>
#end



<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" id="TABLE_CARIAN BU" class="classFade">
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
				No. Bantu Unit	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="BU_NO_BANTUUNIT" 
				name="BU_NO_BANTUUNIT" value="$BU_NO_BANTUUNIT" >				
				</td>
			</tr>
			
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
				<input style="text-transform:uppercase;" size="50" type="text" id="BU_NAMA_PEMOHON" 
				name="BU_NAMA_PEMOHON" value="$BU_NAMA_PEMOHON" >				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Pegawai Pelulus	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="BU_NAMA_PELULUS" 
				name="BU_NAMA_PELULUS" value="$BU_NAMA_PELULUS" >				
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Bantuan</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				Dari
				<input name="BU_TARIKH_BU_MULA" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_BU_TARIKH_BU_MULA')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_BU_TARIKH_BU_MULA')" 
				type="text" id="BU_TARIKH_BU_MULA" style="text-transform:uppercase;" value="$BU_TARIKH_BU_MULA" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('BU_TARIKH_BU_MULA',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      <span id="span_CHECK_BU_TARIKH_BU_MULA">
      <input type='hidden' id='CHECK_BU_TARIKH_BU_MULA' name='CHECK_BU_TARIKH_BU_MULA' value='true' >
      </span>
      &nbsp;&nbsp;
      
       Sehingga
				<input name="BU_TARIKH_BU_AKHIR" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_BU_TARIKH_BU_AKHIR')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_BU_TARIKH_BU_AKHIR')" 
				type="text" id="BU_TARIKH_BU_AKHIR" style="text-transform:uppercase;" value="$BU_TARIKH_BU_AKHIR" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('BU_TARIKH_BU_AKHIR',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       <span id="span_CHECK_BU_TARIKH_BU_AKHIR">
       <input type='hidden' id='CHECK_BU_TARIKH_BU_AKHIR' name='CHECK_BU_TARIKH_BU_AKHIR' value='true' >
       </span>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Negeri Bantuan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="BU_ID_NEGERI"  name="BU_ID_NEGERI" 
				onChange = "doDivAjaxCall$formname('td_BU_ID_UNIT','showUnit_BU','');"
				>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $N in $listNegeri)		
							#set ( $selected_N = "" )
							#if($BU_ID_NEGERI==$N.ID_NEGERI)
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
				Unit Bantuan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  id="td_BU_ID_UNIT" >
				
				<select id="BU_ID_UNIT"  name="BU_ID_UNIT">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $U in $listUnit)		
							#set ( $selected_U = "" )
							#if($BU_ID_UNIT==$U.ID_UNIT)
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
				</td>			
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				
				
				<select id="BU_ID_STATUS"  name="BU_ID_STATUS" >	   
					   <option value = "" >SILA PILIH</option>
					   <option #if($BU_ID_STATUS=='1') selected #end value="1" >BARU</option>
					   <option #if($BU_ID_STATUS=='2') selected #end value="2" >LULUS</option>
					   <option #if($BU_ID_STATUS=='3') selected #end value="3" >TOLAK</option>                       
                       <option #if($BU_ID_STATUS=='4') selected #end value="4" >BELUM BERMULA</option>
                       <option #if($BU_ID_STATUS=='5') selected #end value="5" >LUPUT</option>
					  <!--  <option #if($BU_ID_STATUS=='4') selected #end value="4" >SELESAI</option> --> 
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
				<input type="button" id="cmdCariBU" name="cmdCariBU" value="Cari" 
				onClick="document.getElementById('SenaraiLaporanLogForPrint').innerHTML='';doDivAjaxCall$formname('div_SenaraiBU','showSenaraiBU','FLAG_BU_CARIAN=Y&scrolPosition='+getPageLocation('$command'));" 
				>								
				<input type="button" id="cmdTutupBU" name="cmdTutupBU" value="Reset" 
				onClick="doDivAjaxCall$formname('div_SenaraiBU','showSenaraiBU','BU_OPENCLOSE_CARIAN=close&FLAG_BU_CARIAN=N');" >
				
				
				<input style="display:none;" type="button" id="cmdCetakBU" name="cmdCetakBU" value="Cetak" 
				onClick="doDivAjaxCall$formname('SenaraiLaporanLogForPrint','showSenaraiBU_Print','FLAG_BU_CARIAN=Y');" 
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

