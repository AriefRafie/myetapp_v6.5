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

#set($saizTxtKemajuanTanah="1000")
#set($saizTxtPerkara="1000")
#set($saizTxtNamaProjek="1000")
<p>
  <input name="flagHakmilik" type="hidden" id="flagHakmilik" value="$!flagHakmilik" />
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idKategoriPemohon" type="hidden" id="idKategoriPemohon" value="$idKategoriPemohon"/>
  <input name="idAgensi" type="hidden" id="idAgensi" value="$idAgensi"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idHakmilik" type="hidden" id="idHakmilik" value="$idHakmilik"/>
  <input name="idPermohonanPelepasan" type="hidden" id="idPermohonanPelepasan" value="$idPermohonanPelepasan"/>
  <input name="idTanahGanti" type="hidden" id="idTanahGanti" value="$idTanahGanti"/>
  <input name="idHakmilikAgensiTG" type="hidden" id="idHakmilikAgensiTG" value="$idHakmilikAgensiTG"/>
  <input name="step" type="hidden" id="step" value="$step"/>
  <input name="dopost" type="hidden" id="dopost" />
  <input name="idNegeriPemohon" type="hidden" id="idNegeriPemohon" value="$idNegeriPemohon"/>
  <input name="idKementerianPemohon" type="hidden" id="idKementerianPemohon" value="$idKementerianPemohon"/>
  <input name="idHakmilikPermohonan" type="hidden" id="idHakmilikPermohonan" value="$idHakmilikPermohonan"/>
  <input name="idHakmilikSementara" type="hidden" id="idHakmilikSementara" value="$idHakmilikSementara"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmTKRHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH BERKAITAN</li>
          <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TUKARGUNA</li>
          <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PEMOHON</li>
          <li onClick="doChangeTabUpper(4);" class="TabbedPanelsTab" tabindex="0">TANAH GANTI</li>
          <li onClick="doChangeTabUpper(5);" class="TabbedPanelsTab" tabindex="0">SENARAI SEMAK</li>
          <!-- <li onClick="doChangeTabUpper(6);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT LAMPIRAN</li> -->
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> #if ($flagBorangK == 'Y')
            #parse("app/php2/frmPLPMaklumatBorangK.jsp")
            #else
            #parse("app/php2/frmPLPMaklumatTanah.jsp")
            #parse("app/php2/frmTKRTindakan.jsp")
            #end </div>

          <!-- START MAKLUMAT TANAH BERKAITAN -->
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><fieldset>
                    <legend><b>SENARAI TANAH BERKAITAN</b></legend>
                    <table align="center" width="100%">
                      <tr>
                        <td colspan="8" scope="row">
                          <input name="cmdDaftar" type="button" value="Pilih Hakmilik" onClick="javascript:doDaftarHakmilik('$idPermohonan','$idKategoriPemohon','$idNegeriPemohon','$idKementerianPemohon')"/>
                          <!-- <input name="cmdDaftar" type="button" value="Pilih Borang K" onClick="javascript:doDaftarBorangK('$idPermohonan','$idKategoriPemohon','$idNegeriPemohon','$idKementerianPemohon')"/> -->
                        </td>
                      </tr>
                      <tr class="table_header">
                        <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                        <td width="15%"><strong>Pegangan Hakmilik</strong></td>
                        <td width="10%"><strong>Lot</strong></td>
                        <td width="10%"><strong>No. Hakmilik</strong></td>
                        <td width="10%"><strong>No. Warta</strong></td>
                        <td width="15%"><strong>Mukim</strong></td>
                        <td width="15%"><strong>Daerah</strong></td>
                        <td width="15%"><strong>Negeri</strong></td>
                        <td width="5%"><strong></strong></td>
                      </tr>
                      #set ($senaraiTanahBerkaitan = "")
                      #if ($SenaraiTanahBerkaitan.size() > 0)
                      #foreach ($senaraiTanahBerkaitan in $SenaraiTanahBerkaitan)
                      #if ($senaraiTanahBerkaitan.bil == '')
                      #set( $row = "row1" )
                      #elseif (($senaraiTanahBerkaitan.bil % 2) != 0)
                      #set( $row = "row1" )
                      #else
                      #set( $row = "row2" )
                      #end
                      <tr>
                        <td class="$row" align="center">$senaraiTanahBerkaitan.bil</td>
                        <td class="$row">$senaraiTanahBerkaitan.peganganHakmilik</td>
                        <td class="$row">$senaraiTanahBerkaitan.noLot</td>
                        <td class="$row">$senaraiTanahBerkaitan.noHakmilik</td>
                        <td class="$row">$senaraiTanahBerkaitan.noWarta</td>
                        <td class="$row">$senaraiTanahBerkaitan.mukim</td>
                        <td class="$row">$senaraiTanahBerkaitan.daerah</td>
                        <td class="$row">$senaraiTanahBerkaitan.negeri</td>
                        <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:doHapus('$senaraiTanahBerkaitan.idHakmilikPermohonan')"><img border="0" src="../img/hapus.gif"/></a></td>
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
                        <td class="row1">&nbsp;</td>
                      </tr>
                      #end
                      <tr>
                        <td colspan="8">&nbsp;</td>
                      </tr>
                    </table>
                  </fieldset></td>
              </tr>
            </table>
          </div>
          <!-- END MAKLUMAT TANAH BERKAITAN -->

          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">

              #foreach ($beanMaklumatTukarguna in $BeanMaklumatTukarguna)
              <tr>
                <td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td width="28%" valign="top">Tarikh Terima</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatTukarguna.tarikhTerima" onBlur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass"/>
                  #if ($mode == 'update') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
              </tr>
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td valign="top">Tarikh Surat</td>
                <td>:</td>
                <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatTukarguna.tarikhSurat" onBlur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
                  #if ($mode == 'update')<a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
              </tr>
              <tr>
                <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td valign="top">Perkara</td>
                <td valign="top">:</td>
                <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtPerkara,this.form.remLen,$!saizTxtPerkara);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLen,$!saizTxtPerkara);" >$beanMaklumatTukarguna.perkara</textarea></td>
              </tr>
              #if ($mode == 'update')
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>Baki Aksara :&nbsp;
                  <input type="text" readonly class="disabled" name="remLen" size="3" maxlength="3" value="$!saizTxtPerkara" /></td>
              </tr>
              #end
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Cadangan Kegunaan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtCadanganKegunaan" id="txtCadanganKegunaan" rows="5" cols="50" $readonly class="$inputTextClass">$beanMaklumatTukarguna.cadanganKegunaan</textarea></td>
              </tr>
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td>Luas Kegunaan</td>
                <td>:</td>
                <td >$selectLuasKegunaan</td>
              </tr>
              #end
              #foreach ($beanMaklumatTukarguna1 in $BeanMaklumatTukarguna1)
              <tr>
                <td>&nbsp;</td>
                <td>Keluasan Asal</td>
                <td>:</td>
                <td>$beanMaklumatTukarguna1.luas
                  <input type="hidden" name="txtLuasAsal" id="txtLuasAsal" value="$!beanMaklumatTukarguna1.luasAsal"/></td>
              </tr>
              #end
              #foreach ($beanMaklumatTukarguna in $BeanMaklumatTukarguna)
              <tr>
                <td>&nbsp;</td>
                <td>Keluasan Asal</td>
                <td>:</td>
                <td>$beanMaklumatTukarguna.luasAsal $beanMaklumatTukarguna.keteranganLuasAsal
                  <input type="hidden" name="txtLuasAsal" id="txtLuasAsal" value="$beanMaklumatTukarguna.luasAsal"/></td>
              </tr>
              #if ($idLuasKegunaan == '2')

              #if ($mode == 'update')
              <tr>
                <td><span class="style1">*</span></td>
                <td>Unit Luas</td>
                <td>:</td>
                <td>#parse("app/php2/unit_luas.jsp") </td>
              </tr>
              #else
              <tr>
                <td><span class="style1"></span></td>
                <td>Unit Luas</td>
                <td>:</td>
                <td><select name="socLuas" id="socLuas" $inputTextClass class="$inputTextClass">
                    <option $selected value="0">SILA PILIH</option>
                    <option $selectedL1 value="1">H - HEKTAR</option>
                    <option $selectedL2 value="2">M - METER PERSEGI</option>
                    <option $selectedL3 value="3">P - EKAR PERPULUHAN</option>
                  </select></td>
              </tr>
              #end

              #if ($idLuas != '99999' && $idLuas != '')
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td>Luas Mohon</td>
                <td>:</td>
                <td> #if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' || $idLuas == '5' || $idLuas == '6' || $idLuas == '9')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"  $readonly class="$inputTextClass"/ >
                  #elseif ($idLuas == '7')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatTukarguna.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" / $readonly class="$inputTextClass">
                  #elseif ($idLuas == '8' || $idLuas == '4')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatTukarguna.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatTukarguna.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$beanMaklumatTukarguna.luas3" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"  $readonly class="$inputTextClass"/>
                  #end </td>
              </tr>
              #end
              #end
              <tr>
                <td>&nbsp;</td>
                <td>Luas Bersamaan</td>
                <td>:</td>
                <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" value="$beanMaklumatTukarguna.luasBersamaan"  style="text-align:right" readonly class="disabled"/>
                  HEKTAR</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Baki Luas</td>
                <td>:</td>
                <td><input type="text" name="txtBakiLuas" id="txtBakiLuas" value="$beanMaklumatTukarguna.luasBaki" readonly class="disabled" style="text-align:right"/>
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
                <td> #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="javascript:doSimpanKemaskiniMaklumatTukarguna('$idLuas')"/>
                  <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="javascript:doBatalKemaskini()"/>
                  #end
                  #if ($mode == 'view')
                  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="javascript:doKemaskini()"/>
                  #if($idStatus == '1610198')
                  <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="javascript:doSeterusnya()"/>
                  <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="javascript:gotoBatalPermohonan()"/>
                  #end
                  <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                  #end </td>
              </tr>
              #end
            </table>
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <tr>
                  <td><fieldset>
                      <legend><b>SENARAI TANAH</b></legend>
                      <table align="center" width="100%">
                        <tr class="table_header">
                          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                          <td width="15%"><strong>Pegangan Hakmilik</strong></td>
                          <td width="10%"><strong>Lot</strong></td>
                          <td width="10%"><strong>No. Hakmilik</strong></td>
                          <td width="10%"><strong>No. Warta</strong></td>
                          <td width="15%"><strong>Mukim</strong></td>
                          <td width="15%"><strong>Daerah</strong></td>
                          <td width="15%"><strong>Negeri</strong></td>
                        </tr>
                        #set ($senaraiTanahSemua = "")
                        #if ($SenaraiTanahSemua.size() > 0)
                        #foreach ($senaraiTanahSemua in $SenaraiTanahSemua)
                        #if ($senaraiTanahSemua.bil == '')
                        #set( $row = "row1" )
                        #elseif (($senaraiTanahSemua.bil % 2) != 0)
                        #set( $row = "row1" )
                        #else
                        #set( $row = "row2" )
                        #end
                        <tr>
                          <td class="$row" align="center">$senaraiTanahSemua.bil</td>
                          <td class="$row"><a href="javascript:papar('$senaraiTanahSemua.idHakmilikPermohonan','$senaraiTanahSemua.flagHakmilik')" class="style2">$senaraiTanahSemua.peganganHakmilik</a></td>
                          <td class="$row">$senaraiTanahSemua.noLot</td>
                          <td class="$row">$senaraiTanahSemua.noHakmilik</td>
                          <td class="$row">$senaraiTanahSemua.noWarta</td>
                          <td class="$row">$senaraiTanahSemua.namaMukim</td>
                          <td class="$row">$senaraiTanahSemua.namaDaerah</td>
                          <td class="$row">$senaraiTanahSemua.namaNegeri</td>
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
                          <td class="row1">&nbsp;</td>
                        </tr>
                        #end
                        <tr>
                          <td colspan="8">&nbsp;</td>
                        </tr>
                      </table>
                    </fieldset></td>
                </tr>
              </table>
            </div>
          </div>

          <div class="TabbedPanelsContent">
          	#if($selectedTabUpper==3)
            	#parse("app/php2/frmTKRMaklumatPemohon.jsp")
            #end
          </div>

          #if($selectedTabUpper!=3)
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #if ($flagPopup == 'openPopupTanahGanti')
              <tr>
                <td> #parse("app/php2/frmTKRMaklumatTanahGanti.jsp") </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              #end
              <tr>
                <td><fieldset>
                    <legend><b>SENARAI TANAH GANTI</b></legend>
                    <table align="center" width="100%">
                      <tr>
                        <td colspan="8" scope="row">
                        	<input name="cmdDaftar" type="button" value="Pilih Hakmilik" onClick="javascript:doDaftarHakmilik2('$idPermohonan','$idKategoriPemohon','$idNegeriPemohon','$idKementerianPemohon')"/>
							<!--<input name="cmdDaftar" type="button" value="Daftar Baru" onClick="javascript:doDaftarBaruTanahGanti()"/> -->
                        </td>
                      </tr>
                      <tr class="table_header">
                        <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                        <td width="15%"><strong>Pegangan Hakmilik</strong></td>
                        <td width="10%"><strong>Lot</strong></td>
                        <td width="10%"><strong>No. Hakmilik</strong></td>
                        <td width="10%"><strong>No. Warta</strong></td>
                        <td width="15%"><strong>Mukim</strong></td>
                        <td width="15%"><strong>Daerah</strong></td>
                        <td width="15%"><strong>Negeri</strong></td>
                        <td width="5%"><strong></strong></td>
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
                        <!-- <td class="$row"><a href="javascript:doPaparTanahGanti('$senaraiTanahGanti.idTanahGanti')" class="style2">$senaraiTanahGanti.peganganHakmilik</a></td> -->
                        <td class="$row">$senaraiTanahGanti.peganganHakmilik</td>
                        <td class="$row">$senaraiTanahGanti.noLot</td>
                        <td class="$row">$senaraiTanahGanti.noHakmilik</td>
                        <td class="$row">$senaraiTanahGanti.noWarta</td>
                        <td class="$row">$senaraiTanahGanti.mukim</td>
                        <td class="$row">$senaraiTanahGanti.daerah</td>
                        <td class="$row">$senaraiTanahGanti.negeri</td>
                        <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:doHapus2('$senaraiTanahGanti.idHakmilikPermohonan')"><img border="0" src="../img/hapus.gif"/></a></td>
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
                      #if ($flagPopup == '')
                      <tr>
                        <td colspan="7" align="center">
                          #if($idStatus == '1610198')
                          <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
                          <input type="button" name="cmdBatalPermohonan2" id="cmdBatalPermohonan2" value="Batal Permohonan" onClick="doBatalPermohonan()"/>
                          #end
                          <input type="button" name="cdmCetak4" id="cdmCetak4" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                        </td>
                      </tr>
                      #end
                    </table>
                  </fieldset></td>
              </tr>
            </table>
          </div>
          #end

          <div class="TabbedPanelsContent"> <br>
            #if($selectedTabUpper==5)
            	#parse("app/php2/frmTKRSenaraiSemak.jsp")
            #end
          </div>

          <!-- <div class="TabbedPanelsContent"> <br>
            #if($selectedTabUpper==6)
            	#parse("app/php2/frmTKRPelan.jsp")
            #end
          </div> -->

        </div>
      </div></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
  <legend><strong>SENARAI LAPORAN</strong></legend>
  <table width="100%" border="0" cellspacing="2" cellpadding="2">
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakKulitFail('$idFail')"> Kulit Fail </a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakTKRSuratAkuanTerima('$idFail')"> Surat Akuan Terima </a></td>
    </tr>
    #if ($flagBorangK != 'Y')
    #if ($statusRizab == 'MILIK')
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakMaklumatHakmilik('$idHakmilik')"> Maklumat Tanah </a></td>
    </tr>
    #elseif ($statusRizab == 'RIZAB')
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakMaklumatRizab('$idHakmilik')"> Maklumat Tanah </a></td>
    </tr>
    #end
    #end
  </table>
