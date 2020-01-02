<fieldset>
  <legend><strong>SENARAI FAIL NILAIAN HARTA TAK ALIH (ADA HAKMILIK)</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="20%"><strong>NAMA SI MATI</strong></td>
      <td width="20%"><strong>NAMA PEMOHON</strong></td>
      <td width="15%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="20%" align="center"><strong>STATUS</strong></td>
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
    	#if ($list.Status.toUpper != 'SELESAI')
            #if ($list.date30 == '1')
                #set ($row_color = 'style="color:#FF0000"')
            #elseif ($list.date14 == '1')
                #set ($row_color = 'style="color:#009900"')
            #elseif ($list.date05 == '1')
                #set ($row_color = 'style="color:#0000FF"')
            #else
                #set ($row_color = '')
            #end
        #end
      <td class="$row" valign="top" $row_color align="center">$list.No</td>
      <td class="$row" valign="top" $row_color><a href="javascript:viewNilaianHTAAH('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top" $row_color>$list.NoPermohonan</td>
      <td class="$row" valign="top" $row_color>$list.NamaSiMati</td>
      <td class="$row" valign="top" $row_color>$list.NamaPemohon</td>
      <td class="$row" valign="top" $row_color align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" $row_color align="center">$list.Status</td>
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
<br />
<fieldset>
  <legend><strong>SENARAI FAIL NILAIAN HARTA TAK ALIH (TIADA HAKMILIK)</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="20%"><strong>NAMA SI MATI</strong></td>
      <td width="20%"><strong>NAMA PEMOHON</strong></td>
      <td width="15%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="20%" align="center"><strong>STATUS</strong></td>
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
    	#if ($list.Status.toUpper != 'SELESAI')
            #if ($list.date30 == '1')
                #set ($row_color = 'style="color:#FF0000"')
            #elseif ($list.date14 == '1')
                #set ($row_color = 'style="color:#009900"')
            #elseif ($list.date05 == '1')
                #set ($row_color = 'style="color:#0000FF"')
            #else
                #set ($row_color = '')
            #end
        #end
      <td class="$row" valign="top" $row_color align="center">$list.No</td>
      <td class="$row" valign="top" $row_color><a href="javascript:viewNilaianHTATH('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top" $row_color>$list.NoPermohonan</td>
      <td class="$row" valign="top" $row_color>$list.NamaSiMati</td>
      <td class="$row" valign="top" $row_color>$list.NamaPemohon</td>
      <td class="$row" valign="top" $row_color align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" $row_color align="center">$list.Status</td>
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
<br />
<fieldset>
  <legend><strong>SENARAI FAIL NILAIAN HARTA ALIH (KENDERAAN)</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="20%"><strong>NAMA SI MATI</strong></td>
      <td width="20%"><strong>NAMA PEMOHON</strong></td>
      <td width="15%" align="center"><strong>TARIKH HANTAR</strong></td>
      <td width="20%" align="center"><strong>STATUS</strong></td>
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
    	#if ($list.Status.toUpper != 'SELESAI')
            #if ($list.date30 == '1')
                #set ($row_color = 'style="color:#FF0000"')
            #elseif ($list.date14 == '1')
                #set ($row_color = 'style="color:#009900"')
            #elseif ($list.date05 == '1')
                #set ($row_color = 'style="color:#0000FF"')
            #else
                #set ($row_color = '')
            #end
        #end
      <td class="$row" valign="top" $row_color align="center">$list.No</td>
      <td class="$row" valign="top" $row_color><a href="javascript:viewNilaianHAK('$list.IDPermohonan')" style="color:#0000FF">$list.NoFail</a></td>
      <td class="$row" valign="top" $row_color>$list.NoPermohonan</td>
      <td class="$row" valign="top" $row_color>$list.NamaSiMati</td>
      <td class="$row" valign="top" $row_color>$list.NamaPemohon</td>
      <td class="$row" valign="top" $row_color align="center">$list.TarikhHantar</td>
      <td class="$row" valign="top" $row_color align="center">$list.Status</td>
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
<br />
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" />
<input type="hidden" id="mode" name="mode" value="$mode" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script>
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
</script>