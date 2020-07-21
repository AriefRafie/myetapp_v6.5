#parse("app/ppt/frmLabelSet.jsp")

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
		
		<!-- PPT-03  -->
		<!-- #set($txtNoBangunan=$maklumat_Hakmilik_Salin.no_bangunan)
		#set($txtNoTingkat=$maklumat_Hakmilik_Salin.no_tingkat)
		#set($txtNoPetak=$maklumat_Hakmilik_Salin.no_petak)-->
        
        
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
	<legend><strong>Maklumat Lot</strong></legend>	
		
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
				<td>Daerah</td>
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
				#if($id_negeriprojek != '16')
            	<td>Seksyen</td> 
            	#else 
            	<td>Presint</td>
            	#end
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
				<td>No. Hakmilik </td>
				<td>:</td>
				<td><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="12" maxlength="50" ></td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td> No. Strata</td>
				<td>:</td>
				<td>
                   	<span class="labelinput">No.Bang</span>&nbsp;<input name="txtNoBangunan" type="text" class="$disabled" id="txtNoBangunan"  value="$!txtNoBangunan" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
					<span class="labelinput">No.Ting</span>&nbsp;<input name="txtNoTingkat" type="text" class="$disabled" id="txtNoTingkat"  value="$!txtNoTingkat" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
					<span class="labelinput">No.Petak</span>&nbsp;<input name="txtNoPetak" type="text" class="$disabled" id="txtNoPetak"  value="$!txtNoPetak" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
  				</td>
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
				<td><input type="text" name="txdTarikhLuput" id="txdTarikhLuput" value="$!txdTarikhLuput" size="12" maxlength="10" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this);getBakiLuput()">
				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLuput',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr>
			
			
			<tr>
				<td>&nbsp;</td>
				<td>Baki Tempoh (Tahun)</td>
				<td>:</td>
				<td><input type="text" name="txtBakiTempoh" id="txtBakiTempoh" value="$!txtBakiTempoh" size="12" maxlength="10"   ></td>
			</tr>
			#end
			
			
			<tr>
				<td>&nbsp;</td>
				<td>Lokasi</td>
				<td>:</td>
				<td><input type="text" name="txtLokasi" id="txtLokasi" value="$!txtLokasi" size="50" maxlength="100"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td valign="top">Syarat Nyata</td>
				<td valign="top">:</td>
				<!-- <td><input type="text" name="txtSyaratNyata" id="txtSyaratNyata" value="$!txtSyaratNyata" size="50" maxlength="400"></td> -->
				<td><textarea cols="47%" rows="5" name="txtSyaratNyata" id="txtSyaratNyata">$!txtSyaratNyata</textarea></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Syarat Khas</td>
				<td>:</td>
				<td><input type="text" name="txtSyaratKhas" id="txtSyaratKhas" value="$!txtSyaratKhas" size="50" maxlength="400"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Sekatan Kepentingan</td>
				<td>:</td>
				<!-- <td><input type="text" name="txtSekatanKepentingan" id="txtSekatanKepentingan" value="$!txtSekatanKepentingan" size="50" maxlength="400"   ></td>-->
				<td><textarea cols="47%" rows="5" name="txtSekatanKepentingan" id="txtSekatanKepentingan">$!txtSekatanKepentingan</textarea></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Sekatan Hak</td>
				<td>:</td>
				<td><input type="text" name="txtSekatanHak" id="txtSekatanHak" value="$!txtSekatanHak" size="50" maxlength="400"   ></td>
			</tr>
			<tr>
				<td><font color="red">*</font></td>
				<td>No. Syit</td>
				<td>:</td>
				<td><input type="text" name="txtNoSyit" id="txtNoSyit" value="$!txtNoSyit" size="12" maxlength="100"   ></td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
            	<td>No. PT</td>
            	<td>:</td>
            	<td>$!selectKodLot</td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>&nbsp;</td>
            	<td>&nbsp;</td>
                <td><input type="text" name="txtNoPT" id="txtNoPT" value="$!txtNoPT" size="12" maxlength="20"   ></td>
			</tr>
            
            <tr>
            	<td><font color="red">*</font></td>
            	<td>No.LOT</td>
            	<td>:</td>
                <td>
                <!-- defaultLOT() -->
                <input type="text" name="txtNoLot" id="txtNoLot" value="$!txtNoLot" size="12" maxlength="20" onclick="" onblur="checkExistLot('$!mode','$!isEdit','')" onkeyup="checkExistLot('$!mode','$!isEdit','')"  onkeydown="checkExistLot('$!mode','$!isEdit','')">
                &nbsp;<label id="checkLot" style="color:red" ></label>
        
    <a href="javascript:popupCarianHakmilikSalin('$id_permohonan','skrin_salin_hakmilik_sek8','$id_daerah')"><font color="blue">>> SALIN MAKLUMAT HAKMILIK/LOT</font></a>
    
    


                
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
					<input type="text" name="txtLuasLotAsalSebelumConvert" id="txtLuasLotAsalSebelumConvert" size="50" value="$!txtLuasLotAsalSebelumConvert" maxlength="100"   /><p/>
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
				<td>Unit Luas Ambil</td>
				<td>:</td>
				<td>$!selectUnitLuasAmbil</td>
			</tr>
			<tr>
				<td valign="top"><font color="red">*</font></td>
				<td valign="top">Luas Diambil</td>
				<td valign="top">:</td>
				<td>
					
					#if($showFieldAmbilBeforeConvert=="yes")
					<input type="text" name="txtLuasLotAmbilSebelumConvert" id="txtLuasLotAmbilSebelumConvert" size="50" value="$!txtLuasLotAmbilSebelumConvert" maxlength="100"   /><p/>
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
			
			<!-- PPT-03 Lama -->
			<!-- tr>
				<td>&nbsp;</td>
				<td>No. Bangunan</td>
				<td>:</td>
				<td><input type="text" name="txtNoBangunan" id="txtNoBangunan" value="" size="12" maxlength="100"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>No. Tingkat</td>
				<td>:</td>
				<td><input type="text" name="txtNoTingkat" id="txtNoTingkat" value="" size="12" maxlength=""100""   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>No. Petak</td>
				<td>:</td>
				<td><input type="text" name="txtNoPetak" id="txtNoPetak" value="" size="12" maxlength=""100""   ></td>
			</tr -->
			
			<!-- PPT-3 sama macam atas tapi inline -->
			<!-- tr>
  				<td width="1%"  >
				</td>				        
                <td width="20%">
                <div align="left">
                	<span>No. Strata</span>
                </div>
                </td>
                <td width="1%">:</td>
                <td width="100%">
                	<span>No.Bang</span>&nbsp;<input name="txtNoBangunan" id="txtNoBangunan" type="text"value="$!txtNoBangunan" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
					<span>No.Ting</span>&nbsp;<input name="txtNoTingkat" id="txtNoTingkat" type="text"value="$!txtNoTingkat" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
					<span>No.Petak</span>&nbsp;<input name="txtNoPetak" id="txtNoPetak" type="text" value="$!txtNoPetak" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
  				</td>
            </tr -->
			
			
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
  					<input type="radio" name="sorJenisRizab" id="sorRizabMelayu" $checkSorA value="1" onchange="doOnchange('0')">Kawasan Rizab Melayu<br/>
  					<input type="radio" name="sorJenisRizab" id="sorOrangAsli" $checkSorB value="2" onchange="doOnchange('0')">Kawasan Orang Asli<br/>
  					<input type="radio" name="sorJenisRizab" id="sorRizabOrangAsli" $checkSorC value="3" onchange="doOnchange('0')">Rizab Orang Asli<br/>
  					<input type="radio" name="sorJenisRizab" id="sorBerkelompok" $checkSorD value="4" onchange="doOnchange('0')">Kawasan Penempatan Berkelompok<br/>
  					<input type="radio" name="sorJenisRizab" id="sorLain" $checkSorE value="5" onchange="doOnchange('0')">Lain - lain
  					#if($showLainLain=="yes")
  					&nbsp;: <input type="text" name="txtLain" size="25" value="$!txtLain" maxlength="80" id="txtLain"  >
  					#end
  				</td>
            </tr>
            
            #if($sorJenisRizab!="")
            <tr>
            	<td colspan="3">&nbsp;</td>
            	<td>&nbsp;<a href="javascript:doOnchange('1')"><font color="blue">kosongkan pilihan</font></a></td>
            </tr>
            #end
            
            #if($showWarta=="yes")
            <tr>
            	<td>&nbsp;</td>
            	<td>No.Warta</td>
            	<td>:</td>
            	<td><input type="text" name="txtNoWartaRizab" size="30" value="$!txtNoWartaRizab" maxlength="20" id="txtNoWartaRizab"  ></td>
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
		
		<table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian1</td>
        	</tr>
        </table>
        
	</fieldset>	
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
		
		#set($txtLokasi=$data.lokasi)
		#set($txtSyaratNyata=$data.syarat_nyata)
		#set($txtSyaratKhas=$data.syarat_khas)		
		#set($txtSekatanKepentingan=$data.sekatan_kepentingan)
		#set($txtSekatanHak=$data.sekatan_hak)	
		
		<!-- PPT-03 -->
		#set($txtNoBangunan=$data.no_bangunan)
		#set($txtNoTingkat=$data.no_tingkat)
		#set($txtNoPetak=$data.no_petak)
		
		
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
	
	#set($flagsebahagian="")
	#foreach($data in $dataMaklumatTanah)
		#set($flagsebahagian=$data.flag_segera_sebahagian)
	#end
	<input type='hidden' name='socPSegera' value='$!flagsebahagian'>
		
	<fieldset id="top">
	<legend><strong>Maklumat Lot</strong></legend>	
		
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
				<td>Daerah</td>
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
				#if($id_negeriprojek != '16')
            	<td>Seksyen</td> 
            	#else 
            	<td>Presint</td>
            	#end
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtSeksyen" id="txtSeksyen" value="$!txtSeksyen" size="10" maxlength="30"   ></td>
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
				<td><input $disability $disabilityx type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="12" maxlength="50"   ></td>
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
			
			<tr>
				<td>&nbsp;</td>
				<td>Baki Tempoh (Tahun)</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtBakiTempoh" id="txtBakiTempoh" value="$!txtBakiTempoh" size="12" maxlength="10"   ></td>
			</tr>
			#end
			
			<tr>
				<td>&nbsp;</td>
				<td>Lokasi</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtLokasi" id="txtLokasi" value="$!txtLokasi" size="50" maxlength="100"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td valign="top">Syarat Nyata</td>
				<td valign="top">:</td>
				<!-- <td><input $disability $disabilityx type="text" name="txtSyaratNyata" id="txtSyaratNyata" value="$!txtSyaratNyata" size="50" maxlength="400"   ></td> -->
				<td><textarea cols="47%" $disability $disabilityx rows="5" name="txtSyaratNyata" id="txtSyaratNyata">$!txtSyaratNyata</textarea></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Syarat Khas</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtSyaratKhas" id="txtSyaratKhas" value="$!txtSyaratKhas" size="50" maxlength="400"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Sekatan Kepentingan</td>
				<td>:</td>
				<!-- <td><input $disability $disabilityx type="text" name="txtSekatanKepentingan" id="txtSekatanKepentingan" value="$!txtSekatanKepentingan" size="50" maxlength="400"   ></td>-->
				<td><textarea cols="47%" $disability $disabilityx rows="5" name="txtSekatanKepentingan" id="txtSekatanKepentingan">$!txtSekatanKepentingan</textarea></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Sekatan Hak</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtSekatanHak" id="txtSekatanHak" value="$!txtSekatanHak" size="50" maxlength="400"   ></td>
			</tr>
			<tr>
				<td><font color="red">$!M</font></td>
				<td>No. Syit</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtNoSyit" id="txtNoSyit" value="$!txtNoSyit" size="12" maxlength="100"   ></td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
            	<td>No. PT</td>
            	<td>:</td>
            	<td>$!selectKodLot</td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>&nbsp;</td>
            	<td>&nbsp;</td>
                <td><input $disability $disabilityx type="text" name="txtNoPT" id="txtNoPT" value="$!txtNoPT" size="12" maxlength="20"   ></td>
			</tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>No. LOT</td>
            	<td>:</td>
                <td>
                <!-- defaultLOT() -->
                <input $disability $disabilityx type="text" name="txtNoLot" id="txtNoLot" value="$!txtNoLot" size="12" maxlength="20" onclick="" onblur="checkExistLot('$!mode','$!isEdit','$!id_hakmilik')"  onkeypress="checkExistLot('$!mode','$!isEdit','$!id_hakmilik')"   onkeyup="checkExistLot('$!mode','$!isEdit','$!id_hakmilik')"   onkeydown="checkExistLot('$!mode','$!isEdit','$!id_hakmilik')"   >
                &nbsp;<label id="checkLot" style="color:red" ></label>
                
                
                
                </td>
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
					<input type="text" name="txtLuasLotAsalSebelumConvert" $disability $disabilityx id="txtLuasLotAsalSebelumConvert" size="50" value="$!txtLuasLotAsalSebelumConvert" maxlength="100"   /><p/>
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
				<td valign="top">Luas Diambil</td>
				<td valign="top">:</td>
				<td>
					
					#if($showFieldAmbilBeforeConvert=="yes")
					<input type="text" $disability $disabilityx name="txtLuasLotAmbilSebelumConvert" id="txtLuasLotAmbilSebelumConvert" size="50" value="$!txtLuasLotAmbilSebelumConvert" maxlength="100"   /><p/>
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
			
			 <!-- PPT-03  -->
			<!-- tr>
				<td><font color="red">$!M</font></td>
				<td>No. Bangunan</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtNoBangunan" id="txtNoBangunan" value="$!txtNoBangunan" size="12" maxlength="50"   ></td>
			</tr>
			<tr>
				<td><font color="red">$!M</font></td>
				<td>No. Tingkat</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtNoTingkat" id="txtNoTingkat" value="$!txtNoTingkat" size="12" maxlength="50"   ></td>
			</tr>
			<tr>
				<td><font color="red">$!M</font></td>
				<td>No. Petak</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtNoPetak" id="txtNoPetak" value="$!txtNoPetak" size="12" maxlength="50"   ></td>
			</tr -->
			
			<!-- PPT-03 Usop tambah -->
			<tr>
				<td>&nbsp;</td>
				<td> No. Strata</td>
				<td>:</td>
				<td>
                   	<span class="labelinput">No.Bang</span>&nbsp;<input name="txtNoBangunan" type="text" $disability $disabilityx  id="txtNoBangunan"  value="$!txtNoBangunan" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
					<span class="labelinput">No.Ting</span>&nbsp;<input name="txtNoTingkat" type="text" $disability $disabilityx  id="txtNoTingkat"  value="$!txtNoTingkat" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
					<span class="labelinput">No.Petak</span>&nbsp;<input name="txtNoPetak" type="text" $disability $disabilityx  id="txtNoPetak"  value="$!txtNoPetak" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
  				</td>
			</tr>
			
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
  					<input type="radio" $disability1 name="sorJenisRizab" id="sorRizabMelayu" $checkSorA value="1" onchange="doOnchangeUpdate('0')">Kawasan Rizab Melayu<br/>
  					<input type="radio" $disability1 name="sorJenisRizab" id="sorOrangAsli" $checkSorB value="2" onchange="doOnchangeUpdate('0')">Kawasan Orang Asli<br/>
  					<input type="radio" $disability1 name="sorJenisRizab" id="sorRizabOrangAsli" $checkSorC value="3" onchange="doOnchangeUpdate('0')">Rizab Orang Asli<br/>
  					<input type="radio" $disability1 name="sorJenisRizab" id="sorBerkelompok" $checkSorD value="4" onchange="doOnchangeUpdate('0')">Kawasan Penempatan Berkelompok<br/>
  					<input type="radio" $disability1 name="sorJenisRizab" id="sorLain" $checkSorE value="5" onchange="doOnchangeUpdate('0')">Lain - lain
  					#if($showLainLain=="yes")
  					&nbsp;: <input type="text" $disability $disabilityx name="txtLain" size="25" value="$!txtLain" maxlength="80" id="txtLain"  >
  					#end
  				</td>
            </tr>
            
            #if($isEdit=="yes" && $sorJenisRizab!="")
            <tr>
            	<td colspan="3">&nbsp;</td>
            	<td>&nbsp;<a href="javascript:doOnchangeUpdate('1')"><font color="blue">kosongkan pilihan</font></a></td>
            </tr>
            #end
            
            #if($showWarta=="yes")
            <tr>
            	<td>&nbsp;</td>
            	<td>No.Warta</td>
            	<td>:</td>
            	<td><input type="text" $disability $disabilityx name="txtNoWartaRizab" size="30" value="$!txtNoWartaRizab" maxlength="20" id="txtNoWartaRizab"  ></td>
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
		
		#if($isEdit=="yes")
		<table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian1</td>
        	</tr>
        </table>
        #end
		
	</fieldset>	
	#end


	<table width="100%" border="0">
        <tr align="center">
          <td>
            ##if($mode=="view")
            #if($!id_negeriprojek == "4")
        		<input type="button" name="cmdsemakanhakmilik" value="Capaian Hakmilik e-Tanah" onclick="javascript:semakanHakmilikeTanah('ppt','$id_permohonan')">
          	#end  
