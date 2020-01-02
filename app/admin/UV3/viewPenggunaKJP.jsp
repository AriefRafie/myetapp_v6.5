


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
	<!-- Maklumat '$viewPengguna.USER_NAME' --> $viewPengguna.NAMA_PENUH
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
				$viewPengguna.NAMA_PENUH
				</td>
			</tr>
			
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
				
				#if($current_role_kjp=="online_kjp")
				KEMENTERIAN
				#elseif($current_role_kjp=="online_kjpagensi")
				AGENSI
				#end		
			
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kementerian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NAMA_KEMENTERIAN
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Agensi
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NAMA_AGENSI
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
			#if($viewPengguna.NEGERI_KEMENTERIAN!="")
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
				$viewPengguna.NEGERI_KEMENTERIAN
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
			#if($viewPengguna.NAMA_JAWATAN!="")
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tugasan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NAMA_JAWATAN
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
									<input 
			type="button" id="EDITADDROLE$internalType$viewPengguna.USER_ID" 
			name="EDITADDROLE$internalType$viewPengguna.USER_ID" 
			onClick="doDivAjaxCall$formname('div_listaddrole$internalType$viewPengguna.USER_ID','edit_AddRole','USER_ID=$viewPengguna.USER_ID&internalType=$internalType&USER_LOGIN=$viewPengguna.USER_LOGIN');" 
			value="Kemaskini / Tambah Role" >  
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
									<input 
			type="button" id="EDITADDROLE$internalType$viewPengguna.USER_ID" 
			name="EDITADDROLE$internalType$viewPengguna.USER_ID" 
			onClick="doDivAjaxCall$formname('div_listaddrole$internalType$viewPengguna.USER_ID','edit_AddRole','USER_ID=$viewPengguna.USER_ID&internalType=$internalType&USER_LOGIN=$viewPengguna.USER_LOGIN');" 
			value="Kemaskini / Tambah Role" >  
									</td>
									</tr>					
								</table>
							</div>
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
				ONLINE KJP
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
				<input type="button" id="BTNEDIT$internalType$viewPengguna.USER_ID" name="BTNEDIT$internalType$viewPengguna.USER_ID"   onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','edit_PenggunaKJP','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Kemaskini" > 
	   			<input type="button" id="BTNCLOSE$internalType$viewPengguna.USER_ID" name="BTNCLOSE$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','close_PenggunaKJP','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Tutup" > 
	   			<!--<input type="button" id="BTNPRINT$internalType$viewPengguna.USER_ID" name="BTNPRINT$internalType$viewPengguna.USER_ID" onclick="printDiv('printableArea_$internalType$viewPengguna.USER_ID','$internalType','$viewPengguna.USER_ID')" value="Cetak" />-->
                
                <input type="button" id="BTNPRINT$internalType$viewPengguna.USER_ID" name="BTNPRINT$internalType$viewPengguna.USER_ID" onclick="doDivAjaxCall$formname('printableArea2_$internalType$viewPengguna.USER_ID','cetakMaklumatPengguna','USER_LOGIN=$viewPengguna.USER_LOGIN&USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Cetak" />
	   			
	   			<span id="show_cb_AT_$internalType$viewPengguna.USER_ID" style="display:none" >
	   			<br>
	   			<input type="checkbox" id="CB_AT_$internalType$viewPengguna.USER_ID" name="CB_AT_$internalType$viewPengguna.USER_ID"   
	   			value="Y" > : Cetakan Termasuk Senarai Audittrail	   			
	   			</span>
	   			
	   			<span id="show_cb_PLA_$internalType$viewPengguna.USER_ID" style="display:none" >
	   			<br>
	   			<input type="checkbox" id="CB_PLA_$internalType$viewPengguna.USER_ID" name="CB_PLA_$internalType$viewPengguna.USER_ID"   
	   			value="Y" > : Cetakan Termasuk Senarai Pengurusan Log Aduan
	   			</span>
	   			
	   			<span id="show_cb_HISTORY_$internalType$viewPengguna.USER_ID" style="display:none" >
	   			<br>
	   			<input type="checkbox" id="CB_HISTORY_$internalType$viewPengguna.USER_ID" name="CB_HISTORY_$internalType$viewPengguna.USER_ID"   
	   			value="Y" > : Cetakan Termasuk Sejarah Maklumat Pengguna
	   			</span>
	   			
	   			</td>
			</tr>
            
             <div id="printableArea2_$internalType$viewPengguna.USER_ID" style="display:none">
         </div>
			
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
			<!-- open history -->
			<tr>
				<td valign="top" >			
				</td>	
				<td valign="top" colspan="3" ID="div_ListHISTORY$internalType$viewPengguna.USER_ID" >				
				</td>
			</tr>
			<!-- close history -->
		</table>
		</div>
	</fieldset>
	
	<br>
</td>		
</tr>
