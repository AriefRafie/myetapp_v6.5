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
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
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
      <legend><strong>MAKLUMAT KEPUTUSAN MENTERI</strong></legend>
      #foreach($beanKeputusanMenteri in $BeanKeputusanMenteri)
      #set($tarikhKeputusan = $beanKeputusanMenteri.tarikhKeputusan)
      #set($keputusanMenteri = $beanKeputusanMenteri.keputusanMenteri)
      #end
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right">Tarikh Keputusan :</td>
                <td width="63%"><strong>$tarikhKeputusan</strong></td>
              </tr>
            </table></td>
          <td width="50%"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right">Keputusan Menteri :</td>
                <td width="63%"><strong> #if ($keputusanMenteri == 'S')
                  SETUJU
                  #elseif ($keputusanMenteri == 'T')
                  TIDAK SETUJU
                  #end </strong></td>
              </tr>
            </table></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>SEJARAH PERLANJUTAN TEMPOH KELULUSAN DASAR</strong></legend>
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td align="center"><strong>Tarikh Surat</strong></td>
          <td align="center"><strong>Tempoh (Bulan)</strong></td>
          <td align="center"><strong>Tarikh Tamat Kelulusan Dasar</strong></td>
        </tr>
        #set ($listSejarahPerlanjutan = "")
        #set ( $count = 0 )
        #if ($SenaraiPerlanjutanTempoh.size() > 0)
        #foreach ($listSejarahPerlanjutan in $SenaraiPerlanjutanTempoh)
        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$count</td>
          <td align="center" class="$row">$listSejarahPerlanjutan.tarikhSurat</td>
          <td align="center" class="$row">$listSejarahPerlanjutan.tempoh</td>
          <td align="center" class="$row">$listSejarahPerlanjutan.tarikhTamatKelulusanDasar</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td align="center" class="row1">&nbsp;</td>
          <td align="center" class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT SYARAT-SYARAT KELULUSAN DASAR</strong></legend>
      #foreach($beanKelulusanDasar in $BeanKelulusanDasar)
      <table>
        #if($!mode == 'daftarPerlanjutanTempoh')
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="33%">Tarikh Surat</td>
          <td width="1%">:</td>
          <td width="65%"><input name="txtTarikhSurat" type="text" class="$inputTextClass" id="txtTarikhSurat" onBlur="check_date(this);calcDate();" value="" size="9" maxlength="10" $readonly >
            <a href="javascript:displayDatePicker('txtTarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Tempoh Kelulusan Dasar</td>
          <td>:</td>
          <td><input type="text" name="txtTempohKelulusanDasar" id="txtTempohKelulusanDasar" size="1" maxlength="2" value="" onBlur="validateNumber(this,this.value);calcDate();" $readonly class="$inputTextClass">
            Bulan</td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Tarikh Tamat Kelulusan Dasar</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhTamatKelulusanDasar" type="text" class="$inputTextClass" id="txtTarikhTamatKelulusanDasar" onBlur="check_date(this)" value="" size="9" maxlength="10" $readonly>
            <a href="javascript:displayDatePicker('txtTarikhTamatKelulusanDasar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        #else
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="33%">Tarikh Surat</td>
          <td width="1%">:</td>
          <td width="65%"><input name="txtTarikhSurat" type="text" class="$inputTextClass" id="txtTarikhSurat" onBlur="check_date(this);calcDate();" value="$beanKelulusanDasar.tarikhSurat" size="9" maxlength="10" $readonly >
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Tempoh Kelulusan Dasar</td>
          <td>:</td>
          <td><input type="text" name="txtTempohKelulusanDasar" id="txtTempohKelulusanDasar" size="1" maxlength="2" value="$!beanKelulusanDasar.tempohKelulusanDasar" onBlur="validateNumber(this,this.value);calcDate();" $readonly class="$inputTextClass">
            Bulan</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Tarikh Tamat Kelulusan Dasar</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhTamatKelulusanDasar" type="text" class="$inputTextClass" id="txtTarikhTamatKelulusanDasar" onBlur="check_date(this)" value="$beanKelulusanDasar.tarikhTamatKelulusanDasar" size="9" maxlength="10" $readonly>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhTamatKelulusanDasar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        #end
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Luas Kawasan Yang Dipohon</td>
          <td width="1%">:</td>
          <td width="70%">$!beanKelulusanDasar.luasDipohon</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode!= 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Luas Kawasan Yang Diluluskan</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtLuasKawasan" type="text" class="$inputTextClass" id="txtLuasKawasan" value="$beanKelulusanDasar.txtLuasKawasan" size="10" maxlength="16" $readonly onkeyup="validateNumber(this,this.value,'$beanKelulusanDasar.txtLuasKawasan')" onblur="kiraBatuNautika();kiraJumlahFeeLesen()"/>
            $selectLuas bersamaan
            <input name="txtLuasNautika" type="text" class="disabled" id="txtLuasNautika" value="$beanKelulusanDasar.txtLuasNautika" size="10" maxlength="20" readonly style="text-align:right" onkeyup="validateNumber(this,this.value,'$beanKelulusanDasar.txtLuasNautika')"/>
            batu nautika</td>
        </tr>
        <tr>
          <td>#if ($mode!= 'view')<span class="style1">*</span>#end</td>
          <td>Fee Lesen</td>
          <td>:</td>
          <td>RM
            <input name="txtFeeLesen" type="text" class="$inputTextClass" id="txtFeeLesen" style="text-align:right" onblur="validateCurrency(this,this.value,'$beanKelulusanDasar.txtFeeLesen');kiraJumlahFeeLesen()" value="$beanKelulusanDasar.txtFeeLesen" size="9" maxlength="37" $readonly/>
            bagi setiap
            <input name="txtKmPersegi" type="text" class="$inputTextClass" id="txtKmPersegi" value="$beanKelulusanDasar.txtKmPersegi" size="9" maxlength="16" $readonly style="text-align:right" onblur="kiraJumlahFeeLesen()"/>
            km persegi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jumlah Fee Lesen</td>
          <td>:</td>
          <td>RM
            <input name="txtJumlahFeeLesen" type="text" id="txtJumlahFeeLesen" style="text-align:right" onblur="validateCurrency(this,this.value,'$beanKelulusanDasar.txtJumlahFeeLesen')" value="$beanKelulusanDasar.txtJumlahFeeLesen" size="9" maxlength="37" readonly class="disabled"/></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Wang Cagaran Pematuhan Syarat Lesen</td>
          <td>:</td>
          <td>RM
            <input name="txtWangCagaran" type="text" class="$inputTextClass" id="txtWangCagaran" style="text-align:right" onblur="validateCurrency(this,this.value,'$beanKelulusanDasar.txtWangCagaran')" value="$beanKelulusanDasar.txtWangCagaran" size="9" maxlength="37" $readonly/>          </td>
        </tr>
        <tr>
          <td>#if ($mode!= 'view')<span class="style1">*</span>#end</td>
          <td>Wang Amanah Kebajikan Nelayan</td>
          <td>:</td>
          <td>RM
            <input name="txtWangAmanah" type="text" class="$inputTextClass" id="txtWangAmanah" style="text-align:right" onblur="validateCurrency(this,this.value,'$beanKelulusanDasar.txtWangAmanah')" value="$beanKelulusanDasar.txtWangAmanah" size="9" maxlength="37" $readonly/></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Kadar Royalti Pasir</td>
          <td>:</td>
          <td>RM
            <input name="txtKadarRoyaltiPasir" type="text" class="$inputTextClass" id="txtKadarRoyaltiPasir" value="$beanKelulusanDasar.txtKadarRoyaltiPasir" size="10" maxlength="37" $readonly onblur="validateCurrency(this,this.value,'$beanKelulusanDasar.txtKadarRoyaltiPasir')"  style="text-align:right"/>
            /meter padu</td>
        </tr>
        #if ($mode == 'update')
        <tr>
          <td colspan="4" >&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>#if ($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="doKemaskini()"/>
            #if($idStatus =='1615198')
            <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Daftar Perlanjutan Tempoh" onClick="doDaftarPerlanjutanTempoh()"/>
            <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
            #else
            <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Kutipan Data Perlanjutan Tempoh" onClick="doKutipanDataPerlanjutanTempoh()"/>
            #end            
            <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="doSimpanKemaskini()"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
            #end
            #if ($mode == 'daftarPerlanjutanTempoh')
            <input type="button" name="cmdSimpanDaftarPerlanjutan" id="cmdSimpanDaftarPerlanjutan" value="Simpan" onclick="doSimpanPerlanjutan()"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
            #end
            #if ($mode == 'kutipanDataPerlanjutanTempoh')
            <input type="button" name="cmdSimpanDaftarPerlanjutan" id="cmdSimpanDaftarPerlanjutan" value="Simpan" onclick="doSimpanKutipanDataPerlanjutanTempoh()"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
            #end </td>
        </tr>
      </table>
      #end
      </fieldset></td>
  </tr>
  #elseif ($idFail != '')
  <tr>
    <td> 
      <div class="warning"><strong>STATUS FAIL MASIH DI PERINGKAT $!status</strong></div></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratKelulusanDasar('$idFail','$idPermohonan')"> Surat Kelulusan Dasar </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakEdaranLuarSuratKelulusanDasar('$idFail','$idPermohonan')"> Senarai Edaran Luar </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakLampiranASuratKelulusanDasar('$idFail','$idPermohonan')"> Lampiran A (Syarat-syarat Pertimbangan Dasar) </a></td>
  </tr>
