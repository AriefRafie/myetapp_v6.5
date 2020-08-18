<script type="text/javascript"  src="../library/js/fraction.js"></script>

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
	#set($idUnitLuas=$data.id_unit_luas)
	#set($lblLuasLot=$data.luas_lot)
	#set($countBakiLuas=$data.baki_luas_asal)
	#set($lblNoSyit=$data.no_syit)
    #set($ID_NEGERIPROJEK=$data.id_negeri)
    #set($idUnitLuas=$data.id_kategoritanah)
    
#end


<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_hakmilikpb" value="$!id_hakmilikpb">
<input type="hidden" name="id_award" value="$!id_award">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">
<input type="hidden" name="command5">
<input type="hidden" name="id_nopelan">
<input type="hidden" name="id_permintaanukur" value="$!id_permintaanukur">
<input type="hidden" name="bakiLuas" value="$Utils.RemoveSymbol($countBakiLuas)">
<input type="hidden" name="id_hakmilikpb_salin" id="id_hakmilikpb_salin" value="$!id_hakmilikpb_salin" />
<input type="hidden" name="showButtonConvertAmbil" value="$!showButtonConvertAmbil">
<!-- penambahan yati -->
<input type="hidden" name="showFieldAmbilBeforeConvert" value="$!showFieldAmbilBeforeConvert">
<input type="hidden" name="idUnitLuas" value="$!idUnitLuas">        
<input type="hidden" name="idUnitLuas" value="$!idUnitLuas">
<input type="hidden" name="luasLot" value="$!txtLuasLotAmbil">

<fieldset id="top">
<legend><strong>Permintaan Ukur</strong></legend>
<center>

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>
	
	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>

    #if($ID_NEGERIPROJEK == "4" || $ID_NEGERIPROJEK == "5")
        <table width="100%" border="0">
        <tr>
        <td  align="left">
        Integrasi bersama e-Tanah untuk menghantar Sijil Pembebasan Ukur :
        <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Hantar Sijil Pembebasan Ukur)" onClick="popupEtanah('$id_fail','$id_permohonan','$id_hakmilik','SijilUkur','')">
        </td>
        </tr>
        <tr>
        <td  align="left">
        Integrasi bersama e-Tanah untuk menghantar Maklumat Pelan PA & B1 :
        <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Hantar Maklumat Hakmilik Sambungan)" onClick="popupEtanah('$id_fail','$id_permohonan','$id_hakmilik','PU','')">
        </td>
        </tr>
        </table>
    #end

	<div id="TabbedPanels1" class="TabbedPanels">		
  		<ul class="TabbedPanelsTabGroup">
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);maklumatPermintaanUkur('$!id_hakmilik')" tabindex="1">Penyediaan PU</li>
			#if($showAllTab=="yes")
			<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);maklumatPenyelesaian('$!id_permintaanukur')" tabindex="1">Penyelesaian SA/PA dan B1</li>
            <li class="TabbedPanelsTab" onClick="javascript:setSelected(2);PB('$!id_permintaanukur')" tabindex="1">Pihak Berkepentingan</li>
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(3);pelarasanLuas('$!id_permintaanukur')" tabindex="1">Pelarasan Luas</li>
    			#if($valTab!="" && $valTab!="3")
    				#if($valTab=="1")
    				<li class="TabbedPanelsTab" onClick="javascript:setSelected(4);pelarasanPB('$!id_permintaanukur')" tabindex="1">Pelarasan</li>
    				#end
				<li class="TabbedPanelsTab" onClick="javascript:setSelected(5);susulan('$!id_permintaanukur','$!valTab')" tabindex="1">Surat / Susulan</li>
				<li class="TabbedPanelsTab" onClick="javascript:setSelected(6);screenUpload('$!id_permintaanukur')" tabindex="1">Dokumen</li>	
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
  							<td width="20%">Tarikh Surat PTD / PTG</td>
  							<td width="1%">:</td>
  							<td width="68%"><input name="txdTarikhSuratPTG" id="txdTarikhSuratPTG" size="11" type="text" value="$!txdTarikhSuratPTG" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
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
  							<td width="68%"><input name="txdTarikhHantarJUPEM" id="txdTarikhHantarJUPEM" size="11" type="text" value="$!txdTarikhHantarJUPEM" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantarJUPEM',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. PU</td>
  							<td>:</td>
  							<td><input type="text" name="txtNoPU" id="txtNoPU" value="$!txtNoPU" size="20" maxlength="20"   ></td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tarikh Borang PU</td>
  							<td>:</td>
  							<td><input name="txdTarikhBorangPU" id="txdTarikhBorangPU" size="11" type="text" value="$!txdTarikhBorangPU" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
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
	    			#set($txtNoHakmilik=$dataPU.no_hakmilik_sambung)
	    			#set($txtLotBaru=$dataPU.no_lot_baru)
	    			#set($txtNoLuas=$dataPU.luas_ambil_sambung)
	    			#set($sorDropdownUnitAmbil=$data.id_unitluasambil_convert)
	    			#set($txtLuasLotAmbilSebelumConvert=$data.nama_luas_ambil)
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
  				
  				<br/>
  				
  				<fieldset>
  				<legend><strong>Maklumat Hakmilik Sambungan</strong></legend>
  				
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
  						#if($mode =='new')
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. Hakmilik Sambungan</td>
  							<td>:</td>
  							<td><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="20" maxlength="20"   ></td>
  						</tr>
  						#end
  						#if($mode =='view')
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. Hakmilik</td>
  							<td>:</td>
  							<td><input $disability $disabilityx type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="20" maxlength="20"   ></td>
  						</tr>#end
  						#if($mode =='new')
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. Lot / No. PT Baru</td>
  							<td>:</td>
  							<td><input type="text" name="txtLotBaru" id="txtLotBaru" value="$!txtLotBaru" size="20" maxlength="20"   ></td>
  						</tr>
  						#end
  						#if($mode =='view')
  						<tr>
  							<td>&nbsp;</td>
  							<td>No. Lot / No. PT Baru</td>
  							<td>:</td>
  							<td><input $disability $disabilityx type="text" name="txtLotBaru" id="txtLotBaru" value="$!txtLotBaru" size="20" maxlength="20"   ></td>
  						</tr>
  						#end
  									<tr>
				<td>&nbsp;</td>
				<td>Unit Luas Ambil</td>
				<td>:</td>
				<td>$!selectUnitLuasAmbil</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td valign="top">Luas Diambil</td>
				<td valign="top">:</td>
				<td>
					#if($showFieldAmbilBeforeConvert=="yes")
					<input type="text"  $disability $disabilityx name="txtLuasLotAmbilSebelumConvert" id="txtLuasLotAmbilSebelumConvert" size="50" value="$!txtLuasLotAmbilSebelumConvert" maxlength="100"   /><p/>
					#end
					#if($mode == 'new')
					<input type="text" name="txtLuasLotAmbil" id="txtLuasLotAmbil" size="15" value="$!txtLuasLotAmbil" maxlength="20" onkeyup="validateNilai(this,this.value);" />
					#end
					#if($mode == 'view')
					<input type="text"  $disability $disabilityx name="txtLuasLotAmbil" id="txtLuasLotAmbil" size="15" value="$!txtLuasLotAmbil" maxlength="20" onkeyup="validateNilai(this,this.value);" />
					#end
					#if($showBoxAmbil2=="yes" || $showBoxAmbil3=="yes")<input type="text" name="txtLuasLotAmbil2" id="txtLuasLotAmbil2" size="15" value="$!txtLuasLotAmbil2" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
					#if($showBoxAmbil3=="yes")<input type="text" name="txtLuasLotAmbil3" id="txtLuasLotAmbil3" size="15" value="$!txtLuasLotAmbil3" maxlength="20" onkeyup="validateNilai(this,this.value);" />#end
				
					#if($showDropdownUnitAmbil=="yes")
					<select  $disability $disabilityx name="sorDropdownUnitAmbil" style="width:132px" onchange="onchangeUnitAmbil()">
      		
      					#if($sorDropdownUnitAmbil=="1")
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#elseif($sorDropdownUnitAmbil=="2")
      					<option value="2">METER PERSEGI</option>
      					<option value="1">HEKTAR</option>
      					#else
      					<option value="">SILA PILIH</option>
      					<option value="1">HEKTAR</option>
						<option value="2">METER PERSEGI</option>	
      					#end

					</select>
					#end
					
				</td>
			</tr>	

			#if($isEdit=="no" && $showButtonConvertAmbil=="yes")
			<tr>
				<td colspan="3">&nbsp;</td>
				<td>
					#if($showButtonConvertAmbil=="yes")
					<input type="button" name="cmdConvert" value ="Convert" onClick="javascript:convertNilaiAmbil('$!showBoxAmbil2','$!showBoxAmbil3')">
					#end
					
				</td>
			</tr>
			#end
			
			#if($isEdit=="yes" && ($showButtonConvertAmbil=="yes" || $showFieldAmbilBeforeConvert=="yes"))
			<tr>
				<td colspan="3">&nbsp;</td>
				<td>
					#if($showButtonConvertAmbil=="yes")
					<input type="button" name="cmdConvert" value ="Convert" onClick="javascript:convertNilaiAmbilUpdate('$!showBoxAmbil2','$!showBoxAmbil3')">
					#end
					#if($showFieldAmbilBeforeConvert=="yes")
					<input type="button" name="cmdClear" value ="Kosongkan" onClick="javascript:clearConvertAmbilUpdate()">
					#end
				</td>
			</tr>
			#end
			
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
						 <td>
                         
                         
                         
                         <a href="#" onClick="javascript:cetakBorangPU('$!id_permintaanukur','$!id_permohonan')"><font color="blue">Borang Permintaan Ukur</font></a></td>
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
  						<!-- <tr>
  							<td></td>
  							<td>No. LOT Baru</td>
  							<td>:</td>
  							<td><input type="text" name="txtLotBaru" id="txtLotBaru" value="" size="20" maxlength="20"   ></td>
  						</tr> -->
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
  			
            <div class="TabbedPanelsContent">
          <table width="100%" >
 #if($view_pb == "yes")
 
 <tr>
<td>

<fieldset id="maklumat_pb">

#if($readmode == "edit")
#set($disabledmode = "")
#set($readonlymode = "")
#elseif($readmode == "view")
#set($disabledmode = "disabled")
#set($readonlymode = "readonly")
#else
#set($disabledmode = "")
#set($readonlymode = "")
#end


#if($tambah_kehadiran == "yes" || $tambah_kehadiran_wakil == "yes")

#set($id_hakmilikpb="")
#set($id_pb="")
#set($socJenisPB="")
#set($txtNamaPB = "")  
#set($txtNamaPBKP = "")    
#set($socJenisNOPB="")  
#set($txtNoPB = "")   
#set($txtNoAkaun = "")   
#set($socJenisBank="") 
#set($txtAlamat1PB = "")     
#set($txtAlamat2PB = "")     
#set($txtAlamat3PB = "")        
#set($txtPoskodPB = "")
#set($socNegeri = "")
#set($socBangsa = "")
#set($socWarga = "")
#set($socBandar = "")
#set($txtNoHP = "")
#set($txtNoTel = "")
#set($txtNamaBank = "")
#set($txtKeteranganPB = "")
#set($txtSyorAtas = "")
#set($hadir = "")   
#set($id_kehadiran = "")  
#set($txtPBhadir = "") 
#set($txtCatatanPB = "") 

#set($boxBorangK = "") 
#set($boxBorangE = "") 
#set($boxBorangG = "") 
#set($boxBorangC = "") 


#set($txtSyorBawah = "")
#set($txtSyorAtas = "")
#set($txtSyorBawah_temp = "")
#set($txtSyorAtas_temp = "")
#set($id_bahagianpb = "")

<!-- 27022012 -->
#set($txtPenamaPenyata = "")

<!--  boxBorangC -->


#if($maklumat_PB_Salin.size() > 0)
#set($socJenisPB=$maklumat_PB_Salin.ID_JENISPB)
#set($txtNamaPB = $maklumat_PB_Salin.NAMA_PB)  
#set($txtNamaPBKP = $maklumat_PB_Salin.NAMA_KP)    
#set($socJenisNOPB=$maklumat_PB_Salin.ID_JENISNOPB)  
#set($txtNoPB = $maklumat_PB_Salin.NO_PB)   
#set($txtNoAkaun = $maklumat_PB_Salin.NO_AKAUN)   
#set($socJenisBank=$maklumat_PB_Salin.ID_BANK) 
#set($txtAlamat1PB = $maklumat_PB_Salin.ALAMAT1)     
#set($txtAlamat2PB = $maklumat_PB_Salin.ALAMAT2)     
#set($txtAlamat3PB = $maklumat_PB_Salin.ALAMAT3)        
#set($txtPoskodPB = $maklumat_PB_Salin.POSKOD)
#set($socNegeri = $maklumat_PB_Salin.ID_NEGERI)
#set($socBandar = $maklumat_PB_Salin.ID_BANDAR)
#set($txtNoHP = $maklumat_PB_Salin.NO_HANDPHONE)
#set($txtNoTel = $maklumat_PB_Salin.NO_TEL_RUMAH)
#set($txtNamaBank = $maklumat_PB_Salin.NAMA_BANK_TXT)
#set($txtKeteranganPB = $maklumat_PB_Salin.KETERANGAN_JENIS_PB)
#set($txtCatatanPB = $maklumat_PB_Salin.CATATAN)
#set($socWarga = $maklumat_PB_Salin.ID_WARGANEGARA)
#set($socBangsa = $maklumat_PB_Salin.ID_BANGSA)
#set($txtPenamaPenyata = $maklumat_PB_Salin.NAMA_PENYATA_BANK)
#set($txtUmur = $!Utils.parseInt($!maklumat_PB_Salin.UMUR))
#end


