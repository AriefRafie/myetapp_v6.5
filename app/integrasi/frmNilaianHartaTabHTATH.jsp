#if ($isJPPHUser == 'true')
    #set ($READONLY_JPPH = '')
    #set ($DISABLE_JPPH = '')
	#if ($isPermohonanSelesai == 'true')
		#set ($READONLY_JPPH = 'readonly="readonly" class="disabled"')
	    #set ($DISABLE_JPPH = 'disabled="disabled"')
    #end
    #set ($READONLY_JKPTG = 'readonly="readonly" class="disabled"')
    #set ($DISABLE_JKPTG = 'disabled="disabled"')
#else
	#set ($READONLY_JPPH = 'readonly="readonly" class="disabled"')
    #set ($DISABLE_JPPH = 'disabled="disabled"')
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
<div>
  <input type="checkbox" id="HTATH_NILAIANPADATARIKHMATI" name="HTATH_NILAIANPADATARIKHMATI" value="1" $HTATH_NILAIANPADATARIKHMATI $DISABLE_JKPTG />
  Sila &radic; jika nilaian pada tarikh mati diperlukan<br /><br />
</div>
<fieldset>
  <legend><strong>HARTA TAK ALIH (TIADA HAKMILIK)</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top">Negeri</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top">$selectTHNegeri</td>
      <td width="15%" align="right" valign="top">Kategori Tanah</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" align="left" valign="top">
        $selectTHKategoriTanah
      </td>
    </tr>
    <tr>
      <td align="right" valign="top">Daerah</td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectTHDaerah</td>
      <td rowspan="3" align="right" valign="top">Syarat Nyata</td>
      <td rowspan="3" align="center" valign="top">:</td>
      <td rowspan="3" align="left" valign="top"><textarea name="HTATH_SYARATNYATA" cols="50" rows="3" id="HTATH_SYARATNYATA" $READONLY_JKPTG style="text-transform:uppercase">$!HTATH_SYARATNYATA</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top">Mukim / Bandar</td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectTHMukim</td>
    </tr>
    <tr>
      <td align="right" valign="top">Seksyen</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTATH_SEKSYEN" type="text" id="HTATH_SEKSYEN" value="$!HTATH_SEKSYEN" size="42" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">No PT / Lot</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTATH_NOPTLOT" type="text" id="HTATH_NOPTLOT" value="$!HTATH_NOPTLOT" size="20" maxlength="20" $READONLY_JKPTG style="text-transform:uppercase" />&nbsp;$selectTHJenisPTLot</td>
      <td rowspan="3" align="right" valign="top">Sekatan Kepentingan</td>
      <td rowspan="3" align="center" valign="top">:</td>
      <td rowspan="3" align="left" valign="top"><textarea name="HTATH_SEKATANKEPENTINGAN" cols="50" rows="3" id="HTATH_SEKATANKEPENTINGAN" $READONLY_JKPTG style="text-transform:uppercase">$!HTATH_SEKATANKEPENTINGAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top">No Bangunan / Tingkat / Petak</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTATH_NOBANGUNAN" type="text" id="HTATH_NOBANGUNAN" value="$!HTATH_NOBANGUNAN" size="10" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" />
       / 
      <input name="HTATH_NOTINGKAT" type="text" id="HTATH_NOTINGKAT" value="$!HTATH_NOTINGKAT" size="10" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" /> 
      / 
      <input name="HTATH_NOPETAK" type="text" id="HTATH_NOPETAK" value="$!HTATH_NOPETAK" size="10" maxlength="50" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Bahagian Si Mati</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTATH_BASIMATI" type="text" id="HTATH_BASIMATI" style="text-align:right; text-transform:uppercase" value="$!HTATH_BASIMATI" size="10" maxlength="10" $READONLY_JKPTG />
        /
        <input name="HTATH_BBSIMATI" type="text" id="HTATH_BBSIMATI" value="$!HTATH_BBSIMATI" size="10" maxlength="10" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Jenis Pegangan</td>
      <td align="center" valign="top">:</td>
      <td valign="top">$selectTHJenisPegangan</td>
      <td align="right" valign="top">Alamat Harta</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="HTATH_ALAMAT1HARTA" type="text" id="HTATH_ALAMAT1HARTA" value="$!HTATH_ALAMAT1HARTA" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Tarikh Luput Pajakan</td>
      <td align="center" valign="top">:</td>
      <td valign="top">
#if ($DISABLE_PAJAKAN != '')
        <input name="HTATH_TARIKHLUPUTPAJAKAN" type="text" id="HTATH_TARIKHLUPUTPAJAKAN" value="$!HTATH_TARIKHLUPUTPAJAKAN" size="15" maxlength="10" $READONLY_PAJAKAN style="text-transform:uppercase" />
        &nbsp;
        <img src="../img/calendar.gif" alt="" border="0" $READONLY_PAJAKAN />
#else
        <input name="HTATH_TARIKHLUPUTPAJAKAN" type="text" id="HTATH_TARIKHLUPUTPAJAKAN" value="$!HTATH_TARIKHLUPUTPAJAKAN" size="15" maxlength="10" $READONLY_PAJAKAN style="text-transform:uppercase" />&nbsp;
        <a href="javascript:displayDatePicker('HTATH_TARIKHLUPUTPAJAKAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" $READONLY_PAJAKAN /></a>
