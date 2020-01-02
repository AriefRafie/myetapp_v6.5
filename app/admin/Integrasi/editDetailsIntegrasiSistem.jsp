<script>
if( $jquery('#'+'div_rowAgensi$detailIntegrasi.ID_INTEGRASI').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowAgensi$detailIntegrasi.ID_INTEGRASI').offset().top);
}
else
{
	
	if( $jquery('#'+'div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI').offset().top);
	}
	
}
</script>

<!--#if ($result == "success")
<div class=info>Done.</div>
#end-->
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

<tr>
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Integrasi Antara Sistem</legend>
<table width="100%" border="0">
				<!--<input  size="50" type="text" id="ID_INTEGRASI_$detailIntegrasi.ID_INTEGRASI" 
		name="ID_INTEGRASI_$detailIntegrasi.ID_INTEGRASI" 
		value="$!detailIntegrasi.ID_INTEGRASI" >-->
<tr>
    <td valign="top" align="right">&nbsp;</td>			
    <td valign="top" >
    Jenis Integrasi
    </td>
    <td valign="top" >
    :
    </td>
    <td  >

<select id="JENIS_INTEGRASI_$detailIntegrasi.ID_INTEGRASI"  name="JENIS_INTEGRASI_$detailIntegrasi.ID_INTEGRASI" disabled="disabled">	   
<option value = "" >SILA PILIH</option>
<option value="3" selected >ANTARA SISTEM</option>
</select>

</td>
</tr>

<tr>
    <td valign="top" >&nbsp;</td>			
    <td valign="top" >
    Nama Sistem
    </td>
    <td valign="top" >
    :
    </td>
    <td  >
    <input  size="50" type="text" id="NAMA_$detailIntegrasi.ID_INTEGRASI" 
name="NAMA_$detailIntegrasi.ID_INTEGRASI" 
value="$detailIntegrasi.NAMA" >
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
    <td  >
    <input  size="50" type="text" id="NAMA_LAIN_$detailIntegrasi.ID_INTEGRASI" 
name="NAMA_LAIN_$detailIntegrasi.ID_INTEGRASI" 
value="$detailIntegrasi.NAMA" >
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
				-
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
				<textarea cols="50" rows="10" name="CATATAN_$detailIntegrasi.ID_INTEGRASI">$detailIntegrasi.CATATAN</textarea>
				</td>
			</tr>
            
 <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Mula Dibangunkan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
              
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Mula Digunapakai
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
               
				</td>
			</tr>
          


<tr>
    <td valign="top" >&nbsp;</td>			
    <td valign="top" >
    Status
    </td>
    <td valign="top" >
    :
    </td>
    <td  >
<select id="FLAG_AKTIF_$detailIntegrasi.ID_INTEGRASI"  name="FLAG_AKTIF_$detailIntegrasi.ID_INTEGRASI" >	   
<option value = "" >SILA PILIH</option>
#set ( $selected_aktif = "" )
#set ( $selected_tidakaktif = "" )
#if($detailIntegrasi.FLAG_AKTIF=="1" || $detailIntegrasi.FLAG_AKTIF=="")
#set ( $selected_aktif = "selected" )
#else
#set ( $selected_tidakaktif = "selected" )
#end
<option value="1" $selected_aktif >AKTIF</option>
<option value="2" $selected_tidakaktif >TIDAK AKTIF</option>
</select>
</td>
</tr>


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
				<input  size="50" type="text" id="NAMA_PEMILIK_$detailIntegrasi.ID_INTEGRASI" name="NAMA_PEMILIK_$detailIntegrasi.ID_INTEGRASI" value="$detailIntegrasi.NAMA_PEMILIK" >
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
				<input  size="50" type="text" id="NAMA_LAIN_PEMILIK_$detailIntegrasi.ID_INTEGRASI" name="NAMA_LAIN_PEMILIK_$detailIntegrasi.ID_INTEGRASI" value="$detailIntegrasi.NAMA_LAIN_PEMILIK" >
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
				<select id="KATEG_PEMILIK_$detailIntegrasi.ID_INTEGRASI" name="KATEG_PEMILIK_$detailIntegrasi.ID_INTEGRASI">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_govCT_KATEG_PEMILIK_ = "" )
				#set ( $selected_swastaCT_KATEG_PEMILIK_ = "" )
							#if($detailIntegrasi.KATEG_PEMILIK =="1" )
								#set ( $selected_govCT_KATEG_PEMILIK_ = "selected" )
							#elseif($detailIntegrasi.KATEG_PEMILIK =="2")
								#set ( $selected_swastaCT_KATEG_PEMILIK_ = "selected" )
							#end
				<option value="1" $selected_govCT_KATEG_PEMILIK_ >KERAJAAN</option>
				<option value="2" $selected_swastaCT_KATEG_PEMILIK_ >SWASTA</option>
				</select>
				</td>
		</tr>


<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Username
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="NAMA_LAIN_PEMILIK_$detailIntegrasi.ID_INTEGRASI" name="NAMA_LAIN_PEMILIK_$detailIntegrasi.ID_INTEGRASI" value="$detailIntegrasi.NAMA_LAIN_PEMILIK" >
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Password
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="NAMA_LAIN_PEMILIK_$detailIntegrasi.ID_INTEGRASI" name="NAMA_LAIN_PEMILIK_$detailIntegrasi.ID_INTEGRASI" value="$detailIntegrasi.NAMA_LAIN_PEMILIK" >
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
 #if($detailIntegrasi.ID_INTEGRASI=="")
<input type="button" id="cmdEditAgensi" name="cmdEditAgensi" value="Simpan" onClick="if(valEditIntegrasi('$detailIntegrasi.ID_INTEGRASI','$Type') == true){doDivAjaxCall$formname('div_AddInteg$Type','saveInteg','ID_INTEGRASI=&Type=$Type');}" >
#else
<input type="button" id="cmdEditAgensi" name="cmdEditAgensi" value="Simpan" onClick="doDivAjaxCall$formname('div_viewInteg$ID_INTEGRASI','saveInteg','ID_INTEGRASI=$detailIntegrasi.ID_INTEGRASI&Type=$Type');" >	 
#end

#if($Type=="")
<input type="button" id="cmdTutupAgensi" name="cmdTutupAgensi" value="Tutup" onClick="doDivAjaxCall$formname('div_AddInteg','closeCarian','');" >

#elseif($detailIntegrasi.ID_INTEGRASI=="")
<input type="button" id="cmdTutupAgensi" name="cmdTutupAgensi" value="Tutup" onClick="doDivAjaxCall$formname('div_AddInteg$Type','closeCarian','');" >

#else
<input type="button" id="cmdBatal" name="cmdBatal" value="Batal" onClick="doDivAjaxCall$formname('div_viewInteg$ID_INTEGRASI','addInteg','ID_INTEGRASI=$ID_INTEGRASI');" >
 
<input type="button" id="cmdTutupAgensi" name="cmdTutupAgensi" value="Tutup" onClick="doDivAjaxCall$formname('div_viewInteg$ID_INTEGRASI','closeCarian','');" >
#end
</td>
</tr>
		</table>
</fieldset>


	<br>
</td>		
</tr>




