#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset style="width:70%;align:left">
<legend><strong>Maklumat Endosan Borang K</strong></legend>

	#if($mode=="new")
	<table width="100%" border="0">
		<tr>
			<td width="1%"><font color="red">*</font></td>
			<td width="25%">Tarikh Terima</td>
			<td width="1%">:</td>
			<td width="73%"><input name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Perserahan</td>
			<td>:</td>
			<td><input type="text" name="txtPerserahan" id="txtPerserahan" value="" size="21" maxlength="30" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>
		<!-- <tr>
			<td>&nbsp;</td>
			<td>Diterima Daripada</td>
			<td>:</td>
			<td><select name="sorTerima" style="width:150px">
      		
      			<option value="">SILA PILIH</option>		
		
			</select></td>
		</tr> -->
		<tr>
			<td>&nbsp;</td>
			<td>Tarikh Catatan Dibuat</td>
			<td>:</td>
			<td><input name="txdTarikhCatatan" id="txdTarikhCatatan" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhCatatan',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Masa Catatan Dibuat</td>
			<td>:</td>
			<td><input type="text" name="txtMasaCatatan" id="txtMasaCatatan" value="" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
				<select name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

                    	<option value="0">SILA PILIH</option>
                    	<option value="1">PAGI</option>
                    	<option value="2">TENGAHARI</option>
                    	<option value="3">PETANG</option>	
                    	
                    </select>&nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font></td>
		</tr>		
	</table>
	#end
	
	
	#if($mode=="view")
	
	#foreach($data in $dataEndosanBorangK)
		#set($txdTarikhTerima = $data.tarikh_terima)
		#set($txdTarikhCatatan = $data.tarikh_catatan)
		#set($txtMasaCatatan = $data.masa_catatan)
		#set($socJenisWaktu = $data.jenis_masa)
		#set($txtPerserahan = $data.no_perserahan)
	#end
	
	#if($isEdit=="no")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#set($disability1 = "disabled")
		#set($M = "")
	#else
		#set($M = "*")
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
	#end
	
	<table width="100%" border="0">
		<tr>
			<td width="1%"><font color="red">$M</font></td>
			<td width="25%">Tarikh Terima</td>
			<td width="1%">:</td>
			<td width="73%"><input $disability $disabilityx name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Perserahan</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtPerserahan" id="txtPerserahan" value="$!txtPerserahan" size="21" maxlength="30" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>
		<!-- <tr>
			<td>&nbsp;</td>
			<td>Diterima Daripada</td>
			<td>:</td>
			<td><select $disability1 $disabilityx name="sorTerima" style="width:150px">
      		
      			<option value="">SILA PILIH</option>		
		
			</select></td>
		</tr> -->	
		<tr>
			<td>&nbsp;</td>
			<td>Tarikh Catatan Dibuat</td>
			<td>:</td>
			<td><input $disability $disabilityx name="txdTarikhCatatan" id="txdTarikhCatatan" size="11" type="text" value="$!txdTarikhCatatan" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhCatatan',false,'dmy');">&nbsp;$!frmtdate#end</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Masa Catatan Dibuat</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtMasaCatatan" id="txtMasaCatatan" value="$!txtMasaCatatan" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
				<select $disability1 $disabilityx name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

						#if($socJenisWaktu=="1")
						<option value="1">PAGI</option>
                    	<option value="2">TENGAHARI</option>
                    	<option value="3">PETANG</option>	
                    	<option value="0">SILA PILIH</option>
						#elseif($socJenisWaktu=="2")
						<option value="2">TENGAHARI</option>
						<option value="1">PAGI</option>
                    	<option value="3">PETANG</option>	
                    	<option value="0">SILA PILIH</option>
						#elseif($socJenisWaktu=="3")
						<option value="3">PETANG</option>	
						<option value="1">PAGI</option>
                    	<option value="2">TENGAHARI</option>
                    	<option value="0">SILA PILIH</option>
						#else
						<option value="0">SILA PILIH</option>
                    	<option value="1">PAGI</option>
                    	<option value="2">TENGAHARI</option>
                    	<option value="3">PETANG</option>	
						#end
                    	
                    	
                    </select>&nbsp;#if($isEdit=="yes")<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font>#end</td>
		</tr>
	</table>
	#end
	
	
