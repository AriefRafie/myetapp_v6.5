
<fieldset>
<legend><strong>Penyediaan Kertas MMK/MB/KM/LC</strong></legend>
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
        	
        	<!-- LATAR BELAKANG PERMOHONAN -->	
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
        		
        	<!-- PERIHAL TANAH -->	
			<tr><td><b>3. PERIHAL TANAH :</b></td></tr>
			<tr>
				<td>
					
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
        	
        	
        	<!-- NILAIAN TANAH DAN PERUNTUKAN  -->	
			<tr><td><b>4. NILAIAN TANAH DAN PERUNTUKAN :</b></td></tr>
				
			<tr>
				<td>
					
				#if($senarai_submmk.size()!=0)
    				#foreach($list in $senarai_submmk)   
	    			#if($list.FLAG_JENIS_MMK == "NILAITANAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    			<input type="hidden" name="NILAITANAH_MAIN_temp1"  id="NILAITANAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    				#end
    				#end
    			#end 
    	
    	
    			<span id="NILAITANAH_MAIN"></span>           
   				<div id="NILAITANAH_MAIN_temp"></div>	
					
				</td>
			</tr>
			
        	<tr><td>&nbsp;</td></tr>
        		
        		
        	<!-- SYOR PENTADBIR TANAH  -->	
			<tr><td><b>5. SYOR PENTADBIR TANAH :</b></td></tr>
			<tr>
				<td>
					
				#if($senarai_submmk.size()!=0)
    				#foreach($list in $senarai_submmk)   
    				#if($list.FLAG_JENIS_MMK == "SYOR" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    				<input type="hidden" name="SYOR_MAIN_temp1"  id="SYOR_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    				#end
    				#end
    			#end 
    	
    	
    			<span id="SYOR_MAIN"></span>           
   				<div id="SYOR_MAIN_temp"></div>	
					
				</td>
			</tr>
				
			<tr><td>&nbsp;</td></tr>
				
			<!-- ULASAN PENGARAH NEGERI   -->	
			<tr><td><b>6. ULASAN PENGARAH NEGERI :</b></td></tr>
			<tr>
				<td>
					
				#if($senarai_submmk.size()!=0)
    				#foreach($list in $senarai_submmk)   
    				#if($list.FLAG_JENIS_MMK == "ULASANPENGARAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    				<input type="hidden" name="ULASANPENGARAH_MAIN_temp1"  id="ULASANPENGARAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    				#end
    				#end
    			#end 
    	
    	
    			<span id="ULASANPENGARAH_MAIN"></span>           
   				<div id="ULASANPENGARAH_MAIN_temp"></div>	
					
				</td>
			</tr>
				
			<tr><td>&nbsp;</td></tr>
			
			
			<!-- KEPUTUSAN    -->	
			<tr><td><b>7. KEPUTUSAN :</b></td></tr>
			<tr>
				<td>
					
				#if($senarai_submmk.size()!=0)
    				#foreach($list in $senarai_submmk)   
    				#if($list.FLAG_JENIS_MMK == "KEPUTUSAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    				<input type="hidden" name="KEPUTUSAN_MAIN_temp1"  id="KEPUTUSAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    				#end
    				#end
    			#end 
    	
    	
    			<span id="KEPUTUSAN_MAIN"></span>           
   				<div id="KEPUTUSAN_MAIN_temp"></div>	
					
				</td>
			</tr>
				
    	</table>
</fieldset>



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
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanTUJUAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanTUJUAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
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
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanTUJUAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanTUJUAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
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
	
}



//LATAR BELAKANG
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
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLATARBELAKANG_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLATARBELAKANG_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
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
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
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
	
}//CLOSE LATAR BELAKANG


//PERIHAL TANAH
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
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERIHALTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERIHALTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
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
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
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
	
}//CLOSE PERIHAL TANAH


