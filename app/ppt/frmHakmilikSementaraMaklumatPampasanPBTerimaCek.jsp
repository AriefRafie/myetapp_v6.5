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
     <li class="TabbedPanelsTab" tabindex="0" onclick="tabTuntutan('$id_fail','$id_permohonan','$idPihakBerkepentingan')">Tuntutan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabNilaian('$id_fail','$id_permohonan','$idPihakBerkepentingan')">Nilaian</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTerimaCek('$id_fail','$id_permohonan','$idPihakBerkepentingan')">Penerimaan Cek</li>
    #if($idBayaran != '')
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabSerahCek('$id_fail','$id_permohonan','$idPihakBerkepentingan')">Penyerahan Cek</li>
    #end
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabEFT('$id_fail','$id_permohonan','$idPihakBerkepentingan')">Bayaran Melalui EFT</li>
  </ul>
  
  <div class="TabbedPanelsContentGroup">
  
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
    	
    
    <fieldset><legend><strong>Daripada Agensi</strong></legend>
    <table width="100%">
      <tr>
        <td align="left" width="1%"><span class="style1">#if($readonlyTerimaCekB != 'readonly')*#end</span></td>
        <td align="left" width="20%">Tarikh Terima</td>
        <td width="1%">:</td>
        <td width="29%"><label>
          <input name="txdTkhTerima" type="text" id="txdTkhTerima" value="$TARIKH_TERIMA" size="10"  $readonlyTerimaCekB class="$disabledTerimaCekB" $disabledTerimaCekB onblur="checking_validation(this,'tarikh_terima_check','yes','terima','tarikh');">
        #if($readonlyTerimaCekB != 'readonly')   <a href="javascript:displayDatePicker('txdTkhTerima',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span>        </label></td>
        <td width="1%" align="left"><span class="style1">#if($readonlyTerimaCekB != 'readonly')*#end</span></td>
        <td width="20%" align="left">Amaun Cek</td>
        <td width="1%">:</td>
        <td width="29%"><label>
          <input name="txtAmaunCek" type="text" id="txtAmaunCek" value="$AMAUN_BAYARAN" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAnggaranHrgSeunit')" onkeyup="validateNumber(this,this.value);" $readonlyTerimaCekB class="$disabledTerimaCekB" $disabledTerimaCekB />
         </label></td>
      </tr>
      <tr>
        <td width="1%" align="left">&nbsp;</td>
        <td align="left">Penama Cek</td>
        <td>:</td>
        <td><label>
          <input name="txtPenamaCek" type="text" id="txtPenamaCek" value="$NAMA_PENERIMA" size="40" $readonlyTerimaCekA class="$disabledTerimaCekA" $disabledTerimaCekA>
        </label></td>
        <td width="1%" align="left"><span class="style1">#if($readonlyTerimaCekB != 'readonly')*#end</span></td>
        <td align="left">Tarikh Akhir Cek</td>
        <td>:</td>
        <td>
        <input name="txdTkhAkhirCek" type="text" id="txdTkhAkhirCek" value="$TARIKH_AKHIR_CEK" size="10"  $readonlyTerimaCekB class="$disabledTerimaCekB" $disabledTerimaCekB onblur="checking_validation(this,'tarikh_akhir_check','yes','akhir cek','tarikh');">
         #if($readonlyTerimaCekB != 'readonly') <a href="javascript:displayDatePicker('txdTkhAkhirCek',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span>        </td>
      </tr>
      <tr>
        <td width="1%" align="left"><span class="style1">#if($readonlyTerimaCekB != 'readonly')*#end</span></td>
        <td align="left">No. Cek</td>
        <td>:</td>
        <td><label>
          <input name="txtNoCek" type="text" id="txtNoCek" value="$NO_BAYARAN"  $readonlyTerimaCekB class="$disabledTerimaCekB" $disabledTerimaCekB>
        </label></td>
        <td width="1%" align="left"><span class="style1">#if($readonlyTerimaCekB != 'readonly')*#end</span></td>
        <td align="left">Tarikh Ambil Cek</td>
        <td>:</td>
        <td>
        <input name="txdTkhAmbilCek" type="text" id="txdTkhAmbilCek" value="$TARIKH_AMBIL_CEK" size="10"  $readonlyTerimaCekB class="$disabledTerimaCekB" $disabledTerimaCekB onblur="checking_validation(this,'tarikh_ambil_check','yes','ambil cek','tarikh');">
         #if($readonlyTerimaCekB != 'readonly') <a href="javascript:displayDatePicker('txdTkhAmbilCek',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span>        
         </td>
      </tr>
      <tr>
        <td align="left"><span class="style1">#if($readonlyTerimaCekB != 'readonly')*#end</span></td>
        <td align="left">Tarikh Cek</td>
        <td>:</td>
        <td>
         <input name="txdTkhCek" type="text" id="txdTkhCek" value="$TARIKH_CEK" size="10" $readonlyTerimaCekB class="$disabledTerimaCekB" $disabledTerimaCekB onblur="checking_validation(this,'tarikh_cek_check','yes','cek','tarikh');">
         #if($readonlyTerimaCekB != 'readonly') <a href="javascript:displayDatePicker('txdTkhCek',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span>        </td>
        <td align="left"><span class="style1">#if($readonlyTerimaCekB != 'readonly')*#end</span></td>
        <td align="left">Masa Ambil Cek</td>
        <td>:</td>
        <td><input name="txtMasaAmbilCek" type="text" id="txtMasaAmbilCek" value="$MASA_AMBIL_CEK" size="5" maxlength="5" $readonlyTerimaCekB class="$disabledTerimaCekB" $disabledTerimaCekB/></td>
      </tr>
    </table>
	</fieldset>
    
    </div>
    
     #if($idBayaran != '') 
     <div class="TabbedPanelsContent"></div> 
     #end
 
    <div class="TabbedPanelsContent"></div>
    
  </div>
