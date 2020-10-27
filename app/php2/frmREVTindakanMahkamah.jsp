<fieldset>
<legend>MAKLUMAT TINDAKAN MAHKAMAH</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Buka Fail</td>
     <td width="1%">:</td>
     <td width="70%">
     	<input type="text" name="tarikh_daftar_fail" id="tarikh_daftar_fail" value="$tm.tarikh_daftar_fail" size="9" class="disabled" onblur="check_date(this)"/>
     </td>
   </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Mula Penyewaaan</td>
     <td width="1%">:</td>
     <td width="70%">
     	<input type="text" name="tarikh_mula_penyewaan" id="tarikh_mula_penyewaan" value="$tm.tarikh_mula_penyewaan" size="9" class="disabled" onblur="check_date(this)"/>
     </td>
   </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Tamat Penyewaan</td>
     <td width="1%">:</td>
     <td width="70%">
     	<input type="text" name="tarikh_tamat_penyewaan" id="tarikh_tamat_penyewaan" value="$tm.tarikh_tamat_penyewaan" size="9" class="disabled" onblur="check_date(this)"/>
     </td>
   </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Minit Mesyuarat</td>
     <td width="1%">:</td>
     <td width="70%">
     	<input type="text" name="txtTarikh" id="txtTarikh" value="$tm.tarikh_daftar_fail" size="9" class="disabled" onblur="check_date(this)"/>
     </td>
   </tr>
   <tr>
      <td>&nbsp;</td>
      <td>Bil Mesyuarat</td>
      <td>:</td>
      <td><input type="text" name="txtNoRujukan" id="txtNoRujukan" value="$beanMaklumatBayaranD.noRujukan" class="disabled" onBlur="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Surat Tawaran</td>
     <td width="1%">:</td>
     <td width="70%">
     	<input type="text" name="tarikh_surat_tawaran" id="tarikh_surat_tawaran" value="$tm.tarikh_surat_tawaran" size="9" class="disabled" onblur="check_date(this)"/>
     </td>
   </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Penerimaan SST</td>
     <td width="1%">:</td>
     <td width="70%">
     	<input type="text" name="tarikh_penamaan_sst" id="tarikh_penamaan_sst" value="$tm.tarikh_penamaan_sst" $readonly class="$inputTextClass" size="9" onblur="check_date(this)"/>
       #if ($mode == 'update') <a href="javascript:displayDatePicker('tarikh_penamaan_sst',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end</td>
   </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Penerimaan Deposit</td>
     <td width="1%">:</td>
     <td width="70%">
     	<input type="text" name="tarikh_penerimaan_deposit" id="tarikh_penerimaan_deposit" value="$tm.tarikh_penerimaan_deposit" size="9" class="disabled" onblur="check_date(this)"/>
     </td>
   </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Notis Peringatan</td>
     <td width="1%">:</td>
     <td width="70%">
     	<input type="text" name="tarikh_notis_peringatan" id="tarikh_notis_peringatan" value="$tm.tarikh_notis_peringatan" size="9" class="disabled" onblur="check_date(this)"/>
     </td>
   </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Notis Peringatan Terakhir</td>
     <td width="1%">:</td>
     <td width="70%">
     	<input type="text" name="tarikh_notis_peringatan_akhir" id="tarikh_notis_peringatan_akhir" value="$tm.tarikh_notis_peringatan_akhir" size="9" class="disabled" onblur="check_date(this)"/>
     </td>
   </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Notis Penamatan</td>
     <td width="1%">:</td>
     <td width="70%">
        <input type="text" name="tarikh_notis_penamatan" id="tarikh_notis_penamatan" value="$tm.tarikh_notis_penamatan" $readonly class="$inputTextClass" size="9" onblur="check_date(this)"/>
        #if ($mode == 'update') <a href="javascript:displayDatePicker('tarikh_notis_penamatan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end
     </td>
   </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Notis Rampasan</td>
     <td width="1%">:</td>
     <td width="70%">
     	 <input type="text" name="tarikh_notis_rampasan" id="tarikh_notis_rampasan" value="$tm.tarikh_notis_rampasan" $readonly class="$inputTextClass" size="9" onblur="check_date(this)"/>
     	 #if ($mode == 'update') <a href="javascript:displayDatePicker('tarikh_notis_rampasan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end
     </td>
   </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Memo Rampasan Deposit</td>
     <td width="1%">:</td>
     <td width="70%">
     	<input type="text" name="txtTarikh" id="txtTarikh" value="$tm.tarikh_memo_rampasan_deposit" size="9" class="disabled" onblur="check_date(this)"/>
     </td>
   </tr>
  <tr>
     <td width="1%"></td>
     <td width="28%">Tarikh Notis Tuntutan</td>
     <td width="1%">:</td>
     <td width="70%">
     	<input type="text" name="tarikh_notis_tuntutan" id="tarikh_notis_tuntutan" value="$tm.tarikh_notis_tuntutan" $readonly class="$inputTextClass" size="9" onblur="check_date(this)"/>
        #if ($mode == 'update') <a href="javascript:displayDatePicker('tarikh_notis_tuntutan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end
     </td>
   </tr>
   #if ($mode == 'update')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini2" id="cmdSimpanKemaskini2" value="Simpan" onClick="doSimpanKemaskiniTindakanMahkamah()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
      #end
      #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniTindakanMahkamah()"/>
      #end </td>
  </tr>
</table>
</fieldset>