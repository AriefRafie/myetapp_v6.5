<script type="text/javascript" src="../../library/js/report.js" ></script>
<fieldset id="headerppk" name="headerppk">
	<legend>
		<strong>Maklumat Pembangunan Strata</strong>
	</legend>
	<p>
		<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
		<input type="hidden" name="hitButton">
		<input type="hidden" name="idStrata" value="$!idStrata">
		<input type="hidden" name="mode">
		<input type="hidden" name="idNegeri" id="idNegeri" value="$!idNegeri">
		<input type="hidden" name="idLot" value="$!idLot" id="idLot">
		<input type="hidden" name="noLot" value="$!noLot" id="noLot">
		<input type="hidden" name="orderBy" id="orderBy">
        <input type="hidden" name="flagStrata" value="$!flagStrata"/>
		
		 
	</p>
	<table width="100%">
		<tbody>
			<tr>
				<td width="50%" valign="top">
					<div align="center">
						<table width="100%" border="0">
							<tbody>
                            <tr>
									<td scope="row" width="21%" align="right">Skim Bangunan Khas?</td>
									<td width="1%" scope="row">:</td>
                                    #if($flagStrata=='Y')
									<td style="text-transform:uppercase;">YA</td>
                                    #elseif($flagStrata=='T')
									<td style="text-transform:uppercase;">TIDAK</td>
                                    #end
									</td>
								</tr>
								<tr>
									<td scope="row" align="right">Negeri</td>
									<td width="1%" scope="row">:</td>
									 #if ($selectNegeriStr == '')
									<td width="58%" style="text-transform:uppercase;">$selectNegeriHM</td>
                                    #else 
                                    <td width="58%" style="text-transform:uppercase;">$selectNegeriStr</td>
                                    #end
								</tr>
								<tr>
									<td scope="row" align="right">Daerah</td>
									<td width="1%" scope="row">:</td>
									<td style="text-transform:uppercase;">$selectDaerah</td>
								</tr>
								<tr>
									<td scope="row" align="right">Bandar/Pekan/Mukim</td>
									<td width="1%" scope="row">:</td>
									<td style="text-transform:uppercase;">$selectMukim</td>
								</tr>
								<tr>
									<td width="41%" scope="row" align="right">No Lot</td>
									<td width="1%" scope="row">:</td>
									<td style="text-transform:uppercase;">
										$selectIdLot
										$!noLot
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
				<td width="50%" valign="top">
					<table width="100%" border="0">
						<tbody>
							<tr>
									<td scope="row" align="right">Jenis Hakmilik</td>
									<td width="2%" scope="row">:</td>
									<td width="51%" style="text-transform:uppercase;">$selectJenisHakmilik</td>
								</tr>
								<tr>
									<td width="47%" scope="row" align="right">No Hakmilik</td>
									<td width="2%" scope="row">:</td>
									<td style="text-transform:uppercase;">$!noHakmilik</td>
								</tr>
                               <tr>
									<td width="47%" scope="row" align="right">Bil Petak</td>
									<td width="2%" scope="row">:</td>
									<td style="text-transform:uppercase;">
									$!bilPetak
									</td>
						  </tr>
                          <tr>
									<td width="47%" scope="row" align="right">Pihak Berkuasa Tempatan</td>
									<td width="2%" scope="row">:</td>
									<td style="text-transform:uppercase;">$!txtSenaraiPBT</td>
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
		
		</script>
    #end    -->
    </div>
</fieldset>

