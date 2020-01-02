<fieldset>
<legend>MAKLUMAT TANAH</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Negeri</td>
    <td>:</td>
    <td>$selectNegeriTanah</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Kementerian</td>
    <td>:</td>
    <td>$selectKementerian</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Agensi</td>
    <td>:</td>
    <td>$selectAgensi</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Maklumat Lot</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtMaklumatLot" id="txtMaklumatLot" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$beanMaklumatTanah.maklumatLot</textarea></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jenis Luas</td>
    <td>:</td>
    <td>#parse("app/php2/unit_luas.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Luas</td>
    <td>:</td>
    <td><input name="txtLuas" type="text" class="$inputTextClass" id="txtLuas" value="$beanMaklumatTanah.luas" maxlength="50" $readonly/>
    </td>
  </tr>
  <tr>
    <td width="1%" valign="top">&nbsp;</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td><textarea name="txtCatatanTanah" id="txtCatatanTanah" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$!beanMaklumatTanah.catatanTanah</textarea>    </td>
  </tr>
  #end
  #if ($mode == 'update')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniTanah()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
      #end
      #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniTanah()"/>
      #end </td>
  </tr>
</table>
</fieldset>
