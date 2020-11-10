#parse("app/ppt/Sek8Paging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

#if($showAlertPaging=="yes")
<script>
alert("Sila Klik 'Paging' No.18 Untuk Teruskan ke Borang K");
</script>
#end


<fieldset id="top">
<center>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset id="middle">
	<legend>Pengambilan Segera</legend>
	
	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
	
	<div id="TabbedPanels1" class="TabbedPanels">
	
  		<ul class="TabbedPanelsTabGroup">
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);viewSegera('$!id_permohonan')" tabindex="1">Semakan Borang I</li>
   	 		<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);viewMaklumatSegera('$!id_permohonan')" tabindex="1">Maklumat Pengambilan Segera</li>
   	 		#if($showTab3=="yes")<li class="TabbedPanelsTab" onClick="javascript:setSelected(2);penjanaanBorangJ('$!id_permohonan')" tabindex="1">Penjanaan Borang J</li>#end
  		</ul>
  		
  		<div class="TabbedPanelsContentGroup">
  			
  			
  			
<!-- START TAB 1 -->
    		<div class="TabbedPanelsContent">
   <br/> 		
   
   #set($flagBorangI="")
   
   #foreach($data in $dataMMK)
   	#set($lblNamaPegawai=$data.user_name)
   	#set($lblKeputusanSemakan=$data.keputusan_semakan)
   	#set($lblTarikhSemak=$data.tarikh_semak)
   	#set($lblTarikhHantar=$data.tarikh_hantar)
   	#set($lblTarikhTerima=$data.tarikh_terima)
   	#set($lblTarikhKeputusan=$data.tarikh_mmk)
   	#set($lblNoRujukanMMK=$data.no_rujmmk)
   	#set($lblKeputusan=$data.keputusan_mmk)  	
   #end
  
   #if($onchange=="no")
  	 #foreach($data in $dataMMK)
   		#set($flagBorangI=$data.flag_borangi)
  	 #end
   #else
   	 #set($flagBorangI=$onchangeSemak)
   #end
  	
    		<fieldset>
    		
    		#if($modeMMK=="new")
    		<table width="100%" border="0">   		
    			<tr>
    				<td width="1%"><font color="red">*</font></td>
    				<td width="30%">Semakan Borang I dalam Kertas MMK</td>
    				<td width="1%">:</td>
    				<td width="68%"><select name="socSemakanBorangI" style="width:auto" onChange="onchangeSemak('$!id_permohonan')" >
      		
      					#if($flagBorangI=="1")
      					<option value="1">YA</option>			
      					<option value="2">TIDAK</option>
      					<option value="">SILA PILIH</option>  
      					#elseif($flagBorangI=="2")
      					<option value="2">TIDAK</option>
      				   	<option value="1">YA</option>	
      					<option value="">SILA PILIH</option> 
      					#else
      					<option value="">SILA PILIH</option>    			
      					<option value="1">YA</option>	
      					<option value="2">TIDAK</option>
      					#end
      					
					</select></td>
    			</tr>  			 	
    		</table>
    		#end
    		
    		
    		#if($modeMMK=="view")
    		
    		#if($isEditMMK=="no")
     			#set($flagDisb="disabled")
     			#set($flagDisbx="class=disabled")      	 
   			#else
   	 			#set($flagDisb="")
   	 			#set($flagDisbx="")
   			#end
   
    		<table width="100%" border="0">   		
    			<tr>
    				<td width="1%">#if($isEditMMK=="yes")<font color="red">*</font>#end</td>
    				<td width="30%">Semakan Borang I dalam Kertas MMK</td>
    				<td width="1%">:</td>
    				<td width="68%"><select name="socSemakanBorangI" $flagDisb $flagDisbx style="width:auto" onChange="onchangeSemakUpdate('$!id_permohonan')" >
      		
      					#if($flagBorangI=="1")
      					<option value="1">YA</option>	  			
      					<option value="2">TIDAK</option>
      					<option value="">SILA PILIH</option>
      					#elseif($flagBorangI=="2")
      					<option value="2">TIDAK</option>		
      					<option value="1">YA</option>	
      					<option value="">SILA PILIH</option>  
      					#else
      					<option value="">SILA PILIH</option>    			
      					<option value="1">YA</option>	
      					<option value="2">TIDAK</option>
      					#end
      					
					</select></td>
    			</tr>  			 	
    		</table>
    		#end
    		
   			</fieldset>
   			
   		<br/>
   			
   			#if($flagBorangI=="1" || $showMMK=="yes")
   			<fieldset>
   			<legend><strong>Semakan MMK oleh Penolong Pengarah / Penolong Pegawai Tanah</strong></legend>
   				<table width="100%" border="0">   	
   					<tr>
   						<td width="1%">&nbsp;</td>
   						<td width="30%">Nama Pegawai</td>
   						<td width="1%">:</td>
   						<td width="68%">$!lblNamaPegawai.toUpperCase()</td>
   					</tr>
   					<tr>
   						<td>&nbsp;</td>
   						<td>Keputusan Semakan</td>
   						<td>:</td>
   						<td>$!lblKeputusanSemakan</td>
   					</tr>
   					<tr>
   						<td>&nbsp;</td>
   						<td>Tarikh Semak</td>
   						<td>:</td>
   						<td>$!lblTarikhSemak</td>
   					</tr>
   				</table>
   			</fieldset>	
    		
    	<br/>
    	
    		<fieldset>
    		<legend><strong>Maklumat MMK (Borang I)</strong></legend>
    			<table width="100%" border="0">
    				<tr>
    					<td width="1%">&nbsp;</td>
    					<td width="30%">Tarikh Hantar</td>
    					<td width="1%">:</td>
    					<td width="68%">$!lblTarikhHantar</td>
    				</tr>
    				<tr>
   						<td>&nbsp;</td>
   						<td>Tarikh Terima</td>
   						<td>:</td>
   						<td>$!lblTarikhTerima</td>
   					</tr>
   					<tr>
   						<td>&nbsp;</td>
   						<td>Tarikh Kelulusan MMK</td>
   						<td>:</td>
   						<td>$!lblTarikhKeputusan</td>
   					</tr>
   					<tr>
   						<td>&nbsp;</td>
   						<td>No.Rujukan MMK/Ringkasan/JKKT</td>
   						<td>:</td>
   						<td>$!lblNoRujukanMMK</td>
   					</tr>
   					<tr>
   						<td>&nbsp;</td>
   						<td>Keputusan</td>
   						<td>:</td>
   						<td>$!lblKeputusan</td>
   					</tr>
    			</table>
    		</fieldset>	
    		#end
    		
    		<table width="100%" border="0">
				<tr align="center">
					<td>
					#if($modeMMK=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanFlagBorangI('$!id_mmk')">
					#end
				
					#if($modeMMK=="view")
					#if($isEditMMK=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniFlagBorangI('$!id_mmk')">
					
					#else
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanFlagBorangI('$!id_mmk')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_permohonan')">
					#end
					#end
				
					<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali()">
					</td>
				</tr>
			</table>
    		
    		</div>
