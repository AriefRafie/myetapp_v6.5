<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatLaporanTanah in $BeanMaklumatLaporanTanah)
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Lawatan Tapak</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtTarikhLawatan" id="txtTarikhLawatan" size="9" onBlur="check_date(this)" value="$beanMaklumatLaporanTanah.tarikhLawatan" $readonly class="$inputTextClass">
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhLawatan',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Lawatan Tapak Oleh</td>
    <td width="1%">:</td>
    <td width="70%"><select name="socFlagLawatan" size="1" id="socFlagLawatan" $disabled class="$disabled">
        
      #if ($beanMaklumatLaporanTanah.flagLawatan == '1')
          
        <option>SILA PILIH</option>
        <option selected="selected" value="1">JKPTG IBUPEJABAT</option>
        <option value="2">JKPTG NEGERI</option>
        
      #elseif ($beanMaklumatLaporanTanah.flagLawatan == '2')
      	  
        <option>SILA PILIH</option>
        <option value="1">JKPTG IBUPEJABAT</option>
        <option selected="selected" value="2">JKPTG NEGERI</option>
        
      #else
      	  
        <option selected="selected">SILA PILIH</option>
        <option value="1">JKPTG IBUPEJABAT</option>
        <option value="2">JKPTG NEGERI</option>
        
      #end
    
      </select>
    </td>
  </tr>
  <tr>
    <td width="1%" valign="top">#if ($mode!= 'view')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">Tujuan Laporan</td>
    <td width="1%" valign="top">:</td>
    <td width="70%" valign="top"><textarea name="txtTujuanLaporan" id="txtTujuanLaporan" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.tujuanLaporan</textarea></td>
  </tr>
  #if ($mode != 'view') #end
  <tr>
    <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">Laporan Atas Tanah</td>
    <td width="1%" valign="top">:</td>
    <td valign="top"><textarea name="txtLaporanAtasTanah" id="txtLaporanAtasTanah" rows="5" cols="50" $readonly class="$inputTextClass">$beanMaklumatLaporanTanah.laporanAtasTanah</textarea>
    </td>
  </tr>
  #if ($mode != 'view') #end
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Isu dan Ulasan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtIsuUlasan" id="txtIsuUlasan" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.ulasan</textarea>
    </td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatLaporanTanah.catatan</textarea>
    </td>
  </tr>
  #if ($mode != 'view') #end
  <tr>
    <td colspan="4" valign="bottom"></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
      #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniMaklumatLaporanTanah()"/>
      #if($idStatus == '1610200')
      <!-- <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/> -->
      <!-- <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/> -->
      #end
      #end
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniMaklumatLaporanTanah()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalKemaskiniMaklumatLaporanTanah()"/>
      #end
      #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
      #end </td>
  </tr>
  #end
</table>
<script>
function kemaskiniMaklumatLaporanTanah() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMaklumatLaporanTanah() {
	if(document.${formName}.txtTarikhLawatan.value == ""){
		alert('Sila masukkan Tarikh Lawatan');
  		document.${formName}.txtTarikhLawatan.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanLaporan.value == ""){
		alert('Sila masukkan Tujuan Laporan.');
  		document.${formName}.txtTujuanLaporan.focus(); 
		return; 
	}
	if(document.${formName}.txtLaporanAtasTanah.value == ""){
		alert('Sila masukkan Laporan Atas Tanah.');
  		document.${formName}.txtLaporanAtasTanah.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatLaporanTanah";
	doAjaxCall${formName}("");
}
function batalKemaskiniMaklumatLaporanTanah() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
</script>
