<style type="text/css">

.row:hover {
background-color:#F3F781;

}
.row{

}
.rowMiddle{
border-bottom:dotted 1px #669999;
border-top:none;

}
.rowMiddle:hover {
background-color:#F3F781;

}


table.nav{
border-top:3px solid #C7A317;
border-left:none;
border-right:none;
border-bottom:1px solid #C7A317;
}

.tooltip {
	position: absolute!important;
	overflow:hidden;
	font-size: 12px;
	z-index: 10000!important;
}
	.tooltip .xtop, .tooltip .xbottom { background: transparent; font-size: 0px; }
	.tooltip .xb1, .tooltip .xb2, .tooltip .xb3, .tooltip .xb4 { display: block; overflow: hidden; }
	.tooltip .xb1, .tooltip .xb2, .tooltip .xb3 { height: 0px; }
	.tooltip .xb2, .tooltip .xb3, .tooltip .xb4 { background: #666;}
	.tooltip .xbottom .xb2, .tooltip .xbottom .xb3, .tooltip .xbottom .xb4 { background: #666; }
	.tooltip .xb1 { margin: 0 0px; }
	.tooltip .xb2 { margin: 0 0px; }
	.tooltip .xb3 { margin: 0 0px; }
	.tooltip .xb4 { height: 0px;}

	.tooltip .xboxcontent {
		padding: 0 .5em;
		margin: 0;
		color: #000;
		word-wrap:break-word;
		border: none;
		background-color:white;
		font-family: Verdana;
	}
	
	

a.nav:hover {
color: black;
background-color: white;
text-decoration: underline overline black;
}

a.nav:link{
	link:#333333;
}
a.nav:visited {color: #333333; text-decoration: none; }	
</style>
<script type="text/javascript" src="../library/online/prototype.js"></script>
<script type="text/javascript" src="../library/online/scriptaculous/scriptaculous.js"></script>
<script type="text/javascript" src="../library/online/tooltips.js"></script>

#set ($portal_role = "online")

<p>

<h3>&nbsp;&nbsp;<font color="#7E3517">Selamat Datang $user.name ke Aplikasi Atas Talian JKPTG <i>Online</i></font> </h3>
&nbsp;&nbsp;<b>Sila Klik Menu di Bawah Untuk Memulakan Urusan Anda.</b>

</p>
<p align="center">
<table cellpadding="2" cellspacing="1" border="0" width="100%" class="nav" align="center">
	<tr>
		<td>&nbsp;</td>
	</tr>
	
	<tr>
		<td width="50%" valign="top">
			<table>
				<tr>
					<td rowspan="3" valign="center">
						&nbsp;&nbsp;<img src="../img/online/kblogger.png"/>
					</td>
					<td valign="top" colspan="3">
						&nbsp;<b>Aduan/Cadangan</b>
					</td>
					
				</tr>
				<tr>
				<td>&nbsp;</td>
					<td valign="top" width="90%">
					
						<a href="javascript:goTo('ADUAN')" class="help" title="Hantar Aduan, Cadangan atau Pertanyaan">
						
						
							<li>Aduan / Cadangan Baru</li>
						
						</a>
						
						
						
						
					</td>
					
					<td>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<a href="javascript:goTo('STATUSADUAN')" class="help" title="Semak Status Aduan, Cadangan atau Pertanyaan">
						<li>Status Aduan / Cadangan</li>
					</a>
					</td>
					<td></td>
				</tr>
			</table>
		</td>
		
		<td width="50%" valign="top">
		
			<table>
				<tr>
					<td valign="top" rowspan="4">
						<img src="../img/online/rentIcon.gif" align="top" height="56px" width="58px"/>
					</td>
					<td valign="top" colspan="3">
						<b> Penguatkuasaan dan Hasil Persekutuan</b>
					</td>
					
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top" >
						
						<a href="javascript:goTo('PHP')" class="help" title="Pendaftaran Permohonan Penyewaan Tanah dan Ruang Bangunan">
						
						
						 	<li>Penyewaan</li>
						
						</a>
						
					</td>
					
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top" width="90%" >
						
						<a href="javascript:goTo('PHPAPB')" class="help" title="Pendaftaran permohonan lesen pasir">
						
						
						 	<li>Akta Pelantar Benua(APB)</li>
						
						</a>
						
					</td>
					
				
				</tr>
				
				
			</table>
		
			
		</td>
		
	</tr>
	
	<tr>
		
		<td valign="top" width="50%">
			<table>
				
				<tr>
					<td valign="top" rowspan="4">
						&nbsp;&nbsp;<img src="../img/online/sketch.png" align="center"/>
					</td>
					
					<td colspan="3">
						&nbsp;<b> Pengambilan Tanah</b>
					</td>
				</tr>
				<tr>
				<td>&nbsp;</td>
					<td width="90%" valign="top">
						
						<a href="javascript:goTo('PPT')" class="help" title="Daftar Bantahan Pengambilan Tanah dan Semakan Status">
						
						
						 	<li>Bantahan Mahkamah</li>
						 	
						
						</a>
						
					</td>
					<td>
					
						&nbsp;
					</td>
				</tr>
				
			</table>
		</td>
		<td width="50%">
			<table>
				<tr>
					<td valign="top" rowspan="3">
						&nbsp;&nbsp;<img src="../img/online/help_contents.png" align="center"/>
					</td>
					<td valign="top" colspan="3">
						&nbsp;<b> Panduan</b>
					</td>
					
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top" width="90%">
						
						<a href="javascript:goTo('HELP')" class="help" title="Panduan Permohonan Secara Online Borang A dan Borang P">
						
						
						 	<li>Panduan Permohonan Secara <i>Online</i></li>
						
						</a>
						
					</td>
					<td>
					&nbsp;
					</td>
				
				</tr>
				<tr>
					<td>
					&nbsp;
					</td>
				</tr>
			</table>
		</td>
		
		
		
		
	
	</tr>
	
	<tr>
		
		
		<td valign="top" width="50%">
			<table>
				<tr>
					<td rowspan="2">
						&nbsp;&nbsp;<img src="../img/online/kwallet.png" align="center"/>
					</td>
					<td valign="top" colspan="3">
						&nbsp;<b> Pembayaran <i>Online</i></b>
					</td>
					
				</tr>
				<tr>
				<td>&nbsp;</td>
					<td valign="top" width="90%">
						
						<a href="javascript:goTo('FPX')" class="help"  title="Pembayaran kepada JKPTG secara online">
						
						
						 	<li>Bayaran <i>Online</i></li>
						
						</a>
						
					</td>
					<td>
					
						
					</td>
				
				</tr>
			</table>
		</td>
			
		<td valign="top">
		
			<table>
				<tr>
					<td valign="top" rowspan="2">
						&nbsp;&nbsp;<img src="../img/online/system_preferences.png" align="center"/>
					</td>
					<td valign="top" colspan="3">
						&nbsp;<b><i>My Profile</i></b>
					</td>
					
				</tr>
				<tr>
				<td>&nbsp;</td>
					<td valign="top">
						<div>
						<a href="javascript:goTo('MYINFO')" class="help" title="Tukar Kata Laluan">
						
						
						 	<li>Tukar Kata Laluan</li>
						
						</a>
						</div>
					</td>
				
				</tr>
			</table>
		
			
		</td>
	</tr>
	

	<tr>
		
		
		<td width="50%" valign="top">
		
			<table>
				<tr>
					<td valign="center" rowspan="4">
						&nbsp;&nbsp;<img src="../img/online/home.png" align="center"/>
					</td>
					<td valign="top" colspan="3">
						&nbsp;<b> Pusaka Kecil</b>
					</td>
					
				</tr>
				<tr>
				<td>&nbsp;</td>
					<td valign="top">
						<div>
						<a href="javascript:goTo('PPK')"  title="Daftar Permohonan Awal Pusaka Kecil Seksyen 8 (Borang A)" class="help">
						

						 <li> Permohonan Seksyen 8</li>
						 	
						
						
						</a>
						
						
						</div>
					</td>
					<td>
					
						
					</td>
				
				</tr>
				<tr>
				<td>&nbsp;</td>
					<td valign="top">
						
						<a href="javascript:goTo('PPKSEK17')" class="help" title="Permohonan Berikutnya Pembahagian Pusaka Kecil, Harta Ketinggalan / Lantik Kuasa Tadbir / Batal Kuasa Tadbir / Lantik Pemegang Amanah / Batal Pemegang Amanah (Borang P) ">
						
						
						 	<li>Permohonan Seksyen 17</li>
						
						</a>
						
					</td>
					<td>
						
					</td>
				
				</tr>
				<tr>
				<td>&nbsp;</td>
					<td valign="top">
						<div>
						<a href="javascript:goTo('PPKSTATUS')" class="help" title="Semakan Status Permohonan Pusaka">
						
						
						 	<li>Status Permohonan Pusaka</li>
						
						</a>
						</div>
					</td>
				
				</tr>
			</table>
		
			
		</td>
		
	
	</tr>

</table>
</p>
<script type="text/javascript" charset="utf-8">
		
		$$("a.help").each( function(input) {
			new Tooltip(input, {backgroundColor: "#0000", borderColor: "#0000", textColor: "black",opacity:"100"});
		});
		$$("form input.help").each( function(input) {
			new Tooltip(input, {backgroundColor: "#FC9", borderColor: "#C96", textColor: "#000", textShadowColor: "#FFF"});
		});
	</script>
<script>
	function goTo(location){
		//alert(location);
		if(location=='PPK'){
			
			document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
			document.${formName}.submit();

			//document.${formName}.action = "$EkptgUtil.getTabID('Permohonan Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
			//document.${formName}.submit();
		}
		else if(location=='PPKSEK17'){
			
			document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.ppk.FrmBorangPSek17Online";
			document.${formName}.submit();

			//document.${formName}.action = "$EkptgUtil.getTabID('Permohonan Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
			//document.${formName}.submit();
		}
		else if(location=='PPKSTATUS'){
			
			//document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.online.FrmOnlineMenuPPK";
			//document.${formName}.submit();

			document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
			document.${formName}.submit();
		}
		else if(location=='PPT'){
			document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanDaftarOnline";
			document.${formName}.submit();
		}
		else if(location=='PHP'){
			document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.php2.online.FrmPYWOnlineSenaraiFailView";
			document.${formName}.submit();
		}
		else if(location=='PHPAPB'){
			document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView";
			document.${formName}.submit();
		}
		else if(location=='ADUAN'){
			document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.online.aduan.ComplaintSenderModule";
			document.${formName}.submit();
		}
		else if(location=='STATUSADUAN'){
			document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.online.aduan.ComplaintStatusModule";
			document.${formName}.submit();
		}
		else if(location=='FPX'){
			document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.fpx.FrmFPXView";
			document.${formName}.submit();
		}
		
		else if(location=='MYINFO'){
			document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=lebah_app_UpdateUserProfileModule";
			document.${formName}.submit();
		}
		else if(location=='HELP'){
			document.${formName}.action = "$EkptgUtil.getTabID('Panduan',$portal_role)?_portal_module=ekptg.view.FrmManualPengguna";
			document.${formName}.submit();
		}
		
	}


	function statusPermohonan(){
		document.${formName}.action = "$EkptgUtil.getTabID('Permohonan Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
		document.${formName}.submit();
		
	}

</script>