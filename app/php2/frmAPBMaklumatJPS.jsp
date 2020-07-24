<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT JPS</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatJPS in $BeanMaklumatJPS)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Kementerian</td>
          <td width="1%">:</td>
          <td width="70%">$beanMaklumatJPS.kementerian</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Agensi</td>
          <td width="1%">:</td>
          <td width="70%">$beanMaklumatJPS.agensi</td>
        </tr>
        #if ($flagStatus == '1')
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Tarikh Hantar</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhHantar" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar" onBlur="check_date(this);calcDate()" value="$beanMaklumatJPS.tarikhHantar" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatJPS.jangkamasa" onBlur="validateNumber(this,this.value,'$beanMaklumatJPS.jangkamasa');calcDate()" $readonlyPopup class="$inputTextClassPopup">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhJangkaTerima" onBlur="check_date(this)" value="$beanMaklumatJPS.tarikhJangkaTerima" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhJangkaTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        #else
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh Hantar</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhHantar" type="text" readonly="readonly" class="disabled" id="txtTarikhHantar" value="$beanMaklumatJPS.tarikhHantar" size="9" maxlength="10"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatJPS.jangkamasa" readonly="readonly" class="disabled">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" readonly="readonly" class="disabled" id="txtTarikhJangkaTerima" value="$beanMaklumatJPS.tarikhJangkaTerima" size="9" maxlength="10"></td>
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
                <td width="70%"><input name="txtTarikhTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhTerima" onBlur="check_date(this)" value="$beanMaklumatJPS.tarikhTerima" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Surat</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhSurat" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhSurat" onBlur="check_date(this)" value="$beanMaklumatJPS.tarikhSurat" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td>No Rujukan Surat</td>
                <td>:</td>
                <td><input type="text" name="txtNoRujukanSurat" id="txtNoRujukanSurat" $readonlyPopup class="$inputTextClassPopup" size="50" value="$beanMaklumatJPS.noRujukan" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Ulasan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtUlasan" id="txtUlasan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup">$beanMaklumatJPS.ulasan</textarea></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Nama Pengulas</td>
                <td>:</td>
                <td><input type="text" name="txtNamaPengulas" id="txtNamaPengulas" $readonlyPopup class="$inputTextClassPopup" size="50" value="$beanMaklumatJPS.namaPengulas" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Telefon Pengulas</td>
                <td>:</td>
                <td><input type="text" name="txtNoTelPengulas" id="txtNoTelPengulas" $readonlyPopup class="$inputTextClassPopup" size="50" value="$beanMaklumatJPS.noTelPengulas" /></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        #if ($modePopup == 'view')
        #set($idDokumen = '')
        #set($namaFail = '')
        #foreach ($beanMaklumatDokumenJPS in $BeanMaklumatDokumenJPS)
        	#set($idDokumen = $beanMaklumatDokumenJPS.idDokumen)
    		#set($namaFail = $beanMaklumatDokumenJPS.namaFail)
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
                <td><input id="fileuploadJPS" name="fileuploadJPS" type="file" size="40" />
                  <input name="cmdSimpan3" type="button" value="Simpan Dokumen" onclick="simpanDokumenJPS('$idUlasanTeknikal','$idPermohonan')" /></td>
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
      <input name="cmdSimpan" type="button" onClick="doSimpanMaklumatJPS()" value="Simpan">
      <input name="cmdBatal" type="button" onClick="doBatalMaklumatJPS()" value="Batal">
      #end
      #if ($modePopup == 'newUlangan')
      <input name="cmdSimpan" type="button" onClick="doSimpanMaklumatUlanganJPS()" value="Simpan">
      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatJPS()" value="Batal">
      #end
      #if ($modePopup == 'view')
      #if ($flagAktif != 'T')
      <input name="cmdKemaskini" type="button" onClick="doKemaskiniMaklumatJPS()" value="Kemaskini">
      <input name="cmdHapus" type="button" onClick="doHapusMaklumatUlasan()" value="Hapus">
      #if ($flagStatus == '1')
      <input name="cmdTerima" type="button" onClick="doTerimaJPS()" value="Terima">
      <input name="cmdUlangan" type="button" onClick="doUlanganJPS()" value="Ulangan">
      #end
      #end
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpan2" type="button" onclick="doSimpanKemaskiniMaklumatJPS()" value="Simpan" />
      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatJPS()" value="Batal">
      #end </td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI NOTIFIKASI EMAIL</b></legend>
      <table align="center" width="100%">
              #if ($flagNotifikasi == 'openNotifikasi')
		  <tr>
		    <td> #parse("app/php2/frmAPBNotifikasiEmailDetail.jsp") </td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
  		#end
        <tr>
          <td colspan="9" scope="row"><input name="cmdTambah" type="button" value="Tambah" onClick="javascript:doDaftarNotifikasi()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="25%"><strong>Nama Pegawai</strong></td>
          ##<td width="35%"><strong>PTD / PTG / KJP / JKPTG</strong></td>
          <td width="15%" align="center"><strong>Emel</strong></td>
          <td width="15%" align="center"><strong>Jawatan</strong></td>
          <td width="10%"><strong>Status</strong></td>
          ##<td width="5%"><strong></strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiNotifikasiEmel.size() > 0)
        #foreach ($list in $SenaraiNotifikasiEmel)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          ##<td class="$row"><a href="javascript:paparMaklumatSuratPenghargaan('$list.idUlasanTeknikal')" class="style2">$list.namaPegawai</a></td>
          <td class="$row">$list.namaPegawai</td>
          ##<td class="$row">$list.namaPejabatPTGPTD</td>
          <td class="$row" align="center">$list.emel</td>
          <td class="$row" align="center">$list.jawatan</td>
          <td class="$row">$list.status</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          ##<td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>