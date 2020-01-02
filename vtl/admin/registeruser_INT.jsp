<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style><table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
#set ($disablity="")
#set ($show_form=true)

#if ( $registerUserStatus == "success" )
#set ($disablity="disabled")
#set ($show_form=false)
<div class="info">
	#if ( $submit == "add" )
		Successfully registered new user: <u>$user_name<u><p></p>
	#elseif ($submit == "update" )
		Successfully updated: <u>$user_name<u>
	#end
</div>
#if ( $submit == "add" )
<!--
<input class="stylobutton" type="button" 
value="Cetak Maklumat" onClick="cetakPDF('ekptg.report.admin.MaklumatPengguna')">
-->
#end

##<script>document.getElementById('form_register').style.display = 'none';</script>
#elseif ( $registerUserStatus == "failed" )
    <table width="100%" border="0">
        <tr>
            <td>
           <div id="register_failed_box" class="error">
            Can't register: $user_login - $user_name<br>
            Login id already exist, please try different Login id.
            </div>
            </td>
        </tr>
    </table>
#else
    <table width="100%" border="0">
        <tr>
            <td nowrap>&nbsp;</td>
        </tr>
    </table>
#end

#if ($show_form==true)
<div id="error_box" class="error" style="display:none">
	<div id=error_box_text></div>
</div>
<fieldset>
<legend>Maklumat Pengguna</legend>
<table border="0" cellpadding="2" cellspacing="2">
    <tr>
      <td >Jabatan / Agensi</td>
      <td>$selectJenisJabatan</td>
    </tr>
    <tr>
        <td >ID Pengguna</td>
        <td><input onKeyPress="return gotoNextTabIndex(this, event)" $disablity autocomplete="off" name="user_login" size="20" value="$!user_login"></td>
    </tr>
    <tr>
        <td >Kata Laluan</td>
        <td><input onKeyPress="return gotoNextTabIndex(this, event)" type=password name="user_password" size="20" value="$!user_password"></td>
    </tr>
        <tr>
            <td >Pastikan Kata Laluan</td>
            <td><input onKeyPress="return gotoNextTabIndex(this, event)" type=password name="user_password2" size="20" value="$!user_password"></td>
    </tr>
    <tr>
        <td >Nama Pengguna</td>
        <td><input onKeyPress="return gotoNextTabIndex(this, event)" autocomplete="off" name="user_name" size="50" value="$!user_name"></td>
    </tr>
    <tr>
        <td>E-mel</td>
        <td><input onKeyPress="return gotoNextTabIndex(this, event)" autocomplete="off" name="user_email" size="30" value="$!user_email" id="user_email"></td>
    </tr>    
    <tr>
        <td>Jawatan</td>
        <td>$selectJawatan</td>
    </tr>
    <tr>
        <td>Negeri</td>
        <td>$selectNegeri</td>
    </tr>
    <tr>
        <td>Daerah</td>
        <td>$selectDaerah</td>
    </tr>
    <tr>
        <td>Mukim</td>
        <td>$selectMukim</td>
    </tr>
           <tr>
               <td >Pejabat</td>
               
               <td>             
    	   $selectPejabat</td>
    </tr>
    
               <tr>
                   <td >Nama Pejabat</td>
                   
                   <td>  
                   <!--
                   <input type=text readonly class=disabled size=50 value="$!pejabatJKPTGInfo.get("nama_pejabat")"  >
                   -->
                   $!namaPejabat                   </td>
    </tr>
           <tr>
               <td >Alamat</td>
               <td>             
    	       $!alamatPejabat</td>
    </tr>
    
