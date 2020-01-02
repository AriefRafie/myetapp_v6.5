
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

	#if($mode=="new")	
     #if($maklumat_Hakmilik_Salin.size()>0)
     
     
     
		
		#set($nama_negeriprojek=$maklumat_Hakmilik_Salin.nama_negeri)
		#set($nama_daerah=$maklumat_Hakmilik_Salin.nama_daerah)

		#set($txtSeksyen=$maklumat_Hakmilik_Salin.seksyen)
		#set($txtNoHakmilik=$maklumat_Hakmilik_Salin.no_hakmilik)				
		#set($txdTarikhLuput=$maklumat_Hakmilik_Salin.tarikh_luput)		
		#set($txdTarikhDaftar=$maklumat_Hakmilik_Salin.tarikh_daftar)				
		#set($txtBakiTempoh=$maklumat_Hakmilik_Salin.tempoh_luput)
		#set($txtNoSyit=$maklumat_Hakmilik_Salin.no_syit)
		#set($txtNoLot=$maklumat_Hakmilik_Salin.no_lot)
		#set($txtNoPT=$maklumat_Hakmilik_Salin.no_pt)	
		#set($txtLuasLotAmbil=$maklumat_Hakmilik_Salin.luas_ambil)
		#set($txtLuasLotAsal=$maklumat_Hakmilik_Salin.luas_lot)		
		#set($txtCatatan=$maklumat_Hakmilik_Salin.catatan)		
		#set($txtNoWartaRizab=$maklumat_Hakmilik_Salin.no_warta_rizab)		
		#set($txdTarikhWarta=$maklumat_Hakmilik_Salin.tarikh_warta_rizab)		
		#set($txtLain=$maklumat_Hakmilik_Salin.nama_lain_rizab)		
		#set($sorJenisRizab=$maklumat_Hakmilik_Salin.flag_jenis_rizab)
		#set($sorDropdownUnitAsal=$maklumat_Hakmilik_Salin.id_unitluaslot_convert)
		#set($sorDropdownUnitAmbil=$maklumat_Hakmilik_Salin.id_unitluasambil_convert)
		#set($txtLuasLotAsalSebelumConvert=$maklumat_Hakmilik_Salin.nama_luas_asal)
		#set($txtLuasLotAmbilSebelumConvert=$maklumat_Hakmilik_Salin.nama_luas_ambil)
		
		#set($txtLokasi=$maklumat_Hakmilik_Salin.lokasi)
		#set($txtSyaratNyata=$maklumat_Hakmilik_Salin.syarat_nyata)
		#set($txtSyaratKhas=$maklumat_Hakmilik_Salin.syarat_khas)		
		#set($txtSekatanKepentingan=$maklumat_Hakmilik_Salin.sekatan_kepentingan)
		#set($txtSekatanHak=$maklumat_Hakmilik_Salin.sekatan_hak)
        
        
        
        #set($showDropdownUnitAsal="yes")
        #set($showDropdownUnitAmbil="yes")
        
        #set($showFieldAsalBeforeConvert="yes")
        #set($showFieldAmbilBeforeConvert="yes")
        
        
         <script>
		 
		 /*
		 $jquery(document).ready(function()
		{
				convertNilaiAll('$!showBoxAsal2','$!showBoxAsal3','$!showBoxAmbil2','$!showBoxAmbil3');
				//convertNilaiAsal('$!showBoxAsal2','$!showBoxAsal3');
				//convertNilaiAmbil('$!showBoxAmbil2','$!showBoxAmbil3');
				
		});
		*/
		/*
		$jquery(document).ready(function()
		{
			
				convertNilaiAsal('$!showBoxAsal2','$!showBoxAsal3');
				convertNilaiAmbil('$!showBoxAmbil2','$!showBoxAmbil3');
				
				
		});
		*/
		
	
		</script>	
		
		
		#end
        
        
	<fieldset id="top">
	<legend><strong>Maklumat Hakmilik</strong></legend>	
		
		<br/>
		
		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="23%">Negeri</td>
				<td width="1%">:</td>
				<td width="75%">$!nama_negeriprojek</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				#if($showJajahan=="yes")
				<td>Jajahan</td>
				#else
				<td>Daerah</td>
				#end
				<td>:</td>
				<td>$!nama_daerah</td>
			</tr>
			#if($showJajahan=="yes")
			<tr>
				<td></td>
				<td>Daerah Penggawa</td>
				<td>:</td>
				<td>$!SelectDaerahPenggawa</td>
			</tr>
			#end
			<tr>
				<td><font color="red">*</font></td>
				<td>Bandar/Pekan/Mukim</td>
				<td>:</td>
				<td>$!selectMukim</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Seksyen</td>
				<td>:</td>
				<td><input type="text" name="txtSeksyen" id="txtSeksyen" value="$!txtSeksyen" size="10" maxlength="30" ></td>
			</tr>
		</table>
		
	</fieldset>
	
	
	<fieldset>	
		<table width="100%" border="0">
			<tr>
				<td width="1%"><font color="red">*</font></td>
				<td width="23%">Jenis Hakmilik</td>
				<td width="1%">:</td>
				<td width="75%">$!selectJenisHakmilik</td>
			</tr>	
			<tr>
				<td><font color="red">*</font></td>
				<td>No. Hakmilik</td>
				<td>:</td>
				<td><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="12" maxlength="50" ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Daftar</td>
				<td>:</td>
				<td><input type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" value="$!txdTarikhDaftar" size="12" maxlength="10" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhDaftar',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr>
			
			#if($showLuput=="yes")
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Luput</td>
				<td>:</td>
				<td><input type="text" name="txdTarikhLuput" id="txdTarikhLuput" value="$!txdTarikhLuput" size="12" maxlength="10" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLuput',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr>
			#end
			
			<tr>
				<td>&nbsp;</td>
				<td>Baki Tempoh (Tahun)</td>
				<td>:</td>
				<td><input type="text" name="txtBakiTempoh" id="txtBakiTempoh" value="$!txtBakiTempoh" size="12" maxlength="10" ></td>
			</tr>
			<tr>
				<td><font color="red">*</font></td>
				<td>No. Syit</td>
				<td>:</td>
				<td><input type="text" name="txtNoSyit" id="txtNoSyit" value="$!txtNoSyit" size="12" maxlength="40" ></td>
			</tr>
			
			<tr>
				<td><font color="red">*</font></td>
            	<td>No. PT</td>
            	<td>:</td>
            	<td>$!selectKodLot</td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>&nbsp;</td>
            	<td>&nbsp;</td>
                <td><input type="text" name="txtNoPT" id="txtNoPT" value="$!txtNoPT" size="12" maxlength="20" ></td>
			</tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Lot</td>
            	<td>:</td>
                <td><input type="text" name="txtNoLot" id="txtNoLot" value="$!txtNoLot" size="12" maxlength="20" onblur="this.value=removeSpaces(this.value)" >
                 <a href="javascript:popupCarianHakmilikSalin('$id_permohonan','skrin_hakmilik_sementara','$id_daerah')"><font color="blue">>> SALIN MAKLUMAT HAKMILIK/LOT</font></a>
    
                </td>
			</tr>
			
			<tr>
				<td><font color="red">*</font></td>
				<td>Kategori Tanah</td>
				<td>:</td>
				<td>$!selectKategoriTanah</td>
			</tr>
			<tr>
				<td><font color="red">*</font></td>
				<td>Unit Luas Asal</td>
				<td>:</td>
				<td>$!selectUnitLuasLot</td>
			</tr>
			<tr>
				<td valign="top"><font color="red">*</font></td>
				<td valign="top">Luas Asal</td>
				<td valign="top">:</td>
				<td>
					
					#if($showFieldAsalBeforeConvert=="yes")
					<input type="text" name="txtLuasLotAsalSebelumConvert" id="txtLuasLotAsalSebelumConvert" size="50" value="$!txtLuasLotAsalSebelumConvert" maxlength="100" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /><p/>
					#end
					
					<input type="text" name="txtLuasLotAsal" id="txtLuasLotAsal" size="15" value="$!txtLuasLotAsal" maxlength="20" onkeyup="validateNilai(this,this.value);" />
					#if($showBoxAsal2=="yes" || $showBoxAsal3=="yes")<input type="text" name="txtLuasLotAsal2" id="txtLuasLotAsal2" size="15" value="$!txtLuasLotAsal2" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
					#if($showBoxAsal3=="yes")<input type="text" name="txtLuasLotAsal3" id="txtLuasLotAsal3" size="15" value="$!txtLuasLotAsal3" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
					
					#if($showDropdownUnitAsal=="yes")
					<select name="sorDropdownUnitAsal" style="width:132px" onchange="onchangeUnitAsal()">
      		
      					#if($sorDropdownUnitAsal=="1")
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#elseif($sorDropdownUnitAsal=="2")
      					<option value="2">METER PERSEGI</option>
      					<option value="1">HEKTAR</option>
      					#else
      					<option value="">SILA PILIH</option>
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#end

					</select>
					#end
					
				</td>
			</tr>
			
			#if($showButtonConvertAsal=="yes" || $showFieldAsalBeforeConvert=="yes")
			<tr>
				<td colspan="3">&nbsp;</td>
				<td>
					#if($showButtonConvertAsal=="yes")
					<input type="button" name="cmdConvert" value ="Convert" onClick="javascript:convertNilaiAsal('$!showBoxAsal2','$!showBoxAsal3')">
					#end
					#if($showFieldAsalBeforeConvert=="yes")
					<input type="button" name="cmdClear" value ="Kosongkan" onClick="javascript:clearConvertAsal()">
					#end
				</td>
			</tr>
			#end
			
			<tr>
				<td><font color="red">*</font></td>
				<td>Unit Luas Diguna/Disewa</td>
			  <td>:</td>
				<td>$!selectUnitLuasAmbil</td>
			</tr>
			<tr>
				<td valign="top"><font color="red">*</font></td>
				<td valign="top">Luas Diguna/Disewa</td>
			  <td valign="top">:</td>
				<td>
					
					#if($showFieldAmbilBeforeConvert=="yes")
					<input type="text" name="txtLuasLotAmbilSebelumConvert" id="txtLuasLotAmbilSebelumConvert" size="50" value="$!txtLuasLotAmbilSebelumConvert" maxlength="100" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /><p/>
					#end
					
					<input type="text" name="txtLuasLotAmbil" id="txtLuasLotAmbil" size="15" value="$!txtLuasLotAmbil" maxlength="20" onkeyup="validateNilai(this,this.value);" />
					#if($showBoxAmbil2=="yes" || $showBoxAmbil3=="yes")<input type="text" name="txtLuasLotAmbil2" id="txtLuasLotAmbil2" size="15" value="$!txtLuasLotAmbil2" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
					#if($showBoxAmbil3=="yes")<input type="text" name="txtLuasLotAmbil3" id="txtLuasLotAmbil3" size="15" value="$!txtLuasLotAmbil3" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
				
					#if($showDropdownUnitAmbil=="yes")
					<select name="sorDropdownUnitAmbil" style="width:132px" onchange="onchangeUnitAmbil()">
      		
      					#if($sorDropdownUnitAmbil=="1")
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#elseif($sorDropdownUnitAmbil=="2")
      					<option value="2">METER PERSEGI</option>
      					<option value="1">HEKTAR</option>
      					#else
      					<option value="">SILA PILIH</option>
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#end

					</select>
					#end
					
				</td>
			</tr>		
			
			#if($showButtonConvertAmbil=="yes" || $showFieldAmbilBeforeConvert=="yes")
			<tr>
				<td colspan="3">&nbsp;</td>
				<td>
					#if($showButtonConvertAmbil=="yes")
					<input type="button" name="cmdConvert" value ="Convert" onClick="javascript:convertNilaiAmbil('$!showBoxAmbil2','$!showBoxAmbil3')">
					#end
					#if($showFieldAmbilBeforeConvert=="yes")
					<input type="button" name="cmdClear" value ="Kosongkan" onClick="javascript:clearConvertAmbil()">
					#end
				</td>
			</tr>
			#end
			
        </table>
     </fieldset>	
     
     <fieldset>
       <table width="100%" border="0">
       		
       		#set($checkSorA="")
       		#set($checkSorB="")
       		#set($checkSorC="")
       		#set($checkSorD="")
       		#set($checkSorE="")
       
       		#if($sorJenisRizab=="1")
       			#set($checkSorA="checked")
       		#elseif($sorJenisRizab=="2")
       			#set($checkSorB="checked")
       		#elseif($sorJenisRizab=="3")
       			#set($checkSorC="checked")
       		#elseif($sorJenisRizab=="4")
       			#set($checkSorD="checked")
       		#elseif($sorJenisRizab=="5")
       			#set($checkSorE="checked")
                #set($showLainLain="yes")
       		#end
       		
       
       		<tr>
       			<td width="1%">&nbsp;</td>
            	<td valign="top" width="23%">Jenis Rizab</td>
            	<td valign="top" width="1%">:</td>
            	<td width="75%">
  					<input type="radio" name="sorJenisRizab" id="sorRizabMelayu" $checkSorA value="1" onchange="doOnchange()">Kawasan Rizab Melayu<br/>
  					<input type="radio" name="sorJenisRizab" id="sorOrangAsli" $checkSorB value="2" onchange="doOnchange()">Kawasan Orang Asli<br/>
  					<input type="radio" name="sorJenisRizab" id="sorRizabOrangAsli" $checkSorC value="3" onchange="doOnchange()">Rizab Orang Asli<br/>
  					<input type="radio" name="sorJenisRizab" id="sorBerkelompok" $checkSorD value="4" onchange="doOnchange()">Kawasan Penempatan Berkelompok<br/>
  					<input type="radio" name="sorJenisRizab" id="sorLain" $checkSorE value="5" onchange="doOnchange()">Lain - lain
  					#if($showLainLain=="yes")
  					&nbsp;: <input type="text" name="txtLain" size="25" value="$!txtLain" maxlength="80" id="txtLain" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();">
  					#end
  				</td>
            </tr>
            
            #if($showWarta=="yes")
            <tr>
            	<td>&nbsp;</td>
            	<td>No.Warta</td>
            	<td>:</td>
            	<td><input type="text" name="txtNoWartaRizab" size="20" value="$!txtNoWartaRizab" maxlength="20" id="txtNoWartaRizab" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
            </tr>
            <tr>
            	<td>&nbsp;</td>
        		<td>Tarikh Warta</td>
        		<td>:</td>
            	<td><input name="txdTarikhWarta" id="txdTarikhWarta" size="12" type="text" value="$!txdTarikhWarta" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhWarta',false,'dmy');">&nbsp;$!frmtdate</td>
        	</tr>
        	#end
        	
       </table>
     </fieldset>
     
     <fieldset>       
        <table width="100%" border="0">    
			<tr>
				<td width="1%">&nbsp;</td>
            	<td valign="top" width="23%">Catatan</td>
            	<td valign="top" width="1%">:</td>
            	<td width="75%"><textarea name="txtCatatan" id="txtCatatan" cols="70%" rows="10"  onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" >$!txtCatatan</textarea></td>
            </tr>
            <tr>
        		<td colspan="3">&nbsp;</td>
             	<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="4000"></td>
           	</tr> 
		</table>
	</fieldset>	
    
    <!-- elly alter -->

