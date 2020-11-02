<style type="text/css">
<!--
#parse("css/eTapp_PHP.css") .style1 {
color: #FF0000
}
-->
</style>
<input type="hidden" name="actionPopup" value="$actionPopup"/>
<input type="hidden" name="hitButton"/>
<input type="hidden" name="flagSkrin" value="$!flagSkrin"/>
#if ($close_window == 'yes')
<body onLoad = closeWin();>
#end
#if ($error_msg == 'yes')
<div class="warning">MAKLUMAT PERJANJIAN TIDAK BOLEH DIHAPUS</div>
#end

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="4"><fieldset>
      <legend><strong>MAKLUMAT PERJANJIAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPerjanjian in $BeanMaklumatPerjanjian)
        <input type="hidden" name="idPerjanjian" naidme="idPerjanjian" value="$!idPerjanjian"/>

        #if ($!flagSkrin == 'U')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">No. Siri Perjanjian</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtNoSiri" id="txtNoSiri" value="$!beanMaklumatPerjanjian.noSiri" onBlur="this.value=this.value.toUpperCase();" $readonly class="$inputTextClass"/>
          </td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Status Kelulusan</td>
          <td>:</td>
          <td>
          #if ( $!beanMaklumatPerjanjian.flagKelulusanDasar == 'T')
          	LULUS
          #end
          #if ( $!beanMaklumatPerjanjian.flagKelulusanDasar == 'Y')
          	LULUS DASAR
          #end
          </td>
        </tr>
        #if ($!beanMaklumatPerjanjian.flagKelulusanDasar == 'Y')
         <tr>
          <td><span class="style1">*</span></td>
          <td>Tarikh Mula Kelulusan Dasar</td>
          <td>:</td>
          <td><input name="txtTarikhMulaDasar" type="text" class="$inputTextClass" id="txtTarikhMulaDasar" onBlur="check_date(this);calcDate()" value="$!beanMaklumatPerjanjian.tarikhMulaDasar" size="9" maxlength="10" $readonly />
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tempoh Dasar</td>
          <td>:</td>
          <td><input type="text" name="txtTempohDasar" id="txtTempohDasar" size="1" maxlength="2" value="$!beanMaklumatPerjanjian.tempohDasar" onBlur="validateNumber(this,this.value);calcDate();" $readonly class="$inputTextClass">
            Bulan</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Tarikh Tamat Kelulusan Dasar</td>
          <td>:</td>
          <td><input name="txtTarikhTamatDasar" type="text" class="$inputTextClass" id="txtTarikhTamatDasar" onBlur="check_date(this);calcDate()" value="$!beanMaklumatPerjanjian.tarikhTamatDasar" size="9" maxlength="10" $readonly />
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../../img/calendar.gif"/>#end</td>
        </tr>
        #end
        <tr>
          <td><span class="style1">*</span></td>
          <td>Tarikh Mula Perjanjian</td>
          <td>:</td>
          <td><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this);calcDate()" value="$!beanMaklumatPerjanjian.tarikhMula" size="9" maxlength="10" $readonly />
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tempoh</td>
          <td>:</td>
          <td><input type="text" name="txtTempoh" id="txtTempoh" size="1" maxlength="2" value="$!beanMaklumatPerjanjian.tempoh" onBlur="validateNumber(this,this.value);calcDate();" $readonly class="$inputTextClass">
            Bulan</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Tarikh Tamat Perjanjian</td>
          <td>:</td>
          <td><input name="txtTarikhTamat" type="text" class="$inputTextClass" id="txtTarikhTamat" onBlur="check_date(this);calcDate()" value="$!beanMaklumatPerjanjian.tarikhTamat" size="9" maxlength="10" $readonly />
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td> Kadar Sewa (RM)</td>
          <td>:</td>
          <td><input name="txtKadarSewa" type="text" value="$!beanMaklumatPerjanjian.kadarSewa" $readonly class="$inputTextClass" onBlur="validateCurrency(this,this.value,'$!beanMaklumatPerjanjian.kadarSewa');calcCagaran()" /></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Cagaran (RM)</td>
          <td>:</td>
          <td><input name="txtCagaran" type="text" value="$!beanMaklumatPerjanjian.cagaran" $readonly class="$inputTextClass" onBlur="validateCurrency(this,this.value,'$!beanMaklumatPerjanjian.cagaran');" /></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Mod Caj Sewaan</td>
          <td>:</td>
          <td><select class="texts" name="modCajSewaan" id="modCajSewaan" >
            <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="1" ) selected #end value="1">SETIAP BULAN</option>
            <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="3" ) selected #end value="3">SETIAP 3 BULAN</option>
            <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="6" ) selected #end value="6">SETIAP 6 BULAN</option>
            <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="12" ) selected #end value="12">SETIAP TAHUN</option>
            <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="0" ) selected #end value="0">CAJ PENUH</option>
          </select></td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtCatatan" rows="5" cols="50">$!beanMaklumatPerjanjian.catatan</textarea></td>
        </tr>

        #else

        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">No. Siri Perjanjian</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtNoSiri" id="txtNoSiri" value="$!beanMaklumatPerjanjian.noSiri" onBlur="this.value=this.value.toUpperCase();" $readonly class="$inputTextClass"/>
          </td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Jenis Perjanjian</td>
          <td>:</td>
          <td><select class="texts" name="flagPerjanjian" id="flagPerjanjian" onChange="doChangeFlagPerjanjian()">
              <option value=>SILA PILIH</option>
              <option #if ( $!beanMaklumatPerjanjian.flagPerjanjian =="1" ) selected #end value="1">PENGURANGAN KADAR SEWA</option>
              <option #if ( $!beanMaklumatPerjanjian.flagPerjanjian =="2" ) selected #end value="2">PENAMBAHAN KADAR SEWA</option>
              <option #if ( $!beanMaklumatPerjanjian.flagPerjanjian =="3" ) selected #end value="3">PENGECUALIAN KADAR SEWA</option>
            </select></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Tarikh Mula Kuatkuasa</td>
          <td>:</td>
          <td><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this);calcDate()" value="$!beanMaklumatPerjanjian.tarikhMula" size="9" maxlength="10" $readonly />
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tempoh</td>
          <td>:</td>
          <td><input type="text" name="txtTempoh" id="txtTempoh" size="1" maxlength="2" value="$!beanMaklumatPerjanjian.tempoh" onBlur="validateNumber(this,this.value);calcDate();" $readonly class="$inputTextClass">
            Bulan</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Tarikh Tamat Kuatkuasa</td>
          <td>:</td>
          <td><input name="txtTarikhTamat" type="text" class="$inputTextClass" id="txtTarikhTamat" onBlur="check_date(this);calcDate()" value="$!beanMaklumatPerjanjian.tarikhTamat" size="9" maxlength="10" $readonly />
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../../img/calendar.gif"/>#end</td>
        </tr>
        #if ( $!beanMaklumatPerjanjian.flagPerjanjian != '3' )
        <tr>
          <td><span class="style1">*</span></td>
          <td> Kadar Sewa (RM)</td>
          <td>:</td>
          <td><input name="txtKadarSewa" type="text" value="$!beanMaklumatPerjanjian.kadarSewa" $readonly class="$inputTextClass" onBlur="validateCurrency(this,this.value,'$!beanMaklumatPerjanjian.kadarSewa');" /></td>
        </tr>
        #end
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtCatatan" rows="5" cols="50">$!beanMaklumatPerjanjian.catatan</textarea></td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'new')
      <input name="cmdSimpan" type="button" value="Simpan" onClick="simpan()" />
      #else
      <input name="cmdSimpan" type="button" value="Simpan" onClick="simpanKemaskini()" />
      <input name="cmdHapus" type="button" value="Hapus" onClick="hapus()" />
      #end
      <input name="cmdBatal" type="button" value="Batal" onClick="closeWin()" />
    </td>
  </tr>
