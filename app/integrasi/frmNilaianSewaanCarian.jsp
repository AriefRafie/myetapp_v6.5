<fieldset>
  <legend><strong>CARIAN MAKLUMAT SI MATI</strong>
  </legend><table width="100%">
    <tr>
      <td align="right" valign="top" scope="row">No Fail</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoFail" type="text" id="Carian_NoFail" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoFail" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row">Nama Si Mati</td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><input name="Carian_NamaSiMati" type="text" id="Carian_NamaSiMati" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NamaSiMati" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">No KP Si Mati</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoKPSiMati" type="text" id="Carian_NoKPSiMati" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoKPSiMati" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row"><input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchNilaianSewaan();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyNilaianSewaan();" /></td>
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
      <td width="30%"><strong>NAMA SI MATI</strong></td>
      <td width="15%"><strong>NO KP SI MATI</strong></td>
      <td width="30%"><strong>ALAMAT SI MATI</strong></td>
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
      <td class="$row" valign="top"><a href="javascript:viewNilaianHAK('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NamaSiMati</td>
      <td class="$row" valign="top">$list.NoKPSiMati</td>
      <td class="$row" valign="top">$list.Alamat</td>
    #else
      <td colspan="5" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="5">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="mode" name="mode" value="$mode" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script>
function viewNilaianSewaan(ID_PERMOHONAN) {	
	document.${formName}.action2.value = "viewNilaianSewaan";
	document.${formName}.ID_PERMOHONAN.value = ID_PERMOHONAN;
	document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianSewaan";
	document.${formName}.method = "POST";
	document.${formName}.submit();
}
function searchNilaianSewaan() {
	document.${formName}.action2.value = "searchNilaianSewaan";
	document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianSewaan";
	document.${formName}.method = "POST";
	document.${formName}.submit();
}
function emptyNilaianSewaan() {
	document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianSewaan";
	document.${formName}.method = "POST";
}
</script>