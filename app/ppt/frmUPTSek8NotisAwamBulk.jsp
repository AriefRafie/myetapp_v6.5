#parse("app/ppt/frmLabelSet.jsp")
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<br/>

	#set($disabledCB = "disabled")
	
	#if($mode=="new")
	
		#set($M = "*")
		#set($Mcb = "*")	
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")	
		#set($disabilitydropdown = "")
		#set($disabilitydropdownx = "")
		
		#if($onchangeLocation=="yes")
			#set($disabledCB = "")
		#else
			#set($disabledCB = "disabled")
		#end
	
	#else
		
		#set($Mcb = "")
		#set($disabilitydropdown = "disabled")
		#set($disabilitydropdownx = "class=disabled")
			
		#if($isEdit=="yes")
			#set($M = "*")
			#set($disability = "")
			#set($disabilityx = "")
			#set($disability1 = "")	
			#set($disabledCB = "")
		#else
			#set($M = "")
			#set($disability = "readonly")
			#set($disabilityx = "class=disabled")
			#set($disability1 = "disabled")
			#set($disabledCB = "disabled")
		#end	
		
		#if($onchangeEdit=="no")
			#foreach($data in $dataNotisInBulk)
				#set($txdTarikhKeluar = $data.TARIKH_KELUAR)
				#set($txdTarikhTampal = $data.TARIKH_TAMPAL)
				#set($socTempatTampal = $data.JENIS_TEMPAT_TAMPAL)
				#set($txtTempat = $data.TEMPAT)
			#end
		#end
		
	#end
	
	
<fieldset id="top">
<legend><strong>Maklumat Notis Borang E Yang Terlibat</strong></legend>
	
	 	
    	<table width="100%" border="0">         	
            <tr>
            	<td width="1%"><font color="red">$M</font></td>
            	<td width="24%">Tarikh Dikeluarkan / Endorsan</td>
            	<td width="1%">:</td>
                <td width="74%"><input $disability $disabilityx name="txdTarikhKeluar" id="txdTarikhKeluar" size="11" type="text" value="$!txdTarikhKeluar" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($mode=="new" || ($mode=="view" && $isEdit=="yes"))
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhKeluar',false,'dmy');">&nbsp;$!frmtdate
            	#end
            	</td>
            </tr>
        </table>
        
        
</fieldset>

