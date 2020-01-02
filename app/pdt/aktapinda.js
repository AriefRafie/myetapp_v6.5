
	function doUpdate_() {
		if (document.Fekptg_view_pdt_PendaftaranAktaPindaan.txtNoAktaPindaan.value == "") {
			alert("Sila Masukkan No Akta Pindaan");
			document.Fekptg_view_pdt_PendaftaranAktaPindaan.txtNoAktaPindaan.focus();
			return;
		}
		if (document.Fekptg_view_pdt_PendaftaranAktaPindaan.txtNamaAktaPindaan.value == "") {
			alert("Sila Masukkan Nama Akta Pindaan");
			document.Fekptg_view_pdt_PendaftaranAktaPindaan.txtNamaAktaPindaan.focus();
			return;
		}
		
		var editorInstance = FCKeditorAPI.GetInstance('txtCatatan_');   
	    var tajuk_Dokumen = editorInstance.GetHTML(true);
        var textlength = tajuk_Dokumen.length;                           
		if(textlength==0){
	   		alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
	        oEditor.EditorDocument.body.focus();
	        return;
	   	}	
		alert("."+tajuk_Dokumen);
		alert("."+document.Fekptg_view_pdt_PendaftaranAktaPindaan.txtCatatan.value);
	   	
		//var x = create_request_string(document.${formName});
		var x = "0";
		//return;
		//document.Fekptg_view_pdt_PendaftaranAktaPindaan.method="post";
		document.Fekptg_view_pdt_PendaftaranAktaPindaan.action = "?_portal_module=ekptg.view.pdt.PendaftaranAktaPindaan&command=simpan&mode=doUpdate&"+x+"&txtCatatan="+tajuk_Dokumen;
		document.Fekptg_view_pdt_PendaftaranAktaPindaan.enctype="multipart/form-data";
		document.Fekptg_view_pdt_PendaftaranAktaPindaan.encoding="multipart/form-data";
		//document.Fekptg_view_pdt_PendaftaranAktaPindaan.submit();
			
			//doAjaxCall${formName}("simpan","mode=doUpdate");
			
	}