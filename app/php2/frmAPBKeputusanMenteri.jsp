#set($saizTxtUlasanKeputusan="1000")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatPerakuanKeputusan in $BeanMaklumatPerakuanKeputusan)
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Hantar Kertas Ringkasan</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtTarikhHantarMenteri" type="text" class="$inputTextClass" id="txtTarikhHantarMenteri" onBlur="check_date(this)" value="$beanMaklumatPerakuanKeputusan.tarikhHantarMenteri" size="9" maxlength="10" $readonly>
      #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhHantarMenteri',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Keputusan</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtTarikhKeputusan" type="text" class="$inputTextClass" id="txtTarikhKeputusan" onBlur="check_date(this)" value="$beanMaklumatPerakuanKeputusan.tarikhKeputusanMenteri" size="9" maxlength="10" $readonly>
      #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhKeputusan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Keputusan</td>
    <td width="1%">:</td>
    <td width="70%"><select name="socKeputusan" id="socKeputusan" style="width:120px;" $readonly class="$disabled" $disabled>
        
        
        
	 #if($beanMaklumatPerakuanKeputusan.flagKeputusanMenteri == 'S')
        
        
        
        <option value="">SILA PILIH</option>
        <option value="S" selected="selected"> SETUJU </option>
        <option value="T"> TIDAK SETUJU </option>
        
        
        
     #elseif($beanMaklumatPerakuanKeputusan.flagKeputusanMenteri == 'T')
        
        
        
        <option value="">SILA PILIH</option>
        <option value="S"> SETUJU </option>
        <option value="T" selected="selected"> TIDAK SETUJU </option>
        
        
        
     #else
        
        
        
        <option value="" selected="selected">SILA PILIH</option>
        <option value="S"> SETUJU </option>
        <option value="T"> TIDAK SETUJU </option>
        
        
        
	 #end
      
      
      
      </select>
      &nbsp;&nbsp; 
      #if ($!beanMaklumatPerakuanKeputusan.syorJabatan == 'L') <strong>MELULUSKAN PERMOHONAN INI</strong>#end
      #if ($!beanMaklumatPerakuanKeputusan.syorJabatan == 'D') <strong>MELULUS SECARA DASAR PERMOHONAN INI</strong>#end
      #if ($!beanMaklumatPerakuanKeputusan.syorJabatan == 'T') <strong>MENOLAK PERMOHONAN INI</strong>#end
      #if ($!beanMaklumatPerakuanKeputusan.syorJabatan == 'G') <strong>MENANGGUHKAN PERMOHONAN INI</strong>#end </td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Nama Menteri KeTSA</td>
    <td>:</td>
    <td><input name="txtNamaMenteri" type="text" class="$inputTextClass" id="txtNamaMenteri" value="$beanMaklumatPerakuanKeputusan.namaMenteri" size="50" maxlength="80" $readonly /></td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Ulasan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtUlasanKeputusan" id="txtUlasanKeputusan" rows="5" cols="50" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtUlasanKeputusan,this.form.remLen1,$!saizTxtUlasanKeputusan);" onKeyDown="textCounter(this.form.txtUlasanKeputusan,this.form.remLen1,$!saizTxtUlasanKeputusan);">$beanMaklumatPerakuanKeputusan.ulasanMenteri</textarea></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">Baki Aksara :&nbsp;
      <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtUlasanKeputusan" /></td>
  </tr>
  #end
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top"> #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniKeputusan()"/>
      #if($idStatus == '1610208')
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      #end
      #if($idStatus =='1610205')
      <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniKeputusan()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalKeputusan()"/>
      #end</td>
  </tr>
  #end
</table>
