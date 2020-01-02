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
        <td ><font color="red">*</font></td>
    		<td align="left">Julat Tahun</td>
    		<td align="center">:</td>
        <td>
        <table width="100%" border="0" >	
    	<tr>
    		
    		<td>Dari Tahun</td>
    		<td>$!selectTahun</td>
    		<td>Sehingga Tahun</td>
    		<td>$!selectTahunSehingga</td>
    	</tr>
        </table>
        </td>
        </tr>
        </table>
    	
    	
	
</fieldset>

<fieldset>
	<table width="90%" border="0" >
		<tr>
  			<td align="center">
 				<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />  
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
        <a href="javascript:cetakBorangK()" class="style2" ><font color="blue">Senarai Projek Yang Dikeluarkan Borang K</font></a></td>
      </tr>      
      <tr>
        <td>  
      
        <a href="javascript:cetakBorangKNegeri()" class="style2" ><font color="blue">Senarai Projek Yang Dikeluarkan Borang K Mengikut Daerah</font></a></td>
      </tr>       
      
    </table>
</fieldset>


<!-- Hidden -->
<input type="hidden" name="id_pejabat" value="$!id_pejabat">
<input type="hidden" name="id_daerah" value="$!id_daerah">
<input type="hidden" name="tahun" value="$!tahun">
<input type="hidden" name="bulan" value="$!bulan">
<input type="hidden" name="jenisTempoh" value="$!jenisTempoh">
<input type="hidden" name="tahunSE" value="$!tahunSE">
<input type="hidden" name="bulanSE" value="$!bulanSE">

<script>
function doChange() {
	//document.${formName}.command.value = "doChange";
	//document.${formName}.action = "?_portal_module=ekptg.view.ppt.LaporanPenarikanBalik";
	//document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.LaporanEndorsanK";
	document.${formName}.submit();
}
function cetakBorangK(){

	if(document.${formName}.socPejabat.value == ""){
	   	alert("Sila pilih \"Pejabat JKPTG\" terlebih dahulu.");
		document.${formName}.socPejabat.focus();
		return;
	
	}else if(document.${formName}.socTahun.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahun.focus();
		return;
	
	}else if(document.${formName}.socTahunSehingga.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahunSehingga.focus();
		return;
	}
	else{
		var id_negeri = document.${formName}.socPejabat.value;
		var mula_tahun = (document.${formName}.socTahun.value);
		var akhir_tahun = document.${formName}.socTahunSehingga.value;
		
	
	
		var item = "ID_NEGERI="+id_negeri+"&MULA_TAHUN="+mula_tahun+"&AKHIR_TAHUN="+akhir_tahun;		
	 	//alert("item :"+item);
			var url = "../servlet/ekptg.report.ppt.LaporanKeluarBorangK?"+item 
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		    hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
	
      		   	    
	}

}


function cetakBorangKNegeri(){

	if(document.${formName}.socPejabat.value == ""){
	   	alert("Sila pilih \"Pejabat JKPTG\" terlebih dahulu.");
		document.${formName}.socPejabat.focus();
		return;
	
	}else if(document.${formName}.socTahun.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahun.focus();
		return;
	
	}else if(document.${formName}.socTahunSehingga.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahunSehingga.focus();
		return;
	}
	else{
		var id_negeri = document.${formName}.socPejabat.value;
		var mula_tahun = (document.${formName}.socTahun.value);
		var akhir_tahun = document.${formName}.socTahunSehingga.value;
	
		var item = "ID_NEGERI="+id_negeri+"&MULA_TAHUN="+mula_tahun+"&AKHIR_TAHUN="+akhir_tahun;		
	 	
			var url = "../servlet/ekptg.report.ppt.LaporanStatusEndosK?"+item 
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
		window.location.hash=id;
        goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

</script>
