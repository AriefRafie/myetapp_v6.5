<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>

#set($saizTxtPerkara="1000")
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'> 
	<input name="actionPenyewaan" type="hidden" id="actionPenyewaan" value="$actionPenyewaan" /> 
	<input name="mode" type="hidden" id="mode" value="$mode" /> 
	<input name="hitButton" type="hidden" id="hitButton" /> 
	<input name="idFail" type="hidden" id="idFail" value="$idFail" /> 
	<input name="idStatus" type="hidden" id="idStatus" value="$idStatus" /> 
	<input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi" /> 
	<input type="hidden" name="idHakmilikSementara" id="idHakmilikSementara" value="$idHakmilikSementara" /> 
	<input type="hidden" name="idPHPBorangK" id="idPHPBorangK" value="$idPHPBorangK" /> 
	<input type="hidden" name="idPPTBorangK" id="idPPTBorangK" value="$idPPTBorangK" /> 
	<input type="hidden" name="idHakmilikUrusan" id="idHakmilikUrusan" value="$idHakmilikUrusan" /> 
	<input type="text" name="idJenisPermohonan" id="idJenisPermohonan" value="$idJenisPermohonan" /> 
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td colspan="2"><fieldset>
				<legend>
					<strong>MAKLUMAT PEMOHON</strong>
				</legend>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end
						</td>
						<td width="28%">Kategori Pemohon</td>
						<td>:</td>
						<td width="70%">$selectKategoriPemohon</td>
					</tr>
					#foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon) #if
					($idKategoriPemohon == '1')
					<tr>
						<td>#if ($mode != 'view')<span class="style1">*</span>#end
						</td>
						<td>Nama</td>
						<td>:</td>
						<td><input name="txtNama" type="text" class="$inputTextClass"
							id="txtNama" value="$beanMaklumatPemohon.nama" size="43"
							maxlength="80" $readonly
							onblur="this.value=this.value.toUpperCase();" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>No. Kad Pengenalan/<em>MyID</em></td>
						<td>:</td>
						<td><input name="txtNoPendaftaran" type="text"
							class="$inputTextClass" id="txtNoPendaftaran"
							value="$beanMaklumatPemohon.noPendaftaran" maxlength="12"
							$readonly onblur="this.value=this.value.toUpperCase();" /></td>
					</tr>
					<tr>
						<td valign="top">&nbsp;</td>
						<td valign="top">Pekerjaan</td>
						<td valign="top">:</td>
						<td valign="top"><textarea name="txtPekerjaan" rows="5"
								cols="50" class="$inputTextClass" id="txtPekerjaan"
								onblur="this.value=this.value.toUpperCase();"
								$readonly="$readonly">$beanMaklumatPemohon.pekerjaan</textarea>
						</td>
					</tr>
					#end #if ($idKategoriPemohon == '2')
					<tr>
						<td>#if ($mode != 'view')<span class="style1">*</span>#end
						</td>
						<td>Nama Syarikat</td>
						<td>:</td>
						<td><input name="txtNama" type="text" class="$inputTextClass"
							id="txtNama" value="$beanMaklumatPemohon.nama" size="43"
							maxlength="80" $readonly
							onblur="this.value=this.value.toUpperCase();" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Nama Pegawai</td>
						<td>:</td>
						<td><input name="txtNamaPegawai" type="text"
							class="$inputTextClass" id="txtNamaPegawai"
							value="$beanMaklumatPemohon.namaPegawai" size="43" maxlength="80"
							$readonly onblur="this.value=this.value.toUpperCase();" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>No. Pendaftaran Syarikat/<em>MyCoid</em></td>
						<td>:</td>
						<td><input name="txtNoPendaftaran" type="text"
							class="$inputTextClass" id="txtNoPendaftaran"
							value="$beanMaklumatPemohon.noPendaftaran" maxlength="12"
							$readonly onblur="this.value=this.value.toUpperCase();" /></td>
					</tr>
					<tr>
						<td valign="top">&nbsp;</td>
						<td valign="top">Jenis Perniagaan</td>
						<td valign="top">:</td>
						<td valign="top"><textarea name="txtPekerjaan" rows="5"
								cols="50" class="$inputTextClass" id="txtPekerjaan"
								onblur="this.value=this.value.toUpperCase();"
								$readonly="$readonly">$beanMaklumatPemohon.pekerjaan</textarea>
						</td>
					</tr>
					#end
					<tr>
						<td>&nbsp;</td>
						<td>Alamat</td>
						<td>:</td>
						<td><input name="txtAlamat1" type="text"
							class="$inputTextClass" id="txtAlamat1"
							value="$beanMaklumatPemohon.alamat1" size="43" maxlength="80"
							$readonly onblur="this.value=this.value.toUpperCase();" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><input name="txtAlamat2" type="text"
							class="$inputTextClass" id="txtAlamat2"
							value="$beanMaklumatPemohon.alamat2" size="43" maxlength="80"
							$readonly onblur="this.value=this.value.toUpperCase();" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><input name="txtAlamat3" type="text"
							class="$inputTextClass" id="txtAlamat3"
							value="$beanMaklumatPemohon.alamat3" size="43" maxlength="80"
							$readonly onblur="this.value=this.value.toUpperCase();" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Poskod</td>
						<td>:</td>
						<td><input name="txtPoskod" type="text"
							class="$inputTextClass" id="txtPoskod"
							value="$beanMaklumatPemohon.poskod" size="5" maxlength="5"
							$readonly onblur="validateLength(this.value);" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Negeri</td>
						<td>:</td>
						<td>$selectNegeri</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Bandar</td>
						<td>:</td>
						<td>$selectBandar</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>E-mel</td>
						<td>:</td>
						<td><input name="txtEmel" type="text" class="$inputTextClass"
							id="txtEmel" value="$beanMaklumatPemohon.emel" maxlength="50"
							$readonly /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>No. Tel</td>
						<td>:</td>
						<td><input name="txtNoTel" type="text"
							class="$inputTextClass" id="txtNoTel"
							onkeyup="validateNumber(this,this.value);"
							value="$beanMaklumatPemohon.noTel" maxlength="12" $readonly /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>No. Faks</td>
						<td>:</td>
						<td><input name="txtNoFaks" type="text"
							class="$inputTextClass" id="txtNoFaks"
							onkeyup="validateNumber(this,this.value);"
							value="$beanMaklumatPemohon.noFaks" maxlength="12" $readonly /></td>
					</tr>
					#end
				</table>
			</fieldset></td>
	</tr>
	<tr>  
  		<td colspan="2"><fieldset>
  			<legend><strong>JENIS PERMOHONAN</strong></legend>
  			<table width="100%" border="0" cellspacing="2" cellpadding="2">
  				<tr>
  					<td width="1%"><span class="style1">*</span></td>
    				<td width="28%">Jenis Permohonan</td>
       				<td width="1%">:</td>
        			<td width="70%">
        				<select name="socJenisPermohonan"
							id="socJenisPermohonan" onchange="doChangeJenisPermohonan()"
							$inputTextClass class="$inputTextClass">
							<option $selected value="0">SILA PILIH</option>
							<option $selected1 value="1">PERMOHONAN BARU</option>
							<option $selected2 value="2">PERMOHONAN PERLANJUTAN</option>
							<option $selected3 value="3">PERMOHONAN PENGURANGAN KADAR SEWA</option>
						</select>
					</td>
  				</tr>
  				<tr>
  					<td width="1%"><span class="style1">*</span></td>
    				<td width="28%">Senarai No. Fail Lama</td>
       				<td width="1%">:</td>
        			<td width="70%">$selectNoFailLama</td>
  				</tr>
  			</table>
  		</td>
  	</tr>
  	<tr>
		<td colspan="2"><fieldset>
				<legend>
					<strong>MAKLUMAT TANAH</strong>
				</legend>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="28%">Jenis Tanah</td>
						<td width="1%">:</td>
						<td width="70%"><select name="socJenisTanah"
							id="socJenisTanah" onchange="doChangeJenisTanah()"
							$inputTextClass class="$inputTextClass">
								<option $selected value="0">SILA PILIH</option>
								<option $selected1 value="1">TANAH MILIK PERSEKUTUAN</option>
								<option $selected2 value="2">TANAH RIZAB PERSEKUTUAN</option>
								<option $selected3 value="3">BORANG K</option>
						</select></td>
					</tr>
					#if ($idJenisTanah == '1' || $idJenisTanah == '2') #foreach
					($beanMaklumatTanah in $BeanMaklumatTanah)
					<tr>
						<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end
						</td>
						<td width="28%">Pegangan Hakmilik</td>
						<td width="1%">:</td>
						<td width="70%">#if ($mode == 'new') <input type="text"
							name="txtPeganganHakmilik" id="txtPeganganHakmilik"
							value="$beanMaklumatTanah.peganganHakmilik"
							onblur="doChangePeganganHakmilik();" /> <input type="button"
							name="cmdPilih" id="cmdPilih" value="Pilih Tanah"
							onclick="pilihTanah()" /> #else <input type="text"
							name="txtPeganganHakmilik" id="txtPeganganHakmilik"
							value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly"
							class="disabled" /> #end <span class="style1">$errorPeganganHakmilik</span>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>No. Lot</td>
						<td>:</td>
						<td>$beanMaklumatTanah.lot <input type="hidden"
							name="noLotTanah" id="noLotTanah" value="$beanMaklumatTanah.lot" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Luas Lot</td>
						<td>:</td>
						<td>$beanMaklumatTanah.luas <input type="hidden"
							name="idLuasTanah" id="idLuasTanah"
							value="$beanMaklumatTanah.idLuas" /> <input type="hidden"
							name="luasTanah" id="luasTanah"
							value="$beanMaklumatTanah.luasBersamaan" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>No. Hakmilik</td>
						<td>:</td>
						<td>$beanMaklumatTanah.hakmilik <input type="hidden"
							name="noMilikTanah" id="noMilikTanah"
							value="$beanMaklumatTanah.hakmilik" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>No. Warta</td>
						<td>:</td>
						<td>$beanMaklumatTanah.noWarta <input type="hidden"
							name="noWartaTanah" id="noWartaTanah"
							value="$beanMaklumatTanah.noWarta"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Tarikh Warta</td>
						<td>:</td>
						<td>$beanMaklumatTanah.tarikhWarta</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Mukim</td>
						<td>:</td>
						<td>$beanMaklumatTanah.mukim <input type="hidden"
							name="namaMukimTanah" id="namaMukimTanah"
							value="$beanMaklumatTanah.mukim" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Daerah</td>
						<td>:</td>
						<td>$beanMaklumatTanah.daerah <input type="hidden"
							name="namaDerahTanah" id="namaDerahTanah"
							value="$beanMaklumatTanah.daerah" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Negeri</td>
						<td>:</td>
						<td>$beanMaklumatTanah.negeri <input type="hidden"
							name="idNegeriTanah" id="idNegeriTanah"
							value="$beanMaklumatTanah.idNegeri" /> <input type="hidden"
							name="namaNegeriTanah" id="namaNegeriTanah"
							value="$beanMaklumatTanah.negeri" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Kementerian</td>
						<td>:</td>
						<td>$beanMaklumatTanah.kementerian <input type="hidden"
							name="idKementerianTanah" id="idKementerianTanah"
							value="$beanMaklumatTanah.idKementerian" /></td>
					</tr>
					<tr>
						<td height="31">&nbsp;</td>
						<td>Agensi</td>
						<td>:</td>
						<td>$beanMaklumatTanah.agensi</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Kegunaan Tanah</td>
						<td>:</td>
						<td>$beanMaklumatTanah.kegunaanTanah <input type="hidden"
							name="kegunaanTanah" id="kegunaanTanah"
							value="$beanMaklumatTanah.kegunaanTanah" /> <input type="hidden"
							name="statusRizab" id="statusRizab"
							value="$beanMaklumatTanah.statusRizab" /></td>
					</tr>
					<tr>
						<td>#if ($mode == 'update')<span class="style1">*</span>#end
						</td>
						<td>Luas Kegunaan</td>
						<td>:</td>
						<td>$selectLuasKegunaan</td>
					</tr>
					#end #end #if ($idJenisTanah == '3')
					<tr>
						<td colspan="4"><fieldset>
								<legend>
									<strong>Maklumat Lot</strong>
								</legend>
								<table width="100%" border="0" cellspacing="2" cellpadding="2">
									#foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
									<tr>
										<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end
										</td>
										<td width="28%">Pegangan Hakmilik</td>
										<td width="1%">:</td>
										<td width="70%">#if ($mode == 'new') <input type="text"
											name="txtPeganganHakmilik" id="txtPeganganHakmilik"
											value="$beanMaklumatBorangK.peganganHakmilik"
											onblur="doChangePeganganHakmilikBorangK();" /> <input
											type="button" name="cmdPilih" id="cmdPilih"
											value="Pilih Borang K" onclick="pilihBorangK()" /> #else <input
											type="text" name="txtPeganganHakmilik"
											id="txtPeganganHakmilik"
											value="$beanMaklumatBorangK.peganganHakmilik"
											readonly="readonly" class="disabled" /> #end <span
											class="style1">$errorPeganganHakmilik</span>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>No. Lot</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.lot</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Luas Lot</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.luas <input type="hidden"
											name="idLuasTanah" id="idLuasTanah"
											value="$beanMaklumatBorangK.idLuas"> <input
											type="hidden" name="luasTanah" id="luasTanah"
											value="$beanMaklumatBorangK.luasBersamaan"></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>No. Hakmilik</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.hakmilik</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Mukim</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.mukim</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Daerah</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.daerah</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Negeri</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.negeri <input type="hidden"
											name="idNegeriTanah" id="idNegeriTanah"
											value="$beanMaklumatBorangK.idNegeri"></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Kementerian</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.kementerian <input type="hidden"
											name="idKementerianTanah" id="idKementerianTanah"
											value="$beanMaklumatBorangK.idKementerian"></td>
									</tr>
									<tr>
										<td height="31">&nbsp;</td>
										<td>Agensi</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.agensi</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Kegunaan Tanah</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.kegunaanTanah</td>
									</tr>
									#end
								</table>
							</fieldset></td>
					</tr>
					<tr>
						<td colspan="4"><fieldset>
								<legend>
									<strong>Maklumat Borang K</strong>
								</legend>
								<table width="100%" border="0" cellspacing="2" cellpadding="2">
									#foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
									<tr>
										<td width="1%">&nbsp;</td>
										<td width="28%">Tarikh Borang K</td>
										<td width="1%">:</td>
										<td width="70%">$beanMaklumatBorangK.tarikhBorangK</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Catatan</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.catatan</td>
									</tr>
									#end
								</table>
							</fieldset></td>
					</tr>
					<tr>
						<td colspan="4"><fieldset>
								<legend>
									<strong>Maklumat Rekod Endosan Borang K</strong>
								</legend>
								<table width="100%" border="0" cellspacing="2" cellpadding="2">
									#foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
									<tr>
										<td width="1%">&nbsp;</td>
										<td width="28%">No. Perserahan</td>
										<td width="1%">:</td>
										<td width="70%">$beanMaklumatBorangK.noPerserahan</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Tarikh Catatan Dibuat</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.tarikhCatatan</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Tarikh Terima</td>
										<td>:</td>
										<td>$beanMaklumatBorangK.tarikhTerima</td>
									</tr>
									#end
								</table>
							</fieldset></td>
					</tr>
					#end #if ($idJenisTanah == '4') #foreach ($beanMaklumatTanah in
					$BeanMaklumatTanah)
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="28%">Status Tanah</td>
						<td width="1%">:</td>
						<td width="70%"><select name="socStatusTanah"
							id="socStatusTanah" onchange="doChangeStatusTanah()"
							$inputTextClass class="$inputTextClass">
								<option $selected value="0">SILA PILIH</option>
								<option $selected1 value="1">Tanah Kerajaan Negeri</option>
								<option $selected2 value="2">Tanah Milik Individu</option>
								<option $selected3 value="3">Tanah Serahan Pemaju</option>
								<option $selected4 value="4">Tanah Rizab Negeri</option>
								<option $selected5 value="5">Tanah Pajakan</option>
								<option $selected6 value="6">Tanah Penswastaan</option>
								<option $selected7 value="7">Lain-Lain</option>
						</select></td>
					</tr>
					<tr>
						<td width="30%">&nbsp;</td>
						<td width="70%">#if ($mode == 'new') <input type="button"
							name="cmdTolakRingkas" id="cmdTolakRingkas" value="Tolak Ringkas"
							onclick="doTolakRingkas()" /> <input type="button" name="cetak"
							id="cetak" value="Jana Surat" onclick="cetak()" /> #end
						</td>
					</tr>
					#end #end
				</table>
			</fieldset></td>
	</tr>
	<tr>
		<td colspan="2"><fieldset>
				<legend>
					<strong>MAKLUMAT PERMOHONAN</strong>
				</legend>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="28%" valign="top">No. Fail</td>
						<td width="1%">:</td>
						<td width="70%"><strong>$beanMaklumatPermohonan.noFail</strong>
							<input name="idPermohonan" type="hidden"
							value="$beanMaklumatPermohonan.idPermohonan" /> <input
							name="idPemohon" type="hidden"
							value="$beanMaklumatPermohonan.idPemohon" /></td>
					</tr>
					<tr>
						<td width="1%">&nbsp;</td>
						<td valign="top">No. Fail Negeri</td>
						<td>:</td>
						<td><input name="txtNoFailNegeri" type="text"
							class="$inputTextClass" id="txtNoFailNegeri"
							value="$beanMaklumatPermohonan.noFailNegeri" $readonly
							onblur="this.value=this.value.toUpperCase();" size="50"
							maxlength="50" /></td>
					</tr>
					<tr>
						<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end
						</td>
						<td valign="top">Urusan</td>
						<td>:</td>
						<td>$selectUrusan</td>
					</tr>
					<tr>
						<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end
						</td>
						<td valign="top">Suburusan</td>
						<td>:</td>
						<td>$selectSuburusan</td>
					</tr>
					<tr>
						<td width="1%" valign="top">#if ($mode != 'view')<span
							class="style1">*</span>#end
						</td>
						<td valign="top">Tujuan</td>
						<td valign="top">:</td>
						<td>$selectJenisTujuan</td>
					</tr>
					#if($idJenisTujuan == '32' || $idJenisTujuan == '38' ||
					$idJenisTujuan == '46' || $idJenisTujuan == '52')
					<tr>
						<td width="1%" valign="top">#if ($mode != 'view')<span
							class="style1">*</span>#end
						</td>
						<td valign="top">Lain - Lain Tujuan</td>
						<td valign="top">:</td>
						<td><textarea name="txtTujuan" id="txtTujuan" rows="5"
								cols="50" $readonly class="$inputTextClass"
								onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tujuan</textarea></td>
					</tr>
					#end
					<tr>
						<td width="1%">&nbsp;</td>
						<td valign="top">Tujuan 2</td>
						<td valign="top">:</td>
						<td>$selectJenisTujuan2</td>
					</tr>
					#if($idJenisTujuan2 == '32' || $idJenisTujuan2 == '38' ||
					$idJenisTujuan2 == '46' || $idJenisTujuan2 == '52')
					<tr>
						<td width="1%" valign="top">#if ($mode != 'view')<span
							class="style1">*</span>#end
						</td>
						<td valign="top">Lain - Lain Tujuan 2</td>
						<td valign="top">:</td>
						<td><textarea name="txtTujuan2" id="txtTujuan2" rows="5"
								cols="50" $readonly class="$inputTextClass"
								onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tujuan2</textarea></td>
					</tr>
					#end
					<tr>
						<td width="1%">&nbsp;</td>
						<td valign="top">Tujuan 3</td>
						<td valign="top">:</td>
						<td>$selectJenisTujuan3</td>
					</tr>
					#if($idJenisTujuan3 == '32' || $idJenisTujuan3 == '38' ||
					$idJenisTujuan3 == '46' || $idJenisTujuan3 == '52')
					<tr>
						<td width="1%" valign="top">#if ($mode != 'view')<span
							class="style1">*</span>#end
						</td>
						<td valign="top">Lain - Lain Tujuan 3</td>
						<td valign="top">:</td>
						<td><textarea name="txtTujuan3" id="txtTujuan3" rows="5"
								cols="50" $readonly class="$inputTextClass"
								onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tujuan3</textarea></td>
					</tr>
					#end
					<tr>
						<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end
						</td>
						<td valign="top">Tarikh Surat / Borang</td>
						<td>:</td>
						<td><input type="text" name="tarikhSurat" id="tarikhSurat"
							value="$beanMaklumatPermohonan.tarikhSurat"
							onblur="check_date(this);cekTarikhSurat(this)" size="9" $readonly
							class="$inputTextClass" /> #if ($mode != 'view')<a
							href="javascript:displayDatePicker('tarikhSurat',false,'dmy');"><img
								border="0" src="../img/calendar.gif" /></a>#end</td>
					</tr>
					<tr>
						<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end
						</td>
						<td valign="top">Tarikh Terima</td>
						<td>:</td>
						<td><input type="text" name="tarikhTerima" id="tarikhTerima"
							value="$beanMaklumatPermohonan.tarikhTerima"
							onblur="check_date(this);cekTarikhTerima(this)" size="9"
							$readonly class="$inputTextClass" /> #if ($mode != 'view') <a
							href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img
								border="0" src="../img/calendar.gif" /></a>#end</td>
					</tr>
					<tr>
						<td width="1%">&nbsp;</td>
						<td valign="top">No. Rujukan Surat</td>
						<td>:</td>
						<td><input name="txtNoRujukanSurat" type="text"
							class="$inputTextClass" id="txtNoRujukanSurat"
							value="$beanMaklumatPermohonan.noRujukanSurat" $readonly
							onblur="this.value=this.value.toUpperCase();" size="50"
							maxlength="50" /></td>
					</tr>
					<tr>
						<td width="1%" valign="top">#if ($mode != 'view')<span
							class="style1">*</span>#end
						</td>
						<td valign="top">Perkara</td>
						<td valign="top">:</td>
						<td><textarea name="txtPerkara" id="txtPerkara" rows="5"
								cols="50" $readonly class="$inputTextClass"
								onblur="this.value=this.value.toUpperCase();"
								onKeyUp="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizTxtPerkara);"
								onKeyDown="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizTxtPerkara);">$beanMaklumatPermohonan.perkara</textarea>
							#if ($mode == 'new') #if ($idJenisTanah == '1' || $idJenisTanah
							== '2') <input type="button" name="cmdDaftarBaru2"
							id="cmdDaftarBaru2" value="Jana Tajuk" onclick="janaTajuk()" />
							#end #end</td>
					</tr>
					#if ($mode != 'view')
					<tr>
						<td valign="top">&nbsp;</td>
						<td valign="top">&nbsp;</td>
						<td valign="top">&nbsp;</td>
						<td>Baki Aksara :&nbsp; <input type="text"
							readonly="readonly" class="disabled" name="remLen1" size="3"
							maxlength="3" value="$!saizTxtPerkara" /></td>
					</tr>
					#end
					<tr>
						<td valign="top">&nbsp;</td>
						<td valign="top">Catatan</td>
						<td valign="top">:</td>
						<td><textarea name="txtCatatan" id="txtCatatan" rows="5"
								cols="50" $readonly class="$inputTextClass"
								onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.catatan</textarea></td>
					</tr>
					#end
				</table>
			</fieldset></td>
	</tr>
	
	#if ($mode != 'view')
	<tr>
		<td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font>
				: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
	</tr>
	#end
	<tr>
		<td width="30%">&nbsp;</td>
		<td width="70%">#if ($mode == 'new') <input type="button"
			name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar"
			onclick="daftarBaru()" /> <input type="button" name="cmdBatal"
			id="cmdBatal" value="Batal" onclick="kembali()" /> #end #if ($mode
			== 'view') <input type="button" name="cmdSeterusnya"
			id="cmdSeterusnya" value="Seterusnya"
			onclick="seterusnya('$idStatus')" /> <input type="button"
			name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
			#end
		</td>
	</tr>
