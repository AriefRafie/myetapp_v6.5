<fieldset>
  <legend><strong>CARIAN MAKLUMAT PELESEN</strong>
  </legend><table width="100%">
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO FAIL</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="CARIAN_NOFAIL" type="text" id="CARIAN_NOFAIL" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_NOFAIL" size="50" maxlength="255" placeholder="Nombor fail permohonan" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>TARIKH KUATKUASA</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="CARIAN_TARIKHKUATKUASA" type="text" id="CARIAN_TARIKHKUATKUASA" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_TARIKHKUATKUASA" size="15" maxlength="255" placeholder="Tarikh permohonan dibuat" />
      &nbsp;<a href="javascript:displayDatePicker('CARIAN_TARIKHSURAT',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><strong>TARIKH TAMAT</strong></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><input name="CARIAN_TARIKHTAMAT" type="text" id="CARIAN_TARIKHTAMAT" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_TARIKHTAMAT" size="15" maxlength="255" placeholder="Nama pemohon" />
      <a href="javascript:displayDatePicker('CARIAN_TARIKHTAMAT',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
    </tr>
    
    <tr>
      <td align="right" valign="top" scope="row"><strong>NEGERI</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selCarianNegeri</td>
    </tr>
    
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row"><input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchAPB();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyAPB();" /></td>
    </tr> 
    <tr>
      <td colspan="3" align="center" valign="top" scope="row">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI PELESEN</strong></legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>TARIKH KUATKUASA</strong></td>
      <td width="20%"><strong>TARIKH TAMAT</strong></td>
      <td width="15%"><strong>TEMPOH</strong></td>
      <td width="25%"><strong>NEGERI</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListPermohonan)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
      <td class="$row" valign="top" align="center">$list.NO</td>
      <td class="$row" valign="top"><a href="javascript:viewAPB('$list.ID_PERMOHONAN')" style="color:#0000FF">$list.NO_FAIL</a></td>
      <td class="$row" valign="top">$list.TARIKH_SURAT</td>
      <td class="$row" valign="top">$list.NAMA_PEMOHON</td>
      <td class="$row" valign="top">$list.TEMPOH</td>
      <td class="$row" valign="top">$list.NEGERI</td>
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
<input type="hidden" id="ID_FAIL" name="ID_FAIL" value="$ID_FAIL" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script type="text/javascript">
  function viewAPB(ID_FAIL) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPBPelesen&action2=viewAPB&ID_FAIL=" + ID_FAIL;
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function searchAPB() {
      if (document.${formName}.CARIAN_NOFAIL.value == '' && document.${formName}.CARIAN_TARIKHKUATKUASA.value == '' && document.${formName}.CARIAN_TARIKHTAMAT.value == '' && document.${formName}.CARIAN_NEGERI.value == '') {
		  alert('Sila pastikan salah satu medan carian diisi.');
		  document.${formName}.CARIAN_NOFAIL.focus();
		  return;
	  }
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPBPelesen&action2=searchAPB";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function emptyAPB() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPBPelesen&action2=";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>