#end


#if($view_pb == "yes")
#if($maklumat_kehadiran.size() > 0)

#foreach($list in $maklumat_kehadiran)
#set($id_hakmilikpb=$list.ID_HAKMILIKPB)
#set($id_pb=$list.ID_PIHAKBERKEPENTINGAN)
#set($socJenisPB=$list.ID_JENISPB)
#set($txtNamaPB = $list.NAMA_PB)  
#set($txtNamaPBKP = $list.NAMA_KP)    
#set($socJenisNOPB=$list.ID_JENISNOPB)  
#set($txtNoPB = $list.NO_PB)   
#set($txtNoAkaun = $list.NO_AKAUN)   
#set($socJenisBank=$list.ID_BANK) 
#set($txtAlamat1PB = $list.ALAMAT1)     
#set($txtAlamat2PB = $list.ALAMAT2)     
#set($txtAlamat3PB = $list.ALAMAT3)        
#set($txtPoskodPB = $list.POSKOD)
#set($socNegeri = $list.ID_NEGERI)
#set($socBandar = $list.ID_BANDAR)
#set($txtNoHP = $list.NO_HANDPHONE)
#set($txtNoTel = $list.NO_TEL_RUMAH)
#set($txtNamaBank = $list.NAMA_BANK_TXT)
#set($txtKeteranganPB = $list.KETERANGAN_JENIS_PB)
#set($txtCatatanPB = $list.CATATAN)

#set($socWarga = $list.ID_WARGANEGARA)
#set($socBangsa = $list.ID_BANGSA)



#set($txtSyorAtas = $list.SYER_ATAS)
#set($txtSyorBawah = $list.SYER_BAWAH)
#set($id_bahagianpb = $list.ID_BAHAGIANPB)

#set($txtSyorAtas_temp = $list.SYER_ATAS)
#set($txtSyorBawah_temp = $list.SYER_BAWAH)


#if($list.FLAG_BORANGC != "" )
#set($boxBorangC = "checked") 
#else
#set($boxBorangC = "") 
#end

#if($list.FLAG_BORANGE != "" )
#set($boxBorangE = "checked") 
#else
#set($boxBorangE = "") 
#end

#if($list.FLAG_BORANGG != "" )
#set($boxBorangG = "checked") 
#else
#set($boxBorangG = "") 
#end

#if($list.FLAG_BORANGK != "" )
#set($boxBorangK = "checked") 
#else
#set($boxBorangK = "") 
#end

<!-- 27022012 -->
#set($txtPenamaPenyata = $list.NAMA_PENYATA_BANK)




#end

#set($hadir = "TIDAK HADIR")   
#set($id_kehadiran = "")  
#set($txtPBhadir = "2")  


#if($list_check_kehadiran.size()>0)
#foreach($ch in $list_check_kehadiran)    
#if($ch.ID_HAKMILIKPB == $id_hakmilikpb && $ch.FLAG_HADIR == "1")    
#set($hadir = "HADIR")
#set($txtPBhadir = "1")
#set($id_kehadiran = $ch.ID_KEHADIRAN)
#end    
#end    
#end

#end
#end


#if($maklumat_kehadiran.size() > 0)

#foreach($list in $maklumat_kehadiran)
#set($id_hakmilikpb=$list.ID_HAKMILIKPB)
#set($id_pb=$list.ID_PIHAKBERKEPENTINGAN)
#set($socJenisPB=$list.ID_JENISPB)
#set($txtNamaPB = $list.NAMA_PB)  
#set($txtNamaPBKP = $list.NAMA_KP)    
#set($socJenisNOPB=$list.ID_JENISNOPB)  
#set($txtNoPB = $list.NO_PB)   
#set($txtNoAkaun = $list.NO_AKAUN)   
#set($socJenisBank=$list.ID_BANK) 
#set($txtAlamat1PB = $list.ALAMAT1)     
#set($txtAlamat2PB = $list.ALAMAT2)     
#set($txtAlamat3PB = $list.ALAMAT3)        
#set($txtPoskodPB = $list.POSKOD)
#set($socNegeri = $list.ID_NEGERI)
#set($socBandar = $list.ID_BANDAR)
#set($txtNoHP = $list.NO_HANDPHONE)
#set($txtNoTel = $list.NO_TEL_RUMAH)
#set($txtNamaBank = $list.NAMA_BANK_TXT)
#set($txtKeteranganPB = $list.KETERANGAN_JENIS_PB)
#set($txtCatatanPB = $list.CATATAN)

#set($socWarga = $list.ID_WARGANEGARA)
#set($socBangsa = $list.ID_BANGSA)




#set($txtSyorAtas = $list.SYER_ATAS)
#set($txtSyorBawah = $list.SYER_BAWAH)
#set($id_bahagianpb = $list.ID_BAHAGIANPB)
#set($txtSyorAtas_temp = $list.SYER_ATAS)
#set($txtSyorBawah_temp = $list.SYER_BAWAH)


#if($list.FLAG_BORANGC != "" )
#set($boxBorangC = "checked") 
#else
#set($boxBorangC = "") 
#end

#if($list.FLAG_BORANGE != "" )
#set($boxBorangE = "checked") 
#else
#set($boxBorangE = "") 
#end

#if($list.FLAG_BORANGG != "" )
#set($boxBorangG = "checked") 
#else
#set($boxBorangG = "") 
#end

#if($list.FLAG_BORANGK != "" )
#set($boxBorangK = "checked") 
#else
#set($boxBorangK = "") 
#end

<!-- 27022012 -->
#set($txtPenamaPenyata = $list.NAMA_PENYATA_BANK)

#end

#set($hadir = "TIDAK HADIR")   
#set($id_kehadiran = "")  
#set($txtPBhadir = "2")  
#if($list_check_kehadiran.size()>0)
#foreach($ch in $list_check_kehadiran)    
#if($ch.ID_HAKMILIKPB == $id_hakmilikpb && $ch.FLAG_HADIR == "1")    
#set($hadir = "HADIR")
#set($txtPBhadir = "1")
#set($id_kehadiran = $ch.ID_KEHADIRAN)
#end    
#end    
#end

#end


<legend>MAKLUMAT ORANG BERKEPENTINGAN</legend>

   
<table width="100%"   > 
  <tr>
    <td valign="top"><table width="100%" >
  
    
    
    <tr>
        <td width="1%" >#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%">Jenis Pihak Berkepentingan </td>
        <td width="1%" >:</td>
        <td width="70%">
        
        <!-- $list.JENISPB -->
        
    
        
     #if($readmode == "view" ) 
     #if($socJenisPB=="")
     #set($JenisPB="")                             
     #else                             
     #foreach($ln in $list_jenispb)      
     #if($socJenisPB==$ln.ID_JENISPB)                                      
     #set($JenisPB="$ln.KOD_JENIS_PB - $ln.KETERANGAN")
     #end
     #end                            
     #end    
     $!JenisPB  
     <input name="JenisPB" type="hidden" id="JenisPB" value="$JenisPB" size="50" readonly class="disabled">              
    #else    
    <select name="socJenisPB" class="autoselect" id="socJenisPB"  onchange="checking_validation(this,'socJenisPB_check','yes','jenis pihak berkepentingan','normal');" >  
    #if($socJenisPB != "")
    
    #foreach($ln in $list_jenispb) 
    #if($socJenisPB==$ln.ID_JENISPB)
    
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>  
                                     
    #end 
    #end 
   
    #foreach($ln in $list_jenispb)
    #if($socJenisPB!=$ln.ID_JENISPB && $ln.ID_JENISPB!="42" && $ln.ID_JENISPB!="40" && $ln.ID_JENISPB!="41" && $ln.ID_JENISPB!="27")
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>                                     
    #end      
    #end
    <option value="">SILA PILIH JENIS PB</option> 
    
    #else
   
    <option value="">SILA PILIH JENIS PB</option>        
    #foreach($ln in $list_jenispb)  
    #if($ln.ID_JENISPB!="42" && $ln.ID_JENISPB!="40" && $ln.ID_JENISPB!="41" && $ln.ID_JENISPB!="27")
    <option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB - $ln.KETERANGAN</option>                                     
    #end    
    
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socJenisPB_check" class = "alert_msg" ></span>        </td>
      </tr>
     
       
      <tr>
        <td></td>
        <td valign="top">
        
    
     Keterangan Jenis PB   
     <font style="cursor:help" align="left" class="font2" title="info" onMouseOut="close_window()"  onMouseOver="open_new_window('2');this.style.cursor='help'" >

#parse("app/ppt/infoblink_biru.jsp")
</font>     </td>
        <td valign="top">:</td>
        <td>
        
        #if($readmode == "view")
$txtKeteranganPB                                
#else

        
         <textarea name="txtKeteranganPB" id="txtKeteranganPB" cols="50"   rows="3" style=""         
         onBlur="check_length(this,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');"  
         onKeyup="check_length(this,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');" 
         onKeydown="check_length(this,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtKeteranganPB</textarea> 
        
      
        
         #if($readmode == "edit")           
         <div><span id="txtKeteranganPB_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtKeteranganPB_num" id="txtKeteranganPB_num" size="3" value="4000"  style=" display:none" > 
         #end
         <div id="txtKeteranganPB_check" class="alert_msg" ></div>     
         
    
#end        </td>
      </tr>  
      
      
      
      
      
      
      
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>
   
  
  Nama PB           </td>
        <td>:</td>
        <td>
       #if($readmode == "view")
       $!txtNamaPB
        <input name="txtNamaPB" type="hidden" class = "$disabledmode" id="txtNamaPB" onBlur="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')" onKeyUp="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')" value="$txtNamaPB" size="50" maxlength="80"  $readonlymode>
    <span id="txtNamaPB_check" class = "alert_msg" ></span>
    #else
    
      <input name="txtNamaPB" type="text" class = "$disabledmode" id="txtNamaPB" onBlur="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')" onKeyUp="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')" value="$txtNamaPB" size="50" maxlength="80"  $readonlymode>
    <span id="txtNamaPB_check" class = "alert_msg" ></span>
    
    #end        
    
    
    #if($id_hakmilikpb == "")
    <a href="javascript:popupCarianPB_salinNama('$id_hakmilik','skrin_salin_pb_PU')"><font color="blue">>> SALIN PB (Nama)</font></a>
    #end
    
    
    </td>
      </tr>
      
      
      
      
      
      <tr>
        <td></td>
        <td>
        
        
 Nama PB (KP)    </td>
        <td>:</td>
        <td>
        
        <!-- $list.NAMA_KP  -->
        
        #if($readmode == "view")
        $!txtNamaPBKP
        <input name="txtNamaPBKP" type="hidden" class = "$disabledmode" id="txtNamaPBKP" onBlur="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')" onKeyUp="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')" value="$txtNamaPBKP" size="50" maxlength="80"  $readonlymode>
    <span id="txtNamaPBKP_check" class = "alert_msg" ></span>
    #else
      <input name="txtNamaPBKP" type="text" class = "$disabledmode" id="txtNamaPBKP" onBlur="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')" onKeyUp="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')" value="$txtNamaPBKP" size="50" maxlength="80"  $readonlymode>
    <span id="txtNamaPBKP_check" class = "alert_msg" ></span>
    
    #end        </td>
      </tr>
      
      <tr>
        <td></td>
        <td>Nama Atas Penyata Bank</td>
        <td>:</td>
        <td>
        
        #if($readmode == "view")
        $!txtPenamaPenyata
        <input name="txtPenamaPenyata" type="hidden" class = "$disabledmode" id="txtPenamaPenyata" value="$!txtPenamaPenyata" size="50" maxlength="80"  $readonlymode>
    	#else
      	<input name="txtPenamaPenyata" type="text" class = "$disabledmode" id="txtPenamaPenyata"  value="$!txtPenamaPenyata" size="50" maxlength="80"  $readonlymode>
		#end       
		</td>
      </tr>
      
      <tr>
        <td>&nbsp;</td>
        <td>
        
         Jenis No. Pengenalan PB</td>
        <td>:</td>
        <td>
        
        <!-- $list.JENISNOPB -->
      
      
        
     #if($readmode == "view" ) 
     #if($socJenisNOPB=="")
     #set($JenisNOPB="")                             
     #else   
                        
     #foreach($ln in $list_jenisnopb)      
     #if($socJenisNOPB==$ln.ID_JENISNOPB)                                      
     #set($JenisNOPB="$ln.KOD_JENIS_NOPB - $ln.KETERANGAN")
     #end
     #end                            
     #end  
     $!JenisNOPB    
     <input name="JenisNOPB" type="hidden" id="JenisNOPB" value="$JenisNOPB" size="50" readonly class="disabled">              
    #else    
    <select name="socJenisNOPB" class="autoselect" id="socJenisNOPB"  onchange="checking_validation(this,'socJenisNOPB_check','no','jenis no. pihak berkepentingan','normal_j');" onblur="checking_validation(this,'socJenisNOPB_check','no','jenis no. pihak berkepentingan','normal_j');" >  
    #if($socJenisNOPB != "")
    
    #foreach($ln in $list_jenisnopb) 
    #if($socJenisNOPB==$ln.ID_JENISNOPB)
    <option value="$ln.ID_JENISNOPB">$ln.KOD_JENIS_NOPB - $ln.KETERANGAN</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_jenisnopb)
    #if($socJenisNOPB!=$ln.ID_JENISNOPB)
    <option value="$ln.ID_JENISNOPB">$ln.KOD_JENIS_NOPB - $ln.KETERANGAN</option>                                     
    #end      
    #end
    <option value="">
    


 SILA PILIH JENIS NO. PB

    </option> 
    
    #else
   
    <option value="">
    

 
 SILA PILIH JENIS NO. PB
   
     

