




<table width="100%" border="0">
  <tr>
    <td width="5%">&nbsp;</td>
    <td width="9%" valign="top">Kepada</td>
    <td width="1%" valign="top">:</td>
    <td width="60%" >
   
    
<div id="test_event"></div>
<div id="alert_text">

</div>
<div id="temp"> 
#foreach($ur in $list_user_online_aduan)
<input type="hidden" name="user_id" id="user_id"  value="$ur.user_id" />
#end
#foreach($ur in $user_selected)
<input type="hidden" name="user_id" id="user_id"  value="$ur.user_id" />
#end
#foreach($ur in $set_defaul_user)
<input type="hidden" name="user_id" id="user_id"  value="$ur.user_id" />
#end

</div>
    <table cellpadding="2" cellspacing="1" border="0" width="70%" class="trans_bg">
    <tr>
    <td>

<span id="label_sec">
#foreach($ur in $list_user_online_aduan)
<span class='nav'  align='center' id='$ur.user_id' valign='top' ><font color='blue' >&nbsp;$ur.user_name</font>&nbsp;</span>&nbsp;
#end


#foreach($ur in $set_defaul_user)
<span class='nav' style="display:none"   align='center' id='$ur.user_id' valign='top' ><font color='blue' >&nbsp;$ur.user_name</font>&nbsp;</span>
#end


#foreach($ur in $user_selected)

#if($!pengguna_aktif == $ur.user_id)

<span class='nav'  style="display:none"  align='center' id='$ur.user_id' valign='top' ><font color='blue' >&nbsp;$ur.user_name</font>
<a href="javascript:hapus_selected_user($ur.user_id)"  title="Klik untuk hapus $ur.user_name "  ><img width='11' height='11'  src='../img/validno.png' /></img></a></span>
#else

<!--
#set($check_user_aduan = "")
#foreach($uri in $list_user_online_aduan)
    #if($uri.user_id == $ur.user_id)
    	#set($check_user_aduan = "yes")
    #end
#end

#if($check_user_aduan != "yes")
#end
-->

<span class='nav'  align='center' id='$ur.user_id' valign='top' ><font color='blue' >&nbsp;$ur.user_name</font>
<a href="javascript:hapus_selected_user($ur.user_id)"  title="Klik untuk hapus $ur.user_name "  ><img width='11' height='11'  src='../img/validno.png' /></img></a></span>


#end

#end
</span>
<!-- ::::::::: $!flag_simpan_doc -->



#if($!flag_buka_upload!="yes")
    #if($role == "online" || $role == "ppt-online-user" || $role == "ppk-online-user" || $role == "php-online-user")
    <input type = "text" id = "input" name = "input" style="border:none; display:none" size="20" maxlength="200" list = "datalist"  placeholder="Masukkan Nama Disini..."    />   
    #else
     <input type = "text" id = "input" name = "input" style="border:none;" size="20" maxlength="200" list = "datalist"  placeholder="Masukkan Nama Disini..."    />    
    #end
     <datalist id = "datalist">
        #foreach($ja in $list_user)
        <option label="$!ja.user_name" value = "$!ja.user_id_dum"></option>  
        #end
    </datalist>
#else
 #if($role == "online" || $role == "ppt-online-user" || $role == "ppk-online-user" || $role == "php-online-user")
<input type = "text" id = "input" name = "input" style="border:none; display:none;" size="20" maxlength="200" list = "datalistx"  placeholder="Masukkan Nama Disini..."   />
#else
<input type = "text" id = "input" name = "input" style="border:none;" size="20" maxlength="200" list = "datalistx"  placeholder="Masukkan Nama Disini..."   />
#end

<datalist id = "datalistx">
    #foreach($ja in $list_user)
    <option label="$!ja.user_name" value = "$!ja.user_id_dum"></option>  
    #end
</datalist>
#end



<input type = "text" id = "dummy_input" style="display:none"  />
</td>
</tr>
</table>


 <i ><font color="red" size="1">Perhatian</font> <font  size="1">: Sila masukkan nama, pilih dan tekan 'ENTER' untuk kemasukan maklumat.</font></i>
    



