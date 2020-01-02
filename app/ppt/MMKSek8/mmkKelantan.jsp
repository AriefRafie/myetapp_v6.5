
#foreach($dataK in $dataHeader)
	#set($namaKementerian = $dataK.nama_kementerian)
	#set($alamat1 = $dataK.alamat1)
	#set($alamat2 = $dataK.alamat2)
	#set($alamat3 = $dataK.alamat3)
	#set($poskod = $dataK.poskod)
	#set($negeri_kementerian = $dataK.negeri_kementerian)
	#set($projek_daerah = $dataK.projek_daerah)
#end

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
        	
        		<!-- PERIHAL AGENSI MEMOHON  -->	
				<tr><td><b>2. PERIHAL AGENSI MEMOHON :</b></td></tr>
				
				<tr>
					<td>
					

					   <table width="100%" border="0">
						<tr>
							<td>&nbsp;</td>
						    <td colspan="4">2.1 Nama dan Alamat Permohon :</td>
						</tr>
						<tr><td colspan="5">&nbsp;</td></tr>
 						<tr>
 							<td width="2%">&nbsp;</td>
 							<td width="3%">&nbsp;</td>
						    <td width="15%">2.1.1 Pemohon</td>
						    <td width="1%">&nbsp;</td>
						    <td width="79%">Ketua Pengarah Tanah dan Galian</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>Jabatan Ketua Pengarah Tanah dan</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>Galian(Seksyen Pengambilan Tanah)</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>Kementerian Sumber Asli dan Alam Sekitar</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>Aras 3, Wisma Sumber Asli</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>No. 25, Persiaran Perdana, Presint 4</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>62574 PUTRAJAYA</td>
						</tr>
						<tr><td colspan="5">&nbsp;</td></tr>
						<tr>
 							<td>&nbsp;</td>
 							<td>&nbsp;</td>
						    <td>2.1.2 Kementerian</td>
						    <td>&nbsp;</td>
						    <td>Permohonan ini dibuat bagi pihak :</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>Ketua Setiausaha</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>$!namaKementerian</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>$!alamat1</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>$!alamat2</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>$!alamat3</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						    <td>$!poskod $!negeri_kementerian</td>
						</tr>
					   </table>

					
					
					<p/>
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "PERIHALAP" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="PERIHALAP_MAIN_temp1"  id="PERIHALAP_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    				<span id="PERIHALAP_MAIN"></span>           
   					<div id="PERIHALAP_MAIN_temp"></div>
					
					</td>
				</tr>
			
        		<tr><td>&nbsp;</td></tr>
        		
        		<!-- LATAR BELAKANG PROJEK -->	
				<tr><td><b>3. LATAR BELAKANG PROJEK :</b></td></tr>
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
        	
        		<!-- PERUNTUKAN PENGAMBILAN TANAH -->	
				<tr><td><b>4. PERUNTUKAN PENGAMBILAN TANAH :</b></td></tr>
				
				<tr>
					<td>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "PERUNTUKAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="PERUNTUKAN_MAIN_temp1"  id="PERUNTUKAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="PERUNTUKAN_MAIN"></span>           
   					<div id="PERUNTUKAN_MAIN_temp"></div>	
					
					</td>
				</tr>
			
        		<tr><td>&nbsp;</td></tr>
        		
        		<!-- PERIHAL TANAH-TANAH TERLIBAT -->	
				<tr><td><b>5. PERIHAL TANAH-TANAH TERLIBAT :</b></td></tr>
				
				<tr>
					<td>
					
					<table width="100%" border="0">
						
						<tr>
 							<td width="2%">&nbsp;</td>
 							<td width="3%">5.1</td>
 							<td width="95%">Keluasan dan bilangan lot terlibat</td>
						</tr>
					</table>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "PERIHALTANAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="PERIHALTANAH_MAIN_temp1"  id="PERIHALTANAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    				<span id="PERIHALTANAH_MAIN"></span>           
   					<div id="PERIHALTANAH_MAIN_temp"></div>
					
					<table width="100%" border="0">
						
						<tr>
 							<td width="2%" valign="top">&nbsp;</td>
 							<td width="3%" valign="top">5.2</td>
 							<td width="20%" valign="top">Mukim</td>
 							<td width="1%" valign="top">:</td>
 							<td width="74%" valign="top">$!nama2Mukim</td>
						</tr>
						<tr>
 							<td>&nbsp;</td>
 							<td>5.3</td>
 							<td>Daerah</td>
 							<td>:</td>
 							<td>$!projek_daerah</td>
						</tr>
						#if($mode=='new')
						<tr>
 							<td>&nbsp;</td>
 							<td>5.4</td>
 							<td>Jenis Penggunaan Tanah</td>
 							<td>:</td>
 							<td><input type="text" name="txtJenisPenggunaan" id="txtJenisPenggunaan" maxlength="100" size="50" value="" /></td>
						</tr>
						<tr>
 							<td>&nbsp;</td>
 							<td>5.5</td>
 							<td>Lokasi Tanah</td>
 							<td>:</td>
 							<td><input type="text" name="txtLokasi" id="txtLokasi" maxlength="500" size="50" value="" /></td>
						</tr>
						<tr>
 							<td>&nbsp;</td>
 							<td>5.6</td>
 							<td>kedudukan Tanah</td>
 							<td>:</td>
 							<td><input type="text" name="txtKedudukan" id="txtKedudukan" maxlength="500" size="50" value="" /></td>
						</tr>
						<tr>
 							<td>&nbsp;</td>
 							<td valign="top">5.7</td>
 							<td valign="top">Keadaan Tanah</td>
 							<td valign="top">:</td>
 							<!-- <td><input type="text" name="txtKeadaan" id="txtKeadaan" maxlength="3000" size="60" value="" /></td> -->
 							<td><textarea cols="47%" rows="4" name="txtKeadaan" id="txtKeadaan"></textarea></td>
						</tr>
						
						#else
						
						<tr>
 							<td>&nbsp;</td>
 							<td>5.4</td>
 							<td>Jenis Penggunaan Tanah</td>
 							<td>:</td>
 							<td><input type="text" $disability $disabilityx name="txtJenisPenggunaan" id="txtJenisPenggunaan" maxlength="400" size="50" value="$!txtJenisPenggunaan" /></td>
						</tr>
						<tr>
 							<td>&nbsp;</td>
 							<td>5.5</td>
 							<td>Lokasi Tanah</td>
 							<td>:</td>
 							<td><input type="text" $disability $disabilityx name="txtLokasi" id="txtLokasi" maxlength="500" size="50" value="$!txtLokasi" /></td>
						</tr>
						<tr>
 							<td>&nbsp;</td>
 							<td>5.6</td>
 							<td>kedudukan Tanah</td>
 							<td>:</td>
 							<td><input type="text" $disability $disabilityx name="txtKedudukan" id="txtKedudukan" maxlength="500" size="50" value="$!txtKedudukan" /></td>
						</tr>
						<tr>
 							<td>&nbsp;</td>
 							<td valign="top">5.7</td>
 							<td valign="top">Keadaan Tanah</td>
 							<td valign="top">:</td>
 							<!-- <td><input type="text" $disability $disabilityx name="txtKeadaan" id="txtKeadaan" maxlength="3000" size="60" value="$!txtKeadaan" /></td> -->
							<td><textarea cols="47%" $disability $disabilityx rows="4" name="txtKeadaan" id="txtKeadaan">$!txtKeadaan</textarea></td>
						</tr>
						#end
						
					</table>
					
					</td>
				</tr>
				
        		<tr><td>&nbsp;</td></tr>
        		
        		<!-- NAMA-NAMA TUAN TANAH -->	
				<tr><td><b>6. NAMA-NAMA TUAN TANAH :</b></td></tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;Sebagaimana Borang 'C' di kandungan (   ) dlm fail $!no_rujukan_ptg</td>
				</tr>
				
				<tr><td>&nbsp;</td></tr>
        		
        		<!-- PERAKUAN JAWATANKUASA HAL EHWAL TANAH -->	
				<tr><td><b>7. PERAKUAN JAWATANKUASA HAL EHWAL TANAH :</b></td></tr>
				
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
        		
        		<!-- ASAS-ASAS PERTIMBANGAN -->	
				<tr><td><b>8. ASAS-ASAS PERTIMBANGAN :</b></td></tr>
				
				<tr>
					<td>
					
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
				
				<tr><td>&nbsp;</td></tr>
        		
        		<!-- PENGESYORAN PENTADBIR TANAH JAJAHAN -->	
				<tr><td><b>9. PENGESYORAN PENTADBIR TANAH JAJAHAN :</b></td></tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;Berdasarkan kepada faktor-faktor di atas, Pentadbir Tanah dan
					Jajahan $!projek_daerah membuat pengesyoran seperti berikut :<br/>
					
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
        		
        		<!-- ULASAN PENGARAH TANAH DAN GALIAN KELANTAN -->	
				<tr><td><b>10. ULASAN PENGARAH TANAH DAN GALIAN KELANTAN :</b></td></tr>
				
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
        		
        		<!-- KEPUTUSAN -->	
				<tr><td><b>11. KEPUTUSAN :</b></td></tr>
				
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
	
}//CLOSE TUJUAN

