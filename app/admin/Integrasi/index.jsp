
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
#parse("app/admin/Integrasi/CarianInteg.jsp")
</div>

<br>
<div id="div_SenaraiInteg">
#parse("app/admin/Integrasi/SenaraiUtama.jsp")
</div>


<iframe id="uploadFrame" name="uploadFrame" style="display:none"></iframe>


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

function highlight(size_rekod,search,nama_list)
{
	//alert(" size_rekod : "+size_rekod+" search : "+search+" nama_list :" +nama_list)
	//alert("1");
	if(search != "")
	{
		var word = search;
		searchArray = [word];	
		highlightStartTag = "<font style='color:black; background-color:yellow;'>";
		highlightEndTag = "</font>";
		//alert("2");
		  for (x = 0; x < parseInt(size_rekod); x++)
		  {
		  var span1 = "span1"+nama_list+(x+1);
		  var span2 = "span2"+nama_list+(x+1);
		  var span3 = "span3"+nama_list+(x+1);
		  var span4 = "span4"+nama_list+(x+1);
		  var span5 = "span5"+nama_list+(x+1);
		  var span6 = "span6"+nama_list+(x+1);
		  var span7 = "span7"+nama_list+(x+1);
		  //alert(span1+" [span 1] : "+document.getElementById(span1));
		  
		  if(document.getElementById(span1)!=null)
		  {
		  	  temp_span1 = document.getElementById(span1);
		  	  temp_span2 = document.getElementById(span2); 
		  	  temp_span3 = document.getElementById(span3);
		  	  temp_span4 = document.getElementById(span4); 
		  	  temp_span5 = document.getElementById(span5);
		  	  temp_span6 = document.getElementById(span6); 
		  	  temp_span7 = document.getElementById(span7);		  	   
		  
			  var divText1 = temp_span1.innerHTML;
			  var divText2 = temp_span2.innerHTML;
			  var divText3 = temp_span3.innerHTML;
			  var divText4 = temp_span4.innerHTML;
			  var divText5 = temp_span5.innerHTML;
			  var divText6 = temp_span6.innerHTML;
			  var divText7 = temp_span7.innerHTML;
		   
			  for (var i = 0; i < searchArray.length; i++) 
			  {
				  divText1 = doHighlight(divText1,searchArray[i], highlightStartTag, highlightEndTag);	
				  divText2 = doHighlight(divText2,searchArray[i], highlightStartTag, highlightEndTag);	
				  divText3 = doHighlight(divText3,searchArray[i], highlightStartTag, highlightEndTag);	
				  divText4 = doHighlight(divText4,searchArray[i], highlightStartTag, highlightEndTag);
				  divText5 = doHighlight(divText5,searchArray[i], highlightStartTag, highlightEndTag);	
				  divText6 = doHighlight(divText6,searchArray[i], highlightStartTag, highlightEndTag);
				  divText7 = doHighlight(divText7,searchArray[i], highlightStartTag, highlightEndTag);				  
			  }
			  
			  temp_span1.innerHTML = divText1; 
			  temp_span2.innerHTML = divText2; 
			  temp_span3.innerHTML = divText3; 
			  temp_span4.innerHTML = divText4; 
			  temp_span5.innerHTML = divText5; 
			  temp_span6.innerHTML = divText6; 
			  temp_span7.innerHTML = divText7; 
			  
		  }
			 
		  
		  }
		  //alert("3");
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

function avoidScriptInjection(elem,content) {
    var div = document.createElement('div');
    div.innerHTML = content;
    var scripts = div.getElementsByTagName('script');
    var i = scripts.length;
    while (i--) {
      scripts[i].parentNode.removeChild(scripts[i]);
    }
    elem.value =  div.innerHTML;
    //return div.innerHTML;
  }
  
function printHideDiv(divName,type) {

	var CT_NAMA_ = document.getElementById("CT_NAMA_"+type).value;
	var CT_ID_NEGERI_ = document.getElementById("CT_ID_NEGERI_"+type).value;
	var CT_FLAG_AKTIF_ = document.getElementById("CT_FLAG_AKTIF_"+type).value;
	var CT_KATEGORI_ = document.getElementById("CT_KATEGORI_"+type).value;
	
	var header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>Maklumat Integrasi </b></div><br></header>'
	var footer ="";
	
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
    popupWin.document.write('<html><body onload="window.print()">'+header+'<div class="page-break" >'+ printContents + '</div>'+footer+'</html>');
    popupWin.document.close(); 
    //document.body.innerHTML = originalContents;

	document.getElementById("CT_NAMA_"+type).value = CT_NAMA_ ;
	document.getElementById("CT_ID_NEGERI_"+type).value = CT_ID_NEGERI_ ;
	document.getElementById("CT_FLAG_AKTIF_"+type).value = CT_FLAG_AKTIF_ ;
	document.getElementById("CT_KATEGORI_"+type).value = CT_KATEGORI_ ;
	
    elementHide.style.display = "none";
    $jquery("#"+divName+" :button").show();
    return false;
    
    
    
}

function printHideDiv2(divName,type) {

//	var CT_NAMA_ = document.getElementById("CT_NAMA_"+type).value;
//	var CT_ID_NEGERI_ = document.getElementById("CT_ID_NEGERI_"+type).value;
//	var CT_FLAG_AKTIF_ = document.getElementById("CT_FLAG_AKTIF_"+type).value;
//	var CT_KATEGORI_ = document.getElementById("CT_KATEGORI_"+type).value;-->
	
	var header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>Maklumat Lengkap Integrasi </b></div><br></header>'
	var footer ="";
	
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
    popupWin.document.write('<html><body onload="window.print()">'+header+'<div class="page-break" >'+ printContents + '</div>'+footer+'</html>');
    popupWin.document.close(); 
    //document.body.innerHTML = originalContents;

//	document.getElementById("CT_NAMA_"+type).value = CT_NAMA_ ;
//	document.getElementById("CT_ID_NEGERI_"+type).value = CT_ID_NEGERI_ ;
//	document.getElementById("CT_FLAG_AKTIF_"+type).value = CT_FLAG_AKTIF_ ;
//	document.getElementById("CT_KATEGORI_"+type).value = CT_KATEGORI_ ;
	
    elementHide.style.display = "none";
    $jquery("#"+divName+" :button").show();
    return false;
 
}


function valEditIntegrasi(id_integrasi,type)
{
	   var bool_check = true;
	 /*  if(document.getElementById("JENIS_INTEGRASI_"+id_integrasi).value=="")
	   {
		   alert("Sila Pilih Jenis Integrasi!");
		   document.getElementById("JENIS_INTEGRASI_"+id_integrasi).focus();
		   bool_check = false;
	   }
	   else*/ if(document.getElementById("NAMA_"+id_integrasi).value=="")
	   {
		   alert("Masukkan Nama Agensi/Sistem!");
		   document.getElementById("NAMA_"+id_integrasi).focus();
		   bool_check = false;
	   }	   
	   else if(document.getElementById("KOD_AGENSI_"+id_integrasi).value=="")
	   {
		   alert("Masukkan Emel Pengguna!");
		   document.getElementById("KOD_AGENSI_"+id_integrasi).focus();
		   bool_check = false;
	   }
	   else if(ValidateEmailFormat(document.getElementById("EMEL_"+id_integrasi).value)==false)
	   {
		   alert("Masukkan Emel Dengan Format Yang Sah!");
		   document.getElementById("EMEL_"+id_integrasi).focus();
		   bool_check = false;
	   }
	   
	   else if(document.getElementById("KATEGORI_"+id_integrasi).value=="")
	   {
		   alert("Masukkan Kategori Agensi!");
		   document.getElementById("KATEGORI_"+id_integrasi).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("ID_NEGERI_"+id_integrasi).value=="")
	   {
		   alert("Masukkan Negeri!");
		   document.getElementById("ID_NEGERI_"+id_integrasi).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("FLAG_AKTIF_"+id_integrasi).value=="")
	   {
		   alert("Sila Pilih Status Agensi!");
		   document.getElementById("FLAG_AKTIF_"+id_integrasi).focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
}

function ValidateEmailFormat(inputText)  
{  
	var check = true;
	var atpos = inputText.indexOf("@");
    var dotpos = inputText.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=inputText.length) {
        check = false;
    }
	
	return check;
} 


function uploadDoc(elem,idInteg,div)
{
	document.${formName}.action = "?_portal_module=ekptg.view.admin.IntegrasiReg&command=simpanDokumen&ID_INTEGRASI="+idInteg+"&returnDivUpload="+div;
	document.${formName}.method="post";
	document.${formName}.target="uploadFrame";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();	
}

if('$after_saveDOC'=="Y")
{
		
	window.parent.viewDoc('$returnDivUpload','$commandDoc','$ID_INTEGRASI_AFTERUPLOAD')
}

function viewDoc(div,command,idInteg)
{
	doDivAjaxCall$formname(div,command,'ID_INTEGRASI='+idInteg);
	
	if ($after_uploadLampiran == "Y")
	{
	document.getElementById("tambahDoc").style.display="none";
	}		 	  
	
}

function paparDoc(idDoc) {
    var url = "../servlet/ekptg.view.admin.DisplayBlobLampiranIntegrasi?ID_DOKUMEN="+idDoc;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>
