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
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idPerjanjian" type="hidden" id="idPerjanjian" value="$idPerjanjian"/>
  <input name="idKeputusan" type="hidden" id="idKeputusan" value="$idKeputusan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
  <input name="flagPermohonanDari" type="hidden" id="flagPermohonanDari" value="$flagPermohonanDari"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="step" type="hidden" id="step" value="$step"/>
</p>

#if ($errMsg != "")
<div class="info"><strong>$errMsg</strong></div>
#end

<table width="100%">
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201')
  <tr>
    <td>#parse("app/php2/frmPYWHeader.jsp")</td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI TUGASAN TERLEBIH DAHULU</div></td>
  </tr>
  #else
  #foreach($beanHeader in $BeanHeader)
  <tr>
    <td>&nbsp;
      <div class="warning">FAIL INI MASIH DI STATUS <strong>$beanHeader.status</strong></div></td>
  </tr>
  #end
  #end
  
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201')
  <tr>
    <td><fieldset>
      <legend>KEPUTUSAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach($beanMaklumatKeputusan in $BeanMaklumatKeputusan)
        #if ($idStatus == '1610206')
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Keputusan</td>
          <td width="1%">:</td>
          <td width="70%">
          <select name="socKeputusan" id="socKeputusan" style="width:120px;" $readonly class="$disabled" onchange="javascript:doChangeKeputusan(this.value)" $disabled>
            #if ($beanMaklumatKeputusan.keputusan == 'L')
              <option>SILA PILIH</option>
              <option value="D">LULUS DASAR</option>
              <option value="L" selected>LULUS</option>
              <option value="T">TOLAK</option>
            #elseif ($beanMaklumatKeputusan.keputusan == 'T')
              <option>SILA PILIH</option>
              <option value="D">LULUS DASAR</option>
              <option value="L">LULUS</option>
              <option value="T" selected>TOLAK</option>
            #elseif ($beanMaklumatKeputusan.keputusan == 'D')
              <option>SILA PILIH</option>
              <option value="D" selected>LULUS DASAR</option>
              <option value="L">LULUS</option>
              <option value="T">TOLAK</option>
            #else
              <option selected>SILA PILIH</option>
              <option value="D">LULUS DASAR</option>
              <option value="L">LULUS</option>
              <option value="T">TOLAK</option>
            #end
          </select></td>
        </tr>
        #else
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Keputusan</td>
          <td width="1%">:</td>
          <td width="70%"> #if ($idKeputusan == 'L')
            LULUS
            #elseif ($idKeputusan == 'T')
            TOLAK
            #elseif ($idKeputusan == 'D')
            LULUS DASAR
            #end</td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Hantar Surat</td>
          <td>:</td>
          <td><input name="txtTarikhKeputusan" type="text" class="$inputTextClass" id="txtTarikhKeputusan" onBlur="check_date(this)" value="$beanMaklumatKeputusan.tarikhKeputusan" size="9" maxlength="10" $readonly>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhKeputusan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        #if ($idKeputusan == 'L')
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4"><fieldset>
            <legend>MAKLUMAT PERJANJIAN</legend>
            #foreach($beanMaklumatPerjanjian in $BeanMaklumatPerjanjian)
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Mula Perjanjian</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this);calcDate()" value="$beanMaklumatPerjanjian.tarikhMula" size="9" maxlength="10" $readonly />
                  #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Tempoh</td>
                <td>:</td>
                <td><input type="text" name="txtTempoh" id="txtTempoh" size="1" maxlength="2" value="$beanMaklumatPerjanjian.tempoh" onBlur="validateNumber(this,this.value,'$beanMaklumatPerjanjian.tempoh');calcDate()" $readonly class="$inputTextClass">
                  Bulan</td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Tarikh Tamat Perjanjian</td>
                <td>:</td>
                <td><input name="txtTarikhTamat" type="text" class="$inputTextClass" id="txtTarikhTamat" onBlur="check_date(this);calcDate()" value="$beanMaklumatPerjanjian.tarikhTamat" size="9" maxlength="10" $readonly />
                  #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              #if($idSuburusan != '27')
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Kadar Sewa (RM) asas</td>
                <td>:</td>
                <td><input name="txtKadarSewa" type="text" value="$beanMaklumatPerjanjian.kadarSewa" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.kadarSewa');calcCagaran()" /></td>
              </tr>
              #elseif($idSuburusan == '27')
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Royalti  (RM)</td>
                <td>:</td>
                <td><input name="txtRoyalti" type="text" value="$beanMaklumatPerjanjian.royalti" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.royalti');calcRoyalti()" id="txtRoyalti" /></td>
              </tr>
              #end
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Cagaran (RM)</td>
                <td>:</td>
                <td><input name="txtCagaran" type="text" value="$beanMaklumatPerjanjian.cagaran" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.cagaran');" /></td>
              </tr>
            </table>
            #end
            </fieldset></td>
        </tr>
        #end
        #if ($idKeputusan == 'D')
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4"><fieldset>
            <legend>MAKLUMAT PERJANJIAN</legend>
            #foreach($beanMaklumatPerjanjian in $BeanMaklumatPerjanjian)
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Mula Kelulusan Dasar</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhMulaDasar" type="text" class="$inputTextClass" id="txtTarikhMulaDasar" onBlur="check_date(this);calcDateDasar()" value="$beanMaklumatPerjanjian.tarikhMulaDasar" size="9" maxlength="10" $readonly />
                  #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMulaDasar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Tempoh Dasar</td>
                <td>:</td>
                <td><input type="text" name="txtTempohDasar" id="txtTempohDasar" size="1" maxlength="2" value="$beanMaklumatPerjanjian.tempohDasar" onBlur="validateNumber(this,this.value,'$beanMaklumatPerjanjian.tempohDasar');calcDateDasar()" $readonly class="$inputTextClass">
                  Bulan</td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Tamat Kelulusan Dasar</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhTamatDasar" type="text" class="$inputTextClass" id="txtTarikhTamatDasar" onBlur="check_date(this);calcDateDasar()" value="$beanMaklumatPerjanjian.tarikhTamatDasar" size="9" maxlength="10" $readonly />
                  #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTamatDasar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Mula Perjanjian</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this);calcDate()" value="$beanMaklumatPerjanjian.tarikhMula" size="9" maxlength="10" $readonly />
                  #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Tempoh</td>
                <td>:</td>
                <td><input type="text" name="txtTempoh" id="txtTempoh" size="1" maxlength="2" value="$beanMaklumatPerjanjian.tempoh" onBlur="validateNumber(this,this.value,'$beanMaklumatPerjanjian.tempoh');calcDate()" $readonly class="$inputTextClass">
                  Bulan</td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Tarikh Tamat Perjanjian</td>
                <td>:</td>
                <td><input name="txtTarikhTamat" type="text" class="$inputTextClass" id="txtTarikhTamat" onBlur="check_date(this);calcDate()" value="$beanMaklumatPerjanjian.tarikhTamat" size="9" maxlength="10" $readonly />
                  #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              #if($idSuburusan != '27')
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Kadar Sewa (RM)</td>
                <td>:</td>
                <td><input name="txtKadarSewa" type="text" value="$beanMaklumatPerjanjian.kadarSewa" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.kadarSewa');calcCagaran()" /></td>
              </tr>
              #elseif($idSuburusan == '27')
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Royalti  (RM)</td>
                <td>:</td>
                <td><input name="txtRoyalti" type="text" value="$beanMaklumatPerjanjian.royalti" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.royalti');calcRoyalti()" id="txtRoyalti" /></td>
              </tr>
              #end
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Cagaran (RM)</td>
                <td>:</td>
                <td><input name="txtCagaran" type="text" value="$beanMaklumatPerjanjian.cagaran" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.cagaran');" /></td>
              </tr>
            </table>
            #end
            </fieldset></td>
        </tr>
        #end
        #if ($mode != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> 
          	#if ($mode == 'view')
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
            #end
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
            #if ($idStatus == '1610206')
            #if ($idKeputusan != 'T')
            <input type="button" name="cmdHantar" id="cmdHantar" value="Ke Perjanjian" onClick="doHantarProses()"/>
            #elseif ($idKeputusan == 'T')
            <input type="button" name="cmdHantar" id="cmdHantar" value="Selesai" onClick="doHantarProses()"/>
            #end
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onclick="gotoBatalPermohonan()"/>
            #end
            #end
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKeputusan('$idKeputusan','$idSuburusan')"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/>
            #end
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
            #end </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idKeputusan == 'L')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWSuratTawaran('$idFail','$idPermohonan')"> Surat Tawaran Penyewaan </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWSuratTarikbalikTawaran('$idFail')"> Surat Tarikbalik Tawaran (Tamat Tempoh Maklumbalas)</a></td>
  </tr>
  #elseif($idKeputusan == 'D')
  #if($flagPermohonanDari == '0')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWSuratTawaranDasarDKL('$idFail','$idPermohonan')"> Surat Tawaran Penyewaan Dasar</a></td>
  </tr>
  #elseif ($flagPermohonanDari == '1')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWSuratTawaranDasarLKL('$idFail','$idPermohonan')"> Surat Tawaran Penyewaan Dasar</a></td>
  </tr>
  #end
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWSuratTarikbalikTawaran('$idFail')"> Surat Tarikbalik Tawaran (Tamat Tempoh Maklumbalas)</a></td>
  </tr>
  #elseif($idKeputusan == 'T')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWSuratTolakMesyuarat('$idFail','$idPermohonan')"> Surat Tolak (Mesyuarat) </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWSuratTolakKJP('$idFail')"> Surat Tolak (KJP)</a></td>
  </tr>
  #end
