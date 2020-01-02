<fieldset id="headerppk" name="headerppk">
	<legend>
		<strong>Maklumat Pembangunan Strata</strong>
	</legend>
	<p>
		<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
		<input type="hidden" name="hitButton">
		<input type="hidden" name="idStrata" value="$!idStrata">
<!-- 		<input type="hidden" name="idCf" value="$!idCf"> -->
		<input type="hidden" name="idHm" value="$!idHm">
		<input type="hidden" name="mode" value="$!mode">
		<input type="hidden" name="flagCf" value="$!flagCf"/>
        <input type="hidden" name="flagPemilik" value="$!flagPemilik"/>
		<input type="hidden" name="cnt"/>
		<input type="hidden" name="idCf"/>
		<input type="hidden" name="idcfs" value="$!idcfs"/>
        <input type="hidden" name="idPemilik" value="$!idPemilik"/>
        <input type="hidden" name="idpemilik" value="$!idpemilik"/>
        <input type="hidden" name="flagStrata" value="$!flagStrata"/>
      <!--  <input type="text" name="idTblrujstatusstrata" value="$!idTblrujstatusstrata"/> -->
        
        
		
	</p>
    
   <!-- :::::::::::: $idStrata-->
    
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td width="52%" valign="top">
					<div align="center">
						<table width="100%" border="0">
							<tbody>
                           <!-- <tr>
									<td scope="row" width="21%" align="right"><font color="red">*</font>Skim Bangunan Khas?</td>
									<td width="1%" scope="row">:</td>
                                    #if($flagStrata=='Y')
									<td width="78%"><input type="checkbox" id="myCheck" checked onclick="myFunction()"> 
									Ya
                                    #else
									<td width="78%"><input type="checkbox" id="myCheck" onclick="myFunction()"> 
									Ya
                                    #end
									</td>
								</tr>-->
                                <tr>
									<td scope="row" width="21%" align="right">Skim Bangunan Khas?</td>
									<td width="1%" scope="row">:</td>
									<td width="78%">
										<input id="radio" name="radio" type="radio" class="radio-btn" value="Y" onclick="myFunction()" #if($flagStrata=='Y') checked #end />
										Ya
										<input id="radio" name="radio" type="radio" class="radio-btn" value="T" onclick="myFunction()" #if($flagStrata=='T') checked #end />
										Tidak
									</td>
								</tr>
								<tr>
									<td scope="row" align="right"><font color="red">*</font>Negeri</td>
									<td width="1%" scope="row">:</td>
									<td width="58%">$selectNegeriHM</td>
								</tr>
								<tr>
									<td scope="row" align="right"><font color="red">*</font>Daerah</td>
									<td width="1%" scope="row">:</td>
									<td>$selectDaerah</td>
								</tr>
								<tr>
									<td scope="row" align="right"><font color="red">*</font>Bandar/Pekan/Mukim</td>
									<td width="1%" scope="row">:</td>
									<td>$selectMukim</td>
								</tr>
								<tr>
									<td width="41%" scope="row" align="right"><font color="red">*</font>No Lot</td>
									<td width="1%" scope="row">:</td>
									<td>
										$selectIdLot
										<input name="noLot" type="text" id="noLot" value="$!noLot" size="10" />
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
				<td width="48%" valign="top">
					<table width="100%" border="0">
						<tbody>
							<tr>
									<td scope="row" align="right"><font color="red">*</font>Jenis Hakmilik</td>
									<td width="2%" scope="row">:</td>
									<td width="51%">$selectJenisHakmilik</td>
								</tr>
								<tr>
									<td width="47%" scope="row" align="right"><font color="red">*</font>No Hakmilik</td>
									<td width="2%" scope="row">:</td>
									<td>
										<input name="noHakmilik" type="text" id="noHakmilik" value="$!noHakmilik" size="40" maxlength="8" />
									</td>
								</tr>
                               <tr>
									<td width="47%" scope="row" align="right"><font color="red">*</font>Bil Petak</td>
									<td width="2%" scope="row">:</td>
									<td>
										<input name="bilPetak" $RO_General type="text" id="bilPetak" value="$!bilPetak" size="40" />
									</td>
						  </tr>
                          <tr>
									<td width="47%" scope="row" align="right"><font color="red">*</font>Pihak Berkuasa Tempatan</td>
									<td width="2%" scope="row">:</td>
									<td>
										<select id="txtSenaraiPBT" name="txtSenaraiPBT">
										<option value = "" >SILA PILIH</option>
										#foreach ( $pbt in $senaraiPBT )
										#set ( $selected_doc = "" )
										#if($txtSenaraiPBT==$pbt.ID_PEJABAT)
										#set ( $selected_doc = "selected" )
										#end
										<option $selected_doc value="$pbt.ID_PEJABAT" >$pbt.NAMA_PEJABAT</option>
										#end
										</select>
									</td>
						  </tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
    <br />
    <a href = "javascript:doDivAjaxCall$formname('divShowHistory','showHistory','ID_HAKMILIK=$!idHm');">Papar Maklumat</a>
    <div id="divShowHistory">
    #if($!idHm != "")
