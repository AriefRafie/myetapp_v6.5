#foreach ($beanMaklumatNotis in $BeanMaklumatNotis)
<input name="idUlasanTeknikalDokumen" type="hidden" id="idUlasanTeknikalDokumen" value="$beanMaklumatNotis.idUlasanTeknikal"/>
#end
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td colspan="2"><fieldset>
				<legend>MAKLUMAT DOKUMEN</legend>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
						</td>
						<td width="28%">Jenis Dokumen</td>
						<td width="1%">:</td>
						<td width="70%"><select name="jenisDokumen" id="jenisDokumen"
							style="width: 230px;" $readonlyPopup class="$inputTextClassPopup"
							$inputTextClassPopup onChange=""> #if($jenisDokumen ==
								'15')
								<option value="">SILA PILIH</option>
								<option value="15" selected="selected">SURAT TUNJUK
									SEBAB</option>
								<option value="16">SURAT PERINGATAN</option>
								<option value="17">SURAT PERINGATAN TERAKHIR</option>
								#elseif($jenisDokumen == '16')
								<option value="">SILA PILIH</option>
								<option value="15">SURAT TUNJUK SEBAB</option>
								<option value="16" selected="selected">SURAT PERINGATAN
								</option>
								<option value="17">SURAT PERINGATAN TERAKHIR</option>
								#elseif($jenisDokumen == '17')
								<option value="">SILA PILIH</option>
								<option value="15">SURAT TUNJUK SEBAB</option>
								<option value="16">SURAT PERINGATAN</option>
								<option value="17" selected="selected">SURAT PERINGATAN
									TERAKHIR</option> #else
								<option value="" selected="selected">SILA PILIH</option>
								<option value="15">SURAT TUNJUK SEBAB</option>
								<option value="16">SURAT PERINGATAN</option>
								<option value="17">SURAT PERINGATAN TERAKHIR</option> #end
						</select></td>
					</tr>
					<tr>
						<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
						</td>
						<td width="28%">Surat Ke</td>
						<td width="1%">:</td>
						<td width="70%"><select name="idSuratKe" id="idSuratKe"
							style="width: 100px;" $readonlyPopup class="$inputTextClassPopup"
							$inputTextClassPopup onChange=""> #if($idSuratKe ==
								'PTD')
								<option value="">SILA PILIH</option>
								<option value="PTD" selected="selected">1 - PTD</option>
								<option value="PTG">2 - PTG</option>
								<option value="JKPTG">3 - JKPTG</option> #elseif($idSuratKe ==
								'PTG')
								<option value="">SILA PILIH</option>
								<option value="PTD">1 - PTD</option>
								<option value="PTG" selected="selected">2 - PTG</option>
								<option value="JKPTG">3 - JKPTG</option> #elseif($idSuratKe ==
								'JKPTG')
								<option value="">SILA PILIH</option>
								<option value="PTD">1 - PTD</option>
								<option value="PTG">2 - PTG</option>
								<option value="JKPTG" selected="selected">3 - JKPTG</option>
								#else
								<option value="" selected="selected">SILA PILIH</option>
								<option value="PTD">1 - PTD</option>
								<option value="PTG">2 - PTG</option>
								<option value="JKPTG">3 - JKPTG</option> #end
						</select></td>
					</tr>

					<tr>
						<td>#if ($modePopup != 'view')<span class="style1">*</span>#end
						</td>
						<td>Negeri</td>
						<td>:</td>
						<td>$selectNegeri</td>
					</tr>
					<tr>
						<td>#if ($modePopup != 'view')<span class="style1">*</span>#end
						</td>
						<td>Pejabat</td>
						<td>:</td>
						<td>$selectPejabat</td>
					</tr>
					#foreach ($beanMaklumatPejabat in $BeanMaklumatPejabat)
					<tr>
						<td>&nbsp;</td>
						<td>Nama Pejabat</td>
						<td>:</td>
						<td>$beanMaklumatPejabat.namaPejabat</td>
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
					#end 
					
					#if ($modePopup != 'view')
						#if ($modePopup == 'new')
						<tr>
							<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
							<td>Tarikh Hantar</td>
							<td>:</td>
							<td><input name="txtTarikhHantar" type="text"
								$readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar"
								onBlur="check_date(this);calcDate()"
								value="" size="9" maxlength="10">
								<a
								href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img
									border="0" src="../img/calendar.gif" /></td>
						</tr>
						
						<tr>
							<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
							</td>
							<td>Jangkamasa</td>
							<td>:</td>
							<td><input name="txtJangkaMasa" type="text"
								$readonlyPopup class="$inputTextClassPopup"
								id="txtJangkaMasa" size="1" maxlength="2"
								value=""
								onBlur="validateNumber(this,this.value,'$beanMaklumatNotis.jangkamasa');calcDate()"
								$readonlyPopup class="$inputTextClassPopup"> Hari</td>
						</tr>
						<tr>
							<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
							</td>
							<td>Tarikh Dijangka Terima</td>
							<td>:</td>
							<td><input name="txtTarikhJangkaTerima" type="text"
								$readonlyPopup class="$inputTextClassPopup"
								id="txtTarikhJangkaTerima" onBlur="check_date(this)"
								value="" size="9"
								maxlength="10"> <a
								href="javascript:displayDatePicker('txtTarikhJangkaTerima',false,'dmy');"><img
									border="0" src="../img/calendar.gif" /></td>
						</tr>
						#else
							#foreach ($beanMaklumatNotis in $BeanMaklumatNotis)
							<tr>
							<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
							<td>Tarikh Hantar</td>
							<td>:</td>
							<td><input name="txtTarikhHantar" type="text"
								$readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar"
								onBlur="check_date(this);calcDate()"
								#if ($modePopup == 'update') value="$beanMaklumatNotis.tarikhHantar" #else  value="" #end size="9" maxlength="10">
								<a
								href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img
									border="0" src="../img/calendar.gif" /></td>
						</tr>
						
						<tr>
							<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
							</td>
							<td>Jangkamasa</td>
							<td>:</td>
							<td><input name="txtJangkaMasa" type="text"
								$readonlyPopup class="$inputTextClassPopup"
								id="txtJangkaMasa" size="1" maxlength="2"
								#if ($modePopup == 'update') value="$beanMaklumatNotis.jangkamasa" #else  value="" #end
								onBlur="validateNumber(this,this.value,'$beanMaklumatNotis.jangkamasa');calcDate()"
								$readonlyPopup class="$inputTextClassPopup"> Hari</td>
						</tr>
						<tr>
							<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
							</td>
							<td>Tarikh Dijangka Terima</td>
							<td>:</td>
							<td><input name="txtTarikhJangkaTerima" type="text"
								$readonlyPopup class="$inputTextClassPopup"
								id="txtTarikhJangkaTerima" onBlur="check_date(this)"
								#if ($modePopup == 'update') value="$beanMaklumatNotis.tarikhJangkaTerima" #else  value="" #end size="9"
								maxlength="10"> <a
								href="javascript:displayDatePicker('txtTarikhJangkaTerima',false,'dmy');"><img
									border="0" src="../img/calendar.gif" /></td>
						</tr>
						#end
						#end
					
					#else #foreach ($beanMaklumatNotis in $BeanMaklumatNotis)
					<tr>
						<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
						</td>
						<td>Tarikh Hantar</td>
						<td>:</td>
						<td><input name="txtTarikhHantar" type="text" $readonlyPopup
							class="$inputTextClassPopup" id="txtTarikhHantar"
							value="$beanMaklumatNotis.tarikhHantar" size="9" maxlength="10">
							</td>
					</tr>
					<tr>
						<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
						</td>
						<td>Jangkamasa</td>
						<td>:</td>
						<td><input type="text" name="txtJangkaMasa"
							id="txtJangkaMasa" size="1" maxlength="2"
							value="$beanMaklumatNotis.jangkamasa"
							onBlur="validateNumber(this,this.value,'$beanMaklumatNotis.jangkamasa');calcDate()"
							$readonlyPopup class="$inputTextClassPopup"> Hari</td>
					</tr>
					<tr>
						<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
						</td>
						<td>Tarikh Dijangka Terima</td>
						<td>:</td>
						<td><input name="txtTarikhJangkaTerima" type="text"
							$readonlyPopup class="$inputTextClassPopup"
							id="txtTarikhJangkaTerima"
							value="$beanMaklumatNotis.tarikhJangkaTerima" size="9"
							maxlength="10"></td>
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
		<td width="70%">#if ($modePopup == 'new') <input
			name="cmdSimpanNotis" type="button" onClick="doSimpanMaklumatNotis()"
			value="Simpan"> <input name="cmdBatal" type="button"
			onClick="doBatalMaklumatNotis()" value="Batal"> #end <!--
      #if ($modePopup == 'newUlangan')
      <input name="cmdSimpanNotis" type="button" onClick="doSimpanMaklumatUlanganNotis()" value="Simpan">
      <input name="cmdBatalNotis" type="button" onClick="doBatalKemaskiniMaklumatNotis()" value="Batal">
      #end
      --> 
      #foreach ($beanMaklumatNotis in $BeanMaklumatNotis)
      #if ($modePopup == 'view') #if ($flagAktif != 'T') <input
			name="cmdKemaskiniNotis" type="button"
			onClick="doKemaskiniMaklumatNotis()" value="Kemaskini"> <input
			name="cmdHapusNotis" type="button" onClick="doHapusMaklumatNotis()"
			value="Hapus"> <input name="cmdBatal1" type="button"
			value="Kembali" onClick="batalDokumen()" />
			
			#end #end #if ($modePopup == 'update') <input
			name="cmdSimpanNotis" type="button"
			onClick="doSimpanKemaskiniMaklumatNotis($idUlasanTeknikal)" value="Simpan" /> <input
			name="cmdBatalNotis" type="button"
			onClick="doBatalKemaskiniMaklumatNotis()" value="Batal"> #end #end
		</td>
	</tr>
</table>
