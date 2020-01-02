


<script>
if( $jquery('#'+'div_rowPengguna$internalType$viewPengguna.USER_ID').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPengguna$internalType$viewPengguna.USER_ID').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewPengguna$internalType$viewPengguna.USER_ID').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewPengguna$internalType$viewPengguna.USER_ID').offset().top);
	}
	
}
</script>

<tr id="div_viewPengguna$internalType$viewPengguna.USER_ID">
<td align="left" valign="top" colspan="14">

	<fieldset>
	<legend>
	<!-- Maklumat '$viewPengguna.USER_NAME' -->PROFIL PENGGUNA :   $viewPengguna.USER_NAME
	</legend>
	
	#if($SuccessMesej!="")
	<div class="info" id="div_Success$internalType$viewPengguna.USER_ID">
		#if($SuccessMesej=="Insert")
		
		Maklumat Pengguna Berjaya Didaftar
		
		#elseif($SuccessMesej=="Update")
		
		Maklumat Pengguna Berjaya Dikemaskini
		
		#end
	</div>
	<script >
	$jquery("#div_Success$internalType$viewPengguna.USER_ID").show().delay(3000).fadeOut();
	</script>
	#end
	<div id="printableArea_$internalType$viewPengguna.USER_ID">
	<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
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
                <!--penambahan admin 23/1/2017-->
                <tr><td></td></tr>
                 <tr><td></td></tr>
                  <tr><td></td></tr>
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
				$viewPengguna.DATE_DAYS_AFTERCHANGEPASS
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
                <br /> <font color="red">Nota : Tempoh SAH Kata Laluan adalah selama 3 BULAN sahaja. Auto reset apabila Kata Laluan dikemaskini.</font>
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
				$viewPengguna.WAKTU_AKHIR_LOGIN
				</td>
			</tr>
			<!--tamat-->
			
			#if($viewPengguna.EMEL!="")
			
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
			#end
			
			#if($viewPengguna.JAWATAN!="")
			
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
			
			
			#end
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jenis Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.JENIS_PEJABAT
				</td>
			</tr>
			
			#if($viewPengguna.ID_JENISPEJABAT!="16111")
			
			
			#if($viewPengguna.NEGERI_UI!="")
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
				$viewPengguna.NEGERI_UI
				</td>
			</tr>
			#end
			#if($viewPengguna.DAERAH_UI!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daerah
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.DAERAH_UI
				</td>
			</tr>
			#end
			#if($viewPengguna.NAMA_PEJABAT!="")
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
				$viewPengguna.NAMA_PEJABAT
				</td>
			</tr>
			#end
			<tr id="div_ALAMAT_PEJABAT_$internalType$viewPengguna.USER_ID" style="display:none">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
				#if($viewPejabat.size() > 0)
					#if($viewPejabat.ALAMAT1 != "")
					$viewPejabat.ALAMAT1<br>
					#end
					#if($viewPejabat.ALAMAT2 != "")
						$viewPejabat.ALAMAT2<br>
					#end
					#if($viewPejabat.ALAMAT3 != "")
						$viewPejabat.ALAMAT3<br>
					#end
					#if($viewPejabat.POSKOD != "")
						$viewPejabat.POSKOD &nbsp;						
					#end
					#if($viewPejabat.BANDAR != "")
						$viewPejabat.BANDAR<br>
					#end
					#if($viewPejabat.NEGERI != "")
						$viewPejabat.NEGERI<br>
					#end
					
					#if($viewPejabat.NO_TEL != "")
						No. Tel : $viewPejabat.NO_TEL<br>
					#end
					#if($viewPejabat.NO_FAX != "")
						No. Fax : $viewPejabat.NO_FAX<br>
					#end
					
					<script type="text/javascript">
					document.getElementById("div_ALAMAT_PEJABAT_$internalType$viewPengguna.USER_ID").style.display="";
					</script>
					
				#end
				</td>
			</tr>
			
			
			
			
			
			<tr id="div_displayDaerahJagaan$internalType$viewPengguna.USER_ID" style="display:none">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daerah Urusan / Jagaan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  >
				<script>
					
				  document.getElementById("div_displayDaerahJagaan$internalType$viewPengguna.USER_ID").style.display="";
				  $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_displayDaerahJagaan$internalType$viewPengguna.USER_ID','showDisplayDaerahJagaan','ID_PEJABATJKPTG=$viewPengguna.ID_PEJABAT&ID_JENISPEJABAT=3');			 	  
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
				$viewPengguna.NAMA_ROLE
				</td>
			</tr>
			
			 #if ($USER_LOGIN_ROLE.equals("adminekptg") || $USER_LOGIN_ROLE.equals("adminstate")) 	
			#if($listAdditionalRoleByUserLogin.size()>0)
			<tr id="div_displayaddrole$internalType$viewPengguna.USER_ID">
							<td valign="top" >				
							</td>			
							<td valign="top" >
							Peranan Tambahan (Pilihan)
							</td>
							<td valign="top" >
							:
							</td>
							<td>
							<div id="div_listaddrole$internalType$viewPengguna.USER_ID">
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
									<tr >
									<td></td><td></td>
									<td >
									<!--<input 
			type="button" id="EDITADDROLE$internalType$viewPengguna.USER_ID" 
			name="EDITADDROLE$internalType$viewPengguna.USER_ID" 
			onClick="doDivAjaxCall$formname('div_listaddrole$internalType$viewPengguna.USER_ID','edit_AddRole','USER_ID=$viewPengguna.USER_ID&internalType=$internalType&USER_LOGIN=$viewPengguna.USER_LOGIN');" 
			value="Kemaskini / Tambah Role" >  -->
									</td>
									</tr>					
								</table>
							</div>
							</td>
			</tr>
			<script>	
				document.getElementById("div_displayaddrole$internalType$viewPengguna.USER_ID").style.display="";	
			</script>
			#else
			<tr id="div_displayaddrole$internalType$viewPengguna.USER_ID" >
			<td valign="top" >				
							</td>			
							<td valign="top" >
							Peranan Tambahan (Pilihan)
							</td>
							<td valign="top" >
							:
							</td>
							<td>
							<div id="div_listaddrole$internalType$viewPengguna.USER_ID">
								<table width="100%" border="0" >
															
									<tr >
									<td >
									- &nbsp;
									<!--<input 
			type="button" id="EDITADDROLE$internalType$viewPengguna.USER_ID" 
			name="EDITADDROLE$internalType$viewPengguna.USER_ID" 
			onClick="doDivAjaxCall$formname('div_listaddrole$internalType$viewPengguna.USER_ID','edit_AddRole','USER_ID=$viewPengguna.USER_ID&internalType=$internalType&USER_LOGIN=$viewPengguna.USER_LOGIN');" 
			value="Kemaskini / Tambah Role" >  -->
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
				Jenis Pengguna
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				INTEGRASI
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Keaktifan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($viewPengguna.FLAG_AKTIF=="2")
				TIDAK AKTIF
				#else
				AKTIF
				#end
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
				<input type="button" id="BTNEDIT$internalType$viewPengguna.USER_ID" name="BTNEDIT$internalType$viewPengguna.USER_ID"   onClick="doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','edit_PenggunaINT','USER_ID=$viewPengguna.USER_ID&internalType=');" value="Kemaskini" > 
	   			<!--<input type="button" id="BTNCLOSE$internalType$viewPengguna.USER_ID" name="BTNCLOSE$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','close_PenggunaINT','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Tutup" > -->
	   			<!--<input type="button" id="BTNPRINT$internalType$viewPengguna.USER_ID" name="BTNPRINT$internalType$viewPengguna.USER_ID" onclick="printDiv('printableArea_$internalType$viewPengguna.USER_ID','$internalType','$viewPengguna.USER_ID')" value="Cetak" />-->
	   			<!--<span id="show_cb_AT_$internalType$viewPengguna.USER_ID" style="display:none" >
	   			<br>
	   			<input type="checkbox" id="CB_AT_$internalType$viewPengguna.USER_ID" name="CB_AT_$internalType$viewPengguna.USER_ID"   
	   			value="Y" > : Cetakan Termasuk Senarai Audittrail	   			
	   			</span>
	   			
	   			<span id="show_cb_PLA_$internalType$viewPengguna.USER_ID" style="display:none" >
	   			<br>
	   			<input type="checkbox" id="CB_PLA_$internalType$viewPengguna.USER_ID" name="CB_PLA_$internalType$viewPengguna.USER_ID"   
	   			value="Y" > : Cetakan Termasuk Senarai Pengurusan Log Aduan
	   			</span>-->
	   			</td>
			</tr>
		 #if ($USER_LOGIN_ROLE.equals("admin") || $USER_LOGIN_ROLE.equals("adminstate")) 	
			<tr>
				<td valign="top" >				
				</td>	
				<td valign="top" colspan="3" ID="div_ListAT$internalType$viewPengguna.USER_ID" >				
				<script>   
				 $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_ListAT$internalType$viewPengguna.USER_ID','carianUtamaAT','&carianTerperinciAT=&TARIKH_MULA_AT=&TARIKH_AKHIR_AT=&USER_ID=$viewPengguna.USER_ID&internalType=$internalType');			 	  
				  });
				</script>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>	
				<td valign="top" colspan="3" ID="div_ListPLA$internalType$viewPengguna.USER_ID" >				
				
				</td>
			</tr>
            #end
		</table>
		</div>
	</fieldset>
	
	<br>
</td>		
</tr>
