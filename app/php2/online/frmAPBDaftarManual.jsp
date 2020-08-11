
<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
#set($saizTxtTujuanPengambilan="500")
#set($saizTxtRingkasanPengalaman="900")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
   <input name="idPermohonanLama" type="hidden" id="idPermohonanLama" value="$idPermohonanLama"/>
   <input name="checkId" type="hidden" id="checkId" value="$checkId"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idJenisPermohonan" type="hidden" id="idJenisPermohonan" value="$idJenisPermohonan"/>
  <input name="idNegeriPemohon" type="hidden" id="idNegeriPemohon" value="$idNegeriPemohon"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	#if ($checkId != '')	
 <tr>  
  	<td colspan="2"><fieldset>
  	  <legend><strong>JENIS PERMOHONAN</strong></legend>
  	  <table width="100%" border="0" cellspacing="2" cellpadding="2">
  	<!--  <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Jenis Permohonan</td>
          <td width="1%">:</td>
          <td width="70%">$selectJenisPermohonan</td>
        </tr> 
        -->
        #if ($idStatus == '')
        <tr>
        <td width="1%">&nbsp;</td>
  		<td>Jenis Permohonan</td>
  		<td width = "1%">:</td>
  		<td width ="70%">PERMOHONAN BARU</td>		
  	</tr>
  	#end
  	#if ($idStatus == '1610207')
  	 <tr>
        <td width="1%">&nbsp;</td>
  		<td>Jenis Permohonan</td>
  		<td width = "1%">:</td>
  		<td width ="70%">PEMBAHARUAN LESEN</td>		
  	</tr>
  	
  	 <tr>
        <td width="1%">&nbsp;</td>
  		<td>No. Fail Lama</td>
  		<td width = "1%">:</td>
  		<td width ="70%">$!noFailLama</td>		
  	</tr>
  	 
  	#end
  	  
        
      <!-- 
        #if ($idJenisPermohonan == '1')
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">No. Permohonan</td>
          <td>:</td>
          <td><strong>$beanMaklumatPermohonan.noPermohonan</strong>
            <input name="idPermohonan" type="hidden" value="$beanMaklumatPermohonan.idPermohonan" />
            <input name="idPemohon" type="hidden" value="$beanMaklumatPermohonan.idPemohon" />
          </td>
        </tr>
        #end
        #elseif ($idJenisPermohonan == '2') -->
<!--         #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan) -->
      <!--    <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">No. Fail Lama</td>
          <td>:</td>
          <td>$!c.get("noFailLama")
          	<input type="text" name="txtNoFailLama" id="txtNoFailLama" size="43" value="$beanMaklumatPermohonan.noFail" 
          		onblur="this.value=this.value.toUpperCase();doChangeNoFailAPB();" $readonly class="$inputTextClass"/>
          </td>
          </td>
        </tr> -->
