<script>
document.getElementById("POT_FLAG_OPEN_"+'$BIL'+'$ID_PERMOHONANOT').value="open";
</script>

<table border="0" cellspacing="1" cellpadding="1" width="98%"  class="classFade"> 
	<tr class="table_header" >
		   <td   align="center" valign="top" width="3%">Bil.</td>
		   <td   align="left" valign="top" width="67%">Nama Aktiviti</td>
		   <td   align="center" valign="top" width="10%">Jenis Transaksi</td>
		   <td   align="center" valign="top" width="10%">Minit</td>
		   <td   align="center" valign="top" width="10%">Waktu Log</td>
	</tr>
	#if($listLogOT.size()>0)	
		#foreach($POT in $listLogOT)
		
		<tr  >
			   <td class="$POT.rowCss"  align="center" valign="top" >
			   $POT.BIL
			   </td>			   
			   <td class="$POT.rowCss"  align="left" valign="top">		   
			   $POT.NAMA_AKTIVITI
			   </td>
			   <td class="$POT.rowCss"  align="center" valign="top">		   
			   $POT.TRANSAKSI_TYPE
			   </td>
			   <td class="$POT.rowCss"  align="center" valign="top">		   
			   $POT.MINIT
			   </td>
			    <td class="$POT.rowCss"  align="center" valign="top">		   
			   $POT.HMS
			   </td>
			     
		</tr>
		
		#end
	#else
	<tr >
		   <td  align="left" valign="top" colspan="5" >Tiada Rekod</td>
		     
	</tr>
	#end
	</table>


