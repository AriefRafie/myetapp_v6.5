

  
 
 <!-- dummy_total_tinggi :::::::::--> <input type="hidden" name="dummy_total_tinggi" id="dummy_total_tinggi" />
 
 <table cellpadding="2" cellspacing="1" border="0" width="80%" align="center"  class="lineatas">
  <tr  >
  <td width="2%" valign="top"></td>
  <td  width="3%" valign="top" ><img   align="top" src="../img/fb_msg.png" />
    </td>
  <td  width="60%" valign="top" >
 
 
 

<!--
:::::: $!tengah_cari_mesej
:::::: $!cari_mesej
-->

#if($!tengah_cari_mesej == "yes")
<script>
if('$!cari_mesej' != "")
{
	var word = '$!cari_mesej';
	searchArray = [word];
	//alert("xxx :"+searchArray);
	highlightStartTag = "<font style='color:black; background-color:yellow;'>";
	highlightEndTag = "</font>";
	  for (x = 0; x < parseInt('$listMesejUser.size()'); x++)
	  {
	  var xx = "lokasi_mesej"+(x+1);
	  temp_lokasi = document.getElementById(xx);
	  var divText = temp_lokasi.innerHTML;
	  //alert("divText :"+divText);
		  for (var i = 0; i < searchArray.length; i++) 
		  {
			  divText = doHighlight(divText,searchArray[i], highlightStartTag, highlightEndTag);
			  //alert(divText);
		  }
	  temp_lokasi.innerHTML = divText; 
	  }
 }
</script>
#end
  
  

  <div  id="div_atas" style="width:100%;overflow:auto;" > 
 <!-- id_pendaftar_mesej :::: $id_pendaftar_mesej
  pengguna_aktif ::::: $pengguna_aktif 
  ::: $preview_user
  -->
  #if($preview_user == "display")  
<font  color="blue">$!senarai_penerima</font>
#if($id_pendaftar_mesej == $pengguna_aktif)
<!--<input type="button" name="cmdKemaskiniiUserBaru"  id="cmdKemaskiniiUserBaru" value="Kemaskini" onClick="javascript:kemaskiniUserBaru('$!id_maininbox')" />-->
#end
#foreach($ur in $user_selected)
<!-- :::::::::::--> <input type="hidden" name="user_id" id="user_id"  value="$ur.user_id" />
#end
#else     
<div id="test_event"></div>
<div id="alert_text"></div>
<div id="temp"> 
#foreach($ur in $user_selected)
<input type="hidden" name="user_id" id="user_id"  value="$ur.user_id" />
#end
#foreach($ur in $set_defaul_user)
<input type="hidden" name="user_id" id="user_id"  value="$ur.user_id" />
#end
</div>
    <table cellpadding="2" cellspacing="1" border="0" width="100%" class="trans_bg">
    <tr>
    <td>

<span id="label_sec">
#foreach($ur in $set_defaul_user)
<span class='nav' style="display:none"  align='center' id='$ur.user_id' valign='top' ><font color='blue' >&nbsp;$ur.user_name</font>&nbsp;</span>
#end


#foreach($ur in $user_selected)

#if($!pengguna_aktif == $ur.user_id)
<span class='nav' style="display:none"   align='center' id='$ur.user_id' valign='top' ><font color='blue' >&nbsp;$ur.user_name</font>
<a href="javascript:hapus_selected_user($ur.user_id)"  title="Klik untuk hapus $ur.user_name "  ><img width='11' height='11'  src='../img/validno.png' /></img></a></span>
#else
<span class='nav'  align='center' id='$ur.user_id' valign='top' ><font color='blue' >&nbsp;$ur.user_name</font>
<a href="javascript:hapus_selected_user($ur.user_id)"  title="Klik untuk hapus $ur.user_name "  ><img width='11' height='11'  src='../img/validno.png' /></img></a></span>
#end

