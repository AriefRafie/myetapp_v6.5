<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <fieldset>
        <legend><b>Semakan Permohonan</b></legend>
        <table width="100%" align="center" border="0">
          <tbody>
            <tr>
              <td width="30%" height="24" scope="row" align="right">No Fail : </td>
              <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="30" maxlength="50" style="text-transform:uppercase;" >
         <!-- peje -->
         <input type="hidden" name="idPermohonan"/>
         <input type="hidden" name="idPermohonanSimati"/>
         <input type="hidden" name="idStatus"/>
         <input type="hidden" name="flagFromSenaraiFailSek8"/>
         
         <!-- elly -->
         <input type="hidden" name="id_perbicaraan" value="$id_perbicaraan"/>
         <input type="hidden" name="idpermohonan"/>
         <input type="hidden" name="idpermohonansimati"/>
         <input type="hidden" name="tarikh_mohon" />
         <input type="hidden" name="id_status"/>
         <input type="hidden" name="command"/>
         <input type="hidden" name="id_Simati"/>
         
         <!-- shah -->
		<input type="hidden" name="id_permohonan"/>
		
		 <!-- man -->
		 <input type="hidden" name="idSimati"/>
		 
         </td>
            </tr>
            <tr>
              <td scope="row"></td>
              <td><input name="cmdCari" id="cmdCari" value="Semak" type="button" onClick="javascript:carian()">
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
		<legend><b>Senarai Status Permohonan</b></legend>
        <table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="5%"><strong>Bil</strong></td>
              <td width="95%"><strong>Status</strong></td>
            </tr>
          #set ($list = "")
          #foreach ($list in $SenaraiFail)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
          <tr>
            <td class="$row">$list.bil</td>
            #if($list.idPermohonan == '')
            <td class="$row">$list.keterangan</td>
            #else
            <td class="$row"><a href="javascript:papar('$list.idPermohonan','$list.idStatus','$list.bil','$list.idPermohonanSimati','$list.tarikhMohon','$list.flagjenisfail','$list.seksyen','$list.idSimati')" class="style1">$list.keterangan</a></td>
            #end
          </tr>
          #end
          </tbody>
        </table>
		</fieldset>
	</td>
  </tr>
</table>

<script>
function carian(){
	document.${formName}.action.value = "";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.submit();
}
function papar(idPermohonan,idStatus,bil,idPermohonanSimati,tarikhMohon,jenisfail,seksyen,idSimati) {

	//keputusan rayuan
	if (bil == '7'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&command=semakKeputusanRayuan";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaanSek17&command=semakKeputusanRayuan";
		}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
	} else 
	//permohonan rayuan
	if (bil == '6'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=semakRayuan";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=semakRayuan";
		}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
	} else
		
	//perintah
	if (bil == '5'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
		}
			document.${formName}.idPermohonanSimati.value = idPermohonanSimati;
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idStatus.value = idStatus;
		
	} else 
	//keputusan perbicaraan
	if (bil == '4'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;
	
	} else 
	//notis perbicaraan
	if (bil == '3'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData";
		}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
	
	} else 
	//keputusan permohonan
	if (bil == '2'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
		}
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idpermohonansimati.value = idPermohonanSimati;
			document.${formName}.tarikh_mohon.value = tarikhMohon;
			document.${formName}.idSimati.value = idSimati;
		
	} else 
	//pendaftaran
	if (bil == '1' && (jenisfail == '1' || jenisfail == '2' )){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.command.value = "papar";
			document.${formName}.idSimati.value = idSimati;
	}
	if (bil == '1' && (jenisfail == '3' )){		
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8InternalKutipan&command=papar";
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.command.value = "papar";
	}
	
	document.${formName}.flagFromSenaraiFailSek8.value = "yes";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
</script>