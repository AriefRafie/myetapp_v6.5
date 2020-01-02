#parse("app/ppt/Sek8Paging.jsp")

#if($showAlertPaging=="yes")
<script>
alert("Sila Klik 'Paging' No.6 Untuk Teruskan Proses Seksyen 8");
</script>
#end


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

<fieldset id="top">
<legend><strong>Laporan Awal Tanah Seksyen 8</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset>
	<table width="100%" border="0">
		<tr>
			<td width="25%">Nama Pegawai</td>
			<td width="1%">:</td>
			<td width="74%">$!selectPegawaiHM</td>
		</tr>
	</table>
	</fieldset>
	
	#if($showLaporan=="no")	
		<input type="hidden" name="carianNoLot">
	#else
	
	<br/>
	
	<fieldset>
	<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
		<!--	
			<table width="100%" border="0">   
                	<tr>
                    	<td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20"   ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    	-->		
                
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
    <a href="javascript:popupCarianHakmilik('$id_permohonan','laporan_awal_tanah')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
    </td>
    </tr>
    </table>
    		<!-- List Hakmilik 
            #parse("app/ppt/frmSeksyen8ListHM.jsp")
            -->
	
	</fieldset>

	<br/>

	
	#if($mode=="new")
	
	<fieldset id="changeLaporan">
	<legend><strong>Laporan Awal Tanah</strong></legend>
	
		
		<table width="100%" border="0">
			<tr>
				
				<!-- TABLE KIRI -->
				<td width="53%" valign="top"><table width="100%" border="0">
					
					<tr>
						<td width="37%">Tarikh Mula Charting</td>
						<td width="1%">:</td>
						<td width="62%"><input $disOtherId $disOtherIdx name="txdTarikhMula" value="$!txdTarikhMula" size="11" id="txdTarikhMula" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMula',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
				
					<tr>
						<td>Tarikh Tamat Charting</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx name="txdTarikhTamat" value="$!txdTarikhTamat" size="11" id="txdTarikhTamat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTamat',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
					
					<tr>
						<td>Tarikh Lawat Tapak</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx name="txdTarikhLawat" value="$!txdTarikhLawat" size="11" id="txdTarikhLawat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawat',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
					
					<tr>
						<td>No. Peta Kadaster</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx type="text" name="txtNoPeta" id="txtNoPeta" value="$!txtNoPeta"  maxlength="100" size="33" /></td>
					</tr>
					
					<tr>
						<td>Bil Keseluruhan Lot</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx type="text" name="txtBilKeseluruhan" id="txtBilKeseluruhan" value="$!totalHM"   size="7" onkeyup="validateNumber(this,this.value)" /></td>
					</tr>
				
					<tr>
						<td>Luas Keseluruhan</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx type="text" name="txtLuasKeseluruhan" id="txtLuasKeseluruhan" value="$!txtLuasKeseluruhan"   size="15" onkeyup="validateNumber(this,this.value)" />
						
							<select $disOtherId1 $disOtherIdx name="sorDropdownUnitAsal" style="width:132px" onchange="onchangeUnit()">
      		
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

							</select>
							
						</td>
					</tr>
					
				</table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="47%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td width="56%">Anggaran Kasar Kos Tanah</td>
						<td width="1%">:</td>
						<td width="43%"><input $disOtherId $disOtherIdx type="text" maxlength="50" name="txtKosTanah" id="txtKosTanah" value="$!txtKosTanah" size="19"   /></td>
					</tr>
			
					<tr>
						<td>Anggaran Kasar Kos Bangunan</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx type="text" maxlength="50" name="txtKosBangunan" id="txtKosBangunan" value="$!txtKosBangunan" size="19"   /></td>
					</tr>
					
					<tr>
						<td>Anggaran Kos Keseluruhan Projek </td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx type="text" maxlength="50" name="txtKosProjek" id="txtKosProjek" value="$!txtKosProjek" size="19"   /></td>
					</tr>
					
					<tr>
						<td valign="top">Perihal Bangunan</td>
						<td valign="top">&nbsp;</td>
						<td valign="top">&nbsp;</td>
					</tr>
					
					<tr>
						<td colspan="3"><textarea $disOtherId $disOtherIdx name="txtPerihal" id="txtPerihal" cols="45%" rows="4" onKeyUp="textCounter(this.form.txtPerihal,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtPerihal,this.form.remLen1,400);" >$!txtPerihal</textarea></td>
					</tr>
					
				</table></td>
				
			</tr>
		</table>
	</fieldset>
