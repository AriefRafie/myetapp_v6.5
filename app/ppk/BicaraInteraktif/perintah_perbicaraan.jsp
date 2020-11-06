<table width='100%' align='center' border='0' cellspacing='1' cellpadding='2' class='classFade'>
	<tr>
		<td width='1%'></td><td width='28%'></td><td width='1%'></td><td width='70%'></td>
	</tr>
	<tr>
		<td colspan='4' class='table_header'>Perintah Perbicaraan</td>
	</tr>
</table>

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
		            <li onClick="javascript:setSelectedTabUpper(0);" class="TabbedPanelsTab" tabindex="0">HARTA TAK ALIH (ADA HAKMILIK)</li>
		            <li onClick="javascript:setSelectedTabUpper(1);" class="TabbedPanelsTab" tabindex="0">HARTA TAK ALIH (TIADA HAKMILIK)</li>
		            <li onClick="javascript:setSelectedTabUpper(2);" class="TabbedPanelsTab" tabindex="0">HARTA ALIH</li>
		            #if ($idStatus == 21)
		           		<li onClick="javascript:setSelectedTabUpper(3);" class="TabbedPanelsTab" tabindex="0">PENGHANTARAN PERINTAH</li>
		            #end
	          	</ul>
          		<div class="TabbedPanelsContentGroup">

		            <!-- START CONTENT HARTA TAK ALIH (ADA HAKMILIK) -->
		            <div class="TabbedPanelsContent">
	              		<table width="100%" border="0" cellspacing="2" cellpadding="2">

		                <!-- START LIST HARTA TAK ALIH (ADA HAKMILIK) -->
		                	<tr>
                  				<td>
                  					#if ($flagAdaHTA)
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
						                            <td class="$row"><a href="javascript:paparHTA($listHTA.idPerintahHTAOBMST)"><font color="#0000FF">$listHTA.keteranganHakmilik</font></a></td>
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
                    			&nbsp;<font color="black">SIMATI TIDAK MEMILIKI SEBARANG HARTA TAK ALIH (ADA HAKMILIK)</font> #end </td>
               				</tr>
               			<!-- END LIST HARTA TAK ALIH (ADA HAKMILIK) -->
              			</table>
            		</div>
            		<!-- END CONTENT HARTA TAK ALIH (ADA HAKMILIK) -->

            		<!-- START CONTENT HARTA TAK ALIH (TIADA HAKMILIK) -->
		           	<div class="TabbedPanelsContent">
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
							                          	<td class="$row"><a href="javascript:paparHTATH($listHTATH.idPerintahHTAOBMST)"><font color="#0000FF">$listHTATH.keteranganHakmilik</font><font color="#000000">$listHTATH.keterangan</font></a></td>
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
					                            	<td class="$row"><a href="javascript:paparHA($listHA.idPerintahHAOBMST)"><font color="#0000FF">$listHA.jenisHarta</font><font color="#000000">$listHA.keterangan</font></a></td>
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
      </fieldset></td>
  </tr>
  <!-- END PERINTAH PEMBAHAGIAN -->
  ####end

  ####if($flagPopup == '' || $flagPopup == 'openHTAPT' || $flagPopup == 'openHAPT'  || $flagPopup == 'openHTAPKT' || $flagPopup == 'openHAPKT'|| $flagPopup == 'openHTAPL' || $flagPopup == 'openHAPL'  || $flagPopup == 'openHTAPF' || $flagPopup == 'openHAPF' || $flagPopup == 'openHTAPA1958' || $flagPopup == 'openHAPA1958')
		<tr>
			<td>&nbsp;</td>
		</tr>
		<!-- START PEMBAHAGIAN HARTA -->
		<tr>
    		<td><a name="tabLower"></a>
      			<fieldset>
        			<legend><strong> PEMBAHAGIAN HARTA</strong></legend>

			        <div id="TabbedPanels2" class="TabbedPanels">
						<ul class="TabbedPanelsTabGroup">
				            <li onClick="javascript:setSelectedTabLower(0);" class="TabbedPanelsTab" tabindex="0">PERINTAH TERUS</li>
				            <li onClick="javascript:setSelectedTabLower(1);" class="TabbedPanelsTab" tabindex="0">PERINTAH KUASA TADBIR</li>
				            <li onClick="javascript:setSelectedTabLower(2);" class="TabbedPanelsTab" tabindex="0">PERINTAH LELONG</li>
				            <li onClick="javascript:setSelectedTabLower(3);" class="TabbedPanelsTab" tabindex="0">PERINTAH FARAID</li>
				            <li onClick="javascript:setSelectedTabLower(4);" class="TabbedPanelsTab" tabindex="0">PERINTAH PEMBAHAGIAN AKTA 1958</li><!-- arief add -->
						</ul>
          				<div class="TabbedPanelsContentGroup">
			            <!-- START CONTENT PERINTAH TERUS -->
			            <div class="TabbedPanelsContent">
		              		<table width="100%" border="0" cellspacing="2" cellpadding="2">


                #if ($flagPopup != 'openHAPT')
                <!-- START LIST HTAPT -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>
                      #if($flagAdaHTAPT == '1')

                      #if ($SenaraiHTAPT.size() > 10)
                      <div style="width:100%;height:210;overflow:auto"> #end
                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="65%">KETERANGAN HAKMILIK</td>
                            <td width="15%" align="center">STATUS PEMILIKAN</td>
                            <td width="15%" align="center">JENIS TANAH</td>
                          </tr>
                          #set ($listHTAPT = "")
                          #foreach ($listHTAPT in $SenaraiHTAPT)
                          #if ($listHTAPT.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHTAPT.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHTAPT.bil</td>
                            #if($listHTAPT.idPerintahHTAOBMST == '')
                            <td class="$row">$listHTAPT.keteranganHakmilik</td>
                            #else
                            <td class="$row"><a href="javascript:paparHTAPT('$listHTAPT.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPT.keteranganHakmilik</font></a></td>
                            <td class="$row" align="center">$listHTAPT.kodPB</td>
                            <td class="$row" align="center">$listHTAPT.jenisTanah</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHTAPT.size() > 10) </div>
                      #end

                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HTAPT -->
                #end
                <!-- START OPEN POPUP HAPT -->
                #if ($flagPopup == 'openHAPT')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <input name="idJenisTanah" type="hidden">
                      <tr>
                        <td width="30%" align="right">JENIS HARTA ALIH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisHA</font><font color="#000000">$headerDetail.keterangan</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENAMA/MODEL/BANK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenama</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">NO. PENDAFTARAN/AKAUN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.noDaftar</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
                       <input type="hidden" name="bahagianSimatiAtas" value="$headerDetail.bahagianSimatiAtas" class="$inputTextClass">
                        <input type="hidden" name="bahagianSimatiBawah" value="$headerDetail.bahagianSimatiBawah" class="$inputTextClass">
                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"> #if ($SenaraiHAPTDTL.size() > 10)
                          <div style="width:100%;height:225;overflow:auto"> #set($sizeUp="100%")
                            #set($alignUp="left")
                            #set($sizeDown="99%")
                            #set($alignDown="left")
                            #else
                            #set($sizeUp="95%")
                            #set($alignUp="center")
                            #set($sizeDown="95%")
                            #set($alignDown="center")
                            #end
                            <table width="$sizeUp" border="0" cellspacing="2" cellpadding="2" align="$alignUp">
                              <tr>
                                <td colspan="4" align="right"><div id="calculateTotal_result" style="color:#FF0000;font-weight:bold"></div></td>
                              </tr>
                              <tr class="table_header">
                                <td align="center" width="5%">BIL</td>
                                <td width="40%">NAMA WARIS</td>
                                <td width="20%">STATUS WARIS</td>
                                <td align="center" width="35%">BAHAGIAN WARIS</td>
                              </tr>
                              #set ($listHAPTDTL = "")
                              #set ($cnt = 0)
                              #foreach ($listHAPTDTL in $SenaraiHAPTDTL)
                              #set ($cnt = $cnt+1)
                              <tr>
                                <input name="idspentadbir" type="hidden" value="$listHAPTDTL.idOB">
                                <input name="status" type="hidden" value="$listHAPTDTL.status">
                                <td align="center">$listHAPTDTL.bil</td>
                                #if ($listHAPTDTL.status == '')
                                #if ($listHAPTDTL.statusHidup == '1')
                                <td>$listHAPTDTL.namaWaris &nbsp;&nbsp;<span class="style1"><strong>(MENINGGAL DUNIA)</strong></span></td>
                                #else
                                <td>$listHAPTDTL.namaWaris</td>
                                #end
                                #else
                                <td><a href="javascript:updatePAPTHAPT('$listHAPTDTL.idOB','$idPermohonanSimati','$idSimati','$listHAPTDTL.status','$idPerintah','$mode','$idHA','$flag_kemaskini_selesai','$idFail')"><font color="#0000FF">$listHAPTDTL.namaWaris</font></a></td>
                                #end
                                <td>$listHAPTDTL.status</td>
                                <!-- Harta Tak Alih -->
                                <td align="center"><input name="txtBA" type="text" size="15" maxlength="15" style="text-align:center" value="$listHAPTDTL.BA" onBlur="validateBahagianWaris(this,this.value,$listHAPTDTL.BA);calculateTotal();" $readonly class="$inputTextClass">
                                  /
                                  <input name="txtBB" type="text" size="15" maxlength="15" style="text-align:center" value="$listHAPTDTL.BB" onBlur="validateBahagianWaris(this,this.value,$listHAPTDTL.BB);calculateTotal();" $readonly class="$inputTextClass"></td>
                              </tr>
                              #end
                            </table>
                            #if ($SenaraiHAPTDTL.size() > 10) </div>
                          #else <br/>
                          #end
                          <table width="$sizeDown" border="0" cellspacing="2" cellpadding="2" align="$alignDown">
                            <!-- COMMENT BY PEJE - TIDAK DIPERLUKAN LAGI DAH
                          <tr>
                            <td colspan="4"><hr color="#000000"></td>
                          </tr>
                          <tr>
                            <td align="center" width="5%"><input type="checkbox" name="chkWarisHilang" id="chkWarisHilang" $checked onClick="test();calculateTotal();" value="1" $disabled>
                            </td>
                            <td width="45%">WARIS TIDAK DAPAT DIKESAN &nbsp;
                              <input type="button" name="cmdLantikPT" id="cmdLantikPT" value="Lantik Pentadbir" onClick="javascript:updatePAPTHAPT('','$idPermohonanSimati','$idSimati','HILANG','$idPerintah','$mode','$idHA','$flag_kemaskini_selesai')" $disabledHilang/>
                            </td>
                            <td width="15%">HILANG</td>
                            <td align="center" width="35%"><input name="txtBAHilang" type="text" size="14" maxlength="14" style="text-align:center" value="$BAHilang" onBlur="validateBahagianWaris(this,this.value,$BAHilang);calculateTotal();" $disabledHilang $readonly class="$inputTextClass">
                              /
                              <input name="txtBBHilang" type="text" size="14" maxlength="14" style="text-align:center" value="$BBHilang" onBlur="validateBahagianWaris(this,this.value,$BBHilang);calculateTotal();" $disabledHilang $readonly class="$inputTextClass">
                            </td>
                          </tr>
                          -->
                            <tr class="table_header">
                              <td width="5%">&nbsp;</td>
                              <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                              <td align="center" width="35%"><input name="txtJumlahBA" id="txtJumlahBA" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBA" readonly class="disabled">
                                /
                                <input name="txtJumlahBB" id="txtJumlahBB" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBB" readonly class="disabled"></td>
                            </tr>
                            <tr>
                              <td colspan="3"><i><font color="#ff0000">Perhatian</font> : Sila simpan pembahagian harta terlebih dahulu dan klik pada nama waris untuk melantik pemegang amanah / pentadbir bagi waris yang menerima bahagian.</i></td>
                              <td align="center"> #if ($mode == 'update')
                                <input type="button" name="cmdSamakanPembawah1" id="cmdSamakanPembawah1" value="Samakan Pembawah" onClick="javascript:samakanPembawah()"/>
                                #if($flag_kemaskini_selesai != "yes")
                                <script>
                                        document.getElementById('cmdSamakanPembawah1').style.display = "none";
                                        </script>
                                #end
                                #end </td>
                            </tr>
                            <tr>
                              <td valign="bottom" colspan="4">&nbsp;</td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2" align="center"> #if ($mode == 'update')
                          <input type="button" name="cmdSimpanHAPT1" id="cmdSimpanHAPT1" value="Simpan" onClick="javascript:simpanKemaskiniHAPT()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdSimpanHAPT1').style.display = "none";
                                        </script>
                          #end
                          <input type="button" name="cmdPembahagianRata1" id="cmdPembahagianRata1" value="Pembahagian Separa" onClick="javascript:pembahagianSepara()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdPembahagianRata1').style.display = "none";
                                        </script>
                          #end
                          <input type="button" name="cmdKosongkanPembahagian1" id="cmdKosongkanPembahagian1" value="Kosongkan" onClick="javascript:kosongkanPembahagian()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdKosongkanPembahagian1').style.display = "none";
                                        </script>
                          #end
                          <input type="button" name="cmdPembahagianHartaLain" id="cmdPembahagianHartaLain" value="Pembahagian Harta Lain" onClick="javascript:pembahagianHartaLainHA('$idPerintah','$idHA')"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdPembahagianHartaLain').style.display = "none";
                                        </script>
                          #end
                          <input type="button" name="cmdBatalHAPT" id="cmdBatalHAPT" value="Kembali" onClick="javascript:batalHAPT()"/>
                          #end
                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHAPT" id="cmdBatalHAPT" value="Kembali" onClick="javascript:batalHAPT()"/>
                          #end </td>
                      </tr>
                    </table></td>
                </tr>
                #end
                <!-- END OPEN POPUP HAPT -->
                #if ($flagPopup != 'openHTAPT')
                <!-- START LIST HAPT -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>
                      #if($flagAdaHAPT == '1')

                      #if ($SenaraiHAPT.size() > 10)
                      <div style="width:100%;height:190;overflow:auto"> #end
                        <table align="center" width="100%">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="35%">JENIS HARTA ALIH</td>
                            <td width="30%">JENAMA/MODEL/BANK</td>
                            <td width="30%">NO. PENDAFTARAN/AKAUN</td>
                          </tr>
                          #set ($listHAPT = "")
                          #foreach ($listHAPT in $SenaraiHAPT)
                          #if ($listHAPT.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHAPT.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHAPT.bil</td>
                            #if($listHAPT.idPerintahHAOBMST == '')
                            <td class="$row">$listHAPT.jenisHA</td>
                            #else
                            <td class="$row"><a href="javascript:paparHAPT('$listHAPT.idPerintahHAOBMST')"><font color="#0000FF">$listHAPT.jenisHA.toUpperCase()</font><font color="#000000">$listHAPT.keterangan</font></a></td>
                            <td class="$row">$listHAPT.jenama.toUpperCase()</td>
                            <td class="$row">$listHAPT.noDaftar.toUpperCase()</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHAPT.size() > 10) </div>
                      #end

                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HAPT -->
                #end

              </table>
            </div>
            <!-- END CONTENT PERINTAH TERUS -->
            <!-- START CONTENT PERINTAH KUASA TADBIR -->




            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <!-- START OPEN POPUP HTAPKT -->
                #if ($flagPopup == 'openHTAPKT')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <input name="idJenisTanah" type="hidden" value="$headerDetail.idJenisTanah">
                      <tr>
                        <td width="30%" align="right">KETERANGAN HAKMILIK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.keteranganHakmilik</font></td>
                      </tr>
                      #if ($headerDetail.kategoriHarta == '1')
                      <tr>
                        <td width="30%" align="right">ALAMAT HARTA :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.keterangan</font></td>
                      </tr>
                      #end
                      #if ($headerDetail.kategoriHarta == '2')
                      <tr>
                        <td width="30%" align="right">NO ROH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.keterangan</font></td>
                      </tr>
                      #end
                      #if ($headerDetail.kategoriHarta == '3')
                      <tr>
                        <td width="30%" align="right">JENIS KEPENTINGAN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.keterangan</font></td>
                      </tr>
                      #end
                      <tr>
                        <td width="30%" align="right">STATUS PEMILIKAN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisPB</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENIS TANAH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisTanah</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"><table width="60%" border="0" cellspacing="2" cellpadding="2" align="center">
                            <tr>
                              <td> #if ($ListHTAPKTDTL.size() > 5)
                                <div style="width:100%;height:133;overflow:auto"> #end
                                  <table align="center" width="100%" border="0">
                                    <tr class="table_header"> #if ($listSize != '0')
                                      <td scope="row" width="1%">&nbsp;</td>
                                      #end
                                      <td scope="row" width="4%" align="center">BIL</td>
                                      <td width="95%">NAMA PENTADBIR</td>
                                    </tr>
                                    #set ($list = "")
                                    #foreach ($list in $ListHTAPKTDTL)
                                    #if ($list.bil == '')
                                    #set( $row = "row1" )
                                    #elseif (($list.bil % 2) != 0)
                                    #set( $row = "row1" )
                                    #else
                                    #set( $row = "row2" )
                                    #end
                                    <tr> #if ($listSize != '0')
                                      #if($list.flag == 'Y')
                                      #set($checked = 'checked')
                                      #else
                                      #set($checked = '')
                                      #end
                                      <td class="$row"><input type="checkbox" value="$list.idOB" name="idsOB" $checked $disabled onClick="doUpdateCheck('$list.bil')"/></td>
                                      #end
                                      <td class="$row" align="center">$list.bil</td>
                                      #if ($list.statusHidup == '1')
                                      <td class="$row">$list.namaOB &nbsp;&nbsp;<span class="style1"><strong>(MENINGGAL DUNIA)</strong></span></td>
                                      #else
                                      <td class="$row">$list.namaOB</td>
                                      #end </tr>
                                    #end
                                  </table>
                                  #if ($ListHTAPKTDTL.size() > 5) </div>
                                #end </td>
                            </tr>
                            <tr>
                              <td>&nbsp;</td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td colspan="2">&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2">&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"> #if ($SenaraiPembahagianHTAPKTDTL.size() > 10)
                          <div style="width:100%;height:225;overflow:auto"> #set($sizeUp="100%")
                            #set($alignUp="left")
                            #set($sizeDown="99%")
                            #set($alignDown="left")
                            #else
                            #set($sizeUp="95%")
                            #set($alignUp="center")
                            #set($sizeDown="95%")
                            #set($alignDown="center")
                            #end
                            <table width="$sizeUp" border="0" cellspacing="2" cellpadding="2" align="$alignUp">
                              <tr>
                                <td colspan="4" align="right"><div id="calculateTotal_result" style="color:#FF0000;font-weight:bold"></div></td>
                              </tr>
                              <tr class="table_header">
                                <td colspan="3"><strong>PEMBAHAGIAN HARTA</strong></td>
                              </tr>
                              <tr class="table_header">
                                <td align="center" width="5%">BIL</td>
                                <td width="60%">NAMA WARIS</td>
                                <td align="center" width="35%">BAHAGIAN WARIS</td>
                              </tr>
                              #set ($listPembahagianHTAPKTDTL = "")
                              #set ($cnt = 0)
                              #foreach ($listPembahagianHTAPKTDTL in $SenaraiPembahagianHTAPKTDTL)
                              #set ($cnt = $cnt+1)
                              <tr>
                                <input name="idspentadbir" type="hidden" value="$listPembahagianHTAPKTDTL.idOB">
                                <input name="status" type="hidden" value="$listPembahagianHTAPKTDTL.status">
                                <td align="center">$listPembahagianHTAPKTDTL.bil</td>
                                #if ($listPembahagianHTAPKTDTL.statusHidup == '1')
                                <td>$listPembahagianHTAPKTDTL.namaWaris &nbsp;&nbsp;<span class="style1"><strong>(MENINGGAL DUNIA)</strong></span></td>
                                #else
                                <td>$listPembahagianHTAPKTDTL.namaWaris</td>
                                #end
                                <td align="center"><input name="txtBA" type="text" size="15" maxlength="15" style="text-align:center" value="$listPembahagianHTAPKTDTL.BA" onBlur="validateBahagianWaris(this,this.value,$listPembahagianHTAPKTDTL.BA);calculateTotal();" $readonly class="$inputTextClass">
                                  /
                                  <input name="txtBB" type="text" size="15" maxlength="15" style="text-align:center" value="$listPembahagianHTAPKTDTL.BB" onBlur="validateBahagianWaris(this,this.value,$listPembahagianHTAPKTDTL.BB);calculateTotal();" $readonly class="$inputTextClass"></td>
                              </tr>
                              #end
                            </table>
                            #if ($SenaraiPembahagianHTAPKTDTL.size() > 10) </div>
                          #else <br/>
                          #end
                          <table width="$sizeDown" border="0" cellspacing="2" cellpadding="2" align="$alignDown">
                            <tr class="table_header">
                              <td width="5%">&nbsp;</td>
                              <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                              <td align="center" width="35%"><input name="txtJumlahBA" id="txtJumlahBA" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBA" readonly class="disabled">
                                /
                                <input name="txtJumlahBB" id="txtJumlahBB" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBB" readonly class="disabled"></td>
                            </tr>
                            <tr>
                              <td colspan="3">&nbsp;</td>
                              <td align="center"> #if ($mode == 'update')
                                <input type="button" name="cmdSamakanPembawah" id="cmdSamakanPembawah" value="Samakan Pembawah" onClick="javascript:samakanPembawah()"/>
                                #if($flag_kemaskini_selesai != "yes")
                                <script>
                                        document.getElementById('cmdSamakanPembawah').style.display = "none";
                                        </script>
                                #end
                                #end </td>
                            </tr>
                            <tr>
                              <td valign="bottom" colspan="4">&nbsp;</td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                      <!--
                      #foreach ($pemegangamanah in $ListHTAPKTDTL)
                      	#if ($pemegangamanah.ID_TARAFKPTG  == '10' && $pemegangamanah.flag == 'Y')
                     		<font color="red"><b>
							<blink>PERINTAH INI MELIBATKAN PEMEGANG AMANAH BAGI HARTA TAK ALIH. SILA PASTIKAN BORANG HH DICETAK.</blink></b></font>
							<script type="text/javascript">
								alert("PERINTAH INI MELIBATKAN PEMEGANG AMANAH BAGI HARTA TAK ALIH. SILA PASTIKAN BORANG HH DICETAK.");
							</script>
                      	#end

                      #end-->
                        <td align="center" colspan="2"> #if ($mode == 'update')
                          #if ($modePopup == 'update')
                          <input name="cmdSimpanKemaskiniHTAPKT" id="cmdSimpanKemaskiniHTAPKT" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHTAPKT()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdSimpanKemaskiniHTAPKT').style.display = "none";
                                        </script>
                          #end
                          <input type="button" name="cmdPembahagianRata" id="cmdPembahagianRata" value="Pembahagian Separa" onClick="javascript:pembahagianSepara()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdPembahagianRata').style.display = "none";
                                        </script>
                          #end
                          <input type="button" name="cmdPembahagianFaraid" id="cmdPembahagianFaraid" value="Pembahagian Faraid" onClick="javascript:pembahagianFaraid()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdPembahagianFaraid').style.display = "none";
                                        </script>
                          #end
                          <input type="button" name="cmdKosongkanPembahagian" id="cmdKosongkanPembahagian" value="Kosongkan" onClick="javascript:kosongkanPembahagian()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdKosongkanPembahagian').style.display = "none";
                                        </script>
                          #end
                          <input type="button" name="cmdBatalHTAPKT" id="cmdBatalHTAPKT" value="Kembali" onClick="javascript:batalHTAPKT()"/>
                          #end
                          #end

                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHTAPKT" id="cmdBatalHTAPKT" value="Kembali" onClick="javascript:batalHTAPKT()"/>
                          #end </td>
                      </tr>
                    </table></td>
                </tr>
                #end
                <!-- END OPEN POPUP HTAPKT -->
                #if ($flagPopup != 'openHAPKT')
                <!-- START LIST HTAPKT -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>
                      #if($flagAdaHTAPKT == '1')

                      #if ($SenaraiHTAPKT.size() > 10)
                      <div style="width:100%;height:190;overflow:auto"> #end
                        <table align="center" width="100%">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="65%">KETERANGAN HAKMILIK</td>
                            <td width="15%" align="center">STATUS PEMILIKAN</td>
                            <td width="15%" align="center">JENIS TANAH</td>
                          </tr>
                          #set ($listHTAPKT = "")
                          #foreach ($listHTAPKT in $SenaraiHTAPKT)
                          #if ($listHTAPKT.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHTAPKT.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHTAPKT.bil</td>
                            #if($listHTAPKT.idPerintahHTAOBMST == '')
                            <td class="$row">$listHTAPKT.keteranganHakmilik</td>
                            #else
                            <td class="$row"><a href="javascript:paparHTAPKT('$listHTAPKT.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPKT.keteranganHakmilik</font><font color="#000000">$listHTAPKT.keterangan</font></a></td>
                            <td class="$row" align="center">$listHTAPKT.kodPB</td>
                            <td class="$row" align="center">$listHTAPKT.jenisTanah</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHTAPKT.size() > 10) </div>
                      #end

                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HTAPKT -->
                #end
                <!-- START OPEN POPUP HAPKT -->
                #if ($flagPopup == 'openHAPKT')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <tr>
                        <td width="30%" align="right">JENIS HARTA ALIH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisHA</font><font color="#000000">$headerDetail.keterangan</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENAMA/MODEL/BANK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenama</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">NO. PENDAFTARAN/AKAUN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.noDaftar</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"><table width="60%" border="0" cellspacing="2" cellpadding="2" align="center">
                            <tr>
                              <td> #if ($ListHAPKTDTL.size() > 5)
                                <div style="width:100%;height:133;overflow:auto"> #end
                                  <table align="center" width="100%" border="0">
                                    <tr class="table_header"> #if ($listSize != '0')
                                      <td scope="row" width="1%">&nbsp;</td>
                                      #end
                                      <td scope="row" width="4%" align="center">BIL</td>
                                      <td width="95%">NAMA PENTADBIR</td>
                                    </tr>
                                    #set ($list = "")
                                    #foreach ($list in $ListHAPKTDTL)
                                    #if ($list.bil == '')
                                    #set( $row = "row1" )
                                    #elseif (($list.bil % 2) != 0)
                                    #set( $row = "row1" )
                                    #else
                                    #set( $row = "row2" )
                                    #end
                                    <tr> #if ($listSize != '0')
                                      #if($list.flag == 'Y')
                                      #set($checked = 'checked')
                                      #else
                                      #set($checked = '')
                                      #end
                                      <td class="$row"><input type="checkbox" value="$list.idOB" name="idspentadbir" $checked $disabled onClick="doUpdateCheck('$list.bil')"/></td>
                                      #end
                                      <td class="$row" align="center">$list.bil</td>
                                      #if ($list.statusHidup == '1')
                                      <td class="$row">$list.namaOB &nbsp;&nbsp;<span class="style1"><strong>(MENINGGAL DUNIA)</strong></span></td>
                                      #else
                                      <td class="$row">$list.namaOB</td>
                                      #end </tr>
                                    #end
                                  </table>
                                  #if ($ListHAPKTDTL.size() > 5) </div>
                                #end </td>
                            </tr>
                            <tr>
                              <td>&nbsp;</td>
                            </tr>
                            <tr>
                              <td align="center"> #if ($mode == 'update')
                                #if ($modePopup == 'update')
                                <input name="cmdSimpanKemaskiniHAPKT"  id="cmdSimpanKemaskiniHAPKT" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHAPKT()"/>
                                #if($flag_kemaskini_selesai != "yes")
                                <script>
                                        document.getElementById('cmdSimpanKemaskiniHAPKT').style.display = "none";
                                        </script>
                                #end
                                <input type="button" name="cmdBatalHAPKT" id="cmdBatalHAPKT" value="Kembali" onClick="javascript:batalHAPKT()"/>
                                #end
                                #end

                                #if ($mode == 'view')
                                <input type="button" name="cmdBatalHAPKT" id="cmdBatalHAPKT" value="Kembali" onClick="javascript:batalHAPKT()"/>
                                #end </td>
                            </tr>
                          </table></td>
                      </tr>
                    </table></td>
                </tr>
                #end
                <!-- END OPEN POPUP HAPKT -->
                #if ($flagPopup != 'openHTAPKT')
                <!-- START LIST HAPKT -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>
                      #if($flagAdaHAPKT == '1')
                      <table align="center" width="100%">
                        <tr class="table_header">
                          <td scope="row" width="5%" align="center">BIL</td>
                          <td width="35%">JENIS HARTA ALIH</td>
                          <td width="30%">JENAMA/MODEL/BANK</td>
                          <td width="30%">NO. PENDAFTARAN/AKAUN</td>
                        </tr>
                        #set ($listHAPKT = "")
                        #foreach ($listHAPKT in $SenaraiHAPKT)
                        #if ($listHAPKT.bil == '')
                        #set( $row = "row1" )
                        #elseif (($listHAPKT.bil % 2) != 0)
                        #set( $row = "row1" )
                        #else
                        #set( $row = "row2" )
                        #end
                        <tr>
                          <td class="$row" align="center">$listHAPKT.bil</td>
                          #if($listHAPKT.idPerintahHAOBMST == '')
                          <td class="$row">$listHAPKT.jenisHA</td>
                          #else
                          <td class="$row"><a href="javascript:paparHAPKT('$listHAPKT.idPerintahHAOBMST')"><font color="#0000FF">$listHAPKT.jenisHA</font><font color="#000000">$listHAPKT.keterangan</font></a></td>
                          <td class="$row">$listHAPKT.jenama</td>
                          <td class="$row">$listHAPKT.noDaftar</td>
                          #end </tr>
                        #end
                      </table>
                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HAPKT -->
                #end
              </table>
            </div>
            <!-- END CONTENT PERINTAH KUASA TADBIR -->
            <!-- START CONTENT PERINTAH LELONG -->
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <!-- START OPEN POPUP HTAPL -->
                #if ($flagPopup == 'openHTAPL')
                #set ($maklumatHTAPL = "")
                #foreach($maklumatHTAPL in $BeanMaklumatHTAPL)
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">Keterangan Hakmilik</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">$maklumatHTAPL.keteranganHakmilik</td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' && $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td width="28%">Jenis Lelong</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%"><input type="radio" name="sorJenisLelong" value="A" id="sorJenisLelong" $checkedA $disabled/>
                          Lelong Awam
                          <input type="radio" name="sorJenisLelong" value="T" id="sorJenisLelong" $checkedT $disabled/>
                          Lelong Tender </td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' && $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Tarikh Jualan</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%"><input type="text" name="txdTarikhJualan" size="10" id="txdTarikhJualan" readonly="readonly" value="$maklumatHTAPL.tarikhJualan" class="$inputTextClass"/>
                          #if($mode == 'update' && $modePopup == 'update') <a href="javascript:displayDatePicker('txdTarikhJualan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end </td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' && $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Harga Rizab</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">RM
                          <input type="text" name="txtHargaRizab" id="txtHargaRizab" $readonly class="$inputTextClass" value="$maklumatHTAPL.hargaRizab" onBlur="validateCurrency(this,this.value,'$maklumatHTAPL.hargaRizab');"/></td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' && $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Amaun</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">RM
                          <input type="text" name="txtAmaun" id="txtAmaun" $readonly class="$inputTextClass" value="$maklumatHTAPL.amaun" onBlur="validateCurrency(this,this.value,'$maklumatHTAPL.amaun');"/></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">Catatan</td>
                        <td valign="top" width="1%">:</td>
                        <td valign="top" width="70%"><textarea name="txtCatatanHTAPL" cols="45" rows="5" id="txtCatatanHTAPL" $readonly class="$inputTextClass" >$maklumatHTAPL.catatan</textarea></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2"> #if ($mode == 'update')
                          #if ($modePopup == 'update')
                          <input name="cmdSimpanKemaskiniHTAPL" id="cmdSimpanKemaskiniHTAPL" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHTAPL()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdSimpanKemaskiniHTAPL').style.display = "none";
                                        </script>
                          #end
                          <input type="button" name="cmdBatalHTAPL" id="cmdBatalHTAPL" value="Kembali" onClick="javascript:batalHTAPL()"/>
                          #end
                          #end
                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHTAPL" id="cmdBatalHTAPL" value="Kembali" onClick="javascript:batalHTAPL()"/>
                          #end </td>
                      </tr>
                      #if ($mode == 'update' || $modePopup == 'update')
                      <tr>
                        <td colspan="4" height="50px" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
                      </tr>
                      #end
                    </table></td>
                </tr>
                #end
                #end
                <!-- END OPEN POPUP HTAPL -->
                #if ($flagPopup != 'openHAPL')
                <!-- START LIST HTAPL -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>
                      #if($flagAdaHTAPL == '1')

                      #if ($SenaraiHTAPL.size() > 10)
                      <div style="width:100%;height:250;overflow:auto"> #end
                        <table align="center" width="100%">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="40%">KETERANGAN HAKMILIK</td>
                            <td width="15%">JENIS LELONG</td>
                            <td width="10%" align="center">TARIKH JUALAN</td>
                            <td width="15%" align="right">HARGA RIZAB (RM)</td>
                            <td width="15%" align="right">AMAUN (RM)</td>
                          </tr>
                          #set ($listHTAPL = "")
                          #foreach ($listHTAPL in $SenaraiHTAPL)
                          #if ($listHTAPL.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHTAPL.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHTAPL.bil</td>
                            #if($listHTAPL.idPerintahHTAOBMST == '')
                            <td class="$row">$listHTAPL.keteranganHakmilik</td>
                            #else
                            <td class="$row"><a href="javascript:paparHTAPL('$listHTAPL.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPL.keteranganHakmilik</font></a></td>
                            <td class="$row">$listHTAPL.jenisLelong</td>
                            <td class="$row">$listHTAPL.tarikhJualan</td>
                            <td class="$row" align="right">$listHTAPL.hargaRizab</td>
                            <td class="$row" align="right">$listHTAPL.amaun</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHTAPL.size() > 10) </div>
                      #end

                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HTAPL -->
                #end
                <!-- START OPEN POPUP HAPL -->
                #if ($flagPopup == 'openHAPL')
                #set ($maklumatHAPL = "")
                #foreach($maklumatHAPL in $BeanMaklumatHAPL)
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">Jenis Harta Alih</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">$maklumatHAPL.jenisHA</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">Jenama/Model/Bank</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">$maklumatHAPL.jenama</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">No. Pendaftaran/Akaun</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">$maklumatHAPL.noDaftar</td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' || $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td width="28%">Jenis Lelong</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%"><input type="radio" name="sorJenisLelongHAPL" value="A" id="sorJenisLelongHAPL" $checkedA $disabled/>
                          Lelong Awam
                          <input type="radio" name="sorJenisLelongHAPL" value="T" id="sorJenisLelongHAPL" $checkedT $disabled/>
                          Lelong Tender </td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' || $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Tarikh Jualan</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%"><input type="text" name="txdTarikhJualanHAPL" size="10" id="txdTarikhJualanHAPL" readonly="readonly" value="$maklumatHAPL.tarikhJualan" class="$inputTextClass"/>
                          #if($mode == 'update' || $modePopup == 'update') <a href="javascript:displayDatePicker('txdTarikhJualanHAPL',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end </td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' || $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Harga Rizab</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">RM
                          <input type="text" name="txtHargaRizabHAPL" id="txtHargaRizabHAPL" $readonly class="$inputTextClass" value="$maklumatHAPL.hargaRizab" onBlur="validateCurrency(this,this.value,'$maklumatHAPL.hargaRizab');"/></td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' || $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Amaun</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">RM
                          <input type="text" name="txtAmaunHAPL" id="txtAmaunHAPL" $readonly class="$inputTextClass" value="$maklumatHAPL.amaun" onBlur="validateCurrency(this,this.value,'$maklumatHAPL.amaun');"/></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">Catatan</td>
                        <td valign="top" width="1%">:</td>
                        <td valign="top" width="70%"><textarea name="txtCatatanHTAPL" cols="45" rows="5" id="txtCatatanHTAPL" $readonly class="$inputTextClass" >$maklumatHAPL.catatan</textarea></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2"> #if ($mode == 'update')
                          #if ($modePopup == 'update')
                          <input name="cmdSimpanKemaskiniHAPL" id="cmdSimpanKemaskiniHAPL"  type="button" value="Simpan" onClick="javascript:simpanKemaskiniHAPL()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdSimpanKemaskiniHAPL').style.display = "none";
                                        </script>
                          #end
                          <input type="button" name="cmdBatalHAPL" id="cmdBatalHAPL" value="Kembali" onClick="javascript:batalHAPL()"/>
                          #end
                          #end
                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHAPL" id="cmdBatalHAPL" value="Kembali" onClick="javascript:batalHAPL()"/>
                          #end </td>
                      </tr>
                      #if ($modePopup == 'new' || $modePopup == 'update')
                      <tr>
                        <td colspan="4" height="50px" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
                      </tr>
                      #end
                    </table></td>
                </tr>
                #end
                #end
                <!-- END OPEN POPUP HAPL -->
                #if ($flagPopup != 'openHTAPL')
                <!-- START LIST HAPL -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>
                      #if($flagAdaHAPL == '1')
                      <table align="center" width="100%">
                        <tr class="table_header">
                          <td scope="row" width="5%" align="center">BIL</td>
                          <td width="20%">JENIS HARTA ALIH</td>
                          <td width="20%">NO. PENDAFTARAN/AKAUN</td>
                          <td width="15%">JENIS LELONG</td>
                          <td width="10%" align="center">TARIKH JUALAN</td>
                          <td width="15%" align="right">HARGA RIZAB (RM)</td>
                          <td width="15%" align="right">AMAUN (RM)</td>
                        </tr>
                        #set ($listHAPL = "")
                        #foreach ($listHAPL in $SenaraiHAPL)
                        #if ($listHAPL.bil == '')
                        #set( $row = "row1" )
                        #elseif (($listHAPL.bil % 2) != 0)
                        #set( $row = "row1" )
                        #else
                        #set( $row = "row2" )
                        #end
                        <tr>
                          <td class="$row" align="center">$listHAPL.bil</td>
                          #if($listHAPL.idPerintahHAOBMST == '')
                          <td class="$row">$listHAPL.jenisHA</td>
                          #else
                          <td class="$row"><a href="javascript:paparHAPL('$listHAPL.idPerintahHAOBMST')"><font color="#0000FF">$listHAPL.jenisHA</font><font color="#000000">$listHAPL.keterangan</font></a></td>
                          <td class="$row">$listHAPL.noDaftar</td>
                          <td class="$row">$listHAPL.jenisLelong</td>
                          <td class="$row">$listHAPL.tarikhJualan</td>
                          <td class="$row" align="right">$listHAPL.hargaRizab</td>
                          <td class="$row" align="right">$listHAPL.amaun</td>
                          #end </tr>
                        #end
                      </table>
                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HAPL -->
                #end
              </table>
            </div>
            <!-- END CONTENT PERINTAH LELONG -->
            <!-- START CONTENT PERINTAH FARAID -->
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <!-- START OPEN POPUP HTAPF -->
                #if ($flagPopup == 'openHTAPF')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <tr>
                        <td width="30%" align="right">KETERANGAN HAKMILIK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.keteranganHakmilik</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">STATUS PEMILIKAN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisPB</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENIS TANAH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisTanah</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"><table width="95%" border="0" cellspacing="2" cellpadding="2" align="center">
                            <tr class="table_header">
                              <td align="center" width="5%">BIL</td>
                              <td width="60%">NAMA WARIS</td>
                              <td width="20%">STATUS WARIS</td>
                              <td align="center" width="15%">BAHAGIAN WARIS</td>
                            </tr>
                            #set ($listHTAPFDTL = "")
                            #foreach ($listHTAPFDTL in $SenaraiHTAPFDTL)
                            <tr>
                              <input name="idspentadbir" type="hidden" value="$listHTAPFDTL.idOB">
                              <td align="center">$listHTAPFDTL.bil</td>
                              #if ($listHTAPFDTL.status == '')
                              <td>$listHTAPFDTL.namaWaris</td>
                              #else
                              <td><a href="javascript:updatePAPTHTAPF('$listHTAPFDTL.idOB','$idPermohonanSimati','$idSimati','$listHTAPFDTL.status','$idPerintah','$mode','$idHTA','$flag_kemaskini_selesai')"><font color="#0000FF">$listHTAPFDTL.namaWaris</font></a></td>
                              #end
                              <td>$listHTAPFDTL.status</td>
                              <td align="center">$listHTAPFDTL.bahagianWaris</td>
                            </tr>
                            #end
                            <tr class="table_header">
                              <td width="5%">&nbsp;</td>
                              <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                              <td align="center" width="15%"><strong>$jumlahBahagian</strong></td>
                            </tr>
                            <tr>
                              <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                              <td valign="bottom" colspan="4"><i>&nbsp;&nbsp;&nbsp;&nbsp;<font color="#ff0000">Perhatian</font> : Sila klik pada nama waris untuk melantik pemegang amanah / pentadbir bagi waris yang menerima bahagian.</i></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2" align="center"> #if($mode == 'update')
                          <input name="cmdSimpanKemaskiniHTAPF" id="cmdSimpanKemaskiniHTAPF" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHTAPF()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdSimpanKemaskiniHTAPF').style.display = "none";
                                        </script>
                          #end
                          #end
                          <input type="button" name="cmdBatalHTAPF" id="cmdBatalHTAPF" value="Kembali" onClick="javascript:batalHTAPF()"/></td>
                      </tr>
                    </table></td>
                </tr>
                #end
                <!-- END OPEN POPUP HTAPF -->
                #if ($flagPopup != 'openHAPF')
                <!-- START LIST HTAPF -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>
                      #if($flagAdaHTAPF == '1')

                      #if ($SenaraiHTAPF.size() > 10)
                      <div style="width:100%;height:190;overflow:auto"> #end
                        <table align="center" width="100%">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="65%">KETERANGAN HAKMILIK</td>
                            <td width="15%" align="center">STATUS PEMILIKAN</td>
                            <td width="15%" align="center">JENIS TANAH</td>
                          </tr>
                          #set ($listHTAPF = "")
                          #foreach ($listHTAPF in $SenaraiHTAPF)
                          #if ($listHTAPF.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHTAPF.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHTAPF.bil</td>
                            #if($listHTAPF.idPerintahHTAOBMST == '')
                            <td class="$row">$listHTAPF.keteranganHakmilik</td>
                            #else
                            <td class="$row"><a href="javascript:paparHTAPF('$listHTAPF.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPF.keteranganHakmilik</font></a></td>
                            <td class="$row" align="center">$listHTAPF.kodPB</td>
                            <td class="$row" align="center">$listHTAPF.jenisTanah</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHTAPF.size() > 10) </div>
                      #end

                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HTAPF -->
                #end
                <!-- START OPEN POPUP HAPF -->
                #if ($flagPopup == 'openHAPF')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <tr>
                        <td width="30%" align="right">JENIS HARTA ALIH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisHA</font><font color="#000000">$headerDetail.keterangan</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENAMA/MODEL/BANK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenama</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">NO. PENDAFTARAN/AKAUN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.noDaftar</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"><table width="95%" border="0" cellspacing="2" cellpadding="2" align="center">
                            <tr class="table_header">
                              <td align="center" width="5%">BIL</td>
                              <td width="60%">NAMA WARIS</td>
                              <td width="20%">STATUS WARIS</td>
                              <td align="center" width="15%">BAHAGIAN WARIS</td>
                            </tr>
                            #set ($listHAPFDTL = "")
                            #foreach ($listHAPFDTL in $SenaraiHAPFDTL)
                            <tr>
                              <input name="idspentadbir" type="hidden" value="$listHAPFDTL.idOB">
                              <td align="center">$listHAPFDTL.bil</td>
                              #if ($listHAPFDTL.status == '')
                              <td>$listHAPFDTL.namaWaris</td>
                              #else
                              <td><a href="javascript:updatePAPTHAPF('$listHAPFDTL.idOB','$idPermohonanSimati','$idSimati','$listHAPFDTL.status','$idPerintah','$mode','$idHA','$flag_kemaskini_selesai')"><font color="#0000FF">$listHAPFDTL.namaWaris</font></a></td>
                              #end
                              <td >$listHAPFDTL.status</td>
                              <td align="center">$listHAPFDTL.bahagianWaris</td>
                            </tr>
                            #end
                            <tr class="table_header">
                              <td width="5%">&nbsp;</td>
                              <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                              <td align="center" width="15%"><strong>$jumlahBahagian</strong></td>
                            </tr>
                            <tr>
                              <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                              <td valign="bottom" colspan="4"><i>&nbsp;&nbsp;&nbsp;&nbsp;<font color="#ff0000">Perhatian</font> : Sila klik pada nama waris untuk melantik pemegang amanah / pentadbir bagi waris yang menerima bahagian.</i></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2" align="center"> #if($mode == 'update')
                          <input name="cmdSimpanKemaskiniHAPF" id="cmdSimpanKemaskiniHAPF" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHAPF()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdSimpanKemaskiniHAPF').style.display = "none";
                                        </script>
                          #end
                          #end
                          <input type="button" name="cmdBatalHAPF" id="cmdBatalHAPF" value="Kembali" onClick="javascript:batalHAPF()"/></td>
                      </tr>
                    </table></td>
                </tr>
                #end
                <!-- END OPEN POPUP HAPF -->
                #if ($flagPopup != 'openHTAPF')
                <!-- START LIST HAPF -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>
                      #if($flagAdaHAPF == '1')

                      #if ($SenaraiHAPF.size() > 10)
                      <div style="width:100%;height:190;overflow:auto"> #end
                        <table align="center" width="100%">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="35%">JENIS HARTA ALIH</td>
                            <td width="30%">JENAMA/MODEL/BANK</td>
                            <td width="30%">NO. PENDAFTARAN/AKAUN</td>
                          </tr>
                          #set ($listHAPF = "")
                          #foreach ($listHAPF in $SenaraiHAPF)
                          #if ($listHAPF.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHAPF.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHAPF.bil</td>
                            #if($listHAPF.idPerintahHAOBMST == '')
                            <td class="$row">$listHAPF.jenisHA</td>
                            #else
                            <td class="$row"><a href="javascript:paparHAPF('$listHAPF.idPerintahHAOBMST')"><font color="#0000FF">$listHAPF.jenisHA.toUpperCase()</font><font color="#000000">$listHAPF.keterangan</font></a></td>
                            <td class="$row">$listHAPF.jenama.toUpperCase()</td>
                            <td class="$row">$listHAPF.noDaftar</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHAPF.size() > 10) </div>
                      #end

                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HAPF -->
                #end
              </table>
            </div>

            <!-- END CONTENT PERINTAH FARAID -->
            <!-- arief add open -->
  			<!-- START CONTENT PERINTAH AKTA 1958 -->

  			<div class="TabbedPanelsContent">
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
 				<!-- START OPEN POPUP HTAPA1958 -->

  				#if ($flagPopup == 'openHTAPA1958')
	  			<tr>
	     			<td>
	     				<table width="100%" border="0" cellspacing="2" cellpadding="2">
		     				#set ($headerDetail = "")
		     				#foreach($headerDetail in $BeanHeaderDetail)
			     			<tr>
			     				<td width="30%" align="right">KETERANGAN HAKMILIK :</td>
			        			<td width="70%" align="left"><font color="blue">$headerDetail.keteranganHakmilik</font></td>
			        		</tr>
			     			<tr>
			        			<td width="30%" align="right">STATUS PEMILIKAN :</td>
			        			<td width="70%" align="left"><font color="blue">$headerDetail.jenisPB</font></td>
			     			</tr>
			     			<tr>
			     				<td width="30%" align="right">JENIS TANAH :</td>
			        			<td width="70%" align="left"><font color="blue">$headerDetail.jenisTanah</font></td>
			     			</tr>
			     			<tr>
			     				<td width="30%" align="right">BAHAGIAN SIMATI :</td>
			        			<td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
			     			</tr>
			     			<tr>
			     				<td width="30%" align="right" valign="top">CATATAN :</td>
			        			<td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
			     			</tr>
     						#end
			     			<tr>
			         			<td>&nbsp;</td>
			         			<td>&nbsp;</td>
			     			</tr>
     						<tr>
     							<td colspan="2">
     								<table width="95%" border="0" cellspacing="2" cellpadding="2" align="center">
					        			<tr class="table_header">
					        				<td align="center" width="5%">BIL</td>
					            			<td width="60%">NAMA WARIS</td>
					          				<td width="20%">STATUS WARIS</td>
					            			<td align="center" width="15%">BAHAGIAN WARIS</td>
					       				</tr>

					        			#set ($listHTAPA1958DTL = "")
				        				#foreach ($listHTAPA1958DTL in $SenaraiHTAPA1958DTL)
					       				<tr>
					        				<input name="idspentadbir" type="hidden" value="$listHTAPA1958DTL.idOB">
					            			<td align="center">$listHTAPA1958DTL.bil</td>
					            			#if ($listHTAPA1958DTL.status == '')
					            			<td>$listHTAPA1958DTL.namaWaris</td>
					                		#else
					                    	<td><a href="javascript:updatePAPTHTAPA1958('$listHTAPA1958DTL.idOB','$idPermohonanSimati','$idSimati','$listHTAPFDTL.status','$idPerintah','$mode','$idHTA','$flag_kemaskini_selesai')"><font color="#0000FF">$listHTAP1958DTL.namaWaris</font></a></td>
					                        #end
			                              	<td>$listHTAPA1958DTL.status</td>
			                              	<td align="center">$listHTAPA1958DTL.bahagianWaris</td>
		                    	      	</tr>
                            			#end

			                            <tr class="table_header">
			                              <td width="5%">&nbsp;</td>
			                              <td colspan="2" align="left"><strong>JUMLAH</strong></td>
			                              <td align="center" width="15%"><strong>$jumlahBahagian</strong></td>
			                            </tr>
			                            <tr>
			                              <td colspan="4">&nbsp;</td>
			                            </tr>
			                            <tr>
			                              <td valign="bottom" colspan="4"><i>&nbsp;&nbsp;&nbsp;&nbsp;<font color="#ff0000">Perhatian</font> : Sila klik pada nama waris untuk melantik pemegang amanah / pentadbir bagi waris yang menerima bahagian.</i></td>
			                            </tr>
                          			</table>
                       			</td>
							</tr>
	                      	<tr>
		                        <td>&nbsp;</td>
		                        <td>&nbsp;</td>
	                      	</tr>
							<tr>
		                        <td colspan="2" align="center"> #if($mode == 'update' && $userRole != "user_ppk")
		                          <input name="cmdSimpanKemaskiniHTAPA1958" id="cmdSimpanKemaskiniHTAPA1958" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHTAPA1958()"/>
		                          #if($flag_kemaskini_selesai != "yes")
		                          <script>
									document.getElementById('cmdSimpanKemaskiniHTAPA1958').style.display = "none";
		                          </script>
		                          #end
		                          #end
		                          <input type="button" name="cmdBatalHTAPF" id="cmdBatalHTAPF" value="Kembali" onClick="javascript:batalHTAPA1958()"/>
                          		</td>
	                      	</tr>
	                    </table>
               		</td>
                </tr>
                #end
                <!-- END OPEN POPUP HTAPA1958 -->

                #if ($flagPopup != 'openHAPA1958')
                <!-- START LIST HTAPA1958 -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>
                      #if($flagAdaHTAPA1958 == '1')

                      #if ($SenaraiHTAPA1958.size() > 10)
                      <div style="width:100%;height:190;overflow:auto"> #end
                        <table align="center" width="100%">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="65%">KETERANGAN HAKMILIK</td>
                            <td width="15%" align="center">STATUS PEMILIKAN</td>
                            <td width="15%" align="center">JENIS TANAH</td>
                          </tr>
                          #set ($listHTAPA1958 = "")
                          #foreach ($listHTAPA1958 in $SenaraiHTAPA1958)
                          #if ($listHTAPA1958.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHTAPA1958.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHTAPA1958.bil</td>
                            #if($listHTAPA1958.idPerintahHTAOBMST == '')
                            <td class="$row">$listHTAPA1958.keteranganHakmilik</td>
                            #else
                            <td class="$row"><a href="javascript:paparHTAPA1958('$listHTAPA1958.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPF.keteranganHakmilik</font></a></td>
                            <td class="$row" align="center">$listHTAPA1958.kodPB</td>
                            <td class="$row" align="center">$listHTAPA1958.jenisTanah</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHTAPA1958.size() > 10) </div>
                      #end

                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HTAPA1958 -->
                #end
                <!-- START OPEN POPUP HAPA1958 -->
                #if ($flagPopup == 'openHAPA1958')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <tr>
                        <td width="30%" align="right">JENIS HARTA ALIH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisHA</font><font color="#000000">$headerDetail.keterangan</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENAMA/MODEL/BANK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenama</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">NO. PENDAFTARAN/AKAUN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.noDaftar</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"><table width="95%" border="0" cellspacing="2" cellpadding="2" align="center">
                            <tr class="table_header">
                              <td align="center" width="5%">BIL</td>
                              <td width="60%">NAMA WARIS</td>
                              <td width="20%">STATUS WARIS</td>
                              <td align="center" width="15%">BAHAGIAN WARIS</td>
                            </tr>
                            #set ($listHAPA1958DTL = "")
                            #foreach ($listHAPA1958DTL in $SenaraiHAPA1958DTL)
                            <tr>
                              <input name="idspentadbir" type="hidden" value="$listHAPA1958DTL.idOB">
                              <td align="center">$listHAPA1958DTL.bil</td>
                              #if ($listHAPA1958DTL.status == '')
                              <td>$listHAPA1958DTL.namaWaris</td>
                              #else
                              <td><a href="javascript:updatePAPTHAPA1958('$listHAPA1958DTL.idOB','$idPermohonanSimati','$idSimati','$listHAPA1958DTL.status','$idPerintah','$mode','$idHA','$flag_kemaskini_selesai')"><font color="#0000FF">$listHAPA1958DTL.namaWaris</font></a></td>
                              #end
                              <td >$listHAPA1958DTL.status</td>
                              <td align="center">$listHAPA1958DTL.bahagianWaris</td>
                            </tr>
                            #end
                            <tr class="table_header">
                              <td width="5%">&nbsp;</td>
                              <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                              <td align="center" width="15%"><strong>$jumlahBahagian</strong></td>
                            </tr>
                            <tr>
                              <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                              <td valign="bottom" colspan="4"><i>&nbsp;&nbsp;&nbsp;&nbsp;<font color="#ff0000">Perhatian</font> : Sila klik pada nama waris untuk melantik pemegang amanah / pentadbir bagi waris yang menerima bahagian.</i></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2" align="center"> #if($mode == 'update' && $userRole != "user_ppk")
                          <input name="cmdSimpanKemaskiniHAPA1958" id="cmdSimpanKemaskiniHAPA1958" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHAPA1958()"/>
                          #if($flag_kemaskini_selesai != "yes")
                          <script>
                                        document.getElementById('cmdSimpanKemaskiniHAPA1958').style.display = "none";
                                        </script>
                          #end
                          #end
                          <input type="button" name="cmdBatalHAPA1958" id="cmdBatalHAPA1958" value="Kembali" onClick="javascript:batalHAPA1958()"/></td>
                      </tr>
                    </table></td>
                </tr>
                #end
                <!-- END OPEN POPUP HAPA1958 -->

                #if ($flagPopup != 'openHTAPA1958')
                <!-- START LIST HAPA1958 -->
                <tr>
                	<td>
                		<fieldset>
                      	<legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>
                      	#if($flagAdaHAPA1958 == '1')

                      		#if ($SenaraiHAPA1958.size() > 10)
                      			<div style="width:100%;height:190;overflow:auto">
                      		#end
		                        <table align="center" width="100%">
	                          		<tr class="table_header">
			                            <td scope="row" width="5%" align="center">BIL</td>
			                            <td width="35%">JENIS HARTA ALIH</td>
			                            <td width="30%">JENAMA/MODEL/BANK</td>
			                            <td width="30%">NO. PENDAFTARAN/AKAUN</td>
		                          	</tr>
		                          	#set ($listHAPA1958 = "")
	                          		#foreach ($listHAPA1958 in $SenaraiHAPFA1958)
			                        #if ($listHAPA1958.bil == '')
			                        #set( $row = "row1" )
			                        #elseif (($listHAPA1958.bil % 2) != 0)
			                        #set( $row = "row1" )
			                        #else
			                        #set( $row = "row2" )
			                        #end
		                          	<tr>
			                            <td class="$row" align="center">$listHAPA1958.bil</td>
			                            #if($listHAPA1958.idPerintahHAOBMST == '')
			                            <td class="$row">$listHAPA1958.jenisHA</td>
			                            #else
			                            <td class="$row"><a href="javascript:paparHAPA1958('$listHAPA1958.idPerintahHAOBMST')"><font color="#0000FF">$listHAPF.jenisHA.toUpperCase()</font><font color="#000000">$listHAPA1958.keterangan</font></a></td>
			                            <td class="$row">$listHAPA1958.jenama.toUpperCase()</td>
			                            <td class="$row">$listHAPA1958.noDaftar</td>
		                            	#end
		                            </tr>
		                          #end
		                        </table>
	                        #if ($SenaraiHAPAKTA1958.size() > 10)
		                        </div>
                      		#end

                      	#else <br>
	                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
	                      <br>
                    	#end
                    	</fieldset>
                   	</td>
                </tr>
                <!-- END LIST HAPA1958 -->
                #end
              	</table>
            	</div>
            	<!-- END CONTENT PERINTAH AKTA 1958 -->
          		</div>
        		</div>
      		</fieldset>
      		</td>
  			</tr>
  			<!-- arief add end -->
          </div>
        </div>
      </fieldset>
	</td>
</tr>
</table>

<!-- END PEMBAHAGIAN HARTA -->

<script type="text/javascript">
// js untuk tabs
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});

var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabLower});

function setSelectedTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
function setSelectedTabLower(tabId) {
	document.${formName}.selectedTabLower.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}

function paparHTA(idHTA) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTA";
	document.${formName}.modePopup.value = "update";
	document.${formName}.idHTA.value = idHTA;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.BicaraInteraktif";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.method = "POST";
	document.${formName}.submit();
}
</script>