<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>
<table width="100%" border="0" >
	<tr>
		<td>
			&nbsp;
		</td>
    </tr>
	<tr>
		<td>
 			#parse("app/htp/utiliti/frmIndexCarianHeader.jsp")
			
		            <tr>
		              <td  width="29%"  scope="row" align="right">Nama Pemohon</td>
		              <td width="1%">:</td>		              
		              <td width="70%"><input name="txtNamaPemohon" id="txtNamaPemohon" type="text" size="43" maxlength="50" style="text-transform:uppercase;" /></td>
		            </tr>

            		<tr>
              			<td width="29%" scope="row" align="right">Tarikh Terima</td>
              			<td width="1%">:</td>
              			<td width="70%">
              				<input type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$!txdTarikhTerima" onblur="check_date(this)" size="11"/>
      						<a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
      					</td>
            		</tr>

			#parse("app/htp/utiliti/frmIndexCarianFooter.jsp")

    	</td>
	</tr>
  
	<tr>
    	<td>
    		<fieldset><legend><b>SENARAI PERMOHONAN</b></legend>
        
        		<table align="center" width="100%"> 
				#if (!$!jenisAkses.equals('Readonly'))
		            <tr>
		              <td colspan="5" scope="row">
		              	<input name="cmdDaftar" type="button" value="Daftar Permohonan Baru" onclick="javascript:daftarBaru()"/>
		              </td>
		            </tr>
				#end
		            <tr>
		              	<td colspan="5" scope="row">
		        		#parse("app/utils/record_paging.jsp")
		            	</td>
		            </tr>
		            <tr class="table_header">
				        <td width="3%"><b>Bil.</b></td>
				        <td width="20%"><b>No. Fail</b></td>
				        <td width="37%"><b>Tajuk Fail</b></td>
				        <td width="17%"><b>Negeri</b></td>
				        <td width="23%"><b>Status</b></td>
		            </tr>
          			#set ($list = "")
          		#if ($SenaraiFail.size() > 0)
          			#foreach ($list in $SenaraiFail)
			            #if ($list.bil == '')
			                #set( $row = "row1" )
			            #elseif (($list.bil % 2) != 0)
			                #set( $row = "row1" )
			            #else 
			                #set( $row = "row2" )
			            #end
		          	<tr>
		            	<td class="$row" >$list.bil.</td>
		            	<td class="$row"><a href="javascript:papar('$list.idFail','$list.idStatus','$list.idPermohonan')" class="style1">$list.noFail</a></td>
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
<input type="hidden" name="idPermohonan" value="">
		         		<input type="hidden" name="idFail" />
		         		<input type="hidden" name="actionPajakan" />
		         		<input type="hidden" name="mode" /></td>


<script>
	
//CARIAN
	function cmdChangeDaerahCarian() {
		//doAjaxCall${formName}("","mode=changeDaerah");
		carianFail();
	}
	
//CARIAN
	function carianFail() {
		document.${formName}.actionPajakan.value = "carian";
		doAjaxCall${formName}("");
		
	}
	
	function cancel() {
		document.${formName}.reset();
	}
	
function XXcarian(){
	document.${formName}.actionPajakan.value = "carian";
	document.${formName}.submit();
}

function XXkosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.submit();
}

function daftarBaru(){
	document.${formName}.actionPajakan.value = "daftarBaru";	
	//document.${formName}.submit();
	doAjaxCall${formName}("");
	
}

	function papar(idFail,idStatus,idPermohonan){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		
		if (idStatus == '6' || idStatus == '1' || idStatus == '12' || idStatus == '63'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanPendaftaranView";
			document.${formName}.action = "?_portal_module=ekptg.view.htp.negeri.pajakan.FrmPajakanPendaftaran";
			
		} else if (idStatus == '65'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanMemorandumJemaahMenteriView";
			document.${formName}.action = "?_portal_module=ekptg.view.htp.negeri.pajakan.FrmPajakanMJM";
			
		} else if (idStatus == '69' || idStatus == '86' || idStatus == '87'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanPerjanjianPajakanView";
			document.${formName}.action = "?_portal_module=ekptg.view.htp.negeri.pajakan.FrmPajakanPerjanjian";
			
		} else if (idStatus == '71'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanBayaranPajakanCukaiTanahView&actionPajakan";
			document.${formName}.action = "?_portal_module=ekptg.view.htp.negeri.pajakan.FrmPajakanBayaran";
			
		}else{
			document.${formName}.action = "?_portal_module=ekptg.view.htp.negeri.pajakan.FrmPajakanPendaftaran";
		
		}
	
		document.${formName}.mode.value = "view";
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.submit();
		
	}

function doChangeKementerian() {
	doAjaxCall${formName}("","mode=changeKementerian");
}

//page frmDaftar

//function doChangeKementerian() {
//	doAjaxCall${formName}("doChangeKementerian");
//}

function simpan() {
	
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih Kementerian.');
  		document.${formName}.socKementerian.focus(); 
		return; 
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih Agensi.');
  		document.${formName}.socAgensi.focus(); 
		return; 
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih Sub Urusan.');
  		document.${formName}.socAgensi.focus(); 
		return; 
	}
	if(document.${formName}.socStatusTanah.value == ""){
		alert('Sila pilih Status Tanah.');
  		document.${formName}.socStatusTanah.focus(); 
		return; 
	}
	if(document.${formName}.socJenisFail.value == ""){
		alert('Sila pilih Jenis Fail.');
  		document.${formName}.socJenisFail.focus(); 
		return; 
	}
	/*if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila masukkan No. Fail KJP.');
  		document.${formName}.txtNoFailKJP.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSuratKJP.value == ""){
		alert('Sila masukkan Tarikh Surat KJP.');
  		document.${formName}.tarikhSuratKJP.focus(); 
		return; 
	}
	
	if(document.${formName}.txtNoFailLain.value == ""){
		alert('Sila masukkan No. Fail Lain.');
  		document.${formName}.txtNoFailLain.focus(); 
		return; 
	}
	
	if(document.${formName}.tarikhAgihan.value == ""){
		alert('Sila masukkan Tarikh Agihan.');
  		document.${formName}.tarikhAgihan.focus(); 
		return; 
	}*/
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila masukkan Tajuk.');
  		document.${formName}.txtTajuk.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "daftarBaru";
		return;
	}
	
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.hitButton.value = "simpan";
	document.${formName}.mode.value = "view";	
	doAjaxCall${formName}("");
	//goToNext();
}

function kembali() {	
	document.${formName}.actionPajakan.value = "";
	document.${formName}.submit();
}

function goToNext_Lama(){
	document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanPendaftaranView";	
	document.${formName}.submit();
}
function goToNext(idFail){
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView";
	document.${formName}.submit();
	
}

function langkah1(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
</script>