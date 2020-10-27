#parse("app/ppt/SementaraPaging.jsp")


<!-- Checking id -->
#set($portal_role = "$!{session.getAttribute('_portal_role')}")

#if(($!id_pegawai=="") || ($!{session.getAttribute('_ekptg_user_id')} == $!id_pegawai) || ($portal_role == "(PPT)PengarahUnit" || $portal_role == "(PPT)PenolongPengarahUnit" || $portal_role == "adminppt"))
	#set($editable="yes")
	#set($disOtherId="")
	#set($disOtherId1="")
	#set($disOtherIdx="")
#else
	<!-- set($editable="no")
	set($disOtherId="readonly")
	set($disOtherId1="disabled")
	set($disOtherIdx="class=disabled") -->
	#set($editable="yes")
	#set($disOtherId="")
	#set($disOtherId1="")
	#set($disOtherIdx="")
#end

#foreach($listTanah in $listMaklumatTanah)
	#set($txtLuasKeseluruhan = "$!listTanah.luas_keseluruhan")
    #set($id_hakmilik = "$!listTanah.id_hakmilik")
    <input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" />
#end


#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset id="top">
<legend><strong>Laporan Awal Tanah Penggunaan/Pendudukan Sementara</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>
<center>
	<fieldset>
	<table width="100%" border="0">
		<tr>
			<td width="25%">Nama Pegawai</td>
			<td width="1%">:</td>
			<td width="74%">$!selectPegawaiHM</td>
		</tr>
	</table>
	</fieldset>
	
	#if($showLaporan=="no")	
		<input type="hidden" name="carianNoLot">
	#else
	
	<br/>
	
	<fieldset>
	<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
			
			<table width="100%" border="0">   
                	<tr>
                    	<td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    			
    		#if($saiz_listTanah > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center"><b>No</b></td>
        			<td><b>No.Hakmilik</b></td>
                  	<td><b>QT</b></td>  
                  	<td><b>FT</b></td>               
                  	<td><b>Mukim/Pekan/Bandar</b></td>
            		<td><b>Keluasan</b></td>  
            		<td><b>Pegawai</b></td> 
        		</tr>
        		
        		#if($saiz_listTanah!=0)
                    #foreach($listTanah in $listMaklumatTanah)
                    <input type="hidden" name="nilaihm" id="nilaihm" value="$!Utils.RemoveSymbol($!listTanah.nilaiTanah)">	
                    
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listTanah.bil</td>
                   <td class="$row">$!listTanah.no_hakmilik</td>
                   <td class="$row">$!listTanah.no_lot</td>          
                   <td class="$row">$!listTanah.kod_lot$!listTanah.no_pt</td>     
                   <td class="$row">$!listTanah.nama_mukim</td>
                   <td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>
                   <td class="$row">$!listTanah.nama_pegawai</td>
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
	
	</fieldset>

	<br/>

	
	
	<input type="hidden" name="sizehm" id="sizehm" value="$!listMaklumatTanah.size()">
	
	#if($mode=="new")
	
	<fieldset id="changeLaporan">
	<legend><strong>Laporan Awal Tanah</strong></legend>
	
		
		<table width="100%" border="0">
			<tr>
				
				<!-- TABLE KIRI -->
				<td width="53%" valign="top"><table width="100%" border="0">
					
					<tr>
						<td width="37%">Tarikh Mula Charting</td>
						<td width="1%">:</td>
						<td width="62%"><input $disOtherId $disOtherIdx name="txdTarikhMula" value="" size="11" id="txdTarikhMula" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMula',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
				
					<tr>
						<td>Tarikh Tamat Charting</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx name="txdTarikhTamat" value="" size="11" id="txdTarikhTamat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTamat',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
					
					<tr>
						<td>Tarikh Lawat Tapak</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx name="txdTarikhLawat" value="" size="11" id="txdTarikhLawat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawat',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
                    
					<tr>
						<td>Tarikh Laporan</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx name="txdTarikhLapor" value="" size="11" id="txdTarikhLapor" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLapor',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>                     
					
