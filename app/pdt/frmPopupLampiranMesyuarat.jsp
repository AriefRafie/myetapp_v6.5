<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PDT.css">
<fieldset>
	<legend>
		<strong>Muatnaik Lampiran</strong>
	</legend>
	<table width="100%">
		<tr>
			<td width="29%" align="right" scope="row">Tajuk Dokumen</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">
				<input size="30" name="txtNamaDokumen" type="text" id="txtNamaDokumen" value="$!txtNamaDokumen" />
			</td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row"></td>
			<td width="1%" scope="row">:</td>
			<td width="70%">
				<input id="fileupload" name="fileupload" type="file" size="40" />	
			</td>
		</tr>
		<tr>
			<td width="29%" align="right" scope="row">Jenis Dokumen</td>
			<td width="1%" scope="row">:</td>
			<td width="20%">
				<select name="paramJenis" id="paramJenis">
					<option value="">Sila pilih</option>
					<option value="16101333">Minit Mesyuarat</option>
					<option value="16101334">Kertas Kerja</option>
					<option value="16101335">Kertas Pertimbangan</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" scope="row">&nbsp;</td>
			<td scope="row">&nbsp;</td>
			<td><input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanLampiran()" /> 
				<input type="reset" name="cmdKosongkanAkta" value="Kosongkan"/>
				<input type="reset" name="cmdKembaliMesyuarat" value="Kembali" onclick="kembaliMesyuarat('$!idTblPdtMesyuarat')"/>
			</td>
		</tr>
	</table>
</fieldset>
<input name="idTblPdtMesyuarat" type="hidden" id="idTblPdtMesyuarat" value="$!idTblPdtMesyuarat"/>
