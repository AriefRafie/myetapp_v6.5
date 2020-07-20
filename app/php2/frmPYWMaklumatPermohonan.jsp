<script type="text/javascript" src="../../library/js/report.js" ></script>
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
#set($saizTxtTujuan="1000")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
  <input name="idPermohonanSewa" type="hidden" id="idPermohonanSewa" value="$idPermohonanSewa"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idHakmilik" type="hidden" id="idHakmilik" value="$idHakmilik"/>
  <input name="idHakmilikAgensi" type="hidden" id="idHakmilikAgensi" value="$idHakmilikAgensi"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idNegeriPemohon" type="hidden" id="idNegeriPemohon" value="$idNegeriPemohon"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="statusRizab" type="hidden" id="statusRizab" value="$statusRizab"/>
  <input name="flagBorangK" type="hidden" id="flagBorangK" value="$flagBorangK"/>
  <input name="idBorangK" type="hidden" id="idBorangK" value="$idBorangK"/>
  <input name="idPPTBorangK" type="hidden" id="idPPTBorangK" value="$idPPTBorangK"/>
  <input name="idHakmilikUrusan" type="hidden" id="idHakmilikUrusan" value="$idHakmilikUrusan"/>
  <input name="idPHPBorangK" type="hidden" id="idPHPBorangK" value="$idPHPBorangK"/>
  <input name="idJenisTanah" type="hidden" id="idJenisTanah" value="$idJenisTanah"/>
  <input name="idHakmilikPermohonan" type="hidden" id="idHakmilikPermohonan"/>
  <input name="step" type="hidden" id="step" value="$step"/>
  <input name="userRole" type="hidden" id="userRole" value="$userRole"/>
  <input name="flagSebabTamat" type="hidden" id="flagSebabTamat" value="$flagSebabTamat"/>
  <input name="afterSave" type="hidden" id="afterSave" value="$afterSave"/>
  <input id="inputbaru" name="inputbaru" value="$!inputbaru" type="hidden" />
  <input name="idDokumen" type="hidden" id="idDokumen" value="$!idDokumen"/>
</p>
#set ($inputbaru = '')
 #if($afterSave == 'doTangguhMaklumatTambahan')
<tr>
  <td>&nbsp;
    <div class="warning">FAIL TELAH DIHANTAR UNTUK MAKLUMAT TAMBAHAN</div></td>
</tr>
#elseif ($afterSave == 'doPengesahanPengarahNegeri')
<tr>
  <td>&nbsp;
    <div class="warning">FAIL TELAH DIHANTAR UNTUK SEMAKAN PENGARAH</div></td>
