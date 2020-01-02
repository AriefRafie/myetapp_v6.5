
<style>


@media all {
	.page-break	{ display: none; }
}

@media print {
	
	.page-break	{ display: block; page-break-before: always;}

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
    padding: 1px 1px;
    font-size: 80%;
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
#parse("app/online/AduanPublic/SenaraiUtama.jsp")
</div>




<iframe id="uploadFrame" name="uploadFrame" style="display:none"></iframe>
<input type="hidden" id="setPageCoor" name="setPageCoor" value="$getPageCoor" >
#if($after_uploadLampiran=="Y")
<script>
	//alert('$mode');
	//contoh
	//callfunction after page load
	//alert('afterUpload 1');
	window.parent.viewListLampiran('$ID_ADUANPUBLIC_AFTERUPLOAD','$COOR_UPLOAD','$ID_STATUS','$ID_PENGADU','$ID_PEGAWAI_UI','$ID_PEGAWAI_BAHAGIAN','$mode','$docType');
	

</script>
#end

<script>
var refreshInterval_showtime = [];

function saveLampiran(div,command,ID_ADUANPUBLIC,ID_STATUS,ID_PENGADU,ID_PEGAWAI_UI,ID_PEGAWAI_BAHAGIAN,mode,docType)
{
	
	document.${formName}.action = "?_portal_module=ekptg.view.online.FrmAduanPublic&divLampiran="+div+"&command="+command+"&ID_STATUS="+ID_STATUS+"&ID_PENGADU="+ID_PENGADU+"&ID_PEGAWAI_UI="+ID_PEGAWAI_UI+"&ID_PEGAWAI_BAHAGIAN="+ID_PEGAWAI_BAHAGIAN+"&mode="+mode+"&ID_ADUANPUBLIC="+ID_ADUANPUBLIC+"&getPageCoor="+getPageCoor()+"&docType="+docType;
	document.${formName}.method="post";
	document.${formName}.target="uploadFrame";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();	
}
//contoh function download
function paparDoc(ID_ADUANPUBLICLAMPIRAN) {
    var url = "../servlet/ekptg.view.online.DisplayBlobLampiranAduan?ID_ADUANPUBLICLAMPIRAN="+ID_ADUANPUBLICLAMPIRAN;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
//-------------------------------------------

function viewListLampiran(ID_ADUANPUBLIC,COOR_UPLOAD,ID_STATUS,ID_PENGADU,ID_PEGAWAI_UI,ID_PEGAWAI_BAHAGIAN,mode,docType)
{
	//alert('afterUpload 2');
	document.getElementById("AddLampiran"+docType+ID_ADUANPUBLIC).value = "";
	doDivAjaxCall$formname('divSenaraiLampiran'+docType+ID_ADUANPUBLIC,'showLampiran','COOR_UPLOAD='+COOR_UPLOAD+'&ID_ADUANPUBLIC='+ID_ADUANPUBLIC+'&ID_STATUS='+ID_STATUS+"&ID_PENGADU="+ID_PENGADU+"&ID_PEGAWAI_UI="+ID_PEGAWAI_UI+"&ID_PEGAWAI_BAHAGIAN="+ID_PEGAWAI_BAHAGIAN+"&mode="+mode+"&docType="+docType);		
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


//document.getElementById("AddLampiran"+ID_ADUANPUBLIC).value = "";

function valEditUpload(ev,ID_ADUANPUBLIC,mode,BIL,rowCss,ID_STATUS,ID_PENGADU,ID_PEGAWAI_UI,ID_PEGAWAI_BAHAGIAN,docType)
{
	//var bool_check = true;
	//alert('docType : '+docType);
	if(ID_ADUANPUBLIC=="" && docType=="PENGADU")
	{
		//alert("$alert_mesej_before_upload");
		if(valEdit(ID_ADUANPUBLIC,'deraf')==false)
		{
			var file_element = document.getElementById("AddLampiran"+docType+ID_ADUANPUBLIC);	
			ev.preventDefault();
			file_element.focus();			
		}
		else
		{
			ev.preventDefault();
			doDivAjaxCall3$formname('div_row'+ID_ADUANPUBLIC,'saveDeraf','docType='+docType+'&ID_ADUANPUBLIC='+ID_ADUANPUBLIC+'&mode='+mode+'&commandFrom=&BIL='+BIL+'&rowCss='+rowCss+'&flagUpload=Y');
		}
		
	}
	else
	{
		 //alert("#AddLampiran"+docType+ID_ADUANPUBLIC);
		 $jquery("#AddLampiran"+docType+ID_ADUANPUBLIC).change(function() {
			 //alert("::::::::::::::: "+docType);
			 onchangeUpload(ID_ADUANPUBLIC,mode,ID_STATUS,ID_PENGADU,ID_PEGAWAI_UI,ID_PEGAWAI_BAHAGIAN,docType);
		 });	
	}
	//return bool_check;
}

function onchangeUpload(ID_ADUANPUBLIC,mode,ID_STATUS,ID_PENGADU,ID_PEGAWAI_UI,ID_PEGAWAI_BAHAGIAN,docType)
{
	//alert('1');
	if(showFileSize(ID_ADUANPUBLIC,docType)==true)
	{
		//alert('2');
		saveLampiran('divSenaraiLampiran'+docType+ID_ADUANPUBLIC,'saveLampiran',ID_ADUANPUBLIC,ID_STATUS,ID_PENGADU,ID_PEGAWAI_UI,ID_PEGAWAI_BAHAGIAN,mode,docType);
	}
	else 
	{
		//alert('3');
		var file_element = document.getElementById("AddLampiran"+docType+ID_ADUANPUBLIC);
		file_element.disabled = 'disabled';
  		setTimeout(function() { file_element.disabled = ''; }, 1);
	}
}

function valEdit(ID_ADUANPUBLIC,type)
{
	var bool_check = true;
	//var id_status = document.getElementById("ID_STATUS_"+ID_ADUANPUBLIC).value;
	
	
	if(type=="hantar")
	{
		if(document.getElementById("ID_SUMBERADUAN_"+ID_ADUANPUBLIC).value=="")
		{
			alert('$alert_masuk_sumber_aduan');
			document.getElementById("ID_SUMBERADUAN_"+ID_ADUANPUBLIC).focus();
			bool_check = false;
		}
		else if(document.getElementById("ID_SUMBERADUAN_"+ID_ADUANPUBLIC).value!="16101" && document.getElementById("NAMA_PENGADU_"+ID_ADUANPUBLIC).value=="")
		{
			//nama pengadu
			alert('$alert_masuk_nama_pengadu');
			document.getElementById("NAMA_PENGADU_"+ID_ADUANPUBLIC).focus();
			bool_check = false;
		}
		else if(document.getElementById("ID_SUMBERADUAN_"+ID_ADUANPUBLIC).value!="16101" && document.getElementById("TEL_PENGADU_"+ID_ADUANPUBLIC).value=="")
		{
			//tel pengadu
			alert('$alert_masuk_tel_pengadu');
			document.getElementById("TEL_PENGADU_"+ID_ADUANPUBLIC).focus();
			bool_check = false;
		}
		
		
		else if(document.getElementById("ID_SUMBERADUAN_"+ID_ADUANPUBLIC).value!="16101" 
		&& (document.getElementById("EMEL_PENGADU_"+ID_ADUANPUBLIC).value=="" 
		|| ValidateEmailFormat(document.getElementById("EMEL_PENGADU_"+ID_ADUANPUBLIC).value)==false)
		)
		{
			//emel pengadu
			alert('$alert_masuk_emel_pengadu');
			document.getElementById("EMEL_PENGADU_"+ID_ADUANPUBLIC).focus();
			bool_check = false;
		}	
		
		
		
		else if(document.getElementById("ID_JENISADUAN_"+ID_ADUANPUBLIC).value=="")
		{
			alert('$alert_masuk_jenis_aduan');
			document.getElementById("ID_JENISADUAN_"+ID_ADUANPUBLIC).focus();
			bool_check = false;
		}
		else if(document.getElementById("FLAG_HIDE_INFO_"+ID_ADUANPUBLIC).value=="")
		{
			alert('$alert_masuk_flag_hide');
			document.getElementById("FLAG_HIDE_INFO_"+ID_ADUANPUBLIC).focus();
			bool_check = false;
		}
		else
		{	
		
		
			if(document.getElementById("ID_JENISADUAN_"+ID_ADUANPUBLIC).value=='16101')
			{
				
				if(document.getElementById("ID_KATEGORIADUAN_"+ID_ADUANPUBLIC).value=="")
				{
					alert('$alert_masuk_kategori_aduan');
					document.getElementById("ID_KATEGORIADUAN_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
				else if(document.getElementById("SUBJEK_ADUAN_"+ID_ADUANPUBLIC).value=="")
				{
					alert('$alert_masuk_subjek_aduan');
					document.getElementById("SUBJEK_ADUAN_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
				else if(document.getElementById("TARIKH_KEJADIAN_"+ID_ADUANPUBLIC).value!="" 
				&& document.getElementById("CHECK_TARIKH_KEJADIAN_"+ID_ADUANPUBLIC).value == 'false' )
				{
					alert('$alert_masuk_format_tarikh');
					document.getElementById("TARIKH_KEJADIAN_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
				else if((document.getElementById("JAM_"+ID_ADUANPUBLIC).value!="" 
				|| document.getElementById("MINIT_"+ID_ADUANPUBLIC).value!=""
				|| document.getElementById("JENIS_WAKTU_"+ID_ADUANPUBLIC).value!="") && document.getElementById("JAM_"+ID_ADUANPUBLIC).value=="")
				{
					alert('$alert_masuk_jam');
					document.getElementById("JAM_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
				else if((document.getElementById("JAM_"+ID_ADUANPUBLIC).value!="" 
				|| document.getElementById("MINIT_"+ID_ADUANPUBLIC).value!=""
				|| document.getElementById("JENIS_WAKTU_"+ID_ADUANPUBLIC).value!="") && document.getElementById("MINIT_"+ID_ADUANPUBLIC).value=="")
				{
					alert('$alert_masuk_minit');
					document.getElementById("MINIT_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
				else if((document.getElementById("JAM_"+ID_ADUANPUBLIC).value!="" 
				|| document.getElementById("MINIT_"+ID_ADUANPUBLIC).value!=""
				|| document.getElementById("JENIS_WAKTU_"+ID_ADUANPUBLIC).value!="") && document.getElementById("JENIS_WAKTU_"+ID_ADUANPUBLIC).value=="")
				{
					alert('$alert_masuk_jenis_waktu');
					document.getElementById("JENIS_WAKTU_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}			
				else if(document.getElementById("KETERANGAN_ADUAN_"+ID_ADUANPUBLIC).value=="")
				{
					alert('$alert_masuk_keterangan_aduan');
					document.getElementById("KETERANGAN_ADUAN_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}			
				
			}
			else
			{
				if(document.getElementById("ID_JENISADUAN_"+ID_ADUANPUBLIC).value=='161021' && document.getElementById("ID_KATEGORIPERTANYAAN_"+ID_ADUANPUBLIC).value == "")
				{
					alert('$alert_masuk_kategori_pertanyaan');
					document.getElementById("ID_KATEGORIPERTANYAAN_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				
				}
				else if(document.getElementById("KETERANGAN_ADUAN_"+ID_ADUANPUBLIC).value=="")
				{
					alert('$alert_masuk_keterangan_cadangan');
					document.getElementById("KETERANGAN_ADUAN_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}	
			}
		}
	}
	
	if(bool_check==true)
	{
		var curent_status = document.getElementById("ID_STATUS_"+ID_ADUANPUBLIC).value;
		//alert('s: '+curent_status);
		if(curent_status=='16122')
		{
			bool_check  = valEditUI(ID_ADUANPUBLIC);
		}
	}
	
	
	if(bool_check==true)
	{
		var curent_status = document.getElementById("ID_STATUS_"+ID_ADUANPUBLIC).value;
		if(curent_status=='16127')
		{
			bool_check  = valEditBahagian(ID_ADUANPUBLIC);
		}
	}
	
	
		   
	return bool_check;
	//return false;
}


function onfocusEmail(ID_ADUANPUBLIC, elem)
{
	if(elem.value == "")
	{
		if(document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value=="")
		{
			document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).focus();
		}
		else if(document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value!="" && document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).value=="")
		{
			document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).focus();
		}
		else if(document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value!="" && document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).value!=""
		&& document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).value=="")
		{
			document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).focus();
		}
		else if(document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value!="" && document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).value!=""
		&& document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).value!="" && document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).value=="")
		{
			document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).focus();
		}
	}
}


function valEditUI(ID_ADUANPUBLIC)
{
	var bool_check = true;
	
	var id_status = document.getElementById("ID_STATUS_UI_"+ID_ADUANPUBLIC).value;
	
	//alert(document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).value+" id_status :"+id_status);
	
	if(id_status=="16123" || id_status=="16126")
	{			
	
			var ID_JENISTINDAKAN_element =  document.getElementById('ID_JENISTINDAKAN_'+ID_ADUANPUBLIC);
			if (typeof(ID_JENISTINDAKAN_element) != 'undefined' && ID_JENISTINDAKAN_element != null)
			{
				if(document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).value=="" )
				{
					alert('$alert_masuk_jenis_tindakan');
					document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
				else if((document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).value=="16171" || document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).value=="16172") && document.getElementById("ID_NEGERI_BAHAGIAN_"+ID_ADUANPUBLIC).value=="" )
				{
					alert('$alert_negeri_bahagian');
					document.getElementById("ID_NEGERI_BAHAGIAN_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
				else if((document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).value=="16171" || document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).value=="16172") && document.getElementById("ID_BAHAGIAN_"+ID_ADUANPUBLIC).value=="" )
				{
					alert('$alert_masuk_bahagian');
					document.getElementById("ID_BAHAGIAN_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
				
				else if(document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).value != "16176" && document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).value != "16171" && document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).value != "16172" && document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value=="")
				{
					alert('$alert_mandatori_emel_bahagian');
					document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
				
				else if((document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value!="" 
				&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value)==false)
				)
				{
					//emel pengadu
					alert('$alert_masuk_emel_bahagian');
					document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}	
				else if((document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).value!="" 
				&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).value)==false)
				)
				{
					//emel pengadu
					alert('$alert_masuk_emel_bahagian');
					document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
				else if((document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).value!="" 
				&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).value)==false)
				)
				{
					//emel pengadu
					alert('$alert_masuk_emel_bahagian');
					document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}	
				else if((document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).value!="" 
				&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).value)==false)
				)
				{
					//emel pengadu
					alert('$alert_masuk_emel_bahagian');
					document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
						
				else if((document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).value=="16171" || document.getElementById("ID_JENISTINDAKAN_"+ID_ADUANPUBLIC).value=="16172") && document.getElementById("ID_SUMBERTINDAKAN_"+ID_ADUANPUBLIC).value=="")
				{
					
					alert('$alert_masuk_sumber_tindakan');
					document.getElementById("ID_SUMBERTINDAKAN_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
							
				else if(document.getElementById("MAKLUMBALAS_UI_"+ID_ADUANPUBLIC).value=="")
				{
					alert('$alert_masuk_keterangan_ui');
					document.getElementById("MAKLUMBALAS_UI_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}	
				else if(document.getElementById("ID_STATUS_UI_"+ID_ADUANPUBLIC).value=="")
				{
					alert('$alert_masuk_status_ui');
					document.getElementById("ID_STATUS_UI_"+ID_ADUANPUBLIC).focus();
					bool_check = false;
				}
			}
			
			
			
			
			/*
			if((document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value!="" 
			&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value)==false)
			)
			{
				//emel pengadu
				alert('$alert_masuk_emel_bahagian');
				document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).focus();
				bool_check = false;
			}	
			else if((document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).value!="" 
			&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).value)==false)
			)
			{
				//emel pengadu
				alert('$alert_masuk_emel_bahagian');
				document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).focus();
				bool_check = false;
			}
			else if((document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).value!="" 
			&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).value)==false)
			)
			{
				//emel pengadu
				alert('$alert_masuk_emel_bahagian');
				document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).focus();
				bool_check = false;
			}	
			else if((document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).value!="" 
			&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).value)==false)
			)
			{
				//emel pengadu
				alert('$alert_masuk_emel_bahagian');
				document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).focus();
				bool_check = false;
			}
			*/
			
			
			
			susunEmel(ID_ADUANPUBLIC,document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC),1);
			susunEmel(ID_ADUANPUBLIC,document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC),2);
			susunEmel(ID_ADUANPUBLIC,document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC),3);
			susunEmel(ID_ADUANPUBLIC,document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC),4);
			
			
	}
	
	
	return bool_check;
}
/*
function sendEmelToBahagian(ID_ADUANPUBLICt)
{
		var bool_check = true;
		
		if((document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value!="" 
		&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value)==false)
		)
		{
			//emel pengadu
			alert('$alert_masuk_emel_bahagian');
			document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).focus();
			bool_check = false;
		}	
		else if((document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).value!="" 
		&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).value)==false)
		)
		{
			//emel pengadu
			alert('$alert_masuk_emel_bahagian');
			document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).focus();
			bool_check = false;
		}
		else if((document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).value!="" 
		&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).value)==false)
		)
		{
			//emel pengadu
			alert('$alert_masuk_emel_bahagian');
			document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).focus();
			bool_check = false;
		}	
		else if((document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).value!="" 
		&& ValidateEmailFormat(document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).value)==false)
		)
		{
			//emel pengadu
			alert('$alert_masuk_emel_bahagian');
			document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).focus();
			bool_check = false;
		}
		
		susunEmel(ID_ADUANPUBLIC,document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC),1);
		susunEmel(ID_ADUANPUBLIC,document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC),2);
		susunEmel(ID_ADUANPUBLIC,document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC),3);
		susunEmel(ID_ADUANPUBLIC,document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC),4);
		
		return bool_check;
}
*/

