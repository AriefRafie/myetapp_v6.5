<br/>


#if($!headerppk.size()>0)
#parse("app/ppk/headerppk.jsp")
#end
<br>

<center>

#set($perhatian="<i><font color=red style=font-size:10px>Perhatian</font>&nbsp;<font style=font-size:10px>: Sila masukkan salah satu nombor kad pengenalan dan pastikan label bertanda</font>&nbsp;<font color=red style=font-size:10px>*</font>&nbsp;<font style=font-size:10px>diisi.</font></i>")

	<!-- paging -->	
	#set($pagingPenjaga="CL - 01 - 87")

#foreach($list in $listSemak)
 	#set($NO_FAIL=$list.noFail)
#end

#if($disabilityMode=="yes")
	#set($disability = "class=disabled disabled")
	#set($disability1 = "class=disabled readonly")
#else
	#set($disability = "")
	#set($disability1 = "")
#end

#if($getdata=="yes")
#foreach($data in $dataOBbyID)
	#set($nama =$data.nama_ob)
	#set($nokp1 =$data.no_kp_baru1)
	#set($nokp2 =$data.no_kp_baru2)
	#set($nokp3 =$data.no_kp_baru3)
	#set($nokplama =$data.no_kp_lama)
	#set($jenis_kp =$data.jenis_kp)
	#set($txtjeniskp =$data.no_kp_lain)
	#set($jantina =$data.jantina)
	#set($agama =$data.jenis_agama)
	#set($warganegara =$data.jenis_warga)
	#set($umur =$data.age)
	#set($alamat1 =$data.alamat_1)
	#set($alamat2 =$data.alamat_2)
	#set($alamat3 =$data.alamat_3)
	#set($bandar =$data.id_bandar)
	#set($poskod =$data.poskod)
	
#end
<input type="hidden" name="btxtNamaPenjaga" value="$nama">
<input type="hidden" name="btxtNoKPBaru1" value="$nokp1">
<input type="hidden" name="btxtNoKPBaru2" value="$nokp2">
<input type="hidden" name="btxtNoKPBaru3" value="$nokp3">
<input type="hidden" name="btxtNoKPLama" value="$nokplama">
<input type="hidden" name="bsocJenisKp" value="$jenis_kp">
<input type="hidden" name="btxtJenisKP" value="$txtjeniskp">
<input type="hidden" name="bsocJantina" value="$jantina">
<input type="hidden" name="bsocAgama" value="$agama">
<input type="hidden" name="bsocWarganegara" value="$warganegara">
<input type="hidden" name="btxtUmur" value="$umur">
<input type="hidden" name="btxtalamat1" value="$alamat1">
<input type="hidden" name="btxtalamat2" value="$alamat2">
<input type="hidden" name="btxtalamat3" value="$alamat3">
<input type="hidden" name="btxtbandar" value="$bandar">
<input type="hidden" name="btxtposkod" value="$poskod">
<input type="hidden" name="bsocNegeri" value="$idnegeri">

#else

  #if($onchangeNegeri=="no")
	#set($nama ="")
	#set($nokp1 ="")
	#set($nokp2 ="")
	#set($nokp3 ="")
	#set($nokplama ="")
	#set($jenis_kp ="")
	#set($txtjeniskp ="")
	#set($jantina ="")
	#set($agama ="")
	#set($warganegara ="")
	#set($umur ="")
	#set($alamat1 ="")
	#set($alamat2 ="")
	#set($alamat3 ="")
	#set($bandar ="")
	#set($poskod ="")
	#set($catatanPenjaga="")
	#set($txdTarikhPerlantikanPenjaga="")
	
  #end
  	
#end

#if($listPenjaga_size!=4 || $listPenjaga_size>4) 
	#set($off = "")
	#set($off1 = "")
	#set($Coff = "")
#else
	#set($off = "readonly")	
	#set($off1 = "disabled")	
	#set($Coff = "class=disabled")
#end

#if($dropdownDisable=="disabled")
	#set($classmode = "class=disabled")
#else
	#set($classmode = "")
#end

