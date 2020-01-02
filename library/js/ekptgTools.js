/*
Functions for input validation and many others.
Just add your function secukup rasa disini. :)
*/


// readonly checkbox


//remove spacing 19042010
function removeSpaces(string) {
 return string.split(' ').join('');
}

function ReadOnlyCheckBox(value)
{
	if(value.checked == true)
	{
		value.checked = false;
	}
}


function cetakPDF(reportClass) {
    var url = "../servlet/"+reportClass;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function validatePoskod(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function validateIC(elmnt,content,nextElementID) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
	//goto next column if maximum length reach
	if (content.length == elmnt.maxLength) goTo(nextElementID);
}


function validateIC(e,elmnt,content,nextElementID) {
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
	if (content.length == elmnt.maxLength) goTo(nextElementID);
}

function getAgeByIC(elmnt,content,umurField) {
	if (content.length == elmnt.maxLength) {
	var year = 0;
		
		if(content.charAt(0)== 0)
		{
		year = parseInt(content.charAt(1));
		}
		else
		{	
		year = parseInt(content.substring(0,2));
		}
    //	year = content.charAt(0)<2)
		today = new Date();
 		yearStr = today.getFullYear();
 		//assuming 100101 is year 2010. kalau dob utk year 2011 (110101) lak caner ?? ok.kita
 		//tengok balik logik nie. :) adios.
 		
 		
 		if (year < 20) {
 		
 			year = year + 2000;
 			
 		} else {
 			year = year + 1900
 		}
 	
 		
 		year = parseInt(yearStr) - year;
 		
 		if(year > 0){ 		
		returnObjById(umurField).value = year ;
 		}
 		else if(year == 0){ 		
 	    returnObjById(umurField).value = 1 ;
 	 	}
 		else{
 		returnObjById(umurField).value = 0 ;	 			
 		}
	}
}


function getStatusByIC(elmnt,content,umurField) {
	if (content.length == elmnt.maxLength) {
	var year = 0;
		
		if(content.charAt(0)== 0)
		{
		year = parseInt(content.charAt(1));
		}
		else
		{	
		year = parseInt(content.substring(0,2));
		}
    //	year = content.charAt(0)<2)
		today = new Date();
 		yearStr = today.getFullYear();
 		//assuming 100101 is year 2010. kalau dob utk year 2011 (110101) lak caner ?? ok.kita
 		//tengok balik logik nie. :) adios.
 		
 		
 		if (year < 20) {
 		
 			year = year + 2000;
 			
 		} else {
 			year = year + 1900
 		}
 	
 		
 		year = parseInt(yearStr) - year;
 		
 		
 	
 		if(year >= 18 ){ 		
		returnObjById(umurField).value = 1 ;
 		}
 		else if(year < 18 && year > 0){ 		
 	    returnObjById(umurField).value = 2 ;
 	 	}
 		else if(year == 0){ 		
 	 	    returnObjById(umurField).value = 2 ;
 	 	 	}
 		else{
 		returnObjById(umurField).value = "" ;	 			
 		}
	}
}


/*
function getStatusByAge(elmnt,content,umurField) { 	
 		
 		year = parseInt(content);
 		
 		if(year > 18 ){ 		
		returnObjById(umurField).value = 1 ;
 		}
 		else if(year <= 18 && year > 0){ 		
 	    returnObjById(umurField).value = 2 ;
 	 	}
 		else{
 		returnObjById(umurField).value = "" ;	 			
 		}
	
}
*/

function getStatusByAge(elmnt,content,umurField) { 	
		
		year = parseInt(content);
		
		if(year >= 18 ){ 		
	returnObjById(umurField).value = 1 ;
		}
		else if(year < 18 && year > 0){ 		
	    returnObjById(umurField).value = 2 ;
	 	}
		else{
		returnObjById(umurField).value = "" ;	 			
		}

}

function getAgebyDob(elmnt,content,umurField) { 
if(content != "" && content.substring(6,10).length == 4 && parseInt(content.substring(6,10))>1000)
{
	var umur = 0;	
	today = new Date();
	yearStr = today.getFullYear();		
	year = parseInt(content.substring(6,10));
	
	umur = parseInt(yearStr) - year; 
	
	if(umur > 0 )
	{ 		
	    returnObjById(umurField).value = umur ;
	}
	else if(umur == 0 )
	{ 		
	    returnObjById(umurField).value = 1 ;
	}
	else
	{
		returnObjById(umurField).value = "" ;	 			
	}
    }

}




function getDOBByIC(elmnt,content,DOBField) {
	if (content.length == elmnt.maxLength) {
		year = content.substring(0,2);
 		if (year <=10) {
 			year = parseInt(year) + 2000;
 		} else {
 			year = parseInt(year) + 1900
 		}
		month = content.substring(2,4);
		day = content.substring(4,6);
		returnObjById(DOBField).value = day + '/' + month + '/' + year ;
	}
}

function getGenderByIC(elmnt,content,genderField) {
	if (content.length == elmnt.maxLength) {
		lastDigit = content.substring(3,4);
		if (lastDigit%2 == 0) {
			gender = "2"; //female
		} else {
			gender = "1"; //male
		}
		//alert(gender);
		returnObjById(genderField).selectedIndex=gender;
	}
}


function pilih_jenis_luas(jenisluas,tr_luas,tr_luas_b,l1,l2,l3,l1_field,l2_field,l3_field,luasawal,luasconvert,meterhektar)
{
	
	
var val = document.getElementById(jenisluas).value;

document.getElementById(l1_field).value= "";
document.getElementById(l2_field).value= "";
document.getElementById(l3_field).value= "";



document.getElementById(luasawal).value= "";
document.getElementById(luasconvert).value= "";
document.getElementById(meterhektar).value= "";



document.getElementById(tr_luas).style.display="none";
document.getElementById(tr_luas_b).style.display="none";
document.getElementById(l1).style.display="none";  
document.getElementById(l2).style.display="none";  
document.getElementById(l3).style.display="none";  



if(val == 9)
{

	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
	

}

else if(val == 7)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 4)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="";  
}

