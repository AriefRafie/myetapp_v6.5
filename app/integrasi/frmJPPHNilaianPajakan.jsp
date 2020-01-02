<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
#if ($sendMaklumat == 'true')
<fieldset>
	#if ($isJPPHUser == 'true')
  <div class="success">Maklumat nilaian pajakan tanah persekutuan telah berjaya dihantar ke JKPTG.</div>    
        #set ($READONLY_FOR_JKPTG = 'true')
    #else
  <div class="success">Permohonan mendapatkan nilaian pajakan tanah persekutuan telah berjaya dihantar ke JPPH.</div>    
    #end
</fieldset>
#elseif ($saveMaklumat == 'true')
	#if ($isJPPHUser == 'true')
<fieldset>
  <div class="success">Maklumat nilaian pajakan tanah persekutuan telah berjaya disimpan.</div>    
</fieldset>
	#end
#end
#if ($READONLY_FOR_JPPH != '')
	#set ($READONLY_FOR_JPPH = ' readonly="readonly" class="disabled"')
	#set ($DISABLE_FOR_JPPH = ' disabled="disabled" class="disabled"')
#end
#if ($READONLY_FOR_JKPTG != '')
	#set ($READONLY_FOR_JKPTG = ' readonly="readonly" class="disabled"')
	#set ($DISABLE_FOR_JKPTG = ' disabled="disabled" class="disabled"')
