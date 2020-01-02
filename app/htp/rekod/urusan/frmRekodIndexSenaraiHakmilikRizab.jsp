<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<table width="98%" border="0">
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			#parse("app/htp/rekod/urusan/frmRekodIndexCarianSenaraiHakmilikRizab.jsp")
			<br>
			#parse("app/htp/rekod/utiliti/frmRekodIndexSenaraiHakmilikRizabBawah.jsp")

		</td>
	</tr>
</table>
				        	
<input name="flagAdvSearch" type="hidden" value="$!flagAdvSearch" />

<script>

	function cari(){   
		field_ = document.${formName}.socJenisTanahtemp;
		for (i = 0; i < field_.length; i++){
			if(field_[i].checked==true){
				document.${formName}.socJenisTanah.value = field_[i].value;
			}
		}
		//alert(document.${formName}.socJenisTanah.value);
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab");
		
	}


	
	function hakmilik_detail(id,status){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id+"&statusSah="+status;
		document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBMaklumatPermohonanView";
		document.${formName}.submit();

	}
	
	function rizab_detail(id,status){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
		document.${formName}.submit();
	}
	
	function kosongCarian(idJenisTanah){
		if (idJenisTanah == '1'){
			document.${formName}.txtNoHakmilikC.value = "";
		} else if (idJenisTanah == '2'){
			document.${formName}.txtNoWartaC.value = "";
		} 
		document.${formName}.socJenisTanah.value = "0";
		document.${formName}.socStatus.value = "0";
		document.${formName}.socNegeri.value = "";
		document.${formName}.socDaerah.value = "";
		document.${formName}.socMukim.value = "";	
		document.${formName}.txtNoLotC.value = "";
		document.${formName}.txtNoFailC.value = "";
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";
		document.${formName}.submit();
	}
	
	function doChangeState() {
	  doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeState");
	}
	
	function doChangeDaerah() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeDaerah");
	}
	
	function doChangeMukim() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeMukim");
	}
	
	function doChangeKementerian() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeKementerian");
	}
	
	function deleteHakmilikBaru(id,id2){
		if ( !window.confirm("Adakah Anda Pasti?")) return;
			document.${formName}.command.value = "";
		
		doAjaxCall${formName}("","firstAction=deleteHakmilikBaru&idHakmilik="+id+"&idHakmilikBaru="+id2);
		
	}

	function doChangeTarafRizab(x) { 
		alert("XXXX");
	}

	function more(){
		document.${formName}.flagAdvSearch.value = "open";
		//document.${formName}.submit();
		//doAjaxCall${formName}("","flagAdvSearch=open");
		doAjaxCall${formName}("");
		
	}
	
	function less(){
		document.${formName}.flagAdvSearch.value = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}

</script>
