<fieldset>
<legend>CARIAN</legend>
<table width="100%" border="0" >  
  <tr>
    <td width="23%">&nbsp;</td>
    <td width="7%"></td>
    <td width="7%"><div align="left">Tahun</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="19%">$selectTahun
    <input type="hidden" name="id_tahun" id="id_tahun" value="$!id_tahun"></td>
    <td width="6%"></td>
    <td width="2%">
    <td width="31%">
    <td width="2%">
    <td width="2%">  </tr>
  
  <tr>
  <td>
  </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td></td>
    <td>Bulan</td>
    <td><div align="center">:</div></td>
    <td>$selectBulan<input type="hidden" name="id_bulan" id="id_bulan" value="$!id_bulan"></td>
    <td>&nbsp;</td>
    <td>&nbsp;<td>
    <td>&nbsp;<td>
  </tr>
  
    <td>&nbsp;</td>
    <td></td>
    <td>Julat Tarikh Dari</td>
    <td><div align="center">:</div></td>
     <td >

    

     <input name="txdTarikhMula" type="text" id="txdTarikhMula" size="10"
maxlength="10" value="$!txdTarikhMula"
onblur="validateTarikh(this,this.value);"
onKeyUp="validateTarikh(this,this.value);"  />  

          

     <a
href="javascript:displayDatePicker('txdTarikhMula',false,'dmy');"><img
src="../img/calendar.gif" alt="" border="0"/></a>    

         <span
style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>     

      
    </td>

     <td> Hingga</td>
      <td>:</td>
       <td> 

     <input name="txdTarikhAkhir" type="text" id="txdTarikhAkhir" size="10"
maxlength="10"  value="$!txdTarikhAkhir"
onblur="validateTarikh(this,this.value);"
onKeyUp="validateTarikh(this,this.value);"  />  

          

     <a
href="javascript:displayDatePicker('txdTarikhAkhir',false,'dmy');"><img
src="../img/calendar.gif" alt="" border="0"/></a>    

         <span
