<div align="center"><strong>SENARAI ADUAN BAGI TAHUN $TAHUN</strong></div>
<br />
<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 
	
<!--<tr><td colspan="14" align="right"><img width="20" height="20" src="../img/printer.png" onclick="doCetak('LogMasuk')"></td></tr>-->
<tr class="table_header" >
		   <td width="20%"   align="left" valign="top">Log Aduan</td>
	      <td width="10%"   align="left" valign="top">Tarikh</td>
          <td width="10%"   align="left" valign="top">Jenis</td>
            <td width="10%"   align="left" valign="top">Status</td>
	</tr>
	#if($listAduan.size()>0)
	#foreach($senaraiAduan in $listAduan)
    
    <tr>
	   <td width="20%"   align="left" valign="top" class="$senaraiAduan.rowCss"><!--<a href="javascript:paparAduan()"><font color="BLUE">-->$senaraiAduan.LOG_ADUAN<!--</font></a>--></td>
	   <td width="10%"  align="left" valign="top" class="$senaraiAduan.rowCss">$senaraiAduan.TARIKH_ADUAN</td>
       <td width="10%"  align="left" valign="top" class="$senaraiAduan.rowCss">$senaraiAduan.JENIS_ADUAN</td>
       <td width="10%"  align="left" valign="top" class="$senaraiAduan.rowCss">$senaraiAduan.STATUS</td>
    </tr>
#end
	#else 
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
</table>


<script>
$jquery(document).ready(function () {
		printHideDiv('printStats');
		
		var printStats =  document.getElementById('printStats');
		printStats.style.display = "none";
		
	});
</script>