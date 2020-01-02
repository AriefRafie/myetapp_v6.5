#set($saizTxtSyaratTG="1000")
#set($saizTxtSekatanTG="1000")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #set ($beanMaklumatTanahGanti = "")
  #foreach ($beanMaklumatTanahGanti in $BeanMaklumatTanahGanti)
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Pegangan Hakmilik</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtPeganganHakmilikTG" type="text" class="$inputTextClassPopup"id="txtPeganganHakmilikTG" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatTanahGanti.peganganHakmilik" maxlength="30" $readonlyPopup /></td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Jenis Hakmilik</td>
    <td>:</td>
    <td>$selectJenisHakmilikTG</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>No. Hakmilik</td>
    <td>:</td>
    <td><input name="txtNoHakmilikTG" type="text" class="$inputTextClassPopup" id="txtNoHakmilikTG" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatTanahGanti.noHakmilik" maxlength="21" $readonlyPopup/></td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Jenis Lot</td>
    <td>:</td>
    <td>$selectLotTG</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>No. Lot</td>
    <td>:</td>
    <td><input name="txtNoLotTG" type="text" class="$inputTextClassPopup" id="txtNoLotTG" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatTanahGanti.noLot" maxlength="20" $readonlyPopup/></td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Unit Luas</td>
    <td>:</td>
    <td><select name="socLuasTG" style="width:200px;" 
$readonlyPopup class="$disabledPopup" $disabledPopup 
onchange="javascript:doChangeLuas(this.value)">
        
#set ($listUnitLuas = ["SILA PILIH",
		       "KM - KILOMETER PERSEGI",
		       "H - HEKTAR",
		       "M - METER PERSEGI",
		       "E - EKAR,ROOD,POLE",
		       "K - KAKI PERSEGI",
		       "P - EKAR PERPULUHAN",
		       "D - EKAR,DEPA",
		       "R - RELONG,JEMBA,KAKI PERSEGI",
		       "BN - BATU NAUTIKA"]
      )
#set( $counter = 0 )
#foreach ($i in $listUnitLuas)

#if ($idLuasTG == $counter) 
	
        <option selected value="$counter">$i</option>
        
#else
	
        <option value="$counter">$i</option>
        
#end

#set ($counter = $counter+1)

#end

      </select>    </td>
  </tr>
  #if ($idLuasTG != '99999' && $idLuasTG != '')
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Luas Mohon</td>
    <td>:</td>
    <td> #if ($idLuasTG == '0' || $idLuasTG == '1' || $idLuasTG == '2' || $idLuasTG == '3' || $idLuasTG == '5' || $idLuasTG == '6' || $idLuasTG == '9')
      <input type="text" name="txtLuas1TG" id="txtLuas1TG" value="$beanMaklumatTanahGanti.luas1" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuasTG('$idLuasTG')" size="6" $readonlyPopup class="$inputTextClassPopup"/ >
      #elseif ($idLuasTG == '7')
      <input type="text" name="txtLuas1TG" id="txtLuas1TG" value="$beanMaklumatTanahGanti.luas1" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuasTG('$idLuasTG')"/>
      <input type="text" name="txtLuas2TG" id="txtLuas2TG" value="$beanMaklumatTanahGanti.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuasTG('$idLuasTG')" size="6"/ $readonlyPopup class="$inputTextClassPopup">
      #elseif ($idLuasTG == '8' || $idLuasTG == '4')
      <input type="text" name="txtLuas1TG" id="txtLuas1TG" value="$beanMaklumatTanahGanti.luas1" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuasTG('$idLuasTG')"/>
      <input type="text" name="txtLuas2TG" id="txtLuas2TG" value="$beanMaklumatTanahGanti.luas2" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuasTG('$idLuasTG')"/>
      <input type="text" name="txtLuas3TG" id="txtLuas3TG" value="$beanMaklumatTanahGanti.luas3" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuasTG('$idLuasTG')" size="6" $readonlyPopup class="$inputTextClassPopup"/>    </td>
  </tr>
  #end
  #end
  <tr>
    <td>&nbsp;</td>
    <td>Luas Bersamaan</td>
    <td>:</td>
    <td><input type="text" name="txtLuasBersamaanTG" id="txtLuasBersamaanTG" value="$beanMaklumatTanahGanti.luasBersamaan" readonly="readonly" class="disabled"/>
      HEKTAR</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Negeri</td>
    <td>:</td>
    <td>$selectNegeriTG</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Daerah</td>
    <td>:</td>
    <td>$selectDaerahTG</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Bandar/Pekan/Mukim</td>
    <td>:</td>
    <td>$selectMukimTG</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Syit</td>
    <td>:</td>
    <td><input name="txtNoSyitTG" type="text" class="$inputTextClassPopup" id="txtNoSyitTG" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatTanahGanti.noSyit" maxlength="20" $readonlyPopup/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Pelan</td>
    <td>:</td>
    <td><input name="txtNoPelanTG" type="text" class="$inputTextClassPopup" id="txtNoPelanTG" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatTanahGanti.noPelan" maxlength="20" $readonlyPopup/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Kategori</td>
    <td>:</td>
    <td>$selectKategoriTG</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>SubKategori</td>
    <td>:</td>
    <td>$selectSubKategoriTG</td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Syarat Nyata</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtSyaratTG" id="txtSyaratTG" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup" onKeyUp="textCounter(this.form.txtSyaratTG,this.form.remLen5,$!saizTxtSyaratTG);" onKeyDown="textCounter(this.form.txtSyaratTG,this.form.remLen5,$!saizTxtSyaratTG);" onblur="this.value=this.value.toUpperCase();">$beanMaklumatTanahGanti.syarat</textarea></td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">Baki Aksara :&nbsp;
      <input type="text" readonly="readonly" class="disabled" name="remLen5" size="3" maxlength="3" value="$!saizTxtSyaratTG" /></td>
  </tr>
  #end
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Sekatan Kepentingan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtSekatanTG" id="txtSekatanTG" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup" onKeyUp="textCounter(this.form.txtSekatanTG,this.form.remLen6,$!saizTxtSekatanTG);" onKeyDown="textCounter(this.form.txtSekatanTG,this.form.remLen6,$!saizTxtSekatanTG);" onblur="this.value=this.value.toUpperCase();">$beanMaklumatTanahGanti.sekatan</textarea></td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>Baki Aksara :&nbsp;
      <input type="text" readonly="readonly" class="disabled" name="remLen6" size="3" maxlength="3" value="$!saizTxtSekatanTG" /></td>
  </tr>
  #end
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Kegunaan Tanah</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtKegunaanTanahTG" id="txtKegunaanTanahTG" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();">$beanMaklumatTanahGanti.kegunaanTanah</textarea></td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($modePopup == 'new')
      <input name="cmdSimpan" type="button" value="Simpan" onClick="doSimpanTanahGanti()" />
      <input name="cmdBatal" type="button" value="Batal" onClick="doBatalTanahGanti()" />
      #end
      #if ($modePopup == 'view')
      <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="doKemaskiniTanahGanti()" />
      <input name="cmdHapus" type="button" value="Hapus" onClick="doHapusTanahGanti()" />
      <input name="cmdBatal" type="button" value="Kembali" onClick="doBatalTanahGanti()" />
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="doSimpanKemaskiniTanahGanti()" />
      <input name="txtBatal" type="button" value="Batal" onClick="doBatalKemaskiniTanahGanti()" />
      #end </td>
  </tr>
  #end
</table>
