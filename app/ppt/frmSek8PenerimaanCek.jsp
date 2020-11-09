
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

#if($showAlertPaging=="yes")
<script>
alert("Sila Kembali Pada Skrin Pemilihan Hakmilik dan Klik 'Paging' No.18 Untuk Teruskan ke Borang K");
</script>
#end

#if($showAlertPagingSegera=="yes")
<script>
alert("Sila Kembali Pada Skrin Pemilihan Hakmilik dan Klik 'Paging' No.19/No.20/No.21/No.23 Untuk Proses Seterusnya Jika Belum Dilengkapkan Lagi");
</script>
#end



<fieldset id="top">
<center>
<legend><strong>Bayaran Pampasan Pihak Berkepentingan Melalui Cek</strong></legend>

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>

	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
	
	<div id="TabbedPanels1" class="TabbedPanels">		
  		<ul class="TabbedPanelsTabGroup">
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);tabPenerimaanCek('$!id_bayaran','$!id_terimabayaran','$!id_hakmilikpb')" tabindex="1">Penerimaan Cek</li>
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);tabPenyerahanCek('$!id_bayaran','$!id_terimabayaran','$!id_hakmilikpb','$!cara_bayar')" tabindex="1">Penyerahan Cek</li>
    		<li class="TabbedPanelsTab" style="display:none" onClick="javascript:setSelected(1);tabAfidevit('$!id_bayaran','$!id_terimabayaran','$!id_hakmilikpb','$!cara_bayar')" tabindex="1">Afidavit</li>
    	</ul>
  	
  		<div class="TabbedPanelsContentGroup">
  	
  			<!-- START TAB 1 -->
  			<div class="TabbedPanelsContent">
  				
  			<br/>
  			<fieldset>
  			<legend><strong>Maklumat Penerimaan Cek</strong></legend>
  			
  				#if($mode=="new")

				#foreach($dataPbH in $BorangH)					
				#set($txdTarikhSerah=$dataPbH.tarikh_hantar)
				#end


  				<table width="100%" border="0">
  					<tr>
  						<td width="1%"><font color="red">*</font></td>
  						<td width="25%">Nama PB</td>
  						<td width="1%">:</td>
  						<td width="73%">$!selectPB</td>
  					</tr>
  					<tr>
						<td>&nbsp;</td>
						<td>Kod No. PB</td>
						<td>:</td>
						<td>$!selectJenisNoPB</td>
					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>No. PB</td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20"   ></td>
  					</tr>
  						<tr>
  						<td>&nbsp;</td>
  						<td>Tarikh Akhir Bayar Pampasan</td>
  						<td>:</td>
  						<td><input readonly class="disabled" name="txdTarikhAkhirBayar" id="txdTarikhAkhirBayar" size="11" type="text" value="$!txdTarikhAkhirBayar"></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Jenis Pampasan</td>
  						<td>:</td>
  						<td><select name="sorJenisAward" style="width:220px">
      		
      						#if($sorJenisAward=="1")
      						<option value="1">PAMPASAN PENTADBIR TANAH</option>		
      						<option value="2">PAMPASAN BANTAHAN MAHKAMAH</option>	
      						<option value="3">PAMPASAN AKIBAT PU</option>
      						<option value="">SILA PILIH</option>    
      						#elseif($sorJenisAward=="2")
      						<option value="2">PAMPASAN BANTAHAN MAHKAMAH</option>		
      						<option value="1">PAMPASAN PENTADBIR TANAH</option>			
      						<option value="3">PAMPASAN AKIBAT PU</option>
      						<option value="">SILA PILIH</option>    
      						#elseif($sorJenisAward=="3")
      						<option value="3">PAMPASAN AKIBAT PU</option>
      						<option value="1">PAMPASAN PENTADBIR TANAH</option>	
      						<option value="2">PAMPASAN BANTAHAN MAHKAMAH</option>	
      						<option value="">SILA PILIH</option>   
      						#else
      						<option value="">SILA PILIH</option>    			
      						<option value="1">PAMPASAN PENTADBIR TANAH</option>	
      						<option value="2">PAMPASAN BANTAHAN MAHKAMAH</option>	
      						<option value="3">PAMPASAN AKIBAT PU</option>
      						#end
      							
						</select></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Serah Cek ?</td>
  						<td>:</td>
  						<td><select name="sorFlagSerah" style="width:150px">
      		
      						#if($sorFlagSerah=="1")
      						<option value="1">DISERAHKAN</option>	
      						<option value="2">TIDAK DISERAHKAN</option>	
      						<option value="">SILA PILIH</option>  	
      						#elseif($sorFlagSerah=="2")
      						<option value="2">TIDAK DISERAHKAN</option>		
      						<option value="1">DISERAHKAN</option>	
      						<option value="">SILA PILIH</option>  
      						#else
      						<option value="">SILA PILIH</option>    			
      						<option value="1">DISERAHKAN</option>	
      						<option value="2">TIDAK DISERAHKAN</option>		
      						#end
      						
						</select></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Penama Cek</td>
  						<td>:</td>
  						<td><input type="text" name="txtPenerimaCek" id="txtPenerimaCek" value="$!txtPenerimaCek" size="50" maxlength="100"   ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>No. Cek</td>
  						<td>:</td>
  						<td><input type="text" name="txtNoCek" id="txtNoCek" value="$!txtNoCek" size="20" maxlength="20"   ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Amaun Cek</td>
  						<td>:</td>
  						<td>&nbsp;RM&nbsp;<input type="text" name="txtAmaunCek" id="txtAmaunCek" value="$!txtAmaunCek" size="10" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtAmaunCek')" ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Tarikh Cek</td>
  						<td>:</td>
  						<td><input name="txdTarikhCek" id="txdTarikhCek" size="11" type="text" value="$!txdTarikhCek" onkeyup="validateTarikh(this,this.value)" onblur="txdTarikhAmbilCek(this)" >
          				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhCek',false,'dmy');">&nbsp;$!frmtdate</td>
  					</tr>
  					<tr>
  						<td width="1%"><font color="red">*</font></td>
  						<td>Tarikh Ambil Cek</td>
  						<td>:</td>
  						<td><input name="txdTarikhAmbilCek" id="txdTarikhAmbilCek" size="11" type="text" value="$!txdTarikhAmbilCek" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this);javascript:validateTarikhx(this,this.value)" >
          				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhAmbilCek',false,'dmy'); ">&nbsp;$!frmtdate</td>
  					</tr>
  					<tr>
						<td>&nbsp;</td>
						<td>Masa Ambil Cek</td>
						<td>:</td>
						<td><input type="text" name="txtMasaAmbil" id="txtMasaAmbil" value="$!txtMasaAmbil" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
							<select name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

								#if($socJenisWaktu=="1")
								<option value="1">PAGI</option>								
                    			<option value="2">TENGAH HARI</option>
                    			<option value="3">PETANG</option>
                    			<option value="0">SILA PILIH</option>
								#elseif($socJenisWaktu=="2")
								<option value="2">TENGAH HARI</option>
                    			<option value="1">PAGI</option>
                    			<option value="3">PETANG</option>
                    			<option value="0">SILA PILIH</option>
								#elseif($socJenisWaktu=="3")
								<option value="3">PETANG</option>
                    			<option value="1">PAGI</option>
                    			<option value="2">TENGAH HARI</option>
                    			<option value="0">SILA PILIH</option>
								#else
								<option value="0">SILA PILIH</option>
                    			<option value="1">PAGI</option>
                    			<option value="2">TENGAH HARI</option>
                    			<option value="3">PETANG</option>
								#end
                    				
                    	
                    		</select>&nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font></td>
					</tr>
					<tr>
  						<td>&nbsp;</td>
  						<td>Tempat Ambil</td>
  						<td>:</td>
  						<td><input type="text" name="txtTempatAmbil" id="txtTempatAmbil" value="$!txtTempatAmbil" size="50" maxlength="100"   ></td>
  					</tr>
  				</table>
  				#end
  				
  				#if($mode=="view")
  				
  				#if($onchangeCEK == "no")
  				#foreach($dataPN in $dataPenerimaanCek)
  					##set($txtNoPB=$dataPN.no_pb)
  					#set($txdTarikhSerahPb=$dataPN.tarikh_serahan)
  					#set($txtPenerimaCek=$dataPN.penerima_cek)
  					#set($txdTarikhCek=$dataPN.tarikh_cek)
  					#set($txdTarikhAmbilCek=$dataPN.tarikh_ambil_cek)
  					#set($txtBilLewat=$dataPN.bil_hari_lewat)
  					#set($sorJenisAward=$dataPN.jenis_award)
  					#set($sorFlagSerah=$dataPN.flag_serah_cek)
  					#set($txtNoCek=$dataPN.no_bayaran)
  					#set($txtMasaAmbil=$dataPN.masa_ambil_cek)
  					#set($socJenisWaktu=$dataPN.jenis_waktu_ambil_cek)
  					#set($txtTempatAmbil=$dataPN.tempat_ambil)
  					#set($txtAmaunCek=$dataPN.amaun_bayaran)
  					#set($txtDendaLewat=$dataPN.denda_lewat)
  					#set($txdTarikhLewat=$dataPN.tarikh_cajlewat)
  					#set($txdTarikhAkhirBayar=$dataPN.tarikh_akhir_bayar)
  			
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
				
				#foreach($dataPbH in $BorangH)					
				#set($txdTarikhSerah=$dataPbH.tarikh_hantar)
				#end
  				
  				<table width="100%" border="0">
  					<tr>
  						<td width="1%"><font color="red">$!M</font></td>
  						<td width="25%">Nama PB</td>
  						<td width="1%">:</td>
  						<td width="73%">$!selectPB</td>
  					</tr>
  					<tr>
						<td>&nbsp;</td>
						<td>Kod No. PB</td>
						<td>:</td>
						<td>$!selectJenisNoPB</td>
					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>No. PB</td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20"   ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Tarikh Akhir Bayar Pampasan</td>
  						<td>:</td>
  						<td><input readonly class="disabled" name="txdTarikhAkhirBayar" id="txdTarikhAkhirBayar" size="11" type="text" value="$!txdTarikhAkhirBayar"></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Jenis Pampasan</td>
  						<td>:</td>
  						<td><select $disability1 $disabilityx name="sorJenisAward" style="width:220px">
      		
      						#if($sorJenisAward=="1")
      						<option value="1">PAMPASAN PENTADBIR TANAH</option>		
      						<option value="2">PAMPASAN BANTAHAN MAHKAMAH</option>	
      						<option value="3">PAMPASAN AKIBAT PU</option>
      						<option value="">SILA PILIH</option>    
      						#elseif($sorJenisAward=="2")
      						<option value="2">PAMPASAN BANTAHAN MAHKAMAH</option>		
      						<option value="1">PAMPASAN PENTADBIR TANAH</option>			
      						<option value="3">PAMPASAN AKIBAT PU</option>
      						<option value="">SILA PILIH</option>    
      						#elseif($sorJenisAward=="3")
      						<option value="3">PAMPASAN AKIBAT PU</option>
      						<option value="1">PAMPASAN PENTADBIR TANAH</option>	
      						<option value="2">PAMPASAN BANTAHAN MAHKAMAH</option>	
      						<option value="">SILA PILIH</option>   
      						#else
      						<option value="">SILA PILIH</option>    			
      						<option value="1">PAMPASAN PENTADBIR TANAH</option>	
      						<option value="2">PAMPASAN BANTAHAN MAHKAMAH</option>	
      						<option value="3">PAMPASAN AKIBAT PU</option>
      						#end
      							
						</select></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Serah Cek ?</td>
  						<td>:</td>
  						<td><select $disability1 $disabilityx name="sorFlagSerah" style="width:150px">
      		
      						#if($sorFlagSerah=="1")
      						<option value="1">DISERAHKAN</option>	
      						<option value="2">TIDAK DISERAHKAN</option>	
      						<option value="">SILA PILIH</option>  	
      						#elseif($sorFlagSerah=="2")
      						<option value="2">TIDAK DISERAHKAN</option>		
      						<option value="1">DISERAHKAN</option>	
      						<option value="">SILA PILIH</option>  
      						#else
      						<option value="">SILA PILIH</option>    			
      						<option value="1">DISERAHKAN</option>	
      						<option value="2">TIDAK DISERAHKAN</option>		
      						#end
      						
						</select></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Penama Cek</td>
  						<td>:</td>
  						<td><input $disability $disabilityx type="text" name="txtPenerimaCek" id="txtPenerimaCek" value="$!txtPenerimaCek" size="50" maxlength="100"   ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>No. Cek</td>
  						<td>:</td>
  						<td><input $disability $disabilityx type="text" name="txtNoCek" id="txtNoCek" value="$!txtNoCek" size="20" maxlength="20"   ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Amaun Cek</td>
  						<td>:</td>
  						#if($isEdit=="no")
  						<td>&nbsp;RM&nbsp;<input $disability $disabilityx type="text" name="txtAmaunCek" id="txtAmaunCek" value="$!Utils.format2Decimal($txtAmaunCek)" size="10" maxlength="8" style="text-align:right"></td>
  						#else
  						<td>&nbsp;RM&nbsp;<input $disability $disabilityx type="text" name="txtAmaunCek" id="txtAmaunCek" value="$!txtAmaunCek" size="10" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtAmaunCek')" ></td>
  						#end
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Tarikh Cek</td>
  						<td>:</td>
  						<td><input $disability $disabilityx name="txdTarikhCek" id="txdTarikhCek" size="11" type="text" value="$!txdTarikhCek" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
          				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhCek',false,'dmy');">&nbsp;$!frmtdate#end</td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Tarikh Ambil Cek</td>
  						<td>:</td>
  						<td><input $disability $disabilityx name="txdTarikhAmbilCek" id="txdTarikhAmbilCek" size="11" type="text" value="$!txdTarikhAmbilCek" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this);javascript:validateTarikhx(this,this.value);" >
          				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhAmbilCek',false,'dmy');javascript:validateTarikhx(this,this.value); ">&nbsp;$!frmtdate#end</td>
  					</tr>
  					<tr>
						<td>&nbsp;</td>
						<td>Masa Ambil Cek</td>
						<td>:</td>
						<td><input $disability $disabilityx type="text" name="txtMasaAmbil" id="txtMasaAmbil" value="$!txtMasaAmbil" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
							<select $disability1 $disabilityx name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

								#if($socJenisWaktu=="1")
								<option value="1">PAGI</option>								
                    			<option value="2">TENGAH HARI</option>
                    			<option value="3">PETANG</option>
                    			<option value="0">SILA PILIH</option>
								#elseif($socJenisWaktu=="2")
								<option value="2">TENGAH HARI</option>
                    			<option value="1">PAGI</option>
                    			<option value="3">PETANG</option>
                    			<option value="0">SILA PILIH</option>
								#elseif($socJenisWaktu=="3")
								<option value="3">PETANG</option>
                    			<option value="1">PAGI</option>
                    			<option value="2">TENGAH HARI</option>
                    			<option value="0">SILA PILIH</option>
								#else
								<option value="0">SILA PILIH</option>
                    			<option value="1">PAGI</option>
                    			<option value="2">TENGAH HARI</option>
                    			<option value="3">PETANG</option>
								#end
                    				
                    	
                    		</select>#if($isEdit=="yes")&nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font>#end</td>
					</tr>
					<tr>
  						<td>&nbsp;</td>
  						<td>Tempat Ambil</td>
  						<td>:</td>
  						<td><input $disability $disabilityx type="text" name="txtTempatAmbil" id="txtTempatAmbil" value="$!txtTempatAmbil" size="50" maxlength="100"   ></td>
  					</tr>
  				</table>
  				#end				
  			</fieldset>
  			
  			<fieldset>
  			<legend><strong>Maklumat Caj Bayaran Lewat</strong></legend>
  			
  				#if($mode=="new") <!-- yati -->

  				<table width="100%" border="0">
  					<tr>
  						<td width="1%"></td>
  						<td width="25%">Tarikh Bukti Penyampaian Borang H</td>
  						<td width="1%">:</td>
  						<td width="73%"><input readonly class="disabled" type="text" name="txdTarikhSerah" id="txdTarikhSerah" value="$!txdTarikhSerah" size="10" maxlength="4"   ></td>
  					</tr>
  					<tr>
						<td>&nbsp;</td>
			            <td>Tarikh Kuatkuasa Caj Lewat</td>
			            <td>:</td>
			            <td><input name="txdTarikhLewat" id="txdTarikhLewat" size="11" type="text" value="$!txdTarikhLewat" onkeyup="validateTarikh(this,this.value)" onblur="checkDate(); check_date(this);" >
			            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLewat',false,'dmy');">&nbsp;$!frmtdate</td>
					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Bil. Hari Lewat</td>
  						<td>:</td>
  						<td><input readonly class="disabled"  type="text" name="txtBilLewat" id="txtBilLewat" value="$!txtBilLewat" size="3" maxlength="4" onkeyup="validateNumber(this,this.value);" ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Caj Bayaran Lewat(RM)</td>
  						<td>:</td>
  						<td><input type="text" name="txtDendaLewat" id="txtDendaLewat" value="$!Utils.RemoveSymbol($!txtDendaLewat)" size="10" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtDendaLewat')" >	<span id = "lblCajLewat" ></span></td>
  					</tr>
  					</table>
  					#else
  					<table width="100%" border="0">
  					<tr>
  						<td width="1%"></td>
  						<td width="25%">Tarikh Bukti Penyampaian Borang H</td>
  						<td width="1%">:</td>
  						<td width="73%"><input readonly class="disabled" type="text" name="txdTarikhSerah" id="txdTarikhSerah" value="$!txdTarikhSerah" size="10" maxlength="4"   ></td>
  					</tr>
  					<tr>
						<td>&nbsp;</td>
			            <td>Tarikh Kuatkuasa Caj Lewat</td>
			            <td>:</td>
			            	<td><input $disability $disabilityx name="txdTarikhLewat" id="txdTarikhLewat" size="11" type="text" value="$!txdTarikhLewat" onkeyup="validateTarikh(this,this.value)" onblur="checkDate(this)" >
          				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLewat',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Bil. Hari Lewat</td>
  						<td>:</td>
  						<td><input readonly class="disabled"  type="text" name="txtBilLewat" id="txtBilLewat" value="$!txtBilLewat" size="3" maxlength="4" onkeyup="validateNumber(this,this.value);" ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Caj Bayaran Lewat(RM)</td>
  						<td>:</td>
  						<td><input readonly class="disabled"type="text" name="txtDendaLewat" id="txtDendaLewat" value="$!txtDendaLewat" size="10" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtDendaLewat')" >	<span id = "lblCajLewat" ></span></td>
  					</tr>
  					</table>
  					
  					#end
  					
  					
  					</fieldset>
  					
  				
  				<table width="100%" border="0">
					<tr align="center">
						<td>
			
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPenerimaanCek('$!id_hakmilikpb','$!id_terimabayaran','$!id_bayaran','$!mode')">
						#end
				
						#if($mode=="view")
							#if($isEdit=="no")
							<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPenerimaanCek('$!id_bayaran')">
							<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
							<input type="button" name="cmdHapus" value ="Hapus" onClick="javascript:hapusPenerimaanCek('$!id_bayaran')">
							#else
							<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPenerimaanCek('$!id_hakmilikpb','$!id_terimabayaran','$!id_bayaran','$!mode')">
							<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_bayaran','$!id_terimabayaran','$!id_hakmilikpb')">
							#end
						#end
							<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali('$!id_terimabayaran')">
							<input type="button" name="cmdKembaliHM" value ="Kembali Senarai Hakmilik" onClick="javascript:viewlistHM('$!id_permohonan')">
						</td>
					</tr>
				</table>
  			
  			
  			
  			<fieldset>
			<legend><strong>Senarai Penerima Cek</strong></legend>
			
				#if($mode=="view")
				<table width="100%" border="0">
    				<tr>
    					<td colspan="2"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahPenerimaanCek('$!id_terimabayaran')"></td>
    				</tr>
    			</table>
    			#end
    			
    			#if($saiz_list_penerimaan > 5)
        		<div style="width:100%;height:111;overflow:auto"> 
        		#end
            	
    			<table width="100%" border="0">	
    				<tr class="table_header">
    					<td width="4%" align="center"><b>Bil</b></td>
    					<td>&nbsp;<b>Nama PB</b></td>
    					<td>&nbsp;<b>No. PB</b></td>
    					<td>&nbsp;<b>Tarikh Cek</b></td>   				
    					<td>&nbsp;<b>Penama Cek</b></td>
    				</tr>
    				
    			#if($saiz_list_penerimaan!=0)
           	 	#foreach($listPN in $listMaklumatPenerimaanCek)
              		#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              				#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    
               		<tr>
                   		<td class="$row" align="center">$!listPN.bil</td>
                   		<td class="$row">&nbsp;<a href="javascript:viewPenerimaanCek('$!listPN.id_bayaran','$!listPN.id_terimabayaran','$!listPN.id_hakmilikpb','$!listPN.cara_bayar')"><font color="blue">$!listPN.nama_pb</font></a></td>
                   		<td class="$row">$!listPN.no_pb</td>
                   		<td class="$row">$!listPN.tarikh_cek</td>
                   		<td class="$row">$!listPN.penerima_cek</td>
              		<tr>
           		#end
        		#else
                	<tr>
                	   	<td colspan="2">Tiada rekod</td>
                	</tr>
        		#end
    				
    	 		</table>
    			
    			#if($saiz_list_penerimaan > 5)
        		</div>
        		#end
		
			</fieldset>
  			
  				
  			</div>
  			<!-- END TAB 1 -->
  	
  	
  			<!-- START TAB 2 -->
  			<div class="TabbedPanelsContent">
  			
  			<br/>
  			
  			#if($isShow=="yes")
  			
  			#foreach($dataPN in $dataPenerimaanCek)
  				#set($lblNamaPB=$dataPN.nama_pb)
  				#set($lblNoPB=$dataPN.no_pb)
  				#set($txtNamaWakil=$dataPN.nama_wakil)
  				#set($txtNoWakil=$dataPN.no_wakil)
  				#set($txdTarikhSerahCek=$dataPN.tarikh_serah_cek)
			#end
  				
  			<fieldset>
  			<legend><strong>Maklumat Penyerahan Cek</strong></legend>
  				
  				<table width="100%" border="0">
  				
  					<tr>
  						<td width="1%">&nbsp;</td>
  						<td width="25%">Nama Penerima Cek</td>
  						<td width="1%">:</td>
  						<td width="73%"><font color="blue">$!lblNamaPB</font></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>No. PB Penerima Cek</td>
  						<td>:</td>
  						<td><font color="blue">$!lblNoPB</font></td>
  					</tr>
  					
  				</table>	
  				
  			</fieldset>		
  			
  			<fieldset>
  				
  				#if($mode=="new")
  				<table width="100%" border="0">
  				
  					<tr>
  						<td width="1%">&nbsp;</td>
  						<td width="25%">Nama Wakil</td>
  						<td width="1%">:</td>
  						<td width="73%"><input type="text" name="txtNamaWakil" id="txtNamaWakil" value="" size="50" maxlength="80"   ></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Kod No. Wakil</td>
  						<td>:</td>
  						<td>$!selectJenisNoWakil</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>No. KP Wakil</td>
  						<td>:</td>
  						<td><input type="text" name="txtNoWakil" id="txtNoWakil" value="" size="20" maxlength="20"   ></td>
  					</tr>
  					
  					<tr>
  						<td><font color="red">*</font></td>
  						<td>Tarikh Serah Cek</td>
  						<td>:</td>
  						<td><input name="txdTarikhSerahCek" id="txdTarikhSerahCek" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
          				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerahCek',false,'dmy');">&nbsp;$!frmtdate</td>
  					</tr>
  					
  				</table>
  				#end
  				
  				#if($mode=="view")
  				
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
				
  				<table width="100%" border="0">
  				
  					<tr>
  						<td width="1%">&nbsp;</td>
  						<td width="25%">Nama Wakil</td>
  						<td width="1%">:</td>
  						<td width="73%"><input $disability $disabilityx type="text" name="txtNamaWakil" id="txtNamaWakil" value="$!txtNamaWakil" size="50" maxlength="80"   ></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Kod No. Wakil</td>
  						<td>:</td>
  						<td>$!selectJenisNoWakil</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>No. KP Wakil</td>
  						<td>:</td>
  						<td><input $disability $disabilityx type="text" name="txtNoWakil" id="txtNoWakil" value="$!txtNoWakil" size="20" maxlength="20"   ></td>
  					</tr>
  					
  					<tr>
  						<td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
  						<td>Tarikh Serah Cek</td>
  						<td>:</td>
  						<td><input $disability $disabilityx name="txdTarikhSerahCek" id="txdTarikhSerahCek" size="11" type="text" value="$!txdTarikhSerahCek" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
          				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerahCek',false,'dmy');">&nbsp;$!frmtdate#end</td>
  					</tr>
  					
  				</table>
  				#end
  				
  			</fieldset>
  			
  				<table width="100%" border="0">
					<tr align="center">
						<td>
			
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updatePenyerahanCek('$!id_hakmilikpb','$!id_terimabayaran','$!id_bayaran','$!mode')">
						#end
				
						#if($mode=="view")
							#if($isEdit=="no")
							<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPenyerahanCek('$!id_hakmilikpb','$!id_terimabayaran','$!id_bayaran')">
							<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport2')" />
							#else
							<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updatePenyerahanCek('$!id_hakmilikpb','$!id_terimabayaran','$!id_bayaran','$!mode')">
							<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniSerah('$!id_bayaran','$!id_terimabayaran','$!id_hakmilikpb')">
							#end
						#end
							<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali('$!id_terimabayaran')">
							<input type="button" name="cmdKembaliHM" value ="Kembali Senarai Hakmilik" onClick="javascript:viewlistHM('$!id_permohonan')">
						</td>
					</tr>
				</table>
  		
  			#end
  			
  	 		<fieldset>
			<legend><strong>Senarai Penyerahan Cek</strong></legend>
			
    			#if($saiz_list_penerimaan > 5)
        		<div style="width:100%;height:111;overflow:auto"> 
        		#end
            	
    			<table width="100%" border="0">	
    				<tr class="table_header">
    					<td width="4%" align="center"><b>Bil</b></td>
    					<td>&nbsp;<b>Nama PB</b></td>
    					<td>&nbsp;<b>No. PB</b></td>
    					<td>&nbsp;<b>Nama Wakil</b></td>   				
    				</tr>
    				
    			#if($saiz_list_penerimaan!=0)
           	 	#foreach($listPN in $listMaklumatPenerimaanCek)
              		#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              				#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    
               		<tr>
                   		<td class="$row" align="center">$!listPN.bil</td>
                   		<td class="$row">&nbsp;<a href="javascript:viewPenyerahanCek('$!listPN.id_bayaran','$!listPN.id_terimabayaran','$!listPN.id_hakmilikpb','$!listPN.cara_bayar')"><font color="blue">$!listPN.nama_pb</font></a></td>
                   		<td class="$row">$!listPN.no_pb</td>
                   		<td class="$row">$!listPN.nama_wakil</td>
              		<tr>
           		#end
        		#else
                	<tr>
                	   	<td colspan="2">Tiada rekod</td>
                	</tr>
        		#end
    				
    	 		</table>
    			
    			#if($saiz_list_penerimaan > 5)
        		</div>
        		#end
		
			</fieldset>
			
  			</div>
  			
  	 		<!-- END TAB 2 -->
  	 
  	 
  	 
  	 		<!-- START TAB 3 -->
  	 		<div class="TabbedPanelsContent">
  			
  			<br/>
  			
  			<!-- #if($showAlert=="yes")
  			<script>
  			alert("Sila Pastikan Pihak Berkepentingan Ini Telah Tentukan Pampasannya");
  			</script>
  			#end -->
  			
  			
  			#if($isShow=="yes") 		
  			<fieldset>
  			<legend><strong>Maklumat Afidavit</strong></legend>
  				
  				#if($mode=="new")
  				<table width="100%" border="0">				
  					<tr>
  						<td width="1%">&nbsp;</td>
  						<td width="25%">Kepada</td>
  						<td width="1%">:</td>
  						<td width="73%">$!selectMahkamah</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Alamat</td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtAlamat1" size="50" maxlength="50"   ></td>
  					</tr>
  					
  					<tr>
  						<td colspan="3">&nbsp;</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtAlamat2" size="50" maxlength="50"   ></td>
  					</tr>
  					
  					<tr>
  						<td colspan="3">&nbsp;</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtAlamat3" size="50" maxlength="50"   ></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Poskod</td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtPoskod" size="5" maxlength="5"   ></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Bandar</td>
  						<td>:</td>
  						<td>$!selectBandar</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Negeri</td>
  						<td>:</td>
  						<td>$!selectNegeri</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td valign="top">Tujuan</td>
  						<td valign="top">:</td>
  						<td><textarea name="txtTujuan" id="txtTujuan" rows="4" cols="40%"  onKeyUp="textCounter(this.form.txtTujuan,this.form.remLenAF,1500);" onKeyDown="textCounter(this.form.txtTujuan,this.form.remLenAF,1500);" >$!txtTujuan</textarea></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td valign="top">Perkara Rujukan</td>
  						<td valign="top">:</td>
  						<td><textarea name="txtPerkara" id="txtPerkara" rows="4" cols="40%"  onKeyUp="textCounter(this.form.txtPerkara,this.form.remLenP,1500);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLenP,1500);" >$!txtPerkara</textarea></td>
  					</tr> 					
  				</table>	
  				#end
  				
  				
  				
  				#if($mode=="view")
  				
  				#if($onchangeAF=="no")
  					#foreach($dataAF in $dataAfidevit)
  					#set($txtTujuan=$dataAF.tujuan)
  					#set($txtPerkara=$dataAF.perkara_rujukan)
  					#set($txtAlamat1=$dataAF.alamat1)
  					#set($txtAlamat2=$dataAF.alamat2)
  					#set($txtAlamat3=$dataAF.alamat3)
  					#set($txtPoskod=$dataAF.poskod)
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
  				
  				<table width="100%" border="0">
  				
  					<tr>
  						<td width="1%">&nbsp;</td>
  						<td width="25%">Kepada</td>
  						<td width="1%">:</td>
  						<td width="73%">$!selectMahkamah</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Alamat</td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtAlamat1" size="50" maxlength="50"   ></td>
  					</tr>
  					
  					<tr>
  						<td colspan="3">&nbsp;</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtAlamat2" size="50" maxlength="50"   ></td>
  					</tr>
  					
  					<tr>
  						<td colspan="3">&nbsp;</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtAlamat3" size="50" maxlength="50"   ></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Poskod</td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="" id="" value="$!txtPoskod" size="5" maxlength="5"   ></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Bandar</td>
  						<td>:</td>
  						<td>$!selectBandar</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Negeri</td>
  						<td>:</td>
  						<td>$!selectNegeri</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td valign="top">Tujuan</td>
  						<td valign="top">:</td>
  						<td><textarea $disability $disabilityx name="txtTujuan" id="txtTujuan" rows="4" cols="40%"  onKeyUp="textCounter(this.form.txtTujuan,this.form.remLenAF,1500);" onKeyDown="textCounter(this.form.txtTujuan,this.form.remLenAF,1500);" >$!txtTujuan</textarea></td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td valign="top">Perkara Rujukan</td>
  						<td valign="top">:</td>
  						<td><textarea $disability $disabilityx name="txtPerkara" id="txtPerkara" rows="4" cols="40%"  onKeyUp="textCounter(this.form.txtPerkara,this.form.remLenP,1500);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLenP,1500);" >$!txtPerkara</textarea></td>
  					</tr>
  					
  				</table>	
  				#end
  				
  			</fieldset>	
  			
  				<table width="100%" border="0">
					<tr align="center">
						<td>
			
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanAfidevit('$!id_hakmilikpb','$!id_bayaran','$!id_afidavit','$!mode')">
						#end
				
						#if($mode=="view")
							#if($isEdit=="no")
							<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniAF('$!id_afidavit')">
							<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport3')" />
							#else
							<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanAfidevit('$!id_hakmilikpb','$!id_bayaran','$!id_afidavit','$!mode')">
							<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniSerah('$!id_bayaran','$!id_terimabayaran','$!id_hakmilikpb')">
							#end
						#end
							<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali('$!id_terimabayaran')">
							<input type="button" name="cmdKembaliHM" value ="Kembali Senarai Hakmilik" onClick="javascript:viewlistHM('$!id_permohonan')">
							
						</td>
					</tr>
				</table> 			
  			#end
  			
  			<fieldset>
			<legend><strong>Senarai Afidavit</strong></legend>
			
    			#if($saiz_list_penerimaan > 5)
        		<div style="width:100%;height:111;overflow:auto"> 
        		#end
            	
    			<table width="100%" border="0">	
    				<tr class="table_header">
    					<td width="4%" align="center"><b>Bil</b></td>
    					<td>&nbsp;<b>Nama PB</b></td>
    					<td>&nbsp;<b>No. PB</b></td>			
    				</tr>
    				
    			#if($saiz_list_penerimaan!=0)
           	 	#foreach($listPN in $listMaklumatPenerimaanCek)
              		#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              				#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    
               		<tr>
                   		<td class="$row" align="center">$!listPN.bil</td>
                   		<td class="$row">&nbsp;<a href="javascript:viewAfidevit('$!listPN.id_bayaran','$!listPN.id_terimabayaran','$!listPN.id_hakmilikpb','$!listPN.cara_bayar')"><font color="blue">$!listPN.nama_pb</font></a></td>
                   		<td class="$row">$!listPN.no_pb</td>
              		<tr>
           		#end
        		#else
                	<tr>
                	   	<td colspan="2">Tiada rekod</td>
                	</tr>
        		#end
    				
    	 		</table>
    			
    			#if($saiz_list_penerimaan > 5)
        		</div>
        		#end
		
			</fieldset>
  			
  			
  			</div>
  			<!-- END TAB 3 -->
  			
  			
  		</div>
  	
	</div>