<!--             <input type="button" name="cmdCapaianETanah" value ="Capaian Maklumat Hakmilik" onClick="javascript:viewEtanah('$!id_hakmilik')"> -->
            <input type="button" name="cmdETanah" value ="Semakan Maklumat Hakmilik" onClick="javascript:checkWSHM('$!id_hakmilik')">
            <input type="button" name="cmdSPTB" value ="Semakan Maklumat Geran" onClick="javascript:viewSPTB('$!id_fail','$!id_hakmilik','1')">
			##end
          </td>
        </tr>
		<tr align="center">
			<td>
            
            
				#if($mode=="new")
				<input type="button" name="cmdSimpan" value ="Simpan"  onclick="simpanHM('$!id_permohonan','$!id_hakmilik','$!flag_subjaket','$!mode');" >
				#end
				
				#if($mode=="view")
					#if($isEdit=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniHM('$!id_hakmilik')">
					<input type="button" name="cmdHapus" value="Hapus" onClick="hapusHM('$!id_hakmilik','$!saiz_pb','$!saiz_bebanan')">
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanHM('$!id_permohonan','$!id_hakmilik','$!flag_subjaket','$!mode')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:viewHM('$!id_hakmilik','1')">
					#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:semakHM('$!id_permohonan')">
			</td>
		</tr>
	</table>

