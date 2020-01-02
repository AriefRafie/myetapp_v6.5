<script>
if( $jquery('#'+'div_rowSkimKhidmat$viewSkimKhidmat.ID_JAWATAN').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowSkimKhidmat$viewSkimKhidmat.ID_JAWATAN').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN').offset().top);
	}
	
}
</script>

<!--#if ($result == "success")
<div class=info>Done.</div>
#end-->
<tr id="div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Skim</legend>
<table width="100%" border="0">
				<!--<input  size="50" type="text" id="ID_JAWATAN_$viewSkimKhidmat.ID_JAWATAN" 
		name="ID_JAWATAN_$viewSkimKhidmat.ID_JAWATAN" 
		value="$!viewSkimKhidmat.ID_JAWATAN" >-->
 

    <tr>
        <td valign="top" >				
        </td>			
        <td valign="top" >Nama Skim/ Nama Jawatan</td>
		<td valign="top" >:</td>
		<td valign="top" >
		<input  size="50" type="text" id="KETERANGAN_$viewSkimKhidmat.ID_JAWATAN" 
		name="KETERANGAN_$viewSkimKhidmat.ID_JAWATAN" 
		value="$!viewSkimKhidmat.KETERANGAN" >
        </td>
	</tr>
    <tr>
        <td valign="top" >				
        </td>			
        <td valign="top" >Kod Skim</td>
		<td valign="top" >:</td>
		<td valign="top" >
		<input  size="50" type="text" id="KOD_SKIM_$viewSkimKhidmat.ID_JAWATAN" 
		name="KOD_SKIM_$viewSkimKhidmat.ID_JAWATAN" 
		value="$!viewSkimKhidmat.KOD_SKIM" >
        </td>
	</tr>
    <tr>
		<td valign="top" ></td>			
		<td valign="top" >Keterangan</td>
		<td valign="top" >:</td>
		<td valign="top" >
		<input  size="50" type="text" id="KETERANGAN_TUGASAN_$viewSkimKhidmat.ID_JAWATAN" 
		name="KETERANGAN_TUGASAN_$viewSkimKhidmat.ID_JAWATAN" 
		value="$!viewSkimKhidmat.KETERANGAN_TUGASAN" >
        </td>
	</tr>
    <tr>
        <td valign="top" >
                    
        </td>			
        <td valign="top" >
        Kumpulan Perkhidmatan
        </td>
        <td valign="top" >
        :
        </td>
        <td  >
					<select id="ID_KHIDMAT_$viewSkimKhidmat.ID_JAWATAN"  name="ID_KHIDMAT_$viewSkimKhidmat.ID_JAWATAN" >	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJKHIDMAT )		
#set ( $selected_ruj = "" )						#if($viewSkimKhidmat.ID_KHIDMAT==$ruj.ID)
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
        Nama Klasifikasi
        </td>
        <td valign="top" >
        :
        </td>
        <td  >
		<select id="ID_KLASIFIKASI_$viewSkimKhidmat.ID_JAWATAN"  name="ID_KLASIFIKASI_$viewSkimKhidmat.ID_JAWATAN" >	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJKLASIFIKASI )		
#set ( $selected_ruj = "" )						#if($viewSkimKhidmat.ID_KLASIFIKASI==$ruj.ID)
#set ( $selected_ruj = "selected" )
#end		
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN/$ruj.KETERANGAN2</option>
#end
</select>
</td>
</tr>

<!--<tr>
<td valign="top" >
            
</td>			
<td valign="top" >
Gred
</td>
<td valign="top" >
:
</td>
<td  >
<select id="ID_GRED_$viewSkimKhidmat.ID_JAWATAN"  name="ID_GRED_$viewSkimKhidmat.ID_JAWATAN" >	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJGRED )		
#set ( $selected_ruj = "" )						#if($viewSkimKhidmat.ID_GRED==$ruj.ID)
#set ( $selected_ruj = "selected" )
#end		
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN</option>
#end
</select>
</td>
</tr>
-->
 <tr>
		<td valign="top" ></td>			
		<td valign="top" >Sumber Maklumat</td>
		<td valign="top" >:</td>
		<td valign="top" >
		<input  size="50" type="text" id="SUMBER_MAKLUMAT_$viewSkimKhidmat.ID_JAWATAN" 
		name="SUMBER_MAKLUMAT_$viewSkimKhidmat.ID_JAWATAN" 
		value="$!viewSkimKhidmat.SUMBER_MAKLUMAT" >
        </td>
	</tr>
    <!---->
<!--<tr>
<td valign="top" ></td>			
<td valign="top" >Gred</td>
<td valign="top" >:</td>
<td><select name="selectGred" class="inputFieldb" id="selectGred">
<option>Silih Pilih Gred</option>
#foreach ($Gred in $listGred)
<option value="$Gred.ID_JAWATAN/$Gred.ID_GRED">$Gred.NAMA_GRED</option>
#end
</select></td>
</tr>-->
    <!---->
	<tr>
    <td valign="top" >				
    </td>			
    <td valign="top" >				
    </td>
    <td valign="top" >				
    </td>
    <td valign="top" >
  #if($viewSkimKhidmat.ID_JAWATAN=="")
<input type="button" id="cmdEditSkimKhidmat" name="cmdEditSkimKhidmat" value="Simpan" onClick="if(valEditSkimKhidmat('$viewSkimKhidmat.ID_JAWATAN') == true){doDivAjaxCall$formname('div_ViewSkim','insertSkimKhidmat','ID_JAWATAN=$viewSkimKhidmat.ID_JAWATAN');}" >
#else
<input type="button" id="cmdEditSkimKhidmat" name="cmdEditSkimKhidmat" value="Simpan" onClick="if(valEditSkimKhidmat('$viewSkimKhidmat.ID_JAWATAN') == true){doDivAjaxCall$formname('div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN','updateSkimKhidmat','ID_JAWATAN=$viewSkimKhidmat.ID_JAWATAN');}" >	 
#end

#if($viewSkimKhidmat.ID_JAWATAN=="")
<input type="button" id="cmdTutupSkimKhidmat" name="cmdTutupSkimKhidmat" value="Tutup" onClick="doDivAjaxCall$formname('div_ViewSkim','close_AddSkimKhidmat','ID_JAWATAN=');" >

#else
<input type="button" id="cmdTutupSkimKhidmat" name="cmdTutupSkimKhidmat" value="Tutup" onClick="doDivAjaxCall$formname('div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN','close_AddSkimKhidmat','ID_JAWATAN=$viewSkimKhidmat.ID_JAWATAN');" >
#end

</table>
</fieldset>
<br>
</td>		
</tr>




