#parse("app/ppt/Sek8Paging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset id="top">
<legend><strong>Maklumat JPBD</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>

	<div id="TabbedPanels1" class="TabbedPanels">
  		<ul class="TabbedPanelsTabGroup">
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);viewMaklumat('$!id_permohonan')" tabindex="1">Jabatan Perancangan Bandar dan Desa (JPBD)</li>
   			#if($showTab=="yes")<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);viewMaklumat('$!id_permohonan')" tabindex="1">Jabatan Penilaian dan Perkhidmatan Harta (JPPH)</li>#end
  		</ul>
  		
  		<div class="TabbedPanelsContentGroup">
  		
  			<!-- START TAB 1 -->
    		<div class="TabbedPanelsContent">
    			<br/>
    			#if($mode=="new")
    			
    			<!-- <fieldset>
    				<table width="100%" border="0">
    					<tr>
    						<td width="20%">Kod Pejabat JPBD</td>
    						<td width="1%">:</td>
    						<td width="79%"><input type="text" name="txtKodPejabatJPBD" id="txtKodPejabatJPBD" value="" size="20" maxlength="30"   /></td>
    					</tr>
    				</table>
    			</fieldset> -->
    			
    			<table width="100%" border="0">
    				<tr>
    				
    				<!-- TABLE KIRI -->
    				<td width="50%" valign="top">
    					
    					<fieldset>
    					<legend><strong>Dihantar</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan</td>
    							<td width="1%">:</td>
    							<td width="54%"><input type="text" name="txtBilSurat" id="txtBilSurat" value="" size="30" maxlength="50"  /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Surat</td>
    							<td>:</td>
    							<td><input name="txdTarikhSurat" id="txdTarikhSurat" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr>
    						<tr>
    							<td width="45%">Nama Pegawai JPBD</td>
    							<td width="1%">:</td>
    							<td width="54%"><input type="text" name="namaJPBD" id="namaJPBD" value="" size="50" maxlength="100"  /></td>
    						</tr>
    						<tr>
    							<td width="45%">Emel Pegawai JPBD</td>
    							<td width="1%">:</td>
    							<td width="54%"><input type="text" name="emelJPBD" id="emelJPBD" value="" size="50" maxlength="100"  /></td>
    						</tr>
    						
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				
    				<!-- TABLE KANAN -->
    				<td width="50%" valign="top">
    				
    					<fieldset>
    					<legend><strong>Diterima</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan Surat JPBD</td>
    							<td width="1%">:</td>
    							<td width="54%"><input type="text" name="txtNoRujSurat" id="txtNoRujSurat" value="" size="25" maxlength="50"   /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Terima Surat</td>
    							<td>:</td>
    							<td><input name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr>
    						<!-- <tr>
    							<td>Tarikh Terima</td>
    							<td>:</td>
    							<td><input name="txdTarikhTerimaJT" id="txdTarikhTerimaJT" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaJT',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr> -->
    						<tr>
    							<td>Tarikh Surat JPBD</td>
    							<td>:</td>
    							<td><input name="txdTarikhSuratJT" id="txdTarikhSuratJT" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratJT',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr>
    						<tr>
    							<td valign="top">Ulasan</td>
    							<td valign="top">:</td>
    							<td><textarea name="txtUlasanJPBD" id="txtUlasanJPBD" rows="3" cols="25%" onKeyUp="textCounter(this.form.txtUlasanJPBD,this.form.remLen99,1500);" onKeyDown="textCounter(this.form.txtUlasanJPBD,this.form.remLen99,1500);" ></textarea></td>
    						</tr>
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				</tr>
    			</table>
    			#end
    
    
    			#if($mode=="view")
    			
    				#foreach($data in $dataMaklumatJPBD)
    					#set($txtKodPejabatJPBD=$data.kod_pejabat_jpbd)
    					#set($txtBilSurat=$data.bil_surat)
    					#set($txdTarikhSurat=$data.tarikh_surat)
    					#set($txdTarikhTerima=$data.tarikh_akhir)
    					#set($txtNoRujSurat=$data.no_rujukansuratjt)
    					#set($txdTarikhTerimaJT=$data.tarikh_terimajt)
    					#set($txdTarikhSuratJT=$data.tarikh_suratjt)
    					#set($txtUlasanJPBD=$data.ulasanjt)
    					#set($namaJPBD=$data.nama_jpbd)
    					#set($emelJPBD=$data.emel_jpbd)
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
	
				<!-- <fieldset>
    				<table width="100%" border="0">
    					<tr>
    						<td width="20%">Kod Pejabat JPBD</td>
    						<td width="1%">:</td>
    						<td width="79%"><input $disability $disabilityx type="text" name="txtKodPejabatJPBD" id="txtKodPejabatJPBD" value="$!txtKodPejabatJPBD" size="20" maxlength="30"   /></td>
    					</tr>
    				</table>
    			</fieldset> -->
    			
    			
    			<table width="100%" border="0">
    				<tr>
    				
    				<!-- TABLE KIRI -->
    				<td width="50%" valign="top">
    					
    					<fieldset>
    					<legend><strong>Dihantar</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan</td>
    							<td width="1%">:</td>
    							<td width="54%"><input $disability $disabilityx type="text" name="txtBilSurat" id="txtBilSurat" value="$!txtBilSurat" size="30" maxlength="50"  /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Surat</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhSurat" id="txdTarikhSurat" size="11" type="text" value="$!txdTarikhSurat" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr>
    						</tr>
    							<tr>
    							<td width="45%">Nama Pegawai JPBD</td>
    							<td width="1%">:</td>
    							<td width="54%"><input $disability $disabilityx type="text" name="namaJPBD" id="namaJPBD" value="$!namaJPBD" size="50" maxlength="100"  /></td>
    						</tr>
    						<tr>
    							<td width="45%">Emel Pegawai JPBD</td>
    							<td width="1%">:</td>
    							<td width="54%"><input $disability $disabilityx type="text" name="emelJPBD" id="emelJPBD" value="$!emelJPBD" size="50" maxlength="100"  /></td>
    						</tr>
    						
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				
    				<!-- TABLE KANAN -->
    				<td width="50%" valign="top">
    				
    					<fieldset>
    					<legend><strong>Diterima</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan Surat JPBD</td>
    							<td width="1%">:</td>
    							<td width="54%"><input $disability $disabilityx type="text" name="txtNoRujSurat" id="txtNoRujSurat" value="$!txtNoRujSurat" size="25" maxlength="50"   /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Terima Surat</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr>
    						<!-- <tr>
    							<td>Tarikh Terima</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhTerimaJT" id="txdTarikhTerimaJT" size="11" type="text" value="$!txdTarikhTerimaJT" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaJT',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr> -->
    						
    						<tr>
    							<td>Tarikh Surat JPBD</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhSuratJT" id="txdTarikhSuratJT" size="11" type="text" value="$!txdTarikhSuratJT" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratJT',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr>
    						<tr>
    							<td valign="top">Ulasan</td>
    							<td valign="top">:</td>
    							<td><textarea $disability $disabilityx name="txtUlasanJPBD" id="txtUlasanJPBD" rows="3" cols="25%"  onKeyUp="textCounter(this.form.txtUlasanJPBD,this.form.remLen99,1500);" onKeyDown="textCounter(this.form.txtUlasanJPBD,this.form.remLen99,1500);" >$!txtUlasanJPBD</textarea></td>
    						</tr>
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				</tr>
    			</table>
    			#end
    
    			<table width="100%" border="0">
				<tr align="center">
					<td>
					<input type="button" name="cmdHantarEmel" value ="Hantar Emel" onClick="javascript:hantarEmelJPBD('$!id_permohonan')">
					<input type="button" name="cmdINTJPBD" id="cmdINTJPBD" value="Ulasan JPBD" onclick="javascript:intNilaianJPBD('$!id_permohonan')"/>
                    
					#if($mode=="new")
					
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanMaklumatJPBD('$!id_permohonan','$!id_jpbd','$!mode')">
					#end
				
					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
					
					#if($mode=="view")
						#if($isEdit=="no")
						
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniJPBD('$!id_permohonan')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanMaklumatJPBD('$!id_permohonan','$!id_jpbd','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalJPBD('$!id_permohonan')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali('$!id_permohonan')">
					</td>
				</tr>
				</table>
			
    		</div>
    		<!-- END TAB 1 -->
    
    
    		#if($showTab=="yes")
    		<!-- START TAB 2 -->
    		<div class="TabbedPanelsContent">
    
    			<br/>
    			
    			#if($modeJPPH=="new")
    			
    			<!-- <fieldset>
    				<table width="100%" border="0">
    					<tr>
    						<td width="20%">Kod Pejabat JPPH</td>
    						<td width="1%">:</td>
    						<td width="79%"><input type="text" name="txtKodPejabatJPPH" id="txtKodPejabatJPPH" value="" size="20" maxlength="30"   /></td>
    					</tr>
    				</table>
    			</fieldset> -->
    			
    			
    			<table width="100%" border="0">
    				<tr>
    				
    				<!-- TABLE KIRI -->
    				<td width="50%" valign="top">
    					
    					<fieldset>
    					<legend><strong>Dihantar</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan</td>
    							<td width="1%">:</td>
    							<td width="54%"><input type="text" name="txtBilSuratH" id="txtBilSuratH" value="" size="30" maxlength="50"   /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Surat</td>
    							<td>:</td>
    							<td><input name="txdTarikhSuratH" id="txdTarikhSuratH" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratH',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr>
    						<tr>
    							<td width="45%">Nama Pegawai JPPH</td>
    							<td width="1%">:</td>
    							<td width="54%"><input type="text" name="namaJPPH" id="namaJPPH" value="" size="50" maxlength="100"  /></td>
    						</tr>
    						<tr>
    							<td width="45%">Emel Pegawai JPPH</td>
    							<td width="1%">:</td>
    							<td width="54%"><input type="text" name="emelJPPH" id="emelJPPH" value="" size="50" maxlength="100"  /></td>
    						</tr>
    						
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				
    				<!-- TABLE KANAN -->
    				<td width="50%" valign="top">
    				
    					<fieldset>
    					<legend><strong>Diterima</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan Surat JPPH</td>
    							<td width="1%">:</td>
    							<td width="54%"><input type="text" name="txtNoRujSuratH" id="txtNoRujSuratH" value="" size="25" maxlength="50"   /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Terima Surat</td>
    							<td>:</td>
    							<td><input name="txdTarikhTerimaH" id="txdTarikhTerimaH" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaH',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr>
    						<!-- <tr>
    							<td>Tarikh Terima</td>
    							<td>:</td>
    							<td><input name="txdTarikhTerimaJTH" id="txdTarikhTerimaJTH" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaJTH',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr> -->
    						
    						<tr>
    							<td>Tarikh Surat JPPH</td>
    							<td>:</td>
    							<td><input name="txdTarikhSuratJTH" id="txdTarikhSuratJTH" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratJTH',false,'dmy');">&nbsp;$!frmtdate</td>
    						</tr>
    						<tr>
    							<td valign="top">Ulasan</td>
    							<td valign="top">:</td>
    							<td><textarea name="txtUlasanJPPH" id="txtUlasanJPPH" rows="3" cols="25%"  onKeyUp="textCounter(this.form.txtUlasanJPPH,this.form.remLen9,1500);" onKeyDown="textCounter(this.form.txtUlasanJPPH,this.form.remLen9,1500);" ></textarea></td>
    						</tr>
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				</tr>
    			</table>
    			#end
    			
    			
    			
    			#if($modeJPPH=="view")
    			
    				#foreach($dataH in $dataMaklumatJPPH)
    					#set($txtKodPejabatJPPH=$dataH.kod_pejabat_jpph)
    					#set($txtBilSuratH=$dataH.bil_surat)
    					#set($txdTarikhSuratH=$dataH.tarikh_surat)
    					#set($txdTarikhTerimaH=$dataH.tarikh_akhir)
    					#set($txtNoRujSuratH=$dataH.no_rujukansuratjt)
    					#set($txdTarikhTerimaJTH=$dataH.tarikh_terimajt)
    					#set($txdTarikhSuratJTH=$dataH.tarikh_suratjt)
    					#set($txtUlasanJPPH=$dataH.ulasanjt)
    					#set($namaJPPH=$dataH.nama_jpph)
    					#set($emelJPPH=$dataH.emel_jpph)
    				#end
    			
    			
    				#if($isEditJPPH=="no")
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
	
				<!--  <fieldset>
    				<table width="100%" border="0">
    					<tr>
    						<td width="20%">Kod Pejabat JPPH</td>
    						<td width="1%">:</td>
    						<td width="79%"><input $disability $disabilityx type="text" name="txtKodPejabatJPPH" id="txtKodPejabatJPPH" value="$!txtKodPejabatJPPH" size="20" maxlength="30"   /></td>
    					</tr>
    				</table>
    			</fieldset> -->
    			
    			
    			<table width="100%" border="0">
    				<tr>
    				
    				<!-- TABLE KIRI -->
    				<td width="50%" valign="top">
    					
    					<fieldset>
    					<legend><strong>Dihantar</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan</td>
    							<td width="1%">:</td>
    							<td width="54%"><input $disability $disabilityx type="text" name="txtBilSuratH" id="txtBilSuratH" value="$!txtBilSuratH" size="30" maxlength="50"  /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Surat</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhSuratH" id="txdTarikhSuratH" size="11" type="text" value="$!txdTarikhSuratH" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEditJPPH=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratH',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr>
    							<tr>
    							<td width="45%">Nama Pegawai JPPH</td>
    							<td width="1%">:</td>
    							<td width="54%"><input $disability $disabilityx type="text" name="namaJPPH" id="namaJPPH" value="$!namaJPPH" size="50" maxlength="100"  /></td>
    						</tr>
    						<tr>
    							<td width="45%">Emel Pegawai JPPH</td>
    							<td width="1%">:</td>
    							<td width="54%"><input $disability $disabilityx type="text" name="emelJPPH" id="emelJPPH" value="$!emelJPPH" size="50" maxlength="100"  /></td>
    						</tr>
    						
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				
    				<!-- TABLE KANAN -->
    				<td width="50%" valign="top">
    				
    					<fieldset>
    					<legend><strong>Diterima</strong></legend>
    					<table width="100%" border="0">
    						<tr>
    							<td width="45%">No. Rujukan Surat JPPH</td>
    							<td width="1%">:</td>
    							<td width="54%"><input $disability $disabilityx type="text" name="txtNoRujSuratH" id="txtNoRujSuratH" value="$!txtNoRujSuratH" size="25" maxlength="50"   /></td>
    						</tr>
    						<tr>
    							<td>Tarikh Terima Surat</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhTerimaH" id="txdTarikhTerimaH" size="11" type="text" value="$!txdTarikhTerimaH" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEditJPPH=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaH',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr>
    						<!-- <tr>
    							<td>Tarikh Terima</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhTerimaJTH" id="txdTarikhTerimaJTH" size="11" type="text" value="$!txdTarikhTerimaJTH" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEditJPPH=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaH',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr> -->
    						
    						<tr>
    							<td>Tarikh Surat JPPH</td>
    							<td>:</td>
    							<td><input $disability $disabilityx name="txdTarikhSuratJTH" id="txdTarikhSuratJTH" size="11" type="text" value="$!txdTarikhSuratJTH" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            					#if($isEditJPPH=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratJTH',false,'dmy');">&nbsp;$!frmtdate#end</td>
    						</tr>
    						<tr>
    							<td valign="top">Ulasan</td>
    							<td valign="top">:</td>
    							<td><textarea $disability $disabilityx name="txtUlasanJPPH" id="txtUlasanJPPH" rows="3" cols="25%" onKeyUp="textCounter(this.form.txtUlasanJPPH,this.form.remLen9,1500);" onKeyDown="textCounter(this.form.txtUlasanJPPH,this.form.remLen9,1500);" >$!txtUlasanJPPH</textarea></td>
    						</tr>
    					</table>
    					</fieldset>
    					
    				</td>
    				
    				</tr>
    			</table>
    			#end
    			
    
    		<table width="100%" border="0">
				<tr align="center">
					<td>
					<input type="button" name="cmdHantarEmel" value ="Hantar Emel" onClick="javascript:hantarEmelJPPH('$!id_permohonan')">
					#if($modeJPPH=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanMaklumatJPPH('$!id_permohonan','$!id_jpph','$!modeJPPH')">
					#end
				
					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport2')" />
				
					#if($modeJPPH=="view")
						#if($isEditJPPH=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniJPPH('$!id_permohonan')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanMaklumatJPPH('$!id_permohonan','$!id_jpph','$!modeJPPH')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalJPPH('$!id_permohonan')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali('$!id_permohonan')">
					</td>
				</tr>
			</table>
    
    		</div>
  			<!-- END TAB 1 -->
  			#end
  			
  		</div>
	</div>


</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
    	 	<td><a href="#" onClick="javascript:cetakPerkara1JPBD('$!id_permohonan')"><font color="blue">Maklumat Perancang JPBD</font></a></td>
      </tr>	
      <tr>
        	<td ><a href="#" class="style2" onClick="javascript:cetakSuratJPBD('$!id_permohonan','permohonan')"><font color="blue">Lampiran A1 (Untuk 1 Permohonan)</font></a></td>
      </tr>   
      <tr>
        	<td ><a href="#" class="style2" onClick="javascript:cetakSuratJPBD('$!id_permohonan','lot')"><font color="blue">Lampiran A1 (Untuk Setiap Lot)</font></a></td>
      </tr> 
    </table>
</fieldset>

<fieldset id="tableReport2" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
    	 	<td><a href="#" onClick="javascript:cetakPerancangJPPH('$!id_permohonan')"><font color="blue">Maklumat Perancang JPPH</font></a></td>
      </tr>	
	  <tr>
    	 	<td><a href="#" onClick="javascript:cetakJPBDkeJPPH('$!id_permohonan')"><font color="blue">Surat Kepada JPPH setelah Maklumat JPBD Diperolehi</font></a></td>
      </tr>
    </table>
</fieldset>

<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_jpbd" value="$!id_jpbd">
<input type="hidden" name="id_jpph" value="$!id_jpph">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>




<script>
function cetakJPBDkeJPPH(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=JPBDkeJPPH&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
  
}
function cetakPerancangJPPH(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=PerancangJPPH&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
  
}
function cetakPerkara1JPBD(idpermohonan) {

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=Perkara1JPBD&selectNoFail=yes&flagShowTarikhCetak=yes";
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
function cetakSuratJPBD(idpermohonan,flagCetakJPBD) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&flagCetakJPBD="+flagCetakJPBD+"&report=LampiranA1&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function batalJPPH(idpermohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD";
	document.${formName}.submit();
}
function kemaskiniJPPH(idpermohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.command2.value = "kemaskiniJPPH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD";
	document.${formName}.submit();
}
function simpanMaklumatJPPH(idpermohonan,idjpph,mode) {

	var dat1=document.${formName}.txdTarikhSuratH
	//var dat2=document.${formName}.txdTarikhTerimaH
	//var dat3=document.${formName}.txdTarikhTerimaJTH
	var dat4=document.${formName}.txdTarikhSuratJTH

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat
	var TS  = document.getElementById("txdTarikhSuratH").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateSurat = new Date(yr1, mon1, dt1);

   	
  	//tarikh terima jawapan
	//var TT  = document.getElementById("txdTarikhTerimaH").value;
	//var dt2   = parseInt(TT.substring(0,2),10);
   	//var mon2  = parseInt(TT.substring(3,5),10);
   	//var yr2   = parseInt(TT.substring(6,10),10);
   	//var dateTerima = new Date(yr2, mon2, dt2);

    //tarikh terima JT
	//var TTJT  = document.getElementById("txdTarikhTerimaJTH").value;
	//var dt3   = parseInt(TTJT.substring(0,2),10);
   	//var mon3  = parseInt(TTJT.substring(3,5),10);
   	//var yr3   = parseInt(TTJT.substring(6,10),10);
   	//var dateTerimaJT = new Date(yr3, mon3, dt3);

 	//tarikh surat JT
	var TSJT  = document.getElementById("txdTarikhSuratJTH").value;
	var dt4   = parseInt(TSJT.substring(0,2),10);
   	var mon4  = parseInt(TSJT.substring(3,5),10);
   	var yr4   = parseInt(TSJT.substring(6,10),10);
   	var dateSuratJT = new Date(yr4, mon4, dt4);

	
	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if(dat1.value!="" && (dateSurat < currentDate)){
	   	alert("Sila pastikan \"Tarikh Surat\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhSuratH.focus();
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if(dat2.value!="" && (dateTerima < currentDate)){
	   	alert("Sila pastikan \"Tarikh Jawapan Diterima\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTerimaH.focus();
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
	else if(dat3.value!="" && (dateTerimaJT < currentDate)){
	   	alert("Sila pastikan \"Tarikh Terima\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTerimaJTH.focus();
		return;
	}
*/	else if (dat4.value!="" && isDate(dat4.value)==false)
	{
		dat4.focus()
		return;
	}
/*	else if(dat4.value!="" && (dateSuratJT < currentDate)){
	   	alert("Sila pastikan \"Tarikh Surat JPPH\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhSuratJTH.focus();
		return;
	}
*/	else{
	
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.tabId.value = "1";
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "viewMaklumat";
			document.${formName}.command2.value = "simpanMaklumatJPPH";
		}else{
			document.${formName}.id_jpph.value = idjpph;
			document.${formName}.command.value = "viewMaklumat";
			document.${formName}.command2.value = "kemaskiniJPPH";
			document.${formName}.command3.value = "updateJPPH";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD";
		document.${formName}.submit();
	}
}
function batalJPBD(idpermohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD";
	document.${formName}.submit();
}
function kemaskiniJPBD(idpermohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.command2.value = "kemaskiniJPBD";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD";
	document.${formName}.submit();
}
function simpanMaklumatJPBD(idpermohonan,idjpbd,mode) {

	var dat1=document.${formName}.txdTarikhSurat
	var dat2=document.${formName}.txdTarikhTerima
	//var dat3=document.${formName}.txdTarikhTerimaJT
	var dat4=document.${formName}.txdTarikhSuratJT

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat
	var TS  = document.getElementById("txdTarikhSurat").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateSurat = new Date(yr1, mon1, dt1);

  	//tarikh terima jawapan
	var TT  = document.getElementById("txdTarikhTerima").value;
	var dt2   = parseInt(TT.substring(0,2),10);
   	var mon2  = parseInt(TT.substring(3,5),10);
   	var yr2   = parseInt(TT.substring(6,10),10);
   	var dateTerima = new Date(yr2, mon2, dt2);

    //tarikh terima JT
	//var TTJT  = document.getElementById("txdTarikhTerimaJT").value;
	//var dt3   = parseInt(TTJT.substring(0,2),10);
    //var mon3  = parseInt(TTJT.substring(3,5),10);
    //var yr3   = parseInt(TTJT.substring(6,10),10);
   	//var dateTerimaJT = new Date(yr3, mon3, dt3);

 	//tarikh surat JT
	var TSJT  = document.getElementById("txdTarikhSuratJT").value;
	var dt4   = parseInt(TSJT.substring(0,2),10);
   	var mon4  = parseInt(TSJT.substring(3,5),10);
   	var yr4   = parseInt(TSJT.substring(6,10),10);
   	var dateSuratJT = new Date(yr4, mon4, dt4);

	
	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if(dat1.value!="" && (dateSurat < currentDate)){
	   	alert("Sila pastikan \"Tarikh Surat\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhSurat.focus();
		return;
	}
*/	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
/*	else if(dat2.value!="" && (dateTerima < currentDate)){
	   	alert("Sila pastikan \"Tarikh Jawapan Diterima\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTerima.focus();
		return;
	}
*/	/*else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
	else if(dat3.value!="" && (dateTerimaJT < currentDate)){
	   	alert("Sila pastikan \"Tarikh Terima\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTerimaJT.focus();
		return;
	}*/
	else if (dat4.value!="" && isDate(dat4.value)==false)
	{
		dat4.focus()
		return;
	}
/*	else if(dat4.value!="" && (dateSuratJT < currentDate)){
	   	alert("Sila pastikan \"Tarikh Surat JPBD\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhSuratJT.focus();
		return;
	}
*/	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.tabId.value = "0";
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "viewMaklumat";
			document.${formName}.command2.value = "simpanMaklumatJPBD";
		}else{
			document.${formName}.id_jpbd.value = idjpbd;
			document.${formName}.command.value = "viewMaklumat";
			document.${formName}.command2.value = "kemaskiniJPBD";
			document.${formName}.command3.value = "updateJPBD";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD";
		document.${formName}.submit();
	}
}
function kembali(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD";
	document.${formName}.submit();
}

function hantarEmelJPBD(id_permohonan) {
	//alert(id_permohonan);
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD&FlagHantarEmelJPBD=Y";
	document.${formName}.submit();
}

function hantarEmelJPPH(id_permohonan) {
	//alert(id_permohonan);
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD&FlagHantarEmelJPPH=Y";
	document.${formName}.submit();
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
window.onload = submitForm;
function viewMaklumat(id_permohonan) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD";
	document.${formName}.submit();
}
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
      var strValidCharacters = "1234567890./";
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
function intNilaianJPBD(id_permohonan) {
	// shiqa 17092020
	// to cater for integration
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewBorangLampiranA1&action2=viewBorangLampiranA1&ID_PERMOHONAN=" + id_permohonan;
	document.${formName}.method = "POST";
	document.${formName}.submit();
}
</script>