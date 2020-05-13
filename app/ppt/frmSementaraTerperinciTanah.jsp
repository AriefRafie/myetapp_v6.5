
<!-- Checking id -->
#set($portal_role = "$!{session.getAttribute('_portal_role')}")

#if(($!id_pegawai=="") || ($!{session.getAttribute('_ekptg_user_id')} == $!id_pegawai) || ($portal_role == "(PPT)PengarahUnit" || $portal_role == "(PPT)PenolongPengarahUnit" || $portal_role == "adminppt"))
	#set($editable="yes")
	#set($disOtherId="")
	#set($disOtherId1="")
	#set($disOtherIdx="")
#else

	<!-- set($editable="no")
	set($disOtherId="readonly")
	set($disOtherId1="disabled")
	set($disOtherIdx="class=disabled") -->
	#set($editable="yes")
	#set($disOtherId="")
	#set($disOtherId1="")
	#set($disOtherIdx="")
#end



#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")


#foreach($data in $dataMaklumatTanahTerperinci)

	
	<!-- TAB 1 -->
	#if($onchange=="no")
		#set($txtLuasLotAsal=$data.luas_lot)
		#set($txtLuasLotAmbil=$data.luas_ambil)
		#set($sorDropdownUnitAsal=$data.id_unitluaslot_convert)
		#set($sorDropdownUnitAmbil=$data.id_unitluasambil_convert)
	
		#set($txtNoSyit=$data.no_syit)
		#set($txtNoHakmilik=$data.no_hakmilik)
		#set($txdTarikhLawatAkhir=$data.tarikh_akhir_lawat)
		#set($txdTarikhLawatMula=$data.tarikh_mula_lawat)	
		#set($txtLokasi=$data.lokasi_tanah)
		#set($txtJarak=$data.jarak_bandar)
		#set($txtNamaPBT=$data.nama_pbt)
		#set($txtStatusTanah=$data.status_tanah)
        #set($txtPendahuluan=$data.pendahuluan)
	
		#set($txtJalanUtama=$data.jalan_utama)
		#set($txtJalanMasuk=$data.jalan_masuk)
		#set($txtPerumahan=$data.perumahan)
		#set($txtIndustri=$data.industri)
		#set($txtNamaTempat=$data.nama_tempat)
	
		<!-- flag radio/checkbox -->
		#set($sorPBT=$data.flag_pbt)
		#set($sorRizab=$data.flag_rezab_melayu)
	#end
	
	<!-- TAB 2 -->
	#set($txtPerihalRupabumi=$data.rupabumi)
	#set($txtPerihalKeadaan=$data.keadaan_tanah)
	#set($txtHalangan=$data.halangan)
	#set($txtTanaman=$data.tanaman)
	<!-- flag radio/checkbox -->
	#set($flagLembah=$data.flag_lembah)
	#set($flagLurah=$data.flag_lurah)
	#set($flagBerpaya=$data.flag_paya)
	#set($flagRendah=$data.flag_rendah)
	#set($flagRata=$data.flag_rata)
	#set($flagLandai=$data.flag_landai)
	#set($flagBukit=$data.flag_bukit)		
	#set($flagUsaha=$data.flag_diusaha)
	#set($flagSemak=$data.flag_semak)
	#set($flagBelukar=$data.flag_belukar)
	#set($flagHutan=$data.flag_hutan)
	#set($flagTerbiar=$data.flag_terbiar)
	#set($flagLapang=$data.flag_lapang)
	
	<!-- TAB 3 -->
	#set($txtKemudahan=$data.kemudahan_awam)
	#set($txtSempadanUtara=$data.sempadan_utara)
	#set($txtSempadanSelatan=$data.sempadan_selatan)
	#set($txtSempadanTimur=$data.sempadan_timur)
	#set($txtSempadanBarat=$data.sempadan_barat)
	<!-- flag radio -->
	#set($sorSaliran=$data.flag_saliran)
	
	<!-- TAB 4 -->
	<!-- SO -->
	#set($socUnitHargaSO=$data.unit_harga_so)
	#set($txtHargaSeunitSO=$data.harga_seunit_so)
	#set($txtHargaPasaranSO=$data.harga_pasaran_so)
	#set($txtPenjejasanSO=$data.bayar_penjejasan)
	#set($txtPecahSO=$data.bayar_pecahpisah)
	#set($txtKenaikanSO=$data.bayar_naik_nilaiso)
	<!-- JPPH -->
	#set($socUnitHargaJP=$data.unit_harga)
	#set($txtHargaSeunitJP=$data.harga_seunit_jpph)
	#set($txtHargaPasaranJP=$data.harga_pasaran)
	#set($txtPenjejasanJP=$data.amaun_penjejasan_jpph)
	#set($txtPecahJP=$data.amaun_pecahpisah_jpph)
	#set($txtKenaikanJP=$data.naik_nilai_jpph)
	#set($txtHargaBorangG=$data.harga_seunit_akhir)
	#set($socUnitHargaG=$data.unit_harga_akhir)
	
	#set($txtJenisPisah=$data.pecah_pisah)
	
	<!-- SWASTA --><!-- PPT-42 -->
	#set($socUnitHargaNS=$data.unit_harga_ns)
	#set($txtHargaSeunitNS=$data.harga_seunit_ns)
	#set($txtHargaPasaranNS=$data.harga_pasaran_ns)
	#set($txtPenjejasanNS=$data.bayar_penjejasan_ns)
	#set($txtPecahNS=$data.bayar_pecah_ns)
	#set($txtKenaikanNS=$data.bayar_naik_nilai_ns)
	
	<!-- TAB 5 -->
	#set($txdTarikhUlasan=$data.tarikh_ulasan)
	#set($txtUlasanPegawai=$data.ulasan_pegawai)
	<!-- Nama Pegawai -->
	#set($txtPelapor=$data.user_name)
	<!-- set($txtPelapor=$data.nama_pelapor) -->
	
#end


	#if($isEdit=="no")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#set($disability1 = "disabled")
	#else
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
	#end

<fieldset id="top">

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>

	<fieldset id="middle">
	<legend>Laporan Tanah Terperinci Penggunaan/Pendudukan Sementara - Tanah</legend>
	
	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
	
	<div id="TabbedPanels1" class="TabbedPanels">
	
  		<ul class="TabbedPanelsTabGroup">
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);maklumatTanah('$!id_hakmilik')" tabindex="1">Maklumat Am</li>
   	 		<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);maklumatTanah('$!id_hakmilik')" tabindex="1">Perihal Tanah</li>
   	 		<li class="TabbedPanelsTab" onClick="javascript:setSelected(2);maklumatTanah('$!id_hakmilik')" tabindex="1">Pembangunan Sekitar</li>
   	 		<li class="TabbedPanelsTab" onClick="javascript:setSelected(3);maklumatTanah('$!id_hakmilik')" tabindex="1">Nilaian</li>
   	 		<li class="TabbedPanelsTab" onClick="javascript:setSelected(4);maklumatTanah('$!id_hakmilik')" tabindex="1">Ulasan Pegawai</li>
   	 		<li class="TabbedPanelsTab" onClick="javascript:setSelected(5);maklumatTanah('$!id_hakmilik')" tabindex="1">Dokumen</li>
  		</ul>
  		
  		<div class="TabbedPanelsContentGroup">
  			
  			
  			
