<style type="text/css">
<!--
.style1 {
	color: #ff0000
}
#parse("css/eTapp_PHP.css")
-->
</style>
<input type="hidden" name="actionPopup" value="$actionPopup"/>
<input type="hidden" name="hitButton"/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="4"><fieldset>
      <legend><strong>MAKLUMAT LOT</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Pegangan Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtPeganganHakmilik" type="text" class="$inputTextClassPopup"id="txtPeganganHakmilik" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatBorangK.peganganHakmilik" maxlength="30" $readonlyPopup /></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Jenis Hakmilik</td>
          <td>:</td>
          <td>$selectJenisHakmilik</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>No. Hakmilik</td>
          <td>:</td>
          <td><input name="txtNoHakmilik" type="text" class="$inputTextClassPopup" id="txtNoHakmilik" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatBorangK.noHakmilik" maxlength="21" $readonlyPopup/></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Jenis Lot</td>
          <td>:</td>
          <td>$selectJenisLot</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>No. Lot</td>
          <td>:</td>
          <td><input name="txtNoLot" type="text" class="$inputTextClassPopup" id="txtNoLot" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatBorangK.noLot" maxlength="20" $readonlyPopup/></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Unit Luas Ambil</td>
          <td>:</td>
          <td><select name="socLuas" style="width:200px;" 
$readonlyPopup class="$disabledPopup" $disabledPopup 
onchange="javascript:doChangeLuas(this.value)">
              
              
            
        
#set ($listUnitLuas = ["SILA PILIH",
		       "KM - KILOMETER PERSEGI",
		       "H - HEKTAR",
		       "M - METER PERSEGI",
		       "E - EKAR,ROOD,POLE",
		       "K - KAKI PERSEGI",
		       "P - EKAR PERPULUHAN",
		       "D - EKAR,DEPA",
		       "R - RELONG,JEMBA,KAKI PERSEGI",
		       "BN - BATU NAUTIKA"]
      )
#set( $counter = 0 )
#foreach ($i in $listUnitLuas)

#if ($idLuas == $counter) 
	
        
            
              
              <option selected value="$counter">$i</option>
              
              
            
        
#else
	
        
            
              
              <option value="$counter">$i</option>
              
              
            
        
#end

#set ($counter = $counter+1)

#end

      
          
            
            </select>          </td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Luas Ambil</td>
          <td>:</td>
          <td>#if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' || $idLuas == '5' || $idLuas == '6' || $idLuas == '9')
            <input type="text" name="txtLuas1" id="txtLuas1" value="$beanMaklumatBorangK.luas1" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonlyPopup class="$inputTextClassPopup"/ >
            #elseif ($idLuas == '7')
            <input type="text" name="txtLuas1" id="txtLuas1" value="$beanMaklumatBorangK.luas1" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuas2" id="txtLuas2" value="$beanMaklumatBorangK.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"/ $readonlyPopup class="$inputTextClassPopup">
            #elseif ($idLuas == '8' || $idLuas == '4')
            <input type="text" name="txtLuas1" id="txtLuas1" value="$beanMaklumatBorangK.luas1" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuas2" id="txtLuas2" value="$beanMaklumatBorangK.luas2" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuas3" id="txtLuas3" value="$beanMaklumatBorangK.luas3" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonlypopup class="$inputTextClassPopup"/></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>Luas Bersamaan</td>
          <td>:</td>
          <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" value="$beanMaklumatBorangK.luasBersamaan" readonly="readonly" class="disabled"/>
            HEKTAR</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Negeri</td>
          <td>:</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Daerah</td>
          <td>:</td>
          <td>$selectDaerah</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Bandar/Pekan/Mukim</td>
          <td>:</td>
          <td>$selectMukim</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kategori</td>
          <td>:</td>
          <td>$selectKategori</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Subkategori</td>
          <td>:</td>
          <td>$selectSubKategori</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Syarat Nyata</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtSyarat" id="txtSyarat" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();">$beanMaklumatBorangK.syarat</textarea></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Sekatan Kepentingan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtSekatan" id="txtSekatan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup"  onblur="this.value=this.value.toUpperCase();">$beanMaklumatBorangK.sekatan</textarea></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Kegunaan Tanah</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtKegunaanTanah" id="txtKegunaanTanah" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();">$beanMaklumatBorangK.kegunaanTanah</textarea></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$selectKementerian</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Agensi</td>
          <td>:</td>
          <td>$selectAgensi</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="4"><fieldset>
      <legend><strong>MAKLUMAT BORANG K</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh Borang K</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="tarikhBorangK" id="tarikhBorangK" value="$beanMaklumatBorangK.tarikhBorangK" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('tarikhBorangK',false,'dmy');"><img border="0" src="../../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();">$beanMaklumatBorangK.catatan</textarea></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="4"><fieldset>
      <legend><strong>MAKLUMAT REKOD ENDOSAN BORANG K</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">No. Perserahan</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNoPerserahan" type="text" class="$inputTextClassPopup"id="txtNoPerserahan" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatBorangK.noPerserahan" maxlength="30" $readonlyPopup /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Catatan Dibuat</td>
          <td>:</td>
          <td><input type="text" name="tarikhCatatan" id="tarikhCatatan" value="$beanMaklumatBorangK.tarikhCatatan" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('tarikhCatatan',false,'dmy');"><img border="0" src="../../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Terima</td>
          <td>:</td>
          <td><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatBorangK.tarikhTerima" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../../img/calendar.gif"/></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%">
    <input name="cmdSimpan" type="button" value="Simpan" onClick="doSimpanBorangK()" />
    <input name="cmdBatal" type="button" value="Batal" onClick="doBatalBorangK()" />
    </td>
  </tr>
