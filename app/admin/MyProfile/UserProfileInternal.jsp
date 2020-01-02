<script>
if( $jquery('#'+'div_rowPengguna$viewPengguna.USER_ID').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPengguna$viewPengguna.USER_ID').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewPengguna$viewPengguna.USER_ID').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewPengguna$viewPengguna.USER_ID').offset().top);
	}
	
}
</script>

<tr id="div_viewPengguna$viewPengguna.USER_ID">
<td align="left" valign="top" colspan="14">

	<fieldset>
	<div id="printableArea_$viewPengguna.USER_ID">
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="50%" valign="top"><fieldset>
      <legend><strong>PROFIL PENGGUNA :  $viewPengguna.USER_NAME</strong></legend>
      <table width="100%" border="0">
    <tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="21%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="77%"><!-- $viewPengguna --></td>
		  </tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				ID Pengguna	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                $viewPengguna.USER_LOGIN
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.USER_NAME
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Emel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                $viewPengguna.EMEL
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jawatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.JAWATAN
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Agama
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.AGAMA
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Bangsa
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.BANGSA
				</td>
			</tr>
			<tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
			<tr><td></td></tr>
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
				$viewPengguna.NAMA_BAHAGIAN
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Unit / Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NAMA_PEJABAT
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.ALAMAT1_PEJ
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
				$viewPengguna.ALAMAT2_PEJ
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
				$viewPengguna.ALAMAT3_PEJ
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
				$viewPengguna.POSKOD_PEJ
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
				$viewPengguna.BANDAR_PEJ
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
				$viewPengguna.NEGERI_PEJ
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No. Tel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NO_TEL_PEJ
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No. Fax
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NO_FAX_PEJ
				</td>
			</tr>
            	<tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
			<tr><td></td></tr>
            <!--penambahan admin 201/1/2017-->
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Luput Kata laluan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.DAYS_AFTERCHANGEPASS
				</td>
			</tr>
			
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tempoh Sah
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TEMPOH_SAH Hari
                <br /> <font color="red">Nota : Tempoh SAH Kata Laluan adalah selama 3 BULAN sahaja.</font>
				</td>
			</tr>
            
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Log Masuk terakhir
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.LAST_LOGIN
				</td>
			</tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
			<tr><td></td></tr>
			<!--tamat-->
			#if($viewPengguna.ID_SEKSYEN == "2" )
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daerah Urusan / Jagaan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="div_displayDaerahJagaan" >
				<script>	
				  $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_displayDaerahJagaan','showDisplayDaerahJagaan','ID_PEJABATJKPTG=$viewPengguna.ID_PEJABATJKPTG&ID_JENISPEJABAT=22');			 	  
				  });		
				</script>
				</td>
			</tr>
			#end
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Peranan Utama di dalam Sistem
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.ROLE_MAIN_DETAILS
				</td>
			</tr>
             #if ($USER_LOGIN_ROLE.equals("adminekptg") || $USER_LOGIN_ROLE.equals("adminstate")) 	
            #if($listAdditionalRoleByUserLogin.size()>0)
			<tr id="div_displayaddrole$viewPengguna.USER_ID">
							<td valign="top" >				
							</td>			
							<td valign="top" >
							Peranan Tambahan (Pilihan)
							</td>
							<td valign="top" >
							:
							</td>
							<td>
							<div id="div_listaddrole$viewPengguna.USER_ID">
							<table width="100%" border="0"  >
								<tr>
											<td align="right" width="1%"  valign="top" >
											</td>
											<td align="center"  width="1%"  valign="top" ></td>
											<td align="left"  width="94%"  valign="top" ></td>
											</tr>
									#foreach($ar in $listAdditionalRoleByUserLogin)
									
									#if($ar.LAYER == "1")
									
											<tr>
											<td colspan="3"><b>$ar.KOD</b></td>
											</tr>
									#end
									#if($ar.LAYER == "2")
									
											<tr>
											<td align="right"   valign="top" >
											$ar.NEWBIL.
											</td>
											<td align="center"   valign="top" ></td>
											<td align="left"   valign="top" >$ar.KETERANGAN</td>
											</tr>
									#end
									
									
											
									#end

                                    </table>
                                    </div>
                            </td></tr>				
			#end
            #end
            <tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" colspan="14">
				<input type="button" id="BTNEDIT$viewPengguna.USER_ID" name="BTNEDIT$viewPengguna.USER_ID"   onClick="doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','edit_PenggunaInternal','USER_ID=$viewPengguna.USER_ID&internalType=');" value="Kemaskini" > 
	   			</td>
	   			</tr>
	   			
	 #if ($USER_LOGIN_ROLE.equals("adminekptg") || $USER_LOGIN_ROLE.equals("adminstate")) 	
	  <tr>
				<td valign="top" >				
				</td>	
				<td valign="top" colspan="3" ID="div_ListAT$viewPengguna.USER_ID" >				
				<script>   
				 $jquery(document).ready(function () {
					 doDivAjaxCall$formname('div_ListAT$viewPengguna.USER_ID','carianUtamaAT','carianTerperinciAT=&TARIKH_MULA_AT=&TARIKH_AKHIR_AT=&USER_ID=$viewPengguna.USER_ID&internalType=');			 	  
				  });
				</script>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>	
				<td valign="top" colspan="3" ID="div_ListPLA$viewPengguna.USER_ID" >	
                			
				<script>   
				 $jquery(document).ready(function () {
					 doDivAjaxCall$formname('div_ListPLA$viewPengguna.USER_ID','carianUtamaPLA','carianTerperinciPLA_=&TARIKH_MULA_PLA_=&TARIKH_AKHIR_PLA_=&USER_ID=$viewPengguna.USER_ID&internalType=');			 	  
				  });
				</script>
				</td>
			</tr>
		#end	
		</table>
    
	</fieldset>
    </td></tr></table>
	</div>
    </fieldset>
	<br>
</td>		
</tr>
