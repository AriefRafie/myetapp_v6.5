<fieldset>
<legend><strong>Penyediaan Kertas MMK/MB/KM/LC</strong></legend>
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

        		<tr><td colspan="2">&nbsp;</td></tr>
        	
        		<!-- LATAR BELAKANG PERMOHONAN -->	
				<tr><td colspan="2"><b>2. LATAR BELAKANG :</b></td></tr>
				<tr>
					<td colspan="2">
					
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
			
        		<tr><td colspan="2">&nbsp;</td></tr>
        		
        		<!-- LAPORAN TANAH -->	
				<tr><td colspan="2"><b>3. ASAS - ASAS PERTIMBANGAN :</b></td></tr>
				<tr>
					<td colspan="2">
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "ASASPERTIMBANGAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="ASASPERTIMBANGAN_MAIN_temp1"  id="ASASPERTIMBANGAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="ASASPERTIMBANGAN_MAIN"></span>           
   					<div id="ASASPERTIMBANGAN_MAIN_temp"></div>	
					
					</td>
				</tr>
				
				<tr><td colspan="2">&nbsp;</td></tr>
        	
        		<!-- IMPLIKASI  -->	
				<tr><td colspan="2"><b>4. IMPLIKASI :</b></td></tr>
				
				<tr>
					<td colspan="2">
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "IMPLIKASI" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="IMPLIKASI_MAIN_temp1"  id="IMPLIKASI_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="IMPLIKASI_MAIN"></span>           
   					<div id="IMPLIKASI_MAIN_temp"></div>	
					
					</td>
				</tr>
			
        		<tr><td colspan="2">&nbsp;</td></tr>
        		
        		<!-- ULASAN JABATAN TEKNIKAL  -->	
				<tr><td colspan="2"><b>5. ULASAN JABATAN TEKNIKAL :</b></td></tr>
				
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
				
				<tr><td colspan="2">&nbsp;</td></tr>
        		
        		<!-- KESIMPULAN -->	
				<tr><td colspan="2"><b>6. KESIMPULAN :</b></td></tr>
				<tr>
					<td colspan="2">
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "KESIMPULAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="KESIMPULAN_MAIN_temp1"  id="KESIMPULAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="KESIMPULAN_MAIN"></span>           
   					<div id="KESIMPULAN_MAIN_temp"></div>	
					
					</td>
				</tr>
				
        		<tr><td colspan="2">&nbsp;</td></tr>
        		
        		<!-- SYOR PENTADBIR TANAH DAERAH -->	
				<tr><td colspan="2"><b>7. SYOR PENTADBIR TANAH :</b></td></tr>
				
				<tr>
				    <td width="2%">&nbsp;</td>
					<td width="98%">
					    <br/>
						7.1 Setelah meneliti dan mengkaji segala laporan dan ulasan di atas, 
						    maka Pengarah Tanah Dan Galian Perak dengan ini mengesyorkan :-
					</td>
				</tr>
				
				<tr>
					<td  colspan="2">
					
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

