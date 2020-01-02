:::::::::::::;;;;;

<style type="text/css">
<!--
.style2 {
	font-weight: bold
}

.style3 {
	color: #FFFFFF;
	font-style: italic;
}

.style4 {
	color: #0000FF;
	font-style: italic;
}
-->
</style>

#if($readmode == "edit") #set($disabledmode = "") #set($readonlymode
="") #elseif($readmode == "view") #set($disabledmode = "disabled")
#set($readonlymode = "readonly") #else #set($disabledmode = "")
#set($readonlymode = "") #end #if($tambah_kehadiran == "yes" ||
$tambah_kehadiran_wakil == "yes") #set($id_hakmilikpb="")
#set($id_pb="") #set($socJenisPB="") #set($txtNamaPB = "")
#set($txtNamaPBKP = "") #set($socJenisNOPB="") #set($txtNoPB = "")
#set($txtNoAkaun = "") #set($socJenisBank="") #set($txtAlamat1PB = "")
#set($txtAlamat2PB = "") #set($txtAlamat3PB = "") #set($txtPoskodPB =
"") #set($socNegeri = "") #set($socBangsa = "") #set($socWarga = "")
#set($socBandar = "") #set($txtNoHP = "") #set($txtNoTel = "")
#set($txtNamaBank = "") #set($txtKeteranganPB = "") #set($txtSyorAtas =
"") #set($hadir = "") #set($id_kehadiran = "") #set($txtPBhadir = "")
#set($txtCatatan = "") #set($txtUmur = "") #set($boxBorangK = "")
#set($boxBorangE = "") #set($boxBorangG = "") #set($boxBorangC = "")

#set($txtSyorBawah = "") #set($txtSyorAtas = "") #set($txtSyorBawah_temp
= "") #set($txtSyorAtas_temp = "") #set($id_bahagianpb = "")

<!-- 27022012 -->
#set($txtPenamaPenyata = "")

<!--  boxBorangC -->
#if($maklumat_PB_Salin.size() > 0)
#set($socJenisPB=$maklumat_PB_Salin.ID_JENISPB) #set($txtNamaPB =
$maklumat_PB_Salin.NAMA_PB) #set($txtNamaPBKP =
$maklumat_PB_Salin.NAMA_KP)
#set($socJenisNOPB=$maklumat_PB_Salin.ID_JENISNOPB) #set($txtNoPB =
$maklumat_PB_Salin.NO_PB) #set($txtNoAkaun =
$maklumat_PB_Salin.NO_AKAUN)
#set($socJenisBank=$maklumat_PB_Salin.ID_BANK) #set($txtAlamat1PB =
$maklumat_PB_Salin.ALAMAT1) #set($txtAlamat2PB =
$maklumat_PB_Salin.ALAMAT2) #set($txtAlamat3PB =
$maklumat_PB_Salin.ALAMAT3) #set($txtPoskodPB =
$maklumat_PB_Salin.POSKOD) #set($socNegeri =
$maklumat_PB_Salin.ID_NEGERI) #set($socBandar =
$maklumat_PB_Salin.ID_BANDAR) #set($txtNoHP =
$maklumat_PB_Salin.NO_HANDPHONE) #set($txtNoTel =
$maklumat_PB_Salin.NO_TEL_RUMAH) #set($txtNamaBank =
$maklumat_PB_Salin.NAMA_BANK_TXT) #set($txtKeteranganPB =
$maklumat_PB_Salin.KETERANGAN_JENIS_PB) #set($txtCatatan =
$maklumat_PB_Salin.CATATAN) #set($socWarga =
$maklumat_PB_Salin.ID_WARGANEGARA) #set($socBangsa =
$maklumat_PB_Salin.ID_BANGSA) #set($txtPenamaPenyata =
$maklumat_PB_Salin.NAMA_PENYATA_BANK) #set($txtUmur =
$!Utils.parseInt($!maklumat_PB_Salin.UMUR)) #end #end #if($view_pb ==
"yes") #if($maklumat_kehadiran.size() > 0) #foreach($list in
$maklumat_kehadiran) #set($id_hakmilikpb=$list.ID_HAKMILIKPB)
#set($id_pb=$list.ID_PIHAKBERKEPENTINGAN)
#set($socJenisPB=$list.ID_JENISPB) #set($txtNamaPB = $list.NAMA_PB)
#set($txtNamaPBKP = $list.NAMA_KP)
#set($socJenisNOPB=$list.ID_JENISNOPB) #set($txtNoPB = $list.NO_PB)
#set($txtNoAkaun = $list.NO_AKAUN) #set($socJenisBank=$list.ID_BANK)
#set($txtAlamat1PB = $list.ALAMAT1) #set($txtAlamat2PB = $list.ALAMAT2)
#set($txtAlamat3PB = $list.ALAMAT3) #set($txtPoskodPB = $list.POSKOD)
#set($socNegeri = $list.ID_NEGERI) #set($socBandar = $list.ID_BANDAR)
#set($txtNoHP = $list.NO_HANDPHONE) #set($txtNoTel = $list.NO_TEL_RUMAH)
#set($txtNamaBank = $list.NAMA_BANK_TXT) #set($txtKeteranganPB =
$list.KETERANGAN_JENIS_PB) #set($txtCatatan = $list.CATATAN)

#set($socWarga = $list.ID_WARGANEGARA) #set($socBangsa =
$list.ID_BANGSA) #set($txtSyorAtas = $list.SYER_ATAS) #set($txtSyorBawah
= $list.SYER_BAWAH) #set($id_bahagianpb = $list.ID_BAHAGIANPB)

#set($txtSyorAtas_temp = $list.SYER_ATAS) #set($txtSyorBawah_temp =
$list.SYER_BAWAH) #if($list.FLAG_BORANGC != "" ) #set($boxBorangC =
"checked") #else #set($boxBorangC = "") #end #if($list.FLAG_BORANGE !=
"" ) #set($boxBorangE = "checked") #else #set($boxBorangE = "") #end

#if($list.FLAG_BORANGG != "" ) #set($boxBorangG = "checked") #else
#set($boxBorangG = "") #end #if($list.FLAG_BORANGK != "" )
#set($boxBorangK = "checked") #else #set($boxBorangK = "") #end

<!-- 27022012 -->
#set($txtPenamaPenyata = $list.NAMA_PENYATA_BANK)
<!-- 26072012 -->
#set($txtUmur = $!Utils.parseInt($!list.UMUR)) #end #set($hadir = "TIDAK
HADIR") #set($id_kehadiran = "") #set($txtPBhadir = "2")


#if($list_check_kehadiran.size()>0) #foreach($ch in
$list_check_kehadiran) #if($ch.ID_HAKMILIKPB == $id_hakmilikpb &&
$ch.FLAG_HADIR == "1") #set($hadir = "HADIR") #set($txtPBhadir = "1")
#set($id_kehadiran = $ch.ID_KEHADIRAN) #end #end #end #end #end

