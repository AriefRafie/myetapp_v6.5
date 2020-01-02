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
    		<fieldset><legend><b>CARIAN</b></legend>
	        	<table width="100%" align="center" border="0">
				  <tr>
				    <td width="29%"><div align="right">Negeri</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!socNegeri</td>
				  </tr>
				  <!--<tr>
				    <td width="29%"><div align="right">Daerah</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$socDaerah</td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">
				      <div align="right">Bandar/Pekan/Mukim</div>
				    </div></td>
				    <td width="1%">:</td>
				    <td width="70%">$socMukim</td>
				  </tr>-->
				  <tr>
				    <td width="29%"><div align="right">Kementerian</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!socKementerian</td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">Agensi</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!socAgensi</td>
				  </tr>
	            	<tr>
		            	<td width="29%" scope="row" align="right">No Fail</td>
		            	<td width="1%">:</td>	            	
		              	<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$!txtNoFail" size="43" maxlength="50" style="text-transform:uppercase;" > 
		            </tr>		            
		            <tr>
		              <td  width="29%" height="24" scope="row" align="right">Tajuk Fail</td>
		              <td width="1%">:</td>	              
		              <td><input name="txtTajukFail" id="txtTajukFail" type="text" size="43" maxlength="50" style="text-transform:uppercase;" /></td>
		            </tr>
		            <tr>
		              <td  width="29%"  scope="row" align="right">Nama Pemohon</td>
		              <td width="1%">:</td>		              
		              <td><input name="txtNamaPemohon" id="txtNamaPemohon" type="text" size="43" maxlength="50" style="text-transform:uppercase;" /></td>
		            </tr>

            		<tr>
              			<td width="29%" scope="row" align="right">Tarikh Terima</td>
              			<td width="1%">:</td>
              			<td width="70%">
              				<input type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$!txdTarikhTerima" onblur="check_date(this)" size="11"/>
      						<a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
      					</td>
            		</tr>
            		<tr>
              			<td width="29%">&nbsp;</td>
					    <td width="1%">&nbsp;</td>
					    <td width="70%">
              				<input type="button" class="stylobutton100" name="cmdCari" id="cmdCari" value="Cari" onclick="javascript:carian()">
                			<input type="reset" class="stylobutton100" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="javascript:kosongkan()">
                		</td>
            		</tr>
            		<!--<tr>
              			<td scope="row">&nbsp;</td>
              			<td>&nbsp;</td>
            		</tr>  -->
             		   		
        		</table>
	  		</fieldset>
    	</td>
	</tr>
  
	<tr>
    	<td>
    		<fieldset><legend><b>SENARAI FAIL</b></legend>
        
        		<table align="center" width="100%"> 
		            <!-- <tr>
		              <td colspan="5" scope="row">
		              	<input name="cmdDaftar" type="button" value="Daftar Permohonan Baru" onclick="javascript:daftarBaru()"/>
		              </td>
		            </tr> -->
		            <tr>
		              	<td colspan="5" scope="row">
		        		##parse("app/utils/record_paging.jsp")
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
<input type="hidden" name="idPermohonan" value=""/>
<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch"/>
<input type="hidden" name="idFail" />
<input type="hidden" name="actionPajakan" />
<input type="hidden" name="mode" />


<script>
	
