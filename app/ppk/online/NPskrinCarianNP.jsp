

#if($NP_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN_NP').style.display = "";
	document.getElementById('span_LINK_CARIAN_NP').style.display = "none";	
	if( $jquery('#div_CARIAN_NP').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN_NP').offset().top);
	}
</script>
#end
#if($NP_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN_NP').style.display = "none";
	document.getElementById('span_LINK_CARIAN_NP').style.display = "";
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
				No. Fail	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				<input style="text-transform:uppercase;" size="50" type="text" id="NO_FAIL" 
				name="NO_FAIL" value="$NO_FAIL" >				
				</td>
			</tr>
			
			
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Notis</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				Dari
				<input type="text" name="TARIKH_DARI" id="TARIKH_DARI" value="$TARIKH_DARI" size="9"/>
              	<a href="javascript:displayDatePicker('TARIKH_DARI',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
		      	&nbsp;&nbsp;
		      
		       	Sehingga
				<input type="text" name="TARIKH_HINGGA" id="TARIKH_HINGGA" value="$TARIKH_HINGGA" size="9"/>
              	<a href="javascript:displayDatePicker('TARIKH_HINGGA',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
				</td>
			</tr>
			
			<tr><td colspan=4></td></tr>
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				<input type="button" id="cmdCariNP" name="cmdCariNP" value="Cari" 
				onClick="doDivAjaxCall$formname('div_SenaraiNP','showSenaraiNP','FLAG_NP_CARIAN=Y&scrolPosition='+getPageLocation('$command'));" 
				>								
				<input type="button" id="cmdTutupBU" name="cmdTutupBU" value="Reset" 
				onClick="doDivAjaxCall$formname('div_SenaraiNP','showSenaraiNP','NP_OPENCLOSE_CARIAN=close&FLAG_NP_CARIAN=N');" >
				
				
				<input style="display:none;" type="button" id="cmdCetakBU" name="cmdCetakBU" value="Cetak" 
				onClick="doDivAjaxCall$formname('SenaraiLaporanLogForPrint','showSenaraiNP_Print','FLAG_NP_CARIAN=Y');" 
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

