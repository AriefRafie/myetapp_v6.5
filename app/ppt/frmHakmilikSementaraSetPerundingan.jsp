<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {font-weight: bold}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp") 
<p>
#if($action == 'tambahSet' || $action == 'simpanSet' || $action == 'paparSet' || $action == 'kemaskiniSet' || $action == 'updateSet')
<fieldset>
<legend><strong>Maklumat Perundingan</strong></legend>
<table width="100%">
  <tr>
    <td align="left" width="1%"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td align="left" width="28%">Bil. Perundingan</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtBilRundingan" type="text" class="$disabled" id="txtBilRundingan" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$BIL_RUNDING" $readonly>
    </label></td>
  </tr>
<!--  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Bil. Perundingan Sebelum (Sistem)</td>
    <td>:</td>
    <td width="70%"><label>
    <input name="txtBilRundinganSblm" type="text" class="$disabled" id="txtBilRundinganSblm" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$BIL_RUNDING_SBLM" $readonly />
    </label></td>
  </tr
  <tr>-->
    <td width="1%" align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left">No. Perundingan</td>
    <td>:</td>
    <td width="70%"><label>
      <input name="txtNoPerundingan" type="text" class="$disabled" id="txtNoPerundingan" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$NO_RUNDING" $readonly>
    </label></td>
  </tr>
  <tr>
    <td align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td align="left">Pegawai Perundingan</td>
    <td>:</td>
    <td>$SelectPegawai</td>
  </tr>
    <tr>
    <td width="1%" align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left">Tempat Perundingan</td>
    <td>:</td>
    <td width="70%" valign="top"><input name="txtTempat" type="text" class="$disabled" id="txtTempat" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$TEMPAT_RUNDING" size="45" $readonly /></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left">Alamat</td>
    <td>:</td>
    <td width="70%"><label>
      <input name="txtAlamat1" type="text" class="$disabled" id="txtAlamat1" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$ALAMAT1" size="45" $readonly> 
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="70%"><label>
      <input name="txtAlamat2" type="text" class="$disabled" id="txtAlamat2" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$ALAMAT2" size="45" $readonly>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="70%" valign="top"><label>
      <input name="txtAlamat3" type="text" class="$disabled" id="txtAlamat3" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$ALAMAT3" size="45" $readonly>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left" valign="top">Poskod</td>
    <td valign="top">:</td>
    <td width="70%" valign="top"><label>
      <input name="txtPoskod" type="text" class="$disabled" id="txtPoskod" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$POSKOD" size="6" $readonly>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left">Negeri</td>
    <td>:</td>
    <td width="70%" valign="top">$SelectNegeri</td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left">Bandar</td>
    <td>:</td>
    <td width="70%" valign="top">$SelectBandar</td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left">Status Perundingan</td>
    <td>:</td>
    <td width="70%" valign="top">
    <select name="socStatusRundingan" id="socStatusRundingan" class="$disabled" $disabled>
    #if($STATUS_RUNDINGAN == '0')
      <option value="0" selected="selected">SILA PILIH</option>
      <option value="1">DALAM PROSES</option>
      <option value="2">DITUNDA SEBELUM PERUNDINGAN</option>
      <option value="3">DIBATALKAN</option>
      <option value="4">ULANG PERUNDINGAN</option>
      <option value="5">SAMBUNG PERUNDINGAN</option>
      <option value="6">SELESAI</option>
    #elseif($STATUS_RUNDINGAN == '1')
      <option value="0">SILA PILIH</option>
      <option value="1" selected="selected">DALAM PROSES</option>
      <option value="2">DITUNDA SEBELUM PERUNDINGAN</option>
      <option value="3">DIBATALKAN</option>
      <option value="4">ULANG PERUNDINGAN</option>
      <option value="5">SAMBUNG PERUNDINGAN</option>
      <option value="6">SELESAI</option>
    #elseif($STATUS_RUNDINGAN == '2')
      <option value="0">SILA PILIH</option>
      <option value="1">DALAM PROSES</option>
      <option value="2" selected="selected">DITUNDA SEBELUM PERUNDINGAN</option>
      <option value="3">DIBATALKAN</option>
      <option value="4">ULANG PERUNDINGAN</option>
      <option value="5">SAMBUNG PERUNDINGAN</option>
      <option value="6">SELESAI</option>
    #elseif($STATUS_RUNDINGAN == '3')
      <option value="0">SILA PILIH</option>
      <option value="1">DALAM PROSES</option>
      <option value="2">DITUNDA SEBELUM PERUNDINGAN</option>
      <option value="3" selected="selected">DIBATALKAN</option>
      <option value="4">ULANG PERUNDINGAN</option>
      <option value="5">SAMBUNG PERUNDINGAN</option>
      <option value="6">SELESAI</option>
    #elseif($STATUS_RUNDINGAN == '4')
      <option value="0">SILA PILIH</option>
      <option value="1">DALAM PROSES</option>
      <option value="2">DITUNDA SEBELUM PERUNDINGAN</option>
      <option value="3">DIBATALKAN</option>
      <option value="4" selected="selected">ULANG PERUNDINGAN</option>
      <option value="5">SAMBUNG PERUNDINGAN</option>
      <option value="6">SELESAI</option>
    #elseif($STATUS_RUNDINGAN == '5')
      <option value="0">SILA PILIH</option>
      <option value="1">DALAM PROSES</option>
      <option value="2">DITUNDA SEBELUM PERUNDINGAN</option>
      <option value="3">DIBATALKAN</option>
      <option value="4">ULANG PERUNDINGAN</option>
      <option value="5" selected="selected">SAMBUNG PERUNDINGAN</option>
      <option value="6">SELESAI</option>
    #elseif($STATUS_RUNDINGAN == '6')
      <option value="0">SILA PILIH</option>
      <option value="1">DALAM PROSES</option>
      <option value="2">DITUNDA SEBELUM PERUNDINGAN</option>
      <option value="3">DIBATALKAN</option>
      <option value="4">ULANG PERUNDINGAN</option>
      <option value="5">SAMBUNG PERUNDINGAN</option>
      <option value="6" selected="selected">SELESAI</option>
    #end
    
    </select></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left">Tarikh Perundingan</td>
    <td>:</td>
    <td width="70%" valign="top">
     <input name="txdTkhRundingan" type="text" class="$disabled" id="txdTkhRundingan" value="$TARIKH_RUNDING" size="10" $readonly onblur="checking_validation(this,'tarikh_perundingan_check','yes','perundingan','tarikh');">
     #if($readonly != 'readonly') <a href="javascript:displayDatePicker('txdTkhRundingan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end <span class="style52">dd/mm/yyyy</span> </td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonly != 'readonly')*#end</span></td>
    <td width="28%" align="left">Masa Perundingan</td>
    <td>:</td>
    <td width="70%" valign="top"><input name="txtMasaRundingan" type="text" class="$disabled" id="txtMasaRundingan" value="$MASA_RUNDINGAN" size="10" $readonly />
      <select name="socWaktuRundingan" id="socWaktuRundingan" style="width:100px" class="$disabled" $readonly>
      #if($WAKTU_RUNDING == '0')
        <option value="0" selected="selected">SILA PILIH</option>
        <option value="1">PAGI</option>
        <option value="2">TENGAHARI</option>
        <option value="3">PETANG</option>
        <option value="4">MALAM</option>
      #end
      #if($WAKTU_RUNDING == '1')
        <option value="0">SILA PILIH</option>
        <option value="1" selected="selected">PAGI</option>
        <option value="2">TENGAHARI</option>
        <option value="3">PETANG</option>
        <option value="4">MALAM</option>
      #end
      #if($WAKTU_RUNDING == '2')
        <option value="0">SILA PILIH</option>
        <option value="1">PAGI</option>
        <option value="2" selected="selected">TENGAHARI</option>
        <option value="3">PETANG</option>
        <option value="4">MALAM</option>
      #end
       #if($WAKTU_RUNDING == '3')
        <option value="0">SILA PILIH</option>
        <option value="1">PAGI</option>
        <option value="2">TENGAHARI</option>
        <option value="3" selected="selected">PETANG</option>
        <option value="4">MALAM</option>
      #end
       #if($WAKTU_RUNDING == '4')
        <option value="0">SILA PILIH</option>
        <option value="1">PAGI</option>
        <option value="2">TENGAHARI</option>
        <option value="3">PETANG</option>
        <option value="4" selected="selected">MALAM</option>
      #end
    
    </select>    </td>
  </tr>
  <!--<tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Tarikh Akhir Tampal Notis Awam</td>
    <td>:</td>
    <td width="70%" valign="top">
     <input name="txdTkhAkhirNotis" type="text" class="$disabled" id="txdTkhAkhirNotis" value="$TARIKH_AKHIR" size="10" $readonly onblur="checking_validation(this,'tarikh_ajhir_tampal_check','yes',akhir tampal notis awam','tarikh');">
      #if($readonly != 'readonly') <a href="javascript:displayDatePicker('txdTkhAkhirNotis',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end <span class="style52">dd/mm/yyyy</span> </td>
  </tr>-->
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="28%" align="left" valign="top">Alasan Tangguh/Batal</td>
    <td valign="top">:</td>
    <td width="70%" valign="top"><textarea name="txtAlasan" id="txtAlasan" cols="45" rows="5" $readonly class="$disabled" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$ALASAN</textarea></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="70%" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="8" align="center">
    #if($mode == 'newSet')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanSet()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewSet()" />
    #elseif($mode == 'paparSet')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniSet()" />
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />
      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusSet()" />
    #elseif($mode == 'kemaskiniSet') 
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateSet()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewSet()" />
    #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliSet()" />    </td>
  </tr>
