
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<br/>
<fieldset>
  <legend><strong>Maklumat Penampalan Notis Awam Seksyen 4</strong></legend>

	<table width="100%" cellspacing="1" border="0">
	#if($isNew=="yes")
     
 		 	<tr>
			  <td width="25%"><font color="red">*</font>Tempat Tampal</td>
			  <td width="1%">:&nbsp;</td>
  				<td width="74%"><select name="jenis_tempat_tampal" style="width:auto">
  				
  				  <option value="">SILA PILIH</option> 				 
  				  #if($flag1!="1")<option value="1">PTD/PTG</option>#end
  				  #if($flag2!="1")<option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM/BANDAR</option>#end
  				  #if($flag3!="1")<option value="3">TEMPAT LAIN DI ATAS/BERHAMPIRAN TANAH</option>#end
  			
                </select></td>
 	   		</tr>
 	   				
 		 	<tr>
 		 	  <td valign="top"><font color="red">*</font>Tempat</td>
 		 	  <td valign="top">:&nbsp;</td>
 		 	  <td><textarea name="tempat" rows="4" cols="40%" style="text-transform:uppercase;" onkeyup="this.value=this.value.toUpperCase();"></textarea></td>
 	   		</tr>
 		 	
 		 	<tr>
 		 	  <td><font color="red">*</font>Tarikh Tampal</td>
 		 	  <td>:&nbsp;</td>
 		 	  <td><input type="text" name="tarikh_tampal" onblur="check_date(this)" size="12" id="tarikh_tampal">
              <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('tarikh_tampal',false,'dmy');" style="display:$Style2">$!frmtdate</td>
 	   		</tr>
	 
	#else
	
	#foreach ($notis in $NotisAwamList)
		#set($tempat = $notis.tempat)
		#set($tarikhTampal = $notis.tarikh_tampal)
	#end
	
	#if($isEditNotis=="no")
		#set($disabled="readonly")
		#set($disabled1="disabled")
		#set($disabledX="class=disabled")
	#else
		#set($disabled="")
		#set($disabled1="")
		#set($disabledX="")
	#end
	
	<input type="hidden" name="jenis_tempat_tampal" value="$!prosesWartaDropdown">
	
			<tr>
				<td width="25%">Tempat Tampal</td>
				<td width="1%">:&nbsp;</td>
  				<td width="74%"><select name="jenis_tempat_tampalTMP"  style="width:auto" disabled class="disabled" > 				
  						#if($prosesWartaDropdown=="1")
  							<option value="1">PTD/PTG</option>
  							<option value="">SILA PILIH</option>
  							<option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM/BANDAR</option>
  				  			<option value="3">TEMPAT LAIN DI ATAS/BERHAMPIRAN TANAH</option>
  						#elseif($prosesWartaDropdown=="2")
  							<option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM/BANDAR</option>
  							<option value="">SILA PILIH</option>
  							<option value="1">PTD/PTG</option>
  				  			<option value="3">TEMPAT LAIN DI ATAS/BERHAMPIRAN TANAH</option>
  						#elseif($prosesWartaDropdown=="3")
  							<option value="3">TEMPAT LAIN DI ATAS/BERHAMPIRAN TANAH</option>
  							<option value="">SILA PILIH</option>
  							<option value="1">PTD/PTG</option>
  				  			<option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM/BANDAR</option>
  				  		#else
  							<option value="">SILA PILIH</option>
  							<option value="1">PTD/PTG</option>
  				  			<option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM/BANDAR</option>
  				  			<option value="3">TEMPAT LAIN DI ATAS/BERHAMPIRAN TANAH</option>
  				  		#end
                </select></td>
 	   		</tr>
 		 	
 		 	<tr>
 		 	  <td valign="top"><font color="red">#if($isEditNotis=="yes")*#end</font>Tempat</td>
 		 	  <td valign="top">:&nbsp;</td>
 		 	  <td><textarea name="tempat" rows="4" cols="40%" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $disabledX $disabled >$!tempat</textarea></td>
 	   		</tr>
 		 	
 		 	<tr>
 		 	  <td><font color="red">#if($isEditNotis=="yes")*#end</font>Tarikh Tampal</td>
 		 	  <td>:&nbsp;</td>
 		 	  <td><input name="tarikh_tampal" type="text" size="12" onblur="check_date(this)" value="$!tarikhTampal" $disabledX $disabled >
 		 	  #if($isEditNotis=="yes")<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('tarikh_tampal',false,'dmy');" >$!frmtdate #end</td>
 	   		</tr>
	 
	#end 
	</table>
	
