<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
&nbsp;
<fieldset>
<legend>MAKLUMAT MINIT ARAHAN</legend>
<input name="action1" type="hidden" value="$action1" />
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="" />
<input name="modePAR" type="hidden" value="$modePAR" />
<input name="mode" type="hidden" value="$mode" />
<input name="idLogDokumenKPTG" type="hidden" value="$idLogDokumenKPTG" />
<input name="idMinitArahanSeksyen1" type="hidden" value="$idMinitArahanSeksyen1" />
<input name="idMinitArahanSeksyen2" type="hidden" value="$idMinitArahanSeksyen2" />
<input name="idMinitArahanSeksyen3" type="hidden" value="$idMinitArahanSeksyen3" />
<input name="no_Rujukan_Dokumen" type="hidden" value="$no_Rujukan_Dokumen" />


<table width="100%">
   <tr>
     <td width="1%" scope="row" valign="top">&nbsp;</td>
     <td width="28%" scope="row" valign="top">No Rujukan Dokumen</td>
     <td width="1%" scope="row" valign="top">:</td>
     <td width="70%">$no_Rujukan_Dokumen</td>
   </tr>
   <tr>
     <td scope="row" valign="top">&nbsp;</td>
     <td scope="row" valign="top">Jenis Dokumen</td>
     <td scope="row" valign="top">:</td>
     <td >
		#if ($jenis_Dokumen == '1')
		SURAT
        #elseif ($jenis_Dokumen == '2')
		MEMO
        #elseif ($jenis_Dokumen == '3')
        DOKUMEN TERPERINGKAT
        #else        
        #end</td>
  </tr>
   <tr>
     <td align="left" valign="top" scope="row">&nbsp;</td>
     <td align="left" valign="top" scope="row">Pegawai Yang Memberi Arahan</td>
     <td scope="row" valign="top">:</td>
     <td>$user_Name
     <input name="user_Id" type="hidden" value="$user_Id" /></td>
   </tr>
  <tr>
    <td  align="left" valign="top" scope="row"><span class="style1">*</span></td>
  <input name="idMinitArahan" type="hidden" value="$!id_Minitarahan" />
    <td  align="left" valign="top" scope="row">Minit Arahan</td>
    <td  scope="row" valign="top">:</td>
    <td >
    <label>
        <textarea name="minit_Arahan" cols="41" onkeyup="this.value=this.value.toUpperCase()" id="minit_Arahan" $readOnlyMinit class="$disabledMinit">$!minit_Arahan</textarea>
        </label>     </td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Tarikh Minit Arahan</td>
   <td scope="row">:</td>
    <td>
      <label>
        <input name="tarikh_Minit_Arahan" type="text" id="tarikh_Minit_Arahan" value="$!tarikh_Minit_Arahan" size="10" $readOnlyMinit class="$disabledMinit"/>
      </label>
    <a href="javascript:displayDatePicker('tarikh_Minit_Arahan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row"><div align="right">Minit Kepada </div></td>
    <td scope="row">:</td>
    <td>
    <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="1 Seksyen" onclick = "tambahMinit1()" />
    <input type="button" name="cmdSimpan3" id="cmdSimpan3" value="2 Seksyen" onclick = "tambahMinit2()" />
    <input type="button" name="cmdSimpan4" id="cmdSimpan4" value="3 Seksyen" onclick = "tambahMinit3()" /></td>
  </tr>
</table>
</fieldset>
#if($modeSeksyen == '1')
#if($mode== 'ViewSeksyen1')
&nbsp;
<fieldset>
<legend>ARAHAN KE SEKSYEN LAIN</legend>
<input name="action1" type="hidden" value="$action1" />
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<input name="listPengarahSeksyen" type="hidden" value="$listPengarahSeksyen" />
<table width="100%">
  <tr>
    <td width="2%" align="left" valign="top" scope="row">&nbsp;</td>
    <td width="27%" align="left" valign="top" scope="row">Seksyen</td>
    <td width="1%" valign="top" scope="row">:</td>
