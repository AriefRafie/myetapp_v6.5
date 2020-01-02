function cari(){    	
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","action=carian");
}
function seterusnya(){    	
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","action=papar&hitbutton=next");
}
function sebelumnya(){    	
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","action=papar&hitbutton=previous");
}
function tambahEnakmen(){
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","action=tambah");
	//document.Fekptg_view_pdt_PendaftaranEnakmen.submit();
}
function edit_item(id){
	document.Fekptg_view_pdt_PendaftaranEnakmen.idEnakmen.value = id;
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","action=papar&idEnakmen="+id);
	//document.Fekptg_view_pdt_PendaftaranEnakmen.submit();
}
function kosong()
{
	document.Fekptg_view_pdt_PendaftaranEnakmen.reset();
	document.Fekptg_view_pdt_PendaftaranEnakmen.txtNoEnakmenC.value = "";
	document.Fekptg_view_pdt_PendaftaranEnakmen.txtNamaEnakmenC.value = "";
	document.Fekptg_view_pdt_PendaftaranEnakmen.txtTarikhKuatkuasaC.value = "";
}

function viewEnakmenBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob?type=enakmen&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
//////////
function kembali(){
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","action=kembali");
}
function batal(){
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","action=batal");
	//document.Fekptg_view_pdt_PendaftaranEnakmen.submit();
}

function kemaskini(){
	//document.Fekptg_view_pdt_PendaftaranEnakmen.submit();
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","action=kemaskini");
}
function simpanLama(){
	//document.Fekptg_view_pdt_PendaftaranEnakmen.action.value = "papar";
	//document.Fekptg_view_pdt_PendaftaranEnakmen.hitbutton.value = "update";
	var x = create_request_string(document.Fekptg_view_pdt_PendaftaranEnakmen);
	document.Fekptg_view_pdt_PendaftaranEnakmen.enctype="multipart/form-data";
	document.Fekptg_view_pdt_PendaftaranEnakmen.encoding="multipart/form-data";
	//alert(document.Fekptg_view_pdt_PendaftaranEnakmen.enctype);
	document.Fekptg_view_pdt_PendaftaranEnakmen.action = "?_portal_module=ekptg.view.pdt.PendaftaranEnakmen&action=papar&hitbutton=update&"+x;
	//doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","action=papar&hitbutton=update");
	//document.Fekptg_view_pdt_PendaftaranEnakmen.action = "?action=papar&hitbutton=update";
	document.Fekptg_view_pdt_PendaftaranEnakmen.submit();
}
function simpanBaru(){
		if (document.Fekptg_view_pdt_PendaftaranEnakmen.txtNoEnakmen1.value == ""){
				alert('Sila masukkan " No Enakmen " terlebih dahulu.');
				document.Fekptg_view_pdt_PendaftaranEnakmen.txtNoEnakmen1.focus();
				return;
		}
		else if (document.Fekptg_view_pdt_PendaftaranEnakmen.txtNamaEnakmen1.value == ""){
				alert('Sila masukkan " Nama Enakmen " terlebih dahulu.');
				document.Fekptg_view_pdt_PendaftaranEnakmen.txtNamaEnakmen1.focus();
				return;
		}
		
	var x = create_request_string(document.Fekptg_view_pdt_PendaftaranEnakmen);
	document.Fekptg_view_pdt_PendaftaranEnakmen.enctype="multipart/form-data";
	document.Fekptg_view_pdt_PendaftaranEnakmen.encoding="multipart/form-data";
	document.Fekptg_view_pdt_PendaftaranEnakmen.action = "?_portal_module=ekptg.view.pdt.PendaftaranEnakmen&action=simpan&hitbutton=simpan&"+x;
	document.Fekptg_view_pdt_PendaftaranEnakmen.submit();
	//doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","action=simpan&hitbutton=simpan");

}
/////////
function viewEnakmenBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=enakmen&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
/////
function check_tarafT(){
	document.Fekptg_view_pdt_PendaftaranEnakmen.checked.value = "terbuka";
	//document.Fekptg_view_pdt_PendaftaranEnakmen.submit();
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","");
}
function check_tarafS(){
	document.Fekptg_view_pdt_PendaftaranEnakmen.checked.value = "sulit";
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmen("","");
	//document.Fekptg_view_pdt_PendaftaranEnakmen.submit();
}

