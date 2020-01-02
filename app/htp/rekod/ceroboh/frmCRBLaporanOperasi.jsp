<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanLaporanOperasi in $BeanLaporanOperasi)
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Mula Operasi</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtTarikhOperasi" id="txtTarikhOperasi" size="9" onBlur="check_date(this);" value="$beanLaporanOperasi.tarikhOperasi" $readonly class="$inputTextClass">
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhOperasi',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Tamat Operasi</td>
    <td>:</td>
    <td><input type="text" name="txtTarikhTamatOperasi" id="txtTarikhTamatOperasi" size="9" onBlur="check_date(this);cekTarikhOperasi(this)" value="$beanLaporanOperasi.tarikhTamatOperasi" $readonly class="$inputTextClass">
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhTamatOperasi',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td valign="top">#if ($mode!= 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Laporan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtLaporan" id="txtLaporan" rows="9" cols="60" $readonly class="$inputTextClass">$beanLaporanOperasi.laporan</textarea></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtCatatanLO" id="txtCatatanLO" rows="5" cols="60" $readonly class="$inputTextClass">$beanLaporanOperasi.catatan</textarea></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Kos Operasi</td>
    <td valign="top">:</td>
    <td>RM
      <input type="text" name="txtKosOperasi" id="txtKosOperasi" value="$beanLaporanOperasi.kosOperasi" $readonly class="$inputTextClass" onBlur="validateCurrency(this,this.value,'$beanLaporanOperasi.kosOperasi');" style="text-align:right" size="15"/>    </td>
  </tr>
  
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Nama Pegawai Pelapor</td>
    <td>:</td>
    <td><input type="text" name="txtNamaPelaporOperasi" id="txtNamaPelaporOperasi" value="$beanLaporanOperasi.namaPegawaiOperasi" size="30"  $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jawatan</td>
    <td>:</td>
    <td><input name="txtJawatanLO" type="text" class="$inputTextClass" id="txtJawatanLO" value="$beanLaporanOperasi.jawatanLO" size="60" $readonly onblur="this.value=this.value.toUpperCase();"/>   </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" valign="bottom"></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'view')
      <!-- 
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniLaporanOperasi()"/>
      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      -->
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniLaporanOperasi()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalLaporanOperasi()"/>
      #end </td>
  </tr>
  #end
</table>