<td width="70%"><label>$selectSeksyen1</label></td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah1</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR1</td>
  </tr>
  <tr>
    <td colspan="4" scope="row">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" scope="row" width="30%">&nbsp;</td>
    <td align="left" scope="row" width="70%"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniSeksyen1()" />
    <input type="button" name="cmdKembali5" id="cmdKembali5" value="Kembali" onclick="kembali()" /></td>
  </tr>
</table>
</fieldset>
#elseif($mode== 'KemaskiniSeksyen1' && $action1 == 'kemaskiniSeksyen1')
&nbsp;
<fieldset>
<legend>ARAHAN KE SEKSYEN LAIN</legend>
<input name="action1" type="hidden" value="$action1" />
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<input name="listPengarahSeksyen" type="hidden" value="$listPengarahSeksyen" />
<table width="100%">
  <tr>
    <td width="2%" align="left" valign="top" scope="row"><span class="style1">*</span></td>
    <td width="27%" align="left" valign="top" scope="row">Seksyen</td>
    <td width="1%" valign="top" scope="row">:</td>
<td width="70%"><label>$selectSeksyen1</label></td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR1</td>
  </tr>
  <tr>
    <td colspan="4" scope="row">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" scope="row" width="30%">&nbsp;</td>
    <td align="left" scope="row"  width="70%"><input type="button" name="cmdKemaskini2" id="cmdKemaskini2" value="Simpan" onclick="simpanKemaskiniSeksyen1()" />
    <input type="button" name="cmdKembali5" id="cmdKembali5" value="Batal" onclick="batalKemaskiniSeksyen1()" /></td>
  </tr>
</table>
</fieldset>
#else
&nbsp;
<fieldset>
<legend>ARAHAN KE SEKSYEN LAIN</legend>
<input name="action1" type="hidden" value="$action1" />
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<input name="listPengarahSeksyen" type="hidden" value="$listPengarahSeksyen" />
<table width="100%">
  <tr>
    <td width="2%" align="left" valign="top" scope="row"><span class="style1">*</span></td>
    <td width="27%" align="left" valign="top" scope="row">Seksyen</td>
    <td width="1%" valign="top" scope="row">:</td>
<td width="70%">$selectSeksyen1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR1</td>
  </tr>
  <tr>
    <td colspan="4" scope="row">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" scope="row" width="30%">&nbsp;</td>
    <td align="left" scope="row" width="70%"><input type="button" name="cmdSimpan5" id="cmdSimpan5" value="Simpan" onclick = "simpanSeksyen1()" />
      <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembali()" /></td>
  </tr>
</table>
</fieldset>
#end
#end
#if($modeSeksyen == '2')
#if($mode == 'ViewSeksyen2')
&nbsp;
<fieldset>
<legend>ARAHAN KE SEKSYEN LAIN</legend>
<input name="action1" type="hidden" value="$action1" />
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<input name="listPengarahSeksyen" type="hidden" value="$listPengarahSeksyen" />
<table width="100%">
  <tr>
    <td width="1%" align="left" scope="row">&nbsp;</td>
    <td width="28%" align="left" scope="row"><strong>Seksyen Pertama :</strong></td>
    <td width="1%" scope="row">&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
   <tr>
     <td  align="left" scope="row">&nbsp;</td>
    <td  align="left" scope="row">Seksyen</td>
    <td  scope="row">:</td>
    <td >$selectSeksyen1</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah1</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row"><strong>Seksyen Ke-Dua</strong></td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
    <tr>
      <td  align="left" scope="row">&nbsp;</td>
    <td  align="left" scope="row">Seksyen</td>
    <td  scope="row">:</td>
    <td >$selectSeksyen2</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah2</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR2</td>
  </tr>
  <tr>
    <td colspan="4" scope="row">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" scope="row" width="30%">&nbsp;</td>
    <td align="left" scope="row" width="70%"><input type="button" name="cmdKemaskini3" id="cmdKemaskini3" value="Kemaskini" onclick="kemaskiniSeksyen2()" />
      <input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembali()" /></td>
  </tr>
