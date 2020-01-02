<table width="100%" border="0">

	<!-- TUJUAN -->	
	<tr><td><b>1. TUJUAN :</b></td></tr>
	<tr>
		<td>

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
	<tr><td><b>2. LATAR BELAKANG :</b></td></tr>
	
	<tr>
		<td>
	
		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "LATARBELAKANG" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="LATARBELAKANG_MAIN_temp1"  id="LATARBELAKANG_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="LATARBELAKANG_MAIN"></span>           
   		<div id="LATARBELAKANG_MAIN_temp"></div>
	
		</td>
	</tr>

	<tr><td>&nbsp;</td></tr>
	
	<!-- LAPORAN TANAH -->
	<tr><td><b>3. LAPORAN ATAS TANAH :</b></td></tr>
	
	<tr>	
		<td>
	
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
			
	<!-- PENGESAHAN PERUNTUKAN  -->
	<tr><td><b>4. PENGESAHAN PERUNTUKAN :</b></td></tr>
	
	<tr>	
		<td>
		
		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "WANGPERUNTUKAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="WANGPERUNTUKAN_MAIN_temp1"  id="WANGPERUNTUKAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="WANGPERUNTUKAN_MAIN"></span>           
   		<div id="WANGPERUNTUKAN_MAIN_temp"></div>	
		
		</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<!-- ULASAN JABATAN TEKNIKAL   -->
	<tr><td><b>5. ULASAN JABATAN TEKNIKAL :</b></td></tr>
	
	<tr>	
		<td>
		
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
	
	<!-- PERAKUAN PENTADBIR TANAH    -->
	<tr><td><b>6. PERAKUAN PENTADBIR TANAH :</b></td></tr>
	
	<tr>	
		<td>
		
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
	
	<tr><td>&nbsp;</td></tr>
	
	<!-- KELULUSAN DIPOHON     -->
	<tr><td><b>7. KELULUSAN DIPOHON :</b></td></tr>
	
	<tr>	
		<td>
		
		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "KELULUSAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="KELULUSAN_MAIN_temp1"  id="KELULUSAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="KELULUSAN_MAIN"></span>           
   		<div id="KELULUSAN_MAIN_temp"></div>	
		
		</td>
	</tr>
	
</table>	



<script>
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

