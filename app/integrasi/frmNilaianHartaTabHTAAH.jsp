#if ($isJPPHUser == 'true')
    #set ($READONLY_JPPH = '')
    #set ($DISABLE_JPPH = '')
	#if ($isPermohonanSelesai == 'true')
		#set ($READONLY_JPPH = 'readonly="readonly" class="disabled"')
	    #set ($DISABLE_JPPH = 'disabled="disabled" class="disabled"')
    #end
    #set ($READONLY_JKPTG = 'readonly="readonly" class="disabled"')
    #set ($DISABLE_JKPTG = 'disabled="disabled" class="disabled"')
#else
	#set ($READONLY_JPPH = 'readonly="readonly" class="disabled"')
    #set ($DISABLE_JPPH = 'disabled="disabled" class="disabled"')
    #set ($READONLY_JKPTG = '')
    #set ($DISABLE_JKPTG = '')
#end
#if ($isJPPHUser == 'true')
    #set ($READONLY_PAJAKAN = 'readonly="readonly" class="disabled"')
#else
    #if ($READONLY_PAJAKAN != '')
        #set ($READONLY_PAJAKAN = 'readonly="readonly" class="disabled"')
    #end
#end
<br />***: $READONLY_JPPH
<div>
  <input type="checkbox" id="HTAAH_NILAIANPADATARIKHMATI" name="HTAAH_NILAIANPADATARIKHMATI" value="1" $HTAAH_NILAIANPADATARIKHMATI $DISABLE_JKPTG /> Sila &radic; jika nilaian pada tarikh mati diperlukan<br /><br />
</div>
<fieldset>
  <legend><strong>HARTA TAK ALIH (ADA HAKMILIK)</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top">Negeri</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top">$selectAHNegeri</td>
      <td width="15%" align="right" valign="top">Kategori Tanah</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top">$selectAHKategoriTanah</td>
    </tr>
    <tr>
      <td align="right" valign="top">Daerah</td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectAHDaerah</td>
      <td rowspan="3" align="right" valign="top">Syarat Nyata</td>
      <td rowspan="3" align="center" valign="top">:</td>
      <td rowspan="3" align="left" valign="top"><textarea name="HTAAH_SYARATNYATA" cols="50" rows="3" id="HTAAH_SYARATNYATA" $READONLY_JKPTG style="text-transform:uppercase">$!HTAAH_SYARATNYATA</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top">Mukim / Bandar</td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectAHMukim</td>
    </tr>
    <tr>
      <td align="right" valign="top">Seksyen</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTAAH_SEKSYEN" type="text" id="HTAAH_SEKSYEN" value="$!HTAAH_SEKSYEN" size="42" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Jenis Hakmilik</td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectAHJenisHakmilik</td>
      <td rowspan="3" align="right" valign="top">Sekatan Kepentingan</td>
      <td rowspan="3" align="center" valign="top">:</td>
      <td rowspan="3" align="left" valign="top"><textarea name="HTAAH_SEKATANKEPENTINGAN" cols="50" rows="3" id="HTAAH_SEKATANKEPENTINGAN" $READONLY_JKPTG style="text-transform:uppercase">$!HTAAH_SEKATANKEPENTINGAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top">No Hakmilik</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTAAH_NOHAKMILIK" type="text" id="HTAAH_NOHAKMILIK" value="$!HTAAH_NOHAKMILIK" size="20" maxlength="10" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">No PT / Lot</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTAAH_NOPTLOT" type="text" id="HTAAH_NOPTLOT" value="$!HTAAH_NOPTLOT" size="20" maxlength="20" $READONLY_JKPTG style="text-transform:uppercase" />&nbsp;$selectAHJenisPTLot</td>
    </tr>
    <tr>
      <td align="right" valign="top">No Bangunan / Tingkat / Petak</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTAAH_NOBANGUNAN" type="text" id="HTAAH_NOBANGUNAN" value="$!HTAAH_NOBANGUNAN" size="10" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" />
        / 
        <input name="HTAAH_NOTINGKAT" type="text" id="HTAAH_NOTINGKAT" value="$!HTAAH_NOTINGKAT" size="10" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" /> 
        / 
        <input name="HTAAH_NOPETAK" type="text" id="HTAAH_NOPETAK" value="$!HTAAH_NOPETAK" size="10" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" /></td>
      <td align="right" valign="top">Luas Tanah</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="HTAAH_LUASTANAH" type="text" id="HTAAH_LUASTANAH" value="$!HTAAH_LUASTANAH" size="10" maxlength="10" $READONLY_JKPTG style="text-transform:uppercase" />
      &nbsp;$selectAHJenisLuasTanah</td>
    </tr>
    <tr>
      <td align="right" valign="top">Bahagian Si Mati</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTAAH_BASIMATI" type="text" id="HTAAH_BASIMATI" style="text-align:right; text-transform:uppercase" value="$!HTAAH_BASIMATI" size="10" maxlength="10" $READONLY_JKPTG />
        /
      <input name="HTAAH_BBSIMATI" type="text" id="HTAAH_BBSIMATI" value="$!HTAAH_BBSIMATI" size="10" maxlength="10" $READONLY_JKPTG style="text-transform:uppercase" /></td>
      <td align="right" valign="top">Luas Petak (mp)</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="HTAAH_LUASPETAK" type="text" id="HTAAH_LUASPETAK" value="$!HTAAH_LUASPETAK" size="20" maxlength="10" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Jenis Pegangan</td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectAHJenisPegangan</td>
      <td align="right" valign="top">Alamat Harta</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="HTAAH_ALAMAT1HARTA" type="text" id="HTAAH_ALAMAT1HARTA" value="$!HTAAH_ALAMAT1HARTA" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Tarikh Luput Pajakan</td>
      <td align="center" valign="top">:</td>
      <td valign="top">
