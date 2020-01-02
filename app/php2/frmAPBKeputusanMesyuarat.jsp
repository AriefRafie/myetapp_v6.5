<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatMesyuarat in $BeanMaklumatMesyuarat)
  <tr>
    <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">Tindakan Atas Keputusan Mesyuarat</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><textarea name="txtTindakan" id="txtTindakan" rows="9" cols="65" $readonly class="$inputTextClass"  onChange="trimTindakan(this.form.txtTindakan)">$beanMaklumatMesyuarat.tindakanMesyuarat</textarea></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Ulasan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtUlasanMesyuarat" id="txtUlasanMesyuarat" rows="9" cols="65" $readonly class="$inputTextClass"  onChange="trimUlasan(this.form.txtUlasanMesyuarat)">$beanMaklumatMesyuarat.ulasanMesyuarat</textarea></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'update')
      <input name="cmdSimpanKeputusan" type="button" value="Simpan" onClick="simpanKemaskiniKeputusan()" />
      <input name="cmdBatalKeputusan" type="button" value="Batal" onClick="batalKemaskiniKeputusan()" />
      #end
      #if ($mode == 'view')
      <input name="cmdkmskiniKeputusan" type="button" value="Kemaskini" onclick="kemaskiniKeputusan()" />
      <input name="cmdBatalMesyuarat" type="button" value="Kembali" onclick="batalMesyuarat()" />
      #end </td>
  </tr>
  #end
</table>
