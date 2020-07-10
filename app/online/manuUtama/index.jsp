#set ($portal_role = "online")

<input type="hidden" id="jenismodul" name="jenismodul"  >
<input type="hidden" id="namamodul" name="namamodul"  >
<input type="hidden" id="namatab" name="namatab"  >
<input type="hidden" name="user_id" id="user_id" value="$user_id"/>

<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td width="1%">&nbsp;</td>
		<td align="left"><font color="#7E3517" size="2"><b>$label_welcome $user.name $label_welcome_to</b></font></td>
	</tr>
	<tr>
		<td width="1%">&nbsp;</td>
		<td align="left"><b>$label_online_dashboard_inst</b></td>
		<td align="right"><b>$label_log_masuk_terakhir : $!log_terakhir</b></td>
	</tr>
</table>

<table cellpadding="2" cellspacing="1" border="0" width="100%" class="nav" align="center" >
<tr>
<td width="59%" valign="top">
	<table width="100%" border="0" align="left" class="dashboard_kiri">
	
	
	
	
	
	<tr>
	<td valign="top" align="left">
		<table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
        <tr>
        <!-- <td width="50%" valign="top">
			<table width="100%" border="0" class="regular">
			<tr>
						<td><b>Pembayaran <i>Online</i></b></td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('FPX')" class="help" title="Pembayaran kepada JKPTG secara online">
							<font color="blue"><li>Bayaran <i>Online</i></li></font>
						</a>
						</td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('DokumenPusaka')" class="help" title="Pembelian Dokumen Pusaka Bagi Modul PPK">
							<font color="blue"><li>Pembelian Dokumen Pusaka</li></font>
						</a>
						</td>
			</tr>
			</table>
		</td> -->
		<td width="50%" valign="top">
			<table width="100%" border="0" class="regular">
			<tr>
				<td width="15%" align="center" valign="top"><img src="../img/online/kwallet.png" align="center"/></td>
				<td width="85%">
					<table>
					<tr>
						<td><b>Profil</b></td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('MYINFO')" class="help" title="Tukar Kata Laluan">
							<font color="blue"><li>$change_password</li></font>
						</a>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
		</td>
		<td width="50%" valign="top">
			<table width="100%" border="0" class="regular">
			<tr>
				<td width="15%" align="center" valign="top"><img src="../img/online/help_contents.png" align="center"/></td>
				<td width="85%">
					<table>
					<tr>
						<td><b>Panduan</b></td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('HELP')" class="help" title="Panduan Permohonan Secara Online Borang A dan Borang P">
							<font color="blue"><li>Panduan Permohonan Secara <i>Online</i></li></font>
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
		<table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
        <tr>
		<td width="50%" valign="top">
			<table width="100%" border="0" class="regular">
			<tr>
				<td width="15%" align="center" valign="top"><img src="../img/online/sketch.png" align="center"/></td>
				<td width="85%">
					<table>
					<tr>
						<td><b>Pengambilan Tanah</b></td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('PPT')" class="help" title="Semakan Status Pampasan">
							<font color="blue"><li>Pampasan</li></font>
						</a>
						</td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('PPTSB')" class="help" title="Semakan Status Bicara">
							<font color="blue"><li>Status Bicara</li></font>
						</a>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
		</td>
		<td width="50%" valign="top">
			<table width="100%" border="0" class="regular">
			<tr>
				<td width="15%" align="center" valign="top"><img src="../img/online/Maps.png" align="center" height="56px" width="55px"/></td>
				<td width="85%">
					<table>
					<tr>
						<td><b>Penguatkuasaan dan Hasil Persekutuan</b></td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('PHPAPB')" class="help" title="Permohonan Urusan Akta Pelantar Benua">
							<font color="blue"><li>Permohonan Urusan Akta Pelantar Benua</li></font>
						</a>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td> -->
