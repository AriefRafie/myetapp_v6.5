<!--
condition hidden field untuk store id div yang tengah bukak mengikut jenis skrin
-->
<input type="hidden" id="divIdwaris" name="divIdwaris" value="" />
<input type="hidden" id="divIdob" name="divIdob" value="" />
<input type="hidden" id="divIdsaksi" name="divIdsaksi" value="" />
<input type="hidden" id="divIdpemiutang" name="divIdpemiutang" value="" />
<input type="hidden" id="divIdpenghutang" name="divIdpenghutang" value="" />
<input type="hidden" id="divIdhtaah" name="divIdhtaah" value="" />
<input type="hidden" id="divIdhtaahx" name="divIdhtaahx" value="" />
<input type="hidden" id="divIdha" name="divIdha" value="" />
<input type="hidden" id="divIdpeguam" name="divIdpeguam" value="" />
<input type="hidden" id="divIdkeputusan" name="divIdkeputusan" value="" />
<input type="hidden" id="divIdperubahan" name="divIdperubahan" value="" />
<input type="hidden" id="divIdketeranganhadir" name="divIdketeranganhadir" value="" />
<input type="hidden" id="divIdcetakan" name="divIdcetakan" value="" />


<div id="viewPerbicaraan">
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-top:5px" class="classFade">
<tr>
<td>
<fieldset>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px"  class="box_shadow">
<tr class="table_header" >
<td width="2%" class="underline_td_sub">
</td>
<td width="58%" class="underline_td_sub">
<strong>MAKLUMAT PERBICARAAN</strong></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div id="viewHeader" style="margin-bottom:5px" >
#parse("app/ppk/BicaraInteraktif/viewHeader.jsp")
<script>
/* 
$jquery(document).ready(function () {
doDivAjaxCall$formname('viewHeader','showHeader','ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI');			 	  
});
*/
</script>
</div>

<div id="view_tukarpegawai"></div>




<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;" class="box_shadow" onclick="openCloseBantahan('bantahan','$ID_PERMOHONANSIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','$ID_PEMOHON')">
<tr class="table_header" >
<td width="2%" class="underline_td_sub">
</td>
<td width="58%" class="underline_td_sub">
<span id="icon_bantahan" >> </span><strong>BANTAHAN</strong><input type="hidden" name="flag_bantahan" id="flag_bantahan" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px" id="view_bantahan"></div>



<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;" class="box_shadow" onclick="openCloseKehadiran('kehadiran','$ID_PERMOHONANSIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','$ID_PEMOHON')">
<tr class="table_header" >
<td width="2%" class="underline_td_sub">
</td>
<td width="58%" class="underline_td_sub">
<span id="icon_kehadiran" >> </span><strong>KEHADIRAN</strong><input type="hidden" name="flag_kehadiran" id="flag_kehadiran" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px" id="view_kehadiran"></div>





<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;"  class="box_shadow" >
<tr class="table_header" >
<td width="2%" class="underline_td_sub">
</td>
<td width="98%" class="underline_td_sub">
<strong>MAKLUMAT PERMOHONAN</strong>
</td>
</tr>
</table>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openClose('simati','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKSIMATI','ID_SIMATI','$ID_PERMOHONANSIMATI')">
<tr class="table_header" >
<td width="4%" class="underline_td_sub">
</td>
<td width="56%" class="underline_td_sub">
<span id="icon_simati" >> </span><strong>SIMATI</strong><input type="hidden" name="flag_simati" id="flag_simati" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="view_simati"></div>	

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openClose('pemohon','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKPEMOHON','ID_PEMOHON','$ID_PERMOHONANSIMATI')">
<tr class="table_header" >
<td width="4%" class="underline_td_sub">
</td>
<td width="56%" class="underline_td_sub">
<span id="icon_pemohon" >> </span><strong>PEMOHON</strong><input type="hidden" name="flag_pemohon" id="flag_pemohon" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px" id="view_pemohon"></div>	

