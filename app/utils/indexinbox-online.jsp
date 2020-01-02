
<div id="wrapper">
            <div id="page-wrap">
 
                    <div id="content">
 
                        <div id="main">
 
                        </div>
 
                        <div id="sidebar">
 
                        </div>
                    </div>
                    <!-- Content stops here -->
            </div>
            <!-- Page-wrap stops here -->
</div>


<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<!-- flag_buka_upload ::: --><input type="hidden" id="flag_buka_upload" name="flag_buka_upload" value="$!flag_buka_upload"  />
<!--id_maininbox :::--> <input type="hidden" id="id_maininbox" name='id_maininbox' value='$!id_maininbox'  />
<input type="hidden" id="f_mesej" name='f_mesej'  />
<input type="hidden" id="flag_arkib" name='flag_arkib' value='$!flag_arkib'  />


<!--ScrollX :  --><input type="hidden" id="ScrollX" name='ScrollX'  />
<!--ScrollY :  --><input type="hidden" id="ScrollY" name='ScrollY'  />

<!--alert_saiz : --><input type="hidden" id="alert_saiz" name='alert_saiz'  value="$!alert_saiz" />


<br>
<br>
<!-- ::::::::: $!flag_simpan_doc -->
#if($!flag_simpan_doc == "yes")
#if($view_skrin == "Add")
<body onLoad="ResetScrollPosition();setReload();" >
#end
#if($view_skrin == "skrinPebualan")
<body onLoad="ResetScrollPosition();setReloadPerbualan();" >
#end
#else
<body onLoad="">
#end




<table cellpadding="2" cellspacing="1" border="0" width="100%" class="nava" align="center">
  <tr>
    <td>
    
    <br>
    
    #if($view_skrin == "skrinPebualan")
    #parse("app/utils/inboxSkrinPebualan-Online.jsp")
    #end
    
    #if($view_skrin == "Add")
    #parse("app/utils/inboxAddMesej-online.jsp")
    #end
    
    #if($view_skrin == "List")
    #parse("app/utils/inboxListMesej-online.jsp")
    #end
    <br>
    
   
    </td>
  </tr>
</table>
</body>

<script>

</script>

#if($!set_focus_mesej == "yes")
<script type="text/javascript">
function onload_autofocus()
{
var loc_mesej = document.${formName}.mesej;
loc_mesej.focus();
}
</script>
#end


<script type="text/javascript">
function pretty_date(date_str) {
    var time = ('' + date_str).replace(/-/g, "/").replace(/[TZ]/g, " ");
    var seconds = (new Date - new Date(time)) / 1000;
	
	//alert("1:"+new Date(time) +"2:"+new Date);
	
    var token = 'lepas',
        list_choice = 1;
    if (seconds < 0) {
        seconds = Math.abs(seconds);
        token = 'daripada sekarang';
        list_choice = 2;
    }
    var i = 0,
        format;
    while (format = time_formats[i++]) if (seconds < format[0]) {
        if (typeof format[2] == 'string') 
		{return format[list_choice];}
        else 
		{
		
		if(seconds>=60)
		{
		return  Math.floor(seconds / format[2]) + ' ' + format[1] + ' ' + token;
		}
		else
		{
		return 'sebentar tadi';
		}
		
		
		}
    }
    return time;
};
var time_formats = [
    [60, 'saat', 1], // 60
    [120, '1 minit lepas', '1 minit daripada sekarang'], // 60*2
    [3600, 'minit', 60], // 60*60, 60
    [7200, '1 jam lepas', '1 jam daripada sekarang'], // 60*60*2
    [86400, 'jam', 3600], // 60*60*24, 60*60
    [172800, 'semalam', 'esok'], // 60*60*24*2
    [604800, 'hari', 86400], // 60*60*24*7, 60*60*24
    [1209600, 'minggu lepas', 'minggu depan'], // 60*60*24*7*4*2
    [2419200, 'minggu', 604800], // 60*60*24*7*4, 60*60*24*7
    [4838400, 'bulan lepas', 'bulan depan'], // 60*60*24*7*4*2
    [29030400, 'bulan', 2419200], // 60*60*24*7*4*12, 60*60*24*7*4
    [58060800, 'tahun lepas', 'tahun depan'], // 60*60*24*7*4*12*2
    [2903040000, 'tahun', 29030400], // 60*60*24*7*4*12*100, 60*60*24*7*4*12
    [5806080000, 'abd lepas', 'abad depan'], // 60*60*24*7*4*12*100*2
    [58060800000, 'abad', 2903040000] // 60*60*24*7*4*12*100*20, 60*60*24*7*4*12*100
];
   
    


  function SaveScrollXY() {
    document.${formName}.ScrollX.value = document.body.scrollLeft;
    document.${formName}.ScrollY.value = document.body.scrollTop;
   }
  function ResetScrollPosition() {
    var hidx, hidy;
    hidx = '$ScrollX';
    hidy = '$ScrollY';
		
    if (typeof hidx != 'undefined' && typeof hidy != 'undefined') {
      window.scrollTo(hidx, hidy);
    }
  }


