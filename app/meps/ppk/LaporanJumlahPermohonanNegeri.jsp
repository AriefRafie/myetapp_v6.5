<script type="text/javascript" src="../../library/js/report.js" ></script>

<!-- OPEN HAMIDAH ADD -->
<input type="hidden" name="type_chart" id="type_chart"  />
<input type="hidden" name="chart_id" id="chart_id"  />
<input type="hidden" name="xml" id="xml" value="$!xml"  />
<input type="hidden" name="chart_w" id="chart_w"  />
<input type="hidden" name="chart_h" id="chart_h"  />
<!-- CLOSE HAMIDAH ADD -->

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
        <td valign="top" >
        Negeri
        </td>
        <td valign="top" >
        :
        </td>
        <td >
        
            <select id="ID_NEGERI"  name="ID_NEGERI"
            onChange = "doChangeNegeri('');"
            >	   
             <option value = "" >SILA PILIH</option>
                    #foreach ( $ruj in $list_TBLRUJNEGERI )	                         	
                        #set ( $selected_ruj = "" )
                        #if($ID_NEGERI==$ruj.ID)
                        #set ( $selected_ruj = "selected" )
                        #end	                                
                            <option $selected_ruj value="$ruj.ID" >
                                $ruj.KETERANGAN
                            </option>
                    #end
            </select>
        </td>
    </tr>
    
    <tr>
        <td width="24%">&nbsp;</td>
        <td width="6%"></td>			
        <td valign="top" >
        Unit / Pejabat
        </td>
        <td valign="top" >
        :
        </td>
        <td id="div_ID_PEJABATJKPTG$internalType$viewPengguna.USER_ID" >
            <select id="ID_PEJABATJKPTG"  name="ID_PEJABATJKPTG">	   
               <option value = "" >SILA PILIH</option>
                #foreach ( $ruj in $listPejabatJKPTG )		
                    #set ( $selected_ruj = "" )
                    #if($ID_PEJABATJKPTG==$ruj.ID_PEJABATJKPTG)
                    #set ( $selected_ruj = "selected" )
                    #end		
                    <option $selected_ruj value="$ruj.ID_PEJABATJKPTG" >
                    $ruj.NAMA_UNIT
                    </option>
                #end
            </select>
        </td>	
    </tr>

   <tr>
    <td width="24%">&nbsp;</td>
    <td width="6%"></td>
    <td width="7%"><div align="left">Permohonan</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="62%">
    	$!selectSuburusan
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
    <td width="6%"></td>
    <td width="7%"><div align="left">Bulan</div></td>
    <td width="1%"><div align="center">&nbsp;</div></td>
    <td width="62%">Dari&nbsp;:&nbsp;$!selectBulan&nbsp;&nbsp;Sehingga&nbsp;:&nbsp;$!selectBulanHingga
    	<input type="hidden" name="id_bulan" id="id_bulan" value="$!id_bulan">
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
          <td width="3%" align="center" style="text-transform:uppercase" scope="row">Bil.</td>
          <td width="81%" style="text-transform:uppercase">Tahun</td>
          <td width="16%" style="text-transform:uppercase"><div align="center">Permohonan</div></td> 
          <!-- <td width="16%" style="text-transform:uppercase"><div align="center">Waris</div></td> -->
        </tr>
        
#if($list_size!=0)    
  	#set ($jumlah1 = 0)
  	#set ($jumlah2 = 0)
  
	#foreach($senarai in $PermohonanList)
    	#set ($jumlah1 = $jumlah1 + $EkptgUtil.parseInt($senarai.jumlah1))
    	#set ($jumlah2 = $jumlah2 + $senarai.jumlah2)
       
       	#set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        	#set( $row = "row2" )
        #else
        	 #set( $row = "row1" )
        #end
          	
          <tr valign="top">
            <td class="$row"><div align="center">$!senarai.bil.</div></td>
            <td class="$row">$!senarai.tahun</td>
            <td class="$row"><div align="right">$EkptgUtil.formatNumberWithoutDecimal($EkptgUtil.parseInt($!senarai.jumlah1))</div></td>
            <!-- 
            <td class="$row"><div align="right">$!senarai.jumlah2</div></td> 
            -->
          </tr>          
      #end   
      
    	  <tr>
          	<td colspan="3">&nbsp;</td>
          </tr>
       	  <tr valign="top" class="table_header">
            <td colspan=2><div align="right"><strong>JUMLAH</strong></div></td>
            <td><div align="right"><strong>$EkptgUtil.formatNumberWithoutDecimal($!jumlah1)</strong></div></td>
            <!-- <td><div align="right"><strong>$!jumlah2</strong></div></td> -->
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
              <input type="hidden" name="paramTahunDari">
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
         <embed src="../fusioncharts/Charts/Column3D.swf" flashVars="&dataXML=$xml&chartWidth=1000&chartHeight=300" quality="high" width="1000" height="300" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      </object>
      
      <!-- OPEN HAMIDAH ADD -->
            <br />
            <input type="button" onclick="cetakChart('Column3D.swf','chart$selectedTab',900,300);" id="printButton" value="Cetak Carta">
            <!-- CLOSE HAMIDAH ADD -->
      
      <!--<table width="100%"  cellpadding="1" cellspacing="2" border="0">
           <tr >
            <td width="25%">&nbsp;</td>
            <td width="25%">&nbsp;</td>
             <td width="20%" >&nbsp;</td>
            <td width="20%"> <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="cetakLaporan()" /></td>
          </tr>  
   	</table>-->
      #end
    </div>
  
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
      
      <!--<table width="100%"  cellpadding="1" cellspacing="2" border="0">
           <tr >
            <td width="21%">&nbsp;</td>
            <td width="20%"> <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="cetakLaporan()" /></td>
          </tr>  
   	</table>-->
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
      
      <!--<table width="100%"  cellpadding="1" cellspacing="2" border="0">
           <tr >
            <td width="25%">&nbsp;</td>
            <td width="20%">&nbsp;</td>
            <td width="20%"> <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="cetakLaporan()" /></td>
          </tr>  
   	</table>-->
       #end
     </div>
   
    <!-- END Line Chart -->

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
		</td>
	<tr/>
