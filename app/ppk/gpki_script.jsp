<head>
	<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
    <script type="text/javascript" src="../../library/js/gpki-api.js" ></script>
</head>
<fieldset>
					<legend>
						<strong>Testing DG Cert</strong>
					</legend>
					<div align="left">
						<table border="0" cellpadding="2" cellspacing="2" width="100%">
							<tbody>       
								<tr id="trTarikhDaftar" style="display:none">
									<td scope="row" width="5%" align="right">ID</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">
										<input id="noRujptg" type="text" maxlength="42" size="20" style="text-transform:uppercase;" name="noRujptg" value="$!noRujptg"></input>
									</td>
								</tr>
								<tr>
									<td scope="row" width="5%" align="right">
										<div align="right">ID</div>
									</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">
										<input name="certID" value type="text" maxlength="12" id="certID" value="$!certID">
									</td>
								</tr>                             
								<tr>
									<td scope="row" width="5%" align="right">Pin</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">
										<input name="pin"   id="pin" type="password" maxlength="16" value="$!pin">
									</td>
								</tr>
                                <tr>
									<td scope="row" width="5%" align="right">Data to Sign</td>
									<td width="1%" scope="row">:</td>
									<td width="20%">
										<textarea id="textToSign" maxlength="500" size="30" style="text-transform:uppercase;" name="textToSign" value="$!textToSign">
                                    </textarea>
                                   </td>
								</tr>
                                <tr>
                                <td>
                                <input type="button" id="signButton" value="Simpan" name="signButton" />
                                </td>
                                </tr>
                                
                                <tr>
                                <td width="20%">
										<textarea id="textToSign" maxlength="500" size="30" style="text-transform:uppercase;" name="textToSign" value="$!textToSign">
                                    </textarea>
                                   </td>
								</tr>
                                <tr>
                                <td>
                                <input type="button" id="verifyButton" value="Verify" name="verifyButton" />
                                </td>
                                </tr>
							</tbody>
						</table>
					</div>
				</fieldset>
			</td>
		</tr>
	</tbody>
</table>

<script>

var CERTIFICATE_TYPE = "token";

$(document).ready(function(){
	//alert('123');
	
	//alert('123');
	$("#signButton").click(function(){
		var plainText = encodeURIComponent(Gpki.hash($("#textToSign").val()));
		
		var pin = $("#pin").val();
		var id= $("#certID").val();
		alert('type++++++ '+CERTIFICATE_TYPE);
		sign(CERTIFICATE_TYPE ,id,pin, plainText,detachMode);
		alert('masuk sini');
		Gpki.alert("plainText1: " +plainText);
		
	});
	
});

function parseSignResult(msg){
	$(document).ready(function(){
		var obj = jQuery.parseJSON(msg);
		var statusCode = obj.status_coe;
		var statusMsg = obj.status_message;
		if (statusCode == "0") {
			var signedData = obj.signed_data;
			var startDate = obj.start_date;
			var endDate = obj.end_date;
			var subjectDN = obj.subject_dn;
			var serialNo = obj.serial_no;
			signedData = encodeURIComponent(signedData);
			var certinfo = "<br><br><h2 class='title'>Certificate Data<h2><ul class='listitems'><li>Start Date: "+startDate +"</li><li>End Date: "+endDate +"</li><li>Certificate's Subject DN: "+subjectDN+"</li><li>Serial No: "+serialNo+"</li></ul>";

			$("#gpki-data").html(certinfo+'<textarea name="signedData" cols=""110" rows="15">' + signedData+ '</textarea>');

			Gpki.alert("Successfully Signed the Data");
	
			/* put your logic to handle the result here */

		}else if(statusCode == "12"){
		Gpki.alert("ID not found");

		}else {
			Gpki.alert(statusMsg);
		}
	});
}

/*function sign()
{
		alert('1234');
		var plainText = encodeURICompenent(Gpki.hash($(document.${formName}.textToSign.value)));
		alert('12345');
		var pinNo = document.${formName}.pin.value;
		alert('12346');
		var id= document.${formName}.certID.value;
		alert('12347');
		sign(CERTIFICATE_TYPE ,id,pinNo, plainText,detachMode);
		alert('12348');
		Gpki.alert("plainText: " +plainText);
		alert('12349');
		document.${formName}.submit();	
}*/

</script>