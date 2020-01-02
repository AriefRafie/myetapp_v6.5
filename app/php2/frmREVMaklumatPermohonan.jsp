<fieldset>
<legend>MAKLUMAT PERMOHONAN</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">No. Fail</td>
    <td width="1%" >:</td>
    <td width="70%"><input name="noFail" type="text" id="noFail" size="49" maxlength="100" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.noFail" onBlur="this.value=this.value.toUpperCase();checkingExistNoFailUpdate();" />    </td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Urusan</td>
    <td>:</td>
    <td>$selectUrusan</td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Suburusan</td>
    <td>:</td>
    <td>$selectSuburusan</td>
  </tr>
  <tr>
    <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Perkara</td>
    <td valign="top">:</td>
    <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$beanMaklumatPermohonan.perkara</textarea>    </td>
  </tr>
  <tr>
    <td width="1%" valign="top">&nbsp;</td>
    <td valign="top">Tujuan Sewa</td>
    <td valign="top">:</td>
    <td><textarea name="txtTujuan" id="txtTujuan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$beanMaklumatPermohonan.tujuan</textarea>    </td>
  </tr>
  <tr>
    <td width="1%" valign="top">&nbsp;</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td><textarea name="txtCatatanPermohonan" id="txtCatatanPermohonan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$!beanMaklumatPermohonan.catatan</textarea>    </td>
  </tr>
  #end
  #if ($mode == 'update')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini2" id="cmdSimpanKemaskini2" value="Simpan" onClick="doSimpanKemaskiniPermohonan()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
      #end
      #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniPermohonan()"/>
      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapusPermohonan()"/>
      #end </td>
  </tr>
</table>
</fieldset>