#end
      </td>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top"><input name="HTATH_ALAMAT2HARTA" type="text" id="HTATH_ALAMAT2HARTA" value="$!HTATH_ALAMAT2HARTA" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Tempoh Pajakan</td>
      <td align="center" valign="top">:</td>
      <td valign="top">
#if ($DISABLE_PAJAKAN != '')
        <input name="HTATH_TEMPOHPAJAKAN" type="text" id="HTATH_TEMPOHPAJAKAN" value="$!HTATH_TEMPOHPAJAKAN" size="10" maxlength="5" $READONLY_PAJAKAN style="text-transform:uppercase" />
#else
        <input name="HTATH_TEMPOHPAJAKAN" type="text" id="HTATH_TEMPOHPAJAKAN" value="$!HTATH_TEMPOHPAJAKAN" size="15" maxlength="5" $READONLY_PAJAKAN style="text-transform:uppercase" />
#end
      </td>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top"><input name="HTATH_ALAMAT3HARTA" type="text" id="HTATH_ALAMAT3HARTA" value="$!HTATH_ALAMAT3HARTA" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Nama Pemilik</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="HTATH_NAMAPEMILIK" type="text" id="HTATH_NAMAPEMILIK" value="$!HTATH_NAMAPEMILIK" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Tarikh Perjanjian Jual Beli</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="HTATH_TARIKHPERJANJIANJUALBELI" type="text" id="HTATH_TARIKHPERJANJIANJUALBELI" value="$!HTATH_TARIKHPERJANJIANJUALBELI" size="15" maxlength="10" $READONLY_JKPTG style="text-transform:uppercase" />
      <a href="javascript:displayDatePicker('HTATH_TARIKHPERJANJIANJUALBELI',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" $READONLY_JKPTG /></a></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Nama Pemaju</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="HTATH_NAMAPEMAJU" type="text" id="HTATH_NAMAPEMAJU" value="$!HTATH_NAMAPEMAJU" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Luas Petak (mp)</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="HTATH_LUASPETAK" type="text" id="HTATH_LUASPETAK" value="$!HTATH_LUASPETAK" size="15" maxlength="10" $READONLY_JKPTG style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Catatan</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><textarea name="HTATH_CATATAN" cols="50" rows="3" id="HTATH_CATATAN" $READONLY_JKPTG style="text-transform:uppercase">$!HTATH_CATATAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top">&nbsp;</td>
      <td align="right" valign="top">Nama Pegawai JKPTG</td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="HTATH_NAMAPEGAWAIJKPTG" type="text" id="HTATH_NAMAPEGAWAIJKPTG" value="$!HTATH_NAMAPEGAWAIJKPTG" size="50" maxlength="255" $READONLY_JKPTG style="text-transform:uppercase" /></td>
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
      <td width="34%" valign="top"><input name="HTATH_NILAITARIKHMOHON" type="text" id="HTATH_NILAITARIKHMOHON" value="$!HTATH_NILAITARIKHMOHON" size="20" maxlength="15" $READONLY_JPPH style="text-transform:uppercase" /></td>
      <td width="15%" rowspan="3" align="right" valign="top">Catatan</td>
      <td width="1%" rowspan="3" align="center" valign="top">:</td>
      <td width="34%" rowspan="3" align="left" valign="top"><textarea name="HTATH_JPPHCATATAN" cols="50" rows="3" id="HTATH_JPPHCATATAN" $READONLY_JPPH style="text-transform:uppercase">$!HTATH_JPPHCATATAN</textarea></td>
    </tr>
    
    <tr>
      <td width="15%" align="right" valign="top">Nilai Tarikh Mati (RM)</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="HTATH_NILAITARIKHMATI" type="text" id="HTATH_NILAITARIKHMATI" value="$!HTATH_NILAITARIKHMATI" size="20" maxlength="15" $READONLY_JPPH style="text-transform:uppercase" /></td>
    </tr>
    
    <tr>
      <td align="right" valign="top">Nama Pegawai</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTATH_NAMAPEGAWAI" type="text" id="HTATH_NAMAPEGAWAI" value="$!HTATH_NAMAPEGAWAI" size="50" maxlength="255" $READONLY_JPPH style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Cawangan JPPH</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTATH_CAWANGANJPPH" type="text" id="HTATH_CAWANGANJPPH" value="$!HTATH_CAWANGANJPPH" size="50" maxlength="255" $READONLY_JPPH style="text-transform:uppercase" /></td>
      <td align="right" valign="top">Tarikh Nilaian</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HTATH_TARIKHNILAIAN" type="text" id="HTATH_TARIKHNILAIAN" value="$!HTATH_TARIKHNILAIAN" size="15" maxlength="10" $READONLY_JPPH style="text-transform:uppercase" />
        <a href="javascript:displayDatePicker('HTATH_TARIKHNILAIAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" $READONLY_PAJAKAN /></a> </td>
    </tr>
    <tr>
      <td colspan="6" align="right" valign="top">&nbsp;</td>
    </tr>
  </table>  
</fieldset>
<br />
<div style="text-align:center">
  <input id="cmdKembali" name="cmdKembali" type="button" value="Kembali" onclick="backNilaianHTA();" />&nbsp;
  <input id="cmdCetakMaklumat" name="cmdCetakMaklumat" type="button" value="Cetak Maklumat" onclick="printNilaianHTA('2', '$ID_HTATH');" />&nbsp;
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