<!--					<tr>
						<td>No. Peta Kadaster</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx type="text" name="txtNoPeta" id="txtNoPeta" value="" style="text-transform:uppercase;" maxlength="100" size="33" /></td>
					</tr>-->
					
					<tr>
						<td>Bil Keseluruhan Lot</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx type="text" name="txtBilKeseluruhan" id="txtBilKeseluruhan" value="$!totalHM" style="text-transform:uppercase;"  size="7" onkeyup="validateNumber(this,this.value)" /></td>
					</tr>
				
					<tr>
						<td>Luas Keseluruhan</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx type="text" name="txtLuasKeseluruhan" id="txtLuasKeseluruhan" value="$!txtLuasKeseluruhan" style="text-transform:uppercase;"  size="14" onkeyup="validateNumber(this,this.value)" />
                        
                       <select $disOtherId1 $disOtherIdx name="sorDropdownUnitAsal" style="width:132px" onchange="onchangeUnit()">
                                  		
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
                          
                        </td>
					</tr>
					
				</table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="47%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td width="56%">Anggaran Kasar Kos Tanah</td>
						<td width="1%">:</td>
						<td width="43%"><input $disOtherId $disOtherIdx type="text" maxlength="50" name="txtKosTanah" id="txtKosTanah" value="" size="19" /></td>
					</tr>
			
					<tr>
						<td>Anggaran Kasar Kos Bangunan</td>
						<td>:</td>
						<td><input $disOtherId $disOtherIdx type="text" maxlength="50" name="txtKosBangunan" id="txtKosBangunan" value="" size="19" /></td>
					</tr>
					
					<tr>
						<td valign="top">Perihal Bangunan</td>
						<td valign="top">&nbsp;</td>
						<td valign="top">&nbsp;</td>
					</tr>                                     
					
					<tr>
						<td colspan="3"><textarea $disotherid $disotheridx name="txtPerihal" id="txtPerihal" cols="53%" rows="4" onkeyup="textCounter(this.form.txtPerihal,this.form.remLen1,400);" onkeydown="textCounter(this.form.txtPerihal,this.form.remLen1,400);" ></textarea></td>
				  </tr>                                   
					
			  </table></td>
				
			</tr>
		</table>
	</fieldset>
<br/>	

	<fieldset>
	<legend><strong>Butiran Tanah</strong></legend>
	
		<table width="100%" border="0">
			<tr>
				<td width="19%">Kedudukan Tanah</td>
				<td width="1%">:</td>
				<td width="80%"><input name="sbcLuarKwsnSmpnMelayu" type="checkbox" id="sbcLuarKwsnSmpnMelayu" value="1" />
Di Luar Kawasan Simpanan Melayu</td>
			</tr>					
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input type="checkbox" name="sbcDlmKwsnSmpnMelayu" id="sbcDlmKwsnSmpnMelayu" value="1" />
Di Dalam Kawasan Simpanan Melayu</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input name="sbcLuarKwsnMajlisDaerah" type="checkbox" id="sbcLuarKwsnMajlisDaerah" value="1" />
Di Luar Kawasan Majlis Daerah</td>
			</tr>					
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input name="sbcDlmKwsnMajlisDaerah" type="checkbox" id="sbcDlmKwsnMajlisDaerah" value="1" />
Di Dalam Kawasan Majlis Daerah</td>
			</tr>
			<tr>
				<td valign="top">Lokasi Tanah</td>
				<td valign="top">:</td>
				<td><textarea $disOtherId $disOtherIdx name="txtLokasi" id="txtLokasi" cols="50%" rows="4" onkeyup="textCounter(this.form.txtLokasi,this.form.remLen2,400);" onkeydown="textCounter(this.form.txtLokasi,this.form.remLen2,400);" ></textarea></td>
			</tr>
			<tr>
				<td>Jenis Tanah</td>
				<td>:</td>
				<td><input type="radio" name="sorJenisTanah" id="sorJenisTanah" value="1" > Tanah Desa </td>
			</tr>
            <tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input type="radio" name="sorJenisTanah" id="sorJenisTanah" value="2" > Tanah Pekan </td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input type="radio" name="sorJenisTanah" id="sorJenisTanah" value="3" > Tanah Bandar </td>
			</tr>                                                                           
			  </table>
	</fieldset>
    
