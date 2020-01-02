
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

#foreach($data in $dataMaklumatTanah)
	#set($lblMukim = $data.nama_mukim)
	#set($lblNoHakmilik = $data.no_hakmilik)
	#set($lblJenisHakmilik = $data.jenis_hakmilik)
	#set($txdTarikhTerima = $data.tarikh_terima_hm)
	#set($sorStatusBorangL = $data.status_borangl)
#end

<fieldset>
<legend><strong>Maklumat Penerimaan Borang L</strong></legend>

	#if($mode=="new")
	<table width="100%" border="0">
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="25%">Mukim</td>
			<td width="1%">:</td>
			<td width="73%"><font color="blue">$!lblMukim</font></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Hakmilik</td>
			<td>:</td>
			<td><font color="blue">$!lblNoHakmilik</font></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Jenis Hakmilik</td>
			<td>:</td>
			<td><font color="blue">$!lblJenisHakmilik</font></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Tarikh Terima</td>
			<td>:</td>
			<td><input name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr>
		<tr>
			<td><font color="red">*</font></td>
			<td>Status Borang L</td>
			<td>:</td>
			<td><select name="sorStatusBorangL" style="width:200px">
      		
      			<option value="">SILA PILIH</option>	
      			<option value="1">HAKMILIK BELUM DITERIMA</option>
      			<option value="2">HAKMILIK TELAH DITERIMA</option>	
		
			</select></td>
		</tr>
	</table>
	#end
	
	
	#if($mode=="view")
	
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
			<td width="1%">&nbsp;</td>
			<td width="25%">Mukim</td>
			<td width="1%">:</td>
			<td width="73%"><font color="blue">$!lblMukim</font></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Hakmilik</td>
			<td>:</td>
			<td><font color="blue">$!lblNoHakmilik</font></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Jenis Hakmilik</td>
			<td>:</td>
			<td><font color="blue">$!lblJenisHakmilik</font></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Tarikh Terima</td>
			<td>:</td>
			<td><input $disability $disabilityx name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
		</tr>
		<tr>
			<td><font color="red">$!M</font></td>
			<td>Status Borang L</td>
			<td>:</td>
			<td><select $disability1 $disabilityx name="sorStatusBorangL" style="width:200px">
      		
      			#if($sorStatusBorangL=="1")
      			<option value="1">HAKMILIK BELUM DITERIMA</option>
      			<option value="">SILA PILIH</option>	
      			<option value="2">HAKMILIK TELAH DITERIMA</option>	
      			#elseif($sorStatusBorangL=="2")
      			<option value="2">HAKMILIK TELAH DITERIMA</option>	
      			<option value="">SILA PILIH</option>	
      			<option value="1">HAKMILIK BELUM DITERIMA</option>
      			#else
      			<option value="">SILA PILIH</option>	
      			<option value="1">HAKMILIK BELUM DITERIMA</option>
      			<option value="2">HAKMILIK TELAH DITERIMA</option>	
      			#end
      			
			</select></td>
		</tr>
	</table>
	#end
	
</fieldset>

	<table width="95%" border="0">
		<tr align="center">
			<td>
			
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updateHakmilik('$!id_permohonan','$!id_hakmilik','$!mode')">
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniHakmilik('$!id_hakmilik')">
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updateHakmilik('$!id_permohonan','$!id_hakmilik','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_hakmilik')">
				#end
			#end
				
			<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:viewPenerimaanHakmilik('$!id_permohonan');">
			</td>
		</tr>
	</table>

			<fieldset>
				<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
			
					<table width="100%" border="0">   
                		<tr>
                    		<td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot2" value="$!carianNoLot2" maxlength="20" size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    					</tr>
    				</table>
    			
    				#if($saiz_listTanah > 5)
        			<div style="width:100%;height:100;overflow:auto"> 
        			#end	
    			
    				<table width="100%" border="0"> 
  
        				<tr class="table_header">
        					<td align="center" width="4%"><b>No</b></td>
        					<td><b>No.Hakmilik</b></td>
        					<td><b>Jenis Hakmilik</b></td>
        					<td><b>Bandar/Pekan/Mukim</b></td>
        					<td><b>Tarikh Terima</b></td>
        					<td><b>Status Borang L</b></td>
        					<td><b>Pegawai</b></td>
        				</tr>
        		
        			#if($saiz_listTanah!=0)
                    	#foreach($listTanah in $listMaklumatTanah)
                    	#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              		 		#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    
               			<tr>
                   			<td class="$row" align="center">$!listTanah.bil</td>
                   			<td class="$row"><a href="javascript:MaklumatPenerimaan('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.no_hakmilik</font></a></td> 
                   			<td class="$row">$!listTanah.jenis_hakmilik</td>
                  			<td class="$row">$!listTanah.nama_mukim</td> 
                  			<td class="$row">$!listTanah.tarikh_terima_hm</td> 
                  			<td class="$row">$!listTanah.statusBorangL</td> 
                  			<td class="$row">$!listTanah.nama_pegawai</td> 
                   		<tr>
                    	#end
               		#else
                 		<tr>
                   			<td colspan="2">Tiada rekod</td>
                 		</tr>
              		 #end
               
		 	 		</table>
	
					#if($saiz_listTanah > 5)
        			</div>
       				#end
	
				</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="paging">
<input type="hidden" name="tabId">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
function batalKemaskini(id_hakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "MaklumatPenerimaan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function kemaskiniHakmilik(id_hakmilik) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "MaklumatPenerimaan";
	document.${formName}.command2.value = "kemaskiniHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function MaklumatPenerimaan(id_hakmilik) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "MaklumatPenerimaan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function updateHakmilik(id_permohonan,id_hakmilik,mode) {

	var dat1=document.${formName}.txdTarikhTerima;

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh borang k 
	var TS  = document.getElementById("txdTarikhTerima").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateTerima = new Date(yr1, mon1, dt1);
	
	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if(dat1.value!="" && (dateTerima < currentDate)){
	   	alert("Sila pastikan \"Tarikh Terima\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTerima.focus();
		return;
	}
*/	else if(document.${formName}.sorStatusBorangL.value == ""){
		alert("Sila pilih \"Status Borang L\" terlebih dahulu.");
  		document.${formName}.sorStatusBorangL.focus(); 
		return;
	}
	else{
	
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.id_hakmilik.value = id_hakmilik;

		if(mode=="new"){
			document.${formName}.command.value = "MaklumatPenerimaan";
			document.${formName}.command2.value = "updateHakmilik";
		}else{
			document.${formName}.command.value = "MaklumatPenerimaan";
			document.${formName}.command2.value = "kemaskiniHakmilik";
			document.${formName}.command3.value = "updateHakmilik";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
		document.${formName}.submit();
	}
}
function viewPenerimaanHakmilik(id_permohonan) {
	document.${formName}.paging.value = "19";
	document.${formName}.tabId.value = "1";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewPenerimaanHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "MaklumatPenerimaan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.carianNoLot2.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "MaklumatPenerimaan";
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