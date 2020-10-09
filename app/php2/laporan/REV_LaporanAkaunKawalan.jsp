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
          <td width="30%" align="right"><span class="style1">*</span> Kategori Laporan : </td>
          <td width="70%"><select name="idKategori" id="idKategori" onChange="javascript:doChangeKategori()">
              <option value="">SILA PILIH</option>
              <option value="1" #if($!idKategori == '1') selected #end >BULANAN</option>
              <option value="2" #if($!idKategori == '2') selected #end >TAHUNAN</option>
            </select></td>
        </tr>
        #if($!idKategori == '1')
        <tr>
          <td align="right"><span class="style1">*</span> Bulan : </td>
          <td>$!selectBulan</td>
        </tr>
        <tr>
          <td align="right"><span class="style1">*</span> Tahun : </td>
          <td><input type="text" name="tahun" id="tahun" value="$tahun" onBlur="validateNumber(this,this.value);" size="4" maxlength="4"></td>
        </tr>
        #end
        #if($!idKategori == '2')
        <tr>
          <td align="right"><span class="style1">*</span> Tahun : </td>
          <td><input type="text" name="tahun" id="tahun" value="$tahun" onBlur="validateNumber(this,this.value);" size="4" maxlength="4"></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdJana" id="button" value="Jana Laporan" onclick="janaLaporan()">
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doChangeKategori() {
	doAjaxCall${formName}("doChangeKategori");
}
</script>
<script>
function janaLaporan() {
	var idKategori = document.${formName}.idKategori.value;
	var bulan = "";
	var tahun = "";
	
	if(idKategori == ""){
		alert('Sila pilih Kategori Laporan.');
  		document.${formName}.idKategori.focus(); 
		return; 
	}	
	
	else if (idKategori == '1') {
		bulan = document.${formName}.socBulan.value;
		tahun = document.${formName}.tahun.value;
		
		if(bulan == ""){
			alert('Sila pilih Bulan.');
  			document.${formName}.socBulan.focus(); 
			return; 
		}
		
		if(tahun == ""){
			alert('Sila pilih Tahun.');
  			document.${formName}.tahun.focus(); 
			return; 
		}	
		
		var url = "../servlet/ekptg.report.php2.REVLaporanAkaunKawalanBulanan?BULAN="+bulan+"&TAHUN="+tahun;
	
	} else if (idKategori == '2') {
		tahun = document.${formName}.tahun.value;
		
		if(tahun == ""){
			alert('Sila pilih Tahun.');
  			document.${formName}.socTahun.focus(); 
			return; 
		}
		
		var url = "../servlet/ekptg.report.php2.REVLaporanAkaunKawalanTahunan?TAHUN="+tahun;
	}	
	
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
