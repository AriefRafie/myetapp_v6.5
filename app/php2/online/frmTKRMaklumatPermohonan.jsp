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
#set($saizTxtCadangan="1000")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idHakmilikAgensi" type="hidden" id="idHakmilikAgensi" value="$idHakmilikAgensi"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idTanahGanti" type="hidden" id="idTanahGanti" value="$idTanahGanti"/>
  <!-- <input name="idKementerian" type="hidden" id="idKementerian" value="$idKementerian"/>-->
  <!--  <input name="idAgensi" type="hidden" id="idAgensi" value="$idAgensi"/>-->
  <input name="idHakmilikAgensiTanahGanti" type="hidden" id="idHakmilikAgensiTanahGanti"/>
  <input name="idKementerianPemohon" type="hidden" id="idKementerianPemohon" value="$idKementerianPemohon"/>
  <input name="idAgensiPemohon" type="hidden" id="idAgensiPemohon" value="$idAgensiPemohon"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input type="hidden" name="actionPopup" id="actionPopup"/>
  <input name="idPermohonanPelepasan" type="hidden" id="idPermohonanPelepasan" value="$idPermohonanPelepasan"/>
  <input name="statusRizab" type="hidden" id="statusRizab" value="$statusRizab"/>
  <input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
  <input name="idHakmilikAgensiPopup" type="hidden" id="idHakmilikAgensiPopup"/>
  <input name="idHakmilikAgensiAgensiPopup" type="hidden" id="idHakmilikAgensiAgensiPopup"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
