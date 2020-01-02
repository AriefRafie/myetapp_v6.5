
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<center>
<fieldset>
<legend><strong>Maklumat Surat Jabatan Teknikal</strong></legend>

<br/>

	#if($mode=="new")
	<fieldset id="changeJabatan">
	<legend><strong>Maklumat Surat Kepada Jabatan Teknikal</strong></legend>
		
		<table width="100%" border="0">
			<tr>
			
			<!-- TABLE KIRI -->
			<td width="53%" valign="top"><table width="100%" border="0">
				<tr>
					<td width="32%">No. Rujukan</td>
					<td width="1%">:</td>
					<td width="67%"><input type="text" name="txtBilSurat" maxlength="2" id="txtBilSurat" value="" style="text-transform:uppercase;"  size="4" onkeyup="validateNumber(this,this.value)" /></td>
				</tr>
				<tr>
					<td>Tarikh Surat</td>
					<td>:</td>
					<td><input name="txdTarikhSurat" value="" size="11" id="txdTarikhSurat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">&nbsp;$!frmtdate</td>
				</tr>
				<tr>
					<td>Tempoh (Minggu)</td>
					<td>:</td>
					<td><input type="text" name="txtTempoh" maxlength="2" id="txtTempoh" value="" style="text-transform:uppercase;"  size="4" onkeyup="validateNumber(this,this.value)" /></td>
				</tr>
			</table></td>	
			
			<!-- TABLE KANAN -->
			<td width="47%" valign="top"><table width="100%" border="0">
				<tr>
					<td valign="top" width="22%">Perihal</td>
					<td valign="top" width="1%">:</td>
					<td valign="top" width="77%"><textarea name="txtPerihal" id="txtPerihal" cols="35%" rows="5"  onKeyUp="textCounter(this.form.txtPerihal,this.form.remLen1,1500);" onKeyDown="textCounter(this.form.txtPerihal,this.form.remLen1,1500);" ></textarea></td>
				</tr>
			</table></td>
			
			</tr>
		</table>	
	</fieldset>
	