#if($viewPerbicaraan.LANTIK_PEGUAM == "Y")
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openCloseSenarai('peguam','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKPEGUAM','ID_PEGUAM','$ID_PERMOHONANSIMATI','current','','$ID_PEMOHON')">
<tr class="table_header" >
<td width="4%" class="underline_td_sub">
</td>
<td width="56%" class="underline_td_sub">
<span id="icon_peguam" >> </span><strong>PEGUAM</strong><input type="hidden" name="flag_senarai_peguam" id="flag_senarai_peguam" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="senarai_peguamcurrent"></div>
#end

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openCloseSenarai('waris','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKOBPERMOHONAN','ID_OB','$ID_PERMOHONANSIMATI','current','','$ID_PEMOHON')">
<tr class="table_header" >
<td width="4%" class="underline_td_sub">
</td>
<td width="56%" class="underline_td_sub">
<span id="icon_waris" >> </span><strong>WARIS</strong><input type="hidden" name="flag_senarai_waris" id="flag_senarai_waris" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="senarai_wariscurrent"></div>
<div style="margin-bottom:5px"  id="senarai_warisprevious"></div>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openCloseSenarai('ob','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKOBPERMOHONAN','ID_OBPERMOHONAN','$ID_PERMOHONANSIMATI','current','','$ID_PEMOHON')">
<tr class="table_header" >
<td width="4%" class="underline_td_sub">
</td>
<td width="56%" class="underline_td_sub">
<span id="icon_ob" >> </span><strong>ORANG BERKEPENTINGAN</strong><input type="hidden" name="flag_senarai_ob" id="flag_senarai_ob" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="senarai_obcurrent"></div>
<div style="margin-bottom:5px"  id="senarai_obprevious"></div>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openCloseSenarai('saksi','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKOBPERMOHONAN','ID_OBPERMOHONAN','$ID_PERMOHONANSIMATI','current','','$ID_PEMOHON')">
<tr class="table_header" >
<td width="4%" class="underline_td_sub">
</td>
<td width="56%" class="underline_td_sub">
<span id="icon_saksi" >> </span><strong>SAKSI</strong><input type="hidden" name="flag_senarai_saksi" id="flag_senarai_saksi" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="senarai_saksicurrent"></div>
<div style="margin-bottom:5px"  id="senarai_saksiprevious"></div>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openCloseSenarai('pemiutang','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKOBPERMOHONAN','ID_OBPERMOHONAN','$ID_PERMOHONANSIMATI','current','','$ID_PEMOHON')">
<tr class="table_header" >
<td width="4%" class="underline_td_sub">
</td>
<td width="56%" class="underline_td_sub">
<span id="icon_pemiutang" >> </span><strong>PEMIUTANG</strong><input type="hidden" name="flag_senarai_pemiutang" id="flag_senarai_pemiutang" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="senarai_pemiutangcurrent"></div>
<div style="margin-bottom:5px"  id="senarai_pemiutangprevious"></div>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openCloseSenarai('penghutang','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKOBPERMOHONAN','ID_OBPERMOHONAN','$ID_PERMOHONANSIMATI','current','','$ID_PEMOHON')">
<tr class="table_header" >
<td width="4%" class="underline_td_sub">
</td>
<td width="56%" class="underline_td_sub">
<span id="icon_penghutang" >> </span><strong>PENGHUTANG</strong><input type="hidden" name="flag_senarai_penghutang" id="flag_senarai_penghutang" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="senarai_penghutangcurrent"></div>
<div style="margin-bottom:5px"  id="senarai_penghutangprevious"></div>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openCloseSenarai('htaah','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKHTAPERMOHONAN','ID_HTAPERMOHONAN','$ID_PERMOHONANSIMATI','current','','$ID_PEMOHON')">
<tr class="table_header" >
<td width="4%" class="underline_td_sub">
</td>
<td width="56%" class="underline_td_sub">
<span id="icon_htaah" >> </span><strong>HARTA TAK ALIH (ADA HAKMILIK)</strong><input type="hidden" name="flag_senarai_htaah" id="flag_senarai_htaah" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="senarai_htaahcurrent"></div>
<div style="margin-bottom:5px"  id="senarai_htaahprevious"></div>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openCloseSenarai('htaahx','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKHTAPERMOHONAN','ID_HTAPERMOHONAN','$ID_PERMOHONANSIMATI','current','','$ID_PEMOHON')">
<tr class="table_header" >
<td width="4%" class="underline_td_sub">
</td>
<td width="56%" class="underline_td_sub">
<span id="icon_htaahx" >> </span><strong>HARTA TAK ALIH (TIADA HAKMILIK)</strong><input type="hidden" name="flag_senarai_htaahx" id="flag_senarai_htaahx" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="senarai_htaahxcurrent"></div>
<div style="margin-bottom:5px"  id="senarai_htaahxprevious"></div>


