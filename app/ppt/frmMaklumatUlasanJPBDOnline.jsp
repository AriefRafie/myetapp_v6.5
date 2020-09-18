<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
#if ($saveBorangLampiranA1 == 'true')
	#if ($sendBorangLampiranA1 == 'true')
    	#if ($isJPBDUser == 'true')
<div class="success">Ulasan telah berjaya dihantar ke JKPTG.</div>
		#else
<div class="success">Permohonan bagi mendapatkan ulasan telah berjaya dihantar ke JPBD.</div>
        #end
	#else
    	#if ($isJPBDUser == 'true')
			#if ($verifyBorangLampiranA1 == 'true')
<div class="success">Ulasan telah berjaya disimpan dan sedang menunggu pengesahan pegawai.</div>
			#else
<div class="success">Ulasan telah berjaya disimpan.</div>
			#end
		#end
    #end
<br />
<br />
#end
#if ($doDeleteFile == 'true')
	#if ($deleteFile == 'true')
<div class="success">Fail lampiran berjaya dihapuskan.</div>
    #else
<div class="error">Fail lampiran tidak berjaya dihapuskan. Sila cuba sebentar lagi.</div>
    #end
<br />
#end
#if ($READONLY_JPBD != '')
	#set ($JPBD_CLASS_DISABLED = 'class="disabled"')
#end
#if ($sendBorangLampiranA1 == 'true')
    #if ($isJPBDUser == 'true')
    	#set ($READONLY_JPBD = ' readonly="readonly" class="disabled" ')
    	#set ($DISABLE_JPBD = ' disabled="disabled" class="disabled" ')
		#set ($JPBD_CLASS_DISABLED = ' class="disabled" ')
    #end
#end
<fieldset>
  <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
  <br />
  <table width="70%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>NO FAIL</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="84%" align="left" valign="top" nowrap="nowrap" class="link">$MP_NOFAIL</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO PERMOHONAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NOPERMOHONAN</td>
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
      <td align="right" valign="top" nowrap="nowrap"><strong>TAJUK PERMOHONAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link"><textarea name="MP_TAJUKPERMOHONAN" cols="50" rows="3" id="MP_TAJUKPERMOHONAN" readonly="readonly" style="text-transform:uppercase" class="disabled">$MP_TAJUKPERMOHONAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>Jabatan Perancangan Bandar &amp; Desa (JPBD)</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap"><strong>No Rujukan JPBD</strong></td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap"><input name="JPBD_NORUJUKANJPBD" type="text" id="JPBD_NORUJUKANJPBD" value="$!JPBD_NORUJUKANJPBD" size="50" maxlength="255" style="text-transform:uppercase" $READONLY_JPBD $JPBD_CLASS_DISABLED /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap"><strong>No Warta</strong></td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap"><input name="JPBD_NOWARTA" type="text" id="JPBD_NOWARTA" value="$!JPBD_NOWARTA" size="50" maxlength="255" style="text-transform:uppercase" $READONLY_JPBD $JPBD_CLASS_DISABLED /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    <tr>
      <td width="3%" align="right" valign="top" nowrap="nowrap">1.</td>
      <td colspan="4" valign="top" nowrap="nowrap">Adakah tanah tersebut terletak di dalam kawasan Pihak Berkuasa Perancangan Tempatan?</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td width="3%" align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td width="14%" align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">Tarikh Dilulus / Diwarta</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input type="radio" id="JPBD_DALAMKAWASANPBPT1" name="JPBD_DALAMKAWASANPBPT" $!JPBD_DALAMKAWASANPBPT1 value="1" $DISABLE_JPBD /> Ya</td>
      <td width="19%" align="left" valign="top" nowrap="nowrap"><input type="checkbox" id="JPBD_ADAPELANSTRUKTUR" name="JPBD_ADAPELANSTRUKTUR" value="1" $JPBD_ADAPELANSTRUKTUR $DISABLE_JPBD /> Pelan Struktur</td>
      <td width="61%" align="left" valign="top" nowrap="nowrap">
