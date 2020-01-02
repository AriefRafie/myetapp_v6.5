

	<table style="border-collapse:collapse;" width="95%" align="center" cellpadding="2" cellspacing="1" > 
	<tr  >
	<th colspan="15" align="center" style="font-size:100%;">SENARAI PERANAN DI DALAM SISTEM MYETAPP<br><br></th>
	</tr>
	#if($listRolePrint.size()>0)
	<tr class="table_header" style="border:1px solid black; font-size:90%;">
		   <td  align="center" valign="top" style="border :1px solid black; font-size:90%"><b>BIL.</b></td>
		   <td   align="center" valign="top" style="border :1px solid black; font-size:90%"><b>PERANAN PENGGUNA</b></td>
		   <td   align="center" valign="top" style="border :1px solid black; font-size:90%"><b>KETERANGAN</b></td>
	</tr>
	
		#foreach($allRole in $listRolePrint)
		<tr style="border:1px solid black; font-size:90%;">
			   <td class="$userAT.rowCss"  align="center" valign="top" style="border :1px solid black; font-size:90%">$allRole.BIL</td>
			   <td class="$userAT.rowCss"  align="left" valign="top" style="border :1px solid black; font-size:90%">$allRole.NAME</td>
			   <td  class="$userAT.rowCss"  align="left" valign="top" style="border :1px solid black; font-size:90%">$allRole.DESCRIPTION</td>
		</tr>
		
		#end
		#else
        
		<tr>
		<td colspan="8" style="display:none" >
		Tiada Rekod		</td>
		</tr>
		#end	
</table>

<script>
$jquery(document).ready(function () {
		printDiv('div_PrintRole');
		
	});
</script>