<!-- START TAB 1 -->
    		<div class="TabbedPanelsContent">
   <br/> 		
    		<fieldset>
    		<table width="100%" border="0">   		
    		<tr>
    		
    		#if($mode=="new")
    		<!-- TABLE KIRI -->
    		<td width="53%" valign="top"><table width="100%" border="0">
    		
    			<tr>
    				<td width="1%"><font color="red">#if($editable=="yes")*#end</font></td>
    				<td width="28%">Jenis Hakmilik</td>
    				<td width="1%">:</td>
    				<td width="70%">$!selectJenisHakmilik</td>
    			</tr>
    			<tr>
    				<td><font color="red">#if($editable=="yes")*#end</font></td>
    				<td>No.Hakmilik</td>
    				<td>:</td>
    				<td><input $disOtherId $disOtherIdx type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="12" maxlength="50" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
    			</tr>
    			<tr>
    				<td><font color="red">#if($editable=="yes")*#end</font></td>
    				<td>No.Syit</td>
    				<td>:</td>
    				<td><input $disOtherId $disOtherIdx type="text" name="txtNoSyit" id="txtNoSyit" value="$!txtNoSyit" size="12" maxlength="40" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
    			</tr>
    			<tr>
    				<td><font color="red">#if($editable=="yes")*#end</font></td>
    				<td>Kategori</td>
    				<td>:</td>
    				<td>$!selectKategoriTanah</td>
    			</tr>
    			<tr>
					<td><font color="red">*</font></td>
					<td>Luas Asal</td>
					<td>:</td>
					<td><input type="text" $disOtherId $disOtherIdx name="txtLuasLotAsal" id="txtLuasLotAsal" size="15" value="$!txtLuasLotAsal" maxlength="20" onkeyup="validateNilai(this,this.value);" />
						
						<select $disOtherId1 $disOtherIdx name="sorDropdownUnitAsal" style="width:132px" onchange="onchangeUnitAsal()">
      		
      					#if($sorDropdownUnitAsal=="1")
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#elseif($sorDropdownUnitAsal=="2")
      					<option value="2">METER PERSEGI</option>
      					<option value="1">HEKTAR</option>
      					#else
      					<option value="">SILA PILIH</option>
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#end

						</select></td>
				</tr>
				<tr>
					<td><font color="red">*</font></td>
					<td>Luas Diambil</td>
					<td>:</td>
					<td><input type="text" $disOtherId $disOtherIdx name="txtLuasLotAmbil" id="txtLuasLotAmbil" size="15" value="$!txtLuasLotAmbil" maxlength="20" onkeyup="validateNilai(this,this.value);" />
					
						<select $disOtherId1 $disOtherIdx name="sorDropdownUnitAmbil" style="width:132px" onchange="onchangeUnitAmbil()">
      		
      					#if($sorDropdownUnitAmbil=="1")
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#elseif($sorDropdownUnitAmbil=="2")
      					<option value="2">METER PERSEGI</option>
      					<option value="1">HEKTAR</option>
      					#else
      					<option value="">SILA PILIH</option>
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#end

						</select></td>
				</tr>	
				
    		</table></td>
    		
    		
    		<!-- TABLE KANAN -->
    		<td width="47%" valign="top"><table width="100%" border="0">
    		
    			<tr>
    				<td width="1%">&nbsp;</td>
					<td width="37%">Tarikh Lawat Mula</td>
					<td width="1%">:</td>
					<td width="61%"><input $disOtherId $disOtherIdx name="txdTarikhLawatMula" value="$!txdTarikhLawatMula" size="11" id="txdTarikhLawatMula" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 		#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawatMula',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Lawat Akhir</td>
					<td>:</td>
					<td><input $disOtherId $disOtherIdx name="txdTarikhLawatAkhir" value="$!txdTarikhLawatAkhir" size="11" id="txdTarikhLawatAkhir" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 		#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawatAkhir',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
				
    		</table></td>
    		#end
    		
    		
    		#if($mode=="view")
    		<!-- TABLE KIRI -->
    		<td width="53%" valign="top"><table width="100%" border="0">
    		
    			<tr>
    				<td width="1%">#if($isEdit=="yes")<font color="red">*</font>#end</td>
    				<td width="28%">Jenis Hakmilik</td>
    				<td width="1%">:</td>
    				<td width="70%">$!selectJenisHakmilik</td>
    			</tr>
    			<tr>
    				<td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
    				<td>No.Hakmilik</td>
    				<td>:</td>
    				<td><input $disability $disabilityx type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="12" maxlength="50" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
    			</tr>
    			<tr>
    				<td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
    				<td>No.Syit</td>
    				<td>:</td>
    				<td><input $disability $disabilityx type="text" name="txtNoSyit" id="txtNoSyit" value="$!txtNoSyit" size="12" maxlength="40" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
    			</tr>
    			<tr>
    				<td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
    				<td>Kategori</td>
    				<td>:</td>
    				<td>$!selectKategoriTanah</td>
    			</tr>
    			<tr>
					<td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
					<td>Luas Asal</td>
					<td>:</td>
					<td><input type="text" $disability $disabilityx name="txtLuasLotAsal" id="txtLuasLotAsal" size="15" value="$!txtLuasLotAsal" maxlength="20" onkeyup="validateNilai(this,this.value);" />
						
						<select $disability1 $disabilityx name="sorDropdownUnitAsal" style="width:132px" onchange="onchangeUnitAsalUpdate()">
      		
      					#if($sorDropdownUnitAsal=="1")
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#elseif($sorDropdownUnitAsal=="2")
      					<option value="2">METER PERSEGI</option>
      					<option value="1">HEKTAR</option>
      					#else
      					<option value="">SILA PILIH</option>
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#end

						</select></td>
				</tr>
				<tr>
					<td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
					<td>Luas Diambil</td>
					<td>:</td>
					<td><input type="text" $disability $disabilityx name="txtLuasLotAmbil" id="txtLuasLotAmbil" size="15" value="$!txtLuasLotAmbil" maxlength="20" onkeyup="validateNilai(this,this.value);" />
					
						<select $disability1 $disabilityx name="sorDropdownUnitAmbil" style="width:132px" onchange="onchangeUnitAmbilUpdate()">
      		
      					#if($sorDropdownUnitAmbil=="1")
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#elseif($sorDropdownUnitAmbil=="2")
      					<option value="2">METER PERSEGI</option>
      					<option value="1">HEKTAR</option>
      					#else
      					<option value="">SILA PILIH</option>
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#end

						</select></td>
				</tr>
				
    		</table></td>
    		
    		
    		<!-- TABLE KANAN -->
    		<td width="47%" valign="top"><table width="100%" border="0">
    		
    			<tr>
    				<td width="1%">&nbsp;</td>
					<td width="37%">Tarikh Lawat Mula</td>
					<td width="1%">:</td>
					<td width="61%"><input $disability $disabilityx name="txdTarikhLawatMula" value="$!txdTarikhLawatMula" size="11" id="txdTarikhLawatMula" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 		#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawatMula',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Lawat Akhir</td>
					<td>:</td>
					<td><input $disability $disabilityx name="txdTarikhLawatAkhir" value="$!txdTarikhLawatAkhir" size="11" id="txdTarikhLawatAkhir" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 		#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawatAkhir',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
    		
    		</table></td>
    		#end
    		
    		</tr>    		
    		</table>
   			</fieldset>
   			
   			
   			<fieldset>
   			
   			#if($mode=="new")
 <!--   		<table width="100%" border="0">
    			<tr>
    				<td width="1%">&nbsp;</td>
					<td width="14%" valign="top">Status Tanah</td>
					<td width="1%" valign="top">:</td>
    				<td width="84%"><textarea $disOtherId $disOtherIdx name="txtStatusTanah" id="txtStatusTanah" cols="60%" rows="4" onKeyUp="textCounter(this.form.txtStatusTanah,this.form.remLen40,1500);" onKeyDown="textCounter(this.form.txtStatusTanah,this.form.remLen40,1500);" >$!txtStatusTanah</textarea></td>
    			</tr>
    		</table>-->
            
   			<table width="100%" border="0">
   				<tr>
   					<td width="53%" valign="top"><table width="100%" border="0">
   							<tr>
   								<td>&nbsp;</td>
   								<td>Pendahuluan</td>
   							</tr>
    						<tr>
    							<td>&nbsp;</td>
    							<td><textarea $disOtherId $disOtherIdx name="txtPendahuluan" id="txtPendahuluan" cols="53%" rows="5" onKeyUp="textCounter(this.form.txtPendahuluan,this.form.remLenPND,4000);" onKeyDown="textCounter(this.form.txtPendahuluan,this.form.remLenPND,4000);" >$!txtPendahuluan</textarea></td>
    						</tr>
    				</table></td>	
    				
    				<td width="47%" valign="top"><table width="100%" border="0">
    						<tr>
    							<td>&nbsp;</td>
   								<td>Status Tanah</td>
    						</tr>		
    						<tr>
    							<td>&nbsp;</td>
    							<td><textarea $disOtherId $disOtherIdx name="txtStatusTanah" id="txtStatusTanah" cols="45%" rows="5" onKeyUp="textCounter(this.form.txtStatusTanah,this.form.remLen40,1500);" onKeyDown="textCounter(this.form.txtStatusTanah,this.form.remLen40,1500);" >$!txtStatusTanah</textarea></td>
    						</tr>
    				</table></td>
    			</tr>			
    		</table>            
            
            
    		#end
    		
    		#if($mode=="view")
   			<table width="100%" border="0">
   				<tr>
   					<td width="53%" valign="top"><table width="100%" border="0">
   							<tr>
   								<td>&nbsp;</td>
   								<td>Pendahuluan</td>
   							</tr>
    						<tr>
    							<td>&nbsp;</td>
    							<td><textarea $disability $disabilityx name="txtPendahuluan" id="txtPendahuluan" cols="53%" rows="5" onKeyUp="textCounter(this.form.txtPendahuluan,this.form.remLenPND,4000);" onKeyDown="textCounter(this.form.txtPendahuluan,this.form.remLenPND,4000);" >$!txtPendahuluan</textarea></td>
    						</tr>
    				</table></td>	
    				
    				<td width="47%" valign="top"><table width="100%" border="0">
    						<tr>
    							<td>&nbsp;</td>
   								<td>Status Tanah</td>
    						</tr>		
    						<tr>
    							<td>&nbsp;</td>
    							<td><textarea $disability $disabilityx name="txtStatusTanah" id="txtStatusTanah" cols="45%" rows="5" onKeyUp="textCounter(this.form.txtStatusTanah,this.form.remLen40,1500);" onKeyDown="textCounter(this.form.txtStatusTanah,this.form.remLen40,1500);" >$!txtStatusTanah</textarea></td>
    						</tr>
    				</table></td>
    			</tr>			
    		</table>
    		#end
    		
   			</fieldset>
   
   <br/> 		
    		<fieldset>
    		<legend><strong>Lokasi / Kedudukan Tanah</strong></legend>
    		
    		#if($mode=="new")
    		<table width="100%" border="0">
    			
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="32%" valign="top">Lokasi</td>
    				<td width="1%" valign="top">:</td>
    				<td width="66%"><textarea $disOtherId $disOtherIdx name="txtLokasi" id="txtLokasi" cols="47%" rows="3" onKeyUp="textCounter(this.form.txtLokasi,this.form.remLenLoc,1500);" onKeyDown="textCounter(this.form.txtLokasi,this.form.remLenLoc,1500);" >$!txtLokasi</textarea></td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Jalanraya Utama</td>
    				<td>:</td>
    				<td><input $disOtherId $disOtherIdx type="text" name="txtJalanUtama" id="txtJalanUtama" value="$!txtJalanUtama" size="50" maxlength="100" ></td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Jalan Masuk</td>
    				<td>:</td>
    				<td><input $disOtherId $disOtherIdx type="text" name="txtJalanMasuk" id="txtJalanMasuk" value="$!txtJalanMasuk" size="50" maxlength="100" ></td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Nama Kampung/Pekan/Bandar</td>
    				<td>:</td>
    				<td><input $disOtherId $disOtherIdx type="text" name="txtNamaTempat" id="txtNamaTempat" value="$!txtNamaTempat" size="50" maxlength="100" ></td>
    			</tr>
    		
    			<tr>
    				<td>&nbsp;</td>
    				<td>Jarak Dari Kampung/Pekan/Bandar</td>
    				<td>:</td>
    				<td><input $disOtherId $disOtherIdx type="text" name="txtJarak" id="txtJarak" value="$!txtJarak" size="30" maxlength="400" ></td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Perumahan Terdekat</td>
    				<td>:</td>
    				<td><input $disOtherId $disOtherIdx type="text" name="txtPerumahan" id="txtPerumahan" value="$!txtPerumahan" size="50" maxlength="100" ></td>
    			</tr>
    		
    			<tr>
    				<td>&nbsp;</td>
    				<td>Industri Terdekat</td>
    				<td>:</td>
    				<td><input $disOtherId $disOtherIdx type="text" name="txtIndustri" id="txtIndustri" value="$!txtIndustri" size="50" maxlength="100" ></td>
    			</tr>
    			
    			#set($check1 = "")
    			#set($check2 = "")
    				
    			#if($sorPBT=="1")
    				#set($check1 = "checked")
    				#set($check2 = "")
    			#elseif($sorPBT=="2")
    				#set($check1 = "")
    				#set($check2 = "checked")
    			#end
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Kawasan Pihak Berkuasa Tempatan ?</td>
    				<td>:</td>
    				<td><input $disOtherId1 type="radio" $check1 name="sorPBT" id="sorYa" value="1" >Ya
     				&nbsp;&nbsp;<input $disOtherId1 type="radio" $check2 name="sorPBT" value="2" >Tidak</td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Nama PBT (Sekiranya Ya)</td>
    				<td>:</td>
    				<td><input $disOtherId $disOtherIdx type="text" name="txtNamaPBT" id="txtNamaPBT" value="$!txtNamaPBT" size="40" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
    			</tr>
    			
    			#set($checkR1 = "")
    			#set($checkR2 = "")
    			
    			#if($sorRizab=="1")
    				#set($checkR1 = "checked")
    				#set($checkR2 = "")
    			#elseif($sorRizab=="2")
    				#set($checkR1 = "")
    				#set($checkR2 = "checked")
    			#end
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Kawasan Rizab Melayu ?</td>
    				<td>:</td>
    				<td><input $disOtherId1 type="radio" $checkR1 name="sorRizab" value="1" >Di dalam
     				&nbsp;&nbsp;<input $disOtherId1 type="radio" $checkR2 name="sorRizab" value="2" >Di luar</td>
    			</tr>
    			
    		</table>
    		#end
    		
    		
    		#if($mode=="view")
    		<table width="100%" border="0">
    			
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="32%" valign="top">Lokasi</td>
    				<td width="1%" valign="top">:</td>
    				<td width="66%"><textarea $disability $disabilityx name="txtLokasi" id="txtLokasi" cols="47%" rows="3" onKeyUp="textCounter(this.form.txtLokasi,this.form.remLenLoc,1500);" onKeyDown="textCounter(this.form.txtLokasi,this.form.remLenLoc,1500);" >$!txtLokasi</textarea></td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Jalanraya Utama</td>
    				<td>:</td>
    				<td><input $disability $disabilityx type="text" name="txtJalanUtama" id="txtJalanUtama" value="$!txtJalanUtama" size="50" maxlength="100" ></td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Jalan Masuk</td>
    				<td>:</td>
    				<td><input $disability $disabilityx type="text" name="txtJalanMasuk" id="txtJalanMasuk" value="$!txtJalanMasuk" size="50" maxlength="100" ></td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Nama Kampung/Pekan/Bandar</td>
    				<td>:</td>
    				<td><input $disability $disabilityx type="text" name="txtNamaTempat" id="txtNamaTempat" value="$!txtNamaTempat" size="50" maxlength="100" ></td>
    			</tr>
    		
    			<tr>
    				<td>&nbsp;</td>
    				<td>Jarak Dari Kampung/Pekan/Bandar</td>
    				<td>:</td>
    				<td><input $disability $disabilityx type="text" name="txtJarak" id="txtJarak" value="$!txtJarak" size="30" maxlength="400" ></td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Perumahan Terdekat</td>
    				<td>:</td>
    				<td><input $disability $disabilityx type="text" name="txtPerumahan" id="txtPerumahan" value="$!txtPerumahan" size="50" maxlength="100" ></td>
    			</tr>
    		
    			<tr>
    				<td>&nbsp;</td>
    				<td>Industri Terdekat</td>
    				<td>:</td>
    				<td><input $disability $disabilityx type="text" name="txtIndustri" id="txtIndustri" value="$!txtIndustri" size="50" maxlength="100" ></td>
    			</tr>
    			
    			#if($sorPBT=="1")
    				#set($check1 = "checked")
    				#set($check2 = "")
    			#elseif($sorPBT=="2")
    				#set($check1 = "")
    				#set($check2 = "checked")
    			#else
    				#set($check1 = "")
    				#set($check2 = "")
    			#end
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Kawasan Pihak Berkuasa Tempatan ?</td>
    				<td>:</td>
    				<td><input $disability1 $check1 type="radio" name="sorPBT" id="sorYa" value="1" >Ya
     				&nbsp;&nbsp;<input $disability1 $check2 type="radio" name="sorPBT" value="2" >Tidak</td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Nama PBT (Sekiranya Ya)</td>
    				<td>:</td>
    				<td><input $disability $disabilityx type="text" name="txtNamaPBT" id="txtNamaPBT" value="$!txtNamaPBT" size="40" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
    			</tr>
    			
    			
    			#if($sorRizab=="1")
    				#set($checkR1 = "checked")
    				#set($checkR2 = "")
    			#elseif($sorRizab=="2")
    				#set($checkR1 = "")
    				#set($checkR2 = "checked")
    			#else
    				#set($checkR1 = "")
    				#set($checkR2 = "")
    			#end
    			<tr>
    				<td>&nbsp;</td>
    				<td>Kawasan Rizab Melayu ?</td>
    				<td>:</td>
    				<td><input $disability1 $checkR1 type="radio" name="sorRizab" value="1" >Di dalam
     				&nbsp;&nbsp;<input $disability1 $checkR2 type="radio" name="sorRizab" value="2" >Di luar</td>
    			</tr>
    			
    		</table>
    		#end
    		
    		</fieldset>
    		<table width="100%" border="0">
			<tr align="center">
			<td>
				#if($mode=="new" && $editable=="yes")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanTab1('$!id_hakmilik','$!id_tanah','$!mode')">
				#end
				
				#if($mode=="view" && $editable=="yes")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniTanahTerperinci('$!id_tanah')">
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanTab1('$!id_hakmilik','$!id_tanah','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_hakmilik')">
				#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
			</td>
			</tr>
			</table>
    		
    		</div>
