<script>
if( $jquery('#'+'div_listaddGred$ID_JAWATAN').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_listaddGred$ID_JAWATAN').offset().top);
}
</script>
<div id="div_listaddGred$ID_JAWATAN" >
<fieldset>
<!--	#if($SuccessMesejRole!="")
	<div class="info">
	$SuccessMesejRole
	</div>
	#end
	
	#set($style_role = "border:1px solid black;")
	#if($listRoleByUsegredogin.size()>20)
	#set($style_role = "height:500px;overflow:auto;border:1px solid black;")
	#end
	-->
	
	
	<div>
	<table border="0" cellspacing="1" cellpadding="1" width="80%" > 
	<input type="hidden" id="ID_JAWATAN$viewSkimKhidmat.ID_JAWATAN" name="ID_JAWATAN$viewSkimKhidmat.ID_JAWATAN" value="$ID_JAWATAN" >
	<input type="hidden" id="ID_KLASIFIKASI$viewSkimKhidmat.ID_JAWATAN" name="ID_KLASIFIKASI$viewSkimKhidmat.ID_JAWATAN" value="$ID_KLASIFIKASI" >

	#if ($listGredByIdPejabat.size()>0)
	<tr class="table_header" >
		<td align="center" valign="top">
		<input type="checkbox" id="ALL_CHECKLIST_$ID_JAWATAN" name="ALL_CHECKLIST_$ID_JAWATAN" onclick="doCheckAllGred('$ID_JAWATAN');"/>
        
		</td>
		<td align="left" valign="top" >
		<b>PILIH KESEMUA GRED</b>
		</td>
	</tr>
	
	
	#end
	
	#foreach($gred in $listGredByIdPejabat)
		
<tr>
<td align="center" valign="top" >
<input type="hidden" id="TEMP_GRED_CHECKLIST$ID_JAWATAN" name="TEMP_GRED_CHECKLIST$ID_JAWATAN" value="$gred.KOD" />

<input #if($gred.TICK=='Y') checked #end type="checkbox" 
onClick="doCheckUpdateGred('$ID_JAWATAN');"  name="CHECKLIST_$ID_JAWATAN" id="CHECKLIST_$ID_JAWATAN" value="$gred.ID" >

</td>
<td><!-- 
<b>$gred.KOD</b>  -->
$gred.KETERANGAN 
<input type="hiiden" id="NAMAGRED$ID_JAWATAN" name="NAMAGRED$ID_JAWATAN" value="$gred.ID" >
</td>
</tr>
				
	#end	
	
	</table>
	</div>
	
	
	<table border="0" cellspacing="1" cellpadding="1" width="100%">
	<tr>
	<td>
	<input 
type="button" id="SAVEADDgred$ID_JAWATAN" 
name="SAVEADDgred$ID_JAWATAN" 
onClick="doDivAjaxCall$formname('div_listaddGred$ID_JAWATAN','saveGred','ID_JAWATAN=$ID_JAWATAN');" 
value="Simpan" >
 
<input type="button" id="BTLADDROLE$internalType$ID_JAWATAN" 
name="BTLADDROLE$internalType$ID_JAWATAN" 
onClick="doDivAjaxCall$formname('div_listaddGred$ID_JAWATAN','editAddGred','ID_JAWATAN=$ID_JAWATAN&ID_GRED=$viewSkimKhidmat.ID_GRED');" 
value="Batal" > 

<input 
type="button" id="CLSADDROLE$ID_JAWATAN" 
name="CLSADDROLE$ID_JAWATAN" 
onClick="doDivAjaxCall$formname('div_displayaddGred$ID_JAWATAN','showDisplayGred','ID_JAWATAN=$ID_JAWATAN&ID_GRED=$viewSkimKhidmat.ID_GRED');"  
value="Tutup" >
 
</td>
</tr>
</table>
</fieldset>
</div>

<script>
doCheckUpdateGred('$ID_JAWATAN');
</script>

