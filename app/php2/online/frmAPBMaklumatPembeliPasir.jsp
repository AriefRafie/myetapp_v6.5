<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT PEMBELI PASIR</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPembeliPasir in $BeanMaklumatPembeliPasir)
        <tr>
          <td width="1%">#if ($mode == 'newPembeliPasir' ||  $mode == 'updatePembeliPasir')<span class="style1">*</span>#end</td>
          <td width="28%">Nama</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNamaPembeliPasir" type="text" class="$inputTextClass" id="txtNamaPembeliPasir" value="$beanMaklumatPembeliPasir.nama" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td>#if ($mode == 'newPembeliPasir' ||  $mode == 'updatePembeliPasir')<span class="style1">*</span>#end</td>
          <td>Alamat</td>
          <td>:</td>
          <td><input name="txtAlamat1PembeliPasir" type="text" class="$inputTextClass" id="txtAlamat1PembeliPasir" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatPembeliPasir.alamat1" size="43" maxlength="80" $readonly/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td><input name="txtAlamat2PembeliPasir" type="text" class="$inputTextClass" id="txtAlamat2PembeliPasir" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatPembeliPasir.alamat2" size="43" maxlength="80" $readonly/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td><input name="txtAlamat3PembeliPasir" type="text" class="$inputTextClass" id="txtAlamat3PembeliPasir" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatPembeliPasir.alamat3" size="43" maxlength="80" $readonly/></td>
        </tr>
        <tr>
          <td>#if ($mode == 'newPembeliPasir' ||  $mode == 'updatePembeliPasir')<span class="style1">*</span>#end</td>
          <td>Poskod</td>
          <td>:</td>
          <td><input type="text" name="txtPoskodPembeliPasir" id="txtPoskodPembeliPasir" size="4" onKeyUp="validatePoskod(this,this.value);" maxlength="5" value="$beanMaklumatPembeliPasir.poskod" $readonly class="$inputTextClass"/></td>
        </tr>
        <tr>
          <td>#if ($mode == 'newPembeliPasir' ||  $mode == 'updatePembeliPasir')<span class="style1">*</span>#end</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$selectNegeriPembeliPasir</td>
        </tr>
        <tr>
          <td>#if ($mode == 'newPembeliPasir' ||  $mode == 'updatePembeliPasir')<span class="style1">*</span>#end</td>
          <td>Bandar</td>
          <td>:</td>
          <td>$selectBandarPembeliPasir</td>
        </tr>
        <tr>
          <td>#if ($mode == 'newPembeliPasir' ||  $mode == 'updatePembeliPasir')<span class="style1">*</span>#end</td>
          <td>No. Telefon</td>
          <td>:</td>
          <td><input type="text" name="txtNoTelPembeliPasir" id="txtNoTelPembeliPasir" size="10" maxlength="10" value="$beanMaklumatPembeliPasir.noTel" $readonly class="$inputTextClass" onBlur="validatePoskod(this,this.value);"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Faks</td>
          <td>:</td>
          <td><input type="text" name="txtNoFaxPembeliPasir" id="txtNoFaxPembeliPasir" size="10" maxlength="10" value="$beanMaklumatPembeliPasir.noFax" $readonly class="$inputTextClass" onBlur="validatePoskod(this,this.value);"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jenis Perjanjian</td>
          <td>:</td>
          <td>$selectJenisPerjanjian</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($mode == 'newPembeliPasir' ||  $mode == 'updatePembeliPasir')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'newPembeliPasir')
      <input type="button" name="cmdSimpanPembeliPasir" id="cmdSimpanPembeliPasir" value="Simpan" onClick="simpanPembeliPasir()"/>
      <input type="button" name="cmdBatalPembeliPasir" id="cmdBatalPembeliPasir" value="Batal" onClick="batalPembeliPasir()"/>
      #end
      #if ($mode == 'viewPembeliPasir')
      <input type="button" name="cmdKemaskiniPembeliPasir" id="cmdKemaskiniPembeliPasir" value="Kemaskini" onClick="kemaskiniPembeliPasir()"/>
      <input type="button" name="cmdHapusPembeliPasir" id="cmdHapusPembeliPasir" value="Hapus" onClick="hapusPembeliPasir()"/>
      <input type="button" name="cmdBatalPembeliPasir" id="cmdBatalPembeliPasir" value="Kembali" onClick="batalPembeliPasir()"/>
      #end
      #if ($mode == 'updatePembeliPasir')
      <input type="button" name="cmdSimpanKemaskiniPembeliPasir" id="cmdSimpanKemaskiniPembeliPasir" value="Simpan" onClick="simpanKemaskiniPembeliPasir()"/>
      <input type="button" name="cmdBatalKemaskiniPembeliPasir" id="cmdSimpanKemaskiniPembeliPasir" value="Batal" onClick="batalKemaskiniPembeliPasir()"/>
      #end </td>
  </tr>
</table>
