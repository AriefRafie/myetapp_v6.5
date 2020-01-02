<style>
  .link {
  color:#0000FF;
  }
</style>
<fieldset>
  <legend><strong>MAKLUMAT FAIL</strong>
  </legend><table width="100%">
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO. FAIL PERMOHONAN</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top" class="link">$!NO_FAIL</td>
    </tr>
#if ($NO_RUJUKAN_PTG != '')
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><strong>NO. RUJUKAN PTG</strong></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" valign="top" class="link">$!NO_RUJUKAN_PTG</td>
    </tr>
#end
#if ($NO_RUJUKAN_PTD != '')
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO. RUJUKAN PTD</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top" class="link">$!NO_RUJUKAN_PTD</td>
    </tr>
#end
    <tr>
      <td align="right" valign="top" scope="row"><strong>KEMENTERIAN</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top" class="link">$!NAMA_KEMENTERIAN</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>NAMA AGENSI</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top" class="link">$!NAMA_AGENSI</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>NEGERI</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top" class="link">$!NEGERI</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>DAERAH / JAJAHAN</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top" class="link">$!DAERAH</td>
    </tr>
    
    <tr>
      <td align="right" valign="top" scope="row"><strong>TUJUAN PENGAMBILAN</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top" class="link" style="text-transform:uppercase">$!TUJUAN</td>
    </tr>
    
    
    <tr>
      <td colspan="3" align="center" valign="top" scope="row">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI PIHAK BERKEPENTINGAN</strong></legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>Bil</strong></td>
      <td width="30%"><strong>Nama PB</strong></td>
      <td width="20%"><strong>Jenis PB</strong></td>
<!--      <td width="20%" align="center"><strong>Bahagian &amp; Syer Perkongsian Bahagian (Jika Ada)</strong></td> -->
      <td width="15%" align="center"><strong>Status Kebangkrapan</strong></td>
      <td width="10%" align="center">&nbsp;<!--<input type="checkbox" id="checkAll" name="checkAll" onclick="checkAll(this);" />--></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListPB)
	#if ($list.Bil == '') 
    	#set ($row = 'row1')
    #elseif ($list.Bil % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.Bil != '') 
      <td class="$row" valign="top" align="center">$list.Bil</td>
      <td class="$row" valign="top">$list.NamaPB</td>
      <td class="$row" valign="top">$list.JenisPB</td>
<!--      <td class="$row" valign="top" align="center">$list.Bahagian</td> -->
      <td class="$row" valign="top" align="center">$list.StatusBangkrap</td>
      <td class="$row" valign="top" align="center"><input type="checkbox" id="ID_PB" name="ID_PB" value="$!list.ID_PB" /></td>
    #else
      <td colspan="6" class="$row" style="text-align:center">Tiada Rekod</td>
    #end    
    </tr>
#end
    <tr>
      <td colspan="6" align="right"><input type="button" id="cmdSend" name="cmdSend" value="Hantar" onclick="sendStatus();" /></td>
    </tr>
  </table>
</fieldset>
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="ID_HAKMILIK" name="ID_HAKMILIK" value="$ID_HAKMILIK" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script type="text/javascript">
  function viewFail(ID_PERMOHONAN) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMdISenaraiPB&action2=viewFail&ID_PERMOHONAN=" + ID_PERMOHONAN;
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
  function sendStatus() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMdISenaraiPB&action2=sendStatus";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function checkAll(source) {
      for (i = 0; i < document.getElementsByName('ID_PB').length; i++) {
	      document.getElementsByName('ID_PB').checked = true;
	  }
  }
</script>