#end
</span>
<!--
::::::::: $!flag_simpan_doc
-->
#if($!flag_buka_upload!="yes")

 #if($role == "online" || $role == "ppt-online-user" || $role == "ppk-online-user" || $role == "php-online-user")
<input type = "text" id = "input" name = "input" style="border: none; display:none" size="20" maxlength="200" list = "datalist"  placeholder="Masukkan Nama Disini..."    />
#else
<input type = "text" id = "input" name = "input" style="border: none;" size="20" maxlength="200" list = "datalist"  placeholder="Masukkan Nama Disini..."    />
#end
<datalist id = "datalist">
    #foreach($ja in $list_user)
    <option label="$!ja.user_name" value = "$!ja.user_id_dum"></option>  
    #end
</datalist>
#else

 #if($role == "online" || $role == "ppt-online-user" || $role == "ppk-online-user" || $role == "php-online-user")

<input type = "text" id = "input" name = "input" style="border: none;display:none" onfocus="javascript:bukaUpload()" size="20" maxlength="200" list = "datalistx"  placeholder="Masukkan Nama Disini..."    />

#else
<input type = "text" id = "input" name = "input" style="border: none;" onfocus="javascript:bukaUpload()" size="20" maxlength="200" list = "datalistx"  placeholder="Masukkan Nama Disini..."    />
#end
<datalist id = "datalistx">
    #foreach($ja in $list_user)
    <option label="$!ja.user_name" value = "$!ja.user_id_dum"></option>  
    #end
</datalist>
#end



<input type = "text" id = "dummy_input" style="display:none"  />

 #if($role == "online" || $role == "ppt-online-user" || $role == "ppk-online-user" || $role == "php-online-user")

<a href="javascript:daftarUserBaru('$!id_maininbox')" title="Simpan" style="display:none">
<span class='nav_bg' align='center' valign='top' ><font color='white' >&nbsp;simpan&nbsp;</font></span>
</a>
#else
<a href="javascript:daftarUserBaru('$!id_maininbox')" title="Simpan">
<span class='nav_bg' align='center' valign='top' ><font color='white' >&nbsp;simpan&nbsp;</font></span>
</a>

#end


</td>

</table>


    
  
  
  #end
  
  
  </div>
  
  
  </td>  
  <td  width="10%" align="center" valign="top" >
  
  <ul id="sddm"  >
    <li><a href="#" 
        onmouseover="mopen('m1')" 
        onmouseout="mclosetime()">Tindakan</a>
        <div id="m1" 
            onmouseover="mcancelclosetime()" 
            onmouseout="mclosetime()">
            #if($id_pendaftar_mesej == $pengguna_aktif)
<a href="javascript:kemaskiniUserBaru('$!id_maininbox')"  title="Kemaskini Senarai Penerima Mesej">Kemaskini Penerima Mesej</a>
#end
        <a href="javascript:openForward('$!id_maininbox')" >Kirim Semula Mesej<i>&nbsp;(Forward)</i></a>
        <a href="javascript:setArkib('$!id_maininbox')">Simpan Kedalam Arkib</a>
        <a href="javascript:openHapus('$!id_maininbox')">Padam Mesej</a>
        <a href="javascript:backToList()">Kembali Ke Senarai Utama</a>
       
        </div>
    </li>
   </ul>
<div style="clear:both"></div>
  <!-- <input type="button" name="cmdBackToList"  id="cmdBackToList" value="Senarai Mesej" onClick="javascript:backToList()" /> -->
  
  </td>
  <td  width="25%" align="left"  valign="top" >
  <img   height="22" width="22" align="top" src='../img/small_search.png' /></img>
  <textarea name="cari_mesej" type="text" id="cari_mesej" cols="20"   rows="1"  style="height:20" placeholder="Cari Mesej..." onkeypress="javascript:hadoi(event,'$!id_maininbox')"    >$!cari_mesej</textarea> 
  <!--<input type="button" name="cmd123"  id="cmd123" value="Cari" onclick="javascript:hadoi(event,'$!id_maininbox')" />-->
  </td>

  </tr>


