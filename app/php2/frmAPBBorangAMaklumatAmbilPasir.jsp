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
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idBorangA" type="hidden" id="idBorangA" value="$idBorangA"/>
  <input name="actionLesen" type="hidden" id="actionLesen" value="$actionLesen"/>
  <input name="idJadualKedua" type="hidden" id="idJadualKedua" value="$idJadualKedua"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  
  <tr>
    <td> #parse("app/php2/frmAPBBorangAHeader.jsp") </td>
  </tr>
  
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if (($mode != 'newBarge') && ($mode != 'viewBarge')&& ($mode != 'updateBarge'))
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PENGAMBILAN PASIR</strong></legend>
      #parse("app/php2/frmAPBBorangAAmbilPasirDetail.jsp")
      </fieldset></td>
  </tr>
  #end
  #if(($mode == 'view')||($mode == 'newBarge') || ($mode == 'viewBarge') || ($mode == 'updateBarge'))
  
  <tr>
    <td>
      #parse("app/php2/frmAPBBorangAMaklumatBarge.jsp")
      </td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
   
</table>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakAPBLaporanBorangA('$idBorangA')"><font color="blue"> Laporan Borang A </font></a></td>
      </tr>           
    </table>
</fieldset>

<script>
function cekTarikh(elmnt) {
//CHECK DATE   
	//CHECK DATE 
	var str1  = document.${formName}.txtTarikhMula.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhMula = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.txtTarikhTamat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhTamat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhMula > tarikhTamat){
		alert('Tarikh Mula Operasi tidak boleh melebihi dari Tarikh Tamat Operasi.');
		elmnt.value ="";
  		document.${formName}.txtTarikhTamat.focus(); 
		return;
	}
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakAPBLaporanBorangA(id_borangA) {

	var url = "../servlet/ekptg.report.php2.APBLaporanBorangA?idBorangA="+id_borangA;
	
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function doSimpanMaklumatPasir(){

	if(document.${formName}.socBulan.value == ""){
		alert('Sila pilih bulan.');
  		document.${formName}.socBulan.focus(); 
		return; 
	}
	if(document.${formName}.txtTahun.value == ""){
		alert('Sila masukkan Tahun.');
  		document.${formName}.txtTahun.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.actionLesen.value = "doMaklumatPasir";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatAmbilPasir";
	document.${formName}.submit();
}
function doSimpanKemasMaklumatPasir(){
	
	if(document.${formName}.socBulan.value == ""){
		alert('Sila pilih Bulan.');
  		document.${formName}.socBulan.focus(); 
		return; 
	}
	if(document.${formName}.txtTahun.value == ""){
		alert('Sila masukkan Tahun.');
  		document.${formName}.txtTahun.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.actionLesen.value = "doMaklumatPasir";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatPasir";
	document.${formName}.submit();
}
function doKemaskiniMaklumatPasir(){

	document.${formName}.actionLesen.value = "doMaklumatPasir";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniMaklumatPasir(){
	
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doKembali(){
	
	document.${formName}.actionLesen.value = "papar";
	document.${formName}.submit();
}
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
</script>
