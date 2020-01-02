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
															<td><b>Perbincangan</b></td>
														</tr>
														<tr>
															<td>
																<a href="javascript:gotoInbox()" class="help" title="Ruang Perbincangan Formal">
																	<font color="blue"><li>
																	#if($!notifikasi_inbox > 0)                         
																		<label style="background-color:blue" align="center" valign="top" > 
																			<b><font color="WHITE"><blink>$!notifikasi_inbox</blink></font></b>
																		</label>
																	#end
																	Ruang Perbincangan Formal</li></font>						
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
															<td><b>Aduan</b></td>
														</tr>
														<tr>
															<td>
																<a href="javascript:gotoAgihanAduan()" class="help" title="Agihan Aduan">
																	<font color="blue"><li>&nbsp;Agihan Aduan</li></font>						
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:gotoPendaftaranAduan()" class="help" title="Pendaftaran Aduan (Manual)">
																	<font color="blue"><li>&nbsp;Pendaftaran Aduan (Manual)</li></font>						
																</a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
									<td width="50%" valign="top">
										<table width="100%" border="0">
											<tr>
												<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/new_application.png" align="center"/></td>
												<td width="85%">
													<table>
														<tr>
															<td><b>Laporan</b></td>
														</tr>
														<tr>
															<td>
																<a href="javascript:gotoLaporanBulanan()" class="help" title="Laporan Bulanan">
																	<font color="blue"><li>&nbsp;Laporan Bulanan</li></font>						
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:gotoLaporanKumulatif()" class="help" title="Laporan Kumulatif">
																	<font color="blue"><li>&nbsp;Laporan Kumulatif</li></font>						
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
												<td width="70%" >
													<table width="100%">
														<tr>
															<td colspan="3">
																<b>Statistik (Aduan, Cadangan, Pertanyaan dan lain-lain) di Pangkalan Data <br /><font color="blue">$!negeri_sever</font></b>
															</td>
														</tr>
														<tr>
															<td width="50%" valign="top">
																<font color="blue"><li>Jumlah Keseluruhan</li></font>
															</td>
															<td width="1%" valign="top">:</td>
															<td width="49%" valign="top">
																<b>$!jumlah</b>
															</td>
														</tr>
														<tr>
															<td valign="top">
																<font color="blue"><li>Jumlah Aduan</li></font>
															</td>
															<td valign="top">:</td>
															<td valign="top">
																<b>$!jumlah_aduan</b>
															</td>
														</tr>
														<tr>
															<td valign="top">
																<font color="blue"><li>Jumlah Pertanyaan</li></font>
															</td>
															<td valign="top">:</td>
															<td  valign="top">
																<b>$!jumlah_pertanyaan</b>
															</td>
														</tr>
														<tr>
															<td valign="top">
																<font color="blue"><li>Jumlah Cadangan</li></font>
															</td>
															<td valign="top">:</td>
															<td  valign="top">
																<b>$!jumlah_cadangan</b>
															</td>
														</tr>
														<tr>
															<td valign="top">
																<font color="blue"><li>Jumlah Lain-lain</li></font>
															</td>
															<td valign="top">:</td>
															<td  valign="top">
																<b>$!jumlah_lain</b>
															</td>
														</tr>
													</table>
												</td>
												<td width="15%" align="center" valign="top"></td>
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
										<div id="TabbedPanels1" class="TabbedPanels">
											<ul class="TabbedPanelsTabGroup">
												#if($list_memo_aktif.size()>0)
													<li class="TabbedPanelsTab" tabindex="0" id="Pengumuman_Head">Pengumuman</li>
												#end
												<li class="TabbedPanelsTab" tabindex="0" id="Carta_Head">Carta</li>
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
												<div class="TabbedPanelsContent" id="Carta">   
													<div style="height:250" id="div_stat">
														<table width="100%" >
															<tr>
																<td width="12%" align="center" valign="top">
																	<img width="30" height="30" src="../img/images_stat.png" align="center"/>
																</td>
																<td width="88%" valign="top">
																	<table width="100%">
																		<tr>
																			<td valign="top" >
																				<b>Carta Status (Aduan, Pertanyaan, Cadangan dan Lain-lain) di Pengkalan Data</b>
																			</td>
																		</tr>
																		<tr>
																			<td valign="top" >
																				<canvas id="cvs" width="350px" height="180px" border="1px solid #000">[No canvas support]</canvas>
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
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
			<td width="1%" valign="top"></td>
		</tr>
	</table>
</p>

