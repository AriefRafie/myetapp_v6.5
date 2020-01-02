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
.underline_td_sub {
	border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 100%;
    color : white;
    text-shadow: 2px 2px 5px black;
}

</style>


#parse("app/admin/ModuleEditor/CarianTerperinci.jsp")

<div id="div_senaraiUtama" >
#parse("app/admin/ModuleEditor/SenaraiUtama.jsp")
</div>


<script>
function printDiv(divName) {
    var originalContents = document.body.innerHTML;
    $jquery("#"+divName+" :button").hide();
    var printContents = document.getElementById(divName).innerHTML;
    var header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>Maklumat Modul</b></div><br></header>'
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

function editModule(group,id_trim,id_asal)
{
	var div = "";
	if( $jquery('#div_viewModul'+group+''+id_trim).length)         // use this if you are using id to check
	{
		div = "div_viewModul"+group+""+id_trim;
	}
	else
	{
		div = "div_viewModul";
	}
	doDivAjaxCall$formname(div,'editModule','MODULE_ID='+id_asal+'&MODULE_GROUP='+group);
	
	
}
function closeModule(group,id_trim,id_asal)
{
	var div = "";
	if( $jquery('#div_viewModul'+group+''+id_trim).length)         // use this if you are using id to check
	{
		div = "div_viewModul"+group+""+id_trim;
	}
	else
	{
		div = "div_viewModul";
	}
	doDivAjaxCall$formname(div,'closeModule','MODULE_ID='+id_asal+'&MODULE_GROUP='+group);
}

function saveModule(group,id_trim,id_asal)
{
	var div = "";
	if( $jquery('#div_viewModul'+group+''+id_trim).length)         // use this if you are using id to check
	{
		div = "div_viewModul"+group+""+id_trim;
	}
	else
	{
		div = "div_viewModul";
	}
	if(validateSaveModule(group,id_trim)==true)
	{
		doDivAjaxCall$formname(div,'saveModule','MODULE_ID='+id_asal+'&MODULE_GROUP='+group);
	}
}

function validateSaveModule(group,id_trim)
{
	   var bool_check = true;
	   
	   if(document.getElementById("MODULE_ID_"+group+id_trim).value=="")
	   {
		   alert("Masukkan ID Modul!");
		   document.getElementById("MODULE_ID_"+group+id_trim).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("MODULE_ID_"+group+id_trim).value!="" && document.getElementById("CHECK_MODULE_ID_"+group+id_trim).value=="false")
	   {
		   alert("Id Modul Telah Wujud!");
		   document.getElementById("MODULE_ID_"+group+id_trim).focus();
		   bool_check = false;
	   }	   
	   else if(document.getElementById("MODULE_TITLE_"+group+id_trim).value=="")
	   {
		   alert("Masukkan Nama Modul!");
		   document.getElementById("MODULE_TITLE_"+group+id_trim).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("MODULE_CLASS_"+group+id_trim).value=="")
	   {
		   alert("Masukkan Class Modul!");
		   document.getElementById("MODULE_CLASS_"+group+id_trim).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("MODULE_GROUP_"+group+id_trim).value=="")
	   {
		   alert("Masukkan Kumpulan Modul!");
		   document.getElementById("MODULE_GROUP_"+group+id_trim).focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
}



function doCheckUpdateRole(module_group,module_id) { 
	var group_length = document.getElementsByName("GROUP_CHECKLIST_"+module_group+module_id).length;
	var group_checkbox = document.getElementsByName("GROUP_CHECKLIST_"+module_group+module_id);
	var all_checklist = document.getElementById("ALL_CHECKLIST_"+module_group+module_id);
	//alert(all_checklist.checked);
	var m = 0;
	for (i = 0; i < group_length; i++) 
	{
		var checklist_checkbox = document.getElementsByName("CHECKLIST_"+module_group+module_id+group_checkbox[i].value);
		var checklist_length = document.getElementsByName("CHECKLIST_"+module_group+module_id+group_checkbox[i].value).length;
		var c = 0;
		for (k = 0; k < checklist_length; k++) {
			if(checklist_checkbox[k].checked==false)
			{
				c++;
			}
		}	
		if(c>0)
		{
			group_checkbox[i].checked = false;
		}
		else
		{
			group_checkbox[i].checked = true;
		}
		
		
		if(group_checkbox[i].checked == false)
		{
			m++;
		}		
	}
	
	if(m>0)
	{
		all_checklist.checked = false;
	}
	else
	{
		all_checklist.checked = true;
	}
	
}	


function doCheckAllRoleGroup(module_group,module_id,element) 
{ 
	if(element.checked==true)
	{
		var checklist_checkbox = document.getElementsByName("CHECKLIST_"+module_group+module_id+element.value);
		var checklist_length = document.getElementsByName("CHECKLIST_"+module_group+module_id+element.value).length;
		for (k = 0; k < checklist_length; k++) {
			checklist_checkbox[k].checked = true;
		}
	}
	else if(element.checked==false)
	{
		var checklist_checkbox = document.getElementsByName("CHECKLIST_"+module_group+module_id+element.value);
		var checklist_length = document.getElementsByName("CHECKLIST_"+module_group+module_id+element.value).length;
		for (k = 0; k < checklist_length; k++) {
			checklist_checkbox[k].checked = false;
		}
	}
	doCheckUpdateRole(module_group,module_id);
}

function doCheckAllRole(module_group,module_id) { 
	
	var all_checklist = document.getElementById("ALL_CHECKLIST_"+module_group+module_id);	
	var group_length = document.getElementsByName("GROUP_CHECKLIST_"+module_group+module_id).length;
	var group_checkbox = document.getElementsByName("GROUP_CHECKLIST_"+module_group+module_id);
	if(all_checklist.checked == true)
	{
		for (i = 0; i < group_length; i++) 
		{
			group_checkbox[i].checked=true;
			var checklist_checkbox = document.getElementsByName("CHECKLIST_"+module_group+module_id+group_checkbox[i].value);
			var checklist_length = document.getElementsByName("CHECKLIST_"+module_group+module_id+group_checkbox[i].value).length;
			for (k = 0; k < checklist_length; k++) {
				checklist_checkbox[k].checked = true;
			}			
		}
	}
	else if(all_checklist.checked == false)
	{
		for (i = 0; i < group_length; i++) 
		{
			group_checkbox[i].checked=false;
			var checklist_checkbox = document.getElementsByName("CHECKLIST_"+module_group+module_id+group_checkbox[i].value);
			var checklist_length = document.getElementsByName("CHECKLIST_"+module_group+module_id+group_checkbox[i].value).length;
			for (k = 0; k < checklist_length; k++) {
				checklist_checkbox[k].checked = false;
			}		
		}
	}
}

	
</script>