<br/>

	<fieldset>
	<legend><strong>Maklumat Terimaan Surat Dari Jabatan Teknikal</strong></legend>
		<table width="100%" border="0">
			<tr>
			
			<!-- TABLE KIRI -->
			<td width="53%" valign="top"><table width="100%" border="0">
				<!-- <tr>
					<td>No. Rujukan</td>
					<td width="1%">:</td>
					<td width="67%"><input type="text" name="txtBilSuratJT" maxlength="2" id="txtBilSuratJT" value="" style="text-transform:uppercase;"  size="4" onkeyup="validateNumber(this,this.value)" /></td>
				</tr> -->
				<tr>
					<td width="1%"><font color="red">*</font></td>
					<td width="30%">Jabatan</td>
					<td width="1%">:</td>
					<td width="68%">$!selectJabatan</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>No. Rujukan Surat</td>
					<td>:</td>
					<td><input type="text" name="txtNoRujukan" maxlength="50" id="txtNoRujukan" value="" style="text-transform:uppercase;"  size="20"  onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Terima</td>
					<td>:</td>
					<td><input name="txdTarikhTerima" value="" size="11" id="txdTarikhTerima" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Surat</td>
					<td>:</td>
					<td><input name="txdTarikhSuratJT" value="" size="11" id="txdTarikhSuratJT" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratJT',false,'dmy');">&nbsp;$!frmtdate</td>
				</tr>
			</table></td>	
			
			<!-- TABLE KANAN -->
			<td width="47%" valign="top"><table width="100%" border="0">
				<tr>
					<td width="22%">Keputusan</td>
					<td width="1%">:</td>
					<td width="77%"><select name="socKeputusan" id="socKeputusan" style="width:auto">
      					<option value="">SILA PILIH</option>
						<option value="1">DISOKONG</option>
						<option value="2">TIDAK DISOKONG</option>
					</select></td>
				</tr>
				<tr>
					<td valign="top">Ulasan</td>
					<td valign="top">:</td>
					<td valign="top"><textarea name="txtUlasan" id="txtUlasan" cols="35%" rows="5"  onKeyUp="textCounter(this.form.txtUlasan,this.form.remLen2,1500);" onKeyDown="textCounter(this.form.txtUlasan,this.form.remLen2,1500);" ></textarea></td>
				</tr>
			</table></td>
			
			</tr>
		</table>	
	</fieldset>
	#end


	#if($mode=="view")
	
	#foreach($data in $dataJabatan)
		#set($txtBilSurat=$data.bil_surat)
		#set($txdTarikhSurat=$data.tarikh_surat)
		#set($txtTempoh=$data.tempoh)
		#set($txtPerihal=$data.perihal)
		#set($txtBilSuratJT=$data.bil_surat_jt)
		#set($txtNoRujukan=$data.no_rujukansuratjt)
		#set($txdTarikhTerima=$data.tarikh_terimajt)
		#set($txdTarikhSuratJT=$data.tarikh_suratjt)
		#set($socKeputusan=$data.keputusanjt)
		#set($txtUlasan=$data.ulasanjt)
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
	
	
	<fieldset id="changeJabatan">
	<legend><strong>Maklumat Surat Kepada Jabatan Teknikal</strong></legend>
		
		<table width="100%" border="0">
			<tr>
			
			<!-- TABLE KIRI -->
			<td width="53%" valign="top"><table width="100%" border="0">
				<tr>
					<td width="32%">No. Rujukan</td>
					<td width="1%">:</td>
					<td width="67%"><input $disability $disabilityx type="text" name="txtBilSurat" maxlength="2" id="txtBilSurat" value="$!txtBilSurat" style="text-transform:uppercase;"  size="4" onkeyup="validateNumber(this,this.value)" /></td>
				</tr>
				<tr>
					<td>Tarikh Surat</td>
					<td>:</td>
					<td><input $disability $disabilityx name="txdTarikhSurat" value="$!txdTarikhSurat" size="11" id="txdTarikhSurat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
				<tr>
					<td>Tempoh (Minggu)</td>
					<td>:</td>
					<td><input $disability $disabilityx type="text" name="txtTempoh" maxlength="2" id="txtTempoh" value="$!txtTempoh" style="text-transform:uppercase;"  size="4" onkeyup="validateNumber(this,this.value)" /></td>
				</tr>
			</table></td>	
			
			<!-- TABLE KANAN -->
			<td width="47%" valign="top"><table width="100%" border="0">
				<tr>
					<td valign="top" width="22%">Perihal</td>
					<td valign="top" width="1%">:</td>
					<td valign="top" width="77%"><textarea $disability $disabilityx name="txtPerihal" id="txtPerihal" cols="35%" rows="5" onKeyUp="textCounter(this.form.txtPerihal,this.form.remLen1,1500);" onKeyDown="textCounter(this.form.txtPerihal,this.form.remLen1,1500);" >$!txtPerihal</textarea></td>
				</tr>
			</table></td>
			
			</tr>
		</table>	
	</fieldset>
	
