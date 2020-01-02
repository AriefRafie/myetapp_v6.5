<style type="text/css">
<!--
.style1 {color: #FF0000}
.qw {
	color: #F00;
}
-->
</style>


<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="actionPerletakhakan" type="hidden" value="$actionPerletakhakan" /> 

<input name="mode" type="hidden" value="$mode" id="mode"/>

<input name="hitButton" type="hidden" id="hitButton"/> 
<input name="idFail" type="hidden" value="$idFail" id="idFail" /> 

<input name="idPermohonan" type="hidden" value="$idPermohonan" id="idPermohonan" /> 
<input name="idHakmilikurusan" type="hidden" value="$idHakmilikurusan" id="idHakmilikurusan" /> 


<table width="100%">
	<tr>
		<td>
			#parse("app/htp/perletakhakan/frmPerletakhakanInfoOnline.jsp") 
		</td>
	</tr>
	
	<tr>
		<td>
			<fieldset><legend>SENARAI HAKMILIK</legend>
				#if ($mode == "")
				<input type="button" class="stylobutton100" name="cmdTambahHakmilik" value="Tambah" onclick="tambahHakmilik()"/>
				#end
				
				<table width="100%" border="0">
				  <tr class="table_header">
				  	<td width="5%">Bil</td>
				    <td width="20%">Negeri</td>
				    <td width="20%">Daerah</td>
				    <td width="30%">Mukim</td>
				    <td width="15%">No. Hakmilik</td>
				    <td width="10%">No Lot/PT</td>
				    
				  </tr>
				  #if ($Listhakmilik.size() > 0)
				      #foreach ($list in $Listhakmilik)
				       #set( $i = $velocityCount )
				       #if ( ($i % 2) != 1 ) 
				       #set( $row = "row2" ) 
				       #else 
				       #set( $row = "row1" ) 
				       #end
				  <tr class="$row">
				  	<td  width="1%">
				    		<a href="javascript:paparHakmilik('$list.idHakmilikurusan')">$list.bil.</a>
				    	</td>
				        <td>
				        	<a href="javascript:paparHakmilik('$list.idHakmilikurusan')"><font color="blue">$list.negeri</font></a>
				        </td>
				        <td>$list.daerah</td>
				        <td>$list.mukim</td>
				        <td>$list.noHakmilik</td>
				        <td><a href="javascript:paparHakmilik('$list.idHakmilikurusan')">$list.noLot</a></td>
				      </tr>
				      #end
				   #else
				   
				  <tr class="row1">
				  	<td colspan="4"><font color="#FF0000">Tiada Rekod.</font></td>
				  </tr>
				  
				   #end
				</table>
				</fieldset>
			
		</td>
	</tr>
		
	#if($mode == "newHakmilik" || $mode == "updateHakmilik" || $mode == "viewHakmilik" || $mode == "doChangeState")
	<tr>
		<td>
			<fieldset><legend>HAKMILIK</legend>
				
				<table width="100%" border="0" cellpadding="2">
				  <tr>
				    <td width="50%" valign="top">
				    
				    <table width="100%" border="0" cellpadding="2">
				      <tr>
				        <td width="1%"><span class="style1">*</span></td>
				        <td width="29%">Negeri</td>
				        <td width="70%">: $selectNegeri</td>
				      </tr>
				      <tr>
				        <td width="1%"><span class="style1">*</span></td>
				        <td>Daerah</td>
				        <td>: $selectDaerah</td>
				      </tr>
				      <tr>
				        <td width="1%"><span class="style1">*</span></td>
				        <td>Bandar/Pekan/Mukim</td>
				        <td>: $selectMukim</td>
				      </tr>
				      <tr>
				        <td width="1%"><span class="style1">*</span></td>
				        <td>Lokasi</td>
				        <td>: <input name="txtLokasi" type="text" id="txtLokasi"  style="text-transform:uppercase;" value="$txtLokasi"  $readonly class="$inputTextClass"/></td>
				      </tr>
				      <tr>
				        <td width="1%"><span class="style1">*</span></td>
				        <td>Jenis HM</td>
				        <td>: 
				          $selectJenisHakmilik</td>
				      </tr>
				      <tr>
				        <td width="1%"><span class="style1">*</span></td>
				        <td>No Hakmilik</td>
				        <td>: <input name="txtNoHakmilik" type="text" id="txtNoHakmilik" value="$txtNoHakmilik"  $readonly class="$inputTextClass" style="text-transform:uppercase;"/></td>
				      </tr>
				      <tr>
				        <td width="1%"><span class="style1">*</span></td>
				        <td>Kod Lot</td>
				        <td>: 
				          $selectLot</td>
				      </tr>
				      <tr>
				        <td><span class="style1">*</span></td>
				        <td>No Lot</td>
				        <td>: <input name="txtLot" type="text" id="txtLot" value="$txtLot"  $readonly class="$inputTextClass" style="text-transform:uppercase;"/></td>
				      </tr>
				      				         
				      <tr>
				        <td width="1%"><span class="style1">*</span></td>
				        <td>Kod Luas</td>
				        <td>: 
				          ##$selectLuas
			            	<select $style $readonly class="$inputTextClass" name="socLuas" style="width:200px;" onchange="doChangeKodLuas('$!mode')">
									     #set ($listUnitLuas = ["SILA PILIH","KM - KILOMETER PERSEGI","H - HEKTAR","M - METER PERSEGI","E - EKAR,ROOD,POLE","K - KAKI PERSEGI","P - EKAR PERPULUHAN","D - EKAR,DEPA","R - RELONG,JEMBA,KAKI PERSEGI","BN - BATU NAUTIKA"])
									    #set( $counter = 0 )
									    #foreach ($i in $listUnitLuas)
									    #if ($socLuas == $counter) 
									        <option selected value="$counter">$i</option>
									    #else
									        <option value="$counter">$i</option>
									    #end
									    #set ($counter = $counter+1)
									    #end
							</select>				          
				          </td>
				      </tr>
				      <tr>
				        <td>&nbsp;</td>
				        <td>Luas</td>
				        <td>: 
				       		<!-- <input type="text" name="txtLuas" id="txtLuas" $readonly class="$inputTextClass" value="$txtLuas" style="text-transform:uppercase;" onBlur="kiraLuas('$socLuas')" onkeyup="validateNumber(this,this.value);"/> -->
				  		#if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6')
							<input $style $readonly class="$inputTextClass" value="$!txtLuas1"  name="txtLuas1" type="text" id="txtLuas1" size="8"  
								onBlur="kiraLuas('$socLuas')" onkeyup="validateNumber(this,this.value);"/>
						#elseif($socLuas == '8' || $socLuas == '4')
							<input $style type="text" name="txtLuas2" id="txtLuas2" size="4" />
							<input $style type="text" name="txtLuas3" id="txtLuas3" size="4" />
							<input type="text" name="txtLuas4" id="txtLuas4" size="4" />      
						#elseif($socLuas == '7')
							<input $style name="txtLuas5" type="text" id="txtLuas5" size="8" />
							<input $style name="txtLuas6" type="text" id="txtLuas6" size="8" />
						#else
							<input $style $readonly class="$inputTextClass" value="$!txtLuas1" name="txtLuas1" type="text" 
								onBlur="kiraLuas('$socLuas')" onkeyup="validateNumber(this,this.value);" size="8" maxlength="8" />
						#end
				         </td>
				      </tr>
				      <tr>
				        <td>&nbsp;</td>
				        <td>Luas Bersamaan</td>
				        <td>: 
				          <!--<input name="txtLuasBersamaan" type="text" id="txtLuasBersamaan" $readonly class="$inputTextClass" value="$!txtLuasBersamaan" style="text-transform:uppercase;"/>(Hektar)-->
				          <input name="Luas" type="text" $readonly class="$inputTextClass" value="$!txtLuasBersamaan" style="text-transform:uppercase;"/>(Hektar)
				        </td>
				      </tr>
				    </table>
				    
				    </td>
				    <td width="50%" valign="top">
				    
				    <table width="100%" border="0" cellpadding="2">
				 <!-- <tr>
				    <td width="1%">&nbsp;</td>
				    <td width="28%">No Strata </td>
				    <td width="1%">:</td>
				    <td width="70%">
				    	<input name="txtNoStrata" type="text" id="txtNoStrata"  readonly="readonly" class="disabled" value="$txtNoStrata" style="text-transform:uppercase;"/>
				    </td>
				  </tr> -->
				  <tr>
				    <td><span class="style1">*</span></td>
				    <td>Kategori</td>
				    <td>:</td>
				    <td>$selectKategori</td>
				  </tr>
				  <tr>
				    <td width="1%"><span class="style1">*</span></td>
				    <td>Cukai Semasa (RM)</td>
				    <td>:</td>
				    <td><input name="txtCukaiSemasa" type="text" id="txtCukaiSemasa" value="$txtCukaiSemasa" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value);" onblur="formatCurrencySemasa(this.value)" /></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>No Pelan Akui</td>
				    <td>:</td>
				    <td><input name="txtNoPelan" type="text" id="txtNoPelan" style="text-transform:uppercase;" value="$txtNoPelan" $readonly class="$inputTextClass" /></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>No Syit</td>
				    <td>:</td>
				    <td><input name="txtNoSyit" type="text" id="txtNoSyit" value="$txtNoSyit" $readonly class="$inputTextClass" style="text-transform:uppercase;"/></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td valign="top">Syarat Nyata </td>
				    <td valign="top">:</td>
				    <td valign="top"><textarea name="txtSyaratNyata" id="txtSyaratNyata" cols="50" rows="5" $readonly class="$inputTextClass" style="text-transform:uppercase;">$txtSyaratNyata</textarea></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td valign="top">Sekatan Kepentingan </td>
				    <td valign="top">:</td>
				    <td valign="top"><textarea name="txtSekatan" id="txtSekatan" cols="50" rows="5" $readonly class="$inputTextClass" style="text-transform:uppercase;">$txtSekatan</textarea></td>
				  </tr>
				</table>
				
				    
				    </td>
				  </tr>
				</table>
				
				
				<p>Perhatian : <em>Pastikan label bertanda <span class="style1">*</span> diisi.</em></p>
				</fieldset>
			
		</td>
	</tr>
	#end
	
	<tr>
		<td align="center">
		#if ($mode == 'newHakmilik' || $mode == "doChangeState" )
      		<input class="stylobutton100" type="button" name="btnAdd2" id="btnAdd2" value="Simpan" onClick="simpanHakmilik()" /> 
      		<!--<input class="stylobutton" name="cmdBatal" type="button" value="Batal" onClick="batal()"/> -->
      		<input class="stylobutton100" name="cmdBatal" type="button" value="Batal" onClick="maklumatHakmilikBatal('$!idFail')"/>
      		
	    #end
	    #if ($mode == 'viewHakmilik')
	    	<input class="stylobutton100" type="button" name="btnUpdate2" id="btnUpdate2" value="Kemaskini" onclick="kemaskiniHakmilik()" />
	    	<input class="stylobutton100" id="cmdHapus" name="cmdHapus" type="button" value="Hapus" onClick="hapusHakmilik()"/>	
	    	<input class="stylobutton100" name="cmdBatal" type="button" value="Kembali" onClick="kembali()"/> 
	    	<input class="stylobutton100" name="cmdSeterusnya" type="button" value="Maklumat Pemilik" onClick="pemilik()" style="display:none"/> 
	    #end 
	    #if ($mode == 'updateHakmilik')
	    	<input class="stylobutton100" id="cmdUpdate" name="cmdUpdate" type="button" value="Simpan" onclick="simpanUpdateHakmilik()"/>
	    	<input class="stylobutton100" id="cmdBatal" name="cmdBatal" type="button" value="Batal" onclick="batalUpdateHakmilik()"/>
	    #end  
	    #if ($mode == "")
	   		<!-- <input class="stylobutton" type="button" name="btnExit2" id="btnExit2" value="Kembali" onClick="kembali2()" /> -->
	   		<input class="stylobutton100" type="button" name="btnExit2" id="btnExit2" value="Kembali" onClick="paparSkrin2('$!idFail','$!idPermohonan')" /> 
	   	#end
		</td>
	</tr>
