MMK
#parse("app/ppt/Sek8Paging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

#if($showAlertSemakan=="yes")
<script>
alert("Sila Masukkan Tarikh Hantar MMK Sekiranya Telah Disemak Oleh Pengarah");
</script>
#end 

#if($showAlertPaging=="yes")
<script>
alert("Sila Klik 'Paging' No.7 Untuk Proses Pewartaan");
</script>
#end 





<fieldset id="top">
<legend><strong>Urusan Kertas MMK/MB/KM/LC</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>


	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
	<div id="TabbedPanels1" class="TabbedPanels">
  		<ul class="TabbedPanelsTabGroup">
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);viewMMK('$!id_permohonan')" tabindex="1">Penyediaan</li>
    		#if($showSemak=="yes")<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);viewMMK('$!id_permohonan')" tabindex="1">Semakan</li>#end
    		#if($showKeputusan=="yes")<li class="TabbedPanelsTab" onClick="javascript:setSelected(2);viewMMK('$!id_permohonan')" tabindex="1">Keputusan PBN</li>#end
  		</ul>
  	
  	<div class="TabbedPanelsContentGroup">
  	
<!-- START TAB 1 (PENYEDIAAN)-->
    <div class="TabbedPanelsContent">
    
      <a href="javascript:open_info()" class="help" title="info"><b><font color="red"><span class="blink"><i>* Peringatan! sila klik disini untuk maklumat lanjut.</i></span></font></b></a>
    
    
    #set($txtTajuk= "")
    #set($txtSidang= "")
    #set($txtTempatSidang= "")
    #set($txtTarikhSidang= "")
    #set($txtMasaSidang= "")
    #set($jeniswaktu= "")
    
    
    #foreach($default in $dataHeader)
    	#set($txttujuanTEMP = $default.tujuan)
    #end
    
    #if($mode=="view")
    
    
    
    
    #foreach($data in $dataMMK)
    	#set($txtSidang= $data.KETERANGAN_SIDANG)
        #set($txtTempatSidang= $data.TEMPAT_SIDANG)
        #set($txtTarikhSidang= $data.TARIKH_SIDANG)
        #set($txtMasaSidang= $data.WAKTU_SIDANG)
        #set($jeniswaktu= $data.JENIS_WAKTU_SIDANG)        
        #set($txtTajuk= $data.TAJUK)
        
        
        
    	#set($txtTujuan = $data.tujuan)
    	#set($txtLatarBelakang = $data.latarbelakang)
    	#set($txtPerihalTanah = $data.perihal_tanah)
    	#set($txtNilaianTanah = $data.nilai_atas_tanah)
    	#set($txtSyor = $data.syor)
    	#set($txtUlasan = $data.ulasan_pengarah)
    	#set($txtKeputusan = $data.keputusan)  	    	
    	#set($txtPerihalPohon=$data.perihal_pohon)
    	#set($txtPeruntukan=$data.pengesahan_peruntukan)
    	#set($txtNamaTuanTanah=$data.nama_tuan_tanah)
    	#set($txtPerakuan=$data.perakuan_pentadbir_tnh)
    	#set($txtAsasPertimbangan=$data.asas_pertimbangan) 	
    	#set($txtAnggaranKos=$data.anggaran_kos)
    	#set($txtUlasanJT=$data.ulasan_jabteknikal)
    	#set($txtJawatankuasa=$data.jawatankuasa_tanah)
    	#set($txtPengecualian=$data.pengecualian_upah_ukur)
    	#set($txtHalLain=$data.hal_lain)
    	#set($txtKesimpulan=$data.kesimpulan)
    	#set($txtImplikasi=$data.implikasi)
    	#set($txtPenutup=$data.penutup)
    	#set($txtPandangan=$data.pandangan)
    	#set($txtPerakuanSetiausaha=$data.perakuan_setiausaha)
    	#set($txtButirAsas=$data.butir_asas)
    	#set($txtKeadaanTanah=$data.keadaan_tanah)
    	#set($txtButirTanah=$data.butir_tanah)
    	#set($txtKemudahanAsas=$data.kemudahan_asas)
    	#set($txtJenisPenggunaan=$data.jenis_penggunaan_tnh)
    	#set($txtLokasi=$data.lokasi_tanah)
    	#set($txtKedudukan=$data.kedudukan_tanah)
    	#set($txtKeadaan=$data.keadaan_tanah)
    #end
    #end
    
    #if($isEdit=="no")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#set($disability1 = "disabled")
		#set($M = "")        
        #set($disabledmode = "disabled")
        #set($readonlymode = "readonly")
        #set($readmode = "view")
    
	#else
		#set($M = "*")
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
        #set($disabledmode = "")
    	#set($readonlymode = "")
        #set($readmode = "edit")
	#end
    
    
    
    
	
    	 <!-- MMK by Id Negeri -->
    	 #if($negeriMMK=="2")
		 	#parse("app/ppt/MMKSek8/mmkKedah.jsp")
		 #elseif($negeriMMK=="3")
		 	#parse("app/ppt/MMKSek8/mmkKelantan.jsp")
		 #elseif($negeriMMK=="4")
		 	#parse("app/ppt/MMKSek8/mmkMelaka.jsp")
		 #elseif($negeriMMK=="5")
		 	#parse("app/ppt/MMKSek8/mmkNSembilan.jsp")
		 #elseif($negeriMMK=="6")
		 	#parse("app/ppt/MMKSek8/mmkPahang.jsp")
		 #elseif($negeriMMK=="7")
		 	#parse("app/ppt/MMKSek8/mmkPPinang.jsp")
		 #elseif($negeriMMK=="8")
		 	#parse("app/ppt/MMKSek8/mmkPerak.jsp")
		 #elseif($negeriMMK=="9")
		 	#parse("app/ppt/MMKSek8/mmkPerlis.jsp")
		 #elseif($negeriMMK=="10")
		 	#parse("app/ppt/MMKSek8/mmkSelangor.jsp")
		 #elseif($negeriMMK=="11")
		 	#parse("app/ppt/MMKSek8/mmkTerengganu.jsp")
		 #elseif($negeriMMK=="14" || $negeriMMK=="15" || $negeriMMK=="16")
		 	#parse("app/ppt/MMKSek8/mmkWPKL.jsp")
		 #else
		 	#parse("app/ppt/MMKSek8/mmkSelangor.jsp")
		 #end
    
    
    <table width="100%" border="0">
		<tr align="center">
			<td>
				#if($mode=="new")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPenyediaan('$!id_permohonan','$!mode','$!id_mmk')">
				#end
				
				#if($mode=="view")
					#if($isEdit=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPenyediaan('$!id_mmk')">
                    <!--
                    <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah Deraf MMK (Syor Pentadbir Tanah)" onClick="popupEtanah('$id_fail','$id_permohonan','','MMK_S8','')">
                    -->
                    #if($ID_NEGERIPROJEK == "4")     
                    ##if($ID_NEGERIPROJEK == "4" || $ID_NEGERIPROJEK == "5")     
                        <input type="button" name="cmdPopupeTanah" value="Integrasi e-Tanah Melaka(Hantar Borang C)" onClick="eTanahPermohonanMelaka('$id_fail','$id_permohonan','BorangC','')">
                 	    <!--  <input type="button" name="cmdPopupeTanah" value="Integrasi e-Tanah Melaka(Hantar Borang C)" onClick="popupEtanah('$id_fail','$id_permohonan','BorangC','')"> -->
                 	#end
                    
					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPenyediaan('$!id_permohonan','$!mode','$!id_mmk')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalPenyediaan('$!id_permohonan')">
					#end
				#end
				<input type="button" name="cmdKembali" value ="Kembali" onClick="kembali()">				
			</td>
		</tr>
	</table>

    </div>
