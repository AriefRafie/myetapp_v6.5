   
<script type="text/javascript" src="../library/online/prototype.js"></script>
<script type="text/javascript" src="../library/online/scriptaculous/scriptaculous.js"></script>
<script type="text/javascript" src="../library/online/tooltips.js"></script>

<!--
<table width="100%" cellspacing="0">
	<tr>
		<td width="1%">
		
		</td>
		<td><blink ><FONT color="RED" size="+1"><b>SILA ABAIKAN SKRIN INI, SKRIN INI DIDALAM PROSES PEMBANGUNAN DAN PENGUJIAN, TERIMA KASIH!</b></FONT></blink></td>
	</tr>
	
</table>

-->

<p align="center">
<table cellpadding="2" cellspacing="1" border="0" width="100%" class="nav" align="center" >
	<tr>
		<td width="1%" valign="top">
		</td>
		<td width="59%" valign="top"  >
		<!-- class="nav_sub_kiri" -->

			<table width="100%" border="0" align="left" class="dashboard_kiri">
				<tr>
			    	<td width="50%" valign="top" align="left">
      
						<table width="100%" border="0" cellpadding="2"  cellspacing="1" class="dashboard_sub" align="left">
							<tr>
								<td width="50%" valign="top">
									<table width="100%" border="0">
										<tr>
											<td width="15%" align="center" valign="top"><img src="../img/myprofile.png"  width="30" height="30" align="center"/></td>
											<td width="85%">
												<table>

													<tr>
														<td>
														<b>Profil</b>
														</td>
													</tr>
	
													<tr>
														<td>
															<a href="javascript:gotoProfile()" class="help" title="Tukar Kata Laluan">
																<font color="blue"><li>&nbsp;Tukar Kata Laluan<i></li></font>						
															</a>
														</td>
													</tr>

													<tr>
														<td>
														<a href="javascript:gotoCalendar()" class="help" title="MyCalendar">
																					<font color="blue"><li><i>&nbsp;My Calendar</i></li></font>						
																				</a>
														</td>
													</tr>

													<tr>
														<td>
															<a href="javascript:gotoInbox()" class="help" title="My Inbox">
														                            <font color="blue">
														                            <li>          
														                             #if($!check_notifikasi_inbox > 0)                         
														                             <label style="background-color:blue"  align="center" valign="top" > 
														                            <b><font color="WHITE"><blink>$!check_notifikasi_inbox</blink></font></b>
														                             </label>&nbsp;
														                             #end
														                            Ruang Perbincangan Formal</li></font>	
															</a>
														</td>
													</tr>

												</table>
											</td>
											<!-- <td width="50%" valign="top">
												<table width="100%" border="2">
													<tr>
														<td>
											<a href="http://www.etapp.gov.my/map/" target="_blank" style="color:#0000FF">Maklumat GIS</a>
														</td>
													</tr>
												</table>
											</td> -->
										</tr>
									</table>
								</td>
								<!--<td valign="top" width="50%" >Kanan</td>-->
								<td width="50%" valign="top">
									<table width="100%" border="0">
										<tr>
											<td width="15%" align="center" valign="top"></td>
											<td width="85%">
												<table>
													<tr>
														<td>
															<b>Carian Terperinci</b>
														</td>
													</tr>
													<tr>
														<td>
															<input type="text" name="search" id="search"    maxlength="200"   size="30" onkeydown="return runScript(event)" onkeypress="return runScript(event)" onkeyup="return runScript(event)"  /> 
															<a href="javascript:open_info()" class="help" title="info">							
																<b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
															</a>
															<br/>
															<input type="button" id="but_search" name="but_search" value="Cari" onClick="cari()"  />
															<input type="button" id="but_search" name="but_search" value="Kosongkan" onClick="kosong()"  />
														</td>
													</tr>
													<tr id="tr_carianFail">
														<td>
															<div id="div_carianFail" ></div>
														</td>
													</tr>
													<tr id="tr_carianHakmilik">
														<td>
															<div id="div_carianHakmilik"></div>
														</td>
													</tr>
													
													<tr>
														<td>
															<!--<a href="javascript:alert('dddd')" target="_blank" style="color:#0000FF">Maklumat GIS</a>-->
															 
															 <a href="https://www.gis.myetapp.gov.my/myetappgis/guest.php" target = "_blank" style="color:#0000FF">Maklumat GIS</a>
															
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>			
								</td>
								
							</tr>
						</table>
    
    		</td>
  		</tr>
  		
  		<tr>
    		<td valign="top" align="left">
    
		    <table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
				<tr>

				<td width="50%" valign="top" border="0" >
				
					<table width="100%" border="0" >
						<tr>
							<td width="15%" align="center" valign="top"><img  src="../img/main.png" width="30" height="30" align="center"/></td>
							<td width="85%">
								<table style="width:100%">

									<tr>
										<td>
										<b>Tugasan</b>
										</td>
									</tr>
<!--
<tr>
<td>
bagi modul yang ada tugas spesifik untuk penguna yang login
<a href="javascript:myinfo_skrin_carian()" class="help" title="Fail Tugasan">
							<font color="blue"><li>Inbox</li></font>						
						</a>
