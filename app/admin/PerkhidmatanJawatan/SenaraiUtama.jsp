<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
	<!--checking role, filter list user -->
    <tr>
	<td>
	
		
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="58%" class="underline_td_main">
		<strong>SENARAI KLASIFIKASI</strong>
		
		<!-- open CT -->
		<div  id="span_LINK_CT_CARIAN_KLAS">
		<!--<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_HQ','showCT_HQ','CT_OPENCLOSE_CARIAN=open&internalType=HQ&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>-->
        <a href ="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_KLAS','link_klasifikasi','');"><font color="white"><u>Carian Klasifikasi >>>></u></font></a>

		</div>
		<!-- close CT -->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_HQ"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:document.getElementById('div_ViewKlasifikasi').style.display=''; doDivAjaxCall$formname('div_ViewKlasifikasi','addKlasifikasi','ID_KLASIFIKASI=');">
		<img title="Tambah Klasifikasi" src="../img/plus3.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
		<tr   >
		<td colspan="4" valign="top" id="div_CT_CARIAN_KLAS" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->

		
		<div id="div_Klasifikasi" >

		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_Klasifikasi','showSenaraiKlasifikasi','');			 	  
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
		<strong>SENARAI GRED</strong>		
		<!-- open CT -->
		<div  id="span_LINK_CT_CARIAN_GRED">
		<!--<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_GRED','showCT_Negeri','CT_OPENCLOSE_CARIAN=open&internalType=Negeri&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>-->
        <a href ="#" onclick="javaScript:doDivAjaxCall$formname('div_CT_CARIAN_GRED','link_gred','');"><font color="white"><u> Carian Gred >>>></u></font></a>

		</div>
		<!-- close CT -->
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_Negeri"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:document.getElementById('div_ViewGred').style.display=''; doDivAjaxCall$formname('div_ViewGred','addGred','ID_GRED=');">
		<img title="Tambah Gred" src="../img/plus3.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
		<tr   >
		<td colspan="4" valign="top" id="div_CT_CARIAN_GRED" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->
	
		<div id="div_Gred" >

		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_Gred','showSenaraiGred','');			 	  
		  });
		
		</script>

		</div>	
		
	</td>
	</tr>	
	
	
	<tr>
	<td>
	<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr class="table_header" >
	<td width="2%" class="underline_td_main">
	</td>
	<td width="58%" class="underline_td_main">
	<strong>SENARAI KUMPULAN PERKHIDMATAN</strong>
	<!-- open CT -->
	<div  id="span_LINK_CT_CARIAN_KUMPKHIDMAT">
		<!--<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_KUMPKHIDMAT','showCT_KJP','CT_OPENCLOSE_CARIAN=open&internalType=KJP&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>-->
        <a href ="#" onclick="javaScript:doDivAjaxCall$formname('div_CT_CARIAN_KUMPKHIDMAT','link_Khidmat','');"><font color="white"><u>Carian Kumpulan Perkhidmatan >>>></u></font></a>

	</div>
		<!-- close CT -->
	</td>
	<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_KJP"></span>
	</td>
	<td width="2%" class="underline_td_main">
	<a href="javascript:document.getElementById('div_ViewKhidmat').style.display=''; doDivAjaxCall$formname('div_ViewKhidmat','addKhidmat','ID_KHIDMAT=');">
	<img title="Tambah Kumpulan Khidmat" src="../img/plus3.gif" border="0"></a>
	</td>
	</tr>
	</table>
	<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
		<tr   >
		<td colspan="4" valign="top" id="div_CT_CARIAN_KUMPKHIDMAT" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->
		
        <div id="div_KumpKhidmat" >

		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_KumpKhidmat','showSenaraiKhidmat','');			 	  
		  });
		
		</script>

		</div>	
	</td>
	</tr>	
	
	<tr>
	<td>
	
	
	<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr class="table_header" >
	<td width="2%" class="underline_td_main">
	</td>
	<td width="58%" class="underline_td_main">
	<strong>SENARAI SKIM PERKHIDMATAN (JAWATAN)</strong>
	<!-- open CT -->
	<div  id="span_LINK_CT_CARIAN_SKIM">
		<!--<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_INT','showCT_INT','CT_OPENCLOSE_CARIAN=open&internalType=INT&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>-->
        <a href ="javaScript:doDivAjaxCall$formname('div_CT_CARIAN_SKIM','link_SkimKhidmat','');"><font color="white"><u>Carian Skim >>>></u></font></a>

	</div>
		<!-- close CT -->
	</td>
	<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_INT"></span>
	</td>
	<td width="2%" class="underline_td_main">
	<a href="javascript:document.getElementById('div_ViewSkim').style.display=''; doDivAjaxCall$formname('div_ViewSkim','addSkimKhidmat','ID_JAWATAN=');">
	<img title="Tambah Pengguna" src="../img/plus3.gif" border="0"></a>
	</td>
	</tr>
	</table>
	<!-- open CT -->
	<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
	<tr   >
	<td colspan="4" valign="top" id="div_CT_CARIAN_SKIM" style="display:none">		
	</td>
	</tr>			
	</table>
	<!-- close CT -->
	
		<div id="div_SkimKhidmat" >
		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_SkimKhidmat','showSenaraiSkimKhidmat','');			 	  
		  });
		
		</script>
		</div>
	</td>
	</tr>
	
</table>