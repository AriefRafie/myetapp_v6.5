<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style4 {font-style: italic; color: #FF0000;}
-->
</style>

#set($saizSyorMesyuarat="1500")
#set($saizTujuanMesyuarat="900")

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td><table align="center" width="100%">
        #foreach($beanMaklumatMesyuarat in $BeanMaklumatMesyuarat)
        <tr>
          <td width="1%" valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td width="30%" valign="top">Tarikh Mesyuarat</td>
          <td width="1%" valign="top">:</td>
          <td width="68%" valign="top"><input name="tarikhMesyuarat" type="text" class="$inputTextClass"  id="tarikhMesyuarat" value="$beanMaklumatMesyuarat.tarikhMesyuarat" size="9" maxlength="10" $readonly onBlur="check_date(this);"/>
            <a href="javascript:displayDatePicker('tarikhMesyuarat',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end </td>
        </tr>
        <tr>
          <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td>Bil. Mesyuarat</td>
          <td>:</td>
          <td><input name="txtBilMesyuarat" type="text" class="$inputTextClass" id="txtBilMesyuarat" value="$beanMaklumatMesyuarat.bilMesyuarat" size="5" maxlength="10" $readonly /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Masa Mesyuarat</td>
          <td>:</td>
          <td><input type="text" name="txtWaktuDari" id="txtWaktuDari" size="4" value="$beanMaklumatMesyuarat.waktuMesyuaratDari" $readonly class="$inputTextClass" onblur="validateNumber(this,this.value,'$beanMaklumatMesyuarat.waktuMesyuaratDari');validateLength();" maxlength="4">
            &nbsp;
            <select name="socMasaDari" id="socMasaDari" style="width:100px;" $readonlyPopup class="$inputTextClass" $inputTextClass onblur="checkAMPM()"> 
			  #if($beanMaklumatMesyuarat.idMasaDari == 'AM')
              <option value="">SILA PILIH</option>
              <option value="AM" selected="selected"> PAGI </option>
              <option value="PM"> PETANG </option>
    		  #elseif($beanMaklumatMesyuarat.idMasaDari == 'PM')
              <option value="">SILA PILIH</option>
              <option value="AM"> PAGI </option>
              <option value="PM" selected="selected"> PETANG </option>
    		  #else
              <option value="" selected="selected">SILA PILIH</option>
              <option value="AM"> PAGI </option>
              <option value="PM"> PETANG </option>
			  #end
            </select>
            Hingga
            <input type="text" name="txtWaktuHingga" id="txtWaktuHingga" size="4" value="$beanMaklumatMesyuarat.waktuMesyuaratHingga" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value,'$beanMaklumatMesyuarat.waktuMesyuaratHingga');" onblur="validateLength2();" maxlength="4">
            <select name="socMasaHingga" id="socMasaHingga" style="width:100px;" $readonly class="$inputTextClass" $inputTextClass onblur="checkAMPM2()">
			  #if($beanMaklumatMesyuarat.idMasaHingga == 'AM')
              <option value="">SILA PILIH</option>
              <option value="AM" selected="selected"> PAGI </option>
              <option value="PM"> PETANG </option>
    		  #elseif($beanMaklumatMesyuarat.idMasaHingga == 'PM')
              <option value="">SILA PILIH</option>
              <option value="AM"> PAGI </option>
              <option value="PM" selected="selected"> PETANG </option>
    		  #else
              <option value="" selected="selected">SILA PILIH</option>
              <option value="AM"> PAGI </option>
              <option value="PM"> PETANG </option>
			  #end
            </select>          </td>
        </tr>
        #if ($mode == 'update')
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td class="style4">Sila pastikan masa adalah dalam format 24 jam (HHMM).</td>
        </tr>
        #end
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Tajuk Mesyuarat</td>
          <td valign="top">:</td>
          <td valign="top"><p>
              <textarea name="txtTujuanMesyuarat" id="txtTujuanMesyuarat" rows="2" cols="40" $readonly class="$inputTextClass" onkeyup="textCounter(this.form.txtTujuanMesyuarat,this.form.remLen6,$!saizTujuanMesyuarat);" onkeydown="textCounter(this.form.txtTujuanMesyuarat,this.form.remLen6,$!saizTujuanMesyuarat);">$beanMaklumatMesyuarat.tujuanMesyuarat</textarea>
            </p></td>
        </tr>
        #if ($mode == 'update')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen6" size="3" maxlength="3" value="$!saizTujuanMesyuarat" /></td>
        </tr>
        #end
        <tr>
          <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td valign="top">Syor Mesyuarat</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="syorMesyuarat" class="$inputTextClass"  $readonly cols="40" rows="5" id="syorMesyuarat" onkeyup="textCounter(this.form.syorMesyuarat,this.form.remLen5,$!saizSyorMesyuarat);" onkeydown="textCounter(this.form.syorMesyuarat,this.form.remLen5,$!saizSyorMesyuarat);">$beanMaklumatMesyuarat.syorMesyuarat</textarea></td>
        </tr>
        #if ($mode == 'update')
        <tr>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen5" size="3" maxlength="3" value="$!saizSyorMesyuarat" /></td>
        </tr>
        #end
        #if ($mode == 'update')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td> #if ($mode == 'update')
            <input type="button" name="cmdDaftarBaru" id="cmdSimpanKemaskini" value="Simpan" onclick="simpanKemaskiniMaklumatMesyuarat()"/>
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="doBatalMesyuarat()"/>
            #end 
            #if ($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniMesyuarat()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doBackToList()"/>
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport1')"/>
            #end
            #end
    </table></td>
  </tr>
  #end
</table>
<fieldset id="tableReport1" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakKertasRingkasan('$idFail','$idMohonTamat')"> Kertas Ringkasan </a></td>
  </tr>
</table>
</fieldset>