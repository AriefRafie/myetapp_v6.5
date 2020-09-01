<p> 
<input type="hidden" id="mode" name="mode" value="$!mode">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">
<input type="hidden" name="command5">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$ScreenLocation">
<input type="hidden" name="CursorPoint" value="$CursorPoint">
<input type="hidden" name="tabId">
<input type="hidden" name="id_permohonan" value="$id_permohonan">  
<input type="hidden" name="id_hakmilik" value="$!idTanah">
<input type="hidden" name="txtNoLot">
<input type="hidden" name="id_hakmilik_salin" id="id_hakmilik_salin" value="$!id_hakmilik_salin" />

<input type="hidden" name="txtLuasLotAmbilSebelumConvertHidden" value="$!txtLuasLotAmbilSebelumConvert">
<input type="hidden" name="txtLuasLotAsalSebelumConvertHidden" value="$!txtLuasLotAsalSebelumConvert">

<!-- validation convert -->
<input type="hidden" name="showFieldAsalBeforeConvert" value="$!showFieldAsalBeforeConvert">
<input type="hidden" name="showFieldAmbilBeforeConvert" value="$!showFieldAmbilBeforeConvert">
<input type="text" name="showButtonConvertAsal" value="$!showButtonConvertAsal">
<input type="text" name="showButtonConvertAmbil" value="$!showButtonConvertAmbil">
<input type="hidden" name="showDropdownUnitAmbil" value="$!showDropdownUnitAmbil">
<input type="text" name="showDropdownUnitAsal" value="$!showDropdownUnitAsal">
<input type="hidden" name="showBoxAmbil2" value="$!showBoxAmbil2">
<input type="hidden" name="showBoxAmbil3" value="$!showBoxAmbil3">
<input type="hidden" name="showBoxAsal2" value="$!showBoxAsal2">
<input type="hidden" name="showBoxAsal3" value="$!showBoxAsal3">
<input type="hidden" name="resetRadio">

</p>

#parse("app/ppt/frmLabelSet.jsp")
#foreach($data in $dataMaklumatTanah)

	#set($idPermohonan=$data.id_permohonan)
	#set($idTanah=$data.id_hakmilik)
	#set($txtNoHakmilik=$data.no_hakmilik)
	#set($noLot=$data.no_lot)
	#set($noPT=$data.no_pt)
	#set($luas=$data.luas_lot)
	#set($luasAmbil=$data.luas_ambil)
	#set($seksyen=$data.seksyen)
	#set($catatan=$data.catatan)
    #set($id_fail=$data.id_fail)
    #set($txtNoBangunan=$data.no_bangunan)
    #set($txtNoPetak=$data.no_petak)
    #set($txtNoTingkat=$data.no_tingkat)
    
    
#end

#if($semakTanah=="no")

#if($maklumat_Hakmilik_Salin.size()>0)

	#set($txtNoHakmilik=$maklumat_Hakmilik_Salin.no_hakmilik)
	#set($txtnolot=$maklumat_Hakmilik_Salin.no_lot)
	#set($txtnopt=$maklumat_Hakmilik_Salin.no_pt)
	#set($txtseksyen = $maklumat_Hakmilik_Salin.seksyen)
	#set($txtCatatan=$maklumat_Hakmilik_Salin.catatan)	
	
	<!-- PPT-03 Baru -->
	<!-- #set($txtNoBangunan=$maklumat_Hakmilik_Salin.no_bangunan)
	#set($txtNoTingkat=$maklumat_Hakmilik_Salin.no_tingkat)
	#set($txtNoPetak=$maklumat_Hakmilik_Salin.no_petak)-->

#else
	#set($txtNoHakmilik="")
	#set($txtnolot="")
	#set($txtnopt="")
	#set($txtseksyen = "")
	#set($txtCatatan="")	

#end