function carian(){
	document.${formName}.actionPajakan.value = "carian";
	document.${formName}.submit();
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.submit();
}

	// bil
	function XdaftarBaru(){
		document.${formName}.actionPajakan.value = "daftarBaru";	
		//document.${formName}.submit();
		doAjaxCall${formName}("papar");
	
	}

	function papar(idFail,idStatus,idPermohonan){
		document.${formName}.mode.value = "view";
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.idFail.value = idFail;
		doAjaxCall${formName}("papar");

	}
	
	// butang [cmdDaftar]
	function daftarBaru(){
		//document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.mode.value = "newmaklumatbil";
		doAjaxCall${formName}("papar");
	}	
		
	function batalDaftarBil(){
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "BayaranPajakan";
		doAjaxCall${formName}("papar");
	}
	
	function doChangeTab(tabId) {
		document.${formName}.selectedTab.value = tabId;
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("papar");
	}
	
	function paparPajakanKadar(idPajakanK){
		//document.${formName}.idPajakan.value = idPajakan;
		document.${formName}.mode.value = "viewmaklumatbayaran";
		//document.${formName}.actionPajakan.value = "papar";
		doAjaxCall${formName}("papar","idpajakankadar="+idPajakanK);
	}
	
	function kemaskiniMaklumatBayaran(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.mode.value = "updatemaklumatbayaran";
		doAjaxCall${formName}("papar");
	}
	
	function simpanUpdateMaklumatBayaran(){		
		

		if(document.${formName}.txdTarikhMulaPajakan.value == ""){
			alert('Sila masukkan Tarikh Mula Pajakan.');
	  		document.${formName}.txdTarikhMulaPajakan.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhTamatPajakan.value == ""){
			alert('Sila masukkan Tarikh Tamat Pajakan');
	  		document.${formName}.txdTarikhTamatPajakan.focus(); 
			return; 
		}
		/*if(document.${formName}.txtTempoh.value == ""){
			alert('Sila masukkan Tempoh Pajakan.');
	  		document.${formName}.txtTempoh.focus(); 
			return; 
		}
		*/
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPajakan.value = "papar";
			return;
		}
		
		document.${formName}.mode.value = "saveupdatemaklumatbayaran";
		//document.${formName}.hitButton.value = "saveupdatePajakan";
		doAjaxCall${formName}("papar");
	}
	
	function hapusMaklumatBayaran(){
		document.${formName}.mode.value = "deletemaklumatbayaran";
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.hitButton.value = "hapusPajakan";
		doAjaxCall${formName}("papar");
	}
	
	function cal_tarikh_luput(){
  		var tr = document.${formName}.txdTarikhMulaPajakan.value;
  		var temp_tr = tr.substring(0,6)
  		var year_cur = tr.substring(6,10)
  		var tempoh = document.${formName}.txtTempoh.value; 
  		var luput = temp_tr+(parseInt(year_cur)+parseInt(tempoh));
  		document.${formName}.txdTarikhTamatPajakan.value = luput;
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
	document.${formName}.action = "$EkptgUtil.getTabID("Pajakan",$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanPendaftaranView";	
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


//Skrin frmPajakanTabAkuanMaklumatBayaran
	// Butang [Daftar Baru]
	function daftarMaklumatBayaran(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.mode.value = "newmaklumatbayaran";
		doAjaxCall${formName}("papar");
	}
	
	function simpanMaklumatBayaran(){
		
		/*if(document.${formName}.txdTarikhTandatangan.value == ""){
			alert('Sila masukkan Tarikh Tandatangan.');
  			document.${formName}.txdTarikhTandatangan.focus(); 
			return; 
		}*/
		if(document.${formName}.txdTarikhMulaPajakan.value == ""){
			alert('Sila masukkan Tarikh Mula Pajakan.');
	  		document.${formName}.txdTarikhMulaPajakan.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhTamatPajakan.value == ""){
			alert('Sila masukkan Tarikh Tamat Pajakan');
	  		document.${formName}.txdTarikhTamatPajakan.focus(); 
			return; 
		}
		/*
		if(document.${formName}.txtTempoh.value == ""){
			alert('Sila masukkan Tempoh Pajakan.');
  			document.${formName}.txtTempoh.focus(); 
			return; 
		}
		*/
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			return;
		}
	
		document.${formName}.mode.value = "simpanMaklumatBayaran";
		//document.${formName}.hitButton.value = "savePajakan";
		doAjaxCall${formName}("papar");
	}
	
	function batalMaklumatBayaran(){
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("papar");
		
	}
	
	function getMaklumatBil(){
		document.${formName}.hitButton.value = "changebiltahun";
		doAjaxCall${formName}("papar");
	
	}
	
	function simpanMaklumatBil(){
		if(document.${formName}.Form_tahun.value == ""){
			alert('Sila pilih Tahun.');
	  		document.${formName}.Form_tahun.focus(); 
			return; 
		}
	
		document.${formName}.mode.value = "simpanmaklumatbil";
		doAjaxCall${formName}("papar");

	}
	

</script>