<!-- END TAB 1 -->
    		
    		
    		
    		
    		
<!-- START TAB 2 -->
    		<div class="TabbedPanelsContent">
    		
    <br/> 		
    		<fieldset>
    		<legend><strong>Keadaan Rupa Bumi</strong></legend>
    		
    		#if($mode=="view")
    		
    		#if($flagBukit=="1") #set($checkA = "checked") #else #set($checkA = "") #end
    		#if($flagLandai=="1") #set($checkB = "checked") #else #set($checkB = "") #end
    		#if($flagRata=="1") #set($checkC = "checked") #else #set($checkC = "") #end
    		#if($flagRendah=="1") #set($checkD = "checked") #else #set($checkD = "") #end
    		#if($flagBerpaya=="1") #set($checkE = "checked") #else #set($checkE = "") #end
    		#if($flagLurah=="1") #set($checkF = "checked") #else #set($checkF = "") #end
    		#if($flagLembah=="1") #set($checkG = "checked") #else #set($checkG = "") #end
    		
    		<table width="100%" border="0">   		
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="20%">Keadaan Rupa Bumi</td>
    				<td width="1%">:</td>
    				<td width="78%">
    					<input type="checkbox" $disability1 $checkA name="flagBukit" id="flagBukit" value="1">&nbsp;Berbukit
    					<input type="checkbox" $disability1 $checkB name="flagLandai" id="flagLandai" value="1">&nbsp;Landai
    					<input type="checkbox" $disability1 $checkC name="flagRata" id="flagRata" value="1">&nbsp;Rata
    					<input type="checkbox" $disability1 $checkD name="flagRendah" id="flagRendah" value="1">&nbsp;Rendah
    					<input type="checkbox" $disability1 $checkE name="flagBerpaya" id="flagBerpaya" value="1">&nbsp;Berpaya
    					<input type="checkbox" $disability1 $checkF name="flagLurah" id="flagLurah" value="1">&nbsp;Lurah
    					<input type="checkbox" $disability1 $checkG name="flagLembah" id="flagLembah" value="1">&nbsp;Lembah
    				</td>   				
    			</tr>
    			
    			<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Perihal Lain</td>
            		<td valign="top">:</td>
            		<td><textarea $disability $disabilityx name="txtPerihalRupabumi" id="txtPerihalRupabumi" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtPerihalRupabumi,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtPerihalRupabumi,this.form.remLen1,400);" >$!txtPerihalRupabumi</textarea></td>
            	</tr>
            	
            	#if($isEdit=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
    			
    		</table>
    		#end
    		
    		
    		#if($mode=="new")
    		<table width="100%" border="0">   		
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="20%">Keadaan Rupa Bumi</td>
    				<td width="1%">:</td>
    				<td width="78%">
    					<input type="checkbox" $disOtherId1 name="flagBukit" id="flagBukit" value="1">&nbsp;Berbukit
    					<input type="checkbox" $disOtherId1 name="flagLandai" id="flagLandai" value="1">&nbsp;Landai
    					<input type="checkbox" $disOtherId1 name="flagRata" id="flagRata" value="1">&nbsp;Rata
    					<input type="checkbox" $disOtherId1 name="flagRendah" id="flagRendah" value="1">&nbsp;Rendah
    					<input type="checkbox" $disOtherId1 name="flagBerpaya" id="flagBerpaya" value="1">&nbsp;Berpaya
    					<input type="checkbox" $disOtherId1 name="flagLurah" id="flagLurah" value="1">&nbsp;Lurah
    					<input type="checkbox" $disOtherId1 name="flagLembah" id="flagLembah" value="1">&nbsp;Lembah
    				</td>   				
    			</tr>
    			
    			<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Perihal Lain</td>
            		<td valign="top">:</td>
            		<td><textarea $disOtherId $disOtherIdx name="txtPerihalRupabumi" id="txtPerihalRupabumi" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtPerihalRupabumi,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtPerihalRupabumi,this.form.remLen1,400);" ></textarea></td>
            	</tr>
            	
            	#if($editable=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="400"></td>
           		</tr> 
    			#end
    			
    		</table>
    		#end
    		
    		</fieldset>
    		
    <br/> 		
    
    		<fieldset>
    		<legend><strong>Keadaan Tanah</strong></legend>
    		
    		<table width="100%" border="0">  
    		
    			#if($mode=="new")
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="20%">Keadaan Tanah</td>
    				<td width="1%">:</td>
    				<td width="78%">
    					<input type="checkbox" $disOtherId1 name="flagUsaha" id="flagUsaha" value="1">&nbsp;Diusahakan
    					<input type="checkbox" $disOtherId1 name="flagLapang" id="flagLapang" value="1">&nbsp;Lapang
    					<input type="checkbox" $disOtherId1 name="flagTerbiar" id="flagTerbiar" value="1">&nbsp;Terbiar
    					<input type="checkbox" $disOtherId1 name="flagHutan" id="flagHutan" value="1">&nbsp;Hutan
    					<input type="checkbox" $disOtherId1 name="flagBelukar" id="flagBelukar" value="1">&nbsp;Belukar
    					<input type="checkbox" $disOtherId1 name="flagSemak" id="flagSemak" value="1">&nbsp;Semak
    				</td>   				
    			</tr>
    		
    			<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Perihal Lain</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disOtherId $disOtherIdx name="txtPerihalKeadaan" id="txtPerihalKeadaan" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtPerihalKeadaan,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtPerihalKeadaan,this.form.remLen2,400);" ></textarea></td>
            	</tr>
            	
            	#if($editable=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen2" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Halangan (Jika ada)</td>
            		<td valign="top">:</td>
            		<td valign="top"><textarea $disOtherId $disOtherIdx name="txtHalangan" id="txtHalangan" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtHalangan,this.form.remLen3,400);" onKeyDown="textCounter(this.form.txtHalangan,this.form.remLen3,400);" ></textarea></td>
            	</tr>
            	
            	#if($editable=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Tanaman Terlibat</td>
            		<td valign="top">:</td>
            		<td valign="top"><textarea $disOtherId $disOtherIdx name="txtTanaman" id="txtTanaman" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtTanaman,this.form.remLen4,400);" onKeyDown="textCounter(this.form.txtTanaman,this.form.remLen4,400);" ></textarea></td>
            	</tr>
            	
            	#if($editable=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen4" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		#end
           		
           		
           		
           		#if($mode=="view")
           		
           		#if($flagUsaha=="1") #set($checkxA = "checked") #else #set($checkxA = "") #end
    			#if($flagLapang=="1") #set($checkxB = "checked") #else #set($checkxB = "") #end
    			#if($flagTerbiar=="1") #set($checkxC = "checked") #else #set($checkxC = "") #end
    			#if($flagHutan=="1") #set($checkxD = "checked") #else #set($checkxD = "") #end
    			#if($flagBelukar=="1") #set($checkxE = "checked") #else #set($checkxE = "") #end
    			#if($flagSemak=="1") #set($checkxF = "checked") #else #set($checkxF = "") #end
    			
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="20%">Keadaan Tanah</td>
    				<td width="1%">:</td>
    				<td width="78%">
    					<input type="checkbox" $disability1 $checkxA name="flagUsaha" id="flagUsaha" value="1">&nbsp;Diusahakan
    					<input type="checkbox" $disability1 $checkxB name="flagLapang" id="flagLapang" value="1">&nbsp;Lapang
    					<input type="checkbox" $disability1 $checkxC name="flagTerbiar" id="flagTerbiar" value="1">&nbsp;Terbiar
    					<input type="checkbox" $disability1 $checkxD name="flagHutan" id="flagHutan" value="1">&nbsp;Hutan
    					<input type="checkbox" $disability1 $checkxE name="flagBelukar" id="flagBelukar" value="1">&nbsp;Belukar
    					<input type="checkbox" $disability1 $checkxF name="flagSemak" id="flagSemak" value="1">&nbsp;Semak
    				</td>   				
    			</tr>
    		
    			<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Perihal Lain</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disability $disabilityx name="txtPerihalKeadaan" id="txtPerihalKeadaan" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtPerihalKeadaan,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtPerihalKeadaan,this.form.remLen2,400);" >$!txtPerihalKeadaan</textarea></td>
            	</tr>
            	
            	#if($isEdit=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen2" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Halangan (Jika ada)</td>
            		<td valign="top">:</td>
            		<td valign="top"><textarea $disability $disabilityx name="txtHalangan" id="txtHalangan" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtHalangan,this.form.remLen3,400);" onKeyDown="textCounter(this.form.txtHalangan,this.form.remLen3,400);" >$!txtHalangan</textarea></td>
            	</tr>
            	
            	#if($isEdit=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Tanaman Terlibat</td>
            		<td valign="top">:</td>
            		<td valign="top"><textarea $disability $disabilityx name="txtTanaman" id="txtTanaman" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtTanaman,this.form.remLen4,400);" onKeyDown="textCounter(this.form.txtTanaman,this.form.remLen4,400);" >$!txtTanaman</textarea></td>
            	</tr>
            	
            	#if($isEdit=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen4" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		#end
           		
           		
    		</table>
    		
    		</fieldset>
    		
    		<table width="100%" border="0">
			<tr align="center">
			<td>
				#if($mode=="new" && $editable=="yes")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanTab2('$!id_hakmilik','$!id_tanah','$!mode')">
				#end
				
				#if($mode=="view" && $editable=="yes")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniTanahTerperinci('$!id_tanah')">
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanTab2('$!id_hakmilik','$!id_tanah','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_hakmilik')">
				#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
			</td>
			</tr>
			</table>
			
    		</div>
