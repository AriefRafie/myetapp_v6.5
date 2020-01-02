#if ($deleteMaklumat == 'true')
<fieldset>
  <div class="success">Permohonan mendapatkan nilaian pajakan tanah persekutuan telah berjaya dihapuskan.</div>
</fieldset>
<br />
#end
#if ($returnMaklumat == 'true')
<fieldset>
  <div class="success">Permohonan mendapatkan nilaian pajakan tanah persekutuan telah berjaya dikembalikan ke JKPTG.</div>
</fieldset>
<br />
#end
#if ($READONLY_FOR_JPPH != '')
	#set ($READONLY_FOR_JPPH = ' readonly="readonly" class="disabled"')
	#set ($DISABLE_FOR_JPPH = ' class="disabled"')
#end
#if ($READONLY_FOR_JKPTG != '')
	#set ($READONLY_FOR_JKPTG = ' readonly="readonly" class="disabled"')
	#set ($DISABLE_FOR_JKPTG = ' class="disabled"')
#end
<fieldset>
  <legend><strong>CARIAN MAKLUMAT HARTA TANAH PERSEKUTUAN</strong>
  </legend><table width="100%">
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO FAIL</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Cari_NoFail" type="text" id="Cari_NoFail" onkeyup="this.value=this.value.toUpperCase();" value="$!Cari_NoFail" size="50" maxlength="255" $READONLY_FOR_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>NEGERI</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selectNegeri</td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><strong>DAERAH</strong></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top">$selectDaerah</td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><strong>MUKIM</strong></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top">$selectMukim</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO HAKMILIK</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Cari_NoHakmilik" type="text" id="Cari_NoHakmilik" onkeyup="this.value=this.value.toUpperCase();" value="$!Cari_NoHakmilik" size="50" maxlength="255" $READONLY_FOR_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO PT / LOT</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Cari_NoPTLot" type="text" id="Cari_NoPTLot" onkeyup="this.value=this.value.toUpperCase();" value="$!Cari_NoPTLot" size="50" maxlength="255" $READONLY_FOR_JPPH /></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row">
#if ($isJPPHUser != 'true')
        <input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchMaklumat();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyMaklumat();" />
#end      
      </td>
    </tr> 
    <tr>
      <td colspan="3" align="center" valign="top" scope="row">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI MAKLUMAT</strong></legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NEGERI</strong></td>
      <td width="15%"><strong>DAERAH</strong></td>
      <td width="15%"><strong>MUKIM</strong></td>
      <td width="15%"><strong>NO HAKMILIK</strong></td>
      <td width="15%"><strong>NO PT / LOT</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $SenaraiFail)
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
      <td class="$row" valign="top"><a href="javascript:viewMaklumat('$list.IDPermohonan', '$list.IDHM')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.Negeri</td>
      <td class="$row" valign="top">$list.Daerah</td>
      <td class="$row" valign="top">$list.Mukim</td>
      <td class="$row" valign="top">$list.NoHakmilik</td>
      <td class="$row" valign="top">$list.NoPTLot</td>
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
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="ID_HM" name="ID_HM" value="$ID_HM" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script>
  function doChange() {
	  document.${formName}.action2.value = "view";
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=view";
	  doAjaxCall${formName}();
  }
  function viewMaklumat(ID_PERMOHONAN, ID_HM) {
	  document.${formName}.action2.value = "view";
	  document.${formName}.ID_PERMOHONAN.value = ID_PERMOHONAN;
	  document.${formName}.ID_HM.value = ID_HM;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=view";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function searchMaklumat() {
	  if (document.${formName}.Cari_NoFail.value == '' && document.${formName}.Cari_NoHakmilik.value == '' && document.${formName}.Cari_NoPTLot.value == '' && document.${formName}.Cari_Negeri.value == '' && document.${formName}.Cari_Daerah.value == '') {
		  alert('Sila pastikan salah satu medan carian diisi.');
  		  document.${formName}.Cari_NoFail.focus();
		  return;
	  }
	  document.${formName}.action2.value = "search";
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=search";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function emptyMaklumat() {
	  document.${formName}.action2.value = "";
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=";
      document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
</script>