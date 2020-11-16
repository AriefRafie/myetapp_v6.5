
<link rel="stylesheet" type="text/css"  href="../bootstrap-wysihtml5-master/lib/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"  href="../bootstrap-wysihtml5-master/lib/css/prettify.css" />
<link rel="stylesheet" type="text/css"  href="../bootstrap-wysihtml5-master/src/bootstrap-wysihtml5.css" />


<style>


.onTT {display:none;}

.scrollYes {
	overflow-x:auto;
	height: 1000px;
}

#sddmheader
{
	margin: 0;
	padding: 0;
	z-index: 30
}

#sddmheader li
{
	margin: 0;
	padding: 0;
	list-style: none;
	float: left;
}

#sddmheader li a
{
	display: block;
	color: #FFF;
	text-align: center;
	text-decoration: none
}

#sddmheader li a:hover
{
	background: #E0F2F7;
}

#sddmheader div
{
	position: absolute;
	visibility: hidden;
	margin: 0;
	padding: 0;
	/*border: 1px solid #5970B2;*/
	z-index:99;
}

#sddmheader div a
{
	position: relative;
	display: block;
	margin: 0;
	padding: 2.5px 10px;
	width: auto;
	white-space: nowrap;
	text-align: left;
	text-decoration: none;
	background: #EAEBD8;
	color: #2875DE;
	font: 11px arial;
	z-index:2;
}

#sddmheader div a:hover
{
	background: #49A3FF;
	color: #FFFFFF;
}


.loading {
	position: fixed;
	z-index: 999;
	height: 2em;
	width: 2em;
	overflow: show;
	margin: auto;
	top: 0;
	left: 0%;
	bottom: 0;
	right: 0;
}

/* Transparent Overlay */
.loading:before {
  content: '';
  display: block;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0,0,0,0.3);
}

/* :not(:required) hides these rules from IE9 and below */
.loading:not(:required) {
  font: 0/0 a;
  color: transparent;
  text-shadow: none;
  background-color: transparent;
  border: 0;
}

.loading:not(:required):after {
  content: '';
  display: block;
  font-size: 10px;
  width: 1em;
  height: 1em;
  margin-top: -0.5em;
  -webkit-animation: spinner 1500ms infinite linear;
  -moz-animation: spinner 1500ms infinite linear;
  -ms-animation: spinner 1500ms infinite linear;
  -o-animation: spinner 1500ms infinite linear;
  animation: spinner 1500ms infinite linear;
  border-radius: 0.5em;
  -webkit-box-shadow: rgba(0, 0, 0, 0.75) 1.5em 0 0 0, rgba(0, 0, 0, 0.75) 1.1em 1.1em 0 0, rgba(0, 0, 0, 0.75) 0 1.5em 0 0, rgba(0, 0, 0, 0.75) -1.1em 1.1em 0 0, rgba(0, 0, 0, 0.5) -1.5em 0 0 0, rgba(0, 0, 0, 0.5) -1.1em -1.1em 0 0, rgba(0, 0, 0, 0.75) 0 -1.5em 0 0, rgba(0, 0, 0, 0.75) 1.1em -1.1em 0 0;
  box-shadow: rgba(0, 0, 0, 0.75) 1.5em 0 0 0, rgba(0, 0, 0, 0.75) 1.1em 1.1em 0 0, rgba(0, 0, 0, 0.75) 0 1.5em 0 0, rgba(0, 0, 0, 0.75) -1.1em 1.1em 0 0, rgba(0, 0, 0, 0.75) -1.5em 0 0 0, rgba(0, 0, 0, 0.75) -1.1em -1.1em 0 0, rgba(0, 0, 0, 0.75) 0 -1.5em 0 0, rgba(0, 0, 0, 0.75) 1.1em -1.1em 0 0;
}

/* Animation */