<!-- ADD/NEW MODE -->
#if($viewLantikPenjaga=="no")

	#if($selectedDropdown.size()!=0)
		#set($xob = "")
		#set($xob1 = "")
		#set($xxob = "")
	#else
		#set($xob = "readonly")
		#set($xob1 = "disabled")
		#set($xxob = "class=disabled")
	#end

	#if($id_status!="21" && $id_status!="25" && $id_status!="64" && $id_status!="163" && $id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")
		#set($kemaskiniSelesai = "")
	#else
		#set($kemaskiniSelesai = "disabled")	
	#end
    
    

	<fieldset>
	<legend><strong>MAKLUMAT PENJAGA</strong></legend>
	
	<br/>
	<table width="100%"  cellpadding="2" cellspacing="2" border="0">
			<tr>
				<td width="13%"><b>NAMA WARIS </b></td>
				<td width="77%">:&nbsp;<font color="blue"><b>$nama_ob.toUpperCase()</b></font></td>
			</tr>
	</table>	
	<br/>
	<table width="100%" cellpadding="1" cellspacing="2" border="0">
		<tr>
			<td width="15"><input type="radio" $kemaskiniSelesai $sor1 name="sorPenjaga" $off1 id="penjagaOB" value="0" onClick="doChangeSorPenjagaOB();" />Penjaga OB</td>
			<td width="32%">:&nbsp;<select name="socPenjagaOB" id="socPenjagaOB" $kemaskiniSelesai $Coff $off1 $xxob $xob1 style="width:250" $dropdownDisable $classmode onChange="doChangeSorPenjagaOB();">
                    			
                    		#if($onchange=="yes")
                    				#if($default=="yes")
                    				<option value="0">SILA PILIH&nbsp;</option>
                    				#else
                    				<option value="$value">$valueName.toUpperCase()&nbsp;</option>            
                    				#end                 				
                    			#foreach($obselect in $selectedDropdown)
                    				#if($obselect.id_ob != $value)
                    				<option value="$obselect.id_ob">$obselect.nama_ob.toUpperCase()</option>
                    				#end
                    			#end
                    		#end
                    			
                    		#if($onchange=="no")
                    			<option value="0">SILA PILIH&nbsp;</option>
                    			#set($x=0)
                    			#foreach($obselect in $selectedDropdown)
                    			<option value="$obselect.id_ob">$obselect.nama_ob.toUpperCase()</option>
                    			#end
                    		#end
                    		
                    	
                    		 </select></td>                  		 
			<td width="53%"><input type="radio" $kemaskiniSelesai $sor2 name="sorPenjaga" $off1 id="penjagaBaru" value="1" onClick="doChangeSorPenjagaBaru()" >Penjaga Baru</td>
		</tr>
		<tr><td colspan="3">&nbsp;</td></tr>
	</table>		
	<table width="100%">
		<tr>
		<td width="47%" valign="top">
			<table width="100%" cellpadding="1" cellspacing="2" border="0">
				
				<tr>
					<td width="1%" valign="top">#if($disabilityMode=="no")<font color="red">*</font>#else&nbsp;#end</td>
					<td width="30%">Nama Penjaga</td>
					<td width="69%">:&nbsp;<input $disability1 type="text" size="30" name="txtNamaPenjaga" id="txtNamaPenjaga" value="$nama" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>No.KP Baru</td>
					<td>:&nbsp;<input type="text" $disability1 size="6" name="txtNoKPBaru1" id="txtNoKPBaru1" value="$nokp1" maxlength="6"  onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru2')"  onBlur="getAgeByIC(this,this.value,'txtUmur')"/>-<input name="txtNoKPBaru2" id="txtNoKPBaru2" $disability1 type="text" value="$nokp2"  size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')"/>-<input name="txtNoKPBaru3" $disability1 id="txtNoKPBaru3" onblur="jantinaic()" type="text" value="$nokp3" size="3" maxlength="4" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>No.KP Lama</td>
					<td>:&nbsp;<input $disability1 type="text" name="txtNoKPLama" value="$nokplama" size="10" maxlength="8" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>				
				<tr>
					<td>&nbsp;</td>
					<td>Lain - lain KP</td>
					<td>:&nbsp;<select $disability name="socJenisKp" id="socJenisKp" style="width:110">
                    	#if($jenis_kp=="0" || $jenis_kp=="")
                    	<option value="0">SILA PILIH&nbsp;</option>
                    	<option value="4">NO.PASPORT</option>
                    	<option value="5">NO.TENTERA</option>
                    	<option value="6">NO.POLIS</option>
                    	#end
                    	#if($jenis_kp=="4")
                    	<option value="4">NO.PASPORT</option>
                    	<option value="5">NO.TENTERA</option>
                    	<option value="6">NO.POLIS</option>
                    	<option value="0">-KOSONGKAN-</option>  
                    	#end
                    	#if($jenis_kp=="5")
                    	<option value="5">NO.TENTERA</option>
                    	<option value="4">NO.PASPORT</option>
                    	<option value="6">NO.POLIS</option>
                    	<option value="0">-KOSONGKAN-</option>  
                    	#end
                    	#if($jenis_kp=="6")
                    	<option value="6">NO.POLIS</option>
                    	<option value="4">NO.PASPORT</option>
                    	<option value="5">NO.TENTERA</option>
                    	<option value="0">-KOSONGKAN-</option>  
                    	#end
                  			   </select>&nbsp;<input type="text" name="txtJenisKP" $disability1 value="$txtjeniskp" size="12" maxlength="12" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Jantina</td>
					<td>:&nbsp;<select $disability name="socJantina" id="socJantina" style="width:110">
                    		#if($jantina=="0" || $jantina=="")
                    		<option value="0">SILA PILIH&nbsp;</option>
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
					<td>&nbsp;</td>
					<td>Agama</td>
					<td>:&nbsp;<select $disability name="socAgama" id="socAgama" style="width:110">
                    		#if($agama=="0" || $agama=="")
                    		<option value="0">SILA PILIH&nbsp;</option>
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
					<td>&nbsp;</td>
					<td>Warganegara</td>
					<td>:&nbsp;<select $disability name="socWarganegara" id="socWarganegara" style="width:170">
                    		#if($warganegara=="0" || $warganegara=="")
                    		<option value="0">SILA PILIH&nbsp;</option>
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
					<td>&nbsp;</td>
					<td>Umur</td>
					<td>:&nbsp;<input $disability1 type="text" id="txtUmur" name="txtUmur" value="$umur" size="2" onblur="validatePoskod(this,this.value);" onkeyup="validatePoskod(this,this.value);javascript:validateIC(this,this.value,'txtUmur');"  maxlength="3" />
                    </td>
				</tr>
			</table>
		</td>
		
		<td width="53%" valign="top">
			<table width="100%"  cellpadding="1" cellspacing="2" border="0">
				<tr>
					<td width="19%">Alamat</td>
					<td width="1%">:&nbsp;</td>
					<td width="80%"><input type="text" $disability1 size="40" name="txtalamat1" id="txtalamat1" value="$alamat1" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input type="text" size="40" $disability1 name="txtalamat2" id="txtalamat2" value="$alamat2" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input type="text" size="40" $disability1 name="txtalamat3" id="txtalamat3" value="$alamat3" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				
				<!-- <tr>
					<td>Bandar</td>
					<td>:&nbsp;</td>
					<td><input type="text" $disability1 name="txtbandar" value="$bandar" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr> -->
				
				<tr>
					<td>Poskod</td>
					<td>:&nbsp;</td>
					<td><input type="text" $disability1 name="txtposkod" size="4" onkeyup="validatePoskod(this,this.value);"  value="$poskod" maxlength="5" id="poskod"></td>
				</tr>
				<tr>
					<td>Negeri</td>
					<td>:&nbsp;</td>
					<td>$selectNegeri</td>
				</tr>
				<tr>
					<td>Bandar</td>
					<td>:&nbsp;</td>
					<td>$!selectBandar</td>
				</tr>
				<tr>
					<td>Tarikh Perlantikan Penjaga</td>
					<td>:&nbsp;</td>
					<td><input name="txdTarikhPerlantikanPenjaga" value="$!txdTarikhPerlantikanPenjaga" maxlength="10" size="11" id="txdTarikhPerlantikanPenjaga" type="text" 
				onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
                <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhPerlantikanPenjaga',false,'dmy');" /></td>
				</tr>
				#if($disabilityMode=="no")
				<tr>
					<td valign="top">Catatan</td>
					<td valign="top">:&nbsp;</td>
					<td><textarea name="txtcatatan" id="txtcatatan" $disability1 cols="40%" rows="5" style="text-transform:uppercase;" onKeyUp="textCounter(this.form.txtcatatan,this.form.remLen1,2000);" onKeyDown="textCounter(this.form.txtcatatan,this.form.remLen1,2000);" onBlur="this.value=this.value.toUpperCase();" >$!catatanPenjaga</textarea></td>
				</tr>				
				<tr>
             		<td valign="top">&nbsp;</td>
             		<td valign="top">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="2000"></td>
           		</tr> 
           		#end
			</table>
		</td>
		
		</tr>
		</table>
		
		<br/>
		<br/>	
		 <table width="100%"  cellpadding="1" cellspacing="1" border="0">
        	#if($disabilityMode=="no")
        	<tr>
        	<td>$!perhatian</td>
        	</tr>
      		#else
      		<tr><td>&nbsp;</td></tr>
      		#end
      	</table>	
				
	</fieldset>
