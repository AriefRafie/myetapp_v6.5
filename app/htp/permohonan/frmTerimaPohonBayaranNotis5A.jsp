<style type="text/css">
<!--
.q {
	color: #F00;
}
.star {color: #F00;
}
-->
</style>
#set ($style = "disabled=disabled" )
#if($onlineMode=='1')
#set ($inputstyle = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )
#else 
#set ($inputstyle = "" )
#set ($inputstyleread = "" )
#set ($selectstyle = "" )
#end
#foreach($N in $vNotis)
	#set($a = $N.NoKPPemilikAsal)
#end
<!-- <fieldset>
<legend>Bayaran Notis 5A KTN ONLINE</legend> -->
<table width="99%" border="0">
  <tr>
    <td><fieldset><legend>MAKLUMAT NOTIS</legend>
<table width="100%" border="0">
  <!--<tr>
    <td width="1%" class="q"></td>
    <td width="23%">No K/P Pemilik Asal</td>
    <td width="1%"><strong>:</strong></td>
    <td width="25%">
        <input name="txtNoKPPemilikAsal" type="text" id="txtNoKPPemilikAsal" 
        onkeyup="this.value=this.value.toUpperCase();" value="$!dat.norujukanmop" $style/>     </td>
    <td width="1%">&nbsp;</td>
    <td width="29%">Cukai Tahun Pertama (RM)</td>
    <td width="1%"><strong>:</strong></td>
    <td width="19%"> <input name="txtCukaiTahunPertama" type="text" id="txtCukaiTahunPertama" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.kadarcukai" $style/></td>
    </tr> -->
    
	<tr>
	    <td width="1%" class="q"></td>
	    <td width="23%"&nbsp;</td>
	    <td width="1%">&nbsp;</td>
	    <td width="25%">&nbsp;</td>
	    <td width="1%">&nbsp;</td>
	    <td width="29%">Cukai Tahun Pertama (RM)</td>
	    <td width="1%"><strong>:</strong></td>
	    <td width="19%"> <input name="txtCukaiTahunPertama" type="text" id="txtCukaiTahunPertama" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.kadarcukai" $style/></td>
	</tr>     
  <tr>
    <td><span class="q">*</span></td>
    <td>Tarikh Notis 5A</td>
    <td><strong>:</strong></td>
    <td><input name="txtTarikhNotis5A" type="text" id="txtTarikhNotis5A" onBlur=doCalculateTarikh(); value="$!dat.tarikhnotis5a" size="11" maxlength="10" $style/> 
      <a href="javascript:displayDatePicker('txtTarikhNotis5A',false,'dmy');" $style><img border="0" src="../img/calendar.gif"/></td>
    <td>&nbsp;</td>
    <td><span class="star">* </span> Premium (RM)</td>
    <td><strong>:</strong></td>
    <td><input name="txtPremium" type="text" id="txtPremium" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarpremium" $style/></td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Terima Notis 5A</td>
    <td><strong>:</strong></td>
    <td><input name="txtTarikhTerimaNotis5A" type="text" id="txtTarikhTerimaNotis5A" value="$!dat.tarikhterima" size="11" maxlength="10" $style />      <a href="javascript:displayDatePicker('txtTarikhTerimaNotis5A',false,'dmy');"$style> <img src="../img/calendar.gif" alt="" border="0"/></a></td>
    <td>&nbsp;</td>
    <td><span class="star">* </span> Bayaran Notis 5F (RM)</td>
    <td><strong>:</strong></td>
    <td>
      <input name="txtBayaranNotisF" type="text" id="txtBayaranNotisF" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarnotisf" $style/></td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="23%">Tarikh Luput Notis Pertama</td>
    <td width="1%"><strong>:</strong></td>
    <td width="25%"><input name="txtTarikhLuputPertama" type="text" id="txtTarikhLuputPertama" value="$!dat.tarikhluput1" size="11" maxlength="10" $style/>
      <a href="javascript:displayDatePicker('txtTarikhLuputPertama',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></td>
    <td>&nbsp;</td>
    <td>Bayar Pendaftaran Hakmilik (RM)</td>
    <td><strong>:</strong></td>
    <td><input name="txtPendaftaranHakmilik" type="text" id="txtPendaftaranHakmilik" onBlur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarhakmilik" $style/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Luput Notis Kedua</td>
    <td><strong>:</strong></td>
    <td><input name="txtTarikhLuputNotisKedua" type="text" id="txtTarikhLuputNotisKedua" value="$!dat.tarikhluput2" size="11" maxlength="10" $style />
      <a href="javascript:displayDatePicker('txtTarikhLuputNotisKedua',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></td>
    <td>&nbsp;</td>
    <td>Bayaran Ukur (RM)</td>
    <td><strong>:</strong></td>
    <td><input name="txtBayarUkur" type="text" id="txtBayarUkur" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarukur" $style/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Luput Notis Ketiga</td>
    <td><strong>:</strong></td>
    <td><input name="txtTarikhLuputNotisKetiga" type="text" id="txtTarikhLuputNotisKetiga" value="$!dat.tarikhluput3" size="11" maxlength="10" $style/>      <a href="javascript:displayDatePicker('txtTarikhLuputNotisKetiga',false,'dmy');">   
       <img src="../img/calendar.gif" alt="" border="0"/></td>
    <td>&nbsp;</td>
    <td>Bayaran Perenggan (RM)</td>
    <td><strong>:</strong></td>
    <td><input name="txtBayaranPerenggan" type="text" id="txtBayaranPerenggan" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarperenggan" $style/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Perihal Rayuan</td>
    <td><strong>:</strong></td>
    <td rowspan="3">
        <textarea name="txtPerihalRayuan" id="txtPerihalRayuan" cols="30" rows="5" onkeyup="this.value=this.value.toUpperCase();" $style>$!dat.perihalrayuan</textarea></td>
    <td>&nbsp;</td>
    <td>Bayaran Fail (RM)</td>
    <td><strong>:</strong></td>
    <td><input name="txtBayaranFail" type="text" id="txtBayaranFail" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarfail" $style/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>Tanda Sempadan (RM)</td>
    <td><strong>:</strong></td>
    <td><input name="txtTandaSempadan" type="text" id="txtTandaSempadan" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayarsempadan" $style/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>Bayaran Lain 1 (RM)</td>
    <td><strong>:</strong></td>
    <td><input name="txtBayaranLain" type="text" id="txtBayaranLain" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayaranlain" $style/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>Bayaran Lain 2 (RM)</td>
    <td><strong>:</strong></td>
    <td><input name="txtBayaranLain2" type="text" id="txtBayaranLain2" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayaranlain2" $style/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>Bayaran Lain 3 (RM)</td>
    <td><strong>:</strong></td>
    <td><input name="txtBayaranLain3" type="text" id="txtBayaranLain3" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.bayaranlain3" $style/></td>
  </tr>
     <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
     </tr>
     <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>Rayuan Premium (-RM)</td>
    <td><strong>:</strong></td>
    <td><input name="txtRayuanPremium" type="text" id="txtRayuanPremium" onblur="validateCurrency(this,this.value,'');calculate()" value="$!dat.rayuanpremium" $style/></td>
     </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>Tempoh Rayuan</td>
    <td><strong>:</strong></td>
    <td><input name="txTempohRayuan" type="text" id="txTempohRayuan" size="11" maxlength="10" $style/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>Tarikh Rayuan</td>
    <td><strong>:</strong></td>
    <td><input name="txtTarikhRayuan" type="text" id="txtTarikhRayuan" size="11" maxlength="10" $style/>
               <a href="javascript:displayDatePicker('txtTarikhRayuan',false,'dmy');">   <img src="../img/calendar.gif" alt="" border="0"/></td>
  </tr>
  <tr>
    <td></td>
    <td>&nbsp;</td>
    <td></td>
    <td></td>
    <td></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>
    #if ($tabmode == '1')
      <input class="stylobutton" type="button" value="Simpan" onclick="SimpanNotis()" style="display:none"/>
    #else
     <input class="stylobutton" type="button" value="Simpan" onclick="KemaskiniNotis()" style="display:none"/>
    #end    </td>
    <td></td>
    <td></td>
    <td></td>
    <td>Bayaran Notis 5A (RM)</td>
    <td><strong>:</strong></td>
    <td><input name="txtJumBayaran" type="text" id="txtJumBayaran" onblur="validateCurrency(this,this.value,'');" value="$!dat.jumBayaran" $selectstyle $inputstyleread/></td>
  </tr>
</table>
</fieldset>
</td>
  </tr>
  <tr>
    <td></td>
  </tr>
</table>
<!-- </fieldset> -->
<input type="hidden" name="idNotis" value="$idNotis" />