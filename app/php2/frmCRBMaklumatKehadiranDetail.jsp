 #foreach ($beanMaklumatKehadiran in $BeanMaklumatKehadiran)
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Nama Pegawai</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNama" type="text" class="$inputTextClassPopup" id="txtNama" value="$beanMaklumatKehadiran.namaPegawai" size="50" $readonlyPopup onblur="this.value=this.value.toUpperCase();"/>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Kementerian</td>
    <td>:</td>
    <td>$selectKementerian
      <input type="hidden" name="idKementerian" id="idKementerian" value="$beanMaklumatKehadiran.idKementerian">
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Agensi</td>
    <td>:</td>
    <td>$selectAgensi
      <input type="hidden" name="idAgensi" id="idAgensi" value="$beanMaklumatKehadiran.idAgensi">
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jawatan</td>
    <td>:</td>
    <td><input name="txtJawatan" type="text" class="$inputTextClassPopup" id="txtJawatan" value="$beanMaklumatKehadiran.jawatan" size="50" $readonlyPopup onblur="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($modePopup == 'new')
      <input name="cmdSimpan" type="button" value="Simpan" onClick="simpanKehadiran()" />
      <input name="cmdBatal" type="button" value="Batal" onClick="batalKehadiran()" />
      #end
      #if ($modePopup == 'view')
      <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniKehadiran()" />
      <input name="cmdHapus" type="button" value="Hapus" onClick="hapusMaklumatKehadiran()" >
      <input name="cmdBatal" type="button" value="Kembali" onClick="batalKehadiran()" />
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskiniKehadiran()" />
      <input name="txtBatal" type="button" value="Batal" onClick="batalKemaskiniKehadiran()" />
      #end </td>
  </tr>
</table>
#end
