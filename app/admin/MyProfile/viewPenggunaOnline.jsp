

	<fieldset>
	<legend>
	PROFIL PENGGUNA : $viewPengguna.NAMA_PENUH 
	</legend>
	
	#if($SuccessMesej!="")
	<div class="info" id="div_Success$viewPengguna.USER_ID">
		#if($SuccessMesej=="Insert")
		
		Maklumat Pengguna Berjaya Didaftar
		
		#elseif($SuccessMesej=="Update")
		
		Maklumat Pengguna Berjaya Dikemaskini
		
		#end
	</div>
	<script >
	$jquery("#div_Success$viewPengguna.USER_ID").show().delay(3000).fadeOut();
	</script>
	#end
	<div id="printableArea_$viewPengguna.USER_ID">
		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			
			
			  <tr align="right">
                <td> 
                </td>
			</tr>
            <tr>
            <tr>
            <td></td>
             <td></td>
            <td></td>
            <td valign="top" align="right">				
				Tarikh Akhir Kemaskini
				: <strong>$viewPengguna.TARIKH_KEMASKINI</strong></td>
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
			#if($viewPengguna.KATEGORI!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kategori Pengguna				
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.KATEGORI				
				</td>
			</tr>
			#end
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
				$viewPengguna.NAMA_PENUH
				</td>
			</tr>
			
			#if($viewPengguna.NO_PENGENALAN!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				#if($viewPengguna.KATEGORI=="SYARIKAT")
					MyCOID
				#elseif($viewPengguna.KATEGORI=="INDIVIDU")
					MyID
				#else
					MyID
				#end				
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				#if($viewPengguna.KATEGORI=="SYARIKAT")
					$viewPengguna.NO_PENGENALAN
				#elseif($viewPengguna.KATEGORI=="INDIVIDU")
					$viewPengguna.NO_PENGENALAN
				#else
					$viewPengguna.NO_PENGENALAN1 - $viewPengguna.NO_PENGENALAN2 - $viewPengguna.NO_PENGENALAN3
				#end
				</td>
			</tr>
			#end
			
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
				ONLINE
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
				$viewPengguna.ALAMAT1
				</td>
			</tr>
			#if($viewPengguna.ALAMAT2 != "")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				$viewPengguna.ALAMAT2
				</td>
			</tr>
			#end
			#if($viewPengguna.ALAMAT3 != "")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				$viewPengguna.ALAMAT3
				</td>
			</tr>
			#end
			#if($viewPengguna.POSKOD!="")
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
				$viewPengguna.POSKOD
				</td>
			</tr>
			#end
			#if($viewPengguna.BANDAR != "")
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
				$viewPengguna.BANDAR
				</td>
			</tr>
			#end
			#if($viewPengguna.NEGERI!="")
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
				$viewPengguna.NEGERI
				</td>
			</tr>
			#end
			
			
			
			
			#if($viewPengguna.UMUR!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Umur			
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.UMUR
				</td>
			</tr>
			#end
			
			#if($viewPengguna.JANTINA_KETERANGAN!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jantina			
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.JANTINA_KETERANGAN
				</td>
			</tr>
			#end
			
			
			
			#if($viewPengguna.TARAF_PERKAHWINAN_KETERANGAN!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarif Perkahwinan			
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TARAF_PERKAHWINAN_KETERANGAN
				</td>
			</tr>
			#end
			
			
			#if($viewPengguna.TARIKH_LAHIR!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Lahir			
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TARIKH_LAHIR
				</td>
			</tr>
			#end
			
			#if($viewPengguna.NO_HP)
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No. HP
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NO_HP
				</td>
			</tr>
			#end
			#if($viewPengguna.NO_TEL)
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
				$viewPengguna.NO_TEL
				</td>
			</tr>
			#end
			#if($viewPengguna.NO_FAX!="")
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
				$viewPengguna.NO_FAX
				</td>
			</tr>
			#end
			
           #if ($USER_LOGIN_ROLE.equals("adminekptg") || $USER_LOGIN_ROLE.equals("adminstate"))
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
									<tr >
									<td></td><td></td>
									<td >
									<!--<input 
			type="button" id="EDITADDROLE$viewPengguna.USER_ID" 
			name="EDITADDROLE$viewPengguna.USER_ID" 
			onClick="doDivAjaxCall$formname('div_listaddrole$viewPengguna.USER_ID','edit_AddRole','USER_ID=$viewPengguna.USER_ID&internalType=&USER_LOGIN=$viewPengguna.USER_LOGIN');" 
			value="Kemaskini / Tambah Role" >  -->
									</td>
									</tr>					
								</table>
							</div>
							</td>
			</tr>
			<script>	
				document.getElementById("div_displayaddrole$viewPengguna.USER_ID").style.display="";	
			</script>
			#else
			<tr id="div_displayaddrole$viewPengguna.USER_ID" >
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
								<table width="100%" border="0" >
															
									<tr >
									<td >
									- &nbsp;
									<!--<input 
			type="button" id="EDITADDROLE$viewPengguna.USER_ID" 
			name="EDITADDROLE$viewPengguna.USER_ID" 
			onClick="doDivAjaxCall$formname('div_listaddrole$viewPengguna.USER_ID','edit_AddRole','USER_ID=$viewPengguna.USER_ID&internalType=&USER_LOGIN=$viewPengguna.USER_LOGIN');" 
			value="Kemaskini / Tambah Role" >  -->
									</td>
									</tr>					
								</table>
							</div>
							</td>
			</tr>
			#end
			#end
			
		<!--	<tr>
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
			</tr>-->
            
            <!--penambahan admin 23/1/2017-->
                <tr><td></td></tr>
                 <tr><td></td></tr>
                  <tr><td></td></tr>
           <!-- <tr>
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
			</tr>-->
			
           <!-- <tr>
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
			</tr>-->
            
            <!-- <tr>
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
			</tr>-->
            
          
			<!--tamat-->
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
             
				<input type="button" id="BTNEDIT$viewPengguna.USER_ID" name="BTNEDIT$viewPengguna.USER_ID"   onClick="doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','edit_PenggunaOnline','USER_ID=$viewPengguna.USER_ID&internalType=');" value="Kemaskini" > 
	   			
	   			</td>
			</tr>
		 #if ($USER_LOGIN_ROLE.equals("adminekptg") || $USER_LOGIN_ROLE.equals("adminstate"))
			<tr>
				<td valign="top" >		
				</td>	
				<td valign="top" colspan="3" ID="div_ListAT$viewPengguna.USER_ID" >				
				<script>   
				 $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_ListAT$viewPengguna.USER_ID','carianUtamaAT','&carianTerperinciAT=&TARIKH_MULA_AT=&TARIKH_AKHIR_AT=&USER_ID=$viewPengguna.USER_ID&internalType=');			 	  
				  });
				</script>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>	
				<td valign="top" colspan="3" ID="div_ListPLA$viewPengguna.USER_ID" >				
				
				</td>
			</tr>
            #end
		</table>
		</div>
	</fieldset>
	
	<br>

#if ($USER_LOGIN_ROLE.equals("online")) 
<script>
        var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
</script>

#end