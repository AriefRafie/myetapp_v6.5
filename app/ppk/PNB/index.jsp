
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
</style>

<div id="div_senaraiUtama" >
#parse("app/ppk/PNB/SenaraiUtama.jsp")
</div>


<script>
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

function downloadBorangPNB(idborangpnb,idfail) {
    var url = "../servlet/ekptg.view.ppk.DisplayBlobPNB?idborangpnb="+idborangpnb;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function returnDropDownSelectedText(dropdownid,div_text)
{
	var skillsSelect =  document.getElementById(dropdownid);
	var selectedText = "";
	if (typeof(skillsSelect) != 'undefined' && skillsSelect != null)
    {
		selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
	}
	//alert(" selectedText : "+selectedText);
	$jquery("#"+div_text).html(selectedText);
	
	
}

function returnDropDownSelectedText_value(dropdownid)
{
	//alert("1"+div_text);
	var skillsSelect =  document.getElementById(dropdownid);
	var selectedText = "";
	if (typeof(skillsSelect) != 'undefined' && skillsSelect != null)
    {
		selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
	}
	//alert(" selectedText : "+selectedText);
	return selectedText;
	//alert(selectedText);
	
	//$jquery("#"+div_text).html(selectedText);
}

function printHideDiv(divName) {
	$jquery("#"+divName+" :button").hide();    
    var elementHide =  document.getElementById(divName);
    if (typeof(elementHide) != 'undefined' && elementHide != null)
    {
    	elementHide.style.display = "";
    }    
    var printContents = document.getElementById(divName).innerHTML;   
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()"><div class="page-break" >'+ printContents + '</div></html>');
    popupWin.document.close(); 
    elementHide.style.display = "none";
    $jquery("#"+divName+" :button").show();
    return false;    
}

function valCarian()
{
	   var bool_check = true;
	   if(document.getElementById("CHECK_CARIAN_TARIKH_MULA").value=="false")
	   {
		   alert("Sila pastikan format tarikh adalah tepat!");
		   document.getElementById("CARIAN_TARIKH_MULA").focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("CHECK_CARIAN_TARIKH_AKHIR").value=="false")
	   {
		   alert("Sila pastikan format tarikh adalah tepat!");
		   document.getElementById("CARIAN_TARIKH_AKHIR").focus();
		   bool_check = false;
	   }
	  // alert('bool_check : '+bool_check);
	   return bool_check;   
}


function doCheckUpdatePenerimaAll(ID_BORANGPNB,element,namaField) 
{ 
	var check_checkall_spanreturn = document.getElementById("show_CHECK_RETURN_ALL"+ID_BORANGPNB);
	if(element.checked==true)
	{
		var element_mainspan = document.getElementById("show_TARIKH_"+namaField+"_ALL"+element.value);
		//element_mainspan.value = "";
		element_mainspan.style.display = "";
		
		
		if(namaField=="RETURN")
		{
			var element_mainspan_catatan = document.getElementById("show_CATATAN_"+namaField+"_ALL"+element.value);
			element_mainspan_catatan.style.display = "";
		}
		
		
 		if(namaField=="HANTAROB")
		{
 			alert("Sila Masukkan Tarikh Serahan");
			var element_mainspan_nosurat = document.getElementById("show_NOSURAT_"+namaField+"_ALL"+element.value);
			element_mainspan_nosurat.style.display = "";
		} 
		
		var checklist_checkbox = document.getElementsByName("CHECK_"+namaField+""+element.value);
		var checklist_length = document.getElementsByName("CHECK_"+namaField+""+element.value).length;
		for (k = 0; k < checklist_length; k++) {
			
			var check_element_subspan_flag = document.getElementById("show_CHECK_RETURN"+checklist_checkbox[k].value);
			var check_element_subspan_flaghantar = document.getElementById("show_CHECK_HANTAROB"+checklist_checkbox[k].value);
			
			if((namaField == "RETURN" && check_element_subspan_flag.style.display=='') || namaField == "HANTAROB")
			{
				checklist_checkbox[k].checked = true;
				var element_subspan = document.getElementById("show_TARIKH_"+namaField+""+checklist_checkbox[k].value);
				element_subspan.style.display = "";
				
				var element_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value);
				var value_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value).value;
				checkMandatory_Date(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value,checklist_checkbox[k]);
			}
			
			if(namaField=="RETURN" && check_element_subspan_flag.style.display=='')
			{
				var element_subspan_catatan = document.getElementById("show_CATATAN_"+namaField+""+checklist_checkbox[k].value);
				element_subspan_catatan.style.display = "";
			}
			
 			if(namaField=="HANTAROB" && check_element_subspan_flaghantar.style.display=='')
			{
				var element_subspan_nosurat = document.getElementById("show_NOSURAT_"+namaField+""+checklist_checkbox[k].value);
				element_subspan_nosurat.style.display = "";
			} 
			
			if(namaField=="HANTAROB")
			{
				var checklist_checkbox_return = document.getElementsByName("CHECK_RETURN"+ID_BORANGPNB);					
				var element_subfield_flag = checklist_checkbox_return[k];
				//alert(element_subfield_flag.checked);
				//element_subfield_flag.checked = '';
				var element_subspan_flag = document.getElementById("show_CHECK_RETURN"+checklist_checkbox[k].value);
				element_subspan_flag.style.display = "";				
			}
			
			
			
		}
		
		
		if(namaField=="HANTAROB")
		{
			//var check_checkall_spanreturn = document.getElementById("show_CHECK_RETURN_ALL"+ID_BORANGPNB);
			check_checkall_spanreturn.style.display = '';	
		
		}
	}
	else if(element.checked==false)
	{
		var element_mainspan = document.getElementById("show_TARIKH_"+namaField+"_ALL"+element.value);		
		var element_mainfield = document.getElementById("TARIKH_"+namaField+"_ALL"+element.value);
		element_mainfield.value = "";
		if(namaField=="RETURN")
		{
			var element_mainfield_catatan = document.getElementById("CATATAN_"+namaField+"_ALL"+element.value);
			element_mainfield_catatan.value = "";
			var element_mainspan_catatan = document.getElementById("show_CATATAN_"+namaField+"_ALL"+element.value);	
			element_mainspan_catatan.style.display = "none";
		}
		element_mainspan.style.display = "none";
		
		if(namaField=="HANTAROB")
		{
			var element_mainfield_nosurat = document.getElementById("NOSURAT_"+namaField+"_ALL"+element.value);
			element_mainfield_nosurat.value = "";
			var element_mainfield_nosurat = document.getElementById("show_NOSURAT_"+namaField+"_ALL"+element.value);	
			element_mainfield_nosurat.style.display = "none";
		}
		element_mainspan.style.display = "none";
		
		
		var checklist_checkbox = document.getElementsByName("CHECK_"+namaField+""+element.value);
		var checklist_length = document.getElementsByName("CHECK_"+namaField+""+element.value).length;
		for (k = 0; k < checklist_length; k++) {
			checklist_checkbox[k].checked = false;
			var element_subspan = document.getElementById("show_TARIKH_"+namaField+""+checklist_checkbox[k].value);
			var element_subfield = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value);
			element_subfield.value = "";
			element_subspan.style.display = "none";
			
			if(namaField=="RETURN")
			{
				var element_subfield_catatan = document.getElementById("CATATAN_"+namaField+""+checklist_checkbox[k].value);
				element_subfield_catatan.value = "";
				var element_subspan_catatan = document.getElementById("show_CATATAN_"+namaField+""+checklist_checkbox[k].value);
				element_subspan_catatan.style.display = "none";			
			}
			
			if(namaField=="HANTAROB")
			{
				var element_subfield_catatan = document.getElementById("CATATAN_RETURN"+checklist_checkbox[k].value);
				element_subfield_catatan.value = "";
				var element_subspan_catatan = document.getElementById("show_CATATAN_RETURN"+checklist_checkbox[k].value);
				element_subspan_catatan.style.display = "none";
				
				var element_mainfield_nosurat = document.getElementById("NOSURAT_HANTAROB"+checklist_checkbox[k].value);
				element_mainfield_nosurat.value = "";
				var element_mainfield_nosurat = document.getElementById("show_NOSURAT_HANTAROB"+checklist_checkbox[k].value);
				element_mainfield_nosurat.style.display = "none";
				
				var element_subfield_tarikh = document.getElementById("TARIKH_RETURN"+checklist_checkbox[k].value);
				element_subfield_tarikh.value = "";
				var element_subspan_tarikh = document.getElementById("show_TARIKH_RETURN"+checklist_checkbox[k].value);
				element_subspan_tarikh.style.display = "none";
				
				
				var checklist_checkbox_return = document.getElementsByName("CHECK_RETURN"+ID_BORANGPNB);					
				var element_subfield_flag = checklist_checkbox_return[k];
				//(element_subfield_flag.checked);
				element_subfield_flag.checked = '';
				var element_subspan_flag = document.getElementById("show_CHECK_RETURN"+checklist_checkbox[k].value);
				element_subspan_flag.style.display = "none";
			}
			
			
			var element_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value);
			var value_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value).value;
			checkFormatDate_V3(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value);
		}
		
		if(namaField=="HANTAROB")
		{
			//var check_checkall_spanreturn = document.getElementById("show_CHECK_RETURN_ALL"+ID_BORANGPNB);
			check_checkall_spanreturn.style.display = 'none';	
				
	
		}
		
	}
}

