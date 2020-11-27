<style type="text/css">
<!--
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
.style3 {color: #FF0000; font-style: italic; }
-->
</style>
#parse("app/php2/online/frmAPBBorangAOnlineHeader.jsp")


#if ($clearForm=="yes")
    #set ($txtNamaDokumen = "")
    #set ($txtCatatan = "")
#end

#if ($flag=="semak")
    #foreach ( $data in $dataDokumen )
        #set ($txtNamaDokumen=$data.nama_dokumen)
        #set ($txtCatatan=$data.catatan)
    #end    
#end

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_laporanpasir" id="id_laporanpasir" value="$id_laporanpasir" />
<input type="hidden" name="idJadualKeduaLesen" id="idJadualKeduaLesen" value="$idJadualKeduaLesen" />
<input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan" />
<input type="hidden" name="actionOnline" id="actionOnline" value="$actionOnline"/>
<input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
<input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
<input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
<input type="hidden" name="idFail" id="idFail" value="$idFail"/>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><fieldset>
      <legend>Muatnaik Laporan Pengeluaran Pasir Laut</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatImejan in $BeanMaklumatDokumen)
        <input type="hidden" name="idDokumen" id="idDokumen" value="$beanMaklumatImejan.idDokumen" />
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style3">*</span>#end</td>
          <td width="28%">Nama Dokumen</td>
          <td width="1%">:</td>
          <td width="70%">
          		<input name="txtNamaDokumen" type="text" id="txtNamaDokumen" value="$beanMaklumatImejan.namaLampiran" size="43" maxlength="100" $readonlyPopup class="$inputTextClassPopup" >
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtCatatan" cols="50" rows="5" class="$inputTextClassPopup" id="txtCatatan" $readonlyPopup>$beanMaklumatImejan.catatanLampiran</textarea></td>
        </tr>
        #if ($modePopup != 'new')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Muat Turun Dokumen</td>
          <td width="1%">:</td>
          <td width="70%"><a href="#" onclick="cetakImej($idDokumen)" class="style2"><font color="blue">$beanMaklumatImejan.namaFail</font></a> </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #end
      #end
        #if ($modePopup == 'new')
        <tr>
          <td>#if ($modePopup != 'view')<span class="style3">*</span>#end</td>
          <td>Direktori Imej</td>
          <td>:</td>
          <td><input id="fileupload" name="fileupload" type="file" size="40" $readonlyPopup2  class="$inputTextClassPopup" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><span class="style4"><i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik fail adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz fail yang lebih kecil.</span></span></td>
        </tr>
        #end
        #if ($modePopup != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> #if ($modePopup == 'new')
            <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanDokumen('$id_laporanpasir','$idPermohonan','$idJadualKeduaLesen')" />    
		   	<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalTambahDokumen()" />
            #end
            #if ($modePopup == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniDokumen('$idDokumen')"/>
		    <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusDokumen('$idDokumen')"/>
		    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliSenaraiDokumen('$id_laporanpasir')" /> 
            #end
            #if ($modePopup == 'update')
            <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanUpdateDokumen('$idDokumen')" />    
		   	<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalKemaskini()" />
            #end </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>

<!--<fieldset>
    <legend>Muatnaik Laporan Pengeluaran Pasir Laut</legend>
    <table width="100%">
   		<tr>
    		<td align="left" width="50%">
      		  #if ($button=="view")
      		  &nbsp;&nbsp;&nbsp;Nama Dokumen
      		  #else
      		  <font color="red">*</font>&nbsp;Nama Dokumen
      		  #end    
      		</td>
    		<td width="1%">:</td>
    		<td width="49%">
    		  #if ($button=="view")
    		  <input type="text" name="txtNamaDokumen" id="txtNamaDokumen" value="$!txtNamaDokumen" size="10" class="disabled" readonly>
    		  #else
    		  <input type="text" name="txtNamaDokumen" id="txtNamaDokumen" value="$!txtNamaDokumen" size="10">
    		  #end    
			</td> 
  		</tr>
  		<tr>
    		<td align="left" width="50%">
      		  #if ($button=="view")
      		  &nbsp;&nbsp;&nbsp;Catatan 
      		  #else
      		  <font color="red">*</font>&nbsp;Catatan 
      		  #end    
      		</td>
    		<td width="1%">:</td>
    		<td width="49%">
    		  #if ($button=="view")
    		  <textarea name="txtCatatan" cols="43" rows="5" class="disabled" id="txtCatatan" readonly onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen13,$!saizTxtCatatan);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen13,$!saizTxtCatatan);" >$!txtCatatan</textarea>
    		  #else
    		  <textarea name="txtCatatan" cols="43" rows="5" class="$inputTextClassPopup" id="txtCatatan" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen13,$!saizTxtCatatan);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen13,$!saizTxtCatatan);" >$!txtCatatan</textarea>
    		  #end    
			</td> 
  		</tr>
  		<tr>
    		<td align="left" width="50%">
      		  #if ($button=="view")
      		  &nbsp;&nbsp;&nbsp;Direktori Imej
      		  #else
      		  <font color="red">*</font>&nbsp;Direktori Imej
      		  #end    
      		</td>
    		<td width="1%">:</td>
   			<td><input id="fileupload" name="fileupload" type="file" size="40" $readonlyPopup2  class="$inputTextClassPopup" /></td> 
  		</tr>-->
  		
  		<!--<tr>
  			<td colspan="3" align="center">
  			
		      ##if ($button=="add")
		        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanDokumen()" />    
		   	    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalTambahDokumen()" />
		   	  ##end
		    
		      ##if ($button=="edit")
		      	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanKemaskiniDokumen()" />    
		   	  	<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalKemaskini('$id_dokumen')" />
		      ##end
		    
		      ##if ($button=="view")
		    	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniDokumen('$id_dokumen')"/>
		    	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusDokumen('$id_dokumen')"/>
		    	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliSenaraiDokumen('$id_laporanpasir')" /> 
		      ##end        
		        
    		</td>
  		</tr>
  
  	</table>