<!-- END TAB 1 -->
    
    

<!-- START TAB 2 (SEMAKAN)-->
#if($showSemak=="yes")
    <div class="TabbedPanelsContent">
    
   
    
    	#if($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt" )
			#set($editable="yes")
			#set($modeDis="") 	
			#set($modeDis1="") 
			#set($modeDisx="") 
			#set($M="*")		
		#else
			#set($editable="no")
			#set($modeDis="readonly") 	
			#set($modeDis1="disabled") 
			#set($modeDisx="class=disabled") 
			#set($M="")
		#end
    
    #if($modeSemak=="new")
    
    <fieldset>
    <legend><strong>Semakan Pegawai</strong></legend>
    	<table width="100%" border="0">
    		<tr>
    		
    		<!-- TABLE KIRI -->
    		<td valign="top" width="50%"><table width="100%" border="0">
    		
    			<tr>
    				<td width="1%"><font color="red">$!M</font></td>
    				<td width="40%">Nama Pegawai</td>
    				<td width="1%">:</td>
    				<td width="58%">
    					#if($portal_role != "(PPT)KetuaPenolongPengarahUnit" && $portal_role != "(PPT)PengarahUnit" && $portal_role != "adminppt" )
			  				<input type="text" name="lblpegawai" size="20" value="" readonly class="disabled" >
			  			#else
			  				<!-- $!{session.getAttribute('_portal_username').toUpperCase()} -->
			  				$!selectPengarah
			  			#end
    				</td>
    			</tr>
    			<tr>
    				<td><font color="red">$!M</font></td>
    				<td>Tarikh Semakan</td>
    				<td>:</td>
    				<td><input $modeDis $modeDisx name="txdTarikhSemakan" value="$!tarikhSemakan" size="11" id="txdTarikhSemakan" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
						#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSemakan',false,'dmy');">&nbsp;$!frmtdate#end</td>
    			</tr>
    			<tr>
    				<td><font color="red">$!M</font></td>
    				<td>Keputusan Semakan</td>
    				<td>:</td>
    				<td><select $modeDis1 $modeDisx name="socKeputusanSemak" id="socKeputusanSemak" style="width:auto">
                 	 	<option value="">SILA PILIH</option>
						<option value="1">TELAH DISEMAK</option>
						<option value="2">BELUM DISEMAK</option>
						
					</select></td>
				</tr>
				<tr>
    				<td><font color="red">*</font></td>
    				<td>Tarikh Hantar MMK</td>
    				<td>:</td>
    				<td><input $modeDis $modeDisx name="txdTarikhHantar" value="" size="11" id="txdTarikhHantar" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
						#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantar',false,'dmy');">&nbsp;$!frmtdate#end</td>
    			</tr>
			</table></td>
			
			<td valign="top" width="50%"><table width="100%" border="0">
				
				<tr>
					<td valign="top" width="20%">Ulasan</td>
					<td valign="top" width="1%">:</td>
					<td valign="top" width="79%"><textarea $modeDis $modeDisx  name="txtUlasanSemak" id="txtUlasanSemak" rows="5" cols="40%"  onKeyUp="textCounter(this.form.txtUlasanSemak,this.form.remLen99,1500);" onKeyDown="textCounter(this.form.txtUlasanSemak,this.form.remLen99,1500);" ></textarea></td>
				</tr>
			
			</table></td>
			
			</tr>			
    	</table>
    </fieldset>
    #end
    
    #if($modeSemak=="view")
    
    #foreach($dataS in $dataMMK)
    	#set($nama_pegawai = $dataS.user_name)
    	#set($txdTarikhSemakan = $dataS.tarikh_semak)
    	#set($socKeputusanSemak = $dataS.status_semakan)
    	#set($txtUlasanSemak = $dataS.ulasan)
    	#set($txdTarikhHantar = $dataS.tarikh_hantar)
    #end
    
    #if($isEditSemak=="no")
		#set($dis = "readonly")
		#set($disx = "class=disabled")
		#set($dis1 = "disabled")
		#set($M1 = "")
	#else
		#set($M1 = "*")
		#set($dis = "")
		#set($disx = "")
		#set($dis1 = "")
	#end
	
    <fieldset>
    <legend><strong>Semakan Pegawai</strong></legend>
    	<table width="100%" border="0">
    		<tr>
    		
    		<!-- TABLE KIRI -->
    		<td valign="top" width="50%"><table width="100%" border="0">
    		
    			<tr>
    				<td width="1%"><font color="red">#if($editable=="yes")$M1#end</font></td>
    				<td width="42%">Nama Pegawai</td>
    				<td width="1%">:</td>
    				#if($isEditSemak=="yes" && $editable=="yes")
    				<!-- <td width="56%">$!{session.getAttribute('_portal_username').toUpperCase()}</td> -->
    				<td width="56%">$!selectPengarah</td>
    				#else
    				<td width="56%">$!nama_pegawai.toUpperCase()</td>
    				#end   				
    			</tr>
    			<tr>
    				<td><font color="red">#if($editable=="yes")$M1#end</font></td>
    				<td>Tarikh Semakan</td>
    				<td>:</td>
    				<td><input $dis $disx $modeDis $modeDisx name="txdTarikhSemakan" value="$!txdTarikhSemakan" size="11" id="txdTarikhSemakan" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
						#if($isEditSemak=="yes" && $editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSemakan',false,'dmy');">&nbsp;$!frmtdate#end</td>
    			</tr>
    			<tr>
    				<td><font color="red">#if($editable=="yes")$M1#end</font></td>
    				<td>Keputusan Semakan</td>
    				<td>:</td>
    				<td><select $dis1 $disx $modeDis1 $modeDisx name="socKeputusanSemak" id="socKeputusanSemak" style="width:auto">
    				
    					#if($socKeputusanSemak=="1")
    					<option value="1">TELAH DISEMAK</option>					
						<option value="2">BELUM DISEMAK</option>
						<option value="">SILA PILIH</option>
    					#elseif($socKeputusanSemak=="2")
    					<option value="2">BELUM DISEMAK</option>
						<option value="1">TELAH DISEMAK</option>	
						<option value="">SILA PILIH</option>					
    					#else
    					<option value="">SILA PILIH</option>
						<option value="1">TELAH DISEMAK</option>
						<option value="2">BELUM DISEMAK</option>
    					#end
                 		
					</select></td>
				</tr>
				<tr>
    				<td><font color="red">#if($editable=="yes")$M1#end</font></td>
    				<td>Tarikh Hantar MMK</td>
    				<td>:</td>
    				<td><input $dis $disx name="txdTarikhHantar" value="$!txdTarikhHantar" size="11" id="txdTarikhHantar" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
						#if($isEditSemak=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantar',false,'dmy');">&nbsp;$!frmtdate#end</td>
    			</tr>
			</table></td>
			
			<td valign="top" width="50%"><table width="100%" border="0">
				
				<tr>
					<td valign="top" width="20%">Ulasan</td>
					<td valign="top" width="1%">:</td>
					<td valign="top" width="79%"><textarea $dis $disx $modeDis $modeDisx name="txtUlasanSemak" id="txtUlasanSemak" rows="5" cols="40%" onKeyUp="textCounter(this.form.txtUlasanSemak,this.form.remLen99,1500);" onKeyDown="textCounter(this.form.txtUlasanSemak,this.form.remLen99,1500);" >$!txtUlasanSemak</textarea></td>
				</tr>
			
			</table></td>
			
			</tr>			
    	</table>
    </fieldset>
    #end
    
    <table width="100%" border="0">
		<tr align="center">
			<td>
			
				#if($flag_semakan_pengarah == "" && ($portal_role != "(PPT)KetuaPenolongPengarahUnit" && $portal_role != "(PPT)PengarahUnit" && $portal_role != "adminppt" ))
  					<img src="../img/emel.gif" title="Minta untuk semakan juga akan dihantar melalui emel" >
  					<input name="cmdHantar" type="button" value="Hantar Untuk Semakan" onclick="hantarPengesahan('$!id_mmk')"> 
  				#end
  				
				#if($modeSemak=="new" && $editable=="yes")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updateSemakan('$!id_permohonan','$!id_mmk','$!modeSemak','$!portal_role')">
				#end
				
				#if($modeSemak=="view")
					#if($isEditSemak=="no")
						#if(($tarikh_semak!="") || ($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt" ))
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniSemakan('$!id_mmk')">
						#end
						
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updateSemakan('$!id_permohonan','$!id_mmk','$!modeSemak','$!portal_role')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalSemakan('$!id_permohonan')">
					#end
				#end
				<input type="button" name="cmdKembali" value ="Kembali" onClick="kembali()">				
			</td>
		</tr>
	</table>
   
    </div>
