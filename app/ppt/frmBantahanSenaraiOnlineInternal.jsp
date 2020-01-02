    
<fieldset>
	<legend>Carian</legend>
    	<table  width="100%" cellspacing="4" cellpadding="0" border="0">
        	<tr>
            	<td width="8%">&nbsp;</td>
            	<td width="20%" align="right">No. Fail Permohonan</td>
              <td width="1%">:</td>
              <td width="71%"><input type="text" name="txtNoFail" id="txtNoFail" value="$!txtNoFail" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" size="50" /></td>
          </tr>  
            <tr>
            	<td width="8%">&nbsp;</td>
           	  <td align="right">Tarikh Permohonan</td>
                <td>:</td>
              	<td><input type="text" name="tarikh_permohonan" id="tarikh_permohonan" value="$!tarikh_permohonan" size="9"  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
                  <a href="javascript:displayDatePicker('tarikh_permohonan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span></td>               
            </tr>
      	  	<tr>
      	    	<td>&nbsp;</td>
          		<td></td>
                <td></td>
                <td><input name="cmdCari" value="Cari" type="button" onclick="javascript:search_data();" />
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onclick="javascript:kosongkan()" /></td>
          	</tr>                  
        </table>
</fieldset>
<br/>
<fieldset>
	<legend>Senarai Permohonan</legend>
    #parse("app/utils/record_paging.jsp")
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
            <td width="4%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
            <td style="text-transform:uppercase">No Fail Permohonan</td>
            <td style="text-transform:uppercase">No Fail PTG</td>
            <td style="text-transform:uppercase">No LOT/PT</td>
            <td style="text-transform:uppercase">Tarikh Permohonan</td>
            <td style="text-transform:uppercase">Nama Pembantah</td>
            <td style="text-transform:uppercase">No KP</td>
        </tr>
  #if($list_size!=0)     
       #foreach($senarai in $PermohonanList)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
          	
          <tr valign="top">
              <td class="$row" ><div align="center">$senarai.bil</div></td>
              <td class="$row"><a href="javascript:viewBorangN('$senarai.id_fail','$senarai.id_permohonan','$senarai.id_status','$senarai.id_bantahan','$senarai.id_hakmilik','$senarai.id_hakmilikpb')"><font color="blue">$senarai.no_fail</font></a></td> 
              <td class="$row"><a href="javascript:viewBorangN('$senarai.id_fail','$senarai.id_permohonan','$senarai.id_status','$senarai.id_bantahan','$senarai.id_hakmilik','$senarai.id_hakmilikpb')"><font color="blue">$senarai.no_rujukan_ptg</font></a></td>  
              <td class="$row"><a href="javascript:viewBorangN('$senarai.id_fail','$senarai.id_permohonan','$senarai.id_status','$senarai.id_bantahan','$senarai.id_hakmilik','$senarai.id_hakmilikpb')"><font color="blue">$senarai.no_lotpt</font></a></td>
              <td class="$row">$senarai.tarikh_permohonan</td>
              <td class="$row">$senarai.nama_pb</td>
              <td class="$row">$senarai.no_pb</td>
          </tr>          
      #end   
      	 <tr>
          	<td colspan="10">&nbsp;</td>
          </tr>
   	  #else  
   		  <tr>
        	<td colspan="9">Tiada rekod</td>
          <tr>  
   	  #end  
      </table>

<input type="hidden" name="id_fail" />
<input type="hidden" name="id_permohonan"/>
<input type="hidden" name="id_status" /> 
<input type="hidden" name="id_bantahan" /> 
<input type="hidden" name="id_hakmilik" />
<input type="hidden" name="id_hakmilikpb" />
      
</fieldset>

<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function viewBorangN(id_fail,id_permohonan,id_status,id_bantahan,id_hakmilik,id_hakmilikpb){

	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	
	document.${formName}.command.value = "viewBorangN";
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiOnline";
	document.${formName}.submit();	
	
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.tarikh_permohonan.value = "";
	document.${formName}.submit();
}

function search_data(){
	document.${formName}.command.value = "Cari";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiOnline";
	document.${formName}.submit();
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
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