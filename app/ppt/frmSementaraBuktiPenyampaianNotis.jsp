#parse("app/ppt/SementaraPaging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<center>

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>


	#if($mode=="new")
	<fieldset id="top">
	<legend><strong>Penyampaian Notis</strong></legend>
	<table width="100%" border="0">
		<tr>
			<td width="1%"><font color="red">*</font></td>
			<td width="24%">Nama Penghantar</td>
			<td width="1%">:</td>
			<td width="74%"><input type="text" name="txtNamaHantar" id="txtNamaHantar" value="$!txtNamaHantar" size="47" maxlength="80"   ></td>
		</tr>
		
		<!-- <tr>
			<td>&nbsp;</td>
			<td>Kod / Perihal Dokumen</td>
			<td>:</td>
			<td><select name="socKodDokumen" style="width:auto">
      		
      			<option value="">SILA PILIH</option>    			
				
			</select></td>
		</tr> -->
		
		<tr>
			<td>&nbsp;</td>
			<td>Jenis Serahan</td>
			<td>:</td>
			<td><select name="socJenisSerah" style="width:160px">
      		
      			#if($socJenisSerah=="1")
      			<option value="1">PERKHIDMATAN POS</option>				
      			<option value="2">SERAHAN TANGAN</option>	
      			<option value="3">FAKSIMILI</option>	
      			<option value="">SILA PILIH</option>   	
      			#elseif($socJenisSerah=="2")
      			<option value="2">SERAHAN TANGAN</option>	
      			<option value="1">PERKHIDMATAN POS</option>	
      			<option value="3">FAKSIMILI</option>	
      			<option value="">SILA PILIH</option>  
      			#elseif($socJenisSerah=="3")
      			<option value="3">FAKSIMILI</option>
      			<option value="1">PERKHIDMATAN POS</option>	
      			<option value="2">SERAHAN TANGAN</option>	
      			<option value="">SILA PILIH</option>    
      			#else
      			<option value="">SILA PILIH</option>    			
      			<option value="1">PERKHIDMATAN POS</option>	
      			<option value="2">SERAHAN TANGAN</option>	
      			<option value="3">FAKSIMILI</option>	
      			#end
      			
			</select></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Status Serahan</td>
			<td>:</td>
			<td><select name="socStatusSerah" style="width:auto" onchange="onChangeStatus()">
      		
      			#if($socStatusSerah=="1")
      			<option value="1">DISERAHKAN</option>
      			<option value="2">TIDAK DISERAHKAN</option>	
      			<option value="">SILA PILIH</option> 
      			#elseif($socStatusSerah=="2")
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
			<td><font color="red">*</font></td>
			#if($hideItem=="no")
			<td>Tarikh Serahan</td>
			#else
			<td>Tarikh Tampal</td>
			#end
			<td>:</td>
			<td><input name="txdTarikhSerah" id="txdTarikhSerah" size="11" type="text" value="$!txdTarikhSerah" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerah',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr>
		
		#if($hideItem=="yes")
		<tr>
			<td><font color="red">*</font></td>
			<td>Masa Tampal</td>
			<td>:</td>
			<td><input type="text" name="txtMasaTampal" id="txtMasaTampal" value="$!txtMasaTampal" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
				<select name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

				#if($socJenisWaktu=="1")
				<option value="1">PAGI</option>								
                <option value="2">TENGAHARI</option>
                <option value="3">PETANG</option>
                <option value="0">SILA PILIH</option>
				#elseif($socJenisWaktu=="2")
				<option value="2">TENGAHARI</option>
                <option value="1">PAGI</option>
                <option value="3">PETANG</option>
                <option value="0">SILA PILIH</option>
				#elseif($socJenisWaktu=="3")
				<option value="3">PETANG</option>
                <option value="1">PAGI</option>
                <option value="2">TENGAHARI</option>
                <option value="0">SILA PILIH</option>
				#else
				<option value="0">SILA PILIH</option>
                <option value="1">PAGI</option>
                <option value="2">TENGAHARI</option>
                <option value="3">PETANG</option>
				#end
                    				
                    	
             	</select>&nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font></td>
		</tr>
		#end

	</table>	
	</fieldset>	
	
	<fieldset>
	<table width="100%" border="0">
	
		<tr>
			<td><font color="red">*</font></td>
			<td>Nama Pihak Berkepentingan</td>
			<td>:</td>
			<td>$!selectPB</td>
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
		
		#if($hideItem=="no")	
		<tr>
			<td><font color="red">*</font></td>
			<td>Nama Penerima</td>
			<td>:</td>
			<td><input type="text" name="txtNamaTerima" id="txtNamaTerima" value="$!txtNamaTerima" size="47" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Kod No. Kad Pengenalan</td>
			<td>:</td>
			<td>$!selectJenisNoKP</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Kad Pengenalan</td>
			<td>:</td>
			<td><input type="text" name="txtNoKP" id="txtNoKP" value="$!txtNoKP" size="20" maxlength="20"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Hubungan Dengan PB</td>
			<td>:</td>
			<td><input type="text" name="txtHubungan" id="txtHubungan" value="$!txtHubungan" size="20" maxlength="80"   ></td>
		</tr>
		#else
			<tr>
			<td><font color="red">*</font></td>
			<td>Tempat Tampal</td>
			<td>:</td>
			<td><input type="text" name="txtTempatTampal" id="txtTempatTampal" value="$!txtTempatTampal" size="47" maxlength="80"   ></td>
		</tr>
		#end
		
		<tr>
			<td width="1%">&nbsp;</td>
			#if($hideItem=="no")
			<td width="24%" valign="top">Catatan</td>
			#else
			<td width="24%" valign="top">Cara Tampal</td>
			#end
			<td width="1%" valign="top">:</td>
			<td width="74%" valign="top"><textarea name="txtCatatan" id="txtCatatan" cols="44%" rows="5" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen3,400);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen3,400);" >$!txtCatatan</textarea></td>
		</tr>
		<tr>
        	<td colspan="3">&nbsp;</td>
            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="400"></td>
        </tr> 
	</table>
	</fieldset>
	
	<!-- #if($hideItem=="no")
	<br/>
	
	<fieldset>
	<legend><strong>Senarai Pihak Berkepentingan</strong></legend>
		
			#if($saiz_pb > 5)
        	<div style="width:100%;height:100;overflow:auto"> 
        	#end
            	
    			<table width="100%" border="0">	
    				<tr class="table_header">
    					<td width="4%" align="center"><b>No</b></td>
    					<td width="20%">&nbsp;<b>No. PB</b></td>
    					<td width="72%">&nbsp;<b>Nama PB</b></td>
    					#if($saiz_pb!=0)<td width="4%" align="center"><input type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></td>#end
    				</tr>
    				
    		#if($saiz_pb!=0)
           	 	#foreach($listPB in $listMaklumatPB)
              	#set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              			#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               		<tr>
                   		<td class="$row" align="center">$!listPB.bil</td>
                   		<td class="$row">&nbsp;$!listPB.no_pb</td>
                   		<td class="$row">&nbsp;$!listPB.nama_pb</td>
                   		#if($saiz_pb!=0)<td class="$row" align="center"><input type="checkbox" name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listPB.id_pihakberkepentingan"></td>#end
              		<tr>
           		#end
        	#else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
        	#end
    				
    			</table>
    			
    		#if($saiz_pb > 5)
        	</div>
        	#end

	</fieldset>	
	#end -->
	
	#end
	
	
	
	#if($mode=="view")
	
	#if($onchange=="no")
	#foreach($data in $dataPenyampaianNotis)
		#set($txtNamaHantar=$data.nama_penghantar)
		#set($socJenisSerah=$data.jenis_serahan)
		#set($socStatusSerah=$data.status_serahan)
		#set($txdTarikhSerah=$data.tarikh_hantar)
		#set($txtNamaTerima=$data.nama_penerima)
		#set($txtNoKP=$data.no_kp_penerima)
		#set($txtCatatan=$data.catatan)
		#set($txtNoPB=$data.no_pb)
		#set($txtHubungan=$data.hubungan)
		#set($txtMasaTampal=$data.masa_tampal)
		#set($socJenisWaktu=$data.jenis_waktu)
		#set($txtTempatTampal=$data.tempat_tampal)
		
		
		
	#end
	#end
	
	#if($isEdit=="no")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#set($disability1 = "disabled")
	#else
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
	#end
	
	<fieldset id="top">
	<legend><strong>Penyampaian Notis Borang E</strong></legend>
	<table width="100%" border="0">
		<tr>
			<td width="1%">#if($isEdit=="yes")<font color="red">*</font>#end</td>
			<td width="24%">Nama Penghantar</td>
			<td width="1%">:</td>
			<td width="74%"><input $disability $disabilityx type="text" name="txtNamaHantar" id="txtNamaHantar" value="$!txtNamaHantar" size="47" maxlength="80"   ></td>
		</tr>
		
		<!-- <tr>
			<td>&nbsp;</td>
			<td>Kod / Perihal Dokumen</td>
			<td>:</td>
			<td><select name="socKodDokumen" $disability1 $disabilityx style="width:auto">
      		
      			<option value="">SILA PILIH</option>    			
				
			</select></td>
		</tr> -->
		
		<tr>
			<td>&nbsp;</td>
			<td>Jenis Serahan</td>
			<td>:</td>
			<td><select name="socJenisSerah" $disability1 $disabilityx style="width:160px">
      		
      			#if($socJenisSerah=="1")
      			<option value="1">PERKHIDMATAN POS</option>				
      			<option value="2">SERAHAN TANGAN</option>	
      			<option value="3">FAKSIMILI</option>	
      			<option value="">SILA PILIH</option>   	
      			#elseif($socJenisSerah=="2")
      			<option value="2">SERAHAN TANGAN</option>	
      			<option value="1">PERKHIDMATAN POS</option>	
      			<option value="3">FAKSIMILI</option>	
      			<option value="">SILA PILIH</option>  
      			#elseif($socJenisSerah=="3")
      			<option value="3">FAKSIMILI</option>
      			<option value="1">PERKHIDMATAN POS</option>	
      			<option value="2">SERAHAN TANGAN</option>	
      			<option value="">SILA PILIH</option>    
      			#else
      			<option value="">SILA PILIH</option>    			
      			<option value="1">PERKHIDMATAN POS</option>	
      			<option value="2">SERAHAN TANGAN</option>	
      			<option value="3">FAKSIMILI</option>	
      			#end
      			
			</select></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Status Serahan</td>
			<td>:</td>
			<td><select name="socStatusSerah" $disability1 $disabilityx style="width:auto" onchange="onChangeStatusUpdate()">
      		
      			#if($socStatusSerah=="1")
      			<option value="1">DISERAHKAN</option>
      			<option value="2">TIDAK DISERAHKAN</option>	
      			<option value="">SILA PILIH</option> 
      			#elseif($socStatusSerah=="2")
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
			<td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
			#if($hideItem=="no")
			<td>Tarikh Serahan</td>
			#else
			<td>Tarikh Tampal</td>
			#end
			<td>:</td>
			<td><input name="txdTarikhSerah" $disability $disabilityx id="txdTarikhSerah" size="11" type="text" value="$!txdTarikhSerah" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerah',false,'dmy');">&nbsp;$!frmtdate#end</td>
		</tr>
		
		#if($hideItem=="yes")
		<tr>
			<td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
			<td>Masa Tampal</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtMasaTampal" id="txtMasaTampal" value="$!txtMasaTampal" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
				<select $disability1 $disabilityx name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

				#if($socJenisWaktu=="1")
				<option value="1">PAGI</option>								
                <option value="2">TENGAHARI</option>
                <option value="3">PETANG</option>
                <option value="0">SILA PILIH</option>
				#elseif($socJenisWaktu=="2")
				<option value="2">TENGAHARI</option>
                <option value="1">PAGI</option>
                <option value="3">PETANG</option>
                <option value="0">SILA PILIH</option>
				#elseif($socJenisWaktu=="3")
				<option value="3">PETANG</option>
                <option value="1">PAGI</option>
                <option value="2">TENGAHARI</option>
                <option value="0">SILA PILIH</option>
				#else
				<option value="0">SILA PILIH</option>
                <option value="1">PAGI</option>
                <option value="2">TENGAHARI</option>
                <option value="3">PETANG</option>
				#end
                    				
                    	
             	</select>&nbsp;<font color="blue" style="font-size:10px">#if($isEdit=="yes")<i>format 12 jam (HHMM)</i>#end</font></td>
		</tr>
		#end
		
	</table>	
	</fieldset>	
	
	<fieldset>
	<table width="100%" border="0">
	
		<tr>
			<td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
			<td>Nama Pihak Berkepentingan</td>
			<td>:</td>
			<td>$!selectPB</td>
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
			<td><input type="text" name="txtNoPB" readonly class="disabled" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20"   ></td>
		</tr>
		
		#if($hideItem=="no")
		<tr>
			<td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
			<td>Nama Penerima</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtNamaTerima" id="txtNamaTerima" value="$!txtNamaTerima" size="47" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Kod No. Kad Pengenalan</td>
			<td>:</td>
			<td>$!selectJenisNoKP</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Kad Pengenalan</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtNoKP" id="txtNoKP" value="$!txtNoKP" size="20" maxlength="20"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Hubungan Dengan PB</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtHubungan" id="txtHubungan" value="$!txtHubungan" size="20" maxlength="80"   ></td>
		</tr>		
		#else
			<tr>
			<td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
			<td>Tempat Tampal</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtTempatTampal" id="txtTempatTampal" value="$!txtTempatTampal" size="47" maxlength="80"   ></td>
		</tr>
		#end
			
		<tr>
			<td width="1%">&nbsp;</td>
			#if($hideItem=="no")
			<td width="24%" valign="top">Catatan</td>
			#else
			<td width="24%" valign="top">Cara Tampal</td>
			#end
			<td width="1%" valign="top">:</td>
			<td width="74%" valign="top"><textarea name="txtCatatan" $disability $disabilityx id="txtCatatan" cols="44%" rows="5" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen3,400);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen3,400);" >$!txtCatatan</textarea></td>
		</tr>
		#if($isEdit=="yes")
		<tr>
        	<td colspan="3">&nbsp;</td>
            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="400"></td>
        </tr> 
        #end
	</table>
	</fieldset>
	
	<!-- #if($hideItem=="no")
	<br/>
	
	<fieldset>
	<legend><strong>Senarai Pihak Berkepentingan</strong></legend>
		
			#if($saiz_pb > 5)
        	<div style="width:100%;height:100;overflow:auto"> 
        	#end
            	
    			<table width="100%" border="0">	
    				<tr class="table_header">
    					<td width="4%" align="center"><b>No</b></td>
    					<td width="20%">&nbsp;<b>No. PB</b></td>
    					<td width="72%">&nbsp;<b>Nama PB</b></td>
    					#if($saiz_pb!=0)<td width="4%" align="center"><input type="checkbox" $disability1 title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></td>#end
    				</tr>
    				
    		#if($saiz_pb!=0)
           	 	#foreach($listPB in $listMaklumatPB)
              	#set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              			#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
                    	#if($listPB.flag!="0")
                    		#set($checked = "checked")
                    	#else
                    		#set($checked = "")
                    	#end
                    
               		<tr>
                   		<td class="$row" align="center">$!listPB.bil</td>
                   		<td class="$row">&nbsp;$!listPB.no_pb</td>
                   		<td class="$row">&nbsp;$!listPB.nama_pb</td>
                   		#if($saiz_pb!=0)<td class="$row" align="center"><input type="checkbox" $checked $disability1 name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listPB.id_pihakberkepentingan"></td>#end
              		<tr>
           		#end
        	#else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
        	#end
    				
    			</table>
    			
    		#if($saiz_pb > 5)
        	</div>
        	#end

	</fieldset>	
	#end -->
	
	#end
	

	<table width="100%" border="0">
		<tr align="center">
			<td>
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPenyampaianNotis('$!id_permohonan','$!id_buktipenyampaian','$!mode','$!hideItem')">
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPenyampaianNotis('$!id_buktipenyampaian')">
				<input type="button" name="cmdHapus" value="Hapus" onClick="hapusPenyampaianNotis('$!id_buktipenyampaian')" >
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPenyampaianNotis('$!id_permohonan','$!id_buktipenyampaian','$!mode','$!hideItem')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniPenyampaian('$!id_buktipenyampaian')">
				#end
			#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali('$!id_permohonan')">
				</td>
			</tr>
	</table>


	<fieldset id="bottom">
	<legend><strong>Senarai Penerima Notis Borang E</strong></legend>
	
		#if($mode=="view")
		<table width="100%" border="0">   
              <tr>
                 <td width="30%" align="left"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:viewListPenyampaianNotis('$!id_permohonan');"></td>
              </tr>
    	</table>
		#end
		
		#if($saiz_PenyampaianNotis > 5)
                <div style="width:100%;height:100;overflow:auto"> 
        #end
	
		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>Nama Pihak Berkepentingan</b></td>
        			<td><b>Nama Penerima</b></td>
        			<td><b>Nama Penghantar</b></td>
        			<td><b>Tarikh Serahan / Tarikh Tampal</b></td>
        		</tr>
        		
        		#if($saiz_PenyampaianNotis!=0)
                    #foreach($list in $listPenyampaianNotis)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!list.bil</td>
                   <td class="$row"><a href="javascript:viewPenyampaianNotis('$!list.id_buktipenyampaian')"><font color="blue">$!list.nama_pb</font></a></td>
                   <td class="$row">$!list.nama_penerima</td>
                   <td class="$row">$!list.nama_penghantar</td>
                   <td class="$row">$!list.tarikh_hantar</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		</table>
			
		#if($saiz_PenyampaianNotis > 5)
        	</div>
        #end	

