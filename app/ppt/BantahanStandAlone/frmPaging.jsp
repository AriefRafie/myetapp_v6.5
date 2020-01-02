<!-- Arrow -->
#set($Arrow="<img border='0' src='../img/arrowgaris.png'>")

<!-- img paging (enabled) -->
#set($No1Enabled="<img border='0' title='Maklumat Bantahan' src='../img/1enable.png'>")
#set($No2Enabled="<img border='0' title='Deposit' src='../img/2enable.png'>")
#set($No3Enabled="<img border='0' title='Borang O' src='../img/3enable.png'>")
#set($No4Enabled="<img border='0' title='Maklumat Perintah' src='../img/4enable.png'>")
#set($No5Enabled="<img border='0' title='Pemulangan Deposit' src='../img/5enable.png'>")

<!-- img paging (disabled) -->
#set($No1Disabled="<img border='0' title='Maklumat Bantahan' src='../img/1disable.png'>")
#set($No2Disabled="<img border='0' title='Deposit' src='../img/2disable.png'>")
#set($No3Disabled="<img border='0' title='Borang O' src='../img/3disable2.png'>")
#set($No4Disabled="<img border='0' title='Maklumat Perintah' src='../img/4disable2.png'>")
#set($No5Disabled="<img border='0' title='Pemulangan Deposit' src='../img/5disable2.png'>")

<!-- img paging (current) -->
#set($No1Current="<img border='0' title='Maklumat Bantahan' src='../img/1current.png'>")
#set($No2Current="<img border='0' title='Deposit' src='../img/2current.png'>")
#set($No3Current="<img border='0' title='Borang O' src='../img/3current.png'>")
#set($No4Current="<img border='0' title='Maklumat Perintah' src='../img/4current.png'>")
#set($No5Current="<img border='0' title='Pemulangan Deposit' src='../img/5current.png'>")

<!-- Portal Role -->
#set($portal_role = "${session.getAttribute('myrole')}")

#foreach($data in $dataBantahan)
<!-- Syarat Paging -->
#set($flag_open_paging2 = $data.NO_BANTAHAN)
#set($flag_open_paging3 = $data.TARIKH_TERIMA_RESIT)
#set($flag_open_paging4 = $data.ID_MAINBORANGO)
#set($flag_open_paging5 = $data.ID_MAINBORANGO)
<!--#set($flag_open_paging6 = $data.ID_MAINBORANGO)-->
#end

<!-- PAGING -->
<table width="100%" border="0">
	<tr align="right">
		<td>
		#if($paging=="1")
			$!No1Current
		#else
			<a href="javascript:paging1('$!id_bantahan')">$!No1Enabled</a>
		#end
		
		$!Arrow
		
		#if($flag_open_paging2 != "")
			#if($paging=="2")
				$!No2Current
			#else
				<a href="javascript:paging2('$!id_bantahan')">$!No2Enabled</a>
			#end
		
		#else
			
			$!No2Disabled
		
		#end
		
		$!Arrow
		
		#if($flag_open_paging3 != "")
			#if($paging=="3")
				$!No3Current
			#else
				<a href="javascript:paging3('$!id_bantahan')">$!No3Enabled</a>
			#end
			
		#else
		
			$!No3Disabled
			
		#end
		
		$!Arrow
		
		
		#if($flag_open_paging4 != "")
			#if($paging=="4")
				$!No4Current
			#else
				<a href="javascript:paging4('$!id_bantahan','$!id_borango')">$!No4Enabled</a>
			#end
		#else
		
			$!No4Disabled
			
		#end
        
        $!Arrow
        
        #if($flag_open_paging5 != "")
			#if($paging=="5")
				$!No5Current
			#else
				<a href="javascript:paging5('$!id_bantahan','$!id_borango')">$!No5Enabled</a>
			#end
		#else
		
			$!No5Disabled
			
		#end
		
		
		</td>
	</tr>
</table>

<input type="hidden" name="id_bantahan" id="id_bantahan" value="$!id_bantahan">
<input type="hidden" name="id_borango" id="id_borango" value="$!id_borango">
<input type="hidden" name="paging">
<input type="hidden" name="sub_command" id="sub_command" />
<input type="hidden" name="subminor_command" id="subminor_command" />
  
<!-- PAGING 1 -->
<script>
function paging1(id_bantahan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.paging.value = "1";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "viewBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
</script>


<!-- PAGING 2 -->
<script>
function paging2(id_bantahan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.paging.value = "2";
	document.${formName}.command.value = "viewDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
</script>


<!-- PAGING 3 -->
<script>
function paging3(id_bantahan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.paging.value = "3";
	document.${formName}.command.value = "viewBorangO";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
</script>

<!-- PAGING 4 -->
<script>
function paging4(id_bantahan,id_borango) {
    

	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.id_borango.value = id_borango;
	document.${formName}.paging.value = "4";
	document.${formName}.command.value = "susulanBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
</script>

<!-- PAGING 5 -->
<script>
function paging5(id_bantahan,id_borango) {
    
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.id_borango.value = id_borango;
	document.${formName}.paging.value = "5";
	document.${formName}.command.value = "pemulanganDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
</script>
<!-- PAGING 6 -->
<script>
function paging6(id_bantahan,id_borango) {

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.id_borango.value = id_borango;
	document.${formName}.paging.value = "6";
	document.${formName}.command.value = "pemulanganDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
</script>
