<script>
if( $jquery('#'+'div_detailsKementerian$detailsKementerian.ID_KEMENTERIAN').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_detailsKementerian$detailsKementerian.ID_KEMENTERIAN').offset().top);
}
else
{
	
	if( $jquery('#'+'div_detailsKementerian$detailsKementerian.ID_KEMENTERIAN').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_detailsKementerian$detailsKementerian.ID_KEMENTERIAN').offset().top);
	}
	
}
</script>

<tr id="div_detailsKementerian$detailsKementerian.ID_KEMENTERIAN">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Kementerian</legend>
<table width="100%" border="0">
<tr>
<td valign="top"  width="1%"></td>
<td valign="top"  width="28%"></td>
<td valign="top"  width="1%"></td>
<td valign="top"  width="70%">
<!-- $viewPengguna --></td>
</tr>
<!--  <input type=text name="ID_KEMENTERIAN" value="$detailsKementerian.ID_KEMENTERIAN">-->
             
       
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				 Nama Kementerian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.NAMA_KEMENTERIAN
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kod Kementerian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.KOD_KEMENTERIAN
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
				$detailsKementerian.ALAMAT1
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
				$detailsKementerian.ALAMAT2
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
				$detailsKementerian.ALAMAT3
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
				$detailsKementerian.POSKOD
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
				$detailsKementerian.NAMA_NEGERI
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
				$detailsKementerian.EMEL
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
				-
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
				$detailsKementerian.FLAG_AKTIF
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
				-
				</td>
			</tr>
			
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
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
				$detailsKementerian.ID_MASUK
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
				$detailsKementerian.TARIKH_MASUK
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
				$detailsKementerian.USER_NAME
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
				$detailsKementerian.TARIKH_KEMASKINI
				</td>
		</tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td >
                
 <input type="button" id="cmdEditGred" name="cmdEditGred" value="Kemaskini" onClick="doDivAjaxCall$formname('div_viewKementerian$detailsKementerian.ID_KEMENTERIAN','addKementerian','ID_KEMENTERIAN=$detailsKementerian.ID_KEMENTERIAN');" >
			
<input type="button" id="cmdTutupGred" name="cmdTutupGred" value="Tutup" onClick="doDivAjaxCall$formname('div_viewKementerian$detailsKementerian.ID_KEMENTERIAN','closeDiv','');" >   	

<input type="button" id="cmdCetakPejabat" name="cmdCetakPejabat" value="Cetak"
onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('SenaraiForPrintAgensi','cetakDetailsKementerian','FlagCetak=Y&ID_KEMENTERIAN=$detailsKementerian.ID_KEMENTERIAN');"  />

<input  type="button" id="borangSemak" name="borangSemak" value="Borang Semakan" 
onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('BorangSemakanKJP','cetakBorangSemakanKJP','ID_KEMENTERIAN=$detailsKementerian.ID_KEMENTERIAN');"  />

<input  type="button" id="borangSemakUser" name="borangSemakUser" value="Borang Semakan Pengguna " 
onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('BorangSemakanPenggunaKJP','cetakBorangSemakanPenggunaKJP','ID_KEMENTERIAN=$detailsKementerian.ID_KEMENTERIAN');"  />

<br>
</td>		
</tr>	
</table>

<fieldset>
<legend>Maklumat Agensi <!--<input type="button" id="cmdTambahAgensi" name="cmdTambahAgensi"   
onClick="doDivAjaxCall$formname('div_AddAgensi$detailsKementerian.ID_KEMENTERIAN','addAgensi','ID_KEMENTERIAN=$detailsKementerian.ID_KEMENTERIAN&ID_Agensi=');" value="Tambah Agensi" >--></legend>

<div id="viewCarianAgensi$detailsKementerian.ID_KEMENTERIAN">
<script>   
$jquery(document).ready(function () {
  doDivAjaxCall$formname('viewCarianAgensi$detailsKementerian.ID_KEMENTERIAN','carianAgensi','ID_KEMENTERIAN=$detailsKementerian.ID_KEMENTERIAN');			 	  
 });
</script>
</div> 



<br />



<div id="SenaraiForPrintAgensi"></div>
 <div id="BorangSemakanKJP" style="display:none">	
	</div>
    <div id="BorangSemakanPenggunaKJP" style="display:none">	
	</div>
</fieldset>


		
      