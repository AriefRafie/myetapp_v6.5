<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPK.css" />
<style type="text/css">
<!-- 
input[readonly]{
	background-color:#AAD198;
	border:#546F0E 1px solid;
	color:#000;
}
-->
</style>


#foreach($list in $listSemak)
 	#set($NO_FAIL=$list.noFail)
 	#set($negeri=$list.pmNama_negeri)
 	#set($daerah=$list.namadaerah)
 	#set($unit=$list.namaPejabat)
 	#set($seksyen=$list.seksyen)
 	#set($statusFail=$list.keterangan)
 	#set($tarikhMohon=$list.tarikhMohon)
 	#set($namaSimati=$list.namaSimati)
 	#set($namaSipemohon=$list.namaPemohon)
#end


<body>
 <head>
	<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
    <script type="text/javascript" src="../../library/js/gpki-api.js" ></script>
</head>
	<form name="f1">
		<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BUTIRAN TERPERINCI PERMOHONAN</font></legend>
			<table border="0" cellpadding="1" cellspacing="1" align="center">
						
			<input type="hidden" name="NO_FAIL" id="NO_FAIL" value='$!NO_FAIL'/> 
			<input type="hidden" name="id_perbicaraan" id="id_perbicaraan" value='$!id_perbicaraan'/> 
			<input type="hidden" name="id_fail" id="id_fail" value='$!id_fail'/>
			
            <textarea id="signedText" maxlength="500" size="30" style="text-transform:uppercase;" name="signedText" value="$!signedText" hidden="hidden">
            </textarea>
				 <tr>
            <td width="50%" valign="top"><table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
                <td width="36%" valign="top">NO FAIL</td>
                <td width="1%" valign="top">:&nbsp;</td>
                <td width="63%" valign="top"><font color="blue">$NO_FAIL</font><input name="NO_FAIL" type="hidden" id="NO_FAIL" value="$!NO_FAIL" ></td>
                <!-- <td width="63%" valign="top"><a href="javascript:kembaliSenaraiFail('$NO_FAIL')"><font color="blue"><b>$NO_FAIL</b></font></a></td> --> 
              </tr>
                <tr>
                <td valign="top">NEGERI</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$negeri</font><input name="negeri" type="hidden" id="negeri" value="$!negeri" ></td>
              </tr>
                <tr>
                <td valign="top">DAERAH / JAJAHAN</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$daerah.toUpperCase()</font><input name="daerah" type="hidden" id="daerah" value="$!daerah" ></td>
              </tr>
                <tr>
                <td valign="top">UNIT</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$unit.toUpperCase()</font><input name="unit" type="hidden" id="unit" value="$!unit" ></td>
              </tr>
              </table></td>
            <td width="50%" valign="top"><table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
                <td width="34%" valign="top">STATUS FAIL</td>
                <td width="1%" valign="top">:&nbsp;</td>
                <td width="65%" valign="top"><font color="blue">$statusFail.toUpperCase()</font><input name="statusFail" type="hidden" id="statusFail" value="$!statusFail" ></td>
              </tr>
                <tr>
                <td valign="top">SEKSYEN</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$seksyen.toUpperCase()</font><input name="seksyen" type="hidden" id="seksyen" value="$!seksyen" ></td>
              </tr>
                <tr>
                <td valign="top">TARIKH MOHON</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$tarikhMohon</font><input name="tarikhMohon" type="hidden" id="tarikhMohon" value="$!tarikhMohon" ></td>
              </tr>
                <tr>
                <td valign="top">NAMA PEMOHON</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$namaSipemohon.toUpperCase()</font><input name="namaPemohon" type="hidden" id="namaPemohon" value="$!namaSipemohon" ></td>
              </tr>
                <tr>
                <td valign="top">NAMA SIMATI</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$namaSimati.toUpperCase()</font><input name="namaSimati" type="hidden" id="namaSimati" value="$!namaSimati" ></td>
              </tr>
              </table></td>
          </tr>
    </table>
     	
		</fieldset>
    
    <fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">MAKLUMAT PERINTAH PERBICARAAN</font></legend>
    <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
              <td width="1%">&nbsp;</td>
              <td width="20%">Bil.Bicara</td>
              <td width="79%">:&nbsp;
              $bil_bicara
                    <!-- <input type="text" class="disabled" readonly name="TGBilBicara" value="$previousBil" size="4" /> -->
                 </td><input name="bil_bicara" type="hidden" id="bil_bicara" value="$!bil_bicara" >
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Tarikh Bicara</td>
              <td>:&nbsp;
                   $tarikh_bicara
                    <!-- <input type="text" name="txdTarikhBicara" id="txdTarikhBicara" value="$tarikh_bicara" size="11" maxlength="10" readonly /> -->
                   </td> <input name="tarikh_bicara" type="hidden" id="tarikh_bicara" value="$!tarikh_bicara" >
            </tr>
           
            <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Tarikh Notis</td>
              <td>:&nbsp;
                   $tarikh_notis
                   <!--  <input type="text" size="11" name="txdTarikhNotis" id="txdTarikhNotis" maxlength="10" value="$tarikh_notis" readonly /> -->
                  </td><input name="tarikh_notis" type="hidden" id="tarikh_notis" value="$!tarikh_notis" >
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Masa Bicara</td>
              <td>:&nbsp;
              
              $masa_bicara&nbsp;&nbsp;$jenisMasa
              
                 <!--    <input type="text" name="txtMasaBicara" id="txtMasaBicara" value="$masa_bicara"  maxlength="4" size="4" /> -->
                    
                   </td><input name="masa_bicara" type="hidden" id="masa_bicara" value="$!masa_bicara" >
                   <input name="jenisMasa" type="hidden" id="jenisMasa" value="$!jenisMasa" >
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Tempat Bicara</td>
              <td>:&nbsp;
                    <input type="radio" id="pKptg" $checkP1 checked name="jenisPejabat" value="1"  disabled >
                    Cawangan JKPTG
                    &nbsp;&nbsp;
                    <input type="radio" $checkP2 id="pTanah" name="jenisPejabat" value="2"  disabled>
                    Pejabat Tanah
                    &nbsp;&nbsp;
                    <input type="radio" $checkP3 id="pLain" name="jenisPejabat" value="0" disabled>
                    Lain - lain tempat</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><font color="white">:</font>&nbsp;&nbsp;$selectBicara</td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Alamat</td>
              <td>:&nbsp;
              $alamat1 <input name="alamat1" type="hidden" id="alamat1" value="$!alamat1" >
                <!--     <input type="text" $!addressReadonlyClass $!addressReadonly size="52" name="TGAlamatBicara1" id="TGAlamatBicara1" value="$alamat1" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td> -->
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><font color="white">:</font>&nbsp;
              $alamat2 <input name="alamat2" type="hidden" id="alamat2" value="$!alamat2" >
                    <!-- <input $!addressReadonly $!addressReadonlyClass type="text" size="52" name="TGAlamatBicara2" id="TGAlamatBicara2" value="$alamat2" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td> -->
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><font color="white">:</font>&nbsp;
              $alamat3 <input name="alamat3" type="hidden" id="alamat3" value="$!alamat3" >
                    <!-- <input $!addressReadonly $!addressReadonlyClass type="text" size="52" name="TGAlamatBicara3" id="TGAlamatBicara3" value="$alamat3" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td> -->
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Poskod</td>
              <td>:&nbsp;
              $poskod <input name="poskod" type="hidden" id="poskod" value="$!poskod" >
                   <!--  <input type="text" $!addressReadonlyClass $!addressReadonly onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" name="TGPoskod" id="TGPoskod" maxlength="5" size="5" value="$poskod" /></td -->
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Negeri</td>
              <td>:&nbsp;&nbsp;$!selectNegeri</td> 
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Pegawai Pengendali</td>
              <td>:&nbsp;&nbsp;$!peg_pengendali</td> <input name="peg_pengendali" type="hidden" id="peg_pengendali" value="$!peg_pengendali" >
            </tr>
              </table>
			</fieldset>
           
			
			<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">MAKLUMAT TANDANGAN DIGITAL</font></legend>
    <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
              <td width="1%">&nbsp;</td>
              <td width="20%" >NAMA</td>
               <td width="1%" valign="top">:&nbsp;</td>
              <td width="78%">&nbsp;<b> $!username </b></td>
                   
             <tr>
              <td width="1%">&nbsp;</td>
              <td width="20%">No Kad Pengenalan</td>
               <td width="1%" valign="top">:&nbsp;</td>
              <td width="78%">&nbsp;<b> $!userlogin </b><input name="noKP" type="hidden" maxlength="12" id="certID" value="$!userlogin" ></td>
              </tr> 
            <!-- <tr>
              <td valign="center"><font color="red">*</font></td>
              <td  valign="center">Id</td>
               <td valign="center">:&nbsp;</td>
              <td valign="center">&nbsp; <input type="text" name="certID" id="certID" value="$!certID"/></td>
            </tr> -->
                <tr>
              <td valign="center"><font color="red">*</font></td>
              <td  valign="center">Pin</td>
               <td valign="center">:&nbsp;</td>
              <td valign="center">&nbsp; <input type="password" name="pin" id="pin" size="10"/></td>
            </tr>
              </table>
			</fieldset>	
			
			<table width="100%"  cellpadding="1" cellspacing="1" border="0">
			<tr><td align=center colspan=3>&nbsp;
			
			<input name="textToSign" type="hidden" id="textToSign" value="$!textToSign" >
			</table>
							
			<table width="100%"  cellpadding="1" cellspacing="1" border="0">
			<!--  <tr><td align=center colspan=3><input type="button" name="signButton" id="signButton" value="Tandatangan Digital dan Hantar" align="left"/></td></tr>-->
			<tr><td align=center colspan=3><input type="button" name="hantar" id="hantar" value="Tandatangan Digital dan Hantar" align="left" onclick="sendToDigitalSign();" /></td></tr>
			
			</table>	
    	<div id="locationSaveData" ></div>   
	</form>
