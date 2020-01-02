#if ($isJPPHUser == 'true')
    #set ($READONLY_JPPH = '')
    #set ($DISABLE_JPPH = '')
    #if ($haveINTData != 'true')
        #set ($READONLY_JPPH = 'readonly="readonly" class="disabled"')
        #set ($DISABLE_JPPH = 'disabled="disabled" class="disabled"')
    #end
    #set ($READONLY_JKPTG = 'readonly="readonly" class="disabled"')
    #set ($DISABLE_JKPTG = 'disabled="disabled" class="disabled"')
    #set ($READONLY_PAJAKAN = 'readonly="readonly" class="disabled"')
    #if ($sendNilaianHTA == 'true')
        #set ($READONLY_JPPH = 'readonly="readonly" class="disabled"')
        #set ($DISABLE_JPPH = 'disabled="disabled" class="disabled"')
    #end
#else
	#set ($READONLY_JPPH = 'readonly="readonly" class="disabled"')
    #set ($DISABLE_JPPH = 'disabled="disabled" class="disabled"')
    #if ($mode == 'edit')
        #set ($READONLY_JKPTG = '')
        #set ($DISABLE_JKPTG = '')
        #set ($READONLY_PAJAKAN = '')
        #set ($DISABLE_PAJAKAN = '')
    #else
        #set ($READONLY_JKPTG = 'readonly="readonly" class="disabled"')
        #set ($DISABLE_JKPTG = 'disabled="disabled" class="disabled"')
        #if ($READONLY_PAJAKAN == 'true')
            #set ($READONLY_PAJAKAN = 'readonly="readonly" class="disabled"')
            #set ($DISABLE_PAJAKAN = 'disabled="disabled" class="disabled"')
        #else
            #set ($READONLY_PAJAKAN = '')
            #set ($DISABLE_PAJAKAN = '')
        #end
    #end
#end
<div>
#if ($displayNilaianTarikhMati == 'true')
  <input type="checkbox" id="PERLU_NILAIAN_TARIKH_MATI" name="PERLU_NILAIAN_TARIKH_MATI" value="1" $PERLU_NILAIAN_TARIKH_MATI $DISABLE_JKPTG /> Sila &radic; jika nilaian pada tarikh mati diperlukan<br /><br />
