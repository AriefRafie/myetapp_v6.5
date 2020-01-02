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
      <legend><strong>MAKLUMAT PELARASAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPelarasanD in $BeanMaklumatPelarasanD)
        <tr>
          <td width="1%">#if ($mode != 'viewPelarasanD')<span class="style1">*</span>#end</td>
          <td width="28%">Tarikh Transaksi</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikh" id="txtTarikh" value="$beanMaklumatPelarasanD.tarikh" size="9" $readonly class="$inputTextClass" onblur="cekTarikh(this)"/>
            #if ($mode != 'viewPelarasanD') <a href="javascript:displayDatePicker('txtTarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'viewPelarasanD')<span class="style1">*</span>#end</td>
          <td width="28%">Jenis Pelarasan</td>
          <td width="1%">:</td>
          <td width="70%"><select name="socJenisPelarasan" id="socJenisPelarasan" $readonly class="$inputTextClass" $inputTextClass style="width:260px;" onchange="onChangeCek(this)">
              <option $selected>SILA PILIH</option>
              <option $selectedD value="D">PENAMBAHAN</option>
              <option $selectedK value="K">PENGURANGAN</option>
              <option $selectedC value="C">PEMULANGAN SEMULA CEK/WANG POS</option>
              <option $selectedR value="R">RAMPASAN DEPOSIT</option>
            </select></td>
        </tr>
        #if($cek == 'C')
        <tr>
        <td>&nbsp;</td>
          <td>Bank</td>
          <td>:</td>
          <td>$selectBank</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh Cek</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikhCek" id="txtTarikhCek" value="$beanMaklumatPelarasanD.tarikhCek" size="9" $readonly class="$inputTextClass" onblur="check_date(this)"/>
          #if ($mode != 'viewBayaranD') <a href="javascript:displayDatePicker('txtTarikhCek',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>No. Cek / Rujukan</td>
          <td>:</td>
          <td><input type="text" name="txtNoRujukan" id="txtNoRujukan" value="$beanMaklumatPelarasanD.noRujukan" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();" style="width:260px;"/></td>
        <tr>
          <td>#if ($mode != 'viewPelarasanD')<span class="style1">*</span>#end</td>
          <td>Amaun (RM)</td>
          <td>:</td>
          <td><input type="text" name="txtAmaun" id="txtAmaun" value="$beanMaklumatPelarasanD.amaun" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPelarasanD.amaun');" style="text-align:right;width:260px;"/></td>
        </tr>
        <td>&nbsp;</td>
          <td>No Resit / Baucer</td>
          <td>:</td>
          <td><input type="text" name="txtNoResit" id="txtNoResit" value="$beanMaklumatPelarasanD.noResit" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Resit / Baucer</td>
          <td>:</td>
          <td><input type="text" name="txtTarikhResit" id="txtTarikhResit" value="$beanMaklumatPelarasanD.tarikhResit" $readonly class="$inputTextClass" size="9" onblur="check_date(this)"/>
            #if ($mode != 'viewBayaranD') <a href="javascript:displayDatePicker('txtTarikhResit',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end</td>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Butiran</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtButiran" id="txtButiran" cols="50" rows="5" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$beanMaklumatPelarasanD.butiran</textarea></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($mode == 'updatePelarasanD')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> #if ($mode == 'newPelarasanD')
            <input name="cmdSimpan" type="button" value="Simpan" onclick="simpanPelarasanD()"/>
            <input name="cmdBatal" type="button" value="Batal" onclick="batal()"/>
        #end
        #if ($mode == 'viewPelarasanD')
        <input name="cmdSimpan" type="button" value="Kemaskini" onclick="kemaskiniPelarasanD()"/>
        #if($cek == 'C')<input name="cmdCetak2" type="button" value="Cetak" onclick="javascript:setTable('tableSuratPemulangan')"/>#end
        #if($cek == 'R')<input name="cmdCetak2" type="button" value="Cetak" onclick="javascript:setTable('tableSuratRampasan')"/>#end
            <input name="cmdSimpan" type="button" value="Hapus" onclick="hapusPelarasanD()"/>
            <input name="cmdBatal" type="button" value="Kembali" onclick="batal()"/>
            #end
            #if ($mode == 'updatePelarasanD')
            <input name="cmdSimpan" type="button" value="Simpan" onclick="simpanKemaskiniPelarasanD()"/>
            #if($cek == 'C') 
            <input name="cmdCetak" type="button" value="Cetak" onclick="javascript:setTable('tableSuratPemulangan')"/> 
            #end
            #if($cek == 'R') 
            <input name="cmdCetak" type="button" value="Cetak" onclick="javascript:setTable('tableSuratRampasan')"/> 
            #end
            <input name="cmdBatal" type="button" value="Batal" onclick="batalKemaskiniPelarasanD()"/>
            #end </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<fieldset id="tableSuratPemulangan" style="display:none;">
<legend><strong>SENARAI CETAKAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><a href="#" class="style2" onClick="javascript:janaSuratPemulanganCek()"> Surat Pemulangan Semula </a></td>
  </tr>
</table>
</fieldset>
<fieldset id="tableSuratRampasan" style="display:none;">
<legend><strong>SENARAI CETAKAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><a href="#" class="style2" onClick="javascript:janaSuratRampasanDeposit('$idHasil')"> Surat Rampasan Deposit </a></td>
  </tr>
</table>
</fieldset>