<!--    	<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('divShowHistory','showHistory','ID_HAKMILIK=$!idHm');			 	  
		  });
		
		</script>-->
   #end   
    </div>
    
</fieldset>

#if($idStrata != "")
<table id="main_table" width="100%" border="0">
	<tbody>
		<tr>
			<td colspan="2">
				<fieldset>
					<legend>
						<strong>Maklumat CF</strong>
					</legend>
					<div align="center">
						<table border="0" cellpadding="2" cellspacing="2" width="100%">
							<tbody>
                            <tr id="trNoFailMajlis">
									<td scope="row" width="21%" align="right">No Fail Pihak Berkuasa Tempatan (PBT)</td>
									<td width="1%" scope="row">:</td>
									<td width="78%">
										<input id="noFailMajlis" type="text" maxlength="30" size="30" style="text-transform:uppercase;" name="noFailMajlis" value="$!noFailMajlis"></input>
									</td>
								</tr>
								<tr>
									<td scope="row" width="21%" align="right">Ada CF/CCC?</td>
									<td width="1%" scope="row">:</td>
									<td width="78%">
										<input id="radioCF" name="radioCF" type="radio" class="radio-btn" value="Y" onclick="hideShow('Y')" #if($flagCf=='Y') checked #end />
										Ya
										<input id="radioCF" name="radioCF" type="radio" class="radio-btn" value="T" onclick="hideShow('T')" #if($flagCf=='T') checked #end />
										Tidak
									</td>
								</tr>
								<tr id="trNoCf">
									<td scope="row" width="21%" align="right">No CF</td>
									<td width="1%" scope="row">:</td>
									<td width="78%">
										<input id="noCf" type="text" maxlength="30" size="30" style="text-transform:uppercase;" name="noCf" value="$!noCf"></input>
									</td>
								</tr>
								<tr id="trTarikhCf">
									<td scope="row" align="right">Tarikh CF/CCC/Tarikh No Kelulusan Khas</td>
									<td width="1%" scope="row">:</td>
									<td>
										<input name="tarikhCf" id="tarikhCf" value="$!tarikhCf" onblur="check_date(this);" size="9">
										<a href="javascript:displayDatePicker('tarikhCf',false,'dmy');"><img src="../img/calendar.gif" border="0">
										</a>
									</td>
								</tr>
								<tr id="trNoklsn">
									<td scope="row" align="right">No Kelulusan Khas</td>
									<td width="1%" scope="row">:</td>
									<td width="78%">
										<input id="noKelulusankhas" type="text" maxlength="30" size="30" style="text-transform:uppercase;" name="noKelulusankhas"
											value="$!noKelulusankhas"></input>
									</td>
								</tr>
                                <!--<tr id="trTarikhNoKelulusanKhas">
									<td scope="row" align="right">Tarikh No Kelulusan Khas</td>
									<td width="1%" scope="row">:</td>
									<td>
										<input name="tarikhNoKelulusanKhas" id="tarikhNoKelulusanKhas" value="$!tarikhNoKelulusanKhas" onblur="check_date(this);" size="9">
										<a href="javascript:displayDatePicker('tarikhNoKelulusanKhas',false,'dmy');"><img src="../img/calendar.gif" border="0">
										</a>
									</td>
								</tr>-->
                                <tr id="ulasan">
									<td scope="row" align="right">Ulasan</td>
									<td width="1%" scope="row">:</td>
									<td width="78%">
										<textarea id="ulasan" maxlength="500" size="30" style="text-transform:uppercase;" name="ulasan"
											value="$!ulasan">$!ulasan</textarea>
									</td>
								</tr>   
							</tbody>
						</table>
					</div>
				</fieldset>
			</td>
		</tr>
	</tbody>
