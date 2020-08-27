<!--
<style type="text/css">
.hyu {
	font-size: 15px;
}
.hyu2 {
	font-size: 24px;
}
.hover {
	font-size: 36px;
	color:#C30;
}
a:hover {
	color: #C90;
	text-decoration: underline;
}
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
</style>
-->
<!--
  <style type="text/css">
  .pautanms {color: #0033FF}
  </style>
-->

<script>
function toggle_div(id) {
    var e = document.getElementById(id);
    e.style.display = ((e.style.display!='none') ? 'none' : 'block');
}
</script>

<p align="center">
	<table cellpadding="2" cellspacing="1" border="0" width="100%" class="nav" align="center" >
		<tr>
			<td width="1%" valign="top"></td>
			<td width="59%" valign="top">
				<table width="100%" border="0" align="left" class="dashboard_kiri">
					#if ($portalRole == "online_kjp" || $portalRole == "online_kjpagensi")
					<tr>
						<td valign="top" align="left">
							<table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
								<tr>
									<td width="50%" valign="top">
										<table width="100%" border="0">
											<tr>
											<td width="15%" align="center" valign="top"><img src="../img/myprofile.png" width="30" height="30" align="center"/></td>
												<td width="85%">
													<table>
														<tr>
															<td><b>Profil</b></td>
														</tr>
														<tr>
															<td>
																<a href="javascript:gotoProfile()" class="help" title="Tukar Kata Laluan">
																	<font color="blue"><li>&nbsp;Tukar Kata Laluan</li></font>
																</a>
															</td>
														</tr>
														<!-- <tr>
															<td>
																<a href="javascript:gotoInbox()" class="help" title="My Inbox">
																	<font color="blue"><li>
																	#if($!notifikasi_inbox > 0)
																		<label style="background-color:blue" align="center" valign="top" >
																			<b><font color="WHITE"><blink>$!notifikasi_inbox</blink></font></b>
																		</label>&nbsp;
																	#end
																	Ruang Perbincangan Formal</li></font>
																</a>
															</td>
														</tr> -->
														<!-- <tr>
															<td>
																<a href="javascript:gotoMyCalendar()" class="help" title="MyCalendar">
																	<font color="blue"><li><i>&nbsp;MyCalendar</i></li></font>
																</a>
															</td>
														</tr> -->
													</table>
												</td>
											</tr>
										</table>
									</td>
									<td width="50%" valign="top">

									</td>
								</tr>
							</table>
						</td>
					</tr>
					#end
					<tr>
						<td valign="top" align="left">
							<table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
								<tr>
								#if ($portalRole == "online_kjp" || $portalRole == "online_kjpagensi")
									<td width="50%" valign="top">
										<table width="100%" border="0">
											<tr>
											<td width="15%" align="center" valign="top"><img src="../img/tray.gif" width="30" height="30" align="center"/></td>
												<td width="85%">
													<table>
														<tr>
															<td><b>Urusan Pengambilan Tanah</b></td>
														</tr>
														<tr>
															<td>
																<!-- <a href="#" onclick="toggle_div('toggleDiv');" class="help" title="Membuat Permohonan Pengambilan Tanah"> -->
																<a href="javascript:permohonanUPT()" class="help" title="Membuat Permohonan Pengambilan Tanah">
																	<font color="blue"><li>
																	#if($!jumlah_notifikasi > 0)
																		<label style="background-color:blue" align="center" valign="top" >
																			<b><font color="WHITE"><blink>$!jumlah_notifikasi</blink></font></b>
																		</label>&nbsp;
																	#end

																	<!--
																	<label style="background-color:blue" align="center" valign="top" >
																				<b><font color="WHITE"><blink>$!jumlah_notifikasitolak</blink></font></b>
																		</label>&nbsp;
																	 -->

																	Pengambilan Tanah</li></font>
																</a>
															</td>
														</tr>
														<!-- AISHAH TAMBAH START -->
														<!-- <tr>
															<td>
																<a href="javascript:paparanMalumatPermohonan()" class="help" title="Paparan maklumat permohonan KJP yang dihantar ke negeri">
																	<font color="blue"><li>&nbsp;Paparan Maklumat Permohonan</li></font>
																</a>
															</td>
														</tr> -->
														<!-- <tr>
														<td valign="top" align="left">

														    <table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
															<tr>

															<td width="50%" valign="top">
															<table width="100%" >
																<tr>
																<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/applyonline.png" align="center"/></td>
																<td width="85%" valign="top"><div id="div_getFailMainOnline"></div></td>
																</tr>
															</table>
															</td>
															</tr>
															</table>

														</td>
														</tr> -->
														<!-- AISHAH TAMBAH END -->
														<tr>
															<td>
																<a href="javascript:permohonanPenarikanBalik()" class="help" title="Permohonan Penarikan Balik">
																	<font color="blue"><li>&nbsp;Penarikan Balik</li></font>
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:permohonanPembatalan()" class="help" title="Membuat Permohonan Pembatalan">
																	<font color="blue"><li>&nbsp;Pembatalan</li></font>
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:permohonanDaftarBantahan()" class="help" title="Permohonan Daftar Bantahan">
																	<font color="blue"><li>&nbsp;Bantahan</li></font>
																</a>
															</td>
														</tr>

														<tr>
															<td>
																<a href="javascript:penyewaan()" class="help" title="Penyewaan">
																	<font color="blue"><li>
																	##if($jumlah_notifikasi_penyewaan > 0)
																	<label style="background-color:blue" align="center" valign="top" >
																		<b><font color="WHITE"><blink>$!bilPPTDikembali</blink></font></b>
																	</label>&nbsp;
																	##end
																	Permohonan Dikembalikan</li></font>
																</a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
									#end
									<td width="50%" valign="top">
										<table width="100%" border="0">
											<tr>
											#if ($portalRole == "online_kjp" || $portalRole == "online_kjpagensi")
											<td width="15%" align="center" valign="top"><img src="../img/tray.gif" width="30" height="30" align="center"/></td>
												<td width="85%">
													<table>
														<tr>
															<td><b>Urusan Penguatkuasaan dan Hasil</b></td>
														</tr>
														<!-- <tr>
															<td>
																<a href="javascript:penawaran()" class="help" title="Penawaran">
																	<font color="blue"><li>&nbsp;Penawaran</li></font>
																</a>
															</td>
														</tr> -->
														<tr>
															<td>
																<a href="javascript:penawaran()" class="help" title="Permohonan Penawaran">
																	<font color="blue"><li>
																	#if($jumlah_notifikasi_penawaran > 0)
																	<label style="background-color:blue" align="center" valign="top" >
																		<b><font color="WHITE"><blink>$jumlah_notifikasi_penawaran</blink></font></b>
																	</label>&nbsp;
																	#end
																	Permohonan Penawaran</li></font>
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:tukarGuna()" class="help" title="Permohonan Tukar Guna">
																	<font color="blue"><li>
																	#if($jumlah_notifikasi_tukarguna1 > 0)
																	<label style="background-color:blue" align="center" valign="top" >
																		<b><font color="WHITE"><blink>$jumlah_notifikasi_tukarguna1</blink></font></b>
																	</label>&nbsp;
																	#end
																	Permohonan Tukar Guna</li></font>
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:pelepasan()" class="help" title="Ulasan Pelepasan">
																	<font color="blue"><li>
																	#if($jumlah_notifikasi_pelepasan > 0)
																	<label style="background-color:blue" align="center" valign="top" >
																		<b><font color="WHITE"><blink>$jumlah_notifikasi_pelepasan</blink></font></b>
																	</label>&nbsp;
																	#end
																	Ulasan Pelepasan</li></font>
																</a>
																<!-- <a href="javascript:pelepasan()" class="help" title="Pelepasan">
																	<font color="blue"><li>&nbsp;Pelepasan</li></font>
																</a> -->
															</td>
														</tr>
														
														<tr>
															<td>
																<a href="javascript:tukarGunaUlasan()" class="help" title="Ulasan Tukar Guna">
																	<font color="blue"><li>
																	#if($jumlah_notifikasi_tukarguna > 0)
																	<label style="background-color:blue" align="center" valign="top" >
																		<b><font color="WHITE"><blink>$jumlah_notifikasi_tukarguna</blink></font></b>
																	</label>&nbsp;
																	#end
																	Ulasan Tukar Guna</li></font>
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:penyewaan()" class="help" title="Ulasan Penyewaan">
																	<font color="blue"><li>
																	#if($jumlah_notifikasi_penyewaan > 0)
																	<label style="background-color:blue" align="center" valign="top" >
																		<b><font color="WHITE"><blink>$jumlah_notifikasi_penyewaan</blink></font></b>
																	</label>&nbsp;
																	#end
																	Ulasan Penyewaan</li></font>
																</a>
															</td>
														</tr>
														
														<tr>
															<td>
																<a href="javascript:aktapelantarbenua()" class="help" title="Akta Pelantar Benua">
																	<font color="blue"><li>
																	#if($jumlah_notifikasi_apb > 0)
																	<label style="background-color:blue" align="center" valign="top" >
																		<b><font color="WHITE"><blink>$jumlah_notifikasi_apb</blink></font></b>
																	</label>&nbsp;
																	#end
																	Akta Pelantar Benua</li></font>
																</a>
															</td>
														</tr>
														#if ($idKementerian == '13')
														<tr>
															<td>
																<a href="javascript:ulasanKertasKewangan()" class="help" title="Ulasan Kertas Kewangan">
																	<font color="blue"><li>
																	#if($jumlah_notifikasi_MOF > 0)
																	<label style="background-color:blue" align="center" valign="top" >
																		<b><font color="WHITE"><blink>$jumlah_notifikasi_MOF</blink></font></b>
																	</label>&nbsp;
																	#end
																	Ulasan Menteri Kewangan</li></font>
																</a>
															</td>
														</tr>
														#end
													</table>
												</td>
												#end
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
									<td width="50%" valign="top">
										<table width="100%" border="0">
											<tr>
												#if ($portalRole == "online_kjp" || $portalRole == "online_kjpagensi")
												<td width="15%" align="center" valign="top"><img src="../img/tray.gif" width="30" height="30" align="center"/></td>
												<td width="85%">
													<table>
														<tr>
															<td><b>Urusan Harta Tanah Persekutuan</b></td>
														</tr>
														<tr>
															<td>
																<a href="javascript:senaraiPemberimilikan()" class="help" title="Pemberimilikan / Perizaban">
																	<font color="blue"><li>Pemberimilikan / Perizaban</li></font>
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:pembelian()" class="help" title="Pembelian">
																	<font color="blue"><li>Pembelian</li></font>
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:rekod()" class="help" title="Rekod Hakmilik / Rizab">
																	<font color="blue"><li>Rekod Hakmilik / Rizab</li></font>
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:laporanHakmilik()" class="help" title="Laporan Hakmilik / Rizab">
																	<font color="blue"><li>Laporan Hakmilik / Rizab</li></font>
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:gadaian()" class="help" title="Gadaian">
																	<font color="blue"><li>Gadaian</li></font>
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:jawatankuasaRuangPejabat()" class="help" title="Jawatankuasa Ruang Pejabat">
																	<font color="blue"><li>Jawatankuasa Ruang Pejabat</li></font>
																</a>
															</td>
														</tr>
													</table>
												</td>
												#end
											</tr>
										</table>
									</td>

									<td width="50%" valign="top">
										<table width="100%" >
											<tr>
												<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/aduan.png"/></td>
												<td width="85%">
													<table>
														<tr>
															<td><b>Aduan / Cadangan</b></td>
														</tr>

														<!-- <tr>
															<td>
																<a href="javascript:statusAduan()" class="help" title="Pengurusan Log Aduan">
																	<font color="blue"><li>&nbsp;Status Aduan</li></font>
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
					<!-- #if ($portalRole == "online_kjp")
					<tr>
						<td valign="top" align="left">
							<table cellpadding="2" cellspacing="1" border="0" width="100%" class="" align="left">
								<tr>
									<td width="50%" valign="top">
										<table width="100%" >
											<tr>
												<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/aduan.png"/></td>
												<td width="85%">
													<table>
														<tr>
															<td><b>Aduan / Cadangan</b></td>
														</tr>
														<tr>
															<td>
																<a href="javascript:aduan()" class="help" title="Pengurusan Log Aduan">
																	<font color="blue"><li>&nbsp;Hantar Aduan & Cadangan</li></font>
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:statusAduan()" class="help" title="Pengurusan Log Aduan">
																	<font color="blue"><li>&nbsp;Status Aduan</li></font>
																</a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
									<td width="50%" valign="top">

									</td>
								</tr>
							</table>
						</td>
					</tr>
					#end -->
				</table>
			</td>

			<td width="39%" valign="top">
				<table width="100%" border="0" >
					<!-- <tr>
						<td>
							<table cellpadding="2" cellspacing="1" border="0" width="100%" class=" dashboard_tepi" align="left">
								<tr>
									<td width="100%" valign="top">
										<table width="100%"  id="table_stat">
											<tr>
												<td width="15%" align="center" valign="top"></td>
												<td width="85%" >
													<table width="100%">
														<tr>
															<td colspan="3">
																<b>Statistik Fail di Pangkalan Data <br /><font color="blue">$!negeri_sever</font></b>
															</td>
														</tr>
														<tr>
															<td width="50%" valign="top">
																<font color="blue"><li>Keseluruhan Fail</li></font>
															</td>
															<td width="1%" valign="top">:</td>
															<td width="49%" valign="top">
																<b>$!jumlah_keseluruhan</b>
															</td>
														</tr>
														<tr>
															<td valign="top">
																<font color="blue"><li>Jumlah Dokumen</li></font>
															</td>
															<td valign="top">:</td>
															<td valign="top">
																<b>$!jumlah_dokumen</b>
															</td>
														</tr>
														<tr>
															<td valign="top">
																<font color="blue"><li>Jumlah Fail</li></font>
															</td>
															<td valign="top">:</td>
															<td  valign="top">
																<b>$!jumlah_fail</b>
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
					</tr>	 -->
					#if($list_memo_aktif.size()>0)
						<tr>
							<td>
								<table width="100%" border="0" class="dashboard_bawah" >
									<tr>
										<td>
											<div id="TabbedPanels1" class="TabbedPanels">
												<ul class="TabbedPanelsTabGroup">
													#if($list_memo_aktif.size()>0)
														<li class="TabbedPanelsTab" tabindex="0" id="Pengumuman_Head">Pengumuman</li>
													#end
												</ul>
												<div class="TabbedPanelsContentGroup">
													#if($list_memo_aktif.size()>0)
														<div class="TabbedPanelsContent"  id="Peringatan_Main" style="height:250" >
															<table width="100%" >
																<tr>
																	<td width="12%" align="center" valign="top"><img width="30" height="30" src="../img/online/kblogger.png" align="center"/></td>
																	<td width="88%" valign="top">
																		<table width="100%">
																			<tr>
																				<td valign="top">
																					#parse("app/pfd/frmPengumuman.jsp")
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</div>
													#end
												</div>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					#end
				</table>
			</td>

			<td width="1%" valign="top"></td>
		</tr>
	</table>
</p>
<input type="hidden" name="jawatan" id="jawatan" value="$!jawatan">
<input type="hidden" name="flag_noti" id="flag_noti" value="">
<input type="hidden" name="notifikasi" id="notifikasi" value="$!jumlah_notifikasi">
<div id="divMainStats">
<script>
<!-- AISHAH TAMBAH START -->
function gotoSek4()
{
all_reset('div_getOnline4');
document.getElementById('div_getOnline4').style.display="";
doDivAjaxCall$formname('div_getOnline4','getOnline4','');
}
function gotoSek8()
{
all_reset('div_getOnline8');
document.getElementById('div_getOnline8').style.display="";
doDivAjaxCall$formname('div_getOnline8','getOnline8','');
}
<!-- AISHAH TAMBAH END -->
function permohonanUPT(){
//	document.$(formName).jawatan.value = "$!jawatan";
	document.${formName}.action = "$EkptgUtil.getTabID('Pengambilan Tanah',$portalRole)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";

	var flag_noti = "";
	var noti = document.getElementById('notifikasi').value;
	//alert(" noti : "+noti);
	if(noti!="")
	{
		if(parseInt(noti)>0)
		{
			//alert(" parseInt(noti) : "+parseInt(noti));
			flag_noti = "Y";
		}

	}
	document.getElementById('flag_noti').value = flag_noti;


//	document.${formName}.action = "$EkptgUtil.getTabID('Menu Utama',$portalRole)?_portal_module=ekptg.view.online.FrmOnlineMenuUtamaKJP";
	document.${formName}.submit();
}
function permohonanPenarikanBalik(){
	document.${formName}.action = "$EkptgUtil.getTabID('Pengambilan Tanah',$portalRole)?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.submit();
}
function paparanMalumatPermohonan(){
	document.${formName}.action = "$EkptgUtil.getTabID('Pengambilan Tanah',$portalRole)?_portal_module=ekptg.view.ppt.FrmPaparanMalumatPermohonan";
	document.${formName}.submit();
}

function permohonanPembatalan(){
	document.${formName}.action = "$EkptgUtil.getTabID('Pengambilan Tanah',$portalRole)?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.submit();
}

function permohonanDaftarBantahan(){
	document.${formName}.action = "$EkptgUtil.getTabID('Pengambilan Tanah',$portalRole)?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}

function penawaran(){
	document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portalRole)?_portal_module=ekptg.view.php2.online.FrmPNWOnlineSenaraiFailView";
	document.${formName}.submit();
}

function tukarGuna(){
	document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portalRole)?_portal_module=ekptg.view.php2.online.FrmTKROnlineKJPSenaraiFailView";
	document.${formName}.submit();
}