else if(val == 2)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 5)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 3)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 6)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 1)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 8)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="";  
}


else
{
    document.getElementById(tr_luas).style.display="none";
	document.getElementById(tr_luas_b).style.display="none";
	document.getElementById(l1).style.display="none";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}



}










function ConvertLuasHarta(jenisluas,luasawal,luasconvert,meterhektar,luas1,luas2,luas3,kategory)
{
	//alert("test");
	//alert(" jenisluas : "+document.getElementById(jenisluas).value+" kategory : "+document.getElementById(kategory).value);
	
    if (document.getElementById(jenisluas).value=="2"){
        var a = document.getElementById(luas1).value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		} 
		else
		{   	   
        if(document.getElementById(kategory).value=="2" || document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="5") 
        {       
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"H";		
        }
        else if(document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4"  || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7"  || document.getElementById(kategory).value=="8")        
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"H";
        } 
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}
		     
		
	}
    


	
  else if (document.getElementById(jenisluas).value=="5"){
        var a = document.getElementById(luas1).value;
       //	var num1 = a * 9.290304; 
		//var num = a * 0.09290304;
		
		var num = parseFloat(a) * 0.09290304; // kaki persegi to meter persegi
		var num1 = parseFloat(a) * 0.000009290304; // kaki persegi to hektar    
		
		
		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		} 
		else
		{              
        if(document.getElementById(kategory).value=="2") 
        {       
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"K";		
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6" || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")        
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"K";
        } 
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}     
		}
	}
	
	
	
	 else if (document.getElementById(jenisluas).value=="1"){
        var a = document.getElementById(luas1).value;
     //   var num1 = a * 100; 
	 //	var num = a * 1000000;  
	 
	    var num = parseFloat(a) * 1000000;  // kilo to meter      
        var num1 = parseFloat(a) * 100; // kilo meter to hektar     
		
		
        if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		} 
		else
		{          
        if(document.getElementById(kategory).value=="2") 
        {       
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"KM";		
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")        
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"KM";
        } 
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}
		     
		
	}



 else if (document.getElementById(jenisluas).value=="3"){
        var a = document.getElementById(luas1).value;
    //    var num = a * 1; 
	//	var num1 = a * 0.0001; 
	
	    var num = parseFloat(a) * 1; //meter persegi to meter persegi
		var num1 = parseFloat(a) * 0.0001;  //meter persegi to hektar  
        
		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		} 
		else
		{         
        if(document.getElementById(kategory).value=="2") 
        {       
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"M";		
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")        
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"M";
        } 
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}
		     
		
	}



