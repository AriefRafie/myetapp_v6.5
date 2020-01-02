<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {color: #0033FF}
-->
</style>
<input name="mode" type="hidden" value="$mode" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<!--<input name="idLampiran" type="text" value="" />-->
&nbsp;
  <fieldset>
  <legend><strong>Maklumat Dokumen</strong></legend>
  <table width="100%" border="0" cellspacing="0">
  <tr>
    <td width="29%"><span class="style1">*</span>No Rujukan Dokumen</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtNoRujDokumen" type="text" id="txtNoRujDokumen" size="44" $readOnly value="$noRujDok">
    </label></td>
  </tr>
  <tr>
    <td valign="top"><span class="style1">*</span>Tajuk Dokumen</td>
    <td valign="top">:</td>
    <td><label>
      <textarea name="txtTajukDokumen" cols="41" id="txtTajukDokumen" onkeyup="this.value=this.value.toUpperCase();" $readOnly>$tjkDok</textarea>
    </label></td>
  </tr>
  <tr>
    <td><span class="style1">*</span>Jenis Dokumen</td>
    <td>:</td>
    <td>$selectJenisDokumen</td>
  </tr>
  <tr>
    <td>Tarikh Dokumen</td>
    <td>:</td>
    <td><label>
      <input name="txdTarikhDokumen" type="text" id="txdTarikhDokumen" value="$tkhDok" size="10" $readOnly>
     <a href="javascript:displayDatePicker('txdTarikhDokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></label></td>
  </tr>
  <tr>
    <td>Seksyen / Urusetia</td>
    <td>:</td>
    <td>$selectSeksyen</td>
  </tr>
  <tr>
    <td>No Fail</td>
    <td>:</td>
    <td><input value="$txtNamaFail" $readOnly name="txtNamaFail" type="text" id="txtNamaFail" size="43" maxlength="50" /></td>
  </tr>
  <tr>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td><label>
      <textarea name="txtCatatan" cols="41" id="txtCatatan" $readOnly onChange="javascript:this.value=ucwords(this.value)">$catatan</textarea>
    </label></td>
  </tr>
  <tr>
    <td>Tarikh Daftar</td>
    <td>:</td>
    <td><label>
      
    $tkhDaftar</label></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>#if ($mode == 'View')
      <label>
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" />
      </label>
      <label>
      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()" />
      </label>
      <label></label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()" />
      </label>
      <label></label>
#else
<label>
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
</label>
<label>
<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()" />
</label>
#end </td>
  </tr>
  <tr>
    <td colspan="3" align="center">&nbsp;</td>
    </tr>
</table>
<fieldset><legend><strong>Senarai Lampiran</strong></legend>
<p><strong>
  <label>
  #if ($mode == 'View')
 <input type="button" name="cmdTambahLampiran" id="cmdTambahLampiran" value="Tambah" onclick="tambahLampiran('$idDokumen','tambah')">
 <!--
  <input type="button" name="cmdTambahLampiran" id="cmdTambahLampiran" value="Tambah" onclick="tambahLampiran('tambah')">
  -->
  #end
  </label>
</strong></p>
<table width="100%" border="0" cellspacing="0">
  <tr class="table_header">
    <td width="1%"><strong>No</strong></td>
    <td width="20%"><strong>Nama Fail</strong></td>
    <!--<td width="20%"><strong>Jenis Fail</strong></td>-->
    <td width="2%">&nbsp;</td>
  </tr>
  #foreach($lampiran in $SenaraiLampiran)
    #if ($lampiran.bil == '') 
  	#set ($row = 'row1')
  #elseif ($lampiran.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$lampiran.bil</td>
    #if ($lampiran.bil != '') 
    <td class="$row"><a href="javascript:papar_Lampiran('$lampiran.id_Lampiran')" class="style2">$lampiran.nama_Fail<input name="idLampiran" type="hidden" value="$lampiran.id_Lampiran" /></a></td>
    #else
    <td class="$row">$lampiran.nama_Fail</td>
    #end
    <!--<td class="$row">$lampiran.jenis_Mime</td>-->
    <td class="$row"><label>
      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus('$lampiran.id_Lampiran')">
    </label></td>
  </tr>
#end
</table>

</fieldset>
  </fieldset>
   <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-13</strong></td>
  	</tr>
  </table>

<script type="text/javascript" src="../app/pdt/dokumenlain.js"></script>