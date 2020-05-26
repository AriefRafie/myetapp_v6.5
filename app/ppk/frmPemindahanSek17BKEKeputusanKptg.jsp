<style type="text/css">
<!--
.style40 {color: #0000FF}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
<body>
#set ($nofail="")
#set ($namadaerah = "")
#set ($namanegeri = "")
#set ($namaPejabat = "")
#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    #set ($nofail = $list.noFail)
    #set ($namadaerah = $list.namadaerah)
    #set ($namanegeri = $list.namanegeri)
    #set ($namaPejabat = $list.nama_pejabat)
	#set ($alamat1 = $list.alamat_1)
    #set ($keterangan = $list.keterangan)
    #set ($seksyen = $list.seksyen)
    #set ($tarikhMohon = $list.tarikhMohon)
    #set ($namaPemohon = $list.namaPemohon)
    #set ($namasimati = $list.namaSimati)
    <input name="idPermohonan" type="hidden" value="$id"/>
     <input name="idpermohonan" type="hidden" value="$id"/>
#end
<tr>
<td>
#if($!headerppk.size()>0)
#parse("app/ppk/headerppk.jsp")
#end

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<fieldset style="display:none">
<legend>MAKLUMAT PERMOHONAN</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">No Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style40">$!nofail</div></td>
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Negeri :</div></td>
          <td style="text-transform:uppercase;"><div align="left" class="style40">$!namanegeri</div></td>
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Daerah / Jajahan :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!namadaerah</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Unit :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$namaPejabat &nbsp; $!alamat1</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Seksyen :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!seksyen</span>
                    <input type="hidden" name="txtSeksyen" value="$list.seksyen" readonly="true"/>
          </div></td>
        </tr>
      </table>
    </div></td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">Status Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left"><span class="style40">$!keterangan</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Tarikh Mohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!tarikhMohon</span>
                    <input type="hidden" name="txdTarikhMohon" value="$View.tarikhMohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Simati :</div></td>
          <td style="text-transform:uppercase;"><span class="style40">$!namasimati</span>
            <input type="hidden" name="idSimati" value="$list.idSimati" readonly="true"/></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Pemohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!namaPemohon</span>
                    <input type="hidden" name="txtNamaPemohon" value="$View.namaPemohon" readonly="true"/>
          </div></td>
        </tr>
        
      </table>
    </div></td>
  </tr>
</table>

</fieldset>
</td>
</tr>


#foreach ($listDataNegeriDaerah in $listNegeriDaerah)
#set ($namanegeri = $listDataNegeriDaerah.namanegeri)
#set ($namadaerah = $listDataNegeriDaerah.namadaerah)
#end
#if ($releaseData != "0")
	#foreach ($listData in $listBkeData)
		#set ($tarikhLulus = $listData.tarikhlulus)
		#set ($keputusan = $listData.keputusankptg)
		#set ($catatan = $listData.catatan)
	#end
	#if ($keputusan == "L")
		#set ($checked1 = "checked")
        #set ($checked2 = "")
	#elseif ($keputusan == "G")
    	#set ($checked1 = "")
		#set ($checked2 = "checked")
	#end 
#end
 <input type="hidden" name="action">
 <input type="hidden" name="hitButt" >
 <input type="hidden" name="mode" >
  <input type="hidden" name="idPermohonan" value="$idPermohonan">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
  <table width="100%" border="0">
    <tr>
      <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
		  <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemohonBkeView('0','0','0','0')">PERMOHONAN BKE</li>
          <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="KeputusanView('1','0','0','0')">KEBENARAN</li>
          <li class="TabbedPanelsTab" tabindex="0" onClick="KptgView('2','0','0','0')">KEPUTUSAN PPP</li> <!-- arief ubah label KPTG/PTG kepada PPP-->
          </ul>
          <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent">
          <table width="100%">
          <tr>
          <td width="30%" align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Tarikh Diluluskan :</td>
          <td width="70%"><input name="txdTarikhLulus" type="text" id="txdTarikhLulus" size="10" maxlength="10" $readmode value="$tarikhLulus" onBlur="trans_date(this.value)"/>
            #if ($readmode != "disabled")
             	<a href="javascript:displayDatePicker('txdTarikhLulus',false,'dmy');" $readmode><img src="../img/calendar.gif" alt="" border="0" $setmode/></a>
          	#end
            &nbsp;&nbsp;<span class="style52"><i>format : dd/mm/yyyy</i></span>
			</td>
          </tr>
          <tr>
          <td align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Keputusan :</td>
          <td><input type="radio" name="sorkeputusan" value="L" $readmode $checked1> LULUS &nbsp;&nbsp;<input type="radio" name="sorkeputusan" value="G" $readmode $checked2> TIDAK LULUS</td>
          </tr>
          <tr>
          <td align="right" valign="top">Catatan :</td>
          <td><textarea name="txtCatatanKptg" cols="50" $readmode style="text-transform:uppercase;">$catatan</textarea></td>
          </tr>
		  <tr>
		<td colspan="2" height="50px" valign="bottom"><i><font color="#ff0000">*</font> &nbsp;Sila pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
    	</tr>
          <tr>
          <td></td>
          <td valign="bottom" height="50px">
          #if ($btnKemaskini == "yes")
			<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskini_keputusan('2','0','0','0')">
		  #end
		  #if ($btnSimpan == "yes")	
			<input type="button" name="cmdSimpan" value="Simpan" onClick="simpan_keputusan('2','0','0','0')">
		  #end
		  #if ($btnCetak == "yes")
		  	<!--<input type="button" name="cmdCetak" value="Cetak">-->
		  #end
		  #if ($btnBatal == "yes")
		  	<input type="reset" name="cmdBatal" value="Batal">
		  #end
		  #if ($btnKembali == "yes")
		  	<input type="button" name="cmdKembali" value="Kembali" onClick="kembali_keputusan()">
		  #end
		  </td>
          </tr>
         	</table>
          </div>
          </div>
      </div></td>
    </tr>
  </table>
  #parse("app/ppk/headerppk_script.jsp")
<script type="text/javascript">
function PemohonBkeView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value = "Permohonanview";
	document.${formName}.hitButt.value = "Permohonan17";
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	document.${formName}.action.value = "";
	document.${formName}.submit();
}
function KeputusanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value = "Keputusanview";
	document.${formName}.hitButt.value = "Keputusan17";
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	document.${formName}.action.value = "";
	document.${formName}.submit();
}

