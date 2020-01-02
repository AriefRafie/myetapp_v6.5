<!-- Arrow -->
#set($Arrow="<img border='0' src='../img/arrowgaris.png'>")

<!-- img paging (enabled) -->
#set($No1Enabled="<img border='0' title='Permohonan UPT' src='../img/1enable.png'>")
#set($No2Enabled="<img border='0' title='Agihan Tugas *Hanya Pengarah yang dibenarkan membuat agihan' src='../img/2enable.png'>")
#set($No3Enabled="<img border='0' title='Penyediaan Pra Laporan Tanah' src='../img/3enable.png'>")
#set($No4Enabled="<img border='0' title='Penyediaan Kertas MMK' src='../img/4enable.png'>")
#set($No5Enabled="<img border='0' title='Warta dan Notis Awam' src='../img/5enable.png'>")

<!-- img paging (disabled) -->
#set($No1Disabled="<img border='0' title='Permohonan UPT' src='../img/1disable.png'>")
#set($No2Disabled="<img border='0' title='Agihan Tugas *Hanya Pengarah yang dibenarkan membuat agihan' src='../img/2disable.png'>")
#set($No3Disabled="<img border='0' title='Penyediaan Pra Laporan Tanah' src='../img/3disable2.png'>")
#set($No4Disabled="<img border='0' title='Penyediaan Kertas MMK' src='../img/4disable2.png'>")
#set($No5Disabled="<img border='0' title='Warta dan Notis Awam' src='../img/5disable.png'>")

<!-- img paging (current) -->
#set($No1Current="<img border='0' title='Permohonan UPT' src='../img/1current.png'>")
#set($No2Current="<img border='0' title='Agihan Tugas *Hanya Pengarah yang dibenarkan membuat agihan' src='../img/2current.png'>")
#set($No3Current="<img border='0' title='Penyediaan Pra Laporan Tanah' src='../img/3current.png'>")
#set($No4Current="<img border='0' title='Penyediaan Kertas MMK' src='../img/4current.png'>")
#set($No5Current="<img border='0' title='Warta dan Notis Awam' src='../img/5current.png'>")

<!-- Portal Role -->
#set($portal_role = "${session.getAttribute('_portal_role')}")

<!-- PAGING -->
<table width="100%" border="0">
	<tr align="right">
		<td>
        
        
		#if($paging=="1")$!No1Current#else<a href="javascript:edit_item('$!id_permohonan')">$!No1Enabled</a>#end$!Arrow#if(($id_status=="127" || $id_status=="148" || $id_status=="147" || $id_status=="26" || $id_status=="31" || $id_status=="52") && ($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt" ))#if($paging=="2")$!No2Current#else#if($id_status=="127")<a href="javascript:tambahAgihan('$!id_permohonan')">$!No2Enabled</a>#else<a href="javascript:viewAgihan('$!id_permohonan')">$!No2Enabled</a>#end#end#else$!No2Disabled#end$!Arrow#if($id_status=="148" || $id_status=="147" || $id_status=="26" || $id_status=="31" || $id_status=="52")#if($paging=="3")$!No3Current#else#if($id_status=="148")<a href="javascript:tambahLaporan('$!id_permohonan')">$!No3Enabled</a>#else<a href="javascript:semakLaporan('$!id_permohonan')">$!No3Enabled</a>#end#end#else$!No3Disabled#end$!Arrow#if($id_status=="148" || $id_status=="147" || $id_status=="26" || $id_status=="31" || $id_status=="52")#if($paging=="4")$!No4Current#else<a href="javascript:viewSenaraiMMK('$!id_permohonan')">$!No4Enabled</a>#end#else$!No4Disabled#end$!Arrow#if($id_status=="31" || $id_status=="52")#if($paging=="5")$!No5Current#else<a href="javascript:viewListNotis('$!id_permohonan')">$!No5Enabled</a>#end#else$!No5Disabled#end
		</td>
	</tr>
</table>

<input type="hidden" name="paging">


<!-- PAGING 1 -->
<script>
function edit_item(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "1";
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
</script>


<!-- PAGING 2 -->
<script>
function tambahAgihan(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "2";
	document.${formName}.command.value = "tambahAgihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4";
	document.${formName}.submit();
}
function viewAgihan(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "2";
	document.${formName}.command.value = "viewAgihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4";
	document.${formName}.submit();
}
</script>


<!-- PAGING 3 -->
<script>
function tambahLaporan(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "3";
	document.${formName}.command.value = "tambahLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4LaporanAwalTanahSenarai";
	document.${formName}.submit();
}
function semakLaporan(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "3";
	document.${formName}.command.value = "semakLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4LaporanAwalTanahSenarai";
	document.${formName}.submit();
}
</script>


<!-- PAGING 4 -->
<script>
function viewSenaraiMMK(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "4";
	document.${formName}.command.value = "viewSenaraiMMK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.submit();
}
</script>


<!-- PAGING 5 -->
<script>
function viewListNotis(id_permohonan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.paging.value = "5";
	document.${formName}.command.value = "viewListNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam";
	document.${formName}.submit();
}
</script>
