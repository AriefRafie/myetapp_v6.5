<fieldset>
<legend>CARIAN</legend>
	
	<table width="100%" border="0" >
  		<tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Negeri</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectNegeri</td>
    	</tr>
        <tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Daerah</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectDaerah</td>
    	</tr>
        <tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Mukim</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectMukim</td>
    	</tr>
    	<tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Tahun</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectTahun</td>
    	</tr>
    </table>
    	
</fieldset>

<fieldset>
	<table width="90%" border="0" >
		<tr>
  			<td align="center">
 				<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="cetak()">
    		</td>
  		</tr>
	</table>
</fieldset>

<script>
function cetak(){

	if(document.${formName}.socNegeri.value == ""){
	   	alert("Sila pilih \"Negeri\" terlebih dahulu.");
		document.${formName}.socNegeri.focus();
		return;
	}
	else if(document.${formName}.socDaerah.value == ""){
	   	alert("Sila pilih \"Daerah\" terlebih dahulu.");
		document.${formName}.socDaerah.focus();
		return;
	}
	else if(document.${formName}.socMukim.value == ""){
	   	alert("Sila pilih \"Mukim\" terlebih dahulu.");
		document.${formName}.socMukim.focus();
		return;
	}
	else if(document.${formName}.socTahun.value == ""){
	   	alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.${formName}.socTahun.focus();
		return;
	}
	else{		
	
	
	var url = "../servlet/ekptg.report.ppt.LaporanPemantauanPermohonanRujukanKeMahkamah?id_negeri="+document.${formName}.socNegeri.value+"&tahun="+document.${formName}.socTahun.value+"&id_mukim="+document.${formName}.socMukim.value+"&id_daerah="+document.${formName}.socDaerah.value; 
	
		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}

}
function doChangeNegeri() {
 	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
 	doAjaxCall${formName}("doChangeDaerah");
}
</script>