</table>
<table id="main_table" width="100%" border="0">
	<tbody>
		<tr>
			<td colspan="2">
				<fieldset>
					<legend>
						<strong>Maklumat Pemilik Tanah</strong>
                        
					</legend>
					<div align="left">
						<table width="100%" border="0">
		<tbody>
			<tr>
				<td width="50%" valign="top">
					<div align="center">
						<table width="100%" border="0">
							<tbody>
								<tr>
									<td scope="row" align="right">Jenis Pemilik Tanah</td>
									<td width="1%" scope="row">:</td>
									<!--<td>$selectNegeriHM</td>-->
                                    <td width="50%"><input id="radioPemilik" name="radioPemilik" type="radio" class="radio-btn" value="Individu"  #if($!radioPemilik=='Individu') checked="checked" #end/>
                                      Individu
                                        <input id="radioPemilik" name="radioPemilik" type="radio" class="radio-btn" value="Syarikat" #if($!radioPemilik=='Syarikat') checked="checked" #end/>
                                        Syarikat</td>
								</tr>
								<tr>
													<td width="50%" valign="top">
														<div align="right">No Kad Pengenalan/No Pendaftaran</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td width="50%">
														<input id="noKadPengenalan" type="text" maxlength="80" size="43" name="noKadPengenalan" value="$!noKadPengenalan"></input>
													</td>
												</tr>
								<tr>
													<td width="50%" valign="top">
														<div align="right">Nama Pemilik Tanah</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td width="50%">
														<input id="namaPemilikTanah" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="namaPemilikTanah" value="$!namaPemilikTanah"></input>
													</td>
												</tr>
                                                <tr>
                                                <!--ListPemilik = $ListPemilik-->
													<td colspan="3" valign="top">
                                                     #if($ListPemilik.size() > 0)
													<div align="center">Adakah alamat sama dengan alamat pemilik yang pertama?		 
                                                    <input type="checkbox"  id="checkalamatPemilik" name="checkalamatPemilik" value="Y" onClick="copyAlamatPemilik()"></input>
																																																											</div>
                                                                                                                                                                                                   #end
                                                                                                                                                                                
                                                                                                                                                                                </td>
												</tr>
                               <tr>
													<td width="50%" valign="top">
														<div align="right">Alamat</div>
													</td>
													<td>:</td>
													<td>
														<span id="alamat1P_2a">
															<input id="alamat1Pemilik" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat1Pemilik"
																value="$!alamat1Pemilik"></input>
														</span>
														<span id="talamat1P_2b" style="display: none;">
															<input id="alamat1Pemilik" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat1Pemilik"
																value="$!alamat1Pemilik"></input>
														</span>
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right"></div>
													</td>
													<td>:</td>
													<td>
														<span id="alamat2P_3a">
															<input id="alamat2Pemilik" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat2Pemilik"
																value="$!alamat2Pemilik"></input>
														</span>
														<span id="alamat2P_3b" style="display: none;">
															<input id="alamat2Pemilik" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat2Pemilik"
																value="$!alamat2Pemilik"></input>
														</span>
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right"></div>
													</td>
													<td>:</td>
													<td>
														<span id="alamat3P_4a">
															<input id="alamat3Pemilik" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat3Pemilik"
																value="$!alamat3Pemilik"></input>
														</span>
														<span id="alamat3P_4b" style="display: none;">
															<input id="alamat3Pemilik" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat3Pemilik"
																value="$!alamat3Pemilik"></input>

														</span>
													</td>

												</tr>
                                                <tr>
								<td></td>
								<td></td>
								<td>
									<input type="button" id="cmdAddPemilik" onclick="javascript:doAddPemilik()" value="Tambah Pemilik Tanah" name="cmdAddPemilik" />
                                 
								</td>
                                
							</tbody>
						</table>
					</div>
				</td>
				<td width="50%" valign="top">
					<table width="100%" border="0">
						<tbody>
							<tr>
													<td width="50%" valign="top">
														<div align="right">Poskod</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td>
														<input name="poskodPemilik" class="" id="poskodPemilik" size="5" maxlength="5"
															onblur="validateLength(this.value);" type="text" value="$!poskodPemilik">
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right">Negeri</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td>$selectNegeriPemilik</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right">Bandar</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td>$selectBandarPemilik</td>
												</tr>
						</tbody>
					</table>
				</td>
			</tr>
            <tr>
									<td colspan="3">
										<table width="100%" cellspacing="2" cellpadding="1" border="0">
											<tr class="table_header">
											  	<td width="2%" align="center">No</td>
													<td width="5%"align="center">No Kad Pengenalan / No Pendaftaran</td>	
											  		<td width="5%"align="center">Nama Pemilik Tanah</td>
													<td width="5%"align="center">Alamat</td>	
												<td width="1%"align="center"></td>
											  </tr>
											 
											  #if($ListPemilik.size() > 0)
												#foreach ($result in $ListPemilik )
												#set( $counter = $velocityCount )
												#if ( ($counter % 2) == 0 )
													#set( $row = "row2" )
												#else
													#set( $row = "row1" )
												#end
												<tr>
													<td class="$row" align="center">
													#set ($cnt = ($page - 1) * $itemsPerPage + $counter ) $!cnt</td>
													
                                                        <td class="$row" align="center" style="text-transform:uppercase;">$!result.noKadPengenalan</td>
														<td class="$row" align="center" style="text-transform:uppercase;">$!result.namaPemilikTanah</td>
													
														<td class="$row" align="center" style="text-transform:uppercase;">$!result.alamat1Pemilik $!result.alamat2Pemilik $!result.alamat3Pemilik $!result.namaBandar $!result.namaNegeri</td>
													
													<td class="$row" align="center">
														<a href="javascript:doDeletePemilik('$!result.idPemilik','$!cnt')"><img src="../img/delete.gif" border="0"></a>
												    </td>
													
												</tr>
												#end
											#else
												<tr>
													<td colspan="6">Rekod Tidak Dijumpai</td>
												</tr>
											#end
										</table>
									</td>
								</tr>
		</tbody>
	</table>
					</div>
				</fieldset>
			</td>
		</tr>
        
	</tbody>
