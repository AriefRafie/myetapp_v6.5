#parse("app/ppt/frmLabelSet.jsp")
#parse("app/ppt/Sek4Paging.jsp")

<fieldset id="top">
<legend><strong>Maklumat Warta dan Notis</strong></legend>
<center>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	#set($sorProses="")
	
	#foreach($data in $dataWarta)
		#set($sorProses=$data.proses_warta)
		#set($txtNoWarta=$data.no_warta)
		#set($txdTarikhWarta=$data.tarikh_warta)
	#end
	
	#if($isEditWarta=="yes")
		#set($disability="")
		#set($disability1="")
		#set($disabilityx="")
	#else
		#set($disability="readonly")
		#set($disability1="disabled")
		#set($disabilityx="class=disabled")
	#end

	#if($sorProses=="1")
		#set($checkA = "checked")
		#set($checkB = "")
	#elseif($sorProses=="2")
		#set($checkA = "")
		#set($checkB = "checked")
	#else
		#set($checkA = "")
		#set($checkB = "")
	#end

	<fieldset>
    <legend><strong>Maklumat Warta</strong></legend>
    	
    	<table width="100%" cellspacing="0" border="0">
 
     		<tr>
     			<td width="1%">#if($isEditWarta=="yes")<font color="red">*</font>#else&nbsp;#end</td>
			  	<td width="25%">Proses diwarta oleh</td>
			  	<td width="1%">:</td>
  				<td width="73%"><input name="sorProses" $checkA $disability1 type="radio" id="sorPTD" value="1">CAWANGAN JKPTG</td>   
			</tr>
 		 	
 		 	<tr>
 		 	  	<td colspan="3">&nbsp;</td>
 		 	  	<td><input $checkB $disability1 name="sorProses" type="radio" id="sorPTG" value="2">PTG</td> 
			</tr>
 		 	
 		 	<tr>
 		 	  <td>#if($isEditWarta=="yes")<font color="red">*</font>#else&nbsp;#end</td>	
 		 	  <td>No.Warta </td>
 		 	  <td>:</td>
 		 	  <td>&nbsp;<input type="text" $disability $disabilityx name="txtNoWarta" id="txtNoWarta" maxlength="20" size="20" value="$!txtNoWarta"></td> 
			</tr>
 		 	
 		 	<tr>
 		 		<td>#if($isEditWarta=="yes")<font color="red">*</font>#else&nbsp;#end</td>
 		 	  	<td>Tarikh Warta</td>
 		 	 	<td>:</td>
 		 	  	<td>&nbsp;<input type="text" $disability $disabilityx name="txdTarikhWarta" id="txdTarikhWarta" maxlength="10" size="12" value="$!txdTarikhWarta" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
 		 	 	#if($isEditWarta=="yes")<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhWarta',false,'dmy');" >$!frmtdate#end</td>
			</tr>

	 	</table>
	 	
	 	#if($isEditWarta=="yes")
	 	<table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian3</td>
        	</tr>
        </table>
        #end
	 	
 	</fieldset>
 	 
 	<br/>
    
    <fieldset id="bottom">
	<legend><strong>&nbsp;MUATNAIK DOKUMEN BERKAITAN</strong></legend>              
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
     <a href="javascript:popupCarianDokumen('$id_permohonan','warta')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
    </td>
    </tr>
    </table>
    </fieldset>
    
	<br/>
    
    
    #if($!id_permohonan != "")



 <fieldset id="bottom">
	<legend><strong>&nbsp;MUATNAIK BORANG SABPN  <input type="button" name="button" id="button" value="Cetak SABPN" onClick="javascript:cetakSABPN('$id_permohonan','sabpn_notis_awam_sek4')" /></strong></legend>
                
               
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
     <a href="javascript:popupCarianDokumenNotis('$id_permohonan','notis_awam_sek4')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
    </td>
    </tr>
    </table>
    
    	
	<!--
            #parse("app/ppt/frmCarianListHMSek8.jsp")
            #parse("app/ppt/frmSeksyen8ListHM.jsp")
      -->      
	</fieldset>	
	#end