</table>
<script>
function doChangeJenisTanah() {
	doAjaxCall${formName}("doChangeJenisTanah");
}
function doChangeUrusan() {
	doAjaxCall${formName}("doChangeUrusan");
}
function doChangeKategori() {
	doAjaxCall${formName}("doChangeKategori");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeLuasKegunaan() {
	doAjaxCall${formName}("doChangeLuasKegunaan");
}
function doChangeSuburusan() {
	doAjaxCall${formName}("doChangeSuburusan");
}
function doChangeTujuan() {
	doAjaxCall${formName}("doChangeTujuan");
}
doChangeJenisPermohonan() {
	doAjaxCall${formName}("doChangeJenisPermohonan");
}
function pilihTanah() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupSenaraiTanahView";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensi, idHakmilikSementara) {
	document.${formName}.idHakmilikAgensi.value = idHakmilikAgensi;
	document.${formName}.idHakmilikSementara.value = idHakmilikSementara;
	doAjaxCall${formName}("doChangeMaklumatTanah");
}
function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}
function daftarBaru() {

	if(document.${formName}.socUrusan.value == ""){
		alert('Sila pilih Urusan.');
  		document.${formName}.socUrusan.focus(); 
		return; 
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih Suburusan.');
  		document.${formName}.socSuburusan.focus(); 
		return; 
	}
	if(document.${formName}.socJenisTujuan.value == ""){
		alert('Sila pilih Tujuan.');
  		document.${formName}.socJenisTujuan.focus(); 
		return; 
	}
	
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhTerima.focus(); 
		return;
	}
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat/Borang tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat/Borang tidak boleh melebihi dari Tarikh Terima.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if(document.${formName}.tarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.tarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat/Borang.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus(); 
		return; 
	}
	if(document.${formName}.socKategoriPemohon.value == ""){
		alert('Sila pilih Jenis Kategori Pemohon.');
  		document.${formName}.socKategoriPemohon.focus(); 
		return; 
	}
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukan Nama.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}		
	
	if(document.${formName}.socJenisTanah.value == "3"){
		if(document.${formName}.idPPTBorangK.value == "" && document.${formName}.idHakmilikUrusan.value == "" && document.${formName}.idPHPBorangK.value == ""){
			alert('Sila pilih Pegangan Hakmilik.');
			return; 
		}
	} else {		
		if(document.${formName}.idHakmilikAgensi.value == "" && document.${formName}.idHakmilikSementara.value == ""){
			alert('Sila pilih Pegangan Hakmilik.');
			return; 
		}
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPenyewaan.value = "daftarBaru";
		return;
	}
	
	document.${formName}.value = "papar";
	document.${formName}.hitButton.value = "daftarBaru";
	doAjaxCall${formName}("");
}
function kembali() {	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.actionPenyewaan.value = "";
	document.${formName}.submit();
}

