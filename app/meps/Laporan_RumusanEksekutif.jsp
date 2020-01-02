<script type="text/javascript" src="../library/js/report.js" ></script>

<!-- OPEN HAMIDAH ADD -->
<input type="hidden" name="type_chart" id="type_chart"  />
<input type="hidden" name="chart_id" id="chart_id"  />
<input type="hidden" name="xml" id="xml" value="$!xml"  />
<input type="hidden" name="chart_w" id="chart_w"  />
<input type="hidden" name="chart_h" id="chart_h"  />
<!-- CLOSE HAMIDAH ADD -->


<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onClick="javascript:selectedTab(0);PengambilanTanah()" >Pengambilan Tanah</li>
    <li class="TabbedPanelsTab" tabindex="1" onClick="javascript:selectedTab(1);htp()" >Harta Tanah Persekutuan</li>
    <li class="TabbedPanelsTab" tabindex="2" onClick="javascript:selectedTab(2);php()" >Penguatkuasa dan Hasil Persekutuan</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<!-- LIST OF FAIL -->
    <div class="TabbedPanelsContent">

<br>
    <fieldset>
	<legend>Ringkasan Permohonan Pengambilan Tanah</legend>
    
    <!-- OPEN HAMIDAH ADD -->
				<div id="laporan" >
                <!-- CLOSE HAMIDAH ADD -->
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
          <td width="5%" rowspan="2" align="center" style="text-transform:uppercase" scope="row">Bil</td>
          <td width="15%" rowspan="2" style="text-transform:uppercase">Negeri</td>
          <td colspan="2" style="text-transform:uppercase"><div align="center">jumlah</div></td>
          <td width="20%" rowspan="2" style="text-transform:uppercase"><div align="center">jumlah</div></td>
        </tr>
        <tr class="table_header">
          <td width="30%" style="text-transform:uppercase"><div align="center">kutipan data</div></td> 
          <td width="30%" style="text-transform:uppercase"><div align="center">permohonan baru</div></td>
        </tr>
        
  #if($list_size!=0)  
  #set ($jumlahByNegeri = 0)
  #set ($jumlaKeseluruhan = 0)
   
    #foreach($senarai in $PermohonanList)
    #set ($jumlahByNegeri = $EkptgUtil.parseInt($senarai.jumlah_kutipandata) + $EkptgUtil.parseInt($senarai.jumlah_permohonanbaru))         
    #set ($jumlaKeseluruhan = $jumlaKeseluruhan + $jumlahByNegeri)
    
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
          	
          <tr valign="top">
            <td class="$row" ><div align="center">$senarai.bil</div></td>
            <td class="$row">$senarai.nama_negeri</td>
            <td class="$row"><div align="center">$senarai.jumlah_kutipandata</div></td>
            <td class="$row"><div align="center">$senarai.jumlah_permohonanbaru</div></td>
            <td class="$row"><div align="center">$jumlahByNegeri</div></td>
          </tr>          
      #end     
      
    	  <tr>
          	<td colspan="11">&nbsp;</td>
          </tr>
          <tr valign="top">
            <td class="$row" ></td>
            <td class="$row">&nbsp;</td>
            <td class="$row">&nbsp;</td>
            <td class="$row"><div align="center"><strong>JUMLAH</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlaKeseluruhan</strong></div></td>
          </tr>        
               
      
<!--    	  <tr>
          	<td colspan="5">&nbsp;</td>
    	  </tr>-->
          
   	  #else  
<!--   		  <tr>
        	<td colspan="5">Tiada rekod</td>
          <tr> --> 
   	  #end  
      </table>
      
      <!-- OPEN HAMIDAH ADD -->
            </div>    
            <br />
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="printLaporan('laporan','Ringkasan Permohonan Pengambilan Tanah')" />
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
<br>
    <fieldset>
	<legend>Ringkasan Tanah Pesuruhjaya Tanah Persekutuan</legend>
    
    <!-- OPEN HAMIDAH ADD -->
				<div id="laporan1" >
                <!-- CLOSE HAMIDAH ADD -->
                
    <table width="60%"  cellpadding="1" cellspacing="2" border="0"> 
    #foreach($senaraiHTP in $PermohonanListHTP)         
        <tr valign="top">
          <td>&nbsp;</td>
          <td>Jumlah Tanah Milik</td>
          <td><div align="center">:</div></td>
          <td>$senaraiHTP.jumlah_tanahmilik</td>
        </tr>
     ##end     
     ##foreach($senaraiRizab in $PermohonanListRizab)  
        <tr valign="top">
          <td width="7%" >&nbsp;</td>
          <td width="31%">Jumlah Tanah Rizab</td>
          <td width="2%"><div align="center">:</div></td>
          <td width="60%"><div align="left">$senaraiHTP.jumlah_tanahrizab</div></td>
        </tr>
     #end      
    </table>
    
    <!-- OPEN HAMIDAH ADD -->
            </div>    
            <br />
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="printLaporan1('laporan1','Ringkasan Tanah Pesuruhjaya Tanah Persekutuan')" />
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
            <td width="20%"> <input type="button" name="cmdCetak" id="cmdCetak1" value="Cetak Laporan" onclick="cetakLaporan1()" /></td>
          </tr>  
   	</table>-->
    
    </div> 
    <!-- END BAR GRAPH -->
    
    
    <!-- PIE CHART -->
    <div class="TabbedPanelsContent">