</option>        
    #foreach($ln in $list_jenisnopb)   
    <option value="$ln.ID_JENISNOPB">$ln.KOD_JENIS_NOPB - $ln.KETERANGAN</option>
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socJenisNOPB_check" class = "alert_msg" ></span>        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>
        
        
         
         
 No. Pengenalan PB</td>
        <td>:</td>
        <td>
        
          #if($readmode == "view")
          $!txtNoPB
        <input name="txtNoPB" type="hidden" class = "$disabledmode" id="txtNoPB" onBlur="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')" onKeyUp="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')" value="$txtNoPB" size="50" maxlength="15"  $readonlymode>
    <span id="txtNoPB_check" class = "alert_msg"></span>
    #else
    <input name="txtNoPB" type="text" class = "$disabledmode" id="txtNoPB" onBlur="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')" onKeyUp="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')" value="$txtNoPB" size="50" maxlength="15"  $readonlymode>
    <span id="txtNoPB_check" class = "alert_msg"></span>
    
    #end        
     #if($id_hakmilikpb == "")
    <a href="javascript:popupCarianPB_salinNoKP('$id_hakmilik','skrin_salin_pb_PU')"><font color="blue">>> SALIN PB (MyId)</font></a>
    #end	
    
    </td>
      </tr>
      
      <tr>
        <td>&nbsp;</td>
        <td>Bangsa </td>
        <td>:</td>
        <td><!-- $list.NAMA_NEGERI -->
       
        
     #if($readmode == "view" ) 
     #if($socBangsa=="")
     #set($Bangsa="")                             
     #else                             
     #foreach($ln in $list_bangsa)      
     #if($socBangsa==$ln.ID_BANGSA)                                      
     #set($Bangsa="$ln.KOD_BANGSA - $ln.KETERANGAN")
     #end
     #end                            
     #end      
     $!Bangsa
     <input name="Bangsa" type="hidden" id="Bangsa" value="$Bangsa" size="50" readonly class="disabled">              
    #else    
    <select name="socBangsa" class="autoselect" id="socBangsa"  onchange="checking_validation(this,'socBangsa_check','no','negeri','normal');"  onblur="checking_validation(this,'socBangsa_check','no','negeri','normal');" >  
    #if($socBangsa != "")
    
    #foreach($ln in $list_bangsa) 
    #if($socBangsa==$ln.ID_BANGSA)
    <option value="$ln.ID_BANGSA">$ln.KOD_BANGSA - $ln.KETERANGAN</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_bangsa)
    #if($socBangsa!=$ln.ID_BANGSA)
    <option value="$ln.ID_BANGSA">$ln.KOD_BANGSA - $ln.KETERANGAN</option>                                     
    #end      
    #end
    <option value="">SILA PILIH BANGSA</option> 
    
    #else
   
    <option value="">SILA PILIH BANGSA</option>        
    #foreach($ln in $list_bangsa)   
    <option value="$ln.ID_BANGSA">$ln.KOD_BANGSA - $ln.KETERANGAN</option>
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socBangsa_check" class = "alert_msg" ></span>        </td>
      </tr>
    
      
      
      <tr>
        <td>&nbsp;</td>
        <td>Warganegara</td>
        <td>:</td>
        <td><!-- $list.KETERANGAN -->
       
        
     #if($readmode == "view" ) 
     #if($socWarga=="")
     #set($Warga="")                             
     #else                             
     #foreach($ln in $list_warga)      
     #if($socWarga==$ln.ID_WARGANEGARA)                                      
     #set($Warga="$ln.KOD_WARGA - $ln.KETERANGAN")
     #end
     #end                            
     #end      
     $!Warga
     <input name="Warga" type="hidden" id="Warga" value="$Warga" size="50" readonly class="disabled">              
    #else    
    <select name="socWarga" class="autoselect" id="socWarga"  onchange="checking_validation(this,'socWarga_check','no','Warga','normal')"  onblur="checking_validation(this,'socWarga_check','no','Warga','normal')" >  
    #if($socWarga != "")
    
    #foreach($ln in $list_warga) 
    #if($socWarga==$ln.ID_WARGANEGARA)
    <option value="$ln.ID_WARGANEGARA">$ln.KOD_WARGA - $ln.KETERANGAN</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_warga)
    #if($socWarga!=$ln.ID_WARGANEGARA)
    <option value="$ln.ID_WARGANEGARA">$ln.KOD_WARGA - $ln.KETERANGAN</option>                                     
    #end      
    #end
    <option value="">SILA PILIH WARGANEGARA</option> 
    
    #else
   
    <option value="">SILA PILIH WARGANEGARA</option>        
    #foreach($ln in $list_warga)   
    <option value="$ln.ID_WARGANEGARA">$ln.KOD_WARGA - $ln.KETERANGAN</option>
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socWarga_check" class = "alert_msg" ></span>        </td>
      </tr>
                      #set($countX = 0) 
                      
                      
                      #foreach($list1 in $senarai_pihak_penting_bahagian)
                    
                      #if($list1.ID_BAHAGIANPB == $id_bahagianpb && $list1.ID_HAKMILIKPB != $id_hakmilikpb ) 
                      #set($countX=$countX + 1) 
                      #end
                      #end
      
      
         #if($countX > 0 || $!list_kehadiran_size > 0)  
      
      <tr>
			<td>&nbsp;</td>
			<td>Berkongsi Bahagian (Jika Ada)</td>
			<td>:</td>
			<td>     
            
    #if($id_bahagianpb == "" && $readmode == "view" ) 
    
     <input type="hidden" name="id_bahagianpb" id="id_bahagianpb" value="$id_bahagianpb"  >  
    
    
    #elseif($id_bahagianpb != "" && $readmode == "view" )
    
     <input type="hidden" name="id_bahagianpb" id="id_bahagianpb" value="$id_bahagianpb"  >   
                      
                      #set($count = 0) 
                      #foreach($list1 in $senarai_pihak_penting_bahagian)
                      #if($list1.ID_BAHAGIANPB == $id_bahagianpb && $list1.ID_HAKMILIKPB != $id_hakmilikpb ) 
                      #set($count=$count + 1) 
                      #end
                      #end
                      
                      
                      #if($count > 0)
                      
                      <div style="width:70%">
                      #set($count_total = 0) 
                      #foreach($list1 in $senarai_pihak_penting_bahagian)
                      #if($list1.ID_BAHAGIANPB == $id_bahagianpb && $list1.ID_HAKMILIKPB != $id_hakmilikpb)
                      #set($count_total=$count_total + 1)                      
                      #if($count > 1 && $count != $count_total && $count_total != $count - 1 ) 
                      $list1.NAMA_PB,
                      #elseif($count > 1 && $count != $count_total && $count_total == $count - 1) 
                      $list1.NAMA_PB &
                      #elseif($count > 1 && $count == $count_total)
                      $list1.NAMA_PB
                      #elseif($count == 1)
                      $list1.NAMA_PB
                      #end                                          
                      #end
                      #end                      </div>
    
    					#end
   #else
            
    #if($senarai_pihak_penting_bahagian.size() > 0)  
    <select name="id_bahagianpb" class="autoselect" id="id_bahagianpb" onchange="getSyer('$!id_hakmilikpb','$!id_pembatalan')"  >  
    #if($id_bahagianpb != "")    
    #foreach($ln in $senarai_pihak_penting_bahagian) 
    #if($id_bahagianpb==$ln.ID_BAHAGIANPB && $ln.ID_HAKMILIKPB != $id_hakmilikpb)
    <option value="$ln.ID_BAHAGIANPB">$ln.NAMA_PB</option>                                     
    #end 
    #end 
   
    <option value="">SILA PILIH PB</option> 
   
   
    #foreach($ln in $senarai_pihak_penting_bahagian)
    #if($id_bahagianpb!=$ln.ID_BAHAGIANPB && $ln.ID_HAKMILIKPB != $id_hakmilikpb)
    <option value="$ln.ID_BAHAGIANPB">$ln.NAMA_PB</option>                                     
    #end      
    #end
    
    #else
   
    <option value="">SILA PILIH PB</option>        
    #foreach($ln in $senarai_pihak_penting_bahagian)   
    <option value="$ln.ID_BAHAGIANPB">$ln.NAMA_PB</option>
    #end
    
    #end
    
    </select>
   #else 
    
       <input type="hidden" name="id_bahagianpb" id="id_bahagianpb" value="$id_bahagianpb"  >  
      
   #end
            
            
            
            
            
        
            
            
            
            #end            </td>
		</tr>
      
      #end
      
      <tr>
      <td>      </td>
      <td>
      Bahagian / Syer      </td>
     
      <td>
      :      </td>
      <td>
       <div id="getSyer_div" ></div>  
           
                #if($readmode == "view")         
         #if($txtSyorBawah != "" && $!txtSyorAtas != "")
         $!txtSyorAtas / $!txtSyorBawah
         #end         
             #else
           <input type="text" name="txtSyorAtas" id="txtSyorAtas" value="$!txtSyorAtas" size="20" maxlength="20" onkeyup="validateNumber(this,this.value)" onblur="validateSyer('$!checkSizeBahagianPB_size')" >
           &nbsp;<b>/</b>&nbsp;<input type="text" name="txtSyorBawah" id="txtSyorBawah" value="$!txtSyorBawah" size="20" maxlength="20" onblur="validateSyer('$!checkSizeBahagianPB_size')" onkeyup="validateNumber(this,this.value)">
            #end
          <input type="hidden" name="txtSyorAtas_temp" id="txtSyorAtas_temp" value="$!txtSyorAtas_temp"  >            
            <input type="hidden" name="txtSyorBawah_temp" id="txtSyorBawah_temp" value="$!txtSyorBawah_temp" >      </td>
      </tr>
      
       <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">No. HP</td>
        <td width="1%">:</td>
        <td width="70%">
        <!-- $list.NO_AKAUN -->
        
         #if($readmode == "view")
         $!txtNoHP
        <input name="txtNoHP" type="hidden" class = "$disabledmode" id="txtNoHP" onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')" value="$txtNoHP" size="50" maxlength="15"  $readonlymode>
    <span id="txtNoHP_check" class = "alert_msg" ></span>
        #else
          <input name="txtNoHP" type="text" class = "$disabledmode" id="txtNoHP" onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')" value="$txtNoHP" size="50" maxlength="12"  $readonlymode>
    <span id="txtNoHP_check" class = "alert_msg" ></span>
        #end        </td>
      </tr>
       <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">No. Tel (R/P)</td>
        <td width="1%">:</td>
        <td width="70%">
        <!-- $list.NO_AKAUN -->
        
         #if($readmode == "view")
         $!txtNoTel
        <input name="txtNoTel" type="hidden" class = "$disabledmode" id="txtNoTel" onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')" value="$txtNoTel" size="50" maxlength="15"  $readonlymode>
    <span id="txtNoTel_check" class = "alert_msg"></span>
        #else
          <input name="txtNoTel" type="text" class = "$disabledmode" id="txtNoTel" onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')" value="$txtNoTel" size="50" maxlength="12"  $readonlymode>
    <span id="txtNoTel_check" class = "alert_msg"></span>
        #end        </td>
      </tr>
      
       
      
           <tr>
        <td>&nbsp;</td>
        <td>Nama Bank</td>
        <td>:</td>
        <td>
       #if($readmode == "view")
       $!txtNamaBank
        <input name="txtNamaBank" type="hidden" class = "$disabledmode" id="txtNamaBank" onBlur="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" onKeyUp="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" value="$txtNamaBank" size="50" maxlength="350"  $readonlymode>
    <span id="txtNamaPB_check" class = "alert_msg" ></span>
    #else
    
      <input name="txtNamaBank" type="text" class = "$disabledmode" id="txtNamaBank" onBlur="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" onKeyUp="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')" value="$txtNamaBank" size="50" maxlength="350"  $readonlymode>
    <span id="txtNamaBank_check" class = "alert_msg" ></span>       </td>
      </tr>
      #end
      
         <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">No. Akaun</td>
        <td width="1%">:</td>
        <td width="70%">
        <!-- $list.NO_AKAUN -->
        
         #if($readmode == "view")
         $txtNoAkaun
        <input name="txtNoAkaun" type="hidden" class = "$disabledmode" id="txtNoAkaun" onBlur="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" onKeyUp="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" value="$txtNoAkaun" size="50" maxlength="20"  $readonlymode>
    <span id="txtNoAkaun_check" class = "alert_msg" ></span>
        #else
         <input name="txtNoAkaun" type="text" class = "$disabledmode" id="txtNoAkaun" onBlur="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" onKeyUp="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')" value="$txtNoAkaun" size="50" maxlength="20"  $readonlymode>
    <span id="txtNoAkaun_check" class = "alert_msg" ></span>
        #end        </td>
      </tr>
      
    
      
      <tr>
        <td>&nbsp;</td>
        <td>
        
   
          
         Alamat PB</td>
        <td>:</td>
        <td><!-- $list.ALAMAT1 -->
        
          #if($readmode == "view")  
          $txtAlamat1PB
        <input name="txtAlamat1PB" type="hidden" id="txtAlamat1PB" value="$txtAlamat1PB" size="50" onBlur="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')" onKeyUp="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')"  $readonlymode class = "$disabledmode">
    <span id="txtAlamat1PB_check" class = "alert_msg" ></span>
    #else
     <input name="txtAlamat1PB" type="text" id="txtAlamat1PB" value="$txtAlamat1PB" size="50" onBlur="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')" onKeyUp="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')"  $readonlymode class = "$disabledmode">
    <span id="txtAlamat1PB_check" class = "alert_msg" ></span>
    #end        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>
        <!-- $list.ALAMAT2 -->
        
        #if($readmode == "view")
        $txtAlamat2PB    
        <input name="txtAlamat2PB" type="hidden" id="txtAlamat2PB" value="$txtAlamat2PB" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode">
  #else
   <input name="txtAlamat2PB" type="text" id="txtAlamat2PB" value="$txtAlamat2PB" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode">
  #end        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>
        
        <!-- $list.ALAMAT3 -->
        #if($readmode == "view")
         $txtAlamat3PB
        <input name="txtAlamat3PB" type="hidden" id="txtAlamat3PB" value="$txtAlamat3PB" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode">
        
        #else
         <input name="txtAlamat3PB" type="text" id="txtAlamat3PB" value="$txtAlamat3PB" size="50" onBlur="" onKeyUp=""  $readonlymode class = "$disabledmode">
        #end        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Poskod</td>
        <td>:</td>
        <td><!-- $list.POSKOD -->
       #if($readmode == "view")
       $txtPoskodPB
        <input name="txtPoskodPB" type="hidden" id="txtPoskodPB" onBlur="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)" onKeyUp="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)" value="$txtPoskodPB" size="6" maxlength="5"  $readonlymode class = "$disabledmode">
     <span id="txtPoskodPB_check" class = "alert_msg" ></span>
     #else
      <input name="txtPoskodPB" type="text" id="txtPoskodPB" onBlur="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)" onKeyUp="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)" value="$txtPoskodPB" size="6" maxlength="5"  $readonlymode class = "$disabledmode">
     <span id="txtPoskodPB_check" class = "alert_msg" ></span>
     #end        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Negeri</td>
        <td>:</td>
        <td><!-- $list.NAMA_NEGERI -->
       
        
        #if($readmode == "view" ) 
     #if($socNegeri=="")
     #set($Negeri="")                             
     #else                             
     #foreach($ln in $list_negeri)      
     #if($socNegeri==$ln.ID_NEGERI)                                      
     #set($Negeri="$ln.KOD_NEGERI - $ln.NAMA_NEGERI")
     #end
     #end                            
     #end      
     $!Negeri
     <input name="Negeri" type="hidden" id="Negeri" value="$Negeri" size="50" readonly class="disabled">              
    #else    
    <select name="socNegeri" class="autoselect" id="socNegeri"  onchange="checking_validation(this,'socNegeri_check','no','negeri','normal');getBandar('$!id_hakmilikpb')"  onblur="checking_validation(this,'socNegeri_check','no','negeri','normal');getBandar('$!id_hakmilikpb')" >  
    #if($socNegeri != "")
    
    #foreach($ln in $list_negeri) 
    #if($socNegeri==$ln.ID_NEGERI)
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_negeri)
    #if($socNegeri!=$ln.ID_NEGERI)
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>                                     
    #end      
    #end
    <option value="">SILA PILIH NEGERI</option> 
    
    #else
   
    <option value="">SILA PILIH NEGERI</option>        
    #foreach($ln in $list_negeri)   
    <option value="$ln.ID_NEGERI">$ln.KOD_NEGERI - $ln.NAMA_NEGERI</option>
    #end
    
    #end
    
    </select>  
             
    #end
      <span id="socNegeri_check" class = "alert_msg" ></span>        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Bandar</td>
        <td>:</td>
        <td><!-- $list.NAMA_BANDAR -->
       
         
     #if($readmode == "view" )
    
     #if($socBandar=="")
     #set($Bandar="")                             
     #else                             
     #foreach($lb in $list_bandar)      
     #if($socBandar==$lb.ID_BANDAR)                                      
     #set($Bandar="$lb.KOD_BANDAR - $lb.NAMA_BANDAR")
     #end
     #end                            
     #end   
     $!Bandar   
    <input name="Bandar" type="hidden" id="Bandar" value="$Bandar" size="50" readonly class="disabled">              
    #else    
    <select name="socBandar" class="autoselect" id="socBandar"  onchange="checking_validation(this,'socBandar_check','no','bandar','normal');" >  
    #if($socBandar != "")
    
    #foreach($lb in $list_bandar) 
    #if($socBandar==$lb.ID_BANDAR)
    <option value="$lb.ID_BANDAR">$lb.KOD_BANDAR - $lb.NAMA_BANDAR</option>                                     
    #end 
    #end 
   
    #foreach($lb in $list_bandar)
    #if($socBandar!=$lb.ID_BANDAR)
    <option value="$lb.ID_BANDAR">$lb.KOD_BANDAR - $lb.NAMA_BANDAR</option>                                     
    #end      
    #end
    <option value="">SILA PILIH BANDAR</option> 
    
    #else
   
    <option value="">SILA PILIH BANDAR</option>        
    #foreach($lb in $list_bandar)   
    <option value="$lb.ID_BANDAR">$lb.KOD_BANDAR - $lb.NAMA_BANDAR</option>
    #end
    
    #end
    
    </select>  
             
    #end
          <span id="socBandar_check" class = "alert_msg" ></span>        </td>
      </tr>
       
       
       <tr>
        <td></td>
        <td valign="top">
        
    
     Catatan   </td>
        <td valign="top">:</td>
        <td>
        
        #if($readmode == "view")
