<head>
<title></title>
<!-- <link href="./portal/style.css" rel="stylesheet" type="text/css" /> -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Pragma" Content="cache">
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="./library/scriptaculous/prototype.js" ></script>
<script type="text/javascript" src="./library/scriptaculous/scriptaculous.js" ></script>
<script type="text/javascript" src="./library/scriptaculous/modalbox.js" ></script>
<link rel="stylesheet" type="text/css" href="./library/scriptaculous/modalbox.css" />
<!-- auto complete part-->
<script type="text/javascript" src="./library/scriptaculous/effects.js" ></script>
<script type="text/javascript" src="./library/scriptaculous/controls.js" ></script>
<script type="text/javascript" src="./library/scriptaculous/ajax.js" ></script>
<!-- Jquery -->
<script type="text/javascript" src="./library/js/jquery-1.3.2.min.js"></script>
<script>var $jquery = jQuery.noConflict();</script>
<script type="text/javascript" src="./library/js/jquery.pstrength-min.1.2.js"></script>
<script type="text/javascript" src="./library/js/jquery.captchaRefresh.js"></script>
<script type="text/javascript" src="./library/js/ekptgTools.js" ></script>
</head>
<link rel="shortcut icon" href="./favicon.ico" />
<style>
.blink {
	animation: blink-animation 1s steps(5, start) infinite;
	-webkit-animation: blink-animation 1s steps(5, start) infinite;
}
@keyframes blink-animation {
 to {
 visibility: hidden;
}
}
@-webkit-keyframes blink-animation {
 to {
 visibility: hidden;
}
}
#validEmail {
	margin-top: 4px;
	margin-left: 9px;
	position: absolute;
	width: 16px;
	height: 16px;
}
.style7 {
	font-size: 12px
}
</style>
<div id="doRegisterResult"> </div>
<p></p>
<div id="RegistrationForm">
  <form name='f1' id='f1'>
    <!--
