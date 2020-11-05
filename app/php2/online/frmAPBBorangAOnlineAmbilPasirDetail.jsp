<table width="100%" border="0" cellspacing="2" cellpadding="2">
#foreach ($beanMaklumatAmbilPasir in $BeanMaklumatAmbilPasir)
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Bulan</td>
    <td>:</td>
    <td>$selectBulan</td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tahun</td>
    <td>:</td>
    <td><input name="txtTahun" type="text" class="$inputTextClass" id="txtTahun" value="$beanMaklumatAmbilPasir.tahun" size="10"  $readonly onkeyup="validateNumber(this,this.value);"></td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="35%">Anggaran Jumlah Pasir Yang Akan Dikeluarkan</td>
    <td width="1%">:</td>
    <td width="63%"><input name="txtAnggaranPasir" type="text" class="$inputTextClass" id="txtAnggaranPasir" value="$beanMaklumatAmbilPasir.jumlahPasir" $readonly onkeyup="validateNumber(this,this.value);"> 
      meter padu</td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Kontraktor yang Mengambil Pasir</td>
    <td>:</td>
    <td><input type="text" value="$beanMaklumatAmbilPasir.kontraktor" name="txtKontraktor" id="txtKontraktor" style="width:300px" $readonly class="$inputTextClass" ></td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tujuan Pengambilan</td>
    <td>:</td>
    <td><textarea name="txtTujuanAmbil" id="txtTujuanAmbil" cols="45" rows="5" $readonly class="$inputTextClass" >$beanMaklumatAmbilPasir.tujuanAmbil</textarea></td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Destinasi Pasir Dihantar</td>
    <td>:</td>
    <td><textarea name="txtDestinasiDihantar" id="txtDestinasiDihantar" cols="45" rows="5" $readonly class="$inputTextClass"  >$beanMaklumatAmbilPasir.destinasiHantar</textarea></td>
  </tr>
   <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Nama Syarikat Pembeli</td>
    <td>:</td>
    <td><input type="text" value="$beanMaklumatAmbilPasir.pembeli" name="txtPembeli" id="txtPembeli" style="width:300px"  $readonly class="$inputTextClass" ></td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Mula Operasi</td>
    <td>:</td>
    <td><input type="text" name="txtTarikhMula" id="txtTarikhMula" value="$beanMaklumatAmbilPasir.tarikhMula" size="11"  $readonly class="$inputTextClass"/>
    #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td></td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Tamat Operasi</td>
    <td>:</td>
    <td><input type="text" name="txtTarikhTamat" id="txtTarikhTamat" value="$beanMaklumatAmbilPasir.tarikhTamat" onblur="check_date(this);cekTarikh(this)" size="11" $readonly class="$inputTextClass"/>#if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td></td></td>
  </tr>
    </tr>
   <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Laluan Vessel</td>
    <td>:</td>
    <td><input type="text" value="$beanMaklumatAmbilPasir.laluan" name="txtLaluan" id="txtLaluan" style="width:300px" $readonly class="$inputTextClass" ></td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Kaedah Pengambilan Pasir yang digunakan</td>
    <td>:</td>
    <td><input type="text" value="$beanMaklumatAmbilPasir.kaedah" name="txtKaedah" id="txtKaedah" style="width:300px" $readonly class="$inputTextClass" ></td>
  </tr>
    </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Kawasan Pelupusan Bahan Buangan Korek (spoil) seperti yang dipersetujui oleh Jabatan laut Malaysia (JLM) dan diluluskan dalam Laporan Enviromental Assessement (EA) daripada Jabatan Alam Sekitar (JAS)</td>
    <td>:</td>
    <td><input type="text" value="$beanMaklumatAmbilPasir.kawasan" name="txtKawasan" id="txtKawasan" style="width:300px" $readonly class="$inputTextClass" ></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Anggaran Jumlah Royalti </td>
    <td valign="top">:</td>
    <td valign="top">RM 
    <input type="text" name="txtJumlahRoyalti" id="txtJumlahRoyalti" onblur="validateCurrency(this,this.value,'$beanMaklumatAmbilPasir.jumlahRoyalti');" value="$beanMaklumatAmbilPasir.jumlahRoyalti" $readonly class="$inputTextClass"/></td>
  </tr>
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
    <td>#if ($mode == 'new')
      <input name="cmdSimpan" type="button" onClick="doSimpanMaklumatPasir()" value="Simpan" />
      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatPasir()" value="Batal" />
	  #end
      #if ($mode == 'view')
		<input name="cmdKemaskini" type="button" onClick="doKemaskiniMaklumatPasir()" value="Kemaskini" />
		<input name="cmdKembali" type="button" onClick="doKembali()" value="Kembali" />
		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />
      #end
      #if ($mode == 'update')
		<input name="cmdSimpan2" type="button" onclick="doSimpanKemasMaklumatPasir()" value="Simpan" />
		<input name="cmdBatal2" type="button" onClick="doBatalKemaskiniMaklumatPasir()" value="Batal" />
	  #end </td>
  </tr>
  #end
</table>