$txtCatatanPB
#else

        
         <textarea name="txtCatatanPB" id="txtCatatanPB" cols="50"   rows="6" style=""         
         onBlur="check_length(this,'4000','txtCatatanPB_check','txtCatatanPB_num','normal','no','catatan');"  
         onKeyup="check_length(this,'4000','txtCatatanPB_check','txtCatatanPB_num','normal','no','catatan');" 
         onKeydown="check_length(this,'4000','txtCatatanPB_check','txtCatatanPB_num','normal','no','catatan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtCatatanPB</textarea> 
          
        
         #if($readmode == "edit")           
         <div><span id="txtCatatanPB_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         #else
         <input name="txtCatatanPB_num" id="txtCatatan_num" size="3" value="4000"  style=" display:none" > 
         #end
         <div id="txtCatatanPB_check" class="alert_msg" ></div>     
         
    
#end        </td>
      </tr>
      
      <tr>
        <td></td>
        <td >  </td>
        <td >&nbsp;</td>
        <td>
        
        <fieldset  style="width:50%" >
        <legend>
        Pilihan Borang        </legend>
        <table width="100%">
        <tr>
            <td  colspan="4"></td>
          </tr>
        <tr>
            <td  colspan="4"></td>
          </tr>
          <tr>
            <td width="25%">Borang C & D</td>
            <td width="1%">:</td>
            <td width="24%">
            
           #if($readmode == "edit")
            <input name="boxBorangC" id="boxBorangC" type="checkbox" $boxBorangC >
            #elseif($readmode == "view")
            #if($boxBorangC == "checked")            
            <img src="../img/validyes.png" alt="" border="0"/>
            #else
            <img src="../img/validno.png" alt="" border="0"/>
            #end
            #end            </td>
          </tr>
          <tr>
            <td>Borang E & F</td>
            <td>:</td>
            <td>
               #if($readmode == "edit")
            <input name="boxBorangE" id="boxBorangE" type="checkbox" $boxBorangE >
            #elseif($readmode == "view")
            #if($boxBorangE == "checked")            
            <img src="../img/validyes.png" alt="" border="0"/>
            #else
            <img src="../img/validno.png" alt="" border="0"/>
            #end
            #end            </td>
          </tr>
          <tr>
            <td>Borang G & H</td>
            <td>:</td>
            <td>
            
            #if($readmode == "edit")
            <input name="boxBorangG" id="boxBorangG" type="checkbox" $boxBorangG >
            #elseif($readmode == "view")
            #if($boxBorangG == "checked")            
            <img src="../img/validyes.png" alt="" border="0"/>
            #else
            <img src="../img/validno.png" alt="" border="0"/>
            #end
            #end            </td>
          </tr>
          <tr>
            <td>Borang K</td>
            <td>:</td>
            <td>
            
             #if($readmode == "edit")
            <input name="boxBorangK" id="boxBorangK" type="checkbox" $boxBorangK >
            #elseif($readmode == "view")
            #if($boxBorangK == "checked")            
            <img src="../img/validyes.png" alt="" border="0"/>
            #else
            <img src="../img/validno.png" alt="" border="0"/>
            #end
            #end            </td>
          </tr>
           <tr>
            <td  colspan="4"></td>
          </tr>
        </table>
        </fieldset>               </td>
      </tr>
       
    </table></td>
  </tr>
  <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskinipb('$id_hakmilikpb')">
      </label>
      <label>
         #if($socJenisPB == "40"  || $socJenisPB == "41" || $socJenisPB == "27" || $socJenisPB == "42" || $socJenisPB == "6")
         #end
      <label>    
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapusTurutHadir('$id_hakmilikpb','$id_pb','$id_kehadiran')">    
      </label>
      
      <!--
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">
      -->
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpanPB('$!id_permintaanukur','$id_hakmilikpb','$id_kehadiran','$id_pb','$checkSizeBahagianPB_size')">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal('$id_hakmilikpb')">
      </label>
    #end  
     
           </div>  
     <div class="TabbedPanelsContent">        </div>         </td>
  </tr>
  #if($readmode == "edit")
  <tr>
    <td colspan="4">#parse("app/ppt/PenarikanBalik_Alert.jsp")</td>
  </tr>
  #else
   <tr>
    <td colspan="4"></td>
  </tr>  
  #end
</table>
#end
</fieldset></td>
  </tr>
 
 
 

<tr>
<td>

<fieldset id="senarai_siasatan">
<legend>SENARAI PIHAK BERKEPENTINGAN  <input type="button" value="Tambah Pihak Berkepentingan" onclick="tambahWakil()" /></legend>

<a href="javascript:popupCarianPB('$id_hakmilik','skrin_pb_PU','$!id_hakmilikpb')"><font color="blue">>> SKRIN CAPAIAN PIHAK BERKEPENTINGAN</font></a>	

