<style type="text/css">

.row:hover {
background-color:#F3F781;

}
.row{
border-bottom:dotted 1px #669999;
border-top:dotted 1px #669999;
}
.rowMiddle{
border-bottom:dotted 1px #669999;
border-top:none;

}
.rowMiddle:hover {
background-color:#F3F781;

}


table.nav{
border-top:none;
border-left:none;
border-right:none;
border-bottom:none;
}
</style>

#set($portal_role = "${session.getAttribute('myrole')}")

<p>

<B>Selamat datang ke Sistem JKPTG Online $!user.name .</b> <BR>Sila klik menu di bawah untuk memulakan urusan anda.

</p>
<table cellpadding="4" cellspacing="2" border="0" width="100%" class="nav">
	
	
	<tr>
		
		
		<td width="50%" valign="top" class="row">
			<div>
			<a href="javascript:goTo('ADUAN')">
			<h3>Aduan dan Cadangan</h3>
			<p>
			 Laporkan sebarang aduan, cadangan atau pertanyaan
			</p>
			</a>
			</div>
		</td>
		
		
		<td width="50%" valign="top" class="row">
		
			<div>
			<a href="javascript:goTo('PPK')">
			<h3>Pusaka Kecil</h3>
			<p>
			 Permohonan pembahagian pusaka dan semakkan status permohonan
			</p>
			</a>
			</div>
		</td>
	
	</tr>
	<tr>
		
		<td width="50%" valign="top" class="rowMiddle">
		<div>
			<a href="javascript:goTo('PPT')">
			<h3>Pengambilan Tanah</h3>
			<p>
				Semak status bantahan
			</p>
			</a>
		</div>
		</td>
		
		
		<td width="50%" valign="top" class="rowMiddle">
		<div>
			<a href="javascript:goTo('SEWA')">
			<h3>Penyewaan</h3>
			<p>
				Daftar permohonan Penyewaan dan semakan status.
			</p>
			</a>
		</div>
		</td>
	
	
	</tr>
	
	<tr>
				
		
		<td width="50%" valign="top" class="rowMiddle">
		<div>
		<a href="javascript:goTo('LESEN')">
		
			<h3>Lesen Pasir</h3>
			<p>
				Daftar Permohonan APB dan semakan status.
			</p>
		
		</a>
		</div>
		
		</td>
		
		
		<td width="50%" valign="top" class="rowMiddle">
			<div>
			<a href="javascript:goTo('PLP')">
			<h3>Pelepasan</h3>
			<p>
				Daftar permohonan Pelepasan dan semakan status.
			</p>
			
			</a>
			</div>
		</td>
		
	
	</tr>
	

	<tr>
		
		
		<td width="50%" valign="top" class="rowMiddle">
			<div>
			<a href="javascript:goTo('FPX')">
			<h3>Pembayaran Online</h3>
			<p>
				Pembayaran online menggunakan FPX
			</p>
			</a>
			</div>
		</td>
		
		
		<td width="50%" class="rowMiddle">
		
			<div>
			<a href="javascript:goTo('MYINFO')">
			<h3>My Profile</h3>
			<p>
				Tukar kata laluan
			</p>
			</a>
			</div>
		</td>
		
	
	
	</tr>

</table>
<script>
	function goTo(location){
		//alert(location);
		if(location=='PPK'){
			
			document.${formName}.action = "$EkptgUtil.getTabID('Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.online.FrmOnlineMenuPPK";
			document.${formName}.submit();

			//document.${formName}.action = "$EkptgUtil.getTabID('Permohonan Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
			//document.${formName}.submit();
		}
		else if(location=='PPT'){
			document.${formName}.action = "$EkptgUtil.getTabID('Pengambilan Tanah',$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanDaftarOnline";
			document.${formName}.submit();
		}
		else if(location=='PHP'){
			document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portal_role)?_portal_module=ekptg.view.online.FrmOnlineMenuPHP";
			document.${formName}.submit();
		}
		else if(location=='ADUAN'){
			document.${formName}.action = "$EkptgUtil.getTabID('Aduan',$portal_role)?_portal_module=ekptg.view.online.aduan.ComplaintSenderModule";
			document.${formName}.submit();
		}
		else if(location=='STATUSADUAN'){
			document.${formName}.action = "$EkptgUtil.getTabID('Aduan/Cadangan',$portal_role)?_portal_module=ekptg.view.online.aduan.ComplaintStatusModule";
			document.${formName}.submit();
		}
		else if(location=='FPX'){
			document.${formName}.action = "$EkptgUtil.getTabID('Pembayaran Online',$portal_role)?_portal_module=ekptg.fpx.FrmFPXView";
			document.${formName}.submit();
		}
		
		else if(location=='MYINFO'){
			document.${formName}.action = "$EkptgUtil.getTabID('MY Info',$portal_role)?_portal_module=ekptg.view.admin.UserProfileJKPTGOnline";
			document.${formName}.submit();
		}
		else if(location=='HELP'){
			document.${formName}.action = "$EkptgUtil.getTabID('Panduan',$portal_role)?_portal_module=ekptg.view.FrmManualPengguna";
			document.${formName}.submit();
		}
		else if(location=='SEWA'){
			document.${formName}.action = "$EkptgUtil.getTabID('Penyewaan',$portal_role)?_portal_module=ekptg.view.php2.online.FrmPYWOnlineSenaraiFailView";
			document.${formName}.submit();
		}
		else if(location=='LESEN'){
			document.${formName}.action = "$EkptgUtil.getTabID('Lesen Pasir',$portal_role)?_portal_module=ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView";
			document.${formName}.submit();
		}
		else if(location=='PLP'){
			document.${formName}.action = "$EkptgUtil.getTabID('Pelepasan',$portal_role)?_portal_module=ekptg.view.php2.online.FrmPLPOnlineSenaraiFailView";
			document.${formName}.submit();
		}
		
	}


	function statusPermohonan(){
		document.${formName}.action = "$EkptgUtil.getTabID('Permohonan Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
		document.${formName}.submit();
		
	}

</script>