#end 
<!-- END TAB 2 -->
 
    
    
 
<!-- START TAB 3 (KEPUTUSAN)-->
#if($showKeputusan=="yes")

#foreach($dataS in $dataMMK)
   #set($hdTarikhSemak = $dataS.tarikh_semak)
#end

    <div class="TabbedPanelsContent">
    
    <input type="hidden" name="hdTarikhSemak" id="hdTarikhSemak" value="$!hdTarikhSemak">
    
    #if($modeKeputusan=="new")
    
    <!-- <fieldset>
    <legend><strong>Maklumat MMK/MB/KM/LC</strong></legend> 
    
    	<table width="100%" border="0">
    		<tr>    		
    		
    		<td width="45%" valign="top"><table width="100%" border="0">
    		
    			<tr>
    				<td width="50%">No. Rujukan MMK</td>
    				<td width="1%">:</td>
    				<td width="49%"><input type="text" name="txtNoRujukan" id="txtNoRujukan" maxlength="40" size="20" value=""   /></td>
    			</tr>
    			
    		</table></td>	
    			
    		
    		<td width="55%" valign="top"><table width="100%" border="0">	
    			<tr>
    				<td width="1%"><font color="red">*</font></td>
    				<td width="25%">Tarikh MMK</td>
    				<td width="1%">:</td>
    				<td width="73%"><input name="txdTarikhMMK" value="" size="11" id="txdTarikhMMK" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
						<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMMK',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr>
    			
    		</table></td>   			
    		</tr>	
    	</table>
    </fieldset> -->
    
    <fieldset>
    	<table width="100%" border="0">
    		<tr>    		
    		<!-- TABLE KIRI -->
    		<td width="49%" valign="top"><table width="100%" border="0">
    		
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="28%">No. Perserahan</td>
    				<td width="1%">:</td>
    				<td width="70%"><input type="text" name="txtNoPerserahan" id="txtNoPerserahan" maxlength="50" size="20" value=""   /></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>No. Rujukan MMK</td>
    				<td>:</td>
    				<td><input type="text" name="txtNoRujukan" id="txtNoRujukan" maxlength="40" size="20" value=""   /></td>
    			</tr>
    			
    			<tr>
    				<td valign="top"><font color="red">*</font></td>
    				<td>Tarikh Kelulusan MMK / Tarikh Mesyuarat Bersidang</td>
    				<td>:</td>
    				<td><input name="txdTarikhMMK" value="" size="11" id="txdTarikhMMK" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMMK',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr>
    			<!-- <tr>
    				<td width="1%"><font color="red">*</font></td>
    				<td width="44%">Tarikh Hantar</td>
    				<td width="1%">:</td>
    				<td width="54%"><input name="txdTarikhHantar" value="" size="11" id="txdTarikhHantar" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantar',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr> -->
    			
    			<!-- <tr>
    				<td><font color="red">*</font></td>
    				<td>Tarikh Keputusan</td>
    				<td>:</td>
    				<td><input name="txdTarikhKeputusan" value="" size="11" id="txdTarikhKeputusan" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhKeputusan',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr> -->
    			
    			<tr>
    				<td valign="top"><font color="red">*</font></td>
    				<td>Tarikh Terima Keputusan</td>
    				<td>:</td>
    				<td><input name="txdTarikhTerimaKeputusan" value="" size="11" id="txdTarikhTerimaKeputusan" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaKeputusan',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr>
    			
    			<tr>
    				<td><font color="red">*</font></td>
    				<td>Keputusan</td>
    				<td>:</td>
    				<td><input name="sorKeputusan" type="radio" id="sorKeputusan" value="1">
                     	Diluluskan
                      	<input type="radio" name="sorKeputusan" id="sorKeputusan" value="2">
                      	Ditolak
                      	<input type="radio" name="sorKeputusan" id="sorKeputusan" value="3">
                      	Ditangguh
                    </td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
					<td valign="top">Ulasan</td>
					<td valign="top">:</td>
					<td valign="top"><textarea name="txtUlasanKeputusan" id="txtUlasanKeputusan" rows="5" cols="35%" onKeyUp="textCounter(this.form.txtUlasanKeputusan,this.form.remLen100,1500);" onKeyDown="textCounter(this.form.txtUlasanKeputusan,this.form.remLen100,1500);" ></textarea></td>
				</tr>
    			
    		</table></td>   
    					
    		</tr>		
    	</table>
    </fieldset>
    #end
    
    
    #if($modeKeputusan=="view")
    
    #foreach($dK in $dataMMKKeputusan)
    	#set($txtNoRujukan = $dK.no_rujmmk)
    	#set($txdTarikhMMK = $dK.tarikh_mmk)
    	#set($txdTarikhHantar = $dK.tarikh_hantar)
    	#set($txdTarikhKeputusan = $dK.tarikh_keputusan)
    	#set($txdTarikhTerimaKeputusan = $dK.tarikh_terima)
    	#set($sorKeputusan = $dK.status_keputusan)
    	#set($txtUlasanKeputusan = $dK.ulasan)
    	#set($txtNoPerserahan = $dK.no_perserahan)
    #end
   
    #if($sorKeputusan=="1")
    	#set($checkA="checked")
    	#set($checkB="")
    	#set($checkC="")
    #elseif($sorKeputusan=="2")
    	#set($checkA="")
    	#set($checkB="checked")
    	#set($checkC="")
    #elseif($sorKeputusan=="3")
    	#set($checkA="")
    	#set($checkB="")	
    	#set($checkC="checked")	
    #else
    	#set($checkA="")
    	#set($checkB="")
    	#set($checkC="")
    #end
    
    #if($isEditKeputusan=="no")
		#set($disK = "readonly")
		#set($disKx = "class=disabled")
		#set($disK1 = "disabled")
		#set($M2 = "")
	#else
		#set($M2 = "*")
		#set($disK = "")
		#set($disKx = "")
		#set($disK1 = "")
	#end
	
	<!--  <fieldset>
  	<legend><strong>Maklumat MMK/MB/KM/LC</strong></legend> 
    	<table width="100%" border="0">
    		<tr>    		
    	
    		<td width="45%" valign="top"><table width="100%" border="0">
    		
    			<tr>
    				<td width="50%">No. Rujukan MMK</td>
    				<td width="1%">:</td>
    				<td width="49%"><input $disK $disKx type="text" name="txtNoRujukan" id="txtNoRujukan" maxlength="40" size="20" value="$!txtNoRujukan"   /></td>
    			</tr>
    			
    		</table></td>	
    			
    	
    		<td width="55%" valign="top"><table width="100%" border="0">	
    			<tr>
    				<td width="1%"><font color="red">#if($isEditKeputusan=="yes")*#end</font></td>
    				<td width="25%">Tarikh MMK</td>
    				<td width="1%">:</td>
    				<td width="73%"><input $disK $disKx name="txdTarikhMMK" value="$!txdTarikhMMK" size="11" id="txdTarikhMMK" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
						#if($isEditKeputusan=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMMK',false,'dmy');">&nbsp;$!frmtdate#end</td>
    			</tr>
    			
    		</table></td>   			
    		</tr>	
    	</table>
    </fieldset> -->
   
    <fieldset>
    	<table width="100%" border="0">
    		<tr>    		
    		<!-- TABLE KIRI -->
    		<td width="49%" valign="top"><table width="100%" border="0">
    		
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="42%">No. Perserahan</td>
    				<td width="1%">:</td>
    				<td width="56%"><input $disK $disKx type="text" name="txtNoPerserahan" id="txtNoPerserahan" maxlength="50" size="20" value="$!txtNoPerserahan"   /></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>No. Rujukan MMK</td>
    				<td>:</td>
    				<td><input $disK $disKx type="text" name="txtNoRujukan" id="txtNoRujukan" maxlength="40" size="20" value="$!txtNoRujukan" /></td>
    			</tr>
    			
    			<tr>
    				<td valign="top"><font color="red">$M2</font></td>
    				<td>Tarikh Kelulusan MMK / Tarikh Mesyuarat Bersidang</td>
    				<td>:</td>
    				<td><input $disK $disKx name="txdTarikhMMK" value="$!txdTarikhMMK" size="11" id="txdTarikhMMK" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					#if($isEditKeputusan=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMMK',false,'dmy');">&nbsp;$!frmtdate#end</td>
    			</tr>
    			<!-- <tr>
    				<td width="1%"><font color="red">*</font></td>
    				<td width="44%">Tarikh Hantar</td>
    				<td width="1%">:</td>
    				<td width="54%"><input name="txdTarikhHantar" value="" size="11" id="txdTarikhHantar" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantar',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr> -->
    			
    			<!-- <tr>
    				<td><font color="red">*</font></td>
    				<td>Tarikh Keputusan</td>
    				<td>:</td>
    				<td><input name="txdTarikhKeputusan" value="" size="11" id="txdTarikhKeputusan" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhKeputusan',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr> -->
    			
    			<tr>
    				<td valign="top"><font color="red">$M2</font></td>
    				<td>Tarikh Terima Keputusan</td>
    				<td>:</td>
    				<td><input $disK $disKx name="txdTarikhTerimaKeputusan" value="$!txdTarikhTerimaKeputusan" size="11" id="txdTarikhTerimaKeputusan" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					#if($isEditKeputusan=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaKeputusan',false,'dmy');">&nbsp;$!frmtdate#end</td>
    			</tr>
    			
    			<tr>
    				<td><font color="red">#if($isEditKeputusan=="yes")*#end</font></td>
    				<td>Keputusan</td>
    				<td>:</td>
    				<td><input $checkA $disK1 $disKx name="sorKeputusan" type="radio" id="sorKeputusan" value="1">
                     	Diluluskan
                      	<input type="radio" $checkB $disK1 $disKx name="sorKeputusan" id="sorKeputusan" value="2">
                      	Ditolak
                      	<input type="radio" $checkC $disK1 $disKx name="sorKeputusan" id="sorKeputusan" value="3">
                      	Ditangguh
                    </td>
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
					<td valign="top">Ulasan</td>
					<td valign="top">:</td>
					<td valign="top"><textarea $disK $disKx name="txtUlasanKeputusan" id="txtUlasanKeputusan" rows="5" cols="35%" onKeyUp="textCounter(this.form.txtUlasanKeputusan,this.form.remLen100,1500);" onKeyDown="textCounter(this.form.txtUlasanKeputusan,this.form.remLen100,1500);" >$!txtUlasanKeputusan</textarea></td>
				</tr>
    			
    		</table></td>   
    					
    		</tr>		
    	</table>
    </fieldset>
    #end
    
    
    <table width="100%" border="0">
		<tr align="center">
			<td>
				#if($modeKeputusan=="new")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanKeputusan('$!id_permohonan','$!id_mmk','$!id_mmkkeputusan','$!modeKeputusan')">
				#end
				
				#if($modeKeputusan=="view")
					#if($isEditKeputusan=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniKeputusan('$!id_permohonan','$!id_mmk','$!id_mmkkeputusan')">
                      
				
						#if($id_status=="134")
						<input type="button" name="cmdHantar" value ="Hantar" onClick="javascript:hantar('$!id_permohonan')">
						#end
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanKeputusan('$!id_permohonan','$!id_mmk','$!id_mmkkeputusan','$!modeKeputusan')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKeputusan('$!id_permohonan')">
					#end
				#end
				<input type="button" name="cmdKembali" value ="Kembali" onClick="kembali()">				
			</td>
		</tr>
	</table>
    
    </div>
