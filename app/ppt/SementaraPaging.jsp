<!-- Arrow -->
#set($Arrow="<img border='0' src='../img/arrowgaris.png'>")

<!-- img paging (enabled) -->
#set($No1Enabled="<img border='0' title='Permohonan UPT' src='../img/1enable.png'>")
#set($No2Enabled="<img border='0' title='Hakmilik dan Pihak Berkepentingan' src='../img/2enable.png'>")
#set($No3Enabled="<img border='0' title='Agihan Tugas *Hanya Pengarah yang dibenarkan membuat agihan' src='../img/3enable.png'>")
#set($No4Enabled="<img border='0' title='Laporan Awal Tanah' src='../img/4enable.png'>")
#set($No5Enabled="<img border='0' title='Laporan Tanah Terperinci - Bangunan' src='../img/5enable2.png'>")
#set($No6Enabled="<img border='0' title='Laporan Tanah Terperinci - Tanah' src='../img/6enable.png'>")
#set($No7Enabled="<img border='0' title='Maklumat Jabatan Teknikal' src='../img/7enable.png'>")
#set($No8Enabled="<img border='0' title='Penyediaan Kertas MMK' src='../img/8enable.png'>")
#set($No9Enabled="<img border='0' title='Notis Awam' src='../img/9enable.png'>")
#set($No10Enabled="<img border='0' title='Bukti Penyampaian Notis' src='../img/10enable.png'>")
#set($No11Enabled="<img border='0' title='Maklumat JPPH' src='../img/11enable.png'>")
#set($No12Enabled="<img border='0' title='Penandaan Kawasan' src='../img/12enable2.png'>")
#set($No13Enabled="<img border='0' title='Perundingan' src='../img/13enable3.png'>")
#set($No14Enabled="<img border='0' title='Pampasan' src='../img/14enable.png'>")
#set($No15Enabled="<img border='0' title='Rujukan ke Mahkamah' src='../img/15enable2.png'>")


<!-- img paging (disabled) -->
#set($No1Disabled="<img border='0' title='Permohonan UPT' src='../img/1disable.png'>")
#set($No2Disabled="<img border='0' title='Hakmilik dan Pihak Berkepentingan' src='../img/2disable.png'>")
#set($No3Disabled="<img border='0' title='Agihan Tugas *Hanya Pengarah yang dibenarkan membuat agihan' src='../img/3disable2.png'>")
#set($No4Disabled="<img border='0' title='Laporan Awal Tanah' src='../img/4disable2.png'>")
#set($No5Disabled="<img border='0' title='Laporan Tanah Terperinci - Bangunan' src='../img/5disable2.png'>")
#set($No6Disabled="<img border='0' title='Laporan Tanah Terperinci - Tanah' src='../img/6disable2.png'>")
#set($No7Disabled="<img border='0' title='Maklumat Jabatan Teknikal' src='../img/7disable2.png'>")
#set($No8Disabled="<img border='0' title='Penyediaan Kertas MMK' src='../img/8disable3.png'>")
#set($No9Disabled="<img border='0' title='Notis Awam' src='../img/9disable.png'>")
#set($No10Disabled="<img border='0' title='Bukti Penyampaian Notis' src='../img/10disable2.png'>")
#set($No11Disabled="<img border='0' title='Maklumat JPPH' src='../img/11disable2.png'>")
#set($No12Disabled="<img border='0' title='Penandaan Kawasan' src='../img/12disable.png'>")
#set($No13Disabled="<img border='0' title='Perundingan' src='../img/13disable3.png'>")
#set($No14Disabled="<img border='0' title='Pampasan' src='../img/14disable2.png'>")
#set($No15Disabled="<img border='0' title='Rujukan ke Mahkamah' src='../img/15disable.png'>")

<!-- img paging (current) -->
#set($No1Current="<img border='0' title='Permohonan UPT' src='../img/1current.png'>")
#set($No2Current="<img border='0' title='Hakmilik dan Pihak Berkepentingan' src='../img/2current.png'>")
#set($No3Current="<img border='0' title='Agihan Tugas *Hanya Pengarah yang dibenarkan membuat agihan' src='../img/3current.png'>")
#set($No4Current="<img border='0' title='Laporan Awal Tanah' src='../img/4current.png'>")
#set($No5Current="<img border='0' title='Laporan Tanah Terperinci - Bangunan' src='../img/5current2.png'>")
#set($No6Current="<img border='0' title='Laporan Tanah Terperinci - Tanah' src='../img/6current2.png'>")
#set($No7Current="<img border='0' title='Maklumat Jabatan Teknikal' src='../img/7current2.png'>")
#set($No8Current="<img border='0' title='Penyediaan Kertas MMK' src='../img/8current2.png'>")
#set($No9Current="<img border='0' title='Notis Awam' src='../img/9current.png'>")
#set($No10Current="<img border='0' title='Bukti Penyampaian Notis' src='../img/10current2.png'>")
#set($No11Current="<img border='0' title='Maklumat JPPH' src='../img/11current2.png'>")
#set($No12Current="<img border='0' title='Penandaan Kawasan' src='../img/12current.png'>")
#set($No13Current="<img border='0' title='Perundingan' src='../img/13current3.png'>")
#set($No14Current="<img border='0' title='Pampasan' src='../img/14current2.png'>")
#set($No15Current="<img border='0' title='Rujukan ke Mahkamah' src='../img/15current.png'>")


