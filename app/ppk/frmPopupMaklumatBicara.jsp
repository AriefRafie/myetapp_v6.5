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


#foreach ($senarai in $senaraibicara)
	
#end

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr><td></td></tr>
<!-- 	<tr> -->
<!--   		<td><marquee behavior="scroll" direction="left">Sebarang pertanyaan, sila hubungi Pusat Pertanyaan Pusaka di talian  <b>03-88712999</b></marquee></td> -->
<!--   	</tr> -->
	<tr>
    	<td>
    		<fieldset>
    			<legend><strong>Maklumat Bicara</strong></legend>
	    		<table align="left" width="100%" cellspacing="2" cellpadding="1" border="0">
	    		#foreach ($senarai in $senaraibicara)
	    			<tr><td colspan="3"><font><b>Permohonan bantahan yang dikemukakan telah dihantar. Tuan/Puan adalah dikehendaki hadir pada Hari Perbicaraan seperti tetapan di bawah. Sekiranya gagal hadir, Tuan/Puan dianggap tidak berminat untuk meneruskan permohonan bantahan.</b></font></td></tr>
				  	<tr>
				 		<td>&nbsp;</td>
				  	</tr>
				  	<tr>
				  		<td>Tarikh Bicara</td>
				  		<td> : </td>
				  		<td>$senarai.tarikhBicara</td>
				  	</tr>
				  	<tr>
				  		<td>Masa Bicara</td>
				  		<td> : </td>
				  		<td>$senarai.masaBicara</td>
				  	</tr>
				  	<tr>
					  	<td align="top">Tempat Bicara</td>
					  	<td align="top"> : </td>
					  	<td>
						  	$senarai.tempatBicara.toUpperCase()
						  	<br>
						  	$senarai.alamatBicara1.toUpperCase()
						  	<br>
						  	$senarai.alamatBicara2.toUpperCase()
						  	$senarai.alamatBicara3.toUpperCase()
						  	<br>
						  	$senarai.poskod
						  	$senarai.bandar.toUpperCase()
						  	<br>
						  	$senarai.negeri.toUpperCase()
					  	</td>
				  	</tr>
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


