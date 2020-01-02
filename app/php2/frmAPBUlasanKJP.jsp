<!-- saiz text -->
#set($saizTxtUlasanTerperinci="1500")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatKJPKJT in $BeanMaklumatKJPKJT)
  <tr>
    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Terima</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtTarikhTerima" id="txtTarikhTerima" size="9" onBlur="check_date(this)" value="$beanMaklumatKJPKJT.tarikhTerima" $readonlyPopup class="$inputTextClassPopup">
      #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Surat Agensi</td>
    <td>:</td>
    <td><input type="text" name="txtTarikhSuratAgensi" id="txtTarikhSuratAgensi" size="9" onBlur="check_date(this)" value="$beanMaklumatKJPKJT.tarikhSurat" $readonlyPopup class="$inputTextClassPopup">
      #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhSuratAgensi',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>No. Fail Agensi</td>
    <td>:</td>
    <td><input name="txtNoFailAgensi" type="text" class="$inputTextClassPopup" id="txtNoFailAgensi" value="$beanMaklumatKJPKJT.noRujukan" maxlength="50" $readonlyPopup size="45'"></td>
  </tr>
  <tr>
    <td valign="top">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Ulasan Terperinci</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtUlasanTerperinci" id="txtUlasanTerperinci" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup"  onKeyUp="textCounter(this.form.txtUlasanTerperinci,this.form.remLen11,$!saizTxtUlasanTerperinci);" onKeyDown="textCounter(this.form.txtUlasanTerperinci,this.form.remLen11,$!saizTxtUlasanTerperinci);" >$beanMaklumatKJPKJT.ulasan</textarea></td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">Baki Aksara :&nbsp;
        <input type="text" readonly="readonly" class="disabled" name="remLen11" size="3" maxlength="3" value="$!saizTxtUlasanTerperinci" /></td>
  </tr>
  #end
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Nama Pegawai</td>
    <td>:</td>
    <td><input name="txtNamaPegawai" type="text" class="$inputTextClassPopup" id="txtNamaPegawai" value="$beanMaklumatKJPKJT.namaPegawai" size="43" maxlength="80" $readonlyPopup></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jawatan</td>
    <td>:</td>
    <td>$selectJawatan</td>
  </tr>
  #end
</table>
