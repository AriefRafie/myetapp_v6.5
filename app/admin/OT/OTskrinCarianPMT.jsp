

#if($PMT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN_PMT').style.display = "";
	document.getElementById('span_LINK_CARIAN_PMT').style.display = "none";	
	if( $jquery('#div_CARIAN_PMT').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN_PMT').offset().top);
	}
</script>
#end
#if($PMT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN_PMT').style.display = "none";
	document.getElementById('span_LINK_CARIAN_PMT').style.display = "";
</script>
#end



<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" id="TABLE_CARIAN PMT" class="classFade">
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
				Nama Aktiviti	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<input style="text-transform:uppercase;" size="50" type="text" id="PMT_NAMA_AKTIVITI" 
				name="PMT_NAMA_AKTIVITI" 
				value="$PMT_NAMA_AKTIVITI" 
				>
				
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
				<input type="button" id="cmdCariPMT" name="cmdCariPMT" value="Cari" 
				onClick="doDivAjaxCall$formname('div_SenaraiPMT','showSenaraiPMT','FLAG_PMT_CARIAN=Y&scrolPosition='+getPageLocation('$command'));" 
				>								
				<input type="button" id="cmdTutupCariPenggunaCT_$internalType" name="cmdTutupCariPenggunaCT_$internalType" value="Reset" 
				onClick="doDivAjaxCall$formname('div_SenaraiPMT','showSenaraiPMT','PMT_OPENCLOSE_CARIAN=close&FLAG_PMT_CARIAN=N');" >
				
				
				
				</td>
			</tr>
	</table>
	</fieldset>

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