</body>

<script type="text/javascript">

	/**function openPopupPNB(NO_FAIL,id_perbicaraan,id_fail,signedData){
		input_box = confirm("Sila pastikan butiran yang dihantar adalah tepat!");
		
		if (input_box == true) {
			try {
				window.opener.cetakBorangD_X(NO_FAIL,id_perbicaraan,id_fail,signedData);
			}catch (err) {}
		   	window.close();	
		    return false;
	    
		}
		
	}**/

	function sendToDigitalSign() {
		input_box = confirm("Sila pastikan butiran yang dihantar adalah tepat!");
		if (input_box == true) {
			document.f1.method="post";
			document.f1.action="ekptg.view.ppk.FrmIntegrasiDGCertPerintah?command=sahTandatangan";
			document.f1.submit();			
		}
	
	}


</script>

#if($flagSimpan == "Y")
<script>
openPopupPNB('$NO_FAIL','$id_perintah','$id_fail','');
</script>
#end

<script type="text/javascript">

	var CERTIFICATE_TYPE = "token";
	var detachMode = "true";

	$(document).ready(function(){
		//document.f1.signButton.value = "Please Wait!";
		//document.f1.signButton.disabled = "disabled";
	
		//var plainText = encodeURIComponent(Gpki.hash($("#textToSign").val()));
		$("#signButton").click(function(){
			var data_tosign = $("#NO_FAIL").val() + $("#negeri").val()+ $("#daerah").val()+ $("#unit").val()+ $("#statusFail").val()+ $("#seksyen").val()+ $("#tarikhMohon").val()+ $("#namaPemohon").val()+ $("#namaSimati").val()+ $("#bil_bicara").val()+ $("#tarikh_bicara").val()+ $("#tarikh_notis").val()+ $("#masa_bicara").val()+ $("#jenisMasa").val()+ $("#pKptg").val()+ $("#pTanah").val()+ $("#pLain").val()+ $("#alamat1").val()+ $("#alamat2").val()+ $("#alamat3").val()+ $("#poskod").val()+ $("#peg_pengendali").val();
			//alert("data_tosign:::: "+data_tosign);
			var dataToSign = data_tosign;
			//alert("dataToSign "+dataToSign);
			var plainText = encodeURIComponent(Gpki.hash(dataToSign));
			var pin = $("#pin").val();
			//alert("pin "+pin);
			var id= $("#certID").val();
			//alert("id "+id);
			sign(CERTIFICATE_TYPE ,id,pin, plainText,detachMode);
			//alert('masuk sini');
			//Gpki.alert("plainText1: " +plainText);
			//umpuk signData
				//$("#signedText").val(plainText);
			
		});
		
	});

	function parseSignResult(msg){
		//alert("parseSignResult");
		$(document).ready(function(){
			var obj = jQuery.parseJSON(msg);
			var statusCode = obj.status_code;
			var statusMsg = obj.status_message;
			
			//alert("statusCode : "+statusCode+" statusMsg : "+statusMsg);		
			if (statusCode == "0") {
				var signedData = obj.signed_data;
				var startDate = obj.start_date;
				var endDate = obj.end_date;
				var subjectDN = obj.subject_dn;
				var serialNo = obj.serial_no;
						
				//umpuk signData
				
				signedData = encodeURIComponent(signedData);
				console.log("1st signedData:"+signedData);
				//saveSignedData(signedData);
				//doDivAjaxCall$formname('div_temp','saveSignedData','signedData='+signedData);
				
				//alert("signedData:"+signedData);
				$("#signedText").val(signedData);
						
				var certinfo = "<br><br><h2 class='title'>Certificate Data<h2><ul class='listitems'><li>Start Date: "+startDate +"</li><li>End Date: "+endDate +"</li><li>Certificate's Subject DN: "+subjectDN+"</li><li>Serial No: "+serialNo+"</li></ul>";

				$("#gpki-data").html(certinfo+'<textarea name="signedData" cols=""110" rows="15">' + signedData+ '</textarea>');
			
				//var field = "<input type=\"text\" id=\"startDate\" name=\"startDate\" value=\""+startDate+"\" >"+			
				//Gpki.alert("Successfully Signed the Data");
	
				/* put your logic to handle the result here */
				var NO_FAIL = $("#NO_FAIL").val();
				var id_perbicaraan = $("#id_perbicaraan").val();
				var id_fail = $("#id_fail").val();
				//openPopupPNB(NO_FAIL,id_perbicaraan,id_fail,signedData);
			
				//saveSignedData(NO_FAIL,id_perbicaraan,id_fail,signedData);
				simpan();

			}else if(statusCode == "12"){
				Gpki.alert("ID not found");

			}else {
			Gpki.alert(statusMsg);
			}
		});
	}

	$(document).ready(function(){
		$("#verifyButton").click(function() {
			var originalText = encodeURIComponent(Gpki.hash($("#textToSign").val()));			
			//alert("signedText " + encodeURIComponent($("#signedText").val()));
			var signedText = encodeURIComponent($("#signedText").val());
			$("#originalText").val(signedText);
			//alert("signedText " + signedText);
			verify(originalText, signedText);
		});
	});

	function parseVerifyResult(msg){
		$(document).ready(function(){
			var obj = jQuery.parseJSON(msg);
			var statusCode = obj.status_code;
			//alert("statusCode " + statusCode);
			var statusMsg = obj.status_message;
		
			$("#gpki-data").html(statusMsg);
			if(statusCode == "0") {
				var serialNo = obj.serial_no;
				var validity = obj.cert_validity;
				var subjectDn = obj.subject_dn;
				var signedP = obj.signed_payload;
				var plainP = obj.plain_payload;
				
				msg = "THIS signature is *VALID*" + "serialNo = " + serialNo + "\n" + "validity = " + validity + "\n" + "subjectDn = " + subjectDn;
				Gpki.alert(msg);
				
			}else {
				Gpki.alert("This signature is *INVALID* :" + statusMsg);
			}
		});
	
	}

	function simpan() {
		var NO_FAIL = document.f1.NO_FAIL.value;
		var id_perintah = document.f1.id_perintah.value;
		var idfail = document.f1.id_fail.value;
		var signedData = $("#signedText").val();
	
		document.f1.method="post";
		document.f1.action="ekptg.view.ppk.FrmIntegrasiDGCertPerintah?command=simpanTemp";
		//document.f1.command.value = "simpanTemp";
		document.f1.submit();
	
	}

</script>