</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
    	 <td><a href="#" onClick="javascript:cetakPanggilPBTerimaPampasan('$!id_permohonan','$!id_bayaran')"><font color="blue">Surat Kepada PB - Panggil terima pampasan</font></a></td>
      </tr>   
      <tr>
    	 <td><a href="#" onClick="javascript:cetakPanggilPBTerimaPampasan_LainKos('$!id_permohonan','$!id_bayaran')"><font color="blue">Surat Kepada PB - Panggil terima pampasan dan lain-lain kos</font></a></td>
      </tr> 
      <tr>
    	 <td><a href="#" onClick="javascript:cetakAkuanPenerimaanCek('$!id_permohonan','$!id_bayaran')"><font color="blue">Surat Akuan Penerimaan Cek</font></a></td>
      </tr>
      <tr>
    	 <td><a href="#" onClick="javascript:cetakAkuanPenerimaanCekBayaranLewat('$!id_permohonan','$!id_bayaran')"><font color="blue">Surat Akuan Penerimaan Cek Bayaran Lewat</font></a></td>
      </tr> 
      <tr>
    	 <td><a href="#" onClick="javascript:cetakAkuanPenerimaanCek_LainKos('$!id_permohonan','$!id_bayaran')"><font color="blue">Surat Akuan Penerimaan Cek Lain-Lain Kos</font></a></td>
      </tr>
    </table>
