<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  
  <input name="actionPenswastaan" type="hidden" id="actionPenswastaan" value="$actionPenswastaan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPemaju" type="hidden" id="idPemaju" value="$idPemaju"/>
  <input name="idPengarah" type="hidden" id="idPengarah" value="$idPengarah"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td>
    	#parse("app/htp/frmPenswastaan2Header.jsp")
    </td>
  </tr>  
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if ($mode == 'view' || $mode == 'update')
  <tr>
    <td>
    
    <fieldset>
    <legend><strong>MAKLUMAT SYARIKAT</strong></legend>
    
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
    	
	#if($!BeanMaklumatSyarikat.isEmpty())
     <tr>
        <td width="50%" valign="top">        
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">        
          <tr>
            <td width="1%"><font color="#FF0000">*</font></td>
            <td width="29%">Nama</td>
            <td width="70%">: 
              <input name="txtNamaSyarikat" type="text" class="$inputTextClass" id="txtNamaSyarikat" value="$!beanMaklumatSyarikat.nama" size="40" $readonly onBlur="this.value=this.value.toUpperCase();" ></td>
          </tr>
          <tr>
            <td><font color="#FF0000">*</font></td>
            <td>No. Pendaftaran</td>
            <td>: 
              <input type="text" name="txtNoPendaftaran" id="txtNoPendaftaran" $readonly class="$inputTextClass" value="$!beanMaklumatSyarikat.noRujukan" onBlur="this.value=this.value.toUpperCase();"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>No. Telefon</td>
            <td>: 
              <input type="text" name="txtNoTel" id="txtNoTel" $readonly class="$inputTextClass" value="$!beanMaklumatSyarikat.noTel"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>No. Fax</td>
            <td>: 
              <input type="text" name="txtNoFax" id="txtNoFax" $readonly class="$inputTextClass" value="$!beanMaklumatSyarikat.noFax"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        
        </td>
        <td width="50%" valign="top">
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td width="1%"><font color="#FF0000">*</font></td>
            <td width="29%">Alamat</td>
            <td width="70%">: 
              <input name="txtAlamat1" type="text" class="$inputTextClass" id="txtAlamat1" onBlur="this.value=this.value.toUpperCase();" value="$!beanMaklumatSyarikat.alamat1" size="40" $readonly></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>: 
              <input name="txtAlamat2" type="text" class="$inputTextClass" id="txtAlamat2" onBlur="this.value=this.value.toUpperCase();" value="$!beanMaklumatSyarikat.alamat2" size="40" $readonly></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>: 
              <input name="txtAlamat3" type="text" class="$inputTextClass" id="txtAlamat3" onBlur="this.value=this.value.toUpperCase();" value="$!beanMaklumatSyarikat.alamat3" size="40" $readonly></td>
          </tr>
          <tr>
            <td><font color="#FF0000">*</font></td>
            <td>Poskod</td>
            <td>: 
              <input maxlength="5" onkeyup="validatePoskod(this,this.value);" type="text" name="txtPoskod" id="txtPoskod" $readonly class="$inputTextClass" value="$!beanMaklumatSyarikat.poskod" size="5"></td>
          </tr>
          <tr>
            <td><font color="#FF0000">*</font></td>
            <td>Negeri</td>
            <td>: $selectNegeri</td>
          </tr>
          <tr>
            <td><font color="#FF0000">*</font></td>
            <td>Bandar</td>
            <td>: $selectDaerah</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        
        </td>
      </tr>		
		
	#else
	
      #foreach ($beanMaklumatSyarikat in $BeanMaklumatSyarikat)
      <tr>
        <td width="50%" valign="top">        
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">        
          <tr>
            <td width="1%">#if ($mode == 'update')<font color="#FF0000">*</font>#end</td>
            <td width="29%">Nama</td>
            <td width="70%">: 
              <input name="txtNamaSyarikat" type="text" class="$inputTextClass" id="txtNamaSyarikat" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatSyarikat.nama" size="40" $readonly></td>
          </tr>
          <tr>
            <td>#if ($mode == 'update')<font color="#FF0000">*</font>#end</td>
            <td>No. Pendaftaran</td>
            <td>: 
              <input type="text" name="txtNoPendaftaran" id="txtNoPendaftaran" $readonly class="$inputTextClass" value="$beanMaklumatSyarikat.noRujukan" onBlur="this.value=this.value.toUpperCase();"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>No. Telefon</td>
            <td>: 
              <input type="text" name="txtNoTel" id="txtNoTel" $readonly class="$inputTextClass" value="$beanMaklumatSyarikat.noTel"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>No. Fax</td>
            <td>: 
              <input type="text" name="txtNoFax" id="txtNoFax" $readonly class="$inputTextClass" value="$beanMaklumatSyarikat.noFax"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        
        </td>
        <td width="50%" valign="top">
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td width="1%">#if ($mode == 'update')<font color="#FF0000">*</font>#end</td>
            <td width="29%">Alamat</td>
            <td width="70%">: 
              <input name="txtAlamat1" type="text" class="$inputTextClass" id="txtAlamat1" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatSyarikat.alamat1" size="40" $readonly></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>: 
              <input name="txtAlamat2" type="text" class="$inputTextClass" id="txtAlamat2" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatSyarikat.alamat2" size="40" $readonly></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>: 
              <input name="txtAlamat3" type="text" class="$inputTextClass" id="txtAlamat3" onBlur="this.value=this.value.toUpperCase();" value="$beanMaklumatSyarikat.alamat3" size="40" $readonly></td>
          </tr>
          <tr>
            <td>#if ($mode == 'update')<font color="#FF0000">*</font>#end</td>
            <td>Poskod</td>
            <td>: 
              <input maxlength="5" onkeyup="validatePoskod(this,this.value);" type="text" name="txtPoskod" id="txtPoskod" $readonly class="$inputTextClass" value="$beanMaklumatSyarikat.poskod" size="5"></td>
          </tr>
          <tr>
            <td>#if ($mode == 'update')<font color="#FF0000">*</font>#end</td>
            <td>Negeri</td>
            <td>: $selectNegeri</td>
          </tr>
          <tr>
            <td>#if ($mode == 'update')<font color="#FF0000">*</font>#end</td>
            <td>Bandar</td>
            <td>: $selectDaerah</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        
        </td>
      </tr>
      #end
	
	#end

    </table>

    </fieldset>
    
    </td>
  </tr>  
       <tr>
      	<td align="center">
	#if($!BeanMaklumatSyarikat.isEmpty())
  		#if (!$!jenisAkses.equals('Readonly'))
  
         	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanUpdateSyarikat()" />
            <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
    	#end
    #else
      		
		#if ($mode == 'view')
		#if (!$!jenisAkses.equals('Readonly'))
        	<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniSyarikat()" />
        	<input class="stylobutton100" type="button" name="cmdHapusyarikat" value="Hapus" onclick="javascript:hapusSyarikat()" />
		#end
        #elseif ($mode == 'update')
        	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanUpdateSyarikat()" />
            <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
            <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateSyarikat()" />
        #end 
        
	#end
       </td>
      </tr> 
  
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  #if ($mode == 'newPengarah' || $mode == 'updatePengarah' || $mode == 'viewPengarah')
  <tr>
    <td>
    
    <fieldset><legend><strong>MAKLUMAT PENGARAH</strong></legend>
    
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
      #foreach ($beanMaklumatPengarah in $BeanMaklumatPengarah)
      <tr>
        <td width="1%">#if ($mode == 'updatePengarah' || $mode == 'newPengarah')<font color="#FF0000">*</font>#end</td>
        <td width="29%">Nama Pengarah</td>
        <td width="70%">: 
          <input type="text" name="txtNamaPengarah" size="40" maxlength="39" id="txtNamaPengarah" $readonly class="$inputTextClass" value="$beanMaklumatPengarah.namaPengarah" onBlur="this.value=this.value.toUpperCase();">
        </td>
      </tr>
      <tr>
        <td>#if ($mode == 'updatePengarah' || $mode == 'newPengarah')<font color="#FF0000"></font>#end</td>
        <td>No. Kad Pengenalan/ MyID</td>
        <td>: 
          <input type="text" name="txtNoPengenalanPengarah" id="txtNoPengenalanPengarah" $readonly class="$inputTextClass" value="$beanMaklumatPengarah.noPengenalan" onBlur="this.value=this.value.toUpperCase();">
      	</td>
      </tr>
      #end
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>

    </table>

    </fieldset>
    
    </td>
  </tr>  
        <tr>

        <td align="center">
        #if ($mode == 'newPengarah')
        	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanPengarah()" />
            <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
            <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalPengarah()" />
        #elseif ($mode == 'viewPengarah')
			#if (!$!jenisAkses.equals('Readonly'))
            <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniPengarah()" />
            <input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:hapusPengarah()" />
           	#end
            <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalPengarah()" />            
        #elseif ($mode == 'updatePengarah')
            <input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanUpdatePengarah()" />
            <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
            <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdatePengarah()" />
        #end
        </td>
      </tr>
  
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  #if ($mode != 'update')
  <tr>
    <td>
    
    <fieldset>
    <legend><strong>SENARAI PENGARAH</strong></legend>
    
    	<table align="center" width="100%"> 
            #if ($mode == 'view')
				#if (!$!jenisAkses.equals('Readonly'))
            <tr>
              <td colspan="7" scope="row">
              	<input class="stylobutton100" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruPengarah()"/></td>
            </tr>
            	#end
            #end
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil.</strong></td>
              <td width="75%"><strong>Nama Pengarah</strong></td>
              <td width="20%" ><strong>No. Kad Pengenalan/ MyID</strong></td>
             <input type="hidden" name="txtbilpengarah" value="$!SenaraiPengarah.size()" >
              
        	</tr>
          #set ($list = "")
          #if ($SenaraiPengarah.size() > 0)
          #foreach ($list in $SenaraiPengarah)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
      	<tr>
            <td class="$row" align="center"><a href="javascript:paparPengarah('$list.idPengarah')">$list.bil.</a></td>
            <td class="$row"><a href="javascript:paparPengarah('$list.idPengarah')" class="style1">$list.namaPengarah</a></td>
            <td class="$row" >$list.noPengenalan</td>
      	</tr>
          #end
          #else
          <tr>
            <td class="row1">&nbsp;</td>
            <td class="row1">Tiada Rekod</td>
            <td class="row1">&nbsp;</td>
      </tr>
          #end
        </table>
        
    </fieldset>
    
    </td>
  </tr>
  #end
  #else
  <tr>
    <td>
    	&nbsp;<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
    </td>
  </tr>
  #end
