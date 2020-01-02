<fieldset>
<legend>CARIAN</legend>
	
	<table width="100%" border="0" >
  		<tr>
    		<td width="1%"><font color="red">*</font></td>
    		<td width="20%" align="left">Negeri</td>
    		<td width="1%" align="center">:</td>
    		<td width="78%">$!selectNegeri</td>
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
	
	else{		

		var url = "../servlet/ekptg.report.ppt.LaporanPenarikanBalik?id_negeri="+document.${formName}.socNegeri.value; 
		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}

}

</script>
