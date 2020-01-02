#set($ID_PERMOHONANBANTUUNIT = $viewBU.ID_PERMOHONANBANTUUNIT)
#set($FLAG_CUTI = $viewBU.FLAG_CUTI)

<table width="100%" border="0" cellspacing="0" cellpadding="0" >
#if($listDates.size()>0)
	#foreach($LD in $listDates)
	<tr onClick="doDivAjaxCall$formname('TD_$LD.BIL$ID_PERMOHONANBANTUUNIT','showListLogOT_BU','FLAG_CUTI=$FLAG_CUTI&BIL=$LD.BIL&ID_PERMOHONANBANTUUNIT=$ID_PERMOHONANBANTUUNIT&TARIKH_OT=$LD.TARIKH');" >
	
	<td width="25%" class="font_date" style="border-bottom: 1px solid #000;" >
	<input type="hidden" id="BU_FLAG_OPEN_$LD.BIL$ID_PERMOHONANBANTUUNIT" name="BU_FLAG_OPEN_$LD.BIL$ID_PERMOHONANBANTUUNIT" value="close" >
	$LD.TARIKH
	</td>
	<td width="25%" class="font_date" style="border-bottom: 1px solid #000;">
	MINIT SEHARI : $LD.TM_PD
	</td>
	<td width="25%" class="font_date" style="border-bottom: 1px solid #000;">
	MINIT WAKTU BEKERJA : 
	#if($LD.TM_WH < $LD.WH_ALL)
		<font color="red">$LD.TM_WH</font>
	#else
		$LD.TM_WH
	#end
	</td>
	<td width="25%" class="font_date" style="border-bottom: 1px solid #000;">
	MINIT OVERTIME : $LD.TM_OT
	</td>
	</tr>
	<tr>
	
	<td id="TD_$LD.BIL$ID_PERMOHONANBANTUUNIT" colspan="4" style="border-bottom: 1px solid #000;" align="center"></td>
	</tr>
	#end
#else
<tr>
<td >Tiada Rekod</td>
</tr>
#end
</table>