function checkMandotoryPenerimaAll_TARIKH(ID_BORANGPNB,namaField) 
{ 
  
	var checklist_checkbox = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB);
	var checklist_length = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB).length;
	//alert('checklist_length : '+checklist_length);
	for (k = 0; k < checklist_length; k++) {
			//checklist_checkbox[k].checked = true;
			//alert(checklist_checkbox[k].value);
			var element_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value);
			var value_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value).value;
			checkFormatDate_V3(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value);
			checkMandatory_Date(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value,checklist_checkbox[k]);
			
	}
	
}

function valSenaraiPenerima(ID_BORANGPNB)
{
	var bool_check = true;
	
	if(valCheckSenaraiPenerima(ID_BORANGPNB,'HANTAROB')==false)
	{
		alert("Sila pastikan maklumat Status & Tarikh Hantar adalah tepat!");		 
		bool_check = false;
	}
	
	if(valCheckSenaraiPenerima(ID_BORANGPNB,'RETURN')==false)
	{
		alert("Sila pastikan maklumat Status & Tarikh Pemulangan adalah tepat!");		 
		bool_check = false;
	}
	
	return bool_check;
}


function valCheckSenaraiPenerima(ID_BORANGPNB,namaField)
{
	var bool_check = true;
	var checklist_checkbox = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB);
	var checklist_length = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB).length;
	for (k = 0; k < checklist_length; k++) {
			var value_fielddate = document.getElementById("CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value).value;
			if(value_fielddate=='false')
			{
				bool_check = false;
			}
			
	}
	return bool_check;
}