<fieldset>

	<table width="100%" border="0"> 
		<tr>
            <td width="1%"><font color="red">$M</font></td>
            <td width="24%">Tarikh Tampal</td>
            <td width="1%">:</td>
            <td width="74%"><input $disability $disabilityx name="txdTarikhTampal" id="txdTarikhTampal" size="11" type="text" value="$!txdTarikhTampal" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this);checkTarikhSiasatan()" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))
            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTampal',false,'dmy');">&nbsp;$!frmtdate
            #end
            </td>
        </tr>
		<tr>
        	<td><font color="red">$Mcb</font></td>
            <td>Tempat Tampal</td>
            <td>:</td>
            <td><select $disabilitydropdown $disabilitydropdownx name="socTempatTampal" style="width:auto" onchange="javascript:onchangeLocation()" >
      			
      			<option #if($socTempatTampal=="") selected=selected #end value="">SILA PILIH</option>    
      			<option #if($socTempatTampal=="1") selected=selected #end value="1">PTD / PTG</option>
      			<option #if($socTempatTampal=="2") selected=selected #end value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>
      			<option #if($socTempatTampal=="3") selected=selected #end value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>
      		
			</select></td>
      	</tr>
      	<tr>
			<td valign="top"><font color="red">$M</font></td>
			<td valign="top">Keterangan Tempat</td>
			<td valign="top">:</td>
			<td valign="top"><textarea $disability $disabilityx name="txtTempat" id="txtTempat" cols="45%" rows="4" onKeyUp="textCounter(this.form.txtTempat,this.form.remLen3,400);" onKeyDown="textCounter(this.form.txtTempat,this.form.remLen3,400);" >$!txtTempat</textarea></td>
		</tr>
		
		#if($mode=="new" || ($mode=="view" && $isEdit=="yes"))
		<tr>
        	<td colspan="3">&nbsp;</td>
            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="400"></td>
        </tr>  
        #end
         
	</table> 
    
    
		
	
	<fieldset id="center">
	<legend><strong><font color="red">$M</font> Senarai Pilihan Hakmilik</strong></legend>
	
	<table width="100%"  cellpadding="0" cellspacing="2" border="0">
		
		<tr class="table_header">
			#if($saiz_listHakmilikNotisInBulk != 0)
        	<td align="center" width="4%"><b><input $disabledCB type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></b></td>
        	#end
       		<td align="center"><b>No</b></td>
            <td><b>No.Hakmilik</b></td>
           	<td><b>No.LOT/No.PT</b></td>              
            <td><b>Mukim/Pekan/Bandar</b></td>
            #if($!flag_subjaket!="")<td><b>No.Subjaket</b></td>#end
        </tr>
                    
   		#if($saiz_listHakmilikNotisInBulk!=0)
      		#foreach($listTanah in $listHakmilikNotisInBulk)
            #set( $i = $velocityCount )
         	#if ( ($i % 2) != 1 )
           		#set( $row = "row2" )
	        #else
	            #set( $row = "row1" )
	       	#end
        
        #if($listTanah.selectedcb > 0)
        	#set($checkCB = "checked")
        #else
        	#set($checkCB = "")
        #end
                    	
       	<tr>
       		#if($saiz_listHakmilikNotisInBulk != 0)
            <td class="$row" align="center"><input $disabledCB $checkCB type="checkbox" name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listTanah.id_hakmilik"></td>
            #end
           	<td class="$row" align="center">$!listTanah.bil</td>
            <td class="$row">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</td>
			<td class="$row">$!listTanah.no_lotpt</td>     
            <td class="$row">$!listTanah.nama_mukim #if($listTanah.seksyen!="")<font style=font-size:10px>Seksyen $listTanah.seksyen</font>#end</td>
       		#if($!flag_subjaket!="")<td class="$row">Sj.$!listTanah.no_subjaket</td>#end
        </tr>
        	#end
        #else
        <tr>
           	<td colspan="2">Tiada rekod</td>
       	</tr>
        #end  
        
    </table>	
                
	</fieldset>
	
	
	#if($mode=="new" || ($mode=="view" && $isEdit=="yes"))	
	<table width="100%" align="left">
		<tr>
			<td>$perhatian5</td>
		</tr>
	</table>
	#end	

