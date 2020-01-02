<style type="text/css">
<!--
.style2 {color: #FF0000}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT SURAT TINDAKAN PENGUATKUASAAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPejabat in $BeanMaklumatPejabat)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Nama Pejabat</td>
          <td width="1%">:</td>
          <td width="70%">$beanMaklumatPejabat.namaPejabat
          <input type="hidden" name="idPejabat" id="idPejabat" value="$beanMaklumatPejabat.idPejabat"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.alamat1</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.alamat2</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.alamat3</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.poskod</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Bandar</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.bandar</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.negeri</td>
        </tr> 
        #end
        #foreach ($beanMaklumatSuratTindakan in $BeanMaklumatSuratTindakan)
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Hantar</td>
          <td>:</td>
          <td><input name="txtTarikhHantar" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar" onBlur="check_date(this);" value="$beanMaklumatSuratTindakan.tarikhHantar" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($modePopup == 'new')
      <input name="cmdSimpanSuratTindakan" type="button" onClick="doSimpanMaklumatSuratTindakan()" value="Simpan">
      <input name="cmdBatal" type="button" onClick="doBatalMaklumatSuratTindakan()" value="Batal">
      #end
      #if ($modePopup == 'view')
      <input name="cmdKemaskini" type="button" onClick="doKemaskiniMaklumatSuratTindakan()" value="Kemaskini">
      <input name="cmdHapus" type="button" onClick="doHapusMaklumatSuratTindakan()" value="Hapus">
      <input name="cmdKembali" type="button" onClick="doBatalMaklumatSuratTindakan()" value="Kembali">
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpan" type="button" onClick="doSimpanKemaskiniMaklumatSuratTindakan()" value="Simpan" />
      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatSuratTindakan()" value="Batal">
      #end </td>
  </tr>
</table>
