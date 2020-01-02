<style type="text/css">
<!--
.style1 {color: #FF0000}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
<p>
#parse("app/ppt/frmHakmilikSementaraBorangMPB.jsp") 
<p>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabBorangM('$idFail','$idPermohonan')">Affidavit ke Mahkamah</li>
    <li class="TabbedPanelsTab" tabindex="0">Perintah Mahkamah</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
    <fieldset><legend><strong>Maklumbalas Mahkamah</strong></legend>
    <table width="100%">
      <tr>
        <td align="left" width="1%"><span class="style1">#if($readonlyPerintah != 'readonly')*#end</span></td>
        <td align="left" width="29%">No. Ruj. Prosiding Mahkamah</td>
        <td width="1%">:</td>
        <td width="70%"><label>
          <input name="txtNoRujProsiding" type="text" id="txtNoRujProsiding" value="$NO_RUJUKAN_PROSIDING" class="$disabledPerintah" $readonlyPerintah $disabledPerintah style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">
        </label></td>
      </tr>
    </table>
    </fieldset>
    <fieldset><legend><strong>Perintah Mahkamah</strong></legend>
    <table width="100%">
      <tr>
        <td width="1%" rowspan="2" align="left" valign="top"><span class="style1">#if($readonlyPerintah != 'readonly')*#end</span></td>
        <td width="20%" rowspan="2" align="left" valign="top">Ringkasan Perintah Mahkamah</td>
        <td width="1%" rowspan="2" valign="top">:</td>
        <td width="29%" rowspan="2"><label>
          <textarea name="txtPerintahMahkamah" id="txtPerintahMahkamah" cols="45" rows="5" class="$disabledPerintah" $readonlyPerintah $disabledPerintah style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$PERINTAH_MAHKAMAH</textarea>
        </label></td>
        <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyPerintah != 'readonly')*#end</span></td>
        <td width="20%" align="left" valign="top">Tarikh Perintah</td>
        <td width="1%" valign="top">:</td>
        <td width="29%" valign="top"><label>
          <input name="txdTkhPerintah" type="text" id="txdTkhPerintah" value="$TARIKH_PERINTAH" size="10" class="$disabledPerintah" $readonlyPerintah $disabledPerintah onblur="checking_validation(this,'tarikh_perintah_check','yes','perintah','tarikh');">
        #if($readonlyPerintah != 'readonly')  <a href="javascript:displayDatePicker('txdTkhPerintah',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>#end<span class="style52">dd/mm/yyyy</span>        </label></td>
      </tr>
      <tr>
        <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyPerintah != 'readonly')*#end</span></td>
        <td align="left" valign="top">Tarikh Terima Perintah</td>
        <td valign="top">:</td>
        <td valign="top"><label>
          <input name="txdTkhTrmPerintah" type="text" id="txdTkhTrmPerintah" value="$TARIKH_TERIMA_PERINTAH" size="10" class="$disabledPerintah" $readonlyPerintah $disabledPerintah onblur="checking_validation(this,'tarikh_terima_perintah_check','yes','terima perintah','tarikh');">
         #if($readonlyPerintah != 'readonly') <a href="javascript:displayDatePicker('txdTkhTrmPerintah',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>#end<span class="style52">dd/mm/yyyy</span>        </label></td>
      </tr>
      <tr>

        <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyPerintah != 'readonly')*#end</span></td>
        <td align="left" valign="top">Keputusan Mahkamah</td>
        <td valign="top">:</td>
        <td>#if($KEPUTUSAN_MAHKAMAH == '1')
          <input name="sorKptsnMahkamah" type="radio" class="$disabledPerintah" id="sorPampasanDitambah" value="1" $readonlyPerintah $disabledPerintah checked="checked" />
Pampasan Ditambah
        #else
<input name="sorKptsnMahkamah" type="radio" class="$disabledPerintah" id="sorPampasanDitambah" value="1" $readonlyPerintah $disabledPerintah />
Pampasan Ditambah
        #end </td>
        <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyPerintah != 'readonly')*#end</span></td>
        <td align="left" valign="top">Amaun Pampasan Mahkamah (RM)</td>
        <td valign="top">:</td>
        <td valign="top"><label>
          <input name="txtAmaunPamMahkamah" type="text" id="txtAmaunPamMahkamah" value="$PAMPASAN_MAHKAMAH" class="$disabledPerintah" $readonlyPerintah $disabledPerintah onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAnggaranPecahpisah')" onkeyup="validateNumber(this,this.value);">
        </label></td>
      </tr>
      <tr>
        <td width="1%" align="left" valign="top">&nbsp;</td>
        <td align="left" valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
        <td>#if($KEPUTUSAN_MAHKAMAH == '2')
          <input name="sorKptsnMahkamah" type="radio" class="$disabledPerintah" id="sorPengurusanPampasan" value="2" $readonlyPerintah $disabledPerintah checked="checked" />
Pengurusan Pampasan
        #else
<input name="sorKptsnMahkamah" type="radio" class="$disabledPerintah" id="sorPengurusanPampasan" value="2" $readonlyPerintah $disabledPerintah />
Pengurusan Pampasan
        #end </td>
        <td width="1%" align="left" valign="top">&nbsp;</td>
        <td><label></label></td>
        <td align="left" valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
      </tr>
      <tr>
        <td width="1%" align="left" valign="top">&nbsp;</td>
        <td align="left" valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
        <td>#if($KEPUTUSAN_MAHKAMAH == '3')
          <input name="sorKptsnMahkamah" type="radio" class="$disabledPerintah" id="sorKekal" value="3" $readonlyPerintah $disabledPerintah checked="checked" />
Kekal
        #else
