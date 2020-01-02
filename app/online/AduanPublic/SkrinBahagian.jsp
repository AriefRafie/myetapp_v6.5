#set($modeBahagian = $mode)
#set($showSkrinBahagian = "none")

#if($USER_ROLE == "wakil_bahagian_aduan" && ($view.ID_STATUS == "16126" || $view.ID_STATUS == "16127"))
	#if($view.ID_PEGAWAI_BAHAGIAN == $USER_ID_SYSTEM)
        #set($modeBahagian = "edit")
    	#set($showSkrinBahagian = "")
    #else   		
    <script>
	//alert('1');
	var alert_mesej_ui = '$alert_action_taken_by_bahagian_officer $view.NAMA_PEGAWAI_BAHAGIAN'+'!';
	alert(alert_mesej_ui.toUpperCase());
	document.getElementById('divDisplayAlert_$view.ID_ADUANPUBLIC').innerHTML = "<br><fieldset><div class='mq_css'><h3><font  style = 'color:red' ><b>"+alert_mesej_ui.toUpperCase()+"</b></font></h3></div></fieldset>";
	</script>   
    #end
    
#elseif($view.ID_STATUS == "16123" && $view.ID_PEGAWAI_BAHAGIAN != "") 
	<!-- KALO ADA MAKLUMAT BARU BUKAK BILA SELESAI -->
    #if(($USER_ROLE == "online" || $USER_ROLE == "php-online-user" || $USER_ROLE == "ppk-online-user" || $USER_ROLE == "ppt-online-user") 
&& $ID_SUMBERTINDAKAN != "16101" )  
	#set($modeBahagian = "$mode")
    #set($showSkrinBahagian = "none")
#else
	#set($modeBahagian = "view")
    #set($showSkrinBahagian = "")
#end 
    
#end   


<script>
document.getElementById('divSkrinBahagian_$view.ID_ADUANPUBLIC').style.display = "$showSkrinBahagian";
</script>

<fieldset > 
<legend>$legend_form_tindakan_bahagian</legend>
<table width="100%" border="0"  >
			<tr><td colspan="4" ></td></tr>
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%">
			</td>
			</tr>
            
            #if($modeBahagian == "view")
            #if($view.NAMA_PEGAWAI_BAHAGIAN != "")
            <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Pegawai
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                $view.NAMA_PEGAWAI_BAHAGIAN
				</td>
			</tr>
            #end
            #if($view.EMEL_PEGAWAI_BAHAGIAN != "")
            <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Emel Pegawai
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                $view.EMEL_PEGAWAI_BAHAGIAN
				</td>
			</tr>
            #end
            #if($view.TEL_PEGAWAI_BAHAGIAN != "")
            <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				No. Tel. Unit Pegawai
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                $view.TEL_PEGAWAI_BAHAGIAN
				</td>
			</tr>
            #end           
            #end
            
            <tr>
				<td valign="top" >
                #if($modeBahagian=="edit")
                <font color="red" >*</font>	
                #end	
				</td>			
				<td valign="top" >
				$label_keterangan_bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($modeBahagian=="edit")
				 <textarea style="text-transform:uppercase;" name="MAKLUMBALAS_BAHAGIAN_$view.ID_ADUANPUBLIC" 
				 id="MAKLUMBALAS_BAHAGIAN_$view.ID_ADUANPUBLIC" cols="50" rows="5" 
		 onKeyup="check_length(this,4000,'span_MAKLUMBALAS_BAHAGIAN_$view.ID_ADUANPUBLIC');" >$view.MAKLUMBALAS_BAHAGIAN</textarea>		 		
         <span id="span_MAKLUMBALAS_BAHAGIAN_$view.ID_ADUANPUBLIC"  ></span>
         		#else
                    #if($view.MAKLUMBALAS_BAHAGIAN != "")                 
                    	$view.MAKLUMBALAS_BAHAGIAN
                    #else 
                    	$label_blank 
                    #end
                <input type="hidden" name="MAKLUMBALAS_BAHAGIAN_$view.ID_ADUANPUBLIC" id="MAKLUMBALAS_BAHAGIAN_$view.ID_ADUANPUBLIC" value="$view.MAKLUMBALAS_BAHAGIAN"  />
                #end			
				</td>
			</tr>	
            
            
            <tr id="tr_ID_STATUS_BAHAGIAN_$view.ID_ADUANPUBLIC">
				<td valign="top"  >	
                #if($modeBahagian=="edit")
                <font color="red" >*</font>	
                #end			
				</td>			
				<td valign="top" >
				$label_status_aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id=""  >	
                #if($modeBahagian=="edit")	
                 <script>							
				  $jquery(document).ready(function () {
					 renameButtonHantar('BTNHANTAR$view.ID_ADUANPUBLIC','$view.ID_STATUS');	 	  
				  });	
				</script>		
				<select id="ID_STATUS_BAHAGIAN_$view.ID_ADUANPUBLIC" name="ID_STATUS_BAHAGIAN_$view.ID_ADUANPUBLIC" onchange="renameButtonHantar('BTNHANTAR$view.ID_ADUANPUBLIC',this.value);">	   
					   #foreach ( $N in $listStatus)		
							#set ( $selected_N = "" )
							#if($view.ID_STATUS==$N.ID_STATUS)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_STATUS" >
							$N.KETERANGAN
							</option>							
						#end
				</select>	
                #else
					<script>
                    document.getElementById('tr_ID_STATUS_BAHAGIAN_$view.ID_ADUANPUBLIC').style.display = "none";
                    </script>
                    <input type="hidden" name="ID_STATUS_BAHAGIAN_$view.ID_ADUANPUBLIC" id="ID_STATUS_BAHAGIAN_$view.ID_ADUANPUBLIC" value="$view.ID_STATUS"  />
                #end
				</td>
			</tr>
            
</table>
 #set($docType = "BAHAGIAN")   
    <div id="divLampiranBAHAGIAN_$view.ID_ADUANPUBLIC">
    <!--
    #if($view.ID_ADUANPUBLIC!="")    	
    #end
    -->
    #parse("app/online/AduanPublic/SkrinLampiran.jsp")
    </div>  
</fieldset>

