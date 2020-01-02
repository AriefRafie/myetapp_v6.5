<script>
document.getElementById("BU_FLAG_OPEN_"+'$BIL'+'$ID_PERMOHONANBANTUUNIT').value="open";
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
		#foreach($BU in $listLogOT)
		
		<tr  >
			   <td class="$BU.rowCss"  align="center" valign="top" >
			   $BU.BIL
			   </td>			   
			   <td class="$BU.rowCss"  align="left" valign="top">		   
			   $BU.NAMA_AKTIVITI
			   </td>
			   <td class="$BU.rowCss"  align="center" valign="top">		   
			   $BU.TRANSAKSI_TYPE
			   </td>
			   <td class="$BU.rowCss"  align="center" valign="top">		   
			   $BU.MINIT
			   </td>
			    <td class="$BU.rowCss"  align="center" valign="top">		   
			   $BU.HMS
			   </td>
			     
		</tr>
		
		#end
	#else
	<tr >
		   <td  align="left" valign="top" colspan="5" >Tiada Rekod</td>
		     
	</tr>
	#end
	</table>