</tr>
#else
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPYWHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI TUGASAN TERLEBIH DAHULU</div></td>
  </tr>
  #end
  
  #if ($idFail != '')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH BERKAITAN</li>
          <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PERMOHONAN</li>
          <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PEMOHON</li>
          <!--           <li onClick="doChangeTabUpper(4);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PENAMATAN PENYEWAAN</li> -->
          <li onClick="doChangeTabUpper(4);" class="TabbedPanelsTab" tabindex="0">SENARAI SEMAK</li>
          <li onClick="doChangeTabUpper(5);" class="TabbedPanelsTab" tabindex="0">LAMPIRAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> #if ($flagBorangK == 'Y') 
            #parse("app/php2/frmPLPMaklumatBorangK.jsp") 
            #else
            #parse("app/php2/frmPLPMaklumatTanah.jsp") 
            #end
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="10%"></td>
                <td width="20%" align="left"> #if ($userRole == '(PHP)PYWPengarahNegeri')   
                  #if ($idStatus == '1615200')
                  #set ($modePopup = 'openSyorTolakRingkas')
                  #end
                  <!-- 		          			#if ($idStatus != '1615199') -->
                  <!-- 		          		    	<input name="cmdSetujuTolakRingkas" type="button" value="Setuju Tolak Ringkas" onClick="javascript:doSetujuTolakRingkas('$idPermohonan')"/> -->
                  <!-- 		                 		<input name="cmdTangguhMaklumatTambahan" type="button" value="Tangguh untuk Maklumat Tambahan" onClick="javascript:doTangguhMaklumatTambahan('$idPermohonan')"/>    -->
                  <!-- 		                 	#end -->
                  #elseif ($userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')     
                  #if ($idStatus != '1615199')
                  <!-- <input name="cmdSemakStatusTanah" type="button" value="Semak Status Tanah" onClick="javascript:doSemakStatusTanah('$noLotTanah')"/> -->
                  <input name="cmdSyorTolakRingkas" type="button" value="Syor Tolak Ringkas" onClick="javascript:doSyorTolakRingkas('$idPermohonan')"/>
                  #end         
                  #end </td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td> #parse("app/php2/frmPYWSyorTolakRingkas.jsp") </td>
              </tr>
            </table>
          </div>
          <!-- START MAKLUMAT TANAH BERKAITAN -->
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><fieldset>
                  <legend><b>SENARAI TANAH BERKAITAN</b></legend>
                  <table align="center" width="100%">
                    #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
                    <tr>
                      <td colspan="8" scope="row"><input name="cmdDaftar" type="button" value="Pilih Hakmilik" onClick="javascript:doDaftarHakmilik('$idPermohonan')"/>
                        <input name="cmdDaftar" type="button" value="Pilih Borang K" onClick="javascript:doDaftarBorangK('$idPermohonan')"/></td>
                    </tr>
                    #end
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
                    #if ($flagPopup == '')
                    <tr>
                      <td colspan="8" align="center"> #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
                        #if($idStatus == '1610198')
                        <input type="button" name="cmdHantar" id="cmdHantar" value="Ke Jabatan Teknikal" onClick="doSeterusnya()"/>
                        <input type="button" name="cmdBatalPermohonan2" id="cmdBatalPermohonan2" value="Batal Permohonan" onclick="gotoBatalPermohonan()"/>
                        #end
                        #end
                        <input type="button" name="cdmCetak4" id="cdmCetak4" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                        #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
                        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
                        #end </td>
                    </tr>
                    #end
                  </table>
                  </fieldset></td>
              </tr>
            </table>
          </div>
          <!-- END MAKLUMAT TANAH BERKAITAN -->
          <!-- MAKLUMAT PERMOHONAN -->
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanMaklumatSewa in $BeanMaklumatSewa)
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td valign="top">Tarikh Surat / Borang</td>
                <td>:</td>
                <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatSewa.tarikhSurat" onBlur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
                  #if ($mode == 'update')<a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
              </tr>
              <tr>
                <td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td valign="top" width="28%">Tarikh Terima</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatSewa.tarikhTerima" onBlur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass"/>
                  #if ($mode == 'update') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td valign="top">No. Rujukan Surat</td>
                <td>:</td>
                <td><input name="txtNoRujukanSurat" type="text" class="$inputTextClass" id="txtNoRujukanSurat" value="$beanMaklumatSewa.noRujukanSurat" $readonly onblur="this.value=this.value.toUpperCase();" size="50" maxlength="50"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td valign="top">No. Fail Negeri</td>
                <td>:</td>
                <td><input name="txtNoFailNegeri" type="text" class="$inputTextClass" id="txtNoFailNegeri" value="$beanMaklumatSewa.noFailNegeri" $readonly onblur="this.value=this.value.toUpperCase();" size="50" maxlength="50"/></td>
              </tr>
              <tr>
                <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td valign="top">Perkara</td>
                <td valign="top">:</td>
                <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtPerkara,this.form.remLen,$!saizTxtPerkara);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLen,$!saizTxtPerkara);" >$beanMaklumatSewa.perkara</textarea></td>
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
              #if ($beanMaklumatSewa.tujuan != '')
              <tr>
                <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td valign="top">Tujuan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtTujuan" id="txtTujuan" rows="5" cols="50" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtTujuan,this.form.remLen1,$!saizTxtTujuan);" onKeyDown="textCounter(this.form.txtTujuan,this.form.remLen1,$!saizTxtTujuan);" >$beanMaklumatSewa.tujuan</textarea></td>
              </tr>
              #if ($mode == 'update')
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>Baki Aksara :&nbsp;
                  <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtTujuan" /></td>
              </tr>
              #end
              #else
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Tujuan 1</td>
                <td valign="top">:</td>
                <td>$selectJenisTujuan</td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Tujuan 2</td>
                <td valign="top">:</td>
                <td>$selectJenisTujuan2</td>
              </tr>
              #end
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Catatan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $readonly class="$inputTextClass">$beanMaklumatSewa.catatan</textarea></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Tempoh Permohonan Sewa</td>
                <td>:</td>
                <td><select name="socTempohSewa" id="socTempohSewa" style="width:140px;" $readonly class="$disabled" $disabled>
                    
            #if ($beanMaklumatSewa.flagTempohSewa == 'B')
                    
                    <option>SILA PILIH</option>
                    <option value="B" selected>BULAN KE BULAN</option>
                    <option value="T">3 TAHUN</option>
                    <option value="JP">JANGKA PENDEK</option>
                    
            #elseif ($beanMaklumatSewa.flagTempohSewa == 'T')
                    
                    <option>SILA PILIH</option>
                    <option value="B">BULAN KE BULAN</option>
                    <option value="T" selected>3 TAHUN</option>
                    <option value="JP">JANGKA PENDEK</option>
                    
            #elseif ($beanMaklumatSewa.flagTempohSewa == 'JP')
                    
                    <option>SILA PILIH</option>
                    <option value="B">BULAN KE BULAN</option>
                    <option value="T">3 TAHUN</option>
                    <option value="JP" selected>JANGKA PENDEK</option>
                    
            #else
                    
                    <option selected>SILA PILIH</option>
                    <option value="B">BULAN KE BULAN</option>
                    <option value="T">3 TAHUN</option>
                    <option value="JP">JANGKA PENDEK</option>
                    
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
                <td>Luas Dipohon Sewa</td>
                <td>:</td>
                <td> #if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' )
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatSewa.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"  $readonly class="$inputTextClass"/>
                  #elseif ($idLuas == '7')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatSewa.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatSewa.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" / $readonly class="$inputTextClass">
                  #elseif ($idLuas == '8' || $idLuas == '4')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatSewa.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatSewa.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$beanMaklumatSewa.luas3" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"  $readonly class="$inputTextClass"/>
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
                <td> #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniMaklumatSewa('$idLuas')"/>
                  <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
                  #end
                  #if ($mode == 'view')
                  #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
                  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
                  #if($idStatus == '1610198')
                  <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Ke Jabatan Teknikal" onClick="doSeterusnya()"/>
                  <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
                  #end
                  #end
                  <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                  #end
                  #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
                  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
                  #end </td>
              </tr>
              #end
            </table>
          </div>
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Kategori Pemohon</td>
                <td>:</td>
                <td width="70%">$selectKategoriPemohon</td>
              </tr>
              #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
              #if ($idKategoriPemohon == '1')
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Nama</td>
                <td>:</td>
                <td><input name="txtNama" type="text" class="$inputTextClass" id="txtNama" value="$beanMaklumatPemohon.nama" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><em>MyID </em>/<em> MyCoid</em></td>
                <td>:</td>
                <td><input name="txtNoPendaftaran" type="text" class="$inputTextClass" id="txtNoPendaftaran" value="$beanMaklumatPemohon.noPendaftaran" maxlength="12" $readonly onblur="this.value=this.value.toUpperCase();"/>
                </td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Pekerjaan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtPekerjaan" rows="5" cols="50" class="$inputTextClass" id="txtPekerjaan" onblur="this.value=this.value.toUpperCase();" $readonly="$readonly">$beanMaklumatPemohon.pekerjaan</textarea>
                </td>
              </tr>
              #end
              #if ($idKategoriPemohon == '2')
              <tr>
                <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td>Nama Syarikat</td>
                <td>:</td>
                <td><input name="txtNama" type="text" class="$inputTextClass" id="txtNama" value="$beanMaklumatPemohon.nama" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Nama Pegawai</td>
                <td>:</td>
                <td><input name="txtNamaPegawai" type="text" class="$inputTextClass" id="txtNamaPegawai" value="$beanMaklumatPemohon.namaPegawai" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Pendaftaran</td>
                <td>:</td>
                <td><input name="txtNoPendaftaran" type="text" class="$inputTextClass" id="txtNoPendaftaran" value="$beanMaklumatPemohon.noPendaftaran" maxlength="12" $readonly onblur="this.value=this.value.toUpperCase();"/>
                </td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Jenis Perniagaan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtPekerjaan" rows="5" cols="50" class="$inputTextClass" id="txtPekerjaan" onblur="this.value=this.value.toUpperCase();" $readonly="$readonly">$beanMaklumatPemohon.pekerjaan</textarea>
                </td>
              </tr>
              #end
              <tr>
                <td>&nbsp;</td>
                <td>Alamat</td>
                <td>:</td>
                <td><input name="txtAlamat1" type="text" class="$inputTextClass" id="txtAlamat1" value="$beanMaklumatPemohon.alamat1" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><input name="txtAlamat2" type="text" class="$inputTextClass" id="txtAlamat2" value="$beanMaklumatPemohon.alamat2" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><input name="txtAlamat3" type="text" class="$inputTextClass" id="txtAlamat3" value="$beanMaklumatPemohon.alamat3" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Poskod</td>
                <td>:</td>
                <td><input name="txtPoskod" type="text" class="$inputTextClass" id="txtPoskod" value="$beanMaklumatPemohon.poskod" size="5" maxlength="5" $readonly onblur="validateLength(this.value);"/>
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Negeri</td>
                <td>:</td>
                <td>$selectNegeri</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Bandar</td>
                <td>:</td>
                <td>$selectBandar</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>E-mel</td>
                <td>:</td>
                <td><input name="txtEmel" type="text" class="$inputTextClass" id="txtEmel" value="$beanMaklumatPemohon.emel" maxlength="50" $readonly/>
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Tel</td>
                <td>:</td>
                <td><input name="txtNoTel" type="text" class="$inputTextClass" id="txtNoTel" onkeyup="validateNumber(this,this.value);" value="$beanMaklumatPemohon.noTel" maxlength="12" $readonly/></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Faks</td>
                <td>:</td>
                <td><input name="txtNoFaks" type="text" class="$inputTextClass" id="txtNoFaks" onkeyup="validateNumber(this,this.value);" value="$beanMaklumatPemohon.noFaks" maxlength="12" $readonly/>
                </td>
              </tr>
              #if ($idKategoriPemohon == '2')
              <tr>
                <td colspan="4" valign="bottom">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4" valign="bottom"><fieldset>
                  <legend>KEUPAYAAN KEWANGAN</legend>
                  <table width="100%" border="0" cellspacing="2" cellpadding="2">
                    <tr>
                      <td width="1%">&nbsp;</td>
                      <td width="28%">Modal Dibenarkan</td>
                      <td width="1%">:</td>
                      <td width="70%">RM
                        <input type="text" name="txtModalBenar" id="txtModalBenar" onblur="validateCurrency(this,this.value,'$beanMaklumatPemohon.modalBenar');" value="$beanMaklumatPemohon.modalBenar" $readonly class="$inputTextClass"/></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Modal Jelas</td>
                      <td>:</td>
                      <td>RM
                        <input type="text" name="txtModalJelas" id="txtModalJelas" onblur="validateCurrency(this,this.value,'$beanMaklumatPemohon.modalJelas');" value="$beanMaklumatPemohon.modalJelas" $readonly class="$inputTextClass"/></td>
                    </tr>
                  </table>
                  </fieldset></td>
              </tr>
              #end
              #end
              #if ($mode == 'update')
              <tr>
                <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
              </tr>
              #end
              <tr>
                <td colspan="4" valign="bottom">&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td> #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="doSimpanKemaskiniMaklumatPemohon()"/>
                  <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
                  #end
                  #if ($mode == 'view')
                  #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
                  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
                  #if($idStatus == '1610198')
                  <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Ke Jabatan Teknikal" onClick="doSeterusnya()"/>
                  <input type="button" name="cmdBatalPermohonan2" id="cmdBatalPermohonan2" value="Batal Permohonan" onclick="gotoBatalPermohonan()"/>
                  #end
                  #end
                  <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                  #end
                  #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
                  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
                  #end </td>
              </tr>
            </table>
          </div>
          <!-- Maklumat Penamatan Penyewaan -->
          <!--           <div class="TabbedPanelsContent"> -->
          <!--           	   <table width="100%" border="0" cellspacing="2" cellpadding="2"> -->
          <!-- 	          	   <tr> -->
          <!-- 				    <td> #parse("app/php2/frmPYWMaklumatPenamatanPenyewaanDetail.jsp") </td> -->
          <!-- 				  </tr> -->
          <!-- 			  </table> -->
          <!--           </div> -->
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td> #parse("app/php2/frmPYWDaftarManualSenaraiSemak.jsp") </td>
              </tr>
            </table>
          </div>
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td> #parse("app/php2/frmPYWMaklumatLampiran.jsp") </td>
              </tr>
            </table>
          </div>
        </div>
      </div></td>
  </tr>
  #end
