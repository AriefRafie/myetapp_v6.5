<br>



#set($displayByRole = "block")
#if($USER_ROLE != "(PDT)HQPengguna")
#set($displayByRole = "none")
#end



<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">

	<tr>
	<td>	
    <div style="display:$displayByRole" >
    
    
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="38%" class="underline_td_main">
		<strong>WARTA TANAH RIZAB MELAYU</strong>		
		<!-- open CT -->
		<div  id="span_LINK_CARIAN">
		<a href="#" onClick="document.getElementById('div_row').style.display='none';doDivAjaxCall$formname('div_CARIAN','showCARIAN','OPENCLOSE_CARIAN=open');"><font color="white"><u>Carian Terperinci >></u></font></a>
		</div>
		<!-- close CT -->		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showCount_BU"></span>
		</td>
		<td width="21%" align="right" class="underline_td_main">
        
       	
         <input type="button" id="BTNADD$view.ID_WARTATRM"  name="BTNADD$view.ID_WARTATRM" 
                    onclick="document.getElementById('div_CARIAN').style.display='none';document.getElementById('div_row').style.display=''; doDivAjaxCall3$formname('div_row','add','ID_WARTATRM=&mode=edit&commandFrom=form&setJENISWARTA=W&ID_WARTATRMINDUK=&JENISSUB');" 
                    value="Warta Perwujudan" />     
                       
        <!--
		<a href="javascript:document.getElementById('div_add').style.display=''; doDivAjaxCall$formname('div_add','add','ID_WARTATRM=');">
		<img title="$title_button_tambah_aduan"  height="15" width="15" src="../img/plus.gif" border="0"></a>
        -->
		</td>
        <td width="2%" class="underline_td_main" ></td>
		</tr>
		</table>
		<!-- open CT -->
        
        <table width="100%" id="div_row" style="display:none">
        <tr >
        </tr>
        </table>
        
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
						
		<tr   >
		<td colspan="4" valign="top" id="div_CARIAN" style="display:none">		
		</td>
		</tr>			
		</table>
		<!-- close CT -->
		<div id="div_Senarai" >		
				
		</div>	
        </div>
       
        
       
        <table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="76%" class="underline_td_main">
		<strong>LAPORAN KELUASAN KAWASAN TANAH RIZAB MELAYU</strong>		
		<!-- open CT -->
		<div  id="span_LINK_CARIANLaporan">
		<a href="#" onClick="doDivAjaxCall$formname('div_CARIANLaporan','showCARIANLaporan','OPENCLOSE_CARIAN_LAPORAN=open');"><font color="white"><u>Filter Laporan >></u></font></a>
		</div>
		<!-- close CT -->		
		</td>
		
		<td width="21%" align="right" class="underline_td_main">        
		</td>
        <td width="2%" class="underline_td_main" ></td>
		</tr>
		</table>
        <table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
						
		<tr   >
		<td colspan="4" valign="top" id="div_CARIANLaporan" style="display:none">	
        </td>
		</tr>			
		</table>
		<!-- close CT -->
		<div id="div_Laporan" >		
		
		</div>
        
        	
	</td>
	</tr>
</table>

#if($after_uploadLampiran!="Y")
<script>
  $jquery(document).ready(function () {
	 doDivAjaxCall$formname('div_Senarai','showSenarai','');			 	  
  });
</script>	
#end
