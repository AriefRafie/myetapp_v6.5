<table width='100%' align='center' border='0' cellspacing='1' cellpadding='2' class='classFade'>
	<tr>
		<td width='1%'></td><td width='28%'></td><td width='1%'></td><td width='70%'></td>
	</tr>
	<tr>
		<td colspan='4' class='table_header'>Perintah Perbicaraan</td>
	</tr>
</table>

<input type="hidden" name="idPermohonanSimati_perintah" value="$ID_PERMOHONANSIMATI" />
<input type="hidden" name="idPermohonan" value="$ID_PERMOHONAN" />
<input type="hidden" name="idSimati_perintah" value="$ID_SIMATI" />
<!--  input type="hidden" name="selectedTabLower" id="selectedTabLower" / -->
<input type="hidden" name="actionPerintah" id="actionPerintah" />
<input type="hidden" name="flagPopup" id="flagPopup" />
<input type="hidden" name="idHTA" id="idHTA" />
<input type="hidden" name="idHA" id="idHA" />
<input type="hidden" name="modePopup" id="modePopup" />
<input type="hidden" name="anchor" id="anchor" />
<input type="hidden" name="action" id="action" />
<input type="hidden" name="method" id="method" />


<!-- START PERINTAH PEMBAHAGIAN -->
<table width="100%" border="0" cellspacing="2" cellpadding="2">
<!-- START HEADER -->
<tr>
	<td>
		<a name="tabUpper"></a>
		<fieldset>
        	<legend><strong>PERINTAH PEMBAHAGIAN</strong></legend>
        	<div id="TabbedPanels1" class="TabbedPanels">
				<ul class="TabbedPanelsTabGroup">
		            <li class="TabbedPanelsTab" tabindex="0">HARTA TAK ALIH (ADA HAKMILIK)</li>
		            <li class="TabbedPanelsTab" tabindex="0">HARTA TAK ALIH (TIADA HAKMILIK)</li>
		            <li class="TabbedPanelsTab" tabindex="0">HARTA ALIH</li>
		            #if ($idStatus == 21)
		           		<li class="TabbedPanelsTab" tabindex="0">PENGHANTARAN PERINTAH</li>
		            #end
	          	</ul>
          		<div class="TabbedPanelsContentGroup">

		            <!-- START CONTENT HARTA TAK ALIH (ADA HAKMILIK) -->
		            <div class="TabbedPanelsContent">
		            	<div id="div_keputusan_perintah_htaah"></div>

	              		<table width="100%" border="0" cellspacing="2" cellpadding="2">

		                <!-- START LIST HARTA TAK ALIH (ADA HAKMILIK) -->
		                	<tr>
                  				<td>
                  					#if($flagAdaHTA)
                    				<fieldset>
                    					#set ($printalert = '1')

                      					<legend><strong>SENARAI HARTA TAK ALIH (ADA HAKMILIK)</strong></legend>
	                      				#if ($SenaraiHTA.size() > 10)
			                      		<div style="width:100%;height:215;overflow:auto">
			                      		#end
				                        	<table width="100%" border="0" cellspacing="2" cellpadding="2">
				                          		<tr>
				                            		<td colspan="4">
	                            					#if ($mode == 'update')
					                              	#if ($flagPopup == '')

					                              	#if ($flagSelesaiHTA == '' && $userRole != "user_ppk")
						                              <input name="cmdTambahHTA" id="cmdTambahHTA" value="Daftar Perintah" type="button" onClick="javascript:tambahHTA()"/>
					                              	#end
					                              	#end
					                              	#end
					                              	</td>
				                          		</tr>
					                          	<tr class="table_header">
						                            <td scope="row" width="5%" align="center">BIL</td>
						                            <td width="55%">KETERANGAN HAKMILIK</td>
						                            <td width="20%">JENIS PERINTAH</td>
						                            <td width="10%" align="center">STATUS PEMILIKAN</td>
						                            <td width="10%" align="center">BAHAGIAN SIMATI</td>
					                          	</tr>
					                          	#set ($PKP = "")

			                          			#set ($listHTA = "")

												#set ($idPerintahHTAOBMST="")

												#foreach ($listHTA in $SenaraiHTA)
													#if ($listHTA.bil == '')
													#set( $row = "row1" )
													#set ($idPerintahHTAOBMST=$idPerintahHTAOBMST+$listHTA.idPerintahHTAOBMST)
												#elseif (($listHTA.bil % 2) != 0)
													#set( $row = "row1" )
													#set ($idPerintahHTAOBMST=$idPerintahHTAOBMST+$listHTA.idPerintahHTAOBMST)
												#else
													#set( $row = "row2" )
													#set ($idPerintahHTAOBMST=$idPerintahHTAOBMST+$listHTA.idPerintahHTAOBMST)
												#end
					                          	<tr>
						                            <td class="$row" align="center">$listHTA.bil</td>
						                            #if($listHTA.idPerintahHTAOBMST == '')
						                            <td class="$row">$listHTA.keteranganHakmilik</td>
						                            #else

						                            <td class="$row">
						                            	<a href="javascript:void(0);" onClick="paparFmPerintahHTAAH('div_keputusan_perintah_htaah', 'fmKeputusanPerintahHtaah', $listHTA.idPerintahHTAOBMST)">
						                            		<font color="#0000FF">$listHTA.keteranganHakmilik</font>
						                            	</a>
						                            </td>

						                            ###<!-- td class="$row"><a href="javascript:paparHTA($listHTA.idPerintahHTAOBMST)"><font color="#0000FF">$listHTA.keteranganHakmilik</font></a></td -->

						                            #end
						                            <td class="$row">$listHTA.jenisPerintah

						                            #if ($listHTA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS" || $listHTA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS  (FARAID)" || $listHTA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS (AKTA 1958)")
						                            	#set ($PPT = "Ada")
						                            #end

						                            #if ($listHTA.jenisPerintah == "PERINTAH  KUASA PENTADBIR" || $listHTA.jenisPerintah == "PERINTAH KUASA PENTADBIR")
						                               	#set ($PKP = "Ada")
						                            #end
						                            </td>
						                            <td class="$row" align="center">$listHTA.kodPB</td>
						                            <td class="$row" align="center">$listHTA.bahagianSimati</td>
					                          	</tr>
				                          	#end
			                        		</table>
				                        #if ($SenaraiHTA.size() > 10)
			                        	</div>
				                      	#end
			                    	</fieldset>
			                    #else
                    			&nbsp;<font color="black">SIMATI TIDAK MEMILIKI SEBARANG HARTA TAK ALIH (ADA HAKMILIK)</font>
                   				#end
                   				</td>
               				</tr>
               			<!-- END LIST HARTA TAK ALIH (ADA HAKMILIK) -->
              			</table>
            		</div>
            		<!-- END CONTENT HARTA TAK ALIH (ADA HAKMILIK) -->

            		<!-- START CONTENT HARTA TAK ALIH (TIADA HAKMILIK) -->
		           	<div class="TabbedPanelsContent">
		            	<div id="div_keputusan_perintah_htath"></div>

	             		<table width="100%" border="0" cellspacing="2" cellpadding="2">

                		<!-- START LIST HARTA TAK ALIH (TIADA HAKMILIK) -->
			                <tr>
		                  		<td>
		                  			#if ($flagAdaHTATH)
                    				<fieldset>
			                      		<legend><strong>SENARAI HARTA TAK ALIH (TIADA HAKMILIK)</strong></legend>
				                      		<table width="100%" border="0" cellspacing="2" cellpadding="2">
						                        <tr class="table_header">
						                          	<td scope="row" width="5%" align="center">BIL</td>
						                          	<td width="55%">KETERANGAN HAKMILIK</td>
						                          	<td width="20%">JENIS PERINTAH</td>
						                          	<td width="10%" align="center">STATUS PEMILIKAN</td>
					                          		<td width="10%" align="center">BAHAGIAN SIMATI</td>
						                        </tr>

						                        #set ($listHTATH= "")

						                        #foreach ($listHTATH in $SenaraiHTATH)

							                        #if ($listHTATH.bil == '')
							                        #set( $row = "row1" )
							                        #elseif (($listHTATH.bil % 2) != 0)
							                        #set( $row = "row1" )
							                        #else
							                        #set( $row = "row2" )
							                        #end

	                        						<tr>
							                          	<td class="$row" align="center">$listHTATH.bil</td>
						                          		#if($listHTATH.idPerintahHTAOBMST == '')
							                          	<td class="$row">$listHTATH.keteranganHakmilik</td>
						                          		#else
														<td class="$row">
							                            	<a href="javascript:void(0);"
							                            		onClick="paparFmPerintahHTATH('div_keputusan_perintah_htath', 'fmKeputusanPerintahHtath', $listHTATH.idPerintahHTAOBMST)">
							                            		<font color="#0000FF">$listHTATH.keteranganHakmilik</font><font color="#000000">$listHTATH.keterangan</font>
							                            	</a>
							                            </td>

							                          		###<!-- td class="$row"><a href="javascript:paparHTATH($listHTATH.idPerintahHTAOBMST)"><font color="#0000FF">$listHTATH.keteranganHakmilik</font><font color="#000000">$listHTATH.keterangan</font></a></td -->

						                          		#end
							                          	<td class="$row">$listHTATH.jenisPerintah</td>
	                      								#if ($listHTATH.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS" || $listHTA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS  (FARAID)" || $listHTA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS (AKTA 1958)")
	                            							#set ($PPT = "Ada")
	                            						#end

	                            						#if (($listHTATH.jenisPerintah == "PERINTAH KUASA PENTADBIR") || ($listHTATH.jenisPerintah == "PERINTAH  KUASA PENTADBIR"))
	                            							#set ($PKP = "Ada")
	                            						#end

						                          		<td class="$row" align="center">$listHTATH.kodPB</td>
						                          		<td class="$row" align="center">$listHTATH.bahagianSimati</td>

	                        						</tr>
                        						#end
					                      	</table>
		                    		</fieldset>
           							#else
               					&nbsp;<font color="black">SIMATI TIDAK MEMILIKI SEBARANG HARTA TAK ALIH (TIADA HAKMILIK)</font> #end
               					</td>
           					</tr>
                		<!-- END LIST HARTA TAK ALIH (TIADA HAKMILIK) -->
   						</table>
            		</div>
            <!-- END CONTENT HARTA TAK ALIH (TIADA HAKMILIK) -->

            <!-- START CONTENT HARTA ALIH -->
		            <div class="TabbedPanelsContent">
		            	<div id="div_keputusan_perintah_ha"></div>

          				<table width="100%" border="0" cellspacing="2" cellpadding="2">

                <!-- START LIST HARTA ALIH -->
			                <tr>
			                	<td>
				                	#if ($flagAdaHA)
				                    <fieldset>
				               			<legend><strong>SENARAI HARTA ALIH </strong></legend>
				                      	#if ($SenaraiHA.size() > 10)
				                      	<div style="width:100%;height:215;overflow:auto">
				                      	#end

					                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
					                          	<tr>
					                            	<td colspan="5">
				                            		#if ($mode == 'update')
				                              		#if ($flagPopup == '')
				                               		#if ($flagSelesaiHA == '' && $userRole != "user_ppk")
				                              		<input name="cmdTambahHA" id="cmdTambahHA" value="Daftar Perintah" type="button" onClick="javascript:tambahHA()">
				                              		#end
				                              		#end
				                              		#end
					                              	</td>
					                          	</tr>
					                          	<tr class="table_header">
						                            <td scope="row" width="5%" align="center">BIL</td>
						                            <td width="40%">JENIS HARTA ALIH</td>
						                            <td width="30%">JENIS PERINTAH</td>
						                            <td width="25%" align="right">NILAI TARIKH MOHON (RM)</td>
					                          	</tr>

					                          	#set ($listHA = "")

					                          	#foreach ($listHA in $SenaraiHA)
						                          	#if ($listHA.bil == '')
						                          	#set( $row = "row1" )
						                          	#elseif (($listHA.bil % 2) != 0)
						                          	#set( $row = "row1" )
						                          	#else
						                          	#set( $row = "row2" )
						                          	#end
					                          	<tr>
					                            	<td class="$row" align="center">$listHA.bil</td>
					                           		#if($listHA.idPerintahHAOBMST == '')
					                            	<td class="$row">$listHA.jenisHarta</td>
					                            	#else

														<td class="$row">
							                            	<a href="javascript:void(0);"
							                            		onClick="paparFmPerintahHA('div_keputusan_perintah_ha', 'fmKeputusanPerintahHa', $listHA.idPerintahHAOBMST)">
							                            		<font color="#0000FF">$listHA.jenisHarta</font><font color="#000000">$listHA.keterangan</font>
							                            	</a>
							                            </td>


					                            	###<!-- td class="$row"><a href="javascript:paparHA($listHA.idPerintahHAOBMST)"><font color="#0000FF">$listHA.jenisHarta</font><font color="#000000">$listHA.keterangan</font></a></td -->

					                            	#end
					                            	<td class="$row">$listHA.jenisPerintah

					                            	#if ($listHA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS" || $listHTA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS  (FARAID)" || $listHTA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS (AKTA 1958)")
					                            	#set ($PPT = "Ada")
					                            	#end
					                            	#if ($listHA.jenisPerintah == "PERINTAH  KUASA PENTADBIR")
					                            	#set ($PKP = "Ada")
						                            #end
					                            	</td>
					                            	<td class="$row" align="right">$listHA.nilaiTarikhMohon</td>
					                          	</tr>
					                          	#end
					                        </table>
				                        #if ($SenaraiHA.size() > 5)
				                        </div>
				                      	#end
				                    </fieldset>
				                    #else
				                    &nbsp;<font color="black">SIMATI TIDAK MEMILIKI SEBARANG HARTA ALIH</font>
				                    #end
						     	</td>
			       			</tr>
			                <!-- END LIST HARTA ALIH -->
			           	</table>
					</div>
            <!-- START CONTENT HARTA ALIH -->

					#if ($idStatus == 21)
		            <div class="TabbedPanelsContent">
		              	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		                 <!-- SALNIZAM EDIT START TAMBAH LAPORAN PENGHANTARAN PERINTAH -->
				            <tr>
				            	<td>
				           	 		<!-- <a name="tabLowest"></a>  -->
						            <a name="hantarperintah"></a>
						            <fieldset>
				            			<legend><strong>PENGHANTARAN PERINTAH</strong></legend>
				            			<fieldset>
                      						<legend><strong>MAKLUMAT SERAHAN</strong></legend>
				                      		<table width="100%" border="0">
	                                      		<tr>
		                                     		#set ($sta="sta18")

								              		<td width="20%" >
								              			<div align="right">#if($chkmode!="disabled")<font color="red">*</font>#end&nbsp;Nama Penyerah</div>
							              			</td>
									              	<td width="3%"><div align="right">:</div></td>
									              	<td></td>

									              	#if ($chkmode == "disabled")
									              	<td width="77%"><input size = "50" type="text" $chkmode name="txtNamaPenghantarNotis" id="txtNamaPenghantarNotis" value="$!NAMA_PENYERAH"></td>

	               									#else
									                <td width="77%">
									                #if($NAMA_PENYERAH !="")
									                	<input size = "50" type="text" name="txtNamaPenghantarNotis" id="txtNamaPenghantarNotis" value="$!NAMA_PENYERAH">
									                #else
									                	<input size = "50" type="text" name="txtNamaPenghantarNotis" id="txtNamaPenghantarNotis" value="">
									                #end
                <!-- User tak mahu tarikh nama penyerah daripada database
                <select name="txtNamaPenghantarNotis" style="width:320">
        										#if($onchangeMyList=="no" && $NAMA !="")

        		<option value="$ID_PENGHANTAR_NOTIS">$KOD_PENGHANTAR_NOTIS - $NAMA</option>
                  //<option value="">SILA PILIH1&nbsp;</option>
        											#foreach($pn in $penghantarNotis)
        											#if($pn.kod_penghantar_notis!=$KOD_PENGHANTAR_NOTIS)
                  <option value="$pn.id_penghantarnotis">$pn.kod_penghantar_notis - $pn.nama.toUpperCase()</option>
                  									#end
	                    							#end
	                    						#else

                  <option value="">SILA PILIH&nbsp;</option>
                 									#foreach($pnoc in $penghantarNotis)

                  <option value="$pnoc.id_penghantarnotis">$pnoc.kod_penghantar_notis - $pnoc.nama.toUpperCase()</option>
	                    							#end
	                    						#end
                </select>
                User tak mahu tarikh nama penyerah daripada database -->
									                </td>
									               <!-- $ID_PENGHANTAR_NOTIS -  -->
									               #end
              <!-- <td width="73%"><input type="text" $disableZERO $classZero name="txtNamaPenghantarNotis" $check $checkClass value="$namaPenghantar" maxlength="80" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td> -->
           										/tr>
						                      	<tr>
						                      		<td width="20%" >
							                      		<div align="right">
							                      		#if($chkmode!="disabled")<font color="red">*</font>#end
							                      		&nbsp;Jenis Serahan
							                      		</div>
						                      			<input type="hidden" name="no_fail" value="$noFail">
						                      		</td>
						                      		<td width = "3%">
						                      			<div align="right"> : </div>
						                      		</td>
						                      		<td>
						                      			<input type="radio" name="radioJenis" id="radioJenis" $chkmode onClick="checkIt1()" #if ($Jenis_Penghantaran == "Serahan Tangan") checked #end value="Serahan Tangan">
						                      		</td>
						                      		<td width ="77%">
						                      			<div align="left">
						                      				Serahan Tangan
						                      			</div>
						                      		</td>
						                      	</tr>
												<tr>
                      								<td width="20%" >
                      									<div align="right"></div>
                      								</td>
                      								<td width = "3%"><div align="right"></div>
                      								</td>
							                      	<td>
							                      		<input type="radio" name="radioJenis" id="radioJenis" $chkmode #if ($Jenis_Penghantaran == "Pos") checked #end value="Pos">
							                      	</td>
							                      	<td width ="77%">
							                      		<div align="left">Pos</div>
							                      	</td>
                      </tr>

                      <tr>
                      <td width="20%" ><div align="right">#if($chkmode!="disabled")<font color="red">*</font>#end
                      &nbsp;Tarikh Serahan
                      </div></td>
                      <td width = "3%"><div align="right">
                      :
                      </div></td>
                      <td >

                      </td>

                      <td >

                      <input type="text" $chkmode name="txtTarikh" id="txtTarikh" style="text-transform:uppercase;" #if ($Tarikh_serahan != "") value=$Tarikh_serahan #else value="" #end size="15" maxlength="15" >
                                            #if($chkmode != "disabled")
                       <a href="javascript:displayDatePicker('txtTarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
                       #end

                      </td>
                      <td>
                      </td>
                      </tr>

                      <!-- Status diterima atau dikembalikan tidak perlu. Start.
                     <tr>
                      <td width="20%" ><div align="right">

                      </div></td>
                      <td width = "3%"><div align="right">

                      </div></td>
                      <td >

                      </td>
                      <td width ="77%"><div align="left">
                      <input type="radio" name="radioPos" id="radioPos" onClick="checkIt()" $chkmode #if ($Jenis_Penghantaran == "Pos-Diterima") checked #end value="Pos_Diterima">
                      Diterima
                      </div></td>
                      </tr>

                                           <tr>
                      <td width="20%" ><div align="right">

                      </div></td>
                      <td width = "3%"><div align="right">

                      </div></td>
                      <td >

                      </td>
                      <td width ="77%"><div align="left">
                      <input type="radio" name="radioPos" id="radioPos" onClick="checkIt()" $chkmode #if ($Jenis_Penghantaran == "Pos-Dikembalikan") checked #end value="Pos_Dikembalikan">
                      Dikembalikan
                      </div></td>
                      </tr>
                      Status diterima atau dikembalikan tidak perlu. End. -->
                      </table>
                      </fieldset>
           <fieldset>
                      <legend><strong>MAKLUMAT PENERIMAAN</strong></legend>
                      <table width ="100%" border ="0">

                      <tr>
                      <td width="20%" ><div align="right">#if($chkmode!="disabled")<font color="red">*</font>#end
                      &nbsp;Nama Penerima
                      </div></td>
                      <td width = "3%"><div align="right">
                      :
                      </div></td>
                	<td>#if($chkmode!="disabled")
                   		 <select id="myList" name="myList" onchange="onchangemyList()" style="width:140">

                  			<option value="">SILA PILIH&nbsp;</option>
								#foreach($listOB in $listOBatas18)
                 			<option value="$listOB.id_ob">$listOB.nama_ob.toUpperCase()</option>
                    			#end
                    			#foreach($listPentingPeguam in $listPentingPeguam)

                    			<option value="peguam$listPentingPeguam.namaFirma.toUpperCase()">$listPentingPeguam.namaFirma.toUpperCase()</option>
                    			#end
                    		#if ($selectionBox == "Lain-lain")
                   		 		<option value="Lain-lain" selected="selected">LAIN-LAIN</option>
                   		 	#else
                    			<option value="Lain-lain">LAIN-LAIN</option>
                    		#end

               			</select>
                   		&nbsp;#end
                    	<input type="text" id="namaPenerima" $chkmode $disableZERO $classZero $checkClass name="txtNamaPenerima" value="$!Nama_Penerima" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
               		</td>
                      <td>
                      </td>
                      </tr>
                      <tr>
                      <td width="20%" ><div align="right">
                      No. K/P Penerima
                      </div></td>
                      <td width = "3%"><div align="right">
                      :
                      </div></td>

                      <td>
                      #if($jeniskp=="baru")

                    <input type="text" $chkmode $checkClass $disableZERO $classZero size="6" name="txtNoKPBaru1" id="txtNoKPBaru1" value="$!kp1" maxlength="6"  />
                    -
                    <input name="txtNoKPBaru2"  $chkmode $disableZERO $checkClass $classZero id="txtNoKPBaru2" type="text" value="$!kp2"  size="1" maxlength="2" />
                    -
                    <input name="txtNoKPBaru3" $chkmode $checkClass $disableZERO $classZero id="txtNoKPBaru3"  type="text" value="$!kp3" size="3" maxlength="4"  />
                    <input type="hidden" name="txtNoKPLain" id="txtNoKPLain" value="">
                    <input type="hidden" name="txtNoKPLama" id="txtNoKPLama" value="">
                    #set($No_Kp_Penerima = $kp1 + $kp2 + $kp3)


                    #elseif($jeniskp=="lama")
                    <input type="text" $chkmode $checkClass $disableZERO $classZero size="11" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" name="txtNoKPLama" value="$!kplama" maxlength="10" onkeyup="javascript:validateNumber()" />
                    <input type="hidden" name="txtNoKPBaru1" id="txtNoKPBaru1" value="">
                    <input type="hidden" name="txtNoKPBaru2" id="txtNoKPBaru2" value="">
                    <input type="hidden" name="txtNoKPBaru3" id="txtNoKPBaru3" value="">
                    <input type="hidden" name="txtNoKPLain" id="txtNoKPLain" value="">
                    #set($No_Kp_Penerima = $kplama)
                    #elseif($jeniskp=="lain")

                    <input type="text" $chkmode $checkClass $disableZERO $classZero size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" name="txtNoKPLain" value="$kplain" maxlength="20" onkeyup="javascript:validateNumber()" />
                    <input type="hidden" name="txtNoKPBaru1" id="txtNoKPBaru1" value="">
                    <input type="hidden" name="txtNoKPBaru2" id="txtNoKPBaru2" value="">
                    <input type="hidden" name="txtNoKPBaru3" id="txtNoKPBaru3" value="">
                    <input type="hidden" name="txtNoKPLama" id="txtNoKPLama" value="">
                    #set($No_Kp_Penerima = $kplain)
                    #else

                    <input type="text" $chkmode $checkClass $disableZERO $classZero size="6" name="txtNoKPBaru1" id="txtNoKPBaru1" value="$!kp1" maxlength="6"   />
                    -
                    <input name="txtNoKPBaru2"  $chkmode $disableZERO $checkClass $classZero id="txtNoKPBaru2" type="text" value="$!kp2"  size="1" maxlength="2" />
                    -
                    <input name="txtNoKPBaru3" $chkmode $checkClass $disableZERO $classZero id="txtNoKPBaru3"  type="text" value="$!kp3" size="3" maxlength="4"  />
                    <input type="hidden" name="txtNoKPLain" id="txtNoKPLain" value="">
                    <input type="hidden" name="txtNoKPLama" id="txtNoKPLama" value="">
                    #set($No_Kp_Penerima = $kp1 + "-" + $kp2 + "-" + $kp3)
                    #end
                      </td>
                      </tr>
                      <tr>
                      <td width="20%" ><div align="right">
                      Catatan
                      </div></td>
                      <td width = "3%"><div align="right">
                      :
                      </div></td>
                      <td >

                      #set ($readonly = $chkmode)
                      #if ($readonly == "disabled")
                      	#set ($readonly = 1)
                      #end
                      #if ($Catatan == "")
                     	   #set ($Catatan = "")
                      #else
                           #set ($Catatan = $Catatan)
                      #end
                      #if ($readonly == "1")
                      <textarea rows="4" cols="50" name="txtCatatan" disabled>$Catatan</textarea>
                      #else
                      <textarea rows="4" cols="50" name="txtCatatan">$Catatan</textarea>
                      #end
                      </td>
                      <td>
                      </td>
                      </tr>

                      </table>
                      </fieldset>
                      <table width ="100%" border ="0">
                      <tr><td width="100%"><div align="center">

                      #if ($SimpanStatus == '2')
                      		<input type="button" name="cmdPrint" id="cmdPrint" value="Cetak Laporan Serahan" onClick="PrintLaporan('$NAMA_PENYERAH','$Jenis_Penghantaran','$Tarikh_serahan',document.getElementById('namaPenerima').value,'$No_Kp_Penerima','$Catatan','$noFail')"/>
                       		<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini()"/>

                       #else
                       		<input type="button" name="cmdPrint" id="cmdPrint" disabled value="Cetak Laporan Serahan" onClick="PrintLaporan('$namaPenghantar','$Jenis_Penghantaran','$Tarikh_serahan',document.getElementById('namaPenerima').value,'$No_Kp_Penerima','$Catatan','$noFail')"/>
                       		<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="Batal()"/>
                       	<!-- 	<input type="button" name="cmdKemaskini" disabled id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini()"/> -->

                       #end

                      <input type="button" name="cmdSimpan" id="cmdSimpan" $chkmode value="Simpan" onClick="DoTheCheck()"/>
           				</div></td></tr>
           				</table>
            </fieldset>
            </td>
            </tr>
            <tr><td>
            </td></tr>
            <!-- SALNIZAM EDIT END TAMBAH LAPORAN PENGHANTARAN PERINTAH -->

              </table>
            </div>
            #end







          </div>
        </div>
      </fieldset>
	</td>
</tr>
  <!-- END PERINTAH PEMBAHAGIAN -->
  ####end

  ####if($flagPopup == '' || $flagPopup == 'openHTAPT' || $flagPopup == 'openHAPT'  || $flagPopup == 'openHTAPKT' || $flagPopup == 'openHAPKT'|| $flagPopup == 'openHTAPL' || $flagPopup == 'openHAPL'  || $flagPopup == 'openHTAPF' || $flagPopup == 'openHAPF' || $flagPopup == 'openHTAPA1958' || $flagPopup == 'openHAPA1958')
<tr>
	<td>&nbsp;</td>
</tr>
		<!-- START PEMBAHAGIAN HARTA -->
<tr>
   	<td>
   		<a name="tabLower"></a>
		<fieldset>
        	<legend><strong> PEMBAHAGIAN HARTA</strong></legend>
			#parse("app/ppk/BicaraInteraktif/pembahagian_harta.jsp")
      	</fieldset>
	</td>
</tr>
</table>

<!-- END PEMBAHAGIAN HARTA -->
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});

var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabLower});
</script>