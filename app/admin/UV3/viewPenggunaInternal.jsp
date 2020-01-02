

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


#if($viewPengguna.USER_ID!="")
<tr id="div_viewPengguna$internalType$viewPengguna.USER_ID">
<td align="left" valign="top" colspan="14">

	<fieldset>
	<legend>
	<!-- Maklumat '$viewPengguna.USER_NAME' --> $viewPengguna.USER_NAME
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
			#if($viewPengguna.AGAMA!="")
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
			#end
			#if($viewPengguna.BANGSA!="")
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
			#end
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
			#if($viewPengguna.ALAMAT2_PEJ != "")
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
			#end
			#if($viewPengguna.ALAMAT3_PEJ != "")
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
			#end
			#if($viewPengguna.POSKOD_PEJ!="")
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
			#end
			#if($viewPengguna.BANDAR_PEJ != "")
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
			#end
			#if($viewPengguna.NEGERI_PEJ!="")
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
			#end
			#if($viewPengguna.NO_TEL_PEJ)
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
			#end
			#if($viewPengguna.NO_FAX_PEJ!="")
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
			#end
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
				<td valign="top" id="div_displayDaerahJagaan$internalType$viewPengguna.USER_ID" >
				<script>	
				  $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_displayDaerahJagaan$internalType$viewPengguna.USER_ID','showDisplayDaerahJagaan','ID_PEJABATJKPTG=$viewPengguna.ID_PEJABATJKPTG&ID_JENISPEJABAT=22');			 	  
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
				$viewPengguna.JENIS_PENGGUNA
				</td>
			</tr>
            
                      
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Status Jawatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($viewPengguna.FLAG_JAWATAN=="1" || $viewPengguna.FLAG_JAWATAN=="")
				TETAP		
                #else
                SEMENTARA
                #end
				
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tempoh Mula Berkhidmat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >$viewPengguna.TARIKH_MULA_TEMPOH
				</td>
			</tr>
            
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tempoh Akhir Berkhidmat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TARIKH_TAMAT_TEMPOH
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
            
           <!-- TAMBAH WAKTU KERJA-->
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Waktu Kerja
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($viewPengguna.WAKTU_KERJA=="WP1")
				WP1
				#elseif($viewPengguna.WAKTU_KERJA=="WP2")
				WP2
				#elseif($viewPengguna.WAKTU_KERJA=="WP3")
				WP3
                #else
                TIADA REKOD
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
				<input type="button" id="BTNEDIT$internalType$viewPengguna.USER_ID" name="BTNEDIT$internalType$viewPengguna.USER_ID"   onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','edit_PenggunaInternal','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Kemaskini" > 
	   			<input type="button" id="BTNCLOSE$internalType$viewPengguna.USER_ID" name="BTNCLOSE$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','close_PenggunaInternal','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Tutup" > 
	   			
	   			<input type="button" id="BTNPRINT$internalType$viewPengguna.USER_ID" name="BTNPRINT$internalType$viewPengguna.USER_ID" onclick="printDiv('printableArea_$internalType$viewPengguna.USER_ID','$internalType','$viewPengguna.USER_ID')" value="Cetak" />
                
            <!--    <input type="button" id="BTNPRINT$internalType$viewPengguna.USER_ID" name="BTNPRINT$internalType$viewPengguna.USER_ID" onclick="doDivAjaxCall$formname('printableArea2_$internalType$viewPengguna.USER_ID','cetakMaklumatPengguna','USER_LOGIN=$viewPengguna.USER_LOGIN&USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Cetak" />-->
	   			
	   			
	   			
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
           <tr>
				<td valign="top" >	
				</td>	
				<td valign="top" colspan="3" id="Senarai_Doc$viewPengguna.USER_ID" >

                 <script>
			$jquery(document).ready(function () {
					  doDivAjaxCall$formname('Senarai_Doc$viewPengguna.USER_ID','showDokumen','USER_ID=$viewPengguna.USER_ID&internalType=$internalType&FLAG_DELETE=');			 	  
				  });
			</script>
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
			
			
			
			#else
			
			#if($viewPengguna.ID_PERMOHONAN != "")
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" colspan="3" class="underline_td_tajuk" >
				<b>STATUS PERMOHONAN ID PENGGUNA</b>
				
				</td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Tarikh Permohonan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TARIKH_PERMOHONAN_PIP
				</td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				MyID
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NOKP1_PIP - $viewPengguna.NOKP2_PIP - $viewPengguna.NOKP3_PIP
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
				$viewPengguna.USER_NAME_PIP
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
				$viewPengguna.JAWATAN_PIP
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
				$viewPengguna.NEGERI_PIP
				</td>
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
				$viewPengguna.BAHAGIAN_PIP
				</td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Unit
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.UNIT_PIP
				</td>
			</tr>
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Penyelia
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.PEGAWAIPENYELIA			
				</td>
			</tr>
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Status Semasa Permohonan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($viewPengguna.FLAG_STATUS=="1")
				PERMOHONAN BARU
				#elseif($viewPengguna.FLAG_STATUS=="2")
				DITOLAK
				#elseif($viewPengguna.FLAG_STATUS=="3")
				DITERIMA
				#end		
				</td>
			</tr>
			#if($viewPengguna.PENYEMAK!="")
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Pengawai Pendaftar
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.PENYEMAK			
				</td>
			</tr>
			#end
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				#if($viewPengguna.FLAG_STATUS=="3")
					Tarikh Pendaftaran ID Pengguna
				#else
					Tarikh Keputusan
				#end
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TARIKH_KEPUTUSAN_PIP
				</td>
			</tr>
			#if($viewPengguna.CATATAN_PIP!="")
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.CATATAN_PIP		
				</td>
			</tr>
			#end
			
			#if($viewPengguna.NAMA_DOC!="")
			<tr>
			<td valign="top" align="center" >
			
			</td>
			<td valign="top" align="left" >
			Lampiran
			</td>
			<td valign="top"  >
			:
			</td>
			<td valign="top" align="left" >
			
			<div id="displayDocPIP_$internalType$viewPengguna.USER_ID">
			<script> 	
			  $jquery(document).ready(function () {
				  doDivAjaxCall$formname('displayDocPIP_$internalType$viewPengguna.USER_ID','showDocPIP','ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN&internalType=$internalType&USER_ID=$viewPengguna.USER_ID');			 	  
			  });		
			</script>
			</div>
			
			<span id="displayDocPIP_$internalType$viewPengguna.USER_ID"></span>
			</td>
			</tr>
			#end
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" colspan="3" class="underline_td_tajuk" >
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
				<input type="button" id="BTNCLOSE$internalType$viewPengguna.USER_ID" name="BTNCLOSE$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','close_PenggunaInternal','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Tutup" > 
	   			</td>
			</tr>
			#end
			#end
			
		</table>
		</div>
	</fieldset>
	
	<br>
</td>		
</tr>