<fieldset id="changeTanah">
	<legend><strong>Maklumat Tanah</strong></legend>
  <table width="100%"  cellpadding="0" border="0">
        
         <tr>
            	<td width="20%">Negeri</td>
            	<td width="1%">:</td>
                <td width="79%"><input type="text" name="existNegeri" value="$existNegeri" size="42" class="disabled" readonly>
                	<input type="hidden" name="id_existNegeri" value="$id_existNegeri"></td>
        </tr>
            
        <tr>
            	#if($showJajahan=="yes")
             	<td>Jajahan</td>
             	#else
             	<td>Daerah</td>
             	#end
            	<td>:</td>
                <td><input type="text" name="existDaerah" value="$existDaerah" size="42" class="disabled" readonly>
                	<input type="hidden" name="id_existDaerah" value="$id_existDaerah"></td>
        </tr>
            
            	#if($showJajahan=="yes")
        <tr>
            	<td>Daerah</td>
            	<td>:</td>
            	<td>$!SelectDaerahPenggawa</td>
        </tr>
            #end
            
        <tr>
            	<td>Bandar/Pekan/Mukim <font color="red">*</font></td>
            	<td>:</td>
                <td>$SelectMukim</td>
        </tr>
          
        <tr>
             	#if($id_existNegeri != '16')
            	<td>Seksyen</td> 
            	#else
            	<td>Presint</td>
            	#end
            	<td>:</td>
                <td><input type="text" name="txtseksyen" size="22" value="$txtseksyen" maxlength="30" id="txtseksyen"  ></td>
        </tr>
          
        <tr>
				<td>Jenis Hakmilik</td>
				<td>:</td>
				<td>$!selectJenisHakmilik</td>
		</tr>	
		<tr>
				<td>No. Hakmilik</td>
				<td>:</td>
				<td><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$txtNoHakmilik" size="12" maxlength="50"  ></td>
		</tr>
			
			<!-- PPT-3 Baru -->
		<tr>     
                <td width="2%">
                <div align="left">
                	<span>No. Strata</span>
                </div>
                </td>
                <td width="1%">:</td>
                <td width="10%">
                	<span>No.Bang</span>&nbsp;<input name="txtNoBangunan" id="txtNoBangunan" type="text"value="$!txtNoBangunan" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
					<span>No.Ting</span>&nbsp;<input name="txtNoTingkat" id="txtNoTingkat" type="text"value="$!txtNoTingkat" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
					<span>No.Petak</span>&nbsp;<input name="txtNoPetak" id="txtNoPetak" type="text" value="$!txtNoPetak" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
  				</td>
        </tr>
			
        <tr>
            	<td>No.PT <font color="red">*</font></td>
            	<td>:</td>
            	<td>$!SelectLot</td>
       </tr>
            
       <tr>
            	<td>&nbsp;</td>
            	<td>&nbsp;</td>
                <td><input type="text" name="txtnopt" size="22" value="$txtnopt" maxlength="20" id="txtnopt"  ></td>
       </tr>
             
       <tr>
            	<td>No.LOT <font color="red">*</font></td>
            	<td>:</td>
                <td>
                <!-- defaultLOT() -->
                <input type="text" name="txtnolot" id="txtnolot" value="$txtnolot" size="12" maxlength="20" onclick="" onkeydown="checkExistLot('new','no')" onkeyup="checkExistLot('new','no')" onkeypress="checkExistLot('new','no')" onblur="checkExistLot('new','no')">
                &nbsp;<label id="checkLot" style="color:red" ></label>
   
                 <a href="javascript:popupCarianHakmilikSalin('$id_permohonan','skrin_hakmilik_sek4','$id_existDaerah')"><font color="blue">>> SALIN MAKLUMAT HAKMILIK/LOT</font></a>
              
                </td>
       </tr>
            <!-- PENAMBAHBAIKAN V7 yati -->
       <tr>
				<td>Kategori Tanah<font color="red">*</font></td>
				<td>:</td>
				<td>$!selectKategoriTanah</td>
	   </tr>
       <tr>
			<td>Unit Luas Asal<font color="red">*</font></td>
			<td>:</td>
			<td>$!selectUnitLuasLot</td>
	   </tr>
		
		<tr>
			<td valign="top">Luas Asal<font color="red">*</font></td>
			<td valign="top">:</td>
			<td>
			
			#if($showFieldAsalBeforeConvert=="yes") 
				<input type="text" name="txtLuasLotAsalSebelumConvert" id="txtLuasLotAsalSebelumConvert" size="30" value="$!txtLuasLotAsalSebelumConvert" maxlength="100" onblur="javasript:updatetxtLuasLotAsalSebelumConvert('$!txtLuasLotAsalSebelumConvert')" />	<p /> 
				#end 
				<input type="text" name="txtLuasLotAsal" id="txtLuasLotAsal" size="15" value="$!txtLuasLotAsal" maxlength="20" onkeyup="validateNilai(this,this.value);" />
				#if($showBoxAsal2=="yes" || $showBoxAsal3=="yes")
				<input type="text" name="txtLuasLotAsal2" id="txtLuasLotAsal2" size="15" value="$!txtLuasLotAsal2" maxlength="20" onkeyup="validateNilai(this,this.value);" />
				#end
				#if($showBoxAsal3=="yes")
				<input type="text" name="txtLuasLotAsal3" id="txtLuasLotAsal3" size="15" value="$!txtLuasLotAsal3" maxlength="20" onkeyup="validateNilai(this,this.value);" />
				#end
				
				#if($showDropdownUnitAsal=="yes") 
				<select name="sorDropdownUnitAsal" style="width: 132px" onchange="onchangeUnitAsal()">
					#if($sorDropdownUnitAsal=="1")
					<option value="1">HEKTAR</option>
					<option value="2">METER PERSEGI</option>
					#elseif($sorDropdownUnitAsal=="2")
					<option value="2">METER PERSEGI</option>
					<option value="1">HEKTAR</option> #else
					<option value="">SILA PILIH</option>
					<option value="1">HEKTAR</option>
					<option value="2">METER PERSEGI</option> #end

			</select> #end

			</td>
		</tr>

		#if($showButtonConvertAsal=="yes" || $showFieldAsalBeforeConvert=="yes")
		<tr>
		<!--  <td colspan="3">&nbsp;</td> -->	
			<td colspan="2">&nbsp;</td>
			<td>
			#if($showButtonConvertAsal=="yes") 
			
			<input type="button" name="cmdConvert" value="Convert" onClick="javascript:convertNilaiAsal('$!showBoxAsal2','$!showBoxAsal3')">
			#end 
			#if($showFieldAsalBeforeConvert=="yes") <input type="button" name="cmdClear" value="Kosongkan" onClick="javascript:clearConvertAsal()"> 
			#end
			</td>
		</tr>
		#end

		<tr>
			<td>Unit Luas Ambil<font color="red">*</font></td>
			<td>:</td>
			<td>$!selectUnitLuasAmbil</td>
		</tr>
		<tr>
			<td valign="top">Luas Diambil<font color="red">*</font></td>
			<td valign="top">:</td>
			<td>
				#if($showFieldAmbilBeforeConvert=="yes") 
				<input type="text" name="txtLuasLotAmbilSebelumConvert" id="txtLuasLotAmbilSebelumConvert" size="50" value="$!txtLuasLotAmbilSebelumConvert" maxlength="100" onblur="javascript:updatetxtLuasLotAmbilSebelumConvert('$!txtLuasLotAmbilSebelumConvert')" /><p /> 
				#end 
				<input type="text" name="txtLuasLotAmbil" id="txtLuasLotAmbil" size="15" value="$!txtLuasLotAmbil" maxlength="20" onkeyup="validateNilai(this,this.value);" />
				#if($showBoxAmbil2=="yes" || $showBoxAmbil3=="yes")
				<input type="text" name="txtLuasLotAmbil2" id="txtLuasLotAmbil2" size="15" value="$!txtLuasLotAmbil2" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
				#if($showBoxAmbil3=="yes")
				<input type="text" name="txtLuasLotAmbil3" id="txtLuasLotAmbil3" size="15" value="$!txtLuasLotAmbil3" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end

				#if($showDropdownUnitAmbil=="yes") 
				<select name="sorDropdownUnitAmbil" style="width: 132px" onchange="onchangeUnitAmbil()">

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

			</select> #end

			</td>
		</tr>

		#if($showButtonConvertAmbil=="yes" || $showFieldAmbilBeforeConvert=="yes")
		<tr>
				<td colspan="2">&nbsp;</td>
			<td>
			#if($showButtonConvertAmbil=="yes") 
			<input type="button" name="cmdConvert" value="Convert" onClick="javascript:convertNilaiAmbil('$!showBoxAmbil2','$!showBoxAmbil3')">
			#end 
			#if($showFieldAmbilBeforeConvert=="yes") 
			<input type="button" name="cmdClear" value="Kosongkan" onClick="javascript:clearConvertAmbil()"> 
			#end
			</td>
		</tr>
		#end          
            <tr>
            	<td valign="top">Catatan</td>
            	<td valign="top">:</td>
            	<td><textarea name="txtCatatan" id="txtCatatan" cols="90%" rows="15" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" >$txtCatatan</textarea></td>
            </tr>
            <tr>
        		<td>&nbsp;</td>
             	<td>&nbsp;</td>
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

