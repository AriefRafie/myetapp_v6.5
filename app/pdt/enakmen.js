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

	function doKemaskiniEnakmen() {
	    //doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=doKemaskiniEnakmen");
	    document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=doKemaskiniEnakmen";
	    document.Fekptg_view_pdt_FrmViewEnakmen.submit();
	    
	}

function doAdd() {
    //doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=update");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=update";    
    document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}
function searchEnakmen() {
    document.Fekptg_view_pdt_FrmViewEnakmen.action.value = 'cari';
    document.Fekptg_view_pdt_FrmViewEnakmen.submit();
	
}
function viewEnakmen(Enakmen_IDEnakmen) {
    document.Fekptg_view_pdt_FrmViewEnakmen.Enakmen_IDEnakmen.value = Enakmen_IDEnakmen;
    //document.Fekptg_view_pdt_FrmViewEnakmen.action.value = "view";
    
    document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=view";
    
    document.Fekptg_view_pdt_FrmViewEnakmen.submit();
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=view");
}
function resetEnakmen() {
    document.Fekptg_view_pdt_FrmViewEnakmen.reset();
    document.Fekptg_view_pdt_FrmViewEnakmen.txtNoFail.value = "";
    document.Fekptg_view_pdt_FrmViewEnakmen.txtNamaFail.value = "";
    document.Fekptg_view_pdt_FrmViewEnakmen.txdTarikhKuatkuasa.value = "";
}
//////////////////////////
function tambahEnakmen(){
	//alert("tambah");
	//document.Fekptg_view_pdt_FrmViewEnakmen.action.value="tambah";
	//document.Fekptg_view_pdt_FrmViewEnakmen.submit();
	doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=tambah");
}

function viewEnakmenBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=enakmen&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

	function doChangeTab(TabID) {
	    	//document.Fekptg_view_pdt_FrmViewEnakmen.action.value = '$action';
	    document.Fekptg_view_pdt_FrmViewEnakmen.selectedTab.value = TabID;
    	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=view";
	    document.Fekptg_view_pdt_FrmViewEnakmen.submit();
	    	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=$action");
	    //doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=view");
	}

function doChangeSOC(submitType) {
    //document.Fekptg_view_pdt_FrmViewEnakmen.action.value = '$action';
    //doAjaxCallFekptg_view_pdt_FrmViewEnakmen(submitType);
    //var x = $action;
    //alert(submitType);
    doAjaxCallFekptg_view_pdt_FrmViewEnakmen(submitType,"action=view");
    //doAjaxCallFekptg_view_pdt_FrmViewEnakmen(submitType,"action=doChanges");
}

	function doAdd() {
	    //doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=update");
		document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=update";
	    document.Fekptg_view_pdt_FrmViewEnakmen.submit();
	    
	    
	}

	function doUpdate() {
		if (document.Fekptg_view_pdt_FrmViewEnakmen.txtNoEnakmen.value == "") {
			alert("Sila masukkan no enakmen");
			document.Fekptg_view_pdt_FrmViewEnakmen.txtNoEnakmen.focus();
			return;
		}
		else if (document.Fekptg_view_pdt_FrmViewEnakmen.txtNamaEnakmen.value == "") {
			alert("Sila masukkan nama enakmen");
			document.Fekptg_view_pdt_FrmViewEnakmen.txtNamaEnakmen.focus();
			return;
		}
			var editorInstance = FCKeditorAPI.GetInstance('txtCatatan_');   
	      	var tajuk_Dokumen = editorInstance.GetHTML(true);
        	var textlength = tajuk_Dokumen.length;  
		var x = create_request_string(document.Fekptg_view_pdt_FrmViewEnakmen);
		//alert(x);
		//return;
		document.Fekptg_view_pdt_FrmViewEnakmen.method="post";
		document.Fekptg_view_pdt_FrmViewEnakmen.enctype="multipart/form-data";
		document.Fekptg_view_pdt_FrmViewEnakmen.encoding="multipart/form-data";
		document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&mode=update&action=save&"+x+"&txtCatatan="+tajuk_Dokumen;
		document.Fekptg_view_pdt_FrmViewEnakmen.submit();
	}

