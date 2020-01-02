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
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTuntutan('$idFail','$idPermohonan','$idPihakBerkepentingan')">Tuntutan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabNilaian('$idFail','$idPermohonan','$idPihakBerkepentingan')">Nilaian</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTerimaCek('$idFail','$idPermohonan','$idPihakBerkepentingan')">Penerimaan Cek</li>
    #if($idBayaran != '')
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabSerahCek('$idFail','$idPermohonan','$idPihakBerkepentingan')">Penyerahan Cek</li>
    #end
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabEFT('$idFail','$idPermohonan','$idPihakBerkepentingan')" >Bayaran Melalui EFT</li>
  </ul>
  
  
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    #if($idBayaran != '')
    <div class="TabbedPanelsContent"></div>
    #end
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
    <fieldset>
    <table width="100%">
      <tr>
        <td align="left" width="1%"><span class="style1">#if($readonlyEFTB != 'readonly')*#end</span></td>
        <td align="left" width="28%">No. Ruj. Surat</td>
        <td width="1%">:</td>
        <td width="70%"><input name="txtNoRujSurat" type="text" id="txtNoRujSurat" value="$NO_RUJUKAN_SURATEFT" $readonlyEFTB class="$disabledEFTB" $disabledEFTB></td>
        </tr>
      <tr>
        <td width="1%" align="left"><span class="style1">#if($readonlyEFTB != 'readonly')*#end</span></td>
        <td width="28%" align="left">Tarikh Surat</td>
        <td>:</td>
        <td width="70%"><input name="txdTkhSurat" type="text" id="txdTkhSurat" value="$TARIKH_SURAT_EFT" size="10" $readonlyEFTB class="$disabledEFTB" $disabledEFTB onblur="checking_validation(this,'tarikh_surat_check','yes','surat','tarikh');">
          #if($readonlyEFTB != 'readonly')  <a href="javascript:displayDatePicker('txdTkhSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span></td>
        </tr>
      <tr>
        <td align="left"><span class="style1">#if($readonlyEFTB != 'readonly')*#end</span></td>
        <td width="28%" align="left">Tarikh Terima Surat</td>
        <td>:</td>
        <td width="70%"><input name="txdTkhTerimaSurat" type="text" id="txdTkhTerimaSurat" value="$TARIKH_TERIMA_EFT" size="10" $readonlyEFTB class="$disabledEFTB" $disabledEFTB onblur="checking_validation(this,'tarikh_terima_surat_check','yes','terima surat','tarikh');">
       #if($readonlyEFTB != 'readonly') <a href="javascript:displayDatePicker('txdTkhTerimaSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end       </td>
        </tr>
      <tr>
        <td align="left">&nbsp;</td>
        <td width="28%" align="left">Nama Kementerian</td>
        <td>:</td>
        <td width="70%"><input name="txtNamaKementerian" type="text" class="$disabledEFTA" id="txtNamaKementerian" value="$nama_kementerian" size="60" $readonlyEFTA $disabledEFTA /></td>
        </tr>
    </table>

    </fieldset>
    <fieldset><legend><strong>Maklumat EFT</strong></legend>
    <table width="100%">
      <tr>
        <td align="left" width="1%"><span class="style1">#if($readonlyEFTB != 'readonly')*#end</span></td>
        <td align="left" width="20%">No. EFT</td>
        <td width="1%">:</td>
        <td width="29%"><input name="txtNoEFT" type="text" id="txtNoEFT" value="$NO_EFT" $readonlyEFTB class="$disabledEFTB" $disabledEFTB></td>
        <td align="left" width="1%"><span class="style1">#if($readonlyEFTB != 'readonly')*#end</span></td>
        <td align="left" width="20%">Amaun (RM)</td>
        <td width="1%">:</td>
        <td width="29%"><input name="txtAmaun" type="text" id="txtAmaun" value="$AMAUN_BAYARAN" $readonlyEFTB class="$disabledEFTB" $disabledEFTB onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAnggaranHrgSeunit')" onkeyup="validateNumber(this,this.value);"></td>
      </tr>
      <tr>
        <td width="1%" align="left"><span class="style1">#if($readonlyEFTB != 'readonly')*#end</span></td>
        <td align="left">Tarikh EFT</td>
        <td>:</td>
        <td><input name="txdTkh" type="text" id="txdTkh" value="$TARIKH_BAYARAN_EFT" size="10" $readonlyEFTB class="$disabledEFTB" $disabledEFTB onblur="checking_validation(this,'tarikh_eft_check','yes','EFT','tarikh');">
       #if($readonlyEFTB != 'readonly') <a href="javascript:displayDatePicker('txdTkh',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span></td>
        <td width="1%" align="left">&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="left">&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td colspan="8" align="left">
          <div align="center">
          #if($modeEFT == 'paparEFT')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniEFT()" />
            <!--<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak"  onclick="setTable('tableReport1')" />-->
            #end
            #if($modeEFT == 'newEFT')
            <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanEFT()" />
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewEFT()"/>
            #end
            #if($modeEFT == 'updateEFT')
            <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateEFT()" />
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewEFT()" />
            #end
          <!--  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliEFT()" /> -->
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliTuntutanNew('$!idFail','$!id_permohonan')" />
          </div></td>
        </tr>
    </table>

    </fieldset>
    <fieldset><legend><strong>Senarai Bayaran Melalui EFT</strong></legend>
    <table width="100%">
      <tr>
        <td colspan="4"><label>
          #if($modeEFT != 'newEFT')
          <input type="button" name="cmdTambahEFT" id="cmdTambahEFT" value="Tambah" onclick="tambahEFT()">
          #end
        </label></td>
        </tr>
      <tr class="table_header">
      	<td><strong>Bil</strong></td>
        <td><strong>No. EFT</strong></td>
        <td><strong>Amaun (RM)</strong></td>
        <td><strong>Tarikh</strong></td>
      </tr>
       #foreach ($eft in $SenaraiEFT)
  
  #if ($eft.bil == '') 
  	#set ($row = 'row1')
  #elseif ($eft.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
      <tr>
        <td class="$row">$eft.bil</td>
       	 #if ($eft.bil != '') 
        <td class="$row"><a href="javascript:paparEFT('$eft.ID_BAYARAN')"><font color="blue">$eft.NO_EFT</font></a></td>
        #else
          <td class="$row">$eft.NO_EFT</td>
        #end
        <td class="$row">$eft.AMAUN_BAYARAN</td>
        <td class="$row">$eft.TARIKH_BAYARAN_EFT</td>
      </tr>
   #end
    </table>

    </fieldset>
    </div>
  </div>
