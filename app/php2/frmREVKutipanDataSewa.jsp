<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="step" type="hidden" id="step" value="$!step"/>
  <input name="mode" type="hidden" id="mode" value="$!mode"/>
  <input name="hitButton" type="hidden"/>
  <input name="actionHasil" type="hidden"/>
  <input name="idFail" type="hidden" id="idFail" value="$!idFail"/>
  <input name="idHasil" type="hidden" id="idHasil" value="$!idHasil"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$!idPemohon"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($!flagMsg == 'Y')
  <tr>
    <td colspan="3">&nbsp;
      <div class="success">$outputMsg</div></td>
  </tr>
  #end
  #if ($!flagMsg == 'N')
  <tr>
    <td colspan="3">&nbsp;
      <div class="error">$!outputMsg</div></td>
  </tr>
  #end
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($!beanMaklumatPermohonan in $!BeanMaklumatPermohonan)
        #if ($!mode == 'new')
        <tr>
          <td width="1%">#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%" valign="top">No. Fail</td>
          <td width="1%" >:</td>
          <td width="70%"><input name="noFail" type="text" id="noFail" size="49" maxlength="100" $!readonly class="$!inputTextClass" value="$!beanMaklumatPermohonan.noFail" onBlur="this.value=this.value.toUpperCase();checkingExistNoFail();" />
          </td>
        </tr>
        #end
        #if ($!mode == 'view')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">No. Fail</td>
          <td width="1%" >:</td>
          <td width="70%"><strong>$!beanMaklumatPermohonan.noFail</strong> </td>
        </tr>
        #end
        <tr>
          <td width="1%">#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Urusan</td>
          <td>:</td>
          <td>$!selectUrusan</td>
        </tr>
        <tr>
          <td width="1%">#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Suburusan</td>
          <td>:</td>
          <td>$!selectSuburusan</td>
        </tr>
        <tr>
          <td width="1%" valign="top">#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Perkara</td>
          <td valign="top">:</td>
          <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $!readonly class="$!inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$!beanMaklumatPermohonan.perkara</textarea>
          </td>
        </tr>
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td valign="top">Tujuan Sewa</td>
          <td valign="top">:</td>
          <td><textarea name="txtTujuan" id="txtTujuan" rows="5" cols="50" $!readonly class="$!inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$!beanMaklumatPermohonan.tujuan</textarea>
          </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PENYEWA</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($!beanMaklumatPemohon in $!BeanMaklumatPemohon)
        <tr>
          <td width="1%">#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Nama</td>
          <td>:</td>
          <td width="70%"><input name="txtNama" type="text" class="$!inputTextClass" id="txtNama" value="$!beanMaklumatPemohon.nama" size="43" maxlength="80" $!readonly onBlur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Pengenalan / Pendaftaran</td>
          <td>:</td>
          <td><input name="txtNoPendaftaran" type="text" class="$!inputTextClass" id="txtNoPendaftaran" value="$!beanMaklumatPemohon.noPendaftaran" maxlength="12" $!readonly onBlur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td><input name="txtAlamat1" type="text" class="$!inputTextClass" id="txtAlamat1" value="$!beanMaklumatPemohon.alamat1" size="43" maxlength="80" $!readonly onBlur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input name="txtAlamat2" type="text" class="$!inputTextClass" id="txtAlamat2" value="$!beanMaklumatPemohon.alamat2" size="43" maxlength="80" $!readonly onBlur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input name="txtAlamat3" type="text" class="$!inputTextClass" id="txtAlamat3" value="$!beanMaklumatPemohon.alamat3" size="43" maxlength="80" $!readonly onBlur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td><input name="txtPoskod" type="text" class="$!inputTextClass" id="txtPoskod" value="$!beanMaklumatPemohon.poskod" size="5" maxlength="5" $!readonly onBlur="validateNumber(this,this.value,'$!beanMaklumatPemohon.poskod');validateLength(this.value);"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$!selectNegeri</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Bandar</td>
          <td>:</td>
          <td>$!selectBandar</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>E-mel</td>
          <td>:</td>
          <td><input name="txtEmel" type="text" class="$!inputTextClass" id="txtEmel" value="$!beanMaklumatPemohon.emel" maxlength="50" $!readonly/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Tel</td>
          <td>:</td>
          <td><input name="txtNoTel" type="text" class="$!inputTextClass" id="txtNoTel" onKeyUp="validateNumber(this,this.value);" value="$!beanMaklumatPemohon.noTel" maxlength="12" $!readonly/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Faks</td>
          <td>:</td>
          <td><input name="txtNoFaks" type="text" class="$!inputTextClass" id="txtNoFaks" onKeyUp="validateNumber(this,this.value);" value="$!beanMaklumatPemohon.noFaks" maxlength="12" $!readonly/>
          </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT TANAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($!beanMaklumatTanah in $!BeanMaklumatTanah)
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Negeri</td>
          <td>:</td>
          <td>$!selectNegeriTanah</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Kementerian</td>
          <td>:</td>
          <td>$!selectKementerian</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Agensi</td>
          <td>:</td>
          <td>$!selectAgensi</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">Maklumat Lot</td>
          <td valign="top">:</td>
          <td width="70%" valign="top"><textarea name="txtMaklumatLot" id="txtMaklumatLot" rows="5" cols="50" $!readonly class="$!inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$!beanMaklumatTanah.maklumatLot</textarea></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jenis Luas</td>
          <td>:</td>
          <td>#parse("app/php2/unit_luas.jsp") </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas</td>
          <td>:</td>
          <td><input name="txtLuas" type="text" class="$!inputTextClass" id="txtLuas" value="$!beanMaklumatTanah.luas" maxlength="50" $!readonly/>
          </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERJANJIAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($!beanMaklumatPerjanjian in $!BeanMaklumatPerjanjian)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">No. Siri Perjanjian</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtNoSiri" id="txtNoSiri" value="$!beanMaklumatPerjanjian.noSiri" onBlur="this.value=this.value.toUpperCase();" $!readonly class="$!inputTextClass"/>
          </td>
        </tr>
        <tr>
          <td>#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td>Status Kelulusan Dasar</td>
          <td>:</td>
          <td> #if ($!mode != 'view')
            <select class="texts" name="flagKelulusanDasar" id="flagKelulusanDasar" $!disabled onchange="doChangeFlagKelulusanDasar()">
              <option value=>SILA PILIH</option>
              <option #if ( $!beanMaklumatPerjanjian.flagKelulusanDasar =="Y" ) selected #end value="Y">YA</option>
              <option #if ( $!beanMaklumatPerjanjian.flagKelulusanDasar =="T" ) selected #end value="T">TIDAK</option>
            </select>
            #else
            <select class="disabled" name="flagKelulusanDasar" id="flagKelulusanDasar" $!disabled onchange="doChangeFlagKelulusanDasar()">
              <option value=>SILA PILIH</option>
              <option #if ( $!beanMaklumatPerjanjian.flagKelulusanDasar =="Y" ) selected #end value="Y">YA</option>
              <option #if ( $!beanMaklumatPerjanjian.flagKelulusanDasar =="T" ) selected #end value="T">TIDAK</option>
            </select>
            #end </td>
        </tr>
        #if ($!beanMaklumatPerjanjian.flagKelulusanDasar != "Y")
        <tr>
          <td>#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td>Tarikh Mula Perjanjian</td>
          <td>:</td>
          <td><input name="txtTarikhMula" type="text" class="$!inputTextClass" id="txtTarikhMula" onBlur="check_date(this);calcDate()" value="$!beanMaklumatPerjanjian.tarikhMula" size="9" maxlength="10" $!readonly />
            #if ($!mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td>Tempoh</td>
          <td>:</td>
          <td><input type="text" name="txtTempoh" id="txtTempoh" size="1" maxlength="2" value="$!beanMaklumatPerjanjian.tempoh" onBlur="validateNumber(this,this.value,'$!beanMaklumatPerjanjian.tempoh');calcDate()" $!readonly class="$!inputTextClass">
            Bulan</td>
        </tr>
        <tr>
          <td>#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td>Tarikh Tamat Perjanjian</td>
          <td>:</td>
          <td><input name="txtTarikhTamat" type="text" class="$!inputTextClass" id="txtTarikhTamat" onBlur="check_date(this);calcDate()" value="$!beanMaklumatPerjanjian.tarikhTamat" size="9" maxlength="10" $!readonly />
            #if ($!mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td>Kadar Sewa (RM)</td>
          <td>:</td>
          <td><input name="txtKadarSewa" type="text" value="$!beanMaklumatPerjanjian.kadarSewa" $!readonly class="$!inputTextClass" onBlur="validateCurrency(this,this.value,'$!beanMaklumatPerjanjian.kadarSewa');calcCagaran()" /></td>
        </tr>
        <tr>
          <td>#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td>Cagaran (RM)</td>
          <td>:</td>
          <td><input name="txtCagaran" type="text" value="$!beanMaklumatPerjanjian.cagaran" $!readonly class="$!inputTextClass" onBlur="validateCurrency(this,this.value,'$!beanMaklumatPerjanjian.cagaran');" /></td>
        </tr>
        <tr>
          <td>#if ($!mode != 'view')<span class="style1">*</span>#end</td>
          <td>Mod Caj Sewaan</td>
          <td>:</td>
          <td> #if ($!mode != 'view')
            <select name="modCajSewaan" id="modCajSewaan" $!disabled class="texts">
              <option value=>SILA PILIH</option>
              <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="1" ) selected #end value="1">SETIAP BULAN</option>
              <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="3" ) selected #end value="3">SETIAP 3 BULAN</option>
              <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="6" ) selected #end value="6">SETIAP 6 BULAN</option>
              <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="12" ) selected #end value="12">SETIAP TAHUN</option>
              <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="0" ) selected #end value="0">CAJ PENUH</option>
            </select>
            #else
            <select name="modCajSewaan" id="modCajSewaan" $!disabled class="disabled">
              <option value=>SILA PILIH</option>
              <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="1" ) selected #end value="1">SETIAP BULAN</option>
              <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="3" ) selected #end value="3">SETIAP 3 BULAN</option>
              <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="6" ) selected #end value="6">SETIAP 6 BULAN</option>
              <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="12" ) selected #end value="12">SETIAP TAHUN</option>
              <option #if ( $!beanMaklumatPerjanjian.modCajSewaan =="0" ) selected #end value="0">CAJ PENUH</option>
            </select>
            #end </td>
        </tr>
        #else
        <tr>
          <td>&nbsp;</td>
          <td>Kadar Sewa (RM)</td>
          <td>:</td>
          <td><input name="txtKadarSewa" type="text" value="$!beanMaklumatPerjanjian.kadarSewa" $!readonly class="$!inputTextClass" onBlur="validateCurrency(this,this.value,'$!beanMaklumatPerjanjian.kadarSewa');calcCagaran()" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Cagaran (RM)</td>
          <td>:</td>
          <td><input name="txtCagaran" type="text" value="$!beanMaklumatPerjanjian.cagaran" $!readonly class="$!inputTextClass" onBlur="validateCurrency(this,this.value,'$!beanMaklumatPerjanjian.cagaran');" /></td>
        </tr>
        #end
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">Catatan</td>
          <td valign="top">:</td>
          <td width="70%" valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $!readonly class="$!inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$!beanMaklumatPerjanjian.catatan</textarea></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($!mode != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($!mode == 'new')
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar" onclick="daftarBaru()"/>
      #end
      #if ($!mode == 'view')
      <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya($idHasil)"/>
      #end </td>
  </tr>
