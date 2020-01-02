<!-- Arrow -->
#set($Arrow="<img border='0' src='../img/arrowgaris.png'>")

<!-- img paging (enabled) -->
#set($No1Enabled="<img border='0' title='Penyediaan Pampasan' src='../img/1enable.png'>")
#set($No2Enabled="<img border='0' title='Bentuk Bayaran Pampasan' src='../img/2enable.png'>")
#set($No3Enabled="<img border='0' title='Bayaran Pampasan' src='../img/3enable2.png'>")

<!-- img paging (disabled) -->
#set($No1Disabled="<img border='0' title='Penyediaan Pampasan' src='../img/1disable.png'>")
#set($No2Disabled="<img border='0' title='Bentuk Bayaran Pampasan' src='../img/2disable.png'>")
#set($No3Disabled="<img border='0' title='Bayaran Pampasan' src='../img/3disable.png'>")

<!-- img paging (current) -->
#set($No1Current="<img border='0' title='Penyediaan Pampasan' src='../img/1current.png'>")
#set($No2Current="<img border='0' title='Bentuk Bayaran Pampasan' src='../img/2current.png'>")
#set($No3Current="<img border='0' title='Bayaran Pampasan' src='../img/3current2.png'>")


#set($flag_status_borang_h = 0)

#foreach($dMT in $dataMaklumatTanah)
#set($flag_status_borang_h = $dMT.flag_status_borang_h)
#end

<!-- PAGING -->
<table width="100%" border="0">
	<tr align="right">
		<td>
			<!-- #if($pagingPampasan=="1")$!No1Current#else<a href="javascript:viewJumlahAward('$!id_hakmilik','$!id_siasatan')">$!No1Enabled</a>#end$!Arrow#if($id_status=="68" || $id_status=="72" || $id_status=="76" || $id_status=="82" || ($id_status=="74") || ($id_status=="187") || ($id_status=="204") || ($id_status=="181") || ($id_status=="182") || ($id_status=="183") || ($id_status=="184") || ($id_status=="186") || ($id_status=="199") || ($id_status=="200") || ($id_status=="201") || ($id_status=="203") || ($id_status=="205") || ($id_status=="220") || ($id_status=="1610235"))#if($pagingPampasan=="2")$!No2Current#else<a href="javascript:bentukBayaran('$!id_hakmilik','$!id_siasatan')">$!No2Enabled</a>#end#else$!No2Disabled#end$!Arrow#if($id_status=="68" || $id_status=="72" || $id_status=="76" || $id_status=="82" || ($id_status=="74") || ($id_status=="187") || ($id_status=="204") || ($id_status=="181") || ($id_status=="182") || ($id_status=="183") || ($id_status=="184") || ($id_status=="186") || ($id_status=="199") || ($id_status=="200") || ($id_status=="201") || ($id_status=="203") || ($id_status=="205") || ($id_status=="220") || ($id_status=="1610235"))#if($pagingPampasan=="3")$!No3Current#else<a href="javascript:maklumatSuratAgensi('$!id_hakmilik','$!id_siasatan')">$!No3Enabled</a>#end#else$!No3Disabled#end -->
			#if($pagingPampasan=="1")$!No1Current#else<a href="javascript:viewJumlahAward('$!id_hakmilik','$!id_siasatan')">$!No1Enabled</a>#end$!Arrow#if($flag_status_borang_h > 0)#if($pagingPampasan=="2")$!No2Current#else<a href="javascript:bentukBayaran('$!id_hakmilik','$!id_siasatan')">$!No2Enabled</a>#end#else$!No2Disabled#end$!Arrow#if($flag_status_borang_h > 0)#if($pagingPampasan=="3")$!No3Current#else<a href="javascript:maklumatSuratAgensi('$!id_hakmilik','$!id_siasatan')">$!No3Enabled</a>#end#else$!No3Disabled#end
		</td>
	</tr>
</table>

<input type="hidden" name="pagingPampasan">

<!-- PAGE 1 -->
<script>
function viewJumlahAward(idHakmilik,idSiasatan) {	
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.pagingPampasan.value = "1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.id_siasatan.value = idSiasatan;
	document.${formName}.command.value = "viewJumlahAward";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
</script>

<!-- PAGE 2 -->
<script>
function bentukBayaran(idHakmilik,idSiasatan) {
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.pagingPampasan.value = "2";
	document.${formName}.id_siasatan.value = idSiasatan;
	document.${formName}.command.value = "bentukBayaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
</script>

<!-- PAGE 3 -->
<script>
function maklumatSuratAgensi(idHakmilik,idSiasatan) {
	document.${formName}.tabId.value = "0";
	document.${formName}.ScreenLocation.value = "";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.id_siasatan.value = idSiasatan;
	document.${formName}.pagingPampasan.value = "3";
	document.${formName}.command.value = "maklumatSuratAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
</script>