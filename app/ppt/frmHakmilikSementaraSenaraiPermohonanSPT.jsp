<fieldset>
<legend><strong>Carian</strong></legend>
<table width="100%">
  <tr>
    <td width="29%" align="left">No. Permohonan</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtNoFail" type="text" id="txtNoFail" value="$carianPermohonan" />
    </label></td>
  </tr>
  <tr>
    <td align="left">Tarikh Permohonan</td>
    <td>:</td>
    <td><label>
      <input name="txdTarikh" type="text" id="txdTarikh" value="$carianTarikh" size="10" />
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
    <td><input type="submit" name="cmdCari" id="cmdCari" value="Cari" />
        <input type="submit" name="cmdKosongkan" id="cmdCari" value="Kosongkan" />
    </td>
  </tr>
</table>
</fieldset>
<p>
<fieldset>
<strong>
<legend>Senarai Permohonan</legend>
</strong>
#parse("app/utils/record_paging.jsp")
<table width="100%">
  <tr>
    <td colspan="7"><label>
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar Baru" onClick="javascript:baru();">
    </label></td>
  </tr>
  <tr class="table_header">
    <td><strong>No</strong></td>
    <td><strong>No Permohonan</strong></td>
    <td><strong>Tarikh </strong></td>
    <td><strong>Urusan</strong></td>
    <td><strong>Kementerian </strong></td>
    <td><strong>Status</strong></td>
    <td><strong>No. Fail</strong></td>
  </tr>
  #foreach ($fail in $PermohonanListSPT)
  
  #if ($fail.bil == '') 
  	#set ($row = 'row1')
  #elseif ($fail.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$fail.bil</td>
     #if ($fail.bil != '') 
    <td class="$row"><a href="javascript:edit_item('$fail.id_permohonan')"><font color="blue">$fail.no_permohonan</font></a></td>
    #else
    <td class="$row">$fail.no_permohonan</td>
    #end
    <td class="$row">$fail.tarikh_permohonan</td>
    <td class="$row">$fail.nama_suburusan</td>
    <td class="$row">$fail.nama_kementerian</td>
    <td class="$row">$fail.status</td>
    <td class="$row">$fail.no_fail</td>
  </tr>
  #end
</table>
<input type="hidden" name="no_permohonan">
<input type="hidden" name="tarikh_permohonan">
<input type="hidden" name="status">
<input type="hidden" name="suburusan">
<input type="hidden" name="id_permohonan" value="$id">

</fieldset>
<script>

function baru() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=baru";
	document.${formName}.submit();
}
function edit_item(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action= "?_portal_module=ekptg.view.ppt.SementaraSPT&action=semak";
	document.${formName}.submit();
}
</script>
