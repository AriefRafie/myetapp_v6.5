<style type="text/css">
<!--
.style1 {color: #FF0000}
.style3 {color: #FF0000; font-style: italic; }
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatMesyuarat in $BeanMaklumatMesyuarat)
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Mesyuarat</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtTarikhMesyuarat" id="txtTarikhMesyuarat" size="9" onBlur="check_date(this)" value="$beanMaklumatMesyuarat.tarikhMesyuarat" $readonly class="$inputTextClass">
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhMesyuarat',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Bil. Mesyuarat</td>
    <td>:</td>
    <td><input name="txtBilMesyuarat" type="text" class="$inputTextClass" id="txtBilMesyuarat" value="$beanMaklumatMesyuarat.bilMesyuarat" size="9" maxlength="12" $readonly onblur="this.value=this.value.toUpperCase();"/>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Masa Mesyuarat</td>
    <td>:</td>
    <td>$selectJamDari$selectMinitDari
      &nbsp;Hingga&nbsp;
      $selectJamHingga$selectMinitHingga </td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><span class="style3">Sila pastikan masa adalah dalam format 24 jam (HHMM).</span></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>Tempat Mesyuarat</td>
    <td>:</td>
    <td>$selectLokasi</td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Tajuk Mesyuarat</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtTujuanMesyuarat" id="txtTujuanMesyuarat" rows="5" cols="50" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$beanMaklumatMesyuarat.tujuanMesyuarat</textarea>
    </td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td><textarea name="txtCatatanMesyuarat" id="txtCatatanMesyuarat" rows="5" cols="50" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$beanMaklumatMesyuarat.catatanMesyuarat</textarea></td>
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
    <td> 
      #if ($mode == 'view')
      <input name="cmdkmskiniMesyuarat" type="button" value="Kemaskini" onClick="kemaskiniMesyuarat()" >
      <input name="cmdBatalMesyuarat" type="button" value="Kembali" onClick="batalMesyuarat()" />
      <!-- input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/ -->
      #end  
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="simpanKemaskiniMesyuarat()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onclick="batalKemaskiniMesyuarat()"/>
      #end </td>
  </tr>
  #end
</table>
