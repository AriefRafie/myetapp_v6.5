#set ($portal_role = "online")
<p>
Selamat datang ke Sistem eTaPP Online - <B>Penguatkuasaan & Hasil Persekutuan</B> Sila klik menu di bawah untuk memulakan urusan anda.

</p>
<table cellpadding="1" cellspacing="4" border="1" width="100%" >
	<tr>
		
		<td width="50%">
		<div>
			<a href="javascript:goTo('PHPPenyewaan')">
			<h3>Penyewaan</h3>
			<p>
				Daftar permohonan penyewaan dan semakkan status
			</p>
			</a>
		</div>
		</td>
		
		
		<td width="50%">
		<div>
			<a href="javascript:goTo('PHPApb')">
			<h3>Akta Pelantar Benua (APB)</h3>
			<p>
				Daftar permohonan APB dan semakkan status
			</p>
			</a>
		</div>
		</td>
	
	
	</tr>
	
	<tr>
				
		
		<td width="50%">
		<div>
		<a href="javascript:goTo('PPKBorangP')">
		
			<h3>Pelepasan</h3>
			<p>
				Daftar permohonan Pelepasan dan semakkan status
			</p>
		
		</a>
		</div>
		
		</td>
		
		
		<td width="50%">
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
		if(location=='MENUUTAMA'){
			document.${formName}.action = "$EkptgUtil.getTabID('My Info',$portal_role)?_portal_module=ekptg.view.online.FrmOnlineMenuUtama";
			document.${formName}.submit();
		}
	}

</script>