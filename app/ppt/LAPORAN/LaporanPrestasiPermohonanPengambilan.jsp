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
    			<!--  <input type="radio" $radioA name="sorJenisTempoh" id="sorJenisTempoh" value="sehingga" onChange="doChange()" >Sehingga  -->
    			<input type="radio" $radioB name="sorJenisTempoh" id="sorJenisTempoh" value="julat" onChange="doChange()" >Julat Masa
    			<!-- <input type="radio" $radioC name="sorJenisTempoh" id="sorJenisTempoh" value="semasa" onChange="doChange()" >Tahun Semasa -->
    		</td>
    	</tr>
    
    </table>	
    
    
   
	#if($!jenisTempoh=="julat")
	<table width="100%" border="0" >	
    
    	<tr>
    		<td width="1%">&nbsp;</td>
    		<td width="20%" align="left">&nbsp;</td>
    		<td width="1%" align="center">&nbsp;</td>
    		<td>Tahun</td>
    		<td>$!selectTahun</td>
    		<td>Tahun Sehingga</td>
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
 				<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="cetak('$!jenisTempoh')">
      			<input type="button" name="cmdIsiSemula" id="cmdIsiSemula" value="Kosongkan" onClick="kosongkan()"> 
    		</td>
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
	document.${formName}.command.value = "doChange";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.LaporanPrestasiPermohonanPengambilan";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.LaporanPrestasiPermohonanPengambilan";
	document.${formName}.submit();
}
function cetak(jenisTempoh){

	if(document.${formName}.socPejabat.value == ""){
	   	alert("Sila pilih \"Pejabat JKPTG\" terlebih dahulu.");
		document.${formName}.socPejabat.focus();
		return;
	}
	
	//julat
	else if(jenisTempoh=="julat" && document.${formName}.socTahun.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahun.focus();
		return;
	}	
	else if(jenisTempoh=="julat" && document.${formName}.socTahunSehingga.value == ""){
		alert("Sila pilih \"Tahun Sehingga\" terlebih dahulu.");
		document.${formName}.socTahunSehingga.focus();
		return;
	}

	else{		
		
		var id_pejabat = document.${formName}.id_pejabat.value;
		var id_daerah = document.${formName}.id_daerah.value;
		//var bulan = document.${formName}.bulan.value;
		var tahun = document.${formName}.tahun.value;
		var tahunSE = document.${formName}.socTahunSehingga.value;
		//var bulantahun = bulan+"/"+tahun;
		
		
	    var item = "ID_PEJABAT='"+id_pejabat+"'&TAHUNSE='"+tahunSE+"'&TAHUN='"+tahun+"'&ID_DAERAH='"+id_daerah+"'&jenisTempoh='"+jenisTempoh+"'";		
	    var url = "../servlet/ekptg.report.ppt.LaporanPrestasiPermohonanPengambilan?"+item 
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

</script>
