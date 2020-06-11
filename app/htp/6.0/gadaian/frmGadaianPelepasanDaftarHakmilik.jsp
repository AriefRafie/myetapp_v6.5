#set ($TajukFail = "")
#set ($NoFail = "")
#set ($NoRujukanLain = "")
#set ($IdNegeri = "")
#set ($NoHakmilik = "")
#set ($NoLot = "")
#set ($TarikhMula = "")
#set ($Cukai = "")
#set ($Lokasi = "")
#set ($NoPelan = "")
#set ($Syarat = "")
#set ($TarikhLuput = "")
#set ($CukaiTerkini = "")
#set ($Luas = "")
#set ($TarikhRizab = "")
#set ($NoRizab = "")
#set ($Noptk = "")
#set ($NoSyit = "")
#set ($Sekatan = "")
#set ($IdHakmilikurusan = "")
#set($TarikhLepasGadai = "")
#foreach ( $penHamilik in $PenHamilik )
	#set ($TajukFail = $penHamilik.TajukFail)
    #set ($NoFail = $penHamilik.NoFail)
    #set ($NoRujukanLain = $penHamilik.NoRujukanLain)
    #set ($IdNegeri = $penHamilik.IdNegeri)
	#set ($NoHakmilik = $penHamilik.NoHakmilik)
    #set ($NoLot = $penHamilik.NoLot)
    #set ($TarikhMula = $penHamilik.TarikhMula)
    #set ($Cukai = $penHamilik.Cukai)
    #set ($Lokasi = $penHamilik.Lokasi)
    #set ($NoPelan = $penHamilik.NoPelan)
    #set ($Syarat = $penHamilik.Syarat)
    #set ($TarikhLuput = $penHamilik.TarikhLuput)
    #set ($CukaiTerkini = $penHamilik.CukaiTerkini)
    #set ($Luas = $penHamilik.Luas)
    #set ($TarikhRizab = $penHamilik.TarikhRizab)
    #set ($NoRizab = $penHamilik.NoRizab)
    #set ($Noptk = $penHamilik.Noptk)
    #set ($NoSyit = $penHamilik.NoSyit)
    #set ($Sekatan = $penHamilik.Sekatan)
    #set ($IdHakmilikurusan = $penHamilik.IdHakmilikurusan)
    #set($TarikhLepasGadai = $penHamilik.TarikhLepasGadai)
#end

#set ($btnName = "value='Kosongkan'")
#if ($IdHakmilikurusan != "")
	#set ($btnName = "value='Batal'")
#end

<br />
<fieldset>
<legend><strong>PENDAFTARAN HAKMILIK</strong></legend>
<br/>
<fieldset>
<legend><strong>MAKLUMAT FAIL</strong></legend>
<table width="100%" border="0">
  <tr>
    <td>No Fail Seksyen</td>
    <td>:</td>
    <td><font color="blue">$NoFail</font></td>
  </tr>
  <tr>
    <td>No Fail Lain</td>
    <td>:</td>
    <td><font color="blue">$NoRujukanLain</font></td>
  </tr>
  <tr>
    <td width="22%">Kementerian</td>
    <td width="1%">:</td>
    <td width="77%"><font color="blue">$selectKementerian</font></td>
  </tr>
  <tr>
    <td>Agensi</td>
    <td>:</td>
    <td><font color="blue">$selectAgensi</font></td>
  </tr>
  <tr>
    <td>Tajuk</td>
    <td>:</td>
    <td><font color="blue">$TajukFail</font></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</fieldset>
<br/>
<fieldset>
<legend><strong>MAKLUMAT HAKMILIK</strong></legend>
<table width="100%" border="0">
  <tr>
    <td width="25%">Negeri</td>
    <td width="1%">:</td>
    <td width="24%"><font color="blue">$selectNegeri</font>
      <input type="hidden" name="idNegeri" value="$IdNegeri" /></td>
    <td><font color="#FF0000">*</font>Jenis Hakmilik</td>
    <td>:</td>
    <td><span class="mediumselect">$selectJenisHakmilik</span></td>
    </tr>
  <tr>
    <td><font color="#FF0000">*</font>Daerah</td>
    <td>:</td>
    <td>$selectDaerah</td>
    <td><font color="#FF0000">*</font>No. Hakmilik</td>
    <td>:</td>
    <td><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" maxlength="21" onkeyup="this.value=this.value.toUpperCase();" value="$NoHakmilik" $modeSoc $classDis /></td>
    </tr>
  <tr>
    <td><font color="#FF0000">*</font>Bandar/Pekan/Mukim</td>
    <td>:</td>
    <td>$selectMukim</td>
    <td><font color="#FF0000">*</font>Kod Lot</td>
    <td>:</td>
    <td>$selectLot</td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><font color="#FF0000">*</font>No. Lot</td>
    <td>:</td>
    <td><input type="text" name="txtNoLot" id="txtKodLot" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" value="$NoLot" $modeSoc $classDis /></td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
