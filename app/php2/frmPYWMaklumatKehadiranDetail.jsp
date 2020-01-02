<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatKehadiran in $BeanMaklumatKehadiran)
  <tr>
    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Nama Pegawai</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtNamaKehadiran" id="txtNamaKehadiran" value="$beanMaklumatKehadiran.namaPegawai" size="40" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();"/>
    </td>
  </tr>
  #end
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td> Negeri</td>
    <td>:</td>
    <td>$selectNegeri</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Jawatan</td>
    <td>:</td>
    <td>$selectJawatan</td>
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
      #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
      #if ($modePopup == 'view')
      <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniKehadiran()" />
      <input name="cmdHapus" type="button" value="Hapus" onClick="hapusKehadiran()" >
      #end
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskiniKehadiran()" />
      <input name="txtBatal" type="button" value="Batal" onClick="batalKemaskiniKehadiran()" />
      #end </td>
  </tr>  
</table>
