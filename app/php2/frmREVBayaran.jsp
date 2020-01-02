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
      <legend><strong>MAKLUMAT PEMBAYARAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatBayaran in $BeanMaklumatBayaran)
        <tr>
          <td width="1%">#if ($mode != 'viewBayaran')<span class="style1">*</span>#end</td>
          <td width="28%">Tarikh Transaksi</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikh" id="txtTarikh" value="$beanMaklumatBayaran.tarikh" size="9" $readonly class="$inputTextClass" onblur="check_date(this);"/>
            #if ($mode != 'viewBayaran') <a href="javascript:displayDatePicker('txtTarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'viewBayaran')<span class="style1">*</span>#end</td>
          <td width="28%">Mod Bayaran</td>
          <td width="1%">:</td>
          <td width="70%">$selectModBayaran</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'viewBayaran')<span class="style1">*</span>#end</td>
          <td width="28%">Cara Bayar</td>
          <td width="1%">:</td>
          <td width="70%">$selectCaraBayaran</td>
        </tr>
        #if ($idCaraBayaran == '5' || $idCaraBayaran == '9' || $idCaraBayaran == '11')
        <tr>
          <td>&nbsp;</td>
          <td>Bank</td>
          <td>:</td>
          <td>$selectBank</td>
        </tr>
        #end
        #if ($idCaraBayaran == '3' || $idCaraBayaran == '4' || $idCaraBayaran == '5' || $idCaraBayaran == '9' ||  $idCaraBayaran == '11')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh </td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikhCek" id="txtTarikhCek" value="$beanMaklumatBayaran.tarikhCek" size="9" $readonly class="$inputTextClass" onblur="check_date(this);"/>
            #if ($mode != 'viewBayaran') <a href="javascript:displayDatePicker('txtTarikhCek',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Rujukan</td>
          <td>:</td>
          <td><input type="text" name="txtNoRujukan" id="txtNoRujukan" value="$beanMaklumatBayaran.noRujukan" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        #end
        <tr>
          <td>#if ($mode != 'viewBayaran')<span class="style1">*</span>#end</td>
          <td>Amaun (RM)</td>
          <td>:</td>
          <td><input type="text" name="txtAmaun" id="txtAmaun" value="$beanMaklumatBayaran.amaun" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatBayaran.amaun');" style="text-align:right"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No Resit</td>
          <td>:</td>
          <td><input type="text" name="txtNoResit" id="txtNoResit" value="$beanMaklumatBayaran.noResit" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Resit</td>
          <td>:</td>
          <td><input type="text" name="txtTarikhResit" id="txtTarikhResit" value="$beanMaklumatBayaran.tarikhResit" $readonly class="$inputTextClass" size="9" onblur="check_date(this);"/>
            #if ($mode != 'viewBayaran') <a href="javascript:displayDatePicker('txtTarikhResit',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end</td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Butiran</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtButiran" id="txtButiran" cols="50" rows="5" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$beanMaklumatBayaran.butiran</textarea></td>
        </tr>
        #if ($idModBayaran == 'P')
        <tr>
          <td>&nbsp;</td>
          <td>No Daftar Mel</td>
          <td>:</td>
          <td><input type="text" name="txtNoMel" id="txtNoMel" value="$beanMaklumatBayaran.noMel" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($mode == 'updateBayaran')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> #if ($mode == 'newBayaran')
            <input name="cmdSimpan" type="button" value="Simpan" onclick="simpanBayaran()"/>
            <input name="cmdBatal" type="button" value="Batal" onclick="batal()"/>
            #end
            #if ($mode == 'viewBayaran')
            <input name="cmdSimpan" type="button" value="Kemaskini" onclick="kemaskiniBayaran()"/>
            <input name="cmdSimpan" type="button" value="Hapus" onclick="hapusBayaran()"/>
            <input name="cmdBatal" type="button" value="Kembali" onclick="batal()"/>
            <!--
            <input type="button" name="cdmCetak2" id="cdmCetak2" value="Cetak" onclick="javascript:setTable('tableReport1')"/>
            -->
            #end
            #if ($mode == 'updateBayaran')
            <input name="cmdSimpan" type="button" value="Simpan" onclick="simpanKemaskiniBayaran()"/>
            <input name="cmdBatal" type="button" value="Batal" onclick="batalKemaskiniBayaran()"/>
            #end </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<fieldset id="tableReport1" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratCekTidakSah('$idBayaran')"> Surat Cek Tidak Sah </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratCekTamatTempoh('$idBayaran')"> Surat Cek Tamat Tempoh</a></td>
  </tr>
</table>
</fieldset>
