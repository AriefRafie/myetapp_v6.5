<br>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
#if($USER_ROLE=="adminppk" || $USER_ROLE=="user_ppk")	
	<tr>
	<td>	
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="58%" class="underline_td_main">
		<strong>PERMOHONAN BANTU UPP NEGERI</strong>		
		<!-- open CT -->
		<div  id="span_LINK_CARIAN_BU">
		<a href="#" onClick="doDivAjaxCall$formname('div_CARIAN_BU','showCARIAN_BU','BU_OPENCLOSE_CARIAN=open');"><font color="white"><u>Carian Terperinci >></u></font></a>
		</div>
		<!-- close CT -->		
		</td>
		<td width="32%" class="underline_td_main" align="right" valign="top" >
		<span id="showCount_BU"></span>
		</td>
		<td width="8%" class="underline_td_main" align="left">
		<a href="javascript:document.getElementById('div_addBU').style.display=''; doDivAjaxCall$formname('div_addBU','addBU','ID_PERMOHONANBANTUUNIT=');">
		<img title="Tambah Permohonan Kebenaran Membantu Unit" src="../img/mohon.png" border="0"></a>
		
		</td>
		</tr>
		</table>
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr >
		<td colspan="4" valign="top" id="div_addBU" >		
		</td>
		</tr>					
		<tr   >
		<td colspan="4" valign="top" id="div_CARIAN_BU" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->
		<div id="div_SenaraiBU" >		
			<script>	
			
			  $jquery(document).ready(function () {
				 doDivAjaxCall$formname('div_SenaraiBU','showSenaraiBU','');			 	  
			  });	
			
			</script>		
		</div>		
	</td>
	</tr>
#end

	
</table>