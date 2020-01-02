<div id="div_LaporanKlasifikasiPrint" style="display:none">
<tr>
<td valign="top" colspan="4"  style="border-bottom: 1px solid #000;font-size: 120%;" >
<b>LAPORAN KLASIFIKASI</b>
</td>
</tr>


<table style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%">
<tr>
<td>
<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 


<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Nama Klasifikasi</td>
<td   align="left" valign="top">Keterangan</td>
<td   align="left" valign="top">Status</td>
</tr>

#if($listDataKlasifikasi.size()>0)
	
<!--klasifikasi = userHQ-->
#foreach($klasifikasi in $listDataKlasifikasi)
<tr id="div_rowPejabatUrusan$klasifikasi.ID_klasifikasi">
<td   align="center" valign="top" >$klasifikasi.BIL </td>
<td  align="left" valign="top">$klasifikasi.NAMA_KLASIFIKASI</td>
<td  align="left" valign="top">$klasifikasi.KETERANGAN</td>
<td  align="left" valign="top">
$klasifikasi.FLAG_STATUS
</td>


 
</tr>
<tr  id="div_viewKlasifikasi$klasifikasi.ID_KLASIFIKASI">
<td align="left" valign="top" colspan="14"></td>
</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>


	#if($PrintlistDataKlasifikasi.size()>0)
	<script>
	var bttnCetakKlasifikasi =  document.getElementById('cmdCetakKlasifikasi');
	//alert(' masuk sini ');
	if (typeof(bttnCetakKlasifikasi) != 'undefined' && bttnCetakKlasifikasi != null)
    {
    	bttnCetakKlasifikasi.style.display = "";
    }
	</script>
	#end

</td>
</tr>
</table>
</div>

<script type="text/javascript">
printHideDivKlasifikasi('div_LaporanKlasifikasiPrint','','');
</script>