</fieldset>
<fieldset id="tableReportTanah" style="display:none;"-->
  <legend><strong>SENARAI LAPORAN</strong></legend>
  <table width="100%" border="0" cellspacing="2" cellpadding="2">
    #if ($flagBorangK != 'Y')
    #if ($statusRizab == 'MILIK')
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakMaklumatTanah('$idHakmilikAgensiTG')"> Maklumat Tanah</a></td>
    </tr>
    #elseif ($statusRizab == 'RIZAB')
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakMaklumatTanahRizab('$idHakmilikAgensiTG')"> Maklumat Tanah</a></td>
    </tr>
    #end
    #end
  </table>
</fieldset>
<script type="text/javascript">

// function doSimpanKemaskiniMaklumatTukarguna() {
// 	document.${formName}.mode.value = "update";
// // 	document.${formName}.selectedTabUpper.value = '2';
// 	document.${formName}.flagPopup.value = "openPopupMaklumatPermohonan";
// 	doAjaxCall${formName}("");
// }

function papar(idHakmilikPermohonan, flagHakmilik){
// 	document.${formName}.selectedTabUpper.value = '2';
	document.${formName}.idHakmilikPermohonan.value = idHakmilikPermohonan;
	document.${formName}.flagHakmilik.value = flagHakmilik;
	document.${formName}.flagPopup.value = "openPopupMaklumatPermohonan";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}

