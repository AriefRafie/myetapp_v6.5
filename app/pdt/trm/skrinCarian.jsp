

#if($OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN').style.display = "";
	document.getElementById('span_LINK_CARIAN').style.display = "none";	
	if( $jquery('#div_CARIAN').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN').offset().top-10);
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
	<legend>Carian Terperinci</legend>
	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="2">	
	<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			
              <tr  >
				<td valign="top" >
               
                </td>			
				<td valign="top" >
				Jenis Warta
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                <select id="CR_FLAG_JENISWARTA" name="CR_FLAG_JENISWARTA" >	   
					   <option value = "" >Sila Pilih</option>
                       <option value = "W" #if($CR_FLAG_JENISWARTA == 'W') selected="selected" #end >PEWUJUDAN</option>
                       <option value = "B" #if($CR_FLAG_JENISWARTA == 'T') selected="selected" #end >PEMBATALAN</option>
                       <option value = "G" #if($CR_FLAG_JENISWARTA == 'G') selected="selected" #end >PENGGANTIAN</option>						
				</select>
                 
				</td>
			</tr>
            
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No. Warta
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="CR_NO_WARTA" 
				name="CR_NO_WARTA" value="$CR_NO_WARTA" >				
				</td>
			</tr>
			
			 <tr  >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Negeri
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                <select id="CR_ID_NEGERI" name="CR_ID_NEGERI"               
                onChange = "doDivAjaxCall3$formname('td_CR_ID_DAERAH','showDaerahCR','ID_NEGERI='+this.value);"
				>	   
					   <option value = "" >Sila Pilih</option>
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
            
            
            
            
            <tr  >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Daerah
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="td_CR_ID_DAERAH" >
                <select id="CR_ID_DAERAH" name="CR_ID_DAERAH"               
                onChange = "doDivAjaxCall3$formname('td_CR_ID_MUKIM','showMukimCR','ID_DAERAH='+this.value);"
				>	   
					   <option value = "" >Sila Pilih</option>
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
            
            
             <tr  >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Mukim
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="td_CR_ID_MUKIM" >
                <select id="CR_ID_MUKIM" name="CR_ID_MUKIM" >	   
					   <option value = "" >Sila Pilih</option>
						#foreach ( $N in $listMukim)		
							#set ( $selected_N = "" )
							#if($CR_ID_MUKIM==$N.ID_MUKIM)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_MUKIM" >
							$N.NAMA_MUKIM
							</option>							
						#end
					</select>               
				</td>
			</tr>
         
            
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kawasan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="CR_KAWASAN" 
				name="CR_KAWASAN" value="$CR_KAWASAN" >				
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No. Pelan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="CR_NO_PELAN" 
				name="CR_NO_PELAN" value="$CR_NO_PELAN" >				
				</td>
			</tr>
          
           
			
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Warta</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				Dari
				<input name="CR_TARIKH_MULA" 
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_CR_TARIKH_MULA')" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_CR_TARIKH_MULA')" 
				type="text" id="CR_TARIKH_MULA" style="text-transform:uppercase;" value="$CR_TARIKH_MULA" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('CR_TARIKH_MULA',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      <span id="span_CHECK_CR_TARIKH_MULA">
      <input type='hidden' id='CHECK_CR_TARIKH_MULA' name='CHECK_CR_TARIKH_MULA' value='true' >
      </span>
      &nbsp;&nbsp;
      
       Sehingga
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
			
            
            
            <tr  >
				<td valign="top" >
               
                </td>			
				<td valign="top" >
				Status
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                <select id="CR_FLAG_STATUSWARTA" name="CR_FLAG_STATUSWARTA" >	   
					   <option value = "" >Sila Pilih</option>
                       <option value = "K" #if($CR_FLAG_STATUSWARTA == 'K') selected="selected" #end >KUATKUASA</option>
                       <option value = "T" #if($CR_FLAG_STATUSWARTA == 'T') selected="selected" #end >TIDAK KUATKUASA</option>
                       <option value = "X" #if($CR_FLAG_STATUSWARTA == 'X') selected="selected" #end >TIDAK DIKETAHUI</option>	
                       <option value = "G" #if($CR_FLAG_STATUSWARTA == 'G') selected="selected" #end >LUAS TIDAK DIGANTI</option>						
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
				onClick="if(valCarian()==true){document.getElementById('SenaraiLaporanLogForPrint').innerHTML='';doDivAjaxCall$formname('div_Senarai','showSenarai','FLAG_CARIAN=Y&scrolPosition='+getPageLocation('$command'));}" 
				>								
				<input type="button" id="cmdTutup" name="cmdTutup" value="Reset" 
				onClick="doDivAjaxCall$formname('div_Senarai','showSenarai','OPENCLOSE_CARIAN=close&FLAG_CARIAN=N');" >
				
				
				<input style="display:none;" type="button" id="cmdCetak" name="cmdCetak" value="Cetak" 
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
	window.scrollTo(0, $jquery('#TABLE_CT_ID_'+'$internalType').offset().top-10);
}
*/
</script>

