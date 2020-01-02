<br>

<fieldset>
<legend>Maklumat Pengguna (Insert)</legend>
<table width="100%" >



<tr>
<td width="1%">
<font color="red">*</font>
</td>
<td width="28%">
User Login
</td>
<td width="1%">
:
</td>
<td width="70%">
<input type="text" name="userlogin" id="userlogin" value="$user_login" >
</td>
</tr>

<tr>
<td >
<font color="red">*</font>
</td>
<td >
User Name
</td>
<td >
:
</td>
<td >
<input type="text" name="username" id="username" value="$user_name" >
</td>
</tr>




<tr>
<td >
<font color="red">*</font>
</td>
<td >
Negeri
</td>
<td >
:
</td>
<td >



<select id="negeri" name="negeri">
<option value = "" >Sila Pilih</option>
#foreach ( $neg in $senarai_negeri )

#set ( $selected_negeri = "" )
#if($id_negeri==$neg.ID_NEGERI)
#set ( $selected_negeri = "selected" )
#end

<option $selected_negeri value="$neg.ID_NEGERI" >
$neg.NAMA_NEGERI
</option>
#end
</select>


</td>
</tr>
<tr>
		<td>
		</td>
		<td>
		</td>
		<td>
		</td>
		<td>
			<input type="button" name="insertRekod" id="insertRekod" value="Insert" onclick="javascript:insertData();" />  
				
   		</td>		
		</tr>

</table>

</fieldset>

<script>




function insertData()
{
	if(document.${formName}.userlogin.value == "")
	{
		alert("Sila masukkan user login");
		document.${formName}.userlogin.focus();
	}
	else if(document.${formName}.username.value == "")
	{
		alert("Sila masukkan user name");
		document.${formName}.username.focus();
	}
	else if(document.${formName}.negeri.value == "")
	{
		alert("Sila masukkan negeri");
		document.${formName}.negeri.focus();
	}
	else
	{
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
		//doAjaxCall${formName}("insert_data");
		document.${formName}.command.value = "insert_data";
		document.${formName}.submit();
		}
	}
}
</script>

