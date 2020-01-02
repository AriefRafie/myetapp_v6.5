#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
<p>
<fieldset>
<legend><strong>Senarai Hakmilik</strong></legend>
<table width="100%">
  <tr>
    <td colspan="7"><label>
      <input type="submit" name="cmdTambah" id="cmdTambah" value="Tambah" onClick="tambah()"  />
    </label></td>
  </tr>
  <tr class="table_header">
    <td><strong>Bil</strong></td>
    <td><strong>No Lot / No PT</strong></td>
    <td><strong>No. Hakmilik</strong></td>
    <td><strong>Bandar/Pekan/Mukim</strong></td>
    <td><strong>Daerah</strong></td>
    <td><strong>Luas Lot</strong></td>
    <td><strong>Luas Diguna/Disewa</strong></td>
  </tr>
  #foreach ($hakmilik in $SenaraiHakmilik)
  
  #if ($hakmilik.BIL == '') 
  	#set ($row = 'row1')
  #elseif ($hakmilik.BIL % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$hakmilik.BIL</td>
    #if ($hakmilik.BIL != '') 
    	<td class="$row"><a href="javascript:view_hakmilik('$hakmilik.ID_HAKMILIK')"><font color="blue">$hakmilik.NO_LOT</font></a></td>
    #else
    	<td class="$row">$hakmilik.NO_LOT</td>
    #end
    <td class="$row">$hakmilik.NO_HAKMILIK</td>
    <td class="$row">$hakmilik.NAMA_MUKIM</td>
    <td class="$row">$hakmilik.NAMA_DAERAH</td>
    <td class="$row">$hakmilik.LUAS_LOT</td>
    <td class="$row">$hakmilik.LUAS_AMBIL</td>
  </tr>
  #end
</table>
<input name="idHakmilik" type="hidden" value="" />
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="no_fail">          
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="no_permohonan">
</fieldset>
<script>
function tambah() {
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=tambahHakmilik";
	document.${formName}.submit();
	
}
function view_hakmilik(idHakmilik) {
	
	document.${formName}.idHakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=viewMaklumatHakmilik";
	document.${formName}.submit();
	
}
</script>