#if ($DISABLE_PAJAKAN != '')      
        <input name="HTAAH_TARIKHLUPUTPAJAKAN" type="text" id="HTAAH_TARIKHLUPUTPAJAKAN" value="$!HTAAH_TARIKHLUPUTPAJAKAN" size="15" maxlength="10" $READONLY_PAJAKAN />
        &nbsp;<img src="../img/calendar.gif" alt="" border="0" />
#else        
        <input name="HTAAH_TARIKHLUPUTPAJAKAN" type="text" id="HTAAH_TARIKHLUPUTPAJAKAN" value="$!HTAAH_TARIKHLUPUTPAJAKAN" size="15" maxlength="10" $READONLY_PAJAKAN />&nbsp;<a href="javascript:displayDatePicker('HTAAH_TARIKHLUPUTPAJAKAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
#end  </td>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top"><input name="HTAAH_ALAMAT2HARTA" type="text" id="HTAAH_ALAMAT2HARTA" value="$!HTAAH_ALAMAT2HARTA" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Tempoh Pajakan</td>
      <td align="center" valign="top">:</td>
      <td valign="top">
#if ($DISABLE_PAJAKAN != '')            
        <input name="HTAAH_TEMPOHPAJAKAN" type="text" id="HTAAH_TEMPOHPAJAKAN" value="$!HTAAH_TEMPOHPAJAKAN" size="15" maxlength="5" $READONLY_PAJAKAN style="text-transform:uppercase" />
#else
        <input name="HTAAH_TEMPOHPAJAKAN" type="text" id="HTAAH_TEMPOHPAJAKAN" value="$!HTAAH_TEMPOHPAJAKAN" size="15" maxlength="5" $READONLY_PAJAKAN style="text-transform:uppercase" />
