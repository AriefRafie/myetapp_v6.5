<br>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
	<td>
	
		
		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		
		<td width="58%" class="underline_td_main">
		<strong>SENARAI NEGERI </strong>
		
		<!--open CT 
		<div  id="span_LINK_CT_CARIAN_HQ">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_HQ','showCarianBahagian','TYPE=HQ');">
		<font color="white"><u>Carian >></u></font></a>
		</div>
		close CT 
		-->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_HQ"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<!--<a href="javascript:document.getElementById('div_TambahNegeri').style.display=''; doDivAjaxCall$formname('div_TambahNegeri','editNegeri','ID_NEGERI=');">
		<img title="Tambah Bahagian" src="../img/plus3.gif" border="0"></a>-->
		</td>
		</tr>
		</table>
		<br>
		
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">		
		<tr>
		<td colspan="4" valign="top" id="div_CT_CARIAN_HQ" >
		</td>
		</tr>	
<tr>
<td colspan="4" id="div_TambahNegeri">		
<table width="100%"  style="display:none">
<tr >
</tr>
</table>
</td>
</tr>			
		<tr>
        <div id="SenaraiNegeri">
		#if($FlagCari=="Y")
		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('SenaraiNegeri','showSenaraiNegeri','cetakReport=$cetakReport&FlagCetak=&carianUmum='+$jquery('#carianUmum').val());			 	  
		  });
		
		</script>
		#end
        </div>
		</tr>			
		</table>
		<!-- close CT -->
		<br>
		
	

	</td>
	</tr>
	
    
    	<tr>
	<td>
	
		
		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		
		<td width="58%" class="underline_td_main">
		<strong>SENARAI DAERAH #if ($NAMA_NEGERI != "") BAGI $NAMA_NEGERI #end
		<div id="namaNegeri"></div></strong>
		
		<!--open CT 
		<div  id="span_LINK_CT_CARIAN_HQ">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_HQ','showCarianBahagian','TYPE=HQ');">
		<font color="white"><u>Carian >></u></font></a>
		</div>
		close CT 
		-->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_Daerah"></span>
		</td>
		<td width="2%" class="underline_td_main">
        <a href="javascript:document.getElementById('div_viewDaerah').style.display=''; doDivAjaxCall$formname('div_viewDaerah','editDaerah','ID_NEGERI=$ID_NEGERI&ID_DAERAH=');"><img title="Tambah Daerah" src="../img/plus3.gif" border="0" align="right"></a>
		</td>
		</tr>
		</table>
		<br>
		
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">		
		<tr>
		<td colspan="4" valign="top" id="div_CT_CARIAN_DAERAH" >
		</td>
		</tr>	
<tr>
<td colspan="4" id="div_viewDaerah">		
<table width="100%"  style="display:none">
<tr >
</tr>
</table>
</td>
</tr>			
		<tr>
        <div id="SenaraiDaerah">
		#if($FlagCari=="Y")
		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('SenaraiDaerah','showSenaraiDaerah','cetakReport=$cetakReport&FlagCetak=&ID_NEGERI=&NAMA_DAERAH=&NAMA_MUKIM=&carianUmum='+$jquery('#carianUmum').val());			 	  
		  });
		
		</script>
		#end
        </div>
		</tr>			
		</table>
		<!-- close CT -->
		<br>
		
	

	</td>
	</tr>
    
    
    <tr>
	<td>
	
		
		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		
		<td width="58%" class="underline_td_main">
		<strong>SENARAI MUKIM #if ($NAMA_DAERAH != "") BAGI $NAMA_DAERAH #end </strong>
		
		<!--open CT 
		<div  id="span_LINK_CT_CARIAN_HQ">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_HQ','showCarianBahagian','TYPE=HQ');">
		<font color="white"><u>Carian >></u></font></a>
		</div>
		close CT 
		-->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_Daerah"></span>
		</td>
		<td width="2%" class="underline_td_main">
        <a href="javascript:document.getElementById('div_viewDaerah').style.display=''; doDivAjaxCall$formname('div_viewMukim$ID_DAERAH','editMukim','ID_NEGERI=&ID_DAERAH=&ID_MUKIM=');"><img title="Tambah Daerah" src="../img/plus3.gif" border="0" align="right"></a>
		</td>
		</tr>
		</table>
		<br>
		
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">		
		<tr>
		<td colspan="4" valign="top" id="div_CT_CARIAN_DAERAH" >
		</td>
		</tr>	
<tr>
<td colspan="4" id="div_viewMukim$ID_DAERAH">		
<table width="100%"  style="display:none">
<tr >
</tr>
</table>
</td>
</tr>			
		<tr>
        <div id="SenaraiMukim">
        #if($FlagCari=="Y")
		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('SenaraiMukim','showSenaraiMukim','cetakReport=$cetakReport&FlagCetak=&ID_NEGERI=&NAMA_DAERAH=&NAMA_MUKIM=&carianUmum='+$jquery('#carianUmum').val());			 	  
		  });
		
		</script>
		#end
		</div>
		</tr>			
		</table>
		<!-- close CT -->
		<br>
		
	

	</td>
	</tr>
    
    <tr>
	<td>
	
		
		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		
		<td width="58%" class="underline_td_main">
		<strong>SENARAI SEKSYEN #if ($NAMA_MUKIM != "") BAGI $NAMA_MUKIM #end </strong>
		
		<!--open CT 
		<div  id="span_LINK_CT_CARIAN_HQ">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_HQ','showCarianBahagian','TYPE=HQ');">
		<font color="white"><u>Carian >></u></font></a>
		</div>
		close CT 
		-->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_Daerah"></span>
		</td>
		<td width="2%" class="underline_td_main">
        <a href="javascript:document.getElementById('div_viewMukim').style.display=''; doDivAjaxCall$formname('div_viewSeksyen$ID_MUKIM','editSeksyen','ID_NEGERI=&ID_DAERAH=&ID_MUKIM=&ID_SEKSYEN=');"><img title="Tambah Daerah" src="../img/plus3.gif" border="0" align="right"></a>
		</td>
		</tr>
		</table>
		<br>
		
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">		
		<tr>
		<td colspan="4" valign="top" id="div_CT_CARIAN_DAERAH" >
		</td>
		</tr>	
<tr>
<td colspan="4" id="div_viewSeksyen">		
<table width="100%"  style="display:none">
<tr >
</tr>
</table>
</td>
</tr>			
		<tr>
        <div id="SenaraiSeksyen">
        #if($FlagCari=="Y")
		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('SenaraiSeksyen','showSenaraiSeksyen','cetakReport=$cetakReport&FlagCetak=&ID_NEGERI=&NAMA_DAERAH=&NAMA_MUKIM=&NAMA_SEKSYEN=&carianUmum='+$jquery('#carianUmum').val());			 	  
		  });
		
		</script>
		#end
		</div>
		</tr>			
		</table>
		<!-- close CT -->
		<br>
		
	

	</td>
	</tr>
</table>
