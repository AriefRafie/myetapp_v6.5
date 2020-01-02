#set($ID_PERMOHONANOT = $viewPOT.ID_PERMOHONANOT)
#set($FLAG_CUTI = $viewPOT.FLAG_CUTI)

<table width="100%" border="0" cellspacing="0" cellpadding="0" >
#if($listDates.size()>0)
	#foreach($LD in $listDates)
	<tr onClick="doDivAjaxCall$formname('TD_$LD.BIL$ID_PERMOHONANOT','showListLogOT_POT','FLAG_CUTI=$FLAG_CUTI&BIL=$LD.BIL&ID_PERMOHONANOT=$ID_PERMOHONANOT&TARIKH_OT=$LD.TARIKH');" >
	
	<td width="25%" class="font_date" style="border-bottom: 1px solid #000;" >
	<input type="hidden" id="POT_FLAG_OPEN_$LD.BIL$ID_PERMOHONANOT" name="POT_FLAG_OPEN_$LD.BIL$ID_PERMOHONANOT" value="close" >
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
	
	<td id="TD_$LD.BIL$ID_PERMOHONANOT" colspan="4" style="border-bottom: 1px solid #000;" align="center"></td>
	</tr>
	#end
#else
<tr>
<td >Tiada Rekod</td>
</tr>
#end
</table>