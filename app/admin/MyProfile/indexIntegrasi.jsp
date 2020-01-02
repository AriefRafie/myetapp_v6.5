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
</style>

<div id="div_viewPenggunaHQ$internalType$USER_ID">

</div>

<script> 
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_viewPenggunaHQ$internalType$USER_ID','viewPenggunaINT','');
		  });
</script>


<script>

function pwdStrength() {
	
	$jquery('.password').pstrength();
}

function setPasswordClass(elem,value,internalType,user_id)
{
	var ch = document.getElementById("ps_call"+internalType+user_id).value;
	if(value!="")
	{
		elem.className = "password";
		if(ch=="no")
		{
			document.getElementById("ps_call"+internalType+user_id).value="yes";
			$jquery('.password').pstrength();
		}
	}
	else
	{
		document.getElementById("ps_call"+internalType+user_id).value="";
	}
}


function printDiv(divName,internalType,USER_ID) {
	var carianTerperinci = document.getElementById("carianTerperinci").value;
    var originalContents = document.body.innerHTML;
    $jquery("#"+divName+" :button").hide();
    
    
    var header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>Maklumat Pengguna</b></div><br></header>'
	
    
    var elementAT =  document.getElementById('div_carianAT_'+internalType+USER_ID);
    if (typeof(elementAT) != 'undefined' && elementAT != null)
    {
    	elementAT.style.display = "none";
    }
    else
    {
    	elementAT.style.display = "";
    }
    
    var elementPLA =  document.getElementById('div_carianPLA_'+internalType+USER_ID);
    if (typeof(elementPLA) != 'undefined' && elementPLA != null)
    {
    	elementPLA.style.display = "none";
    }
    else
    {
    	elementPLA.style.display = "";
    }
    
    
    var elementATCB =  document.getElementById('CB_AT_'+internalType+USER_ID);
    if (typeof(elementATCB) != 'undefined' && elementATCB != null)
    {
    	document.getElementById('show_cb_AT_'+internalType+USER_ID).style.display = "none";
    	if(elementATCB.checked==true)
    	{
    		var elementATPrint =  document.getElementById('div_ATforPrint_'+internalType+USER_ID);
		    if (typeof(elementATPrint) != 'undefined' && elementATPrint != null)
		    {
		    	elementATPrint.style.display = "";      	
		    }
		    else
		    {
		    	elementATPrint.style.display = "none";
		    } 
    	}
    }
    
    
    var elementPLACB =  document.getElementById('CB_PLA_'+internalType+USER_ID);
    if (typeof(elementPLACB) != 'undefined' && elementPLACB != null)
    {
    	document.getElementById('show_cb_PLA_'+internalType+USER_ID).style.display = "none";
    	if(elementPLACB.checked==true)
    	{
    		var elementPLAPrint =  document.getElementById('div_PLAforPrint_'+internalType+USER_ID);
		    if (typeof(elementPLAPrint) != 'undefined' && elementPLAPrint != null)
		    {
		    	elementPLAPrint.style.display = "";      	
		    }
		    else
		    {
		    	elementPLAPrint.style.display = "none";
		    } 
    	}
    }
    
    
   
    
   
    
    
    var printContents = document.getElementById(divName).innerHTML;
    var footer ="";
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()">'+header+'<div class="page-break" >'+ printContents + '</div>'+footer+'</html>');
    popupWin.document.close(); 
    document.body.innerHTML = originalContents;
    document.getElementById("carianTerperinci").value = carianTerperinci;
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

function setCheckLogin(internalType,user_id)
{
	//var allkp = "";
	var allkp = document.getElementById("NOKP1_"+internalType+user_id).value + document.getElementById("NOKP2_"+internalType+user_id).value + document.getElementById("NOKP3_"+internalType+user_id).value;
	//alert(" allkp :"+allkp);
	document.getElementById("USER_LOGIN_"+internalType+user_id).value = allkp;	
	if(allkp!="")
	{
		doDivAjaxCall$formname('div_CHECK_USER_LOGIN_'+internalType+user_id,'checkUSER_LOGIN','internalType='+internalType+'&USER_ID='+user_id+'&USER_LOGIN='+$jquery('#USER_LOGIN_'+internalType+user_id).val());
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

function checkCarianPLA(internalType,user_id)
{
	//alert("internalType : "+internalType+" USER_ID : "+USER_ID);
	var bool_check = true;
	
	//alert("TARIKH_MULA_PLA_"+internalType+""+user_id);
	
	var TM = document.getElementById("TARIKH_MULA_PLA_"+internalType+user_id).value;
	//alert("TM:"+TM+ " - "+"TARIKH_MULA_PLA_"+internalType+user_id);
	var TA = document.getElementById("TARIKH_AKHIR_PLA_"+internalType+user_id).value;	
	//alert("TA:"+TA);
	
	
	if(TM!="" && isValidDate_V3(TM)==false)
	{
	alert("Format Tarikh Mula Tidak Tepat! (dd/mm/yyyy)");
	document.getElementById("TARIKH_MULA_PLA_"+internalType+user_id).focus();
	bool_check = false;
	}
	else if(TA!="" && isValidDate_V3(TA)==false)
	{
	alert("Format Tarikh Akhir Tidak Tepat! (dd/mm/yyyy)");
	document.getElementById("TARIKH_AKHIR_PLA_"+internalType+user_id).focus();
	bool_check = false;
	}
	
	else if(TA!="" && TM!="" && convertTODateFormat(TM) > convertTODateFormat(TA))
	{
	alert("Pastikan Tarikh Mula Tidak Melebihi Tarikh Akhir");
	document.getElementById("TARIKH_MULA_PLA_"+internalType+user_id).focus();
	bool_check = false;
	}
		
	return bool_check;
}

function checkCarianAT(internalType,user_id)
{
	//alert("internalType : "+internalType+" USER_ID : "+USER_ID);
	var bool_check = true;
	
	//alert("TARIKH_MULA_AT_"+internalType+""+user_id);
	
	var TM = document.getElementById("TARIKH_MULA_AT_"+internalType+user_id).value;
	//alert("TM:"+TM+ " - "+"TARIKH_MULA_AT_"+internalType+user_id);
	var TA = document.getElementById("TARIKH_AKHIR_AT_"+internalType+user_id).value;	
	//alert("TA:"+TA);
	
	
	if(TM!="" && isValidDate_V3(TM)==false)
	{
	alert("Format Tarikh Mula Tidak Tepat! (dd/mm/yyyy)");
	document.getElementById("TARIKH_MULA_AT_"+internalType+user_id).focus();
	bool_check = false;
	}
	else if(TA!="" && isValidDate_V3(TA)==false)
	{
	alert("Format Tarikh Akhir Tidak Tepat! (dd/mm/yyyy)");
	document.getElementById("TARIKH_AKHIR_AT_"+internalType+user_id).focus();
	bool_check = false;
	}
	
	else if(TA!="" && TM!="" && convertTODateFormat(TM) > convertTODateFormat(TA))
	{
	alert("Pastikan Tarikh Mula Tidak Melebihi Tarikh Akhir");
	document.getElementById("TARIKH_MULA_AT_"+internalType+user_id).focus();
	bool_check = false;
	}
		
	return bool_check;
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

function doCheckUpdateRole(internalType,user_id) { 
	var group_length = document.getElementsByName("GROUP_CHECKLIST_"+internalType+user_id).length;
	var group_checkbox = document.getElementsByName("GROUP_CHECKLIST_"+internalType+user_id);
	var all_checklist = document.getElementById("ALL_CHECKLIST_"+internalType+user_id);
	//alert(all_checklist.checked);
	var m = 0;
	for (i = 0; i < group_length; i++) 
	{
		var checklist_checkbox = document.getElementsByName("CHECKLIST_"+internalType+user_id+group_checkbox[i].value);
		var checklist_length = document.getElementsByName("CHECKLIST_"+internalType+user_id+group_checkbox[i].value).length;
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


function doCheckAllRoleGroup(internalType,user_id,element) 
{ 
	if(element.checked==true)
	{
		var checklist_checkbox = document.getElementsByName("CHECKLIST_"+internalType+user_id+element.value);
		var checklist_length = document.getElementsByName("CHECKLIST_"+internalType+user_id+element.value).length;
		for (k = 0; k < checklist_length; k++) {
			checklist_checkbox[k].checked = true;
		}
	}
	else if(element.checked==false)
	{
		var checklist_checkbox = document.getElementsByName("CHECKLIST_"+internalType+user_id+element.value);
		var checklist_length = document.getElementsByName("CHECKLIST_"+internalType+user_id+element.value).length;
		for (k = 0; k < checklist_length; k++) {
			checklist_checkbox[k].checked = false;
		}
	}
	doCheckUpdateRole(internalType,user_id);
}

function doCheckAllRole(internalType,user_id) { 
	
	var all_checklist = document.getElementById("ALL_CHECKLIST_"+internalType+user_id);	
	var group_length = document.getElementsByName("GROUP_CHECKLIST_"+internalType+user_id).length;
	var group_checkbox = document.getElementsByName("GROUP_CHECKLIST_"+internalType+user_id);
	if(all_checklist.checked == true)
	{
		for (i = 0; i < group_length; i++) 
		{
			group_checkbox[i].checked=true;
			var checklist_checkbox = document.getElementsByName("CHECKLIST_"+internalType+user_id+group_checkbox[i].value);
			var checklist_length = document.getElementsByName("CHECKLIST_"+internalType+user_id+group_checkbox[i].value).length;
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
			var checklist_checkbox = document.getElementsByName("CHECKLIST_"+internalType+user_id+group_checkbox[i].value);
			var checklist_length = document.getElementsByName("CHECKLIST_"+internalType+user_id+group_checkbox[i].value).length;
			for (k = 0; k < checklist_length; k++) {
				checklist_checkbox[k].checked = false;
			}		
		}
	}
}
function valEditPenggunaOnline(internalType,user_id)
{
		var bool_check = true;
		var kat = document.getElementById("KATEGORI_"+internalType+user_id).value;
		
		if(document.getElementById("USER_LOGIN_"+internalType+user_id).value=="")
		{
		alert("Masukkan Id Pengguna (User Login)!");
		document.getElementById("USER_LOGIN_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(document.getElementById("USER_LOGIN_"+internalType+user_id).value!="" && document.getElementById("CHECK_USER_LOGIN_"+internalType+user_id).value=="false")
		{
		alert("User Login Telah Wujud!");
		document.getElementById("USER_LOGIN_"+internalType+user_id).focus();
		bool_check = false;
		}
		
		
		
		//open new checking		
		else if(user_id == "" && document.getElementById("NOKP1_"+internalType+user_id).value=="")
		{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById("NOKP1_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(user_id == "" && document.getElementById("NOKP2_"+internalType+user_id).value=="")
		{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById("NOKP2_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(user_id == "" && document.getElementById("NOKP3_"+internalType+user_id).value=="")
		{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById("NOKP3_"+internalType+user_id).focus();
		bool_check = false;
		}			   
		else if(user_id == "" && document.getElementById("NOKP1_"+internalType+user_id).value.length != document.getElementById("NOKP1_"+internalType+user_id).maxLength)
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NOKP1_"+internalType+user_id).focus();
		bool_check = false;				   
		}
		else if(user_id == "" && document.getElementById("CHECK_NOKP1_"+internalType+user_id).value=="false")
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NOKP1_"+internalType+user_id).focus();
		bool_check = false;				   
		}
		else if(user_id == "" && document.getElementById("NOKP2_"+internalType+user_id).value.length != document.getElementById("NOKP2_"+internalType+user_id).maxLength)
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NOKP2_"+internalType+user_id).focus();
		bool_check = false;				   
		}
		else if(user_id == "" && document.getElementById("NOKP3_"+internalType+user_id).value.length != document.getElementById("NOKP3_"+internalType+user_id).maxLength)
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NOKP3_"+internalType+user_id).focus();
		bool_check = false;				   
		}
		//close new cheking			
		
		
		
		else if(user_id=="" && document.getElementById("GET_USER_ID_EXIST_"+internalType+user_id).value == "" && document.getElementById("PASSWORD_"+internalType+user_id).value == "")
		{
		alert("Masukkan Katalaluan!");
		document.getElementById("PASSWORD_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(user_id=="" && document.getElementById("GET_USER_ID_EXIST_"+internalType+user_id).value == "" && document.getElementById("PASSWORD2_"+internalType+user_id).value == "")
		{
		alert("Masukkan Pengesahan Katalaluan!");
		document.getElementById("PASSWORD2_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(document.getElementById("PASSWORD2_"+internalType+user_id).value != "" && document.getElementById("PASSWORD_"+internalType+user_id).value == "")
		{
		alert("Masukkan Katalaluan!");
		document.getElementById("PASSWORD_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(document.getElementById("PASSWORD_"+internalType+user_id).value != "" && document.getElementById("PASSWORD2_"+internalType+user_id).value == "")
		{
		alert("Masukkan Pengesahan Katalaluan!");
		document.getElementById("PASSWORD2_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if((document.getElementById("PASSWORD_"+internalType+user_id).value != "" && 
		document.getElementById("PASSWORD2_"+internalType+user_id).value != "") && 
		(document.getElementById("PASSWORD_"+internalType+user_id).value != document.getElementById("PASSWORD2_"+internalType+user_id).value))
		{
		alert("Pastikan Katalaluan dan Pengesahan Katalaluan Adalah Sama!");
		document.getElementById("PASSWORD_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(document.getElementById("KATEGORI_"+internalType+user_id).value=="")
		{
		alert("Masukkan Kategori Pengguna!");
		document.getElementById("KATEGORI_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(document.getElementById("USER_NAME_"+internalType+user_id).value=="")
		{
		if(kat=="Individu")
		{
		alert("Masukkan Nama Penuh Pengguna!");
		}
		if(kat=="Syarikat")
		{
		alert("Masukkan Nama Penuh Syarikat!");
		}		   
		document.getElementById("USER_NAME_"+internalType+user_id).focus();
		bool_check = false;
		}   
		else if((kat=="" || kat=="Individu") && document.getElementById("NO_PENGENALAN1_"+internalType+user_id).value=="")
		{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById("NO_PENGENALAN1_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if((kat=="" || kat=="Individu") && document.getElementById("NO_PENGENALAN2_"+internalType+user_id).value=="")
		{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById("NO_PENGENALAN2_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if((kat=="" || kat=="Individu") && document.getElementById("NO_PENGENALAN3_"+internalType+user_id).value=="")
		{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById("NO_PENGENALAN3_"+internalType+user_id).focus();
		bool_check = false;
		}			   
		else if((kat=="" || kat=="Individu") && document.getElementById("NO_PENGENALAN1_"+internalType+user_id).value.length != document.getElementById("NO_PENGENALAN1_"+internalType+user_id).maxLength)
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NO_PENGENALAN1_"+internalType+user_id).focus();
		bool_check = false;				   
		}
		else if((kat=="" || kat=="Individu") && document.getElementById("CHECK_NO_PENGENALAN1_"+internalType+user_id).value=="false")
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NO_PENGENALAN1_"+internalType+user_id).focus();
		bool_check = false;				   
		}
		else if((kat=="" || kat=="Individu") && document.getElementById("NO_PENGENALAN2_"+internalType+user_id).value.length != document.getElementById("NO_PENGENALAN2_"+internalType+user_id).maxLength)
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NO_PENGENALAN2_"+internalType+user_id).focus();
		bool_check = false;				   
		}
		else if((kat=="" || kat=="Individu") && document.getElementById("NO_PENGENALAN3_"+internalType+user_id).value.length != document.getElementById("NO_PENGENALAN3_"+internalType+user_id).maxLength)
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NO_PENGENALAN3_"+internalType+user_id).focus();
		bool_check = false;				   
		} 	   
		else if((kat=="Syarikat") && document.getElementById("NO_PENGENALAN_"+internalType+user_id).value=="")
		{
		alert("Masukkan MyCOID Pengguna!");
		document.getElementById("NO_PENGENALAN_"+internalType+user_id).focus();
		bool_check = false;			  
		}	   
		else if(document.getElementById("EMEL_"+internalType+user_id).value=="")
		{
		alert("Masukkan Emel Pengguna!");
		document.getElementById("EMEL_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(document.getElementById("EMEL_"+internalType+user_id).value!="" && ValidateEmailFormat(document.getElementById("EMEL_"+internalType+user_id).value)==false)
		{
		alert("Masukkan Emel Pengguna Dengan Format Yang Sah!");
		document.getElementById("EMEL_"+internalType+user_id).focus();
		bool_check = false;
		}	   
		else if(document.getElementById("ALAMAT1_"+internalType+user_id).value=="")
		{
		alert("Masukkan Alamat Pengguna!");
		document.getElementById("ALAMAT1_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(document.getElementById("POSKOD_"+internalType+user_id).value=="")
		{
		alert("Masukkan Poskod Pengguna!");
		document.getElementById("POSKOD_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(document.getElementById("POSKOD_"+internalType+user_id).value.length != document.getElementById("POSKOD_"+internalType+user_id).maxLength)
		{
		alert("Format Poskod Tidak Tepat!");
		document.getElementById("POSKOD_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(document.getElementById("ID_NEGERI_"+internalType+user_id).value=="")
		{
		alert("Masukkan Negeri Alamat Pengguna!");
		document.getElementById("ID_NEGERI_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(document.getElementById("ID_BANDAR_"+internalType+user_id).value=="")
		{
		alert("Masukkan Bandar Alamat Pengguna!");
		document.getElementById("ID_BANDAR_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(document.getElementById("TARIKH_LAHIR_"+internalType+user_id).value!="" && isValidDate_V3(document.getElementById("TARIKH_LAHIR_"+internalType+user_id).value)==false)
		{
		alert("Format Tarikh lahir Tidak Tepat!");
		document.getElementById("TARIKH_LAHIR_"+internalType+user_id).focus();
		bool_check = false;
		}
		
		else if(document.getElementById("ROLE_MAIN_"+internalType+user_id).value=="")
		{
		   alert("Masukkan Peranan Utama Pengguna!");
		   document.getElementById("ROLE_MAIN_"+internalType+user_id).focus();
		   bool_check = false;
	    }
		
		return bool_check;
}

function valEditPenggunaINT(internalType,user_id)
{
	   var bool_check = true;
	   if(document.getElementById("USER_LOGIN_"+internalType+user_id).value=="")
	   {
		   alert("Masukkan Id Pengguna (User Login)!");
		   document.getElementById("USER_LOGIN_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("USER_LOGIN_"+internalType+user_id).value!="" && document.getElementById("CHECK_USER_LOGIN_"+internalType+user_id).value=="false")
	   {
		   alert("User Login Telah Wujud!");
		   document.getElementById("USER_LOGIN_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   
	   
	 //open new checking		
		else if(user_id == "" && document.getElementById("NOKP1_"+internalType+user_id).value=="")
		{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById("NOKP1_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(user_id == "" && document.getElementById("NOKP2_"+internalType+user_id).value=="")
		{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById("NOKP2_"+internalType+user_id).focus();
		bool_check = false;
		}
		else if(user_id == "" && document.getElementById("NOKP3_"+internalType+user_id).value=="")
		{
		alert("Masukkan MyID Pengguna Dengan Lengkap!");
		document.getElementById("NOKP3_"+internalType+user_id).focus();
		bool_check = false;
		}			   
		else if(user_id == "" && document.getElementById("NOKP1_"+internalType+user_id).value.length != document.getElementById("NOKP1_"+internalType+user_id).maxLength)
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NOKP1_"+internalType+user_id).focus();
		bool_check = false;				   
		}
		else if(user_id == "" && document.getElementById("CHECK_NOKP1_"+internalType+user_id).value=="false")
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NOKP1_"+internalType+user_id).focus();
		bool_check = false;				   
		}
		else if(user_id == "" && document.getElementById("NOKP2_"+internalType+user_id).value.length != document.getElementById("NOKP2_"+internalType+user_id).maxLength)
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NOKP2_"+internalType+user_id).focus();
		bool_check = false;				   
		}
		else if(user_id == "" && document.getElementById("NOKP3_"+internalType+user_id).value.length != document.getElementById("NOKP3_"+internalType+user_id).maxLength)
		{
		alert("Format MyID Tidak Tepat!");
		document.getElementById("NOKP3_"+internalType+user_id).focus();
		bool_check = false;				   
		}
		//close new cheking		
	
		
		
	   	   
	   else if(user_id=="" && document.getElementById("GET_USER_ID_EXIST_"+internalType+user_id).value == "" && document.getElementById("PASSWORD_"+internalType+user_id).value == "")
	   {
		   alert("Masukkan Katalaluan!");
		   document.getElementById("PASSWORD_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   else if(user_id=="" && document.getElementById("GET_USER_ID_EXIST_"+internalType+user_id).value == "" && document.getElementById("PASSWORD2_"+internalType+user_id).value == "")
	   {
		   alert("Masukkan Pengesahan Katalaluan!");
		   document.getElementById("PASSWORD2_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("PASSWORD2_"+internalType+user_id).value != "" && document.getElementById("PASSWORD_"+internalType+user_id).value == "")
	   {
		   alert("Masukkan Katalaluan!");
		   document.getElementById("PASSWORD_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("PASSWORD_"+internalType+user_id).value != "" && document.getElementById("PASSWORD2_"+internalType+user_id).value == "")
	   {
		   alert("Masukkan Pengesahan Katalaluan!");
		   document.getElementById("PASSWORD2_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   else if((document.getElementById("PASSWORD_"+internalType+user_id).value != "" && 
			   document.getElementById("PASSWORD2_"+internalType+user_id).value != "") && 
			   (document.getElementById("PASSWORD_"+internalType+user_id).value != document.getElementById("PASSWORD2_"+internalType+user_id).value))
	   {
		   alert("Pastikan Katalaluan dan Pengesahan Katalaluan Adalah Sama!");
		   document.getElementById("PASSWORD_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("USER_NAME_"+internalType+user_id).value=="")
	   {
		   alert("Masukkan Nama Penuh Pengguna!");
		   document.getElementById("USER_NAME_"+internalType+user_id).focus();
		   bool_check = false;
	   }	   
	   else if(document.getElementById("EMEL_"+internalType+user_id).value=="")
	   {
		   alert("Masukkan Emel Pengguna!");
		   document.getElementById("EMEL_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("EMEL_"+internalType+user_id).value!="" && ValidateEmailFormat(document.getElementById("EMEL_"+internalType+user_id).value)==false)
	   {
		   alert("Masukkan Emel Pengguna Dengan Format Yang Sah!");
		   document.getElementById("EMEL_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   
	   else if(document.getElementById("ID_JENISPEJABAT_"+internalType+user_id).value=="")
	   {
		   alert("Masukkan Jenis Pejabat!");
		   document.getElementById("ID_JENISPEJABAT_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("ID_JENISPEJABAT_"+internalType+user_id).value=="16111" &&
			   document.getElementById("ID_NEGERI_"+internalType+user_id).value=="")
	   {
		   alert("Masukkan Negeri!");
		   document.getElementById("ID_NEGERI_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("ID_JENISPEJABAT_"+internalType+user_id).value=="16111" &&
			   document.getElementById("ID_DAERAH_"+internalType+user_id).value=="")
	   {
		   alert("Masukkan Daerah!");
		   document.getElementById("ID_DAERAH_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   else if(document.getElementById("ID_JENISPEJABAT_"+internalType+user_id).value=="16111" &&
			   document.getElementById("ID_PEJABAT_"+internalType+user_id).value=="")
	   {
		   alert("Masukkan Pejabat!");
		   document.getElementById("ID_PEJABAT_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   
	   else if(document.getElementById("ROLE_MAIN_"+internalType+user_id).value=="")
	   {
		   alert("Masukkan Peranan Utama Pengguna!");
		   document.getElementById("ROLE_MAIN_"+internalType+user_id).focus();
		   bool_check = false;
	   }
	   
	   alert('bool_check : '+bool_check);
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


function sendValueJenisPengguna(elem, value, nextField)
{
	document.getElementById(nextField).value = value;
}
function removeSpaces(elem, valuekey) {//remove special char & space
	 elem.value = valuekey.replace(/[^A-Z0-9]+/i, '');
}
</script>
