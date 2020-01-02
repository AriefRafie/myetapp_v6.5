<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>

	<fieldset><legend><b>Carian</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">No Fail : </td>
      			<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$!txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row" align="right">Nama Pemohon : </td>
      			<td><input name="txtPemohon" id="txtPemohon" type="text" value="$!txtPemohon" size="50" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
   			</tr>
   			<tr>
      			<td scope="row" align="right">Nama Simati : </td>
      			<td><input name="txtSimati" id="txtSimati" type="text" value="$!txtSimati" size="50" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row" align="right">No.KP Simati : </td>
      			<td ><select name="jenisIc" class="autoselect">
      				<option value="0">Sila Pilih</option>
      				<option value="1">No. KP Baru</option>
      				<option value="2">No. KP Lama</option>
      				<option value="3">No. KP Lain</option>
      			</select>&nbsp;&nbsp;<input name="txtIcSimati" id="txtIcSimati" value="$!txtIcSimati" type="text" size="20" maxlength="14" style="text-transform:uppercase;" /></td>
    		</tr>

      			<td scope="row"></td>
   			  <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:search_data()">
   			    <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onclick="javascript:kosongkan()" /></td>
    		</tr>
    		
		</table>
		
	</fieldset>
	
<br/>

	<fieldset>
	<legend><strong>Senarai Fail Keputusan Perbicaraan</strong></legend>
		#parse("app/utils/record_paging.jsp")
        
        <table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
      			<td scope="row" align="center" style="text-transform:uppercase">Bil</td>
      			<td style="text-transform:uppercase">No Fail</td>
      			<td style="text-transform:uppercase">Nama Simati</td>
      			<td style="text-transform:uppercase">Tarikh Mohon</td>
      			<td style="text-transform:uppercase">Status Fail</td>
    		</tr>         		
#if( $list != 0 )
          #set ($list = "")
          #set ($counter = 0)
          #foreach ($list in $listNotis)
          #set( $counter = $velocityCount - 1 )
          	#if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end

         	<tr>
               <!-- <td class="$row" align="center">$list.bil</td>  -->     
                <td class="$row">
                    #set ($cnt = ($page - 1) * $itemsPerPage + $counter )
                    $list.bil
                </td>                                      
        		<td class="$row"><a href="javascript:papar('$list.id_Permohonan','$list.id_simati','$list.id_status')" class="style1">$list.no_Fail</a></td>               
                <td class="$row">$list.namasimati.toUpperCase()</td>
            	<td class="$row">$list.tarikhmohon</td>
            	<td class="$row">$list.keterangan</td>
        	</tr>	
           #end
           
        #else   	
    		<tr>
    			<td colspan="5">Tiada Rekod</td>
    		</tr>
    	#end 
    		
		</table>
	</fieldset>	
	<input type="hidden" name="idpermohonan"/>
    <input type="hidden" name="idSimati" />
    <input type="hidden" name="id_status" />    


<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function search_data(){
	document.${formName}.command.value = "Cari";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.submit();
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtPemohon.value = "";
	document.${formName}.txtSimati.value = "";
	document.${formName}.jenisIc.value = "";
	document.${formName}.txtIcSimati.value = "";
	document.${formName}.submit();
}

function papar(idpermohonan,id2,id_status) {

//notis
	if (id_status == '18'){
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.idSimati.value = id2;
		document.${formName}.id_status.value = id_status;
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "papar_notis";
		document.${formName}.submit();
	
	}else 
	
//selesai	
	if (id_status == '41'){
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.idSimati.value = id2;
		document.${formName}.id_status.value = id_status;
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";		
		document.${formName}.command.value = "papar_selesai";
		document.${formName}.submit();
	
	}else 

//tangguh
	if (id_status == '44' ){
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.idSimati.value = id2;
		document.${formName}.id_status.value = id_status;
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "papar_tangguh";
		document.${formName}.submit();

	}else  

//batal	
	if (id_status == '47' ){
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.idSimati.value = id2;
		document.${formName}.id_status.value = id_status;
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "papar_batal";
		document.${formName}.submit();

	}else 
	
	if (id_status == '172' || id_status == '173' ){
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.idSimati.value = id2;
		document.${formName}.id_status.value = id_status;
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "papar_selesai_kolateral";
		document.${formName}.submit();
		
	}else 

	if (id_status == '174' || id_status == '175' ){
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.idSimati.value = id2;
		document.${formName}.id_status.value = id_status;
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "papar_selesai_rujukanMT";
		document.${formName}.submit();
		
	}else 
	
	if (id_status == '176' || id_status == '177' ){
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.idSimati.value = id2;
		document.${formName}.id_status.value = id_status;
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "papar_selesai_rujukanROTS";
		document.${formName}.submit();
		
	}else{
		alert("Sila Hubungi Sistem Admin")
	}

	document.${formName}.method="POST";
	document.${formName}.submit();
}

</script>