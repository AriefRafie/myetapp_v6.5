



<tr>
<td>    
 $flag_pinda_selesai
#if($!headerppk.ID_STATUS == "21" || $!headerppk.ID_STATUS == "47" || $!headerppk.ID_STATUS == "70" || $!headerppk.ID_STATUS == "169" || $!headerppk.ID_STATUS == "50" || $!headerppk.ID_STATUS == "999")
  
#if($!role == "adminppk")



<fieldset>
<legend title="Kebenaran Pengemaskinian Maklumat Bagi Fail"><b>Kebenaran Pengemaskinian Maklumat</b></legend>
 
 <table width="97%"  cellpadding="1" cellspacing="2" border="0" >
 <tr>
 <td colspan="3">
 <font color="blue">
 <i>Ruangan ini bertujuan untuk <b>Pegawai</b> memberi kebenaran bagi membuat pengemaskinian maklumat</i>
 </font>
 </td>
 </tr>
 

 #if($getMainFail.NAMA_PEMOHON_EDIT != "")
  <tr>
      			<td valign="top">Pemohon Kebenaran Kemaskini Fail</td>
                <td valign="top">:              
                </td>
      			<td  valign="top">
                <font color="blue">$getMainFail.NAMA_PEMOHON_EDIT</font>
                </td>
                </tr>
                #end
               
 	
            <tr>
    			<td scope="row" width="30%" valign="top">Tujuan Pindaan</td>
                <td width="1%" valign="top">: </td>
      			<td width="69%" valign="top"> <textarea name="txtTujPinda" id="txtTujPinda" cols="50"   rows="4" disabled>$!txtTujPinda</textarea></td>
    		</tr>
      
      <!-- Tambah Tempoh -->   
      
       <tr>
                   
                      		<td scope="row" width="30%" valign="top">Tempoh</td>
                      		<td valign="top">:</td>
                      		<td valign="top"> 
                      		#if($flag_pinda_selesai == "on")
                      			<input name="txtMula" type="text" value="$txtMula" disabled>&nbsp; sehingga <input  name="txtAkhir" type="text" value="$txtAkhir" disabled>
                      		#else
                      			<input name="txtMula" type="text" value="$txtMula">&nbsp; <a href="javascript:displayDatePicker('txtMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>&nbsp; sehingga <input  name="txtAkhir" type="text" value="$txtAkhir"><a href="javascript:displayDatePicker('txtAkhir',false,'dmy');">&nbsp;<img border="0" src="../img/calendar.gif"/></a>
                      		#end
                      		
                      		</td>
                    </tr>      
 
 <tr>
      			<td scope="row" width="30%">Pengesahan Kebenaran Pengemaskinian Fail</td>
                <td width="1%">:              
                </td>
      			<td width="69%">
                #if($flag_pinda_selesai == "on")
                  	#set($disabledkan="disabled")
                #else
                  	#set($disabledkan="")
                #end
                  
                #if($flag_kebenaran_edit == "1")
                
                   #if($usid == $user_id_kebenaran_edit)
                        #if($!flag_kebenaran_edit == "1")
                        #set($check = "checked")
                        #end
                        <input  type="checkbox" $disabledkan name="check_flag_kebenaran_edit"  id="check_flag_kebenaran_edit" $check onClick="check_flag()" >
                   
                   #else
                        <font color="blue">Dibenarkan</font>               
                        #if($!flag_kebenaran_edit == "1")
                        #set($check = "checked")
                        #end
                        <input  type="checkbox" $disabledkan name="check_flag_kebenaran_edit" style="display:none" id="check_flag_kebenaran_edit" $check onClick="check_flag()" >
                   #end
                #else
                    #set($check = "")
                    #if($!flag_kebenaran_edit == "1")
                    #set($check = "checked")
                    #end
                     <input type="checkbox" $disabledkan name="check_flag_kebenaran_edit" id="check_flag_kebenaran_edit" $check onClick="check_flag()" >
                 
                #end 
                
               
                         
                </td>
    		</tr>
    		
    		
    		
             #if($!flag_kebenaran_edit == "1")
                          
            
    		 <tr  id="tr_nama">
      			<td scope="row">Kebenaran Dikeluarkan Oleh</td>
                <td>
                :                              
                </td>                
      			<td>        
                <font color="blue">$!nama_user_yg_beri_kebenaran</font>
                </td>
    		 </tr>
             #if($usid == $user_id_kebenaran_edit) 
              <tr   id="tr_catatan" >
      			<td scope="row" valign="top">Catatan</td>
                <td valign="top">
                :                              
                </td>                
      			<td  valign="top">
      			#if($flag_pinda_selesai == "on")
                  	#set($disabledkan="disabled")
                #else
                  	#set($disabledkan="")
                #end
                  #if($usid == $user_id_kebenaran_edit)
                  
             <textarea name="catatan_kebenaran_edit" $disabledkan id="catatan_kebenaran_edit" cols="50"   rows="4"  placeholder="Sila Masukkan Catatan..."         
         onBlur="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');"  
         onKeyup="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');" 
         onKeydown="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');"                    
           >$catatan_kebenaran_edit</textarea>
            <div><span id="catatan_kebenaran_edit_num" style="color:blue;" ></span><span> Baki Aksara </span></div>
        
         <div id="catatan_kebenaran_edit_check" class="alert_msg" ></div> 
                   
           #end
           
                 
        
         </td>
    		 </tr> 
             #else
             
             #if($!catatan_kebenaran_edit != "")
                  <tr   id="tr_catatan" >
      			<td scope="row" valign="top">Catatan</td>
                <td valign="top">
                :                              
                </td>                
      			<td  valign="top">
                 
           <div  style="width:50%"><font color="blue">$!catatan_kebenaran_edit</font></div>          
          
           
                 
        
         </td>
    		 </tr> 
             #end
             
          #end
             
            #else
            
          
            <tr  id="tr_nama" style="display:none" >
      			<td scope="row">Kebenaran Dikeluarkan Oleh</td>
                <td>
                :                              
                </td>                
      			<td>               
               <font color="blue">$!user_name</font>                
                </td>
    		</tr>
            <tr  id="tr_catatan" style="display:none"  >
      			<td scope="row" valign="top">Catatan</td>
                <td valign="top">
                :                              
                </td>                
      			<td  valign="top">
                
             <textarea name="catatan_kebenaran_edit" id="catatan_kebenaran_edit" cols="50"   rows="4"  placeholder="Sila Masukkan Catatan..."         
         onBlur="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');"  
         onKeyup="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');" 
         onKeydown="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');"                    
           >$catatan_kebenaran_edit</textarea>
           
                 
         <div><span id="catatan_kebenaran_edit_num" style="color:blue;" ></span><span> Baki Aksara </span></div>
        
         <div id="catatan_kebenaran_edit_check" class="alert_msg" ></div> 
         </td>
    		 </tr>
            
            
            #end
            <tr>
            <td></td>
            <td></td>
 <td>
       			#if($flag_pinda_selesai == "on")
                  	#set($disabledkan="disabled")
                #else
                  	#set($disabledkan="")
                #end
  #if($flag_kebenaran_edit == "1") 
       #if($usid == $user_id_kebenaran_edit)
       <input name="cmdSimpan" id="cmdSimpan" $disabledkan value="Simpan" type="button" onClick="javascript:cmdSimpan_flag()">
       #end
  #else
 <input name="cmdSimpan" id="cmdSimpan" value="Simpan" $disabledkan type="button" onClick="javascript:cmdSimpan_flag()">
 
