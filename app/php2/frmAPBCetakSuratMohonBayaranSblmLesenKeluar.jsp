<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="idFail" type="hidden" id="idFail" value="$idFail"/>
<input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
<input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
<input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
<input name="mode" type="hidden" id="mode" value="$mode"/>
<input name="hitButton" type="hidden" id="hitButton"/>
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<!-- saiz text -->
#set($saizTxtUlasanEIA="4000")
#set($saizTxtUlasanHidro="4000")
#set($saizTxtUlasanHidra="4000")
#set($saizTxtUlasanPasir="4000")
#set($saizTxtMaklumatTambahan="4000")
#set($saizTxtSyaratKelulusan="4000")
<table width="100%" border="0" cellpadding="2" cellspacing="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end 
  
  #if ($idFail != '' && $flagOpenDetail)
  <tr>
    <td><fieldset>
      <legend>CETAK SURAT MOHON BAYARAN SEBELUM PENGELUARAN LESEN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatBayaranSblm in $BeanMaklumatBayaranSblm)
        <tr>
          <td width="1%" valign="top">#if ($mode!= 'view')<span class="style1">*</span>#end</td>
          <td width="28%" valign="top">Ulasan Laporan EIA</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top"><textarea name="txtUlasanEIA" cols="50" rows="5" id="txtUlasanEIA" $readonly class="$inputTextClass"  onkeyup="textCounter(this.form.txtUlasanEIA,this.form.remLen1,$!saizTxtUlasanEIA);" onkeydown="textCounter(this.form.txtUlasanEIA,this.form.remLen1,$!saizTxtUlasanEIA);" >$beanMaklumatBayaranSblm.txtUlasanEIA</textarea></td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtUlasanEIA" /></td>
        </tr>
        #end
        <tr>
          <td valign="top">#if ($mode!= 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Ulasan Laporan Hidrografi</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanHidro" cols="50" rows="5" id="txtUlasanHidro" $readonly class="$inputTextClass"  onkeyup="textCounter(this.form.txtUlasanHidro,this.form.remLen2,$!saizTxtUlasanHidro);" onkeydown="textCounter(this.form.txtUlasanHidro,this.form.remLen2,$!saizTxtUlasanHidro);">$beanMaklumatBayaranSblm.txtUlasanHidro</textarea></td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen2" size="3" maxlength="3" value="$!saizTxtUlasanHidro" /></td>
        </tr>
        #end
        <tr>
          <td valign="top">#if ($mode!= 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Ulasan Laporan Hidraulik</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanHidra" cols="50" rows="5" id="txtUlasanHidra" $readonly class="$inputTextClass"  onkeyup="textCounter(this.form.txtUlasanHidra,this.form.remLen2,$!saizTxtUlasanHidra);" onkeydown="textCounter(this.form.txtUlasanHidra,this.form.remLen2,$!saizTxtUlasanHidra);">$!beanMaklumatBayaranSblm.txtUlasanHidra</textarea></td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen2" size="3" maxlength="3" value="$!saizTxtUlasanHidra" /></td>
        </tr>
        #end
        <tr>
          <td valign="top">#if ($mode!= 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Ulasan Laporan Kajian Kuantiti dan Kualiti Sumber Pasir</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtUlasanPasir" cols="50" rows="5" id="txtUlasanPasir" $readonly class="$inputTextClass"  onkeyup="textCounter(this.form.txtUlasanPasir,this.form.remLen2,$!saizTxtUlasanPasir);" onkeydown="textCounter(this.form.txtUlasanPasir,this.form.remLen2,$!saizTxtUlasanPasir);">$!beanMaklumatBayaranSblm.txtUlasanPasir</textarea></td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen2" size="3" maxlength="3" value="$!saizTxtUlasanPasir" /></td>
        </tr>
        #end
        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4"><fieldset>
            <legend><strong>MAKLUMAT SYARAT-SYARAT SEBELUM BAYARAN LESEN</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">#if ($mode!= 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Luas Kawasan</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtLuasKawasan" type="text" class="$inputTextClass" id="txtLuasKawasan" value="$beanMaklumatBayaranSblm.txtLuasKawasan" size="10" maxlength="16" $readonly onkeyup="validateNumber(this,this.value,'$beanMaklumatBayaranSblm.txtLuasKawasan')" onblur="kiraJumlahFeeLesen()"/>
                  $selectLuas</td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Fee Lesen</td>
                <td>:</td>
                <td>RM
                  <input name="txtFeeLesen" type="text" class="$inputTextClass" id="txtFeeLesen" style="text-align:right" onblur="validateCurrency(this,this.value,'$beanMaklumatBayaranSblm.txtFeeLesen');kiraJumlahFeeLesen()" value="$beanMaklumatBayaranSblm.txtFeeLesen" size="9" maxlength="37" $readonly/>
                  bagi setiap
                  <input name="txtKmPersegi" type="text" class="$inputTextClass" id="txtKmPersegi" value="$beanMaklumatBayaranSblm.txtKmPersegi" size="9" maxlength="16" $readonly style="text-align:right" onblur="kiraJumlahFeeLesen()"/>
                  km persegi</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Jumlah Fee Lesen</td>
                <td>:</td>
                <td>RM
                  <input name="txtJumlahFeeLesen" type="text" class="disabled" id="txtJumlahFeeLesen" style="text-align:right" onBlur="validateCurrency(this,this.value,'$beanMaklumatBayaranSblm.txtJumlahFeeLesen')" value="$beanMaklumatBayaranSblm.txtJumlahFeeLesen" size="9" maxlength="37" readonly/></td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Wang Cagaran Pematuhan Syarat Lesen</td>
                <td>:</td>
                <td>RM
                  <input name="txtWangCagaran" type="text" class="$inputTextClass" id="txtWangCagaran" style="text-align:right" onBlur="validateCurrency(this,this.value,'$beanMaklumatBayaranSblm.txtWangCagaran')" value="$beanMaklumatBayaranSblm.txtWangCagaran" size="9" maxlength="37" $readonly/>
                </td>
              </tr>
              <tr>
                <td>#if ($mode!= 'view')<span class="style1">*</span>#end</td>
                <td>Wang Amanah Kebajikan Nelayan</td>
                <td>:</td>
                <td>RM
                  <input name="txtWangAmanah" type="text" class="$inputTextClass" id="txtWangAmanah" style="text-align:right" onBlur="validateCurrency(this,this.value,'$beanMaklumatBayaranSblm.txtWangAmanah')" value="$beanMaklumatBayaranSblm.txtWangAmanah" size="9" maxlength="37" $readonly/></td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Maklumat Tambahan</td>
                <td valign="top" >:</td>
                <td><textarea name="txtMaklumatTambahan" cols="40" rows="5" class="$inputTextClass" id="txtMaklumatTambahan" $readonly onkeyup="textCounter(this.form.txtMaklumatTambahan,this.form.remLen7,$!saizTxtMaklumatTambahan);" onkeydown="textCounter(this.form.txtMaklumatTambahan,this.form.remLen7,$!saizTxtMaklumatTambahan);">$beanMaklumatBayaranSblm.txtMaklumatTambahan</textarea></td>
              </tr>
              #if ($mode!= 'view')
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">Baki Aksara :&nbsp;
                  <input type="text" readonly="readonly" class="disabled" name="remLen7" size="3" maxlength="3" value="$!saizTxtMaklumatTambahan" /></td>
              </tr>
              #end
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Isipadu Dibenarkan</td>
                <td>:</td>
                <td><input name="txtIsipadu" type="text" class="$inputTextClass" id="txtIsipadu" value="$beanMaklumatBayaranSblm.txtIsipadu" size="10" maxlength="26" $readonly onkeyup="validateNumber(this,this.value,'$beanMaklumatBayaranSblm.txtIsipadu')" onblur="calcJumlahRoyaltiSeluruh()"/>
                  meter padu</td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Kadar Royalti Pasir</td>
                <td>:</td>
                <td>RM
                  <input name="txtKadarRoyaltiPasir" type="text" class="$inputTextClass" id="txtKadarRoyaltiPasir" value="$beanMaklumatBayaranSblm.txtKadarRoyaltiPasir" size="10" maxlength="37" $readonly onBlur="validateCurrency(this,this.value,'$beanMaklumatBayaranSblm.txtKadarRoyaltiPasir');calcJumlahRoyaltiSeluruh()"  style="text-align:right"/>
                  /meter padu</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Jumlah Royalti Keseluruhan</td>
                <td>:</td>
                <td>RM
                  <input name="txtJumlahRoyaltiSeluruh" type="text" class="$inputTextClass" id="txtJumlahRoyaltiSeluruh" value="$beanMaklumatBayaranSblm.txtJumlahRoyaltiSeluruh" size="10" maxlength="13" $readonly onBlur="validateCurrency(this,this.value,'$beanMaklumatBayaranSblm.txtJumlahRoyaltiSeluruh');" style="text-align:right"/></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Jumlah Pendahuluan Royalti</td>
                <td>:</td>
                <td>RM
                  <input name="txtJumDahuluRoyalti" type="text" class="$inputTextClass" id="txtJumDahuluRoyalti" value="$beanMaklumatBayaranSblm.txtJumDahuluRoyalti" size="10" maxlength="13" $readonly  style="text-align:right" onBlur="validateCurrency(this,this.value,'$beanMaklumatBayaranSblm.txtKadarRoyaltiPasir')"/></td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Syarat Kelulusan Di Lampiran</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtSyaratKelulusan" cols="40" rows="5" class="$inputTextClass" id="txtSyaratKelulusan" $readonly onkeyup="textCounter(this.form.txtSyaratKelulusan,this.form.remLen6,$!saizTxtSyaratKelulusan);" onkeydown="textCounter(this.form.txtSyaratKelulusan,this.form.remLen6,$!saizTxtSyaratKelulusan);">$beanMaklumatBayaranSblm.txtSyaratKelulusan</textarea></td>
              </tr>
              #if ($mode!= 'view')
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">Baki Aksara :&nbsp;
                  <input type="text" readonly="readonly" class="disabled" name="remLen6" size="3" maxlength="3" value="$!saizTxtSyaratKelulusan" /></td>
              </tr>
              #end
              #end
            </table>
            </fieldset></td>
        </tr>
        #if ($mode == 'update')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> #if ($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
            #if($idStatus =='1610237')
            <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
            #end
            <input type="button" name="cdmCetak3" id="cdmCetak3" value="Cetak" onClick="javascript:setTable('tableReport')"/>
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="doSimpanKemaskiniBayaranSblm()"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
            #end </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  #elseif ($idFail != '')
  <tr>
    <td> 
      <div class="warning"><strong>STATUS FAIL MASIH DI PERINGKAT $!status</strong></div></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;">