style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>     

      
 </td>
  </tr>
  <tr>
    <td width="23%">&nbsp;</td>
    <td width="7%">&nbsp;</td>
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
    <!--li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(1);BarGraph()" >Bar Chart</li-->
    <!--li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(2);PieChart()" >Doughnut Chart</li-->
    <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(1);BarGraph2()" >Bar Chart</li-->
    <!--li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(4);PieChart2()" >Pie Chart</li -->  
    <!--li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(5);LineChart()" >Line Chart</li-->
    <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(2);LineChart2()" >Line Chart</li>
    <!-- li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(7);AreaChart()" >Area Chart</li -->  
  </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<!-- LIST OF FAIL -->
    <div class="TabbedPanelsContent">
    
    <fieldset>
	<legend>$!tajukLaporan</legend>
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
          <td width="6%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
          <td width="44%" style="text-transform:uppercase">Negeri</td>
          <td width="25%" style="text-transform:uppercase"><div align="center">Jumlah Permohonan</div></td> 
          <td width="25%" style="text-transform:uppercase"><div align="center">Jumlah Hakmilik</div></td> 
        </tr>
        
  #if($list_size!=0)    
  #set ($jumlah = 0)
  #set ($jumlahHM = 0)
  
       #foreach($senarai in $PermohonanList)
       #set ($jumlah = $jumlah + $EkptgUtil.parseInt($senarai.jumlah_permohonan))
       #set ($jumlahHM = $jumlahHM + $EkptgUtil.parseInt($senarai.jumlah_hakmilik))
       
               	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
          	
          <tr valign="top">
            <td class="$row" ><div align="center">$senarai.bil</div></td>
            <td class="$row">$senarai.nama_negeri</td>
            <td class="$row"><div align="center">$senarai.jumlah_permohonan</div></td>
            <td class="$row"><div align="center">$senarai.jumlah_hakmilik</div></td>
        </tr>          
      #end   
      
    	  <tr>
          	<td colspan="11">&nbsp;</td>
          </tr>
             <tr valign="top">
            <td class="$row" ></td>
            <td class="$row"><div align="right"><strong>JUMLAH</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlah</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahHM</strong></div></td>
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
   
  
    <!-- PIE CHART -->
   
   
    <!-- END PIE CHART -->
    
    <!-- END BAR GRAPH -->
    
    <!-- BAR GRAPH2 -->
    <div class="TabbedPanelsContent">
    #if ($selectedTab=="1")    
    <object width="900" height="400" id="MSColumn3D" >
         <param name="movie" value="../FusionCharts/MSColumn3D.swf" />
         <param name="FlashVars" value="&dataXML=$xml&chartWidth=900&chartHeight=400">
         <param name="quality" value="high" />
         <embed src="../fusioncharts/Charts/MSColumn2D.swf" flashVars="&dataXML=$xml&chartWidth=900&chartHeight=400" quality="high" width="900" height="400" name="MSColumn3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </object>
      #end
    </div>
  
    <!-- END BAR GRAPH -->
    
    <!--PIE CHART2   --> 
      
      
    <!-- END PIE CHART2-->
    
    <!-- Line Chart -->
   
    <!-- END Line Chart -->
    <!-- Line Chart2 -->
    <div class="TabbedPanelsContent">
    #if ($selectedTab=="2")
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
    <!-- Area Chart >
    <div class="TabbedPanelsContent">
    #if ($selectedTab=="7")
     <object width="900" height="900" id="MSArea" >
      <param name="movie" value="../FusionCharts/MSArea.swf" />
      <param name="FlashVars" value="&dataURL=Data.xml&chartWidth=800&chartHeight=900">
      <param name="quality" value="high" />
      <embed src="../fusioncharts/Charts/MSArea.swf" flashVars="&dataXML=$xml&chartWidth=800&chartHeight=900" quality="high" width="800" height="900" name="MSArea" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </embed>
      </object>
       #end
     </div>
   
    < END Area Chart --> 
  </div>
   
           #if ($refence=='yes')
 <!-- REFERENCE -->

    <fieldset>
        <legend>Rujukan Negeri</legend>
        
          <table width="60%"  cellpadding="1" cellspacing="2" border="0">
            <tr class="table_header">
              <td width="10%" style="text-transform:uppercase">Rujukan</td>
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
	document.${formName}.txdTarikhMula.value = "";
	document.${formName}.txdTarikhAkhir.value = "";	
	document.${formName}.submit();
}

function search_data(){
	document.${formName}.command.value = "search_data";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanTahunanPengambilanTanah";
	document.${formName}.submit();
}

function BarGraph(){
	document.${formName}.command.value = "BarGraph";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanTahunanPengambilanTanah";
	document.${formName}.submit();
}

function PieChart(){
	document.${formName}.command.value = "PieChart";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanTahunanPengambilanTanah";
	document.${formName}.submit();
}

function BarGraph2(){
	document.${formName}.command.value = "BarGraph2";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanTahunanPengambilanTanah";
	document.${formName}.submit();
}

function PieChart2(){
	document.${formName}.command.value = "PieChart2";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanTahunanPengambilanTanah";
	document.${formName}.submit();
}

function LineChart(){
	document.${formName}.command.value = "LineChart";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanTahunanPengambilanTanah";
	document.${formName}.submit();
}
function LineChart2(){
	document.${formName}.command.value = "LineChart2";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanTahunanPengambilanTanah";
	document.${formName}.submit();
}
function AreaChart(){
	document.${formName}.command.value = "AreaChart";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanTahunanPengambilanTanah";
	document.${formName}.submit();
}
function Laporan(){
	document.${formName}.command.value = "Laporan";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanTahunanPengambilanTanah";
	document.${formName}.submit();
}

function setSelected(tabid) {
	document.${formName}.tabId.value = tabid;
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});


</script>