#end  
</div>
<fieldset>
  <legend><strong>HARTA TAK ALIH (TIADA HAKMILIK)</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top"><strong>NEGERI <span class="mandatori">*</span></strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top">$selectNegeri</td>
      <td width="15%" align="right" valign="top"><strong>KATEGORI TANAH <span class="mandatori">*</span></strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top">$selectKategoriTanah</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>DAERAH <span class="mandatori">*</span></strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectDaerah</td>
      <td rowspan="3" align="right" valign="top"><strong>SYARAT NYATA <span class="mandatori">*</span></strong></td>
      <td rowspan="3" align="center" valign="top">:</td>
      <td rowspan="3" align="left" valign="top"><textarea name="SYARAT_NYATA" cols="30" rows="3" id="SYARAT_NYATA" $READONLY_JKPTG style="text-transform:uppercase">$!SYARAT_NYATA</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>MUKIM / BANDAR <span class="mandatori">*</span></strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectMukim</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>SEKSYEN</strong></td>
      <td align="center" valign="top">:</td>
          <td valign="top"><input name="SEKSYEN" type="text" id="SEKSYEN" value="$!SEKSYEN" size="22" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NO PT / LOT <span class="mandatori">*</span></strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="NO_PTLOT" type="text" id="NO_PTLOT" value="$!NO_PTLOT" size="10" maxlength="20" $READONLY_JKPTG style="text-transform:uppercase" />
  &nbsp;$selectJenisPTLot</td>
      <td rowspan="3" align="right" valign="top"><strong>SEKATAN KEPENTINGAN <span class="mandatori">*</span></strong></td>
      <td rowspan="3" align="center" valign="top">:</td>
      <td rowspan="3" align="left" valign="top"><textarea name="SEKATAN_KEPENTINGAN" cols="30" rows="3" id="SEKATAN_KEPENTINGAN" $READONLY_JKPTG style="text-transform:uppercase">$!SEKATAN_KEPENTINGAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NO BANGUNAN / TINGKAT / PETAK <span class="mandatori">*</span></strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="NO_BANGUNAN" type="text" id="NO_BANGUNAN" value="$!NO_BANGUNAN" size="5" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" />
        /
        <input name="NO_TINGKAT" type="text" id="NO_TINGKAT" value="$!NO_TINGKAT" size="5" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" />
        /
  <input name="NO_PETAK" type="text" id="NO_PETAK" value="$!NO_PETAK" size="5" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>BAHAGIAN SIMATI <span class="mandatori">*</span></strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="BA_SIMATI" type="text" id="BA_SIMATI" style="text-align:right; text-transform:uppercase" value="$!BA_SIMATI" size="5" maxlength="10" $READONLY_JKPTG />
        /
        <input name="BB_SIMATI" type="text" id="BB_SIMATI" value="$!BB_SIMATI" size="5" maxlength="10" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>JENIS PEGANGAN <span class="mandatori">*</span></strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectJenisPegangan</td>
      <td align="right" valign="top"><strong>ALAMAT HARTA <span class="mandatori">*</span></strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="ALAMAT1_HARTA" type="text" id="ALAMAT1_HARTA" value="$!ALAMAT1_HARTA" size="30" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>TARIKH LUPUT PAJAKAN <span class="mandatori">*</span></strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"> #if ($DISABLE_PAJAKAN != '')
        <input name="TARIKH_LUPUT_PJKN" type="text" id="TARIKH_LUPUT_PJKN" value="$!TARIKH_LUPUT_PJKN" size="15" maxlength="10" $READONLY_PAJAKAN />
  &nbsp;<img src="../img/calendar.gif" alt="" border="0" /> #else
  <input name="TARIKH_LUPUT_PJKN" type="text" id="TARIKH_LUPUT_PJKN" value="$!TARIKH_LUPUT_PJKN" size="15" maxlength="10" $READONLY_PAJAKAN />
  &nbsp;<a href="javascript:displayDatePicker('TARIKH_LUPUT_PJKN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a> #end </td>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top"><input name="ALAMAT2_HARTA" type="text" id="ALAMAT2_HARTA" value="$!ALAMAT2_HARTA" size="30" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>TEMPOH PAJAKAN <span class="mandatori">*</span></strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"> #if ($DISABLE_PAJAKAN != '')
        <input name="TEMPOH_PJKN" type="text" id="TEMPOH_PJKN" value="$!TEMPOH_PJKN" size="15" maxlength="5" $READONLY_PAJAKAN style="text-transform:uppercase" />
        #else
  <input name="TEMPOH_PJKN" type="text" id="TEMPOH_PJKN" value="$!TEMPOH_PJKN" size="15" maxlength="5" $READONLY_PAJAKAN style="text-transform:uppercase" />
        #end </td>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top"><input name="ALAMAT3_HARTA" type="text" id="ALAMAT3_HARTA" value="$!ALAMAT3_HARTA" size="30" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top"><strong>NAMA PEMILIK</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="NAMA_PEMILIK" type="text" id="NAMA_PEMILIK" value="$!NAMA_PEMILIK" size="30" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top"><strong>TARIKH PERJANJIAN JUAL BELI</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">#if ($DISABLE_JKPTG != '')
        <input name="TARIKH_JUAL_BELI" type="text" id="TARIKH_JUAL_BELI" value="$!TARIKH_JUAL_BELI" size="15" maxlength="10" $READONLY_JKPTG />
&nbsp;<img src="../img/calendar.gif" alt="" border="0" /> #else
<input name="TARIKH_JUAL_BELI" type="text" id="TARIKH_JUAL_BELI" value="$!TARIKH_JUAL_BELI" size="15" maxlength="10" $READONLY_JKPTG />
&nbsp;<a href="javascript:displayDatePicker('TARIKH_LUPUT_PJKN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a> #end </td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top"><strong>NAMA PEMAJU</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="NAMA_PEMAJU" type="text" id="NAMA_PEMAJU" value="$!NAMA_PEMAJU" size="30" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top"><strong>LUAS PETAK (MP) <span class="mandatori">*</span></strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="LUAS_PETAK" type="text" id="LUAS_PETAK" value="$!LUAS_PETAK" size="30" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top"><strong>JENIS HARTA DINILAI</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$selectJenisHartaDinilai</td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top"><strong>CATATAN</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><textarea name="CATATAN" cols="30" rows="3" id="CATATAN" $READONLY_JKPTG style="text-transform:uppercase">$!CATATAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top"><strong>NAMA PEGAWAI JKPTG #if ($isJPPHUser != 'true')<span class="mandatori">*</span>#end</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="NAMA_PEGAWAI_JKPTG" type="text" id="NAMA_PEGAWAI_JKPTG" value="$!NAMA_PEGAWAI_JKPTG" size="30" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top"><strong>NO TEL PEGAWAI JKPTG #if ($isJPPHUser != 'true')<span class="mandatori">*</span>#end</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="NO_TEL_PEGAWAI_JKPTG" type="text" id="NO_TEL_PEGAWAI_JKPTG" value="$!NO_TEL_PEGAWAI_JKPTG" size="30" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
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
      <td colspan="6">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