</div>

<table width="100%">
  <tr>
    <td colspan="6" align="center">
    #if($modeTerimaCek == 'paparTerimaCek')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniTerimaCek()" />
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />
    #end
    #if($modeTerimaCek == 'newTerimaCek')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanTerimaCek()" />      
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewTerimaCek()" />
    #end
    #if($modeTerimaCek == 'updateTerimaCek')
	  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateTerimaCek()" />      
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewTerimaCek()" />    
    #end
    <!--  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliTerimaCek()" /> -->
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliTuntutanNew('$!idFail','$!id_permohonan')" />
   
    </td>
    </tr>

</table>
<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakSuratHadirSerahPampasan('$idFail','$idBayaran','$id_permohonan')"><font color="blue">Surat Kepada Pihak Berkepentingan - Serahan Cek </font></a></td>
      </tr>           
    </table>
</fieldset>
<input type="hidden" name="id_fail" id="id_fail" value="$idFail" />
<input type="hidden" name="id_permohonan" value="$id_permohonan" />
<input name="idPB" type="hidden" value="$idPihakBerkepentingan" />
<input name="id_hakmilik" type="hidden" value="$id_hakmilik" />
<input name="idSiasatan" type="hidden" value="$ID_SIASATAN" />
<input name="idTanah" type="hidden" value="$idTanah" />
<input name="idBayaran" type="hidden" id="idBayaran" value="$idBayaran" />
<input name="idBayaranEFT" type="hidden" value="$idBayaranEFT" />
<input name="idHakmilikPB" type="hidden" value="$idHakmilikPB" />
<input type="hidden" name="id_status" value="$!id_status">


