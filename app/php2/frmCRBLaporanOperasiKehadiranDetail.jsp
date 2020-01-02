#if ($flagKetua != 'Y')
#foreach ($beanKetuaOperasi in $BeanKetuaOperasi)
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <fieldset>
<legend><strong>KETUA OPERASI</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Nama</td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNamaKetua" type="text" class="$inputTextClassPopup" id="txtNamaKetua" value="$beanKetuaOperasi.nama" size="30" $readonlyPopup /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Agensi</td>
    <td>:</td>
    <td><input name="txtAgensiK" type="text" class="$inputTextClassPopup" id="txtAgensiK" value="$beanKetuaOperasi.agensi" size="60" $readonlyPopup /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jawatan</td>
    <td>:</td>
    <td><input name="txtJawatanKetua" type="text" class="$inputTextClassPopup" id="txtJawatanKetua" value="$beanKetuaOperasi.jawatanKetua" size="60" $readonlyPopup /></td>
  </tr>
</table>
</fieldset>
    </td>
  </tr>
</table>
#end 
#end
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <fieldset><legend><strong>SENARAI AHLI AGENSI YANG TERLIBAT</strong></legend>
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
     #foreach ($beanAgensi in $BeanAgensi)
     <tr>
    <td colspan="2"  align="center"><strong>Agensi</strong>#if ($modePopup != 'view')<span class="style1">*</span>#end : <input name="txtAgensi" type="text" class="$inputTextClassPopup" id="txtAgensi" value="$beanAgensi.agensi" size="60" $readonlyPopup /></td>
  </tr>
  #end
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr class="table_header">
    <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
    <td width="95%"><strong>Nama</strong>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
  </tr>
  #foreach ($beanMaklumatKehadiran in $BeanMaklumatKehadiran)
  <tr>
    <td class="$row" align="center">$beanMaklumatKehadiran.bil</td>
    <td class="$row"><input name="txtNama" type="text" class="$inputTextClassPopup" id="txtNama" value="$beanMaklumatKehadiran.nama" size="60" $readonlypopup /></td>

  </tr>
  #end
   <tr>
    <td></td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td colspan="2" align="center"> #if ($modePopup == 'new')
     <input name="cmdSimpan" type="button" value="Simpan" onClick="simpanKehadiran()" />
      <input name="cmdBatalKehadiranM" type="button" value="Batal" onClick="batalKehadiran()" />
      #end </td>
  </tr>
</table>
</fieldset>
    </td>
   
  </tr>
</table>