</table>
</fieldset>
#end
<p>
#if($action == 'newSet') 
<fieldset>
<legend><strong>Senarai Hakmilik</strong></legend>
<table width="100%">
  <tr class="table_header">
    <td><strong>Bil</strong></td>
    <td><strong>No Lot / No PT</strong></td>
    <td><strong>No. Hakmilik</strong></td>
    <td><strong>Bandar/Pekan/Mukim</strong></td>
    <td><strong>Daerah</strong></td>
    <td><strong>Luas Lot</strong></td>
    <td><strong>Luas Diguna/Disewa</strong></td>
  </tr>
  #foreach ($hakmilik in $SenaraiHakmilik)
  
  #if ($hakmilik.BIL == '') 
  	#set ($row = 'row1')
  #elseif ($hakmilik.BIL % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$hakmilik.BIL</td>
    #if ($hakmilik.BIL != '') 
    	<td class="$row"><a href="javascript:daftarRundingan('$hakmilik.ID_HAKMILIK')"><font color="blue">$hakmilik.NO_LOT</font></a></td>
    #else
    	<td class="$row">$hakmilik.NO_LOT</td>
    #end
    <td class="$row">$hakmilik.NO_HAKMILIK</td>
    <td class="$row">$hakmilik.NAMA_MUKIM</td>
    <td class="$row">$hakmilik.NAMA_DAERAH</td>
    <td class="$row">$hakmilik.LUAS_LOT</td>
    <td class="$row">$hakmilik.LUAS_AMBIL</td>
  </tr>
  #end
