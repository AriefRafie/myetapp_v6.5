<!-- defaul skrin carian, kalo tak pakai xperlu letak -->
#parse("app/ppk/MyInfoPPK/carian.jsp")



<!-- first load -->
<div id="div_myinfoppk_list" > 
<script>  $jquery(document).ready(function (){  
	doDivAjaxCall$formname('div_myinfoppk_list','showSenarai','&div=div_myinfoppk_list&namaList=myinfoppk_list&skrinName=myinfoppk');  
}); 
</script>
</div >



<script>

function semakBorangC(x) {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmMTBorangC?noFail="+x+"&command=borangPermohonan";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

//resetSkrinCarian
function resetSkrinCarian(skrinName)
{
	document.getElementById(skrinName+"CARIAN_NO_FAIL").value = "";
	document.getElementById(skrinName+"CARIAN_NAMA_SIMATI").value = "";
	document.getElementById(skrinName+"CARIAN_PENGENALAN_SIMATI").value = "";
	document.getElementById(skrinName+"CARIAN_NAMA_PEMOHON").value = "";
	document.getElementById(skrinName+"CARIAN_PENGENALAN_PEMOHON").value = "";
	document.getElementById(skrinName+"CARIAN_STATUS").value = "";
	document.getElementById(skrinName+"CARIAN_NO_HAKMILIK").value = "";
	document.getElementById(skrinName+"CARIAN_NO_PT").value = "";
	document.getElementById(skrinName+"CARIAN_NO_SIJILMATI").value = "";
	document.getElementById(skrinName+"CARIAN_TARIKH_MOHON").value = "";
	document.getElementById(skrinName+"CARIAN_TARIKH_BICARA").value = "";
	document.getElementById(skrinName+"CARIAN_PENDAFTAR").value = "";
	
	document.getElementById("WC"+skrinName+"CARIAN_NO_FAIL").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_NAMA_SIMATI").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_PENGENALAN_SIMATI").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_NAMA_PEMOHON").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_PENGENALAN_PEMOHON").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_NO_HAKMILIK").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_NO_PT").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_NO_SIJILMATI").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_PENDAFTAR").value = "1";
	
}

function checkSkrinCarian(skrinName)
{
	var CARIAN_NO_FAIL = document.getElementById(skrinName+"CARIAN_NO_FAIL").value;
	var CARIAN_NAMA_SIMATI = document.getElementById(skrinName+"CARIAN_NAMA_SIMATI").value;
	var CARIAN_PENGENALAN_SIMATI = document.getElementById(skrinName+"CARIAN_PENGENALAN_SIMATI").value;
	var CARIAN_NAMA_PEMOHON = document.getElementById(skrinName+"CARIAN_NAMA_PEMOHON").value;
	var CARIAN_PENGENALAN_PEMOHON = document.getElementById(skrinName+"CARIAN_PENGENALAN_PEMOHON").value;
	var CARIAN_STATUS = document.getElementById(skrinName+"CARIAN_STATUS").value;
	var CARIAN_NO_HAKMILIK = document.getElementById(skrinName+"CARIAN_NO_HAKMILIK").value;
	var CARIAN_NO_PT = document.getElementById(skrinName+"CARIAN_NO_PT").value;
	var CARIAN_NO_SIJILMATI = document.getElementById(skrinName+"CARIAN_NO_SIJILMATI").value;
	var CARIAN_TARIKH_MOHON = document.getElementById(skrinName+"CARIAN_TARIKH_MOHON").value;
	var CARIAN_TARIKH_BICARA = document.getElementById(skrinName+"CARIAN_TARIKH_BICARA").value;
	var CARIAN_PENDAFTAR = document.getElementById(skrinName+"CARIAN_PENDAFTAR").value;
	
	var checkCari = true;
	
	if((CARIAN_NO_FAIL == "" || CARIAN_NO_FAIL == "JKPTG/PK/")
	&& CARIAN_NAMA_SIMATI == ""
	&& CARIAN_PENGENALAN_SIMATI == "" 
	&& CARIAN_NAMA_PEMOHON == ""
	&& CARIAN_PENGENALAN_PEMOHON == "" 
	&& CARIAN_STATUS == ""
	&& CARIAN_NO_HAKMILIK == ""
	&& CARIAN_NO_PT == "" 
	&& CARIAN_NO_SIJILMATI == ""
	&& CARIAN_TARIKH_MOHON == ""
	&& CARIAN_TARIKH_BICARA == ""
	&& CARIAN_PENDAFTAR == ""
	)
	{
		 alert("Sila Masukkan 'Filter' Carian!");
		 checkCari = false;
	}
	
	var bool_check_last = false;
	if(checkCari == true)
	{		
		//input_box = confirm("Adakah anda pasti?" );
		//if (input_box == true) {
			bool_check_last = true;
		//}
	}
	return bool_check_last;
	
}


