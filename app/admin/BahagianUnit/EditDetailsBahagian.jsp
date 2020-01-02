<script>
if( $jquery('#'+'div_rowPengguna$internalType$viewBahagianHQ.ID_SEKSYEN').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPengguna$internalType$viewBahagianHQ.ID_SEKSYEN').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewPejabat$internalType$viewBahagianHQ.ID_SEKSYEN').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewPejabat$internalType$viewBahagianHQ.ID_SEKSYEN').offset().top);
	}
	
}
</script>

<tr id="div_viewPejabat$internalType$viewBahagianHQ.ID_SEKSYEN">
<td align="left" valign="top" colspan="5">

<fieldset>
<legend>#if ($viewBahagianHQ.ID_SEKSYEN.equals("")) Tambah Bahagian 
#else 
Kemaskini Maklumat Bahagian
#end
</legend>
<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewBahagianHQ --></td>
			</tr>
			            <tr>
				<td valign="top" ><font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Nama Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="NAMA_SEKSYEN_$viewBahagianHQ.ID_SEKSYEN" 
				name="NAMA_SEKSYEN_$viewBahagianHQ.ID_SEKSYEN" style="text-transform:uppercase;"
				value="$viewBahagianHQ.NAMA_SEKSYEN" >
				</td>
			</tr>
            <tr>
            <td>
           <!-- <input type="text" id="ID_SEKSYEN" 
				name="ID_SEKSYEN" 
				value="$viewBahagianHQ.ID_SEKSYEN" >-->
        
            </td></tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Lain Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="10" type="text" id="KOD_SEKSYEN_$viewBahagianHQ.ID_SEKSYEN" 
				name="KOD_SEKSYEN_$viewBahagianHQ.ID_SEKSYEN" style="text-transform:uppercase;"
				value="$viewBahagianHQ.KOD_SEKSYEN" maxlength="10">
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
				<input  size="50" type="text" id="ALAMAT1_$viewBahagianHQ.ID_SEKSYEN" 
				name="ALAMAT1_$viewBahagianHQ.ID_SEKSYEN" 
				value="$viewBahagianHQ.ALAMAT_1" >
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
				<input  size="50" type="text" id="ALAMAT2_$viewBahagianHQ.ID_SEKSYEN" 
				name="ALAMAT2_$viewBahagianHQ.ID_SEKSYEN" 
				value="$viewBahagianHQ.ALAMAT_2" >
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
				<input  size="50" type="text" id="ALAMAT3_$viewBahagianHQ.ID_SEKSYEN" 
				name="ALAMAT3_$viewBahagianHQ.ID_SEKSYEN" 
				value="$viewBahagianHQ.ALAMAT_3" >
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
				<input  size="50" type="text" id="POSKOD_$viewBahagianHQ.ID_SEKSYEN" 
				name="POSKOD_$viewBahagianHQ.ID_SEKSYEN" 
				value="$viewBahagianHQ.POSKOD" >
				</td>
			</tr>
           <!-- <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Bandar
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="BANDAR_$viewBahagianHQ.ID_SEKSYEN" 
				name="BANDAR_$viewBahagianHQ.ID_SEKSYEN" 
				value="$viewBahagianHQ.BANDAR" >
				</td>
			</tr>-->
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<select id="ID_NEGERI_$viewBahagianHQ.ID_SEKSYEN"  name="ID_NEGERI_$viewBahagianHQ.ID_SEKSYEN"
			onChange = "doDivAjaxCall$formname('div_DAERAH_ID$viewBahagianHQ.ID_SEKSYEN','showListPejabat','ID_NEGERI='+$jquery('#ID_NEGERI_$viewBahagianHQ.ID_SEKSYEN').val());">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJNEGERI )		
							#set ( $selected_ruj = "" )
							#if($viewBahagianHQ.ID_NEGERI==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end	
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>					
							
						#end
					</select>
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No.Telefon
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="NO_TEL_$viewBahagianHQ.ID_SEKSYEN" 
				name="NO_TEL_$viewBahagianHQ.ID_SEKSYEN" 
				value="$viewBahagianHQ.NO_TEL" >
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Faks</td>
				<td valign="top" >
				:</td>
				<td valign="top" >
				<input  size="50" type="text" id="NO_FAX_$viewBahagianHQ.ID_SEKSYEN" 
				name="NO_FAX_$viewBahagianHQ.ID_SEKSYEN" 
				value="$viewBahagianHQ.NO_FAKS" >
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
				<textarea row="5" col="30" name="CATATAN_$viewBahagianHQ.ID_SEKSYEN">$viewBahagianHQ.CATATAN</textarea>
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" ><font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<select id="FLAG_AKTIF_$viewBahagianHQ.ID_SEKSYEN" name="FLAG_AKTIF_$viewBahagianHQ.ID_SEKSYEN">
				#set ( $selected_aktif = "" )
				#set ( $selected_tidakaktif = "" )
							#if($viewBahagianHQ.FLAG_AKTIF == "AKTIF" || $viewBahagianHQ.FLAG_AKTIF == "")
								#set ( $selected_aktif = "selected" )
							#else
								#set ( $selected_tidakaktif = "selected" )
							#end
				<option value="Y" $selected_aktif >AKTIF</option>
				<option value="N" $selected_tidakaktif >TIDAK AKTIF</option>
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
                
                #if ($viewBahagianHQ.ID_SEKSYEN!= "")
                <input type="button" id="cmdEditPejabat" name="cmdEditPejabat" value="Simpan" 
                onClick="if(valEditBahagian('$viewBahagianHQ.ID_SEKSYEN') == true){doDivAjaxCall$formname('div_viewBahagian$viewBahagianHQ.ID_SEKSYEN','saveBahagian','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');}" >
                #else	
                <input type="button" id="cmdEditPejabat" name="cmdEditPejabat" value="Simpan" 
                onClick="if(valEditBahagian('$viewBahagianHQ.ID_SEKSYEN') == true){doDivAjaxCall$formname('div_senaraiUtama','saveBahagian','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN&carianBahagianHQ='+$jquery('#NAMA_SEKSYEN_$viewBahagianHQ.ID_SEKSYEN').val());}" >
                #end
                
				#if ($viewBahagianHQ.ID_SEKSYEN!= "")
	   			<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" value="Tutup" 
	   			onClick="doDivAjaxCall$formname('div_viewBahagian$viewBahagianHQ.ID_SEKSYEN','batalBahagian','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');" >
	   			#else	
				<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" 
				onclick="doDivAjaxCall$formname('div_TambahBahagianHQ','batalBahagian','ID_SEKSYEN=');" value="Reset" >	
			#end
	   		
		</table>
</fieldset>


	<br>
</td>		
</tr>