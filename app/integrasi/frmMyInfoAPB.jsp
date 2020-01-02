<style type="text/css">
<!--
  .link {
    color:  #0000FF;
	cursor: pointer;
  }
-->
</style>
<fieldset>
  <legend><strong>CARIAN MAKLUMAT PERMOHONAN</strong>
  </legend><table width="100%">
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO FAIL</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="CARIAN_NOFAIL" type="text" id="CARIAN_NOFAIL" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_NOFAIL" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>TARIKH SURAT</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="CARIAN_TARIKHMOHON" type="text" id="CARIAN_TARIKHMOHON" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_TARIKHMOHON" size="15" maxlength="255" />&nbsp;<a href="javascript:displayDatePicker('CARIAN_TARIKHMOHON',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><strong>NAMA PEMOHON</strong></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><input name="CARIAN_NAMAPEMOHON" type="text" id="CARIAN_NAMAPEMOHON" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_NAMAPEMOHON" size="50" maxlength="255" /></td>
    </tr>
    
    <tr>
      <td align="right" valign="top" scope="row"><strong>NEGERI TAPAK</strong></td>
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
  <legend><strong>SENARAI FAIL CARIAN</strong></legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="3%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="12%"><strong>TARIKH SURAT</strong></td>
      <td width="20%"><strong>NAMA PEMOHON</strong></td>
      <td width="15%"><strong>NEGERI</strong></td>
      <td width="30%"><strong>AGENSI</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListCarian)
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
      <td class="$row" valign="top"><a href="javascript:viewAPB('$list.ID_ULASANTEKNIKAL')" style="color:#0000FF">$list.NO_FAIL</a></td>
      <td class="$row" valign="top">$list.TARIKH_SURAT</td>
      <td class="$row" valign="top">$list.NAMA_PEMOHON</td>
      <td class="$row" valign="top">$list.NEGERI</td>
      <td class="$row" valign="top">$list.AGENSI</td>
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
<br />
<fieldset>
  <legend><strong>SENARAI FAIL PERMOHONAN ULASAN TEKNIKAL BAGI $NAMA_AGENSI</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="20%" align="center"><strong>NEGERI PERAIRAN</strong></td>
      <td width="20%" align="center"><strong>TARIKH SURAT</strong></td>
      <td width="30%"><strong>NAMA PEMOHON</strong></td>
      <td width="10%" align="center"><strong>STATUS</strong></td>
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
		#if ($list.NO_FAIL == '')
        	#set ($list.NO_FAIL = '[TIADA NOMBOR FAIL]')
        #end    
      <td class="$row" valign="top" $row_color align="center">$list.NO</td>
      <td class="$row" valign="top" $row_color><a href="javascript:viewAPB('$list.ID_ULASANTEKNIKAL')" style="color:#0000FF">$list.NO_FAIL</a></td>
      <td class="$row" valign="top" $row_color align="center">$list.NEGERI_PERAIRAN</td>
      <td class="$row" valign="top" $row_color align="center">$list.TARIKH_SURAT</td>
      <td class="$row" valign="top" $row_color>$list.NAMA_PEMOHON</td>
      <td class="$row" valign="top" $row_color align="center">$list.STATUS</td>
    #else
      <td colspan="7" class="$row" style="text-align:center">Tiada Rekod</td>
    #end    </tr>
#end
    <tr>
      <td colspan="6">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<input type="hidden" id="ID_ULASANTEKNIKAL" name="ID_ULASANTEKNIKAL" />
<input type="hidden" id="action2" name="action2" />
<script type="text/javascript">
  function viewAPB(ID_ULASANTEKNIKAL) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPB&action2=viewAPB&ID_ULASANTEKNIKAL=" + ID_ULASANTEKNIKAL;
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function searchAPB() {
      if (document.${formName}.CARIAN_NOFAIL.value == '' && document.${formName}.CARIAN_TARIKHMOHON.value == '' && document.${formName}.CARIAN_NAMAPEMOHON.value == '' && document.${formName}.CARIAN_NOKPPEMOHON.value == '' && document.${formName}.CARIAN_NEGERI.value == '' && document.${formName}.CARIAN_DAERAH.value == '' && document.${formName}.CARIAN_MUKIM.value == '' && document.${formName}.CARIAN_AGENSI.value == '') {
		  alert('Sila pastikan salah satu medan carian diisi.');
		  document.${formName}.CARIAN_NOFAIL.focus();
		  return;
	  }
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMyInfoAPB&action2=searchAPB";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function emptyAPB() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMyInfoAPB&action2=";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function doChangeSOC() {
      document.${formName}.action2.value = "";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMyInfoAPB&action2=";
      doAjaxCall${formName}();
  }
</script>