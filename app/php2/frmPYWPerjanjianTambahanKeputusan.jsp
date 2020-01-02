<table width="100%">
  <tr>
    <td><fieldset>
      <legend>KEPUTUSAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach($beanMaklumatKeputusan in $BeanMaklumatKeputusan)
        #if ($idStatusPerjanjianTambahan == '1610206')
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Keputusan</td>
          <td width="1%">:</td>
          <td width="70%"><select name="socKeputusan" id="socKeputusan" style="width:140px;" $readonly class="$disabled" $disabled onchange="doChangeKeputusan()">
              
              
              
              
          #if ($beanMaklumatKeputusan.keputusan == 'L')
          	
              
              
              
              <option>SILA PILIH</option>
              <option value="L" selected>LULUS</option>
              <option value="T">TOLAK</option>
              
              
              
              
          #elseif ($beanMaklumatKeputusan.keputusan == 'T')
          	
              
              
              
              <option>SILA PILIH</option>
              <option value="L">LULUS</option>
              <option value="T" selected>TOLAK</option>
              
              
              
              
          #else
          	
              
              
              
              <option selected>SILA PILIH</option>
              <option value="L">LULUS</option>
              <option value="T">TOLAK</option>
              
              
              
              
          #end
          
            
            
            
            </select></td>
        </tr>
        #else
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Keputusan</td>
          <td width="1%">:</td>
          <td width="70%"> #if ($idKeputusan == 'L')
            LULUS
            #elseif ($idKeputusan == 'T')
            TOLAK
            #end</td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Hantar Surat</td>
          <td>:</td>
          <td><input name="txtTarikhKeputusan" type="text" class="$inputTextClass" id="txtTarikhKeputusan" onBlur="check_date(this)" value="$beanMaklumatKeputusan.tarikhKeputusan" size="9" maxlength="10" $readonly>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhKeputusan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($idKeputusan == 'L')
        <tr>
          <td colspan="4"><fieldset>
            <legend>MAKLUMAT PERJANJIAN</legend>
            #foreach($beanMaklumatPerjanjian in $BeanMaklumatPerjanjian)
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              
              #if ($flagJenisPerjanjian == '2')
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Mula Perjanjian</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this)" value="$beanMaklumatPerjanjian.tarikhMula" size="9" maxlength="10" $readonly />
                  #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              #end
              
              #if ($flagJenisPerjanjian == '3')
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Mula Perjanjian</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this)" value="$beanMaklumatPerjanjian.tarikhMula" size="9" maxlength="10" $readonly />
                  #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Kadar Sewa (RM)</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtKadarSewa" type="text" value="$beanMaklumatPerjanjian.kadarSewa" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.kadarSewa');calcCagaran()" /></td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Cagaran Tambahan (RM)</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtCagaran" type="text" value="$beanMaklumatPerjanjian.cagaran" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.cagaran');" /></td>
              </tr>
              #end
              
              #if ($flagJenisPerjanjian == '4')
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Mula Perjanjian</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this)" value="$beanMaklumatPerjanjian.tarikhMula" size="9" maxlength="10" $readonly />
                  #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Kadar Sewa (RM)</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtKadarSewa" type="text" value="$beanMaklumatPerjanjian.kadarSewa" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.kadarSewa');calcCagaran()" /></td>
              </tr>
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Cagaran Tambahan (RM)</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtCagaran" type="text" value="$beanMaklumatPerjanjian.cagaran" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$beanMaklumatPerjanjian.cagaran');" /></td>
              </tr>
              #end
            </table>
            #end
            </fieldset></td>
        </tr>
        #end
        #if ($mode != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>#if ($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
            #if ($idStatusPerjanjianTambahan == '1610206')
            #if ($idKeputusan == 'L')
            <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="seterusnyaLulus()"/>
            #elseif ($idKeputusan == 'T')
            <input type="button" name="cmdHantar" id="cmdHantar" value="Selesai" onClick="seterusnyaTolak()"/>
            #end
            #end
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKeputusan('$idKeputusan','$flagJenisPerjanjian')"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/>
            #end </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doChangeKeputusan() {
	doAjaxCall${formName}("doChangeKeputusan");
}
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	content = content.replace(/,/g,'');
	content2 = content2.replace(/,/g,'');
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function simpanKeputusan(idKeputusan,flagJenisPerjanjian) {
	if(document.${formName}.idKeputusan.value == ""){
		alert('Sila pilih Keputusan');
		document.${formName}.socKeputusan.focus(); 
		return; 
	}
	if (idKeputusan == 'L'){		
	
		if (flagJenisPerjanjian == '2'){
			if(document.${formName}.txtTarikhMula.value == ""){
				alert('Sila masukkan Tarikh Mula Perjanjian');
				document.${formName}.txtTarikhMula.focus(); 
				return; 
			}
		}
		if (flagJenisPerjanjian == '3'){
			if(document.${formName}.txtTarikhMula.value == ""){
				alert('Sila masukkan Tarikh Mula Perjanjian');
				document.${formName}.txtTarikhMula.focus(); 
				return; 
			}
			if(document.${formName}.txtKadarSewa.value == ""){
				alert('Sila masukkan Kadar Sewa Baru');
				document.${formName}.txtKadarSewa.focus(); 
				return; 
			}
			if(document.${formName}.txtCagaran.value == ""){
				alert('Sila masukkan Nilai Cagaran Tambahan');
				document.${formName}.txtCagaran.focus(); 
				return; 
			}
		}
		if (flagJenisPerjanjian == '4'){
			if(document.${formName}.txtTarikhMula.value == ""){
				alert('Sila masukkan Tarikh Mula Perjanjian');
				document.${formName}.txtTarikhMula.focus(); 
				return; 
			}
			if(document.${formName}.txtKadarSewa.value == ""){
				alert('Sila masukkan Kadar Sewa Baru');
				document.${formName}.txtKadarSewa.focus(); 
				return; 
			}
			if(document.${formName}.txtCagaran.value == ""){
				alert('Sila masukkan Nilai Cagaran Tambahan');
				document.${formName}.txtCagaran.focus(); 
				return; 
			}
		}
	}	
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpan";
	doAjaxCall${formName}("");
}
function calcCagaran(){
	if (document.${formName}.txtKadarSewa.value != ""){
		
		var kadarSewa  = document.${formName}.txtKadarSewa.value*1;
		var cagaran  = 0;
		
		cagaran = kadarSewa * 3;
		
		document.${formName}.txtCagaran.value = cagaran.toFixed(2);
	
	} else {
		document.${formName}.txtCagaran.value = "";
	}
}
function seterusnyaLulus(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "seterusnyaLulus";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function seterusnyaTolak(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "seterusnyaTolak";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
</script>
