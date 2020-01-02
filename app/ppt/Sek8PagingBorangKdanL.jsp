<!-- Arrow -->
#set($Arrow="<img border='0' src='../img/arrowgaris.png'>")

<!-- img paging (enabled) -->
#set($No1Enabled="<img border='0' title='Maklumat Borang K' src='../img/1enable.png'>")
#set($No2Enabled="<img border='0' title='Penjanaan Borang L' src='../img/2enable2.png'>")

<!-- img paging (disabled) -->
#set($No1Disabled="<img border='0' title='Maklumat Borang K' src='../img/1disable.png'>")
#set($No2Disabled="<img border='0' title='Penjanaan Borang L' src='../img/2disable2.png'>")

<!-- img paging (current) -->
#set($No1Current="<img border='0' title='Maklumat Borang K' src='../img/1current.png'>")
#set($No2Current="<img border='0' title='Penjanaan Borang L' src='../img/2current2.png'>")

<!-- PAGING -->
<table width="100%" border="0">
	<tr align="right">
		<td>
			#if($flagPagingBorangK=="1")$!No1Current#else<a href="javascript:viewListHM('$!id_permohonan')">$!No1Enabled</a>#end$!Arrow#if($id_status=="76" || $id_status=="82")#if($flagPagingBorangK=="2")$!No2Current#else<a href="javascript:penjanaanBorangL('$!id_permohonan')">$!No2Enabled</a>#end#else$!No2Disabled#end
		</td>
	</tr>
</table>

<input type="hidden" name="flagPagingBorangK">

<!-- PAGING 1 -->
<script>
function viewListHM(id_permohonan) {
	document.${formName}.flagPagingBorangK.value = "1";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
</script>

<!-- PAGING 2 -->
<script>
function penjanaanBorangL(id_permohonan) {
	document.${formName}.flagPagingBorangK.value = "2";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "penjanaanBorangL";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
</script>