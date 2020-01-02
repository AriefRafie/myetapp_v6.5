<br/>

<form name="f1" method="post">
<center>

<!-- ADD/NEW MODE -->
#if($viewLantikPenjaga=="no")
	<fieldset>
	<legend><strong>LANTIK PENJAGA</strong></legend>
		
	<table width="100%">
		<tr>
		<td width="47%" valign="top">
			<table width="100%" cellpadding="1" cellspacing="2" border="0">
				<tr>
					<td>No.KP Baru</td>
					<td>:&nbsp;<input type="text" size="6px" name="txtNoKPBaru1" id="txtNoKPBaru1" value="" maxlength="6"  onkeyup="javascript:validateIC(this,this.value,'txtNoKPBaru2')"  onBlur="getAgeByIC(this,this.value,'txtUmur')"/>-<input name="txtNoKPBaru2" id="txtNoKPBaru2" type="text" value=""  size="1px" maxlength="2" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaru3')"/>-<input name="txtNoKPBaru3" id="txtNoKPBaru3" onblur="jantinaic()" type="text" value="" size="3px" maxlength="4" /></td>
				</tr>
				<tr>
					<td>No.KP Lama</td>
					<td>:&nbsp;<input type="text" name="txtNoKPLama" value="" size="10px" maxlength="8" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>				
				<tr>
					<td>Lain - lain KP</td>
					<td>:&nbsp;<select name="socJenisKp" id="socJenisKp" style="width:110px">
                    	<option value="0">Sila Pilih&nbsp;</option>
                    	<option value="1">NO.PASPORT</option>
                    	<option value="2">NO.TENTERA</option>
                    	<option value="3">NO.POLIS</option>
                  			   </select>&nbsp;<input type="text" name="txtJenisKP" value="" size="15px" maxlength="12" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td><font color="red">*</font>Nama Penjaga</td>
					<td>:&nbsp;<input type="text" size="37px" name="txtNamaPenjaga" id="txtNamaPenjaga" value="" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>Jantina</td>
					<td>:&nbsp;<select name="socJantina" id="socJantina" style="width:110px">
                    		<option>Sila Pilih&nbsp;</option>
                    		<option value="1">LELAKI</option>
                    		<option value="2">PEREMPUAN</option>
						</select>
                  	</td>
				</tr>
				<tr>
					<td>Agama</td>
					<td>:&nbsp;<select name="socAgama" id="socAgama" style="width:110px">
                    		<option value="0">Sila Pilih&nbsp;</option>
                    		<option value="1">ISLAM</option>
                    		<option value="2">BUKAN ISLAM</option>                    		
						</select>
					</td>
				</tr>
				<tr>
					<td>Warganegara</td>
					<td>:&nbsp;<select name="socWarganegara" id="socWarganegara" style="width:170px">
                    		<option value="0">Sila Pilih&nbsp;</option>
                    		<option value="1">WARGANEGARA</option>
                    		<option value="2">BUKAN WARGANEGARA</option>   
                    		<option value="3">PENDUDUK TETAP</option>                   		
						</select>
					</td>
				</tr>
				<tr>
					<td>Umur</td>
					<td>:&nbsp;<input type="text" id="txtUmur" name="txtUmur" value="" size="2px" onkeyup="javascript:validateIC(this,this.value,'txtUmur') maxlength="3" /></td>
				</tr>
			</table>
		</td>
		
		<td width="53%">
			<table width="100%"  cellpadding="1" cellspacing="2" border="0">
				<tr>
					<td width="19%">Alamat</td>
					<td width="1%">:&nbsp;</td>
					<td width="80%"><input type="text" size="40px" name="txtalamat1" id="txtalamat1" value="" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input type="text" size="40px" name="txtalamat2" id="txtalamat2" value="" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input type="text" size="40px" name="txtalamat3" id="txtalamat3" value="" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>Bandar</td>
					<td>:&nbsp;</td>
					<td><input type="text" name="txtbandar" value="" size="30px" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>Poskod</td>
					<td>:&nbsp;</td>
					<td><input type="text" name="txtposkod" size="4px" onkeyup="validatePoskod(this,this.value);"  value="" maxlength="5" id="poskod"></td>
				</tr>
				<tr>
					<td>Negeri</td>
					<td>:&nbsp;</td>
					<td>$selectNegeri</td>
				</tr>
				<tr>
					<td valign="top">Catatan</td>
					<td valign="top">:&nbsp;</td>
					<td><textarea name="txtcatatan" cols="30%" rows="4" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ></textarea></td>
				</tr>
			</table>
		</td>
		
		</tr>
		</table>
				
	</fieldset>
