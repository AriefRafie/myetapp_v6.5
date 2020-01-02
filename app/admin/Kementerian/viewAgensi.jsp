<script>
if( $jquery('#'+'div_detailsAgensi$detailsAgensi.ID_AGENSI').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_detailsAgensi$detailsAgensi.ID_AGENSI').offset().top);
}
else
{
	
	if( $jquery('#'+'div_detailsAgensi$detailsAgensi.ID_AGENSI').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_detailsAgensi$detailsAgensi.ID_AGENSI').offset().top);
	}
	
}
</script>

<tr id="div_detailsAgensi$detailsAgensi.ID_AGENSI">
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
<!--  <input type=text name="ID_KEMENTERIAN" value="$detailsAgensi.ID_KEMENTERIAN">-->
             
       
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
				$detailsAgensi.NAMA_AGENSI
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
				$detailsAgensi.KOD_AGENSI
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
				$detailsAgensi.ALAMAT1
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
				$detailsAgensi.ALAMAT2
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
				$detailsAgensi.ALAMAT3
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
				$detailsAgensi.POSKOD
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
				$detailsAgensi.NAMA_NEGERI
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
				$detailsAgensi.EMEL
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
				$detailsAgensi.FLAG_AKTIF
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
            <!--<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daftar oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsAgensi.ID_MASUK
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
				$detailsAgensi.TARIKH_MASUK
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
				$detailsAgensi.ID_KEMASKINI
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
				$detailsAgensi.TARIKH_KEMASKINI
				</td>
		</tr>-->
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td >
                
 <input type="button" id="cmdEditAgensi" name="cmdEditAgensi" value="Kemaskini" onClick="doDivAjaxCall$formname('div_viewAgensi$detailsAgensi.ID_AGENSI','addAgensi','ID_AGENSI=$detailsAgensi.ID_AGENSI');" >
			
<input type="button" id="cmdTutupAgensi" name="cmdTutupAgensi" value="Tutup" onClick="doDivAjaxCall$formname('div_viewAgensi$detailsAgensi.ID_AGENSI','closeDiv','');" >   	

<br>
</td>		
</tr>	
</table>
</fieldset>
</td>
</tr>