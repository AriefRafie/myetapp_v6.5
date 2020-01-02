
<style type="text/css">
<!--
.style1 {
	font-weight: bold
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">  
  #foreach ($beanMaklumatJAS in $BeanMaklumatJAS)
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Tarikh Tamat Kelulusan Dasar</td>
    <td width="1%">:</td>
    <td width="70%"><strong>$beanMaklumatJAS.tarikhTamatKelulusanDasar</strong></td>
  </tr>
  <tr>
    <td width="1%">#if ($mode!= 'view')<span class="style1">*</span>#end</td>
    <td width="28%">No Ruj. Jabatan Alam Sekitar</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNoRujJAS" type="text" class="$inputTextClass" id="txtNoRujJAS" value="$beanMaklumatJAS.txtNoRujJAS" size="40" maxlength="400" $readonly $inputtextclass /></td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Surat Kelulusan</td>
    <td>:</td>
    <td><input name="txtTarikhSuratLulusJAS" type="text" class="$inputTextClass" id="txtTarikhSuratLulusJAS" onblur="check_date(this)" value="$beanMaklumatJAS.txtTarikhSuratJAS" size="9" maxlength="10" $readonly/>
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhSuratLulusJAS',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Terima</td>
    <td>:</td>
    <td><input name="txtTarikhTerimaJAS" type="text" class="$inputTextClass" id="txtTarikhTerimaJAS" onblur="check_date(this)" value="$beanMaklumatJAS.txtTarikhTerimaJAS" size="9" maxlength="10" $readonly $inputtextclass/>
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhTerimaJAS',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Keputusan</td>
    <td>:</td>
    <td><select name="socKeputusanJAS" id="socKeputusanJAS" style="width:100px;" $readonly class="$inputTextClass" $inputTextClass>
        
        
        
      
	#if($beanMaklumatJAS.socKeputusanJAS == '1')               
      
      
      
        
        
        <option value="">SILA PILIH</option>
        <option value="1" selected="selected">1 - LULUS</option>
        <option value="2">2 - TOLAK </option>
        
        
        
         
    #elseif($beanMaklumatJAS.socKeputusanJAS == '2')              
      
      
        
        
        <option value="">SILA PILIH</option>
        <option value="1">1 - LULUS</option>
        <option value="2" selected="selected">2 - TOLAK </option>
        
        
        
      
    #elseif($beanMaklumatJAS.socKeputusanJAS == '')             
      
      
        
        
        <option value="" selected="selected">SILA PILIH</option>
        <option value="1">1 - LULUS</option>
        <option value="2">2 - TOLAK </option>
        
        
        
      
    #end                                                             
    
    
      
      
      </select>
    </td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Diluluskan</td>
    <td>:</td>
    <td><input name="txtTarikhLulusJAS" type="text" class="$inputTextClass" id="txtTarikhLulusJAS" onblur="check_date(this)" value="$beanMaklumatJAS.txtTarikhLulusJAS" size="9" maxlength="10" $readonly/>
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhLulusJAS',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tempoh Kelulusan EIA</td>
    <td>:</td>
    <td><input name="txtTempohLulusJAS" type="text" class="$inputTextClass" id="txtTempohLulusJAS"  onkeyup="validateNumber(this,this.value,'$beanMaklumatJAS.txtTempohLulusJAS')" value="$beanMaklumatJAS.txtTempohLulusJAS" size="2" maxlength="2" $readonly/>
      $selectTempoh</td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Cawangan Jabatan Alam Sekitar</td>
    <td>&nbsp;</td>
    <td>$selectNegeri </td>
  </tr>
  #if ($mode == 'update')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'view')
      <input name="cmdKemaskini" type="button" onclick="doKemaskiniMaklumatJAS()" value="Kemaskini" />
      #end
      #if ($mode == 'update')
      <input name="cmdSimpan" type="button" onclick="doSimpanKemaskiniMaklumatJAS()" value="Simpan" />
      <input name="cmdBatal" type="button" onclick="doBatalMaklumatJAS()" value="Batal" />
      #end </td>
  </tr>
  #end
</table>
