

<table width="100%">

<tr   >
              <td width="2%"  valign="top">&nbsp;</td>
    <td width="18%" valign="top"> <strong>PERSIDANGAN </strong></td>
    <td width="1%" valign="top">:</td>
     
    <td width="4%" valign="top"></td>
<td width="75%" valign="top">
        
        
        
        
          <textarea   name="txtSidang" id="txtSidang" cols="80"   rows="6"        
         onBlur="check_length(this,'4000','txtSidang_check','txtSidang_num','normal','no','ulasan')"  
         onKeyup="check_length(this,'4000','txtSidang_check','txtSidang_num','normal','no','ulasan')" 
         onKeydown="check_length(this,'4000','txtSidang_check','txtSidang_num','normal','no','ulasan')"                    
          $readonlymode class = "$disabledmode" 
        >$txtSidang</textarea>       
       #if($readmode == "edit")           
       <div><span id="txtSidang_num" style="color:blue;" ></span><span> Baki Aksara</span>       </div>
         #else
         <input name="txtSidang_num" id="txtSidang_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtSidang_check" class="alert_msg" ></div>    </td>
</tr>


<tr   >
              <td  valign="top">&nbsp;</td>
    <td  valign="top"> <strong>MASA</strong></td>
    <td valign="top">:</td>
     
    <td  valign="top"></td>
<td valign="top">
        
        <input name="txtMasaSidang" type="text" id="txtMasaSidang" value="$!txtMasaSidang"  size="5" maxlength="5"   $readonlymode class = "$disabledmode"  onBlur="checking_validation(this,'txtMasaSidang_check','no','persidangan','waktu');jeniswaktu1(this,'jeniswaktu')" onKeyUp="validateTarikh(this,this.value);checking_validation(this,'txtMasaSidang_check','no','persidangan','waktu');" /> 
                                  <label>
                                  #if($readmode=="view")
                                  #set($waktu = "")
                                  #if( $jeniswaktu == 1)
                                  #set($waktu = "PAGI")  
                                  #elseif($jeniswaktu == 2)
                                  #set($waktu = "TENGAHARI")                                   
                                  #elseif($jeniswaktu == 3)
                                  #set($waktu = "PETANG")      
                                  #elseif($jeniswaktu == 4)
                                  #set($waktu = "MALAM")                                   
                                  #else
                                  #set($waktu = "")
                                  #end             
                                  
                  <input name="waktu" type="text" id="waktu"    value="$waktu" size="4" maxlength="3" style="width:100"   readonly class = "disabled" />
                  <input name="jeniswaktu" type="hidden" id="jeniswaktu" value="$!jeniswaktu" />
                                  #else
                                  <select name="jeniswaktu" id="jeniswaktu" style="width:100"  onFocus="jeniswaktu2('txtMasaSidang')" >                                  
                                  #if( $jeniswaktu == 1)                                  
                                  <option value="1" id="op_pagi" >PAGI</option>
                                  <option value="2" id="op_tengahari">TENGAHARI</option>
                                  <option value="3" id="op_petang">PETANG</option>
                                  <option value="4" id="op_malam">MALAM</option>                                  
                                  <option value="">SILA PILIH</option>                                    
                                  #elseif($jeniswaktu == 2)
                                  <option value="2" id="op_tengahari">TENGAHARI</option>
                                  <option value="1" id="op_pagi">PAGI</option>                                 
                                  <option value="3" id="op_petang">PETANG</option>
                                  <option value="4" id="op_malam">MALAM</option>                                  
                                  <option value="">SILA PILIH</option>
                                  #elseif($jeniswaktu == 3)
                                  <option value="3" id="op_petang">PETANG</option>
                                  <option value="1" id="op_pagi">PAGI</option>
                                  <option value="2" id="op_tengahari">TENGAHARI</option>                                   
                                  <option value="4" id="op_malam">MALAM</option>
                                  <option value="">SILA PILIH</option>
                                  #elseif($jeniswaktu == 4)
                                  <option value="4" id="op_malam">MALAM</option>
                                  <option value="1" id="op_pagi">PAGI</option>
                                  <option value="2" id="op_tengahari">TENGAHARI</option>
                                  <option value="3" id="op_petang">PETANG</option>  
                                  <option value="">SILA PILIH</option>
                                  #else
                                  <option value="">SILA PILIH</option>                                 
                                  <option value="1" id="op_pagi" >PAGI</option>                                 
                                  <option value="2" id="op_tengahari" >TENGAHARI</option>
                                  <option value="3"  id="op_petang" >PETANG</option>
                                  <option value="4" id="op_malam" >MALAM</option>                                    
                                  #end 
                                  </select>
                                  #end                                  </label>
                                  #if($readmode == "edit" )
                                   <span style="font-size:9px;color:blue;font-style:italic">format 12 jam (HHMM)</span>       
                                 
                                  #end   
                                  <span id="txtMasaSidang_check" class="alert_msg" ></span>    </td>
</tr>

<tr   >
              <td   valign="top">&nbsp;</td>
    <td  valign="top"> <strong>TARIKH </strong></td>
    <td  valign="top">:</td>
     
    <td  valign="top"></td>
    <td  valign="top">
        
        
     <input name="txtTarikhSidang" type="text" id="txtTarikhSidang" size="10" maxlength="10"   value="$!txtTarikhSidang" onblur="validateTarikh(this,this.value);checking_validation(this,'txtTarikhSidang_check','yes','persidangan','tarikh');" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />   
    #if($readmode == "edit")    
      <a href="javascript:displayDatePicker('txtTarikhSidang',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>        
      #end
       <span id="txtTarikhSidang_check" class="alert_msg" ></span>    </td>
</tr>

<tr   >
              <td   valign="top">&nbsp;</td>
    <td  valign="top"> <strong>TEMPAT</strong></td>
    <td  valign="top">:</td>
     
    <td  valign="top"></td>
<td  valign="top">
        
        
          <textarea    name="txtTempatSidang" id="txtTempatSidang" cols="80"   rows="6"        
         onBlur="check_length(this,'4000','txtTempatSidang_check','txtTempatSidang_num','normal','no','ulasan')"  
         onKeyup="check_length(this,'4000','txtTempatSidang_check','txtTempatSidang_num','normal','no','ulasan')" 
         onKeydown="check_length(this,'4000','txtTempatSidang_check','txtTempatSidang_num','normal','no','ulasan')"                    
          $readonlymode class = "$disabledmode" 
        >$txtTempatSidang</textarea>       
       #if($readmode == "edit")           
       <div><span id="txtTempatSidang_num" style="color:blue;" ></span><span> Baki Aksara</span>       </div>
         #else
         <input name="txtTempatSidang_num" id="txtTempatSidang_num" size="3" value="4000"  style=" display:none" > 
         #end
  <div id="txtTempatSidang_check" class="alert_msg" ></div>    </td>
</tr>

<tr   >
              <td  colspan="5"  valign="top">&nbsp;</td>
</tr>

<tr   >
              <td width="1%"  valign="top">&nbsp;</td>
    <td width="16%" valign="top"> <strong>TAJUK </strong></td>
    <td width="1%" valign="top">:</td>
     
    <td width="2%" valign="top"></td>
<td width="80%" valign="top">
         
          
        
          <textarea   name="txtTajuk" id="txtTajuk" cols="80"   rows="9"        
         onBlur="check_length(this,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan')"  
         onKeyup="check_length(this,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan')" 
         onKeydown="check_length(this,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan')"                    
          $readonlymode class = "$disabledmode" 
        >$txtTajuk</textarea>       
       #if($readmode == "edit")           
       <div><span id="txtTajuk_num" style="color:blue;" ></span><span> Baki Aksara</span>       </div>
         #else
         <input name="txtTajuk_num" id="txtTajuk_num" size="3" value="30000"  style=" display:none" > 
         #end
  <div id="txtTajuk_check" class="alert_msg" ></div>    </td>