<br>
    <fieldset>
	<legend>Ringkasan Permohonan bagi Penguatkuasa dan Hasil Persekutuan</legend>
    
     <!-- OPEN HAMIDAH ADD -->
				<div id="laporan2" >
                <!-- CLOSE HAMIDAH ADD -->
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
          <td width="11%" rowspan="2" align="center" style="text-transform:uppercase" scope="row">Bil</td>
          <td width="27%" rowspan="2" style="text-transform:uppercase">Urusan</td>
          <td colspan="2" style="text-transform:uppercase"><div align="center">jumlah</div></td>
          <td width="18%" rowspan="2" style="text-transform:uppercase"><div align="center">jumlah</div></td>
        </tr>
        <tr class="table_header">
          <td width="21%" style="text-transform:uppercase"><div align="center">kutipan data</div></td> 
          <td width="23%" style="text-transform:uppercase"><div align="center">permohonan baru</div></td>
        </tr>
        
  #if($list_sizePHP!=0)  
  #set ($jumlahByUrusan = 0) 
  #set ($jumlaKeseluruhanPHP = 0)
    
    #foreach($senaraiPHP in $PermohonanListPHP)
    #set ($jumlahByUrusan = $EkptgUtil.parseInt($senaraiPHP.fail_kutipan) + $EkptgUtil.parseInt($senaraiPHP.fail_baru))
    
    #set ($jumlaKeseluruhanPHP = $jumlaKeseluruhanPHP + $jumlahByUrusan)
       
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
          	
          <tr valign="top">
            <td class="$row" ><div align="center">$!senaraiPHP.bil</div></td>
            <td class="$row">$!senaraiPHP.nama_urusan</td>
            <td class="$row"><div align="center">$!senaraiPHP.fail_kutipan</div></td>
            <td class="$row"><div align="center">$!senaraiPHP.fail_baru</div></td>
            <td class="$row"><div align="center">$!jumlahByUrusan</div></td>
          </tr>          
      #end      
      
    	  <tr>
          	<td colspan="11">&nbsp;</td>
          </tr>
          <tr valign="top">
            <td class="$row" ></td>
            <td class="$row">&nbsp;</td>
            <td class="$row">&nbsp;</td>
            <td class="$row"><div align="center"><strong>JUMLAH</strong></div></td>
            <td class="$row"><div align="center"><strong>$jumlaKeseluruhanPHP</strong></div></td>
          </tr>                      
      
<!--    	  <tr>
          	<td colspan="5">&nbsp;</td>
    	  </tr>-->
          
   	  #else  
<!--   		  <tr>
        	<td colspan="5">Tiada rekod</td>
          <tr> --> 
   	  #end  
      </table>
      
      <!-- OPEN HAMIDAH ADD -->
            </div>    
            <br />
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="printLaporan2('laporan2','Ringkasan Permohonan bagi Penguatkuasa dan Hasil Persekutuan')" />
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
            <td width="20%"> <input type="button" name="cmdCetak" id="cmdCetak2" value="Cetak Laporan" onclick="cetakLaporan2()" /></td>
          </tr>  
   	</table>-->
   	
    </div>
    <!-- END PIE CHART -->
  </div>
   
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

function printLaporan1(divName,tajuk) {
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

function printLaporan2(divName,tajuk) {
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

	var reportfile = "RingkasanPermohonanPengambilanTanah";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	
	printReport(reportfile,params);
}

function cetakLaporan1() {

	var reportfile = "RingkasanTanahPesuruhjayaTanahPersekutuan";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	
	printReport(reportfile,params);
}

function cetakLaporan2() {

	var reportfile = "RingkasanPermohonanBagiPenguatkuasaDanHasilPersekutuan";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	
	printReport(reportfile,params);
}

function htp(){
	document.${formName}.command.value = "htp";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.rumusan_eksekutif";
	document.${formName}.submit();
}

function php(){
	document.${formName}.command.value = "php";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.rumusan_eksekutif";
	document.${formName}.submit();
}

function PengambilanTanah(){
	document.${formName}.command.value = "PengambilanTanah";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.rumusan_eksekutif";
	document.${formName}.submit();
}

function selectedTab(tabid) {
	document.${formName}.tabId.value = tabid;
}

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

</script>