</table>
#end

<table id="main_table" width="100%" border="0">
	<tbody>
		<tr>
			<td width="50%" valign="top">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<td valign="top">
								<fieldset id="" name="">
									<legend> Maklumat Pemaju </legend>
									<div align="center">
										<table width="100%" border="0">
											<tbody>
												<tr>
													<td width="50%" valign="top">
														<div align="right"><font color="red">*</font>Nama Pemaju</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td width="50%">
														<input id="namaPemaju" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="namaPemaju" value="$!namaPemaju"></input>
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right">Alamat</div>
													</td>
													<td>:</td>
													<td>
														<span id="alamat1P_2a">
															<input id="alamat1Pemaju" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat1Pemaju"
																value="$!alamat1Pemaju"></input>
														</span>
														<span id="talamat1P_2b" style="display: none;">
															<input id="alamat1Pemaju" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat1Pemaju"
																value="$!alamat1Pemaju"></input>
														</span>
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right"></div>
													</td>
													<td>:</td>
													<td>
														<span id="alamat2P_3a">
															<input id="alamat2Pemaju" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat2Pemaju"
																value="$!alamat2Pemaju"></input>
														</span>
														<span id="alamat2P_3b" style="display: none;">
															<input id="alamat2Pemaju" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat2Pemaju"
																value="$!alamat2Pemaju"></input>
														</span>
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right"></div>
													</td>
													<td>:</td>
													<td>
														<span id="alamat3P_4a">
															<input id="alamat3Pemaju" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat3Pemaju"
																value="$!alamat3Pemaju"></input>
														</span>
														<span id="alamat3P_4b" style="display: none;">
															<input id="alamat3Pemaju" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat3Pemaju"
																value="$!alamat3Pemaju"></input>

														</span>
													</td>

												</tr>

												<tr>
													<td width="50%" valign="top">
														<div align="right">Poskod</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td>
														<input name="poskodPemaju" class="" id="poskodPemaju" size="5" maxlength="5"
															onblur="validateLength(this.value);" type="text" value="$!poskodPemaju">
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right">Negeri</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td>$selectNegeriPemaju</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right">Bandar</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td>$selectBandarPemaju</td>
												</tr>
											</tbody>
										</table>
									</div>
								</fieldset>
							</td>
						</tr>
						<tr></tr>
					</tbody>
				</table>
			</td>
			<td width="50%" valign="top">
				<div align="center">
					<fieldset id="" name="">
						<legend> Maklumat Skim </legend>
						<input id="baca" type="hidden" value="disabled" name="baca"></input>
						<table width="100%" border="0">
							<tbody>
								<tr>
									<td width="50%" valign="top">
										<div align="right"><font color="red">*</font>Nama Skim</div>
									</td>
									<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
									<td width="50%">
										<input id="namaSkim" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="namaSkim" value="$!namaSkim"></input>
									</td>
								</tr>

								<tr>
									<td width="50%" valign="top">
										<div align="right">Alamat</div>
									</td>
									<td>:</td>
									<td>
										<span id="alamat1S_2a">
											<input id="alamat1Skim" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat1Skim" value="$!alamat1Skim"></input>
										</span>
										<span id="alamat1S_2b" style="display: none;">
											<input id="alamat1Skim" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat1Skim" value="$!alamat1Skim"></input>
										</span>
									</td>
								</tr>
								<tr>
									<td width="50%" valign="top">
										<div align="right"></div>
									</td>
									<td>:</td>
									<td>
										<span id="alamat2S_2a">
											<input id="alamat2Skim" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat2Skim" value="$!alamat2Skim"></input>
										</span>
										<span id="alamat2S_2b" style="display: none;">
											<input id="alamat2Skim" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat2Skim" value="$!alamat2Skim"></input>
										</span>
									</td>
								</tr>
								<tr>
									<td width="50%" valign="top">
										<div align="right"></div>
									</td>
									<td>:</td>
									<td>
										<span id="alamat3S_2a">
											<input id="alamat3Skim" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat3Skim" value="$!alamat3Skim"></input>

										</span>
										<span id="alamat3S_2b" style="display: none;">
											<input id="alamat3Skim" type="text" maxlength="80" size="43" style="text-transform:uppercase;" name="alamat3Skim" value="$!alamat3Skim"></input>
										</span>
									</td>
								</tr>
								<tr>
									<td width="50%" valign="top">
										<div align="right">Poskod</div>
									</td>
									<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
									<td>
										<input name="poskodSkim" id="poskodSkim" size="5" maxlength="5" onblur="validateLength(this.value);"
											type="text" value="$!poskodSkim">
									</td>
								</tr>
								<tr>
									<td width="50%" valign="top">
										<div align="right">Negeri</div>
									</td>
									<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
									<td>$selectNegeriSkim</td>
								</tr>
								<tr>
									<td width="50%" valign="top">
										<div align="right">Bandar</div>
									</td>
									<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
									<td>$selectBandarSkim</td>
								</tr>
							</tbody>
						</table>
					</fieldset>
				</div>
			</td>
		</tr>
	</tbody>