</table>
</fieldset>
#end
<p>
#if($action == 'newSet' || $action == 'paparSet' || $action == 'kemaskiniSet' || $action == 'simpanSet' || $action == 'tambahSet' || $action == 'updateSet') 
<fieldset><legend><strong>Senarai  Perundingan</strong></legend>
<table width="100%">
  <tr class="table_header">
    <td><strong>Bil</strong></td>
    <td width="15%"><strong>Bil Perundingan</strong></td>
    <td width="12%"><strong>No Lot/No PT</strong></td>
    <td width="20%"><strong>No. Perundingan</strong></td>
    <td width="20%"><strong>Tarikh Perundingan</strong></td>
    <td width="19%"><strong>Tempat</strong></td>
    <td width="14%"><strong>Status</strong></td>
  </tr>
  #foreach ($listRunding in $SenaraiRundingan)

    #if ($listRunding.bil == '') 
    	#set ($row = 'row1')
    #elseif ($listRunding.bil % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
  <tr>
    <td class="$row">$listRunding.bil</td>
    #if ($listRunding.bil != '') 
    <td class="$row"><a href="javascript:paparRundingan('$listRunding.ID_SIASATAN')"><font color="blue">$listRunding.NO_KES</font></a></td>
    #else
    <td class="$row">$listRunding.NO_KES</td>
    #end
    <td class="$row">$listRunding.NO_LOT</td>
    <td class="$row">$listRunding.NO_SIASATAN</td>
    <td class="$row">$listRunding.TARIKH_SIASATAN</td>
    <td class="$row">$listRunding.TEMPAT</td>
    <td class="$row">$listRunding.STATUS_SIASATAN</td>
  </tr>
  #end
</table>
</fieldset>
#end
#if($action == 'tambahKehadiran' || $action == 'paparKehadiran' || $action == 'kemaskiniKehadiran' || $action == 'updateKehadiran'  || $action == 'hapusKehadiran' || $action == 'simpanKehadiran' || $action == 'tambahTurutHadir' || $action == 'simpanTurutHadir')
#parse("app/ppt/frmHakmilikSementaraMaklumatPerundingan.jsp") 
#end
#if($action == 'tambahKehadiran' || $action == 'paparKehadiran' || $action == 'kemaskiniKehadiran' || $action == 'updateKehadiran'  || $action == 'hapusKehadiran' || $action == 'simpanKehadiran')
<fieldset><legend><strong>Maklumat Kehadiran Perundingan</strong></legend>
<table width="100%">
  <tr>
    <td align="left" width="1%"><span class="style1">*</span></td>
    <td align="left" width="28%">Nama PB dalam Hakmilik</td>
    <td width="1%">:</td>
    <td width="70%">$SelectPB</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">No. PB</td>
    <td>:</td>
    <td width="70%">$NO_PB</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">No. Lot</td>
    <td>:</td>
    <td width="70%">$LOT $NO_LOT</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">No. PT</td>
    <td>:</td>
    <td width="70%">$NO_PT</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">Jenis Berkepentingan(PB)</td>
    <td>:</td>
    <td width="70%">$JENIS_PB</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">Nama Bank</td>
    <td>:</td>
    <td width="70%"><input name="txtNamaBank" type="text" id="txtNamaBank" value="$NAMA_BANK" size="40" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabledPb" $readonlyPb $disabledPb /></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">No. Akaun</td>
    <td>:</td>
    <td width="70%"><input name="txtNoAkaun" type="text" class="$disabledPb" id="txtNoAkaun" value="$NO_AKAUN" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyPb $disabledPb /></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">Alamat</td>
    <td>:</td>
    <td width="70%">$ALAMAT1</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="70%">$ALAMAT2</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="70%">$ALAMAT3</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">Poskod</td>
    <td>&nbsp;</td>
    <td width="70%">$POSKOD</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">Negeri</td>
    <td>:</td>
    <td width="70%">$SelectNegeri</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">Bandar</td>
    <td>:</td>
    <td width="70%">$SelectBandar</td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left">No Telefon Rumah</td>
    <td>:</td>
    <td>$NO_TEL_RUMAH</td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left">No Telefon Bimbit</td>
    <td>:</td>
    <td>$NO_HANDPHONE</td>
  </tr>
 <!-- <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Status Kehadiran</td>
    <td>:</td>
    <td width="70%">
    #if($FLAG_HADIR == '1')
      <input name="sorHadir" type="radio" id="sorHadir" value="1" checked="checked" $readonlyChecked class="$disabledChecked" $disabledChecked />
      Hadir 
    #else
      <input name="sorHadir" type="radio" id="sorHadir" value="1"  $readonlyChecked class="$disabledChecked" $disabledChecked />
      Hadir 
    #end    </td>
  </tr>-->
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="7" align="center">
    #if($modePb == 'newPb')
    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanKehadiran()" />
    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewKehadiran()" />
    #elseif($modePb == 'paparPb')
    <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniKehadiran()" />
    <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusKehadiran()" />
    #elseif($modePb == 'kemaskiniPb')
    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateKehadiran()" />
    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewKehadiran()" />
    #end
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliKehadiran()" />    </td>
  </tr>
