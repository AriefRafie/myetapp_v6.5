
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
#parse("app/admin/BahagianUnit/CarianBahagian.jsp")

</div>

<br >

<div id="div_senaraiUtama" >
#parse("app/admin/BahagianUnit/SenaraiUtama.jsp")
</div>

<div id="SenaraiForPrint"></div>

<iframe id="uploadFrame" name="uploadFrame" style="display:none"></iframe>

<script>

function pwdStrength() {
	
	$jquery('.password').pstrength();
}

function setPasswordClass(elem,value,internalType,user_id)
{
	var ch = document.getElementById("ps_call"+ID_PEJABAT).value;
	if(value!="")
	{
		elem.className = "password";
		if(ch=="no")
		{
			document.getElementById("ps_call"+ID_PEJABAT).value="yes";
			$jquery('.password').pstrength();
		}
	}
	else
	{
		document.getElementById("ps_call"+ID_PEJABAT).value="";
	}
}

function printHideDiv(divName) {

	var carianUmum = document.getElementById("carianUmum").value;
	
	 var header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>Maklumat Bahagian</b></div><br></header>'
		 var footer ="";
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
    popupWin.document.write('<html><body onload="window.print()">'+header+'<div class="page-break" >'+ printContents + '</div>'+footer+'</html>');
    popupWin.document.close(); 
    //document.body.innerHTML = originalContents;

	var carianUmum = document.getElementById("carianUmum").value;
    
    elementHide.style.display = "none";
    $jquery("#"+divName+" :button").show();
    return false;
    
    
    
}

