<!-- 
<script type="text/javascript" src="../library/online/prototype.js"></script>
<script type="text/javascript" src="../library/online/scriptaculous/scriptaculous.js"></script>
<script type="text/javascript" src="../library/online/tooltips.js"></script>
 -->
<script type="text/javascript" src="../library/scriptaculous/prototype.js" ></script>
<script type="text/javascript" src="../library/scriptaculous/scriptaculous.js" ></script>

<!-- class="dashboard_sub_kiri" -->

<p align="center">
<table cellpadding="2" cellspacing="1" border="0" width="100%" class="nav" align="center" >
  <tr>
    <td width="1%" valign="top"></td>
    <td width="59%" valign="top"  ><!-- class="nav_sub_kiri" -->
      
      <table width="100%" border="0" align="left" class="dashboard_kiri">
        <tr>
          <td valign="top" align="left"><table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
              <tr>
                <td width="50%" valign="top"><table width="50%" >
                    <tr>
                      <td width="15%" align="center" valign="top"><img src="../img/myprofile.png"  width="30" height="30" align="center"/></td>
                      <td width="85%"><table>
                          <tr>
                            <td><b>Profil</b></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoProfile()" class="help" title="Tukar Kata Laluan"> <font color="blue">
                              <li>&nbsp;Tukar Kata Laluan</li>
                              </font> </a></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoCalendar()" class="help" title="MyCalendar"> <font color="blue">
                              <li><i>&nbsp;My Calendar</i></li>
                              </font> </a></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoInbox()" class="help" title="My Inbox"> <font color="blue">
                              <li> #if($!check_notifikasi_inbox > 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>$!check_notifikasi_inbox</blink></font></b> </label>
                                &nbsp;
                                #end
                                Ruang Perbincangan Formal</li>
                              </font> </a></td>
                          </tr>
                        </table></td>
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
                  </table></td>
                <!--<td valign="top" width="50%" >Kanan</td>-->
                <td width="50%" valign="top"><table width="100%" border="0">
                    <tr>
                      <td width="15%" align="center" valign="top"><img src="../img/cari_icon.png" alt="cari" width="30" height="30" align="center"/></td>
                      <td width="85%"><table>
                          <tr>
                            <td><b>Carian Terperinci</b></td>
                          </tr>
                          <tr>
                            <td><input type="text" name="search" id="search"    maxlength="200"   size="30" onkeydown="return runScript(event)" onkeypress="return runScript(event)" onkeyup="return runScript(event)"  /></td>
                            <td><a href="javascript:open_info()" class="help" title="info"> <b><font color="blue"><img src="../img/info.png"  align="center" /></font></b> </a></td>
                          </tr>
                          <tr>
                            <td><input type="button" id="but_search" name="but_search" value="Cari" onClick="cari()"  />
                              <input type="button" id="but_search" name="but_search" value="Kosongkan" onClick="kosong()"  /></td>
                          </tr>
                          <tr id="tr_carianFail">
                            <td><div id="div_carianFail" ></div></td>
                          </tr>
                          <!--TAMBAH LIST-->
                          <tr id="tr_carianAktaPindaan">
                            <td><div id="div_carianAktaPindaan" ></div></td>
                          </tr>
                          <!--<tr>
                            <td><a href="http://www.etapp.gov.my/map/" target="_blank" style="color:#0000FF">Maklumat GIS</a></td>
                          </tr>-->
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
        
        <!-- Senarai Tugasan
  		<tr>
    		<td valign="top" align="left">
    
		    <table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
				<tr>

				<td width="50%" valign="top">
				
					<table width="100%" >
						<tr>
							<td width="15%" align="center" valign="top"><img  src="../img/main.png" width="30" height="30" align="center"/></td>
							<td width="85%">
								<table>

									<tr>
										<td>
										<b>Tugasan</b>
										</td>
									</tr> --> 
        <!--
<tr>
<td>
bagi modul yang ada tugas spesifik untuk penguna yang login
<a href="javascript:myinfo_skrin_carian()" class="help" title="Fail Tugasan">
							<font color="blue"><li>Inbox</li></font>						
						</a>