#end
      </td>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top"><input name="HTAAH_ALAMAT3HARTA" type="text" id="HTAAH_ALAMAT3HARTA" value="$!HTAAH_ALAMAT3HARTA" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Jenis Harta Dinilai</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$selectAHJenisHartaDinilai</td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Catatan</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><textarea name="HTAAH_CATATAN" cols="50" rows="3" id="HTAAH_CATATAN" $READONLY_JKPTG style="text-transform:uppercase">$!HTAAH_CATATAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Nama Pegawai JKPTG</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="HTAAH_NAMAPEGAWAIJKPTG" type="text" id="HTAAH_NAMAPEGAWAIJKPTG" value="$HTAAH_NAMAPEGAWAIJKPTG" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
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
    <tr>
      <td width="30%" style="text-align:right">
        Alamat e-mail pegawai:
      </td>
      <td width="70%">
        <input name="EMAIL_ADDR" type="text" id="EMAIL_ADDR" value="$!EMAIL_ADDR" size="50" maxlength="255" />
      </td>
    </tr>
    <tr>
      <td>&nbsp;
        
      </td>
      <td>
        <input type="checkbox" id="EMAIL_SEND" name="EMAIL_SEND" value="1" $EMAIL_SEND /> Sila &radic; jika anda ingin menghantar e-mail pemberitahuan bagi permohonan ini.
      </td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>JPPH</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top">Nilai Tarikh Mohon (RM)</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="HTAAH_NILAITARIKHMOHON" type="text" id="HTAAH_NILAITARIKHMOHON" value="$!HTAAH_NILAITARIKHMOHON" size="20" maxlength="15" $READONLY_JPPH style="text-transform:uppercase" /></td>
      <td width="15%" rowspan="3" align="right" valign="top">Catatan</td>
      <td width="1%" rowspan="3" align="center" valign="top">:</td>
      <td width="34%" rowspan="3" align="left" valign="top"><textarea name="HTAAH_JPPHCATATAN" cols="50" rows="3" id="HTAAH_JPPHCATATAN" $READONLY_JPPH style="text-transform:uppercase">$!HTAAH_JPPHCATATAN</textarea></td>
    </tr>
    
    <tr>
      <td width="15%" align="right" valign="top">Nilai Tarikh Mati (RM)</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="HTAAH_NILAITARIKHMATI" type="text" id="HTAAH_NILAITARIKHMATI" value="$!HTAAH_NILAITARIKHMATI" size="20" maxlength="15" $READONLY_JPPH style="text-transform:uppercase" /></td>
    </tr>
    
    <tr>
      <td align="right" valign="top">Nama Pegawai</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTAAH_NAMAPEGAWAI" type="text" id="HTAAH_NAMAPEGAWAI" value="$!HTAAH_NAMAPEGAWAI" size="50" maxlength="255" $READONLY_JPPH style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Cawangan JPPH</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTAAH_CAWANGANJPPH" type="text" id="HTAAH_CAWANGANJPPH" value="$!HTAAH_CAWANGANJPPH" size="50" maxlength="255" $READONLY_JPPH style="text-transform:uppercase" /></td>
      <td align="right" valign="top">Tarikh Nilaian</td>
      <td align="center" valign="top">:</td>
      <td valign="top">
        <input name="HTAAH_TARIKHNILAIAN" type="text" id="HTAAH_TARIKHNILAIAN" value="$!HTAAH_TARIKHNILAIAN" size="15" maxlength="10" $READONLY_JPPH style="text-transform:uppercase" />
        <a href="javascript:displayDatePicker('HTAAH_TARIKHNILAIAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" $READONLY_PAJAKAN /></a>
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
  <input id="cmdCetakMaklumat" name="cmdCetakMaklumat" type="button" value="Cetak Maklumat" onclick="printNilaianHTA('1', '$ID_HTAAH');" />&nbsp;
#if ($isJPPHUser == 'true')
	#if ($isPermohonanSelesai != 'true')
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Simpan" onclick="saveNilaianHTA();" />&nbsp;&nbsp;
  <input id="cmdDikembalikan" name="cmdDikembalikan" type="button" value="Dikembalikan" onclick="returnNilaianHTA('HTAAH_JPPHCATATAN');" />&nbsp;
  <input id="cmdHantarJKPTG" name="cmdHantarJKPTG" type="button" value="Hantar ke JKPTG" onclick="sendNilaianHTA();" />
	#end
#else
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Hantar ke JPPH" onclick="sendNilaianHTA();" />&nbsp;
  <input id="cmdHapus" name="cmdHapus" type="button" value="Hapus" onclick="deleteNilaianHTA();" />
#end
</div>
<br />