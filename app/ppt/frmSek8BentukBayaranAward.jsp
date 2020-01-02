<fieldset>
<legend><strong>Maklumat Bayaran Pampasan</strong></legend>
	
	#if($mode=="new")
	<table width="100%" border="0">	
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="37%">No. Lot</td>
			<td width="1%">:</td>
			<td width="61%">$!lblNoLot</td>
		</tr>
		<tr>
			<td><font color="red">*</font></td>
			<td>Nama PB</td>
			<td>:</td>
			<td>$!selectPB</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Bahagian / Syer</td>
			<td>:</td>
			<td><input readonly class="disabled" type="text" name="txtSyorAtas" id="txtSyorAtas" value="$!lblSyorAtas" size="5" maxlength="11" >&nbsp;<b>/</b>&nbsp;<input readonly class="disabled" type="text" name="txtSyorBawah" id="txtSyorBawah" value="$!lblSyorBawah" size="5" maxlength="11""></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Jumlah Bayaran Award (RM)</td>
			<td>:</td>
			<td><input readonly class="disabled" style="text-align:right" type="text" name="" id="" value="$!lblJumlahAward" size="16" maxlength="20"></td>
		</tr>
		<tr>
			<td><font color="red">*</font></td>
			<td>Bayaran Award / Surat Tawaran Award</td>
			<td>:</td>
			<td><input id="fileBayaran" name="fileBayaran" type="file" size="43" /></td>
		</tr>
	</table>
	#end
	
	
	
	#if($mode=="view")
	
	#foreach($dataB in $dataBentukPampasan)
		#set($lblNoLot = $dataB.no_lot)
		#set($lblNamaPB = $dataB.nama_pb)
		#set($lblLuasAmbil = $dataB.luas_ambil)
		#set($lblKodLuas = $dataB.unit_luas)
		#set($lblSyorAtas = $dataB.syer_atas)
		#set($lblSyorBawah = $dataB.syer_bawah)
		#set($lblTawaran = $dataB.tawaran)
		#set($lblJumlahAward = $dataB.bayar_award)
		#set($lblNamaFail = $dataB.nama_fail)
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
			<td width="1%">&nbsp;</td>
			<td width="37%">No. Lot</td>
			<td width="1%">:</td>
			<td width="61%">$!lblNoLot</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Nama PB</td>
			<td>:</td>
			<td>$!lblNamaPB</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Bahagian / Syer</td>
			<td>:</td>
			<td>$!lblSyorAtas <b>/</b>&nbsp;$!lblSyorBawah</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Jumlah Bayaran Award</td>
			<td>:</td>
			<td>RM&nbsp;$!lblJumlahAward</td>
		</tr>
		<tr>
			<td><font color="red">$M</font></td>
			<td>Bayaran Award / Surat Tawaran Award</td>
			<td>:</td>
			#if($isEdit=="no")
			<td><input readonly class="disabled" type="text" name="" id="" value="$!lblNamaFail" size="45"></td>
			#else
			<td><input id="fileBayaran" name="fileBayaran" type="file" size="43" /></td>
			#end
		</tr>
	</table>
	#end
	
</fieldset>
	
			<table width="100%" border="0">
				<tr align="center">
					<td>
					#if($mode=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanBentukBayaran('$!id_permohonan','$!id_hakmilik','$!id_award','$!mode')">
					#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBentukBayaran('$!id_award')">
						<input type="button" name="cmdHapusH" value="Hapus" onClick="hapusDokumen('$!id_award')" >
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBentukBayaran('$!id_permohonan','$!id_hakmilik','$!id_award','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batakKemaskini('$!id_award')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembaliListBentukPampasan('$!id_hakmilik','$!id_siasatan')">
					</td>
				</tr>
			</table>

			
	<fieldset id="bottom">
	<legend><strong>Jadual</strong></legend>
		
		#if($mode=="view")
    	<table width="100%" border="0">
    		<tr>
    			<td colspan="2"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahBentukBayaran('$!id_hakmilik')"></td>
    		</tr>
    	</table>
  		#end
    			
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
			
<input type="hidden" name="pagingPampasan">
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
function batakKemaskini(idAward) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_award.value = idAward;
	document.${formName}.command.value = "viewBentukBayaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function hapusDokumen(idAward) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_award.value = idAward;
	document.${formName}.command.value = "hapusDokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kemaskiniBentukBayaran(idAward) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_award.value = idAward;
	document.${formName}.command.value = "viewBentukBayaran";
	document.${formName}.command2.value = "kemaskiniBentukBayaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewBentukBayaran(idAward) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_award.value = idAward;
	document.${formName}.command.value = "viewBentukBayaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function tambahBentukBayaran(idHakmilik) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahBentukBayaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function simpanBentukBayaran(idPermohonan,idHakmilik,idAward,mode) {

	if(mode == "new"  && (document.${formName}.socPB.value == "")){

		alert("Sila pilih \"Nama PB\" terlebih dahulu.");
  		document.${formName}.socPB.focus(); 
		return;

	}else if(document.${formName}.fileBayaran.value == ""){

		alert("Sila pilih \"Dokumen\" yang hendak dimuatnaik terlebih dahulu.");
  		document.${formName}.fileBayaran.focus(); 
		return;
			
	}else{

		if ( !window.confirm("Adakah Anda Pasti?")) return;

		var dp = document.${formName}.form_token.value ;

		var commands = "";
		var idpb = "";
		
		if(mode == "new" ){
			idpb = document.${formName}.socPB.value ;
			commands = "&command=tambahBentukBayaran&command2=PBgetAward&command3=simpanBentukBayaran";
		}else{
			commands = "&command=viewBentukBayaran&command2=kemaskiniBentukBayaran&command3=updateBentukBayaran";
		}
		
		var ids = "&id_permohonan="+idPermohonan+"&id_hakmilik="+idHakmilik+"&id_award="+idAward;
		var att = "&ScreenLocation=bottom&pagingPampasan=2";
		var dopost = "&form_token="+dp;
		var data = "&id_pihakberkepentingan="+idpb;

		var actionItem = (commands+ids+att+data+dopost);
		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.encoding = "multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan"+actionItem;
		document.${formName}.submit();
	}
}
function PBgetAward() {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.pagingPampasan.value = "2";
	document.${formName}.command.value = "tambahBentukBayaran";
	document.${formName}.command2.value = "PBgetAward";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kembaliListBentukPampasan(idHakmilik,idSiasatan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.pagingPampasan.value = "2";
	document.${formName}.id_siasatan.value = idSiasatan;
	document.${formName}.command.value = "bentukBayaran";
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
			
			