//LATARBELAKANG
function textarea_LATARBELAKANG_MAIN(tambahtolak,jenis,index_tolak){
	
	var LATARBELAKANG_MAIN_temp1_length=0;

	if(document.${formName}.LATARBELAKANG_MAIN_temp1 != null){

		if(document.${formName}.LATARBELAKANG_MAIN_temp1.length>0){
			LATARBELAKANG_MAIN_temp1_length = document.${formName}.LATARBELAKANG_MAIN_temp1.length;
		}else{
			LATARBELAKANG_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanLATARBELAKANG_MAIN!=null){

		if(document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanLATARBELAKANG_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG_MAIN_tempX1' name='LATARBELAKANG_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG_MAIN_tempX1' name='LATARBELAKANG_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#LATARBELAKANG_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(LATARBELAKANG_MAIN_temp1_length>0){
			ttLATARBELAKANG_MAIN = LATARBELAKANG_MAIN_temp1_length;
		}else{
			ttLATARBELAKANG_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttLATARBELAKANG_MAIN = ttLATARBELAKANG_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttLATARBELAKANG_MAIN = ttLATARBELAKANG_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttLATARBELAKANG_MAIN; i++){	

  		if(ttLATARBELAKANG_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLATARBELAKANG_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLATARBELAKANG_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttLATARBELAKANG_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttLATARBELAKANG_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1.value

			}else if(ttLATARBELAKANG_MAIN>2 && i!=(ttLATARBELAKANG_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i].value

			}else if(ttLATARBELAKANG_MAIN>1 && i!=(ttLATARBELAKANG_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i+1].value	
				}	

			}else if(ttLATARBELAKANG_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttLATARBELAKANG_MAIN>1 && ttLATARBELAKANG_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttLATARBELAKANG_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#LATARBELAKANG_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1 ){

		for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanLATARBELAKANG_MAIN[t].value = document.${formName}.LATARBELAKANG_MAIN_temp1[t].value;
		}

		}else if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length < 1 ){
		
			for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanLATARBELAKANG_MAIN.value = document.${formName}.LATARBELAKANG_MAIN_temp1[0].value;
			}
		}
		
		else if(LATARBELAKANG_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanLATARBELAKANG_MAIN.value = document.${formName}.LATARBELAKANG_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(LATARBELAKANG_MAIN_temp1_length > 1){

			for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.LATARBELAKANG_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.LATARBELAKANG_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(LATARBELAKANG_MAIN_temp1_length == 1){
			
	 		document.${formName}.LATARBELAKANG_MAIN_temp1.value = "";			
	 		var element = document.${formName}.LATARBELAKANG_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttLATARBELAKANG_MAIN; i++){		

		    if(ttLATARBELAKANG_MAIN==1){
		 check_length(document.${formName}.txtUlasanLATARBELAKANG_MAIN,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanLATARBELAKANG_MAIN[i],'30000','txtUlasanLATARBELAKANG_MAIN_check'+i,'txtUlasanLATARBELAKANG_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE LATARBELAKANG

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
	
}//CLOSE LAPORANTANAH


//WANGPERUNTUKAN
function textarea_WANGPERUNTUKAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var WANGPERUNTUKAN_MAIN_temp1_length=0;

	if(document.${formName}.WANGPERUNTUKAN_MAIN_temp1 != null){

		if(document.${formName}.WANGPERUNTUKAN_MAIN_temp1.length>0){
			WANGPERUNTUKAN_MAIN_temp1_length = document.${formName}.WANGPERUNTUKAN_MAIN_temp1.length;
		}else{
			WANGPERUNTUKAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN!=null){

		if(document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='WANGPERUNTUKAN_MAIN_tempX1' name='WANGPERUNTUKAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='WANGPERUNTUKAN_MAIN_tempX1' name='WANGPERUNTUKAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#WANGPERUNTUKAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(WANGPERUNTUKAN_MAIN_temp1_length>0){
			ttWANGPERUNTUKAN_MAIN = WANGPERUNTUKAN_MAIN_temp1_length;
		}else{
			ttWANGPERUNTUKAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttWANGPERUNTUKAN_MAIN = ttWANGPERUNTUKAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttWANGPERUNTUKAN_MAIN = ttWANGPERUNTUKAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttWANGPERUNTUKAN_MAIN; i++){	

  		if(ttWANGPERUNTUKAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanWANGPERUNTUKAN_MAIN\" id=\"txtUlasanWANGPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check','txtUlasanWANGPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check','txtUlasanWANGPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanWANGPERUNTUKAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanWANGPERUNTUKAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanWANGPERUNTUKAN_MAIN\" id=\"txtUlasanWANGPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check','txtUlasanWANGPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check','txtUlasanWANGPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanWANGPERUNTUKAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanWANGPERUNTUKAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_WANGPERUNTUKAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttWANGPERUNTUKAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_WANGPERUNTUKAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttWANGPERUNTUKAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1.value

			}else if(ttWANGPERUNTUKAN_MAIN>2 && i!=(ttWANGPERUNTUKAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[i].value

			}else if(ttWANGPERUNTUKAN_MAIN>1 && i!=(ttWANGPERUNTUKAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttWANGPERUNTUKAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanWANGPERUNTUKAN_MAIN\" id=\"txtUlasanWANGPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"','txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"','txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanWANGPERUNTUKAN_MAIN\" id=\"txtUlasanWANGPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"','txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"','txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttWANGPERUNTUKAN_MAIN>1 && ttWANGPERUNTUKAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_WANGPERUNTUKAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttWANGPERUNTUKAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_WANGPERUNTUKAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#WANGPERUNTUKAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(WANGPERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length > 1 ){

		for (t = 0; t < WANGPERUNTUKAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN[t].value = document.${formName}.WANGPERUNTUKAN_MAIN_temp1[t].value;
		}

		}else if(WANGPERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length < 1 ){
		
			for (t = 0; t < WANGPERUNTUKAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.value = document.${formName}.WANGPERUNTUKAN_MAIN_temp1[0].value;
			}
		}
		
		else if(WANGPERUNTUKAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.value = document.${formName}.WANGPERUNTUKAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(WANGPERUNTUKAN_MAIN_temp1_length > 1){

			for (t = 0; t < WANGPERUNTUKAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.WANGPERUNTUKAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.WANGPERUNTUKAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(WANGPERUNTUKAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.WANGPERUNTUKAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.WANGPERUNTUKAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttWANGPERUNTUKAN_MAIN; i++){		

		    if(ttWANGPERUNTUKAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check','txtUlasanWANGPERUNTUKAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN[i],'30000','txtUlasanWANGPERUNTUKAN_MAIN_check'+i,'txtUlasanWANGPERUNTUKAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE WANGPERUNTUKAN


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
				  		"		<td width='3%' valign='top'> 5."+(i+1)+"  </td> 																		"+
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
	
}//CLOSE LAPORANTEKNIKAL

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
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERAKUANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
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
				  		"		<td width='3%' valign='top'> 6."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
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
	
}//CLOSE PERAKUANPT


//KELULUSAN
function textarea_KELULUSAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var KELULUSAN_MAIN_temp1_length=0;

	if(document.${formName}.KELULUSAN_MAIN_temp1 != null){

		if(document.${formName}.KELULUSAN_MAIN_temp1.length>0){
			KELULUSAN_MAIN_temp1_length = document.${formName}.KELULUSAN_MAIN_temp1.length;
		}else{
			KELULUSAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanKELULUSAN_MAIN!=null){

		if(document.${formName}.txtUlasanKELULUSAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanKELULUSAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='KELULUSAN_MAIN_tempX1' name='KELULUSAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKELULUSAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='KELULUSAN_MAIN_tempX1' name='KELULUSAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKELULUSAN_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#KELULUSAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(KELULUSAN_MAIN_temp1_length>0){
			ttKELULUSAN_MAIN = KELULUSAN_MAIN_temp1_length;
		}else{
			ttKELULUSAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttKELULUSAN_MAIN = ttKELULUSAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttKELULUSAN_MAIN = ttKELULUSAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttKELULUSAN_MAIN; i++){	

  		if(ttKELULUSAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KELULUSAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KELULUSAN_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanKELULUSAN_MAIN\" id=\"txtUlasanKELULUSAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKELULUSAN_MAIN_check','txtUlasanKELULUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKELULUSAN_MAIN_check','txtUlasanKELULUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKELULUSAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanKELULUSAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanKELULUSAN_MAIN\" id=\"txtUlasanKELULUSAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKELULUSAN_MAIN_check','txtUlasanKELULUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKELULUSAN_MAIN_check','txtUlasanKELULUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKELULUSAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanKELULUSAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_KELULUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttKELULUSAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_KELULUSAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttKELULUSAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.KELULUSAN_MAIN_tempX1.value

			}else if(ttKELULUSAN_MAIN>2 && i!=(ttKELULUSAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.KELULUSAN_MAIN_tempX1[i].value

			}else if(ttKELULUSAN_MAIN>1 && i!=(ttKELULUSAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.KELULUSAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.KELULUSAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.KELULUSAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttKELULUSAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KELULUSAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KELULUSAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 7."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanKELULUSAN_MAIN\" id=\"txtUlasanKELULUSAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKELULUSAN_MAIN_check"+i+"','txtUlasanKELULUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKELULUSAN_MAIN_check"+i+"','txtUlasanKELULUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKELULUSAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKELULUSAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanKELULUSAN_MAIN\" id=\"txtUlasanKELULUSAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKELULUSAN_MAIN_check"+i+"','txtUlasanKELULUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKELULUSAN_MAIN_check"+i+"','txtUlasanKELULUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKELULUSAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKELULUSAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttKELULUSAN_MAIN>1 && ttKELULUSAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_KELULUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttKELULUSAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_KELULUSAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#KELULUSAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(KELULUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKELULUSAN_MAIN.length > 1 ){

		for (t = 0; t < KELULUSAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanKELULUSAN_MAIN[t].value = document.${formName}.KELULUSAN_MAIN_temp1[t].value;
		}

		}else if(KELULUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKELULUSAN_MAIN.length < 1 ){
		
			for (t = 0; t < KELULUSAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanKELULUSAN_MAIN.value = document.${formName}.KELULUSAN_MAIN_temp1[0].value;
			}
		}
		
		else if(KELULUSAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanKELULUSAN_MAIN.value = document.${formName}.KELULUSAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(KELULUSAN_MAIN_temp1_length > 1){

			for (t = 0; t < KELULUSAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.KELULUSAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.KELULUSAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(KELULUSAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.KELULUSAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.KELULUSAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttKELULUSAN_MAIN; i++){		

		    if(ttKELULUSAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanKELULUSAN_MAIN,'30000','txtUlasanKELULUSAN_MAIN_check','txtUlasanKELULUSAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanKELULUSAN_MAIN[i],'30000','txtUlasanKELULUSAN_MAIN_check'+i,'txtUlasanKELULUSAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE KELULUSAN


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


    //LATAR BELAKANG
 	var LATARBELAKANG_MAIN_temp1_length=0;
    
	if(document.${formName}.LATARBELAKANG_MAIN_temp1 != null){
		if(document.${formName}.LATARBELAKANG_MAIN_temp1.length>0){
			LATARBELAKANG_MAIN_temp1_length = document.${formName}.LATARBELAKANG_MAIN_temp1.length;
		}else{
			LATARBELAKANG_MAIN_temp1_length = 1;
		}
	}

    if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1 ){
		for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++){	
    		document.${formName}.LATARBELAKANG_MAIN_temp1[t].value = document.${formName}.txtUlasanLATARBELAKANG_MAIN[t].value;
		}
	}else if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length < 1 ){
		for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++){	
    		document.${formName}.LATARBELAKANG_MAIN_temp1[0].value = document.${formName}.txtUlasanLATARBELAKANG_MAIN.value;
    	}
	}else if(LATARBELAKANG_MAIN_temp1_length == 1){
		document.${formName}.LATARBELAKANG_MAIN_temp1.value = document.${formName}.txtUlasanLATARBELAKANG_MAIN.value;
	}


    //LAPORANTANAH
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


    //WANGPERUNTUKAN
	var WANGPERUNTUKAN_MAIN_temp1_length=0;
    
	if(document.${formName}.WANGPERUNTUKAN_MAIN_temp1 != null){
		if(document.${formName}.WANGPERUNTUKAN_MAIN_temp1.length>0){
			WANGPERUNTUKAN_MAIN_temp1_length = document.${formName}.WANGPERUNTUKAN_MAIN_temp1.length;
		}else{
			WANGPERUNTUKAN_MAIN_temp1_length = 1;
		}
	}

    if(WANGPERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length > 1 ){
		for (t = 0; t < WANGPERUNTUKAN_MAIN_temp1_length; t++){	
    		document.${formName}.WANGPERUNTUKAN_MAIN_temp1[t].value = document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN[t].value;
		}
	}else if(WANGPERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length < 1 ){
		for (t = 0; t < WANGPERUNTUKAN_MAIN_temp1_length; t++){	
    		document.${formName}.WANGPERUNTUKAN_MAIN_temp1[0].value = document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.value;
    	}
	}else if(WANGPERUNTUKAN_MAIN_temp1_length == 1){
		document.${formName}.WANGPERUNTUKAN_MAIN_temp1.value = document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.value;
	}


    //LAPORANTEKNIKAL
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

    
    //PERAKUANPT
	var PERAKUANPT_MAIN_temp1_length=0;
    
	if(document.${formName}.PERAKUANPT_MAIN_temp1 != null){
		if(document.${formName}.PERAKUANPT_MAIN_temp1.length>0){
			PERAKUANPT_MAIN_temp1_length = document.${formName}.PERAKUANPT_MAIN_temp1.length;
		}else{
			PERAKUANPT_MAIN_temp1_length = 1;
		}
	}

    if(PERAKUANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT_MAIN.length > 1 ){
		for (t = 0; t <PERAKUANPT_MAIN_temp1_length; t++){	
    		document.${formName}.PERAKUANPT_MAIN_temp1[t].value = document.${formName}.txtUlasanPERAKUANPT_MAIN[t].value;
		}
	}else if(PERAKUANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT_MAIN.length < 1 ){
		for (t = 0; t <PERAKUANPT_MAIN_temp1_length; t++){	
    		document.${formName}.PERAKUANPT_MAIN_temp1[0].value = document.${formName}.txtUlasanPERAKUANPT_MAIN.value;
    	}
	}else if(PERAKUANPT_MAIN_temp1_length == 1){
		document.${formName}.PERAKUANPT_MAIN_temp1.value = document.${formName}.txtUlasanPERAKUANPT_MAIN.value;
	}

    //KELULUSAN
	var KELULUSAN_MAIN_temp1_length=0;
    
	if(document.${formName}.KELULUSAN_MAIN_temp1 != null){
		if(document.${formName}.KELULUSAN_MAIN_temp1.length>0){
			KELULUSAN_MAIN_temp1_length = document.${formName}.KELULUSAN_MAIN_temp1.length;
		}else{
			KELULUSAN_MAIN_temp1_length = 1;
		}
	}

    if(KELULUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKELULUSAN_MAIN.length > 1 ){
		for (t = 0; t <KELULUSAN_MAIN_temp1_length; t++){	
    		document.${formName}.KELULUSAN_MAIN_temp1[t].value = document.${formName}.txtUlasanKELULUSAN_MAIN[t].value;
		}
	}else if(KELULUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKELULUSAN_MAIN.length < 1 ){
		for (t = 0; t <KELULUSAN_MAIN_temp1_length; t++){	
    		document.${formName}.KELULUSAN_MAIN_temp1[0].value = document.${formName}.txtUlasanKELULUSAN_MAIN.value;
    	}
	}else if(KELULUSAN_MAIN_temp1_length == 1){
		document.${formName}.KELULUSAN_MAIN_temp1.value = document.${formName}.txtUlasanKELULUSAN_MAIN.value;
	}
}

</script>