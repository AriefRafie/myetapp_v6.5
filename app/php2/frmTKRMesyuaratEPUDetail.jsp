<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT MESYUARAT</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatMesyuarat in $BeanMaklumatMesyuarat)
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style2">*</span>#end</td>
          <td width="28%" valign="top">Tajuk Mesyuarat</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top"><textarea name="txtTajukMesyuarat" id="txtTajukMesyuarat" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$beanMaklumatMesyuarat.tajukMesyuarat</textarea></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style2">*</span>#end</td>
          <td>Bil. Mesyuarat</td>
          <td>:</td>
          <td><input name="txtBilMesyuarat" type="text" class="$inputTextClass" id="txtBilMesyuarat" value="$beanMaklumatMesyuarat.bilMesyuarat" size="9" maxlength="12" $readonly onblur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style2">*</span>#end</td>
          <td width="28%">Tarikh Mesyuarat</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikhMesyuarat" id="txtTarikhMesyuarat" size="9" onBlur="check_date(this)" value="$beanMaklumatMesyuarat.tarikhMesyuarat" $readonly class="$inputTextClass">
            #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhMesyuarat',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style2">*</span>#end</td>
          <td>Masa Mesyuarat</td>
          <td>:</td>
          <td>$selectJamDari$selectMinitDari
            &nbsp;Hingga&nbsp;
            $selectJamHingga$selectMinitHingga </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4"><fieldset>
            <legend>KEPUTUSAN</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #if ($idStatus == '1610191')
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style2">*</span>#end</td>
                <td width="28%">Keputusan</td>
                <td width="1%">:</td>
                <td width="70%">
                <select name="socSyor" id="socSyor" style="width:200px;" $readonly class="$inputTextClass" $inputTextClass>
    			#if($beanMaklumatMesyuarat.flagSyor == 'L')        
                    <option value="">SILA PILIH</option>
                    <option value="L" selected="selected">L - LULUS</option>
                    <option value="T">T - TOLAK</option>
                #elseif($beanMaklumatMesyuarat.flagSyor == 'T')
					<option value="">SILA PILIH</option>
                    <option value="L">L - LULUS</option>
                    <option value="T" selected="selected">T - TOLAK</option>
                #else
					<option value="" selected="selected">SILA PILIH</option>
                    <option value="L">L - LULUS</option>
                    <option value="T">T - TOLAK</option>
                #end
				</select></td>
              </tr>
              #else
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Keputusan</td>
                <td width="1%">:</td>
                <td width="70%">
                #if($idKeputusan == 'L')
                LULUS
                #elseif($idKeputusan == 'T')
                TOLAK
                #end
                </td>
              </tr>
              #end
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Catatan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatMesyuarat.catatan</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
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
            <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniMesyuarat()" >
            #if ($idStatus == '1610191')
            <input name="cmdSelesai" type="button" value="Selesai" onClick="doSeterusnya()" >
            #end
            #end 
            #if ($mode == 'update')
            <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskiniMesyuarat()" />
            <input name="txtBatal" type="button" value="Batal" onClick="batalKemaskiniMesyuarat()" />
            #end </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