#end
<fieldset>
  <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
  <br />
  <table width="100%" border="0" align="center">
    <tr>
      <td width="25%" align="right" valign="top" nowrap="nowrap"><strong>NO FAIL</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="74%" align="left" valign="top" nowrap="nowrap" class="link">$MP_NOFAIL</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NEGERI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NEGERI</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>DAERAH / JAJAHAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_DAERAH</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>UNIT</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_UNITJKPTG</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NAMA PEGAWAI JKPTG</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link"><input name="MP_NAMAPEGAWAIJKPTG" type="text" id="MP_NAMAPEGAWAIJKPTG" onkeyup="this.value=this.value.toUpperCase();" value="$!MP_NAMAPEGAWAIJKPTG" size="50" maxlength="255" $READONLY_FOR_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO TEL JKPTG</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link"><input name="MP_NOTELJKPTG" type="text" id="MP_NOTELJKPTG" onkeyup="this.value=this.value.toUpperCase();" value="$!MP_NOTELJKPTG" size="50" maxlength="255" $READONLY_FOR_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>CAWANGAN JKPTG</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link"><input name="MP_CAWANGANJKPTG" type="text" id="MP_CAWANGANJKPTG" onkeyup="this.value=this.value.toUpperCase();" value="$!MP_CAWANGANJKPTG" size="50" maxlength="255" $READONLY_FOR_JPPH /></td>
    </tr>
    <tr>
      <td colspan="6" align="right" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>MAKLUMAT HARTA</strong>
  </legend><table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>NEGERI</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap">$MH_NEGERI</td>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>KATEGORI TANAH</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap">$MH_KATEGORITANAH</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>DAERAH</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$MH_DAERAH</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>SYARAT NYATA</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$MH_SYARATNYATA</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>MUKIM / BANDAR</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$MH_MUKIM</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>SEKATAN KEPENTINGAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$MH_SEKATANKEPENTINGAN</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>SEKSYEN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="MH_SEKSYEN" type="text" id="MH_SEKSYEN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_SEKSYEN" size="20" maxlength="10" $READONLY_FOR_JPPH /></td>
      <td align="right" valign="top" nowrap="nowrap"><strong>LUAS ASAL</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="MH_LUASASAL" type="text" id="MH_LUASASAL" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_LUASASAL" size="10" maxlength="10" $READONLY_FOR_JPPH />
  &nbsp;$MH_JENISLUASASAL</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>JENIS HAKMILIK</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$MH_JENISHAKMILIK</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>LUAS TANAH DIPAJAK</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="MH_LUASDIPAJAK" type="text" id="MH_LUASDIPAJAK" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_LUASDIPAJAK" size="10" maxlength="10" $READONLY_FOR_JPPH />
  &nbsp;$MH_JENISLUASDIPAJAK</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO HAKMILIK</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="MH_NOHAKMILIK" type="text" id="MH_NOHAKMILIK" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_NOHAKMILIK" size="20" maxlength="10" $READONLY_FOR_JPPH /></td>
      <td align="right" valign="top" nowrap="nowrap"><strong>STATUS PEMILIKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="MH_STATUSPEMILIKAN" type="text" id="MH_STATUSPEMILIKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_STATUSPEMILIKAN" size="30" maxlength="255" $READONLY_FOR_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO PT / LOT</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="MH_NOPTLOT" type="text" id="MH_NOPTLOT" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_NOPTLOT" size="20" maxlength="20" $READONLY_FOR_JPPH />
  &nbsp;$MH_JENISPTLOT</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>TUJUAN PAJAKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="MH_TUJUANPAJAKAN" type="text" id="MH_TUJUANPAJAKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_TUJUANPAJAKAN" size="30" maxlength="255" $READONLY_FOR_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO PAJAKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="MH_NOPAJAKAN" type="text" id="MH_NOPAJAKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_NOPAJAKAN" size="20" maxlength="20" $READONLY_FOR_JPPH /></td>
      <td align="right" valign="top" nowrap="nowrap"><strong>NILAIAN YANG DIPERLUKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input type="radio" id="MH_NILAIPAJAK" name="MH_NILAIPAJAK" value="MH_PAJAK99" onchange="doChange('MH_PAJAK99', 'false');" $MH_PAJAK99 $DISABLE_FOR_JPPH />&nbsp;Pajakan 99 Tahun
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO PERSERAHAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="MH_NOPERSERAHAN" type="text" id="MH_NOPERSERAHAN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_NOPERSERAHAN" size="20" maxlength="20" $READONLY_FOR_JPPH /></td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input type="radio" id="MH_NILAIPAJAK" name="MH_NILAIPAJAK" value="MH_PAJAK60" onchange="doChange('MH_PAJAK60', 'false');" $MH_PAJAK60 $DISABLE_FOR_JPPH />&nbsp;Pajakan 60 Tahun</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>JENIS PEGANGAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$MH_JENISPEGANGAN</td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input type="radio" id="MH_NILAIPAJAK" name="MH_NILAIPAJAK" value="MH_PAJAK30" onchange="doChange('MH_PAJAK30', 'false');" $MH_PAJAK30 $DISABLE_FOR_JPPH />&nbsp;Pajakan 30 Tahun
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>TARIKH LUPUT PAJAKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
#if ($READONLY_FOR_JENISPEGANGAN == '')    
        <input name="MH_TARIKHLUPUTPAJAKAN" type="text" id="MH_TARIKHLUPUTPAJAKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_TARIKHLUPUTPAJAKAN" size="15" maxlength="10" />&nbsp;<a href="javascript:displayDatePicker('MH_TARIKHLUPUTPAJAKAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
#else
        <input name="MH_TARIKHLUPUTPAJAKAN" type="text" id="MH_TARIKHLUPUTPAJAKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_TARIKHLUPUTPAJAKAN" size="15" maxlength="10" $READONLY_FOR_JPPH />&nbsp;<img src="../img/calendar.gif" alt="" border="0" />
#end
      </td>  
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input type="radio" id="MH_NILAIPAJAK" name="MH_NILAIPAJAK" value="MH_PAJAKLAINLAIN" onchange="doChange('MH_PAJAKLAINLAIN', 'false');" $MH_PAJAKLAINLAIN $DISABLE_FOR_JPPH />&nbsp;Lain-lain Tempoh: 
#if ($READONLY_FOR_JPPH == '')
	#if ($command == 'MH_PAJAKLAINLAIN')
        <input name="MH_PAJAKLAINLAINTAHUN" type="text" id="MH_PAJAKLAINLAINTAHUN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_PAJAKLAINLAINTAHUN" size="5" maxlength="5" />
        Tahun
	#else
        <input name="MH_PAJAKLAINLAINTAHUN" type="text" id="MH_PAJAKLAINLAINTAHUN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_PAJAKLAINLAINTAHUN" size="5" maxlength="3" class="disabled" disabled="disabled" /> Tahun
    #end
