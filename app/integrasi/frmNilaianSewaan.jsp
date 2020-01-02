<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
#if ($saveNilaianSewaan == 'true')
<fieldset>
  <div style="text-align:center; font-weight:bold">Data telah berjaya disimpan.</div>
</fieldset>
<br />
#end
<fieldset>
  <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
  <br />
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>NO FAIL</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap" class="link">$MP_NOFAIL</td>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>TARIKH MOHON</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap" class="link">$MP_TARIKHMOHON</td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>NEGERI</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap" class="link">$MP_NEGERI</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>NAMA PEMOHON</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMAPEMOHON</td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>DAERAH / JAJAHAN</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap" class="link">$MP_DAERAH</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>ALAMAT PEMOHON</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_ALAMATPEMOHON</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>UNIT</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_UNIT</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO TEL PEMOHON</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NOTELPEMOHON</td>
    </tr>
    <tr>
      <td width="15%" rowspan="3" align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td width="1%" rowspan="3" align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td width="34%" rowspan="3" align="left" valign="top" nowrap="nowrap" class="link">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>NAMA SIMATI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMASIMATI</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO KP SIMATI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NOKPSIMATI</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>TARIKH MATI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_TARIKHMATI</td>
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
      <td width="34%" align="left" valign="top" nowrap="nowrap">$selectSEWANegeri</td>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>KATEGORI TANAH</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap">$selectSEWAKategoriTanah</td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>DAERAH</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap">$selectSEWADaerah</td>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>SYARAT NYATA</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap"><textarea name="SEWA_SYARATNYATA" cols="50" rows="3" id="SEWA_SYARATNYATA" onkeyup="this.value=this.value.toUpperCase();" $disable_jkptg="$DISABLE_JKPTG">$!SEWA_SYARATNYATA</textarea></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>MUKIM / BANDAR</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap">$selectSEWAMukim</td>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>SEKATAN KEPENTINGAN</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap"><textarea name="SEWA_SEKATANKEPENTINGAN" cols="50" rows="3" id="SEWA_SEKATANKEPENTINGAN" onkeyup="this.value=this.value.toUpperCase();" $disable_jkptg="$DISABLE_JKPTG">$!SEWA_SEKATANKEPENTINGAN</textarea></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>SEKSYEN</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap">$selectSEWASeksyen</td>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>LUAS ASAL</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap"><input name="SEWA_LUASASAL" type="text" id="SEWA_LUASASAL" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_LUASASAL" size="10" maxlength="10" $disable_jkptg="$DISABLE_JKPTG" />
        &nbsp;$selectSEWAJenisLuasTanahAsal</td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>JENIS HAKMILIK</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap">$selectSEWAJenisHakMilik</td>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>LUAS TANAH DIPAJAK</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap"><input name="SEWA_LUASTANAHDIPAJAK" type="text" id="SEWA_LUASTANAHDIPAJAK" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_LUASTANAHDIPAJAK" size="10" maxlength="10" $disable_jkptg="$DISABLE_JKPTG" />
