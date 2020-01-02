   
<fieldset>
	<legend>Carian</legend>
    	<table  width="100%" cellspacing="4" cellpadding="0" border="0">
        	<tr>
            	<td width="7%">&nbsp;</td>
           	<td width="21%" align="right">No. Fail Permohonan</td>
              	<td width="1%">:</td>
              	<td width="71%"><input type="text" name="txtNoFail" id="txtNoFail" value="$!txtNoFail" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" size="50" /></td>
          </tr>  
            <tr>
            	<td width="7%">&nbsp;</td>
           	<td align="right">Kementerian</td>
                <td>:</td>
              	<td>$selectKementerian</td>               
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
            <td align="center" style="text-transform:uppercase" scope="row">Bil</td>
          <td style="text-transform:uppercase">No Fail Permohonan</td>
          <td style="text-transform:uppercase">No Fail PTG</td>
          <td style="text-transform:uppercase">kementerian</td>
          <!--<td style="text-transform:uppercase">Status Fail</td>-->
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
              <td class="$row"><a href="javascript:papar('$senarai.id_fail','$senarai.id_permohonan','$senarai.id_status')"><font color="blue">$senarai.no_fail</font></a></td>  
              <td class="$row"><a href="javascript:papar('$senarai.id_fail','$senarai.id_permohonan','$senarai.id_status')"><font color="blue">$senarai.no_rujukan_ptg</font></a></td>  
              <td class="$row">$senarai.nama_kementerian</td>
              <!--<td class="$row">$senarai.keterangan</td>-->
          </tr>          
      #end   
      	 <tr>
          	<td colspan="5">&nbsp;</td>
          </tr>
   	  #else  
   		  <tr>
        	<td colspan="5">Tiada rekod</td>
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

function papar(id_fail,id_permohonan,id_status) {

		document.${formName}.id_fail.value = id_fail;
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.id_status.value = id_status;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	
	if ((id_status == '184' ) || (id_status == '186') || (id_status == '187') ) {		
		document.${formName}.command.value = "UrusanPampasan";	
	}	
	else{
		//alert("Sila Hubungi Sistem Admin")
		document.${formName}.command.value = "UrusanPampasan";	
	}

	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();	
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.socKementerian.value = "";
	document.${formName}.submit();
}

function search_data(){
	document.${formName}.command.value = "Cari";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}
</script>