#if ($READONLY_JPBD != '')
        <input name="JPBD_TARIKHLULUSPELANSTRUKTUR" type="text" id="JPBD_TARIKHLULUSPELANSTRUKTUR" value="$!JPBD_TARIKHLULUSPELANSTRUKTUR" size="15" maxlength="10" $READONLY_JPBD $JPBD_CLASS_DISABLED />
#else
        <input name="JPBD_TARIKHLULUSPELANSTRUKTUR" type="text" id="JPBD_TARIKHLULUSPELANSTRUKTUR" value="$!JPBD_TARIKHLULUSPELANSTRUKTUR" size="15" maxlength="10" $READONLY_JPBD $JPBD_CLASS_DISABLED />
        &nbsp;<a href="javascript:displayDatePicker('JPBD_TARIKHLULUSPELANSTRUKTUR',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
#end
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input type="radio" id="JPBD_DALAMKAWASANPBPT0" name="JPBD_DALAMKAWASANPBPT" $!JPBD_DALAMKAWASANPBPT0 value="0" $DISABLE_JPBD /> Tidak</td>
      <td align="left" valign="top" nowrap="nowrap"><input type="checkbox" id="JPBD_ADAPELANTEMPATAN" name="JPBD_ADAPELANTEMPATAN" value="1" $JPBD_ADAPELANTEMPATAN $DISABLE_JPBD /> Pelan Tempatan</td>
      <td align="left" valign="top" nowrap="nowrap">
#if ($READONLY_JPBD != '')
        <input name="JPBD_TARIKHLULUSPELANTEMPATAN" type="text" id="JPBD_TARIKHLULUSPELANTEMPATAN" value="$!JPBD_TARIKHLULUSPELANTEMPATAN" size="15" maxlength="10" $READONLY_JPBD $JPBD_CLASS_DISABLED />
#else
        <input name="JPBD_TARIKHLULUSPELANTEMPATAN" type="text" id="JPBD_TARIKHLULUSPELANTEMPATAN" value="$!JPBD_TARIKHLULUSPELANTEMPATAN" size="15" maxlength="10" $READONLY_JPBD $JPBD_CLASS_DISABLED />
        &nbsp;<a href="javascript:displayDatePicker('JPBD_TARIKHLULUSPELANTEMPATAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
#end
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="3" align="left" valign="top" nowrap="nowrap" class="mandatori">Sila isikan sama ada medan a) atau b) atau kedua-duanya sekali</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td width="3%" align="left" valign="top" nowrap="nowrap">a)</td>
      <td colspan="3" align="left" valign="top" nowrap="nowrap">Jika YA, apakah kegunaan tanah terlibat sepertimana dinyatakan di dalam pelan pembangunan dan apakah zon kegunaan tanah terlibat?</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="3" align="left" valign="top" nowrap="nowrap"><textarea name="JPBD_KEGUNAANTANAH" cols="100" rows="5" id="JPBD_KEGUNAANTANAH" $READONLY_JPBD $JPBD_CLASS_DISABLED style="text-transform:uppercase">$!JPBD_KEGUNAANTANAH</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">b)</td>
      <td colspan="3" align="left" valign="top" nowrap="nowrap">Jika TIDAK, apakah potensi pembangunan dari sudut gunatanah?</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="3" align="left" valign="top" nowrap="nowrap"><textarea name="JPBD_POTENSIPEMBANGUNAN" cols="100" rows="5" id="JPBD_POTENSIPEMBANGUNAN" $READONLY_JPBD $JPBD_CLASS_DISABLED style="text-transform:uppercase">$!JPBD_POTENSIPEMBANGUNAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">c)</td>
      <td colspan="3" align="left" valign="top" nowrap="nowrap">Nama Pihak Berkuasa Tempatan:&nbsp;</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="3" align="left" valign="top" nowrap="nowrap"><input name="JPBD_NAMAPBT" type="text" id="JPBD_NAMAPBT" value="$!JPBD_NAMAPBT" size="100" maxlength="255" $READONLY_JPBD $JPBD_CLASS_DISABLED style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">2.</td>
      <td colspan="4" align="left" valign="top" nowrap="nowrap">Apakah status kelulusan di sekitar lot yang terlibat?</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="3" align="left" valign="top" nowrap="nowrap"><textarea name="JPBD_STATUSKELULUSAN" cols="100" rows="5" id="JPBD_STATUSKELULUSAN" $READONLY_JPBD $JPBD_CLASS_DISABLED style="text-transform:uppercase">$!JPBD_STATUSKELULUSAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">3.</td>
      <td align="left" valign="top" nowrap="nowrap">a)</td>
      <td colspan="3" align="left" valign="top" nowrap="nowrap">Adakah terdapat apa-apa permohonan untuk memajukan tanah tersebut?</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="3" align="left" valign="top" nowrap="nowrap"><input type="radio" id="JPBD_PERMOHONANMEMAJUKANTANAH1" name="JPBD_PERMOHONANMEMAJUKANTANAH" value="1" $JPBD_PERMOHONANMEMAJUKANTANAH1 $DISABLE_JPBD />
      Ya <input type="radio" id="JPBD_PERMOHONANMEMAJUKANTANAH2" name="JPBD_PERMOHONANMEMAJUKANTANAH" value="0" $JPBD_PERMOHONANMEMAJUKANTANAH0 $DISABLE_JPBD /> Tidak</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">b)</td>
      <td colspan="3" align="left" valign="top" nowrap="nowrap">Sekiranya ada permohonan:</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">i) Tujuan permohonan:</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap"><textarea name="JPBD_TUJUANPERMOHONAN" cols="65" rows="5" id="JPBD_TUJUANPERMOHONAN" $READONLY_JPBD $JPBD_CLASS_DISABLED style="text-transform:uppercase">$!JPBD_TUJUANPERMOHONAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">ii) Tarikh diluluskan / ditolak:</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap">
