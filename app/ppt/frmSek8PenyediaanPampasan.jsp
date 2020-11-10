#parse("app/ppt/Sek8PagingPampasan.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")




<fieldset id="top">
<center>
<legend><strong>Penyediaan Pampasan</strong></legend>

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>



	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>

	<div id="TabbedPanels1" class="TabbedPanels">
  		<ul class="TabbedPanelsTabGroup">   		
   			<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);viewJumlahAward('$!id_hakmilik')" tabindex="1">Borang G dan H</li>
   			#if($id_borangg!="")<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);penerimaanBorangH('$!id_borangg')" tabindex="1">Penerimaan Borang H</li>#end
  		</ul>
  		
  		<div class="TabbedPanelsContentGroup">
  		
    		<!-- START TAB 1 -->
    		<div class="TabbedPanelsContent">
    
    		<br/>
    		
    		<fieldset>
  
    			#if($list_saiz > 5)
        		<div style="width:100%;height:100;overflow:auto"> 
        		#end
            	
    			<table width="100%" border="0">	
    				<tr class="table_header">
    					<td width="4%" align="center"><b>No</b></td>
    					<td>&nbsp;<b>No. PB</b></td>
    					<td>&nbsp;<b>Nama PB</b></td>
    					<td>&nbsp;<b>Jumlah Pampasan</b></td>
    				</tr>
    				
    			#if($list_saiz!=0)
           	 	#foreach($listP in $listSediaPampasan)
              		#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              				#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    
               		<tr>
                   		<td class="$row" align="center">$!listP.bil</td>
                   		<td class="$row">&nbsp;$!listP.no_pb</td>
                   		<td class="$row">&nbsp;$!listP.nama_pb</td>
                   		<td class="$row">&nbsp;RM&nbsp;$!Utils.format2Decimal($listP.bayar)</td>
                   		 

              		<tr>
           		#end
        		#else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
        		#end
    				
    			</table>
    			
    			#if($list_saiz > 5)
        		</div>
        		#end
    		</fieldset>
    			
    		#if($mode=="new")	
    		<fieldset>
    			<table width="100%" border="0">
    				<tr>
    					<td width="1%">&nbsp;</td>
    					<td width="30%">Jumlah Award Keseluruhan (RM)</td>
    					<td width="1%">:</td>
    					<td width="68%"><input readonly class="disabled" type="text" maxlength="12" size="15" style="text-align:right" name="txtJumlahAward" id="txtJumlahAward" value="$!total_pampasan" onkeyup="validateNumber(this,this.value)" onblur="validateModal(this,this.value,'$!total_pampasan')" /></td>
    				</tr>
    				<tr>
    					<td><font color="red">*</font></td>
    					<td>Tarikh Borang G</td>
    					<td>:</td>
    					<td><input name="txdTarikhBorangG" id="txdTarikhBorangG" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangG',false,'dmy');">&nbsp;$!frmtdate</td>
    				</tr>
    				<tr>
			          <td><span class="style1"></span></td>
			          <td>Imej Borang G Yang Ditandatangani</td>
			          <td>:</td>
			        <!--  <td><a onclick="javascript:cetakImej($idImejBorangG)" href="#" style="color: #0000FF">[ $!idImejBorangG ]</a> -->
			          <td>  #foreach($imejG in $imejG) 
			             <a onclick="javascript:cetakImej(idImejBorangG)" href="#" style="color: #0000FF">
			              [ $idImejBorangG]
			    		#end </a> 
					 <input id="fileuploadG" name="fileuploadG" type="file" size="40" $readonlyPopup2  class="$inputTextClassPopup" /> </td>
			        </tr>
			        <td>&nbsp;</td>
    				<tr>
    					<td><font color="red">*</font></td>
    					<td>Tarikh Borang H</td>
    					<td>:</td>
    					<td><input name="txdTarikhBorangH" id="txdTarikhBorangH" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangH',false,'dmy');">&nbsp;$!frmtdate</td>
    				</tr>
    				<tr>
			          <td><span class="style1"></span></td>
			          <td>Imej Borang H Yang Ditandatangani</td>
			          <td>:</td>
			         <!--   <td><a onclick="javascript:cetakImej($idImejBorangH)" href="#" style="color: #0000FF">[ $!idImejBorangH ]</a> -->
			           <td>  #foreach($imejH in $imejH) 
			             <a onclick="javascript:cetakImej(idImejBorangH)" href="#" style="color: #0000FF">
			              [ $idImejBorangH]
			    		#end </a> 
			           <input id="fileuploadH" name="fileuploadH" type="file" size="40" $readonlyPopup2  class="$inputTextClassPopup" />  </td>
			        </tr>
			     
			      <tr>
			          <td><span class="style1"></span></td>
			          <td>Senarai Dokumen Sokongan untuk Bayaran Pampasan</td>
			          <td>:</td>
			          <td>  #foreach($docPampasan in $dokumenPampasan) 
			             <a onclick="javascript:cetakImejA($docPampasan.idDokumen)" href="#" style="color: #0000FF">
			              [ $docPampasan.idDokumen: $docPampasan.nama_fail ]
			    		#end </a> 
			             <input id="fileuploadA" name="fileuploadA" type="file" multiple="multiple" size="40" $readonlyPopup2  class="$inputTextClassPopup" /></td>
			        </tr> 
			         <td>&nbsp;</td>
    			</table>
    		</fieldset>
    		#end	
    		
    		#if($mode=="view")	
    		#foreach($dataG in $dataBorangG)
    			#set($txtJumlahAward=$dataG.jumlah_award)
    			#set($txdTarikhBorangG=$dataG.tarikh_borangg)
    			#set($txdTarikhBorangH=$dataG.tarikh_borangh)
    			
    		#end
    		
    		#foreach($docPampasan in $dokumenPampasan)
    			#set($idDokumen=$docPampasan.idDokumen)
    			#set($nama_fail=$docPampasan.nama_fail)
    		#end
    		
    		#foreach($imejBorangG in $imejG)
    			#set($idImejBorangG=$imejBorangG.id_pptimejborangg)
    			#set($jenis_mimeG=$imejBorangG.jenis_mime)
    		#end
    		
    		#foreach($imejBorangH in $imejH)
    			#set($idImejBorangH=$imejBorangH.id_pptimejborangh)
    			#set($jenis_mimeH=$imejBorangH.jenis_mime)
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
	
			#set($realAmaun="")
			#if($isEdit=="no")
				#set($realAmaun=$txtJumlahAward)
			#else
				#set($realAmaun=$total_pampasan)
			#end
			
			
    		<fieldset>
    			<table width="100%" border="0">
    				<tr>
    					<td width="1%">&nbsp;</td>
    					<td width="30%">Jumlah Award Keseluruhan (RM)</td>
    					<td width="1%">:</td>
    					<td width="68%"><input readonly class="disabled" type="text" maxlength="12" size="15" style="text-align:right" name="txtJumlahAward" id="txtJumlahAward" value="$!total_pampasan" onkeyup="validateNumber(this,this.value)" onblur="validateModal(this,this.value,'$!txtJumlahAward')" />
    					</td>
    				</tr>
    				<tr>
    					<td><font color="red">$M</font></td>
    					<td>Tarikh Borang G</td>
    					<td>:</td>
    					<td><input $disability $disabilityx name="txdTarikhBorangG" id="txdTarikhBorangG" size="11" type="text" value="$!txdTarikhBorangG" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangG',false,'dmy');">&nbsp;$!frmtdate#end</td>
    				</tr>
    				
    				<tr>
			          <td><span class="style1"></span></td>
			          <td>Imej Borang G Yang Ditandatangani</td>
			          <td>:</td>
			          <!--  <td><a onclick="javascript:cetakImej($idImejBorangG)" href="#" style="color: #0000FF">[ $!idImejBorangG ]</a> -->
			          <td>  #foreach($imejG in $imejG) 
			             <a onclick="javascript:cetakImej($idImejBorangG)" href="#" style="color: #0000FF">
			              [ $idImejBorangG]
			    		#end </a> 
					#if($isEdit=="yes") <input id="fileuploadG" name="fileuploadG" type="file" size="40" $readonlyPopup2  class="$inputTextClassPopup" /> #end</td>
			        </tr>
			        <td>&nbsp;</td>
    				<tr>
    					<td><font color="red">$M</font></td>
    					<td>Tarikh Borang H</td>
    					<td>:</td>
    					<td><input $disability $disabilityx name="txdTarikhBorangH" id="txdTarikhBorangH" size="11" type="text" value="$!txdTarikhBorangH" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangH',false,'dmy');">&nbsp;$!frmtdate#end</td>
    				</tr>
    				<br>
    				<tr>
			          <td><span class="style1"></span></td>
			          <td>Imej Borang H Yang Ditandatangani</td>
			          <td>:</td>
			         <!--  <td><a onclick="javascript:cetakImej($idImejBorangG)" href="#" style="color: #0000FF">[ $!idImejBorangG ]</a> -->
			          <td>  #foreach($imejH in $imejH) 
			             <a onclick="javascript:cetakImej($idImejBorangH)" href="#" style="color: #0000FF">
			              [ $idImejBorangH]
			    		#end </a> 
			          #if($isEdit=="yes") <input id="fileuploadH" name="fileuploadH" type="file" size="40" $readonlyPopup2  class="$inputTextClassPopup" /> #end </td>
			        </tr>
			      <td>&nbsp;</td>
			        <tr>
			          <td><span class="style1"></span></td>
			          <td>Senarai Dokumen Sokongan untuk Bayaran Pampasan</td>
			          <td>:</td>
			          <td>  #foreach($docPampasan in $dokumenPampasan) 
			             <a onclick="javascript:cetakImejA($docPampasan.idDokumen)" href="#" style="color: #0000FF">
			              [ $docPampasan.idDokumen : $docPampasan.nama_fail ]
			    		#end </a> 
			              #if($isEdit=="yes")  <input id="fileuploadA" name="fileuploadA" type="file" multiple="multiple" size="40" $readonlyPopup2  class="$inputTextClassPopup" />#end</td>
			        </tr> 
			        
    			</table>
    		</fieldset>
    		#end		
    			
    		<table width="100%" border="0">
				<tr align="center">
					<td>
					#if($mode=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanBorangG('$!id_permohonan','$!id_siasatan','$!id_borangg','$!mode')">
					#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="button" id="button" value="Hantar Emel" onClick="javascript:hantarEmel('$!id_permohonan','$!id_hakmilik')" />
						<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBorangG('$!id_permohonan','$!id_borangg')">
						#else
						
						<input type="button" name="button" id="button" value="Hantar Emel" onClick="javascript:hantarEmel('$!id_permohonan','$!id_hakmilik')" />
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBorangG('$!id_permohonan','$!id_siasatan','$!id_borangg','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniTotalAward('$!id_hakmilik')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewlistHM('$!id_permohonan')">
					</td>
				</tr>
			</table>
    
    		</div>
  			<!-- END TAB 1 -->
  			
  
  			<!-- START TAB 2 -->
  			#if($id_borangg!="")
    		<div class="TabbedPanelsContent">
			
			
				#if($mode=="new")
				<fieldset>
				<legend><strong>Penyampaian Borang H</strong></legend>
				<table width="100%" border="0">
					<tr>
						<td width="1%"><font color="red">*</font></td>
						<td width="20%">Nama Penghantar</td>
						<td width="1%">:</td>
						<td width="78%">
                        <select name="selectPenghantarNotis" class="autoselect" id="selectPenghantarNotis"  onchange="setNamaPenghantar(this.value)" >
                                        <option value="" >SILA PILIH</option>
                                  #foreach($ln in $listPenghantarNotis)                                 
                              <option value="$ln.NAMA_PEGAWAI">$ln.NAMA_PEGAWAI</option>
                             #end
                                        </select>
                        <input type="text" name="txtNamaHantar" id="txtNamaHantar" value="$!txtNamaHantar" size="47" maxlength="80"   ></td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td>Jenis Serahan</td>
						<td>:</td>
						<td><select name="socJenisSerah" style="width:160px" onchange="onChangeStatus()">
			      		
				      		#if($socJenisSerah=="1")
	      					<option value="1">PERKHIDMATAN POS</option>				
	      					<option value="2">SERAHAN TANGAN</option>	
	      					<option value="3">FAKSIMILI</option>
	      					<option value="4">FOLIO KOSONG</option>		
	      					<option value="">SILA PILIH</option>   	
	      					#elseif($socJenisSerah=="2")
	      					<option value="2">SERAHAN TANGAN</option>	
	      					<option value="1">PERKHIDMATAN POS</option>	
			      			<option value="3">FAKSIMILI</option>
			      			<option value="4">FOLIO KOSONG</option>		
			      			<option value="">SILA PILIH</option>  
			      			#elseif($socJenisSerah=="3")
			      			<option value="3">FAKSIMILI</option>
			      			<option value="1">PERKHIDMATAN POS</option>	
			      			<option value="2">SERAHAN TANGAN</option>
			      			<option value="4">FOLIO KOSONG</option>		
			      			<option value="">SILA PILIH</option>    
			      			#elseif($socJenisSerah=="4")
			      			<option value="4">FOLIO KOSONG</option>	
			      			<option value="1">PERKHIDMATAN POS</option>	
			      			<option value="2">SERAHAN TANGAN</option>
			      			<option value="3">FAKSIMILI</option>	
			      			<option value="">SILA PILIH</option> 
			      			#else
			      			<option value="">SILA PILIH</option>    			
			      			<option value="1">PERKHIDMATAN POS</option>	
			      			<option value="2">SERAHAN TANGAN</option>	
			      			<option value="3">FAKSIMILI</option>	
			      			<option value="4">FOLIO KOSONG</option>	
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
						<td>#if($showAst=="yes")<font color="red">*</font>#end</td>
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
						<td width="20%" valign="top">Catatan</td>
						#else
						<td width="20%" valign="top">Cara Tampal</td>
						#end
						<td width="1%" valign="top">:</td>
						<td width="78%" valign="top"><textarea name="txtCatatan" id="txtCatatan" cols="44%" rows="5"  onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen3,400);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen3,400);" >$!txtCatatan</textarea></td>
					</tr>
					<tr>
			        	<td colspan="3">&nbsp;</td>
			            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="400"></td>
			        </tr> 
				</table>
				</fieldset>	
				
				#end
			
			
			
				#if($mode=="view")
				
				#if($onchange=="no")
				#foreach($dataH in $dataBorangH)
					#set($txtNamaHantar=$dataH.nama_penghantar)
					#set($socJenisSerah=$dataH.jenis_serahan)
					#set($socStatusSerah=$dataH.status_serahan)
					#set($txdTarikhSerah=$dataH.tarikh_hantar)
					#set($txtNamaTerima=$dataH.nama_penerima)
					#set($txtNoKP=$dataH.no_kp_penerima)
					#set($txtCatatan=$dataH.catatan)
					#set($txtNoPB=$dataH.no_pb)
					#set($txtHubungan=$dataH.hubungan)
					#set($txtMasaTampal=$dataH.masa_tampal)
					#set($socJenisWaktu=$dataH.jenis_waktu)
					#set($txtTempatTampal=$dataH.tempat_tampal)					
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
				<legend><strong>Penyampaian Borang H</strong></legend>
				<table width="100%" border="0">
					<tr>
						<td width="1%"><font color="red">$!M</font></td>
						<td width="20%">Nama Penghantar</td>
						<td width="1%">:</td>
						<td width="78%">
                        
                         #if($isEdit=="yes")
            <select name="selectPenghantarNotis" class="autoselect" id="selectPenghantarNotis"  onchange="setNamaPenghantar(this.value)" >
                                        <option value="" >SILA PILIH</option>
                                  #foreach($ln in $listPenghantarNotis)                                 
                              <option value="$ln.NAMA_PEGAWAI">$ln.NAMA_PEGAWAI</option>
                             #end
                                        </select>
                                        #end
                        <input $disability $disabilityx type="text" name="txtNamaHantar" id="txtNamaHantar" value="$!txtNamaHantar" size="47" maxlength="80"   ></td>
					</tr>
				
					<tr>
						<td>&nbsp;</td>
						<td>Jenis Serahan</td>
						<td>:</td>
						<td><select name="socJenisSerah" $disability1 $disabilityx style="width:160px" onchange="onChangeStatusUpdate()">
			      		
			      			#if($socJenisSerah=="1")
	      					<option value="1">PERKHIDMATAN POS</option>				
	      					<option value="2">SERAHAN TANGAN</option>	
	      					<option value="3">FAKSIMILI</option>
	      					<option value="4">FOLIO KOSONG</option>		
	      					<option value="">SILA PILIH</option>   	
	      					#elseif($socJenisSerah=="2")
	      					<option value="2">SERAHAN TANGAN</option>	
	      					<option value="1">PERKHIDMATAN POS</option>	
			      			<option value="3">FAKSIMILI</option>
			      			<option value="4">FOLIO KOSONG</option>		
			      			<option value="">SILA PILIH</option>  
			      			#elseif($socJenisSerah=="3")
			      			<option value="3">FAKSIMILI</option>
			      			<option value="1">PERKHIDMATAN POS</option>	
			      			<option value="2">SERAHAN TANGAN</option>
			      			<option value="4">FOLIO KOSONG</option>		
			      			<option value="">SILA PILIH</option>    
			      			#elseif($socJenisSerah=="4")
			      			<option value="4">FOLIO KOSONG</option>	
			      			<option value="1">PERKHIDMATAN POS</option>	
			      			<option value="2">SERAHAN TANGAN</option>
			      			<option value="3">FAKSIMILI</option>	
			      			<option value="">SILA PILIH</option> 
			      			#else
			      			<option value="">SILA PILIH</option>    			
			      			<option value="1">PERKHIDMATAN POS</option>	
			      			<option value="2">SERAHAN TANGAN</option>	
			      			<option value="3">FAKSIMILI</option>	
			      			<option value="4">FOLIO KOSONG</option>	
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
						<td><font color="red">$!M</font></td>
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
						<td><font color="red">$!M</font></td>
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
						<td><font color="red">$!M</font></td>
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
						<td>#if($isEdit=="yes" && $showAst=="yes")<font color="red">$!M</font>#end</td>
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
						<td><font color="red">$!M</font></td>
						<td>Tempat Tampal</td>
						<td>:</td>
						<td><input type="text" $disability $disabilityx name="txtTempatTampal" id="txtTempatTampal" value="$!txtTempatTampal" size="47" maxlength="80"   ></td>
					</tr>
					#end
						
					<tr>
						<td width="1%">&nbsp;</td>
						#if($hideItem=="no")
						<td width="20%" valign="top">Catatan</td>
						#else
						<td width="20%" valign="top">Cara Tampal</td>
						#end
						<td width="1%" valign="top">:</td>
						<td width="78%" valign="top"><textarea name="txtCatatan" $disability $disabilityx id="txtCatatan" cols="44%" rows="5"  onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen3,400);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen3,400);" >$!txtCatatan</textarea></td>
					</tr>
					#if($isEdit=="yes")
					<tr>
			        	<td colspan="3">&nbsp;</td>
			            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="400"></td>
			        </tr> 
			        #end
				</table>
				</fieldset>
                
              #if($!id_borangh != "")  
                 <fieldset id="bottom">
	<legend><strong>&nbsp;MUATNAIK BORANG SABPN <input type="button" name="button" id="button" value="Cetak SABPN" onClick="javascript:cetakSABPN('$id_borangh','$id_permohonan','sabpn_notis_borangh')" /></strong></legend>
                
               
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
     <a href="javascript:popupCarianDokumen('$id_borangh','notis_borangh')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
    </td>
    </tr>
    </table>
    
    	
	<!--
            #parse("app/ppt/frmCarianListHMSek8.jsp")
            #parse("app/ppt/frmSeksyen8ListHM.jsp")
      -->      
	</fieldset>	
    #end
				
				#end
				
			
			<table width="100%" border="0">
				<tr align="center">
					<td>
					
					<!-- #if($list_borangh!=0)
						<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport2')" />
					#end -->
					
					#if($mode=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPenerimaanBorangH('$!id_permohonan','$!id_borangg','$!id_borangh','$!mode','$!hideItem','$!showAst')">
					#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPenerimaanBorangH('$!id_borangh')">
						<input type="button" name="cmdHapusH" value="Hapus" onClick="hapusBorangH('$!id_borangg','$!id_borangh')" >
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPenerimaanBorangH('$!id_permohonan','$!id_borangg','$!id_borangh','$!mode','$!hideItem','$!showAst')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalPenerimaan('$!id_borangh')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewlistHM('$!id_permohonan')">
					</td>
				</tr>
			</table>
			
			
			<fieldset>
			<legend><strong>Senarai Penerima Borang H</strong>
            
            #if($mode=="view")
    		<input type="button" name="cmdTambahH" value="Tambah" onClick="javascript:penerimaanBorangH('$!id_borangg')">
    		
    			#end
             <input type="button" name="cmdMasukNotis" value ="KEMASUKAN MAKLUMAT PENYAMPAIAN NOTIS SECARA PUKAL" onClick="javascript:popupCarianPB_HantarPukal('$id_hakmilik','$!id_borangg','skrin_kemasukan_notisH_secara_pukal')">
  
            </legend>
            <table width="100%" border="0"> 
    <tr >
    <td align="left">
  #if($selesai_simpan_pukal == "yes")  
  <font class="blink" color="blue" >MAKLUMAT PENYAMPAIAN <B>BORANG H</B> TELAH BERJAYA DISIMPAN SECARA PUKAL</font><BR />  
  #end
   <a href="javascript:popupCarianPB('$!id_borangg','skrin_penerima_H')"><font color="blue">>> SKRIN CAPAIAN PIHAK BERKEPENTINGAN (PENERIMA BORANG H)</font></a>	
    </td>
    </tr>
    </table>
    
 
  
            <!--
               
			
				#if($mode=="view")
    			<table width="100%" border="0">
    				<tr>
    					<td colspan="2"><input type="button" name="cmdTambahH" value="Tambah" onClick="javascript:penerimaanBorangH('$!id_borangg')"></td>
    				</tr>
    			</table>
    			#end
				
				#if($list_borangh > 5)
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
		        		
		        		#if($list_borangh!=0)
           	 			#foreach($listH in $listBorangH)
              				#set( $i = $velocityCount )
         					#if ( ($i % 2) != 1 )
              					#set( $row = "row2" )
         					#else
               					#set( $row = "row1" )
         					#end
		                    
		               <tr>
		                   <td class="$row" align="center">$!listH.bil</td>
		                   <td class="$row"><a href="javascript:viewPenerimaanBorangH('$!listH.id_borangh')"><font color="blue">$!listH.nama_pb</font></a></td>
		                   <td class="$row">$!listH.nama_penerima</td>
		                   <td class="$row">$!listH.nama_penghantar</td>
		                   <td class="$row">$!listH.tarikh_hantar</td>
		               <tr>
		                    #end
		               #else
		                    <tr>
		                    	<td colspan="2">Tiada rekod</td>
		                    </tr>
		               #end
				</table>
					
				#if($list_borangh > 5)
		        </div>
		        #end	
		-->
			</fieldset>	

    		</div>
    		#end
    		<!-- END TAB 2 -->
    		
  		</div>
	</div>