</fieldset>

<fieldset id="tableReport2" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
    	 <td><a href="#" onClick="javascript:cetakMaklumanBayaran('$!id_permohonan')"><font color="blue">Surat Kepada AP - Maklumat Cek Pampasan Telah Diserahkan</font></a></td>
      </tr>
      <tr>  
      	 <td><a href="#" onClick="javascript:cetakCajLewat('$!id_permohonan','$!id_hakmilik')"><font color="blue">Surat Bayaran Caj Lewat</font></a></td>
      </tr>  
      <tr>  
      	 <td><a href="#" onClick="javascript:cetakLaporanCajLewat('$!id_fail','$!id_hakmilik')"><font color="blue">Laporan Bayaran Caj Lewat</font></a></td>
      </tr> 
    </table>
</fieldset>

<fieldset id="tableReport3" style="display:none;">
<legend><strong>SENARAI LAPORAN AFIDAVIT</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
    	 <td><a href="#" onClick="javascript:cetakAfidavitPerintah('$!id_permohonan','$!id_hakmilikpb')"><font color="blue">Perintah</font></a></td>
      </tr>   
      <tr>
    	 <td><a href="#" onClick="javascript:cetakAfidavitExParte('$!id_permohonan','$!id_hakmilikpb')"><font color="blue">Saman Pemula Ex-Parte</font></a></td>
      </tr>  
      <tr>
    	 <td><a href="#" onClick="javascript:cetakAfidavit('$!id_permohonan','$!id_hakmilikpb')"><font color="blue">Afidavit</font></a></td>
      </tr>  
      <tr>
    	 <td><a href="#" onClick="javascript:cetakAfidavitSijilPerakuan('$!id_permohonan','$!id_hakmilikpb')"><font color="blue">Sijil Perakuan</font></a></td>
      </tr>  
    </table>
