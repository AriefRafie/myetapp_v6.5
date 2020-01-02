<fieldset>
<legend><strong>Carian</strong></legend>
<table width="100%">
  <tr>
    <td width="29%" align="left">No. Fail</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtNoFail" type="text" id="txtNoFail" value="$CarianFail" size="40" />
    </label></td>
  </tr>
  <tr>
    <td align="left">Tarikh</td>
    <td>:</td>
    <td><label>
      <input name="txdTarikh" type="text" id="txdTarikh" value="$tarikhPermohonan" size="10" />
      <a href="javascript:displayDatePicker('txdTarikh',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> </label></td>
  </tr>
  <tr>
    <td align="left">Status</td>
    <td>:</td>
    <td>$SelectStatus</td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td><input type="submit" name="cmdCari" id="cmdCari" value="Cari" onclick="cari()" />
        <input type="submit" name="cmdKosongkan" id="cmdCari" value="Kosongkan" onclick="kosongkan()" />
    </td>
  </tr>
</table>
</fieldset>
<p>&nbsp; </p>
<fieldset>
<strong>
<legend>Senarai Permohonan</legend>
</strong> #parse("app/utils/record_paging.jsp")
<table width="100%">
  <tr class="table_header">
    <td><strong>No</strong></td>
    <td><strong>No. Ruj. JKPTG </strong></td>
    <td><strong>No Permohonan</strong></td>
    <td><strong>Tarikh </strong></td>
    <td><strong>Urusan</strong></td>
    <td><strong>Kementerian </strong></td>
    <td><strong>Status</strong></td>
  </tr>
  #foreach ($fail in $PermohonanList)
  
  #if ($fail.bil == '') 
  #set ($row = 'row1')
  #elseif ($fail.bil % 2 != 0)
  #set ($row = 'row1')
  #else 
  #set ($row = 'row2')
  #end
  <tr>
    <td class="$row">$fail.bil</td>
    #if($fail.bil != '')
    <td class="$row"><a href="javascript:tambah('$fail.id_fail','$fail.id_permohonan')"><font color="blue">$fail.no_fail</font></a></td>
    #else
      <td class="$row">$fail.no_fail</td>
      #end
    <td class="$row">$fail.no_permohonan</td>
    <td class="$row">$fail.tarikh_permohonan</td>
    <td class="$row">$fail.nama_suburusan</td>
    <td class="$row">$fail.nama_kementerian</td>
    <td class="$row">$fail.keterangan</td>
  </tr>
  #end
</table>
<input type="hidden" name="id_fail" />
<input type="hidden" name="no_fail" />
<input type="hidden" name="id_permohonan" />
<input type="hidden" name="no_permohonan" />
</fieldset>
<script>
function tambah(id_fail,id_permohonan) {
	
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraLaporanTanah&action=newLaporan";
	document.${formName}.submit();
	
}
function cari() {

	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraLaporanTanah&action=";
	document.${formName}.submit();
	
}
function kosongkan() {
	
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txdTarikh.value = "";
	document.${formName}.socStatus.value = "";
	return;
	
}
</script>
