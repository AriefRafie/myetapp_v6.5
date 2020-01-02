<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>LAPORAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="30%" align="right"><span class="style1">*</span> Tahun</td>
          <td width="70%"><input type="text" name="txtTahun" id="txtTahun" value="$txtTahun" onBlur="validateNumber(this,this.value);" size="4" maxlength="4"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdJana" id="button" value="Jana Laporan" onclick="janaLaporan()">
            <input type="reset" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan">
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function janaLaporan(){	
	if(document.${formName}.txtTahun.value == ""){
		alert('Sila masukkan Tahun.');
  		document.${formName}.txtTahun.focus(); 
		return; 
	}
	cetakPLPTKRIkutTahun();
}

function cetakPLPTKRIkutTahun() {
	var url = "../servlet/ekptg.report.php2.PLPTKRLaporanPermohonanMengikutTahun?TAHUN="+document.${formName}.txtTahun.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
