<fieldset>
<legend><strong>KERTAS MAKLUMAN PENAMATAN PERJANJIAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatKertasMakluman in $BeanMaklumatKertasMakluman)
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%" valign="top">Ulasan Penamatan</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><textarea name="txtUlasan" id="txtUlasan" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatKertasMakluman.ulasan</textarea></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%" valign="top">Disediakan Oleh</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><input name="txtSediaOleh" type="text" class="$inputTextClass" id="txtSediaOleh" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatKertasMakluman.sediaOleh" $readonly="$readonly"></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%" valign="top">Jawatan</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top">$selectJawatan</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%" valign="top">Tarikh Disediakan</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="tarikhSedia" id="tarikhSedia" value="$beanMaklumatKertasMakluman.tarikhSedia" onBlur="check_date(this)" size="9" $readonly class="$inputTextClass"/>
      #if($mode != 'view')<a href="javascript:displayDatePicker('tarikhSedia',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>
    #if ($mode == 'view')
    <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskini()"/>
    #end
    #if ($mode == 'update')
    <input name="cmdSimpan" type="button" value="Simpan" onClick="simpanKemaskiniKertasMakluman()"/>
    <input name="cmdBatal" type="button" value="Batal" onClick="batal()"/>
    #end    </td>
  </tr>
</table>
</fieldset>
