
<style type="text/css">
<!--
.style40 {color: #FF0000}
.style6 {
	font-size: 9px;
	font-style: italic;
}
.style44 {color: #0033FF}
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



    <div class="TabbedPanelsContent" style="$display2">
    <fieldset>
      <legend>MAKLUMAT MESYUARAT </legend>
          <input type="hidden" name="idMesyuaratA"  id="idMesyuaratA" value="$mesyuaratAdhock.id_Mesyuarat"/>
      <table width="100%">

  <tr>
    <td width="29%" scope="row"><div align="right" class="style4">
      <div align="left">Bil Mesyuarat</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <label><span class="style44">$!bil_Mesyuarat</span></label></td>
  </tr>
  <tr>
    <td scope="row" valign="top"><div align="right" class="style4">
      <div align="left"><span class="style40"> </span>Tajuk Mesyuarat</div>
    </div></td>
    <td width="1%" scope="row" valign="top"><div align="right">:</div></td>
    <td>
      <span class="style44">
      <label>
       $!tajuk_Mesyuarat        </label>
      </span> </td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style3">
      <div align="left"><span class="style40"> </span>Tarikh</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td>
      <label><span class="style44">
      $!tarikh_Mesyuarat      </span></label>   </td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style3">
      <div align="left"><span class="style40"> </span>Dari Jam</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td>
      <span class="style44">
      <label>
       $!masa_Mesyuarat_Dari      </label>
      <label>

      #if ($waktu_Mesyuarat_Dari == '0')
      		--
      #elseif ($waktu_Mesyuarat_Dari == '1')
			PAGI      
      #elseif ($waktu_Mesyuarat_Dari == '2')
 			PETANG
      #elseif ($waktu_Mesyuarat_Dari == '3') 
			MALAM
      #end      </label>
      </span></td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style3">
      <div align="left"><span class="style40"> </span>Hingga Jam</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td>
      <span class="style44">
      <label>
        $!masa_Mesyuarat_Hingga        </label>
      <label>
       #if ($waktu_Mesyuarat_Hingga == '0')
     
      #elseif ($waktu_Mesyuarat_Hingga == '1')
 			PAGI 
      #elseif ($waktu_Mesyuarat_Hingga == '2')
			PETANG      
      #elseif ($waktu_Mesyuarat_Hingga == '3') 
			MALAM 
      #end      </label>
      </span></td>
  </tr>
<!--  <tr>
    <td scope="row"><div align="right" class="style3"><span class="style40">*</span>Lokasi</div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td>
      <label></label>
     $selectLokasiAdhock</td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style3"><span class="style40">*</span>Status Mesyuarat</div></td>
   <td width="1%" scope="row"><div align="right">:</div></td>
    <td>
      <label></label>
      $selectStatusAdhock</td>
  </tr>
    <tr>
    <td scope="row"><div align="right" class="style3"><span class="style40">*</span>Urusetia / Seksyen </div></td>
    <td scope="row"><div align="right">:</div></td>
    <td>
      <label></label>
        $selectSeksyenAdhock</td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style3"><span class="style40">*</span>Nama Pegawai</div></td>
    <td scope="row"><div align="right">:</div></td>
    <td>
      <label></label>
      $selectPegawaiAdhock</td>
  </tr>-->
  <tr>
    <td scope="row"><div align="right" class="style3">
      <div align="left">No Fail</div>
    </div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td>

      <span class="style44">
<label>
      $!nama_fail      </label>
<!--<label>$selectFail</label>-->     
      </span></td>
  </tr>
  <tr>
    <td scope="row" valign="top"><div align="right" class="style3">
      <div align="left">Catatan</div>
    </div></td>
    <td width="1%" scope="row" valign="top"><div align="right">:</div></td>
    <td>
      <span class="style44">
      <label>
   $!catatan        </label>
      </span> </td>
  </tr>
  <tr>
  <td scope="row" valign="top"><div align="right" class="style3">
    <div align="left"><span class="style6"></span>Disediakan Oleh </div>
  </div></td>
    <td scope="row" valign="top"><div align="right">:</div></td>
    <td>    <span class="style44">
      <label>
       $!nama_pendaftar      </label>
    </span></td>
  </tr>
      <tr>
    <td scope="row" valign="top"><div align="right" class="style3">
      <div align="left"><span class="style6"></span>Disemak Oleh </div>
    </div></td>
    <td scope="row" valign="top"><div align="right">:</div></td>
    <td>    <span class="style44">
      <label>
        $!nama_penyemak      </label>
    </span></td>
  </tr>
  <tr>
    <td scope="row" valign="top">&nbsp;</td>
    <td scope="row" valign="top">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>


  <!--legend>SENARAI AHLI MESYUARAT</legend-->
  #if ($mode == 'View')
        <label>
        <input type="button" name="cmdTambah2" id="cmdTambah2" value="Kemaskini Ahli Mesyuarat" onclick="tambahAhli('1','tambah')"/>
        </label>
  #end
  <label></label>
<table width="100%" cellpadding="2" cellspacing="0">
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
    <td class="$row">$ahli.nama_Ahlimesyuarat</td>
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



