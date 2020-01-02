<script type="text/javascript" src="../library/js/report.js" ></script>

<!-- OPEN HAMIDAH ADD -->
<input type="hidden" name="type_chart" id="type_chart"  />
<input type="hidden" name="chart_id" id="chart_id"  />
<input type="hidden" name="xml" id="xml" value="$!xml"  />
<input type="hidden" name="chart_w" id="chart_w"  />
<input type="hidden" name="chart_h" id="chart_h"  />
<!-- CLOSE HAMIDAH ADD -->

<fieldset>
<legend>CARIAN</legend>
<table width="100%" border="0" > 
  <tr>
    <td width="21%">&nbsp;</td>
    <td width="6%"></td>
    <td width="6%"><div align="left">Laporan</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="19%"> 
	    <select style="width:130px;" id="jenis_laporan"  name="jenis_laporan" onchange="doChangeJenisLaporan(this.value)">
		    <option value = "negeri" #if($getJenisLaporan == "negeri") selected="selected" #end >NEGERI</option>
		    <option value = "kementerian" #if($getJenisLaporan == "kementerian") selected="selected" #end >KEMENTERIAN</option>
	    </select>
	    <input type="hidden" name="getJenisLaporan" id="getJenisLaporan" value="$getJenisLaporan"/>
    </td>
    <td width="4%"></td>
    <td width="1%">
    <td width="41%">
    <td width="1%">
  	<td width="0%">  
  </tr>
  <tr>
    <td width="21%">&nbsp;</td>
    <td width="6%"></td>
    <td width="6%"><div align="left">Tahun</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="19%">$selectTahun
    	<input type="hidden" name="id_tahun" id="id_tahun" value="$!id_tahun"></td>
    <td></td>
    <td><td>
    <td><td>
  </tr>
  <tr>
  	<td></td>
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
  <tr>
    <td>&nbsp;</td>
    <td></td>
    <td>Julat Tarikh Dari</td>
    <td><div align="center">:</div></td>
    <td>
	     <input name="txdTarikhMula" type="text" id="txdTarikhMula" size="10" maxlength="10" value="$!txdTarikhMula" onblur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);"  />  
	     <a href="javascript:displayDatePicker('txdTarikhMula',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>    
	     <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>     
	     Hingga
	     :
	     	    <input name="txdTarikhAkhir" type="text" id="txdTarikhAkhir" size="10" maxlength="10"  value="$!txdTarikhAkhir" onblur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);"  />  
	    <a href="javascript:displayDatePicker('txdTarikhAkhir',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>    
	    <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>   
    </td>
    <td></td>
    <td></td>
    <td> 
	 </td>
  </tr>
  <tr>
    <td width="24%">&nbsp;</td>
    <td width="6%"></td>
    <td width="7%"><div align="left" id="socNegeri1">Negeri</div></td>
    <td width="1%"><div align="center" id="socNegeri2">:</div></td>
    <td width="62%">
    	<div id="socNegeri3">$selectNegeri <input type="hidden" name="idNegeri" id="idNegeri" value="$!idNegeri"></div>
    </td>
  </tr>
  <tr>
    <td width="21%">&nbsp;</td>
    <td width="6%">&nbsp;</td>
    <td><div align="left"></div></td>
    <td><div align="center"></div></td>
    <td><input type="button" name="cmdCari" id="cmdCari" value="Cari" onClick="search_data()" />
    <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()" /></td>
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
    <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(2);PieChart()" >Doughnut Chart</li>
    <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(3);LineChart()" >Line Chart</li>
   <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:setSelected(4);PieChart2()" >Pie Chart</li>
   
   </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<!-- LIST OF FAIL -->
    <div class="TabbedPanelsContent">
    
    <fieldset>
    #if($getJenisLaporan == "kementerian")
    <legend>$!tajukLaporan</legend>
    
    <!-- OPEN HAMIDAH ADD -->
				<div id="laporan" >
                <!-- CLOSE HAMIDAH ADD -->
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
          <td width="3%" align="center" style="text-transform:uppercase" scope="row">Bil.</td>
          <td width="82%" style="text-transform:uppercase">Nama Kementerian</td>
          <td width="15%" style="text-transform:uppercase"><div align="center">Jumlah Permohonan</div></td> 
        </tr>
        
  #if($list_size!=0)
  #set ($jumlah = 0)      
       #foreach($senarai in $PermohonanList)
       #set ($jumlah = $jumlah + $EkptgUtil.parseInt($senarai.jumlah_permohonan))
       
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
          	
          <tr valign="top">
            <td class="$row" ><div align="center">$senarai.bil</div></td>
            <td class="$row">$senarai.nama_kementerian</td>
            <td class="$row"><div align="center">$senarai.jumlah_permohonan</div></td>
          </tr>          
      #end   
      	 <tr>
          	<td colspan="11">&nbsp;</td>
          </tr>
             <tr valign="top">
            <td class="$row" ></td>
            <td class="$row"><div align="right"><strong>JUMLAH</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlah</strong></div></td>
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
      
      <!-- OPEN HAMIDAH ADD -->
            </div>    
            <br />
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="printLaporan('laporan','$!tajukLaporan')" />
            <!-- CLOSE HAMIDAH ADD -->
            
    #elseif($getJenisLaporan == "negeri"||$getJenisLaporan == "")
	<legend>$!tajukLaporan</legend>
    
    <!-- OPEN HAMIDAH ADD -->
				<div id="laporan" >
                <!-- CLOSE HAMIDAH ADD -->
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
          <td width="6%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
          <td width="37%" style="text-transform:uppercase">Negeri</td>
          <td width="17%" style="text-transform:uppercase"><div align="center">Jumlah Permohonan</div></td> 
        </tr>
        
  #if($list_size!=0)    
  #set ($jumlah = 0) 
       #foreach($senarai in $PermohonanList)
       #set ($jumlah = $jumlah + $EkptgUtil.parseInt($senarai.jumlah_permohonan))
       
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
          	
          <tr valign="top">
            <td class="$row" ><div align="center">$senarai.bil</div></td>
            <td class="$row">$!senarai.nama_negeri $!senarai.nama_daerah</td>
            <td class="$row"><div align="center">$senarai.jumlah_permohonan</div></td>
          </tr>          
      #end   
      	 <tr>
          	<td colspan="11">&nbsp;</td>
          </tr>
             <tr valign="top">
            <td class="$row" ></td>
            <td class="$row"><div align="right"><strong>JUMLAH</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlah</strong></div></td>
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
      #end
      
      <!-- OPEN HAMIDAH ADD -->
            </div>    
            <br />
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="printLaporan('laporan','$!tajukLaporan')" />
            <!-- CLOSE HAMIDAH ADD -->