else if (document.getElementById(jenisluas).value=="9"){
        var a = document.getElementById(luas1).value;
       var num = parseFloat(a) * 1; 
		var num1 = parseFloat(a) * 1;   
        
		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		} 
		else
		{         
        if(document.getElementById(kategory).value=="2") 
        {       
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"BN";		
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")        
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"BN";
        } 
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}
		     
		
	}




else if (document.getElementById(jenisluas).value=="6"){
        var a = document.getElementById(luas1).value;
        var num = parseFloat(a) * 1; 
		var num1 = parseFloat(a) * 1;        
        
		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		} 
		else
		{         
        if(document.getElementById(kategory).value=="2") 
        {       
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"P";		
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")        
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"P";
        } 
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}
		     
		
	}

//formula bawah ni hentam dulu
else if (document.getElementById(jenisluas).value=="7"){
        var a = document.getElementById(luas1).value;
        var b = document.getElementById(luas2).value;
       // var num = a * b; 
	//var num1 = a * 0.0001; 
	
		var num = (parseFloat(a) + (parseFloat(b)/1000))*4046.86; 
		var num1 =  (parseFloat(a) + (parseFloat(b)/1000))*0.404686;     
        
		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		} 
        else if(b=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas2);
		return
		} 
		else
		{         
        if(document.getElementById(kategory).value=="2") 
        {       
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"E"+b+"D";		
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")        
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"E"+b+"D";
        } 
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}
		     
		
	}


else if (document.getElementById(jenisluas).value=="8"){
        var a = document.getElementById(luas1).value;
        var b = document.getElementById(luas2).value;
        var c = document.getElementById(luas3).value;
     //   var num = a * b * c; 
	 //	var num1 = a * 0.0001; 
		
		var num = (parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976))*0.711111*4046.86; 
		var num1 = ((parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976)))*0.711111*0.404686;
        
		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		} 
        else if(b=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas2);
		return
		}
        else if(c=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas3);
		return
		} 
		else
		{         
        if(document.getElementById(kategory).value=="2") 
        {       
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"R"+b+"J"+c+"K";		
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")        
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"R"+b+"J"+c+"K";
        } 
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}
		     
		
	}
    
    
    //formula bawah ni hentam dulu
else if (document.getElementById(jenisluas).value=="4"){
        var a = document.getElementById(luas1).value;
        var b = document.getElementById(luas2).value;
        var c = document.getElementById(luas3).value;
     //   var num = a * b * c; 
	//	var num1 = a * 0.0001; 
	var num = (parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160))*4046.86; 
		var num1 = ((parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160)))*0.404686;  
        
		if(a=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas1);
		return
		} 
        else if(b=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas2);
		return
		}
        else if(c=="")
		{
		alert("Sila masukkan jumlah luas asal!")
		goTo(luas3);
		return
		} 
		else
		{         
        if(document.getElementById(kategory).value=="2") 
        {       
		document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.getElementById(meterhektar).value= "Hektar";
		document.getElementById(luasawal).value= a+"E"+b+"R"+c+"P";		
        }
        else if(document.getElementById(kategory).value=="1" || document.getElementById(kategory).value=="3" || document.getElementById(kategory).value=="4" || document.getElementById(kategory).value=="5" || document.getElementById(kategory).value=="6"  || document.getElementById(kategory).value=="7" || document.getElementById(kategory).value=="8")        
        {
        document.getElementById(luasconvert).value=num.toFixed(4);
	    document.getElementById(meterhektar).value= "Meter Persegi";
	    document.getElementById(luasawal).value= a+"E"+b+"R"+c+"P";
        } 
		else
		{
		alert("Sila pilih kategori tanah!")
		goTo(kategory);
		return;
		}
		}
		     
		
	}


}


