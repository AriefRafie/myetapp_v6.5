<input type="hidden" name="flagHantar" value="$!maklumatPermohonan.flagHantar">
<table border="0" width="100%"  class="nav">
  <tr >
    <td valign="top" ><strong>Maklumat Permohonan</strong> </td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="2" cellpadding="2" class="dashboard_sub">
  <tr>
    <td width="1%"></td>
    <td width="20%">No. Fail JKPTG</td>
    <td width="1%">:</td>
    <td width="78%">$!maklumatPermohonan.noFail</td>
  </tr>
  <tr>
    <td  valign="top"></td>
    <td  valign="top">Kementerian</td>
    <td   valign="top">:</td>
    <td  valign="top">$!maklumatPermohonan.namaKementerian</td>
  </tr>
  <tr>
    <td  valign="top"></td>
    <td  valign="top">Projek</td>
    <td   valign="top">:</td>
    <td  valign="top">$!maklumatPermohonan.namaProjek</td>
  </tr>
  #if ($!jenisSkrin == "BorangC" || $!jenisSkrin == "BorangK" || $!jenisSkrin == "PU" || $!jenisSkrin == "SijilUkur" || $!jenisSkrin == "TarikBalik")   
  #if ($!maklumatPermohonan.noWarta != "")
  <tr>
    <td  valign="top"></td>
    <td  valign="top">No. Warta</td>
    <td   valign="top">:</td>
    <td  valign="top">$!maklumatPermohonan.noWarta</td>
  </tr>
  <tr>
    <td  valign="top"></td>
    <td  valign="top">Tarikh Warta</td>
    <td   valign="top">:</td>
    <td  valign="top">$!maklumatPermohonan.tarikhWarta</td>
  </tr>
  #end 
  #elseif ($!jenisSkrin == "WartaS4" || $!jenisSkrin == "WartaS8")
  <tr>
    <td  valign="top"></td>
    <td  valign="top"><font color="blue"><b>No. Warta</b></font></td>
    <td   valign="top">:</td>
    <td  valign="top"> #if ($!maklumatPermohonan.noWarta != "") <font color="blue"><b>$!maklumatPermohonan.noWarta</b></font> #else <font color="red">Sila Pastikan Maklumat Ini Diisi!</font>#end </td>
  </tr>
  <tr>
    <td  valign="top"></td>
    <td  valign="top"><font color="blue"><b>Tarikh Warta</b></font></td>
    <td   valign="top">:</td>
    <td  valign="top"> #if ($!maklumatPermohonan.tarikhWarta != "") <font color="blue"><b>$!maklumatPermohonan.tarikhWarta</b></font> #else <font color="red">Sila Pastikan Maklumat Ini Diisi!</font> #end </td>
  </tr>
  #end
  <tr>
    <td  valign="top"></td>
    <td  valign="top"><b>Status Integrasi</b></td>
    <td   valign="top">:</td>
    <td  valign="top"> #if ($!maklumatPermohonan.flagHantar == "Y") <font color="blue"><b>TELAH DIHANTAR</b></font> #else <font color="red"><b>BELUM DIHANTAR</b></font> #end </td>
  </tr>
  <tr>
    <td  valign="top"></td>
    <td  valign="top"><b>Tarikh Hantar</b></td>
    <td   valign="top">:</td>
    <td  valign="top"><font color="blue"><b>$!maklumatPermohonan.tarikhHantar</b></font> </td>
  </tr>  
</table>