<fieldset>       
   <table width="100%" border="0">    
	   <tr>
		   <td width="1%">&nbsp;</td>
       	   <td valign="top" width="23%">Tempoh Penggunaan</td>
       	   <td valign="top" width="1%">:</td>
   	     <td width="75%"><input type="text" name="txtTempoh" id="txtTempoh" value="$!txtTempoh" size="5" />
       	     <select name="socUnitTempoh" id="socUnitTempoh" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" >
       	              
			#if($unit_tempoh=="1")                                                       
       	       <option value="1">Bulan</option>
               <option value="2">Tahun</option>      	                      
            #elseif($unit_tempoh=="2")                                                   	           
       	       <option value="2">Tahun</option>
               <option value="1">Bulan</option>	                         
	        #else                                                         
       	       <option value="1">Bulan</option>
               <option value="2">Tahun</option>       	                   	        	          
            #end 
                      	    
   	       </select>   	       
           
           </td>
       </tr>
	   <tr>
		   <td width="1%">&nbsp;</td>
       	   <td valign="top" width="23%">Tarikh Mula</td>
       	   <td valign="top" width="1%">:</td>
       	   <td width="75%"> <input type="text" name="txdTkhMula" id="txdTkhMula" value="$!txdTkhMula" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);"  />&nbsp;<img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhMula',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
           
           </td>
       </tr> 
	   <tr>
		   <td width="1%">&nbsp;</td>
       	   <td valign="top" width="23%">Tarikh Akhir</td>
       	   <td valign="top" width="1%">:</td>
       	   <td width="75%"> <input type="text" name="txdTkhAkhir" id="txdTkhAkhir" value="$!txdTkhAkhir" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);"  />&nbsp;<img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhAkhir',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
           
           </td>
       </tr>        
   </table>