<table width="100%">

	<tr>
		<td>#parse("app/ppt/frmPPTHeaderHM.jsp")</td>
	</tr>

	<tr>
		<td>
			<table width="100%">
				#if($view_pb == "yes")

				<tr>
					<td>

						<fieldset id="maklumat_pb">

							#if($maklumat_kehadiran.size() > 0) #foreach($list in
							$maklumat_kehadiran) #set($id_hakmilikpb=$list.ID_HAKMILIKPB)
							#set($id_pb=$list.ID_PIHAKBERKEPENTINGAN)
							#set($socJenisPB=$list.ID_JENISPB) #set($txtNamaPB =
							$list.NAMA_PB) #set($txtNamaPBKP = $list.NAMA_KP)
							#set($socJenisNOPB=$list.ID_JENISNOPB) #set($txtNoPB =
							$list.NO_PB) #set($txtNoAkaun = $list.NO_AKAUN)
							#set($socJenisBank=$list.ID_BANK) #set($txtAlamat1PB =
							$list.ALAMAT1) #set($txtAlamat2PB = $list.ALAMAT2)
							#set($txtAlamat3PB = $list.ALAMAT3) #set($txtPoskodPB =
							$list.POSKOD) #set($socNegeri = $list.ID_NEGERI) #set($socBandar
							= $list.ID_BANDAR) #set($txtNoHP = $list.NO_HANDPHONE)
							#set($txtNoTel = $list.NO_TEL_RUMAH) #set($txtNamaBank =
							$list.NAMA_BANK_TXT) #set($txtKeteranganPB =
							$list.KETERANGAN_JENIS_PB) #set($txtCatatan = $list.CATATAN)

							#set($socWarga = $list.ID_WARGANEGARA) #set($socBangsa =
							$list.ID_BANGSA) #set($txtSyorAtas = $list.SYER_ATAS)
							#set($txtSyorBawah = $list.SYER_BAWAH) #set($id_bahagianpb =
							$list.ID_BAHAGIANPB) #set($txtSyorAtas_temp = $list.SYER_ATAS)
							#set($txtSyorBawah_temp = $list.SYER_BAWAH)

							#if($list.FLAG_BORANGC != "" ) #set($boxBorangC = "checked")
							#else #set($boxBorangC = "") #end #if($list.FLAG_BORANGE != "" )
							#set($boxBorangE = "checked") #else #set($boxBorangE = "") #end

							#if($list.FLAG_BORANGG != "" ) #set($boxBorangG = "checked")
							#else #set($boxBorangG = "") #end #if($list.FLAG_BORANGK != "" )
							#set($boxBorangK = "checked") #else #set($boxBorangK = "") #end

							#end #set($hadir = "TIDAK HADIR") #set($id_kehadiran = "")
							#set($txtPBhadir = "2") #if($list_check_kehadiran.size()>0)
							#foreach($ch in $list_check_kehadiran) #if($ch.ID_HAKMILIKPB ==
							$id_hakmilikpb && $ch.FLAG_HADIR == "1") #set($hadir = "HADIR")
							#set($txtPBhadir = "1") #set($id_kehadiran = $ch.ID_KEHADIRAN)
							#end #end #end #end
							<legend>MAKLUMAT ORANG BERKEPENTINGAN</legend>
							 
							<table width="100%">
								<tr>
									<td valign="top"><table width="100%">
											<tr>
												<td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
												<td width="28%">Jenis Pihak Berkepentingan</td>
												<td width="1%">:</td>
												<td width="70%">
													<!-- $list.JENISPB --> #if($readmode == "view" )
													#if($socJenisPB=="") #set($JenisPB="") #else #foreach($ln
													in $list_jenispb) #if($socJenisPB==$ln.ID_JENISPB)
													#set($JenisPB="$ln.KOD_JENIS_PB - $ln.KETERANGAN") #end
													#end #end $!JenisPB <input name="JenisPB" type="hidden"
													id="JenisPB" value="$JenisPB" size="50" readonly
													class="disabled"> #else <select name="socJenisPB"
													class="autoselect" id="socJenisPB"
													onchange="checking_validation(this,'socJenisPB_check','yes','jenis pihak berkepentingan','normal');">
														#if($socJenisPB != "") #foreach($ln in $list_jenispb)
														#if($socJenisPB==$ln.ID_JENISPB)

														<option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB -
															$ln.KETERANGAN</option> #end #end #foreach($ln in $list_jenispb)
														#if($socJenisPB!=$ln.ID_JENISPB && $ln.ID_JENISPB!="42" &&
														$ln.ID_JENISPB!="40" && $ln.ID_JENISPB!="41" &&
														$ln.ID_JENISPB!="27")
														<option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB -
															$ln.KETERANGAN</option> #end #end
														<option value="">SILA PILIH JENIS PB</option> #else

														<option value="">SILA PILIH JENIS PB</option> #foreach($ln
														in $list_jenispb) #if($ln.ID_JENISPB!="42" &&
														$ln.ID_JENISPB!="40" && $ln.ID_JENISPB!="41" &&
														$ln.ID_JENISPB!="27")
														<option value="$ln.ID_JENISPB">$ln.KOD_JENIS_PB -
															$ln.KETERANGAN</option> #end #end #end

												</select> #end <span id="socJenisPB_check" class="alert_msg"></span>
												</td>
											</tr>
											<tr>
												<td></td>
												<td valign="top">Keterangan Jenis PB <font
													style="cursor: help" align="left" class="font2"
													title="info" onMouseOut="close_window()"
													onMouseOver="open_new_window('2');this.style.cursor='help'">
														#parse("app/ppt/infoblink_biru.jsp")</font>
												</td>
												<td valign="top">:</td>
												<td>#if($readmode == "view") $txtKeteranganPB #else <textarea
														name="txtKeteranganPB" id="txtKeteranganPB" cols="50"
														rows="3" style=""
														onBlur="check_length(this,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');"
														onKeyup="check_length(this,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');"
														onKeydown="check_length(this,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');"
														$readonlymode class="$disabledmode">$txtKeteranganPB</textarea>
													#if($readmode == "edit")
													<div>
														<span id="txtKeteranganPB_num" style="color: blue;"></span><span>
															Baki Aksara</span>
													</div> #else <input name="txtKeteranganPB_num"
													id="txtKeteranganPB_num" size="3" value="4000"
													style="display: none"> #end
													<div id="txtKeteranganPB_check" class="alert_msg"></div>


													#end
												</td>
											</tr>
											<tr>
												<td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
												<td>Nama PB</td>
												<td>:</td>
												<td>#if($readmode == "view") $!txtNamaPB <input
													name="txtNamaPB" type="hidden" class="$disabledmode"
													id="txtNamaPB"
													onBlur="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')"
													onKeyUp="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')"
													value="$txtNamaPB" size="50" maxlength="500" $readonlymode>
													<span id="txtNamaPB_check" class="alert_msg"></span> #else

													<input name="txtNamaPB" type="text" class="$disabledmode"
													id="txtNamaPB"
													onBlur="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')"
													onKeyUp="checking_validation(this,'txtNamaPB_check','yes','nama pihak berkepentingan','normal')"
													value="$txtNamaPB" size="50" maxlength="500" $readonlymode>
													#if($id_hakmilikpb == "") <a
													href="javascript:popupCarianPB_salinNama('$id_hakmilik','skrin_salin_pb_sek8')"><font
														color="blue">>> SALIN PB (Nama)</font></a> #end <span
													id="txtNamaPB_check" class="alert_msg"></span>

													#if($id_hakmilikpb != "") #set($lblPA = "Daftar Pemegang
													Amanah") #if($txtUmur != 0 && $txtUmur < 18)
													<div>
														<a
															href="javascript:popupPemegangAmanah('$!id_hakmilik','$id_hakmilikpb');">
															<font color="blue" style='font-size: 11px'><i>$!lblPA</i></font>
														</a>
													</div> #end #end #end
												</td>
											</tr>
											<tr>
												<td></td>
												<td>Nama PB (KP)</td>
												<td>:</td>
												<td>
													<!-- $list.NAMA_KP  --> #if($readmode == "view")
													$!txtNamaPBKP <input name="txtNamaPBKP" type="hidden"
													class="$disabledmode" id="txtNamaPBKP"
													onBlur="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')"
													onKeyUp="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')"
													value="$txtNamaPBKP" size="50" maxlength="500"
													$readonlymode> <span id="txtNamaPBKP_check"
													class="alert_msg"></span> #else <input name="txtNamaPBKP"
													type="text" class="$disabledmode" id="txtNamaPBKP"
													onBlur="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')"
													onKeyUp="checking_validation(this,'txtNamaPBKP_check','no','nama PB seperti dalam KP','normal')"
													value="$txtNamaPBKP" size="50" maxlength="500"
													$readonlymode> <span id="txtNamaPBKP_check"
													class="alert_msg"></span> #end
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>Umur</td>
												<td>:</td>
												<td>
													<!-- $list.NAMA_KP  --> #if($readmode == "view") $!txtUmur
													<input name="txtUmur" type="hidden" id="txtUmur"
													value="$!txtUmur" $readonlymode> #else <input
													name="txtUmur" type="text" class="$disabledmode"
													id="txtUmur" onBlur="validateNumber(this,this.value)"
													onkeyup="validateNumber(this,this.value)" value="$!txtUmur"
													size="5" maxlength="3" $readonlymode> #end
												</td>
											</tr>

											<tr>
												<td></td>
												<td>Nama Atas Penyata Bank</td>
												<td>:</td>
												<td>
													<!-- $list.NAMA_KP  --> #if($readmode == "view")
													$!txtPenamaPenyata <input name="txtPenamaPenyata"
													type="hidden" class="$disabledmode" id="txtPenamaPenyata"
													value="$!txtPenamaPenyata" size="50" maxlength="80"
													$readonlymode> #else <input name="txtPenamaPenyata"
													type="text" class="$disabledmode" id="txtPenamaPenyata"
													value="$!txtPenamaPenyata" size="50" maxlength="80"
													$readonlymode> #end
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>Jenis No. Pengenalan PB</td> 
												<td>:</td>
												<td>
													#if($jenisMode == "capaianIdentity")
														$list_jenisnopb
														<input type="hidden" name="socJenisNOPB"  id="socJenisNOPB" value="$socJenisNOPB" >
													#else
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
																
																		<select name="socJenisNOPB" class="autoselect" id="socJenisNOPB" onchange="checking_validation(this,'socJenisNOPB_check','no','jenis no. pihak berkepentingan','normal_j');"
																		onblur="checking_validation(this,'socJenisNOPB_check','no','jenis no. pihak berkepentingan','normal_j');">
																		
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
																			<option value="">SILA PILIH JENIS NO. PB</option> 
																		#else
																			<option value="">SILA PILIH JENIS NO. PB</option> 
																			#foreach($ln in $list_jenisnopb)
																				<option value="$ln.ID_JENISNOPB">$ln.KOD_JENIS_NOPB - $ln.KETERANGAN</option> 
																			#end 
																		#end
																		</select> 
																
																#end <span id="socJenisNOPB_check" class="alert_msg"></span>
													#end
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>No. Pengenalan PB</td>
												<td>:</td>
												<td>
												#if($readmode == "view") 
												$!txtNoPB 
												<input name="txtNoPB" type="hidden" class="$disabledmode" id="txtNoPB" 
												onBlur="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')"
												onKeyUp="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')"
												value="$txtNoPB" size="50" maxlength="15" $readonlymode>
												<span id="txtNoPB_check" class="alert_msg"></span> 
												
												#else 
												<input name="txtNoPB" type="text" class="$disabledmode" id="txtNoPB"
												onBlur="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')"
												onKeyUp="checking_validation(this,'txtNoPB_check','no','no PB','normal_kp')"
												value="$txtNoPB" size="50" maxlength="15" $readonlymode>
												<span id="txtNoPB_check" class="alert_msg"></span>

												#if($id_hakmilikpb == "") 
												<a href="javascript:popupCarianPB_salinNoKP('$id_hakmilik','skrin_salin_pb_sek8')">
												<font color="blue">>> SALIN PB (MyId)</font></a> #end #end 
												<input type="button" name="cmdCapaian" id="cmdCapaian" value="Capaian myIDENTITY" onClick="viewMyIdentity()">
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>Bangsa</td>
												<td>:</td>
												<td>
													#if($jenisMode == "capaianIdentity")
														$list_bangsa
														<input type="hidden" name="socBangsa"  id="socBangsa" value="$socBangsa" >
													#else
														<!-- $list.NAMA_NEGERI --> #if($readmode == "view" )
																#if($socBangsa=="") #set($Bangsa="") #else #foreach($ln in
																$list_bangsa) #if($socBangsa==$ln.ID_BANGSA)
																#set($Bangsa="$ln.KOD_BANGSA - $ln.KETERANGAN") #end #end
																#end $!Bangsa <input name="Bangsa" type="hidden"
																id="Bangsa" value="$Bangsa" size="50" readonly
																class="disabled"> #else <select name="socBangsa"
																class="autoselect" id="socBangsa"
																onchange="checking_validation(this,'socBangsa_check','no','negeri','normal');"
																onblur="checking_validation(this,'socBangsa_check','no','negeri','normal');">
																	#if($socBangsa != "") #foreach($ln in $list_bangsa)
																	#if($socBangsa==$ln.ID_BANGSA)
																	<option value="$ln.ID_BANGSA">$ln.KOD_BANGSA -
																		$ln.KETERANGAN</option> #end #end #foreach($ln in $list_bangsa)
																	#if($socBangsa!=$ln.ID_BANGSA)
																	<option value="$ln.ID_BANGSA">$ln.KOD_BANGSA -
																		$ln.KETERANGAN</option> #end #end
																	<option value="">SILA PILIH BANGSA</option> #else
			
																	<option value="">SILA PILIH BANGSA</option> #foreach($ln
																	in $list_bangsa)
																	<option value="$ln.ID_BANGSA">$ln.KOD_BANGSA -
																		$ln.KETERANGAN</option> #end #end
			
															</select> #end <span id="socBangsa_check" class="alert_msg"></span>
													#end			
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>Warganegara</td>
												<td>:</td>
												<td>
													#if($jenisMode == "capaianIdentity")
														$list_warga
														<input type="hidden" name="socWarga"  id="socWarga" value="$socWarga" >
													#else
																	<!-- $list.KETERANGAN --> #if($readmode == "view" )
																	#if($socWarga=="") #set($Warga="") #else #foreach($ln in
																	$list_warga) #if($socWarga==$ln.ID_WARGANEGARA)
																	#set($Warga="$ln.KOD_WARGA - $ln.KETERANGAN") #end #end
																	#end $!Warga <input name="Warga" type="hidden" id="Warga"
																	value="$Warga" size="50" readonly class="disabled">
																	#else <select name="socWarga" class="autoselect"
																	id="socWarga"
																	onchange="checking_validation(this,'socWarga_check','no','Warga','normal')"
																	onblur="checking_validation(this,'socWarga_check','no','Warga','normal')">
																		#if($socWarga != "") #foreach($ln in $list_warga)
																		#if($socWarga==$ln.ID_WARGANEGARA)
																		<option value="$ln.ID_WARGANEGARA">$ln.KOD_WARGA
																			- $ln.KETERANGAN</option> #end #end #foreach($ln in $list_warga)
																		#if($socWarga!=$ln.ID_WARGANEGARA)
																		<option value="$ln.ID_WARGANEGARA">$ln.KOD_WARGA
																			- $ln.KETERANGAN</option> #end #end
																		<option value="">SILA PILIH WARGANEGARA</option> #else
				
																		<option value="">SILA PILIH WARGANEGARA</option>
																		#foreach($ln in $list_warga)
																		<option value="$ln.ID_WARGANEGARA">$ln.KOD_WARGA
																			- $ln.KETERANGAN</option> #end #end
				
																</select> #end <span id="socWarga_check" class="alert_msg"></span>
													#end
												</td>
											</tr>

											#set($countX = 0) #foreach($list1 in
											$senarai_pihak_penting_bahagian) #if($list1.ID_BAHAGIANPB ==
											$id_bahagianpb && $list1.ID_HAKMILIKPB != $id_hakmilikpb )
											#set($countX=$countX + 1) #end #end #if($countX > 0 ||
											$list_kehadiran_size > 0 )

											<tr>
												<td>&nbsp;</td>
												<td>Berkongsi Bahagian (Jika Ada)</td>
												<td>:</td>
												<td>#if($id_bahagianpb == "" && $readmode == "view" ) <input
													type="hidden" name="id_bahagianpb" id="id_bahagianpb"
													value="$id_bahagianpb"> #elseif($id_bahagianpb !=
													"" && $readmode == "view" ) <input type="hidden"
													name="id_bahagianpb" id="id_bahagianpb"
													value="$id_bahagianpb"> #set($count = 0)
													#foreach($list1 in $senarai_pihak_penting_bahagian)
													#if($list1.ID_BAHAGIANPB == $id_bahagianpb &&
													$list1.ID_HAKMILIKPB != $id_hakmilikpb ) #set($count=$count
													+ 1) #end #end #if($count > 0)

													<div style="width: 70%">#set($count_total = 0)
														#foreach($list1 in $senarai_pihak_penting_bahagian)
														#if($list1.ID_BAHAGIANPB == $id_bahagianpb &&
														$list1.ID_HAKMILIKPB != $id_hakmilikpb)
														#set($count_total=$count_total + 1) #if($count > 1 &&
														$count != $count_total && $count_total != $count - 1 )
														$list1.NAMA_PB, #elseif($count > 1 && $count !=
														$count_total && $count_total == $count - 1) $list1.NAMA_PB
														& #elseif($count > 1 && $count == $count_total)
														$list1.NAMA_PB #elseif($count == 1) $list1.NAMA_PB #end
														#end #end</div> #end #else

													#if($senarai_pihak_penting_bahagian.size() > 0) <select
													name="id_bahagianpb" class="autoselect" id="id_bahagianpb"
													onchange="getSyer('$!id_hakmilikpb','$!id_pembatalan')">
														#if($id_bahagianpb != "") #foreach($ln in
														$senarai_pihak_penting_bahagian)
														#if($id_bahagianpb==$ln.ID_BAHAGIANPB && $ln.ID_HAKMILIKPB
														!= $id_hakmilikpb)
														<option value="$ln.ID_BAHAGIANPB">$ln.NAMA_PB</option>
														#end #end

														<option value="">SILA PILIH PB</option> #foreach($ln in
														$senarai_pihak_penting_bahagian)
														#if($id_bahagianpb!=$ln.ID_BAHAGIANPB && $ln.ID_HAKMILIKPB
														!= $id_hakmilikpb)
														<option value="$ln.ID_BAHAGIANPB">$ln.NAMA_PB</option>
														#end #end #else

														<option value="">SILA PILIH PB</option> #foreach($ln in
														$senarai_pihak_penting_bahagian)
														<option value="$ln.ID_BAHAGIANPB">$ln.NAMA_PB</option>
														#end #end

												</select> #else <input type="hidden" name="id_bahagianpb"
													id="id_bahagianpb" value="$id_bahagianpb"> #end

													#end
												</td>
											</tr>

											#end

											<tr>
												<td></td>
												<td>Bahagian / Syer</td>

												<td>:</td>
												<td>
													<div id="getSyer_div"></div> #if($readmode == "view")
													#if($txtSyorBawah != "" && $!txtSyorAtas != "")
													$!txtSyorAtas / $!txtSyorBawah #end #else <input
													type="text" name="txtSyorAtas" id="txtSyorAtas"
													value="$!txtSyorAtas" size="20" maxlength="20"
													onkeyup="validateNumber(this,this.value)"
													onblur="validateSyer('$!checkSizeBahagianPB_size')">
													&nbsp;<b>/</b>&nbsp;<input type="text" name="txtSyorBawah"
													id="txtSyorBawah" value="$!txtSyorBawah" size="20"
													maxlength="20"
													onblur="validateSyer('$!checkSizeBahagianPB_size')"
													onkeyup="validateNumber(this,this.value)"> <input
													type="hidden" name="txtSyorAtas_temp" id="txtSyorAtas_temp"
													value="$!txtSyorAtas_temp"> <input type="hidden"
													name="txtSyorBawah_temp" id="txtSyorBawah_temp"
													value="$!txtSyorBawah_temp"> #end
												</td>
											</tr>

											<tr>
												<td width="1%">&nbsp;</td>
												<td width="28%">No. HP</td>
												<td width="1%">:</td>
												<td width="70%">
													<!-- $list.NO_AKAUN --> #if($readmode == "view") $!txtNoHP
													<input name="txtNoHP" type="hidden" class="$disabledmode"
													id="txtNoHP"
													onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')"
													onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')"
													value="$txtNoHP" size="50" maxlength="15" $readonlymode>
													<span id="txtNoHP_check" class="alert_msg"></span> #else <input
													name="txtNoHP" type="text" class="$disabledmode"
													id="txtNoHP"
													onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')"
													onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoHP_check','no','no telefon bimbit','normal')"
													value="$txtNoHP" size="50" maxlength="12" $readonlymode>
													<span id="txtNoHP_check" class="alert_msg"></span> #end
												</td>
											</tr>
											<tr>
												<td width="1%">&nbsp;</td>
												<td width="28%">No. Tel (R/P)</td>
												<td width="1%">:</td>
												<td width="70%">
													<!-- $list.NO_AKAUN --> #if($readmode == "view") $!txtNoTel
													<input name="txtNoTel" type="hidden" class="$disabledmode"
													id="txtNoTel"
													onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')"
													onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')"
													value="$txtNoTel" size="50" maxlength="15" $readonlymode>
													<span id="txtNoTel_check" class="alert_msg"></span> #else <input
													name="txtNoTel" type="text" class="$disabledmode"
													id="txtNoTel"
													onBlur="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')"
													onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtNoTel_check','no','no telefon rumah / pejabat','normal')"
													value="$txtNoTel" size="50" maxlength="12" $readonlymode>
													<span id="txtNoTel_check" class="alert_msg"></span> #end
												</td>
											</tr>



											<tr>
												<td>&nbsp;</td>
												<td>Nama Bank</td>
												<td>:</td>
												<td>#if($readmode == "view") $!txtNamaBank <input
													name="txtNamaBank" type="hidden" class="$disabledmode"
													id="txtNamaBank"
													onBlur="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')"
													onKeyUp="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')"
													value="$txtNamaBank" size="50" maxlength="350"
													$readonlymode> <span id="txtNamaPB_check"
													class="alert_msg"></span> #else <input name="txtNamaBank"
													type="text" class="$disabledmode" id="txtNamaBank"
													onBlur="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')"
													onKeyUp="checking_validation(this,'txtNamaBank_check','no','nama bank','normal')"
													value="$txtNamaBank" size="50" maxlength="350"
													$readonlymode> <span id="txtNamaBank_check"
													class="alert_msg"></span>
												</td>
											</tr>
											#end

											<tr>
												<td width="1%">&nbsp;</td>
												<td width="28%">No. Akaun</td>
												<td width="1%">:</td>
												<td width="70%">
													<!-- $list.NO_AKAUN --> #if($readmode == "view")
													$txtNoAkaun <input name="txtNoAkaun" type="hidden"
													class="$disabledmode" id="txtNoAkaun"
													onBlur="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')"
													onKeyUp="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')"
													value="$txtNoAkaun" size="50" maxlength="20" $readonlymode>
													<span id="txtNoAkaun_check" class="alert_msg"></span> #else
													<input name="txtNoAkaun" type="text" class="$disabledmode"
													id="txtNoAkaun"
													onBlur="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')"
													onKeyUp="checking_validation(this,'txtNoAkaun_check','no','no akaun','normal')"
													value="$txtNoAkaun" size="50" maxlength="20" $readonlymode>
													<span id="txtNoAkaun_check" class="alert_msg"></span> #end
												</td>
											</tr>



											<tr>
												<td>&nbsp;</td>
												<td>Alamat PB</td>
												<td>:</td>
												<td>
													<!-- $list.ALAMAT1 --> #if($readmode == "view")
													$txtAlamat1PB <input name="txtAlamat1PB" type="hidden"
													id="txtAlamat1PB" value="$txtAlamat1PB" size="50"
													onBlur="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')"
													onKeyUp="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')"
													$readonlymode class="$disabledmode"> <span
													id="txtAlamat1PB_check" class="alert_msg"></span> #else <input
													name="txtAlamat1PB" type="text" id="txtAlamat1PB"
													value="$txtAlamat1PB" size="50"
													onBlur="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')"
													onKeyUp="checking_validation(this,'txtAlamat1PB_check','no','alamat PB','normal')"
													$readonlymode class="$disabledmode"> <span
													id="txtAlamat1PB_check" class="alert_msg"></span> #end
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>:</td>
												<td>
													<!-- $list.ALAMAT2 --> #if($readmode == "view")
													$txtAlamat2PB <input name="txtAlamat2PB" type="hidden"
													id="txtAlamat2PB" value="$txtAlamat2PB" size="50" onBlur=""
													onKeyUp="" $readonlymode class="$disabledmode">
													#else <input name="txtAlamat2PB" type="text"
													id="txtAlamat2PB" value="$txtAlamat2PB" size="50" onBlur=""
													onKeyUp="" $readonlymode class="$disabledmode">
													#end
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>:</td>
												<td>
													<!-- $list.ALAMAT3 --> #if($readmode == "view")
													$txtAlamat3PB <input name="txtAlamat3PB" type="hidden"
													id="txtAlamat3PB" value="$txtAlamat3PB" size="50" onBlur=""
													onKeyUp="" $readonlymode class="$disabledmode">

													#else <input name="txtAlamat3PB" type="text"
													id="txtAlamat3PB" value="$txtAlamat3PB" size="50" onBlur=""
													onKeyUp="" $readonlymode class="$disabledmode">
													#end
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>Poskod</td>
												<td>:</td>
												<td>
													<!-- $list.POSKOD --> #if($readmode == "view") $txtPoskodPB
													<input name="txtPoskodPB" type="hidden" id="txtPoskodPB"
													onBlur="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)"
													onKeyUp="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)"
													value="$txtPoskodPB" size="6" maxlength="5" $readonlymode
													class="$disabledmode"> <span id="txtPoskodPB_check"
													class="alert_msg"></span> #else <input name="txtPoskodPB"
													type="text" id="txtPoskodPB"
													onBlur="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)"
													onKeyUp="checking_validation(this,'txtPoskodPB_check','no','','poskod');validateTarikh(this,this.value)"
													value="$txtPoskodPB" size="6" maxlength="5" $readonlymode
													class="$disabledmode"> <span id="txtPoskodPB_check"
													class="alert_msg"></span> #end
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>Negeri</td>
												<td>:</td>
												<td>
													#if($jenisMode == "capaianIdentity")
														$list_negeri
														<input type="hidden" name="socNegeri"  id="socNegeri" value="$socNegeri" >
													#else
																		<!-- $list.NAMA_NEGERI --> #if($readmode == "view" )
																		#if($socNegeri=="") #set($Negeri="") #else #foreach($ln in
																		$list_negeri) #if($socNegeri==$ln.ID_NEGERI)
																		#set($Negeri="$ln.KOD_NEGERI - $ln.NAMA_NEGERI") #end #end
																		#end $!Negeri <input name="Negeri" type="hidden"
																		id="Negeri" value="$Negeri" size="50" readonly
																		class="disabled"> #else <select name="socNegeri"
																		class="autoselect" id="socNegeri"
																		onchange="checking_validation(this,'socNegeri_check','no','negeri','normal');getBandar('$!id_hakmilikpb')"
																		onblur="checking_validation(this,'socNegeri_check','no','negeri','normal');getBandar('$!id_hakmilikpb')">
					
																			#if($socNegeri != "") #foreach($ln in $list_negeri)
																			#if($socNegeri==$ln.ID_NEGERI)
					
																			<option value="$ln.ID_NEGERI">$ln.KOD_NEGERI -
																				$ln.NAMA_NEGERI</option> #end #end #foreach($ln in $list_negeri)
																			#if($socNegeri!=$ln.ID_NEGERI)
					
																			<option value="$ln.ID_NEGERI">$ln.KOD_NEGERI -
																				$ln.NAMA_NEGERI</option> #end #end
					
																			<option value="">SILA PILIH NEGERI</option> #else
					
					
																			<option value="">SILA PILIH NEGERI</option> #foreach($ln
																			in $list_negeri)
					
																			<option value="$ln.ID_NEGERI">$ln.KOD_NEGERI -
																				$ln.NAMA_NEGERI</option> #end #end
					
					
																	</select> #end <span id="socNegeri_check" class="alert_msg"></span>
													#end
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>Bandar</td>
												<td>:</td>
												<td>
													#if($jenisMode == "capaianIdentity")
														$list_bandar
														<input type="hidden" name="socBandar"  id="socBandar" value="$socBandar" >
													#else
																	<!-- $list.NAMA_BANDAR --> #if($readmode == "view" )
				
																	#if($socBandar=="") #set($Bandar="") #else #foreach($lb in
																	$list_bandar) #if($socBandar==$lb.ID_BANDAR)
																	#set($Bandar="$lb.KOD_BANDAR - $lb.NAMA_BANDAR") #end #end
																	#end $!Bandar <input name="Bandar" type="hidden"
																	id="Bandar" value="$Bandar" size="50" readonly
																	class="disabled"> #else <select name="socBandar"
																	class="autoselect" id="socBandar"
																	onchange="checking_validation(this,'socBandar_check','no','bandar','normal');">
																		#if($socBandar != "") #foreach($lb in $list_bandar)
																		#if($socBandar==$lb.ID_BANDAR)
																		<option value="$lb.ID_BANDAR">$lb.KOD_BANDAR -
																			$lb.NAMA_BANDAR</option> #end #end #foreach($lb in $list_bandar)
																		#if($socBandar!=$lb.ID_BANDAR)
																		<option value="$lb.ID_BANDAR">$lb.KOD_BANDAR -
																			$lb.NAMA_BANDAR</option> #end #end
																		<option value="">SILA PILIH BANDAR</option> #else
				
																		<option value="">SILA PILIH BANDAR</option> #foreach($lb
																		in $list_bandar)
																		<option value="$lb.ID_BANDAR">$lb.KOD_BANDAR -
																			$lb.NAMA_BANDAR</option> #end #end
				
																</select> #end <span id="socBandar_check" class="alert_msg"></span>
													#end
												</td>
											</tr>


											<tr>
												<td></td>
												<td valign="top">Catatan</td>
												<td valign="top">:</td>
												<td>#if($readmode == "view") $txtCatatan #else <textarea
														name="txtCatatan" id="txtCatatan" cols="50" rows="6"
														style=""
														onBlur="check_length(this,'4000','txtCatatan_check','txtCatatan_num','normal','no','catatan');"
														onKeyup="check_length(this,'4000','txtCatatan_check','txtCatatan_num','normal','no','catatan');"
														onKeydown="check_length(this,'4000','txtCatatan_check','txtCatatan_num','normal','no','catatan');"
														$readonlymode class="$disabledmode">$txtCatatan</textarea>


													#if($readmode == "edit")
													<div>
														<span id="txtCatatan_num" style="color: blue;"></span><span>
															Baki Aksara</span>
													</div> #else <input name="txtCatatan_num" id="txtCatatan_num"
													size="3" value="4000" style="display: none"> #end
													<div id="txtCatatan_check" class="alert_msg"></div> #end
												</td>
											</tr>

											<tr>
												<td></td>
												<td></td>
												<td>&nbsp;</td>
												<td>

													<fieldset style="width: 50%">
														<legend> Pilihan Borang </legend>
														<table width="100%">
															<tr>
																<td colspan="4"></td>
															</tr>
															<tr>
																<td colspan="4"></td>
															</tr>
															<tr>
																<td width="25%">Borang C & D</td>
																<td width="1%">:</td>
																<td width="24%">#if($readmode == "edit") <input
																	name="boxBorangC" id="boxBorangC" type="checkbox"
																	$boxBorangC> #elseif($readmode == "view")
																	#if($boxBorangC == "checked") <img
																	src="../img/validyes.png" alt="" border="0" /> #else <img
																	src="../img/validno.png" alt="" border="0" /> #end
																	#end
																</td>
															</tr>
															<tr>
																<td>Borang E & F</td>
																<td>:</td>
																<td>#if($readmode == "edit") <input
																	name="boxBorangE" id="boxBorangE" type="checkbox"
																	$boxBorangE> #elseif($readmode == "view")
																	#if($boxBorangE == "checked") <img
																	src="../img/validyes.png" alt="" border="0" /> #else <img
																	src="../img/validno.png" alt="" border="0" /> #end
																	#end
																</td>
															</tr>
															<tr>
																<td>Borang G & H</td>
																<td>:</td>
																<td>#if($readmode == "edit") <input
																	name="boxBorangG" id="boxBorangG" type="checkbox"
																	$boxBorangG> #elseif($readmode == "view")
																	#if($boxBorangG == "checked") <img
																	src="../img/validyes.png" alt="" border="0" /> #else <img
																	src="../img/validno.png" alt="" border="0" /> #end
																	#end
																</td>
															</tr>
															<tr>
																<td>Borang K</td>
																<td>:</td>
																<td>#if($readmode == "edit") <input
																	name="boxBorangK" id="boxBorangK" type="checkbox"
																	$boxBorangK> #elseif($readmode == "view")
																	#if($boxBorangK == "checked") <img
																	src="../img/validyes.png" alt="" border="0" /> #else <img
																	src="../img/validno.png" alt="" border="0" /> #end
																	#end
																</td>
															</tr>
															<tr>
																<td colspan="4"></td>
															</tr>
														</table>
													</fieldset>
												</td>
											</tr>

										</table></td>
								</tr>
								<tr>
									<td colspan="2">
										<div align="center">
											<input type="button" name="cmdJIM"
												value="Semakan Maklumat Kebangkrapan"
												onClick="javascript:semakanJIM('$!id_pb', '$!id_permohonan','$!txtNoPb','$!txtNamaPBKP')"><br />
											#if($readmode == "view") <label> <input type="button"
												name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini"
												onClick="kemaskinipb('$id_hakmilikpb')">
											</label> <label> #if($socJenisPB == "40" || $socJenisPB ==
												"41" || $socJenisPB == "27" || $socJenisPB == "42" ||
												$socJenisPB == "6") #end <label> <input
													type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus"
													onClick="hapusTurutHadir('$id_hakmilikpb','$id_pb','$id_kehadiran')">
											</label> <!--
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">
      -->
											</label> #end #if($readmode == "edit") <label> <input
												type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"
												onClick="simpanPB('$id_hakmilik','$id_hakmilikpb','$id_kehadiran','$id_pb','$checkSizeBahagianPB_size')">
											</label> <label> <input type="button" name="cmdBatal"
												id="cmdBatal" value="Batal"
												onClick="batal('$id_hakmilikpb')">
											</label> #end #if($id_pembatalan != "") <label> <!-- 
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="" >
      -->
											</label> #end
										</div>
										<div class="TabbedPanelsContent"></div>
									</td>
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
						</fieldset>
					</td>
				</tr>




				<tr>
					<td>

						<fieldset id="senarai_siasatan">
							<legend>
								SENARAI PIHAK BERKEPENTINGAN <input type="button"
									value="Tambah Pihak Berkepentingan" onClick="tambahWakil()">
								#parse('app/ppt/ButtonJanaPB.jsp') <input type="button"
									name="cmdKembali" value="Kembali"
									onClick="javascript:viewHM('$!id_hakmilik')">
							</legend>
							<a
								href="javascript:popupCarianPB('$id_hakmilik','skrin_pb_sek8','$id_hakmilikpb')"><font
								color="blue">>> SKRIN CAPAIAN PIHAK BERKEPENTINGAN</font></a>

							<!--
        #if($list_kehadiran_size > 15)
        <div style="width:100%;height:100;overflow:auto"> 
        #end
      -->
							<table width="100%" style="display: none">
								<tr>
									<td colspan="8">

										<table width="100%">
											<tr>

												<td align="left" width="50%" valign="top"><input
													type="button" name="cmdKembali" value="Kembali"
													onClick="javascript:viewHM('$!id_hakmilik')">
													#parse('app/ppt/ButtonJanaPB.jsp') <input type="button"
													value="Tambah Pihak Berkepentingan" onClick="tambahWakil()">
													#if($list_kehadiran_size > 0) <input type="button"
													value="Simpan Pilihan Borang" onClick="Simpan_Borang()">
													#end #if($list_kehadiran_size!=0) <!-- <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" /> -->
													#end</td>
												<td width="50%" align="right" valign="top">
													#if($list_kehadiran_size!=0)Nama / No. KP PB : <label>
														<input name="CariPB" type="text" id="CariPB"
														value="$!CariPB" size="20" maxlength="150" />
												</label> <label> <input type="button" name="ButtonCariLot"
														id="ButtonCariLot" value="Cari"
														onClick="PB('$id_hakmilik')" />
												</label> <label> <input type="button" name="ButtonCariLot"
														id="ButtonCariLot" value="Kosongkan"
														onClick="PB_Kosong('$id_hakmilik')" />
												</label>

													<div style="display: none">
														Kehadiran PB : <span id="jumlahlot"></span>
													</div> #end
												</td>
											</tr>
										</table>


									</td>
								</tr>
								#if($list_kehadiran_size > 0)
								<tr class="table_header">
									<td width="3%" align="center"><b>Bil</b></td>
									<td width="27%"><b>Nama Pihak Berkepentingan</b></td>
									<td width="24%"><div align="left">
											<b>Jenis Pihak Berkepentingan</b>
										</div></td>
									<td width="14%"><div align="center">
											<b>Bahagian / Syer</b>
										</div>
										<div align="center">
											<b> & </b>
										</div>
										<div align="center">
											<b>Berkongsi Bahagian</b>
										</div>
										<div align="center">
											<b>(Jika Ada)</b>
										</div></td> #if($list_kehadiran_size > 0)
									<td width="8%">
										<div align="center">
											<b>Borang</b>
										</div>
										<div align="center">
											<b>C & D</b>
										</div>
										<div align="center">

											<input type="checkbox" name="all1" id="all1"
												onClick="doCheckAll1('$list_kehadiran_size');"
												title="Semak untuk menunjukkan semua PB disenaraikan di Borang C & D" />
										</div>
									</td> #end


									<td width="8%">
										<div align="center">
											<b>Borang</b>
										</div>
										<div align="center">
											<b>E & F</b>
										</div>
										<div align="center">
											<input type="checkbox" name="allborangE" id="allborangE"
												onClick="doCheckAllBorangE('$list_kehadiran_size');"
												title="Semak untuk menunjukkan semua PB disenaraikan di Borang E & F" />
										</div>
									</td>


									<td width="8%">
										<div align="center">
											<b>Borang</b>
										</div>
										<div align="center">
											<b>G & H</b>
										</div>
										<div align="center">
											<input type="checkbox" name="allborangG" id="allborangG"
												onClick="doCheckAllBorangG('$list_kehadiran_size');"
												title="Semak untuk menunjukkan semua PB disenaraikan di Borang G & H" />
										</div>
									</td>


									<td width="8%">
										<div align="center">
											<b>Borang</b>
										</div>
										<div align="center">
											<b>K</b>
										</div>
										<div align="center">
											<input type="checkbox" name="allborangK" id="allborangK"
												onClick="doCheckAllBorangK('$list_kehadiran_size');"
												title="Semak untuk menunjukkan semua PB disenaraikan di Borang K" />
										</div>
									</td>

									<!-- <td width="2%">
       <div align="center" class="font1"><font style="cursor:help" title="info"  onMouseOut="close_window()"   onMouseOver="open_new_window('1');this.style.cursor='help'" >
       #parse("app/ppt/infoblink_putih.jsp")
       </font>                                 </div>      </td> -->


								</tr>
								#else
								<tr class="table_header">
									<td width="3%">Bil</td>

									<td width="40%">Nama Pihak Berkepentingan</td>


									<td width="23%"><div align="left">Jenis Pihak
											Berkepentingan</div></td>
									<td width="13%"><div align="center">Bahagian / Syer</div>
										<div align="center">&</div>
										<div align="center">Berkongsi Bahagian (Jika Ada)</div></td>
									#if($list_kehadiran_size > 0)
									<td width="8%">
										<div align="center">Borang</div>
										<div align="center">C & D</div>
										<div align="center"></div>
									</td> #end


									<td width="8%">
										<div align="center">Borang</div>
										<div align="center">E & F</div>
										<div align="center"></div>
									</td>


									<td width="8%">
										<div align="center">Borang</div>
										<div align="center">G & H</div>
										<div align="center"></div>
									</td>


									<td width="8%">
										<div align="center">Borang</div>
										<div align="center">K</div>
										<div align="center"></div>
									</td>

									<!-- <td width="2%">
       <div align="center" class="font1"><font style="cursor:help" title="info" onMouseOut="close_window()"  onMouseOver="open_new_window('1');this.style.cursor='help'" >
     #parse("app/ppt/infoblink_putih.jsp")
       </font>                                 </div>      </td> -->

								</tr>

								#end #if($list_kehadiran_size > 0) #foreach($list in
								$list_kehadiran) #set( $i = $velocityCount ) #if ( ($i % 2) != 1
								) #set( $row = "row2" ) #else #set( $row = "row1" ) #end

								<tr>
									<td colspan="5">

										<fieldset id="$list.BILTEMP" class="$row"
											style="visibility: collapse; display: none;">
											<table width="100%">
												<tr>
													<td width="1%">&nbsp;</td>

													<td width="99%"><div align="left">
															<a href="javascript:paparpb('$list.ID_HAKMILIKPB');"
																title="Klik untuk maklumat lengkap pihak berkepentingan"><font
																color="blue">KEMASKINI MAKLUMAT PB</font></a>
														</div></td>
												</tr>
											</table>
											<table width="100%">
												<tr>
													<td width="50%" valign="top"><table width="100%">
															<tr>
																<td width="1%">&nbsp;</td>
																<td width="28%">Jenis Pihak Berkepentingan</td>
																<td width="1%">:</td>
																<td width="70%">$list.JENISPB</td>
															</tr>
															<tr>
																<td>&nbsp;</td>
																<td>Kehadiran</td>
																<td>:</td>
																<td>#set($hadir = "TIDAK HADIR")
																	#if($list_check_kehadiran.size()>0) #foreach($ch in
																	$list_check_kehadiran) #if($ch.ID_HAKMILIKPB ==
																	$list.ID_HAKMILIKPB && $ch.FLAG_HADIR == "1")
																	#set($hadir = "HADIR") #end #end #end $hadir</td>
															</tr>
															<tr>
																<td>&nbsp;</td>
																<td>Nama PB</td>
																<td>:</td>
																<td>$list.NAMA_PB</td>
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
										</fieldset>

									</td>
								</tr>
								<tr class="$row">
									<td align="center">$list.BIL</td>

									<td id="$list.NO_PB">
										<div>
											<a href="javascript:paparpb('$list.ID_HAKMILIKPB');"
												title="Klik untuk maklumat lengkap pihak berkepentingan"><font
												color="blue">$list.NAMA_PB</font></a>
										</div>
										<div>$list.KETERANGAN_JENIS_PB</div> <!--
      <div align="left"><a href="javascript:ShowPopupKehadiran('$list.BILTEMP');" title="Klik untuk maklumat lengkap pihak berkepentingan"><font color="blue">$list.NAMA_PB</font></a></div>
   -->

									</td>

									<td>
										<!--#* 
        #set($lblPA = "Daftar Pemegang Amanah")
    	#if($list.ID_PA1 != "")
    		#set($lblPA = $list.NAMA_PA1+" (Pemegang Amanah)")
    	#end
    		
    	<div align="left">$list.JENISPB</div>
    	#if($list.UMUR != 0 && $list.UMUR < 18)
    	<div><a href="javascript:popupPemegangAmanah('$!list.ID_HAKMILIK','$list.ID_HAKMILIKPB');">
    	<font color="blue" style='font-size:11px'><i>$!lblPA</i></font></a></div>
    	#end
        *# --> #set($lblPA = "Daftar Pemegang Amanah") <!-- #* #if($list.ID_PA1 != "")
    			#set($lblPA = $list.NAMA_PA1+" (Pemegang Amanah)")
    		#end 
            
            *# -->
										<div align="left">$list.JENISPB</div> #if($list.UMUR != 0 &&
										$list.UMUR < 18)
										<div>
											<a
												href="javascript:popupPemegangAmanah('$!list.ID_HAKMILIK','$list.ID_HAKMILIKPB');">
												<font color="blue" style='font-size: 11px'><i>$!lblPA</i></font>
											</a>
										</div> #end
									</td>

									<td width="13%"><div align="center">
											#if($list.SYER_ATAS != "" && $list.SYER_BAWAH != "")
											$list.SYER_ATAS / $list.SYER_BAWAH #end</div> #set($count = 0)
										#foreach($list1 in $senarai_pihak_penting_bahagian)
										#if($list1.ID_BAHAGIANPB == $list.ID_BAHAGIANPB &&
										$list1.ID_HAKMILIKPB != $list.ID_HAKMILIKPB )
										#set($count=$count + 1) #end #end #if($count > 0)

										<div>#set($count_total = 0) #foreach($list1 in
											$senarai_pihak_penting_bahagian) #if($list1.ID_BAHAGIANPB ==
											$list.ID_BAHAGIANPB && $list1.ID_HAKMILIKPB !=
											$list.ID_HAKMILIKPB) #set($count_total=$count_total + 1)
											#if($count > 1 && $count != $count_total && $count_total !=
											$count - 1 ) $list1.NAMA_PB, #elseif($count > 1 && $count !=
											$count_total && $count_total == $count - 1) $list1.NAMA_PB &
											#elseif($count > 1 && $count == $count_total) $list1.NAMA_PB
											#elseif($count == 1) $list1.NAMA_PB #end #end #end</div> #end</td>


									<td>#set($boxC = "") #if($list.FLAG_BORANGC == "1" )
										#set($boxC = "checked") #end




										<div align="center">
											<input type="checkbox" name="ids1" id="ids1"
												onClick="doUpdateCheckAll1('$list_kehadiran_size')"
												title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang C & D"
												$boxC value="$list.ID_HAKMILIKPB">

										</div> <input type="hidden" name="idPB" id="idPB"
										value="$list.ID_HAKMILIKPB">

									</td>

									<td>#set($boxE = "") #if($list.FLAG_BORANGE == "1" )
										#set($boxE = "checked") #end

										<div align="center">
											<input type="checkbox" name="borangE" id="borangE"
												onClick="doUpdateCheckAllBorangE('$list_kehadiran_size')"
												title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang E & F"
												$boxE value="$list.ID_HAKMILIKPB">

										</div>
									</td>


									<td>#set($boxG= "") #if($list.FLAG_BORANGG == "1" )
										#set($boxG = "checked") #end
										<div align="center">
											<input type="checkbox" name="borangG" id="borangG"
												onClick="doUpdateCheckAllBorangG('$list_kehadiran_size')"
												title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang G & H"
												$boxG value="$list.ID_HAKMILIKPB">

										</div>
									</td>

									<td>#set($boxK = "") #if($list.FLAG_BORANGK == "1" )
										#set($boxK = "checked") #end
										<div align="center">
											<input type="checkbox" name="borangK" id="borangK"
												onClick="doUpdateCheckAllBorangK('$list_kehadiran_size')"
												title="Semak untuk menunjukan '$list.NAMA_PB' disenaraikan di Borang K"
												$boxK value="$list.ID_HAKMILIKPB">

										</div>
									</td>

									<td></td>


								</tr>
								#end #else
								<tr>
									<td colspan="5">Tiada Rekod</td>
								</tr>
								#end
							</table>

							<!--    #if($saiz_pb > 5)
        </div>
        #end