#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doDaftarHakmilik(idPermohonan,idKategoriPemohon,idNegeriPemohon,idKementerianPemohon) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupSenaraiTanahBerkaitanView?idPermohonan="+idPermohonan+"&idKategoriPemohon="+idKategoriPemohon+"&idNegeriPemohon="+idNegeriPemohon+"&idKementerianPemohon="+idKementerianPemohon;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function doDaftarBorangK(idPermohonan,idKategoriPemohon,idNegeriPemohon,idKementerianPemohon) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupSenaraiBorangKBerkaitanView?idPermohonan="+idPermohonan+"&idKategoriPemohon="+idKategoriPemohon+"&idNegeriPemohon="+idNegeriPemohon+"&idKementerianPemohon="+idKementerianPemohon;
	var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	   hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
}

</script>
<script>

function cetakMaklumatTanah(idhakmilik){
	var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatHakmilik&idHakmilik="+idhakmilik;
	var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMaklumatTanahRizab(idhakmilik){
	var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatRizab&idHakmilik="+idhakmilik;
	var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function gotoCetak(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function gotoSimpan(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.hitButton.value = "doSimpanMaklumatTanah";
	doAjaxCall${formName}("doChangeMaklumatTanah");
}
function gotoBatal(){
	document.${formName}.hitButton.value = "batal";
	document.${formName}.submit();
}
function goToKemaskini(idKategoriPemohon,idAgensiPemohon,idNegeriJKPTG){
	document.${formName}.hitButton.value = "kemaskiniMaklumatTanah";

	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupSenaraiTanahView?idKategoriPemohon="+idKategoriPemohon+"&idAgensiPemohon="+idAgensiPemohon+"&idNegeriJKPTG="+idNegeriJKPTG;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function gotoHapusFail(){
 	document.${formName}.step.value = "hapusFail";
	document.${formName}.submit();
}
function doChangeTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
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
function doChangeKategori() {
	doAjaxCall${formName}("doChangeKategori");
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
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function doChangeLuasKegunaan() {
	doAjaxCall${formName}("doChangeLuasKegunaan");
}
function doChangeLuas() {
	doAjaxCall${formName}("doChangeLuas");
}
function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}
function doChangePejabat() {
	doAjaxCall${formName}("doChangePejabat");
}
function pilihTanahGanti(idKategoriPemohon,idAgensiPemohon) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupSenaraiTanahGantiView?idKategoriPemohon="+idKategoriPemohon+"&idAgensiPemohon="+idAgensiPemohon;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensiTG) {
	document.${formName}.idHakmilikAgensiTG.value = idHakmilikAgensiTG;
	doAjaxCall${formName}("doChangeMaklumatTanah");
}
function refreshFromPilihTanahBerkaitan(idPermohonan,idHakmilikAgensi,idHakmilikSementara) {
	document.${formName}.idHakmilikAgensiTG.value = idHakmilikAgensi;
	document.${formName}.hitButton.value = "doSimpanMaklumatTanahBerkaitan";
	document.${formName}.dopost.value = "tru";
	document.${formName}.selectedTabUpper.value = 1;
	doAjaxCall${formName}("");
}
function refreshFromPilihTanahBukanBerkaitan(idHakmilikAgensi,idHakmilikSementara) {
	document.${formName}.idHakmilikAgensiTG.value = idHakmilikAgensi;
	document.${formName}.hitButton.value = "doSimpanMaklumatTanah";
	document.${formName}.dopost.value = "tru";
	document.${formName}.selectedTabUpper.value = 0;
	doAjaxCall${formName}("");
}
function refreshFromPilihTanahBorangK() {
	doAjaxCall${formName}("");
}

function doKemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatTukarguna(idLuas) {
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
	if(document.${formName}.socLuasKegunaan.value == ""){
		alert('Sila masukkan Luas Kegunaan.');
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
				alert('Sila masukkan Luas Tukarguna .');
				document.${formName}.txtLuasMohon1.focus();
				return;
			}
		}
		else
		if(idLuas == '4' || idLuas == '8'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Tukarguna.');
				document.${formName}.txtLuasMohon1.focus();
				return;
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Tukarguna.');
				document.${formName}.txtLuasMohon2.focus();
				return;
			}
			if(document.${formName}.txtLuasMohon3.value == ""){
				alert('Sila masukkan Luas Tukarguna.');
				document.${formName}.txtLuasMohon3.focus();
				return;
			}
		}
		else
		if(idLuas == '7'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Tukarguna.');
				document.${formName}.txtLuasMohon1.focus();
				return;
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Tukarguna.');
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
	document.${formName}.flagPopup.value = "openPopupMaklumatPermohonan";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatTukarguna";
	document.${formName}.submit();
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
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
		return;
	}
}
function doDaftarBaruTanahGanti(){
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
	if(document.${formName}.socJenisHakmilikTG.value == ""){
		alert('Sila pilih Jenis Hakmilik.');
  		document.${formName}.socJenisHakmilikTG.focus();
		return;
	}
	if(document.${formName}.txtNoHakmilikTG.value == ""){
		alert('Sila masukkan No. Hakmilik.');
  		document.${formName}.txtNoHakmilikTG.focus();
		return;
	}
	if(document.${formName}.socLotTG.value == ""){
		alert('Sila pilih Lot.');
  		document.${formName}.socLotTG.focus();
		return;
	}
	if(document.${formName}.txtNoLotTG.value == ""){
		alert('Sila masukkan No. Lot.');
  		document.${formName}.txtNoLotTG.focus();
		return;
	}
	if(document.${formName}.socLuasTG.value == "0"){
		alert('Sila pilih Unit Luas.');
		document.${formName}.socLuasTG.focus();
		return;
	}

	var idLuas = document.${formName}.socLuasTG.value;

	if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
		if(document.${formName}.txtLuas1TG.value == ""){
			alert('Sila masukkan Luas .');
			document.${formName}.txtLuas1TG.focus();
			return;
		}
	}
	else
	if(idLuas == '4' || idLuas == '8'){
		if(document.${formName}.txtLuas1TG.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas1TG.focus();
			return;
		}
		if(document.${formName}.txtLuas2TG.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas2TG.focus();
			return;
		}
		if(document.${formName}.txtLuas3TG.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas3TG.focus();
			return;
		}
	}
	else
	if(idLuas == '7'){
		if(document.${formName}.txtLuas1TG.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas1TG.focus();
			return;
		}
		if(document.${formName}.txtLuas2TG.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas2TG.focus();
			return;
		}
	}
	if(document.${formName}.socNegeriTG.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeriTG.focus();
		return;
	}
	if(document.${formName}.socDaerahTG.value == ""){
		alert('Sila pilih Daerah.');
  		document.${formName}.socDaerahTG.focus();
		return;
	}
	if(document.${formName}.socMukimTG.value == ""){
		alert('Sila pilih Mukim.');
  		document.${formName}.socMukimTG.focus();
		return;
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
	if(document.${formName}.socJenisHakmilikTG.value == ""){
		alert('Sila pilih Jenis Hakmilik.');
  		document.${formName}.socJenisHakmilikTG.focus();
		return;
	}
	if(document.${formName}.txtNoHakmilikTG.value == ""){
		alert('Sila masukkan No. Hakmilik.');
  		document.${formName}.txtNoHakmilikTG.focus();
		return;
	}
	if(document.${formName}.socLotTG.value == ""){
		alert('Sila pilih Lot.');
  		document.${formName}.socLotTG.focus();
		return;
	}
	if(document.${formName}.txtNoLotTG.value == ""){
		alert('Sila masukkan No. Lot.');
  		document.${formName}.txtNoLotTG.focus();
		return;
	}
	if(document.${formName}.socLuasTG.value == "0"){
		alert('Sila pilih Unit Luas.');
		document.${formName}.socLuasTG.focus();
		return;
	}

	var idLuas = document.${formName}.socLuasTG.value;

	if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
		if(document.${formName}.txtLuas1TG.value == ""){
			alert('Sila masukkan Luas .');
			document.${formName}.txtLuas1TG.focus();
			return;
		}
	}
	else
	if(idLuas == '4' || idLuas == '8'){
		if(document.${formName}.txtLuas1TG.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas1TG.focus();
			return;
		}
		if(document.${formName}.txtLuas2TG.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas2TG.focus();
			return;
		}
		if(document.${formName}.txtLuas3TG.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas3TG.focus();
			return;
		}
	}
	else
	if(idLuas == '7'){
		if(document.${formName}.txtLuas1TG.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas1TG.focus();
			return;
		}
		if(document.${formName}.txtLuas2TG.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas2TG.focus();
			return;
		}
	}
	if(document.${formName}.socNegeriTG.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeriTG.focus();
		return;
	}
	if(document.${formName}.socDaerahTG.value == ""){
		alert('Sila pilih Daerah.');
  		document.${formName}.socDaerahTG.focus();
		return;
	}
	if(document.${formName}.socMukimTG.value == ""){
		alert('Sila pilih Mukim.');
  		document.${formName}.socMukimTG.focus();
		return;
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
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}

