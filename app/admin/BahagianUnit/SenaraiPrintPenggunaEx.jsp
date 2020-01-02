<style>
.style1 {
	font-size: 20px;
	font-weight: bold;
}
</style>
<div id="div_LaporanPenggunaforPrint" >

 <p class="style1">Maklumat Bahagian </p>
<table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
  <tr>
 
  <td width="5%"  bgcolor="#666666">
   </td>
    
    <td width="95%"> 
    <p align="right"><h3>$viewBahagianHQ.NAMA_SEKSYEN </h3>
         <br>
    </p>
    </td> 
    
  </tr>
 
</table>

 <p class="style1">Panduan Melengkapkan Borang Semakan Pengguna </p>

<table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
  <tr>
 
  <td width="5%"  bgcolor="#666666">
   </td>
    
    <td width="95%"> 
  
  <table width="87%" style="width:80%" >
 
  <tr> 
    <td colspan="10" align="left">Dokumen ini mengandungi Senarai Pengguna Aktif bagi $viewBahagianHQ.NAMA_SEKSYEN  <br>
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
	<td  align="left" valign="top">AHMAD BIN HJ ESNAWI
    <br>(540623125679)
    <br>PENOLONG PEGAWAI TANAH
    </td>
    <td   align="center" valign="top" >AKTIF</td>
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
	<tbody>
		<tr>
		  <td width="50%" valign="top">
            <table width="95%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
				
           <tr>
                 <td width="5%"  bgcolor="#666666" "8"></td>
                 
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
                 <td width="5%" bgcolor="#666666" "8"> </td>
            <td>
            Dimasukkan Oleh : <br />
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
	</tbody>
</table>
</div>

<div id="div_LampiranAPrint" class="Section1">
#parse("app/admin/BahagianUnit/SenaraiPrintPengguna.jsp")
</div>

<script type="text/javascript">
	printHideDivAll('div_LaporanPenggunaforPrint','div_LampiranAPrint');
	</script>