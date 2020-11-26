<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #set ($beanMaklumatTanahGanti = "")
  #foreach ($beanMaklumatTanahGanti in $BeanMaklumatTanahGanti)
   <tr>
     <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
     <td width="28%">Jenis Tanah</td>
     <td width="1%">:</td>
     <td width="70%">
     	<select name="socJenisTanah" id="socJenisTanah" onchange="doChangeJenisTanah()" $readonlyPopup class="$inputTextClassPopup">
         <option $selected value="0">SILA PILIH</option>
         <option $selected1 value="1">TANAH MILIK PERSEKUTUAN</option>
         <option $selected2 value="2">TANAH RIZAB PERSEKUTUAN</option>
       </select>
     </td>
   </tr>
   #if ($idJenisTanah == '1')
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>No. Hakmilik</td>
    <td>:</td>
    <td><input name="txtNoHakmilikTG" type="text" class="$inputTextClassPopup" id="txtNoHakmilikTG" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatTanahGanti.noHakmilik" maxlength="21" $readonlyPopup/></td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Nama Pemilik</td>
    <td>:</td>
    <td><input name="txtNamaPemilikTG" type="text" class="$inputTextClassPopup" id="txtNamaPemilikTG" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatTanahGanti.namaPemilik" maxlength="21" $readonlyPopup/></td>
  </tr>
  #end
  #if ($idJenisTanah == '2')
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>No. Warta</td>
    <td>:</td>
    <td><input name="txtNoWartaTG" type="text" class="$inputTextClassPopup" id="txtNoWartaTG" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatTanahGanti.noWarta" maxlength="21" $readonlyPopup/></td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Pegawai Pengawal</td>
    <td>:</td>
    <td><input name="txtPegawaiPengawalTG" type="text" class="$inputTextClassPopup" id="txtPegawaiPengawalTG" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatTanahGanti.pegawaiPengawal" maxlength="21" $readonlyPopup/></td>
  </tr>
  #end
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>No. PT/Lot</td>
    <td>:</td>
    <td><input name="txtNoLotTG" type="text" class="$inputTextClassPopup" id="txtNoLotTG" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatTanahGanti.noLot" maxlength="20" $readonlyPopup/></td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Daerah</td>
    <td>:</td>
    <td>$selectDaerahTG</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Bandar/Pekan/Mukim</td>
    <td>:</td>
    <td>$selectMukimTG</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Luas Tanah Ganti</td>
    <td>:</td>
    <td>
      <input type="text" name="txtLuas1TG" id="txtLuas1TG" value="$beanMaklumatTanahGanti.luas1" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');" size="6" $readonlyPopup class="$inputTextClassPopup"/ >
     </td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Nilai Pasaran Tanah Ganti</td>
    <td>:</td>
    <td>
      <input type="text" name="txtNilaiPasaranTG" id="txtNilaiPasaranTG" value="$beanMaklumatTanahGanti.nilaiPasaran" size="6" $readonlyPopup class="$inputTextClassPopup"/ >
     </td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($modePopup == 'new')
      <input name="cmdSimpan" type="button" value="Simpan" onClick="doSimpanTanahGanti()" />
      <input name="cmdBatal" type="button" value="Batal" onClick="doBatalTanahGanti()" />
      #end
      #if ($modePopup == 'view')
      <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="doKemaskiniTanahGanti()" />
      <input name="cmdHapus" type="button" value="Hapus" onClick="doHapusTanahGanti()" />
      <input name="cmdBatal" type="button" value="Kembali" onClick="doBatalTanahGanti()" />
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="doSimpanKemaskiniTanahGanti()" />
      <input name="txtBatal" type="button" value="Batal" onClick="doBatalKemaskiniTanahGanti()" />
      #end </td>
  </tr>
  #end
</table>
