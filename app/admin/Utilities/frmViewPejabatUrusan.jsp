<script>
if( $jquery('#'+'div_ViewURUSAN$viewPejabat.ID_PEJABAT').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_ViewURUSAN$viewPejabat.ID_PEJABAT').offset().top);
}
else
{
	
	if( $jquery('#'+'div_ViewURUSAN$viewPejabat.ID_PEJABAT').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_ViewURUSAN$viewPejabat.ID_PEJABAT').offset().top);
	}
	
}
</script>

<tr id="div_ViewURUSAN$viewPejabat.ID_PEJABAT$mode">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Pejabat Urusan</legend>
<div id="printableArea_$viewPejabat.ID_PEJABAT$mode">
<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%"></td>
			</tr>
			   
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPejabat.NAMA_PEJABAT
				</td>
			</tr>
            <tr>
            
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Pejabat Lain
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<!--$viewPejabat.NamaPejabatLain-->
				</td>
			</tr>
            
            <tr>
				<td valign="top" >		
				</td>			
				<td valign="top" >
				Pejabat 
				Urusan</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="">$viewPejabat.PEJ_URUSAN</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPejabat.NAMA_SEKSYEN
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
				$viewPejabat.ALAMAT1
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
				$viewPejabat.ALAMAT2
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
				$viewPejabat.ALAMAT3
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
				$viewPejabat.POSKOD
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
				$viewPejabat.BANDAR
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
				$viewPejabat.NAMA_NEGERI
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
				$viewPejabat.NO_TEL
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
				$viewPejabat.NO_FAX
				</td>
			</tr>

		#if ($viewPejabat.ID_JENISPEJABAT == 3)
 		#if($ListDaerahJagaanDisplay.size()>0)
		<tr id="div_displayaddDaerahJagaan$viewPejabat.ID_PEJABAT">
							<td valign="top" >				
							</td>			
							<td valign="top" >Daerah Jagaan </td>
							<td valign="top" >
							:
							</td>
							<td>
							<div id="div_listaddDaerahJagaan$viewPejabat.ID_PEJABAT">
							<table width="100%" border="0"  >
								<tr>
											<td align="right" width="1%"  valign="top" >
											</td>
											<td align="center"  width="1%"  valign="top" ></td>
											<td align="left"  width="94%"  valign="top" ></td>
											</tr>
											
									#foreach($daerahJagaan in $ListDaerahJagaanDisplay)
									
									<tr>
									<td align="right"   valign="top" >$daerahJagaan.BIL. </td>
									<td align="center"   valign="top" ></td>
									<td align="left"   valign="top" >$daerahJagaan.KETERANGAN</td>
											</tr>
									#end	
									<tr >
									<td></td><td></td>
									<td >
									<input 
			type="button" id="cmdBttnDaerahJagaan$viewPejabat.ID_PEJABAT" 
			name="cmdBttnDaerahJagaan$viewPejabat.ID_PEJABAT" 
			onClick="doDivAjaxCall$formname('div_listaddDaerahJagaan$viewPejabat.ID_PEJABAT','editAddDaerahJagaan','ID_PEJABAT=$viewPejabat.ID_PEJABAT&ID_NEGERI=$viewPejabat.ID_NEGERI&JENISPEJ=$viewPejabat.JENIS_PEJ');" 
			value="Kemaskini / Tambah Daerah Jagaan" >   
									</td>
									</tr>					
								</table>
							</div>
							</td>
			</tr> 
			<script>	
			document.getElementById("div_displayaddDaerahJagaan$viewPejabat.ID_PEJABAT").style.display="";	
			</script>
			#else
			<tr id="div_displayaddDaerahJagaan$viewPejabat.ID_PEJABAT" >
			<td valign="top" >				
							</td>			
							<td valign="top" >
							Daerah Jagaan
							</td>
							<td valign="top" >
							:
							</td>
							<td>
							<div id="div_listaddDaerahJagaan$viewPejabat.ID_PEJABAT">
								<table width="100%" border="0" >
															
									<tr >
									<td >
									- &nbsp;
									<input 
			type="button" id="cmdBttnDaerahJagaan$viewPejabat.ID_PEJABAT" 
			name="cmdBttnDaerahJagaan$viewPejabat.ID_PEJABAT" 
			onclick="doDivAjaxCall$formname('div_listaddDaerahJagaan$viewPejabat.ID_PEJABAT','editAddDaerahJagaan','ID_PEJABAT=$viewPejabat.ID_PEJABAT&ID_NEGERI=$viewPejabat.ID_NEGERI&JENISPEJ=$viewPejabat.JENIS_PEJ');" 
			value="Kemaskini / Tambah Daerah Jagaan" />
			</td>
									</tr>					
								</table>
							</div>
							</td>
			</tr>
			#end
            #end
    
    
     		<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Terlibat dalam Modul Integrasi
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if ($viewPejabat.FLAG_INT == '1') YA #else TIDAK #end
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
				$viewPejabat.FLAG_AKTIF
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
                 #if ($viewPejabat.ID_PEJABAT!= "")
                <input type="button" id="cmdEditPengguna" name="cmdEditPengguna" value="Kemaskini" onClick="doDivAjaxCall$formname('div_ViewURUSAN$viewPejabat.ID_PEJABAT$mode','editPejabat','ID_PEJABAT=$viewPejabat.ID_PEJABAT&ID_NEGERI=$viewPejabat.ID_NEGERI&JENISPEJ=$JENISPEJ&mode=$mode');" >
                #end
				<!--<input type="button" id="BTNEDIT$internalType$viewPengguna.USER_ID" name="BTNEDIT$internalType$viewPengguna.USER_ID"   onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','edit_PenggunaInternal','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Kemaskini" >--> 
                #if ($viewPejabat.ID_PEJABAT!= "")
	   			<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" value="Tutup" onClick="doDivAjaxCall$formname('div_ViewURUSAN$viewPejabat.ID_PEJABAT$mode','close_Pejabat','ID_PEJABAT=$viewPejabat.ID_PEJABAT');" >
	   		#else	
            #set ($viewPejabat.ID_PEJABAT = "")
	<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" onclick="doDivAjaxCall$formname('div_ViewURUSAN$viewPejabat.ID_PEJABAT$mode','close_Pejabat','ID_PEJABAT=$viewPejabat.ID_PEJABAT&mode=$mode');" value="Tutup" >
			#end
			
			<input type="button" id="BTNPRINT$viewPejabat.ID_PEJABAT$mode" name="BTNPRINT$viewPejabat.ID_PEJABAT$mode" onclick="printDiv('printableArea_$viewPejabat.ID_PEJABAT$mode','$viewPejabat.ID_PEJABAT')" value="Cetak" />
            
            <!-- <input type="button" id="BTNPRINTUSER$viewPejabat.ID_PEJABAT$mode" name="BTNPRINTUSER$viewPejabat.ID_PEJABAT$mode" onclick="doDivAjaxCall$formname('printUserArea_$viewPejabat.ID_PEJABAT$mode','cetakPengguna','ID_PEJABAT=$viewPejabat.ID_PEJABAT&mode=$mode');" value="Cetak Pengguna" />-->
             
            </td>
            </tr>
		</table>
		</div>
         <div id="printUserArea_$viewPejabat.ID_PEJABAT$mode" style="display:none">
        </div>
</fieldset>

	<br>
</td>		
</tr>

