	function Xtambah(){
		//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "tambahDataBaru";
		//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
		doAjaxCallFekptg_view_pdt_PendaftaranDokumen("","action=tambahDataBaru");
	}
	
	function carian(){
		//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "";
		//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
		doAjaxCallFekptg_view_pdt_PendaftaranDokumen("","action=");
	}
	
	// Pra kemasukan maklumat baru
	function tambah(){
		
		document.Fekptg_view_pdt_MaklumatPerundangan.command.value = "tambahDataBaru";
		document.Fekptg_view_pdt_MaklumatPerundangan.action = "?_portal_module=ekptg.view.pdt.MaklumatPerundangan";	
		document.Fekptg_view_pdt_MaklumatPerundangan.submit();
				
	}
	
	function edit_item(id){
			//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "papar";
			//document.Fekptg_view_pdt_PendaftaranDokumen.mode.value = "";
		document.Fekptg_view_pdt_MaklumatPerundangan.idDokumen.value = id;
		//doAjaxCallFekptg_view_pdt_PendaftaranDokumen("","action=papar&idDokumen="+id);
		document.Fekptg_view_pdt_MaklumatPerundangan.command.value = "papar";
		document.Fekptg_view_pdt_MaklumatPerundangan.action = "?_portal_module=ekptg.view.pdt.MaklumatPerundangan";	
		document.Fekptg_view_pdt_MaklumatPerundangan.submit();
	
	}
	
	
function Xkosongkan(){
	document.Fekptg_view_pdt_PendaftaranDokumen.reset();
	document.Fekptg_view_pdt_PendaftaranDokumen.txtNoRujDokuman.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.txtTajukDokumen.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.txdTarikhDokumen.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.socSeksyen.value = "";
	
}

function viewDokumenBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=akta&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function kembali(){

	//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "SenaraiDokumen";
	//document.Fekptg_view_pdt_PendaftaranDokumen.mode.value = "";
	//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
	doAjaxCallFekptg_view_pdt_PendaftaranDokumen("","action=SenaraiDokumen");
}
function Xhapus(id){
if ( !window.confirm("Anda Pasti?") ) return;
	/*
	document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "hapus";
	document.Fekptg_view_pdt_PendaftaranDokumen.idLampiran.value = id;
	document.Fekptg_view_pdt_PendaftaranDokumen.mode.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.submit();
	*/
	//alert("idLampiran:"+id);
	doAjaxCallFekptg_view_pdt_PendaftaranDokumen("","action=hapus&idLampiran="+id);
}
function XXbatal(){
	
	if (document.Fekptg_view_pdt_PendaftaranDokumen.mode.value == "Update"){
	
		//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "papar";
		doAjaxCallFekptg_view_pdt_PendaftaranDokumen("","action=papar");
	}
	else if (document.Fekptg_view_pdt_PendaftaranDokumen.mode.value == "New" || document.Fekptg_view_pdt_PendaftaranDokumen.mode.value == "View"){
		//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "SenaraiDokumen";
		doAjaxCallFekptg_view_pdt_PendaftaranDokumen("","action=SenaraiDokumen");
	}
	
	//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
}
function doChangeSeksyen() {
 	doAjaxCallFekptg_view_pdt_PendaftaranDokumen("doChangeSeksyen");
}
function XXtambah(){
	
	/*
	document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "tambahDataLain";
	document.Fekptg_view_pdt_PendaftaranDokumen.idDokumen.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.txtNoRujDokumen.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.txtTajukDokumen.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.socJenisDokumen.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.txdTarikhDokumen.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.socSeksyen.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.socFail.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.txtCatatan.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.mode.value = "";
	document.Fekptg_view_pdt_PendaftaranDokumen.submit();	
	*/
	doAjaxCallFekptg_view_pdt_PendaftaranDokumen("","action=tambahDataLain&mode=");
}
function Xkemaskini(){

	//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "kemaskini";
	//document.Fekptg_view_pdt_PendaftaranDokumen.mode.value = "";
	//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
	doAjaxCallFekptg_view_pdt_PendaftaranDokumen("","action=kemaskini&mode=");
}

	//fungsi kemaskini (menggunakan FCK)
	function kemaskini(){	
			//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "kemaskini";
			//document.Fekptg_view_pdt_PendaftaranDokumen.mode.value = "";
			//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
		//doAjaxCall${formName}("","action=kemaskini&mode=");
		
		document.Fekptg_view_pdt_MaklumatPerundangan.mode.value = "";
		document.Fekptg_view_pdt_MaklumatPerundangan.command.value = "kemaskini";
		document.Fekptg_view_pdt_MaklumatPerundangan.action = "?_portal_module=ekptg.view.pdt.MaklumatPerundangan";	
		document.Fekptg_view_pdt_MaklumatPerundangan.submit();
		
	}

function Xsimpan() {
		//if (document.Fekptg_view_pdt_PendaftaranDokumen.txtNoRujDokumen.value == ""){
				//alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
				//document.Fekptg_view_pdt_PendaftaranDokumen.txtNoRujDokumen.focus();
				//return;
		//} 
	
		if (document.Fekptg_view_pdt_PendaftaranDokumen.txtTajukDokumen.value == ""){
				alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
				document.Fekptg_view_pdt_PendaftaranDokumen.txtTajukDokumen.focus();
				return;
		}
		/*
		if (document.Fekptg_view_pdt_PendaftaranDokumen.socJenisDokumen.value == ""){
				alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.');
				document.Fekptg_view_pdt_PendaftaranDokumen.socJenisDokumen.focus();
				return;
		} */
		
	if ( !window.confirm("Anda Pasti?") ) return;	
	if (document.Fekptg_view_pdt_PendaftaranDokumen.idDokumen.value == "" || document.Fekptg_view_pdt_PendaftaranDokumen.idDokumen.value == 0){
		//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "simpan";
		doAjaxCallFekptg_view_pdt_PendaftaranDokumen("","action=simpan&mode=");
	} else {
		//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "editData"; 
		doAjaxCallFekptg_view_pdt_PendaftaranDokumen("","action=editData&mode=");
	}
	
	//document.Fekptg_view_pdt_PendaftaranDokumen.mode.value = "";
	//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
}
function papar_Lampiran(id) {
	    var url = "../servlet/ekptg.view.pdt.DisplayBlobSemua?id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}
function refreshValue() {
    //document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "papar";
    //doAjaxCallFekptg_view_pdt_PendaftaranDokumen("doRefresh","action=papar");
	document.Fekptg_view_pdt_MaklumatPerundangan.command.value = "papar";
	document.Fekptg_view_pdt_MaklumatPerundangan.action = "?_portal_module=ekptg.view.pdt.MaklumatPerundangan&action=papar";
	document.Fekptg_view_pdt_MaklumatPerundangan.submit();	
	
}
