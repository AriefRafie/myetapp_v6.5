<script>
if( $jquery('#'+'div_listaddDaerahJagaan$ID_PEJABAT').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_listaddDaerahJagaan$ID_PEJABAT').offset().top);
}
</script>
<div id="div_listaddDaerahJagaan$ID_PEJABAT" >
<fieldset>
<!--	#if($SuccessMesejRole!="")
	<div class="info">
	$SuccessMesejRole
	</div>
	#end
	
	#set($style_role = "border:1px solid black;")
	#if($listRoleByUsedjogin.size()>20)
	#set($style_role = "height:500px;overflow:auto;border:1px solid black;")
	#end
	-->
	
	
	<div style="$style_DaerahJagaan">
	<table border="0" cellspacing="1" cellpadding="1" width="80%" > 
	<input type="hidden" id="ID_PEJABAT$viewPejabat.ID_PEJABAT" name="ID_PEJABAT$viewPejabat.ID_PEJABAT" value="$ID_PEJABAT" >
	<input type="hidden" id="JENIS_PEJABAT$viewPejabat.ID_PEJABAT" name="JENIS_PEJABAT$viewPejabat.ID_PEJABAT" value="$viewPejabat.ID_JENISPEJABAT" >
	
	<input type="hidden" id="ID_NEGERI$viewPejabat.ID_PEJABAT" name="ID_NEGERI$viewPejabat.ID_PEJABAT" value="$viewPejabat.ID_NEGERI" >
	<input type="hidden" id="ID_DAERAH$viewPejabat.ID_PEJABAT" name="ID_DAERAH$viewPejabat.ID_PEJABAT" value="$viewPejabat.ID_DAERAH" >
	
	<!-- <input type="hidden" id="ID_JENISPEJABATURUS$viewPejabat.ID_PEJABAT" name="ID_JENISPEJABATURUS$viewPejabat.ID_PEJABAT" value="$viewPejabatUrusan.ID_JENISPEJABAT" >
	 --><input type="hidden" id="ID_SEKSYEN$viewPejabat.ID_PEJABAT" name="ID_SEKSYEN$viewPejabat.ID_PEJABAT" value="$viewPejabat.ID_SEKSYEN" >
	
	#if($listDaerahJagaanByIdPejabat.size()>0)
	<tr class="table_header" >
		<td align="center" valign="top">
		<input type="checkbox" id="ALL_CHECKLIST_$ID_PEJABAT" name="ALL_CHECKLIST_$ID_PEJABAT" onclick="doCheckAllDaerahJagaan('$ID_PEJABAT');"/>
		</td>
		<td align="left" valign="top" >
		<b>PILIH KESEMUA DAERAH JAGAAN</b>
		</td>
	</tr>
	
	
	#end

	#foreach($dj in $listDaerahJagaanByIdPejabat)
		#if($dj.LAYER=="1")
		<tr class="table_header" >
		<td align="center" valign="top">
		<input type="hidden" id="TEMP_GROUP_CHECKLIST_$ID_PEJABAT" name="TEMP_GROUP_CHECKLIST_$ID_PEJABAT" value="$dj.KOD" />
		<!--<input type="checkbox" onclick="doCheckAllRoleGroup('$ID_PEJABAT',this);" id="GROUP_CHECKLIST_$ID_PEJABAT" name="GROUP_CHECKLIST_$ID_PEJABAT" value="$dj.KOD" />-->
		
		</td>
		<td  colspan="2">
		<b>$dj.LAYER_1</b>
        </td>
		</tr>
		#end
        	
        #if($dj.LAYER=="2")  
       <tr>
		<td align="center" valign="top" >
		<input type="hidden" id="TEMP_DAERAH_CHECKLIST$ID_PEJABAT" name="TEMP_DAERAH_CHECKLIST$ID_PEJABAT" value="$dj.KOD_DAERAH" />
		
		<input #if($dj.TICK=='Y') checked #end type="checkbox" 
		onClick="doCheckUpdateRole('$ID_PEJABAT');"  name="CHECKLIST_$ID_PEJABAT" id="CHECKLIST_$ID_PEJABAT" value="$dj.ID_PEJABATURUS/$dj.ID_DAERAH" >
		
		</td>
		<td><!-- 
		<b>$dj.KOD</b>  -->$dj.LAYER_2 
		<input type="hidden" id="ID_PEJABATURUS$ID_PEJABAT" name="ID_PEJABATURUS$ID_PEJABAT" value="$dj.ID_PEJABATURUS" ></td>
		</tr>
		#end	
	#end
	</table>
	</div>
	
	
	<table border="0" cellspacing="1" cellpadding="1" width="100%">
	<tr>
	<td>
	<input 
type="button" id="SAVEADDDJ$ID_PEJABAT" 
name="SAVEADDDJ$ID_PEJABAT" 
onClick="doDivAjaxCall$formname('div_listaddDaerahJagaan$ID_PEJABAT','saveDaerahJagaan','ID_PEJABAT=$ID_PEJABAT');" 
value="Simpan" > 
<input 
type="button" id="BTLADDROLE$internalType$ID_PEJABAT" 
name="BTLADDROLE$internalType$ID_PEJABAT" 
onClick="doDivAjaxCall$formname('div_listaddDaerahJagaan$ID_PEJABAT','editAddDaerahJagaan','ID_PEJABAT=$ID_PEJABAT&ID_NEGERI=$viewPejabat.ID_NEGERI&JENISPEJ=$viewPejabat.JENIS_PEJ');" 
value="Batal" > 
	<input 
type="button" id="CLSADDROLE$ID_PEJABAT" 
name="CLSADDROLE$ID_PEJABAT" 
onClick="doDivAjaxCall$formname('div_displayaddDaerahJagaan$ID_PEJABAT','showDisplayDaerahJagaan','ID_PEJABAT=$ID_PEJABAT&ID_NEGERI=$viewPejabat.ID_NEGERI&JENISPEJ=$viewPejabat.JENIS_PEJ');"  
value="Tutup" > 
	</td>
	</tr>
	</table>
</fieldset>
</div>

<script>
doCheckUpdateRole('$ID_PEJABAT');
</script>

