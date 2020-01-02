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
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idPengadu" type="hidden" id="idPengadu" value="$idPengadu"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="actionAduan" type="hidden" id="actionAduan" value="$actionAduan"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  <tr>
    <td> #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610235'  && $idStatus != '1610201'  && $idStatus != '1610213' && $idStatus != '1610205'  && $idStatus != '1610206' && $idStatus != '1610236' && $idStatus != '1610237' && $idStatus != '1610238' && $idStatus != '1610239'  && $idStatus != '1610244')
      <div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PENDAFTARAN</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PENGADU</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT ADUAN</li>
          <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">LAPORAN/ULASAN JKPTG</li>
          <li onClick="doChangeTab(4);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT KEPUTUSAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> <br>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanAduan in $BeanAduan)
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%" valign="top">Urusan</td>
                <td width="1%">:</td>
                <td width="70%">AKTA PELANTAR BENUA </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Pendaftaran Melalui</td>
                <td>:</td>
                <td><select name="socJenisDaftar" id="socJenisDaftar" style="width:110px;" $readonly class="$inputTextClass" $inputTextClass >
                          
        	     #if($beanAduan.socJenisDaftar=='1')
                   
                  <option value="">SILA PILIH</option>
                  <option value="1" selected="selected">1 - TELEFON</option>
                  <option value="2">2 - EMAIL</option>
                  
                #elseif ($beanAduan.socJenisDaftar=='2')
                   
                  <option value="">SILA PILIH</option>
                  <option value="1">1 - TELEFON</option>
                  <option value="2" selected="selected">2 - EMAIL</option>
                                
                #elseif ($beanAduan.socJenisDaftar=='')
                   
                  <option value="" selected="selected">SILA PILIH</option>
                  <option value="1">1 - TELEFON</option>
                  <option value="2">2 - EMAIL</option>
                  
                #end                    
             
                </select>
                </td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td valign="top">Tarikh Terima</td>
                <td>:</td>
                <td><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanAduan.tarikhTerima" onblur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass"/>
                  #if ($mode != 'view') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td valign="top">Tarikh Surat</td>
                <td>:</td>
                <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanAduan.tarikhSurat" onblur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
                  <a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td width="1%"></td>
                <td valign="top">No. Rujukan Surat</td>
                <td>:</td>
                <td><input type="text" name="txtRujSurat" id="txtRujSurat" value="$beanAduan.rujSurat" $readonly class="$inputTextClass" />                </td>
              </tr>
              <tr>
                <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td valign="top">Perkara</td>
                <td valign="top">:</td>
                <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass">$beanAduan.perkara</textarea></td>
              </tr>
              #if ($mode == 'update')
              #end
              #if ($mode != 'view')
              <tr>
                <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
              </tr>
              #end
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td> #if ($mode == 'view')
                  <input type="button" name="cmdKemasknDaftar" id="cmdKemasknDaftar" value="Kemaskini" onClick="kemaskiniMaklumatDaftar()"/>
                  #end
                  #if ($mode == 'update')
                  <input type="button" name="cmdSimpanDaftar" id="cmdSimpanDaftar" value="Simpan" onClick="simpanKemaskiniMaklumatDaftar()"/>
                  <input type="button" name="cmdBatalDaftar" id="cmdBatalDaftar" value="Batal" onClick="batalMaklumatDaftar()"/>
                  #end </td>
              </tr>
              #end
            </table>
          </div>
          <div class="TabbedPanelsContent"> <br>
            #foreach ($beanMaklumatPengadu in $BeanMaklumatPengadu)
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Kategori Pengadu</td>
                <td>:</td>
                <td width="70%">$selectKategoriPengadu</td>
              </tr>
              #if ($idKategoriPengadu == '3')
              <tr>
                <td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
                <td width="28%">Kementerian</td>
                <td>:</td>
                <td width="70%">$selectKementerian
                  <input name="idKementerian" type="hidden" value="$idKementerian"/></td>
              </tr>
              <tr>
                <td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
                <td>Agensi</td>
                <td>:</td>
                <td>$selectAgensi
                  <input name="idAgensi" type="hidden" value="$idAgensi" /></td>
              </tr>
              #foreach ($beanMaklumatAgensi in $BeanMaklumatAgensi)
              <tr>
                <td>&nbsp;</td>
                <td>Nama Agensi</td>
                <td>:</td>
                <td>$beanMaklumatAgensi.namaAgensi</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Alamat</td>
                <td>:</td>
                <td>$beanMaklumatAgensi.alamat1</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>:</td>
                <td>$beanMaklumatAgensi.alamat2</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>:</td>
                <td>$beanMaklumatAgensi.alamat3</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Poskod</td>
                <td>:</td>
                <td>$beanMaklumatAgensi.poskod</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Negeri</td>
                <td>:</td>
                <td>$beanMaklumatAgensi.negeri</td>
              </tr>
              #end
              #elseif(($idKategoriPengadu == '1') || ($idKategoriPengadu == '2') ||($idKategoriPengadu == '9'))
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Nama Pengadu</td>
                <td>:</td>
                <td><input type="text" name="txtNama" id="txtNama" value="$beanMaklumatPengadu.nama" $readonly class="$inputTextClass" size="45" /></td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Alamat</td>
                <td>:</td>
                <td><input type="text" name="txtAlamat1" id="txtAlamat1" value="$beanMaklumatPengadu.alamat1"  $readonly class="$inputTextClass" size="45"/></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>:</td>
                <td><input type="text" name="txtAlamat2" id="txtAlamat2" value="$beanMaklumatPengadu.alamat2"  $readonly class="$inputTextClass" size="45" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>:</td>
                <td><input type="text" name="txtAlamat3" id="txtAlamat3" value="$beanMaklumatPengadu.alamat3"  $readonly class="$inputTextClass" size="45" /></td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Poskod</td>
                <td>:</td>
                <td><input type="text" name="txtPoskod" id="txtPoskod" size="4" onkeyup="validatePoskod(this,this.value);" maxlength="5" value="$beanMaklumatPengadu.poskod" $readonly class="$inputTextClass"/></td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Negeri</td>
                <td>:</td>
                <td>$selectNegeri</td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1"></span>#end</td>
                <td>No. Tel. (HP)</td>
                <td>:</td>
                <td><input type="text" name="txtNoHp" id="txtNoHp" value="$beanMaklumatPengadu.txtNoHp" size="14" maxlength="12" onkeyup="validateNumber(this,this.value);" $readonly class="$inputTextClass" /></td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<span class="style1"></span>#end</td>
                <td>No. Tel. (R)</td>
                <td>:</td>
                <td><input type="text" name="txtNoTelefon" id="txtNoTelefon" value="$beanMaklumatPengadu.txtNoTelefon" size="14" maxlength="12" onkeyup="validateNumber(this,this.value);" $readonly class="$inputTextClass" /></td>
              </tr>              
              <tr>
                <td>#if ($mode != 'view')<span class="style1"></span>#end</td>
                <td>Email</td>
                <td>:</td>
                <td><input type="text" name="txtEmail" id="txtEmail" value="$beanMaklumatPengadu.txtEmail" $readonly class="$inputTextClass" size="45" /></td>
              </tr>              
              
              #end
              
              <P>
              
              #if ($mode != 'view')
              <tr>
                <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
              </tr>
              #end
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td> #if ($mode == 'view')
                  <input type="button" name="cmdKemaskiniP" id="cmdKemaskiniP" value="Kemaskini" onClick="kemaskiniMaklumatPengadu()"/>
                  <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                  #end
                  #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskiniP" id="cmdSimpanKemaskiniP" value="Simpan" onClick="simpanKemaskiniMaklumatPengadu()"/>
                  <input type="button" name="cmdBatalKemaskiniP" id="cmdBatalKemaskiniP" value="Batal" onClick="batalMaklumatPengadu()"/>
                  #end </td>
              </tr>
            </table>
            #end
            <fieldset id="tableReport" style="display:none;">
            <legend><strong>SENARAI LAPORAN</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td ><a href="#" class="style2" onClick="javascript:cetakAkuanTerimaAduan('$idFail')"> Surat Akuan Penerimaan </a></td>
              </tr>
              <tr>
                <td ><a href="#" class="style2" onClick="javascript:cetakSuratAPMMAduan('$idFail')"> Surat Kepada APMM </a></td>
              </tr>
            </table>
            </fieldset>
          </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBMaklumatAduanDetail.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBLaporanSiasatanAduan.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmAPBKeputusanAduan.jsp") </div>
        </div>
      </div>
      #end </td>
  </tr>
