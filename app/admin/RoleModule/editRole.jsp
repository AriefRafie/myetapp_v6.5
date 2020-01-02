<br>
<fieldset>
#if($viewRole.ID!="")
<legend>$viewRole.NAME</legend>
#end


<script>

	var element =  document.getElementById('div_rowRole$GROUPUNIK$viewRole.ROLEUNIK');
	//alert('div_rowRole$GROUPUNIK$viewRole.ROLEUNIK'+":::: element : "+element);
	if (typeof(element) != 'undefined' && element != null)
	{
		window.scrollTo(0, $jquery('#div_rowRole$GROUPUNIK$viewRole.ROLEUNIK').offset().top);
	}/*
	else
	{
		var element =  document.getElementById('div_rowRole');
		if (typeof(element) != 'undefined' && element != null)
		{
			window.scrollTo(0, $jquery('#div_rowRole').offset().top);
		}
	}
	*/
	
	
</script>

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
<font color="red" >*</font>	
</td>
<td valign="top" align="left">
Role ID
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">

#set($setfieldtype="")
#if($viewRole.ID!="")
$viewRole.NAME
#set($setfieldtype="hidden")
#end


<input size="50" type="$setfieldtype" id="ROLE_NAME_$GROUPUNIK$viewRole.ROLEUNIK" 
				name="ROLE_NAME_$GROUPUNIK$viewRole.ROLEUNIK" 
				value="$viewRole.NAME" 
				onKeyUp="doDivAjaxCall$formname('div_err_ROLE_NAME_$GROUPUNIK$viewRole.ROLEUNIK','checkDuplicateRole','ROLEUNIK=$viewRole.ROLEUNIK&ID=$viewRole.ID&GROUPUNIK=$GROUPUNIK&CSS_TITLE=$CSS_TITLE&ROLE_NAME='+this.value);"
				>
				<div id="div_err_ROLE_NAME_$GROUPUNIK$viewRole.ROLEUNIK" >
				
				<input type="hidden" id="CHECK_ROLE_NAME_$GROUPUNIK$viewRole.ROLEUNIK" 
				name="CHECK_ROLE_NAME_$GROUPUNIK$viewRole.ROLEUNIK" 
				value="true" >
				
				</div>



</td>
</tr>
<tr>
<td valign="top" align="center">
<font color="red" >*</font>	
</td>
<td valign="top" align="left">
Role Name
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
<input size="50" type="text" style="text-transform:uppercase;" id="DESCRIPTION_$GROUPUNIK$viewRole.ROLEUNIK" 
				name="DESCRIPTION_$GROUPUNIK$viewRole.ROLEUNIK" 
				value="$viewRole.DESCRIPTION" 
				>
</td>
</tr>

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
<textarea  rows="10" style="text-transform:uppercase;width:60%" maxlength="4000" id="ROLE_DETAILS_$GROUPUNIK$viewRole.ROLEUNIK" 
				name="ROLE_DETAILS_$GROUPUNIK$viewRole.ROLEUNIK" >$viewRole.ROLE_DETAILS</textarea>


</td>
</tr>

<tr>
<td valign="top" align="center">
<font color="red" >*</font>	
</td>
<td valign="top" align="left">
Kumpulan
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
<select id="GROUP_$GROUPUNIK$viewRole.ROLEUNIK"  name="GROUP_$GROUPUNIK$viewRole.ROLEUNIK" >	   
					   
					  
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listGroup )		
							#set ( $selected_ruj = "" )
							
							#if($viewRole.ID!="")
								#if($viewRole.CSS_NAME==$ruj.CSS_NAME)
								#set ( $selected_ruj = "selected" )
								#end												
							#end
							
								
							<option $selected_ruj value="$ruj.CSS_NAME" >
							$ruj.TITLE
							</option>
						#end
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
				<input type="button" id="BTNSAVE$GROUPUNIK$viewRole.ROLEUNIK" name="BTNSAVE$GROUPUNIK$viewRole.ROLEUNIK" 
				onClick="if(validateSaveRole('$GROUPUNIK','$viewRole.ROLEUNIK')==true){doDivAjaxCall$formname('div_viewRole$GROUPUNIK$viewRole.ROLEUNIK','viewRole','ROLEUNIK=$viewRole.ROLEUNIK&ID=$viewRole.ID&ROLE_NAME=$viewRole.NAME&CSS_TITLE=$CSS_TITLE&GROUPUNIK=$GROUPUNIK&SAVE=Y&BIL=$BIL&page=$page');}" value="Simpan" > 
	   			<input type="button" id="BTNBTL$GROUPUNIK$viewRole.ROLEUNIK" name="BTNBTL$GROUPUNIK$viewRole.ROLEUNIK" onClick="doDivAjaxCall$formname('div_viewRole$GROUPUNIK$viewRole.ROLEUNIK','editRole','ROLEUNIK=$viewRole.ROLEUNIK&ID=$viewRole.ID&ROLE_NAME=$viewRole.NAME&CSS_TITLE=$CSS_TITLE&GROUPUNIK=$GROUPUNIK&BIL=$BIL&page=$page');" value="Batal" > 
	   			
	   			<!-- 
	   			<input type="button" id="BTNCLOSE$GROUPUNIK$viewRole.ID" name="BTNCLOSE$GROUPUNIK$viewRole.ID" onClick="doDivAjaxCall$formname('div_viewRole$GROUPUNIK$viewRole.ROLEUNIK','closeRole','ROLEUNIK=$viewRole.ROLEUNIK&ID=$viewRole.ID&ROLE_NAME=$viewRole.NAME&CSS_TITLE=$CSS_TITLE&GROUPUNIK=$GROUPUNIK&BIL=$BIL');" value="Tutup" >
	   			-->
	   			<input type="button" id="BTNCLOSE$GROUPUNIK$viewRole.NAME" name="BTNCLOSE$GROUPUNIK$viewRole.NAME"   onClick="doDivAjaxCall$formname('div_Main$BIL','showSenaraiRoleByGroup','&carianTerperinci='+$jquery('#carianTerperinci').val()+'&GROUPUNIK=$GROUPUNIK&CSS_TITLE=$CSS_TITLE&BIL=$BIL&SHOWNEXT=&FLAG_DELETE=&page=$page');" value="Tutup" > 
	   			
	   			</td>
			</tr>
</table>
</fieldset>
<br>