<!-- END TAB 1 -->
    		
    		
    		
    		
    		
<!-- START TAB 2 -->
    		<div class="TabbedPanelsContent">
    		
    <br/> 	
    		#if($mode=="new")
    		<fieldset>
    		<legend><strong>Maklumat Surat Agensi</strong></legend>   
    		 		
    		 	<table width="100%" border="0">   
    		 	<tr>	
    		 	
    		 		<!-- TABLE KIRI -->
    				<td width="55%" valign="top"><table width="100%" border="0"> 
    				<tr>
    					<td width="1%">&nbsp;</td>
    					<td width="33%">No. Rujukan Surat</td>
    					<td width="1%">:</td>
    					<td width="65%"><input type="text" name="txtNoRujSurat" id="txtNoRujSurat" value="$!txtNoRujSurat" size="35" maxlength="50"   ></td>
    				</tr>  		
    				<tr>
    					<td>&nbsp;</td>
    					<td>Tarikh Surat</td>
    					<td>:</td>
    					<td><input name="txdTarikhSurat" id="txdTarikhSurat" size="11" type="text" value="$!txdTarikhSurat" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">&nbsp;$!frmtdate</td>
    				</tr>
    				<tr>
    					<td>&nbsp;</td>
    					<td>Tarikh Terima</td>
    					<td>:</td>
    					<td><input name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate</td>
    				</tr>
    				
    				</table></td>
    			
    			
    				<!-- TABLE KANAN -->
    				<td width="45%" valign="top"><table width="100%" border="0">   		
    				<tr>
    					<td width="1%">&nbsp;</td>
    					<td width="35%">Tarikh Borang I</td>
    					<td width="1%">:</td>
    					<td width="63%"><input name="txdTarikhBorangI" id="txdTarikhBorangI" size="11" type="text" value="$!txdTarikhBorangI" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangI',false,'dmy');">&nbsp;$!frmtdate</td>
    				</tr>
    				<tr>
    					<td>&nbsp;</td>
    					<td>Tarikh Borang K</td>
    					<td>:</td>
    					<td><input name="txdTarikhBorangK" id="txdTarikhBorangK" size="11" type="text" value="$!txdTarikhBorangK" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangK',false,'dmy');">&nbsp;$!frmtdate</td>
    				</tr>
    				<tr>
    					<td>&nbsp;</td>
    					<td>Pilihan Hakmilik</td>
    					<td>:</td>
    					<td><select name="socSemakanBorangI" style="width:auto" onChange="onchangePilihanHM('$!id_permohonan')" >
      		
      						#if($socSemakanBorangI=="1")
      						<option value="1">SEMUA HAKMILIK</option>	
      						<option value="2">HAKMILIK TERTENTU</option>
      						<option value="">SILA PILIH</option>
      						#elseif($socSemakanBorangI=="2")
      						<option value="2">HAKMILIK TERTENTU</option>
      						<option value="1">SEMUA HAKMILIK</option>	
      						<option value="">SILA PILIH</option>
      						#else
      						<option value="">SILA PILIH</option>    			
      						<option value="1">SEMUA HAKMILIK</option>	
      						<option value="2">HAKMILIK TERTENTU</option>
      						#end

						</select></td>
    				</tr>
    				</table></td>
    			
    			</tr>
    			</table>
	
    		</fieldset>
    	
    <br/>	
    
    
    	
    <fieldset>
	<legend><strong>Senarai Maklumat Hakmilik Terlibat</strong></legend>
			
			<!-- Fungsi carian by no.lot/pt/nama pb -->
            #parse("app/ppt/frmCarianListHMSek8.jsp")
    			
    	#if($saiz_listTanah > 5)
        	<div style="width:100%;height:100;overflow:auto"> 
        #end	
    			
    	<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			#if($showCB=="yes")<td align="center" width="4%"><input type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></td>#end
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>No.Hakmilik</b></td>
                  	<td><b>No.LOT/No.PT</b></td>                  
                  	<td><b>Mukim/Pekan/Bandar</b></td>
            		<td><b>Keluasan</b></td>
            		<!-- td><b>Pegawai</b></td>  -->
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
               		#if($showCB=="yes")<td align="center" class="$row"><input type="checkbox" name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listTanah.id_hakmilik"></td>#end
                    <td class="$row" align="center">$!listTanah.bil</td>
                    <td class="$row">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</td>
                	<td class="$row">$!listTanah.no_lotpt</td> 
                	<td class="$row">$!listTanah.nama_mukim</td>
                	<td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>							
                   <!-- <td class="$row">$!listTanah.nama_pegawai</td> --> 
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
    #end	
    	
    	
    	
    #if($mode=="view")
    
    #if($onchangeSegera=="no")
    #foreach($dbi in $dataBorangI)
    #set($txtNoRujSurat=$dbi.no_rujukan_surat)
    #set($txdTarikhSurat=$dbi.tarikh_surat)
    #set($txdTarikhTerima=$dbi.tarikh_terima)
    #set($txdTarikhBorangI=$dbi.tarikh_borangi)
    #set($txdTarikhBorangK=$dbi.tarikh_borangk)
    #set($socSemakanBorangI=$dbi.jenis_ambilsegera)
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
    
    		<fieldset>
    		<legend><strong>Maklumat Surat Agensi</strong></legend>   
    		 		
    		 	<table width="100%" border="0">   
    		 	<tr>	
    		 	
    		 		<!-- TABLE KIRI -->
    				<td width="55%" valign="top"><table width="100%" border="0"> 
    				<tr>
    					<td width="1%">&nbsp;</td>
    					<td width="33%">No. Rujukan Surat</td>
    					<td width="1%">:</td>
    					<td width="65%"><input $disability $disabilityx type="text" name="txtNoRujSurat" id="txtNoRujSurat" value="$!txtNoRujSurat" size="35" maxlength="50"   ></td>
    				</tr>  		
    				<tr>
    					<td>&nbsp;</td>
    					<td>Tarikh Surat</td>
    					<td>:</td>
    					<td><input $disability $disabilityx name="txdTarikhSurat" id="txdTarikhSurat" size="11" type="text" value="$!txdTarikhSurat" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">&nbsp;$!frmtdate#end</td>
    				</tr>
    				<tr>
    					<td>&nbsp;</td>
    					<td>Tarikh Terima</td>
    					<td>:</td>
    					<td><input $disability $disabilityx name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
    				</tr>
    				
    				</table></td>
    			
    			
    				<!-- TABLE KANAN -->
    				<td width="45%" valign="top"><table width="100%" border="0">   		
    				<tr>
    					<td width="1%">&nbsp;</td>
    					<td width="35%">Tarikh Borang I</td>
    					<td width="1%">:</td>
    					<td width="63%"><input $disability $disabilityx name="txdTarikhBorangI" id="txdTarikhBorangI" size="11" type="text" value="$!txdTarikhBorangI" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangI',false,'dmy');">&nbsp;$!frmtdate#end</td>
    				</tr>
    				<tr>
    					<td>&nbsp;</td>
    					<td>Tarikh Borang K</td>
    					<td>:</td>
    					<td><input $disability $disabilityx name="txdTarikhBorangK" id="txdTarikhBorangK" size="11" type="text" value="$!txdTarikhBorangK" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangK',false,'dmy');">&nbsp;$!frmtdate#end</td>
    				</tr>
    				<tr>
    					<td>&nbsp;</td>
    					<td>Pilihan Hakmilik</td>
    					<td>:</td>
    					<td><select name="socSemakanBorangI" $disability1 $disabilityx style="width:auto" onChange="onchangePilihanHMUpdate('$!id_permohonan')" >
      		
      						#if($socSemakanBorangI=="1")
      						<option value="1">SEMUA HAKMILIK</option>	
      						<option value="2">HAKMILIK TERTENTU</option>
      						<option value="">SILA PILIH</option>   
      						#elseif($socSemakanBorangI=="2")
      						<option value="2">HAKMILIK TERTENTU</option>
      						<option value="1">SEMUA HAKMILIK</option>	
      						<option value="">SILA PILIH</option>   
      						#else
      						<option value="">SILA PILIH</option>    			
      						<option value="1">SEMUA HAKMILIK</option>	
      						<option value="2">HAKMILIK TERTENTU</option>
      						#end

						</select></td>
    				</tr>
    				</table></td>
    			
    			</tr>
    			</table>
	
    		</fieldset>
    	
    <br/>	
    	
    #if($onchangeSegera=="no")	
    #if($showCB=="yes")
    	#set($showx = "on")
    #else
   		#set($showx = "off")
    #end	
    #end
    
    <fieldset>
	<legend><strong>Senarai Maklumat Hakmilik Terlibat</strong></legend>
			
			<!-- Fungsi carian by no.lot/pt/nama pb -->
            #parse("app/ppt/frmCarianListHMSek8.jsp")
    			
    	#if($saiz_listTanah > 5)
        	<div style="width:100%;height:100;overflow:auto"> 
        #end	
    			
    	<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			#if($showx=="on")<td align="center" width="4%"><input $disability1 type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></td>#end
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>No.Hakmilik</b></td>
                  	<td><b>No.LOT/No.PT</b></td>               
                  	<td><b>Mukim/Pekan/Bandar</b></td>
            		<td><b>Keluasan</b></td>
            		<td><b>Pegawai</b></td> 
        		</tr>
        		
        		#if($saiz_listTanah!=0)
                    #foreach($listTanah in $listMaklumatTanah)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
                    #if($listTanah.flag_ambil_segera == "1")
                    	#set($checkHM = "checked")
                    #else
                   		#set($checkHM = "")
                    #end
                    
               <tr>
                	#if($showx=="on")<td align="center" class="$row"><input $disability1 type="checkbox" $checkHM name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listTanah.id_hakmilik"></td>#end
                    <td class="$row" align="center">$!listTanah.bil</td>
                    <td class="$row">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</td>
                	<td class="$row">$!listTanah.no_lotpt</td>   
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
    #end	
    	
    	
    		<table width="100%" border="0">
				<tr align="center">
					<td>
					#if($mode=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanMaklumatSegera('$!id_permohonan','$!id_borangi','$!id_borangk','$!saiz_listTanah','$!mode')">
					#end
				
					#if($mode=="view")
					#if($isEdit=="no")
					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" /> 
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniMaklumatSegera('$!id_permohonan')">
                        #if($ID_NEGERIPROJEK == "4" || $ID_NEGERIPROJEK == "5")
                         <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Borang I)" onClick="popupEtanah('$id_fail','$id_permohonan','BorangI','')">
                        #end
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanMaklumatSegera('$!id_permohonan','$!id_borangi','$!id_borangk','$!saiz_listTanah','$!mode')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalMaklumatSegera('$!id_permohonan')">
					#end
					#end
				
					<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali()">
					</td>
				</tr>
			</table>
			
			
    		</div>
