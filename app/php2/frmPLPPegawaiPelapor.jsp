<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatLaporanTanah in $BeanMaklumatLaporanTanah)
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Nama</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtNama" id="txtNama" value="$beanMaklumatLaporanTanah.namaPegawai" size="40"  maxlength="80" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/>
    </td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td> Negeri</td>
    <td>:</td>
    <td>$selectNegeriPelapor</td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Jawatan</td>
    <td>:</td>
    <td>$selectJawatanPelapor</td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> 
      #if ($mode == 'view')
      #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
      <input type="button" name="cmdKemaskiniPelapor" id="cmdKemaskiniPelapor" value="Kemaskini" onClick="kemaskiniPelapor()"/>
      #end
      <input type="button" name="cmKembali" id="cmKembali" value="Kembali" onclick="kembali()"/>
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskiniPelapor" id="cmdSimpanKemaskiniPelapor" value="Simpan" onClick="simpanKemaskiniPelapor()"/>
      <input type="button" name="cmdBatalKemaskiniPelapor" id="cmdBatalKemaskiniPelapor" value="Batal" onClick="batalKemaskiniPelapor()"/>
      #end </td>
  </tr>
</table>

<script>
function kemaskiniPelapor() {
	document.${formName}.mode.value = "update";
	//doAjaxCall${formName}("");
	document.${formName}.submit();
}
function batalKemaskiniPelapor() {
	document.${formName}.mode.value = "view";
	//doAjaxCall${formName}("");
	document.${formName}.submit();
}
function simpanKemaskiniPelapor(){
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukkan Nama Pegawai.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}
	if(document.${formName}.socNegeriPelapor.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeriPelapor.focus(); 
		return; 
	}
	if(document.${formName}.socJawatanPelapor.value == ""){
		alert('Sila pilih Jawatan.');
  		document.${formName}.socJawatanPelapor.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniPelapor";
	//doAjaxCall${formName}("");
	document.${formName}.submit();
}
</script>
