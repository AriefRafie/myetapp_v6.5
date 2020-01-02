
function getDisableFieldDiv(div,mode) {
	
	//alert('masuk div == '+ div+" mode :: "+mode);
    var elements = document.getElementById(div);
	//alert('elements == '+ elements);
	var inputElements = elements.querySelectorAll("input, select, checkbox, radio, textarea");
	//alert("inputElements == " + inputElements);
    //var inputTypes = ['text', 'select', 'textarea','input'];
	
	if(mode=='view'){
		for (var i = 0; i < inputElements.length; i++) {
			//alert(inputElements[i].name+" == "+inputElements[i].type);
			var elm = inputElements[i];
			if(elm.type != "hidden")
			{
			//alert("t == "+inputElements[i].type+" elem : "+elm);
			elm.class = 'disabled';
			elm.disabled = 'disabled'; 
			}
						
		}	
	}
	
}

	
    /*for (var i = 0; i < inputElements.length; i++) {
        alert("t == "+inputElements[i].type);
		var elm = inputElements[i];
		
		if(mode=='view')
		{
			alert('mode' + mode);
			elm.class = 'disabled';
			elm.disabled = 'disabled'; 
		}
		else
		{
			alert('mode' + mode);
			elm.class = '';
			elm.disabled = ''; 
		}
		
		var elm = elements[i];
        if (typeof elm.type !== 'undefined' && inputTypes.indexOf(elm.type)) {
			alert('a :'+elm.type);
           // console.log(elm);
           // console.log(elm.type);
        }
		
    }*/


	function doKemaskiniAkta() {
	    //doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=doKemaskiniAkta");
	    document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&action=doKemaskiniAkta";
		document.Fekptg_view_pdt_FrmViewAkta.submit();
	}

	function kosongCarianAkta() {
	    document.Fekptg_view_pdt_FrmViewAkta.reset();
	}
	
	function doAdd() {
	    //2012/09/07 komen sebab perlu guna FCK Editor 
	    doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=update");
	    //document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&action=update;
		//document.Fekptg_view_pdt_FrmViewAkta.submit();
			    
	}

function searchAkta() {
    document.Fekptg_view_pdt_FrmViewAkta.action.value = 'cari';
    document.Fekptg_view_pdt_FrmViewAkta.submit();
	
}
	function viewAkta(Akta_IDAkta) {
	    document.Fekptg_view_pdt_FrmViewAkta.Akta_IDAkta.value = Akta_IDAkta;
	    	//document.Fekptg_view_pdt_FrmViewAkta.action.value = "view";
	    	//document.Fekptg_view_pdt_FrmViewAkta.submit();
		//doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=view");
    	document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&action=view";
		document.Fekptg_view_pdt_FrmViewAkta.submit();
		
	}
	
function resetAkta() {
    document.Fekptg_view_pdt_FrmViewAkta.reset();
    document.Fekptg_view_pdt_FrmViewAkta.txtNoFail.value = "";
    document.Fekptg_view_pdt_FrmViewAkta.txtNamaFail.value = "";
    document.Fekptg_view_pdt_FrmViewAkta.txdTarikhKuatkuasa.value = "";
}
//////////////////////////
	function tambahAkta(){
			//document.Fekptg_view_pdt_FrmViewAkta.action.value="tambah";
			//document.Fekptg_view_pdt_FrmViewAkta.submit();
		//doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=tambah");
		//document.Fekptg_view_pdt_FrmViewAkta.action.value="tambah";
  		document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&action=tambah";
		document.Fekptg_view_pdt_FrmViewAkta.submit();
	
	}

function XviewAktaBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=akta&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

	function doChangeTab(TabID) {
	    //document.Fekptg_view_pdt_FrmViewAkta.action.value = '$action';
	    document.Fekptg_view_pdt_FrmViewAkta.selectedTab.value = TabID;
	    //document.Fekptg_view_pdt_FrmViewAkta.submit();
	    //doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=$action");
	    doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=view");	    
	    
	}

	function doChangeTabEditor(TabID) {
	    document.Fekptg_view_pdt_FrmViewAkta.selectedTab.value = TabID;
	    // 2012/09/07
	    //doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=view");	    
		document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&action=view";
		document.Fekptg_view_pdt_FrmViewAkta.submit();
	    
	}
		
