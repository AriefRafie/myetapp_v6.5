#foreach ($beanMaklumatKehadiran in $BeanMaklumatKehadiran)
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Nama Pegawai</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNama" type="text" class="$inputTextClassPopup" id="txtNama" value="$beanMaklumatKehadiran.nama" size="50" $readonlyPopup onBlur="this.value=this.value.toUpperCase();"/>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>KJP/Agensi/Syarikat</td>
    <td>:</td>
    <td><input name="txtAgensi" type="text" class="$inputTextClassPopup" id="txtAgensi" value="$beanMaklumatKehadiran.agensi" size="50" $readonlyPopup onBlur="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No Telefon</td>
    <td>:</td>
    <td><input name="txtNoTel" type="text" id="txtNoTel" value="$beanMaklumatKehadiran.noTelefon" $readonlyPopup class="$inputTextClassPopup" onkeyup="validateNumber(this,this.value);" size="10" maxlength="10"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jawatan</td>
    <td>:</td>
    <td><input name="txtJawatan" type="text" class="$inputTextClassPopup" id="txtJawatan" value="$beanMaklumatKehadiran.jawatan" size="50" $readonlyPopup onBlur="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Pengerusi</td>
    <td>:</td>
    <td><select name="socPengerusi" id="socPengerusi" style="width:80;" $readonlyPopup class="$inputTextClassPopup" $inputTextClassPopup>
        
              
            #if ($beanMaklumatKehadiran.flagPengerusi == 'Y')
            	
              
              
        <option>SILA PILIH</option>
        <option value="Y" selected>YA</option>
        <option value="T">TIDAK</option>
        
              
              
            #elseif ($beanMaklumatKehadiran.flagPengerusi == 'T')
                
              
              
        <option>SILA PILIH</option>
        <option value="Y">YA</option>
        <option value="T" selected>TIDAK</option>
        
              
              
            #else
                
              
              
        <option selected>SILA PILIH</option>
        <option value="Y">YA</option>
        <option value="T">TIDAK</option>
        
              
              
            #end
            
            
      </select>
    </td>
  </tr>
  <tr>
    <td colspan="4" valign="bottom">&nbsp;</td>
  </tr>
  #if ($modePopup == 'update')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($modePopup == 'view')
      <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniKehadiran()" />
      <input name="cmdBatal" type="button" value="Kembali" onClick="batalKehadiran()" />
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskiniKehadiran()" />
      <input name="txtBatal" type="button" value="Batal" onClick="batalKemaskiniKehadiran()" />
      #end </td>
  </tr>
</table>
#end
<script>
function batalKehadiran(){
	document.${formName}.flagPopup.value = "openPopupMesyuarat";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function batalKemaskiniKehadiran(){
	document.${formName}.flagPopup.value = "openPopupMesyuarat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.flagPopupKehadiran.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
</script>