</td>
</tr>
-->
									<tr>
										<td>
											<a href="javascript:myinfo_skrin_carian()" class="help" title="Fail Tugasan">
												<font color="blue"><li>
												#if($!bilTugasan != '0')                         
													<label style="background-color:blue"  align="center" valign="top" > 
													<b><font color="WHITE"><blink>$!bilTugasan</blink></font></b>
													</label>&nbsp;
												#end Senarai Tugasan												
													</li>
												</font>						
											</a>
										</td>
									</tr>

								</table>
								
								
								<!-- PENAMBAHBAIKAN. SYAZ. 17112014. FAIL BARU BORANG 15A 
									 DISPLAY TO UNIT REKOD & UNIT CUKAI SAHAJA -->
								<br/>
								<table style="width:100%" border="0" >
									<tr>
										<td>
											<b>Notifikasi</b>
										</td>
									</tr>
									<tr>
										<td>
											<!-- <a href="javascript:myinfo_skrin_carian()" class="help" title="Fail Tugasan"> -->
												<font color="blue"><li>
												#if($!bil_borang5ABaru != '0')                         
													<label style="background-color:blue"  align="center" valign="top" > 
													<b><font color="WHITE"><blink>$!bil_borang5ABaru</blink></font></b>
													</label>&nbsp;
												#end Permohonan Baru 5A												
													</li>
												</font>						
											<!--</a>  -->
										</td>
									</tr>
									
									#if($!bil_borang15ABaru != '0') 
									<tr>
										<td>
												<font color="blue"><li>
													<label style="background-color:blue"  align="center" valign="top" > 
													<b><font color="WHITE"><blink>$!bil_borang15ABaru</blink></font></b>
													</label>&nbsp;
												 Permohonan Baru 15A												
													</li>
												</font>						
											<!--</a>  -->
										</td>
									</tr>
									#end
									
									#if($!listKemaskiniCukai.size() != '0')  
									<tr>
										<td>
											<a href="javascript:doGetListCukai()" class="help" title="Senarai Fail">
												<font color="blue"><li>
												#if($!listKemaskiniCukai.size() != '0')                         
													<label style="background-color:blue"  align="center" valign="top" > 
													<b><font color="WHITE"><blink>$!listKemaskiniCukai.size()</blink></font></b>
													</label>&nbsp;
												#end Maklumat Cukai Dikemaskini											
													</li>
												</font>						
											</a>
											<div  id="div_listCukai"  style="width:40"></div>
										</td>
									</tr>
									#end 
									
								</table>
								
								
							</td>
						</tr>
					</table>

				</td>

				<td width="50%" valign="top">

#if($!bilPermohonanOnline != 0)                         

<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/applyonline.png" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b>Capaian Pantas Permohonan <i>Online</i></b>
</td>
</tr>

<tr>
<td>

<a href="javascript:pautanOnline()" class="help" title="Senarai Penerimaan Permohonan Online">
							<font color="blue"><li>
                            ##if($!vecSenaraiOnline.size > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!senaraionline.size()</blink></font></b>
                             </label>&nbsp;
                             ##end
                            
                            Permohonan <i>Online</i></li></font>						
				  </a>
</td>
</tr>



</table>
</td>
</tr>
</table>
#end

</td>

</tr>
</table>
    
    </td>
  </tr>
  <tr>
    <td valign="top" align="left">
    
    <table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
<tr>
	<td width="50%" valign="top">
			
		<table width="100%" >
			<tr>
				<td width="15%" align="center" valign="top"><img  width="30" height="30" src="../img/new_application.png" align="center"/></td>
				<td width="85%">
				<table>
		
					<tr>
						<td>
						<b>Daftar Permohonan</b></td>
						</tr>
		
						<tr>
							<td>
								<a href="javascript:pautan01()" class="help" title="Permohonan Pemberimilikan/ Perizaban">
									<font color="blue"><li>&nbsp;Pemberimilikan/ Perizaban</li></font>						
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="javascript:pautan03()" class="help" title="Pajakan">
									<font color="blue"><li>&nbsp;Pajakan</li></font>						
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="javascript:pautan04()" class="help" title="Pajakan Kecil">
									<font color="blue"><li>&nbsp;Pajakan Kecil</li></font>						
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="javascript:pautan05()" class="help" title="Gadaian/ Melepas Gadaian">
									<font color="blue"><li>&nbsp;Gadaian</li></font>						
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="javascript:pautan06()" class="help" title="Pembelian">
									<font color="blue"><li>&nbsp;Pembelian</li></font>						
								</a>
							</td>
						</tr>
					<tr>
							<td>
								<a href="javascript:pautan07()" class="help" title="Penswastaan ">
									<font color="blue"><li>&nbsp;Penswastaan</li></font>						
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="javascript:pautan08()" class="help" title="Perletakhakan">
									<font color="blue"><li>&nbsp;Perletakhakan</li></font>						
								</a>
							</td>
						</tr>		
						<!-- <tr>
							<td>
								<a href="javascript:gotoAddLama()" class="help" title="Kemasukan Fail Lama">
									<font color="blue"><li>&nbsp;Kutipan Data</li></font>						
								</a>
							</td>
						</tr> -->
		
					</table>
				</td>
			</tr>
		</table>

	</td>

	<td width="50%" valign="top">
