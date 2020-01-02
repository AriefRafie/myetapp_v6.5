<fieldset id="top">
<center>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset id="bottom">
	<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
			
			<table width="100%" border="0">   
                	<tr>
                    	<td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20" ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    			
    		#if($saiz_listTanah > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        		  <td width="4%" align="center"><b>No</b></td>
       			  <td width="17%"><b>No.Hakmilik</b></td>
               	  <td width="17%"><b>No.LOT/No.PT</b></td>                
               	  <td width="26%"><b>Mukim/Pekan/Bandar</b></td>
           		  <td width="16%"><b>Keluasan</b></td>
           		  <td width="20%"><b>Status Bantahan</b></td>
           		</tr>
        		
        		#if($saiz_listTanah!=0)
                    #foreach($listTanah in $listMaklumatTanah)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listTanah.bil</td>
                   <td class="$row">
                   <a href="javascript:screenBorangN('$!id_fail','$!id_permohonan','$!id_status','$!listTanah.id_hakmilik','$!listTanah.id_hakmilikpb')"><font color="blue">$!listTanah.no_hakmilik</font></a>
                   </td>
                   <td class="$row">$!listTanah.no_lotpt</td>  
                   <td class="$row">$!listTanah.nama_mukim</td>
                   <td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>		
                   <td class="$row">$!listTanah.status_bantahan</td>					
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="5">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listTanah > 5)
                </div>
            #end
	
	</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
			</td>
		</tr>
	</table>

</center>
</fieldset>
<input type="hidden" name="id_fail" value="$!id_fail">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_bantahan" >
<input type="hidden" name="id_hakmilik" />
<input type="hidden" name="id_hakmilikpb" />

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>

function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarOnline";
	document.${formName}.submit();
}
/*
function viewBorangN(id_fail,id_permohonan,id_status,id_hakmilik) {

	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_hakmilik.value = id_hakmilik;	
	
	if (id_status == '181' ) {		
		document.${formName}.command.value = "viewBorangN";	
	}	
	else{
		document.${formName}.command.value = "addBorangN";	
	}

	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarOnline";
	document.${formName}.submit();	
}
*/
function screenBorangN(id_fail,id_permohonan,id_status,id_hakmilik,id_hakmilikpb) {
	
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.command.value = "screenBorangN";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarOnline";
	document.${formName}.submit();

}
function cariLOT(id_permohonan) {

	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "papar_ListHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarOnline";
	document.${formName}.submit();
}

function kosongkanLOT(id_permohonan) {

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "papar_ListHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarOnline";
	document.${formName}.submit();
}
</script>