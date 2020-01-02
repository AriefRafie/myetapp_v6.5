function carianMesyuarat(){
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=carian");
}

function tambah(){
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=tambahMesyuarat");
}

function papar(id){
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=edit&idTblPdtMesyuarat="+id);
}

function tambahLampiran(id) {
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=openRujLampp&idTblPdtMesyuarat="+id);
}

function simpan(){
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=saveBaru");
}

function paparLampiran(id){
	var url = "../servlet/ekptg.view.pdt.FrmTmbhSnriDokDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function hapusMesyuarat(id,idRuj){
	 if ( !window.confirm("Mesyuarat akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	    doAjaxCall${formName}("","command2=deleteMesy&idTblPdtMesyuarat="+id+"&idRujMesyuarat="+idRuj);
}

function hapusLampiran(id){
	 if ( !window.confirm("Lampiran akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	    doAjaxCall${formName}("","command2=deleteLamp&idLampiran="+id);
}

function kembali(){
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","");
}


function simpanLampiran() {
		document.${formName}.method.value="post";
		var x = create_request_string(document.${formName});
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmMesyuarat&save=saveRujLamp&"+x;
		document.${formName}.submit();
}

function kembaliMesyuarat(id){
	doAjaxCallFekptg_view_pdt_FrmMesyuarat("","command2=edit&idTblPdtMesyuarat="+id);
}