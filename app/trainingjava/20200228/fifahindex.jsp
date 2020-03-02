20200228/index.jsp
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
        <td><input type="button" name="one" value ="simpan" onclick="simpanLampiran()">      
      </td></tr>
</table></fieldset>


<script>
function simpanLampiran(){
	var dok = document.${formName}.file.value;
	if(dok==''){
		alert('Sila pilih fail terlebih dahulu');
		return;
	} else {
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
	}
	//document.${formName}.command.value="upload";
	document.${formName}.action="?_portal_module=latihan.sip.afifah&command=upload";
  document.${formName}.submit();
}	
	</script>