<!--         #end -->
     <!--     #end
         -->
  	  </table>
  	</td>
  </tr>

  <tr>
  	<td colspan="2"><fieldset>
  	<legend><strong>MAKLUMAT PEMOHON</strong></legend>
  	<table width="100%" border="0" cellspacing="2" cellpadding="2">
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">Kategori Pemohon</td>
  		<td width="1%">:</td>
  		<td width="70%"> $!pemohon.get("kategoriPemohon")</td>
  	</tr>
  	
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">
  		#if($!pemohon.get("kategoriPemohon") == "INDIVIDU") 
  			Nama Pemohon 
  		#end
  		#if($!pemohon.get("kategoriPemohon") != "INDIVIDU")
  			Nama Syarikat
  		#end
  		</td>
  		<td width="1%">:</td>
  		<td width="70%"> $!pemohon.get("namaPemohon")
  		<input type="hidden" name="txtNama" id="txtNama" value="$!pemohon.get("namaPemohon")"></td>
  	</tr>
 	<tr>
  		<td></td>
  		<td>
  		#if($!pemohon.get("kategoriPemohon") == "INDIVIDU") 
  			No. Kad Pengenalan/MyID 
  		#end
  		#if($!pemohon.get("kategoriPemohon") != "INDIVIDU")
  			No. Pendaftaran Syarikat/MyCoid
  		#end
  		</td>
  		<td>:</td>
  		<td>$!pemohon.get("noPengenalan")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td valign="top">Alamat</td>
  		<td valign="top">:</td>
  		<td>$!pemohon.get("alamat1")<br>$!pemohon.get("alamat2")<br>$!pemohon.get("alamat3")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td>Poskod</td>
  		<td>:</td>
  		<td>$!pemohon.get("poskod")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td>Negeri</td>
  		<td>:</td>
  		<td>$!pemohon.get("negeri")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td>Bandar</td>
  		<td>:</td>
  		<td>$!pemohon.get("bandar")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td>No. Tel</td>
  		<td>:</td>
  		<td>$!pemohon.get("noTel")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td>No. Faks</td>
  		<td>:</td>
  		<td>$!pemohon.get("noFax")</td>
  	</tr>
  	  	<tr>
  		<td></td>
  		<td>Emel</td>
  		<td>:</td>
  		<td>$!pemohon.get("emel")</td>
  	</tr>
  	</fieldset>
  	</table>
  	</td>
  </tr>
  
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
       <!--  <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">No. Permohonan</td>
          <td width="1%" >:</td>
          <td width="70%"><strong>$beanMaklumatPermohonan.noPermohonan</strong>
            <input name="idPermohonan" type="hidden" value="$beanMaklumatPermohonan.idPermohonan" />
            <input name="idPemohon" type="hidden" value="$beanMaklumatPermohonan.idPemohon" /></td>
        </tr>
         -->
         #if ($idStatus != '')
        <tr>
        <td width="1%">&nbsp;</td>
  		<td>No. Permohonan</td>
  		<td width = "1%">:</td>
  		<td width ="70%">$!noPermohonan</td>		
  	</tr>
  	#end
        <tr>
          <td>&nbsp;</td>
          <td >Urusan</td>
          <td>:</td>
          <td>AKTA PELANTAR BENUA
            <input type="hidden" name="idUrusan" id="idUrusan" value="9"/>
             <input type="hidden" name="idSubUrusan" id="idSubUrusan" value="57"/></td>
        </tr>
        <tr>
          <td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td valign="top">Jenis Lesen</td>
          <td>:</td>
          <td>$selectJenisLesen</td>
        </tr>
        <tr>
           <td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td>Jenis Tujuan</td>
          <td>:</td>
          <td width="70%">$selectJenisTujuan</td>
        </tr>
        <tr>
          <td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td>Kaitan Tujuan</td>
          <td>:</td>
          <td>$selectTujuanKaitan</td>
        </tr>
        <tr>
          <td valign="top">#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td valign="top">Tujuan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtTujuanPengambilan" id="txtTujuanPengambilan" cols="43" rows="5" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtTujuanPengambilan,this.form.remLen1,$!saizTxtTujuanPengambilan);" onKeyDown="textCounter(this.form.txtTujuanPengambilan,this.form.remLen1,$!saizTxtTujuanPengambilan);" >$beanMaklumatPermohonan.tujuanPengambilan</textarea></td>
        </tr>
        #if ($mode == 'new')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
          <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtTujuanPengambilan" /></td>
        </tr>
        #end
        <tr>
          <td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td>Tempoh Lesen Dipohon</td>
          <td>:</td>
          <td><!--<input name="txtTempoh" type="text" size="1" maxlength="2" onkeyup="validateNumber(this,this.value);" value="$beanMaklumatPermohonan.tempoh" $readonly class="$inputTextClass"/>
            $selectTempoh-->            
            <select name="socTempoh" id="socTempoh" style="width:90px;" $readonly class="$disabled" $disabled >   
            #if ($beanMaklumatPermohonan.tempoh == '1')
                <option>SILA PILIH</option>
                <option value="1" selected="selected">1</option>
                <option value="2">2</option>  
                <option value="3">3</option>  
                <option value="4">4</option>  
                <option value="5">5</option>  
            #elseif ($beanMaklumatPermohonan.tempoh == '2')
                <option>SILA PILIH</option>
                <option value="1">1</option>
                <option value="2" selected="selected">2</option>  
                <option value="3">3</option>  
                <option value="4">4</option>  
                <option value="5">5</option> 
            #elseif ($beanMaklumatPermohonan.tempoh == '3')
                <option>SILA PILIH</option>
                <option value="1">1</option>
                <option value="2">2</option>  
                <option value="3" selected="selected">3</option>  
                <option value="4">4</option>  
                <option value="5">5</option> 
            #elseif ($beanMaklumatPermohonan.tempoh == '4')
                <option>SILA PILIH</option>
                <option value="1">1</option>
                <option value="2">2</option>  
                <option value="3">3</option>  
                <option value="4" selected="selected">4</option>  
                <option value="5">5</option> 
            #elseif ($beanMaklumatPermohonan.tempoh == '5')
                <option>SILA PILIH</option>
                <option value="1">1</option>
                <option value="2">2</option>  
                <option value="3">3</option>  
                <option value="4">4</option>  
                <option value="5" selected="selected">5</option>               
            #else
                 <option>SILA PILIH</option>
                <option value="1">1</option>
                <option value="2">2</option>  
                <option value="3">3</option>  
                <option value="4">4</option>  
                <option value="5">5</option>  
            #end
               </select> 
              TAHUN
           </td>
        </tr>
         #if ($mode == 'new')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top"><i><font color="#ff0000">* </font>Maksimum Tempoh Lesen Dipohon adalah 5 tahun.</i></td>
        </tr>
        #end
        <tr>
          <td valign="top">#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td valign="top">Ringkasan Pengalaman Pemohon</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtRingkasanPengalaman" id="txtRingkasanPengalaman" cols="43" rows="5" onKeyUp="textCounter(this.form.txtRingkasanPengalaman,this.form.remLen2,$!saizTxtRingkasanPengalaman);" onKeyDown="textCounter(this.form.txtRingkasanPengalaman,this.form.remLen2,$!saizTxtRingkasanPengalaman);" $readonly class="$inputTextClass">$beanMaklumatPermohonan.pengalaman</textarea></td>
        </tr>
        #if ($mode == 'new')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
              <input type="text" readonly="readonly" class="disabled" name="remLen2" size="3" maxlength="3" value="$!saizTxtRingkasanPengalaman" /></td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>KAWASAN PERMOHONAN</strong></legend>
     <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)        
        <tr>
        	<td width="1%">&nbsp;</td>
        	<td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td width="28%" valign="top">Luar Perairan Negeri</td>
          <td width="1%">:</td>
          <td width="70%"><strong>$selectFlagLuar </strong>
          </td>
        </tr>
        
        <tr>
          <td></td>
          <td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td>Negeri</td>
          <td width="1%">:</td>
          <td width="70%"><strong>$selectNegeri</strong>
          </td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td>Lokasi</td>
          <td width="1%">:</td>
          <td width="top">
            <input name="txtLokasi" type="text" class="$inputTextClass" id="txtLokasi"  value="$beanMaklumatPermohonan.lokasi" size="43" maxlength="250" $readonly />
            </td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td>Luas Dipohon</td>
          <td width="1%">:</td>
          <td width="top">
            <input type="text" name="txtLuas" id="txtLuas" size="10" onkeyup="validateNumber(this,this.value);" maxlength="10" value="$beanMaklumatPermohonan.luas" $readonly class="$inputTextClass"/>
            $selectLuas</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Untuk makluman kawasan permohonan mestilah di luar perairan negeri dan pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%">       
      #if (($mode == 'new' && $idStatus == '1610207') || ($mode == 'new' && $idStatus == ''))
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Seterusnya" onclick="daftarBaru()"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
      #end 
      </td>
  </tr>
  #end
