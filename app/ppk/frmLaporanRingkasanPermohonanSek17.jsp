<br>
<center>



<fieldset>
<legend>Laporan Ringkasan Permohonan Seksyen 17</legend>

	<table border="0" align="center" width="100%">
		<tr>
			<td width="18%">&nbsp;</td>
			<td width="1%" valign="top"><font color="red">*</font></td>
			<td width="12%">Negeri</td>
			<td width="1%">:&nbsp;</td>
			<td width="68%">$!selectNegeri</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td valign="top"><font color="red">*</font></td>
			<td>Unit</td>
			<td>:&nbsp;</td>
			<td>$!selectUnit</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td valign="top"><font color="red">*</font></td>
			<td>Daerah</td>
			<td>:&nbsp;</td>
			<td>$!selectDaerah</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td valign="top"><font color="red">*</font></td>
			<td>Tahun</td>
			<td>:&nbsp;</td>
			<td>$!selectTahun</td>
		</tr>
	</table>

</fieldset>


<fieldset>

	<table border="0" align="center" width="100%">
		<tr> 
			<td align="center"><input type="button" name="cmdCetak" value="Cetak" onclick="javascript:cetak()"></td>
		</tr>
	</table>

</fieldset>






<script>
function doChangeUnit() {
	doAjaxCall${formName}("doChangeUnit");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function cetak() {

	var negeri = document.${formName}.socNegeri.value;
	var unit = document.${formName}.socUnit.value;
	var daerah = document.${formName}.socDaerah.value;
	var tahun = document.${formName}.socTahun.value;

	
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socUnit.value == ""){
		alert('Sila pilih Unit.');
  		document.${formName}.socUnit.focus(); 
		return; 
	}
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih Daerah.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}
	if(document.${formName}.socTahun.value == ""){
		alert('Sila pilih Tahun.');
  		document.${formName}.socTahun.focus(); 
		return; 
	}
	
	var url = "../servlet/ekptg.report.ppk.LaporanRingkasanPermohonanSek17?idnegeri="+negeri+"&idunit="+unit+"&iddaerah="+daerah+"&tahun="+tahun;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>



</center>