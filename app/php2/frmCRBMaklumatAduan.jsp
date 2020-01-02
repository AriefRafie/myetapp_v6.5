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

#set($saizTxtLain="300")
#set($saizTxtLokasi="300")
#set($saizTxtKeterangan="500")
#set($saizTxtPerkara="1000")
#set($saizTxtAktiviti="300")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idHakmilik" type="hidden" id="idHakmilik" value="$idHakmilik"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idHakmilikAgensi" type="hidden" id="idHakmilikAgensi" value="$idHakmilikAgensi"/>
  <input name="flagBorangK" type="hidden" id="flagBorangK" value="$flagBorangK"/>
  <input name="idBorangK" type="hidden" id="idBorangK" value="$idBorangK"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmCRBHeader.jsp") </td>
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
  #if ($idFail != '' && $flagOpenDetail)
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT ADUAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> #if ($flagBorangK == 'Y') #parse("app/php2/frmCRBMaklumatBorangK.jsp") #else #parse("app/php2/frmCRBMaklumatTanah.jsp") #end </div>
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanMaklumatAduan in $BeanMaklumatAduan)
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Tarikh Terima Aduan</td>
                <td>:</td>
                <td><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatAduan.tarikhTerima" onBlur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass"/>
                  #if ($mode == 'update') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Tarikh Surat Aduan</td>
                <td>:</td>
                <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatAduan.tarikhSurat" onBlur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
                  #if ($mode == 'update')<a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
              </tr>
              <tr>
                <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td valign="top">Perkara</td>
                <td valign="top">:</td>
                <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="65" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtPerkara,this.form.remLen,$!saizTxtPerkara);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLen,$!saizTxtPerkara);" >$beanMaklumatAduan.perkara</textarea></td>
              </tr>
              #if ($mode == 'update')
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>Baki Aksara :&nbsp;
                  <input type="text" readonly="readonly" class="disabled" name="remLen" size="3" maxlength="3" value="$!saizTxtPerkara" /></td>
              </tr>
              #end
              
              #foreach ($senaraiPencerobohan in $SenaraiPencerobohan)
              
              #set($checked = "")
              #if($senaraiPencerobohan.flag =="Y")
              #set($checked = "checked")
              #else
              #set($checked = "")
              #end
              
              #if($senaraiPencerobohan.bil == '1')
              <tr>
                <td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td width="20">Jenis Pencerobohan</td>
                <td width="1%">:</td>
                <td width="68%"><input name="idJenisPencerobohan" type="hidden" id="idJenisPencerobohan" value="$senaraiPencerobohan.idJenisPencerobohan"/>
                  <input type="checkbox" name="sbcSemakan" id="sbcSemakan" value="$senaraiPencerobohan.idJenisPencerobohan" $disabled $checked/>
                  $senaraiPencerobohan.keterangan </td>
              </tr>
              #else
              <tr>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="checkbox" name="sbcSemakan" id="sbcSemakan" value="$senaraiPencerobohan.idJenisPencerobohan" $disabled $checked/>
                  $senaraiPencerobohan.keterangan </td>
              </tr>
              #end
              #end
              <tr>
                <td valign="top"></td>
                <td valign="top">Jika Lain-lain Sila Nyatakan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtLainCeroboh" id="txtLainCeroboh" rows="5" cols="65" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtLainCeroboh,this.form.remLen1,$!saizTxtLain);" onKeyDown="textCounter(this.form.txtLainCeroboh,this.form.remLen1,$!saizTxtLain);">$beanMaklumatAduan.lainCeroboh</textarea></td>
              </tr>
              #if ($mode != 'view')
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td>Baki Aksara :&nbsp;
                  <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtLain" /></td>
              </tr>
              #end
              <tr>
                <td valign="top"></td>
                <td valign="top">Aktiviti</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtAktiviti" id="txtAktiviti" rows="5" cols="65" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtAktiviti,this.form.remLen4,$!saizTxtAktiviti);" onKeyDown="textCounter(this.form.txtAktiviti,this.form.remLen4,$!saizTxtAktiviti);">$beanMaklumatAduan.aktiviti</textarea></td>
              </tr>
              
              #if ($mode != 'view')
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td>Baki Aksara :&nbsp;
                  <input type="text" readonly="readonly" class="disabled" name="remLen4" size="3" maxlength="3" value="$!saizTxtAktiviti" /></td>
              </tr>
              #end
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Lokasi</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtLokasi" id="txtLokasi" rows="5" cols="65" $readonly class="$inputTextClass"   onKeyUp="textCounter(this.form.txtLokasi,this.form.remLen2,$!saizTxtLokasi);" onKeyDown="textCounter(this.form.txtLokasi,this.form.remLen2,$!saizTxtLokasi);">$beanMaklumatAduan.lokasi</textarea></td>
              </tr>
              #if ($mode != 'view')
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td>Baki Aksara :&nbsp;
                  <input type="text" readonly="readonly" class="disabled" name="remLen2" size="3" maxlength="3" value="$!saizTxtLokasi" /></td>
              </tr>
              #end
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Keterangan Aduan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtAduan" id="txtAduan" rows="5" cols="65" $readonly class="$inputTextClass"   onKeyUp="textCounter(this.form.txtAduan,this.form.remLen3,$!saizTxtKeterangan);" onKeyDown="textCounter(this.form.txtAduan,this.form.remLen3,$!saizTxtKeterangan);">$beanMaklumatAduan.keterangan</textarea></td>
              </tr>
              #if ($mode != 'view')
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td>Baki Aksara :&nbsp;
                  <input type="text" readonly="readonly" class="disabled" name="remLen3" size="3" maxlength="3" value="$!saizTxtKeterangan" /></td>
              </tr>
              #end 
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
                  #if ($idStatus == '1610198')
                  <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
                  <input type="button" name="cmdSelesaiPermohonan" id="cmdSelesaiPermohonan" value="Selesai Permohonan" onClick="gotoSelesaiPermohonan()"/>
                  <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
                  #end
                  <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                  #end
                  #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniMaklumatAduan()"/>
                  <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
                  #end </td>
              </tr>
              #end
            </table>
          </div>
        </div>
      </div></td>
  </tr>
  #elseif ($idFail != '')
  <tr>
    <td>&nbsp;
      <div class="warning"><strong>STATUS FAIL MASIH DI PERINGKAT $!status</strong></div></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakKulitFail('$idFail')"> Kulit Fail </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakAkuanTerima('$idFail')"> Surat Akuan Terima </a></td>
  </tr>
</table>
</fieldset>
<script type="text/javascript">
#if ($idFail != '' && $flagOpenDetail)
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function doKemaskini() {
	document.${formName}.mode.value = "update";
	document.${formName}.submit();
}
function doSimpanKemaskiniMaklumatAduan() {
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
	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus(); 
		return; 
	}
	
	var c = 0;
	if (document.${formName}.sbcSemakan.length > 1){
		for (i = 0; i < document.${formName}.sbcSemakan.length; i++){
			if (document.${formName}.sbcSemakan[i].checked == true){
				c++
			}
		}
	} else {
		if (document.${formName}.sbcSemakan.checked == true){
			c++
		}
	}
	if(c == 0){
		alert('Sila pilih Jenis Pencerobohan.');
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatAduan";
	document.${formName}.submit();
}
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
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
function cetakKulitFail(idFail) {
	var url = "../servlet/ekptg.report.php2.CRBKulitFail?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakAkuanTerima(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=suratAkuanTerima";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>

<input name="step" type="hidden" id="step"/>
<script>
function gotoSelesaiPermohonan(){	
	document.${formName}.step.value = "selesaiPermohonan";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>
