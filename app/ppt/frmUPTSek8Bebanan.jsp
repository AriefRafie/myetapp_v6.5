
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")


<center>

#parse("app/ppt/frmPPTHeaderHM.jsp")
	
<br/>

	#if($mode=="new")	
	<fieldset id="changeBebanan">
	<legend><strong>Maklumat Bebanan</strong></legend>
	<table width="100%" border="0">
		<tr>
			<td width="25%"><font color="red">*</font>No. Perserahan</td>
			<td width="1%">:</td>
			<td width="74%"><input type="text" name="txtPerserahan" id="txtPerserahan" value="$!txtPerserahan" size="20" maxlength="30"   ></td>
		</tr>
		<tr>
			<td>Jenis Bebanan</td>
			<td>:</td>
			<td>$!selectBebanan</td>
		</tr>
		<tr>
			<td>Tarikh Daftar</td>
			<td>:</td>
			<td><input name="txdTarikhDaftar" id="txdTarikhDaftar" size="11" type="text" value="$!txdTarikhDaftar" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhDaftar',false,'dmy');">$!frmtdate</td>
		</tr>
	</table>
	</fieldset>
	
	<fieldset>
	<table width="100%" border="0">
	
		<tr>
			<td width="25%">Nama</td>
			<td width="1%">:</td>
			<td width="74%"><input type="text" name="txtNama" id="txtNama" value="$!txtNama" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>Kod No. PB</td>
			<td>:</td>
			<td>$!selectJenisNoPB</td>
		</tr>
		<tr>
			<td>No. PB</td>
			<td>:</td>
			<td><input type="text" name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20"   ></td>
		</tr>
		<tr>
			<td>Alamat</td>
			<td>:</td>
			<td><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>Poskod</td>
			<td>:</td>
			<td><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value);"></td>
		</tr>
		<tr>
			<td>Negeri</td>
			<td>:</td>
			<td>$!selectNegeri</td>
		</tr>
		<tr>
			<td>Bandar</td>
			<td>:</td>
			<td>$!selectBandar</td>
		</tr>		
	</table>
	</fieldset>
	#end

	
	#if($mode=="view")	
	
#if($onchange=="no")	
	#foreach($data in $dataBebanan)
		#set($txtPerserahan = $data.no_perserahan)
		#set($txtNama = $data.nama)
		#set($txtNoPB = $data.no_pb)
		#set($txtAlamat1 = $data.alamat1)
		#set($txtAlamat2 = $data.alamat2)
		#set($txtAlamat3 = $data.alamat3)
		#set($txtPoskod = $data.poskod)
		#set($txdTarikhDaftar=$data.tarikh_daftar)
	#end
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
	
	<fieldset id="changeBebanan">
	<legend><strong>Maklumat Bebanan</strong></legend>
		<table width="100%" border="0">
			<tr>
				<td width="25%"><font color="red">$!M</font>No. Perserahan</td>
				<td width="1%">:</td>
				<td width="74%"><input $disability $disabilityx type="text" name="txtPerserahan" id="txtPerserahan" value="$!txtPerserahan" size="20" maxlength="30"   ></td>
			</tr>
			<tr>
				<td>Jenis Bebanan</td>
				<td>:</td>
				<td>$!selectBebanan</td>
			</tr>
			<tr>
			<td>Tarikh Daftar</td>
			<td>:</td>
			<td><input $disability $disabilityx name="txdTarikhDaftar" id="txdTarikhDaftar" size="11" type="text" value="$!txdTarikhDaftar" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhDaftar',false,'dmy');">$!frmtdate#end</td>
		</tr>
		</table>
	</fieldset>
	
	<fieldset>
		<table width="100%" border="0">
	
			<tr>
				<td width="25%">Nama</td>
				<td width="1%">:</td>
				<td width="74%"><input $disability $disabilityx type="text" name="txtNama" id="txtNama" value="$!txtNama" size="50" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>Kod No. PB</td>
				<td>:</td>
				<td>$!selectJenisNoPB</td>
			</tr>
			<tr>
				<td>No. PB</td>
				<td>:</td>
				<td><input type="text" $disability $disabilityx name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20"   ></td>
			</tr>
			<tr>
				<td>Alamat</td>
				<td>:</td>
				<td><input type="text" $disability $disabilityx name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input type="text" $disability $disabilityx name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input type="text" $disability $disabilityx name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>Poskod</td>
				<td>:</td>
				<td><input type="text" $disability $disabilityx name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value);"></td>
			</tr>
			<tr>
				<td>Negeri</td>
				<td>:</td>
				<td>$!selectNegeri</td>
			</tr>
			<tr>
				<td>Bandar</td>
				<td>:</td>
				<td>$!selectBandar</td>
			</tr>
			
		</table>
	</fieldset>
	#end