</td>
</tr>
--> <!-- senarai Tugasan
									<tr>
										<td>
											<a href="javascript:myinfo_skrin_carian()" class="help" title="Fail Tugasan">
												<font color="blue"><li>&nbsp;Fail Tugasan</li></font>						
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


</td>

</tr>
</table>
    
    </td>
  </tr>  senarai tugasan-->
        <tr>
          <td valign="top" align="left"><table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
              <tr>
                <td width="50%" valign="top"><table width="100%" >
                    <tr>
                      <td width="15%" align="center" valign="top"><img  width="30" height="30" src="../img/new_application.png" align="center"/></td>
                      <td width="85%"><table>
                          <tr>
                            <td><b>Daftar Maklumat</b></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:pautan01()" class="help" title="Akta"> <font color="blue">
                              <li>&nbsp;
                                
                                #if($!failPemberimilikann != 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;$!failPemberimilikann</blink></font></b> </label>
                                &nbsp;
                                #end
                                Akta </li>
                              </font> </a></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:pautan03()" class="help" title="Akta Pindaan"> <font color="blue">
                              <li>&nbsp;
                                
                                #if($!failPerizapann != 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;$!failPerizapann</blink></font></b> </label>
                                &nbsp;
                                #end
                                Akta Pindaan </li>
                              </font> </a></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:pautan07()" class="help" title="Dokumen "> <font color="blue">
                              <li>&nbsp;
                                
                                #if($!failPembeliann != 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;$!failPembeliann</blink></font></b> </label>
                                &nbsp;
                                #end
                                Dokumen </li>
                              </font> </a></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:pautan04()" class="help" title="Enakmen"> <font color="blue">
                              <li>&nbsp;
                                
                                #if($!enakmenBaru != 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;$!enakmenBaru</blink></font></b> </label>
                                &nbsp;
                                #end
                                Enakmen </li>
                              </font> </a></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:pautan05()" class="help" title="Enakmen Pindaan"> <font color="blue">
                              <li>&nbsp;
                                
                                #if($!failPKeciln != 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;$!failPKeciln</blink></font></b> </label>
                                &nbsp;
                                #end
                                Enakmen Pindaan </li>
                              </font> </a></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:pautan08()" class="help" title="Keputusan Mahkamah"> <font color="blue">
                              <li>&nbsp;
                                
                                #if($!failMahkamahn != 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;$!failMahkamahn</blink></font></b> </label>
                                &nbsp;
                                #end
                                Keputusan Mahkamah </li>
                              </font> </a></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:pautan09()" class="help" title="Pandangan Undang-Undang"> <font color="blue">
                              <li>&nbsp;
                                
                                #if($!failPUUn != 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;$!failPUUn</blink></font></b> </label>
                                &nbsp;
                                #end
                                Pandangan Undang-Undang </li>
                              </font> </a></td>
                          </tr>
                          
                          <tr>
                            <td><a href="javascript:pautan06()" class="help" title="Pekeliling"> <font color="blue">
                              <li>&nbsp;
                                
                                #if($!failGadaiann != 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;$!failGadaiann</blink></font></b> </label>
                                &nbsp;
                                #end
                                Pekeliling </li>
                              </font> </a></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:pautan10()" class="help" title="Maklumat Tanah Rizab Melayu"> <font color="blue">
                              <li>&nbsp;
                                
                                #if($!failMaklumatTRMn != 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;$!failMaklumatTRMn</blink></font></b> </label>
                                &nbsp;
                                #end
                                Maklumat Tanah Rizab Melayu </li>
                              </font> </a></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:pautan11()" class="help" title="Dokumen Berkaitan Tanah Rizab Melayu"> <font color="blue">
                              <li>&nbsp;
                                
                                #if($!failDocTRMn != 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;$!failDocTRMn</blink></font></b> </label>
                                &nbsp;
                                #end
                                Dokumen Berkaitan Tanah Rizab Melayu </li>
                              </font> </a></td>
                          </tr>
                          
                          <!--<tr>
                            <td><a href="javascript:pautan08()" class="help" title="Maklumat Perundangan"> <font color="blue">
                              <li>&nbsp;
                                
                                #if($!failPenswastaann != 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;$!failPenswastaann</blink></font></b> </label>
                                &nbsp;
                                #end
                                Maklumat Perundangan </li>
                              </font> </a></td>
                          </tr>-->
                          
                            <td>&nbsp;</td>
                          <tr> </tr>
                          <tr>
                            <td><font class=display_msg>Simbol</font>
                              <label style="background-color:blue" align="center" valign="top" > <b><font color="WHITE"><blink>&nbsp;10</blink></font></b> </label>
                              &nbsp; <font class=display_msg>Merupakan Maklumat Baru Yang Dimasukkan	(dalam masa 21 hari)</font>
                              </li>
                              </font> </a></td>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
                <td width="50%" valign="top"><!-- Kandungan Sebelah Kanan
		<table width="100%" >
			<tr>
				<td width="15%" align="center" valign="top"> --> 
                  <!-- <img width="30" height="30" src="../img/utiliti.png" align="center"/> --> 
                  <!--
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


					</table>
					
				</td>
			</tr>
		</table>
					kandungan sebelah kanan --></td>
              </tr>
            </table></td>
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
          <td valign="top" align="left"><table cellpadding="2" cellspacing="1" border="0" width="100%" class="" align="left">
              <tr>
                <td width="50%" valign="top"><table width="100%" >
                    <tr>
                      <td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/aduan.png"/></td>
                      <td width="85%"><table >
                          <tr>
                            <td><b>PLA</b> (Pengurusan Log Aduan) </td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoFLMS()" class="help" title="Hantar Log Aduan, Cadangan atau Pertanyaan"> <font color="blue">
                              <li> #if($!check_notifikasi_aduan > 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>$!check_notifikasi_aduan</blink></font></b> </label>
                                &nbsp;
                                #end
                                Log Aduan</li>
                              </font> </a></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoFLMSstat()" class="help" title="Statistik Log FLMS"> <font color="blue">
                              <li>&nbsp;Statistik Log</li>
                              </font> </a></td>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
                <td width="50%" valign="top">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td valign="top" align="left"><table cellpadding="2" cellspacing="1" border="0" width="100%" class="" align="left">
              <tr>
                <td width="50%" valign="top"><table width="100%" >
                   <!-- <tr>
                      <td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/lawnet.jpg"/></td>
                      <td width="85%"><table >
                          <tr>
                            <td><a href="http://www.lawnet.com.my/lawnetpublic/Home/tabid/36/Default.aspx" target="_blank" style="color:#0000FF">http://www.lawnet.com.my</a><br /></td>
                          </tr>
                        </table></td>
                    </tr>-->
                  </table></td>
                <td width="50%" valign="top">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    <td width="39%" valign="top"><table width="100%" border="0" >
        <tr>
          <td width="100%"><table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_tepi" align="left">
              <tr>
                <td width="100%" valign="top"><table width="100%" >
                    <tr>
                      <td width="15%" align="center" valign="top"></td>
                      <td width="85%" ><table width="100%">
                          <tr>
                            <td   colspan="3"><b>Statistik Maklumat di Pangkalan Data <br />
                              <font color="blue">$!negeriServer</font></b></td>
                          </tr>
                          <tr>
                            <td width="50%" valign="top"><font color="blue">
                              <li>Keseluruhan </li>
                              </font></td>
                            <td width="1%" valign="top">:</td>
                            <td width="19%" valign="top" align=right><b>$!jumlahKeseluruhan</b></td>
                            <td width="30%" valign="top"></td>
                          </tr>
                          <tr>
                            <td valign="top"><font color="blue">
                              <li>Akta</li>
                              </font></td>
                            <td  valign="top">:</td>
                            <td  valign="top" align=right><b>$!failPemberimilikan</b></td>
                            <td valign="top">
                          </tr>
                          <tr>
                            <td valign="top"><font color="blue">
                              <li>Akta Pindaan</li>
                              </font></td>
                            <td valign="top">:</td>
                            <td  valign="top" align=right><b>$!failPerizapan</b></td>
                            <td valign="top">
                          </tr>
                          <tr>
                            <td valign="top"><font color="blue">
                              <li>Dokumen</li>
                              </font></td>
                            <td valign="top">:</td>
                            <td  valign="top" align=right><b>$!failPembelian</b></td>
                            <td valign="top">
                          </tr>
                          <tr>
                            <td valign="top"><font color="blue">
                              <li>Enakmen </li>
                              </font></td>
                            <td  valign="top">:</td>
                            <td  valign="top" align=right><b>$!failPajakan</b>
                            <td valign="top"></td>
                          </tr>
                          <tr>
                            <td valign="top"><font color="blue">
                              <li>Enakmen Pindaan</li>
                              </font></td>
                            <td valign="top">:</td>
                            <td  valign="top" align=right><b>$!failPKecil</b></td>
                            <td valign="top">
                          </tr>
                          <tr>
                            <td valign="top"><font color="blue">
                              <li>Keputusan Mahkamah</li>
                              </font></td>
                            <td  valign="top">:</td>
                            <td  valign="top" align=right><b>$!failMahkamah</b></td>
                            <td valign="top">
                          </tr>
                          <tr>
                            <td valign="top"><font color="blue">
                              <li>Pandangan Undang-Undang</li>
                              </font></td>
                            <td  valign="top">:</td>
                            <td  valign="top" align=right><b>$!failPUU</b></td>
                            <td valign="top">
                          </tr>
                          <tr>
                            <td valign="top"><font color="blue">
                              <li>Pekeliling</li>
                              </font></td>
                            <td  valign="top">:</td>
                            <td  valign="top" align=right><b>$!failGadaian</b></td>
                            <td valign="top">
                          </tr>
                          <tr>
                            <td valign="top"><font color="blue">
                              <li>Maklumat Tanah Rizab Melayu</li>
                              </font></td>
                            <td  valign="top">:</td>
                            <td  valign="top" align=right><b>$!failMaklumatTRM</b></td>
                            <td valign="top">
                          </tr>
                          <tr>
                            <td valign="top"><font color="blue">
                              <li>Dokumen Berkaitan Tanah Rizab Melayu</li>
                              </font></td>
                            <td  valign="top">:</td>
                            <td  valign="top" align=right><b>$!failDocTRM</b></td>
                            <td valign="top">
                          </tr>
                          
                         <!-- <tr>
                            <td valign="top"><font color="blue">
                              <li>Maklumat Perundangan</li>
                              </font></td>
                            <td  valign="top">:</td>
                            <td  valign="top" align=right><b>$!failPenswastaan</b></td>
                            <td valign="top">
                          </tr>-->
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td><table width="100%" border="0" class="dashboard_bawah" >
              <tr>
                <td> #if($list_memo_aktif.size()>0)
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
                              <td width="88%" valign="top"><table width="100%">
                                  <!----> 
                                  
                                  <!--
										<tr>
										<td  valign="middle">
										<b>Pengumuman</b></td>
										</tr>
										-->
                                  <tr>
                                    <td valign="top" > #parse("app/ppk/frmPengumuman.jsp") </td>
                                  </tr>
                                </table></td>
                            </tr>
                          </table>
                        </div>
                      </div>
                      <div class="TabbedPanelsContent">
                        <div style="height:225" id="div_stat">
                          <table width="100%" >
                            <tr>
                              <td width="12%" align="center" valign="top"><img width="30" height="30" src="../img/images_stat.png" align="center"/></td>
                              <td width="88%" valign="top"><table width="100%">
                                  <tr>
                                    <td valign="top" ><b>Carta Maklumat di Pangkalan Data <br />
                                      <font color="blue">$!negeri_sever</font></b></td>
                                  </tr>
                                  <tr>
                                    <td   valign="top" ><canvas id="cvs" width="300" height="100" >[No canvas support]</canvas></td>
                                  </tr>
                                </table></td>
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
                        <td width="12%" align="center" valign="top"><img width="30" height="30" src="../img/images_stat.png" align="center"/></td>
                        <td width="88%" valign="top"><table width="100%">
                            <tr>
                              <td   valign="top" ><b>Carta Maklumat di Pangkalan Data <br />
                                <font color="blue">$!negeri_sever</font></b></td>
                            </tr>
                            <tr>
                              <td   valign="top" ><canvas id="cvs" width="300" height="100" >[No canvas support]</canvas></td>
                            </tr>
                          </table></td>
                      </tr>
                    </table>
                  </div>
                  #end </td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    <td width="1%" valign="top"></td>
  </tr>
  <!---->