&nbsp;&nbsp;&nbsp;<a href="/online">Utama</a> >> <a href="">Pendaftaran baru</a> 
-->
    <p></p>
    <input name="action" type="hidden" value="$action" />
    <table width="100%" border="0" cellpadding="2" cellspacing="2" id="ID_TABLE_PIP">
      <tr>
        <td valign="top"  width="1%"></td>
        <td valign="top"  width="28%"></td>
        <td valign="top"  width="1%"></td>
        <td valign="top"  width="40%"><!-- $viewPengguna --></td>
        <td valign="top"  width="30%"><!-- $viewPengguna --></td>
      </tr>
      <tr>
        <td valign="top" ><font color="red" >*</font> </td>
        <td valign="top" > Negeri </td>
        <td valign="top" > : </td>
        <td valign="top"><div id="divNegeri" >
            <select id="ID_NEGERI" name="ID_NEGERI" onmouseover="getNegeri();" onChange="getBahagian();">
              <option value="" >SILA PILIH</option>
            </select>
          </div></td>
        <td valign="top" ></td>
      </tr>
      <tr id="divFullBahagian">
        <td valign="top" ><font color="red" >*</font> </td>
        <td valign="top" > Bahagian </td>
        <td valign="top" > : </td>
        <td valign="top"><div id="divBahagian" >
            <select id="ID_BAHAGIAN" name="ID_BAHAGIAN" onChange="getUnit();">
              <option value="" >SILA PILIH</option>
            </select>
          </div></td>
        <td valign="top" ></td>
      </tr>
      <tr>
        <td valign="top" ><font color="red" >*</font> </td>
        <td valign="top" > Unit </td>
        <td valign="top" > : </td>
        <td valign="top"><div id="divUnit" >
            <select id="ID_PEJABAT" name="ID_PEJABAT" >
              <option value="" >SILA PILIH</option>
            </select>
          </div></td>
        <td valign="top" ></td>
      </tr>
       <tr id="tr_divAlamat" style="display:none">
        <td valign="top" ></td>
        <td valign="top" > Alamat Pejabat </td>
        <td valign="top" > : </td>
        <td valign="top"><div id="divPejabat" > </div></td>
        <td valign="top" ></td>
      </tr>
      <tr id="tr_divNoTel" style="display:none" >
        <td valign="top" ><font color="red" ></font> </td>
        <td valign="top" > No. Telefon Pejabat </td>
        <td valign="top" > : </td>
        <td valign="top"><!--<input name="NO_OFFICE" type="text" id="NO_OFFICE" onKeyUp="RemoveNonNumeric_V3(this,this.value)" maxlength="15"  value="" size="50" />-->
          <div id="divNotel" > </div></td>
        <td valign="top" ></td>
      </tr>
      <tr id="tr_divNoFax" style="display:none" >
        <td valign="top" ><font color="red" ></font> </td>
        <td valign="top" > No. Faks </td>
        <td valign="top" > : </td>
        <td valign="top"><!--<input name="NO_OFFICE" type="text" id="NO_OFFICE" onKeyUp="RemoveNonNumeric_V3(this,this.value)" maxlength="15"  value="" size="50" />-->
          <div id="divNoFax" > </div></td>
        <td valign="top" ></td>
      </tr>
      <tr>
        <td valign="top" ><font color="red" >*</font> </td>
        <td valign="top" > MyID </td>
        <td valign="top" > : </td>
        <td valign="top" ><!-- open username by IC -->
          <input name="NOKP1" type="text" 
				id="NOKP1" style="width:50px;" 
				onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP2');checkDOBByIC_V3(this,this.value,'span_NOKP1');" 
				value="" size="7" maxlength="6"  
				
				/>
          <span id="span_NOKP1">
          <input type="hidden" id="CHECK_NOKP1" 
				name="CHECK_NOKP1" value="true" >
          </span> &nbsp;
          <input name="NOKP2" type="text" 
                id="NOKP2" style="width:20px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3');" 
                value="" size="1" maxlength="2"  />
          &nbsp;
          <input name="NOKP3" type="text" 
                id="NOKP3" style="width:40px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3');setCheckLogin();" value="" size="5" maxlength="4" />
          <input size="50" type="hidden" id="USER_LOGIN" name="USER_LOGIN" onKeyUp = "setCheckLogin();"
				>
          <br />
          <span id="divCheckUser"></span>
          <!-- close username by IC -->
        </td>
        <td valign="top" ><span class="style7">*Contoh 880808001111</span></td>
      </tr>
      <tr>
        <td valign="top" ><font color="red" >*</font> </td>
        <td valign="top" > Nama </td>
        <td valign="top" > : </td>
        <td valign="top"><input style="text-transform:uppercase;"  name="NAMA" type="text" id="NAMA"  value="" size="50" />
        </td>
        <td valign="top" ><span class="style7">*Seperti yang tertera dalam Kad Pengenalan</span></td>
      </tr>
      <tr>
        <td valign="top" ><font color="red" >*</font> </td>
        <td valign="top" > Jawatan </td>
        <td valign="top" > : </td>
        <td valign="top"><div id="divJawatan" >
            <select id="ID_JAWATAN" name="ID_JAWATAN" onmouseover="getJawatan();"  onChange="getKumpKhidmat();getKlasifikasi();getGred();" >
              <option value="" >SILA PILIH</option>
            </select>
          </div></td>
        <td valign="top" ></td>
      </tr>
      <div id="divKumpKhidmat" ></div>
      <!-- <tr>
				<td valign="top" >	
				<font color="red" >*</font>					
				</td>			
				<td valign="top" >
				Kumpulan Perkhidmatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top">
				
				 <div id="divKumpKhidmat" >
				 <select id="ID_KUMPKHIDMAT" name="ID_KUMPKHIDMAT" onmouseover="getKumpKhidmat();">
				 <option value="" >SILA PILIH</option>
				 </select>
				 </div> 				
				</td>
				<td valign="top" ></td>
			</tr>-->
      <div id="divKlasifikasi" ></div>
      <!-- <tr>
				<td valign="top" >	
				<font color="red" >*</font>					
				</td>			
				<td valign="top" >
				Klasifikasi
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top">
				
				 <div id="divKlasifikasi" >
				<!-- <select id="ID_KLASIFIKASI" name="ID_KLASIFIKASI" onmouseover="getKlasifikasi();">
				 <option value="" >SILA PILIH</option>
				 </select>
				 </div> 				
				</td>
				<td valign="top" ></td>
			</tr>-->
      <tr>
        <td valign="top" ><font color="red" >*</font> </td>
        <td valign="top" > Gred </td>
        <td valign="top" > : </td>
        <td valign="top"><div id="divGred" >
            <select id="ID_GRED" name="ID_GRED" >
              <option value="" >SILA PILIH</option>
            </select>
          </div></td>
        <td valign="top" ></td>
      </tr>
      <tr>
        <td valign="top" ><font color="red" >*</font> </td>
        <td valign="top" > Emel </td>
        <td valign="top" > : </td>
        <td valign="top"><input name="EMELPEMOHON" type="text" id="EMELPEMOHON"  value="" size="50" onKeyUp="ValidateEmailFormat(this,this.value,'EMELPEMOHON_errorSpan')" />
          <div id="EMELPEMOHON_errorSpan"></div></td>
        <td valign="top" ><span class="style7">*Emel rasmi jabatan seperti yang tertera dalam Portal > Direktori JKPTG</span></td>
      </tr>
       <tr>
        <td valign="top" ><font color="red" ></font> </td>
        <td valign="top" > No. Telefon Bimbit </td>
        <td valign="top" > : </td>
        <td valign="top"><input name="NO_HP" type="text" id="NO_HP" onKeyUp="RemoveNonNumeric_V3(this,this.value)" maxlength="15"  value="" size="50" />
        </td>
        <td valign="top" ></td>
      </tr>
      
      <tr>
        <td valign="top" ><font color="red" >*</font> </td>
        <td valign="top" > Nama Penyelia </td>
        <td valign="top" > : </td>
        <td valign="top"><input style="text-transform:uppercase;"  name="PENYELIA" type="text" id="PENYELIA"  value="" size="50"  />
        </td>
        <td valign="top" ><span class="style7">*Pegawai ini akan mengesahkan kelulusan permohonan anda</span></td>
      </tr>
      <tr>
        <td valign="top"></td>
        <td valign="top"></td>
        <td valign="top"></td>
        <td valign="top"><IFRAME 
		         	scrolling="no"  height = "58" width = "160" frameborder="0" 
			 		id="jquery-captcha-refresh" 
			 		src="./servlet/ekptg.view.online.VerificationServlet"> </IFRAME>
          <br>
          <a  href="#" onClick="javascript:doRefreshCaptcha();">Refresh</a> </td>
        <td valign="top" ></td>
      </tr>
      <tr>
        <td valign="top"><font color="red" >*</font> </td>
        <td valign="top">5 Digit Aksara</td>
        <td valign="top">:</td>
        <td valign="top"><input type="text" TABINDEX="5" size="7" maxlength="5"  name="txtCaptChar" id="txtCaptChar"/>
        </td>
        <td valign="top" ></td>
      </tr>
      
       <tr>
        <td valign="top"><font color="red" ></font> </td>
        <td valign="top">Catatan</td>
        <td valign="top">:</td>
        <td valign="top"><textarea cols="30" rows="10" id="CATATAN_PEMOHON" name="CATATAN_PEMOHON"></textarea>
        </td>
        <td valign="top" ></td>
      </tr>
      
      <tr>
        <td valign="top"></td>
        <td valign="top"></td>
        <td valign="top"></td>
        <td valign="top"></td>
        <td valign="top" ></td>
      </tr>
      <tr>
        <td colspan="14"><table border="0" cellpadding="0" cellspacing="0" align="left">
            <tr>
              <td width="8" nowrap></td>
              <td><!--<table border="0" cellpadding="2" cellspacing="2" width="140%">
 <tr>
 <td height="26" >Info Bantuan :</td>
    </tr>
            <tr>
             <td height="26" > Waktu Operasi :  Isnin - Jumaat ( 08:00 pagi - 05:00 petang ) </td>
    </tr>
    <tr>
             <td> <strong>Bahagian Pengurusan ICT Pentadbiran Tanah, JKPTG Persekutuan ( HQ )</strong> </td>
    </tr>
    <tr>
             <td > No. Telefon : 03-8889 3928 / 03-8889 3927 </td>
    </tr>
      
            <tr>
             <td > Faks : 03-88890763 </td>
    </tr>
    
     <tr>
             <td><strong>Selepas Waktu Operasi sila emelkan kepada myetappsupport@jkptg.gov.my</strong></td>
    </tr>
 
      
           
