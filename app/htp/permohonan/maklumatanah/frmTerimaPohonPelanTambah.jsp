<style type="text/css">
<!--
.p {
	color: #F00;
}
#Simpan {
	text-align: center;
}
-->
</style>
#set ($inputstyle = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )
<fieldset><legend>Pelan / Lakaran Tanah</legend>
  <table width="100%" height="125" border="0">
  <tr>
    <td>
      <fieldset><legend>Maklumat Charting</legend><table width="50%" border="0" align="center">
        <tr>
          <td width="35%">&nbsp;</td>
          <td width="65%">&nbsp;</td>
          </tr>
        <tr>
          <td>No.Pelan</td>
          <td><input type="text" name="NoPelan" id="NoPelan" value="$!nosyit" $selectstyle $inputstyleread/></td>
          </tr>
        <tr>
          <td>No. Syit Piawai</td>
          <td><input type="text" name="NoSyitPiawai" id="NoSyitPiawai" value="$!nopelan" $selectstyle $inputstyleread/></td>
          </tr>
        <tr>
          <td>Jumlah Bayaran Proses (RM)</td>
          <td><input type="text" name="JumBayaranPelan" id="JumBayaranPelan"  onblur="validateCurrency(this,this.value,'')"/></td>
          </tr>
        <tr>
          <td>Charting</td>
          <td>
            <input type="radio" name="RadioGroup1" value="1" id="RadioGroup1_0" />YA
            <input type="radio" name="RadioGroup1" value="2" id="RadioGroup1_1" />TIDAK</td>
          </tr>
        <tr>
          <td>Ulasan :</td>
          <td rowspan="3"><textarea name="ulasan" id="ulasan" cols="45" rows="5" onkeyup="this.value=this.value.toUpperCase();"></textarea></td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          </tr>
      </table></fieldset></td>
    </tr>
  <tr>
    <td><p>
      <span id="Simpan">
        <input class="stylobutton" type="button" name="Simpan" id="Simpan" value="Simpan" onclick="SimpanPohonPelan($idpermohonanlokasi)"/>
        <input class="stylobutton" type="button" name="Kembali" id="Kembali" value="Kembali" onclick="KembaliAsas(0,'kemaskinipermohonan','MakAsasTanah',0)" />
        </span></p>
      <p>&nbsp; </p></td>
    </tr>
</table></fieldset>
<p>&nbsp; </p>


<input type="hidden" value="$idfail" name="idfail"/>   <input type="hidden" value="$idhakmilikurusan" name="idhakmilikurusan"/>
<input type="hidden" value="$idpermohonanlokasi" name="idpermohonanlokasi"/><input type="hidden" value="$nofail2" name="nofail2" id="nofail2"/>