</table>
<script>

if($diff == true){
	if (confirm("Sila tukar password anda!") == true) {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=lebah_app_UpdateUserProfileModule";
		document.${formName}.submit();
	} else {
		
	}
}

if($diff0 == true){
	alert("Sila tukar password anda!");
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=lebah_app_UpdateUserProfileModule";
	document.${formName}.submit();
}

function doCetak(namaborang) {

	if(namaborang=="BorangA") {
    	var url = "../reports/ppk/BORANG A0.pdf";
	} else if(namaborang=="BorangP") {
    	var url = "../reports/ppk/BORANG P0.pdf";
	} else if(namaborang=="BorangK1") {
    	var url = "../reports/ppk/BORANG K10.pdf";
	} else if(namaborang=="BorangK2") {
    	var url = "../reports/ppk/BORANG K20.pdf";
	} else if(namaborang=="BorangPersetujuan") {
    	var url = "../reports/ppk/BORANG PERSETUJUAN.pdf";
	}
	
    var hWnd = window.open(url,'Cetak','width=700,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSlipPendengaran(idfail,NO_FAIL) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idfail="+idfail+"&report=SlipPendengaran";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	
    if (hWnd.focus != null) hWnd.focus();
}

function setTable(id) {
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	} else if(document.getElementById(id).style.display=="block") {
		document.getElementById(id).style.display="none";
	}
}

