<tr 
class="table_header" 
>

<td  align="left" valign="top" colspan="4" >
<br>
<fieldset class="row1">
#if($viewGroup.CSS_TITLE!="")
<legend>$viewGroup.TITLE</legend>
#end
#if($SuccessMesej!="")
<div class="info" id="div_SuccessMesejGroup$viewGroup.GROUPUNIK" >
		#if($SuccessMesej=="Insert")
		
			Maklumat Kumpulan Role Berjaya Didaftar
		
		#elseif($SuccessMesej=="Update")
		
			Maklumat Kumpulan Berjaya Dikemaskini
		
		#end
	</div>	
	<script >
	//alert($jquery('div_SuccessMesejModule$GROUPUNIK$viewRole.NAME').length);
	$jquery("#div_SuccessMesejGroup$viewGroup.GROUPUNIK").show().delay(3000).fadeOut();
	</script>
#end



<script>
var element =  document.getElementById('div_rowGroup$viewGroup.GROUPUNIK');
if (typeof(element) != 'undefined' && element != null)
{
	window.scrollTo(0, $jquery('#div_rowGroup$viewGroup.GROUPUNIK').offset().top);
}
else
{
	var element =  document.getElementById('div_rowGroup');
	if (typeof(element) != 'undefined' && element != null)
	{
		window.scrollTo(0, $jquery('#div_rowGroup').offset().top);
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
#if($MODE=="EDIT")
<font color="red" >*</font>	
#end
</td>
<td valign="top" align="left" class="underline_td_main_view">
Group Name
</td>
<td valign="top" align="center" class="underline_td_main_view">
:
</td>
<td valign="top" align="left" class="underline_td_main_view">

#if($MODE=="EDIT")
<input size="50" type="text" id="CSS_TITLE_$viewGroup.GROUPUNIK" 
				name="CSS_TITLE_$viewGroup.GROUPUNIK" 
				value="$viewGroup.TITLE" 
				onKeyUp="doDivAjaxCall$formname('div_err_GROUP_$viewGroup.GROUPUNIK','checkDuplicateGroup','GROUPUNIK=$viewGroup.GROUPUNIK&CSS_TITLE=$viewGroup.CSS_TITLE&CSS_TITLE_CONTENT='+this.value);"
				>
				<div id="div_err_GROUP_$viewGroup.GROUPUNIK" >
				
				<input type="hidden" id="CHECK_GROUP_$viewGroup.GROUPUNIK" 
				name="CHECK_GROUP_$viewGroup.GROUPUNIK" 
				value="true" >
				
				</div>
#else
$viewGroup.TITLE
#end


</td>
</tr>
<tr>
<td valign="top" align="center" >
#if($MODE=="EDIT")
<font color="red" >*</font>	
#end
</td>
<td valign="top" align="left" class="underline_td_main_view">
Css Name
</td>
<td valign="top" align="center" class="underline_td_main_view">
:
</td>
<td valign="top" align="left" class="underline_td_main_view">
#if($MODE=="EDIT")
<input size="50" type="text" id="CSS_NAME_$viewGroup.GROUPUNIK" 
				name="CSS_NAME_$viewGroup.GROUPUNIK" 
				value="$viewGroup.CSS_NAME" 
				onKeyUp="doDivAjaxCall$formname('div_err_CSS_$viewGroup.GROUPUNIK','checkDuplicateCSS','GROUPUNIK=$viewGroup.GROUPUNIK&CSS_TITLE=$viewGroup.CSS_TITLE&CSS_FILE_CONTENT='+this.value);"
				>
				<div id="div_err_CSS_$viewGroup.GROUPUNIK" >
				
				<input type="hidden" id="CHECK_CSS_$viewGroup.GROUPUNIK" 
				name="CHECK_CSS_$viewGroup.GROUPUNIK" 
				value="true" >
#else
$viewGroup.CSS_NAME
#end
</td>
</tr>

<tr>
<td valign="top" align="center" >
#if($MODE=="EDIT")

#end
</td>
<td valign="top" align="left" class="underline_td_main_view">
Dokumen
</td>
<td valign="top" align="center" class="underline_td_main_view">
:
</td>
<td valign="top" align="left" class="underline_td_main_view">
#if($MODE=="EDIT")
#if($viewGroup.NAMA_DOC!="")
<a href="javascript:paparDoc('$viewGroup.CSS_TITLE')"><u><font color='blue'>$viewGroup.NAMA_DOC</font></u></a>
<br>
#end
<input size="50" type="file" id="FILE_GROUP_$viewGroup.GROUPUNIK" 
				name="FILE_GROUP_$viewGroup.GROUPUNIK" 
				>
				
#else
<a href="javascript:paparDoc('$viewGroup.CSS_TITLE')"><u>$viewGroup.NAMA_DOC</u></a>
#end
</td>
</tr>


<tr>
<td valign="top" align="center" >
#if($MODE=="EDIT")

#end
</td>
<td valign="top" align="left" class="underline_td_main_view">
Modul
</td>
<td valign="top" align="center" class="underline_td_main_view">
:
</td>
<td valign="top" align="left" class="underline_td_main_view">
#if($MODE=="EDIT")
<select id="MODUL_ID_$viewGroup.GROUPUNIK"  name="MODUL_ID_$viewGroup.GROUPUNIK" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listModul )		
							#set ( $selected_ruj = "" )
							#if($viewGroup.MODUL_ID==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>

#else
$viewGroup.MODUL_ID
#end
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
				#if($MODE=="EDIT")
				<input type="button" id="BTNSAVE$viewGroup.GROUPUNIK" name="BTNSAVE$viewGroup.GROUPUNIK" 
				onClick="if(validateSaveGroupRole('$viewGroup.GROUPUNIK','')==true){saveGroupRole('div_rowGroup$viewGroup.GROUPUNIK','viewGroup','$viewGroup.GROUPUNIK','$viewGroup.CSS_TITLE','$viewGroup.CSS_NAME','$viewGroup.MODUL_ID');}" value="Simpan" > 
	   			<input type="button" id="BTNBTL$viewGroup.GROUPUNIK" name="BTNBTL$viewGroup.GROUPUNIK" onClick="doDivAjaxCall$formname('div_rowGroup$viewGroup.GROUPUNIK','viewGroup','GROUPUNIK=$viewGroup.GROUPUNIK&CSS_TITLE=$viewGroup.CSS_TITLE&MODE=EDIT');" value="Batal" > 
	   			<input type="button" id="BTNCLOSE$viewGroup.GROUPUNIK" name="BTNCLOSE$viewGroup.GROUPUNIK" onClick="doDivAjaxCall$formname('div_senaraiUtama','carianUtama','carianTerperinci='+$jquery('#carianTerperinci').val());" value="Tutup" >
	   			#else
	   			<input type="button" id="KEMASKINI$viewGroup.GROUPUNIK" name="KEMASKINI$viewGroup.GROUPUNIK" onClick="doDivAjaxCall$formname('div_rowGroup$viewGroup.GROUPUNIK','viewGroup','GROUPUNIK=$viewGroup.GROUPUNIK&CSS_TITLE=$viewGroup.CSS_TITLE&MODE=EDIT');" value="Kemaskini" > 
	   			<input type="button" id="BTNCLOSE$viewGroup.GROUPUNIK" name="BTNCLOSE$viewGroup.GROUPUNIK" onClick="doDivAjaxCall$formname('div_senaraiUtama','carianUtama','carianTerperinci='+$jquery('#carianTerperinci').val());" value="Tutup" >
	   			#end
	   			</td>
			</tr>
</table>
</fieldset>
<br>

</tr>