</table>
</fieldset>
#elseif($mode== 'KemaskiniSeksyen2' && $action1 == 'kemaskiniSeksyen2')
<fieldset>
<legend>ARAHAN KE SEKSYEN LAIN</legend>
<input name="action1" type="hidden" value="$action1" />
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<input name="listPengarahSeksyen" type="hidden" value="$listPengarahSeksyen" />
<table width="100%">
  <tr>
    <td width="1%" align="left" scope="row">&nbsp;</td>
    <td width="28%" align="left" scope="row"><strong>Seksyen Pertama :</strong></td>
    <td width="1%" scope="row">&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
   <tr>
     <td width="1%" align="left" scope="row"><span class="style1">*</span></td>
    <td width="28%" align="left" scope="row">Seksyen</td>
    <td width="1%" scope="row">:</td>
    <td width="70%">$selectSeksyen1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row"><strong>Seksyen Ke-Dua</strong></td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
    <tr>
      <td width="1%" align="left" scope="row"><span class="style1">*</span></td>
    <td width="28%" align="left" scope="row">Seksyen</td>
    <td width="1%" scope="row">:</td>
    <td width="70%">$selectSeksyen2</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah2</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR2</td>
  </tr>
  <tr>
    <td colspan="4" scope="row">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" scope="row" width="30%">&nbsp;</td>
    <td align="left" scope="row" width="70%"><input type="button" name="cmdSimpan6" id="cmdSimpan6" value="Simpan" onclick = "simpanKemaskiniSeksyen2()" />
      <input type="button" name="cmdKembali6" id="cmdKembali6" value="Batal" onclick="batalKemaskiniSeksyen2()" /></td>
  </tr>
</table>
</fieldset>
#else
<fieldset>
<legend>ARAHAN KE SEKSYEN LAIN</legend>
<input name="action1" type="hidden" value="$action1" />
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<input name="listPengarahSeksyen" type="hidden" value="$listPengarahSeksyen" />
<table width="100%">
  <tr>
    <td width="1%" align="left" scope="row">&nbsp;</td>
    <td width="28%" align="left" scope="row"><strong>Seksyen Pertama :</strong></td>
    <td width="1%" scope="row">&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
   <tr>
     <td width="1%" align="left" scope="row"><span class="style1">*</span></td>
    <td width="28%" align="left" scope="row">Seksyen</td>
    <td width="1%" scope="row">:</td>
    <td width="70%">$selectSeksyen1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row"><strong>Seksyen Ke-Dua</strong></td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
    <tr>
      <td width="1%" align="left" scope="row"><span class="style1">*</span></td>
    <td width="28%" align="left" scope="row">Seksyen</td>
    <td width="1%" scope="row">:</td>
    <td width="70%">$selectSeksyen2</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah2</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR2</td>
  </tr>
  <tr>
    <td colspan="4" scope="row">&nbsp;</td>
    </tr>
  <tr>
    <td colspan="3" align="center" scope="row" width="30%">&nbsp;</td>
    <td align="left" scope="row" width="70%"><input type="button" name="cmdSimpan7" id="cmdSimpan7" value="Simpan" onclick = "simpanSeksyen2()" />
      <input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali" onclick="kembali()" /></td>
  </tr>
</table>
</fieldset>
#end
#end
#if($modeSeksyen == '3')
#if($mode == 'ViewSeksyen3')
&nbsp;
<fieldset>
<legend>ARAHAN KE SEKSYEN LAIN</legend>
<input name="action1" type="hidden" value="$action1" />
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<input name="listPengarahSeksyen" type="hidden" value="$listPengarahSeksyen" />
<table width="100%">
  <tr>
    <td width="1%" align="left" scope="row">&nbsp;</td>
    <td width="28%" align="left" scope="row"><strong>Seksyen Pertama :</strong></td>
    <td width="1%" scope="row">&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
  <tr>
    <td width="1%" align="left" scope="row">&nbsp;</td>
    <td width="28%" align="left" scope="row">Seksyen</td>
    <td width="1%" scope="row">:</td>
    <td width="70%">$selectSeksyen1</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah1</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row"><strong>Seksyen Ke-Dua</strong></td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
    <tr>
      <td width="1%" align="left" scope="row">&nbsp;</td>
    <td width="28%" align="left" scope="row">Seksyen</td>
    <td width="1%" scope="row">:</td>
    <td width="70%">$selectSeksyen2</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah2</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR2</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
    <tr>
      <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row"><strong>Seksyen Ke-Tiga</strong></td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
    <tr>
      <td width="1%" align="left" scope="row">&nbsp;</td>
    <td width="28%" align="left" scope="row">Seksyen</td>
    <td width="1%" scope="row">:</td>
    <td width="70%">$selectSeksyen3</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah3</td>
  </tr>
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR3</td>
  </tr>
    <tr>
      <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" scope="row" width="30%">&nbsp;</td>
    <td align="left" scope="row" width="70%"><input type="button" name="cmdKemaskini4" id="cmdKemaskini4" value="Kemaskini" onclick="kemaskiniSeksyen3()" />
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" /></td>
  </tr>
