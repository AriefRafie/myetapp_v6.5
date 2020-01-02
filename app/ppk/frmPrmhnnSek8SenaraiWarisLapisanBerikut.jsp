<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>frmPrmhnnSek8SenaraiWarisLapisanBerikut</title>
</head>
<body>
<fieldset>

<fieldset>
<strong>WARIS LAPISAN BERIKUT</strong>
<table align="center" border="0" width="100%">
  <tr> 
    <td width="34%">Nama Waris Yang Sudah Meninggal Dunia</td>
    <td width="30%"><select name="socNamaWarisSudahMati">
        <option>&lt;--Sila Pilih--&gt;</option>
      </select></td>
    <td width="13%">Alamat</td>
    <td width="23%"><input type="text" name="txtAlamat1"></td>
  </tr>
  <tr> 
    <td>No. KP Baru</td>
    <td><input type="text" name="txtNoKPBaru1" size="7" maxlength="6">
      - 
      <input type="text" name="txtNoKPBaru2" size="3" maxlength="2">
      - 
      <input type="text" name="txtNoKPBaru3" size="5" maxlength="4"></td>
    <td>&nbsp;</td>
    <td><input type="text" name="txtAlamat2"></td>
  </tr>
  <tr> 
    <td>No. KP Lama</td>
    <td><input type="text" name="txtNoKPLama" size="9" maxlength="8"></td>
    <td>&nbsp;</td>
    <td><input type="text" name="txtAlamat3"></td>
  </tr>
  <tr> 
    <td>Lain-lain KP</td>
    <td><select name="txtJenisKPLain">
        <option>&lt;--Sila Pilih--&gt;</option>
        <option>Tentera</option>
        <option>Polis</option>
        <option>Pasport</option>
      </select> &nbsp;&nbsp; <input type="text" name="txtNoKPLain" size="9" maxlength="8"></td>
    <td>Poskod</td>
    <td><input type="text" name="txtNamaWaris" size="" maxlength=""></td>
  </tr>
  <tr> 
    <td>Nama Waris</td>
    <td><input type="text" name="txtNamaWaris" size="31" maxlength="30"></td>
    <td>Bandar</td>
    <td><input type="text" name="txtNamaWaris" size="" maxlength=""></td>
  </tr>
  <tr> 
    <td>Jantina</td>
    <td><select name="socJantina">
        <option>&lt;--Sila Pilih--&gt;</option>
        <option value="Lelaki">Lelaki</option>
        <option value="Perempuan">Perempuan</option>
      </select></td>
    <td>Negeri</td>
    <td><input type="text" name="txtNamaWaris" size="" maxlength=""></td>
  </tr>
  <tr> 
    <td>Agama</td>
    <td><select name="socAgama">
        <option>&lt;--Sila Pilih--&gt;</option>
        <option value="Islam">Islam</option>
        <option value="Bukan Islam">Bukan Islam</option>
      </select></td>
    <td>No Telefon</td>
    <td><input type="text" name="txtNamaWaris" size="" maxlength=""></td>
  </tr>
  <tr> 
    <td>Warganegara</td>
    <td><select name="socWarganegara ">
        <option>Warganegara</option>
        <option>Bukan Warganegara</option>
        <option>Pemastautin Tetap</option>
      </select></td>
    <td>No. Telefon Bimbit</td>
    <td><input type="text" name="txtNamaWaris" size="" maxlength=""></td>
  </tr>
  <tr> 
    <td>Tarikh Lahir</td>
    <td><input type="text" name="txdTarikhLahir" size="9" maxlength="8"></td>
    <td>Catatan</td>
    <td><textarea name="txtCatatan"></textarea></td>
  </tr>
  <tr> 
    <td>Surat Beranak</td>
    <td><input type="text" name="txtNoSuratBeranak" size="9" maxlength="8"></td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
  <tr> 
    <td>Umur</td>
    <td><input type="text" name="txtUmur" size="9" maxlength="8"></td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
  <tr> 
    <td>Status Waris</td>
    <td><select name="socStatusWaris ">
        <option>Warganegara</option>
        <option>Bukan Warganegara</option>
        <option>Pemastautin Tetap</option>
      </select></td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
  <tr> 
    <td>Talian Persaudaraan</td>
    <td><select name="socTalianPersaudaraan ">
        <option>Warganegara</option>
        <option>Bukan Warganegara</option>
        <option>Pemastautin Tetap</option>
      </select></td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
  <tr> 
    <td>Sudah Meninggal Dunia</td>
    <td><input type="checkbox" name="sbcSudahMeninggalDunia "></td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
  <tr> 
    <td>Tarikh Mati</td>
    <td><input type="text" name="txdTarikhLahir" size="9" maxlength="8"></td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
  <tr>
    <td>Waktu Kematian</td>
    <td><input type="text" size="6" maxlength="5">&nbsp;&nbsp;<select name="socWaktuKematian">
	<option>Pagi</option>
	<option>Petang</option>
	<option>Malam</option>
	</select></td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
</table>
</fieldset>

<form id="form8" name="form8" method="post" action="">
<input name="cmdPapar" id="cmdPapar" value="Kemaskini" type="submit">
<input name="cmdTambah" id="cmdTambah" value="Tambah" type="submit">
<input name="cmdHapus" id="cmdHapus" value="Simpan" type="submit">
<input name="cmdCetak" id="cmdCetak" value="Lapisan Sebelum" type="submit">
<input name="cmdCetak" id="cmdCetak" value="Lapisan Berikut" type="submit">
<input name="cmdCetak" id="cmdCetak" value="Faraid" type="submit">
<input name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit">
<input name="cmdCetak" id="cmdCetak" value="Batal" type="submit">
<input name="cmdKembali" id="cmdKembali" value="Kembali" type="submit">
</form>

	  </td>
    </tr>
    
  </tbody>
</table>
</fieldset>
</fieldset>
</body>
</html>