</table>-->
                </fieldset>
          </table></td>
      </tr>
      <tr>
        <td valign="top"></td>
        <td valign="top"></td>
        <td valign="top"></td>
        <td valign="top"><input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onclick="daftarPengguna()" />
          <input type="reset" value="Reset" />
        </td>
        <td valign="top" ></td>
      </tr>
    </table>
    <div id="ID_TABLE_PIP_LOADER" > </div>
    <input type="hidden" name="command" value="">
  </form>
</div>
<script>
/*
daftarPengguna = function() {
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doRegister";
	target = "doRegisterResult";
	doAjaxUpdater(document.f1, url, target, actionName);
}
*/
//diba tambah checking ic daftar pengguna
setCheckLogin = function () {
	//var allkp = "";
	var allkp = document.getElementById("NOKP1").value + document.getElementById("NOKP2").value + document.getElementById("NOKP3").value;
	//alert(" allkp :"+allkp);
	document.getElementById("USER_LOGIN").value = allkp;	
	if(allkp!="")
	{
	
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "checkUSER_LOGIN";
	target = "divCheckUser";
	doAjaxUpdater(document.f1, url, target, actionName);
	}
}


doRefreshCaptcha = function () {
	$jquery('#jquery-captcha-refresh').attr("src","./servlet/ekptg.view.online.VerificationServlet"); 
}