<!-- -->
		<table width="100%" >
			<tr>
				<td width="15%" align="center" valign="top">
					<!-- <img width="30" height="30" src="../img/utiliti.png" align="center"/> -->
					</td>
				<td width="85%">
					<table>

						<tr>
							<td>
							<b>Rekod</b>
							</td>
						</tr>

						<tr>
							<td>
								<a href="javascript:pautanRekod()" class="help" title="Pendaftaran Hakmilik/ Rizab">
									<font color="blue"><li>&nbsp;Pendaftaran Hakmilik/ Rizab</li></font>						
								</a>
							</td>
						</tr>

						<tr>
							<td>
								<a href="javascript:pautanRekodHakmilikRizab()" class="help" title="Hakmilik/ Rizab">
									<font color="blue"><li>&nbsp;Hakmilik/ Rizab</li></font>						
								</a>
							</td>
						</tr>
						<!--
						<tr>
							<td>
							<a href="javascript:gotoBKE()" class="help" title="Permindahan BKE">
														<font color="blue"><li>&nbsp;Permindahan BKE</li></font>						
											  </a>
							</td>
						</tr> -->

					</table>
				</td>
			</tr>
			
			
			<tr>
				<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/utiliti.png" align="center"/></td>
				<td width="85%">
					<table>

						<tr>
							<td>
							<b>Utiliti</b>
							</td>
						</tr>

						<tr>
							<td>
								<a href="javascript:pautanKemaskini()" class="help" title="Kemaskini Fail">
									<font color="blue"><li>&nbsp;Kemaskini Fail</li></font>						
								</a>
							</td>
						</tr>

						<tr>
							<td>
							<a href="javascript:pautanStatus()" class="help" title="Pertukaran Pada Status Fail">
														<font color="blue"><li>&nbsp;Pertukaran Status Fail</li></font>						
											  </a>
							</td>
						</tr>
						<!--
						<tr>
							<td>
							<a href="javascript:gotoBKE()" class="help" title="Permindahan BKE">
														<font color="blue"><li>&nbsp;Permindahan BKE</li></font>						
											  </a>
							</td>
						</tr> -->

					</table>
				</td>
			</tr>
		</table>

	</td>
</tr>

</table>
    
    </td>
  </tr>
  
 <!-- 
  <tr>
    <td valign="top" align="left">
    
    <table cellpadding="2" cellspacing="1" border="0" width="100%" class="nav_sub" align="left">
<tr>
<td width="50%" valign="top">

<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/transfer.png" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b>Pemindahan Fail</b>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoPindah()" class="help" title="Penerima Fail Dari Luar Melalui Proses Fail Pindah">
							<font color="blue"><li>
                             #if($!check_notifikasi_pindah > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!check_notifikasi_pindah</blink></font></b>
                             </label>&nbsp;
                             #end
                            
                            Penerimaan Fail Pindah</li></font>						
				  </a>
</td>
</tr>



</table>
</td>
</tr>
</table>

</td>
<td width="50%" valign="top">

<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img src="../img/sms.png" width="30" height="30" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b><i>MySMS</i></b>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoSMS()" class="help" title="MySMS">
							<font color="blue"><li><i>&nbsp;MySMS</i></li></font>						
						</a>
</td>
</tr>

</table>
</td>
</tr>
</table>

</td>
</tr>
</table>
    
    </td>
  </tr>
-->  
  <tr>
    <td valign="top" align="left">
    
    <table cellpadding="2" cellspacing="1" border="0" width="100%" class="" align="left">
<tr>
<td width="50%" valign="top">


<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/aduan.png"/></td>
<td width="85%">
<table >

<tr>
<td>
<b>PLA</b> (Pengurusan Log Aduan)
</td>
</tr>

<tr>
<td>


<a href="javascript:gotoFLMS()" class="help" title="Hantar Log Aduan, Cadangan atau Pertanyaan">
                            <font color="blue">
                            <li>          
                             #if($!check_notifikasi_aduan > 0)                        
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!check_notifikasi_aduan</blink></font></b>
                             </label>&nbsp;
                             #end
                           Log Aduan</li></font>	
						</a>



                      
                        
</td>
</tr>
	<tr>
		<td>
			<a href="javascript:gotoFLMSstat()" class="help" title="Statistik Log FLMS">
				<font color="blue"><li>&nbsp;Statistik Log</li></font>						
			</a>
		</td>
	</tr>

</table>
</td>
</tr>
</table>
<br />
<br />
</td>
<td width="50%" valign="top"><table width="100%" border="0">
  <!--<tr>
    <td width="15%" align="center" valign="top"></td>
    <td width="85%"><table>
      <tr>
        <td><b>Capaian Sistem Imbasan Dokumen</b></td>
      </tr>
      <tr>
        <td><input name="txtNoFail" id="txtNoFail" type="text" maxlength="200" size="30" style="text-transform:uppercase;" > 
          <a href="javascript:open_infoSID()" class="help" title="info"> <b><font color="blue"><img src="../img/info.png" alt=""  align="center" /></font></b> </a> <br/>
         <input type="button" id="" name="" value="Cari" onclick="javascript:arkibWindow()"/>
          <input type="button" id="but_search2" name="but_search2" value="Kosongkan" onclick="kosong()"  /></td>
      </tr>
      <tr id="tr_carianFail2">
        <td><div id="div_carianFail2" ></div></td>
      </tr>
      <tr id="tr_carianHakmilik2">
        <td><div id="div_carianHakmilik2"></div></td>
      </tr>
      <tr>
        <td><!--<a href="javascript:alert('dddd')" target="_blank" style="color:#0000FF">Maklumat GIS</a></td>
      </tr>
    </table></td>
  </tr>-->
