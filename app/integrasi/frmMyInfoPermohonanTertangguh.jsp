<fieldset>
  <legend><strong>SENARAI FAIL BORANG J</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="15%"><strong>TARIKH HANTAR</strong></td>
      <td width="20%" align="center"><strong>STATUS</strong></td>
      <td width="5%" align="center"><input type="checkbox" id="CHKALL_BORANGJ" name="CHKALL_BORANGJ" value="" onclick="doCheckAll('CHKALL_BORANGJ', 'CHK_BORANGJ')" /></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListBorangJ)
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
      <td class="$row" valign="top"><a href="javascript:viewBorangJ('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NoPermohonan</td>
      <td class="$row" valign="top">$list.TarikhHantar</td>
      <td class="$row" valign="top" align="center">$list.Status</td>
      <td class="$row" valign="top" align="center"><input type="checkbox" id="CHK_BORANGJ" name="CHK_BORANGJ" value="$list.IDPermohonan" /></td>
    #else
      <td colspan="6" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
  </table>
</fieldset>
<div style="text-align:right">
  <input type="button" id="cmdEmailBorangJ" name="cmdEmailBorangJ" value="HANTAR E-MAIL PERINGATAN" onclick="sendEmail('BorangJ');" />
</div>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL NILAIAN HARTA TAK ALIH (ADA HAKMILIK)</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="15%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="20%" align="center"><strong>STATUS</strong></td>
      <td width="5%" align="center"><input type="checkbox" id="CHKALL_HTAAH" name="CHKALL_HTAAH" value="" onclick="doCheckAll('CHKALL_HTAAH', 'CHK_HTAAH')" /></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListNilaianHTAAH)
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
      <td class="$row" valign="top"><a href="javascript:viewNilaianHTAAH('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NoPermohonan</td>
      <td class="$row" valign="top" align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" align="center">$list.Status</td>
      <td class="$row" valign="top" align="center"><input type="checkbox" id="CHK_HTAAH" name="CHK_HTAAH" value="$list.IDPermohonan" /></td>
    #else
      <td colspan="6" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
  </table>
</fieldset>
<br />
<div style="text-align:right">
  <input type="button" id="cmdEmailBorangJ" name="cmdEmailBorangJ" value="HANTAR E-MAIL PERINGATAN" onclick="sendEmail('HTAAH');" />
</div>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL NILAIAN HARTA TAK ALIH (TIADA HAKMILIK)</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="15%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="20%" align="center"><strong>STATUS</strong></td>
      <td width="5%" align="center"><input type="checkbox" id="CHKALL_HTATH" name="CHKALL_HTATH" value="" onclick="doCheckAll('CHKALL_HTATH', 'CHK_HTATH')" /></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListNilaianHTATH)
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
      <td class="$row" valign="top"><a href="javascript:viewNilaianHTAAH('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NoPermohonan</td>
      <td class="$row" valign="top" align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" align="center">$list.Status</td>
      <td class="$row" valign="top" align="center"><input type="checkbox" id="CHK_HTATH" name="CHK_HTATH" value="$list.IDPermohonan" /></td>
    #else
      <td colspan="6" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
  </table>
</fieldset>
<br />
<div style="text-align:right">
  <input type="button" id="cmdEmailBorangJ" name="cmdEmailBorangJ" value="HANTAR E-MAIL PERINGATAN" onclick="sendEmail('HTATH');" />
</div>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL NILAIAN HARTA ALIH (KENDERAAN)</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="15%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="20%" align="center"><strong>STATUS</strong></td>
      <td width="5%" align="center"><input type="checkbox" id="CHKALL_HAK" name="CHKALL_HAK" value="" onclick="doCheckAll('CHKALL_HAK', 'CHK_HAK')" /></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListNilaianHAK)
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
      <td class="$row" valign="top"><a href="javascript:viewNilaianHTAAH('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top">$list.NoPermohonan</td>
      <td class="$row" valign="top" align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" align="center">$list.Status</td>
      <td class="$row" valign="top" align="center"><input type="checkbox" id="CHK_HAK" name="CHK_HAK" value="$list.IDPermohonan" /></td>
    #else
      <td colspan="6" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
  </table>
