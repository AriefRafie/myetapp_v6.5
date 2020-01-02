<fieldset>
<legend>CARIAN</legend>
	
	<table width="100%" border="0" >

  		<tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Nama Pejabat JKPTG</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectPejabat</td>
    	</tr>
    	
    	<tr>
    		<td><font color="red">*</font></td>
    		<td align="left">Daerah</td>
    		<td align="center">:</td>
    		<td>$!selectDaerah</td>
    	</tr>
    	
    	  <tr>
    	  	 <td width="1%"></td>
             <td align="left">No.Fail JKPTG</td>
             <td align="center">:</td>
             <td><input type = "text" id = "no_fail" name = "no_fail" size="60" maxlength="4000"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
         	 
         	 
             <!-- shaz -->
            
             <!-- elly -->
             <input type="hidden" name="id_fail" />
             
             <!-- razman -->
			 <input type="hidden" name="id_permohonan" />			
             <input type="hidden" name="flagAdvSearch" value="$flagAdvSearch"/>
             <input type="hidden" name="flag_MyInfoPPT"/></td>
            </tr>
        
        <tr>
    		<td></td>
    		<td align="left">Nama Projek</td>
    		<td align="center">:</td>
    		<td>
            	<input type = "text" id = "projek" name = "projek" size="60" maxlength="4000"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
            </td>
    	</tr>
    	
    	#if($jenisTempoh=="sehingga")
    		#set($radioA="checked")
    		#set($radioB="")
    		#set($radioC="")
    	#elseif($jenisTempoh=="julat")
    		#set($radioA="")
    		#set($radioB="checked")
    		#set($radioC="")
    	#else
    		#set($radioA="")
    		#set($radioB="")
    		#set($radioC="checked")
    	#end
    	
    	<tr>
    		<td><font color="red">*</font></td>
    		<td align="left">Jenis Tempoh Tarikh Permohonan</td>
    		<td align="center">:</td>
    		<td>
    			<input type="radio" $radioA name="sorJenisTempoh" id="sorJenisTempoh" value="sehingga" onChange="doChange()" >Sehingga
    			<input type="radio" $radioB name="sorJenisTempoh" id="sorJenisTempoh" value="julat" onChange="doChange()" >Julat Masa
    			<input type="radio" $radioC name="sorJenisTempoh" id="sorJenisTempoh" value="semasa" onChange="doChange()" >Tahun Semasa
    		</td>
    	</tr>
    
    </table>	
    
    
    <!-- SEHINGGA -->
    #if($!jenisTempoh=="sehingga")
    <table width="100%" border="0" >	
    	<tr>
    		<td width="1%">&nbsp;</td>
    		<td width="20%" align="left">&nbsp;</td>
    		<td width="1%" align="center">&nbsp;</td>
    		<td width="7%">Bulan</td>
    		<td width="71%">$!selectBulan</td>
    	</tr>
    
    	<tr>
    		<td>&nbsp;</td>
    		<td align="left">&nbsp;</td>
    		<td align="center">&nbsp;</td>
    		<td>Tahun</td>
    		<td>$!selectTahun</td>
    	</tr>
	</table>
	
	#elseif($!jenisTempoh=="julat")
	<table width="100%" border="0" >	
    	<tr>
    		<td width="1%">&nbsp;</td>
    		<td width="20%" align="left">&nbsp;</td>
    		<td width="1%" align="center">&nbsp;</td>
    		<td width="7%">Bulan</td>
    		<td width="22%">$!selectBulan</td>
    		<td width="8%" rowspan="2"><b>Sehingga</b></td>
    		<td width="7%">Bulan</td>
    		<td width="34%">$!selectBulanSehingga</td>
    	</tr>
    
    	<tr>
    		<td>&nbsp;</td>
    		<td align="left">&nbsp;</td>
    		<td align="center">&nbsp;</td>
    		<td>Tahun</td>
    		<td>$!selectTahun</td>
    		<td>Tahun</td>
    		<td>$!selectTahunSehingga</td>
    	</tr>
	</table>
	
	#else
	<input type="hidden" name="socBulan" value="$!bulan">
	<input type="hidden" name="socTahun" value="$!tahun">
	#end
    
    
	