</table>


<script>
function doChangeState(){
	document.${formName}.submit();
}
function doChangeDaerah(){
	document.${formName}.submit();
}

function kembali() {	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=";
	document.${formName}.submit();
}
//function tambahHakmilik(){
//	alert("w3");
//	document.${formName}.actionPerletakhakan.value = "papar";
//	document.${formName}.mode.value = "newHakmilik";
//	document.${formName}.submit();
//}
function batal(){
	document.${formName}.actionPerletakhakan.value = "papar";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function batalUpdateHakmilik(){
	document.${formName}.actionPerletakhakan.value = "papar";
	document.${formName}.mode.value = "viewHakmilik";
	document.${formName}.submit();
}
function simpanHakmilik(){
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeri.focus();
		return;
	}
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih "Daerah" terlebih dahulu.');
		document.${formName}.socDaerah.focus();
		return;
	}
	if(document.${formName}.socMukim.value == ""){
		alert('Sila pilih "Bandar/Pekan/Mukim" terlebih dahulu.');
		document.${formName}.socMukim.focus();
		return;
	}
	if(document.${formName}.socJenisHakmilik.value == ""){
		alert('Sila pilih "Jenis Hakmilik" terlebih dahulu.');
		document.${formName}.socJenisHakmilik.focus();
		return;
	}
	if(document.${formName}.txtNoHakmilik.value == ""){
		alert('Sila pilih "No Hakmilik" terlebih dahulu.');
		document.${formName}.txtNoHakmilik.focus();
		return;
	}
	if(document.${formName}.socLuas.value == ""){
		alert('Sila pilih "Luas" terlebih dahulu.');
		document.${formName}.socLuas.focus();
		return;
	}
	if(document.${formName}.txtLokasi.value == ""){
		alert('Sila pilih "Lokasi" terlebih dahulu.');
		document.${formName}.txtLokasi.focus();
		return;
	}
	if(document.${formName}.socLot.value == ""){
		alert('Sila pilih "Lot" terlebih dahulu.');
		document.${formName}.socLot.focus();
		return;
	}
	if(document.${formName}.socKategori.value == ""){
		alert('Sila pilih "Kategori" terlebih dahulu.');
		document.${formName}.socKategori.focus();
		return;
	}
	if(document.${formName}.txtCukaiSemasa.value == ""){
		alert('Sila pilih "Cukai Semasa" terlebih dahulu.');
		document.${formName}.txtCukaiSemasa.focus();
		return;
	}
	
	document.${formName}.mode.value ="viewHakmilik";
	document.${formName}.hitButton.value ="simpanHakmilik";
	document.${formName}.submit();   
} 
function paparHakmilik(id){	
document.${formName}.actionPerletakhakan.value = "maklumathakmilik";
	document.${formName}.mode.value ="viewHakmilik";
	document.${formName}.idHakmilikurusan.value = id;
	document.${formName}.submit();
}

