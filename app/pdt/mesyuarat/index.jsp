<style>

.hideline:focus {
  border-color: inherit;
  -webkit-box-shadow: none;
  box-shadow: none;
}

u.dotted_red{
  border-bottom: 1px dashed  #FF0000;
  text-decoration: none;
}

.spanEmel {
    margin:2px 1px 2px 1px; 
    padding:2px 2px 2px 2px; 
	border: 1px dotted #0000FF;
	display: inline-block;
	position:relative; 
    border-radius:4px 4px 4px 4px;
}

.removeBorderField {
   outline : none;
}


.boxsizingBorder {
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box;
}

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
#parse("app/pdt/mesyuarat/carianTerperinci.jsp")
<br>
<div id="div_senaraiUtama" >
<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_senaraiUtama','showFolderUtama','BILANGAN_AFTERINSERT=$BILANGAN_AFTERINSERT&TAHUN_AFTERINSERT=$TAHUN_AFTERINSERT&TAJUK_MESYUARAT_AFTERINSERT=$TAJUK_MESYUARAT_AFTERINSERT&FLAG_AFTERINSERT=$FLAG_AFTERINSERT&FlagCari=$FlagCari&carianTerperinci='+$jquery('#carianTerperinci').val());			 	  
		  });
		  
</script>
</div>


<iframe id="uploadTrg" name="uploadTrg" style="display:none"></iframe>
<input type="hidden" id="setPageCoor" name="setPageCoor" value="$getPageCoor" >
<script>


function showListEmelOninput(e,elem,div,id_pk,id_list,outputfield)
{
	//elem.value = elem.value +';';
	//setupListEmel(elem,div,id_pk);
	
	var current_value = elem.value;
	var outString = current_value.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
	var current_value_filter = current_value.replace(";", "");
	//setupListEmel(outString,current_value_filter,elem,div,id_pk)

	//var val = this.value;
    if($jquery('#'+id_list).find('option').filter(function(){
		//alert('ada');
        return this.value.toUpperCase() === current_value.toUpperCase();        
    }).length) {
		
		
        if(ValidateEmailFormat(current_value_filter)==false)
		{
			alert('Masukkan Emel Dengan Format Yang Sah!');
			elem.value = current_value_filter;
			elem.focus();
		}
		
		else if(document.getElementById(div).innerHTML.toLowerCase().includes(current_value_filter))
		{
			alert('Emel telah wujud!');
			elem.value = "";
			elem.focus();
		}
		
		else
		{
			setupListEmel(outString,current_value_filter,elem,div,id_pk,outputfield);			
		}
			   
    }


	
}

function showListEmel(e,elem,div,id_pk,outputfield)
{
	//alert(elem.value);
	var keynum;
	if(window.event) { // IE                    
      keynum = e.keyCode;
    } else if(e.which){ // Netscape/Firefox/Opera                   
      keynum = e.which;
    }
	
	//alert(keynum);
	
	if(keynum==59 || keynum==13)
	{
		var current_value = elem.value;
		var outString = current_value.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
		if(outString!="")
		{
			var current_value_filter = current_value.replace(";", "");
			if(ValidateEmailFormat(current_value_filter)==false)
			{
				alert('Masukkan Emel Bahagian Dengan Format Yang Sah!');
				elem.value = current_value_filter;
				elem.focus();
			}
			else if(document.getElementById(div).innerHTML.toLowerCase().includes(current_value_filter))
			{
				alert('Emel telah wujud!');
				elem.value = "";
				elem.focus();
			}
			else
			{
				setupListEmel(outString,current_value_filter,elem,div,id_pk,outputfield);
			}
		}
		else
		{
			elem.value = "";
		}
		
	} 
}


