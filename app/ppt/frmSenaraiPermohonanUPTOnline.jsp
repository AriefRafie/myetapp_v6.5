#parse("app/ppt/HadAksesOnlinePPT.jsp")

#set( $countList = 0 )
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<center>
#parse("app/ppt/ListFailOnlineExpired.jsp")

<!-- Carian -->
#parse("app/ppt/frmCarianOnlinePPT.jsp")

<fieldset>
	<legend><strong>SENARAI PERMOHONAN</strong></legend>
	
	 #parse("app/utils/record_paging.jsp")
	 
     <table width="100%" cellspacing="2" cellpadding="2" border="0">  
   
   		
   		#if($id_jawatan_user == $layer1)
   		<tr>
     		<td colspan="2" scope="row"><input type="button" name="cmdDaftar" value ="Daftar Permohonan" onClick="javascript:pendaftaran();"></td>
    	</tr>
    	#end
    	
    	
        <tr class="table_header">
        	<td align="center" width="4%"><b>Bil</b></td>
        	<td><b>No.Rujukan <i>Online</i></b></td> 
        	<td><b>No.Fail Permohonan</b></td> 
        	<!-- <td><b>No.Fail PTG</b></td>
        	<td><b>No.Fail PTD</b></td>
        	<td><b>No.Fail UPT</b></td> -->   
        	<td><b>Nama Projek</b></td>   	
        	<td><b>Tarikh Permohonan Disahkan</b></td>
        	<td><b>Tarikh Permohonan KJP</b></td>
        	<td><b>Urusan</b></td>
            <td><b>Status Permohonan</b></td>
            <!-- <td><b>Kelengkapan Dokumen</b></td> -->
            <td><b>Catatan Penolakan</b></td>
        </tr>
      #if($list_size!=0)
         #foreach($list in $listPermohonan)
            #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         
			<!-- $!notifikasi -->
       		<tr>
        		<td class="$row" align="center">$list.bil</td>
        		<!-- <td class="$row"><a href="javascript:semakPendaftaran('$!list.id_permohonan')"><font color="blue">$!noOnline $flagStatusOnline</font></a></td>  -->
        		<td class="$row"><a href="javascript:semakPendaftaran('$!list.id_permohonan')"><font color="blue">$!list.no_permohonan_online</font></a></td> 
        		<td class="$row"><a href="javascript:semakPendaftaran('$!list.id_permohonan')"><font color="blue">$!list.no_fail</font></a></td> 
        		<!-- <td class="$row"><a href="javascript:semakPendaftaran('$!list.id_permohonan')"><font color="blue">$!list.no_rujukan_ptg</font></a></td>
        		<td class="$row"><a href="javascript:semakPendaftaran('$!list.id_permohonan')"><font color="blue">$!list.no_rujukan_ptd</font></a></td>  
        		<td class="$row"><a href="javascript:semakPendaftaran('$!list.id_permohonan')"><font color="blue">$!list.no_rujukan_upt</font></a></td>  -->       		 
            	<td class="$row">$list.tujuan&nbsp;&nbsp;
      				#if($!list.flag_semakan_online == '1')
      					<label style="background-color:blue" align="center" valign="top" > 
							<b><font color="WHITE" size="0.6em"><span class="blink">Menunggu Semakan</span></font></b>
						</label>
      				#elseif($!list.flag_semakan_online == '2')
						<label style="background-color:blue" align="center" valign="top" > 
							<b><font color="WHITE" size="0.6em"><span class="blink">Menunggu Pengesahan</span></font></b>
						</label>	      				
      				#elseif($!list.flag_semakan_online == '4')
						<label style="background-color:blue" align="center" valign="top" > 
							<b><font color="WHITE" size="0.6em"><span class="blink">Permohonan Dikembalikan</span></font></b>
						</label>
					#elseif($!list.flag_status_online == '1')
						<label style="background-color:blue" align="center" valign="top" > 
							<b><font color="WHITE" size="0.6em"><span class="blink">Permohonan Ditolak</span></font></b>
						</label>
					#end    
            	</td>
            	<td class="$row">$list.tarikh_permohonan</td>
		        <td class="$row">$list.tarikh_permohonan_kjp</td>
            	<td class="$row">$list.suburusan</td>
            	<td class="$row">$list.status</td>
            	<!-- <td class="$row">$list.status</td> -->
            	<td class="$row">
            	#if($list.catatan_status_online != "")
            	JKPTG Negeri : $list.catatan_status_online
            	#elseif($list.CATATANTOLAK_PELULUS != "")
            	Pelulus : $list.CATATANTOLAK_PELULUS
            	#elseif($list.CATATANTOLAK_PENYEMAK != "")
            	Penyemak : $list.CATATANTOLAK_PENYEMAK
            	#end
            	
            	
            	
            	</td>
        	</tr>
																
      
       		#end
    	#else
       	<tr>
       		<td colspan="6">Tiada rekod</td>
       	<tr>
    	#end
    </table> 
</fieldset>
<input type="hidden" name="id_permohonan">
<input type="hidden" name="nofail">
<input type="hidden" name="tarikh_permohonan">
<input type="hidden" name="tarikh_permohonan_kjp">
<input type="hidden" name="status">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

</center>

<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function pendaftaran() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "txtNoRujukanPTG";
	document.${formName}.command.value = "pendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function semakPendaftaran(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function clearData() {
	document.${formName}.command.value = "kosong";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function search_data(){
	document.${formName}.command.value = "cari";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
</script>
