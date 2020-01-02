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
        #foreach ($beanMaklumatBayaranRoyalti in $BeanMaklumatBayaranRoyalti)
        <tr>
          <td width="1%">#if ($mode != 'viewBayaranRoyalti')<span class="style1">*</span>#end</td>
          <td width="28%">Tarikh Transaksi</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikh" id="txtTarikh" value="$beanMaklumatBayaranRoyalti.tarikh" size="9" $readonly class="$inputTextClass" onblur="check_date(this)"/>
            #if ($mode != 'viewBayaranRoyalti') <a href="javascript:displayDatePicker('txtTarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'viewBayaranRoyalti')<span class="style1">*</span>#end</td>
          <td width="28%">Mod Bayaran</td>
          <td width="1%">:</td>
		 <td width="70%">$selectModBayaran</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'viewBayaranRoyalti')<span class="style1">*</span>#end</td>
          <td width="28%">Cara Bayar</td>
          <td width="1%">:</td>
          <td width="70%">$selectCaraBayaran</td>
        </tr>
        #if ($idCaraBayaran == '5' || $idCaraBayaran == '9' ||  $idCaraBayaran == '11')
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
          <td width="28%">Tarikh Cek</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikhCek" id="txtTarikhCek" value="$beanMaklumatBayaranRoyalti.tarikhCek" size="9" $readonly class="$inputTextClass" onblur="check_date(this)"/>
          #if ($mode != 'viewBayaranRoyalti') <a href="javascript:displayDatePicker('txtTarikhCek',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Cek / Rujukan</td>
          <td>:</td>
          <td><input type="text" name="txtNoRujukan" id="txtNoRujukan" value="$beanMaklumatBayaranRoyalti.noRujukan" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        #end
        <tr>
          <td>#if ($mode != 'viewBayaranRoyalti')<span class="style1">*</span>#end</td>
          <td>Amaun (RM)</td>
          <td>:</td>
          <td><input type="text" name="txtAmaun" id="txtAmaun" value="$beanMaklumatBayaranRoyalti.amaun" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatBayaranRoyalti.amaun');" style="text-align:left"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No Resit</td>
          <td>:</td>
          <td>
          	<input type="text" name="txtNoResit" id="txtNoResit" value="$beanMaklumatBayaranRoyalti.noResit" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"/>          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Resit</td>
          <td>:</td>
          <td><input type="text" name="txtTarikhResit" id="txtTarikhResit" value="$beanMaklumatBayaranRoyalti.tarikhResit" $readonly class="$inputTextClass" size="9" onblur="check_date(this)"/>
            #if ($mode != 'viewBayaranRoyalti') <a href="javascript:displayDatePicker('txtTarikhResit',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end</td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Butiran</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtButiran" id="txtButiran" cols="50" rows="5" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$!beanMaklumatBayaranRoyalti.butiran</textarea></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No Daftar Mel</td>
          <td>:</td>
          <td><input type="text" name="txtNoMel" id="txtNoMel" value="$!beanMaklumatBayaranRoyalti.noMel" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($mode == 'updateBayaranRoyalti')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> #if ($mode == 'newBayaranRoyalti')
            <input name="cmdSimpan" type="button" value="Simpan" onclick="simpanBayaranRoyalti()"/>
            <input name="cmdBatal" type="button" value="Batal" onclick="batal()"/>
            #end
            #if ($mode == 'viewBayaranRoyalti')
            <input name="cmdSimpan" type="button" value="Kemaskini" onclick="kemaskiniBayaranRoyalti()"/>
            <input name="cmdSimpan" type="button" value="Hapus" onclick="hapusBayaranRoyalti()"/>
            <input name="cmdBatal" type="button" value="Kembali" onclick="batal()"/>
            #end
            #if ($mode == 'updateBayaranRoyalti')
            <input name="cmdSimpan" type="button" value="Simpan" onclick="simpanKemaskiniBayaranRoyalti()"/>
            <input name="cmdBatal" type="button" value="Batal" onclick="batalKemaskiniBayaranRoyalti()"/>
            #end </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>


