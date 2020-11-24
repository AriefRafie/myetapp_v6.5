<table border="0" width="100%"  class="nav">
  <tr >
    <td valign="top" ><strong>Maklumat Endorsan</strong> </td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
  <tr>
    <td  width="1%" valign="top"></td>
    <td  width="20%" valign="top"><b>Status Maklumbalas</b></td>
    <td  width="1%" valign="top">:</td>
    <td  width="78%" valign="top"> #if ($!maklumatPermohonan.flagEndorsan == "Y") <font color="blue"><b>TELAH DITERIMA</b></font> #else <font color="red"><b>BELUM DITERIMA</b></font> #end </td>
  </tr>
  <tr>
    <td  valign="top"></td>
    <td  valign="top"><b>Tarikh Terima</b></td>
    <td   valign="top">:</td>
    <td  valign="top"><font color="blue"><b>$!maklumatPermohonan.tarikhTerima</b></font> </td>
  </tr>
  <tr>
    <td  valign="top"></td>
    <td  valign="top"><b>No. Jilid</b></td>
    <td   valign="top">:</td>
    <td  valign="top"><b>$!maklumatPermohonan.noJilid</b></td>
  </tr>
</table>
