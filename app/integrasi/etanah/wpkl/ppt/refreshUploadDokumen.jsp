<script>
var idPermohonanIntegrasi = $!idPermohonanIntegrasiReload;
	parent.document.getElementById('dokumenMuatnaik').style.display = "none";
	//doDivAjaxCall$formname('divSenaraiDokumen','refreshDokumenMuatNaik','idPermohonanIntegrasiReload=$!idPermohonanIntegrasiReload');
	parent.document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL&command=refreshDokumenMuatNaik&idPermohonanIntegrasiReload="+$!idPermohonanIntegrasiReload;
	parent.document.${formName}.method="POST";
	parent.document.${formName}.submit();
</script>