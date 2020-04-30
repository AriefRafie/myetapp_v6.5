<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPT.css" />

<style>
<!-- 
.success_detail {
	color: #4F8A10;
	background-color: #DFF2BF;
	border: 1px solid #4F8A10;
}
 -->
</style>

<body>
<div id ="div_PrintMT">
	<table width="95%" border="0" cellspacing="2" cellpadding="2" align="center" bgcolor="#aad198" >
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
	    <tr border="1" bgcolor=#dff2bf>
	   		<td colspan="3" class="success">
		   		<p><span>Permohonan semakan telah berjaya dihantar.</span></p>
		   		<!-- <p><span>Berikut adalah butiran permohonan semakan.</span></p> -->
	   		</td>
	    </tr>
   		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
	<!--     <tr bgcolor=#dff2bf>
	    	<td class="success_detail">
	    		<table>
		    		<tr>
			    		<td align="right">No. Petisyen</td>
			    		<td>:</td>
			    		<td>$!noPetisyen</td>
		    		</tr>
		    		<tr>
			    		<td align="right">Nama Simati</td>
			    		<td>:</td>
			    		<td>$!namaSimati</td>
		    		</tr>
		    		<tr>
			    		<td align="right">Nama Lain Simati</td>
			    		<td>:</td>
			    		<td>$!namaLainSimati</td>
		    		</tr>
		    		<tr>
			    		<td align="right">MyID Simati</td>
			    		<td>:</td>
			    		<td>$!MyIDSimati</td>
		    		</tr>
		    		<tr>
			    		<td align="right">IC Lama Simati</td>
			    		<td>:</td>
			    		<td>$!ICLamaSimati</td>
		    		</tr>
		    		<tr>
			    		<td align="right">IC Lain Simati</td>
			    		<td>:</td>
			    		<td>$!ICLainSimati</td>
		    		</tr>
		    		<tr>
			    		<td align="right">Tarikh Mati</td>
			    		<td>:</td>
			    		<td>$!tarikhMati</td>
		    		</tr>
		    		<tr>
			    		<td align="right">Nama Pemohon</td>
			    		<td>:</td>
			    		<td>$!namaPemohon</td>
		    		</tr>
		    		<tr>
			    		<td align="right">MyID Pemohon</td>
			    		<td>:</td>
			    		<td>$!MyIDPemohon</td>
		    		</tr>
		    		<tr>
			    		<td align="right">Hubungan Pemohon Dengan Simati</td>
			    		<td>:</td>
			    		<td>$!hubungan</td>
		    		</tr>
		    		<tr>
			    		<td align="right">Tarikh Jana Borang B</td>
			    		<td>:</td>
			    		<td>$!tarikhJanaBorangB</td>
		    		</tr>
		    		<tr>
			    		<td align="right">Tarikh Hantar Borang B</td>
			    		<td>:</td>
			    		<td>$!tarikhHantarBorangB</td>
		    		</tr>
		    		<tr>
			    		<td align="right">Kod Pejabat</td>
			    		<td>:</td>
			    		<td>$!kodPejabat - $!namaPejabat</td>
		    		</tr>
	    		</table>
		   	</td>
	   	</tr> -->
	    <tr>
		    <td colspan="3" align="center">
		    	<input type="button" name="tutup" id="tutup" value="Tutup" onclick="tutupTetingkap()" />
			 	<input type="button" id="cmdCetakRole" name="cmdCetakRole" value="Cetak" onClick="printDiv('div_PrintMT');" >
			</td>
		</tr>
	</table>
</div>
</body>

<script>
function tutupTetingkap() {
	
	window.close();
}

function printDiv(divName) {

    var originalContents = document.body.innerHTML;
  // $jquery("#"+divName+" :button").hide();
   document.getElementById('cmdCetakRole').hide();
   document.getElementById('tutup').hide();
    

    
    var printContents = document.getElementById(divName).innerHTML;

    var header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>BUTIRAN TERPERINCI</b></div><br></header>'
	var footer ="";
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()">'+header+'<div class="page-break" >'+ printContents + '</div>'+footer+'</html>');
    popupWin.document.close(); 
    document.body.innerHTML = originalContents;
    return false;
}


</script>