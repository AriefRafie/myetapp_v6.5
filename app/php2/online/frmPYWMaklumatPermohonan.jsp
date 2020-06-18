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

#set($saizTxtTujuan="500")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPermohonanSewa" type="hidden" id="idPermohonanSewa" value="$idPermohonanSewa"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idHakmilikAgensi" type="hidden" id="idHakmilikAgensi" value="$idHakmilikAgensi"/>
  <input name="idHakmilikAgensiPopup" type="hidden" id="idHakmilikAgensiPopup"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idPengarah" type="hidden" id="idPengarah" value="$idPengarah"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="kategori" type="hidden" id="kategori" value="$!pemohon.get("kategoriPemohon")"/>
  <input name="actionPenyewaan" type="hidden" id="actionPenyewaan" value="$actionPenyewaan"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$!idDokumen"/>
</p>
<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/online/frmPYWHeader.jsp") </td>
  </tr>
  
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if ($idFail != '')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PENYEWAAN</li>
          <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">SENARAI SEMAK</li>
          <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">LAMPIRAN</li>
          <li onClick="doChangeTabUpper(4);" class="TabbedPanelsTab" tabindex="0">PENGESAHAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Pegangan Hakmilik</td>
                <td width="1%">:</td>
                <td width="70%"> #if ($mode == 'update')
                  <input type="text" name="txtPeganganHakmilik1" id="txtPeganganHakmilik1" value="$beanMaklumatTanah.peganganHakmilik" onBlur="doChangePeganganHakmilik1();">
                  #else
                  <input type="text" name="txtPeganganHakmilik1" id="txtPeganganHakmilik1" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled">
                  #end
                  <input type="hidden" name="idHakmilikAgensi1" id="idHakmilikAgensi1" value="$idHakmilikAgensi" />
                  <span class="style1">$errorPeganganHakmilik</span> </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Lot</td>
                <td>:</td>
                <td>$beanMaklumatTanah.noLot</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Luas Lot</td>
                <td>:</td>
                <td>$beanMaklumatTanah.luasLot</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Hakmilik</td>
                <td>:</td>
                <td>$beanMaklumatTanah.noHakmilik</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Warta</td>
                <td>:</td>
                <td>$beanMaklumatTanah.noWarta</td>
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
                <td>$beanMaklumatTanah.mukim</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Daerah</td>
                <td>:</td>
                <td>$beanMaklumatTanah.daerah</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Negeri</td>
                <td>:</td>
                <td>$beanMaklumatTanah.negeri
                  <input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$beanMaklumatTanah.idNegeriTanah" />
                  <input type="hidden" name="kodNegeriTanah" id="kodNegeriTanah" value="$beanMaklumatTanah.kodNegeriTanah" />
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Kementerian</td>
                <td>:</td>
                <td>$beanMaklumatTanah.kementerian
                  <input type="hidden" name="idKementerian" id="idKementerian" value="$beanMaklumatTanah.idKementerian" />
                  <input type="hidden" name="kodKementerian" id="kodKementerian" value="$beanMaklumatTanah.kodKementerian" />
                </td>
              </tr>
              <tr>
                <td height="31">&nbsp;</td>
                <td>Agensi</td>
                <td>:</td>
                <td>$beanMaklumatTanah.agensi</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Kegunaan Tanah</td>
                <td>:</td>
                <td>$beanMaklumatTanah.kegunaanTanah</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
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
                <td> #if ($idStatus == '')
                  #if ($mode == 'view')
                  <input type="button" name="cmdKemaskiniTnh" id="cmdKemaskiniTnh" value="Kemaskini" onClick="doKemaskini()"/>
                  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                  #end
                  #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskiniTnh" id="cmdSimpanKemaskiniTnh" value="Simpan" onClick="doSimpanKemaskiniMaklumatTnh()"/>
                  <input type="button" name="cmdBatalKemaskiniTnh" id="cmdBatalKemaskiniTnh" value="Batal" onClick="doBatalKemaskini()"/>
                  #end 
                  #else
                  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                  #end </td>
              </tr>
              #end
            </table>
          </div>
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanMaklumatSewa in $BeanMaklumatSewa)
              <tr>
                <td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td valign="top" width="28%">Tempoh Sewa</td>
                <td width="1%">:</td>
                <td width="70%"><select name="socTempohSewa" id="socTempohSewa" style="width:140px;" $readonly class="$disabled" $disabled>
            #if ($beanMaklumatSewa.flagTempohSewa == 'B')
                    <option>SILA PILIH</option>
                    <option value="B" selected>BULAN KE BULAN</option>
                    <option value="1T">1 TAHUN</option>
                    <option value="2T">2 TAHUN</option>
                    <option value="3T">3 TAHUN</option>
            #elseif ($beanMaklumatSewa.flagTempohSewa == '1T')
                	<option>SILA PILIH</option>
                    <option value="B">BULAN KE BULAN</option>
                    <option value="1T" selected>1 TAHUN</option>
                    <option value="2T">2 TAHUN</option>
                    <option value="3T">3 TAHUN</option>
            #elseif ($beanMaklumatSewa.flagTempohSewa == '2T')
                	<option>SILA PILIH</option>
                    <option value="B">BULAN KE BULAN</option>
                    <option value="1T">1 TAHUN</option>
                    <option value="2T"  selected>2 TAHUN</option>
                    <option value="3T">3 TAHUN</option>
            #elseif ($beanMaklumatSewa.flagTempohSewa == '3T')
                	<option>SILA PILIH</option>
                    <option value="B">BULAN KE BULAN</option>
                    <option value="1T">1 TAHUN</option>
                    <option value="2T">2 TAHUN</option>
                    <option value="3T"  selected>3 TAHUN</option>
            #else
                    <option>SILA PILIH</option>
                    <option value="B">BULAN KE BULAN</option>
                    <option value="1T">1 TAHUN</option>
                    <option value="2T">2 TAHUN</option>
                    <option value="3T">3 TAHUN</option>
            #end
            
            
                  
                  </select>
                </td>
              </tr>
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td>Luas Kegunaan</td>
                <td>:</td>
                <td >$selectLuasKegunaan</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Keluasan Asal</td>
                <td>:</td>
                <td>$beanMaklumatSewa.luasAsal $beanMaklumatSewa.keteranganLuasAsal
                  <input type="hidden" name="txtLuasAsal" id="txtLuasAsal" value="$beanMaklumatSewa.luasAsal"/></td>
              </tr>
              #if ($idLuasKegunaan == '2')
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td>Unit Luas</td>
                <td>:</td>
                <td>#parse("app/php2/unit_luas.jsp") </td>
              </tr>
              #if ($idLuas != '99999' && $idLuas != '')
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td>Luas Mohon</td>
                <td>:</td>
                <td> #if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' || $idLuas == '5' || $idLuas == '6' || $idLuas == '9')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatSewa.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonly class="$inputTextClass"/ >
                  #elseif ($idLuas == '7')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatSewa.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatSewa.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"/ $readonly class="$inputTextClass">
                  #elseif ($idLuas == '8' || $idLuas == '4')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatSewa.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatSewa.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$beanMaklumatSewa.luas3" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonly class="$inputTextClass"/>
                  #end </td>
              </tr>
              #end
              #end
              <tr>
                <td>&nbsp;</td>
                <td>Luas Bersamaan</td>
                <td>:</td>
                <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" value="$beanMaklumatSewa.luasBersamaan"  style="text-align:right" readonly="readonly" class="disabled"/>
                  HEKTAR</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Baki Luas</td>
                <td>:</td>
                <td><input type="text" name="txtBakiLuas" id="txtBakiLuas" value="$beanMaklumatSewa.luasBaki" readonly="readonly" class="disabled" style="text-align:right"/>
                  HEKTAR</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
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
                <td> #if ($idStatus == '')
                  #if ($mode == 'view')
                  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
                  <input type="button" name="cmdBackList2" id="cmdBackList2" value="Kembali" onClick="doBacklist()"/>
                  <!-- butang hantar & email / butang hapus dipindah ke tab pengesahan -->
                  <!-- <input type="button" name="cmdHantar" id="cmdHantar" value="Hantar &amp; Emel" onClick="doHantarEmel()"/>
                  <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>-->
                  #end
                  #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniMaklumatPenyewaan('$idLuas')"/>
                  <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
                  #end
                  #else
                  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                  <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                  #end </td>
              </tr>
              #end              
            </table>
          </div>
          <div class="TabbedPanelsContent">
           	<table width="100%" border="0" cellspacing="2" cellpadding="2">
           	<tr>
  				<td> #parse("app/php2/online/frmPYWDaftarManualSenaraiSemakOnline.jsp") </td>
              </tr>
           	</table>
           	
         </div>
         <div class="TabbedPanelsContent">
         <table width="100%" border="0" cellspacing="2" cellpadding="2">
           	<tr>
           	<td>#parse("app/php2/online/frmPYWMaklumatLampiranOnline.jsp")</td>
           	</tr>
           	</table>
         </div>
         <div class="TabbedPanelsContent">
           	<table width="100%" border="0" cellspacing="2" cellpadding="2">
           	<td valign="top">
           	#if ($idStatus == '')<input type="checkbox" name="pengesahan" id="pengesahan">#end
           	#if ($idStatus != '')<input type="checkbox" name="pengesahan" id="pengesahan" $disabled checked>#end</td>
           	<td>Saya, $!pemohon.get("namaPemohon"), dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka 
           	<br/>tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</td> 
           	<tr>
           	<td colspan=2 align="center">
           	#if ($idStatus == '')
            	<input type="button" name="cmdHantar" id="cmdHantar" value="Hantar &amp; Emel" onClick="doHantarEmel()"/>
            	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>
            #end</td>
           	</tr>          	
           	</table>
         </div>
        </div>
      </div></td>
  </tr>
  #end
