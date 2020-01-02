Test1

${session.getAttribute("_portal_action")}

<br>

<form name=f>
<input type=button value=PopUp onClick="showWindow()">

<br>
Kod Negeri:
<br>

<input name="kod_negeri" size="10" value=""  onkeyup="this.value=this.value.toUpperCase();">


</form>

<script>
function showWindow()
{
	    //var url = "${session.getAttribute("_portal_action")}?_portal_module=ekptg.view.utils.FrmNegeri";
	    var url = "../y/${securityToken}/ekptg.view.utils.FrmNegeri";
	    //var url = "../x/${securityToken}/ekptg.test.Test1";
	    var hWnd = window.open(url,'printuser','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}

function setId(id)
{
	window.opener.document.forms[0].student_id.value = id;
	window.close();
	
}
</script>

</script>
