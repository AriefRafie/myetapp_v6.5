
<table border="1">
  <tr>
  <td >
 SKRIN FIRST
  </td>
  </tr>
  <tr>
	<td>Input 1 &nbsp;:</td>
    <td><input name="t1" id="t1" value="$t1"/></td>
  </tr>
  
  <tr>
 	<td> &nbsp;</td>
    <td><input type="button" name="button1" id="button1" value="PanggilSkrin" onclick="PanggilSkrin()"/></td>
  </tr>
  
  
  
<tr><td colspan="2">


<div id="skrin1"></div>

</td></tr>


</table>

<script>
	function PanggilSkrin()
	{
		doDivAjaxCall${formName}("skrin1", "Panggilskrin", "");
	}
	
	function btnTutup()
	{
		doDivAjaxCall${formName}("skrin1", "tutup_Panggilskrin", "");
	
	}

	
</script>