#if ($READONLY_JPBD != '')
        <input name="JPBD_TARIKHLULUSTOLAK" type="text" id="JPBD_TARIKHLULUSTOLAK" value="$!JPBD_TARIKHLULUSTOLAK" size="15" maxlength="10" $READONLY_JPBD $JPBD_CLASS_DISABLED />
#else
        <input name="JPBD_TARIKHLULUSTOLAK" type="text" id="JPBD_TARIKHLULUSTOLAK" value="$!JPBD_TARIKHLULUSTOLAK" size="15" maxlength="10" $READONLY_JPBD $JPBD_CLASS_DISABLED />
        <a href="javascript:displayDatePicker('JPBD_TARIKHLULUSTOLAK',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
#end      
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">iii) Tarikh luput kelulusan:</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap">
#if ($READONLY_JPBD != '')
        <input name="JPBD_TARIKHLUPUTKELULUSAN" type="text" id="JPBD_TARIKHLUPUTKELULUSAN" value="$!JPBD_TARIKHLUPUTKELULUSAN" size="15" maxlength="10" $READONLY_JPBD $JPBD_CLASS_DISABLED />
#else
        <input name="JPBD_TARIKHLUPUTKELULUSAN" type="text" id="JPBD_TARIKHLUPUTKELULUSAN" value="$!JPBD_TARIKHLUPUTKELULUSAN" size="15" maxlength="10" $READONLY_JPBD $JPBD_CLASS_DISABLED />
        <a href="javascript:displayDatePicker('JPBD_TARIKHLUPUTKELULUSAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
#end      
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">c)</td>
      <td align="left" valign="top" nowrap="nowrap">Lain-lain: </td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap"><textarea name="JPBD_CATATANLAIN" cols="65" rows="5" id="JPBD_CATATANLAIN" $READONLY_JPBD $JPBD_CLASS_DISABLED style="text-transform:uppercase">$!JPBD_CATATANLAIN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="4" align="left" valign="top" nowrap="nowrap"><span class="mandatori">Sila isikan Nama Pegawai Perancang Bandar, Jawatan, Tarikh dan Jabatan jika ingin menghantar kembali permohonan ke JKPTG</span></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><span class="mandatori">*</span></td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap">Nama Pegawai Perancang Bandar:</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap"><input name="JPBD_NAMAPEGAWAIJPBD" type="text" id="JPBD_NAMAPEGAWAIJPBD" value="$!JPBD_NAMAPEGAWAIJPBD" size="100" maxlength="255" $READONLY_JPBD $JPBD_CLASS_DISABLED style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap">Jawatan:</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap"><input name="JPBD_JAWATANPEGAWAIJPBD" type="text" id="JPBD_JAWATANPEGAWAIJPBD" value="$!JPBD_JAWATANPEGAWAIJPBD" size="100" maxlength="255" $READONLY_JPBD $JPBD_CLASS_DISABLED style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="4" align="left" valign="top" nowrap="nowrap"><input type="checkbox" id="JPBD_TANDATANGANBAGIPIHAK" name="JPBD_TANDATANGANBAGIPIHAK" value="1" $JPBD_TANDATANGANBAGIPIHAK $DISABLE_JPBD onclick="togglePegawaiAsal();" /> 
      Sila &radic; jika menandatangani bagi pihak pegawai lain</td>
    </tr>