</div>
<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakMMK()"><font color="blue">Pampasan </font></a></td>
      </tr>           
    </table>
</fieldset>
<!--<input type="hidden" name="id_fail" value="$idFail" />-->
<input type="hidden" name="id_fail" id="id_fail" value="$idFail" />
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
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:4});
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
function tabSerahCek(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabSerahCek";
	document.${formName}.submit();
}
function tabNilaian(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabNilaian";
	document.${formName}.submit();
}
function simpanEFT(){

  	  var surat  = document.${formName}.txdTkhSurat.value;
	  
	  var dt1Surat   = parseInt(surat.substring(0,2),10);
	  var mon1Surat  = parseInt(surat.substring(3,5),10)-1;
	  var yr1Surat   = parseInt(surat.substring(6,10),10);
		   
	  var dateSurat = new Date(yr1Surat, mon1Surat, dt1Surat);
	  
	  var terima  = document.${formName}.txdTkhTerimaSurat.value;
	  
	  var dt1Terima  = parseInt(terima.substring(0,2),10);
	  var mon1Terima  = parseInt(terima.substring(3,5),10)-1;
	  var yr1Terima   = parseInt(terima.substring(6,10),10);
		   
	  var dateTerima = new Date(yr1Terima, mon1Terima, dt1Terima);
	  
	if(dateSurat > dateTerima){
		alert("Tarikh Surat tidak boleh melebihi dari Tarikh Terima Surat. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTkhSurat.focus(); 
		return;
 	}

	if(document.${formName}.txtNoRujSurat.value == ""){
		alert("Sila masukkan \"No. Ruj. Surat\" terlebih dahulu.");
  		document.${formName}.txtNoRujSurat.focus(); 
		return;
	}
	if(document.${formName}.txdTkhSurat.value == ""){
		alert("Sila masukkan \"Tarikh Surat\" terlebih dahulu.");
  		document.${formName}.txdTkhSurat.focus(); 
		return;
	}
	if(document.${formName}.txdTkhTerimaSurat.value == ""){
		alert("Sila masukkan \"Tarikh Terima Surat\" terlebih dahulu.");
  		document.${formName}.txdTkhTerimaSurat.focus(); 
		return;
	}
	if(document.${formName}.txtNoEFT.value == ""){
		alert("Sila masukkan \"No EFT\" terlebih dahulu.");
  		document.${formName}.txtNoEFT.focus(); 
		return;
	}
	if(document.${formName}.txdTkh.value == ""){
		alert("Sila masukkan \"Tarikh Bayaran EFT\" terlebih dahulu.");
  		document.${formName}.txdTkh.focus(); 
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert("Sila masukkan \"Amaun Bayaran\" terlebih dahulu.");
  		document.${formName}.txtAmaun.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=simpanEFT";
	document.${formName}.submit();
}
function kemaskiniEFT(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=kemaskiniEFT";
	document.${formName}.submit();
}
function updateEFT(){
	
	 var surat  = document.${formName}.txdTkhSurat.value;
	  
	  var dt1Surat   = parseInt(surat.substring(0,2),10);
	  var mon1Surat  = parseInt(surat.substring(3,5),10)-1;
	  var yr1Surat   = parseInt(surat.substring(6,10),10);
		   
	  var dateSurat = new Date(yr1Surat, mon1Surat, dt1Surat);
	  
	  var terima  = document.${formName}.txdTkhTerimaSurat.value;
	  
	  var dt1Terima  = parseInt(terima.substring(0,2),10);
	  var mon1Terima  = parseInt(terima.substring(3,5),10)-1;
	  var yr1Terima   = parseInt(terima.substring(6,10),10);
		   
	  var dateTerima = new Date(yr1Terima, mon1Terima, dt1Terima);
	  
	if(dateSurat > dateTerima){
		alert("Tarikh Surat tidak boleh melebihi dari Tarikh Terima Surat. Sila masukkan tarikh dengan betul.")
		document.${formName}.txdTkhSurat.focus(); 
		return;
 	}

	if(document.${formName}.txtNoRujSurat.value == ""){
		alert("Sila masukkan \"No. Ruj. Surat\" terlebih dahulu.");
  		document.${formName}.txtNoRujSurat.focus(); 
		return;
	}
	if(document.${formName}.txdTkhSurat.value == ""){
		alert("Sila masukkan \"Tarikh Surat\" terlebih dahulu.");
  		document.${formName}.txdTkhSurat.focus(); 
		return;
	}
	if(document.${formName}.txdTkhTerimaSurat.value == ""){
		alert("Sila masukkan \"Tarikh Terima Surat\" terlebih dahulu.");
  		document.${formName}.txdTkhTerimaSurat.focus(); 
		return;
	}
	if(document.${formName}.txtNoEFT.value == ""){
		alert("Sila masukkan \"No EFT\" terlebih dahulu.");
  		document.${formName}.txtNoEFT.focus(); 
		return;
	}
	if(document.${formName}.txdTkh.value == ""){
		alert("Sila masukkan \"Tarikh Bayaran EFT\" terlebih dahulu.");
  		document.${formName}.txdTkh.focus(); 
		return;
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert("Sila masukkan \"Amaun Bayaran\" terlebih dahulu.");
  		document.${formName}.txtAmaun.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=updateEFT";
	document.${formName}.submit();
}
function batalNewEFT(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabEFT";
	document.${formName}.submit();
}
function batalViewEFT(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=paparEFT";
	document.${formName}.submit();
}
function kembaliEFT(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=newPampasanPB";
	document.${formName}.submit();
}
function paparEFT(id_bayaran){
	document.${formName}.idBayaranEFT.value = id_bayaran; 
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=paparEFT";
	document.${formName}.submit();
}
function tambahEFT(){
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabEFT";
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