<!-- Portal Role -->
#set($portal_role = "${session.getAttribute('_portal_role')}")






<!-- PAGING -->
<table width="100%" border="0">
	<tr align="right">
		<td>
        
       
		#if($paging=="1")$!No1Current#else<a href="javascript:paging1('$!id_permohonan')">$!No1Enabled</a>#end$!Arrow#if($id_status=="127" || $id_status=="16" || $id_status=="148" || $id_status=="147" || $id_status=="22" || $id_status=="132" || $id_status=="133" || $id_status=="134" || $id_status=="26" || $id_status=="31" || $id_status=="35" || $id_status=="52" || $id_status=="58" || $id_status=="43" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="59" || $id_status=="76" || $id_status=="82" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194")#if($paging=="2")$!No2Current#else<a href="javascript:paging2('$!id_permohonan')">$!No2Enabled</a>#end#else$!No2Disabled#end$!Arrow#if(($id_status=="16" || $id_status=="148" || $id_status=="147" || $id_status=="22" || $id_status=="132" || $id_status=="133" || $id_status=="134" || $id_status=="26" || $id_status=="31" || $id_status=="35" || $id_status=="52" || $id_status=="58" || $id_status=="43" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="59" || $id_status=="76" || $id_status=="82" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194")&& ($portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt" ))#if($paging=="3")$!No3Current#else<a href="javascript:paging3('$!id_permohonan')">$!No3Enabled</a>#end#else$!No3Disabled#end$!Arrow#if($id_status=="148" || $id_status=="147" || $id_status=="22" || $id_status=="132" || $id_status=="133" || $id_status=="134" || $id_status=="26" || $id_status=="31" || $id_status=="35" || $id_status=="52" || $id_status=="58" || $id_status=="43" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="59" || $id_status=="76" || $id_status=="82" || $id_status=="1610193"  || $id_status=="1610192" || $id_status=="1610194")#if($paging=="4")$!No4Current#else<a href="javascript:paging4('$!id_permohonan')">$!No4Enabled</a>#end#else$!No4Disabled#end$!Arrow#if($id_status=="147" || $id_status=="22" || $id_status=="132" || $id_status=="133" || $id_status=="134" || $id_status=="26" || $id_status=="31" || $id_status=="35" || $id_status=="52" || $id_status=="58" || $id_status=="43" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="59" || $id_status=="76" || $id_status=="82" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194")#if($paging=="5")$!No5Current#else<a href="javascript:paging5('$!id_permohonan')">$!No5Enabled</a>#end#else$!No5Disabled#end$!Arrow#if($id_status=="147" || $id_status=="22" || $id_status=="132" || $id_status=="133" || $id_status=="134" || $id_status=="26" || $id_status=="31" || $id_status=="35" || $id_status=="52" || $id_status=="58" || $id_status=="43" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="59" || $id_status=="76" || $id_status=="82" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194")#if($paging=="6")$!No6Current#else<a href="javascript:paging6('$!id_permohonan')">$!No6Enabled</a>#end#else$!No6Disabled#end$!Arrow#if($id_status=="147" || $id_status=="22" || $id_status=="132" || $id_status=="133" || $id_status=="134" || $id_status=="26" || $id_status=="31" || $id_status=="35" || $id_status=="52" || $id_status=="58" || $id_status=="43" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="59" || $id_status=="76" || $id_status=="82" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194")#if($paging=="7")$!No7Current#else<a href="javascript:paging7('$!id_permohonan')">$!No7Enabled</a>#end#else$!No7Disabled#end$!Arrow#if($id_status=="147" || $id_status=="22" || $id_status=="132" || $id_status=="133" || $id_status=="134" || $id_status=="26" || $id_status=="31" || $id_status=="35" || $id_status=="52" || $id_status=="58" || $id_status=="43" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="59" || $id_status=="76" || $id_status=="82" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194")#if($paging=="8")$!No8Current#else<a href="javascript:paging8('$!id_permohonan')">$!No8Enabled</a>#end#else$!No8Disabled#end$!Arrow#if($id_status=="26" || $id_status=="31" || $id_status=="35" || $id_status=="52" || $id_status=="58" || $id_status=="43" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="59" || $id_status=="76" || $id_status=="82" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194")#if($paging=="9")$!No9Current#else<a href="javascript:paging9('$!id_permohonan')">$!No9Enabled</a>#end#else$!No9Disabled#end$!Arrow#if($id_status=="26" || $id_status=="31" || $id_status=="35" || $id_status=="43" || $id_status=="52" || $id_status=="58" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="59" || $id_status=="76" || $id_status=="82" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194")#if($paging=="10")$!No10Current#else<a href="javascript:paging10('$!id_permohonan')">$!No10Enabled</a>#end#else$!No10Disabled#end$!Arrow#if($id_status=="31" || $id_status=="35" || $id_status=="43" || $id_status=="52" || $id_status=="58" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="59" || $id_status=="76" || $id_status=="82" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194")#if($paging=="11")$!No11Current#else<a href="javascript:paging11('$!id_permohonan')">$!No11Enabled</a>#end#else$!No11Disabled#end$!Arrow#if($id_status=="35" || $id_status=="43" || $id_status=="52" || $id_status=="58" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="59" || $id_status=="76" || $id_status=="82" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194" )#if($paging=="12")$!No12Current#else<a href="javascript:paging12('$!id_permohonan')">$!No12Enabled</a>#end#else$!No12Disabled#end
  
		<br/>
        
		#if($id_status=="43" || $id_status=="52" || $id_status=="58" || $id_status=="59" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="76" || $id_status=="82" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194")
