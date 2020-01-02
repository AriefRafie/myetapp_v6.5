<fieldset>
  <legend><strong>CARIAN MAKLUMAT PERMOHONAN</strong>
  </legend><table width="100%">
    <tr>
      <td align="right" valign="top" scope="row">No Fail</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoFail" type="text" id="Carian_NoFail" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoFail" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">No Permohonan</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoPermohonan" type="text" id="Carian_NoPermohonan" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoPermohonan" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row">Tarikh Hantar</td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top">
        <input name="Carian_TarikhHantarDari" type="text" id="Carian_TarikhHantarDari" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_TarikhHantarDari" size="15" maxlength="10" />&nbsp;<a href="javascript:displayDatePicker('Carian_TarikhHantarDari',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
        hingga
        <input name="Carian_TarikhHantarKe" type="text" id="Carian_TarikhHantarKe" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_TarikhHantarKe" size="15" maxlength="10" />&nbsp;<a href="javascript:displayDatePicker('Carian_TarikhHantarKe',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">Tarikh Selesai</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">
        <input name="Carian_TarikhSelesaiDari" type="text" id="Carian_TarikhSelesaiDari" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_TarikhSelesaiDari" size="15" maxlength="10" />&nbsp;<a href="javascript:displayDatePicker('Carian_TarikhSelesaiDari',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
        hingga
        <input name="Carian_TarikhSelesaiKe" type="text" id="Carian_TarikhSelesaiKe" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_TarikhSelesaiKe" size="15" maxlength="10" />&nbsp;<a href="javascript:displayDatePicker('Carian_TarikhSelesaiKe',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a>
      </td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row">
        <input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchNilaianHTATerdahulu();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyNilaianHTATerdahulu();" />
      </td>
    </tr>
    <tr>
      <td colspan="3" align="center" valign="top" scope="row">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI DATA</strong></legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="25%"><strong>NAMA PEMOHON</strong></td>
      <td width="15%"><strong>JENIS PERMOHONAN</strong></td>
      <td width="10%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="10%" align="center"><strong>TARIKH SELESAI</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListNilaian)
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
      <td class="$row" valign="top">$list.NoFail</td>
      <td class="$row" valign="top">$list.NoPermohonan</td>
      <td class="$row" valign="top">$list.NamaPemohon</td>
      <td class="$row" valign="top">$list.JenisPermohonan</td>
      <td class="$row" valign="top" align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" align="center">$list.TarikhSelesai</td>
    #else
      <td colspan="7" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="7">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<input type="hidden" id="ID_PEMOHON" name="ID_PEMOHON" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script type="text/javascript">
  function searchNilaianHTATerdahulu() {
      if (document.${formName}.Carian_NoFail.value == '' && document.${formName}.Carian_NoPermohonan.value == '' && document.${formName}.Carian_TarikhHantarDari.value == '' && document.${formName}.Carian_TarikhSelesaiDari.value == '') {
		  alert('Sila pastikan salah satu medan carian diisi.');
		  document.${formName}.Carian_NoFail.focus();
		  return;
	  }
      document.${formName}.action = "?action2=searchNilaianHTATerdahulu";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function emptyNilaianHTATerdahulu() {
      document.${formName}.action = "?action2=";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>