<br/>	
	
	
	<fieldset>
	<legend><strong>Nilaian Awal Tanah Oleh JPPH</strong></legend>
		<table width="100%" border="0">
			<tr>
			
				<td width="28%" align="top">Anggaran Nilai Tanah</td>
			</tr>
			<tr>
			  <td align="top"><textarea $disOtherId $disOtherIdx name="txtNilai" id="txtNilai" cols="80%" rows="5" onkeyup="textCounter(this.form.txtNilai,this.form.remLenPND,4000);" onkeydown="textCounter(this.form.txtNilai,this.form.remLenPND,4000);" >$!txtNilai</textarea></td>
		  </tr>
		</table>	
	</fieldset>
	
	<fieldset>
	<legend><strong>Pendahuluan</strong></legend>
		<table width="100%" border="0">
			<tr>
				<td><textarea $disOtherId $disOtherIdx name="txtPendahuluan" id="txtPendahuluan" cols="80%" rows="5" onKeyUp="textCounter(this.form.txtPendahuluan,this.form.remLenPND,4000);" onKeyDown="textCounter(this.form.txtPendahuluan,this.form.remLenPND,4000);" >$!txtPendahuluan</textarea></td>
			</tr>
		</table>	
	</fieldset>
	
<br/>
	<fieldset>
	<legend><strong>Latar Belakang Tanah</strong></legend>
	
		<table width="100%" border="0">
			<tr>
			
				<!-- TABLE KIRI -->
				<td width="50%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td>#if($id_projekNegeri == "6") Keadaan Rupa Bumi #else Tanah Milik Persendirian #end </td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtTanahSendiri" id="txtTanahSendiri" cols="60%" rows="8" onKeyUp="textCounter(this.form.txtTanahSendiri,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtTanahSendiri,this.form.remLen2,400);" >$!txtTanahSendiri</textarea></td>
					</tr>
					
					<tr>
						<td>
                        
                        #if($id_projekNegeri == "6") Kawasan Masjlis Daerah / Perbandaran #else Tanah Rizab Negeri #end 
                        
                        </td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtTanahNegeri" id="txtTanahNegeri" cols="60%" rows="8" onKeyUp="textCounter(this.form.txtTanahNegeri,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtTanahNegeri,this.form.remLen2,400);" >$!txtTanahNegeri</textarea></td>
					</tr>
					
					<tr>
						<td>#if($id_projekNegeri == "6") Anggaran Nilaian Tanah #else Tanah Milik/Rizab Persekutuan #end </td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtTanahPersekutuan" id="txtTanahPersekutuan" cols="60%" rows="8" onKeyUp="textCounter(this.form.txtTanahPersekutuan,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtTanahPersekutuan,this.form.remLen2,400);" >$!txtTanahPersekutuan</textarea></td>
					</tr>
					
					
					<tr>
						<td>
                        
                         #if($id_projekNegeri == "6") 
                        Laporan Atas Tanah
                        #else
                        Keadaan Tanah 
                        #end
                        
                        </td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtKeadaanTanah" id="txtKeadaanTanah" cols="60%" rows="8" onKeyUp="textCounter(this.form.txtKeadaanTanah,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtKeadaanTanah,this.form.remLen2,400);" >$!txtKeadaanTanah</textarea></td>
					</tr>
                    
                    
                    <tr>
						<td>
                        
                         #if($id_projekNegeri == "6") 
                        Kemudahan Awam
                        #else
                        Kemudahan Awam 
                        #end
                        
                        </td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtKemudahanAwam" id="txtKemudahanAwam" cols="60%" rows="8" onKeyUp="textCounter(this.form.txtKemudahanAwam,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtKemudahanAwam,this.form.remLen2,400);" >$!txtKemudahanAwam</textarea></td>
					</tr>
					
				</table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="50%" valign="top"><table width="100%" border="0">
					<tr>
						<td>#if($id_projekNegeri == "6") 
                        Lokasi Tanah
                        #else
                        Lokasi Tanah 
                        #end</td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtLokasi" id="txtLokasi" cols="65%" rows="8" onKeyUp="textCounter(this.form.txtLokasi,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtLokasi,this.form.remLen2,400);" >$!txtLokasi</textarea></td>
					</tr>
					
					<tr>
						<td>
                        
                         #if($id_projekNegeri == "6") 
                        Kawasan Simpanan Melayu
                        #else
                        Kawasan Rizab Melayu 
                        #end
                        
                        </td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtTanahMelayu" id="txtTanahMelayu" cols="65%" rows="8" onKeyUp="textCounter(this.form.txtTanahMelayu,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtTanahMelayu,this.form.remLen2,400);" >$!txtTanahMelayu</textarea></td>
					</tr>
					
					<tr>
						<td>  #if($id_projekNegeri == "6") 
                       Lain-lain
                        #else
                       Tanah Kerajaan
                        #end</td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtTanahKerajaan" id="txtTanahKerajaan" cols="65%" rows="8" onKeyUp="textCounter(this.form.txtTanahKerajaan,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtTanahKerajaan,this.form.remLen2,400);" >$!txtTanahKerajaan</textarea></td>
					</tr>
					
					<tr>
						<td>#if($id_projekNegeri == "6") Ulasan Penolong Pegawai Tanah #else Syor/Pandangan #end </td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtSyor" id="txtSyor" cols="65%" rows="8" onKeyUp="textCounter(this.form.txtSyor,this.form.remLen3,1500);" onKeyDown="textCounter(this.form.txtSyor,this.form.remLen3,1500);" >$!txtSyor</textarea></td>
					</tr>
				</table></td>
				
			</tr>
		</table>
		
	</fieldset>
	#end
	

	#if($mode=="view")
	
	#if($onchange=="no")
	#foreach($data in $dataLaporanTanah)
		#set($txdTarikhMula=$data.tarikh_mula_chart)
		#set($txdTarikhTamat=$data.tarikh_akhir_chart)
		#set($txdTarikhLawat=$data.tarikh_lawatan)
		#set($txtBilKeseluruhan=$data.bil_hakmilik)
		#set($txtLuasKeseluruhan=$data.luas_terlibat)
		#set($txtPerihal=$data.perihal_bangunan)
		#set($txtTanahSendiri=$data.perihal_tm_sendiri)
		#set($txtTanahNegeri=$data.perihal_tr_negeri)
		#set($txtTanahPersekutuan=$data.perihal_tmtr_sekutuan)
		#set($txtKeadaanTanah=$data.keadaan_rupabumi)
        #set($txtKemudahanAwam=$data.kemudahan_awam)
		#set($txtLokasi=$data.lokasi_tanah)
		#set($txtTanahMelayu=$data.perihal_kawasan_melayu)
		#set($txtTanahKerajaan=$data.perihal_tnh_kerajaan)
		#set($txtSyor=$data.ulasan_syor)
		#set($txtNoPeta=$data.no_peta_kadaster)
		#set($txtKosTanah=$data.harga_anggar)
		#set($txtKosBangunan=$data.harga_anggar_bangunan)
		#set($txtPendahuluan=$data.pendahuluan)
		#set($txtNilai=$data.nilai_tanah)
		#set($sorDropdownUnitAsal=$data.id_unitluas)
		#set($txtKosProjek=$data.harga_anggar_projek)
	#end
	#end
	
	#if($isEdit=="no")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#set($disability1 = "disabled")
		#set($M = "")
	#else
		#set($M = "*")
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
	#end
	
	<fieldset id="changeLaporan">
	<legend><strong>Laporan Awal Tanah</strong></legend>
	
		
		<table width="100%" border="0">
			<tr>
				
				<!-- TABLE KIRI -->
				<td width="53%" valign="top"><table width="100%" border="0">
					
					<tr>
						<td width="37%">Tarikh Mula Charting</td>
						<td width="1%">:</td>
						<td width="62%"><input $disability $disabilityx name="txdTarikhMula" value="$!txdTarikhMula" size="11" id="txdTarikhMula" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMula',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
				
					<tr>
						<td>Tarikh Tamat Charting</td>
						<td>:</td>
						<td><input $disability $disabilityx name="txdTarikhTamat" value="$!txdTarikhTamat" size="11" id="txdTarikhTamat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTamat',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
					
					<tr>
						<td>Tarikh Lawat Tapak</td>
						<td>:</td>
						<td><input $disability $disabilityx name="txdTarikhLawat" value="$!txdTarikhLawat" size="11" id="txdTarikhLawat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawat',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
					
					<tr>
						<td>No. Peta Kadaster</td>
						<td>:</td>
						<td><input $disability $disabilityx type="text" name="txtNoPeta" id="txtNoPeta" value="$!txtNoPeta"  maxlength="100" size="33" /></td>
					</tr>
					
					<tr>
						<td>Bil Keseluruhan Lot</td>
						<td>:</td>
						<td><input $disability $disabilityx type="text" name="txtBilKeseluruhan" id="txtBilKeseluruhan" value="$!txtBilKeseluruhan"   size="7" onkeyup="validateNumber(this,this.value)" /></td>
					</tr>
				
					<tr>
						<td>Luas Keseluruhan</td>
						<td>:</td>
						<td><input $disability $disabilityx type="text" name="txtLuasKeseluruhan" id="edittxtLuasKeseluruhan" value="$!txtLuasKeseluruhan"   size="15" onkeyup="validateNumber(this,this.value)" />
							
							<select $disability1 $disabilityx name="sorDropdownUnitAsal" style="width:132px" onchange="onchangeUnitUpdate()">
      		
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

							</select>
							
						</td>
					</tr>
					
				</table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="47%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td width="56%">Anggaran Kasar Kos Tanah</td>
						<td width="1%">:</td>
						<td width="43%"><input $disability $disabilityx type="text" maxlength="50" name="txtKosTanah" id="txtKosTanah" value="$!txtKosTanah" size="19"   /></td>
					</tr>
					
					<tr>
						<td>Anggaran Kasar Kos Bangunan</td>
						<td>:</td>
						<td><input $disability $disabilityx type="text" maxlength="50" name="txtKosBangunan" id="txtKosBangunan" value="$!txtKosBangunan"  size="19"   /></td>
					</tr>
					
					<tr>
						<td>Anggaran Kos Keseluruhan Projek </td>
						<td>:</td>
						<td><input $disability $disabilityx type="text" maxlength="50" name="txtKosProjek" id="txtKosProjek" value="$!txtKosProjek" size="19"   /></td>
					</tr>
					
					<tr>
						<td valign="top">Perihal Bangunan</td>
						<td valign="top">&nbsp;</td>
						<td valign="top">&nbsp;</td>
					</tr>
					
					<tr>
						<td colspan="3"><textarea $disability $disabilityx name="txtPerihal" id="txtPerihal" cols="45%" rows="4" onKeyUp="textCounter(this.form.txtPerihal,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtPerihal,this.form.remLen1,400);" >$!txtPerihal</textarea></td>
					</tr>
					
				</table></td>
				
			</tr>
		</table>
	</fieldset>
