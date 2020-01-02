<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
	<!--checking role, filter list user -->
    <tr>
	<td>
	
		
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="58%" class="underline_td_main">
		<strong>SENARAI PEJABAT JKPTG</strong>
		
		<!-- open CT -->
		<div  id="span_LINK_CT_CARIAN_JKPTG">
		<!--<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_HQ','showCT_HQ','CT_OPENCLOSE_CARIAN=open&internalType=HQ&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>-->
        <a href ="#" onClick="javascript:document.getElementById('div_CT_CARIAN_JKPTG').style.display='';doDivAjaxCall$formname('div_CT_CARIAN_JKPTG','carianTerperinci','mode=1');"><font color="white"><u>Carian Terperinci >>>></u></font></a>

		</div>
		<!-- close CT -->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_HQ"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:document.getElementById('div_ViewJKPTG1').style.display=''; doDivAjaxCall$formname('div_ViewJKPTG1','editPejabat','ID_PEJABATJKPTG=&mode=1');">
		<img title="Tambah Pejabat JKPTG" src="../img/plus3.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
		<tr   >
		<td colspan="4" valign="top" id="div_CT_CARIAN_JKPTG" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->

		
		<div id="div_JKPTG" >

		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_JKPTG','showSenaraiPejabat1','mode=1&jenisPejabat=1&cetakReport=Y&JENISPEJ=');			 	  
		  });
		
		</script>

		</div>	
	</td>
	</tr>
	<!--sini tutup-->
	<tr>
	<td>
	

		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="58%" class="underline_td_main">
		<strong>SENARAI PEJABAT URUSAN </strong>		
		<!-- open CT -->
		<div  id="span_LINK_CT_CARIAN_URUSAN">
		<!--<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_GRED','showCT_Negeri','CT_OPENCLOSE_CARIAN=open&internalType=Negeri&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>-->
        <a href ="#" onclick="javascript:document.getElementById('div_CT_CARIAN_URUSAN').style.display='';doDivAjaxCall$formname('div_CT_CARIAN_URUSAN','carianTerperinci','mode=2');"><font color="white"><u> Carian Terperinci >>>></u></font></a>
		</div>
		<!-- close CT -->
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_Negeri"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:document.getElementById('div_ViewURUSAN2').style.display=''; doDivAjaxCall$formname('div_ViewURUSAN2','editPejabat','ID_PEJABAT=&mode=2');">
		<img title="Tambah Pejabat Urusan" src="../img/plus3.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
		<tr   >
		<td colspan="4" valign="top" id="div_CT_CARIAN_URUSAN" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->
	
		<div id="div_URUSAN" >

		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_URUSAN','showSenaraiPejabat2','mode=2&jenisPejabat=2&cetakReport=Y&JENISPEJ=');			 	  
		  });
		
		</script>

		</div>	
		
	</td>
	</tr>	
	
</table>