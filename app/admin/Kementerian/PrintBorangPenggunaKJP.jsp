
<table style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%">
<tr class="table_header">
<td height="41" colspan="10" valign="top">
<strong>Borang Semakan Pengguna KJP</strong></td>
</tr>

<table width="100%" border="0">
<tr>
<td valign="top" colspan="14"><strong>Maklumat Kementerian</strong></td>
</tr> 
         
<tr>
							
				<!--<td width="20%" valign="top" >
				 Nama Kementerian	  </td>
<td width="2%" valign="top" >
				:				</td>-->
<td width="78%" valign="top" colspan="3"><strong>$detailsKementerian.NAMA_KEMENTERIAN</strong></td>
  </tr>
			<!--<tr>
						
				<td valign="top" >
				Kod Kementerian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.KOD_KEMENTERIAN
				</td>
			</tr>	-->
            <tr>
							
				<td valign="top" >
				Alamat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.ALAMAT1
				</td>
			</tr>
           
            
                <tr>
							
				<td valign="top" >
				
				</td>
				<td valign="top" >
				
				</td>
				<td valign="top" >
				$detailsKementerian.ALAMAT2
				</td>
			</tr> 
            
                <tr>
							
				<td valign="top" ></td>
				<td valign="top" >
				
				</td>
				<td valign="top" >
				$detailsKementerian.ALAMAT3
				</td>
			</tr> 
            
            
                <tr>
						
				<td valign="top" >
				Poskod
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.POSKOD
				</td>
			</tr> 
			
			<tr>
						
				<td valign="top" >
				 Nama Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.NAMA_NEGERI
				</td>
			</tr>
			
			<tr>
						
				<td valign="top" >
				Emel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.EMEL
				</td>
			</tr>
			
			<tr>
						
				<td valign="top" >
				URL Portal
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				www.nre.gov.my
				</td>
			</tr>
			
			<tr>
							
				<td valign="top" >
				Koordinat GPS
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				??
				</td>
			</tr>
			
			<tr>
						
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				Aktif/Tidak Aktif
				</td>
			</tr>
			
			<tr>
							
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				Tiada catatan
				</td>
			</tr>
			
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr>
							
				<td valign="top" >
				Daftar oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.ID_MASUK
				</td>
			</tr>
			
			<tr>
							
				<td valign="top" >
				Tarikh Daftar
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.TARIKH_MASUK
				</td>
			</tr>
			<tr>
							
				<td valign="top" >
				Kemaskini oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.ID_KEMASKINI
				</td>
			</tr>
            <tr>
						
				<td valign="top" >
				Tarikh Kemaskini
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.TARIKH_KEMASKINI
				</td>
		</tr>
</table>
<br />
<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<font size="3"><strong> Senarai Pengguna di bawah kementerian </strong></font>
</td>
<td width="38%" class="underline_td_main">
</td>
<td width="2%" class="underline_td_main">

</td>
</tr>
</table> 

<table border="0" cellpadding="2" cellspacing="2" align="center" width="98%">
<tr width="100%" >
<td colspan="14">
<table width="100%" align="center">
<tr>
<td>
<table width="100%" border="1" cellpadding="1" cellspacing="1" bordercolor="#000000" >  
<tr class="table_header" >
<td width="4%"   align="center" valign="top">Bil.</td>
<td width="40%"   align="left" valign="top">Nama</td>
<td width="26%"   align="left" valign="top">No KP</td>
<td width="21%"   align="left" valign="top">Jawatan</td>
<td width="9%"   align="left" valign="top">Status</td>
</tr>
#if($listPenggunaKJP.size()>0)
#foreach($list in $listPenggunaKJP)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" >$list.BIL</td>
<td  align="left" valign="top">$list.NAMA_PENUH</td>
<td  align="left" valign="top">$list.NO_KP</td>
<td  align="left" valign="top">$list.NAMA_JAWATAN</td> 
<td  align="left" valign="top">$list.KEAKTIFAN</td> 
</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>

</td>
</tr>
</table>
</td>
</tr>
</table>

<br />

<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<font size="3"><strong> Pengesahan </strong></font>
</td>
<td width="38%" class="underline_td_main">
</td>
<td width="2%" class="underline_td_main">

</td>
</tr>

<tr >
<td width="2%" >
</td>
<td width="58%" colspan="14">Perhatian : Sebelum menandatangani dokumen ini, sila pastikan maklumat yang telah diisi adalah SAH.</td>
</tr>
</table> 
<br /><br /><br /><br />
<table border="0" cellpadding="2" cellspacing="2" align="center" width="98%">
<tr>
<td width="50%">
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

<td width="50%" align="">
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

<script>
$jquery(document).ready(function () {
		printHideDiv('BorangSemakanPenggunaKJP');
		
		var BorangSemakanPenggunaKJP =  document.getElementById('BorangSemakanPenggunaKJP');
		BorangSemakanPenggunaKJP.style.display = "none";
		
	});
</script>