function doSave() {
    //document.Fekptg_view_pdt_FrmViewEnakmen.action.value = 'save';
    //document.Fekptg_view_pdt_FrmViewEnakmen.submit();
    doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save");
}

	function doSaveEnakmen() {
		if (document.Fekptg_view_pdt_FrmViewEnakmen.txtNoEnakmen.value == "") {
			alert("Sila masukkan no enakmen");
			document.Fekptg_view_pdt_FrmViewEnakmen.txtNoEnakmen.focus();
			return;
		}
		else if (document.Fekptg_view_pdt_FrmViewEnakmen.txtNamaEnakmen.value == "") {
			alert("Sila masukkan nama enakmen");
			document.Fekptg_view_pdt_FrmViewEnakmen.txtNamaEnakmen.focus();
			return;
		}
			var editorInstance = FCKeditorAPI.GetInstance('txtCatatan_');   
	      	var tajuk_Dokumen = editorInstance.GetHTML(true);
        	var textlength = tajuk_Dokumen.length;  
		
	    var x = create_request_string(document.Fekptg_view_pdt_FrmViewEnakmen);
	    //alert(x);
	    //return;
	    document.Fekptg_view_pdt_FrmViewEnakmen.method="post";
	    document.Fekptg_view_pdt_FrmViewEnakmen.enctype="multipart/form-data";
	    document.Fekptg_view_pdt_FrmViewEnakmen.encoding="multipart/form-data";
	    document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&mode=insert&action=save&"+x+"&txtCatatan="+tajuk_Dokumen;
	    document.Fekptg_view_pdt_FrmViewEnakmen.submit();
	}

function doBack() {
    //document.Fekptg_view_pdt_FrmViewEnakmen.action.value = 'view';
    //document.Fekptg_view_pdt_FrmViewEnakmen.submit();
    doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=view");
}
function doDelete() {
    //if ( !window.confirm("Enakmen [444] akan dihapuskan. Adakah Anda Pasti?") ) return;	
	if ( !window.confirm("Enakmen akan dihapuskan. Adakah Anda Pasti?") ) return;	
    document.Fekptg_view_pdt_FrmViewEnakmen.action.value = 'delete';
    document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}
function doPrint() {
    document.Fekptg_view_pdt_FrmViewEnakmen.action.value = 'print';
    document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}
function addPegawaiSulit() {
}

function doBack() {
	doAjaxCallFekptg_view_pdt_FrmViewEnakmen("");
}

