<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT CATATAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatCatatan in $BeanMaklumatCatatan)
        <tr>
          <td valign="top">#if ($mode != 'viewCatatan')<span class="style1">*</span>#end</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtCatatan" id="txtCatatan" cols="50" rows="5" $readonly class="$inputTextClass">$!beanMaklumatCatatan.catatan</textarea></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($mode == 'updateCatatan')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> #if ($mode == 'newCatatan')
            <input name="cmdSimpan" type="button" value="Simpan" onclick="simpanCatatan()"/>
            <input name="cmdBatal" type="button" value="Batal" onclick="batal()"/>
            #end
            #if ($mode == 'viewCatatan')
            <input name="cmdSimpan" type="button" value="Kemaskini" onclick="kemaskiniCatatan()"/>
            <input name="cmdSimpan" type="button" value="Hapus" onclick="hapusCatatan()"/>
            <input name="cmdBatal" type="button" value="Kembali" onclick="batal()"/>
            #end
            #if ($mode == 'updateCatatan')
            <input name="cmdSimpan" type="button" value="Simpan" onclick="simpanKemaskiniCatatan()"/>
            <input name="cmdBatal" type="button" value="Batal" onclick="batalKemaskiniCatatan()"/>
            #end </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
