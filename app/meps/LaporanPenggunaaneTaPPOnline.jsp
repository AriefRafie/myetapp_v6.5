

<fieldset>
<legend>CARIAN</legend>
<table width="100%" border="0" >  
  <tr>
    <td width="24%">&nbsp;</td>
    <td width="6%"></td>
    <td width="7%"><div align="left">Tahun</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="62%">$selectTahun<input type="hidden" name="id_tahun" id="id_tahun" value="$!id_tahun"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td></td>
    <td>Bulan</td>
    <td><div align="center">:</div></td>
    <td>$selectBulan<input type="hidden" name="id_bulan" id="id_bulan" value="$!id_bulan"></td>
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
    <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(1);BarGraph()" >Bar Chart</li>
    <!--<li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(2);PieChart()" >Pie Chart</li>-->
    <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(3);LineChart2()" >Line Chart</li> 
  </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<!-- LIST OF FAIL -->
    <div class="TabbedPanelsContent">
    
    <fieldset>
	<legend>$!tajukLaporan</legend>
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
          <td width="5%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
          <td width="20%" style="text-transform:uppercase">Negeri</td>
          <td width="10%" style="text-transform:uppercase"><div align="center">Aduan & Cadangan Baru</div></td> 
          <td width="10%" style="text-transform:uppercase"><div align="center">Status Cadangan</div></td> 
          <td width="10%" style="text-transform:uppercase"><div align="center">Maklumat Pemohon</div></td>
          <td width="10%" style="text-transform:uppercase"><div align="center">Status Permohonan</div></td>
        </tr>
       
  #if($list_size!=0) 
  #set ($jumlahAduanCadangan = 0)  
  #set ($jumlahStatusAduan = 0)
  #set ($jumlahMaklumatPemohon = 0)
  #set ($jumlahStatusPermohonan = 0)
      
       #foreach($senarai in $PermohonanList)
       #set ($jumlahAduanCadangan = $jumlahAduanCadangan + $EkptgUtil.parseInt($senarai.dari_AduanCadangan))
       #set ($jumlahStatusAduan = $jumlahStatusAduan + $EkptgUtil.parseInt($senarai.dari_StatusAduan))
       #set ($jumlahMaklumatPemohon = $jumlahMaklumatPemohon + $EkptgUtil.parseInt($senarai.dari_MaklumatPemohon))
       #set ($jumlahStatusPermohonan = $jumlahStatusPermohonan + $EkptgUtil.parseInt($senarai.dari_StatusPermohonan))
              
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
          	
          <tr valign="top">
            <td class="$row" ><div align="center">$senarai.bil</div></td>
            <td class="$row">$senarai.nama_negeri</td>
            <td class="$row"><div align="center">$senarai.dari_AduanCadangan</div></td>
            <td class="$row"><div align="center">$senarai.dari_StatusAduan</div></td>
            <td class="$row"><div align="center">$senarai.dari_MaklumatPemohon</div></td>
            <td class="$row"><div align="center">$senarai.dari_StatusPermohonan</div></td>
          </tr>          
      #end 
      
    	  <tr>
          	<td colspan="11">&nbsp;</td>
          </tr>
          <tr valign="top">
            <td class="$row" ></td>
            <td class="$row"><div align="right"><strong>JUMLAH</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahAduanCadangan</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahStatusAduan</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahMaklumatPemohon</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahStatusPermohonan</strong></div></td>
          </tr>                 
        
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
    
    </div>
    <!-- END LIST OF FAIL -->
    
    
    <!-- BAR GRAPH -->
    <div class="TabbedPanelsContent">
    #if ($selectedTab=="1")
   
    $xml
     
    
     <object width="400" height="300" id="MSColumn3D" >
         <param name="movie" value="../FusionCharts/MSColumn3D.swf" />
         <param name="FlashVars" value="&dataXML=$xml&chartWidth=400&chartHeight=300">
         <param name="quality" value="high" />
         <embed src="../fusioncharts/Charts/MSColumn2D.swf" flashVars="&dataXML=$xml&chartWidth=900&chartHeight=300" quality="high" width="900" height="300" name="MSColumn3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </object>
      #end
    </div>
  
    <!-- END BAR GRAPH -->
    
    
    <!-- PIE CHART -->
<!--    <div class="TabbedPanelsContent">
    #if ($selectedTab=="2")
     <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="550" height="300" id="Pie3D" >
      <param name="movie" value="../FusionCharts/Doughnut2D.swf" />
      <param name="FlashVars" value="&dataURL=Data.xml&chartWidth=550&chartHeight=300">
      <param name="quality" value="high" />
      <embed src="../fusioncharts/Charts/Doughnut2D.swf" flashVars="&dataXML=$xml&chartWidth=550&chartHeight=300" quality="high" width="700" height="400" name="Doughnut2D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </embed>
      </object>
       #end
     </div>-->
   
    <!-- END PIE CHART -->
    
    <!-- Line Chart2 -->
    <div class="TabbedPanelsContent">
    #if ($selectedTab=="3")
     <object width="900" height="600" id="MSLine" >
      <param name="movie" value="../FusionCharts/MSLine.swf" />
      <param name="FlashVars" value="&dataURL=Data.xml&chartWidth=900&chartHeight=600">
      <param name="quality" value="high" />
      <embed src="../fusioncharts/Charts/MSLine.swf" flashVars="&dataXML=$xml&chartWidth=900&chartHeight=600" quality="high" width="900" height="600" name="MSLine" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </embed>
      </object>
       #end
     </div>
   
    <!-- END Line Chart2 -->   
    
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
	document.${formName}.socTahun.value = "";
	document.${formName}.socBulan.value = "";
	document.${formName}.id_tahun.value = "";
	document.${formName}.id_bulan.value = "";	
	document.${formName}.submit();
}

function search_data(){
	document.${formName}.command.value = "search_data";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.FrmLaporanPenggunaaneTaPPOnline";
	document.${formName}.submit();
}

function BarGraph(){
	document.${formName}.command.value = "BarGraph";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.FrmLaporanPenggunaaneTaPPOnline";
	document.${formName}.submit();
}

function PieChart(){
	document.${formName}.command.value = "PieChart";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.FrmLaporanPenggunaaneTaPPOnline";
	document.${formName}.submit();
}

function LineChart2(){
	document.${formName}.command.value = "LineChart2";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.FrmLaporanPenggunaaneTaPPOnline";
	document.${formName}.submit();
}
function Laporan(){
	document.${formName}.command.value = "Laporan";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.FrmLaporanPenggunaaneTaPPOnline";
	document.${formName}.submit();
}

function setSelected(tabid) {
	document.${formName}.tabId.value = tabid;
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});


</script>

