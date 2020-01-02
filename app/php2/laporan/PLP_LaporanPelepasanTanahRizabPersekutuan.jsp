<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
<input name="flagJenis" type="hidden" id="flagJenis" value="$flagJenis"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>LAPORAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
          <td width="30%" align="right"><span class="style1">*</span> Jenis Laporan</td>
          <td>
          <select name="socJenis" id="socJenis" style="width:120px;" onChange="onChangeValue(this)">
     		#if($flagJenis == '1')
                <option value="0">SILA PILIH</option>
                <option value="1" selected="selected"> KESELURUHAN </option>
                <option value="2"> NEGERI </option>
                 <option value="3"> KEMENTERIAN </option>
        	#elseif($flagJenis == '2')
        		<option value="0">SILA PILIH</option>
                <option value="1"> KESELURUHAN </option>
                <option value="2" selected="selected"> NEGERI </option>
                 <option value="3"> KEMENTERIAN </option>
            #elseif($flagJenis == '3')  
              	<option value="0">SILA PILIH</option>
                <option value="1"> KESELURUHAN </option>
                <option value="2"> NEGERI </option>
                 <option value="3" selected="selected"> KEMENTERIAN </option>
            #else
            	<option value="0"  selected="selected">SILA PILIH</option>
                <option value="1"> KESELURUHAN </option>
                <option value="2"> NEGERI </option>
                 <option value="3"> KEMENTERIAN </option>
            #end
           </select></td>
        </tr>
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
	if(document.${formName}.flagJenis.value == "0"){
		alert('Sila pilih Jenis Laporan.');
  		document.${formName}.txtTahun.focus(); 
		return; 
	}
	cetakLaporan();
}

function onChangeValue(str){
  
 document.${formName}.flagJenis.value = str.value;
 doAjaxCall${formName}("onChangeValue");	
 
}

function cetakLaporan() {
	var url = "../servlet/ekptg.report.php2.PLPLaporanTanahRizabPersekutuan?JENIS="+document.${formName}.flagJenis.value+"&TAHUN="+document.${formName}.txtTahun.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
