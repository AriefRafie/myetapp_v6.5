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
              	<td>$socKementerian</td>               
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
    #parse("app/utils/record_paging.jsp")
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
            <td scope="row" align="center" style="text-transform:uppercase">Bil</td>
            <td style="text-transform:uppercase">No Fail Permohonan</td>
            <td style="text-transform:uppercase">No Fail PTG</td>
            <td style="text-transform:uppercase">kementerian</td>
            <td style="text-transform:uppercase">Status Fail</td>
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
              <td class="$row" >$senarai.bil</td>
              <td class="$row">$senarai.no_fail</font></a></td>  
              <td class="$row">$senarai.no_rujukan_ptg</a></td>  
              <td class="$row">$senarai.nama_kementerian</td>
              <td class="$row">$senarai.keterangan</td>
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

function papar_pb(id_fail,id_permohonan,id_status) {

	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;

	document.${formName}.command.value = "papar_pb";
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmRayuanOnline";
	document.${formName}.submit();
}

function papar(id_fail,id_permohonan,id_status) {
		document.${formName}.id_fail.value = id_fail;
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.id_status.value = id_status;		
	
	if (id_status == '72' ){
		document.${formName}.command.value = "DaftarBantahanAP";
		document.${formName}.submit();		
	}
	else if (id_status == '199' ){
		document.${formName}.command.value = "dalamProses";
		document.${formName}.submit();	
	}
	else if (id_status == '200' ){
		document.${formName}.command.value = "urusanDeposit";
		document.${formName}.submit();	
	}	
	else if (id_status == '201' ){
		document.${formName}.command.value = "susulanBantahan";
		document.${formName}.submit();	
	}	
	else if (id_status == '202') {
		document.${formName}.command.value = "tarikBalikBantahan";
		document.${formName}.submit();	
	}	
	else if ((id_status == '203' ) || (id_status == '204')) {
		document.${formName}.command.value = "susulanBantahan";
		document.${formName}.submit();	
	}	
	else if (id_status == '1610249') {
		document.${formName}.command.value = "borangO";
		document.${formName}.submit();	
	}	
	else{
		alert("Sila Hubungi Sistem Admin")
	}

	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmRayuanOnline";
	/*document.${formName}.submit();*/	
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.socKementerian.value = "";
	document.${formName}.submit();
}

function search_data(){
	document.${formName}.command.value = "Cari";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmRayuanOnline";
	document.${formName}.submit();
}

</script>