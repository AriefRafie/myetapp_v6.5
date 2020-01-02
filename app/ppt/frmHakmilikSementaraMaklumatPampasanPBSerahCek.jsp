<style type="text/css">
<!--
.style1 {color: #FF0000}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPampasanPB.jsp") 
<p>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
  
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTuntutan('$idFail','$id_permohonan','$idPihakBerkepentingan')">Tuntutan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabNilaian('$idFail','$id_permohonan','$idPihakBerkepentingan')">Nilaian</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTerimaCek('$idFail','$id_permohonan','$idPihakBerkepentingan')">Penerimaan Cek</li>
    #if($idBayaran != '')
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabSerahCek('$idFail','$id_permohonan','$idPihakBerkepentingan')" >Penyerahan Cek</li>
    #end
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabEFT('$idFail','$id_permohonan','$idPihakBerkepentingan')">Bayaran Melalui EFT</li>
    
  </ul>
  
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
    <table width="100%">
      <tr>
        <td align="left" width="20%">Penerima Cek/Wakil</td>
        <td width="1%">:</td>
        <td width="29%"><label>
          <input name="txtPenerima" type="text" class="$disabledSerahCekA" id="txtPenerima" value="$NAMA_PENERIMA" size="40" $readonlySerahCekA $disabledSerahCekA>
        </label></td>
        <td align="left" width="1%"><span class="style1">#if($readonlySerahCekB != 'readonly')*#end</span></td>
        <td align="left" width="20%">Tarikh Serah Cek</td>
        <td width="1%">:</td>
        <td width="29%"><label>
            <input name="txdTkhSerah" type="text" class="$disabledSerahCekB" id="txdTkhSerah" value="$TARIKH_SERAH_CEK" size="10" $readonlySerahCekB $disabledSerahCekB onblur="checking_validation(this,'tarikh_serah_check','yes','serah cek','tarikh')";/>
         #if($readonlySerahCekB != 'readonly')   <a href="javascript:displayDatePicker('txdTkhSerah',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span>        </label></td>
      </tr>
      <tr>
        <td align="left">No. KP</td>
        <td>:</td>
        <td><label>
          <input name="txtNoKP" type="text" class="$disabledSerahCekA" id="txtNoKP" value="$NO_PB" $readonlySerahCekA $disabledSerahCekA>
        </label></td>
        <td width="1%" align="left"><span class="style1">#if($readonlySerahCekB != 'readonly')*#end</span></td>
        <td align="left">Status Penyerahan Cek</td>
        <td>:</td>
        <td><select name="socStatusSerahCek" id="socStatusSerahCek" $readonlySerahCekB class="$disabledSerahCekB" $disabledSerahCekB style="width:85px">
        #if($FLAG_SERAH_CEK == '0')
          <option value="0" selected="selected">SILA PILIH</option>
          <option value="1">DISERAHKAN</option>
          <option value="2">TIDAK DISERAHKAN</option>
        #end
         #if($FLAG_SERAH_CEK == '1')
          <option value="0">SILA PILIH</option>
          <option value="1" selected="selected">DISERAHKAN</option>
          <option value="2">TIDAK DISERAHKAN</option>
        #end
         #if($FLAG_SERAH_CEK == '2')
          <option value="0">SILA PILIH</option>
          <option value="1">DISERAHKAN</option>
          <option value="2" selected="selected">TIDAK DISERAHKAN</option>
        #end
        </select>        
        </td>
      </tr>
    </table>

    </div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<table width="100%">
  <tr>
    <td colspan="6" align="center">
    #if($modeSerahCek == 'paparSerahCek')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniSerahCek()" />
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />
    #end
    #if($modeSerahCek == 'updateSerahCek')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateSerahCek()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewSerahCek()" />
    #end
     <!-- <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliSerahCek()" />-->
     
     <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliTuntutanNew('$!idFail','$!id_permohonan')" />
         </td>
    </tr>
</table>
<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakSuratSerahanCek('$idFail','$idBayaran','$id_permohonan')"><font color="blue">Surat Kepada Agensi Pemohon - Makluman Serahan Cek </font></a></td>
      </tr>           
    </table>
</fieldset>
<input type="hidden" name="id_fail" value="$idFail" />
<!--<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />-->
<input type="hidden" name="id_permohonan" value="$id_permohonan" />
<input name="idPB" type="hidden" value="$idPihakBerkepentingan" />
<input name="id_hakmilik" type="hidden" value="$id_hakmilik" />
<input name="idSiasatan" type="hidden" value="$ID_SIASATAN" />
<input name="idTanah" type="hidden" value="$idTanah" />
<input name="idBayaran" type="hidden" value="$idBayaran" />
<input name="idBayaranEFT" type="hidden" value="$idBayaranEFT" />
<input name="idHakmilikPB" type="hidden" value="$idHakmilikPB" />
<input type="hidden" name="id_status" value="$!id_status">

<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:3});
//-->
</script>
<script>
function kembaliTuntutanNew(id_fail,id_permohonan){
	
	document.${formName}.id_permohonan.value = id_permohonan;	
	document.${formName}.command.value = "viewlistHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB";
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
function cetakSuratSerahanCek(id_fail,id_bayaran,id_permohonan) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	//var url = "../servlet/ekptg.report.ppt.suratMaklumanSerahBayaranPampasanKpdAP?idFail="+id_fail+"&idBayaran="+id_bayaran;
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?idfail="+id_fail+"&id_bayaran="+id_bayaran+"&id_permohonan="+id_permohonan+"&report=suratMaklumanSerahBayaranPampasanKpdAP&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function tabTuntutan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabTuntutan";
	document.${formName}.submit();
}
function tabTerimaCek(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabTerimaCek";
	document.${formName}.submit();
}
function tabNilaian(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabNilaian";
	document.${formName}.submit();
}
function tabEFT(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabEFT";
	document.${formName}.submit();
}
function kemaskiniSerahCek(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=kemaskiniSerahCek";
	document.${formName}.submit();
}
function updateSerahCek(){
	if(document.${formName}.txdTkhSerah.value == ""){
		alert("Sila masukkan \"Tarikh Serah Cek\" terlebih dahulu.");
  		document.${formName}.txdTkhSerah.focus(); 
		return;
	}
	if(document.${formName}.socStatusSerahCek.value == ""){
		alert("Sila masukkan \"Status Penyerahan Cek\" terlebih dahulu.");
  		document.${formName}.socStatusSerahCek.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=updateSerahCek";
	document.${formName}.submit();
}
function batalViewSerahCek(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabSerahCek";
	document.${formName}.submit();
}
function kembaliSerahCek(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=newPampasanPB";
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