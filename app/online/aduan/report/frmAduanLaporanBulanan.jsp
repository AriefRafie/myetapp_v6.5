<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>LAPORAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="30%" align="right"><span class="style1">*</span> Tahun : </td>
          <td width="70%"><input type="text" name="txtTahun" id="txtTahun" value="$txtTahun" onblur="validateLength(this.value);" size="4" maxlength="4"></td>
        </tr>
        <tr>
          <td align="right"> <span class="style1">*</span> Bulan : </td>
          <td>$selectBulan
          <input type="hidden" name="id_bulan" id="id_bulan" value="$!id_bulan"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdJana" id="button" value="Jana Laporan" onclick="janaLaporan()">
            <input type="reset" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosong()">
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakAduanTerimaSelesai()"> Jumlah Aduan yang Diterima Dan Diselesaikan </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSumberAduan()"> Sumber Penerimaan Aduan </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakTempohAduan()"> Tempoh Penyelesaian Aduan </a></td>
  </tr>
</table>
</fieldset>
<script>
function validateLength(str) {	
	if (str.length < 4) {
 		alert("Sila Masukan Tahun Dengan Betul.")
		document.${formName}.txtTahun.value = "";
		return false
	}
}
function janaLaporan(){	
	if(document.${formName}.txtTahun.value == ""){
		alert('Sila masukkan Tahun.');
  		document.${formName}.txtTahun.focus(); 
		return; 
	}
	if(document.${formName}.socBulan.value == ""){
		alert('Sila pilih Bulan.');
  		document.${formName}.socBulan.focus(); 
		return; 
	}
	setTable('tableReport');
}
function kosong(){	
	document.${formName}.reset();
	document.${formName}.txtTahun.value = "";
	document.${formName}.socBulan.value = "";
	document.${formName}.submit();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakAduanTerimaSelesai() {
	var bulan = document.${formName}.socBulan.value;
	if (bulan.length == 1) {
	    bulan = "0" + bulan;
	}
	var url = "../servlet/ekptg.report.online.aduan.AduanDiterimaSelesaiBulanan?TAHUN="+document.${formName}.txtTahun.value+"&BULAN="+bulan;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSumberAduan() {
	var bulan = document.${formName}.socBulan.value;
	if (bulan.length == 1) {
	    bulan = "0" + bulan;
	}
	var url = "../servlet/ekptg.report.online.aduan.SumberPenerimaanAduanBulanan?TAHUN="+document.${formName}.txtTahun.value+"&BULAN="+bulan;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakTempohAduan() {
	var bulan = document.${formName}.socBulan.value;
	if (bulan.length == 1) {
	    bulan = "0" + bulan;
	}
	var url = "../servlet/ekptg.report.online.aduan.TempohSelesaiAduanBulanan?TAHUN="+document.${formName}.txtTahun.value+"&BULAN="+bulan;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