#end
<!-- END ADD/NEW MODE -->


<!-- VIEW & EDIT MODE -->

#if($viewLantikPenjaga=="yes")

	#if($editLantikPenjaga=="yes")
		#set($disability = "")
	#else
		#set($disability = "disabled")	
	#end

	#foreach($data in $dataPenjaga)
		#set($noKPBaru1=$data.no_kp_baru1)
		#set($noKPBaru2=$data.no_kp_baru2)
		#set($noKPBaru3=$data.no_kp_baru3)
		#set($noKPLama=$data.no_kp_lama)
		#set($noKPLain=$data.no_kp_lain)
		#set($namaPenjaga=$data.nama_penjaga)
		#set($umur=$data.umur)
		#set($alamat1=$data.alamat1)
		#set($alamat2=$data.alamat2)
		#set($alamat3=$data.alamat3)
		#set($bandar=$data.bandar)
		#set($poskod=$data.poskod)
		#set($catatan=$data.catatan)
		#set($warganegara=$data.jenis_warga)
		#set($agama=$data.jenis_agama)
		#set($jantina=$data.jantina)
		#set($jenis_kp=$data.jenis_kp)
	#end

	<fieldset>
	<legend><strong>LANTIK PENJAGA</strong></legend>
		
	<table width="100%">
		<tr>
		<td width="47%" valign="top">
			<table width="100%" cellpadding="1" cellspacing="2" border="0">
				<tr>
					<td>No.KP Baru</td>
					<td>:&nbsp;<input type="text" size="6px" name="EDITtxtNoKPBaru1" $disability id="txtNoKPBaru1" value="$noKPBaru1" maxlength="6"  onkeyup="javascript:validateIC(this,this.value,'txtNoKPBaru2')"  onBlur="getAgeByIC(this,this.value,'txtUmur')"/>-<input name="EDITtxtNoKPBaru2" $disability id="txtNoKPBaru2" type="text" value="$noKPBaru2"  size="1px" maxlength="2" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaru3')"/>-<input name="EDITtxtNoKPBaru3" $disability id="txtNoKPBaru3" onblur="jantinaic()" type="text" value="$noKPBaru3" size="3px" maxlength="4" /></td>
				</tr>
				<tr>
					<td>No.KP Lama</td>
					<td>:&nbsp;<input type="text" $disability name="EDITtxtNoKPLama" value="$noKPLama" size="10px" maxlength="8" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>				
				<tr>
					<td>Lain - lain KP</td>
					<td>:&nbsp;<select name="EDITsocJenisKp" id="socJenisKp" style="width:110px" $disability>
                    	#if($jenis_kp=="0" || $jenis_kp=="")
                    	<option value="0">Sila Pilih&nbsp;</option>
                    	<option value="1">NO.PASPORT</option>
                    	<option value="2">NO.TENTERA</option>
                    	<option value="3">NO.POLIS</option>
                    	#end
                    	#if($jenis_kp=="1")
                    	<option value="1">NO.PASPORT</option>
                    	<option value="2">NO.TENTERA</option>
                    	<option value="3">NO.POLIS</option>
                    	<option value="0">-KOSONGKAN-</option>  
                    	#end
                    	#if($jenis_kp=="2")
                    	<option value="2">NO.TENTERA</option>
                    	<option value="1">NO.PASPORT</option>
                    	<option value="3">NO.POLIS</option>
                    	<option value="0">-KOSONGKAN-</option>  
                    	#end
                    	#if($jenis_kp=="3")
                    	<option value="3">NO.POLIS</option>
                    	<option value="1">NO.PASPORT</option>
                    	<option value="2">NO.TENTERA</option>
                    	<option value="0">-KOSONGKAN-</option>  
                    	#end
                  			   </select>&nbsp;<input type="text" $disability name="EDITtxtJenisKP" value="$noKPLain" size="15px" maxlength="12" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>#if($editLantikPenjaga=="yes")<font color="red">*</font>#end Nama Penjaga</td>
					<td>:&nbsp;<input type="text" size="37px" $disability name="EDITtxtNamaPenjaga" id="txtNamaPenjaga" value="$namaPenjaga" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>Jantina</td>
					<td>:&nbsp;<select name="EDITsocJantina" $disability id="socJantina" style="width:110px">
                    		#if($jantina=="0" || $jantina=="")
                    		<option value="0">Sila Pilih&nbsp;</option>
                    		<option value="1">LELAKI</option>
                    		<option value="2">PEREMPUAN</option>
                    		#end
                    		#if($jantina=="1")
                    		<option value="1">LELAKI</option>
                    		<option value="2">PEREMPUAN</option>
                    		<option value="0">-KOSONGKAN-</option>                   		
                    		#end
                    		#if($jantina=="2")
                    		<option value="2">PEREMPUAN</option>
                    		<option value="1">LELAKI</option>
                    		<option value="0">-KOSONGKAN-</option>                   		
                    		#end
						</select>
                  	</td>
				</tr>
				<tr>
					<td>Agama</td>
					<td>:&nbsp;<select name="EDITsocAgama" $disability id="socAgama" style="width:110px">
                    		#if($agama=="0" || $agama=="")
                    		<option value="0">Sila Pilih&nbsp;</option>
                    		<option value="1">ISLAM</option>
                    		<option value="2">BUKAN ISLAM</option>	
                    		#end
                    		#if($agama=="1")
                    		<option value="1">ISLAM&nbsp;</option>
                    		<option value="2">BUKAN ISLAM</option>	
                    		<option value="0">-KOSONGKAN-</option>
                    		#end
                    		#if($agama=="2")
                    		<option value="2">BUKAN ISLAM</option>	
                    		<option value="1">ISLAM&nbsp;</option>
                    		<option value="0">-KOSONGKAN-</option>
                    		#end
                    		
						</select>
					</td>
				</tr>
				<tr>
					<td>Warganegara</td>
					<td>:&nbsp;<select name="EDITsocWarganegara" $disability id="socWarganegara" style="width:170px">
                    		#if($warganegara=="0" || $warganegara=="")
                    		<option value="0">Sila Pilih&nbsp;</option>
                    		<option value="1">WARGANEGARA</option>
                    		<option value="2">BUKAN WARGANEGARA</option>   
                    		<option value="3">PENDUDUK TETAP</option> 
                    		#end
                    		#if($warganegara=="1")
                    		<option value="1">WARGANEGARA</option>
                    		<option value="2">BUKAN WARGANEGARA</option>   
                    		<option value="3">PENDUDUK TETAP</option> 
                    		<option value="0">-KOSONGKAN-</option>
                    		#end   
                    		#if($warganegara=="2")
                    		<option value="2">BUKAN WARGANEGARA</option> 
                    		<option value="1">WARGANEGARA</option>
                    		<option value="3">PENDUDUK TETAP</option> 
                    		<option value="0">-KOSONGKAN-</option>
                    		#end  
                    		#if($warganegara=="3")
                    		<option value="3">PENDUDUK TETAP</option> 
                    		<option value="1">WARGANEGARA</option>
                    		<option value="2">BUKAN WARGANEGARA</option> 
                    		<option value="0">-KOSONGKAN-</option>
                    		#end              		
						</select>
					</td>
				</tr>
				<tr>
					<td>Umur</td>
					<td>:&nbsp;<input type="text" $disability id="txtUmur" name="EDITtxtUmur" value="$umur" size="2px" onkeyup="javascript:validateIC(this,this.value,'txtUmur') maxlength="3" /></td>
				</tr>
			</table>
		</td>
		
		<td width="53%">
			<table width="100%"  cellpadding="1" cellspacing="2" border="0">
				<tr>
					<td width="19%">Alamat</td>
					<td width="1%">:&nbsp;</td>
					<td width="80%"><input type="text" $disability size="40px" name="EDITtxtalamat1" id="txtalamat1" value="$alamat1" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input type="text" size="40px" $disability name="EDITtxtalamat2" id="txtalamat2" value="$alamat2" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input type="text" size="40px" $disability name="EDITtxtalamat3" id="txtalamat3" value="$alamat3" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>Bandar</td>
					<td>:&nbsp;</td>
					<td><input type="text" $disability name="EDITtxtbandar" value="$bandar" size="30px" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>Poskod</td>
					<td>:&nbsp;</td>
					<td><input type="text" $disability name="EDITtxtposkod" size="4px" onkeyup="validatePoskod(this,this.value);"  value="$poskod" maxlength="5" id="poskod"></td>
				</tr>
				<tr>
					<td>Negeri</td>
					<td>:&nbsp;</td>
					<td>$selectNegeri</td>
				</tr>
				<tr>
					<td valign="top">Catatan</td>
					<td valign="top">:&nbsp;</td>
					<td><textarea name="EDITtxtcatatan" $disability cols="30%" rows="4" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" >$catatan</textarea></td>
				</tr>
			</table>
		</td>
		
		</tr>
		</table>
				
	</fieldset>