</p>
<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/online/frmPLPHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI PERMOHONAN TERLEBIH DAHULU</div></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if ($idFail != '')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TUKARGUNA</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH DITUKAR GANTI</li> 
          <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">MUAT NAIK DOKUMEN</li>         
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
                  <!--tukarguna-->
                  <input type="text" name="txtPeganganHakmilik1" id="txtPeganganHakmilik1" value="$beanMaklumatTanah.peganganHakmilik" onBlur="doChangePeganganHakmilik1();" />
                  <!--input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onClick="pilihTanahTKR('3','$idAgensiPmhn' , '99999')"/-->
                  #else
                  <input type="text" name="txtPeganganHakmilik1" id="txtPeganganHakmilik1" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled">
                  #end
                  <input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi" />
                  <span class="style1">$errorPeganganHakmilik</span>&nbsp;&nbsp;
            	#if ($mode == 'update')
                <span class="style1"><i><font color="#ff0000">Contoh</font> : </i><span class="style5">160140GRN00000576</span></span> #end</td>
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
                  #if ($userRole == 'online_kjp' && $layerKJP  == $flagLayerKJP )
                  <input type="button" name="cmdKemaskiniTnh1" id="cmdKemaskiniTnh1" value="Kemaskini" onClick="doKemaskini()"/>
                  <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>
                  #end
                  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>                  
                  #end
                  #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskiniTnh" id="cmdSimpanKemaskiniTnh" value="Simpan" onClick="doSimpanKemaskiniMaklumatTnh()"/>
                  <input type="button" name="cmdBatalKemaskiniTnh" id="cmdBatalKemaskiniTnh" value="Batal" onClick="doBatalKemaskini()"/>
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
              #foreach ($beanMaklumatTukarguna in $BeanMaklumatTukarguna)
              <tr>
                <td width="1%" valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td width="28%" valign="top">Cadangan Kegunaan</td>
                <td width="1%" valign="top">:</td>
                <td width="70%" valign="top"><textarea name="txtCadanganKegunaan" id="txtCadanganKegunaan" rows="5" cols="50" $readonly class="$inputTextClass">$beanMaklumatTukarguna.cadanganKegunaan</textarea></td>
              </tr>
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td>Luas Kegunaan</td>
                <td>:</td>
                <td >$selectLuasKegunaan </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Keluasan Asal</td>
                <td>:</td>
                <td>$beanMaklumatTukarguna.luasAsal $beanMaklumatTukarguna.keteranganLuasAsal
                  <input type="hidden" name="txtLuasAsal" id="txtLuasAsal" value="$beanMaklumatTukarguna.luasAsal"/></td>
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
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonly class="$inputTextClass"/ >
                  #elseif ($idLuas == '7')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatTukarguna.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"/ $readonly class="$inputTextClass">
                  #elseif ($idLuas == '8' || $idLuas == '4')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatTukarguna.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$beanMaklumatTukarguna.luas3" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonly class="$inputTextClass"/>
                  #end </td>
              </tr>
              #end
              #end
              <tr>
                <td>&nbsp;</td>
                <td>Luas Bersamaan</td>
                <td>:</td>
                <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" value="$beanMaklumatTukarguna.luasBersamaan"  style="text-align:right" readonly="readonly" class="disabled"/>
                  HEKTAR</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Baki Luas</td>
                <td>:</td>
                <td><input type="text" name="txtBakiLuas" id="txtBakiLuas" value="$beanMaklumatTukarguna.luasBaki" readonly="readonly" class="disabled" style="text-align:right"/>
                  HEKTAR</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Surat Kelulusan KJP Disertakan ?</td>
                <td>:</td>
                <td>$selectFlagKJPLulus</td>
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
                  #if ($userRole == 'online_kjp' && $layerKJP  == $flagLayerKJP )
                  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
                  <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>
                  #end
                  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                  #end
                  #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniMaklumatPelepasan('$idLuas')"/>
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
          <div class="TabbedPanelsContent"> <br />
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #if ($flagPopup == 'openPopupTanahGanti')
              <tr>
                <td> #parse("app/php2/online/frmTKRMaklumatTanahGanti.jsp") </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              #end
              <tr>
                <td><fieldset>
                  <legend><b>SENARAI TANAH GANTI</b></legend>
                  <table align="center" width="100%">
                    #if ($idStatus == '')
                    #if ($userRole == 'online_kjp' && $layerKJP  == $flagLayerKJP )
                    <tr>
                      <td colspan="7" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:doDaftarBaruTanahGanti()"/></td>
                    </tr>
                    #end
                    #end
                    <tr class="table_header">
                      <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                      <td width="15%"><strong>Pegangan Hakmilik</strong></td>
                      <td width="10%"><strong>Lot</strong></td>
                      <td width="10%"><strong>No. Hakmilik</strong></td>
                      <td width="10%"><strong>No. Warta</strong></td>
                      <td width="15%"><strong>Mukim</strong></td>
                      <td width="15%"><strong>Daerah</strong></td>
                      <td width="20%"><strong>Negeri</strong></td>
                    </tr>
                    #set ($senaraiTanahGanti = "")
                    #if ($SenaraiTanahGanti.size() > 0)
                    #foreach ($senaraiTanahGanti in $SenaraiTanahGanti)
                    #if ($senaraiTanahGanti.bil == '')
                    #set( $row = "row1" )
                    #elseif (($senaraiTanahGanti.bil % 2) != 0)
                    #set( $row = "row1" )
                    #else 
                    #set( $row = "row2" )
                    #end
                    <tr>
                      <td class="$row" align="center">$senaraiTanahGanti.bil</td>
                      <td class="$row"><a href="javascript:doPaparTanahGanti('$senaraiTanahGanti.idTanahGanti')" class="style2">$senaraiTanahGanti.peganganHakmilik</a></td>
                      <td class="$row">$senaraiTanahGanti.noLot</td>
                      <td class="$row">$senaraiTanahGanti.noHakmilik</td>
                      <td class="$row">$senaraiTanahGanti.noWarta</td>
                      <td class="$row">$senaraiTanahGanti.mukim</td>
                      <td class="$row">$senaraiTanahGanti.daerah</td>
                      <td class="$row">$senaraiTanahGanti.negeri</td>
                    </tr>
                    #end
                    #else
                    <tr>
                      <td class="row1" align="center">&nbsp;</td>
                      <td class="row1">Tiada Rekod</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                    </tr>
                    #end
                    <tr>
                      <td colspan="7">&nbsp;</td>
                    </tr>
                    <tr>
                      <td colspan="7" align="center"> #if ($flagPopup == '')
                        #if ($idStatus == '')
                        #if ($mode == 'view')
                        <input type="button" name="cmdBackList3" id="cmdBackList3" value="Kembali" onClick="doBacklist()"/>
                        #if ($userRole == 'online_kjp' && $layerKJP  == $flagLayerKJP )
                        <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>                        
                        #end
                        #end
                         #else
                        <input type="button" name="cmdBackList3" id="cmdBackList3" value="Kembali" onClick="doBacklist()"/>
                        <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                        #end
                        #end </td>
                    </tr>
                  </table>
                  </fieldset></td>
              </tr>
            </table>
          </div>
          <div class="TabbedPanelsContent">
          	#parse("app/php2/online/frmTKRImejan.jsp")
          </div>
        </div>
      </div></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
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
function doChangeTab(tabId) {
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineSenaraiFailView";
	document.${formName}.method="POST";
	doAjaxCall${formName}("");
}
function pilihTanahTKR(idKategoriPemohon,idAgensiPemohon,idNegeriJKPTG) {
	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmTKRPopupSenaraiTanahOnlineView?idKategoriPemohon="+idKategoriPemohon+"&idAgensiPemohon="+idAgensiPemohon+"&idNegeriJKPTG="+idNegeriJKPTG;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function pilihTanahGanti(idKategoriPemohon,idKementerianPemohon,idAgensiPemohon) {
	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmTKRPopupSenaraiTanahGantiOnlineView?idKategoriPemohon="+idKategoriPemohon+"&idKementerianPemohon="+idKementerianPemohon+"&idAgensiPemohon="+idAgensiPemohon;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}function refreshFromPilihTanah(idHakmilikAgensi) {
	document.${formName}.idHakmilikAgensiPopup.value = idHakmilikAgensi;
	doAjaxCall${formName}("doChangeMaklumatTanah");
}

