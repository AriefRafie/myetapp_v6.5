<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="text" name="idFail" />
	<input type="hidden" name="actionPajakan" />
	<input type="hidden" name="mode" />
	<input type="text" name="initiateFlagBuka" id="initiateFlagBuka"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">

	<tr>
		<td>
    		<fieldset><legend><b>CARIAN</b></legend>
	        	<table width="100%" align="center" border="0">
		            <tr>
		              <td height="24" scope="row" align="right">Tajuk Fail :</td>
		              <td><input name="txtTajukFail" id="txtTajukFail" type="text" size="50" maxlength="50" style="text-transform:uppercase;" /></td>
		            </tr>
		            <tr>
		              <td height="24" scope="row" align="right">Nama Pemohon :</td>
		              <td><input name="txtNamaPemohon" id="txtNamaPemohon" type="text" size="50" maxlength="50" style="text-transform:uppercase;" /></td>
		            </tr>
	            	<tr>
		            	<td width="30%" height="24" scope="row" align="right">No Fail : </td>
		              	<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$!txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" ></td> 
		            </tr>
            		<tr>
              			<td width="30%" height="24" scope="row" align="right">Tarikh Terima : </td>
              			<td width="70%">
              				<input type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$!txdTarikhTerima" onblur="check_date(this)" size="9"/>
      						<a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
      					</td>
            		</tr>
            		<tr>
              			<td scope="row"></td>
              			<td>
              				<input type="button" class="stylobutton" name="cmdCari" id="cmdCari" value="Cari" onclick="javascript:carian()">
                			<input type="reset" class="stylobutton" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="javascript:kosongkan()"></td>
            		</tr>
            		<tr>
              			<td scope="row">&nbsp;</td>
              			<td>&nbsp;</td>
            		</tr>     		
        		</table>
	  		</fieldset>
    	</td>
	</tr>
  
	<tr>
    	<td>
    		<fieldset><legend><b>SENARAI PERMOHONAN</b></legend>
        
        		<table align="center" width="100%"> 
		            <tr>
		              <td colspan="5" scope="row">
		              	<input name="cmdDaftar" type="button" value="Daftar Permohonan Baru" onclick="javascript:daftarBaru()"/>
		              </td>
		            </tr>
		            <tr>
		              	<td colspan="5" scope="row">
		        		#parse("app/utils/record_paging.jsp")
		            	</td>
		            </tr>
		            <tr class="table_header">
				        <td width="3%"><b>Bil.</b></td>
				        <td width="20%"><b>No Fail</b></td>
				        <td width="37%"><b>Tajuk Fail</b></td>
				        <td width="17%"><b>Negeri</b></td>
				        <td width="23%"><b>Status</b></td>
		            </tr>
          			#set ($list = "")
          		#if ($senaraiFail.size() > 0)
          			#foreach ($list in $senaraiFail)
			            #if ($list.bil == '')
			                #set( $row = "row1" )
			            #elseif (($list.bil % 2) != 0)
			                #set( $row = "row1" )
			            #else 
			                #set( $row = "row2" )
			            #end
		          	<tr>
		            	<td class="$row" >$list.bil.</td>
		            	<td class="$row"><a href="javascript:papar('$list.idFail')" class="style1">$list.noFail</a>
		            	</td>
		            	<td class="$row" >$list.tajuk</td>
		            	<td class="$row" >$list.negeri</td>
		            	<!--<td class="$row" align="center">$list.tarikhTerima</td>-->
		            	<td class="$row">$list.status</td>
		          	</tr>
          			#end
          		#else
		          	<tr>
		            	<td class="row1" align="center" colspan="5">Tiada Rekod</td>
		          		<!--  <td class="row1">Tiada Rekod</td>
			            <td class="row1">&nbsp;</td>
			            <td class="row1" align="center">&nbsp;</td>
			            <td class="row1">&nbsp;</td> -->
		          	</tr>
          		#end
        		</table>
        
			</fieldset>
		</td>
	</tr>
</table>
<div id="setSessionIdFail_result"></div>

<script>
function carian(){
	document.${formName}.actionPajakan.value = "";
	document.${formName}.submit();
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.submit();
}

function daftarBaru(){
	document.${formName}.actionPajakan.value = "daftarBaru";	
	document.${formName}.submit();
}

function papar(idFail,idStatus){
	url = "../servlet/ekptg.view.online.htp.pajakan.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	
	if (idStatus == '6' || idStatus == '1' || idStatus == '12' || idStatus == '63'){
		document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.online.FrmOnlineMaklumatPajakanView";
		
	} else if (idStatus == '65'){
		document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanMemorandumJemaahMenteriView";
		
	} else if (idStatus == '69' || idStatus == '86' || idStatus == '87'){
		document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanPerjanjianPajakanView";
		
	} else if (idStatus == '71'){
		document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanBayaranPajakanCukaiTanahView&actionPajakan";
		
	}

	document.${formName}.mode.value = "view";
	//document.${formName}.actionPajakan.value = "papar";
	document.${formName}.submit();
}

function papar(idFail){
	document.${formName}.idFail.value = idFail;
	document.${formName}.initiateFlagBuka.value = "Y";
	//document.${formName}.actionPenyewaan.value = "paparMaklumatPenyewaan";
	document.${formName}.action = "$EkptgUtil.getTabID("Harta Tanah Persekutuan",$portal_role)?_portal_module=ekptg.view.htp.online.FrmOnlineMaklumatPajakanView";
	document.${formName}.submit();
}

</script>