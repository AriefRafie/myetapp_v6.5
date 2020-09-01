<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>


<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
#parse("css/eTapp_PPK.css")
</style>

<br>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td>
    		<fieldset>
			  <legend>Maklumat Permohonan</legend>
				<table align="left" width="100%" border="0" cellspacing="2" cellpadding="1">
					<tr>
				 		<td>No. Fail</td>
				 		<td>:</td>
				 		<td><b>$!noFail</b></td>
				  	</tr>
				  	<tr>
				 		<td>Nama Simati</td>
				 		<td>:</td>
				 		<td><b>$!namaS</b></td>
				  	</tr>
				  	<tr>
				 		<td>Status Fail</td>
				 		<td>:</td>
				 		<td><b>$!status</b></td>
				  	</tr>
				  	<tr>
				 		<td valign="top">Sebab Batal</td>
				 		<td valign="top">:</td>
				 		<td>
				 			<table align="left" width="100%" border="0" cellspacing="2" cellpadding="1">
				 				<tr>
				 					<font color="red"><b>PERMOHONAN TUAN/PUAN TELAH DIBATALKAN KERANA <br> $!catatan</b></font>
				 				</tr>
				 				#if($!nofailawal != "undefined")
				 				<tr><td colspan="3"><b><u>Maklumat Permohonan Awal</u></b></td></tr>
				 				<tr>
				 					<td>No Fail Awal</td>
				 					<td>:</td>
				 					<td>$!nofailawal</td>
				 				</tr>
				 				<tr>
				 					<td>Pemohon Awal</td>
				 					<td>:</td>
				 					<td>$!namapemohonawal</td>
				 				</tr>
				 				<tr>
				 					<td>Tempat Permohonan Awal</td>
				 					<td>:</td>
				 					<td>
				 					#foreach($listJ in $listMaklumatMahkamahJ)
										#if( $listJ.id_Pejabat == $pejabatawal) 
											#set($listJid_Pejabat = $listJ.id_Pejabat)
											#set($listJnama_pejabat=$listJ.nama_pejabat)
											#set($listJdaerah = $listJ.daerah)
									    #end
								    #end
								    #set($alam = "$listJnama_pejabat, $listJdaerah")
				 					$!alam
				 					</td>
				 				</tr>
				 				#end
				 			</table>
				 		</td>
				  	</tr>
				  	<tr>
				 		<td>&nbsp;</td>
				  	</tr>
				</table>
			</fieldset>
    		
    	</td>
    </tr>
</table>


<script>

function keluar() {
	window.close();
}

</script>