#end

<!-- END VIEW & EDIT MODE -->

<p/>
		
		<table width="100%"  cellpadding="1" cellspacing="1" border="0">
			<tr align="center">
				<td>
					#if($viewLantikPenjaga=="yes")
					#if($editLantikPenjaga=="no")
					<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali('$id_ob','$nama_ob')" />
      				<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniDataPenjaga()" />
      				#end
      				#end
      				
      				#if($editLantikPenjaga=="yes")
      				<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal('$id_penjaga')" />
      				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="updateDataPenjaga()" />
      				#end
      				
      				#if($viewLantikPenjaga=="no")
      				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali('$id_ob','$nama_ob')" />
      				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="simpanLantikPenjaga()" />
      				#end
      				    				
      			</td>
			</tr>
		</table>
			
<input type="hidden" name="command">
<input type="hidden" name="id_ob" value="$id_ob">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_status" value="$id_status">
<input type="hidden" name="nama_ob" value="$nama_ob">
<input type="hidden" name="id_penjaga" value="$id_penjaga">

</center>
</form>

<script>
function simpanLantikPenjaga(){

	temp1 = parseInt(document.f1.txtposkod.value);

	if ((document.f1.txtNoKPBaru1.value=="" && document.f1.txtNoKPBaru2.value=="" && document.f1.txtNoKPBaru3.value=="" && document.f1.txtNoKPLama.value=="" && (document.f1.socJenisKp.value=="" || document.f1.socJenisKp.value=="0"  ) && document.f1.txtJenisKP.value==""))
		{
			alert("Sila masukkan salah satu nombor kad pengenalan penjaga");
			document.f1.txtNoKPBaru1.focus();
			return;
		}
	else if (document.f1.txtNoKPBaru1.value!="" && document.f1.txtNoKPBaru1.value.charAt(2)> 1 )
	{
		alert("Sila masukkan nombor kad pengenalan penjaga dengan betul");
		document.f1.txtNoKPBaru1.focus();
		return;
	}
	else if (document.f1.txtNoKPBaru1.value!="" && document.f1.txtNoKPBaru1.value.charAt(2)== 1 && document.f1.txtNoKPBaru1.value.charAt(3)> 2 )
	{
		alert("Sila masukkan nombor kad pengenalan penjaga dengan betul");
		document.f1.txtNoKPBaru1.focus();
		return;
	}
	else if (document.f1.txtNoKPBaru1.value!="" && document.f1.txtNoKPBaru1.value.charAt(4)> 3 )
	{
		alert("Sila masukkan nombor kad pengenalan penjaga dengan betul");
		document.f1.txtNoKPBaru1.focus();
		return;
	}
	else if (document.f1.txtNoKPBaru1.value!="" && document.f1.txtNoKPBaru1.value.charAt(4)==3 && document.f1.txtNoKPBaru1.value.charAt(5)> 1)
	{
		alert("Sila masukkan nombor kad pengenalan penjaga dengan betul");
		document.f1.txtNoKPBaru1.focus();
		return;
	}
	else if ((document.f1.socJenisKp.value!="0" && document.f1.socJenisKp.value!=""  ) && document.f1.txtJenisKP.value=="")
	 	{
	 		alert("Sila masukkan nombor kad pengenalan lain penjaga");
	 		document.f1.txtJenisKP.focus();
			return; 
	 	}
 	
	
	else if ((document.f1.socJenisKp.value=="0" || document.f1.socJenisKp.value==""  ) && document.f1.txtJenisKP.value!="")
	 	{
	 		alert("Sila pilih jenis kad pengenalan lain penjaga");
	 		document.f1.socJenisKp.focus();
			return; 
	 	}
		
	else if ((document.f1.txtNoKPBaru1.value!="" || document.f1.txtNoKPBaru2.value!="" || document.f1.txtNoKPBaru3.value!="") && (document.f1.txtNoKPBaru1.value==""))
		{
	 	
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.f1.txtNoKPBaru1.focus();
			return; 
		} 
	else if ((document.f1.txtNoKPBaru1.value!="" || document.f1.txtNoKPBaru2.value!="" || document.f1.txtNoKPBaru3.value!="") && (document.f1.txtNoKPBaru2.value==""))
	 	{
	 	
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.f1.txtNoKPBaru2.focus();
			return; 
		} 
	else if ((document.f1.txtNoKPBaru1.value!="" || document.f1.txtNoKPBaru2.value!="" || document.f1.txtNoKPBaru3.value!="") && (document.f1.txtNoKPBaru3.value==""))
	 	{
	 		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
	 		document.f1.txtNoKPBaru3.focus();
			return; 
		}	
	else if (document.f1.txtNoKPBaru1.value!=""  && document.f1.txtNoKPBaru1.value.length < 6 ) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.f1.txtNoKPBaru1.focus();
			return; 
		}
	else if (document.f1.txtNoKPBaru2.value!="" && document.f1.txtNoKPBaru2.value.length < 2 ) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.f1.txtNoKPBaru2.focus();
			return; 
		}
	else if (document.f1.txtNoKPBaru3.value!="" && document.f1.txtNoKPBaru3.value.length < 4) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.f1.txtNoKPBaru3.focus();
			return; 
		}
	else if(document.f1.txtNamaPenjaga.value == "")
		{
		alert("Sila masukkan \"Nama Penjaga\" terlebih dahulu.");
  		document.f1.txtNamaPenjaga.focus(); 
		return;
		}
	else if(document.f1.txtposkod.value != "" && (temp1 <10000 || temp1>99999))
	{
		alert ("Sila masukkan 5 digit poskod");
		document.f1.txtposkod.focus();
		return;	
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.f1.command.value = "simpanLantikPenjaga";
		document.f1.action = "";
		document.f1.submit();
	
		}
}
function jantinaic()
{
var ch=document.f1.txtNoKPBaru3.value.charAt(3);

if(ch%2 == 0)
{
	document.f1.socJantina.value = 2 ;


}
if(ch%2 != 0)
{
	document.f1.socJantina.value = 1 ;

}
return;
}

