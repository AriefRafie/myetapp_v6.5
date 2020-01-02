<style type="text/css">
<!--
.style2 {
	color: #FF0000
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT PENCEROBOH</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPenceroboh in $BeanMaklumatPenceroboh)
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style2">*</span>#end</td>
          <td width="28%">Nama</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtNama" id="txtNama" value="$beanMaklumatPenceroboh.nama" size="30" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td></td>
          <td>No. Telefon Rumah</td>
          <td>:</td>
          <td><input name="txtNoTelefon" type="text" id="txtNoTelefon" value="$beanMaklumatPenceroboh.noTelefon" $readonlyPopup class="$inputTextClassPopup" maxlength="10" onkeyup="validateNumber(this,this.value);" ></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Tel. Bimbit</td>
          <td>:</td>
          <td><input name="txtNoTelefonBimbit" type="text" id="txtNoTelefonBimbit" value="$beanMaklumatPenceroboh.noTelefonBimbit" $readonlyPopup class="$inputTextClassPopup" maxlength="10" onkeyup="validateNumber(this,this.value);">
            </label></td>
        </tr>
        <tr>
          <td></td>
          <td>Bangsa</td>
          <td>:</td>
          <td>$selectBangsa</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td><input name="txtAlamat1" type="text" id="txtAlamat1" value="$beanMaklumatPenceroboh.alamat1" size="43" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();" ></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input name="txtAlamat2" type="text" id="txtAlamat2" value="$beanMaklumatPenceroboh.alamat2" size="43" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input name="txtAlamat3" type="text" id="txtAlamat3" value="$beanMaklumatPenceroboh.alamat3" size="43" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();"></td>
        </tr>
        <tr>
          <td></td>
          <td>Poskod</td>
          <td>:</td>
          <td><input name="txtPoskod" type="text" id="txtPoskod" value="$beanMaklumatPenceroboh.poskod" size="5" maxlength="5" $readonlyPopup class="$inputTextClassPopup" onkeyup="validateNumber(this,this.value);" ></td>
        </tr>
        <tr>
          <td></td>
          <td>Negeri</td>
          <td>:</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td></td>
          <td>Bandar</td>
          <td>:</td>
          <td>$selectBandar</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>E-mel</td>
          <td>:</td>
          <td><input name="txtEmel" type="text" id="txtEmel" value="$beanMaklumatPenceroboh.emel" $readonlyPopup class="$inputTextClassPopup"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($modePopup != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> #if ($modePopup == 'new')
            <input name="cmdSimpanPenceroboh" type="button" value="Simpan" onClick="simpanPenceroboh()" />
            <input name="cmdBatalPenceroboh" type="button" value="Batal" onClick="batalPenceroboh()" />
            #end
            #if ($modePopup == 'view')
            <input name="cmdKemaskiniPenceroboh" type="button" value="Kemaskini" onClick="kemaskiniPenceroboh()" />
            <input name="cmdHapusPenceroboh" type="button" value="Hapus" onClick="hapusMaklumatPenceroboh()" >
            #end
            #if ($modePopup == 'update')
            <input name="cmdSimpanKemaskiniPenceroboh" type="button" value="Simpan" onClick="simpanKemaskiniPenceroboh()" />
            <input name="txtBatalPenceroboh" type="button" value="Batal" onClick="batalKemaskiniPenceroboh()" />
            #end </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