///////////////////////////////////////
function ucwords( str ) {
    // http://kevin.vanzonneveld.net
    // +   original by: Jonas Raoni Soares Silva (http://www.jsfromhell.com)
    // +   improved by: Waldo Malqui Silva
    // +   bugfixed by: Onno Marsman
    // *     example 1: ucwords('kevin van zonneveld');
    // *     returns 1: 'Kevin Van Zonneveld'
    // *     example 2: ucwords('HELLO WORLD');
    // *     returns 2: 'HELLO WORLD'
 
    return (str+'').replace(/^(.)|\s(.)/g, function ( $1 ) { return $1.toUpperCase ( ); } );
}

function lcfirst( str ) {
    // http://kevin.vanzonneveld.net
    // *     example 1: lcfirst('Kevin Van Zonneveld');
    // *     returns 1: 'kevin Van Zonneveld'
 
    str += '';
    var f = str.charAt(0).toLowerCase();
    return f + str.substr(1);
}

function ucfirst( str ) {
    // http://kevin.vanzonneveld.net
    // +   original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // +   bugfixed by: Onno Marsman
    // +   improved by: Brett Zamir (http://brettz9.blogspot.com)
    // *     example 1: ucfirst('kevin van zonneveld');
    // *     returns 1: 'Kevin van zonneveld'
 
    str += '';
    var f = str.charAt(0).toUpperCase();
    return f + str.substr(1);
}

//********************