</table>
<hr/>
<table width="100%" border="0">
  <tr>
    <td width="28%">Tarikh Terima Hakmilik</td>
    <td width="1%">:</td>
    <td width="25%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="15" value="$TarikhMula" $classDis onblur="check_date(this)" />
      <img src="../img/calendar.gif" alt="calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');" /></td>
    <td width="18%">Tarikh Warta</td>
    <td width="1%">:</td>
    <td width="27%"><input type="text" name="txdTarikhWarta" id="txdTarikhWarta" size="15" value="$TarikhRizab" $classDis onblur="check_date(this)" />
      <img src="../img/calendar.gif" alt="calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTarikhWarta',false,'dmy');" /></td>
  </tr>
  <tr>
    <td>&nbsp; Tarikh Luput Hakmilik</td>
    <td>:</td>
    <td><input type="text" name="txdTarikhLuput" id="txdTarikhLuput" size="15" value="$TarikhLuput" $classDis onblur="check_date(this)" />
      <img src="../img/calendar.gif" alt="calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTarikhLuput',false,'dmy');" /></td>
    <td>No.Warta </td>
    <td>:</td>
    <td><input type="text" name="txtNoWarta" id="txtNoWarta" maxlength="10" onkeyup="this.value=this.value.toUpperCase();" value="$NoRizab" $modeSoc $classDis /></td>
  </tr>
  <tr>
    <td>&nbsp; Cukai Tahun Pertama (RM)</td>
    <td>:</td>
    <td><label>
        <input type="text" name="txtCukaiPertama" id="txtCukaiPertama" maxlength="9" size="17" onkeyup="validateCurrency(this,this.value);" value="$Cukai" $modeSoc $classDis />
      </label></td>
    <td> No. Pelan Akui</td>
    <td>:</td>
    <td><input type="text" name="txtNoPelan" id="txtNoPelan" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$NoPelan" $modeSoc $classDis /></td>
    </tr>
  <tr>
    <td>&nbsp; Cukai Semasa (RM)</td>
    <td>:</td>
    <td><label>
        <input type="text" name="txtCukaiSemasa" id="txtCukaiSemasa" maxlength="9" size="17" onkeyup="validateCurrency(this,this.value);" value="$CukaiTerkini" $modeSoc $classDis />
      </label></td>
    <td>Rizab</td>
    <td>:</td>
    <td>$selectRizab</td>
    </tr>
  <tr>
    <td>&nbsp; Lokasi</td>
    <td>:</td>
    <td><input type="text" name="txtLokasi" id="txtLokasi" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$Lokasi" $modeSoc $classDis /></td>
    <td>Kategori </td>
    <td>:</td>
    <td>$selectKategori</td>
    </tr>
  <tr>
    <td>&nbsp; Luas :</td>
    <td>:</td>
    <td><input type="text" name="txtLuas" id="txtLuas" maxlength="40" onkeyup="validateCurrency(this,this.value);" value="$Luas" $modeSoc $classDis /></td>
    <td> No. PU :</td>
    <td>:</td>
    <td><input type="text" name="txtNoPU" id="txtNoPU" maxlength="5" onkeyup="this.value=this.value.toUpperCase();" value="$Noptk" $modeSoc $classDis /></td>
    </tr>
  <tr>
    <td>&nbsp; Unit Luas </td>
    <td>:</td>
    <td>$selectLuas</td>
    <td> No. Syit Piawai </td>
    <td>:</td>
    <td><input type="text" name="txtNoSyit" id="txtNoSyit" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$NoSyit" $modeSoc $classDis /></td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<!--
##if($lepasGadai == 'SOC')
<fieldset>
<legend>Pelepasan Gadaian</legend>
<table width="100%" border="0">
  <tr>
    <td width="34%"><strong>Tarikh Pelepasan Gadaian</strong></td>
    <td width="1%"><strong>:</strong></td>
    <td width="65%"><input type="text" name="txTarikhLepasGadai" id="txTarikhLepasGadai" size="15" value="$TarikhLepasGadai" />
      <img src="../img/calendar.gif" alt="calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txTarikhLepasGadai',false,'dmy');" /></td>
  </tr>
</table>
</fieldset>
##end
<br/>
-->

<fieldset>
<p>&nbsp;</p>
<legend>SYARAT DAN SEKATAN</legend>
<table width="100%" border="0">
  <tr>
    <td width="27%" valign="top">Syarat Nyata </td>
    <td width="1%" valign="top">:</td>
    <td width="23%"><textarea name="txtSyarat" id="txtSyarat" cols="45" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc $classDis >$Syarat</textarea></td>
    <td width="18%" valign="top">Sekatan Kepentingan</td>
    <td width="1%" valign="top">:</td>
    <td width="30%"><textarea name="txtSekatan" id="txtSekatan" cols="40" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc $classDis >$Sekatan</textarea></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</fieldset>
<br/>

#if($SOC == 'SOC')
<fieldset>
<legend>PELEPASAN GADAIAN</legend>
<table width="100%" border="0">
  <tr>
    <td width="31%">Tarikh Pelepasan Gadaian</td>
    <td width="1%">:</td>
    <td width="68%"><input type="text" name="txdTarikhLepasGadai" id="txdTarikhLepasGadai" size="15" value="$TarikhLepasGadai" $classDis onblur="check_date(this)" />
      <img src="../img/calendar.gif" alt="calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTarikhLepasGadai',false,'dmy');" /></td>
    </tr>
</table>
</fieldset>
<br/>
#end

<table width="100%" border="0">
  <tr>
    <td align="center">
    #if($pagemode == 'baru' || $pagemode == 'kemaskini')

        &nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanPelepasan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fGPHA_Batal()">&nbsp;&nbsp;&nbsp;&nbsp;
        
 	#elseif($pagemode == 'simpan')
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniPelepasan">&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnya()">
        
  	#else
        
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGPHA_Kembali()">&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnya()">
        
   	#end
      </td>
  </tr>
</table>
</fieldset>

  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="noFail" value="$NoFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">

  
  
</fieldset>