<br/>	
	<fieldset>
	<legend><strong>Keadaan Rupabumi</strong></legend>
	
		<table width="100%" border="0">
			<tr>
			
				<!-- TABLE KIRI -->
				<td width="50%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td>Keseluruhan Lot</td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtRupabumiSeluruhLot" id="txtRupabumiSeluruhLot" cols="48%" rows="4" onKeyUp="textCounter(this.form.txtRupabumiSeluruhLot,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtRupabumiSeluruhLot,this.form.remLen2,400);" ></textarea></td>
					</tr>                                                                           
					
			  </table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="50%" valign="top"><table width="100%" border="0">
					<tr>
						<td>Kawasan yang Terlibat</td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtRupabumiKwsTerlibat" id="txtRupabumiKwsTerlibat" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtRupabumiKwsTerlibat,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtRupabumiKwsTerlibat,this.form.remLen2,400);" ></textarea></td>
					</tr>                       
                    
			  </table></td>
				
			</tr>
		</table>
		
	</fieldset>    
    
    
    
    
    
    
    
    
    
<br/>	
	<fieldset>
	<legend><strong>Keadaan Lot</strong></legend>
	
		<table width="100%" border="0">
			<tr>
			
				<!-- TABLE KIRI -->
				<td width="50%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td>Keseluruhan Lot</td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtKeadaanLotSeluruh" id="txtKeadaanLotSeluruh" cols="48%" rows="4" onKeyUp="textCounter(this.form.txtKeadaanLotSeluruh,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtKeadaanLotSeluruh,this.form.remLen2,400);" ></textarea></td>
					</tr>
					
					<tr>
						<td>Jenis Tanaman</td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtKeadaanLotJnsTanaman" id="txtKeadaanLotJnsTanaman" cols="48%" rows="4" onKeyUp="textCounter(this.form.txtKeadaanLotJnsTanaman,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtKeadaanLotJnsTanaman,this.form.remLen2,400);" ></textarea></td>
					</tr>                                                                           
					
			  </table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="50%" valign="top"><table width="100%" border="0">
					<tr>
						<td>Keadaan Tanaman/Umur</td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtKeadaanLotTanaman" id="txtKeadaanLotTanaman" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtKeadaanLotTanaman,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtKeadaanLotTanaman,this.form.remLen2,400);" ></textarea></td>
					</tr>
					
					<tr>
						<td>Kawasan Terlibat</td>
					</tr>
					<tr>
						<td><textarea $disOtherId $disOtherIdx name="txtKeadaanLotKwsTerlibat" id="txtKeadaanLotKwsTerlibat" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtKeadaanLotKwsTerlibat,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtKeadaanLotKwsTerlibat,this.form.remLen2,400);" ></textarea></td>
					</tr>                       
                    
				</table></td>
				
			</tr>
		</table>
		
	</fieldset>        
    
    
    
    
    
    
    
        
	#end
	

	#if($mode=="view")
	
    #if($onchange=="no")
	#foreach($data in $dataLaporanTanah)
		#set($txdTarikhMula=$data.tarikh_mula_chart)
		#set($txdTarikhTamat=$data.tarikh_akhir_chart)
		#set($txdTarikhLawat=$data.tarikh_lawatan)
		#set($txtBilKeseluruhan=$data.bil_hakmilik)
		#set($txtLuasKeseluruhan=$data.luas_terlibat)
		#set($txtPerihal=$data.perihal_bangunan)
		##set($txtTanahSendiri=$data.perihal_tm_sendiri)
		##set($txtTanahNegeri=$data.perihal_tr_negeri)
		##set($txtTanahPersekutuan=$data.perihal_tmtr_sekutuan)
		##set($txtKeadaanTanah=$data.keadaan_rupabumi)
		#set($txtLokasi=$data.lokasi_tanah)
		##set($txtTanahMelayu=$data.perihal_kawasan_melayu)
		##set($txtTanahKerajaan=$data.perihal_tnh_kerajaan)
		##set($txtSyor=$data.ulasan_syor)
		#set($txtNoPeta=$data.no_peta_kadaster)
		#set($txtKosTanah=$data.harga_anggar)
		#set($txtKosBangunan=$data.harga_anggar_bangunan)
        #set($txdTarikhLapor=$data.tarikh_lapor)
        #set($flag_luar_simpanan=$data.flag_luar_simpanan)
        #set($flag_dlm_simpanan=$data.flag_dlm_simpanan)
        #set($flag_luar_majlis=$data.flag_luar_majlis)
        #set($flag_dlm_majlis=$data.flag_dlm_majlis) 
        #set($txtRupabumiSeluruhLot=$data.rupabumi_seluruh_lot)  
        #set($txtRupabumiKwsTerlibat=$data.rupabumi_kwsn_terlibat) 
        #set($flag_jenis_tanah=$data.flag_jenis_tanah)  
        #set($txtKeadaanLotSeluruh=$data.lot_seluruh_lot)
        #set($txtKeadaanLotTanaman=$data.lot_keadaan_tanaman)        
        #set($txtKeadaanLotJnsTanaman=$data.lot_jenis_tanaman)     
        #set($txtKeadaanLotKwsTerlibat=$data.lot_kwsn_terlibat)
        #set($sorDropdownUnitAsal=$data.id_unitluas)
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
	
	<fieldset id="changeLaporan">
	<legend><strong>Laporan Awal Tanah</strong></legend>
		
		<table width="100%" border="0">
			<tr>
				
				<!-- TABLE KIRI -->
				<td width="53%" valign="top"><table width="100%" border="0">
					
					<tr>
						<td width="37%">Tarikh Mula Charting</td>
						<td width="1%">:</td>
						<td width="62%"><input $disability $disabilityx name="txdTarikhMula" value="$!txdTarikhMula" size="11" id="txdTarikhMula" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMula',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
				
					<tr>
						<td>Tarikh Tamat Charting</td>
						<td>:</td>
						<td><input $disability $disabilityx name="txdTarikhTamat" value="$!txdTarikhTamat" size="11" id="txdTarikhTamat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTamat',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
					
					<tr>
						<td>Tarikh Lawat Tapak</td>
						<td>:</td>
						<td><input $disability $disabilityx name="txdTarikhLawat" value="$!txdTarikhLawat" size="11" id="txdTarikhLawat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawat',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
                    
					<tr>
						<td>Tarikh Laporan</td>
						<td>:</td>
					  <td><input $disability $disabilityx name="txdTarikhLapor" value="$!txdTarikhLapor" size="11" id="txdTarikhLapor" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
       		 			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLapor',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
					
					<tr>
						<td>Bil Keseluruhan Lot</td>
						<td>:</td>
						<td><input $disability $disabilityx type="text" name="txtBilKeseluruhan" id="txtBilKeseluruhan" value="$!txtBilKeseluruhan" style="text-transform:uppercase;"  size="7" onkeyup="validateNumber(this,this.value)" /></td>
					</tr>
				
					<tr>
						<td>Luas Keseluruhan</td>
						<td>:</td>
						<td><input $disability $disabilityx type="text" name="txtLuasKeseluruhan" id="txtLuasKeseluruhan" value="$!txtLuasKeseluruhan" style="text-transform:uppercase;"  size="15" onkeyup="validateNumber(this,this.value)" />
                          
                       <select $disability1 $disabilityx name="sorDropdownUnitAsal" style="width:125px" onchange="onchangeUnitUpdate()">
      		
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
                                                 
                          
                          </td>
					</tr>
					
					<!-- <tr>
						<td align="right"><input name="cmdConvert" value="Convert" type="button" onclick="" /></td>
						<td>:</td>
						<td>$!selectUnitLuas</td>
					</tr> -->
					
					
				</table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="47%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td width="56%">Anggaran Kasar Kos Tanah</td>
						<td width="1%">:</td>
						<td width="43%"><input $disability $disabilityx type="text" maxlength="50" name="txtKosTanah" id="txtKosTanah" value="$!txtKosTanah" size="19" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
					</tr>
					
					<tr>
						<td>Anggaran Kasar Kos Bangunan</td>
						<td>:</td>
						<td><input $disability $disabilityx type="text" maxlength="50" name="txtKosBangunan" id="txtKosBangunan" value="$!txtKosBangunan"  size="19" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
					</tr>
					
					<tr>
						<td valign="top">Perihal Bangunan</td>
						<td valign="top">&nbsp;</td>
						<td valign="top">&nbsp;</td>
					</tr>
					
					<tr>
						<td colspan="3"><textarea $disability $disabilityx name="txtPerihal" id="txtPerihal" cols="45%" rows="4" onKeyUp="textCounter(this.form.txtPerihal,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtPerihal,this.form.remLen1,400);" >$!txtPerihal</textarea></td>
					</tr>
                    
					<tr>
						<td colspan="3" valign="top">&nbsp;</td>
					</tr>
					
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>                    
                    
                     
					
			  </table></td>
				
			</tr>
		</table>
	</fieldset>
    
    
