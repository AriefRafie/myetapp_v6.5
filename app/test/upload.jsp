<form name="f1" action="" enctype="multipart/form-data" method="post"
	 onsubmit="setTimeout('fileupload_ajax_query_upload_status()', 1000)">
  #foreach( $i in [1..$num_files] )
  <input id="fileupload" name="fileupload" type=file size=40 /><br/>
  #end
 <input name="idDokumen" type="hidden" value="1" />
 <input type="submit" value="Upload" onClick="Upload()">
</form>

<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>
 

List of files:<br> 
#foreach ( $fail in $SenaraiFail )
<a href="#" onClick="javascript:doOpen($fail.id)">$fail.nama_fail</a><br>
#end	
 
 
#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">File successfully uploaded.</div>";
</script>
#end

<script language="JavaScript">
function Upload(){
	var id = document.f1.idDokumen.value ;
	document.f1.action = "?command=uploadFile&idDokumen="+id;
	document.f1.submit();

}

function doOpen(id) {
	    var url = "../servlet/ekptg.test.DisplayBlob?id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}

</script>