</table>
</fieldset>
#end
<p>
#if($action != 'newSet' && $action != 'tambahSet' && $action != 'tambahTurutHadir' && $action != 'paparTurutHadir' && $action != 'kemaskiniTurutHadir' && $action != 'simpanTurutHadir' && $action != 'hapusTurutHadir' && $action != 'updateTurutHadir') 
<fieldset>
<legend><strong>Senarai Kehadiran</strong></legend>
#if ($action != 'tambahKehadiran')
<input type="button" name="cmdTambahKehadiran" id="cmdTambahKehadiran" value="Tambah" onclick="tambahKehadiran('$idHakmilik')" />
#end
<table width="100%">
  <tr class="table_header">
    <td><strong>Bil</strong></td>
    <td><strong>Nama PB</strong></td>
    <td><strong>No. PB</strong></td>
    <td><strong>No Lot/PT</strong></td>
    <td><strong>Jenis Pihak Berkepentingan</strong></td>
    <td><strong>Status Kehadiran</strong></td>
  </tr>
   #foreach ($listPb in $SenaraiPb)

    #if ($listPb.BIL == '') 
    	#set ($row = 'row1')
    #elseif ($listPb.BIL % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
  <tr>
    <td>$listPb.BIL</td>
    #if ($listPb.BIL != '') 
    <td><a href="javascript:paparKehadiran('$listPb.ID_KEHADIRAN')"><font color="blue">$listPb.NAMA_PB</font></a></td>
    #else
     <td>$listPb.NAMA_PB</td>
    #end
    <td>$listPb.NO_PB</td>
    <td>$listPb.NO_LOT</td>
    <td>$listPb.JENIS_PB</td>
    <td>$listPb.FLAG_HADIR</td>
  </tr>
 #end