function doCopyPenerimaAll_CATATAN(catatan,ID_BORANGPNB,namaField) 
{
	var checklist_checkbox = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB);
	var checklist_length = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB).length;
	//alert('checklist_length : '+checklist_length);
	for (k = 0; k < checklist_length; k++) {
			//checklist_checkbox[k].checked = true;
			//alert(checklist_checkbox[k].value);
			var check_element_subspan_flag = document.getElementById("show_CHECK_RETURN"+checklist_checkbox[k].value);			
			if((namaField == "RETURN" && check_element_subspan_flag.style.display==''))
			{
			document.getElementById("CATATAN_"+namaField+""+checklist_checkbox[k].value).value=catatan;
			}
			/*			
			var element_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value);
			var value_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value).value;
			checkFormatDate_V3(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value);
			checkMandatory_Date(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value,checklist_checkbox[k]);
			*/
			
	}
}

function doCopyPenerimaAll_NOSURAT(nosurat,ID_BORANGPNB,namaField) 
{
	var checklist_checkbox = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB);
	var checklist_length = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB).length;
	//alert('checklist_length : '+checklist_length);
	for (k = 0; k < checklist_length; k++) {
			//checklist_checkbox[k].checked = true;
			//alert(checklist_checkbox[k].value);
			var check_element_subspan_flaghantar = document.getElementById("show_CHECK_HANTAROB"+checklist_checkbox[k].value);			
			if((namaField == "HANTAROB" && check_element_subspan_flaghantar.style.display==''))
			{
			document.getElementById("NOSURAT_"+namaField+""+checklist_checkbox[k].value).value=nosurat;
			}
					
	}
}

function doCopyPenerimaAll_TARIKH(tarikh,ID_BORANGPNB,namaField) 
{ 
  
	var checklist_checkbox = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB);
	var checklist_length = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB).length;
	//alert('checklist_length : '+checklist_length);
	for (k = 0; k < checklist_length; k++) {
			//checklist_checkbox[k].checked = true;
			//alert(checklist_checkbox[k].value);
			var check_element_subspan_flag = document.getElementById("show_CHECK_RETURN"+checklist_checkbox[k].value);			
			if((namaField == "RETURN" && check_element_subspan_flag.style.display=='') || namaField == "HANTAROB")
			{
				document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value).value=tarikh;			
				var element_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value);
				var value_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value).value;
				checkFormatDate_V3(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value);
				checkMandatory_Date(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value,checklist_checkbox[k]);
			}
			
	}
	
}