</table>
<script>
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function doChangeLuas() {
	doAjaxCall${formName}("doChangeLuas");
}
function doChangeKategori() {
	doAjaxCall${formName}("doChangeKategori");
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeAgensi() {
	doAjaxCall${formName}("doChangeAgensi");
}
function validateLuas(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(5);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function kiraLuas(idLuas){

  var jenisLuas = idLuas;
  
  // KILOMETER PERSEGI
  if(jenisLuas == "1"){

		var luasK = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasK = document.${formName}.txtLuas1.value*1;
		}
		var luasH = luasK*100;		
		document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   } else if(jenisLuas == "2"){ //HEKTER
  		
		var luasH = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasH = document.${formName}.txtLuas1.value*1;
		}
		document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   } else if(jenisLuas == "3"){ // METER PERSEGI
    	
		var luasM = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasM = document.${formName}.txtLuas1.value*1;
		}
  	  	var luasH = (luasM*0.0001);
	  	document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   } else if(jenisLuas == "4"){  //EKAR, ROOD, POLE

	  	var luasE = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasE = document.${formName}.txtLuas1.value*1;
		}
	  	var luasR = 0;
		if (document.${formName}.txtLuas2.value != ''){
			luasR = document.${formName}.txtLuas2.value*1;
		}
	  	var luasP = 0;
		if (document.${formName}.txtLuas3.value != ''){
			luasP = document.${formName}.txtLuas3.value*1;
		}
	  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  	document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   } else if(jenisLuas == "5"){ //KAKI PERSEGI
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasAsal = document.${formName}.txtLuas1.value*1;
	  }
	  var luasH = luasAsal*0.0000092;
  	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   } else if(jenisLuas == "6"){	//EKAR PERPULUHAN
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasAsal = document.${formName}.txtLuas1.value*1;
	  }
	  var luasH = luasAsal*0.405;
	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
  	  
   } else if(jenisLuas == "7"){ //EKAR,DEPA
  	  
	  var luasE = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasE = document.${formName}.txtLuas1.value*1;
	  }
	  var luasD = 0;
	  if (document.${formName}.txtLuas2.value != ''){
		  luasD = document.${formName}.txtLuas2.value*1;
	  }
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   } else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI
  	  
	  var luasR = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasR = document.${formName}.txtLuas1.value*1;
	  }
	  var luasJ = 0;
	  if (document.${formName}.txtLuas2.value != ''){
		  luasJ = document.${formName}.txtLuas2.value*1;
	  }
	  var luasK = 0;
	  if (document.${formName}.txtLuas3.value != ''){
		  luasK = document.${formName}.txtLuas3.value*1;
	  }
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
	}
}

function doBatalBorangK(){	
	document.${formName}.actionPopup.value = "";
	document.${formName}.submit();
}
function doSimpanBorangK(){
	if(document.${formName}.socJenisHakmilik.value == ""){
		alert('Sila pilih Jenis Hakmilik.');
  		document.${formName}.socJenisHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txtNoHakmilik.value == ""){
		alert('Sila masukkan No. Hakmilik.');
  		document.${formName}.txtNoHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.socJenisLot.value == ""){
		alert('Sila pilih Lot.');
  		document.${formName}.socJenisLot.focus(); 
		return; 
	}
	if(document.${formName}.txtNoLot.value == ""){
		alert('Sila masukkan No. Lot.');
  		document.${formName}.txtNoLot.focus(); 
		return; 
	}
	if(document.${formName}.socLuas.value == "0"){
		alert('Sila pilih Unit Luas.');
		document.${formName}.socLuas.focus(); 
		return; 
	}
		
	var idLuas = document.${formName}.socLuas.value;
	
	if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
		if(document.${formName}.txtLuas1.value == ""){
			alert('Sila masukkan Luas .');
			document.${formName}.txtLuas1.focus(); 
			return; 
		}
	}
	else
	if(idLuas == '4' || idLuas == '8'){
		if(document.${formName}.txtLuas1.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas1.focus(); 
			return; 
		}
		if(document.${formName}.txtLuas2.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas2.focus(); 
			return; 
		}
		if(document.${formName}.txtLuas3.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas3.focus(); 
			return; 
		}
	} 
	else
	if(idLuas == '7'){
		if(document.${formName}.txtLuas1.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas1.focus(); 
			return; 
		}
		if(document.${formName}.txtLuas2.value == ""){
			alert('Sila masukkan Luas.');
			document.${formName}.txtLuas2.focus(); 
			return; 
		}
	}
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih Daerah.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}
	if(document.${formName}.socMukim.value == ""){
		alert('Sila pilih Mukim.');
  		document.${formName}.socMukim.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionPopup.value = "papar";
	document.${formName}.hitButton.value = "doSimpanBorangK";
	document.${formName}.submit();
}
</script>
