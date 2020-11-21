
<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
#set($saizTxtTujuanPengambilan="500")
#set($saizTxtRingkasanPengalaman="900")
#set($saizTxtUndangUndang="900")
#set($saizTxtJenisPerniagaan="900")
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
		  			<input type="hidden" name="txtNama" id="txtNama" value="$!pemohon.get("namaPemohon")">
		  		</td>
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
  		</table>
	</fieldset></td>  	
  </tr>
  
  <tr>
  	<td colspan="2"><fieldset>
  	  	<legend><strong>JENIS PERMOHONAN</strong></legend>
  	  	<table width="100%" border="0" cellspacing="2" cellpadding="2">
  	  		<tr>
	          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
	          <td valign="top">Jenis Permohonan</td>
	          <td width="1%">:</td>
	          <td width="70%">$selectJenisPermohonan</td>
	        </tr>
        	#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        	#if ($idJenisPermohonan == '1')
        	<tr>
	          <td width="1%">&nbsp;</td>
	          <td valign="top">No. Permohonan</td>
	          <td>:</td>
	          <td><strong>$beanMaklumatPermohonan.noPermohonan</strong>
	            <input name="idPermohonan" type="hidden" value="$beanMaklumatPermohonan.idPermohonan" />
	            <input name="idPemohon" type="hidden" value="$beanMaklumatPermohonan.idPemohon" />
	          </td>
	        </tr>
	        #elseif ($idJenisPermohonan == '2') 
	        <tr>
	          <td width="1%">&nbsp;</td>
	          <td valign="top">Senarai No. Fail Lama</td>
	          <td>:</td>
	          <td>$!selectNoFailLama</td>
	        </tr>
        	#end
        	#end
  	  	</table>
  	</fieldset></td>
  </tr>
  
  <tr>
    <td colspan="2"><fieldset>
      	<legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      		#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
      		#if ($idStatus != '')
		    <tr>
		        <td width="1%">&nbsp;</td>
		  		<td>No. Permohonan</td>
		  		<td width = "1%">:</td>
		  		<td width ="70%">$!noPermohonan</td>		
		  	</tr>
      		#end
      		<tr>
	          <td width="1%">&nbsp;</td>
	          <td width="28%" valign="top">Urusan</td>
	          <td width="1%">:</td>
	          <td width="70%">AKTA PELANTAR BENUA
	            <input type="hidden" name="idUrusan" id="idUrusan" value="9"/>
	            <input type="hidden" name="idSubUrusan" id="idSubUrusan" value="57"/>
	          </td>
	        </tr>
	        <tr>
	          <td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
	          <td width="28%" valign="top">Jenis Lesen</td>
	          <td width="1%">:</td>
	          <td width="70%">$selectJenisLesen</td>
	          <td>
	          	<input type="hidden" name="idJenisLesen" id="idJenisLesen" value="$idJenisLesen"/>
	          </td>
	        </tr>
	        <tr>
	           <td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
		       <td width="28%">Jenis Tujuan</td>
		       <td width="1%">:</td>
		       #if($idJenisLesen == 2)
		       	<td width="70%">MENGOREK</td>
		       #elseif($idJenisLesen == 3 || $idJenisLesen == 4)
		       	<td width="70%">MENCARIGALI/ MENJELAJAH</td>
		       #else
		       	<td></td>
		       #end
	        </tr>
	        <tr>
	          <td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
	          <td width="28%">Kaitan Tujuan</td>
	          <td width="1%">:</td>
	          <td width="70%">$selectTujuanKaitan
				<a href="javascript:open_info()" class="help" title="info">							
					<b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
				</a>
			  </td>
	        </tr>
	        <tr>
	          <td width="1%" valign="top">#if ($mode == 'new')<span class="style1">*</span>#end</td>
	          <td width="28%" valign="top">Tujuan Pengambilan</td>
	          <td width="1%" valign="top">:</td>
	          <td width="70%"><textarea name="txtTujuanPengambilan" id="txtTujuanPengambilan" cols="43" rows="5" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtTujuanPengambilan,this.form.remLen1,$!saizTxtTujuanPengambilan);" onKeyDown="textCounter(this.form.txtTujuanPengambilan,this.form.remLen1,$!saizTxtTujuanPengambilan);" >$beanMaklumatPermohonan.tujuanPengambilan</textarea></td>
	        </tr>
	        #if ($mode == 'new')
	        <tr>
	          <td valign="top">&nbsp;</td>
	          <td valign="top">&nbsp;</td>
	          <td valign="top">&nbsp;</td>
	          <td width="28%">Baki Aksara :&nbsp;
	          	<input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtTujuanPengambilan" />
	          </td>
	        </tr>
	        #end
	        <tr>
	          <td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
	          <td width="28%">Tempoh Lesen Dipohon</td>
	          <td width="1%">:</td>
	          <td>
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
	          <td width="28%"><i><font color="#ff0000">* </font>Maksimum Tempoh Lesen Dipohon adalah 5 tahun.</i></td>
	        </tr>
	        #end
	        <tr>
	          <td width="1%" valign="top">#if ($mode == 'new')<span class="style1">*</span>#end</td>
	          <td width="28%" valign="top">Ringkasan Pengalaman Pemohon</td>
	          <td width="1%" valign="top">:</td>
	          <td width="70%">
	          	<textarea name="txtRingkasanPengalaman" id="txtRingkasanPengalaman" cols="43" rows="5" onKeyUp="textCounter(this.form.txtRingkasanPengalaman,this.form.remLen2,$!saizTxtRingkasanPengalaman);" onKeyDown="textCounter(this.form.txtRingkasanPengalaman,this.form.remLen2,$!saizTxtRingkasanPengalaman);" $readonly class="$inputTextClass">$beanMaklumatPermohonan.pengalaman</textarea>
	          </td>
	        </tr>
	        <tr>
		  	  <td width="1%" valign="top">#if ($mode == 'new')<span class="style1">*</span>#end</td>
		  	  <td width="28%" valign="top">Undang-Undang Diperbadankan</td>
		  	  <td width="1%" valign="top">:</td>
		  	  <td width="70%">
	          	<textarea name="txtUndangUndang" id="txtUndangUndang" cols="43" rows="5" onblur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtUndangUndang,this.form.remLen2,$!saizTxtUndangUndang);" onKeyDown="textCounter(this.form.txtUndangUndang,this.form.remLen2,$!saizTxtUndangUndang);" $readonly class="$inputTextClass">$beanMaklumatPermohonan.undangUndang</textarea>
		      </td>
		    </tr>
	         #if ($mode == 'new')
	        <tr>
	          <td valign="top">&nbsp;</td>
	          <td valign="top">&nbsp;</td>
	          <td valign="top">&nbsp;</td>
	          <td width="1%">Baki Aksara :&nbsp;
	              <input type="text" readonly="readonly" class="disabled" name="remLen2" size="3" maxlength="3" value="$!saizTxtRingkasanPengalaman" />
	          </td>
	        </tr>
	        #end
	        <tr>
		        <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
		        <td valign="top">Jenis Perniagaan</td>
		        <td valign="top">:</td>
		        <td valign="top">
		        	<textarea name="txtJenisPerniagaan" id="txtJenisPerniagaan" cols="43" rows="5" $readonly onblur="this.value=this.value.toUpperCase();" class="$inputTextClass" onKeyUp="textCounter(this.form.txtJenisPerniagaan,this.form.remLen4,$!saizTxtJenisPerniagaan);" onKeyDown="textCounter(this.form.txtJenisPerniagaan,this.form.remLen4,$!saizTxtJenisPerniagaan);" >$beanMaklumatPermohonan.jenisPerniagaan</textarea>
		        	<a href="javascript:open_info1()" class="help" title="info">							
						<b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
					</a>
		        </td>
		    </tr>
		    #if ($mode == 'new')
		    <tr>
		        <td valign="top">&nbsp;</td>
		        <td valign="top">&nbsp;</td>
		        <td valign="top">&nbsp;</td>
		        <td width="28%">Baki Aksara :&nbsp;
		          <input type="text" readonly="readonly" class="disabled" name="remLen4" size="3" maxlength="3" value="$!saizTxtJenisPerniagaan" /></td>
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
        #foreach ($beanMaklumatKawasanMohon in $BeanMaklumatKawasanMohon)
        	<tr>
	          <td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
	          <td width="28%" valign="top">Luar Perairan Negeri</td>
	          <td width="1%">:</td>
	          <td width="70%"><strong>$selectFlagLuar</strong></td>
	        </tr>
	        <tr>
	          <td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
	          <td width="28%">Negeri</td>
	          <td width="1%">:</td>
	          <td width="70%">
	          	<strong>$selectNegeriPerairan</strong>
	          	<input type="hidden" name="idNegeriPerairan" id="idNegeriPerairan" value="$idNegeriPerairan"/>
	          </td>
	        </tr>
	        <tr>
	          <td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
	          <td>Lokasi</td>
	          <td width="1%">:</td>
	          <td width="top">
	            <input name="txtLokasi" type="text" class="$inputTextClass" id="txtLokasi"  value="$beanMaklumatKawasanMohon.lokasi" size="43" maxlength="250" $readonly />
	          </td>
	        </tr>
	        <tr>
	          <td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
	          <td>Luas Dipohon</td>
	          <td width="1%">:</td>
	          <td width="top">
	            <input type="text" name="txtLuas" id="txtLuas" size="10" onkeyup="validateNumber(this,this.value);" maxlength="10" value="$beanMaklumatKawasanMohon.luas" $readonly class="$inputTextClass"/>
	            $selectLuas
	          </td>
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
function doChangeJenisLesen() {
	doAjaxCall${formName}("doChangeJenisLesen");
}
function doChangeNoFailAPB() {
	doAjaxCall${formName}("doChangeNoFailAPB");
}
function doChangeNoFailLama() {
	doAjaxCall${formName}("doChangeNoFailLama");
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
	if(document.${formName}.txtUndangUndang.value == ""){
		alert('Sila masukkan maklumat Undang-Undang Diperbadankan.');
  		document.${formName}.txtUndangUndang.focus(); 
		return; 
	}
	if(document.${formName}.txtJenisPerniagaan.value == ""){
		alert('Sila masukkan maklumat Undang-Undang Diperbadankan.');
  		document.${formName}.txtJenisPerniagaan.focus(); 
		return; 
	}
	if(document.${formName}.socFlagLuar.value == ""){
		alert('Sila masukkan Luar Perairan Negeri.');
  		document.${formName}.socFlagLuar.focus(); 
		return; 
	}
	if(document.${formName}.socNegeriPerairan.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeriPerairan.focus(); 
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
function open_info() {
	var width  = 550;
	var height = 300;
	var left   = (screen.width  - width)/2;
	var top    = (screen.height - height)/2;
 
	var params = 'width='+width+', height='+height;
 	params += ', top='+top+', left='+left;
	params += ', directories=no';
	params += ', location=front';
	params += ', menubar=no';
	params += ', resizable=no';
	params += ', scrollbars=no';
	params += ', status=no';
	params += ', toolbar=no';
	new_window = open("","title",params);
	new_window.document.open();

	new_window.document.write("<html><title>Info Kaitan Tujuan</title>");
	new_window.document.write("<body bgcolor=\"#FFFFFF\">");
	new_window.document.write("<table><tr><td><b><u>Jenis-Jenis Lesen</u></b></td></tr></table>");
	
	new_window.document.write("<table width='100%'><tr><td width='50%' valign='top'>");
	
	new_window.document.write("<table><tr><td align='justify'><b>1. Borang 2(Lesen Pasir)</b> Pilih 'Menjalankan operasi'.</td> <tr><td align='justify'><b>2. Borang 3(Lesen Menjelajah/Mencari Gali/Menggerek)</b> Pilih 'Mencari gali'. <b>3. Borang 4(Lesen galian selain pasir)</b> Pilih 'Melombong'. </td></tr></table>");
	new_window.document.write("</body></html>");
	new_window.document.close();
}
function open_info1() {
	var width  = 550;
	var height = 300;
	var left   = (screen.width  - width)/2;
	var top    = (screen.height - height)/2;
 
	var params = 'width='+width+', height='+height;
 	params += ', top='+top+', left='+left;
	params += ', directories=no';
	params += ', location=front';
	params += ', menubar=no';
	params += ', resizable=no';
	params += ', scrollbars=no';
	params += ', status=no';
	params += ', toolbar=no';
	new_window = open("","title",params);
	new_window.document.open();

	new_window.document.write("<html><title>Info Jenis Perniagaan</title>");
	new_window.document.write("<body bgcolor=\"#FFFFFF\">");
	
	new_window.document.write("<table><tr><td><b>Jenis Perniagaan</b></td></tr>");
	new_window.document.write("<tr><td><font><li>&nbsp;Berhad </li></font>");
	new_window.document.write("<font><li>&nbsp;Sendirian Berhad</li></font>");
	new_window.document.write("<font><li>&nbsp;Enterprise</li></font></td></tr></table>");
	
	new_window.document.write("</body></html>");
	new_window.document.close();
}
</script>