<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});
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
function cetakSuratHadirSerahPampasan(id_fail,idBayaran,id_permohonan) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?idfail="+id_fail+"&id_bayaran="+idBayaran+"&id_permohonan="+id_permohonan+"&report=suratUtkPanggilanTerimaPampasanKpdPB&flagReport=S";
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
function tabNilaian(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabNilaian";
	document.${formName}.submit();
}
function tabSerahCek(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabSerahCek";
	document.${formName}.submit();
}
function tabEFT(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabEFT";
	document.${formName}.submit();
}
function simpanTerimaCek(){
	
	 var cek  = document.${formName}.txdTkhCek.value;
	  
	  var dt1Cek   = parseInt(cek.substring(0,2),10);
	  var mon1Cek  = parseInt(cek.substring(3,5),10)-1;
	  var yr1Cek   = parseInt(cek.substring(6,10),10);
		   
	  var dateCek = new Date(yr1Cek, mon1Cek, dt1Cek);
	  
	  var terima  = document.${formName}.txdTkhTerima.value;
	  
	  var dt1Terima  = parseInt(terima.substring(0,2),10);
	  var mon1Terima  = parseInt(terima.substring(3,5),10)-1;
	  var yr1Terima   = parseInt(terima.substring(6,10),10);
		   
	  var dateTerima = new Date(yr1Terima, mon1Terima, dt1Terima);
	  
	  var akhir  = document.${formName}.txdTkhAkhirCek.value;
	  
	  var dt1Akhir  = parseInt(akhir.substring(0,2),10);
	  var mon1Akhir  = parseInt(akhir.substring(3,5),10)-1;
	  var yr1Akhir   = parseInt(akhir.substring(6,10),10);
		   
	  var dateAkhir = new Date(yr1Akhir, mon1Akhir, dt1Akhir);
	  
	if(dateCek > dateTerima){
		alert("Tarikh Cek tidak boleh melebihi dari Tarikh Terima. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTkhCek.focus(); 
		return;
 	}
	if(dateCek > dateAkhir){
		alert("Tarikh Cek tidak boleh melebihi dari Tarikh Akhir Cek. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTkhAkhirCek.focus(); 
		return;
 	}

	if(document.${formName}.txdTkhTerima.value == ""){
		alert("Sila masukkan \"Tarikh Terima\" terlebih dahulu.");
  		document.${formName}.txdTkhTerima.focus(); 
		return;
	}
	if(document.${formName}.txtNoCek.value == ""){
		alert("Sila masukkan \"No Cek\" terlebih dahulu.");
  		document.${formName}.txtNoCek.focus(); 
		return;
	}
	if(document.${formName}.txdTkhCek.value == ""){
		alert("Sila masukkan \"Tarikh Cek\" terlebih dahulu.");
  		document.${formName}.txdTkhCek.focus(); 
		return;
	}
	if(document.${formName}.txtAmaunCek.value == ""){
		alert("Sila masukkan \"Amaun Cek\" terlebih dahulu.");
  		document.${formName}.txtAmaunCek.focus(); 
		return;
	}
	if(document.${formName}.txdTkhAkhirCek.value == ""){
		alert("Sila masukkan \"Tarikh Akhir Cek\" terlebih dahulu.");
  		document.${formName}.txdTkhAkhirCek.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=simpanTerimaCek";
	document.${formName}.submit();
}
function kemaskiniTerimaCek(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=kemaskiniTerimaCek";
	document.${formName}.submit();
}
function updateTerimaCek(){

	if(document.${formName}.txdTkhTerima.value == ""){
		alert("Sila masukkan \"Tarikh Terima\" terlebih dahulu.");
  		document.${formName}.txdTkhTerima.focus(); 
		return;
	}
	if(document.${formName}.txtNoCek.value == ""){
		alert("Sila masukkan \"No Cek\" terlebih dahulu.");
  		document.${formName}.txtNoCek.focus(); 
		return;
	}
	if(document.${formName}.txdTkhCek.value == ""){
		alert("Sila masukkan \"Tarikh Cek\" terlebih dahulu.");
  		document.${formName}.txdTkhCek.focus(); 
		return;
	}
	if(document.${formName}.txtAmaunCek.value == ""){
		alert("Sila masukkan \"Amaun Cek\" terlebih dahulu.");
  		document.${formName}.txtAmaunCek.focus(); 
		return;
	}
	if(document.${formName}.txdTkhAkhirCek.value == ""){
		alert("Sila masukkan \"Tarikh Akhir Cek\" terlebih dahulu.");
  		document.${formName}.txdTkhAkhirCek.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=updateTerimaCek";
	document.${formName}.submit();
}
function batalNewTerimaCek(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabTerimaCek";
	document.${formName}.submit();
}
function batalViewTerimaCek(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabTerimaCek";
	document.${formName}.submit();
}
function kembaliTerimaCek(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=newPampasanPB";
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
