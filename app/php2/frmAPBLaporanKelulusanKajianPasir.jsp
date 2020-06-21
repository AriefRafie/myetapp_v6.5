
<style type="text/css">
<!--
.style1 {
	font-weight: bold
}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatPasir in $BeanMaklumatPasir)
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Tarikh Tamat Kelulusan Dasar</td>
    <td width="1%">:</td>
    <td width="70%"><strong>$beanMaklumatPasir.tarikhTamatKelulusanDasar</strong></td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">No Ruj Pusat Kajian Pasir</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNoRujPasir" type="text" class="$inputTextClass" id="txtNoRujPasir" value="$beanMaklumatPasir.txtNoRujPasir" size="40" maxlength="400" $readonly /></td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Surat Kelulusan</td>
    <td>:</td>
    <td><input name="txtTarikhSuratLulusPasir" type="text" class="$inputTextClass" id="txtTarikhSuratLulusPasir" onblur="check_date(this)" value="$beanMaklumatPasir.txtTarikhSuratPasir" size="9" maxlength="10" $readonly/>
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhSuratLulusPasir',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Tarikh Terima</td>
    <td>:</td>
    <td><input name="txtTarikhTerimaPasir" type="text" class="$inputTextClass" id="txtTarikhTerimaPasir" onblur="check_date(this)" value="$beanMaklumatPasir.txtTarikhTerimaPasir" size="9" maxlength="10" $readonly/>
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhTerimaPasir',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Keputusan</td>
    <td>:</td>
    <td><select name="socKeputusanPasir" id="socKeputusanPasir" style="width:100px;"  $readonly class="$inputTextClass" $inputTextClass>
        
        
	#if($beanMaklumatPasir.socKeputusanPasir == '1')               
      
        
        <option value="">SILA PILIH</option>
        <option value="1" selected="selected">1 - LULUS</option>
        <option value="2">2 - TOLAK </option>
        
        
    #elseif($beanMaklumatPasir.socKeputusanPasir == '2')              
      
        
        <option value="">SILA PILIH</option>
        <option value="1">1 - LULUS</option>
        <option value="2" selected="selected">2 - TOLAK </option>
        
        
    #elseif($beanMaklumatPasir.socKeputusanPasir == '')             
      
        
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
    <td><input name="txtTarikhLulusPasir" type="text" class="$inputTextClass" id="txtTarikhLulusPasir" onblur="check_date(this)" value="$beanMaklumatPasir.txtTarikhLulusPasir" size="9" maxlength="10" $readonly/>
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhLulusPasir',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
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
      <input name="cmdKemaskini" type="button" onclick="doKemaskiniMaklumatPasir()" value="Kemaskini" />
      #end
      #if ($mode == 'update')
      <input name="cmdSimpan2" type="button" onclick="doSimpanKemaskiniMaklumatPasir()" value="Simpan" />
      <input name="cmdBatal" type="button" onclick="doBatalMaklumatPasir()" value="Batal" />
      #end 
      #if ($mode == 'view' && $idStatus == '1610236')
      <input name="cmdTambahPasir2" type="button" onclick="seterusnya()" value="Seterusnya" />
      #end 
      </td>
  </tr>
  #end
</table>
