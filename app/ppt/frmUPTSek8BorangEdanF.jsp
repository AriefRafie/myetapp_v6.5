#parse("app/ppt/Sek8Paging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")



<center>

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>

	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
	
	<div id="TabbedPanels1" class="TabbedPanels">
  		<ul class="TabbedPanelsTabGroup">
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);viewListBorangF($!id_hakmilik)" tabindex="1">Borang E</li>
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);tambahPenerima($!id_hakmilik)" tabindex="1">Borang F</li>
    	</ul> 	
  	
  		<div class="TabbedPanelsContentGroup">
  	
  	
  		<!-- START TAB 1 (BORANG E)-->
    	<div class="TabbedPanelsContent">
    		
    		<br/>
    		
			<fieldset>
			<legend>Maklumat Borang E</legend>
			#if($mode=="new")
			<table width="100%" border="0">  
				<tr>
					<td width="1%">&nbsp;</td>
					<td width="18%">Tarikh Borang E</td>
					<td width="1%">:</td>
					<td width="80%"><input name="txdTarikhBorangE" id="txdTarikhBorangE" size="11" type="text" value="$!txdTarikhBorangE" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangE',false,'dmy');">&nbsp;$!frmtdate</td>
				</tr>
			
				<tr>
                	<td>&nbsp;</td>
                	<td>Tarikh Borang F</td>
                	<td>:</td>
                	<td><input name="txdTarikhBorangF" id="txdTarikhBorangF" size="11" type="text" value="$!txdTarikhBorangF" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangF',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr>
			
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Akhir Tampal</td>
					<td>:</td>
					<td><input name="txdTarikhTampal" id="txdTarikhTampal" size="11" type="text" value="$!txdTarikhTampal" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this);getTarikhSiasatan()" >
            		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTampal',false,'dmy');">&nbsp;$!frmtdate</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Siasatan</td>
					<td>:</td>
					<td><input name="txdTarikhSiasatan" id="txdTarikhSiasatan" size="11" type="text" value="$!txdTarikhSiasatan" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSiasatan',false,'dmy');">&nbsp;$!frmtdate</td>
				</tr>
			
				<tr>
					<td>&nbsp;</td>
					<td>Masa Siasatan</td>
					<td>:</td>
					<td><input type="text" name="txtMasaSiasatan" id="txtMasaSiasatan" value="$!txtMasaSiasatan" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
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
					<td>Alamat</td>
					<td>:</td>
					<td><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="45" maxlength="80" ></td>
				</tr>
			
				<tr>
					<td colspan="3">&nbsp;</td>
					<td><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="45" maxlength="80" ></td>
				</tr>
			
				<tr>
					<td colspan="3">&nbsp;</td>
					<td><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="45" maxlength="80" ></td>
				</tr>
			
				<tr>
					<td>&nbsp;</td>
					<td>Poskod</td>
					<td>:</td>
					<td><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value)" ></td>
				</tr>
			
				<tr>
					<td>&nbsp;</td>
					<td>Negeri</td>
					<td>:</td>
					<td>$!selectNegeri</td>
				</tr>
			
				<tr>
					<td>&nbsp;</td>
					<td>Bandar</td>
					<td>:</td>
					<td>$!selectBandar</td>
				</tr>
			</table>
			#end
		
		
			#if($mode=="view")
		
			#if($onchange=="no")
			#foreach($data in $dataBorangE)
				#set($txdTarikhBorangF=$data.tarikh_borangf)
				#set($txdTarikhBorangE=$data.tarikh_borange)
				#set($txdTarikhSiasatan=$data.tarikh_siasatan)
				#set($txtMasaSiasatan=$data.masa_siasatan)
				#set($socJenisWaktu=$data.jenis_waktu)
				#set($txtAlamat1=$data.alamat1)
				#set($txtAlamat2=$data.alamat2)
				#set($txtAlamat3=$data.alamat3)
				#set($txtPoskod=$data.poskod)
				#set($tarikh_cetak=$data.tarikh_cetak)
				#set($txdTarikhTampal=$data.tarikh_akhir_tampal)				
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
					<td width="18%">Tarikh Borang E</td>
					<td width="1%">:</td>
					<td width="80%"><input $disability $disabilityx name="txdTarikhBorangE" id="txdTarikhBorangE" size="11" type="text" value="$!txdTarikhBorangE" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
	            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangE',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
				
				<tr>
	                <td>&nbsp;</td>
	                <td>Tarikh Borang F</td>
	                <td>:</td>
	                <td><input $disability $disabilityx name="txdTarikhBorangF" id="txdTarikhBorangF" size="11" type="text" value="$!txdTarikhBorangF" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
	            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangF',false,'dmy');">&nbsp;$!frmtdate#end</td>
	    		</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Akhir Tampal</td>
					<td>:</td>
					<td><input $disability $disabilityx name="txdTarikhTampal" id="txdTarikhTampal" size="11" type="text" value="$!txdTarikhTampal" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this);getTarikhSiasatan()" >
            		#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTampal',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Siasatan</td>
					<td>:</td>
					<td><input $disability $disabilityx name="txdTarikhSiasatan" id="txdTarikhSiasatan" size="11" type="text" value="$!txdTarikhSiasatan" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
	            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSiasatan',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
			
				<tr>
					<td>&nbsp;</td>
					<td>Masa Siasatan</td>
					<td>:</td>
					<td><input $disability $disabilityx type="text" name="txtMasaSiasatan" id="txtMasaSiasatan" value="$!txtMasaSiasatan" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
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
						
	                </select>&nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>Alamat</td>
					<td>:</td>
					<td><input $disability $disabilityx type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="45" maxlength="80"></td>
				</tr>
				
				<tr>
					<td colspan="3">&nbsp;</td>
					<td><input $disability $disabilityx type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="45" maxlength="80"></td>
				</tr>
				
				<tr>
					<td colspan="3">&nbsp;</td>
					<td><input $disability $disabilityx type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="45" maxlength="80" ></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>Poskod</td>
					<td>:</td>
					<td><input $disability $disabilityx type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value)" ></td>
				</tr>
			
				<tr>
					<td>&nbsp;</td>
					<td>Negeri</td>
					<td>:</td>
					<td>$!selectNegeri</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>Bandar</td>
					<td>:</td>
					<td>$!selectBandar</td>
				</tr>
			
			</table>
			#end
			
			<br/>
				
		</fieldset>
	
		<table width="100%" border="0">
			<tr align="center">
				<td>
				
				#if($mode=="new")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanBorangE('$!id_hakmilik','$!id_borange','$!mode')">
				#end
					
				#if($mode=="view")
					#if($isEdit=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBorangE('$!id_borange')">
					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBorangE('$!id_hakmilik','$!id_borange','$!mode')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_hakmilik')">
					#end
				#end
					
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:viewListHM('$!id_permohonan');">
					
				</td>
			</tr>
		</table>
    
    	</div>
    	<!-- END TAB 1 (BORANG E)-->
	
	
	
		<!-- START TAB 2 (BORANG F)-->
    	<div class="TabbedPanelsContent">
    
    		<br/>
    		
    		<fieldset>
			<legend><strong>Maklumat Borang F</strong></legend>
			
				#if($mode=="new")
				<table width="100%" border="0">
					<tr>
						<td width="1%"><font color="red">*</font></td>
						<td width="20%">Nama Penerima</td>
						<td width="1%">:</td>
						<td width="78%">$!selectPB</td>
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
						<td><input type="text" name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20"   ></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Alamat</td>
						<td>:</td>
						<td><input type="text" name="txtAlamat1F" id="txtAlamat1F" value="$!txtAlamat1F" size="45" maxlength="80"   ></td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
						<td><input type="text" name="txtAlamat2F" id="txtAlamat2F" value="$!txtAlamat2F" size="45" maxlength="80"   ></td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
						<td><input type="text" name="txtAlamat3F" id="txtAlamat3F" value="$!txtAlamat3F" size="45" maxlength="80"   ></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Poskod</td>
						<td>:</td>
						<td><input type="text" name="txtPoskodF" id="txtPoskodF" value="$!txtPoskodF" size="5" maxlength="5" onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value)" ></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Negeri</td>
						<td>:</td>
						<td>$!selectNegeriBorangF</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Bandar</td>
						<td>:</td>
						<td>$!selectBandarBorangF</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Tempoh (Hari)</td>
						<td>:</td>
						<td><input type="text" name="txtTempoh" id="txtTempoh" value="$!txtTempoh" size="5" maxlength="3"   ></td>
					</tr>		
					<!--<tr>
						<td>&nbsp;</td>
						<td>Tarikh Terima</td>
						<td>:</td>
						<td><input name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
			            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate</td>
					</tr> -->
					<tr>
						<td>&nbsp;</td>
						<td valign="top">Keterangan</td>
						<td valign="top">:</td>
						<td valign="top"><textarea name="txtKeterangan" id="txtKeterangan" cols="45%" rows="5" onKeyUp="textCounter(this.form.txtKeterangan,this.form.remLen3,1500);" onKeyDown="textCounter(this.form.txtKeterangan,this.form.remLen3,1500);" >$!txtKeterangan</textarea></td>
					</tr>
					<tr>
			        	<td colspan="3">&nbsp;</td>
			            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="1500"></td>
			        </tr> 
				</table>
				#end
				
				
				
				#if($mode=="view")
				
				#if($onchange=="no")
				#foreach($data in $dataPenerima)
					#set($txtAlamat1F=$data.alamat1)
					#set($txtAlamat2F=$data.alamat2)
					#set($txtAlamat3F=$data.alamat3)
					#set($txtPoskodF=$data.poskod)
					#set($txtTempoh=$data.tempoh)
					#set($txdTarikhTerima=$data.tarikh_terima)
					#set($txtKeterangan=$data.ulasan)
					#set($txtNoPB=$data.no_pb)
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
				
				<table width="100%" border="0">
					<tr>
						<td width="1%">#if($isEdit=="yes")<font color="red">*</font>#end</td>
						<td width="20%">Nama Penerima</td>
						<td width="1%">:</td>
						<td width="78%">$!selectPB</td>
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
						<td><input $disability $disabilityx type="text" name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20"   ></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Alamat</td>
						<td>:</td>
						<td><input type="text" $disability $disabilityx name="txtAlamat1F" id="txtAlamat1F" value="$!txtAlamat1F" size="45" maxlength="80"   ></td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
						<td><input type="text" $disability $disabilityx name="txtAlamat2F" id="txtAlamat2F" value="$!txtAlamat2F" size="45" maxlength="80"   ></td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
						<td><input type="text" $disability $disabilityx name="txtAlamat3F" id="txtAlamat3F" value="$!txtAlamat3F" size="45" maxlength="80"   ></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Poskod</td>
						<td>:</td>
						<td><input type="text" $disability $disabilityx name="txtPoskodF" id="txtPoskodF" value="$!txtPoskodF" size="5" maxlength="5" onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value)" ></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Negeri</td>
						<td>:</td>
						<td>$!selectNegeriBorangF</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Bandar</td>
						<td>:</td>
						<td>$!selectBandarBorangF</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Tempoh (Hari)</td>
						<td>:</td>
						<td><input type="text" $disability $disabilityx name="txtTempoh" id="txtTempoh" value="$!txtTempoh" size="5" maxlength="3"   ></td>
					</tr>
					<!--  <tr>
						<td>&nbsp;</td>
						<td>Tarikh Terima</td>
						<td>:</td>
						<td><input name="txdTarikhTerima" $disability $disabilityx id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
			            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr> -->
					<tr>
						<td>&nbsp;</td>
						<td valign="top">Keterangan</td>
						<td valign="top">:</td>
						<td valign="top"><textarea $disability $disabilityx name="txtKeterangan" id="txtKeterangan" cols="45%" rows="5" onKeyUp="textCounter(this.form.txtKeterangan,this.form.remLen3,1500);" onKeyDown="textCounter(this.form.txtKeterangan,this.form.remLen3,1500);" >$!txtKeterangan</textarea></td>
					</tr>
					#if($isEdit=="yes")
					<tr>
			        	<td colspan="3">&nbsp;</td>
			            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="1500"></td>
			        </tr> 
			        #end
			        
				</table>
				#end
				
			</fieldset>
    
    		<table width="100%" border="0">
				<tr align="center">
					<td>
					#if($mode=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPenerima('$!id_hakmilik','$!id_hakmilikpb','$!id_borangf','$!mode')">
					#end
						
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPenerima('$!id_borangf')">
						<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport2')" />
						<input type="button" name="cmdHapus" value="Hapus" onClick="hapusPenerima('$!id_borangf')" >
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPenerima('$!id_hakmilik','$!id_hakmilikpb','$!id_borangf','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniPenerima('$!id_borangf')">
						#end
					#end
						
						<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:viewListHM('$!id_permohonan');">
					</td>
				</tr>
			</table>
    
    		<br/>
    		
    		<fieldset>
    		
    			#if($mode=="view")
				<table width="100%" border="0">   
	              	<tr>
	                 	<td width="30%" align="left"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahPenerima('$!id_hakmilik');"></td>
	              	</tr>
    			</table>
				#end
				
				#if($saiz_listBorangF > 5)
		                <div style="width:100%;height:100;overflow:auto"> 
		        #end
	
				<table width="100%" border="0"> 
  
        			<tr class="table_header">
        				<td align="center" width="4%"><b>No</b></td>
        				<td><b>Nama Penerima</b></td>
        				<td><b>No. PB</b></td>
        			</tr>
        		
	        		#if($saiz_listBorangF!=0)
	                    #foreach($list in $listBorangF)
	                    #set( $i = $velocityCount )
	         			#if ( ($i % 2) != 1 )
	              		 	#set( $row = "row2" )
	         			#else
	               			#set( $row = "row1" )
	         			#end
                    
	               <tr>
	                   <td class="$row" align="center">$!list.bil</td>
	                   <td class="$row"><a href="javascript:viewPenerima('$!list.id_borangf')"><font color="blue">$!list.nama_pb</font></a></td>
	                   <td class="$row">$!list.no_pb</td>
	                   <!-- <td class="$row">$!list.tarikh_terima</td> -->
	               <tr>
                    	#end
               		#else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               		#end
               		
				</table>
			
				#if($saiz_listBorangF > 5)
		        	</div>
		        #end	
		
			</fieldset>

   		</div>
		<!-- END TAB 2 (BORANG F)-->
	
	 	</div>
	 	
	</div>

	

</center>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
		<td><a href="#" onClick="javascript:cetakBorangE('$!id_permohonan','$!id_hakmilik','$!tarikh_cetak')"><font color="blue">Borang E</font></a></td>
	  </tr>
	  <tr>
    	 <td><a href="#" onClick="javascript:cetakSiasatanJPPH('$!id_permohonan','$!id_hakmilik')"><font color="blue">Surat Kepada JPPH Jemputan Hadir Siasatan</font></a></td>
      </tr>	
	  <tr>
    	 <td><a href="#" onClick="javascript:cetakSiasatanAP('$!id_permohonan','$!id_hakmilik','projek')"><font color="blue">Surat Kepada AP Supaya Hadir Untuk Siasatan (1 Projek)</font></a></td>
      </tr>
      <tr>
    	 <td><a href="#" onClick="javascript:cetakSiasatanAP('$!id_permohonan','$!id_hakmilik','lot')"><font color="blue">Surat Kepada AP Supaya Hadir Untuk Siasatan (Mengikut lot)</font></a></td>
      </tr>
      
      #if($userIdNeg=='10')
      <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang E dan F</font></a></td>
	  </tr>
      #else
      <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang E dan F - I</font></a></td>
	  </tr>
	  <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaianRamai('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang E dan F - II</font></a></td>
	  </tr>
      #end
      
      
    </table>
</fieldset>	

<fieldset id="tableReport2" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>  
      	<td><a href="#" onClick="javascript:cetakBorangF('$!id_permohonan','$!id_hakmilik')"><font color="blue">Borang F</font></a></td>
      </tr>     
      
      #if($userIdNeg=='10')
      <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang E dan F</font></a></td>
	  </tr>
      #else
      <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang E dan F - I</font></a></td>
	  </tr>
	  <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaianRamai('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang E dan F - II</font></a></td>
	  </tr>
      #end
      
    </table>
</fieldset>	

#if($showAlertPaging=="yes")
<script>
alert("Sila Teruskan Dengan Mengisi Borang F Dan Klik 'Paging' No.13 Untuk Notis Awam");
</script>
#end 


<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_hakmilikpb" value="$!id_hakmilikpb">
<input type="hidden" name="id_borangf" value="$!id_borangf">
<input type="hidden" name="id_borange" value="$!id_borange">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<script>
function cetakSiasatanJPPH(idpermohonan,idhakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=SiasatanJPPH&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
  
}
function getTarikhSiasatan() {

	var TB  = document.getElementById("txdTarikhTampal").value;

	var a = TB.substring(0,2);
	var b = TB.substring(3,5);
   	var c = TB.substring(6,10);
   	
	var dt1   = parseInt(TB.substring(0,2),10)+24;
   	var mon1  = parseInt(TB.substring(3,5),10)-1;
   	var yr1   = parseInt(TB.substring(6,10),10);

	var myDate=new Date(yr1, mon1, dt1);

	var day = myDate.getDate();
	var month = myDate.getMonth()+1;
	var year = myDate.getFullYear();

	var tarikhSiasatan = "";
	if(month>=10){
		if(day>=10){
			tarikhSiasatan = day + "/" + month + "/" + year;	
		}else{
			tarikhSiasatan = "0"+ day + "/" + month + "/" + year;	
		}
			
	}else{
		if(day>=10){
			tarikhSiasatan = day + "/0" + month + "/" + year;	
		}else{
			tarikhSiasatan = "0"+ day + "/0" + month + "/" + year;	
		}
	}
	
	if((a!="" && a.length=="2") && (b!="" && b.length=="2") && (c!="" && c.length=="4")){
		document.getElementById("txdTarikhSiasatan").value = tarikhSiasatan ;
	}else{
		document.getElementById("txdTarikhSiasatan").value = "" ;
	}
}
function cetakBuktiPenyampaian(idpermohonan,idhakmilik) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=E&report=BuktiPenyampaian&selectNoFail=yes";
	//var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBuktiPenyampaianRamai(idpermohonan,idhakmilik) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=E&report=BuktiPenyampaianRamai&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function onchangeNegeri(){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "tambahPenerima";
	document.${formName}.command2.value = "onchangeNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function onchangePB(){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "tambahPenerima";
	document.${formName}.command2.value = "onchangePB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function onchangeNegeriUpdate(){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewPenerima";
	document.${formName}.command2.value = "kemaskiniPenerima";
	document.${formName}.command3.value = "onchangeNegeriUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function onchangePBUpdate(){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewPenerima";
	document.${formName}.command2.value = "kemaskiniPenerima";
	document.${formName}.command3.value = "onchangePBUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function batalKemaskiniPenerima(id_borangf){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "viewPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function kemaskiniPenerima(id_borangf){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "viewPenerima";
	document.${formName}.command2.value = "kemaskiniPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function hapusPenerima(id_borangf){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "hapusPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function simpanPenerima(id_hakmilik,idHakmilikPb,id_borangf,mode){
	
	//var dat1 = document.${formName}.txdTarikhTerima
	
	if(document.${formName}.socPB.value == ""){
		alert("Sila pilih \"Nama Penerima\" terlebih dahulu.");
  		document.${formName}.socPB.focus(); 
		return;
	}	
	else
	if (document.${formName}.txtPoskodF.value != "" && document.${formName}.txtPoskodF.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.txtPoskodF.focus();
	}
/*	else
	if (dat1.value!="" && isDate(dat1.value)==false){
		dat1.focus()
		return;
	}	
*/	else{
	
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_hakmilik.value = id_hakmilik;
		document.${formName}.id_hakmilikpb.value = idHakmilikPb;

		if(mode=="new"){
			document.${formName}.command.value = "tambahPenerima";
			document.${formName}.command2.value = "simpanPenerima";
		}else{
			document.${formName}.id_borangf.value = id_borangf;
			document.${formName}.command.value = "viewPenerima";
			document.${formName}.command2.value = "kemaskiniPenerima";
			document.${formName}.command3.value = "updatePenerima";
		}

		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
		document.${formName}.submit();
	}
}
function viewListBorangF(idHakmilik) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function cetakSiasatanAP(idpermohonan,idhakmilik,flagCetakSiasatan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=SuratSiasatanKpdAP&selectNoFail=yes&flagShowTarikhCetak=yes&flagCetakSiasatan="+flagCetakSiasatan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function viewListHM(id_permohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function onchangeNegeriBorangEUpdate(){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.command2.value = "kemaskiniBorangE";
	document.${formName}.command3.value = "onchangeNegeriBorangEUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function batalKemaskini(id_hakmilik){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function kemaskiniBorangE(id_borange){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.command2.value = "kemaskiniBorangE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function simpanBorangE(id_hakmilik,id_borange,mode){

	var TB  = document.getElementById("txdTarikhTampal").value;
/*
	var a = TB.substring(0,2);
	var b = TB.substring(3,5);
   	var c = TB.substring(6,10);
*/   	
	var dt1   = parseInt(TB.substring(0,2),10)+20;
   	var mon1  = parseInt(TB.substring(3,5),10)-1;
   	var yr1   = parseInt(TB.substring(6,10),10);

   	var dateValSiasatan = new Date(yr1, mon1, dt1);

	var tarikhSiasatan  = document.getElementById("txdTarikhSiasatan").value;

	var dt2   = parseInt(tarikhSiasatan.substring(0,2),10);
   	var mon2  = parseInt(tarikhSiasatan.substring(3,5),10)-1;
   	var yr2   = parseInt(tarikhSiasatan.substring(6,10),10);

   	var dateSiasatan = new Date(yr2, mon2, dt2);
   	
	var dat1 = document.${formName}.txdTarikhBorangF
	var dat2 = document.${formName}.txdTarikhBorangE
	var dat3 = document.${formName}.txdTarikhSiasatan
	var dat4 = document.${formName}.txdTarikhTampal
	
/*
	if(document.${formName}.txdTarikhBorangE.value == ""){
		alert("Sila masukkan \"Tarikh Borang E\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangE.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhBorangF.value == ""){
		alert("Sila masukkan \"Tarikh Borang F\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangF.focus(); 
		return;
	}	
*/	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if (dat4.value!="" && isDate(dat4.value)==false)
	{
		dat4.focus()
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
	else if((dat4.value!="" && dat3.value!="") && (dateSiasatan < dateValSiasatan)){
		alert("Sila pastikan \"Tarikh Siasatan\" tidak kurang 21 hari dari \"Tarikh Akhir Tampal\"");
	 	document.${formName}.txdTarikhSiasatan.focus();
	 	return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_hakmilik.value = id_hakmilik;

		if(mode=="new"){
			document.${formName}.command.value = "viewListBorangF";
			document.${formName}.command2.value = "simpanBorangE";
		}else{
			document.${formName}.id_borange.value = id_borange;
			document.${formName}.command.value = "viewListBorangF";
			document.${formName}.command2.value = "kemaskiniBorangE";
			document.${formName}.command3.value = "updateBorangE";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
		document.${formName}.submit();
	}
	
}
function onchangeNegeriBorangE(){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.command2.value = "onchangeNegeriBorangE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function cetakBorangE(idpermohonan,idhakmilik,tarikh_cetak) {

/*	if(tarikh_cetak==""){
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.command.value = "simpanTarikhCetak";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
		document.${formName}.submit();
	}*/

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=BorangE&selectNoFail=yes";
    //var url = "../servlet/ekptg.report.ppt.BorangE?id_hakmilik="+idhakmilik+"&namaPegawai="+namaPengarah;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangF(idpermohonan,idhakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=BorangF&selectNoFail=yes";
	//var url = "../servlet/ekptg.report.ppt.BorangF?id_hakmilik='"+idhakmilik+"'&namaPegawai="+namaPengarah;
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
function viewPenerima(id_borangf){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "viewPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function tambahPenerima(idHakmilik){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
</script>


<script>
function checkDigit() {
	if(document.getElementById("txtMasaSiasatan").value != "" && document.getElementById("txtMasaSiasatan").value.length < 4){
		alert("Sila Pastikan Format Masa Siasatan Adalah \"HHMM\"");
		document.${formName}.txtMasaSiasatan.focus(); 
		return;	
	}
}
function validateJenisWaktu(elmnt,content) {

	var length = parseInt(document.getElementById("txtMasaSiasatan").value.length);
	
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
</script>