#end    
<!-- END TAB 3 -->
   
    
  </div>
</div>

</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>  
      	 <td><a href="#" onClick="javascript:cetakSuratIringanMMK('$!id_permohonan')"><font color="blue">Surat Iringan MMK Ke PTG</font></a></td>
      </tr>	
      <tr>  
      	 <td><a href="#" onClick="javascript:cetakMMK('$!id_permohonan','$!id_fail','$!negeriMMK','$!no_fail')"><font color="blue">Kertas Kerja MMK</font></a></td>
      </tr> 
      #if($negeriMMK=="10")
      <tr>  
      	<td><a href="#" onClick="javascript:cetakMBSS8('$!id_permohonan')"><font color="blue">Kertas MB</font></a></td>
      </tr>
      <tr>  
      	<td><a href="#" onClick="javascript:cetakKertasMinitMB('$!id_fail','$!nama_pengarah.toUpperCase()')"><font color="blue">Kertas Minit Pentadbir</font></a></td>
      </tr>
      #end
      <tr>
		 <td><a href="#" onClick="javascript:cetakBorangC('$!id_fail','$!saiz_listTanah','$!negeriMMK')"><font color="blue">Borang C</font></a></td>
	  </tr>
	  <tr>
		 <td><a href="#" onClick="javascript:cetakBorangD('$!id_permohonan','$!id_fail','$!saiz_listTanah','$!nama2Mukim','$!negeriMMK')"><font color="blue">Borang D</font></a></td>
	  </tr>   
	  
	  #if($negeriMMK=="8")
	  <tr>
		 <td><a href="#" onClick="javascript:cetakBorangAkta486('$!id_permohonan')"><font color="blue">Borang Akta 486</font></a></td>
	  </tr>
	  <tr>
		 <td><a href="#" onClick="javascript:cetakWartaPNMB('$!id_permohonan')"><font color="blue">Surat Warta PNMB</font></a></td>
	  </tr>
	  #end	
	  
	  
    </table>