function hantarLog(ID_ADUANPUBLIC,FLAG_SEMENTARA,mode,commandFrom,BIL,rowCss,elem)
{
	if(elem.value == '$button_hantar_aduan' )
	{
		if(confirm('$label_adakah_pasti')){
		doDivAjaxCall$formname('div_row'+ID_ADUANPUBLIC,'save','ID_ADUANPUBLIC='+ID_ADUANPUBLIC+'&FLAG_SEMENTARA='+FLAG_SEMENTARA+'&mode='+mode+'&commandFrom='+commandFrom+'&BIL='+BIL+'&rowCss='+rowCss);	
		}
	}
	else
	{
		doDivAjaxCall$formname('div_row'+ID_ADUANPUBLIC,'save','ID_ADUANPUBLIC='+ID_ADUANPUBLIC+'&FLAG_SEMENTARA='+FLAG_SEMENTARA+'&mode='+mode+'&commandFrom='+commandFrom+'&BIL='+BIL+'&rowCss='+rowCss);
	}
}


function renameButtonHantar(ID_BUTTON,val)
{
	//alert(val);
	//if()
	//button_hantar_aduan
	//button_simpan
	
	if(val=='16123' || val=='16126')
	{
		document.getElementById(ID_BUTTON).value = '$button_hantar_aduan';
	}
	else if(val=='16122' || val=='16127')
	{	
		document.getElementById(ID_BUTTON).value = '$button_simpan';
	}
}


