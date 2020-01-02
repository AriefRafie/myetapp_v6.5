<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
.style1 {color: #000000; }
-->
</style>
#if ($sendNilaianHAK == 'true')
<fieldset>
	#if ($isJPPHUser == 'true')
  <div class="success">Nilaian harta telah berjaya dihantar ke JKPTG.</div>
    #else
  <div class="success">Permohonan bagi mendapatkan nilaian telah berjaya dihantar ke JPPH.</div>
  	#end
</fieldset>
<br />
#elseif ($saveNilaianHAK == 'true')
<fieldset>
	#if ($isJPPHUser == 'true')
  <div class="success">Maklumat nilaian harta telah berjaya disimpan.</div>
	#end
</fieldset>
#end
#if ($isJPPHUser == 'true')
    #set ($READONLY_JPPH = '')
    #set ($DISABLE_JPPH = '')
	#if ($sendNilaianHAK == 'true')
		#set ($READONLY_JPPH = 'readonly="readonly" class="disabled"')
	    #set ($DISABLE_JPPH = 'disabled="disabled"')
    #end
    #set ($READONLY_JKPTG = 'readonly="readonly" class="disabled"')
    #set ($DISABLE_JKPTG = 'disabled="disabled"')
#else
	#set ($READONLY_JPPH = 'readonly="readonly" class="disabled"')
    #set ($DISABLE_JPPH = 'disabled="disabled"')
    #if ($mode == 'edit')
        #set ($READONLY_JKPTG = '')
        #set ($DISABLE_JKPTG = '')
    #else
        #set ($READONLY_JKPTG = 'readonly="readonly" class="disabled"')
        #set ($DISABLE_JKPTG = 'disabled="disabled" class="disabled"')
    #end
#end
<fieldset>
  <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
  <br />
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top"><strong>NO FAIL</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top" class="link">$MP_NOFAIL</td>
      <td width="15%" align="right" valign="top"><strong>TARIKH MOHON</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top" class="link">$MP_TARIKHMOHON</td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top"><strong>NEGERI</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top" class="link">$MP_NEGERI</td>
      <td align="right" valign="top"><strong>NAMA PEMOHON</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="link">$MP_NAMAPEMOHON</td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top"><strong>DAERAH / JAJAHAN</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top" class="link">$MP_DAERAH</td>
      <td align="right" valign="top"><strong>NO KP PEMOHON</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="link">$MP_NOKPPEMOHON</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>UNIT</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="link">$MP_UNIT</td>
      <td align="right" valign="top"><strong>ALAMAT PEMOHON</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="link">$MP_ALAMATPEMOHON</td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top" class="style1">&nbsp;</td>
      <td align="right" valign="top"><strong>NO TEL PEMOHON</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="link">$MP_NOTELPEMOHON</td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top" class="link">&nbsp;</td>
      <td align="right" valign="top"><strong>NAMA SIMATI</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="link">$MP_NAMASIMATI</td>
    </tr>
    
    <tr>
      <td width="15%" align="right" valign="top">&nbsp;</td>
      <td width="1%" align="center" valign="top">&nbsp;</td>
      <td width="34%" align="left" valign="top" class="link">&nbsp;</td>
      <td align="right" valign="top"><strong>NO KP SIMATI</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="link">$MP_NOKPSIMATI</td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top">&nbsp;</td>
      <td width="1%" align="center" valign="top">&nbsp;</td>
      <td width="34%" align="left" valign="top" class="link">&nbsp;</td>
      <td align="right" valign="top"><strong>TARIKH MATI</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="link">$MP_TARIKHMATI</td>
    </tr>
    <tr>
      <td colspan="6" align="right" valign="top">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>HARTA ALIH KENDERAAN</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td align="right" valign="top"><strong>LOKASI KENDERAAN #if ($isJPPHUser != 'true')<span class="mandatori">*</span>#end</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1"><input name="ALAMAT1_LOKASI" type="text" id="ALAMAT1_LOKASI" style="text-transform:uppercase" value="$!ALAMAT1_LOKASI" size="30" maxlength="255" $READONLY_JKPTG /></td>
      <td width="15%" align="right" valign="top"><strong>NO ENJIN</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top"><input name="NO_ENJIN" type="text" id="NO_ENJIN" style="text-transform:uppercase" onkeyup="this.value=this.value.replace(' ', '');" value="$!NO_ENJIN" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top" class="link"><input name="ALAMAT2_LOKASI" type="text" id="ALAMAT2_LOKASI" style="text-transform:uppercase" value="$!ALAMAT2_LOKASI" size="30" maxlength="255" $READONLY_JKPTG /></td>
      <td width="15%" align="right" valign="top"><strong>NO CASIS</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top"><input name="NO_CASIS" type="text" id="NO_CASIS" style="text-transform:uppercase" onkeyup="this.value=this.value.replace(' ', '');" value="$!NO_CASIS" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top">&nbsp;</td>
      <td width="1%" align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top" class="link"><input name="ALAMAT3_LOKASI" type="text" id="ALAMAT3_LOKASI" style="text-transform:uppercase" value="$!ALAMAT3_LOKASI" size="30" maxlength="255" $READONLY_JKPTG /></td>
      <td width="15%" align="right" valign="top"><strong>BAHAN BAKAR</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top">$selectBahanBakar</td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top">&nbsp;</td>
      <td width="1%" align="center" valign="top">&nbsp;</td>
      <td width="34%" align="left" valign="top" class="link"><span class="style1">$selectLokasiKenderaanNegeri</span></td>
      <td width="15%" align="right" valign="top"><strong>ALAMAT HARTA</strong></td>
      <td width="1%" align="center" valign="top"><strong>:</strong></td>
      <td width="34%" align="left" valign="top"><input name="ALAMAT1_HARTA" type="text" id="ALAMAT1_HARTA" style="text-transform:uppercase" value="$!ALAMAT1_HARTA" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top">&nbsp;</td>
      <td width="1%" align="center" valign="top">&nbsp;</td>
      <td width="34%" align="left" valign="top" class="link"><span class="style1">$selectLokasiKenderaanBandar</span></td>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top"><input name="ALAMAT2_HARTA" type="text" id="ALAMAT2_HARTA" style="text-transform:uppercase" value="$!ALAMAT2_HARTA" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top"><strong>NO PENDAFTARAN #if ($isJPPHUser != 'true')<span class="mandatori">*</span>#end</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top"><input name="NO_PENDAFTARAN" type="text" id="NO_PENDAFTARAN" style="text-transform:uppercase" onkeyup="this.value=this.value.replace(' ', '');" value="$!NO_PENDAFTARAN" size="20" maxlength="20" $READONLY_JKPTG /></td>
      <td width="15%" align="right" valign="top">&nbsp;</td>
      <td width="1%" align="center" valign="top">&nbsp;</td>
      <td width="34%" align="left" valign="top"><input name="ALAMAT3_HARTA" type="text" id="ALAMAT3_HARTA" style="text-transform:uppercase" value="$!ALAMAT3_HARTA" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top"><strong>JENIS BADAN</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top">$selectJenisBadan</td>
      <td rowspan="3" align="right" valign="top"><strong>CATATAN</strong></td>
      <td rowspan="3" align="center" valign="top">:</td>
      <td width="34%" rowspan="3" align="left" valign="top"><textarea name="CATATAN" cols="30" rows="4" id="CATATAN" style="text-transform:uppercase" $READONLY_JKPTG>$!CATATAN</textarea></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top"><strong>BUATAN</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top">$selectBuatan</td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top"><strong>NAMA MODEL</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top"><input name="NAMA_MODEL" type="text" id="NAMA_MODEL" style="text-transform:uppercase" value="$!NAMA_MODEL" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top"><strong>KEUPAYAAN ENJIN (CC)</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top"><input name="KEUPAYAAN_ENJIN" type="text" id="KEUPAYAAN_ENJIN" style="text-transform:uppercase" value="$!KEUPAYAAN_ENJIN" size="30" maxlength="255" $READONLY_JKPTG /></td>
      <td align="right" valign="top"><strong>NAMA PEGAWAI JKPTG #if ($isJPPHUser != 'true')<span class="mandatori">*</span>#end</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="NAMA_PEGAWAI_JKPTG" type="text" id="NAMA_PEGAWAI_JKPTG" style="text-transform:uppercase" value="$!NAMA_PEGAWAI_JKPTG" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>TAHUN DIBUAT</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="TAHUN_DIBUAT" type="text" id="TAHUN_DIBUAT" style="text-transform:uppercase" value="$!TAHUN_DIBUAT" size="10" maxlength="4" $READONLY_JKPTG /></td>
      <td align="right" valign="top"><strong>NO TEL PEGAWAI JKPTG #if ($isJPPHUser != 'true')<span class="mandatori">*</span>#end</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="NO_TEL_PEGAWAI_JKPTG" type="text" id="NO_TEL_PEGAWAI_JKPTG" style="text-transform:uppercase" value="$!NO_TEL_PEGAWAI_JKPTG" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top">&nbsp;</td>
      <td width="1%" align="center" valign="top">&nbsp;</td>
      <td width="34%" align="left" valign="top">&nbsp;</td>
      <td align="right" valign="top"><strong>CAWANGAN PEGAWAI JKPTG #if ($isJPPHUser != 'true')<span class="mandatori">*</span>#end</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><textarea name="CAWANGAN_PEGAWAI_JKPTG" id="CAWANGAN_PEGAWAI_JKPTG" style="text-transform:uppercase" rows="2" cols="30" $READONLY_JKPTG>$!CAWANGAN_PEGAWAI_JKPTG</textarea></td>
    </tr>
#if ($isJPPHUser != 'true')
    <tr>
      <td colspan="6" valign="top" style="font-style:italic"><span class="mandatori">Perhatian</span>: Sila pastikan label bertanda <span class="mandatori">*</span> diisi.</td>
    </tr>
#end        
    <tr>
      <td colspan="6" valign="top">&nbsp;</td>
    </tr>
  </table>  
</fieldset>
<br />
#if ($isJPPHUser != 'true')  
<fieldset>
  <legend><strong>E-MAIL</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr>
      <td width="30%" style="text-align:right"><strong>ALAMAT E-MEL PEGAWAI JPPH</strong> :</td>
      <td width="70%"><input name="EMAIL_ADDR_PEGAWAI_JPPH" type="text" id="EMAIL_ADDR_PEGAWAI_JPPH" value="$!EMAIL_ADDR_PEGAWAI_JPPH" size="50" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>
        <input type="checkbox" id="EMAIL_SEND_JPPH" name="EMAIL_SEND_JPPH" value="1" $EMAIL_SEND_JKPTG $DISABLE_JKPTG /> Sila &radic; jika anda ingin menghantar e-mail pemberitahuan bagi permohonan ini.
      </td>
    </tr>
  </table>
</fieldset>
<br />
#end    
<fieldset>
  <legend><strong>JPPH</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top"><strong>NILAI TARIKH MOHON (RM) #if ($isJPPHUser == 'true')<span class="mandatori">*</span>#end</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="JPPH_NILAI_TARIKH_MOHON" type="text" id="JPPH_NILAI_TARIKH_MOHON" value="$!JPPH_NILAI_TARIKH_MOHON" size="20" maxlength="15" $READONLY_JPPH style="text-transform:uppercase" />
      (Tarikh Mohon: $MP_TARIKHMOHON)</td>
      <td width="15%" rowspan="4" align="right" valign="top"><strong>CATATAN</strong></td>
      <td width="1%" rowspan="4" align="center" valign="top">:</td>
      <td width="34%" rowspan="4" align="left" valign="top"><textarea name="JPPH_CATATAN" cols="30" rows="5" wrap="physical" id="JPPH_CATATAN" style="text-transform:uppercase" $READONLY_JPPH>$!JPPH_CATATAN</textarea></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top"><strong>NAMA PEGAWAI #if ($isJPPHUser == 'true')<span class="mandatori">*</span>#end</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="JPPH_NAMA_PEGAWAI" type="text" id="JPPH_NAMA_PEGAWAI" value="$!JPPH_NAMA_PEGAWAI" size="30" maxlength="255" $READONLY_JPPH style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top"><strong>CAWANGAN JPPH #if ($isJPPHUser == 'true')<span class="mandatori">*</span>#end</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="JPPH_CAWANGAN_JPPH" type="text" id="JPPH_CAWANGAN_JPPH" value="$!JPPH_CAWANGAN_JPPH" size="30" maxlength="255" $READONLY_JPPH style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td width="15%" align="right" valign="top"><strong>TARIKH LAWAT PERIKSA #if ($isJPPHUser == 'true')<span class="mandatori">*</span>#end</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top">
        <input name="JPPH_TARIKH_LAWAT_PERIKSA" type="text" id="JPPH_TARIKH_LAWAT_PERIKSA" value="$!JPPH_TARIKH_LAWAT_PERIKSA" size="15" maxlength="10" $READONLY_JPPH style="text-transform:uppercase" />
        <a href="javascript:displayDatePicker('JPPH_TARIKH_LAWAT_PERIKSA',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" $READONLY_PAJAKAN /></a>      </td>
    </tr>
#if ($isJPPHUser == 'true')
    <tr>
      <td colspan="6" valign="top" style="font-style:italic"><span class="mandatori">Perhatian</span>: Sila pastikan label bertanda <span class="mandatori">*</span> diisi.</td>
    </tr>
#end    
    <tr>
      <td colspan="6" valign="top">&nbsp;</td>
    </tr>
  </table>  
</fieldset>
<br />
<div style="text-align:center">
  <input id="cmdKembali" name="cmdKembali" type="button" value="Kembali" onclick="backNilaianHAK();" />&nbsp;
  <input id="cmdCetakMaklumat" name="cmdCetakMaklumat" type="button" value="Cetak Maklumat" onclick="printNilaianHAK('3', '$ID_HA');" />&nbsp;
#if ($isJPPHUser == 'true')
    #if ($sendNilaianHAK != 'true')
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Simpan" onclick="saveNilaianHAK();" />&nbsp;
  <input id="cmdDikembalikan" name="cmdDikembalikan" type="button" value="Dikembalikan" onclick="returnNilaianHAK();" />&nbsp;
  <input id="cmdHantarJKPTG" name="cmdHantarJKPTG" type="button" value="Hantar ke JKPTG" onclick="sendNilaianHAK();" />&nbsp;
	#end
#else
    #if ($permohonanSelesai != 'true')
		#if ($mode == 'edit')
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Hantar ke JPPH" onclick="sendNilaianHAK();" />&nbsp;
		#else
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Kemaskini" onclick="editNilaianHAK();" />&nbsp;
		#end
    #end
#end
#if ($isJPPHUser != 'true')
	#if ($haveINTData == 'true')  
  <input type="button" id="cmdDelete" name="cmdDelete" value="Batalkan Permohonan Nilaian JPPH" onclick="deleteNilaianHAK();" />
	#end  
#end
</div>
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="ID_HA" name="ID_HA" value="$ID_HA" />
<input type="hidden" id="action2" name="action2" value="$action2" />
<input type="hidden" id="mode" name="mode" value="$mode" />
<script type="text/javascript">
  function doChangeTab(TabID) {
      document.${formName}.action2.value = "viewNilaianHAK";
      document.${formName}.selectedTab.value = TabID;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=viewNilaianHAK";
      doAjaxCall${formName}("");
  }
  function doChangeSOC(SOC_TYPE) {
      document.${formName}.action2.value = "viewNilaianHAK";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=viewNilaianHAK&mode=edit";
      doAjaxCall${formName}(SOC_TYPE);
  }
  function sendNilaianHAK() {
#if ($isJPPHUser == 'true')  
      if (document.getElementById('JPPH_NILAI_TARIKH_MOHON').value == '') {
          alert('Sila isikan nilai tarikh mohon kenderaan.');
          document.getElementById('JPPH_NILAI_TARIKH_MOHON').focus();
          return;
      }
      if (document.getElementById('JPPH_NAMA_PEGAWAI').value == '') {
          alert('Sila isikan nama pegawai penilai.');
          document.getElementById('JPPH_NAMA_PEGAWAI').focus();
          return;
      }
      if (document.getElementById('JPPH_CAWANGAN_JPPH').value == '') {
          alert('Sila isikan nama cawangan JPPH yang membuat penilaian.');
          document.getElementById('JPPH_CAWANGAN_JPPH').focus();
          return;
      }
      if (document.getElementById('JPPH_TARIKH_LAWAT_PERIKSA').value == '') {
          alert('Sila isikan tarikh lawat periksa dibuat.');
          document.getElementById('JPPH_TARIKH_LAWAT_PERIKSA').focus();
          return;
      }
#else
      if (document.getElementById('NO_PENDAFTARAN').value == '') {
          alert('Sila isikan no pendaftaran kenderaan.');
          document.getElementById('NO_PENDAFTARAN').focus();
          return;
      }	  
      if (document.getElementById('ALAMAT1_LOKASI').value == '') {
          alert('Sila isikan lokasi kenderaan berada.');
          document.getElementById('ALAMAT1_LOKASI').focus();
          return;
      }	  
      if (document.getElementById('ALAMAT2_LOKASI').value == '') {
          alert('Sila isikan lokasi kenderaan berada.');
          document.getElementById('ALAMAT2_LOKASI').focus();
          return;
      }	  
      if (document.getElementById('ALAMAT3_LOKASI').value == '') {
          alert('Sila isikan lokasi kenderaan berada.');
          document.getElementById('ALAMAT3_LOKASI').focus();
          return;
      }	  
#end	  
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=sendNilaianHAK";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function saveNilaianHAK() {
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=saveNilaianHAK";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function returnNilaianHAK() {
      if (document.getElementById('JPPH_CATATAN').value == '') {
          alert('Sila isikan sebab permohonan dikembalikan dalam ruangan Catatan.');
          document.getElementById('JPPH_CATATAN').focus();
          return;
      }
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=returnNilaianHAK";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function backNilaianHAK() {
#if ($isJPPHUser == 'true')
      document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewMyInfoNilaianHartaTakAlih&action2=";
#else
	#if ($mode == 'edit')
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=viewNilaianHAK";
      document.${formName}.mode.value = "";
	#else
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=";
	#end
      document.${formName}.method = "POST";
#end  
      document.${formName}.submit();
  }
  function viewNilaianHAK(ID_HAK) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=viewNilaianHAK&ID_HAK=" + ID_HAK;
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function deleteNilaianHAK() {
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=deleteNilaianHAK";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function printNilaianHAK(RT, ID_HA) {
	  var url = "../servlet/ekptg.report.integrasi.ReportJPPH?reportType=PDF&rt=" + RT + "&ID_HA=" + ID_HA;
	  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
  }
  function editNilaianHAK() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=viewNilaianHAK&mode=edit";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>