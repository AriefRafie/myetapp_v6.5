<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionMonitoring" type="hidden" id="actionMonitoring" value="$actionMonitoring"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTab" type="hidden" id="selectedTab" value="$selectedTab"/>
  <input name="idPerisian" type="hidden" id="idPerisian" value="$idPerisian"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
</p>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT LESEN</li>
    <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">DOKUMEN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td colspan="2"><fieldset>
            <legend><strong>MAKLUMAT LESEN</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanMaklumatPerisian in $BeanMaklumatPerisian)
              <tr>
                <td width="1%">#if ($mode != 'view') <span class="style1">*</span> #end</td>
                <td width="28%" valign="top">Kategori</td>
                <td width="1%">:</td>
                <td width="70%">$selectKategori</td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view') <span class="style1">*</span> #end</td>
                <td width="28%" valign="top">Nama Lesen</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtNamaPerisian" type="text" class="$inputTextClass" id="txtNamaPerisian" value="$beanMaklumatPerisian.namaPerisian" $readonly onblur="this.value=this.value.toUpperCase();" size="50" maxlength="50"/></td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view') <span class="style1">*</span> #end</td>
                <td width="28%" valign="top">Nama Pengeluar</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtNamaPengeluar" type="text" class="$inputTextClass" id="txtNamaPengeluar" value="$beanMaklumatPerisian.namaPengeluar" $readonly onblur="this.value=this.value.toUpperCase();" size="50" maxlength="50"/></td>
              </tr>
              <tr>
                <td width="1%" valign="top">&nbsp;</td>
                <td valign="top">Keterangan</td>
                <td valign="top">:</td>
                <td><textarea name="txtKeterangan" id="txtKeterangan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();" >$beanMaklumatPerisian.keterangan</textarea></td>
              </tr>
              <tr>
                <td>#if ($mode != 'view') <span class="style1">*</span> #end</td>
                <td width="28%">Nombor Siri</td>
                <td>:</td>
                <td width="70%"><input name="txtNoSiri" type="text" class="$inputTextClass" id="txtNoSiri" value="$beanMaklumatPerisian.noSiri" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td width="28%"><em>CD Key</em></td>
                <td>:</td>
                <td width="70%"><input name="txtCdKey" type="text" class="$inputTextClass" id="txtCdKey" value="$beanMaklumatPerisian.cdKey" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>
                </td>
              </tr>
              <tr>
                <td>#if ($mode != 'view') <span class="style1">*</span> #end</td>
                <td width="28%">Harga</td>
                <td>:</td>
                <td width="70%">RM
                  <input type="text" name="txtHarga" id="txtHarga" value="$beanMaklumatPerisian.harga" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerisian.harga');" />
                </td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view') <span class="style1">*</span> #end</td>
                <td valign="top">Tarikh Pembelian</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBeli" id="txdTarikhBeli" value="$beanMaklumatPerisian.tarikhBeli" onblur="check_date(this)" size="9" $readonly class="$inputTextClass"/>
                  #if ($mode != 'view') <a href="javascript:displayDatePicker('txdTarikhBeli',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end </td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view') <span class="style1">*</span> #end</td>
                <td valign="top">Tarikh Aktif</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhAktif" id="txdTarikhAktif" value="$beanMaklumatPerisian.tarikhAktif" onblur="check_date(this);calcDate()" size="9" $readonly class="$inputTextClass"/>
                  #if ($mode != 'view') <a href="javascript:displayDatePicker('txdTarikhAktif',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end </td>
              </tr>
              <tr>
                <td>#if ($mode != 'view') <span class="style1">*</span> #end</td>
                <td width="28%">Tempoh Lesen</td>
                <td>:</td>
                <td width="70%"><input type="text" name="txtTempoh" id="txtTempoh" size="1" maxlength="2" value="$beanMaklumatPerisian.tempoh" onBlur="validateNumber(this,this.value,'$beanMaklumatPerisian.tempoh');calcDate()" $readonly class="$inputTextClass">
                  Bulan </td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view') <span class="style1">*</span> #end</td>
                <td valign="top">Tarikh Luput</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhLuput" id="txdTarikhLuput" value="$beanMaklumatPerisian.tarikhLuput" onblur="check_date(this)" size="9" $readonly class="$inputTextClass"/>
                  #if ($mode != 'view') <a href="javascript:displayDatePicker('txdTarikhLuput',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end </td>
              </tr>
              <tr>
                <td>#if ($mode != 'view') <span class="style1">*</span> #end</td>
                <td>Bilangan Pengguna</td>
                <td>:</td>
                <td><input name="txtBilPengguna" type="text" class="$inputTextClass" id="txtBilPengguna" value="$beanMaklumatPerisian.bilPengguna" maxlength="5" size="5" $readonly onblur="this.value=this.value.toUpperCase();"/>
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Status</td>
                <td>:</td>
                <td> #if ($list.flagAktif == 'T') <span class="style1">$beanMaklumatPerisian.status</span> #else
                  <strong>$beanMaklumatPerisian.status</strong>
                  #end
                  &nbsp;&nbsp; #if ($beanMaklumatPerisian.bilHari != '') <span class="style1"><strong><blink>$beanMaklumatPerisian.bilHari HARI LAGI</blink></strong></span> #end</td>
              </tr>
              #end
            </table>
            </fieldset></td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
        </tr>
        #end
        <tr>
          <td width="30%">&nbsp;</td>
          <td width="70%"> #if ($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
            <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="simpanKemaskini()"/>
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
            #end </td>
        </tr>
      </table>
    </div>
    <div class="TabbedPanelsContent"> #parse("app/mon/frmDokumen.jsp") </div>
  </div>