function showhideDiv(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function validateIC_V3(e,elmnt,content,nextElementID) {
	//if it is character, then remove it..
	var keycode;
	if(window.event)keycode = window.event.keyCode;
	else if (e) keycode = e.which;
	else return true;
	
	//alert(keycode);
	
	if((keycode >= 37 && keycode <= 40) || (keycode == 9)) return false;
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
	//goto next column if maximum length reach
	if (content.length == elmnt.maxLength) 
	{
		goTo(nextElementID);
		/*
		if(content.length==6)
		{
			
		}
		else
		{
			goTo(nextElementID);
		}
		*/
		
	}
		
}


		
		
function getDOBByIC_V3(elmnt,content,DOBField,err_span) {
	
	//if (content.length == elmnt.maxLength) {
		year = content.substring(0,2);
 		if (year <=10) {
 			year = parseInt(year) + 2000;
 		} else {
 			year = parseInt(year) + 1900
 		}
		month = content.substring(2,4);
		day = content.substring(4,6);
		var date_str = day + '/' + month + '/' + year ;
		//alert("date_str :"+date_str+ "---------- "+isValidDate_V3(date_str))
		
		if(isValidDate_V3(date_str)==true)
		{
			var elementDate =  document.getElementById(DOBField);			
			if (typeof(elementDate) != 'undefined' && elementDate != null)
		    {
				returnObjById(DOBField).value = date_str;
		    }
			
			$jquery("#"+err_span).html("<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='true' >");
			
		}
		else
		{
			$jquery("#"+err_span).html("<font color='red'>Format Tidak Tepat!<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='false' ></font>");
		}
		
	//}
	
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

function RemoveNonNumeric_V3(elmnt,content)
{
      var strValidCharacters = "1234567890";
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

function checkMaxLength_V3(elmnt,content,minlength,err_span)
{
	if(content.length != minlength)
	{
		$jquery("#"+err_span).html("<font color='red'>Sila Masukkan "+minlength+" Aksara Pada Medan Ini!</font><input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='false' >");
	}
	else
	{
		$jquery("#"+err_span).html("<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='true' >");
	}
}

function getJantinaByIC_V3(elmnt,content,JantinaField)
{
	var ch = content.charAt(3);

	if(content.length==4)
  	{
		if(ch%2 == 0)
		{
			//returnObjById(DOBField).value = "P";
			document.getElementById(JantinaField).value = "P";
		}
		else if(ch%2 != 0)
		{
			//returnObjById(DOBField).value  = "L";
			document.getElementById(JantinaField).value = "L";
		}
		else
		{
			document.getElementById(JantinaField).value = "";
		}
  	}
}


function copyValueIC(e,elmnt,content,sendField,kat)
{
	//alert(" kat  :"+kat)
	//if (content.length == elmnt.maxLength) {
		if(kat=="" || kat=="Individu")
		{
		document.getElementById(sendField).value = content;
		}
	//}
}

function convertTODateFormat(date){
	   var parts = date.split("/");
	   return new Date(parts[2], parts[1] - 1, parts[0]);
}

function getAgeByIC_V3(elmnt,content,umurField) {
	if (content.length == elmnt.maxLength) {
		
		var check_year = parseInt(content.substring(0,2));
		var check_month = content.substring(2,4);
		var check_day = content.substring(4,6);
		var check_day_int = 0;
		
		
		if (check_year < 10) {	 		
			check_year = check_year + 2000;
 			
 		} else {
 			check_year = check_year + 1900
 		} 
		
		var check_date_str = check_day + '/' + check_month + '/' + check_year ;
		//alert(check_date_str+ "----------" +isValidDate_V3(check_date_str));
		if(isValidDate_V3(check_date_str)==true)
		{
				var year = 0;
				
				if(content.charAt(0)== 0)
				{
				year = parseInt(content.charAt(1));
				}
				else
				{	
				year = parseInt(content.substring(0,2));
				}
		    	today = new Date();
		 		yearStr = today.getFullYear();
		 		
		 		if (year < 10) {
		 		
		 			year = year + 2000;
		 			
		 		} else {
		 			year = year + 1900
		 		} 		
		 		year = parseInt(yearStr) - year;
		 		
		 		if(year > 0){ 
			 		if(year>=99)
			 		{
			 			alert("Umur Melebihi 99 Tahun!")
			 			returnObjById(umurField).value = 0;
			 		}
			 		else
			 		{
					returnObjById(umurField).value = year ;
			 		}
		 		}
		 		else if(year == 0){ 		
		 	    returnObjById(umurField).value = 1 ;
		 	 	}
		 		else{
		 		returnObjById(umurField).value = 0 ;	 			
		 		}
		}
	}
}	


function doCheckAllDaerahJagaan(ID_PEJABAT) { 
	
	var all_checklist = document.getElementById("ALL_CHECKLIST_"+ID_PEJABAT);	
	var checklist_length = document.getElementsByName("CHECKLIST_"+ID_PEJABAT).length;
	var checklist = document.getElementsByName("CHECKLIST_"+ID_PEJABAT);
	
	  if(all_checklist.checked == true){
		  for (i = 0; i < checklist_length; i++) 
			{
			  checklist[i].checked=true;
						
			}
	  }else {
		  for (i = 0; i < checklist_length; i++) 
			  {
	        	 checklist[i].checked = false;
	         }
	  }
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


function highlight(size_rekod,search,nama_list)
{

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

function sendValueJenisPejabat(elem, value, nextField)
{
	document.getElementById(nextField).value = value;
}

function removeSpaces(elem, valuekey) {//remove special char & space
	 elem.value = valuekey.replace(/[^A-Z0-9]+/i, '');
}

function valEditBahagian(ID_SEKSYEN)
{
		var bool_check = true;
		
		if(document.getElementById("NAMA_SEKSYEN_"+ID_SEKSYEN).value=="")
		{
		alert("Sila Masukkan Nama Bahagian!");
		document.getElementById("NAMA_SEKSYEN_"+ID_SEKSYEN).focus();
		bool_check = false;
		}
		else if(document.getElementById("FLAG_AKTIF_"+ID_SEKSYEN).value=="")
		{
		alert("Sila Nyatakan Status Keaktifan Bahagian!");
		document.getElementById("FLAG_AKTIF_"+ID_SEKSYEN).focus();
		bool_check = false;
		}
		return bool_check;
}


function checkKodJenisPjbt(KOD_JENISPEJABAT,ID_PEJABAT){
	
	//alert('masuk check kod'+KOD_JENISPEJABAT);
	doDivAjaxCall$formname('div_CHECK_KODJENISPEJABAT_','checkKodJenisPejabat','ID_PEJABAT='+ID_PEJABAT+'&KOD_JENISPEJABAT='+$jquery('#KOD_JENISPEJABAT_'+ID_PEJABAT).val());
	
	var kod = document.getElementById("CHECK_KOD_"+ID_PEJABAT);
	alert("kod "+ kod);
	if(kod.value=="null")
	{
	alert("Sila Pilih Kod Lain.");
	document.getElementById("CHECK_KOD_"+ID_PEJABAT).focus();
	document.getElementById("CHECK_KOD_"+ID_PEJABAT).clear();
	}
}

function uploadDoc(elem,idBahagian,div)
{
	document.${formName}.action = "?_portal_module=ekptg.view.admin.FrmBahagian&command=simpanDokumen&ID_SEKSYEN="+idBahagian+"&returnDivUpload="+div;
	document.${formName}.method="post";
	document.${formName}.target="uploadFrame";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();	
}

if('$after_saveDOC'=="Y")
{
		
	window.parent.viewDoc('$returnDivUpload','$commandDoc','$ID_INTEGRASI_AFTERUPLOAD')
}

function viewDoc(div,command,idBahagian)
{
	doDivAjaxCall$formname(div,command,'ID_INTEGRASI='+idBahagian);			 	  
	
}

function paparDoc(idDoc) {
    var url = "../servlet/ekptg.view.admin.DisplayBlobLampiranBahagian?ID_DOKUMEN="+idDoc;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function printHideDivAll(divName1,divName2) {
 //alert('test');
 	//var header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>Maklumat Kementerian</b></div><br></header>'
	
	$jquery("#"+divName1+" :button").hide();
	$jquery("#"+divName2+" :button").hide();
	var head_style = " <head> "+
    " <style> "+
    " @media print { "+ 
	//" thead {display: table-header-group;} "+
	" 	body { "+ 
    "  -webkit-print-color-adjust: exact; "+  /*Chrome, Safari */
    "  color-adjust: exact;  "+  /*Firefox*/
	"	font-size-adjust: exact; "+
    " 	} "+ 
	"         .pageBreak    { table { page-break-inside:auto } "+
  	"		  tr    { page-break-inside:avoid; page-break-after:auto;  } } "+
	"		  .nextPage    { page-break-after:always;  } "+	
    " } "+   
    " </style> "+
	" </head> ";
	
	var printContents1 = document.getElementById(divName1).innerHTML;
	var printContents2 = document.getElementById(divName2).innerHTML;
	
	var printAllContent = head_style + " <table> "+
	   " <thead> "+
		  " <tr> "+
			 " <th align=\'left\' >BORANG SEMAKAN PENGGUNA BAHAGIAN DALAM SISTEM MYETAPP V6</th> "+
		  " </tr> "+
		  " <tr> "+
			 " <th><hr align=\'left\' height=\'30px\' color=\'black\' ></th> "+
		  " </tr> "+
	   " </thead> "+
	   " <tbody> "+
		  " <tr> "+
			 " <td><div class=\'nextPage pageBreak\' >"+printContents1+"</div><div class=\'pageBreak\'>"+printContents2+"</div>"+"</td> "+
		  " </tr> "+
	   " </tbody> "+
	" </table> ";
	  
	
    
	var footer ="";
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
   
    popupWin.document.write('<html><body onload="window.print()">'+printAllContent+'</html>');
	popupWin.document.close(); 
    return false;
}

</script>