<!-- END TAB 2 -->
    		
    		
    		
    		
    		
<!-- START TAB 3 -->
			#if($showTab3=="yes")
    		<div class="TabbedPanelsContent">
    		
    <br/>
    		
    		#if($mode=="new")
    		<fieldset>
    		<legend><strong>Hakmilik Yang Terlibat Dengan Pengambilan Segera</strong></legend>
    		<table width="100%" border="0">
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="20%">No. Hakmilik</td>
    				<td width="1%">:</td>
    				<td width="78%">$!selectHakmilik</td>
    			</tr>
    			#if($showListBangunan=="yes")
    			<tr>
    				<td>&nbsp;</td>
    				<td>No. Lot</td>
    				<td>:</td>
    				<td>$!lblNoLot</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Bandar / Pekan / Mukim</td>
    				<td>:</td>
    				<td>$!lblNamaMukim</td>
    			</tr>
    			#end
    		</table>
    		</fieldset>
    		
    		
    		
    <!-- SENARAI BANGUNAN -->
    #if($showListBangunan=="yes")
<br/>    
    <fieldset id="bangunan">
    <legend>Senarai Bangunan</legend>
    
    		#if($saiz_bangunan > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td width="15%"><b>&nbsp;No.Bangunan</b></td>
        			<td width="30%"><b>&nbsp;Jenis Bangunan</b></td>
        		</tr>
        		
        		#if($saiz_bangunan!=0)
                    #foreach($list in $listMaklumatBangunan)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!list.bil</td>
                   <td class="$row">&nbsp;<a href="javascript:viewBangunan('$!list.id_bangunan')"><font color="blue">$!list.no_bangunan</font></a></td>
                   <td class="$row">&nbsp;$!list.keterangan_bangunan</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_bangunan > 5)
                </div>
            #end
            
    </fieldset>
