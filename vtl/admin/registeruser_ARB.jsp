<br /><br />



<input type="hidden" id="user_id" name="user_id" value="$!user_id"  />
<fieldset>
<legend>Maklumat Pengguna</legend>
<table border="0" cellpadding="2" cellspacing="2" width="100%">    
    <tr>
    	<td width="1%"><font color="red">*</font></td>
        <td  width="20%">ID Pengguna</td>
        <td width="1%">:</td>
        <td width="78%">
        
        <input onKeyPress="return gotoNextTabIndex(this, event)" $disablity autocomplete="off" name="user_login" size="20" value="$!user_login">
        #if($alert_wujud == "yes")
        <font color="red">ID Pengguna '$!user_login' Telah Wujud</font>
        #else
        
        #end
        </td>
    </tr>
    <tr>
    <td ><font color="red">*</font></td>
        <td >Kata Laluan</td>
        <td >:</td>
        <td><input onKeyPress="return gotoNextTabIndex(this, event)" type=password name="user_password" size="20" value="$!user_password"></td>
    </tr>
        <tr>
        <td ><font color="red">*</font></td>
            <td >Pastikan Kata Laluan</td>
             <td >:</td>
            <td><input onKeyPress="return gotoNextTabIndex(this, event)" type=password name="user_password2" size="20" value="$!user_password2"></td>
    </tr>
    <tr>
    <td ><font color="red">*</font></td>
        <td >Nama Pengguna</td>
         <td >:</td>
        <td><input onKeyPress="return gotoNextTabIndex(this, event)" autocomplete="off" name="user_name" size="50" value="$!user_name"></td>
    </tr>
    <tr>
    <td >&nbsp;</td>
        <td>E-mel</td>
         <td >:</td>
        <td><input type="email" onKeyPress="return gotoNextTabIndex(this, event)" autocomplete="off" name="user_email" size="30" value="$!user_email" id="user_email"></td>
    </tr>    
    <tr>
    <td >&nbsp;</td>
        <td>Jawatan</td>
         <td >:</td>
        <td><select name="id_jawatan" id="id_jawatan"  class="autoselect" > 
            <option value="" >SILA PILIH JAWATAN</option>
            #foreach($ln in $list_jawatan) 
            <option value="$!ln.ID_JAWATAN" 
            #if($ln.ID_JAWATAN==$id_jawatan) 
            selected="selected" 
            #end>
            $!ln.NAMA_JAWATAN
            </option>
            #end 
            </select></td>
    </tr>
    
    <tr>
    <td ><font color="red">*</font></td>
        <td>Negeri</td>
         <td >:</td>
        <td>
        
        <select name="id_negeri" id="id_negeri" onchange="selectNegeri()" class="autoselect" > 
        <option value="" >SILA PILIH NEGERI</option>
		#foreach($ln in $list_negeri) 
		<option value="$!ln.ID_NEGERI" 
        #if($ln.ID_NEGERI==$id_negeri) 
        selected="selected" 
        #end>
        $!ln.KOD_NEGERI - $!ln.NAMA_NEGERI
        </option>
		#end 
		</select>
        
        </td>
    </tr>
    #if($id_negeri != "")
    <tr>
    <td ><font color="red">*</font></td>
        <td>Jenis Pengguna</td>
         <td >:</td>
        <td>
        #set($array_jenis_pejabat= ["9", "8"])
            <select name="jenis_pejabat" id="jenis_pejabat" onchange="checkPejabat()" class="autoselect" > 
            <option value="" >SILA PILIH JENIS PENGGUNA</option>
            #foreach($ln in $array_jenis_pejabat) 
            <option value="$!ln" 
            #if($ln==$jenis_pejabat) 
            selected="selected" 
            #end>
            #if($ln == "9")
            AMANAH RAYA BERHAD
            #else
            MAHKAMAH TINGGI
            #end
          
            </option>
            #end 
            </select>
        
        </td>
    </tr>
    #end
    
    #if($jenis_pejabat != "")
           <tr>
           <td ><font color="red">*</font></td>
            <td >Pejabat</td> 
             <td >:</td>              
            <td>             
    	    <select name="id_pejabat" id="id_pejabat" onchange="selectPejabat()" class="autoselect" > 
            <option value="" >SILA PILIH PEJABAT</option>
            #foreach($ln in $list_pejabat) 
            <option value="$!ln.ID_PEJABAT" 
            #if($ln.ID_PEJABAT==$id_pejabat) 
            selected="selected" 
            #end>
            $!ln.NAMA_PEJABAT
            </option>
            #end 
            </select>
            </td>
    </tr>
    #end
    
     #if($id_pejabat != "")
    <tr>
    <td></td>
     <td valign="top" >Alamat</td>
      <td valign="top">:</td>
                   <td valign="top">  
                   
                    #if($!NamaPejabat != "")
                    $!NamaPejabat<br />
                    #end
                
                    #if($!Alamat1 != "")
                    $!Alamat1<br />
                    #end
                    
                    #if($!Alamat2 != "")
                    $!Alamat2 <br />
                    #end
                    
                    #if($!Alamat3 != "")
                    $!Alamat3 <br />
                    #end
                    
                    #if($!Poskod  != "")
                    $!Poskod <br />
                    #end 
                    
                    #if($!Bandar  != "")
                    $!Bandar <br />
                    #end 
                    
                    #if($!Negeri  != "")
                    $!Negeri <br />
                    #end
                    
                    #if($!No_tel  != "")
                    $!No_tel <br />
                    #end 
                    
                    
                    
                                 
                    </td>
    </tr>
   
          
    
     <tr>
     <td ><font color="red">*</font></td>
        <td >Role</td>
         <td >:</td>
        <td>
          
          <select name="role" id="role"  class="autoselect" > 
           
            #foreach($ln in $list_role) 
            <option value="$!ln.NAME" 
            #if($ln.NAME==$role) 
            selected="selected" 
            #end>
            $!ln.NAME
            </option>
            #end 
            </select> 
               </td>
    </tr>
    <tr>
    <td></td>
    <td></td>
    <td></td>
    <td>
    
    <input type="button" name="cmdSimpan"  id="cmdSimpan" value="Simpan" onClick="javascript:add()" />
    #if( $!user_id != "")
    <input type="button" name="cmdHapus"  id="cmdHapus" value="Hapus" onClick="javascript:hapus()" />
    #end
    <input type="button" name="cmdKosong"  id="cmdKosong" value="Kosongkan" onClick="javascript:kosong()" />
    </td>
    </tr>   
  #end
  
