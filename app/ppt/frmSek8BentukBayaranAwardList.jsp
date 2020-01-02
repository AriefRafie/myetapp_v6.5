#parse("app/ppt/Sek8PagingPampasan.jsp")

<fieldset id="top">
<center>
<legend><strong>Maklumat Bayaran Pampasan</strong></legend>

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>

	<fieldset>
	<legend><strong>Maklumat Award</strong></legend>
		<table width="100%" border="0">
			<tr>
				<td width="25%">No. Siasatan Pengambilan</td>				
				<td width="1%">:</td>
				<td width="74%">$!lblNoSiasatan</td>
			</tr>
			<tr>
				<td>No. Warta</td>				
				<td>:</td>
				<td>$!lblNoWarta</td>
			</tr>
			<tr>
				<td>Tarikh Warta</td>				
				<td>:</td>
				<td>$!lblTarikhWarta</td>
			</tr>
		</table>
	</fieldset>

<br/>

	<fieldset>
	<legend><strong>Jadual</strong></legend>
	
    	<table width="100%" border="0">
    		<tr>
    			<td colspan="2"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahBentukBayaran('$!id_hakmilik')"></td>
    		</tr>
    	</table>
  		
    	#if($saiz_jadual > 5)
        <div style="width:100%;height:100;overflow:auto"> 
        #end
            	
    	<table width="100%" border="0">	
    			<tr class="table_header">
    				<td width="4%" align="center"><b>Bil</b></td>
    				<td>&nbsp;<b>Pihak Yang Berkepentingan</b></td>
    				<td>&nbsp;<b>Jumlah Bayaran Award (RM)</b></td>
    				<td>&nbsp;<b>Dokumen</b></td>
    			</tr>
    				
    			#if($saiz_jadual!=0)
           	 	#foreach($listP in $listBentukPampasan)
              		#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              				#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    
               	<tr>
                   	<td class="$row" align="center">$!listP.bil</td>
                   	<td class="$row">&nbsp;<a href="javascript:viewBentukBayaran('$!listP.id_award')"><font color="blue">$!listP.nama_pb</font></a></td>
                   	<td class="$row">&nbsp;$!Utils.format2Decimal($!listP.bayar_award)</td>
                   	<td class="$row">&nbsp;<a href="javascript:paparLampiran('$!listP.id_dokumen')"><font color="blue">$!listP.nama_fail</font></a></td>
              	<tr>
           		#end
        		#else
                <tr>
                   	<td colspan="2">Tiada rekod</td>
                </tr>
        		#end
    				
    	 </table>
    			
    	#if($saiz_jadual > 5)
        </div>
        #end
		
	</fieldset>

</center>
</fieldset>

<input name="tabId" type="hidden" id="tabId"/>
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_siasatan" value="$!id_siasatan">
<input type="hidden" name="id_award" value="$!id_award">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
function viewBentukBayaran(idAward) {
	document.${formName}.pagingPampasan.value = "2";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_award.value = idAward;
	document.${formName}.command.value = "viewBentukBayaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function tambahBentukBayaran(idHakmilik) {
	document.${formName}.pagingPampasan.value = "2";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahBentukBayaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function paparLampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>

<script>
window.onload = submitForm;
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$!selectedTab});
function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}

function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function validateNumber(elmnt,content) {
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
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890./";
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
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content!=""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	}else{
		elmnt.value = "";
	}
}
</script>

<script>
//Declaring valid date character, minimum year and maximum year
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
</script>