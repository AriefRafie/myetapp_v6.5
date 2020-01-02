<style type="text/css">
<!--
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")

<fieldset>
<!--<table width="100%">
  <tr>
    <td align="left" width="49%"><div align="right">Kod Pejabat JPPH</div></td>
    <td width="1%">:</td>
    <td width="50%"><input type="text" name="txtKodJPPH" id="txtKodJPPH" value="$kodPejabat" class="$disabledmode" $readonlymode /></td>
  </tr>
</table>-->
<table width="100%">
  <tr>
    <td colspan="2" align="left" width="50%">
    <fieldset><legend><strong>Dihantar</strong></legend>
    <table width="100%">
  <tr>
    <td align="left" width="50%">Bil. Surat</td>
    <td width="1%">:</td>
    <td width="49%"><label>
      <input name="txtBilSurat" type="text" class="$disabledmode" id="txtBilSurat" value="$bilSurat" size="2" maxlength="2" $readonlymode >
    </label></td>
  </tr>
  <tr>
    <td align="left">Tarikh Surat</td>
    <td>:</td>
    <td><label>
      <input name="txdTkhSurat" type="text" id="txdTkhSurat" size="10" value="$tarikhSurat" class="$disabledmode" $readonlymode onblur="checking_validation(this,'tarikh_surat_check','no','surat','tarikh');">
      #if($readonlymode != 'readonly') <a href="javascript:displayDatePicker('txdTkhSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span>    </label></td>
  </tr>
  <tr>
    <td align="left">Tarikh Akhir Jawapan Diterima</td>
    <td>:</td>
    <td><label>
      <input name="txdTkhAkhirJwpnDiterima" type="text" id="txdTkhAkhirJwpnDiterima" size="10" value="$tarikhAkhir" class="$disabledmode" $readonlymode onblur="checking_validation(this,'tarikh_akhir_check','no','akhir jawapan diterima','tarikh');">
      #if($readonlymode != 'readonly') <a href="javascript:displayDatePicker('txdTkhAkhirJwpnDiterima',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span>    </label></td>
  </tr>
</table>
    </fieldset>    </td>
    <td width="50%">
    <fieldset><legend><strong>Diterima</strong></legend>
    <table width="100%">
  <tr>
    <td align="left" width="50%">No. Ruj. Surat JPPH</td>
    <td width="1%">:</td>
    <td width="49%"><label>
      <input name="txtNoRujSuratJPPH" type="text" id="txtNoRujSuratJPPH" value="$noRujSuratJPPH" class="$disabledmode" $readonlymode onkeyup="this.value=this.value.toUpperCase();" onblur="this.value=this.value.toUpperCase();">
    </label></td>
  </tr>
  <tr>
    <td align="left">Tarikh Terima</td>
    <td>:</td>
    <td><label>
      <input name="txdTkhTerima" type="text" id="txdTkhTerima" value="$tarikhSuratJPPH" size="10" class="$disabledmode" $readonlymode  onblur="checking_validation(this,'tarikh_terima_surat_check','no','terima surat JPPH','tarikh');">
      #if($readonlymode != 'readonly') <a href="javascript:displayDatePicker('txdTkhTerima',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span>    </label></td>
  </tr>
  <tr>
    <td align="left">Tarikh Surat JPPH</td>
    <td>:</td>
    <td><label>
      <input name="txdTkhSuratJPPH" type="text" id="txdTkhSuratJPPH" value="$tarikhTerima" size="10" class="$disabledmode" $readonlymode onblur="checking_validation(this,'tarikh_surat_check','no','surat JPPH','tarikh');">
      #if($readonlymode != 'readonly') <a href="javascript:displayDatePicker('txdTkhSuratJPPH',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span>    </label></td>
  </tr>
</table>
    </fieldset>    </td>
  </tr>
  <tr>
    <td colspan="3" align="center">
    #if($readmode == "new")
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />    
    	<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()" />
    #end
    #if($readmode == "view")    
    	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
    	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus('$idPermohonan')"/>
    #end
    #if($readmode == "update")
    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="kemaskiniJPPH('$idPermohonan')"/>    
    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalJPPH()" />
    #end
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />    </td>
  </tr>

</table>

