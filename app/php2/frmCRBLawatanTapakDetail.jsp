<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatLaporanTanah in $BeanMaklumatLaporanTanah)
  <tr>
    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Fail Diterima</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtTarikhTerimaFail" id="txtTarikhTerimaFail" size="9" onBlur="check_date(this)" value="$beanMaklumatLaporanTanah.tarikhTerimaFail" $readonly class="$inputTextClass">
      #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhTerimaFail',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Lawatan Tapak</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtTarikhLawatan" id="txtTarikhLawatan" size="9" onBlur="check_date(this)" value="$beanMaklumatLaporanTanah.tarikhLawatan" $readonly class="$inputTextClass">
      #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhLawatan',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Laporan</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtTarikhLaporan" id="txtTarikhLaporan" size="9" onBlur="check_date(this)" value="$beanMaklumatLaporanTanah.tarikhLaporan" $readonly class="$inputTextClass">
      #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhLaporan',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td width="1%" valign="top">#if ($modePopup!= 'view')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">Tujuan Laporan</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><textarea name="txtTujuanLaporan" id="txtTujuanLaporan" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.tujuanLaporan</textarea></td>
  </tr>
  <tr>
    <td width="1%" valign="top">&nbsp;</td>
    <td width="28%" valign="top">Lokasi</td>
    <td width="1%" valign="top">:</td>
    <td valign="top"><textarea name="txtLokasi" id="txtLokasi" rows="5" cols="50" $readonly class="$inputTextClass">$beanMaklumatLaporanTanah.lokasi</textarea>
    </td>
  </tr>

  <tr>
    <td width="1%" valign="top">&nbsp;</td>
    <td width="28%" valign="top">Laporan Semasa Tapak</td>
    <td width="1%" valign="top">:</td>
    <td valign="top"><textarea name="txtLaporanSemasaTapak" id="txtLaporanSemasaTapak" rows="5" cols="50" $readonly class="$inputTextClass">$beanMaklumatLaporanTanah.laporanSemasaTapak</textarea>
    </td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Pandangan / Ulasan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtIsuUlasan" id="txtIsuUlasan" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.ulasan</textarea>
    </td>
  </tr>
  <tr>
    <td colspan="4" valign="bottom"></td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($modePopup == 'new')
      <input name="cmdSimpanSiastan" type="button" value="Simpan" onClick="simpanSiasatanLT()" />
      <input name="cmdBatalLT" id="cmdBatalLT" type="button" value="Batal" onClick="batalLawatanTapak()" />
      #end 
      #if ($modePopup == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniMaklumatSiasatanLT()"/>
      <input name="cmdBatalLT2" id="cmdBatalLT" type="button" value="Kembali" onClick="batalLawatanTapak()" />
      #end
      #if ($modePopup == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniMaklumatSiasatanLT()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalMaklumatSiasatanLT()"/>
      #end </td>
  </tr>
  #end
</table>
