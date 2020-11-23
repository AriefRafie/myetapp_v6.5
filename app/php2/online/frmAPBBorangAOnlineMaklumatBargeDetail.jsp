<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style3 {
	color: #FF0000;
	font-style: italic;
	font-size: x-small;
}
-->
</style>
<!-- saiz text -->

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatBarge in $BeanMaklumatBarge)
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Nama Kapal/Baj Yang Didaftarkan</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNamaDaftar" type="text" class="$inputTextClass" id="txtNamaDaftar" value="$beanMaklumatBarge.namaDidaftarkan" $readonly /></td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>No. Telefon INMARSAT (Satellite Phone)</td>
    <td>:</td>
    <td><input type="text" name="txtNoTel" id="txtNoTel" value="$beanMaklumatBarge.noTel" class="$inputTextClass" $readonly onkeyup="validateNumber(this,this.value);" /></td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Jenis Kapal/Baj</td>
    <td>:</td>
    <td><input type="text" name="txtJenis" id="txtJenis" value="$beanMaklumatBarge.jenis" class="$inputTextClass" $readonly  /></td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>No. Pendaftaran</td>
    <td>:</td>
    <td><input type="text" name="txtNoPendaftaran" id="txtNoPendaftaran" value="$beanMaklumatBarge.noPendaftaran" class="$inputTextClass" $readonly  />    </td>
  </tr>
  
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Kapasiti</td>
    <td valign="top">:</td>
    <td valign="top">
      <input type="text" name="txtKapasiti" id="txtKapasiti" value="$beanMaklumatBarge.kapasiti" class="$inputTextClass" $readonly onkeyup="validateNumber(this,this.value);"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'newBarge')
      <input name="cmdSimpan1" type="button" value="Simpan" onClick="doSimpanMaklumatBarge()" />
      <input name="cmdBatal" type="button" value="Batal" onClick="doBatalMaklumatBarge()" />
      #end
      #if ($mode == 'viewBarge')
      <input name="cmdKemaskini1" type="button" value="Kemaskini" onClick="kemaskiniMaklumatBarge()" >
      <input name="cmdBatal" type="button" value="Kembali" onClick="doBatalMaklumatBarge()" />
      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="doHapusBarge()"/>
      #end      
      #if ($mode == 'updateBarge')
      <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskiniMaklumatBarge()" />
      <input name="txtBatal" type="button" value="Batal" onClick="doBatalMaklumatBarge()" />
      #end </td>
  </tr>
  #end
</table>
