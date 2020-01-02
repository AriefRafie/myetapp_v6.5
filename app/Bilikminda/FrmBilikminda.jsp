<br>
<input type="hidden" name="user_id" id="user_id" />

<fieldset>
<legend>Carian</legend>
<table width="100%" >
<tr>
<td width="1%">
</td>
<td width="28%">
User name
</td>
<td width="1%">
:
</td>
<td width="70%">
<input type="text" name="username" id="username" value="$user_name" >
</td>
</tr>
<tr>
<td >
</td>
<td >
Negeri
</td>
<td >
:
</td>
<td >



<select id="negeri" name="negeri" onChange="showBandar(this.value)" >
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
Bandar
</td>
<td>
:
</td>
<td>
<div id="div_bandarCarian">
<select id="bandar" name="bandar">
<option value = "" >Sila Pilih</option>
</select>

</div>
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
			<input type="button" name="cariRekod" id="cariRekod" value="Cari" onclick="javascript:cariData();" />  
			<input type="button" name="resetRekod" id="resetRekod" value="Reset" onclick="javascript:resetData();" />          
   			
   		</td>		
		</tr>

</table>

</fieldset>


<fieldset>
<legend>Senarai Pengguna</legend>
<!-- #parse("app/utils/record_paging.jsp") -->
<input type="button" name="tambahRekod" id="tambahRekod" value="Tambah" onclick="javascript:bukaSkrin();" />   
<table width="100%" border="0">
<tr  class="table_header">
 <td >Bil.</td>
 <td >User Login</td>
 <td >User Name</td>
 <td >Nama Negeri</td>
 <td align="center" >Tindakan</td>
</tr>


#if($listUser.size()==0)
<tr>
 <td class="$row" colspan="5">Tiada Rekod</td>
</tr>
#else
	#set ( $cnt = 0 )
	#foreach ( $dataUser in $listUser )
	#set ( $cnt = $cnt + 1 )
	#if ( ($cnt % 2) == 0 )
	        	#set( $row = "row2" )
	#else
	        	#set( $row = "row1" )
	#end
	<tr>
	 <td class="$row">$cnt</td>
	 <td class="$row">
	 
	 <a href="javascript:paparUser('$dataUser.USER_ID')"><font color="blue">
    $dataUser.USER_LOGIN
     </font></a>
	 
	 
	 
	 
	 </td>
	 <td class="$row">$dataUser.USER_NAME</td>
	 <td class="$row">$dataUser.NAMA_NEGERI</td>
	  <td class="$row" align="center">
	 
	 <a href="javascript:hapusUser('$dataUser.USER_ID')"><font color="blue">
    X
     </font></a>
	</tr>
	#end
#end
</table>
</fieldset>




<script>
//comment

function showBandar(id_negeri)
{

	doDivAjaxCall$formname('div_bandarCarian','getListBandar','id_negeri='+id_negeri);
}

function paparUser(user_id)
{
	document.${formName}.user_id.value = user_id;
	//doAjaxCall${formName}("papar_user");
	document.${formName}.command.value = "papar_user";	
	document.${formName}.submit();
}

function hapusUser(user_id)
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {	
	document.${formName}.user_id.value = user_id;
	doAjaxCall${formName}("hapus_user");
	//document.${formName}.command.value = "hapus_user";	
	//document.${formName}.submit();
	}
}

function bukaSkrin()
{
	//doAjaxCall${formName}("buka_skrin");
	document.${formName}.command.value = "buka_skrin";
	document.${formName}.submit();
}

function cariData()
{
	doAjaxCall${formName}("cari_rekod");
	//document.${formName}.command.value = "cari_rekod";
	//document.${formName}.submit();
}

function resetData()
{
	doAjaxCall${formName}("reset_data");
	//document.${formName}.command.value = "reset_data";
	//document.${formName}.submit();
}
</script>
	