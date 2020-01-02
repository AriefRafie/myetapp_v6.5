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
    
    <!-- OPEN HAMIDAH ADD -->
				<div id="laporan" >
                <!-- CLOSE HAMIDAH ADD -->
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
          <td width="3%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
          <td width="9%" style="text-transform:uppercase">Negeri</td>
          <td width="9%" style="text-transform:uppercase"><div align="center">PPK-Seksyen 8</div></td> 
          <td width="9%" style="text-transform:uppercase"><div align="center">PPK-Seksyen 17</div></td> 
          <td width="9%" style="text-transform:uppercase"><div align="center">PPK-Status Permohonan</div></td>
          <td width="9%" style="text-transform:uppercase"><div align="center">PPT-Bantahan Mahkamah</div></td>
          <td width="9%" style="text-transform:uppercase"><div align="center">PHP-Penyewaan</div></td>
          <td width="9%" style="text-transform:uppercase"><div align="center">PHP-APB</div></td>
          <td width="9%" style="text-transform:uppercase"><div align="center">Pembayaran Online</div></td>
          <td width="9%" style="text-transform:uppercase"><div align="center">Panduan Pengguna</div></td>
          <td width="9%" style="text-transform:uppercase"><div align="center">Aduan dan Cadangan</div></td>
          <td width="9%" style="text-transform:uppercase"><div align="center">Profil Pengguna</div></td>
        </tr>
       
  #if($list_size!=0) 
  #set ($jumlahS8 = 0)  
  #set ($jumlahS17 = 0)
  #set ($jumlahStatusPermohonan = 0)
  #set ($jumlahBantahan = 0)
  #set ($jumlahPenyewaan = 0)
  #set ($jumlahAPB = 0)
  #set ($jumlahPembayaranOnline = 0)
  #set ($jumlahPanduanPengguna = 0)
  #set ($jumlahAduanCadangan = 0)
  #set ($jumlahProfilPengguna = 0)
      
       #foreach($senarai in $PermohonanList)
       #set ($jumlahS8 = $jumlahS8 + $EkptgUtil.parseInt($senarai.dari_S8))
       #set ($jumlahS17 = $jumlahS17 + $EkptgUtil.parseInt($senarai.dari_S17))
       #set ($jumlahStatusPermohonan= $jumlahStatusPermohonan + $EkptgUtil.parseInt($senarai.dari_StatusPermohonan))
       #set ($jumlahBantahan = $jumlahBantahan + $EkptgUtil.parseInt($senarai.dari_Bantahan))
       #set ($jumlahPenyewaan = $jumlahPenyewaan + $EkptgUtil.parseInt($senarai.dari_Penyewaan))
       #set ($jumlahAPB= $jumlahAPB + $EkptgUtil.parseInt($senarai.dari_APB))
       #set ($jumlahPembayaranOnline= $jumlahPembayaranOnline + $EkptgUtil.parseInt($senarai.dari_PembayaranOnline))
       #set ($jumlahPanduanPengguna= $jumlahPanduanPengguna + $EkptgUtil.parseInt($senarai.dari_PanduanPengguna))
       #set ($jumlahAduanCadangan= $jumlahAduanCadangan + $EkptgUtil.parseInt($senarai.dari_AduanCadangan))
       #set ($jumlahProfilPengguna= $jumlahProfilPengguna + $EkptgUtil.parseInt($senarai.dari_ProfilPengguna))
              
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
          	
          <tr valign="top">
            <td class="$row" ><div align="center">$senarai.bil</div></td>
            <td class="$row">$senarai.nama_negeri</td>
            <td class="$row"><div align="center">$senarai.dari_S8</div></td>
            <td class="$row"><div align="center">$senarai.dari_S17</div></td>
            <td class="$row"><div align="center">$senarai.dari_StatusPermohonan</div></td>
            <td class="$row"><div align="center">$senarai.dari_Bantahan</div></td>
            <td class="$row"><div align="center">$senarai.dari_Penyewaan</div></td>
            <td class="$row"><div align="center">$senarai.dari_APB</div></td>
            <td class="$row"><div align="center">$senarai.dari_PembayaranOnline</div></td>
            <td class="$row"><div align="center">$senarai.dari_PanduanPengguna</div></td>
            <td class="$row"><div align="center">$senarai.dari_AduanCadangan</div></td>
            <td class="$row"><div align="center">$senarai.dari_ProfilPengguna</div></td>
          </tr>          
      #end 
      
    	  <tr>
          	<td colspan="11">&nbsp;</td>
          </tr>
          <tr valign="top">
            <td class="$row" ></td>
            <td class="$row"><div align="right"><strong>JUMLAH</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahS8</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahS17</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahStatusPermohonan</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahBantahan</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahPenyewaan</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahAPB</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahPembayaranOnline</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahPanduanPengguna</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahAduanCadangan</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlahProfilPengguna</strong></div></td>
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
            
