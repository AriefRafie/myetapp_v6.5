<TABLE BORDER=4>
<TR>
<TD>
<INPUT TYPE="text"   NAME="Input" Size="16">
<br>
</TD>
</TR>
<TR>
<TD>
<INPUT TYPE="button" NAME="one"   VALUE="  1  " OnClick="getValueCal('1')">
<INPUT TYPE="button" NAME="two"   VALUE="  2  " OnCLick="getValueCal('2')">
<INPUT TYPE="button" NAME="three" VALUE="  3  " OnClick="getValueCal('3')">
<INPUT TYPE="button" NAME="plus"  VALUE="  +  " OnClick="getValueCal(' + ')">
<br>
<INPUT TYPE="button" NAME="four"  VALUE="  4  " OnClick="getValueCal('4')">
<INPUT TYPE="button" NAME="five"  VALUE="  5  " OnCLick="getValueCal('5')">
<INPUT TYPE="button" NAME="six"   VALUE="  6  " OnClick="getValueCal('6')">
<INPUT TYPE="button" NAME="minus" VALUE="  -  " OnClick="getValueCal(' - ')">
<br>
<INPUT TYPE="button" NAME="seven" VALUE="  7  " OnClick="getValueCal('7')">
<INPUT TYPE="button" NAME="eight" VALUE="  8  " OnCLick="getValueCal('8')">
<INPUT TYPE="button" NAME="nine"  VALUE="  9  " OnClick="getValueCal('9')">
<INPUT TYPE="button" NAME="times" VALUE="  x  " OnClick="getValueCal(' * ')">
<br>
<INPUT TYPE="button" NAME="clear" VALUE="  c  " OnClick="getValueCal('')">
<INPUT TYPE="button" NAME="zero"  VALUE="  0  " OnClick="getValueCal('0')">
<INPUT TYPE="button" NAME="DoIt"  VALUE="  =  " OnClick="kiraTotal()">
<INPUT TYPE="button" NAME="div"   VALUE="  /  " OnClick="getValueCal(' / ')">
<br>
</TD>
</TR>
</TABLE>
<script>
function  getValueCal(x)
{
document.${formName}.Input.value += x;
}
function  kiraTotal()
{
document.${formName}.Input.value = eval(document.${formName}.Input.value)
}
</script>





<br /><br />


SALAM DUNIA
<br>
<br>
get simple context.put from contorller : $displayMesej
<br>
<br>
TEST SUBMIT BUTTON
<br>
<input name="cmdTestSubmit" id="cmdTestSubmit" type="button" value="TestPrintlnCommand" onClick="cubaSubmitButton()">
<input name="cmdTestSubmit1" id="cmdTestSubmit1" type="button" value="TestContextPut" onClick="hantarContextPutValue()">
<br>
<br>
TEST AJAX BUTTON
<br>
<input name="cmdTestSubmit2" id="cmdTestSubmit2" type="button" value="TestPrintlnAjax" onClick="cubaSubmitButtonAjax()">
<input name="cmdTestSubmi3" id="cmdTestSubmit3" type="button" value="TestContextPutAjax" onClick="hantarContextPutValueAjax()">

<script>

function cubaSubmitButton() {
	document.${formName}.command.value = "testSubmit";
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