</table>
</fieldset>
#end
#if($action == 'tambahTurutHadir' || $action == 'paparTurutHadir' || $action == 'kemaskiniTurutHadir' || $action == 'simpanTurutHadir' || $action == 'hapusTurutHadir' || $action == 'updateTurutHadir')
<fieldset>
<legend><strong>Maklumat Pihak Berkepentingan Yang Turut Hadir Perundingan</strong></legend>
<table width="100%">
  <tr>
    <td width="1%"><span class="style1">*</span></td>
    <td width="28%">Nama PB</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNamaPB" type="text" id="txtNamaPB" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$NAMA_PBTURUT" size="45" $readonlyTurut $disabledTurut class="$disabledTurut" /></td>
  </tr>
  <tr>
    <td width="1%"><span class="style1">*</span></td>
    <td width="28%">Kod Jenis PB</td>
    <td width="1%">:</td>
    <td width="70%">$SelectJenisPB</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Kod No PB</td>
    <td>:</td>
    <td>$SelectJenisNoPB</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No PB</td>
    <td>:</td>
    <td><input name="txtNoPB" type="text" id="txtNoPB" value="$NO_PBTURUT" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyTurut $disabledTurut class="$disabledTurut" /></td>
  </tr>
  <tr>
    <td><span class="style1">*</span></td>
    <td>Alamat</td>
    <td>:</td>
    <td><input name="txtAlamat4" type="text" id="txtAlamat4" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$ALAMAT1TURUT" size="45" $readonlyTurut $disabledTurut class="$disabledTurut" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input name="txtAlamat5" type="text" id="txtAlamat5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$ALAMAT2TURUT" size="45" $readonlyTurut $disabledTurut class="$disabledTurut" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input name="txtAlamat6" type="text" id="txtAlamat6" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" value="$ALAMAT3TURUT" size="45" $readonlyTurut $disabledTurut class="$disabledTurut" /></td>
  </tr>
  <tr>
    <td><span class="style1">*</span></td>
    <td>Poskod</td>
    <td>:</td>
    <td><input name="txtPoskod2" type="text" id="txtPoskod2" value="$POSKODTURUT" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" size="5" $readonlyTurut $disabledTurut class="$disabledTurut" /></td>
  </tr>
  <tr>
    <td><span class="style1">*</span></td>
    <td>Negeri</td>
    <td>:</td>
    <td>$SelectNegeriTurut</td>
  </tr>
  <tr>
    <td><span class="style1">*</span></td>
    <td>Bandar</td>
    <td>:</td>
    <td>$SelectBandarTurut</td>
  </tr>
  <tr>
    <td><span class="style1">*</span></td>
    <td>No Telefon Pejabat</td>
    <td>:</td>
    <td><input name="txtNoTelPjbt" type="text" id="txtNoTelPjbt" value="$NO_TEL_RUMAHTURUT" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" maxlength="12" $readonlyTurut $disabledTurut class="$disabledTurut"/></td>
  </tr>
  <tr>
    <td><span class="style1">*</span></td>
    <td>No Telefon Bimbit</td>
    <td>:</td>
    <td><input name="txtNoHp" type="text" id="txtNoHp" value="$NO_HANDPHONETURUT" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" maxlength="12" $readonlyTurut $disabledTurut class="$disabledTurut"/></td>
  </tr>
  <tr>
    <td><span class="style1">*</span></td>
    <td>No Faks</td>
    <td>:</td>
    <td><input name="txtNoFaks" type="text" id="txtNoFaks" value="$NO_FAXTURUT" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" maxlength="12" $readonlyTurut $disabledTurut class="$disabledTurut"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>
    #if($modeTurut == 'newTurut')
    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanTurutHadir()" />
    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewTurutHadir()" />
    #elseif($modeTurut == 'paparTurut')
    <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniTurutHadir()" />
    <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusTurutHadir()" />
    #elseif($modeTurut == 'kemaskiniTurut')
    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateTurutHadir()" />
    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewTurutHadir()" />
    #end
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliTurutHadir()" /></td>
  </tr>
</table>

</fieldset>
#end
<p>
#if($action != 'newSet' && $action != 'tambahSet') 
<fieldset>
<legend><strong>Senarai Kehadiran Yang Turut Hadir</strong></legend>
#if ($action != 'tambahTurutHadir')
<input type="button" name="cmdTambahTurutHadir" id="cmdTambahTurutHadir" value="Tambah" onclick="tambahTurutHadir('$idHakmilik')" />
#end
<table width="100%">
  <tr class="table_header">
    <td><strong>Bil</strong></td>
    <td><strong>Nama PB</strong></td>
    <td><strong>No. PB</strong></td>
    <td><strong>No Lot/PT</strong></td>
    <td><strong>Jenis Pihak Berkepentingan</strong></td>
    <td><strong>Status Kehadiran</strong></td>
  </tr>
   #foreach ($listTurutHadir in $SenaraiTurutHadir)

    #if ($listTurutHadir.BIL == '') 
    	#set ($row = 'row1')
    #elseif ($listTurutHadir.BIL % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
  <tr>
    <td>$listTurutHadir.BIL</td>
    #if ($listTurutHadir.BIL != '') 
    <td><a href="javascript:paparTurutHadir('$listTurutHadir.ID_KEHADIRAN')"><font color="blue">$listTurutHadir.NAMA_PB</font></a></td>
    #else
     <td>$listTurutHadir.NAMA_PB</td>
    #end
    <td>$listTurutHadir.NO_PB</td>
    <td>$listTurutHadir.NO_LOT</td>
    <td>$listTurutHadir.JENIS_PB</td>
    <td>$listTurutHadir.FLAG_HADIR</td>
  </tr>
 #end
</table>
</fieldset>
#end
<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" onClick="javascript:cetakSuratRundingan('$idFail','$idHakmilik')"><font color="blue"> Surat Makluman Kepada Agensi Pemohon / Pihak Berkepentingan - Rundingan </font></a></td>
      </tr> 
      <tr>
        <td><a href="#" onClick="javascript:cetakSuratJPPH('$idFail')"><font color="blue"> Surat Kepada JPPH - Makluman Perundingan
 </font></a></td>
      </tr>  
      <tr>
        <td><a href="#" onClick="javascript:cetakBorangQ('$idFail','$idHakmilik')"><font color="blue"> Borang Q - Pemberitahu Menduduki Atau Menggunakan Tanah Bagi Sementara          
 </font></a></td>
      </tr>
  
    </table>
