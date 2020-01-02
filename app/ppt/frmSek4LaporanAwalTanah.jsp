#parse("app/ppt/frmLabelSet.jsp")
#parse("app/ppt/Sek4Paging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset>
<center>
<legend><strong>Laporan Awal Tanah Seksyen 4</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	#if($mode=="new")

	<fieldset id="changeLaporan">
	<legend><strong>Laporan Awal Tanah</strong></legend>
	
		
		<table width="100%" border="0">
			<tr>
				
				<!-- TABLE KIRI -->
				<td width="54%" valign="top"><table width="100%" border="0">
					
					<tr>
						<td width="1%" valign="top"><font color="red">*</font></td>
						<td width="37%" valign="top">No. Syit Piawai</td>
						<td width="1%" valign="top">:</td>
						<td width="61%"><textarea name="txtNoSyit" id="txtNoSyit" cols="30%" rows="1"   onKeyUp="textCounter(this.form.txtNoSyit,this.form.remLen11,400);" onKeyDown="textCounter(this.form.txtNoSyit,this.form.remLen11,400);" ></textarea></td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td>No. Peta Kadaster</td>
						<td>:</td>
						<td><input type="text" name="txtNoPeta" id="txtNoPeta" value="" maxlength="100"   size="33"  /></td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td>Bil Keseluruhan Lot</td>
						<td>:</td>
						<td><input type="text" name="txtBilKeseluruhan" id="txtBilKeseluruhan" value="$!totalHM"   maxlength="5" size="7" onkeyup="validateNumber(this,this.value)" /></td>
					</tr>
				
					<tr>
						<td>&nbsp;</td>
						<td>Luas Keseluruhan</td>
						<td>:</td>
						<td><input type="text" name="txtLuasKeseluruhan" id="txtLuasKeseluruhan" value=""   size="15" onkeyup="validateNumber(this,this.value)" />
							
							<select name="sorDropdownUnitAsal" style="width:132px">
      		
      						<option value="">SILA PILIH</option>
      						<option value="1">HEKTAR</option>
							<option value="2">METER PERSEGI</option>	

							</select>
							
						</td>
					</tr>
		
				</table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="46%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="44%">Tarikh Mula Charting</td>
						<td width="1%">:</td>
						<td width="54%"><input name="txdTarikhMula" value="" size="10" id="txdTarikhMula" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMula',false,'dmy');">$!frmtdate</td>
					</tr>
				
					<tr>
						<td>&nbsp;</td>
						<td>Tarikh Tamat Charting</td>
						<td>:</td>
						<td><input name="txdTarikhTamat" value="" size="10" id="txdTarikhTamat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTamat',false,'dmy');">$!frmtdate</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td>Tarikh Lawat Tapak</td>
						<td>:</td>
						<td><input name="txdTarikhLawat" value="" size="10" id="txdTarikhLawat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawat',false,'dmy');">$!frmtdate</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td colspan="3">Laporan Atas Tanah</td>
					</tr>
						
					<tr>
						<td>&nbsp;</td>
						<td colspan="3"><textarea name="txtLaporanTanah" id="txtLaporanTanah" cols="45%" rows="3" onKeyUp="textCounter(this.form.txtLaporanTanah,this.form.remLen12,4000);" onKeyDown="textCounter(this.form.txtLaporanTanah,this.form.remLen12,4000);" ></textarea></td>
					</tr>
					
				</table></td>
				
			</tr>
		</table>
	</fieldset>