</table>
</fieldset>
<script>
function doKemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doDaftarPerlanjutanTempoh() {
	document.${formName}.mode.value = "daftarPerlanjutanTempoh";
	doAjaxCall${formName}("");
}
function doKutipanDataPerlanjutanTempoh() {
	document.${formName}.mode.value = "kutipanDataPerlanjutanTempoh";
	doAjaxCall${formName}("");
}
function validateNumber(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
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
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function doSimpanKemaskini() {
	
	if(document.${formName}.txtTarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.txtTarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtTempohKelulusanDasar.value == ""){
		alert('Sila masukkan Tempoh Kelulusan Dasar.');
  		document.${formName}.txtTempohKelulusanDasar.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTamatKelulusanDasar.value == ""){
		alert('Sila masukkan Tarikh Tamat Kelulusan Dasar.');
  		document.${formName}.txtTarikhTamatKelulusanDasar.focus(); 
		return; 
	}
	if(document.${formName}.txtLuasKawasan.value == ""){
		alert('Sila masukkan Luas Kawasan Yang Diluluskan.');
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
	document.${formName}.hitButton.value = "doSimpanKemaskini";
	document.${formName}.submit();
}
function doSimpanPerlanjutan() {
	
	if(document.${formName}.txtTarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.txtTarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtTempohKelulusanDasar.value == ""){
		alert('Sila masukkan Tempoh Kelulusan Dasar.');
  		document.${formName}.txtTempohKelulusanDasar.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTamatKelulusanDasar.value == ""){
		alert('Sila masukkan Tarikh Tamat Kelulusan Dasar.');
  		document.${formName}.txtTarikhTamatKelulusanDasar.focus(); 
		return; 
	}
	if(document.${formName}.txtLuasKawasan.value == ""){
		alert('Sila masukkan Luas Kawasan Yang Diluluskan.');
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
	document.${formName}.hitButton.value = "doSimpanPerlanjutan";
	document.${formName}.submit();
}
function doSimpanKutipanDataPerlanjutanTempoh() {
	
	if(document.${formName}.txtTarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.txtTarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtTempohKelulusanDasar.value == ""){
		alert('Sila masukkan Tempoh Kelulusan Dasar.');
  		document.${formName}.txtTempohKelulusanDasar.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTamatKelulusanDasar.value == ""){
		alert('Sila masukkan Tarikh Tamat Kelulusan Dasar.');
  		document.${formName}.txtTarikhTamatKelulusanDasar.focus(); 
		return; 
	}
	if(document.${formName}.txtLuasKawasan.value == ""){
		alert('Sila masukkan Luas Kawasan Yang Diluluskan.');
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
	document.${formName}.hitButton.value = "doSimpanKutipanDataPerlanjutanTempoh";
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
function kiraBatuNautika(){
  if(document.${formName}.txtLuasKawasan.value != '' && document.${formName}.socLuas.value != ''){
	  var jenisLuas = document.${formName}.socLuas.value;
	  var luas = document.${formName}.txtLuasKawasan.value;
	  var jumlahLuas = '';
	  //kilometer persegi kepada batu nautika
	  if(jenisLuas == '1'){
		jumlahLuas = parseFloat(luas) * (0.2915533496);
		document.${formName}.txtLuasNautika.value = jumlahLuas.toFixed(4);	
	
	  }
	  //hektar kepada batu nautika
	  if(jenisLuas == '2'){
		jumlahLuas = parseFloat(luas) * (0.002915533496);
		document.${formName}.txtLuasNautika.value = jumlahLuas.toFixed(4);	
	
	  }
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
function cetakSuratKelulusanDasar(idFail,idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&idPermohonan="+idPermohonan+"&report=suratKelulusanDasar";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakEdaranLuarSuratKelulusanDasar(idFail,idPermohonan) {
	var url = "../servlet/ekptg.report.php2.APBEdaranLuarSuratKelulusanDasar?ID_FAIL="+idFail+"&ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakLampiranASuratKelulusanDasar(idFail,idPermohonan) {
	var url = "../servlet/ekptg.report.php2.APBLampiranASuratKelulusanDasar?ID_FAIL="+idFail+"&ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function calcDate(){
	if (document.${formName}.txtTarikhSurat.value != "" && document.${formName}.txtTempohKelulusanDasar.value != ""){
		
		var tarikhMula  = document.${formName}.txtTarikhSurat.value;
		var month  = parseInt(document.${formName}.txtTempohKelulusanDasar.value);
		
		var dt1   = parseInt(tarikhMula.substring(0,2),10);
		var mon1  = parseInt(tarikhMula.substring(3,5),10)-1 + month;
		var yr1   = parseInt(tarikhMula.substring(6,10),10);
	 
		var myDate = new Date(yr1, mon1, dt1);
		myDate.setDate(myDate.getDate()-1);
		
		var day = myDate.getDate();
		var month = myDate.getMonth()+1;
		var year = myDate.getFullYear();
		
		var tarikhTamat = "";
		if(month>=10){
			if(day>=10){
				tarikhTamat = day + "/" + month + "/" + year;	
			} else {
				tarikhTamat = "0"+ day + "/" + month + "/" + year;	
			}				
		} else {
			if(day>=10){
				tarikhTamat = day + "/0" + month + "/" + year;	
			} else {
				tarikhTamat = "0"+ day + "/0" + month + "/" + year;	
			}
		}
		document.${formName}.txtTarikhTamatKelulusanDasar.value = tarikhTamat;
	
	} else {
		document.${formName}.txtTarikhTamatKelulusanDasar.value = "";
	}
}
</script>
<input name="step" type="hidden" id="step"/>
<script>
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>
