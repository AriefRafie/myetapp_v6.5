<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT PAKAR</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPakar in $BeanMaklumatPakar)
        <tr>
          <td width="1%">#if ($mode == 'newPakar' ||  $mode == 'updatePakar')<span class="style1">*</span>#end</td>
          <td width="28%">Nama</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNamaPakar" type="text" class="$inputTextClass" id="txtNamaPakar" value="$beanMaklumatPakar.nama" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td width="28%" valign="top">Kelayakan</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top"><textarea name="txtKelayakan" rows="5" cols="43" class="$inputTextClass" id="txtKelayakan" onBlur="this.value=this.value.toUpperCase();" $readonly="$readonly">$beanMaklumatPakar.kelayakan</textarea>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($mode == 'newPakar' ||  $mode == 'updatePakar')
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
    <td width="70%"> #if ($mode == 'newPakar')
      <input type="button" name="cmdSimpanPakar" id="cmdSimpanPakar" value="Simpan" onClick="simpanPakar()"/>
      <input type="button" name="cmdBatalPakar" id="cmdBatalPakar" value="Kembali" onClick="batalPakar()"/>
      #end
      #if ($mode == 'viewPakar')
      <input type="button" name="cmdKemaskiniPakar" id="cmdKemaskiniPakar" value="Kemaskini" onClick="kemaskiniPakar()"/>
      <input type="button" name="cmdHapusPakar" id="cmdHapusPakar" value="Hapus" onClick="hapusPakar()"/>
      <input type="button" name="cmdBatalPakar" id="cmdBatalPakar" value="Kembali" onClick="batalPakar()"/>
      #end
      #if ($mode == 'updatePakar')
      <input type="button" name="cmdSimpanKemaskiniPakar" id="cmdSimpanKemaskiniPakar" value="Simpan" onClick="simpanKemaskiniPakar()"/>
      <input type="button" name="cmdBatalKemaskiniPakar" id="cmdSimpanKemaskiniPakar" value="Kembali" onClick="batalKemaskiniPakar()"/>
      #end </td>
  </tr>
</table>