</table>
</fieldset>
#elseif($mode== 'KemaskiniSeksyen3' && $action1 == 'kemaskiniSeksyen3')
<fieldset>
<legend>ARAHAN KE SEKSYEN LAIN</legend>
<input name="action1" type="hidden" value="$action1" />
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<input name="listPengarahSeksyen" type="hidden" value="$listPengarahSeksyen" />
<table width="100%">
  <tr>
    <td align="left" scope="row">&nbsp;</td>
    <td align="left" scope="row"><strong>Seksyen Pertama :</strong></td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Seksyen</td>
    <td scope="row">:</td>
    <td>$selectSeksyen1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row"><strong>Seksyen Ke-Dua</strong></td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Seksyen</td>
    <td scope="row">:</td>
    <td>$selectSeksyen2</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah2</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR2</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row"><strong>Seksyen Ke-Tiga</strong></td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Seksyen</td>
    <td scope="row">:</td>
    <td>$selectSeksyen3</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah3</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR3</td>
  </tr>
    <tr>
      <td width="1%" align="left" scope="row">&nbsp;</td>
    <td width="28%" align="left" scope="row">&nbsp;</td>
    <td width="1%" scope="row">&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" scope="row" width="30%">&nbsp;</td>
    <td align="left" scope="row"><input type="button" name="cmdSimpan8" id="cmdSimpan8" value="Simpan" onclick = "simpanKemaskiniSeksyen3()" />
      <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onclick="batalKemaskiniSeksyen3()"/></td>
  </tr>
</table>
</fieldset>
#else
<fieldset>
<legend>ARAHAN KE SEKSYEN LAIN</legend>
<input name="action1" type="hidden" value="$action1" />
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<input name="listPengarahSeksyen" type="hidden" value="$listPengarahSeksyen" />
<table width="100%">
  <tr>
    <td align="left" scope="row" width="1%">&nbsp;</td>
    <td align="left" scope="row" width="28%"><strong>Seksyen Pertama :</strong></td>
    <td scope="row" width="1%">&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Seksyen</td>
    <td scope="row">:</td>
    <td>$selectSeksyen1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR1</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row"><strong>Seksyen Ke-Dua</strong></td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Seksyen</td>
    <td scope="row">:</td>
    <td>$selectSeksyen2</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah2</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR2</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1"></span></td>
    <td align="left" scope="row"><strong>Seksyen Ke-Tiga</strong></td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Seksyen</td>
    <td scope="row">:</td>
    <td>$selectSeksyen3</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>$selectPengarah3</td>
  </tr>
  <tr>
    <td align="left" scope="row"><span class="style1">*</span></td>
    <td align="left" scope="row">Nama PAR</td>
    <td scope="row">:</td>
    <td>$selectPAR3</td>
  </tr>
    <tr>
      <td width="1%" align="left" scope="row">&nbsp;</td>
    <td width="28%" align="left" scope="row">&nbsp;</td>
    <td width="1%" scope="row">&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" scope="row" width="30%">&nbsp;</td>
    <td align="left" scope="row"><input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick = "simpanSeksyen3()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="kembali()"/></td>
  </tr>
</table>
</fieldset>
#end
#end

