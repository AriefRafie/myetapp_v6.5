<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
#if ($IdAlert == 1)
<script type="text/javascript">
	alert("Maaf. Maklumat dicari tidak wujud.")
</script>
#end
<p></p>
<fieldset>
<legend>Maklumat Simati</legend>
<table border="0" align="center" width="100%">
    <tbody>
      <tr> 
        <td width="30%" height="24" scope="row" align="right">&nbsp;No. KP Baru : </td>
        <td width="70%"><input name="txtNoKPBaru1a" id="txtNoKPBaru1a" style="width: 50px;" type="text" size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2a')" onblur="check(this,this.value)"/>-<input name="txtNoKPBaru2a" id="txtNoKPBaru2a" style="width: 20px;" type="text" size="3" maxlength="2"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3a')"/>-<input name="txtNoKPBaru3a" id="txtNoKPBaru3a"  style="width: 40px;" type="text" size="5" maxlength="4"/></td>
      </tr>
      <tr> 
        <td scope="row" align="right">&nbsp;No. KP Lama : </td>
        <td ><input name="txtNoKPLamaa" type="textbox" id="txtNoKPLamaa" maxlength="8" size="10" style="width: 120px; text-transform:uppercase;" >&nbsp;&nbsp;<i>(Sila kosongkan ruangan ini jika tidak berkaitan)</i></td>
      </tr>
      <tr> 
        <td scope="row" align="right">&nbsp;Lain-lain KP : </td>
        <td ><select name="socJenisKPLaina" style="width: 120px;" >
            #set ($id = "")
            #set ($keterangan = "")
            #set ($selected = "")
	            #foreach($Listkp in $listkp)
	            #set ($id = $Listkp.id)
	            #set ($keterangan = $Listkp.keterangan)
	            	#if ($id == $JenisKpLain)
	            		#set ($selected = "selected")
	            	<option value="$id" $selected/>$keterangan.toUpperCase()</option>
	            	#end
	            #end
            	<option value="0"/>SILA PILIH KP</option>
            	#foreach($Listkp in $listkp)
            	#set ($id = $Listkp.id)
           		#set ($keterangan = $Listkp.keterangan)
	            	<option value="$id"/>$keterangan.toUpperCase()</option>
            	#end
          </select> <input name="txtNoKPLaina" id="txtNoKPLaina" style="width: 97px; text-transform:uppercase;" type="text"  maxlength="9" />&nbsp;&nbsp;<i>(Sila kosongkan ruangan ini jika tidak berkaitan)</i></td>
      </tr>
      <tr> 
        <td scope="row" align="right">&nbsp;No. Permohonan :</td>
        <td ><input name="txtNoPermohonan" type="text" id="txtNoPermohonan" value="$!noPermohonan" size="50" maxlength="50" style="width: 250px; text-transform:uppercase;" ></td>
      </tr>
      <tr> 
      	<td></td>
        <td scope="row" align="left" height="40px" valign="bottom"><input type="button" name="cmdSemak" value="Semak" onClick="Semak()"><input type="reset" name="cmdBatal" value="Batal"></td>
      </tr>
	  <tr> 
        <td colspan="2" height="50px" valign="bottom"><i>*&nbsp;Pastikan salah satu maklumat simati diisi.</i></td>
      </tr>
      </tbody>
      </table>
</fieldset>
	<input type="hidden" name="hitButt" value="check_kp"/>
	<input type="hidden" name="action"/>
