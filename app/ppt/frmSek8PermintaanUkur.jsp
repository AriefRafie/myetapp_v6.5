
#parse("app/ppt/frmLabelSet.jsp")
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

#foreach($data in $dataHakmilikBorangK)
	#set($lblMukim=$data.nama_mukim)
	#set($lblJenisHakmilik=$data.jenis_hakmilik)
	#set($lblNoHakmilik=$data.no_hakmilik)
	#set($lblTarikhDaftar=$data.tarikh_daftar)
	#set($lblNoLot=$data.no_lotpt)
	#set($lblTarikhBorangK=$data.tarikh_borangk)
	#set($lblLuasAmbil=$data.luas_ambil)
	#set($lblUnitLuas=$data.unitByKategori)
	#set($lblLuasLot=$data.luas_lot)
	#set($countBakiLuas=$data.baki_luas_asal)
	#set($lblNoSyit=$data.no_syit)
#end

<fieldset id="top">
<legend><strong>Permintaan Ukur</strong></legend>
<center>

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>
	
	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>

	<div id="TabbedPanels1" class="TabbedPanels">		
  		<ul class="TabbedPanelsTabGroup">
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);maklumatPermintaanUkur('$!id_hakmilik')" tabindex="1">Penyediaan PU</li>
			#if($showAllTab=="yes")
			<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);maklumatPenyelesaian('$!id_permintaanukur')" tabindex="1">Penyelesaian SA/PA dan B1</li>
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(2);pelarasanLuas('$!id_permintaanukur')" tabindex="1">Pelarasan Luas</li>
    			#if($valTab!="" && $valTab!="3")
    				#if($valTab=="1")
    				<li class="TabbedPanelsTab" onClick="javascript:setSelected(3);pelarasanPB('$!id_permintaanukur')" tabindex="1">Pelarasan</li>
    				#end
				<li class="TabbedPanelsTab" onClick="javascript:setSelected(4);susulan('$!id_permintaanukur','$!valTab')" tabindex="1">Surat / Susulan</li>
				<li class="TabbedPanelsTab" onClick="javascript:setSelected(5);screenUpload('$!id_permintaanukur')" tabindex="1">Dokumen</li>	
				#end
			#end
    	</ul>
  	
  		<div class="TabbedPanelsContentGroup">


			<!-- START TAB 1 -->
  			<div class="TabbedPanelsContent">
  			
  				<br/>
  				
  				#if($mode=="new")
  				<fieldset>
  				<legend><strong>Sijil Pengecualian Bayaran Ukur</strong></legend>
  					
  					<table width="100%" border="0">
  						<tr>
  							<td width="1%">&nbsp;</td>
  							<td width="20%">Tarikh Surat PTD</td>
  							<td width="1%">:</td>
  							<td width="68%"><input name="txdTarikhSuratPTG" id="txdTarikhSuratPTG" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratPTG',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. Syit</td>
  							<td>:</td>
  							<td><input type="text" name="txtNoPelan" id="txtNoPelan" value="$!lblNoSyit" size="33" maxlength="40" ></td>
  						</tr>
  					
  						#if($listNoPelan_size!=0)
  						<tr>
  							<td colspan="3">&nbsp;</td>
  							<td>
  							
  							#if($listNoPelan_size > 5)
        					<div style="width:100%;height:100;overflow:auto"> 
        					#end
        		
  							<table width="55%" border="0">
    				
    							<tr class="table_header">
    								<td width="4%" align="center"><input type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></td>
        							<td width="10%" align="center"><b>No</b></td>
    								<td width="64%">&nbsp;<b>No. Syit</b></td>
    								<td width="22%">&nbsp;</td>
    							</tr>
    				
    							#if($listNoPelan_size!=0)
            					#foreach($listPL in $listNoPelan)
              						#set( $i = $velocityCount )
         							#if ( ($i % 2) != 1 )
              							#set( $row = "row2" )
         							#else
               						#set( $row = "row1" )
         							#end
                    
               					<tr>
               						<td class="$row" align="center"><input type="checkbox" name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listPL.id_nopelan"></td>
                   					<td class="$row" align="center">$!listPL.bil</td>
                   					<td class="$row">&nbsp;$!listPL.no_pelan</td>        
                   					<td class="$row" align="center"><input type="button" name="cmdHapus" value ="Hapus" onClick="hapusNoPelan('$!listPL.id_nopelan','new')"></td>               		
              					<tr>
           						#end
        		
        						#else
                    			<tr>
                    				<td colspan="2">Tiada rekod</td>
                   				</tr>
        						#end
    				
    						</table>
    						
    						#if($listNoPelan_size > 5)
        					</div>
        					#end
    						
    						</td>
  						</tr>
  						#end
  						
  						
  					</table>
  					
  				</fieldset>
  				
  				<br/>
  				
  				<fieldset>
  				<legend><strong>Borang PU</strong></legend>
  				
  					<table width="100%" border="0">
  						<tr>
  							<td width="1%">&nbsp;</td>
  							<td width="20%">Tarikh Hantar Ke JUPEM</td>
  							<td width="1%">:</td>
  							<td width="68%"><input name="txdTarikhHantarJUPEM" id="txdTarikhHantarJUPEM" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantarJUPEM',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. PU</td>
  							<td>:</td>
  							<td><input type="text" name="txtNoPU" id="txtNoPU" value="" size="20" maxlength="20"   ></td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tarikh Borang PU</td>
  							<td>:</td>
  							<td><input name="txdTarikhBorangPU" id="txdTarikhBorangPU" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangPU',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  					</table>
  				
  				</fieldset>
  				#end
  				
  				#if($mode=="view")
  				
  				#foreach($dataPU in $dataPermintaanUkur)
	    			<!-- set($txtNoPelan=$dataPU.no_pelan) -->
	    			#set($txtNoPU=$dataPU.no_pu)
	    			#set($txdTarikhSuratPTG=$dataPU.tarikh_surat_ptg)
	    			#set($txdTarikhHantarJUPEM=$dataPU.tarikh_hantar_jupem)
	    			#set($txdTarikhBorangPU=$dataPU.tarikh_pu)
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
  				<legend><strong>Sijil Pengecualian Bayaran Ukur</strong></legend>
  					
  					<table width="100%" border="0">
  						<tr>
  							<td width="1%">&nbsp;</td>
  							<td width="20%">Tarikh Surat PTD</td>
  							<td width="1%">:</td>
  							<td width="68%"><input $disability $disabilityx name="txdTarikhSuratPTG" id="txdTarikhSuratPTG" size="11" type="text" value="$!txdTarikhSuratPTG" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratPTG',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  						
  						#if(($isEdit=="no" && $listNoPelan_size==0) || $isEdit=="yes")
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. Syit</td>
  							<td>:</td>
  							<td><input $disability $disabilityx type="text" name="txtNoPelan" id="txtNoPelan" value="" size="33" maxlength="40" ></td>
  						</tr>
  						#end
  						
  						#if($listNoPelan_size!=0)
  						<tr>
  							#if(($isEdit=="no" && $listNoPelan_size!=0))
  							<td valign="top">&nbsp;</td>
  							<td valign="top">No. Syit</td>
  							<td valign="top">:</td>
  							#else
  							<td colspan="3">&nbsp;</td>
  							#end
  							
  							<td>
  							
  							#if($listNoPelan_size > 5)
        					<div style="width:100%;height:100;overflow:auto"> 
        					#end
        		
  							<table width="55%" border="0">
    				
    							<tr class="table_header">
    								<td width="4%" align="center"><input $disability1 type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></td>
        							<td width="10%" align="center"><b>No</b></td>
    								<td width="64%">&nbsp;<b>No. Syit</b></td>
    								#if($isEdit=="yes")<td width="22%">&nbsp;</td>#end
    							</tr>
    				
    							#if($listNoPelan_size!=0)
            					#foreach($listPL in $listNoPelan)
              						#set( $i = $velocityCount )
         							#if ( ($i % 2) != 1 )
              							#set( $row = "row2" )
         							#else
               						#set( $row = "row1" )
         							#end
         						
         							#if($listPL.flag != 0)
                    					#set($check="checked")
                   				 	#else
                    					#set($check="")
                    				#end
                    
               					<tr>
               						<td class="$row" align="center"><input $disability1 type="checkbox" $check name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listPL.id_nopelan"></td>
                   					<td class="$row" align="center">$!listPL.bil</td>
                   					<td class="$row">&nbsp;$!listPL.no_pelan</td>        
                   					#if($isEdit=="yes")<td class="$row" align="center"><input type="button" name="cmdHapus" value ="Hapus" onClick="hapusNoPelan('$!listPL.id_nopelan','view')"></td>#end                  		
              					<tr>
           						#end
        		
        						#else
                    			<tr>
                    				<td colspan="2">Tiada rekod</td>
                   				</tr>
        						#end
    				
    						</table>
    						
    						#if($listNoPelan_size > 5)
        					</div>
        					#end
    						
    						</td>
  						</tr>
  						#end
  						
  						
  					</table>
  					
  				</fieldset>
  				
  				<br/>
  				
  				<fieldset>
  				<legend><strong>Borang PU</strong></legend>
  				
  					<table width="100%" border="0">
  						<tr>
  							<td width="1%">&nbsp;</td>
  							<td width="20%">Tarikh Hantar Ke JUPEM</td>
  							<td width="1%">:</td>
  							<td width="68%"><input $disability $disabilityx name="txdTarikhHantarJUPEM" id="txdTarikhHantarJUPEM" size="11" type="text" value="$!txdTarikhHantarJUPEM" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantarJUPEM',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. PU</td>
  							<td>:</td>
  							<td><input $disability $disabilityx type="text" name="txtNoPU" id="txtNoPU" value="$!txtNoPU" size="20" maxlength="20"   ></td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tarikh Borang PU</td>
  							<td>:</td>
  							<td><input $disability $disabilityx name="txdTarikhBorangPU" id="txdTarikhBorangPU" size="11" type="text" value="$!txdTarikhBorangPU" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangPU',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  					</table>
  				
  				</fieldset>
  				#end
  				
  				<br/>
  				
  				<fieldset>
  				<legend><strong>Maklumat Hakmilik Asal</strong></legend>
  				
  					<table width="100%" border="0">
  						<tr>
  							<td width="1%">&nbsp;</td>
  							<td width="20%">Mukim</td>
  							<td width="1%">:</td>
  							<td width="68%">$!lblMukim</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Jenis Hakmilik</td>
  							<td>:</td>
  							<td>$!lblJenisHakmilik.toUpperCase()</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. Hakmilik</td>
  							<td>:</td>
  							<td>$!lblNoHakmilik</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. Lot / No. PT</td>
  							<td>:</td>
  							<td>$!lblNoLot</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tarikh Borang K Didaftarkan</td>
  							<td>:</td>
  							<td>$!lblTarikhBorangK</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Luas Diambil</td>
  							<td>:</td>
  							<td>$!lblLuasAmbil&nbsp;$!lblUnitLuas</td>
  						</tr>
  					</table>
  					
  				</fieldset>
  				
  				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanMaklumatPU('$!id_hakmilik','$!id_permintaanukur','$!mode')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReportPU')" />
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniMaklumatPU('$!id_permintaanukur')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanMaklumatPU('$!id_hakmilik','$!id_permintaanukur','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniPU('$!id_hakmilik')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
						</td>
					</tr>
				</table>
  				
  				
  				<fieldset id="tableReportPU" style="display:none;">
				<legend><strong>SENARAI LAPORAN</strong></legend>
					<table width="100%" border="0" cellspacing="2" cellpadding="2">
				      <tr>
						 <td><a href="#" onClick="javascript:cetakCoveringPU('$!id_permintaanukur','$!id_permohonan')"><font color="blue">Covering Borang Permintaan Ukur</font></a></td>
					  </tr>
					  <tr>
						 <td><a href="#" onClick="javascript:cetakBorangPU('$!id_permintaanukur','$!id_permohonan')"><font color="blue">Borang Permintaan Ukur</font></a></td>
					  </tr>
					  <tr>
						 <td><a href="#" onClick="javascript:cetakLampiranAPU('$!id_permintaanukur','$!id_permohonan')"><font color="blue">Lampiran A</font></a></td>
					  </tr>
					  <tr>
						 <td><a href="#" onClick="javascript:cetakLampiranBPU('$!id_permohonan')"><font color="blue">Lampiran B</font></a></td>
					  </tr>
					  <tr>
						 <td><a href="#" onClick="javascript:cetakCoveringSijilPU('$!id_permintaanukur','$!id_permohonan')"><font color="blue">Covering Mohon Sijil Pengecualian Bayaran Ukur</font></a></td>
					  </tr>
					  <tr>
						 <td><a href="#" onClick="javascript:cetakMinitSijilPU('$!id_permintaanukur','$!id_permohonan')"><font color="blue">Minit Mohon Sijil Pengecualian Bayaran Ukur</font></a></td>
					  </tr>
				    </table>
				</fieldset>
  				
  			</div>
  			<!-- END TAB 1 -->
  			
  			
#if($showAllTab=="yes") 			
  			<!-- START TAB 2 -->
  			<div class="TabbedPanelsContent">
  			
  				<br/>
  				
  				<fieldset>
  					
  					#if($mode=="new")
  					<table width="100%" border="0">
  						<tr>
  							<td width="1%">&nbsp;</td>
  							<td width="25%">Tarikh Terima PA (Pelan Akui) dan B1</td>
  							<td width="1%">:</td>
  							<td width="73%"><input name="txdTarikhTerimaPA" id="txdTarikhTerimaPA" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaPA',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  						<tr>
  							<td><font color="red">*</font></td>
  							<td>No. PA dan B1</td>
  							<td>:</td>
  							<td><input type="text" name="txtNoPA" id="txtNoPA" value="" size="20" maxlength="20"   ></td>
  						</tr>
  						<tr>
  							<td></td>
  							<td>No. LOT Baru</td>
  							<td>:</td>
  							<td><input type="text" name="txtLotBaru" id="txtLotBaru" value="" size="20" maxlength="20"   ></td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tarikh Terima SA (Surihan Alih)</td>
  							<td>:</td>
  							<td><input name="txdTarikhTerimaSA" id="txdTarikhTerimaSA" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaSA',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tarikh Penyelesaian</td>
  							<td>:</td>
  							<td><input name="txdTarikhPenyelesaian" id="txdTarikhPenyelesaian" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPenyelesaian',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td valign="top">Catatan</td>
  							<td valign="top">:</td>
  							<td valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="4" cols="40%" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,400);" ></textarea></td>
  						</tr>
  					</table>
  					#end
  					
  					
  					#if($mode=="view")
  					
  					#foreach($dataPU in $dataPermintaanUkur)
	    				#set($txdTarikhTerimaPA=$dataPU.tarikh_terima_pa)
	    				#set($txtNoPA=$dataPU.no_pa)
	    				#set($txdTarikhTerimaSA=$dataPU.tarikh_terima_sa)
	    				#set($txdTarikhPenyelesaian=$dataPU.tarikh_selesai)
	    				#set($txtCatatan=$dataPU.catatan)
	    				#set($txtLotBaru=$dataPU.no_lot_baru)
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
  							<td width="25%">Tarikh Terima PA (Pelan Akui) dan B1</td>
  							<td width="1%">:</td>
  							<td width="73%"><input $disability $disabilityx name="txdTarikhTerimaPA" id="txdTarikhTerimaPA" size="11" type="text" value="$!txdTarikhTerimaPA" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaPA',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  						<tr>
  							<td><font color="red">$!M</font></td>
  							<td>No. PA dan B1</td>
  							<td>:</td>
  							<td><input $disability $disabilityx type="text" name="txtNoPA" id="txtNoPA" value="$!txtNoPA" size="20" maxlength="20"   ></td>
  						</tr>
  						<tr>
  							<td></td>
  							<td>No. LOT Baru</td>
  							<td>:</td>
  							<td><input $disability $disabilityx type="text" name="txtLotBaru" id="txtLotBaru" value="$!txtLotBaru" size="20" maxlength="20"   ></td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tarikh Terima SA (Surihan Alih)</td>
  							<td>:</td>
  							<td><input $disability $disabilityx name="txdTarikhTerimaSA" id="txdTarikhTerimaSA" size="11" type="text" value="$!txdTarikhTerimaSA" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaSA',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tarikh Penyelesaian</td>
  							<td>:</td>
  							<td><input $disability $disabilityx name="txdTarikhPenyelesaian" id="txdTarikhPenyelesaian" size="11" type="text" value="$!txdTarikhPenyelesaian" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPenyelesaian',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td valign="top">Catatan</td>
  							<td valign="top">:</td>
  							<td valign="top"><textarea $disability $disabilityx name="txtCatatan" id="txtCatatan" rows="4" cols="40%" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,400);" >$!txtCatatan</textarea></td>
  						</tr>
  					</table>
  					#end
  					
  				</fieldset>
  			
  				
  				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updatePenyelesaian('$!id_permintaanukur')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPenyelesaian('$!id_permintaanukur')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updatePenyelesaian('$!id_permintaanukur')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniPenyelesaian('$!id_permintaanukur')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
						</td>
					</tr>
				</table>
  			
  			
  			</div>
  			<!-- END TAB 2 -->
  			
  			
  			
  			<!-- START TAB 3 -->
  			<div class="TabbedPanelsContent">
  			
  				<br/>
  				
  				<fieldset>
  				
  				#if($mode=="new")
  				
  				#set($txtKeluasanPU = "")
  				
  				<table width="100%" border="0">
  					<tr>
  						<td width="1%"><font color="red">*</font></td>
  						<td width="25%">Luas PA</td>
  						<td width="1%"><b>A</b></td>
  						<td width="1%">:</td>
  						<td width="72%"><input type="text" name="txtKeluasanPU" id="txtKeluasanPU" value="$!txtKeluasanPU" size="10" style="text-align:right" maxlength="11" onblur="validateLuas(this,this.value,'$!txtKeluasanPU')" onkeyup="validateNilai(this,this.value);kiraBezaLuas()" >&nbsp;<b>Hektar</b></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Luas Asal</td>
  						<td><b>B</b></td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="lblLuasAsal" id="lblLuasAsal" value="$!lblLuasLot" size="10" style="text-align:right" maxlength="8" onkeyup="validateNilai(this,this.value)" >&nbsp;<b>Hektar</b></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Luas Borang K</td>
  						<td><b>C</b></td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="lblLuasAmbil" id="lblLuasAmbil" value="$!lblLuasAmbil" size="10" style="text-align:right" maxlength="8" onkeyup="validateNilai(this,this.value)" >&nbsp;<b>Hektar</b></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Baki Luas Asal (B-C)</td>
  						<td><b>D</b></td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="countBakiLuas" id="countBakiLuas" value="$!countBakiLuas" size="10" style="text-align:right" maxlength="8" onkeyup="validateNilai(this,this.value)" >&nbsp;<b>Hektar</b></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Beza Luas (A-D)</td>
  						<td>&nbsp;</td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="countBezaLuas" id="countBezaLuas" value="" size="10" style="text-align:right" maxlength="8" onkeyup="validateNilai(this,this.value)" >&nbsp;<b>Hektar</b>
  							<!-- &nbsp;<input type="text" readonly class="disabled" size="10" name="lblBezaLuas" value="" /> -->
  						</td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Jenis Pelarasan</td>
  						<td>&nbsp;</td>
  						<td>:</td>
  						<td><select disabled class="disabled" name="sorJenisPelarasan" style="width:200px">
      		
      						<option value="">SILA PILIH</option>
      						<option value="1">PAMPASAN TAMBAHAN</option>
      						<option value="2">PENGURANGAN PAMPASAN</option>
      						<option value="3">KEKAL</option>
      						
						</select></td>		
  					</tr>
  				</table>
  				
  				<input type="hidden" name="hdJenisPelarasan">
  				
  				#end
  				
  				
  				#if($mode=="view")
  				
  				#foreach($dataPU in $dataPermintaanUkur)
	    			#set($txtKeluasanPU=$dataPU.luas_pu)
	    			#set($countBezaLuas=$dataPU.beza_luas)
	    			#set($sorJenisPelarasan=$dataPU.jenis_pelarasan)
	    			#set($lblBezaLuas=$dataPU.lblBezaLuas)
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
  						<td width="25%">Luas PA</td>
  						<td width="1%"><b>A</b></td>
  						<td width="1%">:</td>
  						<td width="72%"><input $disability $disabilityx type="text" name="txtKeluasanPU" id="txtKeluasanPU" value="$!txtKeluasanPU" size="10" style="text-align:right" maxlength="12" onblur="validateLuas(this,this.value,'$!txtKeluasanPU')" onkeyup="validateNilai(this,this.value);kiraBezaLuas()" >&nbsp;<b>Hektar</b></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Luas Asal</td>
  						<td><b>B</b></td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="lblLuasAsal" id="lblLuasAsal" value="$!lblLuasLot" size="10" style="text-align:right" maxlength="8" onkeyup="validateNilai(this,this.value)" >&nbsp;<b>Hektar</b></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Luas Borang K</td>
  						<td><b>C</b></td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="lblLuasAmbil" id="lblLuasAmbil" value="$!lblLuasAmbil" size="10" style="text-align:right" maxlength="8" onkeyup="validateNilai(this,this.value)" >&nbsp;<b>Hektar</b></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Baki Luas Asal (B-C)</td>
  						<td><b>D</b></td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="countBakiLuas" id="countBakiLuas" value="$!countBakiLuas" size="10" style="text-align:right" maxlength="8" onkeyup="validateNilai(this,this.value)" >&nbsp;<b>Hektar</b></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Beza Luas (A-D)</td>
  						<td>&nbsp;</td>
  						<td>:</td>
  						<td><input readonly class="disabled" type="text" name="countBezaLuas" id="countBezaLuas" value="$!countBezaLuas" size="10" style="text-align:right" maxlength="8" onkeyup="validateNilai(this,this.value)" >&nbsp;<b>Hektar</b>
  							<!-- &nbsp;<input type="text" readonly class="disabled" size="10" name="lblBezaLuas" value="$!lblBezaLuas" /> -->
  						</td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Jenis Pelarasan</td>
  						<td>&nbsp;</td>
  						<td>:</td>
  						<td><select disabled class="disabled" name="sorJenisPelarasan" style="width:217px">
      		
      						#if($sorJenisPelarasan=="1")
      						<option value="1">PAMPASAN TAMBAHAN</option>
      						<option value="">SILA PILIH</option>
      						<option value="2">PENGURANGAN PAMPASAN</option>
      						<option value="3">KEKAL</option>
      						#elseif($sorJenisPelarasan=="2")
      						<option value="2">PENGURANGAN PAMPASAN</option>
      						<option value="">SILA PILIH</option>
      						<option value="1">PAMPASAN TAMBAHAN</option>
      						<option value="3">KEKAL</option>
      						#elseif($sorJenisPelarasan=="3")
      						<option value="3">KEKAL</option>
      						<option value="">SILA PILIH</option>
      						<option value="1">PAMPASAN TAMBAHAN</option>
      						<option value="2">PENGURANGAN PAMPASAN</option>	
      						#else
      						<option value="">SILA PILIH</option>
      						<option value="1">PAMPASAN TAMBAHAN</option>
      						<option value="2">PENGURANGAN PAMPASAN</option>
      						<option value="3">KEKAL</option>
      						#end
      						
						</select></td>
  					</tr>
  				</table>
  				
  				<input type="hidden" name="hdJenisPelarasan" value="$!sorJenisPelarasan">
  				
  				#end
  				
  				</fieldset>
  			
  				
  				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updatePelarasanLuas('$!id_permintaanukur')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPelarasanLuas('$!id_permintaanukur')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updatePelarasanLuas('$!id_permintaanukur')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniPelarasanLuas('$!id_permintaanukur')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
						</td>
					</tr>
				</table>
  			
  			</div>
  			<!-- END TAB 3 -->
  			
  			
  			#if($valTab=="1")
  			<!-- START TAB 4 -->
  			<div class="TabbedPanelsContent">
  			
  				<br/>
  				
  				#if($showDetail=="yes")

  				#foreach($dataPB in $dataDetailPB)
  					#set($lblNamaPB=$dataPB.nama_pb)
  					#set($lblJenisNoPB=$dataPB.jenis_nopb)
  					#set($lblNoPB=$dataPB.no_pb)
  					#set($lblSyerAtas=$dataPB.syer_atas)
  					#set($lblSyerBawah=$dataPB.syer_bawah)
  					#set($lblJenisKepentingan=$dataPB.jenis_pb)
  					#set($lblTarikhBayarPampasan=$dataPB.tarikh_akhir_bayaragensi)
  					#set($txtPenama=$dataPB.penama)
  					#set($txtUlasan=$dataPB.ulasan)
  					#set($txdTarikhSedia=$dataPB.tarikh_sedia_bayaran)
  					#set($txtPampasanTanah=$dataPB.pampasan_tanah)
  					#set($txtFaedahSelepas=$dataPB.faedah_selepas_pu)
  					#set($txtFaedahSebelum=$dataPB.faedah_sebelum_pu)
  					#set($txtJumlahPelarasan=$dataPB.jumlah_pelarasan)
  					#set($txtTempoh=$dataPB.tempoh_lewat)
  				#end
  				
  				<fieldset>
  				<table width="100%" border="0">
  					<tr>
  						<td width="1%">&nbsp;</td>
  						<td width="35%">Nama PB</td>
  						<td width="1%">:</td>
  						<td width="63%">$!lblNamaPB</td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Jenis No. PB</td>
  						<td>:</td>
  						<td>$!lblJenisNoPB</td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>No. PB</td>
  						<td>:</td>
  						<td>$!lblNoPB</td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Bahagian</td>
  						<td>:</td>
  						#if($lblSyerAtas!="")
  						<td>$!lblSyerAtas / $!lblSyerBawah</td>
  						#else
  						<td>Tiada Bahagian</td>
  						#end
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Jenis Kepentingan</td>
  						<td>:</td>
  						<td>$!lblJenisKepentingan</td>
  					</tr>
  					
  					#if($mode=="new")
  					<tr>
  						<td><font color="red">*</font></td>
  						<td>Penama / Penerima</td>
  						<td>:</td>
  						<td><input type="text" name="txtPenama" id="txtPenama" value="$!lblNamaPB" size="33" maxlength="80"   ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td valign="top">Catatan (Sekiranya Penama Adalah Bukan PB Ini)</td>
  						<td valign="top">:</td>
  						<td valign="top"><textarea name="txtUlasan" id="txtUlasan" rows="3" cols="30%" onKeyUp="textCounter(this.form.txtUlasan,this.form.remLen1,1500);" onKeyDown="textCounter(this.form.txtUlasan,this.form.remLen1,1500);"  ></textarea></td>
  					</tr>
  					<!-- <tr>
  						<td>&nbsp;</td>
  						<td>Tarikh Bayaran Pampasan</td>
  						<td>:</td>
  						<td>$!lblTarikhBayarPampasan</td>
  					</tr> -->
  					<tr>
  						<td>&nbsp;</td>
  						<td>Tarikh Sedia Bayaran</td>
  						<td>:</td>
  						<td><input name="txdTarikhSedia" id="txdTarikhSedia" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSedia',false,'dmy');">&nbsp;$!frmtdate</td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Pelarasan Pampasan Tanah</td>
  						<td>:</td>
  						<td><input type="text" name="txtPampasanTanah" id="txtPampasanTanah" value="$!PampasanTanah" size="11" style="text-align:right" maxlength="11" onblur="validateModal(this,this.value,'$!PampasanTanah')" onkeyup="validateNilai(this,this.value)" ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Faedah Sebelum / Pada 19.1.1984</td>
  						<td>:</td>
  						<td><input type="text" name="txtFaedahSebelum" id="txtFaedahSebelum" value="" size="11" style="text-align:right" maxlength="11" onblur="validateModal(this,this.value,'$!txtFaedahSebelum')" onkeyup="validateNilai(this,this.value)" ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Faedah Selepas / Pada 20.1.1984</td>
  						<td>:</td>
  						<td><input type="text" name="txtFaedahSelepas" id="txtFaedahSelepas" value="" size="11" style="text-align:right" maxlength="11" onblur="validateModal(this,this.value,'$!txtFaedahSelepas')" onkeyup="validateNilai(this,this.value)" ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Jumlah Keseluruhan Pelarasan</td>
  						<td>:</td>
  						<td><input type="text" name="txtJumlahPelarasan" id="txtJumlahPelarasan" value="$!PampasanTanah" style="text-align:right" size="11" maxlength="11" onblur="validateModal(this,this.value,'$!PampasanTanah')" onkeyup="validateNilai(this,this.value)" ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Tempoh Lewat (Hari)</td>
  						<td>:</td>
  						<td><input type="text" name="txtTempoh" id="txtTempoh" value="" size="2" maxlength="2" onkeyup="validateNumber(this,this.value)" ></td>
  					</tr>
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
  					
  					<tr>
  						<td><font color="red">$!M</font></td>
  						<td>Penama / Penerima</td>
  						<td>:</td>
  						<td><input $disability $disabilityx type="text" name="txtPenama" id="txtPenama" value="$!txtPenama" size="33" maxlength="80"   ></td>
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td valign="top">Catatan (Sekiranya Penama Adalah Bukan PB Ini)</td>
  						<td valign="top">:</td>
  						<td valign="top"><textarea $disability $disabilityx name="txtUlasan" id="txtUlasan" rows="3" cols="30%" onKeyUp="textCounter(this.form.txtUlasan,this.form.remLen1,1500);" onKeyDown="textCounter(this.form.txtUlasan,this.form.remLen1,1500);" >$!txtUlasan</textarea></td>
  					</tr>
  					<!-- <tr>
  						<td>&nbsp;</td>
  						<td>Tarikh Bayaran Pampasan</td>
  						<td>:</td>
  						<td>$!lblTarikhBayarPampasan</td>
  					</tr> --> 					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Tarikh Sedia Bayaran</td>
  						<td>:</td>
  						<td><input $disability $disabilityx name="txdTarikhSedia" id="txdTarikhSedia" size="11" type="text" value="$!txdTarikhSedia" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSedia',false,'dmy');">&nbsp;$!frmtdate#end</td>
  					</tr>
  					
  					<tr>
  						<td>&nbsp;</td>
  						<td>Pelarasan Pampasan Tanah</td>
  						<td>:</td>
  						#if($isEdit=="no")
  						<td><input $disability $disabilityx type="text" name="txtPampasanTanah" id="txtPampasanTanah" value="$!Utils.format2Decimal($txtPampasanTanah)" size="11" style="text-align:right" maxlength="11" onblur="validateModal(this,this.value,'$!txtPampasanTanah')" onkeyup="validateNilai(this,this.value)" ></td>
  						#else
  						<td><input $disability $disabilityx type="text" name="txtPampasanTanah" id="txtPampasanTanah" value="$!txtPampasanTanah" size="11" style="text-align:right" maxlength="11" onblur="validateModal(this,this.value,'$!txtPampasanTanah')" onkeyup="validateNilai(this,this.value)" ></td>
  						#end
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Faedah Sebelum / Pada 19.1.1984</td>
  						<td>:</td>
  						#if($isEdit=="no")
  						<td><input $disability $disabilityx type="text" name="txtFaedahSebelum" id="txtFaedahSebelum" value="$!Utils.format2Decimal($txtFaedahSebelum)" size="11" style="text-align:right" maxlength="11" onblur="validateModal(this,this.value,'$!txtFaedahSebelum')" onkeyup="validateNilai(this,this.value)" ></td>
  						#else
  						<td><input $disability $disabilityx type="text" name="txtFaedahSebelum" id="txtFaedahSebelum" value="$!txtFaedahSebelum" size="11" style="text-align:right" maxlength="11" onblur="validateModal(this,this.value,'$!txtFaedahSebelum')" onkeyup="validateNilai(this,this.value)" ></td>
  						#end
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Faedah Selepas / Pada 20.1.1984</td>
  						<td>:</td>
  						#if($isEdit=="no")
  						<td><input $disability $disabilityx type="text" name="txtFaedahSelepas" id="txtFaedahSelepas" value="$!Utils.format2Decimal($txtFaedahSelepas)" size="11" style="text-align:right" maxlength="11" onblur="validateModal(this,this.value,'$!txtFaedahSelepas')" onkeyup="validateNilai(this,this.value)" ></td>
  						#else
  						<td><input $disability $disabilityx type="text" name="txtFaedahSelepas" id="txtFaedahSelepas" value="$!txtFaedahSelepas" size="11" style="text-align:right" maxlength="11" onblur="validateModal(this,this.value,'$!txtFaedahSelepas')" onkeyup="validateNilai(this,this.value)" ></td>
  						#end
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Jumlah Keseluruhan Pelarasan</td>
  						<td>:</td>
  						#if($isEdit=="no")
  						<td><input $disability $disabilityx type="text" name="txtJumlahPelarasan" id="txtJumlahPelarasan" value="$!Utils.format2Decimal($txtJumlahPelarasan)" style="text-align:right" size="11" maxlength="11" onblur="validateModal(this,this.value,'$!txtJumlahPelarasan')" onkeyup="validateNilai(this,this.value)" ></td>
  						#else
  						<td><input $disability $disabilityx type="text" name="txtJumlahPelarasan" id="txtJumlahPelarasan" value="$!txtJumlahPelarasan" style="text-align:right" size="11" maxlength="11" onblur="validateModal(this,this.value,'$!txtJumlahPelarasan')" onkeyup="validateNilai(this,this.value)" ></td>
  						#end
  					</tr>
  					<tr>
  						<td>&nbsp;</td>
  						<td>Tempoh Lewat (Hari)</td>
  						<td>:</td>
  						<td><input $disability $disabilityx type="text" name="txtTempoh" id="txtTempoh" value="$!txtTempoh" size="2" maxlength="2" onkeyup="validateNumber(this,this.value)" ></td>
  					</tr>
  					#end
  					
  				</table>
  				</fieldset>
  				
  				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updatePelarasanPB('$!id_permintaanukur')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPelarasanPB('$!id_permintaanukur')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updatePelarasanPB('$!id_permintaanukur')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniPelarasanPB('$!id_permintaanukur','$!id_hakmilikpb')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
						</td>
					</tr>
				</table>
				
  				#end
  				
  				
  				<fieldset>
    			<legend><strong>Senarai Pihak Berkepentingan</strong></legend>	
    
    			<table width="100%" border="0">
    				<tr>
    					<td width="70%" align="right">Carian Nama PB :&nbsp;<input type="text" name="carianPBPelarasan" value="$!carianPBPelarasan" maxlength="20" size="20"   ><a href="javascript:cariPBPelarasan('$!id_permintaanukur','$!id_hakmilikpb','$!showDetail')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanPBPelarasan('$!id_permintaanukur','$!id_hakmilikpb','$!showDetail')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    			</table>
    			
    			
    			#if($saiz_pb > 5)
        		<div style="width:100%;height:100;overflow:auto"> 
        		#end
            	
    			<table width="100%" border="0">
    				<tr class="table_header">
    					<td width="4%" align="center"><b>No</b></td>
    					<td width="76%">&nbsp;<b>Nama PB</b></td>
    					<td width="20%">&nbsp;<b>No. PB</b></td>
    					
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
                   		<td class="$row">&nbsp;<a href="javascript:selectPB('$!listPB.id_hakmilikpb')"><font color="blue">$!listPB.nama_pb</font></a></td>
                   		<td class="$row">&nbsp;$!listPB.no_pb</td>                   		
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
  			
  			</div>
  			<!-- END TAB 4 -->
  			#end
  			
  			
  			<!-- START TAB 5 -->
  			<div class="TabbedPanelsContent">
  			
  			
  			#if($valTab=="1")
  				
  				<br/>

  				<fieldset>
  				<legend><strong>Surat Meminta Pampasan Tambahan</strong></legend>
  				
  					#if($mode=="new")
  					<table width="100%" border="0">
  					<tr>
  					
  						<!-- TABLE KIRI -->
  						<td valign="top" width="50%"><table width="100%" border="0">
  						<tr>
  							<td width="1%">&nbsp;</td>
  							<td width="40%">No. Rujukan</td>
  							<td width="1%">:</td>
  							<td width="58%"><input type="text" name="txtBilSurat" id="txtBilSurat" value="$!no_subjaket" size="28" maxlength="50" ></td>
  						</tr>
  						<tr>
  							<td><font color="red">*</font></td>
  							<td>Tarikh Surat</td>
  							<td>:</td>
  							<td><input name="txdTarikhSurat" id="txdTarikhSurat" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tarikh Bayaran Dibuat</td>
  							<td>:</td>
  							<td><input name="txdTarikhBayaranTambahan" id="txdTarikhBayaranTambahan" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBayaranTambahan',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  						</table></td>
  					
  					
  						<!-- TABLE KANAN -->
  						<td valign="top" width="50%"><table width="100%" border="0">
  						<tr>
  							<td width="1%">&nbsp;</td>
  							<td width="47%">Tarikh Terima Oleh Agensi</td>
  							<td width="1%">:</td>
  							<td width="51%"><input name="txdTarikhTerimaAgensi" id="txdTarikhTerimaAgensi" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaAgensi',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tempoh Akhir Sedia Cek</td>
  							<td>:</td>
  							<td><input type="text" name="txtTempohCek" id="txtTempohCek" value="" size="20" maxlength="20"   ></td>
  						</tr>
  						</table></td>
  					
  					</tr>
  					</table>
  					#end
  					
  					
  					#if($mode=="view")
  					
  					#foreach($dataPU in $dataPermintaanUkur)
	    				#set($txtBilSurat=$dataPU.bil_surat)
	    				#set($txdTarikhSurat=$dataPU.tarikh_surat_susulan)
	    				#set($txdTarikhTerimaAgensi=$dataPU.tarikh_terima_agensi)
	    				#set($txtTempohCek=$dataPU.tempoh_akhir_cek)
	    				#set($txdTarikhBayaranTambahan=$dataPU.tarikh_bayaran_tambahan)
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
  					
  						
  						<!-- TABLE KIRI -->
  						<td valign="top" width="50%"><table width="100%" border="0">
  						<tr>
  							<td width="1%">&nbsp;</td>
  							<td width="40%">No. Rujukan</td>
  							<td width="1%">:</td>
  							<td width="58%"><input $disability $disabilityx type="text" name="txtBilSurat" id="txtBilSurat" value="$!txtBilSurat" size="28" maxlength="50"   ></td>
  						</tr>
  						<tr>
  							<td><font color="red">$!M</font></td>
  							<td>Tarikh Surat</td>
  							<td>:</td>
  							<td><input $disability $disabilityx name="txdTarikhSurat" id="txdTarikhSurat" size="11" type="text" value="$!txdTarikhSurat" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tarikh Bayaran Dibuat</td>
  							<td>:</td>
  							<td><input $disability $disabilityx name="txdTarikhBayaranTambahan" id="txdTarikhBayaranTambahan" size="11" type="text" value="$!txdTarikhBayaranTambahan" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBayaranTambahan',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  						</table></td>
  					
  					
  						<!-- TABLE KANAN -->
  						<td valign="top" width="50%"><table width="100%" border="0">
  						<tr>
  							<td width="1%">&nbsp;</td>
  							<td width="47%">Tarikh Terima Oleh Agensi</td>
  							<td width="1%">:</td>
  							<td width="51%"><input $disability $disabilityx name="txdTarikhTerimaAgensi" id="txdTarikhTerimaAgensi" size="11" type="text" value="$!txdTarikhTerimaAgensi" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaAgensi',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tempoh Akhir Sedia Cek</td>
  							<td>:</td>
  							<td><input $disability $disabilityx type="text" name="txtTempohCek" id="txtTempohCek" value="$!txtTempohCek" size="20" maxlength="20"   ></td>
  						</tr>
  						</table></td>
  					
  					</tr>
  					</table>
  					#end
  					
  				</fieldset>
  				
  				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updateSusulan('$!id_permintaanukur')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniSusulan('$!id_permintaanukur')">
						<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updateSusulan('$!id_permintaanukur')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniSusulan('$!id_permintaanukur','$!id_hakmilikpb')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
						</td>
					</tr>
				</table>
				
			#end	
				
  				<br/>
  				
  			#if($valTab=="2")	
  				
  				#if($showDetail=="yes")
  				<br/>
  				
  				#foreach($dataPB in $dataDetailPB)
  					#set($lblNamaPB=$dataPB.nama_pb)
  					#set($txtBilSuratPB=$dataPB.bil_surat)
  					#set($txdTarikhSuratPB=$dataPB.tarikh_surat)
  					#set($txtTempohBayar=$dataPB.tempoh_akhir_bayar_semula)
  					#set($sorStatusBayaran=$dataPB.status_bayar)	
  				#end
  				
  				<fieldset>
  				<legend><strong>Surat Dapatkan Semula Bayaran Pampasan Terlebih Bayar</strong></legend>
  					
  					#if($modePB=="new")
  					<table width="100%" border="0">
  						<tr>
  							<td width="1%">&nbsp;</td>
  							<td width="20%">Nama PB</td>
  							<td width="1%">:</td>
  							<td width="78%"><font color="blue">$!lblNamaPB</font></td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. Rujukan</td>
  							<td>:</td>
  							<td><input type="text" name="txtBilSuratPB" id="txtBilSuratPB" value="" size="5" maxlength="10"   ></td>
  						</tr>
  						<tr>
  							<td><font color="red">*</font></td>
  							<td>Tarikh Surat</td>
  							<td>:</td>
  							<td><input name="txdTarikhSuratPB" id="txdTarikhSuratPB" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratPB',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tempoh Akhir Bayar Semula</td>
  							<td>:</td>
  							<td><input type="text" name="txtTempohBayar" id="txtTempohBayar" value="" size="20" maxlength="20"   ></td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Status Bayaran</td>
  							<td>:</td>
  							<td><select name="sorStatusBayaran" style="width:150px">
      		
      							<option value="">SILA PILIH</option>
      							<option value="1">BELUM DIJELASKAN</option>
      							<option value="2">TELAH DIJELASKAN</option>
      							
							</select></td>
  						</tr>
  						<tr><td colspan="4">&nbsp;</td></tr>
  					</table>
  					#end
  					
  					
  					#if($modePB=="view")
  					
  					#if($isEditPB=="no")
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
  							<td width="20%">Nama PB</td>
  							<td width="1%">:</td>
  							<td width="78%"><font color="blue">$!lblNamaPB</font></td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. Rujukan</td>
  							<td>:</td>
  							<td><input type="text" $disability $disabilityx name="txtBilSuratPB" id="txtBilSuratPB" value="$!txtBilSuratPB" size="5" maxlength="10"   ></td>
  						</tr>
  						<tr>
  							<td><font color="red">$!M</font></td>
  							<td>Tarikh Surat</td>
  							<td>:</td>
  							<td><input $disability $disabilityx name="txdTarikhSuratPB" id="txdTarikhSuratPB" size="11" type="text" value="$!txdTarikhSuratPB" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEditPB=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratPB',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tempoh Akhir Bayar Semula</td>
  							<td>:</td>
  							<td><input $disability $disabilityx type="text" name="txtTempohBayar" id="txtTempohBayar" value="$!txtTempohBayar" size="20" maxlength="20"   ></td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Status Bayaran</td>
  							<td>:</td>
  							<td><select $disability1 $disabilityx name="sorStatusBayaran" style="width:150px">
      			
      							#if($sorStatusBayaran=="1")
      							<option value="1">BELUM DIJELASKAN</option>
      							<option value="">SILA PILIH</option>
      							<option value="2">TELAH DIJELASKAN</option>
      							#elseif($sorStatusBayaran=="2")
      							<option value="2">TELAH DIJELASKAN</option>
      							<option value="">SILA PILIH</option>
      							<option value="1">BELUM DIJELASKAN</option>
      							#else
      							<option value="">SILA PILIH</option>
      							<option value="1">BELUM DIJELASKAN</option>
      							<option value="2">TELAH DIJELASKAN</option>
      							#end
	
							</select></td>
  						</tr>
  						<tr><td colspan="4">&nbsp;</td></tr>
  					</table>
  					#end
  					
  				</fieldset>
  				
  				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($modePB=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updatePBSusulan('$!id_award','$!valTab')">
						#end
				
					#if($modePB=="view")
						#if($isEditPB=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPBSusulan('$!id_award','$!valTab')">
						<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport2')" />
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updatePBSusulan('$!id_award','$!valTab')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniPBSusulan('$!id_hakmilikpb','$!valTab')">
						#end
					#end
				
						</td>
					</tr>
				</table>
				
  				#end
  			
  				<br/>
  				<fieldset>
  				
  				#if($showDetail=="no")
  				<legend><strong>Surat Dapatkan Semula Bayaran Pampasan Terlebih Bayar</strong></legend>	
  				#else
    			<legend><strong>Senarai Pihak Berkepentingan</strong></legend>	
    			#end
    			
    			<table width="100%" border="0">
    				<tr>
    					<td width="70%" align="right">Carian Nama PB :&nbsp;<input type="text" name="carianPBSusulan" value="$!carianPBSusulan" maxlength="20" size="20"   ><a href="javascript:cariPBSusulan('$!id_permintaanukur','$!id_hakmilikpb','$!showDetail','$!valTab')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanPBSusulan('$!id_permintaanukur','$!id_hakmilikpb','$!showDetail','$!valTab')">&nbsp;<u>KOSONGKAN</u></a></td>
    				
    			</table>
    			
    			
    			#if($saiz_pb > 5)
        		<div style="width:100%;height:100;overflow:auto"> 
        		#end
            	
    			<table width="100%" border="0">
    				<tr class="table_header">
    					<td width="4%" align="center"><b>No</b></td>
    					<td width="46%">&nbsp;<b>Nama PB</b></td>
    					<td width="50%">&nbsp;<b>No. PB</b></td>
    					
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
                   		<td class="$row">&nbsp;<a href="javascript:selectPBSusulan('$!listPB.id_hakmilikpb','$!valTab')"><font color="blue">$!listPB.nama_pb</font></a></td>
                   		<td class="$row">&nbsp;$!listPB.no_pb</td>                   		
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
  			
  			#end
  				
  				
  			</div>
  			<!-- END TAB 5 -->
#end  			
  			
  			<!-- START TAB 6 -->
  			<div class="TabbedPanelsContent">
  			
  				<fieldset>
				<legend><strong>Maklumat Dokumen</strong></legend>
				
			    	 <table width="100%" cellpadding="0" border="0">  
			        	
			        	<tr>
			        	  <td width="1%"><font color="red">*</font></td>
			              <td width="20%">Nama Dokumen</td>
			              <td width="1%">:</td>
			              <td width="78%"><input type="text" name="txtNamaDokumen" value="" id="txtNamaDokumen" size="43" /></td>
			          	</tr>
			            
			            <tr>
			            	<td>&nbsp;</td>
			            	<td valign="top">Keterangan</td>
			            	<td valign="top">:</td>
			                <td><textarea name="txtKeterangan" id="txtKeterangan" cols="40%" rows="3"></textarea></td>
			            </tr>
			            
			            <tr>
			            	<td valign="top"><font color="red">*</font></td>
			            	<td valign="top">Dokumen</td>
			            	<td valign="top">:</td>
			  				<td><input id="fileupload" name="fileupload" type=file size=40 /></td><br/>
			  			</tr>
			            
			        </table>
			        
			        <table width="100%" border="0">
			        	<tr><td>&nbsp;</td></tr>
			        	<tr>
			        		<td>$!perhatian2</td>
			        	</tr>
			        </table>
			        
			    </fieldset>
  			
  				<table align="left" width="75%"  cellpadding="0" border="0">
			    	<tr align="center">
			        	<td>
			                <input type="button" value="Simpan" onClick="uploadDokumen('$!id_permohonan','$!id_hakmilik')">   
			                <input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
			            </td>
			        </tr>
			    </table>
    			
    			<br/>
			    <br/>
			    <br/>
			     
			    <fieldset>
			    <legend><strong>Senarai Dokumen yang Disertakan</strong></legend>
			    
			       	<table width="100%"  cellpadding="0" cellspacing="2" border="0">   
			        	<tr class="table_header">
			           		<td width="3%" align="center"><b>No</b></td>
			                <td width="27%"><b>Nama Dokumen</b></td>
			                <td width="34%"><b>Keterangan</b></td>
			                <td width="30%"><b>Dokumen Sokongan</b></td>
			                #if($listD_size!=0)
			                <td width="6" align="center"><b>&nbsp;</b></td>
			                #end
			            </tr>
			              
			         #if($listD_size!=0)
			          
			             #foreach($listD in $listDokumen)  
			                   
			                    #set( $i = $velocityCount )       	
			         		#if ( ($i % 2) != 1 )
			              		#set( $row = "row2" )
			         		#else
			               		#set( $row = "row1" )
			         		#end
			         		      
			          	<tr>
			                <td class="$row" align="center">$listD.bil</td>
			                <td class="$row">$listD.tajuk</td>
			                <td class="$row">$listD.keterangan</td>
			                <td class="$row"><a href="javascript:paparLampiran('$!listD.id_dokumen')"><font color="blue">$listD.nama_fail</font></a></td>
			                #if($listD_size!=0)
			                <td class="$row" align="center"><input type="button" name="cmdHapusDoc" value ="Hapus" onClick="hapusDokumen('$!listD.id_dokumen')"></td>	
			                #end
			            </tr>
			             #end  
			              		 
			         #else
			            <tr>
			                <td colspan="4">Tiada rekod</td>
			            </tr>
			         #end
			                    
			       </table>        	
			    </fieldset>	
    			
  			</div>
  			<!-- END TAB 6 -->
  			
  			
  		</div>
  	</div>
  			
</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
		 <td><a href="#" onClick="javascript:cetakSuratAPBayarTambahan('$!id_permohonan','$!id_hakmilik')"><font color="blue">Surat kepada AP meminta bayaran tambahan</font></a></td>
	  </tr>
    </table>
</fieldset>

<fieldset id="tableReport2" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
		 <td><a href="#" onClick="javascript:cetakSuratPUBayarBalik('$!id_permohonan','$!id_hakmilikpb')"><font color="blue">Surat kepada PB memulangkan wang lebih pampasan</font></a></td>
	  </tr>
    </table>
</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_hakmilikpb" value="$!id_hakmilikpb">
<input type="hidden" name="id_award" value="$!id_award">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="id_nopelan">
<input type="hidden" name="id_permintaanukur" value="$!id_permintaanukur">
<input type="hidden" name="bakiLuas" value="$Utils.RemoveSymbol($countBakiLuas)">


<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script> 
function cetakMinitSijilPU(id_permintaanukur,id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=minitSijilPU&selectNoFail=yes&flagShowTarikhCetak=no";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakCoveringSijilPU(id_permintaanukur,id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=coveringSijilPU&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakLampiranBPU(id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+id_permohonan+"&report=lampiranBPU&selectNoFail=yes&flagShowTarikhCetak=no";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakLampiranAPU(id_permintaanukur,id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=lampiranAPU&selectNoFail=yes&flagShowTarikhCetak=no";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangPU(id_permintaanukur,id_permohonan) {
	var url = "../servlet/ekptg.report.ppt.borangPU?id_permintaanukur="+id_permintaanukur;
	//var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=borangPU&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakCoveringPU(id_permintaanukur,id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=coveringPU&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function hapusDokumen(id_dokumen) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.tabId.value = "5";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur&command=hapusDokumen&id_dokumen="+id_dokumen;
	document.${formName}.submit();
}
function paparLampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function uploadDokumen(idpermohonan,idhakmilik){	

	if(document.${formName}.txtNamaDokumen.value == ""){
		alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu.");
  		document.${formName}.txtNamaDokumen.focus(); 
		return;
	}
	if(document.${formName}.fileupload.value == ""){
		alert("Sila pilih \"Dokumen\" yang hendak dimuat naik terlebih dahulu.");
  		document.${formName}.fileupload.focus(); 
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		
		//token for dopost
		var ft = document.${formName}.form_token.value ;
		var token = "&form_token="+ft;
	
		//data
		var name = document.${formName}.txtNamaDokumen.value ;
		var keterangan = document.${formName}.txtKeterangan.value ;
		
		var command = "&command=screenUpload&command2=uploadFile";
		var itemId = "&id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&tabId=5";
		var data = "&ScreenLocation=TabbedPanels1&txtNamaDokumen="+name+"&txtKeterangan="+keterangan;
	
		var actionItem = (command+itemId+data+token);
		
		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.encoding = "multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur"+actionItem;
		document.${formName}.submit();		
	}
}
function screenUpload(id_permintaanukur) {
	document.${formName}.tabId.value = "5";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "screenUpload";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function hapusNoPelan(id_nopelan,mode) {

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.tabId.value = "0";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_nopelan.value = id_nopelan;	
	
	if(mode=="new"){
		document.${formName}.command.value = "maklumatPermintaanUkur";
		document.${formName}.command2.value = "hapusNoPelan";
	}else{
		document.${formName}.command.value = "maklumatPermintaanUkur";
		document.${formName}.command2.value = "kemaskiniMaklumatPU";
		document.${formName}.command3.value = "hapusNoPelan";
	}

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function cetakSuratPUBayarBalik(idpermohonan,idHakmilikpb) {
	
	//var url = "../servlet/ekptg.report.ppt.SuratPBBayarBalik?ID_HAKMILIKPB="+idHakmilikpb+"&namaPegawai="+namaPengarah;	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilikpb="+idHakmilikpb+"&report=SuratPBBayarBalik&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratAPBayarTambahan(idpermohonan,idhakmilik) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=SuratAPBayarTambahan&selectNoFail=yes&flagShowTarikhCetak=yes";
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
function batalKemaskiniPBSusulan(id_hakmilikpb,flag) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;

	if(flag=="2"){
		document.${formName}.tabId.value = "3";
	}else{
		document.${formName}.tabId.value = "4";
	}
	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "susulan";
	document.${formName}.command2.value = "selectPBSusulan";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function updatePBSusulan(id_award,flag) {

	var dat1=document.${formName}.txdTarikhSuratPB;
	
	if(document.${formName}.txdTarikhSuratPB.value == ""){
		alert("Sila masukkan \"Tarikh Surat\" terlebih dahulu.");
  		document.${formName}.txdTarikhSuratPB.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;

		if(flag=="2"){
			document.${formName}.tabId.value = "3";
		}else{
			document.${formName}.tabId.value = "4";
		}
		
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.command.value = "susulan";
		document.${formName}.command2.value = "selectPBSusulan";
		document.${formName}.command3.value = "updatePBSusulan";
		document.${formName}.id_award.value = id_award;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
		document.${formName}.submit();
	}
}
function kemaskiniPBSusulan(id_award,flag) {

	if(flag=="2"){
		document.${formName}.tabId.value = "3";
	}else{
		document.${formName}.tabId.value = "4";
	}
	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "susulan";
	document.${formName}.command2.value = "selectPBSusulan";
	document.${formName}.command3.value = "kemaskiniPBSusulan";
	document.${formName}.id_award.value = id_award;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function selectPBSusulan(id_hakmilikpb,flag) {

	if(flag=="2"){
		document.${formName}.tabId.value = "3";
	}else{
		document.${formName}.tabId.value = "4";
	}

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "susulan";
	document.${formName}.command2.value = "selectPBSusulan";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function batalKemaskiniSusulan(id_permintaanukur) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.tabId.value = "4";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "susulan";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function kemaskiniSusulan(id_permintaanukur) {
	document.${formName}.tabId.value = "4";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "susulan";
	document.${formName}.command2.value = "kemaskiniSusulan";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function updateSusulan(id_permintaanukur) {

	var dat1=document.${formName}.txdTarikhSurat;
	var dat2=document.${formName}.txdTarikhTerimaAgensi
	
/*	if(document.${formName}.txtBilSurat.value == ""){
		alert("Sila masukkan \"Bilangan Surat\" terlebih dahulu.");
  		document.${formName}.txtBilSurat.focus(); 
		return;
	}
*/	if(document.${formName}.txdTarikhSurat.value == ""){
		alert("Sila masukkan \"Tarikh Surat\" terlebih dahulu.");
  		document.${formName}.txdTarikhSurat.focus(); 
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
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.tabId.value = "4";
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.command.value = "susulan";
		document.${formName}.command2.value = "updateSusulan";
		document.${formName}.id_permintaanukur.value = id_permintaanukur;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
		document.${formName}.submit();
	}
}
function susulan(id_permintaanukur,flag) {

	if(flag=="2"){
		document.${formName}.tabId.value = "3";
	}else{
		document.${formName}.tabId.value = "4";
	}
	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "susulan";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function batalKemaskiniPelarasanPB(id_permintaanukur,id_hakmilikpb) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.tabId.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "pelarasanPB";
	document.${formName}.command2.value = "selectPB";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function kemaskiniPelarasanPB(id_permintaanukur) {

	document.${formName}.tabId.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "pelarasanPB";
	document.${formName}.command2.value = "selectPB";
	document.${formName}.command3.value = "kemaskiniPelarasanPB";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function updatePelarasanPB(id_permintaanukur) {

	var dat1=document.${formName}.txdTarikhSedia
	
	if(document.${formName}.txtPenama.value == ""){
		alert("Sila masukkan \"Penama / Penerima\" terlebih dahulu.");
  		document.${formName}.txtPenama.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.tabId.value = "3";
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.command.value = "pelarasanPB";
		document.${formName}.command2.value = "selectPB";
		document.${formName}.command3.value = "updatePelarasanPB";
		document.${formName}.id_permintaanukur.value = id_permintaanukur;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
		document.${formName}.submit();
	}
}
function pelarasanPB(id_permintaanukur) {
	document.${formName}.tabId.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "pelarasanPB";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function selectPB(id_hakmilikpb) {
	document.${formName}.tabId.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "pelarasanPB";
	document.${formName}.command2.value = "selectPB";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function batalKemaskiniPelarasanLuas(id_permintaanukur) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "pelarasanLuas";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function kemaskiniPelarasanLuas(id_permintaanukur) {
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "pelarasanLuas";
	document.${formName}.command2.value = "kemaskiniPelarasanLuas";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function updatePelarasanLuas(id_permintaanukur) {

	if(document.${formName}.txtKeluasanPU.value == ""){
		alert("Sila masukkan \"Luas Baru\" terlebih dahulu.");
  		document.${formName}.txtKeluasanPU.focus(); 
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.tabId.value = "2";
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.command.value = "pelarasanLuas";
		document.${formName}.command2.value = "updatePelarasanLuas";
		document.${formName}.id_permintaanukur.value = id_permintaanukur;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
		document.${formName}.submit();
	}
	
}
function pelarasanLuas(id_permintaanukur) {
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "pelarasanLuas";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function batalKemaskiniPenyelesaian(id_permintaanukur) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.tabId.value = "1";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatPenyelesaian";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function kemaskiniPenyelesaian(id_permintaanukur) {
	document.${formName}.tabId.value = "1";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatPenyelesaian";
	document.${formName}.command2.value = "kemaskiniPenyelesaian";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function updatePenyelesaian(id_permintaanukur) {

	var dat1=document.${formName}.txdTarikhTerimaPA
	var dat2=document.${formName}.txdTarikhTerimaSA
	var dat3=document.${formName}.txdTarikhPenyelesaian
	
/*	if(document.${formName}.txdTarikhTerimaPA.value == ""){
		alert("Sila masukkan \"Tarikh Terima PA\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerimaPA.focus(); 
		return;
	}
	
*/	
	if(document.${formName}.txtNoPA.value == ""){
		alert("Sila masukkan \"No. PA dan B1\" terlebih dahulu.");
			document.${formName}.txtNoPA.focus(); 
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
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.tabId.value = "1";
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.command.value = "maklumatPenyelesaian";
		document.${formName}.command2.value = "updatePenyelesaian";
		document.${formName}.id_permintaanukur.value = id_permintaanukur;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
		document.${formName}.submit();
		
	}
}
function maklumatPenyelesaian(id_permintaanukur) {
	document.${formName}.tabId.value = "1";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatPenyelesaian";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function batalKemaskiniPU(idHakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.tabId.value = "0";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatPermintaanUkur";
	document.${formName}.id_hakmilik.value = idHakmilik;	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function kemaskiniMaklumatPU(id_permintaanukur) {
	document.${formName}.tabId.value = "0";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatPermintaanUkur";
	document.${formName}.command2.value = "kemaskiniMaklumatPU";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function maklumatPermintaanUkur(idHakmilik) {
	document.${formName}.tabId.value = "0";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatPermintaanUkur";
	document.${formName}.id_hakmilik.value = idHakmilik;	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function simpanMaklumatPU(idHakmilik,id_permintaanukur,mode) {
	
	var dat1=document.${formName}.txdTarikhSuratPTG
	var dat2=document.${formName}.txdTarikhHantarJUPEM
	var dat3=document.${formName}.txdTarikhBorangPU

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
		document.${formName}.tabId.value = "0";
		document.${formName}.ScreenLocation.value = "TabbedPanels1";

		if(mode=="new"){
			document.${formName}.command.value = "maklumatPermintaanUkur";
			document.${formName}.command2.value = "simpanMaklumatPU";
		}else{
			document.${formName}.id_permintaanukur.value = id_permintaanukur;
			document.${formName}.command.value = "maklumatPermintaanUkur";
			document.${formName}.command2.value = "kemaskiniMaklumatPU";
			document.${formName}.command3.value = "updateMaklumatPU";
		}
		document.${formName}.id_hakmilik.value = idHakmilik;	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
		document.${formName}.submit();
	}
}
function viewListHM(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function cariPBPelarasan(id_permintaanukur,id_hakmilikpb,showDetail) {	
	document.${formName}.tabId.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "pelarasanPB";
	
	if(showDetail=="yes"){
		document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
		document.${formName}.command2.value = "selectPB";
	}
	
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function kosongkanPBPelarasan(id_permintaanukur,id_hakmilikpb,showDetail) {
	
	document.${formName}.tabId.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.carianPBPelarasan.value = "";
	document.${formName}.command.value = "pelarasanPB";
	
	if(showDetail=="yes"){
		document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
		document.${formName}.command2.value = "selectPB";
	}
	
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
</script>

<script>
function cariPBSusulan(id_permintaanukur,id_hakmilikpb,showDetail,flag) {	

	if(flag=="2"){
		document.${formName}.tabId.value = "3";
	}else{
		document.${formName}.tabId.value = "4";
	}
	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "susulan";
	
	if(showDetail=="yes"){
		document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
		document.${formName}.command2.value = "selectPBSusulan";
	}
	
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function kosongkanPBSusulan(id_permintaanukur,id_hakmilikpb,showDetail,flag) {
	
	if(flag=="2"){
		document.${formName}.tabId.value = "3";
	}else{
		document.${formName}.tabId.value = "4";
	}
	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.carianPBSusulan.value = "";
	document.${formName}.command.value = "susulan";
	
	if(showDetail=="yes"){
		document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
		document.${formName}.command2.value = "selectPBSusulan";
	}
	
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
</script>

<!-- KIRA KIRA -->
<script>
function showPopup() {

	var luas_baru = parseFloat(document.${formName}.txtKeluasanPU.value);
	var baki_luas = parseFloat(document.${formName}.bakiLuas.value);

	var beza_luas = 0;
	
	if (!isNaN(luas_baru) && !isNaN(baki_luas)){
		beza_luas = luas_baru - baki_luas;
	}

	if(beza_luas < 0){
		alert("Tanah yang diambil adalah lebih");
	}else if(beza_luas > 0){
		alert("Tanah yang diambil adalah kurang");
	}else if(beza_luas == 0){
		alert("Tanah yang diambil adalah sempurna");
	}
}

function kiraBezaLuas() {

	var luas_baru = parseFloat(document.${formName}.txtKeluasanPU.value);
	var baki_luas = parseFloat(document.${formName}.bakiLuas.value);

	var beza_luas = 0;
	
	if (!isNaN(luas_baru) && !isNaN(baki_luas)){
		beza_luas = luas_baru - baki_luas;
	}

	document.${formName}.countBezaLuas.value = beza_luas.toFixed(4);

	if(beza_luas < 0){
		document.${formName}.sorJenisPelarasan.value = "1";
		document.${formName}.hdJenisPelarasan.value = "1";
		//document.${formName}.lblBezaLuas.value = "TERLEBIH";
	}else if(beza_luas > 0){
		document.${formName}.sorJenisPelarasan.value = "2";
		document.${formName}.hdJenisPelarasan.value = "2";
		//document.${formName}.lblBezaLuas.value = "KURANG";
	}else if(beza_luas == 0){
		document.${formName}.sorJenisPelarasan.value = "3";
		document.${formName}.hdJenisPelarasan.value = "3";
		//document.${formName}.lblBezaLuas.value = "SEMPURNA";
	}else{
		document.${formName}.sorJenisPelarasan.value = "";
		document.${formName}.hdJenisPelarasan.value = "";
		//document.${formName}.lblBezaLuas.value = "";
	}
	
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
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
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
function validateLuas(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content!=""){
		var num = content * 1;
		elmnt.value = num.toFixed(4);
		return;
	}else{
		elmnt.value = "";
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
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
</script>