</fieldset>	


<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_mmk" value="$!id_mmk">
<input type="hidden" name="mode" value="$!mode">
<input type="hidden" name="id_mmkkeputusan" value="$!id_mmkkeputusan">

<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>




<script>



window.onload = mmkItem,submitForm;


function open_info() 
{

 var width  = 500;
 var height = 500;
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=front';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
 new_window = open("","title",params);
 new_window.document.open();

new_window.document.write("<html><title>Perhatian</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");
new_window.document.write("<table><tr><td><font color='blue' >Jika anda ingin memasukkan data dengan kaedah </font><font color='red' ><b><i>COPY & PASTE</i></b></font> <font color='blue' >daripada Microsoft Words, Excell ataupun Website, anda hendaklah </font><font color='red' ><b>PASTE pada notepad</b></font> <font color='blue' > terlebih dahulu dan seterusnya perlu memastikan simbol-simbol ataupun <i>special characters</i> seperti <br><br><font color='red' > * '' & $ {} [] || ^^ >></font> <br><br>dan lain-lain seumpamanya digantikan dengan menaip semula simbol-simbol yang sama pada papan kekunci (keyboard) anda. Seterusnya, anda hendaklah memastikan simbol <font color='red' >\"\"</font> tidak disertakan dalam rekod kemasukan maklumat MMK, tetapi anda boleh menggantikannya dengan simbol <font color='red' >''</font> (Jika Perlu). Jika tidak berbuat demikian, kemungkinan besar simbol-simbol pelek (cth : tanda soal terbalik) dan yang tidak sepatutnya akan muncul dipaparan skrin dan cetakan kertas MMK anda.</font></td></tr></table>");
new_window.document.write("</body></html>");
new_window.document.close();

}


