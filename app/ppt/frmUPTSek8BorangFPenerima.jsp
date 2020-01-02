
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset id="top">
<legend><strong>Borang F</strong></legend>

	#if($mode=="new")
	<table width="100%" border="0">
		<tr>
			<td width="1%"><font color="red">*</font></td>
			<td width="20%">Nama Penerima</td>
			<td width="1%">:</td>
			<td width="78%">$!selectPB</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Kod No. PB</td>
			<td>:</td>
			<td>$!selectJenisNoPB</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. PB</td>
			<td>:</td>
			<td><input type="text" name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Alamat</td>
			<td>:</td>
			<td><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="45" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
			<td><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="45" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
			<td><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="45" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Poskod</td>
			<td>:</td>
			<td><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value)" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Negeri</td>
			<td>:</td>
			<td>$!selectNegeri</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Bandar</td>
			<td>:</td>
			<td>$!selectBandar</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Tempoh</td>
			<td>:</td>
			<td><input type="text" name="txtTempoh" id="txtTempoh" value="$!txtTempoh" size="5" maxlength="3" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>		
		<!--<tr>
			<td>&nbsp;</td>
			<td>Tarikh Terima</td>
			<td>:</td>
			<td><input name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr> -->
		<tr>
			<td>&nbsp;</td>
			<td valign="top">Keterangan</td>
			<td valign="top">:</td>
			<td valign="top"><textarea name="txtKeterangan" id="txtKeterangan" cols="45%" rows="5" onKeyUp="textCounter(this.form.txtKeterangan,this.form.remLen3,1500);" onKeyDown="textCounter(this.form.txtKeterangan,this.form.remLen3,1500);" >$!txtKeterangan</textarea></td>
		</tr>
		<tr>
        	<td colspan="3">&nbsp;</td>
            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="1500"></td>
        </tr> 
	</table>
	#end
	
	
	
	#if($mode=="view")
	
	#if($onchange=="no")
	#foreach($data in $dataPenerima)
		#set($txtAlamat1=$data.alamat1)
		#set($txtAlamat2=$data.alamat2)
		#set($txtAlamat3=$data.alamat3)
		#set($txtPoskod=$data.poskod)
		#set($txtTempoh=$data.tempoh)
		#set($txdTarikhTerima=$data.tarikh_terima)
		#set($txtKeterangan=$data.ulasan)
		#set($txtNoPB=$data.no_pb)
	#end
	#end
	
	#if($isEdit=="no")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#set($disability1 = "disabled")
	#else
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
	#end
	
	<table width="100%" border="0">
		<tr>
			<td width="1%">#if($isEdit=="yes")<font color="red">*</font>#end</td>
			<td width="20%">Nama Penerima</td>
			<td width="1%">:</td>
			<td width="78%">$!selectPB</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Kod No. PB</td>
			<td>:</td>
			<td>$!selectJenisNoPB</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. PB</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Alamat</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="45" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
			<td><input type="text" $disability $disabilityx name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="45" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
			<td><input type="text" $disability $disabilityx name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="45" maxlength="80" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Poskod</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value)" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Negeri</td>
			<td>:</td>
			<td>$!selectNegeri</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Bandar</td>
			<td>:</td>
			<td>$!selectBandar</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Tempoh</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtTempoh" id="txtTempoh" value="$!txtTempoh" size="5" maxlength="3" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" ></td>
		</tr>
		<!--  <tr>
			<td>&nbsp;</td>
			<td>Tarikh Terima</td>
			<td>:</td>
			<td><input name="txdTarikhTerima" $disability $disabilityx id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
		</tr> -->
		<tr>
			<td>&nbsp;</td>
			<td valign="top">Keterangan</td>
			<td valign="top">:</td>
			<td valign="top"><textarea $disability $disabilityx name="txtKeterangan" id="txtKeterangan" cols="45%" rows="5" onKeyUp="textCounter(this.form.txtKeterangan,this.form.remLen3,1500);" onKeyDown="textCounter(this.form.txtKeterangan,this.form.remLen3,1500);" >$!txtKeterangan</textarea></td>
		</tr>
		#if($isEdit=="yes")
		<tr>
        	<td colspan="3">&nbsp;</td>
            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="1500"></td>
        </tr> 
        #end
        
	</table>
	#end
	
