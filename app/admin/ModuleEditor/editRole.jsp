
<script>
//alert("div_DisplayRoleModule$MODULE_GROUP$MODULE_ID_TRIM : "+$jquery('#div_DisplayRoleModule$MODULE_GROUP$MODULE_ID_TRIM').length);
	
	if( $jquery('#div_DisplayRoleModule$MODULE_GROUP$MODULE_ID_TRIM').length )         
	{
		window.scrollTo(0, $jquery('#'+'div_DisplayRoleModule$MODULE_GROUP$MODULE_ID_TRIM').offset().top);
	}

</script>
<!--
$MODULE_GROUP
<br>
$MODULE_ID_TRIM
<br>
$MODULE_ID_ASAL
-->



<tr >
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Peranan / Role Terlibat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top">
				
				<div style="height:500px;overflow:auto;border:1px solid black;">
				
	#if($SuccessMesejRole!="")
		<div class="info" id="div_SuccessMesejRole$MODULE_GROUP$MODULE_ID_TRIM">
		$SuccessMesejRole
		</div>
		<script>
		//alert($jquery('div_SuccessMesejModule$MODULE_GROUP$viewModul.MODULE_ID').length);
		$jquery("#div_SuccessMesejRole$MODULE_GROUP$MODULE_ID_TRIM").show().delay(3000).fadeOut();
		</script>
	#end
	
	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	<tr>
	<td width="5%"></td>
	<td width="35%"></td>
	<td width="60%"></td>
	</tr>
	#if($listRoleBySaveModule.size()>0)
	<tr class="table_header" >
		<td align="center" valign="top">
		<input type="checkbox" id="ALL_CHECKLIST_$MODULE_GROUP$MODULE_ID_TRIM" name="ALL_CHECKLIST_$MODULE_GROUP$MODULE_ID_TRIM" onclick="doCheckAllRole('$MODULE_GROUP','$MODULE_ID_TRIM');"/>
		</td>
		<td align="left" valign="top" colspan="2">
		<b>PILIH KESEMUA PERANAN/ROLE</b>
		</td>
		
	</tr>
	#end
	
	#foreach($rl in $listRoleBySaveModule)
		#if($rl.LAYER=="1")
		<tr class="table_header" >
		<td align="center" valign="top">
		<input type="hidden" id="TEMP_GROUP_CHECKLIST_$MODULE_GROUP$MODULE_ID_TRIM" name="TEMP_GROUP_CHECKLIST_$MODULE_GROUP$MODULE_ID_TRIM" value="$rl.KOD" />
		<input type="checkbox" onclick="doCheckAllRoleGroup('$MODULE_GROUP','$MODULE_ID_TRIM',this);" id="GROUP_CHECKLIST_$MODULE_GROUP$MODULE_ID_TRIM" name="GROUP_CHECKLIST_$MODULE_GROUP$MODULE_ID_TRIM" value="$rl.KOD" />
		
		</td>
		<td colspan="2">
		<b>$rl.KOD</b>
		#if($rl.TOTAL>0)
		($rl.TOTAL Peranan/Role Dipilih)
		#end
		</td>
		</tr>
		#end
		#if($rl.LAYER=="2")
		<tr class="row2" >
		<td align="center" valign="top" >
		<input #if($rl.TICK=='Y') checked #end type="checkbox" onClick="doCheckUpdateRole('$MODULE_GROUP','$MODULE_ID_TRIM');"  name="CHECKLIST_$MODULE_GROUP$MODULE_ID_TRIM$rl.KOD" id="CHECKLIST_$MODULE_GROUP$MODULE_ID_TRIM$rl.KOD" value="$rl.ID" >
		</td>
		<td align="left" valign="top">
		$rl.ID
		
		</td>
		<td align="left" valign="top">
		$rl.KETERANGAN
		</td>
		</tr>
		#end						
	#end	
	
	</table>
	</div>
	
	<table border="0" cellspacing="1" cellpadding="1" width="100%">
	<tr>
	<td>
	<input 
type="button" id="SAVEADDROLE$MODULE_GROUP$MODULE_ID_TRIM" 
name="SAVEADDROLE$MODULE_GROUP$MODULE_ID_TRIM" 
onClick="doDivAjaxCall$formname('div_DisplayRoleModule$MODULE_GROUP$MODULE_ID_TRIM','save_AddRole','MODULE_GROUP=$MODULE_GROUP&MODULE_ID_ASAL=$MODULE_ID_ASAL&MODULE_ID_TRIM=$MODULE_ID_TRIM');" 
value="Simpan" > 
<input 
type="button" id="SAVEADDROLE$MODULE_GROUP$MODULE_ID_TRIM" 
name="SAVEADDROLE$MODULE_GROUP$MODULE_ID_TRIM" 
onClick="doDivAjaxCall$formname('div_DisplayRoleModule$MODULE_GROUP$MODULE_ID_TRIM','edit_AddRole','MODULE_GROUP=$MODULE_GROUP&MODULE_ID_ASAL=$MODULE_ID_ASAL&MODULE_ID_TRIM=$MODULE_ID_TRIM');" 
value="Batal" > 
	<input 
type="button" id="SAVEADDROLE$MODULE_GROUP$MODULE_ID_TRIM" 
name="SAVEADDROLE$MODULE_GROUP$MODULE_ID_TRIM" 
onClick="doDivAjaxCall$formname('div_DisplayRoleModule$MODULE_GROUP$MODULE_ID_TRIM','showDisplayRole','MODULE_GROUP=$MODULE_GROUP&MODULE_ID=$MODULE_ID_ASAL&MODULE_ID_TRIM=$MODULE_ID_TRIM');" 
value="Tutup" > 
	</td>
	</tr>
	</table>
			
				</td>
</tr>











	
	
	
	
	


<script>
doCheckUpdateRole('$MODULE_GROUP','$MODULE_ID_TRIM');
</script>