getRefresh = function() {
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "refresh";
	target = "jquery_captcha_refresh";
	doAjaxUpdater(document.f1, url, target, actionName);	
}

//function getNegeri()
getNegeri = function() {
	document.getElementById('ID_BAHAGIAN').value = '';
	document.getElementById('ID_PEJABAT').value = '';
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doGetNegeri";
	target = "divNegeri";
	doAjaxUpdater(document.f1, url, target, actionName);	
}


getJawatan = function() {
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doGetJawatan";
	target = "divJawatan";
	doAjaxUpdater(document.f1, url, target, actionName);	
}

getBahagian = function() {

	var ID_NEGERI = document.getElementById('ID_NEGERI').value;
	
	//alert('ID_NEGERI - '+ ID_NEGERI);
	
	if (ID_NEGERI == "16"){
	document.getElementById('divFullBahagian').style.display = "";
	
	document.getElementById('ID_PEJABAT').value = '';
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doGetBahagian";
	target = "divBahagian";
	doAjaxUpdater(document.f1, url, target, actionName);	
	
	}else {
	document.getElementById('divFullBahagian').style.display = "none";
	
	document.getElementById('ID_PEJABAT').value = '';
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doGetUnit";
	target = "divUnit";
	doAjaxUpdater(document.f1, url, target, actionName);	
	
	}

	
	
}


getAlamat = function() {
	var ID_PEJABAT = document.getElementById('ID_PEJABAT').value;
	
	if(ID_PEJABAT!="")
	{	
		document.getElementById('tr_divAlamat').style.display="";
	}
	else
	{
		document.getElementById('tr_divAlamat').style.display="none";
	}
	
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doGetAlamatPejabat";
	target = "divPejabat";
	doAjaxUpdater(document.f1, url, target, actionName);	
}

getTel = function() {
	var ID_PEJABAT = document.getElementById('ID_PEJABAT').value;
	
	if(ID_PEJABAT!="")
	{	
		document.getElementById('tr_divNoTel').style.display="";
	}
	else
	{
		document.getElementById('tr_divNoTel').style.display="none";
	}
	
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doGetNotelPejabat";
	target = "divNotel";
	doAjaxUpdater(document.f1, url, target, actionName);	
}

getFax = function() {
	var ID_PEJABAT = document.getElementById('ID_PEJABAT').value;
	
	if(ID_PEJABAT!="")
	{	
		document.getElementById('tr_divNoFax').style.display="";
	}
	else
	{
		document.getElementById('tr_divNoFax').style.display="none";
	}
	
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doGetNoFax";
	target = "divNoFax";
	doAjaxUpdater(document.f1, url, target, actionName);	
}

getUnit = function() {
	var ID_NEGERI = document.getElementById('ID_NEGERI').value;
	var ID_BAHAGIAN = document.getElementById('ID_BAHAGIAN').value;
	/*
	if(ID_NEGERI=="")
	{
		alert("Masukan Negeri");
		document.getElementById('ID_NEGERI').focus();
	}
	else if(ID_BAHAGIAN=="")
	{
		alert("Masukan Bahagian");
		document.getElementById('ID_BAHAGIAN').focus();
	}*/
	if(ID_NEGERI!="" && ID_BAHAGIAN!="")
	{
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doGetUnit";
	target = "divUnit";
	doAjaxUpdater(document.f1, url, target, actionName);
	}
}

//diba tambah'
getGred = function() {
	var ID_JAWATAN = document.getElementById('ID_JAWATAN').value;
	
	if(ID_JAWATAN!="")
	{
	//alert('masuk sini');
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doGetGred";
	target = "divGred";
	doAjaxUpdater(document.f1, url, target, actionName);
	}	
}

