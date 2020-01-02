<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatLaporanOperasi in $BeanMaklumatLaporanOperasi)
  <tr>
    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Mula Operasi</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtTarikhOperasi" id="txtTarikhOperasi" size="9" onBlur="check_date(this);" value="$beanMaklumatLaporanOperasi.tarikhOperasi" $readonly class="$inputTextClass">
      #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhOperasi',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Tamat Operasi</td>
    <td>:</td>
    <td><input type="text" name="txtTarikhTamatOperasi" id="txtTarikhTamatOperasi" size="9" onBlur="check_date(this);cekTarikhOperasi(this)" value="$beanMaklumatLaporanOperasi.tarikhTamatOperasi" $readonly class="$inputTextClass">
      #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhTamatOperasi',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td valign="top">#if ($modePopup!= 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Waktu Mula Operasi</td>
    <td valign="top">:</td>
    <td valign="top"><input type="text" name="txtMasaDari" id="txtMasaDari" size="12" $readonly class="$inputTextClass" value="$beanMaklumatLaporanOperasi.masaDari" onblur="this.value=this.value.toUpperCase();"/></td>
  </tr>
    <tr>
    <td valign="top">#if ($modePopup!= 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Waktu Tamat Operasi</td>
    <td valign="top">:</td>
    <td valign="top"><input type="text" name="txtMasaHingga" id="txtMasaHingga" size="12" $readonly class="$inputTextClass" value="$beanMaklumatLaporanOperasi.masaHingga" onblur="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td valign="top">#if ($modePopup!= 'view')<span class="style1">*</span>#end</td>
    <td>Tempat</td>
    <td>:</td>
    <td><input name="txtTempat" type="text" class="$inputTextClass" id="txtTempat" value="$beanMaklumatLaporanOperasi.tempat" size="60" $readonly/>   </td>
  </tr>
   <tr>
    <td valign="top">#if ($modePopup!= 'view')<span class="style1">*</span>#end</td>
    <td>Penceroboh</td>
    <td>:</td>
    <td><input name="txtPenceroboh" type="text" class="$inputTextClass" id="txtPenceroboh" value="$beanMaklumatLaporanOperasi.penceroboh" size="60" $readonly onblur="this.value=this.value.toUpperCase();"/>   </td>
  </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
  	<td colspan="4"><fieldset>
      <legend><strong>RINGKASAN LAPORAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
		  <tr>
		    <td valign="top">#if ($modePopup!= 'view')<span class="style1">*</span>#end</td>
		    <td valign="top">Pendahuluan</td>
		    <td valign="top">:</td>
		    <td valign="top"><textarea name="txtLaporan" id="txtLaporan" rows="10" cols="60" $readonly class="$inputTextClass">$beanMaklumatLaporanOperasi.laporan</textarea></td>
		  </tr>
		  <tr>
		    <td valign="top">#if ($modePopup!= 'view')<span class="style1">*</span>#end</td>
		    <td valign="top">Semasa Operasi</td>
		    <td valign="top">:</td>
		    <td valign="top"><textarea name="txtLaporanSemasa" id="txtLaporanSemasa" rows="30" cols="60" $readonly class="$inputTextClass">$beanMaklumatLaporanOperasi.semasaOperasi</textarea></td>
		  </tr>
		  <tr>
		    <td valign="top">#if ($modePopup!= 'view')<span class="style1">*</span>#end</td>
		    <td valign="top">Selepas Operasi</td>
		    <td valign="top">:</td>
		    <td valign="top"><textarea name="txtLaporanSelepas" id="txtLaporanSelepas" rows="10" cols="60" $readonly class="$inputTextClass">$beanMaklumatLaporanOperasi.selepasOperasi</textarea></td>
		  </tr>
		  <tr>
		    <td valign="top">&nbsp;</td>
		    <td valign="top">Kos Operasi</td>
		    <td valign="top">:</td>
		    <td>RM
		      <input type="text" name="txtKosOperasi" id="txtKosOperasi" value="$beanMaklumatLaporanOperasi.kosOperasi" $readonly class="$inputTextClass" onBlur="validateCurrency(this,this.value,'$beanMaklumatLaporanOperasi.kosOperasi');" style="text-align:right" size="15"/>    </td>
		  </tr>
		  <tr>
		    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
		    <td>Nama Pegawai Pelapor</td>
		    <td>:</td>
		    <td><input type="text" name="txtNamaPelaporOperasi" id="txtNamaPelaporOperasi" value="$beanMaklumatLaporanOperasi.namaPegawaiOperasi" size="30"  $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>Jawatan</td>
		    <td>:</td>
		    <td><input name="txtJawatanLO" type="text" class="$inputTextClass" id="txtJawatanLO" value="$beanMaklumatLaporanOperasi.jawatanLO" size="60" $readonly onblur="this.value=this.value.toUpperCase();"/>   </td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
  		</table></fieldset>
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
      <input name="cmdSimpanLaporanOperasi" type="button" value="Simpan" onClick="simpanLO()" />
      <input name="cmdBatalLO" id="cmdBatalLO" type="button" value="Batal" onClick="batalLO()" />
      #end
      #if ($modePopup == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniLO()"/>
      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      #end
      #if ($modePopup == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniLO()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalKemaskiniLO()"/>
      #end </td>
  </tr>
  #end
</table>
