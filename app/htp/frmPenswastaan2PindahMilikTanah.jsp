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
  <input name="idHakmilikurusan" type="hidden" id="idHakmilikurusan" value="$idHakmilikurusan"/>
  <input name="idPihakKepentingan" type="hidden" id="idPihakKepentingan" value="$idPihakKepentingan" />
</p>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
#if ($idFail != '')

	<tr>
    	<td>
    		#parse("app/htp/frmPenswastaan2Header.jsp")
    	</td>
  	</tr>  
 	
	<tr>
    	<td>
     		<fieldset><legend><strong>SENARAI TANAH PINDAH MILIK</strong></legend>
  
    			<table align="center" width="100%"> 
		            #if ($mode == 'view')
		            #end
		            <tr class="table_header">
		              <td scope="row" width="3%" align="center"><strong>Bil.</strong></td>
		              <td width="17%"><strong>Negeri</strong></td>
		              <td width="20%" align="center"><strong>Daerah</strong></td>
		              <td width="20%" align="center"><strong>Bandar/Pekan/Mukim</strong></td>
		              <!--<td width="16%" align="center"><strong>Jenis Hakmilik</strong></td>-->
		              <td width="20%" ><strong>No. Hakmilik</strong></td>
		              <td width="20%" ><strong>No. Warta</strong></td>
		        	</tr>

		        #set ($list = "")
		        #if ($SenaraiTPMilik.size() > 0)
		        	#foreach ($listTPMilik in $SenaraiTPMilik)
		            	#if ($listTPMilik.bil == '')
		                	#set( $row = "row1" )
		            	#elseif (($listTPMilik.bil % 2) != 0)
		                	#set( $row = "row1" )
		            	#else 
		                	#set( $row = "row2" )
		            	#end
			      	<tr>
			            <td class="$row" align="center">$listTPMilik.bil.</td>
			            <td class="$row"><a href="javascript:daftarBaruPemilik('$listTPMilik.idHakmilikurusan')" class="style1">$listTPMilik.negeri</a></td>
			            <td class="$row" align="center">$listTPMilik.daerah</td>
			            <td class="$row" align="center">$listTPMilik.mukim</td>
			            <!-- <td class="$row" align="center">$listTPMilik.jenisHakmilik</td> -->
			            <td class="$row" >$!listTPMilik.kodJenisHakmilik $listTPMilik.noHakmilik</td>
			          	<td class="$row" >$listTPMilik.noWarta</td>
			      	</tr>
          			#end
          		#else
		          	<tr>
		            	<td colspan="6" class="row1">Tiada Rekod</td>
		        	</tr>
		      	#end
        		</table>
        
			</fieldset>
		</td>
	</tr>
	
   #if ($mode == 'newPemilik' || $mode == 'updatePemilik' || $mode == 'viewPemilik')
  	<tr>
    	<td>
    		
    		<fieldset><legend><strong>MAKLUMAT PINDAH MILIK TANAH</strong></legend>
        		<table width="100%" border="0" cellspacing="2" cellpadding="2">          
		          <!-- <tr>
		          	<td>&nbsp;</td>
		           	<td>&nbsp;</td>
		           	<td>&nbsp;</td>
		           	<td>&nbsp;</td>
		          </tr> -->

				#foreach ($beanPemilikTanah in $BeanPemilik)
	          		<tr>
		                <td>&nbsp;</td>
		                <td>No Hakmilik / Warta</td>
		                <td>:</td>
		                <td>$selectNohakmilik</td>
	              	</tr>
		            <tr>
		                <td width="1%">&nbsp;</td>
		                <td width="28%">No. Perserahan</td>
		                <td width="1%">:</td>
		                <td width="70%"><input type="text" name="txtNoPerserahan" id="txtNoPerserahan" size="20" value="$beanPemilikTanah.noPerserahan" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly/></td>
		           	</tr>
              		<tr>
		                <td>&nbsp;</td>
		                <td>Tarikh Daftar</td>
		                <td>:</td>
		                <td><input type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" size="10" value="$beanPemilikTanah.tarikhDaftar" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);checkDate();" class="$classDis" $readOnly/>
		                  <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhDaftar',false,'dmy');" />
		              	</td>
	              	</tr>
	              <tr>
	                <td width="1%">&nbsp;<font color="#FF0000">*</font></td>
	                <td width="28%">Nama Penerima</td>
	                <td width="1%">:</td>
	                <td width="70%"><input type="text" name="txtNama" id="txtNama" size="50" value="$beanPemilikTanah.nama" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly/></td>
	              </tr>
	              <tr>
	                <td valign="top">&nbsp;<font color="#FF0000">*</font></td>
	                <td valign="top">Alamat</td>
	                <td valign="top">:</td>
	                <td valign="top"><input type="text" name="txtAlamat1" id="txtAlamat1" size="50" value="$beanPemilikTanah.alamat1" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly/></td>
	              </tr>            
	              <tr>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td><input type="text" name="txtAlamat2" id="txtAlamat2" size="50" value="$beanPemilikTanah.alamat2" onBlur="this.value=this.value.toUpperCase();"  class="$classDis" $readOnly/></td>
	              </tr>
	              <tr>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td><input type="text" name="txtAlamat3" id="txtAlamat3" size="50" value="$beanPemilikTanah.alamat3" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly/></td>
	              </tr>
	              <tr>
	                <td>&nbsp;<font color="#FF0000">*</font></td>
	                <td>Poskod</td>
	                <td>:</td>
	                <td><input name="txtPoskod" type="text" class="$classDis" id="txtPoskod" value="$beanPemilikTanah.poskod" size="5" maxlength="5" $readOnly onBlur="validatePoskod(this,this.value);"/></td>
	              </tr>
	              <tr>
	                <td>&nbsp;<font color="#FF0000">*</font></td>
	                <td>Negeri</td>
	                <td>:</td>
	                <td>$selectNegeri</td>
	              </tr>
	              <tr>
	                <td>&nbsp;<font color="#FF0000">*</font></td>
	                <td>Bandar</td>
	                <td>:</td>
	                <td>$selectDaerah</td>
	              </tr>
	              <tr>
	                <td>&nbsp;</td>
	                <td>No. Tel</td>
	                <td>:</td>
	                <td><input type="text" name="txtNoTelefon" id="txtNoTelefon" size="14" value="$beanPemilikTanah.tel"  class="$classDis" $readOnly/></td>
	              </tr>
	              <tr>
	                <td>&nbsp;</td>
	                <td>No. Fax</td>
	                <td>:</td>
	                <td><input type="text" name="txtFax" id="txtFax" size="14" value="$beanPemilikTanah.fax" class="$classDis" $readOnly/></td>
	              </tr>
	              <tr>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	              </tr>
	              <tr>
	              	<td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td colspan="2">
	                #if ($mode == 'newPemilik')
	                	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanPemilik()" />
	                    <input type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
	                    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalPemilik()" />
	                #elseif ($mode == 'viewPemilik')
	                    <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniPemilik()" />
	                    <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:hapusPemilik()" />
	                    <input type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalPemilik()" />
	                    
	                #elseif ($mode == 'updatePemilik')
	                    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdatePemilik()" />
	                    <input type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
	                    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdatePemilik()" />
	                #end
	                
	                	</td>
	            	</tr>
              	#end
            	</table>
        	</fieldset>
        
    	</td>
  	</tr>
  	<!-- <tr>
    	<td>&nbsp;</td>
  	</tr> -->
  	#end
	
	
	<tr>
    	<td>       		
    		<fieldset><legend><strong>SENARAI PEMILIK</strong></legend> 
    			<table align="center" width="100%"> 

		            <tr class="table_header">
		              <td scope="row" width="3%" align="center"><strong>Bil.</strong></td>
		              <td width="20%"><strong>No. Hakmilik / Warta</strong></td>
		              <td width="77%" ><strong>Nama</strong></td>
		              <!--<td width="21%" align="center"><strong>Jenis Hakmilik</strong></td> -->
		          	</tr>
		        #set ($list = "")
		      	#if ($SenaraiPemilik.size() > 0)
			          	
			    	#foreach ($list in $SenaraiPemilik)
			            #if ($list.bil == '')
			                #set( $row = "row1" )
			            #elseif (($list.bil % 2) != 0)
			                #set( $row = "row1" )
			            #else 
			                #set( $row = "row2" )
			            #end
	      			<tr>
				    	<td class="$row" align="center">
				        	<a href="javascript:paparPemilik('$list.idPihakKepentingan')">$list.bil.</a>
				      	</td>
	            		<td class="$row">
	            				<a href="javascript:paparPemilik('$list.idPihakKepentingan')" class="style1">          
					            #if($list.noHakmilik != "")           
					            	$!list.kodJenisHakmilik $list.noHakmilik
					             
					            #else            	
					                $list.noWarta
					                
					            #end
				            	</a>
	            
	            		</td>
	            		<td class="$row" >$list.nama</td>
	            		<!-- <td class="$row" align="center">$list.jenisHakmilik</td> -->
	          		</tr>
	          		
	          		#end
	          		
          		#else
		        	<tr>
		            	<td colspan="3" class="row1">Tiada Rekod</td>
		          	</tr>
          		#end
        		</table>       
    		</fieldset>  
    
    	</td>
  	</tr>	
	
  
