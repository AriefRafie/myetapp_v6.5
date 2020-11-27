<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT JMG</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatJMG in $BeanMaklumatJMG)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Kementerian</td>
          <td width="1%">:</td>
          <td width="70%">$beanMaklumatJMG.kementerian</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Agensi</td>
          <td width="1%">:</td>
          <td width="70%">$beanMaklumatJMG.agensi</td>
        </tr>
        #if ($flagStatus == '1')
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Tarikh Hantar</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhHantar" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar" onBlur="check_date(this);calcDate()" value="$beanMaklumatJMG.tarikhHantar" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatJMG.jangkamasa" onBlur="validateNumber(this,this.value,'$beanMaklumatJMG.jangkamasa');calcDate()" $readonlyPopup class="$inputTextClassPopup">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhJangkaTerima" onBlur="check_date(this)" value="$beanMaklumatJMG.tarikhJangkaTerima" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhJangkaTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        #else
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh Hantar</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhHantar" type="text" readonly="readonly" class="disabled" id="txtTarikhHantar" value="$beanMaklumatJMG.tarikhHantar" size="9" maxlength="10"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatJMG.jangkamasa" readonly="readonly" class="disabled">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" readonly="readonly" class="disabled" id="txtTarikhJangkaTerima" value="$beanMaklumatJMG.tarikhJangkaTerima" size="9" maxlength="10"></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($flagStatus == '2')
        <tr>
          <td colspan="4"><fieldset>
            <legend>MAKLUMBALAS</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Terima</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhTerima" onBlur="check_date(this)" value="$beanMaklumatJMG.tarikhTerima" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Surat</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhSurat" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhSurat" onBlur="check_date(this)" value="$beanMaklumatJMG.tarikhSurat" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td>No Rujukan Surat</td>
                <td>:</td>
                <td><input type="text" name="txtNoRujukanSurat" id="txtNoRujukanSurat" $readonlyPopup class="$inputTextClassPopup" size="50" value="$beanMaklumatJMG.noRujukan" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Ulasan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtUlasan" id="txtUlasan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup">$beanMaklumatJMG.ulasan</textarea></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Nama Pengulas</td>
                <td>:</td>
                <td><input type="text" name="txtNamaPengulas" id="txtNamaPengulas" $readonlyPopup class="$inputTextClassPopup" size="50" value="$beanMaklumatJMG.namaPengulas" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Telefon Pengulas</td>
                <td>:</td>
                <td><input type="text" name="txtNoTelPengulas" id="txtNoTelPengulas" $readonlyPopup class="$inputTextClassPopup" size="50" value="$beanMaklumatJMG.noTelPengulas" /></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        #if ($modePopup == 'view')
        #set($idDokumen = '')
        #set($namaFail = '')
        #foreach ($beanMaklumatDokumenJMG in $BeanMaklumatDokumenJMG)
        	#set($idDokumen = $beanMaklumatDokumenJMG.idDokumen)
    		#set($namaFail = $beanMaklumatDokumenJMG.namaFail)
        #end
        <tr>
          <td colspan="4"><fieldset>
            <legend>LAMPIRAN</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Muat Turun Dokumen</td>
                <td width="1%">:</td>
                <td width="70%">#if ($idDokumen != '') <a href="#" onclick="cetakDokumen($idDokumen)" class="style2">$namaFail</a> &nbsp;&nbsp; #end</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><input id="fileuploadJMG" name="fileuploadJMG" type="file" size="40" />
                  <input name="cmdSimpan3" type="button" value="Simpan Dokumen" onclick="simpanDokumenJMG('$idUlasanTeknikal','$idPermohonan')" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><span class="style4"><i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik fail adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz fail yang lebih kecil.</span></span></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        #end
        #end
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
      <input name="cmdSimpan" type="button" onClick="doSimpanMaklumatJMG()" value="Simpan">
      <input name="cmdBatal" type="button" onClick="doBatalMaklumatJMG()" value="Batal">
      #end
      #if ($modePopup == 'newUlangan')
      <input name="cmdSimpan" type="button" onClick="doSimpanMaklumatUlanganJMG()" value="Simpan">
      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatJMG()" value="Batal">
      #end
      #if ($modePopup == 'view')
      #if ($flagAktif != 'T')
      <input name="cmdKemaskini" type="button" onClick="doKemaskiniMaklumatJMG()" value="Kemaskini">
      <input name="cmdHapus" type="button" onClick="doHapusMaklumatUlasan()" value="Hapus">
      #if ($flagStatus == '1')
      <input name="cmdTerima" type="button" onClick="doTerimaJMG()" value="Terima">
      <input name="cmdUlangan" type="button" onClick="doUlanganJMG()" value="Ulangan">
      #end
      #end
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpan2" type="button" onclick="doSimpanKemaskiniMaklumatJMG()" value="Simpan" />
      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatJMG()" value="Batal">
      #end </td>
  </tr>
</table>