getKumpKhidmat = function() {
	
	var ID_JAWATAN = document.getElementById('ID_JAWATAN').value;
	
	if(ID_JAWATAN!="")
	{
	//alert('masuk sini');
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doGetKumpKhidmat";
	target = "divKumpKhidmat";
	doAjaxUpdater(document.f1, url, target, actionName);
	}	
}

getKlasifikasi = function() {
	
	var ID_JAWATAN = document.getElementById('ID_JAWATAN').value;
	
	if(ID_JAWATAN!="")
	{
	//alert('masuk sini');
	url = "./servlet/ekptg.view.online.PermohonanID";
	actionName = "doGetKlasifikasi";
	target = "divKlasifikasi";
	doAjaxUpdater(document.f1, url, target, actionName);
	}	
}

ValidateEmailFormat = function(elmnt,inputText,err_span)  
{  
	//alert("MASUK");
	var check = true;
	var atpos = inputText.indexOf("@");
    var dotpos = inputText.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=inputText.length) {
        check = false;
        //
        $jquery("#"+err_span).html("<font class='blink' color='red'>Format Emel Tidak Sah!</font><input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='false' >");
    }
    else
    {
    	$jquery("#"+err_span).html("<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='true' >");
    }
    //alert("check : "+check);
	return check;
}

validateIC_V3 = function(e,elmnt,content,nextElementID) {
	//if it is character, then remove it..
	var keycode;
	if(window.event)keycode = window.event.keyCode;
	else if (e) keycode = e.which;
	else return true;
	
	//alert(keycode);
	
	if((keycode >= 37 && keycode <= 40) || (keycode == 9)) return false;
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
	//goto next column if maximum length reach
	if (content.length == elmnt.maxLength) 
	{
		goTo(nextElementID);
				
	}
		
}



checkDOBByIC_V3 = function(elmnt,content,err_span) {
		year = content.substring(0,2);
 		if (year <=10) {
 			year = parseInt(year) + 2000;
 		} else {
 			year = parseInt(year) + 1900
 		}
		month = content.substring(2,4);
		day = content.substring(4,6);
		var date_str = day + '/' + month + '/' + year ;
		if(isValidDate_V3(date_str)==true)
		{
			$jquery("#"+err_span).html("<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='true' >");
			
		}
		else
		{
			$jquery("#"+err_span).html("<font class='blink' color='red'>Format MyID Tidak Tepat!<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='false' ></font>");
		}
	
}

		
		
function isValidDate_V3(s) {
	  var bits = s.split('/');
	  var d = new Date(bits[2], bits[1] - 1, bits[0]);
	  return d.getFullYear() == bits[2] && d.getMonth() + 1 == bits[1];
	} 




isValidDate_V3XXX = function(dateString)
{
	var parts = dateString.split("/");
	var day = parseInt(parts[0], 10);
	var month = parseInt(parts[1], 10);
	var year = parseInt(parts[2], 10);
	var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
	
    if(!/^\d{2}\/\d{2}\/\d{4}$/.test(dateString))
    {
    	return false;
    }
   
    else if(year < 1000 || year > 3000 || month == 0 || month > 12)
    {
    	return false;
    }
    else if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
    {
	monthLength[1] = 29;
    return day > 0 && day <= monthLength[month - 1];
    }
    else
    {
    	return true;
    }

}

