tab ID: $EkptgUtil.getTabID("Roles Management",$portal_role) <br>
Seksyen 8: $EkptgUtil.getTabID("Seksyen 8","adminppk") <br>

UserID : ${session.getAttribute("_ekptg_user_id")}<br>
Username : ${session.getAttribute("_portal_username")}
<br>
Security Token:${session.getAttribute("_portal_action")}
<br>
Role: ${session.getAttribute("_portal_role")}
<br>
Negeri:${session.getAttribute("_ekptg_user_negeri")}
<br>
$util.capitalizedFirstCharacter("noor azam abdullah")
<br>
Today : $util.getDate()
<br>
Today : $util.getDate("dd/MM/yy HH:MM:SS")
<br>
$EkptgUtil.subString("hello world from rqweqweqweweqwe",0,5) <br>

<form name="signup" id="signup" method="post" action="#">
 <table>
   <tr>  
     <td colspan="2"><label for="name">Name</label></td>
     <td colspan="2"> <input type="text" name="name" id="name" tabindex="1" /></td>
   </tr>
   <tr>  
     <td colspan="2"><label for="address1">Address Line 1</label></td>
     <td colspan="2"> <input type="text" name="address1" id="address2" tabindex="2" /></td>
   </tr>
   <tr>  
     <td colspan="2"><label for="address2">Address Line 2</label></td>
     <td colspan="2"> <input type="text" name="address2" id="address2" tabindex="3" /></td>
   </tr>
   <tr>  
     <td colspan="2"><label for="city">City</label></td>
     <td colspan="2"> <input type="text" name="city" id="city" tabindex="4" /></td>
   </tr>
   <tr>  
     <td><label for="state">State</label></td>
     <td></td>
     <td><label for="zip">Zip</label></td>
     <td> <input type="text" name="zip" id="zip" tabindex="6" /></td>
   </tr>
   <tr>  
     <td colspan="2"><label for="email">Email Address</label></td>
     <td colspan="2"><input type="text" name="email" id="email" tabindex="7" /></td>
   </tr>
   <tr>  
     <td colspan="4"><input type="submit" name="Submit" value="Submit" tabindex="8" /></td>
   </tr>
 </table>
</form>