<p/>


#if($mode=="view")
<!-- TAB -->

<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>

<div id="TabbedPanels1" class="TabbedPanels">
  		<ul class="TabbedPanelsTabGroup">
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);" tabindex="1" id="maklumat_pb">Pihak Berkepentingan (PB)</li>
   			<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);" tabindex="1">Bebanan</li>
  		</ul>
  		
  	<div class="TabbedPanelsContentGroup">
  	
  		<!-- TAB 1 -->
    	<div class="TabbedPanelsContent">
    		

<fieldset id="senarai_siasatan">
<legend>SENARAI PIHAK BERKEPENTINGAN <input type="button" value="Tambah Pihak Berkepentingan" onClick="tambahWakil()"> #parse('app/ppt/ButtonJanaPB.jsp')</legend>
<a href="javascript:popupCarianPB('$id_hakmilik','skrin_hakmilik_pb_sek8')"><font color="blue">>> SKRIN CAPAIAN PIHAK BERKEPENTINGAN</font></a>	
<!--
      #if($list_kehadiran_size >15)
      <div style="width:100%;height:100;overflow:auto"> 
      #end  
 -->       
        <table width="100%" border="0" style="display:none">
  			<tr>              
              	<td colspan="8">
              		<table width="100%" >
              			<tr>
             				<td align="left" width="50%" valign="top">
              					<input type="button" value="Tambah Pihak Berkepentingan" onClick="tambahWakil()">
              					#if($list_kehadiran_size > 0)
              					<input type="button" value="Simpan Pilihan Borang" onClick="Simpan_Borang()">
              					#end
                                #parse("app/ppt/ButtonJanaPB.jsp")
              				</td>
              
              				<td width="50%" align="right" valign="top">
                				#if($list_kehadiran_size!=0)
                				Nama / No. KP PB : 
              
                      			<label>
                    				<input name="CariPB" type="text" id="CariPB"  value="$!CariPB" size="20" maxlength="150" />
                    			</label>
                 
                      			<label>
                      				<input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Cari" onClick="PB('$id_hakmilik')" />
                      			</label>
                      
                      			<label>
                      				<input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Kosongkan" onClick="PB_Kosong('$id_hakmilik')"  />
                      			</label>
                    
          						<div style="display:none"> Kehadiran PB : <span id="jumlahlot" ></span> </div>
                      
                 				 #end
                			</td>
              			</tr>
              		</table>
              
              
             	</td>
             </tr>
             
  			<tr class="table_header">
  				<td width="3%"><center><b>Bil</b></center></td>
    			<td width="27%"><b>Nama PB</b></td>
    			<td  width="23%"><b>Jenis PB</b></td>
    			<td width="15%" align="center"><b>Bahagian / Syer & Berkongsi Bahagian (Jika Ada)</b></td>
   
      		#if($list_kehadiran_size > 0)
    			<td width="8%"> 
     				<div align="center"><b>Borang</b></div>
      				<div align="center"><b>C & D</b></div>
      				<div align="center">
		     		<input type="checkbox" name="all1" id="all1" onClick="doCheckAll1('$list_kehadiran_size');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang C & D" />
      				</div>
      			</td>
     
       			<td width="8%"> 
          			<div align="center"><b>Borang</b></div>
      				<div align="center"><b>E & F</b></div>
      				<div align="center">
      				<input type="checkbox" name="allborangE" id="allborangE" onClick="doCheckAllBorangE('$list_kehadiran_size');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang E & F" />
      				</div>
      			</td>
      
      
        		<td width="8%"> 
         			<div align="center"><b>Borang</b></div>
      				<div align="center"><b>G & H</b></div>
				    <div align="center">
				    <input type="checkbox" name="allborangG" id="allborangG" onClick="doCheckAllBorangG('$list_kehadiran_size');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang G & H" />
      				</div>
      			</td>
      
      
		        <td width="8%"> 
		          	<div align="center"><b>Borang</b></div>
      				<div align="center"><b>K</b></div>
		      	  	<div align="center">
		      	  	<input type="checkbox" name="allborangK" id="allborangK" onClick="doCheckAllBorangK('$list_kehadiran_size');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang K" />
		      		</div>
		      	</td>
      
      			<!-- <td width="2%">
       				<div align="center" class="font1">
       					<font style="cursor:help" title="Info"  onMouseOut="close_window()" onMouseOver="open_new_window('1');this.style.cursor='help'" >
       					#parse("app/ppt/infoblink_putih.jsp")
       					</font>                                 
       				</div>      
       			</td> -->
      			

  			#else

    			<td width="8%"> 
      				<div align="center"><b>Borang</b></div>
      				<div align="center"><b>C & D</b></div>
      				<div align="center"></div>
      			</td>

      
			   	<td width="8%"> 
			      	<div align="center"><b>Borang</b></div>
			      	<div align="center"><b>E & F</b></div>
			      	<div align="center"></div>
			    </td>
      
      
		        <td width="8%"> 
		         	<div align="center"><b>Borang</b></div>
		      		<div align="center"><b>G & H</b></div>
		      		<div align="center"></div>
		      	</td>
      
      
		        <td width="8%"> 
		          	<div align="center"><b>Borang</b></div>
		      		<div align="center"><b>K</b></div>
		      		<div align="center"></div>
		      	</td>
      
      			<!-- <td width="2%">
       				<div align="center" class="font1">
       				<font style="cursor:help" title="info" onMouseOut="close_window()"  onMouseOver="open_new_window('1');this.style.cursor='help'" >
       				#parse("app/ppt/infoblink_putih.jsp")
       				</font>                                 
       				</div>      
       			</td> -->
      			

      		</tr>
  
  			#end
  
  
   			#if($list_kehadiran_size > 0)
  				#foreach($list in $list_kehadiran)        
           			#set( $i = $velocityCount )
         			
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
 
   			<tr>
    			<td colspan="5">
 					<fieldset id="$list.BILTEMP" class="$row" style="visibility:collapse; display:none;">
					
					<table width="100%" border="0">
     					<tr>
        					<td width="1%">&nbsp;</td>
       						<td width="99%"><div align="left"><a href="javascript:paparpb('$list.ID_HAKMILIKPB');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">KEMASKINI MAKLUMAT PB</font></a></div></td>
        				</tr>
     				</table>
    
    				<table width="100%" border="0">
  						<tr>
    						<td width="50%" valign="top">
    							<table width="100%">
      								<tr>
								        <td width="1%">&nbsp;</td>
								        <td width="28%">Jenis Pihak Berkepentingan</td>
								        <td width="1%" >:</td>
								        <td width="70%">$list.JENISPB</td>
      								</tr>
      
      								<tr>
								        <td>&nbsp;</td>
								        <td>Kehadiran</td>
								        <td>:</td>
								       	<td>
        
		 									#set($hadir = "TIDAK HADIR")
									    
									    	#if($list_check_kehadiran.size()>0)
									    		#foreach($ch in $list_check_kehadiran)    
									    			#if($ch.ID_HAKMILIKPB == $list.ID_HAKMILIKPB && $ch.FLAG_HADIR == "1")    
									    				#set($hadir = "HADIR")   
									   				#end    
									    		#end    
									    	#end
  
        									$!hadir
        									
        								</td>
							      	</tr>
							      
							      	<tr>
								        <td>&nbsp;</td>
								        <td>Nama PB</td>
								        <td>:</td>
								        <td>$list.NAMA_PB</td>
							      	</tr>
							      
							      	<tr>
							        	<td>&nbsp;</td>
							        	<td>Nama PB (KP)</td>
							        	<td>:</td>
							        	<td>$list.NAMA_KP</td>
							      	</tr>
							      	
							      	<tr>
							        	<td>&nbsp;</td>
							        	<td>Jenis No. PB</td>
							        	<td>:</td>
							        	<td>$list.JENISNOPB</td>
							      	</tr>
      
      								<tr>
								        <td>&nbsp;</td>
								        <td>No. PB</td>
								        <td>:</td>
								        <td>$list.NO_PB</td>
								    </tr>
    							</table>
    						</td>
    
						    <td width="50%" valign="top">
						    	<table width="100%">
						    	
									<tr>
								      	<td width="1%">&nbsp;</td>
								        <td width="28%">No. Akaun</td>
								        <td width="1%">:</td>
								        <td width="70%">$list.NO_AKAUN</td>
								    </tr>
								     
									<tr>
								        <td>&nbsp;</td>
								        <td>Nama Bank</td>
								        <td>:</td>
								        <td>$list.NAMA_BANK</td>
								    </tr>
								     
									<tr>
								        <td>&nbsp;</td>
								        <td>Alamat PB</td>
								        <td>:</td>
								        <td>$list.ALAMAT1</td>
								    </tr>
								      
									<tr>
								        <td>&nbsp;</td>
								        <td>&nbsp;</td>
								        <td>:</td>
								        <td>$list.ALAMAT2</td>
								    </tr>
								      
									<tr>
								        <td>&nbsp;</td>
								        <td>&nbsp;</td>
								        <td>:</td>
								        <td>$list.ALAMAT3</td>
								    </tr>
								      
								 	<tr>
								        <td>&nbsp;</td>
								        <td>Poskod</td>
								        <td>:</td>
								        <td>$list.POSKOD</td>
								    </tr>
								      
								 	<tr>
								        <td>&nbsp;</td>
								        <td>Bandar</td>
								        <td>:</td>
								        <td>$list.NAMA_BANDAR</td>
								     </tr>
								      
								     <tr>
								        <td>&nbsp;</td>
								        <td>Negeri</td>
								        <td>:</td>
								       	<td>$list.NAMA_NEGERI</td>
								   	</tr>
								      
						    	</table>
						    </td>
  						</tr>
					</table>
					
    				</fieldset>

    		</td>
   		</tr>
   
   		<tr  class="$row" >
    		<td><center>$list.BIL</center></td>
 			<td id="$list.NO_PB">
    			<div align="left">
    			<a href="javascript:paparpb('$list.ID_HAKMILIKPB');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">$list.NAMA_PB</font></a>
                <div>$list.KETERANGAN_JENIS_PB</div>
    			</div>
			</td>
   
    		<td>
    		
    		#set($lblPA = "Daftar Pemegang Amanah")
    		<!-- #* #if($list.ID_PA1 != "")
    			#set($lblPA = $list.NAMA_PA1+" (Pemegang Amanah)")
    		#end 
            
            *# -->
    		<div align="left">$list.JENISPB</div>
    		
    		#if($list.UMUR != 0 && $list.UMUR < 18)
    		<div><a href="javascript:popupPemegangAmanah('$!list.ID_HAKMILIK','$list.ID_HAKMILIKPB');">
    		<font color="blue" style='font-size:11px'><i>$!lblPA</i></font></a></div>
    		#end
    		</td>
    		
		    
		    <td>
				<div align="center">
		    	#if($list.SYER_ATAS != "" && $list.SYER_BAWAH != "")
		    		$list.SYER_ATAS / $list.SYER_BAWAH    
		    	#end
		    	</div>
  
    
                      #set($count = 0) 
                      #foreach($list1 in $senarai_pihak_penting_bahagian)
                      #if($list1.ID_BAHAGIANPB == $list.ID_BAHAGIANPB && $list1.ID_HAKMILIKPB != $list.ID_HAKMILIKPB ) 
                      #set($count=$count + 1) 
                      #end
                      #end
                      
                      #if($count > 0)
                     
                      <div>
                      #set($count_total = 0) 
                      #foreach($list1 in $senarai_pihak_penting_bahagian)
                      #if($list1.ID_BAHAGIANPB == $list.ID_BAHAGIANPB && $list1.ID_HAKMILIKPB != $list.ID_HAKMILIKPB)
                      #set($count_total=$count_total + 1)                      
                      #if($count > 1 && $count != $count_total && $count_total != $count - 1 ) 
                      $list1.NAMA_PB,
                      #elseif($count > 1 && $count != $count_total && $count_total == $count - 1) 
                      $list1.NAMA_PB &
                      #elseif($count > 1 && $count == $count_total)
                      $list1.NAMA_PB
                      #elseif($count == 1)
                      $list1.NAMA_PB
                      #end                                          
                      #end
                      #end 
                      </div>
                      #end
    
    		</td>
    
    		<td>
  
		    	#set($boxC = "")    
		    
		    	#if($list.FLAG_BORANGC == "1" )    
		    		#set($boxC = "checked")   
		   		#end    

      			<div align="center">
        			<input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1('$list_kehadiran_size')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang C & D" $boxC value="$list.ID_HAKMILIKPB" >
        		</div>
        			<input type="hidden" name="idPB" id="idPB" value="$list.ID_HAKMILIKPB" >
        			
        	</td>
        
         	<td>
         
		        #set($boxE = "")    
		    	
		    	#if($list.FLAG_BORANGE == "1" )    
		    		#set($boxE = "checked")   
		    	#end  
         
          		<div align="center">
        			<input type="checkbox" name="borangE" id="borangE" onClick="doUpdateCheckAllBorangE('$list_kehadiran_size')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang E & F" $boxE value="$list.ID_HAKMILIKPB" >
        		</div>
        		
         	</td>
    
	        <td>
         
			    #set($boxG= "")    
			    
			    #if($list.FLAG_BORANGG == "1" )    
			    	#set($boxG = "checked")   
			    #end  
         
         		<div align="center">
        			<input type="checkbox" name="borangG" id="borangG" onClick="doUpdateCheckAllBorangG('$list_kehadiran_size')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang G & H" $boxG value="$list.ID_HAKMILIKPB" >
        		</div>
         
         	</td>
         
         	<td>
         	
		         #set($boxK = "")    
		    		
		    	#if($list.FLAG_BORANGK == "1" )    
		    		#set($boxK = "checked")   
		    	#end 
          
          		<div align="center">
        			<input type="checkbox" name="borangK" id="borangK" onClick="doUpdateCheckAllBorangK('$list_kehadiran_size')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang K" $boxK value="$list.ID_HAKMILIKPB" >
        		</div>
         
         	</td>
         	
         	<!-- <td>&nbsp;</td> -->
         	
   		</tr>
  		#end
  		
  	#else
  	
  		<tr>  
    		<td colspan="5">Tiada Rekod</td>    
  		</tr>
  	#end
  	
	</table>
    
    <!--    
    #if($list_kehadiran > 15)
    </div>
    #end
