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
        	
        		<!-- PERIHAL TANAH -->	
				<tr><td><b>2. PERIHAL TANAH :</b></td></tr>
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
        		
        		<!-- LAPORAN TANAH -->	
				<tr><td><b>3. PERIHAL PERMOHONAN :</b></td></tr>
				<tr>
					<td>
					
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
        	
        		<!-- IMPLIKASI  -->	
				<tr><td><b>4. ANGGARAN KASAR KOS PENGAMBILAN :</b></td></tr>
				
				<tr>
					<td>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "ANGGARANKOS" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="ANGGARANKOS_MAIN_temp1"  id="ANGGARANKOS_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="ANGGARANKOS_MAIN"></span>           
   					<div id="ANGGARANKOS_MAIN_temp"></div>	
					
					</td>
				</tr>
			
        		<tr><td>&nbsp;</td></tr>
        		
        		<!-- ULASAN JABATAN TEKNIKAL  -->	
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
        		
        		<!-- ULASAN PENTADBIR TANAH DAERAH  -->	
				<tr><td><b>6. ULASAN PENTADBIR TANAH DAERAH  :</b></td></tr>
				<tr>
					<td>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "ULASANPT" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="ULASANPT_MAIN_temp1"  id="ULASANPT_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="ULASANPT_MAIN"></span>           
   					<div id="ULASANPT_MAIN_temp"></div>	
					
					</td>
				</tr>
				
        		<tr><td>&nbsp;</td></tr>
        		
        		<!-- PERAKUAN PENTADBIR TANAH DAERAH -->	
				<tr><td><b>7. PERAKUAN PENTADBIR TANAH DAERAH :</b></td></tr>
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
        		
        		<!-- JAWATANKUASA TANAH NEGERI -->	
				<tr><td><b>8. JAWATANKUASA TANAH NEGERI :</b></td></tr>
				<tr>
					<td>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "JAWATANKUASANEGERI" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="JAWATANKUASANEGERI_MAIN_temp1"  id="JAWATANKUASANEGERI_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="JAWATANKUASANEGERI_MAIN"></span>           
   					<div id="JAWATANKUASANEGERI_MAIN_temp"></div>	
					
					</td>
				</tr>
				
				<tr><td>&nbsp;</td></tr>
        		
        		<!-- KEPUTUSAN MAJLIS -->	
				<tr><td><b>9. KEPUTUSAN MAJLIS :</b></td></tr>
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
	
}//TUJUAN


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
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
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
	
}//PERIHALTANAH


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
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERIHALPERMOHONAN_MAIN\" id=\"txtUlasanPERIHALPERMOHONAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check','txtUlasanPERIHALPERMOHONAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check','txtUlasanPERIHALPERMOHONAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALPERMOHONAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERIHALPERMOHONAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanPERIHALPERMOHONAN_MAIN\" id=\"txtUlasanPERIHALPERMOHONAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check','txtUlasanPERIHALPERMOHONAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check','txtUlasanPERIHALPERMOHONAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALPERMOHONAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERIHALPERMOHONAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
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
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERIHALPERMOHONAN_MAIN\" id=\"txtUlasanPERIHALPERMOHONAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"','txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"','txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanPERIHALPERMOHONAN_MAIN\" id=\"txtUlasanPERIHALPERMOHONAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"','txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"','txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALPERMOHONAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALPERMOHONAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
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
	
}//PERIHALPERMOHONAN