-->
						</fieldset>
					</td>

				</tr>



			</table>

		</td>
	</tr>
</table>


<input type="hidden" name="sub_command" id="sub_command" />
<input type="hidden" name="subminor_command" id="subminor_command" />
<input type="hidden" name="location" id="location" />
<input type="hidden" name="point" id="point" />
<input type="hidden" name="id_pembatalan" id="id_pembatalan"
	value="$!id_pembatalan" />
<input type="hidden" name="id_permohonan" id="id_permohonan"
	value="$!id_permohonan" />
<input type="hidden" name="screen_name" id="screen_name" value="s2" />
<input type="hidden" name="readmode" id="readmode" value="$!readmode" />
<input type="hidden" name="alert_message" id="alert_message" />
<input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
<input type="hidden" name="jumlah_semua" id="jumlah_semua" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik"
	value="$!id_hakmilik" />
<input type="hidden" name="id_tanahumum" id="id_tanahumum"
	value="$!id_tanahumum" />
<input type="hidden" name="id_siasatan" id="id_siasatan"
	value="$!id_siasatan" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb"
	value="$!id_hakmilikpb" />
<input type="hidden" name="id_hakmilikpb_salin" id="id_hakmilikpb_salin"
	value="$!id_hakmilikpb_salin" />
<input type="hidden" name="id_pb" id="id_pb" value="$!id_pb" />
<input type="hidden" name="id_kehadiran" id="id_kehadiran"
	value="$!id_kehadiran" />