<br/>    
    #end
    		
    <!-- DATA BANGUNAN -->		
    #if($showDataBangunan=="yes")
    		
    #foreach($dataB in $dataBangunan)
	#set($txtNoBangunan=$dataB.no_bangunan)
	#set($txtJenisBangunan=$dataB.jenis_bangunan)
	#set($txtNilai=$dataB.nilai_bangunan)
	#end
    		
    <fieldset>
    <legend><strong>Maklumat Bangunan</strong></legend>
    		<table width="100%" border="0">
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="23%">No. Bangunan</td>
    				<td width="1%">:</td>
    				<td width="75%"><input type="text" name="txtNoBangunan" value="$!txtNoBangunan" id="txtNoBangunan" size="5" maxlength="3"   ></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Jenis Bangunan</td>
    				<td>:</td>
    				<td><input type="text" name="txtJenisBangunan" id="txtJenisBangunan" value="$!txtJenisBangunan" size="20" maxlength="20"   ></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Nilai Bangunan</td>
    				<td>:</td>
    				<td><input type="text" name="txtNilai" id="txtNilai" value="$!txtNilai" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtNilai')"></td>
    			</tr>
    			
    			#set($kos="")
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Kos Pindah / Bina Semula</td>
    				<td>:</td>
    				<td><input type="text" name="txtKos" id="txtKos" value="$!kos" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!kos')"></td>
    			</tr>
    			
    			<!-- <tr>
    				<td>&nbsp;</td>
    				<td>Keputusan Pemilik</td>
    				<td>:</td>
    				<td>&nbsp;</td>
    			</tr> -->
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Tarikh Notis</td>
    				<td>:</td>
    				<td><input name="txdTarikhNotis" id="txdTarikhNotis" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhNotis',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Tarikh Tamat Notis</td>
    				<td>:</td>
    				<td><input name="txdTarikhTamatNotis" id="txdTarikhTamatNotis" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTamatNotis',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Tempoh Notis (Hari)</td>
    				<td>:</td>
    				<td><input type="text" name="txtTempoh" id="txtTempoh" value="" size="5" maxlength="2" onblur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value)" ></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td valign="top">Tindakan</td>
    				<td valign="top">:</td>
    				<td valign="top"><textarea cols="37%" rows="4" name="txtTindakan" id="txtTindakan" onKeyUp="textCounter(this.form.txtTindakan,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtTindakan,this.form.remLen1,400);" ></textarea></td>
    			</tr>
    		</table>
    	
    		
    <fieldset style="width:95%">
    <legend>Senarai Pemilik</legend>
    
    		#if($saiz_maklumatpb > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td width="96%"><b>&nbsp;Nama</b></td>
        		</tr>
        		
        		#if($saiz_maklumatpb!=0)
                    #foreach($listPB in $listMaklumatPB)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listPB.bil</td>
                   <td class="$row">&nbsp;$!listPB.nama_pb</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_maklumatpb > 5)
                </div>
            #end
            
    </fieldset>
    		
    </fieldset>
    #end
    	
    #end
    
    
    
    
    #if($mode=="view")

    #foreach($dataBJ in $dataBorangJ)
	#set($txtNoBangunan=$dataBJ.no_bangunan)
	#set($txtJenisBangunan=$dataBJ.jenis_bangunan)
	#set($txtNilai=$dataBJ.nilai_bangunan)
	
	#set($txdTarikhNotis=$dataBJ.tarikh_notis)
	#set($txdTarikhTamatNotis=$dataBJ.tarikh_tamat_notis)
	#set($txtTempoh=$dataBJ.tempoh)
	#set($txtTindakan=$dataBJ.tindakan)
	#set($txtKos=$dataBJ.kos_pindah)
	#set($txtNilai=$dataBJ.nilai_bangunan)
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
    		
    <fieldset id="bangunan">
    <legend><strong>Maklumat Bangunan</strong></legend>
    		<table width="100%" border="0">
    			<tr>
    				<td width="1%">&nbsp;</td>
    				<td width="23%">No. Bangunan</td>
    				<td width="1%">:</td>
    				<td width="75%"><input $disability $disabilityx type="text" name="txtNoBangunan" value="$!txtNoBangunan" id="txtNoBangunan" size="5" maxlength="3"   ></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Jenis Bangunan</td>
    				<td>:</td>
    				<td><input $disability $disabilityx type="text" name="txtJenisBangunan" id="txtJenisBangunan" value="$!txtJenisBangunan" size="20" maxlength="20"   ></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Nilai Bangunan</td>
    				<td>:</td>
    				#if($isEdit=="no")
					<td><input type="text" $disability $disabilityx name="txtNilai" id="txtNilai" value="$!Utils.format2Decimal($txtNilai)" size="10" maxlength="11" style="text-align:right" ></td>
					#else
					<td><input type="text" $disability $disabilityx name="txtNilai" id="txtNilai" value="$!txtNilai" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtNilai')"></td>
					#end
    			</tr>
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Kos Pindah / Bina Semula</td>
    				<td>:</td>
    				#if($isEdit=="no")
					<td><input type="text" $disability $disabilityx name="txtKos" id="txtKos" value="$!Utils.format2Decimal($txtKos)" size="10" maxlength="11" style="text-align:right" ></td>
					#else
					<td><input type="text" $disability $disabilityx name="txtKos" id="txtKos" value="$!txtKos" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtKos')"></td>
					#end
    			</tr>
    			
    			<!-- <tr>
    				<td>&nbsp;</td>
    				<td>Keputusan Pemilik</td>
    				<td>:</td>
    				<td>&nbsp;</td>
    			</tr> -->
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Tarikh Notis</td>
    				<td>:</td>
    				<td><input $disability $disabilityx name="txdTarikhNotis" id="txdTarikhNotis" size="11" type="text" value="$!txdTarikhNotis" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhNotis',false,'dmy');">&nbsp;$!frmtdate#end</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Tarikh Tamat Notis</td>
    				<td>:</td>
    				<td><input $disability $disabilityx name="txdTarikhTamatNotis" id="txdTarikhTamatNotis" size="11" type="text" value="$!txdTarikhTamatNotis" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTamatNotis',false,'dmy');">&nbsp;$!frmtdate#end</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Tempoh Notis (Hari)</td>
    				<td>:</td>
    				<td><input $disability $disabilityx type="text" name="txtTempoh" id="txtTempoh" value="$!txtTempoh" size="5" maxlength="2" onblur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value)" ></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td valign="top">Tindakan</td>
    				<td valign="top">:</td>
    				<td valign="top"><textarea $disability $disabilityx cols="37%" rows="4" name="txtTindakan" id="txtTindakan" onKeyUp="textCounter(this.form.txtTindakan,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtTindakan,this.form.remLen1,400);" >$!txtTindakan</textarea></td>
    			</tr>
    		</table>
    	
    		
    <fieldset style="width:95%">
    <legend>Senarai Pemilik</legend>
    
    		#if($saiz_maklumatpb > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td width="96%"><b>&nbsp;Nama</b></td>
        		</tr>
        		
        		#if($saiz_maklumatpb!=0)
                    #foreach($listPB in $listMaklumatPB)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listPB.bil</td>
                   <td class="$row">&nbsp;$!listPB.nama_pb</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_maklumatpb > 5)
                </div>
            #end
            
    </fieldset>
    		
    </fieldset>
    #end
    
    
    <br/>
    <fieldset>
    <legend>Senarai Bangunan Yang Terlibat Dalam Pengambilan Segera</legend>
    
    		#if($mode=="view")
    		<table width="100%" border="0">   
        		<tr>
               		<td><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:penjanaanBorangJ('$!id_permohonan')"></td>
    			</tr>
    		</table>
    		#end
    	
    		#if($saiz_listborangj > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>&nbsp;No Hakmilik</b></td>	 
                  	<td><b>&nbsp;No.LOT/No.PT</b></td>     
        			<td><b>&nbsp;No Bangunan</b></td>
        		</tr>
        		
        		#if($saiz_listborangj!=0)
                    #foreach($listJ in $listBorangJ)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listJ.bil</td>
                   <td class="$row">&nbsp;<a href="javascript:viewDataBorangJ('$!listJ.id_borangj')"><font color="blue">$!listJ.kod_jenis_hakmilik $!listJ.no_hakmilik</font></a></td>
                   <td class="$row">&nbsp;$!listJ.no_lotpt</td>          
                   <td class="$row">&nbsp;$!listJ.no_bangunan</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listborangj > 5)
                </div>
            #end
            
    </fieldset>
    		
    		
    		<table width="100%" border="0">
				<tr align="center">
					<td>
					#if($mode=="new" && $showDataBangunan=="yes")
					
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanBorangJ('$!id_permohonan','$!id_bangunan','$!id_borangj','$!mode')">
					#end
				
					#if($mode=="view")
					#if($isEdit=="no")
					
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBorangJ('$!id_borangj')">
					<input type="button" name="cmdHapus" value="Hapus" onClick="javascript:hapusBorangJ('$!id_borangj')">
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBorangJ('$!id_permohonan','$!id_bangunan','$!id_borangj','$!mode')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalBorangJ('$!id_borangj')">
					#end
					#end
				
					#if($saiz_listborangj!=0)
					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport2')" />
					#end 
					
					<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali()">
					</td>
				</tr>
			</table>
    		
    		</div>
    		#end
