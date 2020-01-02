
#if($!headerppk.ID_STATUS == "21" || $!headerppk.ID_STATUS == "47" || $!headerppk.ID_STATUS == "70" || $!headerppk.ID_STATUS == "169" || $!headerppk.ID_STATUS == "50" || $!headerppk.ID_STATUS == "999")

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr >
    
    <td >  
    
    #if($!headerppk.size()>0)
    #parse("app/ppk/headerppk.jsp")
    #end



</td>
</tr>
 
 <input type="hidden" id="open_maklumat_teknikal_temp" name="open_maklumat_teknikal_temp" value="$!open_maklumat_teknikal" />
   
    #if($!open_maklumat_teknikal == "yes")
    
  
  
  
   #else
              
        <input type="hidden"  name="id_statusesaduan" id="id_statusesaduan"  value="$!id_statusesaduan" >
        <input type="hidden"  name="flag_masalah_db" id="flag_masalah_db"  value="$!flag_masalah_db" >
        <input type="hidden"  name="flag_masalah_skrin" id="flag_masalah_skrin"  value="$!flag_masalah_skrin" >
        <input type="hidden"  name="flag_masalah_report" id="flag_masalah_report"  value="$!flag_masalah_report" >
        <input type="hidden"  name="ulasan_teknikal" id="ulasan_teknikal"  value="$!ulasan_teknikal" >   
        
   #end
        
   #if($!id_statusesaduan_DB == "" || $!id_statusesaduan_DB == "16125")  #else
   #if($!role == "admin_es" || $!role == "developer_es")  #end
   #end
  

  
 #if($!open_agihan=="yes" && $!open_maklumat_teknikal == "yes")
 
   #parse("app/ppk/editfail/frmMaklumatKebenaran.jsp")
   
   #if($flag_kebenaran_edit == "1")
    <tr >
    
    <td >
     <fieldset><legend><strong>Agihan Tugas Pengemaskinian Maklumat</strong></legend>
     <table width="100%" border="0" cellspacing="2" cellpadding="2" id="table_upload">
          <tr >
          
          <td width="100%"  valign="top">           
         #parse("app/ppk/editfail/frmAgihanTugas.jsp")
        
            	</td>
        </tr>
     </table>
     </fieldset>
    </td>
   </tr>

  
  #else
  
  
      #if($!role == "user_ppk")        
               <tr>
                <td >
                <fieldset><legend><strong>Pilihan Pegawai bagi Permohonan Kebenaran Kemaskini Fail</strong></legend>
               <table width="100%" border="0" cellspacing="2" cellpadding="2" id="table_upload">
                      <tr >
                      
                      <td  valign="top"></td>
                      <td  valign="top"><font color="red">*</font>Pilih Pegawai</td>
                      <td   valign="top">:</td>
                      <td  valign="top">
                    
                        <select  class="autoselect" name="pilihpegawai" id="pilihpegawai" >
                        <option value=""  >SILA PILIH PEGAWAI</option>
                       
                        #foreach($list1 in $listTechTeam_aduan) 
                        #if($list1.USER_ROLE == "adminppk" || $list1.USER_ROLE == "usernegeri_ppk") 
                        <option value="$!list1.user_id" #if($!list1.user_id==$!pilihpegawai) selected="selected" #end >$list1.user_name</option>
                        #end
                        #end 
                        </select>
                       
                      </td>
                    </tr>
                    
                    <tr>
                    	 	<td valign="top"></td>
                      		<td valign="top"><font color="red">*</font>Tujuan Pindaan</td>
                      		<td valign="top">:</td>
                      		<td valign="top">
                      		
                      		 <textarea name="txtTujPinda" id="txtTujPinda" cols="50"   rows="4"  placeholder="Sila Masukkan Tujuan Pindaan..."         
         onBlur="check_length(this,'4000','tujPinda_edit_check','tujPinda_num','normal','yes','keterangan');"  
         onKeyup="check_length(this,'4000','tujPinda_edit_check','tujPinda_num','normal','yes','keterangan');" 
         onKeydown="check_length(this,'4000','tujPinda_edit_check','tujPinda_num','normal','yes','keterangan');"                    
           >$txtTujPinda</textarea>
            <div><span id="tujPinda_num" style="color:blue;" ></span><span> Baki Aksara </span></div>
        
         <div id="tujPinda_edit_check" class="alert_msg" ></div> 
                      		
                      		</td>
                    
                    </tr>
                    
                    <tr>
                    	<td valign="top" colspan=3> &nbsp;</td>
                    	<td ><input name="cmdSimpanPeg" id="cmdSimpanPeg" value="Simpan" type="button" onClick="javascript:cmdSimpan_Pegawai()"></td>
                    	
               </table>
               
            
            
               </fieldset>
               </td>
               </tr>
               
               #end
              
  #end
  
  <tr >
    
    <td >
     <fieldset><legend><strong>Komen-Komen</strong></legend>
     <table width="100%" border="0" cellspacing="2" cellpadding="2" id="table_upload">
         
       
         <tr>
        
          
          <td width="100%"  valign="top">
           
         #parse("app/ppk/editfail/frmMaklumBalasTeknikal.jsp")

        
            	</td>
        </tr>
       

  
     </table>
     </fieldset>
    </td>
  </tr>
  
  #end
  
</table>


#else
<br />
<fieldset>
<table width="100%">
<tr>
 <td colspan="3">
 <font color="red">
 <i>Fail <b>$!txtNoFailSub</b> adalah tidak sah untuk diberi kebenaran membuat pengemaskinian maklumat.</i>
 </font>
 </td>
 </tr>
 </table>
</fieldset>

#end


<script>

check_length(document.${formName}.txtTujPinda,'4000','tujPinda_check','tujPinda_num','normal','yes','keterangan');

function cmdSimpan_Pegawai()
{
	
	if (document.${formName}.pilihpegawai.value == "")
	{
		alert("Sila pilih Pegawai");
		return;
	}else if(document.${formName}.txtTujPinda.value == ""){
		alert("Sila masukkan Tujuan Pindaan");
		return;
	}else{
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
		document.${formName}.command.value = "simpanPilihanPegawai";
		SaveScrollXY();
		     document.${formName}.submit();	
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

</script>