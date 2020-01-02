<script>
if( $jquery('#'+'div_viewBahagianHQ$viewBahagianHQ.ID_SEKSYEN').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_viewBahagianHQ$viewBahagianHQ.ID_SEKSYEN').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewBahagianHQ$viewBahagianHQ.ID_SEKSYEN').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewBahagianHQ$viewBahagianHQ.ID_SEKSYEN').offset().top);
	}
	
}
</script>

<tr id="div_viewBahagianHQ$viewBahagianHQ.ID_SEKSYEN">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Bahagian</legend>
<table width="100%" border="0">
			<tr colspan="14">
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			   
            <tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.NAMA_SEKSYEN
				</td>
			</tr>
            <tr>
            <td>
           <!-- <input type="text" id="ID_SEKSYEN" 
				name="ID_SEKSYEN" 
				value="$viewBahagianHQ.ID_SEKSYEN" >-->
        
            </td></tr>
            <tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Lain Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.KOD_SEKSYEN
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
				$viewBahagianHQ.ALAMAT_1
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
				$viewBahagianHQ.ALAMAT_2
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
				$viewBahagianHQ.ALAMAT_3
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
				$viewBahagianHQ.POSKOD
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
				$viewBahagianHQ.BANDAR
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
				$viewBahagianHQ.NAMA_NEGERI
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
				$viewBahagianHQ.NO_TEL
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
				$viewBahagianHQ.NO_FAKS
				</td>
			</tr> 
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Emel</td>
				<td valign="top" >
				:</td>
				<td valign="top" >
				$viewBahagianHQ.EMEL
				</td>
			</tr> 
			
			<tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<textarea row="5" col="10" readonly>$viewBahagianHQ.CATATAN</textarea>
				</td>
			</tr>
			
			
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
				$viewBahagianHQ.FLAG_AKTIF
				</td>
			</tr>
            
            <tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Dikemaskini oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.USER_NAME
				</td>
			</tr>
            
            <tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Dikemaskini pada
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.TARIKH_KEMASKINI
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
                
                <input type="button" id="cmdEditPengguna" name="cmdEditPengguna" value="Kemaskini" onClick="doDivAjaxCall$formname('div_viewBahagian$viewBahagianHQ.ID_SEKSYEN','editBahagian','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');" >
				<!--<input type="button" id="BTNEDIT$internalType$viewPengguna.USER_ID" name="BTNEDIT$internalType$viewPengguna.USER_ID"   onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','edit_PenggunaInternal','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Kemaskini" >--> 
                #if ($viewBahagianHQ.ID_SEKSYEN!= "")
	   			<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" value="Tutup" onClick="doDivAjaxCall$formname('div_viewBahagian$viewBahagianHQ.ID_SEKSYEN','batalBahagian','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');" >
	   		#else	
	<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" onclick="doDivAjaxCall$formname('div_viewBahagian','batalBahagian','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');" value="Tutup" >
			#end
			<input type="button" id="cmdPrint" name="cmdPrint" onclick="doDivAjaxCall$formname('SenaraiForPrintDetail$viewBahagianHQ.ID_SEKSYEN','printDetailsBahagian','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');" value="Cetak" >
			<input  type="button" id="borangSemakUser" name="borangSemakUser" value="Borang Semakan Pengguna " 
onClick="doDivAjaxCall$formname('BorangSemakanPengguna$viewBahagianHQ.ID_SEKSYEN','cetakBorangSemakanPengguna','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');"  />

			</td>
			</tr>
            <br />
            <tr>
            <td colspan="14">
            <div id="Senarai_Doc$viewBahagianHQ.ID_SEKSYEN">
            <script>
			$jquery(document).ready(function () {
					  doDivAjaxCall$formname('Senarai_Doc$viewBahagianHQ.ID_SEKSYEN','showDokumen','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN&FLAG_DELETE=');			 	  
				  });
			</script>
            </div>
            </td>
            </tr>
            
			<tr id="div_ListMaklumatUnit$viewBahagianHQ.ID_SEKSYEN" >
				
				<td valign="top" colspan="14" >
				<script>   
				 $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_ListMaklumatUnit$viewBahagianHQ.ID_SEKSYEN','carianUnit','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');			 	  
				  });
				</script>
				</td>
			</tr>
			
			<div id="SenaraiForPrintDetail$viewBahagianHQ.ID_SEKSYEN" style="display:none">
			</div>
            <div id="BorangSemakanPengguna$viewBahagianHQ.ID_SEKSYEN" style="display:none">
			</div>
		</table>
</fieldset>

	<br>
</td>		
</tr>