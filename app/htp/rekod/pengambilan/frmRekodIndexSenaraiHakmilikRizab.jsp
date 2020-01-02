<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<table width="98%" border="0">
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			#parse("app/htp/rekod/pengambilan/frmRekodIndexCarianSenaraiHakmilikRizab.jsp")
			<br>
			<fieldset><legend>SENARAI HAKMILIK/RIZAB</legend>
				<table border="0" width="100%">
				    <tr>
				    	<td colspan="8">#parse("app/utils/record_paging.jsp") </td>
				    </tr>
					<tr class="table_header">
					  <td width="3%"><strong>Bil.</strong></td>
				   	  <td width="15%"><div align="left"><strong>No. Fail</strong></div></td>
				   	  <td width="12%"><div align="left"><strong>
				   	  #if ($idJenisTanah==1)
				   	  	No. Hakmilik
				   	  #elseif ($idJenisTanah==2)
				   	   	No. Warta
				   	  #else
				   	  	No. Hakmilik/Warta
				      #end
				      </strong></div></td>
				  	  <td width="10%"><div align="left"><strong>
				  	  #if ($idJenisTanah==2)
				   	   	No. Lot
				   	  #else
				  	  	No. Lot/PT
				      #end </strong></div></td>
				   	  <td width="15%"><div align="left"><strong>Status</strong></div></td>
				  	  <td width="40%"><div align="left"><strong>Kegunaan Tanah</strong></div></td>
				  </tr>
				##Kemaskini pd 21/09/2011 oleh Mohamad Rosli (Penyelesaian kpd page number apabila Vector kosong) 
				##foreach ($senarai in $SenaraiTanah)
				#foreach ($senarai in $SenaraiFail)
				  #set( $i = $velocityCount )
				    #if ( ($i % 2) != 1 )
				       #set( $row = "row2" )
				    #else
				       #set( $row = "row1" )
				    #end
				    <tr class="$row">
				    <td width="1%">$senarai.bil</td>
				    #if($senarai.bil != '')
				      #if($senarai.jenisTanah == 'M')     	
				       <td width="18%">
				       		<!-- <a href="javascript:hakmilik_detail('$senarai.idHakmilik','$senarai.statusSah');" class="style1"> -->
				       			$!senarai.noFail
				       		<!-- </a> -->
				       	</td>
				      #elseif($senarai.jenisTanah == 'R')
				   	    <td width="18%">
				   	    	<!-- <a href="javascript:rizab_detail('$senarai.idHakmilik','$senarai.statusSah');" class="style1"> -->
				   	    		$!senarai.noFail
				       		<!-- </a> -->
				   	    </td>
				      #else
				   	    <td width="18%">$!senarai.noFail</td>
				      #end    
				    #else
				    	<td width="18%">$!senarai.noFail</td>
				    #end
				   	<td width="8%"><div align="left">$!senarai.kodJenis $!senarai.noHakmilik </div></td>
				    <td width="8%"><div align="left">$!senarai.noLot</div></td>
				    <td width="5%"><div align="center">$!senarai.status</div></td>
				    <td width="23%">$!senarai.kegunaanTanah</td>
					</tr> 
				 #end
				</table>
			</fieldset>

		</td>
	</tr>
</table>
				        	
<input name="flagAdvSearch" type="hidden" value="$!flagAdvSearch" />

<script>
	
	function hakmilik_detail(id,status){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id+"&statusSah="+status;
		document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmCRBMaklumatPermohonanView";
		document.${formName}.submit();
	}
	
	function rizab_detail(id,status){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
		document.${formName}.submit();
	}
	
	function cari(){   
		field_ = document.${formName}.socJenisTanahtemp;
		for (i = 0; i < field_.length; i++){
			if(field_[i].checked==true){
				document.${formName}.socJenisTanah.value = field_[i].value;
			}
		}
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab");
		
	}
	
	function kosongCarian(idJenisTanah){
		if (idJenisTanah == '1'){
			document.${formName}.txtNoHakmilikC.value = "";
		} else if (idJenisTanah == '2'){
			document.${formName}.txtNoWartaC.value = "";
		} 
		document.${formName}.socJenisTanah.value = "0";
		document.${formName}.socStatus.value = "0";
		document.${formName}.socNegeri.value = "";
		document.${formName}.socDaerah.value = "";
		document.${formName}.socMukim.value = "";	
		document.${formName}.txtNoLotC.value = "";
		document.${formName}.txtNoFailC.value = "";
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";
		document.${formName}.submit();
	}
	
	function doChangeState() {
	  doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeState");
	}
	
	function doChangeDaerah() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeDaerah");
	}
	
	function doChangeMukim() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeMukim");
	}
	
	function doChangeKementerian() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeKementerian");
	}
	
	function deleteHakmilikBaru(id,id2){
		if ( !window.confirm("Adakah Anda Pasti?")) return;
			document.${formName}.command.value = "";
		
		doAjaxCall${formName}("","firstAction=deleteHakmilikBaru&idHakmilik="+id+"&idHakmilikBaru="+id2);
		
	}

	function doChangeTarafRizab(x) { 
		alert("XXXX");
	}

	function more(){
		document.${formName}.flagAdvSearch.value = "open";
		//document.${formName}.submit();
		//doAjaxCall${formName}("","flagAdvSearch=open");
		doAjaxCall${formName}("");
		
	}
	
	function less(){
		document.${formName}.flagAdvSearch.value = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}

</script>
