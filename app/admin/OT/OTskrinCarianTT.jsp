	

#if($TT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN_TT').style.display = "";
	document.getElementById('span_LINK_CARIAN_TT').style.display = "none";	
	if( $jquery('#div_CARIAN_TT').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN_TT').offset().top);
	}
</script>
#end
#if($TT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN_TT').style.display = "none";
	document.getElementById('span_LINK_CARIAN_TT').style.display = "";
</script>
#end



<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" id="TABLE_CARIAN TT" class="classFade">
<tr  >
<td  width="100%"  valign="top">
			
	
	<input size="50" type="hidden" id="TT_SEKSYEN" 
				name="TT_SEKSYEN" 
				value="2" 
				>
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
				
				<input style="text-transform:uppercase;" size="50" type="text" id="TT_NAMA_AKTIVITI" 
				name="TT_NAMA_AKTIVITI" 
				value="$TT_NAMA_AKTIVITI" 
				>
				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Table	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<input style="text-transform:uppercase;" size="50" type="text" id="TT_NAMA_TABLE" 
				name="TT_NAMA_TABLE" 
				value="$TT_NAMA_TABLE" 
				>
				
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Field Check	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<input style="text-transform:uppercase;" size="50" type="text" id="TT_NAMA_FIELD" 
				name="TT_NAMA_FIELD" 
				value="$TT_NAMA_FIELD" 
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
				<input type="button" id="cmdCariTT" name="cmdCariTT" value="Cari" 
				onClick="doDivAjaxCall$formname('div_SenaraiTT','showSenaraiTT','FLAG_TT_CARIAN=Y&scrolPosition='+getPageLocation('$command'));" 
				>								
				<input type="button" id="cmdTutupCariPenggunaCT_$internalType" name="cmdTutupCariPenggunaCT_$internalType" value="Reset" 
				onClick="doDivAjaxCall$formname('div_SenaraiTT','showSenaraiTT','TT_OPENCLOSE_CARIAN=close&FLAG_TT_CARIAN=N');" >
				
				
				
				</td>
			</tr>
	</table>
	</fieldset>

<br>
</td>
</tr>
</table>

<script>

if( $jquery('#TABLE_CT_ID_'+'$internalType').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_'+'$internalType').offset().top);
}

</script>