<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openCloseSenarai('ha','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKHTAPERMOHONAN','ID_HTAPERMOHONAN','$ID_PERMOHONANSIMATI','current','','$ID_PEMOHON')">
<tr class="table_header" >
<td width="4%" class="underline_td_sub">
</td>
<td width="56%" class="underline_td_sub">
<span id="icon_ha" >> </span><strong>HARTA ALIH</strong><input type="hidden" name="flag_senarai_ha" id="flag_senarai_ha" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="senarai_hacurrent"></div>
<div style="margin-bottom:5px"  id="senarai_haprevious"></div>


<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openClose('perubahan','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','','','$ID_PERMOHONANSIMATI')">
<tr class="table_header" >
<td width="2%" class="underline_td_sub">
</td>
<td width="58%" class="underline_td_sub">
<span id="icon_perubahan" >> </span><strong>LAMPIRAN PERUBAHAN MAKLUMAT</strong><input type="hidden" name="flag_perubahan" id="flag_perubahan" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="view_perubahan"></div>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openClose('keteranganhadir','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKPEMOHON','ID_PEMOHON','$ID_PERMOHONANSIMATI')">
<tr class="table_header" >
<td width="2%" class="underline_td_sub">
</td>
<td width="58%" class="underline_td_sub">
<span id="icon_keteranganhadir" >> </span><strong>LAMPIRAN KETERANGAN YANG HADIR</strong><input type="hidden" name="flag_keteranganhadir" id="flag_keteranganhadir" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="view_keteranganhadir"></div>


<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openClose('keputusan','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','TBLPPKPERINTAH','ID_PERINTAH','$ID_PERMOHONANSIMATI')">
<tr class="table_header" >
<td width="2%" class="underline_td_sub">
</td>
<td width="58%" class="underline_td_sub">
<span id="icon_keputusan" >> </span><strong>KEPUTUSAN PERBICARAAN</strong><input type="hidden" name="flag_keputusan" id="flag_keputusan" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="view_keputusan"></div>


<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openClose('historyJana','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','','','$ID_PERMOHONANSIMATI')">
<tr class="table_header" >
<td width="2%" class="underline_td_sub">
</td>
<td width="58%" class="underline_td_sub">
<span id="icon_historyJana" >> </span><strong>HISTORY PENJANAAN NOTA & KETERANGAN PERINTAH</strong><input type="hidden" name="flag_historyJana" id="flag_historyJana" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="view_historyJana"></div>


<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;"  class="box_shadow" onclick="openClose('cetakan','$ID_SIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','','','$ID_PERMOHONANSIMATI')">
<tr class="table_header" >
<td width="2%" class="underline_td_sub">
</td>
<td width="58%" class="underline_td_sub">
<span id="icon_cetakan" >> </span><strong>CETAKAN</strong><input type="hidden" name="flag_cetakan" id="flag_cetakan" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px"  id="view_cetakan"></div>




</fieldset>
</td>
</tr>
<tr>
  <td>&nbsp;</td>
</tr>
</table>

</div>

#if($command == "simpanKeputusanPerintah")
<script>
$jquery("#icon_keputusan").html("< ");
document.getElementById("flag_keputusan").value = "open";

$jquery(document).ready(function () {
	doDivAjaxCall$formname('view_keputusan','showKeputusan','NAMA_TABLE=TBLPPKPERINTAH&FIELD_PK=ID_PERINTAH&ID_SIMATI=$ID_SIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&skrinName=$skrinName&scrolPosition="'+getPageLocation()); 	
});
</script>
#end

<script>
	document.getElementById('listPerbicaraan').style.display = "none";
	document.getElementById('cmdKembaliSenarai').style.display = "";
</script>