#if ($isJPPHUser != 'true')
<fieldset>
  <legend><strong>E-MAIL</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr>
      <td width="30%" style="text-align:right"><strong>ALAMAT E-MEL PEGAWAI JPPH</strong> : </td>
      <td width="70%">
        <input name="EMAIL_ADDR_PEGAWAI_JPPH" type="text" id="EMAIL_ADDR_PEGAWAI_JPPH" value="$!EMAIL_ADDR_PEGAWAI_JPPH" size="30" maxlength="255" $READONLY_JKPTG />      </td>
    </tr>
    <tr>
      <td>&nbsp;      </td>
      <td>
        <input type="checkbox" id="EMAIL_SEND_JPPH" name="EMAIL_SEND_JPPH" value="1" $EMAIL_SEND_JKPTG $DISABLE_JKPTG />
        Sila &radic; jika anda ingin menghantar e-mail pemberitahuan bagi permohonan ini.      </td>
    </tr>
#end
  </table>
</fieldset>
<br />
#if ($displayNilaianTarikhMati == 'true')
	#set ($JPPH_CATATAN_ROWSPAN = '3')
#else    
	#set ($JPPH_CATATAN_ROWSPAN = '2')
#end
<fieldset>
  <legend><strong>JPPH</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top"><strong>NILAI TARIKH MOHON (RM)</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="JPPH_NILAI_TARIKH_MOHON" type="text" id="JPPH_NILAI_TARIKH_MOHON" value="$!JPPH_NILAI_TARIKH_MOHON" size="10" maxlength="15" $READONLY_JPPH style="text-transform:uppercase" />
       (Tarikh Mohon: $MP_TARIKHMOHON)</td>
      <td width="15%" rowspan="$JPPH_CATATAN_ROWSPAN" align="right" valign="top"><strong>CATATAN</strong></td>
      <td width="1%" rowspan="$JPPH_CATATAN_ROWSPAN" align="center" valign="top">:</td>
      <td width="34%" rowspan="$JPPH_CATATAN_ROWSPAN" align="left" valign="top"><textarea name="JPPH_CATATAN" cols="30" rows="3" id="JPPH_CATATAN" $READONLY_JPPH style="text-transform:uppercase">$!JPPH_CATATAN</textarea></td>
    </tr>
#if ($displayNilaianTarikhMati == 'true')
    <tr>
      <td align="right" valign="top"><strong>NILAI TARIKH MATI (RM)</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="JPPH_NILAI_TARIKH_MATI" type="text" id="JPPH_NILAI_TARIKH_MATI" value="$!JPPH_NILAI_TARIKH_MATI" size="10" maxlength="15" $READONLY_JPPH style="text-transform:uppercase" />
       (Tarikh Mati: $MP_TARIKHMATI)</td>
    </tr>
#end
    <tr>
      <td align="right" valign="top"><strong>NAMA PEGAWAI JPPH #if ($isJPPHUser == 'true')<span class="mandatori">*</span>#end</strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="JPPH_NAMA_PEGAWAI" type="text" id="JPPH_NAMA_PEGAWAI" value="$!JPPH_NAMA_PEGAWAI" size="30" maxlength="255" $READONLY_JPPH style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>CAWANGAN JPPH #if ($isJPPHUser == 'true')<span class="mandatori">*</span>#end</strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="JPPH_CAWANGAN_JPPH" type="text" id="JPPH_CAWANGAN_JPPH" value="$!JPPH_CAWANGAN_JPPH" size="30" maxlength="255" $READONLY_JPPH style="text-transform:uppercase" /></td>
      <td align="right" valign="top"><strong>TARIKH LAWAT PERIKSA #if ($isJPPHUser == 'true')<span class="mandatori">*</span>#end</strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top">
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
  <input id="cmdKembali" name="cmdKembali" type="button" value="Kembali" onclick="backNilaianHTA();" />&nbsp;
  <input id="cmdCetakMaklumat" name="cmdCetakMaklumat" type="button" value="Cetak Maklumat" onclick="printNilaianHTA('2', '$ID_HTA');" />&nbsp;
#if ($isJPPHUser == 'true')
	#if ($haveINTData == 'true' && $sendNilaianHTA != 'true')
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Simpan" onclick="saveNilaianHTA();" />&nbsp;&nbsp;
  <input id="cmdDikembalikan" name="cmdDikembalikan" type="button" value="Dikembalikan" onclick="returnNilaianHTA('JPPH_CATATAN');" />&nbsp;
  <input id="cmdHantarJKPTG" name="cmdHantarJKPTG" type="button" value="Hantar ke JKPTG" onclick="sendNilaianHTA();" />
	#end
#else
    #if ($permohonanSelesai != 'true')
		#if ($mode == 'edit')
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Hantar ke JPPH" onclick="sendNilaianHTA();" />&nbsp;
		#else
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Kemaskini" onclick="editNilaianHTA();" />&nbsp;
		#end
		#if ($haveINTData == 'true')  
  <input id="cmdHapus" name="cmdHapus" type="button" value="Batalkan Permohonan Nilaian JPPH" onclick="deleteNilaianHTA();" />
		#end
    #end  
#end
</div>
<br />