<script>
function Semak() {
	var negeri_code= document.${formName}.txtNoKPBaru2a.value;
	var dob_code1 = document.${formName}.txtNoKPBaru1a.value;
	if(dob_code1.charAt(0)<3) {
	 	var dum1 = "20";
	}
	else {
		var dum1 = "19";
	}

	var tt1 = dob_code1.charAt(4)+""+dob_code1.charAt(5)+"/"+dob_code1.charAt(2)+""+dob_code1.charAt(3)+"/"+dum1+dob_code1.charAt(0)+""+dob_code1.charAt(1);	
	var dt_dob1   = parseInt(tt1.substring(0,2),10);
    var mon_dob1  = parseInt(tt1.substring(3,5),10)-1;
    var yr_dob1   = parseInt(tt1.substring(6,10),10);
	var date_dob1 = new Date(yr_dob1, mon_dob1, dt_dob1);

	var dtCh= "/";
	var daysInMonth = DaysArray(12);
	var pos1=tt1.indexOf(dtCh);
	var pos2=tt1.indexOf(dtCh,pos1+1);
	var strDay=tt1.substring(0,pos1);
	var strMonth=tt1.substring(pos1+1,pos2);
	var strYear=tt1.substring(pos2+1);
	strYr=strYear;

	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
	}
	
	month=parseInt(strMonth);
	day=parseInt(strDay);
	year=parseInt(strYr);

	var currentTime = new Date()
	var minYear = 1900;
	var maxYear = currentTime.getFullYear();
		
	if (document.${formName}.txtNoKPBaru1a.value == "" && document.${formName}.txtNoKPBaru2a.value == "" && document.${formName}.txtNoKPBaru3a.value == "" &&document.${formName}.txtNoKPLamaa.value == "" && document.${formName}.txtNoKPLaina.value=="" && document.${formName}.txtNoPermohonan.value == ""){
		alert("Sila masukkan salah satu maklumat simati");
		txtNoKPBaru1a.focus();
	}
	else if (isNaN(document.${formName}.txtNoKPBaru1a.value)){
		alert("Sila masukkan nombor sahaja");
		txtNoKPBaru1.focus();
	}
	else if (isNaN(document.${formName}.txtNoKPBaru2a.value)){
		alert("Sila masukkan nombor sahaja");
		txtNoKPBaru2.focus();
	}
	else if (isNaN(document.${formName}.txtNoKPBaru3a.value)){
		alert("Sila masukkan nombor sahaja");
		txtNoKPBaru3.focus();
	}
	else if (document.${formName}.txtNoKPBaru1a.value != "" && document.${formName}.txtNoKPBaru1a.value.length < 6){
		alert("Sila masukkan No KP Baru sepenuhnya");
		txtNoKPBaru1a.focus();
	}
	else if (document.${formName}.txtNoKPBaru2a.value != "" && document.${formName}.txtNoKPBaru2a.value.length < 2){
		alert("Sila masukkan No KP Baru sepenuhnya");
		txtNoKPBaru2a.focus();
	}
	else if (document.${formName}.txtNoKPBaru3a.value != "" && document.${formName}.txtNoKPBaru3a.value.length < 4){
		alert("Sila masukkan No KP Baru sepenuhnya");
		txtNoKPBaru3a.focus();
	}
	else if (document.${formName}.txtNoKPBaru1a.value != "" && isIc(tt1)==false){
	  	txtNoKPBaru1a.focus();
	}
	else if (document.${formName}.txtNoKPBaru2a.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" && negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" && negeri_code!="15" && negeri_code!="58" && negeri_code!="16" && negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99")) {
		alert("Sila masukkan kod negeri yang sah");
		txtNoKPBaru2a.focus()
	}
	else if (document.${formName}.txtNoKPLamaa.value != "" && document.${formName}.txtNoKPLamaa.value.length < 7){
		alert("Sila masukkan NO KP Lama Sepenuhnya");
		txtNoKPLamaa.focus();
	}
	else if (document.${formName}.txtNoKPLaina.value != "" && document.${formName}.socJenisKPLaina.value == "0"){
		alert("Sila pilih jenis No KP");
		socJenisKPLaina.focus();
	}
	else if (document.${formName}.socJenisKPLaina.value != "0" && document.${formName}.txtNoKPLaina.value == ""){
		alert("Sila masukkan No KP Lain");
		txtNoKPLaina.focus();
	}
	else{
		document.${formName}.method = "post";
		document.${formName}.hitButt.value = "check_kp";
		document.${formName}.action.value = "";
		document.${formName}.submit();
	}
}

function getBatal(){
	document.${formName}.txtNoKPBaru1a.value="";
	document.${formName}.txtNoKPBaru2a.value="";
	document.${formName}.txtNoKPBaru3a.value="";
	document.${formName}.txtNoKPLamaa.value="";
	document.${formName}.socJenisKPLaina.selectedIndex=0;
	document.${formName}.txtNoKPLaina.value="";
}
</script>
<script language = "Javascript">
/**
 * DHTML date validation script for dd/mm/yyyy. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */
// Declaring valid date character, minimum year and maximum year
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
</script>

