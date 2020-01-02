
<fieldset>
<legend>Laporan Ringkasan Transaksi FPX Bulanan dan Terkumpul</legend>

	<table width="100%" border="0">
		<tr>
			<td width="2%">&nbsp;</td>
			<td width="17%">Tahun</td>
			<td width="1%">:</td>
			<td width="80%">$!selectTahun</td>       
		</tr>	
	</table>
	
</fieldset>

	<table width="40%" border="0" align="center">
		<tr>
			<td>
				<input type=button value="Cetak" onClick="javascript:cetak();">
  				<input type=button value="Kosongkan" onClick="javascript:clearData();">
            </td>
		</tr>
	</table>

<!-- START HIDDEN VALUE -->

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- Do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<!-- Others -->
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- END HIDDEN VALUE -->



<!-- START MAIN JAVASCRIPT -->
<script>
function cetak(){

	if(document.${formName}.socTahun.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
  		document.${formName}.socTahun.focus(); 
		return;
	}else{

		var tahun = document.${formName}.socTahun.value;
		
		var url = "../servlet/ekptg.fpx.FpxReportRingkasanTransaksiClass?tahun="+tahun;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
   	 	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus(); 
	}
}
function clearData(){
	doAjaxCall${formName}("clearData");
}
</script>
<!-- END MAIN JAVASCRIPT -->



<!-- START OTHERS JAVASCRIPT -->
<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>
<!-- END OTHERS JAVASCRIPT -->