</table>
<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
<input type="hidden" id="paramUserLogin" name="paramUserLogin" value='$!{session.getAttribute("_portal_login")}' >
<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>

<script type="text/javascript">

function doChangeNegeri()
{
	//alert('doChangeNegeri');
	document.${formName}.command.value = "doFilter";
	document.${formName}.submit();
}

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
	var reportfile = "PPKLaporanPermohonanNegeri";
	var params = new Array();
	
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "txt_userLogin="+document.${formName}.paramUserLogin.value;
	params[2] = "tahun_dari="+document.${formName}.socTahun.value;
	params[3] = "tahun_hingga="+document.${formName}.socTahunHingga.value;
	params[4] = "bulan_dari="+document.${formName}.socBulan.value;
	params[5] = "bulan_hingga="+document.${formName}.socBulanHingga.value;
	params[6] = "id_suburusan="+document.${formName}.socSuburusan.value;
	
	printReport (reportfile,params);
}

	function kosongkan() {
		document.${formName}.reset();
		document.${formName}.socTahun.value = "";
		document.${formName}.socTahunHingga.value = "";
		document.${formName}.socBulan.value = "";
		document.${formName}.socBulanHingga.value = "";
		document.${formName}.socSuburusan.value = "";
		document.${formName}.ID_NEGERI.value = "";
		document.${formName}.ID_PEJABATJKPTG.value = "";
		document.${formName}.submit();
// 		doAjaxCall${formName}("");

	}

	function search_data(){
		doAjaxCall${formName}("search_data");
	}

	function BarGraph(){
		//document.${formName}.command.value = "BarGraph";
		//document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanJumlahPengambilanTanahMengikutLot";
		//document.${formName}.submit();
		doAjaxCall${formName}("BarGraph");
	}

	function PieChart(){
		//document.${formName}.command.value = "PieChart";
		//document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanJumlahPengambilanTanahMengikutLot";
		//document.${formName}.submit();
		doAjaxCall${formName}("PieChart");
	}

	function BarGraph2(){
		document.${formName}.command.value = "BarGraph2";
		document.${formName}.action = "?_portal_module=ekptg.view.meps.PPKLaporanJumlahHartaNegeri";
		document.${formName}.submit();
		//doAjaxCall${formName}("BarGraph2");
	}

function PieChart2(){
	document.${formName}.command.value = "PieChart2";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanJumlahPengambilanTanahMengikutLot";
	document.${formName}.submit();
}

	function LineChart(){
		//document.${formName}.command.value = "LineChart";
		//document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanJumlahPengambilanTanahMengikutLot";
		//document.${formName}.submit();
		doAjaxCall${formName}("LineChart");
	}
	
function LineChart2(){
	document.${formName}.command.value = "LineChart2";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanJumlahPengambilanTanahMengikutLot";
	document.${formName}.submit();
}
function AreaChart(){
	document.${formName}.command.value = "AreaChart";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanJumlahPengambilanTanahMengikutLot";
	document.${formName}.submit();
}
	function Laporan(){
		//document.${formName}.command.value = "Laporan";
		//document.${formName}.action = "?_portal_module=ekptg.view.meps.PPT_LaporanJumlahPengambilanTanahMengikutLot";
		//document.${formName}.submit();
		doAjaxCall${formName}("Laporan");
		//doAjaxCall${formName}("");
	}

function setSelected(tabid) {
	document.${formName}.tabId.value = tabid;
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});


</script>

