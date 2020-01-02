<!-- saiz text -->

#set($saizTxtUlasanTerperinci="1500")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatKJPKJT in $BeanMaklumatKJPKJT)
  <tr>
    <td width="1%" valign="top">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">Ulasan Teknikal</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><textarea name="txtUlasanTeknikal" id="txtUlasanTeknikal" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup"   >$beanMaklumatKJPKJT.ulasanTeknikal</textarea></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Nama Pemohon Bertindih</td>
    <td>:</td>
    <td><input name="txtNamaPemohonBertindih" type="text" class="$inputTextClassPopup" id="txtNamaPemohonBertindih" value="$beanMaklumatKJPKJT.namaPemohonBertindih" size="43" maxlength="80" $readonlyPopup></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Mohon Yang Bertindih</td>
    <td>:</td>
    <td><input type="text" name="txtTarikhMohonBertindih" id="txtTarikhMohonBertindih" size="9" onBlur="check_date(this)" value="$beanMaklumatKJPKJT.tarikhMohonBertindih" $readonlyPopup class="$inputTextClassPopup">
      #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhMohonBertindih',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup"   >$beanMaklumatKJPKJT.catatan</textarea></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Nama Pengulas</td>
    <td>:</td>
    <td><input name="txtNamaPegawai" type="text" class="$inputTextClassPopup" id="txtNamaPegawai" value="$beanMaklumatKJPKJT.namaPegawai" size="43" maxlength="80" $readonlyPopup></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Nombor Telefon Pengulas</td>
    <td>:</td>
    <td><input name="txtNoTelPegawai" type="text" class="$inputTextClassPopup" id="txtNoTelPegawai" value="$beanMaklumatKJPKJT.noTelPegawai" size="43" maxlength="80" $readonlyPopup></td>
  </tr>
  #end
</table>
