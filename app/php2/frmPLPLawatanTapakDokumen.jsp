#if ($flagPopup == 'openLawatanTapak')
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>SURAT MINTA LAPORAN TAPAK</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatKJP in $BeanMaklumatKJP)
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Pejabat</td>
          <td>:</td>
          <td>$selectPejabat</td>
        </tr>
        #foreach ($beanMaklumatPejabat in $BeanMaklumatPejabat)
        <tr>
          <td>&nbsp;</td>
          <td>Nama Pejabat</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.namaPejabat</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.alamat1</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.alamat2</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.alamat3</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.poskod</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Bandar</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.bandar</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.negeri</td>
        </tr>
        #end        
        #if ($flagStatus == '1')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh Hantar</td>
          <td width="1%">:</td>
          <td width="70%">
          	<input name="txtTarikhHantar" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar" onBlur="check_date(this);calcDate()" value="$beanMaklumatKJP.tarikhHantar" size="9" maxlength="10">
	            #if ($modePopup != 'view') 
	            	<a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
	            #end            
           </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatKJP.jangkamasa" onBlur="validateNumber(this,this.value,'$beanMaklumatKJP.jangkamasa');calcDate()" $readonlyPopup class="$inputTextClassPopup">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhJangkaTerima" onBlur="check_date(this)" value="$beanMaklumatKJP.tarikhJangkaTerima" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhJangkaTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        #else
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh Hantar</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhHantar" type="text" readonly="readonly" class="disabled" id="txtTarikhHantar" value="$beanMaklumatKJP.tarikhHantar" size="9" maxlength="10"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatKJP.jangkamasa" readonly="readonly" class="disabled">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" readonly="readonly" class="disabled" id="txtTarikhJangkaTerima" value="$beanMaklumatKJP.tarikhJangkaTerima" size="9" maxlength="10"></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($flagStatus == '2')
        <tr>
          <td colspan="4"><fieldset>
            <legend>MAKLUMBALAS</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Terima</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhTerima" onBlur="check_date(this)" value="$beanMaklumatKJP.tarikhTerima" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Surat</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhSurat" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhSurat" onBlur="check_date(this)" value="$beanMaklumatKJP.tarikhSurat" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td>No Rujukan Surat</td>
                <td>:</td>
                <td><input type="text" name="txtNoRujukan" id="txtNoRujukan" $readonlyPopup class="$inputTextClassPopup" size="50" value="$beanMaklumatKJP.noRujukan" onblur="this.value=this.value.toUpperCase();"/></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Ulasan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtUlasan" id="txtUlasan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup">$beanMaklumatKJP.ulasan</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($modePopup == 'new')
      <input name="cmdSimpan" type="button" onClick="doSimpanMaklumatKJP()" value="Simpan">
      <input name="cmdBatal" type="button" onClick="doBatalTambahBaruKJP()" value="Batal">
      #end
      #if ($modePopup == 'newUlangan')
      <input name="cmdSimpan" type="button" onClick="doSimpanMaklumatUlanganKJP()" value="Simpan">
      <input name="cmdBatal" type="button" onClick="javascript:paparMaklumatKJP('$idUlasanTeknikal')" value="Batal">
      #end
      #if ($modePopup == 'view')
      #if ($flagAktif != 'T')
      <input name="cmdKemaskini" type="button" onClick="doKemaskiniMaklumatKJP()" value="Kemaskini">
      <input name="cmdHapus" type="button" onClick="doHapusMaklumatKJP()" value="Hapus">
      #if ($flagStatus == '1')
      <input name="cmdTerima" type="button" onClick="doTerimaKJP()" value="Terima">
      <input name="cmdUlangan" type="button" onClick="doUlanganKJP()" value="Ulangan">
      #end
      #end
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpan2" type="button" onclick="doSimpanKemaskiniMaklumatKJP()" value="Simpan" />
      <input name="cmdBatal" type="button" onClick="javascript:paparMaklumatKJP('$idUlasanTeknikal')" value="Batal">
      #end </td>
  </tr>
</table>
#end

