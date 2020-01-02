<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idHakmilik" type="hidden" id="idHakmilik" value="$idHakmilik"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="hitButtonDaftar" type="hidden" id="hitButtonDaftar"/>
</p>
<body>
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
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if ($idFail != '' && $idStatus != '1610207')
  <tr>
    <td>&nbsp;
     <div class="error">PEMBAHARUAN LESEN TIDAK BOLEH DILAKUKAN BAGI FAIL INI</div></td>
  </tr>  
  #end 
 #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610235'  && $idStatus != '1610201'  && $idStatus != '1610213' && $idStatus != '1610205'  && $idStatus != '1610206' && $idStatus != '1610236' && $idStatus != '1610237' && $idStatus != '1610238' && $idStatus != '1610239' && $idStatus != '1610244')  
  #if ($flagAktif =='1')
  <tr>
    <td><fieldset>
      <legend>PENDAFTARAN PEMBAHARUAN LESEN</legend>
      #foreach($beanMaklumatPendaftaranPembaharuan in $BeanMaklumatPendaftaranPembaharuan)
      <table align="center" width="100%">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="30%" valign="top">Tarikh Mula Pembahruan</td>
          <td width="1%">:</td>
          <td width="68%"><input name="tarikhMula" type="text" class="$inputTextClass" id="tarikhMula" value="$beanMaklumatPendaftaranPembaharuan.tarikhMula" size="9" maxlength="10" $readonly />
            <a href="javascript:displayDatePicker('tarikhMula',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end
            <div id="tarikhTerima_check" style="color:red" ></div></td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Surat</td>
          <td>:</td>
          <td><input name="tarikhSurat" type="text" class="$inputTextClass" id="tarikhSurat" value="$beanMaklumatPendaftaranPembaharuan.tarikhSurat" size="9" maxlength="10" $readonly/>
            <a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td> #if ($mode == 'new')
            <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar" onClick="doProsesPembaharuan()"/>
            #end </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
  #if ($flagAktif =='0')
  <tr>
    <td><fieldset>
      <legend>PENDAFTARAN PEMBAHARUAN LESEN</legend>
      #foreach($beanMaklumatPembaharuan in $BeanMaklumatPembaharuan)
      <table align="center" width="100%">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="30%" valign="top">Tarikh Mula Pembahruan</td>
          <td width="1%">:</td>
          <td width="68%"><input type="text" name="tarikhMula" id="tarikhMula" value="$beanMaklumatPembaharuan.tarikhMula" size="10" $readonly class="$inputTextClass" onBlur="check_date(this)"/>
            <div id="tarikhTerima_check" style="color:red" ></div></td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Surat</td>
          <td>:</td>
          <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPembaharuan.tarikhSurat" onBlur="check_date(this)" size="10" $readonly class="$inputTextClass"/></td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td colspan="4" valign="bottom"></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
  #end
</table>
<script>
function doProsesPembaharuan() {
	if(document.${formName}.tarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula Pembahruan.');
  		document.${formName}.tarikhMula.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "new";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpan";
	document.${formName}.submit();
}
function doHantarProses(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "new";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarProses";
	document.${formName}.submit();
}
</script>
