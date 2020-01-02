Online Registration <br><br>

Enter some text:<br>
<input id="hello-name"><br>
<input id="hello-submit" type="button" value="Registered!" onclick="doRegister()">
<br>
#if ( $name != "" )
<b><font size="4">
HELLO THERE...<br> $util.putLineBreak("$name")
</font></b>
#end

<script>
function doRegister(){
   doAjaxCall${formName}("register");
}
</script>


<br> <a href=".">Main</a>