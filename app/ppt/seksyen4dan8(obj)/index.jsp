
<center>
<fieldset>
  <legend><strong>Carian</strong></legend>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
   
    	 <tr>
    		<td width="19%">&nbsp;</td>
        	<td width="25%">No.Fail Permohonan / PTG / PTD / UPT</td>
        	<td width="1%">:</td>
            <td width="55%">
            	<input type="text" name="txtNoFail" value="$!txtNoFail" style="text-transform:uppercase;" id="txtNoFail" size="35" onblur="this.value=this.value.toUpperCase();" />
            </td>
        </tr>
 
        <tr>
        	<td>&nbsp;</td>
        	<td>Status Fail</td>
        	<td>:</td>
        	<td>
	        	<select name="socStatusFail" style="width:180px">
	      			<option value="" #if($socStatusFail=="") selected=selected #end >SILA PILIH</option>
	      			<option value="11" #if($socStatusFail=='11') selected=selected #end >PERMOHONAN CAWANGAN</option>
					<option value="127" #if($socStatusFail=='127') selected=selected #end >DISAHKAN JKPTG</option>
	  			</select>
  			</td>
        </tr>
        
        <tr>
        	<td colspan="3">&nbsp;</td>
        	<td>
            	<input name="cari" value="Cari" type="button" onclick="javascript:searchData()" />
  				<input name="kosong" type="button" value="Kosongkan" onClick="javascript:clearData();">
            </td>
        </tr>
        
    </table>
</fieldset>

<br/>

<fieldset>
	<legend><strong>Senarai Permohonan</strong></legend>
	
	 #parse("app/utils/record_paging.jsp")
	 
     <table width="100%" cellspacing="2" cellpadding="2" border="0">  
     
     	<tr>
     		<td colspan="2" scope="row"><input type="button" name="cmdBaru" value ="Daftar Baru" onClick="javascript:daftarBaru();"></td>
    	</tr>
      
        <tr class="table_header">
        	<td align="center"><b>No</b></td>
        	<td><b>No.Fail Permohonan</b></td> 
        	<td><b>No.Fail PTG</b></td>
        	<td><b>No.Fail PTD</b></td>
        	<td><b>No.Fail UPT</b></td>       
            <td><b>Kementerian</b></td>
           	<td><b>Status Fail</b></td> 
        </tr>
        
      #set($bil=0)
      #if($lists.size()!=0)
           	#foreach($list in $lists)
           		#set ($bil = $bil + 1)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		
         	#set($flagStatusOnline="")
            #if($list.getFlagStatusOnline=="1")
            	 #set($flagStatusOnline="<br/><font style='font-size:10px'><b><i>(TELAH DITOLAK)</i></b></font>")
            #else
            	 #set($flagStatusOnline="")
            #end	
            	
        	<tr>
        		<td class="$row" align="center">$!bil</td>
        		<td class="$row"><a href="javascript:edit_item('$!list.getIdPermohonan()')"><font color="blue">$!list.getPfdFail().getNoFail()</font></a></td>
            	<td class="$row"><a href="javascript:edit_item('$!list.getIdPermohonan()')"><font color="blue">$!list.getNoRujukanPTG()</font></a></td>
            	<td class="$row"><a href="javascript:edit_item('$!list.getIdPermohonan()')"><font color="blue">$!list.getNoRujukanPTD()</font></a></td>
            	<td class="$row"><a href="javascript:edit_item('$!list.getIdPermohonan()')"><font color="blue">$!list.getNoRujukanUPT()</font></a></td>
            	<td class="$row">$!list.getPfdFail().getKementerian().namaKementerian</td>
            	#if($list.getIdStatus()=="138")
            	<td class="$row"><font color="#3B9C9C"><b>PERMOHONAN ONLINE $!flagStatusOnline</b></font></td>
            	#else
            	<td class="$row">$!list.getRujStatus().getKeterangan()</td>
            	#end
        	</tr>
        	#end
     #else
        	<tr>
        		<td colspan="6">Tiada rekod</td>
        	<tr>
     #end
        
    </table> 
</fieldset>
</center>

<script>
function searchData(){
	doAjaxCall${formName}("elsefunction","flagCarian=Y");
}
function clearData(){
	doAjaxCall${formName}("elsefunction","flagCarian=");
}
function daftarBaru(){
	document.${formName}.command.value = "daftarBaru";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.seksyen4dan8.PermohonanSeksyen4dan8Module";
	document.${formName}.submit();
}

</script>