//ASASPERTIMBANGAN
function textarea_ASASPERTIMBANGAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var ASASPERTIMBANGAN_MAIN_temp1_length=0;

	if(document.${formName}.ASASPERTIMBANGAN_MAIN_temp1 != null){

		if(document.${formName}.ASASPERTIMBANGAN_MAIN_temp1.length>0){
			ASASPERTIMBANGAN_MAIN_temp1_length = document.${formName}.ASASPERTIMBANGAN_MAIN_temp1.length;
		}else{
			ASASPERTIMBANGAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN!=null){

		if(document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ASASPERTIMBANGAN_MAIN_tempX1' name='ASASPERTIMBANGAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ASASPERTIMBANGAN_MAIN_tempX1' name='ASASPERTIMBANGAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ASASPERTIMBANGAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ASASPERTIMBANGAN_MAIN_temp1_length>0){
			ttASASPERTIMBANGAN_MAIN = ASASPERTIMBANGAN_MAIN_temp1_length;
		}else{
			ttASASPERTIMBANGAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttASASPERTIMBANGAN_MAIN = ttASASPERTIMBANGAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttASASPERTIMBANGAN_MAIN = ttASASPERTIMBANGAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttASASPERTIMBANGAN_MAIN; i++){	

  		if(ttASASPERTIMBANGAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ASASPERTIMBANGAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ASASPERTIMBANGAN_MAIN_tempX1[0].value
				} 	
			}		

			
   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanASASPERTIMBANGAN_MAIN\" id=\"txtUlasanASASPERTIMBANGAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanASASPERTIMBANGAN_MAIN_check','txtUlasanASASPERTIMBANGAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanASASPERTIMBANGAN_MAIN_check','txtUlasanASASPERTIMBANGAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanASASPERTIMBANGAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanASASPERTIMBANGAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanASASPERTIMBANGAN_MAIN\" id=\"txtUlasanASASPERTIMBANGAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanASASPERTIMBANGAN_MAIN_check','txtUlasanASASPERTIMBANGAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanASASPERTIMBANGAN_MAIN_check','txtUlasanASASPERTIMBANGAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanASASPERTIMBANGAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanASASPERTIMBANGAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ASASPERTIMBANGAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttASASPERTIMBANGAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ASASPERTIMBANGAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttASASPERTIMBANGAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ASASPERTIMBANGAN_MAIN_tempX1.value

			}else if(ttASASPERTIMBANGAN_MAIN>2 && i!=(ttASASPERTIMBANGAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ASASPERTIMBANGAN_MAIN_tempX1[i].value

			}else if(ttASASPERTIMBANGAN_MAIN>1 && i!=(ttASASPERTIMBANGAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ASASPERTIMBANGAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ASASPERTIMBANGAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ASASPERTIMBANGAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttASASPERTIMBANGAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ASASPERTIMBANGAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ASASPERTIMBANGAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanASASPERTIMBANGAN_MAIN\" id=\"txtUlasanASASPERTIMBANGAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanASASPERTIMBANGAN_MAIN_check"+i+"','txtUlasanASASPERTIMBANGAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanASASPERTIMBANGAN_MAIN_check"+i+"','txtUlasanASASPERTIMBANGAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanASASPERTIMBANGAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanASASPERTIMBANGAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanASASPERTIMBANGAN_MAIN\" id=\"txtUlasanASASPERTIMBANGAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanASASPERTIMBANGAN_MAIN_check"+i+"','txtUlasanASASPERTIMBANGAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanASASPERTIMBANGAN_MAIN_check"+i+"','txtUlasanASASPERTIMBANGAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanASASPERTIMBANGAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanASASPERTIMBANGAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttASASPERTIMBANGAN_MAIN>1 && ttASASPERTIMBANGAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ASASPERTIMBANGAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttASASPERTIMBANGAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ASASPERTIMBANGAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ASASPERTIMBANGAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ASASPERTIMBANGAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN.length > 1 ){

		for (t = 0; t < ASASPERTIMBANGAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN[t].value = document.${formName}.ASASPERTIMBANGAN_MAIN_temp1[t].value;
		}

		}else if(ASASPERTIMBANGAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN.length < 1 ){
		
			for (t = 0; t < ASASPERTIMBANGAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN.value = document.${formName}.ASASPERTIMBANGAN_MAIN_temp1[0].value;
			}
		}
		
		else if(ASASPERTIMBANGAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN.value = document.${formName}.ASASPERTIMBANGAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ASASPERTIMBANGAN_MAIN_temp1_length > 1){

			for (t = 0; t < ASASPERTIMBANGAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ASASPERTIMBANGAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ASASPERTIMBANGAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ASASPERTIMBANGAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.ASASPERTIMBANGAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ASASPERTIMBANGAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttASASPERTIMBANGAN_MAIN; i++){		

		    if(ttASASPERTIMBANGAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN,'30000','txtUlasanASASPERTIMBANGAN_MAIN_check','txtUlasanASASPERTIMBANGAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN[i],'30000','txtUlasanASASPERTIMBANGAN_MAIN_check'+i,'txtUlasanASASPERTIMBANGAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE ASASPERTIMBANGAN


//IMPLIKASI
function textarea_IMPLIKASI_MAIN(tambahtolak,jenis,index_tolak){
	
	var IMPLIKASI_MAIN_temp1_length=0;

	if(document.${formName}.IMPLIKASI_MAIN_temp1 != null){

		if(document.${formName}.IMPLIKASI_MAIN_temp1.length>0){
			IMPLIKASI_MAIN_temp1_length = document.${formName}.IMPLIKASI_MAIN_temp1.length;
		}else{
			IMPLIKASI_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanIMPLIKASI_MAIN!=null){

		if(document.${formName}.txtUlasanIMPLIKASI_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanIMPLIKASI_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='IMPLIKASI_MAIN_tempX1' name='IMPLIKASI_MAIN_tempX1' value= '"+document.${formName}.txtUlasanIMPLIKASI_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='IMPLIKASI_MAIN_tempX1' name='IMPLIKASI_MAIN_tempX1' value= '"+document.${formName}.txtUlasanIMPLIKASI_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#IMPLIKASI_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(IMPLIKASI_MAIN_temp1_length>0){
			ttIMPLIKASI_MAIN = IMPLIKASI_MAIN_temp1_length;
		}else{
			ttIMPLIKASI_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttIMPLIKASI_MAIN = ttIMPLIKASI_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttIMPLIKASI_MAIN = ttIMPLIKASI_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttIMPLIKASI_MAIN; i++){	

  		if(ttIMPLIKASI_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.IMPLIKASI_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.IMPLIKASI_MAIN_tempX1[0].value
				} 	
			}		

			
   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanIMPLIKASI_MAIN\" id=\"txtUlasanIMPLIKASI_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanIMPLIKASI_MAIN_check','txtUlasanIMPLIKASI_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanIMPLIKASI_MAIN_check','txtUlasanIMPLIKASI_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanIMPLIKASI_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanIMPLIKASI_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanIMPLIKASI_MAIN\" id=\"txtUlasanIMPLIKASI_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanIMPLIKASI_MAIN_check','txtUlasanIMPLIKASI_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanIMPLIKASI_MAIN_check','txtUlasanIMPLIKASI_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanIMPLIKASI_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanIMPLIKASI_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_IMPLIKASI_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttIMPLIKASI_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_IMPLIKASI_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttIMPLIKASI_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.IMPLIKASI_MAIN_tempX1.value

			}else if(ttIMPLIKASI_MAIN>2 && i!=(ttIMPLIKASI_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.IMPLIKASI_MAIN_tempX1[i].value

			}else if(ttIMPLIKASI_MAIN>1 && i!=(ttIMPLIKASI_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.IMPLIKASI_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.IMPLIKASI_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.IMPLIKASI_MAIN_tempX1[i+1].value	
				}	

			}else if(ttIMPLIKASI_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.IMPLIKASI_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.IMPLIKASI_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanIMPLIKASI_MAIN\" id=\"txtUlasanIMPLIKASI_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanIMPLIKASI_MAIN_check"+i+"','txtUlasanIMPLIKASI_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanIMPLIKASI_MAIN_check"+i+"','txtUlasanIMPLIKASI_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanIMPLIKASI_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanIMPLIKASI_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanIMPLIKASI_MAIN\" id=\"txtUlasanIMPLIKASI_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanIMPLIKASI_MAIN_check"+i+"','txtUlasanIMPLIKASI_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanIMPLIKASI_MAIN_check"+i+"','txtUlasanIMPLIKASI_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanIMPLIKASI_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanIMPLIKASI_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttIMPLIKASI_MAIN>1 && ttIMPLIKASI_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_IMPLIKASI_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttIMPLIKASI_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_IMPLIKASI_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#IMPLIKASI_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(IMPLIKASI_MAIN_temp1_length > 1 && document.${formName}.txtUlasanIMPLIKASI_MAIN.length > 1 ){

		for (t = 0; t < IMPLIKASI_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanIMPLIKASI_MAIN[t].value = document.${formName}.IMPLIKASI_MAIN_temp1[t].value;
		}

		}else if(IMPLIKASI_MAIN_temp1_length > 1 && document.${formName}.txtUlasanIMPLIKASI_MAIN.length < 1 ){
		
			for (t = 0; t < IMPLIKASI_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanIMPLIKASI_MAIN.value = document.${formName}.IMPLIKASI_MAIN_temp1[0].value;
			}
		}
		
		else if(IMPLIKASI_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanIMPLIKASI_MAIN.value = document.${formName}.IMPLIKASI_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(IMPLIKASI_MAIN_temp1_length > 1){

			for (t = 0; t < IMPLIKASI_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.IMPLIKASI_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.IMPLIKASI_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(IMPLIKASI_MAIN_temp1_length == 1){
			
	 		document.${formName}.IMPLIKASI_MAIN_temp1.value = "";			
	 		var element = document.${formName}.IMPLIKASI_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttIMPLIKASI_MAIN; i++){		

		    if(ttIMPLIKASI_MAIN==1){
		 check_length(document.${formName}.txtUlasanIMPLIKASI_MAIN,'30000','txtUlasanIMPLIKASI_MAIN_check','txtUlasanIMPLIKASI_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanIMPLIKASI_MAIN[i],'30000','txtUlasanIMPLIKASI_MAIN_check'+i,'txtUlasanIMPLIKASI_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE IMPLIKASI


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
	
}//CLOSE LAPORANTEKNIKAL


//KESIMPULAN
function textarea_KESIMPULAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var KESIMPULAN_MAIN_temp1_length=0;

	if(document.${formName}.KESIMPULAN_MAIN_temp1 != null){

		if(document.${formName}.KESIMPULAN_MAIN_temp1.length>0){
			KESIMPULAN_MAIN_temp1_length = document.${formName}.KESIMPULAN_MAIN_temp1.length;
		}else{
			KESIMPULAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanKESIMPULAN_MAIN!=null){

		if(document.${formName}.txtUlasanKESIMPULAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanKESIMPULAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='KESIMPULAN_MAIN_tempX1' name='KESIMPULAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKESIMPULAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='KESIMPULAN_MAIN_tempX1' name='KESIMPULAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKESIMPULAN_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#KESIMPULAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(KESIMPULAN_MAIN_temp1_length>0){
			ttKESIMPULAN_MAIN = KESIMPULAN_MAIN_temp1_length;
		}else{
			ttKESIMPULAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttKESIMPULAN_MAIN = ttKESIMPULAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttKESIMPULAN_MAIN = ttKESIMPULAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttKESIMPULAN_MAIN; i++){	

  		if(ttKESIMPULAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[0].value
				} 	
			}		

			
   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanKESIMPULAN_MAIN\" id=\"txtUlasanKESIMPULAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check','txtUlasanKESIMPULAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check','txtUlasanKESIMPULAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKESIMPULAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanKESIMPULAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanKESIMPULAN_MAIN\" id=\"txtUlasanKESIMPULAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check','txtUlasanKESIMPULAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check','txtUlasanKESIMPULAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKESIMPULAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanKESIMPULAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_KESIMPULAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttKESIMPULAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_KESIMPULAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttKESIMPULAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1.value

			}else if(ttKESIMPULAN_MAIN>2 && i!=(ttKESIMPULAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[i].value

			}else if(ttKESIMPULAN_MAIN>1 && i!=(ttKESIMPULAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttKESIMPULAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KESIMPULAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 6."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanKESIMPULAN_MAIN\" id=\"txtUlasanKESIMPULAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check"+i+"','txtUlasanKESIMPULAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check"+i+"','txtUlasanKESIMPULAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKESIMPULAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKESIMPULAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanKESIMPULAN_MAIN\" id=\"txtUlasanKESIMPULAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check"+i+"','txtUlasanKESIMPULAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKESIMPULAN_MAIN_check"+i+"','txtUlasanKESIMPULAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKESIMPULAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKESIMPULAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttKESIMPULAN_MAIN>1 && ttKESIMPULAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_KESIMPULAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttKESIMPULAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_KESIMPULAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#KESIMPULAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(KESIMPULAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKESIMPULAN_MAIN.length > 1 ){

		for (t = 0; t < KESIMPULAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanKESIMPULAN_MAIN[t].value = document.${formName}.KESIMPULAN_MAIN_temp1[t].value;
		}

		}else if(KESIMPULAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKESIMPULAN_MAIN.length < 1 ){
		
			for (t = 0; t < KESIMPULAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanKESIMPULAN_MAIN.value = document.${formName}.KESIMPULAN_MAIN_temp1[0].value;
			}
		}
		
		else if(KESIMPULAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanKESIMPULAN_MAIN.value = document.${formName}.KESIMPULAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(KESIMPULAN_MAIN_temp1_length > 1){

			for (t = 0; t < KESIMPULAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.KESIMPULAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.KESIMPULAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(KESIMPULAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.KESIMPULAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.KESIMPULAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttKESIMPULAN_MAIN; i++){		

		    if(ttKESIMPULAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanKESIMPULAN_MAIN,'30000','txtUlasanKESIMPULAN_MAIN_check','txtUlasanKESIMPULAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanKESIMPULAN_MAIN[i],'30000','txtUlasanKESIMPULAN_MAIN_check'+i,'txtUlasanKESIMPULAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE KESIMPULAN


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
   	   				  "			<td width='4%'>&nbsp;</td> 																								"+
   	   				  /*"			<td width='3%' valign='top'>&nbsp;</td> 																		"+ */
					  "			#if($mode=='new') 																										"+
					  "			<td width='96%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanSYOR_MAIN\" id=\"txtUlasanSYOR_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check','txtUlasanSYOR_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check','txtUlasanSYOR_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanSYOR_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanSYOR_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='94%'> 																										"+
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
					  "			<td valign='top'>&nbsp;</td>																				"+	
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
 						" <tr><td colspan='3'>&nbsp;</td></tr> "+
	 					" 	<tr> 																														"+
				  		"		<td width='4%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 7.1."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='93%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanSYOR_MAIN\" id=\"txtUlasanSYOR_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check"+i+"','txtUlasanSYOR_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanSYOR_MAIN_check"+i+"','txtUlasanSYOR_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanSYOR_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanSYOR_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='93%'> 																										"+
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

    
  	//ASASPERTIMBANGAN
 	var ASASPERTIMBANGAN_MAIN_temp1_length=0;
    
	if(document.${formName}.ASASPERTIMBANGAN_MAIN_temp1 != null){
		if(document.${formName}.ASASPERTIMBANGAN_MAIN_temp1.length>0){
			ASASPERTIMBANGAN_MAIN_temp1_length = document.${formName}.ASASPERTIMBANGAN_MAIN_temp1.length;
		}else{
			ASASPERTIMBANGAN_MAIN_temp1_length = 1;
		}
	}

    if(ASASPERTIMBANGAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN.length > 1 ){
		for (t = 0; t < ASASPERTIMBANGAN_MAIN_temp1_length; t++){	
    		document.${formName}.ASASPERTIMBANGAN_MAIN_temp1[t].value = document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN[t].value;
		}
	}else if(ASASPERTIMBANGAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN.length < 1 ){
		for (t = 0; t < ASASPERTIMBANGAN_MAIN_temp1_length; t++){	
    		document.${formName}.ASASPERTIMBANGAN_MAIN_temp1[0].value = document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN.value;
    	}
	}else if(ASASPERTIMBANGAN_MAIN_temp1_length == 1){
		document.${formName}.ASASPERTIMBANGAN_MAIN_temp1.value = document.${formName}.txtUlasanASASPERTIMBANGAN_MAIN.value;
	}

    //IMPLIKASI
 	var IMPLIKASI_MAIN_temp1_length=0;
    
	if(document.${formName}.IMPLIKASI_MAIN_temp1 != null){
		if(document.${formName}.IMPLIKASI_MAIN_temp1.length>0){
			IMPLIKASI_MAIN_temp1_length = document.${formName}.IMPLIKASI_MAIN_temp1.length;
		}else{
			IMPLIKASI_MAIN_temp1_length = 1;
		}
	}

    if(IMPLIKASI_MAIN_temp1_length > 1 && document.${formName}.txtUlasanIMPLIKASI_MAIN.length > 1 ){
		for (t = 0; t < IMPLIKASI_MAIN_temp1_length; t++){	
    		document.${formName}.IMPLIKASI_MAIN_temp1[t].value = document.${formName}.txtUlasanIMPLIKASI_MAIN[t].value;
		}
	}else if(IMPLIKASI_MAIN_temp1_length > 1 && document.${formName}.txtUlasanIMPLIKASI_MAIN.length < 1 ){
		for (t = 0; t < IMPLIKASI_MAIN_temp1_length; t++){	
    		document.${formName}.IMPLIKASI_MAIN_temp1[0].value = document.${formName}.txtUlasanIMPLIKASI_MAIN.value;
    	}
	}else if(IMPLIKASI_MAIN_temp1_length == 1){
		document.${formName}.IMPLIKASI_MAIN_temp1.value = document.${formName}.txtUlasanIMPLIKASI_MAIN.value;
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


  	//KESIMPULAN
 	var KESIMPULAN_MAIN_temp1_length=0;
    
	if(document.${formName}.KESIMPULAN_MAIN_temp1 != null){
		if(document.${formName}.KESIMPULAN_MAIN_temp1.length>0){
			KESIMPULAN_MAIN_temp1_length = document.${formName}.KESIMPULAN_MAIN_temp1.length;
		}else{
			KESIMPULAN_MAIN_temp1_length = 1;
		}
	}

    if(KESIMPULAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKESIMPULAN_MAIN.length > 1 ){
		for (t = 0; t < KESIMPULAN_MAIN_temp1_length; t++){	
    		document.${formName}.KESIMPULAN_MAIN_temp1[t].value = document.${formName}.txtUlasanKESIMPULAN_MAIN[t].value;
		}
	}else if(KESIMPULAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKESIMPULAN_MAIN.length < 1 ){
		for (t = 0; t < KESIMPULAN_MAIN_temp1_length; t++){	
    		document.${formName}.KESIMPULAN_MAIN_temp1[0].value = document.${formName}.txtUlasanKESIMPULAN_MAIN.value;
    	}
	}else if(KESIMPULAN_MAIN_temp1_length == 1){
		document.${formName}.KESIMPULAN_MAIN_temp1.value = document.${formName}.txtUlasanKESIMPULAN_MAIN.value;
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
	
}

</script>