<!--
        #if($list_kehadiran.size() > 10)
       <div style="width:100%;height:100;overflow:auto">#end
          <table width="100%" >
              <tr>
                <td colspan="8"><table width="100%" >
                    <tr>
                      <td align="left" width="50%" valign="top"><input type="button" value="Tambah Pihak Berkepentingan" onclick="tambahWakil()" />
                        #if($list_kehadiran.size() > 0)
                        <input type="button" value="Simpan Pilihan Borang" onclick="Simpan_Borang()" />
                        #end
                        
                        
                        
                        #if($list_kehadiran.size()!=0)
                    
                        #end </td>
                      <td width="50%" align="right" valign="top"> #if($list_kehadiran.size()!=0)Nama /
                        No. KP PB :
                        <label>
                          <input name="CariPB" type="text" id="CariPB"  value="$!CariPB" size="20" maxlength="150" />
                          </label>
                          <label>
                          <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Cari" onclick="PB('$id_siasatan')" />
                          </label>
                          <label>
                          <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Kosongkan" onclick="PB_Kosong('$id_siasatan')"  />
                          </label>
                          <div style="display:none"> Kehadiran PB : <span id="jumlahlot"  ></span> </div>
                        #end </td>
                    </tr>
                </table></td>
              </tr>
            #if($list_kehadiran.size() > 0)
            <tr class="table_header">
              <td width="3%">Bil</td>
              <td width="30%">Nama Pihak Berkepentingan</td>
              <td width="15%"><div align="left">Jenis Pihak Berkepentingan</div></td>
              <td width="18%"><div align="left">Bahagian / Syer </div>
          <div align="left"> & </div>
                <div align="left">Berkongsi Bahagian (Jika Ada)</div></td>
              #if($list_kehadiran.size() > 0)
              <td  width="8%"><div align="center">Borang </div>
          <div align="center">C & D </div>
                <div align="center">
            <input type="checkbox" name="all1" id="all1" onclick="doCheckAll1('$list_kehadiran.size()');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang C & D" />
        </div></td>
              #end
              <td  width="8%"><div align="center">Borang</div>
          <div align="center">E & F</div>
                <div align="center">
            <input type="checkbox" name="allborangE" id="allborangE" onclick="doCheckAllBorangE('$list_kehadiran.size()');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang E & F" />
        </div></td>
              <td  width="8%"><div align="center">Borang</div>
          <div align="center">G & H</div>
                <div align="center">
            <input type="checkbox" name="allborangG" id="allborangG" onclick="doCheckAllBorangG('$list_kehadiran.size()');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang G & H" />
        </div></td>
              <td  width="8%"><div align="center">Borang</div>
          <div align="center">K</div>
                <div align="center">
            <input type="checkbox" name="allborangK" id="allborangK" onclick="doCheckAllBorangK('$list_kehadiran.size()');" title="Semak untuk menunjukkan semua PB disenaraikan di Borang K" />
        </div></td>
              <td width="2%"><div align="center" class="font1"><font style="cursor:help" title="info"  onmouseout="close_window()"   onmouseover="open_new_window('1');this.style.cursor='help'" > 
              #parse("app/ppt/infoblink_putih.jsp")
               </font> </div></td>
            </tr>
            #else
            <tr class="table_header">
              <td width="3%">Bil</td>
              <td width="30%">Nama Pihak Berkepentingan</td>
              <td width="15%"><div align="left">Jenis Pihak Berkepentingan</div></td>
              <td width="18%"><div align="left">Bahagian / Syer </div>
          <div align="left"> & </div>
                <div align="left">Berkongsi Bahagian (Jika Ada)</div></td>
              #if($list_kehadiran.size() > 0)
              <td  width="8%"><div align="center">Borang </div>
          <div align="center">C & D </div>
                <div align="center"> </div></td>
              #end
              <td  width="8%"><div align="center">Borang</div>
          <div align="center">E & F</div></td>
              <td  width="8%"><div align="center">Borang</div>
          <div align="center">G & H</div></td>
              <td  width="8%"><div align="center">Borang</div>
          <div align="center">K</div></td>
              <td width="2%"><div align="center" class="font1"><font style="cursor:help" title="info"  onmouseout="close_window()"   onmouseover="open_new_window('1');this.style.cursor='help'" > 
              
           #parse("app/ppt/infoblink_putih.jsp")
              
              </font>
              
              
               </div></td>
            </tr>
            #end
            
            
            #if($list_kehadiran.size() > 0)
            #foreach($list in $list_kehadiran)        
            
            #set( $i = $velocityCount )
            #if ( ($i % 2) != 1 )
            #set( $row = "row2" )
            #else
            #set( $row = "row1" )
            #end
            <tr>
                           <td colspan="5"><fieldset id="$list.BILTEMP" class="$row" style="visibility:collapse; display:none;">
                             <table width="100%"   >
                               <tr>
                                 <td width="1%">&nbsp;</td>
                                 <td width="99%"><div align="left"><a href="javascript:paparpb('$list.ID_HAKMILIKPB');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">KEMASKINI MAKLUMAT PB</font></a></div></td>
                               </tr>
                             </table>
                             <table width="100%"   >
                               <tr>
                                 <td width="50%" valign="top"><table width="100%">
                                     <tr>
                                       <td width="1%">&nbsp;</td>
                                       <td width="28%">Jenis Pihak Berkepentingan</td>
                                       <td width="1%" >:</td>
                                       <td width="70%">$list.JENISPB</td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>Kehadiran</td>
                                       <td>:</td>
                                       <td> #set($hadir = "TIDAK HADIR")
                                         #if($list_check_kehadiran.size()>0)
                                         #foreach($ch in $list_check_kehadiran)    
                                         #if($ch.ID_HAKMILIKPB == $list.ID_HAKMILIKPB && $ch.FLAG_HADIR == "1")    
                                         #set($hadir = "HADIR")   
                                         #end    
                                         #end    
                                         #end
                                         
                                         
                                         $hadir </td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>Nama PB</td>
                                       <td>:</td>
                                       <td>$list.NAMA_PB </td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>Nama PB (KP)</td>
                                       <td>:</td>
                                       <td>$list.NAMA_KP</td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>Jenis No. PB</td>
                                       <td>:</td>
                                       <td>$list.JENISNOPB</td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>No. PB</td>
                                       <td>:</td>
                                       <td>$list.NO_PB</td>
                                     </tr>
                                 </table></td>
                                 <td width="50%" valign="top"><table width="100%">
                                     <tr>
                                       <td width="1%">&nbsp;</td>
                                       <td width="28%">No. Akaun</td>
                                       <td width="1%">:</td>
                                       <td width="70%">$list.NO_AKAUN</td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>Nama Bank</td>
                                       <td>:</td>
                                       <td>$list.NAMA_BANK</td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>Alamat PB</td>
                                       <td>:</td>
                                       <td>$list.ALAMAT1</td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>&nbsp;</td>
                                       <td>:</td>
                                       <td>$list.ALAMAT2</td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>&nbsp;</td>
                                       <td>:</td>
                                       <td>$list.ALAMAT3</td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>Poskod</td>
                                       <td>:</td>
                                       <td>$list.POSKOD</td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>Bandar</td>
                                       <td>:</td>
                                       <td>$list.NAMA_BANDAR</td>
                                     </tr>
                                     <tr>
                                       <td>&nbsp;</td>
                                       <td>Negeri</td>
                                       <td>:</td>
                                       <td>$list.NAMA_NEGERI</td>
                                     </tr>
                                 </table></td>
                               </tr>
                             </table>
                           </fieldset></td>
            </tr>
                         <tr  class="$row" >
                           <td >$list.BIL</td>
                           <td id="$list.NO_PB"><div align="left"><a href="javascript:paparpb('$list.ID_HAKMILIKPB');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">$list.NAMA_PB</font></a>
                           <div>$list.KETERANGAN_JENIS_PB</div>
   
                           </div></td>
                           <td>$list.JENISPB</td>
                           <td width="13%"><div align="left"> #if($list.SYER_ATAS != "" && $list.SYER_BAWAH != "")
                             $list.SYER_ATAS / $list.SYER_BAWAH    
                             #end </div>
                             #set($count = 0) 
                             #foreach($list1 in $senarai_pihak_penting_bahagian)
                             #if($list1.ID_BAHAGIANPB == $list.ID_BAHAGIANPB && $list1.ID_HAKMILIKPB != $list.ID_HAKMILIKPB ) 
                             #set($count=$count + 1) 
                             #end
                             #end
                             
                             #if($count > 0)
                             <div> #set($count_total = 0) 
                               #foreach($list1 in $senarai_pihak_penting_bahagian)
                               #if($list1.ID_BAHAGIANPB == $list.ID_BAHAGIANPB && $list1.ID_HAKMILIKPB != $list.ID_HAKMILIKPB)
                               #set($count_total=$count_total + 1)                      
                               #if($count > 1 && $count != $count_total && $count_total != $count - 1 ) 
                               $list1.NAMA_PB,
                               #elseif($count > 1 && $count != $count_total && $count_total == $count - 1) 
                               $list1.NAMA_PB &
                               #elseif($count > 1 && $count == $count_total)
                               $list1.NAMA_PB
                               #elseif($count == 1)
                               $list1.NAMA_PB
                               #end                                          
                               #end
                               #end </div>
                             #end </td>
                           <td> #set($boxC = "")  
                           
                          
                          
                          
                             #if($list.FLAG_BORANGC == "1" )    
                             #set($boxC = "checked")   
                             #end
                             <div align="center">
              <input type="checkbox" name="ids1" id="ids1" onclick="doUpdateCheckAll1('$list_kehadiran.size()')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang C & D" $boxC value="$list.ID_HAKMILIKPB" />
            </div>
                             <input type="hidden" name="idPB" id="idPB" value="$list.ID_HAKMILIKPB" />                           </td>
                           <td> #set($boxE = "")    
                             #if($list.FLAG_BORANGE == "1" )    
                             #set($boxE = "checked")   
                             #end
                             <div align="center">
              <input type="checkbox" name="borangE" id="borangE" onclick="doUpdateCheckAllBorangE('$list_kehadiran.size()')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang E & F" $boxE value="$list.ID_HAKMILIKPB" />
          </div></td>
                           <td> #set($boxG= "")    
                             #if($list.FLAG_BORANGG == "1" )    
                             #set($boxG = "checked")   
                             #end
                             <div align="center">
              <input type="checkbox" name="borangG" id="borangG" onclick="doUpdateCheckAllBorangG('$list_kehadiran.size()')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang G & H" $boxG value="$list.ID_HAKMILIKPB" />
          </div></td>
                           <td> #set($boxK = "")    
                             #if($list.FLAG_BORANGK == "1" )    
                             #set($boxK = "checked")   
                             #end
                             <div align="center">
              <input type="checkbox" name="borangK" id="borangK" onclick="doUpdateCheckAllBorangK('$list_kehadiran.size()')" title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang K" $boxK value="$list.ID_HAKMILIKPB" />
          </div></td>
                           <td></td>
                         </tr>
            #end
            #else
            <tr>
              <td colspan="5">Tiada Rekod</td>
            </tr>
            #end
          </table>
          #if($list_kehadiran.size() > 10) </div>
        #end
        -->
</fieldset></td>
</tr>
</table>




            </div>
  			
  			
  			<!-- START TAB 3 -->
  			<div class="TabbedPanelsContent">
  			
  				
  				<fieldset>
                <div id="div_pelarasan"></div>
                </fieldset>
  			
  			
  				
  			
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
  						<td><input type="text" name="txtTempoh" id="txtTempoh" value="" size="5" maxlength="10" onkeyup="validateNumber(this,this.value)" ></td>
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
  						<td><input $disability $disabilityx type="text" name="txtTempoh" id="txtTempoh" value="$!txtTempoh" size="5" maxlength="10" onkeyup="validateNumber(this,this.value)" ></td>
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
        		<legend><strong>&nbsp;Senarai Dokumen yang Disertakan</strong></legend>
                <table width="100%" >  
			        	
			        	<tr>
			        	  <td >
                <a href="javascript:popupCarianDokumen('$id_permintaanukur','dokumen_pu')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
                </td>
                </tr>
                </table>
                </fieldset>
            
            
  			<!--
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
    			-->
                <table align="left" width="100%"  cellpadding="0" border="0">
			    	<tr align="center">
			        	<td>
			              <!--  <input type="button" value="Simpan" onClick="uploadDokumen('$!id_permohonan','$!id_hakmilik')">   -->
			                <input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
			            </td>
			        </tr>
			    </table>
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


<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<input type="hidden" name="mode" value='$!mode'>
<input type="hidden" name="isEdit" value='$!isEdit'>


<input type="hidden" name="selectedTabPelarasan" id="selectedTabPelarasan"  value="$!selectedTabPelarasan"  />

 <input type="hidden" name="location" id="location" />
  <input type="hidden" name="point" id="point" />
  
  <input type="hidden" name="id_pb" id="id_pb" value="$!id_pb" />
  <input type="hidden" name="id_kehadiran" id="id_kehadiran" value="$!id_kehadiran" />

  
  <input type="hidden" name="totalSyer" value="$!totalSyer">

#if($currentcommand=="pelarasanLuas")
<script> 

$jquery(document).ready(function () {	
		doDivAjaxCall$formname('div_pelarasan','getPelarasan','');	
});


</script>
#end


	#if($hideAdd=="yes")
	<script>
		alert("Bahagian PB Telah Mencukupi");
	</script>
	#elseif($hideAdd=="notcomplete")
	<script>
		alert("Bahagian PB Masih Tidak Mencukupi");
	</script>
	#end


<script>

