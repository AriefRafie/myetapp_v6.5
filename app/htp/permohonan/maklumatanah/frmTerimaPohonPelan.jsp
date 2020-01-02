#set ($inputstyle = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )
#if($pageLakaranPelan=='1')

<fieldset style="width:60%; "><legend>Pelan / Lakaran Tanah</legend>
<table width="100%" height="496" border="0">
  <tr>
    <td width="100%" valign="top"><fieldset><legend>Maklumat Charting</legend>
        #foreach($p in $PL)
            #set($a = $p.size())
            #set($b = $p.ulasan)
            #set($c = $p.bayarproses)
            #set($pas = $p.flagcharting)
        #end
            <table width="100%" border="0">
              <tr>
                <td width="29%">No.Pelan</td>
                <td width="1%">:</td>
                <td  width="70%"><input type="text" name="NoPelan" id="NoPelan" value="$nopelan"  $selectstyle $inputstyleread/></td>
              </tr>
              <tr>
                <td>No. Syit Piawai</td>
                <td>:</td>
                <td><input type="text" name="NoSyitPiawai" id="NoSyitPiawai" value="$nosyit"  $selectstyle $inputstyleread/></td>
              </tr>
              <tr>
                <td>Jumlah Bayaran Proses (RM)</td>
                <td>&nbsp;</td>
                <td><input type="text" name="JumBayaranPelan" id="JumBayaranPelan" value="$c" onblur="validateCurrency(this,this.value,'')"$selectstyle $inputstyleread/></td>
              </tr>
              <tr>
                <td>Charting</td>
                <td>:</td>
                <td> #if( $pas == 1)
                  <input checked="checked" type="radio" name="RadioGroup1" value="1" id="RadioGroup1_0" $selectstyle $inputstyleread/>
                  YA
                  <input type="radio" name="RadioGroup1" value="2" id="RadioGroup1_1" $selectstyle $inputstyleread/>
                  TIDAK
                  #else
                  <input checked="checked" type="radio" name="RadioGroup1" value="1" id="RadioGroup1_0" $selectstyle $inputstyleread/>
                  YA
                  <input checked="checked" type="radio" name="RadioGroup1" value="2" id="RadioGroup1_1" $selectstyle $inputstyleread />
                  TIDAK
                  #end </td>
              </tr>
              <tr>
                <td colspan="3"><p>Ulasan :</p>
                  <label>
                    <textarea name="textarea2" id="textarea2" cols="45" rows="5" $selectstyle $inputstyleread>$b</textarea>
                  </label></td>
              </tr>
              <tr>
                <td colspan="3"><input class="stylobutton" type="button" name="Kembali" id="Kembali" value="Kembali" onclick="KembaliAsas(0,'kemaskinipermohonan','MakAsasTanah',0)" /></td>
              </tr>
            </table>
    </fieldset></td>
    <td width="50%" style="display:none" valign="top">
    
    <fieldset    ><legend>Gambar Rajah Pelan</legend>
      <table width="100%" border="0">
        <tr>
          <td width="53%">Keterangan Ringkas Imej :
            <label>
              <textarea name="txtPerihalImej" id="txtPerihalImej" cols="45" rows="5" onkeyup="this.value=this.value.toUpperCase();"></textarea>
            </label></td>
          <td colspan="2" rowspan="2"><img src="../servlet/ekptg.view.htp.FrmRekodDisplayImej?id=$idgambar" alt="Imej Pelan" border="1" width="250" height="250"/></td>
        </tr>
        <tr>
          <td><input id="fileupload" name="fileupload" type="file" size="40" $readonly="$readonly"  class="$disabled" /></td>
        </tr>
        <tr>
          <td colspan="3"><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik imej adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz imej yang lebih kecil.</td>
          </tr>
        <tr>
          <td><input class="stylobutton"type="button" name="btnSave" id="btnSave" value="Muat Naik Gambar"  onclick="simpanDetailImej($idhakmilikurusan)" /></td>
          <td width="45%">&nbsp;</td>
          <td width="2%">&nbsp;</td>
        </tr>
        <tr style="display:none">
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
    </fieldset></td>
  </tr>
</table>
</legend>
<input type="hidden" value="$idfail" name="idfail"/>
</p>
#else
<table width="100%" border="0">
  #if($idpermohonanlokasi == 0)
  <tr>
    <td class="w">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #else
  <tr>
    <td class="w">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><input class="stylobutton" type="button" value="Tambah" onclick="SimpanLakaranPelan($idhakmilikurusan)" /></td>
    <td>&nbsp;</td>
  </tr>
  #end
</table>
#end