<style type="text/css">
<!--
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
.style3 {color: #FF0000; font-style: italic; }
-->
</style>
#parse("app/php2/frmAPBLaporanPasirHeader.jsp")


#if ($clearForm=="yes")
    #set ($txtNamaDokumen = "")
    #set ($txtCatatan = "")
#end

#if ($flag=="semak")
    #foreach ( $data in $dataDokumen )
        #set ($txtNamaDokumen=$data.nama_dokumen)
        #set ($txtCatatan=$data.catatan)
        #set ($namaFail=$data.nama_fail)
        #set ($idDokumen=$data.id_dokumen)
    #end    
#end

<fieldset>
    <legend>Muatnaik Laporan Pengeluaran Pasir Laut</legend>
    <table width="100%">
   		<tr>
    		<td align="left" width="20%">
      		  #if ($button=="view")
      		  &nbsp;&nbsp;&nbsp;Nama Dokumen
      		  #else
      		  <font color="red">*</font>&nbsp;Nama Dokumen
      		  #end    
      		</td>
    		<td width="1%">:</td>
    		<td width="79%">
    		  #if ($mode=="disabled")
    		  <input type="text" name="txtNamaDokumen" id="txtNamaDokumen" value="$!txtNamaDokumen" size="10" class="disabled" readonly>
    		  #else
    		  <input type="text" name="txtNamaDokumen" id="txtNamaDokumen" value="$!txtNamaDokumen" size="10">
    		  #end    
			</td> 
  		</tr>
  		<tr>
    		<td align="left">
      		  #if ($button=="view")
      		  &nbsp;&nbsp;&nbsp;Catatan 
      		  #else
      		  <font color="red">*</font>&nbsp;Catatan 
      		  #end    
      		</td>
    		<td>:</td>
    		<td>
    		  #if ($mode=="disabled")
    		  <textarea name="txtCatatan" cols="43" rows="5" class="disabled" id="txtCatatan" readonly onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen13,$!saizTxtCatatan);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen13,$!saizTxtCatatan);" >$!txtCatatan</textarea>
    		  #else
    		  <textarea name="txtCatatan" cols="43" rows="5" class="$inputTextClassPopup" id="txtCatatan" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen13,$!saizTxtCatatan);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen13,$!saizTxtCatatan);" >$!txtCatatan</textarea>
    		  #end    
			</td> 
  		</tr>
  		<tr>
    		<td align="left">
      		  #if ($button=="view")
      		  &nbsp;&nbsp;&nbsp;Direktori Imej
      		  #else
      		  <font color="red">*</font>&nbsp;Direktori Imej
      		  #end    
      		</td>
    		<td>:</td>
   			<td><a href="#" onclick="cetakImej($idDokumen)" class="style2"><font color="blue">$!namaFail</font></a> </td> 
  		</tr>
  		 <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>&nbsp;</td>
  			<td align="left">
  			
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
<input type="hidden" name="actionLaporanPasir" id="actionLaporanPasir" value="$actionLaporanPasir"/>

<script>
function simpanDokumen() {

	if(document.${formName}.txtNamaDokumen.value == ""){
		alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu.");
  		document.${formName}.txtNamaDokumen.focus(); 
		return;		
	}	
	if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan \"Catatan\" terlebih dahulu.");
  		document.${formName}.txtCatatan.focus(); 
		return;		
	}	
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.actionLaporanPasir.value = "simpanDokumen";
		document.${formName}.submit();
	}			
}

function batalTambahDokumen() {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.actionLaporanPasir.value = "papar_laporan";
	document.${formName}.submit();
}

function kembaliSenaraiDokumen(id_laporanpasir) {
	document.${formName}.id_laporanpasir.value = id_laporanpasir;
	document.${formName}.actionLaporanPasir.value = "papar_laporan";
	document.${formName}.submit();
}

function batalKemaskini(id_dokumen) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.actionLaporanPasir.value = "paparDokumen";
	document.${formName}.submit();
}

function kemaskiniDokumen(id_dokumen) {
	
	document.${formName}.id_dokumen.value = id_dokumen;
	document.${formName}.actionLaporanPasir.value = "kemaskiniDokumen";
	document.${formName}.submit();
}

function simpanKemaskiniDokumen() {
	
	if(document.${formName}.txtNamaDokumen.value == ""){
		alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu.");
  		document.${formName}.txtNamaDokumen.focus(); 
		return;		
	}	
	if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan \"Catatan\" terlebih dahulu.");
  		document.${formName}.txtCatatan.focus(); 
		return;		
	}	
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.actionLaporanPasir.value = "simpanKemaskiniLampiran";
		document.${formName}.submit();
	}				
}

function hapusDokumen(id_dokumen){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_dokumen = document.${formName}.id_dokumen.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBLaporanPasir&command=hapusDokumen&id_dokumen="+id_dokumen;	
	document.${formName}.submit();
	}	
}

function cetakImej(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>