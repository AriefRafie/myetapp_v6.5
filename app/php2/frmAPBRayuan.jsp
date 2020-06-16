<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="noRayuan" type="hidden" id="noRayuan" value="$noRayuan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>
  <input name="masterMode" type="hidden" id="masterMode" value="$masterMode"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="hitButtonDaftar" type="hidden" id="hitButtonDaftar"/>
</p>
<body>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td> 
  </tr>
  #end
  #if ($idFail != '' &&  $idStatus != '1610208')
  <tr>
    <td>&nbsp;
      <div class="error">RAYUAN TIDAK BOLEH DILAKUKAN BAGI FAIL INI</div></td> 
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
   #if ($idFail != '' && $idStatus != '94' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610235' && $idStatus != '1610201' && $idStatus != '1610213' && $idStatus != '1610205' && $idStatus != '1610206' && $idStatus != '1610236' && $idStatus != '1610237' && $idStatus != '1610238' && $idStatus != '1610239' && $idStatus != '1610207')
  #if ($flagAktif !='0')
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT PENDAFTARAN RAYUAN</legend>
      ##foreach($beanMaklumatPermohonanRayuan in $BeanMaklumatPermohonanRayuan)
      #foreach($beanMaklumatPermohonanRayuan in $BeanHeader)
      <table align="center" width="100%">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="23%" valign="top">Tarikh Terima</td>
          <td width="1%">:</td>
          <td width="75%"><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonanRayuan.tarikhTerima" size="9" $readonly class="$inputTextClass" onBlur="check_date(this);cekTarikhTerima(this)"/>
            <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end
            <div id="tarikhTerima_check" style="color:red" ></div></td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Surat</td>
          <td>:</td>
          <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPermohonanRayuan.tarikhSurat" onBlur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
            <a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Perkara</td>
          <td valign="top">:</td>
          <td><textarea name="perkara" id="perkara" rows="5" cols="50" $readonly class="$inputTextClass" style="text-transform:uppercase;">$beanMaklumatPermohonanRayuan.perkara</textarea></td>
        </tr>
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tukar Koordinat</td>
          <td valign="top">:</td>
          <td valign="top"><select name="socTukarKoordinat" id="socTukarKoordinat" style="width:100px;" $readonly class="$inputTextClass" $inputTextClass>
              
            #if($beanMaklumatPermohonanRayuan.socTukarKoordinat == "1") 
            
              <option value="">SILA PILIH</option>
              <option value="1" selected="selected">1 - YA</option>
              <option value="2">2 - TIDAK</option>
                                      
            #elseif($beanMaklumatPermohonanRayuan.socTukarKoordinat == "2") 
            
              <option value="">SILA PILIH</option>
              <option value="1">1 - YA</option>
              <option value="2" selected="selected">2 - TIDAK</option>
                          
            #elseif($beanMaklumatPermohonanRayuan.socTukarKoordinat == "") 
            
              <option value="" selected="selected">SILA PILIH</option>
              <option value="1">1 - YA</option>
              <option value="2">2 - TIDAK</option>
            #else
            
            <option value="" selected="selected">SILA PILIH</option>
              <option value="1">1 - YA</option>
              <option value="2">2 - TIDAK</option>                              
            #end
            </select>
          
          </td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        #if ($masterMode == 'update')
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td> #if ($mode == 'new')
            	<input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar" onClick="doProsesRayuan()"/>
               #end
               #if ($mode == 'view')
            	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniRayuan()"/>
               #end
           </td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
    <td>&nbsp;</td>
  </tr>
  #end
  
  #if ($flagAktif =='0')
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT PENDAFTARAN RAYUAN</legend>
      #foreach($beanRayuanLama in $BeanRayuanLama)
      <table align="center" width="100%">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="23%" valign="top">Tarikh Terima</td>
          <td width="1%">:</td>
          <td width="75%"><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanRayuanLama.tarikhTerimaRayuan" size="9" $readonly class="$inputTextClass" onBlur="check_date(this)"/>
            <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end
            <div id="tarikhTerima_check" style="color:red" ></div></td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Surat</td>
          <td>:</td>
          <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanRayuanLama.tarikhSuratRayuan" onBlur="check_date(this)" size="9" $readonly class="$inputTextClass"/>
            <a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Perkara</td>
          <td valign="top">:</td>
          <td><textarea name="perkara" id="perkara" rows="5" cols="50" $readonly class="$inputTextClass" style="text-transform:uppercase;">$beanRayuanLama.perkaraRayuan</textarea></td>
        </tr>
         <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tukar Koordinat</td>
          <td valign="top">:</td>
          <td><select name="socTukarKoordinat" id="socTukarKoordinat" style="width:100px;" $readonly class="$inputTextClass" $inputTextClass>
              
            #if($beanRayuanLama.socTukarKoordinat == "1") 
            
              <option value="">SILA PILIH</option>
              <option value="1" selected="selected">1 - YA</option>
              <option value="2">2 - TIDAK</option>
                                      
            #elseif($beanRayuanLama.socTukarKoordinat == "2") 
            
              <option value="">SILA PILIH</option>
              <option value="1">1 - YA</option>
              <option value="2" selected="selected">2 - TIDAK</option>
                          
            #elseif($beanRayuanLama.socTukarKoordinat == "") 
            
              <option value="" selected="selected">SILA PILIH</option>
              <option value="1">1 - YA</option>
              <option value="2">2 - TIDAK</option>
                                          
            #end
            </select>
          
          </td>
        </tr>
        #if ($masterMode == 'update')
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
    <td>&nbsp;</td>
  </tr>
  #end
  #end
   
</table>
<script>
function doProsesRayuan() {
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
	if(document.${formName}.perkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.perkara.focus(); 
		return; 
	}
	if(document.${formName}.socTukarKoordinat.value == ""){
		alert('Sila pilih Tukar Koordinat.');
  		document.${formName}.socTukarKoordinat.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "new";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpan";
	document.${formName}.hitButtonDaftar.value = "doHantarProses";
	document.${formName}.submit();
}
function doHantarProses(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "new";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarProses";
	document.${formName}.submit();
}
function doKemaskiniRayuan() {
	document.${formName}.mode.value = "update";
	document.${formName}.hitButton.value = "doKemaskiniRayuan";
	document.${formName}.submit();
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
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
</script>
