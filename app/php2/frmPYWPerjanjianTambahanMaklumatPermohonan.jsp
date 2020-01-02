<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN PERJANJIAN TAMBAHAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%" valign="top">Tarikh Terima</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" onblur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass"/>
            #if ($mode != 'view') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Surat</td>
          <td>:</td>
          <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat" onblur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">No. Rujukan Surat</td>
          <td>:</td>
          <td><input name="txtNoRujukanSurat" type="text" class="$inputTextClass" id="txtNoRujukanSurat" value="$beanMaklumatPermohonan.noRujukanSurat" $readonly onblur="this.value=this.value.toUpperCase();" size="50" maxlength="50"/></td>
        </tr>
        #if ($idStatusPerjanjianTambahan == '1610198')
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Jenis Perjanjian Tambahan</td>
          <td valign="top">:</td>
          <td><select name="socFlagJenisPerjanjian" id="socFlagJenisPerjanjian" $readonly class="$disabled" $disabled onchange="doChangeJenisPerjanjian()">
              
              
              
          #if ($beanMaklumatPermohonan.flagJenisPerjanjian == '1')
          	  
              
              
              <option>SILA PILIH</option>
              <option value="1" selected="selected">PERUBAHAN TUJUAN PENYEWAAN</option>
              <option value="2">PERUBAHAN TARIKH MULA PERJANJIAN</option>
              <option value="3">PERUBAHAN LUAS PENYEWAAN</option>
              <option value="4">RAYUAN KADAR SEWA</option>
              
              
              
		  #elseif ($beanMaklumatPermohonan.flagJenisPerjanjian == '2')
          	  
              
              
              <option>SILA PILIH</option>
              <option value="1">PERUBAHAN TUJUAN PENYEWAAN</option>
              <option value="2" selected="selected">PERUBAHAN TARIKH MULA PERJANJIAN</option>
              <option value="3">PERUBAHAN LUAS PENYEWAAN</option>
              <option value="4">RAYUAN KADAR SEWA</option>
              
              
              
          #elseif ($beanMaklumatPermohonan.flagJenisPerjanjian == '3')
          	  
              
              
              <option>SILA PILIH</option>
              <option value="1">PERUBAHAN TUJUAN PENYEWAAN</option>
              <option value="2">PERUBAHAN TARIKH MULA PERJANJIAN</option>
              <option value="3" selected="selected">PERUBAHAN LUAS PENYEWAAN</option>
              <option value="4">RAYUAN KADAR SEWA</option>
              
              
              
          #elseif ($beanMaklumatPermohonan.flagJenisPerjanjian == '4')
          	  
              
              
              <option>SILA PILIH</option>
              <option value="1">PERUBAHAN TUJUAN PENYEWAAN</option>
              <option value="2">PERUBAHAN TARIKH MULA PERJANJIAN</option>
              <option value="3">PERUBAHAN LUAS PENYEWAAN</option>
              <option value="4" selected="selected">RAYUAN KADAR SEWA</option>
              
              
              
          #else
          	  
              
              
              <option selected="selected">SILA PILIH</option>
              <option value="1">PERUBAHAN TUJUAN PENYEWAAN</option>
              <option value="2">PERUBAHAN TARIKH MULA PERJANJIAN</option>
              <option value="3">PERUBAHAN LUAS PENYEWAAN</option>
              <option value="4">RAYUAN KADAR SEWA</option>
              
              
              
          #end
          
            
            
            </select></td>
        </tr>
        #else
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Jenis Perjanjian Tambahan</td>
          <td valign="top">:</td>
          <td>#if ($flagJenisPerjanjian == '1')
            PERUBAHAN TUJUAN PENYEWAAN
            #elseif ($flagJenisPerjanjian == '2')
            PERUBAHAN TARIKH MULA PERJANJIAN
            #elseif ($flagJenisPerjanjian == '3')
            PERUBAHAN LUAS PENYEWAAN
            #elseif ($flagJenisPerjanjian == '4')
            RAYUAN KADAR SEWA
          #end</td>
        </tr>
        #end
        #if ($flagJenisPerjanjian == '1')
        <tr>
          <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tujuan</td>
          <td valign="top">:</td>
          <td><textarea name="txtTujuan" id="txtTujuan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tujuan</textarea></td>
        </tr>
        #end
        #if ($flagJenisPerjanjian == '2')
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Mula Perjanjian Yang Dipohon</td>
          <td>:</td>
          <td><input type="text" name="tarikhMulaPerjanjian" id="tarikhMulaPerjanjian" value="$beanMaklumatPermohonan.tarikhMulaPerjanjian" onblur="check_date(this)" size="9" $readonly class="$inputTextClass"/>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('tarikhMulaPerjanjian',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
        </tr>
        #end
        #if ($flagJenisPerjanjian == '3')
        <tr>
          <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td>Luas Kegunaan</td>
          <td>:</td>
          <td >$selectLuasKegunaan</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Keluasan Asal</td>
          <td>:</td>
          <td>$beanMaklumatPermohonan.luasAsal $beanMaklumatPermohonan.keteranganLuasAsal
            <input type="hidden" name="txtLuasAsal" id="txtLuasAsal" value="$beanMaklumatPermohonan.luasAsal"/></td>
        </tr>
        #if ($idLuasKegunaan == '2')
        <tr>
          <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td>Unit Luas</td>
          <td>:</td>
          <td>#parse("app/php2/unit_luas.jsp") </td>
        </tr>
        #if ($idLuas != '99999' && $idLuas != '')
        <tr>
          <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td>Luas Mohon</td>
          <td>:</td>
          <td> #if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' || $idLuas == '5' || $idLuas == '6' || $idLuas == '9')
            <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPermohonan.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"  $readonly class="$inputTextClass"/>
            #elseif ($idLuas == '7')
            <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPermohonan.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatPermohonan.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" / $readonly class="$inputTextClass">
            #elseif ($idLuas == '8' || $idLuas == '4')
            <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPermohonan.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatPermohonan.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$beanMaklumatPermohonan.luas3" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"  $readonly class="$inputTextClass"/>
            #end </td>
        </tr>
        #end
        #end
        <tr>
          <td>&nbsp;</td>
          <td>Luas Bersamaan</td>
          <td>:</td>
          <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" value="$beanMaklumatPermohonan.luasBersamaan"  style="text-align:right" readonly="readonly" class="disabled"/>
            HEKTAR</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Baki Luas</td>
          <td>:</td>
          <td><input type="text" name="txtBakiLuas" id="txtBakiLuas" value="$beanMaklumatPermohonan.luasBaki" readonly="readonly" class="disabled" style="text-align:right"/>
            HEKTAR</td>
        </tr>
        #end
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.catatan</textarea></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%">#if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
      #if ($idStatusPerjanjianTambahan == '1610198')
      <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnyaMP()"/>
      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusPermohonan()"/>
      #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
	  #end
      #if ($mode == 'update')
	  <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="simpanKemaskiniMaklumatPermohonan('$flagJenisPerjanjian','$idLuas','$idStatus')"/>
	  <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onclick="batal()"/>#end    </td>
  </tr>
</table>

<script>
function doChangeJenisPerjanjian() {
	doAjaxCall${formName}("doChangeJenisPerjanjian");
}
function doChangeLuasKegunaan() {
	doAjaxCall${formName}("doChangeLuasKegunaan");
}
function doChangeLuas() {
	doAjaxCall${formName}("doChangeLuas");
}
function simpanKemaskiniMaklumatPermohonan(flagJenisPerjanjian,idLuas,idStatus) {
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhTerima.focus(); 
		return;
	}
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if(document.${formName}.tarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.tarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if (idStatus == '1610198'){
		if(document.${formName}.socFlagJenisPerjanjian.value == ""){
			alert('Sila pilih Jenis Perjanjian.');
			document.${formName}.socFlagJenisPerjanjian.focus(); 
			return; 
		}
	}
	
	if (flagJenisPerjanjian == '1'){
		if(document.${formName}.txtTujuan.value == ""){
			alert('Sila masukkan Tujuan.');
			document.${formName}.txtTujuan.focus(); 
			return; 
		}
	}
	if (flagJenisPerjanjian == '2'){
		if(document.${formName}.tarikhMulaPerjanjian.value == ""){
			alert('Sila masukkan Tarikh Mula Perjanjian.');
			document.${formName}.tarikhMulaPerjanjian.focus(); 
			return; 
		}
	}
	if (flagJenisPerjanjian == '3'){
		if(document.${formName}.socLuasKegunaan.value == ""){
			alert('Sila pilih Luas Kegunaan.');
			document.${formName}.socLuasKegunaan.focus(); 
			return; 
		}
		if(document.${formName}.socLuasKegunaan.value == "2"){
			if(document.${formName}.socLuas.value == "0"){
				alert('Sila pilih Unit Luas.');
				document.${formName}.socLuas.focus(); 
				return; 
			}
		
			if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
				if(document.${formName}.txtLuasMohon1.value == ""){
					alert('Sila masukkan Luas Sewa .');
					document.${formName}.txtLuasMohon1.focus(); 
					return; 
				}
			}
			else
			if(idLuas == '4' || idLuas == '8'){
				if(document.${formName}.txtLuasMohon1.value == ""){
					alert('Sila masukkan Luas Sewa.');
					document.${formName}.txtLuasMohon1.focus(); 
					return; 
				}
				if(document.${formName}.txtLuasMohon2.value == ""){
					alert('Sila masukkan Luas Sewa.');
					document.${formName}.txtLuasMohon2.focus(); 
					return; 
				}
				if(document.${formName}.txtLuasMohon3.value == ""){
					alert('Sila masukkan Luas Sewa.');
					document.${formName}.txtLuasMohon3.focus(); 
					return; 
				}
			} 
			else
			if(idLuas == '7'){
				if(document.${formName}.txtLuasMohon1.value == ""){
					alert('Sila masukkan Luas Sewa.');
					document.${formName}.txtLuasMohon1.focus(); 
					return; 
				}
				if(document.${formName}.txtLuasMohon2.value == ""){
					alert('Sila masukkan Luas Sewa.');
					document.${formName}.txtLuasMohon2.focus(); 
					return; 
				}
			} 
		}
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatPermohonan";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function hapusPermohonan() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "hapusPermohonan";
	document.${formName}.actionPenyewaan.value = "";
	document.${formName}.submit();
}
function cekTarikhTerima(elmnt) {
//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var currentDate = new Date();
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		elmnt.value ="";
		document.${formName}.tarikhTerima.focus(); 
		return;
	}
}
function cekTarikhSurat(elmnt) {  
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
}
function kiraLuas(idLuas){

  var jenisLuas = idLuas;
  
  // KILOMETER PERSEGI
  if(jenisLuas == "1"){

		var luasK = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasK = document.${formName}.txtLuasMohon1.value*1;
		}
		var luasH = luasK*100;
		
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "2"){ //HEKTER
  	
		var luasH = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasH = document.${formName}.txtLuasMohon1.value*1;
		}
  		
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "3"){ // METER PERSEGI
    	
		var luasM = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasM = document.${formName}.txtLuasMohon1.value*1;
		}
  	  	var luasH = (luasM*0.0001);
	  	
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "4"){  //EKAR, ROOD, POLE

	  	var luasE = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasE = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasR = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasR = document.${formName}.txtLuasMohon2.value*1;
		}
	  	var luasP = 0;
		if (document.${formName}.txtLuasMohon3.value != ''){
			luasP = document.${formName}.txtLuasMohon3.value*1;
		}
	  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  	
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasMohon3.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "5"){ //KAKI PERSEGI
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuasMohon1.value != ''){
	  	  luasAsal = document.${formName}.txtLuasMohon1.value*1;
	  }
	  var luasH = luasAsal*0.0000092;
  	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		 	document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "6"){	//EKAR PERPULUHAN
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuasMohon1.value != ''){
	  	  luasAsal = document.${formName}.txtLuasMohon1.value*1;
	  }
	  var luasH = luasAsal*0.405;
	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}
  	  
   } else if(jenisLuas == "7"){ //EKAR,DEPA
  	  
	  	var luasE = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasE = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasD = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasD = document.${formName}.txtLuasMohon2.value*1;
		}
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		  	document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI
  	  
	 	var luasR = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasR = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasJ = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasJ = document.${formName}.txtLuasMohon2.value*1;
		}
	  	var luasK = 0;
		if (document.${formName}.txtLuasMohon3.value != ''){
			luasK = document.${formName}.txtLuasMohon3.value*1;
		}
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasMohon3.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		  	document.${formName}.txtBakiLuas.value = bakiLuas;
		}
	}
}
function seterusnyaMP(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "seterusnyaMP";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
</script>