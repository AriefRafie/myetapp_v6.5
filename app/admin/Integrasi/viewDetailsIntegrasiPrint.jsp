<script>
if( $jquery('#'+'div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI').offset().top);
}
else
{
	
	if( $jquery('#'+'div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI').offset().top);
	}
	
}
</script>
<div id="DetailsPrint">
<tr id="div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI">
<td align="left" valign="top" colspan="14">
<fieldset>
<legend>Maklumat Agensi</legend>
<table width="100%" border="0">
<tr>
<td valign="top"  width="1%"></td>
<td valign="top"  width="28%"></td>
<td valign="top"  width="1%"></td>
<td valign="top"  width="70%">
<!-- $viewPengguna --></td>
</tr>
             #if ($Type.equals("1") || $Type.equals("2"))	 
             
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				 Nama Agensi
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.NAMA
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kod Agensi
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.KOD_AGENSI
				</td>
			</tr>	
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.ALAMAT1
				</td>
			</tr>
           
            
                <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				
				</td>
				<td valign="top" >
				
				</td>
				<td valign="top" >
				$detailIntegrasi.ALAMAT2
				</td>
			</tr> 
            
                <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				
				</td>
				<td valign="top" >
				
				</td>
				<td valign="top" >
				$detailIntegrasi.ALAMAT3
				</td>
			</tr> 
            
            
                <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Poskod
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.POSKOD
				</td>
			</tr> 
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				 Nama Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.NAMA_NEGERI
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Emel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.EMEL
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
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
				</td>			
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
				</td>			
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($detailIntegrasi.FLAG_AKTIF=="1" || $detailIntegrasi.FLAG_AKTIF=="") Aktif
                #else Tidak Aktif
                #end
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.CATATAN
				</td>
			</tr>
            #end
            
            #if ($Type.equals("3"))	 
             
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				 Nama Sistem
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.NAMA
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Lain Sistem
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.KOD_AGENSI
				</td>
			</tr>	
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jenis Capaian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.ALAMAT1
				</td>
			</tr>
     
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Emel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.EMEL
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
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
				</td>			
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
				</td>			
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($detailIntegrasi.FLAG_AKTIF=="1" || $detailIntegrasi.FLAG_AKTIF=="") Aktif
                #else Tidak Aktif
                #end
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.CATATAN
				</td>
			</tr>
            #end
		
        #if ($Type.equals("2"))	
            <tr><td><br /></td></tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" ><strong>Maklumat Pemilik Sistem</strong>
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
		</tr>
        
         <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Pemilik
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.NAMA_PEMILIK
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Lain Pemilik
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.NAMA_LAIN_PEMILIK
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kategori Pemilik
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.KATEG_PEMILIK
				</td>
		</tr>
        
    <!--      <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" ><br />
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
		</tr>
        
       <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" ><strong>Info Bantuan</strong>
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
		</tr>
        
         <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Pegawai Perhubungan 1 ( Contact Person )
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Bahagian/ Seksyen/ Unit
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jawatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Emel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No.Tel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" ><br />
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Pegawai Perhubungan 2 ( Contact Person )
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Bahagian/ Seksyen/ Unit
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jawatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Emel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No.Tel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        -->
        #end
         <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" ><br />
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daftar oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.USER_NAME
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Daftar
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_MASUK
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kemaskini oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.USER_NAME
				</td>
			</tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Kemaskini
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" ><br />
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
		</tr>
     
</table>

<div id="DetailsForPrint_$Type$detailIntegrasi.ID_INTEGRASI" style="display:none"></div>
</fieldset>

</td>
</tr>
</div>

<script>
	printHideDiv2('DetailsPrint','');
</script>