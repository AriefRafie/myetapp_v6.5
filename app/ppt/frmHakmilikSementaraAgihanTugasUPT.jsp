<style type="text/css">
<!--
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
<input type="hidden" name="id_tugas" value="$id_tugas">
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_status" value="$id_status">
<input type="hidden" name="alert_message" id="alert_message" />
<input name="action" id="action" type="hidden"/>
<input type="hidden" name="mode" value="$mode" />
<input type="hidden" name="action" value="$action" />

<fieldset>
<legend><strong>Maklumat Fail</strong></legend>
<table width="100%">
  <tr>
    <td width="29%" align="left">No. Ruj. JKPTG </td>
    <td width="1%">:</td>
    <td width="70%">$noFail</td>
  </tr>
  <tr>
    <td width="29%" align="left">No. Permohonan</td>
    <td width="1%">:</td>
    <td width="70%">$noPermohonan</td>
  </tr>
  <!--<tr>
    <td width="29%" align="left">Status</td>
    <td width="1%">:</td>
    <td width="70%">$status</td>
  </tr>-->
</table>
</fieldset>
<p>
<fieldset>
          <legend><strong>Maklumat Pengagihan / Penyerahan Tugas</strong></legend>
          <table width="100%" border="0" cellspacing="4">
                      
          <tr>
            <td width="1%">&nbsp;</td>
            <td width="29%">Pegawai Pengagih SPT</td>
            <td width="1%">:</td>
            <td width="70%"><input type="text" size="40" name="txtNamaPegawai" id="txtNamaPegawai" value="$pegawaiPengagih" readonly="true" style="text-transform:uppercase" class="disabled"  />
            <input type="hidden" readonly="true" name="ekptg_user_id" size="5" value="${session.getAttribute("_ekptg_user_id")}" /></td>
          </tr>
          <tr>
            <td width="1%"><font color="red"> #if($readonly != 'readonly')*#end</font></td>
            <td>Tarikh Serah Tugas</td>
            <td>:</td>
            <td><input name="txdTarikhSerahTugas" id="txdTarikhSerahTugas" type="text" value="$txdTarikhSerahTugas" size="11" $readonly class="$disabled" onblur="checking_validation(this,'tarikh_serah_tugas_check','yes','serah tugas','tarikh');"/>
            #if($readonly != 'readonly')
              <a href="javascript:displayDatePicker('txdTarikhSerahTugas',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>#end <span class="style52">dd/mm/yyyy</span></td>
          </tr>
          <tr>
            <td width="1%"><font color="red">#if($readonly != 'readonly')*#end</font></td>
            <td>Pegawai Penerima</td>
            <td>:</td>
            <td>$SelectPegawai<!--#if($id_status == '11') $SelectPegawai #else $addPegawaiPenerima #end--></td>
          </tr>
          <tr>
            <td width="1%" valign="top">&nbsp;</td>
            <td valign="top">Jawatan Pegawai Penerima</td>
            <td valign="top">:</td>
            <td><input name="txtjawatan" id="txtjawatan" value="$addtxtjawatan" type="text" readonly="true" onblur="this.value=this.value.toUpperCase();" size="40" class="disabled" /></td>
          </tr>
          <tr>
            <td width="1%" valign="top">&nbsp;</td>
            <td valign="top">Catatan</td>
            <td valign="top">:</td>
            <td><textarea name="txtCatatan" cols="37%" value="" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" rows="3" id="txtCatatan"$readonly class="$disabled">$txtCatatan</textarea></td>
          </tr>
          <tr>
            <td width="1%" valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>#if($id_status == '127')
              <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Simpan_Agihan('$id_fail','$id_permohonan');" />
              <input name="cmdBatal3" type="button" value="Kembali" onclick="javascript: Kembali_skrin1('$id_fail','$id_permohonan');" />
#end
            
            #if($id_status == '148')
            <!--<input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:KemaskiniSkrin('$id_permohonan','$id_fail','$id_tugas');" />-->
            <input name="cmdBatal3" type="button" value="Kembali" onclick="javascript: Kembali_skrin1('$id_fail','$id_permohonan');" />
#end </td>
          </tr>
  		</table>
  
</fieldset> 
<script>
function doChangeidPegawai() {
    doAjaxCall${formName}("doChangeidPegawai");
	
	
}
function Simpan_Agihan(id_fail,id_permohonan){	

	if(document.${formName}.txdTarikhSerahTugas.value == ""){
		alert("Sila masukkan \"Tarikh Serah Tugas\" terlebih dahulu.");
  		document.${formName}.txdTarikhSerahTugas.focus(); 
		return;
	}
		if(document.${formName}.pegawai.value == ""){
		alert("Sila pilih \"Pegawai Penerima\" terlebih dahulu.");
  		document.${formName}.pegawai.focus(); 
		return;
	}
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraAgihanTugasUPT&action=Simpan_Agihan";
	document.${formName}.submit();
	
}
function Kembali_skrin1(id_fail,id_permohonan){
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraAgihanTugasUPT&action=Kembali_skrin1";
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