</fieldset>-->


<script>
function simpanDokumen(id_laporanpasir,idPermohonan,idJadualKeduaLesen) {

	if(document.${formName}.txtNamaDokumen.value == ""){
	    alert('Sila masukkan Nama Fail.');
	      document.${formName}.txtNamaDokumen.focus(); 
	    return; 
	  }
	  if(document.${formName}.fileupload.value == ""){
	    alert('Sila pilih Fail yang Ingin Dimuatnaik.');
	      document.${formName}.fileupload.focus(); 
	    return; 
	  }

	  if ( !window.confirm("Adakah Anda Pasti ?") ){
	    return;
	  }
	  
	  var namaLampiran = document.${formName}.txtNamaDokumen.value;
	  var catatanLampiran = document.${formName}.txtCatatan.value ;
	  var dp = document.${formName}.form_token.value ;
	  var dopost = "&form_token="+dp;
	  
	  document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView&hitButton=simpanDokumen&namaLampiran="+namaLampiran+"&catatanLampiran="+catatanLampiran+"&idPermohonan="+idPermohonan+"&id_laporanpasir="+id_laporanpasir+"&idJadualKeduaLesen="+idJadualKeduaLesen+"&actionOnline=papar_laporan"+dopost;
	  document.${formName}.method="post";
	  document.${formName}.enctype="multipart/form-data";
	  document.${formName}.encoding="multipart/form-data";
	  document.${formName}.submit();
}

function cetakImej(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
	
function batalTambahDokumen() {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.actionOnline.value = "papar_laporan";
	document.${formName}.submit();
}

function kembaliSenaraiDokumen(id_laporanpasir) {
	document.${formName}.id_laporanpasir.value = id_laporanpasir;
	document.${formName}.actionOnline.value = "papar_laporan";
	document.${formName}.hitButton.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.submit();
}

function batalKemaskini(id_dokumen) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	// document.${formName}.actionOnline.value = "paparDokumen";
	document.${formName}.actionOnline.value = "uploadBaruDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}

function kemaskiniDokumen(idDokumen) {
	
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.actionOnline.value = "uploadBaruDokumen";
	document.${formName}.modePopup.value = "update";
	document.${formName}.submit();
}

function simpanUpdateDokumen(idDokumen){
	if(document.${formName}.txtNamaDokumen.value == ""){
	    alert('Sila masukkan Nama Fail.');
	      document.${formName}.txtNamaDokumen.focus(); 
	    return; 
	  }
// 	  if(document.${formName}.fileupload.value == ""){
// 	    alert('Sila pilih Fail yang Ingin Dimuatnaik.');
// 	      document.${formName}.fileupload.focus(); 
// 	    return; 
// 	  }

	  if ( !window.confirm("Adakah Anda Pasti ?") ){
	    return;
	  }
	 
	  document.${formName}.idDokumen.value = idDokumen;
	  document.${formName}.hitButton.value = "simpanKemaskiniLampiran";
	  document.${formName}.actionOnline.value = "uploadBaruDokumen";
	  document.${formName}.modePopup.value = "view";
	  document.${formName}.submit();
}

function simpanKemaskiniDokumen() {
	
	if(document.${formName}.txtNamaDokumen.value == ""){
		alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu.");
  		document.${formName}.txtNamaDokumen.focus(); 
		return;		
	}	
	/*if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan \"Catatan\" terlebih dahulu.");
  		document.${formName}.txtCatatan.focus(); 
		return;		
	}*/	
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Fail yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.actionOnline.value = "simpanKemaskiniLampiran";
		document.${formName}.submit();
	}				
}

function hapusDokumen(idDokumen){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
// 	var idDokumen = document.${formName}.idDokumen.value ;		
// 	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView&hitButton=hapusDokumen&actionOnline=papar_laporan&modePopup=&idDokumen="+idDokumen;
	
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.hitButton.value = "hapusDokumen";
	document.${formName}.actionOnline.value = "papar_laporan";
	document.${formName}.modePopup.value = "";
	
	document.${formName}.submit();
	}	
}

</script>