</fieldset>
<input name="id_permohonan" type="hidden" id="id_permohonan" value="$idPermohonan" />
<input name="temp_Id" type="hidden" id="temp_Id" value="$tempId" />
<input name="idUlasanTeknikal" id="idUlasanTeknikal" type="hidden" value="$idUlasanTeknikal" />
<input name="id_fail" id="id_fail" type="hidden" value="$idFail" />
<script>
function simpan() {

	  var surat  = document.${formName}.txdTkhSurat.value;
	  
	  var dt1Surat   = parseInt(surat.substring(0,2),10);
	  var mon1Surat  = parseInt(surat.substring(3,5),10)-1;
	  var yr1Surat   = parseInt(surat.substring(6,10),10);
		   
	  var dateSurat = new Date(yr1Surat, mon1Surat, dt1Surat);
	  
	  var akhir  = document.${formName}.txdTkhAkhirJwpnDiterima.value;
	  
	  var dt1Akhir  = parseInt(akhir.substring(0,2),10);
	  var mon1Akhir  = parseInt(akhir.substring(3,5),10)-1;
	  var yr1Akhir   = parseInt(akhir.substring(6,10),10);
		   
	  var dateAkhir = new Date(yr1Akhir, mon1Akhir, dt1Akhir);
	  
	  var terima  = document.${formName}.txdTkhTerima.value;
	  
	  var dt1Terima  = parseInt(terima.substring(0,2),10);
	  var mon1Terima  = parseInt(terima.substring(3,5),10)-1;
	  var yr1Terima   = parseInt(terima.substring(6,10),10);
		   
	  var dateTerima = new Date(yr1Terima, mon1Terima, dt1Terima);
	  
	  var suratJPPH = document.${formName}.txdTkhSuratJPPH.value;
	  
	  var dt1SuratJPPH   = parseInt(suratJPPH.substring(0,2),10);
	  var mon1SuratJPPH  = parseInt(suratJPPH.substring(3,5),10)-1;
	  var yr1SuratJPPH   = parseInt(suratJPPH.substring(6,10),10);
		   
	  var dateSuratJPPH = new Date(yr1SuratJPPH, mon1SuratJPPH, dt1SuratJPPH);
	  
	
	if(dateSurat > dateAkhir){
		alert("Tarikh Surat tidak boleh melebihi dari Tarikh Akhir Jawapan Diterima. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTkhSurat.focus(); 
		return;
 	}
	
	if(dateSuratJPPH > dateTerima){
		alert("Tarikh Surat JPPH tidak boleh melebihi dari Tarikh Terima. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTkhSuratJPPH.focus(); 
		return;
 	}


	if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJPPH&action=add"; 
		document.${formName}.submit(); 	
	
}

function kemaskiniJPPH(idPermohonan) {

      var surat  = document.${formName}.txdTkhSurat.value;
	  
	  var dt1Surat   = parseInt(surat.substring(0,2),10);
	  var mon1Surat  = parseInt(surat.substring(3,5),10)-1;
	  var yr1Surat   = parseInt(surat.substring(6,10),10);
		   
	  var dateSurat = new Date(yr1Surat, mon1Surat, dt1Surat);
	  
	  var akhir  = document.${formName}.txdTkhAkhirJwpnDiterima.value;
	  
	  var dt1Akhir  = parseInt(akhir.substring(0,2),10);
	  var mon1Akhir  = parseInt(akhir.substring(3,5),10)-1;
	  var yr1Akhir   = parseInt(akhir.substring(6,10),10);
		   
	  var dateAkhir = new Date(yr1Akhir, mon1Akhir, dt1Akhir);
	  
	  var terima  = document.${formName}.txdTkhTerima.value;
	  
	  var dt1Terima  = parseInt(terima.substring(0,2),10);
	  var mon1Terima  = parseInt(terima.substring(3,5),10)-1;
	  var yr1Terima   = parseInt(terima.substring(6,10),10);
		   
	  var dateTerima = new Date(yr1Terima, mon1Terima, dt1Terima);
	  
	  var suratJPPH = document.${formName}.txdTkhSuratJPPH.value;
	  
	  var dt1SuratJPPH   = parseInt(suratJPPH.substring(0,2),10);
	  var mon1SuratJPPH  = parseInt(suratJPPH.substring(3,5),10)-1;
	  var yr1SuratJPPH   = parseInt(suratJPPH.substring(6,10),10);
		   
	  var dateSuratJPPH = new Date(yr1SuratJPPH, mon1SuratJPPH, dt1SuratJPPH);
	  
	
	if(dateSurat > dateAkhir){
		alert("Tarikh Surat tidak boleh melebihi dari Tarikh Akhir Jawapan Diterima. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTkhSurat.focus(); 
		return;
 	}
	
	if(dateSuratJPPH > dateTerima){
		alert("Tarikh Surat JPPH tidak boleh melebihi dari Tarikh Terima. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTkhSuratJPPH.focus(); 
		return;
 	}
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.id_permohonan.value = idPermohonan;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJPPH&action=update";
		document.${formName}.submit();
			
}

function kemaskini() {

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJPPH&action=kemaskini"; 
	document.${formName}.submit();	
}

function batal() {

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJPPH&action=newJPPH"; 
	document.${formName}.submit();	
}

function batalJPPH() {

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJPPH&action=newJPPH"; 
	document.${formName}.submit();	
}

function hapus() {
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJPPH&action=hapus"; 
		document.${formName}.submit();
	
}

function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJPPH&action="; 
	document.${formName}.submit();	
}
function checking_validation(field,point,mandatory,value_field,jenis_field){	
    	
	var lepas_or_xlepas = 2;	
	
	
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	      DateField.select();
		 // DateField.focus();
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	   //alert(lepas_or_xlepas);
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   }
	   else
	   {
	  
	   
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.focus();
	   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.select();
	//   DateField.focus();
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	//   DateField.select();
	//   DateField.focus();
	   
	   }
	   
	   }
	   
	   
	   if(jenis_field == "normal")
	   {
	   
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	
	   }
	   else
	   {
	    document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   
	   	   
	   }
	   
	   
	   
	
	}
</script>