<table width="100%" border="0">
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
									<td scope="row" width="5%" align="right">No Fail Pihak Berkuasa Tempatan (PBT)</td>
									<td width="1%" scope="row">:</td>
									<td width="20%" style="text-transform:uppercase;">$!noFailMajlis</td>
								</tr>
								
                                #if($FlagAktif=='Y')
                                <tr>
									<td scope="row" width="5%" align="right">Ada CF/CCC?</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">YA</td>
								</tr>
								<tr id="trNoCf">
									<td scope="row" width="5%" align="right">No CF</td>
									<td width="1%" scope="row">:</td>
									<td width="20%" style="text-transform:uppercase;">$!noCf</td>
								</tr>
								<tr id="trTarikhCf">
									<td scope="row" align="right">Tarikh CF/CCC/Tarikh No Kelulusan Khas</td>
									<td width="1%" scope="row">:</td>
									<td style="text-transform:uppercase;">$!tarikhCf</td>
								</tr>
                                #else
                                 <tr>
									<td scope="row" width="5%" align="right">Ada CF?</td>
									<td width="1%" scope="row">:</td>
                                    
									<td width="20%" style="text-transform:uppercase;">Tidak</td>
								</tr>
                                <tr id="trTarikhCf">
									<td scope="row" align="right">Tarikh CF/Tarikh No Kelulusan Khas</td>
									<td width="1%" scope="row">:</td>
									<td style="text-transform:uppercase;">$!tarikhCf</td>
								</tr>
								<tr id="trNoklsn">
									<td scope="row" align="right">No Kelulusan Khas</td>
									<td width="1%" scope="row">:</td>
									<td width="20%" style="text-transform:uppercase;">$!noKelulusankhas</td>
								</tr>
                               <!-- <tr id="trTarikhNoKelulusanKhas">
									<td scope="row" align="right">Tarikh No Kelulusan Khas</td>
									<td width="1%" scope="row">:</td>
									<td>$!tarikhNoKelulusanKhas</td>
								</tr>-->
                                <tr id="ulasan">
									<td scope="row" align="right">Ulasan</td>
									<td width="1%" scope="row">:</td>
									<td width="20%" style="text-transform:uppercase;">$!ulasan</td>
								</tr> 
                                #end  
							<!--</tbody>-->
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
									<td colspan="3">
                                    <fieldset>
										<legend>
											<strong>Maklumat Pemilik Tanah</strong>
										</legend>
										<table width="100%" cellspacing="2" cellpadding="1" border="0">
											<tr class="table_header">
											  	<td width="2%" align="center">No</td>
											  	
													<td width="5%"align="center">No Kad Pengenalan / No Pendaftaran</td>	
											  		<td width="5%"align="center">Nama Pemilik Tanah</td>
													<td width="5%"align="center">Alamat</td>	
												
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
													
														<td class="$row" align="center" style="text-transform:uppercase;">$!result.alamat1Pemilik, 
                                                        $!result.alamat2Pemilik, 
                                                        $!result.alamat3Pemilik,
                                                        $!result.namaBandar
                                                        $!result.namaNegeri</td>
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
<table width="100%" border="0">
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
														<div align="right">Nama Pemaju</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td width="50%" style="text-transform:uppercase;">
														$namaPemaju
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right">Alamat</div>
													</td>
													<td>:</td>
													<td style="text-transform:uppercase;">
														<span id="alamat1P_2a">
															$alamat1Pemaju
														</span>
														<span id="talamat1P_2b" style="display: none;">
															$alamat1Pemaju
														</span>
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right"></div>
													</td>
													<td>:</td>
													<td style="text-transform:uppercase;">
														<span id="alamat2P_3a">
															$alamat2Pemaju
														</span>
														<span id="alamat2P_3b" style="display: none;">
															$alamat2Pemaju
														</span>
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right"></div>
													</td>
													<td>:</td>
													<td style="text-transform:uppercase;">
														<span id="alamat3P_4a">
															$alamat3Pemaju
														</span>
														<span id="alamat3P_4b" style="display: none;">
															$alamat3Pemaju
														</span>
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right">Poskod</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td style="text-transform:uppercase;">
														$poskodPemaju
													</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right">Negeri</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td style="text-transform:uppercase;">$selectNegeriPemaju</td>
												</tr>
												<tr>
													<td width="50%" valign="top">
														<div align="right">Bandar</div>
													</td>
													<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
													<td style="text-transform:uppercase;">$selectBandarPemaju</td>
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
										<div align="right">Nama Skim</div>
									</td>
									<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
									<td width="50%" style="text-transform:uppercase;">
										$namaSkim
									</td>
								</tr>

								<tr>
									<td width="50%" valign="top">
										<div align="right">Alamat</div>
									</td>
									<td>:</td>
									<td style="text-transform:uppercase;">
										<span id="alamat1S_2a">
											$alamat1Skim
										</span>
										<span id="alamat1S_2b" style="display: none;">
											$alamat1Skim
										</span>
									</td>
								</tr>
								<tr>
									<td width="50%" valign="top">
										<div align="right"></div>
									</td>
									<td>:</td>
									<td style="text-transform:uppercase;">
										<span id="alamat2S_2a">
											$alamat2Skim
										</span>
										<span id="alamat2S_2b" style="display: none;">
											$alamat2Skim
										</span>
									</td>
								</tr>
								<tr>
									<td width="50%" valign="top">
										<div align="right"></div>
									</td>
									<td>:</td>
									<td style="text-transform:uppercase;">
										<span id="alamat3S_2a">
											$alamat3Skim
										</span>
										<span id="alamat3S_2b" style="display: none;">
											$alamat3Skim
										</span>
									</td>
								</tr>
								<tr>
									<td width="50%" valign="top">
										<div align="right">Poskod</div>
									</td>
									<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
									<td style="text-transform:uppercase;">
										$poskodSkim
									</td>
								</tr>
								<tr>
									<td width="50%" valign="top">
										<div align="right">Negeri</div>
									</td>
									<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
									<td style="text-transform:uppercase;">$selectNegeriSkim</td>
								</tr>
								<tr>
									<td width="50%" valign="top">
										<div align="right">Bandar</div>
									</td>
									<td width="1%" valign="top" style="text-transform: uppercase;">:</td>
									<td style="text-transform:uppercase;">$selectBandarSkim</td>
								</tr>
							</tbody>
						</table>
					</fieldset>
				</div>
			</td>
		</tr>
	</tbody>
