



<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr  >
<td  width="100%"  valign="top">
	
<fieldset>

<legend>Carian Terperinci &nbsp;&nbsp;</legend>

<table width="100%">
<tr>
<td width="1%" valign="top" >
</td>
<td width="28%" valign="top" >Kementerian</td>
<td width="1%" valign="top" >:</td>
<td width="70%" valign="top" >
<input type="text" id="carianUmum" name="carianUmum" style="text-transform:uppercase;" size="50" value="$!carianUmum" 
onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','showSenaraiKementerian',''); return false; }">	
<input type="button" id="cmdCariKementerian" name="cmdCariKementerian" value="Cari" onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('div_senaraiUtama','showSenaraiKementerian','');" >
<input type="hidden" id="cetakReport" name="cetakReport" value="$cetakReport" >

<input type="button" id="cmdBatalCariKementerian" name="cmdBatalCariKementerian" value="Reset" onClick="$jquery('#carianUmum').val('');" >


</td>
</tr>

<tr>
 <td></td>
 <td></td>
	<td></td>
    <td>Statistik Kementerian dan Agensi  <!--<img src="../img/images_stat.png" alt="" width="20" height="20" onclick="statsRole('Role')" />-->
    <img width="20" height="20" src="../img/images_stat.png" onclick="doCetakStats('statKJP')">
    </td>
</tr>

<tr>
<td></td>
<td></td>
<td></td>
<td>
<div id="statKJP" style="display:none">
<table>
<tr>
<td>
<a href="javascript:doCetakStatsSub('statKJPAll','1')" class="help" title="">
<font color="blue"><li>Senarai 5 Kementerian yang Paling Aktif </li></font>
</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:doCetakStatsSub('statKJPAll','2')"  class="help" title="">
<font color="blue"><li>Senarai 5 Agensi yang Paling Aktif </li></font>
</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:doCetakStatsSub('statKJPAll','3')" class="help" title="">
<font color="blue"><li>Jumlah Keseluruhan Pengguna Modul KJP mengikut Peranan</li></font>
</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:doCetakStatsSub('statKJPAll','4')" class="help" title="">
<font color="blue"><li>Statistik Aduan Agensi Mengikut Kategori Aduan</li></font>
</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:doCetakStatsSub('statKJPAll','5')" class="help" title="">
<font color="blue"><li>Senarai Kementerian dan Jabatan/ Agensi dan Tahap Penggunaan ( Jumlah Log Masuk )</li></font>

</a>
</td>
</tr>

<!--<tr>
<td>
<a href="javascript:doCetakStatsSub('statKJPAll','6')" class="help" title="Semakan Status Permohonan Pusaka">
<font color="blue"><li>Statistik Penggunaan MyeTaPP ( Modul KJP ) - Hari dan Waktu Bekerja</li></font>

</a>
</td>
</tr>-->

<!--<tr>
<td>
<a href="javascript:doCetakStatsSub('statKJPAll','7')" class="help" title="">
<font color="blue"><li>Statistik Penggunaan MyeTaPP ( Modul KJP ) -  Jenis OS, Pelayar Web dan IP ????????? </li></font>

</a>
</td>
</tr>-->

</table>
</div>
</td>
</tr>  

<tr>
<td colspan="14">
<div id="statKJPAll"></div>
</td>
</tr>

</table>

</fieldset>

</td>
</tr>
</table>
<script>   
$jquery(document).ready(function () 
{
doDivAjaxCall$formname('div_senaraiUtama','showSenaraiKementerian');	  
});  

</script>