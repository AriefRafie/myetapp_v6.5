arief/uploadDoc.jsp
<br>
   <fieldset>
   <legend>MUATNAIK DOKUMEN</legend>
   <table width="100%" border="0">
   <tr>
     <td> Dokumen </td>
     <td width="1%">:</td>
     <td> <input id="file" name="file" type="file">
     </td>
      </tr>
      <tr> 
        <td><input type="button" name="one" value ="simpan" onclick="saveLampiran()">      
      </td></tr>
</table></fieldset>


<script>
function saveLampiran(){
	var doc = document.${formName}.file.value;
	if(doc==''){
		alert('Sila pilih fail terlebih dahulu');
		return;
	} else {
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
	}
	//document.${formName}.command.value="upload";
	document.${formName}.action="?_portal_module=latihan.sip.arief.muatnaikDokument&command=upload"; 
  document.${formName}.submit();
}
function hantar() {
	//document.${formName}.command01.value = "testSubmit";
	document.${formName}.submit();
}

function hantarContextPutValue() {
	document.${formName}.command.value = "hantarContext";
	document.${formName}.submit();
}


function cubaSubmitButtonAjax() {
	doAjaxCall${formName}("testSubmit");
}

function hantarContextPutValueAjax() {
	doAjaxCall${formName}("hantarContext");
}

</script>