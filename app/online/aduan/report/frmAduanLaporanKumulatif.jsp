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
          <td width="70%"><input type="text" name="txtTahun" id="txtTahun" value="$txtTahun" onblur="validateLength(this.value);" size="4" maxlength="4" ></td>
        </tr>
        <tr>
          <td align="right"> <span class="style1">*</span> Bulan : </td>
          <td>$selectBulanMula Hingga         $selectBulanAkhir</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdJana" id="button" value="Jana Laporan" onClick="janaLaporan()">
            <input type="reset" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="kosong()">
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
	if(document.${formName}.socBulanMula.value == ""){
		alert('Sila pilih Bulan Mula.');
  		document.${formName}.socBulanMula.focus(); 
		return; 
	}
	if(document.${formName}.socBulanAkhir.value == ""){
		alert('Sila pilih Bulan Hingga.');
  		document.${formName}.socBulanAkhir.focus(); 
		return; 
	}
	setTable('tableReport');
}
function kosong(){	
	document.${formName}.reset();
	document.${formName}.txtTahun.value = "";
	document.${formName}.socBulanMula.value = "";
	document.${formName}.socBulanAkhir.value = "";
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
	var bulanMula = document.${formName}.socBulanMula.value;
	if (bulanMula.length == 1) {
	    bulanMula = "0" + bulanMula;
	}
	var bulanAkhir = document.${formName}.socBulanAkhir.value
	if (bulanAkhir.length == 1) {
	    bulanAkhir = "0" + bulanAkhir;
	}
 	var url = "../servlet/ekptg.report.online.aduan.AduanDiterimaSelesaiKumulatif?TAHUN="+document.${formName}.txtTahun.value+"&BULAN_MULA="+bulanMula+"&BULAN_AKHIR="+bulanAkhir;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSumberAduan() {
	var bulanMula = document.${formName}.socBulanMula.value;
	if (bulanMula.length == 1) {
	    bulanMula = "0" + bulanMula;
	}
	var bulanAkhir = document.${formName}.socBulanAkhir.value
	if (bulanAkhir.length == 1) {
	    bulanAkhir = "0" + bulanAkhir;
	}
	var url = "../servlet/ekptg.report.online.aduan.SumberPenerimaanAduanKumulatif?TAHUN="+document.${formName}.txtTahun.value+"&BULAN_MULA="+bulanMula+"&BULAN_AKHIR="+bulanAkhir;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakTempohAduan() {
	var bulanMula = document.${formName}.socBulanMula.value;
	if (bulanMula.length == 1) {
	    bulanMula = "0" + bulanMula;
	}
	var bulanAkhir = document.${formName}.socBulanAkhir.value
	if (bulanAkhir.length == 1) {
	    bulanAkhir = "0" + bulanAkhir;
	}
	var url = "../servlet/ekptg.report.online.aduan.TempohSelesaiAduanKumulatif?TAHUN="+document.${formName}.txtTahun.value+"&BULAN_MULA="+bulanMula+"&BULAN_AKHIR="+bulanAkhir;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
