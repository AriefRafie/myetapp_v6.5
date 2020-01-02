<style type="text/css">
<!--
.style1 {color: #FF0000}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
#if($action != 'newPerundingan') 
<p>
#parse("app/ppt/frmHakmilikSementaraMaklumatPerundingan.jsp")
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0">Keterangan Tuan Tanah</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabNilaian('$idFail','$idPermohonan','$idSiasatan','$idHakmilik')">Nilaian</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTuntutan('$idFail','$idPermohonan','$idSiasatan','$idHakmilik')">Tuntutan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabBantahan('$idFail','$idPermohonan','$idSiasatan','$idHakmilik')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabKeputusan('$idFail','$idPermohonan','$idSiasatan','$idHakmilik')">Keputusan</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
    <fieldset>
    <table width="100%">
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuanTanah != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Tempoh Milik Tanah (Tahun)</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><label>
      <input name="txdTkhMilik" type="text" id="txdTkhMilik" value="$TEMPOH_MILIK_TANAH" size="5" maxlength="2" $readonlyTuanTanah class="$disabledTuanTanah" $disabledTuanTanah>
    </label></td>
    </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuanTanah != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Cara Milik Tanah</td>
    <td valign="top">:</td>
    <td width="70%" valign="top"><label>
      <textarea name="txtCaraMilik" id="txtCaraMilik" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyTuanTanah class="$disabledTuanTanah" $disabledTuanTanah>$CARA_MILIK</textarea>
    </label></td>
    </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuanTanah != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Harga Tanah (RM)</td>
    <td valign="top">:</td>
    <td width="70%" valign="top"><label>
      <input name="txtHargaTnh" type="text" id="txtHargaTnh" value="$HARGA_BELI" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();validateNumber(this,this.value);validateModal(this,this.value,'$txtHargaTnh');" onkeyup="this.value=this.value.toUpperCase();validateNumber(this,this.value);" $readonlyTuanTanah class="$disabledTuanTanah" $disabledTuanTanah>
    </label></td>
    </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuanTanah != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Bebanan Tanah</td>
    <td valign="top">:</td>
    <td width="70%" valign="top"><label>
      <textarea name="txtBebanan" id="txtBebanan" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyTuanTanah class="$disabledTuanTanah" $disabledTuanTanah>$BEBANAN</textarea>
    </label></td>
    </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuanTanah != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Keterangan Tuan Tanah/Wakil</td>
    <td valign="top">:</td>
    <td width="70%" valign="top"><label>
    <textarea name="txtKtrgnTuanTnh" id="txtKtrgnTuanTnh" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyTuanTanah class="$disabledTuanTanah" $disabledTuanTanah>$KETERANGAN_TUAN_TANAH</textarea>
    </label></td>
    </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuanTanah != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Jenis Tanaman</td>
    <td valign="top">:</td>
    <td width="70%" valign="top"><textarea name="txtJnsTanaman" id="txtJnsTanaman" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyTuanTanah class="$disabledTuanTanah" $disabledTuanTanah>$JENIS_TANAMAN</textarea></td>
    </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuanTanah != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Jenis Bangunan</td>
    <td valign="top">:</td>
    <td width="70%" valign="top"><textarea name="txtJnsBangunan" id="txtJnsBangunan" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyTuanTanah class="$disabledTuanTanah" $disabledTuanTanah>$JENIS_BANGUNAN</textarea></td>
    </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuanTanah != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Status Pecah Sempadan</td>
    <td valign="top">:</td>
    <td width="70%" valign="top">
    <select name="socStatusPecahSempadan" id="socStatusPecahSempadan" $readonlyTuanTanah class="$disabledTuanTanah" $disabledTuanTanah>
    #if($FLAG_PECAH_SEMPADAN == '0')
      <option value="0" selected="selected">SILA PILIH</option>
      <option value="1">DIBUAT</option>
      <option value="2">TIDAK DIBUAT</option>
    #end
    #if($FLAG_PECAH_SEMPADAN == '1')
      <option value="0">SILA PILIH</option>
      <option value="1" selected="selected">DIBUAT</option>
      <option value="2">TIDAK DIBUAT</option>
    #end
    #if($FLAG_PECAH_SEMPADAN == '2')
      <option value="0">SILA PILIH</option>
      <option value="1">DIBUAT</option>
      <option value="2" selected="selected">TIDAK DIBUAT</option>
    #end
    
    
    </select>    </td>
    </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuanTanah != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Tarikh Pecah Sempadan</td>
    <td valign="top">:</td>
    <td width="70%" valign="top">
<input name="txdTkhPecahSempadan" type="text" id="txdTkhPecahSempadan" value="$TARIKH_PECAH_SEMPADAN" size="10" $readonlytuantanah class="$disabledTuanTanah" $disabledtuantanah onblur="checking_validation(this,'tarikh_pecah_sempadan_check','yes','pecah sempadan','tarikh');" />   
#if($readonlyTuanTanah != 'readonly')   <a href="javascript:displayDatePicker('txdTkhPecahSempadan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52">dd/mm/yyyy</span> </td>
    </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuanTanah != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Status Tukar Syarat</td>
    <td valign="top">:</td>
    <td width="70%" valign="top">
    <select name="socStatusTukarSyarat" id="socStatusTukarSyarat" $readonlyTuanTanah class="$disabledTuanTanah" $disabledTuanTanah>
    #if($FLAG_TUKAR_SYARAT == '0')
      <option value="0" selected="selected">SILA PILIH</option>
      <option value="1">DIBUAT</option>
      <option value="2">TIDAK DIBUAT</option>
    #end
    #if($FLAG_TUKAR_SYARAT == '1')
      <option value="0">SILA PILIH</option>
      <option value="1" selected="selected">DIBUAT</option>
      <option value="2">TIDAK DIBUAT</option>
    #end
    #if($FLAG_TUKAR_SYARAT == '2')
      <option value="0">SILA PILIH</option>
      <option value="1">DIBUAT</option>
      <option value="2" selected="selected">TIDAK DIBUAT</option>
    #end
    
    </select>    </td>
    </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuanTanah != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Tarikh Tukar Syarat</td>
    <td valign="top">:</td>
    <td width="70%" valign="top">
     <input name="txdTkhPecahSyarat" type="text" id="txdTkhPecahSyarat" value="$TARIKH_TUKAR_SYARAT" size="10" $readonlyTuanTanah class="$disabledTuanTanah" $disabledTuanTanah onblur="checking_validation(this,'tarikh_tukar_syarat_check','yes','tukar syarat','tarikh');">
     #if($readonlyTuanTanah != 'readonly')  <a href="javascript:displayDatePicker('txdTkhPecahSyarat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>  #end<span class="style52">dd/mm/yyyy</span> </td>
    </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="28%" align="left" valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td width="70%" valign="top">&nbsp;</td>
    </tr>
</table>

    </fieldset>
    </div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<table width="100%">
  <tr>
    <td colspan="6" align="center">
    #if($modeTuanTanah == 'newTuanTanah')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanTuanTanah()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewTuanTanah()"/>
    #end 
    #if($modeTuanTanah == 'paparTuanTanah')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniTuanTanah()" />
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />
    #end
    #if($modeTuanTanah == 'kemaskiniTuanTanah')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanTuanTanah()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewTuanTanah()"/>
    #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliTuanTanah()" />
    </td>
    </tr>

</table>
#end
#if($action == 'newPerundingan')
<p>
<fieldset>
<legend><strong>Senarai Perundingan</strong></legend>
<table width="100%">
  <tr class="table_header">
    <td><strong>Bil</strong></td>
    <td width="15%"><strong>Bil Perundingan</strong></td>
    <td width="12%"><strong>No Lot/No PT</strong></td>
    <td width="20%"><strong>No. Perundingan</strong></td>
    <td width="20%"><strong>Tarikh Perundingan</strong></td>
    <td width="19%"><strong>Tempat</strong></td>
   
  </tr>
  #foreach ($listRunding in $SenaraiRundingan)

    #if ($listRunding.bil == '') 
    	#set ($row = 'row1')
    #elseif ($listRunding.bil % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
  <tr>
    <td class="$row">$listRunding.bil</td>
    #if ($listRunding.bil != '') 
    <td class="$row"><a href="javascript:paparRundingan('$listRunding.ID_SIASATAN','$listRunding.ID_HAKMILIK')"><font color="blue">$listRunding.NO_KES</font></a></td>
    #else
    <td class="$row">$listRunding.NO_KES</td>
    #end
    <td class="$row">$listRunding.NO_LOT</td>
    <td class="$row">$listRunding.NO_SIASATAN</td>
    <td class="$row">$listRunding.TARIKH_SIASATAN</td>
    <td class="$row">$listRunding.TEMPAT</td>
   
  </tr>
  #end
</table>
</fieldset>
#end
<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakNotaRundingan('$idFail')"><font color="blue">Nota Rundingan </font></a></td>
      </tr>           
    </table>
</fieldset>
<input name="id_siasatan" type="hidden" value="$idSiasatan" />
<input type="hidden" name="id_fail" value="$idFail" />
<input type="hidden" name="id_permohonan" value="$idPermohonan" />
<input name="idHakmilik" type="hidden" value="$idHakmilik" />
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
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
function cetakNotaRundingan(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.NotaRundingan?idfail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function tabNilaian(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabNilaian";
	document.${formName}.submit();
}
function tabTuntutan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabTuntutan";
	document.${formName}.submit();
}
function tabBantahan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabBantahan";
	document.${formName}.submit();
}
function tabKeputusan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabKeputusan";
	document.${formName}.submit();
}
function paparRundingan(id_siasatan,id_hakmilik) {

	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.idHakmilik.value = id_hakmilik;
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabTuanTanah";
	document.${formName}.submit();

}
function simpanTuanTanah(){
	
	if(document.${formName}.txdTkhMilik.value == ""){
		alert("Sila masukkan \"Tempoh Milik Tanah (Tahun)\" terlebih dahulu.");
  		document.${formName}.txdTkhMilik.focus(); 
		return;
	}
	if(document.${formName}.txtCaraMilik.value == ""){
		alert("Sila masukkan \"Cara Milik Tanah\" terlebih dahulu.");
  		document.${formName}.txtCaraMilik.focus(); 
		return;
	}
	if(document.${formName}.txtHargaTnh.value == ""){
		alert("Sila masukkan \"Harga Tanah\" terlebih dahulu.");
  		document.${formName}.txtHargaTnh.focus(); 
		return;
	}
	if(document.${formName}.txtBebanan.value == ""){
		alert("Sila masukkan \"Bebanan Tanah\" terlebih dahulu.");
  		document.${formName}.txtBebanan.focus(); 
		return;
	}
	if(document.${formName}.txtKtrgnTuanTnh.value == ""){
		alert("Sila masukkan \"Keterangan Tuan Tanah/Wakil\" terlebih dahulu.");
  		document.${formName}.txtKtrgnTuanTnh.focus(); 
		return;
	}
	if(document.${formName}.txtJnsTanaman.value == ""){
		alert("Sila masukkan \"Jenis Tanaman\" terlebih dahulu.");
  		document.${formName}.txtJnsTanaman.focus(); 
		return;
	}
	if(document.${formName}.txtJnsBangunan.value == ""){
		alert("Sila masukkan \"Jenis Bangunan\" terlebih dahulu.");
  		document.${formName}.txtJnsBangunan.focus(); 
		return;
	}
	if(document.${formName}.socStatusPecahSempadan.value == "0"){
		alert("Sila masukkan \"Status Pecah Sempadan\" terlebih dahulu.");
  		document.${formName}.socStatusPecahSempadan.focus(); 
		return;
	}
	if(document.${formName}.txdTkhPecahSempadan.value == ""){
		alert("Sila masukkan \"Tarikh Pecah Sempadan\" terlebih dahulu.");
  		document.${formName}.txdTkhPecahSempadan.focus(); 
		return;
	}
	if(document.${formName}.socStatusTukarSyarat.value == "0"){
		alert("Sila masukkan \"Status Tukar Syarat\" terlebih dahulu.");
  		document.${formName}.socStatusTukarSyarat.focus(); 
		return;
	}
	if(document.${formName}.txdTkhPecahSyarat.value == ""){
		alert("Sila masukkan \"Tarikh Pecah Syarat\" terlebih dahulu.");
  		document.${formName}.txdTkhPecahSyarat.focus(); 
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=simpanTuanTanah";
	document.${formName}.submit();
}
function batalNewTuanTanah() {

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabTuanTanah";
	document.${formName}.submit();

}
function kemaskiniTuanTanah() {

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=kemaskiniTuanTanah";
	document.${formName}.submit();

}
function batalViewTuanTanah() {

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabTuanTanah";
	document.${formName}.submit();

}
function kembaliTuanTanah() {

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=newPerundingan";
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