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
      <legend>MAKLUMAT RINGKASAN PERTIMBANGAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
      #foreach ($maklumatRingkasanPertimbangan in $MaklumatRingkasanPertimbangan)
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td valign="top">ULASAN KJP</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanKJP" id="txtUlasanKJP" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$maklumatRingkasanPertimbangan.ulasanKJP</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td valign="top">NILAIAN JPPH</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtNilaianJPPH" id="txtNilaianJPPH" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$maklumatRingkasanPertimbangan.ulasanJPPH</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top"><span class="style4"></span></td>
          <td valign="top">LAPORAN TAPAK</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtLaporanTapak" id="txtLaporanTapak" rows="4" cols="130" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$maklumatRingkasanPertimbangan.ulasanJKPTGN</textarea>
          </td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">CATATAN</td>
          <td valign="top">:</td>
          <td><textarea name="txtCatatanRingkasanPertimbangan" id="txtCatatanRingkasanPertimbangan" rows="4" cols="130" class="" onBlur="this.value=this.value.toUpperCase();">$maklumatRingkasanPertimbangan.catatanRingkasanPertimbangan</textarea></td>
        </tr>
        #end
##        <tr>
##          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
##        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
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