</table></td>
</tr>
</table>
    
    </td>
  </tr>
</table>








</td>
<td width="39%" valign="top">
<table width="100%" border="0" >
	<tr>
		<td width="100%">
		
		
			<table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_tepi" align="left">
				<tr>
					<td width="100%" valign="top">
					
						<table width="100%" >
							<tr>
								<td width="15%" align="center" valign="top">
								
								</td>
								<td width="85%" >
									<table width="100%">
									
										<tr>
											<td   colspan="3">
												<b>Statistik Fail di Pangkalan Data <br /><font color="blue">$!negeriServer</font></b></td>
										</tr>
									
										<tr>
											<td width="50%" valign="top">
												<font color="blue"><li>Keseluruhan Fail</li></font></td>
											<td width="1%" valign="top">:</td>
											<td width="19%" valign="top" align=right>
												<b>$!jumlahKeseluruhan</b>
											</td>
											<td width="30%" valign="top">
											</td>
										</tr>
										<tr>
											<td valign="top">
												<font color="blue"><li>Fail Pemberimilikan</li></font>
											</td>
											<td  valign="top">:</td>
											<td  valign="top" align=right>
											<b>$!failPemberimilikan</b>
											</td>
											<td valign="top">
										</tr>
										<tr>
											<td valign="top">
												<font color="blue"><li>Fail Perizaban</li></font>
											</td>
											<td valign="top">:</td>
											<td  valign="top" align=right>
												<b>$!failPerizapan</b>
											</td>
											<td valign="top">
										</tr>
										<tr>
											<td valign="top">
												<font color="blue"><li>Fail Pajakan</li></font>
											</td>
											<td  valign="top">:</td>
											<td  valign="top" align=right>
											<b>$!failPajakan</b>
											<td valign="top">
											</td>
										</tr>
										<tr>
											<td valign="top">
												<font color="blue"><li>Fail Pajakan Kecil</li></font>
											</td>
											<td valign="top">:</td>
											<td  valign="top" align=right>
												<b>$!failPKecil</b>
											</td>
											<td valign="top">
										</tr>
										<tr>
											<td valign="top">
												<font color="blue"><li>Fail Gadaian</li></font>
											</td>
											<td  valign="top">:</td>
											<td  valign="top" align=right>
											<b>$!failGadaian</b>
											</td>
											<td valign="top">
										</tr>
										<tr>
											<td valign="top">
												<font color="blue"><li>Fail Pembelian</li></font>
											</td>
											<td valign="top">:</td>
											<td  valign="top" align=right>
												<b>$!failPembelian</b>
											</td>
											<td valign="top">
										</tr>
										<tr>
											<td valign="top">
												<font color="blue"><li>Fail Penswastaan</li></font>
											</td>
											<td  valign="top">:</td>
											<td  valign="top" align=right>
											<b>$!failPenswastaan</b>
											</td>
											<td valign="top">
										</tr>
										<tr>
											<td valign="top">
												<font color="blue"><li>Fail Perletakhakan</li></font>
											</td>
											<td valign="top">:</td>
											<td  valign="top" align=right>
												<b>$!failPerletakhakan</b>
											</td>
											<td valign="top">
										</tr>																				
										<tr>
											<td valign="top">
												<font color="blue"><li>Fail Berstatus Hapus</li></font>
											</td>
											<td valign="top">:</td>
											<td valign="top" align=right>
												<b>$!failHapus</b>
											</td>
											<td valign="top">
											</td>
										</tr>
										
									</table>
								</td>
							</tr>
						</table>
					
					</td>
				</tr>
			</table>
		
		
		
		</td>
	</tr>

	<tr>
		<td>
			<table width="100%" border="0" class="dashboard_bawah" >
				<tr>
					<td>
					#if($list_memo_aktif.size()>0)
					<div id="TabbedPanels1" class="TabbedPanels">
					  <ul class="TabbedPanelsTabGroup">
					    <li class="TabbedPanelsTab" tabindex="1" >Pengumuman</li>
					    <li class="TabbedPanelsTab" tabindex="1"  >Carta</li>
					  </ul>
					     <div class="TabbedPanelsContentGroup">
					  
					    <div class="TabbedPanelsContent">
					    <div id="div_memo">
						<table width="100%" >
							<tr>
								<td width="12%" align="center" valign="top"><img width="30" height="30" src="../img/online/kblogger.png" align="center"/></td>
								<td width="88%" valign="top">
									<table width="100%">
										<!--
										<tr>
										<td  valign="middle">
										<b>Pengumuman</b></td>
										</tr>
										-->
										
										<tr>
											<td valign="top" >
	
												#parse("app/ppk/frmPengumuman.jsp")
	
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						</div>
    					</div>
    					
    <div class="TabbedPanelsContent">   
   
<div style="height:225" id="div_stat">
	<table width="100%" >
		<tr>
			<td width="12%" align="center" valign="top">
				<img width="30" height="30" src="../img/images_stat.png" align="center"/>
			</td>
			<td width="88%" valign="top">
			<table width="100%">
				<tr>
					<td valign="top" >
						<b>Carta Rekod Hakmilik/Rizab di Pangkalan Data <br /><font color="blue">$!negeri_sever</font></b>
					</td>
				</tr>

				<tr>
					<td   valign="top" >
					
					    <canvas id="cvs" width="300" height="100" >[No canvas support]</canvas>
					 
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</div>