</fieldset>   
     
     <table align="center">
     	<tr align="center">
     		<td>
     		#if($isNew=="yes")
       			<input name="" type="button" value="Simpan" onclick="add_Notis_Awam('$id_permohonan')">
       		#else
       			#if($isEditNotis=="no")
       				#if($status!="52")
 					<input name="" type="button" value="Kemaskini" onClick="kemaskini('$!id_notisawam')">
 					<input name="" type="button" value="Hapus" onClick="hapusTampal('$!id_notisawam')">
       				#end
       			#else
       				<input name="" type="button" value="Simpan" onclick="edit_Notis('$!id_notisawam')">
       				<input name="" type="button" value="Batal" onclick="batal('$id_notisawam')">
       			#end	
       		#end
       		<input name="" type="button" value="Kembali" onclick="kembali('$id_permohonan')">
       		
       		</td>
       	</tr>
     </table>  	
       
       	
<!-- Flag Tampal Notis -->
<input type="hidden" name="flag1">
<input type="hidden" name="flag2">
<input type="hidden" name="flag3">      
 	
<input name="skrin2" type="hidden">
<input name="skrin3" type="hidden">
<input name="skrin4" type="hidden">
<input name="skrin5" type="hidden">
<input name="id_permohonan" type="hidden" value="$!id_permohonan">
<input name="id_notisawam" type="hidden" value="$!id_notisawam">
  
<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'> 

<script>
function hapusTampal(id_notisawam) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_notisawam.value = id_notisawam;
	document.${formName}.command.value = "hapusTampal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
	document.${formName}.submit();
}
function add_Notis_Awam(id_permohonan) {

	var dat1=document.${formName}.tarikh_tampal
	
	if(document.${formName}.jenis_tempat_tampal.value == ""){
		alert("Sila pilih \"Jenis Tempat Tampal\" terlebih dahulu.");
  		document.${formName}.jenis_tempat_tampal.focus(); 
		return;
	}
	else if(document.${formName}.tempat.value == ""){
		alert("Sila masukkan \"Tempat\" terlebih dahulu.");
  		document.${formName}.tempat.focus(); 
		return;
	}
	else if(document.${formName}.tarikh_tampal.value == ""){
		alert("Sila masukkan \"Tarikh Tampal\" terlebih dahulu.");
  		document.${formName}.tarikh_tampal.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.method = "post";		
		document.${formName}.command.value = "Semak2";	
		document.${formName}.skrin2.value = "TambahNotisTampal";
		document.${formName}.skrin3.value = "SimpanNotisTampal";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
		document.${formName}.submit();
	}
}
function kembali(id_permohonan) {
	document.${formName}.command.value = "Semak2";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
	document.${formName}.submit();
}
function kemaskini(id_notisawam) {
	document.${formName}.id_notisawam.value = id_notisawam;
	document.${formName}.command.value = "Semak2";	
	document.${formName}.skrin2.value = "semakNotisTampal";
	document.${formName}.skrin3.value = "KemaskiniNotisTampal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
	document.${formName}.submit();
}
function edit_Notis(id_notisawam) {


	var dat1=document.${formName}.tarikh_tampal
	
/*	if(document.${formName}.jenis_tempat_tampal.value == ""){
		alert("Sila pilih \"Jenis Tempat Tampal\" terlebih dahulu.");
  		document.${formName}.jenis_tempat_tampal.focus(); 
		return;
	}
*/	if(document.${formName}.tempat.value == ""){
		alert("Sila masukkan \"Tempat\" terlebih dahulu.");
  		document.${formName}.tempat.focus(); 
		return;
	}
	else if(document.${formName}.tarikh_tampal.value == ""){
		alert("Sila masukkan \"Tarikh Tampal\" terlebih dahulu.");
  		document.${formName}.tarikh_tampal.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.id_notisawam.value = id_notisawam;
		document.${formName}.command.value = "Semak2";		
		document.${formName}.skrin2.value = "semakNotisTampal";
		document.${formName}.skrin3.value = "KemaskiniNotisTampal";
		document.${formName}.skrin4.value = "UpdateNotisTampal";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
		document.${formName}.submit();
	}
}
function batal(id_notisawam) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_notisawam.value = id_notisawam;
	document.${formName}.command.value = "Semak2";
	document.${formName}.skrin2.value = "semakNotisTampal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
	document.${formName}.submit();
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