<br />
 	 
	<fieldset id="bottom">
	<legend><strong>Senarai Maklumat Notis</strong></legend>
	
		#set($flag1 = "")
		#set($flag2 = "")
		#set($flag3 = "")
	
		#foreach($dt in $listNotis)
		#set($flag1 = $dt.flag1)
		#set($flag2 = $dt.flag2)
		#set($flag3 = $dt.flag3)
		#end

		#if($flag1 == "1" && $flag2 == "1" && $flag3 == "1")
 		 	#set($hide="1")
 		#else
 		 	#set($hide="0")
 		#end
		
		#if($hide=="0")
		<table width="100%" border="0">   
              <tr>
                 <td width="30%" align="left"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahNotis('$!id_permohonan');"></td>
              </tr>
    	</table>
    	#end
	
		#if($saiz_listNotis > 5)
                <div style="width:100%;height:100;overflow:auto"> 
        #end
	
		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td width="36%"><b>Tempat Tampal</b></td>
        			<td width="46%"><b>Keterangan Tempat</b></td>
        			<td width="14%"><b>Tarikh Tampal</b></td>
        		</tr>
        		
        		#if($saiz_listNotis!=0)
                    #foreach($list in $listNotis)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!list.bil</td>
                   <td class="$row"><a href="javascript:viewNotis('$!list.id_notisawam')"><font color="blue">$!list.jenis_tempat</font></a></td>
                   <td class="$row">$!list.tempat</td>
                   <td class="$row">$!list.tarikh_tampal</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		</table>
			
		#if($saiz_listNotis > 5)
        	</div>
        #end	
		
	</fieldset>

#set($noupdate = "")	

#if($tarikhBorangB!="")
	#set($noupdate = "yes")
#else
	#set($noupdate = "no")	
#end   

#set($completed="")

#if($sorProses!="" && $txtNoWarta!="" && $txdTarikhWarta!="")
 	#set($completed="yes")
#else
	#set($completed="no") 	
#end

	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($isEditWarta=="no")
				<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="javascript:kemaskiniWarta('$!id_permohonan');">
                
                
                #if($ID_NEGERIPROJEK == "4" || $ID_NEGERIPROJEK == "5" )          
                <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Hantar Borang B & Maklumat Warta)" onClick="popupEtanah('$id_fail','$id_permohonan','WartaS4','')">
                #end
                
				<!-- #if($hide!="0") #end -->	
					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
						#if($id_status==31)
						#if($tarikhBorangB!="")
						<input name="cmdHantar" type="button" value="Selesai" onClick="Hantar('$id_permohonan','$!completed')">
						#end
						#end
				#else
				<input type="button" name="cmdUpdate" value="Simpan" onClick="javascript:updateWarta('$!id_warta');">
				<input type="button" name="cmdBatal" value="Batal" onClick="javascript:batal('$!id_permohonan');">
				#end
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
			</td>
		</tr>
	</table> 
	
</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBorangB('$!id_permohonan','$!noupdate','$!completed')"><font color="blue">Borang B</font></a></td>
      </tr>     
    </table>
</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_fail" value="$!id_fail">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_warta" value="$!id_warta">
<input type="hidden" name="id_notisawam">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="tarikh_mmk" id="tarikh_mmk" value="$!tarikh_mmk">

<input type="hidden" name="flag1">
<input type="hidden" name="flag2">
<input type="hidden" name="flag3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function popupCarianDokumenNotis(id_permohonan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}


function cetakSABPN(id_permohonan,jenis_report) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+id_permohonan+"&report="+jenis_report+"&selectNoFail=yes";	
    //var url = "../servlet/ekptg.report.ppt.BorangA?idfail="+idfail+"&namaMukim="+nama2Mukim;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function popupEtanah(id_fail,id_permohonan,jenis_skrin,command) {

	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&jenis_skrin="+jenis_skrin+"&command="+command;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}