</div>
  
   </div>
</div>
 
				#else


<div style="height:240" id="div_stat">
<table width="100%" >
<tr>
<td width="12%" align="center" valign="top">
<img width="30" height="30" src="../img/images_stat.png" align="center"/>
</td>
<td width="88%" valign="top">
<table width="100%">
<tr>
<td   valign="top" >
<b>Carta Rekod Hakmilik/Rizab di Pangkalan Data <br /><font color="blue">$!negeri_sever</font></b>
</td>
</tr>

<tr>
<td   valign="top" >


    <canvas id="cvs" width="300" height="100" >[No canvas support]</canvas>
    
  
  
    



</td>
</tr>
</table>
</td>
</tr>
</table>
</div>

#end

								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

		</td>
		<td width="1%" valign="top">

		</td>
	</tr>
</table>


<input type="hidden" id="idfail" name="idfail" value="">
<input type="hidden" id="idpermohonan" name="idpermohonan" value="">

<script>

function dash() {	
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&command=open_dashboard";
	document.${formName}.submit();
}


function gotoPindah() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailPindah";
	document.${formName}.submit();
}

	function pautanStatus() {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.htp.FrmTukarStatus";
		document.${formName}.submit();
	}


	function pautanKemaskini() {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.htp.FrmPendaftaranFailHTP";
		document.${formName}.submit();
	}

function gotoBKE() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
	document.${formName}.submit();
}

	function pautan01() {
		//doAjaxCall${formName}("pohonfailbaru");
		var urlTemp = "$EkptgUtil.getTabID("Permohonan",$portal_role)?_portal_module=ekptg.view.htp.permohonan.FrmPermohonanTanah&command=pohonfailbaru";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
		
	}

	function pautan03() {
		//document.${formName}.actionPajakan.value = "daftarBaru";	
		var urlTemp = "$EkptgUtil.getTabID("Pajakan",$portal_role)?_portal_module=ekptg.view.htp.pajakan.FrmPajakanSenaraiFailView&actionPajakan=daftarBaru";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautan04() {
		//document.${formName}.langkah.value = '0->1';
		//document.${formName}.pagemode.value = "0";
		var urlTemp = "$EkptgUtil.getTabID("Pajakan Kecil",$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanKecilAjax&command=pkfailbaru&pagemode=0";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautan05() {
		var urlTemp = "$EkptgUtil.getTabID("Gadaian",$portal_role)?_portal_module=ekptg.view.htp.FrmGadaianA&command=FailBaru";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautan06() {
		var urlTemp = "$EkptgUtil.getTabID("Pembelian",$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=tambahFail";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}	
	
	function pautan07() {
		//document.${formName}.actionPenswastaan.value = "daftarBaru";
		//document.${formName}.mode.value = "new";	
		//var urlTemp = "$EkptgUtil.getTabID('Penswastaan',$portal_role)?_portal_module=ekptg.view.htp.FrmPenswastaan2SenaraiFailView&actionPenswastaan=daftarBaru&mode=new";
		var urlTemp = "$EkptgUtil.getTabID('Penswastaan',$portal_role)?_portal_module=ekptg.view.htp.penswastaan.FrmPenswastaanSenaraiFailView&actionPenswastaan=daftarBaru&mode=new";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautan08() {
		var urlTemp = "$EkptgUtil.getTabID("Perletakhakan",$portal_role)?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=tambah";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautanRekod() {
		var urlTemp = "$EkptgUtil.getTabID("Rekod",$portal_role)?_portal_module=ekptg.view.htp.rekod.FrmPendaftaranHakmilikRizabRekod";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautanRekodHakmilikRizab() {
		var urlTemp = "$EkptgUtil.getTabID("Rekod",$portal_role)?_portal_module=ekptg.view.htp.rekod.FrmRekodPendaftaranTanah";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
function gotoAddLama() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8SenaraiSemakInternalKutipan";
	document.${formName}.submit();
}

	function gotoFLMS() {
		//document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
		document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$myrole)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
		document.${formName}.submit();
	}
	
	function gotoFLMSstat() {
		//document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan&command=paparLaporan";
		document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$myrole)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan&command=paparLaporan";
		document.${formName}.submit();
	}

	function gotoProfile() {
		//document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=lebah_app_UpdateUserProfileModule";
		document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$myrole)?_portal_module=ekptg.view.admin.UserProfileInternal";
		document.${formName}.submit();
	}

	function gotoCalendar() {
		//document.${formName}.action = "$EkptgUtil.getTabID('Utiliti',$portal_role)?_portal_module=lebah.pm.module.ActivitiesModule";
		document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$myrole)?_portal_module=lebah.pm.module.ActivitiesModule";
		document.${formName}.submit();
	}

	function gotoInbox() {
		//document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.utils.FrmInboxUsers";
		document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$myrole)?_portal_module=ekptg.view.utils.FrmInboxUsers";
		document.${formName}.submit();
	}

function gotoSMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.sms.mySMS";
	document.${formName}.submit();
}

	function myinfo_skrin_carian() {
	    //document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8";
	    document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.utils.FrmSenaraiTugasan";
		document.${formName}.submit();
	}

	function pautanOnline() {
		//document.${formName}.action = "$EkptgUtil.getTabID("Permohonan <i>Online</i>",$portal_role)?_portal_module=ekptg.view.online.htp.FrmPermohonanPengesahan";
		document.${formName}.action = "$EkptgUtil.getTabID("<i>Online</i>",$portal_role)?_portal_module=ekptg.view.online.htp.FrmPermohonanPengesahan";
		document.${formName}.method="POST";
		document.${formName}.submit();
		
	}