#if($semakTanah=="yes")

<fieldset id="changeTanah">
	<legend><strong>Maklumat Tanah</strong></legend>
	<table width="100%"  cellpadding="1" border="0">
        
      #if($wantedit=="no")
        	
  <tr>
            	<td width="20%">Negeri</td>
            	<td width="1%">:</td>
                <td width="79%"><input type="text" name="existNegeri" value="$existNegeri" size="42" class="disabled" readonly>
                	<input type="hidden" name="id_existNegeri" value="$id_existNegeri"></td>
            </tr>
            
            <tr>
            	#if($showJajahan=="yes")
             	<td>Jajahan</td>
             	#else
             	<td>Daerah</td>
             	#end
            	<td>:</td>
                <td><input type="text" name="existDaerah" value="$existDaerah" size="42" class="disabled" readonly>
                	<input type="hidden" name="id_existDaerah" value="$id_existDaerah"></td>
            </tr>
            
            #if($showJajahan=="yes")
            <tr>
            	<td>Daerah</td>
            	<td>:</td>
            	<td>$!SelectDaerahPenggawa</td>
            </tr>
            #end
            
            <tr>
            	<td>Bandar/Pekan/Mukim</td>
            	<td>:</td>
                <td>$selectMukim</td>
            </tr>
            
            <tr>
            	#if($id_existNegeri != '16')
            	<td>Seksyen</td> 
            	#else 
            	<td>Presint</td>
            	#end
            	<td>:</td>
                <td><input type="text" name="txtseksyen" value="$!seksyen" size="22" maxlength="30" id="txtseksyen"   readonly class="disabled" ></td>
            </tr>
            
            <tr>
				<td>Jenis Hakmilik</td>
				<td>:</td>
				<td>$!selectJenisHakmilik</td>
			</tr>	
			<tr>
				<td>No. Hakmilik</td>
				<td>:</td>
				<td><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="12" maxlength="50" readonly class="disabled"></td>
			</tr>
			
			<!-- PPT-3 Baru -->
			<tr>     
                <td width="2%">
                <div align="left">
                	<span>No. Strata</span>
                </div>
                </td>
                <td width="1%">:</td>
                <td width="10%">
                	<span>No.Bang</span>&nbsp;<input name="txtNoBangunan" id="txtNoBangunan" type="text"value="$!txtNoBangunan" size="3" maxlength="3" readonly class="disabled" onkeyup="this.value=this.value.toUpperCase();"/>
					<span>No.Ting</span>&nbsp;<input name="txtNoTingkat" id="txtNoTingkat" type="text"value="$!txtNoTingkat" size="3" maxlength="3" readonly class="disabled" onkeyup="this.value=this.value.toUpperCase();"/>
					<span>No.Petak</span>&nbsp;<input name="txtNoPetak" id="txtNoPetak" type="text" value="$!txtNoPetak" size="3" maxlength="3" readonly class="disabled" onkeyup="this.value=this.value.toUpperCase();"/>
  				</td>
            </tr>
			
            <tr>
            	<td>No.PT</td>
            	<td>:</td>
            	<td>$!selectLot</td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>&nbsp;</td>
                <td><input type="text" name="txtnopt" size="22" value="$!noPT" maxlength="20" id="txtnopt"   readonly class="disabled"></td>
            </tr>
            
            <tr>
            	<td>No.LOT</td>
            	<td>:</td>
                <td><input type="text" name="txtnolot" size="22" value="$!noLot" maxlength="20" id="txtnolot"   readonly class="disabled" 
                
               onkeydown="checkExistLot('view','yes'')" onkeyup="checkExistLot('view','yes')" onkeypress="checkExistLot('view','yes')" onblur="checkExistLot('view','yes')"></td>
            </tr>
            
            <tr>
            	<td valign="top">Catatan</td>
            	<td valign="top">:</td>
            	<td><textarea name="txtCatatan" id="txtCatatan" cols="90%" rows="15" readonly class="disabled">$!catatan</textarea></td>
            </tr>
            
        #end    
        
        #if($wantedit=="yes")
        	
             <tr>
            	<td width="20%">Negeri</td>
            	<td width="1%">:</td>
                <td width="79%"><input type="text" name="existNegeri" value="$existNegeri" size="42" class="disabled" readonly>
                	<input type="hidden" name="id_existNegeri" value="$id_existNegeri"></td>
             </tr>
            
            <tr>
            	#if($showJajahan=="yes")
             	<td>Jajahan</td>
             	#else
             	<td>Daerah</td>
             	#end
            	<td>:</td>
                <td><input type="text" name="existDaerah" value="$existDaerah" size="42" class="disabled" readonly>
                	<input type="hidden" name="id_existDaerah" value="$id_existDaerah"></td>
            </tr>
           
            #if($showJajahan=="yes")
            <tr>
            	<td>Daerah</td>
            	<td>:</td>
            	<td>$!SelectDaerahPenggawa</td>
            </tr>
            #end
            
            <tr>
            	<td>Bandar/Pekan/Mukim <font color="red">*</font></td>
            	<td>:</td>
                <td>$selectMukim</td>
            </tr>
           
           	<tr>
            	<td>Seksyen</td>
            	<td>:</td>
                <td><input type="text" name="txtseksyen" value="$!seksyen" size="22" maxlength="30" id="txtseksyen"   ></td>
            </tr>
            
             <tr>
				<td>Jenis Hakmilik</td>
				<td>:</td>
				<td>$!selectJenisHakmilik</td>
			</tr>	
			<tr>
				<td>No. Hakmilik</td>
				<td>:</td>
				<td><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="12" maxlength="50"></td>
			</tr>
			
			<!-- PPT-3 Baru -->
			<tr>     
                <td width="2%">
                <div align="left">
                	<span>No. Strata</span>
                </div>
                </td>
                <td width="1%">:</td>
                <td width="10%">
                	<span>No.Bang</span>&nbsp;<input name="txtNoBangunan" id="txtNoBangunan" type="text"value="$!txtNoBangunan" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
					<span>No.Ting</span>&nbsp;<input name="txtNoTingkat" id="txtNoTingkat" type="text"value="$!txtNoTingkat" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
					<span>No.Petak</span>&nbsp;<input name="txtNoPetak" id="txtNoPetak" type="text" value="$!txtNoPetak" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
  				</td>
            </tr>
			
            <tr>
            	<td>No.PT</td>
            	<td>:</td>
            	<td>$!selectLot</td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>&nbsp;</td>
                <td><input type="text" name="txtnopt" size="22" value="$!noPT" maxlength="20" id="txtnopt"  ></td>
            </tr>
            
            <tr>
            	<td>No.LOT</td>
            	<td>:</td>
                <td><input type="text" name="txtnolot" id="txtnolot" value="$!noLot" size="12" maxlength="20" onclick="defaultLOT()" 
                onkeydown="checkExistLot('view','yes'')" onkeyup="checkExistLot('view','yes')" onkeypress="checkExistLot('view','yes')" onblur="checkExistLot('view','yes')">
                &nbsp;<label id="checkLot" style="color:red" ></label></td>
            </tr>
            
            <!-- view penambahbaikan yati -->
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
			<td>#if($showFieldAsalBeforeConvert=="yes") <input type="text"
				name="txtLuasLotAsalSebelumConvert" $disability $disabilityx
				id="txtLuasLotAsalSebelumConvert" size="50"
				value="$!txtLuasLotAsalSebelumConvert" maxlength="100"
				onblur="javasript:updatetxtLuasLotAsalSebelumConvert('$!txtLuasLotAsalSebelumConvert')" />
				<p /> #end 
					<input type="text" $disability $disabilityx name="txtLuasLotAsal"
						id="txtLuasLotAsal" size="15" value="$!txtLuasLotAsal"
						maxlength="20" onkeyup="validateNilai(this,this.value);" />
					#if($showBoxAsal2=="yes" || $showBoxAsal3=="yes")<input type="text"
						name="txtLuasLotAsal2" id="txtLuasLotAsal2" size="15"
						value="$!txtLuasLotAsal2" maxlength="20"
						onkeyup="validateNilai(this,this.value);" />#end
					#if($showBoxAsal3=="yes")<input type="text" name="txtLuasLotAsal3"
						id="txtLuasLotAsal3" size="15" value="$!txtLuasLotAsal3"
						maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
					
					#if($showDropdownUnitAsal=="yes")
					<select $disability1 $disabilityx name="sorDropdownUnitAsal"
						style="width:132px" onchange="onchangeUnitAsalUpdate()">
      		
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


		#if($isEdit=="yes" && ($showButtonConvertAsal=="yes" ||
		$showFieldAsalBeforeConvert=="yes"))
		<tr>
			<td colspan="3">&nbsp;</td>
			<td>#if($showButtonConvertAsal=="yes") <input type="button"
				name="cmdConvert" value="Convert"
				onClick="javascript:convertNilaiAsalUpdate('$!showBoxAsal2','$!showBoxAsal3')">
				#end #if($showFieldAsalBeforeConvert=="yes") <input type="button"
				name="cmdClear" value="Kosongkan"
				onClick="javascript:clearConvertAsalUpdate()"> #end
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
			<td>#if($showFieldAmbilBeforeConvert=="yes") <input type="text"
				$disability $disabilityx name="txtLuasLotAmbilSebelumConvert"
				id="txtLuasLotAmbilSebelumConvert" size="50"
				value="$!txtLuasLotAmbilSebelumConvert" maxlength="100"
				onblur="javascript:updatetxtLuasLotAmbilSebelumConvert($!txtLuasLotAmbilSebelumConvert)" />
				<p /> #end 
					<input $disability $disabilityx type="text" name="txtLuasLotAmbil"
						id="txtLuasLotAmbil" size="15" value="$!txtLuasLotAmbil"
						maxlength="20" onkeyup="validateNilai(this,this.value);" />
					#if($showBoxAmbil2=="yes" || $showBoxAmbil3=="yes")<input
						type="text" name="txtLuasLotAmbil2" id="txtLuasLotAmbil2"
						size="15" value="$!txtLuasLotAmbil2" maxlength="20"
						onkeyup="validateNilai(this,this.value);" />#end
					#if($showBoxAmbil3=="yes")<input type="text"
						name="txtLuasLotAmbil3" id="txtLuasLotAmbil3" size="15"
						value="$!txtLuasLotAmbil3" maxlength="20"
						onkeyup="validateNilai(this,this.value);" />#end
				
					#if($showDropdownUnitAmbil=="yes")
					<select $disability1 $disabilityx name="sorDropdownUnitAmbil"
						style="width:132px" onchange="onchangeUnitAmbilUpdate()">
      		
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

		#if($isEdit=="yes" && ($showButtonConvertAmbil=="yes" ||
		$showFieldAmbilBeforeConvert=="yes"))
		<tr>
			<td colspan="3">&nbsp;</td>
			<td>#if($showButtonConvertAmbil=="yes") <input type="button"
				name="cmdConvert" value="Convert"
				onClick="javascript:convertNilaiAmbilUpdate('$!showBoxAmbil2','$!showBoxAmbil3')">
				#end #if($showFieldAmbilBeforeConvert=="yes") <input type="button"
				name="cmdClear" value="Kosongkan"
				onClick="javascript:clearConvertAmbilUpdate()"> #end
			</td>
		</tr>
		#end 
           	<tr>
            	<td valign="top">Catatan</td>
            	<td valign="top">:</td>
            	<td><textarea name="txtCatatan" id="txtCatatan" cols="90%" rows="15" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" >$!catatan</textarea></td>
            </tr>
            <tr>
        		<td>&nbsp;</td>
             	<td>&nbsp;</td>
             	<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="4000"></td>
           	</tr> 
            
        #end    
        </table>
        
        #if($wantedit=="yes")
        <table width="100%" border="0">
        	<tr>
        		<td>$!perhatian1</td>
        	</tr>
        </table>
        #end
      
    </fieldset>
