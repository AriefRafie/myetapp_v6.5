<input name="action" type="text" value="$action" />
<input name="mode" type="text" value="$mode" />
  <fieldset>
  <legend><strong>Maklumat Dokumen</strong></legend>
  <table width="100%" border="0" cellspacing="0">
  <tr>
    <td width="29%">No Rujukan Dokumen</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtNoRujDokumen" type="text" id="txtNoRujDokumen" size="55">
    </label></td>
  </tr>
  <tr>
    <td valign="top">Tajuk Dokumen</td>
    <td valign="top">:</td>
    <td><label>
      <textarea name="txtTajukDokumen" cols="52" id="txtTajukDokumen"></textarea>
    </label></td>
  </tr>
  <tr>
    <td>Jenis Dokumen</td>
    <td>:</td>
    <td>$selectJenisDokumen</td>
  </tr>
  <tr>
    <td>Tarikh Dokumen</td>
    <td>:</td>
    <td><label>
      <input type="text" name="txdTarikhDokumen" id="txdTarikhDokumen">
     <a href="javascript:displayDatePicker('txdTarikhDokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></label></td>
  </tr>
  <tr>
    <td>Seksyen / Urusetia</td>
    <td>:</td>
    <td>$selectSeksyen</td>
  </tr>
  <tr>
    <td>No Fail</td>
    <td>:</td>
    <td><input name="txtNamaFail" type="text" id="txtNamaFail" size="50" maxlength="50" /></td>
  </tr>
  <tr>
    <td valign="top">Catatan</td>
    <td valign="">:</td>
    <td><label>
      <textarea name="txtCatatan" cols="52" id="txtCatatan"></textarea>
    </label></td>
  </tr>
  <tr>
    <td>Tarikh Daftar</td>
    <td>:</td>
    <td><label>
      <input type="text" name="txdTarikhDaftar" id="txdTarikhDaftar">
    <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></label></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
    <td><label>
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kembali">
    </label>
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan">
      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal">
      </label>
      <label>
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali">
      </label></td>
  </tr>
</table>
<fieldset><legend><strong>Senarai Lampiran</strong></legend>
<p><strong>
  <label>
  <input type="button" name="cmdTambahLampiran" id="cmdTambahLampiran" value="Tambah">
  </label>
</strong></p>
<table width="100%" border="0" cellspacing="0">
  <tr>
    <td width="1%"><strong>No</strong></td>
    <td width="20%"><strong>Nama Fail</strong></td>
    <td width="20%"><strong>Jenis Fail</strong></td>
    <td width="2%">&nbsp;</td>
    <td width="2%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><label>
      <input type="button" name="cmdPapar" id="cmdPapar" value="Papar">
    </label></td>
    <td><label>
      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus">
    </label></td>
  </tr>
</table>

</fieldset>
  </fieldset>
  

<script>
function doChangeSeksyen() {
 	doAjaxCall${formName}("doChangeSeksyen");
}
</script>