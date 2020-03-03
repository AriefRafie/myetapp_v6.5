<table width="100%" border="0" cellspacing="2" cellpadding="2">
	#foreach ($beanMaklumatLaporanTanah in $BeanMaklumatLaporanTanah)
	<tr>
		<td width="1%">&nbsp;</td>
		<td width="28%">Nama Pegawai Penyemak</td>
		<td width="1%">:</td>
		<td width="70%"><input type="text" name="txtNamaPenyemak"
			id="txtNamaPenyemak" value="$beanMaklumatLaporanTanah.namaPenyemak"
			size="40" maxlength="80" $readonly class="$inputTextClass"
			onblur="this.value=this.value.toUpperCase();" /></td>
	</tr>
	<tr>
		<td width="1%">&nbsp;</td>
		<td>Jawatan Pegawai Penyemak</td>
		<td>:</td>
		<td>$selectJawatanPenyemak</td>
	</tr>
	<tr>
		<td width="1%">&nbsp;</td>
		<td>Negeri Pegawai Penyemak</td>
		<td>:</td>
		<td>$selectNegeriPenyemak</td>
	</tr>
	<tr>
		<td width="1%" valign="top">&nbsp;</td>
		<td width="28%" valign="top">Catatan</td>
		<td width="1%" valign="top">:</td>
		<td width="70%" valign="top"><textarea name="txtCatatanPenyemak"
				id="txtCatatanPenyemak" rows="5" cols="40" $readonly
				class="$inputTextClass">$beanMaklumatLaporanTanah.catatanPenyemak</textarea>
		</td>
	</tr>
	#if ($mode != 'view')
	<tr>
		<td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>:
				Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
	</tr>
	#end
	<tr>
		<td>&nbsp;</td>
    	<td>&nbsp;</td>
    	<td>&nbsp;</td>
		<td>#if($mode == 'view')
			#if($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ') 
			<input type="button" name="cmdKemaskiniPenyemak" id="cmdKemaskiniPenyemak" value="Kemaskini" onClick="kemaskiniPenyemak()" /> 
			#end
			#if($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ') #if($idStatus == '1610200') 
			#if ($userRole == 'PenyemakNegeri')
			<input type="button" name="cmdHantarSemula" id="cmdHantarSemula" value="Hantar Untuk Pembetulan" onClick="doPembetulan()" 
			 title="Kembalikan Kepada Penolong Pegawai Tanah Untuk Tujuan Pembetulan" />
			#end
			<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()" title="Ke Menu Cetakan Kertas Ringkasan"/> 
			<input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()" /> 
			#end
			#end 
			<input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')" /> 
			#end 
			#if($mode == 'update') 
			<input type="button" name="cmdSimpanKemaskiniPenyemak" id="cmdSimpanKemaskiniPenyemak" value="Simpan" onClick="simpanKemaskiniPenyemak()" /> 
			<input type="button" name="cmdBatalKemaskiniPenyemak" id="cmdBatalKemaskiniPenyemak" value="Batal" onClick="batalKemaskiniPenyemak()" /> 
			#end
			#if($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan') 
			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()" /> 
			#end
		</td>
	</tr>
	#end
</table>
<script>
function kemaskiniPenyemak() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniPenyemak() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPenyemak(){
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukkan Nama Pegawai Penyemak.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}
	if(document.${formName}.socNegeriPenyemak.value == ""){
		alert('Sila pilih Negeri Pegawai Penyemak.');
  		document.${formName}.socNegeriPenyemak.focus(); 
		return; 
	}
	if(document.${formName}.socJawatanPenyemak.value == ""){
		alert('Sila pilih Jawatan Pegawai Penyemak.');
  		document.${formName}.socJawatanPenyemak.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniPenyemak";
	doAjaxCall${formName}("");
}
</script>