<!-- END TAB 2 -->
    		
    		
    		
    		
    		
<!-- START TAB 3 -->
    		<div class="TabbedPanelsContent">
    		
   			<br/>
    		
    		<fieldset>
    		<table width="100%" border="0">
    		
    			#if($mode=="new")
    			
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="25%">Saliran</td>
    				<td width="1%">:</td>
    				<td width="73%"><input $disOtherId1 type="radio" name="sorSaliran" id="sorBaik" value="1" >Baik
     				&nbsp;&nbsp;<input $disOtherId1 type="radio" name="sorSaliran" id="sorTidak" value="2" >Tidak</td>
    			</tr>
    			
    			<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Kemudahan Asas Yang Ada</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disOtherId $disOtherIdx name="txtKemudahan" id="txtKemudahan" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtKemudahan,this.form.remLen5,400);" onKeyDown="textCounter(this.form.txtKemudahan,this.form.remLen5,400);" ></textarea></td>
            	</tr>
            	
            	#if($editable=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen5" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Sempadan Utara</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disOtherId $disOtherIdx name="txtSempadanUtara" id="txtSempadanUtara" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtSempadanUtara,this.form.remLen6,400);" onKeyDown="textCounter(this.form.txtSempadanUtara,this.form.remLen6,400);" ></textarea></td>
            	</tr>
            	
            	#if($editable=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen6" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Sempadan Selatan</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disOtherId $disOtherIdx name="txtSempadanSelatan" id="txtSempadanSelatan" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtSempadanSelatan,this.form.remLen7,400);" onKeyDown="textCounter(this.form.txtSempadanSelatan,this.form.remLen7,400);" ></textarea></td>
            	</tr>
            	
            	#if($editable=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen7" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Sempadan Timur</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disOtherId $disOtherIdx name="txtSempadanTimur" id="txtSempadanTimur" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtSempadanTimur,this.form.remLen8,400);" onKeyDown="textCounter(this.form.txtSempadanTimur,this.form.remLen8,400);" ></textarea></td>
            	</tr>
            	
            	#if($editable=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen8" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Sempadan Barat</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disOtherId $disOtherIdx name="txtSempadanBarat" id="txtSempadanBarat" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtSempadanBarat,this.form.remLen9,400);" onKeyDown="textCounter(this.form.txtSempadanBarat,this.form.remLen9,400);" ></textarea></td>
            	</tr>
            	
            	#if($editable=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen9" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		#end
           		
           		
           		
           		#if($mode=="view")
           		
           		#if($sorSaliran=="1")
    				#set($check1s = "checked")
    				#set($check2s = "")
    			#elseif($sorSaliran=="2")
    				#set($check1s = "")
    				#set($check2s = "checked")
    			#else
    				#set($check1s = "")
    				#set($check2s = "")
    			#end
           		
           		<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="25%">Saliran</td>
    				<td width="1%">:</td>
    				<td width="73%"><input $disability1 type="radio" $check1s name="sorSaliran" id="sorBaik" value="1" >Baik
     				&nbsp;&nbsp;<input $disability1 type="radio" $check2s name="sorSaliran" id="sorTidak" value="2" >Tidak</td>
    			</tr>
    			
    			<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Kemudahan Asas Yang Ada</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disability $disabilityx name="txtKemudahan" id="txtKemudahan" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtKemudahan,this.form.remLen5,400);" onKeyDown="textCounter(this.form.txtKemudahan,this.form.remLen5,400);" >$!txtKemudahan</textarea></td>
            	</tr>
            	
            	#if($isEdit=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen5" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Sempadan Utara</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disability $disabilityx name="txtSempadanUtara" id="txtSempadanUtara" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtSempadanUtara,this.form.remLen6,400);" onKeyDown="textCounter(this.form.txtSempadanUtara,this.form.remLen6,400);" >$!txtSempadanUtara</textarea></td>
            	</tr>
            	
            	#if($isEdit=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen6" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Sempadan Selatan</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disability $disabilityx name="txtSempadanSelatan" id="txtSempadanSelatan" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtSempadanSelatan,this.form.remLen7,400);" onKeyDown="textCounter(this.form.txtSempadanSelatan,this.form.remLen7,400);" >$!txtSempadanSelatan</textarea></td>
            	</tr>
            	
            	#if($isEdit=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen7" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Sempadan Timur</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disability $disabilityx name="txtSempadanTimur" id="txtSempadanTimur" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtSempadanTimur,this.form.remLen8,400);" onKeyDown="textCounter(this.form.txtSempadanTimur,this.form.remLen8,400);" >$!txtSempadanTimur</textarea></td>
            	</tr>
            	
            	#if($isEdit=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen8" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		<tr>	
    				<td>&nbsp;</td>
            		<td valign="top">Sempadan Barat</td>
            		<td valign="top">:</td>
            		<td valign="top" width="78%"><textarea $disability $disabilityx name="txtSempadanBarat" id="txtSempadanBarat" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtSempadanBarat,this.form.remLen9,400);" onKeyDown="textCounter(this.form.txtSempadanBarat,this.form.remLen9,400);" >$!txtSempadanBarat</textarea></td>
            	</tr>
            	
            	#if($isEdit=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen9" size="3" maxlength="3" value="400"></td>
           		</tr> 
           		#end
           		
           		
           		#end
           		
           		
    		</table>
    		</fieldset>
    		
    		<table width="100%" border="0">
			<tr align="center">
			<td>
				#if($mode=="new" && $editable=="yes")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanTab3('$!id_hakmilik','$!id_tanah','$!mode')">
				#end
				
				#if($mode=="view" && $editable=="yes")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniTanahTerperinci('$!id_tanah')">
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanTab3('$!id_hakmilik','$!id_tanah','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_hakmilik')">
				#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
			</td>
			</tr>
			</table>
    		
    		</div>
<!-- END TAB 3 -->
    		
    		
    		
    		
    		
