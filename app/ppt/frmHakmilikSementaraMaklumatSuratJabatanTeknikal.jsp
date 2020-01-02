<style type="text/css">
<!--
.style1 {color: #FF0000}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
<fieldset>
<strong>
<legend>Maklumat Surat Kepada Jabatan Teknikal</legend>
</strong>
<table width="100%">
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="29%" align="left">Jabatan</td>
    <td width="1%">:</td>
    <td width="70%">$SelectJabatan</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">Bil Surat</td>
    <td>:</td>
    <td><input name="txtBilSurat" type="text" class="$disabled" id="txtBilSurat" value="$BIL_SURAT" size="2" maxlength="2" $readonly /></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="29%" align="left">No Rujukan Surat</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtNoRujSurat" type="text" id="txtNoRujSurat" value="$NO_RUJ_SURAT" $readonly class="$disabled"  />
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="29%" align="left" valign="top">Tarikh Surat</td>
    <td width="1%" valign="top">:</td>
    <td width="70%"><label>
      <input name="txdTarikhSurat" type="text" id="txdTarikhSurat" value="$TARIKH_SURAT" size="10" $readonly class="$disabled" onblur="checking_validation(this,'tarikh_surat_check','yes','surat','tarikh');" />
    </label> #if($readonly != 'readonly') <a href="javascript:displayDatePicker('txdTarikhSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>#end<span class="style52">dd/mm/yyyy</span></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="29%" align="left" valign="top">Tarikh Hantar</td>
    <td width="1%" valign="top">:</td>
    <td width="70%"><input name="txdTarikhHantar" type="text" id="txdTarikhHantar" value="$TARIKH_HANTAR" size="10" $readonly class="$disabled" onblur="checking_validation(this,'tarikh_hantar_check','yes','hantar','tarikh');" />
   #if($readonly != 'readonly') <a href="javascript:displayDatePicker('txdTarikhHantar',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>  #end  <span class="style52">dd/mm/yyyy</span></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="29%" align="left" valign="top">Tempoh (4 minggu)</td>
    <td width="1%" valign="top">:</td>
    <td width="70%"><input name="txtTempoh" type="text" id="txtTempoh" value="$TEMPOH" $readonly class="$disabled"  /></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="29%" align="left" valign="top">Perihal</td>
    <td width="1%" valign="top">:</td>
    <td width="70%"><textarea name="txtPerihal" id="txtPerihal" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonly class="$disabled" >$PERIHAL</textarea></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td align="left" valign="top">Terima Surat Dari JT?</td>
    <td valign="top">:</td>
    <td>
    #if($mode == 'newSuratJbtn')
        #if($flag_Terima == '1')
        <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT" value="1" $TEMPcheckedYa onclick="terimaSuratJT()" $readonly class="$disabled" $disabled checked="checked"  />Ya
        #else
         <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT" value="1" $TEMPcheckedYa onclick="terimaSuratJT()" $readonly class="$disabled" $disabled/>Ya 
         #end
         #if($flag_Terima == '2')
          <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT2" value="2" $TEMPcheckedTidak onclick="takTerimaSuratJT()" $readonly class="$disabled" $disabled checked="checked"/>
          Tidak
          #else
           <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT2" value="2" $TEMPcheckedTidak onclick="takTerimaSuratJT()" $readonly class="$disabled" $disabled/>
          Tidak
          #end
      #end
      #if($mode == 'paparSuratJbtn')
        #if($flag_Terima == '1')
        <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT" value="1" $TEMPcheckedYa onclick="terimaSuratJTUpdate()" $readonly class="$disabled" $disabled checked="checked"  />Ya
        #else
         <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT" value="1" $TEMPcheckedYa onclick="terimaSuratJTUpdate()" $readonly class="$disabled" $disabled/>Ya 
         #end
         #if($flag_Terima == '2')
          <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT2" value="2" $TEMPcheckedTidak onclick="takTerimaSuratJTUpdate()" $readonly class="$disabled" $disabled checked="checked"/>
          Tidak
          #else
           <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT2" value="2" $TEMPcheckedTidak onclick="takTerimaSuratJTUpdate()" $readonly class="$disabled" $disabled/>
          Tidak
          #end
      #end
      #if($mode == 'kemaskiniSuratJbtn')
        #if($flag_Terima == '1')
        <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT" value="1" $TEMPcheckedYa onclick="terimaSuratJTUpdate()" $readonly class="$disabled" $disabled checked="checked"  />Ya
        #else
         <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT" value="1" $TEMPcheckedYa onclick="terimaSuratJTUpdate()" $readonly class="$disabled" $disabled/>Ya 
         #end
         #if($flag_Terima == '2')
          <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT2" value="2" $TEMPcheckedTidak onclick="takTerimaSuratJTUpdate()" $readonly class="$disabled" $disabled checked="checked"/>
          Tidak
          #else
           <input name="sorTerimaSuratJT" type="radio" id="sorTerimaSuratJT2" value="2" $TEMPcheckedTidak onclick="takTerimaSuratJTUpdate()" $readonly class="$disabled" $disabled/>
          Tidak
          #end
      #end      </td>
  </tr>
</table>
</fieldset>
#if($flag_Terima == '1')
<p>
<fieldset>
<strong>
<legend>Maklumat Terimaan Surat Dari Jabatan Teknikal</legend>
</strong>
 
<table width="100%">
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="29%" align="left">Bil. Surat</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtBilSuratTerima" type="text" class="$disabled" id="txtBilSuratTerima" value="$BIL_SURATJT" size="2" maxlength="2" $readonly >
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="29%" align="left">No. Ruj. Surat</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtNoRujSurat" type="text" id="txtNoRujSurat" value="$NO_RUJ_SURATJT" $readonly class="$disabled" >
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="29%" align="left">Tarikh Surat</td>
    <td width="1%">:</td>
    <td width="70%"><!--<input name="txdTkhSurat" type="text" id="txdTkhSurat" value="$TARIKH_SURATJT" size="10" $readonly class="$disabled"  />-->
    <input name="txdTkhSurat" type="text" id="txdTkhSurat" value="$TARIKH_SURATJT" size="10" $readonly class="$disabled" onblur="checking_validation(this,'tarikh_surat_check','yes','surat','tarikh');">
    #if($readonly != 'readonly') <a href="javascript:displayDatePicker('txdTkhSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>#end    <span class="style52">dd/mm/yyyy</span></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="29%" align="left" valign="top">Tarikh Terima</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><label>
      <input name="txdTkhTerima" type="text" id="txdTkhTerima" value="$TARIKH_TERIMAJT" size="10" $readonly class="$disabled" onblur="checking_validation(this,'tarikh_terima_check','yes','terima','tarikh');">
      #if($readonly != 'readonly') <a href="javascript:displayDatePicker('txdTkhTerima',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end
    <span class="style52">dd/mm/yyyy</span></label></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="29%" align="left" valign="top">Keputusan</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><select name="socKeputusanAdd" style="width:100px" class="$disabled" $disabled $readonly >
    #if($KEPUTUSANJT == '')
      <option value="" selected="selected">SILA PILIH</option>
        <option value="Diluluskan">DILULUSKAN</option>
        <option value="Ditolak">DITOLAK</option>
        
    #end
    #if($KEPUTUSANJT == 'Diluluskan')
      <option value="">SILA PILIH</option>
        <option value="Diluluskan" selected="selected">DILULUSKAN</option>
        <option value="Ditolak">DITOLAK</option>
        
    #end
    #if($KEPUTUSANJT == 'Ditolak')
      <option value="">SILA PILIH</option>
        <option value="Diluluskan">DILULUSKAN</option>
        <option value="Ditolak" selected="selected">DITOLAK</option>
        
    #end
      
    
    </select>    </td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="29%" align="left" valign="top">Ulasan</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><textarea name="txtUlasan" id="txtUlasan" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonly class="$disabled" >$ULASANJT</textarea></td>
  </tr>
</table>
</fieldset>
#end
<table width="100%">
<tr align="center">
    <td colspan="6">
     #if($mode == 'newSuratJbtn')
      	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan('$id_fail','$id_permohonan')" />
   	  <input type="submit" name="cmdBatal2" id="cmdBatal2" value="Batal" onclick="batal()" />
     #end
     #if($mode == 'paparSuratJbtn')
  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" />
        <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus('$idUlasanTeknikal')" />
     #end
     #if($mode == 'kemaskiniSuratJbtn')
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateSuratJbtn('$idUlasanTeknikal')"  />
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalSurat()" />
	 #end
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />    </td>
  </tr>
<input name="id_fail" type="hidden" value="$id_fail" />
<input name="id_permohonan" type="hidden" value="$id_permohonan" />
<input name="idUlasanTeknikal" type="hidden" value="$idUlasanTeknikal" />
<input name="namaAgensi" type="hidden" value="$namaAgensi" />
</table>
<script>
function doChangeidKementerian() {
    doAjaxCall${formName}("doChangeidKementerian");
}
function doChangeNamaAgensi() {
    doAjaxCall${formName}("doChangeNamaAgensi");
}
function terimaSuratJT() {
    document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=terimaSuratJbtn";
	document.${formName}.submit();
}
function takTerimaSuratJT() {
   	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=tidakTerimaSuratJbtn";
	document.${formName}.submit();
}
function terimaSuratJTUpdate() {
    document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=terimaSuratJbtnUpdate";
	document.${formName}.submit();
}
function takTerimaSuratJTUpdate() {
   	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=tidakTerimaSuratJbtnUpdate";
	document.${formName}.submit();
}
function simpan(id_fail,id_permohonan){
/*
	if(document.${formName}.socJabatan.value == ""){
		alert("Sila pilih \"Jabatan\" terlebih dahulu.");
  		document.${formName}.socJabatan.focus(); 
		return;
	}
	else if(document.${formName}.txtBilSurat.value == ""){
		alert("Sila masukkan \"Bil Surat\" terlebih dahulu.");
  		document.${formName}.txtBilSurat.focus(); 
		return;
	}
	else if(document.${formName}.txtNoRujSurat.value == ""){
		alert("Sila masukkan \"No Rujukan Surat\" terlebih dahulu.");
  		document.${formName}.txtNoRujSurat.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhSurat.value == ""){
		alert("Sila masukkan \"Tarikh Surat\" terlebih dahulu.");
  		document.${formName}.txdTarikhSurat.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhHantar.value == ""){
		alert("Sila masukkan \"Tarikh Hantar\" terlebih dahulu.");
  		document.${formName}.txdTarikhHantar.focus(); 
		return;
	}
	else if(document.${formName}.txtTempoh.value == ""){
		alert("Sila masukkan \"Tempoh\" terlebih dahulu.");
  		document.${formName}.txtTempoh.focus(); 
		return;
	}
	else if(document.${formName}.txtPerihal.value == ""){
		alert("Sila masukkan \"Perihal\" terlebih dahulu.");
  		document.${formName}.txtPerihal.focus(); 
		return;
	}
	else if(document.${formName}.sorTerimaSuratJT.value == ""){
		alert("Sila pilih \"sama ada telah terima surat dari Jabatan Teknikal atau tidak \" terlebih dahulu.");
  		document.${formName}.sorTerimaSuratJT.focus(); 
		return;
	}
	if (document.${formName}.sorTerimaSuratJT.value == "1"){
		
		if(document.${formName}.txtBilSuratTerima.value == ""){
		alert("Sila masukkan \"Bil Surat Terima\" terlebih dahulu.");
  		document.${formName}.txtBilSuratTerima.focus(); 
		return;
		}
		else if(document.${formName}.txtNoRujSurat.value == ""){
			alert("Sila masukkan \"No Rujukan Surat Terima\" terlebih dahulu.");
			document.${formName}.txtNoRujSurat.focus(); 
			return;
		}
		else if(document.${formName}.txdTkhSurat.value == ""){
			alert("Sila masukkan \"Tarikh Surat Terimaan\" terlebih dahulu.");
			document.${formName}.txdTkhSurat.focus(); 
			return;
		}
		else if(document.${formName}.txdTkhTerima.value == ""){
			alert("Sila masukkan \"Tarikh Terima Surat\" terlebih dahulu.");
			document.${formName}.txdTkhTerima.focus(); 
			return;
		}
		else if(document.${formName}.socKeputusanAdd.value == ""){
			alert("Sila pilih \"Keputusan\" terlebih dahulu.");
			document.${formName}.socKeputusanAdd.focus(); 
			return;
		}
		else if(document.${formName}.txtUlasan.value == ""){
			alert("Sila masukkan \"Ulasan\" terlebih dahulu.");
			document.${formName}.txtUlasan.focus(); 
			return;
		}
	
	
	}
	*/

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=simpanSuratJbtn"; 
	document.${formName}.submit();	
}
function updateSuratJbtn(id_ulasanteknikal){
/*	
	if(document.${formName}.socJabatan.value == ""){
		alert("Sila pilih \"Jabatan\" terlebih dahulu.");
  		document.${formName}.socJabatan.focus(); 
		return;
	}
	else if(document.${formName}.txtBilSurat.value == ""){
		alert("Sila masukkan \"Bil Surat\" terlebih dahulu.");
  		document.${formName}.txtBilSurat.focus(); 
		return;
	}
	else if(document.${formName}.txtNoRujSurat.value == ""){
		alert("Sila masukkan \"No Rujukan Surat\" terlebih dahulu.");
  		document.${formName}.txtNoRujSurat.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhSurat.value == ""){
		alert("Sila masukkan \"Tarikh Surat\" terlebih dahulu.");
  		document.${formName}.txdTarikhSurat.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhHantar.value == ""){
		alert("Sila masukkan \"Tarikh Hantar\" terlebih dahulu.");
  		document.${formName}.txdTarikhHantar.focus(); 
		return;
	}
	else if(document.${formName}.txtTempoh.value == ""){
		alert("Sila masukkan \"Tempoh\" terlebih dahulu.");
  		document.${formName}.txtTempoh.focus(); 
		return;
	}
	else if(document.${formName}.txtPerihal.value == ""){
		alert("Sila masukkan \"Perihal\" terlebih dahulu.");
  		document.${formName}.txtPerihal.focus(); 
		return;
	}
	else if(document.${formName}.sorTerimaSuratJT.value == ""){
		alert("Sila pilih \"sama ada telah terima surat dari Jabatan Teknikal atau tidak \" terlebih dahulu.");
  		document.${formName}.sorTerimaSuratJT.focus(); 
		return;
	}
	if (document.${formName}.sorTerimaSuratJT.value == "1"){
		
		if(document.${formName}.txtBilSuratTerima.value == ""){
		alert("Sila masukkan \"Bil Surat Terima\" terlebih dahulu.");
  		document.${formName}.txtBilSuratTerima.focus(); 
		return;
		}
		else if(document.${formName}.txtNoRujSurat.value == ""){
			alert("Sila masukkan \"No Rujukan Surat Terima\" terlebih dahulu.");
			document.${formName}.txtNoRujSurat.focus(); 
			return;
		}
		else if(document.${formName}.txdTkhSurat.value == ""){
			alert("Sila masukkan \"Tarikh Surat Terimaan\" terlebih dahulu.");
			document.${formName}.txdTkhSurat.focus(); 
			return;
		}
		else if(document.${formName}.txdTkhTerima.value == ""){
			alert("Sila masukkan \"Tarikh Terima Surat\" terlebih dahulu.");
			document.${formName}.txdTkhTerima.focus(); 
			return;
		}
		else if(document.${formName}.socKeputusanAdd.value == ""){
			alert("Sila pilih \"Keputusan\" terlebih dahulu.");
			document.${formName}.socKeputusanAdd.focus(); 
			return;
		}
		else if(document.${formName}.txtUlasan.value == ""){
			alert("Sila masukkan \"Ulasan\" terlebih dahulu.");
			document.${formName}.txtUlasan.focus(); 
			return;
		}
	
	
	}
	*/
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idUlasanTeknikal.value = id_ulasanteknikal;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=updateSuratJbtn"; 
	document.${formName}.submit();	
}
function kemaskini(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=kemaskiniSuratJbtn"; 
	document.${formName}.submit();	
}
function batal(){
	
	document.${formName}.socJabatan.value == "";
	document.${formName}.txtBilSurat.value == "";
	document.${formName}.txtNoRujSurat.value == "";
	document.${formName}.txdTarikhSurat.value == "";
	document.${formName}.txdTarikhHantar.value == "";
	document.${formName}.txtTempoh.value == "";
	document.${formName}.txtPerihal.value == "";
	document.${formName}.sorTerimaSuratJT.value == "";
	document.${formName}.txtBilSuratTerima.value == "";
	document.${formName}.txtNoRujSurat.value == "";
	document.${formName}.txdTkhSurat.value == "";
	document.${formName}.txdTkhTerima.value == "";
	document.${formName}.socKeputusanAdd.value == "";
	document.${formName}.txtUlasan.value == "";
	return;	
}
function batalSurat(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=viewSuratJbtn"; 
	document.${formName}.submit();	
}
function hapus(id_ulasanteknikal){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idUlasanTeknikal.value = id_ulasanteknikal;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=hapusSuratJbtn"; 
	document.${formName}.submit();	
}
function kembali(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=viewSenaraiJbtnTeknikal"; 
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