<table width="100%" border="0" cellspacing="2" cellpadding="2">
<tr>
    <td><fieldset>
      <legend><b>SENARAI DOKUMEN</b></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="9" scope="row"><input name="cmdTambah" type="button" value="Tambah" onclick="javascript:doDaftarDokumenLawatanTapak()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="32%"><strong>Nama Dokumen</strong></td>
          <!-- <td width="32%"><strong>Agensi</strong></td> -->
          <td width="15%" align="center"><strong>Tarikh Hantar</strong></td>
          <td width="15%" align="center"><strong>Tarikh Dijangka Terima</strong></td>
          <td width="15%"><strong>Status</strong></td>
          <td width="10%"><strong>Tindakan</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiDokumenLawatanTapak.size() > 0)
        #foreach ($list in $SenaraiDokumenLawatanTapak)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          #if ($list.bilUlangan == '' || $list.bilUlangan == '0')
          <td class="$row"><a href="javascript:paparMaklumatKJP('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a>&nbsp;&nbsp;<font class="blink" ><span class="style3">$!list.statusHari</span></font></td>
          #else
          <td class="$row"><a href="javascript:paparMaklumatKJP('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a>&nbsp;&nbsp;(ULANGAN $list.bilUlangan)&nbsp;&nbsp;<font class="blink" ><span class="style3">$!list.statusHari</span></font></td>
          #end
          <!-- <td class="$row">$list.agensi</td> -->
          <td class="$row" align="center">$list.tarikhHantar</td>
          <td class="$row" align="center">$list.tarikhJangkaTerima</td>
          <td class="$row">$list.status</td>
          #if ($list.bilUlangan == '' || $list.bilUlangan == '0')
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakPLPSMUKJP('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
          #else
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakPLPSuratUlanganKJP('$idFail','$list.idUlasanTeknikal','$list.bilUlangan')"><img border="0" src="../img/print.gif"/></a></td>
          #end </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>  

<script>
function doBatalTambahBaruKJP(){
	document.${formName}.hitButton.value = "doBatalTambahBaruKJP";
	doAjaxCall${formName}("");
}
function doTerimaKJP(){
	document.${formName}.flagPopup.value = "openLawatanTapak";
	document.${formName}.modePopup.value = "update";
	document.${formName}.flagStatus.value = "2";
	doAjaxCall${formName}("");
}
function doHapusMaklumatKJP(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatKJP";
	doAjaxCall${formName}("");
} 
function doSimpanMaklumatUlanganKJP(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.flagPopup.value = "openLawatanTapak";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatUlanganKJP";
	doAjaxCall${formName}("");
}
function doUlanganKJP(){
	document.${formName}.flagPopup.value = "openLawatanTapak";
	document.${formName}.modePopup.value = "newUlangan";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
function doChangeAgensiByKementerian(){
	document.${formName}.changeDropdown.value = "changeAgensiByKementerian";
	doAjaxCall${formName}("");  
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangePejabat() {
	doAjaxCall${formName}("doChangePejabat");
}
function doDaftarDokumenLawatanTapak(){
	document.${formName}.flagPopup.value = "openLawatanTapak";
	document.${formName}.modePopup.value = "new";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatKJP(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.flagPopup.value = "openLawatanTapak";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanDokumenLawatanTapak";
	doAjaxCall${formName}("");
}
  
function doSimpanKemaskiniMaklumatKJP(){
	if (document.${formName}.flagStatus.value == "2"){
			if (document.${formName}.txtTarikhTerima.value ==""){
				!window.confirm("Sila masukkan Tarikh Terima.");
				return;
			}
			if(document.${formName}.txtTarikhSurat.value ==""){
				!window.confirm("Sila masukkan Tarikh Surat.");
				return;
			}
			if (document.${formName}.txtNoRujukan.value ==""){
				!window.confirm("Sila masukkan No Rujukan Surat.");
				return;
			}
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.flagPopup.value = "openLawatanTapak";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatKJP";
	doAjaxCall${formName}("");	
}  
  
 function paparMaklumatKJP(idUlasanTeknikal){
	document.${formName}.flagPopup.value = "openLawatanTapak";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	doAjaxCall${formName}("");
}  
 
 function doKemaskiniMaklumatKJP(){
		document.${formName}.flagPopup.value = "openLawatanTapak";
		document.${formName}.modePopup.value = "update";
		doAjaxCall${formName}("");
  }
 
  function calcDate(){
		if (document.${formName}.txtTarikhHantar.value != "" && document.${formName}.txtJangkaMasa.value != ""){
			
			var tarikhHantar  = document.${formName}.txtTarikhHantar.value;
			var days  = parseInt(document.${formName}.txtJangkaMasa.value);
			
			var dt1   = parseInt(tarikhHantar.substring(0,2),10) + days;
			var mon1  = parseInt(tarikhHantar.substring(3,5),10)-1;
			var yr1   = parseInt(tarikhHantar.substring(6,10),10);
		 
			var myDate = new Date(yr1, mon1, dt1);
			
			var day = myDate.getDate();
			var month = myDate.getMonth()+1;
			var year = myDate.getFullYear();
			
			var tarikhJangkaTerima = "";
			if(month>=10){
				if(day>=10){
					tarikhJangkaTerima = day + "/" + month + "/" + year;	
				} else {
					tarikhJangkaTerima = "0"+ day + "/" + month + "/" + year;	
				}				
			} else {
				if(day>=10){
					tarikhJangkaTerima = day + "/0" + month + "/" + year;	
				} else {
					tarikhJangkaTerima = "0"+ day + "/0" + month + "/" + year;	
				}
			}
			document.${formName}.txtTarikhJangkaTerima.value = tarikhJangkaTerima;
		
		} else {
			document.${formName}.txtTarikhJangkaTerima.value = "";
		}
	}  
  
  function cetakPLPSMUKJP(idFail,idUlasanTeknikal) {
		var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratLT&idUlasanTeknikal="+idUlasanTeknikal;
	    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}
  
function cetakPLPSuratUlanganKJP(idFail,idUlasanTeknikal,bilUlangan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratUlanganLT&idUlasanTeknikal="+idUlasanTeknikal+"&bilUlangan="+bilUlangan;
	var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	   hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
	   hWnd.focus();
}  
</script>