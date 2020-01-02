<style type="text/css">

.style1 {
	color: #0033FF
}

</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
				<legend><strong>Senarai Surat Agensi</strong></legend>
							  		
			    	#if($list_surat > 5)
			        <div style="width:100%;height:111;overflow:auto"> 
			        #end
			            	
			    	<table align="center" width="100%">
			    		<tr class="table_header">
			    			<td scope="row"  align="center"><b>Bil</b></td>
			    			<td>&nbsp;<b>No. Ruj Surat Agensi</b></td>
			    			<td>&nbsp;<b>Tarikh Surat Agensi</b></td>
			    			<td>&nbsp;<b>Tarikh Terima</b></td>
			    			<td>&nbsp;<b>Tarikh Ambil Cek</b></td>
			    		</tr>
			    				
			    		#set ($list = "")
				        #if ($listSuratAgensi.size() > 0)
				        #foreach ($list in $listSuratAgensi)
				        
				        #if ($list.bil == '')
				        	#set( $row = "row1" )
				        #elseif (($list.bil % 2) != 0)
				        	#set( $row = "row1" )
				        #else 
				        	#set( $row = "row2" )
				        #end
			                    
			       		<tr>
			                <td class="$row" align="center">$!list.bil</td>
			                <!-- <td class="$row">&nbsp;<a href="javascript:viewMaklumatSuratAgensi('$list.id_terimabayaran')"><font color="blue">$!list.no_rujukan_surat</font></a></td> -->
			                <td class="$row"><a id="hoverover" style="cursor:default; color:#0000FF" onClick="ShowPopup(this,$list.bil);"  title="Klik untuk maklumat lengkap">$list.no_rujukan_surat</a></td>
			                <td class="$row" align="left">$!list.tarikh_surat</td>
			                <td class="$row" align="left">$!list.tarikh_terima</td>
			                <td class="$row" align="left">$!list.tarikh_ambil_cek</td>
			            </tr>
			            
						<tr>
					        <td colspan="7">
							<fieldset id="$list.bil" style="visibility:collapse; display:none;" >
								<legend><strong>Senarai Pembayaran Melalui Cek</strong></legend>
							    	
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
					        
									        #set ($listPN = "")
									        #if ($listPenerimaanCek.size() > 0)
									        #foreach ($listPN in $listPenerimaanCek)
									        
									        #if ($listPN.bil == '')
									        	#set( $row = "row1" )
									        #elseif (($listPN.bil % 2) != 0)
									        	#set( $row = "row1" )
									        #else 
									        	#set( $row = "row2" )
									        #end
							                    
							               	<tr>
							                   	<td class="$row" align="center">$!listPN.bil</td>
							                   	<!-- <td class="$row">&nbsp;<a href="javascript:viewPenerimaanCek('$!listPN.id_bayaran','$!listPN.id_terimabayaran','$!listPN.id_hakmilikpb','$!listPN.cara_bayar')"><font color="blue">$!listPN.nama_pb</font></a></td> -->
							                   	<!-- <td class="$row"><a id="hoverover" style="cursor:default; color:#0000FF" onClick="ShowPopup(this,$listPN.bil);"  title="Klik untuk maklumat lengkap">$!listPN.nama_pb</a></td> -->
							                    <td class="$row" align="left">$!listPN.nama_pb</td>
							                    <td class="$row" align="left">$!listPN.no_pb</td>
							                   	<td class="$row" align="left">$!listPN.syer_atas / $!listPN.syer_bawah</td>
							                   	<!-- <td class="$row" align="left">RM&nbsp;$!Utils.format2Decimal($listPN.bayar_pampasan)</td> -->
							                   	<td class="$row" align="left">RM&nbsp;$listPN.bayar_pampasan</td>
							              	</tr>
							              	
							              	<tr>
							              	<td colspan="7">
							              	<fieldset>
								  			<legend><strong>Maklumat Penerimaan Cek</strong></legend>
								  			
								  				#if($mode=="view")
  				
								  				#if($onchangeCEK == "no")
								  				#foreach($dataPN in $dataPenerimaanCek)
								  					#set($txtNoPB=$dataPN.no_pb)
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
								  				
								  				<table width="100%" border="0">
								  					<tr>
								  						<td width="1%"><font color="red">$!M</font></td>
								  						<td width="25%">Nama PB</td>
								  						<td width="1%">:</td>
								  						<!-- <td width="73%">$!selectPB</td> -->
								  						<td class="$row" align="left">$!listPN.no_pb</td>
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
								  						<td>Bil. Hari Lewat</td>
								  						<td>:</td>
								  						<td><input $disability $disabilityx type="text" name="txtBilLewat" id="txtBilLewat" value="$!txtBilLewat" size="3" maxlength="4" onkeyup="validateNumber(this,this.value);" ></td>
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
								  						
								  						<span id = "lblCajLewat" ></span>
								                        </td>
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
								  			</td>
								  			</tr>
		              	
							           		#end
							        		#else
							                <tr>
							                   	<td colspan="2">Tiada rekod</td>
							                </tr>
							        		#end
		    				
							    	 </table>
							        </div>
							        
							</fieldset>
							</td>
						</tr>

			           	#end
			        	#else
			            <tr>
			                <td colspan="2">Tiada rekod</td>
			            </tr>
			        	#end
			    				
			    	</table>
			    	</div>
		</fieldset>
	</td>
  </tr>
</table>
				
<script>

function ShowPopup(hoveritem,tab)
{
	var hp = document.getElementById(tab);
	//alert("hp.style.display :"+hp.style.display+";hp.style.visibility:"+hp.style.visibility);
	//hp.style.display=="none" &&
    if( hp.style.visibility=="collapse" && hp.style.display=="none"){
        hp.style.display = "block";
        hp.style.visibility = "visible";
    }
    //hp.style.display=="block" &&
    else if( hp.style.visibility=="visible" && hp.style.display=="block"){
        hp.style.display = "none";
        hp.style.visibility = "collapse";
    }
}

</script>