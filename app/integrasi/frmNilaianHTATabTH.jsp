#if ($isJPPHUser == 'true')
    #set ($READONLY_JPPH = '')
    #set ($DISABLE_JPPH = '')
	#if ($haveINTData == 'true')
		#set ($READONLY_JPPH = 'readonly="readonly" class="disabled"')
	    #set ($DISABLE_JPPH = 'disabled="disabled" class="disabled"')
    #end
    #set ($READONLY_JKPTG = 'readonly="readonly" class="disabled"')
    #set ($DISABLE_JKPTG = 'disabled="disabled" class="disabled"')
    #set ($READONLY_PAJAKAN = 'readonly="readonly" class="disabled"')
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
  <input type="checkbox" id="PERLU_NILAIAN_TARIKH_MATI" name="PERLU_NILAIAN_TARIKH_MATI" value="1" $PERLU_NILAIAN_TARIKH_MATI $DISABLE_JKPTG /> Sila &radic; jika nilaian pada tarikh mati diperlukan<br /><br />
</div>
<fieldset>
  <legend><strong>HARTA TAK ALIH (ADA HAKMILIK)</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top">Negeri</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top">$selectNegeri</td>
      <td width="15%" align="right" valign="top">Kategori Tanah</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top">$selectKategoriTanah</td>
    </tr>
    <tr>
      <td align="right" valign="top">Daerah</td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectDaerah</td>
      <td rowspan="3" align="right" valign="top">Syarat Nyata</td>
      <td rowspan="3" align="center" valign="top">:</td>
      <td rowspan="3" align="left" valign="top"><textarea name="SYARAT_NYATA" cols="50" rows="3" id="SYARAT_NYATA" $READONLY_JKPTG style="text-transform:uppercase">$!SYARAT_NYATA</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top">Mukim / Bandar</td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectMukim</td>
    </tr>
    <tr>
      <td align="right" valign="top">Seksyen</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="SEKSYEN" type="text" id="SEKSYEN" value="$!SEKSYEN" size="42" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">No PT / Lot</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="NO_PTLOT" type="text" id="NO_PTLOT" value="$!NO_PTLOT" size="20" maxlength="20" $READONLY_JKPTG style="text-transform:uppercase" />
  &nbsp;$selectJenisPTLot</td>
      <td rowspan="3" align="right" valign="top">Sekatan Kepentingan</td>
      <td rowspan="3" align="center" valign="top">:</td>
      <td rowspan="3" align="left" valign="top"><textarea name="SEKATAN_KEPENTINGAN" cols="50" rows="3" id="SEKATAN_KEPENTINGAN" $READONLY_JKPTG style="text-transform:uppercase">$!SEKATAN_KEPENTINGAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top">No Bangunan / Tingkat / Petak</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="NO_BANGUNAN" type="text" id="NO_BANGUNAN" value="$!NO_BANGUNAN" size="10" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" />
        /
        <input name="NO_TINGKAT" type="text" id="NO_TINGKAT" value="$!NO_TINGKAT" size="10" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" />
        /
  <input name="NO_PETAK" type="text" id="NO_PETAK" value="$!NO_PETAK" size="10" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Bahagian Si Mati</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="BA_SIMATI" type="text" id="BA_SIMATI" style="text-align:right; text-transform:uppercase" value="$!BA_SIMATI" size="10" maxlength="10" $READONLY_JKPTG />
        /
        <input name="BB_SIMATI" type="text" id="BB_SIMATI" value="$!BB_SIMATI" size="10" maxlength="10" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Jenis Pegangan</td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectJenisPegangan</td>
      <td align="right" valign="top">Alamat Harta</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="ALAMAT1_HARTA" type="text" id="ALAMAT1_HARTA" value="$!ALAMAT1_HARTA" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Tarikh Luput Pajakan</td>
      <td align="center" valign="top">:</td>
      <td valign="top"> #if ($DISABLE_PAJAKAN != '')
        <input name="TARIKH_LUPUT_PJKN" type="text" id="TARIKH_LUPUT_PJKN" value="$!TARIKH_LUPUT_PJKN" size="15" maxlength="10" $READONLY_PAJAKAN />
  &nbsp;<img src="../img/calendar.gif" alt="" border="0" /> #else
  <input name="TARIKH_LUPUT_PJKN" type="text" id="TARIKH_LUPUT_PJKN" value="$!TARIKH_LUPUT_PJKN" size="15" maxlength="10" $READONLY_PAJAKAN />
  &nbsp;<a href="javascript:displayDatePicker('TARIKH_LUPUT_PJKN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a> #end </td>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top"><input name="ALAMAT2_HARTA" type="text" id="ALAMAT2_HARTA" value="$!ALAMAT2_HARTA" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Tempoh Pajakan</td>
      <td align="center" valign="top">:</td>
      <td valign="top"> #if ($DISABLE_PAJAKAN != '')
        <input name="TEMPOH_PJKN" type="text" id="TEMPOH_PJKN" value="$!TEMPOH_PJKN" size="15" maxlength="5" $READONLY_PAJAKAN style="text-transform:uppercase" />
        #else
  <input name="TEMPOH_PJKN" type="text" id="TEMPOH_PJKN" value="$!TEMPOH_PJKN" size="15" maxlength="5" $READONLY_PAJAKAN style="text-transform:uppercase" />
        #end </td>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top"><input name="ALAMAT3_HARTA" type="text" id="ALAMAT3_HARTA" value="$!ALAMAT3_HARTA" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Nama Pemilik</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="NAMA_PEMILIK" type="text" id="NAMA_PEMILIK" value="$!NAMA_PEMILIK" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Tarikh Perjanjian Jual Beli</td>
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
      <td align="right" valign="top">Nama Pemaju</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="NAMA_PEMAJU" type="text" id="NAMA_PEMAJU" value="$!NAMA_PEMAJU" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Luas Petak (mp)</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="LUAS_PETAK" type="text" id="LUAS_PETAK" value="$!LUAS_PETAK" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Catatan</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><textarea name="CATATAN" cols="50" rows="3" id="CATATAN" $READONLY_JKPTG style="text-transform:uppercase">$!CATATAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Nama Pegawai JKPTG</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="NAMA_PEGAWAI_JKPTG" type="text" id="NAMA_PEGAWAI_JKPTG" value="$!NAMA_PEGAWAI_JKPTG" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td colspan="6">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>E-MAIL</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
