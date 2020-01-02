<style>
.font_tajuk_utama {
	font-size: 120%;
	color : blue;
    text-shadow: 1px 1px 5px grey;
    cursor: pointer;    
}
.font_tajuk_utama_wc {
	font-size: 120%;
	color : blue;
    text-shadow: 1px 1px 5px grey;
}
.font_tajuk_sub{
	font-size: 100%;
	color : blue;
    text-shadow: 1px 1px 5px grey;
    cursor: pointer;    
}
.font_tajuk_sub_wc{
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


<br>
#parse("app/pdt/pandangan/carianTerperinci.jsp")
<br>
<div id="div_senaraiUtama" >
<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_senaraiUtama','showFolderUtama','FlagCari=$FlagCari&carianTerperinci='+$jquery('#carianTerperinci').val());			 	  
		  });
</script>
</div>


<iframe id="uploadTrg" name="uploadTrg" style="display:none"></iframe>
<input type="hidden" id="setPageCoor" name="setPageCoor" value="$getPageCoor" >
<script>

if('$after_uploadLampiran'=="Y")
{
	window.parent.viewListLampiran('$ID_PANDANGANUNDANG_AFTERUPLOAD','$ID_PANDANGANUNDANGUTAMA_AFTERUPLOAD','$COOR_UPLOAD');
}

function getTotalLampiran(name)
{
	var checkbox = document.getElementsByName('tempFieldLampiran'+name);
	if (typeof(checkbox) != 'undefined' && checkbox != null)
	{
	document.getElementById('div_statsUtama'+name).style.display = "";
	document.getElementById('div_totalLampiranUtama'+name).innerHTML = checkbox.length;
	}
	var opencloseMain = document.getElementById('HID_OPENCLOSE_'+name).value;
	//alert(" opencloseMain : "+opencloseMain);
	if(opencloseMain=="CLOSE")
	{
		document.getElementById('div_statsUtama'+name).style.display = "none";
	}
	else
	{
		document.getElementById('div_statsUtama'+name).style.display = "";
	}
}

function getTotalFolder(name)
{
	//alert('NAME TF : '+name);
	var checkbox = document.getElementsByName('tempFieldFolder'+name);
	//alert(" TF : "+checkbox);
	if (typeof(checkbox) != 'undefined' && checkbox != null)
	{	
	document.getElementById('div_statsUtama'+name).style.display = "";
	//alert(" TF count : "+checkbox.length);
	document.getElementById('div_totalDirUtama'+name).innerHTML = checkbox.length;
	}
	var opencloseMain = document.getElementById('HID_OPENCLOSE_'+name).value;
	if(opencloseMain=="CLOSE")
	{
		document.getElementById('div_statsUtama'+name).style.display = "none";
		
	}
	else
	{
		document.getElementById('div_statsUtama'+name).style.display = "";
	}
}

function checkOpenClose(by_id)
{
	return $jquery('#check_'+by_id).val();
}

function checkUFField(by_id)
{
	
	//var ch = document.getElementsByName(by_name);
	var bool_check = false;
	var dahopen = "close";
	/*
	if (ch.value != 'undefined' && ch != null)
	{
		bool_check = true;
	}
	*/
	if($jquery('#'+by_id).length) 
	{
		
		if($jquery('#check_'+by_id).val()=="CLOSE")
		{
			bool_check = true;
			$jquery('#check_'+by_id).val('OPEN');
		}
		else
		{
			dahopen = "open";
			bool_check = false;	
		}
		
		//check_$ID_PANDANGANUNDANGUTAMA$SEND_NUMBERING
	}
	
	
	//alert(by_id+' : ['+bool_check+'] val : '+$jquery('#'+by_id).val()+' -- '+dahopen);
	return bool_check;
}


function countChecked(form) {
    var index, element;
    for (index = 0; index < form.elements.length; ++index) {
        element = form.elements[index];
        // You may want to check `element.name` here as well
        if (element.type.toUpperCase() == "CHECKBOX" && element.checked) {
            ++checked;
        }
    }
    return checked;
}