function cekTarikhTerima(elmnt) {
//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var currentDate = new Date();
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		elmnt.value ="";
		document.${formName}.tarikhTerima.focus(); 
		return;
	}
}
function cekTarikhSurat(elmnt) {  
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
}
function validateLength(str) {	
	if (str.length < 5) {
 		alert("Sila Masukkan Poskod Dengan Betul.")
		document.${formName}.txtPoskod.value = "";
		return false
	}
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function seterusnya(idStatus){
	if (idStatus == '1610214'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWPerjanjianView";
	} else {
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWMaklumatPermohonanView";
	}	
	document.${formName}.submit();
}
function janaTajuk() {
	
		if(document.${formName}.socUrusan.value == ""){
			alert('Sila pilih Urusan Sebelum Menjana Tajuk.');
  			document.${formName}.socUrusan.focus(); 
			return; 
		}
		if(document.${formName}.socSuburusan.value == ""){
			alert('Sila pilih Suburusan Sebelum Menjana Tajuk.');
  			document.${formName}.socSuburusan.focus(); 
			return; 
		}
		if(document.${formName}.socJenisTujuan.value == ""){
			alert('Sila pilih Tujuan Sebelum Menjana Tajuk.');
  			document.${formName}.socJenisTujuan.focus(); 
			return; 
		}
		if(document.${formName}.socKategoriPemohon.value == ""){
			alert('Sila pilih Jenis Kategori Pemohon Sebelum Menjana Tajuk.');
  			document.${formName}.socKategoriPemohon.focus(); 
			return; 
		}
		if(document.${formName}.txtNama.value == ""){
			alert('Sila masukkan Nama Sebelum Menjana Tajuk.');
			document.${formName}.txtNama.focus(); 
			return; 
		} 
		if(document.${formName}.idHakmilikAgensi.value == "" && document.${formName}.idHakmilikSementara.value == ""){
			alert('Sila pilih Pegangan Hakmilik Sebelum Menjana Tajuk.');
			document.${formName}.idHakmilikAgensi.focus(); 
			return; 
		}
	
	var strTajuk = " ";
	var strTujuan = " ";
	var strTujuan2 = " ";
	var strTujuan3 = " ";
	var milikOrRizab = " ";
	var str1  = document.${formName}.noLotTanah.value;
	var str2  = document.${formName}.noMilikTanah.value;
	var str3  = document.${formName}.namaMukimTanah.value;
	var str4  = document.${formName}.namaDerahTanah.value;	
	var str5  = document.${formName}.namaNegeriTanah.value;	
	var namaPemohon = document.${formName}.txtNama.value;
	var str6 = document.${formName}.noWartaTanah.value;
	var statusRizabTnh = document.${formName}.statusRizab.value;
	
	if(document.${formName}.socJenisTujuan.value == '32' || document.${formName}.socJenisTujuan.value == '38' ||
			document.${formName}.socJenisTujuan.value == '46' || document.${formName}.socJenisTujuan.value == '52'){
		strTujuan = document.${formName}.txtTujuan.value;
	} else {
		strTujuan = document.${formName}.namatujuan.value;
	}
	
	if(document.${formName}.socJenisTujuan2.value == '32' || document.${formName}.socJenisTujuan2.value == '38' ||
			document.${formName}.socJenisTujuan2.value == '46' || document.${formName}.socJenisTujuan2.value == '52'){
		strTujuan2 = document.${formName}.txtTujuan2.value;
	} else {
		strTujuan2 = document.${formName}.namatujuan2.value;
	}
	
	if(document.${formName}.socJenisTujuan3.value == '32' || document.${formName}.socJenisTujuan3.value == '38' ||
			document.${formName}.socJenisTujuan3.value == '46' || document.${formName}.socJenisTujuan3.value == '52'){
		strTujuan3 = document.${formName}.txtTujuan3.value;
	} else {
		strTujuan3 = document.${formName}.namatujuan3.value;
	}
	
	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str6;
	}

	if(document.${formName}.socUrusan.value == "7"){
		if(document.${formName}.socSuburusan.value == "35"){
			if (document.${formName}.socJenisTujuan3.value == "" && document.${formName}.socJenisTujuan2.value == "") {
				strTajuk = "PERMOHONAN PENYEWAAN RUANG BANGUNAN " + str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan;
			} else if (document.${formName}.socJenisTujuan3.value == "" && document.${formName}.socJenisTujuan2.value != ""){
				strTajuk = "PERMOHONAN PENYEWAAN RUANG BANGUNAN " + str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan + " DAN " + strTujuan2;
			} else {
				strTajuk = "PERMOHONAN PENYEWAAN RUANG BANGUNAN " + str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan + ", " + strTujuan2 + " DAN " + strTujuan3;
			}
		} else {
			if (document.${formName}.socJenisTujuan3.value == "" && document.${formName}.socJenisTujuan2.value == "") {
				strTajuk = "PERMOHONAN PENYEWAAN TANAH PERSEKUTUAN " + str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan;
			} else if (document.${formName}.socJenisTujuan3.value == "" && document.${formName}.socJenisTujuan2.value != ""){
				strTajuk = "PERMOHONAN PENYEWAAN TANAH PERSEKUTUAN " + str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan + " DAN " + strTujuan2;
			} else {
				strTajuk = "PERMOHONAN PENYEWAAN TANAH PERSEKUTUAN " + str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan + ", " + strTujuan2 + " DAN " + strTujuan3;
			}
		}
	} else if(document.${formName}.socUrusan.value == "12"){
		if (document.${formName}.socJenisTujuan3.value == "" && document.${formName}.socJenisTujuan2.value == "") {
			strTajuk = "PERMOHONAN MENGELUARKAN HASIL " + strTujuan + " DI " +  str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan;
		} else if (document.${formName}.socJenisTujuan3.value == "" && document.${formName}.socJenisTujuan2.value != ""){
			strTajuk = "PERMOHONAN MENGELUARKAN HASIL " + strTujuan + " DI " +  str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan + " DAN " + strTujuan2;
		} else {
			strTajuk = "PERMOHONAN MENGELUARKAN HASIL " + strTujuan + " DI " +  str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan + ", " + strTujuan2 + " DAN " + strTujuan3;
		}
	} else if(document.${formName}.socUrusan.value == "13"){
		if (document.${formName}.socJenisTujuan3.value == "" && document.${formName}.socJenisTujuan2.value == "") {
			strTajuk = "PERMOHONAN MENGELUARKAN " + strTujuan + " DI " +  str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan;
		} else if (document.${formName}.socJenisTujuan3.value == "" && document.${formName}.socJenisTujuan2.value != ""){
			strTajuk = "PERMOHONAN MENGELUARKAN " + strTujuan + " DI " +  str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan + " DAN " + strTujuan2;
		} else {
			strTajuk = "PERMOHONAN MENGELUARKAN " + strTujuan + " DI " +  str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan + ", " + strTujuan2 + " DAN " + strTujuan3;
		}
	 } else{
		strTajuk;
	 }
		
	document.${formName}.txtPerkara.value = strTajuk;
}

function pilihBorangK() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupSenaraiBorangKView";
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihBorangK(idPPTBorangK,idHakmilikUrusan,idPHPBorangK) {
	
	document.${formName}.idPPTBorangK.value = idPPTBorangK;
	document.${formName}.idHakmilikUrusan.value = idHakmilikUrusan;
	document.${formName}.idPHPBorangK.value = idPHPBorangK;
	doAjaxCall${formName}("doChangeMaklumatBorangK");
}
function doChangePeganganHakmilikBorangK() {
	doAjaxCall${formName}("doChangePeganganHakmilikBorangK");
}
</script>