#if ($isJPPHUser == 'true')  
    <tr>
      <td width="30%" style="text-align:right">
        Alamat e-mail pegawai JKPTG:
      </td>
      <td width="70%">
        <input name="EMAIL_ADDR_JPPH" type="text" id="EMAIL_ADDR_JPPH" value="$!EMAIL_ADDR_JPPH" size="50" maxlength="255" $READONLY_JPPH />
      </td>
    </tr>
    <tr>
      <td>&nbsp;
        
      </td>
      <td>
        <input type="checkbox" id="EMAIL_SEND_JPPH" name="EMAIL_SEND_JPPH" value="1" $EMAIL_SEND_JPPH $DISABLE_JPPH /> Sila &radic; jika anda ingin menghantar e-mail pemberitahuan bagi permohonan ini.
      </td>
    </tr>
#else
    <tr>
      <td width="30%" style="text-align:right">
        Alamat e-mail pegawai JPPH:
      </td>
      <td width="70%">
        <input name="EMAIL_ADDR_JKPTG" type="text" id="EMAIL_ADDR_JKPTG" value="$!EMAIL_ADDR_JKPTG" size="50" maxlength="255" $READONLY_JKPTG />
      </td>
    </tr>
    <tr>
      <td>&nbsp;
        
      </td>
      <td>
        <input type="checkbox" id="EMAIL_SEND_JKPTG" name="EMAIL_SEND_JKPTG" value="1" $EMAIL_SEND_JKPTG $DISABLE_JKPTG /> Sila &radic; jika anda ingin menghantar e-mail pemberitahuan bagi permohonan ini.
      </td>
    </tr>
#end    
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>JPPH</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top">Nilai Tarikh Mohon (RM)</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="JPPH_NILAI_MOHON" type="text" id="JPPH_NILAI_MOHON" value="$!JPPH_NILAI_MOHON" size="20" maxlength="15" $READONLY_JPPH style="text-transform:uppercase" /></td>
      <td width="15%" rowspan="3" align="right" valign="top">Catatan</td>
      <td width="1%" rowspan="3" align="center" valign="top">:</td>
      <td width="34%" rowspan="3" align="left" valign="top"><textarea name="JPPH_CATATAN" cols="50" rows="3" id="JPPH_CATATAN" $READONLY_JPPH style="text-transform:uppercase">$!JPPH_CATATAN</textarea></td>
    </tr>
    
    <tr>
      <td width="15%" align="right" valign="top">Nilai Tarikh Mati (RM)</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="JPPH_NILAI_MATI" type="text" id="JPPH_NILAI_MATI" value="$!JPPH_NILAI_MATI" size="20" maxlength="15" $READONLY_JPPH style="text-transform:uppercase" /></td>
    </tr>
    
    <tr>
      <td align="right" valign="top">Nama Pegawai</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="JPPH_NAMA_PEGAWAI" type="text" id="JPPH_NAMA_PEGAWAI" value="$!JPPH_NAMA_PEGAWAI" size="50" maxlength="255" $READONLY_JPPH style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Cawangan JPPH</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="JPPH_NAMA_CAWANGAN" type="text" id="JPPH_NAMA_CAWANGAN" value="$!JPPH_NAMA_CAWANGAN" size="50" maxlength="255" $READONLY_JPPH style="text-transform:uppercase" /></td>
      <td align="right" valign="top">Tarikh Nilaian</td>
      <td align="center" valign="top">:</td>
      <td valign="top">
        <input name="JPPH_TARIKH_NILAIAN" type="text" id="JPPH_TARIKH_NILAIAN" value="$!JPPH_TARIKH_NILAIAN" size="15" maxlength="10" $READONLY_JPPH style="text-transform:uppercase" />
        <a href="javascript:displayDatePicker('JPPH_TARIKH_NILAIAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" $READONLY_PAJAKAN /></a>
      </td>
    </tr>
    <tr>
      <td colspan="6" align="right" valign="top">&nbsp;</td>
    </tr>
  </table>  
</fieldset>
<br />
<div style="text-align:center">
  <input id="cmdKembali" name="cmdKembali" type="button" value="Kembali" onclick="backNilaianHTA();" />&nbsp;
  <input id="cmdCetakMaklumat" name="cmdCetakMaklumat" type="button" value="Cetak Maklumat" onclick="printNilaianHTA('1', '$ID_HTA');" />&nbsp;
#if ($isJPPHUser == 'true')
	#if ($haveINTData != 'true')
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