<table border="0" cellpadding="0" cellspacing="0" width="100%"> 
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Modul & Bahagian
</legend>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" >

<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewBahagianHQ --></td>
			</tr>
			           <tr>
				<td valign="top" >	
				<font color="red" >*</font>			
				</td>			
				<td valign="top" >
				Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="ID_SEKSYEN"  
					name="ID_SEKSYEN" onChange="doDivAjaxCall$formname('td_modul','showSelection','id_bahagian='+this.value);" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJSEKSYEN )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_SEKSYEN==$ruj.ID)
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
            <td>
           <!-- <input type="text" id="ID_SEKSYEN" 
				name="ID_SEKSYEN" 
				value="$viewBahagianHQ.ID_SEKSYEN" >-->
        
            </td></tr>

			<tr>
				<td valign="top" ><font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Modul
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="td_modul"  >
				
                   	
                    </td>
                    </tr>
                    
                    <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				
				</td>
				<td valign="top" ></td>
				<td valign="top" ><br />
			<input type="button" id="SAVEADDDJ$ID_PEJABAT" name="SAVEADDDJ$ID_PEJABAT" onClick="doDivAjaxCall$formname('divSenarai','simpan','ID_MODULBAHAGIAN=');" value="Simpan" > 
            <input type="button" id="BTLADDROLE$internalType$ID_PEJABAT" name="BTLADDROLE$internalType$ID_PEJABAT" 
onClick="doDivAjaxCall$formname('div_listaddDaerahJagaan$ID_PEJABAT','editAddDaerahJagaan','ID_PEJABAT=$ID_PEJABAT&ID_NEGERI=$viewPejabat.ID_NEGERI&JENISPEJ=$viewPejabat.JENIS_PEJ');" 
value="Batal" > 
	<input type="button" id="CLSADDROLE$ID_PEJABAT" name="CLSADDROLE$ID_PEJABAT" 
onClick="doDivAjaxCall$formname('div_displayaddDaerahJagaan$ID_PEJABAT','showDisplayDaerahJagaan','ID_PEJABAT=$ID_PEJABAT&ID_NEGERI=$viewPejabat.ID_NEGERI&JENISPEJ=$viewPejabat.JENIS_PEJ');"  
value="Tutup" >
                    </td>
                    </tr>

</table>

</fieldset>

</td>
</tr>

</table>

<br />

<div id="div_table" >
TEST
</div>

</br>

<div id="divSenarai">
<table border="0" cellpadding="0" cellspacing="0" width="100%"> 
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Senarai Modul mengikut Bahagian
</legend>

<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listRole.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >

		   </td>
		     
	</tr>
	#end 

	<tr class="table_header" >
		   <td   align="center" valign="top" width="3%">Bil.</td>
		   <td   align="left" valign="top"  width="45%">Bahagian</td>
		   <td   align="left" valign="top"  width="32%"><div align="center">Jumlah Modul</div></td>  
	</tr>
	#if($listModulBhgn.size()>0)
	
	
		#foreach($lm in $listModulBhgn)
		<tr id="div_rowRole$GROUPUNIK$lm.ROLEUNIK">
			   <td class="$lm.rowCss"  align="center" valign="top" >$lm.BIL </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
               $lm.NAMA_SEKSYEN
		    </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			     <div align="center">$lm.JUMLAH
		       </div></td>
		
		</tr>
		<tr  >
		<td align="left" valign="top" colspan="5" id="div_viewRole$GROUPUNIK$lm.ROLEUNIK">
		
		</td>		
		</tr>
		#end
	#else
		
		<tr >
			   <td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
			     
		</tr>
		
	#end
	</table>

</fieldset>

</td>
</tr>

</table>
</div>

<script>
$jquery(document).ready(function () {
	
		  doDivAjaxCall$formname('div_table','showTable','');			 	  
	  });
</script>