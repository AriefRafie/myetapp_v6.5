<fieldset>
<legend><strong>CARIAN REKOD</strong>
</legend>
<table width="100%">
    <tr>
      <td align="right" valign="top" scope="row"><strong>NEGERI</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selCarianNegeri</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>DAERAH</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selCarianDaerah</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>BANDAR / PEKAN / MUKIM</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selCarianMukim</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>AGENSI</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selCarianAgensi</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO FAIL</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="CARIAN_NOFAIL" type="text" id="CARIAN_NOFAIL" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_NOFAIL" size="50" maxlength="255" placeholder="Nombor fail permohonan" /></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><strong>TAJUK FAIL</strong></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><input name="CARIAN_TAJUKFAIL" type="text" id="CARIAN_TAJUKFAIL" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_TAJUKFAIL" size="50" maxlength="255" placeholder="Tajuk fail permohonan" /></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row"><input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchRekod();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyRekod();" /></td>
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
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>TARIKH MOHON</strong></td>
      <td width="20%"><strong>NAMA PEMOHON</strong></td>
      <td width="15%"><strong>NEGERI</strong></td>
      <td width="25%"><strong>AGENSI</strong></td>
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
      <td class="$row" valign="top" align="center">$list.No</td>
      <td class="$row" valign="top"><a href="javascript:viewRekod('$list.ID_PERMOHONAN')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.TarikhMohon</td>
      <td class="$row" valign="top">$list.NamaPemohon</td>
      <td class="$row" valign="top">$list.Negeri</td>
      <td class="$row" valign="top">$list.Agensi</td>
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
<input name="action2" id="action2" type="hidden" value="$action2" />
<script type="text/javascript">
  function doChangeSOC() {
      document.${formName}.action2.value = "";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMySPATA&action2=";
      doAjaxCall${formName}();
  }
  function viewRekod(ID_PERMOHONAN) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMySPATA&action2=viewRekod&ID_PERMOHONAN=" + ID_PERMOHONAN;
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function searchRekod() {
      if (document.${formName}.CARIAN_NOFAIL.value == '' && document.${formName}.CARIAN_TARIKHMOHON.value == '' && document.${formName}.CARIAN_NAMAPEMOHON.value == '' && document.${formName}.CARIAN_NOKPPEMOHON.value == '' && document.${formName}.CARIAN_NEGERI.value == '' && document.${formName}.CARIAN_DAERAH.value == '' && document.${formName}.CARIAN_MUKIM.value == '' && document.${formName}.CARIAN_AGENSI.value == '') {
		  alert('Sila pastikan salah satu medan carian diisi.');
		  document.${formName}.CARIAN_NOFAIL.focus();
		  return;
	  }
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMySPATA&action2=searchRekod";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function emptyRekod() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMySPATA&action2=";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>