</table>

<script>
function doChangeNegeri(){
	doAjaxCall${formName}('doChangeNegeri');
}

function KemaskiniSyarikat(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batalUpdateSyarikat(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanUpdateSyarikat(){
	if(document.${formName}.txtNamaSyarikat.value=="0" || document.${formName}.txtNamaSyarikat.value==""){
		alert('Sila Masukkan Maklumat Nama Syarikat.');
  		document.${formName}.txtNamaSyarikat.focus(); 
		return; 	
	}if(document.${formName}.txtNoPendaftaran.value=="0" || document.${formName}.txtNoPendaftaran.value==""){
		alert('Sila Masukkan Maklumat No. Pendaftaran Syarikat.');
  		document.${formName}.txtNoPendaftaran.focus(); 
		return; 	
	}if(document.${formName}.txtAlamat1.value=="0" || document.${formName}.txtAlamat1.value==""){
		alert('Sila Masukkan Maklumat Alamat.');
  		document.${formName}.txtAlamat1.focus(); 
		return; 	
	}if(document.${formName}.txtAlamat2.value=="0" || document.${formName}.txtAlamat2.value==""){
		alert('Sila Masukkan Maklumat Alamat .');
  		document.${formName}.txtAlamat2.focus(); 
		return; 	
	}if(document.${formName}.txtPoskod.value=="0" || document.${formName}.txtPoskod.value==""){
		alert('Sila Masukkan Maklumat Poskod.');
  		document.${formName}.txtPoskod.focus(); 
		return; 	
	}
	
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih Bandar.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "simpanUpdateSyarikat";
	doAjaxCall${formName}("");
}

//MAKLUMAT PENGARAH
function daftarBaruPengarah(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "newPengarah";
	doAjaxCall${formName}("");
}
function simpanPengarah(){
	if(document.${formName}.txtNamaPengarah.value=="0" || document.${formName}.txtNamaPengarah.value==""){
		alert('Sila Masukkan Maklumat Nama Pengarah.');
  		document.${formName}.txtNamaPengarah.focus(); 
		return; 	
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "savePengarah";
	doAjaxCall${formName}("");
}
function batalPengarah(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	doAjaxCall${formName}("");
}
function kemaskiniPengarah(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "updatePengarah";
	doAjaxCall${formName}("");
}
function simpanUpdatePengarah(){

	if(document.${formName}.txtNamaPengarah.value=="0" || document.${formName}.txtNamaPengarah.value==""){
		alert('Sila Masukkan Maklumat Nama Pengarah.');
  		document.${formName}.txtNamaPengarah.focus(); 
		return; 	
	}
	
	document.${formName}.mode.value = "viewPengarah";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "saveUpdatePengarah";
	doAjaxCall${formName}("");
}
function batalUpdatePengarah(){
	document.${formName}.mode.value = "viewPengarah";
	document.${formName}.actionPenswastaan.value = "papar";
	doAjaxCall${formName}("");
}
function paparPengarah(idPengarah){
	document.${formName}.idPengarah.value = idPengarah;
	document.${formName}.mode.value = "viewPengarah";
	document.${formName}.actionPenswastaan.value = "papar";
	doAjaxCall${formName}("");
}
function hapusPengarah(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "hapusPengarah";
	doAjaxCall${formName}("");
}

function hapusSyarikat(){
	if(document.${formName}.txtbilpengarah.value != 0){
		alert('Sila pastikan senarai pengarah dihapus terlebih dahulu');
		return;
	
	}
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "hapusyarikat";
	doAjaxCall${formName}("");
}
</script>

