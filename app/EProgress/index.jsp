<style>

@media all {
	.page-break	{ display: none; }
}

@media print {
	.page-break	{ display: block; page-break-before: always; }
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


.drop_pic{
 width:80%;   
}

.required {
   border-style: solid;
   border-color: red;
}

.underline_td_main {
	background :  #714BFF;
    border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 120%;
    color : white;
    text-shadow: 2px 2px 5px black;
}

.underline_td_main_flagstat {
	padding: 3px 1px;
    font-size: 120%;
    color : #714BFF;
    text-shadow: 1px 1px 1px black;
}

.underline_td_sub {
	background : #A82EFF;
    border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 100%;
    color : white;
    text-shadow: 2px 2px 5px black;
}

.underline_td_item {
	background : #CE9AFF;
    border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 90%; 
    color : white;
    text-shadow: 2px 2px 5px black;       
}

.underline_td_item_content {
	background : #FFD1FF;
    border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 75%; 
    color : black;
    
}

.underline_table_item_content {
	background : #FFD1FF;
    padding: 3px 1px;
    font-size: 75%; 
    color : black;
    
}

.underline_td_item_content_total {
	background : #CE9AFF;
    border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 75%; 
    color : black;
    
}

table.content_stat tr td 
{ 
	font-size: 90%; 
    color : black; 
}

table.content_stat_total tr td 
{ 
	font-size: 100%; 
    color : black;
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


<fieldset>
<legend> PROJEK : 
<select  id="ID_PROJEK"  name="ID_PROJEK" onChange="if(checkProjek(this.value)==true){setTab(0); doDivAjaxCall$formname('div_by_modul','show_by_modul','');}" >	   
	   <option value = "" >SILA PILIH PROJEK</option>
		#foreach ( $pr in $listProjek )		
			#set ( $selected_projek = "" )
			#if($ID_PROJEK==$pr.ID_PROJEK)
			#set ( $selected_projek = "selected" )
			#end		
			<option $selected_projek value="$pr.ID_PROJEK" >
			$pr.PROJEK
			</option>
		#end
	   </select>
</legend>
<table border="0" width="100%">
<tr>
<td>

 
	   

<div id="TabbedPanelsEP" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="doDivAjaxCall$formname('div_by_modul','show_by_modul','ID_PROJEK='+$jquery('#ID_PROJEK').val());">Maklumat Progress</li>
    <li class="TabbedPanelsTab" tabindex="1" onclick="doDivAjaxCall$formname('div_main_statistik_modul','show_statistik_modul','ID_PROJEK='+$jquery('#ID_PROJEK').val());">Statistik By Modul</li>
  	<li class="TabbedPanelsTab" tabindex="2" onclick="doDivAjaxCall$formname('div_main_statistik_individu','show_statistik_individu','ID_PROJEK='+$jquery('#ID_PROJEK').val());">Statistik By Individu</li>
  
  </ul>
	  <div class="TabbedPanelsContentGroup">
		  <div class="TabbedPanelsContent">
		    <div id="div_by_modul" >
		    
		    </div>		   
		  </div>
		  <div class="TabbedPanelsContent">
		   <div id="div_main_statistik_modul" >
		    
		   </div>	   
		  </div>
		  <div class="TabbedPanelsContent">
		   <div id="div_main_statistik_individu" >
		    
		   </div>	   
		  </div>
		  
	  </div>
  </div>
  
 </td>
</tr>
</table>
</fieldset>

<a href="javascript:downloadBase64('16171','sukasuki')"><font color="blue"><u>TEST DOWNLOAD BASE64</u></font></a>

  <script>   
  
  
  function downloadBase64(id,nama_fail) {
    var url = "../servlet/ekptg.view.admin.DisplayBlobBase64?id="+id+"&nama_fail="+nama_fail;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
  
  
  
  $jquery(document).ready(function () {
	  doDivAjaxCall$formname('div_by_modul','show_by_modul','ID_PROJEK='+$jquery('#ID_PROJEK').val());	
	  doDivAjaxCall$formname('div_main_statistik_modul','blank_statistik','ID_PROJEK='+$jquery('#ID_PROJEK').val());
	  doDivAjaxCall$formname('div_main_statistik_individu','blank_statistik','ID_PROJEK='+$jquery('#ID_PROJEK').val());
	  
  });  
  setTab(0);  
  
  
  
  function displayStyle()
  {
	  var style_report = " <style> "+
  	" .required { "+
  	"    border-style: solid; "+
  	"    border-color: red; "+
  	" } "+
"  "+
  	" .underline_td_main { "+
  	" 	background :  #714BFF; "+
  	"     border-bottom: 1px solid #000; "+
  	"     padding: 3px 1px; "+
  	"     font-size: 120%; "+
  	"     color : white; "+
  	"     text-shadow: 2px 2px 5px black; "+
  	" } "+
"  "+
  	" .underline_td_main_flagstat { "+
  	" 	padding: 3px 1px; "+
  	"     font-size: 120%; "+
  	"     color : #714BFF; "+
  	"     text-shadow: 1px 1px 1px black; "+
  	" } "+
"  "+
  	" .underline_td_sub { "+
  	" 	background : #A82EFF; "+
  	"     border-bottom: 1px solid #000; "+
  	"     padding: 3px 1px; "+
  	"     font-size: 100%; "+
  	"     color : white; "+
  	"     text-shadow: 2px 2px 5px black; "+
  	" } "+
"  "+
  	" .underline_td_item { "+
  	" 	background : #CE9AFF; "+
  	"     border-bottom: 1px solid #000; "+
  	"     padding: 3px 1px; "+
  	"     font-size: 90%;  "+
  	"     color : white; "+
  	"     text-shadow: 2px 2px 5px black;    "+    
  	" } "+
"  "+
  	" .underline_td_item_content { "+
  	" 	background : #FFD1FF; "+
  	"     border-bottom: 1px solid #000; "+
  	"     padding: 3px 1px; "+
  	"     font-size: 75%;  "+
  	"     color : black;	  "+   	    
  	" } "+
"  "+
  	" .underline_td_item_content_total { "+
  	" 	background : #CE9AFF; "+
  	"     border-bottom: 1px solid #000; "+
  	"     padding: 3px 1px; "+
  	"     font-size: 75%;  "+
  	"     color : black;	 "+    	    
  	" } "+
"  "+
  	" table.content_stat tr td  "+
  	" {  "+
  	" 	font-size: 90%;  "+
  	"     color : black;  "+
  	" } "+
"  "+

" .underline_table_item_content { "+
" 	background : #FFD1FF; "+
"     padding: 3px 1px; "+
"     font-size: 100%;  "+
"     color : black; "+
"      "+
" } "+

  	" table.content_stat_total tr td  "+
  	" {  "+
  	" 	font-size: 100%;  "+
  	"     color : black; "+
  	" } "+

  " </style>";
	  return style_report;
  }
  function hantarEmel(divName,type,tab_index) {
	  var ID_PROJEK_ASAL = document.getElementById("ID_PROJEK").value;
	  var ID_PROJEKSelect = document.getElementById("ID_PROJEK");
	  var selectedTextProjek = ID_PROJEKSelect.options[ID_PROJEKSelect.selectedIndex].text;
	    
	  var subjek = "";
	  if(type=="modul")
	  {
		  subjek=''+selectedTextProjek+' - LAPORAN PROGRESS BY MODUL as '+getCurrentDate();	
	  }
	  else if(type=="individu")
	  {
		  subjek=''+selectedTextProjek+' - LAPORAN PROGRESS BY INDIVIDU as '+getCurrentDate();	
	  }
	  var printContents = document.getElementById(divName).innerHTML;
	  var footer = "<br><br>Nota : Emel ini dijana oleh Sistem EProgress dan tidak perlu dibalas."
	  //printContents = "xxxxxxxx";
	  if(tab_index==1)
	    {
			document.getElementById("textAreaStatModul").value = '<strong>'+subjek+'</strong><br><br>'+printContents+footer;		
		   	doDivAjaxCall$formname('div_main_statistik_modul','show_statistik_modul','ID_PROJEK='+ID_PROJEK_ASAL+'&EMEL=Y'+'&SUBJEK='+subjek);
		   	document.getElementById("textAreaStatModul").value = "";
	    }
	    else if(tab_index==2)
	    {
		    document.getElementById("textAreaStatIndividu").value = '<strong>'+subjek+'</strong><br><br>'+printContents+footer;
		    doDivAjaxCall$formname('div_main_statistik_individu','show_statistik_individu','ID_PROJEK='+ID_PROJEK_ASAL+'&EMEL=Y'+'&SUBJEK='+subjek);
		    document.getElementById("textAreaStatIndividu").value = "";
	    }
	  setTab(tab_index); 
  }
  
  function printDiv(divName,type,tab_index) {
	  
	    var ID_PROJEK_ASAL = document.getElementById("ID_PROJEK").value;
	    var ID_PROJEKSelect = document.getElementById("ID_PROJEK");
	    var selectedTextProjek = ID_PROJEKSelect.options[ID_PROJEKSelect.selectedIndex].text;
	    //alert("selectedTextProjek :"+selectedTextProjek);
	    
	    var originalContents = document.body.innerHTML;
	    //alert("originalContents :"+originalContents);
	    //$jquery("#"+divName+" :button").hide();
	    //document.getElementById(divName).setAttribute('class','page-break');
	    //$jquery("#"+divName+" :legend").hide();
	    //$jquery("#"+divName+" :fieldset").hide();
	    var printContents = document.getElementById(divName).innerHTML;
	    
	   
	    
	    var header = "";
	    
	    if(type=="modul")
	    {
	    	header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>'+selectedTextProjek+' - LAPORAN PROGRESS BY MODUL as '+getCurrentDate()+'</b></div><br></header>'	
	    }
	    else if(type=="individu")
	    {
	    	header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>'+selectedTextProjek+' - LAPORAN PROGRESS BY INDIVIDU as '+getCurrentDate()+'</b></div><br></header>'	
	    }
	    
		//var footer ="Your Footer";
	    //var header ="";
	    var footer ="";

	    //You can set height width over here
	    
	    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
	    popupWin.document.open();
	    popupWin.document.write(displayStyle()+'<html><body onload="window.print()">'+header+'<div class="page-break" >'+ printContents + '</div>'+footer+'</html>');
	    popupWin.document.close(); 
	    //document.body.innerHTML = originalContents;
	    
	    //alert(" tab_index :"+tab_index+" type :"+type);
	    
	    if(tab_index==1)
	    {
	   	doDivAjaxCall$formname('div_main_statistik_modul','show_statistik_modul','ID_PROJEK='+ID_PROJEK_ASAL);
	   	doDivAjaxCall$formname('div_main_statistik_individu','blank_statistik','ID_PROJEK='+ID_PROJEK_ASAL);
	    }
	    else if(tab_index==2)
	    {
	  	doDivAjaxCall$formname('div_main_statistik_individu','show_statistik_individu','ID_PROJEK='+ID_PROJEK_ASAL);
	  	doDivAjaxCall$formname('div_main_statistik_modul','blank_statistik','ID_PROJEK='+ID_PROJEK_ASAL);
	    }
	  	setTab(tab_index); 
	  	document.getElementById("ID_PROJEK").value = ID_PROJEK_ASAL;
	    return false;
	    
	    
	    
	    
	    /* 
	    document.body.innerHTML = style_report+header+printContents;
	    window.print();
	    document.body.innerHTML = originalContents;
	    */
	    
	}
  
  
  function checkProjek(id_projek)
  {
	  var bool_check = true;
	  /*
	  if(id_projek=="")
	  {
		  alert("Sila Pilih Projek!");
		  document.getElementById("ID_PROJEK").focus();
		  bool_check = false;
	  }*/
	  return bool_check;
  }
   
  function setTab(tab_index)
  {
  	//alert("tab_index :"+tab_index);
  	var tp = new Spry.Widget.TabbedPanels("TabbedPanelsEP");
  	
  	if(tab_index == null || tab_index == "")
  	{
  	//alert("tab_index 1:"+tab_index);	
  	//document.Fekptg_view_ppt_FrmSek8PermintaanUkur.selectedTabPelarasan.value = "0";
  	tp = new Spry.Widget.TabbedPanels("TabbedPanelsEP",{defaultTab:0});	
  	}
  	else
  	{
  	//alert("tab_index 2:"+tab_index);
  	//document.Fekptg_view_ppt_FrmSek8PermintaanUkur.selectedTabPelarasan.value = tab_index;	
  	
  		if(tab_index==0)
  		{
  		tp = new Spry.Widget.TabbedPanels("TabbedPanelsEP",{defaultTab:0});
  		}
  		else if(tab_index==1)
  		{
  		tp = new Spry.Widget.TabbedPanels("TabbedPanelsEP",{defaultTab:1});
  		}
  		else if(tab_index==2)
  		{
  		tp = new Spry.Widget.TabbedPanels("TabbedPanelsEP",{defaultTab:2});
  		}
  		
  	}
  	//alert("4");
  }
  
  function numberOnly(elmnt,content) {
		//if it is character, then remove it..
		if (isNaN(content)) {
			elmnt.value = RemoveNonNumeric(content);
			return;
		}
		
	}
  
  function RemoveNonNumeric( strString )
  {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
              strBuffer = strString.substr( intIndex, 1 );
              // Is this a number
              if( strValidCharacters.indexOf( strBuffer ) > -1 )
              {
                    strReturn += strBuffer;
              }
        }
        return strReturn;
  }
  
  function valEditMainmodul(id_mainmodul)
  {
	   var bool_check = true;
	   
	   if(document.getElementById("MAINMODUL_"+id_mainmodul).value=="")
	   {
		   alert("Masukkan keterangan main modul!");
		   document.getElementById("MAINMODUL_"+id_mainmodul).focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
  }
  
  function valEditSubmodul(id_submodul)
  {
	   var bool_check = true;
	   
	   if(document.getElementById("SUBMODUL_"+id_submodul).value=="")
	   {
		   alert("Masukkan keterangan sub modul!");
		   document.getElementById("SUBMODUL_"+id_submodul).focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
  }
  
  function valAddMainmodul()
  {
	   var bool_check = true;
	   
	   if(document.getElementById("MAINMODUL_").value=="")
	   {
		   alert("Masukkan keterangan main modul!");
		   document.getElementById("MAINMODUL_").focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
  }
  
  function valAddSubmodul(id_mainmodul)
  {
	   var bool_check = true;
	   
	   if(document.getElementById("SUBMODUL_"+id_mainmodul).value=="")
	   {
		   alert("Masukkan keterangan sub modul!");
		   document.getElementById("SUBMODUL_"+id_mainmodul).focus();
		   bool_check = false;
	   }
	   
	   return bool_check;
  }
  
  function valEditPenambahbaikan(id_penambahbaikan)
  {
	   var bool_check = true;
	   
	   if(document.getElementById("PENAMBAHBAIKAN_"+id_penambahbaikan).value=="")
	   {
		   alert("Masukkan keterangan penambahbaikkan!");
		   document.getElementById("PENAMBAHBAIKAN_"+id_penambahbaikan).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("ID_PIC_"+id_penambahbaikan).value=="")
	   {
		   alert("Masukkan person incharge!");
		   document.getElementById("ID_PIC_"+id_penambahbaikan).focus();
		   bool_check = false;
	   }
	   
	   
	   
	   else if(document.getElementById("TARIKH_MULA_"+id_penambahbaikan).value=="")
	   {
		   alert("Masukkan tarikh mula!");
		   document.getElementById("TARIKH_MULA_"+id_penambahbaikan).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("TARIKH_TARGET_SIAP_"+id_penambahbaikan).value=="")
	   {
		   alert("Masukkan tarikh selesai!");
		   document.getElementById("TARIKH_TARGET_SIAP_"+id_penambahbaikan).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("TARIKH_MULA_"+id_penambahbaikan).value!="" && document.getElementById("TARIKH_TARGET_SIAP_"+id_penambahbaikan).value!="")
	   {
		   if(validatedate(document.getElementById("TARIKH_MULA_"+id_penambahbaikan))==false)
		   {
			   alert("Invalid date format (dd/MM/yyyy)!");
			   document.getElementById("TARIKH_MULA_"+id_penambahbaikan).focus();
			   bool_check = false;
		   }		   
		   else if(validatedate(document.getElementById("TARIKH_TARGET_SIAP_"+id_penambahbaikan))==false)
		   {
			   alert("Invalid date format (dd/MM/yyyy)!");
			   document.getElementById("TARIKH_TARGET_SIAP_"+id_penambahbaikan).focus();
			   bool_check = false;
		   }
		   else
		   {
			   if(process(document.getElementById("TARIKH_MULA_"+id_penambahbaikan).value) > process(document.getElementById("TARIKH_TARGET_SIAP_"+id_penambahbaikan).value))
			   {
			   alert("Tarikh mula melepasi tarikh selesai!");
			   bool_check = false;
			   }
		   }
	   }
	   
	   return bool_check;
  }
  
  function valAddPenambahbaikan(id_submodul)
  {
	   var bool_check = true;
	   
	   if(document.getElementById("PENAMBAHBAIKAN_"+id_submodul).value=="")
	   {
		   alert("Masukkan keterangan penambahbaikkan!");
		   document.getElementById("PENAMBAHBAIKAN_"+id_submodul).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("ID_PIC_"+id_submodul).value=="")
	   {
		   alert("Masukkan person incharge!");
		   document.getElementById("ID_PIC_"+id_submodul).focus();
		   bool_check = false;
	   }
	   
	   
	   else if(document.getElementById("TARIKH_MULA_"+id_submodul).value=="")
	   {
		   alert("Masukkan tarikh mula!");
		   document.getElementById("TARIKH_MULA_"+id_submodul).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("TARIKH_TARGET_SIAP_"+id_submodul).value=="")
	   {
		   alert("Masukkan tarikh selesai!");
		   document.getElementById("TARIKH_TARGET_SIAP_"+id_submodul).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("TARIKH_MULA_"+id_submodul).value!="" && document.getElementById("TARIKH_TARGET_SIAP_"+id_submodul).value!="")
	   {
		   if(validatedate(document.getElementById("TARIKH_MULA_"+id_submodul))==false)
		   {
			   alert("Invalid date format (dd/MM/yyyy)!");
			   document.getElementById("TARIKH_MULA_"+id_submodul).focus();
			   bool_check = false;
		   }		   
		   else if(validatedate(document.getElementById("TARIKH_TARGET_SIAP_"+id_submodul))==false)
		   {
			   alert("Invalid date format (dd/MM/yyyy)!");
			   document.getElementById("TARIKH_TARGET_SIAP_"+id_submodul).focus();
			   bool_check = false;
		   }
		   else
		   {
			   if(process(document.getElementById("TARIKH_MULA_"+id_submodul).value) > process(document.getElementById("TARIKH_TARGET_SIAP_"+id_submodul).value))
			   {
			   alert("Tarikh mula melepasi tarikh selesai!");
			   bool_check = false;
			   }
		   }
	   }
	   
	   return bool_check;
  }
  
  function process(date){
	   var parts = date.split("/");
	   return new Date(parts[2], parts[1] - 1, parts[0]);
  }
  
  
  function validatedate(inputText)  
  {  
  var bool_check = true;
  //alert("s");
  var dateformat = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;  
  // Match the date format through regular expression  
  if(inputText.value.match(dateformat))  
  {  
  //document.form1.text1.focus();  
  //Test which seperator is used '/' or '-'  
  var opera1 = inputText.value.split('/');  
  var opera2 = inputText.value.split('-');  
  lopera1 = opera1.length;  
  lopera2 = opera2.length;  
  // Extract the string into month, date and year  
  if (lopera1>1)  
  {  
  var pdate = inputText.value.split('/');  
  }  
  else if (lopera2>1)  
  {  
  var pdate = inputText.value.split('-');  
  }  
  var dd = parseInt(pdate[0]);  
  var mm  = parseInt(pdate[1]);  
  var yy = parseInt(pdate[2]);  
  // Create list of days of a month [assume there is no leap year by default]  
  var ListofDays = [31,28,31,30,31,30,31,31,30,31,30,31];  
  if (mm==1 || mm>2)  
  {  
  if (dd>ListofDays[mm-1])  
  {  
  //alert('Invalid date format!');  
  bool_check = false;
  //return false;  
  }  
  }  
  if (mm==2)  
  {  
  var lyear = false;  
  if ( (!(yy % 4) && yy % 100) || !(yy % 400))   
  {  
  lyear = true;  
  }  
  if ((lyear==false) && (dd>=29))  
  {  
  alert('Invalid date format!');  
  return false;  
  }  
  if ((lyear==true) && (dd>29))  
  {  
  //alert('Invalid date format!');  
  //return false; 
  bool_check = false;
  }  
  }  
  }  
  else  
  {  
  //alert("Invalid date format!");  
  //document.form1.text1.focus();  
  //return false;
  bool_check = false;
  }  
  
  return bool_check;
  
  }  
  
  
  function getCurrentDate()
  {
	  var today = new Date();
	  var dd = today.getDate();
	  var mm = today.getMonth()+1; //January is 0!
	  var yyyy = today.getFullYear();

	  if(dd<10) {
	      dd='0'+dd
	  } 

	  if(mm<10) {
	      mm='0'+mm
	  } 

	  return dd+'/'+mm+'/'+yyyy;
  }
  

  </script>