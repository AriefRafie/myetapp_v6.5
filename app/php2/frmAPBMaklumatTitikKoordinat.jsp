<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatKoordinat in $BeanMaklumatKoordinat)
  <tr>
    <td colspan="4"><table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
            <tr>
              <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
              <td width="29%">Label Titik</td>
              <td width="70%">:
                <input type="text" name="txtNoTitik" id="txtNoTitik" size="3" maxlength="3" $readonlyPopup class="$inputTextClassPopup"  value="$beanMaklumatKoordinat.txtNoTitik" /></td>
            </tr>
            <tr>
              <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
              <td>Darjah U</td>
              <td>:
                <input type="text" name="txtDarjahU" id="txtDarjahU" size="5" maxlength="13" $readonlyPopup class="$inputTextClassPopup" value="$beanMaklumatKoordinat.txtDarjahU" onkeyup="validateNumber(this,this.value);" />
                &deg;</td>
            </tr>
            <tr>
              <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
              <td>Minit U</td>
              <td>:
                <input type="text" name="txtMinitU" id="txtMinitU" size="5" maxlength="13" $readonlyPopup class="$inputTextClassPopup" value="$beanMaklumatKoordinat.txtMinitU" onkeyup="validateNumber(this,this.value);" />
                &prime;</td>
            </tr>
            <tr>
              <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
              <td>Saat U</td>
              <td>:
                <input type="text" name="txtSaatU" id="txtSaatU" size="5" maxlength="13" $readonlyPopup class="$inputTextClassPopup" value="$beanMaklumatKoordinat.txtSaatU" onkeyup="validateNumber(this,this.value);" />
                &Prime;</td>
            </tr>
        </table></td>
        <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
            <tr>
              <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
              <td width="29%">Darjah T</td>
              <td width="70%">:
                <input type="text" name="txtDarjahT" id="txtDarjahT" size="5" maxlength="13" $readonlyPopup class="$inputTextClassPopup" value="$beanMaklumatKoordinat.txtDarjahT" onkeyup="validateNumber(this,this.value);" />
                &deg;</td>
            </tr>
            <tr>
              <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
              <td>Minit T</td>
              <td>:
                <input type="text" name="txtMinitT" id="txtMinitT" size="5" maxlength="13" $readonlyPopup class="$inputTextClassPopup" value="$beanMaklumatKoordinat.txtMinitT" onkeyup="validateNumber(this,this.value);" />
                &prime;</td>
            </tr>
            <tr>
              <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
              <td>Saat T</td>
              <td>:
                <input type="text" name="txtSaatT" id="txtSaatT" size="5" maxlength="13" $readonlyPopup class="$inputTextClassPopup" value="$beanMaklumatKoordinat.txtSaatT" onkeyup="validateNumber(this,this.value);" />
                &Prime;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"> 
      #if ($modePopup == 'new')
      <input name="cmdSimpan" type="button" onclick="doSimpanMaklumatKoordinat()" value="Simpan" />
      <input name="cmdBatal" type="button" onclick="doBatal()" value="Batal" />
      #end
      #if ($modePopup == 'view')
      <input name="cmdKemaskini" type="button" onclick="doKemaskiniMaklumatKoordinat()" value="Kemaskini" />
      <input name="cmdHapus" type="button" onclick="doHapusMaklumatKoordinat()" value="Hapus" />
      <input name="cmdBatal" type="button" onclick="doBatal()" value="Kembali" />
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpan2" type="button" onclick="doSimpanKemaskiniMaklumatKoordinat()" value="Simpan" />
      <input name="cmdBatal" type="button" onclick="doBatal()" value="Batal" />
      #end </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #end
</table>