function kiraLuas(idLuas){

  var jenisLuas = idLuas;

  // KILOMETER PERSEGI
  if(jenisLuas == "6"){

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

   } else if(jenisLuas == "1"){ //HEKTER

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

   } else if(jenisLuas == "2"){ // METER PERSEGI

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

   } else if(jenisLuas == "3"){	//EKAR PERPULUHAN

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
		if (document.${formName}.txtLuas1TG.value != ''){
			luasK = document.${formName}.txtLuas1TG.value*1;
		}
		var luasH = luasK*100;
		document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);

   } else if(jenisLuas == "2"){ //HEKTER

		var luasH = 0;
		if (document.${formName}.txtLuas1TG.value != ''){
			luasH = document.${formName}.txtLuas1TG.value*1;
		}
		document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);

   } else if(jenisLuas == "3"){ // METER PERSEGI

		var luasM = 0;
		if (document.${formName}.txtLuas1TG.value != ''){
			luasM = document.${formName}.txtLuas1TG.value*1;
		}
  	  	var luasH = (luasM*0.0001);
	  	document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);

   } else if(jenisLuas == "4"){  //EKAR, ROOD, POLE

	  	var luasE = 0;
		if (document.${formName}.txtLuas1TG.value != ''){
			luasE = document.${formName}.txtLuas1TG.value*1;
		}
	  	var luasR = 0;
		if (document.${formName}.txtLuas2TG.value != ''){
			luasR = document.${formName}.txtLuas2TG.value*1;
		}
	  	var luasP = 0;
		if (document.${formName}.txtLuas3TG.value != ''){
			luasP = document.${formName}.txtLuas3TG.value*1;
		}
	  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  	document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);

   } else if(jenisLuas == "5"){ //KAKI PERSEGI

	  var luasAsal = 0;
	  if (document.${formName}.txtLuas1TG.value != ''){
		  luasAsal = document.${formName}.txtLuas1TG.value*1;
	  }
	  var luasH = luasAsal*0.0000092;
  	  document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);

   } else if(jenisLuas == "6"){	//EKAR PERPULUHAN

	  var luasAsal = 0;
	  if (document.${formName}.txtLuas1TG.value != ''){
		  luasAsal = document.${formName}.txtLuas1TG.value*1;
	  }
	  var luasH = luasAsal*0.405;
	  document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);

   } else if(jenisLuas == "7"){ //EKAR,DEPA

	  var luasE = 0;
	  if (document.${formName}.txtLuas1TG.value != ''){
		  luasE = document.${formName}.txtLuas1TG.value*1;
	  }
	  var luasD = 0;
	  if (document.${formName}.txtLuas2TG.value != ''){
		  luasD = document.${formName}.txtLuas2TG.value*1;
	  }

	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
	  document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);

   } else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI

	  var luasR = 0;
	  if (document.${formName}.txtLuas1TG.value != ''){
		  luasR = document.${formName}.txtLuas1TG.value*1;
	  }
	  var luasJ = 0;
	  if (document.${formName}.txtLuas2TG.value != ''){
		  luasJ = document.${formName}.txtLuas2TG.value*1;
	  }
	  var luasK = 0;
	  if (document.${formName}.txtLuas3TG.value != ''){
		  luasK = document.${formName}.txtLuas3TG.value*1;
	  }

	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
	  document.${formName}.txtLuasBersamaanTG.value = luasH.toFixed(5);
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
	var url = "../servlet/ekptg.report.php2.TKRKulitFail?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratAkuanTerima(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratAkuanTerima";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakMaklumatRizab(idhakmilik){
	var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatRizab&idHakmilik="+idhakmilik;
	var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMaklumatHakmilik(idhakmilik){
	var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatHakmilik&idHakmilik="+idhakmilik;
	var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
	if (hWnd.focus != null) hWnd.focus();
}

//maklumat pemohon
function doDaftarBaruPemohon(){
	document.${formName}.flagPopup.value = "openPopupPemohon";
	document.${formName}.hitButton.value = "addNewPemohon";
	document.${formName}.mode.value = "addNewPemohon";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatPemohon() {
	if(document.${formName}.socKategoriPemohon.value == ""){
		alert('Sila pilih Jenis Kategori Pemohon.');
  		document.${formName}.socKategoriPemohon.focus();
		return;
	}
	if(document.${formName}.socKategoriPemohon.value == "3"){
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
	} else if(document.${formName}.socKategoriPemohon.value == "8") {
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih Negeri.');
			document.${formName}.socNegeri.focus();
			return;
		}
		if(document.${formName}.socPejabat.value == ""){
			alert('Sila pilih Pejabat.');
			document.${formName}.socPejabat.focus();
			return;
		}
// 		if(document.${formName}.socKementerian.value == ""){
// 			alert('Sila pilih Kementerian.');
// 			document.${formName}.socKementerian.focus();
// 			return;
// 		}
// 		if(document.${formName}.socAgensi.value == ""){
// 			alert('Sila pilih Agensi.');
// 			document.${formName}.socAgensi.focus();
// 			return;
// 		}
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
// 		document.${formName}.mode.value = "update";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "closePopupPemohon";
	document.${formName}.hitButton.value = "doSimpanMaklumatPemohon";
	document.${formName}.submit();
}
function doBatalMaklumatPemohon() {
	document.${formName}.flagPopup.value = "closePopupPemohon";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doPaparPemohon(idPemohon){
	document.${formName}.idPemohon.value = idPemohon;
	document.${formName}.flagPopup.value = "openPopupPemohon";
	document.${formName}.hitButton.value = "viewPemohon";
	document.${formName}.mode.value = "viewPemohon";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatPemohon(){
	document.${formName}.flagPopup.value = "openPopupPemohon";
	document.${formName}.hitButton.value = "updatePemohon";
	document.${formName}.mode.value = "updatePemohon";
	doAjaxCall${formName}("");
}
function doHapusMaklumatPemohon(){
	document.${formName}.flagPopup.value = "closePopupPemohon";
	document.${formName}.hitButton.value = "doHapusMaklumatPemohon";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniMaklumatPemohon() {
	document.${formName}.mode.value = "viewPemohon";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatPemohon() {
	if(document.${formName}.socKategoriPemohon.value == ""){
		alert('Sila pilih Jenis Kategori Pemohon.');
  		document.${formName}.socKategoriPemohon.focus();
		return;
	}
	if(document.${formName}.socKategoriPemohon.value == "3"){
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
	} else if(document.${formName}.socKategoriPemohon.value == "8") {
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih Negeri.');
			document.${formName}.socNegeri.focus();
			return;
		}
		if(document.${formName}.socPejabat.value == ""){
			alert('Sila pilih Pejabat.');
			document.${formName}.socPejabat.focus();
			return;
		}
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

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}

	document.${formName}.flagPopup.value = "closePopupPemohon";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPemohon";
	document.${formName}.submit();
}

function cetakPelan(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

  function daftarDokumenP() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmTKRMaklumatPermohonanView";
	document.${formName}.method="POST";
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}


function simpanDokumenP(idPermohonan) {
	if(document.${formName}.txtNamaPelan.value == ""){
		alert('Sila masukkan Nama Pelan.');
  		document.${formName}.txtNamaPelan.focus();
		return;
	}
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Pelan yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus();
		return;
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	var namaPelan = document.${formName}.txtNamaPelan.value;
 	var catatanPelan = document.${formName}.txtCatatanPelan.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;

	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmTKRMaklumatPermohonanView&hitButton=simpanDokumen&namaPelan="+namaPelan+"&catatanPelan="+catatanPelan+"&selectedTabUpper=6"+dopost+"&mode=view&flagPopup=openPopupDokumen&modePopup=new&idPermohonan="+idPermohonan;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function batalDokumenP(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniDokumenP(){
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniDokumenP() {

	if(document.${formName}.txtNamaPelan.value == ""){
		alert('Sila masukkan Nama Pelan.');
  		document.${formName}.txtNamaPelan.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniDokumen";
	doAjaxCall${formName}("");
}
function paparDokumenP(idDokumen){
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmTKRMaklumatPermohonanView";
	document.${formName}.method="POST";
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}
function hapusDokumenP(idDokumen){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.hitButton.value = "hapusDokumen";
	document.${formName}.submit();
}

function doHapus(idHakmilikPermohonan) {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.idHakmilikPermohonan.value = idHakmilikPermohonan;
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapus";
	doAjaxCall${formName}("");
}

function doHapus2(idHakmilikPermohonan) {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.idHakmilikPermohonan.value = idHakmilikPermohonan;
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapus";
	doAjaxCall${formName}("");
}
</script>
<script>
function doSimpanKemaskiniSenaraiSemak() {

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniSenaraiSemak";
	document.${formName}.submit();
}
</script>
$javascriptLampiran
