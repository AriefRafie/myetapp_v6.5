<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="hitButton" type="hidden" id="hitButton"/>
<input name="step" type="hidden" id="step" value="$step" />
<input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi" />
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT TANAH</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        ##foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Pegangan Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onblur="doChangePeganganHakmilik();"/>
            <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onclick="pilihTanah()"/>
            &nbsp; <span class="style1">$errorPeganganHakmilik</span></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noLot </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.luasLot </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Hakmilik</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noHakmilik </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Warta</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noWarta </td>
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
          <td>$beanMaklumatTanah.mukim </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Daerah</td>
          <td>:</td>
          <td>$beanMaklumatTanah.daerah </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatTanah.negeri </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$beanMaklumatTanah.kementerian </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Agensi</td>
          <td>:</td>
          <td>$beanMaklumatTanah.agensi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kegunaan Tanah</td>
          <td>:</td>
          <td>$beanMaklumatTanah.kegunaanTanah </td>
        </tr>
        ##end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT LAWATAN TAPAK</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Tarikh Lawatan</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txdTarikhLawatan" id="txdTarikhLawatan" value="$txdTarikhLawatan" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhLawatan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td valign="top"><span class="style1">*</span></td>
          <td valign="top">Tujuan Laporan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtTujuanLaporan" id="txtTujuanLaporan" rows="5" cols="50" >$txtTujuanLaporan</textarea></td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" >$txtCatatan</textarea></td>
        </tr>
        <tr>
          <td valign="top"><span class="style1">*</span></td>
          <td valign="top">Disediakan Oleh</td>
          <td valign="top">:</td>
          <td valign="top"><input name="txtPelapor" type="text" id="txtPelapor" value="$txtPelapor" maxlength="40" size="40" onblur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        <tr>
          <td valign="top"><span class="style1">*</span></td>
          <td valign="top">Jawatan</td>
          <td valign="top">:</td>
          <td valign="top"><input name="txtJawatan" type="text" id="txtJawatan" value="$txtJawatan" maxlength="40" size="40" onblur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        <tr>
          <td valign="top"><span class="style1">*</span></td>
          <td valign="top">Negeri</td>
          <td valign="top">:</td>
          <td valign="top">$selectNegeri</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"><input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/></td>
  </tr>
</table>
<script>
	function pilihTanah() {
		var url = "../x/${securityToken}/ekptg.view.PopupSenaraiTanahView";
	    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}
	
function refreshFromPilihTanah(idHakmilikAgensi) {
	document.${formName}.idHakmilikAgensi.value = idHakmilikAgensi;
	doAjaxCall${formName}("");
}
function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}

function simpan() {

	if(document.${formName}.txtPeganganHakmilik.value == ""){
		alert('Sila pilih Pegangan Hakmilik.');
		document.${formName}.txtPeganganHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhLawatan.value == ""){
		alert('Sila masukkan Tarikh Lawatan.');
		document.${formName}.txdTarikhLawatan.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanLaporan.value == ""){
		alert('Sila masukkan Tujuan Laporan.');
		document.${formName}.txtTujuanLaporan.focus(); 
		return; 
	}
	if(document.${formName}.txtPelapor.value == ""){
		alert('Sila masukkan Disediakan Oleh.');
		document.${formName}.txtPelapor.focus(); 
		return; 
	}
	if(document.${formName}.txtJawatan.value == ""){
		alert('Sila masukkan Jawatan.');
		document.${formName}.txtJawatan.focus(); 
		return; 
	}
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
		document.${formName}.socNegeri.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.step.value = "daftar";
		return;
	}
	
	document.${formName}.step.value = "kemaskini";
	document.${formName}.hitButton.value = "simpan";
	document.${formName}.submit();
}
function batal() {	
	document.${formName}.step.value = "";
	document.${formName}.submit();
}
</script>
