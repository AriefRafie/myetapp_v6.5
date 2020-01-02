
<div id="div_LaporanGredPrint" style="display:none">


<tr>
<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
<b>LAPORAN GRED</b>
</td>
</tr>

<table style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%">
<tr>
<td>
<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 

<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Nama Gred</td>
<td   align="left" valign="top">Keterangan</td>
<td   align="left" valign="top">Status</td>
	   
</tr>

#if($listDataGred.size()>0)

	<!--gred = userHQ-->
#foreach($gred in $listDataGred)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">


<td   align="center" valign="top" >$gred.BIL</td>
<td  align="left" valign="top">$gred.NAMA_GRED</td>
<td  align="left" valign="top">$gred.KETERANGAN</td>
<td  align="left" valign="top">$gred.FLAG_STATUS</td>


</tr>
<tr  id="div_viewGred$gred.ID_GRED">
<td align="left" valign="top" colspan="14">
</td>
</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>
	
	#if($PrintlistDataGred.size()>0)
	<script>
	var bttnCetakGred =  document.getElementById('cmdCetakGred');
	//alert(' masuk sini ');
	if (typeof(bttnCetakGred) != 'undefined' && bttnCetakGred != null)
    {
    	bttnCetakGred.style.display = "";
    }
	</script>
	#end

</td>
</tr>
</table>
</div>

<script type="text/javascript">
printHideDiv('div_LaporanGredPrint','','');
</script>

