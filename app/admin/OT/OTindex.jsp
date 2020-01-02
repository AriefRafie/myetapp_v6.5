
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
#parse("app/admin/OT/OTSenaraiUtama.jsp")
</div>




<script>
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

function valEditTT(ID_TRANSAKSI)
{
	   var bool_check = true;
	   if(document.getElementById("NAMA_AKTIVITI_"+ID_TRANSAKSI).value=="")
	   {
		   alert("Masukkan nama aktiviti!");
		   document.getElementById("NAMA_AKTIVITI_"+ID_TRANSAKSI).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("NAMA_TABLE_"+ID_TRANSAKSI).value=="")
	   {
		   alert("Masukkan nama table!");
		   document.getElementById("NAMA_TABLE_"+ID_TRANSAKSI).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("FIELD_CHECK_"+ID_TRANSAKSI).value=="")
	   {
		   alert("Masukkan field!");
		   document.getElementById("FIELD_CHECK_"+ID_TRANSAKSI).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("ID_SEKSYEN_"+ID_TRANSAKSI).value=="")
	   {
		   alert("Masukkan bahagian!");
		   document.getElementById("ID_SEKSYEN_"+ID_TRANSAKSI).focus();
		   bool_check = false;
	   }
	   /*
	   else if(document.getElementById("QUERY_NOFAIL_"+ID_TRANSAKSI).value=="")
	   {
		   alert("Masukkan query!");
		   document.getElementById("QUERY_NOFAIL_"+ID_TRANSAKSI).focus();
		   bool_check = false;
	   }*/	   
	   else if(document.getElementById("checkTransaksiTT_"+ID_TRANSAKSI).value=="false")
	   {
		   alert("Sila pastikan maklumat 'table' and 'field' adalah tepat!");
		   //document.getElementById("ID_SEKSYEN_"+ID_TRANSAKSI).focus();
		   bool_check = false;
	   }
	   
	   return bool_check;   
}

function valEditPMT(ID_MINITTRANSAKSI)
{
	   var bool_check = true;
	   if(document.getElementById("ID_TRANSAKSI_"+ID_MINITTRANSAKSI).value=="")
	   {
		   alert("Masukkan nama aktiviti!");
		   document.getElementById("ID_TRANSAKSI_"+ID_MINITTRANSAKSI).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("MINIT_INSERT_"+ID_MINITTRANSAKSI).value=="")
	   {
		   alert("Masukkan minit untuk kemasukkan!");
		   document.getElementById("MINIT_INSERT_"+ID_MINITTRANSAKSI).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("MINIT_UPDATE_"+ID_MINITTRANSAKSI).value=="")
	   {
		   alert("Masukkan minit untuk pengemaskinian!");
		   document.getElementById("MINIT_UPDATE_"+ID_MINITTRANSAKSI).focus();
		   bool_check = false;
	   }	   	   
	   else if(document.getElementById("checkTransaksiPMT_"+ID_MINITTRANSAKSI).value=="false")
	   {
		   alert("Sila pastikan maklumat 'aktiviti' adalah tepat!");
		   document.getElementById("ID_TRANSAKSI_"+ID_MINITTRANSAKSI).focus();
		   bool_check = false;
	   }
	   return bool_check;   
}

function valEditPOT(ID_PERMOHONANOT)
{
	   var bool_check = true;
	   if(document.getElementById("POT_TARIKH_OT_MULA_"+ID_PERMOHONANOT).value=="")
	   {
		   alert("Masukkan tarikh mula!");
		   document.getElementById("POT_TARIKH_OT_MULA_"+ID_PERMOHONANOT).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("POT_TARIKH_OT_AKHIR_"+ID_PERMOHONANOT).value=="")
	   {
		   alert("Masukkan tarikh akhir!");
		   document.getElementById("POT_TARIKH_OT_AKHIR_"+ID_PERMOHONANOT).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("POT_ID_PELULUS_"+ID_PERMOHONANOT).value=="")
	   {
		   alert("Masukkan pegawai pelulus!");
		   document.getElementById("POT_ID_PELULUS_"+ID_PERMOHONANOT).focus();
		   bool_check = false;
	   }	   
	   else if(document.getElementById("CHECK_POT_TARIKH_OT_MULA_"+ID_PERMOHONANOT).value=="false")
	   {
		   alert("Sila pastikan format tarikh mula adalah tepat!");
		   document.getElementById("POT_TARIKH_OT_MULA_"+ID_PERMOHONANOT).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("CHECK_POT_TARIKH_OT_AKHIR_"+ID_PERMOHONANOT).value=="false")
	   {
		   alert("Sila pastikan format tarikh akhir adalah tepat!");
		   document.getElementById("POT_TARIKH_OT_AKHIR_"+ID_PERMOHONANOT).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("CHECK_DUPLICATE_DATE_"+ID_PERMOHONANOT).value=="false")
	   {
		   alert("Sila pastikan maklumat OT adalah tepat!");
		   document.getElementById("POT_TARIKH_OT_MULA_"+ID_PERMOHONANOT).focus();
		   bool_check = false;
	   }
	   else if(ID_PERMOHONANOT!="")
	   {
		   var stats = document.getElementById("POT_ID_STATUS_"+ID_PERMOHONANOT).value;
		   if(stats!="2" && stats!="3")
		   {
			   alert("Sila masukkan kelulusan permohonan OT!");
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

function printOTForm(divName,ID_PERMOHONANOT,NO_OT,hideDiv) {
	//alert("divName : "+divName+" ID_PERMOHONANOT : "+ID_PERMOHONANOT+" NO_OT : "+NO_OT);
	//var carianTerperinci = document.getElementById("carianTerperinci").value;
    var originalContents = document.body.innerHTML;
    $jquery("#"+divName+" :button").hide();
    
    var elementHide =  document.getElementById(hideDiv);
    if (typeof(elementHide) != 'undefined' && elementHide != null)
    {
    	//alert("2");
    	elementHide.style.display = "";
    } 
    
    
    var header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>Permohonan OT : '+NO_OT+'</b></div><br></header>'
	
    //alert("2");
    var printContents = document.getElementById(divName).innerHTML;
    //alert("printContents : "+printContents);
    var footer ="";
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
   
    popupWin.document.write('<html><body onload="window.print()">'+header+'<div class="page-break" >'+ printContents + '</div>'+footer+'</html>');
    popupWin.document.close(); 
    elementHide.style.display = "none";
    document.body.innerHTML = originalContents;
    //document.getElementById("carianTerperinci").value = carianTerperinci;
    return false;
    
}

function printHideDiv(divName) {
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
    //document.body.innerHTML = originalContents;
    //document.getElementById("carianTerperinci").value = carianTerperinci;
    elementHide.style.display = "none";
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



</script>
