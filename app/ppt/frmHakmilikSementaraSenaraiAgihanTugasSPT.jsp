<fieldset>
<legend><strong>Carian</strong></legend>
<table width="100%">
  <tr>
    <td width="29%" align="left">No. Fail</td>
    <td width="1%">:</td>
    <td><input name="no_failc" style="text-transform:uppercase;" type="text" value="$CarianFail" size="23" ></td>
  </tr>
  <tr>
    <td width="29%" align="left">Tarikh Agihan</td>
    <td width="1%">:</td>
    <td><input name="txdTarikhMohon" value="$CarianTarikhMohon" type="text" size="10" />
    <a href="javascript:displayDatePicker('txdTarikhMohon',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td width="29%" align="left">Status</td>
    <td width="1%">:</td>
    <td><select name="cStatus" class="autoselect">
      <option value="" selected>Sila Pilih</option>
      <option value="128">DILULUSKAN HQ</option>
      <option value="11">PERMOHONAN CAWANGAN</option>
      <!--<option value="149">Agihan Tugas Dari HQ</option>-->
        </select></td>
  </tr>
  <tr>
    <td width="29%" align="left">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td><input name="cari" value="Cari" type="button" onclick="javascript:search_data();" />
    <input type="button" value = "Kosongkan" onclick="javascript:cancel();" /></td>
  </tr>
</table>
</fieldset>
<fieldset><legend><strong>Senarai Agihan</strong></legend>
#parse("app/utils/record_paging.jsp")
<table width="100%">
  <tr class="table_header">
    <td><strong>No</strong></td>
    <td><strong>No. Fail</strong></td>
    <td><strong>Tarikh Agihan</strong></td>
    <td><strong>Nama Pegawai</strong></td>
    <td><strong>Urusan</strong></td>
    <td><strong>Status</strong></td>
    <td><strong>Catatan</strong></td>
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
    #if($fail.bil != "")
          <td class="$row"><a href="javascript:view_item('$fail.id_fail','$fail.id_permohonan')"><font color="blue">$fail.no_fail</font></a></td>
    #else
    
          <td class="$row">$fail.no_fail</td>
    #end   
    <td class="$row">$fail.tarikh_agih</td>
    <td class="$row">$fail.nama_pegawai</td>
    <td class="$row">$fail.nama_suburusan</td>
    <td class="$row">$fail.keterangan</td>
    <td class="$row">$fail.perihal_agih</td>
  </tr>
  #end
</table>
<input type="hidden" name="id_fail">
<input type="hidden" name="no_fail">          
<input type="hidden" name="id_permohonan">
<input type="hidden" name="no_permohonan"> 
<input type="hidden" name="id_tugas">  
<input type="hidden" name="id_status"> 

</fieldset>
<script>
function view_item(id_fail,id_permohonan) {
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraAgihanTugasSPT&action=view_item";
	document.${formName}.submit();
}
function view_item_tugas(id_permohonan,id_fail,id_tugas) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_tugas.value = id_tugas;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraAgihanTugasSPT&action=view_item_tugas";
	document.${formName}.submit();
}
</script>