<script>
if( $jquery('#'+'div_viewUnitHQ$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_viewUnitHQ$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewUnitHQ$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewUnitHQ$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG').offset().top);
	}
	
}
</script>

<tr id="div_viewUnitHQ$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG">
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
				<input  size="50" type="text" id="NAMA_UNIT_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" 
				name="NAMA_UNIT_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" style="text-transform:uppercase;"
				value="$viewUnitHQ.NAMA_UNIT" >
				</td>
			</tr>
            <tr>
            <td>
           <!-- <input type="text" id="ID_SEKSYEN" 
				name="ID_SEKSYEN" 
				value="$viewUnitHQ.ID_PEJABATJKPTG" >-->
        
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
				<input  size="5" type="text" id="KOD_UNIT_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" 
				name="KOD_UNIT_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" style="text-transform:uppercase;"
				value="$viewUnitHQ.KOD_JKPTG" maxlength="2" >
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
				<input  size="50" type="text" id="ALAMAT1_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" 
				name="ALAMAT1_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" style="text-transform:uppercase;"
				value="$viewUnitHQ.ALAMAT1" >
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
				<input  size="50" type="text" id="ALAMAT2_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" 
				name="ALAMAT2_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" style="text-transform:uppercase;"
				value="$viewUnitHQ.ALAMAT2" >
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
				<input  size="50" type="text" id="ALAMAT3_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" 
				name="ALAMAT3_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" style="text-transform:uppercase;"
				value="$viewUnitHQ.ALAMAT3" >
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
				<input  type="text" id="POSKOD_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" 
				name="POSKOD_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" style="text-transform:uppercase;"
				value="$viewUnitHQ.POSKOD" maxlength="5">
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
				<select id="ID_NEGERI_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG"  name="ID_NEGERI_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG"
			onChange = "doDivAjaxCall$formname('div_DAERAH_ID$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG','showListPejabatUnit','ID_PEJABAT=$viewUnitHQ.ID_PEJABATJKPTG&ID_NEGERI='+$jquery('#ID_NEGERI_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG').val());">	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJNEGERI )		
							#set ( $selected_ruj = "" )
							#if($viewUnitHQ.ID_NEGERI==$ruj.ID)
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
				<!--<font color="red" >*</font>	-->		
				</td>			
				<td valign="top" >
				Daerah
				</td>
				<td valign="top" >
				:
				</td>
				<td id="div_DAERAH_ID$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG">
					<select id="ID_DAERAH_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG"  name="ID_DAERAH_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" 
					onChange="doDivAjaxCall$formname('div_BANDAR_ID$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG','showListBandar','ID_NEGERI='+$jquery('#ID_NEGERI_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG').val()+'&ID_DAERAH='+$jquery('#ID_DAERAH_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG').val());"
					>	  
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJDAERAH )		
							#set ( $selected_ruj = "" )
							#if($viewUnitHQ.ID_DAERAH==$ruj.ID)
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
				Bandar
				</td>
				<td valign="top" >
				:
				</td>
				<!--<td valign="top" >
				<input  size="50" type="text" id="BANDAR_$viewUnitHQ.ID_PEJABATJKPTG" 
				name="BANDAR_$viewUnitHQ.ID_PEJABATJKPTG" 
				value="$viewUnitHQ.BANDAR" >
				</td>-->
                <td id="div_BANDAR_ID$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG">
					<select id="ID_BANDAR_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG"  
					name="ID_BANDAR_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG"  >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJBANDAR )		
							#set ( $selected_ruj = "" )
							#if($viewUnitHQ.ID_BANDAR==$ruj.ID)
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
				<input  size="50" type="text" id="NO_TEL_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" 
				name="NO_TEL_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" style="text-transform:uppercase;"
				value="$viewUnitHQ.NO_TEL" >
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
				<input  size="50" type="text" id="NO_FAX_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" 
				name="NO_FAX_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" style="text-transform:uppercase;"
				value="$viewUnitHQ.NO_FAX" >
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
				<textarea row="5" col="30" name="CATATAN_$viewUnitHQ.ID_PEJABATJKPTG">$viewUnitHQ.CATATAN</textarea>
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
				<select id="FLAG_AKTIF_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG" name="FLAG_AKTIF_$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG">
				#set ( $selected_aktif = "" )
				#set ( $selected_tidakaktif = "" )
							#if($viewUnitHQ.FLAG_AKTIF == "AKTIF" || $viewUnitHQ.FLAG_AKTIF == "")
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
                
                <input type="button" id="cmdEditPejabat" name="cmdEditPejabat" value="Simpan" 
                onClick="doDivAjaxCall$formname('div_viewUnit$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG','saveUnitHQ','ID_SEKSYEN=$ID_SEKSYEN&ID_UNIT=$viewUnitHQ.ID_PEJABATJKPTG');" >
				
	   			<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" value="Tutup" 
	   			onClick="doDivAjaxCall$formname('div_viewUnit$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG','batalBahagian','ID_SEKSYEN=$ID_SEKSYEN');" >
	   			
	   		
		</table>
</fieldset>


	<br>
</td>		
</tr>