function kemaskiniHakmilik(){
	document.${formName}.mode.value ="updateHakmilik";
	document.${formName}.submit();
}

	//25/10/2010
	function hapusHakmilik(){
		document.${formName}.mode.value ="hapusHakmilik";
		document.${formName}.submit();
	}

function simpanUpdateHakmilik(){
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeri.focus();
		return;
	}
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih "Daerah" terlebih dahulu.');
		document.${formName}.socDaerah.focus();
		return;
	}
	if(document.${formName}.socMukim.value == ""){
		alert('Sila pilih "Bandar/Pekan/Mukim" terlebih dahulu.');
		document.${formName}.socMukim.focus();
		return;
	}
	if(document.${formName}.socJenisHakmilik.value == ""){
		alert('Sila pilih "Jenis Hakmilik" terlebih dahulu.');
		document.${formName}.socJenisHakmilik.focus();
		return;
	}
	if(document.${formName}.txtNoHakmilik.value == ""){
		alert('Sila pilih "No Hakmilik" terlebih dahulu.');
		document.${formName}.txtNoHakmilik.focus();
		return;
	}
	if(document.${formName}.socLuas.value == ""){
		alert('Sila pilih "Luas" terlebih dahulu.');
		document.${formName}.socLuas.focus();
		return;
	}
	if(document.${formName}.txtLokasi.value == ""){
		alert('Sila pilih "Lokasi" terlebih dahulu.');
		document.${formName}.txtLokasi.focus();
		return;
	}
	if(document.${formName}.socLot.value == ""){
		alert('Sila pilih "Lot" terlebih dahulu.');
		document.${formName}.socLot.focus();
		return;
	}
	if(document.${formName}.socKategori.value == ""){
		alert('Sila pilih "Kategori" terlebih dahulu.');
		document.${formName}.socKategori.focus();
		return;
	}	
	if(document.${formName}.txtCukaiSemasa.value == ""){
		alert('Sila pilih "Cukai Semasa" terlebih dahulu.');
		document.${formName}.txtCukaiSemasa.focus();
		return;
	}
	
	document.${formName}.mode.value ="viewHakmilik";
	document.${formName}.hitButton.value ="simpanUpdateHakmilik";
	document.${formName}.submit();   
} 
function formatCurrencySemasa(num) { 
	num = num.toString().replace(/\$|\,/g,''); 
	if(isNaN(num)) num = "0"; 
	sign = (num == (num = Math.abs(num)));
 	num = Math.floor(num*100+0.50000000001); 
 	cents = num%100; num = Math.floor(num/100).toString();
  	if(cents<10) cents = "0" + cents; 
 	 for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	  num = num.substring(0,num.length-(4*i+3))+','+ num.substring(num.length-(4*i+3)); 
    document.${formName}.txtCukaiSemasa.value = num + '.' + cents; 
}
function pemilik(){
	document.${formName}.actionPerletakhakan.value ="paparPemilik";
	document.${formName}.mode.value ="";
	document.${formName}.submit();
}


</script>