function tukarGunaUlasan(){
	document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portalRole)?_portal_module=ekptg.view.php2.online.FrmTKROnlineKJPSenaraiUlasanFailView";
	document.${formName}.submit();
}

function aktapelantarbenua(){
	document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portalRole)?_portal_module=ekptg.view.php2.online.FrmAPBOnlineKJPSenaraiFailView";
	document.${formName}.submit();
}

function pelepasan(){
	document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portalRole)?_portal_module=ekptg.view.php2.online.FrmPLPOnlineKJPSenaraiFailView";
	document.${formName}.submit();
}

function penyewaan(){
	//alert('$portalRole');
	document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portalRole)?_portal_module=ekptg.view.php2.online.FrmPYWOnlineKJPSenaraiFailView";
	document.${formName}.submit();
}
function penawaran(){
	document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portalRole)?_portal_module=ekptg.view.php2.online.FrmPNWOnlineKJPSenaraiFailView";
	document.${formName}.submit();
}
function ulasanKertasKewangan(){
	document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portalRole)?_portal_module=ekptg.view.php2.online.FrmMOFOnlineKJPSenaraiUlasanFailView";
	document.${formName}.submit();
}
function rekod(){
	document.${formName}.action = "$EkptgUtil.getTabID('Harta Tanah Persekutuan',$portalRole)?_portal_module=ekptg.view.online.htp.rekod.FrmRekodPendaftaranTanah";
	document.${formName}.submit();
}
function laporanHakmilik(){
	document.${formName}.action = "$EkptgUtil.getTabID('Harta Tanah Persekutuan',$portalRole)?_portal_module=ekptg.view.online.htp.rekod.FrmSenaraiLaporanHTPRekod";
	document.${formName}.submit();
}
function senaraiPemberimilikan(){
	document.${formName}.action = "$EkptgUtil.getTabID('Harta Tanah Persekutuan',$portalRole)?_portal_module=ekptg.view.online.htp.permohonan.FrmTerimaPohon1Online";
	document.${formName}.submit();
}
function permohonan(){
	document.${formName}.action = "$EkptgUtil.getTabID('Harta Tanah Persekutuan',$portalRole)?_portal_module=ekptg.view.online.htp.permohonan.FrmPermohonanTanah";
	document.${formName}.submit();
}
function pembelian(){
	document.${formName}.action = "$EkptgUtil.getTabID('Harta Tanah Persekutuan',$portalRole)?_portal_module=ekptg.view.online.htp.pembelian.SenaraiFailModuleOnline";
	document.${formName}.submit();
}
function permohonanPembelian(){
	document.${formName}.action = "$EkptgUtil.getTabID('Harta Tanah Persekutuan',$portalRole)?_portal_module=ekptg.view.online.htp.pembelian.FrmPermohonanPembelian";
	document.${formName}.submit();
}
function gadaian(){
	document.${formName}.action = "$EkptgUtil.getTabID('Harta Tanah Persekutuan',$portalRole)?_portal_module=ekptg.view.online.htp.gadaian.FrmKJPGadaianA";
	document.${formName}.submit();
}
function jawatankuasaRuangPejabat(){
	document.${formName}.action = "$EkptgUtil.getTabID('Harta Tanah Persekutuan',$portalRole)?_portal_module=ekptg.view.online.htp.pajakankecil.FrmKJPJawatankuasaRuangPejabat";
	document.${formName}.submit();
}

/* function aduan(){
	document.${formName}.action = "$EkptgUtil.getTabID('Aduan / Cadangan',$portalRole)?_portal_module=ekptg.view.online.aduan.ComplaintSenderModule";
	document.${formName}.submit();
} */

function aduan() {
	document.${formName}.action = "$EkptgUtil.getTabID("Aduan / Cadangan",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
	document.${formName}.submit();
}
function permohonanDikembalikan() {
	document.${formName}.action = "$EkptgUtil.getTabID("Permohonan Dikembalikan ",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
	document.${formName}.submit();
}

function statusAduan(){
	document.${formName}.action = "$EkptgUtil.getTabID('Aduan / Cadangan',$portalRole)?_portal_module=ekptg.view.online.aduan.ComplaintStatusModule";
	document.${formName}.submit();
}
/*function gotoProfile(){
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portalRole)?_portal_module=ekptg.view.admin.UpdateUserProfileModule";
	document.${formName}.submit();
}*/
function gotoProfile(){
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portalRole)?_portal_module=ekptg.view.admin.UserProfileInternal";
	document.${formName}.submit();
}
function gotoMyCalendar(){
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portalRole)?_portal_module=lebah.pm.module.ActivitiesModule";
	document.${formName}.submit();
}
</script>
</div>