<br/>

	<fieldset>
	<legend><strong>Maklumat Terimaan Surat Dari Jabatan Teknikal</strong></legend>
		<table width="100%" border="0">
			<tr>
			
			<!-- TABLE KIRI -->
			<td width="53%" valign="top"><table width="100%" border="0">
			
				<!-- <tr>
					<td width="32%">Bil. Surat</td>
					<td width="1%">:</td>
					<td width="67%"><input $disability $disabilityx type="text" name="txtBilSuratJT" maxlength="2" id="txtBilSuratJT" value="$!txtBilSuratJT" style="text-transform:uppercase;"  size="4" onkeyup="validateNumber(this,this.value)" /></td>
				</tr> -->
				<tr>
					<td width="1%"><font color="red">#if($isEdit=="yes")*#end</font></td>
					<td width="30%">Jabatan</td>
					<td width="1%">:</td>
					<td width="68%">$!selectJabatan</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>No. Rujukan Surat</td>
					<td>:</td>
					<td><input $disability $disabilityx type="text" name="txtNoRujukan" maxlength="50" id="txtNoRujukan" value="$!txtNoRujukan" style="text-transform:uppercase;"  size="20"  onBlur="this.value=this.value.toUpperCase();" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Terima</td>
					<td>:</td>
					<td><input $disability $disabilityx name="txdTarikhTerima" value="$!txdTarikhTerima" size="11" id="txdTarikhTerima" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Surat</td>
					<td>:</td>
					<td><input $disability $disabilityx name="txdTarikhSuratJT" value="$!txdTarikhSuratJT" size="11" id="txdTarikhSuratJT" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratJT',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
			</table></td>	
			
			<!-- TABLE KANAN -->
			<td width="47%" valign="top"><table width="100%" border="0">
				<tr>
					<td width="22%">Keputusan</td>
					<td width="1%">:</td>
					<td width="77%"><select $disability1 $disabilityx name="socKeputusan" id="socKeputusan" style="width:auto">
      					
      					#if($socKeputusan=="1")
      					<option value="1">DISOKONG</option>			
						<option value="2">TIDAK DISOKONG</option>
						<option value="">SILA PILIH</option>
      					#elseif($socKeputusan=="2")
      					<option value="2">TIDAK DISOKONG</option>
						<option value="1">DISOKONG</option>		
						<option value="">SILA PILIH</option>			
      					#else
      					<option value="">SILA PILIH</option>
						<option value="1">DISOKONG</option>
						<option value="2">TIDAK DISOKONG</option>
      					#end
      					
					</select></td>
				</tr>
				<tr>
					<td valign="top">Ulasan</td>
					<td valign="top">:</td>
					<td valign="top"><textarea $disability $disabilityx name="txtUlasan" id="txtUlasan" cols="35%" rows="5" onKeyUp="textCounter(this.form.txtUlasan,this.form.remLen2,1500);" onKeyDown="textCounter(this.form.txtUlasan,this.form.remLen2,1500);" >$!txtUlasan</textarea></td>
				</tr>
			</table></td>
			
			</tr>
		</table>	
	</fieldset>
	#end

	<table width="100%" border="0">
		<tr align="center">
			<td>
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanJabatan('$!id_permohonan','$!id_ulasanteknikal','$!mode')">
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniJabatan('$!id_ulasanteknikal')">
				<input type="button" name="cmdHapus" value="Hapus" onClick="javascript:hapusJabatan('$!id_ulasanteknikal')" >
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanJabatan('$!id_permohonan','$!id_ulasanteknikal','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:viewJabatan('$!id_ulasanteknikal','1')">
				#end
			#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali('$!id_permohonan')">
			</td>
		</tr>
	</table>

	<fieldset id="bottom">
	<legend><strong>Senarai Jabatan Teknikal</strong></legend>
			
			<table width="100%" border="0">   
                	<tr>
                		#if($mode=="view")
                    	<td width="30%" align="left"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahJabatan('$!id_permohonan');"></td>
    					#else
    					<td width="30%" align="left">&nbsp;</td>
    					#end
    					<td width="70%" align="right">Carian Nama Pejabat :&nbsp;<input type="text" name="carianPejabat2" value="$!carianPejabat2" maxlength="70" size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ><a href="javascript:cariPejabat('$!id_permohonan','$!id_ulasanteknikal','$!mode')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanPejabat('$!id_permohonan','$!id_ulasanteknikal','$!mode')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    			
    		#if($saiz_jabatan > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>Nama Jabatan</b></td>
            		<td><b>No. Rujukan</b></td>
            		<td><b>Tarikh Surat</b></td> 
            		<td><b>Tempoh(Minggu)</b></td> 
        		</tr>
        		
        		#if($saiz_jabatan!=0)
                    #foreach($listJT in $listJabatanTeknikal)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listJT.bil</td>
                   <td class="$row"><a href="javascript:viewJabatan('$!listJT.id_ulasanteknikal','0')"><font color="blue">$!listJT.nama_jabatan</font></a></td>
                   <td class="$row">$!listJT.bil_surat</td>
                   <td class="$row">$!listJT.tarikh_surat</td>
                   <td class="$row">$!listJT.tempoh</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_jabatan > 5)
                </div>
            #end
	
	</fieldset>