<input type="hidden" name="totalSyer" value="$!totalSyer">
<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">
<input type="hidden" name="tabId">


<!-- do post -->
<input type="hidden" name="form_token"
	value='$!{session.getAttribute("form_token")}'>

#if($hideAdd=="yes")
<script>
		alert("Bahagian PB Telah Mencukupi");
	</script>
#elseif($hideAdd=="notcomplete")
<script>
		alert("Bahagian PB Masih Tidak Mencukupi");
	</script>
#end



<script type="text/javascript">

function viewMyIdentity() {
	document.${formName}.command.value = "viewMyIdentity";
	document.${formName}.action = "";
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


	function popupPemegangAmanah(id_hakmilik,id_hakmilikpb){

		var w = "600";
	var h = "400";
		var left = (screen.width/2)-(w/2);
		var top = (screen.height/2)-(h/2);
		var url = "../x/${securityToken}/ekptg.view.ppt.FrmPopupPemegangAmanah?id_hakmilik="+id_hakmilik+"&id_hakmilikpb="+id_hakmilikpb;
			
		var hWnd = window.open(url, "Daftar Pemegang Amanah", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		
	}

var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm;

 
function submitForm() 
{


if('$location' != "" && '$location' != null && '$point' != "" && '$point' != null)
{
window.location.hash='$location';
goTo('$point');

}
else
{
window.location.hash='paging';
goTo('paging');

}




var lk = '$list_kehadiran_size';
if(lk > 0)
{
doUpdateCheckAll1('$list_kehadiran_size');
doUpdateCheckAllBorangG('$list_kehadiran_size');
doUpdateCheckAllBorangE('$list_kehadiran_size');
doUpdateCheckAllBorangK('$list_kehadiran_size');
}

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
checking_validation(document.${formName}.txtNoHP,'txtNoHP_check','no','no telefon bimbit','normal')
checking_validation(document.${formName}.txtNoTel,'txtNoTel_check','no','no telefon rumah / pejabat','normal')
checking_validation(document.${formName}.txtNamaBank,'txtNamaBank_check','no','nama bank','normal')
check_length(document.${formName}.txtKeteranganPB,'4000','txtKeteranganPB_check','txtKeteranganPB_num','normal','no','catatan');
check_length(document.${formName}.txtCatatan,'4000','txtCatatan_check','txtCatatan_num','normal','no','catatan');
}


</script>


<script type="text/javascript">
  









/*
function cari_lot(field)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'bolder';
hp.style.fontStyle = 'italic';
window.location.hash=field;
goTo(field);
}
}
function cari_lot1(field)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'normal';
hp.style.fontStyle = 'normal';
}
document.${formName}.CariLot.value="";
}
*/



function doCheckAll2(sudahbatal){  
var cc = 0;  
    if (document.${formName}.all2.checked == true){
        if (document.${formName}.ids2.length == null){
		cc++;
            document.${formName}.ids2.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids2.length; i++){
                document.${formName}.ids2[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.ids2.length == null){
            document.${formName}.ids2.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids2.length; i++){
                document.${formName}.ids2[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	/*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot_th";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       
       $jquery("#jumlahlot_th").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
}
function doUpdateCheckAll2(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.ids2.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids2.length; i++)
	  {
      if (document.${formName}.ids2[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.ids2.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all2.checked = false;
	  }
	  else
	  {
	  document.${formName}.all2.checked = true;
	  }
	  
	  
	  
	 
}








function doCheckAll1(sudahbatal){  
var cc = 0;  
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
		cc++;
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = false;

        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = false;
            }
        }
    }
	
	  
	
	
}


function doCheckAllBorangG(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangG.checked == true){
        if (document.${formName}.borangG.length == null){
		cc++;
            document.${formName}.borangG.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangG.length; i++){
                document.${formName}.borangG[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangG.length == null){
            document.${formName}.borangG.checked = false;

        } else {
            for (i = 0; i < document.${formName}.borangG.length; i++){
                document.${formName}.borangG[i].checked = false;
            }
        }
    }
	
	  
	
	
}

function doCheckAllBorangE(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangE.checked == true){
        if (document.${formName}.borangE.length == null){
		cc++;
            document.${formName}.borangE.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangE.length; i++){
                document.${formName}.borangE[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangE.length == null){
            document.${formName}.borangE.checked = false;

        } else {
            for (i = 0; i < document.${formName}.borangE.length; i++){
                document.${formName}.borangE[i].checked = false;
            }
        }
    }
	
	 
	
	
}



function doCheckAllBorangK(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangK.checked == true){
        if (document.${formName}.borangK.length == null){
		cc++;
            document.${formName}.borangK.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangK.length; i++){
                document.${formName}.borangK[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangK.length == null){
            document.${formName}.borangK.checked = false;

        } else {
            for (i = 0; i < document.${formName}.borangK.length; i++){
                document.${formName}.borangK[i].checked = false;
            }
        }
    }
	
	
}





function doUpdateCheckAll1(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all1.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1.checked = true;
	  }
	  
	  
	  
	
	
}



function doUpdateCheckAllBorangG(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangG.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangG.length; i++)
	  {
      if (document.${formName}.borangG[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangG.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangG.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangG.checked = true;
	  }
	  
	  
	  
	
}

function doUpdateCheckAllBorangE(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangE.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangE.length; i++)
	  {
      if (document.${formName}.borangE[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangE.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangE.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangE.checked = true;
	  }
	  
	  
	
	
}



function doUpdateCheckAllBorangK(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangK.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangK.length; i++)
	  {
      if (document.${formName}.borangK[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangK.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangK.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangK.checked = true;
	  }
	  
	 
	
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




function jeniswaktu1(time_field,jenis_time)
{
document.getElementById(jenis_time).value = "";
var vm = time_field.value;
if(vm != "" && vm.length == 4)
{
var vm_m = 0;

if(vm.charAt(0) == "0")
{
   var vm_c = vm.charAt(1) + vm.charAt(2) +vm.charAt(3);
   vm_m = parseInt(vm_c)
}
else
{
   vm_m = parseInt(vm)
}

if(vm_m >= 1200 && vm_m <= 1259 )
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";

}
else if(vm_m >= 100 && vm_m <= 659 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="none";
}
else if(vm_m >= 700 && vm_m <= 1159 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";
}
else
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="none";
}


}
else
{

document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="";
}




}





function jeniswaktu2(time_field)
{


var vm = document.getElementById(time_field).value;


if(vm != "" && vm.length == 4)
{
var vm_m = 0;


if(vm.charAt(0) == "0")
{
   var vm_c = vm.charAt(1) + vm.charAt(2) +vm.charAt(3);
   vm_m = parseInt(vm_c)
}
else
{
   vm_m = parseInt(vm)
}

if(vm_m >= 1200 && vm_m <= 1259 )
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";

}
else if(vm_m >= 100 && vm_m <= 659 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="none";
}
else if(vm_m >= 700 && vm_m <= 1159 )
{
document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="";
}
else
{

document.getElementById("op_pagi").disabled=true;
document.getElementById("op_tengahari").disabled=true;
document.getElementById("op_petang").disabled=true;
document.getElementById("op_malam").disabled=true;

document.getElementById("op_pagi").style.display="none";
document.getElementById("op_tengahari").style.display="none";
document.getElementById("op_petang").style.display="none";
document.getElementById("op_malam").style.display="none";
}



}
else
{


document.getElementById("op_pagi").disabled=false;
document.getElementById("op_tengahari").disabled=false;
document.getElementById("op_petang").disabled=false;
document.getElementById("op_malam").disabled=false;

document.getElementById("op_pagi").style.display="";
document.getElementById("op_tengahari").style.display="";
document.getElementById("op_petang").style.display="";
document.getElementById("op_malam").style.display="";
}




}

function getBandarSiasatan(id_hakmilik,id_pembatalan)
{
    document.${formName}.command.value = "tambahPB";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "getBandar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socBandarSiasatan";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}


function tambah(id_hakmilik,id_pembatalan)
{
    document.${formName}.command.value = "tambahPB";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "tambah";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}

/*
function simpan(id_siasatan)
{
var c = 0;

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


if(c>0){
alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
return;
}
else
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "tambahPB";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	
	if(id_siasatan == "")
	{
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
	}
	else
	{
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	}
	
	document.${formName}.submit();
	}
	}
}


function papar(id_siasatan)
{
	document.${formName}.command.value = "tambahPB";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.submit();
	
}


function kemaskini(id_siasatan)
{
	document.${formName}.command.value = "tambahPB";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.submit();
	
}



function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "tambahPB";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Hapus";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";	
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.submit();
	}
}


function hapus_beramai()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "tambahPB";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Hapus_beramai";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";	
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "txtNoKes";
	document.${formName}.submit();
	}
}




function ShowPopupSiasatan(tab)
{

hp = document.getElementById(tab);
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


function ShowPopupKehadiran(tab)
{

hp = document.getElementById(tab);
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


function cari_lot(field)
{


hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'bolder';
hp.style.fontStyle = 'italic';
window.location.hash=field;
goTo(field);
}
else
{

if(field=="")
{
alert("Pastikan no. pb dimasukkan");
}
else 
{
alert("Tiada rekod untuk pb bernombor "+field);
}
}
}
function cari_lot1(field)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'normal';
hp.style.fontStyle = 'normal';
}
document.${formName}.CariLot.value="";
}
*/

function Simpan_Borang()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {	
	document.${formName}.command.value = "tambahPB";	
	document.${formName}.subminor_command.value = "Simpan_Borang";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
	document.${formName}.submit();
	}
}

function paparpb(id_hakmilikpb)
{
    document.${formName}.command.value = "tambahPB";	
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.submit();
}


function kemaskinipb(id_hakmilikpb)
{
    document.${formName}.command.value = "tambahPB";
	
	document.${formName}.subminor_command.value = "Kemaskini";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.submit();
}

function getBandar(id_hakmilikpb)
{
    document.${formName}.command.value = "tambahPB";	
	document.${formName}.subminor_command.value = "getBandar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socBandar";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.submit();
}

function getSyer(id_hakmilikpb)
{
  /*  document.${formName}.command.value = "tambahPB";
	
	document.${formName}.subminor_command.value = "getSyer";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "txtSyorAtas";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();*/
	
	      
		   actionName = "getSyer";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   target = "getSyer_div";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   
}

function batal(id_hakmilikpb)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	document.${formName}.command.value = "tambahPB";
	
	document.${formName}.subminor_command.value = "batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;	
	document.${formName}.submit();
	}
}

function tambahPB()
{

	
	document.${formName}.command.value = "tambahPB";
	
	document.${formName}.subminor_command.value = "tambah";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();
	
}


function tambahWakil()
{

	
	document.${formName}.command.value = "tambahPB";	
	document.${formName}.subminor_command.value = "tambah_wakil";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();
	
}
function salin_pb(id_hakmilikpb_salin)
{
	document.${formName}.command.value = "tambahPB";
	document.${formName}.id_hakmilikpb_salin.value = id_hakmilikpb_salin;	
	document.${formName}.subminor_command.value = "salin_pb";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();	
}

//simpanPB('$id_siasatan','$id_hakmilikpb','$id_kehadiran','$id_pb')

function simpanPB(id_hakmilik,id_hakmilikpb,id_kehadiran,id_pb,sizeBahagian)
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
else if(sizeBahagian != "0" && (document.${formName}.id_bahagianpb.value == "" && ((((valA != "") && (valB != "")) && (newTotal>1.01)))))
{
		alert("Sila pastikan \"Bahagian / Syer PB\" tidak melebihi jumlah keseluruhan");
		document.${formName}.txtSyorAtas.focus();
		return
}
else
{
	/*alert("sizeBahagian :"+sizeBahagian);
	alert("id bahagian :"+document.${formName}.id_bahagianpb);
	alert("valA :"+valA);
	alert("valB :"+valB);
	alert("newTotal :"+newTotal);*/
	
	
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "tambahPB";
	
	document.${formName}.subminor_command.value = "Simpan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	if(id_hakmilikpb!="")	
	{
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	//alert("1");
	}
	else
	{
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
	//alert("2");
	}
	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.id_kehadiran.value = id_kehadiran;
	document.${formName}.id_pb.value = id_pb;	
	
	document.${formName}.submit();
	
	}
}
}



function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		window.location.hash=id;
        goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}


function hapusTurutHadir(id_hakmilikpb,id_pb,id_kehadiran)
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    document.${formName}.command.value = "tambahPB";
	
	document.${formName}.subminor_command.value = "DeleteTurutHadir";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "senarai_siasatan_th";
	document.${formName}.point.value = "senarai_siasatan_th";	
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.id_kehadiran.value = id_kehadiran;
	document.${formName}.id_pb.value = id_pb;
	document.${formName}.submit();
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

function screen5(id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "tambahPB";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
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


function PB(id_hakmilik)
{

	
	document.${formName}.command.value = "tambahPB";
	
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.id_hakmilik.value = id_hakmilik;	
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.submit();

}


function PB_Kosong(id_hakmilik)
{

	
	document.${formName}.command.value = "tambahPB";
	
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.id_hakmilik.value = id_hakmilik;	
	document.${formName}.CariPB.value = "";	
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "maklumat_pb";
	document.${formName}.submit();

}

function validateSyer(sizeBahagian) {

	//syer
	var sA = parseFloat(document.${formName}.txtSyorAtas.value);
	var sB = parseFloat(document.${formName}.txtSyorBawah.value);

	//total syer
	var totalSyer = parseFloat(document.${formName}.totalSyer.value);

	if(sA > sB){

		alert("Sila pastikan \"Syer Atas\" tidak melebihi \"Syer Bawah\"");
		
	}else{

		if(sizeBahagian != "0" && document.${formName}.socBahagianPB.value == ""){	
			if((document.${formName}.txtSyorAtas.value!= "") && (document.${formName}.txtSyorBawah.value!= "")){

				var bhg = sA / sB;
				var newTotal = totalSyer + bhg;

				if(newTotal>1.01){
					alert("Sila pastikan \"Bahagian / Syer PB\" tidak melebihi jumlah keseluruhan");
				}

			}	
		}
	}

	
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

function hantarSyer(atas,bawah,keterangan)
{
    document.${formName}.txtSyorAtas.value = atas;
	document.${formName}.txtSyorBawah.value = bawah;	
	document.${formName}.txtKeteranganPB.value = keterangan;	
}


function viewHM(idHakmilik){

	document.${formName}.ScreenLocation.value = "senarai_siasatan";
	document.${formName}.tabId.value = "0";	
	document.${formName}.command.value = "viewHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}


function semakanJIM(ID_PIHAKBERKEPENTINGAN, ID_PERMOHONAN, NO_PB, NAMA_PB) {
	var noPb = document.${formName}.txtNoPB.value;
	var namaPb = document.${formName}.txtNamaPB.value;
	//document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewMaklumatKebangkrapan&action2=viewMaklumatKebangkrapan&ID_PB=" + ID_PIHAKBERKEPENTINGAN + "&ID_PERMOHONAN=" + ID_PERMOHONAN + "&NO_PB" + NO_PB + "&NAMA_PB" + NAMA_PB;
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewMaklumatKebangkrapan&action2=viewMaklumatKebangkrapan&MODULE=PPT&ID_PB=" + ID_PIHAKBERKEPENTINGAN + "&ID_PERMOHONAN=" + ID_PERMOHONAN + "&NO_PB=" + noPb + "&NAMA_PB=" + namaPb;
	document.${formName}.submit();
}

</script>

