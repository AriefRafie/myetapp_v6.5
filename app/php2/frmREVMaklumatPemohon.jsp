<fieldset>
<legend>MAKLUMAT PEMOHON</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Nama</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNama" type="text" class="$inputTextClass" id="txtNama" value="$!beanMaklumatPemohon.nama" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Pengenalan / Pendaftaran</td>
    <td>:</td>
    <td><input name="txtNoPendaftaran" type="text" class="$inputTextClass" id="txtNoPendaftaran" value="$!beanMaklumatPemohon.noPendaftaran" maxlength="50" $readonly onBlur="this.value=this.value.toUpperCase();"/>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Alamat</td>
    <td>:</td>
    <td><input name="txtAlamat1" type="text" class="$inputTextClass" id="txtAlamat1" value="$!beanMaklumatPemohon.alamat1" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input name="txtAlamat2" type="text" class="$inputTextClass" id="txtAlamat2" value="$!beanMaklumatPemohon.alamat2" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input name="txtAlamat3" type="text" class="$inputTextClass" id="txtAlamat3" value="$!beanMaklumatPemohon.alamat3" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Poskod</td>
    <td>:</td>
    <td><input name="txtPoskod" type="text" class="$inputTextClass" id="txtPoskod" value="$!beanMaklumatPemohon.poskod" size="5" maxlength="5" $readonly onKeyUp="validateNumber(this,this.value);">
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Negeri</td>
    <td>:</td>
    <td>$selectNegeri</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Bandar</td>
    <td>:</td>
    <td>$selectBandar</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>E-mel</td>
    <td>:</td>
    <td><input name="txtEmel" type="text" class="$inputTextClass" id="txtEmel" value="$!beanMaklumatPemohon.emel" maxlength="50" $readonly/>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Tel</td>
    <td>:</td>
    <td><input name="txtNoTel" type="text" class="$inputTextClass" id="txtNoTel" onKeyUp="validateNumber(this,this.value);" value="$!beanMaklumatPemohon.noTel" maxlength="12" $readonly/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Faks</td>
    <td>:</td>
    <td><input name="txtNoFaks" type="text" class="$inputTextClass" id="txtNoFaks" onKeyUp="validateNumber(this,this.value);" value="$!beanMaklumatPemohon.noFaks" maxlength="12" $readonly/>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Rujukan</td>
    <td>:</td>
    <td><input name="txtNoRujukan" type="text" class="$inputTextClass" id="txtNoRujukan" onBlur="this.value=this.value.toUpperCase();" value="$!beanMaklumatPemohon.noRujukan" maxlength="12" $readonly />
    </td>
  </tr>
  <tr>
    <td width="1%" valign="top">#if ($mode != 'view')#end</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$!beanMaklumatPemohon.catatan</textarea>    </td>
  </tr>
  #end
  
  #if ($mode == 'update')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini2" id="cmdSimpanKemaskini2" value="Simpan" onClick="doSimpanKemaskiniPemohon()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
      #end
      #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniPemohon()"/>
      #end </td>
  </tr>
</table>
</fieldset>
