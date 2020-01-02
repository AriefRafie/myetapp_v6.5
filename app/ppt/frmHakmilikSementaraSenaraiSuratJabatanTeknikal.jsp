#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
<p>
<fieldset>
<legend><strong>Senarai Surat Jabatan Teknikal</strong></legend>
<table width="100%">
  <tr>
    <td colspan="4"><label>
      <input type="submit" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambahSuratJbtnTeknikal()" />
    </label></td>
  </tr>
  <tr class="table_header">
  	<td width="8%"><strong>Bil</strong></td>
    <td width="8%"><strong>Nama Jabatan</strong></td>
    <td width="19%"><strong>Perihal</strong></td>
    <td width="16%"><strong>Bil. Surat</strong></td>
    <td width="25%"><strong>Tarikh Surat</strong></td>
    <td width="32%"><strong>Tempoh (4 minggu)</strong></td>
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
    <td class="$row"><a href="javascript:viewSuratJbtnTeknikal('$fail.idUlasanTeknikal')"><font color="blue">$fail.namaJabatan</font></a></td>
    #else
    <td class="$row">$fail.namaJabatan</td>
    #end
    <td class="$row">$fail.perihal</td>
    <td class="$row">$fail.bilSurat</td>
    <td class="$row">$fail.tarikhSurat</td>
    <td class="$row">$fail.tempoh</td>
  </tr>
  #end
</table>
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input name="idUlasanTeknikal" type="hidden" value="" />
</fieldset>
<script>
function tambahSuratJbtnTeknikal(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=tambahSuratJbtn";
	document.${formName}.submit();
}
function viewSuratJbtnTeknikal(id_ulasanteknikal){
	
	document.${formName}.idUlasanTeknikal.value = id_ulasanteknikal;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraJabatanTeknikal&action=viewSuratJbtn";
	document.${formName}.submit();
}
</script>
