<br>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">

	<tr>
	<td>	
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="38%" class="underline_td_main">
		<strong>$label_tajuk_modul_aduan</strong>		
		<!-- open CT -->
		<div  id="span_LINK_CARIAN">
		<a href="#" onClick="document.getElementById('div_row').style.display='none';doDivAjaxCall$formname('div_CARIAN','showCARIAN','OPENCLOSE_CARIAN=open');"><font color="white"><u>$link_carian_terperinci >></u></font></a>
		</div>
		<!-- close CT -->		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showCount_BU"></span>
		</td>
		<td width="21%" align="right" class="underline_td_main">
        
        #set($showButtonAdd = "none")
        #if($USER_ROLE == "ppt-online-user" || $USER_ROLE == "php-online-user" || $USER_ROLE == "ppk-online-user" || $USER_ROLE == "online" || $USER_ROLE == "user_unit_intergriti")
        	#set($showButtonAdd = "")
        #end	
         <input type="button" id="BTNADD$view.ID_ADUANPUBLIC" style="display:$showButtonAdd" name="BTNADD$view.ID_ADUANPUBLIC" 
                    onclick="document.getElementById('div_CARIAN').style.display='none';document.getElementById('div_row').style.display=''; doDivAjaxCall3$formname('div_row','add','ID_ADUANPUBLIC=&mode=edit');" 
                    value="$title_button_tambah_aduan" />     
                       
        <!--
		<a href="javascript:document.getElementById('div_add').style.display=''; doDivAjaxCall$formname('div_add','add','ID_ADUANPUBLIC=');">
		<img title="$title_button_tambah_aduan"  height="15" width="15" src="../img/plus.gif" border="0"></a>
        -->
		</td>
        <td width="2%" class="underline_td_main" ></td>
		</tr>
		</table>
		<!-- open CT -->
        
        <table width="100%" id="div_row" style="display:none" >
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
	</td>
	</tr>


	
</table>

#if($fromDashboard=="Y")
<script>	
			
  $jquery(document).ready(function () {
	 document.getElementById('div_CARIAN').style.display='none';
	 document.getElementById('div_row').style.display=''; 
	 doDivAjaxCall3$formname('div_row','add','ID_ADUANPUBLIC=&mode=edit&fromDashboard=Y');		 	  
  });	

</script>
#else
<script>
  $jquery(document).ready(function () {
	 doDivAjaxCall$formname('div_Senarai','showSenarai','FLAG_NOTIFIKASI=');			 	  
  });
</script>	
#end	