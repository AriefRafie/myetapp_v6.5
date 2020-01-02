<!--<% //@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %> -->
<meta http-equiv="Content-Script-Type" content="text/javascript">

<style type="text/css">
<!--
.style3 {
	font-size: 9px;
	color: #0000FF;
	font-style: italic;
}
.style6 {color: #0000FF}
.style7 {color: #000000}
-->
</style>
<head>
<style type="text/css">
<!--
.style2 {color: #FF0000}
.style4 {
	font-size: 9px;
	font-style: italic;
}
-->
</style>

#set ($chkmode = "")

#if ($SimpanStatus == "2")
#set ($chkmode = "disabled")
#else 
#set ($chkmode = "")
#end

</head>
<body>



 
<form name="f1" id='f1' method="post">

#if($!headerppk.size()>0)
#parse("app/ppk/headerppk.jsp")
<br>
#end
 
             
            
       
 

<fieldset>
<legend>Maklumat Penghutang</legend>
<br>

#foreach($list in $View)
#set($noFail = $list.noFail)
#set($idPemohon = $list.idPemohon)


#end



<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<input name="buktimati" type="hidden" value="$!buktimati" />
<input name="sijilmati" type="hidden" value="$!sijilmati" />
<input name="id_Simati" type="hidden" value="$id_Simati" />

<input name="idSimati" type="hidden" value="$id_Simati" />
<input name="idpermohonan" type="hidden" value="$IdPermohonan" />
<input name="idPemohon" type="hidden" value="$idPemohon" />



<table width="100%" border="0"><tr><td width="50%"  valign="top" >

<table width="100%" border="0">
  <tr>
    <td width="50%"   valign="top" >
    
<table width="100%" border="0">
   
      <tr>
        <td><table width="100%" border="0">
        
          <tr>
            <td width="20%" ><span class="style2">*</span> <strong>No KP</strong></td>
            <td width="3%" valign="top">:</td>
            <td width="77%"> 
            
            
            <select name="jenisIc"  id="jenisIc" class="autoselect">
      				<option value="0">Sila Pilih</option>
      				<option value="1">No. KP Baru</option>
      				<option value="2">No. KP Lama</option>
      				<option value="3">No. KP Lain</option>
      			</select>&nbsp;&nbsp;<input name="txtNoKPBaru1" id="txtNoKPBaru1" type="text" value="" size="20" maxlength="14" style="text-transform:uppercase;" />
             <!-- <input name="txtNoKPBaru1" id="txtNoKPBaru1"  type="text" value="" size="30" maxlength="30" onKeyUp="validateXIC(event,this,this.value,'txtNoKPBaru1')"   /></td> -->
          </tr>
          
          <tr>
            <td width="20%" ><span class="style2">*</span> <strong>Nama</strong></td>
            <td width="3%" valign="top" >:        </td>
            <td width="77%" >  <input type="text" name="txtnama" id="txtnama" size="30" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" value=""   /></td>
          </tr>
          
          
           <tr>
            <td width="20%" ><strong>Alamat</strong></td>
            <td width="3%" valign="top" >:        </td>
            <td width="77%" >  
            	<label><span style="width: 338px;">
                <input name="txtAlamat1" id="txtAlamat1" type="text"  size="60" maxlength="100" value="" style="text-transform:uppercase; onblur="this.value=this.value.toUpperCase()"/>
                </span></label></td>
          </tr>
          
          <tr>
            <td width="20%" ></td>
            <td width="3%" valign="top" >:        </td>
            <td width="77%" >  
            	<label><span style="width: 338px;">
                <input name="txtAlamat2" id="txtAlamat2" type="text"  size="60" maxlength="100" value="" style="text-transform:uppercase; onblur="this.value=this.value.toUpperCase()"/>
                </span></label></td>
          </tr>
          
          <tr>
            <td width="20%" ></td>
            <td width="3%" valign="top" >:        </td>
            <td width="77%" >  
            	<label><span style="width: 338px;">
                <input name="txtAlamat3" id="txtAlamat3" type="text"  size="60" maxlength="100" value="" style="text-transform:uppercase; onblur="this.value=this.value.toUpperCase()"/>
                </span></label></td>
          </tr>
          
          
          <tr>
            <td width="20%" ><strong>Poskod</strong></td>
            <td width="3%" valign="top" >:        </td>
            <td width="77%" >  <input type="text" name="txtposkod" id="txtposkod" size="5" maxlength="5" value=""   /></td>
          </tr>
          
          <tr>
            <td width="20%" ><strong>Negeri</strong></td>
            <td width="3%" valign="top" >:        </td>
            <td width="77%" >  $selectNegeri </td>
          </tr>
          
          <tr>
            <td width="20%" ><strong>Bandar</strong></td>
            <td width="3%" valign="top" >:        </td>
            <td width="77%" ><input name="txtBandar" id="txtBandar" type="text"  width="300px" value="" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()"/> </td>
          </tr>
          
        </table>
        </td>
      </tr>

    </table>
    </td>

 
</table>
#if($chkmode != "disabled")
<table width="100%">
  <tr>
   <td><span class="style4"><span class="style2">Perhatian</span> : Sila pastikan label bertanda <span class="style2">*</span> diisi.</span></td>
  </tr>
</table>
#end 
<p align="center">
<label>



<input type="hidden" name="idtemp" value="$IdPermohonan"/>
<input type="hidden" name="idPermohonan" value="$IdPermohonan"/>
<input type="hidden" name="negid" value="$NegId"/>
<input type="hidden" name="idFlag"/>

<input type="hidden" name="idUser" value="$userid"/>
<input type="text" name="eventStatus" value="$EventStatus"/>
<input type="hidden" name="flagno"/>
<input type="hidden" name="GetNewPemohon" value="$GetNewPemohon"/>
<input type="hidden" name="command" />

#if ($SimpanStatus == "2" )


#if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
<!--<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini()"/>-->
#end

#parse("app/ppk/syarat_kemaskini.jsp")

#if($boleh_kemaskini == "yes")
#end

<input type="button" name="cmdKemaskini" id="cmdKemaskini1" value="Kemaskini" onClick="Kemaskini()"/>
#if($flag_kemaskini_selesai != "yes")
    <script>
	document.getElementById('cmdKemaskini1').style.display = "none";
	</script>
    #end 


#if ($backStatus == "1")
	
	#end
#else
	<input name="cmdSimpan" type="button" id="cmdSimpan"  value="Simpan" onClick="DoTheCheck()"/>
	<input name="cmdBatal" type="reset"  id="cmdBatal" value="Batal"/>
#end</label>

          
             
#parse("app/ppk/headerppk_script.jsp")
</p>
</td>
</tr>
</table>
  </fieldset>
</form>



</body>
 
<script>


function DoTheCheck() {
	
	 var dob_code = "";
	 var tt = "";
	 

	var dm = $jquery('#txtnama').val();
	var jenisIc = $jquery('#jenisIc').val();
	//alert($jquery('#jenisIc').val());
	
	if($jquery('#jenisIc').val() == 0 ){
		alert("Sila pilih Jenis No KP");
	}
	
	else if(jenisIc==1){
	    var dob_code = $jquery('#txtNoKPBaru1').val();
	    
	    
	    if(dob_code.charAt(0)<3)
	    	{
	    	 var dum = "20";
	    	}
	    	else
	    	{
	    	var dum = "19";
	    	}
	    var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""	+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);
	    
	    if ($jquery('#txtNoKPBaru1').val() != "" && isIc(tt)==false){
	    	$jquery('#txtNoKPBaru1').focus();
			return;
		}
	}
	

	if ( $jquery('#txtnama').val() == ""){
		alert("Sila masukkan Nama");
	}
	else if ($jquery('#txtNoKPBaru1').val() == ""){
		alert("Sila masukkan No KP");
	}
	else{
		Simpan();
		//alert("success");
	}

}
function Simpan(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.method="POST";
		document.f1.command.value="Simpan";
		/* document.f1.action = "?_portal_module=FrmPrmhnnSek8Internal"; */
		document.f1.action = "?_portal_module=FrmHtgSemakPenghutang";
		document.f1.submit();
	}	
}
function Seterusnya() {
		document.f1.method="POST";
		document.f1.command.value="Seterusnya";
		document.f1.idFlag.value="1";
		document.f1.flagno.value="2";
		document.f1.action = "?_portal_module=FrmPrmhnnSek8Internal";
		document.f1.submit();
}
function Kemaskini(){
	document.f1.method="POST";
	document.f1.command.value="Kemaskini";
	document.f1.action = "?_portal_module=FrmPrmhnnSek8Internal";
	document.f1.submit();
}


function validDate() {
	var currentTime = new Date();
	var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;

	if (document.f1.txdTarikhByrn.value > currentDate){
		alert("Sila pastikan tarikh bayaran tidak melebihi dari tarikh hari ini.");
		txdTarikhByrn.clear();
	}
	else
	{
	return;
	}
	
}




function Kembali(){
	document.f1.method="POST";
	document.f1.command.value="xxx";
	document.f1.action = "";
	document.f1.submit();
}
function kembaliSenaraiFail(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.f1.submit();
}
function kembaliSenaraiPermohonan(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
	document.f1.method="POST";
	document.f1.submit();
}
function papar(){
	document.f1.command.value = "papar";
	document.f1.method="POST";
		document.f1.action = "?_portal_module=FrmPrmhnnSek8Internal";
	
	document.f1.submit();
}


var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yg sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yg sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yg sah")
		return false
	}
return true
}

function isIc(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
}


 function trans_date(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.f1.txdTarikhByrn.value = trans;

}
else
{
return;
}

}




function ForView(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="+noFail;
	document.f1.submit();
}

validateXIC = function(e,elmnt,content,nextElementID) {

	//if it is character, then remove it..
	
	
	var keycode;
	if(window.event)keycode = window.event.keyCode;
	else if (e) keycode = e.which;
	else return true;
	
	//alert(keycode);
	
	if((keycode >= 37 && keycode <= 40) || (keycode == 9)) return false;
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumericX(content);
		return;
	}
	//goto next column if maximum length reach
	
	
	if (content.length == elmnt.maxLength) 
	{
	//alert("3:"+nextElementID);
	goTo(nextElementID);
	//nextElementID.focus();
	}
	
	
}

function RemoveNonNumericX( strString )
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


</script>