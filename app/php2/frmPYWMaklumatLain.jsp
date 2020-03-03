<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatLaporanTanah in $BeanMaklumatLaporanTanah)
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>LOKASI</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Jalan Hubungan</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtJalanHubungan" type="text" class="$inputTextClass" id="txtJalanHubungan" value="$beanMaklumatLaporanTanah.jalanHubungan" size="40" maxlength="100"  $readonly/>
          </td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Kawasan Berhampiran</td>
          <td>:</td>
          <td><input name="txtKawasanBerhampiran" type="text" class="$inputTextClass" id="txtKawasanBerhampiran"  value="$beanMaklumatLaporanTanah.kawasanBerhampiran" size="40" maxlength="100" $readonly/>
          </td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Nama Bandar</td>
          <td>:</td>
          <td><input name="txtNamaBandar" type="text" class="$inputTextClass" id="txtNamaBandar"  value="$beanMaklumatLaporanTanah.namaBandar" size="40" maxlength="1000" $readonly/>
          </td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Anggaran Jarak Dari Bandar</td>
          <td>:</td>
          <td><input name="txtJarakDariBandar" type="text" class="$inputTextClass" id="txtJarakDariBandar" onkeyup="validateNumber(this,this.value,'$beanMaklumatLaporanTanah.jarakDariBandar')"  value="$beanMaklumatLaporanTanah.jarakDariBandar" size="3" maxlength="100" $readonly/>
            KM </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>PEMBANGUNAN SEKITAR</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Kemudahan Asas</td>
          <td width="1%">:</td>
          <td width="70%"> #set($checkedA = "")
            #set($checkedL = "")
            #set($checkedT = "")
            
            #if ($beanMaklumatLaporanTanah.flagAir == 'Y')
            #set($checkedA = "checked")
            #end
            #if ($beanMaklumatLaporanTanah.flagElektrik == 'Y')
            #set($checkedL = "checked")
            #end
            #if ($beanMaklumatLaporanTanah.flagTel == 'Y')
            #set($checkedT = "checked")
            #end
            <input type="checkbox" name="kemudahanAsasA" id="kemudahanAsasA" value="Y" $checkedA $disabled/>
            Air &nbsp;
            <input type="checkbox" name="kemudahanAsasL" id="kemudahanAsasL" value="Y" $checkedL $disabled/>
            Elektrik &nbsp;
            <input type="checkbox" name="kemudahanAsasT" id="kemudahanAsasT" value="Y" $checkedT $disabled/>
            Telefon </td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Kemudahan Asas Lain</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtKemudahanAsas" id="txtKemudahanAsas" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.kemudahanAsas</textarea></td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Keadaan Tanah</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtKeadaanTanah" id="txtKeadaanTanah" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.keadaanTanah</textarea></td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Keadaan Rupabumi</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtKeadaanRupabumi" id="txtKeadaanRupabumi" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.keadaanRupabumi</textarea></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend>LOT-LOT BERSEMPADAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr valign="top">
          <td width="50%"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="29%" valign="top">Utara</td>
                <td width="1%" valign="top">:</td>
                <td width="70%" valign="top"><textarea name="txtUtara" id="txtUtara" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.utara</textarea></td>
              </tr>
              <tr>
                <td valign="top">Selatan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtSelatan" id="txtSelatan" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.selatan</textarea></td>
              </tr>
            </table></td>
          <td width="50%"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="29%" valign="top">Timur</td>
                <td width="1%" valign="top">:</td>
                <td width="70%" valign="top"><textarea name="txtTimur" id="txtTimur" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.timur</textarea></td>
              </tr>
              <tr>
                <td valign="top">Barat</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtBarat" id="txtBarat" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.barat</textarea></td>
              </tr>
            </table></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend>SEJARAH PEROLEHAN TANAH</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr valign="top">
          <td width="50%"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td valign="top">Sejarah Tanah</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtSejarahTanah" id="txtSejarahTanah" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.sejarahTanah</textarea></td>
              </tr>
          </table></td>
      </tr>
  	  </table>
  	</fieldset></td>
  </tr>
  #end
  <tr>
    <td width="35%">&nbsp;</td>
    <td width="65%"> #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
      #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniMaklumatLain()"/>
      #if($idStatus == '1610200')
      <!-- <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/> -->
      <!-- <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/> -->
      #end
      #end
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="simpanKemaskiniMaklumatLain()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onclick="batalKemaskiniMaklumatLain()"/>
      #end
      #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
      #end </td>
  </tr>
</table>
<script>
function kemaskiniMaklumatLain() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMaklumatLain() {
	if(document.${formName}.txtJalanHubungan.value == ""){
		alert('Sila masukkan Jalan Hubungan');
  		document.${formName}.txtJalanHubungan.focus(); 
		return; 
	}
	if(document.${formName}.txtKawasanBerhampiran.value == ""){
		alert('Sila masukkan Kawasan Berhampiran.');
  		document.${formName}.txtKawasanBerhampiran.focus(); 
		return; 
	}
	if(document.${formName}.txtJarakDariBandar.value == ""){
		alert('Sila masukkan Anggaran Jarak Dari Bandar.');
  		document.${formName}.txtJarakDariBandar.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatLain";
	doAjaxCall${formName}("");
}
function batalKemaskiniMaklumatLain() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
</script>