-->

	</fieldset>
    	
    </div>
    
    <!-- END TAB 1 -->
    	
    	
    	<!-- TAB 2 -->
    	<div class="TabbedPanelsContent">
    		<fieldset>
    		<legend><strong>Senarai Bebanan</strong></legend>	
    		
    			<table width="100%" border="0">
    				<tr>
    					<td width="30%" align="left"><input type="button" name="cmdTambahBebanan" value="Tambah Bebanan" onClick="javascript:tambahBebanan('$!id_hakmilik')"></td>
    					<td width="70%" align="right">Carian No.Perserahan :&nbsp;<input type="text" name="carianNoSerah" value="$!carianNoSerah" maxlength="30" size="20"   ><a href="javascript:cariNoSerah('$!id_hakmilik')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanNoSerah('$!id_hakmilik')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    			</table>

    			#if($saiz_bebanan > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            	#end
            	
    			<table width="100%" border="0">	
    				<tr class="table_header">
    					<td width="4%" align="center"><b>No</b></td>
    					<td width="15%"><b>Nama</b></td>
    					<td width="33%">&nbsp;<b>No. Perserahan</b></td>
    					<td width="48%">&nbsp;<b>Jenis Bebanan</b></td>
    				</tr>
    				
    		   #if($saiz_bebanan!=0)
                    #foreach($listB in $listBebanan)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               		<tr>
                   		<td class="$row" align="center">$!listB.bil</td>
                   		<td class="$row">$!listB.nama</td>
                   		<td class="$row">&nbsp;<a href="javascript:viewBebanan('$!listB.id_bebanan')"><font color="blue">$!listB.no_perserahan</font></a></td>
                   		<td class="$row">&nbsp;$!listB.jenis_bebanan</td>
              		<tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
    				
    			</table>
    			
    			#if($saiz_bebanan > 5)
                </div>
            	#end
    			
    		</fieldset>
    	</div>
    	<!-- END TAB 2 -->
    	
  	</div>
  
