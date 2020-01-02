
function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{
alert("test");
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



function textarea_kerosakan1()
{

	var kerosakan_temp1_length=0;
	
	if(document.${formName}.kerosakan_temp1 != null)
	{
	if(document.${formName}.kerosakan_temp1.length>0)
	{
	kerosakan_temp1_length = document.${formName}.kerosakan_temp1.length;
	}
	else
	{
	kerosakan_temp1_length = 1;
	}
	}

    if(kerosakan_temp1_length > 1 && document.${formName}.maklumbalas.length > 1 )
	{
	for (t = 0; t < kerosakan_temp1_length; t++)
    {	
    document.${formName}.kerosakan_temp1[t].value = document.${formName}.maklumbalas[t].value;
	document.${formName}.kerosakan_temp2[t].value = document.${formName}.maklumbalasAward[t].value;
    }
	}
	else if(kerosakan_temp1_length > 1 && document.${formName}.maklumbalas.length < 1 )
	{
	for (t = 0; t < kerosakan_temp1_length; t++)
    {	
    document.${formName}.kerosakan_temp1[0].value = document.${formName}.maklumbalas.value;
	document.${formName}.kerosakan_temp2[0].value = document.${formName}.maklumbalasAward.value;
    }
	}
	else if(kerosakan_temp1_length == 1)
	{
	document.${formName}.kerosakan_temp1.value = document.${formName}.maklumbalas.value;
	document.${formName}.kerosakan_temp2.value = document.${formName}.maklumbalasAward.value;
	}
	
	
	
	
	
		
	    var total_nilai = 0;
        if (document.${formName}.maklumbalasAward.length == null)
		{
				if(document.${formName}.maklumbalasAward.value != "")
				{				   
				   total_nilai = total_nilai + parseFloat(document.${formName}.maklumbalasAward.value) ;					   	   
				}
				else
				{
				   total_nilai = total_nilai+0;
				}		
        } 
		else {
            for (i = 0; i < document.${formName}.maklumbalasAward.length; i++)
			{
               if(document.${formName}.maklumbalasAward[i].value != "")
				{			  
				   total_nilai = total_nilai + parseFloat(document.${formName}.maklumbalasAward[i].value) ;			   	   	   
				}
				else
				{
				   total_nilai = total_nilai + 0;
				}
            }
        }		
		
		
		
		
		
}

function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}

function validateModal_X(elmnt,content) {
 
	
	if(content != "")
	{
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	}
	else
	{
	elmnt.value ="";
	return;
	}
	
}

function checking_validation(field,point,mandatory,value_field,jenis_field){	

   	var lepas_or_xlepas = 1;
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	      DateField.select();		
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	 
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	
	   }
	   else
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini");
	
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > ");
	

	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	 $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >Sila masukkan tarikh "+value_field+" dengan betul");

	   
	   }
	   
	   }
	   
	 
	   if(jenis_field == "normal")
	   {
	   
	  	
		
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }  
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
		
	   }
	   else
	   {
	
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
	   }
	   
	   	   
	   }
	   
	   
	   	   if(jenis_field == "radio")


	   {
	  
	  
	    var r = 0;
		if(field.length > 1)
		{     
			  for (i = 0; i < field.length; i++)
			  {
			  if (field[i].checked == true)
			  {	 
			  r++
			  }
			  }  
		}
		else
		{
		if (field.checked == true)
		{	 
		r++;
		}	 	
		}  
	   
	     
	   if((r == 0) && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	  
	   if(lepas_or_xlepas == "2")
	   {
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");

	   }
	   else
	   {
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");

	   }
	   
	   	   
	   }
	   
	   
	   
	 
	   
	
}