function goTo(ElementID) {
	//elmnt.focus();
	//elmnt.select();
	//elmnt.id;
	//elmnt.class
	if (ElementID) {
		 returnObjById(ElementID).focus();
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

function returnObjById( id )
{
    if (document.getElementById)
        var returnVar = document.getElementById(id);
    else if (document.all)
        var returnVar = document.all[id];
    else if (document.layers)
        var returnVar = document.layers[id];
    return returnVar;
}

// ************* Check/Uncheck All Fields

var checkflag = "false";
function check(field) {
  if (checkflag == "false") {
    for (i = 0; i < field.length; i++) {
      field[i].checked = true;
    }
    checkflag = "true";
    return "Uncheck All";
  } else {
    for (i = 0; i < field.length; i++) {
      field[i].checked = false;
    }
    checkflag = "false";
    return "Check All";
  }
}


function isUndefined(x)

{

   return x == null && x !== null;

}

function check_date(field){	
	
	/*
	 * utk format : ddmmyy
	 * 				ddmmyyyy
	 * 				ddXmmXyy   
	 * 				ddXmmXyyyy
	 * 
	 * 				( X = sign selain 0-9 )
	 */
	
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
	   /* Delete all chars except 0..9 */
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
	   /* Error-message if err != 0 */
	   else {
	      alert("Sila masukkan tarikh dengan betul");
	      //DateField.select();
		  //DateField.focus();
	   }
	}

//********************* Start editby zulfazdliabuas@gmail.com ******************** 
/* AUTO LOGOUT UNTUK INTERNAL
 * Call function reset_interval() pada file DesktopController.java
 */
var timer;
function set_interval(){
	//the interval 'timer' is set as soon as the page loads
	timer=setInterval("auto_logout()", 10000*60*30);
	// the figure '10000' above indicates how many milliseconds the timer be set to.
	//Eg: to set it to 5 mins, calculate 5min= 5x60=300 sec = 300,000 millisec. So set it to 3000000
}
 
function reset_interval(){
	//resets the timer. The timer is reset on each of the below events:
	// 1. mousemove   2. mouseclick   3. key press 4. scroliing
	//first step: clear the existing timer
	clearInterval(timer);
	//second step: implement the timer again
	timer=setInterval("auto_logout()", 10000*60*30);
	//..completed the reset of the timer
}
 
function auto_logout(){
	//--- zul disable old notification logout. --- 
	//alert('Harap maaf.Sesi anda sudah tamat\nSila login kembali.'); 
	//this function will redirect the user to the logout script
	//window.location="../logout_internal.jsp";
	//--- end disable old notification logout. --- 
	
	//---start zul create new for confirmation logout--- 
	//Call Sweetalert for popup Logout 
	swal({
		title: "Harap maaf",
		text: "Sesi anda telah tamat. Adakah anda ingin",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		cancelButtonText: "MENERUSKAN SESI",
		confirmButtonText: "MENAMATKAN SESI",
		closeOnConfirm: false
	},

	function(){
		//This function akan redirect ke file logout_internal.jsp apabila tekan button "MENAMATKAN SESI".
		window.location="../logout_internal.jsp";
	});
	//---end--- 
}
//********************* End editby zulfazdliabuas@gmail.com *********************

//********************* Start addby zulfazdliabuas@gmail.com *********************
/* AUTO LOGOUT UNTUK ONLINE
 * panggil function reset_interval_online pada file OnlineController.java
 */
function reset_interval_online(){	
	//resets the timer. The timer is reset on each of the below events:
	// 1. mousemove   2. mouseclick   3. key press 4. scroliing
	//first step: clear the existing timer
	clearInterval(timer);
	//second step: implement the timer again
	timer=setInterval("auto_logout_online()", 1800000);
	//Note : 1800000 Miliseconds bersamaan 30 Minit. Untuk testing boleh gunakan 1 minit = 60000 miliseconds
}
 
function auto_logout_online(){
	swal({
		title: "Harap maaf",
		text: "Sesi anda telah tamat. Adakah anda ingin",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		cancelButtonText: "MENERUSKAN SESI",
		confirmButtonText: "MENAMATKAN SESI",
		closeOnConfirm: false
	},
	function(){
		//This function akan redirect ke file logout_online.jsp apabila tekan button "MENAMATKAN SESI".
		window.location="../logout_online.jsp";
	});
}
//********************* End addby zulfazdliabuas@gmail.com *********************


function create_request_string(theForm)
{
	var reqStr = "";
	
	for(i=0; i < theForm.elements.length; i++)
	{
		isFormObject = false;
		
		switch (theForm.elements[i].tagName)
		{
			case "INPUT":
			
			switch (theForm.elements[i].type)
			{
				case "text":
				case "hidden":
				reqStr += theForm.elements[i].name + "=" + encodeURIComponent(theForm.elements[i].value);
				isFormObject = true;
				break;
				
				case "checkbox":
				if (theForm.elements[i].checked){
					reqStr += theForm.elements[i].name + "=" + theForm.elements[i].value;
				}else{
					reqStr += theForm.elements[i].name + "=";
				}
				isFormObject = true;
				break;
				
				case "radio":
				if (theForm.elements[i].checked){
					reqStr += theForm.elements[i].name + "=" + theForm.elements[i].value;
					isFormObject = true;
				}
			}
			break;
			
			case "TEXTAREA":
			
			reqStr += theForm.elements[i].name + "=" + encodeURIComponent(theForm.elements[i].value);
			isFormObject = true;
			break;
			
			case "SELECT":
			var sel = theForm.elements[i];
			reqStr += sel.name + "=" + sel.options[sel.selectedIndex].value;
			isFormObject = true;
			break;
		}
		
		if ((isFormObject) && ((i+1)!= theForm.elements.length))
		{
			reqStr += "&";
		}
	
	}
	
	return reqStr;
}