</fieldset>	
</center>	

<br/>
		

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_buktipenyampaian" value="$!id_buktipenyampaian">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<script>

function onChangeStatusUpdate(){
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.command.value = "viewPenyampaianNotis";
	document.${formName}.command2.value = "kemaskiniPenyampaianNotis";
	document.${formName}.command3.value = "onChangeStatusUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
	document.${formName}.submit();
}
function onChangeStatus(){
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.command.value = "viewListPenyampaianNotis";
	document.${formName}.command2.value = "onChangeStatus";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
	document.${formName}.submit();
}
function onchangeGetnameUpdate(){
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.command.value = "viewPenyampaianNotis";
	document.${formName}.command2.value = "kemaskiniPenyampaianNotis";
	document.${formName}.command3.value = "onchangeGetnameUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
	document.${formName}.submit();
}
function onchangeGetname(){
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.command.value = "viewListPenyampaianNotis";
	document.${formName}.command2.value = "onchangeGetname";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
	document.${formName}.submit();
}
function hapusPenyampaianNotis(idBuktiPenyampaian){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_buktipenyampaian.value = idBuktiPenyampaian;
	document.${formName}.command.value = "hapusPenyampaianNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
	document.${formName}.submit();
}
function batalKemaskiniPenyampaian(idBuktiPenyampaian){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_buktipenyampaian.value = idBuktiPenyampaian;
	document.${formName}.command.value = "viewPenyampaianNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
	document.${formName}.submit();
}
function kemaskiniPenyampaianNotis(idBuktiPenyampaian){
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_buktipenyampaian.value = idBuktiPenyampaian;
	document.${formName}.command.value = "viewPenyampaianNotis";
	document.${formName}.command2.value = "kemaskiniPenyampaianNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
	document.${formName}.submit();
}
function viewListPenyampaianNotis(id_permohonan) {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListPenyampaianNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
	document.${formName}.submit();
}
function viewPenyampaianNotis(idBuktiPenyampaian){
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_buktipenyampaian.value = idBuktiPenyampaian;
	document.${formName}.command.value = "viewPenyampaianNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
	document.${formName}.submit();
}
function simpanPenyampaianNotis(idpermohonan,idBuktiPenyampaian,mode,valHide){

	if(valHide=="no"){
		var dat1 = document.${formName}.txdTarikhSerah

		//current date
		var currentTime = new Date();
    	var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var currentDate = new Date(year, month, day);

		//tarikh surat
		var TS  = document.getElementById("txdTarikhSerah").value;
		var dt1   = parseInt(TS.substring(0,2),10);
   		var mon1  = parseInt(TS.substring(3,5),10);
   		var yr1   = parseInt(TS.substring(6,10),10);
   		var dateSerah = new Date(yr1, mon1, dt1);
	}
	
	if(document.${formName}.txtNamaHantar.value == ""){
		alert("Sila masukkan \"Nama Penghantar\" terlebih dahulu.");
  		document.${formName}.txtNamaHantar.focus(); 
		return;
	}	
	else
	if(document.${formName}.txdTarikhSerah.value == ""){
		if(valHide=="no"){
			alert("Sila masukkan \"Tarikh Serahan\" terlebih dahulu.");
		}else{
			alert("Sila masukkan \"Tarikh Tampal\" terlebih dahulu.");
		}
		document.${formName}.txdTarikhSerah.focus(); 
		return;
	}
	else
	if(valHide=="yes" && document.${formName}.txtMasaTampal.value == ""){
		alert("Sila masukkan \"Masa Tampal\" terlebih dahulu.");
		document.${formName}.txtMasaTampal.focus(); 
		return;
	}
	else
	if (valHide=="no" && (dat1.value!="" && isDate(dat1.value)==false)){
		dat1.focus()
		return;
	}	
	else
	if(document.${formName}.socPB.value == ""){
		alert("Sila pilih \"Pihak Berkepentingan\" terlebih dahulu.");
		document.${formName}.socPB.focus(); 
		return;
	}
/*	else if(valHide=="no" && (dat1.value!="" && (dateSerah > currentDate))){
	   	alert("Sila pastikan \"Tarikh Serahan\" tidak lebih dari tarikh hari ini.");
		document.${formName}.txdTarikhSerah.focus();
		return;
	}
*/	else
	if(valHide=="yes" && document.${formName}.txtTempatTampal.value == ""){
		alert("Sila masukkan \"Tempat Tampal\" terlebih dahulu.");
		document.${formName}.txtTempatTampal.focus(); 
		return;
	}
	else
	if(valHide=="no" && document.${formName}.txtNamaTerima.value == ""){
		alert("Sila masukkan \"Nama Penerima\" terlebih dahulu.");
	  	document.${formName}.txtNamaTerima.focus(); 
		return;
	}	
	else if (valHide=="no" && (document.${formName}.txtNoKP.value != "" && (document.${formName}.socJenisNoKP.value == "1" && document.${formName}.txtNoKP.value.length < 12) || (document.${formName}.socJenisNoKP.value == "1" && document.${formName}.txtNoKP.value.length > 12))) {
		alert("Sila pastikan No.Kad Pengenalan baru diisi dengan betul");
		document.${formName}.txtNoKP.focus(); 
		return;

	}else{	
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "bottom";
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "viewListPenyampaianNotis";
			document.${formName}.command2.value = "simpanPenyampaianNotis";
		}else{
			document.${formName}.id_buktipenyampaian.value = idBuktiPenyampaian;
			document.${formName}.command.value = "viewPenyampaianNotis";
			document.${formName}.command2.value = "kemaskiniPenyampaianNotis";
			document.${formName}.command3.value = "updatePenyampaianNotis";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
		document.${formName}.submit();
	}
}
function kembali(id_permohonan){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
	document.${formName}.submit();
}
function checkDigit() {
	if(document.getElementById("txtMasaTampal").value != "" && document.getElementById("txtMasaTampal").value.length < 4){
		alert("Sila Pastikan Format Masa Tampal Adalah \"HHMM\"");
		document.${formName}.txtMasaTampal.focus(); 
		return;	
	}
}
function validateJenisWaktu(elmnt,content) {

	var length = parseInt(document.getElementById("txtMasaTampal").value.length);
	
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
</script>

<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
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
</script>

<!-- FOR CHECKBOX -->
<script language="javascript">
var checked = false;
function checkALL() {

	 if (document.${formName}.checkall.checked == true){
	
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = true;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = true;
	            }
	        }
	 } else {

	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = false;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = false;
	            }
	        }
	 }
}
function doUpdateCheckAll(){  

	var c = 0;
	if(document.${formName}.cbsemaks.length > 1){     
		
		for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	      if (document.${formName}.cbsemaks[i].checked == false){	 
		  	c++
	      }
		}  

	}else{
		
		if (document.${formName}.cbsemaks.checked == false){				 
			c++;
		}	 	
	}	 
	 
	if(c>0){
			  
		document.${formName}.checkall.checked = false;
	}
	else{
		document.${formName}.checkall.checked = true;
	}       
}
</script>

<script>
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
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
</script>