#end
<!-- END ADD/NEW MODE -->


<!-- VIEW & EDIT MODE -->

#if($viewLantikPenjaga=="yes")

	#if($editLantikPenjaga=="yes")
		#set($disability = "")
		#set($disability1 = "")
	#else
		#set($disability = "class=disabled disabled")	
		#set($disability1 = "class=disabled readonly")	
	#end

  #if($onchangeNegeri=="no")
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
		#set($txdTarikhPerlantikanPenjaga = $data.tarikh_perlantikanpenjaga)
	#end
  #else
  		#set($noKPBaru1=$noKPBaru1x)
		#set($noKPBaru2=$noKPBaru2x)
		#set($noKPBaru3=$noKPBaru3x)
		#set($noKPLama=$noKPLamax)
		#set($noKPLain=$noKPLainx)
		#set($namaPenjaga=$namaPenjagax)
		#set($umur=$umurx)
		#set($alamat1=$alamat1x)
		#set($alamat2=$alamat2x)
		#set($alamat3=$alamat3x)
		#set($bandar=$bandarx)
		#set($poskod=$poskodx)
		#set($catatan=$catatanx)
		#set($warganegara=$warganegarax)
		#set($agama=$agamax)
		#set($jantina=$jantinax)
		#set($jenis_kp=$jenis_kpx)
  #end
	<fieldset>
	<legend><strong>MAKLUMAT PENJAGA</strong></legend>
	
	<br/>
	<table width="100%"  cellpadding="2" cellspacing="2" border="0">
			<tr>
				<td width="20%"><b>NAMA WARIS </b></td>
				<td width="80%">:&nbsp;<font color="blue"><b>$nama_ob.toUpperCase()</b></font></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
	</table>
		
	<table width="100%">
		<tr>
		<td width="47%" valign="top">
			<table width="100%" cellpadding="1" cellspacing="2" border="0">
				
				<tr>
					<td width="1%" valign="top">#if($editLantikPenjaga=="yes")<font color="red">*</font>#else&nbsp;#end</td>
					<td width="30%">Nama Penjaga</td>
					<td width="69%">:&nbsp;<input type="text" size="30" $disability1 name="EDITtxtNamaPenjaga" id="txtNamaPenjaga" value="$namaPenjaga" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>No.KP Baru</td>
					<td>:&nbsp;<input type="text" size="6" name="EDITtxtNoKPBaru1" $disability1 id="txtNoKPBaru1" value="$noKPBaru1" maxlength="6"  onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru2')"  onBlur="getAgeByIC(this,this.value,'txtUmur')"/>-<input name="EDITtxtNoKPBaru2" $disability1 id="txtNoKPBaru2" type="text" value="$noKPBaru2"  size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')"/>-<input name="EDITtxtNoKPBaru3" $disability1 id="txtNoKPBaru3" onblur="jantinaic()" type="text" value="$noKPBaru3" size="3" maxlength="4" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>No.KP Lama</td>
					<td>:&nbsp;<input type="text" $disability1 name="EDITtxtNoKPLama" value="$noKPLama" size="10" maxlength="8" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>				
				<tr>
					<td>&nbsp;</td>
					<td>Lain - lain KP</td>
					<td>:&nbsp;<select name="EDITsocJenisKp" id="socJenisKp" style="width:110" $disability>
                    	#if($jenis_kp=="0" || $jenis_kp=="")
                    	<option value="0">SILA PILIH&nbsp;</option>
                    	<option value="4">NO.PASPORT</option>
                    	<option value="5">NO.TENTERA</option>
                    	<option value="6">NO.POLIS</option>
                    	#end
                    	#if($jenis_kp=="4")
                    	<option value="4">NO.PASPORT</option>
                    	<option value="5">NO.TENTERA</option>
                    	<option value="6">NO.POLIS</option>
                    	<option value="0">-KOSONGKAN-</option>  
                    	#end
                    	#if($jenis_kp=="5")
                    	<option value="5">NO.TENTERA</option>
                    	<option value="4">NO.PASPORT</option>
                    	<option value="6">NO.POLIS</option>
                    	<option value="0">-KOSONGKAN-</option>  
                    	#end
                    	#if($jenis_kp=="6")
                    	<option value="6">NO.POLIS</option>
                    	<option value="4">NO.PASPORT</option>
                    	<option value="5">NO.TENTERA</option>
                    	<option value="0">-KOSONGKAN-</option>  
                    	#end
                  			   </select>&nbsp;<input type="text" $disability1 name="EDITtxtJenisKP" value="$noKPLain" size="12" maxlength="12" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Jantina</td>
					<td>:&nbsp;<select name="EDITsocJantina" $disability id="socJantina" style="width:110">
                    		#if($jantina=="0" || $jantina=="")
                    		<option value="0">SILA PILIH&nbsp;</option>
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
					<td>&nbsp;</td>
					<td>Agama</td>
					<td>:&nbsp;<select name="EDITsocAgama" $disability id="socAgama" style="width:110">
                    		#if($agama=="0" || $agama=="")
                    		<option value="0">SILA PILIH&nbsp;</option>
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
					<td>&nbsp;</td>
					<td>Warganegara</td>
					<td>:&nbsp;<select name="EDITsocWarganegara" $disability id="socWarganegara" style="width:170">
                    		#if($warganegara=="0" || $warganegara=="")
                    		<option value="0">SILA PILIH&nbsp;</option>
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
					<td>&nbsp;</td>
					<td>Umur</td>
					<td>:&nbsp;<input type="text" $disability1 id="txtUmur" name="EDITtxtUmur" value="$umur" size="2" onkeyup="javascript:validateIC(this,this.value,'txtUmur') maxlength="3" /></td>
				</tr>
			</table>
		</td>
		
		<td width="53%" valign="top">
			<table width="100%"  cellpadding="1" cellspacing="2" border="0">
				<tr>
					<td width="19%">Alamat</td>
					<td width="1%">:&nbsp;</td>
					<td width="80%"><input type="text" $disability1 size="40" name="EDITtxtalamat1" id="txtalamat1" value="$alamat1" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input type="text" size="40" $disability1 name="EDITtxtalamat2" id="txtalamat2" value="$alamat2" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input type="text" size="40" $disability1 name="EDITtxtalamat3" id="txtalamat3" value="$alamat3" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				
				<!-- <tr>
					<td>Bandar</td>
					<td>:&nbsp;</td>
					<td><input type="text" $disability1 name="EDITtxtbandar" value="$bandar" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr> -->
				
				<tr>
					<td>Poskod</td>
					<td>:&nbsp;</td>
					<td><input type="text" $disability1 name="EDITtxtposkod" size="4" onkeyup="validatePoskod(this,this.value);"  value="$poskod" maxlength="5" id="poskod"></td>
				</tr>
				<tr>
					<td>Negeri</td>
					<td>:&nbsp;</td>
					<td>$selectNegeri</td>
				</tr>
				<tr>
					<td>Bandar</td>
					<td>:&nbsp;</td>
					<td>$selectBandar</td>
				</tr>
				<tr>
					<td>Tarikh Perlantikan Penjaga</td>
					<td>:&nbsp;</td>
					<td><input name="txdTarikhPerlantikanPenjaga" $disability1 value="$!txdTarikhPerlantikanPenjaga" maxlength="10" size="11" id="$!txdTarikhPerlantikanPenjaga" type="text" />
                </td>
				</tr>
				#if($editable=="yes")
				<tr>
					<td valign="top">Catatan</td>
					<td valign="top">:&nbsp;</td>
					<td><textarea name="EDITtxtcatatan" id="EDITtxtcatatan" $disability1 cols="40%" rows="5" style="text-transform:uppercase;" onKeyUp="textCounter(this.form.EDITtxtcatatan,this.form.remLen2,2000);" onKeyDown="textCounter(this.form.EDITtxtcatatan,this.form.remLen2,2000);" onBlur="this.value=this.value.toUpperCase();" >$catatan</textarea></td>
				</tr>
				#if($editLantikPenjaga=="yes")
				<tr>
             		<td valign="top">&nbsp;</td>
             		<td valign="top">&nbsp;</td>
             		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen2" size="3" maxlength="3" value="2000"></td>
           		</tr>
           		#end
           		#end
			</table>
		</td>
		
		</tr>
		</table>
		
		<br/>
		<br/>	
		 <table width="100%"  cellpadding="1" cellspacing="1" border="0">
        	#if($editLantikPenjaga=="yes")
        	<tr>
        	<td>$!perhatian</td>
        	</tr>
      		#else
      		<tr><td>&nbsp;</td></tr>
      		#end
      	</table>
				
	</fieldset>