function setupListEmel(outString,current_value_filter,elem,div,id_pk,outputfield)
{
 	var total_stored_emel = countEmelHtml(div);
	if(total_stored_emel==10)
	{
		alert("Sila pastikan jumlah emel tidak melebihi "+total_stored_emel+"!");
		elem.value = current_value_filter;
		elem.focus();
	}
	else
	{
		var new_html;
		var current_value_html = "<span id='"+id_pk+outString+"' ><span class='spanEmel'><xxx>"+ current_value_filter + "</xxx><span id='delete_"+id_pk+outString+"'><a href=\"javascript:removeElemen('"+id_pk+outString+"','"+outputfield+"','"+div+"','"+id_pk+"');\"><font color='red'><strong>&nbsp;X</strong></font></a></span></span></span>";
		elem.value = "";
		elem.blur();			
		var curr_html = document.getElementById(div).innerHTML;
		new_html = curr_html  + current_value_html;
		document.getElementById(div).innerHTML = new_html.trim();
		document.getElementById(outputfield).value = new_html.trim();
		if(id_pk!="")
		{
			//doDivAjaxCall3$formname('div_rowFolderUtama'+id_pk,'SimpanMainDir','FLAG_ADD_EMEL=Y&ID_MESYUARATUTAMA='+id_pk);
		}
	}
			
}

function removeElemen(idelem,outputfield,div,id_pk)
{
	//alert(" x : "+idelem);
	$jquery("#"+idelem).remove();
	//alert("div : "+div+" x : "+document.getElementById(div));
	document.getElementById(outputfield).value = document.getElementById(div).innerHTML;
	//alert('total : '+countEmelHtml(div));
	if(countEmelHtml(div)==0)
	{
		document.getElementById(outputfield).value = "";
	}
	//alert('save')
	
	if(id_pk!="")
	{
		//doDivAjaxCall3$formname('div_rowFolderUtama'+id_pk,'SimpanMainDir','FLAG_ADD_EMEL=Y&ID_MESYUARATUTAMA='+id_pk);
	}
}


function countEmelHtml(div)
{
	var arrayEmel = [];
    var elements = document.getElementById(div).getElementsByTagName("xxx");
    for(var i = 0; i < elements.length; i++) {
       var current = elements[i];
        if(current.children.length === 0 && current.textContent.replace(/ |\n/g,'') !== '') {
           // Check the element has no children && that it is not empty
           arrayEmel.push(current.textContent);
        }
    } 
	//alert(" arrayEmel : "+arrayEmel.length);
	return arrayEmel.length;
}

function hideByTag(div,tagname)
{
	//alert("div : "+document.getElementById(div).innerHTML+" tagname : "+tagname);
	//var arrayEmel = [];
    var elements = document.getElementById(div).getElementsByTagName(tagname);
	//alert(elements.length);
    for(var i = 0; i < elements.length; i++) {
       var current = elements[i];
	   current.style.display = 'none';
	   /*
        if(current.children.length === 0 && current.textContent.replace(/ |\n/g,'') !== '') {
           // Check the element has no children && that it is not empty
           arrayEmel.push(current.textContent);
        }
		*/
    } 
	//alert(" arrayEmel : "+arrayEmel.length);
	//return arrayEmel.length;
}


if('$after_uploadLampiran'=="Y")
{
	window.parent.viewListLampiran('$ID_MESYUARATUTAMA_AFTERUPLOAD','$COOR_UPLOAD');
}

/*
if(document.getElementById("carianMesyuarat").value=="" && document.getElementById("carianTerperinci").value=="" 
			   && document.getElementById("carianBahagian").value=="" && document.getElementById("carianLampiran").value==""
				   && document.getElementById("carianTahun").value==""  && document.getElementById("carianBilangan").value==""
					    && document.getElementById("carianStatus").value==""
					    )
*/

function kosongkanCarian()
{
	//var radstat = document.getElementById("radioCarianStatus");
	//alert("1"+radstat[0].value);
	
	document.getElementById("carianMesyuarat").value="";
	document.getElementById("carianTerperinci").value="";
	document.getElementById("carianBahagian").value="";
	document.getElementById("carianLampiran").value="";
	document.getElementById("carianTahun").value="";
	document.getElementById("carianBilangan").value="";
	document.getElementById("carianStatus").value="";
	document.getElementById("carianStatusMesyuarat").value="";
	
	document.getElementsByName("radioCarianStatus")[0].checked="";
	document.getElementsByName("radioCarianStatus")[1].checked="";
	document.getElementsByName("radioCarianStatus")[2].checked="checked";
	
	document.getElementsByName("radioCarianStatusMesyuarat")[0].checked="";
	document.getElementsByName("radioCarianStatusMesyuarat")[1].checked="";
	document.getElementsByName("radioCarianStatusMesyuarat")[2].checked="checked";
}

