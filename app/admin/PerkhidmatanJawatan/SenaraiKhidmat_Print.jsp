
<div id="div_LaporanKhidmatPrint" style="display:none">

<tr>
<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
<b>LAPORAN KHIDMAT</b>
</td>
</tr>

<table style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%">
<tr>
<td>

<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 
   
<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Nama Khidmat</td>
<td   align="left" valign="top">Keterangan</td>
<td   align="left" valign="top">Skop Gred</td>
</tr>

#if($listDataKhidmat.size()>0)
	
	<!--gred = userHQ-->
#foreach($Khidmat in $listDataKhidmat)
<tr id="div_rowPejabatUrusan$gred.ID_KHIDMAT">
<td   align="center" valign="top" >$Khidmat.BIL</td>
<td  align="left" valign="top">$Khidmat.NAMA_KHIDMAT</td>
<td  align="left" valign="top">$Khidmat.KETERANGAN_KHIDMAT</td>
<td  align="left" valign="top">$Khidmat.SKOP_GRED</td>

</td> 
</tr>
<tr  id="div_viewKhidmat$Khidmat.ID_KHIDMAT">
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


#if($PrintlistDataKhidmat.size()>0)
	<script>
	var bttnCetakKhidmat =  document.getElementById('cmdCetakKhidmat');
	//alert(' masuk sini ');
	if (typeof(bttnCetakKhidmat) != 'undefined' && bttnCetakKhidmat != null)
    {
    	bttnCetakKhidmat.style.display = "";
    }
	</script>
	#end

</td>
</tr>
</table>
</div>

<script type="text/javascript">
printHideDivKhidmat('div_LaporanKhidmatPrint','','');
</script>

