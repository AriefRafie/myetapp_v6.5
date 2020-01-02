
<style>


/*
@media all {
	.page-break	{ display: none; }
}

@media print {
	
	.page-break	{ display: block; page-break-before: always;}

}
*/


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
#parse("app/admin/Kementerian/CTKementerian.jsp")</div>

<br>
<div id="div_AddKementerian"></div>
<br>
<div id="div_senaraiUtama"></div>


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
  
  
  
  
 function printHideDiv(divName1,divName2) {
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
	//" 	font-family: geneva, arial, helvetica, sans-serif; "+
    //" 	font-size: 12pt; "+
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
			" <th align=\'left\' >BORANG SEMAKAN PENGGUNA KJP SISTEM MYETAPP V6</th> "+
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
  
//05092017
function printHideDiv_backup(divName) {

	var carianUmum = document.getElementById("carianUmum").value;
	
	var header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>Maklumat Kementerian</b></div><br></header>'
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

	document.getElementById("carianUmum").value = carianUmum;
	
    elementHide.style.display = "none";
    $jquery("#"+divName+" :button").show();
    return false;
    
    
    
}


function printHideDiv2(divName) {

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
    popupWin.document.write('<html><body onload="window.print()">'+'<div class="page-break" >'+ printContents + '</div>'+'</html>');
    popupWin.document.close(); 
    //document.body.innerHTML = originalContents;

    elementHide.style.display = "none";
    $jquery("#"+divName+" :button").show();
	
    return false;
    
    
    
}

function doCetakStats(id){
	//doDivAjaxCall$formname('statKJP','getStatsKJP','');	
	
	//function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="";
		document.getElementById('statKJPAll').style.display="";
	}
	else if(document.getElementById(id).style.display==""){
		document.getElementById(id).style.display="none";
		document.getElementById('statKJPAll').style.display="none";
	}
//}
}

function doCetakStatsSub(div,type){
	doDivAjaxCall$formname(div,'getStatsKJP','STATS='+type);
	}
	
	
function doCetak(divName) {

 $jquery("#"+divName+" :button").hide(); 
 $jquery("#"+divName+" :image").hide();    
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
    popupWin.document.write('<html><body onload="window.print()">'+'<div class="page-break" >'+ printContents + '</div>'+'</html>');
    popupWin.document.close(); 
    //document.body.innerHTML = originalContents;

   // elementHide.style.display = "none";
   // $jquery("#"+divName+" :button").show();
    return false;
			
}	
</script>
