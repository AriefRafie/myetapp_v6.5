<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #0000FF}
.style5 {
	color: #0000FF;
	font-style: italic;
	font-size: 9px;
}
-->
</style>


<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <fieldset><legend><b>Carian</b></legend>
        <table width="100%" align="center" border="0">
  
         
           <tr>
              <td width="30%" height="24" scope="row" align="right">MyID Simati : </td>
              <td width="70%">
                <input name="myIdSimati" id="myIdSimati" type="text" value="$myIdSimati" size="50" maxlength="14" style="text-transform:uppercase;" ></td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right">No Fail : </td>
              <td width="70%"><input name="noFail" id="noFail" type="text" value="$noFail" size="50" maxlength="50" style="text-transform:uppercase;" > 
            </td>
            </tr>
            <tr>
              <td scope="row"></td>
               <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()"></td>
              <!-- <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onClick="if(validateCarian()==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','');}">
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="$jquery('#carianTerperinci').val('');doDivAjaxCall$formname('div_senaraiUtama','carianUtama','carianTerperinci=');"></td> -->
            </tr>
            <tr>
              <td scope="row">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </tbody>
        </table>
	  </fieldset>
    </td>
  </tr>
  <tr>
    <td><fieldset>
		<legend><b>Senarai fail yang telah dibuat pembetulan perintah</b></legend>
		
#parse("app/ppk/perintah/record_pagingSEP.jsp")

 <table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
              <td width="25%"><strong>No Fail</strong></td>
              <td width="30%"><strong>Nama Simati</strong></td>
               <td width="10%"><strong>Tarikh Mohon</strong></td>
               <td width="10%"><strong>Status Fail</strong></td>
               <td width="20%"><strong>Pegawai yang dibenarkan kemaskini fail</strong></td>
             
            </tr>
          #set ($list = "")
          #set ($counter = 0)
          #foreach ($list in $SenaraiFail)
          #set( $counter = $velocityCount - 1 )
          	#if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
            
            <!--#set( $counter = $velocityCount )
			#if ( ($counter % 2) == 0 )
				#set( $row = "row2" )
			#else
				#set( $row = "row1" )
			#end-->
          <tr>
            <td class="$row">
			#set ($cnt = ($page - 1) * $itemsPerPage + $counter )
            $cnt
			</td>
            <td class="$row">
            #if($list.idPermohonan == '')
            $list.noFail
            #else
               <a href="javascript:paparFail($list.ID_FAIL, $list.ID_PERMOHONAN,$list.ID_PERMOHONANSIMATI)" class="style1" > $list.NO_FAIL</a>
            #end
            </td>
            <td class="$row">$list.NAMA_SIMATI.toUpperCase()
            
             	<input type="hidden" name="id_fail_carian" id="id_fail_carian" value="$list.ID_FAIL" />
         		<input type="hidden" name="seksyen" id="seksyen" value="$list.SEKSYEN" />
         		<input type="hidden" name="idPermohonan" id="idPermohonan" value="$list.ID_PERMOHONAN" />
         		<input type="hidden" name="idPermohonanSimati" id="idPermohonanSimati" value="$list.ID_PERMOHONAN" />
         		<input type="hidden" name="idStatus" id="idStatus" value="$list.ID_STATUS" />
            </td>
            <td class="$row">$list.TARIKH_MOHON</td>
            <td class="$row">$list.NAMA_STATUS</td>
            <td class="$row">$list.USER_NAME</td>
            </tr>
          #end
          
           #if($counter == 0)
            <tr>
            <td></td>
            <td  colspan="10">
           Tiada rekod
           </td>
           </tr>
           #end 
          
         
        </table>
        
        
		</fieldset>
	</td>
  </tr>
</table>






<script>

function paparFail(id_fail,idPermohonan,idPermohonanSimati)
{
      
/* document.${formName}.id_fail_carian.value = id_fail;
	//document.${formName}.action = "";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView";
	document.${formName}.command.value = "paparSub";
		document.${formName}.submit(); */
/* 	var seksyen = $jquery('#seksyen').val();
		alert(seksyen);
		
	if (seksyen == '8'){
		alert("masukkk");
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
	}else{
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
	}
	 */
	 
	//alert("oooo="+document.${formName}.idPermohonan.value);
	document.${formName}.idPermohonan.value = idPermohonan;
	document.${formName}.idPermohonanSimati.value = idPermohonanSimati;
	document.${formName}.idStatus.value = idStatus;
	
	
	
	document.${formName}.id_fail_carian.value = id_fail;
	//document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar&idPermohonan="+idPermohonan+"&idPermohonanSimati="+idPermohonanSimati;
	document.${formName}.command.value = "paparSub";
	document.${formName}.submit();
}


function carian(){
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiEditPerintah";
	document.${formName}.command.value = "carianUtama";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.myIdSimati.value = "";
	document.${formName}.noFail.value = "";	
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiEditPerintah";
	document.${formName}.submit();
}

</script>