</table>
<table id="main_table" width="100%" border="0">
	<tbody>
		<tr>
			<td colspan="2">
				<fieldset>
					<legend>
						<strong>Maklumat Permohonan Strata</strong>
					</legend>
					<div align="left">
						<table border="0" cellpadding="2" cellspacing="2" width="100%">
							<tbody>
								<tr>
                                		#set ($selected3 = "")
										#set ($selected2 = "selected")
										#set ($selected1 = "")
                                        #set ($selected4 = "")
                                        
									#if ($idTblrujstatusstrata == '2')
										#set ($selected2 = "selected")
										#set ($selected1 = "")
										#set ($selected3 = "")
                                        #set ($selected4 = "")
									#elseif ($idTblrujstatusstrata == '1')
										#set ($selected1 = "selected")
										#set ($selected2 = "")
										#set ($selected3 = "")
                                        #set ($selected4 = "")
									#elseif ($idTblrujstatusstrata == '3')
										#set ($selected3 = "selected")
										#set ($selected2 = "")
										#set ($selected1 = "")
                                        #set ($selected4 = "")
                                    #elseif ($idTblrujstatusstrata == '4')
										#set ($selected4 = "selected")
                                        #set ($selected3 = "")
										#set ($selected2 = "")
										#set ($selected1 = "")
                                        
									#end
									<td scope="row" width="5%" align="right">Status Permohonan
                                    </td>
									<td width="1%" scope="row">:</td>
									<td width="20%"> 
										<select id="idTblrujstatusstrata" class="autoselect" onchange="hideShowstatusStrata(this.value)" name="idTblrujstatusstrata">
											<option $selected2 value="2">TIADA STRATA</option>
											<option $selected1 value="1">ADA STRATA</option>
											<option $selected3 value="3">PERMOHONAN</option>
                                            <option $selected4 value="4">PRA PERMOHONAN</option>
										</select>
									</td>
								</tr>
                              
								<tr>       
								<tr id="trTarikhDaftar" style="display:none">
									<td scope="row" width="5%" align="right">Tarikh Daftar</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">
										<input name="tarikhDaftarstrata" id="tarikhDaftarstrata" value="$!tarikhDaftarstrata" onblur="check_date(this);cekTarikhDaftar(this)" size="9">
										<a href="javascript:displayDatePicker('tarikhDaftarstrata',false,'dmy');"><img src="../img/calendar.gif" border="0"></a>
									</td>
								</tr>
								<tr id="trNoRujPtg" style="display:none">
									<td scope="row" width="5%" align="right">
										<div align="right">No Rujukan PTG</div>
									</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">
										<input id="noRujptg" type="text" maxlength="42" size="20" style="text-transform:uppercase;" name="noRujptg" value="$!noRujptg"></input>
									</td>
								</tr>
                             
								<tr id="trTarikhMohonstrata" style="display:none">
									<td scope="row" width="5%" align="right">Tarikh Mohon</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">
										<input name="tarikhMohonstrata" id="tarikhMohonstrata" value="$!tarikhMohonstrata" onblur="check_date(this);cekTarikhMohon(this)" size="9" />
										<a href="javascript:displayDatePicker('tarikhMohonstrata',false,'dmy');"><img src="../img/calendar.gif" border="0"></a>
									</td>
								</tr>
                               <tr id="trTarikhSIFUS" style="display:none">
									<td scope="row" width="5%" align="right">Tarikh SiFus</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">
										<input name="tarikhSifus" id="tarikhSifus" value="$!tarikhSifus" onblur="check_date(this);cekTarikhMohon(this)" size="9" />
										<a href="javascript:displayDatePicker('tarikhSifus',false,'dmy');"><img src="../img/calendar.gif" border="0"></a>
									</td>
								</tr>
                                <tr id="trTarikhCPSP" style="display:none">
									<td scope="row" width="5%" align="right">Tarikh CPSP</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">
										<input name="tarikhCPSP" id="tarikhCPSP" value="$!tarikhCPSP" onblur="check_date(this);cekTarikhMohon(this)" size="9" />
										<a href="javascript:displayDatePicker('tarikhCPSP',false,'dmy');"><img src="../img/calendar.gif" border="0"></a>
									</td>
								</tr>
                                <tr id="trUlasan">
									<td scope="row" width="5%" align="right">Ulasan</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">
										<textarea id="ulasanStrata" maxlength="500" size="30" style="text-transform:uppercase;" name="ulasanStrata"
											value="$!ulasanStrata">$!ulasanStrata</textarea>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</fieldset>
			</td>
		</tr>
	</tbody>
