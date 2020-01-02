




<style>
@media all {
	.page-break	{ display: none; }
}

@media print {
	.page-break	{ display: block; page-break-before: always; }
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
.underline_td_main {
	border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 120%;
    color : white;
    text-shadow: 2px 2px 5px black;
}
.underline_td_main_view {
	padding: 3px 1px;
    font-size: 100%;
    color : black;
    text-shadow: 2px 2px 5px white;
   
}

.underline_td_main_anak {
    padding: 3px 1px;
    font-size: 75%;
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

</style>


#parse("app/admin/RoleModule/CarianTerperinci.jsp")
<div id="div_senaraiUtama" >
#parse("app/admin/RoleModule/SenaraiUtama.jsp")
</div>


<iframe id="uploadTrg" name="uploadTrg" style="display:none"></iframe>
<input type="hidden" id="setPageCoor" name="setPageCoor" value="$getPageCoor" >
<script>

function paparDoc(id_dokumen) {
    var url = "../servlet/lebah.app.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function saveGroupRole(div,command,GROUPUNIK,CSS_TITLE,CURRENT_CSS_NAME,MODUL_ID)
{
	var getvalue_CSS_TITLE = $jquery('#CSS_TITLE_'+GROUPUNIK).val();
	var getvalue_CSS_NAME = $jquery('#CSS_NAME_'+GROUPUNIK).val();
	var getvalue_MODUL_ID = $jquery('#MODUL_ID_'+GROUPUNIK).val();
	
	document.${formName}.action = "?_portal_module=lebah.app.RoleModule&divGroup="+div
			+"&command=saveGroup&GROUPUNIK="+GROUPUNIK
			+"&CSS_TITLE="+CSS_TITLE
			+"&CURRENT_CSS_NAME="+CURRENT_CSS_NAME
			+"&getvalue_CSS_TITLE="+getvalue_CSS_TITLE+"&getvalue_CSS_NAME="+getvalue_CSS_NAME+"&getvalue_MODUL_ID="+getvalue_MODUL_ID+
			'&getPageCoor='+getPageCoor();
	document.${formName}.method="post";
	document.${formName}.target="uploadTrg";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();	
//	doDivAjaxCall$formname('div_rowGroup$viewGroup.GROUPUNIK','viewGroup','GROUPUNIK=$viewGroup.GROUPUNIK&CSS_TITLE=$viewGroup.CSS_TITLE&CURRENT_CSS_NAME=$viewGroup.CSS_NAME&MODE=VIEW&SAVE=Y');
}

function viewGroupRole(div,command,GROUPUNIK,CSS_TITLE)
{
	doDivAjaxCall$formname('div_senaraiUtama','carianUtama','carianTerperinci='+$jquery('#carianTerperinci').val());	 
}





//alert('$afterUpload');
/*
if('$afterUpload'=="Y")
{
	//window.parent.zipPhoneCallback('xxxxxxx');
	//parent.$jquery(parent.document).trigger("eventhandler");
	//parent.$('body').trigger('eventhandler')
	//doDivAjaxCall$formname('div_senaraiUtama','carianUtama','carianTerperinci=');	 
}
*/
if('$after_saveGroup'=="Y")
{
	window.parent.viewGroupRole('$divGroup','$command','$GROUPUNIK','$CSS_TITLE')
}

//$jquery(document).ready(function () {
//alert("1");

function zipPhoneCallback(zipphone) {
    //alert("zipphone = " + zipphone);
    //doDivAjaxCall$formname('div_senaraiUtama','carianUtama','carianTerperinci=');	
}

$jquery(function(){
    $jquery(document).on('eventhandler', function() {               
        alert('event was fired');
    });
}); 


window.setTimeout(function() 
{
	document.${formName}.target="";
},1);
//alert("2");
//});

function uploadIframe()
{
	document.${formName}.action = "?_portal_module=lebah.app.RoleModule&command=Upload";
	document.${formName}.method="post";
	document.${formName}.target="uploadTrg";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();	
}
function uploadDokumenTest()
{
	document.${formName}.action = "?_portal_module=lebah.app.RoleModule&command=Upload";
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";	
	doDivAjaxCall$formname('div_upload','Upload','');
	
}
/*
function uploadDokumenTestz(){
    var url = '?_portal_module=lebah.app.RoleModule';
    var pars = '';
    pars = pars + '&command=Upload';
    var target = 'div_upload';
    var myAjax = new Ajax.Updater(target, url, {method: 'post',enctype='multipart/form-data', parameters: pars});
}
*/

function printDiv(divName,GROUPUNIK,ROLEUNIK) {
    var originalContents = document.body.innerHTML;
    $jquery("#"+divName+" :button").hide();
    
    
    var elementModul =  document.getElementById('div_carianModul_'+GROUPUNIK+ROLEUNIK);
    if (typeof(elementModul) != 'undefined' && elementModul != null)
    {
    	elementModul.style.display = "none";
    }
    
    
    var elementModulCB =  document.getElementById('CB_Modul_'+GROUPUNIK+ROLEUNIK);
    if (typeof(elementModulCB) != 'undefined' && elementModulCB != null)
    {
    	document.getElementById('show_cb_Modul_'+GROUPUNIK+ROLEUNIK).style.display = "none";
    	if(elementModulCB.checked==true)
    	{
    		var elementModulPrint =  document.getElementById('div_ModulforPrint_'+GROUPUNIK+ROLEUNIK);
		    if (typeof(elementModulPrint) != 'undefined' && elementModulPrint != null)
		    {
		    	elementModulPrint.style.display = "";      	
		    }
    	}
    }
    
    
    
    
    
    
    
    
    
    
    var printContents = document.getElementById(divName).innerHTML;
    var header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>Maklumat Peranan / Role</b></div><br></header>'
	var footer ="";
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()">'+header+'<div class="page-break" >'+ printContents + '</div>'+footer+'</html>');
    popupWin.document.close(); 
    document.body.innerHTML = originalContents;
    return false;
}
function simpanGroupName(group)
{
	   var bool_check = true;
	   
	   if(document.getElementById("textGroup"+group).value=="")
	   {
		   alert("Masukkan Nama Kumpulan!");
		   document.getElementById("textGroup"+group).focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
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
function getPageCoor()
{
	var tempScrollTop = $jquery(window).scrollTop();
	$jquery('#setPageCoor').val(tempScrollTop);
	return tempScrollTop;
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

function highlightGroup(size_rekod,search,nama_list)
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
		  var span1 = "span1group"+nama_list+(x+1);
		  //alert("span 1 : "+document.getElementById(span1));
		  
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
		  //alert("3");
	 }
}

function highlight_Umum(size_rekod,search,nama_list)
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
		 /* var span3 = "span3"+nama_list+(x+1);
		  var span4 = "span4"+nama_list+(x+1);
		  var span5 = "span5"+nama_list+(x+1);
		  var span6 = "span6"+nama_list+(x+1);
		  var span7 = "span7"+nama_list+(x+1);*/
		  //alert("span 1 : "+document.getElementById(span1));
		  
		  if(document.getElementById(span1)!=null)
		  {
		  	  temp_span1 = document.getElementById(span1);
		  	  temp_span2 = document.getElementById(span2);
		  	  temp_span3 = document.getElementById(span3);
		  	  temp_span4 = document.getElementById(span4);
		  	  /*temp_span3 = document.getElementById(span3);
		  	  temp_span4 = document.getElementById(span4); 
		  	  temp_span5 = document.getElementById(span5);
		  	  temp_span6 = document.getElementById(span6); 
		  	  temp_span7 = document.getElementById(span7);*/		  	   
		  
			  var divText1 = temp_span1.innerHTML;
			  var divText2 = temp_span2.innerHTML;
			  var divText3 = temp_span3.innerHTML;
			  var divText4 = temp_span4.innerHTML;
			  /*var divText3 = temp_span3.innerHTML;
			  var divText4 = temp_span4.innerHTML;
			  var divText5 = temp_span5.innerHTML;
			  var divText6 = temp_span6.innerHTML;
			  var divText7 = temp_span7.innerHTML;*/
		   
			  for (var i = 0; i < searchArray.length; i++) 
			  {
				  divText1 = doHighlight(divText1,searchArray[i], highlightStartTag, highlightEndTag);	
				  divText2 = doHighlight(divText2,searchArray[i], highlightStartTag, highlightEndTag);
				  divText3 = doHighlight(divText3,searchArray[i], highlightStartTag, highlightEndTag);
				  divText4 = doHighlight(divText4,searchArray[i], highlightStartTag, highlightEndTag);
				  
				  /*divText3 = doHighlight(divText3,searchArray[i], highlightStartTag, highlightEndTag);	
				  divText4 = doHighlight(divText4,searchArray[i], highlightStartTag, highlightEndTag);
				  divText5 = doHighlight(divText5,searchArray[i], highlightStartTag, highlightEndTag);	
				  divText6 = doHighlight(divText6,searchArray[i], highlightStartTag, highlightEndTag);
				  divText7 = doHighlight(divText7,searchArray[i], highlightStartTag, highlightEndTag);
				  */
			  }
			  
			  temp_span1.innerHTML = divText1; 
			  temp_span2.innerHTML = divText2; 
			  temp_span3.innerHTML = divText3; 
			  temp_span4.innerHTML = divText4; 
			  /*temp_span3.innerHTML = divText3; 
			  temp_span4.innerHTML = divText4; 
			  temp_span5.innerHTML = divText5; 
			  temp_span6.innerHTML = divText6; 
			  temp_span7.innerHTML = divText7;*/ 
			  
		  }
			 
		  
		  }
		  //alert("3");
	 }
}

function highlight(size_rekod,search,nama_list,namaModul)
{
	//alert(" size_rekod : "+size_rekod+" search : "+search+" nama_list :" +nama_list+" namaModul :"+namaModul)
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
		  var span1 = "span1"+namaModul+nama_list+(x+1);
		  var span2 = "span2"+namaModul+nama_list+(x+1);
		  var span3 = "span3"+namaModul+nama_list+(x+1);
		 /* var span3 = "span3"+nama_list+(x+1);
		  var span4 = "span4"+nama_list+(x+1);
		  var span5 = "span5"+nama_list+(x+1);
		  var span6 = "span6"+nama_list+(x+1);
		  var span7 = "span7"+nama_list+(x+1);*/
		  //alert("span 1 : "+document.getElementById(span1));
		  
		  if(document.getElementById(span1)!=null)
		  {
		  	  temp_span1 = document.getElementById(span1);
		  	  temp_span2 = document.getElementById(span2);
		  	  temp_span3 = document.getElementById(span3);
		  	  /*temp_span3 = document.getElementById(span3);
		  	  temp_span4 = document.getElementById(span4); 
		  	  temp_span5 = document.getElementById(span5);
		  	  temp_span6 = document.getElementById(span6); 
		  	  temp_span7 = document.getElementById(span7);*/		  	   
		  
			  var divText1 = temp_span1.innerHTML;
			  var divText2 = temp_span2.innerHTML;
			  var divText3 = temp_span3.innerHTML;
			  /*var divText3 = temp_span3.innerHTML;
			  var divText4 = temp_span4.innerHTML;
			  var divText5 = temp_span5.innerHTML;
			  var divText6 = temp_span6.innerHTML;
			  var divText7 = temp_span7.innerHTML;*/
		   
			  for (var i = 0; i < searchArray.length; i++) 
			  {
				  divText1 = doHighlight(divText1,searchArray[i], highlightStartTag, highlightEndTag);	
				  divText2 = doHighlight(divText2,searchArray[i], highlightStartTag, highlightEndTag);
				  divText3 = doHighlight(divText3,searchArray[i], highlightStartTag, highlightEndTag);
				  
				  /*divText3 = doHighlight(divText3,searchArray[i], highlightStartTag, highlightEndTag);	
				  divText4 = doHighlight(divText4,searchArray[i], highlightStartTag, highlightEndTag);
				  divText5 = doHighlight(divText5,searchArray[i], highlightStartTag, highlightEndTag);	
				  divText6 = doHighlight(divText6,searchArray[i], highlightStartTag, highlightEndTag);
				  divText7 = doHighlight(divText7,searchArray[i], highlightStartTag, highlightEndTag);
				  */
			  }
			  
			  temp_span1.innerHTML = divText1; 
			  temp_span2.innerHTML = divText2; 
			  temp_span3.innerHTML = divText3; 
			  /*temp_span3.innerHTML = divText3; 
			  temp_span4.innerHTML = divText4; 
			  temp_span5.innerHTML = divText5; 
			  temp_span6.innerHTML = divText6; 
			  temp_span7.innerHTML = divText7;*/ 
			  
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
/*
function editRole(group,name,id)
{
	var div = "";
	
	var element =  document.getElementById('div_viewRole'+group+''+id);
	if (typeof(element) != 'undefined' && element != null)
    {
		//alert("ada");
    	div = "div_viewRole"+group+""+id;
    }
	else
	{
		//alert("takdak");
		div = "div_viewRole";
	}
	
	
	doDivAjaxCall$formname(div,'editRole','ID='+id+'&GROUPUNIK='+group+'&ROLE_NAME='+name);
	
	
}
function closeRole(group,name,id)
{
	var div = "";
	
	var element =  document.getElementById('div_viewRole'+group+''+id);
	if (typeof(element) != 'undefined' && element != null)
    {
		//alert("ada");
    	div = "div_viewRole"+group+""+id;
    }
	else
	{
		//alert("takdak");
		div = "div_viewRole";
	}
	doDivAjaxCall$formname(div,'closeRole','ID='+id+'&GROUPUNIK='+group+'&ROLE_NAME='+name);
}
*/


function validateSaveGroupRole(group,id)
{
	   var bool_check = true;
	   
	   if(document.getElementById("CSS_TITLE_"+group+id).value=="")
	   {
		   alert("Masukkan Nama Kumpulan Role!");
		   document.getElementById("CSS_TITLE_"+group+id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("CSS_TITLE_"+group+id).value!="" && document.getElementById("CHECK_GROUP_"+group+id).value=="false")
	   {
		   alert("Nama Kumpulan Role Telah Wujud!");
		   document.getElementById("CSS_TITLE_"+group+id).focus();
		   bool_check = false;
	   }	   
	   else if(document.getElementById("CSS_NAME_"+group+id).value=="")
	   {
		   alert("Masukkan Nama Fail CSS!");
		   document.getElementById("CSS_NAME_"+group+id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("CSS_NAME_"+group+id).value!="" && document.getElementById("CHECK_CSS_"+group+id).value=="false")
	   {
		   alert("CSS Telah Wujud!");
		   document.getElementById("CSS_NAME_"+group+id).focus();
		   bool_check = false;
	   }
	   return bool_check;
}

function validateSaveRole(group,id)
{
	   var bool_check = true;
	   
	   if(document.getElementById("ROLE_NAME_"+group+id).value=="")
	   {
		   alert("Masukkan Role ID!");
		   document.getElementById("ROLE_NAME_"+group+id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("ROLE_NAME_"+group+id).value!="" && document.getElementById("CHECK_ROLE_NAME_"+group+id).value=="false")
	   {
		   alert("Nama Role Telah Wujud!");
		   document.getElementById("ROLE_NAME_"+group+id).focus();
		   bool_check = false;
	   }	   
	   else if(document.getElementById("DESCRIPTION_"+group+id).value=="")
	   {
		   alert("Masukkan Role Name!");
		   document.getElementById("DESCRIPTION_"+group+id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("GROUP_"+group+id).value=="")
	   {
		   alert("Masukkan Kumpulan Role!");
		   document.getElementById("GROUP_"+group+id).focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
}


function checkCarianModul(GROUPUNIK,ROLEUNIK)
{
	var bool_check = true;		
	return bool_check;
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

function statsRole(id){
if(document.getElementById('div_stats'+id).style.display=="none"){
		document.getElementById('div_stats'+id).style.display="";
		document.getElementById('div_statsSub'+id).style.display="";
	}
	else if(document.getElementById('div_stats'+id).style.display==""){
		document.getElementById('div_stats'+id).style.display="none";
		document.getElementById('div_statsSub'+id).style.display="none";
	}
	
}

</script>