</table>








<script>

  var xxx = parseInt('$listMesejUser.size()');
  
  if(xxx>1)
  {
  for (x = 0; x <xxx; x++)
  {
  var ff = "datetextdiv"+(x+1); 
  var fx = "datetext"+(x+1); 
  $jquery("#"+ff).html("<font color='grey'>"+pretty_date(document.getElementById(fx).value)+"</span>");
  }
  }
  else
  {
  $jquery("#datetextdiv1").html("<font color='grey'>"+pretty_date(document.getElementById('datetext1').value)+"</span>");
  }
  
   var total_tinggi = 0;  
  if(xxx>1)
  {
 
  for (x = 0; x < (xxx-1); x++)
  {//class="top td"
  var a = "input"+(x+1);
  var b = "input"+(x+2);
  var c = "div"+(x+2);
  var d = "tr"+(x+1);  
  var e = "date"+(x+2);
  var f = "check"+(x+2);
  var g = "forward"+(x+2);
  var h = "div"+(x+1);
  var i = "forward"+(x+1);
  
 
 
 
  	if(document.getElementById(a).value == document.getElementById(b).value)
	{	 
    var xx = document.getElementById(c);	
	xx.parentNode.removeChild(xx);	
	
    var pp = document.getElementById(e);
	pp.parentNode.removeChild(pp);
	
	var uu = document.getElementById(f);
	uu.parentNode.removeChild(uu);
	
	var jj = document.getElementById(g);
	jj.parentNode.removeChild(jj);
	
	total_tinggi = total_tinggi + 14;	
	} 
	else
	{
	var yy = document.getElementById(d);
	yy.className  = "bottom td";
	//<div id='$forward_input'> #### &nbsp; </div>
	//var jj = document.getElementById('forward12');
	//jj.innerHTML = "xxx";
    //alert("SSS ::"+jj.innerHTML);
	
	}
  }
  document.getElementById('dummy_total_tinggi').value = total_tinggi;
  }
  
//alert("ssss");
  
</script>


<script type="text/javascript" >
var objDiv = document.getElementById("div_tengah");
var DivH = document.${formName}.dummy_total_tinggi.value;
var var_total = objDiv.clientHeight;
if(var_total > 250)
{
objDiv.style.height = 250;
objDiv.style.width = '80%';
objDiv.align = 'center';
objDiv.style.overflow = 'auto';
}
objDiv.scrollTop = objDiv.scrollHeight;
</script>


<script type="text/javascript" >
var objDivAtas = document.getElementById("div_atas");
var var_total_atas = objDivAtas.clientHeight;
if(var_total_atas > 55)
{
objDivAtas.style.height = 55;
objDivAtas.style.width = '100%';
objDivAtas.style.overflow = 'auto';
}
objDivAtas.scrollTop = objDivAtas.scrollHeight;
</script>

#if($preview_user == "display")  
#else
<table cellpadding="2" cellspacing="1" border="0" width="80%" align="center"  >
 <tr  >
  <td width="2%" valign="top"></td>
  <td  width="3%" valign="top" >
    </td>
  <td  width="60%" valign="top" >
  <i ><font color="red" size="1">Perhatian</font> <font  size="1">: Sila masukkan nama, pilih dan tekan 'ENTER' untuk kemasukan maklumat.</font></i>
 
  </td>
  <td  width="10%" align="left"  valign="top" >
  </td>
  
  <td  width="25%" align="left"  valign="top" >
  </td>
  </tr>
  </table>
#end

