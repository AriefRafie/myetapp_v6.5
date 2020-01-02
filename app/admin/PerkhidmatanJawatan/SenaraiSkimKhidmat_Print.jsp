<div id="div_LaporanSkimKhidmatPrint" style="display:none">
<tr>
<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
<b>LAPORAN SKIM KHIDMAT</b>
</td>
</tr>


<table style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%">
<tr>
<td>

<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 

   
<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Nama Klasifikasi</td>
<td   align="left" valign="top">Nama Skim Khidmat</td>
<td   align="left" valign="top">Kod Skim</td>
<td   align="left" valign="top">Kumpulan Perkhidmatan</td>
<td   align="left" valign="top">Sumber Maklumat</td>
<td   align="left" valign="top">Tarikh Kemaskini</td>
</tr>

#if($listDataSkimKhidmat.size()>0)
	
#foreach($SkimKhidmat in $listDataSkimKhidmat)
<tr id="div_rowPejabatUrusan$SkimKhidmat.ID_JAWATAN">
<td   align="center" 
valign="top" >$SkimKhidmat.BIL</td>
<td  align="left" 
valign="top">$SkimKhidmat.NAMA_KLASIFIKASI</td>
<td  align="left" 
valign="top">$SkimKhidmat.KETERANGAN</td>
<td  align="left" 
valign="top">$SkimKhidmat.KOD_SKIM</td>
<td  align="left" 
valign="top">$SkimKhidmat.NAMA_KHIDMAT</td>
<!--<td  align="left" 
valign="top">$SkimKhidmat.NAMA_GRED</td>-->
<td  align="left" 
valign="top">$SkimKhidmat.SUMBER_MAKLUMAT</td>
<td  align="left" 
valign="top">$SkimKhidmat.TARIKH_KEMASKINI</td>


</td> 
</tr>
<tr  id="div_viewSkimKhidmat$SkimKhidmat.ID_JAWATAN">
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

	#if($PrintlistDataSkimKhidmat.size()>0)
	<script>
	var bttnCetakSkimKhidmat=  document.getElementById('cmdCetakSkimKhidmat');
	//alert(' masuk sini ');
	if (typeof(bttnCetakSkimKhidmat) != 'undefined' && bttnCetakSkimKhidmat != null)
    {
    	bttnCetakSkimKhidmat.style.display = "";
    }
	</script>
	#end

</td>
</tr>
</table>
</div>

<script type="text/javascript">
printHideDivSkimKhidmat('div_LaporanSkimKhidmatPrint','','');
</script>

