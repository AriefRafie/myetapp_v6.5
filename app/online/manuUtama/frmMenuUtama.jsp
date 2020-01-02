<style type="text/css">
<!--
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
	color: #0000FF;
	text-decoration: underline;
}
a:link {
	color: #0000FF;
	text-decoration: none;
}
a:visited {
	color: #0000FF;
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
-->
</style>
#set ($portal_role = "online")

<fieldset><legend>PENGGUNA AWAM</legend>
<fieldset>
  <legend>Menu Utama</legend>
  <table width="100%" border="0">
    <tr>
      <td><fieldset>
          <legend><strong class="hyu2"><img src="../img/tray.gif" width="24" height="24" /></strong><span class="hyu"><a href="" spry:hover="hover"></a></span> Permohonan Pusaka Kecil</legend><table border="0" bordercolor="#CC9933">
        <tr>
          <td>&nbsp;</td>
          <td><img src="../img/main.jpg" width="20" height="20" alt="" /></td>
          <td><a href="javascript:statusPermohonan()">Status Permohonan</a></td>
          <td></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><img src="../img/main.jpg" width="20" height="20" alt="" /></td>
          <td><a href="javascript:PermohonanA()">Permohonan Borang A (Baru)</a></td>
          <td></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><img src="../img/main.jpg" width="20" height="20" alt="" /></td>
          <td>
          	<!-- <a href="javascript:PermohonanP()">Permohonan Borang P (Harta Ketinggalan /  Kuasa Tadbir(Lantik / Batal) / Pemegang Amanah (Lantik / Batal))</a> -->
          	Permohonan Borang P (Harta Ketinggalan /  Kuasa Tadbir(Lantik / Batal) / Pemegang Amanah (Lantik / Batal))
          	</td>
          <td></td>
        </tr>
      </table></fieldset></td>
    </tr>
    <tr>
      <td><fieldset>
          <legend><strong class="hyu2"><img src="../img/tray.gif" width="24" height="24" /></strong><span class="hyu"><a href="" spry:hover="hover"></a></span> Pengambilan Tanah</legend><table border="0" bordercolor="#CC9933">
        <tr style="display:none">
          <td colspan="3"><img src="../img/main.jpg" width="20" height="20" alt="" /> <a href="">Dokumen Permohonan</a></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><img src="../img/main.jpg" width="20" height="20" alt="" /></td>
          <td><a href="javascript:pengambilanTanah()">Daftar Bantahan (Pihak Berkepentingan)</a></td>
          <td></td>
        </tr>
      </table></fieldset></td>
    </tr>
    <tr>
      <td><fieldset>
         <legend><strong class="hyu2"><img src="../img/tray.gif" width="24" height="24" /></strong><span class="hyu"><a href="" spry:hover="hover"></a></span>Penguatkuasaan dan Hasil Persekutuan</legend><table border="0" bordercolor="#CC9933">
       <!--  
        <tr >
        	<td>&nbsp;</td>
        	<td><img src="../img/main.jpg" width="20" height="20" alt="" /></td>
          <td colspan="3"> <a href="javascript:pelepasanPHP()">Pelepasan</a></td>
        </tr>
        -->
        
        <tr>
          <td>&nbsp;</td>
          <td><img src="../img/main.jpg" width="20" height="20" alt="" /></td>
          <td><a href="javascript:penyewaanPHP()">Penyewaan</a></td>

        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><img src="../img/main.jpg" width="20" height="20" alt="" /></td>
          <td><a href="javascript:APBPHP()">Akta Pelantar Benua (APB)</a></td>

        </tr>
      </table></fieldset></td>
    </tr>
    <!-- 
    <tr>
       <td><fieldset>
           <legend><img src="../img/tray.gif" width="24" height="24" /></strong><span class="hyu"><a href=""> Pembayaran <em>Online</em></a></legend><table border="0" bordercolor="#CC9933">
        <tr>
          <td>&nbsp;</td>
        <td><img src="../img/main.jpg" width="20" height="20" alt="" /></td>
          <td colspan="2"><a href="javascript:FPX1()">Bayaran <em>Online</em></a></td>
          <td>&nbsp;</td>
        </tr>
      </table></fieldset></td>
    </tr>
    -->
    <tr>
      <td><fieldset>
           <legend><strong class="hyu2"><img src="../img/tray.gif" width="24" height="24" /></strong><span class="hyu"><a href="" spry:hover="hover"></a></span>Aduan dan Cadangan</legend><table border="0" bordercolor="#CC9933">
        <tr>
          <td>&nbsp;</td>
        <td><img src="../img/main.jpg" width="20" height="20" alt="" /></td>
          <td colspan="2"><a href="javascript:aduan()">Aduan dan Cadangan</a></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        <td><img src="../img/main.jpg" width="20" height="20" alt="" /></td>
          <td colspan="2"><a href="javascript:statusAduan()">Status Aduan dan Cadangan</a></td>
          <td>&nbsp;</td>
        </tr>
      </table></fieldset></td>
    </tr>
    <tr>
      <td></td>
    </tr>
  </table>
</fieldset></fieldset>
<script>
function aduan(){
	document.${formName}.action = "$EkptgUtil.getTabID('My Info',$portal_role)?_portal_module=ekptg.view.online.aduan.ComplaintSenderModule";
	document.${formName}.submit();
}
function statusAduan(){
	document.${formName}.action = "$EkptgUtil.getTabID('My Info',$portal_role)?_portal_module=ekptg.view.online.aduan.ComplaintStatusModule";
	document.${formName}.submit();
}
function pengambilanTanah(){
	
	document.${formName}.action = "$EkptgUtil.getTabID('Pengambilan Tanah',$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanDaftarOnline";
	document.${formName}.submit();
	
}
function pelepasanPHP(){
	document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portal_role)?_portal_module=ekptg.view.php2.online.FrmPLPOnlineSenaraiFailView";
	document.${formName}.submit();
	
}
function penyewaanPHP(){
	document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portal_role)?_portal_module=ekptg.view.php2.online.FrmPYWOnlineSenaraiFailView";
	document.${formName}.submit();
	
}
function APBPHP(){
	document.${formName}.action = "$EkptgUtil.getTabID('Penguatkuasaan dan Hasil Persekutuan',$portal_role)?_portal_module=ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView";
	document.${formName}.submit();
	
}
function FPX1(){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembayaran Online',$portal_role)?_portal_module=ekptg.fpx.FrmFPXView";
	document.${formName}.submit();
	
}
function statusPermohonan(){
	document.${formName}.action = "$EkptgUtil.getTabID('Permohonan Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
	document.${formName}.submit();
	
}
function PermohonanA(){
	document.${formName}.action = "$EkptgUtil.getTabID('Permohonan Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
	document.${formName}.submit();
	
}
function PermohonanP(){
	document.${formName}.action = "$EkptgUtil.getTabID('Permohonan Pusaka Kecil',$portal_role)?_portal_module=ekptg.view.ppk.FrmBorangPSek17Online";
	document.${formName}.submit();
	
}
</script>