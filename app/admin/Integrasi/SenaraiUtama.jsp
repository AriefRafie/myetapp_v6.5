<br>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
	
	<tr>
	<td>
	
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="58%" class="underline_td_main">
		<strong>INTEGRASI DALAMAN</strong>
		
		<!-- open CT -->
		<div  id="span_LinkCarianInteg1">
		<a href="#" onClick="doDivAjaxCall$formname('div_CarianInteg1','showCarianInteg','CT_OPENCLOSE_CARIAN1=open&Type=1&FlagCari=&carianInteg='+$jquery('#carianInteg').val());"><font color="white"><u>Carian Terperinci >></u></font></a>
		</div>
		<!-- close CT -->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_HQ"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:doDivAjaxCall$formname('div_ListInteg1','addInteg','ID_INTEGRASI=&Type=1');">
		<img title="Tambah Pengguna" src="../img/plus3.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
		<tr   >
		<td colspan="4" valign="top" id="div_CarianInteg1" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->
		<div id="div_ListInteg1" >
		<script>
		$jquery(document).ready(function() {
		doDivAjaxCall$formname('div_ListInteg1','showSenaraiInteg1','Type=1&FlagCari=&FlagDelete=&FlagCetak=&carianUmum='+$jquery('#carianUmum').val());
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
	<strong>INTEGRASI LUARAN</strong>
	<!-- open CT -->
	<div  id="span_LinkCarianInteg2">
		<a href="#" onClick="doDivAjaxCall$formname('div_CarianInteg2','showCarianInteg','CT_OPENCLOSE_CARIAN2=open&Type=2&FlagCari=&carianInteg='+$jquery('#carianInteg').val());"><font color="white"><u>Carian Terperinci >></u></font></a>
	</div>
		<!-- close CT -->
	</td>
	<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_KJP"></span>
	</td>
	<td width="2%" class="underline_td_main">
	<a href="javascript:doDivAjaxCall$formname('div_ListInteg2','addInteg','ID_INTEGRASI=&Type=2');">
	<img title="Tambah Pengguna" src="../img/plus3.gif" border="0"></a>
	</td>
	</tr>
	</table>
	<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
		<tr   >
		<td colspan="4" valign="top" id="div_CarianInteg2" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->

	
		<div id="div_ListInteg2" >
        <script>
		$jquery(document).ready(function() {
		doDivAjaxCall$formname('div_ListInteg2','showSenaraiInteg2','Type=2&FlagCari=&FlagDelete=&FlagCetak=&carianUmum='+$jquery('#carianUmum').val());
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
	<strong>INTEGRASI ANTARA SISTEM</strong>
	<!-- open CT -->
	<div  id="span_LinkCarianInteg3">
		<a href="#" onClick="doDivAjaxCall$formname('div_CarianInteg3','showCarianInteg','CT_OPENCLOSE_CARIAN3=open&Type=3&FlagCari=&carianInteg='+$jquery('#carianInteg').val());"><font color="white"><u>Carian Terperinci >></u></font></a>
	</div>
		<!-- close CT -->
	</td>
	<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_INT"></span>
	</td>
	<td width="2%" class="underline_td_main">
	<a href="javascript:doDivAjaxCall$formname('div_ListInteg3','addInteg','ID_INTEGRASI=&Type=3');">
	<img title="Tambah Pengguna" src="../img/plus3.gif" border="0"></a>
	</td>
	</tr>
	</table>
	<!-- open CT -->
	<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
	<tr   >
	<td colspan="4" valign="top" id="div_CarianInteg3" style="display:none">		
	</td>
	</tr>			
	</table>
	<!-- close CT -->
	
		<div id="div_ListInteg3" >
        <script>
		 $jquery(document).ready(function () {
		doDivAjaxCall$formname('div_ListInteg3','showSenaraiInteg3','Type=3&FlagCari=&FlagDelete=&FlagCetak=&carianUmum='+$jquery('#carianUmum').val());
		});
		</script>
		</div>
	</td>
	</tr>
		
</table>