function papar(idPermohonan,idStatus,idFail,idSimati,idpermohonansimati,tarikhMohon,jenisfail,seksyen) {

		if (idStatus == '41' || idStatus == '25'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
			}
		
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idPermohonanSimati.value = idpermohonansimati;
			document.${formName}.idStatus.value = idStatus;
		
		}	
	 	else 
			if (idStatus == '53' || idStatus == '151' ){
				if (seksyen == '8'){
					document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData&tabId=0";
				}else{
					document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakNoData&tabId=0";
				}
				
				document.${formName}.id_permohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
			
			} else  if (idStatus == '44' || idStatus == '173' || idStatus == '175' || idStatus == '177' ){
				if (seksyen == '8'){
					document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis";
				}else{
					document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=tukarNotis";
				}
				document.${formName}.id_permohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
			} 
		else 
		if (idStatus == '21'
		 //|| idStatus == '25'
		 ){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=semakRayuan";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=semakRayuan";
			}			
				document.${formName}.id_permohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
	} else 
		if (idStatus == '64'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=maklumatRayuan&tabId=0";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=maklumatRayuan&tabId=0";
			}			
				document.${formName}.id_permohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
	} else 
		if (idStatus == '163' || idStatus == '164' || idStatus == '165' || idStatus == '166' || idStatus == '167' || idStatus == '180'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&command=semakKeputusanRayuan";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaanSek17&command=semakKeputusanRayuan";
			}
				document.${formName}.id_permohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
	} else 
//elly
		if (idStatus == '18'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_notis";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_notis";
			}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
				document.${formName}.id_Simati.value = idSimati;
	} else 
		if (idStatus == '47'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_batal";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_batal";
			}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
				document.${formName}.id_Simati.value = idSimati;
	}
	else 
		if (idStatus == '172'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_kolateral";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_kolateral";
			}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
				document.${formName}.id_Simati.value = idSimati;

	} else 
		if (idStatus == '174'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanMT";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanMT";
			}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
				document.${formName}.id_Simati.value = idSimati;
	}else 
		if (idStatus == '176'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanROTS";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanROTS";
			}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
				document.${formName}.id_Simati.value = idSimati;

	}
	else 
		if (idStatus == '56')
		{
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE&command=papar";
			}
			
			if (seksyen == '17'){
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE&command=papar17";
			}
			
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.idPermohonan.value = idPermohonan;
		}
	
	else 
		if ((idStatus == '8' || idStatus == '9' || idStatus == '170' || idStatus == '169' || idStatus == '160' ) && (jenisfail == '1' || jenisfail == '2' )){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
			}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.idSimati.value = idSimati;
	} 
	 else 
		if ((idStatus == '8' || idStatus == '9' || idStatus == '170') && jenisfail == '3'){
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8InternalKutipan&command=papar";
			document.${formName}.idpermohonan.value = idPermohonan;
	} 
	 else 
//man		
		if (idStatus == '14' || idStatus == '50' || idStatus == '152'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
			}
				document.${formName}.idPermohonan.value = idPermohonan;
				document.${formName}.idpermohonansimati.value = idpermohonansimati;
				document.${formName}.idSimati.value = idSimati;
				document.${formName}.tarikh_mohon.value = tarikhMohon;
		
	} else 
//kak ayu		
	if (idStatus == '153'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE&hitButt=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanSek17BKE&hitButt=papar";
		}
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.idsimati.value = idSimati;
	
		// Kemaskini oleh Mohamad Rosli pada 10/03/2011, tambah kondisi untuk status BATAL LAIN - LAIN KES (id_status=70)	
		} else if(idStatus == '70'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
			}
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idpermohonansimati.value = idpermohonansimati;
			document.${formName}.idSimati.value = idSimati;
			document.${formName}.tarikh_mohon.value = tarikhMohon;
	
		}else {
			alert("Sila Hubungi Sistem Admin")
	
		}
	
		document.${formName}.flagFromSenaraiPermohonanSek8.value = "yes";
		document.${formName}.method="POST";
		document.${formName}.submit();
	
	}
	
</script>

<!-- default index calls, wajib -->
#parse("app/RazTemplate/index.jsp")