#end

	<table width="100%"  cellpadding="0" border="0">
    	<tr align="center">
        	<td>
        	<!-- #if($currentStatus=="11" || $currentStatus=="113")#end -->
           
           #if($wantedit=="no")
            <input type="button" name="cmdSimpan" value ="Semakan Maklumat Geran" onClick="javascript:viewSPTB('$!id_fail','$idTanah','1')">
			#end
            
            
            
        	#if($semakTanah=="yes")
        		#if($wantedit=="yes")
        		<input name="cmdUpdate" type="button" value="Simpan" onClick="update_maklumat('$idTanah')">
        		<input name="cmdBatal" type="button" value="Batal" onClick="edit_maklumat('$idTanah')">
        		#end
        		#if($wantedit=="no")
        			<input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniTanah()">
        			<input name="cmdHapus" type="button" value="Hapus" onClick="delete_maklumatTanah('$idTanah')"> 
        		#end
        	#end
        		
        		#if($semakTanah=="no")
                <input name="cmdSimpan" type="button" value="Simpan" onClick="add_maklumat_tanah('$!id_permohonan','$!id_hakmilik','$!flag_subjaket','$!mode')">
           
                #end
                
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali('$id_permohonan')">    
       	    
       	    </td>
        </tr>
    </table>
    
	<fieldset id="bottom">
    <legend><strong>Maklumat Tanah Terlibat</strong> 
      #if($semakTanah=="yes")
                  <input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambah();">
    			  #end	
    </legend>
    
     <table width="100%"   border="0">   
            <tr><td  align="left">
            <a href="javascript:popupCarianHakmilik('$id_permohonan','skrin_hakmilik_sek4')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
            </td>
            </tr>
            </table>
    
    <!--

		<table width="100%"  cellpadding="0" cellspacing="2" border="0">   
            <tr align="right">
            
            	  #if($semakTanah=="yes")
                  <td width="30%" align="left"><input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambah();"></td>
    			  #end	
 				<td width="70%" align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot2" value="$!carianNoLot2" maxlength="20" size="20"   ><a href="javascript:cariLOT('$!id_permohonan','$!semakTanah','$!idTanah')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan','$!semakTanah','$!idTanah')">&nbsp;<u>KOSONGKAN</u></a></td>
    		</tr>
    	</table>

		#if($saiz_listTanah > 5)
        <div style="width:100%;height:100;overflow:auto"> 
        #end
                
		<table width="100%"  cellpadding="0" cellspacing="2" border="0">   
    
            <tr class="table_header">
                  <td width="4%" align="center"><b>No</b></td>
                  <td width="46%"><b>&nbsp;Bandar/Pekan/Mukim</b></td>
                  <td width="50%"><b>No.LOT/No.PT</b></td> 
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
                <td class="$row"><a href="javascript:viewMaklumat('$listTanah.id_hakmilik')"><font color="blue">$!listTanah.nama_mukim</font></a></td>
                <td class="$row">$!listTanah.no_lotpt</td>
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
    

