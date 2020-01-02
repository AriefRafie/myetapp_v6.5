

<script>







function selectPelbagaiNegara(id_negeri,div_negara,tr_negara,nama_negara)
{

	//alert("cek negara masuk :"+id_negeri);
	
	if(id_negeri == '17')
	{
		
		//$jquery("#"+div_negara).html("<font size = '-3' color = 'blue' >Bagi yang <b>Pelbagai Negara</b>, Sila masukkan nama negara didalam ruangan yang disediakan dibawah.</font>");
		
		document.getElementById(div_negara).innerHTML = "<font size = '-3' color = 'blue' >Bagi yang <b>Pelbagai Negara</b>, Sila masukkan nama negara didalam ruangan yang disediakan dibawah.</font>";
		document.getElementById(tr_negara).style.display="";
		//alert("Y");
	}
	else
	{
		document.getElementById(div_negara).innerHTML = "";
		
		//$jquery("#"+div_negara).html("");
		document.getElementById(tr_negara).style.display="none";
		document.getElementById(nama_negara).value = "";
		//alert("t");
	}
	//alert("OUT");
}




function alamatwarga(jenis_warga,div_warga,tr_nama_warga,nama_warga)
{



	
	if(jenis_warga == '2')
	{
	//$jquery("#"+div_warga).html("<font size = '-3' color = 'blue' >Bagi yang <b>Bukan Warganegara</b>, maklumat <b>poskod</b>, <b>negeri</b> dan <b>bandar</b> tidak perlu diisi. Sila masukkan alamat luar negara didalam ruangan tiga baris diatas.</font>");
	
	document.getElementById(div_warga).innerHTML = "<font size = '-3' color = 'blue' >Bagi yang <b>Bukan Warganegara</b>, maklumat <b>poskod</b>, <b>negeri</b> dan <b>bandar</b> tidak perlu diisi. Sila masukkan alamat luar negara didalam ruangan tiga baris diatas.</font>";
	
	document.getElementById(tr_nama_warga).style.display="";
	}
	else
	{
	//$jquery("#"+div_warga).html("");
	document.getElementById(div_warga).innerHTML = "";
	document.getElementById(tr_nama_warga).style.display="none";
	document.getElementById(nama_warga).value = "";
	}
}


var timeout	= 500;
var closetimer	= 0;
var ddmenuitem	= 0;
// open hidden layer
function mopen(id)
{	
	// cancel close timer
	mcancelclosetime();
	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden' ;
	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';
	
}
// close showed layer
function mclose()
{	
if(ddmenuitem) ddmenuitem.style.visibility = 'hidden' ;
}
// go close timer
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}
// cancel close timer
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}
// close layer when click-out
document.onclick = mclose; 


</script>

#if($!flag_jenis_vm == "vtemplate")
<script type="text/javascript">
var idPermohonan = document.f1.idPermohonan;
var idPermohonanSimati = document.f1.idPermohonanSimati;
var idStatus = document.f1.idStatus;
var flagFromSenaraiFailSek8 = document.f1.flagFromSenaraiFailSek8;
var flagForView = document.f1.flagForView;
var id_perbicaraan = document.f1.id_perbicaraan;
var idpermohonan = document.f1.idpermohonan;
var idpermohonansimati = document.f1.idpermohonansimati;
var tarikh_mohon = document.f1.tarikh_mohon;
var id_status = document.f1.id_status;
var command = document.f1.command;
var id_Simati = document.f1.id_Simati;
var id_permohonan = document.f1.id_permohonan;
var idSimati = document.f1.idSimati;
var mode = document.f1.mode;
var string_field = "";

