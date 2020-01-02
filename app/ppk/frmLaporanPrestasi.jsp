  
<form name="f1" method="post" >
<br/>
	<fieldset>
	<legend>Laporan Prestasi </legend>
	<p></p>
<!-- 	<fieldset>
		<table border="0" align="center" width="100%">
	      <tr> 
	        <td width="30%" height="24" scope="row" align="right">Sila Pilih&nbsp;&nbsp;</td>
	        <td width="70%" colspan="2" ><input type="radio" $checkA name="sorLaporan" value="1" Onclick="doChangeSelect()" >&nbsp;Laporan Prestasi Seksyen Pembahagian Pusaka (Ibu Pejabat)</td>
	      </tr>
	      <tr> 
	        <td scope="row" align="right">&nbsp;</td>
	        <td colspan="2" ><input type="radio" $checkB name="sorLaporan" value="2" Onclick="doChangeSelect()" >&nbsp;Laporan Prestasi Negeri Pembahagian Pusaka Kecil</td>
	      </tr>
	      <tr> 
	        <td scope="row" align="right">&nbsp;</td>
	        <td colspan="2" ><input type="radio" $checkC name="sorLaporan" value="3" Onclick="doChangeSelect()" >&nbsp;Laporan Prestasi Unit Pembahagian Pusaka Kecil</td>
	      </tr>
	      </table>

	  </fieldset> -->


		<fieldset>
			<table border="0" align="center" width="100%" >
	   	  		<tr> 
		        	<td width="30%" height="24px" scope="row" align="right" valign="top">&nbsp;</td>
		       		<td width="70%" colspan="2" >
		       			<input type="radio" name="sorTempoh" Onclick="doChangeTempoh()" value="1" $check1 $sordisabledA >&nbsp;Bulan&nbsp;&nbsp;
		       			<input type="radio" name="sorTempoh" Onclick="doChangeTempoh()" value="2" $check2 $sordisabledB >&nbsp;Tahun&nbsp;&nbsp;
		       			<input type="radio" name="sorTempoh" Onclick="doChangeTempoh()" value="3" $check3 $sordisabledC >&nbsp;Tempoh
		       		</td>
		      	</tr>	  
			</table>
		</fieldset>
<!-- 
<p/> -->
	<br/>
	<fieldset>
	<table border="0" align="center" width="100%" >      
	     <tbody> 
	<!--       <tr> 
	        <td scope="row" align="left">&nbsp;Negeri </td>
	        <td>:&nbsp;</td>
	        <td>$selectNegeri</td>
	      </tr>
	      <tr> 
	        <td scope="row" align="left">&nbsp;Unit </td>
	        <td>:&nbsp;</td>
	        <td>$selectUnit</td>
	      </tr> -->
		$selectNegeri
		$selectUnit
         
	  <tr> 
	   #if($disA=="show")
        <td scope="row" align="right">&nbsp;Bulan </td>
		<td>:&nbsp;</td>      
		<td><select name="bulan1" style="width: 100px;">
		<option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBRUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		</select>
	  	#if($disB=="show")
		&nbsp;&nbsp; &nbsp;hingga&nbsp;&nbsp;&nbsp;:&nbsp;<select name="bulan2" style="width: 100px;">	
		<option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBRUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		</select></td>
		
	  	#end	
	  #end      
	  </tr>
      
      #if($disC=="show" || ($disA=="show"))
      	<tr> 
        	<td scope="row" align="right">&nbsp;Tahun </td>
        	<td>:&nbsp;</td>
        	<td><select id="yeardropdown" name="tahun1" style="width: 100px;">
        	</select>
          #if($disB=="show")&nbsp;&nbsp;&nbsp;&nbsp;hingga&nbsp;&nbsp;&nbsp;:&nbsp;<select id="yeardropdown2" name="tahun2" style="width: 100px;">#end
			</select>
			</td>
    	</tr>
      #end
   		</tbody>	
   	</table>
	</fieldset>	