#else

	<tr>
    	<td>
    		<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
    	</td>
	</tr>
  	
#end
</table>


<script language="javascript" >

function daftarBaruPemilik(idHakmilikurusan){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "newPemilik";
	document.${formName}.idHakmilikurusan.value = idHakmilikurusan;
	doAjaxCall${formName}("");
}

function doChangeNegeri(){
	doAjaxCall${formName}('doChangeNegeri');
}

function batalPemilik(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	doAjaxCall${formName}("");
}

function SimpanPemilik(){
	
	if(document.${formName}.txtNama.value=="0" || document.${formName}.txtNama.value==""){
		alert('Sila Masukkan Maklumat Nama.');
  		document.${formName}.txtNama.focus(); 
		return; 	
	}
	if(document.${formName}.txtAlamat1.value=="0" || document.${formName}.txtAlamat1.value==""){
		alert('Sila Masukkan Maklumat Alamat.');
  		document.${formName}.txtAlamat1.focus(); 
		return; 	
	}
	if(document.${formName}.txtAlamat2.value=="0" || document.${formName}.txtAlamat2.value==""){
		alert('Sila Masukkan Maklumat Alamat .');
  		document.${formName}.txtAlamat2.focus(); 
		return; 	
	}
	if(document.${formName}.txtPoskod.value=="0" || document.${formName}.txtPoskod.value==""){
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

	document.${formName}.mode.value = "newPemilik";
	document.${formName}.hitButton.value = "savePemilik";
	document.${formName}.actionPenswastaan.value = "papar";
	doAjaxCall${formName}("");
}

function paparPemilik(idPihakKepentingan){
	document.${formName}.idPihakKepentingan.value = idPihakKepentingan;
	document.${formName}.mode.value = "viewPemilik";
	document.${formName}.actionPenswastaan.value = "papar";
	doAjaxCall${formName}("");
}

function hapusPemilik(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "hapusPemilik";
	doAjaxCall${formName}("");
}

function kemaskiniPemilik(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "updatePemilik";
	doAjaxCall${formName}("");
}

function SimpanUpdatePemilik(){
	
	if(document.${formName}.txtNama.value=="0" || document.${formName}.txtNama.value==""){
		alert('Sila Masukkan Maklumat Nama.');
  		document.${formName}.txtNama.focus(); 
		return; 	
	}
	if(document.${formName}.txtAlamat1.value=="0" || document.${formName}.txtAlamat1.value==""){
		alert('Sila Masukkan Maklumat Alamat.');
  		document.${formName}.txtAlamat1.focus(); 
		return; 	
	}
	if(document.${formName}.txtAlamat2.value=="0" || document.${formName}.txtAlamat2.value==""){
		alert('Sila Masukkan Maklumat Alamat .');
  		document.${formName}.txtAlamat2.focus(); 
		return; 	
	}
	if(document.${formName}.txtPoskod.value=="0" || document.${formName}.txtPoskod.value==""){
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
	document.${formName}.mode.value = "viewPemilik";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "saveUpdatePemilik";
	doAjaxCall${formName}("");
	
}



function checkDate() {
	var today = new Date();	
	dari_bulan = document.${formName}.txdTarikhDaftar.value.substring(3,5);
	dari_hari = document.${formName}.txdTarikhDaftar.value.substring(0,2);
	dari_tahun = document.${formName}.txdTarikhDaftar.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var myDate = Date.parse(daritemp);
	
	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		//document.${formName}.txdTarikhDaftar.value= ""; 
  		document.${formName}.txdTarikhDaftar.focus(); 
 		return;
 	}

}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
    if (isNaN(content)) {
    	elmnt.value = RemoveNonNumeric(content);
        return;
   	}
}

</script>