checking_validation(document.${formName}.socJenisPB,'socJenisPB_check','yes','jenis pihak berkepentingan','normal');
checking_validation(document.${formName}.txtNamaPB,'txtNamaPB_check','yes','nama pihak berkepentingan','normal');
checking_validation(document.${formName}.txtNamaPBKP,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal');
checking_validation(document.${formName}.socJenisNOPB,'socJenisNOPB_check','no','jenis no. pihak berkepentingan','normal_j');
checking_validation(document.${formName}.txtNoPB,'txtNoPB_check','no','no PB','normal_kp');
checking_validation(document.${formName}.txtNoAkaun,'txtNoAkaun_check','no','no akaun','normal');
checking_validation(document.${formName}.txtAlamat1PB,'txtAlamat1PB_check','no','alamat PB','normal');
checking_validation(document.${formName}.txtPoskodPB,'txtPoskodPB_check','no','','poskod');
checking_validation(document.${formName}.socNegeri,'socNegeri_check','no','negeri','normal');
checking_validation(document.${formName}.socBangsa,'socBangsa_check','no','bangsa','normal');
checking_validation(document.${formName}.socWarga,'socWarga_check','no','warga','normal');
checking_validation(document.${formName}.socBandar,'socBandar_check','no','bandar','normal');
checking_validation(document.${formName}.txtNoHP,'txtNoHP_check','no','no telefon bimbit','normal');
checking_validation(document.${formName}.txtNoTel,'txtNoTel_check','no','no telefon rumah / pejabat','normal');
checking_validation(document.${formName}.txtNamaBank,'txtNamaBank_check','no','nama bank','normal');
check_length(document.${formName}.txtCatatanPB,'4000','txtCatatanPB_check','txtCatatanPB_num','normal','no','catatan');
check_length(document.${formName}.txtKeteranganPB,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');


function popupCarianPB_salinNoKP(id_hakmilik,flag_skrin)
{
	var no_pb = document.${formName}.txtNoPB.value;	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupSalinPBHakmilik?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&NO_PB="+no_pb;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function popupCarianPB_salinNama(id_hakmilik,flag_skrin)
{
	var nama_pb = document.${formName}.txtNamaPB.value;	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupSalinPBHakmilik?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&NAMA_PB="+nama_pb;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}



function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{

	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
	   }
	   else
	   {	  
	   if (my_form.value.length >= maxLen) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
my_form.value = my_form.value.substring(0, maxLen);
       maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");
}



function checking_validation(field,point,mandatory,value_field,jenis_field){	



   	var lepas_or_xlepas = 1;
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   	
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	 
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
       /*	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   else
	   {
	  
	   /*
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini");
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;

	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName); */
	//   DateField.focus();
    
     $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+" dengan betul");	
	 
	   
	   }
	   
	   }
	   
	// 
	   if(jenis_field == "normal")
	   {
	   
	  
		
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
	 	
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
	 		
	   }
	   
	   	   
	   }
	   
       
       
       
       
       if(jenis_field == "normal_kp")
	   {
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   if(lepas_or_xlepas == "2")
	   {      
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	 	
	   }
	   else
	   {
        
       if(document.${formName}.socJenisNOPB.value == "1" )
       {
       
        field.value = field.value.substring(0,12);	       
        if (isNaN(field.value)) {
            field.value = RemoveNonNumeric2(field.value);
            
        }  
             
       if(field.value.length != 12 )
       {
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila lengkapkan kad pengenalan baru pb");         
       }
       else
       {
       
       
    if(field.value.charAt(0)<2)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}

	
var tt = field.value.charAt(4)+""+field.value.charAt(5)+"/"+field.value.charAt(2)+""+field.value.charAt(3)+"/"+dum+field.value.charAt(0)+""+field.value.charAt(1);
       
       
    var checkstr = "0123456789";
	var DateField = tt;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
        err = 0;
	
	
	   DateValue = tt;	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField = day + seperator + month + seperator + year;
		   
	   }
	  
	   else {	   	
		  lepas_or_xlepas = "3";	   
	   }
	  



	 
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = tt;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
       
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   else
	   {	  
	  
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh lahir "+value_field+" tidak melebihi dari tarikh hari ini");
	   }
	   
	   
	   }   
	   else if(lepas_or_xlepas == "3")
	   {    
     $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh lahir "+value_field+" dengan format betul");	
	 	   
	   }
	   else
	{
      $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
    }
     	
       }
       
       }
       else
       {
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
       }      
        		
	   }
	   
	   	   
	   }
	   
       
       if(jenis_field == "normal_j")
	   {
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   if(lepas_or_xlepas == "2")
	   {      
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	 	
	   }
	   else
	   {
        
       if(field.value == "1" )
       {
       
        document.${formName}.txtNoPB.value = document.${formName}.txtNoPB.value.substring(0,12);	       
        if (isNaN(document.${formName}.txtNoPB.value)) {
            document.${formName}.txtNoPB.value = RemoveNonNumeric2(document.${formName}.txtNoPB.value);
          
        }  
         
         
       if(document.${formName}.txtNoPB.value.length != 12 )
       {
       $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila lengkapkan kad pengenalan baru pb");         
       }
       else
       {
       
   		
  if(document.${formName}.txtNoPB.value.charAt(0)<2)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}

	
var tt = document.${formName}.txtNoPB.value.charAt(4)+""+document.${formName}.txtNoPB.value.charAt(5)+"/"+document.${formName}.txtNoPB.value.charAt(2)+""+document.${formName}.txtNoPB.value.charAt(3)+"/"+dum+document.${formName}.txtNoPB.value.charAt(0)+""+document.${formName}.txtNoPB.value.charAt(1);
       
       
    var checkstr = "0123456789";
	var DateField = tt;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
        err = 0;
	
	
	   DateValue = tt;	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField = day + seperator + month + seperator + year;
		   
	   }
	  
	   else {	   	
		  lepas_or_xlepas = "3";	   
	   }
	  
       
		
        
        
	   if(lepas_or_xlepas == "1")
	   {
	      
          
           var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = tt;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
       
        $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   else
	   {	  
	  
      $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh lahir "+value_field+" tidak melebihi dari tarikh hari ini");
	   }          
	   } 
        else if(lepas_or_xlepas == "3")
	   {    
     $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh lahir "+value_field+" dengan format betul");	
	 	   
	   }
	   else
	{
      $jquery("#txtNoPB_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
    }
     	
        


     
   
       }
       }
       else
       {
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	
       }      
        		
	   }
	   
	   	   
	   }
	   
	   
	   	   if(jenis_field == "radio")
	   {
	  
	  
	    var r = 0;
		if(field.length > 1)
		{     
			  for (i = 0; i < field.length; i++)
			  {
			  if (field[i].checked == true)
			  {	 
			  r++
			  }
			  }  
		}
		else
		{
		if (field.checked == true)
		{	 
		r++;
		}	 	
		}  
	   
	     
	   if((r == 0) && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	  
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila pilih "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");	
	 	
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	 
	   }
	   
	   	   
	   }
	   
	   
	   
	    if(jenis_field == "file") 
	    {
	   	var allBlank = true;
		if(document.${formName}.fileupload!=null)
		{
		
		for( var i=0,e=document.${formName}.fileupload;i<e.length; i++ )
		{			
		if( e[i].type=='file' )
		{	
		if( e[i].value.length  )
		{
		
		allBlank=false;	
		}	
		}	
		}
		
		}
		else
		{
		allBlank=false;	
		}
		
		
		if(allBlank == true)
	    {
        /*
		   document.${formName}.alert_message.value  = "Sila masukkan dokumen terlebih dahulu";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);*/
           $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");	
	 	
	    }
		else
		{
		  /* document.${formName}.alert_message.value  = "";	  
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = point;
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		
		*/
         $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	 	
		}
		
		
	   
	   }
	   
	   
	   
	   if(jenis_field == "waktu")
	   {
	   
		
	if(field.value == "" && mandatory == "yes")
	{
	lepas_or_xlepas = 2;
    }  
	else if(field.value != "" && (mandatory == "yes" || mandatory == "no"))
	{	   
	if (field.value.length < 4) {
	lepas_or_xlepas = 3;
	}
	else if (field.value.charAt(0) > 1)  {
	lepas_or_xlepas = 3;
	}	
	else if ((field.value.charAt(0) == 1) && (field.value.charAt(1) >2 )) {
	lepas_or_xlepas = 3;
	}
	else if ((field.value.charAt(2) > 5) ) {
	lepas_or_xlepas = 3;
	}
	else if ((field.value.charAt(3) > 9) ) {
	lepas_or_xlepas = 3;
	}
	}  
	  
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan waktu "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");	
	 		
	   }
	   else if(lepas_or_xlepas == "3")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan waktu "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
       */
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan waktu "+value_field+" dengan format yang betul");	
	 	
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
         $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   
	   	    
	   }
	   
	   
	   
	   
	   if(jenis_field == "poskod")
	   {
	   
		
	if(field.value == "" && mandatory == "yes")
	{
	lepas_or_xlepas = 2;
    }  
	else if(field.value != "" && (mandatory == "yes" || mandatory == "no"))
	{	   
	if (field.value.length < 5) {
	lepas_or_xlepas = 3;
	}	
	}  
	  
	   if(lepas_or_xlepas == "2")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan poskod "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");		
	   }
	   else if(lepas_or_xlepas == "3")
	   {
       /*
	   document.${formName}.alert_message.value  = "Sila masukkan poskod "+value_field+" dengan format yang betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan poskod "+value_field+" dengan format yang betul");		
		
	   }
	   else
	   {
       /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
        $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");	
	   }
	   
	   	    
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

function simpanPB(id_permintaanukur,id_hakmilikpb,id_kehadiran,id_pb,sizeBahagian)
{
	
var c = 0;

	//syer
	var sA = parseFloat(document.${formName}.txtSyorAtas.value);
	var sB = parseFloat(document.${formName}.txtSyorBawah.value);

	//value syer atas n bawah
	var valA = document.${formName}.txtSyorAtas.value;
	var valB = document.${formName}.txtSyorBawah.value;
	
	//total syer
	var totalSyer = 0.00;
	
	
	
	if(document.${formName}.totalSyer.value!="")
	{totalSyer = parseFloat(document.${formName}.totalSyer.value);}

	var newTotal = 0.00;
	var bhg_temp = 0.00;
	
	if((document.${formName}.txtSyorAtas.value!= "") && (document.${formName}.txtSyorBawah.value!= "")){

		var bhg = sA / sB;
		
		if((document.${formName}.txtSyorAtas_temp.value!= "") && (document.${formName}.txtSyorBawah_temp.value!= ""))
		{		
		bhg_temp = parseFloat(document.${formName}.txtSyorAtas_temp.value) / parseFloat(document.${formName}.txtSyorBawah_temp.value);
		newTotal = totalSyer + bhg - bhg_temp;
		}
		else
		{
		newTotal = totalSyer + bhg;
		}
		
	}
	
	
	
if(document.${formName}.validation_field != null  )
{

   if (document.${formName}.validation_field.length == null)
   {	
    
   if(document.${formName}.validation_field.value == "invalid")
   {  
   
   c++;	
   } 
   	
   } 
   
   else 
   {
   
        for (i = 0; i < document.${formName}.validation_field.length; i++)
		{		
			if(document.${formName}.validation_field[i].value == "invalid")
			{
			
               c++;	 
			}  	
        }
    }	
}

//alert("c");

/*
if(c>0){
alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
return;
}*/



if(document.${formName}.socJenisPB.value == "")
{
        alert("Sila pastikan jenis PB dipilih");
		document.${formName}.socJenisPB.focus();
		return
}
else if(document.${formName}.txtNamaPB.value == "")
{
		alert("Sila pastikan nama PB diisi");
		document.${formName}.txtNamaPB.focus();
		return
}
else if (((valA != "") && (valB !="")) && (sA > sB)) 
{
		alert("Sila pastikan \"Syer Atas\" tidak melebihi \"Syer Bawah\"");
		document.${formName}.txtSyorAtas.focus();
		return
}
else if ((valA != "" && valB == "") || (valA == "" && valB !="")) 
{
		alert("Sila pastikan \"Bahagian / Syer PB\" lengkap diisi");
		document.${formName}.txtSyorAtas.focus();
		return
}
else if(document.${formName}.id_bahagianpb.value == "" && ((((valA != "") && (valB != "")) && (newTotal>1.01))))
{
		alert("Sila pastikan \"Bahagian / Syer PB\" tidak melebihi jumlah keseluruhan");
		document.${formName}.txtSyorAtas.focus();
		return
}
else
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
			
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "PB";
	document.${formName}.command2.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	if(id_hakmilikpb!="")	
	{
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	}
	
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.id_kehadiran.value = id_kehadiran;
	document.${formName}.id_pb.value = id_pb;	
	
	document.${formName}.submit();
	
	}
}
}

function hapusTurutHadir(id_hakmilikpb,id_pb,id_kehadiran)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";	
    document.${formName}.command.value = "PB";
	document.${formName}.command2.value = "DeleteTurutHadir";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.id_kehadiran.value = id_kehadiran;
	document.${formName}.id_pb.value = id_pb;
	document.${formName}.submit();
    }
}

function salin_pb(id_hakmilikpb_salin)
{
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";	
    document.${formName}.command.value = "PB";
	document.${formName}.command2.value = "salin_pb";
	document.${formName}.id_hakmilikpb_salin.value = id_hakmilikpb_salin;	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();	
}

function paparpb(id_hakmilikpb)
{
	//alert("SSSSSSSSSSSS :"+id_hakmilikpb);
	
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";	
    document.${formName}.command.value = "PB";
	document.${formName}.command2.value = "Papar";	
	//alert("XXXXXXXXXXXX :"+id_hakmilikpb);
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	//alert("YYYYYYYYYYYY :"+id_hakmilikpb);
	document.${formName}.submit();
}



function kemaskinipb(id_hakmilikpb)
{
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";	
    document.${formName}.command.value = "PB";
	document.${formName}.command2.value = "Kemaskini";			
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.submit();
}



function tambahWakil()
{
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "PB";
	document.${formName}.command2.value = "tambah_wakil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();
	
}
function getBandar(id_hakmilikpb)
{
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
    
	document.${formName}.command.value = "PB";
	document.${formName}.command2.value = "getBandar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socBandar";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.submit();
}


function PB(id_permintaanukur)
{
	document.${formName}.tabId.value = "2";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "PB";
	document.${formName}.command2.value = "View";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.submit();
}
function popupCarianPB(id_hakmilik,flag_skrin,id_hakmilikpb)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianPB?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&id_hakmilikpb="+id_hakmilikpb;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}


function popupCarianDokumen(id_permintaanukur,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_permintaanukur="+id_permintaanukur+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}


function popupEtanah(id_fail,id_permohonan,id_hakmilik,jenis_skrin,command) {

	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&id_hakmilik="+id_hakmilik+"&jenis_skrin="+jenis_skrin+"&command="+command;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}

function klikTab(tab_id)
{
document.${formName}.selectedTabPelarasan.value = tab_id;	
}
/*
function printDiv()
{
//window.location.href = CollinsPDF(document.getElementById(id).innerHTML,'return as DataUrl'); 

var html = "";
var css = "<style type=\"text/css\">"+
		  "td.td_total_pelarasan{"+
		  "border-top:1px solid blue;"+
		  "border-bottom:3px  double blue;"+
		  "}"+
		  "td.td_total_pengiraan{"+
		  "border-top:1px solid black;"+
		  "}"+		
		  "</style> ";
		  html = css;
		  
		  html = html + "<table  align=\"center\" width=\"100%\"><tr><td valign=\"top\" width=\"5%\"></td><td valign=\"top\" width=\"85%\">";
		  html = html + "<br><br>&nbsp;&nbsp;<b><font style=\"font-size:12px;font-family:Arial;\" >PELARASAN PERBEZAAN LUAS</font></b>";
		  html = html + "<br><br>";
		  html = html + "<div style=\"font-size:12px;font-family:Arial;\" >"+document.getElementById('print_1').innerHTML+"</div>";
		  html = html + "<br>";
		  html = html + "<div style=\"font-size:12px;font-family:Arial;\" >"+document.getElementById('print_2').innerHTML+"</div>";
		  html = html + "<br>";
		  html = html + "<div style=\"font-size:12px;font-family:Arial;\" >"+document.getElementById('print_4').innerHTML+"</div>";
  		  html = html + "</td><td valign=\"top\" width=\"10%\"></td></tr></table>";

window.location.href = CollinsPDF(html,'Return as DataURL');
}
*/

function setTab(tab_index)
{
	
	
	
	//alert("tab_index :"+tab_index);
	var tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan");
	
	if(tab_index == null || tab_index == "")
	{
	//alert("tab_index 1:"+tab_index);	
	document.${formName}.selectedTabPelarasan.value = "0";
	tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:0});	
	}
	else
	{
	//alert("tab_index 2:"+tab_index);
	document.${formName}.selectedTabPelarasan.value = tab_index;	
	
		if(tab_index==0)
		{
		tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:0});
		}
		else if(tab_index==1)
		{
		tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:1});
		}
		else if(tab_index==2)
		{
		tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:2});
		}
		else if(tab_index==3)
		{
		tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:3});
		}	
		else if(tab_index==4)
		{
		tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:4});
		}	
		
	}
	//alert("4");
}