/*
function popupEtanah(id_fail,id_permohonan,id_hakmilik,jenis_skrin,command) {

	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&id_hakmilik="+id_hakmilik+"&jenis_skrin="+jenis_skrin+"&command="+command;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}
*/
	function eTanahPermohonanMelaka(id_fail,id_permohonan,jenis_skrin,command) {	
	//function popupEtanah(id_fail,id_permohonan,jenis_skrin,command) {	
		var url = "../x/${securityToken}/ekptg.view.integrasi.etanah.PermohonanPengambilan?idFail="+id_fail+"&idPermohonan="+id_permohonan+"&jenisSkrin="+jenis_skrin+"&command="+command;	
		//var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&jenis_skrin="+jenis_skrin+"&command="+command;	
	    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		
	}

function close_window() 
{
new_window.close();
}


function cetakBorangAkta486(idpermohonan) {

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=borangAkta486&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakWartaPNMB(idpermohonan) {

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=wartaPNMB&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratIringanMMK(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=SuratIringanMMK&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function mmkItem(){

	if('$negeriMMK'=="2"){
		//kedah
		/*
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),
		textarea_ULASANPENGARAH_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum','');*/
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG1_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG2_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG3_MAIN('tambahtolak','umum',''),
		textarea_ULASANPENGARAH1_MAIN('tambahtolak','umum',''),textarea_ULASANPENGARAH2_MAIN('tambahtolak','umum',''),textarea_ULASANPENGARAH3_MAIN('tambahtolak','umum',''),textarea_ULASANPENGARAH4_MAIN('tambahtolak','umum',''),textarea_ULASANPENGARAH5_MAIN('tambahtolak','umum',''),textarea_ULASANPENGARAH6_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT1_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT2_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT3_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT4_MAIN('tambahtolak','umum',''),textarea_RINGKASAN_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="3"){
		//kelantan
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_PERIHALAP_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_PERUNTUKAN_MAIN('tambahtolak','umum',''),
		textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),textarea_JAWATANKUASANEGERI_MAIN('tambahtolak','umum',''),textarea_ASASPERTIMBANGAN_MAIN('tambahtolak','umum',''),
		textarea_SYOR_MAIN('tambahtolak','umum',''),textarea_ULASANPENGARAH_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="4"){
		//melaka
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_PERIHALPERMOHONAN_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),
		textarea_PERIHALPEMOHON_MAIN('tambahtolak','umum',''),textarea_ANGGARANPAMPASAN_MAIN('tambahtolak','umum',''),textarea_ULASANTEKNIKAL_MAIN('tambahtolak','umum',''),
		textarea_PANDANGANYB_MAIN('tambahtolak','umum',''),textarea_PANDANGANPT_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum',''),
		textarea_ULASANPENGARAH_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="5"){
		//n.sembilan
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),
		textarea_NILAITANAH_MAIN('tambahtolak','umum',''),textarea_SYOR_MAIN('tambahtolak','umum',''),textarea_ULASANPENGARAH_MAIN('tambahtolak','umum',''),
		textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="6"){
		//pahang
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH_MAIN('tambahtolak','umum',''),
		textarea_WANGPERUNTUKAN_MAIN('tambahtolak','umum',''),textarea_SYOR_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="7"){
		//ppinang
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),textarea_PERIHALPERMOHONAN_MAIN('tambahtolak','umum',''),textarea_ANGGARANKOS_MAIN('tambahtolak','umum',''),
		textarea_LAPORANTEKNIKAL_MAIN('tambahtolak','umum',''),textarea_ULASANPT_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum',''),
		textarea_JAWATANKUASANEGERI_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="8"){
		//perak
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_ASASPERTIMBANGAN_MAIN('tambahtolak','umum',''),
		textarea_IMPLIKASI_MAIN('tambahtolak','umum',''),textarea_LAPORANTEKNIKAL_MAIN('tambahtolak','umum',''),textarea_KESIMPULAN_MAIN('tambahtolak','umum',''),
		textarea_SYOR_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="9"){
		//perlis
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),
		textarea_LAPORANTEKNIKAL_MAIN('tambahtolak','umum',''),textarea_ULASANPENGARAH_MAIN('tambahtolak','umum',''),
		textarea_SYOR_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="10"){
		//selangor
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH_MAIN('tambahtolak','umum','')
		,textarea_SYOR_MAIN('tambahtolak','umum',''),textarea_NILAITANAH_MAIN('tambahtolak','umum',''),textarea_WANGPERUNTUKAN_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="11"){
		//terengganu
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),
		textarea_LAPORANTEKNIKAL_MAIN('tambahtolak','umum',''),textarea_TAKSIRANHARGATANAH_MAIN('tambahtolak','umum',''),textarea_SYOR_MAIN('tambahtolak','umum',''),
		textarea_JAWATANKUASANEGERI_MAIN('tambahtolak','umum',''),textarea_ANGGARANKOS_MAIN('tambahtolak','umum',''),textarea_HALLAIN_MAIN('tambahtolak','umum','');
		textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="14" || '$negeriMMK'=="15" || '$negeriMMK'=="16"){
		//kl
		
		/*
		textarea_BUTIRASAS_MAIN('tambahtolak','umum',''),textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH_MAIN('tambahtolak','umum',''),
		textarea_KEADAANTANAH_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),textarea_KEMUDAHANASAS_MAIN('tambahtolak','umum',''),textarea_NILAITANAH_MAIN('tambahtolak','umum',''),
		textarea_WANGPERUNTUKAN_MAIN('tambahtolak','umum',''),textarea_ULASANPT_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
		*/
		
		//format baru
		textarea_BUTIRASAS1_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS2_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS3_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS4_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS5_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS6_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS7_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS8_MAIN('tambahtolak','umum',''),textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH1_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAHSUB_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),textarea_KEMUDAHANASAS_MAIN('tambahtolak','umum',''),textarea_NILAITANAH_MAIN('tambahtolak','umum',''),textarea_WANGPERUNTUKAN_MAIN('tambahtolak','umum',''),textarea_ULASANPT_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
		
		
	}else{
		//selangor
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH_MAIN('tambahtolak','umum','')
		,textarea_SYOR_MAIN('tambahtolak','umum',''),textarea_NILAITANAH_MAIN('tambahtolak','umum',''),textarea_WANGPERUNTUKAN_MAIN('tambahtolak','umum','');
	}
		
}

