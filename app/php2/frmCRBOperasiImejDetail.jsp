<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
#preview img{
   margin: 5px;
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td><fieldset>
				<legend>MAKLUMAT IMEJ</legend>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					#foreach ($beanMaklumatImejan in $BeanMaklumatImejan) #if
					($modePopup != 'new')
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="28%">&nbsp;</td>
						<td width="1%">&nbsp;</td>
						<td width="70%"><img
							src="../servlet/ekptg.view.php2.FrmDisplayImage?id=$idDokumen"
							alt="Imej Pelan" border="1" width="250" height="250"
							onclick="cetakImej($idDokumen)" /></td>
					</tr>
					<tr>
						<td width="1%">&nbsp;</td>
						<td width="28%">&nbsp;</td>
						<td width="1%">&nbsp;</td>
						<td width="70%">&nbsp;</td>
					</tr>
					#end
					<tr>
						<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
						<td width="28%">Jenis Imej</td>
						<td width="1%">:</td>
						<td><select name="socJenisImej" id="socJenisImej"
											style="width: 275px;" $readonlyPopup class="inputTextClassPopup"
											onchange="javascript:doChangeJenisImej(this.value)" $disabled>
							#if ($beanMaklumatImejan.jenisImej == 'SB')
								<option value="0">SILA PILIH</option>
								<option value="SB" selected="selected">IMEJ SEBELUM OPERASI</option>
								<option value="SM">IMEJ SEMASA OPERASI</option>
								<option value="SL">IMEJ SELEPAS OPERASI</option>
							#elseif ($beanMaklumatImejan.jenisImej == 'SM')	
								<option value="0">SILA PILIH</option>
								<option value="SB">IMEJ SEBELUM OPERASI</option>
								<option value="SM" selected="selected">IMEJ SEMASA OPERASI</option>
								<option value="SL">IMEJ SELEPAS OPERASI</option>
							#elseif ($beanMaklumatImejan.jenisImej == 'SL')	
								<option value="0">SILA PILIH</option>
								<option value="SB">IMEJ SEBELUM OPERASI</option>
								<option value="SM">IMEJ SEMASA OPERASI</option>
								<option value="SL" selected="selected">IMEJ SELEPAS OPERASI</option>
							#else
								<option value="0" selected="selected">SILA PILIH</option>
								<option value="SB">IMEJ SEBELUM OPERASI</option>
								<option value="SM">IMEJ SEMASA OPERASI</option>
								<option value="SL">IMEJ SELEPAS OPERASI</option>
							#end
							</select>
						</td>
					</tr>
					<tr>
						<td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end
						</td>
						<td width="28%">Nama Imej</td>
						<td width="1%">:</td>
						<td width="70%"><input name="txtNamaImej" type="text"
							id="txtNamaImej" value="$beanMaklumatImejan.namaImej" size="43"
							maxlength="100" $readonlyPopup class="$inputTextClassPopup"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td valign="top">Catatan</td>
						<td valign="top">:</td>
						<td valign="top"><textarea name="txtCatatanImej" cols="50"
								rows="5" class="$inputTextClassPopup" id="txtCatatanImej"
								$readonlyPopup>$beanMaklumatImejan.catatanImej</textarea></td>
					</tr>
					#end #if ($modePopup == 'new')
					<tr>
						<td>#if ($modePopup != 'view')<span class="style1">*</span>#end
						</td>
						<td>Direktori Imej</td>
						<td>:</td>
						<td><input id="fileupload" name="fileupload" type="file" multiple
							size="40" $readonlyPopup2 class="$inputTextClassPopup" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><span class="style4"><i><font color="#ff0000">Perhatian</font>
									: </i><span class="style5">Saiz muat naik imej adalah tidak
									melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba
									dengan saiz imej yang lebih kecil.</span></span></td>
					</tr>
					#end #if ($modePopup != 'view')
					<tr>
						<td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>:
								Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
					</tr>
					#end
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>#if ($modePopup == 'new') <input name="cmdSimpan"
							type="button" value="Simpan"
							onClick="simpanDokumen('$idLaporanTanah','$idPermohonan')" /> <input
							name="cmdBatal" type="button" value="Batal"
							onClick="batalDokumen()" /> #end #if ($modePopup == 'view') <input
							name="cmdKemaskini" type="button" value="Kemaskini"
							onClick="kemaskiniDokumen()" /> <input name="cmdHapus"
							type="button" value="Hapus" onClick="hapusDokumen()">
							#end #if ($modePopup == 'update') <input
							name="cmdSimpanKemaskini" type="button" value="Simpan"
							onClick="simpanKemaskiniDokumen()" /> <input name="cmdBatal"
							type="button" value="Batal" onClick="batalKemaskiniDokumen()" />
							#end
						</td>
					</tr>
				</table>
			</fieldset></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
