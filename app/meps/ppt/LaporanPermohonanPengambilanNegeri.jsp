<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
   		<td>&nbsp;</td>
	</tr>

	<tr>
    	<td>
<fieldset>
<legend>CARIAN</legend>
<table width="100%" border="0" >  
    <tr>
    <td width="24%">&nbsp;</td>
    <td width="6%"></td>
    <td width="7%"><div align="left">Permohonan</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="62%">
    	$!selectSuburusan
    	<input type="hidden" name="id_tahun" id="id_tahun" value="$!id_tahun">
 	</td>
  </tr>
  <tr>
    <td width="24%">&nbsp;</td>
    <td width="6%"></td>
    <td width="7%"><div align="left">Tahun</div></td>
    <td width="1%"><div align="center">&nbsp;</div></td>
    <td width="62%">Dari&nbsp;:&nbsp;$selectTahun&nbsp;&nbsp;Sehingga&nbsp;:&nbsp;$!selectTahunHingga
    	<input type="hidden" name="id_tahun" id="id_tahun" value="$!id_tahun">
  	</td>
  </tr>
    <tr>
    <td width="24%">&nbsp;</td>
    <td width="6%">&nbsp;</td>
    <td><div align="left"></div></td>
    <td><div align="center"></div></td>
    <td><input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="search_data()" />
    <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onclick="javascript:kosongkan()" /></td>
  </tr>
  <tr>
  <td colspan="5"></td>
  </tr>
</table>
</fieldset>

<br>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(0);Laporan()" >Laporan</li>
    <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(1);BarGraph()" ><i>Bar Chart</i></li>
    <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(2);PieChart()" ><i>Doughnut Chart</i></li>
    <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(3);LineChart()" ><i>Line Chart</i></li>
  <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(4);PieChart()" ><i>Pie Chart</i></li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<!-- LIST OF FAIL -->
    <div class="TabbedPanelsContent">
    
    <fieldset>
	<legend>$!tajukLaporan</legend>
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
          <td width="3%" align="center" style="text-transform:uppercase" scope="row">Bil.</td>
          <td width="80%" style="text-transform:uppercase">Tahun</td>
          <td width="17%" style="text-transform:uppercase"><div align="center">Jumlah Permohonan</div></td> 
        </tr>
        
	#if($list_size!=0)    
  	#set ($jumlah = 0)
  	##set ($jumlahHM = 0)
  
    	#foreach($senarai in $PermohonanList)
       	##set ($jumlah = $jumlah + $EkptgUtil.parseInt($senarai.jumlah_permohonan))
       	#set ($jumlah = $jumlah + $senarai.jumlahPermohonanInt)
       	##set ($jumlahHM = $jumlahHM + $EkptgUtil.parseInt($senarai.jumlah_hakmilik))
       
		#set( $i = $velocityCount )
       	#if ( ($i % 2) != 1 )
        	#set( $row = "row2" )
       	#else
        	#set( $row = "row1" )
     	#end
          	
          <tr valign="top">
            <td class="$row" ><div align="center">$senarai.bil.</div></td>
            <td class="$row">$senarai.nama_negeri</td>
            <td class="$row"><div align="center">$senarai.jumlah_permohonan</div></td>
           <!--  <td class="$row"><div align="center">$senarai.jumlah_hakmilik</div></td> -->
          </tr>          
		#end   
      
    	  <tr>
          	<td colspan="3">&nbsp;</td>
          </tr>
             <tr valign="top" class="table_header">
            <td ></td>
            <td ><div align="right"><strong>JUMLAH</strong></div></td>
            <td ><div align="center"><strong>$!jumlah</strong></div></td>
           <!-- <td class="$row"><div align="center"><strong>$jumlahHM</strong></div></td> --> 
          </tr>        
      
      
      	 <tr>
          	<td colspan="3">&nbsp;</td>
          </tr>
 	#else  
   		  <tr>
        	<td colspan="3">Tiada rekod</td>
          <tr>  
	#end  
      </table>
