
<fieldset>
<legend>$viewRole.DESCRIPTION</legend>
#if($SuccessMesej!="")
<div class="info" id="div_SuccessMesejModule$GROUPUNIK$viewRole.ROLEUNIK" >
		#if($SuccessMesej=="Insert")
		
			Maklumat Role Berjaya Didaftar
		
		#elseif($SuccessMesej=="Update")
		
			Maklumat Role Berjaya Dikemaskini
		
		#end
	</div>	
	<script >
	//alert($jquery('div_SuccessMesejModule$GROUPUNIK$viewRole.NAME').length);
	$jquery("#div_SuccessMesejModule$GROUPUNIK$viewRole.ROLEUNIK").show().delay(3000).fadeOut();
	</script>
#end
<script>
	//alert("div_rowModul$GROUPUNIK$viewRole.NAME : "+$jquery('#div_rowModul$GROUPUNIK$viewRole.NAME').length);
	var element =  document.getElementById('div_rowRole$GROUPUNIK$viewRole.ROLEUNIK');
	if (typeof(element) != 'undefined' && element != null)
	{
		window.scrollTo(0, $jquery('#div_rowRole$GROUPUNIK$viewRole.ROLEUNIK').offset().top);
	}
	else
	{
		var element =  document.getElementById('div_rowRole$GROUPUNIK');
		if (typeof(element) != 'undefined' && element != null)
		{
			window.scrollTo(0, $jquery('#div_rowRole$GROUPUNIK').offset().top);
		}
	}
	
	
	/*
	else
	{
		if( $jquery('#div_rowRole').length)    
		{
			window.scrollTo(0, $jquery('#div_rowRole').offset().top);
		}
	}*/
</script>
<div id="printableArea_$GROUPUNIK$viewRole.ROLEUNIK">
<table border="0" width="100%" cellspacing="1" cellpadding="1" >
<tr>
<td width="1%" valign="top" align="center">
</td>
<td width="28%" valign="top" align="left">
</td>
<td width="1%" valign="top" align="center">
</td>
<td width="70%" valign="top" align="left">
</td>
</tr>
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Role ID
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewRole.NAME
</td>
</tr>
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Role Name
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewRole.DESCRIPTION
</td>
</tr>
<tr>

#if($viewRole.ROLE_DETAILS!="")
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Role Details
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewRole.ROLE_DETAILS
</td>
</tr>
#end
<tr>

</tr>
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Kumpulan
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewRole.CSS_TITLE
</td>
</tr>

<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Css
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewRole.CSS_NAME
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
				<input type="button" id="BTNEDIT$GROUPUNIK$viewRole.NAME" name="BTNEDIT$GROUPUNIK$viewRole.NAME"   onClick="doDivAjaxCall$formname('div_viewRole$GROUPUNIK$viewRole.ROLEUNIK','editRole','ROLEUNIK=$viewRole.ROLEUNIK&ID=$viewRole.ID&ROLE_NAME=$viewRole.NAME&CSS_TITLE=$CSS_TITLE&GROUPUNIK=$GROUPUNIK&BIL=$BIL&page=$page');" value="Kemaskini" > 
	   			<!-- <input type="button" id="BTNCLOSE$GROUPUNIK$viewRole.NAME" name="BTNCLOSE$GROUPUNIK$viewRole.NAME" onClick="doDivAjaxCall$formname('div_viewRole$GROUPUNIK$viewRole.ROLEUNIK','closeRole','ROLEUNIK=$viewRole.ROLEUNIK&ID=$viewRole.ID&ROLE_NAME=$viewRole.NAME&CSS_TITLE=$CSS_TITLE&GROUPUNIK=$GROUPUNIK');" value="Tutup" >  -->
	   			<input type="button" id="BTNCLOSE$GROUPUNIK$viewRole.NAME" name="BTNCLOSE$GROUPUNIK$viewRole.NAME"   onClick="doDivAjaxCall$formname('div_Main$BIL','showSenaraiRoleByGroup','&carianTerperinci='+$jquery('#carianTerperinci').val()+'&GROUPUNIK=$GROUPUNIK&CSS_TITLE=$CSS_TITLE&BIL=$BIL&SHOWNEXT=&FLAG_DELETE=&page=$page');" value="Tutup" > 
	   			<input type="button" id="BTNPRINT$GROUPUNIK$viewRole.NAME" name="BTNPRINT$GROUPUNIK$viewRole.NAME" onclick="printDiv('printableArea_$GROUPUNIK$viewRole.ROLEUNIK','$GROUPUNIK','$viewRole.ROLEUNIK')" value="Cetak" />
	   			
	   			
	   			<span id="show_cb_Modul_$GROUPUNIK$viewRole.ROLEUNIK" style="display:none" >
	   			<br>
	   			<input type="checkbox" id="CB_Modul_$GROUPUNIK$viewRole.ROLEUNIK" 
	   			name="CB_Modul_$GROUPUNIK$viewRole.ROLEUNIK"   
	   			value="Y" > : Cetakan Termasuk Senarai Modul	   			
	   			</span>
	   			
	   			</td>
			</tr>
			
			
			<tr>

<td valign="top" colspan="4" ID="div_ListModul$GROUPUNIK$viewRole.ROLEUNIK" >
				
<script>   
$jquery(document).ready(function () {
	
	doDivAjaxCall$formname('div_ListModul$GROUPUNIK$viewRole.ROLEUNIK','carianUtamaModul','carianTerperinciModul=&ROLE_NAME=$viewRole.NAME&GROUPUNIK=$GROUPUNIK&ROLEUNIK=$viewRole.ROLEUNIK');			 	  
	
});
</script>
</td>
</tr>
			
</table>


</div>
<BR>
</fieldset>
<br>

