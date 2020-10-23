<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>

#set($saizTxtPerkara="1000")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idNegeriPemohon" type="hidden" id="idNegeriPemohon" value="$idNegeriPemohon"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" />
  <input type="hidden" name="idPHPBorangK" id="idPHPBorangK"/>
  <input type="hidden" name="idPPTBorangK" id="idPPTBorangK"/>

</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
<tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Kategori Pemohon</td>
          <td>:</td>
          <td width="70%">$idKategoriPemohon</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$selectKementerian</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Agensi</td>
          <td>:</td>
          <td>$selectAgensi</td>
        </tr>
        #foreach ($beanMaklumatAgensi in $BeanMaklumatAgensi)
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat1</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat2</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat3</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.poskod</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.negeri</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT TANAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Pegangan Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%"> #if ($mode == 'new')
            <!--penawaran-->

            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onBlur="doChangePeganganHakmilik();" />
             <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onClick="pilihTanah('$idKementerian','$idAgensi')">

            #else
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled">
            #end
            <input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi" />
            <span class="style1">$errorPeganganHakmilik</span> </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noLot
          <input type="hidden" name="noLotTanah" id="noLotTanah" value="$beanMaklumatTanah.noLot" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.luasLot</td>
          <input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$!beanMaklumatTanah.luas" />
              <input type="hidden" name="luasTanah" id="luasTanah" value="$beanMaklumatTanah.luasBersamaan" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Hakmilik</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noHakmilik
          <input type="hidden" name="noMilikTanah" id="noMilikTanah" value="$beanMaklumatTanah.noHakmilik" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Warta</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noWarta
            <input type="hidden" name="noWartaTanah" id="noWartaTanah" value="$beanMaklumatTanah.noWarta" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Warta</td>
          <td>:</td>
          <td>$beanMaklumatTanah.tarikhWarta</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Mukim</td>
          <td>:</td>
          <td>$beanMaklumatTanah.mukim
            <input type="hidden" name="namaMukimTanah" id="namaMukimTanah" value="$beanMaklumatTanah.mukim" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Daerah</td>
          <td>:</td>
          <td>$beanMaklumatTanah.daerah
            <input type="hidden" name="namaDerahTanah" id="namaDerahTanah" value="$beanMaklumatTanah.daerah" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatTanah.negeri
            <input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$beanMaklumatTanah.idNegeri" />
            <input type="hidden" name="kodNegeriTanah" id="kodNegeriTanah" value="$beanMaklumatTanah.kodNegeriTanah" />
              <input type="hidden" name="namaNegeriTanah" id="namaNegeriTanah" value="$beanMaklumatTanah.negeri" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$beanMaklumatTanah.kementerian

            <input type="hidden" name="kodKementerian" id="kodKementerian" value="$beanMaklumatTanah.kodKementerian" />
            <input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatTanah.idKementerian" />
            <input type="hidden" name="namaKementerianTanah" id="namaKementerianTanah" value="$beanMaklumatTanah.kementerian" />          </td>
        </tr>
        <tr>
          <td height="31">&nbsp;</td>
          <td>Agensi</td>
          <td>:</td>
          <td>$beanMaklumatTanah.agensi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kegunaan Tanah</td>
          <td>:</td>
          <td>$beanMaklumatTanah.kegunaanTanah
            <input type="hidden" name="kegunaanTanah" id="kegunaanTanah" value="$beanMaklumatTanah.kegunaanTanah" />
              <input type="hidden" name="statusRizab" id="statusRizab" value="$beanMaklumatTanah.statusRizab" /></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
    <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT KEGUNAAN TANAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Luas Kegunaan</td>
          <td width="1%">:</td>
          <td width="70%">$!selectLuasKegunaan </td>
        </tr>
        <tr>
          <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tujuan Kegunaan</td>
          <td valign="top">:</td>
          <td valign="top">
          	<textarea name="txtTujuanKegunaan" id="txtTujuanKegunaan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tujuanKegunaan</textarea>
          </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
<tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">No. Rujukan <em>Online</em></td>
          <td width="1%" >:</td>
          <td width="70%"><strong>$beanMaklumatPermohonan.noRujukanOnline</strong>
            <input name="idPermohonan" type="hidden" value="$beanMaklumatPermohonan.idPermohonan" />
            <input name="idPemohon" type="hidden" value="$beanMaklumatPermohonan.idPemohon" /></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Urusan</td>
          <td>:</td>
          <td>PENAWARAN</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Mohon</td>
          <td>:</td>
          <td><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" size="9" $readonly class="$inputTextClass" onblur="check_date(this);cekTarikhTerima(this)"/>
            #if ($mode != 'view') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Surat</td>
          <td>:</td>
          <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat" onblur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">No. Rujukan Surat</td>
          <td>:</td>
          <td><input name="txtNoRujukanSurat" type="text" class="$inputTextClass" id="txtNoRujukanSurat" value="$beanMaklumatPermohonan.noRujukanSurat" $readonly onblur="this.value=this.value.toUpperCase();" size="50" maxlength="50"/></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'new')
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Simpan" onClick="daftarBaru()"/>
      <input type="button" name="cmdKembali" id="cmdKembali" value="Batal" onClick="kembali()"/>
      #end
      #if ($mode == 'view')
      <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/>
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
      #end </td>
       </td>
  </tr>
</table>
<script>
function pilihTanah(idKementerian,idAgensi) {
	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmPNWPopupSenaraiTanahOnlineView?idKementerian="+idKementerian+"&idAgensi="+idAgensi;

    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensi) {
	document.${formName}.idHakmilikAgensi.value = idHakmilikAgensi;
	doAjaxCall${formName}("doChangeMaklumatTanah");
}
function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}
function daftarBaru() {

	if(document.${formName}.idHakmilikAgensi.value == ""){
		alert('Sila pilih Pegangan Hakmilik.');
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionOnline.value = "daftarBaru";
		return;
	}

	/* document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doDaftarBaru";
	document.${formName}.submit(); */
	document.${formName}.actionOnline.value = "papar";
	document.${formName}.hitButton.value = "doDaftarBaru";
	doAjaxCall${formName}("");
}
function kembali() {
	document.${formName}.actionOnline.value = "";
	document.${formName}.submit();
}
function janaTajuk(idKategoriPemohon) {

	if(document.${formName}.idHakmilikAgensi.value == ""){
		alert('Sila pilih Pegangan Hakmilik Sebelum Menjana Tajuk.');
		document.${formName}.idHakmilikAgensi.focus();
		return;
	}
	if(document.${formName}.socLuasKegunaan.value == ""){
		alert('Sila masukkan Luas Kegunaan.');
		document.${formName}.socLuasKegunaan.focus();
		return;
	}
		if(document.${formName}.txtTujuanKegunaan.value == ""){
		alert('Sila masukkan Tujuan Kegunaan.');
  		document.${formName}.txtTujuanKegunaan.focus();
		return;
	}

	var strTajuk = " ";
	var luasKegunaan = " ";
	var milikOrRizab = " ";
	var pemohon = document.${formName}.socKementerian.value;

	if(document.${formName}.socLuasKegunaan.value == "1") {
		luasKegunaan = "KESELURUHAN";
	}
	else if(document.${formName}.socLuasKegunaan.value == "2"){
		luasKegunaan = "SEBAHAGIAN";
	}

	var str1 = document.${formName}.noLotTanah.value;
	var str2 = document.${formName}.noMilikTanah.value;
	var str3 = document.${formName}.noWartaTanah.value;
	var str4 = document.${formName}.namaMukimTanah.value;
	var str5 = document.${formName}.namaDerahTanah.value;
	var str6 = document.${formName}.namaNegeriTanah.value;
	var kegunaanTanah = document.${formName}.kegunaanTanah.value;
	var statusRizabTnh = document.${formName}.statusRizab.value;
	var kjpTnh = document.${formName}.namaKementerianTanah.value;
	var tujuanKegunaan = document.${formName}.txtTujuanKegunaan.value;
	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str3;
	}
	//strTajuk = "PERMOHONAN TUKARGUNA " + luasKegunaan +" TANAH " + statusRizabTnh + " PERSEKUTUAN " + str1 +", " + milikOrRizab + ", "+ str4 + ", " + str5+ ", " + str6 +" (" + kegunaanTanah + ")" + " DARIPADA " + kjpTnh + " KEPADA " + pemohon +" BAGI TUJUAN " + tujuanKegunaan;
	strTajuk = "PENAWARAN TANAH " + statusRizabTnh + " PERSEKUTUAN DI ATAS " + str1 +", " + milikOrRizab + ", "+ str4 + ", " + str5+ ", " + str6 +" ( " + kegunaanTanah + " )" + " KEGUNAAN  " + kjpTnh;
	document.${formName}.txtPerkara.value = strTajuk;
}
function seterusnya(){
	//alert('BACA SETERUSNYAAA');
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWOnlineKJPSenaraiFailView";
	document.${formName}.method="POST";	
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.submit();
}
</script>
