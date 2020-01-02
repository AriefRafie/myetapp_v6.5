#if ( $userIsLogged )
 <a href='javascript:document.location.href = "logout"' class="font_welcome_red"><u>Logout</u></a>
#else
	#if ( $result == "success" )
	<div class="success">
	Successful login:<br>
	Username:$username
	</div>
	<script>document.getElementById("tableLogin").style.visibility="hidden"</script>
	#elseif ( $result == "failed" )
	<script>document.${formName}.username.focus();</script>
	<div class="error">
	Ralat!! <br>
	Sila periksa ID Pengguna dan Katalaluan anda!
	</div>
	#end
  <div id="inputArea">

  <table cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td>
                   <table cellpadding="1" cellspacing="0" border="0">
                       <tr>
                           <td align="right" class="font_login">
                           ID Pengguna:&nbsp;
                           	<input id="username" name="username" value="" onfocus="emptyLogin()">
                           	</td>
                       </tr>
                       <tr>
                           <td align="right" class="font_login">
                            Katalaluan:&nbsp;
                           	<input type="password" name="password" value="">
                           	</td>
                       </tr>
                       <tr>
                           <td align="right" class="font_login">
                           		<p align="right">
          				
                           		<input id="masuk" class="stylobutton" type="button" value="Masuk" onClick=doLogin()>
          				
          				<!--
          				<img src="../css/Online/login.png" onmouseout="this.src='../css/Online/login_s.png'" onmouseover="this.src='../css/Online/login.png'" 
          				onclick="submitLogin()" style="cursor: pointer;" border="0">
          				-->
       				       <br />
       				       <br />
                     Lupa Katalaluanx? <a href="../forget_password.jsp">Klik Di Sini</a></p>
                   		   <p>&nbsp;</p>
                           <div align="left">Ingin mendapatkan ID Pengguna ? <br />
                             <a href="?portal_module=ekptg.view.utils.OnlineRegistration">Daftar</a>
                             </p>
                   		   </div>
                           <p>&nbsp;</p>
                           </td></tr>				
            </table>
        </td>
    </tr>
</table> 
</div>
#end
<script>

function doLogin(){
    if ( document.${formName}.username.value == "" ) { alert("Sila masukkan ID Pengguna!"); document.${formName}.username.focus(); return; }
    if ( document.${formName}.password.value == "" ) { alert("Sila masukkan katalaluan!"); document.${formName}.password.focus(); return; }
    doAjaxCall${formName}("doLogin");
}

</script>  