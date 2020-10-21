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

<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
  <div id="progressBarBoxContent"></div>
</div>
#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Fail berjaya dimuatnaik.</div>";
</script>
#end

<fieldset>
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
  		 #if ($modePopup != 'new')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Muat Turun Dokumen</td>
          <td width="1%">:</td>
          <td width="70%"><a href="#" onclick="cetakImej($idDokumen)" class="style2">$beanMaklumatImejan.namaFail</a> </td>
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
  		</tr>
  		
  		<tr>
  			<td colspan="3" align="center">
  			
		      #if ($button=="add")
		        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanDokumen()" />    
		   	    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalTambahDokumen()" />
		   	  #end
		    
		      #if ($button=="edit")
		      	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanKemaskiniDokumen()" />    
		   	  	<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalKemaskini('$id_dokumen')" />
		      #end
		    
		      #if ($button=="view")
		    	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniDokumen('$id_dokumen')"/>
		    	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusDokumen('$id_dokumen')"/>
		    	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliSenaraiDokumen('$id_laporanpasir')" /> 
		      #end        
		        
    		</td>
  		</tr>
  
  	</table>
</fieldset>

<input type="hidden" name="id_dokumen" id="id_dokumen" value="$id_dokumen" />
<input type="hidden" name="id_laporanpasir" id="id_laporanpasir" value="$id_laporanpasir" />
<input type="hidden" name="actionOnline" id="actionOnline" value="$actionOnline"/>

<script>
function simpanDokumen() {
//alert("simpan dokumen");
	if(document.${formName}.txtNamaDokumen.value == ""){
		alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu.");
  		document.${formName}.txtNamaDokumen.focus(); 
		return;		
	}
//alert("simpan dokumen 1");
	if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan \"Catatan\" terlebih dahulu.");
  		document.${formName}.txtCatatan.focus(); 
		return;		
	}
//alert("simpan dokumen 3");	
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Fail yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	}
//alert("simpan dokumen 2");
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.actionOnline.value = "simpanDokumen";
		document.${formName}.submit();
	}			
}

function batalTambahDokumen() {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.actionOnline.value = "papar_laporan";
	document.${formName}.submit();
}

function kembaliSenaraiDokumen(id_laporanpasir) {
	document.${formName}.id_laporanpasir.value = id_laporanpasir;
	document.${formName}.actionOnline.value = "papar_laporan";
	document.${formName}.submit();
}

function batalKemaskini(id_dokumen) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.actionOnline.value = "paparDokumen";
	document.${formName}.submit();
}

function kemaskiniDokumen(id_dokumen) {
	
	document.${formName}.id_dokumen.value = id_dokumen;
	document.${formName}.actionOnline.value = "kemaskiniDokumen";
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

function hapusDokumen(id_dokumen){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_dokumen = document.${formName}.id_dokumen.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView&command=hapusDokumen&id_dokumen="+id_dokumen;	
	document.${formName}.submit();
	}	
}

</script>