function KptgView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value = "Kptgview";
	document.${formName}.hitButt.value = "Kptg17";
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	document.${formName}.action.value = "";
	document.${formName}.submit();
}

function validDate() {
	var currentTime = new Date()
	var month = currentTime.getMonth() + 1
	var day = currentTime.getDate()
	var year = currentTime.getFullYear()
	var currentDate = day + "/" + month + "/" + year;

	if (document.${formName}.txdTarikhLulus.value > currentDate){
		alert("Sila pastikan Tarikh Diluluskan tidak melebihi dari tarikh hari ini.");
		txdTarikhLulus.clear();
	}
}

function simpan_keputusan(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	var currentTime = new Date()

	var str1  = document.getElementById("txdTarikhLulus").value;
	var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);
	
	if (document.${formName}.txdTarikhLulus.value == ""){
		alert("Sila pilih Tarikh Diluluskan");
		txdTarikhLulus.focus();
	}
	else if (document.${formName}.txdTarikhLulus.value != "" && isDate(str1)==false){
		txdTarikhLulus.focus();
	}
	else if (date1 > currentTime){
		alert("Sila pastikan Tarikh Diluluskan tidak melebihi dari tarikh hari ini.");
		txdTarikhLulus.focus();
	}
	else if (document.${formName}.sorkeputusan[0].checked == false && document.${formName}.sorkeputusan[1].checked == false){
		alert("Sila pilih Keputusan Pindah");
		sorkeputusan[0].focus();
	}
	else{	
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.method="post";
			document.${formName}.mode.value = "Kptgsimpan";
			document.${formName}.hitButt.value = "Kptg17";
			document.${formName}.tabIdatas.value = tabIdatas;
			document.${formName}.tabIdtengah.value = tabIdtengah;
			document.${formName}.tabIdbawah.value = tabIdbawah;
			document.${formName}.tabIdtepi.value = tabIdtepi;
			document.${formName}.action.value="";
			document.${formName}.submit();
		}
	}
}

function kemaskini_keputusan(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value = "Kptgkemaskini";
		document.${formName}.hitButt.value = "Kptg17";
		document.${formName}.tabIdatas.value = tabIdatas;
		document.${formName}.tabIdtengah.value = tabIdtengah;
		document.${formName}.tabIdbawah.value = tabIdbawah;
		document.${formName}.tabIdtepi.value = tabIdtepi;
		document.${formName}.action.value="";
		document.${formName}.submit();
}

function batal_keputusan(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value = "Kptgbatal";
	document.${formName}.hitButt.value = "Kptg17";
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function kembali_keputusan() {
	document.${formName}.method="post";
	document.${formName}.hitButt.value="Kembali";
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function trans_date(t_d) {
	if(t_d.length == 8){
		var a = t_d.charAt(0);
		var b = t_d.charAt(1);
		var c = t_d.charAt(2);
		var d = t_d.charAt(3);
		var e = t_d.charAt(4);
		var f = t_d.charAt(5);
		var g = t_d.charAt(6);
		var h = t_d.charAt(7);
		
		var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
		document.${formName}.txdTarikhLulus.value = trans;
	}
	else {
		return;
	}
}
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
//-->
</script>
<script language = "Javascript">
/**
 * DHTML date validation script for dd/mm/yyyy. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */
// Declaring valid date character, minimum year and maximum year
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
		alert("Sila masukkan bulan yg sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yg sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yg sah")
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
</body>