RemoveNonNumeric_V3 = function(elmnt,content)
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < content.length; intIndex++ )
      {
            strBuffer = content.substr( intIndex, 1 );
            // Is this a number
            if(strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      elmnt.value = strReturn;
}

function checkMaxLength_V3(elmnt,content,minlength,err_span)
{
	if(content.length != minlength)
	{
		$jquery("#"+err_span).html("<font color='red'>Sila Masukkan "+minlength+" Aksara Pada Medan Ini!</font><input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='false' >");
	}
	else
	{
		$jquery("#"+err_span).html("<input type='hidden' id='CHECK_"+elmnt.id+"' name='CHECK_"+elmnt.id+"' value='true' >");
	}
}
















daftarPengguna = function()
{
	
	
	
	var bool_check = true;
	
	if(document.getElementById("NOKP1").value=="")
	{
	alert("Masukkan MyID Pengguna Dengan Lengkap!");
	document.getElementById("NOKP1").focus();
	bool_check = false;
	}
	else if(document.getElementById("NOKP2").value=="")
	{
	alert("Masukkan MyID Pengguna Dengan Lengkap!");
	document.getElementById("NOKP2").focus();
	bool_check = false;
	}
	else if(document.getElementById("NOKP3").value=="")
	{
	alert("Masukkan MyID Pengguna Dengan Lengkap!");
	document.getElementById("NOKP3").focus();
	bool_check = false;
	}			   
	else if(document.getElementById("NOKP1").value.length != document.getElementById("NOKP1").maxLength)
	{
	alert("Format MyID Tidak Tepat!");
	document.getElementById("NOKP1").focus();
	bool_check = false;				   
	}
	else if(document.getElementById("CHECK_NOKP1").value=="false")
	{
	alert("Format MyID Tidak Tepat!");
	document.getElementById("NOKP1").focus();
	bool_check = false;				   
	}
	else if(document.getElementById("NOKP2").value.length != document.getElementById("NOKP2").maxLength)
	{
	alert("Format MyID Tidak Tepat!");
	document.getElementById("NOKP2").focus();
	bool_check = false;				   
	}
	else if(document.getElementById("NOKP3").value.length != document.getElementById("NOKP3").maxLength)
	{
	alert("Format MyID Tidak Tepat!");
	document.getElementById("NOKP3").focus();
	bool_check = false;				   
	}
	else if(document.getElementById("NAMA").value=="")
	{
	alert("Masukkan Nama Pemohon!");
	document.getElementById("NAMA").focus();
	bool_check = false;
	}
	
	
	
	
	
	else if(document.getElementById("ID_JAWATAN").value=="")
	{
	alert("Masukkan Jawatan!");
	document.getElementById("ID_JAWATAN").focus();
	bool_check = false;
	}
	/*else if(document.getElementById("ID_BAHAGIAN").value=="")
	{
	alert("Masukkan Bahagian!");
	document.getElementById("ID_BAHAGIAN").focus();
	bool_check = false;
	}*/
	else if(document.getElementById("ID_NEGERI").value=="")
	{
	alert("Masukkan Negeri!");
	document.getElementById("ID_NEGERI").focus();
	bool_check = false;
	}
	else if(document.getElementById("ID_PEJABAT").value=="")
	{
	alert("Masukkan Pejabat/Unit!");
	document.getElementById("ID_PEJABAT").focus();
	bool_check = false;
	}
	else if(document.getElementById("EMELPEMOHON").value=="")
	{
	alert("Masukkan Emel!");
	document.getElementById("EMELPEMOHON").focus();
	bool_check = false;
	}
	else if(document.getElementById("EMELPEMOHON").value!="" && document.getElementById("CHECK_EMELPEMOHON").value=="false")
	{
	alert("Format Emel Tidak Tepat!!");
	document.getElementById("EMELPEMOHON").focus();
	bool_check = false;
	}
	/*else if(document.getElementById("NO_OFFICE").value=="")
	{
	alert("Masukkan No. Telefon Pejabat!");
	document.getElementById("NO_OFFICE").focus();
	bool_check = false;
	}*/
	else if(document.getElementById("txtCaptChar").value=="")
	{
	alert("Masukkan Pengesahan 5 Digit Aksara!");
	document.getElementById("txtCaptChar").focus();
	bool_check = false;
	}
	else if(document.getElementById("PENYELIA").value=="")
	{
	alert("Masukkan Pegawai Penyelia!");
	document.getElementById("PENYELIA").focus();
	bool_check = false;
	}
	
	
	
	
	if(bool_check==true)
	{
		document.getElementById('ID_TABLE_PIP').style.display="none";
		document.getElementById('ID_TABLE_PIP_LOADER').innerHTML ='<table width="100%" cellpadding="0" cellspacing="0"><tr><td align="center"><img src="./img/indicator.gif"></td></tr></table>';
		
		url = "./servlet/ekptg.view.online.PermohonanID";
		actionName = "doRegister";
		target = "doRegisterResult";
		doAjaxUpdater(document.f1, url, target, actionName);
	}
	return bool_check;

}

resetSkrin = function() {
	document.getElementById('ID_TABLE_PIP').style.display="";
	document.getElementById('ID_TABLE_PIP_LOADER').innerHTML ='';
		
}


daftar = function() {

 var dob_code = "";
 var tt = "";

 if (document.f1.idKategori.value == "Individu") { 
	 if (document.f1.txtNoKPBaru1.value == "") { 
			alert('Sila pastikan MyID dimasukkan dengan lengkap');
			document.f1.txtNoKPBaru1.focus();
			doRefreshCaptcha();
			return;
		}
		
		if (document.f1.txtNoKPBaru2.value == "") { 
			alert('Sila pastikan MyID dimasukkan dengan lengkap');
			document.f1.txtNoKPBaru2.focus();
			doRefreshCaptcha();
			return;
		}
		
		if (document.f1.txtNoKPBaru3.value == "") { 
			alert('Sila pastikan MyID dimasukkan dengan lengkap');
			document.f1.txtNoKPBaru3.focus();
			doRefreshCaptcha();
			return;
		}
 } else {
		if (document.f1.txtNoKPCO.value == "") { 
			alert('Sila pastikan MyCOID dimasukkan dengan lengkap');
			document.f1.txtNoKPCO.focus();
			doRefreshCaptcha();
			return;
		}
 } 
		
var dob_code = document.f1.txtNoKPBaru1.value;
if(dob_code.charAt(0)<3)
	{
	 var dum = "20";
	}
	else
	{
	var dum = "19";
	}
var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""	+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);

	//alert("xx"+dob_code);
	
	if (document.f1.txtNoKPBaru1.value != "" && isIc(tt)==false){
		document.f1.txtNoKPBaru1.focus()
		return;
	}
	
	
	
	if (document.f1.nama.value == "") { 
			alert('Sila pastikan nama dimasukkan');
			document.f1.nama.focus();
			return;
	}
	
	if (!emailCheck(document.f1.emel.value)) {
		alert('Sila periksa alamat email anda');
		document.f1.emel.focus();
		return;
	}
	
	if (document.f1.id_negeri.value == "") { 
			alert('Sila pastikan pilih negeri');
			document.f1.id_negeri.focus();
			return;
	}
	
	

	/* remove this validation.
	if (document.f1.txtNoKPBaru1.value.length < 12) {
		alert('Sila masukkan " No KP " yang betul.');
		document.f1.txtNoKPBaru1.focus();
		return;	
	}
	*/
	var string = document.f1.password.value; 
	
	if (string.length < 6 ) { 
		alert('Sila pastikan kata laluan anda dimasukkan dengan betul');
		document.f1.password.focus();
		doRefreshCaptcha();
		return;
	}	
	if (document.f1.password.value != document.f1.password2.value) {
		alert('Sila pastikan kata laluan adalah sama');
		document.f1.password2.focus();
		doRefreshCaptcha();
		return;
	}
	/*if (document.f1.txtNoKPBaru.value == ""){
		alert('Sila masukkan " MyID " terlebih dahulu.');
		document.f1.txtNoKPBaru.focus();
		return;
	} */

	if (document.f1.idKategori.value == "Individu") {
		document.f1.txtNoKPBaru.value = document.f1.txtNoKPBaru1.value+""+ document.f1.txtNoKPBaru2.value+""+document.f1.txtNoKPBaru3.value;
	} else {
		document.f1.txtNoKPBaru.value = document.f1.txtNoKPCO.value
	}
  
    
	url = "./servlet/ekptg.view.online.Portal";
	actionName = "doRegister";
	target = "doRegisterResult";
	doAjaxUpdater(document.f1, url, target, actionName);
}