</table>
<div id="checkingExistNoFail_result"></div>
<script>
function doChangeUrusan() {
	doAjaxCall${formName}("doChangeUrusan");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeLuas() {
	doAjaxCall${formName}("doChangeLuas");
}
function doChangeFlagKelulusanDasar() {
	doAjaxCall${formName}("doChangeFlagKelulusanDasar");
}
function validateLength(str) {	
	if (str.length < 5) {
 		alert("Sila Masukan Poskod Dengan Betul.")
		document.${formName}.txtPoskod.value = "";
		return false
	}
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
function checkingExistNoFail() {
	url = "../servlet/ekptg.view.php2.FrmREVServlet?command=checkingExistNoFail";
	actionName = "checkingExistNoFail";
	target = "checkingExistNoFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function popupMsg(){
	alert('No. Fail telah wujud.');
	document.${formName}.noFail.value = "";
	document.${formName}.noFail.focus(); 
}
function daftarBaru() {
	if(document.${formName}.noFail.value.trim() == ''){
		alert('Sila masukkan No Fail.');
  		document.${formName}.noFail.focus(); 
		return; 
	}
	if(document.${formName}.socUrusan.value == ""){
		alert('Sila pilih Jenis Urusan.');
  		document.${formName}.socUrusan.focus(); 
		return; 
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih Jenis Suburusan.');
  		document.${formName}.socSuburusan.focus(); 
		return; 
	}
	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus(); 
		return; 
	}
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukan Nama.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukan Nama.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}	
	if(document.${formName}.flagKelulusanDasar.value == ""){
		alert('Sila pilih Status Kelulusan Dasar.');
  		document.${formName}.flagKelulusanDasar.focus(); 
		return; 
	}
	
	if (document.${formName}.flagKelulusanDasar.value != 'Y') {
		if(document.${formName}.txtTarikhMula.value == ""){
			alert('Sila masukan Tarikh Mula Perjanjian.');
			document.${formName}.txtTarikhMula.focus(); 
			return; 
		}
		if(document.${formName}.txtTempoh.value == ""){
			alert('Sila masukan Tempoh.');
			document.${formName}.txtTempoh.focus(); 
			return; 
		}
		if(document.${formName}.txtTarikhTamat.value == ""){
			alert('Sila masukan Tarikh Tamat Perjanjian.');
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
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.step.value = "papar";
	document.${formName}.hitButton.value = "daftarBaru";
	doAjaxCall${formName}("");
}
function seterusnya(idHasil){
	document.${formName}.action = "$EkptgUtil.getTabID("Hasil",$portal_role)?_portal_module=ekptg.view.php2.FrmREVMemantauBayaranSewaView";
	document.${formName}.actionHasil.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.idHasil.value = idHasil;
	document.${formName}.submit();
}
</script>