#if($!flag_hapus == "Y")
<div align="center">
<div align="center" > 
<table cellpadding="2" cellspacing="0" border="0" width="80%" align="center" >
<tr>
<td width="95%">
<table cellpadding="2" cellspacing="0" border="0" width="50%" align="left" class="ruang_hapus">
<tr>
<td width="40%" valign="middle">
<input type="checkbox" name="all1_delete" id="all1_delete"  style="display:none" onClick="doCheckall1_delete()" title="Semak untuk pilih semua" />
<font color="blue">Pilih Mesej Untuk Dipadam</font>
</td>
<td width="60%" valign="top">
<input type="button" name="cmdPilihSemua"  id="cmdPilihSemua" value="Pilih Semua" onClick="pilihsemua();" />
<input type="button" style="display:none" name="cmdHapusMesej"  id="cmdHapusMesej" value="Hapus" onClick="javascript:deleteMesej('$!id_maininbox')" />
<input type="button" name="cmdBatal"  id="cmdBatal" value="Batal" onClick="javascript:batalpilihsemua()" />
</td>
</tr>
</table>
</td>
</tr>
</table>
</div>
</div>
#end

<div align="center">
<div  id="div_tengah" style="width:80%;overflow:auto;" > 
<table cellpadding="2" cellspacing="0" border="0" width="100%" align="center" class="line_normal"    >
#if($listMesejUser.size() > 0)

#foreach($xx in $listMesejUser)


#if($xx.BIL == "1")
#set($class_x = "line_normal")
#else
#set($class_x = "line_normal")
#end


#set($temp_input = "input"+$xx.BIL)
#set($div_input = "div"+$xx.BIL)
#set($tr_input = "tr"+$xx.BIL)
#set($date_input = "date"+$xx.BIL)
#set($check_input = "check"+$xx.BIL)
#set($datetext_input = "datetext"+$xx.BIL)
#set($datetextdiv_input = "datetextdiv"+$xx.BIL)
#set($forward_input = "forward"+$xx.BIL)


<input type="hidden" name="$temp_input" id="$temp_input" value="$xx.user_id"  />
<tr  id="$tr_input" >
 #if($!flag_hapus != "Y")
<div id="$check_input">
 </div>
  #end
 <td width="5%" valign="top" align="center">  
  #if($!flag_hapus == "Y") 
   <div id="$check_input">
 &nbsp;
  </div>
  <span align="center">
  #if($xx.id_masuk == $pengguna_aktif)
 
  <input type="checkbox" name="ids1_delete" id="ids1_delete"  onClick="doUpdateCheckall1_delete()" value="$xx.id_inboxmesej" >
  #end          
  </span>
  #end
  
 </td>
  <td  width="65%"  valign="top" >
  <div style="width:65%;"> 
  <div id="$div_input" >
  <font color="#06F" ><b>$xx.user_name</b></font>
  <br>
  </div>
  
  #set($lokasi_mesej = "lokasi_mesej"+$xx.BIL)
  
<span id="$!lokasi_mesej">  
$xx.mesej
</span>

<br>
 		  #if($listDokumenMainInboxSkrin.size() > 0)
          #foreach($list1 in $listDokumenMainInboxSkrin) 
          #if($list1.id_inboxmesej == $xx.id_inboxmesej)         
          <img    src="../img/small_doc.png" />
          <a href="javascript:papar_Lampiran('$list1.id_inboxattach')"><font color="blue"><u>$list1.nama_fail</u></font>
          <font color="grey">($list1.saiz)</font>
          </a><a href="javascript:deleteDokumenMain('$list1.id_inboxattach')" title="Hapus" ><font color="blue">&nbsp;<!--<img   src="../img/validno.png"  height="10" width="10" alt="" border="0"/>--></font></a><br />
          #end
          #end
          #end
  </div>
  </td>
  <td  width="15%" valign="top"  >
  <div id="$date_input">
  &nbsp;
  </div>
  <div id="$datetextdiv_input">
  &nbsp;
  </div>
  
  #set($full_time = $xx.tarikh_masuk_24+"T"+$xx.tarikh_masuk_time_24+"Z")

 <input type="hidden" id="$datetext_input" name="$datetext_input" value="$full_time"  />

  <!--
  $!datetext_input :::::: 
  <font color="grey">$xx.tarikh_masuk</font>
  -->
  </td>
  #if($!flag_forward != "Y")
  <span id="$forward_input"></span>
  #end
  <td  width="15%"  valign="top" >
  
 
  #if($!flag_forward == "Y")
  <div id="$forward_input"> &nbsp; </div>
  <a href="javascript:forwardMesej('$xx.mesej')">  
  <img id="id_forward_mesej" name="id_forward_mesej" src="../img/forward_mesej.png"  />
  </a>
  #end
  
 
  </td>
 
