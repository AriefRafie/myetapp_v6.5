<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
<input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>LAPORAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
         <tr>
          <td width="30%" align="right"><span class="style1">*</span> Status Fail</td>
          <td>
          <select name="socStatus" id="socStatus" style="width:120px;" onChange="onChangeValue1(this)" >
     		#if($flagStatus == '1')
                <option value="0">SILA PILIH</option>
                <option value="1" selected="selected"> BELUM SELESAI </option>
                <option value="2"> SELESAI </option>
                <option value="3"> BATAL </option>
                
        	#elseif($flagStatus == '2')
        		<option value="0">SILA PILIH</option>
                <option value="1"> BELUM SELESAI </option>
                <option value="2" selected="selected"> SELESAI </option>
                <option value="3"> BATAL </option>
            #elseif($flagStatus == '3')
        		<option value="0">SILA PILIH</option>
                <option value="1"> BELUM SELESAI </option>
                <option value="2" > SELESAI </option>
                <option value="3" selected="selected"> BATAL </option>
            #else
            	<option value="0"  selected="selected">SILA PILIH</option>
                <option value="1"> BELUM SELESAI </option>
                <option value="2"> SELESAI </option>
                <option value="3"> BATAL </option>
            #end
           </select></td>
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
  
 document.${formName}.socStatus.value = "0";
 document.${formName}.flagStatus.value = "0";
 document.${formName}.txtTahun.value = "";
 doAjaxCall${formName}("kosongkan");	
 
}
function janaLaporan(){	
	if(document.${formName}.socStatus.value == "0"){
		alert('Sila pilih Status Fail.');
  		document.${formName}.txtTahun.focus(); 
		return; 
	}
	cetakLaporan();
}

function onChangeValue1(str){
  
 document.${formName}.flagStatus.value = str.value;
 doAjaxCall${formName}("onChangeValue1");	
 
}

function cetakLaporan() {
	
	if(document.${formName}.txtTahun.value == ""){
	
		var tahun = "0";
	}else{
		
		var tahun = document.${formName}.txtTahun.value;
	}
	
	var url = "../servlet/ekptg.report.php2.CRBLaporanPenguatkuasaanTerperinci?STATUS="+document.${formName}.flagStatus.value+"&TAHUN="+tahun;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