</fieldset>

	<table width="80%" border="0" align="left">
		<tr align="center">
			<td>
			#if($mode=="new")	
			<input type="button" value="Simpan" name="cmdSimpan" onClick="javascript:simpanNotisInBulk('','$!id_permohonan','$!saiz_listHakmilikNotisInBulk','$!mode')"> 
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniNotisInBulk('$!id_notisawam')">
				<input type="button" name="cmdHapus" value="Hapus Semua Pilihan Dan Maklumat" onClick="hapusNotis('$!id_notisawam')" >
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanNotisInBulk('$!id_notisawam','$!id_permohonan','$!saiz_listHakmilikNotisInBulk','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batal('$!id_notisawam')">
				#end
			#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListNotis('$!id_permohonan')">
			</td>
		</tr>
	</table>	

<br/><br/>
<!--
#if($!id_permohonan != "") 
    <fieldset id="bottom">
	<legend><strong>&nbsp;MUATNAIK BORANG SABPN  <input type="button" name="button" id="button" value="Cetak SABPN" onClick="javascript:cetakSABPN('$id_permohonan','sabpn_notis_awam_sek8')" /></strong></legend>
                
               
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
     <a href="javascript:popupCarianDokumen('$id_permohonan','notis_awam_sek8')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
    </td>
    </tr>
    </table>
    
   
	</fieldset>	
    #end
<br/>
-->
<fieldset id="bottom">
	<legend><strong>Senarai Notis Yang Telah Direkodkan</strong></legend>
	<table width="100%"  cellpadding="0" cellspacing="2" border="0">
		
		#if($mode=="view")
		<tr>
			<td colspan='4' align='left'><input type="button" name="cmdMainscreen" value="Kemasukan Maklumat Notis" onClick="javascript:daftarMaklumatNotis();"></td>
		</tr>
		#end
		
		<tr class="table_header">
       		<td align="center"><b>No</b></td>
            <td><b>Keterangan Tempat</b></td>
           	<td><b>Tempat Tampal</b></td>              
            <td><b>Tarikh Tampal</b></td>
            <td align="center"><b>Senarai Lot</b></td>
        </tr>
                    
   		#if($saiz_listNotisInBulk!=0)
      		#foreach($listN in $listNotisInBulk)
            #set( $i = $velocityCount )
         	#if ( ($i % 2) != 1 )
           		#set( $row = "row2" )
	        #else
	            #set( $row = "row1" )
	       	#end
         	
       	<tr>
           	<td class="$row" align="center">$!listN.bil</td>
            <td class="$row">
            <a href="javascript:viewNotisInBulk('$!listN.ID_NOTISAWAM')"><font color="blue">
            
             
                   $listN.TEMPAT
                 
            </font></a></td>
			<td class="$row">$!listN.JENIS_NAMA_TEMPAT_TAMPAL</td>  
			<td class="$row">$!listN.TARIKH_TAMPAL</td>  
			<td align="center" class="$row"><a href="javascript:viewPopupLot('$!listN.ID_NOTISAWAM','$!id_permohonan')"><font color="blue"><b>$!listN.TOTALHM</b></font></a></td> 
        </tr>
        	#end
        #else
        <tr>
           	<td colspan="2">Tiada rekod</td>
       	</tr>
        #end  
        
    </table>
</fieldset>	

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_notisawam" value="$!id_notisawam">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
function cetakSABPN(id_permohonan,jenis_report) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+id_permohonan+"&report="+jenis_report+"&selectNoFail=yes";	
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
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

function viewPopupLot(id_notisawam,id_permohonan){
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmMyInfoPopupPegawaiBertugas?id_notisawam="+id_notisawam+"&type=notis&id_permohonan="+id_permohonan;
	var hWnd = window.open(url,'Senarai Lot','width=800,height=400, resizable=yes,scrollbars=yes');	
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function kemaskiniNotisInBulk(id_notisawam){	
	document.${formName}.id_notisawam.value = id_notisawam;
	document.${formName}.command.value = "viewNotisInBulk";
	document.${formName}.command2.value = "kemaskiniNotisInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();	
}
function hapusNotis(id_notisawam){	
	if ( !window.confirm("Adakah Anda Pasti?")) return;	
	document.${formName}.id_notisawam.value = id_notisawam;
	document.${formName}.command.value = "viewNotisInBulk";
	document.${formName}.command2.value = "hapusNotisInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();	
}
function daftarMaklumatNotis(){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahNotisInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function viewNotisInBulk(id_notisawam){	
	document.${formName}.id_notisawam.value = id_notisawam;
	document.${formName}.command.value = "viewNotisInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();		
}
function onchangeLocation(){	
	document.${formName}.command.value = "tambahNotisInBulk";
	document.${formName}.command2.value = "onchangeLocation";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();		
}

function simpanNotisInBulk(id_notisawam,id_permohonan,size,mode){	

	
	
	var checkSelected=false;
	if(size>1){
		for(var i=0 ; i < document.${formName}.cbsemaks.length; i++) 
		{ 
    		if (document.${formName}.cbsemaks[i].checked)
        	{
  				checkSelected=true; 
  			}
		}
	}else{
		if (document.${formName}.cbsemaks.checked)
    	{
			checkSelected=true; 
    	}
	}
	
	var dat1=document.${formName}.txdTarikhKeluar
	var dat3=document.${formName}.txdTarikhTampal
	
	
	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat
	var TS  = document.getElementById("txdTarikhTampal").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10)-1;
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateTampal = new Date(yr1, mon1, dt1);

   	
	if(document.${formName}.txdTarikhKeluar.value == ""){
		alert("Sila masukkan \"Tarikh Endorsan\" terlebih dahulu.");
	  	document.${formName}.txdTarikhKeluar.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		//return;
	}
	else
	if(document.${formName}.txdTarikhTampal.value == ""){
		alert("Sila masukkan \"Tarikh Tampal\" terlebih dahulu.");
		document.${formName}.txdTarikhTampal.focus(); 
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		//return;
	}
	else
	if(document.${formName}.socTempatTampal.value == ""){
		alert("Sila pilih \"Tempat Tampal\" terlebih dahulu.");
		document.${formName}.socTempatTampal.focus(); 
		return;
	}
	else
	if(document.${formName}.txtTempat.value == ""){
		alert("Sila masukkan \"Tempat\" terlebih dahulu.");
		document.${formName}.txtTempat.focus(); 
		return;
	}
	else if(!checkSelected){
		alert("Sila pilih \"Hakmilik\" terlebih dahulu.");
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;	
		document.${formName}.id_permohonan.value = id_permohonan;
		if(mode=="new"){
			document.${formName}.command.value = "tambahNotisInBulk";
			document.${formName}.command2.value = "simpanNotisInBulk";
		}else{
			document.${formName}.id_notisawam.value = id_notisawam;
			document.${formName}.command.value = "viewNotisInBulk";
			document.${formName}.command2.value = "kemaskiniNotisInBulk";
			document.${formName}.command3.value = "updateNotisInBulk";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
		document.${formName}.submit();		
	}
}
function checkTarikhSiasatan(){	

	var dat1=document.${formName}.txdTarikhTampal
	var dat2=document.${formName}.lblTarikhSiasatan
	var tarikhs=document.${formName}.lblTarikhSiasatan.value;

	//tarikh siasatan
	var TS  = document.getElementById("lblTarikhSiasatan").value;
	var dt1   = parseInt(TS.substring(0,2),10)+21;
   	var mon1  = parseInt(TS.substring(3,5),10)-1;
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateSiasatan = new Date(yr1, mon1, dt1);

  	//tarikh surat
	var TSR  = document.getElementById("txdTarikhTampal").value;
	var dt2   = parseInt(TSR.substring(0,2),10);
   	var mon2  = parseInt(TSR.substring(3,5),10)-1;
   	var yr2   = parseInt(TSR.substring(6,10),10);
   	var dateTampal = new Date(yr2, mon2, dt2);
   	
   	if((dat1.value!="" && dat2.value!="") && (isDate(dat1.value)==true) && (dateTampal > dateSiasatan)){
	   	alert("Sila pastikan \"Tarikh Tampal\" tidak kurang 21 hari dari tarikh siasatan iaitu pada "+tarikhs+".");
		document.${formName}.txdTarikhTampal.focus();
		return;
	}
	
}
function viewListNotis(id_permohonan) {

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function checkALL() {

	 if (document.${formName}.checkall.checked == true){
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = true;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = true;
	            }
	        }
	    } else {
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = false;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = false;	                
	            }
	            
	        }
	    }
}
function doUpdateCheckAll(){  

	var c = 0;
	if(document.${formName}.cbsemaks.length > 1){     
		
		for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	      if (document.${formName}.cbsemaks[i].checked == false){	 
		  	c++
	      }
		}  

	}else{
		
		if (document.${formName}.cbsemaks.checked == false){				 
			c++;
		}	 	
	}	 
	 
	if(c>0){
			  
		document.${formName}.checkall.checked = false;
	}
	else{
		document.${formName}.checkall.checked = true;
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
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
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