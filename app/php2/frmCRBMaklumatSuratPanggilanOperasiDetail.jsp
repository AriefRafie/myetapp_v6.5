<style type="text/css">
<!--
.style2 {color: #FF0000}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT SURAT PANGGILAN OPERASI</legend>
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
        #foreach ($beanMaklumatSuratPanggilanOperasi in $BeanMaklumatSuratPanggilanOperasi)
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Hantar</td>
          <td>:</td>
          <td><input name="txtTarikhHantar" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar" onBlur="check_date(this);" value="$beanMaklumatSuratPanggilanOperasi.tarikhHantar" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style2">*</span>#end</td>
          <td>Tarikh Operasi</td>
          <td>:</td>
          <td><input name="txtTarikhOperasi" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhOperasi" onBlur="check_date(this);" value="$beanMaklumatSuratPanggilanOperasi.tarikhOperasi" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhOperasi',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style2">*</span>#end</td>
          <td>Masa Operasi</td>
          <td>:</td>
          <td>
          	<input type="text" name="txtMasaOperasi" id="txtMasaOperasi" size="12" class="$inputTextClassPopup" $readonlyPopup value="$beanMaklumatSuratPanggilanOperasi.masaOperasi" onblur="this.value=this.value.toUpperCase();"/>          
          	 #if ($modePopup != 'view')
          	 	<i><font color="#ff0000"> Contoh : 08:00 AM</font></i>
          	 #end
          </td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style2">*</span>#end</td>
          <td>Lokasi Operasi</td>
          <td>:</td>
          <td>
          	<input type="text" name="txtLokasiOperasi" id="txtLokasiOperasi" class="$inputTextClassPopup" size="50" $readonlyPopup value="$beanMaklumatSuratPanggilanOperasi.lokasiOperasi"/>
          </td>         
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
      <input name="cmdSimpanSuratPanggilanOperasi" type="button" onClick="doSimpanMaklumatSuratPanggilanOperasi()" value="Simpan">
      <input name="cmdBatal" type="button" onClick="doBatalMaklumatSuratPanggilanOperasi()" value="Batal">
      #end
      #if ($modePopup == 'view')
      <input name="cmdKemaskini" type="button" onClick="doKemaskiniMaklumatSuratPanggilanOperasi()" value="Kemaskini">
      <input name="cmdHapus" type="button" onClick="doHapusMaklumatSuratPanggilanOperasi()" value="Hapus">
      <input name="cmdKembali" type="button" onClick="doBatalMaklumatSuratPanggilanOperasi()" value="Kembali">
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpan" type="button" onClick="doSimpanKemaskiniMaklumatSuratPanggilanOperasi()" value="Simpan" />
      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatSuratPanggilanOperasi()" value="Batal">
      #end </td>
  </tr>
</table>