</fieldset>	
    
<!-- end elly alteres -->        
    
    
    
	#end

	
	
	#if($mode=="view")

	#if($onchangeHM=="no")
		#foreach($data in $dataMaklumatTanah)
		
		#set($nama_negeriprojek=$data.nama_negeri)
		#set($nama_daerah=$data.nama_daerah)

		#set($txtSeksyen=$data.seksyen)
		#set($txtNoHakmilik=$data.no_hakmilik)				
		#set($txdTarikhLuput=$data.tarikh_luput)		
		#set($txdTarikhDaftar=$data.tarikh_daftar)				
		#set($txtBakiTempoh=$data.tempoh_luput)
		#set($txtNoSyit=$data.no_syit)
		#set($txtNoLot=$data.no_lot)
		#set($txtNoPT=$data.no_pt)	
		#set($txtLuasLotAmbil=$data.luas_ambil)
		#set($txtLuasLotAsal=$data.luas_lot)		
		#set($txtCatatan=$data.catatan)		
		#set($txtNoWartaRizab=$data.no_warta_rizab)		
		#set($txdTarikhWarta=$data.tarikh_warta_rizab)		
		#set($txtLain=$data.nama_lain_rizab)		
		#set($sorJenisRizab=$data.flag_jenis_rizab)
		#set($sorDropdownUnitAsal=$data.id_unitluaslot_convert)
		#set($sorDropdownUnitAmbil=$data.id_unitluasambil_convert)
		#set($txtLuasLotAsalSebelumConvert=$data.nama_luas_asal)
		#set($txtLuasLotAmbilSebelumConvert=$data.nama_luas_ambil)
        
        #set($txtTempoh=$data.tempoh_Pendudukan)
		#set($unit_Tempoh=$data.unit_Tempoh)
        #set($txdTkhMula=$data.tarikh_mula)
        #set($txdTkhAkhir=$data.tarikh_akhir)
        
		#end
	#end
	
	
		#if($isEdit=="no")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#set($disability1 = "disabled")
		#set($M = "")
		#else
		#set($M = "*")
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
		#end
		
	<fieldset id="top">
	<legend><strong>Maklumat Hakmilik</strong></legend>	
		
		<br/>
		
		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="23%">Negeri</td>
				<td width="1%">:</td>
				<td width="75%">$!nama_negeriprojek</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				#if($showJajahan=="yes")
				<td>Jajahan</td>
				#else
				<td>Daerah</td>
				#end
				<td>:</td>
				<td>$!nama_daerah</td>
			</tr>
			#if($showJajahan=="yes")
			<tr>
				<td></td>
				<td>Daerah Penggawa</td>
				<td>:</td>
				<td>$!SelectDaerahPenggawa</td>
			</tr>
			#end
			<tr>
				<td><font color="red">$!M</font></td>
				<td>Bandar/Pekan/Mukim</td>
				<td>:</td>
				<td>$!selectMukim</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Seksyen</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtSeksyen" id="txtSeksyen" value="$!txtSeksyen" size="10" maxlength="30" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
			</tr>
		</table>
		
	</fieldset>
	
	
	<fieldset>	
		<table width="100%" border="0">
			<tr>
				<td width="1%"><font color="red">$!M</font></td>
				<td width="23%">Jenis Hakmilik</td>
				<td width="1%">:</td>
				<td width="75%">$!selectJenisHakmilik</td>
			</tr>	
			<tr>
				<td><font color="red">$!M</font></td>
				<td>No. Hakmilik</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="12" maxlength="50" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Daftar</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" value="$!txdTarikhDaftar" size="12" maxlength="10" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhDaftar',false,'dmy');">&nbsp;$!frmtdate#end</td>
			</tr>
			
			#if($showLuput=="yes")
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Luput</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txdTarikhLuput" id="txdTarikhLuput" value="$!txdTarikhLuput" size="12" maxlength="10" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLuput',false,'dmy');">&nbsp;$!frmtdate#end</td>
			</tr>
			#end
			
			<tr>
				<td>&nbsp;</td>
				<td>Baki Tempoh (Tahun)</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtBakiTempoh" id="txtBakiTempoh" value="$!txtBakiTempoh" size="12" maxlength="10" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
			</tr>
			<tr>
				<td><font color="red">$!M</font></td>
				<td>No. Syit</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtNoSyit" id="txtNoSyit" value="$!txtNoSyit" size="12" maxlength="40" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
            	<td>QT</td>
            	<td>:</td>
            	<td>$!selectKodLot</td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>&nbsp;</td>
            	<td>&nbsp;</td>
                <td><input $disability $disabilityx type="text" name="txtNoPT" id="txtNoPT" value="$!txtNoPT" size="12" maxlength="20" onblur="this.value=this.value.toUpperCase();this.value=removeSpaces(this.value)" style="text-transform:uppercase;" ></td>
			</tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>FT</td>
            	<td>:</td>
                <td><input $disability $disabilityx type="text" name="txtNoLot" id="txtNoLot" value="$!txtNoLot" size="12" maxlength="20" onblur="this.value=this.value.toUpperCase();this.value=removeSpaces(this.value)" style="text-transform:uppercase;" ></td>
			</tr>
			
			<tr>
				<td><font color="red">$!M</font></td>
				<td>Kategori Tanah</td>
				<td>:</td>
				<td>$!selectKategoriTanah</td>
			</tr>
			<tr>
				<td><font color="red">$!M</font></td>
				<td>Unit Luas Asal</td>
				<td>:</td>
				<td>$!selectUnitLuasLot</td>
			</tr>
			<tr>
				<td valign="top"><font color="red">$!M</font></td>
				<td valign="top">Luas Asal</td>
				<td valign="top">:</td>
				<td>
					
					#if($showFieldAsalBeforeConvert=="yes")
					<input type="text" name="txtLuasLotAsalSebelumConvert" $disability $disabilityx id="txtLuasLotAsalSebelumConvert" size="50" value="$!txtLuasLotAsalSebelumConvert" maxlength="100" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /><p/>
					#end
					
					<input type="text" $disability $disabilityx name="txtLuasLotAsal" id="txtLuasLotAsal" size="15" value="$!txtLuasLotAsal" maxlength="20" onkeyup="validateNilai(this,this.value);" />
					#if($showBoxAsal2=="yes" || $showBoxAsal3=="yes")<input type="text" name="txtLuasLotAsal2" id="txtLuasLotAsal2" size="15" value="$!txtLuasLotAsal2" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
					#if($showBoxAsal3=="yes")<input type="text" name="txtLuasLotAsal3" id="txtLuasLotAsal3" size="15" value="$!txtLuasLotAsal3" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
					
					#if($showDropdownUnitAsal=="yes")
					<select $disability1 $disabilityx name="sorDropdownUnitAsal" style="width:132px" onchange="onchangeUnitAsalUpdate()">
      		
      					#if($sorDropdownUnitAsal=="1")
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#elseif($sorDropdownUnitAsal=="2")
      					<option value="2">METER PERSEGI</option>
      					<option value="1">HEKTAR</option>
      					#else
      					<option value="">SILA PILIH</option>
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#end

					</select>
					#end
					
				</td>
			</tr>
			
			
			#if($isEdit=="yes" && ($showButtonConvertAsal=="yes" || $showFieldAsalBeforeConvert=="yes"))
			<tr>
				<td colspan="3">&nbsp;</td>
				<td>
					#if($showButtonConvertAsal=="yes")
					<input type="button" name="cmdConvert" value ="Convert" onClick="javascript:convertNilaiAsalUpdate('$!showBoxAsal2','$!showBoxAsal3')">
					#end
					#if($showFieldAsalBeforeConvert=="yes")
					<input type="button" name="cmdClear" value ="Kosongkan" onClick="javascript:clearConvertAsalUpdate()">
					#end
				</td>
			</tr>
			#end
			
			<tr>
				<td><font color="red">$!M</font></td>
				<td>Unit Luas Ambil</td>
				<td>:</td>
				<td>$!selectUnitLuasAmbil</td>
			</tr>
			<tr>
				<td valign="top"><font color="red">$!M</font></td>
				<td valign="top">Luas Diguna/Disewa</td>
				<td valign="top">:</td>
				<td>
					
					#if($showFieldAmbilBeforeConvert=="yes")
					<input type="text" $disability $disabilityx name="txtLuasLotAmbilSebelumConvert" id="txtLuasLotAmbilSebelumConvert" size="50" value="$!txtLuasLotAmbilSebelumConvert" maxlength="100" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /><p/>
					#end
					
					<input $disability $disabilityx type="text" name="txtLuasLotAmbil" id="txtLuasLotAmbil" size="15" value="$!txtLuasLotAmbil" maxlength="20" onkeyup="validateNilai(this,this.value);" />
					#if($showBoxAmbil2=="yes" || $showBoxAmbil3=="yes")<input type="text" name="txtLuasLotAmbil2" id="txtLuasLotAmbil2" size="15" value="$!txtLuasLotAmbil2" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
					#if($showBoxAmbil3=="yes")<input type="text" name="txtLuasLotAmbil3" id="txtLuasLotAmbil3" size="15" value="$!txtLuasLotAmbil3" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
				
					#if($showDropdownUnitAmbil=="yes")
					<select $disability1 $disabilityx name="sorDropdownUnitAmbil" style="width:132px" onchange="onchangeUnitAmbilUpdate()">
      		
      					#if($sorDropdownUnitAmbil=="1")
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#elseif($sorDropdownUnitAmbil=="2")
      					<option value="2">METER PERSEGI</option>
      					<option value="1">HEKTAR</option>
      					#else
      					<option value="">SILA PILIH</option>
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#end

					</select>
					#end
					
				</td>
			</tr>		
			
			#if($isEdit=="yes" && ($showButtonConvertAmbil=="yes" || $showFieldAmbilBeforeConvert=="yes"))
			<tr>
				<td colspan="3">&nbsp;</td>
				<td>
					#if($showButtonConvertAmbil=="yes")
					<input type="button" name="cmdConvert" value ="Convert" onClick="javascript:convertNilaiAmbilUpdate('$!showBoxAmbil2','$!showBoxAmbil3')">
					#end
					#if($showFieldAmbilBeforeConvert=="yes")
					<input type="button" name="cmdClear" value ="Kosongkan" onClick="javascript:clearConvertAmbilUpdate()">
					#end
				</td>
			</tr>
			#end
			
        </table>
     </fieldset>	
     
     <fieldset>
       <table width="100%" border="0">
       		
       		#set($checkSorA="")
       		#set($checkSorB="")
       		#set($checkSorC="")
       		#set($checkSorD="")
       		#set($checkSorE="")
       
       		#if($sorJenisRizab=="1")
       			#set($checkSorA="checked")
       		#elseif($sorJenisRizab=="2")
       			#set($checkSorB="checked")
       		#elseif($sorJenisRizab=="3")
       			#set($checkSorC="checked")
       		#elseif($sorJenisRizab=="4")
       			#set($checkSorD="checked")
       		#elseif($sorJenisRizab=="5")
       			#set($checkSorE="checked")
       		#end
       		
       
       		<tr>
       			<td width="1%">&nbsp;</td>
            	<td valign="top" width="23%">Jenis Rizab</td>
            	<td valign="top" width="1%">:</td>
            	<td width="75%">
  					<input type="radio" $disability1 name="sorJenisRizab" id="sorRizabMelayu" $checkSorA value="1" onchange="doOnchangeUpdate()">Kawasan Rizab Melayu<br/>
  					<input type="radio" $disability1 name="sorJenisRizab" id="sorOrangAsli" $checkSorB value="2" onchange="doOnchangeUpdate()">Kawasan Orang Asli<br/>
  					<input type="radio" $disability1 name="sorJenisRizab" id="sorRizabOrangAsli" $checkSorC value="3" onchange="doOnchangeUpdate()">Rizab Orang Asli<br/>
  					<input type="radio" $disability1 name="sorJenisRizab" id="sorBerkelompok" $checkSorD value="4" onchange="doOnchangeUpdate()">Kawasan Penempatan Berkelompok<br/>
  					<input type="radio" $disability1 name="sorJenisRizab" id="sorLain" $checkSorE value="5" onchange="doOnchangeUpdate()">Lain - lain
  					#if($showLainLain=="yes")
  					&nbsp;: <input type="text" $disability $disabilityx name="txtLain" size="25" value="$!txtLain" maxlength="80" id="txtLain" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();">
  					#end
  				</td>
            </tr>
            
            #if($showWarta=="yes")
            <tr>
            	<td>&nbsp;</td>
            	<td>No.Warta</td>
            	<td>:</td>
            	<td><input type="text" $disability $disabilityx name="txtNoWartaRizab" size="20" value="$!txtNoWartaRizab" maxlength="20" id="txtNoWartaRizab" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
            </tr>
            <tr>
            	<td>&nbsp;</td>
        		<td>Tarikh Warta</td>
        		<td>:</td>
            	<td><input $disability $disabilityx name="txdTarikhWarta" id="txdTarikhWarta" size="12" type="text" value="$!txdTarikhWarta" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhWarta',false,'dmy');">&nbsp;$!frmtdate#end</td>
        	</tr>
        	#end
        	
       </table>
     </fieldset>
     
     <fieldset>       
        <table width="100%" border="0">    
			<tr>
				<td width="1%">&nbsp;</td>
            	<td valign="top" width="23%">Catatan</td>
            	<td valign="top" width="1%">:</td>
            	<td width="75%"><textarea $disability $disabilityx name="txtCatatan" id="txtCatatan" cols="70%" rows="10"  onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" >$!txtCatatan</textarea></td>
            </tr>
            #if($isEdit=="yes")
            <tr>
        		<td colspan="3">&nbsp;</td>
             	<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="4000"></td>
           	</tr> 
           	#end
		</table>
	</fieldset>	
    
