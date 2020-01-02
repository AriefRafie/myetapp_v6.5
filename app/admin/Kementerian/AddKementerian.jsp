<script>
if( $jquery('#'+'div_rowKementerian$detailsKementerian.ID_KEMENTERIAN').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowKementerian$detailsKementerian.ID_KEMENTERIAN').offset().top);
}
else
{
	
	if( $jquery('#'+'div_detailsKementerian$detailsKementerian.ID_KEMENTERIAN').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_detailsKementerian$detailsKementerian.ID_KEMENTERIAN').offset().top);
	}
	
}
</script>

<!--#if ($result == "success")
<div class=info>Done.</div>
#end-->
<tr>
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Kementerian</legend>
<table width="100%" border="0">
				<!--<input  size="50" type="text" id="ID_KEMENTERIAN_$detailsKementerian.ID_KEMENTERIAN" 
		name="ID_KEMENTERIAN_$detailsKementerian.ID_KEMENTERIAN" 
		value="$!detailsKementerian.ID_KEMENTERIAN" >-->

<tr>
    <td valign="top" >
     <font color="red">*</font>           
    </td>			
    <td valign="top" >
    Nama Kementerian
    </td>
    <td valign="top" >
    :
    </td>
    <td  >
    <input  size="70" type="text" id="NAMA_KEMENTERIAN_$detailsKementerian.ID_KEMENTERIAN" 
name="NAMA_KEMENTERIAN_$detailsKementerian.ID_KEMENTERIAN" style="text-transform:uppercase;"
value="$detailsKementerian.NAMA_KEMENTERIAN" >
</td>
</tr>

<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Kod Kementerian</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="10" type="text" id="KOD_KEMENTERIAN_$detailsKementerian.ID_KEMENTERIAN" 
    name="KOD_KEMENTERIAN_$detailsKementerian.ID_KEMENTERIAN" style="text-transform:uppercase;"
    value="$detailsKementerian.KOD_KEMENTERIAN" >
    </td>
</tr>


<tr>
    <td valign="top" ><font color="red">*</font>				
    </td>			
    <td valign="top" >Alamat</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="50" type="text" id="ALAMAT1_$detailsKementerian.ID_KEMENTERIAN" 
    name="ALAMAT1_$detailsKementerian.ID_KEMENTERIAN" style="text-transform:uppercase;"
    value="$detailsKementerian.ALAMAT1" >
    </td>
</tr>


<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Alamat </td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="50" type="text" id="ALAMAT2_$detailsKementerian.ID_KEMENTERIAN" 
    name="ALAMAT2_$detailsKementerian.ID_KEMENTERIAN" style="text-transform:uppercase;"
    value="$detailsKementerian.ALAMAT2" >
    </td>
</tr>


<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >Alamat</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="50" type="text" id="ALAMAT3_$detailsKementerian.ID_KEMENTERIAN" 
    name="ALAMAT3_$detailsKementerian.ID_KEMENTERIAN" style="text-transform:uppercase;"
    value="$detailsKementerian.ALAMAT3" >
    </td>
</tr>

<tr>
    <td valign="top" ><font color="red">*</font>				
    </td>			
    <td valign="top" >Poskod</td>
    <td valign="top" >:</td>
    <td valign="top" >
    <input  size="20" type="text" id="POSKOD_$detailsKementerian.ID_KEMENTERIAN" 
    name="POSKOD_$detailsKementerian.ID_KEMENTERIAN" 
    value="$detailsKementerian.POSKOD"  maxlength="5" >
    </td>
</tr>
<tr>
    <td valign="top" ><font color="red">*</font>
    </td>			
    <td valign="top" >
    Nama Negeri
    </td>
    <td valign="top" >
    :
    </td>
    <td  >
<select id="ID_NEGERI_$detailsKementerian.ID_KEMENTERIAN"  name="ID_NEGERI_$detailsKementerian.ID_KEMENTERIAN" >	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJNEGERI)		
#set ( $selected_ruj = "" )						
#if($detailsKementerian.ID_NEGERI == $ruj.ID)
#set ( $selected_ruj = "selected" )
#end		
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN</option>
#end
</select>
</td>
</tr>

<tr>
    <td valign="top" ><font color="red">*</font>
    </td>			
    <td valign="top" >
    Status
    </td>
    <td valign="top" >
    :
    </td>
    <td  >
<select id="FLAG_AKTIF_$detailsKementerian.ID_KEMENTERIAN"  name="FLAG_AKTIF_$detailsKementerian.ID_KEMENTERIAN" >	   
<option value = "" >SILA PILIH</option>
#set ( $selected_aktif = "" )
#set ( $selected_tidakaktif = "" )
#if($detailsKementerian.FLAG_AKTIF=="AKTIF" || $detailsKementerian.FLAG_AKTIF=="")
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
 #if($detailsKementerian.ID_KEMENTERIAN=="")
<input type="button" id="cmdEditKementerian" name="cmdEditKementerian" value="Simpan" onClick="doDivAjaxCall$formname('div_senaraiUtama','saveKementerian','ID_KEMENTERIAN=&carianUmum='+$jquery('#NAMA_KEMENTERIAN_$detailsKementerian.ID_KEMENTERIAN').val());" >
#else
<input type="button" id="cmdEditKementerian" name="cmdEditKementerian" value="Simpan" onClick="doDivAjaxCall$formname('div_viewKementerian$ID_KEMENTERIAN','saveKementerian','ID_KEMENTERIAN=$detailsKementerian.ID_KEMENTERIAN');" >	 
#end

#if($detailsKementerian.ID_KEMENTERIAN=="")
<input type="button" id="cmdTutupKementerian" name="cmdTutupKementerian" value="Tutup" onClick="doDivAjaxCall$formname('div_AddKementerian','closeDiv','');" >

#else
<input type="button" id="cmdBatal" name="cmdBatal" value="Batal" onClick="doDivAjaxCall$formname('div_viewKementerian$ID_KEMENTERIAN','addKementerian','ID_KEMENTERIAN=$detailsKementerian.ID_KEMENTERIAN');" >
 
<input type="button" id="cmdTutupKementerian" name="cmdTutupKementerian" value="Tutup" onClick="doDivAjaxCall$formname('div_viewKementerian$ID_KEMENTERIAN','closeDiv','');" >
#end

		</table>
</fieldset>


	<br>
</td>		
</tr>