function onbuttonsimpan()
{
   if(document.getElementById('mesej').value != "")
   {
     document.getElementById('cmdDaftarPerbualan').style.display = "";
   }
   else
   {
    document.getElementById('cmdDaftarPerbualan').style.display = "none";
   }
}

function autoExpand(event,field_id){



if(event.keyCode == "13" || event.keyCode == "8")
{
var therows=0
var thetext = document.getElementById(field_id).value;
var newtext = thetext.split("\n");
//var newtext = thetext+"<br>";
therows+=newtext.length
var i;
document.getElementById(field_id).rows = therows;
return false;
}


}



function autoExpand_enter(event,field_id){

var cc = document.${formName}.mesej.value;
var dd =cc.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g,'').replace(/\s+/g,' ');

if(event.keyCode == "13")
{
//alert("XXX:"+dd);
	if(dd!="")
	{
	doAjaxCall${formName}("hantarPerbualan");
	
	}
	
}
}



function carian_enter(event,field_id){
if(event.keyCode == "13")
{	
doAjaxCall${formName}("");	
}
}

function saya()
{
alert("RAZMAN");
}


var submitHandler = function() {
  return false;
}




function keypressed(evt){
  var key = (evt) ? evt.which : event.keyCode;
  alert(String.fromCharCode(key));

  // Later .....
  if (String.fromCharCode(key)=="13"){
      // Do something
  }
}



function hadoi(event,id_maininbox)
{

if(event.keyCode == "13")
{
doAjaxCall${formName}("cariMesej","preview_user=display");
}
}