#end
</center>

<br/>
<table width="100%"  cellpadding="1" cellspacing="1" border="0">
			<tr align="center">
				<td>
					
					#if($editPenjaga=="no")
						#if($listPenjaga_size!=4 || $listPenjaga_size>4) 
							#if($id_status!="21" && $id_status!="25" && $id_status!="64" && $id_status!="163" && $id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")
							<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="simpanLantikPenjaga()" />
      						#end
      					#end
      				#end
      				
      				#if($editPenjaga=="yes")
      				
      					#if($editLantikPenjaga=="no")
						#if($editable=="yes")
							#if($id_status!="21" && $id_status!="25" && $id_status!="64" && $id_status!="163" && $id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")
							<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniDataPenjaga('$id_penjaga')" />
      						#end
      					#end
      					#end
      					
      					#if($listPenjaga_size!=4 || $listPenjaga_size>4) 
      						#if($editLantikPenjaga=="no")
      							#if($id_status!="21" && $id_status!="25" && $id_status!="64" && $id_status!="163" && $id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")
								<input type="button" name="cmdLantik" value="Lantik Penjaga" onClick="lantikPenjaga('$id_ob','$nama_ob')" />
								#end
							#end
						#end
      					
      					#if($editLantikPenjaga=="yes")
      					<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="updateDataPenjaga('$id_penjaga')" />
      					<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal('$id_penjaga')" />
      					#end
      				#end
      				
      				#if($listPenjaga_size!=0)
      				#if($editLantikPenjaga=="no")
                    
                    
                    #if($id_penjaga != "" )
                    <input type="button" name="button" id="button" value="Hapus" onClick="javascript:hapusPenjaga('$id_penjaga','$id_ob','$nama_ob');" />
                    #end
      				<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport')" />
      				#end
      				#end
      				
      					#if($id_status=="53" || $id_status=="151")
      					<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="semakNoData('$id_permohonan')" />
      					#elseif($id_status=="44" || $id_status=="173" || $id_status=="175" || $id_status=="177")
      					<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="tukarNotis('$id_permohonan')" />
      					#else
      					<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="semakWithData('$id_permohonan')" />
      					#end
      				
      				
      			</td>
			</tr>
		</table>