</fieldset>
<br />
<div style="text-align:right">
  <input type="button" id="cmdEmailBorangJ" name="cmdEmailBorangJ" value="HANTAR E-MAIL PERINGATAN" onclick="sendEmail('HAK');" />
</div>
<br />
<fieldset>
  <legend><strong>SENARAI FAIL BORANG LAMPIRAN A1</strong>
  </legend><table width="100%" cellspacing="0" cellpadding="2" style="border:1px solid #000">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="15%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="20%" align="center"><strong>STATUS</strong></td>
      <td width="5%" align="center"><input type="checkbox" id="CHKALL_BORANGLAMPIRANA1" name="CHKALL_BORANGLAMPIRANA1" value="" onclick="doCheckAll('CHKALL_BORANGLAMPIRANA1', 'CHK_BORANGLAMPIRANA1')" /></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListBorangLampiranA1)
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
      <td class="$row" valign="top" align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" align="center">$list.Status</td>
      <td class="$row" valign="top" align="center"><input type="checkbox" id="CHK_BORANGLAMPIRANA1" name="CHK_BORANGLAMPIRANA1" value="$list.IDPermohonan" /></td>
    #else
      <td colspan="6" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
  </table>
</fieldset>
<br />
<div style="text-align:right">
  <input type="button" id="cmdEmailBorangJ" name="cmdEmailBorangJ" value="HANTAR E-MAIL PERINGATAN" onclick="sendEmail('BorangLampiranA1');" />
</div>
<br />
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" />
<input type="hidden" id="mode" name="mode" value="$mode" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script>
function viewBorangJ(ID_PERMOHONAN) {
	document.${formName}.action = "$EkptgUtil.getTabID("JKSM",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewBorangJ&action2=viewBorangJ&ID_PERMOHONAN=" + ID_PERMOHONAN;
	document.${formName}.submit();
}
function viewNilaianHTAAH(ID_PERMOHONAN) {	
	document.${formName}.action = "$EkptgUtil.getTabID("JPPH",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA&selectedTab=0&ID_PERMOHONAN=" + ID_PERMOHONAN;
	document.${formName}.submit();
}
function viewNilaianHTATH(ID_PERMOHONAN) {	
	document.${formName}.action = "$EkptgUtil.getTabID("JPPH",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA&selectedTab=1&ID_PERMOHONAN=" + ID_PERMOHONAN;
	document.${formName}.submit();
}
function viewNilaianHAK(ID_PERMOHONAN) {	
	document.${formName}.action = "$EkptgUtil.getTabID("JPPH",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaAlihKenderaan&action2=viewNilaianHAK&ID_PERMOHONAN=" + ID_PERMOHONAN;
	document.${formName}.submit();
}
function viewBorangLampiranA1(ID_PERMOHONAN) {
	document.${formName}.action = "$EkptgUtil.getTabID("JPBD",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewBorangLampiranA1&action2=viewBorangLampiranA1&ID_PERMOHONAN=" + ID_PERMOHONAN;
	document.${formName}.submit();
}
function doCheckAll(sourceID, targetID) {
    if (document.getElementById(sourceID).checked == true){
        if (document.getElementById(targetID).length == null){
            document.getElementById(targetID).checked = true;
        } else {
            for (i = 0; i < document.getElementById(targetID).length; i++){
                document.getElementById(targetID)[i].checked = true;
            }
        }
    } else {
        if (document.getElementById(targetID).length == null){
            document.getElementById(targetID).checked = false;
        } else {
            for (i = 0; i < document.getElementById(targetID).length; i++){
                document.getElementById(targetID)[i].checked = false;
            }
        }
    }
}
function sendEmail(formType) {
	document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMyInfoPermohonanTertangguh&action2=sendEmail&formType=" + formType;
	document.${formName}.submit();
}
</script>