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
          <td width="30%" align="right"><span class="style1">*</span> Luas Dari</td>
          <td><input name="txtLuasDari" type="text" id="txtLuasDari" onkeyup="validateNumber(this,this.value);"> Kilometer Persegi</td>
        </tr>
         <tr>
          <td width="30%" align="right"><span class="style1">*</span> Luas Hingga</td>
          <td><input name="txtLuasHingga" type="text" id="txtLuasHingga" onkeyup="validateNumber(this,this.value);"> Kilometer Persegi</td>
        </tr>
        <tr>
          <td width="30%" align="right">Tahun</td>
          <td width="70%"><input type="text" name="txtTahun" id="txtTahun" value="$txtTahun" onBlur="validateNumber(this,this.value);" size="4" maxlength="4"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdJana" id="button" value="Jana Laporan" onclick="janaLaporan()">
            <input type="reset" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()">
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function kosongkan(){
  
 document.${formName}.txtLuasDari.value = "";
 document.${formName}.txtLuasHingga.value = "";
 document.${formName}.txtTahun.value = "";
 doAjaxCall${formName}("kosongkan");	
 
}
function janaLaporan(){	
	
	if(document.${formName}.txtLuasDari.value == ""){
		alert('Sila pilih Jenis Laporan.');
  		document.${formName}.txtTahun.focus(); 
		return; 
	}
	if(document.${formName}.txtLuasHingga.value == ""){
		alert('Sila pilih Status Fail.');
  		document.${formName}.txtTahun.focus(); 
		return; 
	}
	cetakLaporan();
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function cetakLaporan() {
	
	if(document.${formName}.txtTahun.value == ""){
	
		var tahun = "0";
	}else{
		
		var tahun = document.${formName}.txtTahun.value;
	}
	
	var url = "../servlet/ekptg.report.php2.APBLaporanPelesenPasirCerakinanLuas?luasDari="+document.${formName}.txtLuasDari.value+"&luasHingga="+document.${formName}.txtLuasHingga.value+"&TAHUN="+tahun;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
