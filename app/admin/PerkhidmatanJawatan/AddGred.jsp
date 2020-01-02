<script>
if( $jquery('#'+'div_rowGred$viewGred.ID_GRED').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowGred$viewGred.ID_GRED').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewGred$viewGred.ID_GRED').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewGred$viewGred.ID_GRED').offset().top);
	}
	
}
</script>

<!--#if ($result == "success")
<div class=info>Done.</div>
#end-->
<tr id="div_viewGred$viewGred.ID_GRED">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Gred</legend>
<table width="100%" border="0">
				<!--<input  size="50" type="text" id="ID_GRED_$viewGred.ID_GRED" 
		name="ID_GRED_$viewGred.ID_GRED" 
		value="$!viewGred.ID_GRED" >-->
<tr>
    <td valign="top" >
                
    </td>			
    <td valign="top" >
    Nama Klasifikasi
    </td>
    <td valign="top" >
    :
    </td>
    <td  >
        <select id="ID_KLASIFIKASI_$viewGred.ID_GRED"  name="ID_KLASIFIKASI_$viewGred.ID_GRED" >	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJKLASIFIKASI )		
#set ( $selected_ruj = "" )						#if($viewGred.ID_KLASIFIKASI==$ruj.ID)
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
        <td valign="top" >Nama Gred</td>
		<td valign="top" >:</td>
		<td valign="top" >
		<input  size="50" type="text" id="NAMA_GRED_$viewGred.ID_GRED" 
		name="NAMA_GRED_$viewGred.ID_GRED" style="text-transform:uppercase;"
		value="$!viewGred.NAMA_GRED" onKeyUp="gredRepeat(this.value,'$viewGred.ID_GRED');">
		<div id="div_CHECK_NAME_GRED_$viewGred.ID_GRED" >
	<input type="hidden" id="CHECK_GRED_$ID_GRED" name="CHECK_GRED_$ID_GRED" value="$checkGred" ></div>
        </td>
	</tr>
    <tr>
		<td valign="top" ></td>			
		<td valign="top" >Keterangan</td>
		<td valign="top" >:</td>
		<td valign="top" ><textarea name="KETERANGAN_$viewGred.ID_GRED" id="KETERANGAN_$viewGred.ID_GRED" cols="50" rows="5">$!viewGred.KETERANGAN</textarea></td>
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
				
				<select id="FLAG_STATUS_$viewGred.ID_GRED" name="FLAG_STATUS_$viewGred.ID_GRED">
				#set ( $selected_aktif = "" )
#set ( $selected_tidakaktif = "" )							
#if($viewGred.CT_FLAG_STATUS=="AKTIF" || $viewGred.CT_FLAG_STATUS=="")
#set ( $selected_aktif = "selected" )
#elseif($viewGred.CT_FLAG_STATUS=="TIDAK AKTIF")
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
  #if($viewGred.ID_GRED=="")
<input type="button" id="cmdEditGred" name="cmdEditGred" value="Simpan" onClick="if(valEditGred('$viewGred.ID_GRED') == true){doDivAjaxCall$formname('div_ViewGred','insertGred','ID_GRED=');}" >
#else
<input type="button" id="cmdEditGred" name="cmdEditGred" value="Simpan" onClick="if(valEditGred('$viewGred.ID_GRED') == true){doDivAjaxCall$formname('div_viewGred$viewGred.ID_GRED','updateGred','ID_GRED=$viewGred.ID_GRED');}" >	 
#end

#if($viewGred.ID_GRED=="")
<input type="button" id="cmdTutupGred" name="cmdTutupGred" value="Tutup" onClick="doDivAjaxCall$formname('div_ViewGred','close_AddGred','ID_GRED=');" >

#else
<input type="button" id="cmdTutupGred" name="cmdTutupGred" value="Tutup" onClick="doDivAjaxCall$formname('div_viewGred$viewGred.ID_GRED','close_AddGred','ID_GRED=$viewGred.ID_GRED');" >
#end
</table>
</fieldset>


	<br>
</td>		
</tr>




