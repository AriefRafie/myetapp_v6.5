
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



<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" id="TABLE_CARIAN BU" class="classFade">
<tr  >
<td  width="100%"  valign="top">
	
	
			
	<fieldset>
	<legend>Carian Terperinci</legend>
	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="2">	
	<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"></td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No. PNB	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="CARIAN_NO_PNB" 
				name="CARIAN_NO_PNB" value="$CARIAN_NO_PNB" >				
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No. Fail PPK	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="CARIAN_NO_FAIL" 
				name="CARIAN_NO_FAIL" value="$CARIAN_NO_FAIL" >				
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jenis Borang	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<select id="CARIAN_JENIS_BORANG"  name="CARIAN_JENIS_BORANG">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $JB in $listJenisBorang)		
							#set ( $selected_JB = "" )
							#if($CARIAN_JENIS_BORANG==$JB.JENIS_BORANG)
							#set ( $selected_JB = "selected" )
							#end
							<option $selected_JB value="$JB.JENIS_BORANG" >
							$JB.JENIS_BORANG
							</option>							
						#end
					</select>							
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Borang	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="CARIAN_NAMA_BORANG" 
				name="CARIAN_NAMA_BORANG" value="$CARIAN_NAMA_BORANG" >				
				</td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Hantar Ke PNB</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				Dari
				<input name="CARIAN_TARIKH_MULA" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_CARIAN_TARIKH_MULA')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_CARIAN_TARIKH_MULA')" 
				type="text" id="CARIAN_TARIKH_MULA" style="text-transform:uppercase;" value="$CARIAN_TARIKH_MULA" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('CARIAN_TARIKH_MULA',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      <span id="span_CHECK_CARIAN_TARIKH_MULA">
      <input type='hidden' id='CHECK_CARIAN_TARIKH_MULA' name='CHECK_CARIAN_TARIKH_MULA' value='true' >
      </span>
      &nbsp;&nbsp;
      
       Sehingga
				<input name="CARIAN_TARIKH_AKHIR" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_CARIAN_TARIKH_AKHIR')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_CARIAN_TARIKH_AKHIR')" 
				type="text" id="CARIAN_TARIKH_AKHIR" style="text-transform:uppercase;" value="$CARIAN_TARIKH_AKHIR" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('CARIAN_TARIKH_AKHIR',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       <span id="span_CHECK_CARIAN_TARIKH_AKHIR">
       <input type='hidden' id='CHECK_CARIAN_TARIKH_AKHIR' name='CHECK_CARIAN_TARIKH_AKHIR' value='true' >
       </span>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Negeri Unit PPK
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="CARIAN_NEGERI"  name="CARIAN_NEGERI" 
				onChange = "doDivAjaxCall$formname('td_CARIAN_UNIT','showUnit','');"
				>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $N in $listNegeri)		
							#set ( $selected_N = "" )
							#if($CARIAN_NEGERI==$N.ID_NEGERI)
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
				Unit PPK
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  id="td_CARIAN_UNIT" >
				
				<select id="CARIAN_UNIT"  name="CARIAN_UNIT">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $U in $listUnit)		
							#set ( $selected_U = "" )
							#if($CARIAN_UNIT==$U.ID_UNIT)
							#set ( $selected_U = "selected" )
							#end
							<option $selected_U value="$U.ID_UNIT" >
							$U.NAMA_UNIT														
							</option>							
						#end
					</select>
				
							
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Penerima	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="CARIAN_NAMA_PENERIMA" 
				name="CARIAN_NAMA_PENERIMA" value="$CARIAN_NAMA_PENERIMA" >				
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat Penerima	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="CARIAN_ALAMAT_PENERIMA" 
				name="CARIAN_ALAMAT_PENERIMA" value="$CARIAN_ALAMAT_PENERIMA" >				
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
				
                
               
				
				<select id="CARIAN_STATUS"  name="CARIAN_STATUS" >	   
					   <option #if($CARIAN_STATUS=='') selected #end value = "" >SILA PILIH</option>
					   <option #if($CARIAN_STATUS=='1') selected #end value="1" >HANTAR KE PNB</option>
					   <option #if($CARIAN_STATUS=='2') selected #end value="2" >DITERIMA PNB</option>
					   <option #if($CARIAN_STATUS=='3') selected #end value="3" >DALAM PROSES</option>  
                       <option #if($CARIAN_STATUS=='4') selected #end value="4" >SELESAI</option> 
                       <option #if($CARIAN_STATUS=='5') selected #end value="5" >DIPULANGKAN</option>                       
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
				<input type="button" id="cmdCari" name="cmdCari" value="Cari" 
				onClick="document.getElementById('SenaraiLaporanLogForPrint').innerHTML='';if(valCarian()==true){doDivAjaxCall$formname('div_SenaraiBorang','showSenaraiBorang','FLAG_PNB_CARIAN=Y&scrolPosition='+getPageLocation('$command'));}" 
				>								
				<input type="button" id="cmdTutup" name="cmdTutup" value="Reset" 
				onClick="document.getElementById('div_CARIAN').innerHTML='';doDivAjaxCall$formname('div_SenaraiBorang','showSenaraiBorang','OPENCLOSE_CARIAN=close&FLAG_PNB_CARIAN=N');" >
				
				
				<input style="display:none;" type="button" id="cmdCetak" name="cmdCetak" value="Cetak" 
				onClick="doDivAjaxCall$formname('SenaraiLaporanLogForPrint','showSenaraiBorang_Print','FLAG_PNB_CARIAN=Y');" 
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

