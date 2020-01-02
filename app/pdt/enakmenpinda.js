function getDisableFieldDiv(div,mode) {
	//alert('masuk div == '+ div);
    var elements = document.getElementById(div);
	//alert('elements == '+ elements);
	var inputElements = elements.querySelectorAll("input, select, checkbox, radio, textarea");
	//alert("inputElements == " + inputElements);
    //var inputTypes = ['text', 'select', 'textarea','input'];
	
	if(mode=='view'){
		for (var i = 0; i < inputElements.length; i++) {
			//alert("t == "+inputElements[i].type);
			var elm = inputElements[i];
			elm.class = 'disabled';
			elm.disabled = 'disabled'; 
		}
		
		
	}
}

function cari(){    	
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","action=carian");
}
function seterusnya(){    	
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","action=papar&hitbutton=next");
}
function sebelumnya(){    	
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","action=papar&hitbutton=previous");
}
	function tambahEnakmen(){
		//doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","action=tambah");
		document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.action = "?_portal_module=ekptg.view.pdt.PendaftaranEnakmenPindaan&action=tambah";
		document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.submit();
	
	}

	function edit_item(id){
		document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.idEnakmenPinda.value = id;
		//doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","action=papar&idEnakmenPinda="+id);
		document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.action = "?_portal_module=ekptg.view.pdt.PendaftaranEnakmenPindaan&action=papar";		
		document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.submit();
	
	}
	
function kosong()
{
	document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.reset();
	document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.txtNoEnakmenC.value = "";
	document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.txtNamaEnakmenC.value = "";
	document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.txtTarikhKuatkuasaC.value = "";
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
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","action=kembali");
}
function batal(){
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","action=batal");
	//document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.submit();
}

	function kemaskini(){
		//doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","action=kemaskini");
		document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.action = "?_portal_module=ekptg.view.pdt.PendaftaranEnakmenPindaan&action=kemaskini";		
		document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.submit();
	
	}

function simpanLama(){
	//document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.action.value = "papar";
	//document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.hitbutton.value = "update";
	var x = create_request_string(document.Fekptg_view_pdt_PendaftaranEnakmenPindaan);
	document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.enctype="multipart/form-data";
	document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.encoding="multipart/form-data";
	//alert(document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.enctype);
	document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.action = "?_portal_module=ekptg.view.pdt.PendaftaranEnakmenPindaan&action=doKemaskini&hitbutton=update&"+x;
	//doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","action=papar&hitbutton=update");
	//document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.action = "?action=papar&hitbutton=update";
	document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.submit();
}
	function simpanBaru(){
			if (document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.txtNoEnakmenAsal.value == ""){
					alert('Sila masukkan " No. Enakmen Asal" terlebih dahulu.');
					document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.txtNoEnakmenAsal.focus();
					return;
			}
			else if (document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.txtNoEnakmen1.value == ""){
					alert('Sila masukkan " No. Enakmen Pindaan" terlebih dahulu.');
					document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.txtNoEnakmen1.focus();
					return;
			}
			else if (document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.txtNamaEnakmen1.value == ""){
					alert('Sila masukkan " Nama Enakmen Pindaan" terlebih dahulu.');
					document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.txtNamaEnakmen1.focus();
					return;
			}
			var editorInstance = FCKeditorAPI.GetInstance('txtCatatan_');   
	      	var tajuk_Dokumen = editorInstance.GetHTML(true);
        	var textlength = tajuk_Dokumen.length;                           
	        //if(textlength==0){
	        //	alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
	        //    oEditor.EditorDocument.body.focus();
	        //   return;
	     	//}		
	     	//alert(tajuk_Dokumen);
			//alert(this.txtCatatan_.value);
						
		var x = create_request_string(document.Fekptg_view_pdt_PendaftaranEnakmenPindaan);
		//var x =0;
		if (document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.idEnakmenPinda.value == "" 
			|| document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.idEnakmenPinda.value == 0){
			document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.action = "?_portal_module=ekptg.view.pdt.PendaftaranEnakmenPindaan&action=simpan&"+x+"&txtCatatan="+tajuk_Dokumen;
			
		} else {
			document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.action = "?_portal_module=ekptg.view.pdt.PendaftaranEnakmenPindaan&action=doKemaskini&"+x+"&txtCatatan="+tajuk_Dokumen;
			
		}
		document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.enctype="multipart/form-data";
		document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.encoding="multipart/form-data";
		document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.submit();
		//doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","action=simpan&hitbutton=simpan");
	
	}
	
/////////
function viewEnakmenBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=enakmen&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function viewEnakmenPindaBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=enakmenpinda&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

/////
function check_tarafT(){
	document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.checked.value = "terbuka";
	//document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.submit();
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","");
}
function check_tarafS(){
	document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.checked.value = "sulit";
	doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","");
	//document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.submit();
}

function deleteEnakmenPindaan(idEnakmenPindaan) {
    if ( !window.confirm("Enakmen Pindaan akan dihapuskan. Adakah Anda Pasti?") ) return;	
    document.Fekptg_view_pdt_PendaftaranEnakmenPindaan.idEnakmenPinda.value = idEnakmenPindaan;
   
    doAjaxCallFekptg_view_pdt_PendaftaranEnakmenPindaan("","action=delete&idEnakmenPinda="+idEnakmenPindaan);
}