</fieldset>

<input type="hidden" name="pagingPampasan" >
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="txdTarikhSerah" value="$!txdTarikhSerah" >
<input type="hidden" name="txdTarikhSedia" value="$!txdTarikhSedia" >

<input type="hidden" name="txd3bulan" id="txd3bulan" value="$!txd3bulan" >
<input type="hidden" name="txt5Percent" id="txt5Percent" value="01/12/2017" >
<input type="hidden" name="txt8Percent" id="txt8Percent" value="30/11/2017" >

<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_hakmilikpb" value="$!id_hakmilikpb">
<input type="hidden" name="id_award" value="$!id_award">
<input type="hidden" name="id_siasatan" value="$!id_siasatan">
<input type="hidden" name="command2" >
<input type="hidden" name="command3" >
<input type="hidden" name="id_terimabayaran" value="$!id_terimabayaran" >
<input type="hidden" name="id_bayaran" value="$!id_bayaran" >
<input type="hidden" name="cara_bayar" value="$!cara_bayar">
<input type="hidden" name="id_afidavit" value="$!id_afidavit">



<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<script>
function viewlistHM(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewlistHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function cetakAfidavitPerintah(idpermohonan,id_hakmilikpb) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilikpb="+id_hakmilikpb+"&report=Afidavit_Perintah&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakAfidavitExParte(idpermohonan,id_hakmilikpb) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilikpb="+id_hakmilikpb+"&report=Afidavit_ExParte&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakAfidavit(idpermohonan,id_hakmilikpb) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilikpb="+id_hakmilikpb+"&report=Afidavit&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakAfidavitSijilPerakuan(idpermohonan,id_hakmilikpb) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilikpb="+id_hakmilikpb+"&report=Afidavit_SijilPerakuan&flagReport=S";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function doChangeMahkamahUpdate() {
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewAfidevit";
	document.${formName}.command2.value = "kemaskiniAF";
	document.${formName}.command3.value = "doChangeMahkamahUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kemaskiniAF(id_afidavit) {
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_afidavit.value = id_afidavit;
	document.${formName}.command.value = "viewAfidevit";
	document.${formName}.command2.value = "kemaskiniAF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function simpanAfidevit(id_hakmilikpb,id_bayaran,id_afidavit,mode) {

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;

	if(mode=="new"){
		document.${formName}.command.value = "viewAfidevit";
		document.${formName}.command2.value = "simpanAfidevit";
	}else{
		document.${formName}.id_afidavit.value = id_afidavit;
		document.${formName}.command.value = "viewAfidevit";
		document.${formName}.command2.value = "kemaskiniAF";
		document.${formName}.command3.value = "updateAfidevit";
	}	
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function doChangeMahkamah() {
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewAfidevit";
	document.${formName}.command2.value = "doChangeMahkamah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewAfidevit(id_bayaran,id_terimabayaran,id_hakmilikpb,flagCBayar) {
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.cara_bayar.value = flagCBayar;
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.command.value = "viewAfidevit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function cetakAkuanPenerimaanCek_LainKos(idpermohonan,id_bayaran) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_bayaran="+id_bayaran+"&report=SuratAkuanPenerimaanCekLainKos&selectNoFail=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakAkuanPenerimaanCek(idpermohonan,id_bayaran) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_bayaran="+id_bayaran+"&report=SuratAkuanPenerimaanCek&selectNoFail=yes";
    //var url = "../servlet/ekptg.report.ppt.SuratAkuanPenerimaanCek?ID_BAYARAN="+id_bayaran;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakAkuanPenerimaanCekBayaranLewat(idpermohonan,id_bayaran) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_bayaran="+id_bayaran+"&report=SuratAkuanPenerimaanCek&selectNoFail=yes";
   // var url = "../servlet/ekptg.report.ppt.SuratAkuanPenerimaanCek?ID_BAYARAN="+id_bayaran+"&selectNoFail=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakPanggilPBTerimaPampasan_LainKos(idpermohonan,idbayaran) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_bayaran="+idbayaran+"&report=SuratPanggilPBTerimaPampasan_LainKos&selectNoFail=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakPanggilPBTerimaPampasan(idpermohonan,idbayaran) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_bayaran="+idbayaran+"&report=SuratPanggilPBTerimaPampasan&selectNoFail=yes";
    //var url = "../servlet/ekptg.report.ppt.SuratPanggilPBTerimaPampasan?idFail="+idfail+"&idBayaran="+idbayaran+"&namaPegawai="+nama_pengarah;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakMaklumanBayaran(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=suratMaklumanSerahBayaranPampasanKpdAPSEK8&selectNoFail=yes&flagShowTarikhCetak=yes";
    //var url = "../servlet/ekptg.report.ppt.suratMaklumanSerahBayaranPampasanKpdAP?idFail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakCajLewat(idpermohonan,idhakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=SuratCajLewat&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakLaporanCajLewat(idfail,idhakmilik) {
	
    var url = "../servlet/ekptg.report.ppt.LaporanCajLewat?idFail="+idfail+"&idHakmilik="+idhakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function viewPenyerahanCek(id_bayaran,id_terimabayaran,id_hakmilikpb,flagCBayar) {
	document.${formName}.tabId.value = "1";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.cara_bayar.value = flagCBayar;
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.command.value = "viewPenyerahanCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function batalKemaskiniSerah(id_bayaran,id_terimabayaran,id_hakmilikpb) {
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.tabId.value = "1";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.command.value = "viewPenyerahanCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kemaskiniPenyerahanCek(id_hakmilikpb,id_terimabayaran,id_bayaran) {
	document.${formName}.tabId.value = "1";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.command.value = "viewPenyerahanCek";
	document.${formName}.command2.value = "kemaskiniPenyerahanCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function updatePenyerahanCek(id_hakmilikpb,id_terimabayaran,id_bayaran,mode) {

	var dat1=document.${formName}.txdTarikhSerahCek
	
	if(document.${formName}.txdTarikhSerahCek.value == ""){
	   	alert("Sila masukkan \"Tarikh Serah Cek\" terlebih dahulu.");
		document.${formName}.txdTarikhSerahCek.focus();
		return;
	}
	else 
	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.tabId.value = "1";
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_terimabayaran.value = id_terimabayaran;
		document.${formName}.id_bayaran.value = id_bayaran;
		document.${formName}.id_hakmilikpb.value = id_hakmilikpb;

		if(mode=="new"){
			document.${formName}.command.value = "viewPenyerahanCek";
			document.${formName}.command2.value = "updatePenyerahanCek";
		}else{
			document.${formName}.command.value = "viewPenyerahanCek";
			document.${formName}.command2.value = "kemaskiniPenyerahanCek";
			document.${formName}.command3.value = "updatePenyerahanCek";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
		document.${formName}.submit();

	}
}
function batalKemaskini(id_bayaran,id_terimabayaran,id_hakmilikpb) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.command.value = "viewPenerimaanCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function onchangeSelectPBUpdate() {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewPenerimaanCek";
	document.${formName}.command2.value = "kemaskiniPenerimaanCek";
	document.${formName}.command3.value = "onchangeSelectPBUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kemaskiniPenerimaanCek(id_bayaran) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.command.value = "viewPenerimaanCek";
	document.${formName}.command2.value = "kemaskiniPenerimaanCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function hapusPenerimaanCek(id_bayaran) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.command.value = "hapusPenerimaanCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function tambahPenerimaanCek(id_terimabayaran) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.command.value = "tambahPenerimaanCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewPenerimaanCek(id_bayaran,id_terimabayaran,id_hakmilikpb,flagCBayar) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.cara_bayar.value = flagCBayar;
	document.${formName}.command.value = "viewPenerimaanCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function simpanPenerimaanCek(id_hakmilikpb,id_terimabayaran,id_bayaran,mode) {

	var dat1=document.${formName}.txdTarikhCek
	var dat2=document.${formName}.txdTarikhAmbilCek

	if(document.${formName}.socPB.value == ""){
	   	alert("Sila pilih \"Nama PB\" terlebih dahulu.");
		document.${formName}.socPB.focus();
		return;
	}
	else if(document.${formName}.txdTarikhAmbilCek.value == ""){
	   	alert("Sila masukkan Tarikh Ambil Cek terlebih dahulu.");
		document.${formName}.txdTarikhAmbilCek.focus();
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if(((document.${formName}.txtBilLewat.value != "") || (document.${formName}.txtBilLewat.value == "0"))&&((document.${formName}.txtDendaLewat.value =='')|| (document.${formName}.txtDendaLewat.value =='0.00'))){
	   	alert("Sila isi caj bayaran lewat terlebih dahulu.");
		document.${formName}.txtDendaLewat.focus();
		return;
	}
	else{
	
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
		document.${formName}.id_terimabayaran.value = id_terimabayaran;

		if(mode=="new"){
			document.${formName}.command.value = "tambahPenerimaanCek";
			document.${formName}.command2.value = "simpanPenerimaanCek";
		}else{
			document.${formName}.id_bayaran.value = id_bayaran;
			document.${formName}.command.value = "viewPenerimaanCek";
			document.${formName}.command2.value = "kemaskiniPenerimaanCek";
			document.${formName}.command3.value = "updatePenerimaanCek";
		}
		
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
		document.${formName}.submit();
	}
}
function onchangeSelectPB() {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "tambahPenerimaanCek";
	document.${formName}.command2.value = "onchangeSelectPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kembali(id_terimabayaran) {
	document.${formName}.tabId.value = "0";
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.command.value = "viewMaklumatSuratAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
</script>

<!-- TAB 1 -->
<script>
function tabPenerimaanCek(id_bayaran,id_terimabayaran,id_hakmilikpb) {
	
	document.${formName}.tabId.value = "0";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;

	if(id_bayaran!=""){
		document.${formName}.id_bayaran.value = id_bayaran;
		document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
		document.${formName}.command.value = "viewPenerimaanCek";
	}else{
		document.${formName}.command.value = "tambahPenerimaanCek";
	}
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
</script>

<!-- TAB 2 -->
<script>
function tabPenyerahanCek(id_bayaran,id_terimabayaran,id_hakmilikpb,flagCBayar) {
	
	document.${formName}.tabId.value = "1";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;

	if(id_bayaran!=""){
		
		if(flagCBayar!="2"){
			document.${formName}.id_bayaran.value = id_bayaran;
			document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
			document.${formName}.command.value = "viewPenyerahanCek";
		}else{
			document.${formName}.command.value = "viewListPenyerahanCek";
		}
		
	}else{
		document.${formName}.command.value = "viewListPenyerahanCek";
	}
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
</script>


<!-- TAB 3 -->
<script>
function tabAfidevit(id_bayaran,id_terimabayaran,id_hakmilikpb,flagCBayar) {
	
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;

	if(id_bayaran!=""){
		
		if(flagCBayar!="2"){
			document.${formName}.id_bayaran.value = id_bayaran;
			document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
			document.${formName}.command.value = "viewAfidevit";
		}else{
			document.${formName}.command.value = "viewListAfidevit";
		}
		
	}else{
		document.${formName}.command.value = "viewListAfidevit";
	}
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
</script>


<script>
window.onload = submitForm;
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$!selectedTab});

function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}

function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>

<script>
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
function checkDigit() {
	if(document.getElementById("txtMasaAmbil").value != "" && document.getElementById("txtMasaAmbil").value.length < 4){
		alert("Sila Pastikan Format Masa Ambil Cek Adalah \"HHMM\"");
		document.${formName}.txtMasaAmbil.focus(); 
		return;	
	}
}
function validateJenisWaktu(elmnt,content) {

	var length = parseInt(document.getElementById("txtMasaAmbil").value.length);
	
	if(length>=2){

		var ValJam = content.substring(0,2);

		if(ValJam!=""){	
		
			if(ValJam >= 07 && ValJam <= 11){
				document.getElementById("socJenisWaktu").value = "1" ;
			}else if(ValJam == 12){
				document.getElementById("socJenisWaktu").value = "2" ;
			}else if(ValJam == 01 || ValJam == 02 || ValJam == 03 || ValJam == 04 || ValJam == 05 || ValJam == 06){
				document.getElementById("socJenisWaktu").value = "3" ;
			}else{
				document.getElementById("socJenisWaktu").value = "0" ;
			}
		
		}else{
			document.getElementById("socJenisWaktu").value = "0" ;
		}	
		return;

	}else{
		document.getElementById("socJenisWaktu").value = "0";
	}
	
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
	//alert("xxxx");
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function validateTarikhx(elmnt,content) {
	//penambahan js yati
	//if it is character, then remove it..
	var dateSerah=document.${formName}.txdTarikhSerah.value;
	var dateCek=document.${formName}.txdTarikhAmbilCek.value;
	//alert(dateCek);
	//alert(dateSerah);
	var one_day = 1000*60*60*24;
	var x = dateSerah.split("/"); 
	var y = dateCek.split("/");
	
	var date1 = new Date(x[2],(x[1]-1), x[0]);
	var date2 = new Date(y[2],(y[1]-1), y[0]);
	
	var month = x[1]-1;
	
	var diffDays = Math.ceil((date2.getTime()-date1.getTime())/(one_day));
	if(!isNaN(diffDays)){
	document.getElementById("txtBilLewat").value =parseInt(diffDays)-90;
	}
	//alert(diffDays);
	if(diffDays > 90 ){
		document.${formName}.txtDendaLewat.focus();
		$jquery("#lblCajLewat").html("<span class = 'blink' style='color:red;''>*Sila isi caj bayaran lewat!</span>");
		
	}
	if(diffDays < 90 ){
		document.getElementById("txtBilLewat").value ="";
		$jquery("#lblCajLewat").html("");
	}
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

//YATI
function checkDate(){
	//alert("masuk");
	
	var Mula  = document.getElementById("txdTarikhLewat").value;
	var dt1   = parseInt(Mula.substring(0,2),10);
	var mon1  = parseInt(Mula.substring(3,5),10)-1;
	var yr1   = parseInt(Mula.substring(6,10),10);
	var date1 = new Date(yr1, mon1, dt1);
	//alert("date 1 :"+date1);
	
	var Mula2  = document.getElementById("txd3bulan").value;
	var dt2   = parseInt(Mula2.substring(0,2),10);
	var mon2  = parseInt(Mula2.substring(3,5),10)-1;
	var yr2   = parseInt(Mula2.substring(6,10),10);
	var date2 = new Date(yr2, mon2, dt2);
	//alert("date 2 :"+date2);
	
	var Mula5  = document.getElementById("txt5Percent").value;
	var dt5   = parseInt(Mula5.substring(0,2),10);
	var mon5  = parseInt(Mula5.substring(3,5),10)-1;
	var yr5   = parseInt(Mula5.substring(6,10),10);
	var date5 = new Date(yr5, mon5, dt5);
	//alert("date 5 :"+date5);
	
	var Mula8  = document.getElementById("txt8Percent").value;
	var dt8   = parseInt(Mula8.substring(0,2),10);
	var mon8  = parseInt(Mula8.substring(3,5),10)-1;
	var yr8   = parseInt(Mula8.substring(6,10),10);
	var date8 = new Date(yr8, mon8, dt8);
	//alert("date 8 :"+date8);
	
	var amaun_cek = document.getElementById("txtAmaunCek").value;
	
	//&& (date1 > date5)
	
	if((date1 > date2) && (date1 > date5))
		{
	
		var diffDateA = Math.abs(date1 - date2) / 1000;
		var daysDateA = Math.floor(diffDateA / 86400)  ;
		
		//alert("days :"+daysDateA);
		//alert("yr"+yr1);
		var leapyr1 = yr1%4;
		//alert(leapyr1);
		var leapyr2 = yr2%4;
		//alert("leap year x :"+leapyr1);
				//alert("leap year 2 :"+leapyr2);
				if(leapyr1=="0"){
					var yrDiff = "366";
				}
				else{
					var yrDiff = "365";
				}
		
		var percent = "0.05";
		//alert(percent);
		//alert(amaun_cek);
		
			var resultYr3 = (amaun_cek * percent * daysDateA) / yrDiff;
			//alert(resultYr3);
			var resultfinal = resultYr3.toFixed(2) ;
		}
	else{
		var leapyr1 = yr1%4;
		//alert(leapyr1);
		var leapyr2 = yr2%4;
		//alert("leap year x :"+leapyr1);
				//alert("leap year 2 :"+leapyr2);
				if(leapyr1=="0"){
					var yrDiff = "366";
				}
				else{
					var yrDiff = "365";
				}
		
		var percent = "0.08";
		//alert(percent);
		//alert(amaun_cek);
		
			var resultYr3 = (amaun_cek * percent * daysDateA) / yrDiff;
			//alert(resultYr3);
			var resultfinal = resultYr3.toFixed(2) ;
	}
	document.${formName}.txtBilLewat.value = daysDateA;
	document.${formName}.txtDendaLewat.value = resultfinal;
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
</script>