//ANGGARANKOS
function textarea_ANGGARANKOS_MAIN(tambahtolak,jenis,index_tolak){
	
	var ANGGARANKOS_MAIN_temp1_length=0;

	if(document.${formName}.ANGGARANKOS_MAIN_temp1 != null){

		if(document.${formName}.ANGGARANKOS_MAIN_temp1.length>0){
			ANGGARANKOS_MAIN_temp1_length = document.${formName}.ANGGARANKOS_MAIN_temp1.length;
		}else{
			ANGGARANKOS_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanANGGARANKOS_MAIN!=null){

		if(document.${formName}.txtUlasanANGGARANKOS_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanANGGARANKOS_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ANGGARANKOS_MAIN_tempX1' name='ANGGARANKOS_MAIN_tempX1' value= '"+document.${formName}.txtUlasanANGGARANKOS_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ANGGARANKOS_MAIN_tempX1' name='ANGGARANKOS_MAIN_tempX1' value= '"+document.${formName}.txtUlasanANGGARANKOS_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ANGGARANKOS_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ANGGARANKOS_MAIN_temp1_length>0){
			ttANGGARANKOS_MAIN = ANGGARANKOS_MAIN_temp1_length;
		}else{
			ttANGGARANKOS_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttANGGARANKOS_MAIN = ttANGGARANKOS_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttANGGARANKOS_MAIN = ttANGGARANKOS_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttANGGARANKOS_MAIN; i++){	

  		if(ttANGGARANKOS_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ANGGARANKOS_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ANGGARANKOS_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanANGGARANKOS_MAIN\" id=\"txtUlasanANGGARANKOS_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanANGGARANKOS_MAIN_check','txtUlasanANGGARANKOS_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanANGGARANKOS_MAIN_check','txtUlasanANGGARANKOS_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanANGGARANKOS_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanANGGARANKOS_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanANGGARANKOS_MAIN\" id=\"txtUlasanANGGARANKOS_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanANGGARANKOS_MAIN_check','txtUlasanANGGARANKOS_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanANGGARANKOS_MAIN_check','txtUlasanANGGARANKOS_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanANGGARANKOS_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanANGGARANKOS_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ANGGARANKOS_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttANGGARANKOS_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ANGGARANKOS_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttANGGARANKOS_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ANGGARANKOS_MAIN_tempX1.value

			}else if(ttANGGARANKOS_MAIN>2 && i!=(ttANGGARANKOS_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ANGGARANKOS_MAIN_tempX1[i].value

			}else if(ttANGGARANKOS_MAIN>1 && i!=(ttANGGARANKOS_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ANGGARANKOS_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ANGGARANKOS_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ANGGARANKOS_MAIN_tempX1[i+1].value	
				}	

			}else if(ttANGGARANKOS_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ANGGARANKOS_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ANGGARANKOS_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanANGGARANKOS_MAIN\" id=\"txtUlasanANGGARANKOS_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanANGGARANKOS_MAIN_check"+i+"','txtUlasanANGGARANKOS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanANGGARANKOS_MAIN_check"+i+"','txtUlasanANGGARANKOS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanANGGARANKOS_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanANGGARANKOS_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanANGGARANKOS_MAIN\" id=\"txtUlasanANGGARANKOS_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanANGGARANKOS_MAIN_check"+i+"','txtUlasanANGGARANKOS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanANGGARANKOS_MAIN_check"+i+"','txtUlasanANGGARANKOS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanANGGARANKOS_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanANGGARANKOS_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttANGGARANKOS_MAIN>1 && ttANGGARANKOS_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ANGGARANKOS_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttANGGARANKOS_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ANGGARANKOS_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ANGGARANKOS_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ANGGARANKOS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanANGGARANKOS_MAIN.length > 1 ){

		for (t = 0; t < ANGGARANKOS_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanANGGARANKOS_MAIN[t].value = document.${formName}.ANGGARANKOS_MAIN_temp1[t].value;
		}

		}else if(ANGGARANKOS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanANGGARANKOS_MAIN.length < 1 ){
		
			for (t = 0; t < ANGGARANKOS_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanANGGARANKOS_MAIN.value = document.${formName}.ANGGARANKOS_MAIN_temp1[0].value;
			}
		}
		
		else if(ANGGARANKOS_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanANGGARANKOS_MAIN.value = document.${formName}.ANGGARANKOS_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ANGGARANKOS_MAIN_temp1_length > 1){

			for (t = 0; t < ANGGARANKOS_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ANGGARANKOS_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ANGGARANKOS_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ANGGARANKOS_MAIN_temp1_length == 1){
			
	 		document.${formName}.ANGGARANKOS_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ANGGARANKOS_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttANGGARANKOS_MAIN; i++){		

		    if(ttANGGARANKOS_MAIN==1){
		 check_length(document.${formName}.txtUlasanANGGARANKOS_MAIN,'30000','txtUlasanANGGARANKOS_MAIN_check','txtUlasanANGGARANKOS_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanANGGARANKOS_MAIN[i],'30000','txtUlasanANGGARANKOS_MAIN_check'+i,'txtUlasanANGGARANKOS_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//ANGGARANKOS


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
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLAPORANTEKNIKAL_MAIN\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLAPORANTEKNIKAL_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanLAPORANTEKNIKAL_MAIN\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check','txtUlasanLAPORANTEKNIKAL_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLAPORANTEKNIKAL_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
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
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLAPORANTEKNIKAL_MAIN\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"','txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"','txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanLAPORANTEKNIKAL_MAIN\" id=\"txtUlasanLAPORANTEKNIKAL_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"','txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"','txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTEKNIKAL_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTEKNIKAL_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
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
	
}//LAPORANTEKNIKAL


//ULASANPT
function textarea_ULASANPT_MAIN(tambahtolak,jenis,index_tolak){
	
	var ULASANPT_MAIN_temp1_length=0;

	if(document.${formName}.ULASANPT_MAIN_temp1 != null){

		if(document.${formName}.ULASANPT_MAIN_temp1.length>0){
			ULASANPT_MAIN_temp1_length = document.${formName}.ULASANPT_MAIN_temp1.length;
		}else{
			ULASANPT_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanULASANPT_MAIN!=null){

		if(document.${formName}.txtUlasanULASANPT_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanULASANPT_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ULASANPT_MAIN_tempX1' name='ULASANPT_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPT_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ULASANPT_MAIN_tempX1' name='ULASANPT_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPT_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ULASANPT_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ULASANPT_MAIN_temp1_length>0){
			ttULASANPT_MAIN = ULASANPT_MAIN_temp1_length;
		}else{
			ttULASANPT_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttULASANPT_MAIN = ttULASANPT_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttULASANPT_MAIN = ttULASANPT_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttULASANPT_MAIN; i++){	

  		if(ttULASANPT_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPT_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPT_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanULASANPT_MAIN\" id=\"txtUlasanULASANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check','txtUlasanULASANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check','txtUlasanULASANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanULASANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanULASANPT_MAIN\" id=\"txtUlasanULASANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check','txtUlasanULASANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check','txtUlasanULASANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanULASANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ULASANPT_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttULASANPT_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ULASANPT_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttULASANPT_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPT_MAIN_tempX1.value

			}else if(ttULASANPT_MAIN>2 && i!=(ttULASANPT_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPT_MAIN_tempX1[i].value

			}else if(ttULASANPT_MAIN>1 && i!=(ttULASANPT_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ULASANPT_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ULASANPT_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ULASANPT_MAIN_tempX1[i+1].value	
				}	

			}else if(ttULASANPT_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPT_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPT_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 6."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanULASANPT_MAIN\" id=\"txtUlasanULASANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check"+i+"','txtUlasanULASANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check"+i+"','txtUlasanULASANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanULASANPT_MAIN\" id=\"txtUlasanULASANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check"+i+"','txtUlasanULASANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check"+i+"','txtUlasanULASANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttULASANPT_MAIN>1 && ttULASANPT_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ULASANPT_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttULASANPT_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ULASANPT_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ULASANPT_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ULASANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPT_MAIN.length > 1 ){

		for (t = 0; t < ULASANPT_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanULASANPT_MAIN[t].value = document.${formName}.ULASANPT_MAIN_temp1[t].value;
		}

		}else if(ULASANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPT_MAIN.length < 1 ){
		
			for (t = 0; t < ULASANPT_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanULASANPT_MAIN.value = document.${formName}.ULASANPT_MAIN_temp1[0].value;
			}
		}
		
		else if(ULASANPT_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanULASANPT_MAIN.value = document.${formName}.ULASANPT_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ULASANPT_MAIN_temp1_length > 1){

			for (t = 0; t < ULASANPT_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ULASANPT_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ULASANPT_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ULASANPT_MAIN_temp1_length == 1){
			
	 		document.${formName}.ULASANPT_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ULASANPT_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttULASANPT_MAIN; i++){		

		    if(ttULASANPT_MAIN==1){
		 check_length(document.${formName}.txtUlasanULASANPT_MAIN,'30000','txtUlasanULASANPT_MAIN_check','txtUlasanULASANPT_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanULASANPT_MAIN[i],'30000','txtUlasanULASANPT_MAIN_check'+i,'txtUlasanULASANPT_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//ULASANPT


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
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERAKUANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERAKUANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
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
				  		"		<td width='3%' valign='top'> 7."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
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
	
}//PERAKUANPT


//JAWATANKUASANEGERI
function textarea_JAWATANKUASANEGERI_MAIN(tambahtolak,jenis,index_tolak){
	
	var JAWATANKUASANEGERI_MAIN_temp1_length=0;

	if(document.${formName}.JAWATANKUASANEGERI_MAIN_temp1 != null){

		if(document.${formName}.JAWATANKUASANEGERI_MAIN_temp1.length>0){
			JAWATANKUASANEGERI_MAIN_temp1_length = document.${formName}.JAWATANKUASANEGERI_MAIN_temp1.length;
		}else{
			JAWATANKUASANEGERI_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN!=null){

		if(document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='JAWATANKUASANEGERI_MAIN_tempX1' name='JAWATANKUASANEGERI_MAIN_tempX1' value= '"+document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='JAWATANKUASANEGERI_MAIN_tempX1' name='JAWATANKUASANEGERI_MAIN_tempX1' value= '"+document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#JAWATANKUASANEGERI_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(JAWATANKUASANEGERI_MAIN_temp1_length>0){
			ttJAWATANKUASANEGERI_MAIN = JAWATANKUASANEGERI_MAIN_temp1_length;
		}else{
			ttJAWATANKUASANEGERI_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttJAWATANKUASANEGERI_MAIN = ttJAWATANKUASANEGERI_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttJAWATANKUASANEGERI_MAIN = ttJAWATANKUASANEGERI_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttJAWATANKUASANEGERI_MAIN; i++){	

  		if(ttJAWATANKUASANEGERI_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.JAWATANKUASANEGERI_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.JAWATANKUASANEGERI_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanJAWATANKUASANEGERI_MAIN\" id=\"txtUlasanJAWATANKUASANEGERI_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanJAWATANKUASANEGERI_MAIN_check','txtUlasanJAWATANKUASANEGERI_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanJAWATANKUASANEGERI_MAIN_check','txtUlasanJAWATANKUASANEGERI_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanJAWATANKUASANEGERI_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanJAWATANKUASANEGERI_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanJAWATANKUASANEGERI_MAIN\" id=\"txtUlasanJAWATANKUASANEGERI_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanJAWATANKUASANEGERI_MAIN_check','txtUlasanJAWATANKUASANEGERI_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanJAWATANKUASANEGERI_MAIN_check','txtUlasanJAWATANKUASANEGERI_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanJAWATANKUASANEGERI_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanJAWATANKUASANEGERI_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_JAWATANKUASANEGERI_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttJAWATANKUASANEGERI_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_JAWATANKUASANEGERI_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttJAWATANKUASANEGERI_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.JAWATANKUASANEGERI_MAIN_tempX1.value

			}else if(ttJAWATANKUASANEGERI_MAIN>2 && i!=(ttJAWATANKUASANEGERI_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.JAWATANKUASANEGERI_MAIN_tempX1[i].value

			}else if(ttJAWATANKUASANEGERI_MAIN>1 && i!=(ttJAWATANKUASANEGERI_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.JAWATANKUASANEGERI_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.JAWATANKUASANEGERI_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.JAWATANKUASANEGERI_MAIN_tempX1[i+1].value	
				}	

			}else if(ttJAWATANKUASANEGERI_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.JAWATANKUASANEGERI_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.JAWATANKUASANEGERI_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 8."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanJAWATANKUASANEGERI_MAIN\" id=\"txtUlasanJAWATANKUASANEGERI_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanJAWATANKUASANEGERI_MAIN_check"+i+"','txtUlasanJAWATANKUASANEGERI_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanJAWATANKUASANEGERI_MAIN_check"+i+"','txtUlasanJAWATANKUASANEGERI_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanJAWATANKUASANEGERI_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanJAWATANKUASANEGERI_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanJAWATANKUASANEGERI_MAIN\" id=\"txtUlasanJAWATANKUASANEGERI_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanJAWATANKUASANEGERI_MAIN_check"+i+"','txtUlasanJAWATANKUASANEGERI_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanJAWATANKUASANEGERI_MAIN_check"+i+"','txtUlasanJAWATANKUASANEGERI_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanJAWATANKUASANEGERI_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanJAWATANKUASANEGERI_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttJAWATANKUASANEGERI_MAIN>1 && ttJAWATANKUASANEGERI_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_JAWATANKUASANEGERI_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttJAWATANKUASANEGERI_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_JAWATANKUASANEGERI_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#JAWATANKUASANEGERI_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(JAWATANKUASANEGERI_MAIN_temp1_length > 1 && document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN.length > 1 ){

		for (t = 0; t < JAWATANKUASANEGERI_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN[t].value = document.${formName}.JAWATANKUASANEGERI_MAIN_temp1[t].value;
		}

		}else if(JAWATANKUASANEGERI_MAIN_temp1_length > 1 && document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN.length < 1 ){
		
			for (t = 0; t < JAWATANKUASANEGERI_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN.value = document.${formName}.JAWATANKUASANEGERI_MAIN_temp1[0].value;
			}
		}
		
		else if(JAWATANKUASANEGERI_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN.value = document.${formName}.JAWATANKUASANEGERI_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(JAWATANKUASANEGERI_MAIN_temp1_length > 1){

			for (t = 0; t < JAWATANKUASANEGERI_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.JAWATANKUASANEGERI_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.JAWATANKUASANEGERI_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(JAWATANKUASANEGERI_MAIN_temp1_length == 1){
			
	 		document.${formName}.JAWATANKUASANEGERI_MAIN_temp1.value = "";			
	 		var element = document.${formName}.JAWATANKUASANEGERI_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttJAWATANKUASANEGERI_MAIN; i++){		

		    if(ttJAWATANKUASANEGERI_MAIN==1){
		 check_length(document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN,'30000','txtUlasanJAWATANKUASANEGERI_MAIN_check','txtUlasanJAWATANKUASANEGERI_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN[i],'30000','txtUlasanJAWATANKUASANEGERI_MAIN_check'+i,'txtUlasanJAWATANKUASANEGERI_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//JAWATANKUASANEGERI


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
				  		"		<td width='3%' valign='top'> 9."+(i+1)+"  </td> 																		"+
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
	
}//KEPUTUSAN


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


    //ANGGARANKOS
	var ANGGARANKOS_MAIN_temp1_length=0;
    
	if(document.${formName}.ANGGARANKOS_MAIN_temp1 != null){
		if(document.${formName}.ANGGARANKOS_MAIN_temp1.length>0){
			ANGGARANKOS_MAIN_temp1_length = document.${formName}.ANGGARANKOS_MAIN_temp1.length;
		}else{
			ANGGARANKOS_MAIN_temp1_length = 1;
		}
	}

    if(ANGGARANKOS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanANGGARANKOS_MAIN.length > 1 ){
		for (t = 0; t < ANGGARANKOS_MAIN_temp1_length; t++){	
    		document.${formName}.ANGGARANKOS_MAIN_temp1[t].value = document.${formName}.txtUlasanANGGARANKOS_MAIN[t].value;
		}
	}else if(ANGGARANKOS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanANGGARANKOS_MAIN.length < 1 ){
		for (t = 0; t < ANGGARANKOS_MAIN_temp1_length; t++){	
    		document.${formName}.ANGGARANKOS_MAIN_temp1[0].value = document.${formName}.txtUlasanANGGARANKOS_MAIN.value;
    	}
	}else if(ANGGARANKOS_MAIN_temp1_length == 1){
		document.${formName}.ANGGARANKOS_MAIN_temp1.value = document.${formName}.txtUlasanANGGARANKOS_MAIN.value;
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

    
    //ULASANPT
	var ULASANPT_MAIN_temp1_length=0;
    
	if(document.${formName}.ULASANPT_MAIN_temp1 != null){
		if(document.${formName}.ULASANPT_MAIN_temp1.length>0){
			ULASANPT_MAIN_temp1_length = document.${formName}.ULASANPT_MAIN_temp1.length;
		}else{
			ULASANPT_MAIN_temp1_length = 1;
		}
	}

    if(ULASANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPT_MAIN.length > 1 ){
		for (t = 0; t < ULASANPT_MAIN_temp1_length; t++){	
    		document.${formName}.ULASANPT_MAIN_temp1[t].value = document.${formName}.txtUlasanULASANPT_MAIN[t].value;
		}
	}else if(ULASANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPT_MAIN.length < 1 ){
		for (t = 0; t < ULASANPT_MAIN_temp1_length; t++){	
    		document.${formName}.ULASANPT_MAIN_temp1[0].value = document.${formName}.txtUlasanULASANPT_MAIN.value;
    	}
	}else if(ULASANPT_MAIN_temp1_length == 1){
		document.${formName}.ULASANPT_MAIN_temp1.value = document.${formName}.txtUlasanULASANPT_MAIN.value;
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

    
    //JAWATANKUASANEGERI
	var JAWATANKUASANEGERI_MAIN_temp1_length=0;
    
	if(document.${formName}.JAWATANKUASANEGERI_MAIN_temp1 != null){
		if(document.${formName}.JAWATANKUASANEGERI_MAIN_temp1.length>0){
			JAWATANKUASANEGERI_MAIN_temp1_length = document.${formName}.JAWATANKUASANEGERI_MAIN_temp1.length;
		}else{
			JAWATANKUASANEGERI_MAIN_temp1_length = 1;
		}
	}

    if(JAWATANKUASANEGERI_MAIN_temp1_length > 1 && document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN.length > 1 ){
		for (t = 0; t < JAWATANKUASANEGERI_MAIN_temp1_length; t++){	
    		document.${formName}.JAWATANKUASANEGERI_MAIN_temp1[t].value = document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN[t].value;
		}
	}else if(JAWATANKUASANEGERI_MAIN_temp1_length > 1 && document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN.length < 1 ){
		for (t = 0; t < JAWATANKUASANEGERI_MAIN_temp1_length; t++){	
    		document.${formName}.JAWATANKUASANEGERI_MAIN_temp1[0].value = document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN.value;
    	}
	}else if(JAWATANKUASANEGERI_MAIN_temp1_length == 1){
		document.${formName}.JAWATANKUASANEGERI_MAIN_temp1.value = document.${formName}.txtUlasanJAWATANKUASANEGERI_MAIN.value;
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