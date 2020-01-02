<style type="text/css">
<!--
.q {
	text-align: right;
}
.w {
	color: #F00;
}
.qw {
	text-align: center;
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
$pageLokasi <br>
$lokasiinfo <br>

#if($pageLokasi=='1')
  #foreach($lokasiinfo in $LI)


  <fieldset>
<legend>Lokai Tanahx </legend>

  <table width="100%" border="0">
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td class="qw">Jarak</td>
      <td class="qw">Perihal</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Jauh Dari Bandar</td>
      <td>:</td>
      <td><input type="text" name="txtJDbandar" id="txtJDbandar" value="$!lokasiinfo.jarak_bandar" $selectstyle $inputstyleread/></td>
      <td><input type="text" name="txtJDbandarPerihal" id="txtJDbandarPerihal" value="$!lokasiinfo.keterangan_bandar"  $selectstyle $inputstyleread/></td>
      <td>&nbsp;</td>
      <td>Sempadan Utara </td>
      <td>:</td>
      <td><input type="text" name="txtSempadanUtara" id="txtSempadanUtara" value="$!lokasiinfo.sempadan_utara" $selectstyle $inputstyleread/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Jauh Dari LebuhRaya </td>
      <td>:</td>
      <td><input type="text" name="txtJDLebuhRaya" id="txtJDLebuhRaya" value="$!lokasiinfo.jarak_jalan" $selectstyle $inputstyleread/></td>
      <td><input type="text" name="txtJDLebuhRayaPerihal" id="txtJDLebuhRayaPerihal" value="$!lokasiinfo.keterangan_jalan" $selectstyle $inputstyleread/></td>
      <td>&nbsp;</td>
      <td>Sempadan Selatan </td>
      <td>:</td>
      <td><input type="text" name="txtSempadanSelatan" id="txtSempadanSelatan" value="$!lokasiinfo.sempadan_selatan" $selectstyle $inputstyleread/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Jauh Dari Jalan Keretapi </td>
      <td>:</td>
      <td><input type="text" name="txtJDJalanKeretapi" id="txtJDJalanKeretapi" value="$!lokasiinfo.jarak_keretapi" $selectstyle $inputstyleread/></td>
      <td><input type="text" name="txtJDJalanKeretapiPerihal" id="txtJDJalanKeretapiPerihal" value="$!lokasiinfo.keterangan_keretapi" $selectstyle $inputstyleread/></td>
      <td>&nbsp;</td>
      <td>Sempadan Timur </td>
      <td>:</td>
      <td><input type="text" name="txtSempadanTimur" id="txtSempadanTimur" value="$!lokasiinfo.sempadan_timur" $selectstyle $inputstyleread/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Jauh Dari Sungai / Pantai </td>
      <td>:</td>
      <td><input type="text" name="txtJDSungaiPantai" id="txtJDSungaiPantai" value="$!lokasiinfo.jarak_sungai" $selectstyle $inputstyleread/></td>
      <td><input type="text" name="txtJDSungaiPantaiPerihal" id="txtJDSungaiPantaiPerihal" value="$!lokasiinfo.keterangan_sungai" $selectstyle $inputstyleread/></td>
      <td>&nbsp;</td>
      <td>Sempadan Barat </td>
      <td>:</td>
      <td><input type="text" name="txtSempadanBarat" id="txtSempadanBarat" value="$!lokasiinfo.sempadan_barat" $selectstyle $inputstyleread/></td>
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
      <td><input type="text" name="txtKeteranganLain" id="txtKeteranganLain" value="$!lokasiinfo.lain_lain" $selectstyle $inputstyleread/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td colspan="4">   
        <!--<input class="stylobutton" type="button" name="Simpan" id="Simpan" value="Simpan"  onclick="SimpanLokasi2()"/>-->
        <input class="stylobutton" type="button" name="Kemaskini" id="Kemaskini" value="Lakaran/Pelan" onclick="SeterusLakaran(3,0,'kemaskinipermohonan','LakaranPelan','$idhakmilikurusan')"/>
        <!--<input type="submit" name="Kembali" id="Kembali" value="Kembali" onclick="javascript:selectTab(0,'kemaskinipermohonan','MakAsasTanah',0)" />-->
 #if($pageMode ==1)       
           <input class="stylobutton" type="button" name="Kemaskini2" id="Kemaskini2" value="Simpan" onclick="SimpanUpdate('$idhakmilikurusan')"  />
#else
<input class="stylobutton" type="button" name="Kemaskini2" id="Kemaskini2" value="Kemaskini" onclick="doKemaskini('$idhakmilikurusan')"  />
#end
    <input class="stylobutton" type="button" name="Kembali" id="Kembali" value="Kembali" onclick="KembaliAsas(0,'kemaskinipermohonan','MakAsasTanah',0)" /></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
</fieldset>


<p>
  <!-- <input type="text" value="$idfail" name="idfail"/>-->
  <input type="hidden" value="$idhakmilikurusan" name="idhakmilkurusan" id="idhakmilkurusan"/>
  <input type="hidden" value="$lokasiinfo.id_maklumattanah" name="idmaklumattanah" id="idmaklumattanah"/>
    #end
<p>#else
<input type="hidden" value="$idhakmilikurusan">
<table width="100%" border="0">
#if($idhakmilikurusan == 0)
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
      <td><input class="stylobutton" type="button" value="Tambah" onclick="SimpanLokasi($idhakmilikurusan)"></td>
      <td>&nbsp;</td>
    </tr>
    #end
</table>

#end
