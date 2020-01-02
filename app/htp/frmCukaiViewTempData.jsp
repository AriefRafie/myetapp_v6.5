#if( $pagemode == '0' )

	#set ($inputstyleread = "readonly class=disabled" )

#elseif( $pagemode == '1' )

	#set ($inputstyleread = "" )

#end

<fieldset>
<legend>BORANG MAKLUMAT MANUAL</legend>
#foreach($list in $listtemp)

<table width="100%" border="0">
  <tr>
    <td width="50%"><fieldset>
        <legend>MAKLUMAT TANAH $list.tahun</legend>
    
        <table width="100%" border="0">
          <tr>
            <td width="1%">&nbsp;</td>
            <td width="28%">Negeri</td>
            <td width="1*">:</td>
            <td width="70%">$list.NAMA_NEGERI</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Daerah</td>
            <td>:</td>
            <td>$list.NAMA_DAERAH</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Mukim/Pekan/Bandar</td>
            <td>:</td>
            <td>$list.NAMA_MUKIM</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Jenis/No HM</td>
            <td>:</td>
            <td>$list.nohakmilikupload</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>NO PT/Lot</td>
            <td>:</td>
            <td>$list.nolotupload</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td rowspan="3">Kegunaan Tanah</td>
            <td>:</td>
            <td rowspan="3"><textarea name="txtKegunaanTanah" id="txtKegunaanTanah" cols="45" rows="5" onkeyup="this.value=this.value.toUpperCase();" $inputstyleread>$list.KEGUNAAN_TANAH</textarea></td>
          </tr>
          <tr>
            <td height="30">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td height="30">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
    </fieldset></td>
    <td width="50%"><fieldset><legend>Bayaran</legend>
    
        <table width="609" height="230" border="0">
          <tr>
            <td width="8"><font color="red">*</font></td>
            <td width="143">Tahunan</td>
            <td width="34">: RM</td>
            <td width="406">
                <input name="txtTahunan" type="text" id="txtTahunan"   value="$list.CUKAI_KENA_BAYAR" size="11" $inputstyleread>
              </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Cukai Lain</td>
            <td>: RM</td>
            <td>
                <input name="txtCukaiLain" type="text" id="txtCukaiLain" size="11" $inputstyleread>
              </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Tunggakan</td>
            <td>: RM</td>
            <td>
                <input name="txtTungakan" type="text" id="txtTungakan"   value="$list.TUNGGAKAN" size="11" $inputstyleread>
             </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Denda Lewat</td>
            <td>: RM</td>
            <td>
                <input name="txtDenda" type="text" id="txtDenda"   value="$list.DENDA" size="11" $inputstyleread>
              </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Lebihan</td>
            <td>: RM</td>
            <td>
                <input name="txtLebihan" type="text" id="txtLebihan"   value="$list.PENGECUALIAN" size="11" $inputstyleread>
              </td>
          </tr>
          <tr>
            <td><font color="red">*</font></td>
            <td>Cukai Kena Bayar</td>
            <td>: RM</td>
            <td>
                <input name="txtJumBayaran" type="text" id="txtJumBayaran"  value="$list.CUKAI_PERLU_BAYAR" size="11"$inputstyleread >
             </td>
          </tr>
        </table>
        
    </fieldset></td>
  </tr>
  <tr>
    <td>
    <input class="stylobutton" type="button" value="Kemaskini" onClick="KemaskiniTemp()">
    <input class="stylobutton" type="button" value="Kembali" onClick="SenaraiFailUpload()"> </td>
    <td>&nbsp;</td>
  </tr>
  
</table>
#end