//NILAI TANAH
function textarea_NILAITANAH_MAIN(tambahtolak,jenis,index_tolak){
	
	var NILAITANAH_MAIN_temp1_length=0;

	if(document.${formName}.NILAITANAH_MAIN_temp1 != null){

		if(document.${formName}.NILAITANAH_MAIN_temp1.length>0){
			NILAITANAH_MAIN_temp1_length = document.${formName}.NILAITANAH_MAIN_temp1.length;
		}else{
			NILAITANAH_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanNILAITANAH_MAIN!=null){

		if(document.${formName}.txtUlasanNILAITANAH_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanNILAITANAH_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='NILAITANAH_MAIN_tempX1' name='NILAITANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanNILAITANAH_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='NILAITANAH_MAIN_tempX1' name='NILAITANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanNILAITANAH_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#NILAITANAH_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(NILAITANAH_MAIN_temp1_length>0){
			ttNILAITANAH_MAIN = NILAITANAH_MAIN_temp1_length;
		}else{
			ttNILAITANAH_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttNILAITANAH_MAIN = ttNILAITANAH_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttNILAITANAH_MAIN = ttNILAITANAH_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttNILAITANAH_MAIN; i++){	

  		if(ttNILAITANAH_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanNILAITANAH_MAIN\" id=\"txtUlasanNILAITANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check','txtUlasanNILAITANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check','txtUlasanNILAITANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanNILAITANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanNILAITANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanNILAITANAH_MAIN\" id=\"txtUlasanNILAITANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check','txtUlasanNILAITANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check','txtUlasanNILAITANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanNILAITANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanNILAITANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_NILAITANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttNILAITANAH_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_NILAITANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttNILAITANAH_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.NILAITANAH_MAIN_tempX1.value

			}else if(ttNILAITANAH_MAIN>2 && i!=(ttNILAITANAH_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[i].value

			}else if(ttNILAITANAH_MAIN>1 && i!=(ttNILAITANAH_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[i+1].value	
				}	

			}else if(ttNILAITANAH_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanNILAITANAH_MAIN\" id=\"txtUlasanNILAITANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check"+i+"','txtUlasanNILAITANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check"+i+"','txtUlasanNILAITANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanNILAITANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanNILAITANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanNILAITANAH_MAIN\" id=\"txtUlasanNILAITANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check"+i+"','txtUlasanNILAITANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check"+i+"','txtUlasanNILAITANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanNILAITANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanNILAITANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttNILAITANAH_MAIN>1 && ttNILAITANAH_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_NILAITANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttNILAITANAH_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_NILAITANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#NILAITANAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(NILAITANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanNILAITANAH_MAIN.length > 1 ){

		for (t = 0; t < NILAITANAH_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanNILAITANAH_MAIN[t].value = document.${formName}.NILAITANAH_MAIN_temp1[t].value;
		}

		}else if(NILAITANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanNILAITANAH_MAIN.length < 1 ){
		
			for (t = 0; t < NILAITANAH_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanNILAITANAH_MAIN.value = document.${formName}.NILAITANAH_MAIN_temp1[0].value;
			}
		}
		
		else if(NILAITANAH_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanNILAITANAH_MAIN.value = document.${formName}.NILAITANAH_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(NILAITANAH_MAIN_temp1_length > 1){

			for (t = 0; t < NILAITANAH_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.NILAITANAH_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.NILAITANAH_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(NILAITANAH_MAIN_temp1_length == 1){
			
	 		document.${formName}.NILAITANAH_MAIN_temp1.value = "";			
	 		var element = document.${formName}.NILAITANAH_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttNILAITANAH_MAIN; i++){		

		    if(ttNILAITANAH_MAIN==1){
		 check_length(document.${formName}.txtUlasanNILAITANAH_MAIN,'30000','txtUlasanNILAITANAH_MAIN_check','txtUlasanNILAITANAH_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanNILAITANAH_MAIN[i],'30000','txtUlasanNILAITANAH_MAIN_check'+i,'txtUlasanNILAITANAH_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE NILAI TANAH


//SYOR
function textarea_SYOR_MAIN(tambahtolak,jenis,index_tolak){
	
	var SYOR_MAIN_temp1_length=0;

	if(document.${formName}.SYOR_MAIN_temp1 != null){

		if(document.${formName}.SYOR_MAIN_temp1.length>0){
			SYOR_MAIN_temp1_length = document.${formName}.SYOR_MAIN_temp1.length;
		}else{
			SYOR_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanSYOR_MAIN!=null){

		if(document.${formName}.txtUlasanSYOR_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanSYOR_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='SYOR_MAIN_tempX1' name='SYOR_MAIN_tempX1' value= '"+document.${formName}.txtUlasanSYOR_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='SYOR_MAIN_tempX1' name='SYOR_MAIN_tempX1' value= '"+document.${formName}.txtUlasanSYOR_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#SYOR_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(SYOR_MAIN_temp1_length>0){
			ttSYOR_MAIN = SYOR_MAIN_temp1_length;
		}else{
			ttSYOR_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttSYOR_MAIN = ttSYOR_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttSYOR_MAIN = ttSYOR_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttSYOR_MAIN; i++){	

  		if(ttSYOR_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.SYOR_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.SYOR_MAIN_tempX1[0].value
				} 	
			}		

			
   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanSYOR_MAIN\" id=\"txtUlasanSYOR_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check','txtUlasanSYOR_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check','txtUlasanSYOR_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanSYOR_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanSYOR_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanSYOR_MAIN\" id=\"txtUlasanSYOR_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check','txtUlasanSYOR_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check','txtUlasanSYOR_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanSYOR_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanSYOR_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_SYOR_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttSYOR_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_SYOR_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttSYOR_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.SYOR_MAIN_tempX1.value

			}else if(ttSYOR_MAIN>2 && i!=(ttSYOR_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.SYOR_MAIN_tempX1[i].value

			}else if(ttSYOR_MAIN>1 && i!=(ttSYOR_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.SYOR_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.SYOR_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.SYOR_MAIN_tempX1[i+1].value	
				}	

			}else if(ttSYOR_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.SYOR_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.SYOR_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 5."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanSYOR_MAIN\" id=\"txtUlasanSYOR_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check"+i+"','txtUlasanSYOR_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check"+i+"','txtUlasanSYOR_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanSYOR_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanSYOR_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanSYOR_MAIN\" id=\"txtUlasanSYOR_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check"+i+"','txtUlasanSYOR_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check"+i+"','txtUlasanSYOR_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanSYOR_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanSYOR_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttSYOR_MAIN>1 && ttSYOR_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_SYOR_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttSYOR_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_SYOR_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#SYOR_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(SYOR_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYOR_MAIN.length > 1 ){

		for (t = 0; t < SYOR_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanSYOR_MAIN[t].value = document.${formName}.SYOR_MAIN_temp1[t].value;
		}

		}else if(SYOR_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYOR_MAIN.length < 1 ){
		
			for (t = 0; t < SYOR_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanSYOR_MAIN.value = document.${formName}.SYOR_MAIN_temp1[0].value;
			}
		}
		
		else if(SYOR_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanSYOR_MAIN.value = document.${formName}.SYOR_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(SYOR_MAIN_temp1_length > 1){

			for (t = 0; t < SYOR_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.SYOR_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.SYOR_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(SYOR_MAIN_temp1_length == 1){
			
	 		document.${formName}.SYOR_MAIN_temp1.value = "";			
	 		var element = document.${formName}.SYOR_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttSYOR_MAIN; i++){		

		    if(ttSYOR_MAIN==1){
		 check_length(document.${formName}.txtUlasanSYOR_MAIN,'30000','txtUlasanSYOR_MAIN_check','txtUlasanSYOR_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanSYOR_MAIN[i],'30000','txtUlasanSYOR_MAIN_check'+i,'txtUlasanSYOR_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE SYOR

//ULASANPENGARAH
function textarea_ULASANPENGARAH_MAIN(tambahtolak,jenis,index_tolak){
	
	var ULASANPENGARAH_MAIN_temp1_length=0;

	if(document.${formName}.ULASANPENGARAH_MAIN_temp1 != null){

		if(document.${formName}.ULASANPENGARAH_MAIN_temp1.length>0){
			ULASANPENGARAH_MAIN_temp1_length = document.${formName}.ULASANPENGARAH_MAIN_temp1.length;
		}else{
			ULASANPENGARAH_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanULASANPENGARAH_MAIN!=null){

		if(document.${formName}.txtUlasanULASANPENGARAH_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanULASANPENGARAH_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH_MAIN_tempX1' name='ULASANPENGARAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH_MAIN_tempX1' name='ULASANPENGARAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ULASANPENGARAH_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ULASANPENGARAH_MAIN_temp1_length>0){
			ttULASANPENGARAH_MAIN = ULASANPENGARAH_MAIN_temp1_length;
		}else{
			ttULASANPENGARAH_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttULASANPENGARAH_MAIN = ttULASANPENGARAH_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttULASANPENGARAH_MAIN = ttULASANPENGARAH_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttULASANPENGARAH_MAIN; i++){	

  		if(ttULASANPENGARAH_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[0].value
				} 	
			}		

			
   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanULASANPENGARAH_MAIN\" id=\"txtUlasanULASANPENGARAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check','txtUlasanULASANPENGARAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check','txtUlasanULASANPENGARAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanULASANPENGARAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH_MAIN\" id=\"txtUlasanULASANPENGARAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check','txtUlasanULASANPENGARAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check','txtUlasanULASANPENGARAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanULASANPENGARAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ULASANPENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttULASANPENGARAH_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ULASANPENGARAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttULASANPENGARAH_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1.value

			}else if(ttULASANPENGARAH_MAIN>2 && i!=(ttULASANPENGARAH_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[i].value

			}else if(ttULASANPENGARAH_MAIN>1 && i!=(ttULASANPENGARAH_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[i+1].value	
				}	

			}else if(ttULASANPENGARAH_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 6."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanULASANPENGARAH_MAIN\" id=\"txtUlasanULASANPENGARAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check"+i+"','txtUlasanULASANPENGARAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check"+i+"','txtUlasanULASANPENGARAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH_MAIN\" id=\"txtUlasanULASANPENGARAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check"+i+"','txtUlasanULASANPENGARAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check"+i+"','txtUlasanULASANPENGARAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttULASANPENGARAH_MAIN>1 && ttULASANPENGARAH_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ULASANPENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttULASANPENGARAH_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ULASANPENGARAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ULASANPENGARAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ULASANPENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH_MAIN.length > 1 ){

		for (t = 0; t < ULASANPENGARAH_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanULASANPENGARAH_MAIN[t].value = document.${formName}.ULASANPENGARAH_MAIN_temp1[t].value;
		}

		}else if(ULASANPENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH_MAIN.length < 1 ){
		
			for (t = 0; t < ULASANPENGARAH_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanULASANPENGARAH_MAIN.value = document.${formName}.ULASANPENGARAH_MAIN_temp1[0].value;
			}
		}
		
		else if(ULASANPENGARAH_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanULASANPENGARAH_MAIN.value = document.${formName}.ULASANPENGARAH_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ULASANPENGARAH_MAIN_temp1_length > 1){

			for (t = 0; t < ULASANPENGARAH_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ULASANPENGARAH_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ULASANPENGARAH_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ULASANPENGARAH_MAIN_temp1_length == 1){
			
	 		document.${formName}.ULASANPENGARAH_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ULASANPENGARAH_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttULASANPENGARAH_MAIN; i++){		

		    if(ttULASANPENGARAH_MAIN==1){
		 check_length(document.${formName}.txtUlasanULASANPENGARAH_MAIN,'30000','txtUlasanULASANPENGARAH_MAIN_check','txtUlasanULASANPENGARAH_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanULASANPENGARAH_MAIN[i],'30000','txtUlasanULASANPENGARAH_MAIN_check'+i,'txtUlasanULASANPENGARAH_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE ULASANPENGARAH

//KEPUTUSAN
function textarea_KEPUTUSAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var KEPUTUSAN_MAIN_temp1_length=0;

	if(document.${formName}.KEPUTUSAN_MAIN_temp1 != null){

		if(document.${formName}.KEPUTUSAN_MAIN_temp1.length>0){
			KEPUTUSAN_MAIN_temp1_length = document.${formName}.KEPUTUSAN_MAIN_temp1.length;
		}else{
			KEPUTUSAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanKEPUTUSAN_MAIN!=null){

		if(document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanKEPUTUSAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='KEPUTUSAN_MAIN_tempX1' name='KEPUTUSAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEPUTUSAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='KEPUTUSAN_MAIN_tempX1' name='KEPUTUSAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEPUTUSAN_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#KEPUTUSAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(KEPUTUSAN_MAIN_temp1_length>0){
			ttKEPUTUSAN_MAIN = KEPUTUSAN_MAIN_temp1_length;
		}else{
			ttKEPUTUSAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttKEPUTUSAN_MAIN = ttKEPUTUSAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttKEPUTUSAN_MAIN = ttKEPUTUSAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttKEPUTUSAN_MAIN; i++){	

  		if(ttKEPUTUSAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[0].value
				} 	
			}		

			
   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanKEPUTUSAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanKEPUTUSAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttKEPUTUSAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttKEPUTUSAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1.value

			}else if(ttKEPUTUSAN_MAIN>2 && i!=(ttKEPUTUSAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i].value

			}else if(ttKEPUTUSAN_MAIN>1 && i!=(ttKEPUTUSAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttKEPUTUSAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 7."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKEPUTUSAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKEPUTUSAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttKEPUTUSAN_MAIN>1 && ttKEPUTUSAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttKEPUTUSAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#KEPUTUSAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1 ){

		for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanKEPUTUSAN_MAIN[t].value = document.${formName}.KEPUTUSAN_MAIN_temp1[t].value;
		}

		}else if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length < 1 ){
		
			for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanKEPUTUSAN_MAIN.value = document.${formName}.KEPUTUSAN_MAIN_temp1[0].value;
			}
		}
		
		else if(KEPUTUSAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanKEPUTUSAN_MAIN.value = document.${formName}.KEPUTUSAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(KEPUTUSAN_MAIN_temp1_length > 1){

			for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.KEPUTUSAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.KEPUTUSAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(KEPUTUSAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.KEPUTUSAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.KEPUTUSAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttKEPUTUSAN_MAIN; i++){		

		    if(ttKEPUTUSAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanKEPUTUSAN_MAIN,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanKEPUTUSAN_MAIN[i],'30000','txtUlasanKEPUTUSAN_MAIN_check'+i,'txtUlasanKEPUTUSAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE KEPUTUSAN


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

    
  	//PERIHAL TANAH
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


	//SYOR
 	var SYOR_MAIN_temp1_length=0;
    
	if(document.${formName}.SYOR_MAIN_temp1 != null){
		if(document.${formName}.SYOR_MAIN_temp1.length>0){
			SYOR_MAIN_temp1_length = document.${formName}.SYOR_MAIN_temp1.length;
		}else{
			SYOR_MAIN_temp1_length = 1;
		}
	}

    if(SYOR_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYOR_MAIN.length > 1 ){
		for (t = 0; t < SYOR_MAIN_temp1_length; t++){	
    		document.${formName}.SYOR_MAIN_temp1[t].value = document.${formName}.txtUlasanSYOR_MAIN[t].value;
		}
	}else if(SYOR_MAIN_temp1_length > 1 && document.${formName}.txtUlasanSYOR_MAIN.length < 1 ){
		for (t = 0; t < SYOR_MAIN_temp1_length; t++){	
    		document.${formName}.SYOR_MAIN_temp1[0].value = document.${formName}.txtUlasanSYOR_MAIN.value;
    	}
	}else if(SYOR_MAIN_temp1_length == 1){
		document.${formName}.SYOR_MAIN_temp1.value = document.${formName}.txtUlasanSYOR_MAIN.value;
	}



 	//NILAI TANAH
 	var NILAITANAH_MAIN_temp1_length=0;
    
	if(document.${formName}.NILAITANAH_MAIN_temp1 != null){
		if(document.${formName}.NILAITANAH_MAIN_temp1.length>0){
			NILAITANAH_MAIN_temp1_length = document.${formName}.NILAITANAH_MAIN_temp1.length;
		}else{
			NILAITANAH_MAIN_temp1_length = 1;
		}
	}

    if(NILAITANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanNILAITANAH_MAIN.length > 1 ){
		for (t = 0; t < NILAITANAH_MAIN_temp1_length; t++){	
    		document.${formName}.NILAITANAH_MAIN_temp1[t].value = document.${formName}.txtUlasanNILAITANAH_MAIN[t].value;
		}
	}else if(NILAITANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanNILAITANAH_MAIN.length < 1 ){
		for (t = 0; t < NILAITANAH_MAIN_temp1_length; t++){	
    		document.${formName}.NILAITANAH_MAIN_temp1[0].value = document.${formName}.txtUlasanNILAITANAH_MAIN.value;
    	}
	}else if(NILAITANAH_MAIN_temp1_length == 1){
		document.${formName}.NILAITANAH_MAIN_temp1.value = document.${formName}.txtUlasanNILAITANAH_MAIN.value;
	}


  	//ULASAN PENGARAH
 	var ULASANPENGARAH_MAIN_temp1_length=0;
    
	if(document.${formName}.ULASANPENGARAH_MAIN_temp1 != null){
		if(document.${formName}.ULASANPENGARAH_MAIN_temp1.length>0){
			ULASANPENGARAH_MAIN_temp1_length = document.${formName}.ULASANPENGARAH_MAIN_temp1.length;
		}else{
			ULASANPENGARAH_MAIN_temp1_length = 1;
		}
	}

    if(ULASANPENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH_MAIN.length > 1 ){
		for (t = 0; t < ULASANPENGARAH_MAIN_temp1_length; t++){	
    		document.${formName}.ULASANPENGARAH_MAIN_temp1[t].value = document.${formName}.txtUlasanULASANPENGARAH_MAIN[t].value;
		}
	}else if(ULASANPENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH_MAIN.length < 1 ){
		for (t = 0; t < ULASANPENGARAH_MAIN_temp1_length; t++){	
    		document.${formName}.ULASANPENGARAH_MAIN_temp1[0].value = document.${formName}.txtUlasanULASANPENGARAH_MAIN.value;
    	}
	}else if(ULASANPENGARAH_MAIN_temp1_length == 1){
		document.${formName}.ULASANPENGARAH_MAIN_temp1.value = document.${formName}.txtUlasanULASANPENGARAH_MAIN.value;
	}

    
    //KEPUTUSAN
 	var KEPUTUSAN_MAIN_temp1_length=0;
    
	if(document.${formName}.KEPUTUSAN_MAIN_temp1 != null){
		if(document.${formName}.KEPUTUSAN_MAIN_temp1.length>0){
			KEPUTUSAN_MAIN_temp1_length = document.${formName}.KEPUTUSAN_MAIN_temp1.length;
		}else{
			KEPUTUSAN_MAIN_temp1_length = 1;
		}
	}

    if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1 ){
		for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++){	
    		document.${formName}.KEPUTUSAN_MAIN_temp1[t].value = document.${formName}.txtUlasanKEPUTUSAN_MAIN[t].value;
		}
	}else if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length < 1 ){
		for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++){	
    		document.${formName}.KEPUTUSAN_MAIN_temp1[0].value = document.${formName}.txtUlasanKEPUTUSAN_MAIN.value;
    	}
	}else if(KEPUTUSAN_MAIN_temp1_length == 1){
		document.${formName}.KEPUTUSAN_MAIN_temp1.value = document.${formName}.txtUlasanKEPUTUSAN_MAIN.value;
	}
	
}

</script>