</div>

#end

<table width="100%" border="0"><tr><td>&nbsp;</td></tr></table>  
 
	<fieldset id="bottom">
	<legend><strong>Senarai Maklumat Lot
    #if($mode=="view")
    <input type="button" name="cmdTambah" value="Tambah Hakmilik" onClick="javascript:tambahHM('$!flag_subjaket');">    
    #end
    <input type="button" name="janaSJ" value ="Jana Subjaket" onClick="javascript:popupCarianHakmilikJanaSubjaket('$id_permohonan','skrin_list_hakmilik_pb_sek8');">
    </strong></legend>
    
    	<a href="javascript:popupCarianHakmilik('$id_permohonan','skrin_list_hakmilik_pb_sek8')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>	
    
    
		<!--	
			<table width="100%" border="0">   
                	<tr>
                		#if($mode=="view")
                    	<td width="30%" align="left">
                        <input type="button" name="cmdTambah" value="Tambah Hakmilik" onClick="javascript:tambahHM('$!flag_subjaket');">
                        </td>
    					#else
    					<td width="30%">&nbsp;</td>
    					#end
    					<td width="70%" align="right">Carian No.LOT<b>/</b>No.PT<b>/</b>Nama Pihak Berkepentingan :&nbsp;<input type="text" name="carianNoLot2" value="$!carianNoLot2" maxlength="20" size="20"   ><a href="javascript:cariLOT('$!id_permohonan','$!mode','$!id_hakmilik')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan','$!mode','$!id_hakmilik')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    			-->
    		<!-- List Hakmilik -->
            
            
          <!--  #parse("app/ppt/frmSeksyen8ListHM.jsp") -->
	
