<style type="text/css">
<!--
.star {color: #F00;
}
-->
</style>
<fieldset>
<legend>Tambah Maklumat Asas Tanah</legend>
<table width="100%" border="0">
  <tr>
    <td width="18%"><span class="star" style="font-weight: bold"><strong>*</strong></span><span style="font-weight: bold"><strong>Negeri :</strong></span></td>
    <td width="18%" colspan="-2">$socNegeri</td>
    <td width="4%" colspan="-2">&nbsp;</td>
    <td width="18%" colspan="-2"><span class="star">*</span>No.Syit</td>
    <td width="42%"><input type="text" name="noSyit" id="noSyit" onkeyup="validateNumber(this,this.value);" /></td>
  </tr>
  <tr>
    <td><span class="star">*</span>Daerah:</td>
    <td colspan="-2">$socDaerah</td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2">No. Pelan</td>
    <td><input type="text" name="noPelan" id="noPelan" onkeyup="this.value=this.value.toUpperCase();"/></td>
    </tr>
  <tr>
    <td><span class="star">*</span>Mukim/Bandar/Pekan :</td>
    <td colspan="-2">$socMukim</td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2"><span class="star">*</span>Kod Luas</td>
    <td>
    ##$kodluas
    <select name="kodluas" id="kodluas" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeKodLuas()">
      #if($socLuas == '1')
      <option value="">SILA PILIH</option>
        <option value="1" selected="selected"> KM - KILOMETER PERSEGI</option>
        <option value="2"> H - HEKTAR</option>
        <option value="3"> M - METER PERSEGI</option>
        <option value="4"> E - EKAR,ROOD,POLE </option>
        <option value="5"> K - KAKI PERSEGI</option>
        <option value="6"> P - EKAR PERPULUHAN</option>
        <option value="7"> D - EKAR,DEPA</option>
        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
        <option value="9"> BN - BATU NAUTIKA</option>
      
      #elseif($socLuas == '2')
        
      <option value="">SILA PILIH</option>
        <option value="1"> KM - KILOMETER PERSEGI</option>
        <option value="2" selected="selected"> H - HEKTAR</option>
        <option value="3"> M - METER PERSEGI</option>
        <option value="4"> E - EKAR,ROOD,POLE </option>
        <option value="5"> K - KAKI PERSEGI</option>
        <option value="6"> P - EKAR PERPULUHAN</option>
        <option value="7"> D - EKAR,DEPA</option>
        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
        <option value="9"> BN - BATU NAUTIKA</option>
      
       #elseif($socLuas == '3')
        
      <option value="">SILA PILIH</option>
        <option value="1"> KM - KILOMETER PERSEGI</option>
        <option value="2"> H - HEKTAR</option>
        <option value="3" selected="selected"> M - METER PERSEGI</option>
        <option value="4"> E - EKAR,ROOD,POLE </option>
        <option value="5"> K - KAKI PERSEGI</option>
        <option value="6"> P - EKAR PERPULUHAN</option>
        <option value="7"> D - EKAR,DEPA</option>
        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
        <option value="9"> BN - BATU NAUTIKA</option>
      
       #elseif($socLuas == '4')
        
      <option value="">SILA PILIH</option>
        <option value="1"> KM - KILOMETER PERSEGI</option>
        <option value="2"> H - HEKTAR</option>
        <option value="3"> M - METER PERSEGI</option>
        <option value="4" selected="selected"> E- EKAR,ROOD,POLE </option>
        <option value="5"> K - KAKI PERSEGI</option>
        <option value="6"> P - EKAR PERPULUHAN</option>
        <option value="7"> D - EKAR,DEPA</option>
        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
        <option value="9"> BN - BATU NAUTIKA</option>
       
       #elseif($socLuas == '5')
        
      <option value="">SILA PILIH</option>
        <option value="1"> KM - KILOMETER PERSEGI</option>
        <option value="2"> H - HEKTAR</option>
        <option value="3"> M - METER PERSEGI</option>
        <option value="4"> E - EKAR,ROOD,POLE </option>
        <option value="5" selected="selected"> K - KAKI PERSEGI</option>
        <option value="6"> P - EKAR PERPULUHAN</option>
        <option value="7"> D - EKAR,DEPA</option>
        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
        <option value="9"> BN - BATU NAUTIKA</option>
      
       #elseif($socLuas == '6')
        
      <option value="">SILA PILIH</option>
        <option value="1"> KM - KILOMETER PERSEGI</option>
        <option value="2"> H - HEKTAR</option>
        <option value="3"> M - METER PERSEGI</option>
        <option value="4"> E - EKAR,ROOD,POLE </option>
        <option value="5"> K - KAKI PERSEGI</option>
        <option value="6" selected="selected"> P - EKAR PERPULUHAN</option>
        <option value="7"> D - EKAR,DEPA</option>
        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
        <option value="9"> BN - BATU NAUTIKA</option>
      
       #elseif($socLuas == '7')
        
      <option value="">SILA PILIH</option>
        <option value="1"> KM - KILOMETER PERSEGI</option>
        <option value="2"> H - HEKTAR</option>
        <option value="3"> M - METER PERSEGI</option>
        <option value="4"> E - EKAR,ROOD,POLE </option>
        <option value="5"> K- KAKI PERSEGI</option>
        <option value="6"> P - EKAR PERPULUHAN</option>
        <option value="7" selected="selected"> D - EKAR,DEPA</option>
        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
        <option value="9"> BN - BATU NAUTIKA</option>
      
       #elseif($socLuas == '8')
        
      <option value="">SILA PILIH</option>
        <option value="1"> KM - KILOMETER PERSEGI</option>
        <option value="2"> H - HEKTAR</option>
        <option value="3"> M - METER PERSEGI</option>
        <option value="4"> E - EKAR,ROOD,POLE </option>
        <option value="5"> K - KAKI PERSEGI</option>
        <option value="6"> P - EKAR PERPULUHAN</option>
        <option value="7"> D - EKAR,DEPA</option>
        <option value="8" selected="selected"> R - RELONG,JEMBA,KAKI PERSEGI</option>
        <option value="9"> BN - BATU NAUTIKA</option>
      
       #elseif($socLuas == '9')
        
      <option value="">SILA PILIH</option>
        <option value="1"> KM - KILOMETER PERSEGI</option>
        <option value="2"> H - HEKTAR</option>
        <option value="3"> M - METER PERSEGI</option>
        <option value="4"> E - EKAR,ROOD,POLE </option>
        <option value="5"> K - KAKI PERSEGI</option>
        <option value="6"> P - EKAR PERPULUHAN</option>
        <option value="7"> D - EKAR,DEPA</option>
        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
        <option value="9" selected="selected"> BN - BATU NAUTIKA</option>
         
       #else
        
      <option value="" selected="selected">SILA PILIH</option>
        <option value="1"> KM - KILOMETER PERSEGI</option>
        <option value="2"> H - HEKTAR</option>
        <option value="3"> M - METER PERSEGI</option>
        <option value="4"> E- EKAR,ROOD,POLE </option>
        <option value="5"> K - KAKI PERSEGI</option>
        <option value="6"> P - EKAR PERPULUHAN</option>
        <option value="7"> D - EKAR,DEPA</option>
        <option value="8"> R - RELONG,JEMBA,KAKI PERSEGI</option>
        <option value="9"> BN - BATU NAUTIKA</option>
                                            
 	  #end
    </select>
    <br />
    #if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6')
      <input name="txtLuas1" type="text" class="$disabled" id="txtLuas1" size="3" $readonly />
    #elseif($socLuas == '8' || $socLuas == '4')
      <input type="text" name="txtLuas2" id="txtLuas2" class="$disabled" size="3" $readonly/>
      <input type="text" name="txtLuas3" id="txtLuas3" class="$disabled" size="3" $readonly/>
      <input type="text" name="txtLuas4" id="txtLuas4" class="$disabled" size="3" $readonly/>      
    #elseif($socLuas == '7')
      <input name="txtLuas5" type="text" class="$disabled" id="txtLuas5" size="3" $readonly />
      <input name="txtLuas6" type="text" class="$disabled" id="txtLuas6" size="3" $readonly />
    #end 
    #if ($socLuas != "")
    <a href="javascript:kiraLuas('$socLuas')" title="Kira luas dalam hektar." class="style1 style3">Kira Luas</a></label>
    #end
       
    </td>
    </tr>
  <tr>
    <td><span class="star">*</span>Kod Lot :</td>
    <td colspan="-2">$noLot</td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2"><span class="star">*</span>Luas</td>
    <td><input type="text" name="Luas" id="Luas" onkeyup="validateNumber(this,this.value);"/>     
       (hektar)</td>
    </tr>
  <tr>
    <td><span class="star">*</span>Nombor Lot :</td>
    <td colspan="-2"><input type="text" name="txtNoLot" id="txtNoLot" onkeyup="validateNumber(this,this.value);" /></td>
    <td colspan="-2">&nbsp;</td>
    <td colspan="-2"><span class="star">*</span>Lokasi</td>
    <td rowspan="3">
      <textarea name="Lokasi" id="Lokasi" cols="35" rows="5" onkeyup="this.value=this.value.toUpperCase();"></textarea>
      <!--<input type="text" name="LuasH" id="LuasH" />--></td>
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
    <td colspan="-2"><span class="star"><!--*</span>Luas(H)--></td>
    </tr>
  <tr>
    <td colspan="5">
      <input type="button" class="stylobutton"  value="Simpan" onclick="SimpanMaklumatAsasTanah()" />
      <input class="stylobutton" type="button" name="Kembali" id="Kembali" value="Kembali" onclick="KembaliAsas(0,'kemaskinipermohonan','MakAsasTanah',0)" />
</td>
  </tr>
</table>
</fieldset>