</div>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>
<script>
function doChangeTab(tabId) {
	document.${formName}.selectedTab.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.actionMonitoring.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.action = "?_portal_module=ekptg.view.mon.FrmSoftwareLicenseMonitoringView";	
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function kemaskini() {	
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batal() {	
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskini() {
	
	if(document.${formName}.socKategori.value == ""){
		alert('Sila pilih Kategori.');
  		document.${formName}.socKategori.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPerisian.value == ""){
		alert('Sila masukkan Nama Lesen.');
  		document.${formName}.txtNamaPerisian.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPengeluar.value == ""){
		alert('Sila masukkan Nama Pengeluar.');
  		document.${formName}.txtNamaPengeluar.focus(); 
		return; 
	}
	if(document.${formName}.txtNoSiri.value == ""){
		alert('Sila masukkan Nombor Siri');
  		document.${formName}.txtNoSiri.focus(); 
		return; 
	}
	if(document.${formName}.txtHarga.value == ""){
		alert('Sila masukkan Harga');
  		document.${formName}.txtHarga.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhBeli.value == ""){
		alert('Sila masukkan Tarikh Pembelian.');
  		document.${formName}.txdTarikhBeli.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhAktif.value == ""){
		alert('Sila masukkan Tarikh Aktif.');
  		document.${formName}.txdTarikhAktif.focus(); 
		return; 
	}
	if(document.${formName}.txtTempoh.value == ""){
		alert('Sila masukkan Tempoh Lesen.');
  		document.${formName}.txtTempoh.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhLuput.value == ""){
		alert('Sila masukkan Tarikh Luput.');
  		document.${formName}.txdTarikhLuput.focus(); 
		return; 
	}
	if(document.${formName}.txtBilPengguna.value == ""){
		alert('Sila masukkan Bilangan Pengguna.');
  		document.${formName}.txtBilPengguna.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionMonitoring.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskini";
	doAjaxCall${formName}("");
}
function hapus() {
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionMonitoring.value = "";
	document.${formName}.hitButton.value = "hapus";
	document.${formName}.submit();
}
function kembali() {	
	document.${formName}.actionMonitoring.value = "";
	document.${formName}.submit();
}
function validateCurrency(elmnt,content,content2) {
	content = content.replace(/,/g,'');
	content2 = content2.replace(/,/g,'');
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
function calcDate(){

	if (document.${formName}.txdTarikhAktif.value != "" && document.${formName}.txtTempoh.value != ""){

		var tarikhMula  = document.${formName}.txdTarikhAktif.value;
		var month  = parseInt(document.${formName}.txtTempoh.value);
		
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
		document.${formName}.txdTarikhLuput.value = tarikhTamat;
	
	} else {
		document.${formName}.txdTarikhLuput.value = "";
	}
}
</script>
