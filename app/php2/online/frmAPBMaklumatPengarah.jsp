<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT PENGARAH</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPengarah in $BeanMaklumatPengarah)
        <input name="idWarganegara" type="hidden" id="idWarganegara" value="$idWarganegara"/>
        <input name="idBangsa" type="hidden"" id="idBangsa" value="$idBangsa"/>
        <tr>
          <td width="1%">#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td width="28%">Warganegara</td>
          <td width="1%">:</td>
          <td width="70%">$selectWarganegara</td>
        </tr>
        #if($idWarganegara == '13')
        <tr>
          <td width="1%"></td>
          <td></td>
          <td>:</td>
          <td>
          	<input name="txtWarga" type="text" class="$inputTextClass" id="txtWarga" value="$beanMaklumatPengarah.warga" 
          	 $readonly  onBlur="this.value=this.value.toUpperCase();" size="47" maxlength="80" />
          </td>
        </tr>
        #end
        <tr>
          <td>#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td>Nama</td>
          <td>:</td>
          <td><input name="txtNamaPengarah" type="text" class="$inputTextClass" id="txtNamaPengarah" value="$beanMaklumatPengarah.nama" 
           $readonly  onBlur="this.value=this.value.toUpperCase();" size="47" maxlength="80" />          
          </td>
        </tr>
        <tr>
          <td>#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td>Jenis Pengenalan</td>
          <td>:</td>
          <td>$selectJenisPengenalan</td>
        </tr>
        <tr>
          <td>#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td>No. Pengenalan</td>
          <td>:</td>
          <td><input name="txtNoPengenalan" type="text" class="$inputTextClass" id="txtNoPengenalan" value="$beanMaklumatPengarah.noPengenalan" size="12" maxlength="12" $readonly /></td>
        </tr>
        <tr>  
          <td>#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td>Bangsa</td>
          <td>: </td>
          <td>$selectBangsa</td>
        </tr>
        #if($idBangsa == '7')
        <tr>
          <td width="1%"></td>
          <td></td>
          <td>:</td>
          <td>
          	<input name="txtBangsa" type="text" class="$inputTextClass" id="txtBangsa" value="$beanMaklumatPengarah.bangsa" 
          	 $readonly  onBlur="this.value=this.value.toUpperCase();" size="47" maxlength="80" />
          </td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>Pegangan Saham</td>
          <td>:</td>
          <td>RM 
            <input name="txtSaham" type="text" class="$inputTextClass" id="txtSaham" onblur="validateCurrency(this,this.value,'$beanMaklumatPengarah.saham');" value="$beanMaklumatPengarah.saham" size="20" $readonly /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'newPengarah')
      <input type="button" name="cmdSimpanPengarah" id="cmdSimpanPengarah" value="Simpan" onClick="simpanPengarah()"/>
      <input type="button" name="cmdBatalPengarah" id="cmdBatalPengarah" value="Kembali" onClick="batalPengarah()"/>
      #end
      #if ($idStatus == '')
      #if ($mode == 'viewPengarah')
      <input type="button" name="cmdKemaskiniPengarah" id="cmdKemaskiniPengarah" value="Kemaskini" onClick="kemaskiniPengarah()"/>
      <input type="button" name="cmdHapusPengarah" id="cmdHapusPengarah" value="Hapus" onClick="hapusPengarah()"/>
      <input type="button" name="cmdBatalPengarah" id="cmdBatalPengarah" value="Kembali" onClick="batalPengarah()"/>
      #end
      #end
      #if ($mode == 'updatePengarah')
      <input type="button" name="cmdSimpanKemaskiniPengarah" id="cmdSimpanKemaskiniPengarah" value="Simpan" onClick="simpanKemaskiniPengarah()"/>
      <input type="button" name="cmdBatalKemaskiniPengarah" id="cmdSimpanKemaskiniPengarah" value="Kembali" onClick="batalKemaskiniPengarah()"/>
      #end </td>
  </tr>
</table>