</table>
<table width="100%" border="0">
	<tbody>
		<tr align="center">
			<th scope="col">
           
				#if($mode =='new')
					<input type="button" id="cmdSimpan" onclick="javascript:simpan()" value="Simpan" name="cmdSimpan" />
					<input type="button" id="cmdBatal" onclick="javascript:batal()" value="Batal" />
				#elseif ($mode == 'kemaskini')
					<input type="button" class="stylobutton" name="cmdKemaskini" id="cmdKemaskini" value="Simpan" onclick="javascript:saveUpdate()" />
					<input type="button" id="cmdBatal" onclick="javascript:batalKemaskini()" value="Batal" />
				#end
				
			</th>
		</tr>
	</tbody>
     <input type="hidden" name="idStatus" value="2"/>
   
       
</table>
<script>
	if('$!idTblrujstatusstrata' != "")
		{
			var idSelected = '$!idTblrujstatusstrata';
		if(idSelected=='1'){
		
		document.getElementById('trTarikhDaftar').style.display = '';
		document.getElementById('trNoRujPtg').style.display = '';
		document.getElementById('trTarikhMohonstrata').style.display = 'none';
		document.getElementById('trUlasan').style.display = 'none';
		document.getElementById('trTarikhSIFUS').style.display = 'none';
		document.getElementById('trTarikhCPSP').style.display = 'none';
		document.${formName}.idTblrujstatusstrata.idSelected = '1';
		
	}else if (idSelected=='3'){
		document.getElementById('trTarikhDaftar').style.display = 'none';
		document.getElementById('trNoRujPtg').style.display = 'none';
		document.getElementById('trTarikhMohonstrata').style.display = '';
		document.getElementById('trUlasan').style.display = '';
		document.getElementById('trTarikhSIFUS').style.display = 'none';
		document.getElementById('trTarikhCPSP').style.display = 'none';
		document.${formName}.idTblrujstatusstrata.idSelected = '3';
		

	}else if (idSelected=='4'){
		document.getElementById('trTarikhDaftar').style.display = 'none';
		document.getElementById('trNoRujPtg').style.display = 'none';
		document.getElementById('trTarikhMohonstrata').style.display = 'none';
		document.getElementById('trUlasan').style.display = '';
		document.getElementById('trTarikhSIFUS').style.display = '';
		document.getElementById('trTarikhCPSP').style.display = '';
		document.${formName}.idTblrujstatusstrata.idSelected = '4';

	}else{
		document.getElementById('trTarikhDaftar').style.display = 'none';
		document.getElementById('trNoRujPtg').style.display = 'none';
		document.getElementById('trTarikhMohonstrata').style.display = 'none';
		document.getElementById('trUlasan').style.display = '';
		document.getElementById('trTarikhSIFUS').style.display = 'none';
		document.getElementById('trTarikhCPSP').style.display = 'none';
		document.${formName}.idTblrujstatusstrata.idSelected = '2';
		
		}
	}

</script>
<script>

if(document.${formName}.flagCf.value == 'Y'){
	hideShow('Y');
	//document.${formName}.radioCF1.checked = true;
	//document.${formName}.flagCf.value = 'Y';
	
}else{
	hideShow('T');
	//document.${formName}.radioCF2.checked = true;
	//document.${formName}.flagCf.value = 'T';
}


//hideShowstatusStrata($idTblrujstatusstrata);

function doChangeNegeri(){
	document.${formName}.command.value = "doFilter";
	document.${formName}.submit();
// 	doAjaxCall${formName}("doFilter");
}

function doChangeDaerah(){
	document.${formName}.command.value = "doFilter";
	document.${formName}.submit();
// 	doAjaxCall${formName}("doFilter");
}

