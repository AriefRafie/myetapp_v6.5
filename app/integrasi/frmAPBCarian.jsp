#if ($deleteAPB == 'true')
<div class="success">Permohonan mendapatkan ulasan teknikal telah berjaya dibatalkan.</div>
<br />
#end
#if ($returnAPB == 'true')
<div class="success">Permohonan mendapatkan ulasan teknikal telah berjaya dikembalikan.</div>
<br />
#end
<fieldset>
  <legend><strong>CARIAN MAKLUMAT PERMOHONAN</strong>
  </legend><table width="100%">
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO FAIL</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="CARIAN_NOFAIL" type="text" id="CARIAN_NOFAIL" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_NOFAIL" size="50" maxlength="255" placeholder="Nombor fail permohonan" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>TARIKH SURAT</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="CARIAN_TARIKHSURAT" type="text" id="CARIAN_TARIKHSURAT" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_TARIKHSURAT" size="15" maxlength="255" placeholder="Tarikh permohonan dibuat" />&nbsp;<a href="javascript:displayDatePicker('CARIAN_TARIKHSURAT',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><strong>NAMA PEMOHON</strong></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><input name="CARIAN_NAMAPEMOHON" type="text" id="CARIAN_NAMAPEMOHON" onkeyup="this.value=this.value.toUpperCase();" value="$!CARIAN_NAMAPEMOHON" size="50" maxlength="255" placeholder="Nama pemohon" /></td>
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
  <legend><strong>SENARAI DATA</strong></legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>TARIKH SURAT</strong></td>
      <td width="20%"><strong>NAMA PEMOHON</strong></td>
      <td width="15%"><strong>NEGERI TAPAK</strong></td>
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
      <td class="$row" valign="top" align="center">$list.NO</td>
      <td class="$row" valign="top"><a href="javascript:viewAPB('$list.ID_PERMOHONAN')" style="color:#0000FF">$list.NO_FAIL</a></td>
      <td class="$row" valign="top">$list.TARIKH_SURAT</td>
      <td class="$row" valign="top">$list.NAMA_PEMOHON</td>
      <td class="$row" valign="top">$list.NEGERI_TAPAK</td>
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
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script type="text/javascript">
  function doChangeSOC() {
      document.${formName}.action2.value = "";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPB&action2=";
      doAjaxCall${formName}();
  }
  function viewAPB(ID_PERMOHONAN) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPB&action2=viewAPB&ID_PERMOHONAN=" + ID_PERMOHONAN;
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function searchAPB() {
      if (document.${formName}.CARIAN_NOFAIL.value == '' && document.${formName}.CARIAN_TARIKHMOHON.value == '' && document.${formName}.CARIAN_NAMAPEMOHON.value == '' && document.${formName}.CARIAN_NOKPPEMOHON.value == '' && document.${formName}.CARIAN_NEGERI.value == '' && document.${formName}.CARIAN_DAERAH.value == '' && document.${formName}.CARIAN_MUKIM.value == '' && document.${formName}.CARIAN_AGENSI.value == '') {
		  alert('Sila pastikan salah satu medan carian diisi.');
		  document.${formName}.CARIAN_NOFAIL.focus();
		  return;
	  }
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPB&action2=searchAPB";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function emptyAPB() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPB&action2=";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>