function cetakMMK(idpermohonan,idfail,negeriMMK,nofail) {

	if(negeriMMK=="2"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8Kedah&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek8Kedah?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="3"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8Kelantan&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek8Kelantan?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="4"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8Melaka&flagReport=S";
	}else if(negeriMMK=="5"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8NSembilan&flagReport=S&flagShowTarikhCetak=yes&selectNoFail=yes";
	}else if(negeriMMK=="6"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8Pahang&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek8Pahang?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="7"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8PPinang&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek8PPinang?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="8"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8Perak&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek8Perak?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="9"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8Perlis&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek8Perlis?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="10"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8Selangor&flagReport=S";
	}else if(negeriMMK=="11"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8Terengganu&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek8Terengganu?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="14" || negeriMMK=="15" || negeriMMK=="16"){
		//var url = "../servlet/ekptg.report.ppt.MMKSek8WPKL?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
		//var url = "../servlet/ekptg.report.ppt.MMKSek4WPKL?idFail="+idfail+"&no_fail="+nofail+"&namaPegawai="+namaPentadbir;
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8WPKL&flagReport=S&selectNoFail=yes&flagShowTarikhCetak=yes";
	}else{
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8Selangor&flagReport=S";
	}

    var hWnd = window.open(url,'Cetak','width=800,height=540, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}
function cetakKertasMinitMB(idfail,namaPengarah) {

	//var url = "../servlet/ekptg.report.ppt.KertasMinitMB?id_fail="+idfail+"&namaPengarah="+namaPengarah;
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_fail="+idfail+"&namaPengarah="+namaPengarah+"&report=KertasMinitMB&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakMBSS8(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MBSelangorSS8&flagReport=S";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function hantarPengesahan(id_mmk)
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_mmk.value = id_mmk;
	document.${formName}.command.value = "viewMMK";
	document.${formName}.command2.value = "hantarPengesahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
	document.${formName}.submit();
}
function viewMMK(id_permohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewMMK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
	document.${formName}.submit();
}
function hantar(idpermohonan) {

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.tabId.value = "2";
	document.${formName}.command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
	document.${formName}.submit();
}
function batalKeputusan(idpermohonan) {

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.tabId.value = "2";
	document.${formName}.command.value = "viewMMK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
	document.${formName}.submit();
}
function kemaskiniKeputusan(idpermohonan,idmmk,idmmkkeputusan) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.id_mmk.value = idmmk;
	document.${formName}.id_mmkkeputusan.value = idmmkkeputusan;
	document.${formName}.tabId.value = "2";
	document.${formName}.command.value = "viewMMK";
	document.${formName}.command2.value = "kemaskiniKeputusan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
	document.${formName}.submit();
}
function simpanKeputusan(idpermohonan,idmmk,idmmkkeputusan,mode) {

	var dat1=document.${formName}.txdTarikhMMK
	//var dat2=document.${formName}.txdTarikhHantar
	//var dat3=document.${formName}.txdTarikhKeputusan
	var dat4=document.${formName}.txdTarikhTerimaKeputusan

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh mmk
	var TM  = document.getElementById("txdTarikhMMK").value;
	var dt1   = parseInt(TM.substring(0,2),10);
   	var mon1  = parseInt(TM.substring(3,5),10)-1;
   	var yr1   = parseInt(TM.substring(6,10),10);
   	var dateRujMMK = new Date(yr1, mon1, dt1);
	//var abc = Date.parse(TM);
	//var dtdd = new Date(abc);
	
  	//tarikh semakan
	var TS  = document.getElementById("hdTarikhSemak").value;
	var dt5   = parseInt(TS.substring(0,2),10);
   	var mon5  = parseInt(TS.substring(3,5),10);
   	var yr5   = parseInt(TS.substring(6,10),10);
   	var dateSemak = new Date(yr5, mon5, dt5);

    //tarikh terima keputusan
	var TTK  = document.getElementById("txdTarikhTerimaKeputusan").value;
	var dtk   = parseInt(TTK.substring(0,2),10);
   	var monk  = parseInt(TTK.substring(3,5),10);
   	var yrk   = parseInt(TTK.substring(6,10),10);
   	var dateTerima = new Date(yrk, monk, dtk);

	var radioSelected = false;
	for (i = 0;  i < ${formName}.sorKeputusan.length;  i++){
		if (${formName}.sorKeputusan[i].checked)
		radioSelected = true;
	}
	
	if(document.${formName}.txdTarikhMMK.value == ""){
		alert("Sila masukkan \"Tarikh Kelulusan MMK\" terlebih dahulu.");
  		document.${formName}.txdTarikhMMK.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if(dateRujMMK < currentDate){
		alert("Sila pastikan \"Tarikh Kelulusan MMK\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhMMK.focus();
		return;
	}
	else if(dateRujMMK < dateSemak){
		alert("Sila pastikan \"Tarikh Kelulusan MMK\" tidak kurang dari Tarikh Semakan MMK.");
		document.${formName}.txdTarikhMMK.focus();
		return;
	}
*/
/*	else if(document.${formName}.txdTarikhHantar.value == ""){
		alert("Sila masukkan \"Tarikh Hantar\" terlebih dahulu.");
  		document.${formName}.txdTarikhHantar.focus(); 
		return;
	}	
	else if(document.${formName}.txdTarikhKeputusan.value == ""){
		alert("Sila masukkan \"Tarikh Keputusan\" terlebih dahulu.");
  		document.${formName}.txdTarikhKeputusan.focus(); 
		return;
	}
*/	else if(document.${formName}.txdTarikhTerimaKeputusan.value == ""){
		alert("Sila masukkan \"Tarikh Terima Keputusan\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerimaKeputusan.focus(); 
		return;
	}
	else if(dateTerima < dateRujMMK){
		alert("Sila pastikan \"Tarikh Terima Keputusan\" tidak kurang dari Tarikh Kelulusan MMK.");
		document.${formName}.txdTarikhTerimaKeputusan.focus();
		return;
	}
	else if (dat4.value!="" && isDate(dat4.value)==false)
	{
		dat4.focus()
		return;
	}
	else if (!radioSelected){
		alert("Sila pilih \"Keputusan\" terlebih dahulu.");
		return (false);
	}
	
/*	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
*/	
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_permohonan.value = idpermohonan;
		document.${formName}.id_mmk.value = idmmk;	
		document.${formName}.tabId.value = "2";

		if(mode=="view"){
			document.${formName}.id_mmkkeputusan.value = idmmkkeputusan;
			document.${formName}.command.value = "viewMMK";
			document.${formName}.command2.value = "kemaskiniKeputusan";
			document.${formName}.command3.value = "updateKeputusan";

		}else{
			document.${formName}.command.value = "viewMMK";
			document.${formName}.command2.value = "simpanKeputusan";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
		document.${formName}.submit();
	}
}
function batalSemakan(idpermohonan) {

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.tabId.value = "1";
	document.${formName}.command.value = "viewMMK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
	document.${formName}.submit();
}
function kemaskiniSemakan(idmmk) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";

	document.${formName}.id_mmk.value = idmmk;
	document.${formName}.tabId.value = "1";
	document.${formName}.command.value = "viewMMK";
	document.${formName}.command2.value = "kemaskiniSemakan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
	document.${formName}.submit();
}
function updateSemakan(idpermohonan,idmmk,mode,role) {

	var dat1=document.${formName}.txdTarikhSemakan;
	var dat2=document.${formName}.txdTarikhHantar;
	
	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh semak
	var TS  = document.getElementById("txdTarikhSemakan").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateSemak = new Date(yr1, mon1, dt1);

  	//tarikh hantar
	var TH  = document.getElementById("txdTarikhHantar").value;
	var dt2   = parseInt(TH.substring(0,2),10);
   	var mon2  = parseInt(TH.substring(3,5),10);
   	var yr2   = parseInt(TH.substring(6,10),10);
   	var dateHantar = new Date(yr2, mon2, dt2);

  
   	if((role=="(PPT)KetuaPenolongPengarahUnit" || role=="(PPT)PengarahUnit" || role=="adminppt") && document.${formName}.socPengarah.value == ""){
		alert("Sila pilih \"Nama Pegawai\" terlebih dahulu.");
  		document.${formName}.socPengarah.focus(); 
		return;
	}
   	else if(document.${formName}.txdTarikhSemakan.value == ""){
		alert("Sila masukkan \"Tarikh Semakan\" terlebih dahulu.");
  		document.${formName}.txdTarikhSemakan.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else 
	if(dateSemak < currentDate){
		alert("Sila pastikan \"Tarikh Semakan\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhSemakan.focus();
		return;
	}
*/	else if(document.${formName}.socKeputusanSemak.value == ""){
		alert("Sila pilih \"Keputusan Semakan\" terlebih dahulu.");
  		document.${formName}.socKeputusanSemak.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhHantar.value == ""){
		alert("Sila masukkan \"Tarikh Hantar MMK\" terlebih dahulu.");
  		document.${formName}.txdTarikhHantar.focus(); 
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value) == false){
		dat2.focus()
		return;
	}
	/* else 
	if(dateHantar < currentDate){
		alert("Sila pastikan \"Tarikh Hantar MMK\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhHantar.focus();
		return;
	}
	else 
	if(dateHantar < dateSemak){
		alert("Sila pastikan \"Tarikh Hantar MMK\" tidak kurang dari \"Tarikh Semakan\".");
		document.${formName}.txdTarikhHantar.focus();
		return;
	} */
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_permohonan.value = idpermohonan;
		document.${formName}.id_mmk.value = idmmk;	
		document.${formName}.tabId.value = "1";

		if(mode=="view"){
			document.${formName}.command.value = "viewMMK";
			document.${formName}.command2.value = "kemaskiniSemakan";
			document.${formName}.command3.value = "updateSemakan2";
		}else{
			document.${formName}.command.value = "viewMMK";
			document.${formName}.command2.value = "updateSemakan";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
		document.${formName}.submit();
	}
}
function simpanPenyediaan(idpermohonan,mode,idmmk) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.tabId.value = "0";
	document.${formName}.command.value = "viewMMK";
	
	if(mode=="view"){
		document.${formName}.id_mmk.value = idmmk;		
		document.${formName}.command2.value = "kemaskiniPenyediaan";
		document.${formName}.command3.value = "updatePenyediaan";		
	}else{
		document.${formName}.command2.value = "simpanPenyediaan";
	}
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
	document.${formName}.submit();
}
function batalPenyediaan(idpermohonan) {

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.tabId.value = "0";
	document.${formName}.command.value = "viewMMK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
	document.${formName}.submit();
}
function kemaskiniPenyediaan(idmmk) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";

	document.${formName}.id_mmk.value = idmmk;
	document.${formName}.tabId.value = "0";
	document.${formName}.command.value = "viewMMK";
	document.${formName}.command2.value = "kemaskiniPenyediaan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
	document.${formName}.submit();
}
function kembali() {

	document.${formName}.command.value = "xx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmMMKSek8";
	document.${formName}.submit();
}

function cetakBorangC(idfail,totalHM,negeri) {
	
	if(negeri=="11")
	{
	if(totalHM > 1){	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_fail="+idfail+"&report=BorangCLebih_TGANU&selectNoFail=no";
	}
	else
	{
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_fail="+idfail+"&report=BorangC_TGANU&selectNoFail=no";
	}
		
	}
    else
	{
	if(totalHM > 1){
		var url = "../servlet/ekptg.report.ppt.BorangCLebih?id_Fail="+idfail;   	
	}else{
		var url = "../servlet/ekptg.report.ppt.BorangC?id_Fail="+idfail;
	}
	}
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangD(idpermohonan,idfail,totalHM,mukim,negeri) {



     if(negeri=="2" || negeri=="5"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=BorangDBI&selectNoFail=yes&flagShowTarikhCetak=yes";
	}else{
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=BorangD&selectNoFail=yes";
	}
	
	
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>



<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$!selectedTab});

function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		if('$CursorPoint' != ""){
			goTo('$CursorPoint');
		}
	}
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
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
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