<legend><strong>SENARAI DOKUMEN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratBayaranFeeLesen('$idFail')"> Surat Mengemukakan Bayaran Fee Lesen </a></td>
  </tr>
</table>
</fieldset>
<script>
function doKemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("doKemaskini");
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniBayaranSblm() {
	
	if(document.${formName}.txtUlasanEIA.value == ""){
		alert('Sila masukkan Ulasan EIA.');
  		document.${formName}.txtUlasanEIA.focus(); 
		return; 
	}
	if(document.${formName}.txtUlasanHidro.value == ""){
		alert('Sila masukkan Ulasan Hidrografi.');
  		document.${formName}.txtUlasanHidro.focus(); 
		return; 
	}
	if(document.${formName}.txtUlasanHidra.value == ""){
		alert('Sila masukkan Ulasan Hidraulik.');
  		document.${formName}.txtUlasanHidra.focus(); 
		return; 
	}
	if(document.${formName}.txtUlasanPasir.value == ""){
		alert('Sila masukkan Ulasan Kajian Kuantiti dan Kualiti Sumber Pasir.');
  		document.${formName}.txtUlasanPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtLuasKawasan.value == ""){
		alert('Sila masukkan Luas Kawasan.');
  		document.${formName}.txtLuasKawasan.focus(); 
		return; 
	}
	if(document.${formName}.socLuas.value == ""){
		alert('Sila pilih Jenis Luas.');
  		document.${formName}.socLuas.focus(); 
		return; 
	}
	if(document.${formName}.txtFeeLesen.value == ""){
		alert('Sila masukkan Fee Lesen.');
  		document.${formName}.txtFeeLesen.focus(); 
		return; 
	}
	if(document.${formName}.txtKmPersegi.value == ""){
		alert('Sila masukkan KM Persegi.');
  		document.${formName}.txtKmPersegi.focus(); 
		return; 
	}
	if(document.${formName}.txtWangCagaran.value == ""){
		alert('Sila masukkan Wang Cagaran Pematuhan Syarat Lesen.');
  		document.${formName}.txtWangCagaran.focus(); 
		return; 
	}
	if(document.${formName}.txtWangAmanah.value == ""){
		alert('Sila masukkan Wang Amanah Kebajikan Nelayan.');
  		document.${formName}.txtWangAmanah.focus(); 
		return; 
	}
	if(document.${formName}.txtIsipadu.value == ""){
		alert('Sila masukkan Isipadu Dibenarkan.');
  		document.${formName}.txtIsipadu.focus(); 
		return; 
	}
	if(document.${formName}.txtKadarRoyaltiPasir.value == ""){
		alert('Sila masukkan Kadar Royalti Pasir.');
  		document.${formName}.txtKadarRoyaltiPasir.focus(); 
		return; 
	}	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniBayaranSblm";
	document.${formName}.submit();
}
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function calcJumlahRoyaltiSeluruh(){
	var isipadu  = document.${formName}.txtIsipadu.value;	
	var kadarRoyalti  = document.${formName}.txtKadarRoyaltiPasir.value;	
	
	if(isipadu==''){
		alert('Sila masukkan Nilai Isipadu Terlehih Dahulu.');
  		document.${formName}.txtIsipadu.focus(); 
		return;
	}
	if(kadarRoyalti==''){
		alert('Sila masukkan Kadar Royalti Pasir Terlehih Dahulu.');
  		document.${formName}.txtKadarRoyaltiPasir.focus(); 
		return;
	}	
	
	var jumlahRoyalti = parseInt(isipadu)*  parseFloat(kadarRoyalti);
	document.${formName}.txtJumlahRoyaltiSeluruh.value = jumlahRoyalti.toFixed(2);	
}
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function kiraJumlahFeeLesen(){
	if(document.${formName}.txtLuasKawasan.value != '' && document.${formName}.socLuas.value != '' && document.${formName}.txtFeeLesen.value != '' && document.${formName}.txtKmPersegi.value != ''){  
		var jenisLuas = document.${formName}.socLuas.value;
	  	var luas = document.${formName}.txtLuasKawasan.value;
	  	var kmPersegi = document.${formName}.txtKmPersegi.value;
	  	var fee = document.${formName}.txtFeeLesen.value;
  
  		// untuk dapatkan fee bagi setiap 1 kilometerpersegi
  		var feeBagiKm = parseFloat(fee)/parseFloat(kmPersegi);
  
  		//hektar kepada kilometer persegi
  		if(jenisLuas == '2'){
			var luasConvert = parseFloat(luas) * (0.0038610215855);
			var jumlahFee = parseFloat(feeBagiKm)* parseFloat(luasConvert);
			document.${formName}.txtJumlahFeeLesen.value = jumlahFee.toFixed(2);
  		}
  		else if(jenisLuas == '1'){
			var jumlahFee = parseFloat(feeBagiKm)* parseFloat(luas);
			document.${formName}.txtJumlahFeeLesen.value = jumlahFee.toFixed(2);
  		}
	}
}
</script>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakSuratBayaranFeeLesen(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&idPermohonan="+idPermohonan+"&report=suratBayaranFeeLesen";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<input name="step" type="hidden" id="step"/>
<script>
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>