</table>
</fieldset>
<br />
<fieldset>
<legend>Senarai Pengguna</legend>
	
		#set ($pagingTitle = "Jumlah Carian")
		#parse("app/utils/record_paging.jsp")
<table width="100%" border="0">
<tr class="table_header">
<td width="4%">
Bil
</td>
<td width="20%">
User Login
</td>
<td width="24%">
User Name
</td>
<td width="18%">
Jenis Pengguna
</td>
<td width="34%">
Pejabat
</td>
</tr>
#if($list_user.size() > 0)

#foreach($ln in $list_user)
    #if(($ln.BIL%2) !=0 )
    #set($row = 'row1')
    #else
    #set($row = 'row2')
    #end
<tr class="$row" >
<td >
$ln.BIL
</td>
<td >

<a href="javascript:getUser($ln.USER_ID)" ><font  color="blue"><b>$ln.USER_LOGIN</b></font></a>
</td>
<td >
$ln.USER_NAME
</td>
<td >
$ln.JENIS_PENGGUNA
</td>
<td >
$ln.NAMA_PEJABAT $ln.ALAMAT1 $ln.NAMA_BANDAR
</td>
</tr>
#end
#else
<tr>
<td colspan="5">
Tiada Rekod
</td>
</tr>
#end
</table>
</fieldset>


<script  type="text/javascript" >
		function selectNegeri()
		{		
		doAjaxCall${formName}("select_negeri");		
		}
		
		function selectPejabat()
		{		
		doAjaxCall${formName}("select_pejabat");		
		}
		
		function checkPejabat()
		{		
		doAjaxCall${formName}("check_pejabat");		
		}
		
		function add()
		{	
		
		if(document.${formName}.user_login.value == "")
		{
		alert("Sila Masukkan ID Pengguna");	
		document.${formName}.user_login.focus();
		}
		else if(document.${formName}.user_password.value == "")
		{
		alert("Sila Masukkan Kata Laluan");	
		document.${formName}.user_password.focus();
		}
		else if(document.${formName}.user_password2.value == "")
		{
		alert("Sila Masukkan Sahkan Kata Laluan");	
		document.${formName}.user_password2.focus();
		}
		else if(document.${formName}.user_name.value == "")
		{
		alert("Sila Masukkan Nama Pengguna");	
		document.${formName}.user_name.focus();
		}/*
		else if(document.${formName}.user_email.value == "")
		{
		alert("Sila Masukkan Emel");	
		document.${formName}.user_email.focus();
		}
		else if(document.${formName}.id_jawatan.value == "")
		{
		alert("Sila Masukkan Jawatan");	
		document.${formName}.id_jawatan.focus();
		}*/
		else if(document.${formName}.id_negeri.value == "")
		{
		alert("Sila Masukkan Negeri");	
		document.${formName}.id_negeri.focus();
		}
		else if(document.${formName}.jenis_pejabat.value == "")
		{
		alert("Sila Masukkan Jenis Pengguna");	
		document.${formName}.jenis_pejabat.focus();
		}
		else if(document.${formName}.id_pejabat.value == "")
		{
		alert("Sila Masukkan Pejabat");	
		document.${formName}.id_pejabat.focus();
		}
		else if(document.${formName}.role.value == "")
		{
		alert("Sila Masukkan Role");	
		document.${formName}.role.focus();
		}
		else if((document.${formName}.user_password.value != "" && document.${formName}.user_password2.value != "") && (document.${formName}.user_password.value != document.${formName}.user_password2.value) )
		{
				
			alert("Sila pastikan kata laluan adalah sama");	
			document.${formName}.user_password.focus();
			
		}
		else
		{	
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
		doAjaxCall${formName}("add");
		}
		
		}
		
		}
		
		function kosong()
		{		
		doAjaxCall${formName}("");		
		}
		
		function getUser(user_id)
		{		
		document.${formName}.user_id.value = user_id;
		doAjaxCall${formName}("getUser");		
		}
		
		
		function hapus()
		{	
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {	
			doAjaxCall${formName}("hapus");	
		}
		}
</script>