</fieldset>



<input type="hidden" name="id_bebanan">
<input type="hidden" name="id_pihakberkepentingan">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">
<input type="hidden" name="command5">
<input type="hidden" name="flag_subjaket" value="$!flag_subjaket">

  <input type="hidden" name="id_hakmilik_salin" id="id_hakmilik_salin" value="$!id_hakmilik_salin" />

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
<input type="hidden" name="resetRadio">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="subminor_command" id="subminor_command" />
<input type="hidden" name="location" id="location" />
<input type="hidden" name="point" id="point" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$!id_hakmilikpb" />

<script>
	//Skrin Maklumat Tanah
	function semakanHakmilikeTanah(modul,idPermohonan) {
		var url = "../x/${securityToken}/FrmPopupCapaianHakmilikeTanah?modul="+modul+"&idPermohonan="+idPermohonan;
	    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	
	}

function viewEtanah(id_hakmilik) {
// 	document.${formName}.ScreenLocation.value = "maklumat_pb";
// 	document.${formName}.command.value = "viewEtanah";
// 	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
// 	document.${formName}.submit();
	
    var url = "../x/${securityToken}/ekptg.view.integrasi.FrmViewETanah?action2=checkHM&isPPT=1&ID_HAKMILIK=" + ID_HAKMILIK;
    var hWnd = window.open(url, 'Semakan Status Hakmilik di Sistem eTanah', 'width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function refreshHakmilik(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}


function popupCarianHakmilikJanaSubjaket(id_permohonan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilikJanaSubjaket?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
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
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
		document.${formName}.submit();
		
		

	}//close else
	
}//close convertNilai Asal


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


function popupCarianPB(id_hakmilik,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianPB?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
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

function popupPemegangAmanah(id_hakmilik,id_hakmilikpb){

	var w = "600";
	var h = "400";
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmPopupPemegangAmanah?id_hakmilik="+id_hakmilik+"&id_hakmilikpb="+id_hakmilikpb;
		
	var hWnd = window.open(url, "Daftar Pemegang Amanah", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}


function checkExistLot(mode,isEdit,id_hakmilik){
	//alert(":::::::::: mode:"+mode);
	//alert(":::::::::: isEdit:"+isEdit);
	//alert(":::::::::: id_hakmilik:"+id_hakmilik);
	if((mode=="new" || (mode=="view" && isEdit=="yes")) 
	&& (document.${formName}.txtNoLot.value != "" && document.${formName}.txtNoLot.value != "TDK"))
	{
	//	alert("masuk");
		url = "../servlet/ekptg.view.ppt.FrmPengambilanCheck?command=checkExistLot&id_hakmilik="+id_hakmilik;
		//alert("masuk1");
		actionName = "checkExistLot";
		//alert("masuk2");
		target = "checkLot";
		//alert("masuk3");
		doAjaxUpdater(document.${formName},url,target,actionName);
		//alert("masuk4");
		
		return;
		//alert("masuk5");
		
	}
}
function getBakiLuput(){

	var dat1=document.${formName}.txdTarikhDaftar;
	var dat2=document.${formName}.txdTarikhLuput;

	//tarikh daftar
	var TD  = document.getElementById("txdTarikhDaftar").value;
	var dt1   = parseInt(TD.substring(0,2),10);
   	var mon1  = parseInt(TD.substring(3,5),10);
   	var yr1   = parseInt(TD.substring(6,10),10);
   	var dateDaftar = new Date(yr1, mon1, dt1);

  	//tarikh Luput
	var TL  = document.getElementById("txdTarikhLuput").value;
	var dt2   = parseInt(TL.substring(0,2),10);
   	var mon2  = parseInt(TL.substring(3,5),10);
   	var yr2   = parseInt(TL.substring(6,10),10);
   	var dateLuput = new Date(yr2, mon2, dt2);

  	//Set 1 day in milliseconds
   	//var one_day=1000*60*60*24

   	//Calculate difference btw the two dates, and convert to days
   	//var days = Math.ceil((dateLuput.getTime()-dateDaftar.getTime())/(one_day))

   	
	if (dat1.value!="" && isDate(dat1.value)==false){
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false){
		dat2.focus()
		return;
	}
	else if(dat1.value!="" && dat2.value!="" && (dateLuput < dateDaftar)){
		alert("Sila pastikan \"Tarikh Luput\" lebih terkini dari \"Tarikh Daftar\" ");
		document.${formName}.txdTarikhLuput.focus();
		return;
	}else if(dat1.value!="" && dat2.value!=""){

		var tempoh = yr2 - yr1;
		document.getElementById("txtBakiTempoh").value = tempoh;	
	}
	
}

function defaultLOT(){

	if(document.${formName}.txtNoLot.value==""){
		document.${formName}.txtNoLot.value = "LOT";
	}
	
}
function viewBebanan(idBebanan){

	document.${formName}.ScreenLocation.value = "changeBebanan";	
	document.${formName}.command.value = "viewBebanan";
	document.${formName}.id_bebanan.value = idBebanan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function tambahBebanan(idHakmilik){

	document.${formName}.ScreenLocation.value = "changeBebanan";
	
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahBebanan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function viewPB(idPB){

	document.${formName}.ScreenLocation.value = "changePB";
	
	document.${formName}.id_pihakberkepentingan.value = idPB;
	document.${formName}.command.value = "viewPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
	
}
function tambahPB(idHakmilik){

	
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
	/*
	document.${formName}.ScreenLocation.value = "changePB";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();*/
}
function salin_hakmilik(id_permohonan,id_hakmilik_salin)
{
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "salinHakmilik";
	document.${formName}.id_permohonan.value = id_permohonan;	
	document.${formName}.id_hakmilik_salin.value = id_hakmilik_salin;	
	//document.${formName}.subminor_command.value = "salin_hakmilik";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	//document.${formName}.location.value = "maklumat_pb";
	//document.${formName}.point.value = "socJenisPB";
	//document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();	
}
function tambahHM(flagSubjaket){

	if(flagSubjaket=="1"){
		alert("No Subjaket perlu dijana semula sekiranya ada penambahan hakmilik");
	}
	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function hapusHM(idHakmilik,saizPB,saizBebanan) {

	document.${formName}.ScreenLocation.value = "top";

	var saizpb = parseInt(saizPB);
	var saizbebanan = parseInt(saizBebanan);

	if(saizpb != 0 || saizbebanan != 0){
		if ( !window.confirm("Adakah Anda Pasti? Terdapat "+saizpb+" Pihak Berkepentingan Dan "+saizbebanan+" Bebanan Didalam Hakmilik Ini") ) return;
	}else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
	}	
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "hapusHM";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function kemaskiniHM(idHakmilik){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function viewHM(idHakmilik,mode){

	if(mode=="1"){
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
	}else if(mode=="popup"){
		document.${formName}.ScreenLocation.value = "senarai_siasatan";	
	}else{
		document.${formName}.ScreenLocation.value = "top";	
	}
	document.${formName}.command.value = "viewHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function simpanHM(id_permohonan,id_hakmilik,flagSubjaket,mode){
	
	var alert_lot = "N";	
	if (document.${formName}.check_lot == null || document.${formName}.check_lot == undefined) {	
	alert_lot = "N";	
	}	else	{
		alert_lot =	document.${formName}.check_lot.value;
	}
	
	
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
		alert("Sila lengkapkan maklumat \"No.PT\" terlebih dahulu.");
  		document.${formName}.socKodLot.focus(); 
		return;
	}
	else if(document.${formName}.txtNoLot.value == "" && document.${formName}.txtNoPT.value == ""){
		alert("Sila masukkan salah satu \"No.PT atau No.LOT\" terlebih dahulu.");
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
		alert("Sila masukkan \"Luas Diambil\" terlebih dahulu.");
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
	else if(alert_lot=="Y")
	{
	alert("No. Lot telah wujud di dalam permohonan ini!");
	return;
	}
	else{
		
	
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		
		document.${formName}.flag_subjaket.value = flagSubjaket;
		document.${formName}.id_permohonan.value = id_permohonan;

		if(mode=="new"){
			document.${formName}.command.value = "tambahHM";
			document.${formName}.command2.value = "simpanHM";
		}else{
			document.${formName}.command.value = "viewHM";
			document.${formName}.command2.value = "kemaskiniHM";
			document.${formName}.command3.value = "updateHM";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
		document.${formName}.submit();
	}

}

function semakHM(id_permohonan){
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
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
</script>


<script>
window.onload = submitForm;

function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$!selectedTab});

function submitForm(){



	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
	


var lk = '$list_kehadiran_size';
if(lk > 0)
{
doUpdateCheckAll1('$list_kehadiran_size');
doUpdateCheckAllBorangG('$list_kehadiran_size');
doUpdateCheckAllBorangE('$list_kehadiran_size');
doUpdateCheckAllBorangK('$list_kehadiran_size');
}



}
</script>


<!-- FUNCTION CONVERT -->
<script>
function onchangeUnitAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.command5.value = "onchangeUnitAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function clearConvertAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.command5.value = "clearConvertAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function convertNilaiAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.command5.value = "convertNilaiAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function onchangeUnitLuasAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function onchangeUnitAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.command5.value = "onchangeUnitAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function clearConvertAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.command5.value = "clearConvertAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function convertNilaiAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.command5.value = "convertNilaiAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function onchangeUnitLuasAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function doOnchangeUpdate(resetVal){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.resetRadio.value = resetVal;
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function onchangeUnitAmbil(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHM";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAmbil";
	document.${formName}.command4.value = "onchangeUnitAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function onchangeUnitAsal(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHM";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAsal";
	document.${formName}.command4.value = "onchangeUnitAsal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function onchangeUnitLuasAsal(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHM";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAsal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function onchangeUnitLuasAmbil(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHM";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function clearConvertAsal(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHM";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAsal";
	document.${formName}.command4.value = "clearConvertAsal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function clearConvertAmbil(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHM";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAmbil";
	document.${formName}.command4.value = "clearConvertAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function doOnchange(resetVal){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.resetRadio.value = resetVal;
	document.${formName}.command.value = "tambahHM";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
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
		document.${formName}.command.value = "tambahHM";
		document.${formName}.command2.value = "doOnchange";
		document.${formName}.command3.value = "onchangeUnitLuasAsal";
		document.${formName}.command4.value = "convertNilaiAsal";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
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
		document.${formName}.command.value = "tambahHM";
		document.${formName}.command2.value = "doOnchange";
		document.${formName}.command3.value = "onchangeUnitLuasAmbil";
		document.${formName}.command4.value = "convertNilaiAmbil";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
		document.${formName}.submit();

	}//close else
	
}//close convertNilai Ambil

</script>


<!-- CARIAN dan KOSONGKAN -->
<script>
function cariLOT(idpermohonan,mode,idhakmilik) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	if(mode=="view"){
		document.${formName}.command.value = "viewHM";
		document.${formName}.id_hakmilik.value = idhakmilik;
	}else{
		document.${formName}.command.value = "tambahHM";
	}
		
	document.${formName}.id_permohonan.value = idpermohonan;	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan,mode,idhakmilik) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	if(mode=="view"){
		document.${formName}.command.value = "viewHM";
		document.${formName}.id_hakmilik.value = idhakmilik;
	}else{
		document.${formName}.command.value = "tambahHM";
	}
	
	document.${formName}.carianNoLot2.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function cariNoSerah(idHakmilik) {
	
	document.${formName}.ScreenLocation.value = "bottom";	

	document.${formName}.tabId.value = "1";
	document.${formName}.command.value = "viewHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();	
}
function kosongkanNoSerah(idHakmilik) {
	
	document.${formName}.ScreenLocation.value = "bottom";	

	document.${formName}.carianNoSerah.value = "";
	document.${formName}.tabId.value = "1";
	document.${formName}.command.value = "viewHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();	
}
function cariPB(idHakmilik) {
	
	document.${formName}.ScreenLocation.value = "bottom";	
	document.${formName}.command.value = "viewHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();	
}
function kosongkanPB(idHakmilik) {
	
	document.${formName}.carianPB.value = "";
	document.${formName}.ScreenLocation.value = "bottom";	
	document.${formName}.command.value = "viewHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
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
var minYear=1800;
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





function Simpan_Borang()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {	
	document.${formName}.command.value = "viewHM";	
	document.${formName}.subminor_command.value = "Simpan_Borang";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.ScreenLocation.value = "senarai_siasatan";
	document.${formName}.submit();
	}
}

function paparpb(id_hakmilikpb)
{
    document.${formName}.command.value = "tambahPB";	
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.submit();
}



function PB(id_hakmilik)
{

	
	document.${formName}.command.value = "viewHM";
	
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.id_hakmilik.value = id_hakmilik;	
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.submit();

}


function PB_Kosong(id_hakmilik)
{

	
	document.${formName}.command.value = "viewHM";
	
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.id_hakmilik.value = id_hakmilik;	
	document.${formName}.CariPB.value = "";	
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.submit();

}



function doCheckAll2(sudahbatal){  
var cc = 0;  
    if (document.${formName}.all2.checked == true){
        if (document.${formName}.ids2.length == null){
		cc++;
            document.${formName}.ids2.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids2.length; i++){
                document.${formName}.ids2[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.ids2.length == null){
            document.${formName}.ids2.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids2.length; i++){
                document.${formName}.ids2[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	/*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot_th";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       
       $jquery("#jumlahlot_th").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
}
function doUpdateCheckAll2(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.ids2.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids2.length; i++)
	  {
      if (document.${formName}.ids2[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.ids2.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all2.checked = false;
	  }
	  else
	  {
	  document.${formName}.all2.checked = true;
	  }
	  
	  
	  
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot_th";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot_th").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
}








function doCheckAll1(sudahbatal){  
var cc = 0;  
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
		cc++;
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = false;

        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}


function doCheckAllBorangG(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangG.checked == true){
        if (document.${formName}.borangG.length == null){
		cc++;
            document.${formName}.borangG.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangG.length; i++){
                document.${formName}.borangG[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangG.length == null){
            document.${formName}.borangG.checked = false;


        } else {
            for (i = 0; i < document.${formName}.borangG.length; i++){
                document.${formName}.borangG[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}

function doCheckAllBorangE(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangE.checked == true){
        if (document.${formName}.borangE.length == null){
		cc++;
            document.${formName}.borangE.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangE.length; i++){
                document.${formName}.borangE[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangE.length == null){
            document.${formName}.borangE.checked = false;

        } else {
            for (i = 0; i < document.${formName}.borangE.length; i++){
                document.${formName}.borangE[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}



function doCheckAllBorangK(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangK.checked == true){
        if (document.${formName}.borangK.length == null){
		cc++;
            document.${formName}.borangK.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangK.length; i++){
                document.${formName}.borangK[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangK.length == null){
            document.${formName}.borangK.checked = false;

        } else {
            for (i = 0; i < document.${formName}.borangK.length; i++){
                document.${formName}.borangK[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}





function doUpdateCheckAll1(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all1.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1.checked = true;
	  }
	  
	  
	 
	
}



function doUpdateCheckAllBorangG(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangG.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangG.length; i++)
	  {
      if (document.${formName}.borangG[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangG.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangG.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangG.checked = true;
	  }
	  
	  
	  
	  	
	
}

function doUpdateCheckAllBorangE(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangE.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangE.length; i++)
	  {
      if (document.${formName}.borangE[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangE.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangE.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangE.checked = true;
	  }
	  
	  
	
}



function doUpdateCheckAllBorangK(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangK.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangK.length; i++)
	  {
      if (document.${formName}.borangK[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangK.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangK.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangK.checked = true;
	  }
	  
	  
	
}



function tambahWakil()
{

	
	document.${formName}.command.value = "tambahPB";	
	document.${formName}.subminor_command.value = "tambah_wakil";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();
	
}

function open_new_window(jenis_popup) 
{
 var width  = 0;
 var height = 0;
if(jenis_popup == "1")
{
  width  = 300;
  height = 300;
}
if(jenis_popup == "3")
{
  width  = 300;
  height = 200;
}
if(jenis_popup == "2")
{
 var width  = 300;
 var height = 300;
}



 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=yes';
 params += ', menubar=yes';
 params += ', toolbar=yes';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
//new_window = open("","hoverwindow",params);
new_window = open("","title",params);

new_window.document.open();

new_window.document.write("<html><title>JavaScript New Window</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");

if(jenis_popup == "1")
{
new_window.document.write("Sila klik pada kotak pilihan borang yang disediakan. Tujuan fungsi 'flag/checkbox' pilihan borang adalah untuk memudahkan pengguna untuk menetapkan/menyenaraikan setiap pihak berkepentingan di borang C,D,E,F,G,H dan K. Tanda '/' pada kotak pilihan borang menunjukkan pihak berkepentingan tersebut telah dipilih dan sebalikynya. Setelah pilihan borang telah dipilih,sila klik butang 'Simpan Pilihan Borang' untuk menyimpan rekod.");
}


if(jenis_popup == "2")
{
new_window.document.write("<table width = '100%' >")
new_window.document.write("<tr>")
new_window.document.write("<td colspan = '3'>")
new_window.document.write("Tujuan medan 'Keterangan Jenis PB' adalah untuk memudahkan pengguna untuk menyatakan sebarang keterangan merujuk kepada jenis pihak berkepentingan yang telah dipilih. Contoh : ");
new_window.document.write("</td>")
new_window.document.write("</tr>")
new_window.document.write("</table>")
new_window.document.write("<table width = '100%'  >")
new_window.document.write("<tr>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Jenis Pihak Berkepentingan");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write(":");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Pemegang Amanah");
new_window.document.write("</td>")
new_window.document.write("</tr>")
new_window.document.write("<tr>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Keterangan Jenis PB");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write(":");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Pemegang Amanah Kepada Ali bin Abu");
new_window.document.write("</td>")
new_window.document.write("</tr>")

new_window.document.write("</table>")
}

if(jenis_popup == "3")
{
new_window.document.write("Skrin 'Pihak Berkepentingan' adalah skrin baru yang ditempatkan di Urusan Siasatan & Perintah Seksyen 8. Tujuan skrin ini adalah untuk memudahkan pengguna untuk menambah, menghapus, mengemaskini dan menetapkan pilihan borang pada mana-mana  pihak bekepentingan.");
}

new_window.document.write("<br>");

new_window.document.write("</body></html>");
new_window.document.close(); 

}

function close_window() 
{
new_window.close();
}

function viewSPTB(ID_FAIL,ID_HAKMILIK,ID_SEKSYEN) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewSPTB&action2=viewSPTB&ID_FAIL=" + ID_FAIL+"&ID_HAKMILIK=" + ID_HAKMILIK+"&ID_SEKSYEN=" + ID_SEKSYEN;
	document.${formName}.submit();
}
function checkWSHM(ID_HAKMILIK) {
	// 20100906 - kmie 
	// check hakmilik status with eTanah
    var url = "../x/${securityToken}/ekptg.view.integrasi.FrmViewETanah?action2=checkHM&isPPT=1&ID_HAKMILIK=" + ID_HAKMILIK;
    var hWnd = window.open(url, 'Semakan Status Hakmilik di Sistem eTanah', 'width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>