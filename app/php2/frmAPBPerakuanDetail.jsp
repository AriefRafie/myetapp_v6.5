<style type="text/css">
<!--
.style1 {
	font-weight: bold
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatPerakuanKeputusan in $BeanMaklumatPerakuanKeputusan)
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Hantar Kertas Ringkasan</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtTarikhHantarKSU" type="text" class="$inputTextClass" id="txtTarikhHantarKSU" onBlur="check_date(this)" value="$beanMaklumatPerakuanKeputusan.tarikhHantarKSU" size="9" maxlength="10" $readonly>
      #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhHantarKSU',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Perakuan</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtTarikhPerakuan" type="text" class="$inputTextClass" id="txtTarikhPerakuan" onBlur="check_date(this)" value="$beanMaklumatPerakuanKeputusan.tarikhPerakuanKSU" size="9" maxlength="10" $readonly>
      #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhPerakuan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
  </tr>
  <tr>
    <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td>Perakuan </td>
    <td>:</td>
    <td><select name="socPerakuan" id="socPerakuan" style="width:120px;" $readonly class="$disabled" $disabled>
        
        
        
        
        
      
        
      
	 #if($beanMaklumatPerakuanKeputusan.flagPerakuanKSU == 'S')
	
      
        
      
        
        
        
        
        <option value="">SILA PILIH</option>
        <option value="S" selected="selected"> SOKONG </option>
        <option value="T"> TIDAK SOKONG </option>
        
        
        
        
        
      
        
      
     #elseif($beanMaklumatPerakuanKeputusan.flagPerakuanKSU == 'T')
	
      
        
      
        
        
        
        
        <option value="">SILA PILIH</option>
        <option value="S"> SOKONG </option>
        <option value="T" selected="selected"> TIDAK SOKONG </option>
        
        
        
        
        
      
        
      
     #else
	
      
        
      
        
        
        
        
        <option value="" selected="selected">SILA PILIH</option>
        <option value="S"> SOKONG </option>
        <option value="T"> TIDAK SOKONG </option>
        
        
        
        
        
      
        
      
	 #end
	
    
      
    
      
      
      
      
      </select>
      &nbsp;&nbsp; 
      #if ($!beanMaklumatPerakuanKeputusan.syorJabatan == 'L') <strong>PERMOHONAN INI DILULUSKAN</strong>#end
      #if ($!beanMaklumatPerakuanKeputusan.syorJabatan == 'D') <strong>PERMOHONAN INI DILULUSKAN SECARA DASAR</strong>#end
      #if ($!beanMaklumatPerakuanKeputusan.syorJabatan == 'T') <strong>PERMOHONAN INI DITOLAK</strong>#end
      #if ($!beanMaklumatPerakuanKeputusan.syorJabatan == 'G') <strong>PERMOHONAN INI DITANGGUHKAN</strong>#end </td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Nama Ketua Setiausaha NRE</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNamaKSU" type="text" class="$inputTextClass" id="txtNamaKSU" value="$beanMaklumatPerakuanKeputusan.namaKSU" size="40" maxlength="80" $readonly ></td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
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
    <td> #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniPerakuanKSU()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniPerakuanKSU()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalPerakuanKSU()"/>
      #end 
       #if($idStatus == '1610208')
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      #end</td>
  </tr>
  #end
</table>