</table>
</fieldset>
<script>
function doChangeKeputusan() {
	doAjaxCall${formName}("doChangeKeputusan");
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
function simpanKeputusan(idKeputusan,idSuburusan) {
	if (idKeputusan == 'L' || idKeputusan == 'D'){
		if (idKeputusan == 'D'){
			if(document.${formName}.txtTarikhMulaDasar.value == ""){
				alert('Sila masukkan Tarikh Mula Kelulusan Dasar');
				document.${formName}.txtTarikhMulaDasar.focus(); 
				return; 
			}
			if(document.${formName}.txtTarikhTamatDasar.value == ""){
				alert('Sila masukkan Tarikh Tamat Kelulusan Dasar');
				document.${formName}.txtTarikhTamatDasar.focus(); 
				return; 
			}
		}
		
		if(document.${formName}.txtTarikhMula.value == ""){
			alert('Sila masukkan Tarikh Mula Perjanjian');
			document.${formName}.txtTarikhMula.focus(); 
			return; 
		}
		if(document.${formName}.txtTempoh.value == ""){
			alert('Sila masukkan Tempoh Perjanjian');
			document.${formName}.txtTempoh.focus(); 
			return; 
		}
		if(document.${formName}.txtTarikhTamat.value == ""){
			alert('Sila masukkan Tarikh Tamat Perjanjian');
			document.${formName}.txtTarikhTamat.focus(); 
			return; 
		}
		if(idSuburusan != '27'){
			if(document.${formName}.txtKadarSewa.value == ""){
				alert('Sila masukkan Kadar Sewa');
				document.${formName}.txtKadarSewa.focus(); 
				return; 
			}
		}
		else{
			if(document.${formName}.txtRoyalti.value == ""){
				alert('Sila masukkan Royalti');
				document.${formName}.txtRoyalti.focus(); 
				return; 
			}
		}
		if(document.${formName}.txtCagaran.value == ""){
			alert('Sila masukkan Cagaran');
			document.${formName}.txtCagaran.focus(); 
			return; 
		}
	}	
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpan";
	doAjaxCall${formName}("");
}
function kemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batal() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doHantarProses(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarProses";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
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
function calcDateDasar(){
	if (document.${formName}.txtTarikhMulaDasar.value != "" && document.${formName}.txtTempohDasar.value != ""){
		
		var tarikhMulaDasar  = document.${formName}.txtTarikhMulaDasar.value;
		var monthDasar  = parseInt(document.${formName}.txtTempohDasar.value);
		
		var dt1   = parseInt(tarikhMulaDasar.substring(0,2),10);
		var mon1  = parseInt(tarikhMulaDasar.substring(3,5),10)-1 + monthDasar;
		var yr1   = parseInt(tarikhMulaDasar.substring(6,10),10);
	 
		var myDate = new Date(yr1, mon1, dt1);
		myDate.setDate(myDate.getDate()-1);
		
		var day = myDate.getDate();
		var month = myDate.getMonth()+1;
		var year = myDate.getFullYear();
		
		var tarikhTamatDasar = "";
		if(month>=10){
			if(day>=10){
				tarikhTamatDasar = day + "/" + month + "/" + year;	
			} else {
				tarikhTamatDasar = "0"+ day + "/" + month + "/" + year;	
			}				
		} else {
			if(day>=10){
				tarikhTamatDasar = day + "/0" + month + "/" + year;	
			} else {
				tarikhTamatDasar = "0"+ day + "/0" + month + "/" + year;	
			}
		}
		document.${formName}.txtTarikhTamatDasar.value = tarikhTamatDasar;
	
	} else {
		document.${formName}.txtTarikhTamatDasar.value = "";
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
function calcRoyalti(){
	if (document.${formName}.txtRoyalti.value != ""){
		
		var royalti  = document.${formName}.txtRoyalti.value*1;
		var cagaran  = 0;
		
		cagaran = royalti * 10/100;
		
		document.${formName}.txtCagaran.value = cagaran.toFixed(2);
	
	} else {
		document.${formName}.txtCagaran.value = "";
	}
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakPYWSuratTawaran(idFail,idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&idPermohonan="+idPermohonan+"&report=PYWSuratTawaran";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTawaranDasarDKL(idFail,idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&idPermohonan="+idPermohonan+"&report=PYWSuratTawaranDasarDKL";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTawaranDasarLKL(idFail,idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&idPermohonan="+idPermohonan+"&report=PYWSuratTawaranDasarLKL";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTolakMesyuarat(idFail,idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&idPermohonan="+idPermohonan+"&report=PYWSuratTolakMesyuarat";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTolakKJP(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratTolakKJP";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratTarikbalikTawaran(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratTarikbalikTawaran";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function gotoSenaraiFailKeseluruhan() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailKeseluruhanView";
	document.${formName}.submit();
}
function doChangeKeputusan() {
	doAjaxCall${formName}("doChangeKeputusan");
}
</script>
