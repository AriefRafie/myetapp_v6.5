<!--frmTerimaPohonMaklumatAsasTanahKemaskini.jsp-->
<!-- CL-02-005 -->
<style type="text/css">
<!--
.star {color: #F00;
}
-->
</style>
#if($pageMode == 0)
#set ($inputstyle = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )
#else
#set ($inputstyle = "" )
#set ($inputstyleread = "" )
#set ($selectstyle = "" )
#end
<fieldset>
<legend>Kemaskini Maklumat Asas Tanah 2</legend>
<table width="100%" border="0">
  <tr>
    <td width="18%"><span class="star" style="font-weight: bold"><strong>*</strong></span><span style="font-weight: bold"><strong>Negeri :</strong></span></td>
    <td width="23%" colspan="-2">$infoNegeri</td>
    <td width="2%" colspan="-2">&nbsp;</td>
    <td width="9%" colspan="-2"><span class="star">*</span>No.Syit</td>
    <td width="48%"><input type="text" name="noSyit" id="noSyit" onkeyup="validateNumber(this,this.value);" value="$infonosyit" $selectstyle $inputstyleread/></td>
    </tr>
  <tr>
    <td><span class="star">*</span>Daerah:</td>
    <td colspan="-2">$infoDaerah</td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2"><span class="star">*</span>No. Pelan</td>
    <td><input type="text" name="noPelan" id="noPelan" onkeyup="this.value=this.value.toUpperCase();" value="$infoPelan" $selectstyle $inputstyleread/></td>
    </tr>
  <tr>
    <td><span class="star">*</span>Mukim/Bandar/Pekan :</td>
    <td colspan="-2">$infoMukim</td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2"><span class="star">*</span>Kod Luas</td>
    <td>$infoIdLot</td>
    </tr>
  <tr>
    <td><span class="star">*</span>Kod Lot :</td>
    <td colspan="-2">$infoLot</td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2"><span class="star">*</span>Luas</td>
    <td><input type="text" name="Luas" id="Luas" onkeyup="validateNumber(this,this.value);" value="$infoLuas" $selectstyle $inputstyleread/></td>
    </tr>
  <tr>
    <td><span class="star">*</span>Nombor Lot :</td>
    <td colspan="-2"><input type="text" name="txtNoLot" id="txtNoLot" onkeyup="this.value=this.value.toUpperCase();" value="$infonolot" $selectstyle $inputstyleread/></td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2"><span class="star">*</span>Lokasi</td>
    <td rowspan="3">
     <textarea name="Lokasi" id="Lokasi" cols="45" rows="5" onkeyup="this.value=this.value.toUpperCase();" $selectstyle $inputstyleread>$infoLokasi</textarea>     <!--<input type="text" name="LuasH" id="LuasH" />
      <span class="star">*</span>Luas(H)--></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2">&nbsp;</td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2">&nbsp;</td>
    </tr>
  <tr>
    <td colspan="5"><label>
      <input type="button" class="stylobutton"  value="Simpan" onclick="UpdateMaklumatAsasTanah(0,2,'kemaskinipermohonan','MakAsasTanah','$mat.idhakmilikurusan')" />
     <input class="stylobutton" type="button" name="Kembali" id="Kembali" value="Kembali" onclick="KembaliAsas(0,'kemaskinipermohonan','MakAsasTanah',0)" />
    </label></td>
  </tr>
</table>
</fieldset>
<input type="hidden" name="idhakmilikurusan" value="$idhakmilikurusan" />