<script>
function batal(){

	window.close();
}
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=papar&pagemode=1&paparArahan=true";
	document.${formName}.submit();
}

function doChangeSeksyenPAR1() {
	document.${formName}.action1.value = "tambahSeksyen1";
 	doAjaxCall${formName}("doChangeSeksyenPAR1","action1=tambahSeksyen1");
}
function doChangeKemaskiniSeksyenPAR1() {
	document.${formName}.action1.value = "kemaskiniSeksyen1";
 	doAjaxCall${formName}("doChangeKemaskiniSeksyenPAR1","action1=kemaskiniSeksyen1");
}
function doChangeSeksyenPAR2() {
	document.${formName}.action1.value = "tambahSeksyen2";
 	doAjaxCall${formName}("doChangeSeksyenPAR2","action1=tambahSeksyen2");
}
function doChangeKemaskiniSeksyenPAR2() {
	document.${formName}.action1.value = "kemaskiniSeksyen2";
 	doAjaxCall${formName}("doChangeKemaskiniSeksyenPAR2","action1=kemaskiniSeksyen2");
}
function doChangeSeksyenPAR3() {
	document.${formName}.action1.value = "tambahSeksyen3";
 	doAjaxCall${formName}("doChangeSeksyenPAR3","action1=tambahSeksyen3");
}
function doChangeKemaskiniSeksyenPAR3() {
	document.${formName}.action1.value = "kemaskiniSeksyen3";
 	doAjaxCall${formName}("doChangeKemaskiniSeksyenPAR3","action1=kemaskiniSeksyen3");
}

function kemaskini(){
	document.${formName}.command1.value = "kemaskini";
	document.${formName}.submit();
}
function tambahMinit1(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tambahSeksyen1&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value+"&minit_Arahan="+document.${formName}.minit_Arahan.value;
	document.${formName}.submit();
}
function tambahMinit2(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tambahSeksyen2&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value+"&minit_Arahan="+document.${formName}.minit_Arahan.value;
	document.${formName}.submit();
}
function tambahMinit3(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tambahSeksyen3&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value+"&minit_Arahan="+document.${formName}.minit_Arahan.value;
	document.${formName}.submit();
}
function simpanSeksyen1(){
	if (document.${formName}.minit_Arahan.value == ""){
				alert('Sila masukkan " Minit Arahan " terlebih dahulu.');
				document.${formName}.minit_Arahan.focus();
				return;
	}
	if (document.${formName}.socSeksyen1.value == ""){
			alert('Sila masukkan " Seksyen " terlebih dahulu.');
			document.${formName}.socSeksyen1.focus();
			return;
	}
	if (document.${formName}.socPengarah1.value == ""){
			alert('Sila masukkan " Pengarah " terlebih dahulu.');
			document.${formName}.socPengarah1.focus();
			return;
	}
	if (document.${formName}.socPAR1.value == ""){
			alert('Sila masukkan " Pembantu Am Rendah " terlebih dahulu.');
			document.${formName}.socPAR1.focus();
			return;
	}
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=simpanSeksyen1&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value+"&minit_Arahan="+document.${formName}.minit_Arahan.value;
	document.${formName}.submit();
}
function kemaskiniSeksyen1(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=kemaskiniSeksyen1&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value;
	document.${formName}.submit();
}
function batalKemaskiniSeksyen1(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=batalKemaskiniSeksyen1&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value;
	document.${formName}.submit();
}
function simpanKemaskiniSeksyen1(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=simpanKemaskiniSeksyen1&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value;
	document.${formName}.submit();
}
function simpanSeksyen2(){
	if (document.${formName}.minit_Arahan.value == ""){
				alert('Sila masukkan " Minit Arahan " terlebih dahulu.');
				document.${formName}.minit_Arahan.focus();
				return;
	}
		if (document.${formName}.socSeksyen1.value == ""){
			alert('Sila masukkan " Seksyen " terlebih dahulu.');
			document.${formName}.socSeksyen1.focus();
			return;
	}
	if (document.${formName}.socPengarah1.value == ""){
			alert('Sila masukkan " Pengarah " terlebih dahulu.');
			document.${formName}.socPengarah1.focus();
			return;
	}
	if (document.${formName}.socPAR1.value == ""){
			alert('Sila masukkan " Pembantu Am Rendah " terlebih dahulu.');
			document.${formName}.socPAR1.focus();
			return;
	}
		if (document.${formName}.socSeksyen2.value == ""){
			alert('Sila masukkan " Seksyen " terlebih dahulu.');
			document.${formName}.socSeksyen2.focus();
			return;
	}
	if (document.${formName}.socPengarah2.value == ""){
			alert('Sila masukkan " Pengarah " terlebih dahulu.');
			document.${formName}.socPengarah2.focus();
			return;
	}
	if (document.${formName}.socPAR2.value == ""){
			alert('Sila masukkan " Pembantu Am Rendah " terlebih dahulu.');
			document.${formName}.socPAR2.focus();
			return;
	}

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=simpanSeksyen2&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value+"&minit_Arahan="+document.${formName}.minit_Arahan.value;
	document.${formName}.submit();
}
function kemaskiniSeksyen2(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=kemaskiniSeksyen2&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value;
	document.${formName}.submit();
}
function batalKemaskiniSeksyen2(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=batalKemaskiniSeksyen2&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value;
	document.${formName}.submit();
}
function simpanKemaskiniSeksyen2(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=simpanKemaskiniSeksyen2&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value;
	document.${formName}.submit();
}

