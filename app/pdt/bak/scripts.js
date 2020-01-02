function tambah(){
	//document.${formName}.action.value = "tambahDataBaru";
	//document.${formName}.submit();
	doAjaxCall${formName}("","action=tambahDataBaru");

}
function carian(){
	//document.${formName}.action.value = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("","action=");
}
function kosongkan(){
	document.${formName}.reset();
	document.${formName}.txtNoRujDokuman.value = "";
	document.${formName}.txtTajukDokumen.value = "";
	document.${formName}.txdTarikhDokumen.value = "";
	document.${formName}.socSeksyen.value = "";
	
}
function edit_item(id){
	//document.${formName}.action.value = "papar";
	//document.${formName}.mode.value = "";
	//document.${formName}.idDokumen.value = id;
	//document.${formName}.submit();
	//alert("idDokumenXX="+id);
	doAjaxCall${formName}("","action=papar&idDokumen="+id);

}
function viewDokumenBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=akta&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function tambahLampiran(id,sendCommand){
	//alert("idDokumen:"+id);alert("sendCommand:"+sendCommand);
	
	var url = "../x/${securityToken}/ekptg.view.pdt.FrmTambahLampiran?idDokumen="+id+"&sendCommand="+sendCommand;
    var hWnd = window.open(url,'printuser','width=800,height=400, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}
function kembali(){

	//document.${formName}.action.value = "SenaraiDokumen";
	//document.${formName}.mode.value = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("","action=SenaraiDokumen");
}
function hapus(id){
if ( !window.confirm("Anda Pasti?") ) return;
	/*
	document.${formName}.action.value = "hapus";
	document.${formName}.idLampiran.value = id;
	document.${formName}.mode.value = "";
	document.${formName}.submit();
	*/
	//alert("idLampiran:"+id);
	doAjaxCall${formName}("","action=hapus&idLampiran="+id);
}
function batal(){
	
	if (document.${formName}.mode.value == "Update"){
	
		//document.${formName}.action.value = "papar";
		doAjaxCall${formName}("","action=papar");
	}
	else if (document.${formName}.mode.value == "New" || document.${formName}.mode.value == "View"){
		//document.${formName}.action.value = "SenaraiDokumen";
		doAjaxCall${formName}("","action=SenaraiDokumen");
	}
	
	//document.${formName}.submit();
}
function doChangeSeksyen() {
 	doAjaxCall${formName}("doChangeSeksyen");
}
function tambah(){
	
	/*
	document.${formName}.action.value = "tambahDataLain";
	document.${formName}.idDokumen.value = "";
	document.${formName}.txtNoRujDokumen.value = "";
	document.${formName}.txtTajukDokumen.value = "";
	document.${formName}.socJenisDokumen.value = "";
	document.${formName}.txdTarikhDokumen.value = "";
	document.${formName}.socSeksyen.value = "";
	document.${formName}.socFail.value = "";
	document.${formName}.txtCatatan.value = "";
	document.${formName}.mode.value = "";
	document.${formName}.submit();	
	*/
	doAjaxCall${formName}("","action=tambahDataLain&mode=");
}
function kemaskini(){

	//document.${formName}.action.value = "kemaskini";
	//document.${formName}.mode.value = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("","action=kemaskini&mode=");
}
function simpan() {
		if (document.${formName}.txtNoRujDokumen.value == ""){
				alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
				document.${formName}.txtNoRujDokumen.focus();
				return;
		} 
		if (document.${formName}.txtTajukDokumen.value == ""){
				alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
				document.${formName}.txtTajukDokumen.focus();
				return;
		}
		if (document.${formName}.socJenisDokumen.value == ""){
				alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.');
				document.${formName}.socJenisDokumen.focus();
				return;
		} 
		
	if ( !window.confirm("Anda Pasti?") ) return;	
	if (document.${formName}.idDokumen.value == "" || document.${formName}.idDokumen.value == 0){
		//document.${formName}.action.value = "simpan";
		doAjaxCall${formName}("","action=simpan&mode=");
	} else {
		//document.${formName}.action.value = "editData"; 
		doAjaxCall${formName}("","action=editData&mode=");
	}
	
	//document.${formName}.mode.value = "";
	//document.${formName}.submit();
}
function papar_Lampiran(id) {
	    var url = "../servlet/ekptg.view.pdt.DisplayBlob?id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}
function refreshValue() {
    //document.${formName}.action.value = "papar";
    //doAjaxCall${formName}("doRefresh","action=papar");
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranDokumen&action=papar";
	document.${formName}.submit();	
}