function susunEmel(ID_ADUANPUBLIC,elem,turut)
{
	if(document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value=="" && elem.value != "" && document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC) != elem && turut > 1)
	{
		document.getElementById("EMEL_BAHAGIAN_1_"+ID_ADUANPUBLIC).value = elem.value;	
		elem.value = "";	
	}
	else if(document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).value=="" && elem.value != "" && document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC) != elem && turut > 2)
	{
		document.getElementById("EMEL_BAHAGIAN_2_"+ID_ADUANPUBLIC).value = elem.value;	
		elem.value = "";		
	}
	else if(document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).value=="" && elem.value != "" && document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC) != elem && turut > 3)
	{
		document.getElementById("EMEL_BAHAGIAN_3_"+ID_ADUANPUBLIC).value = elem.value;	
		elem.value = "";		
	}/*
	else if(document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).value=="" && elem.value != "" && document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC) != elem && turut > 4)
	{
		document.getElementById("EMEL_BAHAGIAN_4_"+ID_ADUANPUBLIC).value = elem.value;	
		elem.value = "";		
	}*/
		
	
}



function valEditBahagian(ID_ADUANPUBLIC)
{
	//alert('x')
	var bool_check = true;
	
	var id_status = document.getElementById("ID_STATUS_BAHAGIAN_"+ID_ADUANPUBLIC).value;
	
	if(id_status=="16123")
	{
			if(document.getElementById("MAKLUMBALAS_BAHAGIAN_"+ID_ADUANPUBLIC).value=="")
			{
				alert('$alert_masuk_keterangan_bahagian');
				document.getElementById("MAKLUMBALAS_BAHAGIAN_"+ID_ADUANPUBLIC).focus();
				bool_check = false;
			}	
			else if(document.getElementById("ID_STATUS_BAHAGIAN_"+ID_ADUANPUBLIC).value=="")
			{
				alert('$alert_masuk_status_bahagian');
				document.getElementById("ID_STATUS_BAHAGIAN_"+ID_ADUANPUBLIC).focus();
				bool_check = false;
			}
	}
	return bool_check;
}
	