<script>
window.onload = submitForm;
function checkExistLot(mode,isEdit){
	document.${formName}.txtNoLot.value = document.${formName}.txtnolot.value;
	if((mode=="new" || (mode=="view" && isEdit=="yes")) && (document.${formName}.txtNoLot.value != "" && document.${formName}.txtNoLot.value != "TDK"))
	{
		url = "../servlet/ekptg.view.ppt.FrmPengambilanCheck?command=checkExistLot";
		actionName = "checkExistLot";
		target = "checkLot";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		return;
	}
}


function popupCarianHakmilikSalin(id_permohonan,flag_skrin,id_daerah)
{
	var no_lot = document.${formName}.txtnolot.value;	
	var no_hakmilik = document.${formName}.txtNoHakmilik.value;	
	var id_mukim = document.${formName}.mukim.value;	
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupSalinPBHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin+"&NO_HAKMILIK="+no_hakmilik+"&NO_LOT="+no_lot+"&ID_MUKIM="+id_mukim+"&ID_DAERAH="+id_daerah;
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


function defaultLOT(){

	if(document.${formName}.txtnolot.value==""){
		document.${formName}.txtnolot.value = "LOT";
	}
	
}


function add_maklumat_tanah(id_permohonan,id_hakmilik,flagSubjaket,mode)
{   
	var alert_lot = "N";	
	if (document.${formName}.check_lot == null || document.${formName}.check_lot == undefined) 
	{	
	alert_lot = "N";	
	}else
	{
	alert_lot =	document.${formName}.check_lot.value;
	}
	document.${formName}.ScreenLocation.value = "changeTanah";
	
	//luas = parseInt(document.${formName}.luas_lot.value);
	//luasAmbil = parseInt(document.${formName}.anggaran_luas.value);

	/*if (luasAmbil > luas) {
		alert ("Anggaran luas melebihi luas lot");
		document.${formName}.anggaran_luas.focus();
		return;
	}
	if(document.${formName}.jenisHakMilik.value == ""){
		alert("Sila pilih \"Jenis hakmilik\" terlebih dahulu.");
  		document.${formName}.jenisHakMilik.focus(); 
		return;
	}
	if(document.${formName}.lot.value == ""){
		alert("Sila pilih \"Kod Lot\" terlebih dahulu.");
  		document.${formName}.lot.focus(); 
		return;
	}
*/	
	if(document.${formName}.mukim.value == ""){
		alert("Sila pilih \"Bandar/Pekan/Mukim\" terlebih dahulu.");
  		document.${formName}.mukim.focus(); 
		return;
	}
/*	if(document.${formName}.luas.value == ""){
		alert("Sila pilih \"Unit luas\" terlebih dahulu.");
  		document.${formName}.luas.focus(); 
		return;
	}*/
	else if((document.${formName}.lot.value != "" && document.${formName}.txtnopt.value == "") || (document.${formName}.lot.value == "" && document.${formName}.txtnopt.value != "")){
		alert("Sila lengkapkan maklumat \"No.PT\" terlebih dahulu.");
  		document.${formName}.lot.focus(); 
		return;
	}
	else if(document.${formName}.txtnolot.value == "" && document.${formName}.txtnopt.value == ""){
		alert("Sila masukkan salah satu \"No.PT atau No.LOT\" terlebih dahulu.");
  		document.${formName}.txtnolot.focus(); 
		return;
	}	
/*	else if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan \"Catatan\" terlebih dahulu.");
  		document.${formName}.txtCatatan.focus(); 
		return;
	} */
	
	else if(alert_lot=="Y")
	{
	alert("No. Lot telah wujud di dalam permohonan ini!");
	return;
	}
	else
	{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=addMaklumatTanah&id_permohonan="+id_permohonan;
	document.${formName}.submit();
	}
	
}


function salin_hakmilik(id_permohonan,id_hakmilik_salin)
{
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "salinHakmilik";
	document.${formName}.id_permohonan.value = id_permohonan;	
	document.${formName}.id_hakmilik_salin.value = id_hakmilik_salin;	
	//document.${formName}.subminor_command.value = "salin_hakmilik";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	//document.${formName}.location.value = "maklumat_pb";
	//document.${formName}.point.value = "socJenisPB";
	//document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();	
}


function kemaskiniTanah() {

	document.${formName}.ScreenLocation.value = "changeTanah";
	document.${formName}.CursorPoint.value = "editMukim";
	
	document.${formName}.command.value = "kemaskiniTanah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
}


function edit_maklumat(id_hakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "edit_maklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}


function kembali(id_permohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=semak&id_permohonan="+id_permohonan;
	document.${formName}.submit();
}


function update_maklumat(id_hakmilik) {
	
	var alert_lot = "N";	
	if (document.${formName}.check_lot == null || document.${formName}.check_lot == undefined) 
	{	
	alert_lot = "N";	
	}else
	{
	alert_lot =	document.${formName}.check_lot.value;
	}

	document.${formName}.ScreenLocation.value = "changeTanah";
	
/*	Editluas = parseInt(document.${formName}.edit_luas_lot.value);
	EditluasAmbil = parseInt(document.${formName}.edit_anggaran_luas.value);

	if (EditluasAmbil > Editluas) {
		alert ("Anggaran luas melebihi luas lot");
		document.${formName}.edit_anggaran_luas.focus();
		return;
	}
	if(document.${formName}.editJenisHakmilik.value == ""){
		alert("Sila pilih \"Jenis hakmilik\" terlebih dahulu.");
  		document.${formName}.editJenisHakmilik.focus(); 
		return;
	}
	if(document.${formName}.editLot.value == ""){
		alert("Sila pilih \"Kod Lot\" terlebih dahulu.");
  		document.${formName}.editLot.focus(); 
		return;
	}
	if(document.${formName}.edit_no_lot.value == ""){
		alert("Sila masukkan \"No.Lot / No.PT\" terlebih dahulu.");
  		document.${formName}.edit_no_lot.focus(); 
		return;
	} */
	if(document.${formName}.editMukim.value == ""){
		alert("Sila pilih \"Bandar/Pekan/Mukim\" terlebih dahulu.");
  		document.${formName}.editMukim.focus(); 
		return;
	}
/*	if(document.${formName}.editLuas.value == ""){
		alert("Sila pilih \"Unit luas\" terlebih dahulu.");
  		document.${formName}.editLuas.focus(); 
		return;
	}*/
	else if((document.${formName}.editLot.value != "" && document.${formName}.txtnopt.value == "") || (document.${formName}.editLot.value == "" && document.${formName}.txtnopt.value != "")){
		alert("Sila lengkapkan maklumat \"No.PT\" terlebih dahulu.");
  		document.${formName}.editLot.focus(); 
		return;
	}
	else if(document.${formName}.txtnolot.value == "" && document.${formName}.txtnopt.value == ""){
		alert("Sila masukkan salah satu \"No.PT atau No.LOT\" terlebih dahulu.");
  		document.${formName}.txtnolot.focus(); 
		return;
	}
/*	else if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan \"Catatan\" terlebih dahulu.");
  		document.${formName}.txtCatatan.focus(); 
		return;
	} */
	
	else if(alert_lot=="Y")
	{
	alert("No. Lot telah wujud di dalam permohonan ini!");
	return;
	}
	else
	{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "updateMaklumatTanah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();	
	}
}


function delete_maklumatTanah(id_hakmilik) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "deleteMaklumatTanah";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}


function validate(elmnt,content) {
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


function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}


function viewMaklumat(id_hakmilik) {
	
	document.${formName}.ScreenLocation.value = "changeTanah";

	//document.${formName}.carianNoLot2.value = "";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "edit_maklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}


function cariLOT(idpermohonan,mode,idhakmilik) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	if(mode=="yes"){
		document.${formName}.command.value = "edit_maklumat";
		document.${formName}.id_hakmilik.value = idhakmilik;
	}else{
		document.${formName}.command.value = "tambah";
	}
		
	document.${formName}.id_permohonan.value = idpermohonan;	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}


function kosongkanLOT(idpermohonan,mode,idhakmilik) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	if(mode=="yes"){
		document.${formName}.command.value = "edit_maklumat";
		document.${formName}.id_hakmilik.value = idhakmilik;
	}else{
		document.${formName}.command.value = "tambah";
	}
	
	document.${formName}.carianNoLot2.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}

 