<!-- END VIEW & EDIT MODE -->
	#if($listPenjaga_size!=0)
<center>	
	<fieldset style="width:50%">
	<legend><strong>SENARAI PENJAGA</strong></legend>
	
		<table width="100%"  cellpadding="2" cellspacing="2" border="0">
		
			<tr class="table_header">
				<td width="8%" align="center"><b>No</b></td>
				<td width="92%"><b>Nama Penjaga OB / Penjaga Baru</b></td>
			</tr>
			
	
        #foreach($listP in $listPenjaga)
          #set($id_penjagaP = $listP.id_penjaga)
      		#set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
			<tr>
				<td align="center" class="$row">$listP.bil</td>
				<td class="$row"><a href="javascript:semakDataPenjaga('$id_penjagaP')"><font color="blue">$listP.nama_penjaga.toUpperCase()</font></a></td>
			</tr>
			
			<input type="hidden" id="jumlahpenjaga" name="jumlahpenjaga" value="$listP.bil">
			<input type="hidden" id="$listP.bil" name="senaraipenjaga" value="$listP.nama_penjaga.toUpperCase()">
			
		#end

		</table>

	</fieldset>

	#end	
	
	<table width="98%"  cellpadding="1" cellspacing="1" border="0">
  			<tr>
  				<td align="right">$!pagingPenjaga</td>
  			</tr>
	</table>
	
	<fieldset id="tableReport" style="display:none;">
	<legend><strong>SENARAI LAPORAN</strong></legend>
		<table width="100%" border="0" cellspacing="2" cellpadding="2">
      		<tr>
        		<td ><a href="#" class="style2" onClick="javascript:cetakBorangH('$id_fail','$idobminor')"><font color="blue">Borang H</font></a></td>
      		</tr> 
    	</table>      
	</fieldset> 
	
</center>

	
		
	
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<!-- <input type="hidden" name="command"> -->

