<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>

#set($saizPerkara="1000")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idUrusan" type="hidden" id="idUrusan" value="$idUrusan"/>
  <input name="idHakmilikAgensi" type="hidden" id="idHakmilikAgensi" value="$idHakmilikAgensi"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">No. Fail</td>
          <td width="1%" >:</td>
          <td width="70%"><strong>$beanMaklumatPermohonan.noFail</strong>
            <input name="idPermohonan" type="hidden" value="$beanMaklumatPermohonan.idPermohonan" />
            <input name="idPemohon" type="hidden" value="$beanMaklumatPermohonan.idPemohon" /></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">No. Rujukan <i>Online</i></td>
          <td width="1%" >:</td>
          <td width="70%">$beanMaklumatPermohonan.noPermohonan </td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Urusan</td>
          <td>:</td>
          <td>PELEPASAN / PENYERAHANBALIK</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Suburusan</td>
          <td>:</td>
          <td>$selectSubUrusanPelepasanMain</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Tarikh Terima</td>
          <td>:</td>
          <td>$beanMaklumatPermohonan.tarikhTerima
          </td>
        </tr>
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Perkara</td>
          <td valign="top">:</td>
          <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonlyTajuk class="$inputTextClassTajuk" onKeyUp="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizPerkara);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizPerkara);" onBlur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.perkara</textarea>
          #if ($mode == 'new')
            #if ($idSuburusan =='34')
            <input type="button" name="cmdJanaTajukPLP" id="cmdJanaTajukPLP" value="Jana Tajuk" onclick="janaTajukPLP()"/>
            #elseif ($idSuburusan =='32')
            <input type="button" name="cmdJanaTajukPNW" id="cmdJanaTajukPNW" value="Jana Tajuk" onclick="janaTajukPNW()"/>
            #elseif ($idSuburusan =='33')
            <input type="button" name="cmdJanaTajukTKR" id="cmdJanaTajukTKR" value="Jana Tajuk" onclick="janaTajukTKR()"/>
            #end
           #end </td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td>Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizPerkara" /></td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
        #if ($idSuburusan =='34')
        <tr>
          <td width="1%"></td>
          <td width="28%">Kategori Pemohon</td>
          <td>:</td>
          <td width="70%">KEMENTERIAN / AGENSI</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Nama</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.nama </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Pengenalan / Pendaftaran</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.noPendaftaran </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.alamat1 </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>$beanMaklumatPemohon.alamat2</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>$beanMaklumatPemohon.alamat3</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.poskod </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>E-mel</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.emel </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Tel</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.noTel</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Faks</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.noFaks </td>
        </tr>
        #else
        <tr>
          <td width="1%"></td>
          <td width="28%">Kategori Pemohon</td>
          <td>:</td>
          <td width="70%">KEMENTERIAN / AGENSI</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$selectKementerian</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Agensi</td>
          <td>:</td>
          <td>$selectAgensi</td>
        </tr>
        #foreach ($beanMaklumatAgensi in $BeanMaklumatAgensi)
        <tr>
          <td>&nbsp;</td>
          <td>Nama</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.namaAgensi
            <input type="hidden" name="namaAgensiKem" id="namaAgensiKem" value="$beanMaklumatAgensi.namaAgensi"/></td>
        </tr>
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
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
   <tr>
    <td colspan="2"><fieldset>
      <legend><strong>
      	 #if ($idSuburusan =='34')
        	  MAKLUMAT PELEPASAN
         #elseif ($idSuburusan =='32')
             MAKLUMAT PENAWARAN
         #elseif ($idSuburusan =='33')
             MAKLUMAT TUKARGUNA
         #end
      </strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPelepasan in $BeanMaklumatPelepasan)
        #if ($idSuburusan =='34')
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td width="28%" valign="top">Tujuan Kegunaan</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top">$beanMaklumatPelepasan.namaProjek
          <input type="hidden" name="txtTujuanKegunaan" id="txtTujuanKegunaan" value="$beanMaklumatPelepasan.namaProjek" /></td>
        </tr>
        #elseif ($idSuburusan =='33')
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td width="28%" valign="top">Cadangan Kegunaan</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top">$beanMaklumatPelepasan.cadanganKegunaan
          <input type="hidden" name="txtCadanganKegunaan" id="txtCadanganKegunaan" value="$beanMaklumatPelepasan.cadanganKegunaan" /></td>
        </tr>
        #end
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Luas Kegunaan</td>
          <td width="1%">:</td>
          <td width="70%">$selectLuasKegunaan</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Keluasan Asal</td>
          <td>:</td>
          <td>$beanMaklumatPelepasan.luasAsal $beanMaklumatPelepasan.keteranganLuasAsal
            <input type="hidden" name="txtLuasAsal" id="txtLuasAsal" value="$beanMaklumatPelepasan.luasAsal"/></td>
        </tr>
        #if ($idLuasKegunaan == '2')
        <tr>
          <td>&nbsp;</td>
          <td>Unit Luas</td>
          <td>:</td>
          <td>#parse("app/php2/unit_luas.jsp") </td>
        </tr>
        #if ($beanMaklumatPelepasan.idLuas != '99999' && $beanMaklumatPelepasan.idLuas != '')
        <tr>
          <td>&nbsp;</td>
          <td>Luas Mohon</td>
          <td>:</td>
          <td> #if ($beanMaklumatPelepasan.idLuas == '0' || $beanMaklumatPelepasan.idLuas == '1' || $beanMaklumatPelepasan.idLuas == '2' || $beanMaklumatPelepasan.idLuas == '3' || $beanMaklumatPelepasan.idLuas == '5' || $beanMaklumatPelepasan.idLuas == '6' || $beanMaklumatPelepasan.idLuas == '9')
            <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPelepasan.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" onBlur="kiraLuas('$beanMaklumatPelepasan.idLuas')" $readonly class="$inputTextClass"/>
            #elseif ($beanMaklumatPelepasan.idLuas == '7')
            <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPelepasan.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$beanMaklumatPelepasan.idLuas')"/>
            <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatPelepasan.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" onBlur="kiraLuas('$beanMaklumatPelepasan.idLuas')" $readonly class="$inputTextClass"/>
            #elseif ($beanMaklumatPelepasan.idLuas == '8' || $beanMaklumatPelepasan.idLuas == '4')
            <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPelepasan.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$beanMaklumatPelepasan.idLuas')"/>
            <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatPelepasan.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$beanMaklumatPelepasan.idLuas')"/>
            <input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$beanMaklumatPelepasan.luas3" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" onBlur="kiraLuas('$beanMaklumatPelepasan.idLuas')" $readonly class="$inputTextClass"/>
            #end </td>
        </tr>
        #end
        #end
        <tr>
          <td>&nbsp;</td>
          <td>Luas Bersamaan</td>
          <td>:</td>
          <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" value="$beanMaklumatPelepasan.luasBersamaan"  style="text-align:right" readonly="readonly" class="disabled"/>
            HEKTAR</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Baki Luas</td>
          <td>:</td>
          <td><input type="text" name="txtBakiLuas" id="txtBakiLuas" value="$beanMaklumatPelepasan.luasBaki" readonly="readonly" class="disabled" style="text-align:right"/>
            HEKTAR</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
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
          <td width="1%">&nbsp;</td>
          <td width="28%">Pegangan Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled" size="30">
          </td>
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
          <td>$beanMaklumatTanah.luasLot
            <input type="hidden" name="luasLotTanah" id="luasLotTanah" value="$beanMaklumatTanah.luasBersamaan" /></td>
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
            <input type="hidden" name="namaNegeriTanah" id="namaNegeriTanah" value="$beanMaklumatTanah.negeri" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$beanMaklumatTanah.kementerian
            <input type="hidden" name="namaKementerianTnh" id="namaKementerianTnh" value="$beanMaklumatTanah.kementerian" /></td>
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
            <input type="hidden" name="txtKegunaanTanah" id="txtKegunaanTanah" value="$beanMaklumatTanah.kegunaanTanah" />
            <input type="hidden" name="statusRizab" id="statusRizab" value="$beanMaklumatTanah.statusRizab" /></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
	<td colspan="2">
		#parse("app/php2/frmPLPDaftarManualSenaraiSemak.jsp")
	</td>
  </tr>
  #if ($mode == 'new')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'new')
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar" onclick="daftarBaru()"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
      #else
      <input type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="kembali()"/>
      #end </td>
  </tr>
