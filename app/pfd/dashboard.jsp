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
<!-- class="dashboard_sub_kiri" -->

<p align="center">
	<table cellpadding="2" cellspacing="1" border="0" width="100%" class="nav" align="center" >
		<tr>
			<td width="1%" valign="top"></td>
			<td width="59%" valign="top">
				<table width="100%" border="0" align="left" class="dashboard_kiri">
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
														<tr>
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
														</tr>
														<tr>
															<td>
																<a href="javascript:gotoMyCalendar()" class="help" title="MyCalendar">
																	<font color="blue"><li><i>&nbsp;MyCalendar</i></li></font>						
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
					<tr>
						<td valign="top" align="left">
							<table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
								<tr>
									<td width="50%" valign="top">
										<table width="100%" border="0">
											<tr>
												<td width="15%" align="center" valign="top"><img src="../img/main.png" width="30" height="30" align="center"/></td>
												<td width="85%">
													<table>
														<tr>
															<td><b>Tugasan</b></td>
														</tr>
														<tr>
															<td>
															#set ($countLogDokumen = 0)
																#set ($countAgihanTugasan = 0)
																#set ($countPenerimaTugasan = 0)
																#set ($countPergerakanFail = 0) 
																#foreach ($listLogDokumenByUserId in $SenaraiDokumenByUserId)
																	#if ($listLogDokumenByUserId.bil != 'Tiada rekod.' && $listLogDokumenByUserId.bil != '') 
																		#set ($countLogDokumen = $countLogDokumen + 1)	
																	#else
																		#set ($countLogDokumen = $countLogDokumen)
																	#end 
																#end
																#foreach ($listAgihanTugasanByUserId in $SenaraiAgihanTugasanByUserId)
																	#if ($listAgihanTugasanByUserId.bil != 'Tiada rekod.' && $listAgihanTugasanByUserId.bil != '') 
																		#set ($countAgihanTugasan = $countAgihanTugasan + 1)
																	#else 
																		#set ($countAgihanTugasan = $countAgihanTugasan)
																	#end
																#end
																#foreach ($listPenerimaTugasanByUserId in $SenaraiPenerimaTugasanByUserId)
																	#if ($listPenerimaTugasanByUserId.bil != 'Tiada rekod.' && $listPenerimaTugasanByUserId.bil != '') 
																		#set ($countPenerimaTugasan = $countPenerimaTugasan + 1)
																	#else 
																		#set ($countPenerimaTugasan = $countPenerimaTugasan)
																	#end
																#end
																#foreach ($senarai in $SenaraiPergerakanFail)
																	#if ($senarai.bil != 'Tiada rekod.' && $senarai.bil != '') 
																		#set ($countPergerakanFail = $countPergerakanFail + 1)
																	#else 
																		#set ($countPergerakanFail = $countPergerakanFail)
																	#end
																#end
																#set ( $countTugasan = $countLogDokumen + $countAgihanTugasan + $countPenerimaTugasan + $countPergerakanFail)
																<a href="javascript:gotoSenaraiTugasan()" class="help" title="Senarai Tugasan">
																	<font color="blue">
																	<li>
																	#if($!countTugasan > 0)
										                            <label style="background-color:blue"  align="center" valign="top" > 
                            										<b><font color="WHITE"><blink>$!countTugasan</blink></font></b>
                             										</label>&nbsp;
                             										#end
																	Senarai Tugasan</li></font>						
																</a>
															</td>
														</tr>
														<!-- <tr>
															<td>
																<a href="javascript:goTo('PHPAPB')" class="help" title="Pendaftaran permohonan lesen pasir">
																	<font color="blue"><li>Akta Pelantar Benua(APB</li></font>						
																</a>
															</td>
														</tr> -->
													</table>
												</td>
											</tr>
										</table>
									</td>
									<td width="50%" valign="top">
										<table width="100%" >
											<tr>
												<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/new_application.png" align="center"/></td>
												<td width="85%">
													<table>
														<tr>
															<td><b>Pengurusan Fail Dokumen</b></td>
														</tr>
														<tr>
															<td>
																<a href="javascript:gotoLogDokumen()" class="help" title="Senarai Log Dokumen">
																	<font color="blue"><li>&nbsp;Log Dokumen (Surat/Memo/Laporan)</li></font>	
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:gotoFail()" class="help" title="Senarai Fail">
																	<font color="blue"><li>&nbsp;Fail</li></font>	
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:gotoPergerakanFail()" class="help" title="Senarai Pergerakan Fail">
																	<font color="blue"><li>&nbsp;Pergerakan Fail</li></font>	
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
															<td><b>PLA</b> (Pengurusan Log Aduan)</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:gotoFLMS()" class="help" title="Pengurusan Log Aduan">
																	<font color="blue"><li>
																		#if($!check_notifikasi_aduan > 0)                        
																			<label style="background-color:blue" align="center" valign="top" > 
																				<b><font color="WHITE">&nbsp;<blink>$!check_notifikasi_aduan</blink></font></b>
																			</label>
																		#end
                             										&nbsp;Log Aduan</li></font>	
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
				</table>
			</td>
			<td width="39%" valign="top">
				<table width="100%" border="0" >
					<tr>
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
																<b>Statistik Fail di Pangkalan Data <br /><font color="blue">$!negeri_server</font></b>
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
					</tr>	
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

<script>
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
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&command=open_dashboard";
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

function gotoMyCalendar() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=lebah.pm.module.ActivitiesModule";
	document.${formName}.submit();
}

