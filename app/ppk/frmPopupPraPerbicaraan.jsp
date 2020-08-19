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


<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr><td></td></tr>
<!-- 	<tr> -->
<!--   		<td><marquee behavior="scroll" direction="left">Sebarang pertanyaan, sila hubungi Pusat Pertanyaan Pusaka di talian  <b>03-88712999</b></marquee></td> -->
<!--   	</tr> -->
	<tr>
    	<td>
    		<fieldset>
    			<legend><strong>Maklumat Notis Pra Perbicaraan</strong></legend>
	    		<table align="left" width="100%" cellspacing="2" cellpadding="1" border="0">
	    		#foreach ($senarai in $senaraiPraBicara)
	    			<tr>
				 		<td>&nbsp;</td>
				  	</tr>
				  	<tr>
				  		<td>Tarikh Pra Perbicaraan</td>
				  		<td> : </td>
				  		<td>$!senarai.tarikh_inkuiri</td>
				  	</tr>
				  	<tr>
					  	<td align="top">Tempat Pra Perbicaraan</td>
					  	<td align="top"> : </td>
					  	<td>
<!-- 						  	$senarai.tempatBicara.toUpperCase() -->
<!-- 						  	<br> -->
						  	$!senarai.alamat_bicara1.toUpperCase()
						  	<br>
						  	$!senarai.alamat_bicara2.toUpperCase()
						  	$!senarai.alamat_bicara3.toUpperCase()
<!-- 						  	<br> -->
<!-- 						  	$senarai.poskod -->
<!-- 						  	$senarai.bandar.toUpperCase() -->
<!-- 						  	<br> -->
<!-- 						  	$senarai.negeri.toUpperCase() -->
					  	</td>
				  	</tr>
				  	<tr>
				  		<td align="top">Sebab</td>
					  	<td align="top"> : </td>
					  	<td>#if ($!senarai.sebab_inkuiri == '1')
					  			PERLANTIKAN SAHABAT/PENJAGA
					  		#elseif ($!senarai.sebab_inkuiri == '2')	
					  			BUKTI KEMATIAN YANG DIRAGUI
					  		#elseif ($!senarai.sebab_inkuiri == '3')	
					  			PERBEZAAN NAMA SIMATI DALAM BUKTI KEMATIAN
					  		#elseif ($!senarai.sebab_inkuiri == '4')	
					  			KEPERLUAN MENDAPATKAN MAKLUMAT TAMBAHAN
					  		#elseif ($!senarai.sebab_inkuiri == '5')		
					  			SEBAB-SEBAB LAIN
					  		#else 
					  		 - TIADA -
					  		 #end
					  	</td>
				  	</tr>
				  	<tr>
				  		<td>Catatan</td>
				  		<td> : </td>
				  		<td> #if($!senarai.catatan_notis != '')
				  				$!senarai.catatan_notis
				  			#else 
				  				- TIADA -
				  				#end
						</td>
				  	</tr>
<!-- 				  	<tr> -->
<!-- 				  		<td>Masa Bicara</td> -->
<!-- 				  		<td> : </td> -->
<!-- 				  		<td>$senarai.masaBicara</td> -->
<!-- 				  	</tr> -->
				  	<tr>
				 		<td>&nbsp;</td>
				  	</tr>
				  #end
				</table>
      		</fieldset>
    	</td>
  	</tr>
  	<tr>
 		<td>&nbsp;</td>
  	</tr>
</table>


<script>


function keluar() {
	window.close();
}

</script>