</tr>
</table>


<table width="100%" border="0">
	
	<!-- TUJUAN -->	
	<tr><td colspan="2"><b>1. TUJUAN :</b></td></tr>
	<tr>
		<td colspan="2">

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "TUJUAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="TUJUAN_MAIN_temp1"  id="TUJUAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="TUJUAN_MAIN"></span>           
   		<div id="TUJUAN_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>
			
	<!-- LATARBELAKANG -->	
	<tr><td colspan="2"><b>2. LATAR BELAKANG :</b></td></tr>
	
	<tr>
		<td width="5%">&nbsp;</td>
		<td width="95%"><b>2.1 Perihal Permohonan</b></td>
	</tr>
	
	<tr>
		<td colspan="2">
	
		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "PERIHALPERMOHONAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="PERIHALPERMOHONAN_MAIN_temp1"  id="PERIHALPERMOHONAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="PERIHALPERMOHONAN_MAIN"></span>           
   		<div id="PERIHALPERMOHONAN_MAIN_temp"></div>
	
		</td>
	</tr>

	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td>&nbsp;</td>
		<td><b>2.2 Perihal Tanah</b></td>
	</tr>
	
	<tr>
		<td colspan="2">
	
		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "PERIHALTANAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="PERIHALTANAH_MAIN_temp1"  id="PERIHALTANAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="PERIHALTANAH_MAIN"></span>           
   		<div id="PERIHALTANAH_MAIN_temp"></div>
	
		</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td>&nbsp;</td>
		<td><b>2.3 Perihal Pemohon</b></td>
	</tr>
	
	<tr>
		<td colspan="2">
	
		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "PERIHALPEMOHON" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="PERIHALPEMOHON_MAIN_temp1"  id="PERIHALPEMOHON_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="PERIHALPEMOHON_MAIN"></span>           
   		<div id="PERIHALPEMOHON_MAIN_temp"></div>
	
		</td>
	</tr>

	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td>&nbsp;</td>
		<td><b>2.4 Anggaran Nilaian Pampasan</b></td>
	</tr>
	
	<tr>
		<td colspan="2">
	
		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "ANGGARANPAMPASAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="ANGGARANPAMPASAN_MAIN_temp1"  id="ANGGARANPAMPASAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="ANGGARANPAMPASAN_MAIN"></span>           
   		<div id="ANGGARANPAMPASAN_MAIN_temp"></div>
	
		</td>
	</tr>
	
	<!-- LAPORAN TANAH -->	
	<tr><td colspan="2"><b>3. LAPORAN TANAH :</b></td></tr>
	<tr>
		<td colspan="2">

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "LAPORANTANAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="LAPORANTANAH_MAIN_temp1"  id="LAPORANTANAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="LAPORANTANAH_MAIN"></span>           
   		<div id="LAPORANTANAH_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>
	
	
	<!-- LAPORAN TEKNIKAL -->	
	<tr><td colspan="2"><b>4. LAPORAN TEKNIKAL :</b></td></tr>
	<tr>
		<td colspan="2">

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "LAPORANTEKNIKAL" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="LAPORANTEKNIKAL_MAIN_temp1"  id="LAPORANTEKNIKAL_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="LAPORANTEKNIKAL_MAIN"></span>           
   		<div id="LAPORANTEKNIKAL_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>
	
	<!-- SYOR Y.B. KAWASAN -->	
	<tr><td colspan="2"><b>5. SYOR Y.B. KAWASAN :</b></td></tr>
	<tr>
		<td colspan="2">

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "PANDANGANYB" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="PANDANGANYB_MAIN_temp1"  id="PANDANGANYB_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="PANDANGANYB_MAIN"></span>           
   		<div id="PANDANGANYB_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>
	
	<!-- SYOR PENTADBIR TANAH -->	
	<tr><td colspan="2"><b>6. SYOR PENTADBIR TANAH :</b></td></tr>
	
	<tr>
		<td>&nbsp;</td>
		<td><b>6.1 Pentadbir Tanah  (Pengarah Jabatan Ketua Pengarah Tanah dan Galian), dengan ini memperakukan supaya :</b></td>
	</tr>
	
	<tr>
		<td colspan="2">

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "PERAKUANPT" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="PERAKUANPT_MAIN_temp1"  id="PERAKUANPT_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	<span id="PERAKUANPT_MAIN"></span>           
   		<div id="PERAKUANPT_MAIN_temp"></div>
    
		</td>

	</tr>

</table>	



<script>


window.onload = check_length(document.${formName}.txtTajuk,'30000','txtTajuk_check','txtTajuk_num','normal','no','ulasan'),check_length(document.${formName}.txtTempatSidang,'4000','txtTempatSidang_check','txtTempatSidang_num','normal','no','ulasan'),check_length(document.${formName}.txtSidang,'4000','txtSidang_check','txtSidang_num','normal','no','ulasan'),checking_validation(document.${formName}.txtMasaSidang,'txtMasaSidang_check','no','persidangan','waktu'),checking_validation(document.${formName}.txtTarikhSidang,'txtTarikhSidang_check','yes','persidangan','tarikh');



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
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
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