<!-- START TAB 4 -->
    		<div class="TabbedPanelsContent">
    		
    		
    		#if($mode=="new")
    		<table width="100%" border="0">
    		<tr>
    		
    		<!-- TABLE KIRI -->
    		<td width="50%" valign="top">
    			<fieldset>
    			<legend>Anggaran Nilaian Penolong Pegawai Tanah</legend>
    			<table width="100%" border="0">
    			
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="38%">Unit Harga</td>
    				<td width="1%">:</td>
    				<td width="60%"><select $disOtherId1 $disOtherIdx name="socUnitHargaSO" style="width:150px">
      		
      					<option value="">SILA PILIH</option>    			
      					<option value="1">METER PERSEGI</option>		
      					<option value="2">HEKTAR</option>	
      						
					</select></td>
    			</tr>
    			
    			#if($txtHargaSeunitSO=="")
           			#set($HSeunitSO="")
        		#else
           			#set($HSeunitSO=$txtHargaSeunitSO)
       			#end
    			<tr>
    				<td>&nbsp;</td>
    				<td>Harga Seunit</td>
    				<td>:</td>
    				<td><input type="text" $disOtherId $disOtherIdx name="txtHargaSeunitSO" id="txtHargaSeunitSO" value="$!HSeunitSO" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HSeunitSO')"></td>
    			</tr>
    			
    			#if($txtHargaPasaranSO=="")
           			#set($HPasaranSO="")
        		#else
           			#set($HPasaranSO=$txtHargaPasaranSO)
       			#end
    			<tr>
    				<td>&nbsp;</td>
    				<td>Harga Pasaran</td>
    				<td>:</td>
    				<td><input type="text" $disOtherId $disOtherIdx name="txtHargaPasaranSO" id="txtHargaPasaranSO" value="$!HPasaranSO" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HPasaranSO')"></td>
    			</tr>
    			
    			#if($txtPenjejasanSO=="")
           			#set($HPenjejasanSO="")
        		#else
           			#set($HPenjejasanSO=$txtPenjejasanSO)
       			#end
    			<tr>
    				<td>&nbsp;</td>
    				<td>Penjejasan Terbabit</td>
    				<td>:</td>
    				<td><input type="text" $disOtherId $disOtherIdx name="txtPenjejasanSO" id="txtPenjejasanSO" value="$!HPenjejasanSO" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HPenjejasanSO')"></td>
    			</tr>
    			
    			#if($txtPecahSO=="")
           			#set($HPecahSO="")
        		#else
           			#set($HPecahSO=$txtPecahSO)
       			#end
    			<tr>
    				<td>&nbsp;</td>
    				<td>Pecah Pisah</td>
    				<td>:</td>
    				<td><input type="text" $disOtherId $disOtherIdx name="txtPecahSO" id="txtPecahSO" value="$!HPecahSO" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HPecahSO')"></td>
    			</tr>
    			
    			#if($txtKenaikanSO=="")
           			#set($HKenaikanSO="")
        		#else
           			#set($HKenaikanSO=$txtKenaikanSO)
       			#end
    			<tr>
    				<td>&nbsp;</td>
    				<td>Kenaikan Nilai</td>
    				<td>:</td>
    				<td><input type="text" $disOtherId $disOtherIdx name="txtKenaikanSO" id="txtKenaikanSO" value="$!HKenaikanSO" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HKenaikanSO')"></td>
    			</tr>
    			
    			</table>
    			</fieldset>
    		</td>
    		
    		
    		<!-- TABLE KANAN -->
    		<td width="50%" valign="top">
    			<fieldset>
    			<legend>Nilaian JPPH</legend>
    			<table width="100%" border="0">
    		
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="38%">Unit Harga</td>
    				<td width="1%">:</td>
    				<td width="60%"><select $disOtherId1 $disOtherIdx name="socUnitHargaJP" style="width:150px">
      		
      					<option value="">SILA PILIH</option>    			
      					<option value="1">METER PERSEGI</option>		
      					<option value="2">HEKTAR</option>		
      						
					</select></td>
    			</tr>
    			
    			#if($txtHargaSeunitJP=="")
           			#set($HSeunitJP="")
        		#else
           			#set($HSeunitJP=$txtHargaSeunitJP)
       			#end
    			<tr>
    				<td>&nbsp;</td>
    				<td>Harga Seunit</td>
    				<td>:</td>
    				<td><input type="text" $disOtherId $disOtherIdx name="txtHargaSeunitJP" id="txtHargaSeunitJP" value="$!HSeunitJP" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HSeunitJP')"></td>
    			</tr>
    			
    			#if($txtHargaPasaranJP=="")
           			#set($HPasaranJP="")
        		#else
           			#set($HPasaranJP=$txtHargaPasaranJP)
       			#end
    			<tr>
    				<td>&nbsp;</td>
    				<td>Harga Pasaran</td>
    				<td>:</td>
    				<td><input type="text" $disOtherId $disOtherIdx name="txtHargaPasaranJP" id="txtHargaPasaranJP" value="$!HPasaranJP" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HPasaranJP')"></td>
    			</tr>
    			
    			#if($txtPenjejasanJP=="")
           			#set($HPenjejasanJP="")
        		#else
           			#set($HPenjejasanJP=$txtPenjejasanJP)
       			#end
    			<tr>
    				<td>&nbsp;</td>
    				<td>Penjejasan Terbabit</td>
    				<td>:</td>
    				<td><input type="text" $disOtherId $disOtherIdx name="txtPenjejasanJP" id="txtPenjejasanJP" value="$!HPenjejasanJP" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HPenjejasanJP')"></td>
    			</tr>
    			
    			#if($txtPecahJP=="")
           			#set($HPecahJP="")
        		#else
           			#set($HPecahJP=$txtPecahJP)
       			#end
    			<tr>
    				<td>&nbsp;</td>
    				<td>Pecah Pisah</td>
    				<td>:</td>
    				<td><input type="text" $disOtherId $disOtherIdx name="txtPecahJP" id="txtPecahJP" value="$!HPecahJP" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HPecahJP')"></td>
    			</tr>
    			
    			#if($txtKenaikanJP=="")
           			#set($HKenaikanJP="")
        		#else
           			#set($HKenaikanJP=$txtKenaikanJP)
       			#end
    			<tr>
    				<td>&nbsp;</td>
    				<td>Kenaikan Nilai</td>
    				<td>:</td>
    				<td><input type="text" $disOtherId $disOtherIdx name="txtKenaikanJP" id="txtKenaikanJP" value="$!HKenaikanJP" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HKenaikanJP')"></td>
    			</tr>
    			
    			</table>
    			</fieldset>
    		</td>
    		
    		
    		</tr>
    		</table>
    		#end
    		
    		
    		
    		#if($mode=="view")
    		<table width="100%" border="0">
    		<tr>
    		
    		<!-- TABLE KIRI -->
    		<td width="50%" valign="top">
    			<fieldset>
    			<legend>Anggaran Nilaian Penolong Pegawai Tanah</legend>
    			<table width="100%" border="0">
    			
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="38%">Unit Harga</td>
    				<td width="1%">:</td>
    				<td width="60%"><select name="socUnitHargaSO" $disability1 $disabilityx style="width:150px">
      		
      					#if($socUnitHargaSO=="1")
      					<option value="1">METER PERSEGI</option>				
      					<option value="2">HEKTAR</option>	  	
      					<option value="">SILA PILIH</option>   
      					#elseif($socUnitHargaSO=="2")	
      					<option value="2">HEKTAR</option>	   
      					<option value="1">METER PERSEGI</option>
      					<option value="">SILA PILIH</option>   				
      					#else
      					<option value="">SILA PILIH</option>    			
      					<option value="1">METER PERSEGI</option>
      					<option value="2">HEKTAR</option>	
      					#end	
      						
					</select></td>
    			</tr>
    			    
    			<tr>
    				<td>&nbsp;</td>
    				<td>Harga Seunit</td>
    				<td>:</td>
    				#if($isEdit=="no")
    				<td><input type="text" $disability $disabilityx name="txtHargaSeunitSO" id="txtHargaSeunitSO" value="$!Utils.format2Decimal($txtHargaSeunitSO)" size="10" maxlength="11" style="text-align:right" ></td>
    				#else
    				<td><input type="text" $disability $disabilityx name="txtHargaSeunitSO" id="txtHargaSeunitSO" value="$!txtHargaSeunitSO" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtHargaSeunitSO')"></td>
    				#end
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Harga Pasaran</td>
    				<td>:</td>
    				#if($isEdit=="no")
    				<td><input type="text" $disability $disabilityx name="txtHargaPasaranSO" id="txtHargaPasaranSO" value="$!Utils.format2Decimal($txtHargaPasaranSO)" size="10" maxlength="11" style="text-align:right" ></td>
    				#else
    				<td><input type="text" $disability $disabilityx name="txtHargaPasaranSO" id="txtHargaPasaranSO" value="$!txtHargaPasaranSO" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtHargaPasaranSO')"></td>
    				#end
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Penjejasan Terbabit</td>
    				<td>:</td>
    				#if($isEdit=="no")
    				<td><input type="text" $disability $disabilityx name="txtPenjejasanSO" id="txtPenjejasanSO" value="$!Utils.format2Decimal($txtPenjejasanSO)" size="10" maxlength="11" style="text-align:right" ></td>
    				#else
    				<td><input type="text" $disability $disabilityx name="txtPenjejasanSO" id="txtPenjejasanSO" value="$!txtPenjejasanSO" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtPenjejasanSO')"></td>
    				#end
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Pecah Pisah</td>
    				<td>:</td>
    				#if($isEdit=="no")
    				<td><input type="text" $disability $disabilityx name="txtPecahSO" id="txtPecahSO" value="$!Utils.format2Decimal($txtPecahSO)" size="10" maxlength="11" style="text-align:right" ></td>
    				#else
    				<td><input type="text" $disability $disabilityx name="txtPecahSO" id="txtPecahSO" value="$!txtPecahSO" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtPecahSO')"></td>
    				#end
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Kenaikan Nilai</td>
    				<td>:</td>
    				#if($isEdit=="no")
    				<td><input type="text" $disability $disabilityx name="txtKenaikanSO" id="txtKenaikanSO" value="$!Utils.format2Decimal($txtKenaikanSO)" size="10" maxlength="11" style="text-align:right" ></td>
    				#else
    				<td><input type="text" $disability $disabilityx name="txtKenaikanSO" id="txtKenaikanSO" value="$!txtKenaikanSO" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtKenaikanSO')"></td>
    				#end
    			</tr>
    			
    			</table>
    			</fieldset>
    		</td>
    		
    		
    		<!-- TABLE KANAN -->
    		<td width="50%" valign="top">
    			<fieldset>
    			<legend>Nilaian JPPH</legend>
    			<table width="100%" border="0">
    		
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="38%">Unit Harga</td>
    				<td width="1%">:</td>
    				<td width="60%"><select name="socUnitHargaJP" $disability1 $disabilityx style="width:150px">
      		
      					#if($socUnitHargaJP=="1")
      					<option value="1">METER PERSEGI</option>		  				
      					<option value="2">HEKTAR</option>	
      					<option value="">SILA PILIH</option>    	
      					#elseif($socUnitHargaJP=="2")	
      					<option value="2">HEKTAR</option>	
      					<option value="1">METER PERSEGI</option>	
      					<option value="">SILA PILIH</option>   			
      					#else
      					<option value="">SILA PILIH</option>    			
      					<option value="1">METER PERSEGI</option>
      					<option value="2">HEKTAR</option>	
      					#end		
      						
					</select></td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Harga Seunit</td>
    				<td>:</td>
    				#if($isEdit=="no")
    				<td><input type="text" $disability $disabilityx name="txtHargaSeunitJP" id="txtHargaSeunitJP" value="$!Utils.format2Decimal($txtHargaSeunitJP)" size="10" maxlength="11" style="text-align:right" ></td>
    				#else
    				<td><input type="text" $disability $disabilityx name="txtHargaSeunitJP" id="txtHargaSeunitJP" value="$!txtHargaSeunitJP" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtHargaSeunitJP')"></td>
    				#end
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Harga Pasaran</td>
    				<td>:</td>
    				#if($isEdit=="no")
    				<td><input type="text" $disability $disabilityx name="txtHargaPasaranJP" id="txtHargaPasaranJP" value="$!Utils.format2Decimal($txtHargaPasaranJP)" size="10" maxlength="11" style="text-align:right" ></td>
    				#else
    				<td><input type="text" $disability $disabilityx name="txtHargaPasaranJP" id="txtHargaPasaranJP" value="$!txtHargaPasaranJP" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtHargaPasaranJP')"></td>
    				#end
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Penjejasan Terbabit</td>
    				<td>:</td>
    				#if($isEdit=="no")
    				<td><input type="text" $disability $disabilityx name="txtPenjejasanJP" id="txtPenjejasanJP" value="$!Utils.format2Decimal($txtPenjejasanJP)" size="10" maxlength="11" style="text-align:right" ></td>
    				#else
    				<td><input type="text" $disability $disabilityx name="txtPenjejasanJP" id="txtPenjejasanJP" value="$!txtPenjejasanJP" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtPenjejasanJP')"></td>
    				#end
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Pecah Pisah</td>
    				<td>:</td>
    				#if($isEdit=="no")
    				<td><input type="text" $disability $disabilityx name="txtPecahJP" id="txtPecahJP" value="$!Utils.format2Decimal($txtPecahJP)" size="10" maxlength="11" style="text-align:right" ></td>
    				#else
    				<td><input type="text" $disability $disabilityx name="txtPecahJP" id="txtPecahJP" value="$!txtPecahJP" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtPecahJP')"></td>
    				#end
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Kenaikan Nilai</td>
    				<td>:</td>
    				#if($isEdit=="no")
    				<td><input type="text" $disability $disabilityx name="txtKenaikanJP" id="txtKenaikanJP" value="$!Utils.format2Decimal($txtKenaikanJP)" size="10" maxlength="11" style="text-align:right" ></td>
    				#else
    				<td><input type="text" $disability $disabilityx name="txtKenaikanJP" id="txtKenaikanJP" value="$!txtKenaikanJP" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtKenaikanJP')"></td>
    				#end
    			</tr>
    			
    			</table>
    			</fieldset>
    		</td>
    		
    		
    		</tr>
    		</table>
    		#end

