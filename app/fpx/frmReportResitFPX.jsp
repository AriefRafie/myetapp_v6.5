#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset>
<legend>Laporan FPX</legend>

	<table width="100%" border="0">
		<tr>
			<td width="2%">&nbsp;</td>
			<td width="25%">Tempoh Tarikh Laporan Resit</td>
			<td width="1%">:</td>
			<td width="22%"><input name="txdTarikhResitDari" id="txdTarikhResitDari" size="13" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhResitDari',false,'dmy');">&nbsp;$!frmtdate</td>
            <td width="2%">&nbsp;</td>
            <td width="7%">Hingga</td>
            <td width="1%">:</td>
            <td width="40%"><input name="txdTarikhResitHingga" id="txdTarikhResitHingga" size="13" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhResitHingga',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr>	
	</table>
	
</fieldset>

	<table width="40%" border="0" align="center">
		<tr>
			<td>
				<input type=button value="Cetak" onClick="javascript:cetak();">
  				<input type=button value="Kosongkan" onClick="javascript:clearData();">
            </td>
		</tr>
	</table>

<!-- START HIDDEN VALUE -->

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- Do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<!-- Others -->
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- END HIDDEN VALUE -->



<!-- START MAIN JAVASCRIPT -->
<script>
function cetak(){

	var from = document.${formName}.txdTarikhResitDari.value;
	var to = document.${formName}.txdTarikhResitHingga.value;

	//tarikh dari
	var TS  = document.getElementById("txdTarikhResitDari").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateFrom = new Date(yr1, mon1, dt1);

  	//tarikh hingga
	var TH  = document.getElementById("txdTarikhResitHingga").value;
	var dt2   = parseInt(TH.substring(0,2),10);
   	var mon2  = parseInt(TH.substring(3,5),10);
   	var yr2   = parseInt(TH.substring(6,10),10);
   	var dateTo = new Date(yr2, mon2, dt2);

   	
	if(document.${formName}.txdTarikhResitDari.value == ""){
		alert("Sila masukkan \"Tempoh Tarikh Laporan Resit\" terlebih dahulu.");
  		document.${formName}.txdTarikhResitDari.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhResitHingga.value == ""){
		alert("Sila masukkan \"Tempoh Tarikh Laporan Resit Hingga\" terlebih dahulu.");
  		document.${formName}.txdTarikhResitHingga.focus(); 
		return;
	}
	else if(dateTo < dateFrom){
		alert("Sila pastikan \"Tempoh Tarikh Laporan Resit Hingga\"  tidak kurang dari  \"Tempoh Tarikh Laporan Resit Dari\".");
		document.${formName}.txdTarikhResitHingga.focus();
		return;
	}else{

		var url = "../servlet/ekptg.fpx.FpxReportResitClass?tarikh_dari="+from+"&tarikh_hingga="+to+"&yearfrom="+yr1;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
   	 	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus(); 
	}
}
function clearData(){
	doAjaxCall${formName}("clearData");
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="";
	}
	else if(document.getElementById(id).style.display==""){
		document.getElementById(id).style.display="none";
	}
}
</script>
<!-- END MAIN JAVASCRIPT -->



<!-- START OTHERS JAVASCRIPT -->
<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
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
		alert("Sila masukkan bulan yang sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yang sah")
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
<!-- END OTHERS JAVASCRIPT -->