</td>
    <td width="25%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Mesej</td>
    <td valign="top">:</td>
    <td>
    <!-- id_inboxmesej ::: --> <input type="hidden" id="id_inboxmesej" name='id_inboxmesej' value='$!id_inboxmesej'  />
    <textarea name="mesej" id="mesej" cols="58"   rows="1"   placeholder="Sila Taip Mesej Disini..."         
         onBlur="check_length(this,'4000','mesej_check','mesej_num','normal','yes','mesej');"  
         onKeyup="check_length(this,'4000','mesej_check','mesej_num','normal','yes','mesej');" 
         onKeydown="check_length(this,'4000','mesej_check','mesej_num','normal','yes','mesej');"                    
           >$!mesej</textarea>
                 
         <div><span id="mesej_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
        
         <div id="mesej_check" class="alert_msg" ></div> </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td valign="middle">
          #if($listDokumen_inbox.size() > 0)
          #foreach($list1 in $listDokumen_inbox)          
          <img    src="../img/small_doc.png" alt="" border="0"/>
          <a href="javascript:papar_Lampiran('$list1.id_inboxattach')"><font color="blue"><u>$list1.nama_fail</u></font>
          <font color="grey">($list1.saiz)</font>
          </a><a href="javascript:deleteDokumenMain('$list1.id_inboxattach')" title="Hapus" ><font color="blue">&nbsp;<img   src="../img/validno.png"  height="10" width="10" alt="" border="0"/></font></a><br />
          #end
          #end
    
    #if($!alert_saiz == "yes")
    <div><font color="#FF0000">Muatnaik Gagal, Fail Melebihi 2 MB!</font></div>
    #end
    <a href="javascript:bukaUpload()"><img src="../img/attachment-icon.png" alt="" border="0"/><font color="blue"><i>Muatnaik Dokumen</i></font></a></td>
    <td>&nbsp;</td>
  </tr>
  
   #if($!flag_buka_upload=="yes")
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>#parse("app/utils/inboxSkrinUpload-online.jsp")</td>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align="left"><input type="button" name="cmdHantarMesej"  id="cmdHantarMesej" value="Hantar" onClick="javascript:hantarMesej()" />
    <input type="button" name="cmdBatalHantarMesej"  id="cmdBatalHantarMesej" value="Batal" onClick="javascript:daftarMesej()" />
    </td>
    <td>&nbsp;</td>
  </tr>
</table>






#if($!flag_simpan_doc != "yes")

<script type="text/javascript">
check_length(document.${formName}.mesej,'4000','mesej_check','mesej_num','normal','yes','mesej');


var datalist = document.getElementById ("datalist");
var input = document.getElementById ("input");
var input_content = document.getElementById ("input").value;

/*input.addEventListener ("click", function (event) {
alert("clik");
},false)*/


input.addEventListener ("keyup", function (event) {
//$jquery("#test_event").html(event.which);
$jquery("#alert_text").html("");

    if (event.which === 13 || event.which === 37 || event.which === 39) {//enter      
	var word = input.value;  	
	var userid = word.substring(0,word.indexOf("#"));
	var username = word.substring(word.indexOf("#")+1,word.length);
	
	if(userid=="")
	{
	//alert("'"+word+"'");
	$jquery("#alert_text").html("<font color='red'><b>"+word+"</b> tiada didalam pengkalan data </font>");
	return false;
	}	
	else
	{	
	
	if(document.${formName}.user_id != null)
	{
	//alert("XXXX:"+document.${formName}.user_id);
	 if(document.${formName}.user_id.length > 0)
	 {
	  for (x = 0; x < document.${formName}.user_id.length; x++)
	  {
		if(document.${formName}.user_id[x].value == userid)
		{
		//alert("'"+username+"' telah wujud");
		
		//alert("XXXX:"+document.${formName}.user_id.length);
		$jquery("#alert_text").html("<font color='red'><b>"+username+"</b> telah dipilih</font>");
		document.getElementById ("input").value = "";	
		return false;	
			
		}  
	  }
	 }
	 else
	 {
	 if (document.${formName}.user_id != null)
	 {
	 
	 //alert("XXXX:"+document.${formName}.user_id.value);
	    if(document.${formName}.user_id.value == userid)
		{
		$jquery("#alert_text").html("<font  color='red'><b>"+username+"</b> telah dipilih</font>");
		document.getElementById ("input").value = "";	
		return false;	
		
		} 
	 }
	 }
	}
	
	
	
	  
	var x = "<input type='hidden' id='user_id' name='user_id' value='"+userid+"' >";
	myDiv_temp = document.getElementById("temp");
	$jquery("#temp").html(myDiv_temp.innerHTML+x);	
	
	var y = "<span class='nav'  align='center' id='"+userid+"' valign='top' ><font color='blue' >&nbsp;"+username+" </font><a href='javascript:hapus_selected_user("+userid+")'  title='Klik untuk hapus "+username+"'><img width='11' height='11'  src='../img/validno.png' /></img></a></span>";
	myDiv_label = document.getElementById("label_sec");
	$jquery("#label_sec").html(myDiv_label.innerHTML+y);	
	document.getElementById ("input").value="";
	}
	
	
    }
	
	
}, true);






