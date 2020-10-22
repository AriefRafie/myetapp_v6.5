<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatKehadiran in $BeanMaklumatKehadiran)
  <tr>
    <td width="1%">#if ($modePopup != 'view')<font color="#ff0000">*</font>#end</td>
    <td width="28%">Nama Pegawai</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtNamaPegawai" id="txtNamaPegawai" value="$beanMaklumatKehadiran.namaPegawai" size="40" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();"/>
    </td>
  </tr>
  #end
  <tr>
    <td>#if ($modePopup != 'view')<font color="#ff0000">*</font>#end</td>
    <td> Negeri</td>
    <td>:</td>
    <td>$selectNegeri</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<font color="#ff0000">*</font>#end</td>
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
    <td> 
    	#if ($modePopup == 'new')
      		<input name="cmdSimpan" type="button" value="Simpan" onClick="simpanKehadiran()" />
	      	<input name="cmdBatal" type="button" value="Batal" onClick="batalKehadiran()" />
	    #end
	    #if ($modePopup == 'update')
	      	<input name="cmdSimpanKemaskini" type="button" value="Kemaskini" onClick="simpanKemaskiniKehadiran()" />
	      	<input name="txtBatal" type="button" value="Kembali" onClick="batalKemaskiniKehadiran()" />
	      	<input name="cmdHapus" type="button" value="Hapus" onClick="hapusKehadiran()" >
	    #end 
	</td>
  </tr>  
</table>