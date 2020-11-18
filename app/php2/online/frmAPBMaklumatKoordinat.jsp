<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT TITIK KOORDINAT</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatKoordinat in $BeanMaklumatKoordinat)
        <tr>
          <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
                    <tr>
                      <td width="1%">#if ($mode == 'newKoordinat' ||  $mode == 'updateKoordinat')<span class="style1">*</span>#end</td>
                      <td width="29%">Label Titik</td>
                      <td width="70%">:
                        <input type="text" name="txtLabelTitik" id="txtLabelTitik" size="3" maxlength="3" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();" value="$beanMaklumatKoordinat.labelTitik"></td>
                    </tr>
                    <tr>
                      <td>#if ($mode == 'newKoordinat' ||  $mode == 'updateKoordinat')<span class="style1">*</span>#end</td>
                      <td>Darjah U</td>
                      <td>:
                        <input type="text" name="txtDarjahU" id="txtDarjahU" size="5" maxlength="13" $readonly class="$inputTextClass" value="$beanMaklumatKoordinat.darjahU" onkeyup="validateNumber(this,this.value);">
                        &deg;</td>
                    </tr>
                    <tr>
                      <td>#if ($mode == 'newKoordinat' ||  $mode == 'updateKoordinat')<span class="style1">*</span>#end</td>
                      <td>Minit U</td>
                      <td>:
                        <input type="text" name="txtMinitU" id="txtMinitU" size="5" maxlength="13" $readonly class="$inputTextClass" value="$beanMaklumatKoordinat.minitU" onkeyup="validateNumber(this,this.value);">
                        &prime;</td>
                    </tr>
                    <tr>
                      <td>#if ($mode == 'newKoordinat' ||  $mode == 'updateKoordinat')<!--<span class="style1">*</span>-->#end</td>
                      <td>Saat U</td>
                      <td>:
                        <input type="text" name="txtSaatU" id="txtSaatU" size="5" maxlength="13" $readonly class="$inputTextClass" value="$beanMaklumatKoordinat.saatU" onkeyup="validateNumber(this,this.value);">
&Prime;</td>
                    </tr>
                  </table></td>
                <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
                    <tr>
                      <td width="1%">#if ($mode == 'newKoordinat' ||  $mode == 'updateKoordinat')<span class="style1">*</span>#end</td>
                      <td width="29%">Darjah T</td>
                      <td width="70%">:
                        <input type="text" name="txtDarjahT" id="txtDarjahT" size="5" maxlength="13" $readonly class="$inputTextClass" value="$beanMaklumatKoordinat.darjahT" onkeyup="validateNumber(this,this.value);">
                        &deg;</td>
                    </tr>
                    <tr>
                      <td>#if ($mode == 'newKoordinat' ||  $mode == 'updateKoordinat')<span class="style1">*</span>#end</td>
                      <td>Minit T</td>
                      <td>:
                        <input type="text" name="txtMinitT" id="txtMinitT" size="5" maxlength="13" $readonly class="$inputTextClass" value="$beanMaklumatKoordinat.minitT" onkeyup="validateNumber(this,this.value);">
                        &prime;</td>
                    </tr>
                    <tr>
                      <td>#if ($mode == 'newKoordinat' ||  $mode == 'updateKoordinat')<!--<span class="style1">*</span>-->#end</td>
                      <td>Saat T</td>
                      <td>:
                        <input type="text" name="txtSaatT" id="txtSaatT" size="5" maxlength="13" $readonly class="$inputTextClass" value="$beanMaklumatKoordinat.saatT" onkeyup="validateNumber(this,this.value);">
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
          <td>&nbsp;</td>
        </tr>
        #if ($mode == 'newKoordinat' ||  $mode == 'updateKoordinat')
        <tr>
          <td valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pelan perlu mengikut spesifikasi MAL Chart dan Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
         <tr>
          <td valign="bottom"><i><font color="#ff0000"> </font></i></td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'newKoordinat')
      <input type="button" name="cmdSimpanKoordinat" id="cmdSimpanKoordinat" value="Simpan" onClick="simpanKoordinat()"/>
      <input type="button" name="cmdBatalKoordinat" id="cmdBatalKoordinat" value="Kembali" onClick="batalKoordinat()"/>
      #end
      #if ($mode == 'viewKoordinat')
      <input type="button" name="cmdKemaskiniKoordinat" id="cmdKemaskiniKoordinat" value="Kemaskini" onClick="kemaskiniKoordinat()"/>
      <input type="button" name="cmdHapusKoordinat" id="cmdHapusKoordinat" value="Hapus" onClick="hapusKoordinat()"/>
      <input type="button" name="cmdBatalKoordinat" id="cmdBatalKoordinat" value="Kembali" onClick="batalKoordinat()"/>
      #end
      #if ($mode == 'updateKoordinat')
      <input type="button" name="cmdSimpanKemaskiniKoordinat" id="cmdSimpanKemaskiniKoordinat" value="Simpan" onClick="simpanKemaskiniKoordinat()"/>
      <input type="button" name="cmdBatalKemaskiniKoordinat" id="cmdSimpanKemaskiniKoordinat" value="Kembali" onClick="batalKemaskiniKoordinat()"/>
      #end </td>
  </tr>
</table>