@-webkit-keyframes spinner {
  0% {
    -webkit-transform: rotate(0deg);
    -moz-transform: rotate(0deg);
    -ms-transform: rotate(0deg);
    -o-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  100% {
    -webkit-transform: rotate(360deg);
    -moz-transform: rotate(360deg);
    -ms-transform: rotate(360deg);
    -o-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}
@-moz-keyframes spinner {
  0% {
    -webkit-transform: rotate(0deg);
    -moz-transform: rotate(0deg);
    -ms-transform: rotate(0deg);
    -o-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  100% {
    -webkit-transform: rotate(360deg);
    -moz-transform: rotate(360deg);
    -ms-transform: rotate(360deg);
    -o-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}
@-o-keyframes spinner {
  0% {
    -webkit-transform: rotate(0deg);
    -moz-transform: rotate(0deg);
    -ms-transform: rotate(0deg);
    -o-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  100% {
    -webkit-transform: rotate(360deg);
    -moz-transform: rotate(360deg);
    -ms-transform: rotate(360deg);
    -o-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}
@keyframes spinner {
  0% {
    -webkit-transform: rotate(0deg);
    -moz-transform: rotate(0deg);
    -ms-transform: rotate(0deg);
    -o-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  100% {
    -webkit-transform: rotate(360deg);
    -moz-transform: rotate(360deg);
    -ms-transform: rotate(360deg);
    -o-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}

.wysihtml5-sandbox
{
	border: 1px solid #000 !important;
  	-moz-border-radius: 10px !important;
  	border-radius: 10px !important;
	padding:5px !important;
	width:99%	!important;
	z-index: 2 !important;
    box-shadow: inset 0px 6px 4px -5px black, inset 0px -6px 4px -5px black !important;


}

.divKeterangan {
    background-color: white;
	/*
	-moz-border-radius: 10px !important;
	border-radius: 10px !important;
	*/
	width:99%	!important;
	padding:5px !important;
}





.disabledpointer {
    pointer-events: none;
}


.disabledbutton {
    pointer-events: none;
    opacity: 0.6;
}

.hidebutton {
	display:none;
}

.textmarq {
 height: 15px;
 overflow: hidden;
 position: relative;
}
.textmarq h3  {
 /*font-size: 3em;*/
 /*color: limegreen;*/
 position: absolute;
 width: 100%;
 height: 100%;
 margin: 0;
 line-height: 15px;
 text-align: center;
 /* Starting position */
 -moz-transform:translateX(100%);
 -webkit-transform:translateX(100%);
 transform:translateX(100%);
 /* Apply animation to this element */
 -moz-animation: textmarq 15s linear infinite;
 -webkit-animation: textmarq 15s linear infinite;
 animation: textmarq 15s linear infinite;
}
/* Move it (define the animation) */
@-moz-keyframes textmarq {
 0%   { -moz-transform: translateX(100%); }
 100% { -moz-transform: translateX(-100%); }
}
@-webkit-keyframes textmarq {
 0%   { -webkit-transform: translateX(100%); }
 100% { -webkit-transform: translateX(-100%); }
}
@keyframes textmarq {
 0%   {
 -moz-transform: translateX(100%); /* Firefox bug fix */
 -webkit-transform: translateX(100%); /* Firefox bug fix */
 transform: translateX(100%);
 }
 100% {
 -moz-transform: translateX(-100%); /* Firefox bug fix */
 -webkit-transform: translateX(-100%); /* Firefox bug fix */
 transform: translateX(-100%);
 }
}
.HeaderFont{
    color : blue;
}

.blue{
    color : blue;
}
.red{
    color : red;
}
.tajukList {
	border-bottom: 1px solid #000;
	margin-bottom:3px;
    padding: 1px 1px;
}

.viewMaklumatTR {
	margin-bottom:3px;
    padding: 5px;
	box-shadow: 2px 2px 5px black;
}

.viewKeterangan {
	margin-bottom:3px;
    padding: 15px;
	box-shadow: 2px 2px 5px black;
}

.underline_below_form {
	border-top: 1px solid #000;
    padding: 3px 1px;
}
.div_history {
	margin:3px;
	box-shadow: 2px 2px 5px black;
}
.fullwidth_input {
     width: 100%;
     box-sizing: border-box;
     -webkit-box-sizing:border-box;
     -moz-box-sizing: border-box;
}
.underline_td_main {
	border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 100%;
    color : white;
    text-shadow: 2px 2px 5px black;
}
.underline_td_sub {
	border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 80%;
    color : white;
    text-shadow: 2px 2px 5px black;
}
.box_shadow {
	box-shadow: 2px 2px 5px black;
}
.classFade{

    animation: fadein 2s;
    -moz-animation: fadein 2s; /* Firefox */
    -webkit-animation: fadein 2s; /* Safari and Chrome */
    -o-animation: fadein 2s; /* Opera */
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


</style>

<!--
<div class="blink" ><font color="red" ><strong class="blink">PERHATIAN!</strong> MODUL INI DIDALAM TEMPOH PENGUJIAN, SILA ABAIKAN BUAT SEMENTARA WAKTU.</font></div>
-->

#set($showListTukarPegawai = "")
#if($id_jawatan_login != "4" && $id_jawatan_login != "5")
	#set($showListTukarPegawai = "style='display:none'")
#end

<!--
<textarea id="inputMinify"></textarea>
<textarea id="outputMinify"></textarea>

<div id="inputMinify">
Dibatalkan nama Nooridah Binti Jelani K/P : 680925-10-6248 dan Effendi Bin Jelani K/P : 740429-10-5651 sebagai PENTADBIR BERSAMA di atas hakmilik seperti berikut :-<br />
<br />
1) PM 1790 LOT 9249 Mukim Hulu Bernam,Hulu Selangor semua bahagian<br />
2) PM 1791 LOT 6906 Mukim Hulu Bernam,Hulu Selangor semua bahagian<br />
3) PM 3468 LOT 11010 Mukim Hulu bernam,Hulu Selangor semua bahagian<br />
<br />
dan dilantik PENTADBIR BERSAMA iaitu Effendi Bin Jelani K/P : 740429-10-5651 dan Ruzylawati Binti Hj Jelani K/P : 710523-10-5220.
</div>
-->

#if($fromDashboard == "Y")
<script>
//alert("1");
$jquery(document).ready(function () {
openCloseTukarPegawai();
});
//alert("2");
</script>
#end
<br />
<div $showListTukarPegawai >
<input type="hidden" name="flag_senarai_tukarpegawai" id="flag_senarai_tukarpegawai" value="close" />
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0"  class="box_shadow"  style="margin-bottom:5px;cursor:pointer;">
<tr class="table_header" >
<td width="2%" class="underline_td_main" onclick="openCloseTukarPegawai()">
</td>
<td width="58%" class="underline_td_main" onclick="openCloseTukarPegawai()">
<span id="icon_tukarpegawai" >> </span><strong>PERMOHONAN TUKAR PEGAWAI BICARA</strong> </td>
<td width="20%" class="underline_td_main" align="right" valign="top" >
</td>
<td width="20%" class="underline_td_main" align="right">
<input type="button" id="cmdKembaliSenaraiTukarPegawai" name="cmdKembaliSenaraiTukarPegawai" value="Senarai Tukar Pegawai" onClick="kembaliSenaraiTukarPegawai()" style="display:none;" >
</td>
</tr>
</table>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0"  >
<tr>
<td >

<div id="view_tukarpegawaiKPP">
</div>
<div id="listPermohonanTukarPegawai">
<script>
//$jquery(document).ready(function () {
	//doDivAjaxCall$formname('listPermohonanTukarPegawai','showPermohonanTukarPegawai','');
//});
</script>
</div>

</td>
</tr>
</table>
</div>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0"  class="box_shadow" >
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<strong>PERBICARAAN INTERAKTIF</strong> </td>
<td width="20%" class="underline_td_main" align="right" valign="top" >
</td>
<td width="20%" class="underline_td_main" align="right">
<input type="button" id="cmdKembaliSenarai" name="cmdKembaliSenarai" value="Senarai Perbicaraan" onClick="kembaliSenarai()" style="display:none;" >
</td>
</tr>
</table>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0"  >
<tr>
<td >
<div id="div_viewPerbicaraan">
</div>
<div id="listPerbicaraan">
<script>
$jquery(document).ready(function () {
doDivAjaxCall$formname('listPerbicaraan','showListPerbicaraan','');
});
</script>
</div>
</td>
</tr>
</table>



<script>



var refreshInterval_showtime = [];

//alert(trim(document.getElementById("inputMinify").innerHTML));
//alert(document.getElementById("inputMinify").value.replace( new RegExp( "\>[ ]+\<" , "g" ) , "><" ));
//alert("x : "+trim(document.getElementById("inputMinify")));

function trim(val,divID) {
	val = val.
    replace(/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
    replace(/[ ]{2,}/gi, " "). // replaces multiple spaces with one space
    replace(/\n +/, "\n"); // Removes spaces after newlines
	alert("elem : "+val);
   // return val;
   document.getElementById(divID).innerHTML = val;
}

function validateNumber(evt) {
    var e = evt || window.event;
    var key = e.keyCode || e.which;

    if (!e.shiftKey && !e.altKey && !e.ctrlKey &&
    // numbers
    key >= 48 && key <= 57 ||
    // Numeric keypad
    key >= 96 && key <= 105 ||
    // Backspace and Tab and Enter
    key == 8 || key == 9 || key == 13 ||
    // Home and End
    key == 35 || key == 36 ||
    // left and right arrows
    key == 37 || key == 39 ||
    // Del and Ins
    key == 46 || key == 45) {
        // input is VALID
    }
    else {
        // input is INVALID
        e.returnValue = false;
        if (e.preventDefault) e.preventDefault();
    }
}

function validateCurrency(e,elmnt,content) {
	//if it is character, then remove it..
	var keycode;
	if(window.event)
	{
		keycode = window.event.keyCode;
	}
	else if (e)
	{
		keycode = e.which;
	}
	else
	{
		return true;
	}

	if((keycode >= 37 && keycode <= 40) || (keycode == 9))
	{
		return false;
	}

	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}



function validateCurrencyBlur(e,elmnt,content)
{
	//alert('x');
	//alert(Math.round(RemoveNonNumeric(content), 2));
	//elmnt.value = Math.round(RemoveNonNumeric(content), 2);
	//return;
	if(content!="")
	{
		elmnt.value = +(Math.round(content + "e+"+2)  + "e-"+2);
	}
}

function validateAreaBlur(e,elmnt,content)
{
	//alert('x');
	//alert(Math.round(RemoveNonNumeric(content), 2));
	//elmnt.value = Math.round(RemoveNonNumeric(content), 2);
	//return;
	if(content!="")
	{
		elmnt.value = +(Math.round(content + "e+"+4)  + "e-"+4);
	}
}


function resetMukim(div,field)
{
	//alert(" div : "+div+" field : "+field);
	$jquery("#"+div).html("<select id='"+field+"' name='"+field+"' class='fullwidth_input' ><option value='' >SILA PILIH</option></select>");
}

function kembaliSenaraiTukarPegawai()
{
	$jquery("#view_tukarpegawaiKPP").html("");
	document.getElementById('listPermohonanTukarPegawai').style.display = "";
	document.getElementById('cmdKembaliSenaraiTukarPegawai').style.display = "none";
}

function kembaliSenarai()
{
	$jquery("#div_viewPerbicaraan").html("");
	document.getElementById('listPerbicaraan').style.display = "";
	document.getElementById('cmdKembaliSenarai').style.display = "none";
}


function checkSubKolateral(jenis_ob, id_ob){
	var c = 0;
	var element = document.getElementsByName("sub"+jenis_ob+"_ob");
	var jenis_ob_vs = "";
	if(jenis_ob=="Plantif")
	{
		jenis_ob_vs = "Defendan";
	}
	else if(jenis_ob=="Defendan")
	{
		jenis_ob_vs = "Plantif";
	}
	var element_vs = document.getElementsByName("sub"+jenis_ob_vs+"_ob");
	if(element.length > 1)
	{
		for (i = 0; i < element.length; i++)
		{
			if (element[i].checked == true)
			{
				//alert('check');
				element_vs[i].checked = false;
			}
		}
	}
	else
	{
		if (document.getElementById("sub"+jenis_ob+"_ob").checked == true)
		{
			element_vs.checked = false;
		}
	}
}





//$jquery("#tukarpegawai_multipleSenaraiPerbicaraan").html("")




/*

 <input type="text" id="listID_PERBICARAAN" name="listID_PERBICARAAN" value="$pr.ID_PERBICARAAN" />
               <input type="text" id="listID_UNITPSK$pr.ID_PERBICARAAN" name="listID_UNITPSK$pr.ID_PERBICARAAN" value="$pr.ID_UNITPSK" />
               <input type="text" id="listPEG_PENGENDALI$pr.ID_PERBICARAAN" name="listPEG_PENGENDALI$pr.ID_PERBICARAAN" value="$pr.PEG_PENGENDALI" />
               <input type="text" id="listTARIKH_BICARA$pr.ID_PERBICARAAN" name="listTARIKH_BICARA$pr.ID_PERBICARAAN" value="$pr.TARIKH_BICARA" />
               <input type="text" id="listMASA_BICARA$pr.ID_PERBICARAAN" name="listMASA_BICARA$pr.ID_PERBICARAAN" value="$pr.MASA_BICARA" />
               <input type="text" id="listBIL_BICARA$pr.ID_PERBICARAAN" name="listBIL_BICARA$pr.ID_PERBICARAAN" value="$pr.BIL_BICARA" />
               <input type="text" id="listNO_FAIL$pr.ID_PERBICARAAN" name="listNO_FAIL$pr.ID_PERBICARAAN" value="$pr.NO_FAIL" />
               <input type="text" id="listID_FAIL$pr.ID_PERBICARAAN" name="listID_FAIL$pr.ID_PERBICARAAN" value="$pr.ID_FAIL" />
               <input type="text" id="listID_PERMOHONANSIMATI$pr.ID_PERBICARAAN" name="listID_PERMOHONANSIMATI$pr.ID_PERBICARAAN" value="$pr.ID_PERMOHONANSIMATI" />
               <input type="text" id="listID_SIMATI$pr.ID_PERBICARAAN" name="listID_SIMATI$pr.ID_PERBICARAAN" value="$pr.ID_SIMATI" />
               <input type="text" id="listID_PERMOHONAN$pr.ID_PERBICARAAN" name="listID_PERMOHONAN$pr.ID_PERBICARAAN" value="$pr.ID_PERMOHONAN" />
               <input type="text" id="listID_STATUS$pr.ID_PERBICARAAN" name="listID_STATUS$pr.ID_PERBICARAAN" value="$pr.ID_STATUS" />
               <input type="text" id="listFLAG_JENIS_KEPUTUSAN$pr.ID_PERBICARAAN" name="listFLAG_JENIS_KEPUTUSAN$pr.ID_PERBICARAAN" value="$pr.FLAG_JENIS_KEPUTUSAN" />
               <input type="text" id="listTOTAL_PERINTAH$pr.ID_PERBICARAAN" name="listTOTAL_PERINTAH$pr.ID_PERBICARAAN" value="$pr.TOTAL_PERINTAH" />
               <input type="text" id="listMAX_BIL_BICARA$pr.ID_PERBICARAAN" name="listMAX_BIL_BICARA$pr.ID_PERBICARAAN" value="$pr.MAX_BIL_BICARA" />

*/


function checkShowHideCBK(id_tukarpegawai)
{
	var showCheck = "yes";
	var id_status = document.getElementById("listkSTATUS_TUKARPEGAWAI"+id_tukarpegawai).value;
	if(id_status != "1")
	{
		showCheck = "no";
	}
	return showCheck;
}



function checkShowHideCB(id_perbicaraan)
{
	var showCheck = "yes";

	var no_fail = document.getElementById("listNO_FAIL"+id_perbicaraan).value;
	var id_status = document.getElementById("listID_STATUS"+id_perbicaraan).value;
	var bil_bicara = document.getElementById("listBIL_BICARA"+id_perbicaraan).value;
	var flag_jenis_keputusan = document.getElementById("listFLAG_JENIS_KEPUTUSAN"+id_perbicaraan).value;
	var total_perintah = document.getElementById("listTOTAL_PERINTAH"+id_perbicaraan).value;
	var max_bicara = document.getElementById("listMAX_BIL_BICARA"+id_perbicaraan).value;
	var permohonanBaruTP = document.getElementById("listREKOD_TUKARPEGAWAI"+id_perbicaraan).value;



	var showCheck = "yes";
	if(parseInt(permohonanBaruTP) > 0 || id_status != "18" || flag_jenis_keputusan != "" || (parseInt(total_perintah) <= 1 && (parseInt(bil_bicara) < parseInt(max_bicara) && flag_jenis_keputusan != "0" && flag_jenis_keputusan != "1" && flag_jenis_keputusan != "2"))
	)
	{
		showCheck = "no";
	}

	return showCheck;
}



function settingShowHideCB(elem,checkALL,showCheck)
{
	var returnOne = 0;
	//alert("elem checked : "+elem.checked+" checkALL : "+checkALL+" showCheck : "+showCheck);
	if(checkALL == "Y" && showCheck == "yes")
	{
		elem.checked = true;
	}
	else if(checkALL == "N")
	{
		elem.checked = false;
	}

	if(elem.checked == true)
	{
		returnOne++;
	}

	return returnOne;
}

function checkTPMainCheckBox(elem)
{
	if(elem.checked == true)
	{
		checkTPCheckBox("Y");
	}
	else
	{
		checkTPCheckBox("N");
	}
}

function checkTPMainCheckBoxK(elem)
{
	if(elem.checked == true)
	{
		checkTPCheckBoxK("Y");
	}
	else
	{
		checkTPCheckBoxK("N");
	}
}

function checkTPCheckBox(checkALL){

	//var totalSeletected = document.getElementById("tukarpegawai_multipleJumlahPerbicaraan").value;
	//alert("totalSeletected : ");
	var totalSeletected = 0;
	var element = document.getElementsByName("listID_PERBICARAAN");
	if(element.length > 1)
	{
		for (i = 0; i < element.length; i++)
		{
			//alert("masuk");
			var id_perbicaraan = element[i].value;
			//alert("id_perbicaraan : "+id_perbicaraan);
			var showCheck = checkShowHideCB(id_perbicaraan);
			var elemCheckBox = document.getElementById("selectTP"+id_perbicaraan);
			totalSeletected += settingShowHideCB(elemCheckBox,checkALL,showCheck);
		}

	}
	else
	{
		element = document.getElementById("listID_PERBICARAAN");
		var id_perbicaraan = element.value;
		var showCheck = checkShowHideCB(id_perbicaraan);
		var elemCheckBox = document.getElementById("selectTP"+id_perbicaraan);
		totalSeletected += settingShowHideCB(elemCheckBox,checkALL,showCheck);
	}
	//alert('x');
	document.getElementById("tukarpegawai_multipleJumlahPerbicaraan").value = totalSeletected;
	document.getElementById("divtukarpegawai_multipleDisplayJumlahPerbicaraan").innerHTML = totalSeletected;

}


function checkTPCheckBoxK(checkALL){

	//var totalSeletected = document.getElementById("tukarpegawai_multipleJumlahPerbicaraan").value;
	//alert("totalSeletected : "+totalSeletected);
	var totalSeletected = 0;
	var size = 0;
	var element = document.getElementsByName("listkID_TUKARPEGAWAI");
	if(element.length > 1)
	{
		size = element.length;
		for (i = 0; i < element.length; i++)
		{
			//alert("masuk 1");
			var id_tukarpegawai = element[i].value;
			//alert("masuk 2");
			var showCheck = checkShowHideCBK(id_tukarpegawai);
			//alert("masuk 3");
			var elemCheckBox = document.getElementById("selectTPK"+id_tukarpegawai);
			//alert("masuk 4");
			totalSeletected += settingShowHideCB(elemCheckBox,checkALL,showCheck);
			//alert("masuk 5");
			//checkMaklumat("listkID_TUKARPEGAWAI");
			//alert("masuk 6");
		}

	}
	else
	{
		size = 1;
		//alert("1");
		element = document.getElementById("listkID_TUKARPEGAWAI");
		var id_tukarpegawai = element.value;
		var showCheck = checkShowHideCBK(id_tukarpegawai);
		var elemCheckBox = document.getElementById("selectTPK"+id_tukarpegawai);
		totalSeletected += settingShowHideCB(elemCheckBox,checkALL,showCheck);
		//alert("2");
		//checkMaklumat("listkID_TUKARPEGAWAI");
	}
	//alert("length : "+element.length);

	if(size > 0)
	{
		//alert("1.1");
		checkMaklumat("listkID_TUKARPEGAWAI");
		//alert("1.2");
	}

	document.getElementById("tukarpegawaiKPP_multipleJumlahPerbicaraan").value = totalSeletected;
	document.getElementById("divtukarpegawaiKPP_multipleDisplayJumlahPerbicaraan").innerHTML = totalSeletected;

}

function checkMaklumat(elemenName)
{
	  //<input type="hidden" id="listkID_PEGAWAIBARU$pr.ID_TUKARPEGAWAI" name="listkID_PEGAWAIBARU$pr.ID_TUKARPEGAWAI" value="$pr.ID_PEGAWAIBARU" />
      //<input type="hidden" id="listkID_NEGERIPEGAWAIBARU$pr.ID_TUKARPEGAWAI" name="listkID_NEGERIPEGAWAIBARU$pr.ID_TUKARPEGAWAI" value="$pr.ID_NEGERIPEGAWAIBARU" />

	var element = document.getElementsByName(elemenName);
	var id_negeri = "XXX";
	var id_pegawai = "XXX";
	var errorMesej = "";
	var bezaNegeri = 0;
	var bezaPegawai = 0;
	//var catatan_pemohon = "XXX";
	//var catatan_pemohon_div = "";
	//var catatan_pemohon_asal_div = document.getElementById("divViewDefaulttukarpegawaiKPP_multipleCATATAN_PEMOHON").innerHTML
	//var catatan_pemohon_asal = document.getElementById("divViewDefaulttukarpegawaiKPP_multipleCATATAN_PEMOHON").innerHTML;
	if(element.length > 1)
	{
		for (i = 0; i < element.length; i++)
		{
			var id_tukarpegawai = element[i].value;
			var elemCheckBox = document.getElementById("selectTPK"+id_tukarpegawai);
			if(elemCheckBox.checked == true)
			{


				var selected_negeri = document.getElementById("listkID_NEGERIPEGAWAIBARU"+id_tukarpegawai).value;
				if((id_negeri == "XXX") || (id_negeri != "XXX" && id_negeri != "" && id_negeri == selected_negeri))
				{
					id_negeri = selected_negeri;
				}
				else
				{
					//alert("Salah");
					bezaNegeri++;
					//errorMesej += "Terdapat perbezaan negeri perbicaraan pada permohonan tukar pegawai yang dipilih, pilihan negeri di tetapkan pada 'Sila Pilih' secara default.\n";
					id_negeri = "";
				}

				var selected_pegawai = document.getElementById("listkID_PEGAWAIBARU"+id_tukarpegawai).value;
				if((id_pegawai == "XXX") || (id_pegawai != "XXX" && id_pegawai != "" && id_pegawai == selected_pegawai))
				{
					id_pegawai = selected_pegawai;
				}
				else
				{
					bezaPegawai++;
					//errorMesej += "Terdapat perbezaan pegawai perbicaraan pada permohonan tukar pegawai yang dipilih, pilihan pegawai di tetapkan pada 'Sila Pilih' secara default.\n";
					id_pegawai = "";
				}

				/*
				var catatan_pemohon_current = document.getElementById("listkCATATAN_PEMOHON"+id_tukarpegawai).value;
				if((catatan_pemohon == "XXX") || (catatan_pemohon != "XXX" && catatan_pemohon != "" && catatan_pemohon_current == catatan_pemohon))
				{
					catatan_pemohon = catatan_pemohon_current;
					catatan_pemohon_div = catatan_pemohon;
				}
				else
				{
					//errorMesej = "Terdapat perbezaan pegawai perbicaraan pada permohonan tukar pegawai yang dipilih, pilihan pegawai di tetapkan pada 'Sila Pilih' secara default.\n";
					catatan_pemohon = "";
					catatan_pemohon_div = "-";
				}
				*/
				/*
				if(errorMesej!="")
				{
					alert(errorMesej);
				*/
			}
		}
	}
	else
	{
		element = document.getElementById(elemenName);
		var id_tukarpegawai = element.value;
		var elemCheckBox = document.getElementById("selectTPK"+id_tukarpegawai);
		if(elemCheckBox.checked == true)
		{
			//var errorMesej = "";
			var selected_negeri = document.getElementById("listkID_NEGERIPEGAWAIBARU"+id_tukarpegawai).value;
			if((id_negeri == "XXX") || (id_negeri != "XXX" && id_negeri != "" && id_negeri == selected_negeri))
			{
				id_negeri = selected_negeri;
			}
			else
			{
				//errorMesej = "Terdapat perbezaan negeri perbicaraan pada permohonan tukar pegawai yang dipilih, pilihan negeri di tetapkan pada 'Sila Pilih' secara default.\n";
				bezaNegeri++;
				id_negeri = "";
			}

			var selected_pegawai = document.getElementById("listkID_PEGAWAIBARU"+id_tukarpegawai).value;
			if((id_pegawai == "XXX") || (id_pegawai != "XXX" && id_pegawai != "" && id_pegawai == selected_pegawai))
			{
				id_pegawai = selected_pegawai;
			}
			else
			{
				//errorMesej = "Terdapat perbezaan pegawai perbicaraan pada permohonan tukar pegawai yang dipilih, pilihan pegawai di tetapkan pada 'Sila Pilih' secara default.\n";
				bezaPegawai++;
				id_pegawai = "";
			}
			/*
			var catatan_pemohon_current = document.getElementById("listkCATATAN_PEMOHON"+id_tukarpegawai).value;
			if((catatan_pemohon == "XXX") || (catatan_pemohon != "XXX" && catatan_pemohon != "" && catatan_pemohon_current == catatan_pemohon))
			{
				catatan_pemohon = catatan_pemohon_current;
				catatan_pemohon_div = catatan_pemohon;
			}
			else
			{
				catatan_pemohon = "";
				catatan_pemohon_div = "-";
			}
			*/

			/*
			if(errorMesej!="")
			{
				alert(errorMesej);
			}
			*/
		}
	}
	/*
	else
	{
		id_negeri = "";
		id_pegawai = "";
	}
	*/
	//alert(">>>>>>>>>>>>>>>>>>>>>>>> id_pegawai : "+id_pegawai+" id_negeri : "+id_negeri+" catatan_pemohon : "+catatan_pemohon);



	if(id_negeri == "XXX")
	{
		id_negeri = "";
	}
	if(id_pegawai == "XXX")
	{
		id_pegawai = "";
	}
	/*
	if(catatan_pemohon == "XXX")
	{
		catatan_pemohon = "";
		catatan_pemohon_div = "-";
	}
	*/
	document.getElementById("tukarpegawaiKPP_multipleID_NEGERIPEGAWAIBARU").value = id_negeri;
	document.getElementById("tukarpegawaiKPP_multipleID_PEGAWAIBARU").value = id_pegawai;
	//document.getElementById("tukarpegawaiKPP_multipleCATATAN_PEMOHON").value = catatan_pemohon;
	//document.getElementById("divViewDefaulttukarpegawaiKPP_multipleCATATAN_PEMOHON").innerHTML = catatan_pemohon_div;
	//divViewDefault
	//alert(bezaPegawai+" : "+bezaNegeri);
	if(bezaPegawai > 0)
	{
		errorMesej += "Terdapat perbezaan pegawai perbicaraan pada permohonan tukar pegawai yang dipilih, pilihan pegawai di tetapkan pada 'Sila Pilih' secara default.\n";
	}
	if(bezaNegeri > 0)
	{
		errorMesej += "Terdapat perbezaan negeri perbicaraan pada permohonan tukar pegawai yang dipilih, pilihan negeri di tetapkan pada 'Sila Pilih' secara default.\n";
	}

	if(errorMesej!="")
	{
		alert(errorMesej);
	}

	//
}

function showHideCheckBoxKPP(name){

	var flag_tukarpegawaiKPP_multiple = document.getElementById("flag_tukarpegawaiKPP_multiple").value;
	//alert("showHideCheckBoxKPP : "+name);
	var element = document.getElementsByName(name);
	//alert("B");
	if(element.length > 1)
	{
		//alert("C");
		for (i = 0; i < element.length; i++)
		{
			var id_tukarpegawai = element[i].value;
			var showCheck = checkShowHideCBK(id_tukarpegawai);

			if(flag_tukarpegawaiKPP_multiple == "open")
			{
				if(showCheck == "yes")
				{
					document.getElementById('divSelectAllTPK').style.display = "";
					document.getElementById('divselectTPK_'+id_tukarpegawai).style.display = "";
					document.getElementById('selectTPK'+id_tukarpegawai).disabled = false;
				}
				else
				{
					document.getElementById('selectTPK'+id_tukarpegawai).checked = false;
					document.getElementById('selectTPK'+id_tukarpegawai).disabled = true;
					document.getElementById('divSelectAllTPK').style.display = "none";
					document.getElementById('divselectTPK_'+id_tukarpegawai).style.display = "none";
				}
			}
			else
			{
				document.getElementById('selectTPK'+id_tukarpegawai).checked = false;
				document.getElementById('selectTPK'+id_tukarpegawai).disabled = true;
				document.getElementById('divSelectAllTPK').style.display = "none";
				document.getElementById('divselectTPK_'+id_tukarpegawai).style.display = "none";
			}
		}
	}
	else
	{
		//alert("xxxxxxxxxxxxxx");
		element = document.getElementById(name);
		var id_tukarpegawai = element.value;
		//alert("id_tukarpegawai : "+id_tukarpegawai);
		var showCheck = checkShowHideCBK(id_tukarpegawai);
		//alert("2 : ");

		if(flag_tukarpegawaiKPP_multiple == "open")
		{
			if(showCheck == "yes")
			{
				document.getElementById('divSelectAllTPK').style.display = "";
				document.getElementById('divselectTPK_'+id_tukarpegawai).style.display = "";
				document.getElementById('selectTPK'+id_tukarpegawai).disabled = false;
			}
			else
			{
				document.getElementById('selectTPK'+id_tukarpegawai).checked = false;
				document.getElementById('selectTPK'+id_tukarpegawai).disabled = true;
				document.getElementById('divSelectAllTPK').style.display = "none";
				document.getElementById('divselectTPK_'+id_tukarpegawai).style.display = "none";
			}
		}
		else
		{
			document.getElementById('selectTPK'+id_tukarpegawai).checked = false;
			document.getElementById('selectTPK'+id_tukarpegawai).disabled = true;
			document.getElementById('divSelectAllTPK').style.display = "none";
			document.getElementById('divselectTPK_'+id_tukarpegawai).style.display = "none";
		}
	}
}

function getValueFromDrop(dropdownid)
{
	//alert("1"+dropdownid);
	var skillsSelect = document.getElementById(dropdownid);
	//alert("1:::"+skillsSelect.type);
	var selectedText = ""
	if(skillsSelect.type == "select-one")
	{
		selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
	}
	//alert("2"+selectedText);
	//$jquery("#"+div_text).html(selectedText);
	return selectedText;
}

function showHideCheckBox(listIdPerbicaraan){
	var flag_tukarpegawai_multiple = document.getElementById("flag_tukarpegawai_multiple").value;

	var element = document.getElementsByName(listIdPerbicaraan);

	if(element.length > 1)
	{

		for (i = 0; i < element.length; i++)
		{
			var id_perbicaraan = element[i].value;
			var showCheck = checkShowHideCB(id_perbicaraan);

			if(flag_tukarpegawai_multiple == "open")
			{
				if(showCheck == "yes")
				{
					document.getElementById('divSelectAllTP').style.display = "";
					document.getElementById('divselectTP_'+id_perbicaraan).style.display = "";
					document.getElementById('selectTP'+id_perbicaraan).disabled = false;
				}
				else
				{
					document.getElementById('selectTP'+id_perbicaraan).checked = false;
					document.getElementById('selectTP'+id_perbicaraan).disabled = true;
					document.getElementById('divSelectAllTP').style.display = "none";
					document.getElementById('divselectTP_'+id_perbicaraan).style.display = "none";
				}
			}
			else
			{
				document.getElementById('selectTP'+id_perbicaraan).checked = false;
				document.getElementById('selectTP'+id_perbicaraan).disabled = true;
				document.getElementById('divSelectAllTP').style.display = "none";
				document.getElementById('divselectTP_'+id_perbicaraan).style.display = "none";
			}
		}
	}
	else
	{
		element = document.getElementById(listIdPerbicaraan);
		var id_perbicaraan = element.value;
		var showCheck = checkShowHideCB(id_perbicaraan);

		if(flag_tukarpegawai_multiple == "open")
		{
			if(showCheck == "yes")
			{
				document.getElementById('divSelectAllTP').style.display = "";
				document.getElementById('divselectTP_'+id_perbicaraan).style.display = "";
				document.getElementById('selectTP'+id_perbicaraan).disabled = false;
			}
			else
			{
				document.getElementById('selectTP'+id_perbicaraan).checked = false;
				document.getElementById('selectTP'+id_perbicaraan).disabled = true;
				document.getElementById('divSelectAllTP').style.display = "none";
				document.getElementById('divselectTP_'+id_perbicaraan).style.display = "none";
			}
		}
		else
		{
			document.getElementById('selectTP'+id_perbicaraan).checked = false;
			document.getElementById('selectTP'+id_perbicaraan).disabled = true;
			document.getElementById('divSelectAllTP').style.display = "none";
			document.getElementById('divselectTP_'+id_perbicaraan).style.display = "none";
		}
	}
}


function doCheckKehadiran(sub_check, main_check){
	var c = 0;
	var element = document.getElementsByName(sub_check);
	var element_all = document.getElementById(main_check);
	if(element.length > 1)
	{
		for (i = 0; i < element.length; i++)
		{
			if (element[i].checked == false)
			{
				c++;
			}
		}
	}
	else
	{
		if (document.getElementById(sub_check).checked == false)
		{
			c++;
		}
	}

	if(c>0)
	{
		element_all.checked = false;
	}
	else
	{
		element_all.checked = true;
	}

}
function doCheckAllKehadiran(main_check,sub_check)
{
	var element_all = document.getElementById(main_check);
	if(element_all.checked==true)
	{
		var checklist_checkbox = document.getElementsByName(sub_check);
		var checklist_length = checklist_checkbox.length;
		for (k = 0; k < checklist_length; k++) {
			checklist_checkbox[k].checked = true;
		}
	}
	else if(element_all.checked==false)
	{
		var checklist_checkbox = document.getElementsByName(sub_check);
		var checklist_length = checklist_checkbox.length;
		for (k = 0; k < checklist_length; k++) {
			checklist_checkbox[k].checked = false;
		}
	}
}

function getPageLocation()
{
	var tempScrollTop = $jquery(window).scrollTop();
	//alert('tempScrollTop : '+tempScrollTop);
	return tempScrollTop;
}

/*
function divToTop(id)
{
	$jquery('#'+id).show();
	alert('ID : '+id);

	$jquery('#'+id).animate({
   		scrollTop:  $jquery(this).offset().top
	}, 'fast');

	//$jquery('#'+id).scrollTop(0);
	alert('totop');
}
*/

$jquery.fn.scrollView = function () {
  return this.each(function () {
    $jquery('html, body').animate({
      scrollTop: $jquery(this).offset().top - 50
    }, 0);
  });
}

function setPageLocation(val)
{
	//alert('setPageLocation :'+val);
	$jquery(window).scrollTop(val);
	//$jquery('#view_keputusan').scrollView();
}
function setingTrDiv(fieldFlagName,divID)
{
	var current_divID = document.getElementById(fieldFlagName).value;
	if(current_divID!="")
	{
		if( $jquery("#"+current_divID).length)         // use this if you are using id to check
		{
			$jquery("#"+current_divID).html("");
		}
	}

	if(divID!="")
	{
		document.getElementById(fieldFlagName).value=divID;
	}
}

function hideElementByID(cl)
{
	$jquery("#"+cl).hide();
}

function offIconByClass(cl)
{
	$jquery("."+cl).hide();
}

function disableByClass(cl)
{
	$jquery("."+cl).addClass("disabledbutton");
}

function hideByClass(cl)
{
	$jquery("."+cl).addClass("hidebutton");
	$jquery("."+cl).removeClass(cl);
}

function showInfoWakil(div)
{
	$jquery("#"+div+" [class=showWakil]").show();
}

function disableInput(div)
{
	/*
	$jquery("#"+div+" :button").addClass("disabledbutton");
	$jquery("#"+div+" input[value=Cetak]").removeClass("disabledbutton");
	$jquery("#"+div+" :text").addClass("disabledbutton");
	$jquery("#"+div+" :checkbox").addClass("disabledbutton");
	*/
	//alert("XXXXXXXX : "+$jquery("#"+div+" :button"));

	$jquery("#"+div+" :button").hide();
	//$jquery("#"+div+" input[value=Kemaskini]").hide();
	$jquery("#"+div+" input[value=Cetak]").show();
	$jquery("#"+div+" input[value=Tutup]").show();
	$jquery("#"+div+" :text").hide();
	$jquery("#"+div+" :checkbox").hide();
}


function resetListPerbicaraan()
{
	document.getElementById("carianBicaraNO_FAIL").value = "";
	document.getElementById("carianBicaraNAMA_SIMATI").value = "";
	document.getElementById("carianBicaraPENGENALAN_SIMATI").value = "";
	document.getElementById("carianBicaraNAMA_PEMOHON").value = "";
	document.getElementById("carianBicaraPENGENALAN_PEMOHON").value = "";
	document.getElementById("carianBicaraID_NEGERIBICARA").value = "";
	//document.getElementById("carianBicaraID_PEGAWAIBICARA").value = "";
	document.getElementById("carianBicaraNAMA_PEGAWAIBICARA").value = "";
	document.getElementById("carianBicaraTARIKH_BICARAMULA").value = "";
	document.getElementById("carianBicaraTARIKH_BICARAAKHIR").value = "";
	document.getElementById("carianBicaraBIL_BICARA").value = "";
	document.getElementById("carianBicaraSTATUS_PERBICARAAN").value = "";
	document.getElementById("carianBicaraSTATUS").value = "";
	doDivAjaxCall$formname('listPerbicaraan','showListPerbicaraan','');
}

function resetListTukarPegawai()
{
	document.getElementById("carianTukarPegawaiNO_TUKARPEGAWAI").value = "";
	document.getElementById("carianTukarPegawaiNO_FAIL").value = "";
	document.getElementById("carianTukarPegawaiID_NEGERIPEGAWAIBARU").value = "";
	document.getElementById("carianTukarPegawaiID_NEGERIMHN").value = "";
	document.getElementById("carianTukarPegawaiNAMAPEGAWAIASAL").value = "";
	document.getElementById("carianTukarPegawaiNAMAPEGAWAIBARU").value = "";
	document.getElementById("carianTukarPegawaiSTATUS_TUKARPEGAWAI").value = "";
	document.getElementById("carianTukarPegawaiTARIKH_MOHONMULA").value = "";
	document.getElementById("carianTukarPegawaiTARIKH_MOHONAKHIR").value = "";
	document.getElementById("carianTukarPegawaiTARIKH_BICARAMULA").value = "";
	document.getElementById("carianTukarPegawaiTARIKH_BICARAAKHIR").value = "";

	doDivAjaxCall$formname('listPermohonanTukarPegawai','showPermohonanTukarPegawai','');
}


function openCloseBantahan(jenis,id_permohonansimati,id_perbicaraan,id_permohonan,id_pemohon)
{
	if(document.getElementById("flag_"+jenis).value == "close")
	{
		$jquery("#icon_"+jenis).html("< ");
		document.getElementById("flag_"+jenis).value = "open";
		$jquery(document).ready(function () {
		doDivAjaxCall$formname('view_'+jenis,'show_'+jenis,'skrinName='+jenis+'&ID_PERBICARAAN='+id_perbicaraan+'&ID_PERMOHONAN='+id_permohonan+'&ID_PERMOHONANSIMATI='+id_permohonansimati+'&ID_PEMOHON='+id_pemohon+'&scrolPosition='+getPageLocation()+'&div=view_'+jenis);
		});
	}
	else
	{
		$jquery("#icon_"+jenis).html("> ");
		document.getElementById("flag_"+jenis).value = "close";
		$jquery("#view_"+jenis).html("");
	}
}


function openCloseKehadiran(jenis,id_permohonansimati,id_perbicaraan,id_permohonan,id_pemohon)
{
	if(document.getElementById("flag_"+jenis).value == "close")
	{
		$jquery("#icon_"+jenis).html("< ");
		document.getElementById("flag_"+jenis).value = "open";
		$jquery(document).ready(function () {
		doDivAjaxCall$formname('view_'+jenis,'show_'+jenis,'ID_PERBICARAAN='+id_perbicaraan+'&ID_PERMOHONAN='+id_permohonan+'&ID_PERMOHONANSIMATI='+id_permohonansimati+'&ID_PEMOHON='+id_pemohon+'&scrolPosition='+getPageLocation()+'&div=view_'+jenis);
		});
	}
	else
	{
		$jquery("#icon_"+jenis).html("> ");
		document.getElementById("flag_"+jenis).value = "close";
		$jquery("#view_"+jenis).html("");
	}
}



function openCloseTukarPegawai()
{
	//alert('masuk');
	if(document.getElementById("flag_senarai_tukarpegawai").value == "close")
	{
		$jquery("#icon_tukarpegawai").html("< ");
		document.getElementById("flag_senarai_tukarpegawai").value = "open";
		doDivAjaxCall$formname('listPermohonanTukarPegawai','showPermohonanTukarPegawai','');
		//call ajax list
	}
	else
	{
		$jquery("#icon_tukarpegawai").html("> ");
		document.getElementById("flag_senarai_tukarpegawai").value = "close";
		$jquery("#listPermohonanTukarPegawai").html("");
		$jquery("#view_tukarpegawaiKPP").html("");
		document.getElementById('cmdKembaliSenaraiTukarPegawai').style.display = "none";

	}
}

//arief add tukar pegawai 2
function openCloseTukarPegawai2()
{
	//alert('masuk');
	if(document.getElementById("flag_senarai_tukarpegawai2").value == "close")
	{
		$jquery("#icon_tukarpegawai2").html("< ");
		document.getElementById("flag_senarai_tukarpegawai2").value = "open";
		doDivAjaxCall$formname('listPermohonanTukarPegawai2','showPermohonanTukarPegawai2','');
		//call ajax list
	}
	else
	{
		$jquery("#icon_tukarpegawai2").html("> ");
		document.getElementById("flag_senarai_tukarpegawai2").value = "close";
		$jquery("#listPermohonanTukarPegawai2").html("");
		$jquery("#view_tukarpegawaiKPP2").html("");
	}
}

/*

doDivAjaxCallFekptg_view_ppk_BicaraInteraktif('listPerbicaraan','cariListPerbicaraan','action=doChangeItemPerPage&scrolPosition='+getPageLocation()

*/


//htmlPageSetup += $jquery(document).ready(function (){";
//htmlPageSetup += " alert('ada1'); ";
//htmlPageSetup += " document.getElementById(\"itemsPerPagecariListPerbicaraan\").value = '100'; ";
//htmlPageSetup += " alert('ada2');";
//htmlPageSetup += " doDivAjaxCall"+formName+"('listPerbicaraan','cariListPerbicaraan','action=doChangeItemPerPage&scrolPosition='+getPageLocation()); ";
//htmlPageSetup += " alert('ada3');";
//htmlPageSetup += " });";
//htmlPageSetup += "

function openCloseMultipleTPfirst(ID_PERMOHONAN,ID_FAIL,ID_SIMATI,ID_PERMOHONANSIMATI,ID_PERBICARAAN,DIV,SKRINNAME,command)
{
	if(document.getElementById("flag_tukarpegawai_multiple").value == "close")
	{
		document.getElementById("itemsPerPage"+command).value = '100';
		doDivAjaxCall$formname('listPerbicaraan',command,'flagOpenTP=Y&action=specialFromList&scrolPosition='+getPageLocation());
	}
	else
	{
		$jquery("#icon_tukarpegawai_multiple").html(">> ");
		document.getElementById("flag_tukarpegawai_multiple").value = "close";
		$jquery("#view_tukarpegawai_multiple").html("");
		showHideCheckBox('listID_PERBICARAAN');
	}
}

function openCloseMultipleTPKfirst(DIV,SKRINNAME,command)
{
	//alert('1');
	if(document.getElementById("flag_tukarpegawaiKPP_multiple").value == "close")
	{
		//alert('2');
		document.getElementById("itemsPerPage"+command).value = '100';
		//alert('3');
		doDivAjaxCall$formname('listPermohonanTukarPegawai',command,'flagOpenTPK=Y&action=specialFromList&scrolPosition='+getPageLocation());
	}
	else
	{
		//alert('3');
		$jquery("#icon_tukarpegawaiKPP_multiple").html(">> ");
		document.getElementById("flag_tukarpegawaiKPP_multiple").value = "close";
		$jquery("#view_tukarpegawaiKPP_multiple").html("");
		showHideCheckBoxKPP('listkID_TUKARPEGAWAI');
	}
}

//arief add tukar pegawai 2
function openCloseMultipleTPSecond(ID_PERMOHONAN,ID_FAIL,ID_SIMATI,ID_PERMOHONANSIMATI,ID_PERBICARAAN,DIV,SKRINNAME,command)
{
	if(document.getElementById("flag_tukarpegawai2").value == "close")
	{
		document.getElementById("itemsPerPage"+command).value = '100';
		doDivAjaxCall$formname('listPerbicaraan',command,'flagOpenTP2=Y&action=specialFromList&scrolPosition='+getPageLocation());

	}
	else
	{
		$jquery("#icon_tukarpegawai2").html(">> ");
		document.getElementById("flag_tukarpegawai2").value = "close";
		$jquery("#view_tukarpegawai2").html("");
		showHideCheckBox('listID_PERBICARAAN2');
	}
}

//arief add tukar pegawai 2
function openCloseMultipleTPKSecond(DIV,SKRINNAME,command)
{
	//alert('1');
	if(document.getElementById("flag_tukarpegawaiKPP2").value == "close")
	{
		//alert('2');
		document.getElementById("itemsPerPage"+command).value = '100';
		//alert('3');
		doDivAjaxCall$formname('listPermohonanTukarPegawai2',command,'flagOpenTPK2=Y&action=specialFromList&scrolPosition='+getPageLocation());
	}
	else
	{
		//alert('3');
		$jquery("#icon_tukarpegawaiKPP2").html(">> ");
		document.getElementById("flag_tukarpegawaiKPP2").value = "close";
		$jquery("#view_tukarpegawaiKPP2").html("");
		showHideCheckBoxKPP('listkID_TUKARPEGAWAI2');
	}
}

function tutupSkrinPegawaiMultiple(skrinName)
{
	$jquery('#view_'+skrinName).html('');
	//alert("1"+skrinName);
	if(skrinName == "tukarpegawai_multiple")
	{
		$jquery("#icon_tukarpegawai_multiple").html(">> ");
		document.getElementById("flag_tukarpegawai_multiple").value = "close";
		$jquery("#view_tukarpegawai_multiple").html("");
		showHideCheckBox('listID_PERBICARAAN');
	}
	else if(skrinName == "tukarpegawaiKPP_multiple")
	{
		//alert("2");
		$jquery("#icon_tukarpegawaiKPP_multiple").html(">> ");
		//alert("3");
		document.getElementById("flag_tukarpegawaiKPP_multiple").value = "close";
		//alert("4");
		$jquery("#view_tukarpegawaiKPP_multiple").html("");
		showHideCheckBoxKPP('listkID_TUKARPEGAWAI');
	}
}

//arief add tukar pegawai 2
function tutupSkrinPegawaiMultiple2(skrinName)
{
	$jquery('#view_'+skrinName).html('');
	//alert("1"+skrinName);
	if(skrinName == "tukarpegawai2")
	{
		$jquery("#icon_tukarpegawai2").html(">> ");
		document.getElementById("flag_tukarpegawai2").value = "close";
		$jquery("#view_tukarpegawai2").html("");
		//showHideCheckBox('listID_PERBICARAAN');
	}
	else if(skrinName == "tukarpegawaiKPP_multiple")
	{
		//alert("2");
		$jquery("#icon_tukarpegawaiKPP_multiple").html(">> ");
		//alert("3");
		document.getElementById("flag_tukarpegawaiKPP_multiple").value = "close";
		//alert("4");
		$jquery("#view_tukarpegawaiKPP_multiple").html("");
		showHideCheckBoxKPP('listkID_TUKARPEGAWAI');
	}
}

function openCloseMultipleTP(ID_PERMOHONAN,ID_FAIL,ID_SIMATI,ID_PERMOHONANSIMATI,ID_PERBICARAAN,DIV,SKRINNAME)
{
	if(document.getElementById("flag_tukarpegawai_multiple").value == "close")
	{
		$jquery("#icon_tukarpegawai_multiple").html("<< ");
		document.getElementById("flag_tukarpegawai_multiple").value = "open";
		$jquery(document).ready(function () {
			doDivAjaxCall$formname(DIV,'openSkrinTukarPegawai_multiple','mode=edit&div=view_tukarpegawai_multiple&ID_PERMOHONAN='+ID_PERMOHONAN+'&FIELD_PK=ID_TUKARPEGAWAI&NAMA_TABLE=TBLPPKTUKARPEGAWAI&ID_FAIL='+ID_FAIL+'&ID_SIMATI='+ID_SIMATI+'&ID_PERMOHONANSIMATI='+ID_PERMOHONANSIMATI+'&ID_PERBICARAAN='+ID_PERBICARAAN+'&skrinName='+SKRINNAME);
		});

		showHideCheckBox('listID_PERBICARAAN');
	}
	else
	{
		$jquery("#icon_tukarpegawai_multiple").html(">> ");
		document.getElementById("flag_tukarpegawai_multiple").value = "close";
		$jquery("#view_tukarpegawai_multiple").html("");
		showHideCheckBox('listID_PERBICARAAN');
	}
}

//arief add tukar pegawai 2
function openCloseMultipleTP2(ID_PERMOHONAN,ID_FAIL,ID_SIMATI,ID_PERMOHONANSIMATI,ID_PERBICARAAN,DIV,SKRINNAME)
{
	if(document.getElementById("flag_tukarpegawai2").value == "close")
	{
		$jquery("#icon_tukarpegawai2").html("<< ");
		document.getElementById("flag_tukarpegawai2").value = "open";
		$jquery(document).ready(function () {
			doDivAjaxCall$formname(DIV,'openSkrinTukarPegawai2','mode=edit&div=view_tukarpegawai2&ID_PERMOHONAN='+ID_PERMOHONAN+'&FIELD_PK=ID_TUKARPEGAWAI&NAMA_TABLE=TBLPPKTUKARPEGAWAI&ID_FAIL='+ID_FAIL+'&ID_SIMATI='+ID_SIMATI+'&ID_PERMOHONANSIMATI='+ID_PERMOHONANSIMATI+'&ID_PERBICARAAN='+ID_PERBICARAAN+'&skrinName='+SKRINNAME);
		});
	}
	else
	{
		$jquery("#icon_tukarpegawai2").html(">> ");
		document.getElementById("flag_tukarpegawai2").value = "close";
		$jquery("#view_tukarpegawai2").html("");
	}
}

function openCloseMultipleTPK(ID_PERMOHONAN,ID_FAIL,ID_SIMATI,ID_PERMOHONANSIMATI,ID_PERBICARAAN,DIV,SKRINNAME)
{
	//alert('1');
	if(document.getElementById("flag_tukarpegawaiKPP_multiple").value == "close")
	{
		//alert('2');
		$jquery("#icon_tukarpegawaiKPP_multiple").html("<< ");
		//alert('2.1');
		document.getElementById("flag_tukarpegawaiKPP_multiple").value = "open";
		//alert('2.2');
		//showHideCheckBox('listkID_TUKARPEGAWAI');
		//alert('2.3');
		$jquery(document).ready(function () {
			doDivAjaxCall$formname(DIV,'openSkrinTukarPegawai_multiple','mode=edit&div=view_tukarpegawaiKPP_multiple&ID_PERMOHONAN='+ID_PERMOHONAN+'&FIELD_PK=ID_TUKARPEGAWAI&NAMA_TABLE=TBLPPKTUKARPEGAWAI&ID_FAIL='+ID_FAIL+'&ID_SIMATI='+ID_SIMATI+'&ID_PERMOHONANSIMATI='+ID_PERMOHONANSIMATI+'&ID_PERBICARAAN='+ID_PERBICARAAN+'&skrinName='+SKRINNAME);
		});
		//alert('2.3');
		showHideCheckBoxKPP('listkID_TUKARPEGAWAI');
		//alert('2.4');
	}
	else
	{
		//alert('3');
		$jquery("#icon_tukarpegawaiKPP_multiple").html(">> ");
		document.getElementById("flag_tukarpegawaiKPP_multiple").value = "close";
		$jquery("#view_tukarpegawaiKPP_multiple").html("");
		showHideCheckBoxKPP('listkID_TUKARPEGAWAI');
	}
}

//arief add tukar pegawai 2
function openCloseMultipleTPK2(ID_PERMOHONAN,ID_FAIL,ID_SIMATI,ID_PERMOHONANSIMATI,ID_PERBICARAAN,DIV,SKRINNAME)
{
	//alert('1');
	if(document.getElementById("flag_tukarpegawaiKPP2").value == "close")
	{
		//alert('2');
		$jquery("#icon_tukarpegawaiKPP2").html("<< ");
		//alert('2.1');
		document.getElementById("flag_tukarpegawaiKPP2").value = "open";
		//alert('2.2');
		//showHideCheckBox('listkID_TUKARPEGAWAI');
		//alert('2.3');
		$jquery(document).ready(function () {
			doDivAjaxCall$formname(DIV,'openSkrinTukarPegawai2','mode=edit&div=view_tukarpegawaiKPP2&ID_PERMOHONAN='+ID_PERMOHONAN+'&FIELD_PK=ID_TUKARPEGAWAI&NAMA_TABLE=TBLPPKTUKARPEGAWAI&ID_FAIL='+ID_FAIL+'&ID_SIMATI='+ID_SIMATI+'&ID_PERMOHONANSIMATI='+ID_PERMOHONANSIMATI+'&ID_PERBICARAAN='+ID_PERBICARAAN+'&skrinName='+SKRINNAME);
		});
		//alert('2.3');
		showHideCheckBoxKPP('listkID_TUKARPEGAWAI');
		//alert('2.4');
	}
	else
	{
		//alert('3');
		$jquery("#icon_tukarpegawaiKPP2").html(">> ");
		document.getElementById("flag_tukarpegawaiKPP2").value = "close";
		$jquery("#view_tukarpegawaiKPP2").html("");
		showHideCheckBoxKPP('listkID_TUKARPEGAWAI2');
	}
}

function openCloseSenarai(jenis,id_simati,id_perbicaraan,id_permohonan,nama_table,field_pk,id_permohonansimati,current_previous,tajukList,id_pemohon)
{
	if(document.getElementById("flag_senarai_"+jenis).value == "close")
	{
		$jquery("#icon_"+jenis).html("< ");
		document.getElementById("flag_senarai_"+jenis).value = "open";
		$jquery(document).ready(function () {
		doDivAjaxCall$formname('senarai_'+jenis+current_previous,'showSenarai','NAMA_TABLE='+nama_table+'&FIELD_PK='+field_pk+'&ID_PERBICARAAN='+id_perbicaraan+'&ID_SIMATI='+id_simati+'&ID_PERMOHONAN='+id_permohonan+'&ID_PERMOHONANSIMATI='+id_permohonansimati+'&skrinName='+jenis+'&current_previous='+current_previous+"&tajukList="+tajukList+"&ID_PEMOHON="+id_pemohon+'&div=senarai_'+jenis+current_previous+"&scrolPosition="+getPageLocation());
		});
	}
	else
	{
		$jquery("#icon_"+jenis).html("> ");
		document.getElementById("flag_senarai_"+jenis).value = "close";
		$jquery("#senarai_"+jenis+"current").html("");
		$jquery("#senarai_"+jenis+"history").html("");
		$jquery("#senarai_"+jenis+"previous").html("");
	}
}


function openClose(jenis,id_simati,id_perbicaraan,id_permohonan,nama_table,field_pk,id_permohonansimati)
{
	if(document.getElementById("flag_"+jenis).value == "close")
	{
		$jquery("#icon_"+jenis).html("< ");
		document.getElementById("flag_"+jenis).value = "open";
		$jquery(document).ready(function () {

		var cmd = "showMaklumat";
		if(jenis == "perubahan")
		{
			cmd = "showMaklumatperubahan"
		}
		else if(jenis == "historyJana")
		{
			cmd = "showMaklumatHistoryJana"
		}
		else if(jenis == "keteranganhadir")
		{
			cmd = "showMaklumatketeranganhadir"
		}
		else if(jenis == "keputusan")
		{
			cmd = "showKeputusan"
		}
		else if(jenis == "cetakan")
		{
			cmd = "showCetakan"
		}



		doDivAjaxCall$formname('view_'+jenis,cmd,'NAMA_TABLE='+nama_table+'&FIELD_PK='+field_pk+'&ID_SIMATI='+id_simati+'&ID_PERBICARAAN='+id_perbicaraan+'&ID_PERMOHONAN='+id_permohonan+'&ID_PERMOHONANSIMATI='+id_permohonansimati+'&skrinName='+jenis+'&scrolPosition='+getPageLocation()+'&div=view_'+jenis);
		});
	}
	else
	{
		$jquery("#icon_"+jenis).html("> ");
		document.getElementById("flag_"+jenis).value = "close";
		$jquery("#view_"+jenis).html("");
	}
}

function selectKeputusanPerintah(flag_keputusan,mode,skrinName,ID_SIMATI,ID_PERMOHONAN,ID_PERBICARAAN,ID_PERMOHONANSIMATI,command)
{
	var tajukKeputusan = "";
	if(flag_keputusan=='0')
	{
		tajukKeputusan = "SELESAI";
	}
	else if(flag_keputusan=='1')
	{
		tajukKeputusan = "TANGGUH";
	}
	else if(flag_keputusan=='2')
	{
		tajukKeputusan = "BATAL";
	}

	if(flag_keputusan == "1" || flag_keputusan == "2")
	{	/*
		document.getElementById('trMaklumatKiv').style.display = "none";
		document.getElementById(skrinName+'CHECK_KIV').value = "";
		document.getElementById(skrinName+'DATE_KIV').value = "";
		document.getElementById(skrinName+'CATATAN_KIV').value = "";
		*/
		$jquery("#label"+skrinName+"TARIKH_PERINTAH").html("Tarikh Perbicaraan Terakhir");
	}
	else if(flag_keputusan == "0")
	{
		//document.getElementById('trMaklumatKiv').style.display = "";
		$jquery("#label"+skrinName+"TARIKH_PERINTAH").html("Tarikh Perintah");
	}

	//alert("flag_keputusan : "+flag_keputusan);
	if(flag_keputusan!="")
	{
		document.getElementById('trShow'+skrinName).style.display = "";
		$jquery("#legend"+skrinName).html(tajukKeputusan);
		doDivAjaxCall$formname('divkeputusanPerintah','viewSuplimentPerintah','FLAG_KEPUTUSAN='+flag_keputusan+'&NAMA_TABLE=TBLPPKPERINTAH&FIELD_PK=ID_PERINTAH&mode='+mode+'&ID_SIMATI='+ID_SIMATI+'&ID_PERBICARAAN='+ID_PERBICARAAN+'&ID_PERMOHONAN='+ID_PERMOHONAN+'&ID_PERMOHONANSIMATI='+ID_PERMOHONANSIMATI+'&skrinName='+skrinName+'&scrolPosition='+getPageLocation());
	}
	else
	{
		document.getElementById('trShow'+skrinName).style.display = "none";
		$jquery("#divkeputusanPerintah").html("");
	}
	/*
	if(command=="showKeputusan")
	{
		$jquery('#view_keputusan').scrollView();
	}
	*/
}

function addClassToDiv(div,className) {
	//alert('div : '+div);
	$jquery("#"+div).addClass(className);
	//alert('2');
}

function removeClassFromDiv(div,className) {
	//alert('div : '+div);
	//$jquery("#"+div).addClass(className);
	$jquery("#"+div).removeClass(className);
	//alert('2');
}

function addreadonly(div) {
	//alert('div : '+div);
	//$jquery("#"+div).addClass(className);
	//alert('2');
	$jquery("#"+div).attr('readonly', 'readonly');
}

function removereadonly(div) {
	//alert('div : '+div);
	//$jquery("#"+div).addClass(className);
	//$jquery("#"+div).removeClass(className);
	//alert('2');
	$jquery("#"+div).removeAttr('readonly');
}


function addClassDisableReadOnly(div)
{
	$jquery("#"+div).addClass("disabled");
	$jquery("#"+div).addClass("disabledpointer");
	$jquery("#"+div).attr('readonly', 'readonly');
}
function removeClassDisableReadOnly(div)
{
	$jquery("#"+div).removeClass("disabled");
	$jquery("#"+div).removeClass("disabledpointer");
	$jquery("#"+div).removeAttr('readonly');
}

function setPejabatKoleteral(id_pejabatjkptg,skrinName,columnName)
{
	//if(id_pejabatjkptg!="")
	{
		doDivAjaxCall$formname('divSetupAlamatPejabat'+skrinName,'getAlamatJkptg','ID_PEJABATJKPTG='+id_pejabatjkptg+"&skrinName="+skrinName);
	}
}

function setPejabatMahkamah(id_pejabat,skrinName,columnName)
{
	//if(id_pejabatjkptg!="")
	{
		doDivAjaxCall$formname('divSetupAlamatPejabat'+skrinName,'getAlamatJkptg','ID_PEJABAT='+id_pejabatjkptg+"&skrinName="+skrinName);
	}
}


function pejabatAddReadonly(skrinName)
{
	addClassDisableReadOnly(skrinName+"NAMA_PEJABAT");
	addClassDisableReadOnly(skrinName+"ALAMAT1");
	addClassDisableReadOnly(skrinName+"ALAMAT2");
	addClassDisableReadOnly(skrinName+"ALAMAT3");
	addClassDisableReadOnly(skrinName+"POSKOD");
	addClassDisableReadOnly(skrinName+"ID_NEGERI");
	addClassDisableReadOnly(skrinName+"ID_BANDAR");
	addClassDisableReadOnly(skrinName+"NO_TEL");
	addClassDisableReadOnly(skrinName+"NO_FAX");
}
function pejabatRemoveReadonly(skrinName)
{
	removeClassDisableReadOnly(skrinName+"NAMA_PEJABAT");
	removeClassDisableReadOnly(skrinName+"ALAMAT1");
	removeClassDisableReadOnly(skrinName+"ALAMAT2");
	removeClassDisableReadOnly(skrinName+"ALAMAT3");
	removeClassDisableReadOnly(skrinName+"POSKOD");
	removeClassDisableReadOnly(skrinName+"ID_NEGERI");
	removeClassDisableReadOnly(skrinName+"ID_BANDAR");
	removeClassDisableReadOnly(skrinName+"NO_TEL");
	removeClassDisableReadOnly(skrinName+"NO_FAX");
}


function selectJenisRujukan(jenis_rujukan,skrinName,mode)
{
	//alert('jenis_rujukan : '+jenis_rujukan);
	if(jenis_rujukan=="1")
	{

		$jquery("#tdTajukMahkamahTangguh").html("Mahkamah Tinggi");
		$jquery("#label"+skrinName+"ID_MAHKAMAH").html("Mahkamah Tinggi");
		document.getElementById('row'+skrinName+'FLAG_RUJUKAN').style.display = "none";
		document.getElementById(skrinName+'FLAG_RUJUKAN').value = "";
		document.getElementById('row'+skrinName+'ID_NEGERIMAHKAMAH').style.display = "";
		//document.getElementById(skrinName+'ID_NEGERIMAHKAMAH').value = "";
		document.getElementById('row'+skrinName+'ID_MAHKAMAH').style.display = "";
		//document.getElementById(skrinName+'ID_MAHKAMAH').value = "";

		pejabatAddReadonly(skrinName);
		document.getElementById('trTajukMahkamahTangguh').style.display = "";
		document.getElementById('row'+skrinName+'NAMA_PEJABAT').style.display = "";
		document.getElementById('row'+skrinName+'ALAMAT1').style.display = "";
		document.getElementById('row'+skrinName+'ALAMAT2').style.display = "";
		document.getElementById('row'+skrinName+'ALAMAT3').style.display = "";
		document.getElementById('row'+skrinName+'POSKOD').style.display = "";
		document.getElementById('row'+skrinName+'ID_NEGERI').style.display = "";
		document.getElementById('row'+skrinName+'ID_BANDAR').style.display = "";
		document.getElementById('row'+skrinName+'NO_TEL').style.display = "";
		document.getElementById('row'+skrinName+'NO_FAX').style.display = "";

		//alert('2');

	}
	else if(jenis_rujukan=="2")
	{
		document.getElementById('row'+skrinName+'FLAG_RUJUKAN').style.display = "";
		pejabatAddReadonly(skrinName);
		document.getElementById('trTajukMahkamahTangguh').style.display = "";
		document.getElementById('row'+skrinName+'NAMA_PEJABAT').style.display = "";
		document.getElementById('row'+skrinName+'ALAMAT1').style.display = "";
		document.getElementById('row'+skrinName+'ALAMAT2').style.display = "";
		document.getElementById('row'+skrinName+'ALAMAT3').style.display = "";
		document.getElementById('row'+skrinName+'POSKOD').style.display = "";
		document.getElementById('row'+skrinName+'ID_NEGERI').style.display = "";
		document.getElementById('row'+skrinName+'ID_BANDAR').style.display = "";
		document.getElementById('row'+skrinName+'NO_TEL').style.display = "";
		document.getElementById('row'+skrinName+'NO_FAX').style.display = "";
	}
	else
	{
		document.getElementById('row'+skrinName+'FLAG_RUJUKAN').style.display = "none";
		document.getElementById(skrinName+'FLAG_RUJUKAN').value = "";
		document.getElementById('row'+skrinName+'ID_NEGERIMAHKAMAH').style.display = "none";
		document.getElementById(skrinName+'ID_NEGERIMAHKAMAH').value = "";
		document.getElementById('row'+skrinName+'ID_MAHKAMAH').style.display = "none";
		document.getElementById(skrinName+'ID_MAHKAMAH').value = "";

		pejabatAddReadonly(skrinName);
		document.getElementById('trTajukMahkamahTangguh').style.display = "none";
		document.getElementById('row'+skrinName+'NAMA_PEJABAT').style.display = "none";
		document.getElementById(skrinName+'NAMA_PEJABAT').value = "";
		document.getElementById('row'+skrinName+'ALAMAT1').style.display = "none";
		document.getElementById(skrinName+'ALAMAT1').value = "";
		document.getElementById('row'+skrinName+'ALAMAT2').style.display = "none";
		document.getElementById(skrinName+'ALAMAT2').value = "";
		document.getElementById('row'+skrinName+'ALAMAT3').style.display = "none";
		document.getElementById(skrinName+'ALAMAT3').value = "";
		document.getElementById('row'+skrinName+'ID_NEGERI').style.display = "none";
		document.getElementById(skrinName+'ID_NEGERI').value = "";
		document.getElementById('row'+skrinName+'POSKOD').style.display = "none";
		document.getElementById(skrinName+'POSKOD').value = "";
		document.getElementById('row'+skrinName+'ID_BANDAR').style.display = "none";
		document.getElementById(skrinName+'ID_BANDAR').value = "";
		document.getElementById('row'+skrinName+'NO_TEL').style.display = "none";
		document.getElementById(skrinName+'NO_TEL').value = "";
		document.getElementById('row'+skrinName+'NO_FAX').style.display = "none";
		document.getElementById(skrinName+'NO_FAX').value = "";
	}
}

function selectJenisBatal(flag_batal,mode,skrinName,ID_SIMATI,ID_PERMOHONAN,ID_PERBICARAAN,ID_PERMOHONANSIMATI,command)
{

	if(flag_batal == "1") //wasiat
	{
		var nama_table = "TBLPPKPERINTAH";
		var field_pk = "ID_PERINTAH";

		doDivAjaxCall$formname('divSkrinSuplimentTangguhBatal','viewSuplimentBatal','FLAG_BATAL='+flag_batal+'&NAMA_TABLE='+nama_table+'&FIELD_PK='+field_pk+'&mode='+mode+'&ID_SIMATI='+ID_SIMATI+'&ID_PERBICARAAN='+ID_PERBICARAAN+'&ID_PERMOHONAN='+ID_PERMOHONAN+'&ID_PERMOHONANSIMATI='+ID_PERMOHONANSIMATI+'&skrinName='+skrinName+'&scrolPosition='+getPageLocation());
	}
	else
	{
		$jquery("#divSkrinSuplimentTangguhBatal").html("");
	}

}

function selectJenisTangguh(flag_tangguh,mode,skrinName,ID_SIMATI,ID_PERMOHONAN,ID_PERBICARAAN,ID_PERMOHONANSIMATI,command)
{	//divSkrinSuplimentTangguh
	//alert("flag_tangguh : "+flag_tangguh);
	document.getElementById('row'+skrinName+'TEMPOH_TUNGGU_FARID').style.display = "none";
	//document.getElementById('row'+skrinName+'SEBAB_TANGGUH').style.display = "none";
	if(flag_tangguh == "6")//koleterel
	{
		document.getElementById(skrinName+'TEMPOH_TUNGGU_FARID').value = "";
	}
	else if(flag_tangguh == "5")//ros
	{
		document.getElementById(skrinName+'TEMPOH_TUNGGU_FARID').value = "";
	}
	else if(flag_tangguh == "7")//sijil faraid
	{
		document.getElementById('row'+skrinName+'TEMPOH_TUNGGU_FARID').style.display = "";
	}
	else
	{
		document.getElementById(skrinName+'TEMPOH_TUNGGU_FARID').value = "";
	}

	//default kena bukak
	document.getElementById('trinfo'+skrinName+'KEPUTUSAN_MAHKAMAH').style.display = "";
	document.getElementById('row'+skrinName+'KEPUTUSAN_MAHKAMAH').style.display = "";
	document.getElementById('trword'+skrinName+'KEPUTUSAN_MAHKAMAH').style.display = "";

	document.getElementById('trinfo'+skrinName+'SEBAB_TANGGUH').style.display = "";
	document.getElementById('row'+skrinName+'SEBAB_TANGGUH').style.display = "";
	document.getElementById('trword'+skrinName+'SEBAB_TANGGUH').style.display = "";



	if(flag_tangguh == "6" || flag_tangguh == "5")
	{
		//alert("flag_tangguh : "+flag_tangguh);
		var nama_table = "";
		var field_pk = "";
		if(flag_tangguh == "5")
		{
			nama_table = "TBLPPKBORANGJ";
			field_pk = "ID_BORANGJ";
		}
		else if(flag_tangguh == "6")
		{
			nama_table = "TBLPPKKOLATERALMST";
			field_pk = "ID_KOLATERALMST";
		}


	doDivAjaxCall$formname('divSkrinSuplimentTangguhBatal','viewSuplimentTangguh','FLAG_TANGGUH='+flag_tangguh+'&NAMA_TABLE='+nama_table+'&FIELD_PK='+field_pk+'&mode='+mode+'&ID_SIMATI='+ID_SIMATI+'&ID_PERBICARAAN='+ID_PERBICARAAN+'&ID_PERMOHONAN='+ID_PERMOHONAN+'&ID_PERMOHONANSIMATI='+ID_PERMOHONANSIMATI+'&skrinName='+skrinName+'&scrolPosition='+getPageLocation());
	//document.getElementById('row'+skrinName+'SEBAB_TANGGUH').style.display = "none";
	//document.getElementById('trword'+skrinName+'SEBAB_TANGGUH').style.display = "none";
	//document.getElementById(skrinName+'SEBAB_TANGGUH').value = "";
	//document.getElementById('row'+skrinName+'KEPUTUSAN_MAHKAMAH').style.display = "none";
	//document.getElementById('trword'+skrinName+'KEPUTUSAN_MAHKAMAH').style.display = "none";
	//document.getElementById(skrinName+'KEPUTUSAN_MAHKAMAH').value = "";
	}
	else
	{
		$jquery("#divSkrinSuplimentTangguhBatal").html("");
		$jquery("#divSkrinSuplimentTangguhBatal_ListPB").html("");
		//document.getElementById('trword'+skrinName+'SEBAB_TANGGUH').style.display = "";
		//document.getElementById('row'+skrinName+'SEBAB_TANGGUH').style.display = "";
		//document.getElementById('trword'+skrinName+'KEPUTUSAN_MAHKAMAH').style.display = "";
		//document.getElementById('row'+skrinName+'KEPUTUSAN_MAHKAMAH').style.display = "";
	}
}

function salinAlamatPemohon(skrinName,ID_PERMOHONAN)
{
	if(document.getElementById("salinAlamatPemohon"+skrinName).checked == true)
	{
		doDivAjaxCall$formname('div'+skrinName+'ALAMAT_1','salinAlamatPemohon','ID_PERMOHONAN='+ID_PERMOHONAN+"&skrinName="+skrinName);
	}
}

function salinAlamatTetap(skrinName)
{
	//alert('skrinName : '+skrinName);
	//alert('CHECK : '+document.getElementById("salinAlamat"+skrinName).checked);
	if(document.getElementById("salinAlamat"+skrinName).checked == true)
	{
		//alert('masuk');
		var id_negeri = document.getElementById(skrinName+"ID_NEGERI").value;
		//alert('id_negeri : '+id_negeri);
		var id_bandar = document.getElementById(skrinName+"ID_BANDAR").value;
		//alert("id_negeri : "+id_negeri+" id_bandar : "+id_bandar);
		document.getElementById(skrinName+"ALAMAT1_SURAT").value = document.getElementById(skrinName+"ALAMAT_1").value;
		document.getElementById(skrinName+"ALAMAT2_SURAT").value = document.getElementById(skrinName+"ALAMAT_2").value;
		document.getElementById(skrinName+"ALAMAT3_SURAT").value = document.getElementById(skrinName+"ALAMAT_3").value;
		document.getElementById(skrinName+"POSKOD_SURAT").value = document.getElementById(skrinName+"POSKOD").value;
		document.getElementById(skrinName+"ID_NEGERISURAT").value = document.getElementById(skrinName+"ID_NEGERI").value;
		document.getElementById(skrinName+"NAMA_PELBAGAINEGARA_SURAT").value = document.getElementById(skrinName+"NAMA_PELBAGAINEGARA").value;
		doDivAjaxCall$formname('div'+skrinName+'ID_NEGERISURAT','showNegeriSurat','ID_NEGERISALIN='+id_negeri+'&ID_BANDARSALIN='+id_bandar+"&skrinName="+skrinName);
	}
}

function setMapPejabat(skrinName,nama,alamat1, alamat2, alamat3, poskod, id_negeri, id_bandar, no_tel)
{
		//alert(' skrinName ::::::::::::: '+skrinName);
		if(skrinName=="pemohon")
		{
			document.getElementById(skrinName+"NAMA_PEMOHON").value = nama;
		}
		else if(skrinName=="ob")
		{
			document.getElementById(skrinName+"NAMA_OB").value = nama;
		}
		document.getElementById(skrinName+"ALAMAT_1").value = alamat1;
		document.getElementById(skrinName+"ALAMAT_2").value = alamat2;
		document.getElementById(skrinName+"ALAMAT_3").value = alamat3;
		document.getElementById(skrinName+"POSKOD").value = poskod;
		document.getElementById(skrinName+"ID_NEGERI").value = id_negeri;
		document.getElementById(skrinName+"NO_TEL_SURAT").value = no_tel;
		//alert('2');
		doDivAjaxCall$formname('div'+skrinName+'ID_NEGERI','showNegeri','ID_NEGERISALIN='+id_negeri+'&ID_BANDARSALIN='+id_bandar+"&skrinName="+skrinName);
}

function setMapPejabatMahkamah(skrinName,nama,alamat1, alamat2, alamat3, poskod, id_negeri, id_bandar, no_tel, no_fax)
{
		//alert("1");
		document.getElementById(skrinName+"NAMA_PEJABAT").value = nama;
		document.getElementById(skrinName+"ALAMAT1").value = alamat1;
		document.getElementById(skrinName+"ALAMAT2").value = alamat2;
		document.getElementById(skrinName+"ALAMAT3").value = alamat3;
		document.getElementById(skrinName+"POSKOD").value = poskod;
		document.getElementById(skrinName+"ID_NEGERI").value = id_negeri;
		document.getElementById(skrinName+"NO_TEL").value = no_tel;
		document.getElementById(skrinName+"NO_FAX").value = no_fax;
		//alert("2");
		//alert('2');
		doDivAjaxCall$formname('div'+skrinName+'ID_NEGERI','showNegeri','ID_NEGERISALIN='+id_negeri+'&ID_BANDARSALIN='+id_bandar+"&skrinName="+skrinName);
}

function setMapPemohon(skrinName,id_permohonan,id_permohonansimati,id_perbicaraan,alamat1, alamat2, alamat3, poskod, id_negeri, id_bandar, negara)
{
		document.getElementById(skrinName+"ALAMAT_1").value = alamat1;
		document.getElementById(skrinName+"ALAMAT_2").value = alamat2;
		document.getElementById(skrinName+"ALAMAT_3").value = alamat3;
		document.getElementById(skrinName+"POSKOD").value = poskod;
		document.getElementById(skrinName+"ID_NEGERI").value = id_negeri;
		document.getElementById(skrinName+"NAMA_PELBAGAINEGARA").value = negara;
		doDivAjaxCall$formname('div'+skrinName+'ID_NEGERI','showNegeri','ID_PERMOHONAN='+id_permohonan+'&ID_PERMOHONANSIMATI='+id_permohonansimati+'&ID_PERBICARAAN='+id_perbicaraan+'&ID_NEGERISALIN='+id_negeri+'&ID_BANDARSALIN='+id_bandar+"&skrinName="+skrinName);

		if(id_negeri=="17")
		{
			document.getElementById("row"+skrinName+"ID_BANDAR").style.display = "none";
			document.getElementById(""+skrinName+"ID_BANDAR").value = "";
			document.getElementById("row"+skrinName+"NAMA_PELBAGAINEGARA").style.display = "";
		}
		else
		{
			document.getElementById("row"+skrinName+"ID_BANDAR").style.display = "";
			document.getElementById("row"+skrinName+"NAMA_PELBAGAINEGARA").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_PELBAGAINEGARA").value = "";
		}

}

function printLaporanStatPegawai()
{
	var url = "../x/${securityToken}/ekptg.view.ppk.BicaraInteraktifPrint?&command=showLaporanStatsPegawai";
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function printLaporanTukarpegawai()
{
	//alert("Cetakan");
	var carianTukarPegawaiNO_TUKARPEGAWAI = document.getElementById("carianTukarPegawaiNO_TUKARPEGAWAI").value;
	var carianTukarPegawaiNO_FAIL = document.getElementById("carianTukarPegawaiNO_FAIL").value;
	var carianTukarPegawaiID_NEGERIPEGAWAIBARU = document.getElementById("carianTukarPegawaiID_NEGERIPEGAWAIBARU").value;
	var carianTukarPegawaiID_NEGERIMHN = document.getElementById("carianTukarPegawaiID_NEGERIMHN").value;
	var carianTukarPegawaiNAMAPEGAWAIASAL = document.getElementById("carianTukarPegawaiNAMAPEGAWAIASAL").value;
	var carianTukarPegawaiNAMAPEGAWAIBARU = document.getElementById("carianTukarPegawaiNAMAPEGAWAIBARU").value;
	var carianTukarPegawaiSTATUS_TUKARPEGAWAI = document.getElementById("carianTukarPegawaiSTATUS_TUKARPEGAWAI").value;
	var carianTukarPegawaiTARIKH_MOHONMULA = document.getElementById("carianTukarPegawaiTARIKH_MOHONMULA").value;
	var carianTukarPegawaiTARIKH_MOHONAKHIR = document.getElementById("carianTukarPegawaiTARIKH_MOHONAKHIR").value;
	var carianTukarPegawaiTARIKH_BICARAMULA = document.getElementById("carianTukarPegawaiTARIKH_BICARAMULA").value;
	var carianTukarPegawaiTARIKH_BICARAAKHIR = document.getElementById("carianTukarPegawaiTARIKH_BICARAAKHIR").value;

	var url = "../x/${securityToken}/ekptg.view.ppk.BicaraInteraktifPrint?&command=showLaporanTukarpegawai&carianTukarPegawaiNO_TUKARPEGAWAI="+carianTukarPegawaiNO_TUKARPEGAWAI
	+"&carianTukarPegawaiNO_FAIL="+carianTukarPegawaiNO_FAIL
	+"&carianTukarPegawaiID_NEGERIPEGAWAIBARU="+carianTukarPegawaiID_NEGERIPEGAWAIBARU
	+"&carianTukarPegawaiID_NEGERIPEGAWAIBARUCONTENT="+getContentDropDown("carianTukarPegawaiID_NEGERIPEGAWAIBARU")
	+"&carianTukarPegawaiID_NEGERIMHN="+carianTukarPegawaiID_NEGERIMHN
	+"&carianTukarPegawaiID_NEGERIMHNCONTENT="+getContentDropDown("carianTukarPegawaiID_NEGERIMHN")
	+"&carianTukarPegawaiNAMAPEGAWAIASAL="+carianTukarPegawaiNAMAPEGAWAIASAL
	+"&carianTukarPegawaiNAMAPEGAWAIBARU="+carianTukarPegawaiNAMAPEGAWAIBARU
	+"&carianTukarPegawaiSTATUS_TUKARPEGAWAI="+carianTukarPegawaiSTATUS_TUKARPEGAWAI
	+"&carianTukarPegawaiSTATUS_TUKARPEGAWAICONTENT="+getContentDropDown("carianTukarPegawaiSTATUS_TUKARPEGAWAI")
	+"&carianTukarPegawaiTARIKH_MOHONMULA="+carianTukarPegawaiTARIKH_MOHONMULA+"&carianTukarPegawaiTARIKH_MOHONAKHIR="+carianTukarPegawaiTARIKH_MOHONAKHIR
	+"&carianTukarPegawaiTARIKH_BICARAMULA="+carianTukarPegawaiTARIKH_BICARAMULA+"&carianTukarPegawaiTARIKH_BICARAAKHIR="+carianTukarPegawaiTARIKH_BICARAAKHIR;

	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}

function getContentDropDown(elemId)
{

	var skillsSelect = document.getElementById(elemId);
	//alert(elemId + " :::::: "+skillsSelect);
	var selectedText = "";
	if(skillsSelect.value != "")
	{
	selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
	}
	return selectedText;

}


function printHistoryJana(ID_HISTORYJANANOTA,ID_PERBICARAAN)
{
	var url = "../x/${securityToken}/ekptg.view.ppk.BicaraInteraktifPrint?ID_PERBICARAAN="+ID_PERBICARAAN+"&ID_HISTORYJANANOTA="+ID_HISTORYJANANOTA+"&command=showJanaNota";
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}


function printCatatanPerintah(ID_PERBICARAAN)
{
	var url = "../x/${securityToken}/ekptg.view.ppk.BicaraInteraktifPrint?&ID_PERBICARAAN="+ID_PERBICARAAN+"&command=showCatatanPerintah";
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function printPerubahan(ID_PERBICARAAN)
{
	var url = "../x/${securityToken}/ekptg.view.ppk.BicaraInteraktifPrint?&ID_PERBICARAAN="+ID_PERBICARAAN+"&command=showMaklumatperubahanPrint";
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function printPerubahan(ID_PERBICARAAN)
{
	var url = "../x/${securityToken}/ekptg.view.ppk.BicaraInteraktifPrint?&ID_PERBICARAAN="+ID_PERBICARAAN+"&command=showMaklumatperubahanPrint";
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function printKeteranganhadir(ID_PERBICARAAN)
{
	//alert('ID_PERBICARAAN : '+ID_PERBICARAAN);
	var url = "../x/${securityToken}/ekptg.view.ppk.BicaraInteraktifPrint?&ID_PERBICARAAN="+ID_PERBICARAAN+"&command=showMaklumatketeranganhadirPrint";
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function setDivRekodPerubahan(val,divid,idtablecetak,msg)
{
	//alert('idtablecetak : '+idtablecetak);
	if(val == "N")
	{
		$jquery("#"+divid).html("&nbsp;"+msg);
		document.getElementById(idtablecetak).style.display = "none";
	}
	else
	{
		document.getElementById(idtablecetak).style.display = "";
	}
}

function setDivTajuk(val,idtablecetak)
{
	if(document.getElementById(idtablecetak))
	{
		if(val == "N")
		{
			document.getElementById(idtablecetak).style.display = "none";
		}
		else
		{
			document.getElementById(idtablecetak).style.display = "";
		}
	}
}

function setLapis(lapis,skrinName)
{
	//alert("setLapis LAPIS :::::::::::: "+lapis);
	var newLapis = 1;
	if(lapis!="")
	{
		newLapis = parseInt(lapis)+1;
	}
	document.getElementById(skrinName+"LAPIS").value = newLapis;
	$jquery("#span"+skrinName+"LAPIS").html(newLapis);
}
function getSetLapis(div,ID_PERMOHONANSIMATI,ID_PERBICARAAN,id_parent,skrinName)
{
	//alert("getSetLapis x");
	doDivAjaxCall3$formname(div,'getLapis','ID_PARENT='+id_parent+"&skrinName="+skrinName+"&ID_PERMOHONANSIMATI="+ID_PERMOHONANSIMATI+"&ID_PERBICARAAN="+ID_PERBICARAAN);
}
function getMapPejabat(div,id_arb,skrinName,ID_PERBICARAAN)
{
	doDivAjaxCall$formname(div,'getAlamatPejabat','ID_ARB='+id_arb+"&skrinName="+skrinName+"&ID_PERBICARAAN="+ID_PERBICARAAN);
}

function resetMapPejabatMahkamahByJenisRujukan(id_jenisrujukan,skrinName,mode)
{

	if(id_jenisrujukan=="" || id_jenisrujukan=="2")
	{
		document.getElementById('trTajukMahkamahTangguh').style.display = "none";
		document.getElementById('row'+skrinName+'ID_NEGERIMAHKAMAH').style.display = "none";
		document.getElementById(skrinName+'ID_NEGERIMAHKAMAH').value = "";
		document.getElementById('row'+skrinName+'ID_MAHKAMAH').style.display = "none";
		document.getElementById(skrinName+'ID_MAHKAMAH').value = "";
		document.getElementById('row'+skrinName+'NAMA_PEJABAT').style.display = "none";
		document.getElementById(skrinName+'NAMA_PEJABAT').value = "";
		document.getElementById('row'+skrinName+'ALAMAT1').style.display = "none";
		document.getElementById(skrinName+'ALAMAT1').value = "";
		document.getElementById('row'+skrinName+'ALAMAT2').style.display = "none";
		document.getElementById(skrinName+'ALAMAT2').value = "";
		document.getElementById('row'+skrinName+'ALAMAT3').style.display = "none";
		document.getElementById(skrinName+'ALAMAT3').value = "";
		document.getElementById('row'+skrinName+'ID_NEGERI').style.display = "none";
		document.getElementById(skrinName+'ID_NEGERI').value = "";
		document.getElementById('row'+skrinName+'POSKOD').style.display = "none";
		document.getElementById(skrinName+'POSKOD').value = "";
		document.getElementById('row'+skrinName+'ID_BANDAR').style.display = "none";
		document.getElementById(skrinName+'ID_BANDAR').value = "";
		document.getElementById('row'+skrinName+'NO_TEL').style.display = "none";
		document.getElementById(skrinName+'NO_TEL').value = "";
		document.getElementById('row'+skrinName+'NO_FAX').style.display = "none";
		document.getElementById(skrinName+'NO_FAX').value = "";
	}
}

function selectFlagRujukan(flag_rujukan,skrinName,mode)
{

	var jenis_rujukan = document.getElementById(skrinName+'JENIS_RUJUKAN').value;
	//alert("flag_rujukan : "+flag_rujukan+" jenis_rujukan : "+jenis_rujukan);
	if(flag_rujukan=="" && jenis_rujukan != "1")
	{
		document.getElementById('trTajukMahkamahTangguh').style.display = "none";
		document.getElementById('row'+skrinName+'ID_NEGERIMAHKAMAH').style.display = "none";
		document.getElementById(skrinName+'ID_NEGERIMAHKAMAH').value = "";
		document.getElementById('row'+skrinName+'ID_MAHKAMAH').style.display = "none";
		document.getElementById(skrinName+'ID_MAHKAMAH').value = "";
		document.getElementById('row'+skrinName+'NAMA_PEJABAT').style.display = "none";
		document.getElementById(skrinName+'NAMA_PEJABAT').value = "";
		document.getElementById('row'+skrinName+'ALAMAT1').style.display = "none";
		document.getElementById(skrinName+'ALAMAT1').value = "";
		document.getElementById('row'+skrinName+'ALAMAT2').style.display = "none";
		document.getElementById(skrinName+'ALAMAT2').value = "";
		document.getElementById('row'+skrinName+'ALAMAT3').style.display = "none";
		document.getElementById(skrinName+'ALAMAT3').value = "";
		document.getElementById('row'+skrinName+'ID_NEGERI').style.display = "none";
		document.getElementById(skrinName+'ID_NEGERI').value = "";
		document.getElementById('row'+skrinName+'POSKOD').style.display = "none";
		document.getElementById(skrinName+'POSKOD').value = "";
		document.getElementById('row'+skrinName+'ID_BANDAR').style.display = "none";
		document.getElementById(skrinName+'ID_BANDAR').value = "";
		document.getElementById('row'+skrinName+'NO_TEL').style.display = "none";
		document.getElementById(skrinName+'NO_TEL').value = "";
		document.getElementById('row'+skrinName+'NO_FAX').style.display = "none";
		document.getElementById(skrinName+'NO_FAX').value = "";
		pejabatAddReadonly(skrinName);
	}
	else if(flag_rujukan!="" && jenis_rujukan == "2")
	{
		document.getElementById('trTajukMahkamahTangguh').style.display = "";
		document.getElementById('row'+skrinName+'ID_NEGERIMAHKAMAH').style.display = "";
		if(flag_rujukan=="1")
		{
			document.getElementById('row'+skrinName+'ID_MAHKAMAH').style.display = "";
			$jquery("#tdTajukMahkamahTangguh").html("Mahkamah Syariah");
			$jquery("#label"+skrinName+"ID_MAHKAMAH").html("Mahkamah Syariah");
			pejabatAddReadonly(skrinName);
		}
		else
		{
			$jquery("#tdTajukMahkamahTangguh").html("Pejabat Mufti");
			document.getElementById('row'+skrinName+'ID_MAHKAMAH').style.display = "none";
			document.getElementById(skrinName+'ID_MAHKAMAH').value = "";
			document.getElementById('row'+skrinName+'ID_NEGERIMAHKAMAH').style.display = "none";
			pejabatRemoveReadonly(skrinName);
		}
		document.getElementById('row'+skrinName+'NAMA_PEJABAT').style.display = "";
		document.getElementById('row'+skrinName+'ALAMAT1').style.display = "";
		document.getElementById('row'+skrinName+'ALAMAT2').style.display = "";
		document.getElementById('row'+skrinName+'ALAMAT3').style.display = "";
		document.getElementById('row'+skrinName+'ID_NEGERI').style.display = "";
		document.getElementById('row'+skrinName+'POSKOD').style.display = "";
		document.getElementById('row'+skrinName+'ID_BANDAR').style.display = "";
		document.getElementById('row'+skrinName+'NO_TEL').style.display = "";
		document.getElementById('row'+skrinName+'NO_FAX').style.display = "";
	}
}


function resetMapPejabatMahkamah(id_negeri,skrinName,mode)
{
	//if(id_negeri=="")
	{
		kosongFieldPejabat(skrinName,mode);
	}
}

function kosongFieldPejabat(skrinName,mode)
{
	//document.getElementById('trTajukMahkamahTangguh').style.display = "none";
		//document.getElementById('row'+skrinName+'ALAMAT_1').style.display = "none";
		document.getElementById(skrinName+'NAMA_PEJABAT').value = "";
		document.getElementById(skrinName+'ALAMAT1').value = "";
		//document.getElementById('row'+skrinName+'ALAMAT_2').style.display = "none";
		document.getElementById(skrinName+'ALAMAT2').value = "";
		//document.getElementById('row'+skrinName+'ALAMAT_3').style.display = "none";
		document.getElementById(skrinName+'ALAMAT3').value = "";
		//document.getElementById('row'+skrinName+'ID_NEGERI').style.display = "none";
		document.getElementById(skrinName+'ID_NEGERI').value = "";
		//document.getElementById('row'+skrinName+'POSKOD').style.display = "none";
		document.getElementById(skrinName+'POSKOD').value = "";
		//document.getElementById('row'+skrinName+'ID_BANDAR').style.display = "none";
		document.getElementById(skrinName+'ID_BANDAR').value = "";
		//document.getElementById('row'+skrinName+'NO_TEL').style.display = "none";
		document.getElementById(skrinName+'NO_TEL').value = "";
		//document.getElementById('row'+skrinName+'NO_FAX').style.display = "none";
		document.getElementById(skrinName+'NO_FAX').value = "";
}


function getMapPejabatMahkamah(div,id_mahkamah,skrinName,ID_PERBICARAAN,columnName)
{
	doDivAjaxCall$formname(div,'getAlamatMahkamah','ID_MAHKAMAH='+id_mahkamah+"&skrinName="+skrinName+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&columnName"+columnName);
}
function getMapPemohon(div,id_permohonan,id_permohonansimati,id_perbicaraan,skrinName)
{
	doDivAjaxCall$formname(div,'getAlamatPemohon','ID_PERMOHONAN='+id_permohonan+'&ID_PERMOHONANSIMATI='+id_permohonansimati+'&ID_PERBICARAAN='+id_perbicaraan+'&skrinName='+skrinName);
}

function showMaklumatMatiWaris(status,skrinName,mode)
{
	//alert('showMaklumatMatiWaris : '+status);
	if(status == "1")
	{
		document.getElementById("row"+skrinName+"TARIKH_MATI").style.display = "";
		document.getElementById("row"+skrinName+"WAKTU_KEMATIAN").style.display = "";
		document.getElementById("row"+skrinName+"JENIS_WAKTU_KEMATIAN").style.display = "";
	}
	else
	{
		document.getElementById("row"+skrinName+"TARIKH_MATI").style.display = "none";
		document.getElementById(""+skrinName+"TARIKH_MATI").value = "";
		document.getElementById("row"+skrinName+"WAKTU_KEMATIAN").style.display = "none";
		document.getElementById(""+skrinName+"WAKTU_KEMATIAN").value = "";
		document.getElementById("row"+skrinName+"JENIS_WAKTU_KEMATIAN").style.display = "none";
		document.getElementById(""+skrinName+"JENIS_WAKTU_KEMATIAN").value = "";
	}
}

function showKeteranganBantahan(status,skrinName,mode)
{
	//alert('showKeteranganBantahan : '+status);
	if(status == "Y")
	{
		document.getElementById("row"+skrinName+"KETERANGAN_BANTAHAN").style.display = "";
	}
	else
	{
		document.getElementById("row"+skrinName+"KETERANGAN_BANTAHAN").style.display = "none";
		document.getElementById(""+skrinName+"KETERANGAN_BANTAHAN").value = "";
	}
}

function showMaklumatKIVPerintah(checkKiv,skrinName,mode)
{
	//alert('showMaklumatMatiWaris : '+status);
	if(checkKiv == "0" || checkKiv == "1" || checkKiv == "3")
	{
		document.getElementById("row"+skrinName+"DATE_KIV").style.display = "";
		document.getElementById("row"+skrinName+"CATATAN_KIV").style.display = "";
	}
	else
	{
		document.getElementById("row"+skrinName+"DATE_KIV").style.display = "none";
		document.getElementById(""+skrinName+"DATE_KIV").value = "";
		document.getElementById("row"+skrinName+"CATATAN_KIV").style.display = "none";
		document.getElementById(""+skrinName+"CATATAN_KIV").value = "";
	}
}

function pilihJenisHTA(val,skrinName,mode,action)
{
	//alert('pilihJenisHTA : '+val+' action : '+action);

		if(val == "Y")
		{
			document.getElementById("row"+skrinName+"FLAG_KATEGORI_HTA").style.display = "none";
			document.getElementById(""+skrinName+"FLAG_KATEGORI_HTA").value = "";
			if(action=="onChange")
			{
				pilihJenisKategoriHTA("",skrinName,mode);
			}
		}
		else if(val == "T")
		{
			document.getElementById("row"+skrinName+"FLAG_KATEGORI_HTA").style.display = "";
			if(action=="onChange")
			{
				document.getElementById(""+skrinName+"FLAG_KATEGORI_HTA").value = "1";
				pilihJenisKategoriHTA("1",skrinName,mode);
			}
		}


}

function pilihJenisHA(val,skrinName)
{
	//alert(val)
	$jquery("#label"+skrinName+"NILAI_HA_TARIKHMOHON").html("Nilai Tarikh Mohon (RM)");
	$jquery("#label"+skrinName+"BUTIRAN").html("Butiran");
	if(val != "")
	{
		if (val == "1")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "none";
			document.getElementById(""+skrinName+"BUTIRAN").value = "";
			document.getElementById("row"+skrinName+"JENAMA").style.display = "";
			$jquery("#label"+skrinName+"JENAMA").html("Agensi");
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "";
			$jquery("#label"+skrinName+"NO_DAFTAR").html("No. Ahli");
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "";
		}
		else if (val == "2")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "none";
			document.getElementById(""+skrinName+"BUTIRAN").value = "";
			document.getElementById("row"+skrinName+"JENAMA").style.display = "";
			$jquery("#label"+skrinName+"JENAMA").html("Agensi");
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "";
			$jquery("#label"+skrinName+"NO_DAFTAR").html("No. Akaun");
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
		else if (val == "3")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "none";
			document.getElementById(""+skrinName+"BUTIRAN").value = "";
			document.getElementById("row"+skrinName+"JENAMA").style.display = "";
			$jquery("#label"+skrinName+"JENAMA").html("Jenis & Jenama");
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "";
			$jquery("#label"+skrinName+"NO_DAFTAR").html("No. Daftar");
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
		else if (val == "4")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "none";
			document.getElementById(""+skrinName+"BUTIRAN").value = "";
			document.getElementById("row"+skrinName+"JENAMA").style.display = "";
			$jquery("#label"+skrinName+"JENAMA").html("No. Hakmilik, No. Lot dan Mukim");
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "";
			$jquery("#label"+skrinName+"NO_DAFTAR").html("No. Rujukan UPT");
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
		else if (val == "5")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "none";
			document.getElementById(""+skrinName+"BUTIRAN").value = "";
			document.getElementById("row"+skrinName+"JENAMA").style.display = "";
			$jquery("#label"+skrinName+"JENAMA").html("Agensi");
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "";
			$jquery("#label"+skrinName+"NO_DAFTAR").html("No. Polisi");
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
		else if (val == "6")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "";
			$jquery("#label"+skrinName+"BUTIRAN").html("Butiran Peti Keselamatan");
			document.getElementById("row"+skrinName+"JENAMA").style.display = "";
			$jquery("#label"+skrinName+"JENAMA").html("Agensi");
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "";
			$jquery("#label"+skrinName+"NO_DAFTAR").html("No. Peti");
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
		else if (val == "7")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "";
			$jquery("#label"+skrinName+"BUTIRAN").html("Lokasi Harta");
			document.getElementById("row"+skrinName+"JENAMA").style.display = "none";
			document.getElementById(""+skrinName+"JENAMA").value = "";
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "none";
			document.getElementById(""+skrinName+"NO_DAFTAR").value = "";
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
		else if (val == "8")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "";
			$jquery("#label"+skrinName+"BUTIRAN").html("Butiran Tuntutan");
			document.getElementById("row"+skrinName+"JENAMA").style.display = "none";
			document.getElementById(""+skrinName+"JENAMA").value = "";
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "none";
			document.getElementById(""+skrinName+"NO_DAFTAR").value = "";
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
		else if (val == "9")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "";
			$jquery("#label"+skrinName+"BUTIRAN").html("Butiran Senjata Api");
			document.getElementById("row"+skrinName+"JENAMA").style.display = "none";
			document.getElementById(""+skrinName+"JENAMA").value = "";
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "none";
			document.getElementById(""+skrinName+"NO_DAFTAR").value = "";
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
		else if (val == "10")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "";
			$jquery("#label"+skrinName+"BUTIRAN").html("Butiran Barang Berharga");
			document.getElementById("row"+skrinName+"JENAMA").style.display = "none";
			document.getElementById(""+skrinName+"JENAMA").value = "";
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "none";
			document.getElementById(""+skrinName+"NO_DAFTAR").value = "";
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
		else if (val == "11")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "";
			$jquery("#label"+skrinName+"BUTIRAN").html("Butiran Ternakan");
			document.getElementById("row"+skrinName+"JENAMA").style.display = "none";
			document.getElementById(""+skrinName+"JENAMA").value = "";
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "none";
			document.getElementById(""+skrinName+"NO_DAFTAR").value = "";
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
		else if (val == "12")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "";
			$jquery("#label"+skrinName+"BUTIRAN").html("Butiran Harta");
			document.getElementById("row"+skrinName+"JENAMA").style.display = "none";
			document.getElementById(""+skrinName+"JENAMA").value = "";
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "none";
			document.getElementById(""+skrinName+"NO_DAFTAR").value = "";
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
		else if (val == "98")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "";
			$jquery("#label"+skrinName+"BUTIRAN").html("Butiran Nilaian Harta");
			document.getElementById("row"+skrinName+"JENAMA").style.display = "none";
			document.getElementById(""+skrinName+"JENAMA").value = "";
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "";
			$jquery("#label"+skrinName+"NO_DAFTAR").html("No. Rujukan");
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
			$jquery("#label"+skrinName+"NILAI_HA_TARIKHMOHON").html("Nilaian (RM)");
		}
		else if (val == "0")
		{
			document.getElementById("row"+skrinName+"BUTIRAN").style.display = "";
			document.getElementById(""+skrinName+"BUTIRAN").value = "";
			document.getElementById("row"+skrinName+"JENAMA").style.display = "none";
			document.getElementById(""+skrinName+"JENAMA").value = "";
			document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
			document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "none";
			document.getElementById(""+skrinName+"NO_DAFTAR").value = "";
			document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
			document.getElementById(""+skrinName+"NO_SIJIL").value = "";
			document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
			document.getElementById(""+skrinName+"BIL_UNIT").value = "";
			document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
			document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
		}
	}
	else
	{
		document.getElementById("row"+skrinName+"BUTIRAN").style.display = "";
		document.getElementById(""+skrinName+"BUTIRAN").value = "";
		document.getElementById("row"+skrinName+"JENAMA").style.display = "none";
		document.getElementById(""+skrinName+"JENAMA").value = "";
		document.getElementById("row"+skrinName+"NAMA_SAHAM").style.display = "none";
		document.getElementById(""+skrinName+"NAMA_SAHAM").value = "";
		document.getElementById("row"+skrinName+"NO_DAFTAR").style.display = "none";
		document.getElementById(""+skrinName+"NO_DAFTAR").value = "";
		document.getElementById("row"+skrinName+"NO_SIJIL").style.display = "none";
		document.getElementById(""+skrinName+"NO_SIJIL").value = "";
		document.getElementById("row"+skrinName+"BIL_UNIT").style.display = "none";
		document.getElementById(""+skrinName+"BIL_UNIT").value = "";
		document.getElementById("row"+skrinName+"NILAI_SEUNIT").style.display = "none";
		document.getElementById(""+skrinName+"NILAI_SEUNIT").value = "";
	}
}

function pilihJenisKategoriHTA(val,skrinName,mode)
{
	//alert(' pilihJenisKategoriHTA : '+val);

	if(val != "")
	{
		document.getElementById("row"+skrinName+"ID_JENISHM").style.display = "none";
		document.getElementById(""+skrinName+"ID_JENISHM").value = "";
		document.getElementById("row"+skrinName+"NO_HAKMILIK").style.display = "none";
		document.getElementById(""+skrinName+"NO_HAKMILIK").value = "";
		document.getElementById("row"+skrinName+"NO_PT").style.display = "none";
		document.getElementById(""+skrinName+"NO_PT").value = "";
		document.getElementById("row"+skrinName+"NO_PERSERAHAN").style.display = "none";
		document.getElementById(""+skrinName+"NO_PERSERAHAN").value = "";
		document.getElementById("row"+skrinName+"SEKATAN").style.display = "none";
		document.getElementById(""+skrinName+"SEKATAN").value = "";
		document.getElementById("row"+skrinName+"SYARAT_NYATA").style.display = "none";
		document.getElementById(""+skrinName+"SYARAT_NYATA").value = "";

		document.getElementById("row"+skrinName+"NAMA_PEMAJU").style.display = "";
		document.getElementById("row"+skrinName+"ALAMAT_PEMAJU1").style.display = "";
		document.getElementById("row"+skrinName+"ALAMAT_PEMAJU2").style.display = "";
		document.getElementById("row"+skrinName+"ALAMAT_PEMAJU3").style.display = "";
		document.getElementById("row"+skrinName+"POSKOD_PEMAJU").style.display = "";
		document.getElementById("row"+skrinName+"ID_NEGERIPEMAJU").style.display = "";
		document.getElementById("row"+skrinName+"ID_BANDARPEMAJU").style.display = "";
		document.getElementById("row"+skrinName+"NO_CAGARAN").style.display = "";

		if(val == "1")
		{
			document.getElementById("row"+skrinName+"NO_PERJANJIAN").style.display = "";
			document.getElementById("row"+skrinName+"TARIKH_PERJANJIAN").style.display = "";
			document.getElementById("row"+skrinName+"ALAMAT_HTA1").style.display = "";
			document.getElementById("row"+skrinName+"ALAMAT_HTA2").style.display = "";
			document.getElementById("row"+skrinName+"ALAMAT_HTA3").style.display = "";
			document.getElementById("row"+skrinName+"POSKOD_HTA").style.display = "";
			document.getElementById("row"+skrinName+"ID_BANDARHTA").style.display = "";

			document.getElementById("row"+skrinName+"NAMA_RANCANGAN").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_RANCANGAN").value = "";
			document.getElementById("row"+skrinName+"NO_ROH").style.display = "none";
			document.getElementById(""+skrinName+"NO_ROH").value = "";
			document.getElementById("row"+skrinName+"NO_LOT_ID").style.display = "none";
			document.getElementById(""+skrinName+"NO_LOT_ID").value = "";
			document.getElementById("row"+skrinName+"JENIS_KEPENTINGAN").style.display = "none";
			document.getElementById(""+skrinName+"JENIS_KEPENTINGAN").value = "";

			$jquery("#label"+skrinName+"NAMA_PEMAJU").html("Nama Pemaju");
			$jquery("#label"+skrinName+"ALAMAT_PEMAJU1").html("Alamat Pemaju 1");
			$jquery("#label"+skrinName+"ALAMAT_PEMAJU2").html("Alamat Pemaju 2");
			$jquery("#label"+skrinName+"ALAMAT_PEMAJU3").html("Alamat Pemaju 3");

		}
		else if(val == "2")
		{
			document.getElementById("row"+skrinName+"NAMA_RANCANGAN").style.display = "";
			document.getElementById("row"+skrinName+"NO_ROH").style.display = "";
			document.getElementById("row"+skrinName+"NO_LOT_ID").style.display = "";

			document.getElementById("row"+skrinName+"NO_PERJANJIAN").style.display = "none";
			document.getElementById(""+skrinName+"NO_PERJANJIAN").value = "";
			document.getElementById("row"+skrinName+"TARIKH_PERJANJIAN").style.display = "none";
			document.getElementById(""+skrinName+"TARIKH_PERJANJIAN").value = "";
			document.getElementById("row"+skrinName+"JENIS_KEPENTINGAN").style.display = "none";
			document.getElementById(""+skrinName+"JENIS_KEPENTINGAN").value = "";
			document.getElementById("row"+skrinName+"ALAMAT_HTA1").style.display = "none";
			document.getElementById(""+skrinName+"ALAMAT_HTA1").value = "";
			document.getElementById("row"+skrinName+"ALAMAT_HTA2").style.display = "none";
			document.getElementById(""+skrinName+"ALAMAT_HTA2").value = "";
			document.getElementById("row"+skrinName+"ALAMAT_HTA3").style.display = "none";
			document.getElementById(""+skrinName+"ALAMAT_HTA3").value = "";
			document.getElementById("row"+skrinName+"POSKOD_HTA").style.display = "none";
			document.getElementById(""+skrinName+"POSKOD_HTA").value = "";
			document.getElementById("row"+skrinName+"ID_BANDARHTA").style.display = "none";
			document.getElementById(""+skrinName+"ID_BANDARHTA").value = "";

			$jquery("#label"+skrinName+"NAMA_PEMAJU").html("Nama Agensi");
			$jquery("#label"+skrinName+"ALAMAT_PEMAJU1").html("Alamat Agensi 1");
			$jquery("#label"+skrinName+"ALAMAT_PEMAJU2").html("Alamat Agensi 2");
			$jquery("#label"+skrinName+"ALAMAT_PEMAJU3").html("Alamat Agensi 3");
		}
		else if(val == "3")
		{
			document.getElementById("row"+skrinName+"JENIS_KEPENTINGAN").style.display = "";

			document.getElementById("row"+skrinName+"NO_PERJANJIAN").style.display = "none";
			document.getElementById(""+skrinName+"NO_PERJANJIAN").value = "";
			document.getElementById("row"+skrinName+"TARIKH_PERJANJIAN").style.display = "none";
			document.getElementById(""+skrinName+"TARIKH_PERJANJIAN").value = "";
			document.getElementById("row"+skrinName+"NAMA_RANCANGAN").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_RANCANGAN").value = "";
			document.getElementById("row"+skrinName+"NO_ROH").style.display = "none";
			document.getElementById(""+skrinName+"NO_ROH").value = "";
			document.getElementById("row"+skrinName+"NO_LOT_ID").style.display = "none";
			document.getElementById(""+skrinName+"NO_LOT_ID").value = "";
			document.getElementById("row"+skrinName+"ALAMAT_HTA1").style.display = "none";
			document.getElementById(""+skrinName+"ALAMAT_HTA1").value = "";
			document.getElementById("row"+skrinName+"ALAMAT_HTA2").style.display = "none";
			document.getElementById(""+skrinName+"ALAMAT_HTA2").value = "";
			document.getElementById("row"+skrinName+"ALAMAT_HTA3").style.display = "none";
			document.getElementById(""+skrinName+"ALAMAT_HTA3").value = "";
			document.getElementById("row"+skrinName+"POSKOD_HTA").style.display = "none";
			document.getElementById(""+skrinName+"POSKOD_HTA").value = "";
			document.getElementById("row"+skrinName+"ID_BANDARHTA").style.display = "none";
			document.getElementById(""+skrinName+"ID_BANDARHTA").value = "";

			$jquery("#label"+skrinName+"NAMA_PEMAJU").html("Nama Pemaju");
			$jquery("#label"+skrinName+"ALAMAT_PEMAJU1").html("Alamat Pemaju 1");
			$jquery("#label"+skrinName+"ALAMAT_PEMAJU2").html("Alamat Pemaju 2");
			$jquery("#label"+skrinName+"ALAMAT_PEMAJU3").html("Alamat Pemaju 3");
		}

	}
	else
	{
		document.getElementById("row"+skrinName+"ID_JENISHM").style.display = "";
		document.getElementById("row"+skrinName+"NO_HAKMILIK").style.display = "";
		document.getElementById("row"+skrinName+"NO_PT").style.display = "";
		document.getElementById("row"+skrinName+"ALAMAT_HTA1").style.display = "";
		document.getElementById("row"+skrinName+"ALAMAT_HTA2").style.display = "";
		document.getElementById("row"+skrinName+"ALAMAT_HTA3").style.display = "";
		document.getElementById("row"+skrinName+"POSKOD_HTA").style.display = "";
		document.getElementById("row"+skrinName+"ID_BANDARHTA").style.display = "";
		document.getElementById("row"+skrinName+"NO_PERSERAHAN").style.display = "";
		document.getElementById("row"+skrinName+"SEKATAN").style.display = "";
		document.getElementById("row"+skrinName+"SYARAT_NYATA").style.display = "";

		document.getElementById("row"+skrinName+"NAMA_PEMAJU").style.display = "none";
		document.getElementById(""+skrinName+"NAMA_PEMAJU").value = "";
		document.getElementById("row"+skrinName+"ALAMAT_PEMAJU1").style.display = "none";
		document.getElementById(""+skrinName+"ALAMAT_PEMAJU1").value = "";
		document.getElementById("row"+skrinName+"ALAMAT_PEMAJU2").style.display = "none";
		document.getElementById(""+skrinName+"ALAMAT_PEMAJU2").value = "";
		document.getElementById("row"+skrinName+"ALAMAT_PEMAJU3").style.display = "none";
		document.getElementById(""+skrinName+"ALAMAT_PEMAJU3").value = "";
		document.getElementById("row"+skrinName+"POSKOD_PEMAJU").style.display = "none";
		document.getElementById(""+skrinName+"POSKOD_PEMAJU").value = "";
		document.getElementById("row"+skrinName+"ID_NEGERIPEMAJU").style.display = "none";
		document.getElementById(""+skrinName+"ID_NEGERIPEMAJU").value = "";
		document.getElementById("row"+skrinName+"ID_BANDARPEMAJU").style.display = "none";
		document.getElementById(""+skrinName+"ID_BANDARPEMAJU").value = "";
		document.getElementById("row"+skrinName+"NO_CAGARAN").style.display = "none";
		document.getElementById(""+skrinName+"NO_CAGARAN").value = "";
		document.getElementById("row"+skrinName+"NO_PERJANJIAN").style.display = "none";
		document.getElementById(""+skrinName+"NO_PERJANJIAN").value = "";
		document.getElementById("row"+skrinName+"TARIKH_PERJANJIAN").style.display = "none";
		document.getElementById(""+skrinName+"TARIKH_PERJANJIAN").value = "";
		document.getElementById("row"+skrinName+"JENIS_KEPENTINGAN").style.display = "none";
		document.getElementById(""+skrinName+"JENIS_KEPENTINGAN").value = "";
		document.getElementById("row"+skrinName+"NAMA_RANCANGAN").style.display = "none";
		document.getElementById(""+skrinName+"NAMA_RANCANGAN").value = "";
		document.getElementById("row"+skrinName+"NO_ROH").style.display = "none";
		document.getElementById(""+skrinName+"NO_ROH").value = "";
		document.getElementById("row"+skrinName+"NO_LOT_ID").style.display = "none";
		document.getElementById(""+skrinName+"NO_LOT_ID").value = "";
	}


}

function setTRNegera(val,skrinName,column_name)
{
	if(skrinName!="keputusan")
	{
		if(val=="17")
		{

			if(column_name=="ID_NEGERI")
			{
				document.getElementById("row"+skrinName+"ID_BANDAR").style.display = "none";
				document.getElementById(""+skrinName+"ID_BANDAR").value = "";
				document.getElementById("row"+skrinName+"NAMA_PELBAGAINEGARA").style.display = "";
			}
			else if(column_name=="ID_NEGERISURAT")
			{
				document.getElementById("row"+skrinName+"ID_BANDARSURAT").style.display = "none";
				document.getElementById(""+skrinName+"ID_BANDARSURAT").value = "";
				document.getElementById("row"+skrinName+"NAMA_PELBAGAINEGARA_SURAT").style.display = "";
			}

		}
		else
		{

			if(column_name=="ID_NEGERI")
			{
				document.getElementById("row"+skrinName+"ID_BANDAR").style.display = "";
				document.getElementById("row"+skrinName+"NAMA_PELBAGAINEGARA").style.display = "none";
				document.getElementById(""+skrinName+"NAMA_PELBAGAINEGARA").value = "";
			}
			else if(column_name=="ID_NEGERISURAT")
			{
				document.getElementById("row"+skrinName+"ID_BANDARSURAT").style.display = "";
				document.getElementById("row"+skrinName+"NAMA_PELBAGAINEGARA_SURAT").style.display = "none";
				document.getElementById(""+skrinName+"NAMA_PELBAGAINEGARA_SURAT").value = "";
			}
		}
	}
}

function showTrIndividuAgensi(skrinName,mode)
{
	//function ni ada 6 jenis skrin yg akan pakai
	//pemohon,waris,ob,saksi,pemiutang,penghutang
	var ID_TARAFKPTG = "";
	if(skrinName != "penghutang")
	{
		ID_TARAFKPTG = document.getElementById(skrinName+"ID_TARAFKPTG").value;
	}
	//alert(" showTrIndividuAgensi :::: ID_TARAFKPTG  :"+ID_TARAFKPTG+"  skrinName : "+skrinName);

	var STATUS_PEMOHON = "";
	if(skrinName == "pemohon")
	{
		STATUS_PEMOHON = document.getElementById(skrinName+"STATUS_PEMOHON").value;
	}
	else if(skrinName == "ob" || skrinName == "pemiutang")
	{
		STATUS_PEMOHON = document.getElementById(skrinName+"JENIS_PEMIUTANG").value;
	}
	else if(skrinName == "penghutang")
	{
		STATUS_PEMOHON = document.getElementById(skrinName+"JENIS_PENGHUTANG").value;
	}

	var agensiOnly = false;
	var pejabatOnly = false;
	if(ID_TARAFKPTG == "6" || ID_TARAFKPTG == "8")
	{
		agensiOnly = true;
		pejabatOnly = true;
	}
	else if(ID_TARAFKPTG == "2" || ID_TARAFKPTG == "4")
	{
		if(STATUS_PEMOHON == "1")
		{
			//agensi
			agensiOnly = true;
		}
	}
	else
	{
		if(skrinName == "penghutang")
		{
			if(STATUS_PEMOHON == "1")
			{
				//agensi
				agensiOnly = true;
			}
			else
			{
				pejabatOnly = true;
			}
		}
	}

	if(skrinName != "pemiutang" && skrinName != "penghutang")
	{
		if(pejabatOnly == false)
		{
			document.getElementById("row"+skrinName+"ID_ARB").style.display = "none";
			document.getElementById(""+skrinName+"ID_ARB").value = "";
		}
		else
		{
			document.getElementById("row"+skrinName+"ID_ARB").style.display = "";
		}
	}

	//alert("agensiOnly : "+agensiOnly);agensiOnly

	if(agensiOnly == true)
	{
		document.getElementById("row"+skrinName+"NO_KP_BARU").style.display = "none";
		if(mode == "edit" )
		{
			document.getElementById(""+skrinName+"NO_KP_BARU1").value = "";
			document.getElementById(""+skrinName+"NO_KP_BARU2").value = "";
			document.getElementById(""+skrinName+"NO_KP_BARU3").value = "";
		}



		document.getElementById("row"+skrinName+"JENIS_KP").style.display = "none";
		document.getElementById(""+skrinName+"JENIS_KP").value = "";

		document.getElementById("row"+skrinName+"NO_KP_LAIN").style.display = "none";
		document.getElementById(""+skrinName+"NO_KP_LAIN").value = "";

		if(skrinName != "pemiutang" && skrinName != "penghutang")
		{
			document.getElementById("row"+skrinName+"NO_KP_LAMA").style.display = "none";
			document.getElementById(""+skrinName+"NO_KP_LAMA").value = "";
		}

		if(skrinName == "ob" || skrinName == "waris" || skrinName == "pemohon")
		{
			document.getElementById("row"+skrinName+"NAMA_LAIN").style.display = "none";
			document.getElementById(""+skrinName+"NAMA_LAIN").value = "";
		}

		if(skrinName != "penghutang")
		{
			document.getElementById("row"+skrinName+"JANTINA").style.display = "none";
			document.getElementById(""+skrinName+"JANTINA").value = "";
		}

		if(skrinName == "pemohon")
		{
			document.getElementById("row"+skrinName+"ID_SAUDARA").style.display = "none";
			document.getElementById(""+skrinName+"ID_SAUDARA").value = "";
		}

		if(skrinName == "ob" || skrinName == "pemiutang")
		{
			document.getElementById("row"+skrinName+"TARIKH_LAHIR").style.display = "none";
			document.getElementById(""+skrinName+"TARIKH_LAHIR").value = "";
		}

		if(skrinName == "pemiutang" || skrinName == "penghutang")
		{
			$jquery("#label"+skrinName+"NO_KP_LAMA").html("No. Pendaftaran");
		}

		if(skrinName == "ob")
		{
			document.getElementById("row"+skrinName+"STATUS_OB").style.display = "none";
			document.getElementById(""+skrinName+"STATUS_OB").value = "";
			document.getElementById("row"+skrinName+"NO_SURAT_BERANAK").style.display = "none";
			document.getElementById(""+skrinName+"NO_SURAT_BERANAK").value = "";
		}

		document.getElementById("row"+skrinName+"JENIS_AGAMA").style.display = "none";
		document.getElementById(""+skrinName+"JENIS_AGAMA").value = "";

		document.getElementById("row"+skrinName+"JENIS_WARGA").style.display = "none";
		document.getElementById(""+skrinName+"JENIS_WARGA").value = "";

		if(skrinName != "penghutang")
		{
			document.getElementById("row"+skrinName+"UMUR").style.display = "none";
			document.getElementById(""+skrinName+"UMUR").value = "";
		}
	}
	else
	{
		//alert("x");
		document.getElementById("row"+skrinName+"NO_KP_BARU").style.display = "";
		document.getElementById("row"+skrinName+"NO_KP_LAMA").display = "";

		if(skrinName == "pemiutang" || skrinName == "penghutang")
		{
			$jquery("#label"+skrinName+"NO_KP_LAMA").html("MyID Lama");
		}

		document.getElementById("row"+skrinName+"JENIS_KP").style.display = "";
		document.getElementById("row"+skrinName+"NO_KP_LAIN").style.display = "";
		if(skrinName == "ob" || skrinName == "waris" || skrinName == "pemohon")
		{
			document.getElementById("row"+skrinName+"NAMA_LAIN").style.display = "";
		}

		if(skrinName != "penghutang")
		{
			document.getElementById("row"+skrinName+"JANTINA").style.display = "";
		}

		if(skrinName == "pemohon")
		{
			document.getElementById("row"+skrinName+"ID_SAUDARA").style.display = "";
		}

		if(skrinName == "ob" || skrinName == "pemiutang")
		{
			document.getElementById("row"+skrinName+"TARIKH_LAHIR").style.display = "";
		}

		if(skrinName == "ob")
		{
			document.getElementById("row"+skrinName+"STATUS_OB").style.display = "";
			document.getElementById("row"+skrinName+"NO_SURAT_BERANAK").style.display = "";
		}
		document.getElementById("row"+skrinName+"JENIS_AGAMA").style.display = "";
		document.getElementById("row"+skrinName+"JENIS_WARGA").style.display = "";
		if(skrinName != "penghutang")
		{
			document.getElementById("row"+skrinName+"UMUR").style.display = "";
		}
	}

	//document.getElementById("CATATAN").value = "SETEL";
}


function setIdPemohon(skrinName, id_pemohon)
{
	//alert("id_pemohon main : "+id_pemohon);
	document.getElementById(skrinName+"ID_PEMOHON").value = id_pemohon;
}

function reloadPejabat(mainTable,skrinName,mode,ID_PERBICARAAN)
{

	var ID_TARAFKPTG = document.getElementById(skrinName+"ID_TARAFKPTG").value;
	//alert(" reloadPejabat :::: ID_TARAFKPTG  :"+ID_TARAFKPTG+"  skrinName : "+skrinName);

	if(ID_TARAFKPTG=="6" || ID_TARAFKPTG=="8")
	{
		if(mode=="edit")
		{
			document.getElementById(skrinName+"NO_KP_BARU1").value = "";
			document.getElementById(skrinName+"NO_KP_BARU2").value = "";
			document.getElementById(skrinName+"NO_KP_BARU3").value = "";
		}
		else
		{
			document.getElementById(skrinName+"NO_KP_BARU").value = "";
		}
		document.getElementById(skrinName+"NO_KP_LAMA").value = "";
		document.getElementById(skrinName+"JENIS_KP").value = "";
		document.getElementById(skrinName+"NO_KP_LAIN").value = "";

		if(skrinName == "pemohon")
		{
			document.getElementById(skrinName+"NAMA_PEMOHON").value = "";
		}
		else if(skrinName == "ob")
		{
			document.getElementById(skrinName+"NAMA_OB").value = "";
		}

		document.getElementById(skrinName+"NAMA_LAIN").value = "";
		document.getElementById(skrinName+"JANTINA").value = "";
		if(skrinName == "pemohon")
		{
			document.getElementById(skrinName+"ID_SAUDARA").value = "";
		}
		if(skrinName == "ob")
		{
			document.getElementById(""+skrinName+"TARIKH_LAHIR").value = "";
			document.getElementById(""+skrinName+"STATUS_OB").value = "";
			document.getElementById(""+skrinName+"NO_SURAT_BERANAK").value = "";
		}
		document.getElementById(skrinName+"JENIS_AGAMA").value = "";
		document.getElementById(skrinName+"JENIS_WARGA").value = "";
		document.getElementById(skrinName+"UMUR").value = "";
		document.getElementById(skrinName+"ALAMAT_1").value = "";
		document.getElementById(skrinName+"ALAMAT_2").value = "";
		document.getElementById(skrinName+"ALAMAT_3").value = "";
		document.getElementById(skrinName+"POSKOD").value = "";
		document.getElementById(skrinName+"ID_NEGERI").value = "";
		document.getElementById(skrinName+"ID_BANDAR").value = "";
		document.getElementById(skrinName+"ALAMAT1_SURAT").value = "";
		document.getElementById(skrinName+"ALAMAT2_SURAT").value = "";
		document.getElementById(skrinName+"ALAMAT3_SURAT").value = "";
		document.getElementById(skrinName+"POSKOD_SURAT").value  = "";
		document.getElementById(skrinName+"ID_NEGERISURAT").value = "";
		document.getElementById(skrinName+"ID_BANDARSURAT").value = "";

		doDivAjaxCall$formname('div'+skrinName+'ID_ARB','showArbBaitulmal','mainTable='+mainTable+'&ID_TARAFKPTG='+document.getElementById(skrinName+"ID_TARAFKPTG").value+'&ID_ARB=&skrinName='+skrinName+'&ID_PERBICARAAN='+ID_PERBICARAAN);
	}
	else
	{
		//alert('reload pejabat');
		showTrIndividuAgensi(skrinName,mode);
	}

}


function simpanTurutHadir(id_bikehadiran,id_perbicaraan,id_permohonan,lokasi,rowCss,BIL)
{
	var nama = document.getElementById("NAMA_TURUTHADIR_"+id_bikehadiran);
	if(nama.value == "")
	{
		alert('Sila masukkan nama!');
		nama.focus();
		return;
	}
	else
	{
		if(id_bikehadiran == "")
		{
			doDivAjaxCall$formname('view_turuthadir','tambah_turuthadir','ID_BIKEHADIRAN='+id_bikehadiran+'&ID_PERBICARAAN='+id_perbicaraan+'&ID_PERMOHONAN='+id_permohonan+'&scrolPosition='+lokasi+'&rowCss='+rowCss+'&BIL='+BIL);
		}
		else
		{
			doDivAjaxCall$formname('rowTurutHadir_'+id_bikehadiran,'saveTurutHadir','ID_BIKEHADIRAN='+id_bikehadiran+'&ID_PERBICARAAN='+id_perbicaraan+'&ID_PERMOHONAN='+id_permohonan+'&scrolPosition='+lokasi+'&rowCss='+rowCss+'&BIL='+BIL);
		}
	}
}

//arief add Saksi
function simpanSaksi(id_bikehadiran,id_perbicaraan,id_permohonan,lokasi,rowCss,BIL)
{
	var nama = document.getElementById("NAMA_SAKSI_"+id_bikehadiran);
	if(nama.value == "")
	{
		alert('Sila masukkan nama!');
		nama.focus();
		return;
	}
	else
	{
		if(id_bikehadiran == "")
		{
			doDivAjaxCall$formname('view_saksi','tambah_saksi','ID_BIKEHADIRAN='+id_bikehadiran+'&ID_PERBICARAAN='+id_perbicaraan+'&ID_PERMOHONAN='+id_permohonan+'&scrolPosition='+lokasi+'&rowCss='+rowCss+'&BIL='+BIL);
		}
		else
		{
			doDivAjaxCall$formname('rowSaksi_'+id_bikehadiran,'saveSaksi','ID_BIKEHADIRAN='+id_bikehadiran+'&ID_PERBICARAAN='+id_perbicaraan+'&ID_PERMOHONAN='+id_permohonan+'&scrolPosition='+lokasi+'&rowCss='+rowCss+'&BIL='+BIL);
		}
	}
}

//arief add Tidak Hadir
function simpanTidakHadir(id_bitidakhadir,id_perbicaraan,id_permohonan,lokasi,rowCss,BIL)
{
	var nama = document.getElementById("NAMA_TIDAKHADIR_"+id_bitidakhadir);
	if(nama.value == "")
	{
		alert('Sila masukkan nama!');
		nama.focus();
		return;
	}
	else
	{
		if(id_bitidakhadir == "")
		{
			doDivAjaxCall$formname('view_tidakhadir','tambah_tidakhadir','ID_BITIDAKHADIR='+id_bitidakhadir+'&ID_PERBICARAAN='+id_perbicaraan+'&ID_PERMOHONAN='+id_permohonan+'&scrolPosition='+lokasi+'&rowCss='+rowCss+'&BIL='+BIL);
		}
		else
		{
			doDivAjaxCall$formname('rowTidakHadir_'+id_bitidakhadir,'saveTidaktHadir','ID_BITIDAKHADIR='+id_bitidakhadir+'&ID_PERBICARAAN='+id_perbicaraan+'&ID_PERMOHONAN='+id_permohonan+'&scrolPosition='+lokasi+'&rowCss='+rowCss+'&BIL='+BIL);
		}
	}
}


function showTDK(skrinName,mode,statusOB)
{
	if(statusOB=="3")
	{
		document.getElementById(skrinName+"NO_KP_LAMA").value = "TDK";
	}
	else
	{
		document.getElementById(skrinName+"NO_KP_LAMA").value = "";
	}
}

function checkNOKPBARU(fieldKP1,fieldKP2,fieldKP3,fieldKPLama,fieldKPLain)
{
	var bool_check = true;
	if(document.getElementById(fieldKP1).value=="" && document.getElementById(fieldKP2).value=="" && document.getElementById(fieldKP3).value==""
	&& document.getElementById(fieldKPLama).value=="" && document.getElementById(fieldKPLain).value=="")
	{
		alert("Masukkan Salah Satu Jenis Pengenalan!");
		document.getElementById(fieldKP1).focus();
		bool_check = false;
	}
	else if((document.getElementById(fieldKP1).value!="" || document.getElementById(fieldKP2).value!="" || document.getElementById(fieldKP3).value!="")
	&& document.getElementById(fieldKP1).value=="")
	{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById(fieldKP1).focus();
		bool_check = false;
	}
	else if((document.getElementById(fieldKP1).value!="" || document.getElementById(fieldKP2).value!="" || document.getElementById(fieldKP3).value!="")
	&& document.getElementById(fieldKP2).value=="")
	{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById(fieldKP2).focus();
		bool_check = false;
	}
	else if((document.getElementById(fieldKP1).value!="" || document.getElementById(fieldKP2).value!="" || document.getElementById(fieldKP3).value!="")
	&& document.getElementById(fieldKP3).value=="")
	{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById(fieldKP3).focus();
		bool_check = false;
	}
	else if((document.getElementById(fieldKP1).value!="" || document.getElementById(fieldKP2).value!="" || document.getElementById(fieldKP3).value!="")
	&& document.getElementById(fieldKP1).value.length != document.getElementById(fieldKP1).maxLength)
	{
		alert("Format MyID Tidak Tepat!");
		document.getElementById(fieldKP1).focus();
		bool_check = false;
	}
	else if((document.getElementById(fieldKP1).value!="" || document.getElementById(fieldKP2).value!="" || document.getElementById(fieldKP3).value!="")
	&& getDOBByIC_V3(document.getElementById(fieldKP1).value) == false)
	{
		alert("Format Tarikh Lahir MyID Tidak Tepat!");
		document.getElementById(fieldKP1).focus();
		bool_check = false;
	}
	else if((document.getElementById(fieldKP1).value!="" || document.getElementById(fieldKP2).value!="" || document.getElementById(fieldKP3).value!="")
	&& document.getElementById(fieldKP2).value.length != document.getElementById(fieldKP2).maxLength)
	{
		alert("Format MyID Tidak Tepat!");
		document.getElementById(fieldKP2).focus();
		bool_check = false;
	}
	else if((document.getElementById(fieldKP1).value!="" || document.getElementById(fieldKP2).value!="" || document.getElementById(fieldKP3).value!="")
	&& document.getElementById(fieldKP3).value.length != document.getElementById(fieldKP3).maxLength)
	{
		alert("Format MyID Tidak Tepat!");
		document.getElementById(fieldKP3).focus();
		bool_check = false;
	}
	return bool_check;
}


function valSimpanKeputusan(skrinName)
{
	var bool_check = true;
	var jp = document.getElementById(skrinName+"FLAG_JENIS_KEPUTUSAN").value;
	//alert('jp : '+jp);
	if(document.getElementById(skrinName+"ID_UNITPSK").value=="")
	{
		alert("Masukkan Pegawai Pengendali!");
		document.getElementById(skrinName+"ID_UNITPSK").focus();
		bool_check = false;
	}
	else if(jp=="")
	{
		alert("Masukkan Keputusan Perbicaraan!");
		document.getElementById(skrinName+"FLAG_JENIS_KEPUTUSAN").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"TARIKH_PERINTAH").value=="")
	{
		if(jp == "0")
		{
			alert("Masukkan Tarikh Perintah!");
		}
		else if(jp == "1" || jp == "2")
		{
			alert("Masukkan Tarikh Perbicaraan Terakhir!");
		}
		document.getElementById(skrinName+"TARIKH_PERINTAH").focus();
		bool_check = false;
	}
	else if(isValidDate_V3(document.getElementById(skrinName+"TARIKH_PERINTAH").value)==false)
	{
		alert("Format Tarikh Tidak Tepat!");
		document.getElementById(skrinName+"TARIKH_PERINTAH").focus();
		bool_check = false;
	}
	/*
	else if(document.getElementById("checkCatatan"+skrinName).value=="Y")
	{
		alert("Masih terdapat 7 daripada 8 keterangan kehadiran waris yang belum dicatat!");
		bool_check = false;
	}
	*/
	else if(jp=="0" && document.getElementById(skrinName+"CHECK_KIV").value!="" && document.getElementById(skrinName+"DATE_KIV").value=="")
	{
		alert("Masukkan Tarikh KIV!");
		document.getElementById(skrinName+"DATE_KIV").focus();
		bool_check = false;
	}
	else if(jp=="0" && document.getElementById(skrinName+"CHECK_KIV").value!=""  && isValidDate_V3(document.getElementById(skrinName+"DATE_KIV").value)==false)
	{
		alert("Format Tarikh Tidak Tepat!");
		document.getElementById(skrinName+"DATE_KIV").focus();
		bool_check = false;
	}
	else if(jp=="0" && document.getElementById(skrinName+"CHECK_KIV").value!="" && document.getElementById(skrinName+"CATATAN_KIV").value=="")
	{
		alert("Masukkan Catatan KIV!");
		document.getElementById(skrinName+"CATATAN_KIV").focus();
		bool_check = false;
	}/*
	else if(jp=="0" && document.getElementById(skrinName+"JUMLAH_BAYARAN24").value=="")
	{
		alert("Masukkan Jumlah Bayaran Perintah!");
		document.getElementById(skrinName+"JUMLAH_BAYARAN24").focus();
		bool_check = false;
	}
	else if(jp=="0" && document.getElementById(skrinName+"NO_RESIT24").value=="")
	{
		alert("Masukkan No. Resit Bayaran Perintah!");
		document.getElementById(skrinName+"NO_RESIT24").focus();
		bool_check = false;
	}
	else if(jp=="0" && document.getElementById(skrinName+"TARIKH_BAYARAN24").value=="")
	{
		alert("Masukkan Tarikh Bayaran Perintah!");
		document.getElementById(skrinName+"TARIKH_BAYARAN24").focus();
		bool_check = false;
	}*/
	else if(jp=="0" && document.getElementById(skrinName+"TARIKH_BAYARAN24").value!="" && isValidDate_V3(document.getElementById(skrinName+"TARIKH_BAYARAN24").value)==false)
	{
		alert("Format Tarikh Tidak Tepat!");
		document.getElementById(skrinName+"TARIKH_BAYARAN24").focus();
		bool_check = false;
	}

	else if(jp=="0" && document.getElementById(skrinName+"CATATAN_KEPUTUSAN_PERBICARAAN").value == "")
	{
		alert("Masukkan Catatan Keputusan Perbicaraan!");
		document.getElementById(skrinName+"CATATAN_KEPUTUSAN_PERBICARAAN").focus();
		bool_check = false;
	}

	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value=="")
	{
		alert("Masukkan Alasan Tangguh Perbicaraan!");
		document.getElementById(skrinName+"FLAG_TANGGUH").focus();
		bool_check = false;
	}
	else if(jp=="2" && document.getElementById(skrinName+"FLAG_BATAL").value=="")
	{
		alert("Masukkan Alasan Batal Perbicaraan!");
		document.getElementById(skrinName+"FLAG_BATAL").focus();
		bool_check = false;
	}

	//ROS
	//MAHKAMAH TINGGI
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "5" && document.getElementById(skrinName+"TARIKH_MOHON").value=="")
	{
		alert("Masukkan Tarikh Rujukan!");
		document.getElementById(skrinName+"TARIKH_MOHON").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "5" && isValidDate_V3(document.getElementById(skrinName+"TARIKH_MOHON").value)==false)
	{
		alert("Format Tarikh Tidak Tepat!");
		document.getElementById(skrinName+"TARIKH_MOHON").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "5" && document.getElementById(skrinName+"JENIS_RUJUKAN").value=="")
	{
		alert("Masukkan Jenis Rujukan!");
		document.getElementById(skrinName+"JENIS_RUJUKAN").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "5" && document.getElementById(skrinName+"JENIS_RUJUKAN").value=="2" && document.getElementById(skrinName+"FLAG_RUJUKAN").value=="")
	{
		alert("Masukkan Rujukan Ruler Of The State!");
		document.getElementById(skrinName+"FLAG_RUJUKAN").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "5" &&
	(document.getElementById(skrinName+"JENIS_RUJUKAN").value=="1" || (document.getElementById(skrinName+"JENIS_RUJUKAN").value=="2" && document.getElementById(skrinName+"FLAG_RUJUKAN").value=="1"))
	 && document.getElementById(skrinName+"ID_NEGERIMAHKAMAH").value=="")
	{
		alert("Masukkan Negeri Mahkamah!");
		document.getElementById(skrinName+"ID_NEGERIMAHKAMAH").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "5" &&
	(document.getElementById(skrinName+"JENIS_RUJUKAN").value=="1" || (document.getElementById(skrinName+"JENIS_RUJUKAN").value=="2" && document.getElementById(skrinName+"FLAG_RUJUKAN").value=="1")) && document.getElementById(skrinName+"ID_MAHKAMAH").value=="")
	{
		alert("Masukkan Mahkamah!");
		document.getElementById(skrinName+"ID_MAHKAMAH").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "5" &&
	(document.getElementById(skrinName+"JENIS_RUJUKAN").value=="1" || (document.getElementById(skrinName+"JENIS_RUJUKAN").value=="2" && document.getElementById(skrinName+"FLAG_RUJUKAN").value=="2")) && document.getElementById(skrinName+"NAMA_PEJABAT").value=="")
	{
		alert("Masukkan Nama Pejabat Mufti!");
		document.getElementById(skrinName+"NAMA_PEJABAT").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "5" &&
	(document.getElementById(skrinName+"JENIS_RUJUKAN").value=="1" || (document.getElementById(skrinName+"JENIS_RUJUKAN").value=="2" && document.getElementById(skrinName+"FLAG_RUJUKAN").value=="2")) && document.getElementById(skrinName+"ALAMAT1").value=="")
	{
		alert("Masukkan Alamat Pejabat Mufti!");
		document.getElementById(skrinName+"ALAMAT1").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "5" &&
	(document.getElementById(skrinName+"JENIS_RUJUKAN").value=="1" || (document.getElementById(skrinName+"JENIS_RUJUKAN").value=="2" && document.getElementById(skrinName+"FLAG_RUJUKAN").value=="2")) && document.getElementById(skrinName+"POSKOD").value=="")
	{
		alert("Masukkan Poskod Pejabat Mufti!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "5" &&
	(document.getElementById(skrinName+"JENIS_RUJUKAN").value=="1" || (document.getElementById(skrinName+"JENIS_RUJUKAN").value=="2" && document.getElementById(skrinName+"FLAG_RUJUKAN").value=="2")) && document.getElementById(skrinName+"POSKOD").value.length != document.getElementById(skrinName+"POSKOD").maxLength)
	{
		alert("Format Poskod Tidak Tepat!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "5" &&
	(document.getElementById(skrinName+"JENIS_RUJUKAN").value=="1" || (document.getElementById(skrinName+"JENIS_RUJUKAN").value=="2" && document.getElementById(skrinName+"FLAG_RUJUKAN").value=="2")) && document.getElementById(skrinName+"ID_NEGERI").value=="")
	{
		alert("Masukkan Negeri Pejabat Mufti!");
		document.getElementById(skrinName+"ID_NEGERI").focus();
		bool_check = false;
	}


	//KOLETERAL
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "6" && document.getElementById(skrinName+"TARIKH_PERAKUAN").value=="")
	{
		alert("Masukkan Tarikh Perakuan!");
		document.getElementById(skrinName+"TARIKH_PERAKUAN").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "6" && isValidDate_V3(document.getElementById(skrinName+"TARIKH_PERAKUAN").value)==false)
	{
		alert("Format Tarikh Tidak Tepat!");
		document.getElementById(skrinName+"TARIKH_PERAKUAN").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "6" && document.getElementById(skrinName+"TARIKH_BICARA").value=="")
	{
		alert("Masukkan Tarikh Bicara!");
		document.getElementById(skrinName+"TARIKH_BICARA").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "6" && isValidDate_V3(document.getElementById(skrinName+"TARIKH_BICARA").value)==false)
	{
		alert("Format Tarikh Tidak Tepat!");
		document.getElementById(skrinName+"TARIKH_BICARA").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "6" && document.getElementById(skrinName+"MASA_BICARA").value=="")
	{
		alert("Masukkan Masa Bicara!");
		document.getElementById(skrinName+"MASA_BICARA").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "6" &&
	document.getElementById(skrinName+"MASA_BICARA").value != "" && document.getElementById(skrinName+"MASA_BICARA").value.length != document.getElementById(skrinName+"MASA_BICARA").maxLength)
	{
		alert("Format Waktu Tidak Tepat!");
		document.getElementById(skrinName+"MASA_BICARA").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"FLAG_TANGGUH").value == "6" && document.getElementById(skrinName+"ID_PEJABATMAHKAMAH").value=="")
	{
		alert("Masukkan Tempat Bicara!");
		document.getElementById(skrinName+"ID_PEJABATMAHKAMAH").focus();
		bool_check = false;
	}
	else if(jp=="1" && document.getElementById(skrinName+"SEBAB_TANGGUH").value == "")
	{
		alert("Masukkan Keterangan Tangguh!");
		document.getElementById(skrinName+"SEBAB_TANGGUH").focus();
		bool_check = false;
	}





	//BATAL
	else if(jp=="2" && document.getElementById(skrinName+"FLAG_BATAL").value == "1" && document.getElementById(skrinName+"JENIS_KELUAR_PERINTAH").value=="")
	{
		alert("Masukkan Kod Keluar Perintah!");
		document.getElementById(skrinName+"JENIS_KELUAR_PERINTAH").focus();
		bool_check = false;
	}
	else if(jp=="2" && document.getElementById(skrinName+"FLAG_BATAL").value == "1" && document.getElementById(skrinName+"ID_NEGERIMAHKAMAH").value=="")
	{
		alert("Masukkan Negeri Mahkamah!");
		document.getElementById(skrinName+"ID_NEGERIMAHKAMAH").focus();
		bool_check = false;
	}
	else if(jp=="2" && document.getElementById(skrinName+"FLAG_BATAL").value == "1" && document.getElementById(skrinName+"ID_PEJABATMAHKAMAH").value=="")
	{
		alert("Masukkan Mahkamah!");
		document.getElementById(skrinName+"ID_PEJABATMAHKAMAH").focus();
		bool_check = false;
	}
	else if(jp=="2" && document.getElementById(skrinName+"SEBAB_BATAL").value == "")
	{
		alert("Masukkan Alasan Pembatalan!");
		document.getElementById(skrinName+"SEBAB_BATAL").focus();
		bool_check = false;
	}


	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}

function valSimpanTukarPegawai(skrinName)
{
	var bool_check = true;


	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}

function valSimpanTukarPegawaiMultiple(skrinName)
{
	var bool_check = true;

	if(document.getElementById(skrinName+"ID_NEGERIPEGAWAIBARU").value=="")
	{
		alert("Sila pilih negeri pegawai ganti!");
		document.getElementById(skrinName+"ID_NEGERIPEGAWAIBARU").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_PEGAWAIBARU").value=="")
	{
		alert("Sila pilih pegawai ganti!");
		document.getElementById(skrinName+"ID_PEGAWAIBARU").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JumlahPerbicaraan").value=="0")
	{
		alert("Sila pilih perbicaraan!");
		document.getElementById(skrinName+"JumlahPerbicaraan").focus();
		bool_check = false;
	}

	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}


function valSimpanbantahan(skrinName)
{
	var bool_check = true;

	if(document.getElementById(skrinName+"FLAG_BANTAHAN").value == "Y" && document.getElementById(skrinName+"KETERANGAN_BANTAHAN").value=="")
	{
		alert("Masukkan keterangan bantahan!");
		document.getElementById(skrinName+"KETERANGAN_BANTAHAN").focus();
		bool_check = false;
	}

	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}


function valSimpanTukarPegawaiMultipleTolak(skrinName)
{
	var bool_check = true;

	if(document.getElementById(skrinName+"JumlahPerbicaraan").value=="0")
	{
		alert("Sila pilih perbicaraan!");
		document.getElementById(skrinName+"JumlahPerbicaraan").focus();
		bool_check = false;
	}

	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}

function valSimpanob(skrinName)
{
	//alert('skrinName : '+skrinName);
	//validation kena hardcode, nak kejar masa tak sempat nk buat dynamic
	var bool_check = true;

	if(document.getElementById(skrinName+"ID_TARAFKPTG").value=="")
	{
		alert("Masukkan Taraf Kepentingan!");
		document.getElementById(skrinName+"ID_TARAFKPTG").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JENIS_PEMIUTANG").value=="")
	{
		alert("Masukkan Jenis Ob!");
		document.getElementById(skrinName+"JENIS_PEMIUTANG").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JENIS_PEMIUTANG").value == "2")
	{
		//alert('1'+skrinName);
		var bool_check_ic = checkNOKPBARU(skrinName+"NO_KP_BARU1",skrinName+"NO_KP_BARU2",skrinName+"NO_KP_BARU3",skrinName+"NO_KP_LAMA",skrinName+"NO_KP_LAIN");
		bool_check = bool_check_ic;
		//alert('2');
	}
	else if(document.getElementById(skrinName+"NAMA_OB").value=="")
	{
		alert("Masukkan Nama Ob!");
		document.getElementById(skrinName+"NAMA_OB").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JENIS_PEMIUTANG").value == "2" && document.getElementById(skrinName+"UMUR").value=="")
	{
		alert("Masukkan Umur!");
		document.getElementById(skrinName+"UMUR").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ALAMAT_1").value=="")
	{
		alert("Masukkan Alamat!");
		document.getElementById(skrinName+"ALAMAT_1").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD").value=="")
	{
		alert("Masukkan Poskod!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD").value.length != document.getElementById(skrinName+"POSKOD").maxLength)
	{
		alert("Format Poskod Tidak Tepat!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_NEGERI").value=="")
	{
		alert("Masukkan Negeri!");
		document.getElementById(skrinName+"ID_NEGERI").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ALAMAT1_SURAT").value=="")
	{
		alert("Masukkan Alamat Surat Menyurat!");
		document.getElementById(skrinName+"ALAMAT1_SURAT").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD_SURAT").value=="")
	{
		alert("Masukkan Poskod Surat Menyurat!");
		document.getElementById(skrinName+"POSKOD_SURAT").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD_SURAT").value.length != document.getElementById(skrinName+"POSKOD_SURAT").maxLength)
	{
		alert("Format Poskod Tidak Tepat!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_NEGERISURAT").value=="")
	{
		alert("Masukkan Negeri Surat Menyurat!");
		document.getElementById(skrinName+"ID_NEGERISURAT").focus();
		bool_check = false;
	}


	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}

function valSimpanhtaahx(skrinName)
{
	return valSimpanhtaah(skrinName);
}

function valSimpanhtaah(skrinName)
{
	var bool_check = true;


	if(document.getElementById(skrinName+"ID_NEGERI").value=="")
	{
		alert("Masukkan Negeri!");
		document.getElementById(skrinName+"ID_NEGERI").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_DAERAH").value=="")
	{
		alert("Masukkan Daerah!");
		document.getElementById(skrinName+"ID_DAERAH").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_MUKIM").value=="")
	{
		alert("Masukkan Mukim!");
		document.getElementById(skrinName+"ID_MUKIM").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JENIS_HTA").value=="T" && document.getElementById(skrinName+"FLAG_KATEGORI_HTA").value == "")
	{
		alert("Masukkan Kategori Harta!");
		document.getElementById(skrinName+"FLAG_KATEGORI_HTA").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JENIS_HTA").value=="Y" && document.getElementById(skrinName+"ID_JENISHM").value == "")
	{
		alert("Masukkan Jenis Hakmilik!");
		document.getElementById(skrinName+"ID_JENISHM").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JENIS_HTA").value=="Y" && document.getElementById(skrinName+"NO_HAKMILIK").value == "")
	{
		alert("Masukkan No. Hakmilik!");
		document.getElementById(skrinName+"NO_HAKMILIK").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JENIS_HTA").value=="Y" && document.getElementById(skrinName+"NO_PT").value == "")
	{
		alert("Masukkan No. Lot Atau No. PT!");
		document.getElementById(skrinName+"NO_PT").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"BA_SIMATI").value=="")
	{
		alert("Masukkan Bahagian Simati!");
		document.getElementById(skrinName+"BA_SIMATI").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"BB_SIMATI").value!="" && document.getElementById(skrinName+"BA_SIMATI").value!="" && (parseInt(document.getElementById(skrinName+"BA_SIMATI").value) / parseInt(document.getElementById(skrinName+"BB_SIMATI").value)) > 1)
	{
		alert("Pastikan Bahagian Simati Kurang Daripada 1/1!");
		document.getElementById(skrinName+"BA_SIMATI").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JENIS_TNH").value=="")
	{
		alert("Masukkan Jenis Tanah!");
		document.getElementById(skrinName+"JENIS_TNH").focus();
		bool_check = false;
	}



	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}


function valSimpanha(skrinName)
{
	var bool_check = true;

	if(document.getElementById(skrinName+"ID_JENISHA").value=="" || document.getElementById(skrinName+"ID_JENISHA").value=="0")
	{
		alert("Masukkan Jenis Harta Alih!");
		document.getElementById(skrinName+"ID_JENISHA").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="1" && document.getElementById(skrinName+"JENAMA").value=="")
	{
		alert("Masukkan Agensi!");
		document.getElementById(skrinName+"JENAMA").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_JENISHA").value=="1" && document.getElementById(skrinName+"NAMA_SAHAM").value=="")
	{
		alert("Masukkan Nama Saham!");
		document.getElementById(skrinName+"NAMA_SAHAM").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_JENISHA").value=="1" && document.getElementById(skrinName+"NO_DAFTAR").value=="")
	{
		alert("Masukkan No. Ahli!");
		document.getElementById(skrinName+"NO_DAFTAR").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="2" && document.getElementById(skrinName+"JENAMA").value=="")
	{
		alert("Masukkan Agensi!");
		document.getElementById(skrinName+"JENAMA").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_JENISHA").value=="2" && document.getElementById(skrinName+"NO_DAFTAR").value=="")
	{
		alert("Masukkan No. Akaun!");
		document.getElementById(skrinName+"NO_DAFTAR").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="3" && document.getElementById(skrinName+"JENAMA").value=="")
	{
		alert("Masukkan Jenis & Jenama!");
		document.getElementById(skrinName+"JENAMA").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_JENISHA").value=="3" && document.getElementById(skrinName+"NO_DAFTAR").value=="")
	{
		alert("Masukkan No. Daftar!");
		document.getElementById(skrinName+"NO_DAFTAR").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="4" && document.getElementById(skrinName+"JENAMA").value=="")
	{
		alert("Masukkan No. Hakmilik, No. Lot dan Mukim!");
		document.getElementById(skrinName+"JENAMA").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_JENISHA").value=="4" && document.getElementById(skrinName+"NO_DAFTAR").value=="")
	{
		alert("Masukkan No. Rujukan UPT!");
		document.getElementById(skrinName+"NO_DAFTAR").focus();
		bool_check = false;
	}


	else if(document.getElementById(skrinName+"ID_JENISHA").value=="5" && document.getElementById(skrinName+"JENAMA").value=="")
	{
		alert("Masukkan Agensi!");
		document.getElementById(skrinName+"JENAMA").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_JENISHA").value=="5" && document.getElementById(skrinName+"NO_DAFTAR").value=="")
	{
		alert("Masukkan No. Polisi!");
		document.getElementById(skrinName+"NO_DAFTAR").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="6" && document.getElementById(skrinName+"BUTIRAN").value=="")
	{
		alert("Masukkan Butiran Peti Keselamatan!");
		document.getElementById(skrinName+"BUTIRAN").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_JENISHA").value=="6" && document.getElementById(skrinName+"JENAMA").value=="")
	{
		alert("Masukkan Agensi!");
		document.getElementById(skrinName+"JENAMA").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_JENISHA").value=="6" && document.getElementById(skrinName+"NO_DAFTAR").value=="")
	{
		alert("Masukkan No. Peti!");
		document.getElementById(skrinName+"NO_DAFTAR").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="7" && document.getElementById(skrinName+"BUTIRAN").value=="")
	{
		alert("Masukkan Lokasi Harta!");
		document.getElementById(skrinName+"BUTIRAN").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="8" && document.getElementById(skrinName+"BUTIRAN").value=="")
	{
		alert("Masukkan Butiran Tuntutan!");
		document.getElementById(skrinName+"BUTIRAN").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="9" && document.getElementById(skrinName+"BUTIRAN").value=="")
	{
		alert("Masukkan Butiran Senjata Api!");
		document.getElementById(skrinName+"BUTIRAN").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="10" && document.getElementById(skrinName+"BUTIRAN").value=="")
	{
		alert("Masukkan Butiran Barang Berharga!");
		document.getElementById(skrinName+"BUTIRAN").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="11" && document.getElementById(skrinName+"BUTIRAN").value=="")
	{
		alert("Masukkan Butiran Ternakan!");
		document.getElementById(skrinName+"BUTIRAN").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="12" && document.getElementById(skrinName+"BUTIRAN").value=="")
	{
		alert("Masukkan Harta!");
		document.getElementById(skrinName+"BUTIRAN").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"ID_JENISHA").value=="98" && document.getElementById(skrinName+"BUTIRAN").value=="")
	{
		alert("Masukkan Butiran Nilaian Harta!");
		document.getElementById(skrinName+"BUTIRAN").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_JENISHA").value=="98" && document.getElementById(skrinName+"NO_DAFTAR").value=="")
	{
		alert("Masukkan No. Rujukan!");
		document.getElementById(skrinName+"NO_DAFTAR").focus();
		bool_check = false;
	}

	else if(document.getElementById(skrinName+"BA_SIMATI").value=="")
	{
		alert("Masukkan Bahagian Simati!");
		document.getElementById(skrinName+"BA_SIMATI").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"BB_SIMATI").value!="" && document.getElementById(skrinName+"BA_SIMATI").value!="" && (parseInt(document.getElementById(skrinName+"BA_SIMATI").value) / parseInt(document.getElementById(skrinName+"BB_SIMATI").value)) > 1)
	{
		alert("Pastikan Bahagian Simati Kurang Daripada 1/1!");
		document.getElementById(skrinName+"BA_SIMATI").focus();
		bool_check = false;
	}


	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}




function valSimpanpenghutang(skrinName)
{
	//alert('skrinName : '+skrinName);
	//validation kena hardcode, nak kejar masa tak sempat nk buat dynamic
	var bool_check = true;

	if(document.getElementById(skrinName+"JENIS_PENGHUTANG").value=="")
	{
		alert("Masukkan Jenis Penghutang!");
		document.getElementById(skrinName+"JENIS_PENGHUTANG").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JENIS_PENGHUTANG").value == "2")
	{
		//alert('1'+skrinName);
		var bool_check_ic = checkNOKPBARU(skrinName+"NO_KP_BARU1",skrinName+"NO_KP_BARU2",skrinName+"NO_KP_BARU3",skrinName+"NO_KP_LAMA",skrinName+"NO_KP_LAIN");
		bool_check = bool_check_ic;
		//alert('2');
	}
	else if(document.getElementById(skrinName+"NAMA_PENGHUTANG").value=="")
	{
		alert("Masukkan Nama Penghutang!");
		document.getElementById(skrinName+"NAMA_PENGHUTANG").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JUMLAH_HUTANG").value=="")
	{
		alert("Masukkan Nilai Hutang!");
		document.getElementById(skrinName+"JUMLAH_HUTANG").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ALAMAT_1").value=="")
	{
		alert("Masukkan Alamat!");
		document.getElementById(skrinName+"ALAMAT_1").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD").value=="")
	{
		alert("Masukkan Poskod!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD").value.length != document.getElementById(skrinName+"POSKOD").maxLength)
	{
		alert("Format Poskod Tidak Tepat!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_NEGERI").value=="")
	{
		alert("Masukkan Negeri!");
		document.getElementById(skrinName+"ID_NEGERI").focus();
		bool_check = false;
	}

	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}

function valSimpanpeguam(skrinName)
{
	//alert('skrinName : '+skrinName);
	//validation kena hardcode, nak kejar masa tak sempat nk buat dynamic
	var bool_check = true;

	if(document.getElementById(skrinName+"NAMA_FIRMA").value=="")
	{
		alert("Masukkan Nama Firma!");
		document.getElementById(skrinName+"NAMA_FIRMA").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"NO_RUJUKAN_FIRMA").value=="")
	{
		alert("Masukkan No. Rujukan Firma!");
		document.getElementById(skrinName+"NO_RUJUKAN_FIRMA").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ALAMAT1").value=="")
	{
		alert("Masukkan Alamat!");
		document.getElementById(skrinName+"ALAMAT1").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD").value=="")
	{
		alert("Masukkan Poskod!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD").value.length != document.getElementById(skrinName+"POSKOD").maxLength)
	{
		alert("Format Poskod Tidak Tepat!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_NEGERI").value=="")
	{
		alert("Masukkan Negeri!");
		document.getElementById(skrinName+"ID_NEGERI").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"NO_TEL").value=="")
	{
		alert("Masukkan No Telefon!");
		document.getElementById(skrinName+"NO_TEL").focus();
		bool_check = false;
	}


	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}



function valSimpanpemiutang(skrinName)
{
	//alert('skrinName : '+skrinName);
	//validation kena hardcode, nak kejar masa tak sempat nk buat dynamic
	var bool_check = true;

	if(document.getElementById(skrinName+"JENIS_PEMIUTANG").value=="")
	{
		alert("Masukkan Jenis Pemiutang!");
		document.getElementById(skrinName+"JENIS_PEMIUTANG").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"JENIS_PEMIUTANG").value == "2")
	{
		//alert('1'+skrinName);
		var bool_check_ic = checkNOKPBARU(skrinName+"NO_KP_BARU1",skrinName+"NO_KP_BARU2",skrinName+"NO_KP_BARU3",skrinName+"NO_KP_LAMA",skrinName+"NO_KP_LAIN");
		bool_check = bool_check_ic;
		//alert('2');
	}
	else if(document.getElementById(skrinName+"NAMA_OB").value=="")
	{
		alert("Masukkan Nama Pemiutang!");
		document.getElementById(skrinName+"NAMA_OB").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"NILAI_HUTANG").value=="")
	{
		alert("Masukkan Nilai Hutang!");
		document.getElementById(skrinName+"NILAI_HUTANG").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ALAMAT_1").value=="")
	{
		alert("Masukkan Alamat!");
		document.getElementById(skrinName+"ALAMAT_1").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD").value=="")
	{
		alert("Masukkan Poskod!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD").value.length != document.getElementById(skrinName+"POSKOD").maxLength)
	{
		alert("Format Poskod Tidak Tepat!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_NEGERI").value=="")
	{
		alert("Masukkan Negeri!");
		document.getElementById(skrinName+"ID_NEGERI").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ALAMAT1_SURAT").value=="")
	{
		alert("Masukkan Alamat Surat Menyurat!");
		document.getElementById(skrinName+"ALAMAT1_SURAT").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD_SURAT").value=="")
	{
		alert("Masukkan Poskod Surat Menyurat!");
		document.getElementById(skrinName+"POSKOD_SURAT").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD_SURAT").value.length != document.getElementById(skrinName+"POSKOD_SURAT").maxLength)
	{
		alert("Format Poskod Tidak Tepat!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_NEGERISURAT").value=="")
	{
		alert("Masukkan Negeri Surat Menyurat!");
		document.getElementById(skrinName+"ID_NEGERISURAT").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"BUTIRAN_HUTANG").value=="")
	{
		alert("Masukkan Butiran Hutang!");
		document.getElementById(skrinName+"BUTIRAN_HUTANG").focus();
		bool_check = false;
	}


	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}


function valSimpanpemohon(skrinName)
{
	//alert('skrinName : '+skrinName);
	//validation kena hardcode, nak kejar masa tak sempat nk buat dynamic
	var bool_check = true;

	if(document.getElementById(skrinName+"ID_TARAFKPTG").value=="")
	{
		alert("Masukkan Taraf Kepentingan!");
		document.getElementById(skrinName+"ID_TARAFKPTG").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"STATUS_PEMOHON").value=="")
	{
		alert("Masukkan Jenis Pemohon!");
		document.getElementById(skrinName+"STATUS_PEMOHON").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"STATUS_PEMOHON").value == "2")
	{
		//alert('1'+skrinName);
		var bool_check_ic = checkNOKPBARU(skrinName+"NO_KP_BARU1",skrinName+"NO_KP_BARU2",skrinName+"NO_KP_BARU3",skrinName+"NO_KP_LAMA",skrinName+"NO_KP_LAIN");
		bool_check = bool_check_ic;
		//alert('2');
	}
	else if(document.getElementById(skrinName+"NAMA_PEMOHON").value=="")
	{
		alert("Masukkan Nama Pemohon!");
		document.getElementById(skrinName+"NAMA_PEMOHON").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ALAMAT_1").value=="")
	{
		alert("Masukkan Alamat!");
		document.getElementById(skrinName+"ALAMAT_1").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD").value=="")
	{
		alert("Masukkan Poskod!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD").value.length != document.getElementById(skrinName+"POSKOD").maxLength)
	{
		alert("Format Poskod Tidak Tepat!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_NEGERI").value=="")
	{
		alert("Masukkan Negeri!");
		document.getElementById(skrinName+"ID_NEGERI").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ALAMAT1_SURAT").value=="")
	{
		alert("Masukkan Alamat Surat Menyurat!");
		document.getElementById(skrinName+"ALAMAT1_SURAT").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD_SURAT").value=="")
	{
		alert("Masukkan Poskod Surat Menyurat!");
		document.getElementById(skrinName+"POSKOD_SURAT").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"POSKOD_SURAT").value.length != document.getElementById(skrinName+"POSKOD_SURAT").maxLength)
	{
		alert("Format Poskod Tidak Tepat!");
		document.getElementById(skrinName+"POSKOD").focus();
		bool_check = false;
	}
	else if(document.getElementById(skrinName+"ID_NEGERISURAT").value=="")
	{
		alert("Masukkan Negeri Surat Menyurat!");
		document.getElementById(skrinName+"ID_NEGERISURAT").focus();
		bool_check = false;
	}


	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}
function valSimpansimati(skrinName)
{
	var bool_check = true;
	bool_check = checkNOKPBARU(skrinName+"NO_KP_BARU1",skrinName+"NO_KP_BARU2",skrinName+"NO_KP_BARU3",skrinName+"NO_KP_LAMA",skrinName+"NO_KP_LAIN");

	if(bool_check == true)
	{
		if(document.getElementById(skrinName+"NAMA_SIMATI").value=="")
		{
			alert("Masukkan Nama Simati!");
			document.getElementById(skrinName+"NAMA_SIMATI").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"JENIS_WARGA").value=="")
		{
			alert("Masukkan Jenis Warganegara!");
			document.getElementById(skrinName+"JENIS_WARGA").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ID_BUKTIMATI").value=="")
		{
			alert("Masukkan Bukti Kematian!");
			document.getElementById(skrinName+"ID_BUKTIMATI").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"TARIKH_MATI").value=="")
		{
			alert("Masukkan Tarikh Kematian!");
			document.getElementById(skrinName+"TARIKH_MATI").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"WAKTU_KEMATIAN").value != "" && document.getElementById(skrinName+"WAKTU_KEMATIAN").value.length != document.getElementById(skrinName+"WAKTU_KEMATIAN").maxLength)
		{
			alert("Format Waktu Tidak Tepat!");
			document.getElementById(skrinName+"WAKTU_KEMATIAN").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"TEMPAT_MATI").value=="")
		{
			alert("Masukkan Tempat Kematian!");
			document.getElementById(skrinName+"TEMPAT_MATI").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"SEBAB_MATI").value=="")
		{
			alert("Masukkan Sebab Kematian!");
			document.getElementById(skrinName+"SEBAB_MATI").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"NAMA_SIMATI").value=="")
		{
			alert("Masukkan Nama Simati!");
			document.getElementById(skrinName+"NAMA_SIMATI").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ALAMAT_1").value=="")
		{
			alert("Masukkan Alamat!");
			document.getElementById(skrinName+"ALAMAT_1").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"POSKOD").value=="")
		{
			alert("Masukkan Poskod!");
			document.getElementById(skrinName+"POSKOD").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"POSKOD").value.length != document.getElementById(skrinName+"POSKOD").maxLength)
		{
			alert("Format Poskod Tidak Tepat!");
			document.getElementById(skrinName+"POSKOD").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ID_NEGERI").value=="")
		{
			alert("Masukkan Negeri!");
			document.getElementById(skrinName+"ID_NEGERI").focus();
			bool_check = false;
		}
	}


	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}

function valSimpanwaris(skrinName)
{
	var bool_check = true;

	bool_check = checkNOKPBARU(skrinName+"NO_KP_BARU1",skrinName+"NO_KP_BARU2",skrinName+"NO_KP_BARU3",skrinName+"NO_KP_LAMA",skrinName+"NO_KP_LAIN");
	//tutup sementara

	if(bool_check == true)
	{
		if(document.getElementById(skrinName+"NAMA_OB").value=="")
		{
			alert("Masukkan Nama Waris!");
			document.getElementById(skrinName+"NAMA_OB").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ID_SAUDARA").value=="")
		{
			alert("Masukkan Jenis Tali Persaudaraan!");
			document.getElementById(skrinName+"ID_SAUDARA").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"STATUS_OB").value=="")
		{
			alert("Masukkan Status Waris!");
			document.getElementById(skrinName+"STATUS_OB").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"STATUS_HIDUP").value=="1" && document.getElementById(skrinName+"TARIKH_MATI").value=="")
		{
			alert("Masukkan Tarikh Kematian!");
			document.getElementById(skrinName+"TARIKH_MATI").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"STATUS_HIDUP").value=="1" &&  document.getElementById(skrinName+"WAKTU_KEMATIAN").value=="")
		{
			alert("Masukkan Waktu Kematian!");
			document.getElementById(skrinName+"WAKTU_KEMATIAN").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"STATUS_HIDUP").value=="1" && document.getElementById(skrinName+"WAKTU_KEMATIAN").value != "" && document.getElementById(skrinName+"WAKTU_KEMATIAN").value.length != document.getElementById(skrinName+"WAKTU_KEMATIAN").maxLength)
		{
			alert("Format Waktu Tidak Tepat!");
			document.getElementById(skrinName+"WAKTU_KEMATIAN").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"STATUS_HIDUP").value=="1" &&  document.getElementById(skrinName+"JENIS_WAKTU_KEMATIAN").value=="")
		{
			alert("Masukkan Jenis Waktu Kematian!");
			document.getElementById(skrinName+"JENIS_WAKTU_KEMATIAN").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ALAMAT_1").value=="")
		{
			alert("Masukkan Alamat!");
			document.getElementById(skrinName+"ALAMAT_1").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"POSKOD").value=="")
		{
			alert("Masukkan Poskod!");
			document.getElementById(skrinName+"POSKOD").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"POSKOD").value.length != document.getElementById(skrinName+"POSKOD").maxLength)
		{
			alert("Format Poskod Tidak Tepat!");
			document.getElementById(skrinName+"POSKOD").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ID_NEGERI").value=="")
		{
			alert("Masukkan Negeri!");
			document.getElementById(skrinName+"ID_NEGERI").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ALAMAT1_SURAT").value=="")
		{
			alert("Masukkan Alamat Surat Menyurat!");
			document.getElementById(skrinName+"ALAMAT1_SURAT").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"POSKOD_SURAT").value=="")
		{
			alert("Masukkan Poskod Surat Menyurat!");
			document.getElementById(skrinName+"POSKOD_SURAT").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"POSKOD_SURAT").value.length != document.getElementById(skrinName+"POSKOD_SURAT").maxLength)
		{
			alert("Format Poskod Tidak Tepat!");
			document.getElementById(skrinName+"POSKOD").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ID_NEGERISURAT").value=="")
		{
			alert("Masukkan Negeri Surat Menyurat!");
			document.getElementById(skrinName+"ID_NEGERISURAT").focus();
			bool_check = false;
		}
	}


	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}


function valSimpansaksi(skrinName)
{
	var bool_check = true;

	bool_check = checkNOKPBARU(skrinName+"NO_KP_BARU1",skrinName+"NO_KP_BARU2",skrinName+"NO_KP_BARU3",skrinName+"NO_KP_LAMA",skrinName+"NO_KP_LAIN");
	//tutup sementara

	if(bool_check == true)
	{
		if(document.getElementById(skrinName+"NAMA_OB").value=="")
		{
			alert("Masukkan Nama Saksi!");
			document.getElementById(skrinName+"NAMA_OB").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"UMUR").value=="")
		{
			alert("Masukkan Umur!");
			document.getElementById(skrinName+"UMUR").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ALAMAT_1").value=="")
		{
			alert("Masukkan Alamat!");
			document.getElementById(skrinName+"ALAMAT_1").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"POSKOD").value=="")
		{
			alert("Masukkan Poskod!");
			document.getElementById(skrinName+"POSKOD").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"POSKOD").value.length != document.getElementById(skrinName+"POSKOD").maxLength)
		{
			alert("Format Poskod Tidak Tepat!");
			document.getElementById(skrinName+"POSKOD").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ID_NEGERI").value=="")
		{
			alert("Masukkan Negeri!");
			document.getElementById(skrinName+"ID_NEGERI").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ALAMAT1_SURAT").value=="")
		{
			alert("Masukkan Alamat Surat Menyurat!");
			document.getElementById(skrinName+"ALAMAT1_SURAT").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"POSKOD_SURAT").value=="")
		{
			alert("Masukkan Poskod Surat Menyurat!");
			document.getElementById(skrinName+"POSKOD_SURAT").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"POSKOD_SURAT").value.length != document.getElementById(skrinName+"POSKOD_SURAT").maxLength)
		{
			alert("Format Poskod Tidak Tepat!");
			document.getElementById(skrinName+"POSKOD").focus();
			bool_check = false;
		}
		else if(document.getElementById(skrinName+"ID_NEGERISURAT").value=="")
		{
			alert("Masukkan Negeri Surat Menyurat!");
			document.getElementById(skrinName+"ID_NEGERISURAT").focus();
			bool_check = false;
		}
	}


	var bool_check_last = false;
	if(bool_check == true)
	{
		input_box = confirm("Adakah anda pasti?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}


function validateIC_V3(e,elmnt,content,nextElementID) {
	//if it is character, then remove it..
	var keycode;
	if(window.event)keycode = window.event.keyCode;
	else if (e) keycode = e.which;
	else return true;
	if((keycode >= 37 && keycode <= 40) || (keycode == 9)) return false;
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
	if (content.length == elmnt.maxLength)
	{
		goTo(nextElementID);
	}
}
function checkBuktiMati(skrinName,val)
{
	if(val == "1")
	{
		document.getElementById("row"+skrinName+"NO_SIJIL_MATI").style.display = "";
		document.getElementById("row"+skrinName+"TARIKH_SURAT_AKUAN").style.display = "none";
		document.getElementById(""+skrinName+"TARIKH_SURAT_AKUAN").value = "";
	}
	else if(val == "2")
	{
		document.getElementById("row"+skrinName+"TARIKH_SURAT_AKUAN").style.display = "";
		document.getElementById("row"+skrinName+"NO_SIJIL_MATI").style.display = "none";
		document.getElementById(""+skrinName+"NO_SIJIL_MATI").value = "";
	}
	else if(val == "3")
	{
		document.getElementById("row"+skrinName+"NO_SIJIL_MATI").style.display = "none";
		document.getElementById("row"+skrinName+"TARIKH_SURAT_AKUAN").style.display = "none";
		document.getElementById(""+skrinName+"TARIKH_SURAT_AKUAN").value = "";
		document.getElementById(""+skrinName+"NO_SIJIL_MATI").value = "";
	}
	else if(val == "4")
	{
		document.getElementById("row"+skrinName+"NO_SIJIL_MATI").style.display = "";
		document.getElementById("row"+skrinName+"TARIKH_SURAT_AKUAN").style.display = "none";
		document.getElementById(""+skrinName+"TARIKH_SURAT_AKUAN").value = "";
	}

}
function getDOBByIC_V3(content) {
	var bool_check = false;
	year = content.substring(0,2);
	if (year <=10) {
		year = parseInt(year) + 2000;
	} else {
		year = parseInt(year) + 1900
	}
	month = content.substring(2,4);
	day = content.substring(4,6);
	var date_str = day + '/' + month + '/' + year ;
	if(isValidDate_V3(date_str)==true)
	{
		bool_check = true;
	}
	return bool_check;
}

function getYear2Date(fieldDate1,fieldDate2,fieldAge)
{
	var dateString1 = document.getElementById(fieldDate1).value;
	var dateString2 = document.getElementById(fieldDate2).value;
	if(isValidDate_V3(dateString1)==true && isValidDate_V3(dateString2)==true)
	{
		var parts1 = dateString1.split("/");
		var year1 = parseInt(parts1[2], 10);

		var parts2 = dateString2.split("/");
		var year2 = parseInt(parts2[2], 10);

		document.getElementById(fieldAge).value = (year2 - year1);
	}
}

function isValidDate_V3(dateString)
{
	var parts = dateString.split("/");
	var day = parseInt(parts[0], 10);
	var month = parseInt(parts[1], 10);
	var year = parseInt(parts[2], 10);
	var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
    if(!/^\d{2}\/\d{2}\/\d{4}$/.test(dateString))
    {
    	return false;
    }
    else if(year < 1000 || year > 3000 || month == 0 || month > 12)
    {
    	return false;
    }
	else if(day > 0 && day > monthLength[month - 1])
	{
		return false;
	}
    else if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
    {
		monthLength[1] = 29;
		return day > 0 && day <= monthLength[month - 1];
    }
    else
    {
    	return true;
    }
}

function calculateUmurSimati(skrinName){
	//alert('skrinName : '+skrinName);
	var tarikh_lahir = document.getElementById(""+skrinName+"TARIKH_LAHIR").value;
	var tarikh_mati = document.getElementById(""+skrinName+"TARIKH_MATI").value;


	if (tarikh_lahir != "" && tarikh_mati != ""){


		var check_format_lahir = true;
		var check_format_mati = true;
		if(isValidDate_V3(document.getElementById(skrinName+"TARIKH_LAHIR").value)==false)
		{
			alert("Format Tarikh Lahir Tidak Tepat!");
			document.getElementById(skrinName+"TARIKH_LAHIR").focus();
			check_format_lahir = false;
			//return false;
		}

		if(isValidDate_V3(document.getElementById(skrinName+"TARIKH_MATI").value)==false)
		{
			alert("Format Tarikh Mati Tidak Tepat!");
			document.getElementById(skrinName+"TARIKH_MATI").focus();
			check_format_mati = false;
			//return false;
		}


		if(check_format_lahir == true && check_format_mati == true)
		{
			var str1  = tarikh_lahir;
			var str2  = tarikh_mati;

			var yr1   = parseInt(str1.substring(6,10),10);
			var yr2   = parseInt(str2.substring(6,10),10);

			var age = (yr2*1) - (yr1*1);
			if (age < 0 )
			{
				age = 0;
			}
			document.getElementById(""+skrinName+"UMUR").value = age;
		}
	}
}

function calculateUmurByTarikh(tarikhValue,fieldUmur){

	if (isValidDate_V3(tarikhValue)==true){

		var str1  = tarikhValue;
		var currentTime = new Date();
		var currentYear = currentTime.getFullYear();

		var yr1   = parseInt(str1.substring(6,10),10);

		var age = (currentYear*1) - (yr1*1);
		if (age < 0 )
			age = 0;
		document.getElementById(fieldUmur).value = age ;
	}
}

function setTarikhLahirIC(noKPWaris,fieldTarikhLahir){

		var currentTime = new Date();

		if(noKPWaris.length == 6){
			var a = noKPWaris.charAt(0);
			var b = noKPWaris.charAt(1);
			var c = noKPWaris.charAt(2);
			var d = noKPWaris.charAt(3);
			var e = noKPWaris.charAt(4);
			var f = noKPWaris.charAt(5);

			var currentYear = currentTime.getFullYear();
			var birthYear = currentYear + "";
			birthYear = birthYear.charAt(0) + "" + birthYear.charAt(1);
			var fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;

			if (fullBirthYear > currentYear ){
				birthYear = birthYear*1 - 1;
				birthYear = birthYear + "";
				fullBirthYear = birthYear.charAt(0) + "" + birthYear.charAt(1) + a + b;
			}
			document.getElementById(fieldTarikhLahir).value = e + f + "/" + c + d + "/" + fullBirthYear;
			//document.f1.txdTarikhLahirWaris.value = e + f + "/" + c + d + "/" + fullBirthYear;

		}

}


function pilihKetegoriTanah(jenisluas,tr_luas,tr_luas_b,l1,l2,l3,l1_field,l2_field,l3_field,luasawal,luasconvert,meterhektar)
{
	var val = document.getElementById(jenisluas).value;

	//alert("pilihKetegoriTanah : "+val);


	document.getElementById(l1_field).value= "";
	//alert("pilihKetegoriTanah a");
	document.getElementById(l2_field).value= "";
	//alert("pilihKetegoriTanah b");
	document.getElementById(l3_field).value= "";
	//alert("pilihKetegoriTanah c");
	document.getElementById(luasawal).value= "";
	//alert("pilihKetegoriTanah d");
	document.getElementById(luasconvert).value= "";
	//alert("pilihKetegoriTanah e");
	document.getElementById(meterhektar).value= "";
	//alert("pilihKetegoriTanah f");
	document.getElementById(tr_luas).style.display="none";
	//alert("pilihKetegoriTanah g");
	document.getElementById(tr_luas_b).style.display="none";
	//alert("pilihKetegoriTanah h");
	document.getElementById(l1).style.display="none";
	//alert("pilihKetegoriTanah i");
	document.getElementById(l2).style.display="none";
	//alert("pilihKetegoriTanah j");
	document.getElementById(l3).style.display="none";
	//alert("pilihKetegoriTanah k");

	//alert("pilihKetegoriTanah 1");

	if(val == 9)
	{
		document.getElementById(tr_luas).style.display="";
		document.getElementById(tr_luas_b).style.display="";
		document.getElementById(l1).style.display="";
		document.getElementById(l2).style.display="none";
		document.getElementById(l3).style.display="none";
	}
	else if(val == 7)
	{
		document.getElementById(tr_luas).style.display="";
		document.getElementById(tr_luas_b).style.display="";
		document.getElementById(l1).style.display="";
		document.getElementById(l2).style.display="";
		document.getElementById(l3).style.display="none";
	}
	else if(val == 4)
	{
		document.getElementById(tr_luas).style.display="";
		document.getElementById(tr_luas_b).style.display="";
		document.getElementById(l1).style.display="";
		document.getElementById(l2).style.display="";
		document.getElementById(l3).style.display="";
	}
	else if(val == 2)
	{
		document.getElementById(tr_luas).style.display="";
		document.getElementById(tr_luas_b).style.display="";
		document.getElementById(l1).style.display="";
		document.getElementById(l2).style.display="none";
		document.getElementById(l3).style.display="none";
	}
	else if(val == 5)
	{
		document.getElementById(tr_luas).style.display="";
		document.getElementById(tr_luas_b).style.display="";
		document.getElementById(l1).style.display="";
		document.getElementById(l2).style.display="none";
		document.getElementById(l3).style.display="none";
	}
	else if(val == 3)
	{
		document.getElementById(tr_luas).style.display="";
		document.getElementById(tr_luas_b).style.display="";
		document.getElementById(l1).style.display="";
		document.getElementById(l2).style.display="none";
		document.getElementById(l3).style.display="none";
	}
	else if(val == 6)
	{
		document.getElementById(tr_luas).style.display="";
		document.getElementById(tr_luas_b).style.display="";
		document.getElementById(l1).style.display="";
		document.getElementById(l2).style.display="none";
		document.getElementById(l3).style.display="none";
	}
	else if(val == 1)
	{
		document.getElementById(tr_luas).style.display="";
		document.getElementById(tr_luas_b).style.display="";
		document.getElementById(l1).style.display="";
		document.getElementById(l2).style.display="none";
		document.getElementById(l3).style.display="none";
	}
	else if(val == 8)
	{
		document.getElementById(tr_luas).style.display="";
		document.getElementById(tr_luas_b).style.display="";
		document.getElementById(l1).style.display="";
		document.getElementById(l2).style.display="";
		document.getElementById(l3).style.display="";
	}
	else
	{
		document.getElementById(tr_luas).style.display="none";
		document.getElementById(tr_luas_b).style.display="none";
		document.getElementById(l1).style.display="none";
		document.getElementById(l2).style.display="none";
		document.getElementById(l3).style.display="none";
	}
	//alert("pilihKetegoriTanah 9");
}

function setJenisLuas(val_kategori,meterhektar)
{
	var jenis_luas = "";
	if(val_kategori!="")
	{
		if(val_kategori == "2")
		{
			jenis_luas = "Hektar"
		}
		else
		{
			jenis_luas = "Meter Persegi"
		}
	}
	document.getElementById(meterhektar).value = jenis_luas;

}

function ConvertLuasHartaV6(jenisluas,luasawal,luasconvert,meterhektar,luas1,luas2,luas3,kategory)
{
	//alert("ConvertLuasHartaV6");
	//alert(" jenisluas : "+document.getElementById(jenisluas).value+" kategory : "+document.getElementById(kategory).value);

    if (document.getElementById(jenisluas).value=="2"){
        var a = document.getElementById(luas1).value;
       	var num = a * 10000;
		var num1 = a * 1;

		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		}
		else
		{
        if(document.getElementById(kategory).value=="2" || document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="5")
        {
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"H";
        }
        else if(document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4"  || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7"  || document.getElementById(kategory).value=="8")
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"H";
        }
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}


	}




  else if (document.getElementById(jenisluas).value=="5"){
        var a = document.getElementById(luas1).value;
       //	var num1 = a * 9.290304;
		//var num = a * 0.09290304;

		var num = parseFloat(a) * 0.09290304; // kaki persegi to meter persegi
		var num1 = parseFloat(a) * 0.000009290304; // kaki persegi to hektar


		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		}
		else
		{
        if(document.getElementById(kategory).value=="2")
        {
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"K";
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6" || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"K";
        }
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}
	}



	 else if (document.getElementById(jenisluas).value=="1"){
        var a = document.getElementById(luas1).value;
     //   var num1 = a * 100;
	 //	var num = a * 1000000;

	    var num = parseFloat(a) * 1000000;  // kilo to meter
        var num1 = parseFloat(a) * 100; // kilo meter to hektar


        if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		}
		else
		{
        if(document.getElementById(kategory).value=="2")
        {
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"KM";
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"KM";
        }
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}


	}



 else if (document.getElementById(jenisluas).value=="3"){
        var a = document.getElementById(luas1).value;
    //    var num = a * 1;
	//	var num1 = a * 0.0001;

	    var num = parseFloat(a) * 1; //meter persegi to meter persegi
		var num1 = parseFloat(a) * 0.0001;  //meter persegi to hektar

		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		}
		else
		{
        if(document.getElementById(kategory).value=="2")
        {
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"M";
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"M";
        }
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}


	}



else if (document.getElementById(jenisluas).value=="9"){
        var a = document.getElementById(luas1).value;
       var num = parseFloat(a) * 1;
		var num1 = parseFloat(a) * 1;

		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		}
		else
		{
        if(document.getElementById(kategory).value=="2")
        {
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"BN";
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"BN";
        }
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}


	}




else if (document.getElementById(jenisluas).value=="6"){
        var a = document.getElementById(luas1).value;
        var num = parseFloat(a) * 1;
		var num1 = parseFloat(a) * 1;

		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		}
		else
		{
        if(document.getElementById(kategory).value=="2")
        {
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"P";
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"P";
        }
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}


	}

//formula bawah ni hentam dulu
else if (document.getElementById(jenisluas).value=="7"){
        var a = document.getElementById(luas1).value;
        var b = document.getElementById(luas2).value;
       // var num = a * b;
	//var num1 = a * 0.0001;

		var num = (parseFloat(a) + (parseFloat(b)/1000))*4046.86;
		var num1 =  (parseFloat(a) + (parseFloat(b)/1000))*0.404686;

		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		}
        else if(b=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas2);
		return
		}
		else
		{
        if(document.getElementById(kategory).value=="2")
        {
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"E"+b+"D";
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"E"+b+"D";
        }
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}


	}


else if (document.getElementById(jenisluas).value=="8"){
        var a = document.getElementById(luas1).value;
        var b = document.getElementById(luas2).value;
        var c = document.getElementById(luas3).value;
     //   var num = a * b * c;
	 //	var num1 = a * 0.0001;

		var num = (parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976))*0.711111*4046.86;
		var num1 = ((parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976)))*0.711111*0.404686;

		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		}
        else if(b=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas2);
		return
		}
        else if(c=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas3);
		return
		}
		else
		{
        if(document.getElementById(kategory).value=="2")
        {
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"R"+b+"J"+c+"K";
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"R"+b+"J"+c+"K";
        }
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}


	}


    //formula bawah ni hentam dulu
else if (document.getElementById(jenisluas).value=="4"){
        var a = document.getElementById(luas1).value;
        var b = document.getElementById(luas2).value;
        var c = document.getElementById(luas3).value;
     //   var num = a * b * c;
	//	var num1 = a * 0.0001;
	var num = (parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160))*4046.86;
		var num1 = ((parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160)))*0.404686;

		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		}
        else if(b=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas2);
		return
		}
        else if(c=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas3);
		return
		}
		else
		{
        if(document.getElementById(kategory).value=="2")
        {
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"E"+b+"R"+c+"P";
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"E"+b+"R"+c+"P";
        }
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}


	}


}



/*
$jquery(function(){
	$jquery("#span123 input").keypress(function (e) {

		if (e.keyCode == 13) {
			alert('You pressed enter!');
		}

	});
});


function getAutoText(elem)
{
	var value = elem.textContent;
	//alert(value);
	//alert(elem.inner);
	//elem.innerHTML = "XXXXX";
	var style_input =  "style=\"width:"+((value.length) * 8) + "px\"";
	//alert(style_input);
	elem.innerHTML = "<input type =\"text\" id=\"inputText\" name=\"inputText\" "+style_input+" class=\"\"  value=\""+value+"\" />"
	//var currentMainSpan = elem.html();
	//alert('c'+currentMainSpan);
}

function autoText(e,elem,inputSpan,mainSpan)
{
	var currentMainSpan = $jquery("#"+mainSpan).html();
	if(e.which == 32)
	{
		var val = elem.value;
		if(val=='' || val==' ')
		{
			val = "&nbsp;";
			//alert(val);
		}


		$jquery("#"+mainSpan).html(currentMainSpan + "<span onclick=\"getAutoText(this);\">"+val+"</span>");
		elem.value = "";
	}
}




function autoWidthInputText(elem)
{
	elem.style.width = ((elem.value.length + 5) * 8) + 'px';
}



function getCaretCharacterOffsetWithin(element) {

	var list_words = ["hello", "world", "test word", "yay", "woot", "multiple words", "blue dog", "blue cat"];

    var caretOffset = 0;
    var doc = element.ownerDocument || element.document;
    var win = doc.defaultView || doc.parentWindow;
    var sel;
    if (typeof win.getSelection != "undefined") {
        sel = win.getSelection();
        if (sel.rangeCount > 0) {
            var range = win.getSelection().getRangeAt(0);
            var preCaretRange = range.cloneRange();
            preCaretRange.selectNodeContents(element);
            preCaretRange.setEnd(range.endContainer, range.endOffset);
            caretOffset = preCaretRange.toString().length;
        }
    } else if ( (sel = doc.selection) && sel.type != "Control") {
        var textRange = sel.createRange();
        var preCaretTextRange = doc.body.createTextRange();
        preCaretTextRange.moveToElementText(element);
        preCaretTextRange.setEndPoint("EndToEnd", textRange);
        caretOffset = preCaretTextRange.text.length;
    }

  word = getWordAtPosition(caretOffset, element);
  //console.log('caretOffset : ', caretOffset+" word : "+word);



  for (var i = 0; i < list_words.length; i++) {
	console.log('check : ', list_words[i]+" word : "+word);
	if (word.indexOf(list_words[i]) > -1)
	{
		console.log("String was found in substring " + list_words);
	}

  }

  return caretOffset;
}

function getWordAtPosition(position, element) {
  var total_text = element.innerHTML;
  var current_word = "";
  var i = 0;
  var word_found = false;
  while(i < total_text.length) {
    if(total_text[i] != ' ')
      current_word += total_text[i];
    else if(word_found)
      return current_word;
    else
      current_word = "";
    if(i == position)
      word_found = true;
    i++;
  }
  return current_word;
}
*/


function fckeditorWordCatatanPerintah(elem, divId) {
	//alert("masuk : "+divId+" divViewEditorkeputusanCATATAN_PERINTAH_BI : "+$jquery("#divViewEditorkeputusanCATATAN_PERINTAH_BI").height());

	var val = elem.innerHTML;
	//alert("divId : "+divId);
	//alert("val : "+val);
	//alert("fckeditor_word_count : "+val);
    var matches = val.replace(/<[^<|>]+?>|&nbsp;/gi,' ').match(/\b/g);
	//alert("matches : "+matches);
    var count = 0;
    if(matches) {
        count = matches.length/2;
    }
	//alert("count : "+count+" divId : "+divId);
    //document.getElementById(divId).innerHTML = count + " Perkataan" + (count == 1 ? "" : "s") + " approx";
	document.getElementById(divId).innerHTML = "Jumlah Perkataan : <span class=\"blue\"><b>"+count+"</b></span>";
	//alert("viewHeight : "+viewHeight);
	$jquery("#divViewEditorkeputusanCATATAN_PERINTAH_BI").removeClass("scrollYes");
	var viewHeight = $jquery("#divViewEditorkeputusanCATATAN_PERINTAH_BI").height();
	//alert("viewHeight : "+viewHeight);
	if(viewHeight>1000)
	{
		//document.getElementById("divViewEditorkeputusanCATATAN_PERINTAH_BI").setAttribute("style","overflow:auto;");
		$jquery("#divViewEditorkeputusanCATATAN_PERINTAH_BI").addClass("scrollYes");
	}

}

function replaceText(selector, text, newText, flags) {
  var matcher = new RegExp(text, flags);
  var elems = document.querySelectorAll(selector), i;

  for (i = 0; i < elems.length; i++)
    if (!elems[i].childNodes.length)
      elems[i].innerHTML = elems[i].innerHTML.replace(matcher, newText);
}


function fckeditor_word_count(elem, divId) {
	var val = elem.value;
	//alert("fckeditor_word_count : "+val);
    var matches = val.replace(/<[^<|>]+?>|&nbsp;/gi,' ').match(/\b/g);

    var count = 0;
    if(matches) {
        count = matches.length/2;
    }
    //document.getElementById(divId).innerHTML = count + " Perkataan" + (count == 1 ? "" : "s") + " approx";
	document.getElementById(divId).innerHTML = "Jumlah Perkataan : <span class=\"blue\"><b>"+count+"</b></span>";
}

function fckeditor_word_zero(elem, divId) {
	document.getElementById(divId).innerHTML = "Jumlah Perkataan : <span class=\"blue\"><b>0</b></span>";
}




function selectBicalaLain(val) {
	if(val!="")
	{
		doDivAjaxCall$formname('div_viewPerbicaraan','selectBicaraLain','ID_PERBICARAAN='+val);
	}
}


function editorKeputusan(skrinName,columnName,value)
{//
//alert("XXXXXXXXXXXX : "+$jquery('#trJanaCatatan').length);
	//alert("columnName : "+columnName+" skrinName : "+skrinName);
	$jquery(document).ready(function () {
			$jquery('#'+skrinName+columnName).wysihtml5({
				"font-styles": true, // Font styling, e.g. h1, h2, etc.
				"emphasis": true, // Italics, bold, etc.
				"lists": true, // (Un)ordered lists, e.g. Bullets, Numbers.
				"html": false, // Button which allows you to edit the generated HTML.
				"link": false, // Button to insert a link.
				"image": false, // Button to insert an image.
				"color": true, // Button to change color of font
				"blockquote": true, // Blockquote
				"size": "small"
			});

		});
		//alert("2");
		var resizeIframeK = function() {

			fckeditor_word_count(document.getElementById(skrinName+columnName),"word"+skrinName+columnName);
			document.getElementById('button'+skrinName).style.display = "none";
			document.getElementById('trinfobutton'+skrinName+columnName).style.display = "";
			$jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.iframe.style.height = $jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.element.scrollHeight + 50 + "px";

			if((skrinName+columnName)=="keputusanINTRO_CATATAN" || (skrinName+columnName)=="keputusanCATATAN_DOCKIV"  || (skrinName+columnName)=="keputusanCATATAN" || (skrinName+columnName)=="keputusanCATATAN_KEPUTUSAN_PERBICARAAN")
			{
				getTimeAutoSavePerintah("timer_"+skrinName+columnName,"FOCUS")
			}

			if ($jquery('#trJanaCatatan').length > 0 && skrinName == "keputusan"
			 //&& columnName == "CATATAN_PERINTAH_BI"
			 ) {
				document.getElementById('trJanaCatatan').style.display = "none";
			}

		}
		//alert("3");
		var resizeIframeBlurK = function() {
			fckeditor_word_count(document.getElementById(skrinName+columnName),"word"+skrinName+columnName);
			if((skrinName+columnName)=="keputusanINTRO_CATATAN" || (skrinName+columnName)=="keputusanCATATAN_DOCKIV"  || (skrinName+columnName)=="keputusanCATATAN" || (skrinName+columnName)=="keputusanCATATAN_KEPUTUSAN_PERBICARAAN")
			{
				getTimeAutoSavePerintah("timer_"+skrinName+columnName,"BLUR")
			}
			document.getElementById('button'+skrinName).style.display = "";
			document.getElementById('trinfobutton'+skrinName+columnName).style.display = "none";
			$jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.iframe.style.height = $jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.element.scrollHeight + 50 + "px";
			if ($jquery('#trJanaCatatan').length > 0 && skrinName == "keputusan"
			//&& columnName == "CATATAN_PERINTAH_BI"
			) {
				document.getElementById('trJanaCatatan').style.display = "";
			}
		}
		//alert("4");
		var resizeIframeKeyUpK = function() {
			fckeditor_word_count(document.getElementById(skrinName+columnName),"word"+skrinName+columnName);
			if((skrinName+columnName)=="keputusanINTRO_CATATAN" || (skrinName+columnName)=="keputusanCATATAN_DOCKIV" || (skrinName+columnName)=="keputusanCATATAN" || (skrinName+columnName)=="keputusanCATATAN_KEPUTUSAN_PERBICARAAN")
			{
				//console.log("resizeIframeKeyUpK : "+columnName);
				getTimeAutoSavePerintah("timer_"+skrinName+columnName,"KEYUP")
			}
			document.getElementById('button'+skrinName).style.display = "none";
			document.getElementById('trinfobutton'+skrinName+columnName).style.display = "";
			var iframeheight = $jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.iframe.style.height;
			var scrollheight = $jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.element.scrollHeight - 1;
			if(parseInt(scrollheight) > parseInt(iframeheight))
			{
				$jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.iframe.style.height = $jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.element.scrollHeight + 50 + "px";
			}
			if ($jquery('#trJanaCatatan').length > 0 && skrinName == "keputusan"
			//&& columnName == "CATATAN_PERINTAH_BI"
			) {
				//document.getElementById('trJanaCatatan').style.display = "none";
			}
		}
		//alert("5");

		var wordCount = function() {
			fckeditor_word_count(document.getElementById(skrinName+columnName),"word"+skrinName+columnName);
		}

		$jquery("#"+skrinName+columnName).data("wysihtml5").editor.on("load", function() {
		  fckeditor_word_count(document.getElementById(skrinName+columnName),"word"+skrinName+columnName);
		  console.log("columnName : "+$jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.element.scrollHeight);
		  $jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.iframe.style.height = $jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.element.scrollHeight + 50 + "px";
		  $jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.element.addEventListener("keyup", resizeIframeKeyUpK , false);
		  $jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.element.addEventListener("keydown", resizeIframeKeyUpK , false);
		  $jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.element.addEventListener("blur", resizeIframeBlurK, false);
		  $jquery("#"+skrinName+columnName).data("wysihtml5").editor.composer.element.addEventListener("focus", resizeIframeK, false);
		});


		 //alert("6 : "+value);

		 //$jquery("#"+skrinName+columnName).data("wysihtml5").editor.setValue(value);

}

function showHideTextIntro(val,skrinName,columnName)
{
	if(val != "")
	{
		//$jquery("#"+skrinName+columnName).data("wysihtml5").editor.setValue("");
		$jquery("#row"+skrinName+columnName).hide();
		//$jquery("#trinfobutton"+skrinName+columnName).hide();
		$jquery("#trword"+skrinName+columnName).hide();
		$jquery("#"+skrinName+columnName).val("");
		//editorKeputusan(skrinName,columnName,value);

	}
	else
	{
		$jquery("#row"+skrinName+columnName).show();
		//$jquery("#trinfobutton"+skrinName+columnName).show();
		$jquery("#trword"+skrinName+columnName).show();
	}

}

function showHideTextDocKembali(val,skrinName,columnName)
{
	//console.log("showHideTextDocKembali :: val : "+val+" skrinName : "+skrinName+" columnName : "+columnName);

	if(val == "1" || val == "")
	{
		//alert("test 1");
		//$jquery("#"+skrinName+columnName).data("wysihtml5").editor.setValue("");
		$jquery("#row"+skrinName+columnName).hide();
			//$jquery("#trinfobutton"+skrinName+columnName).hide();
		$jquery("#trword"+skrinName+columnName).hide();
		$jquery("#"+skrinName+columnName).val("");
		//editorKeputusan(skrinName,columnName,value);
	}
	else
	{
		//alert("test 2");
		$jquery("#row"+skrinName+columnName).show();
			//$jquery("#trinfobutton"+skrinName+columnName).show();
		$jquery("#trword"+skrinName+columnName).show();

	}

}

function reAssignFieldEditorContent(fieldname)
{
	/*
	var fs = "$fontSize";
	alert(" fs : "+fs)
	var regex = new RegExp(fs, "g");
	$jquery("#"+fieldname).data("wysihtml5").editor.setValue(document.getElementById("dummyDivResetup"+fieldname).innerHTML.replace(regex, ''));
	*/
	//alert("2");
	$jquery("#"+fieldname).data("wysihtml5").editor.setValue(document.getElementById("dummyDivResetup"+fieldname).innerHTML);
}


function setDalamDiv(div)
{
	 document.getElementById("keputusanCATATAN_PERINTAH_BI").value = document.getElementById("dummyJanaCatatan").innerHTML;
	 var fs = "$fontSize";
	 var regex = new RegExp(fs, "g");
	 //console.log("::: "+document.getElementById("dummyJanaCatatan").innerHTML.replace(regex, ''));
	 document.getElementById(div).innerHTML = document.getElementById("dummyJanaCatatan").innerHTML.replace(regex, '');
}


function cetakNotaPerbicaraan(NO_FAIL,id_perbicaraan,idfail,idsimati,seksyen,tarikh_permohonan,flagBicaraOnline) {

	var tarikhmohon = tarikh_permohonan;

	var dt1   = parseInt(tarikhmohon.substring(0,2),10);
   	var mon1  = parseInt(tarikhmohon.substring(3,5),10);
   	var yr1   = parseInt(tarikhmohon.substring(6,10),10);

	var dt2 = parseInt("01",10);
	var mon2 = parseInt("09",10);
	var yr2 = parseInt("2009",10);

   	var date1 = new Date(yr1, mon1, dt1);
    var tarikhsmbln = new Date(yr2, mon2, dt2);

    var flag = "";
	if(date1<tarikhsmbln){
		flag = 0;
	}else{
		flag = 1;
	}

	var tajuk = "";
	var url = "";

	if(seksyen=="8")
	{
		tajuk = "PERMOHONAN DI BAWAH SEKSYEN 8 (APPK(1998))";
		url = "../servlet/ekptg.report.ppk.NotaPerbicaraan?nofail="+NO_FAIL+"&flag="+flag+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&tajuk="+tajuk+"&flagBicaraOnline="+flagBicaraOnline;

	}
	else if(seksyen=="17")
	{
		tajuk = "PERMOHONAN DI BAWAH SEKSYEN 17 (APPK(1998))";
		url = "../servlet/ekptg.report.ppk.NotaPerbicaraan17?nofail="+NO_FAIL+"&flag="+flag+"&idfail="+idfail+"&idsimati="+idsimati+"&idperbicaraan="+id_perbicaraan+"&tajuk="+tajuk+"&flagBicaraOnline="+flagBicaraOnline;
	}

    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function goToBU()
{
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.PermohonanBantuUnit";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function checkHartaTertingal(id_button)
{
	//alert("checkHartaTerintggal : "+document.getElementById(id_button));

	var BI_FLAG_HARTA_TERINGGAL =   document.getElementById("BI_FLAG_HARTA_TERINGGAL").value;
	var seksyen_headerppk =   document.getElementById("seksyen_headerppk").value;

	console.log("BI_FLAG_HARTA_TERINGGAL : "+BI_FLAG_HARTA_TERINGGAL+" seksyen_headerppk : "+seksyen_headerppk);
	if(BI_FLAG_HARTA_TERINGGAL == "T" && seksyen_headerppk == "17")
	{
		document.getElementById(id_button).style.display = "none";
	}
	else
	{
		document.getElementById(id_button).style.display = "";
	}

}



function goToPerintah()
{
	//alert('1');
	var seksyen_headerppk =   document.getElementById("seksyen_headerppk").value;
	//alert('seksyen : '+seksyen_headerppk);
	var id_status_headerppk = document.getElementById("id_status_headerppk").value;
	//alert('id_status_headerppk : '+id_status_headerppk);
	var id_permohonan_headerppk = document.getElementById("id_permohonan_headerppk").value;
	//alert('id_permohonan_headerppk : '+id_permohonan_headerppk);
	var id_permohonansimati_headerppk = document.getElementById("id_permohonansimati_headerppk").value;
	//alert('id_permohonansimati_headerppk'+id_permohonansimati_headerppk);
	if (seksyen_headerppk == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
	}else{
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
	}
	document.${formName}.idPermohonanSimati.value = id_permohonansimati_headerppk;
	document.${formName}.idPermohonan.value = id_permohonan_headerppk;
	document.${formName}.idStatus.value = id_status_headerppk;
	document.${formName}.method="POST";
	document.${formName}.submit();

}


function cetakSuratPindahMTII(noFail) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratPindahMTII&flagReport=S";

    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	    //document.${formName}.command.value="getDaftarStatus";
		//document.${formName}.action="";
}

function cetakBorangI(noFail,idperbicaraan) {
    var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idperbicaraan="+idperbicaraan+"&report=BorangI&flagReport=B";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function buktiPenyampaian(noFail,idfail)
{
    var url = "../servlet/ekptg.report.ppk.BuktiPenyampaianMT?nofail="+noFail+"&idfail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function cetakBorangJ(nofail,idFail,id_perbicaraan) {
	var url = "../servlet/ekptg.report.ppk.BorangJ?noFail="+nofail+"&idfail="+idFail+"&idperbicaraan="+id_perbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function cetakBorangL(nofail,idFail,id_perbicaraan) {
	var url = "../servlet/ekptg.report.ppk.BorangL?noFail="+nofail+"&idfail="+idFail+"&idperbicaraan="+id_perbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangM(nofail,idFail,id_perbicaraan) {
	var url = "../servlet/ekptg.report.ppk.BorangM?noFail="+nofail+"&idfail="+idFail+"&idperbicaraan="+id_perbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangN(nofail,idFail,id_perbicaraan) {
	var url = "../servlet/ekptg.report.ppk.BorangN?noFail="+nofail+"&idfail="+idFail+"&idperbicaraan="+id_perbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


var flagDahFocusKeyin = [];

function getTimeAutoSave(divUnique_id,unique_id,action,ID_BIKEHADIRAN)
{
		//alert("x : "+refreshInterval_showtime[divUnique_id]);
		var command = "";
		if(unique_id=="")
		{
			command = "autoSaveKeteranganTuruthadir";
		}
		else
		{
			command = "autoSaveKeterangan";
		}


		if((flagDahFocusKeyin[divUnique_id] == undefined || flagDahFocusKeyin[divUnique_id] == null) && action=="KEYUP" )
		{
			if (typeof(refreshInterval_showtime[divUnique_id]) != 'undefined' && refreshInterval_showtime[divUnique_id] != null)
			{
				window.clearInterval(refreshInterval_showtime[divUnique_id]);
				refreshInterval_showtime[divUnique_id] = null;
				document.getElementById(divUnique_id).innerHTML = "";
			}
			//alert("masuk");
			var timeAutoSave = 30;//5 minit
			//alert("timeAutoSave : "+timeAutoSave);
			refreshInterval_showtime[divUnique_id] = setInterval(function(){
			timeAutoSave = timeAutoSave-1;
			//console.log("****");
			//console.log("flagDahFocusKeyin[divUnique_id] >>>>>>>> "+flagDahFocusKeyin[divUnique_id]);
			document.getElementById(divUnique_id).innerHTML = "<span class=\"blue\">"+timeAutoSave+" saat sebelum 'auto save'</span>";

			if(timeAutoSave <= 0)
			{
				window.clearInterval(refreshInterval_showtime[divUnique_id]);
				refreshInterval_showtime[divUnique_id] = null;
				getTimeAutoSave(divUnique_id,unique_id,"FOCUS",ID_BIKEHADIRAN);
				if(flagDahFocusKeyin[divUnique_id] == true)
				{
					flagDahFocusKeyin[divUnique_id] = null;
					doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&ID_OBPERMOHONAN="+unique_id+"&ID_BIKEHADIRAN="+ID_BIKEHADIRAN+"&divUnique_id="+divUnique_id);
				}
			}

			}, 1000);
		}
		else if(action=="BLUR")
		{
			window.clearInterval(refreshInterval_showtime[divUnique_id]);
			refreshInterval_showtime[divUnique_id] = null;
			flagDahFocusKeyin[divUnique_id] = null;
			document.getElementById(divUnique_id).innerHTML = "";
			doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&ID_OBPERMOHONAN="+unique_id+"&ID_BIKEHADIRAN="+ID_BIKEHADIRAN+"&divUnique_id="+divUnique_id);
		}

		if(action=="KEYUP")
		{
			flagDahFocusKeyin[divUnique_id] = true;
		}
}

function getTimeAutoSavePerintah(divUnique_id,action)
{
	var command = "autoSaveKeteranganPerintah";


	if((flagDahFocusKeyin[divUnique_id] == undefined || flagDahFocusKeyin[divUnique_id] == null) && action=="KEYUP" )
	{
		if (typeof(refreshInterval_showtime[divUnique_id]) != 'undefined' && refreshInterval_showtime[divUnique_id] != null)
		{
			window.clearInterval(refreshInterval_showtime[divUnique_id]);
			refreshInterval_showtime[divUnique_id] = null;
			document.getElementById(divUnique_id).innerHTML = "";
		}
		//alert("masuk");
		var timeAutoSave = 30;//5 minit
		//alert("timeAutoSave : "+timeAutoSave);
		refreshInterval_showtime[divUnique_id] = setInterval(function(){
		timeAutoSave = timeAutoSave-1;
		//console.log("****");
		//console.log("flagDahFocusKeyin[divUnique_id] >>>>>>>> "+flagDahFocusKeyin[divUnique_id]);
		document.getElementById(divUnique_id).innerHTML = "<span class=\"blue\">"+timeAutoSave+" saat sebelum 'auto save'</span>";

		if(timeAutoSave <= 0)
		{
			window.clearInterval(refreshInterval_showtime[divUnique_id]);
			refreshInterval_showtime[divUnique_id] = null;
			getTimeAutoSavePerintah(divUnique_id,"FOCUS")
			if(flagDahFocusKeyin[divUnique_id] == true)
			{
				flagDahFocusKeyin[divUnique_id] = null;
				//doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&ID_OBPERMOHONAN="+unique_id+"&ID_BIKEHADIRAN="+ID_BIKEHADIRAN+"&divUnique_id="+divUnique_id);
				doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&divUnique_id="+divUnique_id);
			}
		}

		}, 1000);
	}
	else if(action=="BLUR")
	{
		window.clearInterval(refreshInterval_showtime[divUnique_id]);
		refreshInterval_showtime[divUnique_id] = null;
		flagDahFocusKeyin[divUnique_id] = null;
		document.getElementById(divUnique_id).innerHTML = "";
		//doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&ID_OBPERMOHONAN="+unique_id+"&ID_BIKEHADIRAN="+ID_BIKEHADIRAN+"&divUnique_id="+divUnique_id);
		doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&divUnique_id="+divUnique_id);
	}

	if(action=="KEYUP")
	{
		flagDahFocusKeyin[divUnique_id] = true;
	}

		//console.log("getTimeAutoSavePerintah : "+divUnique_id);
		/*
		if (typeof(refreshInterval_showtime[divUnique_id]) != 'undefined' && refreshInterval_showtime[divUnique_id] != null)
		{
			window.clearInterval(refreshInterval_showtime[divUnique_id]);
			refreshInterval_showtime[divUnique_id] = null;
			document.getElementById(divUnique_id).innerHTML = "";
		}

		var command = "autoSaveKeteranganPerintah";

		if(action=="KEYUP")
		{
			var timeAutoSave = 10;//5 minit
			refreshInterval_showtime[divUnique_id] = setInterval(function(){
			timeAutoSave = timeAutoSave-1;
			document.getElementById(divUnique_id).innerHTML = "<span class=\"blue\">"+timeAutoSave+" saat sebelum 'auto save'</span>";
			if(timeAutoSave <= 0)
			{
				window.clearInterval(refreshInterval_showtime[divUnique_id]);
				refreshInterval_showtime[divUnique_id] = null;
				doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&divUnique_id="+divUnique_id);
			}

			}, 1000);
		}
		else if(action=="BLUR")
		{
			doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&divUnique_id="+divUnique_id);
		}
		*/
}


function getTimeAutoSave_ASAL(divUnique_id,unique_id,action,ID_BIKEHADIRAN)
{
		//alert("x : "+refreshInterval_showtime[divUnique_id]);
		if (typeof(refreshInterval_showtime[divUnique_id]) != 'undefined' && refreshInterval_showtime[divUnique_id] != null)
		{
			window.clearInterval(refreshInterval_showtime[divUnique_id]);
			refreshInterval_showtime[divUnique_id] = null;
			document.getElementById(divUnique_id).innerHTML = "";
		}

		var command = "";
		if(unique_id=="")
		{
			command = "autoSaveKeteranganTuruthadir";
		}
		else
		{
			command = "autoSaveKeterangan";
		}

		if(action=="KEYUP")
		{
			//alert("masuk");
			var timeAutoSave = 10;//5 minit
			//alert("timeAutoSave : "+timeAutoSave);
			refreshInterval_showtime[divUnique_id] = setInterval(function(){
			timeAutoSave = timeAutoSave-1;
			document.getElementById(divUnique_id).innerHTML = "<span class=\"blue\">"+timeAutoSave+" saat sebelum 'auto save'</span>";

			if(timeAutoSave <= 0)
			{
				window.clearInterval(refreshInterval_showtime[divUnique_id]);
				refreshInterval_showtime[divUnique_id] = null;
				//alert("y : "+refreshInterval_showtime[divUnique_id]);

				//alert("divUnique_id : "+divUnique_id);
				doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&ID_OBPERMOHONAN="+unique_id+"&ID_BIKEHADIRAN="+ID_BIKEHADIRAN+"&divUnique_id="+divUnique_id);
			}

			}, 1000);
		}
		else if(action=="BLUR")
		{
			doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&ID_OBPERMOHONAN="+unique_id+"&ID_BIKEHADIRAN="+ID_BIKEHADIRAN+"&divUnique_id="+divUnique_id);
		}

}


function getTimeAutoSavePerintah_ASAL(divUnique_id,action)
{
		console.log("getTimeAutoSavePerintah : "+divUnique_id);

		if (typeof(refreshInterval_showtime[divUnique_id]) != 'undefined' && refreshInterval_showtime[divUnique_id] != null)
		{
			window.clearInterval(refreshInterval_showtime[divUnique_id]);
			refreshInterval_showtime[divUnique_id] = null;
			document.getElementById(divUnique_id).innerHTML = "";
		}

		var command = "autoSaveKeteranganPerintah";

		if(action=="KEYUP")
		{
			//alert("masuk");
			var timeAutoSave = 10;//5 minit
			//alert("timeAutoSave : "+timeAutoSave);
			refreshInterval_showtime[divUnique_id] = setInterval(function(){
			timeAutoSave = timeAutoSave-1;
			document.getElementById(divUnique_id).innerHTML = "<span class=\"blue\">"+timeAutoSave+" saat sebelum 'auto save'</span>";

			if(timeAutoSave <= 0)
			{
				window.clearInterval(refreshInterval_showtime[divUnique_id]);
				refreshInterval_showtime[divUnique_id] = null;
				//alert("y : "+refreshInterval_showtime[divUnique_id]);

				//alert("divUnique_id : "+divUnique_id);
				doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&divUnique_id="+divUnique_id);
			}

			}, 1000);
		}
		else if(action=="BLUR")
		{
			doDivAjaxCall3$formname(divUnique_id,command,'ACTION='+action+"&divUnique_id="+divUnique_id);
		}

}


function janaCatatanPerintah(ID_SIMATI,ID_PERINTAH,ID_PERMOHONAN,ID_PERMOHONANSIMATI,ID_PERBICARAAN,ID_PEMOHON,skrinName)
{

	//locationJanaCatatan
	doDivAjaxCall$formname('locationJanaCatatan','janaCatatanPerintah','ID_SIMATI='+ID_SIMATI+'&ID_PERINTAH='+ID_PERINTAH+"&ID_PERMOHONAN="+ID_PERMOHONAN+"&ID_PERMOHONANSIMATI="+ID_PERMOHONANSIMATI+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&ID_PEMOHON="+ID_PEMOHON+"&skrinName="+skrinName);
}

function ajaxAutoReSetup(val,loc,id_perbicaraan)
{
	//var ID_PERINTAH = document.getElementById("keputusanID_PERINTAH").value;
	//alert('content : '+val+' loc : '+loc+" ID_PERINTAH :"+ID_PERINTAH);
	doDivAjaxCall$formname('locationreSetup'+loc,'reSetupCatatanPerintah','ID_PERBICARAAN='+id_perbicaraan+"&FIELD_NAME="+loc);
}

function saveBantahan(loc,elem,ID_PERBICARAAN)
{
	var check = elem.checked;
	var VALUE_FLAG_BANTAHAN = "";
	if(check==true)
	{
		VALUE_FLAG_BANTAHAN = elem.value
	}
	else
	{
		VALUE_FLAG_BANTAHAN = "";
	}

	if(ID_PERBICARAAN != "")
	{
		doDivAjaxCall$formname(loc,'saveBantahan','ID_PERBICARAAN='+ID_PERBICARAAN+"&VALUE_FLAG_BANTAHAN="+VALUE_FLAG_BANTAHAN);
	}
}

function saveIntro(loc,val)
{
	var ID_PERINTAH = document.getElementById("keputusanID_PERINTAH").value;
	if(ID_PERINTAH != "")
	{
		doDivAjaxCall$formname(loc,'saveIntro','ID_PERINTAH='+ID_PERINTAH+"&ID_INTROPERINTAH="+val);
	}
}

function setBlank(elem,aktiviti,displayType)
{
	if($jquery('#'+elem).length > 0)
	{
		//alert("ada");
		if(aktiviti == "blank")
		{
			$jquery('#'+elem).val("");
		}
		if(aktiviti == "display")
		{
			document.getElementById(elem).style.display = displayType;
			document.getElementById("div"+elem).style.display = displayType;

			var displayTypeSpan = "none";
			if(displayType=="none")
			{
				displayTypeSpan = "";
			}
			document.getElementById("span"+elem).style.display = displayTypeSpan;
		}
		//document.getElementById(elem).style.display = type;
	}
}

function setField(radiValue,field,aktiviti,displayType)
{
	var nama_field_input1 = radiValue+field+"FIELD1";
	var nama_field_input2 = radiValue+field+"FIELD2";
	var nama_field_input3 = radiValue+field+"FIELD3";

	setBlank(nama_field_input1,aktiviti,displayType);
	setBlank(nama_field_input2,aktiviti,displayType);
	setBlank(nama_field_input3,aktiviti,displayType);
}

function setFieldIntro(id,field,val, size)
{
	//alert("id : "+id+" field : "+field+" val : "+val);
	var element = document.getElementsByName(field+"Radio");
	//var element_all = document.getElementById(main_check);
	//alert("element : "+element.length);

	if(element.length > 1)
	{
		for (i = 0; i < element.length; i++)
		{
			var radioValue = element[i].value;
			if (element[i].checked == false)
			{	 //alert("check"+i+" "+radiValue+field+"FIELD1");
				setField(radioValue,field,"blank","")
				setField(radioValue,field,"display","none")
			}
			else if (element[i].checked == true)
			{
				setField(radioValue,field,"display","")
			}

		}
	}
	else
	{
		var radioValue = element.value;
		if (element[i].checked == false)
		{
			setField(radioValue,field,"blank","")
			setField(radioValue,field,"display","none")
		}
		else if (element[i].checked == true)
		{
			setField(radioValue,field,"display","")
		}
	}


}

/*
function autoReSetup(mainField,tempDiv)
{
	alert("tempDiv : "+tempDiv);
	$jquery(document).ready(function () {
		 alert('1'+document.getElementById(tempDiv).innerHTML);
		 //document.getElementById("keputusanCATATAN").value = '$htmlSkrinMaklumat';
		 $jquery("#"+mainField).data("wysihtml5").editor.setValue(document.getElementById(tempDiv).innerHTML+"ccccccccccccccc");
		// $jquery("#"+mainField).data("wysihtml5").editor.focus();


		// alert('2');
	});
}
*/

/*
$jquery("#inputMinify").on("input", function() {
	//alert("x"+$jquery(this).val();
  $jquery("#outputMinify").val(minify($jquery(this).val(), {
    removeAttributeQuotes: true,
    collapseWhitespace: true
      // other options
  }));
});
*/

function setSelectedTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
function setSelectedTabLower(tabId) {
	document.${formName}.selectedTabLower.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}

/*
 * papar fm perinah htaah / htath
 */

function paparFmPerintahHTAAH(div, cmd, idHTAAH) {

	document.${formname}.idHTAAH.value = idHTAAH;
	doDivAjaxCall$formname(div, cmd, '');
}

/*
 * papar fm perinah ha
 */

function paparFmPerintahHA(div, cmd, idHA) {

	document.${formname}.idHA.value = idHA;
	doDivAjaxCall$formname(div, cmd, '');
}

function kemaskiniPerintahHTA() {
	if(document.${formName}.socJenisPerintahHTA.value == ""){
		alert('Sila pilih Jenis Perintah.');
  		document.${formName}.socJenisPerintahHTA.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}

	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTA";
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButt.value = "simpanKemaskiniHTA";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
</script>




#if($command == "viewPerbicaraanFromPerintah")
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('div_viewPerbicaraan','viewPerbicaraan','ID_PEMOHON=$ID_PEMOHON&ID_SIMATI=$ID_SIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI')
});
</script>
#end


<div id="loadWholePage"></div>

#parse("app/ppk/headerppk_script.jsp")