<!-- elly alter -->

<fieldset>       
   <table width="100%" border="0">    
	   <tr>
		   <td width="1%">&nbsp;</td>
       	   <td valign="top" width="23%">Tempoh</td>
       	   <td valign="top" width="1%">:</td>
   	     	<td width="75%"><input type="text" name="txtTempoh" id="txtTempoh" value="$!txtTempoh" size="8" $disability $disabilityx  />
       	     <select name="socUnitTempoh" id="socUnitTempoh" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" $disability1 $disabilityx >
       	              
			#if($unit_tempoh=="1")                                                       
       	       <option value="1">Bulan</option>
               <option value="2">Tahun</option>      	                      
            #elseif($unit_tempoh=="2")                                                   	           
       	       <option value="2">Tahun</option>
               <option value="1">Bulan</option>	                         
	        #else                                                         
       	       <option value="1">Bulan</option>
               <option value="2">Tahun</option>       	                   	        	          
            #end 
                      	    
   	       </select>   	       
           
           </td>
       </tr>
	   <tr>
		   <td width="1%">&nbsp;</td>
       	   <td valign="top" width="23%">Tarikh Mula</td>
       	   <td valign="top" width="1%">:</td>
       	   <td width="75%"> <input type="text" name="txdTkhMula" id="txdTkhMula" value="$!txdTkhMula" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $disability $disabilityx  />&nbsp;<img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhMula',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
           
           </td>
       </tr> 
	   <tr>
		   <td width="1%">&nbsp;</td>
       	   <td valign="top" width="23%">Tarikh Akhir</td>
       	   <td valign="top" width="1%">:</td>
       	   <td width="75%"> <input type="text" name="txdTkhAkhir" id="txdTkhAkhir" value="$!txdTkhAkhir" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $disability $disabilityx />&nbsp;<img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhAkhir',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
           
           </td>
       </tr>        
   </table>
