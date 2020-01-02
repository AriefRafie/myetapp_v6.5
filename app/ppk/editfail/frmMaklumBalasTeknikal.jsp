
	 <input type="hidden" id="flag_buka_maklumbalasteknikal" name="flag_buka_maklumbalasteknikal" value="$!flag_buka_maklumbalasteknikal"  />
     <input type="hidden" id="id_editcomment" name="id_editcomment"  />
     
     
     #if($listComment_aduan_tech.size()>$tetap_filter_teknikal)
     #set($jum_temp_teknikal = $listComment_aduan_tech.size()-$tetap_filter_teknikal)
     #end
     
     
     
     
     #if($listComment_aduan_tech.size()>$tetap_filter_teknikal)
     <table width="50%">
     <tr height="0.5">
     <td>
     #if($!flag_buka_maklumbalasteknikal != "yes")
     <a href="javascript:bukaMaklumbalasTeknikal()">
     <img src="../img/online/onebit.png" alt="" border="0" height="25" width="25"/>
     <font color="blue">
     Papar kesemua $listComment_aduan_tech.size() maklumbalas teknikal
     </font></a>
     #else
     <a href="javascript:bukaMaklumbalasTeknikal()">
     <img src="../img/online/onebit.png" alt="" border="0" height="25" width="25"/>
     <font color="blue">
     Kecilkan paparan maklumbalas teknikal
     </font></a>
     #end     
     </td>
     </tr>
     </table>
     #end
    
     #if($listComment_aduan_tech.size() > 0)
     #foreach($list1 in $listComment_aduan_tech) 
     #set( $i = $velocityCount ) #if ( ($i % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end
   
   	 #set($buka_maklumbalas_teknikal = "no")     
     #if(($listComment_aduan_tech.size()>$tetap_filter_teknikal) && ($!flag_buka_maklumbalasteknikal != "yes"))          
     #if($list1.BIL > $jum_temp_teknikal)
     #set($buka_maklumbalas_teknikal = "yes")
     #end     
     #else
     #set($buka_maklumbalas_teknikal = "yes")
     #end     
     #if($!buka_maklumbalas_teknikal == "yes")
   
     <table width="50%"  class="row2" >
     <tr>
     <td width="97%">
     <span><b><font  color="#993300" >$list1.nama_user</font></b></span>&nbsp;<span>$list1.maklumbalas</span>     
     <div><font size="1" color="#993300"><i>$list1.tarikh_masuk</i></font></div>
     </td>
     <td valign="top" width="3%">
      #if($!list1.id_masuk == $!pengguna_aktif)   
     <a href="javascript:deleteComment('$list1.id_escomment')" title="Hapus" ><font color="blue"><img  height="12" width="12" src="../img/validno.png" alt="" border="0"/></font></a>
     #end
     </td>
     </tr>     
     </table>
     <table>
     <tr height="0.5">
     <td>
     </td>
     </tr>
     </table>
     #end
     
     
     
     #end
     #end
           <textarea placeholder="Sila Masukkan Komen Teknikal..." name="maklumbalas_teknikal" id="maklumbalas_teknikal" cols="58"   rows="1"   wrap="off" style="overflow:hidden"        
         onBlur="onbuttonsimpan_tech();check_length(this,'4000','maklumbalas_teknikal_check','maklumbalas_teknikal_num','normal','yes','keterangan maklumbalas teknikal');autoExpand(event,'maklumbalas_teknikal');"  
         onKeyup="onbuttonsimpan_tech();check_length(this,'4000','maklumbalas_teknikal_check','maklumbalas_teknikal_num','normal','yes','keterangan maklumbalas teknikal');autoExpand(event,'maklumbalas_teknikal');"
         onKeydown="onbuttonsimpan_tech();check_length(this,'4000','maklumbalas_teknikal_check','maklumbalas_teknikal_num','normal','yes','keterangan maklumbalas teknikal');autoExpand(event,'maklumbalas_teknikal');"                    
           >$!maklumbalas</textarea>
                   
          <div><span id="maklumbalas_teknikal_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
         <div id="maklumbalas_teknikal_check" class="alert_msg" ></div>  
         
         
       
          
         <input type="button" name="cmdDaftarMaklumbalasTeknikal" style="display:none"  id="cmdDaftarMaklumbalasTeknikal" value="Hantar" onClick="javascript:daftarMaklumbalasTeknikal()" />
         
         
   
    
    


<script type="text/javascript" >


check_length(document.${formName}.maklumbalas_teknikal,'4000','maklumbalas_teknikal_check','maklumbalas_teknikal_num','normal','yes','keterangan maklumbalas teknikal');

ResetScrollPosition();

function autoExpand(event,field_id){


if(event.keyCode == "13" || event.keyCode == "8"){

var therows=0
var thetext = document.getElementById(field_id).value;
var newtext = thetext.split("\n");
therows+=newtext.length
var i;

document.getElementById(field_id).rows = therows;
return false;

}
}


function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
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


function onbuttonsimpan_tech()
{
   if(document.getElementById('maklumbalas_teknikal').value != "")
   {
     document.getElementById('cmdDaftarMaklumbalasTeknikal').style.display = "";
   }
   else
   {
    document.getElementById('cmdDaftarMaklumbalasTeknikal').style.display = "none";
   }
}


function daftarMaklumbalasTeknikal()
		{
		     if(document.${formName}.maklumbalas_teknikal.value==""){
   	   			alert('Sila Masukkan Maklumbalas Teknikal');
	   	   		return;
   	   		}
			else
			{
			//doAjaxCall${formName}("simpanMaklumbalasTeknikal");
			
			document.${formName}.command.value = "simpanMaklumbalasTeknikal";
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
			SaveScrollXY();
		    document.${formName}.submit();
			document.${formName}.cmdDaftarMaklumbalasTeknikal.value = "Sila Tunggu....";
			
			}
		}
		
		function deleteComment(id){
          // doAjaxCall${formName}("deleteComment","id_escomment="+id);
		  document.${formName}.id_editcomment.value = id;
		  document.${formName}.command.value = "deleteComment";
		  document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
		  SaveScrollXY();
		  document.${formName}.submit();
}

 function SaveScrollXY() {
	 
document.${formName}.ScrollX.value = document.body.scrollLeft;
document.${formName}.ScrollY.value = document.body.scrollTop;
 
}

function ResetScrollPosition() {

var hidx, hidy;
hidx = '$ScrollX';
hidy = '$ScrollY';
                        
if (typeof hidx != 'undefined' && typeof hidy != 'undefined') {
      window.scrollTo(hidx, hidy);
    }
	
}

function bukaMaklumbalasTeknikal()
		{
		//doAjaxCall${formName}("bukaMaklumbalasTeknikal");	
		document.${formName}.command.value = "bukaMaklumbalasTeknikal";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
		SaveScrollXY();
		     document.${formName}.submit();		
		}
		

</script>