<!-- PPT-42V -->    		
    		<br/>
    		
    		<fieldset>
    		<legend><strong>Nilaian Swasta</strong></legend>
    			
    			#if($mode=="new")
    			<table width="100%" border="0">
    				<tr>
	    				<td width="1%">&nbsp;</td>
	    				<td width="16%">Unit Harga</td>
	    				<td width="1%">:</td>
	    				<td width="70%"><select $disOtherId1 $disOtherIdx name="socUnitHargaNS" style="width:150px">
	      		
	      					<option value="">SILA PILIH</option>    			
	      					<option value="1">METER PERSEGI</option>		
	      					<option value="2">HEKTAR</option>		
	      						
						</select></td>
	    			</tr>
	    			
	    			#if($txtHargaSeunitNS=="")
	           			#set($HSeunitNS="")
	        		#else
	           			#set($HSeunitNS=$txtHargaSeunitNS)
	       			#end
	    			<tr>
	    				<td>&nbsp;</td>
	    				<td>Harga Seunit</td>
	    				<td>:</td>
	    				<td><input type="text" $disOtherId $disOtherIdx name="txtHargaSeunitNS" id="txtHargaSeunitNS" value="$!HSeunitNS" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HSeunitNS')"></td>
	    			</tr>
	    			
	    			#if($txtHargaPasaranNS=="")
	           			#set($HPasaranNS="")
	        		#else
	           			#set($HPasaranNS=$txtHargaPasaranNS)
	       			#end
	    			<tr>
	    				<td>&nbsp;</td>
	    				<td>Harga Pasaran</td>
	    				<td>:</td>
	    				<td><input type="text" $disOtherId $disOtherIdx name="txtHargaPasaranNS" id="txtHargaPasaranNS" value="$!HPasaranNS" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HPasaranNS')"></td>
	    			</tr>
	    			
	    			#if($txtPenjejasanNS=="")
	           			#set($HPenjejasanNS="")
	        		#else
	           			#set($HPenjejasanNS=$txtPenjejasanNS)
	       			#end
	    			<tr>
	    				<td>&nbsp;</td>
	    				<td>Penjejasan Terbabit</td>
	    				<td>:</td>
	    				<td><input type="text" $disOtherId $disOtherIdx name="txtPenjejasanNS" id="txtPenjejasanNS" value="$!HPenjejasanNS" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HPenjejasanNS')"></td>
	    			</tr>
	    			
	    			#if($txtPecahNS=="")
	           			#set($HPecahNS="")
	        		#else
	           			#set($HPecahNS=$txtPecahNS)
	       			#end
	    			<tr>
	    				<td>&nbsp;</td>
	    				<td>Pecah Pisah</td>
	    				<td>:</td>
	    				<td><input type="text" $disOtherId $disOtherIdx name="txtPecahNS" id="txtPecahNS" value="$!HPecahNS" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HPecahNS')"></td>
	    			</tr>
	    			
	    			#if($txtKenaikanNS=="")
	           			#set($HKenaikanNS="")
	        		#else
	           			#set($HKenaikanNS=$txtKenaikanNS)
	       			#end
	    			<tr>
	    				<td>&nbsp;</td>
	    				<td>Kenaikan Nilai</td>
	    				<td>:</td>
	    				<td><input type="text" $disOtherId $disOtherIdx name="txtKenaikanNS" id="txtKenaikanNS" value="$!HKenaikanNS" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HKenaikanNS')"></td>
	    			</tr>
    				
    			</table>
    			#end
    			
    			#if($mode=="view")
    			<table width="100%" border="0">
		   			<tr>
		    				<td width="1%">&nbsp;</td>
		    				<td width="16%">Unit Harga</td>
		    				<td width="1%">:</td>
		    				<td width="70%"><select name="socUnitHargaNS" $disability1 $disabilityx style="width:150px">
		      		
		      					#if($socUnitHargaNS=="1")
		      					<option value="1">METER PERSEGI</option>		  				
		      					<option value="2">HEKTAR</option>	
		      					<option value="">SILA PILIH</option>    	
		      					#elseif($socUnitHargaNS=="2")	
		      					<option value="2">HEKTAR</option>	
		      					<option value="1">METER PERSEGI</option>	
		      					<option value="">SILA PILIH</option>   			
		      					#else
		      					<option value="">SILA PILIH</option>    			
		      					<option value="1">METER PERSEGI</option>
		      					<option value="2">HEKTAR</option>	
		      					#end		
		      						
							</select></td>
		    			</tr>
		    			
		    			<tr>
		    				<td>&nbsp;</td>
		    				<td>Harga Seunit</td>
		    				<td>:</td>
		    				#if($isEdit=="no")
		    				<td><input type="text" $disability $disabilityx name="txtHargaSeunitNS" id="txtHargaSeunitNS" value="$!Utils.format2Decimal($txtHargaSeunitNS)" size="10" maxlength="11" style="text-align:right" ></td>
		    				#else
		    				<td><input type="text" $disability $disabilityx name="txtHargaSeunitNS" id="txtHargaSeunitNS" value="$!txtHargaSeunitNS" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtHargaSeunitNS')"></td>
		    				#end
		    			</tr>
		    			
		    			<tr>
		    				<td>&nbsp;</td>
		    				<td>Harga Pasaran</td>
		    				<td>:</td>
		    				#if($isEdit=="no")
		    				<td><input type="text" $disability $disabilityx name="txtHargaPasaranNS" id="txtHargaPasaranNS" value="$!Utils.format2Decimal($txtHargaPasaranNS)" size="10" maxlength="11" style="text-align:right" ></td>
		    				#else
		    				<td><input type="text" $disability $disabilityx name="txtHargaPasaranNS" id="txtHargaPasaranNS" value="$!txtHargaPasaranNS" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtHargaPasaranNS')"></td>
		    				#end
		    			</tr>
		    			
		    			<tr>
		    				<td>&nbsp;</td>
		    				<td>Penjejasan Terbabit</td>
		    				<td>:</td>
		    				#if($isEdit=="no")
		    				<td><input type="text" $disability $disabilityx name="txtPenjejasanNS" id="txtPenjejasanNS" value="$!Utils.format2Decimal($txtPenjejasanNS)" size="10" maxlength="11" style="text-align:right" ></td>
		    				#else
		    				<td><input type="text" $disability $disabilityx name="txtPenjejasanNS" id="txtPenjejasanNS" value="$!txtPenjejasanNS" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtPenjejasanNS')"></td>
		    				#end
		    			</tr>
		    			
		    			<tr>
		    				<td>&nbsp;</td>
		    				<td>Pecah Pisah</td>
		    				<td>:</td>
		    				#if($isEdit=="no")
		    				<td><input type="text" $disability $disabilityx name="txtPecahNS" id="txtPecahNS" value="$!Utils.format2Decimal($txtPecahNS)" size="10" maxlength="11" style="text-align:right" ></td>
		    				#else
		    				<td><input type="text" $disability $disabilityx name="txtPecahNS" id="txtPecahNS" value="$!txtPecahNS" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtPecahNS')"></td>
		    				#end
		    			</tr>
		    			
		    			<tr>
		    				<td>&nbsp;</td>
		    				<td>Kenaikan Nilai</td>
		    				<td>:</td>
		    				#if($isEdit=="no")
		    				<td><input type="text" $disability $disabilityx name="txtKenaikanNS" id="txtKenaikanNS" value="$!Utils.format2Decimal($txtKenaikanNS)" size="10" maxlength="11" style="text-align:right" ></td>
		    				#else
		    				<td><input type="text" $disability $disabilityx name="txtKenaikanNS" id="txtKenaikanNS" value="$!txtKenaikanNS" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtKenaikanNS')"></td>
		    				#end
		    			</tr>
    			</table>
    			#end
    			
    		</fieldset>	    		
    		
    		
