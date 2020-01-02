<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden"/>
  <input name="actionAktiviti" type="hidden"/>
  <input name="idAktiviti" type="hidden" id="idAktiviti" value="$idAktiviti"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT AKTIVITI</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatAktiviti in $BeanMaklumatAktiviti)
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%" valign="top">Nama Aktiviti</td>
          <td width="1%" >:</td>
          <td width="70%"><input name="txtNamaAktiviti" type="text" id="txtNamaAktiviti" size="49" maxlength="100" $readonly class="$inputTextClass" value="$beanMaklumatAktiviti.namaAktiviti" onBlur="this.value=this.value.toUpperCase();" />
          </td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">Lokasi Aktiviti</td>
          <td width="1%" >:</td>
          <td width="70%"><input name="txtLokasiAktiviti" type="text" id="txtLokasiAktiviti" size="49" maxlength="100" $readonly class="$inputTextClass" value="$beanMaklumatAktiviti.lokasiAktiviti" onBlur="this.value=this.value.toUpperCase();" />
          </td>
        </tr>
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td valign="top">Keterangan Aktiviti</td>
          <td valign="top">:</td>
          <td><textarea name="txtKeteranganAktiviti" id="txtKeteranganAktiviti" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$beanMaklumatAktiviti.keteranganAktiviti</textarea>
          </td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td>Tarikh Mula</td>
          <td>:</td>
          <td><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this);" value="$beanMaklumatAktiviti.tarikhMula" size="9" maxlength="10" $readonly />
            <a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td>Masa Mula</td>
          <td>:</td>
          <td><input name="txtMasaMula" type="text" class="$inputTextClass" id="txtMasaMula" value="$beanMaklumatAktiviti.masaMula" size="4" maxlength="4" $readonly onblur="validateLengthMasa(this,this.value);validateNumber(this,this.value);"/>
            &nbsp;&nbsp;<span class="style2"> Sila Masukan Masa Mengikut format 24 Jam (0800)</span></td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td>Tarikh Tamat</td>
          <td>:</td>
          <td><input name="txtTarikhTamat" type="text" class="$inputTextClass" id="txtTarikhTamat" onBlur="check_date(this);cekTarikhTamat(this);" value="$beanMaklumatAktiviti.tarikhTamat" size="9" maxlength="10" $readonly />
            <a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td>Masa Tamat</td>
          <td>:</td>
          <td><input name="txtMasaTamat" type="text" class="$inputTextClass" id="txtMasaTamat" value="$beanMaklumatAktiviti.masaTamat" size="4" maxlength="4" $readonly onblur="validateLengthMasa(this,this.value);validateNumber(this,this.value);cekMasaTamat(this);"/>
            &nbsp;&nbsp;<span class="style2"> Sila Masukan Masa Mengikut format 24 Jam (0800)</span></td>
        </tr>
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$beanMaklumatAktiviti.catatan</textarea>
          </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'new')
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Simpan" onclick="daftarBaru()"/>
      #end
      #if ($mode == 'view')      
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Simpan" onclick="simpanKemaskini()"/>
      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus()"/>
      #end      
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
    </td>
  </tr>
</table>
<script>
function daftarBaru() {
	if(document.${formName}.txtNamaAktiviti.value == ''){
		alert('Sila masukkan Nama Aktiviti.');
  		document.${formName}.txtNamaAktiviti.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula.');
  		document.${formName}.txtTarikhMula.focus(); 
		return; 
	}
	if(document.${formName}.txtMasaMula.value == ""){
		alert('Sila masukkan Masa Mula.');
  		document.${formName}.txtMasaMula.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTamat.value == ""){
		alert('Sila masukkan Tarikh Tamat.');
  		document.${formName}.txtTarikhTamat.focus(); 
		return; 
	}
	if(document.${formName}.txtMasaTamat.value == ""){
		alert('Sila masukkan Masa Tamat.');
  		document.${formName}.txtMasaTamat.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionAktiviti.value = "papar";
	document.${formName}.hitButton.value = "daftarBaru";
	document.${formName}.submit();
}
function simpanKemaskini() {
	if(document.${formName}.txtNamaAktiviti.value == ''){
		alert('Sila masukkan Nama Aktiviti.');
  		document.${formName}.txtNamaAktiviti.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula.');
  		document.${formName}.txtTarikhMula.focus(); 
		return; 
	}
	if(document.${formName}.txtMasaMula.value == ""){
		alert('Sila masukkan Masa Mula.');
  		document.${formName}.txtMasaMula.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTamat.value == ""){
		alert('Sila masukkan Tarikh Tamat.');
  		document.${formName}.txtTarikhTamat.focus(); 
		return; 
	}
	if(document.${formName}.txtMasaTamat.value == ""){
		alert('Sila masukkan Masa Tamat.');
  		document.${formName}.txtMasaTamat.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionAktiviti.value = "papar";
	document.${formName}.hitButton.value = "simpanKemaskini";
	document.${formName}.submit();
}
function kembali() {	
	document.${formName}.actionAktiviti.value = "";
	document.${formName}.submit();
}
function hapus() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionAktiviti.value = "";
	document.${formName}.hitButton.value = "hapusAktiviti";
	document.${formName}.submit();
}
function cekTarikhTamat(elmnt) {  
	//CHECK DATE   
	var str1  = document.${formName}.txtTarikhMula.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhMula = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.txtTarikhTamat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhTamat = new Date(yr2, mon2, dt2);
	
	if (tarikhTamat < tarikhMula){
		alert('Tarikh Tamat tidak boleh sebelum dari Tarikh Mula.');
		elmnt.value ="";
  		document.${formName}.txtTarikhTamat.focus(); 
		return;
	}
}
function validateLengthMasa(elmnt,str) {	
	var ValJam = str.substring(0,2);
	var ValMin = str.substring(2,4);
	
	if (str.length < 4) {
 		alert("Sila Masukan Masa Mengikut format 24 Jam")
		elmnt.value = "";
		elmnt.focus(); 
		return false
	
	} else if (parseInt(ValJam) > 24) {
 		alert("Sila Masukan Masa Mengikut format 24 Jam")
		elmnt.value = "";
		elmnt.focus();
		return false
		
	} else if (parseInt(ValMin) >= 60) {
 		alert("Sila Masukan Masa Mengikut format 24 Jam")
		elmnt.value = "";
		elmnt.focus();
		return false
	}
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function cekMasaTamat(elmnt) {  
	//CHECK DATE   
	var str1  = document.${formName}.txtTarikhMula.value;		   
	var str2  =  document.${formName}.txtTarikhTamat.value;		   
	
	if (str1 == str2){
		var str3  = document.${formName}.txtMasaMula.value;
		var str4  = document.${formName}.txtMasaTamat.value;

		if (parseInt(str4) <= parseInt(str3)){
			alert('Masa Tamat tidak boleh sebelum atau sama dari Masa Mula.');
			elmnt.value = "";
  			document.${formName}.txtMasaTamat.focus(); 
			return;
		}		
	}
}
</script>
