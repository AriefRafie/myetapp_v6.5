<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
#if ($saveNilaianPajakan == 'true')
<fieldset>
  <div style="text-align:center; font-weight:bold">Data telah berjaya disimpan.</div>
</fieldset>
<br />
#elseif ($sendNilaianPajakan == 'true')
<fieldset>
  <div style="text-align:center; font-weight:bold">Permohonan nilaian telah berjaya dihantar ke JKPTG.</div>
</fieldset>
<br />
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
      <td align="right" valign="top" nowrap="nowrap"><strong>AGENSI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_AGENSI</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NAMA PEGAWAI JKPTG</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link"><input name="MP_NAMA_PEGAWAI_JKPTG" type="text" id="MP_NAMA_PEGAWAI_JKPTG" onkeyup="this.value=this.value.toUpperCase();" value="$!MP_NAMA_PEGAWAI_JKPTG" size="50" maxlength="255" $READONLY_JKPTG /></td>
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
      <td width="34%" align="left" valign="top" nowrap="nowrap">$selectPJKNegeri</td>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>KATEGORI TANAH</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap">$selectPJKKategoriTanah</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>DAERAH</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$selectPJKDaerah</td>
      <td rowspan="3" align="right" valign="top" nowrap="nowrap"><strong>SYARAT NYATA</strong></td>
      <td rowspan="3" align="center" valign="top" nowrap="nowrap">:</td>
      <td rowspan="3" align="left" valign="top" nowrap="nowrap"><textarea name="PJK_SYARATNYATA" cols="30" rows="3" id="PJK_SYARATNYATA" onkeyup="this.value=this.value.toUpperCase();" $READONLY_JKPTG>$!PJK_SYARATNYATA</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>MUKIM / BANDAR</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$selectPJKMukim</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>SEKSYEN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$selectPJKSeksyen</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>JENIS HAKMILIK</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$selectPJKJenisHakmilik</td>
      <td rowspan="3" align="right" valign="top" nowrap="nowrap"><strong>SEKATAN KEPENTINGAN</strong></td>
      <td rowspan="3" align="center" valign="top" nowrap="nowrap">:</td>
      <td rowspan="3" align="left" valign="top" nowrap="nowrap"><textarea name="PJK_SEKATANKEPENTINGAN" cols="30" rows="3" id="PJK_SEKATANKEPENTINGAN" onkeyup="this.value=this.value.toUpperCase();" $READONLY_JKPTG>$!PJK_SEKATANKEPENTINGAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO HAKMILIK</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_NOHAKMILIK" type="text" id="PJK_NOHAKMILIK" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_NOHAKMILIK" size="20" maxlength="10" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO PT / LOT</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_NOPTLOT" type="text" id="PJK_NOPTLOT" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_NOPTLOT" size="20" maxlength="20" $READONLY_JKPTG />
  &nbsp;$selectPJKJenisPTLot</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>JENIS PEGANGAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$selectPJKJenisPegangan</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>LUAS ASAL</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_LUASASAL" type="text" id="PJK_LUASASAL" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_LUASASAL" size="10" maxlength="10" $READONLY_JKPTG />
  &nbsp;$selectPJKJenisLuasTanahAsal</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>TARIKH LUPUT PAJAKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_TARIKHLUPUTPAJAKAN" type="text" id="PJK_TARIKHLUPUTPAJAKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_TARIKHLUPUTPAJAKAN" size="15" maxlength="10" $READONLY_JKPTG />
        <a href="javascript:displayDatePicker('PJK_TARIKHLUPUTPAJAKAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
      <td align="right" valign="top" nowrap="nowrap"><strong>LUAS TANAH DIPAJAK</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_LUASTANAHDIPAJAK" type="text" id="PJK_LUASTANAHDIPAJAK" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_LUASTANAHDIPAJAK" size="10" maxlength="10" $READONLY_JKPTG />
  &nbsp;$selectPJKJenisLuasTanahDipajak</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>TEMPOH PAJAKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_TEMPOHPAJAKAN" type="text" id="PJK_TEMPOHPAJAKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_TEMPOHPAJAKAN" size="15" maxlength="5" $READONLY_JKPTG /></td>
      <td align="right" valign="top" nowrap="nowrap"><strong>STATUS PEMILIKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_STATUSPEMILIKAN" type="text" id="PJK_STATUSPEMILIKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_STATUSPEMILIKAN" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>TUJUAN PAJAKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_TUJUANPAJAKAN" type="text" id="PJK_TUJUANPAJAKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_TUJUANPAJAKAN" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>ALAMAT HARTA</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_ALAMAT1HARTA" type="text" id="PJK_ALAMAT1HARTA" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_ALAMAT1HARTA" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_ALAMAT2HARTA" type="text" id="PJK_ALAMAT2HARTA" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_ALAMAT2HARTA" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_ALAMAT3HARTA" type="text" id="PJK_ALAMAT3HARTA" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_ALAMAT3HARTA" size="30" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>NILAIAN YANG DIPERLUKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_PAJAKAN99" type="radio" id="PJK_PAJAKAN99" value="1" $PJK_PAJAKAN99 $READONLY_JKPTG />
        Pajakan 99 Tahun</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_PAJAKAN60" type="radio" id="PJK_PAJAKAN60" value="1" $PJK_PAJAKAN60 $READONLY_JKPTG />
        Pajakan 60 Tahun</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_PAJAKAN30" type="radio" id="PJK_PAJAKAN30" value="1" $PJK_PAJAKAN30 $READONLY_JKPTG />
        Pajakan 30 Tahun</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_PAJAKANVAR" type="radio" id="PJK_PAJAKANVAR" value="1" $PJK_PAJAKANVAR $READONLY_JKPTG />
        Lain-lain tempoh:
        <input name="PJK_PAJAKANVARYEAR" type="text" id="PJK_PAJAKANVARYEAR" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_PAJAKANVARYEAR" size="5" maxlength="5" $READONLY_JKPTG />
  &nbsp;Tahun</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="PJK_PAJAKANTAHUNAN" type="radio" id="PJK_PAJAKANTAHUNAN" value="1" $PJK_PAJAKANTAHUNAN $READONLY_JKPTG />
        Sewa (pajakan) Tahunan</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>CATATAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><textarea name="PJK_CATATAN" cols="30" rows="3" id="PJK_CATATAN" onkeyup="this.value=this.value.toUpperCase();" $READONLY_JKPTG>$!PJK_CATATAN</textarea></td>
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
	#foreach ($list in $ListHAK)
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
      <td class="$row" valign="top"><a href="javascript:viewNilaianHAK('$list.IDHAK')" class="link">$list.Negeri</a></td>
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
<fieldset>
  <legend><strong>JPPH</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="20%" align="right" valign="top">Tarikh Nilaian</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="29%" valign="top">