#else
        <input name="MH_PAJAKLAINLAINTAHUN" type="text" id="MH_PAJAKLAINLAINTAHUN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_PAJAKLAINLAINTAHUN" size="5" maxlength="3" class="disabled" disabled="disabled" /> Tahun
#end
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>TEMPOH PAJAKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
#if ($READONLY_FOR_JENISPEGANGAN == '' && $isJPPHUser != 'true')        
        <input name="MH_TEMPOHPAJAKAN" type="text" id="MH_TEMPOHPAJAKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_TEMPOHPAJAKAN" size="15" maxlength="5" />
#else
        <input name="MH_TEMPOHPAJAKAN" type="text" id="MH_TEMPOHPAJAKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!MH_TEMPOHPAJAKAN" size="15" maxlength="5" $READONLY_FOR_JPPH />
#end
      </td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input type="radio" id="MH_NILAIPAJAK" name="MH_NILAIPAJAK" value="MH_PAJAKTAHUNAN" onchange="doChange('MH_PAJAKTAHUNAN', 'false');" $MH_PAJAKTAHUNAN $DISABLE_FOR_JPPH />&nbsp;Sewa (Pajakan) Tahunan</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">
        <input type="checkbox" name="MH_ADAPELANTAPAK" id="MH_ADAPELANTAPAK" value="1" $MH_ADAPELANTAPAK $DISABLE_FOR_JPPH $READONLY_FOR_JPPH />
      </td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><strong>ADA LAMPIRAN PELAN TAPAK</strong></td>
      <td align="right" valign="top" nowrap="nowrap"><strong>CATATAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><textarea name="MH_CATATAN" cols="30" rows="3" id="MH_CATATAN" onkeyup="this.value=this.value.toUpperCase();" $READONLY_FOR_JPPH>$!MH_CATATAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
  </table>  
</fieldset>
<br />
#if ($isJPPHUser != 'true')
<fieldset>
  <legend><strong>E-MAIL</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr>
      <td style="text-align:right"><strong>ALAMAT E-MEL PEGAWAI JPPH 1</strong>: </td>
      <td><input name="EMAIL_ADDR1" type="text" id="EMAIL_ADDR1" value="$!EMAIL_ADDR1" size="50" maxlength="255" $READONLY_FOR_JPPH /></td>
    </tr>
    <tr>
      <td style="text-align:right"><strong>ALAMAT E-MEL PEGAWAI JPPH 2</strong>: </td>
      <td><input name="EMAIL_ADDR2" type="text" id="EMAIL_ADDR2" value="$!EMAIL_ADDR2" size="50" maxlength="255" $READONLY_FOR_JPPH /></td>
    </tr>
    <tr>
      <td width="30%" style="text-align:right"><strong>ALAMAT E-MEL PEGAWAI JPPH 3</strong>: </td>
      <td><input name="EMAIL_ADDR3" type="text" id="EMAIL_ADDR3" value="$!EMAIL_ADDR3" size="50" maxlength="255" $READONLY_FOR_JPPH /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="checkbox" id="EMAIL_SEND" name="EMAIL_SEND" value="1" $EMAIL_SEND $DISABLE_FOR_JPPH /> 
      Sila &radic; jika anda ingin menghantar e-mel pemberitahuan kepada pegawai penilai JPPH bagi permohonan ini.</td>
    </tr>
  </table>
</fieldset>
<br />
#end
<fieldset>
  <legend><strong>JPPH</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top"><strong>TARIKH NILAIAN</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top">
#if ($READONLY_FOR_JKPTG == '')
        <input name="JPPH_TARIKHNILAIAN" type="text" id="JPPH_TARIKHNILAIAN" value="$!JPPH_TARIKHNILAIAN" size="20" maxlength="15" $READONLY_FOR_JKPTG /><a href="javascript:displayDatePicker('JPPH_TARIKHNILAIAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
