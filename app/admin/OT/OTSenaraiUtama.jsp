<br>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">

#if($USER_ROLE=="adminekptg")
	<tr>
	<td>	
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="58%" class="underline_td_main">
		<strong>TABLE TRANSAKSI (OT)</strong>		
		<!-- open CT -->
		<div  id="span_LINK_CARIAN_TT">
		<a href="#" onClick="doDivAjaxCall$formname('div_CARIAN_TT','showCARIAN_TT','TT_OPENCLOSE_CARIAN=open');"><font color="white"><u>Carian Terperinci >></u></font></a>
		</div>
		<!-- close CT -->		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showCount_TT"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:document.getElementById('div_addTT').style.display=''; doDivAjaxCall$formname('div_addTT','addTT','ID_TRANSAKSI=');">
		<img title="Tambah Table Transaksi" src="../img/plus.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr >
		<td colspan="4" valign="top" id="div_addTT" >		
		</td>
		</tr>					
		<tr   >
		<td colspan="4" valign="top" id="div_CARIAN_TT" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->
		<div id="div_SenaraiTT" >		
			<script>		
			  $jquery(document).ready(function () {
				 doDivAjaxCall$formname('div_SenaraiTT','showSenaraiTT','');			 	  
			  });		
			</script>		
		</div>	
	</td>
	</tr>
#end	
	
#if($USER_ROLE=="adminppk")	
	<tr>
	<td>	
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="58%" class="underline_td_main">
		<strong>PENETAPAN MINIT TRANSAKSI</strong>		
		<!-- open CT -->
		<div  id="span_LINK_CARIAN_PMT">
		<a href="#" onClick="doDivAjaxCall$formname('div_CARIAN_PMT','showCARIAN_PMT','PMT_OPENCLOSE_CARIAN=open');"><font color="white"><u>Carian Terperinci >></u></font></a>
		</div>
		<!-- close CT -->		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showCount_PMT"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:document.getElementById('div_addPMT').style.display=''; doDivAjaxCall$formname('div_addPMT','addPMT','ID_TRANSAKSI=');">
		<img title="Tambah Table Transaksi" src="../img/plus.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr >
		<td colspan="4" valign="top" id="div_addPMT" >		
		</td>
		</tr>					
		<tr   >
		<td colspan="4" valign="top" id="div_CARIAN_PMT" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->
		<div id="div_SenaraiPMT" >		
			<script>	
			
			  $jquery(document).ready(function () {
				 doDivAjaxCall$formname('div_SenaraiPMT','showSenaraiPMT','');			 	  
			  });	
			
			</script>		
		</div>		
	</td>
	</tr>
#end	
	
#if($USER_ROLE=="adminppk" || $USER_ROLE=="user_ppk")	
	<tr>
	<td>	
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="58%" class="underline_td_main">
		<strong>PERMOHONAN OT</strong>		
		<!-- open CT -->
		<div  id="span_LINK_CARIAN_POT">
		<a href="#" onClick="doDivAjaxCall$formname('div_CARIAN_POT','showCARIAN_POT','POT_OPENCLOSE_CARIAN=open');"><font color="white"><u>Carian Terperinci >></u></font></a>
		</div>
		<!-- close CT -->		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showCount_POT"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:document.getElementById('div_addPOT').style.display=''; doDivAjaxCall$formname('div_addPOT','addPOT','ID_TRANSAKSI=');">
		<img title="Tambah Table Transaksi" src="../img/plus.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr >
		<td colspan="4" valign="top" id="div_addPOT" >		
		</td>
		</tr>					
		<tr   >
		<td colspan="4" valign="top" id="div_CARIAN_POT" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->
		<div id="div_SenaraiPOT" >		
			<script>	
			
			  $jquery(document).ready(function () {
				 doDivAjaxCall$formname('div_SenaraiPOT','showSenaraiPOT','');			 	  
			  });	
			
			</script>		
		</div>		
	</td>
	</tr>
#end

	
</table>