</fieldset>	
    
<!-- end elly alter -->        
	#end
	
	

	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($mode=="new")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanHM('$!id_permohonan','$!id_hakmilik','$!mode')">
				#end
				
				#if($mode=="view")
					#if($isEdit=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniHM('$!id_hakmilik')">
					<input type="button" name="cmdHapus" value="Hapus" onClick="hapusHM('$!id_hakmilik')">
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanHM('$!id_permohonan','$!id_hakmilik','$!mode')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_hakmilik')">
					#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali('$!id_permohonan')">
			</td>
		</tr>
	</table>


<table width="100%" border="0"><tr><td>&nbsp;</td></tr></table>  
 
	<fieldset id="bottom">
	<legend><strong>Senarai Maklumat Hakmilik</strong>
    
    #if($mode=="view")
              <input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahHM();">
    
    					#end
    </legend>
      <a href="javascript:popupCarianHakmilik('$id_permohonan','skrin_hakmilik_sementara')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
   
			<!--
			<table width="100%" border="0">   
                	<tr>
                		#if($mode=="view")
                    	<td width="30%" align="left"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahHM();"></td>
    					#else
    					<td width="30%">&nbsp;</td>
    					#end
    					<td width="70%" align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot2" value="$!carianNoLot2" maxlength="20" size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ><a href="javascript:cariLOT('$!id_permohonan','$!mode','$!id_hakmilik')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan','$!mode','$!id_hakmilik')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    			
    		#if($saiz_listTanah > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
                  	<td><b>No.Hakmilik</b></td>
                  	<td><b>FT</b></td>    
                  	<td><b>QT</b></td>           
                  	<td><b>Mukim/Pekan/Bandar</b></td>
            		<td><b>Keluasan</b></td> 
        		</tr>
        		
        		#if($saiz_listTanah!=0)
                    #foreach($listTanah in $listMaklumatTanah)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listTanah.bil</td>
                	<td class="$row"><a href="javascript:viewHM('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.no_hakmilik</font></a></td>
                	<td class="$row">$!listTanah.no_lot</td>          
                	<td class="$row">$!listTanah.kod_lot$!listTanah.no_pt</td>     
                	<td class="$row">$!listTanah.nama_mukim</td>
                	<td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listTanah > 5)
                </div>
            #end
	-->
	</fieldset>