</table>
<script>
function doChangeJenisPermohonan() {
	doAjaxCall${formName}("doChangeJenisPermohonan");
}
function doChangeNoFailAPB() {
	doAjaxCall${formName}("doChangeNoFailAPB");
}
function daftarBaru() {

	if(document.${formName}.socJenisLesen.value == ""){
		alert('Sila pilih Jenis Lesen.');
  		document.${formName}.socJenisLesen.focus(); 
		return; 
	}
	if(document.${formName}.socKaitanTujuan.value == ""){
		alert('Sila pilih Kaitan Tujuan.');
  		document.${formName}.socKaitanTujuan.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanPengambilan.value == ""){
		alert('Sila masukkan Tujuan.');
  		document.${formName}.txtTujuanPengambilan.focus(); 
		return; 
	}
	if(document.${formName}.socTempoh.value == "SILA PILIH"){
		alert('Sila pilih Tempoh Lesen Dipohon.');
  		document.${formName}.socTempoh.focus(); 
		return; 
	}
	if(document.${formName}.txtRingkasanPengalaman.value == ""){
		alert('Sila masukkan Ringkasan Pengalaman Pemohon.');
  		document.${formName}.txtRingkasanPengalaman.focus(); 
		return; 
	}
	if(document.${formName}.socFlagLuar.value == ""){
		alert('Sila masukkan Luar Perairan Negeri.');
  		document.${formName}.socFlagLuar.focus(); 
		return; 
	}
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.txtLokasi.value == ""){
		alert('Sila masukkan lokasi.');
  		document.${formName}.txtLokasi.focus(); 
		return; 
	}
	if(document.${formName}.txtLuas.value == ""){
		alert('Sila masukkan Luas dipohon.');
  		document.${formName}.txtLuas.focus(); 
		return; 
	}
	if(document.${formName}.socLuas.value == ""){
		alert('Sila pilih jenis luas.');
  		document.${formName}.socLuas.focus();
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionOnline.value = "daftarBaru";
		return;
	}

	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.hitButton.value = "daftarBaru";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function kembali() {	
	document.${formName}.actionOnline.value = "";
	document.${formName}.submit();
}
function seterusnya() {
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
</script>
