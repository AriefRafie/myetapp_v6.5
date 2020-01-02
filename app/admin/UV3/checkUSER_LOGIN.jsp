
$display_info_check_id
<input type="hidden" id="CHECK_USER_LOGIN_$internalType$USER_ID" 
				name="CHECK_USER_LOGIN_$internalType$USER_ID" value="$checkUSERLOGIN" >
<input type="hidden" id="GET_USER_ID_EXIST_$internalType$USER_ID" 
				name="GET_USER_ID_EXIST_$internalType$USER_ID" value="$GET_USER_ID_EXIST" >
#if($GET_USER_ID_EXIST!="")
<script>
//alert('$internalType');
if('$USER_ID'=="")
{
	document.getElementById('mando_PASSWORD_$internalType$USER_ID').innerHTML = "";
	document.getElementById('mando_PASSWORD2_$internalType$USER_ID').innerHTML = "";
}

document.getElementById("USER_NAME_"+'$internalType'+'$USER_ID').value='$viewPenggunaExist.USER_NAME';
document.getElementById("EMEL_"+'$internalType'+'$USER_ID').value='$!viewPenggunaExist.EMEL';

$jquery(document).ready(function () {
	  doDivAjaxCall$formname('div_ROLE_MAIN_$internalType$USER_ID','showListRole','&USER_ID=$USER_ID&GET_USER_ID_EXIST=$GET_USER_ID_EXIST&internalType=$internalType');			 	  
});

</script>
#else
<script>
if('$USER_ID'=="")
{
	document.getElementById('mando_PASSWORD_$internalType$USER_ID').innerHTML = "*";
	document.getElementById('mando_PASSWORD2_$internalType$USER_ID').innerHTML = "*";
}

if($FLAG_PIP!="Y")
{
	document.getElementById("USER_NAME_"+'$internalType'+'$USER_ID').value='';
	document.getElementById("EMEL_"+'$internalType'+'$USER_ID').value='';
}

$jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_ROLE_MAIN_$internalType$USER_ID','showListRole','&USER_ID=$USER_ID&GET_USER_ID_EXIST=$GET_USER_ID_EXIST&internalType=$internalType');			 	  
});
		
</script>
#end

				