<br/>	
  <fieldset>
	<legend><strong>Butiran Tanah</strong></legend>
	
	  <table width="100%" border="0">
                  
					<tr>
						<td width="20%">Kedudukan Tanah</td>
					    <td width="1%">:</td>
					    <td width="79%">
                        
                        #if($flag_luar_simpanan == '1')
                          <input name="sbcLuarKwsnSmpnMelayu" type="checkbox" id="sbcLuarKwsnSmpnMelayu" value="1" checked="checked" $disability $disabilityx $disability1 />
Di Luar Kawasan Simpanan Melayu
                		#else
<input name="sbcLuarKwsnSmpnMelayu2" type="checkbox" id="sbcLuarKwsnSmpnMelayu2" value="1" $disability $disabilityx $disability1 />
Di Luar Kawasan Simpanan Melayu
						#end 
                        
                        </td>
					</tr>
                    
					<tr>
						<td>&nbsp;</td>
					    <td>&nbsp;</td>
					    <td>
                        
                        #if($flag_dlm_simpanan == '1')
                         <input name="sbcDlmKwsnSmpnMelayu" type="checkbox" id="sbcDlmKwsnSmpnMelayu" value="1" checked="checked" $disability $disabilityx $disability1 />
Di Dalam Kawasan Simpanan Melayu
                        #else
						<input type="checkbox" name="sbcDlmKwsnSmpnMelayu" id="sbcDlmKwsnSmpnMelayu" value="1" $disability $disabilityx $disability1 />
