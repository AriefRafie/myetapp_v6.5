<div id="divSenaraiPenggunaLengkap"  >
#set ($jenisPejabatReport = $viewPejabat.NAMA_PEJABAT)
<body>
<h2 class="page-break">Lampiran A</h2>
<p>Statistik Ringkas :</p>


<table width="80%" id="t01" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
  <tr>
 
  <td width="5%"  bgcolor="#666666"> </td>
    
    <td width="95%"><p align="left">&nbsp;</p>
      <p align="left">Jumlah Pengguna Aktif : $totalRecords</p>
      <p align="left">Jumlah Pengguna - Peranan : Pelulus : ? </p>
<p align="left">Jumlah Pengguna - Peranan : Penyemak : ? </p>
<p align="left">Jumlah Pengguna - Peranan : Penyedia : ? </p>
<p align="left">Jumlah Keseluruhan Jabatan/Agensi (termasuk kementerian) : ? </p>
<p align="left">Jumlah Keseluruhan Jabatan/Agensi (termasuk kementerian) yang menggunakan sistem MyeTaPP : ? </p>
<p align="left">&nbsp;</p>
<p align="left">Maklumat Pengguna bagi Kementerian ini dikemaskini kali terakhir pada ?? melalui emel. </p>

   <br>
    </td> 
    
  </tr>
 
</table>



<p><br>
Senarai Pengguna bagi Kementerian ini mengikut Jabatan/Agensi (termasuk Kementerian) yang menggunakan sistem MyeTaPP :</p>

<table width="80%" id="senaraiUser" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >

 <td width="5%"   bgcolor="#666666" > </td>
  
  <td width="95%"> 
  <table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
  <tr> 
  #set($rowStrech = $listPenggunaKJP.length)
   
    <td colspan bgcolor="#666666" width="6%">Bil</td>
    <td colspan bgcolor="#666666" width="43%">Nama Pengguna</td>
    <td bgcolor="#666666" width="43%">Catatan</td>
  </tr>
  
    #if($listPenggunaKJP.size()>0)
	#foreach($PejUrus in $listPenggunaKJP)
  	<tr>
  
 	<td align="center" valign="top" >$PejUrus.BIL.</td>
	<td  align="left" valign="top">$PejUrus.NAMA_PENUH 
    #if ($PejUrus.NO_KP!="")<br>(No KP : $PejUrus.NO_KP) #end 
    <br> Peranan : $PejUrus.NAMA_JAWATAN
    </td>
    <td   align="left" valign="top" >Status : $PejUrus.KEAKTIFAN </td>
  </tr>
  #end
  #end
  </table>
  </td>
</table>

<br>
<h3>Catatan* : </h3>
*sekiranya terdapat sebarang perubahan pada Maklumat Pejabat atau sebarang perkara lain.

<br>


<table width="80%" height="180" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
  <tr>
 
  <td width="5%"  bgcolor="#666666">
   </td>
    
    <td width="95%"> 
  
    </td> 
    
  </tr>
 
</table>

<!--
<p>&nbsp;</p>
<p>&nbsp;</p>
</body>
-->
</div>

