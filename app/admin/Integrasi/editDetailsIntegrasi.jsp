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
<legend>Maklumat Integrasi Dalaman</legend>
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
<option value="1" selected >DALAMAN</option>
</select>

</td>
</tr>

<tr>
    <td valign="top" align="right">&nbsp;</td>			
    <td valign="top" >
    Jenis Pejabat Urusan
    </td>
    <td valign="top" >
    :
    </td>
    <td  > 
<select id="JENIS_PU_$detailIntegrasi.ID_INTEGRASI"  name="JENIS_PU_$detailIntegrasi.ID_INTEGRASI">	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJJENISPEJABAT)		
#set ( $selected_ruj = "" )						
#if($detailIntegrasi.ID_JENISPEJABAT == $ruj.ID)
#set ( $selected_ruj = "selected" )
#end		
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN</option>
#end
</select>

</td>
</tr>

<tr>
    <td valign="top" >&nbsp;</td>			
    <td valign="top" >
    Nama Agensi
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
    Nama Lain
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
    <td valign="top" >&nbsp;</td>			
    <td valign="top" >Kod Agensi</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="5" type="text" id="KOD_AGENSI_$detailIntegrasi.ID_INTEGRASI" 
    name="KOD_AGENSI_$detailIntegrasi.ID_INTEGRASI" 
    value="$detailIntegrasi.KOD_AGENSI" >
    </td>
</tr>

<tr>
    <td valign="top" >&nbsp;</td>			
    <td valign="top" >Kategori Agensi</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <select id="KATEGORI_$detailIntegrasi.ID_INTEGRASI" name="KATEGORI_$detailIntegrasi.ID_INTEGRASI">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_govCT_KATEGORI_ = "" )
				#set ( $selected_swastaCT_KATEGORI_ = "" )
							#if($detailIntegrasi.ID_KATEGORI =="1" )
								#set ( $selected_govCT_KATEGORI_ = "selected" )
							#elseif($detailIntegrasi.ID_KATEGORI =="2")
								#set ( $selected_swastaCT_KATEGORI_ = "selected" )
							#end
				<option value="1" $selected_govCT_KATEGORI_ >KERAJAAN</option>
				<option value="2" $selected_swastaCT_KATEGORI_ >SWASTA</option>
				</select>
    </td>
</tr>


<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Alamat</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="50" type="text" id="ALAMAT1_$detailIntegrasi.ID_INTEGRASI" 
    name="ALAMAT1_$detailIntegrasi.ID_INTEGRASI" 
    value="$detailIntegrasi.ALAMAT1" >
    </td>
</tr>


<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Alamat </td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="50" type="text" id="ALAMAT2_$detailIntegrasi.ID_INTEGRASI" 
    name="ALAMAT2_$detailIntegrasi.ID_INTEGRASI" 
    value="$detailIntegrasi.ALAMAT2" >
    </td>
</tr>


<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Alamat</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="50" type="text" id="ALAMAT3_$detailIntegrasi.ID_INTEGRASI" 
    name="ALAMAT3_$detailIntegrasi.ID_INTEGRASI" 
    value="$detailIntegrasi.ALAMAT3" >
    </td>
</tr>

<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Poskod</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="50" type="text" id="POSKOD_$detailIntegrasi.ID_INTEGRASI" 
    name="POSKOD_$detailIntegrasi.ID_INTEGRASI" 
    value="$detailIntegrasi.POSKOD" >
    </td>
</tr>
<tr>
    <td valign="top" >&nbsp;</td>			
    <td valign="top" >
    Nama Negeri
    </td>
    <td valign="top" >
    :
    </td>
    <td  >
<select id="ID_NEGERI_$detailIntegrasi.ID_INTEGRASI"  name="ID_NEGERI_$detailIntegrasi.ID_INTEGRASI" >	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJNEGERI)		
#set ( $selected_ruj = "" )						
#if($detailIntegrasi.ID_NEGERI == $ruj.ID)
#set ( $selected_ruj = "selected" )
#end		
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN</option>
#end
</select>
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
				<input  size="50" type="text" id="EMEL_$detailIntegrasi.ID_INTEGRASI" name="EMEL_$detailIntegrasi.ID_INTEGRASI" value="$detailIntegrasi.EMEL" >
				</td>
			</tr>
			
			<!--<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				URL Portal
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="EMEL_$detailIntegrasi.ID_INTEGRASI" name="EMEL_$detailIntegrasi.ID_INTEGRASI" value="$detailIntegrasi.EMEL" >
				</td>
			</tr>-->
			
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