function kiraBezaLuasNew(saiz_pb) {
	//alert("1");
	var luas_baru = parseFloat(document.${formName}.txtKeluasanPU.value);
	var baki_luas = parseFloat(document.${formName}.bakiLuasNew.value);
	var nilai_seunit_field = parseFloat(document.${formName}.NilaiSeunit.value);
	
	var total_bayaran_lewat = 0;
    var total_jumlah_hari = 0;

	var beza_luas = 0;
	var beza_luas_mp = 0;
	var nilai_seunit = 0;
	
	
	if (!isNaN(luas_baru) && !isNaN(baki_luas)){
		beza_luas =  baki_luas - luas_baru;
		beza_luas_mp = beza_luas * 10000;
		
		if(beza_luas_mp < 0)
		{
			beza_luas_mp = beza_luas_mp*-1;
		}
	}
	
	if (!isNaN(nilai_seunit_field)){
		nilai_seunit = nilai_seunit_field;
	}

	document.${formName}.countBezaLuas.value = beza_luas.toFixed(4);
	document.${formName}.countBezaLuasMp.value = beza_luas_mp.toFixed(4);
	$jquery("#beza_luas").html("<font color='blue'><b>"+beza_luas.toFixed(4)+"</b></font>");		
	$jquery("#beza_luas_mp").html("<font color='blue'>"+beza_luas_mp.toFixed(4)+"</font>");	 
	$jquery("#jumlah_nilai").html("<font color='blue'><b>RM "+formatCurrency(beza_luas_mp*nilai_seunit)+"</b></font>");	 
	$jquery("#jumlah_nilai_bhg").html("<font color='blue'><b>RM "+formatCurrency(beza_luas_mp*nilai_seunit)+"</b></font>");	 
	
	
	 
	if(beza_luas > 0){
		document.${formName}.sorJenisPelarasan.value = "1";
		document.${formName}.hdJenisPelarasan.value = "1";
		 $jquery("#jenis_pelarasan").html("<font color='blue'>PAMPASAN TAMBAHAN</font>");	 
		 $jquery("#jenis_pelarasan_nilai").html("<font color='blue'>(PAMPASAN TAMBAHAN)</font>");	 
		 $jquery("#jenis_pelarasan_nilai_bhg").html("<font color='blue'>(PAMPASAN TAMBAHAN)</font>");	
		 document.getElementById('fs_bayaran_lewat').style.display=""; 
		 document.getElementById('fs_pelarasan_bayaran_lewat').style.display=""; 
		 document.getElementById('tab_caj_1').style.display="";
		 document.getElementById('tab_caj_2').style.display=""; 	
		 document.getElementById('tab_caj_3').style.display="";	 	 
	}else if(beza_luas < 0){
		document.${formName}.sorJenisPelarasan.value = "2";
		document.${formName}.hdJenisPelarasan.value = "2";
		$jquery("#jenis_pelarasan").html("<font color='blue'>PENGURANGAN PAMPASAN</font>");	
		$jquery("#jenis_pelarasan_nilai").html("<font color='blue'>(PENGURANGAN PAMPASAN)</font>");
		$jquery("#jenis_pelarasan_nilai_bhg").html("<font color='blue'>(PENGURANGAN PAMPASAN)</font>");
		document.getElementById('fs_bayaran_lewat').style.display="none";
		document.getElementById('fs_pelarasan_bayaran_lewat').style.display="none"; 
		document.getElementById('tab_caj_1').style.display="none";
		document.getElementById('tab_caj_2').style.display="none"; 	
		document.getElementById('tab_caj_3').style.display="none";		 
	}else if(beza_luas == 0){
		document.${formName}.sorJenisPelarasan.value = "3";
		document.${formName}.hdJenisPelarasan.value = "3";
		$jquery("#jenis_pelarasan").html("<font color='blue'>KEKAL</font>");	
		$jquery("#jenis_pelarasan_nilai").html("<font color='blue'>(KEKAL)</font>");
		$jquery("#jenis_pelarasan_nilai_bhg").html("<font color='blue'>(KEKAL)</font>");
		document.getElementById('fs_bayaran_lewat').style.display="none"; 
		document.getElementById('fs_pelarasan_bayaran_lewat').style.display="none"; 
		document.getElementById('tab_caj_1').style.display="none";
		document.getElementById('tab_caj_2').style.display="none"; 	
		document.getElementById('tab_caj_3').style.display="none";		
	}else{
		document.${formName}.sorJenisPelarasan.value = "";
		document.${formName}.hdJenisPelarasan.value = "";
		$jquery("#jenis_pelarasan").html("");	
		$jquery("#jenis_pelarasan_nilai").html("");
		$jquery("#jenis_pelarasan_nilai_bhg").html("");
		document.getElementById('tab_caj_1').style.display="";
		document.getElementById('tab_caj_2').style.display=""; 	
		document.getElementById('tab_caj_3').style.display="";	
	}
	
	
	
	var frac_total = new Fraction(1,1);	
	var div_frac_nilai = 0;
	var fin_total = beza_luas_mp*nilai_seunit;
	for (var i=0;i<saiz_pb;i++)
	{
		var bhg_ats = document.getElementById('div_syer_atas'+(i+1)).value;
		var bhg_bwh = document.getElementById('div_syer_bawah'+(i+1)).value;
		
		if (!isNaN(bhg_ats) && !isNaN(bhg_bwh))
		{
		var div_total = ((fin_total*parseInt(bhg_ats))/parseInt(bhg_bwh));			
		$jquery("#div_bhg_per"+(i+1)).html("<font color = 'blue'>"+formatCurrency(div_total)+"</font>");	
		frac_total = frac_total.add(new Fraction(bhg_ats,bhg_bwh));
		div_frac_nilai = div_frac_nilai + div_total;
		}
		else
		{
		$jquery("#div_bhg_per"+(i+1)).html("");		
		}
	
	}
	frac_total = frac_total.subtract(1,1);
	$jquery("#div_frac_total").html("<b>"+frac_total+"</b>");	
	$jquery("#div_frac_nilai").html("<b>"+formatCurrency(div_frac_nilai)+"</b>");
	
	var dt_borangk = document.${formName}.dtBorangK.value;
	var dt_bayartambah = document.${formName}.tarikhBayarTambahan.value;
	var set_div_date = "";
	if(isDateValid(dt_bayartambah)==true && isDateValid(dt_borangk)==true)
	{
		var hari_bayar   = parseInt(dt_bayartambah.substring(0,2),10);
        var bulan_bayar  = parseInt(dt_bayartambah.substring(3,5),10);
        var tahun_bayar   = parseInt(dt_bayartambah.substring(6,10),10);	
		
		var hari_borangk   = parseInt(dt_borangk.substring(0,2),10);
        var bulan_borangk  = parseInt(dt_borangk.substring(3,5),10);
        var tahun_borangk   = parseInt(dt_borangk.substring(6,10),10);
		
		
		set_div_date = set_div_date + " <table width=\"100%\" border=\"0\"  align=\"left\"  >";
		set_div_date = set_div_date + " <tr><td>";
		
		set_div_date = set_div_date + " "
		+ "<table width=\"100%\" border=\"0\"  align=\"left\"  >  "
		+ " <tr class=\"table_header\">  "
		+ " <td align=\"center\"  width = \"25%\" >Tahun (Hari)</td> "
		+ " <td align=\"center\" width = \"50%\"  >Pengiraan</td> "
		+ " <td align=\"right\" width = \"25%\" >Jumlah Bayaran (RM)</td> "
		+ " </tr></table> ";
		
		set_div_date = set_div_date + " </td></tr>";
		
		
		if(parseInt(tahun_bayar)-parseInt(tahun_borangk) == 0)
		{
				var jumlah_hari = 0;
				var jumlah_hari_dalam_tahun = 0;
				set_div_date = set_div_date + " <tr><td>";
				set_div_date = set_div_date + "<table width=\"100%\" border=\"0\"  align=\"left\"  >  ";
				var myDate_borangk_last = new Date(tahun_bayar,bulan_bayar-1,hari_bayar);
				var myDate_borangk = new Date(tahun_borangk,bulan_borangk-1,hari_borangk);					
				jumlah_hari = DateDiff.inDays(myDate_borangk, myDate_borangk_last)+1;	
				jumlah_hari_dalam_tahun = daysInYear(tahun_borangk);				
				
				set_div_date = set_div_date + "<br><table width=\"100%\" border=\"0\"  align=\"left\"  >  ";
				set_div_date = set_div_date + "<tr >";
				set_div_date = set_div_date + " <td  width = \"25%\" align=\"center\"  >"+tahun_borangk+" (<b><font color='blue'>"+jumlah_hari+"</font></b>)</td> ";
				set_div_date = set_div_date + " <td  align=\"center\" width = \"50%\" >";				
				set_div_date = set_div_date + " <table width = \"70%\" align=\"center\" border = \"0\" >";
				set_div_date = set_div_date +"<tr><td align=\"center\"> ";
				set_div_date = set_div_date +"<b><font color='blue'>"+jumlah_hari+"</font></b> hari X RM <b><font color='blue'>"+formatCurrency(beza_luas_mp*nilai_seunit)+ "</font></b> X <b><font color='blue'>0.08</font></b>";
				set_div_date = set_div_date +"</td></tr> ";
				set_div_date = set_div_date +"<tr><td align=\"center\" class = \"td_total_pengiraan\" > ";
				set_div_date = set_div_date +"<b><font color='blue'>"+jumlah_hari_dalam_tahun+"</font></b>";
				set_div_date = set_div_date +"</td></tr> ";
				set_div_date = set_div_date +"</table> ";
				set_div_date = set_div_date +"</td> ";
				var bayaran_lewat = (jumlah_hari * (beza_luas_mp*nilai_seunit) * 0.08) / jumlah_hari_dalam_tahun;
				set_div_date = set_div_date + " <td  align=\"right\" width = \"25%\" ><font color='blue'>"+formatCurrency(bayaran_lewat)+"</font></td> "; 			
				set_div_date = set_div_date + "</tr>";
				total_bayaran_lewat = total_bayaran_lewat + bayaran_lewat;
				set_div_date = set_div_date + "</table>";
				set_div_date = set_div_date + " </td></tr>";
				
				total_jumlah_hari = total_jumlah_hari + jumlah_hari;
				
		}		
		else
		{
			set_div_date = set_div_date + " <tr><td>";
			set_div_date = set_div_date + "<table width=\"100%\" border=\"0\"  align=\"left\"  >  ";			
			for (var i=parseInt(tahun_borangk);i<=parseInt(tahun_bayar);i++)
			{
				var jumlah_hari = 0;
				var jumlah_hari_dalam_tahun = 0;
				
				jumlah_hari_dalam_tahun = daysInYear(i);
				var row = "";
				if ( (i % 2) != 1 )
              	{
					row = "row2";
				}
         		else
               	{
					row = "row1"
				}        		
				
				if(parseInt(tahun_borangk) == i)
				{
					var myDate_borangk_last = new Date(tahun_borangk,12-1,31);
					var myDate_borangk = new Date(tahun_borangk,bulan_borangk-1,hari_borangk);					
					jumlah_hari = DateDiff.inDays(myDate_borangk, myDate_borangk_last)+1;	
				}
				else if(parseInt(tahun_bayar) == i)
				{
					var myDate_bayar_first = new Date(tahun_bayar,1-1,1);
					var myDate_bayar = new Date(tahun_bayar,bulan_bayar-1,hari_bayar);					
					jumlah_hari = DateDiff.inDays(myDate_bayar_first, myDate_bayar)+1;						
				}
				else
				{
				jumlah_hari = daysInYear(i);
				}
				
				set_div_date = set_div_date + "<tr class="+row+">";
				set_div_date = set_div_date + " <td  width = \"25%\" align=\"center\"  >"+i+" (<b><font color='blue'>"+jumlah_hari+"</font></b>)</td> ";
				set_div_date = set_div_date + " <td  align=\"center\" width = \"50%\" >";				
				set_div_date = set_div_date + " <table width = \"70%\" align=\"center\" border = \"0\" >";
				set_div_date = set_div_date +"<tr><td align=\"center\"> ";
				set_div_date = set_div_date +"<b><font color='blue'>"+jumlah_hari+"</font></b> hari X RM <b><font color='blue'>"+formatCurrency(beza_luas_mp*nilai_seunit)+ "</font></b> X <b><font color='blue'>0.08</font></b>";
				set_div_date = set_div_date +"</td></tr> ";
				set_div_date = set_div_date +"<tr><td align=\"center\" class = \"td_total_pengiraan\" > ";
				set_div_date = set_div_date +"<b><font color='blue'>"+jumlah_hari_dalam_tahun+"</font></b>";
				set_div_date = set_div_date +"</td></tr> ";
				set_div_date = set_div_date +"</table> ";
				set_div_date = set_div_date +"</td> ";
				var bayaran_lewat = (jumlah_hari * (beza_luas_mp*nilai_seunit) * 0.08) / jumlah_hari_dalam_tahun;
				set_div_date = set_div_date + " <td  align=\"right\" width = \"25%\" ><font color='blue'>"+formatCurrency(bayaran_lewat)+"</font></td> "; 			
				set_div_date = set_div_date + "</tr>";
				total_bayaran_lewat = total_bayaran_lewat + bayaran_lewat;
				
				total_jumlah_hari = total_jumlah_hari + jumlah_hari;
			}
			set_div_date = set_div_date + "</table>";
			set_div_date = set_div_date + " </td></tr>";
		}
		set_div_date = set_div_date + " <tr><td>";
		set_div_date = set_div_date + " "
		+ "<table width=\"100%\" border=\"0\"  align=\"left\"  >  "
		+ " <tr class=\"table_header\">  "
		+ " <td align=\"right\"  width = \"75%\"  ><b>JUMLAH :</b></td> "
		+ " <td align=\"right\" width = \"25%\" ><b>"+formatCurrency(total_bayaran_lewat)+"</b></td> "
		+ " </tr></table> ";
		
		set_div_date = set_div_date + " </td></tr>";
		
		
		$jquery("#jumlah_bayaran_lewat").html("<font color='blue'><b>RM "+formatCurrency(total_bayaran_lewat)+"</b></font>");
		
		
		var frac_total_caj = new Fraction(1,1);	
		var div_frac_nilai_caj = 0;
		var fin_total_caj = total_bayaran_lewat;
		for (var i=0;i<saiz_pb;i++)
		{
			var bhg_ats_caj = document.getElementById('div_syer_atas_caj'+(i+1)).value;
			var bhg_bwh_caj = document.getElementById('div_syer_bawah_caj'+(i+1)).value;
			
			if (!isNaN(bhg_ats_caj) && !isNaN(bhg_bwh_caj))
			{
			var div_total_caj = ((fin_total_caj*parseInt(bhg_ats_caj))/parseInt(bhg_bwh_caj));			
			$jquery("#div_bhg_per_caj"+(i+1)).html("<font color = 'blue'>"+formatCurrency(div_total_caj)+"</font>");
			frac_total_caj = frac_total_caj.add(new Fraction(bhg_ats_caj,bhg_bwh_caj));
			div_frac_nilai_caj = div_frac_nilai_caj + div_total_caj;	
			}
			else
			{
			$jquery("#div_bhg_per_caj"+(i+1)).html("");		
			}
		
		}
		frac_total_caj = frac_total_caj.subtract(1,1);
		$jquery("#div_frac_total_caj").html("<b>"+frac_total_caj+"</b>");	
		$jquery("#div_frac_nilai_caj").html("<b>"+formatCurrency(div_frac_nilai_caj)+"</b>");
	
	
	set_div_date = set_div_date +"</table> ";
	}
	$jquery("#set_div_date").html(set_div_date);	
	$jquery("#set_total_day").html("<font color='blue'><b>"+total_jumlah_hari+"</font></b> hari");
	
	
	var total_total = 0;
	var total_pampasan_tanah = 0;
	var total_pampasan_lewat = 0;
	
	for (var i=0;i<saiz_pb;i++)
	{
		var bhg_ats_all = document.getElementById('div_syer_atas_all'+(i+1)).value;
		var bhg_bwh_all = document.getElementById('div_syer_bawah_all'+(i+1)).value;
		
		if (!isNaN(bhg_ats_all) && !isNaN(bhg_bwh_all))
		{
		var div_total_all = ((fin_total*parseInt(bhg_ats_all))/parseInt(bhg_bwh_all));
		var div_total_caj_all = ((fin_total_caj*parseInt(bhg_ats_all))/parseInt(bhg_bwh_all));	
		
							
		$jquery("#div_pampasan_tanah_all"+(i+1)).html("<font color = 'blue'>"+formatCurrency(div_total_all)+"</font>");	
		document.getElementById('text_pampasan_tanah_all'+(i+1)).value = formatCurrencyWithout(div_total_all);		
		
		$jquery("#div_bhg_per_caj_all"+(i+1)).html("<font color = 'blue'>"+formatCurrency(div_total_caj_all)+"</font>");	
		document.getElementById('text_bhg_per_caj_all'+(i+1)).value = formatCurrencyWithout(div_total_caj_all);	
			
		$jquery("#div_tempoh_lewat_all"+(i+1)).html("<font color = 'blue'>"+total_jumlah_hari+"</font>");	
		document.getElementById('text_tempoh_lewat_all'+(i+1)).value = total_jumlah_hari;		
		
		$jquery("#div_total_all"+(i+1)).html("<font color = 'blue'>"+formatCurrency(parseFloat(formatCurrencyWithout(div_total_all))+parseFloat(formatCurrencyWithout(div_total_caj_all)))+"</font>");	
		document.getElementById('text_total_all'+(i+1)).value = parseFloat(formatCurrencyWithout(div_total_all))+parseFloat(formatCurrencyWithout(div_total_caj_all));	
		
		total_total = total_total + parseFloat(formatCurrencyWithout(div_total_all))+parseFloat(formatCurrencyWithout(div_total_caj_all));
		total_pampasan_tanah = total_pampasan_tanah + parseFloat(formatCurrencyWithout(div_total_all));	
		total_pampasan_lewat = total_pampasan_lewat + parseFloat(formatCurrencyWithout(div_total_caj_all));
				
		}
		else
		{
		$jquery("#div_pampasan_tanah_all"+(i+1)).html("");		
		document.getElementById('text_pampasan_tanah_all'+(i+1)).value = "";
		$jquery("#div_bhg_per_caj_all"+(i+1)).html("");	
		document.getElementById('text_bhg_per_caj_all'+(i+1)).value = "";
		$jquery("#div_tempoh_lewat_all"+(i+1)).html("");	
		document.getElementById('text_tempoh_lewat_all'+(i+1)).value = "";
		$jquery("#div_total_all"+(i+1)).html("");	
		document.getElementById('text_total_all'+(i+1)).value = "";	
		}
	
	}
	$jquery("#div_total_pampasan_tanah_all").html("<b>"+formatCurrency(fin_total)+"</b>");
	$jquery("#div_total_bhg_per_caj_all").html("<b>"+formatCurrency(total_pampasan_lewat)+"</b>");
	$jquery("#div_total_total_all").html("<b>"+formatCurrency(total_total)+"</b>");
	
	
	
}