#if ($JPBD_TANDATANGANBAGIPIHAK == '')
    <tr id="JPBD_STYLE_NAMAPEGAWAIASAL" style="visibility:collapse">
#else    
    <tr id="JPBD_STYLE_NAMAPEGAWAIASAL" style="visibility:visible">
#end
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap">Nama Pegawai Perancang Bandar Asal:</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap"><input name="JPBD_NAMAPEGAWAIASAL" type="text" id="JPBD_NAMAPEGAWAIASAL" value="$!JPBD_NAMAPEGAWAIASAL" size="100" maxlength="255" $READONLY_JPBD $JPBD_CLASS_DISABLED style="text-transform:uppercase" /></td>
    </tr>
#if ($JPBD_TANDATANGANBAGIPIHAK == '')
    <tr id="JPBD_STYLE_JAWATANPEGAWAIASAL" style="visibility:collapse">
#else    
    <tr id="JPBD_STYLE_JAWATANPEGAWAIASAL" style="visibility:visible">
#end
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap">Jawatan:</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap"><input name="JPBD_JAWATANPEGAWAIASAL" type="text" id="JPBD_JAWATANPEGAWAIASAL" value="$!JPBD_JAWATANPEGAWAIASAL" size="100" maxlength="255" $READONLY_JPBD $JPBD_CLASS_DISABLED style="text-transform:uppercase" /></td>
    </tr>
    
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap">Tarikh:</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap">
        #if ($READONLY_JPBD != '')
        <input name="JPBD_TARIKHPERMOHONAN" type="text" id="JPBD_TARIKHPERMOHONAN" value="$!JPBD_TARIKHPERMOHONAN" size="15" maxlength="10" $READONLY_JPBD $JPBD_CLASS_DISABLED />
        #else
        <input name="JPBD_TARIKHPERMOHONAN" type="text" id="JPBD_TARIKHPERMOHONAN" value="$!JPBD_TARIKHPERMOHONAN" size="15" maxlength="10" $READONLY_JPBD $JPBD_CLASS_DISABLED />
        <a href="javascript:displayDatePicker('JPBD_TARIKHPERMOHONAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
        #end      
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap">Jabatan:</td>
      <td colspan="2" align="left" valign="top" nowrap="nowrap"><input name="JPBD_JABATAN" type="text" id="JPBD_JABATAN" value="$!JPBD_JABATAN" size="100" maxlength="255" $READONLY_JPBD $JPBD_CLASS_DISABLED style="text-transform:uppercase" /></td>
    </tr>
    <tr>
      <td colspan="5">&nbsp;</td>
    </tr>
  </table>  
