<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT PERJANJIAN</legend>
      #foreach($beanMaklumatPerjanjian in $BeanMaklumatPerjanjian)
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">No. Siri Perjanjian</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtNoSiri" id="txtNoSiri" value="$beanMaklumatPerjanjian.noSiri" onBlur="this.value=this.value.toUpperCase();" $readonly class="$inputTextClass"/>
          </td>
        </tr>
        #if ($flagJenisPerjanjian == '2' || $flagJenisPerjanjian == '3' || $flagJenisPerjanjian == '4')
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Tarikh Mula Perjanjian</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhMulaTabPerjanjian" type="text" class="$inputTextClass" id="txtTarikhMulaTabPerjanjian" onBlur="check_date(this)" value="$beanMaklumatPerjanjian.tarikhMula" size="9" maxlength="10" $readonly />
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMulaTabPerjanjian',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        #end
        
        #if ($flagJenisPerjanjian == '3' || $flagJenisPerjanjian == '4')
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
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="2">#if ($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini()"/>
            #if ($idStatusPerjanjianTambahan == '1610214')
            <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onClick="seterusnyaPA()"/>
            #end
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniPerjanjian('$flagJenisPerjanjian')"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/>
            #end</td>
        </tr>
      </table>
      #end
      </fieldset></td>
  </tr>
</table>
<script>
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
function simpanKemaskiniPerjanjian(flagJenisPerjanjian) {
	if(document.${formName}.txtNoSiri.value == ""){
		alert('Sila masukkan No Siri Perjanjian');
  		document.${formName}.txtNoSiri.focus(); 
		return; 
	}
	if (flagJenisPerjanjian == '2'){
		if(document.${formName}.txtTarikhMulaTabPerjanjian.value == ""){
			alert('Sila masukkan Tarikh Mula Perjanjian');
			document.${formName}.txtTarikhMulaTabPerjanjian.focus(); 
			return; 
		}
	}
	if (flagJenisPerjanjian == '3'){
		if(document.${formName}.txtTarikhMulaTabPerjanjian.value == ""){
			alert('Sila masukkan Tarikh Mula Perjanjian');
			document.${formName}.txtTarikhMulaTabPerjanjian.focus(); 
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
		if(document.${formName}.txtTarikhMulaTabPerjanjian.value == ""){
			alert('Sila masukkan Tarikh Mula Perjanjian');
			document.${formName}.txtTarikhMulaTabPerjanjian.focus(); 
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
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniPerjanjian";
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
function seterusnyaPA(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "seterusnyaPA";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
</script>
