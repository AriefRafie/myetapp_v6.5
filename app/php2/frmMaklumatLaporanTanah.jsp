<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="step"id="step" value="$step"/>
	<input type="hidden" name="selectedTab" id="selectedTab" value="$selectedTab"/>
	<input type="hidden" name="hitButton" id="hitButton" />
	<input type="hidden" name="flagPopup" id="flagPopup" value="$flagPopup"/>
	<input type="hidden" name="modePopup" id="modePopup" value="$modePopup"/>
	<input type="hidden" name="idLaporan" id="idLaporan" value="$idLaporan"/>
	<input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan"/>
	<input type="hidden" name="idUlasanTeknikal" id="idUlasanTeknikal" value="$!idUlasanTeknikal"/>
	<input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi"/>
	<input type="hidden" name="idPegawaiLaporanTanah"id="idPegawaiLaporanTanah" value="$idPegawaiLaporanTanah"/>
	<input type="hidden" name="idDokumen" id="idDokumen" value="$idDokumen"/>
</p>

#if ($errMsg != "")
<div class="info"><strong>$errMsg</strong></div>
#end

<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT TANAH</legend>
      #parse("app/php2/frmMaklumatTanah.jsp")
      </fieldset></td>
  </tr>
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT LAWATAN TAPAK</li>
        <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT LAIN</li>
        <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">KEHADIRAN</li>
        <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">IMEJAN</li>
      </ul>
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
          <table width="100%" border="0" cellspacing="2" cellpadding="2">
            #foreach ($beanMaklumatLaporan in $BeanMaklumatLaporan)
            <tr>
              	<td width="1%"><font color="#ff0000">*</font></td>
              	<td width="28%">Tarikh Lawatan</td>
	            <td width="1%">:</td>
	            <td width="70%"><input type="text" name="txdTarikhLawatan" id="txdTarikhLawatan" value="$beanMaklumatLaporan.tarikhLawatan" onBlur="check_date(this)" size="9"/>
	                <a href="javascript:displayDatePicker('txdTarikhLawatan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
	            </td>
            </tr>
            <tr>
			    <td valign="top">&nbsp;</td>
			    <td width="28%">Lawatan Tapak Oleh</td>
			    <td width="1%">:</td>
			    <td width="70%">JKPTG NEGERI</td>
			</tr>
            <tr>
              	<td valign="top"><font color="#ff0000">*</font></td>
              	<td valign="top">Tujuan Laporan</td>
              	<td valign="top">:</td>
              	<td valign="top">
              		<textarea name="txtTujuanLaporan" id="txtTujuanLaporan" rows="5" cols="50">$beanMaklumatLaporan.tujuanLaporan</textarea>
              	</td>
            </tr>
            <tr>
		    	<td width="1%" valign="top"><font color="#ff0000">*</font></td>
		    	<td width="28%" valign="top">Laporan Atas Tanah</td>
		    	<td width="1%" valign="top">:</td>
		    	<td valign="top">
		    		<textarea name="txtLaporanAtasTanah" id="txtLaporanAtasTanah" rows="5" cols="50">$beanMaklumatLaporan.laporanAtasTanah</textarea>    
		    	</td>
		  	</tr>
		  	<tr>
		    	<td valign="top">&nbsp;</td>
		    	<td valign="top">Isu dan Ulasan</td>
		    	<td valign="top">:</td>
		    	<td valign="top">
		    		<textarea name="txtIsuUlasan" id="txtIsuUlasan" rows="5" cols="50">$beanMaklumatLaporan.ulasan</textarea>    
		    	</td>
		  	</tr>
            <tr>
              	<td valign="top">&nbsp;</td>
              	<td valign="top">Catatan</td>
              	<td valign="top">:</td>
              	<td valign="top">
              		<textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50">$beanMaklumatLaporan.catatan</textarea>
              	</td>
            </tr>
            <tr>
              	<td valign="top"><font color="#ff0000">*</font></td>
              	<td valign="top">Disediakan Oleh</td>
              	<td valign="top">:</td>
              	<td valign="top">
              		<input name="txtPelapor" type="text" id="txtPelapor" value="$beanMaklumatLaporan.pelapor" maxlength="40" size="40" 
              			onBlur="this.value=this.value.toUpperCase();"/>
              	</td>
            </tr>
            <tr>
		    	<td valign="top"><font color="#ff0000">*</font></td>
		    	<td valign="top">Jawatan</td>
		    	<td valign="top">:</td>
		    	<td valign="top">$selectJawatanPelapor</td>
		  	</tr>
            <tr>
              	<td valign="top"><font color="#ff0000">*</font></td>
              	<td valign="top">Negeri</td>
              	<td valign="top">:</td>
              	<td valign="top">$selectNegeri</td>
            </tr>
            #end
            <tr>
              	<td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
            </tr>
            <tr>
              	<td>&nbsp;</td>
              	<td>&nbsp;</td>
              	<td>&nbsp;</td>
              	<td>
              		<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Simpan" onClick="kemaskini()"/>
                	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>
                </td>
            </tr>
          </table>
        </div>
        <div class="TabbedPanelsContent"> #foreach ($beanMaklumatLaporan in $BeanMaklumatLaporan)
          <table width="100%" border="0" cellspacing="2" cellpadding="2">
            <tr>
              <td colspan="2"><fieldset>
                <legend>LOKASI</legend>
                <table width="100%" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="29%">Jalan Hubungan</td>
                    <td width="1%">:</td>
                    <td width="70%"><input name="txtJalanHubungan" type="text"  id="txtJalanHubungan" value="$beanMaklumatLaporan.jalanHubungan" size="40" maxlength="100"  /></td>
                  </tr>
                  <tr>
                    <td>Kawasan Berhampiran</td>
                    <td>:</td>
                    <td><input name="txtKawasanBerhampiran" type="text"  id="txtKawasanBerhampiran"  value="$beanMaklumatLaporan.kawasanBerhampiran" size="40" maxlength="100" /></td>
                  </tr>
                  <tr>
                    <td>Anggaran Jarak Dari Bandar</td>
                    <td>:</td>
                    <td><input name="txtJarakDariBandar" type="text"  id="txtJarakDariBandar" onKeyUp="validateNumber(this,this.value)"  value="$beanMaklumatLaporan.jarakDariBandar" size="3" maxlength="100" />
                      KM</td>
                  </tr>
                </table>
                </fieldset></td>
            </tr>
            <tr>
              <td colspan="2"><fieldset>
                <legend>PEMBANGUNAN SEKITAR</legend>
                <table width="100%" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="29%" valign="top">Kemudahan Asas</td>
                    <td width="1%" valign="top">:</td>
                    <td width="70%" valign="top">#set($checkedA = "")
                      #set($checkedL = "")
                      #set($checkedT = "")
                      
                      #if ($beanMaklumatLaporan.flagAir == 'Y')
                      #set($checkedA = "checked")
                      #end
                      #if ($beanMaklumatLaporan.flagElektrik == 'Y')
                      #set($checkedL = "checked")
                      #end
                      #if ($beanMaklumatLaporan.flagTel == 'Y')
                      #set($checkedT = "checked")
                      #end
                      <input type="checkbox" name="kemudahanAsasA" id="kemudahanAsasA" value="Y" $checkedA />
                      Air &nbsp;
                      <input type="checkbox" name="kemudahanAsasL" id="kemudahanAsasL" value="Y" $checkedL />
                      Elektrik &nbsp;
                      <input type="checkbox" name="kemudahanAsasT" id="kemudahanAsasT" value="Y" $checkedT />
                      Telefon</td>
                  </tr>
                  <tr>
                    <td valign="top">Kemudahan Asas Lain</td>
                    <td valign="top">:</td>
                    <td valign="top"><textarea name="txtKemudahanAsasLain" id="txtKemudahanAsasLain" rows="5" cols="50"  >$beanMaklumatLaporan.kemudahanAsasLain</textarea></td>
                  </tr>
                  <tr>
                    <td valign="top">Keadaan Tanah</td>
                    <td valign="top">:</td>
                    <td valign="top"><textarea name="txtKeadaanTanah" id="txtKeadaanTanah" rows="5" cols="50"  >$beanMaklumatLaporan.keadaanTanah</textarea></td>
                  </tr>
                  <tr>
                    <td valign="top">Keadaan Rupabumi</td>
                    <td valign="top">:</td>
                    <td valign="top"><textarea name="txtKeadaanRupabumi" id="txtKeadaanRupabumi" rows="5" cols="50"  >$beanMaklumatLaporan.keadaanRupabumi</textarea></td>
                  </tr>
                </table>
                </fieldset></td>
            </tr>
            <tr>
              <td colspan="2"><fieldset>
                <legend>LOT-LOT BERSEMPADAN</legend>
                <table width="100%" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="29%" valign="top">Utara</td>
                    <td width="1%" valign="top">:</td>
                    <td width="70%" valign="top"><textarea name="txtUtara" id="txtUtara" rows="5" cols="50"  >$beanMaklumatLaporan.utara</textarea></td>
                  </tr>
                  <tr>
                    <td valign="top">Selatan</td>
                    <td valign="top">:</td>
                    <td valign="top"><textarea name="txtSelatan" id="txtSelatan" rows="5" cols="50"  >$beanMaklumatLaporan.selatan</textarea></td>
                  </tr>
                  <tr>
                    <td valign="top">Timur</td>
                    <td valign="top">:</td>
                    <td valign="top"><textarea name="txtTimur" id="txtTimur" rows="5" cols="50"  >$beanMaklumatLaporan.timur</textarea></td>
                  </tr>
                  <tr>
                    <td valign="top">Barat</td>
                    <td valign="top">:</td>
                    <td valign="top"><textarea name="txtBarat" id="txtBarat" rows="5" cols="50"  >$beanMaklumatLaporan.barat</textarea></td>
                  </tr>
                </table>
                </fieldset></td>
            </tr>
            <tr>
              <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
            </tr>
            <tr>
              <td width="30%">&nbsp;</td>
              <td width="70%"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Simpan" onClick="kemaskini()"/>
                <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/></td>
            </tr>
          </table>
          #end 
        </div>
        <div class="TabbedPanelsContent"> #parse("app/php2/frmMaklumatKehadiranLaporanTanah.jsp") </div>
        <div class="TabbedPanelsContent"> #parse("app/php2/frmImejanLaporanTanah.jsp") </div>
      </div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>
<script>
function doChangeTab(tabId) {
	document.${formName}.selectedTab.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmSenaraiLaporanTanahView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function kemaskini() {

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
	if(document.${formName}.txtLaporanAtasTanah.value == ""){
		alert('Sila masukkan Laporan Atas Tanah.');
  		document.${formName}.txtLaporanAtasTanah.focus(); 
		return; 
	}
	if(document.${formName}.txtPelapor.value == ""){
		alert('Sila masukkan Disediakan Oleh.');
		document.${formName}.txtPelapor.focus(); 
		return; 
	}
	if(document.${formName}.socJawatanPelapor.value == ""){
		alert('Sila pilih Jawatan.');
  		document.${formName}.socJawatanPelapor.focus(); 
		return; 
	}
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
		document.${formName}.socNegeri.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.step.value = "kemaskini";
		return;
	}
	
	document.${formName}.hitButton.value = "kemaskini";
	doAjaxCall${formName}("");
}
function kembali() {	
	document.${formName}.step.value = "";
	document.${formName}.submit();
}
function hantar(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "sendNotification";
	document.${formName}.submit();
}
</script>