</fieldset>
<input name="id_siasatan" type="hidden" value="$idSiasatan" />
<input type="hidden" name="id_fail" value="$idFail" />
<input type="hidden" name="id_permohonan" value="$idPermohonan" />
<input name="id_hakmilik" type="hidden" value="$idHakmilik" />
<input name="id_kehadiran" type="hidden" value="$idKehadiran" />
<input name="idHakmilikPb" type="hidden" value="$idHakmilikPb" />
<input name="idTurutHadir" type="hidden" value="$idTurutHadir" />
<input name="idPB" type="hidden" value="$idPB" />
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function paparRundingan(id_siasatan) {

document.${formName}.id_siasatan.value = id_siasatan;
document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=paparSet";
document.${formName}.submit();

}
function daftarRundingan(id_hakmilik) {

document.${formName}.id_hakmilik.value = id_hakmilik;
document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=tambahSet";
document.${formName}.submit();

}

function tambahKehadiran(id_hakmilik){
document.${formName}.id_hakmilik.value = id_hakmilik;
document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=tambahKehadiran";
document.${formName}.submit();

}
function simpanSet(){
	
	if(document.${formName}.txtBilRundingan.value == ""){
		alert("Sila masukkan \"Bil Perundingan\" terlebih dahulu.");
  		document.${formName}.txtBilRundingan.focus(); 
		return;
	}
	/*if(document.${formName}.txtBilRundinganSblm.value == ""){
		alert("Sila masukkan \"Bil Perundingan Sebelum\" terlebih dahulu.");
  		document.${formName}.txtBilRundinganSblm.focus(); 
		return;
	}*/
	if(document.${formName}.txtNoPerundingan.value == ""){
		alert("Sila masukkan \"No Perundingan\" terlebih dahulu.");
  		document.${formName}.txtNoPerundingan.focus(); 
		return;
	}
	if(document.${formName}.socPegawai.value == "0"){
		alert("Sila masukkan \"No Perundingan\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}
	if(document.${formName}.txtAlamat1.value == ""){
		alert("Sila masukkan \"Alamat\" terlebih dahulu.");
  		document.${formName}.txtAlamat1.focus(); 
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
  		document.${formName}.txtPoskod.focus(); 
		return;
	}
	if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;
	}
	if(document.${formName}.socBandar.value == ""){
		alert("Sila pilih \"Bandar\" terlebih dahulu.");
  		document.${formName}.socBandar.focus(); 
		return;
	}
	if(document.${formName}.socStatusRundingan.value == ""){
		alert("Sila pilih \"Status Perundingan\" terlebih dahulu.");
  		document.${formName}.socStatusRundingan.focus(); 
		return;
	}
	if(document.${formName}.txdTkhRundingan.value == ""){
		alert("Sila masukkan \"Tarikh Perundingan\" terlebih dahulu.");
  		document.${formName}.txdTkhRundingan.focus(); 
		return;
	}
	if(document.${formName}.txtMasaRundingan.value == ""){
		alert("Sila masukkan \"Masa Perundingan\" terlebih dahulu.");
  		document.${formName}.txtMasaRundingan.focus(); 
		return;
	}
	if(document.${formName}.socWaktuRundingan.value == ""){
		alert("Sila masukkan \"Waktu Perundingan\" terlebih dahulu.");
  		document.${formName}.socWaktuRundingan.focus(); 
		return;
	}
	
	if(document.${formName}.txtTempat.value == ""){
		alert("Sila masukkan \"Tempat Perundingan\" terlebih dahulu.");
  		document.${formName}.txtTempat.focus(); 
		return;
	}
	/*if(document.${formName}.txdTkhAkhirNotis.value == ""){
		alert("Sila masukkan \"Tarikh Akhir Tampal Notis Awam\" terlebih dahulu.");
  		document.${formName}.txdTkhAkhirNotis.focus(); 
		return;
	}*/
	
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=simpanSet";
	document.${formName}.submit();



}
function batalNewSet(){

	document.${formName}.txtBilRundingan.value = "";
	document.${formName}.txtBilRundinganSblm.value = "";
	document.${formName}.txtNoPerundingan.value = "";
	document.${formName}.txtAlamat1.value = "";
	document.${formName}.txtAlamat2.value = "";
	document.${formName}.txtAlamat3.value = "";
	document.${formName}.txtPoskod.value = "";
	document.${formName}.socNegeri.value = "";
	document.${formName}.socBandar.value = "";
	document.${formName}.socStatusRundingan.value = "";
	document.${formName}.txdTkhRundingan.value = "";
	document.${formName}.txtMasaRundingan.value = "";
	document.${formName}.txtTempat.value = "";
	document.${formName}.txdTkhAkhirNotis.value = "";
	document.${formName}.txtAlasan.value = "";
	document.${formName}.txtBilRundingan.focus();
	
	return;

}
function kemaskiniSet(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=kemaskiniSet";
	document.${formName}.submit();

}
function cetakBorangQ(id_fail,id_hakmilik) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.BorangQ?idfail="+id_fail+"&idhakmilik="+id_hakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function cetakSuratRundingan(id_fail,id_hakmilik) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.SuratKpdAPRundingan?idfail="+id_fail+"&idhakmilik="+id_hakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function cetakSuratJPPH(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.SuratKepadaJPPHSupayaMenghadiriPerundingan?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function hapusSet(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=hapusSet";
document.${formName}.submit();

}
function updateSet(){

	if(document.${formName}.txtBilRundingan.value == ""){
		alert("Sila masukkan \"Bil Perundingan\" terlebih dahulu.");
  		document.${formName}.txtBilRundingan.focus(); 
		return;
	}
	/*if(document.${formName}.txtBilRundinganSblm.value == ""){
		alert("Sila masukkan \"Bil Perundingan Sebelum\" terlebih dahulu.");
  		document.${formName}.txtBilRundinganSblm.focus(); 
		return;
	}*/
	if(document.${formName}.txtNoPerundingan.value == ""){
		alert("Sila masukkan \"No Perundingan\" terlebih dahulu.");
  		document.${formName}.txtNoPerundingan.focus(); 
		return;
	}
	if(document.${formName}.socPegawai.value == "0"){
		alert("Sila masukkan \"No Perundingan\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}
	if(document.${formName}.txtAlamat1.value == ""){
		alert("Sila masukkan \"Alamat\" terlebih dahulu.");
  		document.${formName}.txtAlamat1.focus(); 
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
  		document.${formName}.txtPoskod.focus(); 
		return;
	}
	if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;
	}
	if(document.${formName}.socBandar.value == ""){
		alert("Sila pilih \"Bandar\" terlebih dahulu.");
  		document.${formName}.socBandar.focus(); 
		return;
	}
	if(document.${formName}.socStatusRundingan.value == ""){
		alert("Sila pilih \"Status Perundingan\" terlebih dahulu.");
  		document.${formName}.socStatusRundingan.focus(); 
		return;
	}
	if(document.${formName}.txdTkhRundingan.value == ""){
		alert("Sila masukkan \"Tarikh Perundingan\" terlebih dahulu.");
  		document.${formName}.txdTkhRundingan.focus(); 
		return;
	}
	if(document.${formName}.txtMasaRundingan.value == ""){
		alert("Sila masukkan \"Masa Perundingan\" terlebih dahulu.");
  		document.${formName}.txtMasaRundingan.focus(); 
		return;
	}
	if(document.${formName}.socWaktuRundingan.value == ""){
		alert("Sila masukkan \"Waktu Perundingan\" terlebih dahulu.");
  		document.${formName}.socWaktuRundingan.focus(); 
		return;
	}
	
	if(document.${formName}.txtTempat.value == ""){
		alert("Sila masukkan \"Tempat Perundingan\" terlebih dahulu.");
  		document.${formName}.txtTempat.focus(); 
		return;
	}
	/*if(document.${formName}.txdTkhAkhirNotis.value == ""){
		alert("Sila masukkan \"Tarikh Akhir Tampal Notis Awam\" terlebih dahulu.");
  		document.${formName}.txdTkhAkhirNotis.focus(); 
		return;
	}*/
	
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=updateSet";
	document.${formName}.submit();


}
function batalViewSet(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=paparSet";
	document.${formName}.submit();

}
function kembaliSet(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=newSet";
	document.${formName}.submit();

}
function simpanKehadiran(){
    
   
	if(document.${formName}.socPB.value == ""){
		alert("Sila pilih \"Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.socPB.focus(); 
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=simpanKehadiran";
	document.${formName}.submit();
}
function kemaskiniKehadiran(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=kemaskiniKehadiran";
	document.${formName}.submit();

}
function paparKehadiran(id_kehadiran){
	
	document.${formName}.id_kehadiran.value = id_kehadiran;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=paparKehadiran";
	document.${formName}.submit();

}
function updateKehadiran(){

	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=updateKehadiran";
	document.${formName}.submit();

}
function batalNewKehadiran(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=tambahKehadiran";
	document.${formName}.submit();
	
}
function batalViewKehadiran(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=paparKehadiran";
	document.${formName}.submit();

}
function hapusKehadiran(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=hapusKehadiran";
document.${formName}.submit();

}
function kembaliKehadiran(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=paparSet";
	document.${formName}.submit();

}
function tambahTurutHadir(id_hakmilik){
document.${formName}.id_hakmilik.value = id_hakmilik;
document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=tambahTurutHadir";
document.${formName}.submit();

}
function paparTurutHadir(id_kehadiran){
	
	document.${formName}.idTurutHadir.value = id_kehadiran;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=paparTurutHadir";
	document.${formName}.submit();

}
function simpanTurutHadir(){
    
   
	if(document.${formName}.txtNamaPB.value == ""){
		alert("Sila masukkan \"Nama PB\" terlebih dahulu.");
  		document.${formName}.txtNamaPB.focus(); 
		return;
	}
	
	if(document.${formName}.socKodJenisPB.value == ""){
		alert("Sila pilih \"Kod Jenis PB\" terlebih dahulu.");
  		document.${formName}.socKodJenisPB.focus(); 
		return;
	}
	
	if(document.${formName}.txtAlamat4.value == ""){
		alert("Sila masukkan \"Alamat\" terlebih dahulu.");
  		document.${formName}.txtAlamat4.focus(); 
		return;
	}
	
	if(document.${formName}.txtPoskod2.value == ""){
		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
  		document.${formName}.txtPoskod2.focus(); 
		return;
	}
	
	if(document.${formName}.socNegeriTurut.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeriTurut.focus(); 
		return;
	}
	
	if(document.${formName}.socBandarTurut.value == ""){
		alert("Sila pilih \"Bandar\" terlebih dahulu.");
  		document.${formName}.socBandarTurut.focus(); 
		return;
	}
	
	if(document.${formName}.txtNoTelPjbt.value == ""){
		alert("Sila masukkan \"No Telefon Pejabat\" terlebih dahulu.");
  		document.${formName}.txtNoTelPjbt.focus(); 
		return;
	}
	
	if(document.${formName}.txtNoHp.value == ""){
		alert("Sila masukkan \"No Telefon Bimbit\" terlebih dahulu.");
  		document.${formName}.txtNoHp.focus(); 
		return;
	}
	
	if(document.${formName}.txtNoFaks.value == ""){
		alert("Sila masukkan \"No Faks\" terlebih dahulu.");
  		document.${formName}.txtNoFaks.focus(); 
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=simpanTurutHadir";
	document.${formName}.submit();
}
function kemaskiniTurutHadir(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=kemaskiniTurutHadir";
	document.${formName}.submit();

}
function updateTurutHadir(){
	
	if(document.${formName}.txtNamaPB.value == ""){
		alert("Sila masukkan \"Nama PB\" terlebih dahulu.");
  		document.${formName}.txtNamaPB.focus(); 
		return;
	}
	
	if(document.${formName}.socKodJenisPB.value == ""){
		alert("Sila pilih \"Kod Jenis PB\" terlebih dahulu.");
  		document.${formName}.socKodJenisPB.focus(); 
		return;
	}
	
	if(document.${formName}.txtAlamat4.value == ""){
		alert("Sila masukkan \"Alamat\" terlebih dahulu.");
  		document.${formName}.txtAlamat4.focus(); 
		return;
	}
	
	if(document.${formName}.txtPoskod2.value == ""){
		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
  		document.${formName}.txtPoskod2.focus(); 
		return;
	}
	
	if(document.${formName}.socNegeriTurut.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeriTurut.focus(); 
		return;
	}
	
	if(document.${formName}.socBandarTurut.value == ""){
		alert("Sila pilih \"Bandar\" terlebih dahulu.");
  		document.${formName}.socBandarTurut.focus(); 
		return;
	}
	
	if(document.${formName}.txtNoTelPjbt.value == ""){
		alert("Sila masukkan \"No Telefon Pejabat\" terlebih dahulu.");
  		document.${formName}.txtNoTelPjbt.focus(); 
		return;
	}
	
	if(document.${formName}.txtNoHp.value == ""){
		alert("Sila masukkan \"No Telefon Bimbit\" terlebih dahulu.");
  		document.${formName}.txtNoHp.focus(); 
		return;
	}
	
	if(document.${formName}.txtNoFaks.value == ""){
		alert("Sila masukkan \"No Faks\" terlebih dahulu.");
  		document.${formName}.txtNoFaks.focus(); 
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=updateTurutHadir";
	document.${formName}.submit();

}
function batalNewTurutHadir(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=tambahTurutHadir";
	document.${formName}.submit();

}
function batalViewTurutHadir(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=paparTurutHadir";
	document.${formName}.submit();

}
function hapusTurutHadir(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=hapusTurutHadir";
	document.${formName}.submit();

}
function kembaliTurutHadir(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSetPerundingan&action=paparSet";
	document.${formName}.submit();

}
function doChangeidNegeri() {
    doAjaxCall${formName}("doChangeidNegeri");
}
function doChangeidNegeriUpdate() {
    doAjaxCall${formName}("doChangeidNegeriUpdate");
}
function doChangeidNegeriTurut() {
    doAjaxCall${formName}("doChangeidNegeriTurut");
}
function doChangeidNegeriTurutUpdate() {
    doAjaxCall${formName}("doChangeidNegeriTurutUpdate");
}
function doChangePB() {
    doAjaxCall${formName}("doChangePB");
}
function checking_validation(field,point,mandatory,value_field,jenis_field){	
    	
	var lepas_or_xlepas = 2;	
	
	
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	      DateField.select();
		 // DateField.focus();
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	   //alert(lepas_or_xlepas);
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   }
	   else
	   {
	  
	   
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.focus();
	   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.select();
	//   DateField.focus();
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	//   DateField.select();
	//   DateField.focus();
	   
	   }
	   
	   }
	   
	   
	   if(jenis_field == "normal")
	   {
	   
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	
	   }
	   else
	   {
	    document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   
	   	   
	   }
	   
	   
	   
	
	}
</script>