function simpanSeksyen3(){
	if (document.${formName}.minit_Arahan.value == ""){
				alert('Sila masukkan " Minit Arahan " terlebih dahulu.');
				document.${formName}.minit_Arahan.focus();
				return;
	}
	if (document.${formName}.socSeksyen1.value == ""){
			alert('Sila masukkan " Seksyen " terlebih dahulu.');
			document.${formName}.socSeksyen1.focus();
			return;
	}
	if (document.${formName}.socPengarah1.value == ""){
			alert('Sila masukkan " Pengarah " terlebih dahulu.');
			document.${formName}.socPengarah1.focus();
			return;
	}
	if (document.${formName}.socPAR1.value == ""){
			alert('Sila masukkan " Pembantu Am Rendah " terlebih dahulu.');
			document.${formName}.socPAR1.focus();
			return;
	}
		if (document.${formName}.socSeksyen2.value == ""){
			alert('Sila masukkan " Seksyen " terlebih dahulu.');
			document.${formName}.socSeksyen2.focus();
			return;
	}
	if (document.${formName}.socPengarah2.value == ""){
			alert('Sila masukkan " Pengarah " terlebih dahulu.');
			document.${formName}.socPengarah2.focus();
			return;
	}
	if (document.${formName}.socPAR2.value == ""){
			alert('Sila masukkan " Pembantu Am Rendah " terlebih dahulu.');
			document.${formName}.socPAR2.focus();
			return;
	}
		if (document.${formName}.socSeksyen3.value == ""){
			alert('Sila masukkan " Seksyen " terlebih dahulu.');
			document.${formName}.socSeksyen3.focus();
			return;
	}
	if (document.${formName}.socPengarah3.value == ""){
			alert('Sila masukkan " Pengarah " terlebih dahulu.');
			document.${formName}.socPengarah3.focus();
			return;
	}
	if (document.${formName}.socPAR3.value == ""){
			alert('Sila masukkan " Pembantu Am Rendah " terlebih dahulu.');
			document.${formName}.socPAR3.focus();
			return;
	}

	if ( !window.confirm("Adakah Anda Pasti?") ) return;

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=simpanSeksyen3&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value+"&minit_Arahan="+document.${formName}.minit_Arahan.value;
	document.${formName}.submit();
}
function kemaskiniSeksyen3(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=kemaskiniSeksyen3&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value;
	document.${formName}.submit();
}
function batalKemaskiniSeksyen3(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=batalKemaskiniSeksyen3&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value;
	document.${formName}.submit();
}
function simpanKemaskiniSeksyen3(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=simpanKemaskiniSeksyen3&idLogDokumenKPTG="+document.${formName}.idLogDokumenKPTG.value;
	document.${formName}.submit();
}
</script>