<br>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
	<!--checking role, filter list user -->
    <tr>
	<td>
	
		
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="58%" class="underline_td_main">
		<strong>PENGGUNA DALAMAN (HQ)</strong>
		
		<!-- open CT -->
		<div  id="span_LINK_CT_CARIAN_HQ">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_HQ','showCT_HQ','CT_OPENCLOSE_CARIAN=open&internalType=HQ&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>
		</div>
		<!-- close CT -->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_HQ"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:document.getElementById('div_viewPenggunaHQ').style.display=''; doDivAjaxCall$formname('div_viewPenggunaHQ','edit_PenggunaInternal','USER_ID=&internalType=HQ');">
		<img title="Tambah Pengguna" src="../img/plus3.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
		<tr   >
		<td colspan="4" valign="top" id="div_CT_CARIAN_HQ" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->

		
		<div id="div_PenggunaInternalHQ" >
		#if($FlagCari=="Y")
		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_PenggunaInternalHQ','showSenaraiPenggunaInternalHQ','FlagCari=$FlagCari&carianTerperinci='+$jquery('#carianTerperinci').val());			 	  
		  });
		
		</script>
		#end
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
		<strong>PENGGUNA DALAMAN (NEGERI)</strong>		
		<!-- open CT -->
		<div  id="span_LINK_CT_CARIAN_Negeri">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_Negeri','showCT_Negeri','CT_OPENCLOSE_CARIAN=open&internalType=Negeri&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>
		</div>
		<!-- close CT -->
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_Negeri"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:document.getElementById('div_viewPenggunaNegeri').style.display=''; doDivAjaxCall$formname('div_viewPenggunaNegeri','edit_PenggunaInternal','USER_ID=&internalType=Negeri');">
		<img title="Tambah Pengguna" src="../img/plus3.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
		<tr   >
		<td colspan="4" valign="top" id="div_CT_CARIAN_Negeri" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->
	
		<div id="div_PenggunaInternalNegeri" >
		
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
	<strong>PENGGUNA KJP</strong>
	<!-- open CT -->
	<div  id="span_LINK_CT_CARIAN_KJP">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_KJP','showCT_KJP','CT_OPENCLOSE_CARIAN=open&internalType=KJP&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>
	</div>
		<!-- close CT -->
	</td>
	<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_KJP"></span>
	</td>
	<td width="2%" class="underline_td_main">
	<a href="javascript:document.getElementById('div_viewPenggunaKJP').style.display=''; doDivAjaxCall$formname('div_viewPenggunaKJP','edit_PenggunaKJP','USER_ID=&internalType=KJP');">
	<img title="Tambah Pengguna" src="../img/plus3.gif" border="0"></a>
	</td>
	</tr>
	</table>
	<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
		<tr   >
		<td colspan="4" valign="top" id="div_CT_CARIAN_KJP" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->

	
		<div id="div_PenggunaKJP" >
		
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
	<strong>PENGGUNA INTEGRASI</strong>
	<!-- open CT -->
	<div  id="span_LINK_CT_CARIAN_INT">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_INT','showCT_INT','CT_OPENCLOSE_CARIAN=open&internalType=INT&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>
	</div>
		<!-- close CT -->
	</td>
	<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_INT"></span>
	</td>
	<td width="2%" class="underline_td_main">
	<a href="javascript:document.getElementById('div_viewPenggunaINT').style.display=''; doDivAjaxCall$formname('div_viewPenggunaINT','edit_PenggunaINT','USER_ID=&internalType=INT');">
	<img title="Tambah Pengguna" src="../img/plus3.gif" border="0"></a>
	</td>
	</tr>
	</table>
	<!-- open CT -->
	<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
	<tr   >
	<td colspan="4" valign="top" id="div_CT_CARIAN_INT" style="display:none">		
	</td>
	</tr>			
	</table>
	<!-- close CT -->
	
		<div id="div_PenggunaINT" >
		
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
<strong>PENGGUNA JKPTG ONLINE</strong>
<!-- open CT -->
<div  id="span_LINK_CT_CARIAN_Online">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_Online','showCT_Online','CT_OPENCLOSE_CARIAN=open&internalType=Online&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>
</div>
<!-- close CT -->
</td>
<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_Online"></span>
	</td>
<td width="2%" class="underline_td_main">
<a href="javascript:document.getElementById('div_viewPenggunaOnline').style.display=''; doDivAjaxCall$formname('div_viewPenggunaOnline','edit_PenggunaOnline','USER_ID=&internalType=Online');">
<img title="Tambah Pengguna" src="../img/plus3.gif" border="0"></a>
</td>
</tr>
</table>
<!-- open CT -->
<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
<tr   >
<td colspan="4" valign="top" id="div_CT_CARIAN_Online" style="display:none">		
</td>
</tr>			
</table>
<!-- close CT -->
		<div id="div_PenggunaOnline" >
		
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
	<strong>PERMOHONAN ID PENGGUNA</strong>
	<!-- open CT -->
	<div  id="span_LINK_CT_CARIAN_MOHON">
			<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_MOHON','showCT_MOHON','CT_OPENCLOSE_CARIAN=open&internalType=MOHON&FlagCari=&carianTerperinci='+$jquery('#carianTerperinci').val());"><font color="white"><u>Carian Terperinci >></u></font></a>
	</div>
	<!-- close CT -->
	</td>
	<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_MOHON"></span>
	</td>
	<td width="2%" class="underline_td_main">
	<!--  
	<a href="javascript:document.getElementById('div_viewPenggunaMOHON').style.display=''; doDivAjaxCall$formname('div_viewPenggunaMOHON','edit_PenggunaMOHON','USER_ID=&internalType=MOHON');">
	<img title="Tambah Pengguna" src="../img/plus3.gif" border="0"></a>
	-->
	</td>
	</tr>
	</table>
	
	<!-- open CT -->
	<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
	<tr   >
	<td colspan="4" valign="top" id="div_CT_CARIAN_MOHON" style="display:none">		
	</td>
	</tr>			
	</table>
	<!-- close CT -->
	
	<div id="div_PenggunaMOHON" >
		
	</div>
	</td>
	</tr>
	
	
</table>