<!-- END PPT-42 -->   		
    		<br/>
    		
    		<fieldset>
    		<legend><strong>Pecah Pisah</strong></legend>
    			
    			#if($mode=="new")
    			<table width="100%" border="0">
    				<tr>
    					<td width="1%" valign="top">&nbsp;</td>
    					<td width="8%" valign="top">Ulasan</td>
    					<td width="1%" valign="top">:</td>
    					<td width="80%"><textarea $disOtherId $disOtherIdx name="txtJenisPisah" id="txtJenisPisah" cols="40%" rows="3" onKeyUp="textCounter(this.form.txtJenisPisah,this.form.remLenPP,1500);" onKeyDown="textCounter(this.form.txtJenisPisah,this.form.remLenPP,1500);" ></textarea></td>
    				</tr>
    			</table>
    			#end
    			
    			#if($mode=="view")
    			<table width="100%" border="0">
    				<tr>
    					<td width="1%" valign="top">&nbsp;</td>
    					<td width="8%" valign="top">Ulasan</td>
    					<td width="1%" valign="top">:</td>
    					<td width="80%"><textarea $disability $disabilityx name="txtJenisPisah" id="txtJenisPisah" cols="40%" rows="3" onKeyUp="textCounter(this.form.txtJenisPisah,this.form.remLenPP,1500);" onKeyDown="textCounter(this.form.txtJenisPisah,this.form.remLenPP,1500);" >$!txtJenisPisah</textarea></td>
    				</tr>
    			</table>
    			#end
    			
    		</fieldset>	
    		
    		<!-- <table width="100%" border="0">
    			<tr>
    			<td>
    			
    			<fieldset>
    			<legend><strong>Borang G</strong></legend>
    			<table width="100%" border="0">
    		
    					#if($mode=="new")
    					<tr>
    						<td width="1%">&nbsp;</td>
    						<td width="18%">Unit Harga</td>
    						<td width="1%">:</td>
    						<td width="80%"><select $disOtherId1 $disOtherIdx name="socUnitHargaG" style="width:150px">
      		
      							<option value="">SILA PILIH</option>    			
      							<option value="1">METER PERSEGI</option>
      							<option value="2">HEKTAR</option>	
      						
							</select></td>
    					</tr>
    					#if($txtHargaBorangG=="")
           					#set($HBorangG="")
        				#else
           					#set($HBorangG=$txtHargaBorangG)
       					#end
    					<tr>
    						<td>&nbsp;</td>
    						<td>Harga Seunit</td>
    						<td>:</td>
    						<td><input type="text" $disOtherId $disOtherIdx name="txtHargaBorangG" id="txtHargaBorangG" value="$!HBorangG" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!HBorangG')"></td>
    					</tr>    					
    					#end
    					
    					
    					
    					#if($mode=="view")
    
    					<tr>
    						<td width="1%">&nbsp;</td>
    						<td width="18%">Unit Harga</td>
    						<td width="1%">:</td>
    						<td width="80%"><select name="socUnitHargaG" $disability1 $disabilityx style="width:150px">
      							
      							#if($socUnitHargaG=="1")
      							<option value="1">METER PERSEGI</option>	
      							<option value="2">HEKTAR</option>	
      							<option value="">SILA PILIH</option>     	
      							#elseif($socUnitHargaG=="2")	
      							<option value="2">HEKTAR</option>	
      							<option value="1">METER PERSEGI</option>
      							<option value="">SILA PILIH</option>   				
      							#else
      							<option value="">SILA PILIH</option>    			
      							<option value="1">METER PERSEGI</option>
      							<option value="2">HEKTAR</option>	
      							#end	
      						
							</select></td>
    					</tr>
    					
    					<tr>
    						<td>&nbsp;</td>
    						<td>Harga Seunit</td>
    						<td>:</td>
    						#if($isEdit=="no")
    						<td><input type="text" $disability $disabilityx name="txtHargaBorangG" id="txtHargaBorangG" value="$!Utils.format2Decimal($txtHargaBorangG)" size="10" maxlength="11" style="text-align:right" ></td>
    						#else
    						<td><input type="text" $disability $disabilityx name="txtHargaBorangG" id="txtHargaBorangG" value="$!txtHargaBorangG" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtHargaBorangG')"></td>
    						#end
    					</tr>  
    					  					
    					#end
    					
    					
    			</table>
    			</fieldset>
    					
    			</td>
    			</tr>
    		</table> -->
    		
    		<br/>
 
    		<table width="100%" border="0">
			<tr align="center">
			<td>
				#if($mode=="new" && $editable=="yes")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanTab4('$!id_hakmilik','$!id_tanah','$!mode')">
				#end
				
				#if($mode=="view" && $editable=="yes")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniTanahTerperinci('$!id_tanah')">
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanTab4('$!id_hakmilik','$!id_tanah','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_hakmilik')">
				#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
			</td>
			</tr>
			</table>
    		
    		</div>
<!-- END TAB 4 -->
    		
    		
    		
    		
<!-- START TAB 5 -->
    		<div class="TabbedPanelsContent">
    		
    		<br/>
    		
    		#if($mode=="new")
    		<fieldset>
    		<table width="100%" border="0">
   
    			<tr>	
    				<td width="1%">&nbsp;</td>
            		<td width="25%" valign="top">Pandangan dan Syor</td>
            		<td width="1%" valign="top">:</td>
            		<td width="73%" valign="top" width="78%"><textarea $disOtherId $disOtherIdx name="txtUlasanPegawai" id="txtUlasanPegawai" cols="50%" rows="6" onKeyUp="textCounter(this.form.txtUlasanPegawai,this.form.remLen10,1500);" onKeyDown="textCounter(this.form.txtUlasanPegawai,this.form.remLen10,1500);" ></textarea></td>
            	</tr>
            	
            	#if($editable=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen10" size="3" maxlength="3" value="1500"></td>
           		</tr> 
           		#end
           		    		
           	</table>
    		</fieldset>
    		
    		<fieldset>
    		<table width="100%" border="0">
    		
    			<tr><td colspan="4">&nbsp;</td></tr>
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="25%">Nama Penolong Pegawai Tanah</td>
    				<td width="1%">:</td>
    				<td width="73%"><input type="text" readonly class="disabled" name="txtPelapor" id="txtPelapor" value="$!{session.getAttribute('_portal_username')}" size="40" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
					<td>Tarikh</td>
					<td>:</td>
					<td><input name="txdTarikhUlasan" readonly class="disabled" value="$!sysdate" size="11" id="txdTarikhUlasan" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 		$!frmtdate</td>
				</tr>
				
    		</table>		
    		</fieldset>
    		#end
    		
    		
    		#if($mode=="view")
    		<fieldset>
    		<table width="100%" border="0">
   
    			<tr>	
    				<td width="1%">&nbsp;</td>
            		<td width="25%" valign="top">Pandangan dan Syor</td>
            		<td width="1%" valign="top">:</td>
            		<td width="73%" valign="top" width="78%"><textarea $disability $disabilityx name="txtUlasanPegawai" id="txtUlasanPegawai" cols="50%" rows="6" onKeyUp="textCounter(this.form.txtUlasanPegawai,this.form.remLen10,1500);" onKeyDown="textCounter(this.form.txtUlasanPegawai,this.form.remLen10,1500);" >$!txtUlasanPegawai</textarea></td>
            	</tr>
            	
            	#if($isEdit=="yes")
            	<tr>
        			<td colspan="3">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen10" size="3" maxlength="3" value="1500"></td>
           		</tr> 
           		#end
           			
           	</table>
    		</fieldset>
    		
    		<fieldset>
    		<table width="100%" border="0">
    		
    			<tr><td colspan="4">&nbsp;</td></tr>
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="25%">Nama Penolong Pegawai Tanah</td>
    				<td width="1%">:</td>
    				#if($isEdit=="no")
    				<td width="73%"><input type="text" readonly class="disabled" name="txtPelapor" id="txtPelapor" value="$!txtPelapor" size="40" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
    				#else
    				<td width="73%"><input type="text" readonly class="disabled" name="txtPelapor" id="txtPelapor" value="$!{session.getAttribute('_portal_username')}" size="40" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
    				#end
    				
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
					<td>Tarikh</td>
					<td>:</td>
					#if($isEdit=="no")
					<td><input name="txdTarikhUlasan" readonly class="disabled" value="$!txdTarikhUlasan" size="11" id="txdTarikhUlasan" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 		$!frmtdate</td>
					#else
					<td><input name="txdTarikhUlasan" readonly class="disabled" value="$!sysdate" size="11" id="txdTarikhUlasan" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 		$!frmtdate</td>
					#end
					
				</tr>
				
    		</table>		
    		</fieldset>
    		#end
    		
    		
    		<table width="100%" border="0">
			<tr align="center">
			<td>
				#if($mode=="new" && $editable=="yes")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanTab5('$!id_hakmilik','$!id_tanah','$!mode')">
				#end
				
				#if($mode=="view" && $editable=="yes")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniTanahTerperinci('$!id_tanah')">
				<!--<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />-->
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanTab5('$!id_hakmilik','$!id_tanah','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_hakmilik')">
				#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
			</td>
			</tr>
			</table>
    		
    		</div>
<!-- END TAB 5 -->
    		
    		
<!-- START TAB 6 -->  		
    		<div class="TabbedPanelsContent">
    		
    		<br/>
    		
    		<fieldset>
    		<table width="100%" border="0">
    			<tr>
    				<td width="1%"><font color="red">#if($editable=="yes")*#end</font></td>
    				<td width="20%">Tajuk</td>
    				<td width="1%">:</td>
    				<td width="78%"><input $disOtherId $disOtherIdx type="text" name="txtTajuk" id="txtTajuk" value="" size="43" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td valign="top">Keterangan</td>
    				<td valign="top">:</td>
    				<td><textarea $disOtherId $disOtherIdx name="txtKeterangan" id="txtKeterangan" cols="40%" rows="3" onKeyUp="textCounter(this.form.txtKeterangan,this.form.remLenDoc,400);" onKeyDown="textCounter(this.form.txtKeterangan,this.form.remLenDoc,400);" ></textarea></td>
    			</tr>
    			
    			<tr>
            		<td colspan="3">&nbsp;</td>
  					<td><input $disOtherId1 $disOtherIdx id="fileupload" name="fileupload" type="file" size="60" /></td>
  				</tr>
    		</table>
    		</fieldset>
    		
    		<br/>
    		
    		<table width="100%" border="0">
			<tr align="center">
			<td>
				#if($editable=="yes")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanTab6('$!id_permohonan','$!id_hakmilik','$!id_tanah','$!mode')">
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">		
			</td>
			</tr>
			</table>
    		
    		<br/>
    		
    		<fieldset>
    		<legend><strong>Senarai Dokumen</strong></legend>
    				
    				#if($saiz_listDokumen > 5)
                	<div style="width:100%;height:100;overflow:auto"> 
           			#end	
    			
    				<table width="100%" border="0"> 
  
        				<tr class="table_header">
        					<td align="center" width="4%"><b>No</b></td>
        					<td><b>Tajuk</b></td>
                  			<td><b>Keterangan</b></td> 
                  			#if($saiz_listDokumen!=0) 
                  			<td width="13%"><b>&nbsp;</b></td>  
                  			#if($editable=="yes")            
                  			<td width="11%"><b>&nbsp;</b></td>
                  			#end
                  			#end
        				</tr>
        		
        			#if($saiz_listDokumen!=0)
                    	#foreach($list in $listDokumen)
                    	#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              		 		#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                   	 
               			<tr>
                   			<td class="$row" align="center">$!list.bil</td>
                   			<td class="$row">$!list.tajuk</td>
                			<td class="$row">$!list.keterangan</td> 
                			#if($saiz_listDokumen!=0) 
                			<td class="$row" align="center"><input type="button" name="cmdDownload" value ="Muat Turun" onClick="papar_Lampiran('$!list.id_dokumen')"></td>   
                			#if($editable=="yes")
                			<td class="$row" align="center"><input type="button" name="cmdHapusDoc" value ="Hapus" onClick="hapusDokumen('$!id_permohonan','$!id_hakmilik','$!id_tanah','$!list.id_dokumen')"></td>  
                			#end
                			#end 
               			</tr>
                    	#end
                    
               		#else
                    	<tr>
                    		<td colspan="2">Tiada rekod</td>
                    	</tr>
              	 	#end
              	 	
		  			</table>
	
					#if($saiz_listDokumen > 5)
                	</div>
            		#end
            		
    		</fieldset>
    		
    		</div>