function popupCarianDokumen(id_permohonan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function Hantar(id_permohonan,mode) {

	if(mode=="no"){
		alert("Sila lengkapkan maklumat warta terlebih dahulu");
	}
	else{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "bottom";
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.command.value = "hantar";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam"; 
		document.${formName}.submit();	
	}
}
function batal(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam";
	document.${formName}.submit();
}
function updateWarta(idWarta){	

	//tarikh mmk
	var TMK  = document.getElementById("tarikh_mmk").value;
	var dtMK   = parseInt(TMK.substring(0,2),10);
   	var monMK  = parseInt(TMK.substring(3,5),10);
   	var yrMK   = parseInt(TMK.substring(6,10),10);
   	var dateMMK = new Date(yrMK, monMK, dtMK);
   	
	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh warta
	var TW  = document.getElementById("txdTarikhWarta").value;
	var dt1   = parseInt(TW.substring(0,2),10);
   	var mon1  = parseInt(TW.substring(3,5),10);
   	var yr1   = parseInt(TW.substring(6,10),10);
   	var dateWarta = new Date(yr1, mon1, dt1);

   	var dat1=document.${formName}.txdTarikhWarta
   	
	var radioSelected = false;
	for (i = 0;  i < ${formName}.sorProses.length;  i++){
		if (${formName}.sorProses[i].checked)
		radioSelected = true;
	}

	if (!radioSelected){
		alert("Sila pilih \"Proses Warta\" terlebih dahulu.");
		return (false);
	}
	else 
	if(document.${formName}.txtNoWarta.value == ""){
		alert("Sila masukkan \"No.Warta\" terlebih dahulu.");
  		document.${formName}.txtNoWarta.focus(); 
		return;
	}
	else 
	if(document.${formName}.txdTarikhWarta.value == ""){
		alert("Sila masukkan \"Tarikh Warta\" terlebih dahulu.");
	  	document.${formName}.txdTarikhWarta.focus(); 
		return;
	}	
	else 
	if(dateWarta < dateMMK){
		alert("Sila pastikan \"Tarikh Warta\" tidak kurang dari tarikh \"Tarikh Kelulusan MMK\".");
		document.${formName}.txdTarikhWarta.focus();
		return;
	}
/*	else 
	if(dateWarta < currentDate){
		alert("Sila pastikan \"Tarikh Warta\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhWarta.focus();
		return;
	}
*/	else 
	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "bottom";
		document.${formName}.id_warta.value = idWarta;
		document.${formName}.command.value = "viewListNotis";
		document.${formName}.command2.value = "kemaskiniWarta";
		document.${formName}.command3.value = "updateWarta";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam";
		document.${formName}.submit();
	}
}
function kemaskiniWarta(idPermohonan){	
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = idPermohonan;
	document.${formName}.command.value = "viewListNotis";
	document.${formName}.command2.value = "kemaskiniWarta";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam";
	document.${formName}.submit();
}
function viewNotis(idNotisAwam){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_notisawam.value = idNotisAwam;
	document.${formName}.command.value = "viewNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam";
	document.${formName}.submit();
}
function tambahNotis(idpermohonan){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam";
	document.${formName}.submit();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakBorangB(idpermohonan,noupdate,mode) {

	if(mode=="no"){

		alert("Sila lengkapkan maklumat warta terlebih dahulu");

	}else{

		if(noupdate=="no"){
			document.${formName}.command.value = "SimpanTarikhBorangB";
			document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam";		
			document.${formName}.submit();
		}

		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=BorangB&selectNoFail=yes&flagShowTarikhCetak=yes";	
   	 	//var url = "../servlet/ekptg.report.ppt.BorangB?idfail="+idfail+"&namaPegawai="+namaPengarah;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
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