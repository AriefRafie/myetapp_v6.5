<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT TANAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatTanahGanti in $BeanMaklumatTanahGanti)
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Pegangan Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtPeganganHakmilikTG" id="txtPeganganHakmilikTG" value="$beanMaklumatTanahGanti.peganganHakmilikTG" onblur="doChangePeganganHakmilikGanti();" $readonlyPopup class="$inputTextClassPopup"/>
            #if ($modePopup != 'view')
            <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onclick="pilihTanahGanti('3','$idKementerianPmhn','$idAgensiPmhn')"/>
            #end
            <input type="hidden" name="idHakmilikAgensiTG" id="idHakmilikAgensiTG" value="$beanMaklumatTanahGanti.idHakmilikAgensiTG" />
            <input type="hidden" name="idHakmilikAgensiTGOld" id="idHakmilikAgensiTGOld" value="$idHakmilikAgensiTGOld" />
            <span class="style1">$errorPeganganHakmilik</span> </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanahGanti.noLotTG</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanahGanti.luasLotTG
            <input type="hidden" name="txtidLuasAsalTG" id="txtidLuasAsalTG" value="$beanMaklumatTanahGanti.idLuasAsalTG"/>
            <input type="hidden" name="txtLuasAsalTG" id="txtLuasAsalTG" value="$beanMaklumatTanahGanti.luasAsalTG"/>          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Hakmilik</td>
          <td>:</td>
          <td>$beanMaklumatTanahGanti.noHakmilikTG</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Warta</td>
          <td>:</td>
          <td>$beanMaklumatTanahGanti.noWartaTG</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Warta</td>
          <td>:</td>
          <td>$beanMaklumatTanahGanti.tarikhWartaTG</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Mukim</td>
          <td>:</td>
          <td>$beanMaklumatTanahGanti.mukimTG</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Daerah</td>
          <td>:</td>
          <td>$beanMaklumatTanahGanti.daerahTG</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatTanahGanti.negeriTG</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$beanMaklumatTanahGanti.kementerianTG</td>
        </tr>
        <tr>
          <td height="31">&nbsp;</td>
          <td>Agensi</td>
          <td>:</td>
          <td>$beanMaklumatTanahGanti.agensiTG</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kegunaan Tanah</td>
          <td>:</td>
          <td>$beanMaklumatTanahGanti.kegunaanTanahTG</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($idHakmilikAgensiTG != '')
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT LUAS DITUKARGANTI</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatLuasTG in $BeanMaklumatLuasTG)
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Luas Kegunaan</td>
          <td width="1%">:</td>
          <td width="70%">$selectLuasKegunaanTG</td>
        </tr>
        #if ($idLuasKegunaanTG == '2')
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Unit Luas</td>
          <td>:</td>
          <td>#parse("app/php2/unit_luas_popup.jsp") </td>
        </tr>
        #if ($idLuasTG != '99999' && $idLuasTG != '')
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Luas Ditukarganti</td>
          <td>:</td>
          <td> #if ($idLuasTG == '0' || $idLuasTG == '1' || $idLuasTG == '2' || $idLuasTG == '3' || $idLuasTG == '5' || $idLuasTG == '6' || $idLuasTG == '9')
            <input type="text" name="txtLuasTG1" id="txtLuasTG1" value="$beanMaklumatLuasTG.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuasTG('$idLuasTG')" size="6" $readonlyPopup class="$inputTextClassPopup"/ >
            #elseif ($idLuasTG == '7')
            <input type="text" name="txtLuasTG1" id="txtLuasTG1" value="$beanMaklumatLuasTG.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuasTG('$idLuasTG')"/>
            <input type="text" name="txtLuasTG2" id="txtLuasTG2" value="$beanMaklumatLuasTG.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuasTG('$idLuasTG')" size="6"/ $readonlyPopup class="$inputTextClassPopup">
            #elseif ($idLuasTG == '8' || $idLuasTG == '4')
            <input type="text" name="txtLuasTG1" id="txtLuasTG1" value="$beanMaklumatLuasTG.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuasTG('$idLuasTG')"/>
            <input type="text" name="txtLuasTG2" id="txtLuasTG2" value="$beanMaklumatLuasTG.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuasTG('$idLuasTG')"/>
            <input type="text" name="txtLuasTG3" id="txtLuasTG3" value="$beanMaklumatLuasTG.luas3" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuasTG('$idLuasTG')" size="6" $readonlyPopup class="$inputTextClassPopup"/>
            #end </td>
        </tr>
        #end
        #end
        <tr>
          <td>&nbsp;</td>
          <td>Luas Bersamaan</td>
          <td>:</td>
          <td><input type="text" name="txtLuasBersamaanTG" id="txtLuasBersamaanTG" value="$beanMaklumatLuasTG.luasBersamaan"  style="text-align:right" readonly="readonly" class="disabled"/>
            HEKTAR</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Baki Luas</td>
          <td>:</td>
          <td><input type="text" name="txtBakiLuasTG" id="txtBakiLuasTG" value="$beanMaklumatLuasTG.luasBaki" readonly="readonly" class="disabled" style="text-align:right"/>
            HEKTAR</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%">#if ($modePopup == 'new')
      <input name="cmdSimpan" type="button" value="Simpan" onclick="doSimpanTanahGanti()"/>
      <input name="cmdBatal" type="button" value="Batal" onclick="doBatalTanahGanti()"/>
      #end
      #if ($modePopup == 'view')
      #if ($userRole == 'online_kjp' && $layerKJP  == $flagLayerKJP )
      <input name="cmdKemaskini" type="button" value="Kemaskini" onclick="doKemaskiniTanahGanti()"/>
      <input name="cmdHapus" type="button" value="Hapus" onclick="doHapusTanahGanti()"/>
      #end
      <input name="cmdKembali" type="button" value="Kembali" onclick="doBatalTanahGanti()"/>
      
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpan" type="button" value="Simpan" onclick="doSimpanKemaskiniTanahGanti()"/>
      <input name="cmdBatal" type="button" value="Batal" onclick="doBatalKemaskiniTanahGanti()"/>
      #end</td>
  </tr>
</table>


