<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionMonitoring" type="hidden" id="actionMonitoring" value="$actionMonitoring"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idPerisian" type="hidden" id="idPerisian" value="$idPerisian"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT LESEN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPerisian in $BeanMaklumatPerisian)
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%" valign="top">Kategori</td>
          <td width="1%">:</td>
          <td width="70%">$selectKategori</td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%" valign="top">Nama Lesen</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNamaPerisian" type="text" class="$inputTextClass" id="txtNamaPerisian" value="$beanMaklumatPerisian.txtNamaPerisian" $readonly onblur="this.value=this.value.toUpperCase();" size="50" maxlength="50"/></td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%" valign="top">Nama Pengeluar</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNamaPengeluar" type="text" class="$inputTextClass" id="txtNamaPengeluar" value="$beanMaklumatPerisian.txtNamaPengeluar" $readonly onblur="this.value=this.value.toUpperCase();" size="50" maxlength="50"/></td>
        </tr>
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td valign="top">Keterangan</td>
          <td valign="top">:</td>
          <td><textarea name="txtKeterangan" id="txtKeterangan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();" >$beanMaklumatPerisian.txtKeterangan</textarea></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td width="28%">Nombor Siri</td>
          <td>:</td>
          <td width="70%"><input name="txtNoSiri" type="text" class="$inputTextClass" id="txtNoSiri" value="$beanMaklumatPerisian.txtNoSiri" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td width="28%"><em>CD Key</em></td>
          <td>:</td>
          <td width="70%"><input name="txtCdKey" type="text" class="$inputTextClass" id="txtCdKey" value="$beanMaklumatPerisian.txtCdKey" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>          </td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td width="28%">Harga</td>
          <td>:</td>
          <td width="70%">RM
            <input type="text" name="txtHarga" id="txtHarga" value="$beanMaklumatPerisian.txtHarga" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerisian.txtHarga');"/>          </td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td valign="top">Tarikh Pembelian</td>
          <td>:</td>
          <td><input type="text" name="txdTarikhBeli" id="txdTarikhBeli" value="$beanMaklumatPerisian.txdTarikhBeli" onblur="check_date(this)" size="9" $readonly class="$inputTextClass"/>
            <a href="javascript:displayDatePicker('txdTarikhBeli',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td valign="top">Tarikh Aktif</td>
          <td>:</td>
          <td><input type="text" name="txdTarikhAktif" id="txdTarikhAktif" value="$beanMaklumatPerisian.txdTarikhAktif" onblur="check_date(this);calcDate()" size="9" $readonly class="$inputTextClass"/>
            <a href="javascript:displayDatePicker('txdTarikhAktif',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td width="28%">Tempoh Lesen</td>
          <td>:</td>
          <td width="70%"><input type="text" name="txtTempoh" id="txtTempoh" size="1" maxlength="2" value="$beanMaklumatPerisian.txtTempoh" onBlur="validateNumber(this,this.value,'$beanMaklumatPerisian.txtTempoh');calcDate();" $readonly class="$inputTextClass">
            Bulan          </td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td valign="top">Tarikh Luput</td>
          <td>:</td>
          <td><input type="text" name="txdTarikhLuput" id="txdTarikhLuput" value="$beanMaklumatPerisian.txdTarikhLuput" onblur="check_date(this)" size="9" $readonly class="$inputTextClass"/>
            <a href="javascript:displayDatePicker('txdTarikhLuput',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Bilangan Pengguna</td>
          <td>:</td>
          <td><input name="txtBilPengguna" type="text" class="$inputTextClass" id="txtBilPengguna" value="$beanMaklumatPerisian.txtBilPengguna" maxlength="5" size="5" $readonly onblur="this.value=this.value.toUpperCase();"/>          </td>
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
    <td width="70%"><input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar" onclick="daftarBaru()"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
    </td>
  </tr>
</table>
<script>
function validateCurrency(elmnt,content,content2) {
	content = content.replace(/,/g,'');
	content2 = content2.replace(/,/g,'');
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function daftarBaru() {
	
	if(document.${formName}.socKategori.value == ""){
		alert('Sila pilih Kategori.');
  		document.${formName}.socKategori.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPerisian.value == ""){
		alert('Sila masukkan Nama Lesen.');
  		document.${formName}.txtNamaPerisian.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPengeluar.value == ""){
		alert('Sila masukkan Nama Pengeluar.');
  		document.${formName}.txtNamaPengeluar.focus(); 
		return; 
	}
	if(document.${formName}.txtNoSiri.value == ""){
		alert('Sila masukkan Nombor Siri');
  		document.${formName}.txtNoSiri.focus(); 
		return; 
	}
	if(document.${formName}.txtHarga.value == ""){
		alert('Sila masukkan Harga');
  		document.${formName}.txtHarga.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhBeli.value == ""){
		alert('Sila masukkan Tarikh Pembelian.');
  		document.${formName}.txdTarikhBeli.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhAktif.value == ""){
		alert('Sila masukkan Tarikh Aktif.');
  		document.${formName}.txdTarikhAktif.focus(); 
		return; 
	}
	if(document.${formName}.txtTempoh.value == ""){
		alert('Sila masukkan Tempoh Lesen.');
  		document.${formName}.txtTempoh.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhLuput.value == ""){
		alert('Sila masukkan Tarikh Luput.');
  		document.${formName}.txdTarikhLuput.focus(); 
		return; 
	}
	if(document.${formName}.txtBilPengguna.value == ""){
		alert('Sila masukkan Bilangan Pengguna.');
  		document.${formName}.txtBilPengguna.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionMonitoring.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "daftarBaru";
	document.${formName}.submit();
}
function kembali() {	
	document.${formName}.actionMonitoring.value = "";
	document.${formName}.submit();
}
function calcDate(){

	if (document.${formName}.txdTarikhAktif.value != "" && document.${formName}.txtTempoh.value != ""){

		var tarikhMula  = document.${formName}.txdTarikhAktif.value;
		var month  = parseInt(document.${formName}.txtTempoh.value);
		
		var dt1   = parseInt(tarikhMula.substring(0,2),10);
		var mon1  = parseInt(tarikhMula.substring(3,5),10)-1 + month;
		var yr1   = parseInt(tarikhMula.substring(6,10),10);
	 
		var myDate = new Date(yr1, mon1, dt1);
		myDate.setDate(myDate.getDate()-1);
		
		var day = myDate.getDate();
		var month = myDate.getMonth()+1;
		var year = myDate.getFullYear();
		
		var tarikhTamat = "";
		if(month>=10){
			if(day>=10){
				tarikhTamat = day + "/" + month + "/" + year;	
			} else {
				tarikhTamat = "0"+ day + "/" + month + "/" + year;	
			}				
		} else {
			if(day>=10){
				tarikhTamat = day + "/0" + month + "/" + year;	
			} else {
				tarikhTamat = "0"+ day + "/0" + month + "/" + year;	
			}
		}
		document.${formName}.txdTarikhLuput.value = tarikhTamat;
	
	} else {
		document.${formName}.txdTarikhLuput.value = "";
	}
}
</script>
