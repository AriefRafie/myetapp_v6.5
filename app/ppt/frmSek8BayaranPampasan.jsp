#parse("app/ppt/Sek8PagingPampasan.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

	#foreach($data in $dataBorangG)
		#set($txdTarikhBorangH=$data.tarikh_borangh)
		#set($jumlahAward=$data.jumlah_award)
	#end
		<input name="txdTarikhBorangH" type="hidden" id="txdTarikhBorangH" value="$txdTarikhBorangH"/>
		<input name="jumlahAward" type="hidden" id="jumlahAward" value="$jumlahAward"/>
		<input name="Date5" type="hidden" id="Date5" value="$Date5"/>
		<input name="Date8" type="hidden" id="Date8" value="$Date8"/>


<fieldset id="top">
<center>

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>

	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
	
	<div id="TabbedPanels1" class="TabbedPanels">		
  		<ul class="TabbedPanelsTabGroup">
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);maklumatSuratAgensi('$!id_hakmilik','$!id_siasatan')" tabindex="1">Pembayaran Melalui Cek</li>
			<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);viewListEFT()" tabindex="1">Melalui EFT</li>
    	
    	</ul>
    	
    	
    	<div class="TabbedPanelsContentGroup">
    	
    	
    		<!-- START TAB 1 -->
  			<div class="TabbedPanelsContent">
  				
  				<br/>
  				<fieldset>
	
				#if($mode=="new")
				<table width="100%" border="0">
					<tr>
						<td width="1%"><font color="red">*</font></td>
						<td width="24%">No. Ruj Surat Agensi</td>
						<td width="1%">:</td>
						<td width="74%"><input type="text" name="txtNoRujSurat" id="txtNoRujSurat" value="" size="40" maxlength="50"   ></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
			            <td>Tarikh Surat Agensi</td>
			            <td>:</td>
			            <td><input name="txdTarikhSurat" id="txdTarikhSurat" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
			            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">&nbsp;$!frmtdate</td>
					</tr>	
					<tr>
						<td>&nbsp;</td>
			            <td>Tarikh Kuatkuasa Caj Lewat</td>
			            <td>:</td>
			            <td><input name="txdTarikhLewat" id="txdTarikhLewat" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="checkDate(); check_date(this);" >
			            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLewat',false,'dmy');">&nbsp;$!frmtdate</td>
					</tr>
					<tr>
  						<td>&nbsp;</td>
  						<td>Caj Bayaran Lewat</td>
  						<td>:</td>
  						<td>&nbsp;RM&nbsp;<input type="text" name="txtCekLewat" id="txtCekLewat" value="$!Utils.RemoveSymbol($!txtCekLewat)" size="10" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtCekLewat')" ></td>
  					</tr>	
  	  				
					<tr>
						<td>&nbsp;</td>
						<td>Tarikh Terima</td>
						<td>:</td>
						<td><input name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
			            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate</td>
					</tr>
					<tr>
						<td><font color="red">*</font></td>
						<td>Tarikh Sedia Cek / Tarikh Bayaran</td>
						<td>:</td>
						<td><input name="txdTarikhSedia" id="txdTarikhSedia" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
			            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSedia',false,'dmy');">&nbsp;$!frmtdate</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Nama Bank</td>
						<td>:</td>
						<td><input type="text" name="txtNamaBank" id="txtNamaBank" value="" size="40" maxlength="50"   ></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Tarikh Ambil Cek</td>
						<td>:</td>
						<td><input name="txdTarikhAmbil" id="txdTarikhAmbil" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
			            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhAmbil',false,'dmy');">&nbsp;$!frmtdate</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Tempat Ambil</td>
						<td>:</td>
						<td><input type="text" name="txtTempatAmbil" id="txtTempatAmbil" value="" size="40" maxlength="100"   ></td>
					</tr>
					<tr><td colspan="4">&nbsp;</td></tr>
				</table>
				#end
	
	
	
				#if($mode=="view")
				
				#foreach($data in $dataSuratAgensi)
			    	#set($txtNoRujSurat=$data.no_rujukan_surat)
			    	#set($txdTarikhSurat=$data.tarikh_surat)
			    	#set($txdTarikhTerima=$data.tarikh_terima)
			    	#set($txtNamaBank=$data.nama_bank)
			    	#set($txdTarikhAmbil=$data.tarikh_ambil_cek)
			    	#set($txtTempatAmbil=$data.tempat_ambil)
			    	#set($txdTarikhSedia=$data.tarikh_bayaran)
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
						<td width="1%"><font color="red">$!M</font></td>
						<td width="24%">No. Ruj Surat Agensi</td>
						<td width="1%">:</td>
						<td width="74%"><input $disability $disabilityx type="text" name="txtNoRujSurat" id="txtNoRujSurat" value="$!txtNoRujSurat" size="40" maxlength="50"   ></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
			            <td>Tarikh Surat Agensi</td>
			            <td>:</td>
			            <td><input $disability $disabilityx name="txdTarikhSurat" id="txdTarikhSurat" size="11" type="text" value="$!txdTarikhSurat" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
			            #if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>	
						<tr>
						<td>&nbsp;</td>
			            <td>Tarikh Kuatkuasa Caj Lewat</td>
			            <td>:</td>
			            <td><input $disability $disabilityx name="txdTarikhLewat" id="txdTarikhLewat" size="11" type="text" value="$!txdTarikhLewat" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
			            #if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLewat',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>	
					<tr>
  						<td>&nbsp;</td>
  						<td>Caj Bayaran Lewat</td>
  						<td>:</td>
                        <td>
  						#if($isEdit=="no")
  						&nbsp;RM&nbsp;<input $disability $disabilityx type="text" name="txtCekLewat" id="txtCekLewat" value="$!Utils.format2Decimal($txtCekLewat)" size="10" maxlength="8" style="text-align:right">
  						#else
  						&nbsp;RM&nbsp;<input $disability $disabilityx type="text" name="txtCekLewat" id="txtCekLewat" value="$!txtCekLewat" size="10" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtCekLewat')" >
  						#end
                        </td>
  					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Tarikh Terima</td>
						<td>:</td>
						<td><input $disability $disabilityx name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
			            #if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
					<tr>
						<td><font color="red">$!M</font></td>
						<td>Tarikh Sedia Cek / Tarikh Bayaran</td>
						<td>:</td>
						<td><input $disability $disabilityx name="txdTarikhSedia" id="txdTarikhSedia" size="11" type="text" value="$!txdTarikhSedia" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
			            #if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSedia',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Nama Bank</td>
						<td>:</td>
						<td><input $disability $disabilityx type="text" name="txtNamaBank" id="txtNamaBank" value="$!txtNamaBank" size="40" maxlength="50"   ></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Tarikh Ambil Cek</td>
						<td>:</td>
						<td><input $disability $disabilityx name="txdTarikhAmbil" id="txdTarikhAmbil" size="11" type="text" value="$!txdTarikhAmbil" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
			            #if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhAmbil',false,'dmy');">&nbsp;$!frmtdate#end</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Tempat Ambil</td>
						<td>:</td>
						<td><input $disability $disabilityx type="text" name="txtTempatAmbil" id="txtTempatAmbil" value="$!txtTempatAmbil" size="40" maxlength="100"   ></td>
					</tr>
					<tr><td colspan="4">&nbsp;</td></tr>
				</table>
				#end
	
	
				<table width="90%" border="0">
					<tr align="center">
						<td>
						
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanMaklumatSuratAgensi('$!id_hakmilik','$!id_terimabayaran','$!mode')">
						#end
							
						#if($mode=="view")
							#if($isEdit=="no")
							<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniMaklumatSuratAgensi('$!id_terimabayaran')">
							<input type="button" name="cmdHapus" value="Hapus" onClick="hapusMaklumatSuratAgensi('$!id_terimabayaran')" >
							#else
							<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanMaklumatSuratAgensi('$!id_hakmilik','$!id_terimabayaran','$!mode')">
							<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_terimabayaran')">
							#end
						#end
						
						<input type="button" name="cmdKembali" value ="Kembali Senarai Hakmilik" onClick="javascript:viewlistHM('$!id_permohonan')">
							
						</td>
					</tr>
				</table>
	
				<fieldset>
				<legend><strong>Senarai Surat Agensi</strong></legend>
				
					#if($mode=="view")
			    	<table width="100%" border="0">
			    		<tr>
			    			<td colspan="2"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:maklumatSuratAgensi('$!id_hakmilik','$!id_siasatan')"></td>
			    		</tr>
			    	</table>
			  		#end
			  		
			    	#if($list_surat > 5)
			        <div style="width:100%;height:111;overflow:auto"> 
			        #end
			            	
			    	<table width="100%" border="0">	
			    		<tr class="table_header">
			    			<td width="4%" align="center"><b>Bil</b></td>
			    			<td>&nbsp;<b>No. Ruj Surat Agensi</b></td>
			    			<td>&nbsp;<b>Tarikh Surat Agensi</b></td>
			    			<td>&nbsp;<b>Tarikh Terima</b></td>
			    			<td>&nbsp;<b>Tarikh Ambil Cek</b></td>
			    		</tr>
			    				
			    		#if($list_surat!=0)
			           	 #foreach($list in $listSuratAgensi)
			              	#set( $i = $velocityCount )
			         			#if ( ($i % 2) != 1 )
			              			#set( $row = "row2" )
			         			#else
			               			#set( $row = "row1" )
			         			#end
			                    
			       		<tr>
			                <td class="$row" align="center">$!list.bil</td>
			                <td class="$row">&nbsp;<a href="javascript:viewMaklumatSuratAgensi('$!list.id_terimabayaran')"><font color="blue">$!list.no_rujukan_surat</font></a></td>
			                <td class="$row">$!list.tarikh_surat</td>
			                <td class="$row">$!list.tarikh_terima</td>
			                <td class="$row">$!list.tarikh_ambil_cek</td>
			            <tr>
			           	 #end
			        	#else
			            <tr>
			                <td colspan="2">Tiada rekod</td>
			            </tr>
			        	#end
			    				
			    	 </table>
			    			
			    	#if($list_surat > 5)
			        </div>
			        #end
					
				</fieldset>
	
			</fieldset>
  			
  			
  			#if($mode=="view")
			<br/>
	
			<fieldset>
			<legend><strong>Senarai Pembayaran Melalui Cek</strong></legend>
			
				<table width="100%" border="0">
		    		<tr>
		    			<td colspan="2"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahPenerimaanCek('$!id_terimabayaran')"></td>
		    		</tr>
		    	</table>
		    	
		    	#if($saiz_list_penerimaan > 5)
		        <div style="width:100%;height:111;overflow:auto"> 
		        #end
		            	
		    	<table width="100%" border="0">	
		    			<tr class="table_header">
		    				<td width="4%" align="center"><b>Bil</b></td>
		    				<td>&nbsp;<b>Nama PB</b></td>
		    				<td>&nbsp;<b>No. PB</b></td>
		    				<td>&nbsp;<b>Bahagian Syer</b></td>   				
		    				<td>&nbsp;<b>Jumlah Pampasan</b></td>
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
		                    <td class="$row">&nbsp;$!listPN.no_pb</td>
		                   	<td class="$row">&nbsp;$!listPN.syer_atas / $!listPN.syer_bawah</td>
		                   	<td class="$row">&nbsp;RM&nbsp;$!Utils.format2Decimal($listPN.bayar_award)</td>
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
			#end
  			
  			</div>
  			<!-- END TAB 1 -->
  			
  			
  			
    		<!-- START TAB 2 -->
  			<div class="TabbedPanelsContent">
  			
  				<br/>
  	  		
  	  		#if($mode=="new")
  	  		<fieldset>
  	  			<table width="100%" border="0">
  	  				<tr>
  	  					<td width="1%">&nbsp;</td>
  	  					<td width="25%">Nama Kementerian</td>
  	  					<td width="1%">:</td>
  	  					<td width="73%"><font color="blue">$!kementerianEFT</font></td>
  	  				</tr>
  	  				<tr>
  	  					<td><font color="red">*</font></td>
  	  					<td>Nama PB</td>
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
  						<td><input readonly class="disabled" type="text" name="txtNoPB" id="txtNoPB" value="$!lblNoPB" size="20" maxlength="20"   ></td>
  					</tr>
  	  			</table>
  	  		</fieldset>
  	  	
  	  		<fieldset>
  	  		
  	  			
  	  			<table width="100%" border="0">
  	  				<tr>
  	  					<td width="1%">&nbsp;</td>
  	  					<td width="25%">No. Rujukan Surat</td>
  	  					<td width="1%">:</td>
  	  					<td width="73%"><input type="text" name="txtNoRujSuratEft" id="txtNoRujSuratEft" value="$!txtNoRujSuratEft" size="40" maxlength="50"   ></td>
  	  				</tr>
  	  				<tr>
  	  					<td><font color="red">*</font></td>
  	  					<td>No. EFT</td>
  	  					<td>:</td>
  	  					<td><input type="text" name="txtNoEft" id="txtNoEft" value="$!txtNoEft" size="40" maxlength="100"   ></td>
  	  				</tr>
                    
                    <tr>
  						<td>&nbsp;</td>
  						<td>Bil. Hari Lewat</td>
  						<td>:</td>
  						<td>
                         <input type="text" name="txtBilLewat" id="txtBilLewat" value="$!txtBilLewat" size="3" maxlength="4" onkeyup="validateNumber(this,this.value);" ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Caj Bayaran Lewat</td>
  						<td>:</td>
  						<td>&nbsp;RM&nbsp;<input type="text" name="txtDendaLewat" id="txtDendaLewat" value="$!Utils.RemoveSymbol($!txtDendaLewat)" size="10" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtDendaLewat')" ></td>
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
  	  					<td>Amaun</td>
  	  					<td>:</td>
  	  					<td>&nbsp;RM&nbsp;<input type="text" name="txtAmaun" id="txtAmaun" value="$!txtAmaun" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtAmaun')" ></td>
  	  				</tr>
  	  				<tr>
  	  					<td>&nbsp;</td>
  	  					<td>Tarikh Terima Surat</td>
  	  					<td>:</td>
  	  					<td><input name="txdTarikhTerimaSurat" id="txdTarikhTerimaSurat" size="11" type="text" value="$!txdTarikhTerimaSurat" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
          				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaSurat',false,'dmy');">&nbsp;$!frmtdate</td>
  	  				</tr>
  	  				<tr>
  	  					<td><font color="red">*</font></td>
  	  					<td>Tarikh EFT</td>
  	  					<td>:</td>
  	  					<td><input name="txdTarikhSuratEFT" id="txdTarikhSuratEFT" size="11" type="text" value="$!txdTarikhSurat" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
          				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratEFT',false,'dmy');">&nbsp;$!frmtdate</td>
  	  				</tr>
  	  				<tr>
  	  					<td>&nbsp;</td>
  	  					<td>No. Baucer</td>
  	  					<td>:</td>
  	  					<td><input type="text" name="txtNoBaucer" id="txtNoBaucer" value="$!txtNoBaucer" size="20" maxlength="20"   ></td>
  	  				</tr>
  	  				<tr>
  	  					<td>&nbsp;</td>
  	  					<td valign="top">Perihal</td>
  	  					<td valign="top">:</td>
  	  					<td><textarea name="txtPerihal" id="txtPerihal" cols="37%" rows="3"  onKeyUp="textCounter(this.form.txtPerihal,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtPerihal,this.form.remLen1,400);" >$!txtPerihal</textarea></td>
  	  				</tr>
  	  				<tr>
        				<td colspan="3">&nbsp;</td>
             			<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="400"></td>
           			</tr> 
  	  			</table>
  	  			#end
  	  			
  	  			
  	  			#if($mode=="view")
  	  			
  	  			#if($onchangeEFT=="no")
  	  			#foreach($dataPN in $dataEFT)
  					#set($lblNamaPB=$dataPN.nama_pb)
  					#set($lblNoPB=$dataPN.no_pb)			
  					#set($txtNoRujSuratEft=$dataPN.no_rujukan_surateft)
  					#set($txtNoEft=$dataPN.no_eft)
  					#set($txtAmaun=$dataPN.amaun_bayaran)
  					#set($txdTarikhTerimaSurat=$dataPN.tarikh_terima_eft)
  					#set($txdTarikhSuratEFT=$dataPN.tarikh_surat_eft)
  					#set($txtNoBaucer=$dataPN.no_baucer)
  					#set($txtPerihal=$dataPN.perihal)
                    #set($txtBilLewat=$dataPN.bil_hari_lewat)
  					#set($sorJenisAward=$dataPN.jenis_award)
                    #set($txtDendaLewat=$dataPN.denda_lewat)
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
				
				<fieldset>
  	  			<table width="100%" border="0">
  	  				<tr>
  	  					<td width="1%">&nbsp;</td>
  	  					<td width="25%">Nama Kementerian</td>
  	  					<td width="1%">:</td>
  	  					<td width="73%"><font color="blue">$!kementerianEFT</font></td>
  	  				</tr>
  	  				<tr>
  	  					<td><font color="red">$M</font></td>
  	  					<td>Nama PB</td>
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
  						<td><input readonly class="disabled" type="text" name="txtNoPB" id="txtNoPB" value="$!lblNoPB" size="20" maxlength="20"   ></td>
  					</tr>
  	  			</table>
  	  			</fieldset>
  	  		
  	  			<fieldset>
  	  			
  	  			<table width="100%" border="0">
  	  				<tr>
  	  					<td width="1%">&nbsp;</td>
  	  					<td width="25%">No. Rujukan Surat</td>
  	  					<td width="1%">:</td>
  	  					<td width="73%"><input $disability $disabilityx type="text" name="txtNoRujSuratEft" id="txtNoRujSuratEft" value="$!txtNoRujSuratEft" size="40" maxlength="50"   ></td>
  	  				</tr>
  	  				<tr>
  	  					<td><font color="red">*</font></td>
  	  					<td>No. EFT</td>
  	  					<td>:</td>
  	  					<td><input $disability $disabilityx type="text" name="txtNoEft" id="txtNoEft" value="$!txtNoEft" size="40" maxlength="100"   ></td>
  	  				</tr>
                    <tr>
  						<td>&nbsp;</td>
  						<td>Bil. Hari Lewat</td>
  						<td>:</td>
  						<td>
                        
                        <input $disability $disabilityx type="text" name="txtBilLewat" id="txtBilLewat" value="$!txtBilLewat" size="3" maxlength="4" onkeyup="validateNumber(this,this.value);" ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Caj Bayaran Lewat</td>
  						<td>:</td>
                        <td>
  						#if($isEdit=="no")
  						&nbsp;RM&nbsp;<input $disability $disabilityx type="text" name="txtDendaLewat" id="txtDendaLewat" value="$!Utils.format2Decimal($txtDendaLewat)" size="10" maxlength="8" style="text-align:right">
  						#else
  						&nbsp;RM&nbsp;<input $disability $disabilityx type="text" name="txtDendaLewat" id="txtDendaLewat" value="$!txtDendaLewat" size="10" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtDendaLewat')" >
  						#end
                        </td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Jenis Pampasan</td>
  						<td>:</td>
  						<td><select $disability1 $disabilityx name="sorJenisAward" style="width:220px" >
      		
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
  	  					<td>Amaun</td>
  	  					<td>:</td>
  	  					#if($isEdit=="no")
  	  					<td>&nbsp;RM&nbsp;<input $disability $disabilityx type="text" name="txtAmaun" id="txtAmaun" value="$Utils.format2Decimal($!txtAmaun)" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtAmaun')" ></td>
  	  					#else
  	  					<td>&nbsp;RM&nbsp;<input $disability $disabilityx type="text" name="txtAmaun" id="txtAmaun" value="$!txtAmaun" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtAmaun')" ></td>
  	  					#end
  	  				</tr>
  	  				<tr>
  	  					<td>&nbsp;</td>
  	  					<td>Tarikh Terima Surat</td>
  	  					<td>:</td>
  	  					<td><input $disability $disabilityx name="txdTarikhTerimaSurat" id="txdTarikhTerimaSurat" size="11" type="text" value="$!txdTarikhTerimaSurat" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
          				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaSurat',false,'dmy');">&nbsp;$!frmtdate#end</td>
  	  				</tr>
  	  				<tr>
  	  					<td><font color="red">*</font></td>
  	  					<td>Tarikh EFT</td>
  	  					<td>:</td>
  	  					<td><input $disability $disabilityx name="txdTarikhSuratEFT" id="txdTarikhSuratEFT" size="11" type="text" value="$!txdTarikhSuratEFT" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
          				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratEFT',false,'dmy');">&nbsp;$!frmtdate#end</td>
  	  				</tr>
  	  				<tr>
  	  					<td>&nbsp;</td>
  	  					<td>No. Baucer</td>
  	  					<td>:</td>
  	  					<td><input $disability $disabilityx type="text" name="txtNoBaucer" id="txtNoBaucer" value="$!txtNoBaucer" size="20" maxlength="20"   ></td>
  	  				</tr>
  	  				<tr>
  	  					<td>&nbsp;</td>
  	  					<td valign="top">Perihal</td>
  	  					<td valign="top">:</td>
  	  					<td><textarea $disability $disabilityx name="txtPerihal" id="txtPerihal" cols="37%" rows="3"  onKeyUp="textCounter(this.form.txtPerihal,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtPerihal,this.form.remLen1,400);" >$!txtPerihal</textarea></td>
  	  				</tr>
  	  				#if($isEdit=="yes")
  	  				<tr>
        				<td colspan="3">&nbsp;</td>
             			<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="400"></td>
           			</tr> 
           			#end
  	  			</table>
  	  			#end
  	  			
  	  		</fieldset>
  	  		
  	  			<table width="100%" border="0">
					<tr align="center">
						<td>
			
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPBEFT('$!id_hakmilikpb','$!id_bayaran','$!mode')">
						#end
				
						#if($mode=="view")
							#if($isEdit=="no")
							<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPBEFT('$!id_bayaran')">
							<input type="button" name="cmdHapus" value="Hapus" onClick="hapusEFT('$!id_bayaran')" >
							#else
							<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPBEFT('$!id_hakmilikpb','$!id_bayaran','$!mode')">
							<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniEFT('$!id_bayaran')">
							#end
						#end
				
						<input type="button" name="cmdKembali" value ="Kembali Senarai Hakmilik" onClick="javascript:viewlistHM('$!id_permohonan')">
						
						</td>
					</tr>
				</table>

  	  			<fieldset>
				<legend><strong>Senarai Pembayaran Melalui EFT</strong></legend>
			
    			#if($saiz_list_eft > 5)
        		<div style="width:100%;height:111;overflow:auto"> 
        		#end
            	
    			<table width="100%" border="0">	
    				<tr class="table_header">
    					<td width="4%" align="center"><b>Bil</b></td>
    					<td>&nbsp;<b>Nama PB</b></td>
    					<td>&nbsp;<b>No. PB</b></td>
    					<td>&nbsp;<b>No. Rujukan Surat EFT</b></td>   				
    				</tr>
    				
    			#if($saiz_list_eft!=0)
           	 	#foreach($listPN in $listEFT)
              		#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              				#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    
               		<tr>
                   		<td class="$row" align="center">$!listPN.bil</td>
                   		<td class="$row">&nbsp;<a href="javascript:viewDataEFT('$!listPN.id_bayaran','$!listPN.id_hakmilikpb')"><font color="blue">$!listPN.nama_pb</font></a></td>
                   		<td class="$row">$!listPN.no_pb</td>
                   		<td class="$row">$!listPN.no_rujukan_surateft</td>
              		<tr>
           		#end
        		#else
                	<tr>
                	   	<td colspan="2">Tiada rekod</td>
                	</tr>
        		#end
    				
    	 		</table>
    			
    			#if($saiz_list_eft > 5)
        		</div>
        		#end
		
				</fieldset>
  			
  			</div>
  			<!-- END TAB 2 -->
  			
  			
  			
  			
  			
  			
  		
  		</div>
  	</div>
  			
  		