function doCheckUpdatePenerima(ID_BORANGPNB,namaField) { 
	    var checklist_checkbox = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB);
		//alert('checklist_checkbox : '+checklist_checkbox);
		var checklist_length = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB).length;
		//alert('checklist_length : '+checklist_length);
		var c = 0;
		var check_all_return = 0;
		for (k = 0; k < checklist_length; k++) {
			if(checklist_checkbox[k].checked==false)
			{
				c++;
				var element_subspan = document.getElementById("show_TARIKH_"+namaField+""+checklist_checkbox[k].value);
				var element_subfield = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value);
				element_subfield.value = "";
				element_subspan.style.display = "none";
				if(namaField=="RETURN")
				{
					var element_subfield_catatan = document.getElementById("CATATAN_"+namaField+""+checklist_checkbox[k].value);
					element_subfield_catatan.value = "";
					var element_subspan_catatan = document.getElementById("show_CATATAN_"+namaField+""+checklist_checkbox[k].value);
					element_subspan_catatan.style.display = "none";
				}
				
				if(namaField=="HANTAROB")
				{
					var element_subfield_catatan = document.getElementById("CATATAN_RETURN"+checklist_checkbox[k].value);
					element_subfield_catatan.value = "";
					var element_subspan_catatan = document.getElementById("show_CATATAN_RETURN"+checklist_checkbox[k].value);
					element_subspan_catatan.style.display = "none";
					
 					var element_subfield_nosurat= document.getElementById("NOSURAT_HANTAROB"+checklist_checkbox[k].value);
 					element_subfield_nosurat.value = "";
					var element_subspan_nosurat = document.getElementById("show_NOSURAT_HANTAROB"+checklist_checkbox[k].value);
					element_subspan_nosurat.style.display = "none";  
					
					var element_subfield_tarikh = document.getElementById("TARIKH_RETURN"+checklist_checkbox[k].value);
					element_subfield_tarikh.value = "";
					var element_subspan_tarikh = document.getElementById("show_TARIKH_RETURN"+checklist_checkbox[k].value);
					element_subspan_tarikh.style.display = "none";
					
					var checklist_checkbox_return = document.getElementsByName("CHECK_RETURN"+ID_BORANGPNB);					
					var element_subfield_flag = checklist_checkbox_return[k];
					//(element_subfield_flag.checked);
					element_subfield_flag.checked = '';
					var element_subspan_flag = document.getElementById("show_CHECK_RETURN"+checklist_checkbox[k].value);
					element_subspan_flag.style.display = "none";
				}
				
				
				var element_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value);
				var value_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value).value;
				checkFormatDate_V3(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value);
				//checkMandatory_Date(element_fielddate,value_fielddate,'span_CHECK_TARIKH_HANTAROB'+checklist_checkbox[k].value);
			}
			else
			{
				var element_subspan = document.getElementById("show_TARIKH_"+namaField+""+checklist_checkbox[k].value);
				element_subspan.style.display = "";
				if(namaField=="RETURN")
				{
					var element_subspan_catatan = document.getElementById("show_CATATAN_"+namaField+""+checklist_checkbox[k].value);
					element_subspan_catatan.style.display = "";
				}
				
				if(namaField=="HANTAROB")
				{
					var element_subspan_nosurat = document.getElementById("show_NOSURAT_"+namaField+""+checklist_checkbox[k].value);
					element_subspan_nosurat.style.display = "";
				}
				
				if(namaField=="HANTAROB")
				{
					var checklist_checkbox_return = document.getElementsByName("CHECK_RETURN"+ID_BORANGPNB);					
					var element_subfield_flag = checklist_checkbox_return[k];
					//alert(element_subfield_flag.checked);
					//element_subfield_flag.checked = '';
					var element_subspan_flag = document.getElementById("show_CHECK_RETURN"+checklist_checkbox[k].value);
					element_subspan_flag.style.display = "";
					
				}
				
				
				var element_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value);
				var value_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value).value;
				//checkFormatDate_V3(element_fielddate,value_fielddate,'span_CHECK_TARIKH_HANTAROB'+checklist_checkbox[k].value);
				checkMandatory_Date(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value,checklist_checkbox[k]);
			}
			
			
			
			if(namaField=="HANTAROB")
			{
				var check_element_subspan_flag = document.getElementById("show_CHECK_RETURN"+checklist_checkbox[k].value);
				//alert(check_element_subspan_flag.style.display);
				if(check_element_subspan_flag.style.display=='')
				{
			    	check_all_return++;
				}
			}
		}

		
		if(namaField=="HANTAROB")
		{
			var check_checkall_spanreturn = document.getElementById("show_CHECK_RETURN_ALL"+ID_BORANGPNB);
			//alert('check_all_return : '+check_all_return);
			if(check_all_return>0)
			{
				check_checkall_spanreturn.style.display = '';	
			}
			else
			{
				check_checkall_spanreturn.style.display = 'none';
			}
		}
				
		

}



function checkMandatory_Date(elmnt,content,err_span,checkbox_elmnt) {
	
	var date_str = content;
	if(date_str=="" && checkbox_elmnt.checked == true)
	{
		$jquery("#"+err_span).html("<font class='blink' color='red'>Sila Masukkan Tarikh!<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='false' ></font>");
	}
	else
	{	
	checkFormatDate_V4(elmnt,content,err_span);	
	}
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


</script>

