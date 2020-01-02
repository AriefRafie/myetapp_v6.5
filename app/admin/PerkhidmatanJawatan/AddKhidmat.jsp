<script>
if( $jquery('#'+'div_rowKhidmat$viewKhidmat.ID_KHIDMAT').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowKhidmat$viewKhidmat.ID_KHIDMAT').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewKhidmat$viewKhidmat.ID_KHIDMAT').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewKhidmat$viewKhidmat.ID_KHIDMAT').offset().top);
	}
	
}
</script>

<!--#if ($result == "success")
<div class=info>Done.</div>
#end-->
<tr id="div_viewKhidmat$viewKhidmat.ID_KHIDMAT">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Kumpulan Perkhidmatan</legend>
<table width="100%" border="0">
				<!--<input  size="50" type="text" id="ID_KHIDMAT_$viewKhidmat.ID_KHIDMAT" 
		name="ID_KHIDMAT_$viewKhidmat.ID_KHIDMAT" 
		value="$!viewKhidmat.ID_KHIDMAT" >-->
 

    <tr>
        <td valign="top" >				
        </td>			
        <td valign="top" >Nama Kumpulan Perkhidmatan</td>
		<td valign="top" >:</td>
		<td valign="top" >
		<input  size="50" type="text" id="NAMA_KHIDMAT_$viewKhidmat.ID_KHIDMAT" 
		name="NAMA_KHIDMAT_$viewKhidmat.ID_KHIDMAT" style="text-transform:uppercase;"
		value="$!viewKhidmat.NAMA_KHIDMAT" >
        </td>
	</tr>
    <tr>
        <td valign="top" >				
        </td>			
        <td valign="top" >Nama Lain Kumpulan Perkhidmatan </td>
		<td valign="top" >:</td>
		<td valign="top" >
		<input  size="50" type="text" id="NAMA_LAIN_KHIDMAT_$viewKhidmat.ID_KHIDMAT" 
		name="NAMA_LAIN_KHIDMAT_$viewKhidmat.ID_KHIDMAT" style="text-transform:uppercase;"
		value="$!viewKhidmat.NAMA_LAIN_KHIDMAT" >
        </td>
	</tr>
    <tr>
		<td valign="top" ></td>			
		<td valign="top" >Keterangan</td>
		<td valign="top" >:</td>
		<td valign="top" >
		<textarea name="KETERANGAN_KHIDMAT_$viewKhidmat.ID_KHIDMAT" id="KETERANGAN_KHIDMAT_$viewKhidmat.ID_KHIDMAT" cols="50" rows="5">$!viewKhidmat.KETERANGAN_KHIDMAT</textarea></td></tr>
    <tr>
        <td valign="top" >
                    
        </td>			
        <td valign="top" >
      	Skop Gred 
        </td>
        <td valign="top" >
        :
        </td>
        <td>
<select id="SKOP_GRED1_$viewKhidmat.ID_KHIDMAT"  name="SKOP_GRED1_$viewKhidmat.ID_KHIDMAT" >	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJGRED )		
#set ( $selected_ruj = "" )						
#if($viewKhidmat.SKOP_GRED1==$ruj.KETERANGAN)
#set ( $selected_ruj = "selected" )
#end		
<option $selected_ruj value="$ruj.KETERANGAN" >
$ruj.KETERANGAN</option>
#end
</select> 
- 
<select id="SKOP_GRED2_$viewKhidmat.ID_KHIDMAT"  name="SKOP_GRED2_$viewKhidmat.ID_KHIDMAT" >	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj2 in $list_TBLRUJGRED )		
#set ( $selected_ruj2 = "" )						
#if($viewKhidmat.SKOP_GRED2==$ruj2.KETERANGAN)
#set ( $selected_ruj2 = "selected" )
#end		
<option $selected_ruj2 value="$ruj2.KETERANGAN" >
$ruj2.KETERANGAN</option>
#end
</select>
       <!--  <input  size="50" type="text" id="SKOP_GRED_$viewKhidmat.ID_KHIDMAT" name="SKOP_GRED_$viewKhidmat.ID_KHIDMAT" value="$!viewKhidmat.SKOP_GRED" >
 --></td>
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
    <td  >
<textarea name="CATATAN_$viewKhidmat.ID_KHIDMAT" id="CATATAN_$viewKhidmat.ID_KHIDMAT" cols="50" rows="5">$!viewKhidmat.CATATAN</textarea>
</td>
</tr>

 <tr>
		<td valign="top" ></td>			
		<td valign="top" >Sumber Maklumat</td>
		<td valign="top" >:</td>
		<td valign="top" >
		<input  size="50" type="text" id="SUMBER_MAKLUMAT_$viewKhidmat.ID_KHIDMAT" 
		name="SUMBER_MAKLUMAT_$viewKhidmat.ID_KHIDMAT" 
		value="$!viewKhidmat.SUMBER_MAKLUMAT" >
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
  #if($viewKhidmat.ID_KHIDMAT=="")
<input type="button" id="cmdEditKhidmat" name="cmdEditKhidmat" value="Simpan" onClick="if(valEditKhidmat('') == true){doDivAjaxCall$formname('div_ViewKhidmat','insertKhidmat','ID_KHIDMAT=');}" >
#else
<input type="button" id="cmdEditKhidmat" name="cmdEditKhidmat" value="Simpan" onClick="if(valEditKhidmat('$viewKhidmat.ID_KHIDMAT') == true){doDivAjaxCall$formname('div_viewKhidmat$viewKhidmat.ID_KHIDMAT','insertKhidmat','ID_KHIDMAT=$viewKhidmat.ID_KHIDMAT');}" >	 
#end

#if($viewKhidmat.ID_KHIDMAT=="")
<input type="button" id="cmdTutupKhidmat" name="cmdTutupKhidmat" value="Tutup" onClick="doDivAjaxCall$formname('div_ViewKhidmat','close_AddKhidmat','ID_KHIDMAT=');" >

#else
<input type="button" id="cmdTutupKhidmat" name="cmdTutupKhidmat" value="Tutup" onClick="doDivAjaxCall$formname('div_viewKhidmat$viewKhidmat.ID_KHIDMAT','close_AddKhidmat','ID_KHIDMAT=$viewKhidmat.ID_KHIDMAT');" >
#end

</table>
</fieldset>
<br>
</td>		
</tr>