<input name="sorKptsnMahkamah" type="radio" class="$disabledPerintah" id="sorKekal" value="3" $readonlyPerintah $disabledPerintah />
Kekal
        #end </td>
        <td width="1%" align="left" valign="top">&nbsp;</td>
        <td align="left" valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
        <td valign="top"><label></label></td>
      </tr>
    </table>

    </fieldset>
    </div>
  </div>
</div>
<table width="100%">
  <tr>
    <td colspan="6" align="center">
      #if($modePerintah == "baru")
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()" />
      #end
     #if($modePerintah == "papar")
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" />
     <!-- <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />-->
      #end
     #if($modePerintah == "kemaskini")
      <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick="kemaskiniPerintahMahkamah('$idPermohonan')" />
      <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onclick="batalPerintahMahkamah()" />
     #end
     <!-- <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />   -->
     
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliNew('$!id_permohonan')" />
      </td>
  </tr>
</table>
<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakBorangM('$idFail','$idHakmilikPB')"><font color="blue">Borang M – Perkara Yang Dirujuk Kepada Mahkamah</font></a></td>
      </tr>           
    </table>
</fieldset>
<input name="id_fail" type="hidden" value="$idFail" />
<input name="id_permohonan" type="hidden" value="$idPermohonan" />
<input name="hdnKeputusanMahkamah" type="hidden" value="$hdnKeputusanMahkamah" />
<input name="idBorangM" type="hidden" value="$idBorangM" />
<input name="idHakmilikPB" type="hidden" value="$idHakmilikPB" />
<input name="idProjekNegeri" type="hidden" value="$idProjekNegeri" /> 
<input type="hidden" name="id_status" value="$id_status" />
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
//-->
</script>
<script>
function kembaliNew(id_permohonan){
	
	document.${formName}.id_permohonan.value = id_permohonan;	
	document.${formName}.command.value = "baruBorangM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM";
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
function cetakBorangM(id_fail,id_hakmilikpb) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.BorangM?idfail="+id_fail+"&idhakmilikpb="+id_hakmilikpb;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function tabBorangM(id_fail,id_permohonan){	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=tabBorangM";
	document.${formName}.submit();
}

function simpan() {	
	
	if(document.${formName}.txtNoRujProsiding.value == ""){
		alert("Sila masukkan \"No. Ruj. Prosiding Mahkamah\" terlebih dahulu.");
  		document.${formName}.txtNoRujProsiding.focus(); 
		return;
	}
	if(document.${formName}.txtPerintahMahkamah.value == ""){
		alert("Sila masukkan \"Ringkasan Perintah Mahkamah\" terlebih dahulu.");
  		document.${formName}.txtPerintahMahkamah.focus(); 
		return;
	}
	if(document.${formName}.sorKptsnMahkamah.value == ""){
		alert("Sila masukkan \"Keputusan Mahkamah\" terlebih dahulu.");
  		document.${formName}.sorKptsnMahkamah.focus(); 
		return;
	}
	if(document.${formName}.txdTkhPerintah.value == ""){
		alert("Sila masukkan \"Tarikh Perintah\" terlebih dahulu.");
  		document.${formName}.txdTkhPerintah.focus(); 
		return;
	}
	if(document.${formName}.txdTkhTrmPerintah.value == ""){
		alert("Sila masukkan \"Tarikh Terima Perintah\" terlebih dahulu.");
  		document.${formName}.txdTkhTrmPerintah.focus(); 
		return;
	}
	if(document.${formName}.txtAmaunPamMahkamah.value == ""){
		alert("Sila masukkan \"Amaun Pampasan Mahkamah\" terlebih dahulu.");
  		document.${formName}.txtAmaunPamMahkamah.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=simpanPerintahMahkamah";
	document.${formName}.submit();
}

function batal() {	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=tabPerintahMahkamah";
	document.${formName}.submit();
}

function kemaskini() {	
	
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=kemaskiniPerintahMahkamah";
	document.${formName}.submit();
}




function kemaskiniPerintahMahkamah() {	

	if(document.${formName}.txtNoRujProsiding.value == ""){
		alert("Sila masukkan \"No. Ruj. Prosiding Mahkamah\" terlebih dahulu.");
  		document.${formName}.txtNoRujProsiding.focus(); 
		return;
	}
	if(document.${formName}.txtPerintahMahkamah.value == ""){
		alert("Sila masukkan \"Ringkasan Perintah Mahkamah\" terlebih dahulu.");
  		document.${formName}.txtPerintahMahkamah.focus(); 
		return;
	}
	if(document.${formName}.sorKptsnMahkamah.value == ""){
		alert("Sila masukkan \"Keputusan Mahkamah\" terlebih dahulu.");
  		document.${formName}.sorKptsnMahkamah.focus(); 
		return;
	}
	if(document.${formName}.txdTkhPerintah.value == ""){
		alert("Sila masukkan \"Tarikh Perintah\" terlebih dahulu.");
  		document.${formName}.txdTkhPerintah.focus(); 
		return;
	}
	if(document.${formName}.txdTkhTrmPerintah.value == ""){
		alert("Sila masukkan \"Tarikh Terima Perintah\" terlebih dahulu.");
  		document.${formName}.txdTkhTrmPerintah.focus(); 
		return;
	}
	if(document.${formName}.txtAmaunPamMahkamah.value == ""){
		alert("Sila masukkan \"Amaun Pampasan Mahkamah\" terlebih dahulu.");
  		document.${formName}.txtAmaunPamMahkamah.focus(); 
		return;
	}
	

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=updatePerintahMahkamah";
	document.${formName}.submit();
}

function batalPerintahMahkamah() {	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=tabPerintahMahkamah";
	document.${formName}.submit();
}

function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=baruBorangM";
	document.${formName}.submit();
}
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
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