Di Dalam Kawasan Simpanan Melayu
						#end 
                        
                        
                        </td>
					</tr> 
                    
					<tr>
						<td>&nbsp;</td>
					    <td>&nbsp;</td>
					    <td>
                        
                        #if($flag_luar_majlis == '1')
                          <input name="sbcLuarKwsnMajlisDaerah" type="checkbox" id="sbcLuarKwsnMajlisDaerah" value="1" checked="checked" $disability $disabilityx $disability1 />
Di Luar Kawasan Majlis Daerah
                		#else
						 <input name="sbcLuarKwsnMajlisDaerah" type="checkbox" id="sbcLuarKwsnMajlisDaerah" value="1" $disability $disabilityx $disability1 />
Di Luar Kawasan Majlis Daerah
						#end
                        
                        </td>
					</tr> 
                     
					<tr>
						<td>&nbsp;</td>
					    <td>&nbsp;</td>
					    <td>
                        
                        #if($flag_dlm_majlis == '1')
                         <input name="sbcDlmKwsnMajlisDaerah" type="checkbox" id="sbcDlmKwsnMajlisDaerah" value="1" checked="checked" $disability $disabilityx $disability1 />
Di Dalam Kawasan Majlis Daerah
                		#else
						<input name="sbcDlmKwsnMajlisDaerah" type="checkbox" id="sbcDlmKwsnMajlisDaerah" value="1" $disability $disabilityx $disability1 />