function kembali(id_ob,nama_ob) {
	
	document.f1.id_ob.value = id_ob;
	document.f1.nama_ob.value = nama_ob;
	document.f1.command.value = "maklumatPenjagaAdd";
	document.f1.action = "";
	document.f1.submit();
}
function kemaskiniDataPenjaga() {
	document.f1.command.value = "kemaskiniDataPenjaga";
	document.f1.action = "";
	document.f1.submit();
}
function batal(id_penjaga) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.f1.id_penjaga.value = id_penjaga;
	document.f1.command.value = "semakDataPenjaga";
	document.f1.action = "";
	document.f1.submit();
}
function validatePoskod(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function updateDataPenjaga(){

	temp1 = parseInt(document.f1.EDITtxtposkod.value);

	if ((document.f1.EDITtxtNoKPBaru1.value=="" && document.f1.EDITtxtNoKPBaru2.value=="" && document.f1.EDITtxtNoKPBaru3.value=="" && document.f1.EDITtxtNoKPLama.value=="" && (document.f1.EDITsocJenisKp.value=="" || document.f1.EDITsocJenisKp.value=="0"  ) && document.f1.EDITtxtJenisKP.value==""))
		{
			alert("Sila masukkan salah satu nombor kad pengenalan penjaga");
			document.f1.EDITtxtNoKPBaru1.focus();
			return;
		}
	else if (document.f1.EDITtxtNoKPBaru1.value!="" && document.f1.EDITtxtNoKPBaru1.value.charAt(2)> 1 )
	{
		alert("Sila masukkan nombor kad pengenalan penjaga dengan betul");
		document.f1.EDITtxtNoKPBaru1.focus();
		return;
	}
	else if (document.f1.EDITtxtNoKPBaru1.value!="" && document.f1.EDITtxtNoKPBaru1.value.charAt(2)== 1 && document.f1.EDITtxtNoKPBaru1.value.charAt(3)> 2 )
	{
		alert("Sila masukkan nombor kad pengenalan penjaga dengan betul");
		document.f1.EDITtxtNoKPBaru1.focus();
		return;
	}
	else if (document.f1.EDITtxtNoKPBaru1.value!="" && document.f1.EDITtxtNoKPBaru1.value.charAt(4)> 3 )
	{
		alert("Sila masukkan nombor kad pengenalan penjaga dengan betul");
		document.f1.EDITtxtNoKPBaru1.focus();
		return;
	}
	else if (document.f1.EDITtxtNoKPBaru1.value!="" && document.f1.EDITtxtNoKPBaru1.value.charAt(4)==3 && document.f1.EDITtxtNoKPBaru1.value.charAt(5)> 1)
	{
		alert("Sila masukkan nombor kad pengenalan penjaga dengan betul");
		document.f1.EDITtxtNoKPBaru1.focus();
		return;
	}
	else if ((document.f1.EDITsocJenisKp.value!="0" && document.f1.EDITsocJenisKp.value!=""  ) && document.f1.EDITtxtJenisKP.value=="")
	 	{
	 		alert("Sila masukkan nombor kad pengenalan lain penjaga");
	 		document.f1.EDITtxtJenisKP.focus();
			return; 
	 	}
	
	else if ((document.f1.EDITsocJenisKp.value=="0" || document.f1.EDITsocJenisKp.value==""  ) && document.f1.EDITtxtJenisKP.value!="")
	 	{
	 		alert("Sila pilih jenis kad pengenalan lain penjaga");
	 		document.f1.EDITsocJenisKp.focus();
			return; 
	 	}
		
	else if ((document.f1.EDITtxtNoKPBaru1.value!="" || document.f1.EDITtxtNoKPBaru2.value!="" || document.f1.EDITtxtNoKPBaru3.value!="") && (document.f1.EDITtxtNoKPBaru1.value==""))
		{
	 	
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.f1.EDITtxtNoKPBaru1.focus();
			return; 
		} 
	else if ((document.f1.EDITtxtNoKPBaru1.value!="" || document.f1.EDITtxtNoKPBaru2.value!="" || document.f1.EDITtxtNoKPBaru3.value!="") && (document.f1.EDITtxtNoKPBaru2.value==""))
	 	{
	 	
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.f1.EDITtxtNoKPBaru2.focus();
			return; 
		} 
	else if ((document.f1.EDITtxtNoKPBaru1.value!="" || document.f1.EDITtxtNoKPBaru2.value!="" || document.f1.EDITtxtNoKPBaru3.value!="") && (document.f1.EDITtxtNoKPBaru3.value==""))
	 	{
	 		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
	 		document.f1.EDITtxtNoKPBaru3.focus();
			return; 
		}	
	else if (document.f1.EDITtxtNoKPBaru1.value!=""  && document.f1.EDITtxtNoKPBaru1.value.length < 6 ) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.f1.EDITtxtNoKPBaru1.focus();
			return; 
		}
	else if (document.f1.EDITtxtNoKPBaru2.value!="" && document.f1.EDITtxtNoKPBaru2.value.length < 2 ) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.f1.EDITtxtNoKPBaru2.focus();
			return; 
		}
	else if (document.f1.EDITtxtNoKPBaru3.value!="" && document.f1.EDITtxtNoKPBaru3.value.length < 4) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.f1.EDITtxtNoKPBaru3.focus();
			return; 
		}
	else if(document.f1.EDITtxtNamaPenjaga.value == "")
		{
		alert("Sila masukkan \"Nama Penjaga\" terlebih dahulu.");
  		document.f1.EDITtxtNamaPenjaga.focus(); 
		return;
		}
	else if(document.f1.EDITtxtposkod.value != "" && (temp1 <10000 || temp1>99999))
	{
		alert ("Sila masukkan 5 digit poskod");
		document.f1.EDITtxtposkod.focus();
		return;	
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.f1.command.value = "updateDataPenjaga";
		document.f1.action = "";
		document.f1.submit();
	
		}
}
</script>