</script>




<script type="text/javascript" charset="utf-8">
		
		$$("a.help").each( function(input) {
			new Tooltip(input, {backgroundColor: "#0000", borderColor: "#0000", textColor: "black",opacity:"100"});
		});
		$$("form input.help").each( function(input) {
			new Tooltip(input, {backgroundColor: "#FC9", borderColor: "#C96", textColor: "#000", textShadowColor: "#FFF"});
		});

</script>    
    
    <script src="../RGraph/libraries/RGraph.common.core.js" ></script>
    <script src="../RGraph/libraries/RGraph.common.dynamic.js" ></script>
    <script src="../RGraph/libraries/RGraph.common.tooltips.js" ></script>
    <script src="../RGraph/libraries/RGraph.common.effects.js" ></script>
    <script src="../RGraph/libraries/RGraph.hbar.js" ></script>
    <script src="../RGraph/libraries/RGraph.pie.js" ></script>   

<script>
	//var sek8 = '$!fail_sek8';
	//var sek17 = '$!fail_sek17';
	//var hapus = '$!fail_hapus';
	//var semua = '$!jumlah_keseluruhan';
	//var semua_pie = parseInt(sek8)+parseInt(sek17);
	var hakmilik = '$!jumlahHakmilik';
	var rizab = '$!jumlahRizab';
	
	window.onload = function (e){
	
            var hbar = new RGraph.HBar('cvs', [parseInt(hakmilik),parseInt(rizab)]);
            hbar.Set('chart.units.pre','');
            hbar.Set('chart.units.post','');
            hbar.Set('chart.colors', ['blue','red','blue','blue']);
            hbar.Set('chart.strokestyle', 'rgba(0,0,0,0)');
            hbar.Set('chart.labels.above', true);			
            //hbar.Set('chart.vmargin', 20);
            hbar.Set('chart.background.grid', true);
            hbar.Set('chart.labels', ['Hakmilik','Rizab']);            
            if (!RGraph.isOld()) {
                hbar.Set('chart.tooltips', ['Hakmilik','Rizab']);
            }            
            hbar.Set('chart.labels.above.decimals',0);
            hbar.Set('chart.xlabels', false);
            hbar.Set('chart.gutter.left', 90);
            hbar.Set('chart.gutter.right', 60);
            hbar.Set('chart.gutter.top',10);
    
            hbar.Set('chart.noxaxis', true);
            hbar.Set('chart.noxtickmarks', true);
            hbar.Set('chart.noytickmarks', true);
            RGraph.isOld() ? hbar.Draw() : RGraph.Effects.HBar.Grow(hbar);
			
			/*
			 var pie = new RGraph.Pie('cvs_pie', [parseInt(sek8),parseInt(sek17)]);
			//pie.Set('chart.gutter.left', 45);
            pie.Set('chart.colors', ['purple','yellow']);
            pie.Set('chart.labels', ['Seksyen 8','Seksyen 17']);
			//pie.Set('chart.key', ['Seksyen 8','Seksyen 17']);
            //pie.Set('chart.key.background', 'white');
            //pie.Set('chart.labels.sticks', true);
            //pie.Set('chart.labels.sticks.length', 5);
            pie.Set('chart.exploded', 5);            
            if (!RGraph.isOld()) {
                pie.Set('chart.shadow', true);
                pie.Set('chart.shadow.offsetx', 4);
                pie.Set('chart.shadow.offsety', 4);
                pie.Set('chart.shadow.blur', 4);
                pie.Set('chart.tooltips', ['123','9291']);
            }			
            pie.Set('chart.radius', 50);
          //  pie.Set('chart.centerx', 90);
           // pie.Set('chart.centery', 120);
            pie.Set('chart.strokestyle', 'rgba(0,0,0,0)');            
            RGraph.isOld() ? pie.Draw() : RGraph.Effects.Pie.RoundRobin(pie); 
			*/          
        }
		/*
		function changeTab(index)
		{
		doAjaxCall${formName}("changeTab","tab_index="+index+"");
		}*/
	</script>

#if($list_memo_aktif.size()>0)
	<script>	
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});		
    </script>
#end
    

<!-- Carian Terperinci -->    
<script>
function runScript(e) {
	 var key;     
    if(window.event)
         key = window.event.keyCode; //IE
    else
         key = e.which; //firefox     

    return (key != 13);
}	