function getPageLocation(val)
{
	var tempScrollTop = $jquery(window).scrollTop();
	$jquery('#scrolPosition'+val).val(tempScrollTop);
	return tempScrollTop;
}
function getPageLocationById(val)
{
	var tempScrollTop = $jquery(window).scrollTop();
	$jquery('#'+val).val(tempScrollTop);
	return tempScrollTop;
}
function setPageLocation(val)
{
$jquery(window).scrollTop(val);
}
function validateCarian()
{
	   var bool_check = true;
	   
	   if(document.getElementById("carianTerperinci").value=="" && document.getElementById("carianTerperinciLampiran").value=="")
	   {
		   alert("Masukkan maklumat carian!");
		   document.getElementById("carianTerperinci").focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
}

function validateTajukUtama(ID_PANDANGANUNDANGUTAMA)
{
	   var bool_check = true;
	   
	   if(document.getElementById("TAJUK_UTAMA_"+ID_PANDANGANUNDANGUTAMA).value=="")
	   {
		   alert("Masukkan Rujukan Utama!");
		   document.getElementById("TAJUK_UTAMA_"+ID_PANDANGANUNDANGUTAMA).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("fieldvalidateMainDir_"+ID_PANDANGANUNDANGUTAMA).value=='Y')
	   {
		   //alert("3");
		   alert("Nama Rujukan Telah Wujud!");
		   document.getElementById("TAJUK_UTAMA_"+ID_PANDANGANUNDANGUTAMA).focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
}

function validateCheckLampiran(ID_PANDANGANUNDANGUTAMA,ID_PANDANGANUNDANG,ID_PANDANGANLAMPIRAN)
{
	   //alert("1 :"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN);
	   var bool_check = true;
	   
	   //alert(" ccc : "+document.getElementById("fieldvalidatelampiran_"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN).value);
	   
	   if(document.getElementById("editLampiranField_"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN).value=="")
	   {
		   //alert("2");
		   alert("Masukkan Nama Dokumen!");
		   document.getElementById("editLampiranField_"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("fieldvalidatelampiran_"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN).value=='Y')
	   {
		   //alert("3");
		   alert("Nama Dokumen Telah Wujud!");
		   document.getElementById("editLampiranField_"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN).focus();
		   bool_check = false;
	   }
	   //alert("4"+bool_check);
	   return bool_check;
}


function validateCheckSubDir(ID_PANDANGANUNDANGUTAMA,ID_PANDANGANUNDANG,ID_REFER)
{
	//#set($spanvalidatesubdir = "validate_subdir_"+$viewSubFoler.ID_PANDANGANUNDANGUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_PANDANGANUNDANG)
	//#set($spansubviewfield = "editSubDirField_"+$viewSubFoler.ID_PANDANGANUNDANGUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_PANDANGANUNDANG)
	//#set($fieldvalidateSubDir = "fieldvalidateSubDir_"+$viewSubFoler.ID_PANDANGANUNDANGUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_PANDANGANUNDANG)

	   //alert("1 :"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN);
	   var bool_check = true;
	   
	   //alert(" ccc : "+document.getElementById("fieldvalidatelampiran_"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN).value);
	   
	   if(document.getElementById("editSubDirField_"+ID_PANDANGANUNDANGUTAMA+"_"+ID_REFER+"_"+ID_PANDANGANUNDANG).value=="")
	   {
		   //alert("2");
		   alert("Masukkan Nama Rujukan!");
		   document.getElementById("editSubDirField_"+ID_PANDANGANUNDANGUTAMA+"_"+ID_REFER+"_"+ID_PANDANGANUNDANG).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("fieldvalidateSubDir_"+ID_PANDANGANUNDANGUTAMA+"_"+ID_REFER+"_"+ID_PANDANGANUNDANG).value=='Y')
	   {
		   //alert("3");
		   alert("Nama Rujukan Telah Wujud!");
		   document.getElementById("editSubDirField_"+ID_PANDANGANUNDANGUTAMA+"_"+ID_REFER+"_"+ID_PANDANGANUNDANG).focus();
		   bool_check = false;
	   }
	   //alert("4"+bool_check);
	   return bool_check;
}

/*
#set($spanmainviewfield = "editMainDirField_"+$viewSubFoler.ID_PANDANGANUNDANGUTAMA)
#set($spanvalidateMaindir = "validate_maindir_"+$viewSubFoler.ID_PANDANGANUNDANGUTAMA)
#set($fieldvalidateMainDir = "fieldvalidateMainDir_"+$viewSubFoler.ID_PANDANGANUNDANGUTAMA)
*/
function validateCheckMainDir(ID_PANDANGANUNDANGUTAMA)
{
	   var bool_check = true;
	   
	   //alert(" ccc : "+document.getElementById("fieldvalidatelampiran_"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN).value);
	   
	   if(document.getElementById("editMainDirField_"+ID_PANDANGANUNDANGUTAMA).value=="")
	   {
		   //alert("2");
		   alert("Masukkan Nama Rujukan!");
		   document.getElementById("editMainDirField_"+ID_PANDANGANUNDANGUTAMA).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("fieldvalidateMainDir_"+ID_PANDANGANUNDANGUTAMA).value=='Y')
	   {
		   //alert("3");
		   alert("Nama Rujukan Telah Wujud!");
		   document.getElementById("editMainDirField_"+ID_PANDANGANUNDANGUTAMA).focus();
		   bool_check = false;
	   }
	   //alert("4"+bool_check);
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


function highlight_sub(size_rekod,search,nama_list)
{
	//alert(" size_rekod : "+size_rekod+" search : "+search+" nama_list : "+nama_list)
	if(search != "")
	{
		var word = search;
		searchArray = [word];	
		highlightStartTag = "<font style='color:black; background-color:yellow;'>";
		highlightEndTag = "</font>";
		
		  for (x = 0; x < parseInt(size_rekod); x++)
		  {
		  var span1 = "span1"+nama_list+(x+1);
		  //alert(" span1 : "+span1);
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
}

function highlight(size_rekod,search,nama_list)
{
	//alert(" size_rekod : "+size_rekod+" search : "+search+" nama_list : "+nama_list)
	if(search != "")
	{
		var word = search;
		searchArray = [word];	
		highlightStartTag = "<font style='color:black; background-color:yellow;'>";
		highlightEndTag = "</font>";
		
		  for (x = 0; x < parseInt(size_rekod); x++)
		  {
		  var span1 = "span1"+nama_list+(x+1);
		  //alert(" span1 : "+span1);
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

function viewListLampiran(ID_PANDANGANUNDANG,ID_PANDANGANUNDANGUTAMA,COOR_UPLOAD)
{
	doDivAjaxCall$formname('divSubLampiran'+ID_PANDANGANUNDANG,'showLampiran','COOR_UPLOAD='+COOR_UPLOAD+'&FLAG_LAMP_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_PANDANGANUNDANGUTAMA='+ID_PANDANGANUNDANGUTAMA+'&ID_REFER='+ID_PANDANGANUNDANG+'&ID_PANDANGANUNDANG='+ID_PANDANGANUNDANG+'&LAYER=&AUTOLOAD=N&carianTerperinci=&carianTerperinciLampiran=');
	
}



function setPageLocation(val)
{
	$jquery(window).scrollTop(val);
}

function paparDoc(ID_PANDANGANLAMPIRAN) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlobLampiranUndang?ID_PANDANGANLAMPIRAN="+ID_PANDANGANLAMPIRAN;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function getPageCoor()
{
	var tempScrollTop = $jquery(window).scrollTop();
	$jquery('#setPageCoor').val(tempScrollTop);
	return tempScrollTop;
}

function saveLampiran(div,command,ID_PANDANGANUNDANG,ID_PANDANGANUNDANGUTAMA)
{
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmSenaraiDokumen&divLampiran="+div
			+"&command="+command+"&ID_PANDANGANUNDANG="+ID_PANDANGANUNDANG
			+"&ID_PANDANGANUNDANGUTAMA="+ID_PANDANGANUNDANGUTAMA+'&getPageCoor='+getPageCoor();
	document.${formName}.method="post";
	document.${formName}.target="uploadTrg";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();	
}

</script>