function getTotalTindakan(name)
{
	//alert("name : "+name);
	var checkbox = document.getElementsByName('tempFieldTindakan'+name);
	
	if (typeof(checkbox) != 'undefined' && checkbox != null)
	{
		//alert("1 : ");
	document.getElementById('div_statsUtama'+name).style.display = "";
	//alert(checkbox.length);
	document.getElementById('div_totalTindakanUtama'+name).innerHTML = checkbox.length;
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
		
		//check_$ID_MESYUARATUTAMA$SEND_NUMBERING
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
	   
	   if(document.getElementById("carianMesyuarat").value=="" && document.getElementById("carianTerperinci").value=="" 
			   && document.getElementById("carianBahagian").value=="" && document.getElementById("carianLampiran").value==""
				   && document.getElementById("carianTahun").value==""  && document.getElementById("carianBilangan").value==""
					    && document.getElementById("carianStatus").value=="" && document.getElementById("carianStatusMesyuarat").value==""
						&& document.getElementById("carianPIC").value==""
					    )
	   {
		   alert("Masukkan maklumat carian!");
		   document.getElementById("carianTerperinci").focus();
		   bool_check = false;
	   }	   
	   else if(document.getElementById("carianTahun").value!="" && 
			   yearValidation(document.getElementById("carianTahun").value)==false)
	   {
		   //alert("C : "+yearValidation(document.getElementById("editTahunField_"+ID_MESYUARATUTAMA).value));
		   alert("Masukkan Format Tahun Mesyuarat dengan Tepat!");
		   document.getElementById("carianTahun").focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
}

function validateTajukUtama(ID_MESYUARATUTAMA)
{
	   var bool_check = true;
	   
	   if(document.getElementById("TAJUK_MESYUARAT_"+ID_MESYUARATUTAMA).value=="")
	   {
		   alert("Masukkan Tajuk Mesyuarat!");
		   document.getElementById("TAJUK_MESYUARAT_"+ID_MESYUARATUTAMA).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("fieldvalidateMainDir_"+ID_MESYUARATUTAMA).value=='Y')
	   {
		   //alert("3");
		   alert("Nama Mesyuarat Telah Wujud!");
		   document.getElementById("TAJUK_MESYUARAT_"+ID_MESYUARATUTAMA).focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
}

function validateCheckTindakan(ID_MESYUARATUTAMA,ID_MESYUARATCONTENT,ID_MESYUARATTINDAKAN)
{
	   //alert("1 :"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN);
	   var bool_check = true;
	   
	   //alert(" ccc : "+document.getElementById("fieldvalidatelampiran_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).value);
	   
	   //alert(document.getElementById("editEmelBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN));
	   
	   
	   var emel_to = '';
	   if(ID_MESYUARATTINDAKAN=="")
	   {
		   emel_to = document.getElementById("editEmelBahagian_"+ID_MESYUARATCONTENT+"_X000000X").value;	   	   
	   }
	   else
	   {
		   emel_to = document.getElementById("editEmelBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).value;
	   }
	   
	   
	   
	   if(document.getElementById("editBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).value=="")
	   {
		   //alert("2");
		   alert("Masukkan Nama Bahagian!");
		   document.getElementById("editBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("fieldvalidateTindakan_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).value=='Y')
	   {
		   //alert("3");
		   alert("Nama Bahagian Telah Wujud!");
		   document.getElementById("editBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).focus();
		   bool_check = false;
	   }
	   else if(emel_to=="")
		{
		alert("Masukkan Emel Bahagian!");
		document.getElementById("editEmelBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).focus();
		bool_check = false;
		}
		/*
		else if(document.getElementById("editEmelBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).value!="" && ValidateEmailFormat(document.getElementById("editEmelBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).value)==false)
		{
		alert("Masukkan Emel Bahagian Dengan Format Yang Sah!");
		document.getElementById("editEmelBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).focus();
		bool_check = false;
		}
		*/
		else if(document.getElementById("editStatusBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).value=="")
	   {
		   //alert("2");
		   alert("Masukkan Status Tindakan!");
		   document.getElementById("editStatusBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).focus();
		   bool_check = false;
	   }
	   //alert("4"+bool_check);
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

function validateCheckSubDir(ID_MESYUARATUTAMA,ID_MESYUARATCONTENT,ID_REFER)
{
	//#set($spanvalidatesubdir = "validate_subdir_"+$viewSubFoler.ID_MESYUARATUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_MESYUARATCONTENT)
	//#set($spansubviewfield = "editSubDirField_"+$viewSubFoler.ID_MESYUARATUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_MESYUARATCONTENT)
	//#set($fieldvalidateSubDir = "fieldvalidateSubDir_"+$viewSubFoler.ID_MESYUARATUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_MESYUARATCONTENT)

	   //alert("1 :"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN);
	   var bool_check = true;
	   
	   //alert(" ccc : "+document.getElementById("fieldvalidatelampiran_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).value);
	   
	   if(document.getElementById("editSubDirField_"+ID_MESYUARATUTAMA+"_"+ID_REFER+"_"+ID_MESYUARATCONTENT).value=="")
	   {
		   //alert("2");
		   alert("Masukkan Item Mesyuarat!");
		   document.getElementById("editSubDirField_"+ID_MESYUARATUTAMA+"_"+ID_REFER+"_"+ID_MESYUARATCONTENT).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("fieldvalidateSubDir_"+ID_MESYUARATUTAMA+"_"+ID_REFER+"_"+ID_MESYUARATCONTENT).value=='Y')
	   {
		   //alert("3");
		   alert("Item Mesyuarat Telah Wujud!");
		   document.getElementById("editSubDirField_"+ID_MESYUARATUTAMA+"_"+ID_REFER+"_"+ID_MESYUARATCONTENT).focus();
		   bool_check = false;
	   }
	   //alert("4"+bool_check);
	   return bool_check;
}

/*
#set($spanmainviewfield = "editMainDirField_"+$viewSubFoler.ID_MESYUARATUTAMA)
#set($spanvalidateMaindir = "validate_maindir_"+$viewSubFoler.ID_MESYUARATUTAMA)
#set($fieldvalidateMainDir = "fieldvalidateMainDir_"+$viewSubFoler.ID_MESYUARATUTAMA)
*/
function validateCheckMainDir(ID_MESYUARATUTAMA)
{
	   var bool_check = true;
	   
	   var emel_pic = '';
	   if(ID_MESYUARATUTAMA=="")
	   {
		   emel_pic = document.getElementById("editEMEL_PIC_X000000X").value;	   	   
	   }
	   else
	   {
		   emel_pic = document.getElementById("editEMEL_PIC_"+ID_MESYUARATUTAMA).value;
	   }
	   
	   //alert(" x : "+"editTahunField_"+ID_MESYUARATUTAMA+" ("+document.getElementById("editTahunField_"+ID_MESYUARATUTAMA).value+")");
	   //alert(" ccc : "+document.getElementById("fieldvalidatelampiran_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN).value);
	   
	   if(document.getElementById("editMainDirField_"+ID_MESYUARATUTAMA).value=="")
	   {
		   //alert("2");
		   alert("Masukkan Tajuk Masyuarat!");
		   document.getElementById("editMainDirField_"+ID_MESYUARATUTAMA).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("editTahunField_"+ID_MESYUARATUTAMA).value=="")
	   {
		   //alert("2");
		   alert("Masukkan Tahun Mesyuarat!");
		   document.getElementById("editTahunField_"+ID_MESYUARATUTAMA).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("editTahunField_"+ID_MESYUARATUTAMA).value!="" && 
			   yearValidation(document.getElementById("editTahunField_"+ID_MESYUARATUTAMA).value)==false)
	   {
		   //alert("C : "+yearValidation(document.getElementById("editTahunField_"+ID_MESYUARATUTAMA).value));
		   alert("Masukkan Format Tahun Mesyuarat dengan Tepat!");
		   document.getElementById("editTahunField_"+ID_MESYUARATUTAMA).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("fieldvalidateMainDir_"+ID_MESYUARATUTAMA).value=='Y')
	   {
		   //alert("3");
		   alert("Tajuk Masyuarat Telah Wujud!");
		   document.getElementById("editMainDirField_"+ID_MESYUARATUTAMA).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("editStatusMesyuarat_"+ID_MESYUARATUTAMA).value=="")
	   {
		   //alert("2");
		   alert("Masukkan Status Mesyuarat!");
		   document.getElementById("editStatusMesyuarat_"+ID_MESYUARATUTAMA).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("editPIC_"+ID_MESYUARATUTAMA).value=="")
	   {
		   //alert("2");
		   alert("Masukkan PIC!");
		   document.getElementById("editPIC_"+ID_MESYUARATUTAMA).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("editTEL_PIC_"+ID_MESYUARATUTAMA).value=="")
	   {
		   //alert("2");
		   alert("Masukkan Tel PIC!");
		   document.getElementById("editTEL_PIC_"+ID_MESYUARATUTAMA).focus();
		   bool_check = false;
	   }
	   
	   
	    else if(emel_pic=="")
	   {
		   //alert("2");
		   alert("Masukkan Emel!");
		   document.getElementById("editEMEL_PIC_"+ID_MESYUARATUTAMA).focus();
		   bool_check = false;
	   }
	   //alert("4"+bool_check);
	   return bool_check;
}


function yearValidation(year) {
	  //alert(" year : "+year);
	  var text = /^[0-9]+$/;
	  if(year.length==4) 
	  {
		    if (year != 0) {
		        if ((year != "") && (!text.test(year))) {
	
		            //alert("Please Enter Numeric Values Only");
		            return false;
		        }
	
		        if (year.length != 4) {
		            //alert("Year is not proper. Please check");
		            return false;
		        }
		        var current_year=new Date().getFullYear();
		        if((year < 1920) || (year > current_year))
		        {
		            //alert("Year should be in range 1920 to current year");
		            return false;
		        }
		        return true;
		    } 
	  }
	  else
	  {
		  return false;
	  }
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

function viewListLampiran(ID_MESYUARATUTAMA,COOR_UPLOAD)
{
	doDivAjaxCall$formname('divSubLampiran'+ID_MESYUARATUTAMA,'showLampiran','COOR_UPLOAD='+COOR_UPLOAD+'&FLAG_LAMP_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA='+ID_MESYUARATUTAMA+'&ID_REFER=&LAYER=&AUTOLOAD=N&carianLampiran=');
	
}



function validateCheckLampiran(ID_MESYUARATUTAMA,ID_MESYUARATDOKUMEN)
{
	   //alert("1 :"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN);
	   var bool_check = true;
	   
	   //alert(" ccc : "+document.getElementById("fieldvalidatelampiran_"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN).value);
	   
	   if(document.getElementById("editLampiranField_"+ID_MESYUARATDOKUMEN).value=="")
	   {
		   //alert("2");
		   alert("Masukkan Nama Dokumen!");
		   document.getElementById("editLampiranField_"+ID_MESYUARATDOKUMEN).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("fieldvalidatelampiran_"+ID_MESYUARATDOKUMEN).value=='Y')
	   {
		   //alert("3");
		   alert("Nama Dokumen Telah Wujud!");
		   document.getElementById("editLampiranField_"+ID_MESYUARATDOKUMEN).focus();
		   bool_check = false;
	   }
	   //alert("4"+bool_check);
	   return bool_check;
}


function setPageLocation(val)
{
	$jquery(window).scrollTop(val);
}

function paparDoc(ID_MESYUARATDOKUMEN) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlobLampiranMesyuarat?ID_MESYUARATDOKUMEN="+ID_MESYUARATDOKUMEN;
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

function saveLampiran(div,command,ID_MESYUARATUTAMA)
{
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmMesyuarat&divLampiran="+div
			+"&command="+command+"&ID_MESYUARATUTAMA="+ID_MESYUARATUTAMA+'&getPageCoor='+getPageCoor();
	document.${formName}.method="post";
	document.${formName}.target="uploadTrg";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();	
}

function validate(evt){
   
}

function numberOnly(elmnt,content) {
	
		
	
		if (isNaN(content)) {
			
			//alert(content);
			
			if(content=="")
			{
				elmnt.value = 1;
			}
			else
			{
				elmnt.value = RemoveNonNumeric(content);
				//elmnt.value = elmnt.value.replace(/[^1-9]/g,"");
			}
			
			return;
		}
	
}

function check_length(my_form,maxLen,text_num)
{
	my_form.setAttribute('maxlength',maxLen);
	var current_value = my_form.value;
	var total_check = maxLen - current_value.length;	
	$jquery("#"+text_num).html("<br><font color='blue'>"+total_check+"</font> Baki Aksara");
}

function fromRadioToText(elem,val,textfield)
{
	document.getElementById(textfield).value = val;
}

function RemoveNonNumeric_V3(elmnt,content)
{
      var strValidCharacters = "1234567890/-";
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

</script>