Di Dalam Kawasan Majlis Daerah
						#end 
                        
                        </td>
					</tr>          
					
				  <tr>
					  <td valign="top">Lokasi Tanah</td>
				      <td valign="top">:</td>
				      <td><textarea $disability $disabilityx name="txtLokasi" id="txtLokasi" cols="48%" rows="4" onkeyup="textCounter(this.form.txtLokasi,this.form.remLen2,400);" onkeydown="textCounter(this.form.txtLokasi,this.form.remLen2,400);" >$!txtLokasi</textarea></td>
				  </tr>
				  <tr>
					  <td>Jenis Tanah</td>
				      <td>:</td>
				      <td>
                      
                  #if($flag_jenis_tanah == '1')
                  <input type="radio" name="sorJenisTanah" id="sorJenisTanah" value="1" checked="checked"  $disability $disabilityx $disability1 />
Tanah Desa
                  #else
				  <input type="radio" name="sorJenisTanah" id="sorJenisTanah" value="1" $disability $disabilityx $disability1 />
Tanah Desa
                  #end 
                  
                  </td>
				  </tr>
					
					
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>
                    
                  	#if($flag_jenis_tanah == '2')
                     <input name="sorJenisTanah" type="radio" id="sorJenisTanah" value="2" checked="checked" $disability $disabilityx $disability1 />
Tanah Pekan
                 	#else
					 <input name="sorJenisTanah" type="radio" id="sorJenisTanah" value="2" $disability $disabilityx $disability1 />
Tanah Pekan
                 	#end 
                    
                    </td>
	    		</tr>
				  <tr>
					  <td>&nbsp;</td>
				      <td>&nbsp;</td>
				      <td>
                      
                      #if($flag_jenis_tanah == '3')
                        <input name="sorJenisTanah" type="radio" id="sorJenisTanah" value="3" checked="checked" $disability $disabilityx $disability1 />
                        Tanah Bandar
                	  #else
                        <input name="sorJenisTanah" type="radio" id="sorJenisTanah" value="3" $disability $disabilityx $disability1 />
                        Tanah Bandar
                	  #end 
                
                </td>
				  </tr>                           
			  </table>
		</td>				
						
				
			</tr>
		</table>
		
  </fieldset>

    
    
<br/>	
	<fieldset>
	<legend><strong>Keadaan Rupabumi</strong></legend>
	
		<table width="100%" border="0">
			<tr>
			
				<!-- TABLE KIRI -->
				<td width="50%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td>Keseluruhan Lot</td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtRupabumiSeluruhLot" id="txtRupabumiSeluruhLot" cols="48%" rows="4" onkeyup="textCounter(this.form.txtRupabumiSeluruhLot,this.form.remLen2,400);" onkeydown="textCounter(this.form.txtRupabumiSeluruhLot,this.form.remLen2,400);" >$!txtRupabumiSeluruhLot</textarea></td>
					</tr>                           
					
				</table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="50%" valign="top"><table width="100%" border="0">
					<tr>
						<td>Keadaan Tanaman/Umur</td>
					</tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtRupabumiKwsTerlibat" id="txtRupabumiKwsTerlibat" cols="50%" rows="4" onKeyUp="textCounter(this.form.txtRupabumiKwsTerlibat,this.form.remLen2,400);" onKeyDown="textCounter(this.form.txtRupabumiKwsTerlibat,this.form.remLen2,400);" >$!txtRupabumiKwsTerlibat</textarea></td>
					</tr>
				</table></td>
				
			</tr>
		</table>
		
	</fieldset>
        
    
    
    
