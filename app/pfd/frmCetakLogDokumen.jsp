
<style type="text/css">
<!--
.style40 {color: #FF0000}
.style41 {color: #000000}
.style42 {color: #0000FF}
-->
</style>

<input name="idLogDokumen" type="hidden" value="$idLogDokumen"/>
<input type="hidden" name="ekptg_user_id" id="ekptg_user_id" size="5" value="${session.getAttribute("_ekptg_user_id")}" /> 

<table width="100%">
  <tr>
    <td>
<fieldset>
<legend>CETAK LOG DOKUMEN MASUK</legend>
<table width="100%">
  <tr>
    <td width="9%" align="left" valign="top" scope="row">&nbsp;</td>
    <td width="21%" align="left" valign="top" scope="row">Tarikh Log Dokumen Masuk Dari</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="16%" align="left" valign="top"><label>
      <input name="txtcmdCetakButtonA" type="text" id="txtcmdCetakButtonA" value="$!txtcmdCetakButtonA" size="10" $readOnlyMinit $disabledMinit  />
    </label>
    <a href="javascript:displayDatePicker('txtcmdCetakButtonA',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
    <td width="22%" align="left" valign="top">Tarikh Log Dokumen Masuk Hingga</td>
    <td width="1%" align="left" valign="top">:</td>
    <td width="30%" align="left" valign="top"><label>
      <input name="txtcmdCetakButtonB" type="text" id="txtcmdCetakButtonB" value="$!txtcmdCetakButtonB" size="10" $readOnlyMinit $disabledMinit  />
    </label>
      <a href="javascript:displayDatePicker('txtcmdCetakButtonB',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td colspan="7" align="center" scope="row"><input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Log Dokumen Masuk" onclick="cetakMasuk(document.${formName}.txtcmdCetakButtonA.value,document.${formName}.txtcmdCetakButtonB.value,1,document.${formName}.ekptg_user_id.value)"  /></td>
  </tr>
</table>
</fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
<legend>CETAK LOG DOKUMEN KELUAR</legend>
<table width="100%">
  <tr>
    <td width="9%" align="left" valign="top" scope="row">&nbsp;</td>
    <td width="21%" align="left" valign="top" scope="row">Tarikh Log Dokumen Keluar Dari</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="16%" align="left" valign="top"><label>
      <input name="txtcmdCetakButtonC" type="text" id="txtcmdCetakButtonC" value="$!txtcmdCetakButtonC" size="10" $readOnlyMinit $disabledMinit />
    </label>
    <a href="javascript:displayDatePicker('txtcmdCetakButtonC',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
    <td width="22%" align="left" valign="top">Tarikh Log Dokumen Keluar Hingga</td>
    <td width="1%" align="left" valign="top">:</td>
    <td width="30%" align="left" valign="top"><label>
      <input name="txtcmdCetakButtonD" type="text" id="txtcmdCetakButtonD" value="$!txtcmdCetakButtonD" size="10" $readOnlyMinit $disabledMinit />
    </label>
      <a href="javascript:displayDatePicker('txtcmdCetakButtonD',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td colspan="7" align="center" scope="row"><input type="button" name="cmdCetak2" id="cmdCetak2" value="Cetak Log Dokumen Keluar" onclick="cetakKeluar(document.${formName}.txtcmdCetakButtonC.value,document.${formName}.txtcmdCetakButtonD.value,2,document.${formName}.ekptg_user_id.value)"  /></td>
  </tr>
</table>
</fieldset></td>
  </tr>
</table>




<script>
function cetakMasuk(DariTarikh,HinggaTarikh,Flag_dokumen,ekptg_user_id) {

	if (HinggaTarikh == "" || DariTarikh == "" ){
		alert('Sila Masukkan Tarikh terlebih dahulu.');
		return;
	}else{
	
	//var mula  = document.getElementById(txtcmdCetakButtonA).value;
   //var akhir  = document.getElementById(txtcmdCetakButtonB).value;
  
  
  if(DariTarikh != "" && HinggaTarikh != "")
  {
		  
		   var dtx   = parseInt(DariTarikh.substring(0,2),10);
		   var monx  = parseInt(DariTarikh.substring(3,5),10)-1;
		   var yrx   = parseInt(DariTarikh.substring(6,10),10);	   
		   var datex = new Date(yrx, monx, dtx);		   
		   
		   var dtx1   = parseInt(HinggaTarikh.substring(0,2),10);
		   var monx1  = parseInt(HinggaTarikh.substring(3,5),10)-1;
		   var yrx1   = parseInt(HinggaTarikh.substring(6,10),10);	   
		   var datex1 = new Date(yrx1, monx1, dtx1);	 
			 
			 
			 
			 
			  if (datex > datex1)
			  {
			  alert("Sila pastikan tarikh '"+DariTarikh+"' tidak melebihi tarikh '"+HinggaTarikh+"'");
			  return;
			  }
			  
			  
 }
	}
	var url = "../servlet/ekptg.report.pfd.DaftarSurat?reportType=PDF&dari_tahun="+DariTarikh+"&hingga_tahun="+HinggaTarikh+"&Flag_dokumen="+Flag_dokumen+"&user_id="+ekptg_user_id;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}
function cetakKeluar(DariTarikh,HinggaTarikh,Flag_dokumen,ekptg_user_id) {
  	if (HinggaTarikh == "" || DariTarikh == "" ){
		alert('Sila Masukkan Tarikh terlebih dahulu.');
		return;
	}else{
  
  if(DariTarikh != "" && HinggaTarikh != "")
  {
		  
		   var dtx   = parseInt(DariTarikh.substring(0,2),10);
		   var monx  = parseInt(DariTarikh.substring(3,5),10)-1;
		   var yrx   = parseInt(DariTarikh.substring(6,10),10);	   
		   var datex = new Date(yrx, monx, dtx);		   
		   
		   var dtx1   = parseInt(HinggaTarikh.substring(0,2),10);
		   var monx1  = parseInt(HinggaTarikh.substring(3,5),10)-1;
		   var yrx1   = parseInt(HinggaTarikh.substring(6,10),10);	   
		   var datex1 = new Date(yrx1, monx1, dtx1);	 
			 
			 
			 
			 
			  if (datex > datex1)
			  {
			  alert("Sila pastikan tarikh '"+DariTarikh+"' tidak melebihi tarikh '"+HinggaTarikh+"'");
			  return;
			  }
			  
			  
 }
	}

	var url = "../servlet/ekptg.report.pfd.DaftarSurat?reportType=PDF&DariTarikh="+DariTarikh+"&HinggaTarikh="+HinggaTarikh+"&Flag_dokumen="+Flag_dokumen+"&user_id="+ekptg_user_id;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}



function date_check(tmula,takhir)
{



  var mula  = document.getElementById(tmula).value;
  var akhir  = document.getElementById(takhir).value;
  
  
  if(mula != "" && akhir != "")
  {
		  
		   var dtx   = parseInt(mula.substring(0,2),10);
		   var monx  = parseInt(mula.substring(3,5),10)-1;
		   var yrx   = parseInt(mula.substring(6,10),10);	   
		   var datex = new Date(yrx, monx, dtx);		   
		   
		   var dtx1   = parseInt(akhir.substring(0,2),10);
		   var monx1  = parseInt(akhir.substring(3,5),10)-1;
		   var yrx1   = parseInt(akhir.substring(6,10),10);	   
		   var datex1 = new Date(yrx1, monx1, dtx1);	 
			 
			 
			 
			 
			  if (datex > datex1)
			  {
			  alert("Sila pastikan tarikh '"+mula+"' tidak melebihi tarikh '"+akhir+"'");
			  return false;
			  }
			  
			  
			  }

}


</script>