</fieldset>
<br />
<fieldset>
  <legend><strong>LAMPIRAN</strong></legend>
  <table width="100%">
    <tr>
      <td width="30%" align="right" valign="top" scope="row">Fail untuk Dimuatnaik</td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="UPLOAD_FILE" type="file" id="UPLOAD_FILE" style="font-family:Verdana, Geneva, sans-serif" size="50" /></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row"><input id="cmdCari" name="cmdCari" type="button" value="Muat Naik" onclick="uploadFile();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyForm();" /></td>
    </tr> 
  </table>
  <br />
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="35%"><strong>NAMA FAIL</strong></td>
      <td width="20%"><strong>JENIS MIME</strong></td>
      <td width="20%"><strong>TARIKH MUATNAIK</strong></td>
      <td width="10%"><strong></strong></td>
      <td width="10%"><strong></strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListLampiran)
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
      <td class="$row" valign="top">$list.NamaFail</td>
      <td class="$row" valign="top">$list.JenisMIME</td>
      <td class="$row" valign="top">$list.TarikhMuatNaik</td>
      <td class="$row" valign="top"><input type="button" id="cmdPaparFail" name="cmdPaparFail" value="Papar Fail" onclick="displayFile($list.IDDokumen);" /></td>
      <td class="$row" valign="top"><input type="button" id="cmdHapusFail" name="cmdHapusFail" value="Hapus Fail" onclick="deleteFile($list.IDDokumen);" /></td>
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
<div style="text-align:center">
  <input type="button" id="cmdBack" name="cmdBack" value="Kembali" onclick="backBorangLampiranA1();" />&nbsp;
<!--  <input type="button" id="cmdPrintBorangLampiranA1" name="cmdPrintBorangLampiranA1" value="Cetak Borang Lampiran A1" onclick="printBorangLampiranA1();" />&nbsp; -->
#if ($isJPBDUser == 'true')
	#if ($sendBorangLampiranA1 != 'true')
  <input type="button" id="cmdSave" name="cmdSave" value="Simpan" onclick="saveBorangLampiranA1();" />&nbsp;
		#if ($isPegawaiJPBD == 'true')  
  <input type="button" id="cmdSend" name="cmdSend" value="Hantar ke JKPTG" onclick="sendBorangLampiranA1();" />&nbsp;
		#else  
  <input type="button" id="cmdVerify" name="cmdVerify" value="Mohon Pengesahan Pegawai" onclick="verifyBorangLampiranA1();" />&nbsp;
  		#end
	#end
#else
<!--  <input type="button" id="cmdPrintSuratIringan" name="cmdPrintSuratIringan" value="Cetak Surat Iringan" onclick="printSuratIringan('JKPTG');" />&nbsp; -->
  <input type="button" id="cmdSave" name="cmdSave" value="Hantar ke JPBD" onclick="sendBorangLampiranA1();" />&nbsp;
	#if ($haveINTData == 'true')
  <input type="button" id="cmdDelete" name="cmdDelete" value="Batalkan Permohonan JPBD" onclick="deleteBorangLampiranA1();" />
	#end
#end
</div>
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="action2" name="action2" value="$action2" />
<script type="text/javascript">
  function togglePegawaiAsal() {
      if (document.getElementById('JPBD_TANDATANGANBAGIPIHAK').checked) {
          document.getElementById('JPBD_STYLE_NAMAPEGAWAIASAL').style.visibility = "visible";
          document.getElementById('JPBD_STYLE_JAWATANPEGAWAIASAL').style.visibility = "visible";
	  } else {
          document.getElementById('JPBD_STYLE_NAMAPEGAWAIASAL').style.visibility = "collapse";
          document.getElementById('JPBD_STYLE_JAWATANPEGAWAIASAL').style.visibility = "collapse";
	  }
  }
  function printSuratIringan(PRINT_TYPE) {
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewBorangLampiranA1&action2=printSuratIringanJKPTG&printType=" + PRINT_TYPE;
	  document.${formName}.submit();
  }
  function printBorangLampiranA1() {
	  var url = "../servlet/ekptg.report.integrasi.ReportUlasanJPBD?reportType=PDF";
	  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
  }
  function saveBorangLampiranA1() {
      if (!window.confirm("Adakah anda pasti?")) return;
	  document.${formName}.method = "POST";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewBorangLampiranA1&action2=saveBorangLampiranA1";
	  document.${formName}.submit();
  }
  function sendBorangLampiranA1() {
#if ($isJPBDUser == 'true')  
	  if (document.${formName}.JPBD_KEGUNAANTANAH.value == '' && document.${formName}.JPBD_POTENSIPEMBANGUNAN.value == '') {
		  alert('Sila isikan salah satu atau kedua-dua medan kegunaan tanah dan/atau potensi pembangunan.');
		  document.${formName}.JPBD_KEGUNAANTANAH.focus();
		  return false;
      }
	  if (document.${formName}.JPBD_NAMAPEGAWAIJPBD.value == '') {
		  alert('Sila masukkan nama pegawai JPBD.');
		  document.${formName}.JPBD_NAMAPEGAWAIJPBD.focus();
		  return false;
	  }
	  if (document.${formName}.JPBD_TARIKHPERMOHONAN.value == '') {
		  alert('Sila masukkan tarikh.');
		  document.${formName}.JPBD_TARIKHPERMOHONAN.focus();
		  return false;
	  }
	  if (document.${formName}.JPBD_JABATAN.value == '') {
		  alert('Sila masukkan nama jabatan.');
		  document.${formName}.JPBD_JABATAN.focus();
		  return false;
	  }
#end	  
      if (!window.confirm("Adakah anda pasti?")) return;
	  document.${formName}.method = "POST";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewBorangLampiranA1&action2=sendBorangLampiranA1";
	  document.${formName}.submit();
  }