function gotoLogDokumen() {
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumen";
	document.${formName}.submit();
}

function gotoFail() {
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.PendaftaranFail";
	document.${formName}.submit();
}

function gotoPergerakanFail() {
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.PergerakanFail";
	document.${formName}.submit();
}

function gotoFLMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
	document.${formName}.submit();
}

function gotoFLMSstat() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan&command=paparLaporan";
	document.${formName}.submit();
}

function gotoProfile() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=lebah_app_UpdateUserProfileModule";
	document.${formName}.submit();
}

function gotoCalendar() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=lebah.pm.module.ActivitiesModule";
	document.${formName}.submit();
}

function gotoInbox() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.utils.FrmInboxUsers";
	document.${formName}.submit();
}

function gotoSMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.sms.mySMS";
	document.${formName}.submit();
}

function gotoSenaraiTugasan() {
    document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.pfd.SenaraiTugasan";
	document.${formName}.submit();
}

function gotoPendaftaranMesyuarat() {

	document.${formName}.action = "$EkptgUtil.getTabID("Mesyuarat",$portal_role)?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTemplate";
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
	var sek8 = '$!fail_sek8';
	var sek17 = '$!fail_sek17';
	var hapus = '$!fail_hapus';
	var semua = '$!jumlah_keseluruhan';
	var semua_pie = parseInt(sek8)+parseInt(sek17);
	var fail_selesai = '$!fail_selesai';
	var fail_xselesai = '$!fail_xselesai';
	
        window.onload = function (e)
        {
            var hbar = new RGraph.HBar('cvs', [parseInt(fail_xselesai),parseInt(fail_selesai)]);
            hbar.Set('chart.units.pre','');
            hbar.Set('chart.units.post','');
            hbar.Set('chart.colors', ['blue','red','blue','blue']);
            hbar.Set('chart.strokestyle', 'rgba(0,0,0,0)');
            hbar.Set('chart.labels.above', true);			
            //hbar.Set('chart.vmargin', 20);
            hbar.Set('chart.background.grid', true);
            hbar.Set('chart.labels', ['Belum Selesai','Selesai']);            
            if (!RGraph.isOld()) {
                hbar.Set('chart.tooltips', ['Belum Selesai','Selesai']);
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
</script>
    
<script>
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
</script>	   