<!--   <input name="cmdSimpan" id="cmdSimpan" value="Cetak Surat Pembetulan/Pindaan ke atas Perintah" type="button" onClick="javascript:cetakSuratKemaskiniPerintah()"> -->
 #end
 
 </td>
 </tr>    		
		</table>
        
        
        
        
</fieldset>


#else

<fieldset>
<legend title="Kebenaran Pengemaskinian Maklumat Bagi Fail Berstatus SELESAI"><b>Kebenaran Pengemaskinian Maklumat</b></legend>
 
 <table width="97%"  cellpadding="1" cellspacing="2" border="0" >
 <tr>
 <td colspan="4">
 <font color="blue">
 <i>Ruangan ini bertujuan untuk <b>Pegawai</b> memberi kebenaran bagi membuat pengemaskinian maklumat</i>
 </font>
 </td>
 </tr>
 <tr>
      			<td scope="row" width="28%">Pengesahan Kebenaran Pengemaskinian Fail</td>
                <td width="1%">:              
                </td>
      			<td width="67%" colspan="2">
                
                #if($flag_kebenaran_edit == "1") 
                <font color="blue">Dibenarkan</font>               
                #else
                <font color="red">Tidak Dibenarkan</font> 
                #end
               
                         
                </td>
    		</tr>
            #if($!flag_kebenaran_edit == "1")  
    		<tr >
      			<td scope="row">Kebenaran Dikeluarkan Oleh</td>
                <td>
                :                              
                </td>                
      			<td colspan="2">
                               
                <font color="blue">$!nama_user_yg_beri_kebenaran</font>
                
                
                </td>
    		</tr>
            #if($!catatan_kebenaran_edit != "")
            <tr >
      			<td scope="row" valign="top">Catatan</td>
                <td valign="top">
                :                              
                </td>                
      			<td  colspan="2">
                               
                <div  style="width:50%"><font color="blue">$!catatan_kebenaran_edit</font></div>  
                
                
                </td>
                
    		</tr>
            #end
            #end
                		
		</table>
        
        
        
        
