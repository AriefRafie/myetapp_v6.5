
<table cellpadding="2" cellspacing="1" border="0" width="100%" class="classFade dashboard_tepi " align="left">
<tr>
<td width="100%" valign="top"  >

<table width="100%"  id="table_stat">
<tr>
<td width="10%" align="center" valign="top">



</td>
<td width="90%" >
<table width="100%" >

<tr>
<td   colspan="3">


<b>Statistik Fail di <font color="blue">$!negeri_sever</font></b></td>
</tr>

<tr>
<td width="50%" valign="top">
<font color="blue"><li>Keseluruhan Fail</li></font></td>
<td width="1%" valign="top">
:</td>
<td width="49%" valign="top">
<b>$!jumlah_keseluruhan</b></td>
</tr>

<tr>
<td valign="top">
<font color="blue"><li>Pengambilan Seksyen 4</li></font></td>
<td  valign="top">
:</td>
<td  valign="top">
<b>$!sek4</b></td>
</tr>

<tr>
<td valign="top">
<font color="blue"><li>Pengambilan Seksyen 8</li></font></td>
<td valign="top">
:</td>
<td  valign="top">
<b>$!sek8</b></td>
</tr>

<tr>
<td valign="top">
<font color="blue"><li>Pengambilan Sementara</li></font></td>
<td valign="top">
:</td>
<td  valign="top">
<b>$!sekSementara</b></td>
</tr>

<!--
<tr>
<td valign="top">
<font color="blue"><li>Fail Berstatus Hapus</li></font></td>
<td  valign="top">
:</td>
<td  valign="top">
<b>$!fail_hapus</b></td>
</tr>
-->

</table>
</td>
</tr>
</table>

</td>
</tr>
</table>
<script>
$jquery(document).ready(function () {
	//doDivAjaxCall$formname('dashboard_tab','getTabDashboard','');	
	//doDivAjaxCall$formname('div_getAgihan','getAgihan','');	
	//doDivAjaxCall$formname('div_carianMain','getCarianMain','');
	doDivAjaxCall3$formname('div_getMainOnline','getMainOnline','');			
});
</script>