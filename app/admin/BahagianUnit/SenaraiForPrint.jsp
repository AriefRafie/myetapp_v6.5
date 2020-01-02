<table width="98%" align="center"> 
<tr>
<td>

<table width="100%" cellpadding="0" cellspacing="0" style="border-collapse: collapse;" > 
 	
  <tr class="table_header"style="border:1px solid black; font-size:90%;" >
		   <td   align="center" valign="top" style="border:1px solid black; font-size:90%;"><strong>Bil.</strong></td>
	      <td   align="left" valign="top" style="border:1px solid black; font-size:90%;"><strong>Nama Bahagian/Unit</strong></td>
    <!-- <td   align="left" valign="top">Negeri</td> -->
           <td   align="left" valign="top" style="border:1px solid black; font-size:90%;"><strong>Status</strong></td>
          <td   align="left" valign="top" style="border:1px solid black; font-size:90%;"><strong>Catatan</strong></td>
	</tr>
	#if($listPrint.size()>0)
	#foreach($cariBahagianHQ in $listPrint)
		<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT" style="border:1px solid black; font-size:90%;">
			   <td   align="center" valign="top" style="border:1px solid black; font-size:90%;">$cariBahagianHQ.BIL. </td>
			   <td  align="left" valign="top" style="border:1px solid black; font-size:90%;">$cariBahagianHQ.NAMA_SEKSYEN ($cariBahagianHQ.KOD_SEKSYEN)</td>
			  <!--  <td  align="left" valign="top">??</td> -->
			   <td  align="left" valign="top" style="border:1px solid black; font-size:90%;">$cariBahagianHQ.FLAG_AKTIF</td>
			   <td align="left" valign="top" style="border:1px solid black; font-size:90%;">$cariBahagianHQ.CATATAN</td>
		</tr>
		<tr id="div_viewBahagian$cariBahagianHQ.ID_SEKSYEN" >
		<td align="left" valign="top" colspan="14" >		</td>
		</tr>
		#end
	#else
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
	</table>

	#if($listPrint.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetak');
	//alert(' masuk sini ');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
    }
	</script>
	#end

</td>
</tr>
</table>

<script>
$jquery(document).ready(function () {
		printHideDiv('SenaraiForPrint');
		
		var divPrint =  document.getElementById('cmdCetak');
		divPrint.style.display = "none";
		
		var SenaraiForPrint =  document.getElementById('SenaraiForPrint');
		SenaraiForPrint.style.display = "none";
		
	});
</script>