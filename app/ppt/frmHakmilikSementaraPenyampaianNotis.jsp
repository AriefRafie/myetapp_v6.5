<style type="text/css">
<!--
.style1 {color: #FF0000}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>

<p>#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp") </p>
<p>
<fieldset><legend><strong>Penyampaian Notis</strong></legend>
<table width="100%">
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">Nama Penghantar Notis</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtNamaPenghantarNotis" type="text" class="$disabled" id="txtNamaPenghantarNotis" value="$namaPenghantarNotis" size="44" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonly $disabled >
    </label></td>
  </tr>
  <!--<tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Kod Perihal Dokumen</td>
    <td>:</td>
    <td width="70%">$SelectJenisDokumen</td>
  </tr>-->
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Status Serahan</td>
    <td valign="top">:</td>
    <td width="70%" valign="top">
    <select name="socStatusSerahan" size="1" id="socStatusSerahan" class="$disabled" $readonly $disabled onchange="showField(this.value)" >
     
      
    
     #if($statusSerahan == '')
     
      <option value="0">SILA PILIH</option>
      <option value="1" selected="selected">DISERAHKAN</option>
      <option value="2">TIDAK DISERAHKAN</option>
       
     #end
     #if($statusSerahan == '1')
     
      <option value="0">SILA PILIH</option>
      <option value="1" selected="selected">DISERAHKAN</option>
      <option value="2">TIDAK DISERAHKAN</option>
       
     #end
     #if($statusSerahan == '2')
     
      <option value="0">SILA PILIH</option> 
      <option value="1">DISERAHKAN</option>
      <option value="2" selected="selected">TIDAK DISERAHKAN</option> 
     #end
    
    
    </select>      </td>
  </tr>
  <tr id="tidakdiserahkan" style="visibility:collapse">
    <td align="left">&nbsp;</td>
    <td align="left">Tarikh Tampal Notis</td>
    <td>:</td>
    <td><input name="txdTarikhTampal" type="text" id="txdTarikhTampal" size="10" />      #if($readonly != 'readonly')  <a href="javascript:displayDatePicker('txdTarikhTampal',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>#end <span class="style52">dd/mm/yyyy</span> </td>
  </tr>
  <tr id="diserahkan1" style="visibility:visible">
    <td width="1%" align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left">Jenis Serahan</td>
    <td>:</td>
    <td width="70%"><select name="socJenisSerahan" size="1" id="socJenisSerahan" class="$disabled" $readonly $disabled>
        
        
    #if($jenisSerahan == '')
      <option value="0" selected="selected">SILA PILIH</option>
      <option value="1">PERKHIDMATAN POS</option>
      <option value="2">SERAHAN TANGAN</option>
      <option value="3">FAKSIMILI</option>
     
      
    #end 
    #if($jenisSerahan == '1')
       <option value="0">SILA PILIH</option>
      <option value="1" selected="selected">PERKHIDMATAN POS</option>
      <option value="2">SERAHAN TANGAN</option>
      <option value="3">FAKSIMILI</option>
     
      
    #end 
    #if($jenisSerahan == '2')
       <option value="0">SILA PILIH</option>
      <option value="1">PERKHIDMATAN POS</option>
      <option value="2" selected="selected">SERAHAN TANGAN</option>
      <option value="3">FAKSIMILI</option>
     
      
    #end 
    #if($jenisSerahan == '3')
       <option value="0">SILA PILIH</option>
      <option value="1">PERKHIDMATAN POS</option>
      <option value="2">SERAHAN TANGAN</option>
      <option value="3" selected="selected">FAKSIMILI</option>
    #end 
    
    
    </select>    </td>
  </tr>
  <tr id="diserahkan2" style="visibility:visible">
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">Tarikh Serahan</td>
    <td>:</td>
    <td width="70%"><label>
      <input name="txdTarikhSerahan" type="text" id="txdTarikhSerahan" value="$tarikhHantar" size="10" class="$disabled" $readonly $disabled onblur="checking_validation(this,'tarikh_serahan_check','yes','serahan','tarikh');">
    #if($readonly != 'readonly')  <a href="javascript:displayDatePicker('txdTarikhSerahan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>#end <span class="style52">dd/mm/yyyy</span>    </label></td>
  </tr>
  <tr id="diserahkan3" style="visibility:visible">
    <td width="1%" align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left">Nama Penerima</td>
    <td>:</td>
    <td width="70%">$SelectNamaPB 
    <input name="txtNamaWakil" type="text" id="txtNamaWakil" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$namaPenerima" size="35" $readonly class="$disabled" $disabled/></td>
  </tr>
  <tr id="diserahkan4" style="visibility:visible">
    <td width="1%" align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left">No. KP Penerima</td>
    <td>:</td>
    <td width="70%"><input name="txtNoKP" type="text" id="txtNoKP" value="$nokp" class="$disabled" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonly $disabled /></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="28%" align="left" valign="top">Catatan</td>
    <td valign="top">:</td>
    <td width="70%"><textarea name="txtCatatan" id="txtCatatan" cols="41" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $readonly $disabled>$catatan</textarea></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="7" align="center">
     #if($mode == 'new')
     <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick = "simpan('$idPermohonan')" />
     <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick = "batal()"/>
     #end
     #if($mode == 'view')
     <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick = "kemaskini()" />
     <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus"  onClick = "hapus()"/>
     #end
     #if($mode == 'update')
     <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick = "simpan_kemaskini('$idBuktiPenyampaian')"/>      
     <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick = "batal_kemaskini()" />
     #end
     <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>    </td>
  </tr>
</table>
<input type="hidden" name="id_fail" value="$idFail" />
<input type="hidden" name="id_permohonan" value="$idPermohonan" />
<input type="hidden" name="id_buktipenyampaian" value="$idBuktiPenyampaian" />
<input type="hidden" name="status_serahan" value="$statusSerahan" />
<input type="hidden" name="jenis_serahan" value="$jenisSerahan" />
</fieldset>
<p>
<fieldset>
<legend><strong>Senarai Pihak Berkepentingan</strong></legend>

<table width="100%" border="0">
  <tr class = "table_header">
    <td><strong>No</strong></td>
    <td width="63%"><strong>Nama Pihak Berkepentingan</strong></td>
    <td width="34%"><strong>No KP</strong></td>
    #if($size_listPB != 0)
    <td width="3%">
      <input type="checkbox" name="checkall" id="checkall" title="Sila Semak Untuk Pilih Semua" onclick="checkALL()" class="$disabled" $readonly $disabled/>
    </td>
    #end
  </tr>
  #if($size_listPB != 0)
  #foreach ($fail in $listPB)
  
  #if ($fail.no == '') 
  #set ($row = 'row1')
  #elseif ($fail.no % 2 != 0)
  #set ($row = 'row1')
  #else 
  #set ($row = 'row2')
  #end
  
 
  
  #if($fail.flag!="0")
     #set($checked = "checked")
  #else
     #set($checked = "")
  #end

  <tr>
    <td class="$row">$fail.no</td>
     <td class="$row">$fail.nama_pb</td>
    <td class="$row">$fail.no_pb</td>
      #if($size_listPB != 0)
     <td class="$row">
      <input name="cbsemaks" type="checkbox" id="cbsemaks"  #if($mode == 'view' || $mode == 'update')$checked #end onclick="doUpdateCheckAll()" value="$fail.id_pihakberkepentingan" class="$disabled" $readonly $disabled />
    </td>
   #end 
  </tr>
  #end
  #else
  <tr>
     <td></td>
     <td>Tiada rekod.</td>
     <td></td>
  </tr>
  #end
</table>
</fieldset>
<p>
<fieldset>
	<legend><strong>Maklumat Penyampaian Notis</strong></legend>
    #if($mode != 'new')
    <input name="cmdTambah" type="button" id = "cmdTambah" onclick="tambahNotis()" value="Tambah" />
#end
<table width="100%">
    	<tr class = "table_header" >
          <td><strong>No</strong></td>
          <td><strong>Nama Penerima</strong></td>
          <td><strong>Nama Penghantar</strong></td>
          <td><strong>Tarikh Serahan</strong></td>
      </tr>
      #foreach ($notis in $listPenyampaianNotis)
  		
      #if ($notis.bil == '') 
      #set ($row = 'row1')
      #elseif ($notis.bil % 2 != 0)
      #set ($row = 'row1')
      #else 
      #set ($row = 'row2')
      #end
        <tr>
       	  <td class="$row">$notis.bil</td>
             #if ($notis.bil != '') 
          <td class="$row"><a href="javascript:viewPenerima('$notis.id_buktipenyampaian')"><font color="blue">$notis.nama_penerima</font></a></td>
            #else
          <td class="$row">$notis.nama_penerima</td>
            #end
          <td class="$row">$notis.nama_penghantar</td>
            <td class="$row">$notis.tarikh_hantar</td>
        </tr>
      #end
    </table>
</fieldset>
<script>
window.onload=function()
{
  	document.getElementById("diserahkan1").style.visibility="visible";
			document.getElementById("diserahkan2").style.visibility="visible";
			document.getElementById("diserahkan3").style.visibility="visible";
			document.getElementById("diserahkan4").style.visibility="visible";
	
	/*var id = document.${formName}.socStatusSerahan.value;
	
	if(id == "1"){
	
		if(document.getElementById("diserahkan").style.visibility=="collapse"){
			document.getElementById("diserahkan1").style.visibility="visible";
			document.getElementById("diserahkan2").style.visibility="visible";
			document.getElementById("diserahkan3").style.visibility="visible";
			document.getElementById("diserahkan4").style.visibility="visible";
			//document.getElementById("tidakdiserahkan").style.visibility="collapse";
		}
		else if(document.getElementById("diserahkan").style.visibility=="visible"){
			document.getElementById("diserahkan1").style.visibility="collapse";
			document.getElementById("diserahkan2").style.visibility="collapse";
			document.getElementById("diserahkan3").style.visibility="collapse";
			document.getElementById("diserahkan4").style.visibility="collapse";
			//document.getElementById("tidakdiserahkan").style.visibility="visible";
		}
		
		document.getElementById("tidakdiserahkan").style.visibility="collapse";
		
	}
	else if(id == "2"){
	
		if(document.getElementById("tidakdiserahkan").style.visibility=="collapse"){
			document.getElementById("tidakdiserahkan").style.visibility="visible";
			//document.getElementById("diserahkan").style.visibility="collapse";
		}
		else if(document.getElementById("tidakdiserahkan").style.visibility=="visible"){
			document.getElementById("tidakdiserahkan").style.visibility="collapse";
			//document.getElementById("diserahkan").style.visibility="visible";
		}
		
			document.getElementById("diserahkan1").style.visibility="collapse";
			document.getElementById("diserahkan2").style.visibility="collapse";
			document.getElementById("diserahkan3").style.visibility="collapse";
			document.getElementById("diserahkan4").style.visibility="collapse";
		
	}
	else if(id == "0"){
			document.getElementById("tidakdiserahkan").style.visibility="collapse";
			document.getElementById("diserahkan1").style.visibility="visible";
			document.getElementById("diserahkan2").style.visibility="visible";
			document.getElementById("diserahkan3").style.visibility="visible";
			document.getElementById("diserahkan4").style.visibility="visible";
			
	}*/

}
function kembali(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisIndividu";
	document.${formName}.submit();
}
function simpan(id_permohonan){
	
	/*if(document.${formName}.txtNamaPenghantarNotis.value == ""){
		alert("Sila masukkan \"Nama Penghantar Notis\" terlebih dahulu.");
  		document.${formName}.txtNamaPenghantarNotis.focus(); 
		return;
	}*/
	//if(document.${formName}.socJenisDokumen.value == ""){
	//	alert("Sila masukkan \"Kod Perihal Dokumen\" terlebih dahulu.");
  	//	document.${formName}.socJenisDokumen.focus(); 
	//	return;
	//}
	if(document.${formName}.socStatusSerahan.value == "0"){
		alert("Sila pilih \"Status Serahan\" terlebih dahulu.");
  		document.${formName}.socStatusSerahan.focus(); 
		return;
	}
	if(document.${formName}.socJenisSerahan.value == "0"){
		alert("Sila pilih \"Jenis Serahan\" terlebih dahulu.");
  		document.${formName}.socJenisSerahan.focus(); 
		return;
	}
/*	if(document.${formName}.txdTarikhSerahan.value == ""){
		alert("Sila masukkan \"Tarikh Serahan\" terlebih dahulu.");
  		document.${formName}.txdTarikhSerahan.focus(); 
		return;
	}*/
	if(document.${formName}.socNamaPB.value == ""){
	   if(document.${formName}.txtNamaWakil.value == ""){
		alert("Sila masukkan \"Nama Penerima\" terlebih dahulu.");
  		document.${formName}.socNamaPB.focus(); 
		return;
		}
	}
	if(document.${formName}.txtNamaWakil.value == ""){
	   if(document.${formName}.socNamaPB.value == ""){
		alert("Sila masukkan \"Nama Penerima\" terlebih dahulu.");
  		document.${formName}.socNamaPB.focus(); 
		return;
		}
	}
	if(document.${formName}.txtNoKP.value == ""){
		alert("Sila masukkan \"No KP Penerima\" terlebih dahulu.");
  		document.${formName}.txtNoKP.focus(); 
		return;
	}
	/*if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan \"Catatan\" terlebih dahulu.");
  		document.${formName}.txtCatatan.focus(); 
		return;
	}*/
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisIndividu&action=simpanPenyampaiNotis";
	document.${formName}.submit();
}
function batal(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisIndividu&action=newNotisIndividu";
	document.${formName}.submit();
}
function viewPenerima(id_buktipenyampaian){
	
	document.${formName}.id_buktipenyampaian.value = id_buktipenyampaian;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisIndividu&action=viewPenerimaNotis";
	document.${formName}.submit();
}
function kemaskini(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisIndividu&action=kemaskini";
	document.${formName}.submit();
}
function batal_kemaskini(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisIndividu&action=viewPenerimaNotis";
	document.${formName}.submit();
}
function simpan_kemaskini(id_buktipenyampaian){

	/*if(document.${formName}.txtNamaPenghantarNotis.value == ""){
		alert("Sila masukkan \"Nama Penghantar Notis\" terlebih dahulu.");
  		document.${formName}.txtNamaPenghantarNotis.focus(); 
		return;
	}*/
	//if(document.${formName}.socJenisDokumen.value == ""){
	//	alert("Sila masukkan \"Kod Perihal Dokumen\" terlebih dahulu.");
  	//	document.${formName}.socJenisDokumen.focus(); 
	//	return;
	//}
	if(document.${formName}.socStatusSerahan.value == "0"){
		alert("Sila pilih \"Status Serahan\" terlebih dahulu.");
  		document.${formName}.socStatusSerahan.focus(); 
		return;
	}
	if(document.${formName}.socJenisSerahan.value == "0"){
		alert("Sila pilih \"Jenis Serahan\" terlebih dahulu.");
  		document.${formName}.socJenisSerahan.focus(); 
		return;
	}
	if(document.${formName}.txdTarikhSerahan.value == ""){
		alert("Sila masukkan \"Tarikh Serahan\" terlebih dahulu.");
  		document.${formName}.txdTarikhSerahan.focus(); 
		return;
	}
	if(document.${formName}.socNamaPB.value == ""){
	   if(document.${formName}.txtNamaWakil.value == ""){
		alert("Sila masukkan \"Nama Penerima\" terlebih dahulu.");
  		document.${formName}.socNamaPB.focus(); 
		return;
		}
	}
	if(document.${formName}.txtNamaWakil.value == ""){
	   if(document.${formName}.socNamaPB.value == ""){
		alert("Sila masukkan \"Nama Penerima\" terlebih dahulu.");
  		document.${formName}.socNamaPB.focus(); 
		return;
		}
	}
	if(document.${formName}.txtNoKP.value == ""){
		alert("Sila masukkan \"No KP Penerima\" terlebih dahulu.");
  		document.${formName}.txtNoKP.focus(); 
		return;
	}
	/*if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan \"Catatan\" terlebih dahulu.");
  		document.${formName}.txtCatatan.focus(); 
		return;
	}*/
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_buktipenyampaian.value = id_buktipenyampaian;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisIndividu&action=simpanKemaskini";
	document.${formName}.submit();
}
function hapus(){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisIndividu&action=hapusMaklumat";
	document.${formName}.submit();
}
function tambahNotis(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraNotisIndividu&action=newNotisIndividu";
	document.${formName}.submit();

}
function doChangePB() {
    doAjaxCall${formName}("doChangePB");
}

function showField(statusSerahan){
	
	alert(statusSerahan);
	
	if(statusSerahan == "1"){
		alert("xxx");
		alert(document.getElementById("diserahkan1").style.visibility);
		if(document.getElementById("diserahkan1").style.visibility=="collapse"){
			document.getElementById("diserahkan1").style.visibility="visible";
			document.getElementById("diserahkan2").style.visibility="visible";
			document.getElementById("diserahkan3").style.visibility="visible";
			document.getElementById("diserahkan4").style.visibility="visible";
			//document.getElementById("tidakdiserahkan").style.visibility="collapse";
		}
		else if(document.getElementById("diserahkan1").style.visibility=="visible"){
			document.getElementById("diserahkan1").style.visibility="collapse";
			document.getElementById("diserahkan2").style.visibility="collapse";
			document.getElementById("diserahkan3").style.visibility="collapse";
			document.getElementById("diserahkan4").style.visibility="collapse";
			//document.getElementById("tidakdiserahkan").style.visibility="visible";
		}
		
		document.getElementById("tidakdiserahkan").style.visibility="collapse";
		
	}
	else if(statusSerahan == "2"){
	
		if(document.getElementById("tidakdiserahkan").style.visibility=="collapse"){
			document.getElementById("tidakdiserahkan").style.visibility="visible";
			//document.getElementById("diserahkan").style.visibility="collapse";
		}
		else if(document.getElementById("tidakdiserahkan").style.visibility=="visible"){
			document.getElementById("tidakdiserahkan").style.visibility="collapse";
			//document.getElementById("diserahkan").style.visibility="visible";
		}
		
			document.getElementById("diserahkan1").style.visibility="collapse";
			document.getElementById("diserahkan2").style.visibility="collapse";
			document.getElementById("diserahkan3").style.visibility="collapse";
			document.getElementById("diserahkan4").style.visibility="collapse";
		
	}
	else if(statusSerahan == "0"){
			document.getElementById("tidakdiserahkan").style.visibility="collapse";
			document.getElementById("diserahkan1").style.visibility="visible";
			document.getElementById("diserahkan2").style.visibility="visible";
			document.getElementById("diserahkan3").style.visibility="visible";
			document.getElementById("diserahkan4").style.visibility="visible";
			
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
<!-- FOR CHECKBOX -->
<script language="javascript">
var checked = false;
function checkALL() {

	 if (document.${formName}.checkall.checked == true){
	
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = true;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = true;
	            }
	        }
	 } else {

	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = false;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = false;
	            }
	        }
	 }
}
function doUpdateCheckAll(){  

	var c = 0;
	if(document.${formName}.cbsemaks.length > 1){     
		
		for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	      if (document.${formName}.cbsemaks[i].checked == false){	 
		  	c++
	      }
		}  

	}else{
		
		if (document.${formName}.cbsemaks.checked == false){				 
			c++;
		}	 	
	}	 
	 
	if(c>0){
			  
		document.${formName}.checkall.checked = false;
	}
	else{
		document.${formName}.checkall.checked = true;
	}       
}
</script>