</fieldset>

<fieldset>
	<table width="90%" border="0" >
		<tr>
  			<td align="center">
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" /> 
 				<!-- <input type="button" name="cmdCetak" id="cmdCetak" value="" onClick="cetak('$!jenisTempoh')"> -->
      			<input type="button" name="cmdIsiSemula" id="cmdIsiSemula" value="Kosongkan" onClick="kosongkan()"> 
    		</td>
  		</tr>
	</table>
</fieldset>


<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2"> 
      <tr>
        <td>  
        <a href="javascript:cetak('$!jenisTempoh')" class="style2" ><font color="blue">Lampiran A</font></a></td>
      </tr>    
      <tr>
        <td>  
        <a href="javascript:cetaksek4('$!jenisTempoh')" class="style2" ><font color="blue">Lampiran Seksyen 4</font></a></td>
      </tr>      
      <tr>
        <td>  
      
        <a href="javascript:cetakRingkasan('$!jenisTempoh')" class="style2" ><font color="blue">Laporan Ringkasan Projek
 </font></a></td>
      </tr>       
      
    </table>
</fieldset>


<div id="div_LampiranAhtml">

</div>

<script>
/*
  $jquery(document).ready(function () {
	 doDivAjaxCall$formname('div_LampiranAhtml','showHtmlReport','');			 	  
  });
  */
</script>


<!-- Hidden -->
<input type="hidden" name="id_pejabat" value="$!id_pejabat">
<input type="hidden" name="id_daerah" value="$!id_daerah">
<input type="hidden" name="tahun" value="$!tahun">
<input type="hidden" name="bulan" value="$!bulan">
<input type="hidden" name="jenisTempoh" value="$!jenisTempoh">
<input type="hidden" name="tahunSE" value="$!tahunSE">
<input type="hidden" name="bulanSE" value="$!bulanSE">

<script>
function printLaporan(divName,header) {
	//var originalContents = document.body.innerHTML;
    $jquery("#"+divName+" :button").hide();
	//$jquery("#divLampiran_"+id_aduanpublic).hide();  
	//$jquery("#divKronologi_"+id_aduanpublic).hide();
	//$jquery("#divDisplayAlert_"+id_aduanpublic).hide();
	  
	var head_style = " <head> "+
    " <style> "+
    " @media print { "+ 
	" 	body { "+ 
    "  -webkit-print-color-adjust: exact; "+  /*Chrome, Safari */
    "  color-adjust: exact;  "+  /*Firefox*/
    " 	} "+ 
	"         table { page-break-inside:auto } "+
  	"		  tr    { page-break-inside:avoid; page-break-after:auto;  } "+
    " } "+   
    " </style> "+
	" </head> ";
	  
	//var style = '<link rel="stylesheet" type="text/css" media="screen, print" href="../css/eTapp_PDT.css" />'
	var style = '';
    var printContents = document.getElementById(divName).innerHTML;
	//printContents = printContents.replace(/background-color:yellow/g , "background-color:white;");
	//.replace("background-color:yellow", "background-color:");	
    var footer ="";
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
   
    popupWin.document.write('<html><body onload="window.print()">'+head_style+style+'<br>'+printContents + '<br></html>');
	//class="page-break"
    popupWin.document.close(); 
    //elementHide.style.display = "none";
	//$jquery("#"+divName+" :button").show();    
    //document.body.innerHTML = originalContents;
    return false;
}

function doChange() {
	document.${formName}.command.value = "doChange";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.LaporanBulananProjek";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.LaporanBulananProjek";
	document.${formName}.submit();
}


