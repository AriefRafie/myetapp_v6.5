<script>
if( $jquery('#'+'div_viewUnitHQ$viewUnitHQ.ID_PEJABATJKPTG').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_viewUnitHQ$viewUnitHQ.ID_PEJABATJKPTG').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewUnitHQ$viewUnitHQ.ID_PEJABATJKPTG').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewUnitHQ$viewUnitHQ.ID_PEJABATJKPTG').offset().top);
	}
	
}
</script>

<tr id="div_viewUnitHQ$viewUnitHQ.ID_PEJABATJKPTG">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Unit </legend>
<table width="100%" border="0">
			<tr colspan="14">
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			   
            <tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Unit/Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewUnitHQ.NAMA_UNIT
				</td>
			</tr>
            <tr>
            <td>
            <input type="hidden" id="ID_PEJABATJKPTG" 
				name="ID_PEJABATJKPTG" 
				value="$viewUnitHQ.ID_PEJABATJKPTG" >
        
            </td></tr>
            <tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >Kod Unit/Pejabat</td>
			  <td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewUnitHQ.KOD_JKPTG
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
				$viewUnitHQ.ALAMAT1
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
				$viewUnitHQ.ALAMAT2
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
				$viewUnitHQ.ALAMAT3
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
				$viewUnitHQ.POSKOD
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
				<td valign="top" >
				$viewUnitHQ.BANDAR
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
				$viewUnitHQ.NEGERI</td>
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
				$viewUnitHQ.NO_TEL
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
				$viewUnitHQ.NO_FAX
				</td>
			</tr> 
			
			 <!-- <tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<textarea row="5" col="10" readonly>$viewUnitHQ.CATATAN</textarea>
				</td>
			</tr>  -->
			
			
			<tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewUnitHQ.FLAG_AKTIF
				</td>
			</tr>
    		
			<tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
                
                <input type="button" id="cmdEditPengguna" name="cmdEditPengguna" value="Kemaskini" onClick="doDivAjaxCall$formname('div_viewUnit$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG','editUnit','ID_SEKSYEN=$viewUnitHQ.ID_SEKSYEN&ID_UNIT=$viewUnitHQ.ID_PEJABATJKPTG');" >
				
                <input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" value="Tutup" onClick="doDivAjaxCall$formname('div_viewUnit$ID_SEKSYEN$viewUnitHQ.ID_PEJABATJKPTG','batalBahagian','ID_SEKSYEN=$viewUnitHQ.ID_SEKSYEN');" >
	   		
			</td>
			</tr>
		</table>
</fieldset>

	<br>
</td>		
</tr>