function textarea_kerosakan(tambahtolak,jenis,index_tolak)
{

var kerosakan_temp1_length=0;

if(document.${formName}.kerosakan_temp1 != null)
{

if(document.${formName}.kerosakan_temp1.length>0)
{
kerosakan_temp1_length = document.${formName}.kerosakan_temp1.length;
}
else
{
kerosakan_temp1_length = 1;
}


}

var code_temp = "";

//alert(document.${formName}.kerosakan_temp1);

if(document.${formName}.maklumbalas!=null)
{


if(document.${formName}.maklumbalas.length > 1)
{
 for (i = 0; i < document.${formName}.maklumbalas.length; i++)
 {
 code_temp += "<tr><td><input type='hidden' id='temp_temp' name='temp_temp' value= '"+document.${formName}.maklumbalas[i].value+"'></td></tr>";
 code_temp += "<tr><td><input type='hidden' id='temp_temp1' name='temp_temp1' value= '"+document.${formName}.maklumbalasAward[i].value+"'></td></tr>"; 
 } 
}
else
{
code_temp += "<tr><td><input type='hidden' id='temp_temp' name='temp_temp' value= '"+document.${formName}.maklumbalas.value+"'></td></tr>";
code_temp += "<tr><td><input type='hidden' id='temp_temp1' name='temp_temp1' value= '"+document.${formName}.maklumbalasAward.value+"'></td></tr>"; 
}
}

$jquery("#kerosakan_temp").html(""+code_temp); 
var codes = "";

if(jenis == "umum")
{
if(kerosakan_temp1_length>0)
{
tt = kerosakan_temp1_length;
}
else
{
tt = 1;
}
}

//alert("sebelum::"+tt);

if(jenis == "tambah")
{
tt = tt + parseInt(tambahtolak);


}
if(jenis == "tolak")
{
tt = tt + parseInt(tambahtolak);
}



//alert("selepas::"+tt);
  for (i = 0; i < tt; i++)
  {	
  if(tt==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.temp_temp[1].value
	temp_amaunt = document.${formName}.temp_temp1[1].value
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.temp_temp[0].value
	temp_amaunt = document.${formName}.temp_temp1[0].value	
    } 	
	}		
   
	codes += "<table width='100%'><tr>"+
	"<td  >   "+
	 " <textarea name=\"maklumbalas\" id=\"maklumbalas\"  cols=\"40\" rows=\"4\""+          
            " onBlur=\"check_length(this,'400','maklumbalas_check','maklumbalas_num','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+  
         " onKeyup=\"check_length(this,'400','maklumbalas_check','maklumbalas_num','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+
         " onKeydown=\"check_length(this,'400','maklumbalas_check','maklumbalas_num','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+   
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"maklumbalas_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"maklumbalas_num\" id=\"maklumbalas_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"maklumbalas_check\" class=\"alert_msg\" ></div> "+
	    
	" </td>"+
		
	"</tr><tr>"+	
	
	"<td align='left' valign='top' > "+
	     "JUMLAH RM :  <input name=\"maklumbalasAward\" id=\"maklumbalasAward\" style=\"text-align:right\"  size=\"15\" maxlength=\"15\" "+          
    "onBlur=\"validateTarikh(this,this.value);validateModal_X(this,this.value);"+
	"checking_validation(this,'maklumbalasAward_check','no','amaun pampasan kerosakan','normal');textarea_kerosakan1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);"+
	"checking_validation(this,'maklumbalasAward_check','no','amaun pampasan kerosakan','normal');textarea_kerosakan1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);"+
		 "checking_validation(this,'maklumbalasAward_check','no','amaun pampasan kerosakan','normal');textarea_kerosakan1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"maklumbalasAward_check\" class=\"alert_msg\" ></span> ";	 	
		 codes +=  "#if($readmode == 'edit' ) ";
	     codes += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+'  onkeypress=\"textarea_kerosakan(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_kerosakan(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kerosakan'> "+
	      " </span>"; 	
	     if(tt>1) {      
	     codes += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_kerosakan(-1,'tolak','');textarea_kerosakan1()\"  onClick=\"textarea_kerosakan(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kerosakan' > "+
	"</span> ";
	}
	codes +="#end";		 
	codes+=" </td>"+	
	"</tr></table>";
	
	
	}
	else
	{
	
	var temp_value = "";
	var temp_amaunt = "";
	
	if(tt==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.temp_temp.value
	temp_amaunt = document.${formName}.temp_temp1.value
	}	
	else if(tt>2 && i!=(tt-1) && jenis == "tambah")
	{
			
	temp_value = document.${formName}.temp_temp[i].value
	temp_amaunt = document.${formName}.temp_temp1[i].value
//	alert("111:::"+tt);
	}	
	else if(tt>1 && i!=(tt+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.temp_temp[parseInt(index_tolak)+1].value
	temp_amaunt = document.${formName}.temp_temp1[parseInt(index_tolak)+1].value
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.temp_temp[i].value
	temp_amaunt = document.${formName}.temp_temp1[i].value	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.temp_temp[i+1].value	
	temp_amaunt = document.${formName}.temp_temp1[i+1].value
   }	

	}
	else if(tt==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.temp_temp[1].value;
	temp_amaunt = document.${formName}.temp_temp1[1].value;
	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.temp_temp[0].value;
	temp_amaunt = document.${formName}.temp_temp1[0].value;
	
   }
   }		
	
	
	
	
	codes += "<table width='100%'><tr>"+
	"<td >"+
	     "<textarea name=\"maklumbalas\" id=\"maklumbalas\" cols=\"40\"   rows=\"4\""+          
         "onBlur=\"check_length(this,'400','maklumbalas_check"+i+"','maklumbalas_num"+i+"','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,400,'maklumbalas_check"+i+"','maklumbalas_num"+i+"','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'400','maklumbalas_check"+i+"','maklumbalas_num"+i+"','normal','no','ulasan kerosakan');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"maklumbalas_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"maklumbalas_num"+i+"\" id=\"maklumbalas_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"maklumbalas_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td align='left' valign='top' > "+
	     "JUMLAH RM : <input name=\"maklumbalasAward\" id=\"maklumbalasAward\" size=\"15\" style=\"text-align:right\"  maxlength=\"15\" "+          
         "onBlur=\"validateTarikh(this,this.value);checking_validation(this,'maklumbalasAward_check"+i+"','no','amaun pampasan kerosakan','normal');validateModal_X(this,this.value);textarea_kerosakan1()\" "+  
         "onKeyup=\"validateTarikh(this,this.value);checking_validation(this,'maklumbalasAward_check"+i+"','no','amaun pampasan kerosakan','normal');textarea_kerosakan1()\" "+
         "onKeydown=\"validateTarikh(this,this.value);checking_validation(this,'maklumbalasAward_check"+i+"','no','amaun pampasan kerosakan','normal');textarea_kerosakan1()\" "+    
         " $readonlymode class = \"$disabledmode\" value = '"+temp_amaunt+"' /> "+			 
         "<span id=\"maklumbalasAward_check"+i+"\" class=\"alert_msg\" ></span> ";		
		 codes +=  "#if($readmode == 'edit' ) ";
		 
    if(tt>1 && tt==(i+1)) {  	
	codes += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_kerosakan(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_kerosakan(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kerosakan'> "+
	" </span>"; 
	}
	if(tt>1) {      
	codes += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_kerosakan(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_kerosakan(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kerosakan' > "+
	"</span> ";
	}
	codes +="#end";		 
	codes +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
//	alert("CODES ::"+codes);
	
	$jquery("#kerosakan").html(codes);
	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(kerosakan_temp1_length > 1 && document.${formName}.maklumbalas.length > 1 )
	{
	for (t = 0; t < kerosakan_temp1_length; t++)
    {	
    document.${formName}.maklumbalas[t].value = document.${formName}.kerosakan_temp1[t].value;
	document.${formName}.maklumbalasAward[t].value = document.${formName}.kerosakan_temp2[t].value;
    }
	}
	else if(kerosakan_temp1_length > 1 && document.${formName}.maklumbalas.length < 1 )
	{
	for (t = 0; t < kerosakan_temp1_length; t++)
    {	
    document.${formName}.maklumbalas.value = document.${formName}.kerosakan_temp1[0].value;
	document.${formName}.maklumbalasAward.value = document.${formName}.kerosakan_temp2[0].value;
    }
	}
	else if(kerosakan_temp1_length == 1)
	{
	document.${formName}.maklumbalas.value = document.${formName}.kerosakan_temp1.value;
	document.${formName}.maklumbalasAward.value = document.${formName}.kerosakan_temp2.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(kerosakan_temp1_length > 1)
	{
	for (t = 0; t < kerosakan_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.kerosakan_temp1[index_tolak].value = "";	
	var element = document.${formName}.kerosakan_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	document.${formName}.kerosakan_temp2[index_tolak].value = "";	
	var element2 = document.${formName}.kerosakan_temp2[index_tolak];
    element2.parentNode.removeChild(element2);
	}
    }	
	}
	else if(kerosakan_temp1_length == 1)
	{	
	 document.${formName}.kerosakan_temp1.value = "";			
	 var element = document.${formName}.kerosakan_temp1;
     element.parentNode.removeChild(element);	 
	 document.${formName}.kerosakan_temp2.value = "";			
	 var element2 = document.${formName}.kerosakan_temp2;
     element2.parentNode.removeChild(element2);	
	}
	}			
	
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < tt; i++)
    {		
    if(tt==1)
    {	
	check_length(document.${formName}.maklumbalas,'400','maklumbalas_check','maklumbalas_num','normal','no','ulasan kerosakan');	
	}
	else
	{	
	check_length(document.${formName}.maklumbalas[i],'400','maklumbalas_check'+i,'maklumbalas_num'+i,'normal','no','ulasan kerosakan');
	}		 
	}	
	}
	
	
}






function doCheckAll1(){    
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
            }


        }
    } else {
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = false;
            }
        }
    }
}
function doUpdateCheckAll1(){  
var c = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all1.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1.checked = true;
	  }
	  
}

function ReadOnly_CheckBox(v)
{
return false;
}

		
		