</table>
<script type="text/javascript">
#if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610235'  && $idStatus != '1610201'  && $idStatus != '1610213' && $idStatus != '1610205'  && $idStatus != '1610206' && $idStatus != '1610236' && $idStatus != '1610237' && $idStatus != '1610238' && $idStatus != '1610239'  && $idStatus != '1610244')  
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeTab(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.submit();
}
function doChangeKategoriPengadu() {
	doAjaxCall${formName}("doChangeKategoriPengadu");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeAgensi() {
	doAjaxCall${formName}("doChangeAgensi");
}
function cekTarikhTerima(elmnt) {
//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var currentDate = new Date();
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		elmnt.value ="";
		document.${formName}.tarikhTerima.focus(); 
		return;
	}
}
function cekTarikhSurat(elmnt) {
//CHECK DATE   
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
}
function kemaskiniMaklumatPengadu() {
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMaklumatPengadu() {
	if(document.${formName}.socKategoriPengadu.value == ""){
		alert('Sila pilih Jenis Kategori Pengadu.');
  		document.${formName}.socKategoriPengadu.focus(); 
		return; 
	}
	
	if (document.${formName}.socKategoriPengadu.value == '3'){
		
		if(document.${formName}.socKementerian.value == ""){
			alert('Sila pilih Kementerian.');
			document.${formName}.socKementerian.focus(); 
			return; 
	    }
		if(document.${formName}.socAgensi.value == ""){
			alert('Sila pilih Agensi.');
			document.${formName}.socAgensi.focus(); 
			return; 
		}
	}
	else {
		
		if(document.${formName}.txtNama.value == ""){
			alert('Sila masukkan Nama Pengadu.');
			document.${formName}.txtNama.focus(); 
			return; 
		}
		if(document.${formName}.txtAlamat1.value == ""){
			alert('Sila masukkan Alamat.');
			document.${formName}.txtAlamat1.focus(); 
			return; 
		}
		if(document.${formName}.txtPoskod.value == ""){
			alert('Sila masukkan Poskod.');
			document.${formName}.txtPoskod.focus(); 
			return; 
		}
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih Negeri.');
			document.${formName}.socNegeri.focus(); 
			return; 
		}
	} 
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPengadu";
	document.${formName}.submit();
}
function batalMaklumatPengadu() {
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function kemaskiniMaklumatDaftar() {
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMaklumatDaftar() {
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhTerima.focus(); 
		return;
	}
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
		
	if(document.${formName}.tarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.tarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus(); 
		return; 
	}
	
	else{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	
		document.${formName}.actionAduan.value = "maklumatAduan";
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "doSimpanKemaskiniBasicAduan";
		document.${formName}.submit();
	}
}
function batalMaklumatDaftar() {
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function setTable(id){

	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakAkuanTerimaAduan(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=SuratAkuanTerimaAduan";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratAPMMAduan(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=SuratAPMMAduan";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakKertasRingkasanAduan(idFail,idAduan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=SuratKertasRingkasanAduan&idAduan="+idAduan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratAmaranAduan(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=SuratAmaranAduan";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratTamatLesenAduan(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=SuratTamatLesenAduan";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratTunjukSebabAduan(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=SuratTunjukSebabAduan";
	var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
</script>