<!--
         <tr>
         <td >Jenis Pengguna</td>
         <td>
             <select name="user_type">
                 <option value="internal">Internal</option>
                 <option value="online">Online</option>
             </select>
         </td>
    </tr>
 -->
 <input type=hidden name=user_type value=internal>
     <tr>
        <td >Role</td>
        <td>
            <select name="user_role">

            #foreach ( $role in $userRoles )
                #if ( $m_role == $role.getName() )
                    #set ( $selected = "selected")
                #else
                    #set ($selected = "")
                #end
                <option value="$role.getName()" $selected>$role.getName()</option>
            #end
            </select>        </td>
    </tr>   
  <!--
  <tr>
        <td >Page Style</td>
        <td>
            <select name="page_style">

            #foreach ( $style in $pageStyleList )
                #if ( $m_style == $style.getCssName() )
                    #set ( $selected = "selected")
                #else
                    #set ($selected = "")
                #end
                <option value="$style.getCssName()" $selected>$style.getTitle()</option>
            #end
            </select>
        </td>
    </tr>    
    -->
    <tr>
        <td colspan="2" align="left">
            #if ( $registerUserStatus != "none" )
                <input class="stylobutton" type="button" value="Update" onclick="submitRegister('update')">
            #else
                <input class="stylobutton" type="button" value="Daftar" onclick="submitRegister('add')">
            #end
            <input class="stylobutton" type="button" value="Batal" onclick="resetFields()">        </td>
    </tr>   
</table>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name=id_daerah value="$!pejabatJKPTGInfo.get("id_daerah")">  
<!--
<input type=hidden name=id_pejabatjkptg value="$!pejabatJKPTGInfo.get("id")">  
-->
</fieldset>
#end
<script>
function submitRegister(s) {
	#if ( $registerUserStatus == "failed" )
    	document.getElementById('register_failed_box').style.display = 'none';
    #end    

    if ( document.${formName}.user_login.value == "" ) { 
	    document.getElementById('error_box').style.display = '';
	    document.getElementById('error_box_text').innerHTML = "Sila masukkan ID Pengguna";
	    document.${formName}.user_login.focus(); return; 
    }

    if ( document.${formName}.user_password.value == "" ) { 
	    document.getElementById('error_box').style.display = '';
	    document.getElementById('error_box_text').innerHTML = "Sila masukkan Kata Laluan";
	    document.${formName}.user_password.focus(); return;
	}
    
    if ( document.${formName}.user_password.value != document.${formName}.user_password2.value ) { 
  	    document.getElementById('error_box').style.display = '';
  	    document.getElementById('error_box_text').innerHTML = "Sila pastikan kata laluan adalah sama";
	    document.${formName}.user_password2.select();
	    document.${formName}.user_password2.focus(); 
	    return; 
	}
	    
    if ( document.${formName}.user_name.value == "" )  { 
  	    document.getElementById('error_box').style.display = '';
  	    document.getElementById('error_box_text').innerHTML = "Sila masukkan Nama pengguna";
  	    document.${formName}.user_name.focus(); return;
	}
    
   	if ( document.${formName}.id_jawatan.value == "" )  { 
  	    document.getElementById('error_box').style.display = '';
  	    document.getElementById('error_box_text').innerHTML = "Sila pilih Jawatan pengguna";
  	    document.${formName}.id_jawatan.focus(); return; 
	}
  
    if ( document.${formName}.id_negeri.value == "" ) { 
  	    document.getElementById('error_box').style.display = '';
  	    document.getElementById('error_box_text').innerHTML = "Sila pilih Negeri";
  	    document.${formName}.id_negeri.focus(); return; 
    }

    if ( document.${formName}.user_email.value == "" ) { 
  	    document.getElementById('error_box').style.display = '';
  	    document.getElementById('error_box_text').innerHTML = "Sila masukkan alamat e-mel pengguna.";
  	    document.${formName}.user_email.focus(); return; 
    }

    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
}   
function resetFields() {
    document.${formName}.user_login.value = "";
    document.${formName}.user_name.value = "";
    document.${formName}.user_password.value = "";  
    document.${formName}.user_role.value = "";
    document.${formName}.user_login.focus();
    //reset also the drop down please...
}


function doChanges() {
	doAjaxCall${formName}("doChanges");
	//document.${formName}.AddressPejabatJKPTG.value=myString;
	//alert(myString);
}

function showhide(layer_ref,displayType) {

if (displayType =="show") displayType = "display:inline";
else displayType = "display:none";

if (document.all) { //IS IE 4 or 5 (or 6 beta)
eval( "document.all." + layer_ref + ".style.display = "+displayType);
}
if (document.layers) { //IS NETSCAPE 4 or below
document.layers[layer_ref].display = displayType ;
}
if (document.getElementById &&!document.all) {
hza = document.getElementById(layer_ref);
hza.style.display = displayType;
}
} 

</script>