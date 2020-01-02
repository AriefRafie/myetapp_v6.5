<style type="text/css">
<!--
.style1 {color: #FF0000}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
<p>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabPTGPTD('$idFail','$idPermohonan')">PTG/PTD</li>
    <li class="TabbedPanelsTab" tabindex="0">Dalam Mukim</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabAtasTanah('$idFail','$idPermohonan')">Tempat Lain Di Atas/Berhampiran Tanah</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
    <fieldset><legend><strong>Maklumat Penampalan Notis Awam</strong></legend>
    <table width="100%">
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">*</span></td>
    <td width="29%" align="left" valign="top">Tempat</td>
    <td width="1%" valign="top">:</td>
    <td width="70%"><label>

      <textarea name="txtTempat" cols="40" class="$disabled" id="txtTempat" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonly="$readonly">$tempat</textarea>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td align="left">Tarikh Tampal</td>
    <td>:</td>
    <td><label>
      <input name="txdTarikhTampal" type="text" id="txdTarikhTampal" value="$tarikh" size="10" $readonly class="$disabled" onblur="checking_validation(this,'tarikh_tampal_check','yes','tampal','tarikh');">
      #if($readonly != 'readonly')<a href="javascript:displayDatePicker('txdTarikhTampal',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>#end <span class="style52">dd/mm/yyyy</span>    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td>
    #if($mode == 'newMukim')
      <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpan('$idPermohonan')"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick = "batal()"/>
    #end
    #if($mode == 'viewMukim')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>     
      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus()" /> 
    #end
    #if($mode == 'updateMukim')
     <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan_update('$id_notisawam')"/>
     <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalupdate()"/>
    #end   
     <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>    </td>
  </tr>
</table>

    
    
    </fieldset>
    <br>
     <fieldset>
        <legend><strong>Senarai Notis  Dalam Mukim</strong></legend>
        <table width="100%">
          <tr>
            <td colspan="3"><label>
           #if($mode != 'newMukim')
              <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onClick = "tambah()"/>
            #end
            </label></td>
          </tr>
          <tr class="table_header">
            <td width="5%"><strong>Bil.</strong></td>
            <td width="38%"><strong>Tempat</strong></td>
            <td width="37%"><strong>Tarikh Tampal</strong></td>
          </tr>
          #foreach ($fail in $TampalNotisList)
  
            #if ($fail.bil == '') 
            #set ($row = 'row1')
            #elseif ($fail.bil % 2 != 0)
            #set ($row = 'row1')
            #else 
            #set ($row = 'row2')
            #end
          <tr>
            <td class="$row">$fail.bil</td>
             #if ($fail.bil != '') 
            <td class="$row"><a href="javascript:viewNotisMukim('$fail.id_notisawam')"><font color="blue">$fail.tempat</font></a></td>
            #else
            <td class="$row">$fail.tempat</td>

            #end
            <td width="20%" class="$row">$fail.tarikh</td>
          </tr>
          #end
        </table>
      </fieldset>
    </div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<input type="hidden" name="id_fail" value="$idFail" />
<input type="hidden" name="id_permohonan" value="$idPermohonan" />
<input name="id_NotisAwam" type="hidden" value="$id_notisawam" />
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});

//-->
</script>
<script>
function tabPTGPTD(id_fail,id_permohonan) {
	
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=newNotis";
	document.${formName}.submit();
	
}
function tabAtasTanah(id_fail,id_permohonan) {
	
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=tabAtasTanah";
	document.${formName}.submit();
	
}
function simpan(id_permohonan){
	
	 var tampal  = document.${formName}.txdTarikhTampal.value;
	  
	  var dt1Tampal  = parseInt(tampal.substring(0,2),10);
	  var mon1Tampal  = parseInt(tampal.substring(3,5),10)-1;
	  var yr1Tampal   = parseInt(tampal.substring(6,10),10);
		   
	  var dateTampal = new Date(yr1Tampal, mon1Tampal, dt1Tampal);
	  
	  var currentTime = new Date();
	   
	if(dateTampal < currentTime){
		alert("Tarikh Tampal tidak boleh kurang dari tarikh hari ini. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTarikhTampal.focus(); 
		return;
 	}

	if(document.${formName}.txtTempat.value == ""){
		alert("Sila masukkan \"Tempat\" terlebih dahulu.");
  		document.${formName}.txtTempat.focus(); 
		return;
	}
	if(document.${formName}.txdTarikhTampal.value == ""){
		alert("Sila masukkan \"Tarikh Tampal\" terlebih dahulu.");
  		document.${formName}.txdTarikhTampal.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=addMukim";
	document.${formName}.submit();
}
function batal(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=tabDalamMukim";
	document.${formName}.submit();
}
function kembali(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam";
	document.${formName}.submit();
}
function viewNotisMukim(id_NotisAwam){
	
	document.${formName}.id_NotisAwam.value = id_NotisAwam;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=viewNotisMukim";
	document.${formName}.submit();
}
function kemaskini(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=updateNotisMukim";
	document.${formName}.submit();
}
function batalupdate(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=viewNotisMukim";
	document.${formName}.submit();
}
function simpan_update(id_NotisAwam){
	
	 var tampal  = document.${formName}.txdTarikhTampal.value;
	  
	  var dt1Tampal  = parseInt(tampal.substring(0,2),10);
	  var mon1Tampal  = parseInt(tampal.substring(3,5),10)-1;
	  var yr1Tampal   = parseInt(tampal.substring(6,10),10);
		   
	  var dateTampal = new Date(yr1Tampal, mon1Tampal, dt1Tampal);
	  
	  var currentTime = new Date();
	   
	if(dateTampal < currentTime){
		alert("Tarikh Tampal tidak boleh kurang dari tarikh hari ini. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTarikhTampal.focus(); 
		return;
 	}

	if(document.${formName}.txtTempat.value == ""){
		alert("Sila masukkan \"Tempat\" terlebih dahulu.");
  		document.${formName}.txtTempat.focus(); 
		return;
	}
	if(document.${formName}.txdTarikhTampal.value == ""){
		alert("Sila masukkan \"Tarikh Tampal\" terlebih dahulu.");
  		document.${formName}.txdTarikhTampal.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	
	document.${formName}.id_NotisAwam.value = id_NotisAwam;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=simpanUpdateMukim";
	document.${formName}.submit();
}
function hapus(){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=hapusNotisMukim";
	document.${formName}.submit();
}
function tambah(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisAwam&action=tabDalamMukim";
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
