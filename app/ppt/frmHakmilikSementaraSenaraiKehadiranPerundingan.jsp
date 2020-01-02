
<fieldset>
<legend><strong>Carian</strong></legend>
<table width="100%">
  <tr>
    <td width="29%" align="left">No. Fail</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input type="text" name="txtNoFail" id="txtNoFail" />
    </label></td>
  </tr>
  <tr>
    <td align="left">Tarikh</td>
    <td>:</td>
    <td><label>
      <input name="txdTarikh" type="text" id="txdTarikh" size="10" />
      <a href="javascript:displayDatePicker('txdTarikh',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> </label></td>
  </tr>
  <tr>
    <td align="left">Status</td>
    <td>:</td>
    <td></td>
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
<p>&nbsp; </p>
<fieldset>
<strong>
<legend>Senarai Permohonan</legend>
</strong>
<table width="100%">
  <tr>
    <td><strong>No</strong></td>
    <td><strong>No Fail</strong></td>
    <td><strong>No Permohonan</strong></td>
    <td><strong>Tarikh </strong></td>
    <td><strong>Urusan</strong></td>
    <td><strong>Kementerian </strong></td>
    <td><strong>Status</strong></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</fieldset>
<script>
function tambah() {
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMaklumatHakmilikPB&action=tambah";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
	
}

</script>