<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="id_status" value="$id_status">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_keputusanpermohonan" value="$id_keputusanpermohonan">
<input type="hidden" name="id_perbicaraan" value="$id_perbicaraan">
<input type="hidden" name="id_simati" value="$id_simati">
<input type="hidden" name="id_ob" value="$id_ob">
<input type="hidden" name="nama_ob" value="$nama_ob">
<input type="hidden" name="listPenjaga_size" value="$listPenjaga_size">
<input type="hidden" name="id_penjaga" value="$id_penjaga">
<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
<input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
<input name="flagForView" type="hidden" id="flagForView" value="$flagForView"/>
<input type="hidden" name="idobminor" value="$idobminor">
<input name="flag_KP" type="hidden" id="flag_KP" value="$flagFromKeputusanPermohonan"/>

<input type="hidden" name="id_fail" value="$id_fail">



#parse("app/ppk/headerppk_script.jsp")
<script>

function semakNoData(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakNoData";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function semakWithData(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakWithData";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function tukarNotis(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "tukarNotis";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function lantikPenjaga(id_ob,nama_ob) {
	document.${formName}.id_ob.value = id_ob;
	document.${formName}.nama_ob.value = nama_ob;
	document.${formName}.command.value = "maklumatPenjagaAdd";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function semakDataPenjaga(id_penjaga) {
	document.${formName}.id_penjaga.value = id_penjaga;
	document.${formName}.command.value = "semakDataPenjaga";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function hapusPenjaga(id_penjaga,id_ob,nama_ob) {
	document.${formName}.id_penjaga.value = id_penjaga;
	document.${formName}.id_ob.value = id_ob;
	document.${formName}.nama_ob.value = nama_ob;
	document.${formName}.command.value = "hapusPenjaga";
	//document.${formName}.command2.value = "hapusPenjaga";
	document.${formName}.action = "";
	document.${formName}.submit()
}




function doChangeidPenjaga() {
	document.${formName}.command.value = "doChangeidPenjaga";
	document.${formName}.action = ""; 
	document.${formName}.submit();
	
}
function simpanLantikPenjaga(){

//	var negeri_code = document.${formName}.txtNoKPBaru2.value;
/*	var dob_code = document.${formName}.txtNoKPBaru1.value;
	if(dob_code.charAt(0)<3)
		{
		 var dum = "20";
		}
		else
		{
		var dum = "19";
		}

		
	var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
		 var dt_dob   = parseInt(tt.substring(0,2),10);
	     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
	     var yr_dob   = parseInt(tt.substring(6,10),10);
		 var date_dob = new Date(yr_dob, mon_dob, dt_dob);
*/
	temp1 = parseInt(document.${formName}.txtposkod.value);

	if(document.${formName}.txtNamaPenjaga.value == "")
	{
			alert("Sila masukkan \"Nama Penjaga\" terlebih dahulu.");
			document.${formName}.txtNamaPenjaga.focus(); 
			return;
	}
/*	else if ((document.${formName}.txtNoKPBaru1.value=="" && document.${formName}.txtNoKPBaru2.value=="" && document.${formName}.txtNoKPBaru3.value=="" && document.${formName}.txtNoKPLama.value=="" && (document.${formName}.socJenisKp.value=="" || document.${formName}.socJenisKp.value=="0"  ) && document.${formName}.txtJenisKP.value==""))
		{
			alert("Sila masukkan salah satu nombor kad pengenalan penjaga");
			document.${formName}.txtNoKPBaru1.focus();
			return;
		}
	else if ((document.${formName}.socJenisKp.value!="0" && document.${formName}.socJenisKp.value!=""  ) && document.${formName}.txtJenisKP.value=="")
	 	{
	 		alert("Sila masukkan nombor kad pengenalan lain penjaga");
	 		document.${formName}.txtJenisKP.focus();
			return; 
	 	}
 	
	
	else if ((document.${formName}.socJenisKp.value=="0" || document.${formName}.socJenisKp.value==""  ) && document.${formName}.txtJenisKP.value!="")
	 	{
	 		alert("Sila pilih jenis kad pengenalan lain penjaga");
	 		document.${formName}.socJenisKp.focus();
			return; 
	 	}
		
	else if ((document.${formName}.txtNoKPBaru1.value!="" || document.${formName}.txtNoKPBaru2.value!="" || document.${formName}.txtNoKPBaru3.value!="") && (document.${formName}.txtNoKPBaru1.value==""))
		{
	 	
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.${formName}.txtNoKPBaru1.focus();
			return; 
		} 
	else if ((document.${formName}.txtNoKPBaru1.value!="" || document.${formName}.txtNoKPBaru2.value!="" || document.${formName}.txtNoKPBaru3.value!="") && (document.${formName}.txtNoKPBaru2.value==""))
	 	{
	 	
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.${formName}.txtNoKPBaru2.focus();
			return; 
		} 
	else if ((document.${formName}.txtNoKPBaru1.value!="" || document.${formName}.txtNoKPBaru2.value!="" || document.${formName}.txtNoKPBaru3.value!="") && (document.${formName}.txtNoKPBaru3.value==""))
	 	{
	 		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
	 		document.${formName}.txtNoKPBaru3.focus();
			return; 
		}	
	else if (document.${formName}.txtNoKPBaru1.value!=""  && document.${formName}.txtNoKPBaru1.value.length < 6 ) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.${formName}.txtNoKPBaru1.focus();
			return; 
		}
	else if (document.${formName}.txtNoKPBaru2.value!="" && document.${formName}.txtNoKPBaru2.value.length < 2 ) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.${formName}.txtNoKPBaru2.focus();
			return; 
		}
	else if (document.${formName}.txtNoKPBaru3.value!="" && document.${formName}.txtNoKPBaru3.value.length < 4) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.${formName}.txtNoKPBaru3.focus();
			return; 
		}
*/	else if (document.${formName}.txtposkod.value != "" && document.${formName}.txtposkod.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.txtposkod.focus();
		return;
	}
/*	else if (document.${formName}.txtNoKPBaru1.value != "" && isIc(tt)==false){
		document.${formName}.txtNoKPBaru1.focus()
		return;
	}
	else if(document.${formName}.txtNoKPBaru2.value != "" &&(negeri_code!="01" && negeri_code!="21" && 
			negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && 
			negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && 
			negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && 
			negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && 
			negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && 
			negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && 
			negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && 
			negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && 
			negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
			negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
			negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 	negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  	negeri_code!="15" && negeri_code!="58" &&
		    negeri_code!="16" && 
		    negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
		alert("Sila masukkan kod negeri yang sah");
		document.${formName}.txtNoKPBaru2.focus()
		return;
	
	}
*/	else{
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "simpanLantikPenjaga";
		document.${formName}.action = "";
		document.${formName}.submit();
	
		}
}
function jantinaic()
{
var ch=document.${formName}.txtNoKPBaru3.value.charAt(3);

if(ch%2 == 0)
{
	document.${formName}.socJantina.value = 2 ;


}
if(ch%2 != 0)
{
	document.${formName}.socJantina.value = 1 ;

}
return;
}
function kembali(id_ob,nama_ob) {
	
	document.${formName}.id_ob.value = id_ob;
	document.${formName}.nama_ob.value = nama_ob;
	document.${formName}.command.value = "maklumatPenjagaAdd";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function kemaskiniDataPenjaga(id_penjaga) {
	document.${formName}.id_penjaga.value = id_penjaga;
	document.${formName}.command.value = "semakDataPenjaga";
	document.${formName}.command2.value = "kemaskiniDataPenjaga";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function batal(id_penjaga) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_penjaga.value = id_penjaga;
	document.${formName}.command.value = "semakDataPenjaga";
	document.${formName}.action = "";
	document.${formName}.submit();
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
function updateDataPenjaga(id_penjaga){

//	var negeri_code = document.${formName}.EDITtxtNoKPBaru2.value;
/*	var dob_code = document.${formName}.EDITtxtNoKPBaru1.value;
	if(dob_code.charAt(0)<3)
		{
		 var dum = "20";
		}
		else
		{
		var dum = "19";
		}

		
	var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
		 var dt_dob   = parseInt(tt.substring(0,2),10);
	     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
	     var yr_dob   = parseInt(tt.substring(6,10),10);
		 var date_dob = new Date(yr_dob, mon_dob, dt_dob);
*/	
	temp1 = parseInt(document.${formName}.EDITtxtposkod.value);

	if(document.${formName}.EDITtxtNamaPenjaga.value == "")
		{
			alert("Sila masukkan \"Nama Penjaga\" terlebih dahulu.");
			document.${formName}.EDITtxtNamaPenjaga.focus(); 
			return;
		}
/*	else if ((document.${formName}.EDITtxtNoKPBaru1.value=="" && document.${formName}.EDITtxtNoKPBaru2.value=="" && document.${formName}.EDITtxtNoKPBaru3.value=="" && document.${formName}.EDITtxtNoKPLama.value=="" && (document.${formName}.EDITsocJenisKp.value=="" || document.${formName}.EDITsocJenisKp.value=="0"  ) && document.${formName}.EDITtxtJenisKP.value==""))
		{
			alert("Sila masukkan salah satu nombor kad pengenalan penjaga");
			document.${formName}.EDITtxtNoKPBaru1.focus();
			return;
		}

	else if ((document.${formName}.EDITsocJenisKp.value!="0" && document.${formName}.EDITsocJenisKp.value!=""  ) && document.${formName}.EDITtxtJenisKP.value=="")
	 	{
	 		alert("Sila masukkan nombor kad pengenalan lain penjaga");
	 		document.${formName}.EDITtxtJenisKP.focus();
			return; 
	 	}
	
	else if ((document.${formName}.EDITsocJenisKp.value=="0" || document.${formName}.EDITsocJenisKp.value==""  ) && document.${formName}.EDITtxtJenisKP.value!="")
	 	{
	 		alert("Sila pilih jenis kad pengenalan lain penjaga");
	 		document.${formName}.EDITsocJenisKp.focus();
			return; 
	 	}
		
	else if ((document.${formName}.EDITtxtNoKPBaru1.value!="" || document.${formName}.EDITtxtNoKPBaru2.value!="" || document.${formName}.EDITtxtNoKPBaru3.value!="") && (document.${formName}.EDITtxtNoKPBaru1.value==""))
		{
	 	
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.${formName}.EDITtxtNoKPBaru1.focus();
			return; 
		} 
	else if ((document.${formName}.EDITtxtNoKPBaru1.value!="" || document.${formName}.EDITtxtNoKPBaru2.value!="" || document.${formName}.EDITtxtNoKPBaru3.value!="") && (document.${formName}.EDITtxtNoKPBaru2.value==""))
	 	{
	 	
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.${formName}.EDITtxtNoKPBaru2.focus();
			return; 
		} 
	else if ((document.${formName}.EDITtxtNoKPBaru1.value!="" || document.${formName}.EDITtxtNoKPBaru2.value!="" || document.${formName}.EDITtxtNoKPBaru3.value!="") && (document.${formName}.EDITtxtNoKPBaru3.value==""))
	 	{
	 		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
	 		document.${formName}.EDITtxtNoKPBaru3.focus();
			return; 
		}	
	else if (document.${formName}.EDITtxtNoKPBaru1.value!=""  && document.${formName}.EDITtxtNoKPBaru1.value.length < 6 ) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.${formName}.EDITtxtNoKPBaru1.focus();
			return; 
		}
	else if (document.${formName}.EDITtxtNoKPBaru2.value!="" && document.${formName}.EDITtxtNoKPBaru2.value.length < 2 ) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.${formName}.EDITtxtNoKPBaru2.focus();
			return; 
		}
	else if (document.${formName}.EDITtxtNoKPBaru3.value!="" && document.${formName}.EDITtxtNoKPBaru3.value.length < 4) 
		{
			alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
			document.${formName}.EDITtxtNoKPBaru3.focus();
			return; 
		}
*/	else if (document.${formName}.EDITtxtposkod.value != "" && document.${formName}.EDITtxtposkod.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.EDITtxtposkod.focus();
		return;
	}
/*	else if (document.${formName}.EDITtxtNoKPBaru1.value != "" && isIc(tt)==false){
		document.${formName}.EDITtxtNoKPBaru1.focus()
		return;
	}
	else if(document.${formName}.EDITtxtNoKPBaru2.value != "" &&(negeri_code!="01" && negeri_code!="21" && 
			negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && 
			negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && 
			negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && 
			negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && 
			negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && 
			negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && 
			negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && 
			negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && 
			negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
			negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
			negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 	negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  	negeri_code!="15" && negeri_code!="58" &&
		    negeri_code!="16" && 
		    negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
		alert("Sila masukkan kod negeri yang sah");
		document.${formName}.EDITtxtNoKPBaru2.focus()
		return;
	
	}
*/	else{
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.id_penjaga.value = id_penjaga;
		document.${formName}.command.value = "semakDataPenjaga";
		document.${formName}.command2.value = "kemaskiniDataPenjaga";
		document.${formName}.command3.value = "updateDataPenjaga";
		document.${formName}.action = "";
		document.${formName}.submit();
	
		}
}
function doChangeGetBandarByNegeriUpdate() {
	document.${formName}.command.value = "semakDataPenjaga";
	document.${formName}.command2.value = "kemaskiniDataPenjaga";
	document.${formName}.command3.value = "doChangeGetBandarByNegeriUpdate";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function doChangeSorPenjagaOB() {
	//doAjaxCall${formName}("maklumatPenjagaAdd","command2=doChangeSorPenjagaOB");
	document.${formName}.command.value = "maklumatPenjagaAdd";
	document.${formName}.command2.value = "doChangeSorPenjagaOB";
	document.${formName}.action = ""; 
	document.${formName}.submit();	
}
function doChangeGetBandarByNegeri() {
	document.${formName}.command.value = "maklumatPenjagaAdd";
	document.${formName}.command2.value = "doChangeSorPenjagaBaru";
	document.${formName}.command3.value = "doChangeGetBandarByNegeri";
	document.${formName}.action = ""; 
	document.${formName}.submit();	
}
function doChangeSorPenjagaBaru() {
	document.${formName}.command.value = "maklumatPenjagaAdd";
	document.${formName}.command2.value = "doChangeSorPenjagaBaru";
	document.${formName}.action = ""; 
	document.${formName}.submit();	
}
//Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yang sah")
		return false
	}
return true
}

function isIc(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakBorangH(idfail,idobminor) {

	/*
		var a = "";
		var b = "";
		var c = "";
		var d = "";
		var x = "";
		
		if(total==1){
			var a = document.getElementById("1").value;
			x = a;
		}
		if(total==2){
			var a = document.getElementById("1").value;
			var b = document.getElementById("2").value;
			x = (a+" dan "+b);
		}
		if(total==3){
			var a = document.getElementById("1").value;
			var b = document.getElementById("2").value;
			var c = document.getElementById("3").value;
			x = (a+", "+b+" dan "+c);
		}
		if(total==4){
			var a = document.getElementById("1").value;
			var b = document.getElementById("2").value;
			var c = document.getElementById("3").value;
			var d = document.getElementById("4").value;
			x = (a+", "+b+", "+c+" dan "+d);
		}
	*/
		
	//var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idobminor="+idobminor+"&report=BorangH&flagReport=B";
	var url = "../servlet/ekptg.report.ppk.BorangHNotis?idfail="+idfail+"&idobminor="+idobminor+"&BorangH_Notis=Y";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
function displayHTML(field) 
{
	var inf = field.value;
	win = window.open('', 'popup', 'resizable=yes,scrollbars=yes,toolbar=no,status=no');
	win.document.write("" + inf + "");
}

</script>