</table>
<script>
function daftarBaru() {
	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus();
		return;
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionOnline.value = "daftarBaru";
		return;
	}

	document.${formName}.actionOnline.value = "papar";
	document.${formName}.hitButton.value = "daftarBaru";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.actionOnline.value = "";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function janaTajukPLP() {

	var strTajuk = " ";
	var str2  = document.${formName}.noLotTanah.value;
	var str3  = document.${formName}.noMilikTanah.value;
	var noWarta  = document.${formName}.noWartaTanah.value;
	var str4  = document.${formName}.namaMukimTanah.value;
	var str5  = document.${formName}.namaDerahTanah.value;
	var str6  = document.${formName}.namaNegeriTanah.value;
	var kegunaanTanah = document.${formName}.txtKegunaanTanah.value;
	var tujuanKegunaan = document.${formName}.txtTujuanKegunaan.value;

	if(document.${formName}.socLuasKegunaan.value == "1") {
		luasKegunaan = "KESELURUHAN";
	}
	else if(document.${formName}.socLuasKegunaan.value == "2"){
		luasKegunaan = "SEBAHAGIAN";
	}

	if(document.${formName}.statusRizab.value == "MILIK"){
		strTajuk = "PERMOHONAN PENYERAHAN BALIK " + luasKegunaan +" TANAH MILIK PERSEKUTUAN " + str2 +", " + str3 + ", "+ str4 + ", " + str5 + ", " + str6 +" ( " + kegunaanTanah + " )" +" BAGI TUJUAN " + tujuanKegunaan ;
	} else if(document.${formName}.statusRizab.value == "RIZAB"){

				if(document.${formName}.socLuasKegunaan.value == "1"){

		strTajuk = "PERMOHONAN PELEPASAN TANAH RIZAB PERSEKUTUAN " +  str2 +" , " + noWarta +", " + str4 + ", "+ str5 + ", " + str6  +" ( " + kegunaanTanah + " )" +" BAGI TUJUAN " + tujuanKegunaan ;
				}
				else if(document.${formName}.socLuasKegunaan.value == "2"){
		strTajuk = "PERMOHONAN PEMBATALAN TANAH RIZAB PERSEKUTUAN " +  str2 +" , " + noWarta +", " + str4 + ", "+ str5 + ", " + str6  +" ( " + kegunaanTanah + " )" +" BAGI TUJUAN " + tujuanKegunaan;
				}
	}
	document.${formName}.txtPerkara.value = strTajuk;
}
function janaTajukPNW() {

	var strTujuan = " ";
	var strTajuk = " ";
	var luasKegunaan = " ";
	var milikOrRizab = " ";
	var pemohon = " ";


	var str1 = document.${formName}.noLotTanah.value;
	var str2 = document.${formName}.noMilikTanah.value;
	var str3 = document.${formName}.noWartaTanah.value;
	var str4 = document.${formName}.namaMukimTanah.value;
	var str5 = document.${formName}.namaDerahTanah.value;
	var str6 = document.${formName}.namaNegeriTanah.value;
	var kegunaanTanah = document.${formName}.txtKegunaanTanah.value;
	var statusRizabTnh = document.${formName}.statusRizab.value;
	var kjpTnh = document.${formName}.namaKementerianTnh.value;

	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str3;
	}
	strTajuk = "PENAWARAN TANAH " + statusRizabTnh + " PERSEKUTUAN DI ATAS " + str1 +", " + milikOrRizab + ", "+ str4 + ", " + str5+ ", " + str6 +" ( " + kegunaanTanah + " )" + " KEGUNAAN  " + kjpTnh;

	document.${formName}.txtPerkara.value = strTajuk;
}
function janaTajukTKR() {

	var strTujuan = " ";
	var strTajuk = " ";
	var milikOrRizab = " ";
	var	pemohon = document.${formName}.namaAgensiKem.value;

	var str1 = document.${formName}.noLotTanah.value;
	var str2 = document.${formName}.noMilikTanah.value;
	var str3 = document.${formName}.noWartaTanah.value;
	var str4 = document.${formName}.namaMukimTanah.value;
	var str5 = document.${formName}.namaDerahTanah.value;
	var str6 = document.${formName}.namaNegeriTanah.value;
	var kegunaanTanah = document.${formName}.txtKegunaanTanah.value;
	var statusRizabTnh = document.${formName}.statusRizab.value;
	var kjpTnh = document.${formName}.namaKementerianTnh.value;
	var tujuanKegunaan = document.${formName}.txtCadanganKegunaan.value;

	if(document.${formName}.socLuasKegunaan.value == "1") {
		luasKegunaan = "KESELURUHAN";
	}
	else if(document.${formName}.socLuasKegunaan.value == "2"){
		luasKegunaan = "SEBAHAGIAN";
	}

	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str3;
	}

	strTajuk = "PERMOHONAN TUKARGUNA " + luasKegunaan +" TANAH " + statusRizabTnh + " PERSEKUTUAN " + str1 +", " + milikOrRizab + ", "+ str4 + ", " + str5+ ", " + str6 +" ( " + kegunaanTanah + " )" + " DARIPADA " + kjpTnh + " KEPADA " + pemohon +" BAGI TUJUAN " + tujuanKegunaan;


	document.${formName}.txtPerkara.value = strTajuk;


}
</script>
$javascriptLampiran