var DateDiff = {
 
    inDays: function(d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();
 
        return parseInt((t2-t1)/(24*3600*1000));
    },
 
    inWeeks: function(d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();
 
        return parseInt((t2-t1)/(24*3600*1000*7));
    },
 
    inMonths: function(d1, d2) {
        var d1Y = d1.getFullYear();
        var d2Y = d2.getFullYear();
        var d1M = d1.getMonth();
        var d2M = d2.getMonth();
 
        return (d2M+12*d2Y)-(d1M+12*d1Y);
    },
 
    inYears: function(d1, d2) {
        return d2.getFullYear()-d1.getFullYear();
    }
}

function daysInYear(year) {
    if(year % 4 === 0 && (year % 100 !== 0 || year % 400 === 0)) {
        // Leap year
        return 366;
    } else {
        // Not a leap year
        return 365;
    }
}


function formatCurrency(num) {
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num)) num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10) cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
	num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') +  num + '.' + cents);
}

function formatCurrencyWithout(num) {
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num)) num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10) cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
	num = num.substring(0, num.length - (4 * i + 3)) + '' + num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') +  num + '.' + cents);
}

function isDateValid(value, sepVal, dayIdx, monthIdx, yearIdx) {
    try {
        value = value.replace(/-/g, "/").replace(/\./g, "/");
        var SplitValue = value.split(sepVal || "/");
        if (SplitValue.length != 3) {
            return false;
        }
 
        //Auto  detection of indexes
        if (dayIdx === undefined || monthIdx === undefined || yearIdx === undefined) {
            if (SplitValue[0] > 31) {
                yearIdx = 0;
                monthIdx = 1;
                dayIdx = 2;
            } else {
                yearIdx = 2;
                monthIdx = 1;
                dayIdx = 0;
            }
        }
 
        //Change the below values to determine which format of date you wish to check. It is set to dd/mm/yyyy by default.
        var DayIndex = dayIdx !== undefined ? dayIdx : 0;
        var MonthIndex = monthIdx !== undefined ? monthIdx : 1;
        var YearIndex = yearIdx !== undefined ? yearIdx : 2;
 
        var OK = true;
        if (!(SplitValue[DayIndex].length == 1 || SplitValue[DayIndex].length == 2)) {
            OK = false;
        }
        if (OK && !(SplitValue[MonthIndex].length == 1 || SplitValue[MonthIndex].length == 2)) {
            OK = false;
        }
        if (OK && SplitValue[YearIndex].length != 4) {
            OK = false;
        }
        if (OK) {
            var Day = parseInt(SplitValue[DayIndex], 10);
            var Month = parseInt(SplitValue[MonthIndex], 10);
            var Year = parseInt(SplitValue[YearIndex], 10);
            var MonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
 
            if (OK = (Month <= 12 && Month > 0)) {
 
                var LeapYear = (((Year % 4) == 0) && ((Year % 100) != 0) || ((Year % 400) == 0));
                MonthDays[1] = (LeapYear ? 29 : 28);
 
                OK = Day > 0 && Day <= MonthDays[Month - 1];
            }
        }
        return OK;
    }
    catch (e) {
        return false;
    }
}

</script>




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
	document.${formName}.tabId.value = "6";
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
		document.${formName}.tabId.value = "4";
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
			document.${formName}.tabId.value = "4";
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
	
		document.${formName}.tabId.value = "4";
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
		document.${formName}.tabId.value = "4";
	}else{
		document.${formName}.tabId.value = "5";
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
		document.${formName}.tabId.value = "4";
	}else{
		document.${formName}.tabId.value = "5";
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
	document.${formName}.tabId.value = "4";
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
	document.${formName}.tabId.value = "3";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "pelarasanLuas";
	document.${formName}.id_permintaanukur.value = id_permintaanukur;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function kemaskiniPelarasanLuas(id_permintaanukur) {
	document.${formName}.tabId.value = "3";
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
		document.${formName}.tabId.value = "3";
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.command.value = "pelarasanLuas";
		document.${formName}.command2.value = "updatePelarasanLuas";
		document.${formName}.id_permintaanukur.value = id_permintaanukur;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
		document.${formName}.submit();
	}
	
}
function pelarasanLuas(id_permintaanukur) {
	document.${formName}.tabId.value = "3";
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
	//document.${formName}.command3.value = "doOnchangeUpdate";
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
//penambahan yati
function onchangeUnitLuasAmbil(){

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "maklumatPermintaanUkur";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}

function onchangeUnitLuasAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatPermintaanUkur";
	document.${formName}.command2.value = "kemaskiniMaklumatPU";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();

}
function convertNilaiAmbilUpdate(){
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "maklumatPermintaanUkur";
	document.${formName}.command2.value = "kemaskiniMaklumatPU";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.command5.value = "convertNilaiAmbilUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}
function clearConvertAmbil(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "maklumatPermintaanUkur";
	document.${formName}.command2.value = "doOnchange";
	document.${formName}.command3.value = "onchangeUnitLuasAmbil";
	document.${formName}.command4.value = "clearConvertAmbil";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
	document.${formName}.submit();
}

function clearConvertAmbilUpdate(){

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "maklumatPermintaanUkur";
	document.${formName}.command2.value = "kemaskiniMaklumatPU";
	document.${formName}.command3.value = "doOnchangeUpdate";
	document.${formName}.command4.value = "onchangeUnitLuasAmbilUpdate";
	document.${formName}.command5.value = "clearConvertAmbilUpdate";
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
		document.${formName}.tabId.value = "4";
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
		document.${formName}.tabId.value = "4";
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

//penambahan yati
function convertNilaiAmbil(boxAmbil2,boxAmbil3){

	var socUnitLuasAmbil = document.${formName}.socUnitLuasAmbil.value;
	
	if(socUnitLuasAmbil!="" && document.${formName}.txtLuasLotAmbil.value == ""){	

		alert("Sila masukkan \"Luas Lot Ambil\" terlebih dahulu.");
		document.${formName}.txtLuasLotAmbil.focus(); 
		return;
			
	}else 
	if(boxAmbil2=="yes" && (document.${formName}.txtLuasLotAmbil.value == "" || document.${formName}.txtLuasLotAmbil2.value == "")){	

		alert("Sila lengkapkan \"Luas Lot Ambil\" terlebih dahulu.");
		document.${formName}.txtLuasLotAmbil2.focus(); 
		return;
				
	}else 
	if(boxAmbil3=="yes" && (document.${formName}.txtLuasLotAmbil.value == "" || document.${formName}.txtLuasLotAmbil2.value == "" 
	   || document.${formName}.txtLuasLotAmbil3.value == "")){	

		alert("Sila lengkapkan \"Luas Lot Ambil\" terlebih dahulu.");
		document.${formName}.txtLuasLotAmbil3.focus(); 
		return;
						
	}else{
		
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.command.value = "maklumatPermintaanUkur";
		document.${formName}.command2.value = "doOnchange";
		document.${formName}.command3.value = "onchangeUnitLuasAmbil";
		document.${formName}.command4.value = "convertNilaiAmbil";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur";
		document.${formName}.submit();

	}//close else
	
}//close convertNilai Ambil
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