<br/>	
	<fieldset>
	<legend><strong>Keadaan Lot</strong></legend>
	
		<table width="100%" border="0">
			<tr>
			
				<!-- TABLE KIRI -->
				<td width="50%" valign="top"><table width="100%" border="0">
				
					<tr>
						<td>Keseluruhan Lot</td>
					</tr>
					<tr>
					  <td><textarea $disability $disabilityx name="txtKeadaanLotSeluruh" id="txtKeadaanLotSeluruh" cols="48%" rows="4" onkeyup="textCounter(this.form.txtKeadaanLotSeluruh,this.form.remLen2,400);" onkeydown="textCounter(this.form.txtKeadaanLotSeluruh,this.form.remLen2,400);" >$!txtKeadaanLotSeluruh</textarea></td>
				  </tr>
					<tr>
					  <td>Jenis Tanaman</td>
				  </tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtKeadaanLotJnsTanaman" id="txtKeadaanLotJnsTanaman" cols="48%" rows="4" onkeyup="textCounter(this.form.txtKeadaanLotJnsTanaman,this.form.remLen2,400);" onkeydown="textCounter(this.form.txtKeadaanLotJnsTanaman,this.form.remLen2,400);" >$!txtKeadaanLotJnsTanaman</textarea></td>
					</tr>                           
					
				</table></td>
				
				
				<!-- TABLE KANAN -->
				<td width="50%" valign="top"><table width="100%" border="0">
					<tr>
						<td>Kawasan yang Terlibat</td>
					</tr>
					<tr>
					  <td><textarea $disability $disabilityx name="txtKeadaanLotTanaman" id="txtKeadaanLotTanaman" cols="50%" rows="4" onkeyup="textCounter(this.form.txtKeadaanLotTanaman,this.form.remLen2,400);" onkeydown="textCounter(this.form.txtKeadaanLotTanaman,this.form.remLen2,400);" >$!txtKeadaanLotTanaman</textarea></td>
				  </tr>
					<tr>
					  <td>Kawasan Terlibat</td>
				  </tr>
					<tr>
						<td><textarea $disability $disabilityx name="txtKeadaanLotKwsTerlibat" id="txtKeadaanLotKwsTerlibat" cols="50%" rows="4" onkeyup="textCounter(this.form.txtKeadaanLotKwsTerlibat,this.form.remLen2,400);" onkeydown="textCounter(this.form.txtKeadaanLotKwsTerlibat,this.form.remLen2,400);" >$!txtKeadaanLotKwsTerlibat</textarea></td>
					</tr>
				</table></td>
				
			</tr>
		</table>
		
	</fieldset>    
    
    
    
    
    
    
    
    
    
    
	#end

	#end
	
	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($mode=="new" && $editable=="yes")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanLaporan('$!id_permohonan','0','$!id_tanahumum')">
				#end
				
				#if($mode=="view" && $editable=="yes")
					#if($isEdit=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniLaporan('$!id_tanahumum')">
                    <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')"/>
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanLaporan('$!id_permohonan','1','$!id_tanahumum')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batal('$id_permohonan','$!id_pegawai')">
					#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="kembali()">
			</td>
		</tr>
	</table>

</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
<!--       <tr>
        <td><a href="#" onClick="javascript:cetakLaporanAwalSementara('$!id_tanahumum')"><font color="blue">Laporan Awal Tanah</font></a></td>
      </tr> -->
      
       <tr>
        <td>
        <a href="#" onClick="javascript:cetakLaporanTanah('$id_fail','$id_tanahumum')"><font color="blue"> Laporan Tanah </font></a>
        </td>      
       </tr>
                
    </table>
