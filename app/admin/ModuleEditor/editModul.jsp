<br>
<input type="hidden" id="CURRENT_MODULE_ID_$MODULE_GROUP$viewModul.MODULE_ID" 
				name="CURRENT_MODULE_ID_$MODULE_GROUP$viewModul.MODULE_ID" 
				value="$viewModul.MODULE_ID_ASAL" >
<fieldset>
#if($viewModul.MODULE_ID!="")
<legend>$viewModul.MODULE_ID_ASAL</legend>
#end


<script>
	//alert("div_rowModul$MODULE_GROUP$viewModul.MODULE_ID : "+$jquery('#div_rowModul$MODULE_GROUP$viewModul.MODULE_ID').length);
	if( $jquery('#div_rowModul$MODULE_GROUP$viewModul.MODULE_ID').length)         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_rowModul$MODULE_GROUP$viewModul.MODULE_ID').offset().top);
	}
	else
	{
		if( $jquery('#div_rowModul').length)         // use this if you are using id to check
		{
			window.scrollTo(0, $jquery('#div_rowModul').offset().top);
		}
	}
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
ID Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
#set($setfieldtype="")
#if($viewModul.MODULE_ID_ASAL!="")
$viewModul.MODULE_ID_ASAL
#set($setfieldtype="hidden")
#end


<input size="50" type="$setfieldtype" id="MODULE_ID_$MODULE_GROUP$viewModul.MODULE_ID" 
				name="MODULE_ID_$MODULE_GROUP$viewModul.MODULE_ID" 
				value="$viewModul.MODULE_ID_ASAL" 
				onKeyUp="doDivAjaxCall$formname('div_err_MODULE_ID_$MODULE_GROUP$viewModul.MODULE_ID','checkDuplicateModule','MODULE_ID=$viewModul.MODULE_ID&MODULE_GROUP=$MODULE_GROUP');"
				>
				<div id="div_err_MODULE_ID_$MODULE_GROUP$viewModul.MODULE_ID" >
				
				<input type="hidden" id="CHECK_MODULE_ID_$MODULE_GROUP$viewModul.MODULE_ID" 
				name="CHECK_MODULE_ID_$MODULE_GROUP$viewModul.MODULE_ID" 
				value="true" >
				
				</div>
</td>
</tr>
<tr>
<td valign="top" align="center">
<font color="red" >*</font>	
</td>
<td valign="top" align="left">
Nama Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
<input size="50" type="text" id="MODULE_TITLE_$MODULE_GROUP$viewModul.MODULE_ID" 
				name="MODULE_TITLE_$MODULE_GROUP$viewModul.MODULE_ID" 
				value="$viewModul.MODULE_TITLE" 
				>
</td>
</tr>
<tr>
<td valign="top" align="center">
<font color="red" >*</font>	
</td>
<td valign="top" align="left">
Class Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
<input size="50" type="text" id="MODULE_CLASS_$MODULE_GROUP$viewModul.MODULE_ID" 
				name="MODULE_CLASS_$MODULE_GROUP$viewModul.MODULE_ID" 
				value="$viewModul.MODULE_CLASS" 
				>
</td>
</tr>
<tr>
<td valign="top" align="center">
<font color="red" >*</font>	
</td>
<td valign="top" align="left">
Kumpulan Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">

 <datalist id="DROPDOWNGROUP_$MODULE_GROUP$viewModul.MODULE_ID">
 #foreach($lddg in $listDropDownGroup)
 <option value="$lddg.MODULE_GROUP">
 #end
 </datalist> 

<input list="DROPDOWNGROUP_$MODULE_GROUP$viewModul.MODULE_ID" size="50"  type="text" id="MODULE_GROUP_$MODULE_GROUP$viewModul.MODULE_ID" 
				name="MODULE_GROUP_$MODULE_GROUP$viewModul.MODULE_ID" 
				value="$viewModul.MODULE_GROUP" 
				>
</td>
</tr>
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Keterangan Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
<textarea style="width:200px" id="MODULE_DESCRIPTION_$MODULE_GROUP$viewModul.MODULE_ID" 
				name="MODULE_DESCRIPTION_$MODULE_GROUP$viewModul.MODULE_ID" >$viewModul.MODULE_DESCRIPTION</textarea>
</td>
</tr>
<!--penambahan admin 18/1/2017-->
</tr>
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Versi Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
<input size="50" type="text" id="MODULE_VERSION_$MODULE_GROUP$viewModul.MODULE_ID" 
				name="MODULE_VERSION_$MODULE_GROUP$viewModul.MODULE_ID" 
				value="$!viewModul.MODULE_VERSION">
</td>
</tr>
<!--tamat-->


<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
				<input type="button" id="BTNSAVE$MODULE_GROUP$viewModul.MODULE_ID" name="BTNSAVE$MODULE_GROUP$viewModul.MODULE_ID" onClick="saveModule('$MODULE_GROUP','$viewModul.MODULE_ID','$viewModul.MODULE_ID_ASAL')" value="Simpan" > 
	   			<input type="button" id="BTNBTL$MODULE_GROUP$viewModul.MODULE_ID" name="BTNBTL$MODULE_GROUP$viewModul.MODULE_ID" onClick="editModule('$MODULE_GROUP','$viewModul.MODULE_ID','$viewModul.MODULE_ID_ASAL')" value="Batal" > 
	   			<input type="button" id="BTNCLOSE$MODULE_GROUP$viewModul.MODULE_ID" name="BTNCLOSE$MODULE_GROUP$viewModul.MODULE_ID" onClick="closeModule('$MODULE_GROUP','$viewModul.MODULE_ID','$viewModul.MODULE_ID_ASAL')" value="Tutup" >
	   			</td>
			</tr>
</table>
</fieldset>
<br>