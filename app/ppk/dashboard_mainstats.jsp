<table width="100%"  id="table_stat" class="classFade" >
<tr>
<td width="15%" align="center" valign="top">



</td>
<td width="85%" >
<table width="100%">

<tr>
<td   colspan="3">


<b>Statistik Fail di Pengkalan Data <br /><font color="blue">$!negeri_sever</font></b></td>
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
<font color="blue"><li>Fail Seksyen 8</li></font></td>
<td  valign="top">
:</td>
<td  valign="top">
<b>$!fail_sek8</b></td>
</tr>

<tr>
<td valign="top">
<font color="blue"><li>Fail Seksyen 17</li></font></td>
<td valign="top">
:</td>
<td  valign="top">
<b>$!fail_sek17</b></td>
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

<script>
$jquery(document).ready(function () {
	doDivAjaxCall3$formname('dashboard_tab','getTabDashboard','');	
		
});
</script>