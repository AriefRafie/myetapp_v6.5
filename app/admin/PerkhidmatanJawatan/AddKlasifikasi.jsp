<script>
if( $jquery('#'+'div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI').offset().top);
	}
	
}
</script>

<!--#if ($result == "success")
<div class=info>Done.</div>
#end-->
<tr id="div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Klasifikasi</legend>
<table width="100%" border="0">
		<!--<input  size="50" type="text" id="ID_KLASIFIKASI_$viewKlasifikasi.ID_KLASIFIKASI" 
		name="ID_GRED_$viewKlasifikasi.ID_KLASIFIKASI" 
		value="$!viewKlasifikasi.ID_KLASIFIKASI" >-->
    <tr>
        <td valign="top" >				
        </td>			
        <td valign="top" >Nama Klasifikasi</td>
		<td valign="top" >:</td>
		<td valign="top" >
		<input  size="50" type="text" id="NAMA_KLASIFIKASI_$viewKlasifikasi.ID_KLASIFIKASI" 
		name="NAMA_KLASIFIKASI_$viewKlasifikasi.ID_KLASIFIKASI" style="text-transform:uppercase;"
		value="$!viewKlasifikasi.NAMA_KLASIFIKASI" onKeyUp="valRepeat(this.value,'$viewKlasifikasi.ID_KLASIFIKASI');">
		<div id="div_CHECK_NAME_CLASS_$viewKlasifikasi.ID_KLASIFIKASI" >
	<input type="hidden" id="CHECK_KOD_$ID_KLASIFIKASI" name="CHECK_KOD_$ID_KLASIFIKASI" value="$checkKod" ></div>
        </td>
        
	</tr>
    <tr>
		<td valign="top" ></td>			
		<td valign="top" >Keterangan</td>
		<td valign="top" >:</td>
		<td valign="top" >
        
        <textarea name="KETERANGAN_$viewKlasifikasi.ID_KLASIFIKASI" id="KETERANGAN_$viewKlasifikasi.ID_KLASIFIKASI" cols="50" rows="5">$!viewKlasifikasi.KETERANGAN</textarea>
		<!--<input  size="50" type="text" id="KETERANGAN_$viewKlasifikasi.ID_KLASIFIKASI" 
		name="KETERANGAN_$viewKlasifikasi.ID_KLASIFIKASI" 
		value="$!viewKlasifikasi.KETERANGAN">-->
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
				
				<select id="FLAG_STATUS_$viewKlasifikasi.ID_KLASIFIKASI" name="FLAG_STATUS_$viewKlasifikasi.ID_KLASIFIKASI">

                #set ( $selected_aktif = "" )
                #set ( $selected_tidakaktif = "" )							
                #if($viewGred.CT_FLAG_STATUS=="AKTIF" || $viewGred.CT_FLAG_STATUS=="")
                #set ( $selected_aktif = "selected" )
                #elseif($viewGred.CT_FLAG_STATUS=="TIDAK AKTIF")
                #set ( $selected_tidakaktif = "selected" )
                #end
                <option value="1" $selected_aktif >AKTIF</option>
                <option value="2" $selected_tidakaktif >TIDAK AKTIF</option></select>
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
 <!--Insert-->
  #if($viewKlasifikasi.ID_KLASIFIKASI=="")
   <input type="button" id="cmdEditKlasifikasi" name="cmdEditKlasifikasi" value="Simpan" onClick="doDivAjaxCall$formname('div_ViewKlasifikasi','insertKlasifikasi','ID_KLASIFIKASI=$viewKlasifikasi.ID_KLASIFIKASI&carianKlasifikasi='+$jquery('#carianKlasifikasi').val(''));hideAddDiv();" >
 
#else
<input type="button" id="cmdEditKlasifikasi" name="cmdEditKlasifikasi" value="Simpan" onClick="if(valEditKlasifikasi('$viewKlasifikasi.ID_KLASIFIKASI') == true){doDivAjaxCall$formname('div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI','updateKlasifikasi','ID_KLASIFIKASI=$viewKlasifikasi.ID_KLASIFIKASI');}" >	 
#end

<!--Tutup-->
#if($viewKlasifikasi.ID_KLASIFIKASI=="")			 
<input type="button" id="cmdTutupKlasifikasi" name="cmdTutupKlasifikasi" value="Tutup" onClick="doDivAjaxCall$formname('div_ViewKlasifikasi','close_AddKlasifikasi','ID_KLASIFIKASI=');" >
   
#else		 
<input type="button" id="cmdTutupKlasifikasi" name="cmdTutupKlasifikasi" value="Tutup" onClick="doDivAjaxCall$formname('div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI','close_AddKlasifikasi','ID_KLASIFIKASI=$viewKlasifikasi.ID_KLASIFIKASI');" >
#end
<!--  div_senaraiUtamaKlasifikasi.style.display = "none" -->
		</table>
</fieldset>


	<br>
</td>		
</tr>

<script>

function hideAddDiv(){
	
   var div_AddKlasifikasi =  document.getElementById('div_AddKlasifikasi');
   div_AddKlasifikasi.style.display = "none";
}

</script>

 
