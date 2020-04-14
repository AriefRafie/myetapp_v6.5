
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px;cursor:pointer;" class="box_shadow" onclick="openCloseBantahan('bantahan','$ID_PERMOHONANSIMATI','$ID_PERBICARAAN','$ID_PERMOHONAN','$ID_PEMOHON')">
<tr class="table_header" >
<td width="2%" class="underline_td_sub">
</td>
<td width="58%" class="underline_td_sub">
<span id="icon_bantahan" >> </span><strong>MAKLUMAT FAIL</strong><input type="hidden" name="flag_bantahan" id="flag_bantahan" value="close" /></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>
<div style="margin-bottom:5px" id="view_bantahan"></div>

<!-- tidak wajib untuk call, untuk semakan prestasi saja -->
#parse("app/RazTemplate/loadingInfo.jsp")