function highlight_item(search,span1)
{
	//alert(" search : "+search+" span1 : "+span1)
	if(search != "")
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

function afterPilihTindakan(td,command,ID_ADUANPUBLIC,ID_JENISTINDAKAN)
{
	doDivAjaxCall3$formname('tdJenisTindakan$view.ID_ADUANPUBLIC','showBahagian','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=edit&ID_JENISTINDAKAN='+ID_JENISTINDAKAN);	
}


function printAduanForm(divName,id_aduanpublic) {
	var originalContents = document.body.innerHTML;
    $jquery("#"+divName+" :button").hide();
	//alert(1);
	//$jquery("#divLampiranPENGADU_"+id_aduanpublic).hide();
	document.getElementById("divLampiranPENGADU_"+id_aduanpublic).style.display = "none";
	//alert(2);
	//$jquery("#divLampiranUI_"+id_aduanpublic).hide();
	document.getElementById("divLampiranUI_"+id_aduanpublic).style.display = "none";
	//alert(3);
	//$jquery("#divLampiranBAHAGIAN_"+id_aduanpublic).hide();
	document.getElementById("divLampiranBAHAGIAN_"+id_aduanpublic).style.display = "none";
	//alert(4);
	$jquery("#tips_"+id_aduanpublic).hide();  
	$jquery("#divKronologi_"+id_aduanpublic).hide();
	$jquery("#divDisplayAlert_"+id_aduanpublic).hide();
	  
	  
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
    $jquery("#"+divName+" :button").hide();    
    //alert("1");
    var elementHide =  document.getElementById(divName);
	
	/*
    if (typeof(elementHide) != 'undefined' && elementHide != null)
    {
    	elementHide.style.display = "";
    } 
	*/   
	//tukar ke
	if ($jquery('#'+divName).length > 0)
    {
    	if(elementHide.style.display == 'none')
		{
    		elementHide.style.display = "";
		}
    }
    var printContents = document.getElementById(divName).innerHTML;  
	elementHide.style.display = "none";
	
	 
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()"><div class="page-break" >'+ printContents + '</div></html>');
    popupWin.document.close(); 
    //document.body.innerHTML = originalContents;
    //document.getElementById("carianTerperinci").value = carianTerperinci;
    
    $jquery("#"+divName+" :button").show();
    return false;
    
    
    
}

function returnDropDownSelectedText(dropdownid,div_text)
{
	//alert("1"+div_text);
	var skillsSelect = document.getElementById(dropdownid);
	var selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
	//alert("2"+selectedText);
	$jquery("#"+div_text).html(selectedText);
}

function returnDropDownSelectedText_value(dropdownid)
{
	//alert("1"+div_text);
	var skillsSelect = document.getElementById(dropdownid);
	var selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
	//alert("2"+selectedText);
	return selectedText;
	//$jquery("#"+div_text).html(selectedText);
}


function sendValueJenisPengguna(elem, value, nextField)
{
	document.getElementById(nextField).value = value;
}





function showFileSize(ID_ADUANPUBLIC,docType) {
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
    input = document.getElementById('AddLampiran'+docType+ID_ADUANPUBLIC);
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


</script>