</table>
<div id="calculateTotalPercentPengarah_result"></div>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"> Borang Permohonan </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"> Pengesahan Permohonan </a></td>
  </tr>
</table>
</fieldset>
<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeTabUpper(tabId) {
	document.${formName}.actionPenyewaan.value = "paparMaklumatPenyewaan";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function pilihTanahPYW() {
	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmPYWOnlinePopupSenaraiTanahView";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensi) {
	document.${formName}.idHakmilikAgensiPopup.value = idHakmilikAgensi;
	doAjaxCall${formName}("doChangeMaklumatTanah");
}
function doSimpanKemaskiniMaklumatTnh() {

	if(document.${formName}.idHakmilikAgensi1.value == ""){
		alert('Sila pilih Pegangan Hakmilik.');
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPenyewaan.value = "paparMaklumatPenyewaan";
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 0;
	document.${formName}.actionPenyewaan.value = "paparMaklumatPenyewaan";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatTnh";
	document.${formName}.submit();
}
function doChangePeganganHakmilik1() {
	doAjaxCall${formName}("doChangePeganganHakmilik1");
}
function doBacklist() {
	document.${formName}.actionPenyewaan.value = "";
	document.${formName}.submit();
}
function validateLuas(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(5);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function doChangeLuasKegunaan() {
	doAjaxCall${formName}("doChangeLuasKegunaan");
}
function doChangeLuas() {
	doAjaxCall${formName}("doChangeLuas");
}
function calculate(valueMohon,valueBaki){	
	var luasPenyewaan = document.${formName}.txtLuasPenyewaan.value * 1;
	var luasAsal = document.${formName}.txtLuasAsal.value * 1;
	
	if (luasPenyewaan != ""){	
		if (luasPenyewaan > luasAsal){
			alert('Luas yang dimasukkan telah melebihi luas asal.');
			document.${formName}.txtLuasPenyewaan.value = valueMohon;
			document.${formName}.txtBakiLuas.value = valueBaki;
			return; 
		} else {
			var luasBaki = (luasAsal - luasPenyewaan) * 1;
			document.${formName}.txtBakiLuas.value = luasBaki.toFixed(5);
		}
	}
}
function doKemaskini() {
	document.${formName}.actionPenyewaan.value = "paparMaklumatPenyewaan";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("doKemaskini");
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatPenyewaan(idLuas) {

	if(document.${formName}.socTempohSewa.value == "SILA PILIH"){
		alert('Sila pilih Tempoh Sewa.');
  		document.${formName}.socTempohSewa.focus(); 
		return; 
	}
	if(document.${formName}.socLuasKegunaan.value == ""){
		alert('Sila pilih Luas Kegunaan.');
  		document.${formName}.socLuasKegunaan.focus(); 
		return; 
	}
	if(document.${formName}.socLuasKegunaan.value == "2"){
		if(document.${formName}.socLuas.value == "0"){
			alert('Sila pilih Unit Luas.');
			document.${formName}.socLuas.focus(); 
			return; 
		}
	
		if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Sewa .');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
		}
		else
		if(idLuas == '4' || idLuas == '8'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Sewa.');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Sewa.');
				document.${formName}.txtLuasMohon2.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon3.value == ""){
				alert('Sila masukkan Luas Sewa.');
				document.${formName}.txtLuasMohon3.focus(); 
				return; 
			}
		} 
		else
		if(idLuas == '7'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Sewa.');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Sewa.');
				document.${formName}.txtLuasMohon2.focus(); 
				return; 
			}
		} 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPenyewaan";
	document.${formName}.submit();
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doSimpanKemaskiniMaklumatPemohon(type) {
	if(type == "syarikat"){
		if(document.${formName}.txtModalBenar.value == ""){
			alert('Sila masukan Modal Dibenarkan.');
  			document.${formName}.txtModalBenar.focus(); 
			return; 
		}		
		if(document.${formName}.txtModalJelas.value == ""){
			alert('Sila masukan Modal Jelas.');
  			document.${formName}.txtModalJelas.focus(); 
			return; 
		}
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPemohon";
	document.${formName}.submit();
}
function doDaftarPengarah(){
	document.${formName}.flagPopup.value = "openPopupPengarah";
	document.${formName}.modePopup.value = "new";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doBatalPengarah(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doPaparPengarah(idPengarah){
	document.${formName}.idPengarah.value = idPengarah;
	document.${formName}.flagPopup.value = "openPopupPengarah";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doKemaskiniPengarah(){
	document.${formName}.flagPopup.value = "openPopupPengarah";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniPengarah(){
	document.${formName}.flagPopup.value = "openPopupPengarah";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniPengarah(){
	if(document.${formName}.socWargaPengarah.value == ""){
		alert('Sila pilih Warganegara.');
  		document.${formName}.socWargaPengarah.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPengarah.value == ""){
		alert('Sila masukkan Nama Pengarah.');
  		document.${formName}.txtNamaPengarah.focus(); 
		return; 
	}
	if(document.${formName}.txtSaham.value == ""){
		alert('Sila masukan Pegangan Saham.');
  		document.${formName}.txtSaham.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupPengarah";
		document.${formName}.modePopup.value = "new";
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.flagPopup.value = "openPopupPengarah";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPengarah";
	doAjaxCall${formName}("");
}
function doSimpanPengarah(){
	if(document.${formName}.socWargaPengarah.value == ""){
		alert('Sila pilih Warganegara.');
  		document.${formName}.socWargaPengarah.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPengarah.value == ""){
		alert('Sila masukkan Nama Pengarah.');
  		document.${formName}.txtNamaPengarah.focus(); 
		return; 
	}
	if(document.${formName}.txtSaham.value == ""){
		alert('Sila masukan Pegangan Saham.');
  		document.${formName}.txtSaham.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupPengarah";
		document.${formName}.modePopup.value = "new";
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "doSimpanPengarah";
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
function echeck(str) {

		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   alert("Sila Masukan Email Dengan Betul.")
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		   alert("Sila Masukan Email Dengan Betul.")
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }
		
		 if (str.indexOf(" ")!=-1){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }

 		 return true					
	}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function validateLength(str) {
	
	if (str.length < 5) {
 		alert("Sila Masukan Poskod Dengan Betul.")
		 return false
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
function checkPercentage(x){;
	if(parseInt(x) > 100){
		alert('Sila Nilai Peratusan Pegangan Saham dengan Betul.');
  		document.${formName}.txtSaham.focus(); 
		return; 
	}
}
function doHapusMaklumatPengarah(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusMaklumatPengarah";
	document.${formName}.submit();
}
function validateTempohSewa() {
	//if it is character, then remove it..
	if (document.${formName}.txtTempohSewa.value > 36 ){
		alert('Tempoh sewa anda melebihi had yang dibenarkan. Sila isi tempoh yang betul.');
		document.${formName}.txtTempohSewa.focus(); 
		return;
	}
}
function kiraLuas(idLuas){

  var jenisLuas = idLuas;
  
  // KILOMETER PERSEGI
  if(jenisLuas == "1"){

		var luasK = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasK = document.${formName}.txtLuasMohon1.value*1;
		}
		var luasH = luasK*100;
		
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "2"){ //HEKTER
  	
		var luasH = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasH = document.${formName}.txtLuasMohon1.value*1;
		}
  		
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "3"){ // METER PERSEGI
    	
		var luasM = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasM = document.${formName}.txtLuasMohon1.value*1;
		}
  	  	var luasH = (luasM*0.0001);
	  	
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "4"){  //EKAR, ROOD, POLE

	  	var luasE = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasE = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasR = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasR = document.${formName}.txtLuasMohon2.value*1;
		}
	  	var luasP = 0;
		if (document.${formName}.txtLuasMohon3.value != ''){
			luasP = document.${formName}.txtLuasMohon3.value*1;
		}
	  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  	
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasMohon3.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "5"){ //KAKI PERSEGI
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuasMohon1.value != ''){
	  	  luasAsal = document.${formName}.txtLuasMohon1.value*1;
	  }
	  var luasH = luasAsal*0.0000092;
  	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		 	document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "6"){	//EKAR PERPULUHAN
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuasMohon1.value != ''){
	  	  luasAsal = document.${formName}.txtLuasMohon1.value*1;
	  }
	  var luasH = luasAsal*0.405;
	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}
  	  
   } else if(jenisLuas == "7"){ //EKAR,DEPA
  	  
	  	var luasE = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasE = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasD = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasD = document.${formName}.txtLuasMohon2.value*1;
		}
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		  	document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI
  	  
	 	var luasR = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasR = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasJ = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasJ = document.${formName}.txtLuasMohon2.value*1;
		}
	  	var luasK = 0;
		if (document.${formName}.txtLuasMohon3.value != ''){
			luasK = document.${formName}.txtLuasMohon3.value*1;
		}
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasMohon3.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		  	document.${formName}.txtBakiLuas.value = bakiLuas;
		}
	}
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function calculateTotalPercentPengarah() {
	url = "../servlet/ekptg.view.php2.FrmPYWServlet?command=calculateTotalPercentPengarah";
	actionName = "calculateTotalPercentPengarah";
	target = "calculateTotalPercentPengarah_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function checkPercentage(){;
	if(parseInt(document.${formName}.txtSaham.value) > 100){
		alert('Sila masukkan nilai peratusan saham dengan betul.');
		document.${formName}.txtSaham.value = ""; 
  		document.${formName}.txtSaham.focus(); 
		return; 
	}
}
function popupMsg(){
	alert('Jumlah peratusan saham yang dimasukkan telah melebihi 100%');
	document.${formName}.txtSaham.value = "";
}
function doHantarEmel(){
	if(pengesahan.checked != true){
		alert('Sila tanda pada checkbox untuk teruskan permohonan. ');
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarEmel";
	document.${formName}.submit();
}
function doHapus(){
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapus";
	document.${formName}.actionPenyewaan.value = "";
	document.${formName}.submit();
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
function cetakBorangPermohonan(idPermohonan) {
	var url = "../servlet/ekptg.report.php2.online.PYWBorangPermohonan?ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPengesahanPermohonan(idPermohonan) {
	var url = "../servlet/ekptg.report.php2.online.PYWPengesahanPermohonanOnline?ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>

<script>
<!-- MAKLUMAT LAMPIRAN -->
function daftarLampiran() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPYWOnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.actionPenyewaan = "paparMaklumatPenyewaan";
	document.${formName}.mode.value = "view";	
	document.${formName}.flagPopup.value = "openPopupLampiran";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}

<!-- SENARAI SEMAK -->
function doSimpanKemaskiniSenaraiSemak() {
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniSenaraiSemak";
	document.${formName}.submit();
}
</script>
