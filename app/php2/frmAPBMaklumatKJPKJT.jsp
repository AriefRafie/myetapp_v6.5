<!-- saiz text -->
#set($saizTxtMaklumatTambahan="500")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatKJPKJT in $BeanMaklumatKJPKJT)
  
  #set($idDokumen = $beanMaklumatKJPKJT.idDokumen)
  #set($idAgensi = $beanMaklumatKJPKJT.idAgensi)
  #set($idKementerian = $beanMaklumatKJPKJT.idKementerian)
  #set($flagStatus = $beanMaklumatKJPKJT.flagStatus)
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Jenis Dokumen</td>
    <td>:</td>
    <td>$selectDokumen</td>
  </tr>
  <tr>
    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Kementerian</td>
    <td width="1%">:</td>
    <td width="70%">$selectKementerian</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Agensi</td>
    <td>:</td>
    <td>$selectAgensi</td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Hantar</td>
    <td>:</td>
    <td><input type="text" name="txtTarikhHantar" id="txtTarikhHantar" size="9" onBlur="check_date(this);calculcateDate()" value="$beanMaklumatKJPKJT.tarikhHantar" $readonlyPopup class="$inputTextClassPopup">
      #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Jangkamasa</td>
    <td>:</td>
    <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatKJPKJT.jangkamasa" onBlur="validateNumber(this,this.value,'$beanMaklumatKJPKJT.jangkamasa');calculcateDate()" $readonlyPopup class="$inputTextClassPopup">
      Hari</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Dijangka Terima</td>
    <td>:</td>
    <td><input type="text" name="txtTarikhJangkaTerima" id="txtTarikhJangkaTerima" size="9" value="$beanMaklumatKJPKJT.tarikhJangkaTerima" readonly class="disabled"></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Maklumat Tambahan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtMaklumatTambahan" id="txtMaklumatTambahan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup" onKeyUp="textCounter(this.form.txtMaklumatTambahan,this.form.remLen10,$!saizTxtMaklumatTambahan);" onKeyDown="textCounter(this.form.txtMaklumatTambahan,this.form.remLen10,$!saizTxtMaklumatTambahan);" >$beanMaklumatKJPKJT.maklumatTambahan</textarea></td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">Baki Aksara :&nbsp;
        <input type="text" readonly="readonly" class="disabled" name="remLen10" size="3" maxlength="3" value="$!saizTxtMaklumatTambahan" /></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>Untuk Perhatian</td>
    <td>:</td>
    <td><input type="text" $readonlyPopup class="$inputTextClassPopup" name="txtUntukPerhatian" size="40" maxlength="80" value="$beanMaklumatKJPKJT.untukPerhatian" id="txtUntukPerhatian" /></td>
  </tr>
  <tr>
    <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td>Status</td>
    <td>:</td>
    <td>$selectStatusJabatanTeknikal</td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if ($flagUlasan == 'KJP' || $flagUlasan == 'JUPEM')
  <tr>
    <td colspan="4"><fieldset>
      <legend><strong>ULASAN</strong></legend>
      #parse("app/php2/frmAPBJawapanUlasanKJP.jsp")
      </fieldset></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($modePopup == 'new')
      <input name="cmdSimpan" type="button" onClick="doSimpanMaklumatKJPKJT('$flagUlasan')" value="Simpan">
      <input name="cmdBatal" type="button" onClick="doBatalMaklumatKJPKJT()" value="Batal">
      #end
      #if ($modePopup == 'view')
      <input name="cmdKemaskini" type="button" onClick="doKemaskiniMaklumatKJPKJT()" value="Kemaskini">
      <input name="cmdHapus" type="button" onClick="doHapusMaklumatKJPKJT()" value="Hapus">
      <input name="cmdBatal" type="button" onClick="doBatalMaklumatKJPKJT()" value="Kembali">
      #if ($idDokumen == '1' && $idKementerian == '18' && $idAgensi == '435')
      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport1')"/>
      #end
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpan2" type="button" onclick="doSimpanKemaskiniMaklumatKJPKJT('$flagUlasan')" value="Simpan" />
      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatKJPKJT()" value="Batal">
      #end </td>
  </tr>
</table>
<fieldset id="tableReport1" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPengesahanPelanKawasan('$idFail','$idUlasanTeknikal')"> Pengesahan Pelan Kawasan </a> </td>
  </tr>
</table>
</fieldset>
