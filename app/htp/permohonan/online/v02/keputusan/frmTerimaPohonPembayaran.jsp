<!--frmTerimaPohonPembayaran.jsp-->
<!-- CL-02-009 -->
<fieldset>
	<div id="TabbedPanels3" class="TabbedPanels">
	  	<ul class="TabbedPanelsTabGroup">
		    <li class="TabbedPanelsTab" tabindex="0" onClick="selectTab3('0','kemaskinipermohonan','Pembayaran','BuktiPembayaran','')">BUKTI PEMBAYARAN PERMOHONAN</li>
		    <li class="TabbedPanelsTab" tabindex="1" onClick="selectTab3('1','kemaskinipermohonan','Pembayaran','hantarpermohonan','')">HANTAR PERMOHONAN</li>
		    <li class="TabbedPanelsTab" tabindex="2" onClick="selectTab3('2','kemaskinipermohonan','Pembayaran','KeputusanPermohonan','')">KEPUTUSAN PERMOHONAN</li>
		    <li class="TabbedPanelsTab" tabindex="3" onClick="selectTab3('3','kemaskinipermohonan','Pembayaran','tindakanpermohonan','')">TINDAKAN</li>
	  	</ul>
  		<div class="TabbedPanelsContentGroup">
  			
    		<div class="TabbedPanelsContent">
    		<!-- Start Content 1-->  
				<fieldset>
  				<table width="100%" border="0">
  					
         			<tr>
	         			<td valign="top">
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%">							
						 			&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Tarikh Surat Ke 
											#parse ("app/htp/permohonan/utiliti/frmPejabatTanah2LabelScript.jsp")
											</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						           	#if($pagemode == "view")
						            		$!pembayaran.getTarikhTerimaBayaranStr()
						            #else
						            	<input name="txtTarikhSuratKePTD" type="text" id="txtTarikhSuratKePTD" value="$!pembayaran.getTarikhTerimaBayaranStr()" size="11" maxlength="10" $style/>
									  		<a href="javascript:displayDatePicker('txtTarikhSuratKePTD',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
									#end
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Tarikh Baucer/Cek/Bank Draft/EFT</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						           	#if($pagemode == "view")
						            		$!pembayaran.getTarikhBayaranStr()
						            #else						            	
						            	<input name="txtTarikhBaucerCek" type="text" id="txtTarikhBaucerCek" value="$!pembayaran.getTarikhBayaranStr()" size="11" maxlength="10" $style/>
											<a href="javascript:displayDatePicker('txtTarikhBaucerCek',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
									#end
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">No. Baucer/Cek/Bank Draft/EFT</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            #if($pagemode == "view")
						            		$!pembayaran.getNoRujukan()
						            #else	
						            	<input type="text" name="txtNoBaucerCekDraft" id="txtNoBaucerCekDraft" size="30"  value="$!pembayaran.getNoRujukan()" onKeyUp="this.value=this.value.toUpperCase();" $style>
						            #end
						            </td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Cara Bayaran</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">$!socBayaran</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Bayaran Proses (RM)</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            #if($pagemode == "view")
						            	$!jumlahBayaranFormat
			            		
						            #else
						                 <input type="text" name="txtBayaranProses" id="txtBayaranProses" size="11" 
    										value="$!jumlahBayaranFormat" onBlur="validateCurrency(this,this.value,'')"  $style>
    								#end
    								</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Penerima Bayaran</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            #if($pagemode == "view")
						            		$!pembayaran.getBank()
						            #else						            	
						            	<input type="text" name="txttempatBayaran" id="txttempatBayaran" size="30"  value="$!pembayaran.getBank()" onKeyUp="this.value=this.value.toUpperCase();" $style></td>
						          	#end
						          </tr>
	         				</table>			
	         			</td>
        
	        			<td width="50%" valign="top">
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Tarikh Resit</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						            #if($pagemode == "view")
						            		$!pembayaran.getResit().getTarikhFormat()
						            #else						            	
						            	<input name="txtTarikhResit" type="text" id="txtTarikhResit" value="$!pembayaran.getResit().getTarikhFormat()" size="11" maxlength="10" $style/>
									      <a href="javascript:displayDatePicker('txtTarikhResit',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
									#end
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%" valign="top">
						            	<div align="right" class="labelinput">
											<div align="left">No.Resit</div>
										</div>             
						            </td>
						            <td width="1%" valign="top">:</td>
						            <td width="58%">
						            #if($pagemode == "view")
						            		$!pembayaran.getResit().getNoRujukan()
						            #else
						            	<input type="text" name="txtNoResit" id="txtNoResit" size="30" value="$!pembayaran.getResit().getNoRujukan()" 
    										onKeyUp="this.value=this.value.toUpperCase();" $style>
									#end
    								</td>
						          </tr>
	         				</table>		        				
	         			</td>
					</tr>
					<!--<tr>
						<td colspan=2 align="center">

					</tr> -->
				</table>
				</fieldset>
							<div align="center">
						    #if ($tabmode == "3" && $!style == "")
 	      						##if($!idNegeriNotis=="12" || $!idNegeriNotis=="13")
								#if (!$!jenisAkses.equals('Readonly'))
						    	<input type="button" class="stylobutton100"  value="Simpan" onclick="TambahPembayaran()" />
						    	#end
						    
						    #elseif ($tabmode == "4" && $!style == "")
						   		<input type="button" class="stylobutton100"  value="Simpan" onclick="doKemaskiniBuktiBayaran($tabmode)" />
						   		<input type="button" class="stylobutton100"  value="Batal" onclick="selectTab3('0','kemaskinipermohonan','Pembayaran','BuktiPembayaran','')" />
						    #else
 	      						##if($!idNegeriNotis=="12" || $!idNegeriNotis=="13")
								#if (!$!jenisAkses.equals('Readonly'))
						    	<input type="button" class="stylobutton100"  value="Kemaskini" onclick="doViewForKemaskiniBuktiBayaran($tabmode)" />
								#end
						    #end 
						 	<!--Add on 2010/06/01 for print Surat-->
 	      						##if($!idNegeriNotis=="12" || $!idNegeriNotis=="13")
								#if (!$!jenisAkses.equals('Readonly'))
								<!-- <input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" 
								onclick="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$idpermohonan','urusan','&template=HTPPermohonanSuratPermohonanRizabPTD$!suratExt')"/> 
								-->
								#end
							</div>
   
     		</div>
     		<!-- End Content 1-->
 
     		<div class="TabbedPanelsContent">
    		<!-- Start Content 1.1 -->  
				<fieldset>
  				<table width="100%" border="0"> 					
         			<tr>
	         			<td valign="top">
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%" valign="top">
						 				<span class="labelmandatory">
									    #if($pagemode != "view")
						 				*
						 				#end
						 				</span>
						 				&nbsp;</td>
						            <td width="40%" valign="top">
						            	<div align="right" class="labelinput">
											<div align="left">Tarikh Hantar Ke 
				 								#parse ("app/htp/permohonan/utiliti/frmPejabatTanah2LabelScript.jsp")
											</div>
										</div>             
						            </td>
						            <td width="1%" valign="top">:</td>
						            <td width="58%" valign="top">
									    #if($pagemode == "view")
						            		$!selesaiBean.tarikhSelesai
						            	#else
						            	<input name="txttarikhsuratPTGPTD" type="text" id="txttarikhsuratPTGPTD" value="$!selesaiBean.tarikhSelesai" size="11" maxlength="10" $!style/>
									    #if($visible == 1)
									      <a href="javascript:displayDatePicker('txttarikhsuratPTGPTD',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
										#end
										
										#end
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%" valign="top">
						            	<div align="right" class="labelinput">
											<div align="left">Catatan</div>
										</div>             
						            </td>
						            <td width="1%" valign="top">:</td>
						            <td width="58%">
						            #if($pagemode == "view")
						            	$!selesaiBean.catatan
						            #else
						            	<textarea name="txtcatatanPTGPTD" id="txtcatatanPTGPTD" cols="45" rows="5" onKeyUp="this.value=this.value.toUpperCase();" $style>$!selesaiBean.catatan</textarea>
									#end
									</td>
						          </tr>
	         				</table>			
	         			</td>
        
	        			<td width="50%" valign="top">
	         				<table width="100%" border="0">
	         				</table>		        				
	         			</td>
					</tr>
					<!--<tr>
						<td colspan=2 align="center">

					</tr> -->
				</table>				
				</fieldset>
				<input name="txtid" type="hidden" value="$!selesaiBean.id_suburusanstatusfail" size="11" maxlength="10" $style/>
							<div align="center">
						    #if ($tabmode == "3" && $!style == "")
 	      						##if($!idNegeriNotis=="12" || $!idNegeriNotis=="13")
								#if (!$!jenisAkses.equals('Readonly'))
						    	<input type="button" class="stylobutton100"  value="Simpan" onclick="tambahHantarPTGPTD()" />
						    	<input type="reset" class="stylobutton100"  value="Kosongkan" />
						    	#end
						    
						    #elseif ($tabmode == "4" && $!style == "")
						   		<input type="button" class="stylobutton100"  value="Simpan" onclick="kemaskiniSimpanPTGPTD()" />
						    	<input type="button" class="stylobutton100"  value="Batal" onclick="selectTab3('1','kemaskinipermohonan','Pembayaran','hantarpermohonan','')"/>
						    #else
 	      						##if($!idNegeriNotis=="12" || $!idNegeriNotis=="13")
								#if (!$!jenisAkses.equals('Readonly'))
						    	<input type="button" class="stylobutton100"  value="Kemaskini" onclick="kemaskiniHantarPTGPTD()" />
						    	<!-- <input type="button" class="stylobutton100"  value="hapus" onclick="hapusHantarPTGPTD()" /> -->
						    	#end
						    
						    #end 
						 	<!-- Add on 2010/06/01 for print Surat -->
								#if (!$!jenisAkses.equals('Readonly'))
							<input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" 
								onclick="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$idpermohonan','urusan','&template=HTPPermohonanSuratPermohonanRizabPTD$!suratExt')"/> 
							#end
							</div>
   
     		</div>
     		<!-- End Content 1.1 -->
    
    		<!-- Start Content 2-->  
     		<div class="TabbedPanelsContent">
				<fieldset>
  				<table width="100%" border="0">
  					
         			<tr>
	         			<td valign="top">  
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Tarikh Keputusan</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
									    ##if($visible == 1)
									    #if($!pagemode == "view")
									    	$!keputusan.tarikhkeputusan
										#else
						            		<input name="txtTarikhKeputusan" type="text" id="txtTarikhKeputusan" value="$!keputusan.tarikhkeputusan" size="11" maxlength="10" $style onblur="check_date(this);validateTarikhSemasaIsNull(this);"/>
									      	<a href="javascript:displayDatePicker('txtTarikhKeputusan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>										
										#end
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">No. Rujukan 
				 						#parse ("app/htp/permohonan/utiliti/frmPejabatTanah2LabelScript.jsp")											
											</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						           	#if($!pagemode == "view")
										$!keputusan.norujukankeputusan
									#else
										<input type="text" name="txtNoRujukPTD" id="txtNoRujukPTD" size="30" value="$!keputusan.norujukankeputusan" onKeyUp="this.value=this.value.toUpperCase();" $style/></td>
						          	#end
						          </tr>
	         				</table>			
	         			</td>
        
	        			<td width="50%" valign="top">
	         				<table width="100%" border="0">
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%">
						            	<div align="right" class="labelinput">
											<div align="left">Status Keputusan</div>
										</div>             
						            </td>
						            <td width="1%">:</td>
						            <td width="58%">
						          
										#if($!idNegeriNotis=="13")
											#set ($list = ["TINJAUAN TAPAK","BORANG SITTING APPLICATION","PERMOHONAN DITERIMA","PERMOHONAN DIHANTAR JTS/BDA","LAWATAN TAPAK BERSAMA","BORANG PENERIMAAN TAPAK","LULUS MPN/SPA","TIDAK LULUS MPN/SPA","KELULUSAN PEMBERI MILIKAN (BORANG L&S80)","SELESAI PEMBAYARAN(PREMIUN/PAMPASAN/KOS UKUR/LAIN-LAIN)","KOS PENGAMBILAN TANAH TELAH DIJELASKAN","DITOLAK","DIBATAL","DITANGGUH","SELESAI (HAK MILIK DAFTAR PTP)"])				      				
					      				#elseif($!idNegeriNotis=="12")
											#set ($list = ["SUDAH DISOKONG DALAM LUC","PTU PERAKUKAN KE SUHB","SURAT TAWARAN SUDAH DIKELUARKAN"])
				                		#else	
											#set ($list = ["BELUM ADA KEPUTUSAN","DILULUSKAN","TIDAK DILULUSKAN","DIBATALKAN/DITARIKBALIK"])
				                   		#end
				                 		
				                 		#if($!pagemode == "view")
				                 			#set($hideshow="disabled class='disabled'")
										#else
				                 			#set($hideshow="")
										#end
										
										#set( $counter = 0 )
						            	<select name="PilihKeputusan" style="width:300px;" $hideshow >
											#foreach ($i in $list)
												#if ($!keputusan.status == $counter) 
													<option selected value="0$counter">$i</option>
												#else
													<option value="0$counter">$i</option>
												#end
												#set ($counter = $counter+1)
											#end									
													
										</select> 
										
										 
									</td>
						          </tr>
						          <tr>
						 			<td width="1%">&nbsp;</td>
						            <td width="40%" valign="top">
						            	<div align="right" class="labelinput">
											<div align="left">Catatan</div>
										</div>             
						            </td>
						            <td width="1%" valign="top">:</td>
						            <td width="58%">
								    #if($!pagemode == "view")
									    $!keputusan.ulasan
									#else						            
										<textarea name="txtCatatan" id="txtCatatan" cols="45" rows="5" onKeyUp="this.value=this.value.toUpperCase();" $style>$!keputusan.ulasan</textarea></td>
						          	#end
						          </tr>
						          
						          <tr>
						          	<td>&nbsp;</td>
						          	<td class="labelinput">Notifikasi ke Dashboard</td>
						          	<td>:</td>
						          	<td>
						          		<input type="checkbox" id="flagNotifikasi" name="flagNotifikasi" value="Y" 
						          		$hideshow #if($!keputusan.flag_notifikasi=='Y') checked #else #end>
						          	</td>
						          </tr>
						          
						          <input type="hidden" name="idkeputusanmohon" id="idkeputusanmohon" value="$!keputusan.idkeputusanmohon" />
	         				</table>		        				
	         			</td>
					</tr>
					<!--<tr>
						<td colspan=2 align="center">

					</tr> -->
				</table>
				</fieldset>

							<div align="center">
						    #if ($tabmode == "3" && $!style == "")
								#if (!$!jenisAkses.equals('Readonly'))
						    	<input type="button" class="stylobutton100" value="Simpan" onclick="TambahKeputusan()" />
						    	<input type="reset" class="stylobutton100" value="Kosongkan"  />
						    	#end
						    
						    #elseif ($tabmode == "4")
						    
						    	#if($!pagemode == "view")
							    	<!-- <input type="button" class="stylobutton100" name="btnhapus" value="Hapus" onclick="hapusKeputusan($tabmode)" /> -->
							    	<input type="button" class="stylobutton100" name="btnkemaskini" value="Kemaskini" onclick="doViewForKemaskiniKeputusan($tabmode)" />
							    #else
							    	<input type="button" class="stylobutton100"  value="Simpan" onclick="doKemaskiniKeputusan($tabmode)" />
							   	 	<input type="button" class="stylobutton100" name="btnbatal" value="Batal" onclick="selectTab3('2','kemaskinipermohonan','Pembayaran','KeputusanPermohonan','')" />
							    #end 
							    
						    #end 
							</div>
    		</div>
       		<!-- End Content 2-->
 
    		<!-- Start Content 3-->  
     		<div class="TabbedPanelsContent">
			#if ($mode =='new' || $mode == 'view' || $mode == 'update')
     			   			
			<fieldset>
				<legend><strong>MAKLUMAT TINDAKAN </strong></legend>
  					<table width="100%" >
  						<tr>
  							<td width="50%" valign="top">&nbsp;
								<table width="100%" >
			          				<tr>
							 			<td width="1%">&nbsp;</td>
							            <td width="40%">
							            	<div align="right" class="labelinput">
												<div align="left">Tarikh Tindakan</div>
											</div>             
							            </td>
							            <td width="1%">:</td>
							            <td width="58%">
							            	<input name="txtarikhkeputusan" type="text" value="$!tarikhHantar" size="11" maxlength="10" $!readonly class="$disabled"/>
										    #if($mode != 'view')
										      <a href="javascript:displayDatePicker('txtarikhkeputusan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
											#end
										</td>
						          	</tr>
				          			<tr>
							 			<td width="1%" valign=top>&nbsp;</td>
							            <td width="40%" valign=top>
							            	<div align="right" class="labelinput">
												<div align="left">Catatan</div>
											</div>             
							            </td>
							            <td width="1%" valign=top>:</td>
							            <td width="58%"><textarea name="txtcatatan" cols="45" rows="5" $!readonly class="$disabled" 
							            	onblur="this.value=this.value.toUpperCase();"
							            	onkeyup="textCounter(this.form.txtcatatan,this.form.remtxtcatatan,250);" 
								           	onkeydown="textCounter(this.form.txtcatatan,this.form.remtxtcatatan,250);" 
							            	>$!txtCatatan</textarea></td>
							     	</tr>
							     #if ($mode != 'view')
									<tr>
								        <td>&nbsp;</td>
								        <td>&nbsp;</td>
								        <td valign="top">&nbsp;</td>
								        <td><input type="text" readonly class="disabled" name="remtxtcatatan" size="3" maxlength="4" value="250"> Baki Aksara </td>
								    </tr>	
								#end															
								</table>
							</td>
  							<td width="50%" valign="top">&nbsp;</td> 							
						</tr>
		
				</table>
			</fieldset>

				<div align="center">
			#if ($mode =='new')
		    	<input type="button" class="stylobutton100" name="cmdtambah" value="Simpan" onclick="simpanTindakan('$permohonanInfo.idpermohonan')"/>
		    	<input type="button" class="stylobutton100" name="cmdBatal" value="Batal" onclick="skrinDariTindakan('$permohonanInfo.idpermohonan')"/>
		  	#elseif ($mode == 'update')
		    	<input type="button" class="stylobutton100" name="btnUpdateHakmilik" id="btnUpdateHakmilik" value="Simpan" onclick="doKemaskiniTindakan('$!idSusulanStatus','$!idSusulan','$permohonanInfo.idpermohonan')"/>
		    	<!--  <input type="button" class="stylobutton100" name="btnBackPergerakan" value="Batal" onclick="batalTindakan('$permohonanInfo.idpermohonan','$!idSusulanStatus','$!idSusulan')"/> -->
		    	<input type="button" class="stylobutton100" name="btnBackPergerakan" value="Batal" onclick="viewTindakan('$!idSusulanStatus','$senarai.idStatusFail','$!idSusulan','$!permohonanInfo.idpermohonan')"/>
		  	#elseif ($mode == 'view')
		  		#if(($disableFungsi) && $portal_role.contains('PenggunaNegeri'))
		    		<input type="button" class="stylobutton" name="cmdtambah" value="Hantar Untuk Semakan" onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
		  	
		  		#elseif (($disableFungsi) && $portal_role.contains('PegawaiNegeri'))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Sahkan " onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
		  		
		  		#elseif (($disableFungsi) && $portal_role.contains('PengarahNegeri'))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Dilulus" onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
	  			
	  			#elseif(($disableFungsi) && $portal_role.contains('HQPengguna'))
		    		<input type="button" class="stylobutton" name="cmdtambah" value="Hantar Untuk Semakan" onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
		  	
		  		#elseif (($disableFungsi) && ($portal_role.contains('HQPegawai') || $portal_role.contains('KetuaPenolong')))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Sahkan " onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
		  		
		  		#elseif (($disableFungsi) && $portal_role.contains('HQPengarah'))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Dilulus" onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
		  				  		
		  		#end
		    	<input type="button" class="stylobutton100" name="btnKemaskiniHakmilik" value="Kemaskini" onclick="kemaskiniTindakan('$!idSusulanStatus','$!idSusulan','$permohonanInfo.idpermohonan')"/>
		    	<!-- <input type="button" class="stylobutton100" name="btnBackPergerakan" id="btnBackPergerakan" value="Kembali" onclick="kembaliSenaraiPergerakan()"/> 
        		<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="screen8('$permohonanInfo.idpermohonan')"> -->
        		<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="selectTab3('3','kemaskinipermohonan','Pembayaran','tindakanpermohonan','')"/>
		    #end
  				</div>
						
		#else			
			 <!--	<tr>
    	<td> -->
			<fieldset>
				<legend><strong>SENARAI MAKLUMAT TINDAKAN</strong></legend>
  					<table width="100%" >
			##if (!$!jenisAkses.equals('Readonly'))
						<tr>
				        	<td width="100%">
				  				<input type="button" class="stylobutton100" name="cmdTambah" value="Tambah" onclick="skrinTindakanTambah('$permohonanInfo.idpermohonan')">      		
					        </td>
			##end
				 		<tr>	        
				 		<tr>
				        	<td width="100%">
				  			<table width="100%" >
							  <tr class="table_header">
							  	<td width="3%"><b>Bil.</b></td>
							  	<td width="74%"><b>Catatan</b></td>
							  	<td width="10%"><b>Tarikh Catatan</b></td>
							  	<td width="10%"><b>Tarikh Daftar</b></td>
							  	<td width="3%">&nbsp;</td>
							  </tr>
				              
				              	#set ( $cnth = 0 )			
				  				#foreach ( $senarai in $senaraiTindakan )
				  				#set ( $cnth = $cnth + 1 )
			        			#set( $i = $velocityCount )
				                #if ( ($i % 2) == 0 )
				                    #set( $row = "row2" )
				                #else
				                    #set( $row = "row1" )
				                #end
					                
							  <tr>
							    <td class="$row">$senarai.bil.</td>
							    <td class="$row">
							    	<a href="javascript:viewTindakan('$senarai.idSusulanStatus','$senarai.idStatusFail','$senarai.idSusulan','$!permohonanInfo.idpermohonan')" class="pautanms">$senarai.keterangan </a>
						   		 <div class="pautanms"><b><blink>$!senarai.status</blink></b></div>
							    </td>
							    <td class="$row">$senarai.tarikh</td>
							    <td class="$row">$senarai.tarikh</td>
							    <td class="$row">
							#if($senarai.bil !=0)
			    				#if($!senarai.status == '')
			    				
						   		<a alt="Hapus" href = "javascript:hapusTindakan('$senarai.idSusulan','$senarai.idSusulanStatus','$permohonanInfo.idpermohonan')">
						   			<img border="0" src="../img/delete.gif" />
						   		</a>
						   		
						   		#end
						   	#end
							    </td>
						
						        </tr>
								  #end
								#if ($cnth == 0)
								<tr> 
									<td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
								</tr> 
				  				#end
								  	
				  			</table>
						</td>
					</tr>
				</table>
			</fieldset>
<!--
			<p align="center">
				<div align="center">
        			<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="skrinKemaskiniPermohonan('$permohonanInfo.idpermohonan')">
  				</div>
			</p> 
    
		</td>
	</tr> -->		
		
			#end			
		   	<input type="hidden" name="id_kemaskini">
		    <input type="hidden" name="fail" >
		   	<input type="hidden" name="pagemode" >
		    <input type="hidden" name="langkah" value="0" >
    		</div>
       		<!-- End Content 3-->        
  		</div>	  	


	</div>

</fieldset>

<script type="text/javascript">
	var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTab3});
</script>