<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">
<input type="hidden" name="command5">

<!-- Id daerah & Id projek negeri -->
<input type="hidden" name="id_daerah" value="$!id_daerah">
<input type="hidden" name="id_negeriprojek" value="$!id_negeriprojek">

<!-- validation convert -->
<input type="hidden" name="showFieldAsalBeforeConvert" value="$!showFieldAsalBeforeConvert">
<input type="hidden" name="showFieldAmbilBeforeConvert" value="$!showFieldAmbilBeforeConvert">
<input type="hidden" name="showButtonConvertAsal" value="$!showButtonConvertAsal">
<input type="hidden" name="showButtonConvertAmbil" value="$!showButtonConvertAmbil">
<input type="hidden" name="showBoxAmbil2" value="$!showBoxAmbil2">
<input type="hidden" name="showBoxAmbil3" value="$!showBoxAmbil3">
<input type="hidden" name="showBoxAsal2" value="$!showBoxAsal2">
<input type="hidden" name="showBoxAsal3" value="$!showBoxAsal3">

<input type="hidden" name="id_hakmilik_salin" id="id_hakmilik_salin" value="$!id_hakmilik_salin" />


<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<input type="hidden" name="tabId">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function convertNilaiAll(boxAsal2,boxAsal3,boxAmbil2,boxAmbil3){

	var socUnitAsal = document.${formName}.socUnitLuasLot.value;
	var socUnitLuasAmbil = document.${formName}.socUnitLuasAmbil.value;

	if(document.${formName}.socKategoriTanah.value == ""){
		
		alert("Sila pilih \"Kategori Tanah\" terlebih dahulu.");
		document.${formName}.socKategoriTanah.focus(); 
		return;		
		
	}else 
	if(socUnitAsal!="" && document.${formName}.txtLuasLotAsal.value == ""){	

		alert("Sila masukkan \"Luas Lot Asas\" terlebih dahulu.");
		document.${formName}.txtLuasLotAsal.focus(); 
		return;
		
	}else 
	if(boxAsal2=="yes" && (document.${formName}.txtLuasLotAsal.value == "" || document.${formName}.txtLuasLotAsal2.value == "")){	

		alert("Sila lengkapkan \"Luas Lot Asas\" terlebih dahulu.");
		document.${formName}.txtLuasLotAsal2.focus(); 
		return;
			
	}else 
	if(boxAsal3=="yes" && (document.${formName}.txtLuasLotAsal.value == "" || document.${formName}.txtLuasLotAsal2.value == "" 
	   || document.${formName}.txtLuasLotAsal3.value == "")){	

		alert("Sila lengkapkan \"Luas Lot Asas\" terlebih dahulu.");
		document.${formName}.txtLuasLotAsal3.focus(); 
		return;
				
	}
	else 
	if(socUnitLuasAmbil!="" && document.${formName}.txtLuasLotAmbil.value == ""){	

		alert("Sila masukkan \"Luas Lot Ambil\" terlebih dahulu.");
		document.${formName}.txtLuasLotAmbil.focus(); 
		return;
			
	}else 
	if(boxAmbil2=="yes" && (document.${formName}.txtLuasLotAmbil.value == "" || document.${formName}.txtLuasLotAmbil2.value == "")){	

		alert("Sila lengkapkan \"Luas Lot Ambil\" terlebih dahulu.");
		document.${formName}.txtLuasLotAmbil2.focus(); 
		return;
				
	}else 
	if(boxAmbil3=="yes" && (document.${formName}.txtLuasLotAmbil.value == "" || document.${formName}.txtLuasLotAmbil2.value == "" 
	   || document.${formName}.txtLuasLotAmbil3.value == "")){	

		alert("Sila lengkapkan \"Luas Lot Ambil\" terlebih dahulu.");
		document.${formName}.txtLuasLotAmbil3.focus(); 
		return;
						
	}
	else{
		
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.command.value = "tambahHakmilik";
		document.${formName}.command2.value = "doOnchange";
		document.${formName}.command3.value = "onchangeUnitLuasAll";
		document.${formName}.command4.value = "convertNilaiAll";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
		document.${formName}.submit();
		
		

	}//close else
	
}//close convertNilai As
function salin_hakmilik(id_permohonan,id_hakmilik_salin)
{
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "salinHakmilik";
	document.${formName}.id_permohonan.value = id_permohonan;	
	document.${formName}.id_hakmilik_salin.value = id_hakmilik_salin;	
	//document.${formName}.subminor_command.value = "salin_hakmilik";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	//document.${formName}.location.value = "maklumat_pb";
	//document.${formName}.point.value = "socJenisPB";
	//document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();	
}

