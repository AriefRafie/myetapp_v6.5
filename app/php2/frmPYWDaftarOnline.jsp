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
  <input name="idHakmilikAgensi" type="hidden" id="idHakmilikAgensi" value="$idHakmilikAgensi"/>
  <input name="idUrusan" type="hidden" id="idUrusan" value="$idUrusan"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
</p>
<table width="100%" border="0">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
        <tr>
          <td width="1%"></td>
          <td width="28%">Kategori Pemohon</td>
          <td>:</td>
          <td width="70%">$selectKategoriPemohon</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Nama</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.nama
            <input type="hidden" name="namaPemohon" id="namaPemohon" value="$beanMaklumatPemohon.nama " /></td>
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
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT TANAH</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Pegangan Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled">
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noLot
            <input type="hidden" name="noLotTanah" id="noLotTanah" value="$beanMaklumatTanah.noLot" />
            <input type="hidden" name="statusRizab" id="statusRizab" value="$beanMaklumatTanah.statusRizab" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.luasLot</td>
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
          <td>$beanMaklumatTanah.kementerian </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Agensi</td>
          <td>:</td>
          <td>$beanMaklumatTanah.agensi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kegunaan Tanah</td>
          <td>:</td>
          <td>$beanMaklumatTanah.kegunaanTanah</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">No Fail</td>
          <td width="1%">:</td>
          <td width="70%"><strong>$beanMaklumatPermohonan.noFail </strong></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">No. Permohonan</td>
          <td width="1%" >:</td>
          <td width="70%">$beanMaklumatPermohonan.noPermohonan </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Urusan</td>
          <td>:</td>
          <td>$selectUrusan</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Suburusan</td>
          <td>:</td>
          <td>$selectSuburusan</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Tarikh Terima</td>
          <td>:</td>
          <td><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" readonly="readonly" class="disabled" size="11">
          </td>
        </tr>
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Perkara</td>
          <td valign="top">:</td>
          <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizPerkara);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizPerkara);" onBlur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.perkara</textarea>
            #if ($mode == 'new')
            <input type="button" name="cmdDaftarBaru2" id="cmdDaftarBaru2" value="Jana Tajuk" onclick="janaTajuk()"/>
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
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.catatan</textarea></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  
  #if ($mode == 'new')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"></td>
          <td width="28%"></td>
          <td width="1%"></td>
          <td width="70%"> #if ($mode == 'new')
            <input type="button" name="cmdDaftar" id="cmdDaftar" value="Daftar" onclick="daftar()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Batal" onclick="kembali()"/>
            #else
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
            #end </td>
        </tr>
      </table></td>
  </tr>
</table>
<script>
function daftar() {
	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionOnline.value = "daftar";
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
function janaTajuk() {

	var strTujuan = " ";
	var strTajuk = " ";
	var milikOrRizab = " ";
	
	if(document.${formName}.socUrusan.value == "12"){
		
		if(document.${formName}.socSuburusan.value == "26") {
		   strTujuan = "KELAPA SAWIT DAN SUSU GETAH";
		} else if(document.${formName}.socSuburusan.value == "27") {
		   strTujuan = "BAHAN BATUAN / TANAH MERAH";
		} else if(document.${formName}.socSuburusan.value == "28") {
		   strTujuan = "KAYU BALAK";
		} else if(document.${formName}.socSuburusan.value == "29") {
		   strTujuan = "KELAPA SAWIT";
		} else if(document.${formName}.socSuburusan.value == "30") {
		   strTujuan = "KAYU GETAH"; 
		} else if(document.${formName}.socSuburusan.value == "31") {
		   strTujuan = "SUSU GETAH";
		}
	}
	
	var str1  = document.${formName}.noLotTanah.value;
	var str2  = document.${formName}.noMilikTanah.value;
	var str3  = document.${formName}.namaMukimTanah.value;
	var str4  = document.${formName}.namaDerahTanah.value;	
	var str5  = document.${formName}.namaNegeriTanah.value;	
	var namaPemohon = document.${formName}.namaPemohon.value;
	var str6 = document.${formName}.noWartaTanah.value;
	var statusRizabTnh = document.${formName}.statusRizab.value;
	
	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str6;
	}

	if(document.${formName}.socUrusan.value == "7"){
		strTajuk = "PERMOHONAN PENYEWAAN TANAH PERSEKUTUAN " + str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan ;
	} else if(document.${formName}.socUrusan.value == "12"){
		strTajuk = "PERMOHONAN MENGELUARKAN HASIL " + strTujuan + " DI " +  str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon ;
	} else {
	    strTajuk;
	 }
		
	document.${formName}.txtPerkara.value = strTajuk;
}
</script>