</fieldset>
    
     <!--<table width="100%"  cellpadding="1" cellspacing="2" border="0">
           <tr >
            <td width="25%">&nbsp;</td>
            <td width="25%">&nbsp;</td>
             <td width="25%" >&nbsp;</td>
            <td width="25%">&nbsp;</td>
              <input type="hidden" name="paramTahun">
              <input type="hidden" name="paramBulan">
            <td width="20%"> <input type="button" name="cmdCetak" id="cmdCetak1" value="Cetak Laporan" onclick="cetakLaporan()" /></td>
          </tr>  
   	</table>-->
   	
    </div>
    <!-- END LIST OF FAIL -->
    
    
    <!-- BAR GRAPH -->
    <div class="TabbedPanelsContent">
    #if ($selectedTab=="1")
   
    $xml
     
    
     <object width="1330" height="600" id="MSColumn3D" >
         <param name="movie" value="../FusionCharts/MSColumn3D.swf" />
         <param name="FlashVars" value="&dataXML=$xml&chartWidth=1330&chartHeight=600">
         <param name="quality" value="high" />
         <embed src="../fusioncharts/Charts/MSColumn2D.swf" flashVars="&dataXML=$xml&chartWidth=1330&chartHeight=600" quality="high" width="1330" height="600" name="MSColumn3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </object>
      
      <!-- OPEN HAMIDAH ADD -->
            <br />
            <input type="button" onclick="cetakChart('MSColumn3D.swf','chart$selectedTab',1330,600);" id="printButton" value="Cetak Carta">
            <!-- CLOSE HAMIDAH ADD -->
            
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
     <object width="1330" height="600" id="MSLine" >
      <param name="movie" value="../FusionCharts/MSLine.swf" />
      <param name="FlashVars" value="&dataURL=Data.xml&chartWidth=1330&chartHeight=600">
      <param name="quality" value="high" />
      <embed src="../fusioncharts/Charts/MSLine.swf" flashVars="&dataXML=$xml&chartWidth=1330&chartHeight=600" quality="high" width="1330" height="600" name="MSLine" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </embed>
      </object>
      
      <!-- OPEN HAMIDAH ADD -->
            <br />
            <input type="button" onclick="cetakChart('MSLine.swf','chart$selectedTab',1330,600);" id="printButton" value="Cetak Carta">
            <!-- CLOSE HAMIDAH ADD -->
            
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
              <!--<td width="10%" style="text-transform:uppercase">Abbrev</td>-->
              <td width="50%" colspan="2" style="text-transform:uppercase"><div align="center">Nama Negeri</div></td> 
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

function cetakLaporan() {

	var reportfile = "FrmLaporanPenggunaaneTaPPOnline";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "id_tahun="+document.${formName}.id_tahun.value;
	params[2] = "id_bulan="+document.${formName}.id_bulan.value;
	
	printReport(reportfile,params);
}


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

