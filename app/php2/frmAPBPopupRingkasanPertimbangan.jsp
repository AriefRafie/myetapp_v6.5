<style type="text/css">
<!--
.style1 {
	color: #0000FF
}
#parse("css/eTapp_PHP.css")
-->
</style>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="actionPopup" value='$!actionPopup'/>
	<input type="hidden" name="idFail"/>
	<input type="hidden" name="idMesyuarat" value='$!idMesyuarat'/>
	<input type="hidden" name="hitButton" id="hitButton"/>
	<input type="hidden" name="step" id="step" value='$!step'/>
	<input type="hidden" name="catatanRingkasan" id="catatanRingkasan"/>
</p>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT PEMOHON</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
      #foreach ($maklumatRingkasanPemohon in $MaklumatRingkasanPemohon)
      	<tr>
          <td valign="top"><span class="style4"></span></td>
          <td width="34%" valign="top">NAMA PEMOHON</td>
          <td width="3%" valign="top">:</td>
          <td width="63%" valign="top"><strong>$!maklumatRingkasanPemohon.nama</strong></td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td width="34%" valign="top">LOKASI</td>
          <td width="3%" valign="top">:</td>
          <td width="63%" valign="top"><strong>$!maklumatRingkasanPemohon.lokasi</strong></td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td width="34%" valign="top">LEMBAGA PENGARAH</td>
          <td width="3%" valign="top">:</td>
          <td width="63%" valign="top">
          	<input type=text name="txtNamaPemohon" id="txtNamaPemohon"></input>
          </td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td width="34%" valign="top">MODAL DIBENARKAN</td>
          <td width="3%" valign="top">:</td>
          <td width="63%" valign="top"><strong>$!maklumatRingkasanPemohon.modalBenar</strong></td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td width="34%" valign="top">MODAL JELAS</td>
          <td width="3%" valign="top">:</td>
          <td width="63%" valign="top"><strong>$!maklumatRingkasanPemohon.modalJelas</strong></td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td width="34%" valign="top">TUJUAN</td>
          <td width="3%" valign="top">:</td>
          <td width="63%" valign="top">
          	<textarea name="txtTujuan" id="txtTujuan" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$!maklumatRingkasanPemohon.tujuan</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td width="34%" valign="top">PERJANJIAN/ SURAT MINAT PEMBELIAN PASIR</td>
          <td width="3%" valign="top">:</td>
          <td width="63%" valign="top"><textarea name="txtUlasanJUPEM" id="txtUlasanJUPEM" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$!maklumatRingkasanPertimbangan.ulasanJUPEM</textarea>
          </td>
        </tr>
        #end
      </table>
    </fieldset></td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>ULASAN JABATAN TEKNIKAL</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
      #foreach ($maklumatRingkasanPertimbangan in $MaklumatRingkasanPertimbangan)
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td width="34%" valign="top">ULASAN JUPEM</td>
          <td width="3%" valign="top">:</td>
          <td width="63%" valign="top"><textarea name="txtUlasanJUPEM" id="txtUlasanJUPEM" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$!maklumatRingkasanPertimbangan.ulasanJUPEM</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td valign="top">ULASAN JPS</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanJPS" id="txtUlasanJPS" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$!maklumatRingkasanPertimbangan.ulasanJPS</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td valign="top">ULASAN JMG</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanJMG" id="txtUlasanJMG" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$!maklumatRingkasanPertimbangan.ulasanJMG</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td valign="top">ULASAN PHN</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanPHN" id="txtUlasanPHN" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$!maklumatRingkasanPertimbangan.ulasanPHN</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td valign="top">ULASAN DOF</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanDOF" id="txtUlasanDOF" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$!maklumatRingkasanPertimbangan.ulasanDOF</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td valign="top">ULASAN JLM</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanJLM" id="txtUlasanJLM" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$!maklumatRingkasanPertimbangan.ulasanJLM</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td valign="top">ULASAN JAS</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanJAS" id="txtUlasanJAS" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$!maklumatRingkasanPertimbangan.ulasanJAS</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td valign="top">ULASAN PTG</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanPTG" id="txtUlasanPTG" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$!maklumatRingkasanPertimbangan.ulasanPTG</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">CATATAN</td>
          <td valign="top">:</td>
          <td><textarea name="txtCatatanRingkasanPertimbangan" id="txtCatatanRingkasanPertimbangan" rows="4" cols="130" class="" onBlur="this.value=this.value.toUpperCase();">$!maklumatRingkasanPertimbangan.catatanRingkasanPertimbangan</textarea></td>
        </tr>
        #end
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td>
          	<input name="cmdSimpanCatatanRingkasanPertimbangan" type="button" value="Simpan Catatan" onClick="simpanRingkasanPertimbangan()" />
            ##<input name="cmdBatalRingkasanPertimbangan" type="button" value="Batal" onClick="batalRingkasanPertimbangan()" />
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doTutup() {
	document.${formName}.actionPopup.value = "tutup";
	document.${formName}.submit();
}

function doPilih() {
	if(validatePilihanPermohonan()){
		if ( !window.confirm("Adakah Anda Pasti?") ){
			return;
		}
		document.${formName}.hitButton.value = "doSimpanPilihan";
		document.${formName}.submit();
	}
}

function validatePilihanPermohonan() {
	var err_count = 0;
	var checking = 0;
	var size = document.${formName}.checkPermohonan.length ;
	
	if( size > 1 ){
		for( var i = 0; i < document.${formName}.checkPermohonan.length; i++ ){
    		if(document.${formName}.checkPermohonan[i].checked){ 
    			checking += 1;
    		};
    	}
	} else {
		if( document.${formName}.checkPermohonan.checked == true ){
			checking += 1; 
		}
	}
	
	if ( checking == 0 ){
		$('err_checkPermohonan').innerHTML = "Sila pilih permohonan terlebih dahulu"; err_count++;  
	}
	
	if (err_count == 0) {
	}
	
	return err_count == 0;
}

function carian(){
	document.${formName}.actionPopup.value = "";
	doAjaxCall${formName}("");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.socJenisPermohonan.value = "";
	doAjaxCall${formName}("");
}
function simpanRingkasanPertimbangan() {
	if ( !window.confirm("Adakah Anda Pasti?") ){
		return;
	}
	document.${formName}.catatanRingkasan.value = document.getElementById("txtCatatanRingkasanPertimbangan").value;
	document.${formName}.hitButton.value = "doSimpanRingkasanPertimbangan";
	document.${formName}.submit();
}
function batalRingkasanPertimbangan() {
	document.${formName}.reset();
	document.${formName}.actionMesyuarat.value = "";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
</script>
