<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td colspan="2"><fieldset>
				<legend>MAKLUMAT JPPH</legend>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					#foreach ($beanMaklumatPejabat in $BeanMaklumatPejabat)
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="28%">Nama Pejabat</td>
						<td width="1%">:</td>
						<td width="70%">$beanMaklumatPejabat.namaPejabat <input
							type="hidden" name="idPejabatJPPH" id="idPejabatJPPH"
							value="$beanMaklumatPejabat.idPejabat" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Alamat</td>
						<td>:</td>
						<td>$beanMaklumatPejabat.alamat1</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>:</td>
						<td>$beanMaklumatPejabat.alamat2</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>:</td>
						<td>$beanMaklumatPejabat.alamat3</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Poskod</td>
						<td>:</td>
						<td>$beanMaklumatPejabat.poskod</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Bandar</td>
						<td>:</td>
						<td>$beanMaklumatPejabat.bandar</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Negeri</td>
						<td>:</td>
						<td>$beanMaklumatPejabat.negeri</td>
					</tr>
					#end #foreach ($beanMaklumatJPPH in $BeanMaklumatJPPH) #if
					($flagStatus == '1')
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="28%">Tarikh Hantar</td>
						<td width="1%">:</td>
						<td width="70%"><input name="txtTarikhHantar" type="text"
							$readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar"
							onBlur="check_date(this);calcDate()"
							value="$beanMaklumatJPPH.tarikhHantar" size="9" maxlength="10">
							#if ($modePopup != 'view') <a
							href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img
								border="0" src="../img/calendar.gif" />#end</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Jangkamasa</td>
						<td>:</td>
						<td><input type="text" name="txtJangkaMasa"
							id="txtJangkaMasa" size="1" maxlength="2"
							value="$beanMaklumatJPPH.jangkamasa"
							onBlur="validateNumber(this,this.value,'$beanMaklumatJPPH.jangkamasa');calcDate()"
							$readonlyPopup class="$inputTextClassPopup"> Hari</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Tarikh Dijangka Terima</td>
						<td>:</td>
						<td><input name="txtTarikhJangkaTerima" type="text"
							$readonlyPopup class="$inputTextClassPopup"
							id="txtTarikhJangkaTerima" onBlur="check_date(this)"
							value="$beanMaklumatJPPH.tarikhJangkaTerima" size="9"
							maxlength="10"> #if ($modePopup != 'view') <a
							href="javascript:displayDatePicker('txtTarikhJangkaTerima',false,'dmy');"><img
								border="0" src="../img/calendar.gif" />#end</td>
					</tr>
					#else
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="28%">Tarikh Hantar</td>
						<td width="1%">:</td>
						<td width="70%"><input name="txtTarikhHantar" type="text"
							readonly="readonly" class="disabled" id="txtTarikhHantar"
							value="$beanMaklumatJPPH.tarikhHantar" size="9" maxlength="10"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Jangkamasa</td>
						<td>:</td>
						<td><input type="text" name="txtJangkaMasa"
							id="txtJangkaMasa" size="1" maxlength="2"
							value="$beanMaklumatJPPH.jangkamasa" readonly="readonly"
							class="disabled"> Hari</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Tarikh Dijangka Terima</td>
						<td>:</td>
						<td><input name="txtTarikhJangkaTerima" type="text"
							readonly="readonly" class="disabled" id="txtTarikhJangkaTerima"
							value="$beanMaklumatJPPH.tarikhJangkaTerima" size="9"
							maxlength="10"></td>
					</tr>
					#end
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					#if ($flagStatus == '2')
					<tr>
						<td colspan="4"><fieldset>
								<legend>MAKLUMBALAS</legend>
								<table width="100%" border="0" cellspacing="2" cellpadding="2">
									<tr>
										<td width="1%">&nbsp;</td>
										<td width="28%">Tarikh Terima</td>
										<td width="1%">:</td>
										<td width="70%"><input name="txtTarikhTerima" type="text"
											$readonlyPopup class="$inputTextClassPopup"
											id="txtTarikhTerima" onBlur="check_date(this)"
											value="$beanMaklumatJPPH.tarikhTerima" size="9"
											maxlength="10"> #if ($modePopup != 'view') <a
											href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img
												border="0" src="../img/calendar.gif" />#end</td>
									</tr>
									<tr>
										<td width="1%">&nbsp;</td>
										<td width="28%">Tarikh Surat</td>
										<td width="1%">:</td>
										<td width="70%"><input name="txtTarikhSurat" type="text"
											$readonlyPopup class="$inputTextClassPopup"
											id="txtTarikhSurat" onBlur="check_date(this)"
											value="$beanMaklumatJPPH.tarikhSurat" size="9" maxlength="10">
											#if ($modePopup != 'view') <a
											href="javascript:displayDatePicker('txtTarikhSurat',false,'dmy');"><img
												border="0" src="../img/calendar.gif" />#end</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>No Rujukan Surat</td>
										<td>:</td>
										<td><input type="text" name="txtNoRujukan"
											id="txtNoRujukan" $readonlyPopup class="$inputTextClassPopup"
											size="50" value="$beanMaklumatJPPH.noRujukan"
											onblur="this.value=this.value.toUpperCase();" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Jenis Kadar Sewa</td>
										<td>:</td>
										<td><select name="socKadarSewa" id="socKadarSewa"
											style="width: 145px;" $readonlyPopup class="$inputTextClassPopup"
											onchange="javascript:doChangeJenisKadarSewa(this.value)" $disabled>
											#if ($beanMaklumatJPPH.jenisKadarSewa == 'B')
												<option>SILA PILIH</option>
												<option value="B" selected="selected">BULANAN</option>
												<option value="M">SEMUSIM</option>
												<option value="T">SEUNIT</option>
												<option value="S">SEKALIGUS</option>
											#elseif ($beanMaklumatJPPH.jenisKadarSewa == 'M')
												<option>SILA PILIH</option>
												<option value="B">BULANAN</option>
												<option value="M" selected="selected">SEMUSIM</option>
												<option value="T">SEUNIT</option>
												<option value="S">SEKALIGUS</option>
											#elseif ($beanMaklumatJPPH.jenisKadarSewa == 'T')
												<option>SILA PILIH</option>
												<option value="B">BULANAN</option>
												<option value="M">SEMUSIM</option>
												<option value="T" selected="selected">SEUNIT</option>
												<option value="S">SEKALIGUS</option>
											#elseif ($beanMaklumatJPPH.jenisKadarSewa == 'S')
												<option>SILA PILIH</option>
												<option value="B">BULANAN</option>
												<option value="M">SEMUSIM</option>
												<option value="T">SEUNIT</option>
												<option value="S" selected="selected">SEKALIGUS</option>
											#else
												<option selected="selected">SILA PILIH</option>
												<option value="B">BULANAN</option>
												<option value="M">SEMUSIM</option>
												<option value="T">SEUNIT</option>
												<option value="S">SEKALIGUS</option>
											#end
										</select></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Kadar Sewa (RM)</td>
										<td>:</td>
										<td><input name="txtKadarSewaBulan" type="text"
											value="$beanMaklumatJPPH.kadarSewaBulan" $readonlyPopup
											class="$inputTextClassPopup"
											onblur="validateCurrency(this,this.value,'$beanMaklumatJPPH.kadarSewaBulan')" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>Kadar Sewa Bagi 3 Tahun (RM)</td>
										<td>:</td>
										<td><input name="txtKadarSewaTahun" type="text"
											value="$beanMaklumatJPPH.kadarSewaTahun" $readonlyPopup
											class="$inputTextClassPopup"
											onblur="validateCurrency(this,this.value,'$beanMaklumatJPPH.kadarSewaTahun')" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td valign="top">Ulasan</td>
										<td valign="top">:</td>
										<td valign="top"><textarea name="txtUlasan"
												id="txtUlasan" rows="5" cols="50" $readonlyPopup
												class="$inputTextClassPopup">$beanMaklumatJPPH.ulasan</textarea></td>
									</tr>
									#if ($modePopup == 'view')
									<tr>
										<td>&nbsp;</td>
										<td valign="top">&nbsp;</td>
										<td valign="top">&nbsp;</td>
										<td valign="top">&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td valign="top">Lampiran</td>
										<td valign="top">:</td>
										<td valign="top">#if ($idLampiran != '')<a
											href="javascript:cetakLampiran($idLampiran)" class="style2">$namaFailLampiran</a>
											#end &nbsp;&nbsp;&nbsp;&nbsp; <input name="cmdUpload"
											type="button" onclick="uploadDoc($idUlasanTeknikal)"
											value="Muat Naik Lampiran" /></td>
									</tr>
									#end
								</table>
							</fieldset></td>
					</tr>
					#end #end
				</table>
			</fieldset></td>
	</tr>
	#if ($modePopup != 'view')
	<tr>
		<td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font>
				: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
	</tr>
	#end
	<tr>
		<td width="30%">&nbsp;</td>
		<td width="70%">#if ($modePopup == 'new') <input name="cmdSimpan"
			type="button" onClick="doSimpanMaklumatJPPH()" value="Simpan">
			<input name="cmdBatal" type="button" onClick="doBatalMaklumatJPPH()"
			value="Batal"> #end #if ($modePopup == 'newUlangan') <input
			name="cmdSimpan" type="button"
			onClick="doSimpanMaklumatUlanganJPPH()" value="Simpan"> <input
			name="cmdBatal" type="button"
			onClick="doBatalKemaskiniMaklumatJPPH()" value="Batal"> #end
			#if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' ||
			$!{session.getAttribute("FLAG_FROM")} == 'failHQ') #if ($modePopup ==
			'view') #if ($aktif != 'T') <input name="cmdKemaskini" type="button"
			onClick="doKemaskiniMaklumatJPPH()" value="Kemaskini"> <input
			name="cmdHapus" type="button" onClick="doHapusMaklumatJPPH()"
			value="Hapus"> #if ($flagStatus == '1') <input
			name="cmdTerima" type="button" onClick="doTerimaJPPH()"
			value="Terima"> <input name="cmdUlangan" type="button"
			onClick="doUlanganJPPH()" value="Ulangan"> #end #end #end
			#end #if ($modePopup == 'update') <input name="cmdSimpan2"
			type="button" onclick="doSimpanKemaskiniMaklumatJPPH()"
			value="Simpan" /> <input name="cmdBatal" type="button"
			onClick="doBatalKemaskiniMaklumatJPPH()" value="Batal"> #end
		</td>
	</tr>
</table>