<script>
function gotoInbox() {
	document.${formName}.action = "$EkptgUtil.getTabID("Aduan Online",$portal_role)?_portal_module=ekptg.view.utils.FrmInboxUsers";
	document.${formName}.submit();
}

function gotoPendaftaranAduan() {
	document.${formName}.action = "$EkptgUtil.getTabID("Aduan Online",$portal_role)?_portal_module=ekptg.view.online.aduan.ComplaintManualModule";
	document.${formName}.submit();
}

function gotoAgihanAduan() {
    document.${formName}.action = "$EkptgUtil.getTabID("Aduan Online",$portal_role)?_portal_module=ekptg.view.online.aduan.OnlineComplaintManagerModule";
	document.${formName}.submit();
}

function gotoLaporanBulanan() {

	document.${formName}.action = "$EkptgUtil.getTabID("Laporan",$portal_role)?_portal_module=ekptg.view.online.aduan.ComplaintLaporanBulanan";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function gotoLaporanKumulatif() {
	document.${formName}.action = "$EkptgUtil.getTabID("Laporan",$portal_role)?_portal_module=ekptg.view.online.aduan.ComplaintLaporanKumulatif";
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
	var jumlah_selesai_aduan = '$!jumlah_selesai_aduan';
	var jumlah_dalam_proses_aduan = '$!jumlah_dalam_proses_aduan';
	var jumlah_selesai_pertanyaan = '$!jumlah_selesai_pertanyaan';
	var jumlah_dalam_proses_pertanyaan = '$!jumlah_dalam_proses_pertanyaan';
	var jumlah_selesai_cadangan = '$!jumlah_selesai_cadangan';
	var jumlah_dalam_proses_cadangan = '$!jumlah_dalam_proses_cadangan';
	var jumlah_selesai_lain = '$!jumlah_selesai_lain';
	var jumlah_dalam_proses_lain = '$!jumlah_dalam_proses_lain';
	
        window.onload = function (e)
        {
        	//---------------------------->Aduan
            var hbar = new RGraph.HBar('cvs', [parseInt(jumlah_selesai_aduan),parseInt(jumlah_dalam_proses_aduan),parseInt(jumlah_selesai_pertanyaan),parseInt(jumlah_dalam_proses_pertanyaan),parseInt(jumlah_selesai_cadangan),parseInt(jumlah_dalam_proses_cadangan),parseInt(jumlah_selesai_lain),parseInt(jumlah_dalam_proses_lain)]);
            hbar.Set('chart.units.pre','');
            hbar.Set('chart.units.post','');
            //hbar.Set('chart.title','Carta Status (Aduan, Pertanyaan, Cadangan dan Lain-lain) di Pengkalan Data');
            hbar.Set('chart.colors.sequential', true);
            hbar.Set('chart.colors', ['blue', 'red', 'blue', 'red', 'blue', 'red', 'blue', 'red']);
            hbar.Set('chart.key.position.gutter.boxed',true);
            //hbar.Set('chart.grouping','grouped');
            //hbar.Set('chart.strokestyle', 'rgba(0,0,0,0)');
            hbar.Set('chart.background.barcolor1','rgba(0,0,255,0)');
            hbar.Set('chart.labels.above', true);
            //hbar.Set('chart.annotatable', true);			
            //hbar.Set('chart.vmargin', 20);
            hbar.Set('chart.background.grid', true);
            hbar.Set('chart.labels', ['Aduan','Pertanyaan','Cadangan','Lain-Lain']);            
            if (!RGraph.isOld()) {
            	hbar.Set('chart.tooltips', ['Selesai','Dalam Proses','Selesai','Dalam Proses','Selesai','Dalam Proses','Selesai','Dalam Proses']);
            }            
            hbar.Set('chart.labels.above.decimals',0);
            hbar.Set('chart.xlabels', false);
            hbar.Set('chart.gutter.left', 90);
            hbar.Set('chart.gutter.right', 50);
            hbar.Set('chart.gutter.top',10);
            hbar.Set('chart.gutter.bottom',0);
            //hbar.Set('chart.text.size',5);
       		hbar.Set('chart.text.color','black');
			hbar.Set('chart.text.font','Arial');
            hbar.Set('chart.noxaxis', true);
            hbar.Set('chart.noxtickmarks', true);
            hbar.Set('chart.noytickmarks', true);
            RGraph.isOld() ? hbar.Draw() : RGraph.Effects.HBar.Grow(hbar);
        }
</script>
    
<script>
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
</script>	   