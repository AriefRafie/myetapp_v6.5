
<style>


@media all {
	.page-break	{ display: none; }
}

@media print {
	
	.page-break	{ display: block; page-break-before: always;}

}

.classFade{
    
    animation: fadein 2s;
    -moz-animation: fadein 2s; /* Firefox */
    -webkit-animation: fadein 2s; /* Safari and Chrome */
    -o-animation: fadein 2s; /* Opera */
}

.classFade1{
    
    animation: fadein 2s;
    -moz-animation: fadein 2s; /* Firefox */
    -webkit-animation: fadein 2s; /* Safari and Chrome */
    -o-animation: fadein 2s; /* Opera */
}

.classFade2{
    
    animation: fadein 0s;
    -moz-animation: fadein 0s; /* Firefox */
    -webkit-animation: fadein 0s; /* Safari and Chrome */
    -o-animation: fadein 0s; /* Opera */
}



@keyframes fadein {
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-moz-keyframes fadein { /* Firefox */
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-webkit-keyframes fadein { /* Safari and Chrome */
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-o-keyframes fadein { /* Opera */
    from {
        opacity:0;
    }
    to {
        opacity: 1;
    }
}



.underline_td_tajuk {
	border-bottom: 1px solid #000;
    padding: 3px 1px;    
}


.underline_td_main {
	border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 120%;
    color : white;
    text-shadow: 2px 2px 5px black;
}
.underline_td_sub {
	border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 100%;
    color : white;
    text-shadow: 2px 2px 5px black;
}

  
.blink {
  animation: blink-animation 1s steps(5, start) infinite;
  -webkit-animation: blink-animation 1s steps(5, start) infinite;
}
@keyframes blink-animation {
  to {
    visibility: hidden;
  }
}
@-webkit-keyframes blink-animation {
  to {
    visibility: hidden;
  }
}
</style>

<div id="div_carian" >
#parse("app/admin/Spatial/CarianUmumSpatial.jsp")

</div>

<br >

<div id="div_senaraiUtama" >
#parse("app/admin/Spatial/SenaraiUtama2.jsp")
</div>


<div id="SenaraiForPrint">	
</div>


<script>

function setDivTop(div_id)
{
	$jquery('#'+div_id).addClass('fixed');	
}


function getPageLocation(val)
{
	var tempScrollTop = $jquery(window).scrollTop();
	//alert("tempScrollTop :"+tempScrollTop);
	$jquery('#scrolPosition'+val).val(tempScrollTop);
	//document.getElementById("scrolPosition").value = tempScrollTop;
	return tempScrollTop;
}
function getPageLocationById(val)
{
	var tempScrollTop = $jquery(window).scrollTop();
	//alert("tempScrollTop :"+tempScrollTop);
	$jquery('#'+val).val(tempScrollTop);
	//document.getElementById("scrolPosition").value = tempScrollTop;
	return tempScrollTop;
}
function setPageLocation(val)
{
$jquery(window).scrollTop(val);
}

function validateCarian()
{
	   var bool_check = true;
	   
	   if(document.getElementById("carianTerperinci").value=="")
	   {
		   alert("Masukkan maklumat carian!");
		   document.getElementById("carianTerperinci").focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
}

function sendValueJenisPejabat(elem, value, nextField)
{
	//alert(elem + value + nextField);
	document.getElementById(nextField).value = value;
}

function removeSpaces(elem, valuekey) {//remove special char & space
	 elem.value = valuekey.replace(/[^A-Z0-9]+/i, '');
}

function valEditPejabat(ID_PEJABAT)
{
		var bool_check = true;
		
		if(document.getElementById("ID_JENISPEJABAT_"+ID_PEJABAT).value=="")
		{
		alert("Sila Pilih Jenis Pejabat!");
		document.getElementById("ID_JENISPEJABAT_"+ID_PEJABAT).focus();
		bool_check = false;
		}
		else if(document.getElementById("NAMA_PEJABAT_"+ID_PEJABAT).value=="")
		{
		alert("Sila Masukkan Nama Pejabat!");
		document.getElementById("NAMA_PEJABAT_"+ID_PEJABAT).focus();
		bool_check = false;
		}
		else if(document.getElementById("ID_SEKSYEN_"+ID_PEJABAT).value=="")
		{
		alert("Sila Pilih Bahagian Pejabat!");
		document.getElementById("ID_SEKSYEN_"+ID_PEJABAT).focus();
		bool_check = false;
		}			   
		else if(document.getElementById("ALAMAT1_"+ID_PEJABAT).value=="")
		{
		alert("Sila Masukkan Alamat Pejabat!");
		document.getElementById("ALAMAT1_"+ID_PEJABAT).focus();
		bool_check = false;				   
		}
		else if(document.getElementById("POSKOD_"+ID_PEJABAT).value=="")
		{
		alert("Masukkan Poskod Pejabat!");
		document.getElementById("POSKOD_"+ID_PEJABAT).focus();
		bool_check = false;
		}
		else if(document.getElementById("POSKOD_"+ID_PEJABAT).value.length != document.getElementById("POSKOD_"+ID_PEJABAT).maxLength)
		{
		alert("Format Poskod Tidak Tepat!");
		document.getElementById("POSKOD_"+ID_PEJABAT).focus();
		bool_check = false;
		}
		else if(document.getElementById("ID_NEGERI_"+ID_PEJABAT).value=="")
		{
		alert("Masukkan Negeri Pejabat!");
		document.getElementById("ID_NEGERI_"+ID_PEJABAT).focus();
		bool_check = false;
		}
		else if(document.getElementById("ID_DAERAH_"+ID_PEJABAT).value=="")
		{
		alert("Masukkan Daerah Pejabat!");
		document.getElementById("ID_DAERAH_"+ID_PEJABAT).focus();
		bool_check = false;
		}
		else if(document.getElementById("ID_BANDAR_"+ID_PEJABAT).value=="")
		{
		alert("Masukkan Bandar Pejabat!");
		document.getElementById("ID_BANDAR_"+ID_PEJABAT).focus();
		bool_check = false;
		}
		else if(document.getElementById("NO_TEL_"+ID_PEJABAT).value=="")
		{
		alert("Masukkan Nombor Telefon Pejabat!");
		document.getElementById("NO_TEL_"+ID_PEJABAT).focus();
		bool_check = false;
		}
		else if(document.getElementById("FLAG_AKTIF_"+ID_PEJABAT).value=="")
		{
		alert("Sila Nyatakan Status Keaktifan Pejabat!");
		document.getElementById("FLAG_AKTIF_"+ID_PEJABAT).focus();
		bool_check = false;
		}
		return bool_check;
}


function checkKodJenisPjbt(KOD_JENISPEJABAT,ID_NEGERI){
	
	//alert('masuk check kod'+KOD_JENISPEJABAT);
	doDivAjaxCall$formname('div_CHECK_KOD_'+ID_NEGERI,'checkKodJenisPejabat','ID_NEGERI='+ID_NEGERI+'&KOD_DAERAH='+$jquery('#KOD_DAERAH'+ID_NEGERI).val());
	
	var kod = document.getElementById("CHECK_KOD_"+ID_NEGERI);
	//alert("kod "+ kod);//check sini
	if(kod.value=="null")
	{
	//alert("Sila Pilih Kod Lain.");
	document.getElementById("CHECK_KOD_"+ID_NEGERI).focus();
	document.getElementById("CHECK_KOD_"+ID_NEGERI).clear();
	}
}

function checkKodMukim(KOD_JENISPEJABAT,ID_DAERAH){
	
	//alert('masuk check kod'+KOD_JENISPEJABAT);
	doDivAjaxCall$formname('div_CHECK_KOD_'+ID_DAERAH,'checkKodMukim','ID_DAERAH='+ID_DAERAH+'&KOD_MUKIM='+$jquery('#KOD_MUKIM'+ID_DAERAH).val());
	
	var kod = document.getElementById("CHECK_KOD_"+ID_DAERAH);
	//alert("kod "+ kod);//check sini
	if(kod.value=="")
	{
	//alert("Sila Pilih Kod Lain.");
	document.getElementById("KOD_MUKIM_"+ID_DAERAH).focus();
	document.getElementById("CHECK_KOD_"+ID_DAERAH).clear();
	}
}

function checkKodUPI(KOD_UPI,ID_NEGERI){
	
	//alert('masuk check kod'+KOD_JENISPEJABAT);
	doDivAjaxCall$formname('div_CHECK_KODUPI_'+ID_NEGERI,'checkKodUPI','ID_NEGERI='+ID_NEGERI+'&KOD_DAERAH_UPI='+$jquery('#KOD_DAERAH_UPI'+ID_NEGERI).val());
	
	var kod = document.getElementById("CHECK_KODUPI_"+ID_NEGERI);
	//alert("kod "+ kod);//check sini
	if(kod.value=="null")
	{
	//alert("Sila Pilih Kod Lain.");
	document.getElementById("CHECK_KODUPI_"+ID_NEGERI).focus();
	document.getElementById("CHECK_KODUPI_"+ID_NEGERI).clear();
	}
}

function hideDiv(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function printHideDiv(divName) {
	
	//var Form_id_negeri = document.getElementById("Form_id_negeri").value;
 	//var originalContents = document.body.innerHTML;
	
    $jquery("#"+divName+" :button").hide();
		 
   	//alert("1");
	
    var elementHide =  document.getElementById(divName);
    if (typeof(elementHide) != 'undefined' && elementHide != null)
    {
    	//alert("2");
    	elementHide.style.display = "";
    }    
    var printContents = document.getElementById(divName).innerHTML;   
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()"><div class="page-break" >'+ printContents + '</div></html>');
    popupWin.document.close(); 

	//document.getElementById("Form_id_negeri").value = Form_id_negeri;
    //document.body.innerHTML = originalContents;
    return false;
	
}

function sendValueNegeri(elem, value, nextField)
{
	alert('test'+elem+value+nextField);
	document.getElementById(nextField).value = value;
}

function showCarianTerperinci(){
	doDivAjaxCall$formname('tableCT','showCT','');
}
</script>
