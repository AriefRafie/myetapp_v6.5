
<table width="100%" border="0" class="classFade dashboard_bawah"  >
<tr>
<td>


<div id="TabbedPanels2" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">

   	<li class="TabbedPanelsTab" tabindex="0" id="statLogin"   >Trend Log Masuk</li>  
    <li class="TabbedPanelsTab" tabindex="0"  id="statUsereTapp"  >Statistik Pengguna eTapp</li>
    <li class="TabbedPanelsTab" tabindex="0" id="statTableAdmin" >Senarai Aktiviti Pengguna</li>
  </ul>
     <div class="TabbedPanelsContentGroup"  id="group_tab" >
   

<div class="TabbedPanelsContent" id="statLog"  >  
<br />
<div align="center"><strong>TREND LOG MASUK BAGI TAHUN $TAHUN</strong></div>
<br />

<table width="100%" border="0" cellspacing="1" cellpadding="1" >
<tr><td colspan="14" align="right"><img width="20" height="20" src="../img/printer.png" onclick="doCetak('LogMasuk')"></td></tr>
<tr class="table_header" >
<td width="27%" align="center" valign="top"><strong>Bulan</strong></td>
<td width="73%" valign="top" align="center"><strong>Log Pengguna</strong></td>
</tr>
#if ($statLogin.size()>0)
#foreach ($stat in $statLogin)
<tr>
<td width="27%" align="center" valign="top" class="$stat.rowCss">$stat.MONTH_DISPLAY</td>
<td width="73%" valign="top" align="center" class="$stat.rowCss">$stat.JUMLAH</td>
</tr>
#end
#end
</table>
</div> 

<div class="TabbedPanelsContent" id="statUser"  > 
<br />
<div align="center"><strong>TREND PENDAFTARAN PENGGUNA BAGI TAHUN $TAHUN</strong></div>
<br /> 
<table width="100%" border="0" cellspacing="1" cellpadding="1" >
<tr><td colspan="14" align="right"><img width="20" height="20" src="../img/printer.png" onclick="doCetak('logJumlahDaftar')" /></td></tr>
<tr class="table_header" >
<td width="37%" align="center" valign="top"><strong>Bulan</strong></td>
<td width="63%" valign="top" align="center"><strong>Daftar Pengguna</strong></td>
<!--<td width="73%" valign="top" align="center"><strong>Nyahaktif Pengguna</strong></td>-->
</tr>
#if ($statDaftar.size()>0)
#foreach ($stat in $statDaftar)
<tr>
<td width="37%" align="center" valign="top" class="$stat.rowCss">$stat.MONTH_DISPLAY</td>
<td width="63%" valign="center" align="center" class="$stat.rowCss">$stat.JUMLAH_DAFTAR</td>
<!--<td width="63%" valign="center" align="center"></td>-->
</tr>
#end
#end
</table>
</div>

<div class="TabbedPanelsContent"  id="statTable"  >  
<br />
<div align="center"><strong>SENARAI LOG PENGGUNA BAGI TAHUN $TAHUN</strong></div>
<br />
<div id ="senaraiAktivitiPengguna" >
<script>
$jquery(document).ready(function () {
doDivAjaxCall$formname('senaraiAktivitiPengguna','showSenaraiAktiviti','');	
});
</script>
</div>
</div>

 


</td>
</tr>
</table>

    <script>
    var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:0});
	</script>


 	
   
    
    
    
    
    
    
  
   
    
   
   <!--  #parse("app/ppt/dashboard/script.jsp")-->