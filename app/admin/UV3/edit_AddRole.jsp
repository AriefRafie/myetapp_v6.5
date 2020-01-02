<script>
if( $jquery('#'+'div_listaddrole$internalType$USER_ID').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_listaddrole$internalType$USER_ID').offset().top);
}
</script>
<div id="div_listaddrole$internalType$USER_ID" >
<fieldset>
	#if($SuccessMesejRole!="")
	<div class="info">
	$SuccessMesejRole
	</div>
	#end
	
	#set($style_role = "border:1px solid black;")
	#if($listRoleByUserLogin.size()>20)
	#set($style_role = "height:500px;overflow:auto;border:1px solid black;")
	#end
	
	<div style="$style_role">
	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listRoleByUserLogin.size()>0)
	<tr class="table_header" >
		<td align="center" valign="top">
		<input type="checkbox" id="ALL_CHECKLIST_$internalType$USER_ID" name="ALL_CHECKLIST_$internalType$USER_ID" onclick="doCheckAllRole('$internalType','$USER_ID');"/>
		</td>
		<td align="left" valign="top"  colspan="2">
		<b>PILIH KESEMUA PERANAN/ROLE</b>
		</td>
	</tr>
	#end
	
	#foreach($rl in $listRoleByUserLogin)
		#if($rl.LAYER=="1")
		<tr class="table_header" >
		<td align="center" valign="top">
		<input type="hidden" id="TEMP_GROUP_CHECKLIST_$internalType$USER_ID" name="TEMP_GROUP_CHECKLIST_$internalType$USER_ID" value="$rl.KOD" />
		<input type="checkbox" onclick="doCheckAllRoleGroup('$internalType','$USER_ID',this);" id="GROUP_CHECKLIST_$internalType$USER_ID" name="GROUP_CHECKLIST_$internalType$USER_ID" value="$rl.KOD" />
		
		</td>
		<td  colspan="2">
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
		<input #if($rl.TICK=='Y') checked #end type="checkbox" onClick="doCheckUpdateRole('$internalType','$USER_ID');"  name="CHECKLIST_$internalType$USER_ID$rl.KOD" id="CHECKLIST_$internalType$USER_ID$rl.KOD" value="$rl.ID" >
		</td>
		<td align="left" valign="top">$rl.ID</td><td> $rl.KETERANGAN</td>
		</tr>
		#end						
	#end	
	
	</table>
	</div>
	
	
	<table border="0" cellspacing="1" cellpadding="1" width="100%">
	<tr>
	<td>
	<input 
type="button" id="SAVEADDROLE$internalType$USER_ID" 
name="SAVEADDROLE$internalType$USER_ID" 
onClick="doDivAjaxCall$formname('div_listaddrole$internalType$USER_ID','save_AddRole','USER_ID=$USER_ID&internalType=$internalType&USER_LOGIN=$USER_LOGIN');" 
value="Simpan" > 
<input 
type="button" id="BTLADDROLE$internalType$USER_ID" 
name="BTLADDROLE$internalType$USER_ID" 
onClick="doDivAjaxCall$formname('div_listaddrole$internalType$USER_ID','edit_AddRole','USER_ID=$USER_ID&internalType=$internalType&USER_LOGIN=$USER_LOGIN');" 
value="Batal" > 
	<input 
type="button" id="CLSADDROLE$internalType$USER_ID" 
name="CLSADDROLE$internalType$USER_ID" 
onClick="doDivAjaxCall$formname('div_displayaddrole$internalType$USER_ID','showDisplayAddRole','USER_LOGIN=$USER_LOGIN&internalType=$internalType&USER_ID=$USER_ID');" 
value="Tutup" > 
	</td>
	</tr>
	</table>
</fieldset>
</div>

<script>
doCheckUpdateRole('$internalType','$USER_ID');
</script>