<br/>	

	<fieldset>
	<legend><strong>Pendahuluan</strong></legend>
		<table width="100%" border="0">
			<tr>
				<td><textarea name="txtPendahuluan" id="txtPendahuluan" cols="80%" rows="5" onKeyUp="textCounter(this.form.txtPendahuluan,this.form.remLenPND,4000);" onKeyDown="textCounter(this.form.txtPendahuluan,this.form.remLenPND,4000);" ></textarea></td>
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
						<td>Kawasan Majlis Daerah / Perbandaran</td>
					</tr>
					<tr>
						<td><textarea name="txtKawasanMajlis" id="txtKawasanMajlis" cols="48%" rows="2" onKeyUp="textCounter(this.form.txtKawasanMajlis,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtKawasanMajlis,this.form.remLen2,400);" ></textarea></td>
					</tr>
					
					<tr>
						<td>Kawasan Simpanan Melayu</td>
					</tr>
					<tr>
						<td><textarea name="txtSimpanMelayu" id="txtSimpanMelayu" cols="48%" rows="2" onKeyUp="textCounter(this.form.txtSimpanMelayu,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtSimpanMelayu,this.form.remLen2,400);" ></textarea></td>
					</tr>
					
					<tr>
						<td>Lain-lain</td>
					</tr>
					<tr>
						<td><textarea name="txtLainlain" id="txtLainlain" cols="48%" rows="2" onKeyUp="textCounter(this.form.txtLainlain,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtLainlain,this.form.remLen2,400);" ></textarea></td>
					</tr>
					
					
					<tr>
						<td>Hal-hal lain / Ulasan / Pandangan</td>
					</tr>
					<tr>
						<td><textarea name="txtUlasan" id="txtUlasan" cols="48%" rows="2" onKeyUp="textCounter(this.form.txtUlasan,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtUlasan,this.form.remLen2,400);" ></textarea></td>
					</tr>
					
				</table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="50%" valign="top"><table width="100%" border="0">
					<tr>
						<td>Lokasi Tanah</td>
					</tr>
					<tr>
						<td><textarea name="txtLokasi" id="txtLokasi" cols="50%" rows="2" onKeyUp="textCounter(this.form.txtLokasi,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtLokasi,this.form.remLen2,400);" ></textarea></td>
					</tr>
					
					<tr>
						<td>Keadaan Rupabumi</td>
					</tr>
					<tr>
						<td><textarea name="txtRupabumi" id="txtRupabumi" cols="50%" rows="2" onKeyUp="textCounter(this.form.txtRupabumi,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtRupabumi,this.form.remLen2,400);" ></textarea></td>
					</tr>
					
					<tr>
						<td>Perihal Bangunan</td>
					</tr>
					<tr>
						<td><textarea name="txtPerihal" id="txtPerihal" cols="50%" rows="2" onKeyUp="textCounter(this.form.txtPerihal,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtPerihal,this.form.remLen2,400);" ></textarea></td>
					</tr>
				
					<tr>
						<td>Kemudahan Awam</td>
					</tr>
					<tr>
						<td><textarea name="txtKemudahan" id="txtKemudahan" cols="50%" rows="2" onKeyUp="textCounter(this.form.txtKemudahan,this.form.remLen3,400);" onKeyDown="textCounter(this.form.txtKemudahan,this.form.remLen3,400);" ></textarea></td>
					</tr>
				</table></td>
				
			</tr>
		</table>
		
		<table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian2</td>
        	</tr>
        </table>
        
	</fieldset>
	#end


	#if($mode=="view")

		#foreach($data in $dataLaporanTanah)	
		#set($txtKemudahan=$data.kemudahan_awam)
		#set($txtSimpanMelayu=$data.perihal_kawasan_simpan)
		#set($txtLainlain=$data.perihal_kawasan_lain2)	
		#set($txtKawasanMajlis=$data.perihal_kawasan_majlis)
		#set($txtNoSyit=$data.perihal_syit)
		#set($txdTarikhMula=$data.tarikh_mula_chart)
		#set($txdTarikhTamat=$data.tarikh_akhir_chart)
		#set($txdTarikhLawat=$data.tarikh_lawatan)
		#set($txtBilKeseluruhan=$data.bil_hakmilik)
		#set($txtLuasKeseluruhan=$data.luas_terlibat)
		#set($txtPerihal=$data.perihal_bangunan)
		#set($txtKeadaanTanah=$data.keadaan_rupabumi)
		#set($txtLokasi=$data.lokasi_tanah)
		#set($txtSyor=$data.ulasan_syor)		
		#set($txtNoPeta=$data.no_peta_kadaster)
		#set($txtLaporanTanah=$data.laporan_tanah)
		#set($txtPendahuluan=$data.pendahuluan)
		#set($sorDropdownUnitAsal=$data.id_unitluas)
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
				<td width="54%" valign="top"><table width="100%" border="0">
					
					<tr>
						<td width="1%" valign="top">#if($isEdit=="yes")<font color="red">*</font>#end</td>
						<td width="37%" valign="top">No. Syit Piawai</td>
						<td width="1%" valign="top">:</td>
						<td width="61%"><textarea $disability $disabilityx name="txtNoSyit" id="txtNoSyit" cols="30%" rows="1"   onKeyUp="textCounter(this.form.txtNoSyit,this.form.remLen11,400);" onKeyDown="textCounter(this.form.txtNoSyit,this.form.remLen11,400);" >$!txtNoSyit</textarea></td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td>No. Peta Kadaster</td>
						<td>:</td>
						<td><input type="text" $disability $disabilityx name="txtNoPeta" id="txtNoPeta" value="$!txtNoPeta" maxlength="100"   size="33"  /></td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td>Bil Keseluruhan Lot</td>
						<td>:</td>
						<td><input type="text" $disability $disabilityx name="txtBilKeseluruhan" id="txtBilKeseluruhan" value="$!txtBilKeseluruhan"   size="7" onkeyup="validateNumber(this,this.value)" /></td>
					</tr>
				
					<tr>
						<td>&nbsp;</td>
						<td>Luas Keseluruhan</td>
						<td>:</td>
						<td><input type="text" $disability $disabilityx name="txtLuasKeseluruhan" id="txtLuasKeseluruhan" value="$!txtLuasKeseluruhan"   size="15" onkeyup="validateNumber(this,this.value)" />
							
							<select $disability1 $disabilityx name="sorDropdownUnitAsal" style="width:132px">
      		
      						#if($sorDropdownUnitAsal=="1")
      						<option value="1">HEKTAR</option>
							<option value="2">METER PERSEGI</option>	
							<option value="">SILA PILIH</option>
      						#elseif($sorDropdownUnitAsal=="2")
      						<option value="2">METER PERSEGI</option>
      						<option value="1">HEKTAR</option>
      						<option value="">SILA PILIH</option>
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
				<td width="46%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="44%">Tarikh Mula Charting</td>
						<td width="1%">:</td>
						<td width="54%"><input $disability $disabilityx name="txdTarikhMula" value="$!txdTarikhMula" size="10" id="txdTarikhMula" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMula',false,'dmy');">$!frmtdate#end</td>
					</tr>
				
					<tr>
						<td>&nbsp;</td>
						<td>Tarikh Tamat Charting</td>
						<td>:</td>
						<td><input $disability $disabilityx name="txdTarikhTamat" value="$!txdTarikhTamat" size="10" id="txdTarikhTamat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTamat',false,'dmy');">$!frmtdate#end</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td>Tarikh Lawat Tapak</td>
						<td>:</td>
						<td><input $disability $disabilityx name="txdTarikhLawat" value="$!txdTarikhLawat" size="10" id="txdTarikhLawat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawat',false,'dmy');">$!frmtdate#end</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td colspan="3">Laporan Atas Tanah</td>
					</tr>
						
					<tr>
						<td>&nbsp;</td>
						<td colspan="3"><textarea $disability $disabilityx name="txtLaporanTanah" id="txtLaporanTanah" cols="45%" rows="3" onKeyUp="textCounter(this.form.txtLaporanTanah,this.form.remLen12,4000);" onKeyDown="textCounter(this.form.txtLaporanTanah,this.form.remLen12,4000);" >$!txtLaporanTanah</textarea></td>
					</tr>
					
				</table></td>
				
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
						<td>Kawasan Majlis Daerah / Perbandaran</td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtKawasanMajlis" id="txtKawasanMajlis" cols="48%" rows="2" onKeyUp="textCounter(this.form.txtKawasanMajlis,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtKawasanMajlis,this.form.remLen2,400);" >$!txtKawasanMajlis</textarea></td>
					</tr>
					
					<tr>
						<td>Kawasan Simpanan Melayu</td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtSimpanMelayu" id="txtSimpanMelayu" cols="48%" rows="2" onKeyUp="textCounter(this.form.txtSimpanMelayu,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtSimpanMelayu,this.form.remLen2,400);" >$!txtSimpanMelayu</textarea></td>
					</tr>
					
					<tr>
						<td>Lain-lain</td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtLainlain" id="txtLainlain" cols="48%" rows="2" onKeyUp="textCounter(this.form.txtLainlain,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtLainlain,this.form.remLen2,400);" >$!txtLainlain</textarea></td>
					</tr>
					
					
					<tr>
						<td>Hal-hal lain / Ulasan / Pandangan</td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtUlasan" id="txtUlasan" cols="48%" rows="2" onKeyUp="textCounter(this.form.txtUlasan,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtUlasan,this.form.remLen2,400);" >$!txtSyor</textarea></td>
					</tr>
					
				</table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="50%" valign="top"><table width="100%" border="0">
					<tr>
						<td>Lokasi Tanah</td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtLokasi" id="txtLokasi" cols="50%" rows="2" onKeyUp="textCounter(this.form.txtLokasi,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtLokasi,this.form.remLen2,400);" >$!txtLokasi</textarea></td>
					</tr>
					
					<tr>
						<td>Keadaan Rupabumi</td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtRupabumi" id="txtRupabumi" cols="50%" rows="2" onKeyUp="textCounter(this.form.txtRupabumi,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtRupabumi,this.form.remLen2,400);" >$!txtKeadaanTanah</textarea></td>
					</tr>
					
					<tr>
						<td>Perihal Bangunan</td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtPerihal" id="txtPerihal" cols="50%" rows="2" onKeyUp="textCounter(this.form.txtPerihal,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtPerihal,this.form.remLen2,400);" >$!txtPerihal</textarea></td>
					</tr>
				
					<tr>
						<td>Kemudahan Awam</td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtKemudahan" id="txtKemudahan" cols="50%" rows="2" onKeyUp="textCounter(this.form.txtKemudahan,this.form.remLen3,400);" onKeyDown="textCounter(this.form.txtKemudahan,this.form.remLen3,400);" >$!txtKemudahan</textarea></td>
					</tr>
				</table></td>
				
			</tr>
		</table>
		
		#if($isEdit=="yes")
		<table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian2</td>
        	</tr>
        </table>
		#end
		
	</fieldset>
	#end

	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($mode=="new")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanLaporan('$!id_permohonan','0','$!id_tanahumum')">
				#end
				
				#if($mode=="view")
					#if($isEdit=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniLaporan('$!id_tanahumum')">
					<input type="button" name="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport1')" />
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanLaporan('$!id_permohonan','1','$!id_tanahumum')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batal('$id_permohonan')">
					#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="kembali()">
			</td>
		</tr>
	</table>