<!-- END TAB 6 -->
    		
 		</div>
 		
	</div>

    </fieldset>
    	
</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>  
      	 <td><a href="#" onClick="javascript:cetakLaporanTanah('$!id_fail','$!id_hakmilik')"><font color="blue">Laporan Penolong Pegawai Tanah</font></a></td>
      </tr>  
    </table>
</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_tanah" value="$!id_tanah">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
function onchangeUnitAmbilUpdate() {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatTanah";
	document.${formName}.command2.value = "kemaskiniTanahTerperinci";
	document.${formName}.command3.value = "onchangeUnitAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
function onchangeUnitAsalUpdate() {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatTanah";
	document.${formName}.command2.value = "kemaskiniTanahTerperinci";
	document.${formName}.command3.value = "onchangeUnitAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
function onchangeUnitAmbil() {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatTanah";
	document.${formName}.command2.value = "onchangeUnitAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
function onchangeUnitAsal() {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatTanah";
	document.${formName}.command2.value = "onchangeUnitAsal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
function hapusDokumen(idpermohonan,idHakmilik,idTanah,iddokumen) {
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;

	//token for dopost
	var ft = document.${formName}.form_token.value ;
	
	var commands = "&command=hapusDokumen&ScreenLocation=TabbedPanels1";
	var id = "&id_hakmilik="+idHakmilik+"&id_permohonan="+idpermohonan+"&id_tanah="+idTanah+"&id_dokumen="+iddokumen;
	var token = "&form_token="+ft;

	var actionItem = (commands+id+token);
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah"+actionItem;
	document.${formName}.submit();
	
}
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function simpanTab6(idpermohonan,idHakmilik,idTanah,mode) {

	if(document.${formName}.txtTajuk.value == ""){
		
		alert("Sila masukkan \"Tajuk Dokumen\" terlebih dahulu.");
  		document.${formName}.txtTajuk.focus(); 
		return;
		
	}else 
	if(document.${formName}.fileupload.value == ""){

		alert("Sila pilih \"Dokumen\" yang hendak dimuatnaik terlebih dahulu.");
  		document.${formName}.fileupload.focus(); 
		return;
			
	}else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;

		//token for dopost
		var ft = document.${formName}.form_token.value ;

		//data
		var txtTajuk = document.${formName}.txtTajuk.value ;
		var txtKeterangan = document.${formName}.txtKeterangan.value ;
		
		var commands = "";
		var token = "&form_token="+ft;
		var data = "&txtTajuk="+txtTajuk+"&txtKeterangan="+txtKeterangan;
		var mainItem = "&id_hakmilik="+idHakmilik+"&id_permohonan="+idpermohonan+"&ScreenLocation=TabbedPanels1";  
		
		
		if(mode=="new"){
			commands = "&command=maklumatTanah&command2=simpanTanahTerperinci";
		}else{
			commands = "&command=maklumatTanah&command2=kemaskiniTanahTerperinci&command3=updateTanahTerperinci&id_tanah="+idTanah;
		}

		var actionItem = (commands+mainItem+token+data);

		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.encoding = "multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah"+actionItem+"&tabId=5";
		document.${formName}.submit();
		
	}
}
function cetakLaporanTanah(idfail,idHakmilik) {
	
    var url = "../servlet/ekptg.report.ppt.LaporanTanah?id_Fail="+idfail+"&idHakmilik="+idHakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function batalKemaskini(idHakmilik){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "maklumatTanah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
function kemaskiniTanahTerperinci(idTanah) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_tanah.value = idTanah;
	document.${formName}.command.value = "maklumatTanah";
	document.${formName}.command2.value = "kemaskiniTanahTerperinci";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
function simpanTab1(idHakmilik,idTanah,mode) {

	var dat1=document.${formName}.txdTarikhLawatMula
	var dat2=document.${formName}.txdTarikhLawatAkhir

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat
	var TLM  = document.getElementById("txdTarikhLawatMula").value;
	var dt1   = parseInt(TLM.substring(0,2),10);
   	var mon1  = parseInt(TLM.substring(3,5),10);
   	var yr1   = parseInt(TLM.substring(6,10),10);
   	var dateMula = new Date(yr1, mon1, dt1);

  	//tarikh terima jawapan
	var TLA  = document.getElementById("txdTarikhLawatAkhir").value;
	var dt2   = parseInt(TLA.substring(0,2),10);
   	var mon2  = parseInt(TLA.substring(3,5),10);
   	var yr2   = parseInt(TLA.substring(6,10),10);
   	var dateAkhir = new Date(yr2, mon2, dt2);

   	
   	if(document.${formName}.socJenisHakmilik.value == ""){
		alert("Sila pilih \"Jenis Hakmilik\" terlebih dahulu.");
  		document.${formName}.socJenisHakmilik.focus(); 
		return;
	}
   	else if(document.${formName}.txtNoHakmilik.value == ""){
		alert("Sila masukkan \"No.Hakmilik\" terlebih dahulu.");
  		document.${formName}.txtNoHakmilik.focus(); 
		return;
	}
   	else if(document.${formName}.txtNoSyit.value == ""){
		alert("Sila masukkan \"No.Syit\" terlebih dahulu.");
  		document.${formName}.txtNoSyit.focus(); 
		return;
	}
	else if(document.${formName}.socKategoriTanah.value == ""){
		alert("Sila pilih \"Kategori Tanah\" terlebih dahulu.");
  		document.${formName}.socKategoriTanah.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if(dat1.value!="" && (dateMula < currentDate)){
	   	alert("Sila pastikan \"Tarikh Lawat Mula\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhLawatMula.focus();
		return;
	}
*/	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
/*	else if(dat2.value!="" && (dateAkhir < currentDate)){
	   	alert("Sila pastikan \"Tarikh Lawat Akhir\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhLawatAkhir.focus();
		return;
	}
*/	else if(dat1.value!="" && dat2.value!="" && (dateAkhir < dateMula)){
	   	alert("Sila pastikan \"Tarikh Lawat Akhir\" tidak kurang dari \"Tarikh Lawat Mula\".");
		document.${formName}.txdTarikhLawatAkhir.focus();
		return;
	}
	else{

		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_hakmilik.value = idHakmilik;

		if(mode=="new"){
			document.${formName}.command.value = "maklumatTanah";
			document.${formName}.command2.value = "simpanTanahTerperinci";
		}else{
			document.${formName}.id_tanah.value = idTanah;
			document.${formName}.command.value = "maklumatTanah";
			document.${formName}.command2.value = "kemaskiniTanahTerperinci";
			document.${formName}.command3.value = "updateTanahTerperinci";
		}

		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
		document.${formName}.submit();
	}
}
function simpanTab2(idHakmilik,idTanah,mode) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_hakmilik.value = idHakmilik;
	if(mode=="new"){
		document.${formName}.command.value = "maklumatTanah";
		document.${formName}.command2.value = "simpanTanahTerperinci";
	}else{
		document.${formName}.id_tanah.value = idTanah;
		document.${formName}.command.value = "maklumatTanah";
		document.${formName}.command2.value = "kemaskiniTanahTerperinci";
		document.${formName}.command3.value = "updateTanahTerperinci";
	}
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
function simpanTab3(idHakmilik,idTanah,mode) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_hakmilik.value = idHakmilik;
	if(mode=="new"){
		document.${formName}.command.value = "maklumatTanah";
		document.${formName}.command2.value = "simpanTanahTerperinci";
	}else{
		document.${formName}.id_tanah.value = idTanah;
		document.${formName}.command.value = "maklumatTanah";
		document.${formName}.command2.value = "kemaskiniTanahTerperinci";
		document.${formName}.command3.value = "updateTanahTerperinci";
	}
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
//PPT-42
function simpanTab4(idHakmilik,idTanah,mode) {  
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_hakmilik.value = idHakmilik;
	if(mode=="new"){
		document.${formName}.command.value = "maklumatTanah";
		document.${formName}.command2.value = "simpanTanahTerperinci";
	}else{
		document.${formName}.id_tanah.value = idTanah;
		document.${formName}.command.value = "maklumatTanah";
		document.${formName}.command2.value = "kemaskiniTanahTerperinci";
		document.${formName}.command3.value = "updateTanahTerperinci";
	}
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
function simpanTab5(idHakmilik,idTanah,mode) {

	var dat1=document.${formName}.txdTarikhUlasan

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat
	var TS  = document.getElementById("txdTarikhUlasan").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateUlasan = new Date(yr1, mon1, dt1);
   	
	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if(dat1.value!="" && (dateUlasan < currentDate)){
	   	alert("Sila pastikan \"Tarikh Ulasan\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhUlasan.focus();
		return;
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_hakmilik.value = idHakmilik;
		if(mode=="new"){
			document.${formName}.command.value = "maklumatTanah";
			document.${formName}.command2.value = "simpanTanahTerperinci";
		}else{
			document.${formName}.id_tanah.value = idTanah;
			document.${formName}.command.value = "maklumatTanah";
			document.${formName}.command2.value = "kemaskiniTanahTerperinci";
			document.${formName}.command3.value = "updateTanahTerperinci";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
		document.${formName}.submit();
	}
}
function maklumatTanah(idHakmilik) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "maklumatTanah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
function viewListHM(id_permohonan) {

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
</script>


<script>
window.onload = submitForm;

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$!selectedTab});

function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content!=""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	}else{
		elmnt.value = "";
	}
}
function validateNilai(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric3(content);
		return;
	}
}
function RemoveNonNumeric3( strString )
{
      var strValidCharacters = "1234567890.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
</script>

<script>
//Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yang sah")
		return false
	}
return true
}
</script>
