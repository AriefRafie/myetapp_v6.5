
<table width="100%" border="0" class="classFade dashboard_bawah"  >
<tr>
<td>


<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">

    <li class="TabbedPanelsTab" tabindex="0" id="statMonthPLA" >Statistik PLA Mengikut Bulan</li>
   	<li class="TabbedPanelsTab" tabindex="0" id="statModulePLA"   >Statistik PLA Mengikut Modul</li>  
    <li class="TabbedPanelsTab" tabindex="0"  id="statJKPTGPLA"  >Statistik PLA Mengikut Negeri</li>
     <li class="TabbedPanelsTab" tabindex="0"  id="listPLA"  >Senarai Log Aduan </li>
  </ul>
     <div class="TabbedPanelsContentGroup"  id="group_tab" >
   
   
<div class="TabbedPanelsContent"  id="statMonth"  >  
<br />
<div align="center"><strong>JUMLAH ADUAN MENGIKUT BULAN BAGI TAHUN $TAHUN</strong></div>
<br />
<table width="100%" border="0" cellspacing="1" cellpadding="1" >
<tr><td colspan="14" align="right"><img width="20" height="20" src="../img/printer.png" onclick="doCetak('aduanBulan')"></td></tr>
<tr class="table_header" >
<td width="27%" align="center" valign="top"><strong>Bulan</strong></td>
<td width="73%" valign="top" align="center"><strong>Jum. Aduan</strong></td>
</tr>
#if ($statMonthPLA.size()>0)
#foreach ($stat in $statMonthPLA)
<tr>
<td width="27%" align="center" valign="top" class="$stat.rowCss">$stat.MONTH_DISPLAY</td>
<td width="73%" valign="top" align="center" class="$stat.rowCss">$stat.JUMLAH</td>
</tr>
#end
#else
<tr >
<td colspan="14">Tiada rekod aduan. </td>
</tr>
#end
</table>
</div>

<div class="TabbedPanelsContent" id="statModule"  >  
<br />
<div align="center"><strong>JUMLAH ADUAN MENGIKUT MODUL BAGI TAHUN $TAHUN</strong></div>
<br />
<table width="100%" border="0" cellspacing="1" cellpadding="1">
<tr><td colspan="14" align="right"><img width="20" height="20" src="../img/printer.png" onclick="doCetak('aduanModul')"></td></tr>
<tr class="table_header">
<td width="27%" align="center" valign="top"><strong>Modul</strong></td>
<td width="73%" valign="top" align="center"><strong>Jum. Aduan</strong></td>
</tr>
#if ($statModulePLA.size()>0)
#foreach ($stat in $statModulePLA)
<tr>
<td width="27%" align="center" valign="top" class="$stat.rowCss">$stat.MODUL</td>
<td width="73%" valign="top" align="center" class="$stat.rowCss">$stat.JUMLAH</td>
</tr>
#end
#else
<tr >
<td colspan="14">Tiada rekod aduan. </td>
</tr>
#end
</table>
</div> 

<div class="TabbedPanelsContent" id="statJKPTG"  > 
<br />
<div align="center"><strong>JUMLAH ADUAN MENGIKUT NEGERI BAGI TAHUN $TAHUN</strong></div>
<br /> 
<table width="100%" border="0" cellspacing="1" cellpadding="1">
<tr><td colspan="14" align="right"><img width="20" height="20" src="../img/printer.png" onclick="doCetak('aduanPejabat')"></td></tr>
<tr class="table_header" >
<td width="37%" align="center" valign="top"><strong>Negeri</strong></td>
<td width="63%" valign="top" align="center"><strong>Jum. Aduan</strong></td>
</tr>
#if ($statJKPTGPLA.size()>0)
#foreach ($stat in $statJKPTGPLA)
<tr>
<td width="37%" align="center" valign="top" class="$stat.rowCss">$stat.NAMA_NEGERI</td>
<td width="63%" valign="center" align="center" class="$stat.rowCss">$stat.JUMLAH</td>
</tr>
#end
#else
<tr >
<td colspan="14">Tiada rekod aduan. </td>
</tr>
#end
</table>
</div>


<div class="TabbedPanelsContent"  id="listPLA"  >  
<br />
<div align="center"><strong>SENARAI ADUAN BAGI TAHUN $TAHUN</strong></div>
<br />
<div id ="senaraiLogAduan">
<script>
$jquery(document).ready(function () {
doDivAjaxCall$formname('senaraiLogAduan','showSenaraiAduan','');				
});
</script>
</div>

<!--<table width="50%" border="1" align="center" bordercolor="#000000">
<tr bgcolor="#999999" >
<td width="27%" align="center" valign="top"><strong>Bulan</strong></td>
<td width="73%" valign="top" align="center"><strong>Jum. Aduan</strong></td>
</tr>
#if ($statMonthPLA.size()>0)
#foreach ($stat in $statMonthPLA)
<tr>
<td width="27%" align="center" valign="top">$stat.MONTH_DISPLAY</td>
<td width="73%" valign="top" align="center">$stat.JUMLAH</td>
</tr>
#end
#else
<tr >
<td colspan="14">Tiada rekod aduan. </td>
</tr>
#end
</table>-->
</div>


</div>

</div>

 


</td>
</tr>
</table>

    <script>
    var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
	</script>


 	
   
    
    
    
    
    
    
  
   
    
   
   <!--  #parse("app/ppt/dashboard/script.jsp")-->