</table>
#end
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWKulitFail('$idFail')"> Kulit Fail </a></td>
  </tr>
  #if ($!idHTPHakmilik != '')
  #if ($statusRizab == 'MILIK')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakMaklumatHakmilik('$idHTPHakmilik')"> Maklumat Tanah </a></td>
  </tr>
  #elseif ($statusRizab == 'RIZAB')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakMaklumatRizab('$idHTPHakmilik')"> Maklumat Tanah </a></td>
  </tr>
  #end
  #end
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWSuratAkuanTerima('$idFail')"> Surat Akuan Terima </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWSuratTolakAdaPenyewa('$idFail')"> Surat Tolak Ringkas Kerana Ada Penyewa </a></td>
  </tr>
  #if ($!selectedTabUpper == '4')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWNotisSenaraiSemakTidakLengkap('$idFail', '$idKategoriPemohon')"> Surat Pemberitahuan Senarai Semak Tidak Lengkap </a></td>
  </tr>
  #end
</table>
</fieldset>
<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function goBatal() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
// function doSeterusnyaTamatSewa() {
// 	if ( !window.confirm("Adakah Anda Pasti ?") ){
// 		return;
// 	}
// 	document.${formName}.hitButton.value = "doSeterusnyaTamatSewa";
// 	doAjaxCall${formName}("");
// }
function goKemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doSimpanTamatSewa() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.hitButton.value = "doSimpanTamatSewa";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
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
function doChangeTujuan() {
	doAjaxCall${formName}("doChangeTujuan");
}
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
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
function doKemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatSewa(idLuas) {
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
		alert('Tarikh Surat/Borang tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat/Borang tidak boleh melebihi dari Tarikh Terima.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if(document.${formName}.tarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.tarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat/Borang.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuan.value == ""){
		alert('Sila masukkan tujuan.');
  		document.${formName}.txtTujuan.focus(); 
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
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatSewa";
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
function doSimpanKemaskiniMaklumatPemohon() {
	if(document.${formName}.socKategoriPemohon.value == ""){
		alert('Sila pilih Jenis Kategori Pemohon.');
  		document.${formName}.socKategoriPemohon.focus(); 
		return; 
	}
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukan Nama.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}			
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPemohon";
	document.${formName}.submit();
}
function doSimpanKemaskiniSenaraiSemak() {
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniSenaraiSemak";
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
function cetakPYWKulitFail(idFail) {
	var url = "../servlet/ekptg.report.php2.PYWKulitFail?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWSuratAkuanTerima(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratAkuanTerima";
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
function cetakPYWSuratTolakAdaPenyewa(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSuratTolakAdaPenyewa";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWNotisSenaraiSemakTidakLengkap(idFail, idKategoriPemohon) {
	if (idKategoriPemohon == '1') {
		var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSenaraiSemakIndividu";
	} else {
		var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakLaporanView?idFail="+idFail+"&report=PYWSenaraiSemakSyarikat";
	}
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function doDaftarHakmilik(idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupSenaraiTanahBerkaitanView?idPermohonan="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function doDaftarBorangK(idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupSenaraiBorangKBerkaitanView?idPermohonan="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanahBorangK() {
	doAjaxCall${formName}("");
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
function gotoSenaraiFailKeseluruhan() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailKeseluruhanView";
	document.${formName}.submit();
}

function doSetujuTolakRingkas(idPermohonan) {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "doSetujuTolakRingkas";
	doAjaxCall${formName}("");
}
function doTangguhMaklumatTambahan(idPermohonan) {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "doTangguhMaklumatTambahan";
	doAjaxCall${formName}("");
}
function doSemakStatusTanah(noLotTanah) {
	var url = "../x/${securityToken}/ekptg.view.utils.FrmPopupMaklumatTanahDanLaporanTanahView?noLot="+noLotTanah;	//&idFail="+document.${formName}.idFail.value+"&userRole="+document.${formName}.userRole.value;
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function doSyorTolakRingkas(idPermohonan) {	
	document.${formName}.hitButton.value = "doSyorTolakRingkas";
	document.${formName}.modePopup.value = "openSyorTolakRingkas";
	doAjaxCall${formName}("");
}

function doSimpanSyorTolakRingkas() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "doSimpanSyorTolakRingkas";
	doAjaxCall${formName}("");
}
function doCetakSyorTolakRingkas(idFail) {
	
	var reportfile = "PYWSuratTolakRingkas";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "ID_FAIL="+idFail;
	
	printReport(reportfile,params);
}

function doPengesahanPengarahNegeri(idPermohonan) {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "doPengesahanPengarahNegeri";
	doAjaxCall${formName}("");
}

function doKemaskiniSyorTolakRingkas(){
	document.getElementById('sebabTolakRingkas').removeAttribute('class');
	document.getElementById('sebabTolakRingkas').removeAttribute('disabled');
	document.getElementById('sebabTolakRingkas').removeAttribute('readonly');
	document.getElementById('tarikhSuratTolakRingkas').removeAttribute('class');
	document.getElementById('tarikhSuratTolakRingkas').removeAttribute('disabled');
	document.getElementById('tarikhSuratTolakRingkas').removeAttribute('readonly');
	document.getElementById('catatanMaklumatTambahan').removeAttribute('class');
	document.getElementById('catatanMaklumatTambahan').removeAttribute('disabled');
	document.getElementById('catatanMaklumatTambahan').removeAttribute('readonly');
	
	document.getElementById('cmdKemaskiniSyorTolakRingkas').style.display="none";
	document.getElementById('cmdCetakSyorTolakRingkas').style.display="none";
	document.getElementById('cmdPengesahanPengarahNegeri').style.display="none";
	
	document.getElementById('cmdSimpanSyorTolakRingkas').style.display="block";
}
</script>
<!-- MAKLUMAT LAMPIRAN -->
<script>
function daftarLampiran() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPYWMaklumatPermohonanView";
	document.${formName}.method="POST";
	document.${formName}.mode.value = "view";	
	document.${formName}.flagPopup.value = "openPopupLampiran";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}
</script>
