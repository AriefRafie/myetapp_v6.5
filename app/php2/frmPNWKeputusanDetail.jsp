<fieldset>
<legend>KEPUTUSAN</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach($beanMaklumatKeputusan in $BeanMaklumatKeputusan)
  <input name="kelulusan" type="hidden" id="kelulusan" value="$beanMaklumatKeputusan.keputusan"/>
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201')
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Keputusan</td>
    <td width="1%">:</td>
    <td width="70%"><select name="socKeputusan" id="socKeputusan" style="width:140px;" $readonly class="$disabled" $disabled>
     
        #if ($beanMaklumatKeputusan.keputusan == 'L')
        <option>SILA PILIH</option>
        <option value="L" selected>LULUS</option>
        <option value="T">TOLAK</option>
		#elseif ($beanMaklumatKeputusan.keputusan == 'T')
        <option>SILA PILIH</option>
        <option value="L">LULUS</option>
        <option value="T" selected>TOLAK</option>
        #else
        <option selected>SILA PILIH</option>
        <option value="L">LULUS</option>
        <option value="T">TOLAK</option>
        #end
      </select>
    </td>
  </tr>
  #end
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Hantar Surat</td>
    <td>:</td>
    <td><input name="txtTarikhKeputusan" type="text" class="$inputTextClass" id="txtTarikhKeputusan" onBlur="check_date(this)" value="$beanMaklumatKeputusan.tarikhKeputusan" size="9" maxlength="10" $readonly>
      #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhKeputusan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
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
    <td>#if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="doKemaskini()"/>
      #if ($idStatus == '1610206')
      	<input type="button" name="cmdHantar" id="cmdHantar" value="Selesai" onclick="doSeterusnya()"/>
        <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onclick="gotoBatalPermohonan()"/>
#end #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKeputusan('$idStatus')"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
      #end </td>
  </tr>
  #end
</table>
</fieldset>