</fieldset>

#end

#end
		

<input type="hidden" name="flag_kebenaran_edit" id="flag_kebenaran_edit"  value="$flag_kebenaran_edit" >    
<input type="hidden" name="user_id_kebenaran_edit" id="user_id_kebenaran_edit"  value="$user_id_kebenaran_edit">
<input type="hidden" name="id_permohonan_kebenaran" id="id_permohonan_kebenaran" value="$id_permohonan_kebenaran"/>
<input type="hidden" name="user_id_temp" id="user_id_temp"   value="$!usid" >
<input type="hidden" name="id_fail_carian" id="id_fail_carian"   value="$!id_fail_carian" >    
<input type="hidden" name="txtNoFailSub" id="txtNoFailSub"   value="$!txtNoFailSub" >   
    </td>
    
    </tr>
   
  
 
  

  



#if($!role == "adminppk")
<script type="text/javascript">
check_flag();
</script>
#end



<script type="text/javascript" >

check_length(document.${formName}.catatan_kebenaran_edit,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');

function cmdSimpan_flag()
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
	document.${formName}.command.value = "simpanFlag";
		SaveScrollXY();
		     document.${formName}.submit();		

}


function check_flag()
{
	//alert("AAA"+document.${formName}.check_flag_kebenaran_edit.checked);
	if(document.${formName}.check_flag_kebenaran_edit.checked == true)
	{
	document.${formName}.flag_kebenaran_edit.value = "1";	
	document.${formName}.user_id_kebenaran_edit.value = document.${formName}.user_id_temp.value;
	document.getElementById('tr_nama').style.display = "";	
	document.getElementById('tr_catatan').style.display = "";
	}
	else
	{
	document.${formName}.flag_kebenaran_edit.value = "";
	document.${formName}.user_id_kebenaran_edit.value = "";	
	document.getElementById('tr_nama').style.display = "none";	
	document.getElementById('tr_catatan').style.display = "none";
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


//aishah
function cetakSuratKemaskiniPerintah() {
	var idfail = $jquery('#id_fail_carian').val();
	var noFail = $jquery('#txtNoFailSub').val();
	
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupSuratEditPerintahReportView?noFail="+noFail+"&idfail="+idfail+"&report=SuratKemaskiniPerintah"; 
	/* var url = "../x/${securityToken}/ekptg.report.ppk.SuratPembetulanPerintah?nofail="+noFail+"&idfail="+idfail; */
	
	
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	
    if (hWnd.focus != null) hWnd.focus();
}


</script>


