#if ($deleteMaklumatKebangkrapan == 'true')
<div class="success">Permohonan semakan kebangkrapan telah berjaya dihapuskan.</div>
<br />
#end
<fieldset>
  <legend><strong>CARIAN MAKLUMAT PEMOHON</strong>
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
      <td width="29%" align="right" valign="top" scope="row">Nama Pemohon</td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><input name="Carian_NamaPemohon" type="text" id="Carian_NamaPemohon" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NamaPemohon" size="50" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">No KP Pemohon</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoKPPemohon" type="text" id="Carian_NoKPPemohon" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoKPPemohon" size="50" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
#if ($READONLY_JKPTG == '')    
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row">
        <input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchMaklumatKebangkrapan();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyMaklumatKebangkrapan();" />
      </td>
    </tr>
#end    
    <tr>
      <td colspan="3" align="center" valign="top" scope="row">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI DATA</strong></legend>
#if ($READONLY_JKPTG == '')  
	#parse("app/utils/record_paging.jsp")
#end
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="20%"><strong>NAMA PEMOHON</strong></td>
      <td width="15%"><strong>NO KP PEMOHON</strong></td>
      <td width="25%"><strong>ALAMAT PEMOHON</strong></td>
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
      <td class="$row" valign="top"><a href="javascript:viewMaklumatKebangkrapan('$list.ID_PERMOHONAN', '$list.ID_PB')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NoPermohonan</td>
      <td class="$row" valign="top">$list.NamaPemohon</td>
      <td class="$row" valign="top">$list.NoKPPemohon</td>
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
<input type="hidden" id="ID_PB" name="ID_PB" />
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script type="text/javascript">
  function viewMaklumatKebangkrapan(ID_PERMOHONAN, ID_PB) {	
      document.${formName}.action = "?action2=viewMaklumatKebangkrapan&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_PB=" + ID_PB;
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function searchMaklumatKebangkrapan() {
      if (document.${formName}.Carian_NoFail.value == '' && document.${formName}.Carian_NoPermohonan.value == '' && document.${formName}.Carian_NamaPemohon.value == '' && document.${formName}.Carian_NoKPPemohon.value == '') {
		  alert('Sila pastikan salah satu medan carian diisi.');
		  document.${formName}.Carian_NoFail.focus();
		  return;
	  }
      document.${formName}.action = "?action2=searchMaklumatKebangkrapan";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function emptyMaklumatKebangkrapan() {
      document.${formName}.action = "?action2=";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>