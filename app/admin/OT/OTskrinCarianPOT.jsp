

#if($POT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN_POT').style.display = "";
	document.getElementById('span_LINK_CARIAN_POT').style.display = "none";	
	if( $jquery('#div_CARIAN_POT').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN_POT').offset().top);
	}
</script>
#end
#if($POT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN_POT').style.display = "none";
	document.getElementById('span_LINK_CARIAN_POT').style.display = "";
</script>
#end



<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" id="TABLE_CARIAN POT" class="classFade">
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
				No. OT	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="POT_NO_OT" 
				name="POT_NO_OT" value="$POT_NO_OT" >				
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
				<input style="text-transform:uppercase;" size="50" type="text" id="POT_NAMA_PEMOHON" 
				name="POT_NAMA_PEMOHON" value="$POT_NAMA_PEMOHON" >				
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
				<input style="text-transform:uppercase;" size="50" type="text" id="POT_NAMA_PELULUS" 
				name="POT_NAMA_PELULUS" value="$POT_NAMA_PELULUS" >				
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh OT
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				Dari
				<input name="POT_TARIKH_OT_MULA" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_POT_TARIKH_OT_MULA')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_POT_TARIKH_OT_MULA')" 
				type="text" id="POT_TARIKH_OT_MULA" style="text-transform:uppercase;" value="$POT_TARIKH_OT_MULA" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('POT_TARIKH_OT_MULA',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      <span id="span_CHECK_POT_TARIKH_OT_MULA">
      <input type='hidden' id='CHECK_POT_TARIKH_OT_MULA' name='CHECK_POT_TARIKH_OT_MULA' value='true' >
      </span>
      &nbsp;&nbsp;
      
       Sehingga
				<input name="POT_TARIKH_OT_AKHIR" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_POT_TARIKH_OT_AKHIR')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_POT_TARIKH_OT_AKHIR')" 
				type="text" id="POT_TARIKH_OT_AKHIR" style="text-transform:uppercase;" value="$POT_TARIKH_OT_AKHIR" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('POT_TARIKH_OT_AKHIR',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       <span id="span_CHECK_POT_TARIKH_OT_AKHIR">
       <input type='hidden' id='CHECK_POT_TARIKH_OT_AKHIR' name='CHECK_POT_TARIKH_OT_AKHIR' value='true' >
       </span>
				</td>
			</tr>
			
			#if($USER_ROLE == "adminppk" && $USER_NEGERI == "16")
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="POT_ID_NEGERI"  name="POT_ID_NEGERI" 
				onChange = "doDivAjaxCall$formname('td_POT_ID_UNIT','showUnit_POT','');"
				>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $N in $listNegeri)		
							#set ( $selected_N = "" )
							#if($POT_ID_NEGERI==$N.ID_NEGERI)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_NEGERI" >
							$N.NAMA_NEGERI
							</option>							
						#end
					</select>
				
							
				</td>
			</tr>
			#else
			<select style="display:none"  id="POT_ID_NEGERI"  name="POT_ID_NEGERI" >	   
					   #foreach ( $N in $listNegeri)		
							#set ( $selected_N = "" )
							#if($USER_NEGERI==$N.ID_NEGERI)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_NEGERI" >
							$N.NAMA_NEGERI
							</option>							
						#end
					</select>				
			#end
			
			
			
			#if($USER_ROLE == "adminppk")
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Unit
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  id="td_POT_ID_UNIT" >
				
				<select id="POT_ID_UNIT"  name="POT_ID_UNIT">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $U in $listUnit)		
							#set ( $selected_U = "" )
							#if($POT_ID_UNIT==$U.ID_UNIT)
							#set ( $selected_U = "selected" )
							#end
							<option $selected_U value="$U.ID_UNIT" >
							#if($U.ID_UNIT=="ALL")
								$U.NAMA_UNIT
							#else
								$U.NAMA_UNIT, $U.NAMA_NEGERI
							#end							
							</option>							
						#end
					</select>
				
							
				</td>
			</tr>
			#else
			<input type="hidden" id="POT_ID_UNIT" name="POT_ID_UNIT" value="$USER_UNIT" >			
			#end
			
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
				
				
				
				<select id="POT_ID_STATUS"  name="POT_ID_STATUS" >	   
					   <option value = "" >SILA PILIH</option>
					   <option #if($POT_ID_STATUS=='1') selected #end value="1" >BARU</option>
					   <option #if($POT_ID_STATUS=='2') selected #end value="2" >LULUS</option>
					   <option #if($POT_ID_STATUS=='3') selected #end value="3" >TOLAK</option>
					  <!--  <option #if($POT_ID_STATUS=='4') selected #end value="4" >SELESAI</option> --> 
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
				<input type="button" id="cmdCariPOT" name="cmdCariPOT" value="Cari" 
				onClick="document.getElementById('SenaraiLaporanLogForPrint').innerHTML='';doDivAjaxCall$formname('div_SenaraiPOT','showSenaraiPOT','FLAG_POT_CARIAN=Y&scrolPosition='+getPageLocation('$command'));" 
				>								
				<input type="button" id="cmdTutupPOT" name="cmdTutupPOT" value="Reset" 
				onClick="doDivAjaxCall$formname('div_SenaraiPOT','showSenaraiPOT','POT_OPENCLOSE_CARIAN=close&FLAG_POT_CARIAN=N');" >
				
				
				<input style="display:none;" type="button" id="cmdCetakPOT" name="cmdCetakPOT" value="Cetak" 
				onClick="doDivAjaxCall$formname('SenaraiLaporanLogForPrint','showSenaraiPOT_Print','FLAG_POT_CARIAN=Y');" 
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

