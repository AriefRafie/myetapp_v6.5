
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<center>
<fieldset>
  <legend><strong>Carian</strong></legend>
	<table width="100%" cellspacing="2" cellpadding="0" border="0">
   
    	 <tr>
    		<td width="20%">&nbsp;</td>
        	<td width="25%">No.Fail Permohonan / PTG / PTD / UPT</td>
            <td width="55%">:&nbsp;<input type="text" name="no_permohonan" value="$!carianPermohonan" style="text-transform:uppercase;" id="no_permohonan" size="35" onblur="this.value=this.value.toUpperCase();" /></td>
        </tr>
        
        <!-- <tr>
        	<td>&nbsp;</td>
        	<td>Tarikh Permohonan &nbsp; </td>
            <td>:&nbsp;<input name="tarikh_permohonan" id="tarikh_permohonan" size="11" type="text" value="$!carianTarikh" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_permohonan',false,'dmy');">$!frmtdate</td>
        </tr> -->
        
   		<!-- <tr>
        	<td>&nbsp;</td>
        	<td>Status &nbsp; </td>
            <td>:&nbsp;$!SelectStatusPPT</td>
        </tr> -->
        
        <tr>
        	<td>&nbsp;</td>
        	<td>Status Fail</td>
        	<td>:&nbsp;<select name="carianStatusFail" style="width:180px">
      			
      			#if($carianStatusFail=="0")
      			<option value="0">PENDAFTARAN</option>
      			<option value="">SILA PILIH</option>
      			<option value="1">TUNGGU PENGESAHAN</option>
				<option value="2">TELAH DISAHKAN</option>
      			#elseif($carianStatusFail=="1")
      			<option value="1">TUNGGU PENGESAHAN</option>
      			<option value="">SILA PILIH</option>
      			<option value="0">PENDAFTARAN</option>
				<option value="2">TELAH DISAHKAN</option>
      			#elseif($carianStatusFail=="2")
      			<option value="2">TELAH DISAHKAN</option>
      			<option value="">SILA PILIH</option>
      			<option value="0">PENDAFTARAN</option>
      			<option value="1">TUNGGU PENGESAHAN</option>
      			#else
      			<option value="">SILA PILIH</option>
      			<option value="0">PENDAFTARAN</option>
      			<option value="1">TUNGGU PENGESAHAN</option>
				<option value="2">TELAH DISAHKAN</option>
      			#end
  
			</select></td>
        </tr>
        
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>
            	&nbsp;&nbsp;<input name="cari" value="Cari" type="button" onclick="javascript:search_data()" />
  				<input type=button value = "Kosongkan" onClick="javascript:clearData();">
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
     		<td colspan="2" scope="row"><input type="button" name="cmdBaru" value ="Daftar Baru" onClick="javascript:baru();"></td>
    	</tr>
      
        <tr class="table_header">
        	<td align="center"><b>No</b></td>
        	<td><b>No.Fail Permohonan</b></td> 
        	<td><b>No.Fail PTG</b></td>
        	<td><b>No.Fail PTD</b></td>
        	<td><b>No.Fail UPT</b></td>
        	<!-- <td><b>Tarikh Permohonan</b></td> -->           
            <td><b>Kementerian</b></td>
           	<td><b>Status Fail</b></td> 
        </tr>
        
      #if($list_size!=0)
           	#foreach($list in $PermohonanListSPT)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		
         	#set($flagStatusOnline="")
            #if($list.FLAG_STATUS_ONLINE=="1")
            	 #set($flagStatusOnline="<br/><font style='font-size:10px'><b><i>(TELAH DITOLAK)</i></b></font>")
            #else
            	 #set($flagStatusOnline="")
            #end	
            	
        	<tr>
        		<td class="$row" align="center">$!list.bil</td>
        		<td class="$row"><a href="javascript:edit_item('$list.id_permohonan')"><font color="blue">$!list.no_fail</font></a></td>
            	<td class="$row"><a href="javascript:edit_item('$list.id_permohonan')"><font color="blue">$!list.no_rujukan_ptg</font></a></td>
            	<td class="$row"><a href="javascript:edit_item('$list.id_permohonan')"><font color="blue">$!list.no_rujukan_ptd</font></a></td>
            	<td class="$row"><a href="javascript:edit_item('$list.id_permohonan')"><font color="blue">$!list.no_rujukan_upt</font></a></td>
            	<!-- <td class="$row">$!list.tarikh_permohonan</td> -->
            	<td class="$row">$!list.nama_kementerian</td>
            	#if($list.id_status=="138")
            	<td class="$row"><font color="#3B9C9C"><b>PERMOHONAN ONLINE $!flagStatusOnline</b></font></td>
            	#else
            	<td class="$row">$!list.status_fail</td>
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
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="no_permohonan">
<input type="hidden" name="tarikh_permohonan">
<input type="hidden" name="status">
<input type="hidden" name="suburusan">

</center>

<script>
function edit_item(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function clearData() {
	doAjaxCall${formName}("clearData");
}
function baru() {
	document.${formName}.command.value = "baru";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function search_data(){

	doAjaxCall${formName}("cari");
	
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
