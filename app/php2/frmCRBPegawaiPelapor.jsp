<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatLaporanTanah in $BeanMaklumatLaporanTanah)
  <input type="hidden" name="idPegawaiLaporanTanahPelapor" id="idPegawaiLaporanTanahPelapor" value="$beanMaklumatLaporanTanah.idPegawaiLaporanTanah">
  <tr>
    <td colspan="4"><fieldset>
      <legend><strong>LAPORAN DISEDIAKAN OLEH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
		  <tr>
		    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
		    <td width="28%">Nama</td>
		    <td width="1%">:</td>
		    <td width="70%"><input type="text" name="txtNamaPelapor" id="txtNamaPelapor" value="$beanMaklumatLaporanTanah.namaPegawai" size="30"   $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/>
		    </td>
		  </tr>
		  <tr>
		    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
		    <td width="28%">Jawatan</td>
		    <td width="1%">:</td>
		    <td width="70%"><input name="txtJawatanPelapor" type="text" class="$inputTextClass" id="txtJawatanPelapor" value="$beanMaklumatLaporanTanah.jawatanPelapor"  $readonly onblur="this.value=this.value.toUpperCase();" size="30"/></td>
		  </tr>
		  <tr>
		    <td width="1%">&nbsp;</td>
		    <td width="28%">JKPTG Negeri</td>
		    <td width="1%">:</td>
		    <td width="70%">$selectNegeriPelapor</td>
		  </tr>
		  <tr>
		    <td width="1%">&nbsp;</td>
		    <td width="28%">&nbsp;</td>
		    <td width="1%">&nbsp;</td>
		    <td width="70%">&nbsp;</td>
		  </tr>
		  <tr>
	</table></fieldset>
  </td>
</tr>	
<tr>
  <td colspan="4"><fieldset>
    <legend><strong>LAPORAN DISEMAK OLEH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
		  <tr>
		    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
		    <td width="28%">Nama</td>
		    <td width="1%">:</td>
		    <td width="70%"><input type="text" name="txtNamaPenyemak" id="txtNamaPenyemak" value="$beanMaklumatLaporanTanah.namaPenyemak" size="30"   $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/>
		    </td>
		  </tr>
		  <tr>
		    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
		    <td width="28%">Jawatan</td>
		    <td width="1%">:</td>
		    <td width="70%"><input name="txtJawatanPenyemak" type="text" class="$inputTextClass" id="txtJawatanPenyemak" value="$beanMaklumatLaporanTanah.jawatanPenyemak"  $readonly onblur="this.value=this.value.toUpperCase();" size="30"/></td>
		  </tr>
		  <tr>
		    <td width="1%">&nbsp;</td>
		    <td width="28%">JKPTG Negeri</td>
		    <td width="1%">:</td>
		    <td width="70%">$selectNegeriPenyemak</td>
		  </tr>
		  <tr>
		    <td width="1%">&nbsp;</td>
		    <td width="28%">&nbsp;</td>
		    <td width="1%">&nbsp;</td>
		    <td width="70%">&nbsp;</td>
		  </tr>
	</table></fieldset>
  </td>
</tr>
  #if ($modePopup != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"> #if ($modePopup == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniPegawaiPelapor()"/>
      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      <input name="cmdBatalLT" id="cmdBatalLT" type="button" value="Kembali" onClick="batalLawatanTapak()" />
      #end
      #if ($modePopup == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniPegawaiPelapor()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalPegawaiPelapor()"/>
      #end </td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakCRBLaporanTanah('$idFail','$idLaporanTanah')"> Laporan Tanah </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakCRBLampiranA('$idLaporanTanah')"> Lampiran A </a></td>
  </tr>
</table>
</fieldset>
<script>
 
function kemaskiniPegawaiPelapor() {
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPegawaiPelapor() {
	if(document.${formName}.txtNamaPelapor.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPelapor.focus(); 
		return; 
	}
	if(document.${formName}.txtJawatanPelapor.value == ""){
		alert('Sila masukkan Jawatan.');
  		document.${formName}.txtJawatanPelapor.focus(); 
		return; 
	}
	if(document.${formName}.socidNegeriPelapor.value == ""){
		alert('Sila pilih JKPTG Negeri.');
  		document.${formName}.socidNegeriPelapor.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPenyemak.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPenyemak.focus(); 
		return; 
	}
	if(document.${formName}.txtJawatanPenyemak.value == ""){
		alert('Sila masukkan Jawatan.');
  		document.${formName}.txtJawatanPenyemak.focus(); 
		return; 
	}
	if(document.${formName}.socidNegeriPenyemak.value == ""){
		alert('Sila pilih JKPTG Negeri.');
  		document.${formName}.socidNegeriPenyemak.focus(); 
		return; 
	}	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.modePopup.value = "update";
		return;
	}
	
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPelapor";
	doAjaxCall${formName}("");
}
function batalPegawaiPelapor() {
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
 </script>