#if ($READONLY_JPPH == '')
        <input name="PJK_JPPHTARIKHNILAIAN" type="text" id="PJK_JPPHTARIKHNILAIAN" value="$!PJK_JPPHTARIKHNILAIAN" size="20" maxlength="15" $READONLY_JPPH />
        <a href="javascript:displayDatePicker('PJK_TARIKHNILAIAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
#else
        <input name="PJK_JPPHTARIKHNILAIAN" type="text" id="PJK_JPPHTARIKHNILAIAN" value="" size="20" maxlength="15" $READONLY_JPPH />
        <img src="../img/calendar.gif" alt="" border="0" />
#end      </td>
      <td width="15%" rowspan="6" align="right" valign="top">Catatan</td>
      <td width="1%" rowspan="6" align="center" valign="top">:</td>
      <td width="34%" rowspan="6" valign="top"><textarea name="HAK_JPPHCATATAN" cols="30" rows="10" id="HAK_JPPHCATATAN" $READONLY_JPPH>$!PJK_JPPHCATATAN</textarea></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top">Nilai Pajakan 99 Tahun (RM)</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="PJK_JPPHNILAIPAJAKAN99" type="text" id="PJK_JPPHNILAIPAJAKAN99" value="$!PJK_JPPHNILAIPAJAKAN99" size="20" maxlength="15" $READONLY_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Nilai Pajakan 60 Tahun (RM)</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="PJK_JPPHNILAIPAJAKAN60" type="text" id="PJK_JPPHNILAIPAJAKAN60" value="$!PJK_JPPHNILAIPAJAKAN60" size="20" maxlength="15" $READONLY_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Nilai Pajakan 30 Tahun (RM)</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="PJK_JPPHNILAIPAJAKAN30" type="text" id="PJK_JPPHNILAIPAJAKAN30" value="$!PJK_JPPHNILAIPAJAKAN30" size="20" maxlength="15" $READONLY_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Nilai Pajakan 
        <input name="PJK_JPPHNILAIPAJAKANVARYEAR" type="text" id="PJK_JPPHNILAIPAJAKANVARYEAR" onkeyup="this.value=this.value.toUpperCase();" value="$!PJK_JPPHNILAIPAJAKANVARYEAR" size="5" maxlength="5" $READONLY_JPPH />
Tahun (RM)</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="PJK_JPPHNILAIPAJAKANVAR" type="text" id="PJK_JPPHNILAIPAJAKANVAR" value="$!PJK_JPPHNILAIPAJAKANVAR" size="20" maxlength="15" $READONLY_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Nama Pegawai</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="PJK_JPPHNAMAPEGAWAI" type="text" id="PJK_JPPHNAMAPEGAWAI" value="$!PJK_JPPHNAMAPEGAWAI" size="30" maxlength="255" $READONLY_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Cawangan JPPH</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="PJK_JPPHCAWANGANJPPH" type="text" id="PJK_JPPHCAWANGANJPPH" value="$!PJK_JPPHCAWANGANJPPH" size="30" maxlength="255" $READONLY_JPPH /></td>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="6" align="right" valign="top">&nbsp;</td>
    </tr>
  </table>  
</fieldset>
<br />
<div style="text-align:center">
  <input type="button" id="cmdSave" name="cmdSave" value="Simpan" onclick="saveNilaianPajakan();" />&nbsp;
  <input type="button" id="cmdBack" name="cmdBack" value="Kembali" onclick="backNilaianPajakan();" />&nbsp;
  <input type="button" id="cmdDelete" name="cmdDelete" value="Hapus" onclick="deleteNilaianPajakan();" />
</div>
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="ID_SIMATI" name="ID_SIMATI" value="$ID_SIMATI" />
<input type="hidden" id="ID_PEMOHON" name="ID_PEMOHON" value="$ID_PEMOHON" />
<input type="hidden" id="mode" name="mode" value="$mode" />
<input type="hidden" id="action2" name="action2" value="$action2" />
<script type="text/javascript">
  function doChangeTab(TabID) {
	  document.${formName}.selectedTab.value = TabID;
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianPajakan";
	  doAjaxCall${formName}("");
  }
  function doChangeSOC(SOC_TYPE) {
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianPajakan";
	  doAjaxCall${formName}(SOC_TYPE);
  }
  function saveNilaianPajakan() {
	  document.${formName}.action2.value = "saveNilaianPajakan";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianPajakan";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function viewNilaianPajakan(ID_PERMOHONAN) {
	  document.${formName}.action2.value = "viewNilaianPajakan";
	  document.${formName}.ID_PERMOHONAN.value = ID_PERMOHONAN;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianPajakan";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
</script>