</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>  
      	<td><a href="#" onClick="javascript:cetakLaporanTanah('$!id_permohonan','$!id_tanahumum')"><font color="blue">Laporan Awal Tanah</font></a></td>
      </tr>     
    </table>
</fieldset>	

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="id_tanahumum" value="$!id_tanahumum">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<script>
function cetakLaporanTanah(idpermohonan,id_tanahumum) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_tanahumum="+id_tanahumum+"&report=laporanTanahSS4&selectNoFail=yes";	
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
function batal(id_permohonan) {

	document.${formName}.ScreenLocation.value = "changeLaporan";
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4LaporanAwalTanahSenarai";
	document.${formName}.submit();
}
function kemaskiniLaporan(idTanahUmum) {

	document.${formName}.ScreenLocation.value = "changeLaporan";
	
	document.${formName}.id_tanahumum.value = idTanahUmum;
	document.${formName}.command.value = "semakLaporan";
	document.${formName}.command2.value = "kemaskiniLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4LaporanAwalTanahSenarai";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4LaporanAwalTanahSenarai";
	document.${formName}.submit();
}
function simpanLaporan(idpermohonan,mode,idTanahUmum) {

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh mula charting
	var TM  = document.getElementById("txdTarikhMula").value;
	var dt1   = parseInt(TM.substring(0,2),10);
   	var mon1  = parseInt(TM.substring(3,5),10);
   	var yr1   = parseInt(TM.substring(6,10),10);
   	var dateMulaC = new Date(yr1, mon1, dt1);

  	//tarikh tamat charting
	var TT  = document.getElementById("txdTarikhTamat").value;
	var dt2   = parseInt(TT.substring(0,2),10);
   	var mon2  = parseInt(TT.substring(3,5),10);
   	var yr2   = parseInt(TT.substring(6,10),10);
   	var dateTamatC = new Date(yr2, mon2, dt2);

    //tarikh lawat tapak
	var TL  = document.getElementById("txdTarikhLawat").value;
	var dt3   = parseInt(TL.substring(0,2),10);
   	var mon3  = parseInt(TL.substring(3,5),10);
   	var yr3   = parseInt(TL.substring(6,10),10);
   	var dateLawat = new Date(yr3, mon3, dt3);
	
	var dat1=document.${formName}.txdTarikhMula
	var dat2=document.${formName}.txdTarikhTamat
	var dat3=document.${formName}.txdTarikhLawat
	
	if(document.${formName}.txtNoSyit.value == ""){
		alert("Sila masukkan \" No. Syit Piawai \" terlebih dahulu.");
  		document.${formName}.txtNoSyit.focus(); 
		return;
	}
/*	else 
	if(document.${formName}.txdTarikhMula.value == ""){
		alert("Sila masukkan \" Tarikh Mula Charting \" terlebih dahulu.");
  		document.${formName}.txdTarikhMula.focus(); 
		return;
	}
	else 
	if(dateMulaC < currentDate){
   		alert("Sila pastikan \"Tarikh Mula Charting\" tidak kurang dari tarikh hari ini.");
	 	document.${formName}.txdTarikhMula.focus();
	 	return;
	}
	else 
	if(document.${formName}.txdTarikhTamat.value == ""){
		alert("Sila masukkan \" Tarikh Tamat Charting \" terlebih dahulu.");
	  	document.${formName}.txdTarikhTamat.focus(); 
		return;
	}
	else 
	if(dateTamatC < currentDate){
		alert("Sila pastikan \"Tarikh Tamat Charting\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTamat.focus();
		return;
	}
*/	else 
	if(dateTamatC < dateMulaC){
		alert("Sila pastikan \"Tarikh Tamat Charting\" tidak kurang dari tarikh mula charting.");
		document.${formName}.txdTarikhTamat.focus();
		return;
	}
/*	else 
	if(document.${formName}.txdTarikhLawat.value == ""){
		alert("Sila masukkan \" Tarikh Lawat Tapak \" terlebih dahulu.");
	  	document.${formName}.txdTarikhLawat.focus(); 
		return;
	}
	else 
	if(dateLawat < currentDate){
		alert("Sila pastikan \"Tarikh Lawat Tapak\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhLawat.focus();
		return;
	}
*/	else if (dat1.value!="" && isDate(dat1.value)==false)
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
		document.${formName}.ScreenLocation.value = "changeLaporan";
		document.${formName}.id_permohonan.value = idpermohonan;	
		if(mode=="0"){
			document.${formName}.command.value = "tambahLaporan";
			document.${formName}.command2.value = "simpanLaporan";
		}else{
			document.${formName}.id_tanahumum.value = idTanahUmum;
			document.${formName}.command.value = "semakLaporan";
			document.${formName}.command2.value = "kemaskiniLaporan";
			document.${formName}.command3.value = "updateLaporan";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4LaporanAwalTanahSenarai";
		document.${formName}.submit();
	}
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