</fieldset>

	<table width="70%" border="0" align="left">
		<tr align="center">
			<td>
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanEndosanBorangK('$!id_borangk','$!id_endosanborangk','$!mode')">
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniEndosan('$!id_endosanborangk')">
				<input type="button" name="cmdHapus" value="Hapus" onClick="hapusEndosan('$!id_endosanborangk')" >
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanEndosanBorangK('$!id_borangk','$!id_endosanborangk','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_endosanborangk')">
				#end
			#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:maklumatBorangK('$!id_hakmilik')">
				</td>
			</tr>
	</table>

	<!-- <br/>

	<fieldset id="bottom">
	<legend><strong>Senarai Endosan Borang K</strong></legend>
	 	
	 	#if($mode=="view")
		<table width="100%" border="0">   
              <tr>
                 <td width="30%" align="left"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahEndosanBorangK('$!id_hakmilik','$!id_borangk');"></td>
              </tr>
    	</table>
		#end
	
		#if($saiz_endosan > 5)
                <div style="width:100%;height:100;overflow:auto"> 
        #end
	
		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>No. Perserahan</b></td>
        			<td><b>Tarikh Terima</b></td>
        			<td><b>Tarikh Catatan Dibuat</b></td>
        			<td><b>Masa Catatan Dibuat</b></td>		
        		</tr>
        		
        		#if($saiz_endosan!=0)
                    #foreach($list in $listEndosanBorangK)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!list.bil</td>
                   <td class="$row"><a href="javascript:viewEndosan('$!list.id_endosanborangk')"><font color="blue">$!list.no_perserahan</font></a></td>
                   <td class="$row">$!list.tarikh_terima</td>
                   <td class="$row">$!list.tarikh_catatan</td>
                   <td class="$row">$!list.masa_catatan&nbsp;$!list.masa</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		</table>
			
		#if($saiz_endosan > 5)
        	</div>
        #end	
		
	</fieldset> -->


<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_borangk" value="$!id_borangk">
<input type="hidden" name="id_endosanborangk" value="$!id_endosanborangk">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function batalKemaskini(id_endosanborangk) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_endosanborangk.value = id_endosanborangk;
	document.${formName}.command.value = "viewEndosanBorangK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function kemaskiniEndosan(id_endosanborangk) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_endosanborangk.value = id_endosanborangk;
	document.${formName}.command.value = "viewEndosanBorangK";
	document.${formName}.command2.value = "kemaskiniEndosan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function hapusEndosan(id_endosanborangk) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_endosanborangk.value = id_endosanborangk;
	document.${formName}.command.value = "hapusEndosan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function tambahEndosanBorangK(idHakmilik,idBorangK) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.id_borangk.value = idBorangK;
	document.${formName}.command.value = "tambahEndosanBorangK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function viewEndosan(id_endosanborangk) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_endosanborangk.value = id_endosanborangk;
	document.${formName}.command.value = "viewEndosanBorangK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function simpanEndosanBorangK(idBorangK,id_endosanborangk,mode) {

	var dat1=document.${formName}.txdTarikhTerima;
	var dat2=document.${formName}.txdTarikhCatatan;

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh Terima
	var TS  = document.getElementById("txdTarikhTerima").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateTerima = new Date(yr1, mon1, dt1);
	
	if(document.${formName}.txdTarikhTerima.value == ""){
		alert("Sila masukkan \"Tarikh Terima\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerima.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if(dat1.value!="" && (dateTerima < currentDate)){
	   	alert("Sila pastikan \"Tarikh Terima\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTerima.focus();
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_borangk.value = idBorangK;

		if(mode=="new"){
			document.${formName}.command.value = "tambahEndosanBorangK";
			document.${formName}.command2.value = "simpanEndosanBorangK";
		}else{
			document.${formName}.id_endosanborangk.value = id_endosanborangk;
			document.${formName}.command.value = "viewEndosanBorangK";
			document.${formName}.command2.value = "kemaskiniEndosan";
			document.${formName}.command3.value = "updateEndosan";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
		document.${formName}.submit();
	}
}
function maklumatBorangK(idHakmilik) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "maklumatBorangK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
</script>

<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>


<script>
function checkDigit() {
	if(document.getElementById("txtMasaCatatan").value != "" && document.getElementById("txtMasaCatatan").value.length < 4){
		alert("Sila Pastikan Format Masa Catatan Adalah \"HHMM\"");
		document.${formName}.txtMasaCatatan.focus(); 
		return;	
	}
}
function validateJenisWaktu(elmnt,content) {

	var length = parseInt(document.getElementById("txtMasaCatatan").value.length);
	
	if(length>=2){

		var ValJam = content.substring(0,2);

		if(ValJam!=""){	
		
			if(ValJam >= 07 && ValJam <= 11){
				document.getElementById("socJenisWaktu").value = "1" ;
			}else if(ValJam == 12){
				document.getElementById("socJenisWaktu").value = "2" ;
			}else if(ValJam == 01 || ValJam == 02 || ValJam == 03 || ValJam == 04 || ValJam == 05 || ValJam == 06){
				document.getElementById("socJenisWaktu").value = "3" ;
			}else{
				document.getElementById("socJenisWaktu").value = "0" ;
			}
		
		}else{
			document.getElementById("socJenisWaktu").value = "0" ;
		}	
		return;

	}else{
		document.getElementById("socJenisWaktu").value = "0";
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