function paparAkta2(id_akta) {
	//?_portal_module=ekptg.view.pdt.FrmViewAkta&action=view
	    //alert('a');
		document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Dokumen Teks",$portal_role)?_portal_module=ekptg.view.pdt.FrmViewAkta&action=view&Akta_IDAkta="+id_akta;	
		
		//document.${formName}.idpermohonan.value = idPermohonan;
		//document.${formName}.idSimati.value = idSimati;
		//document.${formName}.flagFromSenaraiPermohonanSek8.value = "yes";
		
		//document.${formName}.method="POST";
		
		document.${formName}.submit();
}

function paparAktaPinda(id_aktapinda) {
	//?_portal_module=ekptg.view.pdt.FrmViewAkta&action=view
	    //alert('a');
		document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Dokumen Teks",$portal_role)?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan&command=paparsenaraipindaan_detail&idAktaPindaan="+id_aktapinda;	
		
		//document.${formName}.idpermohonan.value = idPermohonan;
		//document.${formName}.idSimati.value = idSimati;
		//document.${formName}.flagFromSenaraiPermohonanSek8.value = "yes";
		
		//document.${formName}.method="POST";
		
		document.${formName}.submit();
}


function paparBorangB(idPermohonan,idSimati,seksyen) {
	if (seksyen == '8') {
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";	
	} else {
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
	}	
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.idSimati.value = idSimati;
		document.${formName}.flagFromSenaraiPermohonanSek8.value = "yes";
		
		document.${formName}.method="POST";
		
		document.${formName}.submit();
}