</fieldset>
    
    </div>
    <!-- END LIST OF FAIL -->
    
    
    <!-- BAR GRAPH -->
    <div class="TabbedPanelsContent">
    #if ($selectedTab=="1")
     <object width="400" height="300" id="Column3D" >
         <param name="movie" value="../FusionCharts/Column3D.swf" />
         <param name="FlashVars" value="&dataXML=$xml&chartWidth=400&chartHeight=300">
         <param name="quality" value="high" />
         <embed src="../fusioncharts/Charts/Column3D.swf" flashVars="&dataXML=$xml&chartWidth=1000&chartHeight=300" quality="high" width="1000" height="300" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </object>
      #end
    </div>
  
    <!-- END BAR GRAPH -->
    
    
    <!-- PIE CHART -->
    <div class="TabbedPanelsContent">
    #if ($selectedTab=="2")
     <object width="550" height="300" id="Pie3D" >
      <param name="movie" value="../FusionCharts/Doughnut2D.swf" />
      <param name="FlashVars" value="&dataURL=Data.xml&chartWidth=550&chartHeight=300">
      <param name="quality" value="high" />
      <embed src="../fusioncharts/Charts/Doughnut2D.swf" flashVars="&dataXML=$xml&chartWidth=550&chartHeight=300" quality="high" width="700" height="400" name="Doughnut2D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </embed>
      </object>
       #end
     </div>
   
    <!-- END PIE CHART -->
    
     <!-- Line Chart -->
    <div class="TabbedPanelsContent">
    #if ($selectedTab=="3")
     <object width="900" height="400" id="Line" >
      <param name="movie" value="../FusionCharts/Line.swf" />
      <param name="FlashVars" value="&dataURL=Data.xml&chartWidth=900&chartHeight=400">
      <param name="quality" value="high" />
      <embed src="../fusioncharts/Charts/Line.swf" flashVars="&dataXML=$xml&chartWidth=900&chartHeight=400" quality="high" width="900" height="400" name="Line" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </embed>
      </object>
       #end
     </div>
   
    <!-- END Line Chart -->
    <!--PIE CHART2   --> 
       <div class="TabbedPanelsContent">
       #if ($selectedTab=="4")
        <object width="550" height="300" id="Pie3D" >
         <param name="movie" value="../FusionCharts/Pie3D.swf" />
         <param name="FlashVars" value="&dataURL=Data.xml&chartWidth=550&chartHeight=300">
         <param name="quality" value="high" />
         <embed src="../fusioncharts/Charts/Pie3D.swf" flashVars="&dataXML=$xml&chartWidth=550&chartHeight=300" quality="high" width="700" height="400" name="Pie3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
         </embed>
         </object>
          #end
        </div>
      
    <!-- END PIE CHART2-->
   </div>
           #if ($refence=='yes')
 <!-- REFERENCE -->

    <fieldset>
        <legend>Rujukan Negeri</legend>
        
          <table width="60%"  cellpadding="1" cellspacing="2" border="0">
            <tr class="table_header">
              <td width="10%" style="text-transform:uppercase">Abbrev</td>
              <td width="50%" style="text-transform:uppercase"><div align="center">Nama Negeri</div></td> 
            </tr>
            
      #if($listKod_size!=0)     
           #foreach($SenaraiKod in $SenaraiKod)
                    #set( $i = $velocityCount )
                    #if ( ($i % 2) != 1 )
                         #set( $row = "row2" )
                    #else
                         #set( $row = "row1" )
                    #end
                
              <tr valign="top">
                <td class="$row">$SenaraiKod.abbrev</td>
                <td class="$row"><div align="left">$SenaraiKod.negeri</div></td>
              </tr>          
          #end   
             <tr>
                <td colspan="11">&nbsp;</td>
              </tr>
          #else  
              <tr>
                <td colspan="10">Tiada rekod</td>
              <tr>  
          #end  
          </table>
    </fieldset>  
<!-- END OF REFERENCE -->   
#end   
</div>
<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>

<script type="text/javascript">

	function kosongkan() {
		document.${formName}.reset();
		//document.${formName}.socTahun.value = "";
		//document.${formName}.socBulan.value = "";
		//document.${formName}.id_tahun.value = "";
		//document.${formName}.id_bulan.value = "";	
		document.${formName}.submit();
	}

	function search_data(){
		doAjaxCall${formName}("search_data");				
	}

	function BarGraph(){
		//document.${formName}.command.value = "BarGraph";
		//document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanBulananProjek";
		//document.${formName}.submit();
		doAjaxCall${formName}("BarGraph");		

	}

	function PieChart(){
		//document.${formName}.command.value = "PieChart";
		//document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanBulananProjek";
		//document.${formName}.submit();
		doAjaxCall${formName}("PieChart");		

	}

	function LineChart(){
		document.${formName}.command.value = "LineChart";
		//document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanBulananProjek";
		//document.${formName}.submit();
		doAjaxCall${formName}("");		

	}
	
function PieChart2(){
    document.${formName}.command.value = "PieChart2";
    document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanBulananProjek";
    document.${formName}.submit();
}

	function Laporan(){
		//document.${formName}.command.value = "Laporan";
		//document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanBulananProjek";
		//document.${formName}.submit();
		doAjaxCall${formName}("Laporan");
		
	}

function setSelected(tabid) {
	document.${formName}.tabId.value = tabid;
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

</script>