#else
        <input name="JPPH_TARIKHNILAIAN" type="text" id="JPPH_TARIKHNILAIAN" value="" size="20" maxlength="15" $READONLY_FOR_JKPTG /><img src="../img/calendar.gif" alt="" border="0" />
#end      </td>
      <td width="15%" rowspan="4" align="right" valign="top"><strong>CATATAN</strong></td>
      <td width="1%" rowspan="4" align="center" valign="top">:</td>
      <td width="34%" rowspan="4" valign="top">
        <textarea name="JPPH_CATATAN" cols="30" rows="10" id="JPPH_CATATAN" onkeyup="this.value=this.value.toUpperCase();" $READONLY_FOR_JKPTG>$!JPPH_CATATAN</textarea>      </td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NILAI PAJAKAN 99 TAHUN (RM)</strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="JPPH_NILAIPAJAK99" type="text" id="JPPH_NILAIPAJAK99" value="$!JPPH_NILAIPAJAK99" onkeyup="this.value=this.value.toUpperCase();" size="20" maxlength="15" $READONLY_FOR_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NILAI PAJAKAN 60 TAHUN (RM)</strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="JPPH_NILAIPAJAK60" type="text" id="JPPH_NILAIPAJAK60" value="$!JPPH_NILAIPAJAK60" onkeyup="this.value=this.value.toUpperCase();" size="20" maxlength="15" $READONLY_FOR_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NILAI PAJAKAN 30 TAHUN (RM)</strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="JPPH_NILAIPAJAK30" type="text" id="JPPH_NILAIPAJAK30" value="$!JPPH_NILAIPAJAK30" onkeyup="this.value=this.value.toUpperCase();" size="20" maxlength="15" $READONLY_FOR_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NILAI PAJAKAN $!MH_PAJAKLAINLAINTAHUN TAHUN (RM)</strong></td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top"><input name="JPPH_NILAIPAJAKLAINLAIN" type="text" id="JPPH_NILAIPAJAKLAINLAIN" value="$!JPPH_NILAIPAJAKLAINLAIN" onkeyup="this.value=this.value.toUpperCase();" size="20" maxlength="15" $READONLY_FOR_JKPTG /></td>
      <td align="right" valign="top"><strong>NAMA PEGAWAI</strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="JPPH_NAMAPEGAWAI" type="text" id="JPPH_NAMAPEGAWAI" value="$!JPPH_NAMAPEGAWAI" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="255" $READONLY_FOR_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NILAI SEWA (PAJAKAN) TAHUNAN (RM)</strong></td>
      <td align="center" valign="top">&nbsp;</td>
      <td valign="top"><input name="JPPH_NILAIPAJAKTAHUNAN" type="text" id="JPPH_NILAIPAJAKTAHUNAN" value="$!JPPH_NILAIPAJAKTAHUNAN" onkeyup="this.value=this.value.toUpperCase();" size="20" maxlength="15" $READONLY_FOR_JKPTG /></td>
      <td align="right" valign="top"><strong>CAWANGAN JPPH</strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="JPPH_CAWANGAN" type="text" id="JPPH_CAWANGAN" value="$!JPPH_CAWANGAN" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="255" $READONLY_FOR_JKPTG /></td>
    </tr>
    <tr>
      <td colspan="6" align="right" valign="top">&nbsp;</td>
    </tr>
  </table>  
</fieldset>
<br />
<div style="text-align:center">
  <input type="button" id="cmdBack" name="cmdBack" value="Kembali" onclick="backMaklumat();" />&nbsp;
  <input type="button" id="cmdPrint" name="cmdPrint" value="Cetak Maklumat" onclick="printMaklumat();" />&nbsp;
#if ($isJPPHUser != 'true')
	#if ($isCompleted != 'true')
	    #if ($hasMaklumat == 'true' || $action2 == 'edit')
  <input type="button" id="cmdSendToJPPH" name="cmdSendToJPPH" value="Hantar ke JPPH" onclick="sendMaklumat();" />&nbsp;
    	#end
        #if ($action2 != 'edit')
  <input type="button" id="cmdEdit" name="cmdEdit" value="Kemaskini" onclick="editMaklumat();" />&nbsp;
  		#end
	    #if ($hasMaklumat == 'true')
  <input type="button" id="cmdCancel" name="cmdCancel" value="Batalkan Permohonan Nilaian JPPH" onclick="cancelMaklumat();" />
    	#end
    #end