</tr>



#end
#else
<tr   >
 <td width="5%" valign="top"></td>
  <td  width="65%"  valign="top" >
   Tiada Rekod
   </td>
  <td  width="15%" valign="top"  >
   </td>
  
  <td  width="15%"  >
  
  </td>
</tr>
#end
</table>
</div>
</div>














<table cellpadding="2" cellspacing="1" border="0" width="80%" align="center" class="linebawah"  >
  <tr class="line" >
  <td width="5%" valign="top"></td>
  <td  width="65%"  valign="top" >
   

         <input type="hidden" id="id_inboxmesej" name='id_inboxmesej' value='$!id_inboxmesej'  />
         <textarea name="mesej" id="mesej" cols="58"   rows="1"   placeholder="Sila Taip Mesej Disini..."         
         onBlur="check_length(this,'4000','mesej_check','mesej_num','normal','yes','mesej');"  
         onKeyup="check_length(this,'4000','mesej_check','mesej_num','normal','yes','mesej');autoExpand_enter(event,'mesej');" 
         onKeydown="check_length(this,'4000','mesej_check','mesej_num','normal','yes','mesej');"                    
           >$mesej</textarea>
          
         
	 
          
           
                   
         <span><span id="mesej_num" style="color:blue;" ></span><span> Baki Aksara</span>         </span>
         <div id="mesej_check" class="alert_msg" ></div> 
          
         <input type="button" name="cmdDaftarPerbualan" style="display:none"  id="cmdDaftarPerbualan" value="Hantar" onClick="javascript:daftarPerbualan()" />

  
  </td>
  <td  width="15%" valign="top"  ></td>
  
  <td  width="15%"  ></td>

  </tr>
  
  
  
  
  <tr>
  

    <td>&nbsp;</td>
    <td valign="middle">
          #if($listDokumen_inbox.size() > 0)
          #foreach($list1 in $listDokumen_inbox)          
          <img    src="../img/small_doc.png" alt="" border="0"/>
          <a href="javascript:papar_Lampiran('$list1.id_inboxattach')"><font color="blue"><u>$list1.nama_fail</u></font>
          <font color="grey">($list1.saiz)</font>
          </a><a href="javascript:deleteDokumenPerbualan('$list1.id_inboxattach')" title="Hapus" ><font color="blue">&nbsp;<img   src="../img/validno.png"  height="10" width="10" alt="" border="0"/></font></a><br />
          #end
          #end
    
    #if($!alert_saiz == "yes")
    <div><font color="#FF0000">Muatnaik Gagal, Fail Melebihi 2 MB!</font></div>
    #end
    
    <a href="javascript:bukaUploadPerbualan()"><img src="../img/attachment-icon.png" alt="" border="0"/><font color="blue"><i>Muatnaik Dokumen</i></font></a></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  
   #if($!flag_buka_upload=="yes")
  <tr>
   
    <td>&nbsp;</td>
    <td>#parse("app/utils/inboxSkrinUpload.jsp")</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #end
  
  
  
  
  </table>


#if($papar_mesej == "YES")
<script type="text/javascript">
window.location.hash='mesej';
</script>
#end
 
<!--flag_simpan_doc  ::: $!flag_simpan_doc -->

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