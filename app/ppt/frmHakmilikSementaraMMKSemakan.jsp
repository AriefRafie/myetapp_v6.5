<style type="text/css">
<!--
.style1 {color: #FF0000}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>


#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp") 

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabPenyediaan('$idFail','$idPermohonan')">Penyediaan</li>
    <li class="TabbedPanelsTab" tabindex="0">Semakan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabKeputusan('$idFail','$idPermohonan')">Keputusan PBN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
    <table width="100%">
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="20%" align="left" valign="top">Nama Pegawai</td>
    <td width="1%" valign="top">:</td>
    <td width="29%" valign="top"><label>
      <input name="txtNamaPegawai" type="text" class="disabled" id="txtNamaPegawai" value="$NAMAPEGAWAI" readonly="readonly">
    </label></td>
    <td width="1%" rowspan="3" align="left" valign="top"><span class="style1">*</span></td>
    <td width="20%" rowspan="3" align="left" valign="top">Ulasan</td>
    <td width="1%" valign="top">:</td>
    <td width="29%" rowspan="3"><textarea name="txtUlasan" id="txtUlasan" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlySemak class="$disabledSemak">$ULASANSEMAKAN</textarea></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td align="left">Tarikh Semakan</td>
    <td>:</td>
    <td><label>
      <input name="txdTarikhSemakan" type="text" class="$disabledSemak" id="txdTarikhSemakan" value="$TARIKHSEMAKAN" size="10" $readonlySemak  onblur="checking_validation(this,'tarikh_semak_check','yes','semakan','tarikh');">
     #if($readonlySemak != 'readonly') <a href="javascript:displayDatePicker('txdTarikhSemakan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end
     <span class="style52">dd/mm/yyyy</span></label></td>
    <td>&nbsp;</td>
    </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td align="left">Keputusan Semakan</td>
    <td>:</td>
    <td><select name="socKeputusan" id="socKeputusan" $readonlysemak class="$disabledSemak" $disabledsemak>
      
      #if($KEPUTUSANSEMAKAN == '')
      
      <option selected="selected">SILA PILIH</option>
      <option value="2">BELUM DISEMAK</option>
      <option value="1">TELAH DISEMAK</option>
      
      #end
      #if($KEPUTUSANSEMAKAN == '2')
      
      <option>SILA PILIH</option>
      <option value="2" selected="selected">BELUM DISEMAK</option>
      <option value="1">TELAH DISEMAK</option>
      
      #end
      #if($KEPUTUSANSEMAKAN == '1')
      
      <option>SILA PILIH</option>
      <option value="2">BELUM DISEMAK</option>
      <option value="1" selected="selected">TELAH DISEMAK</option>
      
      #end
    
    </select>    </td>
    <td valign="top">&nbsp;</td>
    </tr>
</table>
    </div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<table width="100%">
  <tr>
    <td colspan="6" align="center">
     #if($modeSemak == 'newMMK')
       <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanSemakan('$idMMK')" />
       <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalSemakanNew()" />

    #end
    #if($modeSemak == 'paparMMK')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniSemakan()" />
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')"/>
    <!--  <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusSemakan()" />-->
    #end
    #if($modeSemak == 'kemaskiniMMK')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateSemakan()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalSemakanUpdate()" />

    #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
    </td>
    </tr>

</table>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td>
        
        #if($negeriMMK=="2")
		 
		 #elseif($negeriMMK=="3")
		 	
		 #elseif($negeriMMK=="4")
		 	
		 #elseif($negeriMMK=="5")
		 	<a href="#" class="style2" onClick="javascript:cetakMMKNS('$idFail')"><font color="blue"> Kertas MMK/KM/MB/LC </font></a>
		 #elseif($negeriMMK=="6")
		 
		 #elseif($negeriMMK=="7")
		 	
		 #elseif($negeriMMK=="8")
		 	
		 #elseif($negeriMMK=="9")
		 	
		 #elseif($negeriMMK=="10")
		 	<a href="#" class="style2" onClick="javascript:cetakMMKSelangor('$idFail')"><font color="blue"> Kertas MMK/KM/MB/LC </font></a>
		 #elseif($negeriMMK=="11")
		 	
		 #elseif($negeriMMK=="14")
		 	<a href="#" class="style2" onClick="javascript:cetakMMKKL('$idFail')"><font color="blue"> Kertas MMK/KM/MB/LC </font></a>
		 #else
		 	
		 #end
        
        
        
        
        
        
        </td>
      </tr>           
    </table>
</fieldset>
<input name="id_fail" type="hidden" value="$idFail" />
<input name="id_permohonan" type="hidden" value="$idPermohonan" />
<input name="idMMK" type="hidden" value="$idMMK" />
<input name="modeSemak" type="hidden" value="$modeSemak" />

<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
//-->
</script>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakMMKNS(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.MMKSementara?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function cetakMMKSelangor(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.SementaraMMKSelangor?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function cetakMMKKL(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.SementaraMMKKL?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function tabPenyediaan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=newMMK";
	document.${formName}.submit();
}
function tabKeputusan(id_fail,id_permohonan){
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=tabKeputusan";
	document.${formName}.submit();
}
function simpanSemakan(idMMK){

	if(document.${formName}.txdTarikhSemakan.value == ""){
		alert("Sila masukkan \"Tarikh Semakan\" terlebih dahulu.");
  		document.${formName}.txdTarikhSemakan.focus(); 
		return;
	}
	else if(document.${formName}.socKeputusan.value == ""){
		alert("Sila pilih \"Keputusan Semakan\" terlebih dahulu.");
  		document.${formName}.socKeputusan.focus(); 
		return;
	}
	else if(document.${formName}.txtUlasan.value == ""){
		alert("Sila masukkan \"Ulasan\" terlebih dahulu.");
  		document.${formName}.txtUlasan.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idMMK.value = idMMK;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=simpanSemakan";
	document.${formName}.submit();
}
function kemaskiniSemakan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=kemaskiniSemakan";
	document.${formName}.submit();
}
function hapusSemakan(){
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=hapusSemakan";
	document.${formName}.submit();
}
function updateSemakan(){

	if(document.${formName}.txdTarikhSemakan.value == ""){
		alert("Sila masukkan \"Tarikh Semakan\" terlebih dahulu.");
  		document.${formName}.txdTarikhSemakan.focus(); 
		return;
	}
	else if(document.${formName}.socKeputusan.value == ""){
		alert("Sila pilih \"Keputusan Semakan\" terlebih dahulu.");
  		document.${formName}.socKeputusan.focus(); 
		return;
	}
	else if(document.${formName}.txtUlasan.value == ""){
		alert("Sila masukkan \"Ulasan\" terlebih dahulu.");
  		document.${formName}.txtUlasan.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=updateSemakan";
	document.${formName}.submit();
}
function batalSemakanUpdate(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=batalSemakanUpdate";
	document.${formName}.submit();
}
function batalSemakanNew(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=batalbatalSemakanNew";
	document.${formName}.submit();
}
function kembali(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=";
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