function simpan() {
	
	//validation
/*	var idNegeri = document.${formName}.idNegeri.value;
	var idDaerah = document.${formName}.idDaerah.value;
	var idBandarpekanmukim = document.${formName}.idBandarpekanmukim.value;
	var idKodjenishakmilik = document.${formName}.idKodjenishakmilik.value;
	var noHakmilik = document.${formName}.noHakmilik.value;
	var idNegeriStr = document.${formName}.idNegeriStr.value;
	var idLot = document.${formName}.idLot.value;*/
	
	if(document.${formName}.flagStrata.value == ''){
		alert('Sila pilih Skim Bangunan Khas atau bukan Skim Bangunan Khas');
		document.${formName}.flagStrata.focus();
		return;
	}else if(document.${formName}.idNegeri.value == ''){
		alert('Sila pilih Negeri');
		document.${formName}.idNegeri.focus();
		return;
	}else if(document.${formName}.idDaerah.value == ''){
		alert('Sila pilih Daerah');
		document.${formName}.idDaerah.focus();
		return;
	}else if(document.${formName}.idBandarpekanmukim.value == ''){
		alert('Sila pilih Bandar/Pekan/Mukim');
		document.${formName}.idBandarpekanmukim.focus();
		return;
	}else if(document.${formName}.idKodjenishakmilik.value == ''){
		alert('Sila pilih Jenis Hakmilik');
		document.${formName}.idKodjenishakmilik.focus();
		return;
	}else if(document.${formName}.noHakmilik.value == ''){
		alert('Sila masukkan No Hakmilik');
		document.${formName}.noHakmilik.focus();
		return;
	}/*else if(document.${formName}.idNegeriStr.value == ''){
		alert('Sila masukkan Negeri');
		document.${formName}.idNegeriStr.focus();
		return;*/
	else if(document.${formName}.idLot.value == ''){
		alert('Sila masukkan No.Lot');
		document.${formName}.idLot.focus();
		return;
	}else if(document.${formName}.namaPemaju.value == ''){
		alert('Sila masukkan Nama Pemaju');
		document.${formName}.namaPemaju.focus();
		return;
	}else if(document.${formName}.namaSkim.value == ''){
		alert('Sila masukkan Nama Skim');
		document.${formName}.namaSkim.focus();
		return;
	}else{
		document.${formName}.hitButton.value = "simpan";
		document.${formName}.submit();		
	}
	
}