#if ($isJPBDUser == 'true')  
  function verifyBorangLampiranA1(ID_PERMOHONAN) {
	  if (document.${formName}.JPBD_KEGUNAANTANAH.value == '' && document.${formName}.JPBD_POTENSIPEMBANGUNAN.value == '') {
		  alert('Sila isikan salah satu atau kedua-dua medan kegunaan tanah dan/atau potensi pembangunan.');
		  document.${formName}.JPBD_KEGUNAANTANAH.focus();
		  return false;
      }
	  if (document.${formName}.JPBD_NAMAPEGAWAIJPBD.value == '') {
		  alert('Sila masukkan nama pegawai JPBD.');
		  document.${formName}.JPBD_NAMAPEGAWAIJPBD.focus();
		  return false;
	  }
	  if (document.${formName}.JPBD_TARIKHPERMOHONAN.value == '') {
		  alert('Sila masukkan tarikh.');
		  document.${formName}.JPBD_TARIKHPERMOHONAN.focus();
		  return false;
	  }
	  if (document.${formName}.JPBD_JABATAN.value == '') {
		  alert('Sila masukkan nama jabatan.');
		  document.${formName}.JPBD_JABATAN.focus();
		  return false;
	  }
      if (!window.confirm("Adakah anda pasti?")) return;
	  document.${formName}.method = "POST";
      document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMaklumatUlasanJPBDOnline&action2=verifyBorangLampiranA1";
	  document.${formName}.submit();
  }
#end
  function viewBorangLampiranA1(ID_PERMOHONAN) {
      document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMaklumatUlasanJPBDOnline&action2=viewBorangLampiranA1&ID_PERMOHONAN=" + ID_PERMOHONAN;
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function deleteBorangLampiranA1() {
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMaklumatUlasanJPBDOnline&action2=deleteBorangLampiranA1";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function backBorangLampiranA1() {
#if ($isJPBDUser == 'true')
    document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewMyInfoBorangLampiranA1";
#else  
      document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMaklumatUlasanJPBDOnline&action2=";
	  document.${formName}.method = "POST";
#end
	  document.${formName}.submit();
  }
  function displayFile(IDFail) {	
      var url = "../servlet/ekptg.view.integrasi.DisplayBlob?ptype=JPBD&IDFail=" + IDFail;
	  var hWnd = window.open(url,'DisplayFile','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
  }
  function deleteFile(IDFail) {	
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMaklumatUlasanJPBDOnline&action2=deleteFile&FAIL_IDFAIL=" + IDFail;
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function uploadFile() {	
      if (document.${formName}.UPLOAD_FILE.value == '') {
		  alert('Sila pilih fail untuk dimuatnaik.');
		  return;
	  }
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMaklumatUlasanJPBDOnline&action2=doUpload&ID_PERMOHONAN=$ID_PERMOHONAN";
      document.${formName}.method = "POST";
      document.${formName}.enctype = "multipart/form-data";
      document.${formName}.encoding = "multipart/form-data";
      document.${formName}.submit();
  }
  function emptyForm() {
      document.${formName}.UPLOAD_FILE.value = "";
  }
</script>