///PENGGAL
function doSavePenggal() {
	if (document.Fekptg_view_pdt_FrmViewEnakmen.txtNoPenggal.value == "") {
		alert("Sila masukkan no penggal");
		document.Fekptg_view_pdt_FrmViewEnakmen.txtNoPenggal.focus();
		
		return;
	}
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=add");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=add";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function viewPenggal(ID_Penggal) {
	  document.Fekptg_view_pdt_FrmViewEnakmen.Penggal_IDPenggal.value = ID_Penggal;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=viewDetail&Penggal_IDPenggal="+ID_Penggal);
}

function doKemaskiniPenggal() {
	//alert('yaww');
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=update");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=update";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function doHapusPenggal(ID_Penggal) {
	  if ( !window.confirm("Penggal dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewEnakmen.Penggal_IDPenggal.value = ID_Penggal;
	  //doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=delete&Penggal_IDPenggal="+ID_Penggal);
	  document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=delete";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

//BAHAGIAN
function doSaveBahagian() {
	
	/*
	if (document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Penggal.focus();
		return;
	}
	*/
	if (document.Fekptg_view_pdt_FrmViewEnakmen.txtNoBahagian.value == "") {
		alert("Sila masukkan no bahagian");
		document.Fekptg_view_pdt_FrmViewEnakmen.txtNoBahagian.focus();
		return;
	}
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=add");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=add";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function doKemaskiniBahagian() {
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=update");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=update";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function viewBahagian(ID_Bahagian) {
	  document.Fekptg_view_pdt_FrmViewEnakmen.Bahagian_IDBahagian.value = ID_Bahagian;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=viewDetail&Bahagian_IDBahagian="+ID_Bahagian);
}

function doHapusBahagian(ID_Bahagian) {
	  if ( !window.confirm("Bahagian dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewEnakmen.Bahagian_IDBahagian.value = ID_Bahagian;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=delete&Bahagian_IDBahagian="+ID_Bahagian);
}

//BAB
function doSaveBab() {
	/*
	if (document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Penggal.focus();
		return;
	}
	*/
	if (document.Fekptg_view_pdt_FrmViewEnakmen.txtNoBab.value == "") {
		alert("Sila masukkan no bab");
		document.Fekptg_view_pdt_FrmViewEnakmen.txtNoBab.focus();
		return;
	}
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=add");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=add";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function doKemaskiniBab() {
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=update");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=update";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function viewBab(ID_Bab) {
	//alert(ID_Bab);
	  document.Fekptg_view_pdt_FrmViewEnakmen.Bab_IDBab.value = ID_Bab;
	 doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=viewDetail&Bab_IDBab="+ID_Bab);
	 // document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=viewDetail&mode=update";
}

function doHapusBab(ID_Bab) {
	  if ( !window.confirm("Bab dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewEnakmen.Bab_IDBab.value = ID_Bab;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=delete&Bab_IDBab="+ID_Bab);
}

//SEKSYEN
function doSaveSeksyen() {
	/*
	if (document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Penggal.focus();
		return;
	}
	else 
	*/
	if (document.Fekptg_view_pdt_FrmViewEnakmen.txtNoSeksyen.value == "") {
		alert("Sila masukkan no seksyen");
		document.Fekptg_view_pdt_FrmViewEnakmen.txtNoSeksyen.focus();
		return;
	}
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=add");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=add";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function doKemaskiniSeksyen() {
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=update");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=update";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function viewSeksyen(ID_Seksyen) {
	  document.Fekptg_view_pdt_FrmViewEnakmen.Seksyen_IDSeksyen.value = ID_Seksyen;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=viewDetail&Seksyen_IDSeksyen="+ID_Seksyen);
}

function doHapusSeksyen(ID_Seksyen) {
	  if ( !window.confirm("Seksyen dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewEnakmen.Seksyen_IDSeksyen.value = ID_Seksyen;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=delete&Seksyen_IDSeksyen="+ID_Seksyen);
}
/// SUB SEKSYEN
function doSaveSubSeksyen() {
	/*
	if (document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Penggal.focus();
		return;
	}
	else 
	*/
	if (document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Seksyen.value == "") {
			alert("Sila pilih Seksyen");
			document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Seksyen.focus();
			return;
		}
	else if (document.Fekptg_view_pdt_FrmViewEnakmen.txtNoSubSeksyen.value == "") {
		alert("Sila masukkan no seksyen");
		document.Fekptg_view_pdt_FrmViewEnakmen.txtNoSubSeksyen.focus();
		return;
	}
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=add");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=add";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function doKemaskiniSubSeksyen(ID_SubSeksyen) {
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=update&SubSeksyen_IDSubSeksyen="+ID_SubSeksyen);
	document.Fekptg_view_pdt_FrmViewEnakmen.SubSeksyen_IDSubSeksyen = ID_SubSeksyen;
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=update";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function viewSubSeksyen(ID_SubSeksyen) {
	  document.Fekptg_view_pdt_FrmViewEnakmen.SubSeksyen_IDSubSeksyen.value = ID_SubSeksyen;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=viewDetail&SubSeksyen_IDSubSeksyen="+ID_SubSeksyen);
}

function doHapusSubSeksyen(ID_SubSeksyen) {
	  if ( !window.confirm("Sub Seksyen dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewEnakmen.SubSeksyen_IDSubSeksyen.value = ID_SubSeksyen;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=delete&SubSeksyen_IDSubSeksyen="+ID_SubSeksyen);
}
/// PARA
function doSavePara() {
	if (document.Fekptg_view_pdt_FrmViewEnakmen.SOC_SubSeksyen.value == "") {
		alert("Sila Pilih SubSeksyen");
		document.Fekptg_view_pdt_FrmViewEnakmen.SOC_SubSeksyen.focus();
		return;
	}
	else if (document.Fekptg_view_pdt_FrmViewEnakmen.txtPara.value == "") {
		alert("Sila Masukkan Para");
		document.Fekptg_view_pdt_FrmViewEnakmen.txtPara.focus();
		return;
	}
	doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=add");
}

function doKemaskiniPara() {
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=update");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=update";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function viewPara(ID_Para) {
	  document.Fekptg_view_pdt_FrmViewEnakmen.Para_IDPara.value = ID_Para;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=viewDetail&Para_IDPara="+ID_Para);
}

function doHapusPara(ID_Para) {
	  if ( !window.confirm("Para dan kesemua maklumat yang berkaitan akan dihapuskan.\Adakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewEnakmen.Para_IDPara.value = ID_Para;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=delete&Para_IDPara="+ID_Para);
}

/// SUB PARA
function doSaveSubPara() {
	if (document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Para.value == "") {
		alert("Sila pilih Para");
		document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Para.focus();
		return;
	}
	else if (document.Fekptg_view_pdt_FrmViewEnakmen.txtSubPara.value == "") {
		alert("Sila masukkan maklumat sub para");
		document.Fekptg_view_pdt_FrmViewEnakmen.txtSubPara.focus();
		return;
	}
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=add");
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=add";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function doKemaskiniSubPara(ID_subPara) {
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=update");
	 document.Fekptg_view_pdt_FrmViewEnakmen.SubPara_IDSubPara.value = ID_subPara;
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=save&mode=update";
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}

function viewSubPara(ID_subPara) {
	  document.Fekptg_view_pdt_FrmViewEnakmen.SubPara_IDSubPara.value = ID_subPara;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=viewDetail&SubPara_IDSubPara="+ID_subPara);
}

function doHapusSubPara(ID_subPara) {
	  if ( !window.confirm("Sub para dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewEnakmen.SubPara_IDSubPara.value = ID_subPara;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=delete&SubPara_IDSubPara="+ID_subPara);
}


function viewEnakmenBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=enakmen&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function doSaveJadualPara() {
	/*if (document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Para.value == "") {
		alert("Sila pilih Para");
		document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Para.focus();
		return;
	}
	else if (document.Fekptg_view_pdt_FrmViewEnakmen.txtSubPara.value == "") {
		alert("Sila masukkan maklumat sub para");
		document.Fekptg_view_pdt_FrmViewEnakmen.txtSubPara.focus();
		return;
	}*/
	var x = create_request_string(document.Fekptg_view_pdt_FrmViewEnakmen);
	document.Fekptg_view_pdt_FrmViewEnakmen.method="post";
	document.Fekptg_view_pdt_FrmViewEnakmen.enctype="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewEnakmen.encoding="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&mode=add&action=save&"+x;
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=add");
}

function doKemaskiniJadualPara(IDJadual) {
	/*if (document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Para.value == "") {
		alert("Sila pilih Para");
		document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Para.focus();
		return;
	}
	else if (document.Fekptg_view_pdt_FrmViewEnakmen.txtSubPara.value == "") {
		alert("Sila masukkan maklumat sub para");
		document.Fekptg_view_pdt_FrmViewEnakmen.txtSubPara.focus();
		return;
	}*/
	var x = create_request_string(document.Fekptg_view_pdt_FrmViewEnakmen);
	document.Fekptg_view_pdt_FrmViewEnakmen.method="post";
	document.Fekptg_view_pdt_FrmViewEnakmen.enctype="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewEnakmen.encoding="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&IDJadual="+IDJadual+"&mode=update&action=save&"+x;
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=save&mode=add");
}

function doHapusJadual(IDJadual) {
	  if ( !window.confirm("Jadual dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  //document.Fekptg_view_pdt_FrmViewEnakmen.ID_Jadual.value = ID_Jadual;
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=delete&IDJadual="+IDJadual);
}


function viewJadual(IDJadual) {
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=viewDetail&IDJadual="+IDJadual);
}

////////////////
function doSaveJadualLampiran() {
	if (document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Jadual.value == "") {
		alert("Sila pilih Jadual");
		document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Jadual.focus();
		return;
	}
	var x = create_request_string(document.Fekptg_view_pdt_FrmViewEnakmen);
	document.Fekptg_view_pdt_FrmViewEnakmen.method="post";
	document.Fekptg_view_pdt_FrmViewEnakmen.enctype="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewEnakmen.encoding="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&mode=add&action=save&"+x;
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}
function doKemaskiniJadualLampiran(ID_JadualLampiran) {
	if (document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Jadual.value == "") {
		alert("Sila pilih Jadual");
		document.Fekptg_view_pdt_FrmViewEnakmen.SOC_Jadual.focus();
		return;
	}
	var x = create_request_string(document.Fekptg_view_pdt_FrmViewEnakmen);
	document.Fekptg_view_pdt_FrmViewEnakmen.method="post";
	document.Fekptg_view_pdt_FrmViewEnakmen.enctype="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewEnakmen.encoding="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&IDJadualLampiran="+ID_JadualLampiran+"&mode=update&action=save&"+x;
	document.Fekptg_view_pdt_FrmViewEnakmen.submit();
}
function doHapusJadualLampiran(ID_JadualLampiran) {
	  if ( !window.confirm("Lampiran jadual akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=delete&IDJadualLampiran="+ID_JadualLampiran);
}
function viewJadualLampiran(ID_JadualLampiran) {
	  doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=viewDetail&IDJadualLampiran="+ID_JadualLampiran);
}

function doCancel2() {
 doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=$action");
}

	function doCancel() {
		//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=view");
		document.Fekptg_view_pdt_FrmViewEnakmen.action = "?_portal_module=ekptg.view.pdt.FrmViewEnakmen&action=view";
		document.Fekptg_view_pdt_FrmViewEnakmen.submit();		
	
	}


function viewJadualLampiranBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=jaduallampiran&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function doChangeJumlahLampiran(IDJadualLampiran,j,a) {

	if (j.value < 1) {
		alert("Sila masukkan nombor yang sah");
		j.value = 1;
		return;
	}
	doAjaxCallFekptg_view_pdt_FrmViewAkta("changejumlahlampiran","X="+a+"&action=changeLampiran&IDJadualLampiran="+IDJadualLampiran);

	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("changejumlahlampiran","action=changeLampiran&IDJadualLampiran="+IDJadualLampiran);
}

function deleteJadualLampiranBlob(IDJadualLampiran,IDLampiranFail) {
doAjaxCallFekptg_view_pdt_FrmViewEnakmen("deleteLampiran","action=deleteLampiran&IDJadualLampiran="+IDJadualLampiran+"&idLampiranFail="+IDLampiranFail);
}

function deleteEnakmen(idEnakmen) {
    if ( !window.confirm("Enakmen akan dihapuskan. Adakah Anda Pasti?") ) return;	
    document.Fekptg_view_pdt_FrmViewEnakmen.Enakmen_IDEnakmen.value = idEnakmen;
    doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=delete&Enakmen_IDEnakmen="+idEnakmen);
}