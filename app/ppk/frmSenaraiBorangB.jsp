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




 <input type="hidden" name="idPermohonan" />
             <input type="hidden" name="idPermohonanSimati"/>
             <input type="hidden" name="idStatus" />
             
             <!-- shaz -->
             <input type="hidden" name="id_permohonan" />
             <input type="hidden" name="id_status" />
             
             <!-- elly -->
             <input type="hidden" name="idpermohonan" />
             <input type="hidden" name="id_Simati" />
             <input type="hidden" name="id_fail" />
             
             <!-- razman -->
			 <input type="hidden" name="idpermohonansimati" />
			 <input type="hidden" name="tarikh_mohon" />
             <input type="hidden" name="idSimati" />
             
             <!-- kak ayu -->
             <input type="hidden" name="idsimati" />
             
             
             <input type="hidden" name="flagAdvSearch" value="$flagAdvSearch"/>
             <input type="hidden" name="flagFromSenaraiPermohonanSek8"/>


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
		<legend><b>Senarai Fail</b></legend>
        ** Fail-fail permohonan dimana maklumat keputusan permohonan tidak dimasukan sedangkan fail permohonan ini telah cukup tempoh 30 hari daripada tarikh Borang B dicetak		
		#set ($pagingTitle = "Jumlah Carian")
		#parse("app/utils/record_paging.jsp")
        <table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
              <td width="45%"><strong>No Fail</strong></td>
              <td width="40%"><strong>Nama Simati</strong></td>
             
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
            
          
            <a href="javascript:  paparBorangB('$list.ID_PERMOHONAN','$list.ID_SIMATI','$list.SEKSYEN','$list.ID_PERMOHONANSIMATI','$list.TARIKH_MOHON')" class="style1">$list.NO_FAIL</a>
            #end
            </td>
            <td class="$row">$list.NAMA_SIMATI.toUpperCase()</td>
           
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

function paparBorangB(idPermohonan,idSimati,seksyen,idpermohonansimati,tarikhMohon)
{

	if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
			}
				document.${formName}.idPermohonan.value = idPermohonan;
				document.${formName}.idpermohonansimati.value = idpermohonansimati;
				document.${formName}.idSimati.value = idSimati;
				document.${formName}.tarikh_mohon.value = tarikhMohon;
	
	document.${formName}.method="POST";
	
	document.${formName}.submit();
	
}

function carian(){
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiBorangB";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.myIdSimati.value = "";
	document.${formName}.noFail.value = "";	
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiBorangB";
	document.${formName}.submit();
}

</script>