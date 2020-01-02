
<div align="center"><strong>SENARAI LOG PENGGUNA BAGI TAHUN $TAHUN</strong></div>
<br />

<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >   
<tr class="table_header" >
		   <td width="26%"   align="left" valign="top">Nama</td>
	      <td width="25%"   align="left" valign="top">Tarikh Terakhir Log masuk</td>
          <td width="49%"   align="left" valign="top">Aktiviti</td>
	</tr>
	#if($listAktiviti.size()>0)
	#foreach($senaraiAktiviti in $listAktiviti)
    
    <tr>
	   <td width="26%"   align="left" valign="top" class="$senaraiAktiviti.rowCss">$senaraiAktiviti.USER_NAME</td>
	   <td width="25%"  align="left" valign="top" class="$senaraiAktiviti.rowCss">$senaraiAktiviti.LOG_DATE</td>
       <td width="49%"  align="left" valign="top" class="$senaraiAktiviti.rowCss">$senaraiAktiviti.JENIS_AKTIVITI</td>
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