&nbsp;$selectSEWAJenisLuasTanahDipajak</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO HAKMILIK</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="SEWA_NOHAKMILIK" type="text" id="SEWA_NOHAKMILIK" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_NOHAKMILIK" size="20" maxlength="10" $DISABLE_JKPTG /></td>
      <td align="right" valign="top" nowrap="nowrap"><strong>STATUS PEMILIKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="SEWA_STATUSPEMILIKAN" type="text" id="SEWA_STATUSPEMILIKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_STATUSPEMILIKAN" size="50" maxlength="255" $DISABLE_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO PT / LOT</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="SEWA_NOPTLOT" type="text" id="SEWA_NOPTLOT" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_NOPTLOT" size="20" maxlength="20" $DISABLE_JKPTG />
        &nbsp;$selectSEWAJenisPTLot</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>TUJUAN SEWAAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="SEWA_TUJUANSEWAAN" type="text" id="SEWA_TUJUANSEWAAN" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_TUJUANSEWAAN" size="50" maxlength="255" $DISABLE_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>JENIS PEGANGAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$selectSEWAJenisPegangan</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>ALAMAT HARTA</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="SEWA_ALAMAT1HARTA" type="text" id="SEWA_ALAMAT1HARTA" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_ALAMAT1HARTA" size="50" maxlength="255" $DISABLE_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>TARIKH LUPUT PAJAKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="SEWA_TARIKHLUPUTPAJAKAN" type="text" id="SEWA_TARIKHLUPUTPAJAKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_TARIKHLUPUTPAJAKAN" size="15" maxlength="10" $DISABLE_JKPTG />
      <a href="javascript:displayDatePicker('SEWA_TARIKHLUPUTPAJAKAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="SEWA_ALAMAT2HARTA" type="text" id="SEWA_ALAMAT2HARTA" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_ALAMAT2HARTA" size="50" maxlength="255" $DISABLE_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>TEMPOH PAJAKAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="SEWA_TEMPOHPAJAKAN" type="text" id="SEWA_TEMPOHPAJAKAN" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_TEMPOHPAJAKAN" size="15" maxlength="5" $DISABLE_JKPTG /></td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="SEWA_ALAMAT3HARTA" type="text" id="SEWA_ALAMAT3HARTA" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_ALAMAT3HARTA" size="50" maxlength="255" $DISABLE_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO LOT BERKAITAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="SEWA_NOLOTBERKAITAN" type="text" id="SEWA_NOLOTBERKAITAN" onkeyup="this.value=this.value.toUpperCase();" value="$!SEWA_NOLOTBERKAITAN" size="50" maxlength="255" $DISABLE_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>CATATAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><textarea name="SEWA_CATATAN" cols="50" rows="5" id="SEWA_CATATAN" onkeyup="this.value=this.value.toUpperCase();" $disable_jkptg="$DISABLE_JKPTG">$!SEWA_CATATAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
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
      <td width="15%" align="right" valign="top">Tarikh Nilaian</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="SEWA_TARIKHNILAIAN" type="text" id="SEWA_TARIKHNILAIAN" value="$!SEWA_TARIKHNILAIAN" size="20" maxlength="15" $DISABLE_JPPH />
      <a href="javascript:displayDatePicker('SEWA_TARIKHNILAIAN',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
      <td width="15%" rowspan="5" align="right" valign="top">Catatan</td>
      <td width="1%" rowspan="5" align="center" valign="top">:</td>
      <td width="34%" rowspan="5" align="left" valign="top"><textarea name="HAK_JPPHCATATAN" cols="50" rows="5" id="HAK_JPPHCATATAN" $DISABLE_JPPH>$!SEWA_JPPHCATATAN</textarea></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top">Kadar Sewa Bulan ke Bulan (RM)</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="SEWA_KADARSEWABULANKEBULAN" type="text" id="SEWA_KADARSEWABULANKEBULAN" value="$!SEWA_KADARSEWABULANKEBULAN" size="20" maxlength="15" $DISABLE_JPPH /></td>
    </tr>
    <tr>
      <td width="15%" align="right" valign="top">Kadar Sewa Bulanan Dalam Tempoh 3 Tahun</td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="34%" valign="top"><input name="SEWA_KADARSEWABULANAN3TAHUN" type="text" id="SEWA_KADARSEWABULANAN3TAHUN" value="$!SEWA_KADARSEWABULANAN3TAHUN" size="20" maxlength="15" $DISABLE_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Nama Pegawai</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HAK_NAMAPEGAWAI" type="text" id="HAK_NAMAPEGAWAI" value="$!SEWA_NAMAPEGAWAI" size="50" maxlength="255" $DISABLE_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top">Cawangan JPPH</td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="HAK_CAWANGANJPPH" type="text" id="HAK_CAWANGANJPPH" value="$!SEWA_CAWANGANJPPH" size="50" maxlength="255" $DISABLE_JPPH /></td>
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
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianSewaan";
	  doAjaxCall${formName}("");
  }
  function doChangeSOC(SOC_TYPE) {
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianSewaan";
	  doAjaxCall${formName}(SOC_TYPE);
  }
  function saveNilaianSewaan() {
	  document.${formName}.action2.value = "saveNilaianSewaan";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianSewaan";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function viewNilaianPajakan(ID_PERMOHONAN) {
	  document.${formName}.action2.value = "viewNilaianPajakan";
	  document.${formName}.ID_PERMOHONAN.value = ID_PERMOHONAN;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianSewaan";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
</script>