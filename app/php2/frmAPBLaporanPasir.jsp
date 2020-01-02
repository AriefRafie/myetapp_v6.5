    
<fieldset>
	<legend>Carian</legend>
    	<table  width="100%" cellspacing="4" cellpadding="0" border="0">
        	<tr>
            	<td width="8%">&nbsp;</td>
            	<td width="22%" align="right">Nama Pelesen</td>
              <td width="1%">:</td>
              <td width="69%"><input type="text" name="txtNamaPelesen" id="txtNamaPelesen" value="$!txtNamaPelesen" style="text-transform:uppercase;"  size="50" /></td>
          </tr>  
            <tr>
            	<td width="8%">&nbsp;</td>
           	  <td align="right">No. Lesen</td>
                <td>:</td>
              	<td><input type="text" name="txtNoLesen" id="txtNoLesen" value="$!txtNoLesen" style="text-transform:uppercase;"  size="20" /></td>               
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
	<legend>Senarai Maklumat Pelesen</legend>
    
     #parse("app/utils/record_paging.jsp")
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
            <td width="6%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
            <td width="54%" style="text-transform:uppercase">Nama Pelesen</td>
            <td width="40%" style="text-transform:uppercase">No Lesen</td>
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
              <td class="$row" align="center">$senarai.bil</td>            
              <td class="$row"><a href="javascript:papar_pelesen('$senarai.id_jadualkedualesenAPB')"><font color="blue">$senarai.nama</font></a></td>  
              <td class="$row">$senarai.no_siri_lesen</td>
          </tr>          
      	#end   
        
   #else
          <tr>
              <td colspan="6">Tiada rekod</td>
          <tr>
   #end
   
      </table>

<input type="hidden" name="id_jadualkedualesenAPB" />
<input type="hidden" name="actionLaporanPasir" id="actionLaporanPasir" value="$actionLaporanPasir"/>
      
</fieldset>

<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function papar_pelesen(id_jadualkedualesenAPB) {
	document.${formName}.id_jadualkedualesenAPB.value = id_jadualkedualesenAPB;
	document.${formName}.actionLaporanPasir.value = "papar_pelesen";
	document.${formName}.submit();
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNamaPelesen.value = "";
	document.${formName}.txtNoLesen.value = "";
	document.${formName}.submit();
}

function search_data(){

	document.${formName}.actionLaporanPasir.value = "";
	document.${formName}.submit();
	
}

</script>