</center>
</fieldset>

#if($showAlertPaging=="yes")
<script>
alert("Sila Kembali Pada Skrin Pemilihan Hakmilik dan Klik 'Paging' No.18 Untuk Teruskan ke Borang K");
</script>
#end 

#if($showAlertAgensi=="yes")
<script>
alert("Sila Klik Pada Senarai Surat Agensi Yang Baru Sahaja Disimpan, dan Klik Butang Tambah Untuk Proses Penyerahan Cek");
</script>
#end 

#if($showAlertPagingSegera=="yes")
<script>
alert("Sila Klik 'Paging' No.19/No.20/No.21 Untuk Proses Seterusnya Jika Belum Dilengkapkan Lagi");
</script>
#end 

<input type="hidden" name="txdTarikhSerah" value="$!txdTarikhSerah" >
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_siasatan" value="$!id_siasatan">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">
<input type="hidden" name="id_terimabayaran" value="$!id_terimabayaran" >
<input type="hidden" name="id_bayaran" value="$!id_bayaran">
<input type="hidden" name="id_hakmilikpb" value="$!id_hakmilikpb">
<input type="hidden" name="cara_bayar">

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
function hapusEFT(id_bayaran) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.tabId.value = "1";
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "hapusEFT";
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function batalKemaskiniEFT(id_bayaran) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.tabId.value = "1";
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewDataEFT";
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function getPBEFTUpdate() {
	document.${formName}.tabId.value = "1";
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewDataEFT";
	document.${formName}.command2.value = "kemaskiniPBEFT";
	document.${formName}.command3.value = "getPBEFTUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kemaskiniPBEFT(id_bayaran) {
	document.${formName}.tabId.value = "1";
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewDataEFT";
	document.${formName}.command2.value = "kemaskiniPBEFT";
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewDataEFT(id_bayaran,id_hakmilikpb) {
	document.${formName}.tabId.value = "1";
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewDataEFT";
	document.${formName}.id_bayaran.value = id_bayaran;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function simpanPBEFT(id_hakmilikpb,id_bayaran,mode) {

	var dat1=document.${formName}.txdTarikhTerimaSurat
	var dat2=document.${formName}.txdTarikhSuratEFT
	
	if(document.${formName}.socPB.value == ""){
	   	alert("Sila pilih \"Pihak Berkepentingan\" terlebih dahulu.");
		document.${formName}.socPB.focus();
		return;
	}
	else if(document.${formName}.txdTarikhSuratEFT.value == ""){
	   	alert("Sila pilih \"Tarikh EFT\" terlebih dahulu.");
		document.${formName}.txdTarikhSuratEFT.focus();
		return;
	}
	else if(document.${formName}.txtNoEft.value == ""){
	   	alert("Sila pilih \"No. EFT\" terlebih dahulu.");
		document.${formName}.txtNoEft.focus();
		return;
	}
/*	else if(document.${formName}.txtNoRujSuratEft.value == ""){
	   	alert("Sila masukkan \"No. Ruj Surat EFT\" terlebih dahulu.");
		document.${formName}.txtNoRujSuratEft.focus();
		return;
	}
*/	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.tabId.value = "1";
		document.${formName}.pagingPampasan.value = "3";
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_hakmilikpb.value = id_hakmilikpb;

		if(mode=="new"){
			document.${formName}.command.value = "viewListEFT";
			document.${formName}.command2.value = "getPBEFT";
			document.${formName}.command3.value = "simpanPBEFT";
		}else{
			document.${formName}.id_bayaran.value = id_bayaran;
			document.${formName}.command.value = "viewDataEFT";
			document.${formName}.command2.value = "kemaskiniPBEFT";
			document.${formName}.command3.value = "updatePBEFT";
		}
		
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
		document.${formName}.submit();
	}
}
function getPBEFT() {
	document.${formName}.tabId.value = "1";
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewListEFT";
	document.${formName}.command2.value = "getPBEFT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}

function viewListEFT() {
	document.${formName}.tabId.value = "1";
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewListEFT";
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
function tambahPenerimaanCek(id_terimabayaran) {
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.command.value = "tambahPenerimaanCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function hapusMaklumatSuratAgensi(id_terimabayaran) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.command.value = "hapusMaklumatSuratAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function batalKemaskini(id_terimabayaran) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.command.value = "viewMaklumatSuratAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kemaskiniMaklumatSuratAgensi(id_terimabayaran) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.command.value = "viewMaklumatSuratAgensi";
	document.${formName}.command2.value = "kemaskiniMaklumatSuratAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function maklumatSuratAgensi(idHakmilik,idSiasatan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.id_siasatan.value = idSiasatan;
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.command.value = "maklumatSuratAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewMaklumatSuratAgensi(id_terimabayaran) {
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_terimabayaran.value = id_terimabayaran;
	document.${formName}.command.value = "viewMaklumatSuratAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function simpanMaklumatSuratAgensi(idHakmilik,id_terimabayaran,mode) {

	var dat1=document.${formName}.txdTarikhSurat
	var dat2=document.${formName}.txdTarikhTerima
	var dat3=document.${formName}.txdTarikhAmbil
	var dat4=document.${formName}.txdTarikhSedia
	
	if(document.${formName}.txtNoRujSurat.value == ""){
	   	alert("Sila masukkan \"No. Ruj Surat Agensi\" terlebih dahulu.");
		document.${formName}.txtNoRujSurat.focus();
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
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
	if(document.${formName}.txdTarikhSedia.value == ""){
	   	alert("Sila masukkan \"Tarikh Sedia Cek / Tarikh Bayaran\" terlebih dahulu.");
		document.${formName}.txdTarikhSedia.focus();
		return;
	}
	else if (dat4.value!="" && isDate(dat4.value)==false)
	{
		dat4.focus()
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.pagingPampasan.value = "3";
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_hakmilik.value = idHakmilik;

		if(mode=="new"){
			document.${formName}.command.value = "maklumatSuratAgensi";
			document.${formName}.command2.value = "simpanMaklumatSuratAgensi";
		}else{
			document.${formName}.id_terimabayaran.value = id_terimabayaran;
			document.${formName}.command.value = "viewMaklumatSuratAgensi";
			document.${formName}.command2.value = "kemaskiniMaklumatSuratAgensi";
			document.${formName}.command3.value = "updateMaklumatSuratAgensi";
		}
			
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
		document.${formName}.submit();
		
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
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
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
      var strValidCharacters = "1234567890,.";
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

function checkDate() {
	//tarikh pendudukan mula PPT-41
	var Mula  = document.getElementById("txdTarikhLewat").value;
	var dt1   = parseInt(Mula.substring(0,2),10);
	var mon1  = parseInt(Mula.substring(3,5),10)-1;
	var yr1   = parseInt(Mula.substring(6,10),10);
	var dateLewat = new Date(yr1, mon1, dt1);
	//alert(dateLewat);
	
	var TarikhH  = document.getElementById("txdTarikhBorangH").value;
	var dtH   = parseInt(TarikhH.substring(0,2),10);
	var monH  = parseInt(TarikhH.substring(3,5),10)-1;
	var yrH   = parseInt(TarikhH.substring(6,10),10);
	var dateH = new Date(yrH, monH, dtH);
	//alert(dateH);
	
	var Tarikh5  = document.getElementById("Date5").value;
	var dt5   = parseInt(Tarikh5.substring(0,2),10);
	var mon5  = parseInt(Tarikh5.substring(3,5),10)-1;
	var yr5   = parseInt(Tarikh5.substring(6,10),10);
	var date5 = new Date(yr5, mon5, dt5);
	//alert(date5);
	
	var Tarikh8  = document.getElementById("Date8").value;
	var dt8   = parseInt(Tarikh8.substring(0,2),10);
	var mon8  = parseInt(Tarikh8.substring(3,5),10)-1;
	var yr8   = parseInt(Tarikh8.substring(6,10),10);
	var date8 = new Date(yr8, mon8, dt8);
	
	// get total seconds between two dates
	var res = Math.abs(dateLewat - dateH) / 1000;
	var days = Math.floor(res / 86400);
	//alert(days);
	
	var res2 = Math.abs(date8-date5) / 1000;
	var days2 = Math.floor(res2 / 86400);
	//alert(days2);
	
	var res3 = Math.abs(dateLewat-date8) / 1000;
	var days3 = Math.floor(res3 / 86400);
	//alert(days3);
	
	var award = document.getElementById("jumlahAward").value;
	
	if(dateLewat>date5){
		//alert(5);
		var c = Math.abs(5*award)/100;		
		//alert(c);
		
	}
	if(dateLewat<date8){
		//alert(8);
		var c = Math.abs(8*award)/100;		
		//alert(c);
		
	}
	
	
	document.${formName}.txtCekLewat.value = c;
	
}

</script>