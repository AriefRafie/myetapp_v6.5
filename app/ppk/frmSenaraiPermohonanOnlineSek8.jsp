<fieldset>
<legend><strong>Carian</strong></legend>
<table width="100%" border="0">
  <tr>
    <td width="29%"><div align="right">Negeri</div></td>
    <td width="1%">:</td>
    <td width="70%">$selectNegeri</td>
  </tr>
  <tr>
    <td><div align="right">Unit</div></td>
    <td>:</td>
    <td>$selectUnit</td>
  </tr>
  <tr>
    <td><div align="right">Daerah</div></td>
    <td>:</td>
    <td>$selectDaerah</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="cari()" >
    <input type="reset" name="cmdKosong" id="cmdKosong" value="Kosongkan"></td>
  </tr>
</table>
</fieldset>
<br>
<fieldset>
<legend><strong>Senarai Permohonan Online Seksyen 8</strong></legend>
#parse("app/utils/record_paging.jsp")
<table width="100%" border="0">
  <tr class="table_header">
    <td width="5%"><strong>No</strong></td>
    <td><strong>No Permohonan Online</strong></td>
    <td><strong>Nama Simati</strong></td>
    <td width="15%"><strong>Tarikh Mohon</strong></td>
    <td><strong>No Fail</strong></td>
  </tr>
  #foreach ($fail in $SenaraiPermohonan)
  
  #if ($fail.bil == '') 
  #set ($row = 'row1')
  #elseif ($fail.bil % 2 != 0)
  #set ($row = 'row1')
  #else 
  #set ($row = 'row2')
  #end
  <tr>
    <td class="$row">$fail.bil</td>
    <td class="$row">$fail.NO_PERMOHONAN_ONLINE</td>
    <td class="$row">$fail.NAMA_SIMATI</td>
    <td class="$row">$fail.TARIKH_MOHON</td>
    <td class="$row">$fail.NO_FAIL</td>
  </tr>
  #end
</table>
</fieldset>
<script>
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeUnit() {
	doAjaxCall${formName}("doChangeUnit");
}
function cari() {

	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanOnlineSek8&action=";
	document.${formName}.submit();
	
}
</script>