#if($paging=="13")$!No13Current#else<a href="javascript:paging13('$!id_permohonan')">$!No13Enabled</a>#end#else$!No13Disabled#end$!Arrow#if($id_status=="43" || $id_status=="52" || $id_status=="58" || $id_status=="59" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="82" || $id_status=="76" || $id_status == "1610193" || $id_status=="1610192" || $id_status=="1610194" )#if($paging=="14")$!No14Current#else<a href="javascript:paging14('$!id_permohonan')">$!No14Enabled</a>#end#else$!No14Disabled#end$!Arrow#if($id_status=="43" || $id_status=="58" || $id_status=="38" || $id_status=="48" || $id_status=="62" || $id_status=="68" || $id_status=="72" || $id_status=="82" || $id_status=="76" || $id_status=="1610193" || $id_status=="1610192" || $id_status=="1610194")#if($paging=="15")$!No15Current#else<a href="javascript:paging15('$!id_permohonan')">$!No15Enabled</a>#end#else$!No15Disabled#end
 
		</td>
	</tr>
</table>


<input type="hidden" name="paging">
<input type="hidden" name="sub_command" id="sub_command" />
<input type="hidden" name="subminor_command" id="subminor_command" />
  
<!-- PAGING 1 -->
<script>
function paging1(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "1";
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
</script>


<!-- PAGING 2 -->
<script>
function paging2(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "2";
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik";
	document.${formName}.submit();
}
</script>


<!-- PAGING 3 -->
<script>
function paging3(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "3";
	document.${formName}.command.value = "tambahAgihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas";
	document.${formName}.submit();
}
</script>


<!-- PAGING 4 -->
<script>
function paging4(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "4";
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah";
	document.${formName}.submit();
}
</script>


<!-- PAGING 5 -->
<script>
function paging5(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "5";
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciBangunan";
	document.${formName}.submit();
}
</script>


<!-- PAGING 6 -->
<script>
function paging6(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "6";
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraTerperinciTanah";
	document.${formName}.submit();
}
</script>


<!-- PAGING 7 -->
<script>
function paging7(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "7";
	document.${formName}.command.value = "semakSenaraiJabatan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();
}
</script>


<!-- PAGING 8 -->
<script>
function paging8(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "8";
	document.${formName}.command.value = "viewMMK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraMMK";
	document.${formName}.submit();
}
</script>



<!-- PAGING 9 -->
<script>
function paging9(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "9";
	document.${formName}.command.value = "viewListNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraNotisAwam";
	document.${formName}.submit();
}
</script>


<!-- PAGING 10 -->
<script>
function paging10(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "10";
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis";
	document.${formName}.submit();
}
</script>


<!-- PAGING 11 -->
<script>
function paging11(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "11";
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH";
	document.${formName}.submit();
}
</script>


<!-- PAGING 12 -->
<script>
function paging12(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "12";
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPenandaanKawasan";
	document.${formName}.submit();
}
</script>


<!-- PAGING 13 -->
<script>
function paging13(id_permohonan)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.paging.value = "13";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan";
	document.${formName}.submit();
}
</script>


<!-- PAGING 14 -->
<script>
function paging14(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;	
	document.${formName}.paging.value = "14";
	document.${formName}.command.value = "viewlistHM";
	//document.${formName}.command.value = "newPampasanPB";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB";
	document.${formName}.submit();
}
</script>

<!-- PAGING 15 -->
<script>
function paging15(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "15";
	document.${formName}.command.value = "baruBorangM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM";
	document.${formName}.submit();
}
</script>