<br/>	

<fieldset>
	<legend><strong>Nilai Awal Tanah Oleh JPPH</strong></legend>
		<table width="100%" border="0">
			<tr>
				<td width="28%" align="top">Anggaran Nilai Tanah</td>
			</tr>
			<tr>
				<td width ="70%"><textarea $disability $disabilityx name="txtNilai" id="txtNilai" cols="80%" rows="5" onKeyUp="textCounter(this.form.txtNilai,this.form.remLenPND,4000);" onKeyDown="textCounter(this.form.txtNilai,this.form.remLenPND,4000);" >$!txtNilai</textarea></td>
			</tr>
		</table>	
	</fieldset>

<br/>

	<fieldset>
	<legend><strong>Pendahuluan</strong></legend>
		<table width="100%" border="0">
			<tr>
				<td><textarea $disability $disabilityx name="txtPendahuluan" id="txtPendahuluan" cols="80%" rows="5" onKeyUp="textCounter(this.form.txtPendahuluan,this.form.remLenPND,4000);" onKeyDown="textCounter(this.form.txtPendahuluan,this.form.remLenPND,4000);" >$!txtPendahuluan</textarea></td>
			</tr>
		</table>	
	</fieldset>

<br/>
	<fieldset>
	<legend><strong>Latar Belakang Tanah</strong></legend>
	
		<table width="100%" border="0">
			<tr>
			
				<!-- TABLE KIRI -->
				<td width="50%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td> #if($id_projekNegeri == "6") 
                        Keadaan Rupa Bumi
                        #else
                        Tanah Milik Persendirian 
                        #end    </td>
					</tr>
					<tr>
						<td>
                        <textarea $disability $disabilityx name="txtTanahSendiri" id="txtTanahSendiri" cols="60%" rows="8" onKeyUp="textCounter(this.form.txtTanahSendiri,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtTanahSendiri,this.form.remLen2,400);" >$!txtTanahSendiri </textarea></td>
					</tr>
					
					<tr>
						<td>
                        
                        
                        #if($id_projekNegeri == "6") 
                       Kawasan Masjlis Daerah / Perbandaran
                        #else
                        Tanah Rizab Negeri 
                        #end
                        
                        
                         </td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtTanahNegeri" id="txtTanahNegeri" cols="60%" rows="8" onKeyUp="textCounter(this.form.txtTanahNegeri,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtTanahNegeri,this.form.remLen2,400);" >$!txtTanahNegeri</textarea></td>
					</tr>
					
					<tr>
						<td>
                        
                        
                        #if($id_projekNegeri == "6") 
                        Anggaran Nilaian Tanah
                        #else
                        Tanah Milik/Rizab Persekutuan 
                        #end
                        
                        
                        
                        </td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtTanahPersekutuan" id="txtTanahPersekutuan" cols="60%" rows="8" onKeyUp="textCounter(this.form.txtTanahPersekutuan,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtTanahPersekutuan,this.form.remLen2,400);" >$!txtTanahPersekutuan</textarea></td>
					</tr>
					
					
					<tr>
						<td> 
                        
                        
                        #if($id_projekNegeri == "6") 
                        Laporan Atas Tanah
                        #else
                        Keadaan Tanah 
                        #end
                        
                        
                        </td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtKeadaanTanah" id="txtKeadaanTanah" cols="60%" rows="8" onKeyUp="textCounter(this.form.txtKeadaanTanah,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtKeadaanTanah,this.form.remLen2,400);" >$!txtKeadaanTanah</textarea></td>
					</tr>
                    
                    
                    <tr>
						<td> 
                        
                        
                        #if($id_projekNegeri == "6") 
                        Kemudahan Awam
                        #else
                        Kemudahan Awam 
                        #end
                        
                        
                        </td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtKemudahanAwam" id="txtKemudahanAwam" cols="60%" rows="8" onKeyUp="textCounter(this.form.txtKemudahanAwam,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtKemudahanAwam,this.form.remLen2,400);" >$!txtKemudahanAwam</textarea></td>
					</tr>
					
				</table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="50%" valign="top"><table width="100%" border="0">
					<tr>
						<td> 
                        
                        
                        #if($id_projekNegeri == "6") 
                         Lokasi Tanah 
                        #else
                        Lokasi Tanah 
                        #end
                        
                        
                        </td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtLokasi" id="txtLokasi" cols="65%" rows="8" onKeyUp="textCounter(this.form.txtLokasi,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtLokasi,this.form.remLen2,400);" >$!txtLokasi</textarea></td>
					</tr>
					
					<tr>
						<td> 
                        
                        
                        #if($id_projekNegeri == "6") 
                        Kawasan Simpanan Melayu
                        #else
                        Kawasan Rizab Melayu 
                        #end
                        
                        </td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtTanahMelayu" id="txtTanahMelayu" cols="65%" rows="8" onKeyUp="textCounter(this.form.txtTanahMelayu,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtTanahMelayu,this.form.remLen2,400);" >$!txtTanahMelayu</textarea></td>
					</tr>
					
					<tr>
						<td> 
                        
                        
                        #if($id_projekNegeri == "6") 
                       Lain-lain
                        #else
                       Tanah Kerajaan
                        #end
                        
                        </td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtTanahKerajaan" id="txtTanahKerajaan" cols="65%" rows="8" onKeyUp="textCounter(this.form.txtTanahKerajaan,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtTanahKerajaan,this.form.remLen2,400);" >$!txtTanahKerajaan</textarea></td>
					</tr>
					
					<tr>
						<td> 
                        
                        
                        #if($id_projekNegeri == "6") 
                       Ulasan Penolong Pegawai Tanah
                        #else
                       Syor/Pandangan
                        #end
                        
                        
                        </td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtSyor" id="txtSyor" cols="65%" rows="8" onKeyUp="textCounter(this.form.txtSyor,this.form.remLen3,1500);" onKeyDown="textCounter(this.form.txtSyor,this.form.remLen3,1500);" >$!txtSyor</textarea></td>
					</tr>
				</table></td>
				
			</tr>
		</table>
		
	</fieldset>
	#end

	#end
	
	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($mode=="new" && $editable=="yes")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanLaporan('$!id_permohonan','0','$!id_tanahumum')">
				#end
				
				#if($mode=="view" && $editable=="yes")
					#if($isEdit=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniLaporan('$!id_tanahumum')">
					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanLaporan('$!id_permohonan','1','$!id_tanahumum')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batal('$id_permohonan','$!id_pegawai')">
					#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="kembali()">
			</td>
		</tr>
	</table>