</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPenerima('$!id_hakmilik','$!id_hakmilikpb','$!id_borangf','$!mode')">
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPenerima('$!id_borangf')">
				<input type="button" name="cmdHapus" value="Hapus" onClick="hapusPenerima('$!id_borangf')" >
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPenerima('$!id_hakmilik','$!id_hakmilikpb','$!id_borangf','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniPenerima('$!id_borangf')">
				#end
			#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListBorangF('$!id_hakmilik')">
			</td>
		</tr>
	</table>


	<fieldset id="bottom">
	<legend><strong>Senarai Borang F</strong></legend>
	
		#if($mode=="view")
		<table width="100%" border="0">   
              <tr>
                 <td width="30%" align="left"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahPenerima('$!id_hakmilik');"></td>
              </tr>
    	</table>
		#end
		
		#if($saiz_listBorangF > 5)
                <div style="width:100%;height:100;overflow:auto"> 
        #end
	
		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>Nama Penerima</b></td>
        			<td><b>No. PB</b></td>
        			<!-- <td><b>Tarikh Terima</b></td> -->
        		</tr>
        		
        		#if($saiz_listBorangF!=0)
                    #foreach($list in $listBorangF)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!list.bil</td>
                   <td class="$row"><a href="javascript:viewPenerima('$!list.id_borangf')"><font color="blue">$!list.nama_pb</font></a></td>
                   <td class="$row">$!list.no_pb</td>
                   <!-- <td class="$row">$!list.tarikh_terima</td> -->	
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		</table>
			
		#if($saiz_listBorangF > 5)
        	</div>
        #end	
		
	</fieldset>
	
	

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilikpb" value="$!id_hakmilikpb">
<input type="hidden" name="id_borangf" value="$!id_borangf">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<script>
function onchangeNegeriUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewPenerima";
	document.${formName}.command2.value = "kemaskiniPenerima";
	document.${formName}.command3.value = "onchangeNegeriUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function onchangePBUpdate(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewPenerima";
	document.${formName}.command2.value = "kemaskiniPenerima";
	document.${formName}.command3.value = "onchangePBUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function batalKemaskiniPenerima(id_borangf){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "viewPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function kemaskiniPenerima(id_borangf){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "viewPenerima";
	document.${formName}.command2.value = "kemaskiniPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function hapusPenerima(id_borangf){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "hapusPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function viewPenerima(id_borangf){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "viewPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function tambahPenerima(id_hakmilik){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "tambahPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function simpanPenerima(id_hakmilik,idHakmilikPb,id_borangf,mode){
	
	//var dat1 = document.${formName}.txdTarikhTerima
	
	if(document.${formName}.socPB.value == ""){
		alert("Sila pilih \"Nama Penerima\" terlebih dahulu.");
  		document.${formName}.socPB.focus(); 
		return;
	}	
	else
	if (document.${formName}.txtPoskod.value != "" && document.${formName}.txtPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.txtPoskod.focus();
	}
/*	else
	if (dat1.value!="" && isDate(dat1.value)==false){
		dat1.focus()
		return;
	}	
*/	else{
	
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_hakmilik.value = id_hakmilik;
		document.${formName}.id_hakmilikpb.value = idHakmilikPb;

		if(mode=="new"){
			document.${formName}.command.value = "tambahPenerima";
			document.${formName}.command2.value = "simpanPenerima";
		}else{
			document.${formName}.id_borangf.value = id_borangf;
			document.${formName}.command.value = "viewPenerima";
			document.${formName}.command2.value = "kemaskiniPenerima";
			document.${formName}.command3.value = "updatePenerima";
		}

		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
		document.${formName}.submit();
	}
}
function viewListBorangF(id_hakmilik) {

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function onchangeNegeri(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahPenerima";
	document.${formName}.command2.value = "onchangeNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function onchangePB(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahPenerima";
	document.${formName}.command2.value = "onchangePB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
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
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
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
</script>