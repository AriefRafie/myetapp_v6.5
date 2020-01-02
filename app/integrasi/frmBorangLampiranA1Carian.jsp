#if ($deleteBorangLampiranA1 == 'true')
<div class="success">Permohonan telah berjaya dibatalkan.</div>
<br />
#end
<fieldset>
  <legend><strong>CARIAN MAKLUMAT PERMOHONAN</strong>
  </legend><table width="100%">
    <tr>
      <td align="right" valign="top" scope="row">No Fail</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoFail" type="text" id="Carian_NoFail" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoFail" size="50" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">No Permohonan</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoPermohonan" type="text" id="Carian_NoPermohonan" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoPermohonan" size="50" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row">No Lot</td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><input name="Carian_NoLot" type="text" id="Carian_NoLot" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoLot" size="50" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">No Syit</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoSyit" type="text" id="Carian_NoSyit" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoSyit" size="50" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row">
#if ($isJPBDUser != 'true')      
        <input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchBorangLampiranA1();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyBorangLampiranA1();" />
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
  <legend><strong>SENARAI DATA</strong></legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="20%"><strong>NO LOT</strong></td>
      <td width="15%"><strong>NO SYIT</strong></td>
      <td width="30%"><strong>MUKIM / PEKAN / BANDAR</strong></td>
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
      <td class="$row" valign="top"><a href="javascript:viewBorangLampiranA1('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NoPermohonan</td>
      <td class="$row" valign="top">$list.NoLot</td>
      <td class="$row" valign="top">$list.NoSyit</td>
      <td class="$row" valign="top">$list.Mukim</td>
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
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script>
function viewBorangLampiranA1(ID_PERMOHONAN) {	
	document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewBorangLampiranA1&action2=viewBorangLampiranA1&ID_PERMOHONAN=" + ID_PERMOHONAN;
	document.${formName}.method = "POST";
	document.${formName}.submit();
}
function searchBorangLampiranA1() {
	if (document.${formName}.Carian_NoFail.value == "" && document.${formName}.Carian_NoPermohonan.value == "" && document.${formName}.Carian_NoLot.value == "" && document.${formName}.Carian_NoSyit.value == "") {
		alert("Sila pastikan sekurang-kurangnya salah satu medan carian diisi.");
		document.${formName}.Carian_NoFail.focus();
		return false;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewBorangLampiranA1&action2=searchBorangLampiranA1";
	document.${formName}.submit();
}
function emptyBorangLampiranA1() {
	document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewBorangLampiranA1&action2=";
}
</script>