<table border="0" align="center" width="100%" >
              <tr>
                <td width="30%" >&nbsp;</td>
                <td width="70%" colspan="2"><input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPrestasi()"/></td>
              </tr> 
	<!-- <tr> 
		<td align="center"><input type="button" name="cmdCetak" value="Cetak" onclick="javascript:cetakPrestasi()"></td>
	</tr> -->
	
	<!-- <tr> 
			<td align="center"><a href=javascript:prestasiNegeriByBulanan(1,1,1,'LaporanPrestasiByNegeriBulanan')>Prestasi Negeri Bulanan</a></td>
	</tr>	<tr> 
		<td align="center"><a href=javascript:prestasiNegeriByTempoh(1,1,1,'LaporanPrestasiByNegeriTempoh')>Prestasi Negeri Mengikut Tempoh</a></td>
	</tr>	<tr> 
		<td align="center"><a href=javascript:prestasiNegeriByTahunan(1,1,1,'LaporanPrestasiByNegeriTahunan')>Prestasi Tahunan</a></td>
	</tr> -->		
</table>

</fieldset>


#if($valLaporan=="1")
	<input type="hidden" name="LcanEdit" value="byPejabat">
#elseif($valLaporan=="2")
	<input type="hidden" name="LcanEdit" value="byNegeri">
#elseif($valLaporan=="3")
	<input type="hidden" name="LcanEdit" value="byUnit">			
#else
	<input type="hidden" name="LcanEdit" value="">	
#end

#if($valTempoh=="1")
	<input type="hidden" name="TcanEdit" value="byBulan">
	<input type="hidden" name="bulan2" value="">
	<input type="hidden" name="tahun2" value="">
#elseif($valTempoh=="2")
	<input type="hidden" name="TcanEdit" value="byTahun">
	<input type="hidden" name="bulan1" value="">
	<input type="hidden" name="bulan2" value="">
	<input type="hidden" name="tahun2" value="">
#elseif($valTempoh=="3")
	<input type="hidden" name="TcanEdit" value="byTempoh">
#else
	<input type="hidden" name="TcanEdit" value="">	
#end


<input type="hidden" name="command">
<input type="hidden" name="command2">
<input type="hidden" name="command3">	
<input name="valLaporan" type="hidden" value="$valLaporan" />
</form>	
	

	
<script>
window.onload=function()
{
populatedropdown("yeardropdown")
populatedropdown2("yeardropdown2")
}
function doChangeSelect() {
	document.f1.command.value = "doChangeSelect";
	document.f1.action = "";
	document.f1.submit();
}
function doChangeTempoh() {
	document.f1.command.value = "doChangeSelect";
	document.f1.command2.value = "doChangeTempoh";
	document.f1.action = "";
	document.f1.submit();
}
function doChangeTempatBicara() {
	document.f1.command.value = "doChangeSelect";
	document.f1.command2.value = "doChangeTempoh";
	document.f1.command3.value = "doChangeTempatBicara";
	document.f1.action = "";
	document.f1.submit();
}
function populatedropdown(yearfield){
var today=new Date()
var yearfield=document.getElementById(yearfield)

var thisyear=today.getFullYear()
for (var y=0; y<20; y++){	
yearfield.options[y]=new Option(thisyear, thisyear)
thisyear-=1
}
yearfield.options[0]=new Option(today.getFullYear(), today.getFullYear(), true, true) 
}