</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
		 <td><a href="#" onClick="javascript:cetakBorangG('$!id_permohonan','$!id_hakmilik')"><font color="blue">Borang G</font></a></td>
	  </tr>
	  <tr>
		 <td><a href="#" onClick="javascript:cetakBorangH('$!id_permohonan','$!id_hakmilik')"><font color="blue">Borang H</font></a></td>
	  </tr>
	 <!--  <tr>
		 <td><a href="#" onClick="javascript:cetakMohonBayaran('$!id_permohonan','$!id_hakmilik')"><font color="blue">Surat Kepada Agensi Pemohon Supaya Menyediakan Bayaran Pampasan</font></a> atau <a href="#" onClick="javascript:hantarEmailMohonBayaran('$!id_permohonan','$!id_hakmilik')"><font color="blue">Hantar Email</font></a></td>
	  </tr> -->
	   <tr>
		 <td><a href="#" onClick="javascript:cetakMohonBayaran('$!id_permohonan','$!id_hakmilik')"><font color="blue">Surat Kepada Agensi Pemohon Supaya Menyediakan Bayaran Pampasan</font></a> atau <a href="#" onClick="javascript:cetakEmailMohonBayaran('$!id_permohonan','$!id_hakmilik')"><font color="blue">Hantar Email</font></a></td>
	  </tr>
	  <tr>
		 <td><a href="#" onClick="javascript:cetakSuratLainKos('$!id_permohonan','$!id_hakmilik')"><font color="blue">Surat Kepada Agensi Pemohon Supaya Menyediakan Bayaran Lain-Lain Kos</font></a></td>
	  </tr>
	  <tr>
		 <td>
         
         
         #if($userIdNeg=='2')
         <a href="#" onClick="javascript:cetakBayaranLainKosKedah('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bayaran Lain-Lain Kos</font></a>
         #else
         <a href="#" onClick="javascript:cetakBayaranLainKos('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bayaran Lain-Lain Kos</font></a>
         #end
         
         </td>
	  </tr>
	  
	  #if($userIdNeg=='10')
	  <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang H</font></a></td>
	  </tr>
	  #else
	  <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang H - I</font></a></td>
	  </tr>
	  <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaianRamai('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang H - II</font></a></td>
	  </tr>
	  #end
	  
    </table>
</fieldset>	

#if($showAlertPaging=="yes")
<script>
alert("Sila Klik 'Sub Paging' No.2/No.3 Untuk Meneruskan Proses Bayaran Pampasan");
</script>
#end

<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_tanah" value="$!id_tanah">
<input type="hidden" name="id_bangunan" value="$!id_bangunan">
<input type="hidden" name="id_siasatan" value="$!id_siasatan">
<input type="hidden" name="id_pihakberkepentingan" value="$!id_pihakberkepentingan">
<input type="hidden" name="id_hakmilikpb" value="$!id_hakmilikpb">
<input type="hidden" name="id_award" value="$!id_award">
<input type="hidden" name="id_borangg" value="$!id_borangg">
<input type="hidden" name="id_borangh" value="$!id_borangh">
<input type="hidden" name="id_jenisdokumen" value="$!id_jenisdokumen">
<input type="hidden" name="idImejBorangH" value="$!idImejBorangH">
<input type="hidden" name="idImejBorangG" value="$!idImejBorangG">
<input type="hidden" name="idDokumen" value="$!idDokumen">

<!-- DATA TEMP -->
<input type="hidden" name="id_kategoritanah" value="$!id_kategoritanah">
<input type="hidden" name="luas_ambil" value="$!luas_ambil">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>




<script>

function cetakImej(id){
	var url = "../servlet/ekptg.view.ppt.FrmDisplayImageBorang?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakImejA(id){
	var url = "../servlet/ekptg.view.ppt.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSABPN(id_borangh,id_permohonan,jenis_report) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_borangh="+id_borangh+"&id_permohonan="+id_permohonan+"&report="+jenis_report+"&selectNoFail=yes";	
    //var url = "../servlet/ekptg.report.ppt.BorangA?idfail="+idfail+"&namaMukim="+nama2Mukim;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function popupCarianDokumen(id_borangh,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_borangh="+id_borangh+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}
function setNamaPenghantar(val)
{
	//alert("VAL :"+val);
	document.${formName}.txtNamaHantar.value = val;
	document.${formName}.id_borangh.value = "";
	//alert(document.${formName}.id_borangh.value);
}

function popupCarianPB_HantarPukal(id_hakmilik,id_borangg,flag_skrin)
{
	var txtNamaHantar = document.${formName}.txtNamaHantar.value;
	var socJenisSerah = document.${formName}.socJenisSerah.value;
	var socStatusSerah = document.${formName}.socStatusSerah.value;
	var txdTarikhSerah = document.${formName}.txdTarikhSerah.value;
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupNotisSecaraPukal?&id_hakmilik="+id_hakmilik+"&id_borangg="+id_borangg+"&flag_skrin="+flag_skrin+"&txtNamaHantar="+txtNamaHantar+"&socJenisSerah="+socJenisSerah+"&socStatusSerah="+socStatusSerah+"&txdTarikhSerah="+txdTarikhSerah;
	var hWnd = window.open(url,'printuser','width=1400,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function popupCarianPB(id_borangg,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianPBNotis?&id_borangg="+id_borangg+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function cetakBuktiPenyampaian(idpermohonan,idhakmilik) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=H&report=BuktiPenyampaianH&selectNoFail=yes";
	//var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBuktiPenyampaianRamai(idpermohonan,idhakmilik) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=H&report=BuktiPenyampaianRamaiH&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
/*
function cetakBuktiPenyampaianRamai(idpermohonan,idhakmilik) {
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=H&report=BuktiPenyampaianRamai&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBuktiPenyampaian(idpermohonan,idhakmilik) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&flagJenisSuratCara=H&report=BuktiPenyampaian&selectNoFail=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}*/



function onChangeStatusUpdate(){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewPenerimaanBorangH";
	document.${formName}.command2.value = "kemaskiniPenerimaanBorangH";
	document.${formName}.command3.value = "onChangeStatusUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function onchangeGetnameUpdate(){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "viewPenerimaanBorangH";
	document.${formName}.command2.value = "kemaskiniPenerimaanBorangH";
	document.${formName}.command3.value = "onchangeGetnameUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function onChangeStatus(){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "penerimaanBorangH";
	document.${formName}.command2.value = "onChangeStatus";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function onchangeGetname(){
	
	document.${formName}.id_borangh.value = "";
	//alert(document.${formName}.id_borangh.value);
	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "penerimaanBorangH";
	document.${formName}.command2.value = "onchangeGetname";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}

function cetakBayaranLainKos(idpermohonan,idhakmilik) {
	//var url = "../servlet/ekptg.report.ppt.BayaranLainKos?id_hakmilik="+idhakmilik;
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_hakmilik="+idhakmilik+"&id_permohonan="+idpermohonan+"&report=BayaranLainKos1&selectNoFail=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBayaranLainKosKedah(idpermohonan,idhakmilik) {
	//var url = "../servlet/ekptg.report.ppt.BayaranLainKos?id_hakmilik="+idhakmilik;
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=BayaranLainKos_Nofail&selectNoFail=yes";
    
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratLainKos(idpermohonan,idhakmilik) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=SuratLainKos&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakMohonBayaran(idpermohonan,idhakmilik) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=SuratMintaBayaran&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

/*function cetakEmailMohonBayaran(idpermohonan,idhakmilik) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=EmailMintaBayaran&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}*/
function cetakEmailMohonBayaran(idpermohonan,idhakmilik) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=EmailSuratMintaBayaran&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function hantarEmailMohonBayaran(idpermohonan,idhakmilik) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=SuratMintaBayaran&selectNoFail=yes&flagShowTarikhCetak=yes&todo=sendEmail";
    var hWnd = window.open(url,'Cetak','width=400,height=100, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangH(idpermohonan,idhakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=BorangH&selectNoFail=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangG(idpermohonan,idhakmilik) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?bydate=&id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=BorangG&selectNoFail=yes";
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
function kemaskiniPenerimaanBorangH(id_borangh) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_borangh.value = id_borangh;
	document.${formName}.command.value = "viewPenerimaanBorangH";
	document.${formName}.command2.value = "kemaskiniPenerimaanBorangH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function hapusBorangH(id_borangg,id_borangh) {
	//alert("masuk");
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_borangg.value = id_borangg;
	document.${formName}.id_borangh.value = id_borangh;
	document.${formName}.command.value = "hapusBorangH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function batalPenerimaan(id_borangh) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_borangh.value = id_borangh;
	document.${formName}.command.value = "viewPenerimaanBorangH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewPenerimaanBorangH(id_borangh) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_borangh.value = id_borangh;
	document.${formName}.command.value = "viewPenerimaanBorangH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function simpanPenerimaanBorangH(id_permohonan,id_borangg,id_borangh,mode,valHide,showAst) {

	
	if(id_borangh != ""){
		document.${formName}.id_borangh.value = id_borangh;
		}
	if(mode == "new"){
	var id_borangh = "";
	//alert(id_borangh);
	document.${formName}.id_borangh.value = id_borangh;
	}
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
	else
	if(valHide=="yes" && document.${formName}.txtTempatTampal.value == ""){
		alert("Sila masukkan \"Tempat Tampal\" terlebih dahulu.");
		document.${formName}.txtTempatTampal.focus(); 
		return;
	}
	else
	if((valHide=="no" && showAst=="yes") && document.${formName}.txtNamaTerima.value == ""){
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
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.tabId.value = "1";
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.id_borangg.value = id_borangg;
		
		if(mode=="new"){
			document.${formName}.command.value = "penerimaanBorangH";
			document.${formName}.command2.value = "simpanPenerimaanBorangH";
		}else{
			//document.${formName}.id_borangh.value = id_borangh;
			document.${formName}.command.value = "viewPenerimaanBorangH";
			document.${formName}.command2.value = "kemaskiniPenerimaanBorangH";
			document.${formName}.command3.value = "updatePenerimaanBorangH";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
		document.${formName}.submit();
	}
	
}
function penerimaanBorangH_displaySelesai(id_borangg) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_borangg.value = id_borangg;
	document.${formName}.command.value = "penerimaanBorangH_displaySelesai";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function penerimaanBorangH(id_borangg) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_borangg.value = id_borangg;
	document.${formName}.command.value = "penerimaanBorangH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kemaskiniBorangG(id_permohonan,id_borangg) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_borangg.value = id_borangg;
	document.${formName}.command.value = "viewJumlahAward";
	document.${formName}.command2.value = "kemaskiniBorangG";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function simpanBorangG(id_permohonan,id_siasatan,id_borangg,mode) {

	var dat1=document.${formName}.txdTarikhBorangG;
	var dat2=document.${formName}.txdTarikhBorangH;

	if(document.${formName}.txdTarikhBorangG.value == ""){
		alert("Sila masukkan \"Tarikh Borang G\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangG.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false){
		dat1.focus()
		return;
	}
	else if(document.${formName}.txdTarikhBorangH.value == ""){
		alert("Sila masukkan \"Tarikh Borang H\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangH.focus(); 
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false){
		dat2.focus()
		return;
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.tabId.value = "0";
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.id_siasatan.value = id_siasatan;

		if(mode=="new"){
			document.${formName}.command.value = "viewJumlahAward";
			document.${formName}.command2.value = "simpanBorangG";
		}else{
			document.${formName}.id_borangg.value = id_borangg;
			document.${formName}.command.value = "viewJumlahAward";
			document.${formName}.command2.value = "kemaskiniBorangG";
			document.${formName}.command3.value = "updateBorangG";
		}
		/* document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
		document.${formName}.submit(); */
		
		 var dp = document.${formName}.form_token.value ;
			var dopost = "&form_token="+dp;
			
	 		var data = create_request_string(document.${formName});
			document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan&"+data+dopost;
			document.${formName}.method="post";
			document.${formName}.enctype="multipart/form-data";
		    document.${formName}.encoding="multipart/form-data"; 
		    document.${formName}.submit();
		
	}
}
function batalKemaskiniTotalAward(id_hakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewJumlahAward";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewJumlahAward(id_hakmilik) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewJumlahAward";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
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
function kembali() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewlistHM(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewlistHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}

//function - 4/10/2017
function status_penerima()
{

        if (document.${formName}.id_hakmilik_pb.length == null)
		{
				if(document.${formName}.id_hakmilik_pb.value == document.${formName}.id_hakmilik_pb.value)
				{
				document.${formName}.id_status_penerima.value  = document.${formName}.socAwardKepada.value;				
				document.${formName}.temp_xhadir.value  = document.${formName}.txtUlasanTidakHadir.value;
				document.${formName}.temp_perintah.value  = document.${formName}.txtUlasanCatatanPerintah.value;
				document.${formName}.temp_nilai.value  = document.${formName}.txtPeratusJejas.value;
				
				}		
        } 
		else {
            for (i = 0; i < document.${formName}.id_hakmilik_pb.length; i++)
			{
               if(document.${formName}.id_hakmilik_pb[i].value == document.${formName}.id_hakmilik_pb.value)
				{
				//alert("lalalalla");
				document.${formName}.id_status_penerima[i].value  = document.${formName}.socAwardKepada.value;				
				document.${formName}.temp_xhadir[i].value  = document.${formName}.txtUlasanTidakHadir.value;
				document.${formName}.temp_perintah[i].value  = document.${formName}.txtUlasanCatatanPerintah.value;	
				document.${formName}.temp_nilai[i].value  = document.${formName}.txtPeratusJejas.value;
				}
            }
        }
		

}


//FUNCTION yati-23/1/2017
function hantarEmel(id_permohonan,id_hakmilik) {
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewJumlahAward";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan&FlagHantarEmel=Y";
	document.${formName}.submit();
}
function hantarEmelX(id_permohonan,id_hakmilik){

	alert(id_permohonan);
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	//alert("1")
	document.${formName}.tabId.value = "0";
	//alert("2")
	document.${formName}.id_permohonan.value = id_permohonan;
	//alert("3")
	document.${formName}.id_hakmilik.value = id_hakmilik;
	//document.${formName}.id_borangg.value = id_borangg;
	//document.${formName}.command.value = "viewJumlahAward";
	document.${formName}.command.value = "sentEmel";
	//alert("3")
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan&id_hakmilik="+id_hakmilik;
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