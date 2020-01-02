
<style >

/*
@media all {
	.page-break	{ display: none; page-break-before: always; }
}
*/



@media print {
	
	.page-break	{page-break-before: always;}

}


.autoSizing{
 	 width: 100%; 
     box-sizing: border-box;
     -webkit-box-sizing:border-box;
     -moz-box-sizing: border-box;
}

.font_tajuk_sub{
	font-size: 100%;
	color : blue;
    text-shadow: 1px 1px 5px grey;
     
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
    padding: 2px 1px;
    font-size: 90%;
    color : white;
    text-shadow: 2px 2px 5px black;
}

.underline_td_sub2 {
	border-bottom: 1px solid #000;
    padding: 1px 1px;
    font-size: 80%;
    text-shadow: 1px 1px 1px grey;
}

.font_date {
	font-size: 80%;
	color : blue;
    text-shadow: 1px 1px 2px grey;
    cursor: pointer;    
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



.mq_css {
 height: 25px;	
 overflow: hidden;
 position: relative;
}
.mq_css h3 {
 position: absolute;
 width: 100%;
 height: 100%;
 margin: 0;
 line-height: 25px;
 text-align: left;
 /* Apply animation to this element */	
 -moz-animation: mq_css 8s linear infinite alternate;
 -webkit-animation: mq_css 8s linear infinite alternate;
 animation: mq_css 8s linear infinite alternate;
}
/* Move it (define the animation) */
@-moz-keyframes mq_css {
 0%   { -moz-transform: translateX(70%); }
 100% { -moz-transform: translateX(0%); }
}
@-webkit-keyframes mq_css {
 0%   { -webkit-transform: translateX(70%); }
 100% { -webkit-transform: translateX(0%); }
}
@keyframes mq_css {
 0%   { 
 -moz-transform: translateX(70%); /* Firefox bug fix */
 -webkit-transform: translateX(70%); /* Firefox bug fix */
 transform: translateX(70%); 		
 }
 100% { 
 -moz-transform: translateX(0%); /* Firefox bug fix */
 -webkit-transform: translateX(0%); /* Firefox bug fix */
 transform: translateX(0%); 
 }
}


</style>




<div id="div_senaraiUtama" >
#parse("app/pdt/trm/SenaraiUtama.jsp")
</div>

<iframe id="uploadFrame" name="uploadFrame" style="display:none"></iframe>
<input type="hidden" id="setPageCoor" name="setPageCoor" value="$getPageCoor" >
#if($after_uploadLampiran=="Y")
<script>
//alert('x');
	window.parent.viewListLampiran('$JENISSUB','$ID_WARTATRMINDUK','$ID_WARTATRM_AFTERUPLOAD','$COOR_UPLOAD','$divLampiran','$type','$mode','$TEMPID_WARTATRM');
</script>
#end

<script>

//checkProgresLuas('$repNegeri.LUAS','$repNegeri.BIL','$listREPORTNEGERI.size()')
function checkProgresLuas(curr_luas,bil,total_negeri)
{
	//alert('curr_luas : '+curr_luas+' bil : '+bil+' total_negeri : '+total_negeri);
	var curr_luas_double = parseFloat(curr_luas);
	//var curr_tahun_int = parseInt(curr_tahun);
	var span_show_elem = document.getElementById('spanProgress_'+bil);
	var temp_prev_location = parseInt(bil) + parseInt(total_negeri);
	
	//alert('LUAS_ASAL_CAL_'+temp_prev_location);
	var prev_luas = document.getElementById('LUAS_ASAL_CAL_'+temp_prev_location);
	
	var show_span = false;
	if( $jquery('#LUAS_ASAL_CAL_'+temp_prev_location).length )         // use this if you are using id to check
	{
		show_span = true;
	}	
	
	
	if(show_span == true)
	{
		var prev_luas = document.getElementById('LUAS_ASAL_CAL_'+temp_prev_location).value;
		
		var prev_luas_double = parseFloat(prev_luas);
		var percen = 0;
		
		if(curr_luas_double != 0.0 && prev_luas_double != 0.0)
		{
			percen = (curr_luas_double/prev_luas_double) * 100;
			if(percen<100)
			{
				percen = 100 - percen;
			}
			else
			{
				percen = percen-100;
			}
		}
		
		percen = percen.toFixed(1);
		
		var str_pencen = "";
		if(percen > 0 || percen < 0)
		{
			str_percen = percen + "%";
		}
		else
		{
			str_percen = "";
		}
		//alert();
		if(curr_luas_double>prev_luas_double)
		{
			span_show_elem.innerHTML = '<br><font color=\'blue\'>(+)'+str_percen+'<font>';
		}
		else if(curr_luas_double<prev_luas_double)
		{
			span_show_elem.innerHTML = '<br><font color=\'red\'>(-)'+str_percen+'</font>';
		}
		else if(curr_luas_double==prev_luas_double && curr_luas_double != 0.0 && prev_luas_double != 0.0)
		{
			span_show_elem.innerHTML = '<br>(=)'+str_percen;
		}
	}
	else	
	{
		span_show_elem.innerHTML = '';
	}
	
}

function checkProgresLuasXXXXX(curr_luas,curr_tahun,curr_id_negeri)
{
	var curr_luas_double = parseFloat(curr_luas);
	var curr_tahun_int = parseInt(curr_tahun);
	var span_show_elem = document.getElementById('spanProgress_'+curr_tahun+curr_id_negeri);
	
	var prev_luas = document.getElementById('LUAS_ASAL_'+(curr_tahun_int-1)+curr_id_negeri);
	
	var show_span = false;
	if( $jquery('#LUAS_ASAL_'+(curr_tahun_int-1)+curr_id_negeri).length )         // use this if you are using id to check
	{
		show_span = true;
	}	
	
	
	if(show_span == true)
	{
		var prev_luas = document.getElementById('LUAS_ASAL_'+(curr_tahun-1)+curr_id_negeri).value;
		
		var prev_luas_double = parseFloat(prev_luas);
		var percen = 0;
		
		if(curr_luas_double != 0.0 && prev_luas_double != 0.0)
		{
			percen = (curr_luas_double/prev_luas_double) * 100;
			if(percen<100)
			{
				percen = 100 - percen;
			}
			else
			{
				percen = percen-100;
			}
		}
		
		percen = percen.toFixed(1);
		
		var str_pencen = "";
		if(percen > 0 || percen < 0)
		{
			str_percen = percen + "%";
		}
		else
		{
			str_percen = "";
		}
		//alert();
		if(curr_luas_double>prev_luas_double)
		{
			span_show_elem.innerHTML = '<br><font color=\'blue\'>(+)'+str_percen+'<font>';
		}
		else if(curr_luas_double<prev_luas_double)
		{
			span_show_elem.innerHTML = '<br><font color=\'red\'>(-)'+str_percen+'</font>';
		}
		else if(curr_luas_double==prev_luas_double && curr_luas_double != 0.0 && prev_luas_double != 0.0)
		{
			span_show_elem.innerHTML = '<br>(=)'+str_percen;
		}
	}
	else	
	{
		span_show_elem.innerHTML = '';
	}
	
}

function printLaporan(divName,header) {
	//var originalContents = document.body.innerHTML;
    $jquery("#"+divName+" :button").hide();
	//$jquery("#divLampiran_"+id_aduanpublic).hide();  
	//$jquery("#divKronologi_"+id_aduanpublic).hide();
	//$jquery("#divDisplayAlert_"+id_aduanpublic).hide();
	  
	var head_style = " <head> "+
    " <style> "+
    " @media print { "+ 
	" 	body { "+ 
    "  -webkit-print-color-adjust: exact; "+  /*Chrome, Safari */
    "  color-adjust: exact;  "+  /*Firefox*/
    " 	} "+ 
	"         table { page-break-inside:auto } "+
  	"		  tr    { page-break-inside:avoid; page-break-after:auto;  } "+
    " } "+   
    " </style> "+
	" </head> ";
	  
	//var style = '<link rel="stylesheet" type="text/css" media="screen, print" href="../css/eTapp_PDT.css" />'
	var style = '';
    var printContents = document.getElementById(divName).innerHTML;
	//printContents = printContents.replace(/background-color:yellow/g , "background-color:white;");
	//.replace("background-color:yellow", "background-color:");	
    var footer ="";
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
   
    popupWin.document.write('<html><body onload="window.print()">'+head_style+style+'<br>'+printContents + '<br></html>');
	//class="page-break"
    popupWin.document.close(); 
    //elementHide.style.display = "none";
	//$jquery("#"+divName+" :button").show();    
    //document.body.innerHTML = originalContents;
    return false;
}

function setTabLocation(div,tab_id, index)
{
	//alert(" tab_id : "+tab_id+" index : "+index);
 	var TabbedPanels1 = new Spry.Widget.TabbedPanels(tab_id, {
		defaultTab : index
	});
}

function uploadTRM(div,command,JENISSUB,ID_WARTATRMINDUK,ID_WARTATRM,mode,type,field_name)
{
	var TEMPID_WARTATRM = document.getElementById("TEMPID_WARTATRM_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value;
	//alert('TEMPID_WARTATRM : '+TEMPID_WARTATRM);
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM&divLampiran="+div+"&field_name="+field_name+"&type="+type+"&command="+command+"&mode="+mode+"&JENISSUB="+JENISSUB+"&ID_WARTATRMINDUK="+ID_WARTATRMINDUK+"&ID_WARTATRM="+ID_WARTATRM+"&getPageCoor="+getPageCoor()+"&TEMPID_WARTATRM="+TEMPID_WARTATRM;
	document.${formName}.method="post";
	document.${formName}.target="uploadFrame";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();	
}


function saveLampiran(div,command,ID_WARTATRM,ID_STATUS,ID_PENGADU,mode)
{
	document.${formName}.action = "?_portal_module=ekptg.view.online.FrmAduanPublic&divLampiran="+div+"&command="+command+"&ID_STATUS="+ID_STATUS+"&ID_PENGADU="+ID_PENGADU+"&mode="+mode+"&ID_WARTATRM="+ID_WARTATRM+"&getPageCoor="+getPageCoor();
	document.${formName}.method="post";
	document.${formName}.target="uploadFrame";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();	
}
//contoh function download
function paparDoc(ID_WARTATRM,type) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlobTRM?ID_WARTATRM="+ID_WARTATRM+"&type="+type;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
//-------------------------------------------
//viewListLampiran('$ID_WARTATRM_AFTERUPLOAD','$COOR_UPLOAD','$divLampiran','$type','$mode');
function viewListLampiran(JENISSUB,ID_WARTATRMINDUK,ID_WARTATRM,COOR_UPLOAD,divLampiran,type,mode,TEMPID_WARTATRM)
{
	//alert('afterUpload 2');
	//FAILWARTA_$view.ID_WARTATRM
	document.getElementById("FAIL"+type+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value = "";
	doDivAjaxCall$formname(divLampiran,'displayUpload','COOR_UPLOAD='+COOR_UPLOAD+'&ID_WARTATRM='+ID_WARTATRM+'&JENISSUB='+JENISSUB+'&ID_WARTATRMINDUK='+ID_WARTATRMINDUK+"&type="+type+"&mode="+mode+"&TEMPID_WARTATRM="+TEMPID_WARTATRM);		
}
	
function getPageCoor()
{
	var tempScrollTop = $jquery(window).scrollTop();
	$jquery('#setPageCoor').val(tempScrollTop);
	return tempScrollTop;
}

function check_length(my_form,maxLen,text_num)
{
	my_form.setAttribute('maxlength',maxLen);
	var current_value = my_form.value;
	var total_check = maxLen - current_value.length;	
	$jquery("#"+text_num).html("<br><font color='blue'>"+total_check+"</font> Baki Aksara");
}

function numberOnly(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}	
}


function valFilterLaporan()
{
	//alert('x');
	var bool_check = true;	
	var elem_tahun_dari = document.getElementById('LAP_TAHUN_DARI');
	var elem_tahun_hingga = document.getElementById('LAP_TAHUN_HINGGA');
	
	var elem_sub = document.getElementsByName('checkSUB_NEGERI');
	var elem_sub_byID = document.getElementById('checkSUB_NEGERI');
	var c = 0;
	if(elem_sub.length > 1)
	{     
		  for (i = 0; i < elem_sub.length; i++)
		  {
		  if (elem_sub[i].checked == true)
		  {	 
		  c++
		  }
		  }  
	}
	else
	{
		//alert('x : '+elem_sub.checked);
		if (elem_sub_byID.checked == true)
		{	 
		c++;
		}	 	
	}	 
	
	
	
	
	
	
	//alert('x12'+document.getElementById('CHECK_'+elem_tahun_dari).value);
	if(elem_tahun_dari.value == "")
	{
		bool_check = false;	
		alert('Sila Masukkan Tahun Mula!');
		elem_tahun_dari.focus();
	}
	else if(elem_tahun_hingga.value == "")
	{
		bool_check = false;	
		alert('Sila Masukkan Tahun Hingga!');
		elem_tahun_hingga.focus();
	}
	else if(document.getElementById('CHECK_'+elem_tahun_dari.id).value=='false')
	{
		bool_check = false;	
		alert('Pastikan Format Tahun Mula Adalah Tepat!');
		elem_tahun_dari.focus();
		
	}
	else if(document.getElementById('CHECK_'+elem_tahun_hingga.id).value=='false')
	{
		bool_check = false;	
		alert('Pastikan Format Tahun Hingga Adalah Tepat!');
		elem_tahun_hingga.focus();
	}	
	else if(c==0)
	{
		bool_check = false;	
		alert('Pastikan sekurang-kurangnya satu negeri dipilih!');
		//elem_tahun_hingga.focus();
	}
	
	return bool_check;
}


function valFilterLaporanTahun()
{
	//alert('x');
	var bool_check = true;	
	var elem_tahun_dari = document.getElementById('LAP_TAHUN_DARI');
	var elem_tahun_hingga = document.getElementById('LAP_TAHUN_HINGGA');
	
	var elem_sub = document.getElementsByName('checkSUB_NEGERI');
	var elem_sub_byID = document.getElementById('checkSUB_NEGERI');
	
	//alert('x12'+document.getElementById('CHECK_'+elem_tahun_dari).value);
	if(elem_tahun_dari.value == "")
	{
		bool_check = false;	
		//alert('Sila Masukkan Tahun Mula!');
		//elem_tahun_dari.focus();
	}
	else if(elem_tahun_hingga.value == "")
	{
		bool_check = false;	
		//alert('Sila Masukkan Tahun Hingga!');
		//elem_tahun_hingga.focus();
	}
	else if(document.getElementById('CHECK_'+elem_tahun_dari.id).value=='false')
	{
		bool_check = false;	
		//alert('Pastikan Format Tahun Mula Adalah Tepat!');
		//elem_tahun_dari.focus();
		
	}
	else if(document.getElementById('CHECK_'+elem_tahun_hingga.id).value=='false')
	{
		bool_check = false;	
		//alert('Pastikan Format Tahun Hingga Adalah Tepat!');
		//elem_tahun_hingga.focus();
	}	
	
	//alert(' bool_check : '+bool_check);
	return bool_check;
}

function numberOnlyTahun(id_mula,id_hingga) {
	//if it is character, then remove it..
	
	var elem_tahun_dari = document.getElementById(id_mula);
	var elem_tahun_hingga = document.getElementById(id_hingga);
	
	if (isNaN(elem_tahun_dari.value) && elem_tahun_dari.value != '') {
		elem_tahun_dari.value = RemoveNonNumeric(elem_tahun_dari.value);			
		return;
	}	
	if (isNaN(elem_tahun_hingga.value) && elem_tahun_hingga.value != '') {
		elem_tahun_hingga.value = RemoveNonNumeric(elem_tahun_hingga.value);		
		return;
	}	
	
	
	if(checkMaxleng(elem_tahun_dari)==false && elem_tahun_dari.value != '')
	{
		$jquery("#span_CHECK_"+elem_tahun_dari.id).html("<font class='blink' color='red'>Format Tahun Mula Tidak Tepat (YYYY)!<input type='hidden' id='CHECK_"+elem_tahun_dari.id+"' name='CHECK_"+elem_tahun_dari.id+"' value='false' ></font>");
	}
	else
	{
		$jquery("#span_CHECK_"+elem_tahun_dari.id).html("<input type='hidden' id='CHECK_"+elem_tahun_dari.id+"' name='CHECK_"+elem_tahun_dari.id+"' value='true' >");
	}
		
	if(checkMaxleng(elem_tahun_hingga)==false && elem_tahun_hingga.value != '')
	{
		$jquery("#span_CHECK_"+elem_tahun_hingga.id).html("<font class='blink' color='red'>Format Tahun Mula Tidak Tepat (YYYY)!<input type='hidden' id='CHECK_"+elem_tahun_hingga.id+"' name='CHECK_"+elem_tahun_dari.id+"' value='false' ></font>");
	}
	else
	{
		$jquery("#span_CHECK_"+elem_tahun_hingga.id).html("<input type='hidden' id='CHECK_"+elem_tahun_hingga.id+"' name='CHECK_"+elem_tahun_hingga.id+"' value='true' >");
	}
	
	
	if(elem_tahun_dari.value.length == elem_tahun_dari.maxLength && elem_tahun_hingga.value.length == elem_tahun_hingga.maxLength)
	{
		var curr_yr = new Date().getFullYear();
		
		if(elem_tahun_dari.value>elem_tahun_hingga.value)
		{
			$jquery("#span_CHECK_"+elem_tahun_hingga.id).html("<font class='blink' color='red'>Pastikan Tahun Mula Tidak Melebihi Tahun Hingga!<input type='hidden' id='CHECK_"+elem_tahun_hingga.id+"' name='CHECK_"+elem_tahun_hingga.id+"' value='false' ></font>");
		}
		else if(elem_tahun_hingga.value > curr_yr)
		{
			$jquery("#span_CHECK_"+elem_tahun_hingga.id).html("<font class='blink' color='red'>Pastikan Tahun Hingga Tidak Melebihi Tahun Semasa!<input type='hidden' id='CHECK_"+elem_tahun_hingga.id+"' name='CHECK_"+elem_tahun_hingga.id+"' value='false' ></font>");
		}
		else
		{
			$jquery("#span_CHECK_"+elem_tahun_hingga.id).html("<input type='hidden' id='CHECK_"+elem_tahun_hingga.id+"' name='CHECK_"+elem_tahun_hingga.id+"' value='true' >");
			$jquery("#span_CHECK_"+elem_tahun_dari.id).html("<input type='hidden' id='CHECK_"+elem_tahun_dari.id+"' name='CHECK_"+elem_tahun_dari.id+"' value='true' >");
		}
	}
	
}

function checkMaxleng(elem)
{
	//alert(elem.value.length + ' '+ elem.maxLength)
	var check = true;	
	if(elem.value.length  != elem.maxLength)
	{
		check = false;
	}
	//alert(check);
	return check;
}

function valCarian()
{
	var bool_check = true;
	if(document.getElementById("CR_TARIKH_MULA").value!="" 
	&& document.getElementById("CHECK_CR_TARIKH_MULA").value == 'false' )
	{
		alert('$alert_masuk_format_tarikh');
		document.getElementById("CR_TARIKH_MULA").focus();
		bool_check = false;
	}
	else if(document.getElementById("CR_TARIKH_AKHIR").value!="" 
	&& document.getElementById("CHECK_CR_TARIKH_AKHIR").value == 'false' )
	{
		alert('$alert_masuk_format_tarikh');
		document.getElementById("CR_CR_TARIKH_AKHIR").focus();
		bool_check = false;
	}
	return bool_check;
}


//document.getElementById("AddLampiran"+ID_WARTATRM).value = "";

function valEditUpload(ev,ID_WARTATRM,mode,BIL,rowCss,ID_STATUS,ID_PENGADU)
{
	//var bool_check = true;
	if(ID_WARTATRM=="")
	{
		//alert("$alert_mesej_before_upload");
		if(valEdit(ID_WARTATRM,'deraf')==false)
		{
			var file_element = document.getElementById("AddLampiran"+ID_WARTATRM);	
			ev.preventDefault();
			file_element.focus();			
		}
		else
		{
			ev.preventDefault();
			doDivAjaxCall3$formname('div_row'+ID_WARTATRM,'saveDeraf','ID_WARTATRM='+ID_WARTATRM+'&mode='+mode+'&commandFrom=&BIL='+BIL+'&rowCss='+rowCss+'&flagUpload=Y');
		}
		
	}
	else
	{
		 $jquery("#AddLampiran"+ID_WARTATRM).change(function() {
			 onchangeUpload(ID_WARTATRM,mode,ID_STATUS,ID_PENGADU);
		 });	
	}
	//return bool_check;
}

function onchangeUpload(ID_WARTATRM,mode,ID_STATUS,ID_PENGADU)
{
	if(showFileSize(ID_WARTATRM)==true)
	{
		saveLampiran('divSenaraiLampiran'+ID_WARTATRM,'saveLampiran',ID_WARTATRM,ID_STATUS,ID_PENGADU,mode);
	}
	else 
	{
		var file_element = document.getElementById("AddLampiran"+ID_WARTATRM);
		file_element.disabled = 'disabled';
  		setTimeout(function() { file_element.disabled = ''; }, 1);
	}
}

function valEdit(ID_WARTATRM,JENISSUB,ID_WARTATRMINDUK)
{
	
	//alert(document.getElementById("div_row"+ID_WARTATRM).length)
	
	//alert('atas');
	var bool_check = true;
	
	
	
	if(document.getElementById("ID_NEGERI_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value=="" && JENISSUB != "B")
	{
		alert('Sila Pilih Negeri!');
		document.getElementById("ID_NEGERI_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).focus();
		bool_check = false;
	}
	else if(document.getElementById("ID_DAERAH_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value=="" && JENISSUB != "B")
	{
		alert('Sila Pilih Daerah!');
		document.getElementById("ID_DAERAH_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).focus();
		bool_check = false;
	}
	else if(document.getElementById("ID_MUKIM_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value=="" && JENISSUB != "B")
	{
		alert('Sila Pilih Mukim!');
		document.getElementById("ID_MUKIM_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).focus();
		bool_check = false;
	}
	else if(document.getElementById("NO_WARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value=="")
	{
		alert('Sila Masukkan No. Warta!');
		document.getElementById("NO_WARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).focus();
		bool_check = false;
	}
	else if(document.getElementById("NO_WARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value!="" 
	&& document.getElementById("CHECK_NO_WARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value == "Y")
	{
		alert('No. Warta Telah Wujud!');
		document.getElementById("NO_WARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).focus();
		bool_check = false;
	}
	else if(document.getElementById("TARIKH_WARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value=="")
	{
		alert('Sila Masukkan Tarikh Warta!');
		document.getElementById("TARIKH_WARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).focus();
		bool_check = false;
	}
	else if(document.getElementById("TARIKH_WARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value!="" 
				&& document.getElementById("CHECK_TARIKH_WARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value == 'false' )
	{
		alert('Sila Pastikan Format Tarikh Adalah Tepat!');
		document.getElementById("TARIKH_WARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).focus();
		bool_check = false;
	}
	else if(document.getElementById("LUAS_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value=="")
	{
		alert('Sila Masukkan Luas!');
		document.getElementById("LUAS_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).focus();
		bool_check = false;
	}
	else if(document.getElementById("LUAS_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value!=""
	&& document.getElementById("CHECK_LUAS_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value=="Y"
	&& document.getElementById("FLAG_JENISWARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value=="B")
	{
		alert('Pembatalan Luas adalah Melebihi Luas Asal!');
		document.getElementById("LUAS_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).focus();
		bool_check = false;
	}
	else if(document.getElementById("FLAG_STATUSWARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).value=="")
	{
		alert('Sila Pilih Status Warta!');
		document.getElementById("FLAG_STATUSWARTA_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM).focus();
		bool_check = false;
	}
	
	
	
	
	
	
	
	//alert('bawah');
	
	/*
	
	if(type=="hantar")
	{
		if(document.getElementById("ID_SUMBERADUAN_"+ID_WARTATRM).value=="")
		{
			alert('$alert_masuk_sumber_aduan');
			document.getElementById("ID_SUMBERADUAN_"+ID_WARTATRM).focus();
			bool_check = false;
		}
		else if(document.getElementById("ID_SUMBERADUAN_"+ID_WARTATRM).value!="16101" && document.getElementById("NAMA_PENGADU_"+ID_WARTATRM).value=="")
		{
			//nama pengadu
			alert('$alert_masuk_nama_pengadu');
			document.getElementById("NAMA_PENGADU_"+ID_WARTATRM).focus();
			bool_check = false;
		}
		else if(document.getElementById("ID_SUMBERADUAN_"+ID_WARTATRM).value!="16101" && document.getElementById("TEL_PENGADU_"+ID_WARTATRM).value=="")
		{
			//tel pengadu
			alert('$alert_masuk_tel_pengadu');
			document.getElementById("TEL_PENGADU_"+ID_WARTATRM).focus();
			bool_check = false;
		}
		else if(document.getElementById("ID_SUMBERADUAN_"+ID_WARTATRM).value!="16101" 
		&& (document.getElementById("EMEL_PENGADU_"+ID_WARTATRM).value=="" 
		|| ValidateEmailFormat(document.getElementById("EMEL_PENGADU_"+ID_WARTATRM).value)==false)
		)
		{
			//emel pengadu
			alert('$alert_masuk_emel_pengadu');
			document.getElementById("EMEL_PENGADU_"+ID_WARTATRM).focus();
			bool_check = false;
		}	
		else if(document.getElementById("ID_JENISADUAN_"+ID_WARTATRM).value=="")
		{
			alert('$alert_masuk_jenis_aduan');
			document.getElementById("ID_JENISADUAN_"+ID_WARTATRM).focus();
			bool_check = false;
		}
		else if(document.getElementById("FLAG_HIDE_INFO_"+ID_WARTATRM).value=="")
		{
			alert('$alert_masuk_flag_hide');
			document.getElementById("FLAG_HIDE_INFO_"+ID_WARTATRM).focus();
			bool_check = false;
		}
		else
		{	
		
		
			if(document.getElementById("ID_JENISADUAN_"+ID_WARTATRM).value=='16101')
			{
				
				if(document.getElementById("ID_KATEGORIADUAN_"+ID_WARTATRM).value=="")
				{
					alert('$alert_masuk_kategori_aduan');
					document.getElementById("ID_KATEGORIADUAN_"+ID_WARTATRM).focus();
					bool_check = false;
				}
				else if(document.getElementById("SUBJEK_ADUAN_"+ID_WARTATRM).value=="")
				{
					alert('$alert_masuk_subjek_aduan');
					document.getElementById("SUBJEK_ADUAN_"+ID_WARTATRM).focus();
					bool_check = false;
				}
				else if(document.getElementById("TARIKH_KEJADIAN_"+ID_WARTATRM).value!="" 
				&& document.getElementById("CHECK_TARIKH_KEJADIAN_"+ID_WARTATRM).value == 'false' )
				{
					alert('$alert_masuk_format_tarikh');
					document.getElementById("TARIKH_KEJADIAN_"+ID_WARTATRM).focus();
					bool_check = false;
				}
				else if((document.getElementById("JAM_"+ID_WARTATRM).value!="" 
				|| document.getElementById("MINIT_"+ID_WARTATRM).value!=""
				|| document.getElementById("JENIS_WAKTU_"+ID_WARTATRM).value!="") && document.getElementById("JAM_"+ID_WARTATRM).value=="")
				{
					alert('$alert_masuk_jam');
					document.getElementById("JAM_"+ID_WARTATRM).focus();
					bool_check = false;
				}
				else if((document.getElementById("JAM_"+ID_WARTATRM).value!="" 
				|| document.getElementById("MINIT_"+ID_WARTATRM).value!=""
				|| document.getElementById("JENIS_WAKTU_"+ID_WARTATRM).value!="") && document.getElementById("MINIT_"+ID_WARTATRM).value=="")
				{
					alert('$alert_masuk_minit');
					document.getElementById("MINIT_"+ID_WARTATRM).focus();
					bool_check = false;
				}
				else if((document.getElementById("JAM_"+ID_WARTATRM).value!="" 
				|| document.getElementById("MINIT_"+ID_WARTATRM).value!=""
				|| document.getElementById("JENIS_WAKTU_"+ID_WARTATRM).value!="") && document.getElementById("JENIS_WAKTU_"+ID_WARTATRM).value=="")
				{
					alert('$alert_masuk_jenis_waktu');
					document.getElementById("JENIS_WAKTU_"+ID_WARTATRM).focus();
					bool_check = false;
				}			
				else if(document.getElementById("KETERANGAN_ADUAN_"+ID_WARTATRM).value=="")
				{
					alert('$alert_masuk_keterangan_aduan');
					document.getElementById("KETERANGAN_ADUAN_"+ID_WARTATRM).focus();
					bool_check = false;
				}			
				
			}
			else
			{
				if(document.getElementById("KETERANGAN_ADUAN_"+ID_WARTATRM).value=="")
				{
					alert('$alert_masuk_keterangan_cadangan');
					document.getElementById("KETERANGAN_ADUAN_"+ID_WARTATRM).focus();
					bool_check = false;
				}	
			}
		}
	}
	*/
	
	/*
	if(bool_check==true)
	{
		var curent_status = document.getElementById("ID_STATUS_"+ID_WARTATRM).value;
		//alert('s: '+curent_status);
		if(curent_status=='16122')
		{
			bool_check  = valEditUI(ID_WARTATRM);
		}
	}
	
	
	if(bool_check==true)
	{
		var curent_status = document.getElementById("ID_STATUS_"+ID_WARTATRM).value;
		if(curent_status=='16127')
		{
			bool_check  = valEditBahagian(ID_WARTATRM);
		}
	}
	
	*/
		   
	return bool_check;
	//return false;
}

/*
function valEditUI(ID_WARTATRM)
{
	var bool_check = true;
	
	var id_status = document.getElementById("ID_STATUS_UI_"+ID_WARTATRM).value;
	
	if(id_status=="16123" || id_status=="16126")
	{			
			var ID_JENISTINDAKAN_element =  document.getElementById('ID_JENISTINDAKAN_'+ID_WARTATRM);
			if (typeof(ID_JENISTINDAKAN_element) != 'undefined' && ID_JENISTINDAKAN_element != null)
			{
				if(document.getElementById("ID_JENISTINDAKAN_"+ID_WARTATRM).value=="" )
				{
					alert('$alert_masuk_jenis_tindakan');
					document.getElementById("ID_JENISTINDAKAN_"+ID_WARTATRM).focus();
					bool_check = false;
				}
				else if((document.getElementById("ID_JENISTINDAKAN_"+ID_WARTATRM).value=="16171" || document.getElementById("ID_JENISTINDAKAN_"+ID_WARTATRM).value=="16172") && document.getElementById("ID_NEGERI_BAHAGIAN_"+ID_WARTATRM).value=="" )
				{
					alert('$alert_negeri_bahagian');
					document.getElementById("ID_NEGERI_BAHAGIAN_"+ID_WARTATRM).focus();
					bool_check = false;
				}
				else if((document.getElementById("ID_JENISTINDAKAN_"+ID_WARTATRM).value=="16171" || document.getElementById("ID_JENISTINDAKAN_"+ID_WARTATRM).value=="16172") && document.getElementById("ID_BAHAGIAN_"+ID_WARTATRM).value=="" )
				{
					alert('$alert_masuk_bahagian');
					document.getElementById("ID_BAHAGIAN_"+ID_WARTATRM).focus();
					bool_check = false;
				}
				else if(document.getElementById("ID_JENISTINDAKAN_"+ID_WARTATRM).value!="16176" && document.getElementById("ID_SUMBERTINDAKAN_"+ID_WARTATRM).value=="")
				{
					alert('$alert_masuk_sumber_tindakan');
					document.getElementById("ID_SUMBERTINDAKAN_"+ID_WARTATRM).focus();
					bool_check = false;
				}
				else if(document.getElementById("MAKLUMBALAS_UI_"+ID_WARTATRM).value=="")
				{
					alert('$alert_masuk_keterangan_ui');
					document.getElementById("MAKLUMBALAS_UI_"+ID_WARTATRM).focus();
					bool_check = false;
				}	
				else if(document.getElementById("ID_STATUS_UI_"+ID_WARTATRM).value=="")
				{
					alert('$alert_masuk_status_ui');
					document.getElementById("ID_STATUS_UI_"+ID_WARTATRM).focus();
					bool_check = false;
				}
			}
	}
	
	
	return bool_check;
}
*/



function valEditBahagian(ID_WARTATRM)
{
	//alert('x')
	var bool_check = true;
	
	var id_status = document.getElementById("ID_STATUS_BAHAGIAN_"+ID_WARTATRM).value;
	
	if(id_status=="16123")
	{
			if(document.getElementById("MAKLUMBALAS_BAHAGIAN_"+ID_WARTATRM).value=="")
			{
				alert('$alert_masuk_keterangan_bahagian');
				document.getElementById("MAKLUMBALAS_BAHAGIAN_"+ID_WARTATRM).focus();
				bool_check = false;
			}	
			else if(document.getElementById("ID_STATUS_BAHAGIAN_"+ID_WARTATRM).value=="")
			{
				alert('$alert_masuk_status_bahagian');
				document.getElementById("ID_STATUS_BAHAGIAN_"+ID_WARTATRM).focus();
				bool_check = false;
			}
	}
	return bool_check;
}
	

function highlight_item(search,span1)
{
	
	
	if(search != "" )
	{
		var word = search;
		searchArray = [word];	
		highlightStartTag = "<font style='color:black; background-color:yellow;'>";
		highlightEndTag = "</font>";
		
		  if(document.getElementById(span1)!=null)
		  {
		  	  temp_span1 = document.getElementById(span1);	  
			  var divText1 = temp_span1.innerHTML;		   
			  for (var i = 0; i < searchArray.length; i++) 
			  {
				  divText1 = doHighlight(divText1,searchArray[i], highlightStartTag, highlightEndTag);				  			  
			  }			  
			  temp_span1.innerHTML = divText1; 
		  }
		
		  
	 }
}

function doHighlight(bodyText, searchTerm, highlightStartTag, highlightEndTag) 
{
  
  if ((!highlightStartTag) || (!highlightEndTag)) {
    highlightStartTag = "<font style='color:blue; background-color:yellow;'>";
    highlightEndTag = "</font>";
  }  
  var newText = "";
  var i = -1;
  var lcSearchTerm = searchTerm.toLowerCase();
  var lcBodyText = bodyText.toLowerCase();
    
  while (bodyText.length > 0) {
    i = lcBodyText.indexOf(lcSearchTerm, i+1);
    if (i < 0) {
      newText += bodyText;
      bodyText = "";
    } else {
      // skip anything inside an HTML tag
      if (bodyText.lastIndexOf(">", i) >= bodyText.lastIndexOf("<", i)) {
        // skip anything inside a <script> block
        if (lcBodyText.lastIndexOf("/script>", i) >= lcBodyText.lastIndexOf("<script", i)) {
          newText += bodyText.substring(0, i) + highlightStartTag + bodyText.substr(i, searchTerm.length) + highlightEndTag;
          bodyText = bodyText.substr(i + searchTerm.length);
          lcBodyText = bodyText.toLowerCase();
          i = -1;
        }
      }
    }
  }
  
  //alert("kuar:"+newText) 
  return newText;
}

function getPageLocation(val)
{
	var tempScrollTop = $jquery(window).scrollTop();
	//alert("tempScrollTop :"+tempScrollTop);
	$jquery('#scrolPosition'+val).val(tempScrollTop);
	//document.getElementById("scrolPosition").value = tempScrollTop;
	return tempScrollTop;
}
function setPageLocation(val)
{
	//alert(" val : "+val)
$jquery(window).scrollTop(val);
}



function isNumberKey(evt,val,elem)
{
	
  var charCode = (evt.which) ? evt.which : evt.keyCode;
  //37, 39
  //alert(charCode);
  if (charCode != 46 && charCode != 45 && charCode != 37 && charCode != 39 && charCode > 31 && (charCode < 48 || charCode > 57))
  {
	 return false;
  }
	
  if((val.indexOf(".")>-1 && charCode == 46)  || (val.indexOf("-")>-1 && charCode == 45))	
  {
	  return false;
  }
  
  

  return true;
}

function convertFourDec(val,elem)
{
	if(val!="")
	{
	elem.value = parseFloat(val).toFixed(4);
	}
}

/*
function RemoveNonNumericConvert(elmnt,content)
{
      var strValidCharacters = "1234567890.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < content.length; intIndex++ )
      {
            strBuffer = content.substr( intIndex, 1 );
            // Is this a number
            if(strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      elmnt.value = strReturn;
}
*/

function checkFormatDate_V4(elmnt,content,err_span) {
	
	var date_str = content;
	if(date_str=="")
	{
		$jquery("#"+err_span).html("<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='true' >");
	}
	else
	{	
		if(isValidDate_V3(date_str)==true)
		{
			
			var arrStartDate = content.split("/");
			
			var date1 = new Date(arrStartDate[2], arrStartDate[1]-1, arrStartDate[0]);
			var date2 = new Date();
			//alert(' arrStartDate[2] : '+arrStartDate[2]+' arrStartDate[0] : '+arrStartDate[0]+' arrStartDate[1] : '+arrStartDate[1]);
			
			//alert(' content : '+content+' date1 : '+date1+' date2 : '+date2);
			
			if(date1>date2)
			{
				$jquery("#"+err_span).html("<font class='blink' color='red'>Tarikh melebihi hari ini!<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='false' ></font>");
			}
			else
			{
				$jquery("#"+err_span).html("<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='true' >");
			}
			
		}
		else
		{
			$jquery("#"+err_span).html("<font class='blink' color='red'>Format Tarikh Tidak Tepat!<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='false' ></font>");
		}
	}
}

function checkFormatDate_V3(elmnt,content,err_span) {
	
	var date_str = content;
	if(date_str=="")
	{
		$jquery("#"+err_span).html("<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='true' >");
	}
	else
	{	
		if(isValidDate_V3(date_str)==true)
		{
			$jquery("#"+err_span).html("<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='true' >");
			
		}
		else
		{
			$jquery("#"+err_span).html("<font class='blink' color='red'>Format Tarikh Tidak Tepat!<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='false' ></font>");
		}
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
function fromRadioToText(elem,val,textfield)
{
	document.getElementById(textfield).value = val;
}

function printWartaForm(divName) {
	
	//alert("divName : "+divName);
	
	var originalContents = document.body.innerHTML;
    $jquery("#"+divName+" :button").hide();
	  
	  
    var header=''
    var printContents = document.getElementById(divName).innerHTML;
	printContents = printContents.replace(/background-color:yellow/g , "background-color:");
	//.replace("background-color:yellow", "background-color:");	
    var footer ="";
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
   
    popupWin.document.write('<html><body onload="window.print()">'+header+'<div class="page-break" >'+ printContents + '</div>'+footer+'</html>');
    popupWin.document.close(); 
    //elementHide.style.display = "none";
	//$jquery("#"+divName+" :button").show();    
    document.body.innerHTML = originalContents;
    return false;
}

function printHideDiv(divName) {
	//var originalContents = document.body.innerHTML;
	//alert(" divName : "+divName);
    $jquery("#"+divName+" :button").hide();    
    
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
    //document.body.innerHTML = originalContents;
    //document.getElementById("carianTerperinci").value = carianTerperinci;
    elementHide.style.display = "none";
    $jquery("#"+divName+" :button").show();
    return false;    
}

function returnDropDownSelectedText(dropdownid,div_text)
{
	//alert("1"+div_text);
	if($jquery('#'+dropdownid).length)
	{
		var skillsSelect = document.getElementById(dropdownid);
		var selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
		//alert("2"+selectedText);
		$jquery("#"+div_text).html(selectedText);
	}
}

function returnDropDownSelectedText_value(dropdownid)
{
	//alert("1"+dropdownid);
	var return_value = '';
	if($jquery('#'+dropdownid).length)
	{
		//alert('masuk');
		var skillsSelect = document.getElementById(dropdownid);
		//alert(" dropdownid : "+dropdownid+" skillsSelect : "+skillsSelect);
		var selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
		//alert("2"+selectedText);
		//return selectedText;
		//$jquery("#"+div_text).html(selectedText);
		return_value = selectedText;
	}
	else
	{
		return_value = '';
	}
	return return_value;
}



function sendValueJenisPengguna(elem, value, nextField)
{
	document.getElementById(nextField).value = value;
}





function showFileSize(ID_WARTATRM) {
	var bool_check = true;
	//alert('1');
    var input, file;
    // (Can't use `typeof FileReader === "function"` because apparently
    // it comes back as "object" on some browsers. So just see if it's there
    // at all.)
    if (!window.FileReader) {
        alert("$alert_API_not_support");
		bool_check = false;
    }
	//alert('2');
    input = document.getElementById('AddLampiran'+ID_WARTATRM);
	file = input.files[0];
	//alert('3');
    if (!input) {
        //bodyAppend("p", "Um, couldn't find the fileinput element.");
		alert("$alert_tiada_field_input_file");
		bool_check = false;
    }
    else if (!input.files) {
        //bodyAppend("p", "This browser doesn't seem to support the `files` property of file inputs.");
		alert("$alert_browser_tak_support_file");
		bool_check = false;
    }
    else if (!file) {
        //bodyAppend("p", "Please select a file before clicking 'Load'");
		alert("$alert_tiada_upload_file");
		bool_check = false;
    }
    else if(file.size>3000000)
    {
        //bodyAppend("p", "File " + file.name + " is " + file.size + " bytes in size");
		
		if('$selectedLanguage'=='ENGLISH')
		{
			alert("File [" + file.name + "] is [" + file.size + " bytes] in size, Please make sure the file size is less then 3MB!!");
		}
		else
		{
			alert("Saiz Fail [" + file.name + "] adalah sebesar [" + file.size + " bytes], Pastikan saiz fail tidak melebihi 3MB!!");
		}
		bool_check = false;
    }
	//alert(' bool_check : '+bool_check);
	return bool_check;
}


function ValidateEmailFormat(inputText)  
{  
	//alert('emel : '+inputText);
	var check = true;
	var atpos = inputText.indexOf("@");
    var dotpos = inputText.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=inputText.length) {
        check = false;
    }
	//alert('check : '+check);
	return check;
}  


function checkCarianHISTORY(ID_WARTATRM)
{
	//alert("internalType : "+internalType+" USER_ID : "+USER_ID);
	var bool_check = true;
	
	//alert("TARIKH_MULA_HISTORY_"+internalType+""+user_id);
	
	var TM = document.getElementById("TARIKH_MULA_HISTORY_"+ID_WARTATRM).value;
	//alert("TM:"+TM+ " - "+"TARIKH_MULA_HISTORY_"+ID_WARTATRM);
	var TA = document.getElementById("TARIKH_AKHIR_HISTORY_"+ID_WARTATRM).value;	
	//alert("TA:"+TA);
	
	
	if(TM!="" && isValidDate_V3(TM)==false)
	{
	alert("Format Tarikh Mula Tidak Tepat! (dd/mm/yyyy)");
	document.getElementById("TARIKH_MULA_HISTORY_"+ID_WARTATRM).focus();
	bool_check = false;
	}
	else if(TA!="" && isValidDate_V3(TA)==false)
	{
	alert("Format Tarikh Akhir Tidak Tepat! (dd/mm/yyyy)");
	document.getElementById("TARIKH_AKHIR_HISTORY_"+ID_WARTATRM).focus();
	bool_check = false;
	}
	
	else if(TA!="" && TM!="" && convertTODateFormat(TM) > convertTODateFormat(TA))
	{
	alert("Pastikan Tarikh Mula Tidak Melebihi Tarikh Akhir");
	document.getElementById("TARIKH_MULA_HISTORY_"+ID_WARTATRM).focus();
	bool_check = false;
	}
		
	return bool_check;
}
function convertTODateFormat(date){
	   var parts = date.split("/");
	   return new Date(parts[2], parts[1] - 1, parts[0]);
}


function setTableFade(id){
	//alert(id);
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		document.getElementById(id).className = "classFade";
		if( $jquery('#'+id).length ) 
		{
			window.scrollTo(0, $jquery('#'+id).offset().top-10);
		}
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function checkLuas(elem,fieldAsal,jeniswarta,fieldbeforeedit)
{
	var str_luas_asal = document.getElementById(fieldAsal).value;
	if(str_luas_asal!= "" && elem.value != "" && jeniswarta == "B")
	{
		var field_check = document.getElementById('CHECK_'+elem.id);
		var alert_span = document.getElementById('span_CHECK_'+elem.id);
		var luas_asal = parseFloat(str_luas_asal);
		var curr_luas = parseFloat(elem.value)
		var luasSblmEdir = document.getElementById(fieldbeforeedit);
		
		if(luasSblmEdir.value != "")
		{
			curr_luas = curr_luas - parseFloat(luasSblmEdir.value);
		}
		
		
		if(curr_luas>luas_asal)
		{
			alert_span.innerHTML = "<span class='blink'><font color='red'>Pembatalan Luas adalah Melebihi Luas Asal!</font></span>";  
			field_check.value = "Y";
		}
		else
		{
			alert_span.innerHTML = "";
		    field_check.value = "N";
		}
	}	
}


function doCheckMain(id_main,id_sub){  

	var elem_main = document.getElementById(id_main);
	var elem_sub = document.getElementsByName(id_sub);
	var elem_sub_byID = document.getElementById(id_sub);
  	//alert(' elem_sub.length : '+elem_sub.length);
	//alert(elem_main.checked);
    if (elem_main.checked == true){
        if (elem_sub.length == null || elem_sub.length == 'undefined'){
            elem_sub_byID.checked = true;
        } else {
            for (i = 0; i < elem_sub.length; i++){
				//alert('masuk');
                elem_sub[i].checked = true;
            }
        }
    } else {
        if (elem_sub.length == null || elem_sub.length == 'undefined'){
            elem_sub_byID.checked = false;
        } else {
            for (i = 0; i < elem_sub.length; i++){
                elem_sub[i].checked = false;
            }
        }
    }
}
function doCheckSub(id_main,id_sub){  
		var elem_main = document.getElementById(id_main);
		var elem_sub = document.getElementsByName(id_sub);
		var elem_sub_byID = document.getElementById(id_sub);
		var c = 0;
		if(elem_sub.length > 1)
		{     
			  for (i = 0; i < elem_sub.length; i++)
			  {
			  if (elem_sub[i].checked == false)
			  {	 
			  c++
			  }
			  }  
		}
		else
		{
			//alert('x : '+elem_sub.checked);
			if (elem_sub_byID.checked == false)
			{	 
			c++;
			}	 	
		}	 
   	  
	  	//alert(c);
	  
		if(c>0)
		{	  
			elem_main.checked = false;
		}
		else
		{
			elem_main.checked = true;
		}
	  
}


function number_format (number, decimals, dec_point, thousands_sep) {
    var n = number, prec = decimals;

    var toFixedFix = function (n,prec) {
        var k = Math.pow(10,prec);
        return (Math.round(n*k)/k).toString();
    };

    n = !isFinite(+n) ? 0 : +n;
    prec = !isFinite(+prec) ? 0 : Math.abs(prec);
    var sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep;
    var dec = (typeof dec_point === 'undefined') ? '.' : dec_point;

    var s = (prec > 0) ? toFixedFix(n, prec) : toFixedFix(Math.round(n), prec); 
    //fix for IE parseFloat(0.55).toFixed(0) = 0;

    var abs = toFixedFix(Math.abs(n), prec);
    var _, i;

    if (abs >= 1000) {
        _ = abs.split(/\D/);
        i = _[0].length % 3 || 3;

        _[0] = s.slice(0,i + (n < 0)) +
               _[0].slice(i).replace(/(\d{3})/g, sep+'$1');
        s = _.join(dec);
    } else {
        s = s.replace('.', dec);
    }

    var decPos = s.indexOf(dec);
    if (prec >= 1 && decPos !== -1 && (s.length-decPos-1) < prec) {
        s += new Array(prec-(s.length-decPos-1)).join(0)+'0';
    }
    else if (prec >= 1 && decPos === -1) {
        s += dec+new Array(prec).join(0)+'0';
    }
    return s; 
}



</script>