function populatedropdown2(yearfield){
	var today=new Date()
	var yearfield=document.getElementById(yearfield)

	var thisyear=today.getFullYear()
	for (var y=0; y<20; y++){
	yearfield.options[y]=new Option(thisyear, thisyear)
	thisyear-=1
	}
	yearfield.options[0]=new Option(today.getFullYear(), today.getFullYear(), true, true) 
	}

	function cetakPrestasi() {
		var radioSelected = true;
/* 		var radioSelected = false;
		for (i = 0;  i < f1.sorLaporan.length;  i++){
			if (f1.sorLaporan[i].checked)
				radioSelected = true;
		}
 */
		var radioSelected2 = false;
		for (i = 0;  i < f1.sorTempoh.length;  i++){
			if (f1.sorTempoh[i].checked)
				radioSelected2 = true;
		}
	
		var LcanEdit = document.f1.LcanEdit.value;
		var TcanEdit = document.f1.TcanEdit.value;
		var negeri = document.f1.socNegeri.value;
		var unit = document.f1.socTempatBicara.value;
		var bulan1 = document.f1.bulan1.value;
		var bulan2 = document.f1.bulan2.value;
		var tahun1 = document.f1.tahun1.value;
		var tahun2 = document.f1.tahun2.value;
		
		if(!radioSelected){
			//sila pilih salah satu jenis laporan
			alert("Sila pilih \"Jenis Laporan\" terlebih dahulu.");
			return;	
		
		}else if(!radioSelected2){
			//sila pilih salah satu jenis laporan
			alert("Sila pilih \"bulan/tahun/tempoh\" terlebih dahulu.");
			return;	
		
		}else if(LcanEdit=="byNegeri" && (negeri=="")){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.f1.socNegeri.focus(); 
			return;		
		
		}else if(LcanEdit=="byUnit" && (negeri=="")){
			//validation negeri
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.f1.socNegeri.focus(); 
			return;	
	
		}else if(LcanEdit=="byUnit" && (unit=="")){
			//validation unit
			alert("Sila pilih \"Unit\" terlebih dahulu.");
			document.f1.socTempatBicara.focus(); 
			return;	
		
		}else if(TcanEdit=="byBulan" && (bulan1=="")){
			//validation unit
			alert("Sila pilih \"Bulan\" terlebih dahulu.");
			document.f1.bulan1.focus(); 
			return;	
		
		}else if(TcanEdit=="byBulan" && (tahun1=="")){
			//validation unit
			alert("Sila pilih \"Tahun\" terlebih dahulu.");
			document.f1.tahun1.focus(); 
			return;	
	
		}else if(TcanEdit=="byTempoh" && (bulan1=="" || bulan2=="")){
			//validation unit
			alert("Sila pilih \"Bulan\" terlebih dahulu.");
			document.f1.bulan1.focus(); 
			return;	
	
		}else{

			if(LcanEdit=="byNegeri"){
				var tahun = document.f1.tahun1.value;
				var bulan = document.f1.bulan1.value;
				var negeri = document.f1.socNegeri.value;
/* 				if(TcanEdit=="byBulan"){
					alert("cetak Prestasi Negeri BY Bulan");
					var url = "../servlet/ekptg.report.ppk.LaporanPrestasiByNegeriBulanan?tahun="+tahun+"&bulan="+bulan+"&negeri="+negeri;
				    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
				    if ((document.window != null) && (!hWnd.opener))
					hWnd.opener = document.window;
				    if (hWnd.focus != null) hWnd.focus();
				
				}else if(TcanEdit=="byTahun"){
					alert("cetak Prestasi Negeri by Tahun");	
					var negeri = document.f1.socNegeri.value;
					var tahun = document.f1.tahun1.value;
					
					var url = "../x/${securityToken}/ekptg.view.ppk.CetakLaporanPrestasi?negeri="+negeri+"&tahun="+tahun;
					var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
			  		if ((document.window != null) && (!hWnd.opener))
			  	    hWnd.opener = document.window;
			    	if (hWnd.focus != null) hWnd.focus();
					hWnd.focus();	
								    
				}else if(TcanEdit=="byTempoh"){
					var url = "../servlet/ekptg.report.ppk.LaporanPrestasiByNegeriTempoh?bulan="+bulan1+"&bulanhingga="+bulan2+"&tahun="+tahun+"&negeri="+negeri;
			    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
			    	if ((document.window != null) && (!hWnd.opener))
					hWnd.opener = document.window;
			    	if (hWnd.focus != null) hWnd.focus();	
				} */
				getJenisLaporan(LcanEdit,TcanEdit,negeri,unit,tahun,tahun2,bulan,bulan2);
		
			}else if(LcanEdit=="byPejabat"){
				var tahun = document.f1.tahun1.value;
				var bulan = document.f1.bulan1.value;
				if(document.f1.socTempatBicara.value == "0"){
					getJenisLaporan(LcanEdit,TcanEdit,negeri,unit,tahun,tahun2,bulan,bulan2);
				}else{
					getUnit(TcanEdit,negeri,unit,tahun,tahun2,bulan,bulan2) ;
				}
			
			}else if(LcanEdit=="byUnit"){
				var tahun = document.f1.tahun1.value;
				var bulan = document.f1.bulan1.value;
				var negeri = document.f1.socNegeri.value;
				var unit = document.f1.socTempatBicara.value;
				if(TcanEdit=="byBulan"){
					//alert("cetak Prestasi Negeri BY Bulan");
					var url = "../x/${securityToken}/ekptg.view.ppk.LaporanPrestasiByUnitBulanan?TAHUN_DARI="+tahun+"&BULAN_DARI="+bulan+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
					var hWnd = window.open(url,'Cetak','width=1200,height=500, resizable=yes,scrollbars=yes');
					//var url = "../servlet/ekptg.report.ppk.LaporanPrestasiByUnitBulanan?TAHUN="+tahun+"&BULAN="+bulan+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
				    //var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
				    if ((document.window != null) && (!hWnd.opener))
					hWnd.opener = document.window;
				    if (hWnd.focus != null) hWnd.focus();
				
				}else if(TcanEdit=="byTahun"){
					//alert("cetak Prestasi Negeri BY Tahun");	
					//var url = "../x/${securityToken}/ekptg.view.ppk.LaporanPrestasiByUnitTempoh?TAHUN="+tahun+"&NEGERI="+negeri+"&UNIT="+unit;
					var url = "../x/${securityToken}/ekptg.view.ppk.LaporanPrestasiByUnitTahun?TAHUN="+tahun+"&NEGERI="+negeri+"&UNIT="+unit;
					var hWnd = window.open(url,'Cetak','width=1200,height=500, resizable=yes,scrollbars=yes');
			    	if ((document.window != null) && (!hWnd.opener))
					hWnd.opener = document.window;
			    	if (hWnd.focus != null) hWnd.focus();
			    
				}else if(TcanEdit=="byTempoh"){
					//alert("cetak Prestasi Negeri BY Tempoh");	
					//var url = "../servlet/ekptg.report.ppk.LaporanPrestasiByUnitTempoh?BULAN_DARI="+bulan1+"&BULAN_HINGGA="+bulan2+"&TAHUN_DARI="+tahun1+"&TAHUN_HINGGA="+tahun2+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
			    	var url = "../x/${securityToken}/ekptg.view.ppk.LaporanPrestasiByUnitTempoh?BULAN_DARI="+bulan1+"&BULAN_HINGGA="+bulan2+"&TAHUN_DARI="+tahun1+"&TAHUN_HINGGA="+tahun2+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
					var hWnd = window.open(url,'Cetak','width=1200,height=500, resizable=yes,scrollbars=yes');
			    	if ((document.window != null) && (!hWnd.opener))
					hWnd.opener = document.window;
			    	if (hWnd.focus != null) hWnd.focus();	
			    	
				}
				
			}
			  
		}//close if
		
	}

	function getUnit(TcanEdit,negeri,unit,tahun,tahun2,bulan,bulan2) {
		var url = "";
		if(TcanEdit=="byBulan"){
			url = "../x/${securityToken}/ekptg.view.ppk.LaporanPrestasiByUnitBulanan?TAHUN_DARI="+tahun+"&BULAN_DARI="+bulan+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
		
		}else if(TcanEdit=="byTahun"){
			url = "../x/${securityToken}/ekptg.view.ppk.LaporanPrestasiByUnitTahun?TAHUN="+tahun+"&NEGERI="+negeri+"&UNIT="+unit;
  
		}else if(TcanEdit=="byTempoh"){
	    	url = "../x/${securityToken}/ekptg.view.ppk.LaporanPrestasiByUnitTempoh?BULAN_DARI="+bulan+"&BULAN_HINGGA="+bulan2+"&TAHUN_DARI="+tahun+"&TAHUN_HINGGA="+tahun2+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
				    	
		}
		
		
		var hWnd = window.open(url,'Cetak','width=1200,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();	
		
	}
	function getJenisLaporan(LcanEdit,TcanEdit,negeri,unit,tahun,tahun2,bulan,bulan2) {
		var url = "";
		var template = "ByUnit";
		if(LcanEdit=="byPejabat"){
			//template = "ByNegeri";
		}
		if(TcanEdit=="byBulan"){
			url = "../x/${securityToken}/ekptg.view.ppk.LaporanPrestasi"+template+"Bulanan?TAHUN_DARI="+tahun+"&BULAN_DARI="+bulan+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
		
		}else if(TcanEdit=="byTahun"){
			url = "../x/${securityToken}/ekptg.view.ppk.LaporanPrestasi"+template+"Tahun?TAHUN="+tahun+"&NEGERI="+negeri+"&UNIT="+unit;
  
		}else if(TcanEdit=="byTempoh"){
	    	url = "../x/${securityToken}/ekptg.view.ppk.LaporanPrestasi"+template+"Tempoh?BULAN_DARI="+bulan+"&BULAN_HINGGA="+bulan2+"&TAHUN_DARI="+tahun+"&TAHUN_HINGGA="+tahun2+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
				    	
		}
		
		
		var hWnd = window.open(url,'Cetak','width=1200,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();	
		
	}

</script>


