#if ($deleteNilaianHAK == 'true')
<fieldset>
  <div class="success">Permohonan nilaian telah berjaya dihapuskan.</div>
</fieldset>
<br />
#end
#if ($returnNilaianHAK == 'true')
<fieldset>
  <div class="success">Permohonan nilaian telah dikembalikan ke JKPTG.</div>
</fieldset>
<br />
#end
#if ($isJPPHUser == 'true')
	#set ($READONLY_JPPH = 'readonly="readonly" class="disabled"')
#end
<fieldset>
  <legend><strong>CARIAN MAKLUMAT SI MATI</strong>
  </legend><table width="100%">
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO FAIL</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoFail" type="text" id="Carian_NoFail" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoFail" size="50" maxlength="255" $READONLY_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO KENDERAAN</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoPendaftaran" type="text" id="Carian_NoPendaftaran" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoPendaftaran" size="50" maxlength="255" $READONLY_JPPH />      </td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><strong>NEGERI KENDERAAN BERADA</strong></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top">$selectNegeriKenderaanBerada</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>NAMA SIMATI</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NamaSiMati" type="text" id="Carian_NamaSiMati" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NamaSiMati" size="50" maxlength="255" $READONLY_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><strong>NO KP SIMATI</strong></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoKPSiMati" type="text" id="Carian_NoKPSiMati" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoKPSiMati" size="50" maxlength="255" $READONLY_JPPH /></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row">
#if ($isJPPHUser != 'true')
        <input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchNilaianHTA();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyNilaianHTA();" />
#end      </td>
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
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PENDAFTARAN</strong></td>
      <td width="20%"><strong>NEGERI KENDERAAN BERADA</strong></td>
      <td width="15%"><strong>NAMA SIMATI</strong></td>
      <td width="30%"><strong>NO KP SIMATI</strong></td>
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
      <td class="$row" valign="top"><a href="javascript:viewNilaianHA('$list.IDPermohonan', '$list.IDHA')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NoPendaftaran</td>
      <td class="$row" valign="top">$list.NegeriKenderaanBerada</td>
      <td class="$row" valign="top">$list.NamaSiMati</td>
      <td class="$row" valign="top">$list.NoKPSiMati</td>
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
<input type="hidden" id="ID_HA" name="ID_HA" value="$ID_HA" />
<input type="hidden" id="mode" name="mode" value="$mode" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script>
function viewNilaianHA(ID_PERMOHONAN, ID_HA) {	
	document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=viewNilaianHAK&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_HA=" + ID_HA;
	document.${formName}.method = "POST";
	document.${formName}.submit();
}
function searchNilaianHTA() {
	if (document.${formName}.Carian_NoFail.value == '' && document.${formName}.Carian_NoPendaftaran.value == '' && document.${formName}.Carian_NamaSiMati.value == '' && document.${formName}.Carian_NoKPSiMati.value == '') {
		alert('Sila pastikan salah satu medan carian diisi.');
		document.${formName}.Carian_NoFail.focus();
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=searchNilaianHAK";
	document.${formName}.method = "POST";
	document.${formName}.submit();
}
function emptyNilaianHTA() {
	document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=";
    document.${formName}.method = "POST";
	document.${formName}.submit();
}
</script>