</fieldset>

<!--<table width="100%"  cellpadding="1" cellspacing="2" border="0">
           <tr >
            <td width="25%">&nbsp;</td>
            <td width="25%">&nbsp;</td>
             <td width="25%" >&nbsp;</td>
            <td width="25%">&nbsp;</td>
              <input type="hidden" name="paramTahun">
              <input type="hidden" name="paramBulan">
            <td width="20%"> <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="cetakLaporan()" /></td>
          </tr>  
   	</table>-->

    
    </div>
    <!-- END LIST OF FAIL -->
    
    
    <!-- BAR GRAPH -->
    <div class="TabbedPanelsContent">
    #if ($selectedTab=="1")
     <object width="400" height="300" id="Column3D" >
         <param name="movie" value="../FusionCharts/Column3D.swf" />
         <param name="FlashVars" value="&dataXML=$xml&chartWidth=400&chartHeight=300">
         <param name="quality" value="high" />
         <embed src="../fusioncharts/Charts/Column3D.swf" flashVars="&dataXML=$xml&chartWidth=900&chartHeight=300" quality="high" width="900" height="300" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </object>
      
      <!-- OPEN HAMIDAH ADD -->
            <br />
            <input type="button" onclick="cetakChart('Column3D.swf','chart$selectedTab',900,300);" id="printButton" value="Cetak Carta">
            <!-- CLOSE HAMIDAH ADD -->
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
      
      <!-- OPEN HAMIDAH ADD -->
            <br />
            <input type="button" onclick="cetakChart('Doughnut2D.swf','chart$selectedTab',700,500);" id="printButton" value="Cetak Carta">
            <!-- CLOSE HAMIDAH ADD -->
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
      <!-- OPEN HAMIDAH ADD -->
            <br />
            <input type="button" onclick="cetakChart('Line.swf','chart$selectedTab',1100,400);" id="printButton" value="Cetak Carta">
            <!-- CLOSE HAMIDAH ADD -->
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
         <!-- OPEN HAMIDAH ADD -->
            <br />
            <input type="button" onclick="cetakChart('Pie3D.swf','chart$selectedTab',700,600);" id="printButton" value="Cetak Carta">
            <!-- CLOSE HAMIDAH ADD -->
          #end
        </div>
      
    <!-- END PIE CHART2-->
    
   </div>
   
   #if ($refence=='yes')
 <!-- REFERENCE -->

    <fieldset>
       #if($getJenisLaporan=="kementerian" )
        <legend>Rujukan Kementerian</legend>
       
          <table width="60%"  cellpadding="1" cellspacing="2" border="0">
            <tr class="table_header">
              <td width="10%" style="text-transform:uppercase">Kod Kementerian</td>
              <td width="50%" style="text-transform:uppercase"><div align="center">Nama Kementerian</div></td> 
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
                <td class="$row">$SenaraiKod.kod</td>
                <td class="$row"><div align="left">$SenaraiKod.kementerian</div></td>
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
          
            #else
            
            <legend>Rujukan Negeri</legend>
       
          <table width="60%"  cellpadding="1" cellspacing="2" border="0">
            <tr class="table_header">
              <td width="10%" style="text-transform:uppercase">SINGKATAN</td>
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
            
    
    #end 
    </fieldset>  