function tambah() {
	
	document.${formName}.ScreenLocation.value = "changeTanah";
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=tambah"; 
	document.${formName}.submit();
}


function viewSPTB(ID_FAIL,ID_HAKMILIK,ID_SEKSYEN) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewSPTB&action2=viewSPTB&ID_FAIL=" + ID_FAIL+"&ID_HAKMILIK=" + ID_HAKMILIK+"&ID_SEKSYEN=" + ID_SEKSYEN;
	document.${formName}.submit();
}
//PENAMBAHBAIKAN V7 - yati
function updatetxtLuasLotAmbilSebelumConvert(txtLuasLotAmbilSebelumConvert){
	document.${formName}.txtLuasLotAmbilSebelumConvertHidden.value = document.${formName}.txtLuasLotAmbilSebelumConvert.value;
}
function updatetxtLuasLotAsalSebelumConvert(txtLuasLotAsalSebelumConvert){
	document.${formName}.txtLuasLotAsalSebelumConvertHidden.value = document.${formName}.txtLuasLotAsalSebelumConvert.value;
}

function onchangeUnitAmbil(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambah";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAmbil";
	document.${formName}.command4.value = "onchangeUnitAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function onchangeUnitAsal(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambah";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAsal";
	document.${formName}.command4.value = "onchangeUnitAsal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}

/*function onchangeUnitLuasAsal(){
	alert("fdffdfgfd");
	document.${formName}.ScreenLocation.value = "top";
	alert(1);
	document.${formName}.command.value = "tambah";
	alert(2);
	document.${formName}.command2.value = "doOnchange";
	alert(3);
	document.${formName}.command3.value = "onchangeUnitLuasAsal";
	alert(4);
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}*/
function onchangeUnitLuasAsal(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambah";
	alert(document.${formName}.command.value);
	document.${formName}.command2.value = "doOnchange";
	alert(document.${formName}.command2.value);
	document.${formName}.command3.value = "onchangeUnitLuasAsal";
	alert(document.${formName}.command3.value);
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function onchangeUnitLuasAmbil(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambah";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function clearConvertAsal(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambah";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAsal";
	document.${formName}.command4.value = "clearConvertAsal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function clearConvertAmbil(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambah";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAmbil";
	document.${formName}.command4.value = "clearConvertAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}

//update luas
function clearConvertAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.command5.value = "clearConvertAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function convertNilaiAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.command5.value = "convertNilaiAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function onchangeUnitLuasAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function onchangeUnitAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.command5.value = "onchangeUnitAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function clearConvertAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.command5.value = "clearConvertAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function convertNilaiAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.command5.value = "convertNilaiAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function onchangeUnitLuasAsalUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAsalUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function doOnchangeUpdate(resetVal){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.resetRadio.value = resetVal;
	document.${formName}.command.value = "viewHM";
	document.${formName}.command2.value = "kemaskiniHM";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
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
		document.${formName}.command.value = "tambah";
		document.${formName}.command2.value = "doOnchange";
		document.${formName}.command3.value = "onchangeUnitLuasAsal";
		document.${formName}.command4.value = "convertNilaiAsal";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
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
		document.${formName}.command.value = "tambah";
		document.${formName}.command2.value = "doOnchange";
		document.${formName}.command3.value = "onchangeUnitLuasAmbil";
		document.${formName}.command4.value = "convertNilaiAmbil";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
		document.${formName}.submit();

	}//close else
	
}//close convertNilai Ambil
</script>