</fieldset>
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">
<input type="hidden" name="id_tanahumum" value="$!id_tanahumum">
<input type="hidden" name="id_pegawai" value="$!id_pegawai">
<input type="hidden" name="id_fail" value="$!id_fail">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>

function onchangeUnitUpdate() {
	document.${formName}.ScreenLocation.value = "changeLaporan";
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.command3.value = "kemaskiniLaporan";
	document.${formName}.command4.value = "onchangeUnitUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah";
	document.${formName}.submit();
}
function onchangeUnit() {
	document.${formName}.ScreenLocation.value = "changeLaporan";
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.command3.value = "onchangeUnit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah";
	document.${formName}.submit();
} 
function cetakLaporanTanah(id_fail,id_tanahumum) {
	
	//var url = "../servlet/ekptg.report.ppt.LaporanTanahSementara?idfail="+id_fail+"&idTanahumum="+id_tanahumum;
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_fail="+id_fail+"&id_tanahumum="+id_tanahumum+"&report=LaporanTanahSementara&flagReport=S";	
	//var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&idfail="+idfail+"&namaPengarah="+namaPengarah+"&report=KertasMinitMB&flagReport=S";
	//var url = "../servlet/ekptg.report.ppt.LaporanTanahSementara?idfail="+id_fail+"&idTanahumum="+id_tanahumum;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function cetakLaporanAwalSementara(id_tanahumum) {

   // var url = "../servlet/ekptg.report.ppt.laporanTanahSS8?idTanahumum="+id_tanahumum;
   var url = "../servlet/ekptg.report.ppt.LaporanTanahSementara?idfail="+id_fail+"&idTanahumum="+id_tanahumum;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function getLaporan() {
	document.${formName}.carianNoLot.value = "";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah";
	document.${formName}.submit();
}
function batal(id_permohonan,id_pegawai) {

	document.${formName}.ScreenLocation.value = "changeLaporan";
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pegawai.value = id_pegawai;
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah";
	document.${formName}.submit();
}
function kemaskiniLaporan(idTanahUmum) {

	document.${formName}.ScreenLocation.value = "changeLaporan";
	
	document.${formName}.id_tanahumum.value = idTanahUmum;
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.command3.value = "kemaskiniLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah";
	document.${formName}.submit();
}
function simpanLaporan(idpermohonan,mode,idTanahUmum) {
	
	document.${formName}.ScreenLocation.value = "changeLaporan";

	var dat1=document.${formName}.txdTarikhMula
	var dat2=document.${formName}.txdTarikhTamat
	var dat3=document.${formName}.txdTarikhLawat

	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
	else{

		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.id_permohonan.value = idpermohonan;	

		if(mode=="0"){
			document.${formName}.command.value = "tambahLaporan";
			document.${formName}.command2.value = "getLaporan";
			document.${formName}.command3.value = "simpanLaporan";
		}else{
			document.${formName}.id_tanahumum.value = idTanahUmum;
			document.${formName}.command.value = "tambahLaporan";
			document.${formName}.command2.value = "getLaporan";
			document.${formName}.command3.value = "kemaskiniLaporan";
			document.${formName}.command4.value = "updateLaporan";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah";
		document.${formName}.submit();
		}
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "top";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "top";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.command2.value = "getLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah";
	document.${formName}.submit();
}
</script>




<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}

	var size = document.getElementById("sizehm").value;

	var total = 0;

	if(size>1){
		
		for(var i=0 ; i < document.${formName}.nilaihm.length; i++) 
		{     		
    		if (document.${formName}.nilaihm[i].value!="")
        	{
  				total += parseFloat(document.${formName}.nilaihm[i].value);									
  			}  		
		}

	}else{
		if(document.${formName}.nilaihm.value!=""){
			total += parseFloat(document.${formName}.nilaihm.value);
    	}
	}
	//alert(total);
	document.getElementById("txtLuasKeseluruhan").value = total.toFixed(4);	
}

function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
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
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content!=""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	}else{
		elmnt.value = "";
	}
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