function dash() {	
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&command=open_dashboard";
	document.${formName}.submit();
}

function gotoSenaraiBorangB() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiBorangB";
	document.${formName}.submit();
}

function gotoSenaraiKiv() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiKiv";
	document.${formName}.submit();
}

function gotoPindah() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailPindah";
	document.${formName}.submit();
}

function gotoStatus() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmTukarStatus";
	document.${formName}.submit();
}

function gotoEdit() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
	document.${formName}.submit();
}

function gotoKemaskini() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView";
	document.${formName}.submit();
}

function gotoBKE() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
	document.${formName}.submit();
}

function gotoHTP() {
	document.${formName}.action = "$EkptgUtil.getTabID("Harta Tanah Persekutuan",$portal_role)";
	document.${formName}.submit();
}

function gotoFail() {
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumenPendaftaranFail";
	document.${formName}.submit();
}

function gotoPergerakanFail() {
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumenPergerakanFail";
	document.${formName}.submit();
}

function gotoFLMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
	document.${formName}.submit();
}

function gotoFLMSstat() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan&command=paparLaporan";
	document.${formName}.submit();
}

function gotoPPK() {
	document.${formName}.action = "$EkptgUtil.getTabID("Pusaka Kecil",$portal_role)";
	document.${formName}.submit();
}