//PERIHALAP
function textarea_PERIHALAP_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERIHALAP_MAIN_temp1_length=0;

	if(document.${formName}.PERIHALAP_MAIN_temp1 != null){

		if(document.${formName}.PERIHALAP_MAIN_temp1.length>0){
			PERIHALAP_MAIN_temp1_length = document.${formName}.PERIHALAP_MAIN_temp1.length;
		}else{
			PERIHALAP_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERIHALAP_MAIN!=null){

		if(document.${formName}.txtUlasanPERIHALAP_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERIHALAP_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERIHALAP_MAIN_tempX1' name='PERIHALAP_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALAP_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERIHALAP_MAIN_tempX1' name='PERIHALAP_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALAP_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#PERIHALAP_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERIHALAP_MAIN_temp1_length>0){
			ttPERIHALAP_MAIN = PERIHALAP_MAIN_temp1_length;
		}else{
			ttPERIHALAP_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERIHALAP_MAIN = ttPERIHALAP_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERIHALAP_MAIN = ttPERIHALAP_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERIHALAP_MAIN; i++){	

  		if(ttPERIHALAP_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERIHALAP_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERIHALAP_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
				  	  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'>2.2</td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERIHALAP_MAIN\" id=\"txtUlasanPERIHALAP_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALAP_MAIN_check','txtUlasanPERIHALAP_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALAP_MAIN_check','txtUlasanPERIHALAP_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALAP_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERIHALAP_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanPERIHALAP_MAIN\" id=\"txtUlasanPERIHALAP_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALAP_MAIN_check','txtUlasanPERIHALAP_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALAP_MAIN_check','txtUlasanPERIHALAP_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALAP_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERIHALAP_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERIHALAP_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERIHALAP_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERIHALAP_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERIHALAP_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERIHALAP_MAIN_tempX1.value

			}else if(ttPERIHALAP_MAIN>2 && i!=(ttPERIHALAP_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERIHALAP_MAIN_tempX1[i].value

			}else if(ttPERIHALAP_MAIN>1 && i!=(ttPERIHALAP_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERIHALAP_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERIHALAP_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERIHALAP_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERIHALAP_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERIHALAP_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERIHALAP_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
						" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+2)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERIHALAP_MAIN\" id=\"txtUlasanPERIHALAP_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALAP_MAIN_check"+i+"','txtUlasanPERIHALAP_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALAP_MAIN_check"+i+"','txtUlasanPERIHALAP_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALAP_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALAP_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanPERIHALAP_MAIN\" id=\"txtUlasanPERIHALAP_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALAP_MAIN_check"+i+"','txtUlasanPERIHALAP_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALAP_MAIN_check"+i+"','txtUlasanPERIHALAP_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALAP_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALAP_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERIHALAP_MAIN>1 && ttPERIHALAP_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERIHALAP_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERIHALAP_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERIHALAP_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERIHALAP_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERIHALAP_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALAP_MAIN.length > 1 ){

		for (t = 0; t < PERIHALAP_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERIHALAP_MAIN[t].value = document.${formName}.PERIHALAP_MAIN_temp1[t].value;
		}

		}else if(PERIHALAP_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALAP_MAIN.length < 1 ){
		
			for (t = 0; t < PERIHALAP_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERIHALAP_MAIN.value = document.${formName}.PERIHALAP_MAIN_temp1[0].value;
			}
		}
		
		else if(PERIHALAP_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERIHALAP_MAIN.value = document.${formName}.PERIHALAP_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERIHALAP_MAIN_temp1_length > 1){

			for (t = 0; t < PERIHALAP_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERIHALAP_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERIHALAP_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERIHALAP_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERIHALAP_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERIHALAP_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERIHALAP_MAIN; i++){		

		    if(ttPERIHALAP_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERIHALAP_MAIN,'30000','txtUlasanPERIHALAP_MAIN_check','txtUlasanPERIHALAP_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERIHALAP_MAIN[i],'30000','txtUlasanPERIHALAP_MAIN_check'+i,'txtUlasanPERIHALAP_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE PERIHALAP


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
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
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
	
}//CLOSE LATARBELAKANG


//PERUNTUKAN
function textarea_PERUNTUKAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERUNTUKAN_MAIN_temp1_length=0;

	if(document.${formName}.PERUNTUKAN_MAIN_temp1 != null){

		if(document.${formName}.PERUNTUKAN_MAIN_temp1.length>0){
			PERUNTUKAN_MAIN_temp1_length = document.${formName}.PERUNTUKAN_MAIN_temp1.length;
		}else{
			PERUNTUKAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERUNTUKAN_MAIN!=null){

		if(document.${formName}.txtUlasanPERUNTUKAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERUNTUKAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERUNTUKAN_MAIN_tempX1' name='PERUNTUKAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERUNTUKAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERUNTUKAN_MAIN_tempX1' name='PERUNTUKAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERUNTUKAN_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#PERUNTUKAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERUNTUKAN_MAIN_temp1_length>0){
			ttPERUNTUKAN_MAIN = PERUNTUKAN_MAIN_temp1_length;
		}else{
			ttPERUNTUKAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERUNTUKAN_MAIN = ttPERUNTUKAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERUNTUKAN_MAIN = ttPERUNTUKAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERUNTUKAN_MAIN; i++){	

  		if(ttPERUNTUKAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERUNTUKAN_MAIN\" id=\"txtUlasanPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check','txtUlasanPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check','txtUlasanPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERUNTUKAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERUNTUKAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanPERUNTUKAN_MAIN\" id=\"txtUlasanPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check','txtUlasanPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check','txtUlasanPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERUNTUKAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERUNTUKAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERUNTUKAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERUNTUKAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERUNTUKAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERUNTUKAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1.value

			}else if(ttPERUNTUKAN_MAIN>2 && i!=(ttPERUNTUKAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[i].value

			}else if(ttPERUNTUKAN_MAIN>1 && i!=(ttPERUNTUKAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERUNTUKAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERUNTUKAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERUNTUKAN_MAIN\" id=\"txtUlasanPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check"+i+"','txtUlasanPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check"+i+"','txtUlasanPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERUNTUKAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERUNTUKAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanPERUNTUKAN_MAIN\" id=\"txtUlasanPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check"+i+"','txtUlasanPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERUNTUKAN_MAIN_check"+i+"','txtUlasanPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERUNTUKAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERUNTUKAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERUNTUKAN_MAIN>1 && ttPERUNTUKAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERUNTUKAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERUNTUKAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERUNTUKAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERUNTUKAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERUNTUKAN_MAIN.length > 1 ){

		for (t = 0; t < PERUNTUKAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERUNTUKAN_MAIN[t].value = document.${formName}.PERUNTUKAN_MAIN_temp1[t].value;
		}

		}else if(PERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERUNTUKAN_MAIN.length < 1 ){
		
			for (t = 0; t < PERUNTUKAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERUNTUKAN_MAIN.value = document.${formName}.PERUNTUKAN_MAIN_temp1[0].value;
			}
		}
		
		else if(PERUNTUKAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERUNTUKAN_MAIN.value = document.${formName}.PERUNTUKAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERUNTUKAN_MAIN_temp1_length > 1){

			for (t = 0; t < PERUNTUKAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERUNTUKAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERUNTUKAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERUNTUKAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERUNTUKAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERUNTUKAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERUNTUKAN_MAIN; i++){		

		    if(ttPERUNTUKAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERUNTUKAN_MAIN,'30000','txtUlasanPERUNTUKAN_MAIN_check','txtUlasanPERUNTUKAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERUNTUKAN_MAIN[i],'30000','txtUlasanPERUNTUKAN_MAIN_check'+i,'txtUlasanPERUNTUKAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE PERUNTUKAN


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
   	   				  "			<td width='3%' valign='top'>&nbsp;</td> "+
   	   				  "			<td width='3%' valign='top'>&nbsp;</td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERIHALTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
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
					  "			<td colspan='3' valign='top'>&nbsp;</td>																				"+	
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
				  		"		<td width='3%' valign='top'>&nbsp;</td>  "+
				  		"		<td width='3%' valign='top'> 5.1."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
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
			  			"		<td colspan='3' valign='top'>&nbsp;</td>																				"+	
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
	
}//CLOSE PERIHALTANAH

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
				  		"		<td width='3%' valign='top'> 7."+(i+1)+"  </td> 																		"+
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
	
}//CLOSE JAWATANKUASANEGERI


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
				  		"		<td width='3%' valign='top'> 8."+(i+1)+"  </td> 																		"+
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
				  		"		<td width='3%' valign='top'> 9."+(i+1)+"  </td> 																		"+
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
				  		"		<td width='3%' valign='top'> 10."+(i+1)+"  </td> 																		"+
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
				  		"		<td width='3%' valign='top'> 11."+(i+1)+"  </td> 																		"+
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


    //PERIHALAP
	var PERIHALAP_MAIN_temp1_length=0;
	if(document.${formName}.PERIHALAP_MAIN_temp1 != null){
		if(document.${formName}.PERIHALAP_MAIN_temp1.length>0){
			PERIHALAP_MAIN_temp1_length = document.${formName}.PERIHALAP_MAIN_temp1.length;
		}else{
			PERIHALAP_MAIN_temp1_length = 1;
		}
	}
    if(PERIHALAP_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALAP_MAIN.length > 1 ){
		for (t = 0; t < PERIHALAP_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALAP_MAIN_temp1[t].value = document.${formName}.txtUlasanPERIHALAP_MAIN[t].value;
		}
	}else if(PERIHALAP_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALAP_MAIN.length < 1 ){
		for (t = 0; t < PERIHALAP_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALAP_MAIN_temp1[0].value = document.${formName}.txtUlasanPERIHALAP_MAIN.value;
    	}
	}else if(PERIHALAP_MAIN_temp1_length == 1){
		document.${formName}.PERIHALAP_MAIN_temp1.value = document.${formName}.txtUlasanPERIHALAP_MAIN.value;
	}


    //LATARBELAKANG
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


    //PERUNTUKAN
	var PERUNTUKAN_MAIN_temp1_length=0;
	if(document.${formName}.PERUNTUKAN_MAIN_temp1 != null){
		if(document.${formName}.PERUNTUKAN_MAIN_temp1.length>0){
			PERUNTUKAN_MAIN_temp1_length = document.${formName}.PERUNTUKAN_MAIN_temp1.length;
		}else{
			PERUNTUKAN_MAIN_temp1_length = 1;
		}
	}
    if(PERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERUNTUKAN_MAIN.length > 1 ){
		for (t = 0; t < PERUNTUKAN_MAIN_temp1_length; t++){	
    		document.${formName}.PERUNTUKAN_MAIN_temp1[t].value = document.${formName}.txtUlasanPERUNTUKAN_MAIN[t].value;
		}
	}else if(PERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERUNTUKAN_MAIN.length < 1 ){
		for (t = 0; t < PERUNTUKAN_MAIN_temp1_length; t++){	
    		document.${formName}.PERUNTUKAN_MAIN_temp1[0].value = document.${formName}.txtUlasanPERUNTUKAN_MAIN.value;
    	}
	}else if(PERUNTUKAN_MAIN_temp1_length == 1){
		document.${formName}.PERUNTUKAN_MAIN_temp1.value = document.${formName}.txtUlasanPERUNTUKAN_MAIN.value;
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

    
    //ULASANPENGARAH
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