function doChangeSOC(submitType) {
    //document.Fekptg_view_pdt_FrmViewAkta.action.value = '$action';
    //doAjaxCallFekptg_view_pdt_FrmViewAkta(submitType);
    //var x = $action;
    //alert(submitType);
    doAjaxCallFekptg_view_pdt_FrmViewAkta(submitType,"action=view");
    //doAjaxCallFekptg_view_pdt_FrmViewAkta(submitType,"action=doChanges");
}

	function doUpdate() {
		if (document.Fekptg_view_pdt_FrmViewAkta.txtNoAkta.value == "") {
			alert("Sila masukkan no akta");
			document.Fekptg_view_pdt_FrmViewAkta.txtNoAkta.focus();
			return;
		}
		else if (document.Fekptg_view_pdt_FrmViewAkta.txtNamaAkta.value == "") {
			alert("Sila masukkan nama akta");
			document.Fekptg_view_pdt_FrmViewAkta.txtNamaAkta.focus();
			return;
		}
		var editorInstance = FCKeditorAPI.GetInstance('txtCatatan_');   
	    var tajuk_Dokumen = editorInstance.GetHTML(true);
        var textlength = tajuk_Dokumen.length;  
		
		var x = create_request_string(document.Fekptg_view_pdt_FrmViewAkta);
		//return;
		document.Fekptg_view_pdt_FrmViewAkta.method="post";
		document.Fekptg_view_pdt_FrmViewAkta.enctype="multipart/form-data";
		document.Fekptg_view_pdt_FrmViewAkta.encoding="multipart/form-data";
		document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&mode=update&action=save&"+x+"&txtCatatan="+tajuk_Dokumen;
		document.Fekptg_view_pdt_FrmViewAkta.submit();
		
	}

	function doSave() {
	    //document.Fekptg_view_pdt_FrmViewAkta.action.value = 'save';
	    //document.Fekptg_view_pdt_FrmViewAkta.submit();
	    // 2012/09/07
	    //doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save");
	  	document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&action=save";
		document.Fekptg_view_pdt_FrmViewAkta.submit();  
		
	}

	function doSaveAkta() {
		if (document.Fekptg_view_pdt_FrmViewAkta.txtNoAkta.value == "") {
			alert("Sila masukkan no. akta");
			document.Fekptg_view_pdt_FrmViewAkta.txtNoAkta.focus();
			return;
		
		}else if (document.Fekptg_view_pdt_FrmViewAkta.txtNamaAkta.value == "") {
			alert("Sila masukkan nama akta");
			document.Fekptg_view_pdt_FrmViewAkta.txtNamaAkta.focus();
			return;
		}
		var editorInstance = FCKeditorAPI.GetInstance('txtCatatan_');   
	    var tajuk_Dokumen = editorInstance.GetHTML(true);
        var textlength = tajuk_Dokumen.length;  
		
	    // 2012/09/07
	    var x = create_request_string(document.Fekptg_view_pdt_FrmViewAkta);
	    //var x = X;
	    document.Fekptg_view_pdt_FrmViewAkta.method="post";
	    document.Fekptg_view_pdt_FrmViewAkta.enctype="multipart/form-data";
	    document.Fekptg_view_pdt_FrmViewAkta.encoding="multipart/form-data";
	    document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&mode=insert&action=save&"+x+"&txtCatatan="+tajuk_Dokumen;
	    document.Fekptg_view_pdt_FrmViewAkta.submit();
	    
	}

function doBack() {
    //document.Fekptg_view_pdt_FrmViewAkta.action.value = 'view';
    //document.Fekptg_view_pdt_FrmViewAkta.submit();
    doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=view");
}
function doDelete() {
    //if ( !window.confirm("Akta [444] akan dihapuskan. Adakah Anda Pasti?") ) return;	
	if ( !window.confirm("Akta akan dihapuskan. Adakah Anda Pasti?") ) return;	
    document.Fekptg_view_pdt_FrmViewAkta.action.value = 'delete';
    document.Fekptg_view_pdt_FrmViewAkta.submit();
}
function doPrint() {
    document.Fekptg_view_pdt_FrmViewAkta.action.value = 'print';
    document.Fekptg_view_pdt_FrmViewAkta.submit();
}
function addPegawaiSulit() {
}

function doBack() {
	doAjaxCallFekptg_view_pdt_FrmViewAkta("");
}