function cetaksek4(jenisTempoh){
	//alert("masuk");

	if(document.${formName}.socPejabat.value == ""){
	   	alert("Sila pilih \"Pejabat JKPTG\" terlebih dahulu.");
		document.${formName}.socPejabat.focus();
		return;
	//Sehingga	
	}else if((jenisTempoh=="sehingga" || jenisTempoh=="julat") && document.${formName}.socBulan.value == ""){
		alert("Sila pilih \"Bulan\" terlebih dahulu.");
		document.${formName}.socBulan.focus();
		return;

	}else if((jenisTempoh=="sehingga" || jenisTempoh=="julat") && document.${formName}.socTahun.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahun.focus();
		return;
	//julat	
	}else if(jenisTempoh=="julat" && document.${formName}.socBulanSehingga.value == ""){
		alert("Sila pilih \"Bulan\" terlebih dahulu.");
		document.${formName}.socBulanSehingga.focus();
		return;

	}else if(jenisTempoh=="julat" && document.${formName}.socTahunSehingga.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahunSehingga.focus();
		return;

	}else{		

		var bulanSE = "00";
		var bulanSE2 = "00";
		var tahunSE = "";
		var bulantahunSE = "";
		var type = "";
		if(jenisTempoh=="julat"){
			bulanSE = parseInt(document.${formName}.socBulanSehingga.value);
			bulanSE2 = parseInt(document.${formName}.socBulanSehingga.value);
			tahunSE = document.${formName}.socTahunSehingga.value;
			bulantahunSE = bulanSE+"/"+tahunSE
			type = "2";
			
		}else if(jenisTempoh=="sehingga"){
			type = "1";
		}else{
			type = "3";
		}
	
		var id_pejabat = document.${formName}.id_pejabat.value;
		var id_daerah = document.${formName}.id_daerah.value;
		var bulan = document.${formName}.bulan.value;
		var tahun = document.${formName}.tahun.value;
		var projek = document.${formName}.projek.value;
		var no_fail = document.${formName}.no_fail.value;
	
		
		//alert("JGCGHSCGHVDH :"+projek.trim());		
		if(projek!=""){
			projek = document.${formName}.projek.value;
		}else{		
			//projek = "NONE";	
			projek = "";	
		}
		if(no_fail!=""){
			no_fail = document.${formName}.no_fail.value;
		}else{		
			//projek = "NONE";	
			no_fail = "";	
		}
		
		// kemaskini oleh Mohamd Rosli pada 25/07/2013
		var template = "Laporan_Allsek4";	
		//alert(template);
		if(id_daerah != '000')
			template = "LaporanBulananPengambilanTanahMengikutProjekDaerahSek4";
		else	
			template = "LaporanBulananPengambilanTanahMengikutProjekNegeriSek4";

		if(type != '1' && type != '3'){
			template += "Selang";
			if(bulanSE2 =='00')
				bulanSE2 = bulan;
			if(tahunSE == '')
				tahunSE = tahun;
		}
		else if(type == '3'){
			
			var d = new Date();
			var n = d.getFullYear();
			var m = d.getMonth() + 1 
			
			var tahun = '';
			
			if(tahun == '')
				tahun = n;
			
			if(tahunSE == '')
				tahunSE = n;
			
			if(bulanSE == '' || '00')
				bulanSE = m;
			
			//alert(bulanSE);
			//alert(tahunSE);
			
		}
		//alert(jenisTempoh);
		//alert(template);
		var bulantahun = bulan+"/"+tahun;			
		var item = "template="+template+"&ID_PEJABAT="+id_pejabat+"&BULANSE="+bulanSE2+"&TAHUNSE="+tahunSE+"&BULAN="+bulan+"&type="+type+"&TAHUN="+tahun+"&bulantahun='"+bulantahun+"'&ID_DAERAH="+id_daerah+"&jenisTempoh='"+jenisTempoh+"'&bulantahunSE='"+bulantahunSE+"'"+"&projek="+projek+"&no_fail="+no_fail+"";		
		var url = "../servlet/ekptg.report.ppt.LaporanBulananProjekSek4?"+item 
		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}

}


	function cetak(jenisTempoh){

		if(document.${formName}.socPejabat.value == ""){
		   	alert("Sila pilih \"Pejabat JKPTG\" terlebih dahulu.");
			document.${formName}.socPejabat.focus();
			return;
		//Sehingga	
		}else if((jenisTempoh=="sehingga" || jenisTempoh=="julat") && document.${formName}.socBulan.value == ""){
			alert("Sila pilih \"Bulan\" terlebih dahulu.");
			document.${formName}.socBulan.focus();
			return;
	
		}else if((jenisTempoh=="sehingga" || jenisTempoh=="julat") && document.${formName}.socTahun.value == ""){
			alert("Sila pilih \"Tahun\" terlebih dahulu.");
			document.${formName}.socTahun.focus();
			return;
		//julat	
		}else if(jenisTempoh=="julat" && document.${formName}.socBulanSehingga.value == ""){
			alert("Sila pilih \"Bulan\" terlebih dahulu.");
			document.${formName}.socBulanSehingga.focus();
			return;
	
		}else if(jenisTempoh=="julat" && document.${formName}.socTahunSehingga.value == ""){
			alert("Sila pilih \"Tahun\" terlebih dahulu.");
			document.${formName}.socTahunSehingga.focus();
			return;
	
		}else{		

			var bulanSE = "00";
			var bulanSE2 = "00";
			var tahunSE = "";
			var bulantahunSE = "";
			var type = "";
			if(jenisTempoh=="julat"){
				bulanSE = parseInt(document.${formName}.socBulanSehingga.value);
				bulanSE2 = parseInt(document.${formName}.socBulanSehingga.value);
				tahunSE = document.${formName}.socTahunSehingga.value;
				bulantahunSE = bulanSE+"/"+tahunSE
				type = "2";
				
			}else if(jenisTempoh=="sehingga"){
				type = "1";
			}else{
				type = "3";
			}
		
			var id_pejabat = document.${formName}.id_pejabat.value;
			var id_daerah = document.${formName}.id_daerah.value;
			var bulan = document.${formName}.bulan.value;
			var tahun = document.${formName}.tahun.value;
			var projek = document.${formName}.projek.value;
			var no_fail = document.${formName}.no_fail.value;
		
			//alert("JGCGHSCGHVDH :"+projek.trim());		
			if(projek!=""){
				projek = document.${formName}.projek.value;
			}else{		
				//projek = "NONE";	
				projek = "";	
			}	
			
			if(no_fail!=""){
				no_fail = document.${formName}.no_fail.value;
			}else{		
				//projek = "NONE";	
				no_fail = "";	
			}	
			// kemaskini oleh Mohamd Rosli pada 25/07/2013
			var template = "Laporan_All";	
			//alert(template);
			if(id_daerah != '000')
				template = "LaporanBulananPengambilanTanahMengikutProjekDaerah";
			else	
				template = "LaporanBulananPengambilanTanahMengikutProjekNegeri";

			if(type != '1' && type != '3'){
				template += "Selang";
				if(bulanSE2 =='00')
					bulanSE2 = bulan;
				if(tahunSE == '')
					tahunSE = tahun;
			}
			else if(type == '3'){
				
				var d = new Date();
				var n = d.getFullYear();
				var m = d.getMonth() + 1 
				
				var tahun = '';
				
				if(tahun == '')
					tahun = n;
				
				if(tahunSE == '')
					tahunSE = n;
				
				if(bulanSE == '' || '00')
					bulanSE = m;
				
				//alert(bulanSE);
				//alert(tahunSE);
				
			}
			//alert(jenisTempoh);
			//alert(template);
			var bulantahun = bulan+"/"+tahun;			
			var item = "template="+template+"&ID_PEJABAT="+id_pejabat+"&BULANSE="+bulanSE2+"&TAHUNSE="+tahunSE+"&BULAN="+bulan+"&type="+type+"&TAHUN="+tahun+"&bulantahun='"+bulantahun+"'&ID_DAERAH="+id_daerah+"&jenisTempoh='"+jenisTempoh+"'&bulantahunSE='"+bulantahunSE+"'"+"&projek="+projek+"&no_fail="+no_fail+"";				
			var url = "../servlet/ekptg.report.ppt.LaporanBulananProjek?"+item 
			var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		    
		}
	
	}

	function cetakRingkasan(jenisTempoh){

		if(document.${formName}.socPejabat.value == ""){
	   		alert("Sila pilih \"Pejabat JKPTG\" terlebih dahulu.");
			document.${formName}.socPejabat.focus();
			return;
			
		}	
		//Sehingga	
		else if((jenisTempoh=="sehingga" || jenisTempoh=="julat") && document.${formName}.socBulan.value == ""){
			alert("Sila pilih \"Bulan\" terlebih dahulu.");
			document.${formName}.socBulan.focus();
			return;
			
		}else if((jenisTempoh=="sehingga" || jenisTempoh=="julat") && document.${formName}.socTahun.value == ""){
			alert("Sila pilih \"Tahun\" terlebih dahulu.");
			document.${formName}.socTahun.focus();
			return;

		}
		//julat
		else if(jenisTempoh=="julat" && document.${formName}.socBulanSehingga.value == ""){
			alert("Sila pilih \"Bulan\" terlebih dahulu.");
			document.${formName}.socBulanSehingga.focus();
			return;
		}else if(jenisTempoh=="julat" && document.${formName}.socTahunSehingga.value == ""){
			alert("Sila pilih \"Tahun\" terlebih dahulu.");
			document.${formName}.socTahunSehingga.focus();
			return;

		}else{		

			var bulanSE = "";
			var bulanSE2 = "";
			var tahunSE = "";
			var bulantahunSE = "";
			var type = "";
			var template = "LaporanRingkasanProjekMain";
			if(jenisTempoh=="julat"){
				bulanSE = parseInt(document.${formName}.socBulanSehingga.value);
				bulanSE2 = parseInt(document.${formName}.socBulanSehingga.value);
				tahunSE = document.${formName}.socTahunSehingga.value;
				bulantahunSE = bulanSE+"/"+tahunSE
				type = "2";
				//template += "Selang";
					
			}else if(jenisTempoh=="sehingga"){
				type = "1";									
			}else{
				//template += "Selang";
				type = "3";
			}
			var id_pejabat = document.${formName}.id_pejabat.value;
			var id_daerah = document.${formName}.id_daerah.value;
			var bulan = document.${formName}.bulan.value;
			var tahun = document.${formName}.tahun.value;
			var projek = document.${formName}.projek.value;	
			var no_fail = document.${formName}.no_fail.value;

			if(id_daerah != '000')
				template += "Daerah";
			else	
				template += "Negeri";

			if(type == '2'){
			
				if(bulanSE2 =='00')
					bulanSE2 = bulan;
				if(tahunSE == '')
					tahunSE = tahun;
			}
			else if(type == '3'){
				
				var d = new Date();
				var n = d.getFullYear();
				var m = d.getMonth() + 1 
				
				var tahun = '';
				
				if(tahun == '')
					tahun = n;
				
				if(tahunSE == '')
					tahunSE = n;
				
				if(bulanSE == '' || '00')
					bulanSE = m;
				
				//alert(bulanSE);
				//alert(tahunSE);
				
			}
			
			if(projek!=""){
				projek = document.${formName}.projek.value;
			}else{		
				//projek = "NONE";	
				projek = "";	
			}	
			
			if(no_fail!=""){
				no_fail = document.${formName}.no_fail.value;
			}else{		
				//projek = "NONE";	
				no_fail = "";	
			}	
			var bulantahun = bulan+"/"+tahun;
			
			var item = "template="+template+"&ID_PEJABAT="+id_pejabat+"&BULANSE="+bulanSE+"&TAHUNSE="+tahunSE+"&BULAN="+bulan+"&type="+type+"&TAHUN="+tahun+"&bulantahun='"+bulantahun+"'&ID_DAERAH="+id_daerah+"&jenisTempoh='"+jenisTempoh+"'&bulantahunSE='"+bulantahunSE+"'"+"&projek="+projek+"&no_fail="+no_fail+"";			
			var url = "../servlet/ekptg.report.ppt.LaporanRingkasanProjek?"+item 
			var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		    
		}
	
	}


function showDaerah(){
 document.${formName}.command.value = "daerah";
 document.${formName}.submit();

}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		//window.location.hash=id;
        //goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}


</script>
