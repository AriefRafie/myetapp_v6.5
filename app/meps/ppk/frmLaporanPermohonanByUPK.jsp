<script type="text/javascript" src="../library/js/report.js" ></script>

<!-- OPEN HAMIDAH ADD -->
<input type="hidden" name="type_chart" id="type_chart"  />
<input type="hidden" name="chart_id" id="chart_id"  />
<input type="hidden" name="xml" id="xml" value="$!xml"  />
<input type="hidden" name="chart_w" id="chart_w"  />
<input type="hidden" name="chart_h" id="chart_h"  />
<!-- CLOSE HAMIDAH ADD -->


<p></p>
<fieldset>
<legend>Senarai Permohonan Mengikut Unit Permohonan Pusaka Kecil Negeri</legend>

<!-- OPEN HAMIDAH ADD -->
	<div id="laporan" >
<!-- CLOSE HAMIDAH ADD -->
                
<table width="100%"  cellpadding="1" cellspacing="2" border="0">
  <tr class="table_header">
    <td width="5%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
    <td width="80%" style="text-transform:uppercase">Negeri</td>
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
	    <td class="row2" ><div align="center"><strong>$senarai.bil</strong></div></td>
	    <td class="row2"><strong>$senarai.nama_negeri</strong></td>
	    <td class="row2"><div align="center"><strong>$EkptgUtil.formatNumberWithoutDecimal($EkptgUtil.parseInt($senarai.jumlah_permohonan))</strong></div></td>
	  </tr>
	  #foreach($upk in $upklist)          
		  #if($upk.ID_NEGERI == $senarai.ID_NEGERI )
		  <tr valign="top">
		    <td class="row1" ><div align="center"></div></td>
		    <td class="row1">$upk.NAMA_PEJABAT</td>
		    <td class="row1"><div align="center">$EkptgUtil.formatNumberWithoutDecimal($EkptgUtil.parseInt($upk.BILANGAN_PERMOHONAN))</div></td>
		  </tr>
		  #end
	  #end
  #end
  <tr>
    <td colspan="11">&nbsp;</td>
  </tr>
  <tr valign="top">
    <td class="row2" ></td>
    <td class="row2"><div align="right"><strong>JUMLAH</strong></div></td>
    <td class="row2"><div align="center"><strong>$EkptgUtil.formatNumberWithoutDecimal($jumlah)</strong></div></td>
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
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="printLaporan('laporan','Senarai Permohonan Mengikut Unit Permohonan Pusaka Kecil Negeri')" />
            <!-- CLOSE HAMIDAH ADD -->
            
</fieldset>

    
    	<!-- <table width="100%"  cellpadding="1" cellspacing="2" border="0">
           <tr >
            <td width="25%">&nbsp;</td>
            <td width="25%">&nbsp;</td>
             <td width="25%" >&nbsp;</td>
            <td width="25%">&nbsp;</td>
              <input type="hidden" name="paramTahun">
              <input type="hidden" name="paramBulan">
            <td width="20%"> <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="cetakLaporan()" /></td>
          </tr>  
   	</table> -->
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
	var reportfile = "PPK_LaporanPermohonanByUPK";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	
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
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPK_FrmLaporanJumPermohonanS8";
	document.${formName}.submit();
}

function BarGraph(){
	document.${formName}.command.value = "BarGraph";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPK_FrmLaporanJumPermohonanS8";
	document.${formName}.submit();
}

function PieChart(){
	document.${formName}.command.value = "PieChart";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPK_FrmLaporanJumPermohonanS8";
	document.${formName}.submit();
}

function Laporan(){
	document.${formName}.command.value = "Laporan";
	document.${formName}.action = "?_portal_module=ekptg.view.meps.PPK_FrmLaporanJumPermohonanS8";
	document.${formName}.submit();
}

function setSelected(tabid) {
	document.${formName}.tabId.value = tabid;
}

</script>
