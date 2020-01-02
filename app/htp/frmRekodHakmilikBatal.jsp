<style type="text/css">
<!--
.style1 {color: #0000FF}
.style2 {
	font-size: x-small;
	font-style: italic;
	color: #0000FF;
}
.style3 {color: #FF0000}
-->
</style>
<input name="idHakmilik" type="hidden" value="$idHakmilik" />
<input name="mode" type="hidden" value="$mode" />
<input name="cetak" type="hidden" value="$cetak" />
<<<<<<< .mine
<input name="modePopup" type="hidden">
<fieldset>
<legend>PEMBATALAN HAKMILIK</legend>
=======
<fieldset><legend>PENDAFTARAN HAKMILIK</legend>
>>>>>>> .r28125
<table width="100%" border="0">
  <tr>
<<<<<<< .mine
    <td width="24%"><div align="right">Kementerian</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtNamaKementerian</span></td>
    <td width="24%"><div align="right">No. Fail KJP</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtFailKJP</span></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Agensi</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtNamaAgensi</span></td>
    <td width="24%"><div align="right">No. Fail PTG</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtFailPTG</span></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">No. Fail Seksyen</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtNoFailSeksyen</span></td>
    <td width="24%"><div align="right">No. Fail PTD</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Tajuk</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtTajuk</span></td>
    <td width="24%"><div align="right">Cara Perolehan</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$caraPerolehan</span></td>
  </tr>
  <tr>
    <td  width="100%" colspan="6"><hr /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font></i> Negeri</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">$selectNegeriHR</td>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font> </i>Unit Luas</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><select name="socLuas" id="socLuas" style="width:200px;" readonly class="disabled" disabled onchange="doChangeTaraf()">
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
         
       #elseif($socLuas == '')
        
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
    </select>    </td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font></i> Daerah</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">$selectDaerahHR</td>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font> </i>Luas</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><label></label>
      <input name="txtLuasLama" type="text" id="txtLuasLama" value="$txtLuasLama" size="15" readonly class="disabled"/></td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font></i> Bandar/Pekan/Mukim</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">$selectMukimHR</td>
    <td width="24%"><div align="right">Luas Bersamaan</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtLuas" type="text" class="disabled" id="txtLuas" onkeyup="this.value=this.value.toUpperCase();" value="$txtLuas" size="20" readonly/>
      (hektar) </td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font></i> Jenis Hakmilik</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">$selectJenisHakmilikHR</td>
    <td width="24%"><div align="right">No Pelan Akui</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtNoPelan" type="text" id="txtNoPelan" value="$txtNoPelan" size="30" readonly class="disabled" onkeyup="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font></i> No. Hakmilik</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">
      <input name="txtNoHakmilik" type="text" class="disabled" id="txtNoHakmilik" onkeyup="validateNumber(this,this.value);" value="$txtNoHakmilik" size="20" readonly />
    </span></td>
    <td width="24%"><div align="right">No. Syit Piawai</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtNoSyit" type="text" id="txtNoSyit" value="$txtNoSyit" size="30" readonly class="disabled" style="text-transform:uppercase;"/></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">No. Strata</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">
      <input name="txtNoBangunan" type="text" class="disabled" id="txtNoBangunan" onkeyup="validateNumber(this,this.value);" value="$txtNoBangunan" size="3" readonly />
      <input name="txtNoTingkat" type="text" class="disabled" id="txtNoTingkat" onkeyup="validateNumber(this,this.value);" value="$txtNoTingkat" size="3" readonly />
      <input name="txtNoPetak" type="text" class="disabled" id="txtNoPetak" onkeyup="validateNumber(this,this.value);" value="$txtNoPetak" size="3" readonly />
    </span></td>
    <td width="24%"><div align="right">No. PU</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtNoPu" type="text" id="txtNoPu" value="$txtNoPu" size="30" readonly class="disabled" style="text-transform:uppercase;"/></td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font></i> Jenis Lot</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">$selectJenisLotHR</td>
    <td width="24%"><div align="right">Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><select name="socRizab" id="socRizab" style="width:200px;" readonly class="disabled" disabled onchange="doChangeTaraf()">

=======
    <td width="24%"><div align="right">Kementerian</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtNamaKementerian</span></td>
    <td width="24%"><div align="right">No. Fail KJP</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtFailKJP</span></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Agensi</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtNamaAgensi</span></td>
    <td width="24%"><div align="right">No. Fail PTG</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtFailPTG</span></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">No. Fail Seksyen</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtNoFailSeksyen</span></td>
    <td width="24%"><div align="right">No. Fail PTD</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Tajuk</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtTajuk</span></td>
    <td width="24%"><div align="right">Cara Perolehan</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$caraPerolehan</span></td>
  </tr>
  <tr>
    <td  width="100%" colspan="6"><hr /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Negeri</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">$selectNegeriHR</td>
    <td><div align="right">No Pelan Akui</div></td>
    <td><div align="center">:</div></td>
    <td><input name="txtNoPelan" type="text" id="txtNoPelan" value="$txtNoPelan" size="30" readonly="readonly" class="disabled" onkeyup="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Daerah</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">$selectDaerahHR</td>
    <td width="24%"><div align="right">No. Syit Piawai</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtNoSyit" type="text" id="txtNoSyit" value="$txtNoSyit" size="30" readonly="readonly" class="disabled" style="text-transform:uppercase;"/></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Bandar/Pekan/Mukim</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">$selectMukimHR</td>
    <td width="24%"><div align="right">No. PU</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtNoPu" type="text" id="txtNoPu" value="$txtNoPu" size="30" readonly="readonly" class="disabled" style="text-transform:uppercase;"/></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Jenis Hakmilik</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">$selectJenisHakmilikHR</td>
    <td width="24%"><div align="right">Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><select name="socRizab" id="socRizab" style="width:200px;" readonly="readonly" class="disabled" disabled="disabled" onchange="doChangeTaraf()">
      
      
      
      

>>>>>>> .r28125
         #if($socRizab == 'Y')
<<<<<<< .mine
	    <option value="">SILA PILIH</option>
        <option value="Y" selected="selected"> Y - YA</option>
        <option value="T"> T - TIDAK</option>
        #elseif ($socRizab == 'T') 
   	    <option value="">SILA PILIH</option>
        <option value="Y"> Y - YA</option>
        <option value="T"selected="selected"> T - TIDAK</option>
        #else   
      	<option value="" selected="selected">SILA PILIH</option>
        <option value="Y"> Y - YA</option>
        <option value="T"> T - TIDAK</option>
=======
	    
      
      
      
      <option value="">SILA PILIH</option>
      <option value="Y" selected="selected"> Y - YA</option>
      <option value="T"> T - TIDAK</option>
      
      
      
      
        #elseif ($socRizab == 'T') 
   	    
      
      
      
      <option value="">SILA PILIH</option>
      <option value="Y"> Y - YA</option>
      <option value="T"selected="selected"> T - TIDAK</option>
      
      
      
      
        #else   
      	
      
      
      
      <option value="" selected="selected">SILA PILIH</option>
      <option value="Y"> Y - YA</option>
      <option value="T"> T - TIDAK</option>
      
      
      
      
>>>>>>> .r28125
        #end
<<<<<<< .mine
    </select></td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font></i> No Lot</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">
      <input name="txtNoLot" type="text" class="disabled" id="txtNoLot" onkeyup="validateNumber(this,this.value);" value="$txtNoLot" size="20" readonly />
    </span></td>
    <td width="24%"><div align="right">No. Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><label>
      <input name="txtNoRizab" type="text" id="txtNoRizab" value="$txtNoRizab" readonly class="disabled"/>
    </label></td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font> </i>Tarikh Terima Hakmilik</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style2">
      <input name="txdTarikhTerima" type="text" id="txdTarikhTerima" value="$txdTarikhTerima" size="9" readonly class="disabled"
        onkeyup="validateNumber(this,this.value);"/>
    </span></td>
    <td width="24%"><div align="right">Tarikh Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txdTarikhRizab" type="text" id="txdTarikhRizab" value="$txdTarikhRizab" size="9" readonly class="disabled" onkeyup="validateNumber(this,this.value);"/>      </td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font> </i>Tarikh Daftar Hakmilik </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" size="9" readonly class="disabled" 
        onkeyup="validateNumber(this,this.value);" />    </td>
    <td width="24%"><div align="right">Jenis Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><label> $selectRizab</label></td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font> </i>Taraf Hakmilik </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><select name="socTaraf" id="socTaraf" style="width:200px;" readonly class="disabled" disabled onchange="doChangeTaraf()">
             
=======
    
    
    
    
    </select></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">No. Hakmilik</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">
      <input name="txtNoHakmilik" type="text" class="disabled" id="txtNoHakmilik" onkeyup="validateNumber(this,this.value);" value="$txtNoHakmilik" size="20" readonly="readonly" />
    </span></td>
    <td width="24%"><div align="right">No. Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><label>
      <input name="txtNoRizab" type="text" id="txtNoRizab" value="$txtNoRizab" readonly="readonly" class="disabled"/>
    </label></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">No. Strata</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">
      <input name="txtNoBangunan" type="text" class="disabled" id="txtNoBangunan" onkeyup="validateNumber(this,this.value);" value="$txtNoBangunan" size="3" readonly="readonly" />
      <input name="txtNoTingkat" type="text" class="disabled" id="txtNoTingkat" onkeyup="validateNumber(this,this.value);" value="$txtNoTingkat" size="3" readonly="readonly" />
      <input name="txtNoPetak" type="text" class="disabled" id="txtNoPetak" onkeyup="validateNumber(this,this.value);" value="$txtNoPetak" size="3" readonly="readonly" />
    </span></td>
    <td width="24%"><div align="right">Tarikh Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txdTarikhRizab" type="text" id="txdTarikhRizab" value="$txdTarikhRizab" size="9" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);"/>
    </td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Jenis Lot</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">$selectJenisLotHR</td>
    <td width="24%"><div align="right">Jenis Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><label> $selectRizab</label></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">No Lot</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">
      <input name="txtNoLot" type="text" class="disabled" id="txtNoLot" onkeyup="validateNumber(this,this.value);" value="$txtNoLot" size="20" readonly="readonly" />
    </span></td>
    <td width="24%"><div align="right">Kawasan Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtKawasanRizab" type="text" id="txtKawasanRizab" style="text-transform:uppercase;" 
        value="$txtKawasanRizab" size="27" readonly="readonly" class="disabled"/>
    </td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Tarikh Terima Hakmilik</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style2">
      <input name="txdTarikhTerima" type="text" id="txdTarikhTerima" value="$txdTarikhTerima" size="9" readonly="readonly" class="disabled"
        onkeyup="validateNumber(this,this.value);"/>
    </span></td>
    <td><div align="right">Syarat Nyata</div></td>
    <td><div align="center">:</div></td>
    <td width="24%" rowspan="4"><textarea name="txtSyarat" cols="27" rows="5" readonly="readonly" class="disabled" id="txtSyarat" style="text-transform:uppercase;">$txtSyarat </textarea></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Tarikh Daftar Hakmilik </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" size="9" readonly="readonly" class="disabled" 
        onkeyup="validateNumber(this,this.value);" />
    </td>
    <td  width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Taraf Hakmilik </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><select name="socTaraf" id="socTaraf" style="width:200px;" readonly="readonly" class="disabled" disabled="disabled">
      
             
>>>>>>> .r28125
        #if($socTaraf == 'P')
<<<<<<< .mine
          
      <option value="">SILA PILIH</option>
        <option value="P" selected="selected">P - PAJAKAN</option>
        <option value="F">F - PEGANGAN BEBAS</option>
=======
          
      
      <option value="">SILA PILIH</option>
      <option value="P" selected="selected">P - PAJAKAN</option>
      <option value="F">F - PEGANGAN BEBAS</option>
      
      
        #elseif($socTaraf == 'F')
          
      
      <option value="">SILA PILIH</option>
      <option value="P">P - PAJAKAN</option>
      <option value="F" selected="selected">F - PEGANGAN BEBAS/FREE HOLD</option>
      
      
        #else
          
      
      <option value="" selected="selected">SILA PILIH</option>
      <option value="P">P - PAJAKAN</option>
      <option value="F">F - PEGANGAN BEBAS/FREE HOLD</option>
      
      
        #end
        
    
    </select>
    </td>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Tempoh </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtTempoh" type="text" id="txtTempoh" value="$txtTempoh" size="4" readonly="readonly" class="disabled" onchange="cal_tarikh_luput()" onkeyup="validateNumber(this,this.value);" onblur="cal_tarikh_luput()"/>
      Tahun</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Tarikh Luput </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txdTarikhLuput" type="text" id="txdTarikhLuput" value="$txdTarikhLuput" size="9" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);" /></td>
    <td><div align="right">Sekatan Kepentingan</div></td>
    <td><div align="center">:</div></td>
    <td width="24%" rowspan="4"><textarea name="txtSekatan" cols="27" rows="5" id="txtSekatan" style="text-transform:uppercase;" readonly="readonly" class="disabled">$txtSekatan</textarea></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Cukai Tahun Pertama (RM)</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtCukaiTahun" type="text" id="txtCukaiTahun" value="$txtCukaiTahun" size="20" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);" onblur="formatCurrencyPertama(this.value)"/>
      </a></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Cukai Semasa (RM)</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><div align="left">
      <input name="txtCukaiTerkini" type="text" id="txtCukaiTerkini" value="$txtCukaiTerkini" size="20" $readonly class="disabled" onkeyup="validateNumber(this,this.value);" onblur="formatCurrencySemasa(this.value)"/>
      <span class="style5"><br />
        <a href="javascript:viewTransaksiCukai('$idHakmilik')" class="style2" title="Papar transaksi cukai terdahulu,"></a></span></div></td>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><a href="javascript:viewTransaksiCukai('$idHakmilik')" class="style2" title="Papar transaksi cukai terdahulu,">Senarai cukai terdahulu.</a></td>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td><div align="right">Lokasi </div></td>
    <td><div align="center">:</div></td>
    <td><input name="txtLokasi" type="text" id="txtLokasi" value="$txtLokasi" size="30" readonly="readonly" class="disabled" style="text-transform:uppercase;"/></td>
    <td><div align="right">Catatan</div></td>
    <td><div align="center">:</div></td>
    <td width="24%" rowspan="4"><textarea name="txtKemAgenTerkini" cols="27" rows="5" class="$disabled" id="txtKemAgenTerkini" $readonly="$readonly">$txtKemAgenTerkini</textarea></td>
  </tr>
  <tr>
    <td><div align="right">Kegunaan Tanah</div></td>
    <td><div align="center">:</div></td>
    <td width="24%" rowspan="4"><textarea name="txtKegunaanTanah" cols="27" rows="5" id="txtKegunaanTanah" style="text-transform:uppercase;" readonly="readonly" class="disabled">$txtKegunaanTanah</textarea></td>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td width="24%"><div align="right"> Status Sah</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><select name="socStatus" id="socStatus" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeTaraf()">
>>>>>>> .r28125
      
<<<<<<< .mine
        #elseif($socTaraf == 'F')
          
      <option value="">SILA PILIH</option>
        <option value="P">P - PAJAKAN</option>
        <option value="F" selected="selected">F - PEGANGAN BEBAS/FREE HOLD</option>
=======
>>>>>>> .r28125
      
        #else
          
      <option value="" selected="selected">SILA PILIH</option>
        <option value="P">P - PAJAKAN</option>
        <option value="F">F - PEGANGAN BEBAS/FREE HOLD</option>
      
<<<<<<< .mine
        #end
        
    </select>    </td>
    <td width="24%"><div align="right">Kawasan Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtKawasanRizab" type="text" id="txtKawasanRizab" style="text-transform:uppercase;" 
        value="$txtKawasanRizab" size="27" readonly class="disabled"/>    </td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Tempoh </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"> #if($socTaraf == 'P')
      <input name="txtTempoh" type="text" id="txtTempoh" value="$txtTempoh" size="4" $readonly class="$disabled" onchange="cal_tarikh_luput()" onkeyup="validateNumber(this,this.value);" onblur="cal_tarikh_luput()"/>
      #else
      <input name="txtTempoh" type="text" id="txtTempoh" value="" size="4" readonly="readonly" class="disabled" onchange="cal_tarikh_luput()" onkeyup="validateNumber(this,this.value);" onblur="cal_tarikh_luput()"/>
      #end Tahun</td>
    <td width="24%"><div align="right">Syarat Nyata</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%" rowspan="4"><label></label>
        <label>
        <textarea name="txtSyarat" cols="27" rows="5" id="txtSyarat" style="text-transform:uppercase;"  readonly class="disabled">$txtSyarat </textarea>
      </label></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Tarikh Luput </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">
    #if($socTaraf == 'P')
      <input name="txdTarikhLuput" type="text" id="txdTarikhLuput" value="txdTarikhLuput" size="9" readonly class="disabled" onkeyup="validateNumber(this,this.value);" /></td>
    #else
    <input name="txdTarikhLuput" type="text" id="txdTarikhLuput" value="" size="9" readonly="readonly" class="disabled" onkeyup="validateNumber(this,this.value);" />
  #end 
  <td width="24%">&nbsp;</td>
  <td width="1%">&nbsp;</td>
  <tr>
    <td  width="24%"><div align="right">Kod Bayaran Cukai</div></td>
    <td  width="1%"><div align="center">:</div></td>
    <td  width="24%">&nbsp;</td>
    <td  width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font> </i>Cukai Tahun Pertama (RM)</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtCukaiTahun" type="text" id="txtCukaiTahun" value="$txtCukaiTahun" size="20" readonly class="disabled" onkeyup="validateNumber(this,this.value);" onblur="formatCurrencyPertama(this.value)"/>
      </a></td>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Cukai Semasa (RM)</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><div align="left">
        <input name="txtCukaiTerkini" type="text" id="txtCukaiTerkini" value="$txtCukaiTerkini" size="20" $readonly class="disabled" onkeyup="validateNumber(this,this.value);" onblur="formatCurrencySemasa(this.value)"/>
        <span class="style5"><br />
          <a href="javascript:viewTransaksiCukai('$idHakmilik')" class="style2" title="Papar transaksi cukai terdahulu,">Senarai cukai terdahulu.</a></span></div></td>
    <td width="24%"><div align="right">Sekatan Kepentingan</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%" rowspan="4"><textarea name="txtSekatan" cols="27" rows="5" id="txtSekatan" style="text-transform:uppercase;" readonly class="disabled">$txtSekatan</textarea></td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font> </i>Lokasi </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtLokasi" type="text" id="txtLokasi" value="$txtLokasi" size="30" readonly class="disabled" style="text-transform:uppercase;"/></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font> </i>Kegunaan Tanah</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%" rowspan="4"><textarea name="txtKegunaanTanah" cols="27" rows="5" id="txtKegunaanTanah" style="text-transform:uppercase;" readonly class="disabled">$txtKegunaanTanah</textarea></td>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="24%"><div align="right">Kementerian/Agensi Terkini</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtKemAgenTerkini" type="text" id="txtKemAgenTerkini" size="30" readonly class="disabled"/></td>
  </tr>
  <tr>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font></i> Status Sah</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><select name="socStatus" id="socStatus" style="width:200px;" $readonly class="$disabled" $disabled>
=======
      
      
>>>>>>> .r28125
            #if($socStatus == 'D')
<<<<<<< .mine
          		<option value="">SILA PILIH</option>
            	<option value="D" selected="selected">D - DAFTAR</option>
                <option value="P">P - BATAL PELEPASAN</option>
                <option value="T">T - BATAL TUKARGUNA</option>
            	<option value="S">S - BATAL SAMBUNGAN</option>    
            #elseif($socStatus == 'P')
                <option value="">SILA PILIH</option>
                <option value="D">D - DAFTAR</option>
                <option value="P" selected="selected">P- BATAL PELEPASAN</option>
                <option value="T">T - BATAL TUKARGUNA</option>
				<option value="S">S - BATAL SAMBUNGAN</option>
            #elseif($socStatus == 'S')
                <option value="">SILA PILIH</option>
                <option value="D">D - DAFTAR</option>
                <option value="P">BP - BATAL PELEPASAN</option>
                <option value="T">T - BATAL TUKARGUNA</option>
				<option value="S" selected="selected">S - BATAL SAMBUNGAN</option>    
            #elseif($socStatus == 'T')
                <option value="">SILA PILIH</option>
                <option value="D">D - DAFTAR</option>
                <option value="P">BP - BATAL PELEPASAN</option>
                <option value="T" selected="selected">T - BATAL TUKARGUNA</option>
				<option value="S">S - BATAL SAMBUNGAN</option>                      
=======
          		
      
      
      
      
      <option value="">SILA PILIH</option>
      <option value="D" selected="selected">D - DAFTAR</option>
      <option value="P">P - BATAL PELEPASAN</option>
      <option value="T">T - TUKARGUNA</option>
      <option value="S">S - BATAL SAMBUNGAN</option>
      
      
      
      
          
            #elseif($socStatus == 'P')
                
      
      
      
      
      <option value="">SILA PILIH</option>
      <option value="D">D - DAFTAR</option>
      <option value="P" selected="selected">P- BATAL PELEPASAN</option>
      <option value="T">T - TUKARGUNA</option>
      <option value="S">S - BATAL SAMBUNGAN</option>
      
      
      
      
      
            #elseif($socStatus == 'S')
                
      
      
      
      
      <option value="">SILA PILIH</option>
      <option value="D">D - DAFTAR</option>
      <option value="P">BP - BATAL PELEPASAN</option>
      <option value="T">T -TUKARGUNA</option>
      <option value="S" selected="selected">S - BATAL SAMBUNGAN</option>
      
      
      
      
          
            #elseif($socStatus == 'T')
                
      
      
      
      
      <option value="">SILA PILIH</option>
      <option value="D">D - DAFTAR</option>
      <option value="P">BP - BATAL PELEPASAN</option>
      <option value="T" selected="selected">T - TUKARGUNA</option>
      <option value="S">S - BATAL SAMBUNGAN</option>
      
      
      
      
                            
>>>>>>> .r28125
            #else
<<<<<<< .mine
                <option value="" selected="selected">SILA PILIH</option>
                <option value="D">D - DAFTAR</option>
                <option value="P">BP - BATAL PELEPASAN</option>
                <option value="T">T - BATAL TUKARGUNA</option>
				<option value="S" selected="selected">S - BATAL SAMBUNGAN</option>   
=======
                
      
      
      
      
      <option value="" selected="selected">SILA PILIH</option>
      <option value="D">D - DAFTAR</option>
      <option value="P">BP - BATAL PELEPASAN</option>
      <option value="T">T - BATAL TUKARGUNA</option>
      <option value="S" selected="selected">S - BATAL SAMBUNGAN</option>
      
      
      
      
         
>>>>>>> .r28125
        	#end
<<<<<<< .mine
        </select>    </td>
=======
        
    
    
    
    
    </select>
    </td>
>>>>>>> .r28125
  </tr>
  <tr>
<<<<<<< .mine
    <td width="24%"><div align="right"><i><font color="#ff0000">*</font></i> Kategori</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">$selectKategori</td>
    <td width="24%"><div align="right">Pegawai Akhir Kemaskini</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtPegawaiAkhir" type="text" class="disabled" id="txtPegawaiAkhir" value="$txtPegawaiAkhir" size="30" readonly /></td>
=======
    <td width="24%"><div align="right">No Hakmilik Asal</div></td>
    <td width="1%">:</td>
    <td width="24%"><input name="txtNoHakmilikAsal" type="text" id="txtNoHakmilikAsal" value="$txtNoHakmilikAsal" size="20" readonly="readonly" class="disabled" /></td>
    <td width="24%"><div align="right">Pegawai Akhir Kemaskini</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtPegawaiAkhir" type="text" class="disabled" id="txtPegawaiAkhir" value="$txtPegawaiAkhir" size="30" readonly="readonly" /></td>
>>>>>>> .r28125
  </tr>
  <tr>
<<<<<<< .mine
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="24%">&nbsp;</td>
    <td width="24%"><div align="right">Tarikh Akhir Kemaskini</div></td>
=======
    <td><div align="right">Kategori</div></td>
    <td><div align="center">:</div></td>
    <td>$selectKategori</td>
    <td width="24%"><div align="right">Tarikh Akhir Kemaskini</div></td>
>>>>>>> .r28125
    <td width="1%"><div align="center">:</div></td>
<<<<<<< .mine
    <td width="24%"><input name="txdTarikhKemaskini" type="text" id="txdTarikhKemaskini" value="$txdTarikhKemaskini" size="9" onkeyup="validateNumber(this,this.value);" readonly class="disabled"/></td>
=======
    <td width="24%"><input name="txdTarikhKemaskini" type="text" id="txdTarikhKemaskini" value="$txdTarikhKemaskini" size="9" onkeyup="validateNumber(this,this.value);" readonly="readonly" class="disabled"/></td>
>>>>>>> .r28125
  </tr>
  
  <tr>
<<<<<<< .mine
    <td colspan="6">&nbsp;</td>
=======
    <td width="24%"><div align="right">Unit Luas</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><select name="socLuas" id="socLuas" style="width:200px;" readonly="readonly" class="disabled" disabled="disabled" onchange="doChangeTaraf()">
      
      
      
      
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
      
      
      
      
         
       #elseif($socLuas == '')
        
      
      
      
      
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
    </td>
    <td>&nbsp;</td>
>>>>>>> .r28125
    <td>&nbsp;</td>
    <td width="24%">&nbsp;</td>
  </tr>
  <tr>
<<<<<<< .mine
    <td colspan="6">    </td>
=======
    <td width="24%"><div align="right">Luas</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><label></label>
        <input name="txtLuasLama" type="text" id="txtLuasLama" value="$txtLuasLama" size="15" readonly="readonly" class="disabled"/></td>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="24%">&nbsp;</td>
>>>>>>> .r28125
  </tr>
<<<<<<< .mine
  #if($socStatus=="S")
=======
>>>>>>> .r28125
  <tr>
<<<<<<< .mine
    <td colspan="6">
      <fieldset>
      <legend>SENARAI HAKMILIK SAMBUNGAN</legend>
      <table border="0" width="100%">
        
        <tr class="table_header">
          <td width="6%">Bil.</td>
          <td width="94%">No Hakmilik Sambungan</td>
        </tr>
        #foreach ($senarai in $listSambungan)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2")
        #else
        #set( $row = "row1")
        #end
        <tr class="$row">
          <td>$senarai.bil</td>
          <td>$senarai.kodJenisBaru $senarai.noHakmilikBaru</td>
          #if($senarai.bil!="")
          #else          #end        </tr>
        #end
      </table>
     </fieldset>     </td>
=======
    <td width="24%"><div align="right">Luas Bersamaan</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><input name="txtLuas" type="text" class="disabled" id="txtLuas" onkeyup="this.value=this.value.toUpperCase();" value="$txtLuas" size="20" readonly="readonly"/>
      (hektar) </td>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="24%">&nbsp;</td>
>>>>>>> .r28125
  </tr>
<<<<<<< .mine
  <tr>
  #end
      <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6">
      <div align="center">
      <input type="button" name="btnBackHakmilik" id="btnBackHakmilik" value="Kembali" onclick="kembaliHakmilik()"/>
      </div></td>
  </tr>
  </table>
</fieldset> 
    <p>&nbsp;</p>
=======
  <tr>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="24%">&nbsp;</td>
    <td width="24%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="24%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6"></td>
  </tr>
  #if($socStatus=='S')
  <tr>
    <td colspan="6"><fieldset>
      <legend>SENARAI HAKMILIK SAMBUNGAN</legend>
      <table border="0" width="100%">
        <tr class="table_header">
          <td width="8%">Bil.</td>
          <td width="55%">No Hakmilik Sambungan</td>
          <td width="37%">Tindakan</td>
        </tr>
        #foreach ($senarai in $listSambungan)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = 'row2')
        #else
        #set( $row = 'row1')
        #end
        <tr class="$row">
                  <td>$senarai.bil</td>
          <td>$senarai.kodJenisBaru $senarai.noHakmilikBaru</td>
          #if($senarai.bil!='')
          <td><div align="left" class="style2 style3"><a href="javascript:deleteHakmilikBaru('$senarai.idHakmilikAsal','$senarai.idHakmilikBaru')" class="style1">Hapus</a></div></td>
          #else
          <td>&nbsp;</td>
          #end </tr>
        #end
      </table>
    </fieldset></td>
  </tr>
  #end
  <tr>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6"><div align="center">
      <input type="button" name="btnBackHakmilik" id="btnBackHakmilik" value="Kembali" onclick="kembaliHakmilik()"/>
    </div></td>
  </tr>
</table>
>>>>>>> .r28125
<p>&nbsp;</p>
</fieldset>
<<<<<<< .mine
<script>
function kemaskini_detailHakmilik(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=kemaskiniDetailHakmilik&idHakmilik="+id;
	document.${formName}.submit();
	//doAjaxCall${formName}("kemaskiniDetailHakmilik","idHakmilik="+id);
}
function update_detailHakmilik(id){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
	   return;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=updateDetailHakmilik&idHakmilik="+id;
	document.${formName}.submit();
	//doAjaxCall${formName}("updateDetailHakmilik","idHakmilik="+id);
}
function tambahHakmilikSambungan(id){
	document.${formName}.modePopup.value="openSambungan";
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=addHakmilikBaru&idHakmilik="+id;
	document.${formName}.submit();
	//doAjaxCall${formName}("kemaskiniDetailHakmilik","idHakmilik="+id);
}
function hakmilikBaru(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=daftarHakmilik&idHakmilik="+id;
	document.${formName}.submit();
	//doAjaxCall${formName}("kemaskiniDetailHakmilik","idHakmilik="+id);
}
function deleteHakmilikBaru(id,id2){
if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=deleteHakmilikBaru&idHakmilik="+id+"&idHakmilikBaru="+id2;
	document.${formName}.submit();
}
function viewTransaksiCukai(id) {
	var url = "../x/${securityToken}/ekptg.view.htp.FrmRekodTransaksiCukai?idHakmilik="+id;
    var hWnd = window.open(url,'printuser','width=300,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function kembaliHakmilik(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodBatalHakmilik&firstAction=carianHakmilikRizab";	
	document.${formName}.submit();
}
</script>
=======
>>>>>>> .r28125