</table>
<table width="100%" border="0">
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
									<td scope="row" width="5%" align="right">Status Permohonan</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">
										#if($idTblrujstatusstrata == 1)
											ADA STRATA
										#elseif ($idTblrujstatusstrata == 2)
											TIADA STRATA
										#elseif ($idTblrujstatusstrata == 3)
											PERMOHONAN
										#elseif ($idTblrujstatusstrata == 4)
											PRA PERMOHONAN
                                        #end
									</td>
								</tr>
								<tr>
								<tr id="trTarikhDaftar">
									<td scope="row" width="5%" align="right">Tarikh Daftar</td>
									<td width="1%" scope="row">:</td>
									<td width="20%" style="text-transform:uppercase;">
										$tarikhDaftarstrata
									</td>
								</tr>
								<tr id="trNoRujPtg">
									<td scope="row" width="5%" align="right">
										<div align="right">No Rujukan PTG</div>
									</td>
									<td width="1%" scope="row">:</td>
									<td width="20%" style="text-transform:uppercase;">
										$noRujptg
									</td>
								</tr>
								<tr id="trTarikhMohonstrata">
									<td scope="row" width="5%" align="right">Tarikh Mohon</td>
									<td width="1%" scope="row">:</td>
									<td width="20%" style="text-transform:uppercase;">
										$tarikhMohonstrata
									</td>
								</tr>
                                <tr id="trTarikhSIFUS">
									<td scope="row" width="5%" align="right">
										<div align="right">Tarikh Sifus</div>
									</td>
									<td width="1%" scope="row">:</td>
									<td width="20%" style="text-transform:uppercase;">
										$tarikhSifus
									</td>
                                    </tr>
                                <tr id="trTarikhCPSP">
									<td scope="row" width="5%" align="right">
										<div align="right">Tarikh CPSP</div>
									</td>
									<td width="1%" scope="row">:</td>
									<td width="20%" style="text-transform:uppercase;">
										$tarikhCPSP
									</td>
                                    </tr>
                                 <tr id="trUlasan">
									<td scope="row" width="5%" align="right">
										<div align="right">Ulasan</div>
									</td>
									<td width="1%" scope="row">:</td>
									<td width="20%" style="text-transform:uppercase;">
										$ulasanStrata
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
	
				<input type="button" id="cmdBatal" name="cmdBatal" value="Kembali" onclick="javascript:kembali()"/>

			</th>
		</tr>
	</tbody>

</table>
<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>
<input type="hidden" id="paramUserLogin" name="paramUserLogin" value='$!{session.getAttribute("_portal_login")}' >
<script>

/*
if(!$noCf.isEmpty()){
	hideShow('Y');
}else{
	hideShow('N');
}
*/

hideShowstatusStrata($idTblrujstatusstrata);

// function viewCetak() {

// 	var paramNegeri = document.${formName}.paramNegeri.value;
// 	var paramKodLot = document.${formName}.paramKodLot.value;
// 	var paramNoLot = document.${formName}.paramNoLot.value;
// 	var paramNoCF = document.${formName}.paramNoCF.value;
// 	var paramNamaPemaju = document.${formName}.paramNamaPemaju.value;
// 	var paramNamaSkim = document.${formName}.paramNamaSkim.value;
// 	var paramOrderBy = document.${formName}.paramOrderBy.value;
	
// 	var url = "../x/${securityToken}/ekptg.view.str.FrmPopupSenaraiSkimStrata";//?paramNegeri="+paramNegeri+"&paramKodLot="+paramKodLot+"&paramNoLot="+paramNoLot+"&paramNoCF="+paramNoCF+"&paramNamaPemaju="+paramNamaPemaju+"&paramNamaSkim="+paramNamaSkim;
//     var hWnd = window.open(url,'printuser','width=600,height=300, resizable=yes,scrollbars=yes');
//     if ((document.window != null) && (!hWnd.opener))
//        hWnd.opener = document.window;
//     if (hWnd.focus != null) hWnd.focus();
// 	hWnd.focus();
// }

function viewCetak(){
	//alert(document.${formName}.paramNegeri.value);
	var reportfile = "strSenaraiSkimStrata";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "idNegeri="+document.${formName}.idNegeri.value;
	params[2] = "idLot="+document.${formName}.idLot.value;
	params[3] = "noLot="+document.${formName}.noLot.value;
	params[4] = "txt_userLogin="+document.${formName}.paramUserLogin.value;
	params[5] = "orderBy=D";
	
	printReport(reportfile,params);
}


function simpan() {
	document.${formName}.hitButton.value = "simpan";
	document.${formName}.submit();
}

function kemaskini(){
	document.${formName}.hitButton.value = "kemaskini";
	document.${formName}.idStrata.value = $idStrata;
	document.${formName}.mode.value = "kemaskini";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.hitButton.value = "batal";
	document.${formName}.mode.value = "batal";
	document.${formName}.submit();
}

function saveUpdate(){
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "saveUpdate";
	document.${formName}.submit();
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
</script>