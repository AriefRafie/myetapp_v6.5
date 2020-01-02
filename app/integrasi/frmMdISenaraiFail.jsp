<fieldset>
  <legend><strong>CARIAN PIHAK BERKEPENTINGAN</strong>
  </legend><table width="100%">
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><strong>NO FAIL</strong></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><input name="CARIAN_NOFAIL" type="text" id="CARIAN_NOFAIL" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_NOFAIL" size="50" maxlength="255" placeholder="Nombor fail permohonan" /></td>
    </tr>
    
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row"><input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchFail();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyFail();" /></td>
    </tr> 
    <tr>
      <td colspan="3" align="center" valign="top" scope="row">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL</strong></legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO HAKMILIK</strong></td>
      <td width="20%"><strong>NO LOT/PT</strong></td>
      <td width="30%"><strong>MUKIM/PEKAN/BANDAR</strong></td>
      <td width="10%" align="center"><strong>JUMLAH PB</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListFail)
	#if ($list.NO == '') 
    	#set ($row = 'row1')
    #elseif ($list.NO % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.NO != '') 
      <td class="$row" valign="top" align="center">$list.NO</td>
      <td class="$row" valign="top"><a href="javascript:viewFail('$list.ID_PERMOHONAN', '$list.ID_HAKMILIK')" style="color:#0000FF">$list.NO_FAIL</a></td>
      <td class="$row" valign="top">$list.NO_HAKMILIK</td>
      <td class="$row" valign="top">$list.NO_LOTPT</td>
      <td class="$row" valign="top">$list.MUKIM</td>
      <td class="$row" valign="top" align="center">$list.JUMLAH_PB</td>
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
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="ID_HAKMILIK" name="ID_HAKMILIK" value="$ID_HAKMILIK" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script type="text/javascript">
  function viewFail(ID_PERMOHONAN, ID_HAKMILIK) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMdISenaraiPB&action2=viewFail&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_HAKMILIK=" + ID_HAKMILIK;
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function searchFail() {
      if (document.${formName}.CARIAN_NOFAIL.value == '') {
		  alert('Sila pastikan medan nombor fail diisi.');
		  document.${formName}.CARIAN_NOFAIL.focus();
		  return;
	  }
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMdISenaraiPB&action2=searchFail";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function emptyFail() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMdISenaraiPB&action2=";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>