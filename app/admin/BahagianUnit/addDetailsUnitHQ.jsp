<script>
if( $jquery('#'+'div_viewUnitHQ$ID_SEKSYEN').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_viewUnitHQ$ID_SEKSYEN').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewUnitHQ$ID_SEKSYEN').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewUnitHQ$ID_SEKSYEN').offset().top);
	}
	
}
</script>


<tr id="div_viewUnitHQ$ID_SEKSYEN">
<td align="left" valign="top" colspan="5">

<fieldset>
<legend>Maklumat Unit</legend>
<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewUnitHQ --></td>
			</tr>
			      <tr>
                  <!-- <input type="text" value="$ID_SEKSYEN" name="ID_SEKSYEN"/>
                   <input type="text" value="$ID_UNIT" name="ID_UNIT"/> -->
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Unit/Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="50" type="text" id="NAMA_UNIT_$ID_SEKSYEN" name="NAMA_UNIT_$ID_SEKSYEN" style="text-transform:uppercase;" value="" >
				</td>
			</tr>
            <tr>
            <td>
           <!-- <input type="text" id="ID_SEKSYEN" 
				name="ID_SEKSYEN" 
				value="$ID_SEKSYEN" >-->
        
            </td></tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kod Unit/Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input  size="5" type="text" id="KOD_UNIT_$ID_SEKSYEN" 
				name="KOD_UNIT_$ID_SEKSYEN" style="text-transform:uppercase;"
				value="" maxlength="2" >
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
				<input  size="50" type="text" id="ALAMAT1_$ID_SEKSYEN" 
				name="ALAMAT1_$ID_SEKSYEN" style="text-transform:uppercase;"
				value="" >
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
				<input  size="50" type="text" id="ALAMAT2_$ID_SEKSYEN" 
				name="ALAMAT2_$ID_SEKSYEN" style="text-transform:uppercase;"
				value="" >
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
				<input  size="50" type="text" id="ALAMAT3_$ID_SEKSYEN" 
				name="ALAMAT3_$ID_SEKSYEN" style="text-transform:uppercase;"
				value="" >
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
				<input  type="text" id="POSKOD_$ID_SEKSYEN" 
				name="POSKOD_$ID_SEKSYEN" style="text-transform:uppercase;"
				value="" maxlength="5">
				</td>
			</tr>
            
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
				<select id="ID_NEGERI_$ID_SEKSYEN"  name="ID_NEGERI_$ID_SEKSYEN"
			onChange = "doDivAjaxCall$formname('div_DAERAH_ID$ID_SEKSYEN','showListPejabat','ID_NEGERI='+$jquery('#ID_NEGERI_$ID_SEKSYEN').val());">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJNEGERI )		
							#set ( $selected_ruj = "" )
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>					
							
						#end
					</select>
				</td>
			</tr>
            
            	<tr>
				<td valign="top" >	
				<!--<font color="red" >*</font>	-->		
				</td>			
				<td valign="top" >
				Daerah
				</td>
				<td valign="top" >
				:
				</td>
				<td id="div_DAERAH_ID$ID_SEKSYEN">
					<select id="ID_DAERAH_$ID_SEKSYEN"  name="ID_DAERAH_$ID_SEKSYEN" 
					onChange="doDivAjaxCall$formname('div_BANDAR_ID$ID_SEKSYEN','showListBandar','ID_NEGERI='+$jquery('#ID_NEGERI_$ID_SEKSYEN').val()+'&ID_DAERAH='+$jquery('#ID_DAERAH_$ID_SEKSYEN').val());"
					>	  
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJDAERAH )		
							#set ( $selected_ruj = "" )
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
				Bandar
				</td>
				<td valign="top" >
				:
				</td>
				<!--<td valign="top" >
				<input  size="50" type="text" id="BANDAR_$ID_SEKSYEN" 
				name="BANDAR_$ID_SEKSYEN" 
				value="$viewUnitHQ.BANDAR" >
				</td>-->
                <td id="div_BANDAR_ID$ID_SEKSYEN">
					<select id="ID_BANDAR_$ID_SEKSYEN"  
					name="ID_BANDAR_$ID_SEKSYEN"  >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJBANDAR )		
							#set ( $selected_ruj = "" )
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
				<input  size="50" type="text" id="NO_TEL_$ID_SEKSYEN" 
				name="NO_TEL_$ID_SEKSYEN" style="text-transform:uppercase;"
				value="" >
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
				<input  size="50" type="text" id="NO_FAX_$ID_SEKSYEN" 
				name="NO_FAX_$ID_SEKSYEN" style="text-transform:uppercase;"
				value="" >
				</td>
			</tr> 
			
			<!--<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<textarea row="5" col="30" name="CATATAN_$ID_SEKSYEN">$viewUnitHQ.CATATAN</textarea>
				</td>
			</tr>-->
			
			
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
				<select id="FLAG_AKTIF_$ID_SEKSYEN" name="FLAG_AKTIF_$ID_SEKSYEN">
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
                
                <input type="button" id="cmdEditPejabat" name="cmdEditPejabat" value="Simpan" 
                onClick="doDivAjaxCall$formname('div_viewUnit$ID_SEKSYEN$ID_UNIT','saveUnitHQ','ID_SEKSYEN=$ID_SEKSYEN&ID_UNIT=');" >
				
				<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" 
				onclick="doDivAjaxCall$formname('div_viewUnit$ID_SEKSYEN','batalBahagian','ID_SEKSYEN=$ID_SEKSYEN');" value="Tutup" >	

	   		
		</table>
</fieldset>


	<br>
</td>		
</tr>