<!-- 						<a href="javascript:goTo('')" class="help" title="Pengaduan Aktiviti Pencerobohan"> -->
<!-- 							<font color="blue"><li>Permohonan Penguatkuasaan</li></font> -->
<!-- 						</a> -->
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<td>
						<a href="javascript:goTo('PermohonanSewa')" class="help" title="Permohonan Urusan Penyewaan">
							<font color="blue"><li>Permohonan Penyewaan</li></font>
						</a>
						</td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('PembayaranSewa')" class="help" title="Penyemakan Status Pembayaran Sewa Tanah dan Ruang Bangunan">
							<font color="blue"><li>Status Pembayaran Sewa</li></font>
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
		<table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
        <tr>
		<td width="50%" valign="top">
			
			<table width="100%" border="0" class="regular">
			<tr>
				<td width="15%" align="center" valign="top"><img src="../img/online/home.png" align="center"/></td>
				<td width="85%">
					<table>
					<tr>
						<td><b>Pusaka Kecil</b></td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('PPK')" class="help" title="Daftar Permohonan Awal Pusaka Kecil Seksyen 8 (Borang A)">
							<font color="blue"><li>Permohonan Seksyen 8</li></font>
						</a>
						</td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('PPKSEK17')" class="help" title="Permohonan Berikutnya Pembahagian Pusaka Kecil, Harta Ketinggalan / Lantik Kuasa Tadbir / Batal Kuasa Tadbir / Lantik Pemegang Amanah / Batal Pemegang Amanah (Borang P)">
							<font color="blue"><li>Permohonan Seksyen 17</li></font>
						</a>
						</td>
					</tr>
					<!-- <tr>
						<td>
						<a href="javascript:goTo('DERAFPPK')" class="help" title="Deraf Permohonan PPK">
							<font color="blue"><li>Deraf Permohonan PPK</li></font>
						</a>
						</td>
					</tr> -->
					<tr>
                    <td>
                    <a href="javascript:goTo('DERAFPPK')" class="help" title="Deraf Permohonan PPK">
							<font color="blue"><li>
							#if($notifikasiDeraf > 0)
								<label style="background-color:blue" align="center" valign="top" > 
									<b><font color="WHITE" ><span class="blink">$notifikasiDeraf</span></font></b>
								</label>&nbsp;
							#end
							Deraf Permohonan PPK
							</li></font>
					</a>
                    </td>
                    </tr>
					<tr>
						<td>
						<a href="javascript:goTo('PPKSTATUS')" class="help" title="Semakan Status Permohonan Pusaka">
							<font color="blue"><li>Status Permohonan Pusaka</li></font>
						</a>
						</td>
					</tr>
					<tr>
						<td>
							<a href="javascript:goTo('PPKBANTAHAN')" class="help" title="Permohonan Bantahan">
								<font color="blue"><li>Permohonan Bantahan</li></font>
							</a>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td> -->
<!-- 						<a href="javascript:goTo('SENARAI')" class="help" title="Senarai Notis Perbicaraan Pembahagian Pusaka"> -->
<!-- 							<font color="blue"><li>Senarai Notis Perbicaraan Pembahagian Pusaka</li></font> -->
<!-- 						</a> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
<!-- 						<a href="javascript:goTo('PENGGANTI')" class="help" title="Permohonan Pengganti Pempetisyen"> -->
<!-- 							<font color="blue"><li>Permohonan Pengganti Pempetisyen</li></font> -->
<!-- 						</a> -->
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<td>
							<a href="javascript:goTo('dikembalikan')" class="help" title="Penyewaan">
								<font color="blue"><li>
									##if($jumlah_notifikasi_penyewaan > 0)
								<label style="background-color:blue" align="center" valign="top" > 
								<b><font color="WHITE"><blink>$!bilPPKkembali</blink></font></b>
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
		<td width="50%" valign="top">
			<table width="100%" border="0" class="regular">
			<tr>
				<td width="15%" align="center" valign="top"><img src="../img/online/kblogger.png" align="center"/></td>
				<td width="85%">
					<table>
					<tr>
						<td><b>Aduan / Cadangan</b></td>
					</tr>
					<tr>
						<td>
						<!-- <a href="javascript:goTo('ADUAN')" class="help" title="Membuat Aduan, Memberi Cadangan atau Pertanyaan">
							<font color="blue"><li>Hantar</li></font>
						</a> -->
						<a href="javascript:goTo('daftarAduan')" class="help" title="Membuat Aduan, Memberi Cadangan atau Pertanyaan">
							<font color="blue"><li>Daftar Aduan</li></font>
						</a>
						</td>
					</tr>
					<tr>
						<td>
						<!-- <a href="javascript:goTo('STATUSADUAN')" class="help" title="Semak Status Aduan, Cadangan atau Pertanyaan">
							<font color="blue"><li>Status</li></font>
						</a> -->
						</td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('senaraiAduan')" class="help" title="Status Aduan">
							<font color="blue"><li>
								#if($notifikasiaAduan > 0)
									<label style="background-color:blue" align="center" valign="top" ><b><font color="WHITE" ><span class="blink">$notifikasiaAduan</span></font></b></label>
								#end
							Status Aduan
							</li></font>
						</a>
						<script>/*
						$jquery(document).ready(function () {
							doDivAjaxCall$formname('divNotifikasiAduan','showNotifikasiAduan','');				
						});
						*/
						</script>
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
		<td width="50%" valign="top">
			<table width="100%" border="0" class="regular">
			<tr>
				<td width="15%" align="center" valign="top"><img src="../img/online/kwallet.png" align="center"/></td>
				<td width="85%">
					<table>
					<tr>
						<td><b>Harta Tanah Persekutuan</b></td>
					</tr>
					<tr>
						<td>
						<a href="javascript:goTo('HTP')" class="help" title="Daftar Permohonan Pajakan">
							<font color="blue"><li>Permohonan Pajakan</li></font>
						</a>
						</td>
					</tr>
					<tr>
					<td>
					<a href="javascript:permhonanDikembalikan()" class="help" title="Permohonan Dikembalikan">
						<font color="blue"><li>&nbsp;Permohonan Dikembalikan</li></font>	
						</a>
					</td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
		</td>
		<td width="50%" valign="top">
			<table width="100%" border="0" class="regular">
			<tr>
				<td width="15%" align="center" valign="top"></td>
				<td width="85%">
					<table>
					<tr>
						<td><b></b></td>
					</tr>
					<tr>
						<td>
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

