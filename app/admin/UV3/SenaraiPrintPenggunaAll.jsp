
<div id="divSenaraiPenggunaLengkapHQ" >
<body>
<h2>Lampiran A</h2>
<p>Statistik Ringkas :</p>

<table width="80%" id="t01" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
  <tr>
 
  <th width="5%" colspan bgcolor="#666666" "5"> </th>
    
    <th width="95%"> 
    <p align="left">Jawatan:</p>
<p align="left">Jumlah Pengguna Aktif : $totalRecords</p>
<table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
  
  <tr>
  #set($rowStrech = $listStatsJawatan.length)
    <td colspan bgcolor="#666666" "5" width="5%" rowspan="$rowStrech">&nbsp;</td>
    <td colspan bgcolor="#666666" width="52%">Jawatan</td>
    <td colspan bgcolor="#666666" width="39%">Jumlah Pengguna</td>
  </tr>
  #if ($listStatsJawatan.size()>0)
  #foreach ($jawatan in $listStatsJawatan)
  <tr>
   <td style="border:none" bgcolor="#666666" ></td>
    <td>$jawatan.JAWATAN</td>
    <td align="center">$jawatan.JUMLAH_PENGGUNA</td>
  </tr>
  #end
  #end

</table>
   <br>
    </th> 
    
  </tr>
 
</table>



<br>
Senarai Pengguna :
<br>

<table width="80%" id="senaraiUser" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >

 <th width="5%" height="311" colspan bgcolor="#666666" "5"> </th>
  
  <th width="95%"> 
  <table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" align="left">
  <tr> 
  #set($rowStrech = $PrintlistPengguna.length)
   
    <td colspan bgcolor="#666666" width="6%">Bil</td>
    <td colspan bgcolor="#666666" width="43%">Nama Pengguna</td>
    <td bgcolor="#666666" width="43%">Catatan</td>
  </tr>
  
    #if($PrintlistPengguna.size()>0)
	#foreach($PejUrus in $PrintlistPengguna)
  	<tr>
  
 	<td align="center" valign="top" >$PejUrus.BIL.</td>
	<td  align="left" valign="top">$PejUrus.FULLNAME 
    <br> $PejUrus.JAWATAN
    </td>
    <td   align="center" valign="top" >$PejUrus.KEAKTIFAN </td>
  </tr>
  #end
  #end
  </table>
  </th>
</table>

<br>
<h3>Catatan* : </h3>
*sekiranya terdapat sebarang perubahan pada Maklumat Pejabat atau sebarang perkara lain.
<br>


<table width="80%" height="180" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
  <tr>
 
  <th width="5%" colspan bgcolor="#666666" "5">
   </th>
    
    <th width="95%"> 
  
    </th> 
    
  </tr>
 
</table>


<p>&nbsp;</p>
<p>&nbsp;</p>
</body>
</div>

<script type="text/javascript">

	printHideDiv('divSenaraiPenggunaLengkapHQ','','');
	</script>