</fieldset>
</center>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_ulasanteknikal" value="$!id_ulasanteknikal">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<script>
function cariPejabat(idpermohonan,id_ulasanteknikal,mode) {
	
	document.${formName}.ScreenLocation.value = "bottom";	

	if(mode=="view"){
		document.${formName}.command.value = "viewJabatan";
		document.${formName}.id_ulasanteknikal.value = id_ulasanteknikal;
	}else{
		document.${formName}.command.value = "tambahJabatan";
	}

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();	
}
function kosongkanPejabat(idpermohonan,id_ulasanteknikal,mode) {

	document.${formName}.ScreenLocation.value = "bottom";	
	
	if(mode=="view"){
		document.${formName}.command.value = "viewJabatan";
		document.${formName}.id_ulasanteknikal.value = id_ulasanteknikal;
	}else{
		document.${formName}.command.value = "tambahJabatan";
	}

	document.${formName}.carianPejabat2.value = "";	
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();
	
}
function hapusJabatan(id_ulasanteknikal){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "bottom";	
	document.${formName}.command.value = "hapusJabatan";
	document.${formName}.id_ulasanteknikal.value = id_ulasanteknikal;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();
}
function tambahJabatan(idpermohonan){

	document.${formName}.ScreenLocation.value = "changeJabatan";
	
	document.${formName}.command.value = "tambahJabatan";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();
}
function viewJabatan(id_ulasanteknikal,mode){

	if(mode=="1"){
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
	}
	
	document.${formName}.ScreenLocation.value = "changeJabatan";
	
	document.${formName}.command.value = "viewJabatan";
	document.${formName}.id_ulasanteknikal.value = id_ulasanteknikal;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();
}
function kemaskiniJabatan(id_ulasanteknikal){

	document.${formName}.ScreenLocation.value = "changeJabatan";
	
	document.${formName}.command.value = "viewJabatan";
	document.${formName}.command2.value = "kemaskiniJabatan";
	document.${formName}.id_ulasanteknikal.value = id_ulasanteknikal;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();
}
function simpanJabatan(idpermohonan,id_ulasanteknikal,mode) {

	document.${formName}.ScreenLocation.value = "changeJabatan";

	var dat1=document.${formName}.txdTarikhSurat
	var dat2=document.${formName}.txdTarikhTerima
	var dat3=document.${formName}.txdTarikhSuratJT
	
	if(mode=="new"){
	document.${formName}.CursorPoint.value = "socJabatan";
	}

	if(document.${formName}.socJabatan.value==""){
		alert("Sila pilih \"Jabatan\" terlebih dahulu.");
  		document.${formName}.socJabatan.focus(); 
		return;
	}
	else if(dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="view"){
			document.${formName}.id_ulasanteknikal.value = id_ulasanteknikal;
			document.${formName}.command.value = "viewJabatan";
			document.${formName}.command2.value = "kemaskiniJabatan";
			document.${formName}.command3.value = "updateJabatan";
		}else{
			document.${formName}.command.value = "tambahJabatan";
			document.${formName}.command2.value = "simpanJabatan";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
		document.${formName}.submit();
	}
}
function kembali(id_permohonan) {

	document.${formName}.ScreenLocation.value = "bottom";
	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakSenaraiJabatan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
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
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890.";
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