</center>


	<table width="100%" border="0">
		<tr align="center">
			<td>
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanBebanan('$!id_hakmilik','$!id_bebanan','0')">
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBebanan('$!id_bebanan')">
				<input type="button" name="cmdHapus" value="Hapus" onClick="hapusBebanan('$!id_bebanan','$!id_hakmilik')" >
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBebanan('$!id_hakmilik','$!id_bebanan','1')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:viewBebanan('$!id_bebanan','1')">
				#end
			#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewHM('$!id_hakmilik')">
				</td>
			</tr>
	</table>

	<fieldset id="bottom">
    <legend><strong>Senarai Bebanan</strong></legend>	
    		
    		<table width="100%" border="0">
    			<tr>
    				#if($mode=="view")
    				<td width="30%" align="left"><input type="button" name="cmdTambahBebanan" value="Tambah" onClick="javascript:tambahBebanan('$!id_hakmilik')"></td>
    				#else
    				<td width="30%">&nbsp;</td>
    				#end
 					<td width="70%" align="right">Carian No.Perserahan :&nbsp;<input type="text" name="carianNoSerah2" value="$!carianNoSerah2" maxlength="30" size="20"   ><a href="javascript:cariNoSerah('$!id_hakmilik','$!mode','$!id_bebanan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanNoSerah('$!id_hakmilik','$!mode','$!id_bebanan')">&nbsp;<u>KOSONGKAN</u></a></td>   				
    			</tr>
    		</table>

    		#if($saiz_bebanan > 5)
               <div style="width:100%;height:100;overflow:auto"> 
            #end
            	
    		<table width="100%" border="0">	
    			<tr class="table_header">
    				<td width="4%" align="center"><b>No</b></td>
    				<td width="15%"><b>Nama</b></td>
    				<td width="33%">&nbsp;<b>No. Perserahan</b></td>
    				<td width="48%">&nbsp;<b>Jenis Bebanan</b></td>
    			</tr>
    				
    		   #if($saiz_bebanan!=0)
                    #foreach($listB in $listBebanan)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               	<tr>
                   	<td class="$row" align="center">$!listB.bil</td>
                   	<td class="$row">$!listB.nama</td>
                   	<td class="$row">&nbsp;<a href="javascript:viewBebanan('$!listB.id_bebanan','0')"><font color="blue">$!listB.no_perserahan</font></a></td>
                   	<td class="$row">&nbsp;$!listB.jenis_bebanan</td>
              	<tr>
                    #end
               #else
                <tr>
                    <td colspan="2">Tiada rekod</td>
                </tr>
               #end
    				
    		</table>
    			
    			#if($saiz_bebanan > 5)
                </div>
            	#end
    			
    </fieldset>

<input type="hidden" name="tabId">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_bebanan" value="$!id_bebanan">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
function kosongkanNoSerah(idHakmilik,mode,idBebanan) {

	document.${formName}.ScreenLocation.value = "bottom";	
	
	if(mode=="view"){
		document.${formName}.command.value = "viewBebanan";
		document.${formName}.id_bebanan.value = idBebanan;
	}else{
		document.${formName}.command.value = "tambahBebanan";
	}

	document.${formName}.carianNoSerah2.value = "";	
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
	
}
function cariNoSerah(idHakmilik,mode,idBebanan) {
	
	document.${formName}.ScreenLocation.value = "bottom";	

	if(mode=="view"){
		document.${formName}.command.value = "viewBebanan";
		document.${formName}.id_bebanan.value = idBebanan;
	}else{
		document.${formName}.command.value = "tambahBebanan";
	}

	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();	
}
function kemaskiniBebanan(idBebanan){

	document.${formName}.ScreenLocation.value = "changeBebanan";

	document.${formName}.id_bebanan.value = idBebanan;
	document.${formName}.command.value = "viewBebanan";
	document.${formName}.command2.value = "kemaskiniBebanan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function onchangeNegeriBebananUpdate(){

	document.${formName}.ScreenLocation.value = "changeBebanan";

	document.${formName}.command.value = "viewBebanan";
	document.${formName}.command2.value = "kemaskiniBebanan";
	document.${formName}.command3.value = "onchangeNegeriBebananUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function onchangeNegeriBebanan(){

	document.${formName}.ScreenLocation.value = "changeBebanan";

	document.${formName}.command.value = "tambahBebanan";
	document.${formName}.command2.value = "onchangeNegeriBebanan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function hapusBebanan(idBebanan,idHakmilik){

	document.${formName}.ScreenLocation.value = "bottom";

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "hapusBebanan";
	document.${formName}.id_bebanan.value = idBebanan;
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function viewBebanan(idBebanan,mode){

	if(mode=="1"){
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
	}
	
	document.${formName}.ScreenLocation.value = "changeBebanan";	
	document.${formName}.command.value = "viewBebanan";
	document.${formName}.id_bebanan.value = idBebanan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function tambahBebanan(idHakmilik){

	document.${formName}.ScreenLocation.value = "changeBebanan";
	
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahBebanan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function simpanBebanan(idHakmilik,idBebanan,mode){

	document.${formName}.ScreenLocation.value = "changeBebanan";

	if(mode=="0"){
		document.${formName}.CursorPoint.value = "txtPerserahan";
	} 

	var dat1=document.${formName}.txdTarikhDaftar
	
	if(document.${formName}.txtPerserahan.value == ""){
		alert("Sila masukkan \"No.Perserahan\" terlebih dahulu.");
  		document.${formName}.txtPerserahan.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else
	if (document.${formName}.txtPoskod.value != "" && document.${formName}.txtPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.txtPoskod.focus();
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;	
		document.${formName}.id_hakmilik.value = idHakmilik;

		if(mode=="1"){
			document.${formName}.id_bebanan.value = idBebanan;
			document.${formName}.command.value = "viewBebanan";
			document.${formName}.command2.value = "kemaskiniBebanan";
			document.${formName}.command3.value = "updateBebanan";
		}else{
			document.${formName}.command.value = "tambahBebanan";
			document.${formName}.command2.value = "simpanBebanan";
		}	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
		document.${formName}.submit();
	}
}
function viewHM(idHakmilik){

	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.tabId.value = "1";
	
	document.${formName}.command.value = "viewHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
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