/*function gotoCalendar() {
	<!--lebah.pm.module.ActivitiesModule-->
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=lebah.pm.module.ActivitiesModule";
	document.${formName}.submit();
}*/

function gotoInbox() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.utils.FrmInboxUsers";
	document.${formName}.submit();
}

function gotoSMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.sms.mySMS";
	document.${formName}.submit();
}

function gotoPPT() {
    document.${formName}.action = "$EkptgUtil.getTabID("Pengambilan Tanah",$portal_role)";
	document.${formName}.submit();
}

function gotoPHP() {

	document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasa Dan Hasil Persekutuan",$portal_role)";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function gotoTempahanBilik() {
	document.${formName}.action = "$EkptgUtil.getTabID("Mesyuarat",$portal_role)?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTempahan";
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
	
	var bilAktan = '$!failPemberimilikann';
	var bilAktapn = '$!failPerizapann';
	var bilEnakmenn = '$!enakmenBaru';
	var bilEnakmenpn = '$!failPKeciln';
	var bilPekelilingn = '$!failGadaiann';
	var bilDokumenn = '$!failPembeliann';
	var bilPerundangann = '$!failPenswastaann';

	var fail_selesai = parseInt(bilAktan)+parseInt(bilAktapn)+parseInt(bilEnakmenn)+parseInt(bilEnakmenpn)+parseInt(bilPekelilingn)+parseInt(bilDokumenn)+parseInt(bilPerundangann);
	var fail_xselesai = '$!jumlahKeseluruhan';
	
        window.onload = function (e){
            var hbar = new RGraph.HBar('cvs', [parseInt(fail_xselesai),parseInt(fail_selesai)]);
            hbar.Set('chart.units.pre','');
            hbar.Set('chart.units.post','');
            hbar.Set('chart.colors', ['blue','red','blue','blue']);
            hbar.Set('chart.strokestyle', 'rgba(0,0,0,0)');
            hbar.Set('chart.labels.above', true);			
            //hbar.Set('chart.vmargin', 20);
            hbar.Set('chart.background.grid', true);
            hbar.Set('chart.labels', ['Keseluruhan','Baru (21 Hari)']);            
            if (!RGraph.isOld()) {
                hbar.Set('chart.tooltips', ['Keseluruhan','Baru (21 Hari)']);
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
			
        }
        
	//Kiri, bahagian atas
	function gotoProfile() {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=lebah_app_UpdateUserProfileModule";
		document.${formName}.submit();
	}
	function gotoCalendar() {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=lebah.pm.module.ActivitiesModule";
		document.${formName}.submit();
	}	
	
	function gotoInbox() {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.utils.FrmInboxUsers";
		document.${formName}.submit();
	}
	//Kiri, End bahagian atas
	
	//Kiri, bahagian bawah
	function gotoFLMS() {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
		document.${formName}.submit();
	}
	
	function gotoFLMSstat() {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan&command=paparLaporan";
		document.${formName}.submit();
	}
	//Kiri, End bahagian bawah
	
	//Maklumat Akta
	function pautan01() {
		var urlTemp = "$EkptgUtil.getTabID("Pengurusan Dokumen Teks",$portal_role)?_portal_module=ekptg.view.pdt.FrmViewAkta";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
		
	}

	function pautan03() {
		var urlTemp = "$EkptgUtil.getTabID("Pengurusan Dokumen Teks",$portal_role)?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautan04() {
		var urlTemp = "$EkptgUtil.getTabID("Pengurusan Dokumen Teks",$portal_role)?_portal_module=ekptg.view.pdt.FrmViewEnakmen";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautan05() {
		var urlTemp = "$EkptgUtil.getTabID("Pengurusan Dokumen Teks",$portal_role)?_portal_module=ekptg.view.pdt.PendaftaranEnakmenPindaan";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautan06() {
		var urlTemp = "$EkptgUtil.getTabID("Pengurusan Dokumen Teks",$portal_role)?_portal_module=ekptg.view.pdt.PendaftaranPekeliling";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}	
	
	function pautan07() {
		var urlTemp = "$EkptgUtil.getTabID("Pengurusan Dokumen Teks",$portal_role)?_portal_module=ekptg.view.pdt.PendaftaranDokumen";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautan08() {
		var urlTemp = "$EkptgUtil.getTabID("Pengurusan Dokumen Teks",$portal_role)?_portal_module=ekptg.view.pdt.FrmKeputusanMahkamah";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautan09() {
		var urlTemp = "$EkptgUtil.getTabID("Pengurusan Dokumen Teks",$portal_role)?_portal_module=ekptg.view.pdt.FrmSenaraiDokumen";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautan10() {
		var urlTemp = "$EkptgUtil.getTabID("Tanah Rizab Melayu",$portal_role)?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	
	function pautan11() {
		var urlTemp = "$EkptgUtil.getTabID("Tanah Rizab Melayu",$portal_role)?_portal_module=ekptg.view.pdt.FrmDokBerkaitanTanahRizabMelayu";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	
	}
	//end Maklumat Akta
	
	
</script> 
<script>
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
</script> 

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
	
	//new_window.document.write("<table><tr><td><b>Fail</b></td></tr>");
	//new_window.document.write("<tr><td><font color='blue'><li>&nbsp;ID Akta </li></font>");
	new_window.document.write("<font color='blue'><li>&nbsp;No. Akta</li></font>");
	new_window.document.write("<font color='blue'><li>&nbsp;Nama Akta</li></font>");
	new_window.document.write("<font color='blue'><li>&nbsp;Tarikh Kuatkuasa</li></font>	");
	new_window.document.write("<font color='blue'><li>&nbsp;Nama Seksyen</li></font></td></tr></table>");
	new_window.document.write("</td></tr></table>");
	
	new_window.document.write("<table><tr><td align='justify'>Sistem akan memaparkan bilangan fail Akta dan Akta Pindaan berdasarkan maklumat carian yang dimasukkan. Jika bilangan menunjukkan lebih daripada '0', pengguna boleh klik pada <i>link</i> tersebut bagi memaparkan senarai fail.</td></tr></table>");
	
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
		doDivAjaxCall$formname('div_carianFail','doCarianFail','');
		document.getElementById('tr_carianAktaPindaan').style.display="";
		doDivAjaxCall$formname('div_carianAktaPindaan','doCarianAktaPindaan','');

	}
}

function kosong(){	
	document.getElementById('search').value="";
	//reset_jquery('_');	
	//reset_jqueryAgihan('_');
	document.getElementById('tr_carianFail').style.display="none";
	doDivAjaxCall$formname('div_carianFail','doCloseCarianFail','');
	document.getElementById('tr_carianAktaPindaan').style.display="none";
	doDivAjaxCall$formname('div_carianAktaPindaan','doCloseCarianAktaPindaan','');

}

function doGetListFail(){
	reset_jqueryCarian('div_listFail');	
	document.getElementById('div_listFail').style.display="";		
	doDivAjaxCall$formname('div_listFail','doGetListFail','');
}

function doGetListAktaPindaan(){
	reset_jqueryCarianAktaPindaan('div_listAktaPindaan');	
	document.getElementById('div_listAktaPindaan').style.display="";		
	doDivAjaxCall$formname('div_listAktaPindaan','doGetListAktaPindaan','');
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
}
function reset_jqueryCarianAktaPindaan(current_div){
	if(current_div!="div_listAktaPindaan")
	{
		
		if (document.getElementById('div_listAktaPindaan') != null && document.getElementById('div_listAktaPindaan') != undefined) 
		{
			
		document.getElementById('div_listAktaPindaan').style.display="none";	
		
		doDivAjaxCall$formname('div_listAktaPindaan','doCloseListAktaPindaan','');	
		}
	}	
}







</script> 