</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
		 <td><a href="#" onClick="javascript:cetakLaporanAwalSS8('$!id_permohonan','$!id_tanahumum','$!id_projekNegeri')"><font color="blue">Laporan Awal Tanah</font></a></td>
	  </tr>   
    </table>
</fieldset>	

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">
<input type="hidden" name="id_tanahumum" value="$!id_tanahumum">
<input type="hidden" name="id_pegawai" value="$!id_pegawai">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>





<script>
function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	var id_pegawai = document.${formName}.socPegawaiHM.value;
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin+"&id_pegawai="+id_pegawai;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}


function onchangeUnitUpdate() {
	document.${formName}.ScreenLocation.value = "changeLaporan";
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.command3.value = "kemaskiniLaporan";
	document.${formName}.command4.value = "onchangeUnitUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah";
	document.${formName}.submit();
}
function onchangeUnit() {
	document.${formName}.ScreenLocation.value = "changeLaporan";
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.command3.value = "onchangeUnit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah";
	document.${formName}.submit();
}
function cetakLaporanAwalSS8(idpermohonan,id_tanahumum,id_negeri) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_tanahumum="+id_tanahumum+"&id_negeri="+id_negeri+"&report=laporanTanahSS8&selectNoFail=yes";	
    //var url = "../servlet/ekptg.report.ppt.laporanTanahSS8?idTanahumum="+id_tanahumum;
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
function getLaporan() {
	
	document.${formName}.carianNoLot.value = "";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah";
	document.${formName}.submit();
}
function batal(id_permohonan,id_pegawai) {

	document.${formName}.ScreenLocation.value = "changeLaporan";
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pegawai.value = id_pegawai;
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah";
	document.${formName}.submit();
}
function kemaskiniLaporan(idTanahUmum) {

	document.${formName}.ScreenLocation.value = "changeLaporan";
	
	document.${formName}.id_tanahumum.value = idTanahUmum;
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.command3.value = "kemaskiniLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah";
	document.${formName}.submit();
}
function simpanLaporan(idpermohonan,mode,idTanahUmum) {
	
	document.${formName}.ScreenLocation.value = "changeLaporan";

	var dat1=document.${formName}.txdTarikhMula
	var dat2=document.${formName}.txdTarikhTamat
	var dat3=document.${formName}.txdTarikhLawat

	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
	else{

		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.id_permohonan.value = idpermohonan;	

		if(mode=="0"){
			document.${formName}.command.value = "tambahLaporan";
			document.${formName}.command2.value = "getLaporan";
			document.${formName}.command3.value = "simpanLaporan";
		}else{
			document.${formName}.id_tanahumum.value = idTanahUmum;
			document.${formName}.command.value = "tambahLaporan";
			document.${formName}.command2.value = "getLaporan";
			document.${formName}.command3.value = "kemaskiniLaporan";
			document.${formName}.command4.value = "updateLaporan";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah";
		document.${formName}.submit();
		}
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "top";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "top";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah";
	document.${formName}.submit();
}
</script>




<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}

}

function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
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
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
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