function kemaskini(){
	document.${formName}.hitButton.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function batal(){
	document.${formName}.hitButton.value = "batal";
	document.${formName}.mode.value = "batal";
	document.${formName}.submit();
}

function batalKemaskini(){
	if(document.${formName}.idStrata.value != ''){
		document.${formName}.hitButton.value = "papar";
	}else{
		document.${formName}.hitButton.value = "";
	}
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}

/*function saveUpdate(){
	document.${formName}.mode.value = "kemaskini";
	document.${formName}.hitButton.value = "kemaskiniSimpan";
	document.${formName}.submit();
}*/

function saveUpdate() {
	var exist = false;
	input_box = confirm("Adakah anda pasti untuk Simpan?");
	if (input_box == true){
		if(document.${formName}.flagStrata.value == ''){
			alert('Sila pilih Skim Bangunan Khas atau bukan Skim Bangunan Khas');
			document.${formName}.flagStrata.focus();
			return;
		}else if(document.${formName}.idNegeri.value == ''){
			alert('Sila pilih Negeri');
			document.${formName}.idNegeri.focus();
			return;
		}else if(document.${formName}.idDaerah.value == ''){
			alert('Sila pilih Daerah');
			document.${formName}.idDaerah.focus();
			return;
		}else if(document.${formName}.idBandarpekanmukim.value == ''){
			alert('Sila pilih Bandar/Pekan/Mukim');
			document.${formName}.idBandarpekanmukim.focus();
			return;
		}else if(document.${formName}.idKodjenishakmilik.value == ''){
			alert('Sila pilih Jenis Hakmilik');
			document.${formName}.idKodjenishakmilik.focus();
			return;
		}else if(document.${formName}.noHakmilik.value == ''){
			alert('Sila masukkan No Hakmilik');
			document.${formName}.noHakmilik.focus();
			return;
		}/*else if(document.${formName}.idNegeriStr.value == ''){
			alert('Sila masukkan Negeri');
			document.${formName}.idNegeriStr.focus();
			return;*/
		else if(document.${formName}.idLot.value == ''){
			alert('Sila masukkan No.Lot');
			document.${formName}.idLot.focus();
			return;
		}else if(document.${formName}.namaPemaju.value == ''){
			alert('Sila masukkan Nama Pemaju');
			document.${formName}.namaPemaju.focus();
			return;
		}else if(document.${formName}.namaSkim.value == ''){
			alert('Sila masukkan Nama Skim');
			document.${formName}.namaSkim.focus();
			return;
		}else{
			document.${formName}.mode.value = "kemaskini";
			document.${formName}.hitButton.value = "kemaskiniSimpan";
			document.${formName}.submit();		
		}
	}
}

//function utk hide show radio button Y n N
function hideShow(value) {
	
	if(value == 'T'){
		
		
		
// 		document.getElementById('trNoCf').style.visibility = 'hidden';
// 		document.getElementById('trTarikhCf').style.visibility= 'hidden';
// 		document.getElementById('trNoklsn').style.visibility = 'visible';
		document.getElementById('trNoCf').style.display = 'none';
		document.getElementById('trTarikhCf').style.display = '';
		document.getElementById('trNoklsn').style.display = '';
		document.getElementById('ulasan').style.display = '';
		//document.getElementById('trTarikhNoKelulusanKhas').style.display = '';
		//alert("6");
		document.${formName}.flagCf.value = 'T';;
		
	}else{
		document.getElementById('trNoCf').style.display = '';
		document.getElementById('trTarikhCf').style.display = '';
		document.getElementById('trNoklsn').style.display = 'none';
		document.getElementById('ulasan').style.display = 'none';
		//document.getElementById('trTarikhNoKelulusanKhas').style.display = '';
		document.${formName}.flagCf.value = 'Y';
	}
}

//Function utk hide show drop down list
function hideShowstatusStrata(idSelected){
	if(idSelected=='1'){
		
		document.getElementById('trTarikhDaftar').style.display = '';
		document.getElementById('trNoRujPtg').style.display = '';
		document.getElementById('trTarikhMohonstrata').style.display = 'none';
		document.getElementById('trUlasan').style.display = 'none';
		document.getElementById('trTarikhSIFUS').style.display = 'none';
		document.getElementById('trTarikhCPSP').style.display = 'none';
		document.${formName}.idTblrujstatusstrata.idSelected = '1';

		
	}else if (idSelected=='3'){
		document.getElementById('trTarikhDaftar').style.display = 'none';
		document.getElementById('trNoRujPtg').style.display = 'none';
		document.getElementById('trTarikhMohonstrata').style.display = '';
		document.getElementById('trUlasan').style.display = '';
		document.getElementById('trTarikhSIFUS').style.display = 'none';
		document.getElementById('trTarikhCPSP').style.display = 'none';
		document.${formName}.idTblrujstatusstrata.idSelected = '3';
		

	}else if (idSelected=='4'){
		document.getElementById('trTarikhDaftar').style.display = 'none';
		document.getElementById('trNoRujPtg').style.display = 'none';
		document.getElementById('trTarikhMohonstrata').style.display = 'none';
		document.getElementById('trUlasan').style.display = '';
		document.getElementById('trTarikhSIFUS').style.display = '';
		document.getElementById('trTarikhCPSP').style.display = '';
		document.${formName}.idTblrujstatusstrata.idSelected = '4';
		

	}else{
		//alert("xxxx");
		document.getElementById('trTarikhDaftar').style.display = 'none';
		document.getElementById('trNoRujPtg').style.display = 'none';
		document.getElementById('trTarikhMohonstrata').style.display = 'none';
		document.getElementById('trUlasan').style.display = '';
		document.getElementById('trTarikhSIFUS').style.display = 'none';
		document.getElementById('trTarikhCPSP').style.display = 'none';
		document.${formName}.idTblrujstatusstrata.idSelected = '2';
		
	}
}

function doDelete(id,no) {
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.idCf.value = id;
	document.${formName}.idcfs.value = id;
	document.${formName}.idPemilik.value = id;
	document.${formName}.idpemilik.value = id;
	document.${formName}.cnt.value = no;
	document.${formName}.hitButton.value = "doDelete";
	document.${formName}.submit();
}

function doDeletePemilik(id,no) {
	
if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.idPemilik.value = id;
	//document.${formName}.idpemilik.value = id;
	document.${formName}.cnt.value = no;
	document.${formName}.hitButton.value = "doDeletePemilik";
	document.${formName}.submit();	
}

function doAddCf() {
	//alert ("test");
	document.${formName}.mode.value = "kemaskini";
	document.${formName}.hitButton.value = "doAddCf";
 	document.${formName}.submit();

}

function doAddPemilik() {
	//document.${formName}.mode.value = "kemaskini";
	document.${formName}.mode.value = "kemaskini";
	document.${formName}.hitButton.value = "doAddPemilik";
 	document.${formName}.submit();

}

function copyAlamatPemilik() {
	//document.${formName}.mode.value = "kemaskini";
	
	var checkval = document.getElementById('checkalamatPemilik');
	//alert(" checkval.checked : "+checkval.checked);
	if(checkval.checked == true)
	{	
		document.${formName}.mode.value = "kemaskini";
		document.${formName}.hitButton.value = "copyAlamatPemilik";
		document.${formName}.submit();
	}

}

function myFunction() {
  // Get the checkbox
  var radioBtn = document.getElementById("radio");

  // If the checkbox is checked, display the output text
  if (radioBtn.checked == true){
    document.${formName}.flagStrata.value = "Y";
  } else {
    document.${formName}.flagStrata.value = "T";
  }
}

</script>