</table>
<script>
function calcDate(){
	if (document.${formName}.txtTarikhMula.value != "" && document.${formName}.txtTempoh.value != ""){

		var tarikhMula  = document.${formName}.txtTarikhMula.value;
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
		document.${formName}.txtTarikhTamat.value = tarikhTamat;

	} else {
		document.${formName}.txtTarikhTamat.value = "";
	}
}
function calcCagaran(){
	if (document.${formName}.txtKadarSewa.value != ""){

		var kadarSewa  = document.${formName}.txtKadarSewa.value*1;
		var cagaran  = 0;

		cagaran = kadarSewa * 3;

		document.${formName}.txtCagaran.value = cagaran.toFixed(2);

	} else {
		document.${formName}.txtCagaran.value = "";
	}
}
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
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
function simpan() {
	var flagSkrin = document.${formName}.flagSkrin.value;

	if (flagSkrin == 'U') {
		if(document.${formName}.flagKelulusanDasar.value == ""){
			alert('Sila pilih Status Kelulusan Dasar.');
			document.${formName}.flagKelulusanDasar.focus();
			return;
		}

		if (document.${formName}.flagKelulusanDasar.value != 'Y') {
			if(document.${formName}.txtTarikhMula.value == ""){
				alert('Sila masukan Tarikh Mula.');
				document.${formName}.txtTarikhMula.focus();
				return;
			}
			if(document.${formName}.txtTarikhTamat.value == ""){
				alert('Sila masukan Tarikh Tamat.');
				document.${formName}.txtTarikhTamat.focus();
				return;
			}
			if(document.${formName}.txtKadarSewa.value == ""){
				alert('Sila masukkan Kadar Sewa.');
				document.${formName}.txtKadarSewa.focus();
				return;
			}
			if(document.${formName}.txtCagaran.value == ""){
				alert('Sila masukkan Cagaran.');
				document.${formName}.txtCagaran.focus();
				return;
			}
			if(document.${formName}.modCajSewaan.value == ""){
				alert('Sila pilih Mod Caj Sewaan.');
				document.${formName}.modCajSewaan.focus();
				return;
			}
		}
	}

	if (flagSkrin == 'T') {
		if(document.${formName}.flagPerjanjian.value == ""){
			alert('Sila pilih Jenis Perjanjian.');
			document.${formName}.flagPerjanjian.focus();
			return;
		}
		if(document.${formName}.txtTarikhMula.value == ""){
			alert('Sila masukan Tarikh Mula.');
			document.${formName}.txtTarikhMula.focus();
			return;
		}
		if(document.${formName}.txtTarikhTamat.value == ""){
			alert('Sila masukan Tarikh Tamat.');
			document.${formName}.txtTarikhTamat.focus();
			return;
		}
		if (document.${formName}.flagPerjanjian.value != "3") {
			if(document.${formName}.txtKadarSewa.value == ""){
				alert('Sila masukkan Kadar Sewa.');
				document.${formName}.txtKadarSewa.focus();
				return;
			}
		}
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.hitButton.value = "simpan";
	document.${formName}.submit();
}
function simpanKemaskini() {
	var flagSkrin = document.${formName}.flagSkrin.value;

	if (flagSkrin == 'U') {
		if(document.${formName}.flagKelulusanDasar.value == ""){
			alert('Sila pilih Status Kelulusan Dasar.');
			document.${formName}.flagKelulusanDasar.focus();
			return;
		}

		if (document.${formName}.flagKelulusanDasar.value != 'Y') {
			if(document.${formName}.txtTarikhMula.value == ""){
				alert('Sila masukan Tarikh Mula.');
				document.${formName}.txtTarikhMula.focus();
				return;
			}
			if(document.${formName}.txtTarikhTamat.value == ""){
				alert('Sila masukan Tarikh Tamat.');
				document.${formName}.txtTarikhTamat.focus();
				return;
			}
			if(document.${formName}.txtKadarSewa.value == ""){
				alert('Sila masukkan Kadar Sewa.');
				document.${formName}.txtKadarSewa.focus();
				return;
			}
			if(document.${formName}.txtCagaran.value == ""){
				alert('Sila masukkan Cagaran.');
				document.${formName}.txtCagaran.focus();
				return;
			}
			if(document.${formName}.modCajSewaan.value == ""){
				alert('Sila pilih Mod Caj Sewaan.');
				document.${formName}.modCajSewaan.focus();
				return;
			}
		}
	}

	if (flagSkrin == 'T') {
		if(document.${formName}.flagPerjanjian.value == ""){
			alert('Sila pilih Jenis Perjanjian.');
			document.${formName}.flagPerjanjian.focus();
			return;
		}
		if(document.${formName}.txtTarikhMula.value == ""){
			alert('Sila masukan Tarikh Mula.');
			document.${formName}.txtTarikhMula.focus();
			return;
		}
		if(document.${formName}.txtTarikhTamat.value == ""){
			alert('Sila masukan Tarikh Tamat.');
			document.${formName}.txtTarikhTamat.focus();
			return;
		}
		if (document.${formName}.flagPerjanjian.value != "3") {
			if(document.${formName}.txtKadarSewa.value == ""){
				alert('Sila masukkan Kadar Sewa.');
				document.${formName}.txtKadarSewa.focus();
				return;
			}
		}
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.hitButton.value = "simpanKemaskini";
	document.${formName}.submit();
}
function hapus() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.hitButton.value = "hapus";
	document.${formName}.submit();
}
function closeWin(){
	window.opener.doRefreshScreen();
	window.close();
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = "";
		return;
	} else {
		if (content.length < 2){
        	elmnt.value = "0" + content;
        }
    }
}
function doChangeFlagPerjanjian() {
	doAjaxCall${formName}("doChangeFlagPerjanjian");
}
function doChangeFlagKelulusanDasar() {
	doAjaxCall${formName}("doChangeFlagKelulusanDasar");
}
</script>