function popupCarianHakmilikSalin(id_permohonan,flag_skrin,id_daerah)
{
	var no_lot = document.${formName}.txtNoLot.value;	
	var no_hakmilik = document.${formName}.txtNoHakmilik.value;	
	var id_mukim = document.${formName}.socMukim.value;	
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupSalinPBHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin+"&NO_HAKMILIK="+no_hakmilik+"&NO_LOT="+no_lot+"&ID_MUKIM="+id_mukim+"&ID_DAERAH="+id_daerah;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function onchangeUnitAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.command5.value = "onchangeUnitAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function clearConvertAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.command5.value = "clearConvertAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function convertNilaiAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.command5.value = "convertNilaiAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function onchangeUnitLuasAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function onchangeUnitAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.command5.value = "onchangeUnitAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function clearConvertAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.command5.value = "clearConvertAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function convertNilaiAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.command5.value = "convertNilaiAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function onchangeUnitLuasAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function doOnchangeUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function batalKemaskini(id_hakmilik){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function kemaskiniHM(id_hakmilik){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function tambahHM(){
	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function hapusHM(id_hakmilik){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "hapusHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function viewHM(id_hakmilik){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function onchangeUnitAmbil(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHakmilik";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAmbil";
	document.${formName}.command4.value = "onchangeUnitAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function onchangeUnitAsal(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHakmilik";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAsal";
	document.${formName}.command4.value = "onchangeUnitAsal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function onchangeUnitLuasAsal(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHakmilik";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAsal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function onchangeUnitLuasAmbil(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHakmilik";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function clearConvertAsal(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHakmilik";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAsal";
	document.${formName}.command4.value = "clearConvertAsal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function clearConvertAmbil(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHakmilik";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAmbil";
	document.${formName}.command4.value = "clearConvertAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function doOnchange(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHakmilik";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function kembali(idpermohonan){
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.tabId.value = "0";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function simpanHM(id_permohonan,id_hakmilik,mode){

	var dat2=document.${formName}.txdTarikhDaftar

	document.${formName}.ScreenLocation.value = "top";

	if(document.${formName}.socMukim.value == ""){
		alert("Sila pilih \"Mukim\" terlebih dahulu.");
		document.${formName}.socMukim.focus(); 
		return;
	}
	else if(document.${formName}.socJenisHakmilik.value == ""){
		alert("Sila pilih \"Jenis Hakmilik\" terlebih dahulu.");
  		document.${formName}.socJenisHakmilik.focus(); 
		return;
	}
	else
	if(document.${formName}.txtNoHakmilik.value == ""){
		alert("Sila masukkan \"No Hakmilik\" terlebih dahulu.");
	  	document.${formName}.txtNoHakmilik.focus(); 
		return;
	}
	else 
	if (dat2.value!="" && isDate(dat2.value)==false){
		dat2.focus()
		return;
	}
	else
	if(document.${formName}.txtNoSyit.value == ""){
		alert("Sila masukkan \"No Syit\" terlebih dahulu.");
	    document.${formName}.txtNoSyit.focus(); 
		return;
	}
	else if((document.${formName}.socKodLot.value != "" && document.${formName}.txtNoPT.value == "") || (document.${formName}.socKodLot.value == "" && document.${formName}.txtNoPT.value != "")){
		alert("Sila lengkapkan maklumat \"QT\" terlebih dahulu.");
  		document.${formName}.socKodLot.focus(); 
		return;
	}
	else if(document.${formName}.txtNoLot.value == "" && document.${formName}.txtNoPT.value == ""){
		alert("Sila masukkan salah satu \"QT atau FT\" terlebih dahulu.");
  		document.${formName}.txtNoLot.focus(); 
		return;
	}
	else
	if(document.${formName}.socKategoriTanah.value == ""){
		alert("Sila pilih \"Kategori Tanah\" terlebih dahulu.");
		document.${formName}.socKategoriTanah.focus(); 
		return;
	}
	else
	if(document.${formName}.socUnitLuasLot.value == ""){
		alert("Sila pilih \"Unit Luas Asal\" terlebih dahulu.");
		document.${formName}.socUnitLuasLot.focus(); 
		return;
	}
	else
	if(document.${formName}.txtLuasLotAsal.value == ""){
		alert("Sila masukkan \"Luas Asal\" terlebih dahulu.");
		document.${formName}.txtLuasLotAsal.focus(); 
		return;
	}
	else
	if(document.${formName}.socUnitLuasAmbil.value == ""){
		alert("Sila pilih \"Unit Luas Ambil\" terlebih dahulu.");
		document.${formName}.socUnitLuasAmbil.focus(); 
		return;
	}
	else
	if(document.${formName}.txtLuasLotAmbil.value == ""){
		alert("Sila masukkan \"Luas Diguna/Disewa\" terlebih dahulu.");
		document.${formName}.txtLuasLotAmbil.focus(); 
		return;
	}
	else 
	if(document.getElementById("sorLain").checked == true && document.${formName}.txtLain.value == "" ){
		alert("Sila masukkan \"Lain - lain Jenis Rizab\" terlebih dahulu.");
  		document.${formName}.txtLain.focus(); 
		return;
	}
	else 
	if (document.${formName}.showButtonConvertAsal.value == "yes") {
		alert ("Sila 'Convert' nilai luas asal terlebih dahulu");
		return;
	}
	else 
	if (document.${formName}.showButtonConvertAmbil.value == "yes") {
		alert ("Sila 'Convert' nilai luas ambil terlebih dahulu");
		return;
	}
	else{
	
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		
		document.${formName}.id_permohonan.value = id_permohonan;

		if(mode=="new"){
			document.${formName}.command.value = "tambahHakmilik";
			document.${formName}.command2.value = "simpanHakmilik";
		}else{
			document.${formName}.command.value = "viewHM";
			document.${formName}.command2.value = "kemaskiniHM";
			document.${formName}.command3.value = "updateHM";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
		document.${formName}.submit();
	}
}
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
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
function validateNumber(elmnt,content) {
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
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
function cariLOT(idpermohonan,mode,idhakmilik) {
	
	document.${formName}.ScreenLocation.value = "txtLuasLotAsal";

	if(mode=="view"){
		document.${formName}.command.value = "viewHM";
		document.${formName}.id_hakmilik.value = idhakmilik;
	}else{
		document.${formName}.command.value = "tambahHakmilik";
	}
		
	document.${formName}.id_permohonan.value = idpermohonan;	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan,mode,idhakmilik) {
	
	document.${formName}.ScreenLocation.value = "txtLuasLotAsal";

	if(mode=="view"){
		document.${formName}.command.value = "viewHM";
		document.${formName}.id_hakmilik.value = idhakmilik;
	}else{
		document.${formName}.command.value = "tambahHakmilik";
	}
	
	document.${formName}.carianNoLot2.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$!selectedTab});
</script>

<script>
function convertNilaiAsal(boxAsal2,boxAsal3){

	var socUnitAsal = document.${formName}.socUnitLuasLot.value;

	if(document.${formName}.socKategoriTanah.value == ""){
		
		alert("Sila pilih \"Kategori Tanah\" terlebih dahulu.");
		document.${formName}.socKategoriTanah.focus(); 
		return;		
		
	}else 
	if(socUnitAsal!="" && document.${formName}.txtLuasLotAsal.value == ""){	

		alert("Sila masukkan \"Luas Lot Asas\" terlebih dahulu.");
		document.${formName}.txtLuasLotAsal.focus(); 
		return;
		
	}else 
	if(boxAsal2=="yes" && (document.${formName}.txtLuasLotAsal.value == "" || document.${formName}.txtLuasLotAsal2.value == "")){	

		alert("Sila lengkapkan \"Luas Lot Asas\" terlebih dahulu.");
		document.${formName}.txtLuasLotAsal2.focus(); 
		return;
			
	}else 
	if(boxAsal3=="yes" && (document.${formName}.txtLuasLotAsal.value == "" || document.${formName}.txtLuasLotAsal2.value == "" 
	   || document.${formName}.txtLuasLotAsal3.value == "")){	

		alert("Sila lengkapkan \"Luas Lot Asas\" terlebih dahulu.");
		document.${formName}.txtLuasLotAsal3.focus(); 
		return;
				
	}else{
		
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.command.value = "tambahHakmilik";
		document.${formName}.command2.value = "doOnchange";
		document.${formName}.command3.value = "onchangeUnitLuasAsal";
		document.${formName}.command4.value = "convertNilaiAsal";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
		document.${formName}.submit();

	}//close else
	
}//close convertNilai Asal

function convertNilaiAmbil(boxAmbil2,boxAmbil3){

	var socUnitLuasAmbil = document.${formName}.socUnitLuasAmbil.value;

	if(document.${formName}.socKategoriTanah.value == ""){
		
		alert("Sila pilih \"Kategori Tanah\" terlebih dahulu.");
		document.${formName}.socKategoriTanah.focus(); 
		return;		
		
	}else 
	if(socUnitLuasAmbil!="" && document.${formName}.txtLuasLotAmbil.value == ""){	

		alert("Sila masukkan \"Luas Lot Ambil\" terlebih dahulu.");
		document.${formName}.txtLuasLotAmbil.focus(); 
		return;
			
	}else 
	if(boxAmbil2=="yes" && (document.${formName}.txtLuasLotAmbil.value == "" || document.${formName}.txtLuasLotAmbil2.value == "")){	

		alert("Sila lengkapkan \"Luas Lot Ambil\" terlebih dahulu.");
		document.${formName}.txtLuasLotAmbil2.focus(); 
		return;
				
	}else 
	if(boxAmbil3=="yes" && (document.${formName}.txtLuasLotAmbil.value == "" || document.${formName}.txtLuasLotAmbil2.value == "" 
	   || document.${formName}.txtLuasLotAmbil3.value == "")){	

		alert("Sila lengkapkan \"Luas Lot Ambil\" terlebih dahulu.");
		document.${formName}.txtLuasLotAmbil3.focus(); 
		return;
						
	}else{
		
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.command.value = "tambahHakmilik";
		document.${formName}.command2.value = "doOnchange";
		document.${formName}.command3.value = "onchangeUnitLuasAmbil";
		document.${formName}.command4.value = "convertNilaiAmbil";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
		document.${formName}.submit();

	}//close else
	
}//close convertNilai Ambil

function validateNilai(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric3(content);
		return;
	}
}
function RemoveNonNumeric3( strString )
{
      var strValidCharacters = "1234567890.";
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
</script>

<script>
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
</script>