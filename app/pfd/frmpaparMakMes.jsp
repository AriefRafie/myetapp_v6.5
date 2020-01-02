
<style type="text/css">
<!--
.style2 {font-size: 10px}
.style40 {color: #FF0000}
.style6 {
	font-size: 9px;
	font-style: italic;
}
.style42 {color: #000000}
-->
</style>
<div id=content>

<input type="hidden" name="mode" value="$mode" />
<input type="hidden" name="tabId" id="tabId" value="$!selectedTab"/>
<input type="hidden" name="idMesyuarat" id="idMesyuarat"  value="$!idMesyuarat"/>
<input type="hidden" name="action"  id="action" value="$!action"/>
<input type="hidden" name="OidMesyuarat" value="$!OidMesyuarat" />


&nbsp;
<table width="100%" border="1" cellspacing="0">
  <tr>
    <td>

    <fieldset>
          <legend>MAKLUMAT MESYUARAT</legend>
      #foreach ($mesyuaratAdhock in $MesyuaratAdhock) 
          <input type="hidden" name="idMesyuaratA"  id="idMesyuaratA" value="$mesyuaratAdhock.id_Mesyuarat"/>
   <table width="100%" style="vertical-align:top">
  <tr><td width="50%">
  <table width="100%">
  <tr><td valign="TOP">
         <table width="100%">
  <tr>
    <td width="29%" scope="row"><div align="right" class="style4">
      <div align="left">Bil Mesyuarat</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <label>#if ($listMesyuarat.kategori_Mesyuarat =='2')
      #else
        <input type="text" name="txtBilMsyrtA" id="txtBilMsyrtA" value="$mesyuaratAdhock.bil_Mesyuarat" $readOnly class="disabled" />
      #end        </label>       </td>
  </tr>
  <tr>
    <td scope="row" valign="top"><div align="right" class="style4">
      <div align="left">Tajuk Mesyuarat</div>
    </div></td>
    <td width="1%" scope="row" valign="top"><div align="right">:</div></td>
    <td>
      <label>
        <textarea name="txtTajukMsyrtA" cols="41" id="txtTajukMsyrtA" onkeyup="this.value=this.value.toUpperCase()" class="disabled" $readOnly>$mesyuaratAdhock.tajuk_Mesyuarat</textarea>
        </label>        </td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style3">
      <div align="left">Tarikh</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td>
      <label>
        <input name="txdTarikhA" type="text" id="txdTarikhA" value="$mesyuaratAdhock.tarikh_Mesyuarat" size="10" class="disabled"  readonly="readonly"/>
        </label>        </td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style3">
      <div align="left">Dari Jam</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td>
      <label>
        <input type="text" name="txtDariJamA" class="disabled"  id="txtDariJamA" value="$mesyuaratAdhock.masa_Mesyuarat_Dari" width="80" $readOnly/>
        </label>
      <label>
      <select name="socWaktuDariJamA" id="socWaktuDariJamA" style="width:auto" class="disabled" disabled >
      #if ($mesyuaratAdhock.waktu_Mesyuarat_Dari == '0')
        <option value="0" selected="selected" >-Sila Pilih-</option>
        <option value="1">PAGI</option>
        <option value="2">PETANG</option>
        <option value="3">MALAM</option>
      
      #elseif ($mesyuaratAdhock.waktu_Mesyuarat_Dari == '1')
        <option value="0">-Sila Pilih-</option>
        <option value="1" selected="selected">PAGI</option>
        <option value="2">PETANG</option>
        <option value="3">MALAM</option>
      
      #elseif ($mesyuaratAdhock.waktu_Mesyuarat_Dari == '2')
        <option value="0">-Sila Pilih-</option>
        <option value="1">PAGI</option>
        <option value="2" selected="selected">PETANG</option>
        <option value="3">MALAM</option>
      
      #elseif ($mesyuaratAdhock.waktu_Mesyuarat_Dari == '3') 
        <option value="0">-Sila Pilih-</option>
        <option value="1">PAGI</option>
        <option value="2">PETANG</option>
        <option value="3" selected="selected">MALAM</option>
      #end
      </select>
      </label></td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style3">
      <div align="left">Hingga Jam</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td>
      <label>
        <input type="text" name="txtHinggaJamA" class="disabled"  id="txtHinggaJamA" value="$mesyuaratAdhock.masa_Mesyuarat_Hingga" width="80" $readOnly/>
        </label>
      <label>
      <select name="socWaktuHinggaJamA" id="socWaktuHinggaJamA" style="width:auto" class="disabled" disabled>
        #if ($mesyuaratAdhock.waktu_Mesyuarat_Hingga == '0')
        <option value="0" selected="selected">-Sila Pilih-</option>
        <option value="1">PAGI</option>
        <option value="2">PETANG</option>
        <option value="3">MALAM</option>
     
      #elseif ($mesyuaratAdhock.waktu_Mesyuarat_Hingga == '1')
        <option value="0">-Sila Pilih-</option>
        <option value="1" selected="selected">PAGI</option>
        <option value="2">PETANG</option>
        <option value="3">MALAM</option>
      
      #elseif ($mesyuaratAdhock.waktu_Mesyuarat_Hingga == '2')
        <option value="0">-Sila Pilih-</option>
        <option value="1">PAGI</option>
        <option value="2" selected="selected">PETANG</option>
        <option value="3">MALAM</option>
     
      #elseif ($mesyuaratAdhock.waktu_Mesyuarat_Hingga == '3') 
        <option value="0">-Sila Pilih-</option>
        <option value="1">PAGI</option>
        <option value="2">PETANG</option>
        <option value="3" selected="selected">MALAM</option>
      #end
      </select>
      </label></td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style3">
      <div align="left">Lokasi</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td>
      <label></label>
     $selectLokasiAdhock</td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style3">
      <div align="left">Status Mesyuarat</div>
    </div></td>
   <td width="1%" scope="row"><div align="right">:</div></td>
    <td>
      <label></label>
      $selectStatusAdhock</td>
  </tr>
    <tr>
    <td scope="row"><div align="right" class="style3">
      <div align="left">Urusetia / Seksyen </div>
    </div></td>
    <td scope="row"><div align="right">:</div></td>
    <td>
      <label></label>
        $selectSeksyenAdhock</td>
  </tr>
  <tr>
    <td scope="row" valign="top"><div align="left"></div></td>
    <td scope="row" valign="top">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
 </table>
 
 </td><td valign="top" width="50%">
 <table width="100%" align="center">
   <tr valign="top">
 <td scope="row" width="29%"><div align="right" class="style3">
      <div align="left">Nama Pegawai</div>
    </div></td>
    <td scope="row" width="1%"><div align="right">:</div></td>
    <td width="70%">
      <label></label>
      $selectPegawaiAdhock</td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style3">
      <div align="left">No Fail</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td>

      <label>
        <input type="text" name="txtNamaFailA" class="disabled"  id="txtNamaFailA" value="$mesyuaratAdhock.nama_fail" width="80" $readOnly/>
      </label>
<!--<label>$selectFail</label>-->     </td>
  </tr>
  <tr>
    <td scope="row" valign="top"><div align="right" class="style3">
      <div align="left">Catatan</div>
    </div></td>
    <td width="1%" scope="row" valign="top"><div align="right">:</div></td>
    <td>
      <label>
        <textarea name="txtCatatanA" cols="41" class="disabled"  id="txtCatatanA" $readOnly onChange="javascript:this.value=ucwords(this.value)">$mesyuaratAdhock.catatan</textarea>
        </label>    </td>
  </tr>
  <tr>
  <td scope="row" valign="top"><div align="right" class="style3">
    <div align="left"><span class="style6"></span>Disediakan Oleh </div>
  </div></td>
    <td scope="row" valign="top"><div align="right">:</div></td>
    <td>    <label>
        <input type="text" name="txtNamaPendaftarA" class="disabled"  id="txtNamaPendaftarA" value="$mesyuaratAdhock.nama_pendaftar" width="80" $readOnly/>
    </label></td>
  </tr>
      <tr>
    <td scope="row" valign="top"><div align="right" class="style3">
      <div align="left"><span class="style6"></span>Disemak Oleh </div>
    </div></td>
    <td scope="row" valign="top"><div align="right">:</div></td>
    <td>    <label>
        <input type="text" name="txtNamaPenyemakA" class="disabled"  id="txtNamaPenyemakA" value="$mesyuaratAdhock.nama_penyemak" width="80" $readOnly/>
    </label></td>
    </tr>
    <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
    <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
        <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
            <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
    <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
    <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
        <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
            <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table>
 </td></tr>
 </table>
 
    </td>
  </tr>
  <tr><td></td></tr>
 </table>
 
#end 
    </fieldset> 
    <fieldset>
  <legend>SENARAI AHLI MESYUARAT</legend>
  #if ($mode == 'View')
        <label>
        <input type="button" name="cmdTambah2" id="cmdTambah2" value="Kemaskini Ahli Mesyuarat" onclick="tambahAhli('1','tambah')"/>
        </label>
  #end
  <label></label>
<table width="100%" >
  <tr class="table_header">
    <td width="1%" scope="row">NO</td>
    <td width="20%">AHLI MESYUARAT DALAMAN</td>
    <td width="10%">SEKSYEN</td>
    <td width="20%">AHLI MESYUARAT LUARAN</td>
    <td width="10%">AGENSI LUAR</td>
  </tr>
  #foreach ($ahli in $SenaraiAhliMesyuarat)
  #if ($ahli.bil == '') 
  #set ($row = 'row1')
  #elseif ($ahli.bil % 2 != 0)
  #set ($row = 'row1')
  #else 
  #set ($row = 'row2')
  #end
  <tr>
    <td class="$row">$ahli.bil</td>
    #if ($ahli.bil != '')
    <td class="$row">$ahli.nama_Pegawai</td>
    #else
    <td class="$row">$ahli.nama_Pegawai</td>
    #end
    <td class="$row">$ahli.kod_Seksyen</td>
    <td class="$row"><!--<a href="javascript:papar_ahli('$ahli.id_Ahlimesyuarat','papar')" class="style1">$ahli.nama_Ahlimesyuarat</a>-->$ahli.nama_Ahlimesyuarat</td>
    <td class="$row">$ahli.agensi_Luar</td>
  </tr>
  #end
</table>
</fieldset>


</table>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-02</strong></td>
  </tr>
</table>


<script type="text/javascript">
</script>