#elseif ($isJPPHUser == 'true')
	#if ($isCompleted != 'true')
  <input type="button" id="cmdSave" name="cmdSave" value="Simpan" onclick="saveMaklumat();" />
  <input type="button" id="cmdSendToJKPTG" name="cmdSendToJKPTG" value="Hantar ke JKPTG" onclick="sendMaklumat();" />&nbsp;
  <input type="button" id="cmdReturn" name="cmdReturn" value="Dikembalikan" onclick="returnMaklumat();" />&nbsp;
	#end  
#end  
</div>
<br />
<fieldset>
  <legend><strong>SENARAI HARTA</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="15%"><strong>NEGERI</strong></td>
      <td width="20%"><strong>DAERAH</strong></td>
      <td width="20%"><strong>MUKIM</strong></td>
      <td width="15%"><strong>NO HAKMILIK</strong></td>
      <td width="20%"><strong>NO PT / LOT</strong></td>
    </tr>
	#set ($list = '')
	#foreach ($list in $ListHM)
		#if ($list.No == '') 
	    	#set ($row = 'row1')
	    #elseif ($list.No % 2 != 0)
	    	#set ($row = 'row1')
	    #else 
	    	#set ($row = 'row2')
    	#end
    <tr>
	    #if ($list.No != '') 
      <td class="$row" valign="top" align="center">$list.No</td>
      <td class="$row" valign="top"><a href="javascript:viewMaklumat('$list.IDHM')" class="link">$list.Negeri</a></td>
      <td class="$row" valign="top">$list.Daerah</td>
      <td class="$row" valign="top">$list.Mukim</td>
      <td class="$row" valign="top">$list.NoHakmilik</td>
      <td class="$row" valign="top">$list.NoPTLot</td>
    	#else
    <td colspan="6" class="$row" style="text-align:center">Tiada Rekod</td>
	    #end
    </tr>
	#end
    <tr>
      <td colspan="6">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="ID_HM" name="ID_HM" value="$ID_HM" />
<input type="hidden" id="MH_NILAIPAJAK2" name="MH_NILAIPAJAK2" value="$MH_NILAIPAJAK2" />
<input type="hidden" id="action2" name="action2" value="$action2" />
<script type="text/javascript">
  function doChange(CMD, IS_SOC) {
      if (IS_SOC != 'true') {
          document.${formName}.MH_NILAIPAJAK2.value = CMD;
      }
	  document.${formName}.action2.value = "edit";
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=edit";
	  doAjaxCall${formName}(CMD);
  }
  function editMaklumat() {
	  document.${formName}.action2.value = "edit";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=edit";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function saveMaklumat() {
      if (!window.confirm("Adakah anda pasti?")) return;
	  document.${formName}.action2.value = "save";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=save";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function viewMaklumat() {
	  document.${formName}.action2.value = "view";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=view";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function sendMaklumat() {
      if (!window.confirm("Adakah anda pasti?")) return;
	  document.${formName}.action2.value = "send";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=send";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function cancelMaklumat() {
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action2.value = "cancel";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=cancel";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function backMaklumat() {
	#if ($isJPPHUser == 'true')
      document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)??_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih";
	#else
      document.${formName}.action = "$EkptgUtil.getTabID("Pajakan",$portal_role)??_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&action2=cancel&idFail=$!idFail&idPermohonan=$!idPermohonan&idStatus=6";
	#end  
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function printMaklumat() {
      var url = "../servlet/ekptg.report.integrasi.ReportJPPH?reportType=PDF&rt=5&ID_PERMOHONAN=$ID_PERMOHONAN&ID_HM=$ID_HM";
      var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
      if ((document.window != null) && (!hWnd.opener))
      hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();
  }
</script>