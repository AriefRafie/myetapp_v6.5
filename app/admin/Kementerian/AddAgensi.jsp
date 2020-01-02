<script>
if( $jquery('#'+'div_rowAgensi$detailsAgensi.ID_AGENSI').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowAgensi$detailsAgensi.ID_AGENSI').offset().top);
}
else
{
	
	if( $jquery('#'+'div_detailsAgensi$detailsAgensi.ID_AGENSI').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_detailsAgensi$detailsAgensi.ID_AGENSI').offset().top);
	}
	
}
</script>

<!--#if ($result == "success")
<div class=info>Done.</div>
#end-->
<tr>
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Agensi</legend>
<table width="100%" border="0">
				<!--<input  size="50" type="text" id="ID_AGENSI_$detailsAgensi.ID_AGENSI" 
		name="ID_AGENSI_$detailsAgensi.ID_AGENSI" 
		value="$!detailsAgensi.ID_AGENSI" >-->

<tr>
    <td valign="top" >
                
    </td>			
    <td valign="top" >
    Nama Agensi
    </td>
    <td valign="top" >
    :
    </td>
    <td  >
    <input  size="50" type="text" id="NAMA_AGENSI_$detailsAgensi.ID_AGENSI" name="NAMA_AGENSI_$detailsAgensi.ID_AGENSI" value="$detailsAgensi.NAMA_AGENSI" style="text-transform:uppercase;" >
</td>
</tr>

<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Kod Agensi</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="10" type="text" id="KOD_AGENSI_$detailsAgensi.ID_AGENSI" 
    name="KOD_AGENSI_$detailsAgensi.ID_AGENSI" style="text-transform:uppercase;"
    value="$detailsAgensi.KOD_AGENSI" >
    </td>
</tr>


<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Alamat</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="50" type="text" id="ALAMAT1_$detailsAgensi.ID_AGENSI" 
    name="ALAMAT1_$detailsAgensi.ID_AGENSI" style="text-transform:uppercase;"
    value="$detailsAgensi.ALAMAT1" >
    </td>
</tr>


<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Alamat </td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="50" type="text" id="ALAMAT2_$detailsAgensi.ID_AGENSI" 
    name="ALAMAT2_$detailsAgensi.ID_AGENSI" style="text-transform:uppercase;"
    value="$detailsAgensi.ALAMAT2" >
    </td>
</tr>


<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Alamat</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="50" type="text" id="ALAMAT3_$detailsAgensi.ID_AGENSI" 
    name="ALAMAT3_$detailsAgensi.ID_AGENSI" style="text-transform:uppercase;"
    value="$detailsAgensi.ALAMAT3" >
    </td>
</tr>

<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Poskod</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="20"  maxlength="5" type="text" id="POSKOD_$detailsAgensi.ID_AGENSI" 
    name="POSKOD_$detailsAgensi.ID_AGENSI" 
    value="$detailsAgensi.POSKOD" >
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
    <td  >
<select id="ID_NEGERI_$detailsAgensi.ID_AGENSI"  name="ID_NEGERI_$detailsAgensi.ID_AGENSI" >	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJNEGERI)		
#set ( $selected_ruj = "" )						
#if($detailsAgensi.ID_NEGERI == $ruj.ID)
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
    Status
    </td>
    <td valign="top" >
    :
    </td>
    <td  >
<select id="FLAG_AKTIF_$detailsAgensi.ID_AGENSI"  name="FLAG_AKTIF_$detailsAgensi.ID_AGENSI" >	   
<option value = "" >SILA PILIH</option>
#set ( $selected_aktif = "" )
#set ( $selected_tidakaktif = "" )
#if($detailsAgensi.FLAG_AKTIF=="AKTIF" || $detailsAgensi.FLAG_AKTIF=="")
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
 #if($detailsAgensi.ID_AGENSI=="")
<input type="button" id="cmdEditAgensi" name="cmdEditAgensi" value="Simpan" onClick="doDivAjaxCall$formname('div_AddAgensi$ID_KEMENTERIAN','saveAgensi','ID_KEMENTERIAN=$ID_KEMENTERIAN&ID_AGENSI=');" >
#else
<input type="button" id="cmdEditAgensi" name="cmdEditAgensi" value="Simpan" onClick="doDivAjaxCall$formname('div_viewAgensi$ID_AGENSI','saveAgensi','ID_AGENSI=$detailsAgensi.ID_AGENSI&ID_KEMENTERIAN=$ID_KEMENTERIAN');" >	 
#end

#if($detailsAgensi.ID_AGENSI=="")
<input type="button" id="cmdTutupAgensi" name="cmdTutupAgensi" value="Tutup" onClick="doDivAjaxCall$formname('div_AddAgensi$ID_KEMENTERIAN','closeDiv','ID_KEMENTERIAN=$ID_KEMENTERIAN');" >

#else
<input type="button" id="cmdBatal" name="cmdBatal" value="Batal" onClick="doDivAjaxCall$formname('div_viewAgensi$ID_AGENSI','addAgensi','ID_AGENSI=$detailsAgensi.ID_AGENSI');" >
 
<input type="button" id="cmdTutupAgensi" name="cmdTutupAgensi" value="Tutup" onClick="doDivAjaxCall$formname('div_viewAgensi$ID_AGENSI','closeDiv','');" >
#end

		</table>
</fieldset>


	<br>
</td>		
</tr>