doEffect = function () {
	new Effect.Highlight('doRegisterResult', {startcolor:'#CEB089',endcolor:'#FFFFFF', restorecolor:'#EFEFEF'});
}

doHide = function () {
	$jquery('#RegistrationForm').hide("slow");
}

doRefreshCaptcha = function () {
	$jquery('#jquery-captcha-refresh').attr("src","./servlet/ekptg.view.online.VerificationServlet"); 
}


isValidEmailAddress = function (emailAddress) {
	var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
	return pattern.test(emailAddress);
}



function emailCheck (emailStr) {

/* The following variable tells the rest of the function whether or not
to verify that the address ends in a two-letter country or well-known
TLD.  1 means check it, 0 means don't. */

var checkTLD=1;

/* The following is the list of known TLDs that an e-mail address must end with. */

var knownDomsPat=/^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;

/* The following pattern is used to check if the entered e-mail address
fits the user@domain format.  It also is used to separate the username
from the domain. */

var emailPat=/^(.+)@(.+)$/;

/* The following string represents the pattern for matching all special
characters.  We don't want to allow special characters in the address. 
These characters include ( ) < > @ , ; : \ " . [ ] */

var specialChars="\\(\\)><@,;:\\\\\\\"\\.\\[\\]";

/* The following string represents the range of characters allowed in a 
username or domainname.  It really states which chars aren't allowed.*/

var validChars="\[^\\s" + specialChars + "\]";

/* The following pattern applies if the "user" is a quoted string (in
which case, there are no rules about which characters are allowed
and which aren't; anything goes).  E.g. "jiminy cricket"@disney.com
is a legal e-mail address. */

var quotedUser="(\"[^\"]*\")";

/* The following pattern applies for domains that are IP addresses,
rather than symbolic names.  E.g. joe@[123.124.233.4] is a legal
e-mail address. NOTE: The square brackets are required. */

var ipDomainPat=/^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;

/* The following string represents an atom (basically a series of non-special characters.) */

var atom=validChars + '+';

/* The following string represents one word in the typical username.
For example, in john.doe@somewhere.com, john and doe are words.
Basically, a word is either an atom or quoted string. */

var word="(" + atom + "|" + quotedUser + ")";

// The following pattern describes the structure of the user

var userPat=new RegExp("^" + word + "(\\." + word + ")*$");

/* The following pattern describes the structure of a normal symbolic
domain, as opposed to ipDomainPat, shown above. */

var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$");

/* Finally, let's start trying to figure out if the supplied address is valid. */

/* Begin with the coarse pattern to simply break up user@domain into
different pieces that are easy to analyze. */

var matchArray=emailStr.match(emailPat);

if (matchArray==null) {

/* Too many/few @'s or something; basically, this address doesn't
even fit the general mould of a valid e-mail address. */

//alert("Email address seems incorrect (check @ and .'s)");
return false;
}
var user=matchArray[1];
var domain=matchArray[2];

// Start by checking that only basic ASCII characters are in the strings (0-127).

for (i=0; i<user.length; i++) {
if (user.charCodeAt(i)>127) {
//alert("Ths username contains invalid characters.");
return false;
   }
}
for (i=0; i<domain.length; i++) {
if (domain.charCodeAt(i)>127) {
//alert("Ths domain name contains invalid characters.");
return false;
   }
}

// See if "user" is valid 

if (user.match(userPat)==null) {

// user is not valid

//alert("The username doesn't seem to be valid.");
return false;
}

/* if the e-mail address is at an IP address (as opposed to a symbolic
host name) make sure the IP address is valid. */

var IPArray=domain.match(ipDomainPat);
if (IPArray!=null) {

// this is an IP address

for (var i=1;i<=4;i++) {
if (IPArray[i]>255) {
//alert("Destination IP address is invalid!");
return false;
   }
}
return true;
}

// Domain is symbolic name.  Check if it's valid.
 
var atomPat=new RegExp("^" + atom + "$");
var domArr=domain.split(".");
var len=domArr.length;
for (i=0;i<len;i++) {
if (domArr[i].search(atomPat)==-1) {
//alert("The domain name does not seem to be valid.");
return false;
   }
}

/* domain name seems valid, but now make sure that it ends in a
known top-level domain (like com, edu, gov) or a two-letter word,
representing country (uk, nl), and that there's a hostname preceding 
the domain or country. */

if (checkTLD && domArr[domArr.length-1].length!=2 && 
domArr[domArr.length-1].search(knownDomsPat)==-1) {
//alert("The address must end in a well-known domain or two letter " + "country.");
return false;
}

// Make sure there's a host name preceding the domain.

if (len<2) {
//alert("This address is missing a hostname!");
return false;
}

// If we've gotten this far, everything's valid!
return true;
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



isIc = function(dtStr){
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
		alert("Format MyID seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada MyID")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada MyID")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan MyID yang sah")
		return false
	}
return true
}


validateXIC = function(e,elmnt,content,nextElementID) {

	//if it is character, then remove it..
	
	
	var keycode;
	if(window.event)keycode = window.event.keyCode;
	else if (e) keycode = e.which;
	else return true;
	
	//alert(keycode);
	
	if((keycode >= 37 && keycode <= 40) || (keycode == 9)) return false;
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumericX(content);
		return;
	}
	//goto next column if maximum length reach
	
	
	if (content.length == elmnt.maxLength) 
	{
	//alert("3:"+nextElementID);
	goTo(nextElementID);
	//nextElementID.focus();
	}
	
	
}

function RemoveNonNumericX( strString )
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
$jquery(document).ready(function () {

alert('Sila hubungi pihak BPICT untuk pendaftaran Pengguna KJP.');
});

</script>