///PENGGAL
function doSavePenggal() {
	if (document.Fekptg_view_pdt_FrmViewAkta.txtNoPenggal.value == "") {
		alert("Sila masukkan no penggal");
		document.Fekptg_view_pdt_FrmViewAkta.txtNoPenggal.focus();
		return;
	}
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=add");
}

function viewPenggal(ID_Penggal) {
	//alert("ID_Penggal : "+ID_Penggal);
	document.Fekptg_view_pdt_FrmViewAkta.Penggal_IDPenggal.value = ID_Penggal;
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=viewDetail");
}

function doKemaskiniPenggal() {
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=update");
}

function doHapusPenggal(ID_Penggal) {
	  if ( !window.confirm("Penggal dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewAkta.Penggal_IDPenggal.value = ID_Penggal;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=delete&Penggal_IDPenggal="+ID_Penggal);
}

//BAHAGIAN
function doSaveBahagian() {
	
	/*
	if (document.Fekptg_view_pdt_FrmViewAkta.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.Fekptg_view_pdt_FrmViewAkta.SOC_Penggal.focus();
		return;
	}
	*/
	if (document.Fekptg_view_pdt_FrmViewAkta.txtNoBahagian.value == "") {
		alert("Sila masukkan no bahagian");
		document.Fekptg_view_pdt_FrmViewAkta.txtNoBahagian.focus();
		return;
	}
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=add");
}

function doKemaskiniBahagian() {
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=update");
}

function viewBahagian(ID_Bahagian) {
	  document.Fekptg_view_pdt_FrmViewAkta.Bahagian_IDBahagian.value = ID_Bahagian;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=viewDetail&Bahagian_IDBahagian="+ID_Bahagian);
}

function doHapusBahagian(ID_Bahagian) {
	  if ( !window.confirm("Bahagian dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewAkta.Bahagian_IDBahagian.value = ID_Bahagian;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=delete&Bahagian_IDBahagian="+ID_Bahagian);
}

//BAB
function doSaveBab() {
	/*
	if (document.Fekptg_view_pdt_FrmViewAkta.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.Fekptg_view_pdt_FrmViewAkta.SOC_Penggal.focus();
		return;
	}
	*/
	if (document.Fekptg_view_pdt_FrmViewAkta.txtNoBab.value == "") {
		alert("Sila masukkan no bab");
		document.Fekptg_view_pdt_FrmViewAkta.txtNoBab.focus();
		return;
	}
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=add");
}

function doKemaskiniBab() {
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=update");
}

function viewBab(ID_Bab) {
	  document.Fekptg_view_pdt_FrmViewAkta.Bab_IDBab.value = ID_Bab;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=viewDetail&Bab_IDBab="+ID_Bab);
}

function doHapusBab(ID_Bab) {
	  if ( !window.confirm("Bab dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewAkta.Bab_IDBab.value = ID_Bab;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=delete&Bab_IDBab="+ID_Bab);
}

//SEKSYEN
function doSaveSeksyen() {
	/*
	if (document.Fekptg_view_pdt_FrmViewAkta.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.Fekptg_view_pdt_FrmViewAkta.SOC_Penggal.focus();
		return;
	}
	else 
	*/
	if (document.Fekptg_view_pdt_FrmViewAkta.txtNoSeksyen.value == "") {
		alert("Sila masukkan no seksyen");
		document.Fekptg_view_pdt_FrmViewAkta.txtNoSeksyen.focus();
		return;
	}
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=add");
}

function doKemaskiniSeksyen() {
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=update");
}

function viewSeksyen(ID_Seksyen) {
	  document.Fekptg_view_pdt_FrmViewAkta.Seksyen_IDSeksyen.value = ID_Seksyen;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=viewDetail&Seksyen_IDSeksyen="+ID_Seksyen);
}

function doHapusSeksyen(ID_Seksyen) {
	  if ( !window.confirm("Seksyen dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewAkta.Seksyen_IDSeksyen.value = ID_Seksyen;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=delete&Seksyen_IDSeksyen="+ID_Seksyen);
}
/// SUB SEKSYEN
function doSaveSubSeksyen() {
	/*
	if (document.Fekptg_view_pdt_FrmViewAkta.SOC_Penggal.value == "") {
		alert("Sila pilih Penggal");
		document.Fekptg_view_pdt_FrmViewAkta.SOC_Penggal.focus();
		return;
	}
	else 
	*/
	if (document.Fekptg_view_pdt_FrmViewAkta.SOC_Seksyen.value == "") {
			alert("Sila pilih Seksyen");
			document.Fekptg_view_pdt_FrmViewAkta.SOC_Seksyen.focus();
			return;
		}
	else if (document.Fekptg_view_pdt_FrmViewAkta.txtNoSubSeksyen.value == "") {
		alert("Sila masukkan no seksyen");
		document.Fekptg_view_pdt_FrmViewAkta.txtNoSubSeksyen.focus();
		return;
	}
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=add");
}

function doKemaskiniSubSeksyen(ID_SubSeksyen) {
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=update&SubSeksyen_IDSubSeksyen="+ID_SubSeksyen);
}

function viewSubSeksyen(ID_SubSeksyen) {
	  document.Fekptg_view_pdt_FrmViewAkta.SubSeksyen_IDSubSeksyen.value = ID_SubSeksyen;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=viewDetail&SubSeksyen_IDSubSeksyen="+ID_SubSeksyen);
}

function doHapusSubSeksyen(ID_SubSeksyen) {
	  if ( !window.confirm("Sub Seksyen dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewAkta.SubSeksyen_IDSubSeksyen.value = ID_SubSeksyen;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=delete&SubSeksyen_IDSubSeksyen="+ID_SubSeksyen);
}
/// PARA
function doSavePara() {
	if (document.Fekptg_view_pdt_FrmViewAkta.SOC_SubSeksyen.value == "") {
		alert("Sila Pilih SubSeksyen");
		document.Fekptg_view_pdt_FrmViewAkta.SOC_SubSeksyen.focus();
		return;
	}
	else if (document.Fekptg_view_pdt_FrmViewAkta.txtPara.value == "") {
		alert("Sila Masukkan Para");
		document.Fekptg_view_pdt_FrmViewAkta.txtPara.focus();
		return;
	}
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=add");
}

function doKemaskiniPara() {
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=update");
}

function viewPara(ID_Para) {
	  document.Fekptg_view_pdt_FrmViewAkta.Para_IDPara.value = ID_Para;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=viewDetail&Para_IDPara="+ID_Para);
}

function doHapusPara(ID_Para) {
	  if ( !window.confirm("Para dan kesemua maklumat yang berkaitan akan dihapuskan.\Adakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewAkta.Para_IDPara.value = ID_Para;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=delete&Para_IDPara="+ID_Para);
}

/// SUB PARA
function doSaveSubPara() {
	if (document.Fekptg_view_pdt_FrmViewAkta.SOC_Para.value == "") {
		alert("Sila pilih Para");
		document.Fekptg_view_pdt_FrmViewAkta.SOC_Para.focus();
		return;
	}
	else if (document.Fekptg_view_pdt_FrmViewAkta.txtSubPara.value == "") {
		alert("Sila masukkan maklumat sub para");
		document.Fekptg_view_pdt_FrmViewAkta.txtSubPara.focus();
		return;
	}
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=add");
}

function doKemaskiniSubPara() {
	doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=update");
}

function viewSubPara(ID_subPara) {
	  document.Fekptg_view_pdt_FrmViewAkta.SubPara_IDSubPara.value = ID_subPara;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=viewDetail&SubPara_IDSubPara="+ID_subPara);
}

function doHapusSubPara(ID_subPara) {
	  if ( !window.confirm("Sub para dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  document.Fekptg_view_pdt_FrmViewAkta.SubPara_IDSubPara.value = ID_subPara;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=delete&SubPara_IDSubPara="+ID_subPara);
}


function viewAktaBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=akta&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function doSaveJadualPara() {
	/*if (document.Fekptg_view_pdt_FrmViewAkta.SOC_Para.value == "") {
		alert("Sila pilih Para");
		document.Fekptg_view_pdt_FrmViewAkta.SOC_Para.focus();
		return;
	}
	else if (document.Fekptg_view_pdt_FrmViewAkta.txtSubPara.value == "") {
		alert("Sila masukkan maklumat sub para");
		document.Fekptg_view_pdt_FrmViewAkta.txtSubPara.focus();
		return;
	}*/
	var x = create_request_string(document.Fekptg_view_pdt_FrmViewAkta);
	document.Fekptg_view_pdt_FrmViewAkta.method="post";
	document.Fekptg_view_pdt_FrmViewAkta.enctype="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewAkta.encoding="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&mode=add&action=save&"+x;
	document.Fekptg_view_pdt_FrmViewAkta.submit();
	//doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=add");
}

function doKemaskiniJadualPara(IDJadual) {
	/*if (document.Fekptg_view_pdt_FrmViewAkta.SOC_Para.value == "") {
		alert("Sila pilih Para");
		document.Fekptg_view_pdt_FrmViewAkta.SOC_Para.focus();
		return;
	}
	else if (document.Fekptg_view_pdt_FrmViewAkta.txtSubPara.value == "") {
		alert("Sila masukkan maklumat sub para");
		document.Fekptg_view_pdt_FrmViewAkta.txtSubPara.focus();
		return;
	}*/
	var x = create_request_string(document.Fekptg_view_pdt_FrmViewAkta);
	document.Fekptg_view_pdt_FrmViewAkta.method="post";
	document.Fekptg_view_pdt_FrmViewAkta.enctype="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewAkta.encoding="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&IDJadual="+IDJadual+"&mode=update&action=save&"+x;
	document.Fekptg_view_pdt_FrmViewAkta.submit();
	//doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=save&mode=add");
}

function doHapusJadual(IDJadual) {
	  if ( !window.confirm("Jadual dan kesemua maklumat yang berkaitan akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  //document.Fekptg_view_pdt_FrmViewAkta.ID_Jadual.value = ID_Jadual;
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=delete&IDJadual="+IDJadual);
}


function viewJadual(IDJadual) {
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=viewDetail&IDJadual="+IDJadual);
}

////////////////
function doSaveJadualLampiran() {
	if (document.Fekptg_view_pdt_FrmViewAkta.SOC_Jadual.value == "") {
		alert("Sila pilih Jadual");
		document.Fekptg_view_pdt_FrmViewAkta.SOC_Jadual.focus();
		return;
	}
	var x = create_request_string(document.Fekptg_view_pdt_FrmViewAkta);
	document.Fekptg_view_pdt_FrmViewAkta.method="post";
	document.Fekptg_view_pdt_FrmViewAkta.enctype="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewAkta.encoding="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&mode=add&action=save&"+x;
	document.Fekptg_view_pdt_FrmViewAkta.submit();
}
function doKemaskiniJadualLampiran(ID_JadualLampiran) {
	if (document.Fekptg_view_pdt_FrmViewAkta.SOC_Jadual.value == "") {
		alert("Sila pilih Jadual");
		document.Fekptg_view_pdt_FrmViewAkta.SOC_Jadual.focus();
		return;
	}
	var x = create_request_string(document.Fekptg_view_pdt_FrmViewAkta);
	document.Fekptg_view_pdt_FrmViewAkta.method="post";
	document.Fekptg_view_pdt_FrmViewAkta.enctype="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewAkta.encoding="multipart/form-data";
	document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&IDJadualLampiran="+ID_JadualLampiran+"&mode=update&action=save&"+x;
	document.Fekptg_view_pdt_FrmViewAkta.submit();
}
function doHapusJadualLampiran(ID_JadualLampiran) {
	  if ( !window.confirm("Lampiran jadual akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=delete&IDJadualLampiran="+ID_JadualLampiran);
}
function viewJadualLampiran(ID_JadualLampiran) {
	  doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=viewDetail&IDJadualLampiran="+ID_JadualLampiran);
}

function doCancel2() {
 doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=$action");
}

	function doCancel() {
		//doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=view");
		document.Fekptg_view_pdt_FrmViewAkta.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta&action=view";
		document.Fekptg_view_pdt_FrmViewAkta.submit();
		
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
}

function deleteJadualLampiranBlob(IDJadualLampiran,IDLampiranFail) {
	doAjaxCallFekptg_view_pdt_FrmViewAkta("deleteLampiran","action=deleteLampiran&IDJadualLampiran="+IDJadualLampiran+"&idLampiranFail="+IDLampiranFail);
}

function paparAkta(idAkta) {
    var url = "../y/ekptg.view.pdt.FrmViewAkta2?idAkta="+idAkta;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function deleteAkta(idAkta) {
    if ( !window.confirm("Akta akan dihapuskan. Adakah Anda Pasti?") ) return;	
    document.Fekptg_view_pdt_FrmViewAkta.Akta_IDAkta.value = idAkta;
    doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=delete&Akta_IDAkta="+idAkta);
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}