if(testIsValidObject(mode)==false)
{
string_field = string_field + "<input type='hidden'  name='mode'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(idPermohonan)==false)
{
string_field = string_field + "<input type='hidden'  name='idPermohonan'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(idPermohonanSimati)==false)
{
string_field = string_field + "<input type='hidden'  name='idPermohonanSimati'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(idStatus)==false)
{
string_field = string_field + "<input type='hidden'  name='idStatus'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(flagFromSenaraiFailSek8)==false)
{
string_field = string_field + "<input type='hidden'  name='flagFromSenaraiFailSek8'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(flagForView)==false)
{
string_field = string_field + "<input type='hidden'  name='flagForView'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(id_perbicaraan)==false)
{
string_field = string_field + "<input type='hidden'  name='id_perbicaraan'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(idpermohonan)==false)
{
string_field = string_field + "<input type='hidden'  name='idpermohonan'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(idpermohonansimati)==false)
{
string_field = string_field + "<input type='hidden'  name='idpermohonansimati'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(tarikh_mohon)==false)
{
string_field = string_field + "<input type='hidden'  name='tarikh_mohon'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(id_status)==false)
{
string_field = string_field + "<input type='hidden'  name='id_status'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(command)==false)
{
string_field = string_field + "<input type='hidden'  name='command'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(id_Simati)==false)
{
string_field = string_field + "<input type='hidden'  name='id_Simati'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(id_permohonan)==false)
{
string_field = string_field + "<input type='hidden'  name='id_permohonan'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(idSimati)==false)
{
string_field = string_field + "<input type='hidden'  name='idSimati'/>";
}
else
{
string_field = string_field + "";
}

if(string_field!="")
{
$jquery("#field_location").html(string_field);
}


function testIsValidObject(objToTest) {
//alert("1111111"+objToTest);
	if (objToTest == null || objToTest == undefined) 
	{
	return false;
	//alert("false");
	}
	else
	{
	return true;
	//alert("true");
	}
}






function currentFail(seksyen,idPermohonan,idStatus)
{
if (idStatus == '53' || idStatus == '151' )
{
			if (seksyen == '8'){
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData&tabId=0";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakNoData&tabId=0";
			}

} 
else if (idStatus == '44' || idStatus == '173' || idStatus == '175' || idStatus == '177' )
{
			if (seksyen == '8'){
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&tabId=0";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=tukarNotis&tabId=0";
			}

} 

else if (idStatus == '18')
{
			if (seksyen == '8'){
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&tabId=0";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData&tabId=0";
			}

}
			document.f1.id_permohonan.value = idPermohonan;
			document.f1.id_status.value = idStatus;
			document.f1.method="POST";
	document.f1.submit();
}


function paparKemaskiniKemaskiniBayaran(id_fail_carian,txtNoFailSub) {
document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmKemaskiniBayaranPusaka&command=paparSub&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"";
document.f1.submit();
}


function historyFail(seksyen,idPermohonan,idStatus,idPermohonanSimati)
{
if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
		}
			document.f1.idPermohonanSimati.value = idPermohonanSimati;
			document.f1.idPermohonan.value = idPermohonan;
			document.f1.idStatus.value = idStatus;
			document.f1.method="POST";
	document.f1.submit();
}



function papar_header(idPermohonan,idStatus,bil,idPermohonanSimati,tarikhMohon,jenisfail,seksyen,idSimati) {

	//keputusan rayuan
	if (idStatus == '164' || idStatus == '165' || idStatus == '166' || idStatus == '167' || idStatus == '180' ){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&command=semakKeputusanRayuan";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaanSek17&command=semakKeputusanRayuan";
		}
			document.f1.id_permohonan.value = idPermohonan;
			document.f1.id_status.value = idStatus;
	} else 
	//permohonan rayuan
	if (idStatus == '64' || idStatus == '163' ){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=semakRayuan";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=semakRayuan";
		}
			document.f1.id_permohonan.value = idPermohonan;
			document.f1.id_status.value = idStatus;
	} else
		
	//perintah
	if (idStatus == '21' || idStatus == '25'){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
		}
			document.f1.idPermohonanSimati.value = idPermohonanSimati;
			document.f1.idPermohonan.value = idPermohonan;
			document.f1.idStatus.value = idStatus;
		
	} else 
	//keputusan perbicaraan (selesai perbicaraan)
	if (idStatus == '41' ){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai";
		}
			document.f1.idpermohonan.value = idPermohonan;
			document.f1.id_status.value = idStatus;
			document.f1.id_Simati.value = idSimati;
	} else
	//keputusan perbicaraan (tangguh perbicaraan)		
	if (idStatus == '44' ){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_tangguh";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_tangguh";
		}
			document.f1.idpermohonan.value = idPermohonan;
			document.f1.id_status.value = idStatus;
			document.f1.id_Simati.value = idSimati;

	} else
	//keputusan perbicaraan (tangguh MT)		
	if (idStatus == '174' ){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanMT";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanMT";
		}
			document.f1.idpermohonan.value = idPermohonan;
			document.f1.id_status.value = idStatus;
			document.f1.id_Simati.value = idSimati;

	} else
	//keputusan perbicaraan (tangguh ROTS)		
	if (idStatus == '176' ){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanROTS";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanROTS";
		}
			document.f1.idpermohonan.value = idPermohonan;
			document.f1.id_status.value = idStatus;
			document.f1.id_Simati.value = idSimati;
			
	} else
	//keputusan perbicaraan (tangguh kolateral)		
	if (idStatus == '172' ){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_kolateral";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_kolateral";
		}
			document.f1.idpermohonan.value = idPermohonan;
			document.f1.id_status.value = idStatus;
			document.f1.id_Simati.value = idSimati;			
					
	} else
	//keputusan perbicaraan (batal perbicaraan)		
	if (idStatus == '47' ){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_batal";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_batal";
		}
			document.f1.idpermohonan.value = idPermohonan;
			document.f1.id_status.value = idStatus;
			document.f1.id_Simati.value = idSimati;		
	
	} else 
	//notis perbicaraan
	if ((idStatus == '18' || idStatus == '44' || idStatus == '175' || idStatus == '173' || idStatus == '177' )){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&tabId=0";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData&tabId=0";
		}
			document.f1.id_permohonan.value = idPermohonan;
			document.f1.id_status.value = idStatus;
	
	} else 
	//keputusan permohonan
	if ((idStatus == '50' || idStatus == '53' || idStatus == '151' || idStatus == '152' || idStatus == '14'  || idStatus == '70'  )){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
		}
			document.f1.idPermohonan.value = idPermohonan;
			document.f1.idpermohonansimati.value = idPermohonanSimati;
			document.f1.tarikh_mohon.value = tarikhMohon;
			document.f1.idSimati.value = idSimati;
		
	} 
	
	else 
	//pendaftaran
	if ((idStatus == '8' || idStatus == '9'  || idStatus == '169' ) && (jenisfail == '1' || jenisfail == '2' )){
		if (seksyen == '8'){
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";
		}else{
			document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
		}
			document.f1.idpermohonan.value = idPermohonan;
			document.f1.idPermohonan.value = idPermohonan;
			document.f1.command.value = "papar";
			document.f1.idSimati.value = idSimati;
			//document.f1.mode.value = "Simatiview";
			
	}
	if (bil == '1' && (jenisfail == '3' )){		
		document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8InternalKutipan&command=papar";
		document.f1.idpermohonan.value = idPermohonan;
		document.f1.idPermohonan.value = idPermohonan;
		document.f1.command.value = "papar";
		document.f1.idSimati.value = idSimati;
		//document.f1.mode.value = "Simatiview";
	}
	
	
	//document.f1.flagForView.value = "yes";
	document.f1.method="POST";
	//alert("XXXX");
	document.f1.submit();
}
</script>
#else
<script type="text/javascript">

//alert("::::::: 6");


var idPermohonan = document.${formName}.idPermohonan;
var idPermohonanSimati = document.${formName}.idPermohonanSimati;
var idStatus = document.${formName}.idStatus;
var flagFromSenaraiFailSek8 = document.${formName}.flagFromSenaraiFailSek8;
var flagForView = document.${formName}.flagForView;
var id_perbicaraan = document.${formName}.id_perbicaraan;
var idpermohonan = document.${formName}.idpermohonan;
var idpermohonansimati = document.${formName}.idpermohonansimati;
var tarikh_mohon = document.${formName}.tarikh_mohon;
var id_status = document.${formName}.id_status;
var command = document.${formName}.command;
var id_Simati = document.${formName}.id_Simati;
var id_permohonan = document.${formName}.id_permohonan;
var idSimati = document.${formName}.idSimati;
var mode = document.${formName}.mode;
var string_field = "";

if(testIsValidObject(mode)==false)
{
string_field = string_field + "<input type='hidden'  name='mode'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(idPermohonan)==false)
{
string_field = string_field + "<input type='hidden'  name='idPermohonan'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(idPermohonanSimati)==false)
{
string_field = string_field + "<input type='hidden'  name='idPermohonanSimati'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(idStatus)==false)
{
string_field = string_field + "<input type='hidden'  name='idStatus'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(flagFromSenaraiFailSek8)==false)
{
string_field = string_field + "<input type='hidden'  name='flagFromSenaraiFailSek8'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(flagForView)==false)
{
string_field = string_field + "<input type='hidden'  name='flagForView'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(id_perbicaraan)==false)
{
string_field = string_field + "<input type='hidden'  name='id_perbicaraan'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(idpermohonan)==false)
{
string_field = string_field + "<input type='hidden'  name='idpermohonan' />";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(idpermohonansimati)==false)
{
string_field = string_field + "<input type='hidden'  name='idpermohonansimati'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(tarikh_mohon)==false)
{
string_field = string_field + "<input type='hidden'  name='tarikh_mohon'/>";
}
else
{
string_field = string_field + "";
}

if(testIsValidObject(id_status)==false)
{
string_field = string_field + "<input type='hidden'  name='id_status'/>";
}
else
{
string_field = string_field + "";
}

/*
if(testIsValidObject(command)==false)
{
string_field = string_field + "<input type='hidden'  name='command'/>";
}
else
{
string_field = string_field + "";
}
*/

if(testIsValidObject(id_Simati)==false)
{
string_field = string_field + "<input type='hidden'  name='id_Simati'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(id_permohonan)==false)
{
string_field = string_field + "<input type='hidden'  name='id_permohonan'/>";
}
else
{
string_field = string_field + "";
}


if(testIsValidObject(idSimati)==false)
{
string_field = string_field + "<input type='hidden'  name='idSimati'/>";
}
else
{
string_field = string_field + "";
}

if(string_field!="")
{
$jquery("#field_location").html(string_field);
}


function testIsValidObject(objToTest) {
	
if (objToTest == null || objToTest == undefined) {

return false;
}

return true;
}




function currentFail(seksyen,idPermohonan,idStatus)
{
if (idStatus == '53' || idStatus == '151' )
{
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData&tabId=0";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakNoData&tabId=0";
			}

} 
else if (idStatus == '44' || idStatus == '173' || idStatus == '175' || idStatus == '177' )
{
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&tabId=0";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=tukarNotis&tabId=0";
			}

} 

else if (idStatus == '18')
{
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&tabId=0";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData&tabId=0";
			}

}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.method="POST";
	document.${formName}.submit();
}


function paparKemaskiniKemaskiniBayaran(id_fail_carian,txtNoFailSub) {
document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmKemaskiniBayaranPusaka&command=paparSub&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"";
document.${formName}.submit();
}

function historyFail(seksyen,idPermohonan,idStatus,idPermohonanSimati)
{
	//alert(":::::: 4");
if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
		}
		
			document.${formName}.idPermohonanSimati.value = idPermohonanSimati;
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idStatus.value = idStatus;
			document.${formName}.method="POST";
	document.${formName}.submit();
	
	//alert(":::::: 5");
}


//razman add untuk call skrin di bicara online
function funcBicaraInteraktif() {
	
	//alert('x');
	var id_perbicaraan = document.getElementById("idPerbicaraan").value;
	//alert("id_perbicaraan : "+id_perbicaraan);
	document.${formName}.action = "$EkptgUtil.getTabID("Bicara Interaktif",$portal_role)?_portal_module=ekptg.view.ppk.BicaraInteraktif&command=viewPerbicaraanFromPerintah&ID_PERBICARAAN="+id_perbicaraan;
	document.${formName}.method="POST";
	document.${formName}.submit();
	
}



function papar_header(idPermohonan,idStatus,bil,idPermohonanSimati,tarikhMohon,jenisfail,seksyen,idSimati) {

	//keputusan rayuan
	if (idStatus == '164' || idStatus == '165' || idStatus == '166' || idStatus == '167' || idStatus == '180' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&command=semakKeputusanRayuan";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaanSek17&command=semakKeputusanRayuan";
		}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
	} else 
	//permohonan rayuan
	if (idStatus == '64' || idStatus == '163' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=semakRayuan";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=semakRayuan";
		}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
	} else
		
	//perintah
	if (idStatus == '21' || idStatus == '25'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
		}
			document.${formName}.idPermohonanSimati.value = idPermohonanSimati;
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idStatus.value = idStatus;
		
	} else 
	//keputusan perbicaraan (selesai perbicaraan)
	if (idStatus == '41' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;
	} else
	//keputusan perbicaraan (tangguh perbicaraan)		
	if (idStatus == '44' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_tangguh";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_tangguh";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;

	} else
	//keputusan perbicaraan (tangguh MT)		
	if (idStatus == '174' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanMT";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanMT";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;

	} else
	//keputusan perbicaraan (tangguh ROTS)		
	if (idStatus == '176' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanROTS";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanROTS";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;
			
	} else
	//keputusan perbicaraan (tangguh kolateral)		
	if (idStatus == '172' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_kolateral";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_kolateral";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;			
					
	} else
	//keputusan perbicaraan (batal perbicaraan)		
	if (idStatus == '47' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_batal";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_batal";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;		
	
	} else 
	//notis perbicaraan
	if ((idStatus == '18' || idStatus == '44' || idStatus == '175' || idStatus == '173' || idStatus == '177' )){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&tabId=0";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData&tabId=0";
		}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
	
	} else 
	//keputusan permohonan
	if ((idStatus == '50' || idStatus == '53' || idStatus == '151' || idStatus == '152' || idStatus == '14'  || idStatus == '70'  )){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
		}
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idpermohonansimati.value = idPermohonanSimati;
			document.${formName}.tarikh_mohon.value = tarikhMohon;
			document.${formName}.idSimati.value = idSimati;
		
	} 
	
	else 
	//pendaftaran
	if ((idStatus == '8' || idStatus == '9'  || idStatus == '169' ) && (jenisfail == '1' || jenisfail == '2' )){
		
		
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
		}
		   
			document.${formName}.idpermohonan.value = idPermohonan;
			
			document.${formName}.idPermohonan.value = idPermohonan;
		
			document.${formName}.command.value = "papar";
			
			document.${formName}.idSimati.value = idSimati;
			
			//document.${formName}.mode.value = "Simatiview";
	}
	if (bil == '1' && (jenisfail == '3' )){		
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8InternalKutipan&command=papar";
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.command.value = "papar";
		document.${formName}.idSimati.value = idSimati;
		//document.${formName}.mode.value = "Simatiview";
	}
	
	//document.${formName}.flagForView.value = "yes";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

//gotoEdit(
</script>
#end