
<style type="text/css">
<!--
.style1 {
	font-weight: bold
}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatHIDRO in $BeanMaklumatHIDRO)
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Tarikh Tamat Kelulusan Dasar</td>
    <td width="1%">:</td>
    <td width="70%"><strong>$beanMaklumatHIDRO.tarikhTamatKelulusanDasar</strong></td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">No Ruj Pusat Hidrografi Nasional</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNoRujHidro" type="text" class="$inputTextClass" id="txtNoRujHidro" value="$beanMaklumatHIDRO.txtNoRujHidro" size="40" maxlength="400" $readonly /></td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Surat Kelulusan</td>
    <td>:</td>
    <td><input name="txtTarikhSuratLulusHIDRO" type="text" class="$inputTextClass" id="txtTarikhSuratLulusHIDRO" onblur="check_date(this)" value="$beanMaklumatHIDRO.txtTarikhSuratHIDRO" size="9" maxlength="10" $readonly/>
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhSuratLulusHIDRO',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Terima</td>
    <td>:</td>
    <td><input name="txtTarikhTerimaHIDRO" type="text" class="$inputTextClass" id="txtTarikhTerimaHIDRO" onblur="check_date(this)" value="$beanMaklumatHIDRO.txtTarikhTerimaHIDRO" size="9" maxlength="10" $readonly/>
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhTerimaHIDRO',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Keputusan</td>
    <td>:</td>
    <td><select name="socKeputusanHIDRO" id="socKeputusanHIDRO" style="width:100px;"  $readonly class="$inputTextClass" $inputTextClass>
        
        
	#if($beanMaklumatHIDRO.socKeputusanHIDRO == '1')               
      
        
        <option value="">SILA PILIH</option>
        <option value="1" selected="selected">1 - LULUS</option>
        <option value="2">2 - TOLAK </option>
        
        
    #elseif($beanMaklumatHIDRO.socKeputusanHIDRO == '2')              
      
        
        <option value="">SILA PILIH</option>
        <option value="1">1 - LULUS</option>
        <option value="2" selected="selected">2 - TOLAK </option>
        
        
    #elseif($beanMaklumatHIDRO.socKeputusanHIDRO == '')             
      
        
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
    <td><input name="txtTarikhLulusHIDRO" type="text" class="$inputTextClass" id="txtTarikhLulusHIDRO" onblur="check_date(this)" value="$beanMaklumatHIDRO.txtTarikhLulusHIDRO" size="9" maxlength="10" $readonly/>
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhLulusHIDRO',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
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
      <input name="cmdKemaskini" type="button" onclick="doKemaskiniMaklumatHIDRO()" value="Kemaskini" />
      #end
      #if ($mode == 'update')
      <input name="cmdSimpan2" type="button" onclick="doSimpanKemaskiniMaklumatHIDRO()" value="Simpan" />
      <input name="cmdBatal" type="button" onclick="doBatalMaklumatHIDRO()" value="Batal" />
      #end 
      #if ($mode == 'view' && $idStatus == '1610236')
      <input name="cmdTambahHidro2" type="button" onclick="seterusnya()" value="Seterusnya" />
      #end 
      </td>
  </tr>
  #end
</table>
