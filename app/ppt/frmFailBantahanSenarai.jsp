  
<fieldset>
	<legend>Carian</legend>
    	<table  width="100%" cellspacing="4" cellpadding="0" border="0">
        	<tr>
            	<td width="8%">&nbsp;</td>
            	<td width="22%" align="right">No. Fail Permohonan</td>
              <td width="1%">:</td>
              <td width="69%"><input type="text" name="txtNoFail" id="txtNoFail" value="$!txtNoFail" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" size="50" /></td>
          </tr>  
            <tr>
            	<td width="8%">&nbsp;</td>
           	  <td align="right">Kementerian</td>
                <td>:</td>
              	<td>$selectKementerian</td>               
            </tr>
      	  	<tr>
      	    	<td>&nbsp;</td>
          		<td></td>
                <td></td>
                <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:search_data()" />
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onclick="javascript:kosongkan()" /></td>
          	</tr>                  
        </table>
</fieldset>
<br/>
<fieldset>
	<legend>Senarai Permohonan</legend>
    &nbsp;Jumlah Keseluruhan Fail : $JumlahFail<br>
	#set ($pagingTitle = "Jumlah Carian")
    #parse("app/utils/record_paging.jsp")
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
            <td scope="row" align="center" style="text-transform:uppercase">Bil</td>
            <td style="text-transform:uppercase">No Fail Permohonan</td>
            <td style="text-transform:uppercase">No.Fail PTG</td>
            <td style="text-transform:uppercase">Nama PB</td>
            <td style="text-transform:uppercase">No.Kes</td>
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
              <!--<td class="$row">$senarai.bil</td>-->    
              <td class="$row">
               #set ($cnt = ($page - 1) * $itemsPerPage + $counter )
               $senarai.bil
              </td>          
              <td class="$row">$senarai.no_fail</td>  
              <td class="$row">$senarai.no_rujukan_ptg</td> 
              <td class="$row">$senarai.nama_pb</td>
              <td class="$row">$senarai.no_kes</td>
          </tr>          
      #end   
      	 <tr>
          	<td colspan="7">&nbsp;</td>
          </tr>
   	  #else  
   		  <tr>
        	<td colspan="6">Tiada rekod</td>
          <tr>  
   	  #end  
      </table>

<input type="hidden" name="id_fail" />
<input type="hidden" name="id_permohonan"/>
<input type="hidden" name="id_status" /> 
 

</fieldset>

<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.socKementerian.value = "";
	document.${formName}.submit();
}

function search_data(){
	document.${formName}.command.value = "Cari";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmFailBantahanSenarai";
	document.${formName}.submit();
}

</script>