</script>


#else
<script type="text/javascript">
check_length1(document.${formName}.mesej,'4000','mesej_check','mesej_num','normal','yes','mesej');

var datalist = document.getElementById ("datalist");
var input = document.getElementById ("input");
var input_content = document.getElementById ("input").value;




input.addEventListener ("keyup", function (event) {
//$jquery("#test_event").html(event.which);
//alert("1");
$jquery("#alert_text").html("");

    if (event.which === 13 || event.which === 37 || event.which === 39) {//enter 
	//alert("2");     
	var word = input.value;  	
	var userid = word.substring(0,word.indexOf("#"));
	var username = word.substring(word.indexOf("#")+1,word.length);
	
	if(userid=="")
	{
	//alert("'"+word+"'");
	$jquery("#alert_text").html("<font color='red'><b>"+word+"</b> tiada didalam pengkalan data </font>");
	return false;
	}	
	else
	{	
	
	if(document.${formName}.user_id != null)
	{
	//alert("XXXX:"+document.${formName}.user_id);
	 if(document.${formName}.user_id.length > 0)
	 {
	  for (x = 0; x < document.${formName}.user_id.length; x++)
	  {
		if(document.${formName}.user_id[x].value == userid)
		{
		//alert("'"+username+"' telah wujud");
		
		//alert("XXXX:"+document.${formName}.user_id.length);
		$jquery("#alert_text").html("<font color='red'><b>"+username+"</b> telah dipilih</font>");
		document.getElementById ("input").value = "";	
		return false;	
			
		}  
	  }
	 }
	 else
	 {
	 if (document.${formName}.user_id != null)
	 {
	 
	 //alert("XXXX:"+document.${formName}.user_id.value);
	    if(document.${formName}.user_id.value == userid)
		{
		$jquery("#alert_text").html("<font  color='red'><b>"+username+"</b> telah dipilih</font>");
		document.getElementById ("input").value = "";	
		return false;	
		
		} 
	 }
	 }
	}
	
	
	
	  
	var x = "<input type='hidden' id='user_id' name='user_id' value='"+userid+"' >";
	myDiv_temp = document.getElementById("temp");
	$jquery("#temp").html(myDiv_temp.innerHTML+x);	
	
	var y = "<span class='nav'  align='center' id='"+userid+"' valign='top' ><font color='blue' >&nbsp;"+username+" </font><a href='javascript:hapus_selected_user("+userid+")'  title='Klik untuk hapus "+username+"'><img width='11' height='11'  src='../img/validno.png' /></img></a></span>";
	myDiv_label = document.getElementById("label_sec");
	$jquery("#label_sec").html(myDiv_label.innerHTML+y);	
	document.getElementById ("input").value="";
	}
	
	
    }
	
	
}, true);


function check_length1(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{


	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
	   if(lepas_or_xlepas == "2")
	   {	   
	   //$jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (my_form.value.length >= maxLen) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
my_form.value = my_form.value.substring(0, maxLen);
       maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");



}



</script>
#end