<!-- END OF REFERENCE -->   
#end   
   
</div>
<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
<input type="hidden" id="paramUserLogin" name="paramUserLogin" value='$!{session.getAttribute("_portal_login")}' >
<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>


<script type="text/javascript">

//OPEN HAMIDAH ADD
function cetakChart(type_chart,chart_id,chart_w,chart_h)
{
document.${formName}.type_chart.value = type_chart;
document.${formName}.chart_id.value = chart_id;
document.${formName}.chart_w.value = chart_w;
document.${formName}.chart_h.value = chart_h;

//document.${formName}.xml.value = '$xml';
window.open('../app/meps/popup.jsp','popuppage','width=1100,height=600');
}


function returnDropDownSelectedText(dropdownid)
{
	//alert('dropdownid : '+dropdownid);
	var selectedText = '';
	var skillsSelect = document.getElementById(dropdownid);
	//alert(' skillsSelect : '+skillsSelect);
	if (skillsSelect) {
		//alert(skillsSelect.value);
		if(skillsSelect.value != '' && skillsSelect.value != '-1' && skillsSelect.value != '9999')	
		{
		selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
		}
	}
	//alert(' selectedText : '+selectedText);
	return selectedText;
}


function printLaporan(divName,tajuk) {
	$jquery("#"+divName+" :button").hide();
	  
	var head_style = " <head> "+
    " <style> "+
    " @media print { "+ 
	" 	body { "+ 
    "  -webkit-print-color-adjust: exact; "+  /*Chrome, Safari */
    "  color-adjust: exact;  "+  /*Firefox*/
    " 	} "+ 
	"         table { page-break-inside:auto; } "+
  	"		  tr    { page-break-inside:avoid; page-break-after:auto;  } "+	
    " } "+   
	"		  td    { border:1px solid black;font-size:75%;  } "+
    " </style> "+
	" </head> ";
	
	/*
	var socTahunDari = '$id_tahunDari';
	var socTahunHingga = '$id_tahunHingga';
	var socBulanDari = '$id_bulanDari';
	var socBulanHingga = '$id_bulanHingga';
	var socNegeri = returnDropDownSelectedText('socNegeri');
	
	alert(" socTahunDari : "+socTahunDari+" socTahunHingga : "+socTahunHingga+" socBulanDari : "+socBulanDari+" socBulanHingga : "+socBulanHingga+" socNegeri : "+socNegeri);
	
	
	var filter = ' <table width="95%" style="border-collapse:collapse;" align="center" >'+
	' <tr>';
	filter += ' <td width="29%"></td><td width="1%" align = "center" ></td><td width="70%"></td> ';
	filter += ' <td width="29%">Tahun</td><td width="1%" align = "center" >:</td><td width="70%"></td> ';
	filter += ' <td width="29%">Bulan</td><td width="1%" align = "center" >:</td><td width="70%"></td> ';
	filter += ' <td width="29%">Negeri</td><td width="1%" align = "center" >:</td><td width="70%"></td> ';
	filter += ' </tr>'+
	' </table>';
	
	*/
	
	 
	var tajuk = '<div style="width:95%;margin: auto;font-size:100%;" align="center" ><b>'+tajuk.toUpperCase()+'</b></div>'
    var printContents = document.getElementById(divName).innerHTML;
	printContents = printContents.replace("<table ", "<table style=\"border-collapse:collapse;\" ");
	printContents = printContents.replace('class="table_header"',' style="background-color:#d5d1d0;" ');
	
	//style="border-collapse:collapse;"
	var footer ="";
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
   
    popupWin.document.write('<html><body onload="window.print()"><div style="width:95%;margin: auto;"  >'+head_style+'<br>'+tajuk+'<br>'+printContents+'</div></html>');
	popupWin.document.close(); 
    return false;
}
//CLOSE HAMIDAH ADD

