<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT PROJEK</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatProjek in $BeanMaklumatProjek)
        <tr>
          <td width="1%">#if ($mode == 'newProjek' ||  $mode == 'updateProjek')<span class="style1">*</span>#end</td>
          <td width="28%">Nama</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNamaProjek" type="text" class="$inputTextClass" id="txtNamaProjek" value="$beanMaklumatProjek.namaProjek" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($mode == 'newProjek' ||  $mode == 'updateProjek')
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
    <td width="70%"> #if ($mode == 'newProjek')
      <input type="button" name="cmdSimpanProjek" id="cmdSimpanProjek" value="Simpan" onClick="simpanProjek()"/>
      <input type="button" name="cmdBatalProjek" id="cmdBatalProjek" value="Kembali" onClick="batalProjek()"/>
      #end
      #if ($mode == 'viewProjek')
      <input type="button" name="cmdKemaskiniProjek" id="cmdKemaskiniProjek" value="Kemaskini" onClick="kemaskiniProjek()"/>
      <input type="button" name="cmdHapusProjek" id="cmdHapusProjek" value="Hapus" onClick="hapusProjek()"/>
      <input type="button" name="cmdBatalProjek" id="cmdBatalProjek" value="Kembali" onClick="batalProjek()"/>
      #end
      #if ($mode == 'updateProjek')
      <input type="button" name="cmdSimpanKemaskiniProjek" id="cmdSimpanKemaskiniProjek" value="Simpan" onClick="simpanKemaskiniProjek()"/>
      <input type="button" name="cmdBatalKemaskiniProjek" id="cmdSimpanKemaskiniProjek" value="Kembali" onClick="batalKemaskiniProjek()"/>
      #end </td>
  </tr>
</table>
