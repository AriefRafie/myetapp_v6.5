<fieldset>
<legend><strong>MAKLUMAT PENAMATAN PERJANJIAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
  <tr>
    <td width="1%">#if($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">Tarikh Terima</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" onblur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass"/>
      #if($mode != 'view')<a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
  </tr>
  <tr>
    <td width="1%">#if($mode != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Tarikh Surat</td>
    <td>:</td>
    <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat" onblur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
      #if($mode != 'view')<a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td valign="top">No. Rujukan Surat</td>
    <td>:</td>
    <td><input name="txtNoRujukanSurat" type="text" class="$inputTextClass" id="txtNoRujukanSurat" value="$beanMaklumatPermohonan.noRujukanSurat" $readonly onblur="this.value=this.value.toUpperCase();" size="50" maxlength="50"/></td>
  </tr>
  #if ($idStatus == '1610221')
  <tr>
    <td width="1%" valign="top">#if($mode != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Sebab Penamatan</td>
    <td valign="top">:</td>
    <td><select name="socFlagSebabTamat" id="socFlagSebabTamat" $readonly class="$disabled" $disabled >
        
        
        
                          
          #if ($beanMaklumatPermohonan.flagSebabTamat == '1')
          	  
                          
        
        
        <option>SILA PILIH</option>
        <option value="1" selected="selected">PEMBATALAN PERJANJIAN</option>
        <option value="2">PELANGGARAN PERJANJIAN</option>
        <option value="3">PERMINTAAN KJP</option>
        
        
        
                          
          #elseif ($beanMaklumatPermohonan.flagSebabTamat == '2')
          	  
                          
        
        
        <option>SILA PILIH</option>
        <option value="1">PEMBATALAN PERJANJIAN</option>
        <option value="2" selected="selected">PELANGGARAN PERJANJIAN</option>
        <option value="3">PERMINTAAN KJP</option>
        
        
        
                          
           #elseif ($beanMaklumatPermohonan.flagSebabTamat == '3')
          	  
                          
        
        
        <option>SILA PILIH</option>
        <option value="1">PEMBATALAN PERJANJIAN</option>
        <option value="2">PELANGGARAN PERJANJIAN</option>
        <option value="3" selected="selected">PERMINTAAN KJP</option>
        
        
        
                          
           #else
          	  
                          
        
        
        <option selected="selected">SILA PILIH</option>
        <option value="1">PEMBATALAN PERJANJIAN</option>
        <option value="2">PELANGGARAN PERJANJIAN</option>
        <option value="3">PERMINTAAN KJP</option>
        
        
        
                          
           #end
          	
                        
      
      
      </select></td>
  </tr>
  #else
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Keputusan</td>
    <td width="1%">:</td>
    <td width="70%"> 
      #if ($beanMaklumatPermohonan.flagSebabTamat == '1')
      PEMBATALAN PERJANJIAN
      #elseif ($beanMaklumatPermohonan.flagSebabTamat == '2')
      PELANGGARAN PERJANJIAN
      #elseif ($beanMaklumatPermohonan.flagSebabTamat == '3')
      PERMINTAAN KJP
      #end</td>
  </tr>
  #end
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.catatan</textarea></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'view')
      <input name="cmdKemaskini" type="button" value="Kemaskini" onclick="kemaskini()"/>
      #end
      #if ($mode == 'update')
      <input name="cmdSimpan" type="button" value="Simpan" onclick="simpanKemaskiniMaklumatPenamatan('$idStatus')"/>
      <input name="cmdBatal" type="button" value="Batal" onclick="batal()"/>
      #end </td>
  </tr>
</table>
</fieldset>
