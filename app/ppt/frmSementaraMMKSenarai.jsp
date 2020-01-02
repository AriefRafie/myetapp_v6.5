

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<center>
<fieldset>
  <legend><strong>Carian</strong></legend>
	<table width="100%" cellspacing="2" cellpadding="0" border="0">
   
    	 <tr>
    		<td width="20%">&nbsp;</td>
        	<td width="25%">No.Fail Permohonan / PTG / PTD / UPT</td>
            <td width="55%">:&nbsp;<input type="text" name="nofail" value="$!nofail" style="text-transform:uppercase;" id="nofail" size="35" onkeyup="this.value=this.value.toUpperCase();" /></td>
        </tr>
        
        <!-- <tr>
        	<td>&nbsp;</td>
        	<td>Tarikh Permohonan &nbsp; </td>
            <td>:&nbsp;<input name="tarikh_permohonan" id="tarikh_permohonan" size="11" type="text" value="$!carianTarikh" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_permohonan',false,'dmy');">&nbsp;$!frmtdate</td>
        </tr> -->
        
        
       	<tr>
      		<td>&nbsp;</td>
      		<td>Status &nbsp; </td>
      		<td>:&nbsp;<select name="carianStatus" class="autoselect">
      		
      			#if($carianStatus=="22")
      			<option value="22">MAKLUMAT JABATAN TEKNIKAL</option>
      			<option value="147">PENYEDIAAN LAPORAN AWAL</option>
      			<option value="43">JPPH / JPBD</option>	
				<option value="132">PENYEDIAAN MMK</option>
				<option value="133">SEMAKAN MMK</option>
				<option value="134">KEPUTUSAN MMK</option>
      			<option value="26">MAKLUMAT MMK</option>
      			<option value="">SILA PILIH</option>
      			#elseif($carianStatus=="132")
      			<option value="132">PENYEDIAAN MMK</option>
      			<option value="147">PENYEDIAAN LAPORAN AWAL</option>
      			<option value="43">JPPH / JPBD</option>	
				<option value="22">MAKLUMAT JABATAN TEKNIKAL</option>
				<option value="133">SEMAKAN MMK</option>
				<option value="134">KEPUTUSAN MMK</option>
      			<option value="26">MAKLUMAT MMK</option>
      			<option value="">SILA PILIH</option>
      			#elseif($carianStatus=="133")
      			<option value="133">SEMAKAN MMK</option>
      			<option value="147">PENYEDIAAN LAPORAN AWAL</option>
      			<option value="43">JPPH / JPBD</option>	
				<option value="22">MAKLUMAT JABATAN TEKNIKAL</option>
				<option value="132">PENYEDIAAN MMK</option>
				<option value="134">KEPUTUSAN MMK</option>
      			<option value="26">MAKLUMAT MMK</option>
      			<option value="">SILA PILIH</option>
      			#elseif($carianStatus=="134")
      			<option value="134">KEPUTUSAN MMK</option>
      			<option value="147">PENYEDIAAN LAPORAN AWAL</option>
      			<option value="43">JPPH / JPBD</option>	
				<option value="22">MAKLUMAT JABATAN TEKNIKAL</option>
				<option value="132">PENYEDIAAN MMK</option>
				<option value="133">SEMAKAN MMK</option>				
      			<option value="26">MAKLUMAT MMK</option>
      			<option value="">SILA PILIH</option>
      			#elseif($carianStatus=="26")
      			<option value="26">MAKLUMAT MMK</option>
      			<option value="147">PENYEDIAAN LAPORAN AWAL</option>
      			<option value="43">JPPH / JPBD</option>	
				<option value="22">MAKLUMAT JABATAN TEKNIKAL</option>
				<option value="132">PENYEDIAAN MMK</option>
				<option value="133">SEMAKAN MMK</option>
				<option value="134">KEPUTUSAN MMK</option>  
				<option value="">SILA PILIH</option>
				#elseif($carianStatus=="147")
				<option value="147">PENYEDIAAN LAPORAN AWAL</option>
				<option value="43">JPPH / JPBD</option>	
				<option value="22">MAKLUMAT JABATAN TEKNIKAL</option>
				<option value="132">PENYEDIAAN MMK</option>
				<option value="133">SEMAKAN MMK</option>
				<option value="134">KEPUTUSAN MMK</option>   	
				<option value="26">MAKLUMAT MMK</option>	
				<option value="">SILA PILIH</option>	
      			#elseif($carianStatus=="43")
      			<option value="43">JPPH / JPBD</option>	
				<option value="147">PENYEDIAAN LAPORAN AWAL</option>
				<option value="22">MAKLUMAT JABATAN TEKNIKAL</option>
				<option value="132">PENYEDIAAN MMK</option>
				<option value="133">SEMAKAN MMK</option>
				<option value="134">KEPUTUSAN MMK</option>   	
				<option value="26">MAKLUMAT MMK</option>	
				<option value="">SILA PILIH</option>	
      			#else
      			<option value="">SILA PILIH</option>
      			<option value="147">PENYEDIAAN LAPORAN AWAL</option>
      			<option value="43">JPPH / JPBD</option>	
				<option value="22">MAKLUMAT JABATAN TEKNIKAL</option>
				<option value="132">PENYEDIAAN MMK</option>
				<option value="133">SEMAKAN MMK</option>
				<option value="134">KEPUTUSAN MMK</option>
      			<option value="26">MAKLUMAT MMK</option>
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
   
        <tr class="table_header">
        	<td align="center"><b>No</b></td>
        	<td><b>No.Fail Permohonan</b></td> 
        	<td><b>No.Fail PTG</b></td>
        	<td><b>No.Fail PTD</b></td>
        	<td><b>No.Fail UPT</b></td>
        	<td><b>Kementerian</b></td>
        	<td><b>Status MMK</b></td>
            <td><b>Status Permohonan</b></td> 
        </tr>
        
      #if($list_size!=0)
           	#foreach($list in $listPermohonan)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
        	<tr>
        		<td class="$row" align="center">$list.bil</td>
        		<td class="$row"><a href="javascript:viewMMK('$!list.id_permohonan')"><font color="blue">$!list.no_fail</font></a></td> 
        		<td class="$row"><a href="javascript:viewMMK('$!list.id_permohonan')"><font color="blue">$!list.no_rujukan_ptg</font></a></td> 
        		<td class="$row"><a href="javascript:viewMMK('$!list.id_permohonan')"><font color="blue">$!list.no_rujukan_ptd</font></a></td> 
        		<td class="$row"><a href="javascript:viewMMK('$!list.id_permohonan')"><font color="blue">$!list.no_rujukan_upt</font></a></td> 
        		<td class="$row">$list.nama_kementerian</td>
        		<td class="$row">$list.flag_semakan_pengarah</td>
            	<td class="$row">$list.status</td>
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
<input type="hidden" name="nofail">
<input type="hidden" name="tarikh_permohonan">
<input type="hidden" name="status">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

</center>

<script>
function viewMMK(id_permohonan) {

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewMMK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraMMK";
	document.${formName}.submit();
}
function clearData() {
	doAjaxCall${formName}("clearData");
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
