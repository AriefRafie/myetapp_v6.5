<style type="text/css">
<!--
.qw {
	text-align: center;
}
.wq {
	text-align: left;
}
-->
</style>
#if($statussimpan=='0')
Maklumat Berjaya Disimpan!
#end
<fieldset>
<legend>Lokasi Tanah</legend>
  <table width="100%" border="0">
    <tr>
      <td width="7">&nbsp;</td>
      <td width="200">&nbsp;</td>
      <td width="10">&nbsp;</td>
      <td width="155" class="qw">Jarak</td>
      <td width="155" class="qw">Perihal</td>
      <td width="19">&nbsp;</td>
      <td width="249">&nbsp;</td>
      <td width="10">&nbsp;</td>
      <td width="405"> Jarak</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Jauh Dari Bandar</td>
      <td>:</td>
      <td><input type="text" name="txtJDbandar" id="txtJDbandar"  onkeyup="validateNumber(this,this.value);"/></td>
      <td><input type="text" name="txtJDbandarPerihal" id="txtJDbandarPerihal" onkeyup="this.value=this.value.toUpperCase();" /></td>
      <td>&nbsp;</td>
      <td>Sempadan Utara </td>
      <td>:</td>
      <td><input type="text" name="txtSempadanUtara" id="txtSempadanUtara" onkeyup="validateNumber(this,this.value);" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Jauh Dari LebuhRaya </td>
      <td>:</td>
      <td><input type="text" name="txtJDLebuhRaya" id="txtJDLebuhRaya"  onkeyup="validateNumber(this,this.value);"/></td>
      <td><input type="text" name="txtJDLebuhRayaPerihal" id="txtJDLebuhRayaPerihal" onkeyup="this.value=this.value.toUpperCase();"/></td>
      <td>&nbsp;</td>
      <td>Sempadan Selatan </td>
      <td>:</td>
      <td><input type="text" name="txtSempadanSelatan" id="txtSempadanSelatan"  onkeyup="validateNumber(this,this.value);"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Jauh Dari Jalan Keretapi </td>
      <td>:</td>
      <td><input type="text" name="txtJDJalanKeretapi" id="txtJDJalanKeretapi"  onkeyup="validateNumber(this,this.value);"/></td>
      <td><input type="text" name="txtJDJalanKeretapiPerihal" id="txtJDJalanKeretapiPerihal" onkeyup="this.value=this.value.toUpperCase();"/></td>
      <td>&nbsp;</td>
      <td>Sempadan Timur </td>
      <td>:</td>
      <td><input type="text" name="txtSempadanTimur" id="txtSempadanTimur"  onkeyup="validateNumber(this,this.value);"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Jauh Dari Sungai / Pantai </td>
      <td>:</td>
      <td><input type="text" name="txtJDSungaiPantai" id="txtJDSungaiPantai"  onkeyup="validateNumber(this,this.value);"/></td>
      <td><input type="text" name="txtJDSungaiPantaiPerihal" id="txtJDSungaiPantaiPerihal" onkeyup="this.value=this.value.toUpperCase();"/></td>
      <td>&nbsp;</td>
      <td>Sempadan Barat </td>
      <td>:</td>
      <td><input type="text" name="txtSempadanBarat" id="txtSempadanBarat"  onkeyup="validateNumber(this,this.value);"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>Keterangan Lain Menganai Tanah </td>
      <td>:</td>
      <td><input type="text" name="txtKeteranganLain" id="txtKeteranganLain"  onkeyup="validateNumber(this,this.value);"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td colspan="8" class="wq">   
        <input class="stylobutton" type="button" name="Simpan" id="Simpan" value="Simpan"  onclick="SimpanLokasi2($idhakmilikurusan)"/>
        <input class="stylobutton" type="button" name="Kembali" id="Kembali" value="Kembali" onclick="KembaliAsas(0,'kemaskinipermohonan','MakAsasTanah',0)" />
          <!--<input class="stylobutton" type="submit" name="Seterusnya" id="Seterusnya" value="Seterusnya" onclick="SeterusLakaran()"/>-->
      </td>
    </tr>
  </table>
</fieldset>
<p>
 <!-- <input type="text" value="$idfail" name="idfail"/>-->
 <input type="hidden" value="$idhakmilikurusan" name="idhakmilkurusan" id="idhakmilkurusan"/> 