function open_info() {
	var width  = 550;
	var height = 300;
	var left   = (screen.width  - width)/2;
	var top    = (screen.height - height)/2;
 
	var params = 'width='+width+', height='+height;
 	params += ', top='+top+', left='+left;
	params += ', directories=no';
	params += ', location=front';
	params += ', menubar=no';
	params += ', resizable=no';
	params += ', scrollbars=no';
	params += ', status=no';
	params += ', toolbar=no';
	new_window = open("","title",params);
	new_window.document.open();

	new_window.document.write("<html><title>Info Maklumat Carian</title>");
	new_window.document.write("<body bgcolor=\"#FFFFFF\">");
	new_window.document.write("<table><tr><td><b><u>Jenis-Jenis Carian</u></b></td></tr></table>");
	
	new_window.document.write("<table width='100%'><tr><td width='50%' valign='top'>");
	
	new_window.document.write("<table><tr><td><b>Fail</b></td></tr>");
	new_window.document.write("<tr><td><font color='blue'><li>&nbsp;No. Fail </li></font>");
	new_window.document.write("<font color='blue'><li>&nbsp;No. Fail PTG</li></font>");
	new_window.document.write("<font color='blue'><li>&nbsp;No. Fail PTD</li></font>");
	new_window.document.write("<font color='blue'><li>&nbsp;Tajuk</li></font>	");
	new_window.document.write("<font color='blue'><li>&nbsp;Kementerian</li></font></td></tr></table>");
	
	new_window.document.write("</td><td width='50%' valign='top'>");
	
	new_window.document.write("<table><tr><td><b>Hakmilik</b></td></tr>");
	new_window.document.write("<tr><td><font color='blue'><li>&nbsp;No. Hakmilik</li></font>");
	new_window.document.write("<font color='blue'><li>&nbsp;No. Lot/PT</li></font>");
	new_window.document.write("</td></tr></table>");
	
	new_window.document.write("</td></tr></table>");
	
	new_window.document.write("<table><tr><td align='justify'>Sistem akan memaparkan bilangan fail dan hakmilik berdasarkan maklumat carian yang dimasukkan. Jika bilangan menunjukkan lebih daripada '0', pengguna boleh klik pada <i>link</i> tersebut bagi memaparkan senarai fail, hakmilik dan pihak berkepentingan yang berkenaan.</td></tr></table>");
	new_window.document.write("</body></html>");
	new_window.document.close();
}

function open_infoSID() {
	var width  = 550;
	var height = 300;
	var left   = (screen.width  - width)/2;
	var top    = (screen.height - height)/2;
 
	var params = 'width='+width+', height='+height;
 	params += ', top='+top+', left='+left;
	params += ', directories=no';
	params += ', location=front';
	params += ', menubar=no';
	params += ', resizable=no';
	params += ', scrollbars=no';
	params += ', status=no';
	params += ', toolbar=no';
	new_window = open("","title",params);
	new_window.document.open();

	new_window.document.write("<html><title>Info Maklumat Carian</title>");
	new_window.document.write("<body bgcolor=\"#FFFFFF\">");
	new_window.document.write("<table><tr><td><b><u>Jenis-Jenis Carian</u></b></td></tr></table>");
	
	new_window.document.write("<table width='100%'><tr><td width='50%' valign='top'>");
	
	new_window.document.write("<table><tr><td><b>Fail</b></td></tr>");
	new_window.document.write("<tr><td><font color='blue'><li>&nbsp;No. Fail </li></font>");
	
	new_window.document.write("</td></tr></table>");
	
	new_window.document.write("<table><tr><td align='justify'>Sistem akan memaparkan senarai dokumen berkaitan dengan no fail yang di masukkan.</td></tr></table>");
	new_window.document.write("</body></html>");
	new_window.document.close();
}

function cari(){
	if(document.getElementById('search').value==""){
		alert("Sila Masukkan Maklumat Carian");
		document.getElementById('search').focus();
	}
	else{
		//reset_jquery('_');	
		//reset_jqueryAgihan('_');	
		document.getElementById('tr_carianFail').style.display="";
		document.getElementById('tr_carianHakmilik').style.display="";
		doDivAjaxCall$formname('div_carianFail','doCarianFail','');
		doDivAjaxCall$formname('div_carianHakmilik','doCarianHakmilik','');
	}
}

function kosong(){	
	document.getElementById('search').value="";
	//reset_jquery('_');	
	//reset_jqueryAgihan('_');
	document.getElementById('tr_carianFail').style.display="none";
	document.getElementById('tr_carianHakmilik').style.display="none";
	doDivAjaxCall$formname('div_carianFail','doCloseCarianFail','');
	doDivAjaxCall$formname('div_carianHakmilik','doCloseCarianHakmilik','');
}

function doGetListFail(){
	reset_jqueryCarian('div_listFail');	
	document.getElementById('div_listFail').style.display="";		
	doDivAjaxCall$formname('div_listFail','doGetListFail','');
}

function doGetListHakmilik(){
	reset_jqueryCarian('div_listHakmilik');	
	document.getElementById('div_listHakmilik').style.display="";		
	doDivAjaxCall$formname('div_listHakmilik','doGetListHakmilik','');
}