if(document.${formName}.jenis_laporan.value == 'kementerian'){
	document.getElementById('socNegeri1').style.display = "none";
	document.getElementById('socNegeri2').style.display = "none";
	document.getElementById('socNegeri3').style.display = "none";
}

function doChangeJenisLaporan(val){
	if(val == 'kementerian'){
		document.getElementById('socNegeri1').style.display = "none";
		document.getElementById('socNegeri2').style.display = "none";
		document.getElementById('socNegeri3').style.display = "none";
	} else {
		document.getElementById('socNegeri1').style.display = "block";
		document.getElementById('socNegeri2').style.display = "block"
			document.getElementById('socNegeri3').style.display = "block"
	}
}

function cetakLaporan() {

	var reportfile = "HTP_LaporanRingkasanTanahCerobohRizabMengikutNegeri";
	var params = new Array();
	var paramTahun= document.${formName}.paramTahun.value;
	var paramBulan = document.${formName}.paramBulan.value;
	
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "id_tahun="+document.${formName}.id_tahun.value;
	params[2] = "id_bulan="+document.${formName}.id_bulan.value;
	params[3] = "laporan="+document.${formName}.jenis_laporan.value.toUpperCase();
	params[4] = "tarikh_mula="+document.${formName}.txdTarikhMula.value;
	params[5] = "tarikh_akhir="+document.${formName}.txdTarikhAkhir.value;
	
	printReport(reportfile,params);
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.socTahun.value = "";
	document.${formName}.socBulan.value = "";
	document.${formName}.id_tahun.value = "";
	document.${formName}.id_bulan.value = "";
	document.${formName}.txdTarikhMula.value = "";
	document.${formName}.txdTarikhAkhir.value = "";
	document.${formName}.getJenisLaporan.value = "";		
	document.${formName}.submit();
}

function search_data(){
	document.${formName}.command.value = "search_data";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.HTP_LaporanRingkasanTanahCerobohRizabMengikutNegeri";
	document.${formName}.submit();
}

function BarGraph(){
	document.${formName}.command.value = "BarGraph";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.HTP_LaporanRingkasanTanahCerobohRizabMengikutNegeri";
	document.${formName}.submit();
}

function PieChart(){
	document.${formName}.command.value = "PieChart";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.HTP_LaporanRingkasanTanahCerobohRizabMengikutNegeri";
	document.${formName}.submit();
}

function LineChart(){
	document.${formName}.command.value = "LineChart";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.HTP_LaporanRingkasanTanahCerobohRizabMengikutNegeri";
	document.${formName}.submit();
}
function PieChart2(){
    document.${formName}.command.value = "PieChart2";
    document.${formName}.action = "?_portal_module=ekptg.view.meps.HTP_LaporanRingkasanTanahCerobohRizabMengikutNegeri";
    document.${formName}.submit();
}
function Laporan(){
	document.${formName}.command.value = "Laporan";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.HTP_LaporanRingkasanTanahCerobohRizabMengikutNegeri";
	document.${formName}.submit();
}

function setSelected(tabid) {
	document.${formName}.tabId.value = tabid;
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

function validateTarikh(elmnt,content) {

    //if it is character, then remove it..

    if (isNaN(content)) {

                    elmnt.value = RemoveNonNumeric2(content);

                    return;

    }

}

function RemoveNonNumeric2( strString )

{

var strValidCharacters = "1234567890/.";

var strReturn = "";

var strBuffer = "";

var intIndex = 0;

// Loop through the string

for( intIndex = 0; intIndex < strString.length; intIndex++ )

{

strBuffer = strString.substr( intIndex, 1 );

// Is this a number

if( strValidCharacters.indexOf( strBuffer ) > -1 )

{

      strReturn += strBuffer;

}

}

return strReturn;

}

</script>