function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{


	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
	   if(lepas_or_xlepas == "2")
	   {	   
	   //$jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (my_form.value.length >= maxLen) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
my_form.value = my_form.value.substring(0, maxLen);
       maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");



}



function hapus_selected_user(id)
{

var d = document.getElementById(id);
d.parentNode.removeChild( d ); 

 if (document.${formName}.user_id.length > 0)
 {
  for (x = 0; x < document.${formName}.user_id.length; x++)
  {
  	if(document.${formName}.user_id[x].value == id)
	{
	//alert("jumpa");
	var e = document.${formName}.user_id[x];
	e.parentNode.removeChild(e); 
	}  
  }
 }
 else
 {
 if (document.${formName}.user_id != null)
 {
 //alert("jumpa 1"+document.${formName}.user_id.id);
 var t = document.${formName}.user_id;
 t.parentNode.removeChild(t);
 }
 }
 
}


		function setReload()
		{
		doAjaxCall${formName}("setReload");
		}
		
		function setReloadPerbualan()
		{
		doAjaxCall${formName}("setReloadPerbualan");
		}
		
		function backToList()
		{
		doAjaxCall${formName}("");
		}

		function bukaUpload()
		{
		doAjaxCall${formName}("bukaUpload");
		}
		
		function bukaUploadPerbualan()
		{
		
		doAjaxCall${formName}("bukaUploadPerbualan");
		}
		
		function daftarPerbualan()
		{
		doAjaxCall${formName}("hantarPerbualan");
		}
		
		
		function deleteDokumenMain(id_inboxattach)
		{
		doAjaxCall${formName}("deleteDokumenMain","id_inboxattach="+id_inboxattach);
		}
		
		function deleteDokumenPerbualan(id_inboxattach)
		{
		doAjaxCall${formName}("deleteDokumenPerbualan","id_inboxattach="+id_inboxattach);
		}
		
		
		
		function bukaMesej(id_maininbox)
		{
		document.${formName}.id_maininbox.value = id_maininbox;
		doAjaxCall${formName}("paparMesej","preview_user=display");
		}
		
		function openHapus(id_maininbox)
		{
		document.${formName}.id_maininbox.value = id_maininbox;
		doAjaxCall${formName}("openHapus","preview_user=display");
		}
		
		function openForward(id_maininbox)
		{
		document.${formName}.id_maininbox.value = id_maininbox;
		doAjaxCall${formName}("openForward","preview_user=display");
		}
		
		function deleteMesej(id_maininbox)
		{
		document.${formName}.id_maininbox.value = id_maininbox;
		input_box = confirm("Adakah anda pasti? Setelah anda menghapus maklumat \nperbualan ini, ianya tidak boleh dikembalikan semula.");
		if (input_box == true) {
		doAjaxCall${formName}("deleteMesej","preview_user=display");
		document.${formName}.cmdHapusMesej.value = "Sila Tunggu....";
		
		}
		}
		
		
		
		
		
		function pilihArkib()
		{
		document.${formName}.carian_main.value = "";
		document.${formName}.flag_arkib.value = "Y";
		doAjaxCall${formName}("pilihArkib");
		}
		
		
		function pilihUnread()
		{
		document.${formName}.carian_main.value = "";
		document.${formName}.flag_arkib.value = "";
		doAjaxCall${formName}("pilihUnread");
		}
		
		
		context.put("flag_arkib",flag_arkib);
		
		
		function setUnread(id_maininbox)
		{
		document.${formName}.id_maininbox.value = id_maininbox;
		doAjaxCall${formName}("setUnread");
		}
		
		
		function setArkib(id_maininbox)
		{
		document.${formName}.id_maininbox.value = id_maininbox;
		doAjaxCall${formName}("setArkib");
		}
		
		function setUnArkib(id_maininbox)
		{
		document.${formName}.id_maininbox.value = id_maininbox;
		doAjaxCall${formName}("setUnArkib");
		}
		
		
		
		
		function daftarUserBaru(id_maininbox)
		{
		document.${formName}.id_maininbox.value = id_maininbox;
		doAjaxCall${formName}("daftarUserBaru");
		}
		
		
		function kemaskiniUserBaru(id_maininbox)
		{
		document.${formName}.id_maininbox.value = id_maininbox;
		doAjaxCall${formName}("paparMesej","preview_user=edit");
		}
		
		
		
		
		
		
		function daftarMesej()
		{
		doAjaxCall${formName}("daftarMesej");	
		document.${formName}.cmdAddMesej.value = "Sila Tunggu....";		
		}
		
		function forwardMesej(mesej)
		{
		document.${formName}.f_mesej.value = mesej;	
		doAjaxCall${formName}("forwardMesej");	
		
		}
		
		
		
		
		function hantarMesej()
		{
		if(document.${formName}.user_id == null)
		{
		alert('Sila Pilih Nama Penerima');
	 	}
		else if(document.${formName}.mesej.value==""){
   	   	alert('Sila Masukkan Mesej');
   	   	return;
   	   	}		
		else
		{
		doAjaxCall${formName}("hantar");
		document.${formName}.cmdHantarMesej.value = "Sila Tunggu....";	
		}
		
		}
		
		
   function uploadDokumen(id_maininbox,id_inboxmesej)
	{
   if(document.${formName}.fileupload.value == "")
   {
   alert("Sila Masukkan Dokumen");  
   }	 		
	else
	{
	SaveScrollXY();	
	document.${formName}.action = "?_portal_module=ekptg.view.utils.FrmInboxUsersOnline&command=simpanDokumen&id_maininbox="+id_maininbox+"&id_inboxmesej="+id_inboxmesej+"&mesej="+document.${formName}.mesej.value+"&ScrollX="+document.${formName}.ScrollX.value+"&ScrollY="+document.${formName}.ScrollY.value;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();
	document.${formName}.cmduploadDokumen.value = "Sila Tunggu....";
	}
	}
	
	
	function uploadDokumenPerbualan(id_maininbox,id_inboxmesej)
	{
    if(document.${formName}.fileupload.value == "")
    {
    alert("Sila Masukkan Dokumen");  
    }	 		
	else
	{
	SaveScrollXY();	
	document.${formName}.action = "?_portal_module=ekptg.view.utils.FrmInboxUsersOnline&command=simpanDokumenPerbualan&id_maininbox="+id_maininbox+"&id_inboxmesej="+id_inboxmesej+"&mesej="+document.${formName}.mesej.value+"&ScrollX="+document.${formName}.ScrollX.value+"&ScrollY="+document.${formName}.ScrollY.value;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.submit();
	document.${formName}.cmduploadDokumenPerbualan.value = "Sila Tunggu....";
	}
	}
	
	function papar_Lampiran(id_inboxattach) {
    var url = "../servlet/ekptg.model.utils.DisplayBlob?id="+id_inboxattach;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function setCursor(el,st,end) 
{
	if(el.setSelectionRange) 
	{
	el.focus();
	el.setSelectionRange(st,end);
	}
	else 
	{
		if(el.createTextRange) 
		{
		range=el.createTextRange();
		range.collapse(true);
		range.moveEnd('character',end);
		range.moveStart('character',st);
		range.select();
		}
	}
}

function checkEnter(e){
 e = e || event;
 return (e.keyCode || event.which || event.charCode || 0) !== 13;
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
  
  return newText;
}







var timeout	= 500;
var closetimer	= 0;
var ddmenuitem	= 0;

// open hidden layer
function mopen(id)
{	
	// cancel close timer
	mcancelclosetime();

	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';

	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';

}
// close showed layer
function mclose()
{
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}




// close layer when click-out
document.onclick = mclose; 

function pilihsemua()
{
document.${formName}.all1_delete.checked = true;
doCheckall1_delete();
}

function batalpilihsemua()
{
document.${formName}.all1_delete.checked = false;
doCheckall1_delete();
}

function doCheckall1_delete(){    
    if (document.${formName}.all1_delete.checked == true){
	document.getElementById('cmdHapusMesej').style.display = "";
        if (document.${formName}.ids1_delete.length == null){
            document.${formName}.ids1_delete.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1_delete.length; i++){
                document.${formName}.ids1_delete[i].checked = true;
            }


        }
    } else {
	document.getElementById('cmdHapusMesej').style.display = "none";
        if (document.${formName}.ids1_delete.length == null){
            document.${formName}.ids1_delete.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids1_delete.length; i++){
                document.${formName}.ids1_delete[i].checked = false;
            }
        }
    }
}


function doUpdateCheckall1_delete(){  
var c = 0;
var x = 0;

if(document.${formName}.ids1_delete.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1_delete.length; i++)
	  {
      if (document.${formName}.ids1_delete[i].checked == false)
	  {	 
	  c++
      }else
	  {	 
	  x++
      }
	  }  
}
else
{
if (document.${formName}.ids1_delete.checked == false)
{	 
c++;
}
else
{
x++
}
	 	
}
 
      if(c>0)
	  {	  
	  document.${formName}.all1_delete.checked = false;
	  }
	  else
	  {
		
	  document.${formName}.all1_delete.checked = true;
	  }
	 
	  if(x>0) 
	  {
	  document.getElementById('cmdHapusMesej').style.display = "";
	  }
	  else
	  {
	  document.getElementById('cmdHapusMesej').style.display = "none";
	  }
	  
}





</script>