function doChangePeganganHakmilik1() {
	doAjaxCall${formName}("doChangePeganganHakmilik1");
}

function doSimpanKemaskiniMaklumatTnh() {

	if(document.${formName}.txtPeganganHakmilik1.value == ""){
		alert('Sila pilih Pegangan Hakmilik.');
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionOnline.value = "seterusnya";
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 0;
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatTnh";
	document.${formName}.submit();
}
function validateLuasMohon(elmnt,content,content2) {
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
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeAgensi() {
	doAjaxCall${formName}("doChangeAgensi");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangePejabat() {
	doAjaxCall${formName}("doChangePejabat");
}
function doChangeNegeriTanahGanti() {
	doAjaxCall${formName}("doChangeNegeriTanahGanti");
}
function doChangeDaerahTanahGanti() {
	doAjaxCall${formName}("doChangeDaerahTanahGanti");
}
function doChangeLuasKegunaan(param) {
	if (param == 'K'){
		document.getElementById("txtLuasPelepasan").setAttribute("className", "disabled");
		document.getElementById("txtLuasPelepasan").setAttribute("class", "disabled");
		document.${formName}.txtLuasPelepasan.readOnly = true;
		document.${formName}.txtBakiLuas.value = "0.00000";
		document.${formName}.txtLuasPelepasan.value = document.${formName}.txtLuasAsal.value;
	} else if (param == 'S'){
		document.getElementById("txtLuasPelepasan").setAttribute("className", "");
		document.getElementById("txtLuasPelepasan").setAttribute("class", "");
		document.${formName}.txtLuasPelepasan.readOnly = false;
	}
}
function calculate(valueMohon,valueBaki){	
	var luasPelepasan = document.${formName}.txtLuasPelepasan.value * 1;
	var luasAsal = document.${formName}.txtLuasAsal.value * 1;
	
	if (luasPelepasan != ""){	
		if (luasPelepasan > luasAsal){
			alert('Luas yang dimasukkan telah melebihi luas asal.');
			document.${formName}.txtLuasPelepasan.value = valueMohon;
			document.${formName}.txtBakiLuas.value = valueBaki;
			return; 
		} else if (luasPelepasan<0){
		    alert('Luas Tukar Guna tidak boleh kurang dari 0');
			document.${formName}.txtLuasPelepasan.value = "";
		}
		else {
			var luasBaki = (luasAsal - luasPelepasan) * 1;
			document.${formName}.txtBakiLuas.value = luasBaki.toFixed(5);
		}
	}
	else if (luasPelepasan == 0){
	 	   alert('Luas Tukar Guna tidak boleh 0');
		   document.${formName}.txtLuasPelepasan.value = "";
	 }
			
}
function doKemaskini() {
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.mode.value = "update";
	document.${formName}.submit();
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatPelepasan(idLuas) {
	
	if(document.${formName}.txtCadanganKegunaan.value == ""){
		alert('Sila masukkan Cadangan Kegunaan.');
  		document.${formName}.txtCadanganKegunaan.focus(); 
		return; 
	}
	if(document.${formName}.socLuasKegunaan.value == ""){
		alert('Sila pilih Luas Kegunaan.');
  		document.${formName}.socLuasKegunaan.focus(); 
		return; 
	}
	if(document.${formName}.socLuasKegunaan.value == "2"){
		if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Mohon .');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
		}
		else
		if(idLuas == '4' || idLuas == '8'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Mohon.');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Mohon.');
				document.${formName}.txtLuasMohon2.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon3.value == ""){
				alert('Sila masukkan Luas Mohon.');
				document.${formName}.txtLuasMohon3.focus(); 
				return; 
			}
		} 
		else
		if(idLuas == '7'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Mohon.');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Mohon.');
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
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPelepasan";
	document.${formName}.submit();
}
function doDaftarBaruTanahGanti(idNegeriPemohon){
	document.${formName}.flagPopup.value = "openPopupTanahGanti";
	document.${formName}.modePopup.value = "new";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doBatalTanahGanti(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doSimpanTanahGanti(){
	if(document.${formName}.txtPeganganHakmilikTG.value == ""){
		alert('Sila masukkan Pegangan Hakmilik.');
  		document.${formName}.txtPeganganHakmilikTG.focus(); 
		return; 
	}
	if(document.${formName}.socLuasKegunaanTG.value == ""){
		alert('Sila pilih Luas Kegunaan.');
  		document.${formName}.socLuasKegunaanTG.focus(); 
		return; 
	}
	
	if (document.${formName}.socLuasKegunaanTG.value == '2'){
		if(document.${formName}.socLuasTG.value == "0"){
			alert('Sila pilih Unit Luas.');
			document.${formName}.socLuasTG.focus(); 
			return; 
		}
		
		var idLuas = document.${formName}.socLuasTG.value;
	
		if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
			if(document.${formName}.txtLuasTG1.value == ""){
				alert('Sila masukkan Luas .');
				document.${formName}.txtLuasTG1.focus(); 
				return; 
			}
		}
		else
		if(idLuas == '4' || idLuas == '8'){
			if(document.${formName}.txtLuasTG1.value == ""){
				alert('Sila masukkan Luas.');
				document.${formName}.txtLuasTG1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasTG2.value == ""){
				alert('Sila masukkan Luas.');
				document.${formName}.txtLuasTG2.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasTG3.value == ""){
				alert('Sila masukkan Luas.');
				document.${formName}.txtLuasTG3.focus(); 
				return; 
			}
		} 
		else
		if(idLuas == '7'){
			if(document.${formName}.txtLuasTG1.value == ""){
				alert('Sila masukkan Luas.');
				document.${formName}.txtLuasTG1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasTG2.value == ""){
				alert('Sila masukkan Luas.');
				document.${formName}.txtLuasTG2.focus(); 
				return; 
			}
		}
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupTanahGanti";
		document.${formName}.modePopup.value = "new";
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.flagPopup.value = "openPopupTanahGanti";
	document.${formName}.modePopup.value = "new";
	document.${formName}.hitButton.value = "doSimpanTanahGanti";
	document.${formName}.submit();
}
function doBatalTanahGanti(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doPaparTanahGanti(idTanahGanti){
	document.${formName}.idTanahGanti.value = idTanahGanti;
	document.${formName}.flagPopup.value = "openPopupTanahGanti";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doKemaskiniTanahGanti(){
	document.${formName}.flagPopup.value = "openPopupTanahGanti";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniTanahGanti(){
	document.${formName}.flagPopup.value = "openPopupTanahGanti";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniTanahGanti(){
	if(document.${formName}.txtPeganganHakmilikTG.value == ""){
		alert('Sila masukkan Pegangan Hakmilik.');
  		document.${formName}.txtPeganganHakmilikTG.focus(); 
		return; 
	}
	if(document.${formName}.socLuasKegunaanTG.value == ""){
		alert('Sila pilih Luas Kegunaan.');
  		document.${formName}.socLuasKegunaanTG.focus(); 
		return; 
	}
	
	if (document.${formName}.socLuasKegunaanTG.value == '2'){
		if(document.${formName}.socLuasTG.value == "0"){
			alert('Sila pilih Unit Luas.');
			document.${formName}.socLuasTG.focus(); 
			return; 
		}
		
		var idLuas = document.${formName}.socLuasTG.value;
	

		if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
			if(document.${formName}.txtLuasTG1.value == ""){
				alert('Sila masukkan Luas .');
				document.${formName}.txtLuasTG1.focus(); 
				return; 
			}
		}
		else
		if(idLuas == '4' || idLuas == '8'){
			if(document.${formName}.txtLuasTG1.value == ""){
				alert('Sila masukkan Luas.');
				document.${formName}.txtLuasTG1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasTG2.value == ""){
				alert('Sila masukkan Luas.');
				document.${formName}.txtLuasTG2.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasTG3.value == ""){
				alert('Sila masukkan Luas.');
				document.${formName}.txtLuasTG3.focus(); 
				return; 
			}
		} 
		else
		if(idLuas == '7'){
			if(document.${formName}.txtLuasTG1.value == ""){
				alert('Sila masukkan Luas.');
				document.${formName}.txtLuasTG1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasTG2.value == ""){
				alert('Sila masukkan Luas.');
				document.${formName}.txtLuasTG2.focus(); 
				return; 
			}
		}
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupTanahGanti";
		document.${formName}.modePopup.value = "update";
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.flagPopup.value = "openPopupTanahGanti";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniTanahGanti";
	doAjaxCall${formName}("");
}
function doHapusTanahGanti(idTanahGanti){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupTanahGanti";
		document.${formName}.modePopup.value = "view";
		return;
	}
	
	document.${formName}.modePopup.value = "";
	document.${formName}.flagPopup.value = "";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusTanahGanti";
	document.${formName}.submit();
}
function doHantar(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantar";
	document.${formName}.submit();
}
function pilihTanah(idKementerian,idAgensi) {

	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmTKRPopupSenaraiTanahOnlineView?idKementerian="+idKementerian+"&idAgensi="+idAgensi+"&tanahGanti=ganti";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanahGanti(idHakmilikAgensiTG) {
	document.${formName}.idHakmilikAgensiTG.value = idHakmilikAgensiTG;
	doAjaxCall${formName}("doChangeMaklumatTanahGanti");
}
function doChangePeganganHakmilikGanti() {
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "openPopupTanahGanti";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("doChangePeganganHakmilikGanti");
}
function doHapusTanahGanti(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupTanahGanti";
		document.${formName}.modePopup.value = "new";
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "doHapusTanahGanti";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}

function doChangeLuasKegunaan() {
	doAjaxCall${formName}("doChangeLuasKegunaan");
}
function doChangeLuas() {
	doAjaxCall${formName}("doChangeLuas");
}
function doBacklist() {
	document.${formName}.actionOnline.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit();
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
function kiraLuasTG(idLuas){

  var jenisLuas = idLuas;
  
  // KILOMETER PERSEGI
  if(jenisLuas == "1"){

		var luasK = 0;
		if (document.${formName}.txtLuasTG1.value != ''){
			luasK = document.${formName}.txtLuasTG1.value*1;
		}
		var luasH = luasK*100;
		
		if (luasH > (document.${formName}.txtLuasAsalTG.value)*1){
			alert('Luas dimasukkan telah melebihi luas asal.')
			document.${formName}.txtLuasTG1.value = "";
			document.${formName}.txtLuasBersamaanTG.value = "";
			document.${formName}.txtBakiLuasTG.value = "";
		} else {
			document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsalTG.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuasTG.value = bakiLuas;
		}

   } else if(jenisLuas == "2"){ //HEKTER
  	
		var luasH = 0;
		if (document.${formName}.txtLuasTG1.value != ''){
			luasH = document.${formName}.txtLuasTG1.value*1;
		}
  		
		if (luasH > (document.${formName}.txtLuasAsalTG.value)*1){
			alert('Luas dimasukkan telah melebihi luas asal.')
			document.${formName}.txtLuasTG1.value = "";
			document.${formName}.txtLuasBersamaanTG.value = "";
			document.${formName}.txtBakiLuasTG.value = "";
		} else {
			document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsalTG.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuasTG.value = bakiLuas;
		}

   } else if(jenisLuas == "3"){ // METER PERSEGI
    	
		var luasM = 0;
		if (document.${formName}.txtLuasTG1.value != ''){
			luasM = document.${formName}.txtLuasTG1.value*1;
		}
  	  	var luasH = (luasM*0.0001);
	  	
		if (luasH > (document.${formName}.txtLuasAsalTG.value)*1){
			alert('Luas dimasukkan telah melebihi luas asal.')
			document.${formName}.txtLuasTG1.value = "";
			document.${formName}.txtLuasBersamaanTG.value = "";
			document.${formName}.txtBakiLuasTG.value = "";
		} else {
			document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsalTG.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuasTG.value = bakiLuas;
		}

   } else if(jenisLuas == "4"){  //EKAR, ROOD, POLE

	  	var luasE = 0;
		if (document.${formName}.txtLuasTG1.value != ''){
			luasE = document.${formName}.txtLuasTG1.value*1;
		}
	  	var luasR = 0;
		if (document.${formName}.txtLuasTG2.value != ''){
			luasR = document.${formName}.txtLuasTG2.value*1;
		}
	  	var luasP = 0;
		if (document.${formName}.txtLuasTG3.value != ''){
			luasP = document.${formName}.txtLuasTG3.value*1;
		}
	  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  	
		if (luasH > (document.${formName}.txtLuasAsalTG.value)*1){
			alert('Luas dimasukkan telah melebihi luas asal.')
			document.${formName}.txtLuasTG1.value = "";
			document.${formName}.txtLuasTG2.value = "";
			document.${formName}.txtLuasTG3.value = "";
			document.${formName}.txtLuasBersamaanTG.value = "";
			document.${formName}.txtBakiLuasTG.value = "";
		} else {
			document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsalTG.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuasTG.value = bakiLuas;
		}

   } else if(jenisLuas == "5"){ //KAKI PERSEGI
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuasTG1.value != ''){
	  	  luasAsal = document.${formName}.txtLuasTG1.value*1;
	  }
	  var luasH = luasAsal*0.0000092;
  	  
	  if (luasH > (document.${formName}.txtLuasAsalTG.value)*1){
			alert('Luas dimasukkan telah melebihi luas asal.')
			document.${formName}.txtLuasTG1.value = "";
			document.${formName}.txtLuasBersamaanTG.value = "";
			document.${formName}.txtBakiLuasTG.value = "";
		} else {
			document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsalTG.value - luasH).toFixed(5);
		 	document.${formName}.txtBakiLuasTG.value = bakiLuas;
		}

   } else if(jenisLuas == "6"){	//EKAR PERPULUHAN
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuasTG1.value != ''){
	  	  luasAsal = document.${formName}.txtLuasTG1.value*1;
	  }
	  var luasH = luasAsal*0.405;
	  
	  if (luasH > (document.${formName}.txtLuasAsalTG.value)*1){
			alert('Luas dimasukkan telah melebihi luas asal.')
			document.${formName}.txtLuasTG1.value = "";
			document.${formName}.txtLuasBersamaanTG.value = "";
			document.${formName}.txtBakiLuasTG.value = "";
		} else {
			document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsalTG.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuasTG.value = bakiLuas;
		}
  	  
   } else if(jenisLuas == "7"){ //EKAR,DEPA
  	  
	  	var luasE = 0;
		if (document.${formName}.txtLuasTG1.value != ''){
			luasE = document.${formName}.txtLuasTG1.value*1;
		}
	  	var luasD = 0;
		if (document.${formName}.txtLuasTG2.value != ''){
			luasD = document.${formName}.txtLuasTG2.value*1;
		}
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
	  
	  if (luasH > (document.${formName}.txtLuasAsalTG.value)*1){
			alert('Luas dimasukkan telah melebihi luas asal.')
			document.${formName}.txtLuasTG1.value = "";
			document.${formName}.txtLuasTG2.value = "";
			document.${formName}.txtLuasBersamaanTG.value = "";
			document.${formName}.txtBakiLuasTG.value = "";
		} else {
			document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsalTG.value - luasH).toFixed(5);
		  	document.${formName}.txtBakiLuasTG.value = bakiLuas;
		}

   } else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI
  	  
	 	var luasR = 0;
		if (document.${formName}.txtLuasTG1.value != ''){
			luasR = document.${formName}.txtLuasTG1.value*1;
		}
	  	var luasJ = 0;
		if (document.${formName}.txtLuasTG2.value != ''){
			luasJ = document.${formName}.txtLuasTG2.value*1;
		}
	  	var luasK = 0;
		if (document.${formName}.txtLuasTG3.value != ''){
			luasK = document.${formName}.txtLuasTG3.value*1;
		}
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
	  
	  if (luasH > (document.${formName}.txtLuasAsalTG.value)*1){
			alert('Luas dimasukkan telah melebihi luas asal.')
			document.${formName}.txtLuasTG1.value = "";
			document.${formName}.txtLuasTG2.value = "";
			document.${formName}.txtLuasTG3.value = "";
			document.${formName}.txtLuasBersamaanTG.value = "";
			document.${formName}.txtBakiLuasTG.value = "";
		} else {
			document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsalTG.value - luasH).toFixed(5);
		  	document.${formName}.txtBakiLuasTG.value = bakiLuas;
		}
	}
}
function doHantarSemakan(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarSemakan";
	document.${formName}.actionOnline.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doHantarKelulusan(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarKelulusan";
	document.${formName}.actionOnline.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doHantarEmel(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarEmel";
	document.${formName}.actionOnline.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doHapus(){
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapus";
	document.${formName}.actionOnline.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineSenaraiFailView";
	document.${formName}.method="POST";
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
function cetakPengesahanPermohonan(idPermohonan) {
	var url = "../servlet/ekptg.report.php2.online.TKRPengesahanPermohonanOnline?ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
