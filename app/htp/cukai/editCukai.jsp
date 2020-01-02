#if($mode == 'kemaskini')
#set ($inputstyle = "class=disabled" )
#set ($inputstyle2 = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )
#elseif($mode == 'simpan')
#set ($inputstyle2 = "class=disabled" )
#set ($inputstyle = "" )
#end
<fieldset>
<legend>KEMASKINI MAKLUMAT MANUAL</legend>
<table width="100%" border="0">
  <tr>
    <td width="50%"><fieldset><legend>MAKLUMAT TANAH</legend>
    
        <table width="100%" border="0">
          <tr>
            <td width="1%"><font color="red">*</font></td>
            <td width="28%">Negeri</td>
            <td width="1*">:</td>
            <td width="70%">$manualNegeri</td>
          </tr>
          <tr>
            <td><font color="red">*</font></td>
            <td>Daerah</td>
            <td>:</td>
            <td>$manualDaerah</td>
          </tr>
          <tr>
            <td><font color="red">*</font></td>
            <td>Mukim/Pekan/Bandar</td>
            <td>:</td>
            <td>$manualMukim</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Jenis HM</td>
            <td>:</td>
            <td>$JenisHakmilik</td>
          </tr>
          <tr>
            <td><font color="red">*</font></td>
            <td>Jenis/No HM</td>
            <td>:</td>
            <td><input name="txtNoHakmilik" type="text" id="txtNoHakmilik" size="46" onkeyup="this.value=this.value.toUpperCase();" value="$cukai.noHakmilik" $inputstyle2>
              </td>
          </tr>
          <tr>
            <td><font color="red">*</font></td>
            <td>Jenis PT/Lot</td>
            <td>:</td>
            <td>$jenisLot
              </td>
          </tr>
          <tr>
            <td height="30">&nbsp;</td>
            <td>NO PT/Lot</td>
            <td>:</td>
            <td><input name="txtNoLot" type="text" id="txtNoLot" size="47" onkeyup="this.value=this.value.toUpperCase();" value="$!cukai.noLot"/ $inputstyle2></td>
          </tr>
          <tr>
            <td height="30" valign="top"><font color="red">*</font></td>
            <td valign="top">Kegunaan Tanah</td>
            <td>:</td>
            <td><textarea name="txtKegunaanTanah" id="txtKegunaanTanah" cols="45" rows="5" onkeyup="this.value=this.value.toUpperCase();" $inputstyle2>$!cukai.kegunaanTanah</textarea></td>
          </tr>
        </table>
    </fieldset></td>
    <td width="50%"><fieldset><legend>BAYARAN</legend>
    
        <table width="609" height="259" border="0">
          <tr>
            <td width="8"><font color="red">*</font></td>
            <td width="143">Cukai Semasa</td>
            <td width="34">: RM</td>
            <td width="406">
                <input name="txtTahunan" type="text" id="txtTahunan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$UTIL.format2Decimal($!cukai.cukaiKenaBayar)" $inputstyle>
              </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Tunggakan</td>
            <td>: RM</td>
            <td>
              <input name="txtTungakan" type="text" id="txtTungakan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$UTIL.format2Decimal($!cukai.tunggakkan)" $inputstyle>
              </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Denda Lewat</td>
            <td>: RM</td>
            <td>
                <input name="txtDenda" type="text" id="txtDenda" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$UTIL.format2Decimal($!cukai.denda)" $inputstyle>
              </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Cukai Lain</td>
            <td>: RM</td>
            <td><input name="txtCukaiLain" type="text" id="txtCukaiLain" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$UTIL.format2Decimal($!cukai.cukailain)" $inputstyle></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Lebihan [ - ]</td>
            <td>: RM</td>
            <td><input name="txtLebihan" type="text" id="txtLebihan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$UTIL.format2Decimal($!cukai.lebihan)" $inputstyle></td>
          </tr>
          <tr>
            <td><font color="red">*</font></td>
            <td>Cukai Kena Bayar</td>
            <td>: RM</td>
            <td>
                <input name="txtJumBayaran" type="text" id="txtJumBayaran" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$UTIL.format2Decimal($!cukai.cukaiPerluBayar)" $inputstyle2 readonly="readonly">
             </td>
          </tr>
          <tr>
            <td height="30">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td height="30">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
    </fieldset>
    </td>
</tr>
<tr>
	<td colspan="2" align="center">
    #if ($mode == "kemaskini")
   		<input class="stylobutton" name="Kemaskini" type="button" value="Kemaskini" onclick="kemaskiniManual()"/>
    #elseif ($mode == "simpan")
		<input class="stylobutton" name="Simpan" type="button" value="Simpan" onclick="updateManual()"/>
    #end
 		<input class="stylobutton" name="Batal" type="button" value="Batal" onclick="Kembali()"/>
	
	</td>

</tr>
</table>

</fieldset>
<input type="hidden" name="idCukai" value="$!cukai.idCukaiTemp"><input type="hidden" value="$Tahun" name="tahun"/>
<script>

</script>