<!-- END TAB 3 -->
	
	
 		</div>
 		
	</div>

    </fieldset>
    	
</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>  
      	<td><a href="#" onClick="javascript:cetakBorangI('$!id_fail','$!saiz_listTanah','$!nama_pengarah')"><font color="blue">Borang I</font></a></td>
      </tr>     
    </table>
</fieldset>	
<fieldset id="tableReport2" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>  
      	<td><a href="#" onClick="javascript:cetakBorangJ('$!id_fail')"><font color="blue">Borang J</font></a></td>
      </tr>     
    </table>
</fieldset>	

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_mmk" value="$!id_mmk">
<input type="hidden" name="id_borangi" value="$!id_borangi">
<input type="hidden" name="id_borangj" value="$!id_borangj">
<input type="hidden" name="id_borangk" value="$!id_borangk">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_bangunan" value="$!id_bangunan">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>




<!-- MAIN FUNCTION -->
<script>

function popupEtanah(id_fail,id_permohonan,jenis_skrin,command) {

	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&jenis_skrin="+jenis_skrin+"&command="+command;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}

function cetakBorangJ(idfail) {

	//var url = "../servlet/ekptg.report.ppt.BorangJ?id_Fail="+idfail;
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_fail="+idfail+"&report=BorangJ";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}
function cetakBorangI(idfail,totalHM,namaPengarah) {

	if(totalHM > 1){
		var url = "../servlet/ekptg.report.ppt.BorangILebih?id_Fail="+idfail+"&namaPegawai="+namaPengarah;   	
	}else{
		var url = "../servlet/ekptg.report.ppt.BorangI?id_Fail="+idfail+"&namaPegawai="+namaPengarah;   
	}
	
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
function batalBorangJ(id_borangj) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "bangunan";
	document.${formName}.tabId.value = "2";
	document.${formName}.id_borangj.value = id_borangj;
	document.${formName}.command.value = "viewDataBorangJ";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function kemaskiniBorangJ(id_borangj) {
	document.${formName}.ScreenLocation.value = "bangunan";
	document.${formName}.tabId.value = "2";
	document.${formName}.id_borangj.value = id_borangj;
	document.${formName}.command.value = "viewDataBorangJ";
	document.${formName}.command2.value = "kemaskiniBorangJ";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function hapusBorangJ(id_borangj) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "2";
	document.${formName}.id_borangj.value = id_borangj;
	document.${formName}.command.value = "hapusBorangJ";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function viewDataBorangJ(id_borangj) {
	document.${formName}.ScreenLocation.value = "bangunan";
	document.${formName}.tabId.value = "2";
	document.${formName}.id_borangj.value = id_borangj;
	document.${formName}.command.value = "viewDataBorangJ";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function simpanBorangJ(id_permohonan,id_bangunan,id_borangj,mode) {

	var dat1=document.${formName}.txdTarikhNotis
	var dat2=document.${formName}.txdTarikhTamatNotis

	//tarikh Notis
	var TN  = document.getElementById("txdTarikhNotis").value;
	var dt1   = parseInt(TN.substring(0,2),10);
   	var mon1  = parseInt(TN.substring(3,5),10);
   	var yr1   = parseInt(TN.substring(6,10),10);
   	var dateNotis = new Date(yr1, mon1, dt1);

  	//tarikh tamat notis
	var TTN  = document.getElementById("txdTarikhTamatNotis").value;
	var dt2   = parseInt(TTN.substring(0,2),10);
   	var mon2  = parseInt(TTN.substring(3,5),10);
   	var yr2   = parseInt(TTN.substring(6,10),10);
   	var dateTamatNotis = new Date(yr2, mon2, dt2);
	
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
	else if(dat1.value!="" && dat2.value!="" && (dateTamatNotis < dateNotis)){
	   	alert("Sila pastikan \"Tarikh Tamat Notis\" tidak kurang dari \"Tarikh Notis\".");
		document.${formName}.txdTarikhTamatNotis.focus();
		return;
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.tabId.value = "2";
		document.${formName}.id_permohonan.value = id_permohonan;

		if(mode=="new"){
			document.${formName}.id_bangunan.value = id_bangunan;
			document.${formName}.command.value = "penjanaanBorangJ";
			document.${formName}.command2.value = "getListBangunan";
			document.${formName}.command3.value = "viewBangunan";
			document.${formName}.command4.value = "simpanBorangJ";
		}else{
			document.${formName}.id_borangj.value = id_borangj;
			document.${formName}.command.value = "viewDataBorangJ";
			document.${formName}.command2.value = "kemaskiniBorangJ";
			document.${formName}.command3.value = "updateBorangJ";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
		document.${formName}.submit();
	}
}
function viewBangunan(id_bangunan) {
	document.${formName}.ScreenLocation.value = "bangunan";
	document.${formName}.tabId.value = "2";
	document.${formName}.id_bangunan.value = id_bangunan;
	document.${formName}.command.value = "penjanaanBorangJ";
	document.${formName}.command2.value = "getListBangunan";
	document.${formName}.command3.value = "viewBangunan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function getListBangunan(id_permohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "2";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "penjanaanBorangJ";
	document.${formName}.command2.value = "getListBangunan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function batalMaklumatSegera(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewMaklumatSegera";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function onchangePilihanHMUpdate(idpermohonan){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewMaklumatSegera";
	document.${formName}.command2.value = "kemaskiniMaklumatSegera";
	document.${formName}.command3.value = "onchangePilihanHMUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function kemaskiniMaklumatSegera(idpermohonan){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewMaklumatSegera";
	document.${formName}.command2.value = "kemaskiniMaklumatSegera";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function onchangePilihanHM(idpermohonan){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewMaklumatSegera";
	document.${formName}.command2.value = "onchangePilihanHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function simpanMaklumatSegera(idpermohonan,idborangI,idborangK,size,mode){

	var typeVal = parseInt(document.${formName}.socSemakanBorangI.value);

	if(typeVal == 2 ){
		
		var checkSelected=false;
		if(size>1){
			for(var i=0 ; i < document.${formName}.cbsemaks.length; i++) 
			{ 
    			if (document.${formName}.cbsemaks[i].checked)
        		{
  					checkSelected=true; 
  				}
			}
		}else{
			if (document.${formName}.cbsemaks.checked)
    		{
				checkSelected=true; 
    		}
		}
	}

	var dat1=document.${formName}.txdTarikhSurat
	var dat2=document.${formName}.txdTarikhTerima
	var dat3=document.${formName}.txdTarikhBorangI
	var dat4=document.${formName}.txdTarikhBorangK

	
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
	else if (dat4.value!="" && isDate(dat4.value)==false)
	{
		dat4.focus()
		return;
	}
	else if(typeVal == 2 && !checkSelected)
	{
		alert("Sila Pilih Hakmilik Tertentu Terlebih Dahulu.");
		document.${formName}.cbsemaks[0].focus();
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "viewMaklumatSegera";
			document.${formName}.command2.value = "simpanMaklumatSegera";
		}else{
			document.${formName}.id_borangi.value = idborangI;
			document.${formName}.id_borangk.value = idborangK;
			document.${formName}.command.value = "viewMaklumatSegera";
			document.${formName}.command2.value = "kemaskiniMaklumatSegera";
			document.${formName}.command3.value = "updateMaklumatSegera";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
		document.${formName}.submit();
	}
	
}
function batalKemaskini(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewSegera";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function onchangeSemakUpdate(idpermohonan){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewSegera";
	document.${formName}.command2.value = "kemaskiniFlagBorangI";
	document.${formName}.command3.value = "onchangeSemakUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}		
function kemaskiniFlagBorangI(idmmk){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_mmk.value = idmmk;
	document.${formName}.command.value = "viewSegera";
	document.${formName}.command2.value = "kemaskiniFlagBorangI";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function simpanFlagBorangI(idmmk){

	if(document.${formName}.socSemakanBorangI.value == ""){
		alert("Sila pilih \"Status Semakan Borang I\" terlebih dahulu.");
  		document.${formName}.socSemakanBorangI.focus(); 
		return;
	}
	else{		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_mmk.value = idmmk;
		document.${formName}.command.value = "viewSegera";
		document.${formName}.command2.value = "simpanFlagBorangI";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
		document.${formName}.submit();
	}
}
function onchangeSemak(idpermohonan){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewSegera";
	document.${formName}.command2.value = "onchangeSemak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.tabId.value = "1";
	document.${formName}.command.value = "viewMaklumatSegera";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.tabId.value = "1";
	document.${formName}.command.value = "viewMaklumatSegera";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
</script>


<!-- FOR TAB -->
<script>
function penjanaanBorangJ(id_permohonan) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "penjanaanBorangJ";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function viewSegera(id_permohonan) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewSegera";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
function viewMaklumatSegera(id_permohonan) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewMaklumatSegera";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera";
	document.${formName}.submit();
}
</script>



<!-- FOR CHECKBOX -->
<script language="javascript">
var checked = false;
function checkALL() {

	 if (document.${formName}.checkall.checked == true){
		 document.${formName}.socSemakanBorangI.value = 1;
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = true;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = true;
	            }
	        }
	 } else {
		 document.${formName}.socSemakanBorangI.value = 2;
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
		document.${formName}.socSemakanBorangI.value = 2;
	}
	else{
		document.${formName}.checkall.checked = true;
		document.${formName}.socSemakanBorangI.value = 1;
	}       
}
</script>



<!-- FOR OTHERS -->
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