//TUJUAN
function textarea_TUJUAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var TUJUAN_MAIN_temp1_length=0;

	if(document.${formName}.TUJUAN_MAIN_temp1 != null){

		if(document.${formName}.TUJUAN_MAIN_temp1.length>0){
			TUJUAN_MAIN_temp1_length = document.${formName}.TUJUAN_MAIN_temp1.length;
		}else{
			TUJUAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanTUJUAN_MAIN!=null){

		if(document.${formName}.txtUlasanTUJUAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanTUJUAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='TUJUAN_MAIN_tempX1' name='TUJUAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanTUJUAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='TUJUAN_MAIN_tempX1' name='TUJUAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanTUJUAN_MAIN.value+"'></td></tr>";
		}

	}


	$jquery("#TUJUAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(TUJUAN_MAIN_temp1_length>0){
			ttTUJUAN_MAIN = TUJUAN_MAIN_temp1_length;
		}else{
			ttTUJUAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttTUJUAN_MAIN = ttTUJUAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttTUJUAN_MAIN = ttTUJUAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttTUJUAN_MAIN; i++){	

  		if(ttTUJUAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.TUJUAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.TUJUAN_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanTUJUAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanTUJUAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttTUJUAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_TUJUAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttTUJUAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.TUJUAN_MAIN_tempX1.value

			}else if(ttTUJUAN_MAIN>2 && i!=(ttTUJUAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i].value

			}else if(ttTUJUAN_MAIN>1 && i!=(ttTUJUAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.TUJUAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttTUJUAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.TUJUAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.TUJUAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 1."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanTUJUAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanTUJUAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttTUJUAN_MAIN>1 && ttTUJUAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttTUJUAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_TUJUAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#TUJUAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length > 1 ){

		for (t = 0; t < TUJUAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanTUJUAN_MAIN[t].value = document.${formName}.TUJUAN_MAIN_temp1[t].value;
		}

		}else if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length < 1 ){
		
			for (t = 0; t < TUJUAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanTUJUAN_MAIN.value = document.${formName}.TUJUAN_MAIN_temp1[0].value;
			}
		}
		
		else if(TUJUAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanTUJUAN_MAIN.value = document.${formName}.TUJUAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(TUJUAN_MAIN_temp1_length > 1){

			for (t = 0; t < TUJUAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.TUJUAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.TUJUAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(TUJUAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.TUJUAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.TUJUAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttTUJUAN_MAIN; i++){		

		    if(ttTUJUAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanTUJUAN_MAIN,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanTUJUAN_MAIN[i],'30000','txtUlasanTUJUAN_MAIN_check'+i,'txtUlasanTUJUAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//close tujuan


//LATAR BELAKANG 2.1

//PERIHALPERMOHONAN
function textarea_PERIHALPERMOHONAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERIHALPERMOHONAN_MAIN_temp1_length=0;

	if(document.${formName}.PERIHALPERMOHONAN_MAIN_temp1 != null){

		if(document.${formName}.PERIHALPERMOHONAN_MAIN_temp1.length>0){
			PERIHALPERMOHONAN_MAIN_temp1_length = document.${formName}.PERIHALPERMOHONAN_MAIN_temp1.length;
		}else{
			PERIHALPERMOHONAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN!=null){

		if(document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERIHALPERMOHONAN_MAIN_tempX1' name='PERIHALPERMOHONAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERIHALPERMOHONAN_MAIN_tempX1' name='PERIHALPERMOHONAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN.value+"'></td></tr>";
		}

	}


	$jquery("#PERIHALPERMOHONAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERIHALPERMOHONAN_MAIN_temp1_length>0){
			ttPERIHALPERMOHONAN_MAIN = PERIHALPERMOHONAN_MAIN_temp1_length;
		}else{
			ttPERIHALPERMOHONAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERIHALPERMOHONAN_MAIN = ttPERIHALPERMOHONAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERIHALPERMOHONAN_MAIN = ttPERIHALPERMOHONAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERIHALPERMOHONAN_MAIN; i++){	

  		if(ttPERIHALPERMOHONAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERIHALPERMOHONAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERIHALPERMOHONAN_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='8%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='90%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERIHALPERMOHONAN_MAIN\" id=\"txtUlasanPERIHALPERMOHONAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check','txtUlasanPERIHALPERMOHONAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check','txtUlasanPERIHALPERMOHONAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALPERMOHONAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERIHALPERMOHONAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='90%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanPERIHALPERMOHONAN_MAIN\" id=\"txtUlasanPERIHALPERMOHONAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check','txtUlasanPERIHALPERMOHONAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check','txtUlasanPERIHALPERMOHONAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALPERMOHONAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERIHALPERMOHONAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERIHALPERMOHONAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERIHALPERMOHONAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERIHALPERMOHONAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERIHALPERMOHONAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERIHALPERMOHONAN_MAIN_tempX1.value

			}else if(ttPERIHALPERMOHONAN_MAIN>2 && i!=(ttPERIHALPERMOHONAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERIHALPERMOHONAN_MAIN_tempX1[i].value

			}else if(ttPERIHALPERMOHONAN_MAIN>1 && i!=(ttPERIHALPERMOHONAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERIHALPERMOHONAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERIHALPERMOHONAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERIHALPERMOHONAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERIHALPERMOHONAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERIHALPERMOHONAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERIHALPERMOHONAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='8%' valign='top' align='right'> 2.1."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='90%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERIHALPERMOHONAN_MAIN\" id=\"txtUlasanPERIHALPERMOHONAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"','txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"','txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='90%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanPERIHALPERMOHONAN_MAIN\" id=\"txtUlasanPERIHALPERMOHONAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"','txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"','txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERIHALPERMOHONAN_MAIN>1 && ttPERIHALPERMOHONAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERIHALPERMOHONAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERIHALPERMOHONAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERIHALPERMOHONAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERIHALPERMOHONAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERIHALPERMOHONAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN.length > 1 ){

		for (t = 0; t < PERIHALPERMOHONAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN[t].value = document.${formName}.PERIHALPERMOHONAN_MAIN_temp1[t].value;
		}

		}else if(PERIHALPERMOHONAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN.length < 1 ){
		
			for (t = 0; t < PERIHALPERMOHONAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN.value = document.${formName}.PERIHALPERMOHONAN_MAIN_temp1[0].value;
			}
		}
		
		else if(PERIHALPERMOHONAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN.value = document.${formName}.PERIHALPERMOHONAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERIHALPERMOHONAN_MAIN_temp1_length > 1){

			for (t = 0; t < PERIHALPERMOHONAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERIHALPERMOHONAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERIHALPERMOHONAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERIHALPERMOHONAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERIHALPERMOHONAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERIHALPERMOHONAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERIHALPERMOHONAN_MAIN; i++){		

		    if(ttPERIHALPERMOHONAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check','txtUlasanPERIHALPERMOHONAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN[i],'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check'+i,'txtUlasanPERIHALPERMOHONAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//close PERIHALPERMOHONAN


//PERIHALTANAH
function textarea_PERIHALTANAH_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERIHALTANAH_MAIN_temp1_length=0;

	if(document.${formName}.PERIHALTANAH_MAIN_temp1 != null){

		if(document.${formName}.PERIHALTANAH_MAIN_temp1.length>0){
			PERIHALTANAH_MAIN_temp1_length = document.${formName}.PERIHALTANAH_MAIN_temp1.length;
		}else{
			PERIHALTANAH_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERIHALTANAH_MAIN!=null){

		if(document.${formName}.txtUlasanPERIHALTANAH_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERIHALTANAH_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERIHALTANAH_MAIN_tempX1' name='PERIHALTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALTANAH_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERIHALTANAH_MAIN_tempX1' name='PERIHALTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALTANAH_MAIN.value+"'></td></tr>";
		}

	}


	$jquery("#PERIHALTANAH_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERIHALTANAH_MAIN_temp1_length>0){
			ttPERIHALTANAH_MAIN = PERIHALTANAH_MAIN_temp1_length;
		}else{
			ttPERIHALTANAH_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERIHALTANAH_MAIN = ttPERIHALTANAH_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERIHALTANAH_MAIN = ttPERIHALTANAH_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERIHALTANAH_MAIN; i++){	

  		if(ttPERIHALTANAH_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='8%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='90%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERIHALTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='90%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERIHALTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERIHALTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERIHALTANAH_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERIHALTANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERIHALTANAH_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1.value

			}else if(ttPERIHALTANAH_MAIN>2 && i!=(ttPERIHALTANAH_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[i].value

			}else if(ttPERIHALTANAH_MAIN>1 && i!=(ttPERIHALTANAH_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERIHALTANAH_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='8%' valign='top' align='right'> 2.2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='90%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='90%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERIHALTANAH_MAIN>1 && ttPERIHALTANAH_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERIHALTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERIHALTANAH_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERIHALTANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERIHALTANAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length > 1 ){

		for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERIHALTANAH_MAIN[t].value = document.${formName}.PERIHALTANAH_MAIN_temp1[t].value;
		}

		}else if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length < 1 ){
		
			for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERIHALTANAH_MAIN.value = document.${formName}.PERIHALTANAH_MAIN_temp1[0].value;
			}
		}
		
		else if(PERIHALTANAH_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERIHALTANAH_MAIN.value = document.${formName}.PERIHALTANAH_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERIHALTANAH_MAIN_temp1_length > 1){

			for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERIHALTANAH_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERIHALTANAH_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERIHALTANAH_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERIHALTANAH_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERIHALTANAH_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERIHALTANAH_MAIN; i++){		

		    if(ttPERIHALTANAH_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERIHALTANAH_MAIN,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERIHALTANAH_MAIN[i],'30000','txtUlasanPERIHALTANAH_MAIN_check'+i,'txtUlasanPERIHALTANAH_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//close PERIHALTANAH


//PERIHALPEMOHON
function textarea_PERIHALPEMOHON_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERIHALPEMOHON_MAIN_temp1_length=0;

	if(document.${formName}.PERIHALPEMOHON_MAIN_temp1 != null){

		if(document.${formName}.PERIHALPEMOHON_MAIN_temp1.length>0){
			PERIHALPEMOHON_MAIN_temp1_length = document.${formName}.PERIHALPEMOHON_MAIN_temp1.length;
		}else{
			PERIHALPEMOHON_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERIHALPEMOHON_MAIN!=null){

		if(document.${formName}.txtUlasanPERIHALPEMOHON_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERIHALPEMOHON_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERIHALPEMOHON_MAIN_tempX1' name='PERIHALPEMOHON_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALPEMOHON_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERIHALPEMOHON_MAIN_tempX1' name='PERIHALPEMOHON_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALPEMOHON_MAIN.value+"'></td></tr>";
		}

	}


	$jquery("#PERIHALPEMOHON_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERIHALPEMOHON_MAIN_temp1_length>0){
			ttPERIHALPEMOHON_MAIN = PERIHALPEMOHON_MAIN_temp1_length;
		}else{
			ttPERIHALPEMOHON_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERIHALPEMOHON_MAIN = ttPERIHALPEMOHON_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERIHALPEMOHON_MAIN = ttPERIHALPEMOHON_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERIHALPEMOHON_MAIN; i++){	

  		if(ttPERIHALPEMOHON_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERIHALPEMOHON_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERIHALPEMOHON_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='8%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='90%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERIHALPEMOHON_MAIN\" id=\"txtUlasanPERIHALPEMOHON_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPEMOHON_MAIN_check','txtUlasanPERIHALPEMOHON_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPEMOHON_MAIN_check','txtUlasanPERIHALPEMOHON_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALPEMOHON_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERIHALPEMOHON_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='90%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanPERIHALPEMOHON_MAIN\" id=\"txtUlasanPERIHALPEMOHON_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPEMOHON_MAIN_check','txtUlasanPERIHALPEMOHON_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPEMOHON_MAIN_check','txtUlasanPERIHALPEMOHON_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALPEMOHON_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERIHALPEMOHON_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERIHALPEMOHON_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERIHALPEMOHON_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERIHALPEMOHON_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERIHALPEMOHON_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERIHALPEMOHON_MAIN_tempX1.value

			}else if(ttPERIHALPEMOHON_MAIN>2 && i!=(ttPERIHALPEMOHON_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERIHALPEMOHON_MAIN_tempX1[i].value

			}else if(ttPERIHALPEMOHON_MAIN>1 && i!=(ttPERIHALPEMOHON_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERIHALPEMOHON_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERIHALPEMOHON_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERIHALPEMOHON_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERIHALPEMOHON_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERIHALPEMOHON_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERIHALPEMOHON_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='8%' valign='top' align='right'> 2.3."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='90%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERIHALPEMOHON_MAIN\" id=\"txtUlasanPERIHALPEMOHON_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPEMOHON_MAIN_check"+i+"','txtUlasanPERIHALPEMOHON_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPEMOHON_MAIN_check"+i+"','txtUlasanPERIHALPEMOHON_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALPEMOHON_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALPEMOHON_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='90%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanPERIHALPEMOHON_MAIN\" id=\"txtUlasanPERIHALPEMOHON_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPEMOHON_MAIN_check"+i+"','txtUlasanPERIHALPEMOHON_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPEMOHON_MAIN_check"+i+"','txtUlasanPERIHALPEMOHON_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALPEMOHON_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALPEMOHON_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERIHALPEMOHON_MAIN>1 && ttPERIHALPEMOHON_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERIHALPEMOHON_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERIHALPEMOHON_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERIHALPEMOHON_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERIHALPEMOHON_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERIHALPEMOHON_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALPEMOHON_MAIN.length > 1 ){

		for (t = 0; t < PERIHALPEMOHON_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERIHALPEMOHON_MAIN[t].value = document.${formName}.PERIHALPEMOHON_MAIN_temp1[t].value;
		}

		}else if(PERIHALPEMOHON_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALPEMOHON_MAIN.length < 1 ){
		
			for (t = 0; t < PERIHALPEMOHON_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERIHALPEMOHON_MAIN.value = document.${formName}.PERIHALPEMOHON_MAIN_temp1[0].value;
			}
		}
		
		else if(PERIHALPEMOHON_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERIHALPEMOHON_MAIN.value = document.${formName}.PERIHALPEMOHON_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERIHALPEMOHON_MAIN_temp1_length > 1){

			for (t = 0; t < PERIHALPEMOHON_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERIHALPEMOHON_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERIHALPEMOHON_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERIHALPEMOHON_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERIHALPEMOHON_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERIHALPEMOHON_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERIHALPEMOHON_MAIN; i++){		

		    if(ttPERIHALPEMOHON_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERIHALPEMOHON_MAIN,'30000','txtUlasanPERIHALPEMOHON_MAIN_check','txtUlasanPERIHALPEMOHON_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERIHALPEMOHON_MAIN[i],'30000','txtUlasanPERIHALPEMOHON_MAIN_check'+i,'txtUlasanPERIHALPEMOHON_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//close PERIHALPEMOHON


//ANGGARANPAMPASAN
function textarea_ANGGARANPAMPASAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var ANGGARANPAMPASAN_MAIN_temp1_length=0;

	if(document.${formName}.ANGGARANPAMPASAN_MAIN_temp1 != null){

		if(document.${formName}.ANGGARANPAMPASAN_MAIN_temp1.length>0){
			ANGGARANPAMPASAN_MAIN_temp1_length = document.${formName}.ANGGARANPAMPASAN_MAIN_temp1.length;
		}else{
			ANGGARANPAMPASAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN!=null){

		if(document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ANGGARANPAMPASAN_MAIN_tempX1' name='ANGGARANPAMPASAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ANGGARANPAMPASAN_MAIN_tempX1' name='ANGGARANPAMPASAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN.value+"'></td></tr>";
		}

	}


	$jquery("#ANGGARANPAMPASAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ANGGARANPAMPASAN_MAIN_temp1_length>0){
			ttANGGARANPAMPASAN_MAIN = ANGGARANPAMPASAN_MAIN_temp1_length;
		}else{
			ttANGGARANPAMPASAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttANGGARANPAMPASAN_MAIN = ttANGGARANPAMPASAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttANGGARANPAMPASAN_MAIN = ttANGGARANPAMPASAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttANGGARANPAMPASAN_MAIN; i++){	

  		if(ttANGGARANPAMPASAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ANGGARANPAMPASAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ANGGARANPAMPASAN_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='8%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='90%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanANGGARANPAMPASAN_MAIN\" id=\"txtUlasanANGGARANPAMPASAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanANGGARANPAMPASAN_MAIN_check','txtUlasanANGGARANPAMPASAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanANGGARANPAMPASAN_MAIN_check','txtUlasanANGGARANPAMPASAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanANGGARANPAMPASAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanANGGARANPAMPASAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='90%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanANGGARANPAMPASAN_MAIN\" id=\"txtUlasanANGGARANPAMPASAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanANGGARANPAMPASAN_MAIN_check','txtUlasanANGGARANPAMPASAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanANGGARANPAMPASAN_MAIN_check','txtUlasanANGGARANPAMPASAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanANGGARANPAMPASAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanANGGARANPAMPASAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ANGGARANPAMPASAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttANGGARANPAMPASAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ANGGARANPAMPASAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttANGGARANPAMPASAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ANGGARANPAMPASAN_MAIN_tempX1.value

			}else if(ttANGGARANPAMPASAN_MAIN>2 && i!=(ttANGGARANPAMPASAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ANGGARANPAMPASAN_MAIN_tempX1[i].value

			}else if(ttANGGARANPAMPASAN_MAIN>1 && i!=(ttANGGARANPAMPASAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ANGGARANPAMPASAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ANGGARANPAMPASAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ANGGARANPAMPASAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttANGGARANPAMPASAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ANGGARANPAMPASAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ANGGARANPAMPASAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='8%' valign='top' align='right'> 2.4."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='90%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanANGGARANPAMPASAN_MAIN\" id=\"txtUlasanANGGARANPAMPASAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanANGGARANPAMPASAN_MAIN_check"+i+"','txtUlasanANGGARANPAMPASAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanANGGARANPAMPASAN_MAIN_check"+i+"','txtUlasanANGGARANPAMPASAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanANGGARANPAMPASAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanANGGARANPAMPASAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='90%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanANGGARANPAMPASAN_MAIN\" id=\"txtUlasanANGGARANPAMPASAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanANGGARANPAMPASAN_MAIN_check"+i+"','txtUlasanANGGARANPAMPASAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanANGGARANPAMPASAN_MAIN_check"+i+"','txtUlasanANGGARANPAMPASAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanANGGARANPAMPASAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanANGGARANPAMPASAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttANGGARANPAMPASAN_MAIN>1 && ttANGGARANPAMPASAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ANGGARANPAMPASAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttANGGARANPAMPASAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ANGGARANPAMPASAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ANGGARANPAMPASAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ANGGARANPAMPASAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN.length > 1 ){

		for (t = 0; t < ANGGARANPAMPASAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN[t].value = document.${formName}.ANGGARANPAMPASAN_MAIN_temp1[t].value;
		}

		}else if(ANGGARANPAMPASAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN.length < 1 ){
		
			for (t = 0; t < ANGGARANPAMPASAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN.value = document.${formName}.ANGGARANPAMPASAN_MAIN_temp1[0].value;
			}
		}
		
		else if(ANGGARANPAMPASAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN.value = document.${formName}.ANGGARANPAMPASAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ANGGARANPAMPASAN_MAIN_temp1_length > 1){

			for (t = 0; t < ANGGARANPAMPASAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ANGGARANPAMPASAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ANGGARANPAMPASAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ANGGARANPAMPASAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.ANGGARANPAMPASAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ANGGARANPAMPASAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttANGGARANPAMPASAN_MAIN; i++){		

		    if(ttANGGARANPAMPASAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN,'30000','txtUlasanANGGARANPAMPASAN_MAIN_check','txtUlasanANGGARANPAMPASAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN[i],'30000','txtUlasanANGGARANPAMPASAN_MAIN_check'+i,'txtUlasanANGGARANPAMPASAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//close ANGGARANPAMPASAN

//LAPORANTANAH
function textarea_LAPORANTANAH_MAIN(tambahtolak,jenis,index_tolak){
	
	var LAPORANTANAH_MAIN_temp1_length=0;

	if(document.${formName}.LAPORANTANAH_MAIN_temp1 != null){

		if(document.${formName}.LAPORANTANAH_MAIN_temp1.length>0){
			LAPORANTANAH_MAIN_temp1_length = document.${formName}.LAPORANTANAH_MAIN_temp1.length;
		}else{
			LAPORANTANAH_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanLAPORANTANAH_MAIN!=null){

		if(document.${formName}.txtUlasanLAPORANTANAH_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanLAPORANTANAH_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='LAPORANTANAH_MAIN_tempX1' name='LAPORANTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTANAH_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='LAPORANTANAH_MAIN_tempX1' name='LAPORANTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTANAH_MAIN.value+"'></td></tr>";
		}

	}


	$jquery("#LAPORANTANAH_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(LAPORANTANAH_MAIN_temp1_length>0){
			ttLAPORANTANAH_MAIN = LAPORANTANAH_MAIN_temp1_length;
		}else{
			ttLAPORANTANAH_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttLAPORANTANAH_MAIN = ttLAPORANTANAH_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttLAPORANTANAH_MAIN = ttLAPORANTANAH_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttLAPORANTANAH_MAIN; i++){	

  		if(ttLAPORANTANAH_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLAPORANTANAH_MAIN\" id=\"txtUlasanLAPORANTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check','txtUlasanLAPORANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check','txtUlasanLAPORANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLAPORANTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanLAPORANTANAH_MAIN\" id=\"txtUlasanLAPORANTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check','txtUlasanLAPORANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check','txtUlasanLAPORANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLAPORANTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_LAPORANTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttLAPORANTANAH_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_LAPORANTANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttLAPORANTANAH_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1.value

			}else if(ttLAPORANTANAH_MAIN>2 && i!=(ttLAPORANTANAH_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[i].value

			}else if(ttLAPORANTANAH_MAIN>1 && i!=(ttLAPORANTANAH_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[i+1].value	
				}	

			}else if(ttLAPORANTANAH_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLAPORANTANAH_MAIN\" id=\"txtUlasanLAPORANTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check"+i+"','txtUlasanLAPORANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check"+i+"','txtUlasanLAPORANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanLAPORANTANAH_MAIN\" id=\"txtUlasanLAPORANTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check"+i+"','txtUlasanLAPORANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check"+i+"','txtUlasanLAPORANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttLAPORANTANAH_MAIN>1 && ttLAPORANTANAH_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_LAPORANTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttLAPORANTANAH_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_LAPORANTANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#LAPORANTANAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(LAPORANTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTANAH_MAIN.length > 1 ){

		for (t = 0; t < LAPORANTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanLAPORANTANAH_MAIN[t].value = document.${formName}.LAPORANTANAH_MAIN_temp1[t].value;
		}

		}else if(LAPORANTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTANAH_MAIN.length < 1 ){
		
			for (t = 0; t < LAPORANTANAH_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanLAPORANTANAH_MAIN.value = document.${formName}.LAPORANTANAH_MAIN_temp1[0].value;
			}
		}
		
		else if(LAPORANTANAH_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanLAPORANTANAH_MAIN.value = document.${formName}.LAPORANTANAH_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(LAPORANTANAH_MAIN_temp1_length > 1){

			for (t = 0; t < LAPORANTANAH_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.LAPORANTANAH_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.LAPORANTANAH_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(LAPORANTANAH_MAIN_temp1_length == 1){
			
	 		document.${formName}.LAPORANTANAH_MAIN_temp1.value = "";			
	 		var element = document.${formName}.LAPORANTANAH_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttLAPORANTANAH_MAIN; i++){		

		    if(ttLAPORANTANAH_MAIN==1){
		 check_length(document.${formName}.txtUlasanLAPORANTANAH_MAIN,'30000','txtUlasanLAPORANTANAH_MAIN_check','txtUlasanLAPORANTANAH_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanLAPORANTANAH_MAIN[i],'30000','txtUlasanLAPORANTANAH_MAIN_check'+i,'txtUlasanLAPORANTANAH_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//close LAPORANTANAH

//LAPORANTEKNIKAL
function textarea_LAPORANTEKNIKAL_MAIN(tambahtolak,jenis,index_tolak){
	
	var LAPORANTEKNIKAL_MAIN_temp1_length=0;

	if(document.${formName}.LAPORANTEKNIKAL_MAIN_temp1 != null){

		if(document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.length>0){
			LAPORANTEKNIKAL_MAIN_temp1_length = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.length;
		}else{
			LAPORANTEKNIKAL_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN!=null){

		if(document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='LAPORANTEKNIKAL_MAIN_tempX1' name='LAPORANTEKNIKAL_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='LAPORANTEKNIKAL_MAIN_tempX1' name='LAPORANTEKNIKAL_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.value+"'></td></tr>";
		}

	}


	$jquery("#LAPORANTEKNIKAL_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(LAPORANTEKNIKAL_MAIN_temp1_length>0){
			ttLAPORANTEKNIKAL_MAIN = LAPORANTEKNIKAL_MAIN_temp1_length;
		}else{
			ttLAPORANTEKNIKAL_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttLAPORANTEKNIKAL_MAIN = ttLAPORANTEKNIKAL_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttLAPORANTEKNIKAL_MAIN = ttLAPORANTEKNIKAL_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttLAPORANTEKNIKAL_MAIN; i++){	

  		if(ttLAPORANTEKNIKAL_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLAPORANTEKNIKAL_MAIN\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLAPORANTEKNIKAL_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanLAPORANTEKNIKAL_MAIN\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLAPORANTEKNIKAL_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_LAPORANTEKNIKAL_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttLAPORANTEKNIKAL_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_LAPORANTEKNIKAL_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttLAPORANTEKNIKAL_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1.value

			}else if(ttLAPORANTEKNIKAL_MAIN>2 && i!=(ttLAPORANTEKNIKAL_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[i].value

			}else if(ttLAPORANTEKNIKAL_MAIN>1 && i!=(ttLAPORANTEKNIKAL_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[i+1].value	
				}	

			}else if(ttLAPORANTEKNIKAL_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LAPORANTEKNIKAL_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLAPORANTEKNIKAL_MAIN\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"','txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"','txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanLAPORANTEKNIKAL_MAIN\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"','txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"','txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttLAPORANTEKNIKAL_MAIN>1 && ttLAPORANTEKNIKAL_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_LAPORANTEKNIKAL_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttLAPORANTEKNIKAL_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_LAPORANTEKNIKAL_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#LAPORANTEKNIKAL_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(LAPORANTEKNIKAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length > 1 ){

		for (t = 0; t < LAPORANTEKNIKAL_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN[t].value = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[t].value;
		}

		}else if(LAPORANTEKNIKAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length < 1 ){
		
			for (t = 0; t < LAPORANTEKNIKAL_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.value = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[0].value;
			}
		}
		
		else if(LAPORANTEKNIKAL_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.value = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(LAPORANTEKNIKAL_MAIN_temp1_length > 1){

			for (t = 0; t < LAPORANTEKNIKAL_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(LAPORANTEKNIKAL_MAIN_temp1_length == 1){
			
	 		document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.value = "";			
	 		var element = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttLAPORANTEKNIKAL_MAIN; i++){		

		    if(ttLAPORANTEKNIKAL_MAIN==1){
		 check_length(document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN[i],'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check'+i,'txtUlasanLAPORANTEKNIKAL_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//close LAPORANTEKNIKAL


//PANDANGANYB
function textarea_PANDANGANYB_MAIN(tambahtolak,jenis,index_tolak){
	
	var PANDANGANYB_MAIN_temp1_length=0;

	if(document.${formName}.PANDANGANYB_MAIN_temp1 != null){

		if(document.${formName}.PANDANGANYB_MAIN_temp1.length>0){
			PANDANGANYB_MAIN_temp1_length = document.${formName}.PANDANGANYB_MAIN_temp1.length;
		}else{
			PANDANGANYB_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPANDANGANYB_MAIN!=null){

		if(document.${formName}.txtUlasanPANDANGANYB_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPANDANGANYB_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PANDANGANYB_MAIN_tempX1' name='PANDANGANYB_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPANDANGANYB_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PANDANGANYB_MAIN_tempX1' name='PANDANGANYB_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPANDANGANYB_MAIN.value+"'></td></tr>";
		}

	}


	$jquery("#PANDANGANYB_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PANDANGANYB_MAIN_temp1_length>0){
			ttPANDANGANYB_MAIN = PANDANGANYB_MAIN_temp1_length;
		}else{
			ttPANDANGANYB_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPANDANGANYB_MAIN = ttPANDANGANYB_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPANDANGANYB_MAIN = ttPANDANGANYB_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPANDANGANYB_MAIN; i++){	

  		if(ttPANDANGANYB_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PANDANGANYB_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PANDANGANYB_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPANDANGANYB_MAIN\" id=\"txtUlasanPANDANGANYB_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPANDANGANYB_MAIN_check','txtUlasanPANDANGANYB_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPANDANGANYB_MAIN_check','txtUlasanPANDANGANYB_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPANDANGANYB_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPANDANGANYB_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanPANDANGANYB_MAIN\" id=\"txtUlasanPANDANGANYB_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPANDANGANYB_MAIN_check','txtUlasanPANDANGANYB_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPANDANGANYB_MAIN_check','txtUlasanPANDANGANYB_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPANDANGANYB_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPANDANGANYB_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PANDANGANYB_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPANDANGANYB_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PANDANGANYB_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPANDANGANYB_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PANDANGANYB_MAIN_tempX1.value

			}else if(ttPANDANGANYB_MAIN>2 && i!=(ttPANDANGANYB_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PANDANGANYB_MAIN_tempX1[i].value

			}else if(ttPANDANGANYB_MAIN>1 && i!=(ttPANDANGANYB_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PANDANGANYB_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PANDANGANYB_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PANDANGANYB_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPANDANGANYB_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PANDANGANYB_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PANDANGANYB_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 5."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPANDANGANYB_MAIN\" id=\"txtUlasanPANDANGANYB_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPANDANGANYB_MAIN_check"+i+"','txtUlasanPANDANGANYB_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPANDANGANYB_MAIN_check"+i+"','txtUlasanPANDANGANYB_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPANDANGANYB_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPANDANGANYB_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanPANDANGANYB_MAIN\" id=\"txtUlasanPANDANGANYB_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPANDANGANYB_MAIN_check"+i+"','txtUlasanPANDANGANYB_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPANDANGANYB_MAIN_check"+i+"','txtUlasanPANDANGANYB_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPANDANGANYB_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPANDANGANYB_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPANDANGANYB_MAIN>1 && ttPANDANGANYB_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PANDANGANYB_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPANDANGANYB_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PANDANGANYB_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PANDANGANYB_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PANDANGANYB_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPANDANGANYB_MAIN.length > 1 ){

		for (t = 0; t < PANDANGANYB_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPANDANGANYB_MAIN[t].value = document.${formName}.PANDANGANYB_MAIN_temp1[t].value;
		}

		}else if(PANDANGANYB_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPANDANGANYB_MAIN.length < 1 ){
		
			for (t = 0; t < PANDANGANYB_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPANDANGANYB_MAIN.value = document.${formName}.PANDANGANYB_MAIN_temp1[0].value;
			}
		}
		
		else if(PANDANGANYB_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPANDANGANYB_MAIN.value = document.${formName}.PANDANGANYB_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PANDANGANYB_MAIN_temp1_length > 1){

			for (t = 0; t < PANDANGANYB_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PANDANGANYB_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PANDANGANYB_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PANDANGANYB_MAIN_temp1_length == 1){
			
	 		document.${formName}.PANDANGANYB_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PANDANGANYB_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPANDANGANYB_MAIN; i++){		

		    if(ttPANDANGANYB_MAIN==1){
		 check_length(document.${formName}.txtUlasanPANDANGANYB_MAIN,'30000','txtUlasanPANDANGANYB_MAIN_check','txtUlasanPANDANGANYB_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPANDANGANYB_MAIN[i],'30000','txtUlasanPANDANGANYB_MAIN_check'+i,'txtUlasanPANDANGANYB_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//close PANDANGANYB

//PERAKUANPT
function textarea_PERAKUANPT_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERAKUANPT_MAIN_temp1_length=0;

	if(document.${formName}.PERAKUANPT_MAIN_temp1 != null){

		if(document.${formName}.PERAKUANPT_MAIN_temp1.length>0){
			PERAKUANPT_MAIN_temp1_length = document.${formName}.PERAKUANPT_MAIN_temp1.length;
		}else{
			PERAKUANPT_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERAKUANPT_MAIN!=null){

		if(document.${formName}.txtUlasanPERAKUANPT_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERAKUANPT_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT_MAIN_tempX1' name='PERAKUANPT_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT_MAIN_tempX1' name='PERAKUANPT_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT_MAIN.value+"'></td></tr>";
		}

	}


	$jquery("#PERAKUANPT_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERAKUANPT_MAIN_temp1_length>0){
			ttPERAKUANPT_MAIN = PERAKUANPT_MAIN_temp1_length;
		}else{
			ttPERAKUANPT_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERAKUANPT_MAIN = ttPERAKUANPT_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERAKUANPT_MAIN = ttPERAKUANPT_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERAKUANPT_MAIN; i++){	

  		if(ttPERAKUANPT_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='8%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='90%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERAKUANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='90%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERAKUANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERAKUANPT_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERAKUANPT_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERAKUANPT_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERAKUANPT_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1.value

			}else if(ttPERAKUANPT_MAIN>2 && i!=(ttPERAKUANPT_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[i].value

			}else if(ttPERAKUANPT_MAIN>1 && i!=(ttPERAKUANPT_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERAKUANPT_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='8%' valign='top' align='right'> 6.1."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='90%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='90%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERAKUANPT_MAIN>1 && ttPERAKUANPT_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERAKUANPT_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERAKUANPT_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERAKUANPT_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERAKUANPT_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERAKUANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT_MAIN.length > 1 ){

		for (t = 0; t < PERAKUANPT_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERAKUANPT_MAIN[t].value = document.${formName}.PERAKUANPT_MAIN_temp1[t].value;
		}

		}else if(PERAKUANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT_MAIN.length < 1 ){
		
			for (t = 0; t < PERAKUANPT_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERAKUANPT_MAIN.value = document.${formName}.PERAKUANPT_MAIN_temp1[0].value;
			}
		}
		
		else if(PERAKUANPT_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERAKUANPT_MAIN.value = document.${formName}.PERAKUANPT_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERAKUANPT_MAIN_temp1_length > 1){

			for (t = 0; t < PERAKUANPT_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERAKUANPT_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERAKUANPT_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERAKUANPT_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERAKUANPT_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERAKUANPT_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERAKUANPT_MAIN; i++){		

		    if(ttPERAKUANPT_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERAKUANPT_MAIN,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERAKUANPT_MAIN[i],'30000','txtUlasanPERAKUANPT_MAIN_check'+i,'txtUlasanPERAKUANPT_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//close PERAKUANPT

function textarea_kerosakan1()
{

	//TUJUAN
	var TUJUAN_MAIN_temp1_length=0;
    
	if(document.${formName}.TUJUAN_MAIN_temp1 != null){
		if(document.${formName}.TUJUAN_MAIN_temp1.length>0){
			TUJUAN_MAIN_temp1_length = document.${formName}.TUJUAN_MAIN_temp1.length;
		}else{
			TUJUAN_MAIN_temp1_length = 1;
		}
	}

    if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length > 1 ){
		for (t = 0; t < TUJUAN_MAIN_temp1_length; t++){	
    		document.${formName}.TUJUAN_MAIN_temp1[t].value = document.${formName}.txtUlasanTUJUAN_MAIN[t].value;
		}
	}else if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length < 1 ){
		for (t = 0; t < TUJUAN_MAIN_temp1_length; t++){	
    		document.${formName}.TUJUAN_MAIN_temp1[0].value = document.${formName}.txtUlasanTUJUAN_MAIN.value;
    	}
	}else if(TUJUAN_MAIN_temp1_length == 1){
		document.${formName}.TUJUAN_MAIN_temp1.value = document.${formName}.txtUlasanTUJUAN_MAIN.value;
	}


  	//PERIHALPERMOHONAN
	var PERIHALPERMOHONAN_MAIN_temp1_length=0;
    
	if(document.${formName}.PERIHALPERMOHONAN_MAIN_temp1 != null){
		if(document.${formName}.PERIHALPERMOHONAN_MAIN_temp1.length>0){
			PERIHALPERMOHONAN_MAIN_temp1_length = document.${formName}.PERIHALPERMOHONAN_MAIN_temp1.length;
		}else{
			PERIHALPERMOHONAN_MAIN_temp1_length = 1;
		}
	}

    if(PERIHALPERMOHONAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN.length > 1 ){
		for (t = 0; t < PERIHALPERMOHONAN_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALPERMOHONAN_MAIN_temp1[t].value = document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN[t].value;
		}
	}else if(PERIHALPERMOHONAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN.length < 1 ){
		for (t = 0; t < PERIHALPERMOHONAN_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALPERMOHONAN_MAIN_temp1[0].value = document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN.value;
    	}
	}else if(PERIHALPERMOHONAN_MAIN_temp1_length == 1){
		document.${formName}.PERIHALPERMOHONAN_MAIN_temp1.value = document.${formName}.txtUlasanPERIHALPERMOHONAN_MAIN.value;
	}

    
    //PERIHALTANAH
	var PERIHALTANAH_MAIN_temp1_length=0;
    
	if(document.${formName}.PERIHALTANAH_MAIN_temp1 != null){
		if(document.${formName}.PERIHALTANAH_MAIN_temp1.length>0){
			PERIHALTANAH_MAIN_temp1_length = document.${formName}.PERIHALTANAH_MAIN_temp1.length;
		}else{
			PERIHALTANAH_MAIN_temp1_length = 1;
		}
	}

    if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length > 1 ){
		for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALTANAH_MAIN_temp1[t].value = document.${formName}.txtUlasanPERIHALTANAH_MAIN[t].value;
		}
	}else if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length < 1 ){
		for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALTANAH_MAIN_temp1[0].value = document.${formName}.txtUlasanPERIHALTANAH_MAIN.value;
    	}
	}else if(PERIHALTANAH_MAIN_temp1_length == 1){
		document.${formName}.PERIHALTANAH_MAIN_temp1.value = document.${formName}.txtUlasanPERIHALTANAH_MAIN.value;
	}

    
    //PERIHALPEMOHON
	var PERIHALPEMOHON_MAIN_temp1_length=0;
    
	if(document.${formName}.PERIHALPEMOHON_MAIN_temp1 != null){
		if(document.${formName}.PERIHALPEMOHON_MAIN_temp1.length>0){
			PERIHALPEMOHON_MAIN_temp1_length = document.${formName}.PERIHALPEMOHON_MAIN_temp1.length;
		}else{
			PERIHALPEMOHON_MAIN_temp1_length = 1;
		}
	}

    if(PERIHALPEMOHON_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALPEMOHON_MAIN.length > 1 ){
		for (t = 0; t < PERIHALPEMOHON_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALPEMOHON_MAIN_temp1[t].value = document.${formName}.txtUlasanPERIHALPEMOHON_MAIN[t].value;
		}
	}else if(PERIHALPEMOHON_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALPEMOHON_MAIN.length < 1 ){
		for (t = 0; t < PERIHALPEMOHON_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALPEMOHON_MAIN_temp1[0].value = document.${formName}.txtUlasanPERIHALPEMOHON_MAIN.value;
    	}
	}else if(PERIHALPEMOHON_MAIN_temp1_length == 1){
		document.${formName}.PERIHALPEMOHON_MAIN_temp1.value = document.${formName}.txtUlasanPERIHALPEMOHON_MAIN.value;
	}

    
    //ANGGARANPAMPASAN
	var ANGGARANPAMPASAN_MAIN_temp1_length=0;
    
	if(document.${formName}.ANGGARANPAMPASAN_MAIN_temp1 != null){
		if(document.${formName}.ANGGARANPAMPASAN_MAIN_temp1.length>0){
			ANGGARANPAMPASAN_MAIN_temp1_length = document.${formName}.ANGGARANPAMPASAN_MAIN_temp1.length;
		}else{
			ANGGARANPAMPASAN_MAIN_temp1_length = 1;
		}
	}

    if(ANGGARANPAMPASAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN.length > 1 ){
		for (t = 0; t < ANGGARANPAMPASAN_MAIN_temp1_length; t++){	
    		document.${formName}.ANGGARANPAMPASAN_MAIN_temp1[t].value = document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN[t].value;
		}
	}else if(ANGGARANPAMPASAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN.length < 1 ){
		for (t = 0; t < ANGGARANPAMPASAN_MAIN_temp1_length; t++){	
    		document.${formName}.ANGGARANPAMPASAN_MAIN_temp1[0].value = document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN.value;
    	}
	}else if(ANGGARANPAMPASAN_MAIN_temp1_length == 1){
		document.${formName}.ANGGARANPAMPASAN_MAIN_temp1.value = document.${formName}.txtUlasanANGGARANPAMPASAN_MAIN.value;
	}
	
    
    //LAPORAN TANAH
 	var LAPORANTANAH_MAIN_temp1_length=0;
    
	if(document.${formName}.LAPORANTANAH_MAIN_temp1 != null){
		if(document.${formName}.LAPORANTANAH_MAIN_temp1.length>0){
			LAPORANTANAH_MAIN_temp1_length = document.${formName}.LAPORANTANAH_MAIN_temp1.length;
		}else{
			LAPORANTANAH_MAIN_temp1_length = 1;
		}
	}

    if(LAPORANTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTANAH_MAIN.length > 1 ){
		for (t = 0; t < LAPORANTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.LAPORANTANAH_MAIN_temp1[t].value = document.${formName}.txtUlasanLAPORANTANAH_MAIN[t].value;
		}
	}else if(LAPORANTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTANAH_MAIN.length < 1 ){
		for (t = 0; t < LAPORANTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.LAPORANTANAH_MAIN_temp1[0].value = document.${formName}.txtUlasanLAPORANTANAH_MAIN.value;
    	}
	}else if(LAPORANTANAH_MAIN_temp1_length == 1){
		document.${formName}.LAPORANTANAH_MAIN_temp1.value = document.${formName}.txtUlasanLAPORANTANAH_MAIN.value;
	}

    
  	//LAPORAN TEKNIKAL
 	var LAPORANTEKNIKAL_MAIN_temp1_length=0;
    
	if(document.${formName}.LAPORANTEKNIKAL_MAIN_temp1 != null){
		if(document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.length>0){
			LAPORANTEKNIKAL_MAIN_temp1_length = document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.length;
		}else{
			LAPORANTEKNIKAL_MAIN_temp1_length = 1;
		}
	}

    if(LAPORANTEKNIKAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length > 1 ){
		for (t = 0; t < LAPORANTEKNIKAL_MAIN_temp1_length; t++){	
    		document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[t].value = document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN[t].value;
		}
	}else if(LAPORANTEKNIKAL_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.length < 1 ){
		for (t = 0; t < LAPORANTEKNIKAL_MAIN_temp1_length; t++){	
    		document.${formName}.LAPORANTEKNIKAL_MAIN_temp1[0].value = document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.value;
    	}
	}else if(LAPORANTEKNIKAL_MAIN_temp1_length == 1){
		document.${formName}.LAPORANTEKNIKAL_MAIN_temp1.value = document.${formName}.txtUlasanLAPORANTEKNIKAL_MAIN.value;
	}

    
	//PANDANGANYB
 	var PANDANGANYB_MAIN_temp1_length=0;
    
	if(document.${formName}.PANDANGANYB_MAIN_temp1 != null){
		if(document.${formName}.PANDANGANYB_MAIN_temp1.length>0){
			PANDANGANYB_MAIN_temp1_length = document.${formName}.PANDANGANYB_MAIN_temp1.length;
		}else{
			PANDANGANYB_MAIN_temp1_length = 1;
		}
	}

    if(PANDANGANYB_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPANDANGANYB_MAIN.length > 1 ){
		for (t = 0; t < PANDANGANYB_MAIN_temp1_length; t++){	
    		document.${formName}.PANDANGANYB_MAIN_temp1[t].value = document.${formName}.txtUlasanPANDANGANYB_MAIN[t].value;
		}
	}else if(PANDANGANYB_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPANDANGANYB_MAIN.length < 1 ){
		for (t = 0; t < PANDANGANYB_MAIN_temp1_length; t++){	
    		document.${formName}.PANDANGANYB_MAIN_temp1[0].value = document.${formName}.txtUlasanPANDANGANYB_MAIN.value;
    	}
	}else if(PANDANGANYB_MAIN_temp1_length == 1){
		document.${formName}.PANDANGANYB_MAIN_temp1.value = document.${formName}.txtUlasanPANDANGANYB_MAIN.value;
	}

    
  	//PERAKUAN PT
 	var PERAKUANPT_MAIN_temp1_length=0;
    
	if(document.${formName}.PERAKUANPT_MAIN_temp1 != null){
		if(document.${formName}.PERAKUANPT_MAIN_temp1.length>0){
			PERAKUANPT_MAIN_temp1_length = document.${formName}.PERAKUANPT_MAIN_temp1.length;
		}else{
			PERAKUANPT_MAIN_temp1_length = 1;
		}
	}

    if(PERAKUANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT_MAIN.length > 1 ){
		for (t = 0; t < PERAKUANPT_MAIN_temp1_length; t++){	
    		document.${formName}.PERAKUANPT_MAIN_temp1[t].value = document.${formName}.txtUlasanPERAKUANPT_MAIN[t].value;
		}
	}else if(PERAKUANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT_MAIN.length < 1 ){
		for (t = 0; t < PERAKUANPT_MAIN_temp1_length; t++){	
    		document.${formName}.PERAKUANPT_MAIN_temp1[0].value = document.${formName}.txtUlasanPERAKUANPT_MAIN.value;
    	}
	}else if(PERAKUANPT_MAIN_temp1_length == 1){
		document.${formName}.PERAKUANPT_MAIN_temp1.value = document.${formName}.txtUlasanPERAKUANPT_MAIN.value;
	}

}

</script>
