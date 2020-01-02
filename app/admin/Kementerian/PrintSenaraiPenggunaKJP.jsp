<style>
.style1 {
	font-size: 16px;
	font-weight: bold;
}
</style>
<div id="div_LaporanPenggunaforPrint" class="Section1">
 <table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
  <tr>
 
  <td width="5%"  colspan bgcolor="#666666" "5"> </td>
    
    <td width="95%"> 
    <p align="right"><h3> $detailsKementerian.NAMA_KEMENTERIAN  </h3>
    <br> $detailsKementerian.ALAMAT1 $detailsKementerian.ALAMAT2 
    #if ($detailsKementerian.ALAMAT3!="") <br> $detailsKementerian.ALAMAT3 #end
    <br> $detailsKementerian.POSKOD $detailsKementerian.NAMA_NEGERI	
    <br> No.Tel :
    <br> No. Faks : 
    <br>
    </p>
    </td> 
    
  </tr>
 
</table>

 <p class="style1">Panduan Melengkapkan Borang Semakan Pengguna </p>

<table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
  <tr>
 
  <td width="5%" bgcolor="#666666"> </td>
  <td width="95%">
  
  <table width="87%"  >
 
  <tr> 
    <td colspan="10" align="left">Dokumen ini mengandungi Senarai Pengguna Aktif bagi $detailsKementerian.NAMA_KEMENTERIAN <br>
    Sila isikan maklumat berkaitan Pengguna di ruangan Catatan yang disediakan seperti contoh di bawah : <br>

    </td>
  </tr>
  <tr> 
  <table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
  <tr>
    <td colspan bgcolor="#666666" width="6%">Bil</td>
    <td colspan bgcolor="#666666" width="43%">Nama Pengguna</td>
    <td bgcolor="#666666" width="43%">Catatan</td>
  </tr>
  
  	<tr>
 	<td align="center" valign="top" >1.</td>
	<td  align="left" valign="top">NAMA
    <br>(NO_KP)
    <br>JAWATAN)
    </td>
    <td   align="center" valign="top" >KEAKTIFAN <br> CATATAN</td>
  </tr>
  </table>
  </tr>
  <tr> 
    <td colspan="10" align="left">Akses kepada Sistem MyeTaPP bagi pengguna berstatus Tidak Aktif akan disekat berdasarkan kesesuaian. <br>
</td>
  </tr>
  </table>

  
  </td> 
    
  </tr>
 
</table>

<p class="style1"> Senarai Pengguna : Sila rujuk Lampiran A </p>

<p> Sebelum menandatangani dokumen ini, sila pastikan maklumat yang telah diisi adalah SAH. </p>

<table id="main_table" style="width:80%" border="0">
	
		<tr>
		  <td width="50%" valign="top">
            <table width="95%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
				
           <tr>
                 <td width="5%" colspan bgcolor="#666666" "8"></td>
                 
            <td>
            Disahkan Oleh :
            <br />
            <br />
            <br />
            <br />
            .............................
            <br />
            Nama : 
            <br />
            Jawatan : 
            <br />
            Tarikh : 
            <br />
            Cop Rasmi : 
            <br />
            <br />
            <br />
            <br />
                    
                    </td>
                </tr>
              </table>
		  </td>
            
			<td width="50%" valign="top">
            	 <table width="95%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" align="right">
                <tr>
                 <td width="5%" colspan bgcolor="#666666" "8"> </td>
            <td>
            Dimasukkan Oleh : (Admin Sistem)
            <br />
            <br />
            <br />
            <br />
            .............................
            <br />
            Nama : 
            <br />
            Jawatan : 
            <br />
            Tarikh : 
            <br />
            Cop Rasmi : 
            <br />
            <br />
            <br />
            <br />
                    </td>
                </tr>
                </table>
		  </td>
		</tr>
</table>
</div>

<div id="div_LampiranAPrint" class="Section1">
#parse("app/admin/Kementerian/PrintSenaraiPenggunaKJPLengkap.jsp")
</div>


</div>
<script type="text/javascript">
	printHideDiv('div_LaporanPenggunaforPrint','div_LampiranAPrint');
	//printHideDiv('div_LaporanPenggunaforPrint','$jenisPejabatReport','');
</script>