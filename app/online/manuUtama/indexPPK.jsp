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
#set ($portal_role = "online")
<br>
<p>
<h4>
Selamat datang ke Sistem eTaPP Online - <B>Pusaka Kecil.</B> Sila klik menu di bawah untuk memulakan urusan anda.
</h4>
</p>
<table cellpadding="4" cellspacing="2" border="0" width="100%" >
	<tr>
		
		<td width="50%" valign="top" class="row">
		<div>
			<a href="javascript:goTo('PPKStatus')">
			<h3>Semakkan Status Permohonan</h3>
			<p>
				Semakkan status permohonan berdasarkan MYID/No. KP Pemohon atau Si Mati
			</p>
			</a>
		</div>
		</td>
		
		
		<td width="50%" valign="top" class="row">
		<div>
			<a href="javascript:goTo('PPKBorangA')">
			<h3>Permohonan</h3>
			<p>
				Permohonan awal pembahagian pusaka (Borang A)
			</p>
			</a>
		</div>
		</td>
	
	
	</tr>
	
	<tr>
				
		
		<td width="50%" valign="top" class="rowMiddle">
		<div>
		<a href="javascript:goTo('PPKBorangP')">
		
			<h3>Permohonan Terkemudian</h3>
			<p>
				Permohonan Terkemudian Pembahagian Pusaka Kecil Harta Ketinggalan / Lantik Kuasa Tadbir / Batal Kuasa Tadbir / Lantik Pemegang Amanah / Batal Pemegang Amanah (Borang P)
			</p>
		
		</a>
		</div>
		
		</td>
		
		
		<td width="50%" valign="top" class="rowMiddle">
			<div>
				<a href="javascript:goTo('MENUUTAMA')">
		
					<h3>Menu Utama</h3>
					<p>
						Kembali ke Menu Utama
					</p>
				
				</a>
			</div>
		</td>
		
	
	</tr>
	
	



</table>
<script>
	function goTo(location){
		if(location=='PPKStatus'){
			
			document.${formName}.action = "$EkptgUtil.getTabID('Permohonan Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
			document.${formName}.submit();

			//document.${formName}.action = "$EkptgUtil.getTabID('Permohonan Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
			//document.${formName}.submit();
		}
		else if(location=='PPKBorangA'){
			document.${formName}.action = "$EkptgUtil.getTabID('Permohonan Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
			document.${formName}.submit();
		}

		else if(location=='MENUUTAMA'){
			document.${formName}.action = "$EkptgUtil.getTabID('My Info',$portal_role)?_portal_module=ekptg.view.online.FrmOnlineMenuUtama";
			document.${formName}.submit();
		}
	}

</script>