function reset_jqueryCarian(current_div){
	if(current_div!="div_listFail")
	{
		
		if (document.getElementById('div_listFail') != null && document.getElementById('div_listFail') != undefined) 
		{
			
		document.getElementById('div_listFail').style.display="none";	
		
		doDivAjaxCall$formname('div_listFail','doCloseListFail','');	
		}
	}
	
	if(current_div!="div_listHakmilik")
	{
		//alert("2");
		if (document.getElementById('div_listHakmilik') != null && document.getElementById('div_listHakmilik') != undefined) 
		{
		//	alert("2a");
		document.getElementById('div_listHakmilik').style.display="none";	
		
		doDivAjaxCall$formname('div_listHakmilik','doCloseListHakmilik','');
		}
	}
	
	if(current_div!="div_listCukai")
	{
		//alert("2");
		if (document.getElementById('div_listCukai') != null && document.getElementById('div_listCukai') != undefined) 
		{
		//	alert("2a");
		document.getElementById('div_listCukai').style.display="none";	
		
		doDivAjaxCall$formname('div_listCukai','doCloseListCukai','');
		}
	}
	
}
function papar(ID_FAIL,ID_URUSAN,ID_STATUS,ID_PERMOHONAN,ID_HTPPERMOHONAN,NO_FAIL) {

	//(1,2,3,10,108,4,5,309)
	
	
	if (ID_URUSAN == '1' || ID_URUSAN == '10'){	
		document.${formName}.action = "$EkptgUtil.getTabID('Permohonan',$portal_role)?_portal_module=ekptg.view.htp.permohonan.FrmPerolehanTanahPermohonan&command=viewMaklumatPermohonan";
		document.${formName}.idfail.value = ID_FAIL;
		document.${formName}.idpermohonan.value = ID_PERMOHONAN;
	}//
	
	else if (ID_URUSAN == '2'){	
		document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=detail&idPermohonan="+ID_PERMOHONAN+"&idHtpPermohonan="+ID_HTPPERMOHONAN;              
	}//
	
	else if (ID_URUSAN == '3'){	
		document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView&mode=view&idPermohonan="+ID_PERMOHONAN+"&idFail="+ID_FAIL;
	}//
	
	else if (ID_URUSAN == '4'){	
		url = "../servlet/ekptg.view.htp.FrmPenswastaan2Servlet?command=papar&idFail="+ID_FAIL;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		
		document.${formName}.action = "$EkptgUtil.getTabID('Penswastaan',$portal_role)?_portal_module=ekptg.view.htp.FrmPenswastaan2SenaraiFailView&actionPenswastaan=papar&mode=view&idFail="+ID_FAIL;
	}//
	
	else if (ID_URUSAN == '5'){	
		document.${formName}.action = "$EkptgUtil.getTabID('Perletakhakan',$portal_role)?_portal_module=ekptg.view.htp.FrmHakmilikPerletakhakan&actionPerletakhakan=papar&idFail="+ID_FAIL;
	}//
	
	else if (ID_URUSAN == '108'){	
		document.${formName}.action = "$EkptgUtil.getTabID('Gadaian',$portal_role)?_portal_module=ekptg.view.htp.FrmGadaianA&command=SenaraiPermohonan&idFail="+ID_FAIL+"&noFail="+NO_FAIL;
	}//
	
	<!-- STOP SINI
	else if (ID_URUSAN == '309'){	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=semak&paging=1";
		document.${formName}.id_fail.value = ID_FAIL;
	}//
	-->
	
	else {
		alert("Sila Hubungi Sistem Admin");
	}
	
	document.${formName}.method="POST";
	document.${formName}.submit();
	
}

//Penambahbaikan. syaz. 26112014. notifikasi cukai
function doGetListCukai(){
	reset_jqueryCarian('div_listCukai');	
	document.getElementById('div_listCukai').style.display="";		
	doDivAjaxCall$formname('div_listCukai','doGetListCukai','');
}

function paparCukai(ID_NEGERI,ID_DAERAH,ID_MUKIM,ID_KEMENTERIAN,NO_HAKMILIK,ID_JENISHAKMILIK,ID_HAKMILIKCUKAI) {
	
	var params = "&idNegeri="+ID_NEGERI+"&idDaerah="+ID_DAERAH+"&idMukim="+ID_MUKIM+"&idKementerian="+ID_KEMENTERIAN+"&noHakmilik="+NO_HAKMILIK+"&idJenisHakmilik="+ID_JENISHAKMILIK+"&idHakmilikCukai="+ID_HAKMILIKCUKAI;
	
	document.${formName}.action = "$EkptgUtil.getTabID('Cukai',$portal_role)?_portal_module=ekptg.view.htp.FrmCukaiKemaskini"+
								  "&command1=kemaskiniCukai&pagemode=viewCukaiV1"+params;
	
	document.${formName}.method="POST";
	document.${formName}.submit();
}
	function doReadCukai(ID_CUKAITEMP){
		document.getElementById('div_listCukai').style.display="";		
		doDivAjaxCall$formname('div_listCukai','doReadCukai','id_cukaitemp='+ID_CUKAITEMP);
	}

function paparDetailHakmilik(idHakmilik,noHakmilik,statusSah){
	var params = "&idHakmilikDashboard="+idHakmilik+"&statusSahDashboard="+statusSah+"&fromDashboard=yes&noHakmilikDashboard="+noHakmilik;
	document.${formName}.action = "$EkptgUtil.getTabID('Rekod',$portal_role)?_portal_module=ekptg.view.htp.rekod.FrmRekodTanah"+params;
	document.${formName}.method="POST";
	document.${formName}.submit();
}

</script>    
#parse("app/integrasi/gis/javaScript.jsp")    

<script>
function arkibWindow(){
	var noFail = document.${formName}.txtNoFail.value;
		var url = "../x/${securityToken}/ekptg.view.integrasi.sid.FrmPopupPaparArkibDokumen?kodModul=HTP&noFail="+noFail;
		
		var hWnd = window.open(url,"printuser","scrollbars=1,width=800,height=700");
		 if ((document.window != null) && (!hWnd.opener))
		       hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
			
	}
</script>

   