#if($list_memo_aktif.size()>0)
<td width="39%" valign="middle">
	<table width="100%" border="0">
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
								#parse("app/online/manuUtama/frmPengumuman.jsp")
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
	</table>
</td>
#end

</tr>
</table>

<input type="hidden" name="fromDashboard" id="fromDashboard" />
<script>

	function tindakanPPK(){
		document.${formName}.action = "?myrole=ppk-online-user";
		document.${formName}.namatab.value = "Pusaka Kecil";
        document.${formName}.namamodul.value = "ekptg.view.ppk.FrmDraffPermohonanOnlinePPK";
		document.${formName}.submit();
		
	}
goMenuTab('$!nTab');

function goMenuTab(tab){
if ( tab != '' ){
        document.${formName}.action = "$EkptgUtil.getTabID($!nTab,$myrole)?_portal_module=" + "$!nModul";
        document.${formName}.submit();
}
document.${formName}.namamodul.value = "";
document.${formName}.namatab.value = "";
}

	function goTo(location){
		document.${formName}.namamodul.value = "";
		document.${formName}.namatab.value = "";
		
		if(location=='PPK'){
			document.${formName}.action = "?myrole=ppk-online-user";
		    document.${formName}.namatab.value = "Pusaka Kecil";
		    document.${formName}.namamodul.value = "ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
		    document.${formName}.submit();
		
		}else if(location=='dikembalikan'){
			actionParam = "&_portal_module=ekptg.view.ppk.FrmDraffPermohonanOnlinePPK&command="+location;
			//1345099281439
			//alert("$EkptgUtil.getTabID('Pusaka Kecil','ppk-online-user')");
			document.${formName}.action = "$EkptgUtil.getTabID('Pusaka Kecil','ppk-online-user')?"+actionParam;
			/* document.${formName}.action = "?myrole=ppk-online-user"+actionParam;
		    document.${formName}.namatab.value = "Pusaka Kecil";
		    document.${formName}.namamodul.value = "ekptg.view.ppk.FrmDraffPermohonanOnlinePPK";
    */
			document.${formName}.submit();
        
		}else if(location=='daftarAduan'){
	
		document.${formName}.action = "$EkptgUtil.getTabID("Menu",online)?_portal_module=ekptg.view.online.FrmAduanPublic&fromDashboard=Y";
		document.${formName}.submit();
}
else if(location=='senaraiAduan'){
	
		document.${formName}.action = "$EkptgUtil.getTabID("Menu",online)?_portal_module=ekptg.view.online.FrmAduanPublic&fromDashboard=";
		document.${formName}.submit();
}
else if(location=='PPKSEK17'){
        document.${formName}.action = "?myrole=ppk-online-user";
        document.${formName}.namatab.value = "Pusaka Kecil";
        document.${formName}.namamodul.value = "ekptg.view.ppk.FrmPrmhnnBorangPOnline"; 
        document.${formName}.submit();
}
else if(location=='PPKSTATUS'){
        document.${formName}.action = "?myrole=ppk-online-user";
        document.${formName}.namatab.value = "Pusaka Kecil";
        document.${formName}.namamodul.value = "ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
        document.${formName}.submit();
}

else if(location=='DERAFPPK'){
        document.${formName}.action = "?myrole=ppk-online-user";
        document.${formName}.namatab.value = "Pusaka Kecil";
        document.${formName}.namamodul.value = "ekptg.view.ppk.FrmDraffPermohonanOnlinePPK";
        document.${formName}.submit();
}
else if(location=='PPKBANTAHAN'){
    document.${formName}.action = "?myrole=ppk-online-user";
    document.${formName}.namatab.value = "Pusaka Kecil";
    document.${formName}.namamodul.value = "ekptg.view.ppk.FrmPrmhnnBantahanOnline";
    document.${formName}.submit();
}
else if(location=='SENARAI'){
    document.${formName}.action = "?myrole=ppk-online-user";
    document.${formName}.namatab.value = "Pusaka Kecil";
    document.${formName}.namamodul.value = "ekptg.view.online.CarianNotisPerbicaraan";
    document.${formName}.submit();
}
else if(location=='PENGGANTI'){
    document.${formName}.action = "?myrole=ppk-online-user";
    document.${formName}.namatab.value = "Pusaka Kecil";
    document.${formName}.namamodul.value = "ekptg.view.online.FrmTukarPemohon";
    document.${formName}.submit();
}
else if(location=='PPT'){
        document.${formName}.action = "?myrole=ppt-online-user";
        document.${formName}.namatab.value = "Pengambilan Tanah";
        document.${formName}.namamodul.value = "ekptg.view.ppt.FrmPampasanOnline";
        document.${formName}.submit();
}
else if(location=='PPTSB'){
    document.${formName}.action = "?myrole=ppt-online-user";
    document.${formName}.namatab.value = "Pengambilan Tanah";
    document.${formName}.namamodul.value = "ekptg.view.ppt.FrmStatusBicaraOnline";
    document.${formName}.submit();
}
else if(location=='PermohonanSewa'){
    document.${formName}.action = "?myrole=php-online-user";
    document.${formName}.namatab.value = "Penguatkuasaan dan Hasil Persekutuan";
    document.${formName}.namamodul.value = "ekptg.view.php2.online.FrmPYWOnlineSenaraiFailView";
    document.${formName}.submit();
}
else if(location=='PembayaranSewa'){
    document.${formName}.action = "?myrole=php-online-user";
    document.${formName}.namatab.value = "Penguatkuasaan dan Hasil Persekutuan";
    document.${formName}.namamodul.value = "ekptg.view.php2.online.FrmStatusPembayaranSewa";
    document.${formName}.submit();
}
else if(location=='PHPAPB'){
        document.${formName}.action = "?myrole=php-online-user";
        document.${formName}.namatab.value = "Penguatkuasaan dan Hasil Persekutuan";
        document.${formName}.namamodul.value = "ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView";
        document.${formName}.submit();
}
else if(location=='ADUAN'){
        document.${formName}.action = "?myrole=online";
        document.${formName}.namatab.value = "Menu";
        document.${formName}.namamodul.value = "ekptg.view.online.aduan.ComplaintSenderModule";
        document.${formName}.submit();
}
else if(location=='STATUSADUAN'){
        document.${formName}.action = "?myrole=online";
        document.${formName}.namatab.value = "Menu";
        document.${formName}.namamodul.value = "ekptg.view.online.aduan.ComplaintStatusModule";
        document.${formName}.submit();
}
else if(location=='PENERIMAANADUAN'){
        document.${formName}.action = "?myrole=online";
        document.${formName}.namatab.value = "Menu";
        document.${formName}.namamodul.value = "ekptg.view.online.aduan.ComplaintStatusModule";
        document.${formName}.submit();
}
else if(location=='FPX'){
        document.${formName}.action = "?myrole=online";
        document.${formName}.namatab.value = "Menu";
        document.${formName}.namamodul.value = "ekptg.fpx.FrmFPXView";
        document.${formName}.submit();
}
else if(location=='DokumenPusaka'){
        document.${formName}.action = "?myrole=online";
        document.${formName}.namatab.value = "Menu";
        document.${formName}.namamodul.value = "ekptg.fpx.FrmFPXView";
        document.${formName}.submit();
}
else if(location=='MYINFO'){
        document.${formName}.action = "?myrole=online";
        document.${formName}.namatab.value = "Menu";
        document.${formName}.namamodul.value = "ekptg.view.admin.UserProfileInternal";
        document.${formName}.submit();
}
else if(location=='INBOX'){
        document.${formName}.action = "?myrole=online";
        document.${formName}.namatab.value = "Menu";
        document.${formName}.namamodul.value = "ekptg.view.utils.FrmInboxUsersOnline";
        document.${formName}.submit();
}
else if(location=='HELP'){
        document.${formName}.action = "?myrole=online";
        document.${formName}.namatab.value = "Panduan";
        document.${formName}.namamodul.value = "ekptg.view.FrmManualPengguna";
        document.${formName}.submit();
}

else if(location=='HTP'){
    document.${formName}.action = "?myrole=online";
    //document.${formName}.action = "?myrole=htp-online-user";
	document.${formName}.namatab.value = "Harta Tanah Persekutuan";
    document.${formName}.namamodul.value = "ekptg.view.htp.online.FrmPajakanOnlineSenaraiFailView";
    document.${formName}.submit();
}
}

function statusPermohonan(){
document.${formName}.action = "?myrole=online";
document.${formName}.namatab.value = "Panduan";
document.${formName}.namamodul.value = "ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
document.${formName}.submit();
}
</script>

<script>
        var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
</script>